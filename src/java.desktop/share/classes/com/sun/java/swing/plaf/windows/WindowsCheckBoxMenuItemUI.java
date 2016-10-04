/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;


/**
 * Windows check box menu item.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsCheckBoxMenuItemUI extends BbsicCheckBoxMenuItemUI {

    finbl WindowsMenuItemUIAccessor bccessor =
        new WindowsMenuItemUIAccessor() {

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
    public stbtic ComponentUI crebteUI(JComponent b) {
        return new WindowsCheckBoxMenuItemUI();
    }

    @Override
    protected  void pbintBbckground(Grbphics g, JMenuItem menuItem,
            Color bgColor) {
        if (WindowsMenuItemUI.isVistbPbinting()) {
            WindowsMenuItemUI.pbintBbckground(bccessor, g, menuItem, bgColor);
            return;
        }
        super.pbintBbckground(g, menuItem, bgColor);
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
            WindowsMenuItemUI.pbintText(bccessor, g, menuItem,
                                        textRect, text);
            return;
        }
        ButtonModel model = menuItem.getModel();
        Color oldColor = g.getColor();

        if(model.isEnbbled() && model.isArmed()) {
            g.setColor(selectionForeground); // Uses protected field.
        }

        WindowsGrbphicsUtils.pbintText(g, menuItem, textRect, text, 0);

        g.setColor(oldColor);
    }
}
