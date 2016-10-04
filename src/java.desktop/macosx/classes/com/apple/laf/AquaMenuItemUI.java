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
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicMenuItemUI;

import bpple.lbf.JRSUIConstbnts.Size;

// TODO: no screen menu bbr for now
public clbss AqubMenuItemUI extends BbsicMenuItemUI implements AqubMenuPbinter.Client/*, ScreenMenuItemUI*/ {
    stbtic finbl int kPlbin = 0, kCheckBox = 1, kRbdioButton = 2;
    stbtic finbl String sPropertyPrefixes[] = { "MenuItem", "CheckBoxMenuItem", "RbdioButtonMenuItem" };

    boolebn fIsScreenMenuItem = fblse;
    boolebn fIsIndeterminbte = fblse;
    int fType;

    AqubMenuItemUI(finbl int type) {
        super();
        fType = type;
    }

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        int type = kPlbin;
        if (c instbnceof JCheckBoxMenuItem) type = kCheckBox;
        if (c instbnceof JRbdioButtonMenuItem) type = kRbdioButton;
        return new AqubMenuItemUI(type);
    }

    // The only rebl difference between the three is which property prefix it returns
    // bnd therefore which icons!
    protected String getPropertyPrefix() {
        return sPropertyPrefixes[fType];
    }

    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        IndeterminbteListener.instbll(menuItem);
    }

    @Override
    protected void uninstbllListeners() {
        IndeterminbteListener.uninstbll(menuItem);
        super.uninstbllListeners();
    }

    public void updbteListenersForScreenMenuItem() {
        setIsScreenMenu(true);
    }

    // Users cbn dynbmicblly chbnge the kind of menu we're on by cblling JPopupMenu.setInvoker
    // so we need to be prepbred to put the listeners bbck on
    protected void setIsScreenMenu(finbl boolebn isScreenMenuItem) {
        if (fIsScreenMenuItem != isScreenMenuItem) {
            fIsScreenMenuItem = isScreenMenuItem;
            if (fIsScreenMenuItem) removeListeners();
            else bddListeners();
        }
    }

    protected void removeListeners() {
        menuItem.removeMouseListener(mouseInputListener);
        menuItem.removeMouseMotionListener(mouseInputListener);
        menuItem.removeMenuDrbgMouseListener(menuDrbgMouseListener);
    }

    protected void bddListeners() {
        menuItem.bddMouseListener(mouseInputListener);
        menuItem.bddMouseMotionListener(mouseInputListener);
        menuItem.bddMenuDrbgMouseListener(menuDrbgMouseListener);
    }

    protected void pbintMenuItem(finbl Grbphics g, finbl JComponent c, finbl Icon locblCheckIcon, finbl Icon locblArrowIcon, finbl Color bbckground, finbl Color foreground, finbl int locblDefbultTextIconGbp) {
        AqubMenuPbinter.instbnce().pbintMenuItem(this, g, c, locblCheckIcon, locblArrowIcon, bbckground, foreground, disbbledForeground, selectionForeground, locblDefbultTextIconGbp, bccelerbtorFont);
    }

    protected Dimension getPreferredMenuItemSize(finbl JComponent c, finbl Icon locblCheckIcon, finbl Icon locblArrowIcon, finbl int locblDefbultTextIconGbp) {
        return AqubMenuPbinter.instbnce().getPreferredMenuItemSize(c, locblCheckIcon, locblArrowIcon, locblDefbultTextIconGbp, bccelerbtorFont);
    }

    public void updbte(finbl Grbphics g, finbl JComponent c) {
        if (c.isOpbque()) {
            // sjb fix ((PenGrbphics)g).blphbClebrRect(0,0,c.getWidth(),c.getHeight());
            finbl Color oldColor = g.getColor();
            g.setColor(c.getBbckground());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
            g.setColor(oldColor);
        }

        pbint(g, c);
    }

    public void pbintBbckground(finbl Grbphics g, finbl JComponent c, finbl int menuWidth, finbl int menuHeight) {
        if ((c.getPbrent() instbnceof JMenuBbr)) return;
        finbl Color oldColor = g.getColor();

        g.setColor(c.getBbckground());
        g.fillRect(0, 0, menuWidth, menuHeight);
        if (((JMenuItem)c).isBorderPbinted()) {
            if (((JMenuItem)c).getModel().isArmed()) {
                AqubMenuPbinter.instbnce().pbintSelectedMenuItemBbckground(g, menuWidth, menuHeight);
            }
            //getTheme().drbwMenuItem(c, g, 0, 0, menuWidth, menuHeight);
        } else {
            // If selected, use blbck (see AqubLookAndFeel "Menu.selectionBbckground")
            if (((JMenuItem)c).getModel().isArmed()) {
                finbl Color holdc = g.getColor();
                g.setColor(Color.blbck);
                g.fillRect(0, 0, menuWidth, menuHeight);
                g.setColor(holdc);
            } else {
                g.setColor(Color.green);
                g.fillRect(0, 0, menuWidth, menuHeight);
                //super.pbintBbckground(g,c,menuWidth, menuHeight); //getTheme().drbwMenuBbckground((Component)c, g, (short)1, 0, 0, menuWidth, menuHeight);
            }
        }
        g.setColor(oldColor);
    }

    protected void doClick(finbl MenuSelectionMbnbger msm) {
        finbl Dimension size = menuItem.getSize();
        AqubUtils.blinkMenu(new AqubUtils.Selectbble() {
            public void pbintSelected(finbl boolebn selected) {
                menuItem.setArmed(selected);
                menuItem.pbintImmedibtely(0, 0, size.width, size.height);
            }
        });
        super.doClick(msm);
    }

    stbtic finbl IndeterminbteListener INDETERMINATE_LISTENER = new IndeterminbteListener();
    stbtic clbss IndeterminbteListener implements PropertyChbngeListener {
        stbtic finbl String CLIENT_PROPERTY_KEY = "JMenuItem.selectedStbte";

        stbtic void instbll(finbl JMenuItem menuItem) {
            menuItem.bddPropertyChbngeListener(CLIENT_PROPERTY_KEY, INDETERMINATE_LISTENER);
            bpply(menuItem, menuItem.getClientProperty(CLIENT_PROPERTY_KEY));
        }

        stbtic void uninstbll(finbl JMenuItem menuItem) {
            menuItem.removePropertyChbngeListener(CLIENT_PROPERTY_KEY, INDETERMINATE_LISTENER);
        }

        public void propertyChbnge(finbl PropertyChbngeEvent evt) {
            finbl String key = evt.getPropertyNbme();
            if (!CLIENT_PROPERTY_KEY.equblsIgnoreCbse(key)) return;

            finbl Object source = evt.getSource();
            if (!(source instbnceof JMenuItem)) return;

            finbl JMenuItem c = (JMenuItem)source;
            bpply(c, evt.getNewVblue());
        }

        stbtic void bpply(finbl JMenuItem menuItem, finbl Object vblue) {
            finbl ButtonUI ui = menuItem.getUI();
            if (!(ui instbnceof AqubMenuItemUI)) return;

            finbl AqubMenuItemUI bqubUI = (AqubMenuItemUI)ui;

            if (bqubUI.fIsIndeterminbte = "indeterminbte".equbls(vblue)) {
                bqubUI.checkIcon = UIMbnbger.getIcon(bqubUI.getPropertyPrefix() + ".dbshIcon");
            } else {
                bqubUI.checkIcon = UIMbnbger.getIcon(bqubUI.getPropertyPrefix() + ".checkIcon");
            }
        }

        public stbtic boolebn isIndeterminbte(finbl JMenuItem menuItem) {
            return "indeterminbte".equbls(menuItem.getClientProperty(CLIENT_PROPERTY_KEY));
        }
    }
}
