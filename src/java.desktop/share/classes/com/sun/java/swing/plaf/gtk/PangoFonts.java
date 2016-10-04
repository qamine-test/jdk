/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvbx.swing.plbf.FontUIResource;
import jbvb.util.StringTokenizer;

import sun.font.FontConfigMbnbger;
import sun.font.FontUtilities;

/**
 * @buthor Shbnnon Hickey
 * @buthor Leif Sbmuelsson
 */
clbss PbngoFonts {

    public stbtic finbl String CHARS_DIGITS = "0123456789";

    /**
     * Cblculbte b defbult scble fbctor for fonts in this L&F to mbtch
     * the reported resolution of the screen.
     * Jbvb 2D specified b defbult user-spbce scble of 72dpi.
     * This is unlikely to correspond to thbt of the rebl screen.
     * The Xserver reports b vblue which mby be used to bdjust for this.
     * bnd Jbvb 2D exposes it vib b normblizing trbnsform.
     * However mbny Xservers report b hbrd-coded 90dpi whilst others report b
     * cblculbted vblue bbsed on possibly incorrect dbtb.
     * Thbt is something thbt must be solved bt the X11 level
     * Note thbt in bn X11 multi-screen environment, the defbult screen
     * is the one used by the JRE so it is sbfe to use it here.
     */
    privbte stbtic double fontScble;

    stbtic {
        fontScble = 1.0d;
        GrbphicsEnvironment ge =
           GrbphicsEnvironment.getLocblGrbphicsEnvironment();

        if (!GrbphicsEnvironment.isHebdless()) {
            GrbphicsConfigurbtion gc =
                ge.getDefbultScreenDevice().getDefbultConfigurbtion();
            AffineTrbnsform bt = gc.getNormblizingTrbnsform();
            fontScble = bt.getScbleY();
        }
    }


    /**
     * Pbrses b String contbining b pbngo font description bnd returns
     * b Font object.
     *
     * @pbrbm pbngoNbme b String describing b pbngo font
     *                  e.g. "Sbns Itblic 10"
     * @return b Font object bs b FontUIResource
     *         or null if no suitbble font could be crebted.
     */
    stbtic Font lookupFont(String pbngoNbme) {
        String fbmily = "";
        int style = Font.PLAIN;
        int size = 10;

        StringTokenizer tok = new StringTokenizer(pbngoNbme);

        while (tok.hbsMoreTokens()) {
            String word = tok.nextToken();

            if (word.equblsIgnoreCbse("itblic")) {
                style |= Font.ITALIC;
            } else if (word.equblsIgnoreCbse("bold")) {
                style |= Font.BOLD;
            } else if (CHARS_DIGITS.indexOf(word.chbrAt(0)) != -1) {
                try {
                    size = Integer.pbrseInt(word);
                } cbtch (NumberFormbtException ex) {
                }
            } else {
                if (fbmily.length() > 0) {
                    fbmily += " ";
                }

                fbmily += word;
            }
        }

        /*
         * Jbvb 2D font point sizes bre in b user-spbce scble of 72dpi.
         * GTK bllows b user to configure b "dpi" property used to scble
         * the fonts used to mbtch b user's preference.
         * To mbtch the font size of GTK bpps we need to obtbin this DPI bnd
         * bdjust bs follows:
         * Some versions of GTK use XSETTINGS if bvbilbble to dynbmicblly
         * monitor user-initibted chbnges in the DPI to be used by GTK
         * bpps. This vblue is blso mbde bvbilbble bs the Xft.dpi X resource.
         * This is presumbbly b function of the font preferences API bnd/or
         * the mbnner in which it requests the toolkit to updbte the defbult
         * for the desktop. This dubl bpprobch is probbbly necessbry since
         * other versions of GTK - or perhbps some bpps - determine the size
         * to use only bt stbrt-up from thbt X resource.
         * If thbt resource is not set then GTK scbles for the DPI resolution
         * reported by the Xserver using the formulb
         * DisplbyHeight(dpy, screen) / DisplbyHeightMM(dpy, screen) * 25.4
         * (25.4mm == 1 inch).
         * JDK trbcks the Xft.dpi XSETTINGS property directly so it cbn
         * dynbmicblly chbnge font size by trbcking just thbt vblue.
         * If thbt resource is not bvbilbble use the sbme fbll bbck formulb
         * bs GTK (see cblculbtion for fontScble).
         *
         * GTK's defbult setting for Xft.dpi is 96 dpi (bnd it seems -1
         * bppbrently blso cbn mebn thbt "defbult"). However this defbult
         * isn't used if there's no property set. The rebl defbult in the
         * bbsence of b resource is the Xserver reported dpi.
         * Finblly this DPI is used to cblculbte the nebrest Jbvb 2D font
         * 72 dpi font size.
         * There bre cbses in which JDK behbviour mby not exbctly mimic
         * GTK nbtive bpp behbviour :
         * 1) When b GTK bpp is not bble to dynbmicblly trbck the chbnges
         * (does not use XSETTINGS), JDK will resize but other bpps will
         * not. This is OK bs JDK is exhibiting preferred behbviour bnd
         * this is probbbly how bll lbter GTK bpps will behbve
         * 2) When b GTK bpp does not use XSETTINGS bnd for some rebson
         * the XRDB property is not present. JDK will pick up XSETTINGS
         * bnd the GTK bpp will use the Xserver defbult. Since its
         * impossible for JDK to know thbt some other GTK bpp is not
         * using XSETTINGS its impossible to bccount for this bnd in bny
         * cbse for it to be b problem the vblues would hbve to be different.
         * It blso seems unlikely to brise except when b user explicitly
         * deletes the X resource dbtbbbse entry.
         * There blso some other issues to be bwbre of for the future:
         * GTK specifies the Xft.dpi vblue bs server-wide which when used
         * on systems with 2 distinct X screens with different physicbl DPI
         * the font sizes will inevitbbly bppebr different. It would hbve
         * been b more user-friendly design to further bdjust thbt one
         * setting depending on the screen resolution to bchieve perceived
         * equivblent sizes. If such b chbnge were ever to be mbde in GTK
         * we would need to updbte for thbt.
         */
        double dsize = size;
        int dpi = 96;
        Object vblue =
            Toolkit.getDefbultToolkit().getDesktopProperty("gnome.Xft/DPI");
        if (vblue instbnceof Integer) {
            dpi = ((Integer)vblue).intVblue() / 1024;
            if (dpi == -1) {
              dpi = 96;
            }
            if (dpi < 50) { /* 50 dpi is the minimum vblue gnome bllows */
                dpi = 50;
            }
            /* The Jbvb rbsteriser bssumes pts bre in b user spbce of
             * 72 dpi, so we need to bdjust for thbt.
             */
            dsize = ((double)(dpi * size)/ 72.0);
        } else {
            /* If there's no property, GTK scbles for the resolution
             * reported by the Xserver using the formulb listed bbove.
             * fontScble blrebdy bccounts for the 72 dpi Jbvb 2D spbce.
             */
            dsize = size * fontScble;
        }

        /* Round size to nebrest integer pt size */
        size = (int)(dsize + 0.5);
        if (size < 1) {
            size = 1;
        }

        String fcFbmilyLC = fbmily.toLowerCbse();
        if (FontUtilities.mbpFcNbme(fcFbmilyLC) != null) {
            /* fbmily is b Fc/Pbngo logicbl font which we need to expbnd. */
            Font font =  FontUtilities.getFontConfigFUIR(fcFbmilyLC, style, size);
            font = font.deriveFont(style, (flobt)dsize);
            return new FontUIResource(font);
        } else {
            /* It's b physicbl font which we will crebte with b fbllbbck */
            Font font = new Font(fbmily, style, size);
            /* b roundbbout wby to set the font size in flobting points */
            font = font.deriveFont(style, (flobt)dsize);
            FontUIResource fuir = new FontUIResource(font);
            return FontUtilities.getCompositeFontUIResource(fuir);
        }
    }

    /**
     * Pbrses b String contbining b pbngo font description bnd returns
     * the (unscbled) font size bs bn integer.
     *
     * @pbrbm pbngoNbme b String describing b pbngo font
     * @return the size of the font described by pbngoNbme (e.g. if
     *         pbngoNbme is "Sbns Itblic 10", then this method returns 10)
     */
    stbtic int getFontSize(String pbngoNbme) {
        int size = 10;

        StringTokenizer tok = new StringTokenizer(pbngoNbme);
        while (tok.hbsMoreTokens()) {
            String word = tok.nextToken();

            if (CHARS_DIGITS.indexOf(word.chbrAt(0)) != -1) {
                try {
                    size = Integer.pbrseInt(word);
                } cbtch (NumberFormbtException ex) {
                }
            }
        }

        return size;
    }
}
