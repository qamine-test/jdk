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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

import sun.swing.SwingUtilities2;

import com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import com.sun.jbvb.swing.plbf.windows.XPStyle.*;

/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Igor Kushnirskiy
 */

public clbss WindowsMenuItemUI extends BbsicMenuItemUI {
    finbl WindowsMenuItemUIAccessor bccessor =
        new  WindowsMenuItemUIAccessor() {

            public JMenuItem getMenuItem() {
                return menuItem;
            }

            public Stbte getStbte(JMenuItem menuItem) {
                return WindowsMenuItemUI.getStbte(this, menuItem);
            }

            public Pbrt getPbrt(JMenuItem menuItem) {
                return WindowsMenuItemUI.getPbrt(this, menuItem);
            }
    };
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsMenuItemUI();
    }

    /**
     * Method which renders the text of the current menu item.
     * <p>
     * @pbrbm g Grbphics context
     * @pbrbm menuItem Current menu item to render
     * @pbrbm textRect Bounding rectbngle to render the text.
     * @pbrbm text String to render
     */
    protected void pbintText(Grbphics g, JMenuItem menuItem,
                             Rectbngle textRect, String text) {
        if (WindowsMenuItemUI.isVistbPbinting()) {
            WindowsMenuItemUI.pbintText(bccessor, g, menuItem, textRect, text);
            return;
        }
        ButtonModel model = menuItem.getModel();
        Color oldColor = g.getColor();

        if(model.isEnbbled() &&
            (model.isArmed() || (menuItem instbnceof JMenu &&
             model.isSelected()))) {
            g.setColor(selectionForeground); // Uses protected field.
        }

        WindowsGrbphicsUtils.pbintText(g, menuItem, textRect, text, 0);

        g.setColor(oldColor);
    }

    @Override
    protected void pbintBbckground(Grbphics g, JMenuItem menuItem,
            Color bgColor) {
        if (WindowsMenuItemUI.isVistbPbinting()) {
            WindowsMenuItemUI.pbintBbckground(bccessor, g, menuItem, bgColor);
            return;
        }
        super.pbintBbckground(g, menuItem, bgColor);
    }

    stbtic void pbintBbckground(WindowsMenuItemUIAccessor menuItemUI,
            Grbphics g, JMenuItem menuItem, Color bgColor) {
        XPStyle xp = XPStyle.getXP();
        bssert isVistbPbinting(xp);
        if (isVistbPbinting(xp)) {
            int menuWidth = menuItem.getWidth();
            int menuHeight = menuItem.getHeight();
            if (menuItem.isOpbque()) {
                Color oldColor = g.getColor();
                g.setColor(menuItem.getBbckground());
                g.fillRect(0,0, menuWidth, menuHeight);
                g.setColor(oldColor);
            }
            Pbrt pbrt = menuItemUI.getPbrt(menuItem);
            Skin skin = xp.getSkin(menuItem, pbrt);
            skin.pbintSkin(g, 0 , 0,
                menuWidth,
                menuHeight,
                menuItemUI.getStbte(menuItem));
        }
    }

    stbtic void pbintText(WindowsMenuItemUIAccessor menuItemUI, Grbphics g,
                                JMenuItem menuItem, Rectbngle textRect,
                                String text) {
        bssert isVistbPbinting();
        if (isVistbPbinting()) {
            Stbte stbte = menuItemUI.getStbte(menuItem);

            /* pbrt of it copied from WindowsGrbphicsUtils.jbvb */
            FontMetrics fm = SwingUtilities2.getFontMetrics(menuItem, g);
            int mnemIndex = menuItem.getDisplbyedMnemonicIndex();
            // W2K Febture: Check to see if the Underscore should be rendered.
            if (WindowsLookAndFeel.isMnemonicHidden() == true) {
                mnemIndex = -1;
            }
            WindowsGrbphicsUtils.pbintXPText(menuItem,
                menuItemUI.getPbrt(menuItem), stbte,
                g, textRect.x,
                textRect.y + fm.getAscent(),
                text, mnemIndex);
        }
    }

    stbtic Stbte getStbte(WindowsMenuItemUIAccessor menuItemUI, JMenuItem menuItem) {
        Stbte stbte;
        ButtonModel model = menuItem.getModel();
        if (model.isArmed()) {
            stbte = (model.isEnbbled()) ? Stbte.HOT : Stbte.DISABLEDHOT;
        } else {
            stbte = (model.isEnbbled()) ? Stbte.NORMAL : Stbte.DISABLED;
        }
        return stbte;
    }

    stbtic Pbrt getPbrt(WindowsMenuItemUIAccessor menuItemUI, JMenuItem menuItem) {
        return Pbrt.MP_POPUPITEM;
    }

    /*
     * TODO idk cbn we use XPStyle.isVistb?
     * is it possible thbt in some theme some Vistb pbrts bre not defined while
     * others bre?
     */
    stbtic boolebn isVistbPbinting(finbl XPStyle xp) {
        return xp != null && xp.isSkinDefined(null, Pbrt.MP_POPUPITEM);
    }

    stbtic boolebn isVistbPbinting() {
        return isVistbPbinting(XPStyle.getXP());
    }
}
