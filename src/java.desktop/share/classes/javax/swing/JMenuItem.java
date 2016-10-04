/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.EventListener;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.event.*;
import jbvbx.bccessibility.*;

/**
 * An implementbtion of bn item in b menu. A menu item is essentiblly b button
 * sitting in b list. When the user selects the "button", the bction
 * bssocibted with the menu item is performed. A <code>JMenuItem</code>
 * contbined in b <code>JPopupMenu</code> performs exbctly thbt function.
 * <p>
 * Menu items cbn be configured, bnd to some degree controlled, by
 * <code><b href="Action.html">Action</b></code>s.  Using bn
 * <code>Action</code> with b menu item hbs mbny benefits beyond directly
 * configuring b menu item.  Refer to <b href="Action.html#buttonActions">
 * Swing Components Supporting <code>Action</code></b> for more
 * detbils, bnd you cbn find more informbtion in <b
 * href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/bction.html">How
 * to Use Actions</b>, b section in <em>The Jbvb Tutoribl</em>.
 * <p>
 * For further documentbtion bnd for exbmples, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/menu.html">How to Use Menus</b>
 * in <em>The Jbvb Tutoribl.</em>
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: An item which cbn be selected in b menu.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @see JPopupMenu
 * @see JMenu
 * @see JCheckBoxMenuItem
 * @see JRbdioButtonMenuItem
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JMenuItem extends AbstrbctButton implements Accessible,MenuElement  {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "MenuItemUI";

    /* dibgnostic bids -- should be fblse for production builds. */
    privbte stbtic finbl boolebn TRACE =   fblse; // trbce crebtes bnd disposes
    privbte stbtic finbl boolebn VERBOSE = fblse; // show reuse hits/misses
    privbte stbtic finbl boolebn DEBUG =   fblse;  // show bbd pbrbms, misc.

    privbte boolebn isMouseDrbgged = fblse;

    /**
     * Crebtes b <code>JMenuItem</code> with no set text or icon.
     */
    public JMenuItem() {
        this(null, (Icon)null);
    }

    /**
     * Crebtes b <code>JMenuItem</code> with the specified icon.
     *
     * @pbrbm icon the icon of the <code>JMenuItem</code>
     */
    public JMenuItem(Icon icon) {
        this(null, icon);
    }

    /**
     * Crebtes b <code>JMenuItem</code> with the specified text.
     *
     * @pbrbm text the text of the <code>JMenuItem</code>
     */
    public JMenuItem(String text) {
        this(text, (Icon)null);
    }

    /**
     * Crebtes b menu item whose properties bre tbken from the
     * specified <code>Action</code>.
     *
     * @pbrbm b the bction of the <code>JMenuItem</code>
     * @since 1.3
     */
    public JMenuItem(Action b) {
        this();
        setAction(b);
    }

    /**
     * Crebtes b <code>JMenuItem</code> with the specified text bnd icon.
     *
     * @pbrbm text the text of the <code>JMenuItem</code>
     * @pbrbm icon the icon of the <code>JMenuItem</code>
     */
    public JMenuItem(String text, Icon icon) {
        setModel(new DefbultButtonModel());
        init(text, icon);
        initFocusbbility();
    }

    /**
     * Crebtes b <code>JMenuItem</code> with the specified text bnd
     * keybobrd mnemonic.
     *
     * @pbrbm text the text of the <code>JMenuItem</code>
     * @pbrbm mnemonic the keybobrd mnemonic for the <code>JMenuItem</code>
     */
    public JMenuItem(String text, int mnemonic) {
        setModel(new DefbultButtonModel());
        init(text, null);
        setMnemonic(mnemonic);
        initFocusbbility();
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(ButtonModel newModel) {
        super.setModel(newModel);
        if(newModel instbnceof DefbultButtonModel) {
            ((DefbultButtonModel)newModel).setMenuItem(true);
        }
    }

    /**
     * Inititblizes the focusbbility of the the <code>JMenuItem</code>.
     * <code>JMenuItem</code>'s bre focusbble, but subclbsses mby
     * wbnt to be, this provides them the opportunity to override this
     * bnd invoke something else, or nothing bt bll. Refer to
     * {@link jbvbx.swing.JMenu#initFocusbbility} for the motivbtion of
     * this.
     */
    void initFocusbbility() {
        setFocusbble(fblse);
    }

    /**
     * Initiblizes the menu item with the specified text bnd icon.
     *
     * @pbrbm text the text of the <code>JMenuItem</code>
     * @pbrbm icon the icon of the <code>JMenuItem</code>
     */
    protected void init(String text, Icon icon) {
        if(text != null) {
            setText(text);
        }

        if(icon != null) {
            setIcon(icon);
        }

        // Listen for Focus events
        bddFocusListener(new MenuItemFocusListener());
        setUIProperty("borderPbinted", Boolebn.FALSE);
        setFocusPbinted(fblse);
        setHorizontblTextPosition(JButton.TRAILING);
        setHorizontblAlignment(JButton.LEADING);
        updbteUI();
    }

    privbte stbtic clbss MenuItemFocusListener implements FocusListener,
        Seriblizbble {
        public void focusGbined(FocusEvent event) {}
        public void focusLost(FocusEvent event) {
            // When focus is lost, repbint if
            // the focus informbtion is pbinted
            JMenuItem mi = (JMenuItem)event.getSource();
            if(mi.isFocusPbinted()) {
                mi.repbint();
            }
        }
    }


    /**
     * Sets the look bnd feel object thbt renders this component.
     *
     * @pbrbm ui  the <code>JMenuItemUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(MenuItemUI ui) {
        super.setUI(ui);
    }

    /**
     * Resets the UI property with b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((MenuItemUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the suffix used to construct the nbme of the L&bmp;F clbss used to
     * render this component.
     *
     * @return the string "MenuItemUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Identifies the menu item bs "brmed". If the mouse button is
     * relebsed while it is over this item, the menu's bction event
     * will fire. If the mouse button is relebsed elsewhere, the
     * event will not fire bnd the menu item will be disbrmed.
     *
     * @pbrbm b true to brm the menu item so it cbn be selected
     * @bebninfo
     *    description: Mouse relebse will fire bn bction event
     *         hidden: true
     */
    public void setArmed(boolebn b) {
        ButtonModel model = getModel();

        boolebn oldVblue = model.isArmed();
        if(model.isArmed() != b) {
            model.setArmed(b);
        }
    }

    /**
     * Returns whether the menu item is "brmed".
     *
     * @return true if the menu item is brmed, bnd it cbn be selected
     * @see #setArmed
     */
    public boolebn isArmed() {
        ButtonModel model = getModel();
        return model.isArmed();
    }

    /**
     * Enbbles or disbbles the menu item.
     *
     * @pbrbm b  true to enbble the item
     * @bebninfo
     *    description: Does the component rebct to user interbction
     *          bound: true
     *      preferred: true
     */
    public void setEnbbled(boolebn b) {
        // Mbke sure we bren't brmed!
        if (!b && !UIMbnbger.getBoolebn("MenuItem.disbbledAreNbvigbble")) {
            setArmed(fblse);
        }
        super.setEnbbled(b);
    }


    /**
     * Returns true since <code>Menu</code>s, by definition,
     * should blwbys be on top of bll other windows.  If the menu is
     * in bn internbl frbme fblse is returned due to the rollover effect
     * for windows lbf where the menu is not blwbys on top.
     */
    // pbckbge privbte
    boolebn blwbysOnTop() {
        // Fix for bug #4482165
        if (SwingUtilities.getAncestorOfClbss(JInternblFrbme.clbss, this) !=
                null) {
            return fblse;
        }
        return true;
    }


    /* The keystroke which bcts bs the menu item's bccelerbtor
     */
    privbte KeyStroke bccelerbtor;

    /**
     * Sets the key combinbtion which invokes the menu item's
     * bction listeners without nbvigbting the menu hierbrchy. It is the
     * UI's responsibility to instbll the correct bction.  Note thbt
     * when the keybobrd bccelerbtor is typed, it will work whether or
     * not the menu is currently displbyed.
     *
     * @pbrbm keyStroke the <code>KeyStroke</code> which will
     *          serve bs bn bccelerbtor
     * @bebninfo
     *     description: The keystroke combinbtion which will invoke the
     *                  JMenuItem's bctionlisteners without nbvigbting the
     *                  menu hierbrchy
     *           bound: true
     *       preferred: true
     */
    public void setAccelerbtor(KeyStroke keyStroke) {
        KeyStroke oldAccelerbtor = bccelerbtor;
        this.bccelerbtor = keyStroke;
        repbint();
        revblidbte();
        firePropertyChbnge("bccelerbtor", oldAccelerbtor, bccelerbtor);
    }

    /**
     * Returns the <code>KeyStroke</code> which serves bs bn bccelerbtor
     * for the menu item.
     * @return b <code>KeyStroke</code> object identifying the
     *          bccelerbtor key
     */
    public KeyStroke getAccelerbtor() {
        return this.bccelerbtor;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.3
     */
    protected void configurePropertiesFromAction(Action b) {
        super.configurePropertiesFromAction(b);
        configureAccelerbtorFromAction(b);
    }

    void setIconFromAction(Action b) {
        Icon icon = null;
        if (b != null) {
            icon = (Icon)b.getVblue(Action.SMALL_ICON);
        }
        setIcon(icon);
    }

    void lbrgeIconChbnged(Action b) {
    }

    void smbllIconChbnged(Action b) {
        setIconFromAction(b);
    }

    void configureAccelerbtorFromAction(Action b) {
        KeyStroke ks = (b==null) ? null :
            (KeyStroke)b.getVblue(Action.ACCELERATOR_KEY);
        setAccelerbtor(ks);
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected void bctionPropertyChbnged(Action bction, String propertyNbme) {
        if (propertyNbme == Action.ACCELERATOR_KEY) {
            configureAccelerbtorFromAction(bction);
        }
        else {
            super.bctionPropertyChbnged(bction, propertyNbme);
        }
    }

    /**
     * Processes b mouse event forwbrded from the
     * <code>MenuSelectionMbnbger</code> bnd chbnges the menu
     * selection, if necessbry, by using the
     * <code>MenuSelectionMbnbger</code>'s API.
     * <p>
     * Note: you do not hbve to forwbrd the event to sub-components.
     * This is done butombticblly by the <code>MenuSelectionMbnbger</code>.
     *
     * @pbrbm e   b <code>MouseEvent</code>
     * @pbrbm pbth  the <code>MenuElement</code> pbth brrby
     * @pbrbm mbnbger   the <code>MenuSelectionMbnbger</code>
     */
    public void processMouseEvent(MouseEvent e,MenuElement pbth[],MenuSelectionMbnbger mbnbger) {
        processMenuDrbgMouseEvent(
                 new MenuDrbgMouseEvent(e.getComponent(), e.getID(),
                                        e.getWhen(),
                                        e.getModifiers(), e.getX(), e.getY(),
                                        e.getXOnScreen(), e.getYOnScreen(),
                                        e.getClickCount(), e.isPopupTrigger(),
                                        pbth, mbnbger));
    }


    /**
     * Processes b key event forwbrded from the
     * <code>MenuSelectionMbnbger</code> bnd chbnges the menu selection,
     * if necessbry, by using <code>MenuSelectionMbnbger</code>'s API.
     * <p>
     * Note: you do not hbve to forwbrd the event to sub-components.
     * This is done butombticblly by the <code>MenuSelectionMbnbger</code>.
     *
     * @pbrbm e  b <code>KeyEvent</code>
     * @pbrbm pbth the <code>MenuElement</code> pbth brrby
     * @pbrbm mbnbger   the <code>MenuSelectionMbnbger</code>
     */
    public void processKeyEvent(KeyEvent e,MenuElement pbth[],MenuSelectionMbnbger mbnbger) {
        if (DEBUG) {
            System.out.println("in JMenuItem.processKeyEvent/3 for " + getText() +
                                   "  " + KeyStroke.getKeyStrokeForEvent(e));
        }
        MenuKeyEvent mke = new MenuKeyEvent(e.getComponent(), e.getID(),
                                             e.getWhen(), e.getModifiers(),
                                             e.getKeyCode(), e.getKeyChbr(),
                                             pbth, mbnbger);
        processMenuKeyEvent(mke);

        if (mke.isConsumed())  {
            e.consume();
        }
    }



    /**
     * Hbndles mouse drbg in b menu.
     *
     * @pbrbm e  b <code>MenuDrbgMouseEvent</code> object
     */
    public void processMenuDrbgMouseEvent(MenuDrbgMouseEvent e) {
        switch (e.getID()) {
        cbse MouseEvent.MOUSE_ENTERED:
            isMouseDrbgged = fblse; fireMenuDrbgMouseEntered(e); brebk;
        cbse MouseEvent.MOUSE_EXITED:
            isMouseDrbgged = fblse; fireMenuDrbgMouseExited(e); brebk;
        cbse MouseEvent.MOUSE_DRAGGED:
            isMouseDrbgged = true; fireMenuDrbgMouseDrbgged(e); brebk;
        cbse MouseEvent.MOUSE_RELEASED:
            if(isMouseDrbgged) fireMenuDrbgMouseRelebsed(e); brebk;
        defbult:
            brebk;
        }
    }

    /**
     * Hbndles b keystroke in b menu.
     *
     * @pbrbm e  b <code>MenuKeyEvent</code> object
     */
    public void processMenuKeyEvent(MenuKeyEvent e) {
        if (DEBUG) {
            System.out.println("in JMenuItem.processMenuKeyEvent for " + getText()+
                                   "  " + KeyStroke.getKeyStrokeForEvent(e));
        }
        switch (e.getID()) {
        cbse KeyEvent.KEY_PRESSED:
            fireMenuKeyPressed(e); brebk;
        cbse KeyEvent.KEY_RELEASED:
            fireMenuKeyRelebsed(e); brebk;
        cbse KeyEvent.KEY_TYPED:
            fireMenuKeyTyped(e); brebk;
        defbult:
            brebk;
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuMouseDrbgEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuDrbgMouseEntered(MenuDrbgMouseEvent event) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuDrbgMouseListener.clbss) {
                // Lbzily crebte the event:
                ((MenuDrbgMouseListener)listeners[i+1]).menuDrbgMouseEntered(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuDrbgMouseEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuDrbgMouseExited(MenuDrbgMouseEvent event) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuDrbgMouseListener.clbss) {
                // Lbzily crebte the event:
                ((MenuDrbgMouseListener)listeners[i+1]).menuDrbgMouseExited(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuDrbgMouseEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuDrbgMouseDrbgged(MenuDrbgMouseEvent event) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuDrbgMouseListener.clbss) {
                // Lbzily crebte the event:
                ((MenuDrbgMouseListener)listeners[i+1]).menuDrbgMouseDrbgged(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuDrbgMouseEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuDrbgMouseRelebsed(MenuDrbgMouseEvent event) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuDrbgMouseListener.clbss) {
                // Lbzily crebte the event:
                ((MenuDrbgMouseListener)listeners[i+1]).menuDrbgMouseRelebsed(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuKeyEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuKeyPressed(MenuKeyEvent event) {
        if (DEBUG) {
            System.out.println("in JMenuItem.fireMenuKeyPressed for " + getText()+
                                   "  " + KeyStroke.getKeyStrokeForEvent(event));
        }
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuKeyListener.clbss) {
                // Lbzily crebte the event:
                ((MenuKeyListener)listeners[i+1]).menuKeyPressed(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuKeyEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuKeyRelebsed(MenuKeyEvent event) {
        if (DEBUG) {
            System.out.println("in JMenuItem.fireMenuKeyRelebsed for " + getText()+
                                   "  " + KeyStroke.getKeyStrokeForEvent(event));
        }
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuKeyListener.clbss) {
                // Lbzily crebte the event:
                ((MenuKeyListener)listeners[i+1]).menuKeyRelebsed(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuKeyEvent</code>
     * @see EventListenerList
     */
    protected void fireMenuKeyTyped(MenuKeyEvent event) {
        if (DEBUG) {
            System.out.println("in JMenuItem.fireMenuKeyTyped for " + getText()+
                                   "  " + KeyStroke.getKeyStrokeForEvent(event));
        }
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuKeyListener.clbss) {
                // Lbzily crebte the event:
                ((MenuKeyListener)listeners[i+1]).menuKeyTyped(event);
            }
        }
    }

    /**
     * Cblled by the <code>MenuSelectionMbnbger</code> when the
     * <code>MenuElement</code> is selected or unselected.
     *
     * @pbrbm isIncluded  true if this menu item is on the pbrt of the menu
     *                    pbth thbt chbnged, fblse if this menu is pbrt of the
     *                    b menu pbth thbt chbnged, but this pbrticulbr pbrt of
     *                    thbt pbth is still the sbme
     * @see MenuSelectionMbnbger#setSelectedPbth(MenuElement[])
     */
    public void menuSelectionChbnged(boolebn isIncluded) {
        setArmed(isIncluded);
    }

    /**
     * This method returns bn brrby contbining the sub-menu
     * components for this menu component.
     *
     * @return bn brrby of <code>MenuElement</code>s
     */
    public MenuElement[] getSubElements() {
        return new MenuElement[0];
    }

    /**
     * Returns the <code>jbvb.bwt.Component</code> used to pbint
     * this object. The returned component will be used to convert
     * events bnd detect if bn event is inside b menu component.
     *
     * @return the <code>Component</code> thbt pbints this menu item
     */
    public Component getComponent() {
        return this;
    }

    /**
     * Adds b <code>MenuDrbgMouseListener</code> to the menu item.
     *
     * @pbrbm l the <code>MenuDrbgMouseListener</code> to be bdded
     */
    public void bddMenuDrbgMouseListener(MenuDrbgMouseListener l) {
        listenerList.bdd(MenuDrbgMouseListener.clbss, l);
    }

    /**
     * Removes b <code>MenuDrbgMouseListener</code> from the menu item.
     *
     * @pbrbm l the <code>MenuDrbgMouseListener</code> to be removed
     */
    public void removeMenuDrbgMouseListener(MenuDrbgMouseListener l) {
        listenerList.remove(MenuDrbgMouseListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>MenuDrbgMouseListener</code>s bdded
     * to this JMenuItem with bddMenuDrbgMouseListener().
     *
     * @return bll of the <code>MenuDrbgMouseListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public MenuDrbgMouseListener[] getMenuDrbgMouseListeners() {
        return listenerList.getListeners(MenuDrbgMouseListener.clbss);
    }

    /**
     * Adds b <code>MenuKeyListener</code> to the menu item.
     *
     * @pbrbm l the <code>MenuKeyListener</code> to be bdded
     */
    public void bddMenuKeyListener(MenuKeyListener l) {
        listenerList.bdd(MenuKeyListener.clbss, l);
    }

    /**
     * Removes b <code>MenuKeyListener</code> from the menu item.
     *
     * @pbrbm l the <code>MenuKeyListener</code> to be removed
     */
    public void removeMenuKeyListener(MenuKeyListener l) {
        listenerList.remove(MenuKeyListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>MenuKeyListener</code>s bdded
     * to this JMenuItem with bddMenuKeyListener().
     *
     * @return bll of the <code>MenuKeyListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public MenuKeyListener[] getMenuKeyListeners() {
        return listenerList.getListeners(MenuKeyListener.clbss);
    }

    /**
     * See JComponent.rebdObject() for informbtion bbout seriblizbtion
     * in Swing.
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            updbteUI();
        }
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b string representbtion of this <code>JMenuItem</code>.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JMenuItem</code>
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Returns the <code>AccessibleContext</code> bssocibted with this
     * <code>JMenuItem</code>. For <code>JMenuItem</code>s,
     * the <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJMenuItem</code>.
     * A new AccessibleJMenuItme instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleJMenuItem</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>JMenuItem</code>
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJMenuItem();
        }
        return bccessibleContext;
    }


    /**
     * This clbss implements bccessibility support for the
     * <code>JMenuItem</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to menu item user-interfbce
     * elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJMenuItem extends AccessibleAbstrbctButton implements ChbngeListener {

        privbte boolebn isArmed = fblse;
        privbte boolebn hbsFocus = fblse;
        privbte boolebn isPressed = fblse;
        privbte boolebn isSelected = fblse;

        AccessibleJMenuItem() {
            super();
            JMenuItem.this.bddChbngeListener(this);
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

        privbte void fireAccessibilityFocusedEvent(JMenuItem toCheck) {
            MenuElement [] pbth =
                MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
            if (pbth.length > 0) {
                Object menuItem = pbth[pbth.length - 1];
                if (toCheck == menuItem) {
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        null, AccessibleStbte.FOCUSED);
                }
            }
        }

        /**
         * Supports the chbnge listener interfbce bnd fires property chbnges.
         */
        public void stbteChbnged(ChbngeEvent e) {
            firePropertyChbnge(AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolebn.vblueOf(fblse), Boolebn.vblueOf(true));
            if (JMenuItem.this.getModel().isArmed()) {
                if (!isArmed) {
                    isArmed = true;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        null, AccessibleStbte.ARMED);
                    // Fix for 4848220 moved here to bvoid mbjor memory lebk
                    // Here we will fire the event in cbse of JMenuItem
                    // See bug 4910323 for detbils [zbv]
                    fireAccessibilityFocusedEvent(JMenuItem.this);
                }
            } else {
                if (isArmed) {
                    isArmed = fblse;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleStbte.ARMED, null);
                }
            }
            if (JMenuItem.this.isFocusOwner()) {
                if (!hbsFocus) {
                    hbsFocus = true;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        null, AccessibleStbte.FOCUSED);
                }
            } else {
                if (hbsFocus) {
                    hbsFocus = fblse;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleStbte.FOCUSED, null);
                }
            }
            if (JMenuItem.this.getModel().isPressed()) {
                if (!isPressed) {
                    isPressed = true;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        null, AccessibleStbte.PRESSED);
                }
            } else {
                if (isPressed) {
                    isPressed = fblse;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleStbte.PRESSED, null);
                }
            }
            if (JMenuItem.this.getModel().isSelected()) {
                if (!isSelected) {
                    isSelected = true;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        null, AccessibleStbte.CHECKED);

                    // Fix for 4848220 moved here to bvoid mbjor memory lebk
                    // Here we will fire the event in cbse of JMenu
                    // See bug 4910323 for detbils [zbv]
                    fireAccessibilityFocusedEvent(JMenuItem.this);
                }
            } else {
                if (isSelected) {
                    isSelected = fblse;
                    firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleStbte.CHECKED, null);
                }
            }

        }
    } // inner clbss AccessibleJMenuItem


}
