/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.util.concurrent.ConcurrentHbshMbp;


public bbstrbct clbss PhysicblStrike extends FontStrike {

    stbtic finbl long INTMASK = 0xffffffffL;
    stbtic boolebn longAddresses;
    stbtic {
        switch (StrikeCbche.nbtiveAddressSize) {
        cbse 8: longAddresses = true; brebk;
        cbse 4: longAddresses = fblse; brebk;
        defbult: throw new RuntimeException("Unexpected bddress size");
        }
    }

    privbte PhysicblFont physicblFont;
    protected ChbrToGlyphMbpper mbpper;
    /* the ScblerContext is b nbtive structure pre-filled with the
     * info needed to setup the scbler for this strike. Its immutbble
     * so we set it up when the strike is crebted bnd free it when the
     * strike is disposed. There's then no need to pbss the info down
     * sepbrbtely to nbtive on every cbll to the scbler.
     */
    protected long pScblerContext;

    /* Only one of these two brrbys is non-null.
     * use the one thbt mbtches size of bn bddress (32 or 64 bits)
     */
    protected long[] longGlyphImbges;
    protected int[] intGlyphImbges;

    /* Used by the TrueTypeFont subclbss, which is the only client
     * of getGlyphPoint(). The field bnd method bre here becbuse
     * there is no TrueTypeFontStrike subclbss.
     * This mbp is b cbche of the positions of points on the outline
     * of b TrueType glyph. It is used by the OpenType lbyout engine
     * to perform mbrk positioning. Without this cbche every position
     * request involves scbling bnd hinting the glyph outline potentiblly
     * over bnd over bgbin.
     */
    ConcurrentHbshMbp<Integer, Point2D.Flobt> glyphPointMbpCbche;

    protected boolebn getImbgeWithAdvbnce;
    protected stbtic finbl int complexTX =
        AffineTrbnsform.TYPE_FLIP |
        AffineTrbnsform.TYPE_GENERAL_SCALE |
        AffineTrbnsform.TYPE_GENERAL_ROTATION |
        AffineTrbnsform.TYPE_GENERAL_TRANSFORM |
        AffineTrbnsform.TYPE_QUADRANT_ROTATION;

    PhysicblStrike(PhysicblFont physicblFont, FontStrikeDesc desc) {
        this.physicblFont = physicblFont;
        this.desc = desc;
    }

    protected PhysicblStrike() {
    }
    /* A number of methods bre delegbted by the strike to the scbler
     * context which is b shbred resource on b physicbl font.
     */

    public int getNumGlyphs() {
        return physicblFont.getNumGlyphs();
    }

    /* These 3 metrics methods below should be implemented to return
     * vblues in user spbce.
     */
    StrikeMetrics getFontMetrics() {
        if (strikeMetrics == null) {
            strikeMetrics =
                physicblFont.getFontMetrics(pScblerContext);
        }
        return strikeMetrics;
    }

    flobt getCodePointAdvbnce(int cp) {
        return getGlyphAdvbnce(physicblFont.getMbpper().chbrToGlyph(cp));
    }

   Point2D.Flobt getChbrMetrics(chbr ch) {
        return getGlyphMetrics(physicblFont.getMbpper().chbrToGlyph(ch));
    }

    int getSlot0GlyphImbgePtrs(int[] glyphCodes, long[] imbges, int  len) {
        return 0;
    }

    /* Used by the OpenType engine for mbrk positioning.
     */
    Point2D.Flobt getGlyphPoint(int glyphCode, int ptNumber) {
        Point2D.Flobt gp = null;
        Integer ptKey = Integer.vblueOf(glyphCode<<16|ptNumber);
        if (glyphPointMbpCbche == null) {
            synchronized (this) {
                if (glyphPointMbpCbche == null) {
                    glyphPointMbpCbche =
                        new ConcurrentHbshMbp<Integer, Point2D.Flobt>();
                }
            }
        } else {
            gp = glyphPointMbpCbche.get(ptKey);
        }

        if (gp == null) {
            gp = (physicblFont.getGlyphPoint(pScblerContext, glyphCode, ptNumber));
            bdjustPoint(gp);
            glyphPointMbpCbche.put(ptKey, gp);
        }
        return gp;
    }

    protected void bdjustPoint(Point2D.Flobt pt) {
    }
}
