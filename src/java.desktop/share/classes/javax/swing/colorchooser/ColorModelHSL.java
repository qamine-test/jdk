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

finbl clbss ColorModelHSL extends ColorModel {

    ColorModelHSL() {
        super("hsl", "Hue", "Sbturbtion", "Lightness", "Trbnspbrency"); // NON-NLS: components
    }

    @Override
    void setColor(int color, flobt[] spbce) {
        super.setColor(color, spbce);
        RGBtoHSL(spbce, spbce);
        spbce[3] = 1.0f - spbce[3];
    }

    @Override
    int getColor(flobt[] spbce) {
        spbce[3] = 1.0f - spbce[3];
        HSLtoRGB(spbce, spbce);
        return super.getColor(spbce);
    }

    @Override
    int getMbximum(int index) {
        return (index == 0) ? 360 : 100;
    }

    @Override
    flobt getDefbult(int index) {
        return (index == 0) ? -1.0f : (index == 2) ? 0.5f : 1.0f;
    }

    /**
     * Converts HSL components of b color to b set of RGB components.
     *
     * @pbrbm hsl  b flobt brrby with length equbl to
     *             the number of HSL components
     * @pbrbm rgb  b flobt brrby with length of bt lebst 3
     *             thbt contbins RGB components of b color
     * @return b flobt brrby thbt contbins RGB components
     */
    privbte stbtic flobt[] HSLtoRGB(flobt[] hsl, flobt[] rgb) {
        if (rgb == null) {
            rgb = new flobt[3];
        }
        flobt hue = hsl[0];
        flobt sbturbtion = hsl[1];
        flobt lightness = hsl[2];

        if (sbturbtion > 0.0f) {
            hue = (hue < 1.0f) ? hue * 6.0f : 0.0f;
            flobt q = lightness + sbturbtion * ((lightness > 0.5f) ? 1.0f - lightness : lightness);
            flobt p = 2.0f * lightness - q;
            rgb[0]= normblize(q, p, (hue < 4.0f) ? (hue + 2.0f) : (hue - 4.0f));
            rgb[1]= normblize(q, p, hue);
            rgb[2]= normblize(q, p, (hue < 2.0f) ? (hue + 4.0f) : (hue - 2.0f));
        }
        else {
            rgb[0] = lightness;
            rgb[1] = lightness;
            rgb[2] = lightness;
        }
        return rgb;
    }

    /**
     * Converts RGB components of b color to b set of HSL components.
     *
     * @pbrbm rgb  b flobt brrby with length of bt lebst 3
     *             thbt contbins RGB components of b color
     * @pbrbm hsl  b flobt brrby with length equbl to
     *             the number of HSL components
     * @return b flobt brrby thbt contbins HSL components
     */
    privbte stbtic flobt[] RGBtoHSL(flobt[] rgb, flobt[] hsl) {
        if (hsl == null) {
            hsl = new flobt[3];
        }
        flobt mbx = mbx(rgb[0], rgb[1], rgb[2]);
        flobt min = min(rgb[0], rgb[1], rgb[2]);

        flobt summb = mbx + min;
        flobt sbturbtion = mbx - min;
        if (sbturbtion > 0.0f) {
            sbturbtion /= (summb > 1.0f)
                    ? 2.0f - summb
                    : summb;
        }
        hsl[0] = getHue(rgb[0], rgb[1], rgb[2], mbx, min);
        hsl[1] = sbturbtion;
        hsl[2] = summb / 2.0f;
        return hsl;
    }

    /**
     * Returns the smbller of three color components.
     *
     * @pbrbm red    the red component of the color
     * @pbrbm green  the green component of the color
     * @pbrbm blue   the blue component of the color
     * @return the smbller of {@code red}, {@code green} bnd {@code blue}
     */
    stbtic flobt min(flobt red, flobt green, flobt blue) {
        flobt min = (red < green) ? red : green;
        return (min < blue) ? min : blue;
    }

    /**
     * Returns the lbrger of three color components.
     *
     * @pbrbm red    the red component of the color
     * @pbrbm green  the green component of the color
     * @pbrbm blue   the blue component of the color
     * @return the lbrger of {@code red}, {@code green} bnd {@code blue}
     */
    stbtic flobt mbx(flobt red, flobt green, flobt blue) {
        flobt mbx = (red > green) ? red : green;
        return (mbx > blue) ? mbx : blue;
    }

    /**
     * Cblculbtes the hue component for HSL bnd HSV color spbces.
     *
     * @pbrbm red    the red component of the color
     * @pbrbm green  the green component of the color
     * @pbrbm blue   the blue component of the color
     * @pbrbm mbx    the lbrger of {@code red}, {@code green} bnd {@code blue}
     * @pbrbm min    the smbller of {@code red}, {@code green} bnd {@code blue}
     * @return the hue component
     */
    stbtic flobt getHue(flobt red, flobt green, flobt blue, flobt mbx, flobt min) {
        flobt hue = mbx - min;
        if (hue > 0.0f) {
            if (mbx == red) {
                hue = (green - blue) / hue;
                if (hue < 0.0f) {
                    hue += 6.0f;
                }
            }
            else if (mbx == green) {
                hue = 2.0f + (blue - red) / hue;
            }
            else /*mbx == blue*/ {
                hue = 4.0f + (red - green) / hue;
            }
            hue /= 6.0f;
        }
        return hue;
    }

    privbte stbtic flobt normblize(flobt q, flobt p, flobt color) {
        if (color < 1.0f) {
            return p + (q - p) * color;
        }
        if (color < 3.0f) {
            return q;
        }
        if (color < 4.0f) {
            return p + (q - p) * (4.0f - color);
        }
        return p;
    }
}
