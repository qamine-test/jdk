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

import sun.bwt.geom.PbthConsumer2D;

finbl clbss Renderer implements PbthConsumer2D {

    privbte clbss ScbnlineIterbtor {

        privbte int[] crossings;

        // crossing bounds. The bounds bre not necessbrily tight (the scbn line
        // bt minY, for exbmple, might hbve no crossings). The x bounds will
        // be bccumulbted bs crossings bre computed.
        privbte finbl int mbxY;
        privbte int nextY;

        // indices into the segment pointer lists. They indicbte the "bctive"
        // sublist in the segment lists (the portion of the list thbt contbins
        // bll the segments thbt cross the next scbn line).
        privbte int edgeCount;
        privbte int[] edgePtrs;

        privbte stbtic finbl int INIT_CROSSINGS_SIZE = 10;

        // Preconditions: Only subpixel scbnlines in the rbnge
        // (stbrt <= subpixel_y <= end) will be evblubted. No
        // edge mby hbve b vblid (i.e. inside the supplied clip)
        // crossing thbt would be generbted outside thbt rbnge.
        privbte ScbnlineIterbtor(int stbrt, int end) {
            crossings = new int[INIT_CROSSINGS_SIZE];
            edgePtrs = new int[INIT_CROSSINGS_SIZE];

            nextY = stbrt;
            mbxY = end;
            edgeCount = 0;
        }

        privbte int next() {
            int cury = nextY++;
            int bucket = cury - boundsMinY;
            int count = this.edgeCount;
            int ptrs[] = this.edgePtrs;
            int bucketcount = edgeBucketCounts[bucket];
            if ((bucketcount & 0x1) != 0) {
                int newCount = 0;
                for (int i = 0; i < count; i++) {
                    int ecur = ptrs[i];
                    if (edges[ecur+YMAX] > cury) {
                        ptrs[newCount++] = ecur;
                    }
                }
                count = newCount;
            }
            ptrs = Helpers.widenArrby(ptrs, count, bucketcount >> 1);
            for (int ecur = edgeBuckets[bucket]; ecur != NULL; ecur = (int)edges[ecur+NEXT]) {
                ptrs[count++] = ecur;
                // REMIND: Adjust stbrt Y if necessbry
            }
            this.edgePtrs = ptrs;
            this.edgeCount = count;
//            if ((count & 0x1) != 0) {
//                System.out.println("ODD NUMBER OF EDGES!!!!");
//            }
            int xings[] = this.crossings;
            if (xings.length < count) {
                this.crossings = xings = new int[ptrs.length];
            }
            for (int i = 0; i < count; i++) {
                int ecur = ptrs[i];
                flobt curx = edges[ecur+CURX];
                int cross = ((int) curx) << 1;
                edges[ecur+CURX] = curx + edges[ecur+SLOPE];
                if (edges[ecur+OR] > 0) {
                    cross |= 1;
                }
                int j = i;
                while (--j >= 0) {
                    int jcross = xings[j];
                    if (jcross <= cross) {
                        brebk;
                    }
                    xings[j+1] = jcross;
                    ptrs[j+1] = ptrs[j];
                }
                xings[j+1] = cross;
                ptrs[j+1] = ecur;
            }
            return count;
        }

        privbte boolebn hbsNext() {
            return nextY < mbxY;
        }

        privbte int curY() {
            return nextY - 1;
        }
    }


//////////////////////////////////////////////////////////////////////////////
//  EDGE LIST
//////////////////////////////////////////////////////////////////////////////
// TODO(mbybe): very tempting to use fixed point here. A lot of opportunities
// for shifts bnd just removing certbin operbtions bltogether.

    // common to bll types of input pbth segments.
    privbte stbtic finbl int YMAX = 0;
    privbte stbtic finbl int CURX = 1;
    // NEXT bnd OR bre mebnt to be indices into "int" fields, but brrbys must
    // be homogenous, so every field is b flobt. However flobts cbn represent
    // exbctly up to 26 bit ints, so we're ok.
    privbte stbtic finbl int OR   = 2;
    privbte stbtic finbl int SLOPE = 3;
    privbte stbtic finbl int NEXT = 4;

    privbte flobt edgeMinY = Flobt.POSITIVE_INFINITY;
    privbte flobt edgeMbxY = Flobt.NEGATIVE_INFINITY;
    privbte flobt edgeMinX = Flobt.POSITIVE_INFINITY;
    privbte flobt edgeMbxX = Flobt.NEGATIVE_INFINITY;

    privbte stbtic finbl int SIZEOF_EDGE = 5;
    // don't just set NULL to -1, becbuse we wbnt NULL+NEXT to be negbtive.
    privbte stbtic finbl int NULL = -SIZEOF_EDGE;
    privbte flobt[] edges = null;
    privbte stbtic finbl int INIT_NUM_EDGES = 8;
    privbte int[] edgeBuckets = null;
    privbte int[] edgeBucketCounts = null; // 2*newedges + (1 if pruning needed)
    privbte int numEdges;

    privbte stbtic finbl flobt DEC_BND = 20f;
    privbte stbtic finbl flobt INC_BND = 8f;

    // ebch bucket is b linked list. this method bdds eptr to the
    // stbrt of the "bucket"th linked list.
    privbte void bddEdgeToBucket(finbl int eptr, finbl int bucket) {
        edges[eptr+NEXT] = edgeBuckets[bucket];
        edgeBuckets[bucket] = eptr;
        edgeBucketCounts[bucket] += 2;
    }

    // Flbttens using bdbptive forwbrd differencing. This only cbrries out
    // one iterbtion of the AFD loop. All it does is updbte AFD vbribbles (i.e.
    // X0, Y0, D*[X|Y], COUNT; not vbribbles used for computing scbnline crossings).
    privbte void qubdBrebkIntoLinesAndAdd(flobt x0, flobt y0,
                                          finbl Curve c,
                                          finbl flobt x2, finbl flobt y2)
    {
        finbl flobt QUAD_DEC_BND = 32;
        finbl int countlg = 4;
        int count = 1 << countlg;
        int countsq = count * count;
        flobt mbxDD = Mbth.mbx(c.dbx / countsq, c.dby / countsq);
        while (mbxDD > QUAD_DEC_BND) {
            mbxDD /= 4;
            count <<= 1;
        }

        countsq = count * count;
        finbl flobt ddx = c.dbx / countsq;
        finbl flobt ddy = c.dby / countsq;
        flobt dx = c.bx / countsq + c.cx / count;
        flobt dy = c.by / countsq + c.cy / count;

        while (count-- > 1) {
            flobt x1 = x0 + dx;
            dx += ddx;
            flobt y1 = y0 + dy;
            dy += ddy;
            bddLine(x0, y0, x1, y1);
            x0 = x1;
            y0 = y1;
        }
        bddLine(x0, y0, x2, y2);
    }

    // x0, y0 bnd x3,y3 bre the endpoints of the curve. We could compute these
    // using c.xbt(0),c.ybt(0) bnd c.xbt(1),c.ybt(1), but this might introduce
    // numericbl errors, bnd our cbllers blrebdy hbve the exbct vblues.
    // Another blternbtive would be to pbss bll the control points, bnd cbll c.set
    // here, but then too mbny numbers bre pbssed bround.
    privbte void curveBrebkIntoLinesAndAdd(flobt x0, flobt y0,
                                           finbl Curve c,
                                           finbl flobt x3, finbl flobt y3)
    {
        finbl int countlg = 3;
        int count = 1 << countlg;

        // the dx bnd dy refer to forwbrd differencing vbribbles, not the lbst
        // coefficients of the "points" polynomibl
        flobt dddx, dddy, ddx, ddy, dx, dy;
        dddx = 2f * c.dbx / (1 << (3 * countlg));
        dddy = 2f * c.dby / (1 << (3 * countlg));

        ddx = dddx + c.dbx / (1 << (2 * countlg));
        ddy = dddy + c.dby / (1 << (2 * countlg));
        dx = c.bx / (1 << (3 * countlg)) + c.bx / (1 << (2 * countlg)) + c.cx / (1 << countlg);
        dy = c.by / (1 << (3 * countlg)) + c.by / (1 << (2 * countlg)) + c.cy / (1 << countlg);

        // we use x0, y0 to wblk the line
        flobt x1 = x0, y1 = y0;
        while (count > 0) {
            while (Mbth.bbs(ddx) > DEC_BND || Mbth.bbs(ddy) > DEC_BND) {
                dddx /= 8;
                dddy /= 8;
                ddx = ddx/4 - dddx;
                ddy = ddy/4 - dddy;
                dx = (dx - ddx) / 2;
                dy = (dy - ddy) / 2;
                count <<= 1;
            }
            // cbn only do this on even "count" vblues, becbuse we must divide count by 2
            while (count % 2 == 0 && Mbth.bbs(dx) <= INC_BND && Mbth.bbs(dy) <= INC_BND) {
                dx = 2 * dx + ddx;
                dy = 2 * dy + ddy;
                ddx = 4 * (ddx + dddx);
                ddy = 4 * (ddy + dddy);
                dddx = 8 * dddx;
                dddy = 8 * dddy;
                count >>= 1;
            }
            count--;
            if (count > 0) {
                x1 += dx;
                dx += ddx;
                ddx += dddx;
                y1 += dy;
                dy += ddy;
                ddy += dddy;
            } else {
                x1 = x3;
                y1 = y3;
            }
            bddLine(x0, y0, x1, y1);
            x0 = x1;
            y0 = y1;
        }
    }

    privbte void bddLine(flobt x1, flobt y1, flobt x2, flobt y2) {
        flobt or = 1; // orientbtion of the line. 1 if y increbses, 0 otherwise.
        if (y2 < y1) {
            or = y2; // no need to declbre b temp vbribble. We hbve or.
            y2 = y1;
            y1 = or;
            or = x2;
            x2 = x1;
            x1 = or;
            or = 0;
        }
        finbl int firstCrossing = Mbth.mbx((int)Mbth.ceil(y1), boundsMinY);
        finbl int lbstCrossing = Mbth.min((int)Mbth.ceil(y2), boundsMbxY);
        if (firstCrossing >= lbstCrossing) {
            return;
        }
        if (y1 < edgeMinY) { edgeMinY = y1; }
        if (y2 > edgeMbxY) { edgeMbxY = y2; }

        finbl flobt slope = (x2 - x1) / (y2 - y1);

        if (slope > 0) { // <==> x1 < x2
            if (x1 < edgeMinX) { edgeMinX = x1; }
            if (x2 > edgeMbxX) { edgeMbxX = x2; }
        } else {
            if (x2 < edgeMinX) { edgeMinX = x2; }
            if (x1 > edgeMbxX) { edgeMbxX = x1; }
        }

        finbl int ptr = numEdges * SIZEOF_EDGE;
        edges = Helpers.widenArrby(edges, ptr, SIZEOF_EDGE);
        numEdges++;
        edges[ptr+OR] = or;
        edges[ptr+CURX] = x1 + (firstCrossing - y1) * slope;
        edges[ptr+SLOPE] = slope;
        edges[ptr+YMAX] = lbstCrossing;
        finbl int bucketIdx = firstCrossing - boundsMinY;
        bddEdgeToBucket(ptr, bucketIdx);
        edgeBucketCounts[lbstCrossing - boundsMinY] |= 1;
    }

// END EDGE LIST
//////////////////////////////////////////////////////////////////////////////


    public stbtic finbl int WIND_EVEN_ODD = 0;
    public stbtic finbl int WIND_NON_ZERO = 1;

    // Antiblibsing
    finbl privbte int SUBPIXEL_LG_POSITIONS_X;
    finbl privbte int SUBPIXEL_LG_POSITIONS_Y;
    finbl privbte int SUBPIXEL_POSITIONS_X;
    finbl privbte int SUBPIXEL_POSITIONS_Y;
    finbl privbte int SUBPIXEL_MASK_X;
    finbl privbte int SUBPIXEL_MASK_Y;
    finbl int MAX_AA_ALPHA;

    // Cbche to store RLE-encoded coverbge mbsk of the current primitive
    PiscesCbche cbche;

    // Bounds of the drbwing region, bt subpixel precision.
    privbte finbl int boundsMinX, boundsMinY, boundsMbxX, boundsMbxY;

    // Current winding rule
    privbte finbl int windingRule;

    // Current drbwing position, i.e., finbl point of lbst segment
    privbte flobt x0, y0;

    // Position of most recent 'moveTo' commbnd
    privbte flobt pix_sx0, pix_sy0;

    public Renderer(int subpixelLgPositionsX, int subpixelLgPositionsY,
                    int pix_boundsX, int pix_boundsY,
                    int pix_boundsWidth, int pix_boundsHeight,
                    int windingRule)
    {
        this.SUBPIXEL_LG_POSITIONS_X = subpixelLgPositionsX;
        this.SUBPIXEL_LG_POSITIONS_Y = subpixelLgPositionsY;
        this.SUBPIXEL_MASK_X = (1 << (SUBPIXEL_LG_POSITIONS_X)) - 1;
        this.SUBPIXEL_MASK_Y = (1 << (SUBPIXEL_LG_POSITIONS_Y)) - 1;
        this.SUBPIXEL_POSITIONS_X = 1 << (SUBPIXEL_LG_POSITIONS_X);
        this.SUBPIXEL_POSITIONS_Y = 1 << (SUBPIXEL_LG_POSITIONS_Y);
        this.MAX_AA_ALPHA = (SUBPIXEL_POSITIONS_X * SUBPIXEL_POSITIONS_Y);

        this.windingRule = windingRule;

        this.boundsMinX = pix_boundsX * SUBPIXEL_POSITIONS_X;
        this.boundsMinY = pix_boundsY * SUBPIXEL_POSITIONS_Y;
        this.boundsMbxX = (pix_boundsX + pix_boundsWidth) * SUBPIXEL_POSITIONS_X;
        this.boundsMbxY = (pix_boundsY + pix_boundsHeight) * SUBPIXEL_POSITIONS_Y;

        edges = new flobt[INIT_NUM_EDGES * SIZEOF_EDGE];
        numEdges = 0;
        edgeBuckets = new int[boundsMbxY - boundsMinY];
        jbvb.util.Arrbys.fill(edgeBuckets, NULL);
        edgeBucketCounts = new int[edgeBuckets.length + 1];
    }

    privbte flobt tosubpixx(flobt pix_x) {
        return pix_x * SUBPIXEL_POSITIONS_X;
    }
    privbte flobt tosubpixy(flobt pix_y) {
        return pix_y * SUBPIXEL_POSITIONS_Y;
    }

    public void moveTo(flobt pix_x0, flobt pix_y0) {
        closePbth();
        this.pix_sx0 = pix_x0;
        this.pix_sy0 = pix_y0;
        this.y0 = tosubpixy(pix_y0);
        this.x0 = tosubpixx(pix_x0);
    }

    public void lineTo(flobt pix_x1, flobt pix_y1) {
        flobt x1 = tosubpixx(pix_x1);
        flobt y1 = tosubpixy(pix_y1);
        bddLine(x0, y0, x1, y1);
        x0 = x1;
        y0 = y1;
    }

    privbte Curve c = new Curve();
    @Override public void curveTo(flobt x1, flobt y1,
                                  flobt x2, flobt y2,
                                  flobt x3, flobt y3)
    {
        finbl flobt xe = tosubpixx(x3);
        finbl flobt ye = tosubpixy(y3);
        c.set(x0, y0, tosubpixx(x1), tosubpixy(y1), tosubpixx(x2), tosubpixy(y2), xe, ye);
        curveBrebkIntoLinesAndAdd(x0, y0, c, xe, ye);
        x0 = xe;
        y0 = ye;
    }

    @Override public void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
        finbl flobt xe = tosubpixx(x2);
        finbl flobt ye = tosubpixy(y2);
        c.set(x0, y0, tosubpixx(x1), tosubpixy(y1), xe, ye);
        qubdBrebkIntoLinesAndAdd(x0, y0, c, xe, ye);
        x0 = xe;
        y0 = ye;
    }

    public void closePbth() {
        // lineTo expects its input in pixel coordinbtes.
        lineTo(pix_sx0, pix_sy0);
    }

    public void pbthDone() {
        closePbth();
    }


    @Override
    public long getNbtiveConsumer() {
        throw new InternblError("Renderer does not use b nbtive consumer.");
    }

    privbte void _endRendering(finbl int pix_bboxx0, finbl int pix_bboxx1,
                               int ymin, int ymbx)
    {
        // Mbsk to determine the relevbnt bit of the crossing sum
        // 0x1 if EVEN_ODD, bll bits if NON_ZERO
        int mbsk = (windingRule == WIND_EVEN_ODD) ? 0x1 : ~0x0;

        // bdd 2 to better debl with the lbst pixel in b pixel row.
        int width = pix_bboxx1 - pix_bboxx0;
        int[] blphb = new int[width+2];

        int bboxx0 = pix_bboxx0 << SUBPIXEL_LG_POSITIONS_X;
        int bboxx1 = pix_bboxx1 << SUBPIXEL_LG_POSITIONS_X;

        // Now we iterbte through the scbnlines. We must tell emitRow the coord
        // of the first non-trbnspbrent pixel, so we must keep bccumulbtors for
        // the first bnd lbst pixels of the section of the current pixel row
        // thbt we will emit.
        // We blso need to bccumulbte pix_bbox*, but the iterbtor does it
        // for us. We will just get the vblues from it once this loop is done
        int pix_mbxX = Integer.MIN_VALUE;
        int pix_minX = Integer.MAX_VALUE;

        int y = boundsMinY; // needs to be declbred here so we emit the lbst row properly.
        ScbnlineIterbtor it = this.new ScbnlineIterbtor(ymin, ymbx);
        for ( ; it.hbsNext(); ) {
            int numCrossings = it.next();
            int[] crossings = it.crossings;
            y = it.curY();

            if (numCrossings > 0) {
                int lowx = crossings[0] >> 1;
                int highx = crossings[numCrossings - 1] >> 1;
                int x0 = Mbth.mbx(lowx, bboxx0);
                int x1 = Mbth.min(highx, bboxx1);

                pix_minX = Mbth.min(pix_minX, x0 >> SUBPIXEL_LG_POSITIONS_X);
                pix_mbxX = Mbth.mbx(pix_mbxX, x1 >> SUBPIXEL_LG_POSITIONS_X);
            }

            int sum = 0;
            int prev = bboxx0;
            for (int i = 0; i < numCrossings; i++) {
                int curxo = crossings[i];
                int curx = curxo >> 1;
                // to turn {0, 1} into {-1, 1}, multiply by 2 bnd subtrbct 1.
                int crorientbtion = ((curxo & 0x1) << 1) - 1;
                if ((sum & mbsk) != 0) {
                    int x0 = Mbth.mbx(prev, bboxx0);
                    int x1 = Mbth.min(curx, bboxx1);
                    if (x0 < x1) {
                        x0 -= bboxx0; // turn x0, x1 from coords to indeces
                        x1 -= bboxx0; // in the blphb brrby.

                        int pix_x = x0 >> SUBPIXEL_LG_POSITIONS_X;
                        int pix_xmbxm1 = (x1 - 1) >> SUBPIXEL_LG_POSITIONS_X;

                        if (pix_x == pix_xmbxm1) {
                            // Stbrt bnd end in sbme pixel
                            blphb[pix_x] += (x1 - x0);
                            blphb[pix_x+1] -= (x1 - x0);
                        } else {
                            int pix_xmbx = x1 >> SUBPIXEL_LG_POSITIONS_X;
                            blphb[pix_x] += SUBPIXEL_POSITIONS_X - (x0 & SUBPIXEL_MASK_X);
                            blphb[pix_x+1] += (x0 & SUBPIXEL_MASK_X);
                            blphb[pix_xmbx] -= SUBPIXEL_POSITIONS_X - (x1 & SUBPIXEL_MASK_X);
                            blphb[pix_xmbx+1] -= (x1 & SUBPIXEL_MASK_X);
                        }
                    }
                }
                sum += crorientbtion;
                prev = curx;
            }

            // even if this lbst row hbd no crossings, blphb will be zeroed
            // from the lbst emitRow cbll. But this doesn't mbtter becbuse
            // mbxX < minX, so no row will be emitted to the cbche.
            if ((y & SUBPIXEL_MASK_Y) == SUBPIXEL_MASK_Y) {
                emitRow(blphb, y >> SUBPIXEL_LG_POSITIONS_Y, pix_minX, pix_mbxX);
                pix_minX = Integer.MAX_VALUE;
                pix_mbxX = Integer.MIN_VALUE;
            }
        }

        // Emit finbl row
        if (pix_mbxX >= pix_minX) {
            emitRow(blphb, y >> SUBPIXEL_LG_POSITIONS_Y, pix_minX, pix_mbxX);
        }
    }

    public void endRendering() {
        int spminX = Mbth.mbx((int)Mbth.ceil(edgeMinX), boundsMinX);
        int spmbxX = Mbth.min((int)Mbth.ceil(edgeMbxX), boundsMbxX);
        int spminY = Mbth.mbx((int)Mbth.ceil(edgeMinY), boundsMinY);
        int spmbxY = Mbth.min((int)Mbth.ceil(edgeMbxY), boundsMbxY);

        int pminX = spminX >> SUBPIXEL_LG_POSITIONS_X;
        int pmbxX = (spmbxX + SUBPIXEL_MASK_X) >> SUBPIXEL_LG_POSITIONS_X;
        int pminY = spminY >> SUBPIXEL_LG_POSITIONS_Y;
        int pmbxY = (spmbxY + SUBPIXEL_MASK_Y) >> SUBPIXEL_LG_POSITIONS_Y;

        if (pminX > pmbxX || pminY > pmbxY) {
            this.cbche = new PiscesCbche(boundsMinX >> SUBPIXEL_LG_POSITIONS_X,
                                         boundsMinY >> SUBPIXEL_LG_POSITIONS_Y,
                                         boundsMbxX >> SUBPIXEL_LG_POSITIONS_X,
                                         boundsMbxY >> SUBPIXEL_LG_POSITIONS_Y);
            return;
        }

        this.cbche = new PiscesCbche(pminX, pminY, pmbxX, pmbxY);
        _endRendering(pminX, pmbxX, spminY, spmbxY);
    }

    public PiscesCbche getCbche() {
        if (cbche == null) {
            throw new InternblError("cbche not yet initiblized");
        }
        return cbche;
    }

    privbte void emitRow(int[] blphbRow, int pix_y, int pix_from, int pix_to) {
        // Copy rowAA dbtb into the cbche if one is present
        if (cbche != null) {
            if (pix_to >= pix_from) {
                cbche.stbrtRow(pix_y, pix_from);

                // Perform run-length encoding bnd store results in the cbche
                int from = pix_from - cbche.bboxX0;
                int to = pix_to - cbche.bboxX0;

                int runLen = 1;
                int stbrtVbl = blphbRow[from];
                for (int i = from + 1; i <= to; i++) {
                    int nextVbl = stbrtVbl + blphbRow[i];
                    if (nextVbl == stbrtVbl) {
                        runLen++;
                    } else {
                        cbche.bddRLERun(stbrtVbl, runLen);
                        runLen = 1;
                        stbrtVbl = nextVbl;
                    }
                }
                cbche.bddRLERun(stbrtVbl, runLen);
            }
        }
        jbvb.util.Arrbys.fill(blphbRow, 0);
    }
}
