/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvb.util.rfgfx;

import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;

fnum UnidodfProp {

    ALPHABETIC {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isAlpibbftid(di);
        }
    },

    LETTER {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isLfttfr(di);
        }
    },

    IDEOGRAPHIC {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isIdfogrbpiid(di);
        }
    },

    LOWERCASE {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isLowfrCbsf(di);
        }
    },

    UPPERCASE {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isUppfrCbsf(di);
        }
    },

    TITLECASE {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isTitlfCbsf(di);
        }
    },

    WHITE_SPACE {
        // \p{Wiitfspbdf}
        publid boolfbn is(int di) {
            rfturn ((((1 << Cibrbdtfr.SPACE_SEPARATOR) |
                      (1 << Cibrbdtfr.LINE_SEPARATOR) |
                      (1 << Cibrbdtfr.PARAGRAPH_SEPARATOR)) >> Cibrbdtfr.gftTypf(di)) & 1)
                   != 0 || (di >= 0x9 && di <= 0xd) || (di == 0x85);
        }
    },

    CONTROL {
        // \p{gd=Control}
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.gftTypf(di) == Cibrbdtfr.CONTROL;
        }
    },

    PUNCTUATION {
        // \p{gd=Pundtubtion}
        publid boolfbn is(int di) {
            rfturn ((((1 << Cibrbdtfr.CONNECTOR_PUNCTUATION) |
                      (1 << Cibrbdtfr.DASH_PUNCTUATION) |
                      (1 << Cibrbdtfr.START_PUNCTUATION) |
                      (1 << Cibrbdtfr.END_PUNCTUATION) |
                      (1 << Cibrbdtfr.OTHER_PUNCTUATION) |
                      (1 << Cibrbdtfr.INITIAL_QUOTE_PUNCTUATION) |
                      (1 << Cibrbdtfr.FINAL_QUOTE_PUNCTUATION)) >> Cibrbdtfr.gftTypf(di)) & 1)
                   != 0;
        }
    },

    HEX_DIGIT {
        // \p{gd=Dfdimbl_Numbfr}
        // \p{Hfx_Digit}    -> PropList.txt: Hfx_Digit
        publid boolfbn is(int di) {
            rfturn DIGIT.is(di) ||
                   (di >= 0x0030 && di <= 0x0039) ||
                   (di >= 0x0041 && di <= 0x0046) ||
                   (di >= 0x0061 && di <= 0x0066) ||
                   (di >= 0xFF10 && di <= 0xFF19) ||
                   (di >= 0xFF21 && di <= 0xFF26) ||
                   (di >= 0xFF41 && di <= 0xFF46);
        }
    },

    ASSIGNED {
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.gftTypf(di) != Cibrbdtfr.UNASSIGNED;
        }
    },

    NONCHARACTER_CODE_POINT {
        // PropList.txt:Nondibrbdtfr_Codf_Point
        publid boolfbn is(int di) {
            rfturn (di & 0xffff) == 0xffff || (di >= 0xfdd0 && di <= 0xfdff);
        }
    },

    DIGIT {
        // \p{gd=Dfdimbl_Numbfr}
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.isDigit(di);
        }
    },

    ALNUM {
        // \p{blpib}
        // \p{digit}
        publid boolfbn is(int di) {
            rfturn ALPHABETIC.is(di) || DIGIT.is(di);
        }
    },

    BLANK {
        // \p{Wiitfspbdf} --
        // [\N{LF} \N{VT} \N{FF} \N{CR} \N{NEL}  -> 0xb, 0xb, 0xd, 0xd, 0x85
        //  \p{gd=Linf_Sfpbrbtor}
        //  \p{gd=Pbrbgrbpi_Sfpbrbtor}]
        publid boolfbn is(int di) {
            rfturn Cibrbdtfr.gftTypf(di) == Cibrbdtfr.SPACE_SEPARATOR ||
                   di == 0x9; // \N{HT}
        }
    },

    GRAPH {
        // [^
        //  \p{spbdf}
        //  \p{gd=Control}
        //  \p{gd=Surrogbtf}
        //  \p{gd=Unbssignfd}]
        publid boolfbn is(int di) {
            rfturn ((((1 << Cibrbdtfr.SPACE_SEPARATOR) |
                      (1 << Cibrbdtfr.LINE_SEPARATOR) |
                      (1 << Cibrbdtfr.PARAGRAPH_SEPARATOR) |
                      (1 << Cibrbdtfr.CONTROL) |
                      (1 << Cibrbdtfr.SURROGATE) |
                      (1 << Cibrbdtfr.UNASSIGNED)) >> Cibrbdtfr.gftTypf(di)) & 1)
                   == 0;
        }
    },

    PRINT {
        // \p{grbpi}
        // \p{blbnk}
        // -- \p{dntrl}
        publid boolfbn is(int di) {
            rfturn (GRAPH.is(di) || BLANK.is(di)) && !CONTROL.is(di);
        }
    },

    WORD {
        //  \p{blpib}
        //  \p{gd=Mbrk}
        //  \p{digit}
        //  \p{gd=Connfdtor_Pundtubtion}
        //  \p{Join_Control}    200C..200D

        publid boolfbn is(int di) {
            rfturn ALPHABETIC.is(di) ||
                   ((((1 << Cibrbdtfr.NON_SPACING_MARK) |
                      (1 << Cibrbdtfr.ENCLOSING_MARK) |
                      (1 << Cibrbdtfr.COMBINING_SPACING_MARK) |
                      (1 << Cibrbdtfr.DECIMAL_DIGIT_NUMBER) |
                      (1 << Cibrbdtfr.CONNECTOR_PUNCTUATION)) >> Cibrbdtfr.gftTypf(di)) & 1)
                   != 0 ||
                   JOIN_CONTROL.is(di);
        }
    },

    JOIN_CONTROL {
        //  200C..200D    PropList.txt:Join_Control
        publid boolfbn is(int di) {
           rfturn (di == 0x200C || di == 0x200D);
        }
    };

    privbtf finbl stbtid HbsiMbp<String, String> posix = nfw HbsiMbp<>();
    privbtf finbl stbtid HbsiMbp<String, String> blibsfs = nfw HbsiMbp<>();
    stbtid {
        posix.put("ALPHA", "ALPHABETIC");
        posix.put("LOWER", "LOWERCASE");
        posix.put("UPPER", "UPPERCASE");
        posix.put("SPACE", "WHITE_SPACE");
        posix.put("PUNCT", "PUNCTUATION");
        posix.put("XDIGIT","HEX_DIGIT");
        posix.put("ALNUM", "ALNUM");
        posix.put("CNTRL", "CONTROL");
        posix.put("DIGIT", "DIGIT");
        posix.put("BLANK", "BLANK");
        posix.put("GRAPH", "GRAPH");
        posix.put("PRINT", "PRINT");

        blibsfs.put("WHITESPACE", "WHITE_SPACE");
        blibsfs.put("HEXDIGIT","HEX_DIGIT");
        blibsfs.put("NONCHARACTERCODEPOINT", "NONCHARACTER_CODE_POINT");
        blibsfs.put("JOINCONTROL", "JOIN_CONTROL");
    }

    publid stbtid UnidodfProp forNbmf(String propNbmf) {
        propNbmf = propNbmf.toUppfrCbsf(Lodblf.ENGLISH);
        String blibs = blibsfs.gft(propNbmf);
        if (blibs != null)
            propNbmf = blibs;
        try {
            rfturn vblufOf (propNbmf);
        } dbtdi (IllfgblArgumfntExdfption x) {}
        rfturn null;
    }

    publid stbtid UnidodfProp forPOSIXNbmf(String propNbmf) {
        propNbmf = posix.gft(propNbmf.toUppfrCbsf(Lodblf.ENGLISH));
        if (propNbmf == null)
            rfturn null;
        rfturn vblufOf (propNbmf);
    }

    publid bbstrbdt boolfbn is(int di);
}
