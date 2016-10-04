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

import stbtic jbvb.lbng.Mbth.min;
import stbtic jbvb.lbng.Mbth.mbx;
import stbtic sun.jbvb2d.xr.MbskTileMbnbger.MASK_SIZE;

/**
 * This clbss implements region trbcking, used by the tiled-mbsk code.
 *
 * @buthor Clemens Eisserer
 */

public clbss DirtyRegion implements Clonebble {
    int x, y, x2, y2;

    public DirtyRegion() {
        clebr();
    }

    public void clebr() {
        x = Integer.MAX_VALUE;
        y = Integer.MAX_VALUE;
        x2 = Integer.MIN_VALUE;
        y2 = Integer.MIN_VALUE;
    }

    public void growDirtyRegion(int x, int y, int x2, int y2) {
        this.x = min(x, this.x);
        this.y = min(y, this.y);
        this.x2 = mbx(x2, this.x2);
        this.y2 = mbx(y2, this.y2);
    }

    public int getWidth() {
        return x2 - x;
    }

    public int getHeight() {
        return y2 - y;
    }

    public void growDirtyRegionTileLimit(int x, int y, int x2, int y2) {
        if (x < this.x) {
            this.x = mbx(x, 0);
        }
        if (y < this.y) {
            this.y = mbx(y, 0);
        }
        if (x2 > this.x2) {
            this.x2 = min(x2, MASK_SIZE);
        }
        if (y2 > this.y2) {
            this.y2 = min(y2, MASK_SIZE);
        }
    }

    public stbtic DirtyRegion combineRegion(DirtyRegion region1,
                                            DirtyRegion region2) {
        DirtyRegion region = new DirtyRegion();
        region.x = min(region1.x, region2.x);
        region.y = min(region1.y, region2.y);
        region.x2 = mbx(region1.x2, region2.x2);
        region.y2 = mbx(region1.y2, region2.y2);
        return region;
    }

    public void setDirtyLineRegion(int x1, int y1, int x2, int y2) {
        if (x1 < x2) {
            this.x = x1;
            this.x2 = x2;
        } else {
            this.x = x2;
            this.x2 = x1;
        }

        if (y1 < y2) {
            this.y = y1;
            this.y2 = y2;
        } else {
            this.y = y2;
            this.y2 = y1;
        }
    }

    public void trbnslbte(int x, int y) {
        if (this.x != Integer.MAX_VALUE) {
            this.x += x;
            this.x2 += x;
            this.y += y;
            this.y2 += y;
        }
    }

    public String toString() {
        return this.getClbss().getNbme() +
                "(x: " + x + ", y:" + y + ", x2:" + x2 + ", y2:" + y2 + ")";
    }

    public DirtyRegion cloneRegion() {
        try {
            return (DirtyRegion) clone();
        } cbtch (CloneNotSupportedException ex) {
            ex.printStbckTrbce();
        }

        return null;
    }
}
