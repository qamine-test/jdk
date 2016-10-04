/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.bwt.peer.ChoicePeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.bccessibility.*;


/**
 * The <code>Choice</code> clbss presents b pop-up menu of choices.
 * The current choice is displbyed bs the title of the menu.
 * <p>
 * The following code exbmple produces b pop-up menu:
 *
 * <hr><blockquote><pre>
 * Choice ColorChooser = new Choice();
 * ColorChooser.bdd("Green");
 * ColorChooser.bdd("Red");
 * ColorChooser.bdd("Blue");
 * </pre></blockquote><hr>
 * <p>
 * After this choice menu hbs been bdded to b pbnel,
 * it bppebrs bs follows in its normbl stbte:
 * <p>
 * <img src="doc-files/Choice-1.gif" blt="The following text describes the grbphic"
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * In the picture, <code>"Green"</code> is the current choice.
 * Pushing the mouse button down on the object cbuses b menu to
 * bppebr with the current choice highlighted.
 * <p>
 * Some nbtive plbtforms do not support brbitrbry resizing of
 * <code>Choice</code> components bnd the behbvior of
 * <code>setSize()/getSize()</code> is bound by
 * such limitbtions.
 * Nbtive GUI <code>Choice</code> components' size bre often bound by such
 * bttributes bs font size bnd length of items contbined within
 * the <code>Choice</code>.
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public clbss Choice extends Component implements ItemSelectbble, Accessible {
    /**
     * The items for the <code>Choice</code>.
     * This cbn be b <code>null</code> vblue.
     * @seribl
     * @see #bdd(String)
     * @see #bddItem(String)
     * @see #getItem(int)
     * @see #getItemCount()
     * @see #insert(String, int)
     * @see #remove(String)
     */
    Vector<String> pItems;

    /**
     * The index of the current choice for this <code>Choice</code>
     * or -1 if nothing is selected.
     * @seribl
     * @see #getSelectedItem()
     * @see #select(int)
     */
    int selectedIndex = -1;

    trbnsient ItemListener itemListener;

    privbte stbtic finbl String bbse = "choice";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4075310674757313071L;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        /* initiblize JNI field bnd method ids */
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Crebtes b new choice menu. The menu initiblly hbs no items in it.
     * <p>
     * By defbult, the first item bdded to the choice menu becomes the
     * selected item, until b different selection is mbde by the user
     * by cblling one of the <code>select</code> methods.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       #select(int)
     * @see       #select(jbvb.lbng.String)
     */
    public Choice() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        pItems = new Vector<>();
    }

    /**
     * Constructs b nbme for this component.  Cblled by
     * <code>getNbme</code> when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (Choice.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the <code>Choice</code>'s peer.  This peer bllows us
     * to chbnge the look
     * of the <code>Choice</code> without chbnging its functionblity.
     * @see     jbvb.bwt.Toolkit#crebteChoice(jbvb.bwt.Choice)
     * @see     jbvb.bwt.Component#getToolkit()
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteChoice(this);
            super.bddNotify();
        }
    }

    /**
     * Returns the number of items in this <code>Choice</code> menu.
     *
     * @return the number of items in this <code>Choice</code> menu
     * @see     #getItem
     * @since   1.1
     */
    public int getItemCount() {
        return countItems();
    }

    /**
     * Returns the number of items in this {@code Choice} menu.
     *
     * @return the number of items in this {@code Choice} menu
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getItemCount()</code>.
     */
    @Deprecbted
    public int countItems() {
        return pItems.size();
    }

    /**
     * Gets the string bt the specified index in this
     * <code>Choice</code> menu.
     *
     * @pbrbm  index the index bt which to begin
     * @return the item bt the specified index
     * @see    #getItemCount
     */
    public String getItem(int index) {
        return getItemImpl(index);
    }

    /*
     * This is cblled by the nbtive code, so client code cbn't
     * be cblled on the toolkit threbd.
     */
    finbl String getItemImpl(int index) {
        return pItems.elementAt(index);
    }

    /**
     * Adds bn item to this <code>Choice</code> menu.
     * @pbrbm      item    the item to be bdded
     * @exception  NullPointerException   if the item's vblue is
     *                  <code>null</code>
     * @since      1.1
     */
    public void bdd(String item) {
        bddItem(item);
    }

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.1.  Plebse use the
     * <code>bdd</code> method instebd.
     * <p>
     * Adds bn item to this <code>Choice</code> menu.
     * @pbrbm item the item to be bdded
     * @exception NullPointerException if the item's vblue is equbl to
     *          <code>null</code>
     */
    public void bddItem(String item) {
        synchronized (this) {
            insertNoInvblidbte(item, pItems.size());
        }

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Inserts bn item to this <code>Choice</code>,
     * but does not invblidbte the <code>Choice</code>.
     * Client methods must provide their own synchronizbtion before
     * invoking this method.
     * @pbrbm item the item to be bdded
     * @pbrbm index the new item position
     * @exception NullPointerException if the item's vblue is equbl to
     *          <code>null</code>
     */
    privbte void insertNoInvblidbte(String item, int index) {
        if (item == null) {
            throw new
                NullPointerException("cbnnot bdd null item to Choice");
        }
        pItems.insertElementAt(item, index);
        ChoicePeer peer = (ChoicePeer)this.peer;
        if (peer != null) {
            peer.bdd(item, index);
        }
        // no selection or selection shifted up
        if (selectedIndex < 0 || selectedIndex >= index) {
            select(0);
        }
    }


    /**
     * Inserts the item into this choice bt the specified position.
     * Existing items bt bn index grebter thbn or equbl to
     * <code>index</code> bre shifted up by one to bccommodbte
     * the new item.  If <code>index</code> is grebter thbn or
     * equbl to the number of items in this choice,
     * <code>item</code> is bdded to the end of this choice.
     * <p>
     * If the item is the first one being bdded to the choice,
     * then the item becomes selected.  Otherwise, if the
     * selected item wbs one of the items shifted, the first
     * item in the choice becomes the selected item.  If the
     * selected item wbs no bmong those shifted, it rembins
     * the selected item.
     * @pbrbm item the non-<code>null</code> item to be inserted
     * @pbrbm index the position bt which the item should be inserted
     * @exception IllegblArgumentException if index is less thbn 0
     */
    public void insert(String item, int index) {
        synchronized (this) {
            if (index < 0) {
                throw new IllegblArgumentException("index less thbn zero.");
            }
            /* if the index grebter thbn item count, bdd item to the end */
            index = Mbth.min(index, pItems.size());

            insertNoInvblidbte(item, index);
        }

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Removes the first occurrence of <code>item</code>
     * from the <code>Choice</code> menu.  If the item
     * being removed is the currently selected item,
     * then the first item in the choice becomes the
     * selected item.  Otherwise, the currently selected
     * item rembins selected (bnd the selected index is
     * updbted bccordingly).
     * @pbrbm      item  the item to remove from this <code>Choice</code> menu
     * @exception  IllegblArgumentException  if the item doesn't
     *                     exist in the choice menu
     * @since      1.1
     */
    public void remove(String item) {
        synchronized (this) {
            int index = pItems.indexOf(item);
            if (index < 0) {
                throw new IllegblArgumentException("item " + item +
                                                   " not found in choice");
            } else {
                removeNoInvblidbte(index);
            }
        }

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Removes bn item from the choice menu
     * bt the specified position.  If the item
     * being removed is the currently selected item,
     * then the first item in the choice becomes the
     * selected item.  Otherwise, the currently selected
     * item rembins selected (bnd the selected index is
     * updbted bccordingly).
     * @pbrbm      position the position of the item
     * @throws IndexOutOfBoundsException if the specified
     *          position is out of bounds
     * @since      1.1
     */
    public void remove(int position) {
        synchronized (this) {
            removeNoInvblidbte(position);
        }

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Removes bn item from the <code>Choice</code> bt the
     * specified position, but does not invblidbte the <code>Choice</code>.
     * Client methods must provide their
     * own synchronizbtion before invoking this method.
     * @pbrbm      position   the position of the item
     */
    privbte void removeNoInvblidbte(int position) {
        pItems.removeElementAt(position);
        ChoicePeer peer = (ChoicePeer)this.peer;
        if (peer != null) {
            peer.remove(position);
        }
        /* Adjust selectedIndex if selected item wbs removed. */
        if (pItems.size() == 0) {
            selectedIndex = -1;
        } else if (selectedIndex == position) {
            select(0);
        } else if (selectedIndex > position) {
            select(selectedIndex-1);
        }
    }


    /**
     * Removes bll items from the choice menu.
     * @see       #remove
     * @since     1.1
     */
    public void removeAll() {
        synchronized (this) {
            if (peer != null) {
                ((ChoicePeer)peer).removeAll();
            }
            pItems.removeAllElements();
            selectedIndex = -1;
        }

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Gets b representbtion of the current choice bs b string.
     * @return    b string representbtion of the currently
     *                     selected item in this choice menu
     * @see       #getSelectedIndex
     */
    public synchronized String getSelectedItem() {
        return (selectedIndex >= 0) ? getItem(selectedIndex) : null;
    }

    /**
     * Returns bn brrby (length 1) contbining the currently selected
     * item.  If this choice hbs no items, returns <code>null</code>.
     * @see ItemSelectbble
     */
    public synchronized Object[] getSelectedObjects() {
        if (selectedIndex >= 0) {
            Object[] items = new Object[1];
            items[0] = getItem(selectedIndex);
            return items;
        }
        return null;
    }

    /**
     * Returns the index of the currently selected item.
     * If nothing is selected, returns -1.
     *
     * @return the index of the currently selected item, or -1 if nothing
     *  is currently selected
     * @see #getSelectedItem
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Sets the selected item in this <code>Choice</code> menu to be the
     * item bt the specified position.
     *
     * <p>Note thbt this method should be primbrily used to
     * initiblly select bn item in this component.
     * Progrbmmbticblly cblling this method will <i>not</i> trigger
     * bn <code>ItemEvent</code>.  The only wby to trigger bn
     * <code>ItemEvent</code> is by user interbction.
     *
     * @pbrbm      pos      the position of the selected item
     * @exception  IllegblArgumentException if the specified
     *                            position is grebter thbn the
     *                            number of items or less thbn zero
     * @see        #getSelectedItem
     * @see        #getSelectedIndex
     */
    public synchronized void select(int pos) {
        if ((pos >= pItems.size()) || (pos < 0)) {
            throw new IllegblArgumentException("illegbl Choice item position: " + pos);
        }
        if (pItems.size() > 0) {
            selectedIndex = pos;
            ChoicePeer peer = (ChoicePeer)this.peer;
            if (peer != null) {
                peer.select(pos);
            }
        }
    }

    /**
     * Sets the selected item in this <code>Choice</code> menu
     * to be the item whose nbme is equbl to the specified string.
     * If more thbn one item mbtches (is equbl to) the specified string,
     * the one with the smbllest index is selected.
     *
     * <p>Note thbt this method should be primbrily used to
     * initiblly select bn item in this component.
     * Progrbmmbticblly cblling this method will <i>not</i> trigger
     * bn <code>ItemEvent</code>.  The only wby to trigger bn
     * <code>ItemEvent</code> is by user interbction.
     *
     * @pbrbm       str     the specified string
     * @see         #getSelectedItem
     * @see         #getSelectedIndex
     */
    public synchronized void select(String str) {
        int index = pItems.indexOf(str);
        if (index >= 0) {
            select(index);
        }
    }

    /**
     * Adds the specified item listener to receive item events from
     * this <code>Choice</code> menu.  Item events bre sent in response
     * to user input, but not in response to cblls to <code>select</code>.
     * If l is <code>null</code>, no exception is thrown bnd no bction
     * is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     * @pbrbm         l    the item listener
     * @see           #removeItemListener
     * @see           #getItemListeners
     * @see           #select
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
     * item events from this <code>Choice</code> menu.
     * If l is <code>null</code>, no exception is thrown bnd no
     * bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     * @pbrbm         l    the item listener
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
     * registered on this choice.
     *
     * @return bll of this choice's <code>ItemListener</code>s
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
     * upon this <code>Choice</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>Choice</code> <code>c</code>
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
     *          <code><em>Foo</em>Listener</code>s on this choice,
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
     * Processes events on this choice. If the event is bn
     * instbnce of <code>ItemEvent</code>, it invokes the
     * <code>processItemEvent</code> method. Otherwise, it cblls its
     * superclbss's <code>processEvent</code> method.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm      e the event
     * @see        jbvb.bwt.event.ItemEvent
     * @see        #processItemEvent
     * @since      1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof ItemEvent) {
            processItemEvent((ItemEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes item events occurring on this <code>Choice</code>
     * menu by dispbtching them to bny registered
     * <code>ItemListener</code> objects.
     * <p>
     * This method is not cblled unless item events bre
     * enbbled for this component. Item events bre enbbled
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
     * @see         #bddItemListener(ItemListener)
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected void processItemEvent(ItemEvent e) {
        ItemListener listener = itemListener;
        if (listener != null) {
            listener.itemStbteChbnged(e);
        }
    }

    /**
     * Returns b string representing the stbte of this <code>Choice</code>
     * menu. This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return    the pbrbmeter string of this <code>Choice</code> menu
     */
    protected String pbrbmString() {
        return super.pbrbmString() + ",current=" + getSelectedItem();
    }


    /* Seriblizbtion support.
     */

    /*
     * Choice Seribl Dbtb Version.
     * @seribl
     */
    privbte int choiceSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble <code>ItemListeners</code>
     * bs optionbl dbtb. The non-seriblizbble
     * <code>ItemListeners</code> bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @seriblDbtb <code>null</code> terminbted sequence of 0
     *   or more pbirs; the pbir consists of b <code>String</code>
     *   bnd bn <code>Object</code>; the <code>String</code> indicbtes
     *   the type of object bnd is one of the following:
     *   <code>itemListenerK</code> indicbting bn
     *     <code>ItemListener</code> object
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

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd if it
     * isn't <code>null</code> bdds b listener to receive
     * item events fired by the <code>Choice</code> item.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @seribl
     * @see #removeItemListener(ItemListener)
     * @see #bddItemListener(ItemListener)
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
      GrbphicsEnvironment.checkHebdless();
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
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>Choice</code>. For <code>Choice</code> components,
     * the <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleAWTChoice</code>. A new <code>AccessibleAWTChoice</code>
     * instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleAWTChoice</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>Choice</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTChoice();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Choice</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to choice user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTChoice extends AccessibleAWTComponent
        implements AccessibleAction
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 7175603582428509322L;

        /**
         * Constructor for {@code AccessibleAWTChoice}
         */
        public AccessibleAWTChoice() {
            super();
        }

        /**
         * Get the AccessibleAction bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleAction interfbce on behblf of itself.
         *
         * @return this object
         * @see AccessibleAction
         */
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.COMBO_BOX;
        }

        /**
         * Returns the number of bccessible bctions bvbilbble in this object
         * If there bre more thbn one, the first one is considered the "defbult"
         * bction of the object.
         *
         * @return the zero-bbsed number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 0;  //  To be fully implemented in b future relebse
        }

        /**
         * Returns b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         * @return b String description of the bction
         * @see #getAccessibleActionCount
         */
        public String getAccessibleActionDescription(int i) {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Perform the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the bction wbs performed; otherwise fblse.
         * @see #getAccessibleActionCount
         */
        public boolebn doAccessibleAction(int i) {
            return fblse;  //  To be fully implemented in b future relebse
        }

    } // inner clbss AccessibleAWTChoice

}
