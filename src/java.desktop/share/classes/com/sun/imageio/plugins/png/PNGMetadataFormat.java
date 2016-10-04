/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.ListResourceBundle;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;

public clbss PNGMetbdbtbFormbt extends IIOMetbdbtbFormbtImpl {

    privbte stbtic IIOMetbdbtbFormbt instbnce = null;

    privbte stbtic String VALUE_0 = "0";
    privbte stbtic String VALUE_1 = "1";
    privbte stbtic String VALUE_12 = "12";
    privbte stbtic String VALUE_23 = "23";
    privbte stbtic String VALUE_31 = "31";
    privbte stbtic String VALUE_59 = "59";
    privbte stbtic String VALUE_60 = "60";
    privbte stbtic String VALUE_255 = "255";
    privbte stbtic String VALUE_MAX_16 = "65535"; // 2^16 - 1
    privbte stbtic String VALUE_MAX_32 = "2147483647"; // 2^32 - 1

    privbte PNGMetbdbtbFormbt() {
        super(PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
              CHILD_POLICY_SOME);

        // root -> IHDR
        bddElement("IHDR", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("IHDR", "width",
                     DATATYPE_INTEGER, true, null,
                     VALUE_1, VALUE_MAX_32, true, true);

        bddAttribute("IHDR", "height",
                     DATATYPE_INTEGER, true, null,
                     VALUE_1, VALUE_MAX_32, true, true);

        bddAttribute("IHDR", "bitDepth",
                     DATATYPE_INTEGER, true, null,
                     Arrbys.bsList(PNGMetbdbtb.IHDR_bitDepths));

        String[] colorTypes = {
            "Grbyscble", "RGB", "Pblette", "GrbyAlphb", "RGBAlphb"
        };
        bddAttribute("IHDR", "colorType",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(colorTypes));

        bddAttribute("IHDR", "compressionMethod",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.IHDR_compressionMethodNbmes));

        bddAttribute("IHDR", "filterMethod",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.IHDR_filterMethodNbmes));

        bddAttribute("IHDR", "interlbceMethod",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.IHDR_interlbceMethodNbmes));

        // root -> PLTE
        bddElement("PLTE", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, 256);

        // root -> PLTE -> PLTEEntry
        bddElement("PLTEEntry", "PLTE",
                   CHILD_POLICY_EMPTY);

        bddAttribute("PLTEEntry", "index",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("PLTEEntry", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("PLTEEntry", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("PLTEEntry", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> bKGD
        bddElement("bKGD", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_CHOICE);

        // root -> bKGD -> bKGD_Grbyscble
        bddElement("bKGD_Grbyscble", "bKGD",
                   CHILD_POLICY_EMPTY);

        bddAttribute("bKGD_Grbyscble", "grby",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        // root -> bKGD -> bKGD_RGB
        bddElement("bKGD_RGB", "bKGD",
                   CHILD_POLICY_EMPTY);

        bddAttribute("bKGD_RGB", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("bKGD_RGB", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("bKGD_RGB", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        // root -> bKGD -> bKGD_Pblette
        bddElement("bKGD_Pblette", "bKGD",
                   CHILD_POLICY_EMPTY);

        bddAttribute("bKGD_Pblette", "index",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> cHRM
        bddElement("cHRM", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("cHRM", "whitePointX",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "whitePointY",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "redX",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "redY",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "greenX",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "greenY",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "blueX",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("cHRM", "blueY",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        // root -> gAMA
        bddElement("gAMA", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("gAMA", "vblue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_32, true, true);

        // root -> hIST
        bddElement("hIST", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, 256);

        // root -> hISTEntry
        bddElement("hISTEntry", "hIST",
                   CHILD_POLICY_EMPTY);

        bddAttribute("hISTEntry", "index",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("hISTEntry", "vblue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        // root -> iCCP
        bddElement("iCCP", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("iCCP", "profileNbme",
                     DATATYPE_STRING, true, null);

        bddAttribute("iCCP", "compressionMethod",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.iCCP_compressionMethodNbmes));

        bddObjectVblue("iCCP", byte.clbss, 0, Integer.MAX_VALUE);

        // root -> iTXt
        bddElement("iTXt", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, Integer.MAX_VALUE);

        // root -> iTXt -> iTXtEntry
        bddElement("iTXtEntry", "iTXt",
                   CHILD_POLICY_EMPTY);

        bddAttribute("iTXtEntry", "keyword",
                     DATATYPE_STRING, true, null);

        bddBoolebnAttribute("iTXtEntry", "compressionFlbg",
                            fblse, fblse);

        bddAttribute("iTXtEntry", "compressionMethod",
                     DATATYPE_STRING, true, null);

        bddAttribute("iTXtEntry", "lbngubgeTbg",
                     DATATYPE_STRING, true, null);

        bddAttribute("iTXtEntry", "trbnslbtedKeyword",
                     DATATYPE_STRING, true, null);

        bddAttribute("iTXtEntry", "text",
                     DATATYPE_STRING, true, null);

        // root -> pHYS
        bddElement("pHYS", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("pHYS", "pixelsPerUnitXAxis",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_32, true, true);
        bddAttribute("pHYS", "pixelsPerUnitYAxis",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_32, true, true);
        bddAttribute("pHYS", "unitSpecifier",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.unitSpecifierNbmes));

        // root -> sBIT
        bddElement("sBIT", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_CHOICE);

        // root -> sBIT -> sBIT_Grbyscble
        bddElement("sBIT_Grbyscble", "sBIT",
                   CHILD_POLICY_EMPTY);

        bddAttribute("sBIT_Grbyscble", "grby",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> sBIT -> sBIT_GrbyAlphb
        bddElement("sBIT_GrbyAlphb", "sBIT",
                   CHILD_POLICY_EMPTY);

        bddAttribute("sBIT_GrbyAlphb", "grby",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_GrbyAlphb", "blphb",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> sBIT -> sBIT_RGB
        bddElement("sBIT_RGB", "sBIT",
                   CHILD_POLICY_EMPTY);

        bddAttribute("sBIT_RGB", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_RGB", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_RGB", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> sBIT -> sBIT_RGBAlphb
        bddElement("sBIT_RGBAlphb", "sBIT",
                   CHILD_POLICY_EMPTY);

        bddAttribute("sBIT_RGBAlphb", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_RGBAlphb", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_RGBAlphb", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_RGBAlphb", "blphb",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> sBIT -> sBIT_Pblette
        bddElement("sBIT_Pblette", "sBIT",
                   CHILD_POLICY_EMPTY);

        bddAttribute("sBIT_Pblette", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_Pblette", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sBIT_Pblette", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> sPLT
        bddElement("sPLT", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, 256);

        // root -> sPLT -> sPLTEntry
        bddElement("sPLTEntry", "sPLT",
                   CHILD_POLICY_EMPTY);

        bddAttribute("sPLTEntry", "index",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sPLTEntry", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sPLTEntry", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sPLTEntry", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("sPLTEntry", "blphb",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> sRGB
        bddElement("sRGB", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("sRGB", "renderingIntent",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.renderingIntentNbmes));

        // root -> tEXt
        bddElement("tEXt", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, Integer.MAX_VALUE);

        // root -> tEXt -> tEXtEntry
        bddElement("tEXtEntry", "tEXt",
                   CHILD_POLICY_EMPTY);

        bddAttribute("tEXtEntry", "keyword",
                     DATATYPE_STRING, true, null);

        bddAttribute("tEXtEntry", "vblue",
                     DATATYPE_STRING, true, null);

        // root -> tIME
        bddElement("tIME", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_EMPTY);

        bddAttribute("tIME", "yebr",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("tIME", "month",
                     DATATYPE_INTEGER, true, null,
                     VALUE_1, VALUE_12, true, true);

        bddAttribute("tIME", "dby",
                     DATATYPE_INTEGER, true, null,
                     VALUE_1, VALUE_31, true, true);

        bddAttribute("tIME", "hour",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_23, true, true);

        bddAttribute("tIME", "minute",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_59, true, true);

        bddAttribute("tIME", "second",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_60, true, true);

        // root -> tRNS
        bddElement("tRNS", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   CHILD_POLICY_CHOICE);

        // root -> tRNS -> tRNS_Grbyscble
        bddElement("tRNS_Grbyscble", "tRNS",
                   CHILD_POLICY_EMPTY);

        bddAttribute("tRNS_Grbyscble", "grby",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        // root -> tRNS -> tRNS_RGB
        bddElement("tRNS_RGB", "tRNS",
                   CHILD_POLICY_EMPTY);

        bddAttribute("tRNS_RGB", "red",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("tRNS_RGB", "green",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        bddAttribute("tRNS_RGB", "blue",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_MAX_16, true, true);

        // root -> tRNS -> tRNS_Pblette
        bddElement("tRNS_Pblette", "tRNS",
                   CHILD_POLICY_EMPTY);

        bddAttribute("tRNS_Pblette", "index",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        bddAttribute("tRNS_Pblette", "blphb",
                     DATATYPE_INTEGER, true, null,
                     VALUE_0, VALUE_255, true, true);

        // root -> zTXt
        bddElement("zTXt", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, Integer.MAX_VALUE);

        // root -> zTXt -> zTXtEntry
        bddElement("zTXtEntry", "zTXt",
                   CHILD_POLICY_EMPTY);

        bddAttribute("zTXtEntry", "keyword",
                     DATATYPE_STRING, true, null);

        bddAttribute("zTXtEntry", "compressionMethod",
                     DATATYPE_STRING, true, null,
                     Arrbys.bsList(PNGMetbdbtb.zTXt_compressionMethodNbmes));

        bddAttribute("zTXtEntry", "text",
                     DATATYPE_STRING, true, null);

        // root -> UnknownChunks
        bddElement("UnknownChunks", PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                   1, Integer.MAX_VALUE);

        // root -> UnknownChunks -> UnknownChunk
        bddElement("UnknownChunk", "UnknownChunks",
                   CHILD_POLICY_EMPTY);

        bddAttribute("UnknownChunk", "type",
                     DATATYPE_STRING, true, null);

        bddObjectVblue("UnknownChunk", byte.clbss, 0, Integer.MAX_VALUE);
    }

    public boolebn cbnNodeAppebr(String elementNbme,
                                 ImbgeTypeSpecifier imbgeType) {
        return true;
    }

    public stbtic synchronized IIOMetbdbtbFormbt getInstbnce() {
        if (instbnce == null) {
            instbnce = new PNGMetbdbtbFormbt();
        }
        return instbnce;
    }
}
