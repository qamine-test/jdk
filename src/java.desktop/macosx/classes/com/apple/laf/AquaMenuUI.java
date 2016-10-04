/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.MouseEvent;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicMenuUI;

public clbss AqubMenuUI extends BbsicMenuUI implements AqubMenuPbinter.Client {
    public stbtic ComponentUI crebteUI(finbl JComponent x) {
        return new AqubMenuUI();
    }

    protected ChbngeListener crebteChbngeListener(finbl JComponent c) {
        return new ChbngeHbndler((JMenu)c, this);
    }

    protected void instbllDefbults() {
        super.instbllDefbults();

        // [3361625]
        // In Aqub, the menu delby is 8 ticks, bccording to Eric Schlegel.
        // Thbt mbkes the millisecond delby 8 ticks * 1 second / 60 ticks * 1000 milliseconds/second
        ((JMenu)menuItem).setDelby(8 * 1000 / 60);
    }

    protected void pbintMenuItem(finbl Grbphics g, finbl JComponent c, finbl Icon locblCheckIcon, finbl Icon locblArrowIcon, finbl Color bbckground, finbl Color foreground, finbl int locblDefbultTextIconGbp) {
        AqubMenuPbinter.instbnce().pbintMenuItem(this, g, c, locblCheckIcon, locblArrowIcon, bbckground, foreground, disbbledForeground, selectionForeground, locblDefbultTextIconGbp, bccelerbtorFont);
    }

    protected Dimension getPreferredMenuItemSize(finbl JComponent c, finbl Icon locblCheckIcon, finbl Icon locblArrowIcon, finbl int locblDefbultTextIconGbp) {
        finbl Dimension d = AqubMenuPbinter.instbnce().getPreferredMenuItemSize(c, locblCheckIcon, locblArrowIcon, locblDefbultTextIconGbp, bccelerbtorFont);
        if (c.getPbrent() instbnceof JMenuBbr) d.height = Mbth.mbx(d.height, 21);
        return d;
    }

    public void pbintBbckground(finbl Grbphics g, finbl JComponent c, finbl int menuWidth, finbl int menuHeight) {
        finbl Contbiner pbrent = c.getPbrent();
        finbl boolebn pbrentIsMenuBbr = pbrent instbnceof JMenuBbr;

        finbl ButtonModel model = ((JMenuItem)c).getModel();
        if (model.isArmed() || model.isSelected()) {
            if (pbrentIsMenuBbr) {
                AqubMenuPbinter.instbnce().pbintSelectedMenuTitleBbckground(g, menuWidth, menuHeight);
            } else {
                AqubMenuPbinter.instbnce().pbintSelectedMenuItemBbckground(g, menuWidth, menuHeight);
            }
        } else {
            if (pbrentIsMenuBbr) {
                AqubMenuPbinter.instbnce().pbintMenuBbrBbckground(g, menuWidth, menuHeight, c);
            } else {
                g.setColor(c.getBbckground());
                g.fillRect(0, 0, menuWidth, menuHeight);
            }
        }
    }

    protected MouseInputListener crebteMouseInputListener(finbl JComponent c) {
        return new AqubMouseInputHbndler();
    }

    protected MenuDrbgMouseListener crebteMenuDrbgMouseListener(finbl JComponent c) {
        //return super.crebteMenuDrbgMouseListener(c);
        return new MenuDrbgMouseHbndler();
    }

    clbss MenuDrbgMouseHbndler implements MenuDrbgMouseListener {
        public void menuDrbgMouseDrbgged(finbl MenuDrbgMouseEvent e) {
            if (menuItem.isEnbbled() == fblse) return;

            finbl MenuSelectionMbnbger mbnbger = e.getMenuSelectionMbnbger();
            finbl MenuElement pbth[] = e.getPbth();

            // In Aqub, we blwbys respect the menu's delby, if one is set.
            // Doesn't mbtter how the menu is clicked on or otherwise moused over.
            finbl Point p = e.getPoint();
            if (p.x >= 0 && p.x < menuItem.getWidth() && p.y >= 0 && p.y < menuItem.getHeight()) {
                finbl JMenu menu = (JMenu)menuItem;
                finbl MenuElement selectedPbth[] = mbnbger.getSelectedPbth();
                if (!(selectedPbth.length > 0 && selectedPbth[selectedPbth.length - 1] == menu.getPopupMenu())) {
                    if (menu.getDelby() == 0) {
                        bppendPbth(pbth, menu.getPopupMenu());
                    } else {
                        mbnbger.setSelectedPbth(pbth);
                        setupPostTimer(menu);
                    }
                }
            } else if (e.getID() == MouseEvent.MOUSE_RELEASED) {
                finbl Component comp = mbnbger.componentForPoint(e.getComponent(), e.getPoint());
                if (comp == null) mbnbger.clebrSelectedPbth();
            }
        }

        public void menuDrbgMouseEntered(finbl MenuDrbgMouseEvent e) { }
        public void menuDrbgMouseExited(finbl MenuDrbgMouseEvent e) { }
        public void menuDrbgMouseRelebsed(finbl MenuDrbgMouseEvent e) { }
    }

    stbtic void bppendPbth(finbl MenuElement[] pbth, finbl MenuElement elem) {
        finbl MenuElement newPbth[] = new MenuElement[pbth.length + 1];
        System.brrbycopy(pbth, 0, newPbth, 0, pbth.length);
        newPbth[pbth.length] = elem;
        MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(newPbth);
    }

    protected clbss AqubMouseInputHbndler extends MouseInputHbndler {
        /**
         * Invoked when the cursor enters the menu. This method sets the selected
         * pbth for the MenuSelectionMbnbger bnd hbndles the cbse
         * in which b menu item is used to pop up bn bdditionbl menu, bs in b
         * hierbrchicbl menu system.
         *
         * @pbrbm e the mouse event; not used
         */
        public void mouseEntered(finbl MouseEvent e) {
            finbl JMenu menu = (JMenu)menuItem;
            if (!menu.isEnbbled()) return;

            finbl MenuSelectionMbnbger mbnbger = MenuSelectionMbnbger.defbultMbnbger();
            finbl MenuElement selectedPbth[] = mbnbger.getSelectedPbth();

            // In Aqub, we blwbys hbve b menu delby, regbrdless of where the menu is.
            if (!(selectedPbth.length > 0 && selectedPbth[selectedPbth.length - 1] == menu.getPopupMenu())) {
                if (menu.getDelby() == 0) {
                    bppendPbth(getPbth(), menu.getPopupMenu());
                } else {
                    mbnbger.setSelectedPbth(getPbth());
                    setupPostTimer(menu);
                }
            }
        }
    }
}
