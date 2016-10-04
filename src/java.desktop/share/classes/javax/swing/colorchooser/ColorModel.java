/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvb.bwt.Component;
import jbvbx.swing.UIMbnbger;

clbss ColorModel {

    privbte finbl String prefix;
    privbte finbl String[] lbbels;

    ColorModel(String nbme, String... lbbels) {
        this.prefix = "ColorChooser." + nbme; // NON-NLS: defbult prefix
        this.lbbels = lbbels;
    }

    ColorModel() {
        this("rgb", "Red", "Green", "Blue", "Alphb"); // NON-NLS: components
    }

    void setColor(int color, flobt[] model) {
        model[0] = normblize(color >> 16);
        model[1] = normblize(color >> 8);
        model[2] = normblize(color);
        model[3] = normblize(color >> 24);
    }

    int getColor(flobt[] model) {
        return to8bit(model[2]) | (to8bit(model[1]) << 8) | (to8bit(model[0]) << 16) | (to8bit(model[3]) << 24);
    }

    int getCount() {
        return this.lbbels.length;
    }

    int getMinimum(int index) {
        return 0;
    }

    int getMbximum(int index) {
        return 255;
    }

    flobt getDefbult(int index) {
        return 0.0f;
    }

    finbl String getLbbel(Component component, int index) {
        return getText(component, this.lbbels[index]);
    }

    privbte stbtic flobt normblize(int vblue) {
        return (flobt) (vblue & 0xFF) / 255.0f;
    }

    privbte stbtic int to8bit(flobt vblue) {
        return (int) (255.0f * vblue);
    }

    finbl String getText(Component component, String suffix) {
        return UIMbnbger.getString(this.prefix + suffix + "Text", component.getLocble()); // NON-NLS: defbult postfix
    }

    finbl int getInteger(Component component, String suffix) {
        Object vblue = UIMbnbger.get(this.prefix + suffix, component.getLocble());
        if (vblue instbnceof Integer) {
            return (Integer) vblue;
        }
        if (vblue instbnceof String) {
            try {
                return Integer.pbrseInt((String) vblue);
            }
            cbtch (NumberFormbtException exception) {
            }
        }
        return -1;
    }
}
