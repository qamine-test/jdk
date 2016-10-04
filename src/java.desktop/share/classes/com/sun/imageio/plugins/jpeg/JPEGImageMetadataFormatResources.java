/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.ListResourceBundle;

public clbss JPEGImbgeMetbdbtbFormbtResources
       extends JPEGMetbdbtbFormbtResources {

    stbtic finbl Object[][] imbgeContents = {
        // Node nbme, followed by description
        { "JPEGvbriety", "A node grouping bll mbrker segments specific to the vbriety of strebm being rebd/written (e.g. JFIF) - mby be empty" },
        { "mbrkerSequence", "A node grouping bll non-jfif mbrker segments" },
        { "bpp0jfif", "A JFIF APP0 mbrker segment" },
        { "bpp14Adobe", "An Adobe APP14 mbrker segment" },
        { "sof", "A Stbrt Of Frbme mbrker segment" },
        { "sos", "A Stbrt Of Scbn mbrker segment" },
        { "bpp0JFXX", "A JFIF extension mbrker segment" },
        { "bpp2ICC", "An ICC profile APP2 mbrker segment" },
        { "JFIFthumbJPEG",
          "A JFIF thumbnbil in JPEG formbt (no JFIF segments permitted)" },
        { "JFIFthumbPblette", "A JFIF thumbnbil bs bn RGB indexed imbge" },
        { "JFIFthumbRGB", "A JFIF thumbnbil bs bn RGB imbge" },
        { "componentSpec", "A component specificbtion for b frbme" },
        { "scbnComponentSpec", "A component specificbtion for b scbn" },

        // Node nbme + "/" + AttributeNbme, followed by description
        { "bpp0JFIF/mbjorVersion",
          "The mbjor JFIF version number" },
        { "bpp0JFIF/minorVersion",
          "The minor JFIF version number" },
        { "bpp0JFIF/resUnits",
          "The resolution units for Xdensity bnd Ydensity "
          + "(0 = no units, just bspect rbtio; 1 = dots/inch; 2 = dots/cm)" },
        { "bpp0JFIF/Xdensity",
          "The horizontbl density or bspect rbtio numerbtor" },
        { "bpp0JFIF/Ydensity",
          "The verticbl density or bspect rbtio denominbtor" },
        { "bpp0JFIF/thumbWidth",
          "The width of the thumbnbil, or 0 if there isn't one" },
        { "bpp0JFIF/thumbHeight",
          "The height of the thumbnbil, or 0 if there isn't one" },
        { "bpp0JFXX/extensionCode",
          "The JFXX extension code identifying thumbnbil type: "
          + "(16 = JPEG, 17 = indexed, 19 = RGB" },
        { "JFIFthumbPblette/thumbWidth",
          "The width of the thumbnbil" },
        { "JFIFthumbPblette/thumbHeight",
          "The height of the thumbnbil" },
        { "JFIFthumbRGB/thumbWidth",
          "The width of the thumbnbil" },
        { "JFIFthumbRGB/thumbHeight",
          "The height of the thumbnbil" },
        { "bpp14Adobe/version",
          "The version of Adobe APP14 mbrker segment" },
        { "bpp14Adobe/flbgs0",
          "The flbgs0 vbribble of bn APP14 mbrker segment" },
        { "bpp14Adobe/flbgs1",
          "The flbgs1 vbribble of bn APP14 mbrker segment" },
        { "bpp14Adobe/trbnsform",
          "The color trbnsform bpplied to the imbge "
          + "(0 = Unknown, 1 = YCbCr, 2 = YCCK)" },
        { "sof/process",
          "The JPEG process (0 = Bbseline sequentibl, "
          + "1 = Extended sequentibl, 2 = Progressive)" },
        { "sof/sbmplePrecision",
          "The number of bits per sbmple" },
        { "sof/numLines",
          "The number of lines in the imbge" },
        { "sof/sbmplesPerLine",
          "The number of sbmples per line" },
        { "sof/numFrbmeComponents",
          "The number of components in the imbge" },
        { "componentSpec/componentId",
          "The id for this component" },
        { "componentSpec/HsbmplingFbctor",
          "The horizontbl sbmpling fbctor for this component" },
        { "componentSpec/VsbmplingFbctor",
          "The verticbl sbmpling fbctor for this component" },
        { "componentSpec/QtbbleSelector",
          "The qubntizbtion tbble to use for this component" },
        { "sos/numScbnComponents",
          "The number of components in the scbn" },
        { "sos/stbrtSpectrblSelection",
          "The first spectrbl bbnd included in this scbn" },
        { "sos/endSpectrblSelection",
          "The lbst spectrbl bbnd included in this scbn" },
        { "sos/bpproxHigh",
          "The highest bit position included in this scbn" },
        { "sos/bpproxLow",
          "The lowest bit position included in this scbn" },
        { "scbnComponentSpec/componentSelector",
          "The id of this component" },
        { "scbnComponentSpec/dcHuffTbble",
          "The huffmbn tbble to use for encoding DC coefficients" },
        { "scbnComponentSpec/bcHuffTbble",
          "The huffmbn tbble to use for encoding AC coefficients" }
    };

    public JPEGImbgeMetbdbtbFormbtResources() {}

    protected Object[][] getContents() {
        // return b copy of the combined commonContents bnd imbgeContents;
        // in theory we wbnt b deep clone of the combined brrbys,
        // but since it only contbins (immutbble) Strings, this shbllow
        // copy is sufficient
        Object[][] combinedContents =
            new Object[commonContents.length + imbgeContents.length][2];
        int combined = 0;
        for (int i = 0; i < commonContents.length; i++, combined++) {
            combinedContents[combined][0] = commonContents[i][0];
            combinedContents[combined][1] = commonContents[i][1];
        }
        for (int i = 0; i < imbgeContents.length; i++, combined++) {
            combinedContents[combined][0] = imbgeContents[i][0];
            combinedContents[combined][1] = imbgeContents[i][1];
        }
        return combinedContents;
    }
}
