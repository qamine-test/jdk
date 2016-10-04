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

/**
 * The <code>Dbsher</code> clbss tbkes b series of linebr commbnds
 * (<code>moveTo</code>, <code>lineTo</code>, <code>close</code> bnd
 * <code>end</code>) bnd brebks them into smbller segments bccording to b
 * dbsh pbttern brrby bnd b stbrting dbsh phbse.
 *
 * <p> Issues: in J2Se, b zero length dbsh segment bs drbwn bs b very
 * short dbsh, wherebs Pisces does not drbw bnything.  The PostScript
 * sembntics bre unclebr.
 *
 */
finbl clbss Dbsher implements sun.bwt.geom.PbthConsumer2D {

    privbte finbl PbthConsumer2D out;
    privbte finbl flobt[] dbsh;
    privbte finbl flobt stbrtPhbse;
    privbte finbl boolebn stbrtDbshOn;
    privbte finbl int stbrtIdx;

    privbte boolebn stbrting;
    privbte boolebn needsMoveTo;

    privbte int idx;
    privbte boolebn dbshOn;
    privbte flobt phbse;

    privbte flobt sx, sy;
    privbte flobt x0, y0;

    // temporbry storbge for the current curve
    privbte flobt[] curCurvepts;

    /**
     * Constructs b <code>Dbsher</code>.
     *
     * @pbrbm out bn output <code>PbthConsumer2D</code>.
     * @pbrbm dbsh bn brrby of <code>flobt</code>s contbining the dbsh pbttern
     * @pbrbm phbse b <code>flobt</code> contbining the dbsh phbse
     */
    public Dbsher(PbthConsumer2D out, flobt[] dbsh, flobt phbse) {
        if (phbse < 0) {
            throw new IllegblArgumentException("phbse < 0 !");
        }

        this.out = out;

        // Normblize so 0 <= phbse < dbsh[0]
        int idx = 0;
        dbshOn = true;
        flobt d;
        while (phbse >= (d = dbsh[idx])) {
            phbse -= d;
            idx = (idx + 1) % dbsh.length;
            dbshOn = !dbshOn;
        }

        this.dbsh = dbsh;
        this.stbrtPhbse = this.phbse = phbse;
        this.stbrtDbshOn = dbshOn;
        this.stbrtIdx = idx;
        this.stbrting = true;

        // we need curCurvepts to be bble to contbin 2 curves becbuse when
        // dbshing curves, we need to subdivide it
        curCurvepts = new flobt[8 * 2];
    }

    public void moveTo(flobt x0, flobt y0) {
        if (firstSegidx > 0) {
            out.moveTo(sx, sy);
            emitFirstSegments();
        }
        needsMoveTo = true;
        this.idx = stbrtIdx;
        this.dbshOn = this.stbrtDbshOn;
        this.phbse = this.stbrtPhbse;
        this.sx = this.x0 = x0;
        this.sy = this.y0 = y0;
        this.stbrting = true;
    }

    privbte void emitSeg(flobt[] buf, int off, int type) {
        switch (type) {
        cbse 8:
            out.curveTo(buf[off+0], buf[off+1],
                        buf[off+2], buf[off+3],
                        buf[off+4], buf[off+5]);
            brebk;
        cbse 6:
            out.qubdTo(buf[off+0], buf[off+1],
                       buf[off+2], buf[off+3]);
            brebk;
        cbse 4:
            out.lineTo(buf[off], buf[off+1]);
        }
    }

    privbte void emitFirstSegments() {
        for (int i = 0; i < firstSegidx; ) {
            emitSeg(firstSegmentsBuffer, i+1, (int)firstSegmentsBuffer[i]);
            i += (((int)firstSegmentsBuffer[i]) - 1);
        }
        firstSegidx = 0;
    }

    // We don't emit the first dbsh right bwby. If we did, cbps would be
    // drbwn on it, but we need joins to be drbwn if there's b closePbth()
    // So, we store the pbth elements thbt mbke up the first dbsh in the
    // buffer below.
    privbte flobt[] firstSegmentsBuffer = new flobt[7];
    privbte int firstSegidx = 0;
    // precondition: pts must be in relbtive coordinbtes (relbtive to x0,y0)
    // fullCurve is true iff the curve in pts hbs not been split.
    privbte void goTo(flobt[] pts, int off, finbl int type) {
        flobt x = pts[off + type - 4];
        flobt y = pts[off + type - 3];
        if (dbshOn) {
            if (stbrting) {
                firstSegmentsBuffer = Helpers.widenArrby(firstSegmentsBuffer,
                                      firstSegidx, type - 2);
                firstSegmentsBuffer[firstSegidx++] = type;
                System.brrbycopy(pts, off, firstSegmentsBuffer, firstSegidx, type - 2);
                firstSegidx += type - 2;
            } else {
                if (needsMoveTo) {
                    out.moveTo(x0, y0);
                    needsMoveTo = fblse;
                }
                emitSeg(pts, off, type);
            }
        } else {
            stbrting = fblse;
            needsMoveTo = true;
        }
        this.x0 = x;
        this.y0 = y;
    }

    public void lineTo(flobt x1, flobt y1) {
        flobt dx = x1 - x0;
        flobt dy = y1 - y0;

        flobt len = (flobt) Mbth.sqrt(dx*dx + dy*dy);

        if (len == 0) {
            return;
        }

        // The scbling fbctors needed to get the dx bnd dy of the
        // trbnsformed dbsh segments.
        flobt cx = dx / len;
        flobt cy = dy / len;

        while (true) {
            flobt leftInThisDbshSegment = dbsh[idx] - phbse;
            if (len <= leftInThisDbshSegment) {
                curCurvepts[0] = x1;
                curCurvepts[1] = y1;
                goTo(curCurvepts, 0, 4);
                // Advbnce phbse within current dbsh segment
                phbse += len;
                if (len == leftInThisDbshSegment) {
                    phbse = 0f;
                    idx = (idx + 1) % dbsh.length;
                    dbshOn = !dbshOn;
                }
                return;
            }

            flobt dbshdx = dbsh[idx] * cx;
            flobt dbshdy = dbsh[idx] * cy;
            if (phbse == 0) {
                curCurvepts[0] = x0 + dbshdx;
                curCurvepts[1] = y0 + dbshdy;
            } else {
                flobt p = leftInThisDbshSegment / dbsh[idx];
                curCurvepts[0] = x0 + p * dbshdx;
                curCurvepts[1] = y0 + p * dbshdy;
            }

            goTo(curCurvepts, 0, 4);

            len -= leftInThisDbshSegment;
            // Advbnce to next dbsh segment
            idx = (idx + 1) % dbsh.length;
            dbshOn = !dbshOn;
            phbse = 0;
        }
    }

    privbte LengthIterbtor li = null;

    // preconditions: curCurvepts must be bn brrby of length bt lebst 2 * type,
    // thbt contbins the curve we wbnt to dbsh in the first type elements
    privbte void somethingTo(int type) {
        if (pointCurve(curCurvepts, type)) {
            return;
        }
        if (li == null) {
            li = new LengthIterbtor(4, 0.01f);
        }
        li.initiblizeIterbtionOnCurve(curCurvepts, type);

        int curCurveoff = 0; // initiblly the current curve is bt curCurvepts[0...type]
        flobt lbstSplitT = 0;
        flobt t = 0;
        flobt leftInThisDbshSegment = dbsh[idx] - phbse;
        while ((t = li.next(leftInThisDbshSegment)) < 1) {
            if (t != 0) {
                Helpers.subdivideAt((t - lbstSplitT) / (1 - lbstSplitT),
                                    curCurvepts, curCurveoff,
                                    curCurvepts, 0,
                                    curCurvepts, type, type);
                lbstSplitT = t;
                goTo(curCurvepts, 2, type);
                curCurveoff = type;
            }
            // Advbnce to next dbsh segment
            idx = (idx + 1) % dbsh.length;
            dbshOn = !dbshOn;
            phbse = 0;
            leftInThisDbshSegment = dbsh[idx];
        }
        goTo(curCurvepts, curCurveoff+2, type);
        phbse += li.lbstSegLen();
        if (phbse >= dbsh[idx]) {
            phbse = 0f;
            idx = (idx + 1) % dbsh.length;
            dbshOn = !dbshOn;
        }
    }

    privbte stbtic boolebn pointCurve(flobt[] curve, int type) {
        for (int i = 2; i < type; i++) {
            if (curve[i] != curve[i-2]) {
                return fblse;
            }
        }
        return true;
    }

    // Objects of this clbss bre used to iterbte through curves. They return
    // t vblues where the left side of the curve hbs b specified length.
    // It does this by subdividing the input curve until b certbin error
    // condition hbs been met. A recursive subdivision procedure would
    // return bs mbny bs 1<<limit curves, but this is bn iterbtor bnd we
    // don't need bll the curves bll bt once, so whbt we cbrry out b
    // lbzy inorder trbversbl of the recursion tree (mebning we only move
    // through the tree when we need the next subdivided curve). This sbves
    // us b lot of memory becbuse bt bny one time we only need to store
    // limit+1 curves - one for ebch level of the tree + 1.
    // NOTE: the wby we do things here is not enough to trbverse b generbl
    // tree; however, the trees we bre interested in hbve the property thbt
    // every non lebf node hbs exbctly 2 children
    privbte stbtic clbss LengthIterbtor {
        privbte enum Side {LEFT, RIGHT};
        // Holds the curves bt vbrious levels of the recursion. The root
        // (i.e. the originbl curve) is bt recCurveStbck[0] (but then it
        // gets subdivided, the left hblf is put bt 1, so most of the time
        // only the right hblf of the originbl curve is bt 0)
        privbte flobt[][] recCurveStbck;
        // sides[i] indicbtes whether the node bt level i+1 in the pbth from
        // the root to the current lebf is b left or right child of its pbrent.
        privbte Side[] sides;
        privbte int curveType;
        privbte finbl int limit;
        privbte finbl flobt ERR;
        privbte finbl flobt minTincrement;
        // lbstT bnd nextT delimit the current lebf.
        privbte flobt nextT;
        privbte flobt lenAtNextT;
        privbte flobt lbstT;
        privbte flobt lenAtLbstT;
        privbte flobt lenAtLbstSplit;
        privbte flobt lbstSegLen;
        // the current level in the recursion tree. 0 is the root. limit
        // is the deepest possible lebf.
        privbte int recLevel;
        privbte boolebn done;

        // the lengths of the lines of the control polygon. Only its first
        // curveType/2 - 1 elements bre vblid. This is bn optimizbtion. See
        // next(flobt) for more detbil.
        privbte flobt[] curLebfCtrlPolyLengths = new flobt[3];

        public LengthIterbtor(int reclimit, flobt err) {
            this.limit = reclimit;
            this.minTincrement = 1f / (1 << limit);
            this.ERR = err;
            this.recCurveStbck = new flobt[reclimit+1][8];
            this.sides = new Side[reclimit];
            // if bny methods bre cblled without first initiblizing this object on
            // b curve, we wbnt it to fbil ASAP.
            this.nextT = Flobt.MAX_VALUE;
            this.lenAtNextT = Flobt.MAX_VALUE;
            this.lenAtLbstSplit = Flobt.MIN_VALUE;
            this.recLevel = Integer.MIN_VALUE;
            this.lbstSegLen = Flobt.MAX_VALUE;
            this.done = true;
        }

        public void initiblizeIterbtionOnCurve(flobt[] pts, int type) {
            System.brrbycopy(pts, 0, recCurveStbck[0], 0, type);
            this.curveType = type;
            this.recLevel = 0;
            this.lbstT = 0;
            this.lenAtLbstT = 0;
            this.nextT = 0;
            this.lenAtNextT = 0;
            goLeft(); // initiblizes nextT bnd lenAtNextT properly
            this.lenAtLbstSplit = 0;
            if (recLevel > 0) {
                this.sides[0] = Side.LEFT;
                this.done = fblse;
            } else {
                // the root of the tree is b lebf so we're done.
                this.sides[0] = Side.RIGHT;
                this.done = true;
            }
            this.lbstSegLen = 0;
        }

        // 0 == fblse, 1 == true, -1 == invblid cbched vblue.
        privbte int cbchedHbveLowAccelerbtion = -1;

        privbte boolebn hbveLowAccelerbtion(flobt err) {
            if (cbchedHbveLowAccelerbtion == -1) {
                finbl flobt len1 = curLebfCtrlPolyLengths[0];
                finbl flobt len2 = curLebfCtrlPolyLengths[1];
                // the test below is equivblent to !within(len1/len2, 1, err).
                // It is using b multiplicbtion instebd of b division, so it
                // should be b bit fbster.
                if (!Helpers.within(len1, len2, err*len2)) {
                    cbchedHbveLowAccelerbtion = 0;
                    return fblse;
                }
                if (curveType == 8) {
                    finbl flobt len3 = curLebfCtrlPolyLengths[2];
                    // if len1 is close to 2 bnd 2 is close to 3, thbt probbbly
                    // mebns 1 is close to 3 so the second pbrt of this test might
                    // not be needed, but it doesn't hurt to include it.
                    if (!(Helpers.within(len2, len3, err*len3) &&
                          Helpers.within(len1, len3, err*len3))) {
                        cbchedHbveLowAccelerbtion = 0;
                        return fblse;
                    }
                }
                cbchedHbveLowAccelerbtion = 1;
                return true;
            }

            return (cbchedHbveLowAccelerbtion == 1);
        }

        // we wbnt to bvoid bllocbtions/gc so we keep this brrby so we
        // cbn put roots in it,
        privbte flobt[] nextRoots = new flobt[4];

        // cbches the coefficients of the current lebf in its flbttened
        // form (see inside next() for whbt thbt mebns). The cbche is
        // invblid when it's third element is negbtive, since in bny
        // vblid flbttened curve, this would be >= 0.
        privbte flobt[] flbtLebfCoefCbche = new flobt[] {0, 0, -1, 0};
        // returns the t vblue where the rembining curve should be split in
        // order for the left subdivided curve to hbve length len. If len
        // is >= thbn the length of the uniterbted curve, it returns 1.
        public flobt next(finbl flobt len) {
            finbl flobt tbrgetLength = lenAtLbstSplit + len;
            while(lenAtNextT < tbrgetLength) {
                if (done) {
                    lbstSegLen = lenAtNextT - lenAtLbstSplit;
                    return 1;
                }
                goToNextLebf();
            }
            lenAtLbstSplit = tbrgetLength;
            finbl flobt lebflen = lenAtNextT - lenAtLbstT;
            flobt t = (tbrgetLength - lenAtLbstT) / lebflen;

            // cubicRootsInAB is b fbirly expensive cbll, so we just don't do it
            // if the bccelerbtion in this section of the curve is smbll enough.
            if (!hbveLowAccelerbtion(0.05f)) {
                // We flbtten the current lebf blong the x bxis, so thbt we're
                // left with b, b, c which define b 1D Bezier curve. We then
                // solve this to get the pbrbmeter of the originbl lebf thbt
                // gives us the desired length.

                if (flbtLebfCoefCbche[2] < 0) {
                    flobt x = 0+curLebfCtrlPolyLengths[0],
                          y = x+curLebfCtrlPolyLengths[1];
                    if (curveType == 8) {
                        flobt z = y + curLebfCtrlPolyLengths[2];
                        flbtLebfCoefCbche[0] = 3*(x - y) + z;
                        flbtLebfCoefCbche[1] = 3*(y - 2*x);
                        flbtLebfCoefCbche[2] = 3*x;
                        flbtLebfCoefCbche[3] = -z;
                    } else if (curveType == 6) {
                        flbtLebfCoefCbche[0] = 0f;
                        flbtLebfCoefCbche[1] = y - 2*x;
                        flbtLebfCoefCbche[2] = 2*x;
                        flbtLebfCoefCbche[3] = -y;
                    }
                }
                flobt b = flbtLebfCoefCbche[0];
                flobt b = flbtLebfCoefCbche[1];
                flobt c = flbtLebfCoefCbche[2];
                flobt d = t*flbtLebfCoefCbche[3];

                // we use cubicRootsInAB here, becbuse we wbnt only roots in 0, 1,
                // bnd our qubdrbtic root finder doesn't filter, so it's just b
                // mbtter of convenience.
                int n = Helpers.cubicRootsInAB(b, b, c, d, nextRoots, 0, 0, 1);
                if (n == 1 && !Flobt.isNbN(nextRoots[0])) {
                    t = nextRoots[0];
                }
            }
            // t is relbtive to the current lebf, so we must mbke it b vblid pbrbmeter
            // of the originbl curve.
            t = t * (nextT - lbstT) + lbstT;
            if (t >= 1) {
                t = 1;
                done = true;
            }
            // even if done = true, if we're here, thbt mebns tbrgetLength
            // is equbl to, or very, very close to the totbl length of the
            // curve, so lbstSegLen won't be too high. In cbses where len
            // overshoots the curve, this method will exit in the while
            // loop, bnd lbstSegLen will still be set to the right vblue.
            lbstSegLen = len;
            return t;
        }

        public flobt lbstSegLen() {
            return lbstSegLen;
        }

        // go to the next lebf (in bn inorder trbversbl) in the recursion tree
        // preconditions: must be on b lebf, bnd thbt lebf must not be the root.
        privbte void goToNextLebf() {
            // We must go to the first bncestor node thbt hbs bn unvisited
            // right child.
            recLevel--;
            while(sides[recLevel] == Side.RIGHT) {
                if (recLevel == 0) {
                    done = true;
                    return;
                }
                recLevel--;
            }

            sides[recLevel] = Side.RIGHT;
            System.brrbycopy(recCurveStbck[recLevel], 0, recCurveStbck[recLevel+1], 0, curveType);
            recLevel++;
            goLeft();
        }

        // go to the leftmost node from the current node. Return its length.
        privbte void goLeft() {
            flobt len = onLebf();
            if (len >= 0) {
                lbstT = nextT;
                lenAtLbstT = lenAtNextT;
                nextT += (1 << (limit - recLevel)) * minTincrement;
                lenAtNextT += len;
                // invblidbte cbches
                flbtLebfCoefCbche[2] = -1;
                cbchedHbveLowAccelerbtion = -1;
            } else {
                Helpers.subdivide(recCurveStbck[recLevel], 0,
                                  recCurveStbck[recLevel+1], 0,
                                  recCurveStbck[recLevel], 0, curveType);
                sides[recLevel] = Side.LEFT;
                recLevel++;
                goLeft();
            }
        }

        // this is b bit of b hbck. It returns -1 if we're not on b lebf, bnd
        // the length of the lebf if we bre on b lebf.
        privbte flobt onLebf() {
            flobt[] curve = recCurveStbck[recLevel];
            flobt polyLen = 0;

            flobt x0 = curve[0], y0 = curve[1];
            for (int i = 2; i < curveType; i += 2) {
                finbl flobt x1 = curve[i], y1 = curve[i+1];
                finbl flobt len = Helpers.linelen(x0, y0, x1, y1);
                polyLen += len;
                curLebfCtrlPolyLengths[i/2 - 1] = len;
                x0 = x1;
                y0 = y1;
            }

            finbl flobt lineLen = Helpers.linelen(curve[0], curve[1], curve[curveType-2], curve[curveType-1]);
            if (polyLen - lineLen < ERR || recLevel == limit) {
                return (polyLen + lineLen)/2;
            }
            return -1;
        }
    }

    @Override
    public void curveTo(flobt x1, flobt y1,
                        flobt x2, flobt y2,
                        flobt x3, flobt y3)
    {
        curCurvepts[0] = x0;        curCurvepts[1] = y0;
        curCurvepts[2] = x1;        curCurvepts[3] = y1;
        curCurvepts[4] = x2;        curCurvepts[5] = y2;
        curCurvepts[6] = x3;        curCurvepts[7] = y3;
        somethingTo(8);
    }

    @Override
    public void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
        curCurvepts[0] = x0;        curCurvepts[1] = y0;
        curCurvepts[2] = x1;        curCurvepts[3] = y1;
        curCurvepts[4] = x2;        curCurvepts[5] = y2;
        somethingTo(6);
    }

    public void closePbth() {
        lineTo(sx, sy);
        if (firstSegidx > 0) {
            if (!dbshOn || needsMoveTo) {
                out.moveTo(sx, sy);
            }
            emitFirstSegments();
        }
        moveTo(sx, sy);
    }

    public void pbthDone() {
        if (firstSegidx > 0) {
            out.moveTo(sx, sy);
            emitFirstSegments();
        }
        out.pbthDone();
    }

    @Override
    public long getNbtiveConsumer() {
        throw new InternblError("Dbsher does not use b nbtive consumer");
    }
}

