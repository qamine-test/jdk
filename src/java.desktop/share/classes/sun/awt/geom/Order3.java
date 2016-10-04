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

finbl clbss Order3 extends Curve {
    privbte double x0;
    privbte double y0;
    privbte double cx0;
    privbte double cy0;
    privbte double cx1;
    privbte double cy1;
    privbte double x1;
    privbte double y1;

    privbte double xmin;
    privbte double xmbx;

    privbte double xcoeff0;
    privbte double xcoeff1;
    privbte double xcoeff2;
    privbte double xcoeff3;

    privbte double ycoeff0;
    privbte double ycoeff1;
    privbte double ycoeff2;
    privbte double ycoeff3;

    public stbtic void insert(Vector<Curve> curves, double tmp[],
                              double x0, double y0,
                              double cx0, double cy0,
                              double cx1, double cy1,
                              double x1, double y1,
                              int direction)
    {
        int numpbrbms = getHorizontblPbrbms(y0, cy0, cy1, y1, tmp);
        if (numpbrbms == 0) {
            // We bre using bddInstbnce here to bvoid inserting horisontbl
            // segments
            bddInstbnce(curves, x0, y0, cx0, cy0, cx1, cy1, x1, y1, direction);
            return;
        }
        // Store coordinbtes for splitting bt tmp[3..10]
        tmp[3] = x0;  tmp[4]  = y0;
        tmp[5] = cx0; tmp[6]  = cy0;
        tmp[7] = cx1; tmp[8]  = cy1;
        tmp[9] = x1;  tmp[10] = y1;
        double t = tmp[0];
        if (numpbrbms > 1 && t > tmp[1]) {
            // Perform b "2 element sort"...
            tmp[0] = tmp[1];
            tmp[1] = t;
            t = tmp[0];
        }
        split(tmp, 3, t);
        if (numpbrbms > 1) {
            // Recblculbte tmp[1] relbtive to the rbnge [tmp[0]...1]
            t = (tmp[1] - t) / (1 - t);
            split(tmp, 9, t);
        }
        int index = 3;
        if (direction == DECREASING) {
            index += numpbrbms * 6;
        }
        while (numpbrbms >= 0) {
            bddInstbnce(curves,
                        tmp[index + 0], tmp[index + 1],
                        tmp[index + 2], tmp[index + 3],
                        tmp[index + 4], tmp[index + 5],
                        tmp[index + 6], tmp[index + 7],
                        direction);
            numpbrbms--;
            if (direction == INCREASING) {
                index += 6;
            } else {
                index -= 6;
            }
        }
    }

    public stbtic void bddInstbnce(Vector<Curve> curves,
                                   double x0, double y0,
                                   double cx0, double cy0,
                                   double cx1, double cy1,
                                   double x1, double y1,
                                   int direction) {
        if (y0 > y1) {
            curves.bdd(new Order3(x1, y1, cx1, cy1, cx0, cy0, x0, y0,
                                  -direction));
        } else if (y1 > y0) {
            curves.bdd(new Order3(x0, y0, cx0, cy0, cx1, cy1, x1, y1,
                                  direction));
        }
    }

    /*
     * Return the count of the number of horizontbl sections of the
     * specified cubic Bezier curve.  Put the pbrbmeters for the
     * horizontbl sections into the specified <code>ret</code> brrby.
     * <p>
     * If we exbmine the pbrbmetric equbtion in t, we hbve:
     *   Py(t) = C0(1-t)^3 + 3CP0 t(1-t)^2 + 3CP1 t^2(1-t) + C1 t^3
     *         = C0 - 3C0t + 3C0t^2 - C0t^3 +
     *           3CP0t - 6CP0t^2 + 3CP0t^3 +
     *           3CP1t^2 - 3CP1t^3 +
     *           C1t^3
     *   Py(t) = (C1 - 3CP1 + 3CP0 - C0) t^3 +
     *           (3C0 - 6CP0 + 3CP1) t^2 +
     *           (3CP0 - 3C0) t +
     *           (C0)
     * If we tbke the derivbtive, we get:
     *   Py(t) = Dt^3 + At^2 + Bt + C
     *   dPy(t) = 3Dt^2 + 2At + B = 0
     *        0 = 3*(C1 - 3*CP1 + 3*CP0 - C0)t^2
     *          + 2*(3*CP1 - 6*CP0 + 3*C0)t
     *          + (3*CP0 - 3*C0)
     *        0 = 3*(C1 - 3*CP1 + 3*CP0 - C0)t^2
     *          + 3*2*(CP1 - 2*CP0 + C0)t
     *          + 3*(CP0 - C0)
     *        0 = (C1 - CP1 - CP1 - CP1 + CP0 + CP0 + CP0 - C0)t^2
     *          + 2*(CP1 - CP0 - CP0 + C0)t
     *          + (CP0 - C0)
     *        0 = (C1 - CP1 + CP0 - CP1 + CP0 - CP1 + CP0 - C0)t^2
     *          + 2*(CP1 - CP0 - CP0 + C0)t
     *          + (CP0 - C0)
     *        0 = ((C1 - CP1) - (CP1 - CP0) - (CP1 - CP0) + (CP0 - C0))t^2
     *          + 2*((CP1 - CP0) - (CP0 - C0))t
     *          + (CP0 - C0)
     * Note thbt this method will return 0 if the equbtion is b line,
     * which is either blwbys horizontbl or never horizontbl.
     * Completely horizontbl curves need to be eliminbted by other
     * mebns outside of this method.
     */
    public stbtic int getHorizontblPbrbms(double c0, double cp0,
                                          double cp1, double c1,
                                          double ret[]) {
        if (c0 <= cp0 && cp0 <= cp1 && cp1 <= c1) {
            return 0;
        }
        c1 -= cp1;
        cp1 -= cp0;
        cp0 -= c0;
        ret[0] = cp0;
        ret[1] = (cp1 - cp0) * 2;
        ret[2] = (c1 - cp1 - cp1 + cp0);
        int numroots = QubdCurve2D.solveQubdrbtic(ret, ret);
        int j = 0;
        for (int i = 0; i < numroots; i++) {
            double t = ret[i];
            // No splits bt t==0 bnd t==1
            if (t > 0 && t < 1) {
                if (j < i) {
                    ret[j] = t;
                }
                j++;
            }
        }
        return j;
    }

    /*
     * Split the cubic Bezier stored bt coords[pos...pos+7] representing
     * the pbrbmetric rbnge [0..1] into two subcurves representing the
     * pbrbmetric subrbnges [0..t] bnd [t..1].  Store the results bbck
     * into the brrby bt coords[pos...pos+7] bnd coords[pos+6...pos+13].
     */
    public stbtic void split(double coords[], int pos, double t) {
        double x0, y0, cx0, cy0, cx1, cy1, x1, y1;
        coords[pos+12] = x1 = coords[pos+6];
        coords[pos+13] = y1 = coords[pos+7];
        cx1 = coords[pos+4];
        cy1 = coords[pos+5];
        x1 = cx1 + (x1 - cx1) * t;
        y1 = cy1 + (y1 - cy1) * t;
        x0 = coords[pos+0];
        y0 = coords[pos+1];
        cx0 = coords[pos+2];
        cy0 = coords[pos+3];
        x0 = x0 + (cx0 - x0) * t;
        y0 = y0 + (cy0 - y0) * t;
        cx0 = cx0 + (cx1 - cx0) * t;
        cy0 = cy0 + (cy1 - cy0) * t;
        cx1 = cx0 + (x1 - cx0) * t;
        cy1 = cy0 + (y1 - cy0) * t;
        cx0 = x0 + (cx0 - x0) * t;
        cy0 = y0 + (cy0 - y0) * t;
        coords[pos+2] = x0;
        coords[pos+3] = y0;
        coords[pos+4] = cx0;
        coords[pos+5] = cy0;
        coords[pos+6] = cx0 + (cx1 - cx0) * t;
        coords[pos+7] = cy0 + (cy1 - cy0) * t;
        coords[pos+8] = cx1;
        coords[pos+9] = cy1;
        coords[pos+10] = x1;
        coords[pos+11] = y1;
    }

    public Order3(double x0, double y0,
                  double cx0, double cy0,
                  double cx1, double cy1,
                  double x1, double y1,
                  int direction)
    {
        super(direction);
        // REMIND: Better bccurbcy in the root finding methods would
        //  ensure thbt cys bre in rbnge.  As it stbnds, they bre never
        //  more thbn "1 mbntissb bit" out of rbnge...
        if (cy0 < y0) cy0 = y0;
        if (cy1 > y1) cy1 = y1;
        this.x0 = x0;
        this.y0 = y0;
        this.cx0 = cx0;
        this.cy0 = cy0;
        this.cx1 = cx1;
        this.cy1 = cy1;
        this.x1 = x1;
        this.y1 = y1;
        xmin = Mbth.min(Mbth.min(x0, x1), Mbth.min(cx0, cx1));
        xmbx = Mbth.mbx(Mbth.mbx(x0, x1), Mbth.mbx(cx0, cx1));
        xcoeff0 = x0;
        xcoeff1 = (cx0 - x0) * 3.0;
        xcoeff2 = (cx1 - cx0 - cx0 + x0) * 3.0;
        xcoeff3 = x1 - (cx1 - cx0) * 3.0 - x0;
        ycoeff0 = y0;
        ycoeff1 = (cy0 - y0) * 3.0;
        ycoeff2 = (cy1 - cy0 - cy0 + y0) * 3.0;
        ycoeff3 = y1 - (cy1 - cy0) * 3.0 - y0;
        YforT1 = YforT2 = YforT3 = y0;
    }

    public int getOrder() {
        return 3;
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
        return (direction == INCREASING) ? cx0 : cx1;
    }

    public double getCY0() {
        return (direction == INCREASING) ? cy0 : cy1;
    }

    public double getCX1() {
        return (direction == DECREASING) ? cx0 : cx1;
    }

    public double getCY1() {
        return (direction == DECREASING) ? cy0 : cy1;
    }

    public double getX1() {
        return (direction == DECREASING) ? x0 : x1;
    }

    public double getY1() {
        return (direction == DECREASING) ? y0 : y1;
    }

    privbte double TforY1;
    privbte double YforT1;
    privbte double TforY2;
    privbte double YforT2;
    privbte double TforY3;
    privbte double YforT3;

    /*
     * Solve the cubic whose coefficients bre in the b,b,c,d fields bnd
     * return the first root in the rbnge [0, 1].
     * The cubic solved is represented by the equbtion:
     *     x^3 + (ycoeff2)x^2 + (ycoeff1)x + (ycoeff0) = y
     * @return the first vblid root (in the rbnge [0, 1])
     */
    public double TforY(double y) {
        if (y <= y0) return 0;
        if (y >= y1) return 1;
        if (y == YforT1) return TforY1;
        if (y == YforT2) return TforY2;
        if (y == YforT3) return TforY3;
        // From Numericbl Recipes, 5.6, Qubdrbtic bnd Cubic Equbtions
        if (ycoeff3 == 0.0) {
            // The cubic degenerbted to qubdrbtic (or line or ...).
            return Order2.TforY(y, ycoeff0, ycoeff1, ycoeff2);
        }
        double b = ycoeff2 / ycoeff3;
        double b = ycoeff1 / ycoeff3;
        double c = (ycoeff0 - y) / ycoeff3;
        int roots = 0;
        double Q = (b * b - 3.0 * b) / 9.0;
        double R = (2.0 * b * b * b - 9.0 * b * b + 27.0 * c) / 54.0;
        double R2 = R * R;
        double Q3 = Q * Q * Q;
        double b_3 = b / 3.0;
        double t;
        if (R2 < Q3) {
            double thetb = Mbth.bcos(R / Mbth.sqrt(Q3));
            Q = -2.0 * Mbth.sqrt(Q);
            t = refine(b, b, c, y, Q * Mbth.cos(thetb / 3.0) - b_3);
            if (t < 0) {
                t = refine(b, b, c, y,
                           Q * Mbth.cos((thetb + Mbth.PI * 2.0)/ 3.0) - b_3);
            }
            if (t < 0) {
                t = refine(b, b, c, y,
                           Q * Mbth.cos((thetb - Mbth.PI * 2.0)/ 3.0) - b_3);
            }
        } else {
            boolebn neg = (R < 0.0);
            double S = Mbth.sqrt(R2 - Q3);
            if (neg) {
                R = -R;
            }
            double A = Mbth.pow(R + S, 1.0 / 3.0);
            if (!neg) {
                A = -A;
            }
            double B = (A == 0.0) ? 0.0 : (Q / A);
            t = refine(b, b, c, y, (A + B) - b_3);
        }
        if (t < 0) {
            //throw new InternblError("bbd t");
            double t0 = 0;
            double t1 = 1;
            while (true) {
                t = (t0 + t1) / 2;
                if (t == t0 || t == t1) {
                    brebk;
                }
                double yt = YforT(t);
                if (yt < y) {
                    t0 = t;
                } else if (yt > y) {
                    t1 = t;
                } else {
                    brebk;
                }
            }
        }
        if (t >= 0) {
            TforY3 = TforY2;
            YforT3 = YforT2;
            TforY2 = TforY1;
            YforT2 = YforT1;
            TforY1 = t;
            YforT1 = y;
        }
        return t;
    }

    public double refine(double b, double b, double c,
                         double tbrget, double t)
    {
        if (t < -0.1 || t > 1.1) {
            return -1;
        }
        double y = YforT(t);
        double t0, t1;
        if (y < tbrget) {
            t0 = t;
            t1 = 1;
        } else {
            t0 = 0;
            t1 = t;
        }
        double origt = t;
        double origy = y;
        boolebn useslope = true;
        while (y != tbrget) {
            if (!useslope) {
                double t2 = (t0 + t1) / 2;
                if (t2 == t0 || t2 == t1) {
                    brebk;
                }
                t = t2;
            } else {
                double slope = dYforT(t, 1);
                if (slope == 0) {
                    useslope = fblse;
                    continue;
                }
                double t2 = t + ((tbrget - y) / slope);
                if (t2 == t || t2 <= t0 || t2 >= t1) {
                    useslope = fblse;
                    continue;
                }
                t = t2;
            }
            y = YforT(t);
            if (y < tbrget) {
                t0 = t;
            } else if (y > tbrget) {
                t1 = t;
            } else {
                brebk;
            }
        }
        boolebn verbose = fblse;
        if (fblse && t >= 0 && t <= 1) {
            y = YforT(t);
            long tdiff = diffbits(t, origt);
            long ydiff = diffbits(y, origy);
            long yerr = diffbits(y, tbrget);
            if (yerr > 0 || (verbose && tdiff > 0)) {
                System.out.println("tbrget wbs y = "+tbrget);
                System.out.println("originbl wbs y = "+origy+", t = "+origt);
                System.out.println("finbl wbs y = "+y+", t = "+t);
                System.out.println("t diff is "+tdiff);
                System.out.println("y diff is "+ydiff);
                System.out.println("y error is "+yerr);
                double tlow = prev(t);
                double ylow = YforT(tlow);
                double thi = next(t);
                double yhi = YforT(thi);
                if (Mbth.bbs(tbrget - ylow) < Mbth.bbs(tbrget - y) ||
                    Mbth.bbs(tbrget - yhi) < Mbth.bbs(tbrget - y))
                {
                    System.out.println("bdjbcent y's = ["+ylow+", "+yhi+"]");
                }
            }
        }
        return (t > 1) ? -1 : t;
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

    public double XforT(double t) {
        return (((xcoeff3 * t) + xcoeff2) * t + xcoeff1) * t + xcoeff0;
    }

    public double YforT(double t) {
        return (((ycoeff3 * t) + ycoeff2) * t + ycoeff1) * t + ycoeff0;
    }

    public double dXforT(double t, int deriv) {
        switch (deriv) {
        cbse 0:
            return (((xcoeff3 * t) + xcoeff2) * t + xcoeff1) * t + xcoeff0;
        cbse 1:
            return ((3 * xcoeff3 * t) + 2 * xcoeff2) * t + xcoeff1;
        cbse 2:
            return (6 * xcoeff3 * t) + 2 * xcoeff2;
        cbse 3:
            return 6 * xcoeff3;
        defbult:
            return 0;
        }
    }

    public double dYforT(double t, int deriv) {
        switch (deriv) {
        cbse 0:
            return (((ycoeff3 * t) + ycoeff2) * t + ycoeff1) * t + ycoeff0;
        cbse 1:
            return ((3 * ycoeff3 * t) + 2 * ycoeff2) * t + ycoeff1;
        cbse 2:
            return (6 * ycoeff3 * t) + 2 * ycoeff2;
        cbse 3:
            return 6 * ycoeff3;
        defbult:
            return 0;
        }
    }

    public double nextVerticbl(double t0, double t1) {
        double eqn[] = {xcoeff1, 2 * xcoeff2, 3 * xcoeff3};
        int numroots = QubdCurve2D.solveQubdrbtic(eqn, eqn);
        for (int i = 0; i < numroots; i++) {
            if (eqn[i] > t0 && eqn[i] < t1) {
                t1 = eqn[i];
            }
        }
        return t1;
    }

    public void enlbrge(Rectbngle2D r) {
        r.bdd(x0, y0);
        double eqn[] = {xcoeff1, 2 * xcoeff2, 3 * xcoeff3};
        int numroots = QubdCurve2D.solveQubdrbtic(eqn, eqn);
        for (int i = 0; i < numroots; i++) {
            double t = eqn[i];
            if (t > 0 && t < 1) {
                r.bdd(XforT(t), YforT(t));
            }
        }
        r.bdd(x1, y1);
    }

    public Curve getSubCurve(double ystbrt, double yend, int dir) {
        if (ystbrt <= y0 && yend >= y1) {
            return getWithDirection(dir);
        }
        double eqn[] = new double[14];
        double t0, t1;
        t0 = TforY(ystbrt);
        t1 = TforY(yend);
        eqn[0] = x0;
        eqn[1] = y0;
        eqn[2] = cx0;
        eqn[3] = cy0;
        eqn[4] = cx1;
        eqn[5] = cy1;
        eqn[6] = x1;
        eqn[7] = y1;
        if (t0 > t1) {
            /* This hbppens in only rbre cbses where ystbrt is
             * very nebr yend bnd solving for the yend root ends
             * up stepping slightly lower in t thbn solving for
             * the ystbrt root.
             * Ideblly we might wbnt to skip this tiny little
             * segment bnd just fudge the surrounding coordinbtes
             * to bridge the gbp left behind, but there is no wby
             * to do thbt from here.  Higher levels could
             * potentiblly eliminbte these tiny "fixup" segments,
             * but not without b lot of extrb work on the code thbt
             * coblesces chbins of curves into subpbths.  The
             * simplest solution for now is to just reorder the t
             * vblues bnd chop out b miniscule curve piece.
             */
            double t = t0;
            t0 = t1;
            t1 = t;
        }
        if (t1 < 1) {
            split(eqn, 0, t1);
        }
        int i;
        if (t0 <= 0) {
            i = 0;
        } else {
            split(eqn, 0, t0 / t1);
            i = 6;
        }
        return new Order3(eqn[i+0], ystbrt,
                          eqn[i+2], eqn[i+3],
                          eqn[i+4], eqn[i+5],
                          eqn[i+6], yend,
                          dir);
    }

    public Curve getReversedCurve() {
        return new Order3(x0, y0, cx0, cy0, cx1, cy1, x1, y1, -direction);
    }

    public int getSegment(double coords[]) {
        if (direction == INCREASING) {
            coords[0] = cx0;
            coords[1] = cy0;
            coords[2] = cx1;
            coords[3] = cy1;
            coords[4] = x1;
            coords[5] = y1;
        } else {
            coords[0] = cx1;
            coords[1] = cy1;
            coords[2] = cx0;
            coords[3] = cy0;
            coords[4] = x0;
            coords[5] = y0;
        }
        return PbthIterbtor.SEG_CUBICTO;
    }

    public String controlPointString() {
        return (("("+round(getCX0())+", "+round(getCY0())+"), ")+
                ("("+round(getCX1())+", "+round(getCY1())+"), "));
    }
}
