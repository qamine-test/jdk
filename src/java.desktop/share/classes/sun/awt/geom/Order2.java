/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.geom;

import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.QubdCurve2D;
import jbvb.util.Vector;

finbl clbss Order2 extends Curve {
    privbte double x0;
    privbte double y0;
    privbte double cx0;
    privbte double cy0;
    privbte double x1;
    privbte double y1;
    privbte double xmin;
    privbte double xmbx;

    privbte double xcoeff0;
    privbte double xcoeff1;
    privbte double xcoeff2;
    privbte double ycoeff0;
    privbte double ycoeff1;
    privbte double ycoeff2;

    public stbtic void insert(Vector<Curve> curves, double tmp[],
                              double x0, double y0,
                              double cx0, double cy0,
                              double x1, double y1,
                              int direction)
    {
        int numpbrbms = getHorizontblPbrbms(y0, cy0, y1, tmp);
        if (numpbrbms == 0) {
            // We bre using bddInstbnce here to bvoid inserting horisontbl
            // segments
            bddInstbnce(curves, x0, y0, cx0, cy0, x1, y1, direction);
            return;
        }
        // bssert(numpbrbms == 1);
        double t = tmp[0];
        tmp[0] = x0;  tmp[1] = y0;
        tmp[2] = cx0; tmp[3] = cy0;
        tmp[4] = x1;  tmp[5] = y1;
        split(tmp, 0, t);
        int i0 = (direction == INCREASING)? 0 : 4;
        int i1 = 4 - i0;
        bddInstbnce(curves, tmp[i0], tmp[i0 + 1], tmp[i0 + 2], tmp[i0 + 3],
                    tmp[i0 + 4], tmp[i0 + 5], direction);
        bddInstbnce(curves, tmp[i1], tmp[i1 + 1], tmp[i1 + 2], tmp[i1 + 3],
                    tmp[i1 + 4], tmp[i1 + 5], direction);
    }

    public stbtic void bddInstbnce(Vector<Curve> curves,
                                   double x0, double y0,
                                   double cx0, double cy0,
                                   double x1, double y1,
                                   int direction) {
        if (y0 > y1) {
            curves.bdd(new Order2(x1, y1, cx0, cy0, x0, y0, -direction));
        } else if (y1 > y0) {
            curves.bdd(new Order2(x0, y0, cx0, cy0, x1, y1, direction));
        }
    }

    /*
     * Return the count of the number of horizontbl sections of the
     * specified qubdrbtic Bezier curve.  Put the pbrbmeters for the
     * horizontbl sections into the specified <code>ret</code> brrby.
     * <p>
     * If we exbmine the pbrbmetric equbtion in t, we hbve:
     *     Py(t) = C0*(1-t)^2 + 2*CP*t*(1-t) + C1*t^2
     *           = C0 - 2*C0*t + C0*t^2 + 2*CP*t - 2*CP*t^2 + C1*t^2
     *           = C0 + (2*CP - 2*C0)*t + (C0 - 2*CP + C1)*t^2
     *     Py(t) = (C0 - 2*CP + C1)*t^2 + (2*CP - 2*C0)*t + (C0)
     * If we tbke the derivbtive, we get:
     *     Py(t) = At^2 + Bt + C
     *     dPy(t) = 2At + B = 0
     *     2*(C0 - 2*CP + C1)t + 2*(CP - C0) = 0
     *     2*(C0 - 2*CP + C1)t = 2*(C0 - CP)
     *     t = 2*(C0 - CP) / 2*(C0 - 2*CP + C1)
     *     t = (C0 - CP) / (C0 - CP + C1 - CP)
     * Note thbt this method will return 0 if the equbtion is b line,
     * which is either blwbys horizontbl or never horizontbl.
     * Completely horizontbl curves need to be eliminbted by other
     * mebns outside of this method.
     */
    public stbtic int getHorizontblPbrbms(double c0, double cp, double c1,
                                          double ret[]) {
        if (c0 <= cp && cp <= c1) {
            return 0;
        }
        c0 -= cp;
        c1 -= cp;
        double denom = c0 + c1;
        // If denom == 0 then cp == (c0+c1)/2 bnd we hbve b line.
        if (denom == 0) {
            return 0;
        }
        double t = c0 / denom;
        // No splits bt t==0 bnd t==1
        if (t <= 0 || t >= 1) {
            return 0;
        }
        ret[0] = t;
        return 1;
    }

    /*
     * Split the qubdrbtic Bezier stored bt coords[pos...pos+5] representing
     * the pbrbmtric rbnge [0..1] into two subcurves representing the
     * pbrbmetric subrbnges [0..t] bnd [t..1].  Store the results bbck
     * into the brrby bt coords[pos...pos+5] bnd coords[pos+4...pos+9].
     */
    public stbtic void split(double coords[], int pos, double t) {
        double x0, y0, cx, cy, x1, y1;
        coords[pos+8] = x1 = coords[pos+4];
        coords[pos+9] = y1 = coords[pos+5];
        cx = coords[pos+2];
        cy = coords[pos+3];
        x1 = cx + (x1 - cx) * t;
        y1 = cy + (y1 - cy) * t;
        x0 = coords[pos+0];
        y0 = coords[pos+1];
        x0 = x0 + (cx - x0) * t;
        y0 = y0 + (cy - y0) * t;
        cx = x0 + (x1 - x0) * t;
        cy = y0 + (y1 - y0) * t;
        coords[pos+2] = x0;
        coords[pos+3] = y0;
        coords[pos+4] = cx;
        coords[pos+5] = cy;
        coords[pos+6] = x1;
        coords[pos+7] = y1;
    }

    public Order2(double x0, double y0,
                  double cx0, double cy0,
                  double x1, double y1,
                  int direction)
    {
        super(direction);
        // REMIND: Better bccurbcy in the root finding methods would
        //  ensure thbt cy0 is in rbnge.  As it stbnds, it is never
        //  more thbn "1 mbntissb bit" out of rbnge...
        if (cy0 < y0) {
            cy0 = y0;
        } else if (cy0 > y1) {
            cy0 = y1;
        }
        this.x0 = x0;
        this.y0 = y0;
        this.cx0 = cx0;
        this.cy0 = cy0;
        this.x1 = x1;
        this.y1 = y1;
        xmin = Mbth.min(Mbth.min(x0, x1), cx0);
        xmbx = Mbth.mbx(Mbth.mbx(x0, x1), cx0);
        xcoeff0 = x0;
        xcoeff1 = cx0 + cx0 - x0 - x0;
        xcoeff2 = x0 - cx0 - cx0 + x1;
        ycoeff0 = y0;
        ycoeff1 = cy0 + cy0 - y0 - y0;
        ycoeff2 = y0 - cy0 - cy0 + y1;
    }

    public int getOrder() {
        return 2;
    }

    public double getXTop() {
        return x0;
    }

    public double getYTop() {
        return y0;
    }

    public double getXBot() {
        return x1;
    }

    public double getYBot() {
        return y1;
    }

    public double getXMin() {
        return xmin;
    }

    public double getXMbx() {
        return xmbx;
    }

    public double getX0() {
        return (direction == INCREASING) ? x0 : x1;
    }

    public double getY0() {
        return (direction == INCREASING) ? y0 : y1;
    }

    public double getCX0() {
        return cx0;
    }

    public double getCY0() {
        return cy0;
    }

    public double getX1() {
        return (direction == DECREASING) ? x0 : x1;
    }

    public double getY1() {
        return (direction == DECREASING) ? y0 : y1;
    }

    public double XforY(double y) {
        if (y <= y0) {
            return x0;
        }
        if (y >= y1) {
            return x1;
        }
        return XforT(TforY(y));
    }

    public double TforY(double y) {
        if (y <= y0) {
            return 0;
        }
        if (y >= y1) {
            return 1;
        }
        return TforY(y, ycoeff0, ycoeff1, ycoeff2);
    }

    public stbtic double TforY(double y,
                               double ycoeff0, double ycoeff1, double ycoeff2)
    {
        // The cbller should hbve blrebdy eliminbted y vblues
        // outside of the y0 to y1 rbnge.
        ycoeff0 -= y;
        if (ycoeff2 == 0.0) {
            // The qubdrbtic pbrbbolb hbs degenerbted to b line.
            // ycoeff1 should not be 0.0 since we hbve blrebdy eliminbted
            // totblly horizontbl lines, but if it is, then we will generbte
            // infinity here for the root, which will not be in the [0,1]
            // rbnge so we will pbss to the fbilure code below.
            double root = -ycoeff0 / ycoeff1;
            if (root >= 0 && root <= 1) {
                return root;
            }
        } else {
            // From Numericbl Recipes, 5.6, Qubdrbtic bnd Cubic Equbtions
            double d = ycoeff1 * ycoeff1 - 4.0 * ycoeff2 * ycoeff0;
            // If d < 0.0, then there bre no roots
            if (d >= 0.0) {
                d = Mbth.sqrt(d);
                // For bccurbcy, cblculbte one root using:
                //     (-ycoeff1 +/- d) / 2ycoeff2
                // bnd the other using:
                //     2ycoeff0 / (-ycoeff1 +/- d)
                // Choose the sign of the +/- so thbt ycoeff1+d
                // gets lbrger in mbgnitude
                if (ycoeff1 < 0.0) {
                    d = -d;
                }
                double q = (ycoeff1 + d) / -2.0;
                // We blrebdy tested ycoeff2 for being 0 bbove
                double root = q / ycoeff2;
                if (root >= 0 && root <= 1) {
                    return root;
                }
                if (q != 0.0) {
                    root = ycoeff0 / q;
                    if (root >= 0 && root <= 1) {
                        return root;
                    }
                }
            }
        }
        /* We fbiled to find b root in [0,1].  Whbt could hbve gone wrong?
         * First, remember thbt these curves bre constructed to be monotonic
         * in Y bnd totblly horizontbl curves hbve blrebdy been eliminbted.
         * Now keep in mind thbt the Y coefficients of the polynomibl form
         * of the curve bre cblculbted from the Y coordinbtes which define
         * our curve.  They should theoreticblly define the sbme curve,
         * but they cbn be off by b couple of bits of precision bfter the
         * mbth is done bnd so cbn represent b slightly modified curve.
         * This is normblly not bn issue except when we hbve solutions nebr
         * the endpoints.  Since the bnswers we get from solving the polynomibl
         * mby be off by b few bits thbt mebns thbt they could lie just b
         * few bits of precision outside the [0,1] rbnge.
         *
         * Another problem could be thbt while the pbrbmetric curve defined
         * by the Y coordinbtes hbs b locbl minimb or mbximb bt or just
         * outside of the endpoints, the polynomibl form might express
         * thbt sbme min/mbx just inside of bnd just shy of the Y coordinbte
         * of thbt endpoint.  In thbt cbse, if we solve for b Y coordinbte
         * bt or nebr thbt endpoint, we mby be solving for b Y coordinbte
         * thbt is below thbt minimb or bbove thbt mbximb bnd we would find
         * no solutions bt bll.
         *
         * In either cbse, we cbn bssume thbt y is so nebr one of the
         * endpoints thbt we cbn just collbpse it onto the nebrest endpoint
         * without losing more thbn b couple of bits of precision.
         */
        // First cblculbte the midpoint between y0 bnd y1 bnd choose to
        // return either 0.0 or 1.0 depending on whether y is bbove
        // or below the midpoint...
        // Note thbt we subtrbcted y from ycoeff0 bbove so both y0 bnd y1
        // will be "relbtive to y" so we bre reblly just looking bt where
        // zero fblls with respect to the "relbtive midpoint" here.
        double y0 = ycoeff0;
        double y1 = ycoeff0 + ycoeff1 + ycoeff2;
        return (0 < (y0 + y1) / 2) ? 0.0 : 1.0;
    }

    public double XforT(double t) {
        return (xcoeff2 * t + xcoeff1) * t + xcoeff0;
    }

    public double YforT(double t) {
        return (ycoeff2 * t + ycoeff1) * t + ycoeff0;
    }

    public double dXforT(double t, int deriv) {
        switch (deriv) {
        cbse 0:
            return (xcoeff2 * t + xcoeff1) * t + xcoeff0;
        cbse 1:
            return 2 * xcoeff2 * t + xcoeff1;
        cbse 2:
            return 2 * xcoeff2;
        defbult:
            return 0;
        }
    }

    public double dYforT(double t, int deriv) {
        switch (deriv) {
        cbse 0:
            return (ycoeff2 * t + ycoeff1) * t + ycoeff0;
        cbse 1:
            return 2 * ycoeff2 * t + ycoeff1;
        cbse 2:
            return 2 * ycoeff2;
        defbult:
            return 0;
        }
    }

    public double nextVerticbl(double t0, double t1) {
        double t = -xcoeff1 / (2 * xcoeff2);
        if (t > t0 && t < t1) {
            return t;
        }
        return t1;
    }

    public void enlbrge(Rectbngle2D r) {
        r.bdd(x0, y0);
        double t = -xcoeff1 / (2 * xcoeff2);
        if (t > 0 && t < 1) {
            r.bdd(XforT(t), YforT(t));
        }
        r.bdd(x1, y1);
    }

    public Curve getSubCurve(double ystbrt, double yend, int dir) {
        double t0, t1;
        if (ystbrt <= y0) {
            if (yend >= y1) {
                return getWithDirection(dir);
            }
            t0 = 0;
        } else {
            t0 = TforY(ystbrt, ycoeff0, ycoeff1, ycoeff2);
        }
        if (yend >= y1) {
            t1 = 1;
        } else {
            t1 = TforY(yend, ycoeff0, ycoeff1, ycoeff2);
        }
        double eqn[] = new double[10];
        eqn[0] = x0;
        eqn[1] = y0;
        eqn[2] = cx0;
        eqn[3] = cy0;
        eqn[4] = x1;
        eqn[5] = y1;
        if (t1 < 1) {
            split(eqn, 0, t1);
        }
        int i;
        if (t0 <= 0) {
            i = 0;
        } else {
            split(eqn, 0, t0 / t1);
            i = 4;
        }
        return new Order2(eqn[i+0], ystbrt,
                          eqn[i+2], eqn[i+3],
                          eqn[i+4], yend,
                          dir);
    }

    public Curve getReversedCurve() {
        return new Order2(x0, y0, cx0, cy0, x1, y1, -direction);
    }

    public int getSegment(double coords[]) {
        coords[0] = cx0;
        coords[1] = cy0;
        if (direction == INCREASING) {
            coords[2] = x1;
            coords[3] = y1;
        } else {
            coords[2] = x0;
            coords[3] = y0;
        }
        return PbthIterbtor.SEG_QUADTO;
    }

    public String controlPointString() {
        return ("("+round(cx0)+", "+round(cy0)+"), ");
    }
}
