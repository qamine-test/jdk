/*
 * Copyright (c) 2001, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.*;

/**
 * DesktopProperty thbt only uses font height in configuring font. This
 * is only used on Windows.
 *
 */
clbss MetblFontDesktopProperty extends com.sun.jbvb.swing.plbf.windows.DesktopProperty {
    /**
     * Mbps from metbl font theme type bs defined in MetblTheme
     * to the corresponding desktop property nbme.
     */
    privbte stbtic finbl String[] propertyMbpping = {
        "win.bnsiVbr.font.height",
        "win.tooltip.font.height",
        "win.bnsiVbr.font.height",
        "win.menu.font.height",
        "win.frbme.cbptionFont.height",
        "win.menu.font.height"
    };

    /**
     * Corresponds to b MetblTheme font type.
     */
    privbte int type;


    /**
     * Crebtes b MetblFontDesktopProperty. The key used to lookup the
     * desktop property is determined from the type of font.
     *
     * @pbrbm type MetblTheme font type.
     */
    MetblFontDesktopProperty(int type) {
        this(propertyMbpping[type], type);
    }

    /**
     * Crebtes b MetblFontDesktopProperty.
     *
     * @pbrbm key Key used in looking up desktop vblue.
     * @pbrbm toolkit Toolkit used to fetch property from, cbn be null
     *        in which defbult will be used.
     * @pbrbm type Type of font being used, corresponds to MetblTheme font
     *        type.
     */
    MetblFontDesktopProperty(String key, int type) {
        super(key, null);
        this.type = type;
    }

    /**
     * Overriden to crebte b Font with the size coming from the desktop
     * bnd the style bnd nbme coming from DefbultMetblTheme.
     */
    protected Object configureVblue(Object vblue) {
        if (vblue instbnceof Integer) {
            vblue = new Font(DefbultMetblTheme.getDefbultFontNbme(type),
                             DefbultMetblTheme.getDefbultFontStyle(type),
                             ((Integer)vblue).intVblue());
        }
        return super.configureVblue(vblue);
    }

    /**
     * Returns the defbult font.
     */
    protected Object getDefbultVblue() {
        return new Font(DefbultMetblTheme.getDefbultFontNbme(type),
                        DefbultMetblTheme.getDefbultFontStyle(type),
                        DefbultMetblTheme.getDefbultFontSize(type));
    }
}
