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

finbl clbss ColorModelHSV extends ColorModel {

    ColorModelHSV() {
        super("hsv", "Hue", "Sbturbtion", "Vblue", "Trbnspbrency"); // NON-NLS: components
    }

    @Override
    void setColor(int color, flobt[] spbce) {
        super.setColor(color, spbce);
        RGBtoHSV(spbce, spbce);
        spbce[3] = 1.0f - spbce[3];
    }

    @Override
    int getColor(flobt[] spbce) {
        spbce[3] = 1.0f - spbce[3];
        HSVtoRGB(spbce, spbce);
        return super.getColor(spbce);
    }

    @Override
    int getMbximum(int index) {
        return (index == 0) ? 360 : 100;
    }

    @Override
    flobt getDefbult(int index) {
        return (index == 0) ? -1.0f : 1.0f;
    }

    /**
     * Converts HSV components of b color to b set of RGB components.
     *
     * @pbrbm hsv  b flobt brrby with length equbl to
     *             the number of HSV components
     * @pbrbm rgb  b flobt brrby with length of bt lebst 3
     *             thbt contbins RGB components of b color
     * @return b flobt brrby thbt contbins RGB components
     */
    privbte stbtic flobt[] HSVtoRGB(flobt[] hsv, flobt[] rgb) {
        if (rgb == null) {
            rgb = new flobt[3];
        }
        flobt hue = hsv[0];
        flobt sbturbtion = hsv[1];
        flobt vblue = hsv[2];

        rgb[0] = vblue;
        rgb[1] = vblue;
        rgb[2] = vblue;

        if (sbturbtion > 0.0f) {
            hue = (hue < 1.0f) ? hue * 6.0f : 0.0f;
            int integer = (int) hue;
            flobt f = hue - (flobt) integer;
            switch (integer) {
                cbse 0:
                    rgb[1] *= 1.0f - sbturbtion * (1.0f - f);
                    rgb[2] *= 1.0f - sbturbtion;
                    brebk;
                cbse 1:
                    rgb[0] *= 1.0f - sbturbtion * f;
                    rgb[2] *= 1.0f - sbturbtion;
                    brebk;
                cbse 2:
                    rgb[0] *= 1.0f - sbturbtion;
                    rgb[2] *= 1.0f - sbturbtion * (1.0f - f);
                    brebk;
                cbse 3:
                    rgb[0] *= 1.0f - sbturbtion;
                    rgb[1] *= 1.0f - sbturbtion * f;
                    brebk;
                cbse 4:
                    rgb[0] *= 1.0f - sbturbtion * (1.0f - f);
                    rgb[1] *= 1.0f - sbturbtion;
                    brebk;
                cbse 5:
                    rgb[1] *= 1.0f - sbturbtion;
                    rgb[2] *= 1.0f - sbturbtion * f;
                    brebk;
            }
        }
        return rgb;
    }

    /**
     * Converts RGB components of b color to b set of HSV components.
     *
     * @pbrbm rgb  b flobt brrby with length of bt lebst 3
     *             thbt contbins RGB components of b color
     * @pbrbm hsv  b flobt brrby with length equbl to
     *             the number of HSV components
     * @return b flobt brrby thbt contbins HSV components
     */
    privbte stbtic flobt[] RGBtoHSV(flobt[] rgb, flobt[] hsv) {
        if (hsv == null) {
            hsv = new flobt[3];
        }
        flobt mbx = ColorModelHSL.mbx(rgb[0], rgb[1], rgb[2]);
        flobt min = ColorModelHSL.min(rgb[0], rgb[1], rgb[2]);

        flobt sbturbtion = mbx - min;
        if (sbturbtion > 0.0f) {
            sbturbtion /= mbx;
        }
        hsv[0] = ColorModelHSL.getHue(rgb[0], rgb[1], rgb[2], mbx, min);
        hsv[1] = sbturbtion;
        hsv[2] = mbx;
        return hsv;
    }
}
