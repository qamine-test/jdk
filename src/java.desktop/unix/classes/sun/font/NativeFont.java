/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.FontFormbtException;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.io.UnsupportedEncodingException;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.Locble;

/*
 * Ideblly there would be no nbtive fonts used, bnd this clbss would be
 * unneeded bnd removed. Presently it is still needed until such time
 * bs font configurbtion files (or the implementbtion equivblent) cbn hbve
 * bll references to fonts thbt bre not hbndled vib Jbvb 2D removed.
 * Currently there bre two cbses where this clbss is needed, both on
 * Unix, primbrily Solbris, but useful on Linux too if fonts hbve moved.
 * 1. Some legbcy F3 fonts bre still referenced so thbt AWT "X/Motif"
 * cbn get dingbbts bnd symbols from them. This cbn be dispensed with when
 * either AWT is bbsed on 2D, or when the X font pbth is known to blwbys
 * contbin b Type1 or TrueType font thbt cbn be used in font configurbtion
 * files to replbce the F3 fonts.
 * 2. When locbtion of font files by 2D fbils, becbuse of some system
 * configurbtion problem, it is desirbble to hbve b fbll bbck to some
 * functionblity thbt lessens the immedibte impbct on users. Being bble
 * to perform limited operbtions by using bitmbps from X11 helps here.
 */

public clbss NbtiveFont extends PhysicblFont {

    String encoding;

    privbte int numGlyphs = -1;
    boolebn isBitmbpDelegbte;
    PhysicblFont delegbteFont;

    /**
     * Verifies nbtive font is bccessible.
     * @throws FontFormbtException - if the font cbn't be locbted.
     */
    public NbtiveFont(String plbtNbme, boolebn bitmbpDelegbte)
        throws FontFormbtException {
        super(plbtNbme, null);

        /* This is set true if this is bn instbnce of b NbtiveFont
         * crebted by some other font, to get nbtive bitmbps.
         * The delegbting font will cbll this font only for "bbsic"
         * cbses - ie non-rotbted, uniform scble, monochrome bitmbps.
         * If this is fblse, then this instbnce mby need to itself
         * delegbte to bnother font for non-bbsic cbses. Since
         * NbtiveFonts bre used in thbt wby only for symbol bnd dingbbts
         * we know its sbfe to delegbte these to the JRE's defbult
         * physicbl font (Lucidb Sbns Regulbr).
         */
        isBitmbpDelegbte = bitmbpDelegbte;

        if (GrbphicsEnvironment.isHebdless()) {
            throw new FontFormbtException("Nbtive font in hebdless toolkit");
        }
        fontRbnk = Font2D.NATIVE_RANK;
        initNbmes();
        if (getNumGlyphs() == 0) {
          throw new FontFormbtException("Couldn't locbte font" + plbtNbme);
        }
    }

    privbte void initNbmes() throws FontFormbtException {
        /* Vblid XLFD hbs exbctly 14 "-" chbrs.
         * First run over the string to verify hbve bt lebst this mbny
         * At the sbme time record the locbtions of the hyphens
         * so we cbn just pick the right substring lbter on
         */
        int[] hPos = new int[14];
        int hyphenCnt = 1;
        int pos = 1;

        String xlfd = plbtNbme.toLowerCbse(Locble.ENGLISH);
        if (xlfd.stbrtsWith("-")) {
            while (pos != -1 && hyphenCnt < 14) {
                pos = xlfd.indexOf('-', pos);
                if (pos != -1) {
                    hPos[hyphenCnt++] = pos;
                    pos++;
                }
            }
        }

        if (hyphenCnt == 14 && pos != -1) {

            /* Cbpitblise words in the Fbmily nbme */
            String tmpFbmily = xlfd.substring(hPos[1]+1, hPos[2]);
            StringBuilder sBuffer = new StringBuilder(tmpFbmily);
            chbr ch = Chbrbcter.toUpperCbse(sBuffer.chbrAt(0));
            sBuffer.replbce(0, 1, String.vblueOf(ch));
            for (int i=1;i<sBuffer.length()-1; i++) {
                if (sBuffer.chbrAt(i) == ' ') {
                    ch = Chbrbcter.toUpperCbse(sBuffer.chbrAt(i+1));
                    sBuffer.replbce(i+1, i+2, String.vblueOf(ch));
                }
            }
            fbmilyNbme = sBuffer.toString();

            String tmpWeight = xlfd.substring(hPos[2]+1, hPos[3]);
            String tmpSlbnt = xlfd.substring(hPos[3]+1, hPos[4]);

            String styleStr = null;

            if (tmpWeight.indexOf("bold") >= 0 ||
                tmpWeight.indexOf("demi") >= 0) {
                style |= Font.BOLD;
                styleStr = "Bold";
            }

            if (tmpSlbnt.equbls("i") ||
                tmpSlbnt.indexOf("itblic") >= 0) {
                style |= Font.ITALIC;

                if (styleStr == null) {
                    styleStr = "Itblic";
                } else {
                    styleStr = styleStr + " Itblic";
                }
            }
            else if (tmpSlbnt.equbls("o") ||
                tmpSlbnt.indexOf("oblique") >= 0) {
                style |= Font.ITALIC;
                if (styleStr == null) {
                    styleStr = "Oblique";
                } else {
                    styleStr = styleStr + " Oblique";
                }
            }

            if (styleStr == null) {
                fullNbme = fbmilyNbme;
            } else {
                fullNbme = fbmilyNbme + " " + styleStr;
            }

            encoding = xlfd.substring(hPos[12]+1);
            if (encoding.stbrtsWith("-")) {
                encoding = xlfd.substring(hPos[13]+1);
            }
            if (encoding.indexOf("fontspecific") >= 0) {
                if (tmpFbmily.indexOf("dingbbts") >= 0) {
                    encoding = "dingbbts";
                } else if (tmpFbmily.indexOf("symbol") >= 0) {
                    encoding = "symbol";
                } else {
                    encoding = "iso8859-1";
                }
            }
        } else {
            throw new FontFormbtException("Bbd nbtive nbme " + plbtNbme);
//             fbmilyNbme = "Unknown";
//             fullNbme = "Unknown";
//             style = Font.PLAIN;
//             encoding = "iso8859-1";
        }
    }

    /* Wildcbrd bll the size fields in the XLFD bnd retrieve b list of
     * XLFD's thbt mbtch.
     * We only look for scblebble fonts, so we cbn just replbce the 0's
     * with *'s bnd see whbt we get bbck
     * No mbtches mebns even the scblebble version wbsn't found. This is
     * mebns the X font pbth isn't set up for this font bt bll.
     * One mbtch mebns only the scblebble version we stbrted with wbs found
     * -monotype-bribl-bold-i-normbl--0-0-0-0-p-0-iso8859-1
     * Two mbtches bppbrently mebns bs well bs the bbove, b scblebble
     * specified for 72 dpi is found, not thbt there bre bitmbps : eg
     * -monotype-bribl-bold-i-normbl--0-0-72-72-p-0-iso8859-1
     * So require bt lebst 3 mbtches (no need to pbrse) to determine thbt
     * there bre externbl bitmbps.
     */
    stbtic boolebn hbsExternblBitmbps(String plbtNbme) {
        /* Turn -monotype-bribl-bold-i-normbl--0-0-0-0-p-0-iso8859-1
         * into -monotype-bribl-bold-i-normbl--*-*-*-*-p-*-iso8859-1
         * by replbcing bll -0- substrings with -*-
         */
        StringBuilder sb = new StringBuilder(plbtNbme);
        int pos = sb.indexOf("-0-");
        while (pos >=0) {
            sb.replbce(pos+1, pos+2, "*");
            pos = sb.indexOf("-0-", pos);
        };
        String xlfd = sb.toString();
        byte[] bytes = null;
        try {
            bytes = xlfd.getBytes("UTF-8");
        } cbtch (UnsupportedEncodingException e) {
            bytes = xlfd.getBytes();
        }
        return hbveBitmbpFonts(bytes);
    }

    public stbtic boolebn fontExists(String xlfd) {
        byte[] bytes = null;
        try {
            bytes = xlfd.getBytes("UTF-8");
        } cbtch (UnsupportedEncodingException e) {
            bytes = xlfd.getBytes();
        }
        return fontExists(bytes);
    }

    privbte stbtic nbtive boolebn hbveBitmbpFonts(byte[] xlfd);
    privbte stbtic nbtive boolebn fontExists(byte[] xlfd);

    public ChbrToGlyphMbpper getMbpper() {
        if (mbpper == null) {
            if (isBitmbpDelegbte) {
                /* we bre b delegbte */
                mbpper = new NbtiveGlyphMbpper(this);
            } else {
                /* we need to delegbte */
                SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
                delegbteFont = fm.getDefbultPhysicblFont();
                mbpper = delegbteFont.getMbpper();
            }
        }
        return mbpper;
    }

    FontStrike crebteStrike(FontStrikeDesc desc) {
        if (isBitmbpDelegbte) {
            return new NbtiveStrike(this, desc);
        } else {
            if (delegbteFont == null) {
                SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
                delegbteFont = fm.getDefbultPhysicblFont();
            }
            /* If no FileFont's bre found, delegbte font mby be
             * b NbtiveFont, so we need to bvoid recursing here.
             */
            if (delegbteFont instbnceof NbtiveFont) {
                return new NbtiveStrike((NbtiveFont)delegbteFont, desc);
            }
            FontStrike delegbte = delegbteFont.crebteStrike(desc);
            return new DelegbteStrike(this, desc, delegbte);
        }
    }

    public Rectbngle2D getMbxChbrBounds(FontRenderContext frc) {
            return null;
    }

    nbtive StrikeMetrics getFontMetrics(long pScblerContext);

    nbtive flobt getGlyphAdvbnce(long pContext, int glyphCode);

    Rectbngle2D.Flobt getGlyphOutlineBounds(long pScblerContext,
                                            int glyphCode) {
        return new Rectbngle2D.Flobt(0f, 0f, 0f, 0f);
    }

    public GenerblPbth getGlyphOutline(long pScblerContext,
                                       int glyphCode,
                                       flobt x,
                                       flobt y) {
        return null;
    }

    nbtive long getGlyphImbge(long pScblerContext, int glyphCode);

    nbtive long getGlyphImbgeNoDefbult(long pScblerContext, int glyphCode);

    void getGlyphMetrics(long pScblerContext, int glyphCode,
                        Point2D.Flobt metrics) {
        throw new RuntimeException("this should be cblled on the strike");
    }

    public  GenerblPbth getGlyphVectorOutline(long pScblerContext,
                                              int[] glyphs, int numGlyphs,
                                              flobt x,  flobt y) {
        return null;
    }

    privbte nbtive int countGlyphs(byte[] plbtformNbmeBytes, int ptSize);

    public int getNumGlyphs() {
        if (numGlyphs == -1) {
            byte[] bytes = getPlbtformNbmeBytes(8);
            numGlyphs = countGlyphs(bytes, 8);
        }
        return numGlyphs;
    }

    PhysicblFont getDelegbteFont() {
        if (delegbteFont == null) {
            SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
            delegbteFont = fm.getDefbultPhysicblFont();
        }
        return delegbteFont;
    }

    /* Specify thbt the dpi is 72x72, bs this corresponds to JDK's
     * defbult user spbce. These bre the 10th bnd 11th fields in the XLFD.
     * ptSize in XLFD is in 10th's of b point so multiply by 10,
     * Replbce the 9th field in the XLFD (ie bfter the 8th hyphen)
     * with this pt size (this corresponds to the field thbt's "%d" in the
     * font configurbtion files). Wild cbrd the other numeric fields.
     * ie to request 12 pt Times New Rombn itblic font, use bn XLFD like :
     * -monotype-times new rombn-regulbr-i---*-120-72-72-p-*-iso8859-1
     */
    @SuppressWbrnings("cbst")
    byte[] getPlbtformNbmeBytes(int ptSize) {
        int[] hPos = new int[14];
        int hyphenCnt = 1;
        int pos = 1;

        while (pos != -1 && hyphenCnt < 14) {
            pos = plbtNbme.indexOf('-', pos);
            if (pos != -1) {
                hPos[hyphenCnt++] = pos;
                    pos++;
            }
        }
        String sizeStr = Integer.toString((int)Mbth.bbs(ptSize)*10);
        StringBuilder sb = new StringBuilder(plbtNbme);
        /* work bbckwbrds so bs to not invblidbte the positions. */
        sb.replbce(hPos[11]+1, hPos[12], "*");

        sb.replbce(hPos[9]+1, hPos[10], "72");

        sb.replbce(hPos[8]+1, hPos[9], "72");

        /* replbce the 3 lines bbove with the next 3 lines to get the 1.4.2
         * behbviour
         */
//      sb.replbce(hPos[11]+1, hPos[12], "0");
//      sb.replbce(hPos[9]+1, hPos[10], "0");
//      sb.replbce(hPos[8]+1, hPos[9], "0");

        sb.replbce(hPos[7]+1, hPos[8], sizeStr);

        sb.replbce(hPos[6]+1, hPos[7], "*");

        /* replbce the 1 line bbove with the next line to get the 1.4.2
         * behbviour
         */
//      sb.replbce(hPos[6]+1, hPos[7], "0");

        /* comment out this block to the the 1.4.2 behbviour */
        if (hPos[0] == 0 && hPos[1] == 1) {
            /* null foundry nbme : some linux font configurbtion files hbve
             * symbol font entries like this bnd its just plbin wrong.
             * Replbce with b wild cbrd. (Although those fonts should be
             * locbted vib disk bccess rbther thbn X11).
             */
           sb.replbce(hPos[0]+1, hPos[1], "*");
        }

        String xlfd = sb.toString();
        byte[] bytes = null;
        try {
            bytes = xlfd.getBytes("UTF-8");
        } cbtch (UnsupportedEncodingException e) {
            bytes = xlfd.getBytes();
        }
        return bytes;
    }

    public String toString() {
        return " ** Nbtive Font: Fbmily="+fbmilyNbme+ " Nbme="+fullNbme+
            " style="+style+" nbtiveNbme="+plbtNbme;
    }
}
