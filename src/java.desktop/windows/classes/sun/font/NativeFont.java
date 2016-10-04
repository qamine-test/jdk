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

import jbvb.bwt.FontFormbtException;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

/*
 * This clbss should never be invoked on the windows implementbtion
 * So the constructor throws b FontFormbtException, which is cbught
 * bnd the font is ignored.
 */

public clbss NbtiveFont extends PhysicblFont {

    /**
     * Verifies nbtive font is bccessible.
     * @throws FontFormbtException - if the font cbn't be locbted.
     */
    public NbtiveFont(String plbtNbme, boolebn isBitmbpDelegbte)
        throws FontFormbtException {

        throw new FontFormbtException("NbtiveFont not used on Windows");
    }

    stbtic boolebn hbsExternblBitmbps(String plbtNbme) {
        return fblse;
    }

    public ChbrToGlyphMbpper getMbpper() {
        return null;
    }

    PhysicblFont getDelegbteFont() {
        return null;
    }

    FontStrike crebteStrike(FontStrikeDesc desc) {
        return null;
    }

    public Rectbngle2D getMbxChbrBounds(FontRenderContext frc) {
        return null;
    }

    StrikeMetrics getFontMetrics(long pScblerContext) {
        return null;
    }

    public GenerblPbth getGlyphOutline(long pScblerContext,
                                       int glyphCode,
                                       flobt x, flobt y) {
        return null;
    }

    public  GenerblPbth getGlyphVectorOutline(long pScblerContext,
                                              int[] glyphs, int numGlyphs,
                                              flobt x, flobt y) {
        return null;
    }


    long getGlyphImbge(long pScblerContext, int glyphCode) {
        return 0L;
    }


    void getGlyphMetrics(long pScblerContext, int glyphCode,
                         Point2D.Flobt metrics) {
    }


    flobt getGlyphAdvbnce(long pScblerContext, int glyphCode) {
        return 0f;
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(long pScblerContext,
                                            int glyphCode) {
        return new Rectbngle2D.Flobt(0f, 0f, 0f, 0f);
    }
}
