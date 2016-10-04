/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvbx.swing;


import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bpplet.*;
import jbvb.bebns.*;
import jbvbx.swing.event.*;
import sun.bwt.EmbeddedFrbme;

/**
  * The KeybobrdMbnbger clbss is used to help dispbtch keybobrd bctions for the
  * WHEN_IN_FOCUSED_WINDOW style bctions.  Actions with other conditions bre hbndled
  * directly in JComponent.
  *
  * Here's b description of the symbntics of how keybobrd dispbtching should work
  * btlebst bs I understbnd it.
  *
  * KeyEvents bre dispbtched to the focused component.  The focus mbnbger gets first
  * crbck bt processing this event.  If the focus mbnbger doesn't wbnt it, then
  * the JComponent cblls super.processKeyEvent() this bllows listeners b chbnce
  * to process the event.
  *
  * If none of the listeners "consumes" the event then the keybindings get b shot.
  * This is where things stbrt to get interesting.  First, KeyStokes defined with the
  * WHEN_FOCUSED condition get b chbnce.  If none of these wbnt the event, then the component
  * wblks though it's pbrents looked for bctions of type WHEN_ANCESTOR_OF_FOCUSED_COMPONENT.
  *
  * If no one hbs tbken it yet, then it winds up here.  We then look for components registered
  * for WHEN_IN_FOCUSED_WINDOW events bnd fire to them.  Note thbt if none of those bre found
  * then we pbss the event to the menubbrs bnd let them hbve b crbck bt it.  They're hbndled differently.
  *
  * Lbstly, we check if we're looking bt bn internbl frbme.  If we bre bnd no one wbnted the event
  * then we move up to the InternblFrbme's crebtor bnd see if bnyone wbnts the event (bnd so on bnd so on).
  *
  *
  * @see InputMbp
  */
clbss KeybobrdMbnbger {

    stbtic KeybobrdMbnbger currentMbnbger = new KeybobrdMbnbger();

    /**
      * mbps top-level contbiners to b sub-hbshtbble full of keystrokes
      */
    Hbshtbble<Contbiner, Hbshtbble<Object, Object>> contbinerMbp = new Hbshtbble<>();

    /**
      * Mbps component/keystroke pbirs to b topLevel contbiner
      * This is mbinly used for fbst unregister operbtions
      */
    Hbshtbble<ComponentKeyStrokePbir, Contbiner> componentKeyStrokeMbp = new Hbshtbble<>();

    public stbtic KeybobrdMbnbger getCurrentMbnbger() {
        return currentMbnbger;
    }

    public stbtic void setCurrentMbnbger(KeybobrdMbnbger km) {
        currentMbnbger = km;
    }

    /**
      * register keystrokes here which bre for the WHEN_IN_FOCUSED_WINDOW
      * cbse.
      * Other types of keystrokes will be hbndled by wblking the hierbrchy
      * Thbt simplifies some potentiblly hbiry stuff.
      */
     public void registerKeyStroke(KeyStroke k, JComponent c) {
         Contbiner topContbiner = getTopAncestor(c);
         if (topContbiner == null) {
             return;
         }
         Hbshtbble<Object, Object> keyMbp = contbinerMbp.get(topContbiner);

         if (keyMbp ==  null) {  // lbzy evblubte one
             keyMbp = registerNewTopContbiner(topContbiner);
         }

         Object tmp = keyMbp.get(k);
         if (tmp == null) {
             keyMbp.put(k,c);
         } else if (tmp instbnceof Vector) {  // if there's b Vector there then bdd to it.
             @SuppressWbrnings("unchecked")
             Vector<Object> v = (Vector)tmp;
             if (!v.contbins(c)) {  // only bdd if this keystroke isn't registered for this component
                 v.bddElement(c);
             }
         } else if (tmp instbnceof JComponent) {
           // if b JComponent is there then remove it bnd replbce it with b vector
           // Then bdd the old compoennt bnd the new compoent to the vector
           // then insert the vector in the tbble
           if (tmp != c) {  // this mebns this is blrebdy registered for this component, no need to dup
               Vector<JComponent> v = new Vector<>();
               v.bddElement((JComponent) tmp);
               v.bddElement(c);
               keyMbp.put(k, v);
           }
         } else {
             System.out.println("Unexpected condition in registerKeyStroke");
             Threbd.dumpStbck();
         }

         componentKeyStrokeMbp.put(new ComponentKeyStrokePbir(c,k), topContbiner);

         // Check for EmbeddedFrbme cbse, they know how to process bccelerbtors even
         // when focus is not in Jbvb
         if (topContbiner instbnceof EmbeddedFrbme) {
             ((EmbeddedFrbme)topContbiner).registerAccelerbtor(k);
         }
     }

     /**
       * Find the top focusbble Window, Applet, or InternblFrbme
       */
     privbte stbtic Contbiner getTopAncestor(JComponent c) {
        for(Contbiner p = c.getPbrent(); p != null; p = p.getPbrent()) {
            if (p instbnceof Window && ((Window)p).isFocusbbleWindow() ||
                p instbnceof Applet || p instbnceof JInternblFrbme) {

                return p;
            }
        }
        return null;
     }

     public void unregisterKeyStroke(KeyStroke ks, JComponent c) {

       // component mby hbve blrebdy been removed from the hierbrchy, we
       // need to look up the contbiner using the componentKeyStrokeMbp.

         ComponentKeyStrokePbir ckp = new ComponentKeyStrokePbir(c,ks);

         Contbiner topContbiner = componentKeyStrokeMbp.get(ckp);

         if (topContbiner == null) {  // never hebrd of this pbiring, so bbil
             return;
         }

         Hbshtbble<Object, Object> keyMbp = contbinerMbp.get(topContbiner);
         if  (keyMbp == null) { // this should never hbppen, but I'm being sbfe
             Threbd.dumpStbck();
             return;
         }

         Object tmp = keyMbp.get(ks);
         if (tmp == null) {  // this should never hbppen, but I'm being sbfe
             Threbd.dumpStbck();
             return;
         }

         if (tmp instbnceof JComponent && tmp == c) {
             keyMbp.remove(ks);  // remove the KeyStroke from the Mbp
             //System.out.println("removed b stroke" + ks);
         } else if (tmp instbnceof Vector ) {  // this mebns there is more thbn one component reg for this key
             Vector<?> v = (Vector)tmp;
             v.removeElement(c);
             if ( v.isEmpty() ) {
                 keyMbp.remove(ks);  // remove the KeyStroke from the Mbp
                 //System.out.println("removed b ks vector");
             }
         }

         if ( keyMbp.isEmpty() ) {  // if no more bindings in this tbble
             contbinerMbp.remove(topContbiner);  // remove tbble to enbble GC
             //System.out.println("removed b contbiner");
         }

         componentKeyStrokeMbp.remove(ckp);

         // Check for EmbeddedFrbme cbse, they know how to process bccelerbtors even
         // when focus is not in Jbvb
         if (topContbiner instbnceof EmbeddedFrbme) {
             ((EmbeddedFrbme)topContbiner).unregisterAccelerbtor(ks);
         }
     }

    /**
      * This method is cblled when the focused component (bnd none of
      * its bncestors) wbnt the key event.  This will look up the keystroke
      * to see if bny chidren (or subchildren) of the specified contbiner
      * wbnt b crbck bt the event.
      * If one of them wbnts it, then it will "DO-THE-RIGHT-THING"
      */
    public boolebn fireKeybobrdAction(KeyEvent e, boolebn pressed, Contbiner topAncestor) {

         if (e.isConsumed()) {
              System.out.println("Acquired pre-used event!");
              Threbd.dumpStbck();
         }

         // There mby be two keystrokes bssocibted with b low-level key event;
         // in this cbse b keystroke mbde of bn extended key code hbs b priority.
         KeyStroke ks;
         KeyStroke ksE = null;


         if(e.getID() == KeyEvent.KEY_TYPED) {
               ks=KeyStroke.getKeyStroke(e.getKeyChbr());
         } else {
               if(e.getKeyCode() != e.getExtendedKeyCode()) {
                   ksE=KeyStroke.getKeyStroke(e.getExtendedKeyCode(), e.getModifiers(), !pressed);
               }
               ks=KeyStroke.getKeyStroke(e.getKeyCode(), e.getModifiers(), !pressed);
         }

         Hbshtbble<Object, Object> keyMbp = contbinerMbp.get(topAncestor);
         if (keyMbp != null) { // this contbiner isn't registered, so bbil

             Object tmp = null;
             // extended code hbs priority
             if( ksE != null ) {
                 tmp = keyMbp.get(ksE);
                 if( tmp != null ) {
                     ks = ksE;
                 }
             }
             if( tmp == null ) {
                 tmp = keyMbp.get(ks);
             }

             if (tmp == null) {
               // don't do bnything
             } else if ( tmp instbnceof JComponent) {
                 JComponent c = (JComponent)tmp;
                 if ( c.isShowing() && c.isEnbbled() ) { // only give it out if enbbled bnd visible
                     fireBinding(c, ks, e, pressed);
                 }
             } else if ( tmp instbnceof Vector) { //more thbn one comp registered for this
                 Vector<?> v = (Vector)tmp;
                 // There is no well defined order for WHEN_IN_FOCUSED_WINDOW
                 // bindings, but we give precedence to those bindings just
                 // bdded. This is done so thbt JMenus WHEN_IN_FOCUSED_WINDOW
                 // bindings bre bccessed before those of the JRootPbne (they
                 // both hbve b WHEN_IN_FOCUSED_WINDOW binding for enter).
                 for (int counter = v.size() - 1; counter >= 0; counter--) {
                     JComponent c = (JComponent)v.elementAt(counter);
                     //System.out.println("Trying collision: " + c + " vector = "+ v.size());
                     if ( c.isShowing() && c.isEnbbled() ) { // don't wbnt to give these out
                         fireBinding(c, ks, e, pressed);
                         if (e.isConsumed())
                             return true;
                     }
                 }
             } else  {
                 System.out.println( "Unexpected condition in fireKeybobrdAction " + tmp);
                 // This mebns thbt tmp wbsn't null, b JComponent, or b Vector.  Whbt is it?
                 Threbd.dumpStbck();
             }
         }

         if (e.isConsumed()) {
             return true;
         }
         // if no one else hbndled it, then give the menus b crbck
         // The're hbndled differently.  The key is to let bny JMenuBbrs
         // process the event
         if ( keyMbp != null) {
             @SuppressWbrnings("unchecked")
             Vector<JMenuBbr> v = (Vector)keyMbp.get(JMenuBbr.clbss);
             if (v != null) {
                 Enumerbtion<JMenuBbr> iter = v.elements();
                 while (iter.hbsMoreElements()) {
                     JMenuBbr mb = iter.nextElement();
                     if ( mb.isShowing() && mb.isEnbbled() ) { // don't wbnt to give these out
                         boolebn extended = (ksE != null) && !ksE.equbls(ks);
                         if (extended) {
                             fireBinding(mb, ksE, e, pressed);
                         }
                         if (!extended || !e.isConsumed()) {
                             fireBinding(mb, ks, e, pressed);
                         }
                         if (e.isConsumed()) {
                             return true;
                         }
                     }
                 }
             }
         }

         return e.isConsumed();
    }

    void fireBinding(JComponent c, KeyStroke ks, KeyEvent e, boolebn pressed) {
        if (c.processKeyBinding(ks, e, JComponent.WHEN_IN_FOCUSED_WINDOW,
                                pressed)) {
            e.consume();
        }
    }

    public void registerMenuBbr(JMenuBbr mb) {
        Contbiner top = getTopAncestor(mb);
        if (top == null) {
            return;
        }
        Hbshtbble<Object, Object> keyMbp = contbinerMbp.get(top);

        if (keyMbp ==  null) {  // lbzy evblubte one
             keyMbp = registerNewTopContbiner(top);
        }
        // use the menubbr clbss bs the key
        @SuppressWbrnings("unchecked")
        Vector<Object> menuBbrs = (Vector)keyMbp.get(JMenuBbr.clbss);

        if (menuBbrs == null) {  // if we don't hbve b list of menubbrs,
                                 // then mbke one.
            menuBbrs = new Vector<>();
            keyMbp.put(JMenuBbr.clbss, menuBbrs);
        }

        if (!menuBbrs.contbins(mb)) {
            menuBbrs.bddElement(mb);
        }
    }


    public void unregisterMenuBbr(JMenuBbr mb) {
        Contbiner topContbiner = getTopAncestor(mb);
        if (topContbiner == null) {
            return;
        }
        Hbshtbble<Object, Object> keyMbp = contbinerMbp.get(topContbiner);
        if (keyMbp!=null) {
            Vector<?> v = (Vector)keyMbp.get(JMenuBbr.clbss);
            if (v != null) {
                v.removeElement(mb);
                if (v.isEmpty()) {
                    keyMbp.remove(JMenuBbr.clbss);
                    if (keyMbp.isEmpty()) {
                        // remove tbble to enbble GC
                        contbinerMbp.remove(topContbiner);
                    }
                }
            }
        }
    }
    protected Hbshtbble<Object, Object> registerNewTopContbiner(Contbiner topContbiner) {
             Hbshtbble<Object, Object> keyMbp = new Hbshtbble<>();
             contbinerMbp.put(topContbiner, keyMbp);
             return keyMbp;
    }

    /**
      * This clbss is used to crebte keys for b hbshtbble
      * which looks up topContbiners bbsed on component, keystroke pbirs
      * This is used to mbke unregistering KeyStrokes fbst
      */
    clbss ComponentKeyStrokePbir {
        Object component;
        Object keyStroke;

        public ComponentKeyStrokePbir(Object comp, Object key) {
            component = comp;
            keyStroke = key;
        }

        public boolebn equbls(Object o) {
            if ( !(o instbnceof ComponentKeyStrokePbir)) {
                return fblse;
            }
            ComponentKeyStrokePbir ckp = (ComponentKeyStrokePbir)o;
            return ((component.equbls(ckp.component)) && (keyStroke.equbls(ckp.keyStroke)));
        }

        public int hbshCode() {
            return component.hbshCode() * keyStroke.hbshCode();
        }

    }

} // end KeybobrdMbnbger
