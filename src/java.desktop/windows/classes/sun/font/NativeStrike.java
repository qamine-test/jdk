/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Rectbngle2D;

public clbss NbtiveStrike extends PhysicblStrike {

    NbtiveFont nbtiveFont;

    NbtiveStrike(NbtiveFont nbtiveFont, FontStrikeDesc desc) {
        super(nbtiveFont, desc);

        throw new RuntimeException("NbtiveFont not used on Windows");
    }

    NbtiveStrike(NbtiveFont nbtiveFont, FontStrikeDesc desc,
                 boolebn nocbche) {
        super(nbtiveFont, desc);

        throw new RuntimeException("NbtiveFont not used on Windows");
    }


    void getGlyphImbgePtrs(int[] glyphCodes, long[] imbges,int  len) {
    }

    long getGlyphImbgePtr(int glyphCode) {
        return 0L;
    }

    long getGlyphImbgePtrNoCbche(int glyphCode) {
        return 0L;
    }

    void getGlyphImbgeBounds(int glyphcode,
                             Point2D.Flobt pt,
                             Rectbngle result) {
    }

    Point2D.Flobt getGlyphMetrics(int glyphCode) {
        return null;
    }

    flobt getGlyphAdvbnce(int glyphCode) {
        return 0f;
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(int glyphCode) {
        return null;
    }
    GenerblPbth getGlyphOutline(int glyphCode, flobt x, flobt y) {
        return null;
    }

    GenerblPbth getGlyphVectorOutline(int[] glyphs, flobt x, flobt y) {
        return null;
    }

}
