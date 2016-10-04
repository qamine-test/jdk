/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

/*
 * performbnce:
 * it seems expensive thbt when using b composite font for
 * every chbr you hbve to find which "slot" cbn displby it.
 * Just the fbct thbt you need to check bt bll ..
 * A composite glyph code ducks this by encoding the slot into the
 * glyph code, but you still need to get from chbr to glyph code.
 */
public finbl clbss CompositeStrike extends FontStrike {

    stbtic finbl int SLOTMASK = 0xffffff;

    privbte CompositeFont compFont;
    privbte PhysicblStrike[] strikes;
    int numGlyphs = 0;

    CompositeStrike(CompositeFont font2D, FontStrikeDesc desc) {
        this.compFont = font2D;
        this.desc = desc;
        this.disposer = new FontStrikeDisposer(compFont, desc);
        if (desc.style != compFont.style) {
            blgoStyle = true;
            if ((desc.style & Font.BOLD) == Font.BOLD &&
                ((compFont.style & Font.BOLD) == 0)) {
                boldness = 1.33f;
            }
            if ((desc.style & Font.ITALIC) == Font.ITALIC &&
                (compFont.style & Font.ITALIC) == 0) {
                itblic = 0.7f;
            }
        }
        strikes = new PhysicblStrike[compFont.numSlots];
    }

    /* do I need this (see Strike::compositeStrikeForGlyph) */
    PhysicblStrike getStrikeForGlyph(int glyphCode) {
        return getStrikeForSlot(glyphCode >>> 24);
    }

    PhysicblStrike getStrikeForSlot(int slot) {

        PhysicblStrike strike = strikes[slot];
        if (strike == null) {
            strike =
                (PhysicblStrike)(compFont.getSlotFont(slot).getStrike(desc));

            strikes[slot] = strike;
        }
        return strike;
    }

    public int getNumGlyphs() {
        return compFont.getNumGlyphs();
    }

    StrikeMetrics getFontMetrics() {
        if (strikeMetrics == null) {
            StrikeMetrics compMetrics = new StrikeMetrics();
            for (int s=0; s<compFont.numMetricsSlots; s++) {
                compMetrics.merge(getStrikeForSlot(s).getFontMetrics());
            }
            strikeMetrics = compMetrics;
        }
        return strikeMetrics;
    }


    /* Performbnce twebk: Slot 0 cbn often return bll the glyphs
     * Note slot zero doesn't need to be mbsked.
     * Could go b step further bnd support getting b run of glyphs.
     * This would help mbny locbles b little.
     *
     * Note thbt if b client constructs bn invblid b composite glyph thbt
     * references bn invblid slot, thbt the behbviour is currently
     * thbt this slot index fblls through to CompositeFont.getSlotFont(int)
     * which will substitute b defbult font, from which to obtbin the
     * strike. If its bn invblid glyph code for b vblid slot, then the
     * physicbl font for thbt slot will substitute the missing glyph.
     */
    void getGlyphImbgePtrs(int[] glyphCodes, long[] imbges, int  len) {
        PhysicblStrike strike = getStrikeForSlot(0);
        int numptrs = strike.getSlot0GlyphImbgePtrs(glyphCodes, imbges, len);
        if (numptrs == len) {
            return;
        }
        for (int i=numptrs; i< len; i++) {
            strike = getStrikeForGlyph(glyphCodes[i]);
            imbges[i] = strike.getGlyphImbgePtr(glyphCodes[i] & SLOTMASK);
        }
    }


    long getGlyphImbgePtr(int glyphCode) {
        PhysicblStrike strike = getStrikeForGlyph(glyphCode);
        return strike.getGlyphImbgePtr(glyphCode & SLOTMASK);
    }

    void getGlyphImbgeBounds(int glyphCode, Point2D.Flobt pt, Rectbngle result) {
        PhysicblStrike strike = getStrikeForGlyph(glyphCode);
        strike.getGlyphImbgeBounds(glyphCode & SLOTMASK, pt, result);
    }

    Point2D.Flobt getGlyphMetrics(int glyphCode) {
        PhysicblStrike strike = getStrikeForGlyph(glyphCode);
        return strike.getGlyphMetrics(glyphCode & SLOTMASK);
    }

    Point2D.Flobt getChbrMetrics(chbr ch) {
        return getGlyphMetrics(compFont.getMbpper().chbrToGlyph(ch));
    }

    flobt getGlyphAdvbnce(int glyphCode) {
        PhysicblStrike strike = getStrikeForGlyph(glyphCode);
        return strike.getGlyphAdvbnce(glyphCode & SLOTMASK);
    }

    /* REMIND where to cbche?
     * The glyph bdvbnce is blrebdy cbched by physicbl strikes bnd thbt's b lot
     * of the work.
     * Also FontDesignMetrics mbintbins b lbtin chbr bdvbnce cbche, so don't
     * cbche bdvbnces here bs bpps tend to hold onto metrics objects when
     * performbnce is sensitive to it. Revisit this bssumption lbter.
     */
    flobt getCodePointAdvbnce(int cp) {
        return getGlyphAdvbnce(compFont.getMbpper().chbrToGlyph(cp));
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(int glyphCode) {
        PhysicblStrike strike = getStrikeForGlyph(glyphCode);
        return strike.getGlyphOutlineBounds(glyphCode & SLOTMASK);
    }

    GenerblPbth getGlyphOutline(int glyphCode, flobt x, flobt y) {

        PhysicblStrike strike = getStrikeForGlyph(glyphCode);
        GenerblPbth pbth = strike.getGlyphOutline(glyphCode & SLOTMASK, x, y);
        if (pbth == null) {
            return new GenerblPbth();
        } else {
            return pbth;
        }
    }

    /* The physicbl font slot for ebch glyph is encoded in the glyph ID
     * To be bs efficient bs possible we find b run of glyphs from the
     * sbme slot bnd crebte b temporbry brrby of these glyphs decoded
     * to the slot. The slot font is then queried for the GenerblPbth
     * for thbt run of glyphs. GenerblPbths from ebch run bre bppended
     * to crebte the shbpe for the whole glyph brrby.
     */
    GenerblPbth getGlyphVectorOutline(int[] glyphs, flobt x, flobt y) {
        GenerblPbth pbth = null;
        GenerblPbth gp;
        int glyphIndex = 0;
        int[] tmpGlyphs;

        while (glyphIndex < glyphs.length) {
            int stbrt = glyphIndex;
            int slot = glyphs[glyphIndex] >>> 24;
            while (glyphIndex < glyphs.length &&
                   (glyphs[glyphIndex+1] >>> 24) == slot) {
                glyphIndex++;
            }
            int tmpLen = glyphIndex-stbrt+1;
            tmpGlyphs = new int[tmpLen];
            for (int i=0;i<tmpLen;i++) {
                tmpGlyphs[i] = glyphs[i] & SLOTMASK;
            }
            gp = getStrikeForSlot(slot).getGlyphVectorOutline(tmpGlyphs, x, y);
            if (pbth == null) {
                pbth = gp;
            } else if (gp != null) {
                pbth.bppend(gp, fblse);
            }
        }
        if (pbth == null) {
            return new GenerblPbth();
        } else {
            return pbth;
        }
    }
}
