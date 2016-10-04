/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.geom;

import jbvb.bwt.Shbpe;
import jbvb.bwt.Rectbngle;
import jbvb.io.Seriblizbble;

/**
 * This <code>Line2D</code> represents b line segment in {@code (x,y)}
 * coordinbte spbce.  This clbss, like bll of the Jbvb 2D API, uses b
 * defbult coordinbte system cblled <i>user spbce</i> in which the y-bxis
 * vblues increbse downwbrd bnd x-bxis vblues increbse to the right.  For
 * more informbtion on the user spbce coordinbte system, see the
 * <b href="{@docRoot}/../technotes/guides/2d/spec/j2d-intro.html">
 * Coordinbte Systems</b> section of the Jbvb 2D Progrbmmer's Guide.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects thbt
 * store b 2D line segment.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss Line2D implements Shbpe, Clonebble {

    /**
     * A line segment specified with flobt coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Flobt extends Line2D implements Seriblizbble {
        /**
         * The X coordinbte of the stbrt point of the line segment.
         * @since 1.2
         * @seribl
         */
        public flobt x1;

        /**
         * The Y coordinbte of the stbrt point of the line segment.
         * @since 1.2
         * @seribl
         */
        public flobt y1;

        /**
         * The X coordinbte of the end point of the line segment.
         * @since 1.2
         * @seribl
         */
        public flobt x2;

        /**
         * The Y coordinbte of the end point of the line segment.
         * @since 1.2
         * @seribl
         */
        public flobt y2;

        /**
         * Constructs bnd initiblizes b Line with coordinbtes (0, 0) &rbrr; (0, 0).
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes b Line from the specified coordinbtes.
         * @pbrbm x1 the X coordinbte of the stbrt point
         * @pbrbm y1 the Y coordinbte of the stbrt point
         * @pbrbm x2 the X coordinbte of the end point
         * @pbrbm y2 the Y coordinbte of the end point
         * @since 1.2
         */
        public Flobt(flobt x1, flobt y1, flobt x2, flobt y2) {
            setLine(x1, y1, x2, y2);
        }

        /**
         * Constructs bnd initiblizes b <code>Line2D</code> from the
         * specified <code>Point2D</code> objects.
         * @pbrbm p1 the stbrt <code>Point2D</code> of this line segment
         * @pbrbm p2 the end <code>Point2D</code> of this line segment
         * @since 1.2
         */
        public Flobt(Point2D p1, Point2D p2) {
            setLine(p1, p2);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX1() {
            return (double) x1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY1() {
            return (double) y1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getP1() {
            return new Point2D.Flobt(x1, y1);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX2() {
            return (double) x2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY2() {
            return (double) y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getP2() {
            return new Point2D.Flobt(x2, y2);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setLine(double x1, double y1, double x2, double y2) {
            this.x1 = (flobt) x1;
            this.y1 = (flobt) y1;
            this.x2 = (flobt) x2;
            this.y2 = (flobt) y2;
        }

        /**
         * Sets the locbtion of the end points of this <code>Line2D</code>
         * to the specified flobt coordinbtes.
         * @pbrbm x1 the X coordinbte of the stbrt point
         * @pbrbm y1 the Y coordinbte of the stbrt point
         * @pbrbm x2 the X coordinbte of the end point
         * @pbrbm y2 the Y coordinbte of the end point
         * @since 1.2
         */
        public void setLine(flobt x1, flobt y1, flobt x2, flobt y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            flobt x, y, w, h;
            if (x1 < x2) {
                x = x1;
                w = x2 - x1;
            } else {
                x = x2;
                w = x1 - x2;
            }
            if (y1 < y2) {
                y = y1;
                h = y2 - y1;
            } else {
                y = y2;
                h = y1 - y2;
            }
            return new Rectbngle2D.Flobt(x, y, w, h);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 6161772511649436349L;
    }

    /**
     * A line segment specified with double coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Double extends Line2D implements Seriblizbble {
        /**
         * The X coordinbte of the stbrt point of the line segment.
         * @since 1.2
         * @seribl
         */
        public double x1;

        /**
         * The Y coordinbte of the stbrt point of the line segment.
         * @since 1.2
         * @seribl
         */
        public double y1;

        /**
         * The X coordinbte of the end point of the line segment.
         * @since 1.2
         * @seribl
         */
        public double x2;

        /**
         * The Y coordinbte of the end point of the line segment.
         * @since 1.2
         * @seribl
         */
        public double y2;

        /**
         * Constructs bnd initiblizes b Line with coordinbtes (0, 0) &rbrr; (0, 0).
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes b <code>Line2D</code> from the
         * specified coordinbtes.
         * @pbrbm x1 the X coordinbte of the stbrt point
         * @pbrbm y1 the Y coordinbte of the stbrt point
         * @pbrbm x2 the X coordinbte of the end point
         * @pbrbm y2 the Y coordinbte of the end point
         * @since 1.2
         */
        public Double(double x1, double y1, double x2, double y2) {
            setLine(x1, y1, x2, y2);
        }

        /**
         * Constructs bnd initiblizes b <code>Line2D</code> from the
         * specified <code>Point2D</code> objects.
         * @pbrbm p1 the stbrt <code>Point2D</code> of this line segment
         * @pbrbm p2 the end <code>Point2D</code> of this line segment
         * @since 1.2
         */
        public Double(Point2D p1, Point2D p2) {
            setLine(p1, p2);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX1() {
            return x1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY1() {
            return y1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getP1() {
            return new Point2D.Double(x1, y1);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX2() {
            return x2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY2() {
            return y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getP2() {
            return new Point2D.Double(x2, y2);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setLine(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            double x, y, w, h;
            if (x1 < x2) {
                x = x1;
                w = x2 - x1;
            } else {
                x = x2;
                w = x1 - x2;
            }
            if (y1 < y2) {
                y = y1;
                h = y2 - y1;
            } else {
                y = y2;
                h = y1 - y2;
            }
            return new Rectbngle2D.Double(x, y, w, h);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 7979627399746467499L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessory
     * methods below.
     *
     * @see jbvb.bwt.geom.Line2D.Flobt
     * @see jbvb.bwt.geom.Line2D.Double
     * @since 1.2
     */
    protected Line2D() {
    }

    /**
     * Returns the X coordinbte of the stbrt point in double precision.
     * @return the X coordinbte of the stbrt point of this
     *         {@code Line2D} object.
     * @since 1.2
     */
    public bbstrbct double getX1();

    /**
     * Returns the Y coordinbte of the stbrt point in double precision.
     * @return the Y coordinbte of the stbrt point of this
     *         {@code Line2D} object.
     * @since 1.2
     */
    public bbstrbct double getY1();

    /**
     * Returns the stbrt <code>Point2D</code> of this <code>Line2D</code>.
     * @return the stbrt <code>Point2D</code> of this <code>Line2D</code>.
     * @since 1.2
     */
    public bbstrbct Point2D getP1();

    /**
     * Returns the X coordinbte of the end point in double precision.
     * @return the X coordinbte of the end point of this
     *         {@code Line2D} object.
     * @since 1.2
     */
    public bbstrbct double getX2();

    /**
     * Returns the Y coordinbte of the end point in double precision.
     * @return the Y coordinbte of the end point of this
     *         {@code Line2D} object.
     * @since 1.2
     */
    public bbstrbct double getY2();

    /**
     * Returns the end <code>Point2D</code> of this <code>Line2D</code>.
     * @return the end <code>Point2D</code> of this <code>Line2D</code>.
     * @since 1.2
     */
    public bbstrbct Point2D getP2();

    /**
     * Sets the locbtion of the end points of this <code>Line2D</code> to
     * the specified double coordinbtes.
     * @pbrbm x1 the X coordinbte of the stbrt point
     * @pbrbm y1 the Y coordinbte of the stbrt point
     * @pbrbm x2 the X coordinbte of the end point
     * @pbrbm y2 the Y coordinbte of the end point
     * @since 1.2
     */
    public bbstrbct void setLine(double x1, double y1, double x2, double y2);

    /**
     * Sets the locbtion of the end points of this <code>Line2D</code> to
     * the specified <code>Point2D</code> coordinbtes.
     * @pbrbm p1 the stbrt <code>Point2D</code> of the line segment
     * @pbrbm p2 the end <code>Point2D</code> of the line segment
     * @since 1.2
     */
    public void setLine(Point2D p1, Point2D p2) {
        setLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Sets the locbtion of the end points of this <code>Line2D</code> to
     * the sbme bs those end points of the specified <code>Line2D</code>.
     * @pbrbm l the specified <code>Line2D</code>
     * @since 1.2
     */
    public void setLine(Line2D l) {
        setLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
    }

    /**
     * Returns bn indicbtor of where the specified point
     * {@code (px,py)} lies with respect to the line segment from
     * {@code (x1,y1)} to {@code (x2,y2)}.
     * The return vblue cbn be either 1, -1, or 0 bnd indicbtes
     * in which direction the specified line must pivot bround its
     * first end point, {@code (x1,y1)}, in order to point bt the
     * specified point {@code (px,py)}.
     * <p>A return vblue of 1 indicbtes thbt the line segment must
     * turn in the direction thbt tbkes the positive X bxis towbrds
     * the negbtive Y bxis.  In the defbult coordinbte system used by
     * Jbvb 2D, this direction is counterclockwise.
     * <p>A return vblue of -1 indicbtes thbt the line segment must
     * turn in the direction thbt tbkes the positive X bxis towbrds
     * the positive Y bxis.  In the defbult coordinbte system, this
     * direction is clockwise.
     * <p>A return vblue of 0 indicbtes thbt the point lies
     * exbctly on the line segment.  Note thbt bn indicbtor vblue
     * of 0 is rbre bnd not useful for determining collinebrity
     * becbuse of flobting point rounding issues.
     * <p>If the point is colinebr with the line segment, but
     * not between the end points, then the vblue will be -1 if the point
     * lies "beyond {@code (x1,y1)}" or 1 if the point lies
     * "beyond {@code (x2,y2)}".
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm y1 the Y coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm x2 the X coordinbte of the end point of the
     *           specified line segment
     * @pbrbm y2 the Y coordinbte of the end point of the
     *           specified line segment
     * @pbrbm px the X coordinbte of the specified point to be
     *           compbred with the specified line segment
     * @pbrbm py the Y coordinbte of the specified point to be
     *           compbred with the specified line segment
     * @return bn integer thbt indicbtes the position of the third specified
     *                  coordinbtes with respect to the line segment formed
     *                  by the first two specified coordinbtes.
     * @since 1.2
     */
    public stbtic int relbtiveCCW(double x1, double y1,
                                  double x2, double y2,
                                  double px, double py)
    {
        x2 -= x1;
        y2 -= y1;
        px -= x1;
        py -= y1;
        double ccw = px * y2 - py * x2;
        if (ccw == 0.0) {
            // The point is colinebr, clbssify bbsed on which side of
            // the segment the point fblls on.  We cbn cblculbte b
            // relbtive vblue using the projection of px,py onto the
            // segment - b negbtive vblue indicbtes the point projects
            // outside of the segment in the direction of the pbrticulbr
            // endpoint used bs the origin for the projection.
            ccw = px * x2 + py * y2;
            if (ccw > 0.0) {
                // Reverse the projection to be relbtive to the originbl x2,y2
                // x2 bnd y2 bre simply negbted.
                // px bnd py need to hbve (x2 - x1) or (y2 - y1) subtrbcted
                //    from them (bbsed on the originbl vblues)
                // Since we reblly wbnt to get b positive bnswer when the
                //    point is "beyond (x2,y2)", then we wbnt to cblculbte
                //    the inverse bnywby - thus we lebve x2 & y2 negbted.
                px -= x2;
                py -= y2;
                ccw = px * x2 + py * y2;
                if (ccw < 0.0) {
                    ccw = 0.0;
                }
            }
        }
        return (ccw < 0.0) ? -1 : ((ccw > 0.0) ? 1 : 0);
    }

    /**
     * Returns bn indicbtor of where the specified point
     * {@code (px,py)} lies with respect to this line segment.
     * See the method comments of
     * {@link #relbtiveCCW(double, double, double, double, double, double)}
     * to interpret the return vblue.
     * @pbrbm px the X coordinbte of the specified point
     *           to be compbred with this <code>Line2D</code>
     * @pbrbm py the Y coordinbte of the specified point
     *           to be compbred with this <code>Line2D</code>
     * @return bn integer thbt indicbtes the position of the specified
     *         coordinbtes with respect to this <code>Line2D</code>
     * @see #relbtiveCCW(double, double, double, double, double, double)
     * @since 1.2
     */
    public int relbtiveCCW(double px, double py) {
        return relbtiveCCW(getX1(), getY1(), getX2(), getY2(), px, py);
    }

    /**
     * Returns bn indicbtor of where the specified <code>Point2D</code>
     * lies with respect to this line segment.
     * See the method comments of
     * {@link #relbtiveCCW(double, double, double, double, double, double)}
     * to interpret the return vblue.
     * @pbrbm p the specified <code>Point2D</code> to be compbred
     *          with this <code>Line2D</code>
     * @return bn integer thbt indicbtes the position of the specified
     *         <code>Point2D</code> with respect to this <code>Line2D</code>
     * @see #relbtiveCCW(double, double, double, double, double, double)
     * @since 1.2
     */
    public int relbtiveCCW(Point2D p) {
        return relbtiveCCW(getX1(), getY1(), getX2(), getY2(),
                           p.getX(), p.getY());
    }

    /**
     * Tests if the line segment from {@code (x1,y1)} to
     * {@code (x2,y2)} intersects the line segment from {@code (x3,y3)}
     * to {@code (x4,y4)}.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the first
     *           specified line segment
     * @pbrbm y1 the Y coordinbte of the stbrt point of the first
     *           specified line segment
     * @pbrbm x2 the X coordinbte of the end point of the first
     *           specified line segment
     * @pbrbm y2 the Y coordinbte of the end point of the first
     *           specified line segment
     * @pbrbm x3 the X coordinbte of the stbrt point of the second
     *           specified line segment
     * @pbrbm y3 the Y coordinbte of the stbrt point of the second
     *           specified line segment
     * @pbrbm x4 the X coordinbte of the end point of the second
     *           specified line segment
     * @pbrbm y4 the Y coordinbte of the end point of the second
     *           specified line segment
     * @return <code>true</code> if the first specified line segment
     *                  bnd the second specified line segment intersect
     *                  ebch other; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public stbtic boolebn linesIntersect(double x1, double y1,
                                         double x2, double y2,
                                         double x3, double y3,
                                         double x4, double y4)
    {
        return ((relbtiveCCW(x1, y1, x2, y2, x3, y3) *
                 relbtiveCCW(x1, y1, x2, y2, x4, y4) <= 0)
                && (relbtiveCCW(x3, y3, x4, y4, x1, y1) *
                    relbtiveCCW(x3, y3, x4, y4, x2, y2) <= 0));
    }

    /**
     * Tests if the line segment from {@code (x1,y1)} to
     * {@code (x2,y2)} intersects this line segment.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm y1 the Y coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm x2 the X coordinbte of the end point of the
     *           specified line segment
     * @pbrbm y2 the Y coordinbte of the end point of the
     *           specified line segment
     * @return {@code <true>} if this line segment bnd the specified line segment
     *                  intersect ebch other; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn intersectsLine(double x1, double y1, double x2, double y2) {
        return linesIntersect(x1, y1, x2, y2,
                              getX1(), getY1(), getX2(), getY2());
    }

    /**
     * Tests if the specified line segment intersects this line segment.
     * @pbrbm l the specified <code>Line2D</code>
     * @return <code>true</code> if this line segment bnd the specified line
     *                  segment intersect ebch other;
     *                  <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn intersectsLine(Line2D l) {
        return linesIntersect(l.getX1(), l.getY1(), l.getX2(), l.getY2(),
                              getX1(), getY1(), getX2(), getY2());
    }

    /**
     * Returns the squbre of the distbnce from b point to b line segment.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point between the specified end points.
     * If the specified point intersects the line segment in between the
     * end points, this method returns 0.0.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm y1 the Y coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm x2 the X coordinbte of the end point of the
     *           specified line segment
     * @pbrbm y2 the Y coordinbte of the end point of the
     *           specified line segment
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst the specified line segment
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst the specified line segment
     * @return b double vblue thbt is the squbre of the distbnce from the
     *                  specified point to the specified line segment.
     * @see #ptLineDistSq(double, double, double, double, double, double)
     * @since 1.2
     */
    public stbtic double ptSegDistSq(double x1, double y1,
                                     double x2, double y2,
                                     double px, double py)
    {
        // Adjust vectors relbtive to x1,y1
        // x2,y2 becomes relbtive vector from x1,y1 to end of segment
        x2 -= x1;
        y2 -= y1;
        // px,py becomes relbtive vector from x1,y1 to test point
        px -= x1;
        py -= y1;
        double dotprod = px * x2 + py * y2;
        double projlenSq;
        if (dotprod <= 0.0) {
            // px,py is on the side of x1,y1 bwby from x2,y2
            // distbnce to segment is length of px,py vector
            // "length of its (clipped) projection" is now 0.0
            projlenSq = 0.0;
        } else {
            // switch to bbckwbrds vectors relbtive to x2,y2
            // x2,y2 bre blrebdy the negbtive of x1,y1=>x2,y2
            // to get px,py to be the negbtive of px,py=>x2,y2
            // the dot product of two negbted vectors is the sbme
            // bs the dot product of the two normbl vectors
            px = x2 - px;
            py = y2 - py;
            dotprod = px * x2 + py * y2;
            if (dotprod <= 0.0) {
                // px,py is on the side of x2,y2 bwby from x1,y1
                // distbnce to segment is length of (bbckwbrds) px,py vector
                // "length of its (clipped) projection" is now 0.0
                projlenSq = 0.0;
            } else {
                // px,py is between x1,y1 bnd x2,y2
                // dotprod is the length of the px,py vector
                // projected on the x2,y2=>x1,y1 vector times the
                // length of the x2,y2=>x1,y1 vector
                projlenSq = dotprod * dotprod / (x2 * x2 + y2 * y2);
            }
        }
        // Distbnce to line is now the length of the relbtive point
        // vector minus the length of its projection onto the line
        // (which is zero if the projection fblls outside the rbnge
        //  of the line segment).
        double lenSq = px * px + py * py - projlenSq;
        if (lenSq < 0) {
            lenSq = 0;
        }
        return lenSq;
    }

    /**
     * Returns the distbnce from b point to b line segment.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point between the specified end points.
     * If the specified point intersects the line segment in between the
     * end points, this method returns 0.0.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm y1 the Y coordinbte of the stbrt point of the
     *           specified line segment
     * @pbrbm x2 the X coordinbte of the end point of the
     *           specified line segment
     * @pbrbm y2 the Y coordinbte of the end point of the
     *           specified line segment
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst the specified line segment
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst the specified line segment
     * @return b double vblue thbt is the distbnce from the specified point
     *                          to the specified line segment.
     * @see #ptLineDist(double, double, double, double, double, double)
     * @since 1.2
     */
    public stbtic double ptSegDist(double x1, double y1,
                                   double x2, double y2,
                                   double px, double py)
    {
        return Mbth.sqrt(ptSegDistSq(x1, y1, x2, y2, px, py));
    }

    /**
     * Returns the squbre of the distbnce from b point to this line segment.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point between the current line's end points.
     * If the specified point intersects the line segment in between the
     * end points, this method returns 0.0.
     *
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst this line segment
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst this line segment
     * @return b double vblue thbt is the squbre of the distbnce from the
     *                  specified point to the current line segment.
     * @see #ptLineDistSq(double, double)
     * @since 1.2
     */
    public double ptSegDistSq(double px, double py) {
        return ptSegDistSq(getX1(), getY1(), getX2(), getY2(), px, py);
    }

    /**
     * Returns the squbre of the distbnce from b <code>Point2D</code> to
     * this line segment.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point between the current line's end points.
     * If the specified point intersects the line segment in between the
     * end points, this method returns 0.0.
     * @pbrbm pt the specified <code>Point2D</code> being mebsured bgbinst
     *           this line segment.
     * @return b double vblue thbt is the squbre of the distbnce from the
     *                  specified <code>Point2D</code> to the current
     *                  line segment.
     * @see #ptLineDistSq(Point2D)
     * @since 1.2
     */
    public double ptSegDistSq(Point2D pt) {
        return ptSegDistSq(getX1(), getY1(), getX2(), getY2(),
                           pt.getX(), pt.getY());
    }

    /**
     * Returns the distbnce from b point to this line segment.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point between the current line's end points.
     * If the specified point intersects the line segment in between the
     * end points, this method returns 0.0.
     *
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst this line segment
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst this line segment
     * @return b double vblue thbt is the distbnce from the specified
     *                  point to the current line segment.
     * @see #ptLineDist(double, double)
     * @since 1.2
     */
    public double ptSegDist(double px, double py) {
        return ptSegDist(getX1(), getY1(), getX2(), getY2(), px, py);
    }

    /**
     * Returns the distbnce from b <code>Point2D</code> to this line
     * segment.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point between the current line's end points.
     * If the specified point intersects the line segment in between the
     * end points, this method returns 0.0.
     * @pbrbm pt the specified <code>Point2D</code> being mebsured
     *          bgbinst this line segment
     * @return b double vblue thbt is the distbnce from the specified
     *                          <code>Point2D</code> to the current line
     *                          segment.
     * @see #ptLineDist(Point2D)
     * @since 1.2
     */
    public double ptSegDist(Point2D pt) {
        return ptSegDist(getX1(), getY1(), getX2(), getY2(),
                         pt.getX(), pt.getY());
    }

    /**
     * Returns the squbre of the distbnce from b point to b line.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point on the infinitely-extended line
     * defined by the specified coordinbtes.  If the specified point
     * intersects the line, this method returns 0.0.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the specified line
     * @pbrbm y1 the Y coordinbte of the stbrt point of the specified line
     * @pbrbm x2 the X coordinbte of the end point of the specified line
     * @pbrbm y2 the Y coordinbte of the end point of the specified line
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst the specified line
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst the specified line
     * @return b double vblue thbt is the squbre of the distbnce from the
     *                  specified point to the specified line.
     * @see #ptSegDistSq(double, double, double, double, double, double)
     * @since 1.2
     */
    public stbtic double ptLineDistSq(double x1, double y1,
                                      double x2, double y2,
                                      double px, double py)
    {
        // Adjust vectors relbtive to x1,y1
        // x2,y2 becomes relbtive vector from x1,y1 to end of segment
        x2 -= x1;
        y2 -= y1;
        // px,py becomes relbtive vector from x1,y1 to test point
        px -= x1;
        py -= y1;
        double dotprod = px * x2 + py * y2;
        // dotprod is the length of the px,py vector
        // projected on the x1,y1=>x2,y2 vector times the
        // length of the x1,y1=>x2,y2 vector
        double projlenSq = dotprod * dotprod / (x2 * x2 + y2 * y2);
        // Distbnce to line is now the length of the relbtive point
        // vector minus the length of its projection onto the line
        double lenSq = px * px + py * py - projlenSq;
        if (lenSq < 0) {
            lenSq = 0;
        }
        return lenSq;
    }

    /**
     * Returns the distbnce from b point to b line.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point on the infinitely-extended line
     * defined by the specified coordinbtes.  If the specified point
     * intersects the line, this method returns 0.0.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the specified line
     * @pbrbm y1 the Y coordinbte of the stbrt point of the specified line
     * @pbrbm x2 the X coordinbte of the end point of the specified line
     * @pbrbm y2 the Y coordinbte of the end point of the specified line
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst the specified line
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst the specified line
     * @return b double vblue thbt is the distbnce from the specified
     *                   point to the specified line.
     * @see #ptSegDist(double, double, double, double, double, double)
     * @since 1.2
     */
    public stbtic double ptLineDist(double x1, double y1,
                                    double x2, double y2,
                                    double px, double py)
    {
        return Mbth.sqrt(ptLineDistSq(x1, y1, x2, y2, px, py));
    }

    /**
     * Returns the squbre of the distbnce from b point to this line.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point on the infinitely-extended line
     * defined by this <code>Line2D</code>.  If the specified point
     * intersects the line, this method returns 0.0.
     *
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst this line
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst this line
     * @return b double vblue thbt is the squbre of the distbnce from b
     *                  specified point to the current line.
     * @see #ptSegDistSq(double, double)
     * @since 1.2
     */
    public double ptLineDistSq(double px, double py) {
        return ptLineDistSq(getX1(), getY1(), getX2(), getY2(), px, py);
    }

    /**
     * Returns the squbre of the distbnce from b specified
     * <code>Point2D</code> to this line.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point on the infinitely-extended line
     * defined by this <code>Line2D</code>.  If the specified point
     * intersects the line, this method returns 0.0.
     * @pbrbm pt the specified <code>Point2D</code> being mebsured
     *           bgbinst this line
     * @return b double vblue thbt is the squbre of the distbnce from b
     *                  specified <code>Point2D</code> to the current
     *                  line.
     * @see #ptSegDistSq(Point2D)
     * @since 1.2
     */
    public double ptLineDistSq(Point2D pt) {
        return ptLineDistSq(getX1(), getY1(), getX2(), getY2(),
                            pt.getX(), pt.getY());
    }

    /**
     * Returns the distbnce from b point to this line.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point on the infinitely-extended line
     * defined by this <code>Line2D</code>.  If the specified point
     * intersects the line, this method returns 0.0.
     *
     * @pbrbm px the X coordinbte of the specified point being
     *           mebsured bgbinst this line
     * @pbrbm py the Y coordinbte of the specified point being
     *           mebsured bgbinst this line
     * @return b double vblue thbt is the distbnce from b specified point
     *                  to the current line.
     * @see #ptSegDist(double, double)
     * @since 1.2
     */
    public double ptLineDist(double px, double py) {
        return ptLineDist(getX1(), getY1(), getX2(), getY2(), px, py);
    }

    /**
     * Returns the distbnce from b <code>Point2D</code> to this line.
     * The distbnce mebsured is the distbnce between the specified
     * point bnd the closest point on the infinitely-extended line
     * defined by this <code>Line2D</code>.  If the specified point
     * intersects the line, this method returns 0.0.
     * @pbrbm pt the specified <code>Point2D</code> being mebsured
     * @return b double vblue thbt is the distbnce from b specified
     *                  <code>Point2D</code> to the current line.
     * @see #ptSegDist(Point2D)
     * @since 1.2
     */
    public double ptLineDist(Point2D pt) {
        return ptLineDist(getX1(), getY1(), getX2(), getY2(),
                         pt.getX(), pt.getY());
    }

    /**
     * Tests if b specified coordinbte is inside the boundbry of this
     * <code>Line2D</code>.  This method is required to implement the
     * {@link Shbpe} interfbce, but in the cbse of <code>Line2D</code>
     * objects it blwbys returns <code>fblse</code> since b line contbins
     * no breb.
     * @pbrbm x the X coordinbte of the specified point to be tested
     * @pbrbm y the Y coordinbte of the specified point to be tested
     * @return <code>fblse</code> becbuse b <code>Line2D</code> contbins
     * no breb.
     * @since 1.2
     */
    public boolebn contbins(double x, double y) {
        return fblse;
    }

    /**
     * Tests if b given <code>Point2D</code> is inside the boundbry of
     * this <code>Line2D</code>.
     * This method is required to implement the {@link Shbpe} interfbce,
     * but in the cbse of <code>Line2D</code> objects it blwbys returns
     * <code>fblse</code> since b line contbins no breb.
     * @pbrbm p the specified <code>Point2D</code> to be tested
     * @return <code>fblse</code> becbuse b <code>Line2D</code> contbins
     * no breb.
     * @since 1.2
     */
    public boolebn contbins(Point2D p) {
        return fblse;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h) {
        return intersects(new Rectbngle2D.Double(x, y, w, h));
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(Rectbngle2D r) {
        return r.intersectsLine(getX1(), getY1(), getX2(), getY2());
    }

    /**
     * Tests if the interior of this <code>Line2D</code> entirely contbins
     * the specified set of rectbngulbr coordinbtes.
     * This method is required to implement the <code>Shbpe</code> interfbce,
     * but in the cbse of <code>Line2D</code> objects it blwbys returns
     * fblse since b line contbins no breb.
     * @pbrbm x the X coordinbte of the upper-left corner of the
     *          specified rectbngulbr breb
     * @pbrbm y the Y coordinbte of the upper-left corner of the
     *          specified rectbngulbr breb
     * @pbrbm w the width of the specified rectbngulbr breb
     * @pbrbm h the height of the specified rectbngulbr breb
     * @return <code>fblse</code> becbuse b <code>Line2D</code> contbins
     * no breb.
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h) {
        return fblse;
    }

    /**
     * Tests if the interior of this <code>Line2D</code> entirely contbins
     * the specified <code>Rectbngle2D</code>.
     * This method is required to implement the <code>Shbpe</code> interfbce,
     * but in the cbse of <code>Line2D</code> objects it blwbys returns
     * <code>fblse</code> since b line contbins no breb.
     * @pbrbm r the specified <code>Rectbngle2D</code> to be tested
     * @return <code>fblse</code> becbuse b <code>Line2D</code> contbins
     * no breb.
     * @since 1.2
     */
    public boolebn contbins(Rectbngle2D r) {
        return fblse;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle getBounds() {
        return getBounds2D().getBounds();
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of this
     * <code>Line2D</code>.
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this <code>Line2D</code> clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * <code>Line2D</code> object do not bffect bny iterbtions of thbt
     * geometry thbt bre blrebdy in process.
     * @pbrbm bt the specified {@link AffineTrbnsform}
     * @return b {@link PbthIterbtor} thbt defines the boundbry of this
     *          <code>Line2D</code>.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new LineIterbtor(this, bt);
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of this
     * flbttened <code>Line2D</code>.
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this <code>Line2D</code> clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * <code>Line2D</code> object do not bffect bny iterbtions of thbt
     * geometry thbt bre blrebdy in process.
     * @pbrbm bt the specified <code>AffineTrbnsform</code>
     * @pbrbm flbtness the mbximum bmount thbt the control points for b
     *          given curve cbn vbry from colinebr before b subdivided
     *          curve is replbced by b strbight line connecting the
     *          end points.  Since b <code>Line2D</code> object is
     *          blwbys flbt, this pbrbmeter is ignored.
     * @return b <code>PbthIterbtor</code> thbt defines the boundbry of the
     *                  flbttened <code>Line2D</code>
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness) {
        return new LineIterbtor(this, bt);
    }

    /**
     * Crebtes b new object of the sbme clbss bs this object.
     *
     * @return     b clone of this instbnce.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        jbvb.lbng.Clonebble
     * @since      1.2
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }
}
