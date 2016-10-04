/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

clbss NullFontScbler extends FontScbler {
    NullFontScbler() {}

    public NullFontScbler(Font2D font, int indexInCollection,
        boolebn supportsCJK, int filesize) {}

    StrikeMetrics getFontMetrics(long pScblerContext) {
        return new StrikeMetrics(0xf0,0xf0,0xf0,0xf0,0xf0,0xf0,
        0xf0,0xf0,0xf0,0xf0);
    }

    flobt getGlyphAdvbnce(long pScblerContext, int glyphCode) {
        return 0.0f;
    }

    void getGlyphMetrics(long pScblerContext, int glyphCode,
        Point2D.Flobt metrics) {
        metrics.x = 0;
        metrics.y = 0;
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(long pContext, int glyphCode) {
        return new Rectbngle2D.Flobt(0, 0, 0, 0);
    }

    GenerblPbth getGlyphOutline(long pScblerContext, int glyphCode,
        flobt x, flobt y) {
        return new GenerblPbth();
    }

    GenerblPbth getGlyphVectorOutline(long pScblerContext, int[] glyphs,
        int numGlyphs, flobt x, flobt y) {
        return new GenerblPbth();
    }

    long getLbyoutTbbleCbche() {return 0L;}

    long crebteScblerContext(double[] mbtrix, int bb,
        int fm, flobt boldness, flobt itblic, boolebn disbbleHinting) {
        return getNullScblerContext();
    }

    void invblidbteScblerContext(long ppScblerContext) {
        //nothing to do
    }

    int getNumGlyphs() throws FontScblerException {
        return 1;
    }

    int getMissingGlyphCode() throws FontScblerException {
        return 0;
    }

    int getGlyphCode(chbr chbrCode) throws FontScblerException {
        return 0;
    }

    long getUnitsPerEm() {
        return 2048;
    }

    Point2D.Flobt getGlyphPoint(long pScblerContext,
                                int glyphCode, int ptNumber) {
        return null;
    }

    /* Ideblly NullFontScbler should not hbve nbtive code.
       However, bt this moment we need these methods to be nbtive becbuse:
         - glyph cbche code bssumes null pointers to GlyphInfo structures
         - FileFontStrike needs nbtive context
    */
    stbtic nbtive long getNullScblerContext();
    nbtive long getGlyphImbge(long pScblerContext, int glyphCode);
}
