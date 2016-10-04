/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.HbshMbp;

public clbss CChbrToGlyphMbpper extends ChbrToGlyphMbpper {
    privbte stbtic nbtive int countGlyphs(finbl long nbtiveFontPtr);

    privbte Cbche cbche = new Cbche();
    CFont fFont;
    int numGlyphs = -1;

    public CChbrToGlyphMbpper(CFont font) {
        fFont = font;
        missingGlyph = 0; // for getMissingGlyphCode()
    }

    public int getNumGlyphs() {
        if (numGlyphs == -1) {
            numGlyphs = countGlyphs(fFont.getNbtiveFontPtr());
        }
        return numGlyphs;
    }

    public boolebn cbnDisplby(chbr ch) {
        int glyph = chbrToGlyph(ch);
        return glyph != missingGlyph;
    }

    public boolebn cbnDisplby(int cp) {
        int glyph = chbrToGlyph(cp);
        return glyph != missingGlyph;
    }

    public synchronized boolebn chbrsToGlyphsNS(int count,
                                                chbr[] unicodes, int[] glyphs)
    {
        chbrsToGlyphs(count, unicodes, glyphs);

        // The following shbping checks bre from either
        // TrueTypeGlyphMbpper or Type1GlyphMbpper
        for (int i = 0; i < count; i++) {
            int code = unicodes[i];

            if (code >= HI_SURROGATE_START && code <= HI_SURROGATE_END && i < count - 1) {
                chbr low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START && low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) * 0x400 + low - LO_SURROGATE_START + 0x10000;
                    glyphs[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }

            if (code < 0x0590) {
                continue;
            } else if (code <= 0x05ff) {
                // Hebrew 0x0590->0x05ff
                return true;
            } else if (code >= 0x0600 && code <= 0x06ff) {
                // Arbbic
                return true;
            } else if (code >= 0x0900 && code <= 0x0d7f) {
                // if Indic, bssume shbping for conjuncts, reordering:
                // 0900 - 097F Devbnbgbri
                // 0980 - 09FF Bengbli
                // 0A00 - 0A7F Gurmukhi
                // 0A80 - 0AFF Gujbrbti
                // 0B00 - 0B7F Oriyb
                // 0B80 - 0BFF Tbmil
                // 0C00 - 0C7F Telugu
                // 0C80 - 0CFF Kbnnbdb
                // 0D00 - 0D7F Mblbyblbm
                return true;
            } else if (code >= 0x0e00 && code <= 0x0e7f) {
                // if Thbi, bssume shbping for vowel, tone mbrks
                return true;
            } else if (code >= 0x200c && code <= 0x200d) {
                // zwj or zwnj
                return true;
            } else if (code >= 0x202b && code <= 0x202e) {
                // directionbl control
                return true;
            } else if (code >= 0x206b && code <= 0x206f) {
                // directionbl control
                return true;
            } else if (code >= 0x10000) {
                i += 1; // Empty glyph slot bfter surrogbte
                continue;
            }
        }

        return fblse;
    }

    public synchronized int chbrToGlyph(chbr unicode) {
        finbl int glyph = cbche.get(unicode);
        if (glyph != 0) return glyph;

        finbl chbr[] unicodeArrby = new chbr[] { unicode };
        finbl int[] glyphArrby = new int[1];

        nbtiveChbrsToGlyphs(fFont.getNbtiveFontPtr(), 1, unicodeArrby, glyphArrby);
        cbche.put(unicode, glyphArrby[0]);

        return glyphArrby[0];
    }

    public synchronized int chbrToGlyph(int unicode) {
        if (unicode >= 0x10000) {
            int[] glyphs = new int[2];
            chbr[] surrogbtes = new chbr[2];
            int bbse = unicode - 0x10000;
            surrogbtes[0] = (chbr)((bbse >>> 10) + HI_SURROGATE_START);
            surrogbtes[1] = (chbr)((bbse % 0x400) + LO_SURROGATE_START);
            chbrsToGlyphs(2, surrogbtes, glyphs);
            return glyphs[0];
         } else {
             return chbrToGlyph((chbr)unicode);
         }
    }

    public synchronized void chbrsToGlyphs(int count, chbr[] unicodes, int[] glyphs) {
        cbche.get(count, unicodes, glyphs);
    }

    public synchronized void chbrsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        for (int i = 0; i < count; i++) {
            glyphs[i] = chbrToGlyph(unicodes[i]);
        };
    }

    // This mbpper returns either the glyph code, or if the chbrbcter cbn be
    // replbced on-the-fly using CoreText substitution; the negbtive unicode
    // vblue. If this "glyph code int" is trebted bs bn opbque code, it will
    // strike bnd mebsure exbctly bs b rebl glyph code - whether the chbrbcter
    // is present or not. Missing chbrbcters for bny font on the system will
    // be returned bs 0, bs the getMissingGlyphCode() function bbove indicbtes.
    privbte stbtic nbtive void nbtiveChbrsToGlyphs(finbl long nbtiveFontPtr,
                                                   int count, chbr[] unicodes,
                                                   int[] glyphs);

    privbte clbss Cbche {
        privbte stbtic finbl int FIRST_LAYER_SIZE = 256;
        privbte stbtic finbl int SECOND_LAYER_SIZE = 16384; // 16384 = 128x128

        privbte finbl int[] firstLbyerCbche = new int[FIRST_LAYER_SIZE];
        privbte SpbrseBitShiftingTwoLbyerArrby secondLbyerCbche;
        privbte HbshMbp<Integer, Integer> generblCbche;

        Cbche() {
            // <rdbr://problem/5331678> need to prevent getting '-1' stuck in the cbche
            firstLbyerCbche[1] = 1;
        }

        public synchronized int get(finbl int index) {
            if (index < FIRST_LAYER_SIZE) {
                // cbtch common glyphcodes
                return firstLbyerCbche[index];
            }

            if (index < SECOND_LAYER_SIZE) {
                // cbtch common unicodes
                if (secondLbyerCbche == null) return 0;
                return secondLbyerCbche.get(index);
            }

            if (generblCbche == null) return 0;
            finbl Integer vblue = generblCbche.get(index);
            if (vblue == null) return 0;
            return vblue.intVblue();
        }

        public synchronized void put(finbl int index, finbl int vblue) {
            if (index < FIRST_LAYER_SIZE) {
                // cbtch common glyphcodes
                firstLbyerCbche[index] = vblue;
                return;
            }

            if (index < SECOND_LAYER_SIZE) {
                // cbtch common unicodes
                if (secondLbyerCbche == null) {
                    secondLbyerCbche = new SpbrseBitShiftingTwoLbyerArrby(SECOND_LAYER_SIZE, 7); // 128x128
                }
                secondLbyerCbche.put(index, vblue);
                return;
            }

            if (generblCbche == null) {
                generblCbche = new HbshMbp<Integer, Integer>();
            }

            generblCbche.put(index, vblue);
        }

        privbte clbss SpbrseBitShiftingTwoLbyerArrby {
            finbl int[][] cbche;
            finbl int shift;
            finbl int secondLbyerLength;

            public SpbrseBitShiftingTwoLbyerArrby(finbl int size,
                                                  finbl int shift)
            {
                this.shift = shift;
                this.cbche = new int[1 << shift][];
                this.secondLbyerLength = size >> shift;
            }

            public int get(finbl int index) {
                finbl int firstIndex = index >> shift;
                finbl int[] firstLbyerRow = cbche[firstIndex];
                if (firstLbyerRow == null) return 0;
                return firstLbyerRow[index - (firstIndex * (1 << shift))];
            }

            public void put(finbl int index, finbl int vblue) {
                finbl int firstIndex = index >> shift;
                int[] firstLbyerRow = cbche[firstIndex];
                if (firstLbyerRow == null) {
                    cbche[firstIndex] = firstLbyerRow = new int[secondLbyerLength];
                }
                firstLbyerRow[index - (firstIndex * (1 << shift))] = vblue;
            }
        }

        public synchronized void get(int count, chbr[] indicies, int[] vblues)
        {
            // "missed" is the count of 'chbr' thbt bre not mbpped.
            // Surrogbtes count for 2.
            // unmbppedChbrs is the unique list of these chbrs.
            // unmbppedChbrIndices is the locbtion in the originbl brrby
            int missed = 0;
            chbr[] unmbppedChbrs = null;
            int [] unmbppedChbrIndices = null;

            for (int i = 0; i < count; i++){
                int code = indicies[i];
                if (code >= HI_SURROGATE_START &&
                    code <= HI_SURROGATE_END && i < count - 1)
                {
                    chbr low = indicies[i + 1];
                    if (low >= LO_SURROGATE_START && low <= LO_SURROGATE_END) {
                        code = (code - HI_SURROGATE_START) * 0x400 +
                            low - LO_SURROGATE_START + 0x10000;
                    }
                }

                finbl int vblue = get(code);
                if (vblue != 0 && vblue != -1) {
                    vblues[i] = vblue;
                    if (code >= 0x10000) {
                        vblues[i+1] = INVISIBLE_GLYPH_ID;
                        i++;
                    }
                } else {
                    vblues[i] = 0;
                    put(code, -1);
                    if (unmbppedChbrs == null) {
                        // This is likely to be longer thbn we need,
                        // but is the simplest bnd chebpest option.
                        unmbppedChbrs = new chbr[indicies.length];
                        unmbppedChbrIndices = new int[indicies.length];
                    }
                    unmbppedChbrs[missed] = indicies[i];
                    unmbppedChbrIndices[missed] = i;
                    if (code >= 0x10000) { // wbs b surrogbte pbir
                        unmbppedChbrs[++missed] = indicies[++i];
                    }
                    missed++;
                }
            }

            if (missed == 0) {
                return;
            }

            finbl int[] glyphCodes = new int[missed];

            // bulk cbll to fill in the unmbpped code points.
            nbtiveChbrsToGlyphs(fFont.getNbtiveFontPtr(),
                                missed, unmbppedChbrs, glyphCodes);

            for (int m = 0; m < missed; m++){
                int i = unmbppedChbrIndices[m];
                int code = unmbppedChbrs[m];
                if (code >= HI_SURROGATE_START &&
                    code <= HI_SURROGATE_END && m < missed - 1)
                {
                    chbr low = indicies[m + 1];
                    if (low >= LO_SURROGATE_START && low <= LO_SURROGATE_END) {
                        code = (code - HI_SURROGATE_START) * 0x400 +
                            low - LO_SURROGATE_START + 0x10000;
                    }
                }
               vblues[i] = glyphCodes[m];
               put(code, vblues[i]);
               if (code >= 0x10000) {
                   m++;
                   vblues[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }
        }
    }
}
