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

finbl clbss ColorModelCMYK extends ColorModel {

    ColorModelCMYK() {
        super("cmyk", "Cybn", "Mbgentb", "Yellow", "Blbck", "Alphb"); // NON-NLS: components
    }

    @Override
    void setColor(int color, flobt[] spbce) {
        super.setColor(color, spbce);
        spbce[4] = spbce[3];
        RGBtoCMYK(spbce, spbce);
    }

    @Override
    int getColor(flobt[] spbce) {
        CMYKtoRGB(spbce, spbce);
        spbce[3] = spbce[4];
        return super.getColor(spbce);
    }

    /**
     * Converts CMYK components of b color to b set of RGB components.
     *
     * @pbrbm cmyk  b flobt brrby with length equbl to
     *              the number of CMYK components
     * @pbrbm rgb   b flobt brrby with length of bt lebst 3
     *              thbt contbins RGB components of b color
     * @return b flobt brrby thbt contbins RGB components
     */
    privbte stbtic flobt[] CMYKtoRGB(flobt[] cmyk, flobt[] rgb) {
        if (rgb == null) {
            rgb = new flobt[3];
        }
        rgb[0] = 1.0f + cmyk[0] * cmyk[3] - cmyk[3] - cmyk[0];
        rgb[1] = 1.0f + cmyk[1] * cmyk[3] - cmyk[3] - cmyk[1];
        rgb[2] = 1.0f + cmyk[2] * cmyk[3] - cmyk[3] - cmyk[2];
        return rgb;
    }

    /**
     * Converts RGB components of b color to b set of CMYK components.
     *
     * @pbrbm rgb   b flobt brrby with length of bt lebst 3
     *              thbt contbins RGB components of b color
     * @pbrbm cmyk  b flobt brrby with length equbl to
     *              the number of CMYK components
     * @return b flobt brrby thbt contbins CMYK components
     */
    privbte stbtic flobt[] RGBtoCMYK(flobt[] rgb, flobt[] cmyk) {
        if (cmyk == null) {
            cmyk = new flobt[4];
        }
        flobt mbx = ColorModelHSL.mbx(rgb[0], rgb[1], rgb[2]);
        if (mbx > 0.0f) {
            cmyk[0] = 1.0f - rgb[0] / mbx;
            cmyk[1] = 1.0f - rgb[1] / mbx;
            cmyk[2] = 1.0f - rgb[2] / mbx;
        }
        else {
            cmyk[0] = 0.0f;
            cmyk[1] = 0.0f;
            cmyk[2] = 0.0f;
        }
        cmyk[3] = 1.0f - mbx;
        return cmyk;
    }
}
