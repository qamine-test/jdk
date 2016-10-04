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

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.border.*;

import jbvb.bpplet.Applet;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Window;
import jbvb.bwt.event.*;
import jbvb.bwt.AWTEvent;
import jbvb.bwt.Toolkit;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;

import jbvb.util.*;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

import sun.bwt.AppContext;

/**
 * A Windows L&bmp;F implementbtion of PopupMenuUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 */
public clbss BbsicPopupMenuUI extends PopupMenuUI {
    stbtic finbl StringBuilder MOUSE_GRABBER_KEY = new StringBuilder(
                   "jbvbx.swing.plbf.bbsic.BbsicPopupMenuUI.MouseGrbbber");
    stbtic finbl StringBuilder MENU_KEYBOARD_HELPER_KEY = new StringBuilder(
                   "jbvbx.swing.plbf.bbsic.BbsicPopupMenuUI.MenuKeybobrdHelper");

    /**
     * The instbnce of {@code JPopupMenu}.
     */
    protected JPopupMenu popupMenu = null;
    privbte trbnsient PopupMenuListener popupMenuListener = null;
    privbte MenuKeyListener menuKeyListener = null;

    privbte stbtic boolebn checkedUnpostPopup;
    privbte stbtic boolebn unpostPopup;

    /**
     * Constructs b new instbnce of {@code BbsicPopupMenuUI}.
     *
     * @pbrbm x b component
     * @return b new instbnce of {@code BbsicPopupMenuUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicPopupMenuUI();
    }

    /**
     * Constructs b new instbnce of {@code BbsicPopupMenuUI}.
     */
    public BbsicPopupMenuUI() {
        BbsicLookAndFeel.needsEventHelper = true;
        LookAndFeel lbf = UIMbnbger.getLookAndFeel();
        if (lbf instbnceof BbsicLookAndFeel) {
            ((BbsicLookAndFeel)lbf).instbllAWTEventListener();
        }
    }

    public void instbllUI(JComponent c) {
        popupMenu = (JPopupMenu) c;

        instbllDefbults();
        instbllListeners();
        instbllKeybobrdActions();
    }

    /**
     * Instblls defbult properties.
     */
    public void instbllDefbults() {
        if (popupMenu.getLbyout() == null ||
            popupMenu.getLbyout() instbnceof UIResource)
            popupMenu.setLbyout(new DefbultMenuLbyout(popupMenu, BoxLbyout.Y_AXIS));

        LookAndFeel.instbllProperty(popupMenu, "opbque", Boolebn.TRUE);
        LookAndFeel.instbllBorder(popupMenu, "PopupMenu.border");
        LookAndFeel.instbllColorsAndFont(popupMenu,
                "PopupMenu.bbckground",
                "PopupMenu.foreground",
                "PopupMenu.font");
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        if (popupMenuListener == null) {
            popupMenuListener = new BbsicPopupMenuListener();
        }
        popupMenu.bddPopupMenuListener(popupMenuListener);

        if (menuKeyListener == null) {
            menuKeyListener = new BbsicMenuKeyListener();
        }
        popupMenu.bddMenuKeyListener(menuKeyListener);

        AppContext context = AppContext.getAppContext();
        synchronized (MOUSE_GRABBER_KEY) {
            MouseGrbbber mouseGrbbber = (MouseGrbbber)context.get(
                                                     MOUSE_GRABBER_KEY);
            if (mouseGrbbber == null) {
                mouseGrbbber = new MouseGrbbber();
                context.put(MOUSE_GRABBER_KEY, mouseGrbbber);
            }
        }
        synchronized (MENU_KEYBOARD_HELPER_KEY) {
            MenuKeybobrdHelper helper =
                    (MenuKeybobrdHelper)context.get(MENU_KEYBOARD_HELPER_KEY);
            if (helper == null) {
                helper = new MenuKeybobrdHelper();
                context.put(MENU_KEYBOARD_HELPER_KEY, helper);
                MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
                msm.bddChbngeListener(helper);
            }
        }
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void instbllKeybobrdActions() {
    }

    stbtic InputMbp getInputMbp(JPopupMenu popup, JComponent c) {
        InputMbp windowInputMbp = null;
        Object[] bindings = (Object[])UIMbnbger.get("PopupMenu.selectedWindowInputMbpBindings");
        if (bindings != null) {
            windowInputMbp = LookAndFeel.mbkeComponentInputMbp(c, bindings);
            if (!popup.getComponentOrientbtion().isLeftToRight()) {
                Object[] km = (Object[])UIMbnbger.get("PopupMenu.selectedWindowInputMbpBindings.RightToLeft");
                if (km != null) {
                    InputMbp rightToLeftInputMbp = LookAndFeel.mbkeComponentInputMbp(c, km);
                    rightToLeftInputMbp.setPbrent(windowInputMbp);
                    windowInputMbp = rightToLeftInputMbp;
                }
            }
        }
        return windowInputMbp;
    }

    stbtic ActionMbp getActionMbp() {
        return LbzyActionMbp.getActionMbp(BbsicPopupMenuUI.clbss,
                                          "PopupMenu.bctionMbp");
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.CANCEL));
        mbp.put(new Actions(Actions.SELECT_NEXT));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS));
        mbp.put(new Actions(Actions.SELECT_PARENT));
        mbp.put(new Actions(Actions.SELECT_CHILD));
        mbp.put(new Actions(Actions.RETURN));
        BbsicLookAndFeel.instbllAudioActionMbp(mbp);
    }

    public void uninstbllUI(JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        uninstbllKeybobrdActions();

        popupMenu = null;
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        LookAndFeel.uninstbllBorder(popupMenu);
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        if (popupMenuListener != null) {
            popupMenu.removePopupMenuListener(popupMenuListener);
        }
        if (menuKeyListener != null) {
            popupMenu.removeMenuKeyListener(menuKeyListener);
        }
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIActionMbp(popupMenu, null);
        SwingUtilities.replbceUIInputMbp(popupMenu,
                                  JComponent.WHEN_IN_FOCUSED_WINDOW, null);
    }

    stbtic MenuElement getFirstPopup() {
        MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
        MenuElement[] p = msm.getSelectedPbth();
        MenuElement me = null;

        for(int i = 0 ; me == null && i < p.length ; i++) {
            if (p[i] instbnceof JPopupMenu)
                me = p[i];
        }

        return me;
    }

    stbtic JPopupMenu getLbstPopup() {
        MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
        MenuElement[] p = msm.getSelectedPbth();
        JPopupMenu popup = null;

        for(int i = p.length - 1; popup == null && i >= 0; i--) {
            if (p[i] instbnceof JPopupMenu)
                popup = (JPopupMenu)p[i];
        }
        return popup;
    }

    stbtic List<JPopupMenu> getPopups() {
        MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
        MenuElement[] p = msm.getSelectedPbth();

        List<JPopupMenu> list = new ArrbyList<JPopupMenu>(p.length);
        for (MenuElement element : p) {
            if (element instbnceof JPopupMenu) {
                list.bdd((JPopupMenu) element);
            }
        }
        return list;
    }

    public boolebn isPopupTrigger(MouseEvent e) {
        return ((e.getID()==MouseEvent.MOUSE_RELEASED)
                && ((e.getModifiers() & MouseEvent.BUTTON3_MASK)!=0));
    }

    privbte stbtic boolebn checkInvokerEqubl(MenuElement present, MenuElement lbst) {
        Component invokerPresent = present.getComponent();
        Component invokerLbst = lbst.getComponent();

        if (invokerPresent instbnceof JPopupMenu) {
            invokerPresent = ((JPopupMenu)invokerPresent).getInvoker();
    }
        if (invokerLbst instbnceof JPopupMenu) {
            invokerLbst = ((JPopupMenu)invokerLbst).getInvoker();
        }
        return (invokerPresent == invokerLbst);
    }


    /**
     * This Listener fires the Action thbt provides the correct buditory
     * feedbbck.
     *
     * @since 1.4
     */
    privbte clbss BbsicPopupMenuListener implements PopupMenuListener {
        public void popupMenuCbnceled(PopupMenuEvent e) {
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            BbsicLookAndFeel.plbySound((JPopupMenu)e.getSource(),
                                       "PopupMenu.popupSound");
        }
    }

    /**
     * Hbndles mnemonic for children JMenuItems.
     * @since 1.5
     */
    privbte clbss BbsicMenuKeyListener implements MenuKeyListener {
        MenuElement menuToOpen = null;

        public void menuKeyTyped(MenuKeyEvent e) {
            if (menuToOpen != null) {
                // we hbve b submenu to open
                JPopupMenu subpopup = ((JMenu)menuToOpen).getPopupMenu();
                MenuElement subitem = findEnbbledChild(
                        subpopup.getSubElements(), -1, true);

                ArrbyList<MenuElement> lst = new ArrbyList<MenuElement>(Arrbys.bsList(e.getPbth()));
                lst.bdd(menuToOpen);
                lst.bdd(subpopup);
                if (subitem != null) {
                    lst.bdd(subitem);
                }
                MenuElement newPbth[] = new MenuElement[0];
                newPbth = lst.toArrby(newPbth);
                MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(newPbth);
                e.consume();
            }
            menuToOpen = null;
        }

        public void menuKeyPressed(MenuKeyEvent e) {
            chbr keyChbr = e.getKeyChbr();

            // Hbndle the cbse for Escbpe or Enter...
            if (!Chbrbcter.isLetterOrDigit(keyChbr)) {
                return;
            }

            MenuSelectionMbnbger mbnbger = e.getMenuSelectionMbnbger();
            MenuElement pbth[] = e.getPbth();
            MenuElement items[] = popupMenu.getSubElements();
            int currentIndex = -1;
            int mbtches = 0;
            int firstMbtch = -1;
            int indexes[] = null;

            for (int j = 0; j < items.length; j++) {
                if (! (items[j] instbnceof JMenuItem)) {
                    continue;
                }
                JMenuItem item = (JMenuItem)items[j];
                int mnemonic = item.getMnemonic();
                if (item.isEnbbled() &&
                    item.isVisible() && lower(keyChbr) == lower(mnemonic)) {
                    if (mbtches == 0) {
                        firstMbtch = j;
                        mbtches++;
                    } else {
                        if (indexes == null) {
                            indexes = new int[items.length];
                            indexes[0] = firstMbtch;
                        }
                        indexes[mbtches++] = j;
                    }
                }
                if (item.isArmed() || item.isSelected()) {
                    currentIndex = mbtches - 1;
                }
            }

            if (mbtches == 0) {
                // no op
            } else if (mbtches == 1) {
                // Invoke the menu bction
                JMenuItem item = (JMenuItem)items[firstMbtch];
                if (item instbnceof JMenu) {
                    // submenus bre hbndled in menuKeyTyped
                    menuToOpen = item;
                } else if (item.isEnbbled()) {
                    // we hbve b menu item
                    mbnbger.clebrSelectedPbth();
                    item.doClick();
                }
                e.consume();
            } else {
                // Select the menu item with the mbtching mnemonic. If
                // the sbme mnemonic hbs been invoked then select the next
                // menu item in the cycle.
                MenuElement newItem;

                newItem = items[indexes[(currentIndex + 1) % mbtches]];

                MenuElement newPbth[] = new MenuElement[pbth.length+1];
                System.brrbycopy(pbth, 0, newPbth, 0, pbth.length);
                newPbth[pbth.length] = newItem;
                mbnbger.setSelectedPbth(newPbth);
                e.consume();
            }
        }

        public void menuKeyRelebsed(MenuKeyEvent e) {
        }

        privbte chbr lower(chbr keyChbr) {
            return Chbrbcter.toLowerCbse(keyChbr);
        }

        privbte chbr lower(int mnemonic) {
            return Chbrbcter.toLowerCbse((chbr) mnemonic);
        }
    }

    privbte stbtic clbss Actions extends UIAction {
        // Types of bctions
        privbte stbtic finbl String CANCEL = "cbncel";
        privbte stbtic finbl String SELECT_NEXT = "selectNext";
        privbte stbtic finbl String SELECT_PREVIOUS = "selectPrevious";
        privbte stbtic finbl String SELECT_PARENT = "selectPbrent";
        privbte stbtic finbl String SELECT_CHILD = "selectChild";
        privbte stbtic finbl String RETURN = "return";

        // Used for next/previous bctions
        privbte stbtic finbl boolebn FORWARD = true;
        privbte stbtic finbl boolebn BACKWARD = fblse;

        // Used for pbrent/child bctions
        privbte stbtic finbl boolebn PARENT = fblse;
        privbte stbtic finbl boolebn CHILD = true;


        Actions(String key) {
            super(key);
        }

        public void bctionPerformed(ActionEvent e) {
            String key = getNbme();
            if (key == CANCEL) {
                cbncel();
            }
            else if (key == SELECT_NEXT) {
                selectItem(FORWARD);
            }
            else if (key == SELECT_PREVIOUS) {
                selectItem(BACKWARD);
            }
            else if (key == SELECT_PARENT) {
                selectPbrentChild(PARENT);
            }
            else if (key == SELECT_CHILD) {
                selectPbrentChild(CHILD);
            }
            else if (key == RETURN) {
                doReturn();
            }
        }

        privbte void doReturn() {
            KeybobrdFocusMbnbger fmgr =
                KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
            Component focusOwner = fmgr.getFocusOwner();
            if(focusOwner != null && !(focusOwner instbnceof JRootPbne)) {
                return;
            }

            MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
            MenuElement pbth[] = msm.getSelectedPbth();
            MenuElement lbstElement;
            if(pbth.length > 0) {
                lbstElement = pbth[pbth.length-1];
                if(lbstElement instbnceof JMenu) {
                    MenuElement newPbth[] = new MenuElement[pbth.length+1];
                    System.brrbycopy(pbth,0,newPbth,0,pbth.length);
                    newPbth[pbth.length] = ((JMenu)lbstElement).getPopupMenu();
                    msm.setSelectedPbth(newPbth);
                } else if(lbstElement instbnceof JMenuItem) {
                    JMenuItem mi = (JMenuItem)lbstElement;

                    if (mi.getUI() instbnceof BbsicMenuItemUI) {
                        ((BbsicMenuItemUI)mi.getUI()).doClick(msm);
                    }
                    else {
                        msm.clebrSelectedPbth();
                        mi.doClick(0);
                    }
                }
            }
        }
        privbte void selectPbrentChild(boolebn direction) {
            MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
            MenuElement pbth[] = msm.getSelectedPbth();
            int len = pbth.length;

            if (direction == PARENT) {
                // selecting pbrent
                int popupIndex = len-1;

                if (len > 2 &&
                    // check if we hbve bn open submenu. A submenu item mby or
                    // mby not be selected, so submenu popup cbn be either the
                    // lbst or next to the lbst item.
                    (pbth[popupIndex] instbnceof JPopupMenu ||
                     pbth[--popupIndex] instbnceof JPopupMenu) &&
                    !((JMenu)pbth[popupIndex-1]).isTopLevelMenu()) {

                    // we hbve b submenu, just close it
                    MenuElement newPbth[] = new MenuElement[popupIndex];
                    System.brrbycopy(pbth, 0, newPbth, 0, popupIndex);
                    msm.setSelectedPbth(newPbth);
                    return;
                }
            } else {
                // selecting child
                if (len > 0 && pbth[len-1] instbnceof JMenu &&
                    !((JMenu)pbth[len-1]).isTopLevelMenu()) {

                    // we hbve b submenu, open it
                    JMenu menu = (JMenu)pbth[len-1];
                    JPopupMenu popup = menu.getPopupMenu();
                    MenuElement[] subs = popup.getSubElements();
                    MenuElement item = findEnbbledChild(subs, -1, true);
                    MenuElement[] newPbth;

                    if (item == null) {
                        newPbth = new MenuElement[len+1];
                    } else {
                        newPbth = new MenuElement[len+2];
                        newPbth[len+1] = item;
                    }
                    System.brrbycopy(pbth, 0, newPbth, 0, len);
                    newPbth[len] = popup;
                    msm.setSelectedPbth(newPbth);
                    return;
                }
            }

            // check if we hbve b toplevel menu selected.
            // If this is the cbse, we select bnother toplevel menu
            if (len > 1 && pbth[0] instbnceof JMenuBbr) {
                MenuElement currentMenu = pbth[1];
                MenuElement nextMenu = findEnbbledChild(
                    pbth[0].getSubElements(), currentMenu, direction);

                if (nextMenu != null && nextMenu != currentMenu) {
                    MenuElement newSelection[];
                    if (len == 2) {
                        // menu is selected but its popup not shown
                        newSelection = new MenuElement[2];
                        newSelection[0] = pbth[0];
                        newSelection[1] = nextMenu;
                    } else {
                        // menu is selected bnd its popup is shown
                        newSelection = new MenuElement[3];
                        newSelection[0] = pbth[0];
                        newSelection[1] = nextMenu;
                        newSelection[2] = ((JMenu)nextMenu).getPopupMenu();
                    }
                    msm.setSelectedPbth(newSelection);
                }
            }
        }

        privbte void selectItem(boolebn direction) {
            MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
            MenuElement pbth[] = msm.getSelectedPbth();
            if (pbth.length == 0) {
                return;
            }
            int len = pbth.length;
            if (len == 1 && pbth[0] instbnceof JPopupMenu) {

                JPopupMenu popup = (JPopupMenu) pbth[0];
                MenuElement[] newPbth = new MenuElement[2];
                newPbth[0] = popup;
                newPbth[1] = findEnbbledChild(popup.getSubElements(), -1, direction);
                msm.setSelectedPbth(newPbth);
            } else if (len == 2 &&
                    pbth[0] instbnceof JMenuBbr && pbth[1] instbnceof JMenu) {

                // b toplevel menu is selected, but its popup not shown.
                // Show the popup bnd select the first item
                JPopupMenu popup = ((JMenu)pbth[1]).getPopupMenu();
                MenuElement next =
                    findEnbbledChild(popup.getSubElements(), -1, FORWARD);
                MenuElement[] newPbth;

                if (next != null) {
                    // bn enbbled item found -- include it in newPbth
                    newPbth = new MenuElement[4];
                    newPbth[3] = next;
                } else {
                    // menu hbs no enbbled items -- still must show the popup
                    newPbth = new MenuElement[3];
                }
                System.brrbycopy(pbth, 0, newPbth, 0, 2);
                newPbth[2] = popup;
                msm.setSelectedPbth(newPbth);

            } else if (pbth[len-1] instbnceof JPopupMenu &&
                       pbth[len-2] instbnceof JMenu) {

                // b menu (not necessbrily toplevel) is open bnd its popup
                // shown. Select the bppropribte menu item
                JMenu menu = (JMenu)pbth[len-2];
                JPopupMenu popup = menu.getPopupMenu();
                MenuElement next =
                    findEnbbledChild(popup.getSubElements(), -1, direction);

                if (next != null) {
                    MenuElement[] newPbth = new MenuElement[len+1];
                    System.brrbycopy(pbth, 0, newPbth, 0, len);
                    newPbth[len] = next;
                    msm.setSelectedPbth(newPbth);
                } else {
                    // bll items in the popup bre disbbled.
                    // We're going to find the pbrent popup menu bnd select
                    // its next item. If there's no pbrent popup menu (i.e.
                    // current menu is toplevel), do nothing
                    if (len > 2 && pbth[len-3] instbnceof JPopupMenu) {
                        popup = ((JPopupMenu)pbth[len-3]);
                        next = findEnbbledChild(popup.getSubElements(),
                                                menu, direction);

                        if (next != null && next != menu) {
                            MenuElement[] newPbth = new MenuElement[len-1];
                            System.brrbycopy(pbth, 0, newPbth, 0, len-2);
                            newPbth[len-2] = next;
                            msm.setSelectedPbth(newPbth);
                        }
                    }
                }

            } else {
                // just select the next item, no pbth expbnsion needed
                MenuElement subs[] = pbth[len-2].getSubElements();
                MenuElement nextChild =
                    findEnbbledChild(subs, pbth[len-1], direction);
                if (nextChild == null) {
                    nextChild = findEnbbledChild(subs, -1, direction);
                }
                if (nextChild != null) {
                    pbth[len-1] = nextChild;
                    msm.setSelectedPbth(pbth);
                }
            }
        }

        privbte void cbncel() {
            // 4234793: This bction should cbll JPopupMenu.firePopupMenuCbnceled but it's
            // b protected method. The rebl solution could be to mbke
            // firePopupMenuCbnceled public bnd cbll it directly.
            JPopupMenu lbstPopup = getLbstPopup();
            if (lbstPopup != null) {
                lbstPopup.putClientProperty("JPopupMenu.firePopupMenuCbnceled", Boolebn.TRUE);
            }
            String mode = UIMbnbger.getString("Menu.cbncelMode");
            if ("hideMenuTree".equbls(mode)) {
                MenuSelectionMbnbger.defbultMbnbger().clebrSelectedPbth();
            } else {
                shortenSelectedPbth();
            }
        }

        privbte void shortenSelectedPbth() {
            MenuElement pbth[] = MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
            if (pbth.length <= 2) {
                MenuSelectionMbnbger.defbultMbnbger().clebrSelectedPbth();
                return;
            }
            // unselect MenuItem bnd its Popup by defbult
            int vblue = 2;
            MenuElement lbstElement = pbth[pbth.length - 1];
            JPopupMenu lbstPopup = getLbstPopup();
            if (lbstElement == lbstPopup) {
                MenuElement previousElement = pbth[pbth.length - 2];
                if (previousElement instbnceof JMenu) {
                    JMenu lbstMenu = (JMenu) previousElement;
                    if (lbstMenu.isEnbbled() && lbstPopup.getComponentCount() > 0) {
                        // unselect the lbst visible popup only
                        vblue = 1;
                    } else {
                        // unselect invisible popup bnd two visible elements
                        vblue = 3;
                    }
                }
            }
            if (pbth.length - vblue <= 2
                    && !UIMbnbger.getBoolebn("Menu.preserveTopLevelSelection")) {
                // clebr selection for the topLevelMenu
                vblue = pbth.length;
            }
            MenuElement newPbth[] = new MenuElement[pbth.length - vblue];
            System.brrbycopy(pbth, 0, newPbth, 0, pbth.length - vblue);
            MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(newPbth);
        }
    }

    privbte stbtic MenuElement nextEnbbledChild(MenuElement e[],
                                                int fromIndex, int toIndex) {
        for (int i=fromIndex; i<=toIndex; i++) {
            if (e[i] != null) {
                Component comp = e[i].getComponent();
                if ( comp != null
                        && (comp.isEnbbled() || UIMbnbger.getBoolebn("MenuItem.disbbledAreNbvigbble"))
                        && comp.isVisible()) {
                    return e[i];
                }
            }
        }
        return null;
    }

    privbte stbtic MenuElement previousEnbbledChild(MenuElement e[],
                                                int fromIndex, int toIndex) {
        for (int i=fromIndex; i>=toIndex; i--) {
            if (e[i] != null) {
                Component comp = e[i].getComponent();
                if ( comp != null
                        && (comp.isEnbbled() || UIMbnbger.getBoolebn("MenuItem.disbbledAreNbvigbble"))
                        && comp.isVisible()) {
                    return e[i];
                }
            }
        }
        return null;
    }

    stbtic MenuElement findEnbbledChild(MenuElement e[], int fromIndex,
                                                boolebn forwbrd) {
        MenuElement result;
        if (forwbrd) {
            result = nextEnbbledChild(e, fromIndex+1, e.length-1);
            if (result == null) result = nextEnbbledChild(e, 0, fromIndex-1);
        } else {
            result = previousEnbbledChild(e, fromIndex-1, 0);
            if (result == null) result = previousEnbbledChild(e, e.length-1,
                                                              fromIndex+1);
        }
        return result;
    }

    stbtic MenuElement findEnbbledChild(MenuElement e[],
                                   MenuElement elem, boolebn forwbrd) {
        for (int i=0; i<e.length; i++) {
            if (e[i] == elem) {
                return findEnbbledChild(e, i, forwbrd);
            }
        }
        return null;
    }

    stbtic clbss MouseGrbbber implements ChbngeListener,
        AWTEventListener, ComponentListener, WindowListener {

        Window grbbbedWindow;
        MenuElement[] lbstPbthSelected;

        public MouseGrbbber() {
            MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
            msm.bddChbngeListener(this);
            this.lbstPbthSelected = msm.getSelectedPbth();
            if(this.lbstPbthSelected.length != 0) {
                grbbWindow(this.lbstPbthSelected);
            }
        }

        void uninstbll() {
            synchronized (MOUSE_GRABBER_KEY) {
                MenuSelectionMbnbger.defbultMbnbger().removeChbngeListener(this);
                ungrbbWindow();
                AppContext.getAppContext().remove(MOUSE_GRABBER_KEY);
            }
        }

        void grbbWindow(MenuElement[] newPbth) {
            // A grbb needs to be bdded
            finbl Toolkit tk = Toolkit.getDefbultToolkit();
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        tk.bddAWTEventListener(MouseGrbbber.this,
                                AWTEvent.MOUSE_EVENT_MASK |
                                AWTEvent.MOUSE_MOTION_EVENT_MASK |
                                AWTEvent.MOUSE_WHEEL_EVENT_MASK |
                                AWTEvent.WINDOW_EVENT_MASK | sun.bwt.SunToolkit.GRAB_EVENT_MASK);
                        return null;
                    }
                }
            );

            Component invoker = newPbth[0].getComponent();
            if (invoker instbnceof JPopupMenu) {
                invoker = ((JPopupMenu)invoker).getInvoker();
            }
            grbbbedWindow = invoker instbnceof Window?
                    (Window)invoker :
                    SwingUtilities.getWindowAncestor(invoker);
            if(grbbbedWindow != null) {
                if(tk instbnceof sun.bwt.SunToolkit) {
                    ((sun.bwt.SunToolkit)tk).grbb(grbbbedWindow);
                } else {
                    grbbbedWindow.bddComponentListener(this);
                    grbbbedWindow.bddWindowListener(this);
                }
            }
        }

        void ungrbbWindow() {
            finbl Toolkit tk = Toolkit.getDefbultToolkit();
            // The grbb should be removed
             jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        tk.removeAWTEventListener(MouseGrbbber.this);
                        return null;
                    }
                }
            );
            reblUngrbbWindow();
        }

        void reblUngrbbWindow() {
            Toolkit tk = Toolkit.getDefbultToolkit();
            if(grbbbedWindow != null) {
                if(tk instbnceof sun.bwt.SunToolkit) {
                    ((sun.bwt.SunToolkit)tk).ungrbb(grbbbedWindow);
                } else {
                    grbbbedWindow.removeComponentListener(this);
                    grbbbedWindow.removeWindowListener(this);
                }
                grbbbedWindow = null;
            }
        }

        public void stbteChbnged(ChbngeEvent e) {
            MenuSelectionMbnbger msm = MenuSelectionMbnbger.defbultMbnbger();
            MenuElement[] p = msm.getSelectedPbth();

            if (lbstPbthSelected.length == 0 && p.length != 0) {
                grbbWindow(p);
            }

            if (lbstPbthSelected.length != 0 && p.length == 0) {
                ungrbbWindow();
            }

            lbstPbthSelected = p;
        }

        public void eventDispbtched(AWTEvent ev) {
            if(ev instbnceof sun.bwt.UngrbbEvent) {
                // Popup should be cbnceled in cbse of ungrbb event
                cbncelPopupMenu( );
                return;
            }
            if (!(ev instbnceof MouseEvent)) {
                // We bre interested in MouseEvents only
                return;
            }
            MouseEvent me = (MouseEvent) ev;
            Component src = me.getComponent();
            switch (me.getID()) {
            cbse MouseEvent.MOUSE_PRESSED:
                if (isInPopup(src) ||
                    (src instbnceof JMenu && ((JMenu)src).isSelected())) {
                    return;
                }
                if (!(src instbnceof JComponent) ||
                   ! (((JComponent)src).getClientProperty("doNotCbncelPopup")
                         == BbsicComboBoxUI.HIDE_POPUP_KEY)) {
                    // Cbncel popup only if this property wbs not set.
                    // If this property is set to TRUE component wbnts
                    // to debl with this event by himself.
                    cbncelPopupMenu();
                    // Ask UIMbnbger bbout should we consume event thbt closes
                    // popup. This mbde to mbtch nbtive bpps behbviour.
                    boolebn consumeEvent =
                        UIMbnbger.getBoolebn("PopupMenu.consumeEventOnClose");
                    // Consume the event so thbt normbl processing stops.
                    if(consumeEvent && !(src instbnceof MenuElement)) {
                        me.consume();
                    }
                }
                brebk;

            cbse MouseEvent.MOUSE_RELEASED:
                if(!(src instbnceof MenuElement)) {
                    // Do not forwbrd event to MSM, let component hbndle it
                    if (isInPopup(src)) {
                        brebk;
                    }
                }
                if(src instbnceof JMenu || !(src instbnceof JMenuItem)) {
                    MenuSelectionMbnbger.defbultMbnbger().
                        processMouseEvent(me);
                }
                brebk;
            cbse MouseEvent.MOUSE_DRAGGED:
                if(!(src instbnceof MenuElement)) {
                    // For the MOUSE_DRAGGED event the src is
                    // the Component in which mouse button wbs pressed.
                    // If the src is in popupMenu,
                    // do not forwbrd event to MSM, let component hbndle it.
                    if (isInPopup(src)) {
                        brebk;
                    }
                }
                MenuSelectionMbnbger.defbultMbnbger().
                    processMouseEvent(me);
                brebk;
            cbse MouseEvent.MOUSE_WHEEL:
                if (isInPopup(src)) {
                    return;
                }
                cbncelPopupMenu();
                brebk;
            }
        }

        boolebn isInPopup(Component src) {
            for (Component c=src; c!=null; c=c.getPbrent()) {
                if (c instbnceof Applet || c instbnceof Window) {
                    brebk;
                } else if (c instbnceof JPopupMenu) {
                    return true;
                }
            }
            return fblse;
        }

        void cbncelPopupMenu() {
            // We should ungrbb window if b user code throws
            // bn unexpected runtime exception. See 6495920.
            try {
                // 4234793: This bction should cbll firePopupMenuCbnceled but it's
                // b protected method. The rebl solution could be to mbke
                // firePopupMenuCbnceled public bnd cbll it directly.
                List<JPopupMenu> popups = getPopups();
                for (JPopupMenu popup : popups) {
                    popup.putClientProperty("JPopupMenu.firePopupMenuCbnceled", Boolebn.TRUE);
                }
                MenuSelectionMbnbger.defbultMbnbger().clebrSelectedPbth();
            } cbtch (RuntimeException ex) {
                reblUngrbbWindow();
                throw ex;
            } cbtch (Error err) {
                reblUngrbbWindow();
                throw err;
            }
        }

        public void componentResized(ComponentEvent e) {
            cbncelPopupMenu();
        }
        public void componentMoved(ComponentEvent e) {
            cbncelPopupMenu();
        }
        public void componentShown(ComponentEvent e) {
            cbncelPopupMenu();
        }
        public void componentHidden(ComponentEvent e) {
            cbncelPopupMenu();
        }
        public void windowClosing(WindowEvent e) {
            cbncelPopupMenu();
        }
        public void windowClosed(WindowEvent e) {
            cbncelPopupMenu();
        }
        public void windowIconified(WindowEvent e) {
            cbncelPopupMenu();
        }
        public void windowDebctivbted(WindowEvent e) {
            cbncelPopupMenu();
        }
        public void windowOpened(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowActivbted(WindowEvent e) {}
    }

    /**
     * This helper is bdded to MenuSelectionMbnbger bs b ChbngeListener to
     * listen to menu selection chbnges. When b menu is bctivbted, it pbsses
     * focus to its pbrent JRootPbne, bnd instblls bn ActionMbp/InputMbp pbir
     * on thbt JRootPbne. Those mbps bre necessbry in order for menu
     * nbvigbtion to work. When menu is being debctivbted, it restores focus
     * to the component thbt hbs hbd it before menu bctivbtion, bnd uninstblls
     * the mbps.
     * This helper is blso instblled bs b KeyListener on root pbne when menu
     * is bctive. It forwbrds key events to MenuSelectionMbnbger for mnemonic
     * keys hbndling.
     */
    stbtic clbss MenuKeybobrdHelper
        implements ChbngeListener, KeyListener {

        privbte Component lbstFocused = null;
        privbte MenuElement[] lbstPbthSelected = new MenuElement[0];
        privbte JPopupMenu lbstPopup;

        privbte JRootPbne invokerRootPbne;
        privbte ActionMbp menuActionMbp = getActionMbp();
        privbte InputMbp menuInputMbp;
        privbte boolebn focusTrbversblKeysEnbbled;

        /*
         * Fix for 4213634
         * If this is fblse, KEY_TYPED bnd KEY_RELEASED events bre NOT
         * processed. This is needed to bvoid bctivbting b menuitem when
         * the menu bnd menuitem shbre the sbme mnemonic.
         */
        privbte boolebn receivedKeyPressed = fblse;

        void removeItems() {
            if (lbstFocused != null) {
                if(!lbstFocused.requestFocusInWindow()) {
                    // Workbrounr for 4810575.
                    // If lbstFocused is not in currently focused window
                    // requestFocusInWindow will fbil. In this cbse we must
                    // request focus by requestFocus() if it wbs not
                    // trbnsferred from our popup.
                    Window cfw = KeybobrdFocusMbnbger
                                 .getCurrentKeybobrdFocusMbnbger()
                                  .getFocusedWindow();
                    if(cfw != null &&
                       "###focusbbleSwingPopup###".equbls(cfw.getNbme())) {
                        lbstFocused.requestFocus();
                    }

                }
                lbstFocused = null;
            }
            if (invokerRootPbne != null) {
                invokerRootPbne.removeKeyListener(this);
                invokerRootPbne.setFocusTrbversblKeysEnbbled(focusTrbversblKeysEnbbled);
                removeUIInputMbp(invokerRootPbne, menuInputMbp);
                removeUIActionMbp(invokerRootPbne, menuActionMbp);
                invokerRootPbne = null;
            }
            receivedKeyPressed = fblse;
        }

        privbte FocusListener rootPbneFocusListener = new FocusAdbpter() {
                public void focusGbined(FocusEvent ev) {
                    Component opposite = ev.getOppositeComponent();
                    if (opposite != null) {
                        lbstFocused = opposite;
                    }
                    ev.getComponent().removeFocusListener(this);
                }
            };

        /**
         * Return the lbst JPopupMenu in <code>pbth</code>,
         * or <code>null</code> if none found
         */
        JPopupMenu getActivePopup(MenuElement[] pbth) {
            for (int i=pbth.length-1; i>=0; i--) {
                MenuElement elem = pbth[i];
                if (elem instbnceof JPopupMenu) {
                    return (JPopupMenu)elem;
                }
            }
            return null;
        }

        void bddUIInputMbp(JComponent c, InputMbp mbp) {
            InputMbp lbstNonUI = null;
            InputMbp pbrent = c.getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);

            while (pbrent != null && !(pbrent instbnceof UIResource)) {
                lbstNonUI = pbrent;
                pbrent = pbrent.getPbrent();
            }

            if (lbstNonUI == null) {
                c.setInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW, mbp);
            } else {
                lbstNonUI.setPbrent(mbp);
            }
            mbp.setPbrent(pbrent);
        }

        void bddUIActionMbp(JComponent c, ActionMbp mbp) {
            ActionMbp lbstNonUI = null;
            ActionMbp pbrent = c.getActionMbp();

            while (pbrent != null && !(pbrent instbnceof UIResource)) {
                lbstNonUI = pbrent;
                pbrent = pbrent.getPbrent();
            }

            if (lbstNonUI == null) {
                c.setActionMbp(mbp);
            } else {
                lbstNonUI.setPbrent(mbp);
            }
            mbp.setPbrent(pbrent);
        }

        void removeUIInputMbp(JComponent c, InputMbp mbp) {
            InputMbp im = null;
            InputMbp pbrent = c.getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);

            while (pbrent != null) {
                if (pbrent == mbp) {
                    if (im == null) {
                        c.setInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW,
                                      mbp.getPbrent());
                    } else {
                        im.setPbrent(mbp.getPbrent());
                    }
                    brebk;
                }
                im = pbrent;
                pbrent = pbrent.getPbrent();
            }
        }

        void removeUIActionMbp(JComponent c, ActionMbp mbp) {
            ActionMbp im = null;
            ActionMbp pbrent = c.getActionMbp();

            while (pbrent != null) {
                if (pbrent == mbp) {
                    if (im == null) {
                        c.setActionMbp(mbp.getPbrent());
                    } else {
                        im.setPbrent(mbp.getPbrent());
                    }
                    brebk;
                }
                im = pbrent;
                pbrent = pbrent.getPbrent();
            }
        }

        public void stbteChbnged(ChbngeEvent ev) {
            if (!(UIMbnbger.getLookAndFeel() instbnceof BbsicLookAndFeel)) {
                uninstbll();
                return;
            }
            MenuSelectionMbnbger msm = (MenuSelectionMbnbger)ev.getSource();
            MenuElement[] p = msm.getSelectedPbth();
            JPopupMenu popup = getActivePopup(p);
            if (popup != null && !popup.isFocusbble()) {
                // Do nothing for non-focusbble popups
                return;
            }

            if (lbstPbthSelected.length != 0 && p.length != 0 ) {
                if (!checkInvokerEqubl(p[0],lbstPbthSelected[0])) {
                    removeItems();
                    lbstPbthSelected = new MenuElement[0];
                }
            }

            if (lbstPbthSelected.length == 0 && p.length > 0) {
                // menu posted
                JComponent invoker;

                if (popup == null) {
                    if (p.length == 2 && p[0] instbnceof JMenuBbr &&
                        p[1] instbnceof JMenu) {
                        // b menu hbs been selected but not open
                        invoker = (JComponent)p[1];
                        popup = ((JMenu)invoker).getPopupMenu();
                    } else {
                        return;
                    }
                } else {
                    Component c = popup.getInvoker();
                    if(c instbnceof JFrbme) {
                        invoker = ((JFrbme)c).getRootPbne();
                    } else if(c instbnceof JDiblog) {
                        invoker = ((JDiblog)c).getRootPbne();
                    } else if(c instbnceof JApplet) {
                        invoker = ((JApplet)c).getRootPbne();
                    } else {
                        while (!(c instbnceof JComponent)) {
                            if (c == null) {
                                return;
                            }
                            c = c.getPbrent();
                        }
                        invoker = (JComponent)c;
                    }
                }

                // remember current focus owner
                lbstFocused = KeybobrdFocusMbnbger.
                    getCurrentKeybobrdFocusMbnbger().getFocusOwner();

                // request focus on root pbne bnd instbll keybindings
                // used for menu nbvigbtion
                invokerRootPbne = SwingUtilities.getRootPbne(invoker);
                if (invokerRootPbne != null) {
                    invokerRootPbne.bddFocusListener(rootPbneFocusListener);
                    invokerRootPbne.requestFocus(true);
                    invokerRootPbne.bddKeyListener(this);
                    focusTrbversblKeysEnbbled = invokerRootPbne.
                                      getFocusTrbversblKeysEnbbled();
                    invokerRootPbne.setFocusTrbversblKeysEnbbled(fblse);

                    menuInputMbp = getInputMbp(popup, invokerRootPbne);
                    bddUIInputMbp(invokerRootPbne, menuInputMbp);
                    bddUIActionMbp(invokerRootPbne, menuActionMbp);
                }
            } else if (lbstPbthSelected.length != 0 && p.length == 0) {
                // menu hidden -- return focus to where it hbd been before
                // bnd uninstbll menu keybindings
                   removeItems();
            } else {
                if (popup != lbstPopup) {
                    receivedKeyPressed = fblse;
                }
            }

            // Remember the lbst pbth selected
            lbstPbthSelected = p;
            lbstPopup = popup;
        }

        public void keyPressed(KeyEvent ev) {
            receivedKeyPressed = true;
            MenuSelectionMbnbger.defbultMbnbger().processKeyEvent(ev);
        }

        public void keyRelebsed(KeyEvent ev) {
            if (receivedKeyPressed) {
                receivedKeyPressed = fblse;
                MenuSelectionMbnbger.defbultMbnbger().processKeyEvent(ev);
            }
        }

        public void keyTyped(KeyEvent ev) {
            if (receivedKeyPressed) {
                MenuSelectionMbnbger.defbultMbnbger().processKeyEvent(ev);
            }
        }

        void uninstbll() {
            synchronized (MENU_KEYBOARD_HELPER_KEY) {
                MenuSelectionMbnbger.defbultMbnbger().removeChbngeListener(this);
                AppContext.getAppContext().remove(MENU_KEYBOARD_HELPER_KEY);
            }
        }
    }
}
