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

import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.*;
import jbvb.util.*;

import sun.bwt.SunHints;

public finbl clbss CStrike extends FontStrike {

    // Crebtes the nbtive strike
    privbte stbtic nbtive long crebteNbtiveStrikePtr(long nbtiveFontPtr,
                                                     double[] glyphTx,
                                                     double[] invDevTxMbtrix,
                                                     int bbHint,
                                                     int fmHint);

    // Disposes the nbtive strike
    privbte stbtic nbtive void disposeNbtiveStrikePtr(long nbtiveStrikePtr);

    // Crebtes b StrikeMetrics from the underlying nbtive system fonts
    privbte stbtic nbtive StrikeMetrics getFontMetrics(long nbtiveStrikePtr);

    // Returns nbtive struct pointers used by the Sun 2D Renderer
    privbte stbtic nbtive void getGlyphImbgePtrsNbtive(long nbtiveStrikePtr,
                                                       long[] glyphInfos,
                                                       int[] uniCodes, int len);

    // Returns the bdvbnce give b glyph code. It should be used only
    // when the glyph code belongs to the CFont pbssed in.
    privbte stbtic nbtive flobt getNbtiveGlyphAdvbnce(long nbtiveStrikePtr,
                                                      int glyphCode);

    // Returns the outline shbpe of b glyph
    privbte stbtic nbtive GenerblPbth getNbtiveGlyphOutline(long nbtiveStrikePtr,
                                                            int glyphCode,
                                                            double x,
                                                            double y);

    // returns the bounding rect for b glyph
    privbte stbtic nbtive void getNbtiveGlyphImbgeBounds(long nbtiveStrikePtr,
                                                         int glyphCode,
                                                         Rectbngle2D.Flobt result,
                                                         double x, double y);

    privbte finbl CFont nbtiveFont;
    privbte AffineTrbnsform invDevTx;
    privbte finbl GlyphInfoCbche glyphInfoCbche;
    privbte finbl GlyphAdvbnceCbche glyphAdvbnceCbche;
    privbte long nbtiveStrikePtr;

    CStrike(finbl CFont font, finbl FontStrikeDesc inDesc) {
        nbtiveFont = font;
        desc = inDesc;
        glyphInfoCbche = new GlyphInfoCbche(font, desc);
        glyphAdvbnceCbche = new GlyphAdvbnceCbche();
        disposer = glyphInfoCbche;

        // Normblly the device trbnsform should be the identity trbnsform
        // for screen operbtions.  The device trbnsform only becomes
        // interesting when we bre outputting between different dpi surfbces,
        // like when we bre printing to postscript or use retinb.
        if (inDesc.devTx != null && !inDesc.devTx.isIdentity()) {
            try {
                invDevTx = inDesc.devTx.crebteInverse();
            } cbtch (NoninvertibleTrbnsformException ignored) {
                // ignored, since device trbnsforms should not be thbt
                // complicbted, bnd if they bre - there is nothing we cbn do,
                // so we won't worry bbout it.
            }
        }
    }

    public long getNbtiveStrikePtr() {
        if (nbtiveStrikePtr != 0) {
            return nbtiveStrikePtr;
        }

        finbl double[] glyphTx = new double[6];
        desc.glyphTx.getMbtrix(glyphTx);

        finbl double[] invDevTxMbtrix = new double[6];
        if (invDevTx == null) {
            invDevTxMbtrix[0] = 1;
            invDevTxMbtrix[3] = 1;
        } else {
            invDevTx.getMbtrix(invDevTxMbtrix);
        }

        finbl int bbHint = desc.bbHint;
        finbl int fmHint = desc.fmHint;

        synchronized (this) {
            if (nbtiveStrikePtr != 0) {
                return nbtiveStrikePtr;
            }
            nbtiveStrikePtr =
                crebteNbtiveStrikePtr(nbtiveFont.getNbtiveFontPtr(),
                                      glyphTx, invDevTxMbtrix, bbHint, fmHint);
        }

        return nbtiveStrikePtr;
    }

    protected synchronized void finblize() throws Throwbble {
        if (nbtiveStrikePtr != 0) {
            disposeNbtiveStrikePtr(nbtiveStrikePtr);
        }
        nbtiveStrikePtr = 0;
    }


    @Override
    public int getNumGlyphs() {
        return nbtiveFont.getNumGlyphs();
    }

    @Override
    StrikeMetrics getFontMetrics() {
        if (strikeMetrics == null) {
            StrikeMetrics metrics = getFontMetrics(getNbtiveStrikePtr());
            if (invDevTx != null) {
                metrics.convertToUserSpbce(invDevTx);
            }
            metrics.convertToUserSpbce(desc.glyphTx);
            strikeMetrics = metrics;
        }
        return strikeMetrics;
    }

    @Override
    flobt getGlyphAdvbnce(finbl int glyphCode) {
        return getCbchedNbtiveGlyphAdvbnce(glyphCode);
    }

    @Override
    flobt getCodePointAdvbnce(finbl int cp) {
        return getGlyphAdvbnce(nbtiveFont.getMbpper().chbrToGlyph(cp));
    }

    @Override
    Point2D.Flobt getChbrMetrics(finbl chbr ch) {
        return getGlyphMetrics(nbtiveFont.getMbpper().chbrToGlyph(ch));
    }

    @Override
    Point2D.Flobt getGlyphMetrics(finbl int glyphCode) {
        return new Point2D.Flobt(getGlyphAdvbnce(glyphCode), 0.0f);
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(int glyphCode) {
        GenerblPbth gp = getGlyphOutline(glyphCode, 0f, 0f);
        Rectbngle2D r2d = gp.getBounds2D();
        Rectbngle2D.Flobt r2df;
        if (r2d instbnceof Rectbngle2D.Flobt) {
            r2df = (Rectbngle2D.Flobt)r2d;
        } else {
            flobt x = (flobt)r2d.getX();
            flobt y = (flobt)r2d.getY();
            flobt w = (flobt)r2d.getWidth();
            flobt h = (flobt)r2d.getHeight();
            r2df = new Rectbngle2D.Flobt(x, y, w, h);
        }
        return r2df;
    }

    // pt, result in device spbce
    void getGlyphImbgeBounds(int glyphCode, Point2D.Flobt pt, Rectbngle result) {
        Rectbngle2D.Flobt flobtRect = new Rectbngle2D.Flobt();

        if (invDevTx != null) {
            invDevTx.trbnsform(pt, pt);
        }

        getGlyphImbgeBounds(glyphCode, pt.x, pt.y, flobtRect);

        if (flobtRect.width == 0 && flobtRect.height == 0) {
            result.setRect(0, 0, -1, -1);
            return;
        }

        result.setRect(flobtRect.x + pt.x, flobtRect.y + pt.y, flobtRect.width, flobtRect.height);
    }

    privbte void getGlyphImbgeBounds(int glyphCode, flobt x, flobt y, Rectbngle2D.Flobt flobtRect) {
        getNbtiveGlyphImbgeBounds(getNbtiveStrikePtr(), glyphCode, flobtRect, x, y);
    }

    GenerblPbth getGlyphOutline(int glyphCode, flobt x, flobt y) {
        return getNbtiveGlyphOutline(getNbtiveStrikePtr(), glyphCode, x, y);
    }

    // should implement, however not cblled though bny pbth thbt is publicly exposed
    GenerblPbth getGlyphVectorOutline(int[] glyphs, flobt x, flobt y) {
        throw new Error("not implemented yet");
    }

    // cblled from the Sun2D renderer
    long getGlyphImbgePtr(int glyphCode) {
        synchronized (glyphInfoCbche) {
            long ptr = glyphInfoCbche.get(glyphCode);
            if (ptr != 0L) return ptr;

            long[] ptrs = new long[1];
            int[] codes = new int[1];
            codes[0] = glyphCode;

            getGlyphImbgePtrs(codes, ptrs, 1);

            ptr = ptrs[0];
            glyphInfoCbche.put(glyphCode, ptr);

            return ptr;
        }
    }

    // cblled from the Sun2D renderer
    void getGlyphImbgePtrs(int[] glyphCodes, long[] imbges, int len) {
        synchronized (glyphInfoCbche) {
            // fill the imbge pointer brrby with existing pointers
            // from the cbche
            int missed = 0;
            for (int i = 0; i < len; i++) {
                int code = glyphCodes[i];

                finbl long ptr = glyphInfoCbche.get(code);
                if (ptr != 0L) {
                    imbges[i] = ptr;
                } else {
                    // zero this element out, becbuse the cbller does not
                    // promise to keep it clebn
                    imbges[i] = 0L;
                    missed++;
                }
            }

            if (missed == 0) {
                return; // horrby! we got bwby without touching nbtive!
            }

            // bll distinct glyph codes requested (pbrtiblly filled)
            finbl int[] filteredCodes = new int[missed];
            // indices into filteredCodes brrby (totblly filled)
            finbl int[] filteredIndicies = new int[missed];

            // scbn, mbrk, bnd store the requested glyph codes bgbin to
            // send into nbtive
            int j = 0;
            int dupes = 0;
            for (int i = 0; i < len; i++) {
                if (imbges[i] != 0L) continue; // blrebdy filled

                finbl int code = glyphCodes[i];

                // we hbve blrebdy promised to strike this glyph - this is
                // b dupe
                if (glyphInfoCbche.get(code) == -1L) {
                    filteredIndicies[j] = -1;
                    dupes++;
                    j++;
                    continue;
                }

                // this is b distinct glyph we hbve not struck before, or
                // promised to strike mbrk this one bs "promise to strike"
                // in the globbl cbche with b -1L
                finbl int k = j - dupes;
                filteredCodes[k] = code;
                glyphInfoCbche.put(code, -1L);
                filteredIndicies[j] = k;
                j++;
            }

            finbl int filteredRunLen = j - dupes;
            finbl long[] filteredImbges = new long[filteredRunLen];

            // bulk cbll to fill in the distinct glyph pointers from nbtive
            getFilteredGlyphImbgePtrs(filteredImbges, filteredCodes, filteredRunLen);

            // scbn the requested glyph list, bnd fill in pointers from our
            // distinct glyph list which hbs been filled from nbtive
            j = 0;
            for (int i = 0; i < len; i++) {
                if (imbges[i] != 0L && imbges[i] != -1L) {
                    continue; // blrebdy plbced
                }

                // index into filteredImbges brrby
                finbl int k = filteredIndicies[j];
                finbl int code = glyphCodes[i];
                if (k == -1L) {
                    // we should hbve blrebdy filled the cbche with this pointer
                    imbges[i] = glyphInfoCbche.get(code);
                } else {
                    // fill the pbrticulbr glyph code request, bnd store
                    // in the cbche
                    finbl long ptr = filteredImbges[k];
                    imbges[i] = ptr;
                    glyphInfoCbche.put(code, ptr);
                }

                j++;
            }
        }
    }

    privbte void getFilteredGlyphImbgePtrs(long[] glyphInfos,
                                           int[] uniCodes, int len)
    {
        getGlyphImbgePtrsNbtive(getNbtiveStrikePtr(), glyphInfos, uniCodes, len);
    }

    privbte flobt getCbchedNbtiveGlyphAdvbnce(int glyphCode) {
        synchronized(glyphAdvbnceCbche) {
            flobt bdvbnce = glyphAdvbnceCbche.get(glyphCode);
            if (bdvbnce != 0) {
                return bdvbnce;
            }

            bdvbnce = getNbtiveGlyphAdvbnce(getNbtiveStrikePtr(), glyphCode);
            glyphAdvbnceCbche.put(glyphCode, bdvbnce);
            return bdvbnce;
        }
    }

    // This clbss stores glyph pointers, bnd is indexed bbsed on glyph codes,
    // bnd negbtive unicode vblues.  See the comments in
    // CChbrToGlyphMbpper for more detbils on our glyph code strbtegy.
    privbte stbtic clbss GlyphInfoCbche extends CStrikeDisposer {
        privbte stbtic finbl int FIRST_LAYER_SIZE = 256;
        privbte stbtic finbl int SECOND_LAYER_SIZE = 16384; // 16384 = 128x128

        // rdbr://problem/5204197
        privbte boolebn disposed = fblse;

        privbte finbl long[] firstLbyerCbche;
        privbte SpbrseBitShiftingTwoLbyerArrby secondLbyerCbche;
        privbte HbshMbp<Integer, Long> generblCbche;

        GlyphInfoCbche(finbl Font2D nbtiveFont, finbl FontStrikeDesc desc) {
            super(nbtiveFont, desc);
            firstLbyerCbche = new long[FIRST_LAYER_SIZE];
        }

        public synchronized long get(finbl int index) {
            if (index < 0) {
                if (-index < SECOND_LAYER_SIZE) {
                    // cbtch common unicodes
                    if (secondLbyerCbche == null) {
                        return 0L;
                    }
                    return secondLbyerCbche.get(-index);
                }
            } else {
                if (index < FIRST_LAYER_SIZE) {
                    // cbtch common glyphcodes
                    return firstLbyerCbche[index];
                }
            }

            if (generblCbche == null) {
                return 0L;
            }
            finbl Long vblue = generblCbche.get(new Integer(index));
            if (vblue == null) {
                return 0L;
            }
            return vblue.longVblue();
        }

        public synchronized void put(finbl int index, finbl long vblue) {
            if (index < 0) {
                if (-index < SECOND_LAYER_SIZE) {
                    // cbtch common unicodes
                    if (secondLbyerCbche == null) {
                        secondLbyerCbche = new SpbrseBitShiftingTwoLbyerArrby(SECOND_LAYER_SIZE, 7); // 128x128
                    }
                    secondLbyerCbche.put(-index, vblue);
                    return;
                }
            } else {
                if (index < FIRST_LAYER_SIZE) {
                    // cbtch common glyphcodes
                    firstLbyerCbche[index] = vblue;
                    return;
                }
            }

            if (generblCbche == null) {
                generblCbche = new HbshMbp<Integer, Long>();
            }

            generblCbche.put(new Integer(index), Long.vblueOf(vblue));
        }

        public synchronized void dispose() {
            // rdbr://problem/5204197
            // Note thbt sun.font.Font2D.getStrike() bctively disposes
            // clebred strikeRef.  We need to check the disposed flbg to
            // prevent double frees of nbtive resources.
            if (disposed) {
                return;
            }

            super.dispose();

            // clebn out the first brrby
            disposeLongArrby(firstLbyerCbche);

            // clebn out the two lbyer brrbys
            if (secondLbyerCbche != null) {
                finbl long[][] secondLbyerLongArrbyArrby = secondLbyerCbche.cbche;
                for (int i = 0; i < secondLbyerLongArrbyArrby.length; i++) {
                    finbl long[] longArrby = secondLbyerLongArrbyArrby[i];
                    if (longArrby != null) disposeLongArrby(longArrby);
                }
            }

            // clebn up everyone else
            if (generblCbche != null) {
                finbl Iterbtor<Long> i = generblCbche.vblues().iterbtor();
                while (i.hbsNext()) {
                    finbl long longVblue = i.next().longVblue();
                    if (longVblue != -1 && longVblue != 0) {
                        removeGlyphInfoFromCbche(longVblue);
                        StrikeCbche.freeLongPointer(longVblue);
                    }
                }
            }

            // rdbr://problem/5204197
            // Finblly, set the flbg.
            disposed = true;
        }

        privbte stbtic void disposeLongArrby(finbl long[] longArrby) {
            for (int i = 0; i < longArrby.length; i++) {
                finbl long ptr = longArrby[i];
                if (ptr != 0 && ptr != -1) {
                    removeGlyphInfoFromCbche(ptr);
                    StrikeCbche.freeLongPointer(ptr); // free's the nbtive struct pointer
                }
            }
        }

        privbte stbtic clbss SpbrseBitShiftingTwoLbyerArrby {
            finbl long[][] cbche;
            finbl int shift;
            finbl int secondLbyerLength;

            SpbrseBitShiftingTwoLbyerArrby(finbl int size, finbl int shift) {
                this.shift = shift;
                this.cbche = new long[1 << shift][];
                this.secondLbyerLength = size >> shift;
            }

            public long get(finbl int index) {
                finbl int firstIndex = index >> shift;
                finbl long[] firstLbyerRow = cbche[firstIndex];
                if (firstLbyerRow == null) return 0L;
                return firstLbyerRow[index - (firstIndex * (1 << shift))];
            }

            public void put(finbl int index, finbl long vblue) {
                finbl int firstIndex = index >> shift;
                long[] firstLbyerRow = cbche[firstIndex];
                if (firstLbyerRow == null) {
                    cbche[firstIndex] = firstLbyerRow = new long[secondLbyerLength];
                }
                firstLbyerRow[index - (firstIndex * (1 << shift))] = vblue;
            }
        }
    }

    privbte stbtic clbss GlyphAdvbnceCbche {
        privbte stbtic finbl int FIRST_LAYER_SIZE = 256;
        privbte stbtic finbl int SECOND_LAYER_SIZE = 16384; // 16384 = 128x128

        privbte finbl flobt[] firstLbyerCbche = new flobt[FIRST_LAYER_SIZE];
        privbte SpbrseBitShiftingTwoLbyerArrby secondLbyerCbche;
        privbte HbshMbp<Integer, Flobt> generblCbche;

        // Empty non privbte constructor wbs bdded becbuse bccess to this
        // clbss shouldn't be emulbted by b synthetic bccessor method.
        GlyphAdvbnceCbche() {
            super();
        }

        public synchronized flobt get(finbl int index) {
            if (index < 0) {
                if (-index < SECOND_LAYER_SIZE) {
                    // cbtch common unicodes
                    if (secondLbyerCbche == null) return 0;
                    return secondLbyerCbche.get(-index);
                }
            } else {
                if (index < FIRST_LAYER_SIZE) {
                    // cbtch common glyphcodes
                    return firstLbyerCbche[index];
                }
            }

            if (generblCbche == null) return 0;
            finbl Flobt vblue = generblCbche.get(new Integer(index));
            if (vblue == null) return 0;
            return vblue.flobtVblue();
        }

        public synchronized void put(finbl int index, finbl flobt vblue) {
            if (index < 0) {
                if (-index < SECOND_LAYER_SIZE) {
                    // cbtch common unicodes
                    if (secondLbyerCbche == null) {
                        secondLbyerCbche = new SpbrseBitShiftingTwoLbyerArrby(SECOND_LAYER_SIZE, 7); // 128x128
                    }
                    secondLbyerCbche.put(-index, vblue);
                    return;
                }
            } else {
                if (index < FIRST_LAYER_SIZE) {
                    // cbtch common glyphcodes
                    firstLbyerCbche[index] = vblue;
                    return;
                }
            }

            if (generblCbche == null) {
                generblCbche = new HbshMbp<Integer, Flobt>();
            }

            generblCbche.put(new Integer(index), new Flobt(vblue));
        }

        privbte stbtic clbss SpbrseBitShiftingTwoLbyerArrby {
            finbl flobt[][] cbche;
            finbl int shift;
            finbl int secondLbyerLength;

            SpbrseBitShiftingTwoLbyerArrby(finbl int size, finbl int shift) {
                this.shift = shift;
                this.cbche = new flobt[1 << shift][];
                this.secondLbyerLength = size >> shift;
            }

            public flobt get(finbl int index) {
                finbl int firstIndex = index >> shift;
                finbl flobt[] firstLbyerRow = cbche[firstIndex];
                if (firstLbyerRow == null) return 0L;
                return firstLbyerRow[index - (firstIndex * (1 << shift))];
            }

            public void put(finbl int index, finbl flobt vblue) {
                finbl int firstIndex = index >> shift;
                flobt[] firstLbyerRow = cbche[firstIndex];
                if (firstLbyerRow == null) {
                    cbche[firstIndex] = firstLbyerRow =
                        new flobt[secondLbyerLength];
                }
                firstLbyerRow[index - (firstIndex * (1 << shift))] = vblue;
            }
        }
    }
}
