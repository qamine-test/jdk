/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.common;

import jbvb.util.ListResourceBundle;

public clbss StbndbrdMetbdbtbFormbtResources extends ListResourceBundle {

    public StbndbrdMetbdbtbFormbtResources() {}

    protected Object[][] getContents() {
        return new Object[][] {

        // Node nbme, followed by description, or
        // Node nbme + "/" + AttributeNbme, followed by description

        { "Chromb", "Chromb (color) informbtion" },

        { "ColorSpbceType", "The rbw color spbce of the imbge" },

        { "NumChbnnels",
          "The number of chbnnels in the rbw imbge, including blphb" },

        { "Gbmmb", "The imbge gbmmb" },

        { "BlbckIsZero",
          "True if smbller vblues represent dbrker shbdes"},

        { "Pblette", "Pblette-color informbtion" },

        { "PbletteEntry", "A pblette entry" },
        { "PbletteEntry/index", "The index of the pblette entry" },
        { "PbletteEntry/red", "The red vblue for the pblette entry" },
        { "PbletteEntry/green", "The green vblue for the pblette entry" },
        { "PbletteEntry/blue", "The blue vblue for the pblette entry" },
        { "PbletteEntry/blphb", "The blphb vblue for the pblette entry" },

        { "BbckgroundIndex", "A pblette index to be used bs b bbckground" },

        { "BbckgroundColor", "An RGB triple to be used bs b bbckground" },
        { "BbckgroundColor/red", "The red bbckground vblue" },
        { "BbckgroundColor/green", "The green bbckground vblue" },
        { "BbckgroundColor/blue", "The blue bbckground vblue" },

        { "Compression", "Compression informbtion" },

        { "CompressionTypeNbme", "The nbme of the compression scheme in use" },

        { "Lossless",
          "True if the compression scheme is lossless" },

        { "BitRbte", "The estimbted bit rbte of the compression scheme" },

        { "NumProgressiveScbns",
          "The number of progressive scbns used in the imbge encoding"},

        { "Dbtb", "Informbtion on the imbge lbyout" },

        { "PlbnbrConfigurbtion",
          "The orgbnizbtion of imbge sbmples in the strebm" },

        { "SbmpleFormbt", "The numeric formbt of imbge sbmples" },

        { "BitsPerSbmple", "The number of bits per sbmple"},
        { "BitsPerSbmple/vblue",
          "A list of integers, one per chbnnel" },

        { "SignificbntBitsPerSbmple",
          "The number of significbnt bits per sbmple"},
        { "SignificbntBitsPerSbmple/vblue",
          "A list of integers, one per chbnnel" },

        { "SbmpleMSB",
          "The position of the most significbnt bit of ebch sbmple"},
        { "SbmpleMSB/vblue",
          "A list of integers, one per chbnnel" },

        { "Dimension", "Dimension informbtion" },

        { "PixelAspectRbtio", "The width of b pixel divided by its height" },

        { "ImbgeOrientbtion", "The desired orientbtion of the imbge in terms of flips bnd counter-clockwise rotbtions" },

        { "HorizontblPixelSize",
  "The width of b pixel, in millimeters, bs it should be rendered on medib" },

        { "VerticblPixelSize",
  "The height of b pixel, in millimeters, bs it should be rendered on medib" },

        { "HorizontblPhysicblPixelSpbcing",
          "The horizontbl distbnce in the subject of the imbge, in millimeters, represented by one pixel bt the center of the imbge" },

        { "VerticblPhysicblPixelSpbcing",
          "The verticbl distbnce in the subject of the imbge, in millimeters, represented by one pixel bt the center of the imbge" },

        { "HorizontblPosition",
          "The horizontbl position, in millimeters, where the imbge should be rendered on medib " },

        { "VerticblPosition",
          "The verticbl position, in millimeters, where the imbge should be rendered on medib " },

        { "HorizontblPixelOffset",
          "The horizontbl position, in pixels, where the imbge should be rendered onto b rbster displby" },

        { "VerticblPixelOffset",
          "The verticbl position, in pixels, where the imbge should be rendered onto b rbster displby" },

        { "HorizontblScreenSize",
          "The width, in pixels, of the rbster displby into which the imbge should be rendered" },

        { "VerticblScreenSize",
          "The height, in pixels, of the rbster displby into which the imbge should be rendered" },

        { "Document", "Document informbtion" },

        { "FormbtVersion",
          "The version of the formbt used by the strebm" },

        { "SubimbgeInterpretbtion",
          "The interpretbtion of this imbge in relbtion to the other imbges stored in the sbme strebm" },

        { "ImbgeCrebtionTime", "The time of imbge crebtion" },
        { "ImbgeCrebtionTime/yebr",
          "The full yebr (e.g., 1967, not 67)" },
        { "ImbgeCrebtionTime/month",
          "The month, with Jbnubry = 1" },
        { "ImbgeCrebtionTime/dby",
          "The dby of the month" },
        { "ImbgeCrebtionTime/hour",
          "The hour from 0 to 23" },
        { "ImbgeCrebtionTime/minute",
          "The minute from 0 to 59" },
        { "ImbgeCrebtionTime/second",
          "The second from 0 to 60 (60 = lebp second)" },

        { "ImbgeModificbtionTime", "The time of the lbst imbge modificbtion" },
        { "ImbgeModificbtionTime/yebr",
          "The full yebr (e.g., 1967, not 67)" },
        { "ImbgeModificbtionTime/month",
          "The month, with Jbnubry = 1" },
        { "ImbgeModificbtionTime/dby",
          "The dby of the month" },
        { "ImbgeModificbtionTime/hour",
          "The hour from 0 to 23" },
        { "ImbgeModificbtionTime/minute",
          "The minute from 0 to 59" },
        { "ImbgeModificbtionTime/second",
          "The second from 0 to 60 (60 = lebp second)" },

        { "Text", "Text informbtion" },

        { "TextEntry", "A text entry"},
        { "TextEntry/keyword", "A keyword bssocibted with the text entry" },
        { "TextEntry/vblue", "the text entry" },
        { "TextEntry/lbngubge", "The lbngubge of the text" },
        { "TextEntry/encoding", "The encoding of the text" },
        { "TextEntry/compression", "The method used to compress the text" },

        { "Trbnspbrency", "Trbnspbrency informbtion" },

        { "Alphb", "The type of blphb informbtion contbined in the imbge" },

        { "TrbnspbrentIndex", "A pblette index to be trebted bs trbnspbrent" },

        { "TrbnspbrentColor", "An RGB color to be trebted bs trbnspbrent" },
        { "TrbnspbrentColor/red",
          "The red chbnnel of the trbnspbrent color" },
        { "TrbnspbrentColor/green",
          "The green chbnnel of the trbnspbrent color" },
        { "TrbnspbrentColor/blue",
          "The blue chbnnel of the trbnspbrent color" },

        { "TileTrbnspbrencies", "A list of completely trbnspbrent tiles" },

        { "TrbnspbrentTile", "The index of b completely trbnspbrent tile" },
        { "TrbnspbrentTile/x", "The tile's X index" },
        { "TrbnspbrentTile/y", "The tile's Y index" },

        { "TileOpbcities", "A list of completely opbque tiles" },

        { "OpbqueTile", "The index of b completely opbque tile" },
        { "OpbqueTile/x", "The tile's X index" },
        { "OpbqueTile/y", "The tile's Y index" },

        };
    }
}
