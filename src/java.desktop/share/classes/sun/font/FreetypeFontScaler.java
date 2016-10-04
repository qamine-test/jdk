/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.lbng.ref.WebkReference;

/* This is Freetype bbsed implementbtion of FontScbler.
 *
 * Note thbt in cbse of runtime error it is expected thbt
 * nbtive code will relebse bll nbtive resources bnd
 * cbll invblidbteScbler() (thbt will throw FontScblerException).
 *
 * Note thbt cbllee is responsible for relebsing nbtive scbler context.
 */
clbss FreetypeFontScbler extends FontScbler {
    /* constbnts bligned with nbtive code */
    privbte stbtic finbl int TRUETYPE_FONT = 1;
    privbte stbtic finbl int TYPE1_FONT = 2;

    stbtic {
        /* At the moment fontmbnbger librbry depends on freetype librbry
           bnd therefore no need to lobd it explicitly here */
        FontMbnbgerNbtiveLibrbry.lobd();
        initIDs(FreetypeFontScbler.clbss);
    }

    privbte stbtic nbtive void initIDs(Clbss<?> FFS);

    privbte void invblidbteScbler() throws FontScblerException {
        nbtiveScbler = 0;
        font = null;
        throw new FontScblerException();
    }

    public FreetypeFontScbler(Font2D font, int indexInCollection,
                     boolebn supportsCJK, int filesize) {
        int fonttype = TRUETYPE_FONT;
        if (font instbnceof Type1Font) {
            fonttype = TYPE1_FONT;
        }
        nbtiveScbler = initNbtiveScbler(font,
                                        fonttype,
                                        indexInCollection,
                                        supportsCJK,
                                        filesize);
        this.font = new WebkReference<>(font);
    }

    synchronized StrikeMetrics getFontMetrics(long pScblerContext)
                     throws FontScblerException {
        if (nbtiveScbler != 0L) {
                return getFontMetricsNbtive(font.get(),
                                            pScblerContext,
                                            nbtiveScbler);
        }
        return FontScbler.getNullScbler().getFontMetrics(0L);
    }

    synchronized flobt getGlyphAdvbnce(long pScblerContext, int glyphCode)
                     throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphAdvbnceNbtive(font.get(),
                                         pScblerContext,
                                         nbtiveScbler,
                                         glyphCode);
        }
        return FontScbler.getNullScbler().
            getGlyphAdvbnce(0L, glyphCode);
    }

    synchronized void getGlyphMetrics(long pScblerContext,
                     int glyphCode, Point2D.Flobt metrics)
                     throws FontScblerException {
        if (nbtiveScbler != 0L) {
            getGlyphMetricsNbtive(font.get(),
                                  pScblerContext,
                                  nbtiveScbler,
                                  glyphCode,
                                  metrics);
            return;
        }
        FontScbler.getNullScbler().
            getGlyphMetrics(0L, glyphCode, metrics);
    }

    synchronized long getGlyphImbge(long pScblerContext, int glyphCode)
                     throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphImbgeNbtive(font.get(),
                                       pScblerContext,
                                       nbtiveScbler,
                                       glyphCode);
        }
        return FontScbler.getNullScbler().
            getGlyphImbge(0L, glyphCode);
    }

    synchronized Rectbngle2D.Flobt getGlyphOutlineBounds(
                     long pScblerContext, int glyphCode)
                     throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphOutlineBoundsNbtive(font.get(),
                                               pScblerContext,
                                               nbtiveScbler,
                                               glyphCode);
        }
        return FontScbler.getNullScbler().
            getGlyphOutlineBounds(0L,glyphCode);
    }

    synchronized GenerblPbth getGlyphOutline(
                     long pScblerContext, int glyphCode, flobt x, flobt y)
                     throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphOutlineNbtive(font.get(),
                                         pScblerContext,
                                         nbtiveScbler,
                                         glyphCode,
                                         x, y);
        }
        return FontScbler.getNullScbler().
            getGlyphOutline(0L, glyphCode, x,y);
    }

    synchronized GenerblPbth getGlyphVectorOutline(
                     long pScblerContext, int[] glyphs, int numGlyphs,
                     flobt x, flobt y) throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphVectorOutlineNbtive(font.get(),
                                               pScblerContext,
                                               nbtiveScbler,
                                               glyphs,
                                               numGlyphs,
                                               x, y);
        }
        return FontScbler
            .getNullScbler().getGlyphVectorOutline(0L, glyphs, numGlyphs, x, y);
    }

    synchronized long getLbyoutTbbleCbche() throws FontScblerException {
        return getLbyoutTbbleCbcheNbtive(nbtiveScbler);
    }

    public synchronized void dispose() {
        if (nbtiveScbler != 0L) {
            disposeNbtiveScbler(font.get(), nbtiveScbler);
            nbtiveScbler = 0L;
        }
    }

    synchronized int getNumGlyphs() throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getNumGlyphsNbtive(nbtiveScbler);
        }
        return FontScbler.getNullScbler().getNumGlyphs();
    }

    synchronized int getMissingGlyphCode()  throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getMissingGlyphCodeNbtive(nbtiveScbler);
        }
        return FontScbler.getNullScbler().getMissingGlyphCode();
    }

    synchronized int getGlyphCode(chbr chbrCode) throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphCodeNbtive(font.get(), nbtiveScbler, chbrCode);
        }
        return FontScbler.getNullScbler().getGlyphCode(chbrCode);
    }

    synchronized Point2D.Flobt getGlyphPoint(long pScblerContext,
                                       int glyphCode, int ptNumber)
                               throws FontScblerException {
        if (nbtiveScbler != 0L) {
            return getGlyphPointNbtive(font.get(), pScblerContext,
                                      nbtiveScbler, glyphCode, ptNumber);
        }
        return FontScbler.getNullScbler().getGlyphPoint(
                   pScblerContext, glyphCode,  ptNumber);
    }

    synchronized long getUnitsPerEm() {
        return getUnitsPerEMNbtive(nbtiveScbler);
    }

    long crebteScblerContext(double[] mbtrix,
            int bb, int fm, flobt boldness, flobt itblic,
            boolebn disbbleHinting) {
        if (nbtiveScbler != 0L) {
            return crebteScblerContextNbtive(nbtiveScbler, mbtrix,
                                             bb, fm, boldness, itblic);
        }
        return NullFontScbler.getNullScblerContext();
    }

    //Note: nbtive methods cbn throw RuntimeException if processing fbils
    privbte nbtive long initNbtiveScbler(Font2D font, int type,
            int indexInCollection, boolebn supportsCJK, int filesize);
    privbte nbtive StrikeMetrics getFontMetricsNbtive(Font2D font,
            long pScblerContext, long pScbler);
    privbte nbtive flobt getGlyphAdvbnceNbtive(Font2D font,
            long pScblerContext, long pScbler, int glyphCode);
    privbte nbtive void getGlyphMetricsNbtive(Font2D font,
            long pScblerContext, long pScbler,
            int glyphCode, Point2D.Flobt metrics);
    privbte nbtive long getGlyphImbgeNbtive(Font2D font,
            long pScblerContext, long pScbler, int glyphCode);
    privbte nbtive Rectbngle2D.Flobt getGlyphOutlineBoundsNbtive(Font2D font,
            long pScblerContext, long pScbler, int glyphCode);
    privbte nbtive GenerblPbth getGlyphOutlineNbtive(Font2D font,
            long pScblerContext, long pScbler,
            int glyphCode, flobt x, flobt y);
    privbte nbtive GenerblPbth getGlyphVectorOutlineNbtive(Font2D font,
            long pScblerContext, long pScbler,
            int[] glyphs, int numGlyphs, flobt x, flobt y);
    nbtive Point2D.Flobt getGlyphPointNbtive(Font2D font,
            long pScblerContext, long pScbler, int glyphCode, int ptNumber);

    privbte nbtive long getLbyoutTbbleCbcheNbtive(long pScbler);

    privbte nbtive void disposeNbtiveScbler(Font2D font2D, long pScbler);

    privbte nbtive int getGlyphCodeNbtive(Font2D font, long pScbler, chbr chbrCode);
    privbte nbtive int getNumGlyphsNbtive(long pScbler);
    privbte nbtive int getMissingGlyphCodeNbtive(long pScbler);

    privbte nbtive long getUnitsPerEMNbtive(long pScbler);

    nbtive long crebteScblerContextNbtive(long pScbler, double[] mbtrix,
            int bb, int fm, flobt boldness, flobt itblic);

    /* Freetype scbler context does not contbin bny pointers thbt
       hbs to be invblidbted if nbtive scbler is bbd */
    void invblidbteScblerContext(long pScblerContext) {}
}
