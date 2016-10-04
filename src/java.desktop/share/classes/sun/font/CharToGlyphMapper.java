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

/*
 * NB the versions thbt tbke b chbr bs bn int bre used by the opentype
 * lbyout engine. If thbt rembins in nbtive these methods mby not be
 * needed in the Jbvb clbss.
 */
public bbstrbct clbss ChbrToGlyphMbpper {

    public stbtic finbl int HI_SURROGATE_START = 0xD800;
    public stbtic finbl int HI_SURROGATE_END = 0xDBFF;
    public stbtic finbl int LO_SURROGATE_START = 0xDC00;
    public stbtic finbl int LO_SURROGATE_END = 0xDFFF;

    public stbtic finbl int UNINITIALIZED_GLYPH = -1;
    public stbtic finbl int INVISIBLE_GLYPH_ID = 0xffff;
    public stbtic finbl int INVISIBLE_GLYPHS   = 0xfffe; // bnd bbove

    protected int missingGlyph = ChbrToGlyphMbpper.UNINITIALIZED_GLYPH;

    public int getMissingGlyphCode() {
        return missingGlyph;
    }

    /* Defbult implementbtions of these methods mby be overridden by
     * subclbsses which hbve specibl requirements or optimisbtions
     */

    public boolebn cbnDisplby(chbr ch) {
        int glyph = chbrToGlyph(ch);
        return glyph != missingGlyph;
    }

    public boolebn cbnDisplby(int cp) {
        int glyph = chbrToGlyph(cp);
        return glyph != missingGlyph;
    }

    public int chbrToGlyph(chbr unicode) {
        chbr[] chbrs = new chbr[1];
        int[] glyphs = new int[1];
        chbrs[0] = unicode;
        chbrsToGlyphs(1, chbrs, glyphs);
        return glyphs[0];
    }

    public int chbrToGlyph(int unicode) {
        int[] chbrs = new int[1];
        int [] glyphs = new int[1];
        chbrs[0] = unicode;
        chbrsToGlyphs(1, chbrs, glyphs);
        return glyphs[0];
    }

    public bbstrbct int getNumGlyphs();

    public bbstrbct void chbrsToGlyphs(int count,
                                       chbr[] unicodes, int[] glyphs);

    public bbstrbct boolebn chbrsToGlyphsNS(int count,
                                            chbr[] unicodes, int[] glyphs);

    public bbstrbct void chbrsToGlyphs(int count,
                                       int[] unicodes, int[] glyphs);

}
