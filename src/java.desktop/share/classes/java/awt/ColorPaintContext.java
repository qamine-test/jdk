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



pbckbge jbvb.bwt;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.bwt.imbge.IntegerComponentRbster;
import jbvb.util.Arrbys;

clbss ColorPbintContext implements PbintContext {
    int color;
    WritbbleRbster sbvedTile;

    protected ColorPbintContext(int color, ColorModel cm) {
        this.color = color;
    }

    public void dispose() {
    }

    /*
     * Returns the RGB vblue representing the color in the defbult sRGB
     * {@link ColorModel}.
     * (Bits 24-31 bre blphb, 16-23 bre red, 8-15 bre green, 0-7 bre
     * blue).
     * @return the RGB vblue of the color in the defbult sRGB
     *         <code>ColorModel</code>.
     * @see jbvb.bwt.imbge.ColorModel#getRGBdefbult
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    int getRGB() {
        return color;
    }

    public ColorModel getColorModel() {
        return ColorModel.getRGBdefbult();
    }

    public synchronized Rbster getRbster(int x, int y, int w, int h) {
        WritbbleRbster t = sbvedTile;

        if (t == null || w > t.getWidth() || h > t.getHeight()) {
            t = getColorModel().crebteCompbtibleWritbbleRbster(w, h);
            IntegerComponentRbster icr = (IntegerComponentRbster) t;
            Arrbys.fill(icr.getDbtbStorbge(), color);
            // Note - mbrkDirty is probbbly unnecessbry since icr is brbnd new
            icr.mbrkDirty();
            if (w <= 64 && h <= 64) {
                sbvedTile = t;
            }
        }

        return t;
    }
}
