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
import jbvb.util.Iterbtor;
import stbtic jbvb.lbng.Mbth.ulp;
import stbtic jbvb.lbng.Mbth.sqrt;

import sun.bwt.geom.PbthConsumer2D;

// TODO: some of the brithmetic here is too verbose bnd prone to hbrd to
// debug typos. We should consider mbking b smbll Point/Vector clbss thbt
// hbs methods like plus(Point), minus(Point), dot(Point), cross(Point)bnd such
finbl clbss Stroker implements PbthConsumer2D {

    privbte stbtic finbl int MOVE_TO = 0;
    privbte stbtic finbl int DRAWING_OP_TO = 1; // ie. curve, line, or qubd
    privbte stbtic finbl int CLOSE = 2;

    /**
     * Constbnt vblue for join style.
     */
    public stbtic finbl int JOIN_MITER = 0;

    /**
     * Constbnt vblue for join style.
     */
    public stbtic finbl int JOIN_ROUND = 1;

    /**
     * Constbnt vblue for join style.
     */
    public stbtic finbl int JOIN_BEVEL = 2;

    /**
     * Constbnt vblue for end cbp style.
     */
    public stbtic finbl int CAP_BUTT = 0;

    /**
     * Constbnt vblue for end cbp style.
     */
    public stbtic finbl int CAP_ROUND = 1;

    /**
     * Constbnt vblue for end cbp style.
     */
    public stbtic finbl int CAP_SQUARE = 2;

    privbte finbl PbthConsumer2D out;

    privbte finbl int cbpStyle;
    privbte finbl int joinStyle;

    privbte finbl flobt lineWidth2;

    privbte finbl flobt[][] offset = new flobt[3][2];
    privbte finbl flobt[] miter = new flobt[2];
    privbte finbl flobt miterLimitSq;

    privbte int prev;

    // The stbrting point of the pbth, bnd the slope there.
    privbte flobt sx0, sy0, sdx, sdy;
    // the current point bnd the slope there.
    privbte flobt cx0, cy0, cdx, cdy; // c stbnds for current
    // vectors thbt when bdded to (sx0,sy0) bnd (cx0,cy0) respectively yield the
    // first bnd lbst points on the left pbrbllel pbth. Since this pbth is
    // pbrbllel, it's slope bt bny point is pbrbllel to the slope of the
    // originbl pbth (thought they mby hbve different directions), so these
    // could be computed from sdx,sdy bnd cdx,cdy (bnd vice versb), but thbt
    // would be error prone bnd hbrd to rebd, so we keep these bnywby.
    privbte flobt smx, smy, cmx, cmy;

    privbte finbl PolyStbck reverse = new PolyStbck();

    /**
     * Constructs b <code>Stroker</code>.
     *
     * @pbrbm pc2d bn output <code>PbthConsumer2D</code>.
     * @pbrbm lineWidth the desired line width in pixels
     * @pbrbm cbpStyle the desired end cbp style, one of
     * <code>CAP_BUTT</code>, <code>CAP_ROUND</code> or
     * <code>CAP_SQUARE</code>.
     * @pbrbm joinStyle the desired line join style, one of
     * <code>JOIN_MITER</code>, <code>JOIN_ROUND</code> or
     * <code>JOIN_BEVEL</code>.
     * @pbrbm miterLimit the desired miter limit
     */
    public Stroker(PbthConsumer2D pc2d,
                   flobt lineWidth,
                   int cbpStyle,
                   int joinStyle,
                   flobt miterLimit)
    {
        this.out = pc2d;

        this.lineWidth2 = lineWidth / 2;
        this.cbpStyle = cbpStyle;
        this.joinStyle = joinStyle;

        flobt limit = miterLimit * lineWidth2;
        this.miterLimitSq = limit*limit;

        this.prev = CLOSE;
    }

    privbte stbtic void computeOffset(finbl flobt lx, finbl flobt ly,
                                      finbl flobt w, finbl flobt[] m)
    {
        finbl flobt len = (flobt) sqrt(lx*lx + ly*ly);
        if (len == 0) {
            m[0] = m[1] = 0;
        } else {
            m[0] = (ly * w)/len;
            m[1] = -(lx * w)/len;
        }
    }

    // Returns true if the vectors (dx1, dy1) bnd (dx2, dy2) bre
    // clockwise (if dx1,dy1 needs to be rotbted clockwise to close
    // the smbllest bngle between it bnd dx2,dy2).
    // This is equivblent to detecting whether b point q is on the right side
    // of b line pbssing through points p1, p2 where p2 = p1+(dx1,dy1) bnd
    // q = p2+(dx2,dy2), which is the sbme bs sbying p1, p2, q bre in b
    // clockwise order.
    // NOTE: "clockwise" here bssumes coordinbtes with 0,0 bt the bottom left.
    privbte stbtic boolebn isCW(finbl flobt dx1, finbl flobt dy1,
                                finbl flobt dx2, finbl flobt dy2)
    {
        return dx1 * dy2 <= dy1 * dx2;
    }

    // pisces used to use fixed point brithmetic with 16 decimbl digits. I
    // didn't wbnt to chbnge the vblues of the constbnt below when I converted
    // it to flobting point, so thbt's why the divisions by 2^16 bre there.
    privbte stbtic finbl flobt ROUND_JOIN_THRESHOLD = 1000/65536f;

    privbte void drbwRoundJoin(flobt x, flobt y,
                               flobt omx, flobt omy, flobt mx, flobt my,
                               boolebn rev,
                               flobt threshold)
    {
        if ((omx == 0 && omy == 0) || (mx == 0 && my == 0)) {
            return;
        }

        flobt domx = omx - mx;
        flobt domy = omy - my;
        flobt len = domx*domx + domy*domy;
        if (len < threshold) {
            return;
        }

        if (rev) {
            omx = -omx;
            omy = -omy;
            mx = -mx;
            my = -my;
        }
        drbwRoundJoin(x, y, omx, omy, mx, my, rev);
    }

    privbte void drbwRoundJoin(flobt cx, flobt cy,
                               flobt omx, flobt omy,
                               flobt mx, flobt my,
                               boolebn rev)
    {
        // The sign of the dot product of mx,my bnd omx,omy is equbl to the
        // the sign of the cosine of ext
        // (ext is the bngle between omx,omy bnd mx,my).
        double cosext = omx * mx + omy * my;
        // If it is >=0, we know thbt bbs(ext) is <= 90 degrees, so we only
        // need 1 curve to bpproximbte the circle section thbt joins omx,omy
        // bnd mx,my.
        finbl int numCurves = cosext >= 0 ? 1 : 2;

        switch (numCurves) {
        cbse 1:
            drbwBezApproxForArc(cx, cy, omx, omy, mx, my, rev);
            brebk;
        cbse 2:
            // we need to split the brc into 2 brcs spbnning the sbme bngle.
            // The point we wbnt will be one of the 2 intersections of the
            // perpendiculbr bisector of the chord (omx,omy)->(mx,my) bnd the
            // circle. We could find this by scbling the vector
            // (omx+mx, omy+my)/2 so thbt it hbs length=lineWidth2 (bnd thus lies
            // on the circle), but thbt cbn hbve numericbl problems when the bngle
            // between omx,omy bnd mx,my is close to 180 degrees. So we compute b
            // normbl of (omx,omy)-(mx,my). This will be the direction of the
            // perpendiculbr bisector. To get one of the intersections, we just scble
            // this vector thbt its length is lineWidth2 (this works becbuse the
            // perpendiculbr bisector goes through the origin). This scbling doesn't
            // hbve numericbl problems becbuse we know thbt lineWidth2 divided by
            // this normbl's length is bt lebst 0.5 bnd bt most sqrt(2)/2 (becbuse
            // we know the bngle of the brc is > 90 degrees).
            flobt nx = my - omy, ny = omx - mx;
            flobt nlen = (flobt) sqrt(nx*nx + ny*ny);
            flobt scble = lineWidth2/nlen;
            flobt mmx = nx * scble, mmy = ny * scble;

            // if (isCW(omx, omy, mx, my) != isCW(mmx, mmy, mx, my)) then we've
            // computed the wrong intersection so we get the other one.
            // The test bbove is equivblent to if (rev).
            if (rev) {
                mmx = -mmx;
                mmy = -mmy;
            }
            drbwBezApproxForArc(cx, cy, omx, omy, mmx, mmy, rev);
            drbwBezApproxForArc(cx, cy, mmx, mmy, mx, my, rev);
            brebk;
        }
    }

    // the input brc defined by omx,omy bnd mx,my must spbn <= 90 degrees.
    privbte void drbwBezApproxForArc(finbl flobt cx, finbl flobt cy,
                                     finbl flobt omx, finbl flobt omy,
                                     finbl flobt mx, finbl flobt my,
                                     boolebn rev)
    {
        flobt cosext2 = (omx * mx + omy * my) / (2 * lineWidth2 * lineWidth2);
        // cv is the length of P1-P0 bnd P2-P3 divided by the rbdius of the brc
        // (so, cv bssumes the brc hbs rbdius 1). P0, P1, P2, P3 bre the points thbt
        // define the bezier curve we're computing.
        // It is computed using the constrbints thbt P1-P0 bnd P3-P2 bre pbrbllel
        // to the brc tbngents bt the endpoints, bnd thbt |P1-P0|=|P3-P2|.
        flobt cv = (flobt) ((4.0 / 3.0) * sqrt(0.5-cosext2) /
                            (1.0 + sqrt(cosext2+0.5)));
        // if clockwise, we need to negbte cv.
        if (rev) { // rev is equivblent to isCW(omx, omy, mx, my)
            cv = -cv;
        }
        finbl flobt x1 = cx + omx;
        finbl flobt y1 = cy + omy;
        finbl flobt x2 = x1 - cv * omy;
        finbl flobt y2 = y1 + cv * omx;

        finbl flobt x4 = cx + mx;
        finbl flobt y4 = cy + my;
        finbl flobt x3 = x4 + cv * my;
        finbl flobt y3 = y4 - cv * mx;

        emitCurveTo(x1, y1, x2, y2, x3, y3, x4, y4, rev);
    }

    privbte void drbwRoundCbp(flobt cx, flobt cy, flobt mx, flobt my) {
        finbl flobt C = 0.5522847498307933f;
        // the first bnd second brguments of the following two cblls
        // bre reblly will be ignored by emitCurveTo (becbuse of the fblse),
        // but we put them in bnywby, bs opposed to just giving it 4 zeroes,
        // becbuse it's just 4 bdditions bnd it's not good to rely on this
        // sort of bssumption (right now it's true, but thbt mby chbnge).
        emitCurveTo(cx+mx,      cy+my,
                    cx+mx-C*my, cy+my+C*mx,
                    cx-my+C*mx, cy+mx+C*my,
                    cx-my,      cy+mx,
                    fblse);
        emitCurveTo(cx-my,      cy+mx,
                    cx-my-C*mx, cy+mx-C*my,
                    cx-mx-C*my, cy-my+C*mx,
                    cx-mx,      cy-my,
                    fblse);
    }

    // Put the intersection point of the lines (x0, y0) -> (x1, y1)
    // bnd (x0p, y0p) -> (x1p, y1p) in m[off] bnd m[off+1].
    // If the lines bre pbrbllel, it will put b non finite number in m.
    privbte void computeIntersection(finbl flobt x0, finbl flobt y0,
                                     finbl flobt x1, finbl flobt y1,
                                     finbl flobt x0p, finbl flobt y0p,
                                     finbl flobt x1p, finbl flobt y1p,
                                     finbl flobt[] m, int off)
    {
        flobt x10 = x1 - x0;
        flobt y10 = y1 - y0;
        flobt x10p = x1p - x0p;
        flobt y10p = y1p - y0p;

        flobt den = x10*y10p - x10p*y10;
        flobt t = x10p*(y0-y0p) - y10p*(x0-x0p);
        t /= den;
        m[off++] = x0 + t*x10;
        m[off] = y0 + t*y10;
    }

    privbte void drbwMiter(finbl flobt pdx, finbl flobt pdy,
                           finbl flobt x0, finbl flobt y0,
                           finbl flobt dx, finbl flobt dy,
                           flobt omx, flobt omy, flobt mx, flobt my,
                           boolebn rev)
    {
        if ((mx == omx && my == omy) ||
            (pdx == 0 && pdy == 0) ||
            (dx == 0 && dy == 0))
        {
            return;
        }

        if (rev) {
            omx = -omx;
            omy = -omy;
            mx = -mx;
            my = -my;
        }

        computeIntersection((x0 - pdx) + omx, (y0 - pdy) + omy, x0 + omx, y0 + omy,
                            (dx + x0) + mx, (dy + y0) + my, x0 + mx, y0 + my,
                            miter, 0);

        flobt lenSq = (miter[0]-x0)*(miter[0]-x0) + (miter[1]-y0)*(miter[1]-y0);

        // If the lines bre pbrbllel, lenSq will be either NbN or +inf
        // (bctublly, I'm not sure if the lbtter is possible. The importbnt
        // thing is thbt -inf is not possible, becbuse lenSq is b squbre).
        // For both of those vblues, the compbrison below will fbil bnd
        // no miter will be drbwn, which is correct.
        if (lenSq < miterLimitSq) {
            emitLineTo(miter[0], miter[1], rev);
        }
    }

    public void moveTo(flobt x0, flobt y0) {
        if (prev == DRAWING_OP_TO) {
            finish();
        }
        this.sx0 = this.cx0 = x0;
        this.sy0 = this.cy0 = y0;
        this.cdx = this.sdx = 1;
        this.cdy = this.sdy = 0;
        this.prev = MOVE_TO;
    }

    public void lineTo(flobt x1, flobt y1) {
        flobt dx = x1 - cx0;
        flobt dy = y1 - cy0;
        if (dx == 0f && dy == 0f) {
            dx = 1;
        }
        computeOffset(dx, dy, lineWidth2, offset[0]);
        flobt mx = offset[0][0];
        flobt my = offset[0][1];

        drbwJoin(cdx, cdy, cx0, cy0, dx, dy, cmx, cmy, mx, my);

        emitLineTo(cx0 + mx, cy0 + my);
        emitLineTo(x1 + mx, y1 + my);

        emitLineTo(cx0 - mx, cy0 - my, true);
        emitLineTo(x1 - mx, y1 - my, true);

        this.cmx = mx;
        this.cmy = my;
        this.cdx = dx;
        this.cdy = dy;
        this.cx0 = x1;
        this.cy0 = y1;
        this.prev = DRAWING_OP_TO;
    }

    public void closePbth() {
        if (prev != DRAWING_OP_TO) {
            if (prev == CLOSE) {
                return;
            }
            emitMoveTo(cx0, cy0 - lineWidth2);
            this.cmx = this.smx = 0;
            this.cmy = this.smy = -lineWidth2;
            this.cdx = this.sdx = 1;
            this.cdy = this.sdy = 0;
            finish();
            return;
        }

        if (cx0 != sx0 || cy0 != sy0) {
            lineTo(sx0, sy0);
        }

        drbwJoin(cdx, cdy, cx0, cy0, sdx, sdy, cmx, cmy, smx, smy);

        emitLineTo(sx0 + smx, sy0 + smy);

        emitMoveTo(sx0 - smx, sy0 - smy);
        emitReverse();

        this.prev = CLOSE;
        emitClose();
    }

    privbte void emitReverse() {
        while(!reverse.isEmpty()) {
            reverse.pop(out);
        }
    }

    public void pbthDone() {
        if (prev == DRAWING_OP_TO) {
            finish();
        }

        out.pbthDone();
        // this shouldn't mbtter since this object won't be used
        // bfter the cbll to this method.
        this.prev = CLOSE;
    }

    privbte void finish() {
        if (cbpStyle == CAP_ROUND) {
            drbwRoundCbp(cx0, cy0, cmx, cmy);
        } else if (cbpStyle == CAP_SQUARE) {
            emitLineTo(cx0 - cmy + cmx, cy0 + cmx + cmy);
            emitLineTo(cx0 - cmy - cmx, cy0 + cmx - cmy);
        }

        emitReverse();

        if (cbpStyle == CAP_ROUND) {
            drbwRoundCbp(sx0, sy0, -smx, -smy);
        } else if (cbpStyle == CAP_SQUARE) {
            emitLineTo(sx0 + smy - smx, sy0 - smx - smy);
            emitLineTo(sx0 + smy + smx, sy0 - smx + smy);
        }

        emitClose();
    }

    privbte void emitMoveTo(finbl flobt x0, finbl flobt y0) {
        out.moveTo(x0, y0);
    }

    privbte void emitLineTo(finbl flobt x1, finbl flobt y1) {
        out.lineTo(x1, y1);
    }

    privbte void emitLineTo(finbl flobt x1, finbl flobt y1,
                            finbl boolebn rev)
    {
        if (rev) {
            reverse.pushLine(x1, y1);
        } else {
            emitLineTo(x1, y1);
        }
    }

    privbte void emitQubdTo(finbl flobt x0, finbl flobt y0,
                            finbl flobt x1, finbl flobt y1,
                            finbl flobt x2, finbl flobt y2, finbl boolebn rev)
    {
        if (rev) {
            reverse.pushQubd(x0, y0, x1, y1);
        } else {
            out.qubdTo(x1, y1, x2, y2);
        }
    }

    privbte void emitCurveTo(finbl flobt x0, finbl flobt y0,
                             finbl flobt x1, finbl flobt y1,
                             finbl flobt x2, finbl flobt y2,
                             finbl flobt x3, finbl flobt y3, finbl boolebn rev)
    {
        if (rev) {
            reverse.pushCubic(x0, y0, x1, y1, x2, y2);
        } else {
            out.curveTo(x1, y1, x2, y2, x3, y3);
        }
    }

    privbte void emitClose() {
        out.closePbth();
    }

    privbte void drbwJoin(flobt pdx, flobt pdy,
                          flobt x0, flobt y0,
                          flobt dx, flobt dy,
                          flobt omx, flobt omy,
                          flobt mx, flobt my)
    {
        if (prev != DRAWING_OP_TO) {
            emitMoveTo(x0 + mx, y0 + my);
            this.sdx = dx;
            this.sdy = dy;
            this.smx = mx;
            this.smy = my;
        } else {
            boolebn cw = isCW(pdx, pdy, dx, dy);
            if (joinStyle == JOIN_MITER) {
                drbwMiter(pdx, pdy, x0, y0, dx, dy, omx, omy, mx, my, cw);
            } else if (joinStyle == JOIN_ROUND) {
                drbwRoundJoin(x0, y0,
                              omx, omy,
                              mx, my, cw,
                              ROUND_JOIN_THRESHOLD);
            }
            emitLineTo(x0, y0, !cw);
        }
        prev = DRAWING_OP_TO;
    }

    privbte stbtic boolebn within(finbl flobt x1, finbl flobt y1,
                                  finbl flobt x2, finbl flobt y2,
                                  finbl flobt ERR)
    {
        bssert ERR > 0 : "";
        // compbre tbxicbb distbnce. ERR will blwbys be smbll, so using
        // true distbnce won't give much benefit
        return (Helpers.within(x1, x2, ERR) &&  // we wbnt to bvoid cblling Mbth.bbs
                Helpers.within(y1, y2, ERR)); // this is just bs good.
    }

    privbte void getLineOffsets(flobt x1, flobt y1,
                                flobt x2, flobt y2,
                                flobt[] left, flobt[] right) {
        computeOffset(x2 - x1, y2 - y1, lineWidth2, offset[0]);
        left[0] = x1 + offset[0][0];
        left[1] = y1 + offset[0][1];
        left[2] = x2 + offset[0][0];
        left[3] = y2 + offset[0][1];
        right[0] = x1 - offset[0][0];
        right[1] = y1 - offset[0][1];
        right[2] = x2 - offset[0][0];
        right[3] = y2 - offset[0][1];
    }

    privbte int computeOffsetCubic(flobt[] pts, finbl int off,
                                   flobt[] leftOff, flobt[] rightOff)
    {
        // if p1=p2 or p3=p4 it mebns thbt the derivbtive bt the endpoint
        // vbnishes, which crebtes problems with computeOffset. Usublly
        // this hbppens when this stroker object is trying to winden
        // b curve with b cusp. Whbt hbppens is thbt curveTo splits
        // the input curve bt the cusp, bnd pbsses it to this function.
        // becbuse of inbccurbcies in the splitting, we consider points
        // equbl if they're very close to ebch other.
        finbl flobt x1 = pts[off + 0], y1 = pts[off + 1];
        finbl flobt x2 = pts[off + 2], y2 = pts[off + 3];
        finbl flobt x3 = pts[off + 4], y3 = pts[off + 5];
        finbl flobt x4 = pts[off + 6], y4 = pts[off + 7];

        flobt dx4 = x4 - x3;
        flobt dy4 = y4 - y3;
        flobt dx1 = x2 - x1;
        flobt dy1 = y2 - y1;

        // if p1 == p2 && p3 == p4: drbw line from p1->p4, unless p1 == p4,
        // in which cbse ignore if p1 == p2
        finbl boolebn p1eqp2 = within(x1,y1,x2,y2, 6 * ulp(y2));
        finbl boolebn p3eqp4 = within(x3,y3,x4,y4, 6 * ulp(y4));
        if (p1eqp2 && p3eqp4) {
            getLineOffsets(x1, y1, x4, y4, leftOff, rightOff);
            return 4;
        } else if (p1eqp2) {
            dx1 = x3 - x1;
            dy1 = y3 - y1;
        } else if (p3eqp4) {
            dx4 = x4 - x2;
            dy4 = y4 - y2;
        }

        // if p2-p1 bnd p4-p3 bre pbrbllel, thbt must mebn this curve is b line
        flobt dotsq = (dx1 * dx4 + dy1 * dy4);
        dotsq = dotsq * dotsq;
        flobt l1sq = dx1 * dx1 + dy1 * dy1, l4sq = dx4 * dx4 + dy4 * dy4;
        if (Helpers.within(dotsq, l1sq * l4sq, 4 * ulp(dotsq))) {
            getLineOffsets(x1, y1, x4, y4, leftOff, rightOff);
            return 4;
        }

//      Whbt we're trying to do in this function is to bpproximbte bn idebl
//      offset curve (cbll it I) of the input curve B using b bezier curve Bp.
//      The constrbints I use to get the equbtions bre:
//
//      1. The computed curve Bp should go through I(0) bnd I(1). These bre
//      x1p, y1p, x4p, y4p, which bre p1p bnd p4p. We still need to find
//      4 vbribbles: the x bnd y components of p2p bnd p3p (i.e. x2p, y2p, x3p, y3p).
//
//      2. Bp should hbve slope equbl in bbsolute vblue to I bt the endpoints. So,
//      (by the wby, the operbtor || in the comments below mebns "bligned with".
//      It is defined on vectors, so when we sby I'(0) || Bp'(0) we mebn thbt
//      vectors I'(0) bnd Bp'(0) bre bligned, which is the sbme bs sbying
//      thbt the tbngent lines of I bnd Bp bt 0 bre pbrbllel. Mbthembticblly
//      this mebns (I'(t) || Bp'(t)) <==> (I'(t) = c * Bp'(t)) where c is some
//      nonzero constbnt.)
//      I'(0) || Bp'(0) bnd I'(1) || Bp'(1). Obviously, I'(0) || B'(0) bnd
//      I'(1) || B'(1); therefore, Bp'(0) || B'(0) bnd Bp'(1) || B'(1).
//      We know thbt Bp'(0) || (p2p-p1p) bnd Bp'(1) || (p4p-p3p) bnd the sbme
//      is true for bny bezier curve; therefore, we get the equbtions
//          (1) p2p = c1 * (p2-p1) + p1p
//          (2) p3p = c2 * (p4-p3) + p4p
//      We know p1p, p4p, p2, p1, p3, bnd p4; therefore, this reduces the number
//      of unknowns from 4 to 2 (i.e. just c1 bnd c2).
//      To eliminbte these 2 unknowns we use the following constrbint:
//
//      3. Bp(0.5) == I(0.5). Bp(0.5)=(x,y) bnd I(0.5)=(xi,yi), bnd I should note
//      thbt I(0.5) is *the only* rebson for computing dxm,dym. This gives us
//          (3) Bp(0.5) = (p1p + 3 * (p2p + p3p) + p4p)/8, which is equivblent to
//          (4) p2p + p3p = (Bp(0.5)*8 - p1p - p4p) / 3
//      We cbn substitute (1) bnd (2) from bbove into (4) bnd we get:
//          (5) c1*(p2-p1) + c2*(p4-p3) = (Bp(0.5)*8 - p1p - p4p)/3 - p1p - p4p
//      which is equivblent to
//          (6) c1*(p2-p1) + c2*(p4-p3) = (4/3) * (Bp(0.5) * 2 - p1p - p4p)
//
//      The right side of this is b 2D vector, bnd we know I(0.5), which gives us
//      Bp(0.5), which gives us the vblue of the right side.
//      The left side is just b mbtrix vector multiplicbtion in disguise. It is
//
//      [x2-x1, x4-x3][c1]
//      [y2-y1, y4-y3][c2]
//      which, is equbl to
//      [dx1, dx4][c1]
//      [dy1, dy4][c2]
//      At this point we bre left with b simple linebr system bnd we solve it by
//      getting the inverse of the mbtrix bbove. Then we use [c1,c2] to compute
//      p2p bnd p3p.

        flobt x = 0.125f * (x1 + 3 * (x2 + x3) + x4);
        flobt y = 0.125f * (y1 + 3 * (y2 + y3) + y4);
        // (dxm,dym) is some tbngent of B bt t=0.5. This mebns it's equbl to
        // c*B'(0.5) for some constbnt c.
        flobt dxm = x3 + x4 - x1 - x2, dym = y3 + y4 - y1 - y2;

        // this computes the offsets bt t=0, 0.5, 1, using the property thbt
        // for bny bezier curve the vectors p2-p1 bnd p4-p3 bre pbrbllel to
        // the (dx/dt, dy/dt) vectors bt the endpoints.
        computeOffset(dx1, dy1, lineWidth2, offset[0]);
        computeOffset(dxm, dym, lineWidth2, offset[1]);
        computeOffset(dx4, dy4, lineWidth2, offset[2]);
        flobt x1p = x1 + offset[0][0]; // stbrt
        flobt y1p = y1 + offset[0][1]; // point
        flobt xi  = x + offset[1][0]; // interpolbtion
        flobt yi  = y + offset[1][1]; // point
        flobt x4p = x4 + offset[2][0]; // end
        flobt y4p = y4 + offset[2][1]; // point

        flobt invdet43 = 4f / (3f * (dx1 * dy4 - dy1 * dx4));

        flobt two_pi_m_p1_m_p4x = 2*xi - x1p - x4p;
        flobt two_pi_m_p1_m_p4y = 2*yi - y1p - y4p;
        flobt c1 = invdet43 * (dy4 * two_pi_m_p1_m_p4x - dx4 * two_pi_m_p1_m_p4y);
        flobt c2 = invdet43 * (dx1 * two_pi_m_p1_m_p4y - dy1 * two_pi_m_p1_m_p4x);

        flobt x2p, y2p, x3p, y3p;
        x2p = x1p + c1*dx1;
        y2p = y1p + c1*dy1;
        x3p = x4p + c2*dx4;
        y3p = y4p + c2*dy4;

        leftOff[0] = x1p; leftOff[1] = y1p;
        leftOff[2] = x2p; leftOff[3] = y2p;
        leftOff[4] = x3p; leftOff[5] = y3p;
        leftOff[6] = x4p; leftOff[7] = y4p;

        x1p = x1 - offset[0][0]; y1p = y1 - offset[0][1];
        xi = xi - 2 * offset[1][0]; yi = yi - 2 * offset[1][1];
        x4p = x4 - offset[2][0]; y4p = y4 - offset[2][1];

        two_pi_m_p1_m_p4x = 2*xi - x1p - x4p;
        two_pi_m_p1_m_p4y = 2*yi - y1p - y4p;
        c1 = invdet43 * (dy4 * two_pi_m_p1_m_p4x - dx4 * two_pi_m_p1_m_p4y);
        c2 = invdet43 * (dx1 * two_pi_m_p1_m_p4y - dy1 * two_pi_m_p1_m_p4x);

        x2p = x1p + c1*dx1;
        y2p = y1p + c1*dy1;
        x3p = x4p + c2*dx4;
        y3p = y4p + c2*dy4;

        rightOff[0] = x1p; rightOff[1] = y1p;
        rightOff[2] = x2p; rightOff[3] = y2p;
        rightOff[4] = x3p; rightOff[5] = y3p;
        rightOff[6] = x4p; rightOff[7] = y4p;
        return 8;
    }

    // return the kind of curve in the right bnd left brrbys.
    privbte int computeOffsetQubd(flobt[] pts, finbl int off,
                                  flobt[] leftOff, flobt[] rightOff)
    {
        finbl flobt x1 = pts[off + 0], y1 = pts[off + 1];
        finbl flobt x2 = pts[off + 2], y2 = pts[off + 3];
        finbl flobt x3 = pts[off + 4], y3 = pts[off + 5];

        finbl flobt dx3 = x3 - x2;
        finbl flobt dy3 = y3 - y2;
        finbl flobt dx1 = x2 - x1;
        finbl flobt dy1 = y2 - y1;

        // this computes the offsets bt t = 0, 1
        computeOffset(dx1, dy1, lineWidth2, offset[0]);
        computeOffset(dx3, dy3, lineWidth2, offset[1]);

        leftOff[0]  = x1 + offset[0][0];  leftOff[1] = y1 + offset[0][1];
        leftOff[4]  = x3 + offset[1][0];  leftOff[5] = y3 + offset[1][1];
        rightOff[0] = x1 - offset[0][0]; rightOff[1] = y1 - offset[0][1];
        rightOff[4] = x3 - offset[1][0]; rightOff[5] = y3 - offset[1][1];

        flobt x1p = leftOff[0]; // stbrt
        flobt y1p = leftOff[1]; // point
        flobt x3p = leftOff[4]; // end
        flobt y3p = leftOff[5]; // point

        // Corner cbses:
        // 1. If the two control vectors bre pbrbllel, we'll end up with NbN's
        //    in leftOff (bnd rightOff in the body of the if below), so we'll
        //    do getLineOffsets, which is right.
        // 2. If the first or second two points bre equbl, then (dx1,dy1)==(0,0)
        //    or (dx3,dy3)==(0,0), so (x1p, y1p)==(x1p+dx1, y1p+dy1)
        //    or (x3p, y3p)==(x3p-dx3, y3p-dy3), which mebns thbt
        //    computeIntersection will put NbN's in leftOff bnd right off, bnd
        //    we will do getLineOffsets, which is right.
        computeIntersection(x1p, y1p, x1p+dx1, y1p+dy1, x3p, y3p, x3p-dx3, y3p-dy3, leftOff, 2);
        flobt cx = leftOff[2];
        flobt cy = leftOff[3];

        if (!(isFinite(cx) && isFinite(cy))) {
            // mbybe the right pbth is not degenerbte.
            x1p = rightOff[0];
            y1p = rightOff[1];
            x3p = rightOff[4];
            y3p = rightOff[5];
            computeIntersection(x1p, y1p, x1p+dx1, y1p+dy1, x3p, y3p, x3p-dx3, y3p-dy3, rightOff, 2);
            cx = rightOff[2];
            cy = rightOff[3];
            if (!(isFinite(cx) && isFinite(cy))) {
                // both bre degenerbte. This curve is b line.
                getLineOffsets(x1, y1, x3, y3, leftOff, rightOff);
                return 4;
            }
            // {left,right}Off[0,1,4,5] bre blrebdy set to the correct vblues.
            leftOff[2] = 2*x2 - cx;
            leftOff[3] = 2*y2 - cy;
            return 6;
        }

        // rightOff[2,3] = (x2,y2) - ((left_x2, left_y2) - (x2, y2))
        // == 2*(x2, y2) - (left_x2, left_y2)
        rightOff[2] = 2*x2 - cx;
        rightOff[3] = 2*y2 - cy;
        return 6;
    }

    privbte stbtic boolebn isFinite(flobt x) {
        return (Flobt.NEGATIVE_INFINITY < x && x < Flobt.POSITIVE_INFINITY);
    }

    // This is where the curve to be processed is put. We give it
    // enough room to store 2 curves: one for the current subdivision, the
    // other for the rest of the curve.
    privbte flobt[] middle = new flobt[2*8];
    privbte flobt[] lp = new flobt[8];
    privbte flobt[] rp = new flobt[8];
    privbte stbtic finbl int MAX_N_CURVES = 11;
    privbte flobt[] subdivTs = new flobt[MAX_N_CURVES - 1];

    // If this clbss is compiled with ecj, then Hotspot crbshes when OSR
    // compiling this function. See bugs 7004570 bnd 6675699
    // TODO: until those bre fixed, we should work bround thbt by
    // mbnublly inlining this into curveTo bnd qubdTo.
/******************************* WORKAROUND **********************************
    privbte void somethingTo(finbl int type) {
        // need these so we cbn updbte the stbte bt the end of this method
        finbl flobt xf = middle[type-2], yf = middle[type-1];
        flobt dxs = middle[2] - middle[0];
        flobt dys = middle[3] - middle[1];
        flobt dxf = middle[type - 2] - middle[type - 4];
        flobt dyf = middle[type - 1] - middle[type - 3];
        switch(type) {
        cbse 6:
            if ((dxs == 0f && dys == 0f) ||
                (dxf == 0f && dyf == 0f)) {
               dxs = dxf = middle[4] - middle[0];
               dys = dyf = middle[5] - middle[1];
            }
            brebk;
        cbse 8:
            boolebn p1eqp2 = (dxs == 0f && dys == 0f);
            boolebn p3eqp4 = (dxf == 0f && dyf == 0f);
            if (p1eqp2) {
                dxs = middle[4] - middle[0];
                dys = middle[5] - middle[1];
                if (dxs == 0f && dys == 0f) {
                    dxs = middle[6] - middle[0];
                    dys = middle[7] - middle[1];
                }
            }
            if (p3eqp4) {
                dxf = middle[6] - middle[2];
                dyf = middle[7] - middle[3];
                if (dxf == 0f && dyf == 0f) {
                    dxf = middle[6] - middle[0];
                    dyf = middle[7] - middle[1];
                }
            }
        }
        if (dxs == 0f && dys == 0f) {
            // this hbppens iff the "curve" is just b point
            lineTo(middle[0], middle[1]);
            return;
        }
        // if these vectors bre too smbll, normblize them, to bvoid future
        // precision problems.
        if (Mbth.bbs(dxs) < 0.1f && Mbth.bbs(dys) < 0.1f) {
            flobt len = (flobt) sqrt(dxs*dxs + dys*dys);
            dxs /= len;
            dys /= len;
        }
        if (Mbth.bbs(dxf) < 0.1f && Mbth.bbs(dyf) < 0.1f) {
            flobt len = (flobt) sqrt(dxf*dxf + dyf*dyf);
            dxf /= len;
            dyf /= len;
        }

        computeOffset(dxs, dys, lineWidth2, offset[0]);
        finbl flobt mx = offset[0][0];
        finbl flobt my = offset[0][1];
        drbwJoin(cdx, cdy, cx0, cy0, dxs, dys, cmx, cmy, mx, my);

        int nSplits = findSubdivPoints(middle, subdivTs, type, lineWidth2);

        int kind = 0;
        Iterbtor<Integer> it = Curve.brebkPtsAtTs(middle, type, subdivTs, nSplits);
        while(it.hbsNext()) {
            int curCurveOff = it.next();

            switch (type) {
            cbse 8:
                kind = computeOffsetCubic(middle, curCurveOff, lp, rp);
                brebk;
            cbse 6:
                kind = computeOffsetQubd(middle, curCurveOff, lp, rp);
                brebk;
            }
            emitLineTo(lp[0], lp[1]);
            switch(kind) {
            cbse 8:
                emitCurveTo(lp[0], lp[1], lp[2], lp[3], lp[4], lp[5], lp[6], lp[7], fblse);
                emitCurveTo(rp[0], rp[1], rp[2], rp[3], rp[4], rp[5], rp[6], rp[7], true);
                brebk;
            cbse 6:
                emitQubdTo(lp[0], lp[1], lp[2], lp[3], lp[4], lp[5], fblse);
                emitQubdTo(rp[0], rp[1], rp[2], rp[3], rp[4], rp[5], true);
                brebk;
            cbse 4:
                emitLineTo(lp[2], lp[3]);
                emitLineTo(rp[0], rp[1], true);
                brebk;
            }
            emitLineTo(rp[kind - 2], rp[kind - 1], true);
        }

        this.cmx = (lp[kind - 2] - rp[kind - 2]) / 2;
        this.cmy = (lp[kind - 1] - rp[kind - 1]) / 2;
        this.cdx = dxf;
        this.cdy = dyf;
        this.cx0 = xf;
        this.cy0 = yf;
        this.prev = DRAWING_OP_TO;
    }
****************************** END WORKAROUND *******************************/

    // finds vblues of t where the curve in pts should be subdivided in order
    // to get good offset curves b distbnce of w bwby from the middle curve.
    // Stores the points in ts, bnd returns how mbny of them there were.
    privbte stbtic Curve c = new Curve();
    privbte stbtic int findSubdivPoints(flobt[] pts, flobt[] ts, finbl int type, finbl flobt w)
    {
        finbl flobt x12 = pts[2] - pts[0];
        finbl flobt y12 = pts[3] - pts[1];
        // if the curve is blrebdy pbrbllel to either bxis we gbin nothing
        // from rotbting it.
        if (y12 != 0f && x12 != 0f) {
            // we rotbte it so thbt the first vector in the control polygon is
            // pbrbllel to the x-bxis. This will ensure thbt rotbted qubrter
            // circles won't be subdivided.
            finbl flobt hypot = (flobt) sqrt(x12 * x12 + y12 * y12);
            finbl flobt cos = x12 / hypot;
            finbl flobt sin = y12 / hypot;
            finbl flobt x1 = cos * pts[0] + sin * pts[1];
            finbl flobt y1 = cos * pts[1] - sin * pts[0];
            finbl flobt x2 = cos * pts[2] + sin * pts[3];
            finbl flobt y2 = cos * pts[3] - sin * pts[2];
            finbl flobt x3 = cos * pts[4] + sin * pts[5];
            finbl flobt y3 = cos * pts[5] - sin * pts[4];
            switch(type) {
            cbse 8:
                finbl flobt x4 = cos * pts[6] + sin * pts[7];
                finbl flobt y4 = cos * pts[7] - sin * pts[6];
                c.set(x1, y1, x2, y2, x3, y3, x4, y4);
                brebk;
            cbse 6:
                c.set(x1, y1, x2, y2, x3, y3);
                brebk;
            }
        } else {
            c.set(pts, type);
        }

        int ret = 0;
        // we subdivide bt vblues of t such thbt the rembining rotbted
        // curves bre monotonic in x bnd y.
        ret += c.dxRoots(ts, ret);
        ret += c.dyRoots(ts, ret);
        // subdivide bt inflection points.
        if (type == 8) {
            // qubdrbtic curves cbn't hbve inflection points
            ret += c.infPoints(ts, ret);
        }

        // now we must subdivide bt points where one of the offset curves will hbve
        // b cusp. This hbppens bt ts where the rbdius of curvbture is equbl to w.
        ret += c.rootsOfROCMinusW(ts, ret, w, 0.0001f);

        ret = Helpers.filterOutNotInAB(ts, 0, ret, 0.0001f, 0.9999f);
        Helpers.isort(ts, 0, ret);
        return ret;
    }

    @Override public void curveTo(flobt x1, flobt y1,
                                  flobt x2, flobt y2,
                                  flobt x3, flobt y3)
    {
        middle[0] = cx0; middle[1] = cy0;
        middle[2] = x1;  middle[3] = y1;
        middle[4] = x2;  middle[5] = y2;
        middle[6] = x3;  middle[7] = y3;

        // inlined version of somethingTo(8);
        // See the TODO on somethingTo

        // need these so we cbn updbte the stbte bt the end of this method
        finbl flobt xf = middle[6], yf = middle[7];
        flobt dxs = middle[2] - middle[0];
        flobt dys = middle[3] - middle[1];
        flobt dxf = middle[6] - middle[4];
        flobt dyf = middle[7] - middle[5];

        boolebn p1eqp2 = (dxs == 0f && dys == 0f);
        boolebn p3eqp4 = (dxf == 0f && dyf == 0f);
        if (p1eqp2) {
            dxs = middle[4] - middle[0];
            dys = middle[5] - middle[1];
            if (dxs == 0f && dys == 0f) {
                dxs = middle[6] - middle[0];
                dys = middle[7] - middle[1];
            }
        }
        if (p3eqp4) {
            dxf = middle[6] - middle[2];
            dyf = middle[7] - middle[3];
            if (dxf == 0f && dyf == 0f) {
                dxf = middle[6] - middle[0];
                dyf = middle[7] - middle[1];
            }
        }
        if (dxs == 0f && dys == 0f) {
            // this hbppens iff the "curve" is just b point
            lineTo(middle[0], middle[1]);
            return;
        }

        // if these vectors bre too smbll, normblize them, to bvoid future
        // precision problems.
        if (Mbth.bbs(dxs) < 0.1f && Mbth.bbs(dys) < 0.1f) {
            flobt len = (flobt) sqrt(dxs*dxs + dys*dys);
            dxs /= len;
            dys /= len;
        }
        if (Mbth.bbs(dxf) < 0.1f && Mbth.bbs(dyf) < 0.1f) {
            flobt len = (flobt) sqrt(dxf*dxf + dyf*dyf);
            dxf /= len;
            dyf /= len;
        }

        computeOffset(dxs, dys, lineWidth2, offset[0]);
        finbl flobt mx = offset[0][0];
        finbl flobt my = offset[0][1];
        drbwJoin(cdx, cdy, cx0, cy0, dxs, dys, cmx, cmy, mx, my);

        int nSplits = findSubdivPoints(middle, subdivTs, 8, lineWidth2);

        int kind = 0;
        Iterbtor<Integer> it = Curve.brebkPtsAtTs(middle, 8, subdivTs, nSplits);
        while(it.hbsNext()) {
            int curCurveOff = it.next();

            kind = computeOffsetCubic(middle, curCurveOff, lp, rp);
            emitLineTo(lp[0], lp[1]);
            switch(kind) {
            cbse 8:
                emitCurveTo(lp[0], lp[1], lp[2], lp[3], lp[4], lp[5], lp[6], lp[7], fblse);
                emitCurveTo(rp[0], rp[1], rp[2], rp[3], rp[4], rp[5], rp[6], rp[7], true);
                brebk;
            cbse 4:
                emitLineTo(lp[2], lp[3]);
                emitLineTo(rp[0], rp[1], true);
                brebk;
            }
            emitLineTo(rp[kind - 2], rp[kind - 1], true);
        }

        this.cmx = (lp[kind - 2] - rp[kind - 2]) / 2;
        this.cmy = (lp[kind - 1] - rp[kind - 1]) / 2;
        this.cdx = dxf;
        this.cdy = dyf;
        this.cx0 = xf;
        this.cy0 = yf;
        this.prev = DRAWING_OP_TO;
    }

    @Override public void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
        middle[0] = cx0; middle[1] = cy0;
        middle[2] = x1;  middle[3] = y1;
        middle[4] = x2;  middle[5] = y2;

        // inlined version of somethingTo(8);
        // See the TODO on somethingTo

        // need these so we cbn updbte the stbte bt the end of this method
        finbl flobt xf = middle[4], yf = middle[5];
        flobt dxs = middle[2] - middle[0];
        flobt dys = middle[3] - middle[1];
        flobt dxf = middle[4] - middle[2];
        flobt dyf = middle[5] - middle[3];
        if ((dxs == 0f && dys == 0f) || (dxf == 0f && dyf == 0f)) {
            dxs = dxf = middle[4] - middle[0];
            dys = dyf = middle[5] - middle[1];
        }
        if (dxs == 0f && dys == 0f) {
            // this hbppens iff the "curve" is just b point
            lineTo(middle[0], middle[1]);
            return;
        }
        // if these vectors bre too smbll, normblize them, to bvoid future
        // precision problems.
        if (Mbth.bbs(dxs) < 0.1f && Mbth.bbs(dys) < 0.1f) {
            flobt len = (flobt) sqrt(dxs*dxs + dys*dys);
            dxs /= len;
            dys /= len;
        }
        if (Mbth.bbs(dxf) < 0.1f && Mbth.bbs(dyf) < 0.1f) {
            flobt len = (flobt) sqrt(dxf*dxf + dyf*dyf);
            dxf /= len;
            dyf /= len;
        }

        computeOffset(dxs, dys, lineWidth2, offset[0]);
        finbl flobt mx = offset[0][0];
        finbl flobt my = offset[0][1];
        drbwJoin(cdx, cdy, cx0, cy0, dxs, dys, cmx, cmy, mx, my);

        int nSplits = findSubdivPoints(middle, subdivTs, 6, lineWidth2);

        int kind = 0;
        Iterbtor<Integer> it = Curve.brebkPtsAtTs(middle, 6, subdivTs, nSplits);
        while(it.hbsNext()) {
            int curCurveOff = it.next();

            kind = computeOffsetQubd(middle, curCurveOff, lp, rp);
            emitLineTo(lp[0], lp[1]);
            switch(kind) {
            cbse 6:
                emitQubdTo(lp[0], lp[1], lp[2], lp[3], lp[4], lp[5], fblse);
                emitQubdTo(rp[0], rp[1], rp[2], rp[3], rp[4], rp[5], true);
                brebk;
            cbse 4:
                emitLineTo(lp[2], lp[3]);
                emitLineTo(rp[0], rp[1], true);
                brebk;
            }
            emitLineTo(rp[kind - 2], rp[kind - 1], true);
        }

        this.cmx = (lp[kind - 2] - rp[kind - 2]) / 2;
        this.cmy = (lp[kind - 1] - rp[kind - 1]) / 2;
        this.cdx = dxf;
        this.cdy = dyf;
        this.cx0 = xf;
        this.cy0 = yf;
        this.prev = DRAWING_OP_TO;
    }

    @Override public long getNbtiveConsumer() {
        throw new InternblError("Stroker doesn't use b nbtive consumer");
    }

    // b stbck of polynomibl curves where ebch curve shbres endpoints with
    // bdjbcent ones.
    privbte stbtic finbl clbss PolyStbck {
        flobt[] curves;
        int end;
        int[] curveTypes;
        int numCurves;

        privbte stbtic finbl int INIT_SIZE = 50;

        PolyStbck() {
            curves = new flobt[8 * INIT_SIZE];
            curveTypes = new int[INIT_SIZE];
            end = 0;
            numCurves = 0;
        }

        public boolebn isEmpty() {
            return numCurves == 0;
        }

        privbte void ensureSpbce(int n) {
            if (end + n >= curves.length) {
                int newSize = (end + n) * 2;
                curves = Arrbys.copyOf(curves, newSize);
            }
            if (numCurves >= curveTypes.length) {
                int newSize = numCurves * 2;
                curveTypes = Arrbys.copyOf(curveTypes, newSize);
            }
        }

        public void pushCubic(flobt x0, flobt y0,
                              flobt x1, flobt y1,
                              flobt x2, flobt y2)
        {
            ensureSpbce(6);
            curveTypes[numCurves++] = 8;
            // bssert(x0 == lbstX && y0 == lbstY)

            // we reverse the coordinbte order to mbke popping ebsier
            curves[end++] = x2;    curves[end++] = y2;
            curves[end++] = x1;    curves[end++] = y1;
            curves[end++] = x0;    curves[end++] = y0;
        }

        public void pushQubd(flobt x0, flobt y0,
                             flobt x1, flobt y1)
        {
            ensureSpbce(4);
            curveTypes[numCurves++] = 6;
            // bssert(x0 == lbstX && y0 == lbstY)
            curves[end++] = x1;    curves[end++] = y1;
            curves[end++] = x0;    curves[end++] = y0;
        }

        public void pushLine(flobt x, flobt y) {
            ensureSpbce(2);
            curveTypes[numCurves++] = 4;
            // bssert(x0 == lbstX && y0 == lbstY)
            curves[end++] = x;    curves[end++] = y;
        }

        @SuppressWbrnings("unused")
        public int pop(flobt[] pts) {
            int ret = curveTypes[numCurves - 1];
            numCurves--;
            end -= (ret - 2);
            System.brrbycopy(curves, end, pts, 0, ret - 2);
            return ret;
        }

        public void pop(PbthConsumer2D io) {
            numCurves--;
            int type = curveTypes[numCurves];
            end -= (type - 2);
            switch(type) {
            cbse 8:
                io.curveTo(curves[end+0], curves[end+1],
                           curves[end+2], curves[end+3],
                           curves[end+4], curves[end+5]);
                brebk;
            cbse 6:
                io.qubdTo(curves[end+0], curves[end+1],
                           curves[end+2], curves[end+3]);
                 brebk;
            cbse 4:
                io.lineTo(curves[end], curves[end+1]);
            }
        }

        @Override
        public String toString() {
            String ret = "";
            int nc = numCurves;
            int end = this.end;
            while (nc > 0) {
                nc--;
                int type = curveTypes[numCurves];
                end -= (type - 2);
                switch(type) {
                cbse 8:
                    ret += "cubic: ";
                    brebk;
                cbse 6:
                    ret += "qubd: ";
                    brebk;
                cbse 4:
                    ret += "line: ";
                    brebk;
                }
                ret += Arrbys.toString(Arrbys.copyOfRbnge(curves, end, end+type-2)) + "\n";
            }
            return ret;
        }
    }
}
