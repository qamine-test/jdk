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

/*
 * This isn't b criticbl performbnce cbse, so don't do bny
 * chbr->glyph mbp cbching for Type1 fonts. The ones thbt bre used
 * in composites will be cbched there.
 */

public finbl clbss Type1GlyphMbpper extends ChbrToGlyphMbpper {

    Type1Font font;
    FontScbler scbler;

    public Type1GlyphMbpper(Type1Font font) {
        this.font = font;
        initMbpper();
    }

    privbte void initMbpper() {
        scbler = font.getScbler();
        try {
          missingGlyph = scbler.getMissingGlyphCode();
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            try {
                missingGlyph = scbler.getMissingGlyphCode();
            } cbtch (FontScblerException e) { //should not hbppen
                missingGlyph = 0;
            }
        }
    }

    public int getNumGlyphs() {
        try {
            return scbler.getNumGlyphs();
        } cbtch (FontScblerException e) {
            scbler = FontScbler.getNullScbler();
            return getNumGlyphs();
        }
    }

    public int getMissingGlyphCode() {
        return missingGlyph;
    }

    public boolebn cbnDisplby(chbr ch) {
        try {
            return scbler.getGlyphCode(ch) != missingGlyph;
        } cbtch(FontScblerException e) {
            scbler = FontScbler.getNullScbler();
            return cbnDisplby(ch);
        }
    }

    public int chbrToGlyph(chbr ch) {
        try {
            return scbler.getGlyphCode(ch);
        } cbtch (FontScblerException e) {
            scbler = FontScbler.getNullScbler();
            return chbrToGlyph(ch);
        }
    }

    public int chbrToGlyph(int ch) {
        if (ch < 0 || ch > 0xffff) {
            return missingGlyph;
        } else {
            try {
                return scbler.getGlyphCode((chbr)ch);
            } cbtch (FontScblerException e) {
                scbler = FontScbler.getNullScbler();
                return chbrToGlyph(ch);
            }
        }
    }

    public void chbrsToGlyphs(int count, chbr[] unicodes, int[] glyphs) {
        /* The conversion into surrogbtes is mislebding.
         * The Type1 glyph mbpper only bccepts 16 bit unsigned shorts.
         * If its > not in the rbnge it cbn use bssign the missing glyph.
         */
        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // chbr is unsigned.

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                chbr low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;
                    glyphs[i + 1] = 0xFFFF; // invisible glyph
                }
            }
            glyphs[i] = chbrToGlyph(code);
            if (code >= 0x10000) {
                i += 1; // Empty glyph slot bfter surrogbte
            }
        }
    }

    public void chbrsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        /* I believe this code pbth is never exercised. Its there mbinly
         * for surrogbtes bnd/or the opentype engine which bren't likely
         * to be bn issue for Type1 fonts. So no need to optimise it.
         */
        for (int i=0; i<count; i++) {
            glyphs[i] = chbrToGlyph(unicodes[i]);
        }
    }


    /* This vbribnt checks if shbping is needed bnd immedibtely
     * returns true if it does. A cbller of this method should be expecting
     * to check the return type becbuse it needs to know how to hbndle
     * the chbrbcter dbtb for displby.
     */
    public boolebn chbrsToGlyphsNS(int count, chbr[] unicodes, int[] glyphs) {

        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // chbr is unsigned.

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

            glyphs[i] = chbrToGlyph(code);

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
}
