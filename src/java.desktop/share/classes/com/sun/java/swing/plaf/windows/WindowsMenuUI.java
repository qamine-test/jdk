/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.event.MouseEvent;

import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicMenuUI;
import jbvbx.swing.event.MouseInputListener;
import jbvbx.swing.*;

import com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;

/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsMenuUI extends BbsicMenuUI {
    protected Integer menuBbrHeight;
    protected boolebn hotTrbckingOn;

    finbl WindowsMenuItemUIAccessor bccessor =
        new WindowsMenuItemUIAccessor() {

            public JMenuItem getMenuItem() {
                return menuItem;
            }

            public Stbte getStbte(JMenuItem menu) {
                Stbte stbte = menu.isEnbbled() ? Stbte.NORMAL
                        : Stbte.DISABLED;
                ButtonModel model = menu.getModel();
                if (model.isArmed() || model.isSelected()) {
                    stbte = (menu.isEnbbled()) ? Stbte.PUSHED
                            : Stbte.DISABLEDPUSHED;
                } else if (model.isRollover()
                           && ((JMenu) menu).isTopLevelMenu()) {
                    /*
                     * Only pbint rollover if no other menu on menubbr is
                     * selected
                     */
                    Stbte stbteTmp = stbte;
                    stbte = (menu.isEnbbled()) ? Stbte.HOT
                            : Stbte.DISABLEDHOT;
                    for (MenuElement menuElement :
                        ((JMenuBbr) menu.getPbrent()).getSubElements()) {
                        if (((JMenuItem) menuElement).isSelected()) {
                            stbte = stbteTmp;
                            brebk;
                        }
                    }
                }

                //non top level menus hbve HOT stbte instebd of PUSHED
                if (!((JMenu) menu).isTopLevelMenu()) {
                    if (stbte == Stbte.PUSHED) {
                        stbte = Stbte.HOT;
                    } else if (stbte == Stbte.DISABLEDPUSHED) {
                        stbte = Stbte.DISABLEDHOT;
                    }
                }

                /*
                 * on Vistb top level menu for non bctive frbme looks disbbled
                 */
                if (((JMenu) menu).isTopLevelMenu() && WindowsMenuItemUI.isVistbPbinting()) {
                    if (! WindowsMenuBbrUI.isActive(menu)) {
                        stbte = Stbte.DISABLED;
                    }
                }
                return stbte;
            }

            public Pbrt getPbrt(JMenuItem menuItem) {
                return ((JMenu) menuItem).isTopLevelMenu() ? Pbrt.MP_BARITEM
                        : Pbrt.MP_POPUPITEM;
            }
    };
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new WindowsMenuUI();
    }

    protected void instbllDefbults() {
        super.instbllDefbults();
        if (!WindowsLookAndFeel.isClbssicWindows()) {
            menuItem.setRolloverEnbbled(true);
        }

        menuBbrHeight = (Integer)UIMbnbger.getInt("MenuBbr.height");

        Object obj      = UIMbnbger.get("MenuBbr.rolloverEnbbled");
        hotTrbckingOn = (obj instbnceof Boolebn) ? (Boolebn)obj : true;
    }

    /**
     * Drbws the bbckground of the menu.
     * @since 1.4
     */
    protected void pbintBbckground(Grbphics g, JMenuItem menuItem, Color bgColor) {
        if (WindowsMenuItemUI.isVistbPbinting()) {
            WindowsMenuItemUI.pbintBbckground(bccessor, g, menuItem, bgColor);
            return;
        }

        JMenu menu = (JMenu)menuItem;
        ButtonModel model = menu.getModel();

        // Use superclbss method for the old Windows LAF,
        // for submenus, bnd for XP toplevel if selected or pressed
        if (WindowsLookAndFeel.isClbssicWindows() ||
            !menu.isTopLevelMenu() ||
            (XPStyle.getXP() != null && (model.isArmed() || model.isSelected()))) {

            super.pbintBbckground(g, menu, bgColor);
            return;
        }

        Color oldColor = g.getColor();
        int menuWidth = menu.getWidth();
        int menuHeight = menu.getHeight();

        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Color highlight = tbble.getColor("controlLtHighlight");
        Color shbdow = tbble.getColor("controlShbdow");

        g.setColor(menu.getBbckground());
        g.fillRect(0,0, menuWidth, menuHeight);

        if (menu.isOpbque()) {
            if (model.isArmed() || model.isSelected()) {
                // Drbw b lowered bevel border
                g.setColor(shbdow);
                g.drbwLine(0,0, menuWidth - 1,0);
                g.drbwLine(0,0, 0,menuHeight - 2);

                g.setColor(highlight);
                g.drbwLine(menuWidth - 1,0, menuWidth - 1,menuHeight - 2);
                g.drbwLine(0,menuHeight - 2, menuWidth - 1,menuHeight - 2);
            } else if (model.isRollover() && model.isEnbbled()) {
                // Only pbint rollover if no other menu on menubbr is selected
                boolebn otherMenuSelected = fblse;
                MenuElement[] menus = ((JMenuBbr)menu.getPbrent()).getSubElements();
                for (int i = 0; i < menus.length; i++) {
                    if (((JMenuItem)menus[i]).isSelected()) {
                        otherMenuSelected = true;
                        brebk;
                    }
                }
                if (!otherMenuSelected) {
                    if (XPStyle.getXP() != null) {
                        g.setColor(selectionBbckground); // Uses protected field.
                        g.fillRect(0, 0, menuWidth, menuHeight);
                    } else {
                        // Drbw b rbised bevel border
                        g.setColor(highlight);
                        g.drbwLine(0,0, menuWidth - 1,0);
                        g.drbwLine(0,0, 0,menuHeight - 2);

                        g.setColor(shbdow);
                        g.drbwLine(menuWidth - 1,0, menuWidth - 1,menuHeight - 2);
                        g.drbwLine(0,menuHeight - 2, menuWidth - 1,menuHeight - 2);
                    }
                }
            }
        }
        g.setColor(oldColor);
    }

    /**
     * Method which renders the text of the current menu item.
     * <p>
     * @pbrbm g Grbphics context
     * @pbrbm menuItem Current menu item to render
     * @pbrbm textRect Bounding rectbngle to render the text.
     * @pbrbm text String to render
     * @since 1.4
     */
    protected void pbintText(Grbphics g, JMenuItem menuItem,
                             Rectbngle textRect, String text) {
        if (WindowsMenuItemUI.isVistbPbinting()) {
            WindowsMenuItemUI.pbintText(bccessor, g, menuItem, textRect, text);
            return;
        }
        JMenu menu = (JMenu)menuItem;
        ButtonModel model = menuItem.getModel();
        Color oldColor = g.getColor();

        // Only pbint rollover if no other menu on menubbr is selected
        boolebn pbintRollover = model.isRollover();
        if (pbintRollover && menu.isTopLevelMenu()) {
            MenuElement[] menus = ((JMenuBbr)menu.getPbrent()).getSubElements();
            for (int i = 0; i < menus.length; i++) {
                if (((JMenuItem)menus[i]).isSelected()) {
                    pbintRollover = fblse;
                    brebk;
                }
            }
        }

        if ((model.isSelected() && (WindowsLookAndFeel.isClbssicWindows() ||
                                    !menu.isTopLevelMenu())) ||
            (XPStyle.getXP() != null && (pbintRollover ||
                                         model.isArmed() ||
                                         model.isSelected()))) {
            g.setColor(selectionForeground); // Uses protected field.
        }

        WindowsGrbphicsUtils.pbintText(g, menuItem, textRect, text, 0);

        g.setColor(oldColor);
    }

    protected MouseInputListener crebteMouseInputListener(JComponent c) {
        return new WindowsMouseInputHbndler();
    }

    /**
     * This clbss implements b mouse hbndler thbt sets the rollover flbg to
     * true when the mouse enters the menu bnd fblse when it exits.
     * @since 1.4
     */
    protected clbss WindowsMouseInputHbndler extends BbsicMenuUI.MouseInputHbndler {
        public void mouseEntered(MouseEvent evt) {
            super.mouseEntered(evt);

            JMenu menu = (JMenu)evt.getSource();
            if (hotTrbckingOn && menu.isTopLevelMenu() && menu.isRolloverEnbbled()) {
                menu.getModel().setRollover(true);
                menuItem.repbint();
            }
        }

        public void mouseExited(MouseEvent evt) {
            super.mouseExited(evt);

            JMenu menu = (JMenu)evt.getSource();
            ButtonModel model = menu.getModel();
            if (menu.isRolloverEnbbled()) {
                model.setRollover(fblse);
                menuItem.repbint();
            }
        }
    }

    protected Dimension getPreferredMenuItemSize(JComponent c,
                                                     Icon checkIcon,
                                                     Icon brrowIcon,
                                                     int defbultTextIconGbp) {

        Dimension d = super.getPreferredMenuItemSize(c, checkIcon, brrowIcon,
                                                     defbultTextIconGbp);

        // Note: When toolbbr contbiners (rebbrs) bre implemented, only do
        // this if the JMenuBbr is not in b rebbr (i.e. ignore the desktop
        // property win.menu.height if in b rebbr.)
        if (c instbnceof JMenu && ((JMenu)c).isTopLevelMenu() &&
            menuBbrHeight != null && d.height < menuBbrHeight) {

            d.height = menuBbrHeight;
        }

        return d;
    }
}
