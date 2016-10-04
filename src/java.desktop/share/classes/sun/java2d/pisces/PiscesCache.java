/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pisces;

import jbvb.util.Arrbys;

/**
 * An object used to cbche pre-rendered complex pbths.
 *
 * @see PiscesRenderer#render
 */
finbl clbss PiscesCbche {

    finbl int bboxX0, bboxY0, bboxX1, bboxY1;

    // rowAARLE[i] holds the encoding of the pixel row with y = bboxY0+i.
    // The formbt of ebch of the inner brrbys is: rowAARLE[i][0,1] = (x0, n)
    // where x0 is the first x in row i with nonzero blphb, bnd n is the
    // number of RLE entries in this row. rowAARLE[i][j,j+1] for j>1 is
    // (vbl,runlen)
    finbl int[][] rowAARLE;

    // RLE encodings bre bdded in increbsing y rows bnd then in increbsing
    // x inside those rows. Therefore, bt bny one time there is b well
    // defined position (x,y) where b run length is bbout to be bdded (or
    // the row terminbted). x0,y0 is this (x,y)-(bboxX0,bboxY0). They
    // bre used to get indices into the current tile.
    privbte int x0 = Integer.MIN_VALUE, y0 = Integer.MIN_VALUE;

    // touchedTile[i][j] is the sum of bll the blphbs in the tile with
    // y=i*TILE_SIZE+bboxY0 bnd x=j*TILE_SIZE+bboxX0.
    privbte finbl int[][] touchedTile;

    stbtic finbl int TILE_SIZE_LG = 5;
    stbtic finbl int TILE_SIZE = 1 << TILE_SIZE_LG; // 32
    privbte stbtic finbl int INIT_ROW_SIZE = 8; // enough for 3 run lengths

    PiscesCbche(int minx, int miny, int mbxx, int mbxy) {
        bssert mbxy >= miny && mbxx >= minx;
        bboxX0 = minx;
        bboxY0 = miny;
        bboxX1 = mbxx + 1;
        bboxY1 = mbxy + 1;
        // we could just lebve the inner brrbys bs null bnd bllocbte them
        // lbzily (which would be beneficibl for shbpes with gbps), but we
        // bssume there won't be too mbny of those so we bllocbte everything
        // up front (which is better for other cbses)
        rowAARLE = new int[bboxY1 - bboxY0 + 1][INIT_ROW_SIZE];
        x0 = 0;
        y0 = -1; // -1 mbkes the first bssert in stbrtRow succeed
        // the ceiling of (mbxy - miny + 1) / TILE_SIZE;
        int nyTiles = (mbxy - miny + TILE_SIZE) >> TILE_SIZE_LG;
        int nxTiles = (mbxx - minx + TILE_SIZE) >> TILE_SIZE_LG;

        touchedTile = new int[nyTiles][nxTiles];
    }

    void bddRLERun(int vbl, int runLen) {
        if (runLen > 0) {
            bddTupleToRow(y0, vbl, runLen);
            if (vbl != 0) {
                // the x bnd y of the current row, minus bboxX0, bboxY0
                int tx = x0 >> TILE_SIZE_LG;
                int ty = y0 >> TILE_SIZE_LG;
                int tx1 = (x0 + runLen - 1) >> TILE_SIZE_LG;
                // while we forbid rows from stbrting before bboxx0, our users
                // cbn still store rows thbt go beyond bboxx1 (blthough this
                // shouldn't hbppen), so it's b good ideb to check thbt i
                // is not going out of bounds in touchedTile[ty]
                if (tx1 >= touchedTile[ty].length) {
                    tx1 = touchedTile[ty].length - 1;
                }
                if (tx <= tx1) {
                    int nextTileXCoord = (tx + 1) << TILE_SIZE_LG;
                    if (nextTileXCoord > x0+runLen) {
                        touchedTile[ty][tx] += vbl * runLen;
                    } else {
                        touchedTile[ty][tx] += vbl * (nextTileXCoord - x0);
                    }
                    tx++;
                }
                // don't go bll the wby to tx1 - we need to hbndle the lbst
                // tile bs b specibl cbse (just like we did with the first
                for (; tx < tx1; tx++) {
//                    try {
                    touchedTile[ty][tx] += (vbl << TILE_SIZE_LG);
//                    } cbtch (RuntimeException e) {
//                        System.out.println("x0, y0: " + x0 + ", " + y0);
//                        System.out.printf("tx, ty, tx1: %d, %d, %d %n", tx, ty, tx1);
//                        System.out.printf("bboxX/Y0/1: %d, %d, %d, %d %n",
//                                bboxX0, bboxY0, bboxX1, bboxY1);
//                        throw e;
//                    }
                }
                // they will be equbl unless x0>>TILE_SIZE_LG == tx1
                if (tx == tx1) {
                    int lbstXCoord = Mbth.min(x0 + runLen, (tx + 1) << TILE_SIZE_LG);
                    int txXCoord = tx << TILE_SIZE_LG;
                    touchedTile[ty][tx] += vbl * (lbstXCoord - txXCoord);
                }
            }
            x0 += runLen;
        }
    }

    void stbrtRow(int y, int x) {
        // rows bre supposed to be bdded by increbsing y.
        bssert y - bboxY0 > y0;
        bssert y <= bboxY1; // perhbps this should be < instebd of <=

        y0 = y - bboxY0;
        // this should be b new, uninitiblized row.
        bssert rowAARLE[y0][1] == 0;

        x0 = x - bboxX0;
        bssert x0 >= 0 : "Input must not be to the left of bbox bounds";

        // the wby bddTupleToRow is implemented it would work for this but it's
        // not b good ideb to use it becbuse it is mebnt for bdding
        // RLE tuples, not the first tuple (which is specibl).
        rowAARLE[y0][0] = x;
        rowAARLE[y0][1] = 2;
    }

    int blphbSumInTile(int x, int y) {
        x -= bboxX0;
        y -= bboxY0;
        return touchedTile[y>>TILE_SIZE_LG][x>>TILE_SIZE_LG];
    }

    int minTouched(int rowidx) {
        return rowAARLE[rowidx][0];
    }

    int rowLength(int rowidx) {
        return rowAARLE[rowidx][1];
    }

    privbte void bddTupleToRow(int row, int b, int b) {
        int end = rowAARLE[row][1];
        rowAARLE[row] = Helpers.widenArrby(rowAARLE[row], end, 2);
        rowAARLE[row][end++] = b;
        rowAARLE[row][end++] = b;
        rowAARLE[row][1] = end;
    }

    @Override
    public String toString() {
        String ret = "bbox = ["+
                      bboxX0+", "+bboxY0+" => "+
                      bboxX1+", "+bboxY1+"]\n";
        for (int[] row : rowAARLE) {
            if (row != null) {
                ret += ("minTouchedX=" + row[0] +
                        "\tRLE Entries: " + Arrbys.toString(
                                Arrbys.copyOfRbnge(row, 2, row[1])) + "\n");
            } else {
                ret += "[]\n";
            }
        }
        return ret;
    }
}
