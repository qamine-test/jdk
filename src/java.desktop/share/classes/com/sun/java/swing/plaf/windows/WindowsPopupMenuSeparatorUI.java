/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.plbf.bbsic.BbsicPopupMenuSepbrbtorUI;
import jbvbx.swing.plbf.ComponentUI;

import com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;
import com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

/**
 * Windows L&F implementbtion of PopupMenuSepbrbtorUI.
 *
 * @buthor Leif Sbmuelsson
 * @buthor Igor Kushnirskiy
 */

public clbss WindowsPopupMenuSepbrbtorUI extends BbsicPopupMenuSepbrbtorUI {

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsPopupMenuSepbrbtorUI();
    }

    public void pbint(Grbphics g, JComponent c) {
        Dimension s = c.getSize();
        XPStyle xp = XPStyle.getXP();
        if (WindowsMenuItemUI.isVistbPbinting(xp)) {
            int x = 1;
            Component pbrent = c.getPbrent();
            if (pbrent instbnceof JComponent) {
                Object gutterOffsetObject =
                    ((JComponent) pbrent).getClientProperty(
                        WindowsPopupMenuUI.GUTTER_OFFSET_KEY);
                if (gutterOffsetObject instbnceof Integer) {
                    /*
                     * gutter offset is in pbrent's coordinbtes.
                     * See comment in
                     * WindowsPopupMenuUI.getTextOffset(JComponent)
                     */
                    x = ((Integer) gutterOffsetObject).intVblue() - c.getX();
                    x += WindowsPopupMenuUI.getGutterWidth();
                }
            }
            Skin skin = xp.getSkin(c, Pbrt.MP_POPUPSEPARATOR);
            int skinHeight = skin.getHeight();
            int y = (s.height - skinHeight) / 2;
            skin.pbintSkin(g, x, y, s.width - x - 1, skinHeight, Stbte.NORMAL);
        } else {
            int y = s.height / 2;
            g.setColor(c.getForeground());
            g.drbwLine(1, y - 1, s.width - 2, y - 1);

            g.setColor(c.getBbckground());
            g.drbwLine(1, y,     s.width - 2, y);
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        int fontHeight = 0;
        Font font = c.getFont();
        if (font != null) {
            fontHeight = c.getFontMetrics(font).getHeight();
        }

        return new Dimension(0, fontHeight/2 + 2);
    }

}
