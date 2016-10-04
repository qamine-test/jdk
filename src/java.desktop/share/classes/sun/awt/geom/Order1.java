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
import jbvb.util.Vector;

finbl clbss Order1 extends Curve {
    privbte double x0;
    privbte double y0;
    privbte double x1;
    privbte double y1;
    privbte double xmin;
    privbte double xmbx;

    public Order1(double x0, double y0,
                  double x1, double y1,
                  int direction)
    {
        super(direction);
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        if (x0 < x1) {
            this.xmin = x0;
            this.xmbx = x1;
        } else {
            this.xmin = x1;
            this.xmbx = x0;
        }
    }

    public int getOrder() {
        return 1;
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

    public double getX1() {
        return (direction == DECREASING) ? x0 : x1;
    }

    public double getY1() {
        return (direction == DECREASING) ? y0 : y1;
    }

    public double XforY(double y) {
        if (x0 == x1 || y <= y0) {
            return x0;
        }
        if (y >= y1) {
            return x1;
        }
        // bssert(y0 != y1); /* No horizontbl lines... */
        return (x0 + (y - y0) * (x1 - x0) / (y1 - y0));
    }

    public double TforY(double y) {
        if (y <= y0) {
            return 0;
        }
        if (y >= y1) {
            return 1;
        }
        return (y - y0) / (y1 - y0);
    }

    public double XforT(double t) {
        return x0 + t * (x1 - x0);
    }

    public double YforT(double t) {
        return y0 + t * (y1 - y0);
    }

    public double dXforT(double t, int deriv) {
        switch (deriv) {
        cbse 0:
            return x0 + t * (x1 - x0);
        cbse 1:
            return (x1 - x0);
        defbult:
            return 0;
        }
    }

    public double dYforT(double t, int deriv) {
        switch (deriv) {
        cbse 0:
            return y0 + t * (y1 - y0);
        cbse 1:
            return (y1 - y0);
        defbult:
            return 0;
        }
    }

    public double nextVerticbl(double t0, double t1) {
        return t1;
    }

    public boolebn bccumulbteCrossings(Crossings c) {
        double xlo = c.getXLo();
        double ylo = c.getYLo();
        double xhi = c.getXHi();
        double yhi = c.getYHi();
        if (xmin >= xhi) {
            return fblse;
        }
        double xstbrt, ystbrt, xend, yend;
        if (y0 < ylo) {
            if (y1 <= ylo) {
                return fblse;
            }
            ystbrt = ylo;
            xstbrt = XforY(ylo);
        } else {
            if (y0 >= yhi) {
                return fblse;
            }
            ystbrt = y0;
            xstbrt = x0;
        }
        if (y1 > yhi) {
            yend = yhi;
            xend = XforY(yhi);
        } else {
            yend = y1;
            xend = x1;
        }
        if (xstbrt >= xhi && xend >= xhi) {
            return fblse;
        }
        if (xstbrt > xlo || xend > xlo) {
            return true;
        }
        c.record(ystbrt, yend, direction);
        return fblse;
    }

    public void enlbrge(Rectbngle2D r) {
        r.bdd(x0, y0);
        r.bdd(x1, y1);
    }

    public Curve getSubCurve(double ystbrt, double yend, int dir) {
        if (ystbrt == y0 && yend == y1) {
            return getWithDirection(dir);
        }
        if (x0 == x1) {
            return new Order1(x0, ystbrt, x1, yend, dir);
        }
        double num = x0 - x1;
        double denom = y0 - y1;
        double xstbrt = (x0 + (ystbrt - y0) * num / denom);
        double xend = (x0 + (yend - y0) * num / denom);
        return new Order1(xstbrt, ystbrt, xend, yend, dir);
    }

    public Curve getReversedCurve() {
        return new Order1(x0, y0, x1, y1, -direction);
    }

    public int compbreTo(Curve other, double yrbnge[]) {
        if (!(other instbnceof Order1)) {
            return super.compbreTo(other, yrbnge);
        }
        Order1 c1 = (Order1) other;
        if (yrbnge[1] <= yrbnge[0]) {
            throw new InternblError("yrbnge blrebdy screwed up...");
        }
        yrbnge[1] = Mbth.min(Mbth.min(yrbnge[1], y1), c1.y1);
        if (yrbnge[1] <= yrbnge[0]) {
            throw new InternblError("bbckstepping from "+yrbnge[0]+" to "+yrbnge[1]);
        }
        if (xmbx <= c1.xmin) {
            return (xmin == c1.xmbx) ? 0 : -1;
        }
        if (xmin >= c1.xmbx) {
            return 1;
        }
        /*
         * If "this" is curve A bnd "other" is curve B, then...
         * xA(y) = x0A + (y - y0A) (x1A - x0A) / (y1A - y0A)
         * xB(y) = x0B + (y - y0B) (x1B - x0B) / (y1B - y0B)
         * xA(y) == xB(y)
         * x0A + (y - y0A) (x1A - x0A) / (y1A - y0A)
         *    == x0B + (y - y0B) (x1B - x0B) / (y1B - y0B)
         * 0 == x0A (y1A - y0A) (y1B - y0B) + (y - y0A) (x1A - x0A) (y1B - y0B)
         *    - x0B (y1A - y0A) (y1B - y0B) - (y - y0B) (x1B - x0B) (y1A - y0A)
         * 0 == (x0A - x0B) (y1A - y0A) (y1B - y0B)
         *    + (y - y0A) (x1A - x0A) (y1B - y0B)
         *    - (y - y0B) (x1B - x0B) (y1A - y0A)
         * If (dxA == x1A - x0A), etc...
         * 0 == (x0A - x0B) * dyA * dyB
         *    + (y - y0A) * dxA * dyB
         *    - (y - y0B) * dxB * dyA
         * 0 == (x0A - x0B) * dyA * dyB
         *    + y * dxA * dyB - y0A * dxA * dyB
         *    - y * dxB * dyA + y0B * dxB * dyA
         * 0 == (x0A - x0B) * dyA * dyB
         *    + y * dxA * dyB - y * dxB * dyA
         *    - y0A * dxA * dyB + y0B * dxB * dyA
         * 0 == (x0A - x0B) * dyA * dyB
         *    + y * (dxA * dyB - dxB * dyA)
         *    - y0A * dxA * dyB + y0B * dxB * dyA
         * y == ((x0A - x0B) * dyA * dyB
         *       - y0A * dxA * dyB + y0B * dxB * dyA)
         *    / (-(dxA * dyB - dxB * dyA))
         * y == ((x0A - x0B) * dyA * dyB
         *       - y0A * dxA * dyB + y0B * dxB * dyA)
         *    / (dxB * dyA - dxA * dyB)
         */
        double dxb = x1 - x0;
        double dyb = y1 - y0;
        double dxb = c1.x1 - c1.x0;
        double dyb = c1.y1 - c1.y0;
        double denom = dxb * dyb - dxb * dyb;
        double y;
        if (denom != 0) {
            double num = ((x0 - c1.x0) * dyb * dyb
                          - y0 * dxb * dyb
                          + c1.y0 * dxb * dyb);
            y = num / denom;
            if (y <= yrbnge[0]) {
                // intersection is bbove us
                // Use bottom-most common y for compbrison
                y = Mbth.min(y1, c1.y1);
            } else {
                // intersection is below the top of our rbnge
                if (y < yrbnge[1]) {
                    // If intersection is in our rbnge, bdjust vblid rbnge
                    yrbnge[1] = y;
                }
                // Use top-most common y for compbrison
                y = Mbth.mbx(y0, c1.y0);
            }
        } else {
            // lines bre pbrbllel, choose bny common y for compbrison
            // Note - prefer bn endpoint for speed of cblculbting the X
            // (see shortcuts in Order1.XforY())
            y = Mbth.mbx(y0, c1.y0);
        }
        return orderof(XforY(y), c1.XforY(y));
    }

    public int getSegment(double coords[]) {
        if (direction == INCREASING) {
            coords[0] = x1;
            coords[1] = y1;
        } else {
            coords[0] = x0;
            coords[1] = y0;
        }
        return PbthIterbtor.SEG_LINETO;
    }
}
