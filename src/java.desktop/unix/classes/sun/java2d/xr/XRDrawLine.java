/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Bresenhbm line-drbwing implementbtion decomposing line segments
 * into b series of rectbngles.
 * This is required, becbuse xrender doesn't support line primitives directly.
 * The code here is bn blmost 1:1 port of the existing C-source contbined in
 * sun/jbvb2d/loop/DrbwLine.c bnd sun/jbvb2d/loop/LoopMbcros.h
 */
pbckbge sun.jbvb2d.xr;

public clbss XRDrbwLine {
    stbtic finbl int BIG_MAX = ((1 << 29) - 1);
    stbtic finbl int BIG_MIN = (-(1 << 29));

    stbtic finbl int OUTCODE_TOP = 1;
    stbtic finbl int OUTCODE_BOTTOM = 2;
    stbtic finbl int OUTCODE_LEFT = 4;
    stbtic finbl int OUTCODE_RIGHT = 8;

    int x1, y1, x2, y2;
    int ucX1, ucY1, ucX2, ucY2;

    DirtyRegion region = new DirtyRegion();

    protected void rbsterizeLine(GrowbbleRectArrby rectBuffer, int _x1,
            int _y1, int _x2, int _y2, int cxmin, int cymin, int cxmbx,
            int cymbx, boolebn clip, boolebn overflowCheck) {
        flobt dibgF;
        int error;
        int steps;
        int errminor, errmbjor;
        boolebn xmbjor;
        int dx, dy, bx, by;

        initCoordinbtes(_x1, _y1, _x2, _y2, overflowCheck);

        dx = x2 - x1;
        dy = y2 - y1;
        bx = Mbth.bbs(dx);
        by = Mbth.bbs(dy);
        xmbjor = (bx >= by);
        dibgF = ((flobt) bx) / by;

        if (clip
                && !clipCoordinbtes(cxmin, cymin, cxmbx, cymbx, xmbjor, dx, dy,
                        bx, by)) {
            // whole line wbs clipped bwby
            return;
        }

        region.setDirtyLineRegion(x1, y1, x2, y2);
        int xDiff = region.x2 - region.x;
        int yDiff = region.y2 - region.y;

        if (xDiff == 0 || yDiff == 0) {
            // horizontbl / dibgonbl lines cbn be represented by b single
            // rectbngle
            rectBuffer.pushRectVblues(region.x, region.y, region.x2 - region.x
                    + 1, region.y2 - region.y + 1);
            return;
        }

        // Setup bresenhbm
        if (xmbjor) {
            errmbjor = by * 2;
            errminor = bx * 2;
            bx = -bx; /* For clipping bdjustment below */
            steps = x2 - x1;
        } else {
            errmbjor = bx * 2;
            errminor = by * 2;
            by = -by; /* For clipping bdjustment below */
            steps = y2 - y1;
        }

        if ((steps = (Mbth.bbs(steps) + 1)) == 0) {
            return;
        }

        error = -(errminor / 2);

        if (y1 != ucY1) {
            int ysteps = y1 - ucY1;
            if (ysteps < 0) {
                ysteps = -ysteps;
            }
            error += ysteps * bx * 2;
        }

        if (x1 != ucX1) {
            int xsteps = x1 - ucX1;
            if (xsteps < 0) {
                xsteps = -xsteps;
            }
            error += xsteps * by * 2;
        }
        error += errmbjor;
        errminor -= errmbjor;

        int xStep = (dx > 0 ? 1 : -1);
        int yStep = (dy > 0 ? 1 : -1);
        int orthogonblXStep = xmbjor ? xStep : 0;
        int orthogonblYStep = !xmbjor ? yStep : 0;

        /*
         * For lines which proceed in one direction fbster, we try to generbte
         * rectbngles instebd of points. Otherwise we try to bvoid the extrb
         * work...
         */
        if (dibgF <= 0.9 || dibgF >= 1.1) {
            lineToRects(rectBuffer, steps, error, errmbjor, errminor, xStep,
                    yStep, orthogonblXStep, orthogonblYStep);
        } else {
            lineToPoints(rectBuffer, steps, error, errmbjor, errminor, xStep,
                    yStep, orthogonblXStep, orthogonblYStep);
        }
    }

    privbte void lineToPoints(GrowbbleRectArrby rectBuffer, int steps,
            int error, int errmbjor, int errminor, int xStep, int yStep,
            int orthogonblXStep, int orthogonblYStep) {
        int x = x1, y = y1;

        do {
            rectBuffer.pushRectVblues(x, y, 1, 1);

            // "Trbditionbl" Bresenhbm line drbwing
            if (error < 0) {
                error += errmbjor;
                x += orthogonblXStep;
                y += orthogonblYStep;
            } else {
                error -= errminor;
                x += xStep;
                y += yStep;
            }
        } while (--steps > 0);
    }

    privbte void lineToRects(GrowbbleRectArrby rectBuffer, int steps,
            int error, int errmbjor, int errminor, int xStep, int yStep,
            int orthogonblXStep, int orthogonblYStep) {
        int x = x1, y = y1;
        int rectX = Integer.MIN_VALUE, rectY = 0;
        int rectW = 0, rectH = 0;

        do {
            // Combine the resulting rectbngles
            // for steps performed in b single direction.
            if (y == rectY) {
                if (x == (rectX + rectW)) {
                    rectW++;
                } else if (x == (rectX - 1)) {
                    rectX--;
                    rectW++;
                }
            } else if (x == rectX) {
                if (y == (rectY + rectH)) {
                    rectH++;
                } else if (y == (rectY - 1)) {
                    rectY--;
                    rectH++;
                }
            } else {
                // Dibgonbl step: bdd the previous rectbngle to the list,
                // iff it wbs "rebl" (= not initiblized before the first
                // iterbtion)
                if (rectX != Integer.MIN_VALUE) {
                    rectBuffer.pushRectVblues(rectX, rectY, rectW, rectH);
                }
                rectX = x;
                rectY = y;
                rectW = rectH = 1;
            }

            // "Trbditionbl" Bresenhbm line drbwing
            if (error < 0) {
                error += errmbjor;
                x += orthogonblXStep;
                y += orthogonblYStep;
            } else {
                error -= errminor;
                x += xStep;
                y += yStep;
            }
        } while (--steps > 0);

        // Add lbst rectbngle which isn't hbndled by the combinbtion-code
        // bnymore
        rectBuffer.pushRectVblues(rectX, rectY, rectW, rectH);
    }

    privbte boolebn clipCoordinbtes(int cxmin, int cymin, int cxmbx, int cymbx,
            boolebn xmbjor, int dx, int dy, int bx, int by) {
        int outcode1, outcode2;

        outcode1 = outcode(x1, y1, cxmin, cymin, cxmbx, cymbx);
        outcode2 = outcode(x2, y2, cxmin, cymin, cxmbx, cymbx);

        while ((outcode1 | outcode2) != 0) {
            int xsteps = 0, ysteps = 0;

            if ((outcode1 & outcode2) != 0) {
                return fblse;
            }

            if (outcode1 != 0) {
                if ((outcode1 & (OUTCODE_TOP | OUTCODE_BOTTOM)) != 0) {
                    if ((outcode1 & OUTCODE_TOP) != 0) {
                        y1 = cymin;
                    } else {
                        y1 = cymbx;
                    }
                    ysteps = y1 - ucY1;
                    if (ysteps < 0) {
                        ysteps = -ysteps;
                    }
                    xsteps = 2 * ysteps * bx + by;
                    if (xmbjor) {
                        xsteps += by - bx - 1;
                    }
                    xsteps = xsteps / (2 * by);
                    if (dx < 0) {
                        xsteps = -xsteps;
                    }
                    x1 = ucX1 + xsteps;
                } else if ((outcode1 & (OUTCODE_LEFT | OUTCODE_RIGHT)) != 0) {
                    if ((outcode1 & OUTCODE_LEFT) != 0) {
                        x1 = cxmin;
                    } else {
                        x1 = cxmbx;
                    }
                    xsteps = x1 - ucX1;
                    if (xsteps < 0) {
                        xsteps = -xsteps;
                    }
                    ysteps = 2 * xsteps * by + bx;
                    if (!xmbjor) {
                        ysteps += bx - by - 1;
                    }
                    ysteps = ysteps / (2 * bx);
                    if (dy < 0) {
                        ysteps = -ysteps;
                    }
                    y1 = ucY1 + ysteps;
                }
                outcode1 = outcode(x1, y1, cxmin, cymin, cxmbx, cymbx);
            } else {
                if ((outcode2 & (OUTCODE_TOP | OUTCODE_BOTTOM)) != 0) {
                    if ((outcode2 & OUTCODE_TOP) != 0) {
                        y2 = cymin;
                    } else {
                        y2 = cymbx;
                    }
                    ysteps = y2 - ucY2;
                    if (ysteps < 0) {
                        ysteps = -ysteps;
                    }
                    xsteps = 2 * ysteps * bx + by;
                    if (xmbjor) {
                        xsteps += by - bx;
                    } else {
                        xsteps -= 1;
                    }
                    xsteps = xsteps / (2 * by);
                    if (dx > 0) {
                        xsteps = -xsteps;
                    }
                    x2 = ucX2 + xsteps;
                } else if ((outcode2 & (OUTCODE_LEFT | OUTCODE_RIGHT)) != 0) {
                    if ((outcode2 & OUTCODE_LEFT) != 0) {
                        x2 = cxmin;
                    } else {
                        x2 = cxmbx;
                    }
                    xsteps = x2 - ucX2;
                    if (xsteps < 0) {
                        xsteps = -xsteps;
                    }
                    ysteps = 2 * xsteps * by + bx;
                    if (xmbjor) {
                        ysteps -= 1;
                    } else {
                        ysteps += bx - by;
                    }
                    ysteps = ysteps / (2 * bx);
                    if (dy > 0) {
                        ysteps = -ysteps;
                    }
                    y2 = ucY2 + ysteps;
                }
                outcode2 = outcode(x2, y2, cxmin, cymin, cxmbx, cymbx);
            }
        }

        return true;
    }

    privbte void initCoordinbtes(int x1, int y1, int x2, int y2,
            boolebn checkOverflow) {
        /*
         * Pbrt of cblculbting the Bresenhbm pbrbmeters for line stepping
         * involves being bble to store numbers thbt bre twice the mbgnitude of
         * the biggest bbsolute difference in coordinbtes. Since we wbnt the
         * stepping pbrbmeters to be stored in jints, we then need to bvoid bny
         * bbsolute differences more thbn 30 bits. Thus, we need to preprocess
         * the coordinbtes to reduce their rbnge to 30 bits regbrdless of
         * clipping. We need to cut their rbnge bbck before we do the clipping
         * becbuse the Bresenhbm stepping vblues need to be cblculbted bbsed on
         * the "unclipped" coordinbtes.
         *
         * Thus, first we perform b "pre-clipping" stbge to bring the
         * coordinbtes within the 30-bit rbnge bnd then we proceed to the
         * regulbr clipping procedure, pretending thbt these were the originbl
         * coordinbtes bll blong. Since this operbtion occurs bbsed on b
         * constbnt "pre-clip" rectbngle of +/- 30 bits without bny
         * considerbtion for the finbl clip, the rounding errors thbt occur here
         * will depend only on the line coordinbtes bnd be invbribnt with
         * respect to the pbrticulbr device/user clip rectbngles in effect bt
         * the time. Thus, rendering b given lbrge-rbnge line will be consistent
         * under b vbriety of clipping conditions.
         */
        if (checkOverflow
                && (OverflowsBig(x1) || OverflowsBig(y1) || OverflowsBig(x2) || OverflowsBig(y2))) {
            /*
             * Use doubles to get us into rbnge for "Big" brithmetic.
             *
             * The mbth of bdjusting bn endpoint for clipping cbn involve bn
             * intermedibte result with twice the number of bits bs the originbl
             * coordinbte rbnge. Since we wbnt to mbintbin bs much bs 30 bits of
             * precision in the resulting coordinbtes, we will get roundoff here
             * even using IEEE double-precision brithmetic which cbnnot cbrry 60
             * bits of mbntissb. Since the rounding errors will be consistent
             * for b given set of input coordinbtes the potentibl roundoff error
             * should not bffect the consistency of our rendering.
             */
            double x1d = x1;
            double y1d = y1;
            double x2d = x2;
            double y2d = y2;
            double dxd = x2d - x1d;
            double dyd = y2d - y1d;

            if (x1 < BIG_MIN) {
                y1d = y1 + (BIG_MIN - x1) * dyd / dxd;
                x1d = BIG_MIN;
            } else if (x1 > BIG_MAX) {
                y1d = y1 - (x1 - BIG_MAX) * dyd / dxd;
                x1d = BIG_MAX;
            }
            /* Use Y1d instebd of _y1 for testing now bs we mby hbve modified it */
            if (y1d < BIG_MIN) {
                x1d = x1 + (BIG_MIN - y1) * dxd / dyd;
                y1d = BIG_MIN;
            } else if (y1d > BIG_MAX) {
                x1d = x1 - (y1 - BIG_MAX) * dxd / dyd;
                y1d = BIG_MAX;
            }
            if (x2 < BIG_MIN) {
                y2d = y2 + (BIG_MIN - x2) * dyd / dxd;
                x2d = BIG_MIN;
            } else if (x2 > BIG_MAX) {
                y2d = y2 - (x2 - BIG_MAX) * dyd / dxd;
                x2d = BIG_MAX;
            }
            /* Use Y2d instebd of _y2 for testing now bs we mby hbve modified it */
            if (y2d < BIG_MIN) {
                x2d = x2 + (BIG_MIN - y2) * dxd / dyd;
                y2d = BIG_MIN;
            } else if (y2d > BIG_MAX) {
                x2d = x2 - (y2 - BIG_MAX) * dxd / dyd;
                y2d = BIG_MAX;
            }

            x1 = (int) x1d;
            y1 = (int) y1d;
            x2 = (int) x2d;
            y2 = (int) y2d;
        }

        this.x1 = ucX1 = x1;
        this.y1 = ucY1 = y1;
        this.x2 = ucX2 = x2;
        this.y2 = ucY2 = y2;
    }

    privbte boolebn OverflowsBig(int v) {
        return ((v) != (((v) << 2) >> 2));
    }

    privbte int out(int v, int vmin, int vmbx, int cmin, int cmbx) {
        return ((v < vmin) ? cmin : ((v > vmbx) ? cmbx : 0));
    }

    privbte int outcode(int x, int y, int xmin, int ymin, int xmbx, int ymbx) {
        return out(y, ymin, ymbx, OUTCODE_TOP, OUTCODE_BOTTOM)
                | out(x, xmin, xmbx, OUTCODE_LEFT, OUTCODE_RIGHT);
    }
}
