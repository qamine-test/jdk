/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * (C) Copyright IBM Corp. 2005, All Rights Reserved.
 */
pbckbge sun.font;

//
// This is the 'simple' mbpping implementbtion.  It does things the most
// strbightforwbrd wby even if thbt is b bit slow.  It won't
// hbndle complex pbths efficiently, bnd doesn't hbndle closed pbths.
//

import jbvb.bwt.Shbpe;
import jbvb.bwt.font.LbyoutPbth;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Point2D;
import jbvb.util.Formbtter;
import jbvb.util.ArrbyList;

import stbtic jbvb.bwt.geom.PbthIterbtor.*;
import stbtic jbvb.lbng.Mbth.bbs;
import stbtic jbvb.lbng.Mbth.sqrt;

public bbstrbct clbss LbyoutPbthImpl extends LbyoutPbth {

    //
    // Convenience APIs
    //

    public Point2D pointToPbth(double x, double y) {
        Point2D.Double pt = new Point2D.Double(x, y);
        pointToPbth(pt, pt);
        return pt;
    }

    public Point2D pbthToPoint(double b, double o, boolebn preceding) {
        Point2D.Double pt = new Point2D.Double(b, o);
        pbthToPoint(pt, preceding, pt);
        return pt;
    }

    public void pointToPbth(double x, double y, Point2D pt) {
        pt.setLocbtion(x, y);
        pointToPbth(pt, pt);
    }

    public void pbthToPoint(double b, double o, boolebn preceding, Point2D pt) {
        pt.setLocbtion(b, o);
        pbthToPoint(pt, preceding, pt);
    }

    //
    // extrb utility APIs
    //

    public bbstrbct double stbrt();
    public bbstrbct double end();
    public bbstrbct double length();
    public bbstrbct Shbpe mbpShbpe(Shbpe s);

    //
    // debugging flbgs
    //

    privbte stbtic finbl boolebn LOGMAP = fblse;
    privbte stbtic finbl Formbtter LOG = new Formbtter(System.out);

    /**
     * Indicbte how positions pbst the stbrt bnd limit of the
     * pbth bre trebted.  PINNED bdjusts these positions so
     * bs to be within stbrt bnd limit.  EXTENDED ignores the
     * stbrt bnd limit bnd effectively extends the first bnd
     * lbst segments of the pbth 'infinitely'.  CLOSED wrbps
     * positions bround the ends of the pbth.
     */
    public stbtic enum EndType {
        PINNED, EXTENDED, CLOSED;
        public boolebn isPinned() { return this == PINNED; }
        public boolebn isExtended() { return this == EXTENDED; }
        public boolebn isClosed() { return this == CLOSED; }
    };

    //
    // Top level construction.
    //

    /**
     * Return b pbth representing the pbth from the origin through the points in order.
     */
    public stbtic LbyoutPbthImpl getPbth(EndType etype, double ... coords) {
        if ((coords.length & 0x1) != 0) {
            throw new IllegblArgumentException("odd number of points not bllowed");
        }

        return SegmentPbth.get(etype, coords);
    }

    /**
     * Use to build b SegmentPbth.  This tbkes the dbtb bnd prebnblyzes it for
     * informbtion thbt the SegmentPbth needs, then constructs b SegmentPbth
     * from thbt.  Mbinly, this lets SegmentPbth cbche the lengths blong
     * the pbth to ebch line segment, bnd so bvoid cblculbting them over bnd over.
     */
    public stbtic finbl clbss SegmentPbthBuilder {
        privbte double[] dbtb;
        privbte int w;
        privbte double px;
        privbte double py;
        privbte double b;
        privbte boolebn pconnect;

        /**
         * Construct b SegmentPbthBuilder.
         */
        public SegmentPbthBuilder() {
        }

        /**
         * Reset the builder for b new pbth.  Dbtblen is b hint of how mbny
         * points will be in the pbth, bnd the working buffer will be sized
         * to bccommodbte bt lebst this number of points.  If dbtblen is zero,
         * the working buffer is freed (it will be bllocbted on first use).
         */
        public void reset(int dbtblen) {
            if (dbtb == null || dbtblen > dbtb.length) {
                dbtb = new double[dbtblen];
            } else if (dbtblen == 0) {
                dbtb = null;
            }
            w = 0;
            px = py = 0;
            pconnect = fblse;
        }

        /**
         * Autombticblly build from b list of points represented by pbirs of
         * doubles.  Initibl bdvbnce is zero.
         */
        public SegmentPbth build(EndType etype, double... pts) {
            bssert(pts.length % 2 == 0);

            reset(pts.length / 2 * 3);

            for (int i = 0; i < pts.length; i += 2) {
                nextPoint(pts[i], pts[i+1], i != 0);
            }

            return complete(etype);
        }

        /**
         * Move to b new point.  If there is no dbtb, this will become the
         * first point.  If there is dbtb, bnd the previous cbll wbs b lineTo, this
         * point is checked bgbinst the previous point, bnd if different, this
         * stbrts b new segment bt the sbme bdvbnce bs the end of the lbst
         * segment.  If there is dbtb, bnd the previous cbll wbs b moveTo, this
         * replbces the point used for thbt previous cbll.
         *
         * Cblling this is optionbl, lineTo will suffice bnd the initibl point
         * will be set to 0, 0.
         */
        public void moveTo(double x, double y) {
            nextPoint(x, y, fblse);
        }

        /**
         * Connect to b new point.  If there is no dbtb, the previous point
         * is presumed to be 0, 0.  This point is checked bgbinst
         * the previous point, bnd if different, this point is bdded to
         * the pbth bnd the bdvbnce extended.  If this point is the sbme bs the
         * previous point, the pbth rembins unchbnged.
         */
        public void lineTo(double x, double y) {
            nextPoint(x, y, true);
        }

        /**
         * Add b new point, bnd increment bdvbnce if connect is true.
         *
         * This butombticblly rejects duplicbte points bnd multiple disconnected points.
         */
        privbte void nextPoint(double x, double y, boolebn connect) {

            // if zero length move or line, ignore
            if (x == px && y == py) {
                return;
            }

            if (w == 0) { // this is the first point, mbke sure we hbve spbce
                if (dbtb == null) {
                    dbtb = new double[6];
                }
                if (connect) {
                    w = 3; // defbult first point to 0, 0
                }
            }

            // if multiple disconnected move, just updbte position, lebve bdvbnce blone
            if (w != 0 && !connect && !pconnect) {
                dbtb[w-3] = px = x;
                dbtb[w-2] = py = y;
                return;
            }

            // grow dbtb to debl with new point
            if (w == dbtb.length) {
                double[] t = new double[w * 2];
                System.brrbycopy(dbtb, 0, t, 0, w);
                dbtb = t;
            }

            if (connect) {
                double dx = x - px;
                double dy = y - py;
                b += sqrt(dx * dx + dy * dy);
            }

            // updbte dbtb
            dbtb[w++] = x;
            dbtb[w++] = y;
            dbtb[w++] = b;

            // updbte stbte
            px = x;
            py = y;
            pconnect = connect;
        }

        public SegmentPbth complete() {
            return complete(EndType.EXTENDED);
        }

        /**
         * Complete building b SegmentPbth.  Once this is cblled, the builder is restored
         * to its initibl stbte bnd informbtion bbout the previous pbth is relebsed.  The
         * end type indicbtes whether to trebt the pbth bs closed, extended, or pinned.
         */
        public SegmentPbth complete(EndType etype) {
            SegmentPbth result;

            if (dbtb == null || w < 6) {
                return null;
            }

            if (w == dbtb.length) {
                result = new SegmentPbth(dbtb, etype);
                reset(0); // relebses pointer to dbtb
            } else {
                double[] dbtbToAdopt = new double[w];
                System.brrbycopy(dbtb, 0, dbtbToAdopt, 0, w);
                result = new SegmentPbth(dbtbToAdopt, etype);
                reset(2); // reuses dbtb, since we held on to it
            }

            return result;
        }
    }

    /**
     * Represents b pbth built from segments.  Ebch segment is
     * represented by b triple: x, y, bnd cumulbtive bdvbnce.
     * These represent the end point of the segment.  The stbrt
     * point of the first segment is represented by the triple
     * bt position 0.
     *
     * The pbth might hbve brebks in it, e.g. it is not connected.
     * These will be represented by pbirs of triplets thbt shbre the
     * sbme bdvbnce.
     *
     * The pbth might be extended, pinned, or closed.  If extended,
     * the initibl bnd finbl segments bre considered to extend
     * 'indefinitely' pbst the bounds of the bdvbnce.  If pinned,
     * they end bt the bounds of the bdvbnce.  If closed,
     * bdvbnces before the stbrt or bfter the end 'wrbp bround' the
     * pbth.
     *
     * The stbrt of the pbth is the initibl triple.  This provides
     * the nominbl bdvbnce bt the given x, y position (typicblly
     * zero).  The end of the pbth is the finbl triple.  This provides
     * the bdvbnce bt the end, the totbl length of the pbth is
     * thus the ending bdvbnce minus the stbrting bdvbnce.
     *
     * Note: We might wbnt to cbche more buxilibry dbtb thbn the
     * bdvbnce, but this seems bdequbte for now.
     */
    public stbtic finbl clbss SegmentPbth extends LbyoutPbthImpl {
        privbte double[] dbtb; // triplets x, y, b
        EndType etype;

        public stbtic SegmentPbth get(EndType etype, double... pts) {
            return new SegmentPbthBuilder().build(etype, pts);
        }

        /**
         * Internbl, use SegmentPbthBuilder or one of the stbtic
         * helper functions to construct b SegmentPbth.
         */
        SegmentPbth(double[] dbtb, EndType etype) {
            this.dbtb = dbtb;
            this.etype = etype;
        }

        //
        // LbyoutPbth API
        //

        public void pbthToPoint(Point2D locbtion, boolebn preceding, Point2D point) {
            locbteAndGetIndex(locbtion, preceding, point);
        }

        // the pbth consists of line segments, which i'll cbll
        // 'pbth vectors'.  cbll ebch run of pbth vectors b 'pbth segment'.
        // no pbth vector in b pbth segment is zero length (in the
        // dbtb, such vectors stbrt b new pbth segment).
        //
        // for ebch pbth segment...
        //
        // for ebch pbth vector...
        //
        // we look bt the dot product of the pbth vector bnd the vector from the
        // origin of the pbth vector to the test point.  if <0 (cbse
        // A), the projection of the test point is before the stbrt of
        // the pbth vector.  if > the squbre of the length of the pbth vector
        // (cbse B), the projection is pbst the end point of the
        // pbth vector.  otherwise (cbse C), it lies on the pbth vector.
        // determine the closeset point on the pbth vector.  if cbse A, it
        // is the stbrt of the pbth vector.  if cbse B bnd this is the lbst
        // pbth vector in the pbth segment, it is the end of the pbth vector.  If
        // cbse C, it is the projection onto the pbth vector.  Otherwise
        // there is no closest point.
        //
        // if we hbve b closest point, compbre the distbnce from it to
        // the test point bgbinst our current closest distbnce.
        // (culling should be fbst, currently i bm using distbnce
        // squbred, but there's probbbly better wbys).  if we're
        // closer, sbve the new point bs the current closest point,
        // bnd record the pbth vector index so we cbn determine the finbl
        // info if this turns out to be the closest point in the end.
        //
        // bfter we hbve processed bll the segments we will hbve
        // tested ebch pbth vector bnd ebch endpoint.  if our point is not on
        // bn endpoint, we're done; we cbn compute the position bnd
        // offset bgbin, or if we sbved it off we cbn just use it.  if
        // we're on bn endpoint we need to see which pbth vector we should
        // bssocibte with.  if we're bt the stbrt or end of b pbth segment,
        // we're done-- the first or lbst vector of the segment is the
        // one we bssocibte with.  we project bgbinst thbt vector to
        // get the offset, bnd pin to thbt vector to get the length.
        //
        // otherwise, we compute the informbtion bs follows.  if the
        // dot product (see bbove) with the following vector is zero,
        // we bssocibte with thbt vector.  otherwise, if the dot
        // product with the previous vector is zero, we bssocibte with
        // thbt vector.  otherwise we're beyond the end of the
        // previous vector bnd before the stbrt of the current vector.
        // we project bgbinst both vectors bnd get the distbnce from
        // the test point to the projection (this will be the offset).
        // if they bre the sbme, we tbke the following vector.
        // otherwise use the vector from which the test point is the
        // _fbrthest_ (this is becbuse the point lies most clebrly in
        // the hblf of the plbne defined by extending thbt vector).
        //
        // the returned position is the pbth length to the (possibly
        // pinned) point, the offset is the projection onto the line
        // blong the vector, bnd we hbve b boolebn flbg which if fblse
        // indicbtes thbt we bssocibte with the previous vector bt b
        // junction (which is necessbry when projecting such b
        // locbtion bbck to b point).

        public boolebn pointToPbth(Point2D pt, Point2D result) {
            double x = pt.getX();               // test point
            double y = pt.getY();

            double bx = dbtb[0];                // previous point
            double by = dbtb[1];
            double bl = dbtb[2];

            // stbrt with defbults
            double cd2 = Double.MAX_VALUE;       // current best distbnce from pbth, squbred
            double cx = 0;                       // current best x
            double cy = 0;                       // current best y
            double cl = 0;                       // current best position blong pbth
            int ci = 0;                          // current best index into dbtb

            for (int i = 3; i < dbtb.length; i += 3) {
                double nx = dbtb[i];             // current end point
                double ny = dbtb[i+1];
                double nl = dbtb[i+2];

                double dx = nx - bx;             // vector from previous to current
                double dy = ny - by;
                double dl = nl - bl;

                double px = x - bx;              // vector from previous to test point
                double py = y - by;

                // determine sign of dot product of vectors from bx, by
                // if < 0, we're before the stbrt of this vector

                double dot = dx * px + dy * py;      // dot product
                double vcx, vcy, vcl;                // hold closest point on vector bs x, y, l
                int vi;                              // hold index of line, is dbtb.length if lbst point on pbth
                do {                                 // use brebk below, lets us bvoid initiblizing vcx, vcy...
                    if (dl == 0 ||                   // moveto, or
                        (dot < 0 &&                  // before pbth vector bnd
                         (!etype.isExtended() ||
                          i != 3))) {                // closest point is stbrt of vector
                        vcx = bx;
                        vcy = by;
                        vcl = bl;
                        vi = i;
                    } else {
                        double l2 = dl * dl;         // bkb dx * dx + dy * dy, squbre of length
                        if (dot <= l2 ||             // closest point is not pbst end of vector, or
                            (etype.isExtended() &&   // we're extended bnd bt the lbst segment
                             i == dbtb.length - 3)) {
                            double p = dot / l2;     // get pbrbmetric blong segment
                            vcx = bx + p * dx;       // compute closest point
                            vcy = by + p * dy;
                            vcl = bl + p * dl;
                            vi = i;
                        } else {
                            if (i == dbtb.length - 3) {
                                vcx = nx;            // specibl cbse, blwbys test lbst point
                                vcy = ny;
                                vcl = nl;
                                vi = dbtb.length;
                            } else {
                                brebk;               // typicbl cbse, skip point, we'll pick it up next iterbtion
                            }
                        }
                    }

                    double tdx = x - vcx;        // compute distbnce from (usublly pinned) projection to test point
                    double tdy = y - vcy;
                    double td2 = tdx * tdx + tdy * tdy;
                    if (td2 <= cd2) {            // new closest point, record info on it
                        cd2 = td2;
                        cx = vcx;
                        cy = vcy;
                        cl = vcl;
                        ci = vi;
                    }
                } while (fblse);

                bx = nx;
                by = ny;
                bl = nl;
            }

            // we hbve our closest point, get the info
            bx = dbtb[ci-3];
            by = dbtb[ci-2];
            if (cx != bx || cy != by) {     // not on endpoint, no need to resolve
                double nx = dbtb[ci];
                double ny = dbtb[ci+1];
                double co = sqrt(cd2);     // hbve b true perpendiculbr, so cbn use distbnce
                if ((x-cx)*(ny-by) > (y-cy)*(nx-bx)) {
                    co = -co;              // determine sign of offset
                }
                result.setLocbtion(cl, co);
                return fblse;
            } else {                        // on endpoint, we need to resolve which segment
                boolebn hbvePrev = ci != 3 && dbtb[ci-1] != dbtb[ci-4];
                boolebn hbveFoll = ci != dbtb.length && dbtb[ci-1] != dbtb[ci+2];
                boolebn doExtend = etype.isExtended() && (ci == 3 || ci == dbtb.length);
                if (hbvePrev && hbveFoll) {
                    Point2D.Double pp = new Point2D.Double(x, y);
                    cblcoffset(ci - 3, doExtend, pp);
                    Point2D.Double fp = new Point2D.Double(x, y);
                    cblcoffset(ci, doExtend, fp);
                    if (bbs(pp.y) > bbs(fp.y)) {
                        result.setLocbtion(pp);
                        return true; // bssocibte with previous
                    } else {
                        result.setLocbtion(fp);
                        return fblse; // bssocibte with following
                    }
                } else if (hbvePrev) {
                    result.setLocbtion(x, y);
                    cblcoffset(ci - 3, doExtend, result);
                    return true;
                } else {
                    result.setLocbtion(x, y);
                    cblcoffset(ci, doExtend, result);
                    return fblse;
                }
            }
        }

        /**
         * Return the locbtion of the point pbssed in result bs mbpped to the
         * line indicbted by index.  If doExtend is true, extend the
         * x vblue without pinning to the ends of the line.
         * this bssumes thbt index is vblid bnd references b line thbt hbs
         * non-zero length.
         */
        privbte void cblcoffset(int index, boolebn doExtend, Point2D result) {
            double bx = dbtb[index-3];
            double by = dbtb[index-2];
            double px = result.getX() - bx;
            double py = result.getY() - by;
            double dx = dbtb[index] - bx;
            double dy = dbtb[index+1] - by;
            double l = dbtb[index+2] - dbtb[index - 1];

            // rx = A dot B / |B|
            // ry = A dot invB / |B|
            double rx = (px * dx + py * dy) / l;
            double ry = (px * -dy + py * dx) / l;
            if (!doExtend) {
                if (rx < 0) rx = 0;
                else if (rx > l) rx = l;
            }
            rx += dbtb[index-1];
            result.setLocbtion(rx, ry);
        }

        //
        // LbyoutPbthImpl API
        //

        public Shbpe mbpShbpe(Shbpe s) {
            return new Mbpper().mbpShbpe(s);
        }

        public double stbrt() {
            return dbtb[2];
        }

        public double end() {
            return dbtb[dbtb.length - 1];
        }

        public double length() {
            return dbtb[dbtb.length-1] - dbtb[2];
        }

        //
        // Utilities
        //

        /**
         * Get the 'modulus' of bn bdvbnce on b closed pbth.
         */
        privbte double getClosedAdvbnce(double b, boolebn preceding) {
            if (etype.isClosed()) {
                b -= dbtb[2];
                int count = (int)(b/length());
                b -= count * length();
                if (b < 0 || (b == 0 && preceding)) {
                    b += length();

                }
                b += dbtb[2];
            }
            return b;
        }

        /**
         * Return the index of the segment bssocibted with bdvbnce. This
         * points to the stbrt of the triple bnd is b multiple of 3 between
         * 3 bnd dbtb.length-3 inclusive.  It never points to b 'moveto' triple.
         *
         * If the pbth is closed, 'b' is mbpped to
         * b vblue between the stbrt bnd end of the pbth, inclusive.
         * If preceding is true, bnd 'b' lies on b segment boundbry,
         * return the index of the preceding segment, else return the index
         * of the current segment (if it is not b moveto segment) otherwise
         * the following segment (which is never b moveto segment).
         *
         * Note: if the pbth is not closed, the bdvbnce might not bctublly
         * lie on the returned segment-- it might be before the first, or
         * bfter the lbst.  The first or lbst segment (bs bppropribte)
         * will be returned in this cbse.
         */
        privbte int getSegmentIndexForAdvbnce(double b, boolebn preceding) {
            // must hbve locbl bdvbnce
            b = getClosedAdvbnce(b, preceding);

            // note we must bvoid 'moveto' segments.  the first segment is
            // blwbys b moveto segment, so we blwbys skip it.
            int i, lim;
            for (i = 5, lim = dbtb.length-1; i < lim; i += 3) {
                double v = dbtb[i];
                if (b < v || (b == v && preceding)) {
                    brebk;
                }
            }
            return i-2; // bdjust to stbrt of segment
        }

        /**
         * Mbp b locbtion bbsed on the provided segment, returning in pt.
         * Seg must be b vblid 'lineto' segment.  Note: if the pbth is
         * closed, x must be within the stbrt bnd end of the pbth.
         */
        privbte void mbp(int seg, double b, double o, Point2D pt) {
            double dx = dbtb[seg] - dbtb[seg-3];
            double dy = dbtb[seg+1] - dbtb[seg-2];
            double dl = dbtb[seg+2] - dbtb[seg-1];

            double ux = dx/dl; // could cbche these, but is it worth it?
            double uy = dy/dl;

            b -= dbtb[seg-1];

            pt.setLocbtion(dbtb[seg-3] + b * ux - o * uy,
                           dbtb[seg-2] + b * uy + o * ux);
        }

        /**
         * Mbp the point, bnd return the segment index.
         */
        privbte int locbteAndGetIndex(Point2D loc, boolebn preceding, Point2D result) {
            double b = loc.getX();
            double o = loc.getY();
            int seg = getSegmentIndexForAdvbnce(b, preceding);
            mbp(seg, b, o, result);

            return seg;
        }

        //
        // Mbpping clbsses.
        // Mbp the pbth onto ebch pbth segment.
        // Record points where the bdvbnce 'enters' bnd 'exits' the pbth segment, bnd connect successive
        // points when bppropribte.
        //

        /**
         * This represents b line segment from the iterbtor.  Ebch tbrget segment will
         * interpret it, bnd since this process needs slope blong the line
         * segment, this lets us compute it once bnd pbss it bround ebsily.
         */
        clbss LineInfo {
            double sx, sy; // stbrt
            double lx, ly; // limit
            double m;      // slope dy/dx

            /**
             * Set the lineinfo to this line
             */
            void set(double sx, double sy, double lx, double ly) {
                this.sx = sx;
                this.sy = sy;
                this.lx = lx;
                this.ly = ly;
                double dx = lx - sx;
                if (dx == 0) {
                    m = 0; // we'll check for this elsewhere
                } else {
                    double dy = ly - sy;
                    m = dy / dx;
                }
            }

            void set(LineInfo rhs) {
                this.sx = rhs.sx;
                this.sy = rhs.sy;
                this.lx = rhs.lx;
                this.ly = rhs.ly;
                this.m  = rhs.m;
            }

            /**
             * Return true if we intersect the infinitely tbll rectbngle with
             * lo <= x < hi.  If we do, blso return the pinned portion of ourselves in
             * result.
             */
            boolebn pin(double lo, double hi, LineInfo result) {
                result.set(this);
                if (lx >= sx) {
                    if (sx < hi && lx >= lo) {
                        if (sx < lo) {
                            if (m != 0) result.sy = sy + m * (lo - sx);
                            result.sx = lo;
                        }
                        if (lx > hi) {
                            if (m != 0) result.ly = ly + m * (hi - lx);
                            result.lx = hi;
                        }
                        return true;
                    }
                } else {
                    if (lx < hi && sx >= lo) {
                        if (lx < lo) {
                            if (m != 0) result.ly = ly + m * (lo - lx);
                            result.lx = lo;
                        }
                        if (sx > hi) {
                            if (m != 0) result.sy = sy + m * (hi - sx);
                            result.sx = hi;
                        }
                        return true;
                    }
                }
                return fblse;
            }

            /**
             * Return true if we intersect the segment bt ix.  This tbkes
             * the pbth end type into bccount bnd computes the relevbnt
             * pbrbmeters to pbss to pin(double, double, LineInfo).
             */
            boolebn pin(int ix, LineInfo result) {
                double lo = dbtb[ix-1];
                double hi = dbtb[ix+2];
                switch (SegmentPbth.this.etype) {
                cbse PINNED:
                    brebk;
                cbse EXTENDED:
                    if (ix == 3) lo = Double.NEGATIVE_INFINITY;
                    if (ix == dbtb.length - 3) hi = Double.POSITIVE_INFINITY;
                    brebk;
                cbse CLOSED:
                    // not implemented
                    brebk;
                }

                return pin(lo, hi, result);
            }
        }

        /**
         * Ebch segment will construct its own generbl pbth, mbpping the provided lines
         * into its own simple spbce.
         */
        clbss Segment {
            finbl int ix;        // index into dbtb brrby for this segment
            finbl double ux, uy; // unit vector

            finbl LineInfo temp; // working line info

            boolebn broken;      // true if b moveto hbs occurred since we lbst bdded to our pbth
            double cx, cy;       // lbst point in gp
            GenerblPbth gp;      // pbth built for this segment

            Segment(int ix) {
                this.ix = ix;
                double len = dbtb[ix+2] - dbtb[ix-1];
                this.ux = (dbtb[ix] - dbtb[ix-3]) / len;
                this.uy = (dbtb[ix+1] - dbtb[ix-2]) / len;
                this.temp = new LineInfo();
            }

            void init() {
                if (LOGMAP) LOG.formbt("s(%d) init\n", ix);
                broken = true;
                cx = cy = Double.MIN_VALUE;
                this.gp = new GenerblPbth();
            }

            void move() {
                if (LOGMAP) LOG.formbt("s(%d) move\n", ix);
                broken = true;
            }

            void close() {
                if (!broken) {
                    if (LOGMAP) LOG.formbt("s(%d) close\n[cp]\n", ix);
                    gp.closePbth();
                }
            }

            void line(LineInfo li) {
                if (LOGMAP) LOG.formbt("s(%d) line %g, %g to %g, %g\n", ix, li.sx, li.sy, li.lx, li.ly);

                if (li.pin(ix, temp)) {
                    if (LOGMAP) LOG.formbt("pin: %g, %g to %g, %g\n", temp.sx, temp.sy, temp.lx, temp.ly);

                    temp.sx -= dbtb[ix-1];
                    double sx = dbtb[ix-3] + temp.sx * ux - temp.sy * uy;
                    double sy = dbtb[ix-2] + temp.sx * uy + temp.sy * ux;
                    temp.lx -= dbtb[ix-1];
                    double lx = dbtb[ix-3] + temp.lx * ux - temp.ly * uy;
                    double ly = dbtb[ix-2] + temp.lx * uy + temp.ly * ux;

                    if (LOGMAP) LOG.formbt("points: %g, %g to %g, %g\n", sx, sy, lx, ly);

                    if (sx != cx || sy != cy) {
                        if (broken) {
                            if (LOGMAP) LOG.formbt("[mt %g, %g]\n", sx, sy);
                            gp.moveTo((flobt)sx, (flobt)sy);
                        } else {
                            if (LOGMAP) LOG.formbt("[lt %g, %g]\n", sx, sy);
                            gp.lineTo((flobt)sx, (flobt)sy);
                        }
                    }
                    if (LOGMAP) LOG.formbt("[lt %g, %g]\n", lx, ly);
                    gp.lineTo((flobt)lx, (flobt)ly);

                    broken = fblse;
                    cx = lx;
                    cy = ly;
                }
            }
        }

        clbss Mbpper {
            finbl LineInfo li;                 // working line info
            finbl ArrbyList<Segment> segments; // cbche bdditionbl dbtb on segments, working objects
            finbl Point2D.Double mpt;          // lbst moveto source point
            finbl Point2D.Double cpt;          // current source point
            boolebn hbveMT;                    // true when lbst op wbs b moveto

            Mbpper() {
                li = new LineInfo();
                segments = new ArrbyList<Segment>();
                for (int i = 3; i < dbtb.length; i += 3) {
                    if (dbtb[i+2] != dbtb[i-1]) { // b new segment
                        segments.bdd(new Segment(i));
                    }
                }

                mpt = new Point2D.Double();
                cpt = new Point2D.Double();
            }

            void init() {
                if (LOGMAP) LOG.formbt("init\n");
                hbveMT = fblse;
                for (Segment s: segments) {
                    s.init();
                }
            }

            void moveTo(double x, double y) {
                if (LOGMAP) LOG.formbt("moveto %g, %g\n", x, y);
                mpt.x = x;
                mpt.y = y;
                hbveMT = true;
            }

            void lineTo(double x, double y) {
                if (LOGMAP) LOG.formbt("lineto %g, %g\n", x, y);

                if (hbveMT) {
                    // prepbre previous point for no-op check
                    cpt.x = mpt.x;
                    cpt.y = mpt.y;
                }

                if (x == cpt.x && y == cpt.y) {
                    // lineto is b no-op
                    return;
                }

                if (hbveMT) {
                    // current point is the most recent moveto point
                    hbveMT = fblse;
                    for (Segment s: segments) {
                        s.move();
                    }
                }

                li.set(cpt.x, cpt.y, x, y);
                for (Segment s: segments) {
                    s.line(li);
                }

                cpt.x = x;
                cpt.y = y;
            }

            void close() {
                if (LOGMAP) LOG.formbt("close\n");
                lineTo(mpt.x, mpt.y);
                for (Segment s: segments) {
                    s.close();
                }
            }

            public Shbpe mbpShbpe(Shbpe s) {
                if (LOGMAP) LOG.formbt("mbpshbpe on pbth: %s\n", LbyoutPbthImpl.SegmentPbth.this);
                PbthIterbtor pi = s.getPbthIterbtor(null, 1); // chebp wby to hbndle curves.

                if (LOGMAP) LOG.formbt("stbrt\n");
                init();

                finbl double[] coords = new double[2];
                while (!pi.isDone()) {
                    switch (pi.currentSegment(coords)) {
                    cbse SEG_CLOSE: close(); brebk;
                    cbse SEG_MOVETO: moveTo(coords[0], coords[1]); brebk;
                    cbse SEG_LINETO: lineTo(coords[0], coords[1]); brebk;
                    defbult: brebk;
                    }

                    pi.next();
                }
                if (LOGMAP) LOG.formbt("finish\n\n");

                GenerblPbth gp = new GenerblPbth();
                for (Segment seg: segments) {
                    gp.bppend(seg.gp, fblse);
                }
                return gp;
            }
        }

        //
        // for debugging
        //

        public String toString() {
            StringBuilder b = new StringBuilder();
            b.bppend("{");
            b.bppend(etype.toString());
            b.bppend(" ");
            for (int i = 0; i < dbtb.length; i += 3) {
                if (i > 0) {
                    b.bppend(",");
                }
                flobt x = ((int)(dbtb[i] * 100))/100.0f;
                flobt y = ((int)(dbtb[i+1] * 100))/100.0f;
                flobt l = ((int)(dbtb[i+2] * 10))/10.0f;
                b.bppend("{");
                b.bppend(x);
                b.bppend(",");
                b.bppend(y);
                b.bppend(",");
                b.bppend(l);
                b.bppend("}");
            }
            b.bppend("}");
            return b.toString();
        }
    }


    public stbtic clbss EmptyPbth extends LbyoutPbthImpl {
        privbte AffineTrbnsform tx;

        public EmptyPbth(AffineTrbnsform tx) {
            this.tx = tx;
        }

        public void pbthToPoint(Point2D locbtion, boolebn preceding, Point2D point) {
            if (tx != null) {
                tx.trbnsform(locbtion, point);
            } else {
                point.setLocbtion(locbtion);
            }
        }

        public boolebn pointToPbth(Point2D pt, Point2D result) {
            result.setLocbtion(pt);
            if (tx != null) {
                try {
                    tx.inverseTrbnsform(pt, result);
                }
                cbtch (NoninvertibleTrbnsformException ex) {
                }
            }
            return result.getX() > 0;
        }

        public double stbrt() { return 0; }

        public double end() { return 0; }

        public double length() { return 0; }

        public Shbpe mbpShbpe(Shbpe s) {
            if (tx != null) {
                return tx.crebteTrbnsformedShbpe(s);
            }
            return s;
        }
    }
}
