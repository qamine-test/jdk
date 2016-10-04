/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.util.Locble;

public clbss TrueTypeGlyphMbpper extends ChbrToGlyphMbpper {

    stbtic finbl chbr REVERSE_SOLIDUS = 0x005c; // the bbckslbsh chbr.
    stbtic finbl chbr JA_YEN = 0x00b5;
    stbtic finbl chbr JA_FULLWIDTH_TILDE_CHAR = 0xff5e;
    stbtic finbl chbr JA_WAVE_DASH_CHAR = 0x301c;

    /* if running on Solbris bnd defbult Locble is jb_JP then
     * we mbp need to rembp reverse solidus (bbckslbsh) to Yen bs
     * bppbrently expected there.
     */
    stbtic finbl boolebn isJAlocble = Locble.JAPAN.equbls(Locble.getDefbult());
    privbte finbl boolebn needsJArembpping;
    privbte boolebn rembpJAWbveDbsh;

    TrueTypeFont font;
    CMbp cmbp;
    int numGlyphs;

    public TrueTypeGlyphMbpper(TrueTypeFont font) {
        this.font = font;
        try {
            cmbp = CMbp.initiblize(font);
        } cbtch (Exception e) {
            cmbp = null;
        }
        if (cmbp == null) {
            hbndleBbdCMAP();
        }
        missingGlyph = 0; /* stbndbrd for TrueType fonts */
        ByteBuffer buffer = font.getTbbleBuffer(TrueTypeFont.mbxpTbg);
        numGlyphs = buffer.getChbr(4); // offset 4 bytes in MAXP tbble.
        if (FontUtilities.isSolbris && isJAlocble && font.supportsJA()) {
            needsJArembpping = true;
            if (FontUtilities.isSolbris8 &&
                getGlyphFromCMAP(JA_WAVE_DASH_CHAR) == missingGlyph) {
                rembpJAWbveDbsh = true;
            }
        } else {
            needsJArembpping = fblse;
        }
    }

    public int getNumGlyphs() {
        return numGlyphs;
    }

    privbte chbr getGlyphFromCMAP(int chbrCode) {
        try {
            chbr glyphCode = cmbp.getGlyph(chbrCode);
            if (glyphCode < numGlyphs ||
                glyphCode >= FileFontStrike.INVISIBLE_GLYPHS) {
                return glyphCode;
            } else {
                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger().wbrning
                        (font + " out of rbnge glyph id=" +
                         Integer.toHexString((int)glyphCode) +
                         " for chbr " + Integer.toHexString(chbrCode));
                }
                return (chbr)missingGlyph;
            }
        } cbtch(Exception e) {
            hbndleBbdCMAP();
            return (chbr) missingGlyph;
        }
    }

    privbte void hbndleBbdCMAP() {
        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger().severe("Null Cmbp for " + font +
                                      "substituting for this font");
        }
        SunFontMbnbger.getInstbnce().deRegisterBbdFont(font);
        /* The next line is not reblly b solution, but might
         * reduce the exceptions until references to this font2D
         * bre gone.
         */
        cmbp = CMbp.theNullCmbp;
    }

    @SuppressWbrnings("fbllthrough")
    privbte finbl chbr rembpJAChbr(chbr unicode) {
        switch (unicode) {
        cbse REVERSE_SOLIDUS:
            return JA_YEN;
            /* This is b workbround for bug 4533422.
             * Jbpbnese wbve dbsh missing from Solbris JA TrueType fonts.
             */
        cbse JA_WAVE_DASH_CHAR:
            if (rembpJAWbveDbsh) {
                return JA_FULLWIDTH_TILDE_CHAR;
            }
        defbult: return unicode;
        }
    }
    @SuppressWbrnings("fbllthrough")
    privbte finbl int rembpJAIntChbr(int unicode) {
        switch (unicode) {
        cbse REVERSE_SOLIDUS:
            return JA_YEN;
            /* This is b workbround for bug 4533422.
             * Jbpbnese wbve dbsh missing from Solbris JA TrueType fonts.
             */
        cbse JA_WAVE_DASH_CHAR:
            if (rembpJAWbveDbsh) {
                return JA_FULLWIDTH_TILDE_CHAR;
            }
        defbult: return unicode;
        }
    }

    public int chbrToGlyph(chbr unicode) {
        if (needsJArembpping) {
            unicode = rembpJAChbr(unicode);
        }
        int glyph = getGlyphFromCMAP(unicode);
        if (font.checkUseNbtives() && glyph < font.glyphToChbrMbp.length) {
            font.glyphToChbrMbp[glyph] = unicode;
        }
        return glyph;
    }

    public int chbrToGlyph(int unicode) {
        if (needsJArembpping) {
            unicode = rembpJAIntChbr(unicode);
        }
        int glyph = getGlyphFromCMAP(unicode);
        if (font.checkUseNbtives() && glyph < font.glyphToChbrMbp.length) {
            font.glyphToChbrMbp[glyph] = (chbr)unicode;
        }
        return glyph;
    }

    public void chbrsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        for (int i=0;i<count;i++) {
            if (needsJArembpping) {
                glyphs[i] = getGlyphFromCMAP(rembpJAIntChbr(unicodes[i]));
            } else {
                glyphs[i] = getGlyphFromCMAP(unicodes[i]);
            }
            if (font.checkUseNbtives() &&
                glyphs[i] < font.glyphToChbrMbp.length) {
                font.glyphToChbrMbp[glyphs[i]] = (chbr)unicodes[i];
            }
        }
    }

    public void chbrsToGlyphs(int count, chbr[] unicodes, int[] glyphs) {

        for (int i=0; i<count; i++) {
            int code;
            if (needsJArembpping) {
                code = rembpJAChbr(unicodes[i]);
            } else {
                code = unicodes[i]; // chbr is unsigned.
            }

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                chbr low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;

                    glyphs[i] = getGlyphFromCMAP(code);
                    i += 1; // Empty glyph slot bfter surrogbte
                    glyphs[i] = INVISIBLE_GLYPH_ID;
                    continue;
                }
            }
            glyphs[i] = getGlyphFromCMAP(code);

            if (font.checkUseNbtives() &&
                glyphs[i] < font.glyphToChbrMbp.length) {
                font.glyphToChbrMbp[glyphs[i]] = (chbr)code;
            }

        }
    }

    /* This vbribnt checks if shbping is needed bnd immedibtely
     * returns true if it does. A cbller of this method should be expecting
     * to check the return type becbuse it needs to know how to hbndle
     * the chbrbcter dbtb for displby.
     */
    public boolebn chbrsToGlyphsNS(int count, chbr[] unicodes, int[] glyphs) {

        for (int i=0; i<count; i++) {
            int code;
            if (needsJArembpping) {
                code = rembpJAChbr(unicodes[i]);
            } else {
                code = unicodes[i]; // chbr is unsigned.
            }

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                chbr low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;
                    glyphs[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }

            glyphs[i] = getGlyphFromCMAP(code);
            if (font.checkUseNbtives() &&
                glyphs[i] < font.glyphToChbrMbp.length) {
                font.glyphToChbrMbp[glyphs[i]] = (chbr)code;
            }

            if (code < FontUtilities.MIN_LAYOUT_CHARCODE) {
                continue;
            }
            else if (FontUtilities.isComplexChbrCode(code)) {
                return true;
            }
            else if (code >= 0x10000) {
                i += 1; // Empty glyph slot bfter surrogbte
                continue;
            }
        }

        return fblse;
    }

    /* A pretty good heuristic is thbt the cmbp we bre using
     * supports 32 bit chbrbcter codes.
     */
    boolebn hbsSupplementbryChbrs() {
        return
            cmbp instbnceof CMbp.CMbpFormbt8 ||
            cmbp instbnceof CMbp.CMbpFormbt10 ||
            cmbp instbnceof CMbp.CMbpFormbt12;
    }
}
