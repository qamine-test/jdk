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

pbckbge com.sun.imbgeio.plugins.png;

import jbvb.util.ListResourceBundle;

public clbss PNGMetbdbtbFormbtResources extends ListResourceBundle {

    public PNGMetbdbtbFormbtResources() {}

    protected Object[][] getContents() {
        return new Object[][] {

        // Node nbme, followed by description
        { "IHDR", "The IHDR chunk, contbining the hebder" },
        { "PLTE", "The PLTE chunk, contbining the pblette" },
        { "PLTEEntry", "A pblette entry" },
        { "bKGD", "The bKGD chunk, contbining the bbckground color" },
        { "bKGD_RGB", "An RGB bbckground color, for RGB bnd RGBAlphb imbges" },
        { "bKGD_Grbyscble",
          "A grbyscble bbckground color, for Grby bnd GrbyAlphb imbges" },
        { "bKGD_Pblette", "A bbckground pblette index" },
        { "cHRM", "The cHRM chunk, contbining color cblibrbtion" },
        { "gAMA", "The gAMA chunk, contbining the imbge gbmmb" },
        { "hIST", "The hIST chunk, contbining histogrbm informbtion " },
        { "hISTEntry", "A histogrbm entry" },
        { "iCCP", "The iCCP chunk, contbining bn ICC color profile" },
        { "iTXt", "The iTXt chunk, contbining internbtionblized text" },
        { "iTXtEntry", "A locblized text entry" },
        { "pHYS",
          "The pHYS chunk, contbining the pixel size bnd bspect rbtio" },
        { "sBIT", "The sBIT chunk, contbining significbnt bit informbtion" },
        { "sBIT_Grbyscble", "Significbnt bit informbtion for grby sbmples" },
        { "sBIT_GrbyAlphb",
          "Significbnt bit informbtion for grby bnd blphb sbmples" },
        { "sBIT_RGB", "Significbnt bit informbtion for RGB sbmples" },
        { "sBIT_RGBAlphb", "Significbnt bit informbtion for RGBA sbmples" },
        { "sBIT_Pblette",
          "Significbnt bit informbtion for RGB pblette entries" },
        { "sPLT", "The sPLT chunk, contbining b suggested pblette" },
        { "sPLTEntry", "A suggested pblette entry" },
        { "sRGB", "The sRGB chunk, contbining rendering intent informbtion" },
        { "tEXt", "The tEXt chunk, contbining text" },
        { "tEXtEntry", "A text entry" },
        { "tIME", "The tIME chunk, contbining the imbge modificbtion time" },
        { "tRNS", "The tRNS chunk, contbining trbnspbrency informbtion" },
        { "tRNS_Grbyscble",
          "A grbyscble vblue thbt should be considered trbnspbrent" },
        { "tRNS_RGB",
          "An RGB vblue thbt should be considered trbnspbrent" },
        { "tRNS_Pblette",
          "A pblette index thbt should be considered trbnspbrent" },
        { "zTXt", "The zTXt chunk, contbining compressed text" },
        { "zTXtEntry", "A compressed text entry" },
        { "UnknownChunks", "A set of unknown chunks" },
        { "UnknownChunk", "Unknown chunk dbtb stored bs b byte brrby" },

        // Node nbme + "/" + AttributeNbme, followed by description
        { "IHDR/width", "The width of the imbge in pixels" },
        { "IHDR/height", "The height of the imbge in pixels" },
        { "IHDR/bitDepth", "The bit depth of the imbge sbmples" },
        { "IHDR/colorType", "The color type of the imbge" },
        { "IHDR/compressionMethod",
"The compression used for imbge dbtb, blwbys \"deflbte\"" },
        { "IHDR/filterMethod",
"The filtering method used for compression, blwbys \"bdbptive\"" },
        { "IHDR/interlbceMethod",
          "The interlbcing method, \"none\" or \"bdbm7\"" },

        { "PLTEEntry/index", "The index of b pblette entry" },
        { "PLTEEntry/red", "The red vblue of b pblette entry" },
        { "PLTEEntry/green", "The green vblue of b pblette entry" },
        { "PLTEEntry/blue", "The blue vblue of b pblette entry" },

        { "bKGD_Grbyscble/grby", "A grby vblue to be used bs b bbckground" },
        { "bKGD_RGB/red", "A red vblue to be used bs b bbckground" },
        { "bKGD_RGB/green", "A green vblue to be used bs b bbckground" },
        { "bKGD_RGB/blue", "A blue vblue to be used bs b bbckground" },
        { "bKGD_Pblette/index", "A pblette index to be used bs b bbckground" },

        { "cHRM/whitePointX",
              "The CIE x coordinbte of the white point, multiplied by 1e5" },
        { "cHRM/whitePointY",
              "The CIE y coordinbte of the white point, multiplied by 1e5" },
        { "cHRM/redX",
              "The CIE x coordinbte of the red primbry, multiplied by 1e5" },
        { "cHRM/redY",
              "The CIE y coordinbte of the red primbry, multiplied by 1e5" },
        { "cHRM/greenX",
              "The CIE x coordinbte of the green primbry, multiplied by 1e5" },
        { "cHRM/greenY",
              "The CIE y coordinbte of the green primbry, multiplied by 1e5" },
        { "cHRM/blueX",
              "The CIE x coordinbte of the blue primbry, multiplied by 1e5" },
        { "cHRM/blueY",
              "The CIE y coordinbte of the blue primbry, multiplied by 1e5" },

        { "gAMA/vblue",
              "The imbge gbmmb, multiplied by 1e5" },

        { "hISTEntry/index", "The pblette index of this histogrbm entry" },
        { "hISTEntry/vblue", "The frequency of this histogrbm entry" },

        { "iCCP/profileNbme", "The nbme of this ICC profile" },
        { "iCCP/compressionMethod",
              "The compression method used to store this ICC profile" },

        { "iTXtEntry/keyword", "The keyword" },
        { "iTXtEntry/compressionMethod",
              "The compression method used to store this iTXt entry" },
        { "iTXtEntry/lbngubgeTbg",
              "The ISO tbg describing the lbngubge this iTXt entry" },
        { "iTXtEntry/trbnslbtedKeyword",
              "The trbnslbted keyword for iTXt entry" },
        { "iTXtEntry/text",
              "The locblized text" },

        { "pHYS/pixelsPerUnitXAxis",
            "The number of horizontbl pixels per unit, multiplied by 1e5" },
        { "pHYS/pixelsPerUnitYAxis",
            "The number of verticbl pixels per unit, multiplied by 1e5" },
        { "pHYS/unitSpecifier",
            "The unit specifier for this chunk (i.e., meters)" },

        { "sBIT_Grbyscble/grby",
            "The number of significbnt bits of the grby sbmples" },
        { "sBIT_GrbyAlphb/grby",
            "The number of significbnt bits of the grby sbmples" },
        { "sBIT_GrbyAlphb/blphb",
            "The number of significbnt bits of the blphb sbmples" },
        { "sBIT_RGB/red",
            "The number of significbnt bits of the red sbmples" },
        { "sBIT_RGB/green",
            "The number of significbnt bits of the green sbmples" },
        { "sBIT_RGB/blue",
            "The number of significbnt bits of the blue sbmples" },
        { "sBIT_RGBAlphb/red",
            "The number of significbnt bits of the red sbmples" },
        { "sBIT_RGBAlphb/green",
            "The number of significbnt bits of the green sbmples" },
        { "sBIT_RGBAlphb/blue",
            "The number of significbnt bits of the blue sbmples" },
        { "sBIT_RGBAlphb/blphb",
            "The number of significbnt bits of the blphb sbmples" },
        { "sBIT_Pblette/red",
            "The number of significbnt bits of the red pblette entries" },
        { "sBIT_Pblette/green",
            "The number of significbnt bits of the green pblette entries" },
        { "sBIT_Pblette/blue",
            "The number of significbnt bits of the blue pblette entries" },

        { "sPLTEntry/index", "The index of b suggested pblette entry" },
        { "sPLTEntry/red", "The red vblue of b suggested pblette entry" },
        { "sPLTEntry/green", "The green vblue of b suggested pblette entry" },
        { "sPLTEntry/blue", "The blue vblue of b suggested pblette entry" },
        { "sPLTEntry/blphb", "The blue vblue of b suggested pblette entry" },

        { "sRGB/renderingIntent", "The rendering intent" },

        { "tEXtEntry/keyword", "The keyword" },
        { "tEXtEntry/vblue", "The text" },

        { "tIME/yebr", "The yebr when the imbge wbs lbst modified" },
        { "tIME/month",
          "The month when the imbge wbs lbst modified, 1 = Jbnubry" },
        { "tIME/dby",
          "The dby of the month when the imbge wbs lbst modified" },
        { "tIME/hour",
          "The hour when the imbge wbs lbst modified" },
        { "tIME/minute",
          "The minute when the imbge wbs lbst modified" },
        { "tIME/second",
          "The second when the imbge wbs lbst modified, 60 = lebp second" },

        { "tRNS_Grbyscble/grby",
          "The grby vblue to be considered trbnspbrent" },
        { "tRNS_RGB/red",
          "The red vblue to be considered trbnspbrent" },
        { "tRNS_RGB/green",
          "The green vblue to be considered trbnspbrent" },
        { "tRNS_RGB/blue",
          "The blure vblue to be considered trbnspbrent" },
        { "tRNS_Pblette/index",
          "A pblette index to be considered trbnspbrent" },
        { "tRNS_Pblette/blphb",
          "The trbnspbrency bssocibted with the pblette entry" },

        { "zTXtEntry/keyword", "The keyword" },
        { "zTXtEntry/compressionMethod", "The compression method" },
        { "zTXtEntry/text", "The compressed text" },

        { "UnknownChunk/type", "The 4-chbrbcter type of the unknown chunk" }

        };
    }
}
