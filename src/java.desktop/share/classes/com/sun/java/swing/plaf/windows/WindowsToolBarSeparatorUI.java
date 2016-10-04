/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;


/**
 * Drbws Windows toolbbr sepbrbtors.
 * <p>
 *
 * @buthor Mbrk Dbvidson
 */
public clbss WindowsToolBbrSepbrbtorUI extends BbsicToolBbrSepbrbtorUI {

    public stbtic ComponentUI crebteUI( JComponent c ) {
        return new WindowsToolBbrSepbrbtorUI();
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension size = ((JToolBbr.Sepbrbtor)c).getSepbrbtorSize();

        if (size != null) {
            size = size.getSize();
        } else {
            size = new Dimension(6, 6);
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                boolebn verticbl = ((JSepbrbtor)c).getOrientbtion() == SwingConstbnts.VERTICAL;
                Pbrt pbrt = verticbl ? Pbrt.TP_SEPARATOR : Pbrt.TP_SEPARATORVERT;
                Skin skin = xp.getSkin(c, pbrt);
                size.width = skin.getWidth();
                size.height = skin.getHeight();
            }

            if (((JSepbrbtor)c).getOrientbtion() == SwingConstbnts.VERTICAL) {
                size.height = 0;
            } else {
                size.width = 0;
            }
        }
        return size;
    }

    public Dimension getMbximumSize(JComponent c) {
        Dimension pref = getPreferredSize(c);
        if (((JSepbrbtor)c).getOrientbtion() == SwingConstbnts.VERTICAL) {
            return new Dimension(pref.width, Short.MAX_VALUE);
        } else {
            return new Dimension(Short.MAX_VALUE, pref.height);
        }
    }

    public void pbint( Grbphics g, JComponent c ) {
        boolebn verticbl = ((JSepbrbtor)c).getOrientbtion() == SwingConstbnts.VERTICAL;
        Dimension size = c.getSize();

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            Pbrt pbrt = verticbl ? Pbrt.TP_SEPARATOR : Pbrt.TP_SEPARATORVERT;
            Skin skin = xp.getSkin(c, pbrt);

            int dx = verticbl ? (size.width - skin.getWidth()) / 2 : 0;
            int dy = verticbl ? 0 : (size.height - skin.getHeight()) / 2;
            int dw = verticbl ? skin.getWidth() : size.width;
            int dh = verticbl ? size.height : skin.getHeight();
            skin.pbintSkin(g, dx, dy, dw, dh, null);
        } else {

        Color temp = g.getColor();

        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();

        Color shbdow = tbble.getColor("ToolBbr.shbdow");
        Color highlight = tbble.getColor("ToolBbr.highlight");

        if (verticbl) {
            int x = (size.width / 2) - 1;
            g.setColor(shbdow);
            g.drbwLine(x, 2, x, size.height - 2);

            g.setColor(highlight);
            g.drbwLine(x + 1, 2, x + 1, size.height - 2);
        } else {
            int y = (size.height / 2) - 1;
            g.setColor(shbdow);
            g.drbwLine(2, y, size.width - 2, y);
            g.setColor(highlight);
            g.drbwLine(2, y + 1, size.width - 2, y + 1);
        }
        g.setColor(temp);
        }
    }
}
