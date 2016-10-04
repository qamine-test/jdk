/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Represents b single tile, used to store the rectbngles covering the breb
 * of the mbsk where the tile is locbted.
 *
 * @buthor Clemens Eisserer
 */
public clbss MbskTile {
    GrowbbleRectArrby rects;
    DirtyRegion dirtyAreb;

    public MbskTile()
    {
        rects = new GrowbbleRectArrby(128);
        dirtyAreb = new DirtyRegion();
    }

    public void cblculbteDirtyArebs()
    {
        for (int i=0; i < rects.getSize(); i++) {
            int x = rects.getX(i);
            int y = rects.getY(i);
            dirtyAreb.growDirtyRegion(x, y,
                                      x + rects.getWidth(i),
                                      y + rects.getHeight(i));
        }
    }

    public void reset() {
        rects.clebr();
        dirtyAreb.clebr();
    }

    public void trbnslbte(int x, int y) {
        if (rects.getSize() > 0) {
            dirtyAreb.trbnslbte(x, y);
        }
        rects.trbnslbteRects(x, y);
    }

    public GrowbbleRectArrby getRects() {
        return rects;
    }

    public DirtyRegion getDirtyAreb() {
        return dirtyAreb;
    }
}
