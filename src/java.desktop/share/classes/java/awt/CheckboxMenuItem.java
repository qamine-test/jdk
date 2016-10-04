/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.peer.CheckboxMenuItemPeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvbx.bccessibility.*;
import sun.bwt.AWTAccessor;


/**
 * This clbss represents b check box thbt cbn be included in b menu.
 * Selecting the check box in the menu chbnges its stbte from
 * "on" to "off" or from "off" to "on."
 * <p>
 * The following picture depicts b menu which contbins bn instbnce
 * of <code>CheckBoxMenuItem</code>:
 * <p>
 * <img src="doc-files/MenuBbr-1.gif"
 * blt="Menu lbbeled Exbmples, contbining items Bbsic, Simple, Check, bnd More Exbmples. The Check item is b CheckBoxMenuItem instbnce, in the off stbte."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * The item lbbeled <code>Check</code> shows b check box menu item
 * in its "off" stbte.
 * <p>
 * When b check box menu item is selected, AWT sends bn item event to
 * the item. Since the event is bn instbnce of <code>ItemEvent</code>,
 * the <code>processEvent</code> method exbmines the event bnd pbsses
 * it blong to <code>processItemEvent</code>. The lbtter method redirects
 * the event to bny <code>ItemListener</code> objects thbt hbve
 * registered bn interest in item events generbted by this menu item.
 *
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.event.ItemEvent
 * @see         jbvb.bwt.event.ItemListener
 * @since       1.0
 */
public clbss CheckboxMenuItem extends MenuItem implements ItemSelectbble, Accessible {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        AWTAccessor.setCheckboxMenuItemAccessor(
            new AWTAccessor.CheckboxMenuItemAccessor() {
                public boolebn getStbte(CheckboxMenuItem cmi) {
                    return cmi.stbte;
                }
            });
    }

   /**
    * The stbte of b checkbox menu item
    * @seribl
    * @see #getStbte()
    * @see #setStbte(boolebn)
    */
    boolebn stbte = fblse;

    trbnsient ItemListener itemListener;

    privbte stbtic finbl String bbse = "chkmenuitem";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 6190621106981774043L;

    /**
     * Crebte b check box menu item with bn empty lbbel.
     * The item's stbte is initiblly set to "off."
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since   1.1
     */
    public CheckboxMenuItem() throws HebdlessException {
        this("", fblse);
    }

    /**
     * Crebte b check box menu item with the specified lbbel.
     * The item's stbte is initiblly set to "off."

     * @pbrbm     lbbel   b string lbbel for the check box menu item,
     *                or <code>null</code> for bn unlbbeled menu item.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public CheckboxMenuItem(String lbbel) throws HebdlessException {
        this(lbbel, fblse);
    }

    /**
     * Crebte b check box menu item with the specified lbbel bnd stbte.
     * @pbrbm      lbbel   b string lbbel for the check box menu item,
     *                     or <code>null</code> for bn unlbbeled menu item.
     * @pbrbm      stbte   the initibl stbte of the menu item, where
     *                     <code>true</code> indicbtes "on" bnd
     *                     <code>fblse</code> indicbtes "off."
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since      1.1
     */
    public CheckboxMenuItem(String lbbel, boolebn stbte)
        throws HebdlessException {
        super(lbbel);
        this.stbte = stbte;
    }

    /**
     * Construct b nbme for this MenuComponent.  Cblled by getNbme() when
     * the nbme is null.
     */
    String constructComponentNbme() {
        synchronized (CheckboxMenuItem.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the peer of the checkbox item.  This peer bllows us to
     * chbnge the look of the checkbox item without chbnging its
     * functionblity.
     * Most bpplicbtions do not cbll this method directly.
     * @see     jbvb.bwt.Toolkit#crebteCheckboxMenuItem(jbvb.bwt.CheckboxMenuItem)
     * @see     jbvb.bwt.Component#getToolkit()
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = Toolkit.getDefbultToolkit().crebteCheckboxMenuItem(this);
            super.bddNotify();
        }
    }

    /**
     * Determines whether the stbte of this check box menu item
     * is "on" or "off."
     *
     * @return      the stbte of this check box menu item, where
     *                     <code>true</code> indicbtes "on" bnd
     *                     <code>fblse</code> indicbtes "off"
     * @see        #setStbte
     */
    public boolebn getStbte() {
        return stbte;
    }

    /**
     * Sets this check box menu item to the specified stbte.
     * The boolebn vblue <code>true</code> indicbtes "on" while
     * <code>fblse</code> indicbtes "off."
     *
     * <p>Note thbt this method should be primbrily used to
     * initiblize the stbte of the check box menu item.
     * Progrbmmbticblly setting the stbte of the check box
     * menu item will <i>not</i> trigger
     * bn <code>ItemEvent</code>.  The only wby to trigger bn
     * <code>ItemEvent</code> is by user interbction.
     *
     * @pbrbm      b   <code>true</code> if the check box
     *             menu item is on, otherwise <code>fblse</code>
     * @see        #getStbte
     */
    public synchronized void setStbte(boolebn b) {
        stbte = b;
        CheckboxMenuItemPeer peer = (CheckboxMenuItemPeer)this.peer;
        if (peer != null) {
            peer.setStbte(b);
        }
    }

    /**
     * Returns the bn brrby (length 1) contbining the checkbox menu item
     * lbbel or null if the checkbox is not selected.
     * @see ItemSelectbble
     */
    public synchronized Object[] getSelectedObjects() {
        if (stbte) {
            Object[] items = new Object[1];
            items[0] = lbbel;
            return items;
        }
        return null;
    }

    /**
     * Adds the specified item listener to receive item events from
     * this check box menu item.  Item events bre sent in response to user
     * bctions, but not in response to cblls to setStbte().
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l the item listener
     * @see           #removeItemListener
     * @see           #getItemListeners
     * @see           #setStbte
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since         1.1
     */
    public synchronized void bddItemListener(ItemListener l) {
        if (l == null) {
            return;
        }
        itemListener = AWTEventMulticbster.bdd(itemListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified item listener so thbt it no longer receives
     * item events from this check box menu item.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l the item listener
     * @see           #bddItemListener
     * @see           #getItemListeners
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since         1.1
     */
    public synchronized void removeItemListener(ItemListener l) {
        if (l == null) {
            return;
        }
        itemListener = AWTEventMulticbster.remove(itemListener, l);
    }

    /**
     * Returns bn brrby of bll the item listeners
     * registered on this checkbox menuitem.
     *
     * @return bll of this checkbox menuitem's <code>ItemListener</code>s
     *         or bn empty brrby if no item
     *         listeners bre currently registered
     *
     * @see           #bddItemListener
     * @see           #removeItemListener
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since 1.4
     */
    public synchronized ItemListener[] getItemListeners() {
        return getListeners(ItemListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>CheckboxMenuItem</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>CheckboxMenuItem</code> <code>c</code>
     * for its item listeners with the following code:
     *
     * <pre>ItemListener[] ils = (ItemListener[])(c.getListeners(ItemListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this checkbox menuitem,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getItemListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ItemListener.clbss) {
            l = itemListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        if (e.id == ItemEvent.ITEM_STATE_CHANGED) {
            if ((eventMbsk & AWTEvent.ITEM_EVENT_MASK) != 0 ||
                itemListener != null) {
                return true;
            }
            return fblse;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this check box menu item.
     * If the event is bn instbnce of <code>ItemEvent</code>,
     * this method invokes the <code>processItemEvent</code> method.
     * If the event is not bn item event,
     * it invokes <code>processEvent</code> on the superclbss.
     * <p>
     * Check box menu items currently support only item events.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm        e the event
     * @see          jbvb.bwt.event.ItemEvent
     * @see          #processItemEvent
     * @since        1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof ItemEvent) {
            processItemEvent((ItemEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes item events occurring on this check box menu item by
     * dispbtching them to bny registered <code>ItemListener</code> objects.
     * <p>
     * This method is not cblled unless item events bre
     * enbbled for this menu item. Item events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>ItemListener</code> object is registered
     * vib <code>bddItemListener</code>.
     * <li>Item events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the item event
     * @see         jbvb.bwt.event.ItemEvent
     * @see         jbvb.bwt.event.ItemListener
     * @see         #bddItemListener
     * @see         jbvb.bwt.MenuItem#enbbleEvents
     * @since       1.1
     */
    protected void processItemEvent(ItemEvent e) {
        ItemListener listener = itemListener;
        if (listener != null) {
            listener.itemStbteChbnged(e);
        }
    }

    /*
     * Post bn ItemEvent bnd toggle stbte.
     */
    void doMenuEvent(long when, int modifiers) {
        setStbte(!stbte);
        Toolkit.getEventQueue().postEvent(
            new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED,
                          getLbbel(),
                          stbte ? ItemEvent.SELECTED :
                                  ItemEvent.DESELECTED));
    }

    /**
     * Returns b string representing the stbte of this
     * <code>CheckBoxMenuItem</code>. This
     * method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return     the pbrbmeter string of this check box menu item
     */
    public String pbrbmString() {
        return super.pbrbmString() + ",stbte=" + stbte;
    }

    /* Seriblizbtion support.
     */

    /*
     * Seribl Dbtb Version
     * @seribl
     */
    privbte int checkboxMenuItemSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble <code>ItemListeners</code>
     * bs optionbl dbtb.  The non-seriblizbble
     * <code>ItemListeners</code> bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @seriblDbtb <code>null</code> terminbted sequence of
     *  0 or more pbirs; the pbir consists of b <code>String</code>
     *  bnd bn <code>Object</code>; the <code>String</code> indicbtes
     *  the type of object bnd is one of the following:
     *  <code>itemListenerK</code> indicbting bn
     *    <code>ItemListener</code> object
     *
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see jbvb.bwt.Component#itemListenerK
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws jbvb.io.IOException
    {
      s.defbultWriteObject();

      AWTEventMulticbster.sbve(s, itemListenerK, itemListener);
      s.writeObject(null);
    }

    /*
     * Rebds the <code>ObjectInputStrebm</code> bnd if it
     * isn't <code>null</code> bdds b listener to receive
     * item events fired by the <code>Checkbox</code> menu item.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @seribl
     * @see removeActionListener()
     * @see bddActionListener()
     * @see #writeObject
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException
    {
      s.defbultRebdObject();

      Object keyOrNull;
      while(null != (keyOrNull = s.rebdObject())) {
        String key = ((String)keyOrNull).intern();

        if (itemListenerK == key)
          bddItemListener((ItemListener)(s.rebdObject()));

        else // skip vblue for unrecognized key
          s.rebdObject();
      }
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this CheckboxMenuItem.
     * For checkbox menu items, the AccessibleContext tbkes the
     * form of bn AccessibleAWTCheckboxMenuItem.
     * A new AccessibleAWTCheckboxMenuItem is crebted if necessbry.
     *
     * @return bn AccessibleAWTCheckboxMenuItem thbt serves bs the
     *         AccessibleContext of this CheckboxMenuItem
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTCheckboxMenuItem();
        }
        return bccessibleContext;
    }

    /**
     * Inner clbss of CheckboxMenuItem used to provide defbult support for
     * bccessibility.  This clbss is not mebnt to be used directly by
     * bpplicbtion developers, but is instebd mebnt only to be
     * subclbssed by menu component developers.
     * <p>
     * This clbss implements bccessibility support for the
     * <code>CheckboxMenuItem</code> clbss.  It provides bn implementbtion
     * of the Jbvb Accessibility API bppropribte to checkbox menu item
     * user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTCheckboxMenuItem extends AccessibleAWTMenuItem
        implements AccessibleAction, AccessibleVblue
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -1122642964303476L;

        /**
         * Get the AccessibleAction bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleAction interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Get the AccessibleVblue bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleVblue interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /**
         * Returns the number of Actions bvbilbble in this object.
         * If there is more thbn one, the first one is the "defbult"
         * bction.
         *
         * @return the number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 0;  //  To be fully implemented in b future relebse
        }

        /**
         * Return b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         */
        public String getAccessibleActionDescription(int i) {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Perform the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the bction wbs performed; otherwise fblse.
         */
        public boolebn doAccessibleAction(int i) {
            return fblse;    //  To be fully implemented in b future relebse
        }

        /**
         * Get the vblue of this object bs b Number.  If the vblue hbs not been
         * set, the return vblue will be null.
         *
         * @return vblue of the object
         * @see #setCurrentAccessibleVblue
         */
        public Number getCurrentAccessibleVblue() {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return true if the vblue wbs set; otherwise fblse
         * @see #getCurrentAccessibleVblue
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            return fblse;  //  To be fully implemented in b future relebse
        }

        /**
         * Get the minimum vblue of this object bs b Number.
         *
         * @return Minimum vblue of the object; null if this object does not
         * hbve b minimum vblue
         * @see #getMbximumAccessibleVblue
         */
        public Number getMinimumAccessibleVblue() {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Get the mbximum vblue of this object bs b Number.
         *
         * @return Mbximum vblue of the object; null if this object does not
         * hbve b mbximum vblue
         * @see #getMinimumAccessibleVblue
         */
        public Number getMbximumAccessibleVblue() {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }

    } // clbss AccessibleAWTMenuItem

}
