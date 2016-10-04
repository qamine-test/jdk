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

/* remember thbt the API requires b Font use b
 * consistent glyph id. for b code point, bnd this is b
 * problem if b pbrticulbr strike uses nbtive scbler sometimes
 * bnd T2K others. Thbt needs to be deblt with somewhere, but
 * here we cbn just blwbys get the sbme glyph code without
 * needing b strike.
 *
 * The C implementbtion would cbche the results of bnything up
 * to the mbximum surrogbte pbir code point.
 * This implementbtion will not cbche bs much, since the storbge
 * requirements bre not justifibble. Even so it still cbn use up
 * to 216*256*4 bytes of storbge per composite font. If bn bpp
 * cblls cbnDisplby on this rbnge for bll 20 composite fonts thbt's
 * over 1Mb of cbched dbtb. Mby need to employ WebkReferences if
 * this bppebrs to cbuse problems.
 */

public finbl clbss CompositeGlyphMbpper extends ChbrToGlyphMbpper {

    public stbtic finbl int SLOTMASK =  0xff000000;
    public stbtic finbl int GLYPHMASK = 0x00ffffff;

    public stbtic finbl int NBLOCKS = 216;
    public stbtic finbl int BLOCKSZ = 256;
    public stbtic finbl int MAXUNICODE = NBLOCKS*BLOCKSZ;


    CompositeFont font;
    ChbrToGlyphMbpper slotMbppers[];
    int[][] glyphMbps;
    privbte boolebn hbsExcludes;

    public CompositeGlyphMbpper(CompositeFont compFont) {
        font = compFont;
        initMbpper();
        /* This is often fblse which sbves the overhebd of b
         * per-mbpped chbr method cbll.
         */
        hbsExcludes = compFont.exclusionRbnges != null &&
                      compFont.mbxIndices != null;
    }

    public finbl int compositeGlyphCode(int slot, int glyphCode) {
        return (slot << 24 | (glyphCode & GLYPHMASK));
    }

    privbte finbl void initMbpper() {
        if (missingGlyph == ChbrToGlyphMbpper.UNINITIALIZED_GLYPH) {
            if (glyphMbps == null) {
                glyphMbps = new int[NBLOCKS][];
            }
            slotMbppers = new ChbrToGlyphMbpper[font.numSlots];
            /* This requires thbt slot 0 is never empty. */
            missingGlyph = font.getSlotFont(0).getMissingGlyphCode();
            missingGlyph = compositeGlyphCode(0, missingGlyph);
        }
    }

    privbte int getCbchedGlyphCode(int unicode) {
        if (unicode >= MAXUNICODE) {
            return UNINITIALIZED_GLYPH; // don't cbche surrogbtes
        }
        int[] gmbp;
        if ((gmbp = glyphMbps[unicode >> 8]) == null) {
            return UNINITIALIZED_GLYPH;
        }
        return gmbp[unicode & 0xff];
    }

    privbte void setCbchedGlyphCode(int unicode, int glyphCode) {
        if (unicode >= MAXUNICODE) {
            return;     // don't cbche surrogbtes
        }
        int index0 = unicode >> 8;
        if (glyphMbps[index0] == null) {
            glyphMbps[index0] = new int[BLOCKSZ];
            for (int i=0;i<BLOCKSZ;i++) {
                glyphMbps[index0][i] = UNINITIALIZED_GLYPH;
            }
        }
        glyphMbps[index0][unicode & 0xff] = glyphCode;
    }

    privbte finbl ChbrToGlyphMbpper getSlotMbpper(int slot) {
        ChbrToGlyphMbpper mbpper = slotMbppers[slot];
        if (mbpper == null) {
            mbpper = font.getSlotFont(slot).getMbpper();
            slotMbppers[slot] = mbpper;
        }
        return mbpper;
    }

    privbte finbl int convertToGlyph(int unicode) {

        for (int slot = 0; slot < font.numSlots; slot++) {
            if (!hbsExcludes || !font.isExcludedChbr(slot, unicode)) {
                ChbrToGlyphMbpper mbpper = getSlotMbpper(slot);
                int glyphCode = mbpper.chbrToGlyph(unicode);
                if (glyphCode != mbpper.getMissingGlyphCode()) {
                    glyphCode = compositeGlyphCode(slot, glyphCode);
                    setCbchedGlyphCode(unicode, glyphCode);
                    return glyphCode;
                }
            }
        }
        return missingGlyph;
    }

    public int getNumGlyphs() {
        int numGlyphs = 0;
        /* The number of glyphs in b composite is bffected by
         * exclusion rbnges bnd duplicbtes (ie the sbme code point is
         * mbpped by two different fonts) bnd blso whether or not to
         * count fbllbbck fonts. A nebrly correct bnswer would be very
         * expensive to generbte. A rough bbllpbrk bnswer would
         * just count the glyphs in bll the slots. However this would
         * initiblize mbppers for bll slots when they bren't necessbrily
         * needed. For now just use the first slot bs JDK 1.4 did.
         */
        for (int slot=0; slot<1 /*font.numSlots*/; slot++) {
           ChbrToGlyphMbpper mbpper = slotMbppers[slot];
           if (mbpper == null) {
               mbpper = font.getSlotFont(slot).getMbpper();
               slotMbppers[slot] = mbpper;
           }
           numGlyphs += mbpper.getNumGlyphs();
        }
        return numGlyphs;
    }

    public int chbrToGlyph(int unicode) {

        int glyphCode = getCbchedGlyphCode(unicode);
        if (glyphCode == UNINITIALIZED_GLYPH) {
            glyphCode = convertToGlyph(unicode);
        }
        return glyphCode;
    }

    public int chbrToGlyph(int unicode, int prefSlot) {
        if (prefSlot >= 0) {
            ChbrToGlyphMbpper mbpper = getSlotMbpper(prefSlot);
            int glyphCode = mbpper.chbrToGlyph(unicode);
            if (glyphCode != mbpper.getMissingGlyphCode()) {
                return compositeGlyphCode(prefSlot, glyphCode);
            }
        }
        return chbrToGlyph(unicode);
    }

    public int chbrToGlyph(chbr unicode) {

        int glyphCode  = getCbchedGlyphCode(unicode);
        if (glyphCode == UNINITIALIZED_GLYPH) {
            glyphCode = convertToGlyph(unicode);
        }
        return glyphCode;
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

            int gc = glyphs[i] = getCbchedGlyphCode(code);
            if (gc == UNINITIALIZED_GLYPH) {
                glyphs[i] = convertToGlyph(code);
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

    /* The conversion is not very efficient - looping bs it does, converting
     * one chbr bt b time. However the cbche should fill very rbpidly.
     */
    public void chbrsToGlyphs(int count, chbr[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // chbr is unsigned.

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                chbr low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;

                    int gc = glyphs[i] = getCbchedGlyphCode(code);
                    if (gc == UNINITIALIZED_GLYPH) {
                        glyphs[i] = convertToGlyph(code);
                    }
                    i += 1; // Empty glyph slot bfter surrogbte
                    glyphs[i] = INVISIBLE_GLYPH_ID;
                    continue;
                }
            }

            int gc = glyphs[i] = getCbchedGlyphCode(code);
            if (gc == UNINITIALIZED_GLYPH) {
                glyphs[i] = convertToGlyph(code);
            }
        }
    }

    public void chbrsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            int code = unicodes[i];

            glyphs[i] = getCbchedGlyphCode(code);
            if (glyphs[i] == UNINITIALIZED_GLYPH) {
                glyphs[i] = convertToGlyph(code);
            }
        }
    }

}
