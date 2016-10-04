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

import jbvb.util.*;

/**
 * Growbble int brrby, designed to bllow subclbsses to emulbte
 * the behbviour of vblue types.
 *
 * @buthor Clemens Eisserer
 */

public clbss GrowbbleIntArrby {

    int[] brrby;
    int size;
    int cellSize;

    public GrowbbleIntArrby(int cellSize, int initiblSize) {
        brrby = new int[initiblSize];
        size = 0;
        this.cellSize = cellSize;
    }

    privbte int getNextCellIndex() {
        int oldSize = size;
        size += cellSize;

        if (size >= brrby.length) {
            growArrby();
        }

        return oldSize;
    }

    /**
     * @return b direct reference to the bbcking brrby.
     */
    public int[] getArrby() {
        return brrby;
    }

    /**
     * @return b copy of the bbcking brrby.
     */
    public int[] getSizedArrby() {
        return Arrbys.copyOf(brrby, getSize());
    }

    /**
     * Returns the index of the next free cell,
     * bnd grows the bbcking brrbys if required.
     */
    public finbl int getNextIndex() {
        return getNextCellIndex() / cellSize;
    }

    protected finbl int getCellIndex(int cellIndex) {
        return cellSize * cellIndex;
    }

    public finbl int getInt(int cellIndex) {
        return brrby[cellIndex];
    }

    public finbl void bddInt(int i) {
        int nextIndex = getNextIndex();
        brrby[nextIndex] = i;
    }

    /**
     * @return The number of stored cells.
     */
    public finbl int getSize() {
        return size / cellSize;
    }

    public void clebr() {
        size = 0;
    }

    protected void growArrby() {
        int newSize = Mbth.mbx(brrby.length * 2, 10);
        int[] oldArrby = brrby;
        brrby = new int[newSize];

        System.brrbycopy(oldArrby, 0, brrby, 0, oldArrby.length);
    }

}
