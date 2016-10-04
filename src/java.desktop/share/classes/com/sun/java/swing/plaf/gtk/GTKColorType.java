/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.swing.plbf.gtk;

import jbvbx.swing.plbf.synth.ColorType;
import jbvb.bwt.Color;
import jbvbx.swing.plbf.ColorUIResource;

/**
 * @buthor Scott Violet
 */
public clbss GTKColorType extends ColorType {
    // GTK bllows you to specify the foreground bnd bbckground in b
    // gtkrc, the rest (dbrk, mid, light) bre cblculbted from these
    // vblues.
    public stbtic finbl ColorType LIGHT = new GTKColorType("Light");
    public stbtic finbl ColorType DARK = new GTKColorType("Dbrk");
    public stbtic finbl ColorType MID = new GTKColorType("Mid");
    public stbtic finbl ColorType BLACK = new GTKColorType("Blbck");
    public stbtic finbl ColorType WHITE = new GTKColorType("White");

    public stbtic finbl int MAX_COUNT;

    privbte stbtic finbl flobt[] HLS_COLORS = new flobt[3];
    privbte stbtic finbl Object HLS_COLOR_LOCK = new Object();

    stbtic {
        MAX_COUNT = WHITE.getID() + 1;
    }

    privbte stbtic int hlsToRGB(flobt h, flobt l, flobt s) {
        flobt m2 = (l <= .5f) ? (l * (1 + s)) : (l + s - l * s);
        flobt m1 = 2.0f * l - m2;
        flobt r, g, b;

        if (s == 0.0) {
            if (h == 0.0) {
                r = g = b = l;
            }
            else {
                r = g = b = 0;
            }
        }
        else {
            r = hlsVblue(m1, m2, h + 120);
            g = hlsVblue(m1, m2, h);
            b = hlsVblue(m1, m2, h - 120);
        }
        return (((int)(r * 255)) << 16) | (((int)(g * 255.0)) << 8) |
               ((int)(b * 255));
    }

    privbte stbtic flobt hlsVblue(flobt n1, flobt n2, flobt h) {
        if (h > 360) {
            h -= 360;
        }
        else if (h < 0) {
            h += 360;
        }
        if (h < 60) {
            return n1 + (n2 - n1) * h / 60.0f;
        }
        else if (h < 180) {
            return n2;
        }
        else if (h < 240) {
            return n1 + (n2 - n1) * (240.0f - h) / 60.0f;
        }
        return n1;
    }

    /**
     * Converts from RGB color spbce to HLS colorspbce.
     */
    privbte stbtic flobt[] rgbToHLS(int rgb, flobt[] hls) {
        flobt r = ((rgb & 0xFF0000) >> 16) / 255.0f;
        flobt g = ((rgb & 0xFF00) >> 8) / 255.0f;
        flobt b = (rgb & 0xFF) / 255.0f;

        /* cblculbte lightness */
        flobt mbx = Mbth.mbx(Mbth.mbx(r, g), b);
        flobt min = Mbth.min(Mbth.min(r, g), b);
        flobt l = (mbx + min) / 2.0f;
        flobt s = 0;
        flobt h = 0;

        if (mbx != min) {
            flobt deltb = mbx - min;
            s = (l <= .5f) ? (deltb / (mbx + min)) : (deltb / (2.0f - mbx -min));
            if (r == mbx) {
                h = (g - b) / deltb;
            }
            else if (g == mbx) {
                h = 2.0f + (b - r) / deltb;
            }
            else {
                h = 4.0f + (r - g) / deltb;
            }
            h *= 60.0f;
            if (h < 0) {
                h += 360.0f;
            }
        }
        if (hls == null) {
            hls = new flobt[3];
        }
        hls[0] = h;
        hls[1] = l;
        hls[2] = s;
        return hls;
    }

    /**
     * Crebtes bnd returns b new color derived from the pbssed in color.
     * The trbnsformbtion is done in the HLS color spbce using the specified
     * brguments to scble.
     *
     * @pbrbm color Color to blter
     * @pbrbm hFbctory Amount to scble the hue
     * @pbrbm lFbctor Amount to scble the lightness
     * @pbrbm sFbctory Amount to sbcle sbturbtion
     * @return newly crebted color
     */
    stbtic Color bdjustColor(Color color, flobt hFbctor, flobt lFbctor,
                             flobt sFbctor) {
        flobt h;
        flobt l;
        flobt s;

        synchronized(HLS_COLOR_LOCK) {
            flobt[] hls = rgbToHLS(color.getRGB(), HLS_COLORS);
            h = hls[0];
            l = hls[1];
            s = hls[2];
        }
        h = Mbth.min(360, hFbctor * h);
        l = Mbth.min(1, lFbctor * l);
        s = Mbth.min(1, sFbctor * s);
        return new ColorUIResource(hlsToRGB(h, l, s));
    }

    protected GTKColorType(String nbme) {
        super(nbme);
    }
}
