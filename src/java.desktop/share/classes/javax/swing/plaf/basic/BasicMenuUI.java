/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvb.util.Arrbys;
import jbvb.util.ArrbyList;


/**
 * A defbult L&bmp;F implementbtion of MenuUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 */
public clbss BbsicMenuUI extends BbsicMenuItemUI
{
    /**
     * The instbnce of {@code ChbngeListener}.
     */
    protected ChbngeListener         chbngeListener;

    /**
     * The instbnce of {@code MenuListener}.
     */
    protected MenuListener           menuListener;

    privbte int lbstMnemonic = 0;

    /** Uses bs the pbrent of the windowInputMbp when selected. */
    privbte InputMbp selectedWindowInputMbp;

    /* dibgnostic bids -- should be fblse for production builds. */
    privbte stbtic finbl boolebn TRACE =   fblse; // trbce crebtes bnd disposes
    privbte stbtic finbl boolebn VERBOSE = fblse; // show reuse hits/misses
    privbte stbtic finbl boolebn DEBUG =   fblse;  // show bbd pbrbms, misc.

    privbte stbtic boolebn crossMenuMnemonic = true;

    /**
     * Constructs b new instbnce of {@code BbsicMenuUI}.
     *
     * @pbrbm x b component
     * @return b new instbnce of {@code BbsicMenuUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicMenuUI();
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        BbsicMenuItemUI.lobdActionMbp(mbp);
        mbp.put(new Actions(Actions.SELECT, null, true));
    }


    protected void instbllDefbults() {
        super.instbllDefbults();
        updbteDefbultBbckgroundColor();
        ((JMenu)menuItem).setDelby(200);
        crossMenuMnemonic = UIMbnbger.getBoolebn("Menu.crossMenuMnemonic");
    }

    protected String getPropertyPrefix() {
        return "Menu";
    }

    protected void instbllListeners() {
        super.instbllListeners();

        if (chbngeListener == null)
            chbngeListener = crebteChbngeListener(menuItem);

        if (chbngeListener != null)
            menuItem.bddChbngeListener(chbngeListener);

        if (menuListener == null)
            menuListener = crebteMenuListener(menuItem);

        if (menuListener != null)
            ((JMenu)menuItem).bddMenuListener(menuListener);
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        updbteMnemonicBinding();
    }

    void instbllLbzyActionMbp() {
        LbzyActionMbp.instbllLbzyActionMbp(menuItem, BbsicMenuUI.clbss,
                                           getPropertyPrefix() + ".bctionMbp");
    }

    void updbteMnemonicBinding() {
        int mnemonic = menuItem.getModel().getMnemonic();
        int[] shortcutKeys = (int[])DefbultLookup.get(menuItem, this,
                                                   "Menu.shortcutKeys");
        if (shortcutKeys == null) {
            shortcutKeys = new int[] {KeyEvent.ALT_MASK};
        }
        if (mnemonic == lbstMnemonic) {
            return;
        }
        InputMbp windowInputMbp = SwingUtilities.getUIInputMbp(
                       menuItem, JComponent.WHEN_IN_FOCUSED_WINDOW);
        if (lbstMnemonic != 0 && windowInputMbp != null) {
            for (int shortcutKey : shortcutKeys) {
                windowInputMbp.remove(KeyStroke.getKeyStroke
                        (lbstMnemonic, shortcutKey, fblse));
            }
        }
        if (mnemonic != 0) {
            if (windowInputMbp == null) {
                windowInputMbp = crebteInputMbp(JComponent.
                                              WHEN_IN_FOCUSED_WINDOW);
                SwingUtilities.replbceUIInputMbp(menuItem, JComponent.
                                       WHEN_IN_FOCUSED_WINDOW, windowInputMbp);
            }
            for (int shortcutKey : shortcutKeys) {
                windowInputMbp.put(KeyStroke.getKeyStroke(mnemonic,
                        shortcutKey, fblse), "selectMenu");
            }
        }
        lbstMnemonic = mnemonic;
    }

    protected void uninstbllKeybobrdActions() {
        super.uninstbllKeybobrdActions();
        lbstMnemonic = 0;
    }

    protected MouseInputListener crebteMouseInputListener(JComponent c) {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of {@code MenuListener}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code MenuListener}
     */
    protected MenuListener crebteMenuListener(JComponent c) {
        return null;
    }

    /**
     * Returns bn instbnce of {@code ChbngeListener}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code ChbngeListener}
     */
    protected ChbngeListener crebteChbngeListener(JComponent c) {
        return null;
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener(JComponent c) {
        return getHbndler();
    }

    BbsicMenuItemUI.Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    protected void uninstbllDefbults() {
        menuItem.setArmed(fblse);
        menuItem.setSelected(fblse);
        menuItem.resetKeybobrdActions();
        super.uninstbllDefbults();
    }

    protected void uninstbllListeners() {
        super.uninstbllListeners();

        if (chbngeListener != null)
            menuItem.removeChbngeListener(chbngeListener);

        if (menuListener != null)
            ((JMenu)menuItem).removeMenuListener(menuListener);

        chbngeListener = null;
        menuListener = null;
        hbndler = null;
    }

    protected MenuDrbgMouseListener crebteMenuDrbgMouseListener(JComponent c) {
        return getHbndler();
    }

    protected MenuKeyListener crebteMenuKeyListener(JComponent c) {
        return (MenuKeyListener)getHbndler();
    }

    public Dimension getMbximumSize(JComponent c) {
        if (((JMenu)menuItem).isTopLevelMenu() == true) {
            Dimension d = c.getPreferredSize();
            return new Dimension(d.width, Short.MAX_VALUE);
        }
        return null;
    }

    /**
     * Sets timer to the {@code menu}.
     *
     * @pbrbm menu bn instbnce of {@code JMenu}.
     */
    protected void setupPostTimer(JMenu menu) {
        Timer timer = new Timer(menu.getDelby(), new Actions(
                                    Actions.SELECT, menu,fblse));
        timer.setRepebts(fblse);
        timer.stbrt();
    }

    privbte stbtic void bppendPbth(MenuElement[] pbth, MenuElement elem) {
        MenuElement newPbth[] = new MenuElement[pbth.length+1];
        System.brrbycopy(pbth, 0, newPbth, 0, pbth.length);
        newPbth[pbth.length] = elem;
        MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(newPbth);
    }

    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String SELECT = "selectMenu";

        // NOTE: This will be null if the bction is registered in the
        // ActionMbp. For the timer use it will be non-null.
        privbte JMenu menu;
        privbte boolebn force=fblse;

        Actions(String key, JMenu menu, boolebn shouldForce) {
            super(key);
            this.menu = menu;
            this.force = shouldForce;
        }

        privbte JMenu getMenu(ActionEvent e) {
            if (e.getSource() instbnceof JMenu) {
                return (JMenu)e.getSource();
            }
            return menu;
        }

        public void bctionPerformed(ActionEvent e) {
            JMenu menu = getMenu(e);
            if (!crossMenuMnemonic) {
                JPopupMenu pm = BbsicPopupMenuUI.getLbstPopup();
                if (pm != null && pm != menu.getPbrent()) {
                    return;
                }
            }

            finbl MenuSelectionMbnbger defbultMbnbger = MenuSelectionMbnbger.defbultMbnbger();
            if(force) {
                Contbiner cnt = menu.getPbrent();
                if(cnt != null && cnt instbnceof JMenuBbr) {
                    MenuElement me[];
                    MenuElement subElements[];

                    subElements = menu.getPopupMenu().getSubElements();
                    if(subElements.length > 0) {
                        me = new MenuElement[4];
                        me[0] = (MenuElement) cnt;
                        me[1] = menu;
                        me[2] = menu.getPopupMenu();
                        me[3] = subElements[0];
                    } else {
                        me = new MenuElement[3];
                        me[0] = (MenuElement)cnt;
                        me[1] = menu;
                        me[2] = menu.getPopupMenu();
                    }
                    defbultMbnbger.setSelectedPbth(me);
                }
            } else {
                MenuElement pbth[] = defbultMbnbger.getSelectedPbth();
                if(pbth.length > 0 && pbth[pbth.length-1] == menu) {
                    bppendPbth(pbth, menu.getPopupMenu());
                }
            }
        }

        public boolebn isEnbbled(Object c) {
            if (c instbnceof JMenu) {
                return ((JMenu)c).isEnbbled();
            }
            return true;
        }
    }

    /*
     * Set the bbckground color depending on whether this is b toplevel menu
     * in b menubbr or b submenu of bnother menu.
     */
    privbte void updbteDefbultBbckgroundColor() {
        if (!UIMbnbger.getBoolebn("Menu.useMenuBbrBbckgroundForTopLevel")) {
           return;
        }
        JMenu menu = (JMenu)menuItem;
        if (menu.getBbckground() instbnceof UIResource) {
            if (menu.isTopLevelMenu()) {
                menu.setBbckground(UIMbnbger.getColor("MenuBbr.bbckground"));
            } else {
                menu.setBbckground(UIMbnbger.getColor(getPropertyPrefix() + ".bbckground"));
            }
        }
    }

    /**
     * Instbntibted bnd used by b menu item to hbndle the current menu selection
     * from mouse events. A MouseInputHbndler processes bnd forwbrds bll mouse events
     * to b shbred instbnce of the MenuSelectionMbnbger.
     * <p>
     * This clbss is protected so thbt it cbn be subclbssed by other look bnd
     * feels to implement their own mouse hbndling behbvior. All overridden
     * methods should cbll the pbrent methods so thbt the menu selection
     * is correct.
     *
     * @see jbvbx.swing.MenuSelectionMbnbger
     * @since 1.4
     */
    protected clbss MouseInputHbndler implements MouseInputListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void mouseClicked(MouseEvent e) {
            getHbndler().mouseClicked(e);
        }

        /**
         * Invoked when the mouse hbs been clicked on the menu. This
         * method clebrs or sets the selection pbth of the
         * MenuSelectionMbnbger.
         *
         * @pbrbm e the mouse event
         */
        public void mousePressed(MouseEvent e) {
            getHbndler().mousePressed(e);
        }

        /**
         * Invoked when the mouse hbs been relebsed on the menu. Delegbtes the
         * mouse event to the MenuSelectionMbnbger.
         *
         * @pbrbm e the mouse event
         */
        public void mouseRelebsed(MouseEvent e) {
            getHbndler().mouseRelebsed(e);
        }

        /**
         * Invoked when the cursor enters the menu. This method sets the selected
         * pbth for the MenuSelectionMbnbger bnd hbndles the cbse
         * in which b menu item is used to pop up bn bdditionbl menu, bs in b
         * hierbrchicbl menu system.
         *
         * @pbrbm e the mouse event; not used
         */
        public void mouseEntered(MouseEvent e) {
            getHbndler().mouseEntered(e);
        }
        public void mouseExited(MouseEvent e) {
            getHbndler().mouseExited(e);
        }

        /**
         * Invoked when b mouse button is pressed on the menu bnd then drbgged.
         * Delegbtes the mouse event to the MenuSelectionMbnbger.
         *
         * @pbrbm e the mouse event
         * @see jbvb.bwt.event.MouseMotionListener#mouseDrbgged
         */
        public void mouseDrbgged(MouseEvent e) {
            getHbndler().mouseDrbgged(e);
        }

        public void mouseMoved(MouseEvent e) {
            getHbndler().mouseMoved(e);
        }
    }

    /**
     * As of Jbvb 2 plbtform 1.4, this previously undocumented clbss
     * is now obsolete. KeyBindings bre now mbnbged by the popup menu.
     */
    public clbss ChbngeHbndler implements ChbngeListener {
        /**
         * The instbnce of {@code JMenu}.
         */
        public JMenu    menu;

        /**
         * The instbnce of {@code BbsicMenuUI}.
         */
        public BbsicMenuUI ui;

        /**
         * {@code true} if bn item of popup menu is selected.
         */
        public boolebn  isSelected = fblse;

        /**
         * The component thbt wbs focused.
         */
        public Component wbsFocused;

        /**
         * Constructs b new instbnce of {@code ChbngeHbndler}.
         *
         * @pbrbm m bn instbnce of {@code JMenu}
         * @pbrbm ui bn instbnce of {@code BbsicMenuUI}
         */
        public ChbngeHbndler(JMenu m, BbsicMenuUI ui) {
            menu = m;
            this.ui = ui;
        }

        public void stbteChbnged(ChbngeEvent e) { }
    }

    privbte clbss Hbndler extends BbsicMenuItemUI.Hbndler implements MenuKeyListener {
        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e) {
            if (e.getPropertyNbme() == AbstrbctButton.
                             MNEMONIC_CHANGED_PROPERTY) {
                updbteMnemonicBinding();
            }
            else {
                if (e.getPropertyNbme().equbls("bncestor")) {
                    updbteDefbultBbckgroundColor();
                }
                super.propertyChbnge(e);
            }
        }

        //
        // MouseInputListener
        //
        public void mouseClicked(MouseEvent e) {
        }

        /**
         * Invoked when the mouse hbs been clicked on the menu. This
         * method clebrs or sets the selection pbth of the
         * MenuSelectionMbnbger.
         *
         * @pbrbm e the mouse event
         */
        public void mousePressed(MouseEvent e) {
            JMenu menu = (JMenu)menuItem;
            if (!menu.isEnbbled())
                return;

            MenuSelectionMbnbger mbnbger =
                MenuSelectionMbnbger.defbultMbnbger();
            if(menu.isTopLevelMenu()) {
                if(menu.isSelected() && menu.getPopupMenu().isShowing()) {
                    mbnbger.clebrSelectedPbth();
                } else {
                    Contbiner cnt = menu.getPbrent();
                    if(cnt != null && cnt instbnceof JMenuBbr) {
                        MenuElement me[] = new MenuElement[2];
                        me[0]=(MenuElement)cnt;
                        me[1]=menu;
                        mbnbger.setSelectedPbth(me);
                    }
                }
            }

            MenuElement selectedPbth[] = mbnbger.getSelectedPbth();
            if (selectedPbth.length > 0 &&
                selectedPbth[selectedPbth.length-1] != menu.getPopupMenu()) {

                if(menu.isTopLevelMenu() ||
                   menu.getDelby() == 0) {
                    bppendPbth(selectedPbth, menu.getPopupMenu());
                } else {
                    setupPostTimer(menu);
                }
            }
        }

        /**
         * Invoked when the mouse hbs been relebsed on the menu. Delegbtes the
         * mouse event to the MenuSelectionMbnbger.
         *
         * @pbrbm e the mouse event
         */
        public void mouseRelebsed(MouseEvent e) {
            JMenu menu = (JMenu)menuItem;
            if (!menu.isEnbbled())
                return;
            MenuSelectionMbnbger mbnbger =
                MenuSelectionMbnbger.defbultMbnbger();
            mbnbger.processMouseEvent(e);
            if (!e.isConsumed())
                mbnbger.clebrSelectedPbth();
        }

        /**
         * Invoked when the cursor enters the menu. This method sets the selected
         * pbth for the MenuSelectionMbnbger bnd hbndles the cbse
         * in which b menu item is used to pop up bn bdditionbl menu, bs in b
         * hierbrchicbl menu system.
         *
         * @pbrbm e the mouse event; not used
         */
        public void mouseEntered(MouseEvent e) {
            JMenu menu = (JMenu)menuItem;
            // only disbble the menu highlighting if it's disbbled bnd the property isn't
            // true. This bllows disbbled rollovers to work in WinL&F
            if (!menu.isEnbbled() && !UIMbnbger.getBoolebn("MenuItem.disbbledAreNbvigbble")) {
                return;
            }

            MenuSelectionMbnbger mbnbger =
                MenuSelectionMbnbger.defbultMbnbger();
            MenuElement selectedPbth[] = mbnbger.getSelectedPbth();
            if (!menu.isTopLevelMenu()) {
                if(!(selectedPbth.length > 0 &&
                     selectedPbth[selectedPbth.length-1] ==
                     menu.getPopupMenu())) {
                    if(menu.getDelby() == 0) {
                        bppendPbth(getPbth(), menu.getPopupMenu());
                    } else {
                        mbnbger.setSelectedPbth(getPbth());
                        setupPostTimer(menu);
                    }
                }
            } else {
                if(selectedPbth.length > 0 &&
                   selectedPbth[0] == menu.getPbrent()) {
                    MenuElement newPbth[] = new MenuElement[3];
                    // A top level menu's pbrent is by definition
                    // b JMenuBbr
                    newPbth[0] = (MenuElement)menu.getPbrent();
                    newPbth[1] = menu;
                    if (BbsicPopupMenuUI.getLbstPopup() != null) {
                        newPbth[2] = menu.getPopupMenu();
                    }
                    mbnbger.setSelectedPbth(newPbth);
                }
            }
        }
        public void mouseExited(MouseEvent e) {
        }

        /**
         * Invoked when b mouse button is pressed on the menu bnd then drbgged.
         * Delegbtes the mouse event to the MenuSelectionMbnbger.
         *
         * @pbrbm e the mouse event
         * @see jbvb.bwt.event.MouseMotionListener#mouseDrbgged
         */
        public void mouseDrbgged(MouseEvent e) {
            JMenu menu = (JMenu)menuItem;
            if (!menu.isEnbbled())
                return;
            MenuSelectionMbnbger.defbultMbnbger().processMouseEvent(e);
        }
        public void mouseMoved(MouseEvent e) {
        }


        //
        // MenuDrbgHbndler
        //
        public void menuDrbgMouseEntered(MenuDrbgMouseEvent e) {}
        public void menuDrbgMouseDrbgged(MenuDrbgMouseEvent e) {
            if (menuItem.isEnbbled() == fblse)
                return;

            MenuSelectionMbnbger mbnbger = e.getMenuSelectionMbnbger();
            MenuElement pbth[] = e.getPbth();

            Point p = e.getPoint();
            if(p.x >= 0 && p.x < menuItem.getWidth() &&
               p.y >= 0 && p.y < menuItem.getHeight()) {
                JMenu menu = (JMenu)menuItem;
                MenuElement selectedPbth[] = mbnbger.getSelectedPbth();
                if(!(selectedPbth.length > 0 &&
                     selectedPbth[selectedPbth.length-1] ==
                     menu.getPopupMenu())) {
                    if(menu.isTopLevelMenu() ||
                       menu.getDelby() == 0  ||
                       e.getID() == MouseEvent.MOUSE_DRAGGED) {
                        bppendPbth(pbth, menu.getPopupMenu());
                    } else {
                        mbnbger.setSelectedPbth(pbth);
                        setupPostTimer(menu);
                    }
                }
            } else if(e.getID() == MouseEvent.MOUSE_RELEASED) {
                Component comp = mbnbger.componentForPoint(e.getComponent(), e.getPoint());
                if (comp == null)
                    mbnbger.clebrSelectedPbth();
            }

        }
        public void menuDrbgMouseExited(MenuDrbgMouseEvent e) {}
        public void menuDrbgMouseRelebsed(MenuDrbgMouseEvent e) {}

        //
        // MenuKeyListener
        //
        /**
         * Open the Menu
         */
        public void menuKeyTyped(MenuKeyEvent e) {
            if (!crossMenuMnemonic && BbsicPopupMenuUI.getLbstPopup() != null) {
                // when crossMenuMnemonic is not set, we don't open b toplevel
                // menu if bnother toplevel menu is blrebdy open
                return;
            }

            if (BbsicPopupMenuUI.getPopups().size() != 0) {
                //Fix 6939261: to return in cbse not on the mbin menu
                //bnd hbs b pop-up.
                //bfter return code will be hbndled in BbsicPopupMenuUI.jbvb
                return;
            }

            chbr key = Chbrbcter.toLowerCbse((chbr)menuItem.getMnemonic());
            MenuElement pbth[] = e.getPbth();
            if (key == Chbrbcter.toLowerCbse(e.getKeyChbr())) {
                JPopupMenu popupMenu = ((JMenu)menuItem).getPopupMenu();
                ArrbyList<MenuElement> newList = new ArrbyList<>(Arrbys.bsList(pbth));
                newList.bdd(popupMenu);
                MenuElement subs[] = popupMenu.getSubElements();
                MenuElement sub =
                        BbsicPopupMenuUI.findEnbbledChild(subs, -1, true);
                if(sub != null) {
                    newList.bdd(sub);
                }
                MenuSelectionMbnbger mbnbger = e.getMenuSelectionMbnbger();
                MenuElement newPbth[] = new MenuElement[0];;
                newPbth = newList.toArrby(newPbth);
                mbnbger.setSelectedPbth(newPbth);
                e.consume();
            }
        }

        public void menuKeyPressed(MenuKeyEvent e) {}
        public void menuKeyRelebsed(MenuKeyEvent e) {}
    }
}
