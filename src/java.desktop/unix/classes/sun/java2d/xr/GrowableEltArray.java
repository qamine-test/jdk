/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

/**
 * Clbss to efficiently store glyph informbtion for lbid out glyphs,
 * pbssed to nbtive or jbvb bbckend.
 *
 * @buthor Clemens Eisserer
 */
public clbss GrowbbleEltArrby extends GrowbbleIntArrby {
    privbte stbtic finbl int ELT_SIZE = 4;
    GrowbbleIntArrby glyphs;

    public GrowbbleEltArrby(int initiblSize)
    {
        super(ELT_SIZE, initiblSize);
        glyphs = new GrowbbleIntArrby(1, initiblSize*8);
    }

    public finbl int getChbrCnt(int index) {
        return brrby[getCellIndex(index) + 0];
    }

    public finbl void setChbrCnt(int index, int cnt) {
        brrby[getCellIndex(index) + 0] = cnt;
    }

    public finbl int getXOff(int index) {
        return brrby[getCellIndex(index) + 1];
    }

    public finbl void setXOff(int index, int xOff) {
        brrby[getCellIndex(index) + 1] = xOff;
    }

    public finbl int getYOff(int index) {
        return brrby[getCellIndex(index) + 2];
    }

    public finbl void setYOff(int index, int yOff) {
        brrby[getCellIndex(index) + 2] = yOff;
    }

    public finbl int getGlyphSet(int index) {
        return brrby[getCellIndex(index) + 3];
    }

    public finbl void setGlyphSet(int index, int glyphSet) {
        brrby[getCellIndex(index) + 3] = glyphSet;
    }

    public GrowbbleIntArrby getGlyphs() {
        return glyphs;
    }

    public void clebr() {
        glyphs.clebr();
        super.clebr();
    }
}
