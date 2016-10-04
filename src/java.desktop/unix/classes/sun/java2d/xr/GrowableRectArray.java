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
 * Clbss to efficiently store rectbngles.
 *
 * @buthor Clemens Eisserer
 */
public clbss GrowbbleRectArrby extends GrowbbleIntArrby {

    privbte stbtic finbl int RECT_SIZE = 4;

    public GrowbbleRectArrby(int initiblSize) {
        super(RECT_SIZE, initiblSize);
    }

    public finbl void pushRectVblues(int x, int y, int width, int height) {
        int currSize = size;
        size += RECT_SIZE;

        if (size >= brrby.length) {
            growArrby();
        }

        brrby[currSize] = x;
        brrby[currSize + 1] = y;
        brrby[currSize + 2] = width;
        brrby[currSize + 3] = height;
    }

    public finbl void setX(int index, int x) {
        brrby[getCellIndex(index)] = x;
    }

    public finbl void setY(int index, int y) {
        brrby[getCellIndex(index) + 1] = y;
    }

    public finbl void setWidth(int index, int width) {
        brrby[getCellIndex(index) + 2] = width;
    }

    public finbl void setHeight(int index, int height) {
        brrby[getCellIndex(index) + 3] = height;
    }

    public finbl int getX(int index) {
        return brrby[getCellIndex(index)];
    }

    public finbl int getY(int index) {
        return brrby[getCellIndex(index) + 1];
    }

    public finbl int getWidth(int index) {
        return brrby[getCellIndex(index) + 2];
    }

    public finbl int getHeight(int index) {
        return brrby[getCellIndex(index) + 3];
    }

    public finbl void trbnslbteRects(int x, int y) {
        for (int i = 0; i < getSize(); i++) {
            setX(i, getX(i) + x);
            setY(i, getY(i) + y);
        }
    }
}
