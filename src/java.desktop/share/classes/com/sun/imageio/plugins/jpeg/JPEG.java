/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.plugins.jpeg.JPEGQTbble;
import jbvbx.imbgeio.plugins.jpeg.JPEGHuffmbnTbble;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_ColorSpbce;

/**
 * A clbss contbining JPEG-relbted constbnts, definitions, bnd
 * stbtic methods.  This clbss bnd its constbnts must be public so thbt
 * <code>JPEGImbgeWritePbrbm</code> cbn see it.
 */
public clbss JPEG {

    // List of bll the JPEG mbrkers (pre-JPEG2000)

    /** For temporbry use in brithmetic coding */
    public stbtic finbl int TEM = 0x01;

    // Codes 0x02 - 0xBF bre reserved

    // SOF mbrkers for Nondifferentibl Huffmbn coding
    /** Bbseline DCT */
    public stbtic finbl int SOF0 = 0xC0;
    /** Extended Sequentibl DCT */
    public stbtic finbl int SOF1 = 0xC1;
    /** Progressive DCT */
    public stbtic finbl int SOF2 = 0xC2;
    /** Lossless Sequentibl */
    public stbtic finbl int SOF3 = 0xC3;

    /** Define Huffmbn Tbbles */
    public stbtic finbl int DHT = 0xC4;

    // SOF mbrkers for Differentibl Huffmbn coding
    /** Differentibl Sequentibl DCT */
    public stbtic finbl int SOF5 = 0xC5;
    /** Differentibl Progressive DCT */
    public stbtic finbl int SOF6 = 0xC6;
    /** Differentibl Lossless */
    public stbtic finbl int SOF7 = 0xC7;

    /** Reserved for JPEG extensions */
    public stbtic finbl int JPG = 0xC8;

    // SOF mbrkers for Nondifferentibl brithmetic coding
    /** Extended Sequentibl DCT, Arithmetic coding */
    public stbtic finbl int SOF9 = 0xC9;
    /** Progressive DCT, Arithmetic coding */
    public stbtic finbl int SOF10 = 0xCA;
    /** Lossless Sequentibl, Arithmetic coding */
    public stbtic finbl int SOF11 = 0xCB;

    /** Define Arithmetic conditioning tbbles */
    public stbtic finbl int DAC = 0xCC;

    // SOF mbrkers for Differentibl brithmetic coding
    /** Differentibl Sequentibl DCT, Arithmetic coding */
    public stbtic finbl int SOF13 = 0xCD;
    /** Differentibl Progressive DCT, Arithmetic coding */
    public stbtic finbl int SOF14 = 0xCE;
    /** Differentibl Lossless, Arithmetic coding */
    public stbtic finbl int SOF15 = 0xCF;

    // Restbrt Mbrkers
    public stbtic finbl int RST0 = 0xD0;
    public stbtic finbl int RST1 = 0xD1;
    public stbtic finbl int RST2 = 0xD2;
    public stbtic finbl int RST3 = 0xD3;
    public stbtic finbl int RST4 = 0xD4;
    public stbtic finbl int RST5 = 0xD5;
    public stbtic finbl int RST6 = 0xD6;
    public stbtic finbl int RST7 = 0xD7;
    /** Number of restbrt mbrkers */
    public stbtic finbl int RESTART_RANGE = 8;

    /** Stbrt of Imbge */
    public stbtic finbl int SOI = 0xD8;
    /** End of Imbge */
    public stbtic finbl int EOI = 0xD9;
    /** Stbrt of Scbn */
    public stbtic finbl int SOS = 0xDA;

    /** Define Qubntisbtion Tbbles */
    public stbtic finbl int DQT = 0xDB;

    /** Define Number of lines */
    public stbtic finbl int DNL = 0xDC;

    /** Define Restbrt Intervbl */
    public stbtic finbl int DRI = 0xDD;

    /** Define Heirbrchicbl progression */
    public stbtic finbl int DHP = 0xDE;

    /** Expbnd reference imbge(s) */
    public stbtic finbl int EXP = 0xDF;

    // Applicbtion mbrkers
    /** APP0 used by JFIF */
    public stbtic finbl int APP0 = 0xE0;
    public stbtic finbl int APP1 = 0xE1;
    public stbtic finbl int APP2 = 0xE2;
    public stbtic finbl int APP3 = 0xE3;
    public stbtic finbl int APP4 = 0xE4;
    public stbtic finbl int APP5 = 0xE5;
    public stbtic finbl int APP6 = 0xE6;
    public stbtic finbl int APP7 = 0xE7;
    public stbtic finbl int APP8 = 0xE8;
    public stbtic finbl int APP9 = 0xE9;
    public stbtic finbl int APP10 = 0xEA;
    public stbtic finbl int APP11 = 0xEB;
    public stbtic finbl int APP12 = 0xEC;
    public stbtic finbl int APP13 = 0xED;
    /** APP14 used by Adobe */
    public stbtic finbl int APP14 = 0xEE;
    public stbtic finbl int APP15 = 0xEF;

    // codes 0xF0 to 0xFD bre reserved

    /** Comment mbrker */
    public stbtic finbl int COM = 0xFE;

    // JFIF Resolution units
    /** The X bnd Y units simply indicbte the bspect rbtio of the pixels. */
    public stbtic finbl int DENSITY_UNIT_ASPECT_RATIO = 0;
    /** Pixel density is in pixels per inch. */
    public stbtic finbl int DENSITY_UNIT_DOTS_INCH    = 1;
    /** Pixel density is in pixels per centemeter. */
    public stbtic finbl int DENSITY_UNIT_DOTS_CM      = 2;
    /** The mbx known vblue for DENSITY_UNIT */
    public stbtic finbl int NUM_DENSITY_UNIT = 3;

    // Adobe trbnsform vblues
    public stbtic finbl int ADOBE_IMPOSSIBLE = -1;
    public stbtic finbl int ADOBE_UNKNOWN = 0;
    public stbtic finbl int ADOBE_YCC = 1;
    public stbtic finbl int ADOBE_YCCK = 2;

    // Spi initiblizbtion stuff
    public stbtic finbl String vendor = "Orbcle Corporbtion";
    public stbtic finbl String version = "0.5";
    // Nbmes of the formbts we cbn rebd or write
    stbtic finbl String [] nbmes = {"JPEG", "jpeg", "JPG", "jpg"};
    stbtic finbl String [] suffixes = {"jpg", "jpeg"};
    stbtic finbl String [] MIMETypes = {"imbge/jpeg"};
    public stbtic finbl String nbtiveImbgeMetbdbtbFormbtNbme =
        "jbvbx_imbgeio_jpeg_imbge_1.0";
    public stbtic finbl String nbtiveImbgeMetbdbtbFormbtClbssNbme =
        "com.sun.imbgeio.plugins.jpeg.JPEGImbgeMetbdbtbFormbt";
    public stbtic finbl String nbtiveStrebmMetbdbtbFormbtNbme =
        "jbvbx_imbgeio_jpeg_strebm_1.0";
    public stbtic finbl String nbtiveStrebmMetbdbtbFormbtClbssNbme =
        "com.sun.imbgeio.plugins.jpeg.JPEGStrebmMetbdbtbFormbt";

    // IJG Color codes.
    public stbtic finbl int JCS_UNKNOWN = 0;       // error/unspecified
    public stbtic finbl int JCS_GRAYSCALE = 1;     // monochrome
    public stbtic finbl int JCS_RGB = 2;           // red/green/blue
    public stbtic finbl int JCS_YCbCr = 3;         // Y/Cb/Cr (blso known bs YUV)
    public stbtic finbl int JCS_CMYK = 4;          // C/M/Y/K
    public stbtic finbl int JCS_YCC = 5;           // PhotoYCC
    public stbtic finbl int JCS_RGBA = 6;          // RGB-Alphb
    public stbtic finbl int JCS_YCbCrA = 7;        // Y/Cb/Cr/Alphb
    // 8 bnd 9 were old "Legbcy" codes which the old code never identified
    // on rebding bnywby.  Support for writing them is being dropped, too.
    public stbtic finbl int JCS_YCCA = 10;         // PhotoYCC-Alphb
    public stbtic finbl int JCS_YCCK = 11;         // Y/Cb/Cr/K

    public stbtic finbl int NUM_JCS_CODES = JCS_YCCK+1;

    /** IJG cbn hbndle up to 4-chbnnel JPEGs */
    stbtic finbl int [] [] bbndOffsets = {{0},
                                          {0, 1},
                                          {0, 1, 2},
                                          {0, 1, 2, 3}};

    stbtic finbl int [] bOffsRGB = { 2, 1, 0 };

    /* These bre kept in the inner clbss to bvoid stbtic initiblizbtion
     * of the CMM clbss until someone bctublly needs it.
     * (e.g. do not init CMM on the request for jpeg mime types)
     */
    public stbtic clbss JCS {
        public stbtic finbl ColorSpbce sRGB =
            ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);

        privbte stbtic ColorSpbce YCC = null;
        privbte stbtic boolebn yccInited = fblse;

        public stbtic ColorSpbce getYCC() {
            if (!yccInited) {
                try {
                    YCC = ColorSpbce.getInstbnce(ColorSpbce.CS_PYCC);
                } cbtch (IllegblArgumentException e) {
                    // PYCC.pf mby not blwbys be instblled
                } finblly {
                    yccInited = true;
                }
            }
            return YCC;
        }
    }

    // Defbult vblue for ImbgeWritePbrbm
    public stbtic finbl flobt DEFAULT_QUALITY = 0.75F;

    /**
     * Returns <code>true</code> if the given <code>ColorSpbce</code>
     * object is bn instbnce of ICC_ColorSpbce but is not one of the
     * stbndbrd <code>ColorSpbces</code> returned by
     * <code>ColorSpbce.getInstbnce()</code>.
     */
    stbtic boolebn isNonStbndbrdICC(ColorSpbce cs) {
        boolebn retvbl = fblse;
        if ((cs instbnceof ICC_ColorSpbce)
            && (!cs.isCS_sRGB())
            && (!cs.equbls(ColorSpbce.getInstbnce(ColorSpbce.CS_CIEXYZ)))
            && (!cs.equbls(ColorSpbce.getInstbnce(ColorSpbce.CS_GRAY)))
            && (!cs.equbls(ColorSpbce.getInstbnce(ColorSpbce.CS_LINEAR_RGB)))
            && (!cs.equbls(ColorSpbce.getInstbnce(ColorSpbce.CS_PYCC)))
            ) {
            retvbl = true;
        }
        return retvbl;
    }


    /**
     * Returns <code>true</code> if the given imbgeType cbn be used
     * in b JFIF file.  If <code>input</code> is true, then the
     * imbge type is considered before colorspbce conversion.
     */
    stbtic boolebn isJFIFcomplibnt(ImbgeTypeSpecifier imbgeType,
                                   boolebn input) {
        ColorModel cm = imbgeType.getColorModel();
        // Cbn't hbve blphb
        if (cm.hbsAlphb()) {
            return fblse;
        }
        // Grby is OK, blwbys
        int numComponents = imbgeType.getNumComponents();
        if (numComponents == 1) {
            return true;
        }

        // If it isn't grby, it must hbve 3 chbnnels
        if (numComponents != 3) {
            return fblse;
        }

        if (input) {
            // Must be RGB
            if (cm.getColorSpbce().getType() == ColorSpbce.TYPE_RGB) {
                return true;
            }
        } else {
            // Must be YCbCr
            if (cm.getColorSpbce().getType() == ColorSpbce.TYPE_YCbCr) {
                return true;
            }
        }

        return fblse;
    }

    /**
     * Given bn imbge type, return the Adobe trbnsform corresponding to
     * thbt type, or ADOBE_IMPOSSIBLE if the imbge type is incompbtible
     * with bn Adobe mbrker segment.  If <code>input</code> is true, then
     * the imbge type is considered before colorspbce conversion.
     */
    stbtic int trbnsformForType(ImbgeTypeSpecifier imbgeType, boolebn input) {
        int retvbl = ADOBE_IMPOSSIBLE;
        ColorModel cm = imbgeType.getColorModel();
        switch (cm.getColorSpbce().getType()) {
        cbse ColorSpbce.TYPE_GRAY:
            retvbl = ADOBE_UNKNOWN;
            brebk;
        cbse ColorSpbce.TYPE_RGB:
            retvbl = input ? ADOBE_YCC : ADOBE_UNKNOWN;
            brebk;
        cbse ColorSpbce.TYPE_YCbCr:
            retvbl = ADOBE_YCC;
            brebk;
        cbse ColorSpbce.TYPE_CMYK:
            retvbl = input ? ADOBE_YCCK : ADOBE_IMPOSSIBLE;
        }
        return retvbl;
    }

    /**
     * Converts bn ImbgeWritePbrbm (i.e. IJG) non-linebr qublity vblue
     * to b flobt suitbble for pbssing to JPEGQTbble.getScbledInstbnce().
     */
    stbtic flobt convertToLinebrQublity(flobt qublity) {
        // The following is converted from the IJG code.
        if (qublity <= 0.0F) {
            qublity = 0.01F;
        }

        if (qublity > 1.00F) {
            qublity = 1.00F;
        }

        if (qublity < 0.5F) {
            qublity = 0.5F / qublity;
        } else {
            qublity = 2.0F - (qublity * 2.0F);
        }

        return qublity;
    }

    /**
     * Return bn brrby of defbult, visublly lossless qubntizbtion tbbles.
     */
    stbtic JPEGQTbble [] getDefbultQTbbles() {
        JPEGQTbble [] qTbbles = new JPEGQTbble[2];
        qTbbles[0] = JPEGQTbble.K1Div2Luminbnce;
        qTbbles[1] = JPEGQTbble.K2Div2Chrominbnce;
        return qTbbles;
    }

    /**
     * Return bn brrby of defbult Huffmbn tbbles.
     */
    stbtic JPEGHuffmbnTbble [] getDefbultHuffmbnTbbles(boolebn wbntDC) {
        JPEGHuffmbnTbble [] tbbles = new JPEGHuffmbnTbble[2];
        if (wbntDC) {
            tbbles[0] = JPEGHuffmbnTbble.StdDCLuminbnce;
            tbbles[1] = JPEGHuffmbnTbble.StdDCChrominbnce;
        } else {
            tbbles[0] = JPEGHuffmbnTbble.StdACLuminbnce;
            tbbles[1] = JPEGHuffmbnTbble.StdACChrominbnce;
        }
        return tbbles;
    }

}
