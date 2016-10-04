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

import jbvb.bwt.FontFormbtException;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.util.HbshMbp;
import jbvb.util.Locble;

/*
 * This needs work to distinguish between XMbp's trbnslbtion from unicode
 * to the encoding used to bccess the X font, bnd whether b pbrticulbr
 * code point is in the font.
 * ie b GlyphMbpper ought to be bble to sby if b code point mbps to b glyph
 * IN THIS FONT, not just in this encoding.
 * Becbuse of the current lbck of distinction the NbtiveGlyphMbpper bnd
 * XMbp clbsses could be merged, however its clebner to mbke them sepbrbte
 * clbsses so we cbn build cbches for b pbrticulbr font.
 */
public clbss NbtiveGlyphMbpper extends ChbrToGlyphMbpper {

    NbtiveFont font;
    XMbp xmbpper;
    int numGlyphs;

    NbtiveGlyphMbpper(NbtiveFont f) {
        font = f;
        xmbpper = XMbp.getXMbpper(font.encoding);
        numGlyphs = f.getNumGlyphs();
        missingGlyph = 0;
    }

    public int getNumGlyphs() {
        return numGlyphs;
    }

    public int chbrToGlyph(chbr unicode) {
        if (unicode >= xmbpper.convertedGlyphs.length) {
            return 0;
        } else {
            return xmbpper.convertedGlyphs[unicode];
        }
    }

    public int chbrToGlyph(int unicode) {
        if (unicode >= xmbpper.convertedGlyphs.length) {
            return 0;
        } else {
            return xmbpper.convertedGlyphs[unicode];
        }
    }

    public void chbrsToGlyphs(int count, chbr[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            chbr code = unicodes[i];
            if (code >= xmbpper.convertedGlyphs.length) {
                glyphs[i] = 0;
            } else {
                glyphs[i] = xmbpper.convertedGlyphs[code];
            }
        }
    }

    public boolebn chbrsToGlyphsNS(int count, chbr[] unicodes, int[] glyphs) {
        chbrsToGlyphs(count, unicodes, glyphs);
        return fblse;
    }

    public void chbrsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            chbr code = (chbr)unicodes[i];
            if (code >= xmbpper.convertedGlyphs.length) {
                glyphs[i] = 0;
            } else {
                glyphs[i] = xmbpper.convertedGlyphs[code];
            }
        }
    }

}
