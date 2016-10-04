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

import jbvb.bwt.peer.MenuItemPeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvbx.bccessibility.*;
import sun.bwt.AWTAccessor;

/**
 * All items in b menu must belong to the clbss
 * <code>MenuItem</code>, or one of its subclbsses.
 * <p>
 * The defbult <code>MenuItem</code> object embodies
 * b simple lbbeled menu item.
 * <p>
 * This picture of b menu bbr shows five menu items:
 * <IMG SRC="doc-files/MenuBbr-1.gif" blt="The following text describes this grbphic."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <br style="clebr:left;">
 * The first two items bre simple menu items, lbbeled
 * <code>"Bbsic"</code> bnd <code>"Simple"</code>.
 * Following these two items is b sepbrbtor, which is itself
 * b menu item, crebted with the lbbel <code>"-"</code>.
 * Next is bn instbnce of <code>CheckboxMenuItem</code>
 * lbbeled <code>"Check"</code>. The finbl menu item is b
 * submenu lbbeled <code>"More&nbsp;Exbmples"</code>,
 * bnd this submenu is bn instbnce of <code>Menu</code>.
 * <p>
 * When b menu item is selected, AWT sends bn bction event to
 * the menu item. Since the event is bn
 * instbnce of <code>ActionEvent</code>, the <code>processEvent</code>
 * method exbmines the event bnd pbsses it blong to
 * <code>processActionEvent</code>. The lbtter method redirects the
 * event to bny <code>ActionListener</code> objects thbt hbve
 * registered bn interest in bction events generbted by this
 * menu item.
 * <P>
 * Note thbt the subclbss <code>Menu</code> overrides this behbvior bnd
 * does not send bny event to the frbme until one of its subitems is
 * selected.
 *
 * @buthor Sbmi Shbio
 */
public clbss MenuItem extends MenuComponent implements Accessible {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        AWTAccessor.setMenuItemAccessor(
            new AWTAccessor.MenuItemAccessor() {
                public boolebn isEnbbled(MenuItem item) {
                    return item.enbbled;
                }

                public String getLbbel(MenuItem item) {
                    return item.lbbel;
                }

                public MenuShortcut getShortcut(MenuItem item) {
                    return item.shortcut;
                }

                public String getActionCommbndImpl(MenuItem item) {
                    return item.getActionCommbndImpl();
                }

                public boolebn isItemEnbbled(MenuItem item) {
                    return item.isItemEnbbled();
                }
            });
    }

    /**
     * A vblue to indicbte whether b menu item is enbbled
     * or not.  If it is enbbled, <code>enbbled</code> will
     * be set to true.  Else <code>enbbled</code> will
     * be set to fblse.
     *
     * @seribl
     * @see #isEnbbled()
     * @see #setEnbbled(boolebn)
     */
    boolebn enbbled = true;

    /**
     * <code>lbbel</code> is the lbbel of b menu item.
     * It cbn be bny string.
     *
     * @seribl
     * @see #getLbbel()
     * @see #setLbbel(String)
     */
    String lbbel;

    /**
     * This field indicbtes the commbnd thb hbs been issued
     * by b  pbrticulbr menu item.
     * By defbult the <code>bctionCommbnd</code>
     * is the lbbel of the menu item, unless it hbs been
     * set using setActionCommbnd.
     *
     * @seribl
     * @see #setActionCommbnd(String)
     * @see #getActionCommbnd()
     */
    String bctionCommbnd;

    /**
     * The eventMbsk is ONLY set by subclbsses vib enbbleEvents.
     * The mbsk should NOT be set when listeners bre registered
     * so thbt we cbn distinguish the difference between when
     * listeners request events bnd subclbsses request them.
     *
     * @seribl
     */
    long eventMbsk;

    trbnsient ActionListener bctionListener;

    /**
     * A sequence of key stokes thbt ib bssocibted with
     * b menu item.
     * Note :in 1.1.2 you must use setActionCommbnd()
     * on b menu item in order for its shortcut to
     * work.
     *
     * @seribl
     * @see #getShortcut()
     * @see #setShortcut(MenuShortcut)
     * @see #deleteShortcut()
     */
    privbte MenuShortcut shortcut = null;

    privbte stbtic finbl String bbse = "menuitem";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -21757335363267194L;

    /**
     * Constructs b new MenuItem with bn empty lbbel bnd no keybobrd
     * shortcut.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since    1.1
     */
    public MenuItem() throws HebdlessException {
        this("", null);
    }

    /**
     * Constructs b new MenuItem with the specified lbbel
     * bnd no keybobrd shortcut. Note thbt use of "-" in
     * b lbbel is reserved to indicbte b sepbrbtor between
     * menu items. By defbult, bll menu items except for
     * sepbrbtors bre enbbled.
     * @pbrbm       lbbel the lbbel for this menu item.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since       1.0
     */
    public MenuItem(String lbbel) throws HebdlessException {
        this(lbbel, null);
    }

    /**
     * Crebte b menu item with bn bssocibted keybobrd shortcut.
     * Note thbt use of "-" in b lbbel is reserved to indicbte
     * b sepbrbtor between menu items. By defbult, bll menu
     * items except for sepbrbtors bre enbbled.
     * @pbrbm       lbbel the lbbel for this menu item.
     * @pbrbm       s the instbnce of <code>MenuShortcut</code>
     *                       bssocibted with this menu item.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since       1.1
     */
    public MenuItem(String lbbel, MenuShortcut s) throws HebdlessException {
        this.lbbel = lbbel;
        this.shortcut = s;
    }

    /**
     * Construct b nbme for this MenuComponent.  Cblled by getNbme() when
     * the nbme is null.
     */
    String constructComponentNbme() {
        synchronized (MenuItem.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the menu item's peer.  The peer bllows us to modify the
     * bppebrbnce of the menu item without chbnging its functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = Toolkit.getDefbultToolkit().crebteMenuItem(this);
        }
    }

    /**
     * Gets the lbbel for this menu item.
     * @return  the lbbel of this menu item, or <code>null</code>
                       if this menu item hbs no lbbel.
     * @see     jbvb.bwt.MenuItem#setLbbel
     * @since   1.0
     */
    public String getLbbel() {
        return lbbel;
    }

    /**
     * Sets the lbbel for this menu item to the specified lbbel.
     * @pbrbm     lbbel   the new lbbel, or <code>null</code> for no lbbel.
     * @see       jbvb.bwt.MenuItem#getLbbel
     * @since     1.0
     */
    public synchronized void setLbbel(String lbbel) {
        this.lbbel = lbbel;
        MenuItemPeer peer = (MenuItemPeer)this.peer;
        if (peer != null) {
            peer.setLbbel(lbbel);
        }
    }

    /**
     * Checks whether this menu item is enbbled.
     *
     * @return {@code true} if the item is enbbled;
     *         otherwise {@code fblse}
     * @see        jbvb.bwt.MenuItem#setEnbbled
     * @since      1.0
     */
    public boolebn isEnbbled() {
        return enbbled;
    }

    /**
     * Sets whether or not this menu item cbn be chosen.
     * @pbrbm      b  if <code>true</code>, enbbles this menu item;
     *                       if <code>fblse</code>, disbbles it.
     * @see        jbvb.bwt.MenuItem#isEnbbled
     * @since      1.1
     */
    public synchronized void setEnbbled(boolebn b) {
        enbble(b);
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEnbbled(boolebn)</code>.
     */
    @Deprecbted
    public synchronized void enbble() {
        enbbled = true;
        MenuItemPeer peer = (MenuItemPeer)this.peer;
        if (peer != null) {
            peer.setEnbbled(true);
        }
    }

    /**
     * Sets whether or not this menu item cbn be chosen.
     *
     * @pbrbm  b if {@code true}, enbbles this menu item;
     *           otherwise disbbles
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEnbbled(boolebn)</code>.
     */
    @Deprecbted
    public void enbble(boolebn b) {
        if (b) {
            enbble();
        } else {
            disbble();
        }
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEnbbled(boolebn)</code>.
     */
    @Deprecbted
    public synchronized void disbble() {
        enbbled = fblse;
        MenuItemPeer peer = (MenuItemPeer)this.peer;
        if (peer != null) {
            peer.setEnbbled(fblse);
        }
    }

    /**
     * Get the <code>MenuShortcut</code> object bssocibted with this
     * menu item,
     * @return      the menu shortcut bssocibted with this menu item,
     *                   or <code>null</code> if none hbs been specified.
     * @see         jbvb.bwt.MenuItem#setShortcut
     * @since       1.1
     */
    public MenuShortcut getShortcut() {
        return shortcut;
    }

    /**
     * Set the <code>MenuShortcut</code> object bssocibted with this
     * menu item. If b menu shortcut is blrebdy bssocibted with
     * this menu item, it is replbced.
     * @pbrbm       s  the menu shortcut to bssocibte
     *                           with this menu item.
     * @see         jbvb.bwt.MenuItem#getShortcut
     * @since       1.1
     */
    public void setShortcut(MenuShortcut s) {
        shortcut = s;
        MenuItemPeer peer = (MenuItemPeer)this.peer;
        if (peer != null) {
            peer.setLbbel(lbbel);
        }
    }

    /**
     * Delete bny <code>MenuShortcut</code> object bssocibted
     * with this menu item.
     * @since      1.1
     */
    public void deleteShortcut() {
        shortcut = null;
        MenuItemPeer peer = (MenuItemPeer)this.peer;
        if (peer != null) {
            peer.setLbbel(lbbel);
        }
    }

    /*
     * Delete b mbtching MenuShortcut bssocibted with this MenuItem.
     * Used when iterbting Menus.
     */
    void deleteShortcut(MenuShortcut s) {
        if (s.equbls(shortcut)) {
            shortcut = null;
            MenuItemPeer peer = (MenuItemPeer)this.peer;
            if (peer != null) {
                peer.setLbbel(lbbel);
            }
        }
    }

    /*
     * The mbin gobl of this method is to post bn bppropribte event
     * to the event queue when menu shortcut is pressed. However,
     * in subclbsses this method mby do more thbn just posting
     * bn event.
     */
    void doMenuEvent(long when, int modifiers) {
        Toolkit.getEventQueue().postEvent(
            new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                            getActionCommbnd(), when, modifiers));
    }

    /*
     * Returns true if the item bnd bll its bncestors bre
     * enbbled, fblse otherwise
     */
    privbte finbl boolebn isItemEnbbled() {
        // Fix For 6185151: Menu shortcuts of bll menuitems within b menu
        // should be disbbled when the menu itself is disbbled
        if (!isEnbbled()) {
            return fblse;
        }
        MenuContbiner contbiner = getPbrent_NoClientCode();
        do {
            if (!(contbiner instbnceof Menu)) {
                return true;
            }
            Menu menu = (Menu)contbiner;
            if (!menu.isEnbbled()) {
                return fblse;
            }
            contbiner = menu.getPbrent_NoClientCode();
        } while (contbiner != null);
        return true;
    }

    /*
     * Post bn ActionEvent to the tbrget (on
     * keydown) bnd the item is enbbled.
     * Returns true if there is bn bssocibted shortcut.
     */
    boolebn hbndleShortcut(KeyEvent e) {
        MenuShortcut s = new MenuShortcut(e.getKeyCode(),
                             (e.getModifiers() & InputEvent.SHIFT_MASK) > 0);
        MenuShortcut sE = new MenuShortcut(e.getExtendedKeyCode(),
                             (e.getModifiers() & InputEvent.SHIFT_MASK) > 0);
        // Fix For 6185151: Menu shortcuts of bll menuitems within b menu
        // should be disbbled when the menu itself is disbbled
        if ((s.equbls(shortcut) || sE.equbls(shortcut)) && isItemEnbbled()) {
            // MenuShortcut mbtch -- issue bn event on keydown.
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                doMenuEvent(e.getWhen(), e.getModifiers());
            } else {
                // silently ebt key relebse.
            }
            return true;
        }
        return fblse;
    }

    MenuItem getShortcutMenuItem(MenuShortcut s) {
        return (s.equbls(shortcut)) ? this : null;
    }

    /**
     * Enbbles event delivery to this menu item for events
     * to be defined by the specified event mbsk pbrbmeter
     * <p>
     * Since event types bre butombticblly enbbled when b listener for
     * thbt type is bdded to the menu item, this method only needs
     * to be invoked by subclbsses of <code>MenuItem</code> which desire to
     * hbve the specified event types delivered to <code>processEvent</code>
     * regbrdless of whether b listener is registered.
     *
     * @pbrbm       eventsToEnbble the event mbsk defining the event types
     * @see         jbvb.bwt.MenuItem#processEvent
     * @see         jbvb.bwt.MenuItem#disbbleEvents
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected finbl void enbbleEvents(long eventsToEnbble) {
        eventMbsk |= eventsToEnbble;
        newEventsOnly = true;
    }

    /**
     * Disbbles event delivery to this menu item for events
     * defined by the specified event mbsk pbrbmeter.
     *
     * @pbrbm       eventsToDisbble the event mbsk defining the event types
     * @see         jbvb.bwt.MenuItem#processEvent
     * @see         jbvb.bwt.MenuItem#enbbleEvents
     * @see         jbvb.bwt.Component#disbbleEvents
     * @since       1.1
     */
    protected finbl void disbbleEvents(long eventsToDisbble) {
        eventMbsk &= ~eventsToDisbble;
    }

    /**
     * Sets the commbnd nbme of the bction event thbt is fired
     * by this menu item.
     * <p>
     * By defbult, the bction commbnd is set to the lbbel of
     * the menu item.
     * @pbrbm       commbnd   the bction commbnd to be set
     *                                for this menu item.
     * @see         jbvb.bwt.MenuItem#getActionCommbnd
     * @since       1.1
     */
    public void setActionCommbnd(String commbnd) {
        bctionCommbnd = commbnd;
    }

    /**
     * Gets the commbnd nbme of the bction event thbt is fired
     * by this menu item.
     *
     * @return the bction commbnd nbme
     * @see jbvb.bwt.MenuItem#setActionCommbnd
     * @since 1.1
     */
    public String getActionCommbnd() {
        return getActionCommbndImpl();
    }

    // This is finbl so it cbn be cblled on the Toolkit threbd.
    finbl String getActionCommbndImpl() {
        return (bctionCommbnd == null? lbbel : bctionCommbnd);
    }

    /**
     * Adds the specified bction listener to receive bction events
     * from this menu item.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm      l the bction listener.
     * @see        #removeActionListener
     * @see        #getActionListeners
     * @see        jbvb.bwt.event.ActionEvent
     * @see        jbvb.bwt.event.ActionListener
     * @since      1.1
     */
    public synchronized void bddActionListener(ActionListener l) {
        if (l == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.bdd(bctionListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified bction listener so it no longer receives
     * bction events from this menu item.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm      l the bction listener.
     * @see        #bddActionListener
     * @see        #getActionListeners
     * @see        jbvb.bwt.event.ActionEvent
     * @see        jbvb.bwt.event.ActionListener
     * @since      1.1
     */
    public synchronized void removeActionListener(ActionListener l) {
        if (l == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.remove(bctionListener, l);
    }

    /**
     * Returns bn brrby of bll the bction listeners
     * registered on this menu item.
     *
     * @return bll of this menu item's <code>ActionListener</code>s
     *         or bn empty brrby if no bction
     *         listeners bre currently registered
     *
     * @see        #bddActionListener
     * @see        #removeActionListener
     * @see        jbvb.bwt.event.ActionEvent
     * @see        jbvb.bwt.event.ActionListener
     * @since 1.4
     */
    public synchronized ActionListener[] getActionListeners() {
        return getListeners(ActionListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>MenuItem</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>MenuItem</code> <code>m</code>
     * for its bction listeners with the following code:
     *
     * <pre>ActionListener[] bls = (ActionListener[])(m.getListeners(ActionListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this menu item,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getActionListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ActionListener.clbss) {
            l = bctionListener;
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    /**
     * Processes events on this menu item. If the event is bn
     * instbnce of <code>ActionEvent</code>, it invokes
     * <code>processActionEvent</code>, bnother method
     * defined by <code>MenuItem</code>.
     * <p>
     * Currently, menu items only support bction events.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the event
     * @see         jbvb.bwt.MenuItem#processActionEvent
     * @since       1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof ActionEvent) {
            processActionEvent((ActionEvent)e);
        }
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        if (e.id == ActionEvent.ACTION_PERFORMED) {
            if ((eventMbsk & AWTEvent.ACTION_EVENT_MASK) != 0 ||
                bctionListener != null) {
                return true;
            }
            return fblse;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes bction events occurring on this menu item,
     * by dispbtching them to bny registered
     * <code>ActionListener</code> objects.
     * This method is not cblled unless bction events bre
     * enbbled for this component. Action events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>ActionListener</code> object is registered
     * vib <code>bddActionListener</code>.
     * <li>Action events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the bction event
     * @see         jbvb.bwt.event.ActionEvent
     * @see         jbvb.bwt.event.ActionListener
     * @see         jbvb.bwt.MenuItem#enbbleEvents
     * @since       1.1
     */
    protected void processActionEvent(ActionEvent e) {
        ActionListener listener = bctionListener;
        if (listener != null) {
            listener.bctionPerformed(e);
        }
    }

    /**
     * Returns b string representing the stbte of this <code>MenuItem</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return the pbrbmeter string of this menu item
     */
    public String pbrbmString() {
        String str = ",lbbel=" + lbbel;
        if (shortcut != null) {
            str += ",shortcut=" + shortcut;
        }
        return super.pbrbmString() + str;
    }


    /* Seriblizbtion support.
     */

    /**
     * Menu item seriblized dbtb version.
     *
     * @seribl
     */
    privbte int menuItemSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble <code>ActionListeners</code>
     * bs optionbl dbtb. The non-seriblizbble listeners bre
     * detected bnd no bttempt is mbde to seriblize them.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @seriblDbtb <code>null</code> terminbted sequence of 0
     *   or more pbirs; the pbir consists of b <code>String</code>
     *   bnd bn <code>Object</code>; the <code>String</code>
     *   indicbtes the type of object bnd is one of the following:
     *   <code>bctionListenerK</code> indicbting bn
     *     <code>ActionListener</code> object
     *
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws IOException
    {
      s.defbultWriteObject();

      AWTEventMulticbster.sbve(s, bctionListenerK, bctionListener);
      s.writeObject(null);
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd if it
     * isn't <code>null</code> bdds b listener to receive
     * bction events fired by the <code>Menu</code> Item.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see #removeActionListener(ActionListener)
     * @see #bddActionListener(ActionListener)
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
      // HebdlessException will be thrown from MenuComponent's rebdObject
      s.defbultRebdObject();

      Object keyOrNull;
      while(null != (keyOrNull = s.rebdObject())) {
        String key = ((String)keyOrNull).intern();

        if (bctionListenerK == key)
          bddActionListener((ActionListener)(s.rebdObject()));

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
     * Gets the AccessibleContext bssocibted with this MenuItem.
     * For menu items, the AccessibleContext tbkes the form of bn
     * AccessibleAWTMenuItem.
     * A new AccessibleAWTMenuItem instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTMenuItem thbt serves bs the
     *         AccessibleContext of this MenuItem
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTMenuItem();
        }
        return bccessibleContext;
    }

    /**
     * Inner clbss of MenuItem used to provide defbult support for
     * bccessibility.  This clbss is not mebnt to be used directly by
     * bpplicbtion developers, but is instebd mebnt only to be
     * subclbssed by menu component developers.
     * <p>
     * This clbss implements bccessibility support for the
     * <code>MenuItem</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to menu item user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTMenuItem extends AccessibleAWTMenuComponent
        implements AccessibleAction, AccessibleVblue
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -217847831945965825L;

        /**
         * Get the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be null if this
         * object does not hbve b nbme
         */
        public String getAccessibleNbme() {
            if (bccessibleNbme != null) {
                return bccessibleNbme;
            } else {
                if (getLbbel() == null) {
                    return super.getAccessibleNbme();
                } else {
                    return getLbbel();
                }
            }
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU_ITEM;
        }

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
         * Returns the number of Actions bvbilbble in this object.  The
         * defbult behbvior of b menu item is to hbve one bction.
         *
         * @return 1, the number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 1;
        }

        /**
         * Return b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         */
        public String getAccessibleActionDescription(int i) {
            if (i == 0) {
                // [[[PENDING:  WDW -- need to provide b locblized string]]]
                return "click";
            } else {
                return null;
            }
        }

        /**
         * Perform the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the bction wbs performed; otherwise fblse.
         */
        public boolebn doAccessibleAction(int i) {
            if (i == 0) {
                // Simulbte b button click
                Toolkit.getEventQueue().postEvent(
                        new ActionEvent(MenuItem.this,
                                        ActionEvent.ACTION_PERFORMED,
                                        MenuItem.this.getActionCommbnd(),
                                        EventQueue.getMostRecentEventTime(),
                                        0));
                return true;
            } else {
                return fblse;
            }
        }

        /**
         * Get the vblue of this object bs b Number.
         *
         * @return An Integer of 0 if this isn't selected or bn Integer of 1 if
         * this is selected.
         * @see jbvbx.swing.AbstrbctButton#isSelected()
         */
        public Number getCurrentAccessibleVblue() {
            return Integer.vblueOf(0);
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return True if the vblue wbs set.
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            return fblse;
        }

        /**
         * Get the minimum vblue of this object bs b Number.
         *
         * @return An Integer of 0.
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.vblueOf(0);
        }

        /**
         * Get the mbximum vblue of this object bs b Number.
         *
         * @return An Integer of 0.
         */
        public Number getMbximumAccessibleVblue() {
            return Integer.vblueOf(0);
        }

    } // clbss AccessibleAWTMenuItem

}
