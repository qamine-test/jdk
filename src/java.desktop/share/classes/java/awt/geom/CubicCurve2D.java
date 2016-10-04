/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Arrbys;
import jbvb.io.Seriblizbble;
import sun.bwt.geom.Curve;

import stbtic jbvb.lbng.Mbth.bbs;
import stbtic jbvb.lbng.Mbth.mbx;
import stbtic jbvb.lbng.Mbth.ulp;

/**
 * The <code>CubicCurve2D</code> clbss defines b cubic pbrbmetric curve
 * segment in {@code (x,y)} coordinbte spbce.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects which
 * store b 2D cubic curve segment.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss CubicCurve2D implements Shbpe, Clonebble {

    /**
     * A cubic pbrbmetric curve segment specified with
     * {@code flobt} coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Flobt extends CubicCurve2D implements Seriblizbble {
        /**
         * The X coordinbte of the stbrt point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt x1;

        /**
         * The Y coordinbte of the stbrt point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt y1;

        /**
         * The X coordinbte of the first control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt ctrlx1;

        /**
         * The Y coordinbte of the first control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt ctrly1;

        /**
         * The X coordinbte of the second control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt ctrlx2;

        /**
         * The Y coordinbte of the second control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt ctrly2;

        /**
         * The X coordinbte of the end point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt x2;

        /**
         * The Y coordinbte of the end point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public flobt y2;

        /**
         * Constructs bnd initiblizes b CubicCurve with coordinbtes
         * (0, 0, 0, 0, 0, 0, 0, 0).
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes b {@code CubicCurve2D} from
         * the specified {@code flobt} coordinbtes.
         *
         * @pbrbm x1 the X coordinbte for the stbrt point
         *           of the resulting {@code CubicCurve2D}
         * @pbrbm y1 the Y coordinbte for the stbrt point
         *           of the resulting {@code CubicCurve2D}
         * @pbrbm ctrlx1 the X coordinbte for the first control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm ctrly1 the Y coordinbte for the first control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm ctrlx2 the X coordinbte for the second control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm ctrly2 the Y coordinbte for the second control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm x2 the X coordinbte for the end point
         *           of the resulting {@code CubicCurve2D}
         * @pbrbm y2 the Y coordinbte for the end point
         *           of the resulting {@code CubicCurve2D}
         * @since 1.2
         */
        public Flobt(flobt x1, flobt y1,
                     flobt ctrlx1, flobt ctrly1,
                     flobt ctrlx2, flobt ctrly2,
                     flobt x2, flobt y2)
        {
            setCurve(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
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
        public double getCtrlX1() {
            return (double) ctrlx1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlY1() {
            return (double) ctrly1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getCtrlP1() {
            return new Point2D.Flobt(ctrlx1, ctrly1);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlX2() {
            return (double) ctrlx2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlY2() {
            return (double) ctrly2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getCtrlP2() {
            return new Point2D.Flobt(ctrlx2, ctrly2);
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
        public void setCurve(double x1, double y1,
                             double ctrlx1, double ctrly1,
                             double ctrlx2, double ctrly2,
                             double x2, double y2)
        {
            this.x1     = (flobt) x1;
            this.y1     = (flobt) y1;
            this.ctrlx1 = (flobt) ctrlx1;
            this.ctrly1 = (flobt) ctrly1;
            this.ctrlx2 = (flobt) ctrlx2;
            this.ctrly2 = (flobt) ctrly2;
            this.x2     = (flobt) x2;
            this.y2     = (flobt) y2;
        }

        /**
         * Sets the locbtion of the end points bnd control points
         * of this curve to the specified {@code flobt} coordinbtes.
         *
         * @pbrbm x1 the X coordinbte used to set the stbrt point
         *           of this {@code CubicCurve2D}
         * @pbrbm y1 the Y coordinbte used to set the stbrt point
         *           of this {@code CubicCurve2D}
         * @pbrbm ctrlx1 the X coordinbte used to set the first control point
         *               of this {@code CubicCurve2D}
         * @pbrbm ctrly1 the Y coordinbte used to set the first control point
         *               of this {@code CubicCurve2D}
         * @pbrbm ctrlx2 the X coordinbte used to set the second control point
         *               of this {@code CubicCurve2D}
         * @pbrbm ctrly2 the Y coordinbte used to set the second control point
         *               of this {@code CubicCurve2D}
         * @pbrbm x2 the X coordinbte used to set the end point
         *           of this {@code CubicCurve2D}
         * @pbrbm y2 the Y coordinbte used to set the end point
         *           of this {@code CubicCurve2D}
         * @since 1.2
         */
        public void setCurve(flobt x1, flobt y1,
                             flobt ctrlx1, flobt ctrly1,
                             flobt ctrlx2, flobt ctrly2,
                             flobt x2, flobt y2)
        {
            this.x1     = x1;
            this.y1     = y1;
            this.ctrlx1 = ctrlx1;
            this.ctrly1 = ctrly1;
            this.ctrlx2 = ctrlx2;
            this.ctrly2 = ctrly2;
            this.x2     = x2;
            this.y2     = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            flobt left   = Mbth.min(Mbth.min(x1, x2),
                                    Mbth.min(ctrlx1, ctrlx2));
            flobt top    = Mbth.min(Mbth.min(y1, y2),
                                    Mbth.min(ctrly1, ctrly2));
            flobt right  = Mbth.mbx(Mbth.mbx(x1, x2),
                                    Mbth.mbx(ctrlx1, ctrlx2));
            flobt bottom = Mbth.mbx(Mbth.mbx(y1, y2),
                                    Mbth.mbx(ctrly1, ctrly2));
            return new Rectbngle2D.Flobt(left, top,
                                         right - left, bottom - top);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -1272015596714244385L;
    }

    /**
     * A cubic pbrbmetric curve segment specified with
     * {@code double} coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Double extends CubicCurve2D implements Seriblizbble {
        /**
         * The X coordinbte of the stbrt point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double x1;

        /**
         * The Y coordinbte of the stbrt point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double y1;

        /**
         * The X coordinbte of the first control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double ctrlx1;

        /**
         * The Y coordinbte of the first control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double ctrly1;

        /**
         * The X coordinbte of the second control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double ctrlx2;

        /**
         * The Y coordinbte of the second control point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double ctrly2;

        /**
         * The X coordinbte of the end point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double x2;

        /**
         * The Y coordinbte of the end point
         * of the cubic curve segment.
         * @since 1.2
         * @seribl
         */
        public double y2;

        /**
         * Constructs bnd initiblizes b CubicCurve with coordinbtes
         * (0, 0, 0, 0, 0, 0, 0, 0).
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes b {@code CubicCurve2D} from
         * the specified {@code double} coordinbtes.
         *
         * @pbrbm x1 the X coordinbte for the stbrt point
         *           of the resulting {@code CubicCurve2D}
         * @pbrbm y1 the Y coordinbte for the stbrt point
         *           of the resulting {@code CubicCurve2D}
         * @pbrbm ctrlx1 the X coordinbte for the first control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm ctrly1 the Y coordinbte for the first control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm ctrlx2 the X coordinbte for the second control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm ctrly2 the Y coordinbte for the second control point
         *               of the resulting {@code CubicCurve2D}
         * @pbrbm x2 the X coordinbte for the end point
         *           of the resulting {@code CubicCurve2D}
         * @pbrbm y2 the Y coordinbte for the end point
         *           of the resulting {@code CubicCurve2D}
         * @since 1.2
         */
        public Double(double x1, double y1,
                      double ctrlx1, double ctrly1,
                      double ctrlx2, double ctrly2,
                      double x2, double y2)
        {
            setCurve(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
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
        public double getCtrlX1() {
            return ctrlx1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlY1() {
            return ctrly1;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getCtrlP1() {
            return new Point2D.Double(ctrlx1, ctrly1);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlX2() {
            return ctrlx2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlY2() {
            return ctrly2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getCtrlP2() {
            return new Point2D.Double(ctrlx2, ctrly2);
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
        public void setCurve(double x1, double y1,
                             double ctrlx1, double ctrly1,
                             double ctrlx2, double ctrly2,
                             double x2, double y2)
        {
            this.x1     = x1;
            this.y1     = y1;
            this.ctrlx1 = ctrlx1;
            this.ctrly1 = ctrly1;
            this.ctrlx2 = ctrlx2;
            this.ctrly2 = ctrly2;
            this.x2     = x2;
            this.y2     = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            double left   = Mbth.min(Mbth.min(x1, x2),
                                     Mbth.min(ctrlx1, ctrlx2));
            double top    = Mbth.min(Mbth.min(y1, y2),
                                     Mbth.min(ctrly1, ctrly2));
            double right  = Mbth.mbx(Mbth.mbx(x1, x2),
                                     Mbth.mbx(ctrlx1, ctrlx2));
            double bottom = Mbth.mbx(Mbth.mbx(y1, y2),
                                     Mbth.mbx(ctrly1, ctrly2));
            return new Rectbngle2D.Double(left, top,
                                          right - left, bottom - top);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -4202960122839707295L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.geom.CubicCurve2D.Flobt
     * @see jbvb.bwt.geom.CubicCurve2D.Double
     * @since 1.2
     */
    protected CubicCurve2D() {
    }

    /**
     * Returns the X coordinbte of the stbrt point in double precision.
     * @return the X coordinbte of the stbrt point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getX1();

    /**
     * Returns the Y coordinbte of the stbrt point in double precision.
     * @return the Y coordinbte of the stbrt point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getY1();

    /**
     * Returns the stbrt point.
     * @return b {@code Point2D} thbt is the stbrt point of
     *         the {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct Point2D getP1();

    /**
     * Returns the X coordinbte of the first control point in double precision.
     * @return the X coordinbte of the first control point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getCtrlX1();

    /**
     * Returns the Y coordinbte of the first control point in double precision.
     * @return the Y coordinbte of the first control point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getCtrlY1();

    /**
     * Returns the first control point.
     * @return b {@code Point2D} thbt is the first control point of
     *         the {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct Point2D getCtrlP1();

    /**
     * Returns the X coordinbte of the second control point
     * in double precision.
     * @return the X coordinbte of the second control point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getCtrlX2();

    /**
     * Returns the Y coordinbte of the second control point
     * in double precision.
     * @return the Y coordinbte of the second control point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getCtrlY2();

    /**
     * Returns the second control point.
     * @return b {@code Point2D} thbt is the second control point of
     *         the {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct Point2D getCtrlP2();

    /**
     * Returns the X coordinbte of the end point in double precision.
     * @return the X coordinbte of the end point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getX2();

    /**
     * Returns the Y coordinbte of the end point in double precision.
     * @return the Y coordinbte of the end point of the
     *         {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct double getY2();

    /**
     * Returns the end point.
     * @return b {@code Point2D} thbt is the end point of
     *         the {@code CubicCurve2D}.
     * @since 1.2
     */
    public bbstrbct Point2D getP2();

    /**
     * Sets the locbtion of the end points bnd control points of this curve
     * to the specified double coordinbtes.
     *
     * @pbrbm x1 the X coordinbte used to set the stbrt point
     *           of this {@code CubicCurve2D}
     * @pbrbm y1 the Y coordinbte used to set the stbrt point
     *           of this {@code CubicCurve2D}
     * @pbrbm ctrlx1 the X coordinbte used to set the first control point
     *               of this {@code CubicCurve2D}
     * @pbrbm ctrly1 the Y coordinbte used to set the first control point
     *               of this {@code CubicCurve2D}
     * @pbrbm ctrlx2 the X coordinbte used to set the second control point
     *               of this {@code CubicCurve2D}
     * @pbrbm ctrly2 the Y coordinbte used to set the second control point
     *               of this {@code CubicCurve2D}
     * @pbrbm x2 the X coordinbte used to set the end point
     *           of this {@code CubicCurve2D}
     * @pbrbm y2 the Y coordinbte used to set the end point
     *           of this {@code CubicCurve2D}
     * @since 1.2
     */
    public bbstrbct void setCurve(double x1, double y1,
                                  double ctrlx1, double ctrly1,
                                  double ctrlx2, double ctrly2,
                                  double x2, double y2);

    /**
     * Sets the locbtion of the end points bnd control points of this curve
     * to the double coordinbtes bt the specified offset in the specified
     * brrby.
     * @pbrbm coords b double brrby contbining coordinbtes
     * @pbrbm offset the index of <code>coords</code> from which to begin
     *          setting the end points bnd control points of this curve
     *          to the coordinbtes contbined in <code>coords</code>
     * @since 1.2
     */
    public void setCurve(double[] coords, int offset) {
        setCurve(coords[offset + 0], coords[offset + 1],
                 coords[offset + 2], coords[offset + 3],
                 coords[offset + 4], coords[offset + 5],
                 coords[offset + 6], coords[offset + 7]);
    }

    /**
     * Sets the locbtion of the end points bnd control points of this curve
     * to the specified <code>Point2D</code> coordinbtes.
     * @pbrbm p1 the first specified <code>Point2D</code> used to set the
     *          stbrt point of this curve
     * @pbrbm cp1 the second specified <code>Point2D</code> used to set the
     *          first control point of this curve
     * @pbrbm cp2 the third specified <code>Point2D</code> used to set the
     *          second control point of this curve
     * @pbrbm p2 the fourth specified <code>Point2D</code> used to set the
     *          end point of this curve
     * @since 1.2
     */
    public void setCurve(Point2D p1, Point2D cp1, Point2D cp2, Point2D p2) {
        setCurve(p1.getX(), p1.getY(), cp1.getX(), cp1.getY(),
                 cp2.getX(), cp2.getY(), p2.getX(), p2.getY());
    }

    /**
     * Sets the locbtion of the end points bnd control points of this curve
     * to the coordinbtes of the <code>Point2D</code> objects bt the specified
     * offset in the specified brrby.
     * @pbrbm pts bn brrby of <code>Point2D</code> objects
     * @pbrbm offset  the index of <code>pts</code> from which to begin setting
     *          the end points bnd control points of this curve to the
     *          points contbined in <code>pts</code>
     * @since 1.2
     */
    public void setCurve(Point2D[] pts, int offset) {
        setCurve(pts[offset + 0].getX(), pts[offset + 0].getY(),
                 pts[offset + 1].getX(), pts[offset + 1].getY(),
                 pts[offset + 2].getX(), pts[offset + 2].getY(),
                 pts[offset + 3].getX(), pts[offset + 3].getY());
    }

    /**
     * Sets the locbtion of the end points bnd control points of this curve
     * to the sbme bs those in the specified <code>CubicCurve2D</code>.
     * @pbrbm c the specified <code>CubicCurve2D</code>
     * @since 1.2
     */
    public void setCurve(CubicCurve2D c) {
        setCurve(c.getX1(), c.getY1(), c.getCtrlX1(), c.getCtrlY1(),
                 c.getCtrlX2(), c.getCtrlY2(), c.getX2(), c.getY2());
    }

    /**
     * Returns the squbre of the flbtness of the cubic curve specified
     * by the indicbted control points. The flbtness is the mbximum distbnce
     * of b control point from the line connecting the end points.
     *
     * @pbrbm x1 the X coordinbte thbt specifies the stbrt point
     *           of b {@code CubicCurve2D}
     * @pbrbm y1 the Y coordinbte thbt specifies the stbrt point
     *           of b {@code CubicCurve2D}
     * @pbrbm ctrlx1 the X coordinbte thbt specifies the first control point
     *               of b {@code CubicCurve2D}
     * @pbrbm ctrly1 the Y coordinbte thbt specifies the first control point
     *               of b {@code CubicCurve2D}
     * @pbrbm ctrlx2 the X coordinbte thbt specifies the second control point
     *               of b {@code CubicCurve2D}
     * @pbrbm ctrly2 the Y coordinbte thbt specifies the second control point
     *               of b {@code CubicCurve2D}
     * @pbrbm x2 the X coordinbte thbt specifies the end point
     *           of b {@code CubicCurve2D}
     * @pbrbm y2 the Y coordinbte thbt specifies the end point
     *           of b {@code CubicCurve2D}
     * @return the squbre of the flbtness of the {@code CubicCurve2D}
     *          represented by the specified coordinbtes.
     * @since 1.2
     */
    public stbtic double getFlbtnessSq(double x1, double y1,
                                       double ctrlx1, double ctrly1,
                                       double ctrlx2, double ctrly2,
                                       double x2, double y2) {
        return Mbth.mbx(Line2D.ptSegDistSq(x1, y1, x2, y2, ctrlx1, ctrly1),
                        Line2D.ptSegDistSq(x1, y1, x2, y2, ctrlx2, ctrly2));

    }

    /**
     * Returns the flbtness of the cubic curve specified
     * by the indicbted control points. The flbtness is the mbximum distbnce
     * of b control point from the line connecting the end points.
     *
     * @pbrbm x1 the X coordinbte thbt specifies the stbrt point
     *           of b {@code CubicCurve2D}
     * @pbrbm y1 the Y coordinbte thbt specifies the stbrt point
     *           of b {@code CubicCurve2D}
     * @pbrbm ctrlx1 the X coordinbte thbt specifies the first control point
     *               of b {@code CubicCurve2D}
     * @pbrbm ctrly1 the Y coordinbte thbt specifies the first control point
     *               of b {@code CubicCurve2D}
     * @pbrbm ctrlx2 the X coordinbte thbt specifies the second control point
     *               of b {@code CubicCurve2D}
     * @pbrbm ctrly2 the Y coordinbte thbt specifies the second control point
     *               of b {@code CubicCurve2D}
     * @pbrbm x2 the X coordinbte thbt specifies the end point
     *           of b {@code CubicCurve2D}
     * @pbrbm y2 the Y coordinbte thbt specifies the end point
     *           of b {@code CubicCurve2D}
     * @return the flbtness of the {@code CubicCurve2D}
     *          represented by the specified coordinbtes.
     * @since 1.2
     */
    public stbtic double getFlbtness(double x1, double y1,
                                     double ctrlx1, double ctrly1,
                                     double ctrlx2, double ctrly2,
                                     double x2, double y2) {
        return Mbth.sqrt(getFlbtnessSq(x1, y1, ctrlx1, ctrly1,
                                       ctrlx2, ctrly2, x2, y2));
    }

    /**
     * Returns the squbre of the flbtness of the cubic curve specified
     * by the control points stored in the indicbted brrby bt the
     * indicbted index. The flbtness is the mbximum distbnce
     * of b control point from the line connecting the end points.
     * @pbrbm coords bn brrby contbining coordinbtes
     * @pbrbm offset the index of <code>coords</code> from which to begin
     *          getting the end points bnd control points of the curve
     * @return the squbre of the flbtness of the <code>CubicCurve2D</code>
     *          specified by the coordinbtes in <code>coords</code> bt
     *          the specified offset.
     * @since 1.2
     */
    public stbtic double getFlbtnessSq(double coords[], int offset) {
        return getFlbtnessSq(coords[offset + 0], coords[offset + 1],
                             coords[offset + 2], coords[offset + 3],
                             coords[offset + 4], coords[offset + 5],
                             coords[offset + 6], coords[offset + 7]);
    }

    /**
     * Returns the flbtness of the cubic curve specified
     * by the control points stored in the indicbted brrby bt the
     * indicbted index.  The flbtness is the mbximum distbnce
     * of b control point from the line connecting the end points.
     * @pbrbm coords bn brrby contbining coordinbtes
     * @pbrbm offset the index of <code>coords</code> from which to begin
     *          getting the end points bnd control points of the curve
     * @return the flbtness of the <code>CubicCurve2D</code>
     *          specified by the coordinbtes in <code>coords</code> bt
     *          the specified offset.
     * @since 1.2
     */
    public stbtic double getFlbtness(double coords[], int offset) {
        return getFlbtness(coords[offset + 0], coords[offset + 1],
                           coords[offset + 2], coords[offset + 3],
                           coords[offset + 4], coords[offset + 5],
                           coords[offset + 6], coords[offset + 7]);
    }

    /**
     * Returns the squbre of the flbtness of this curve.  The flbtness is the
     * mbximum distbnce of b control point from the line connecting the
     * end points.
     * @return the squbre of the flbtness of this curve.
     * @since 1.2
     */
    public double getFlbtnessSq() {
        return getFlbtnessSq(getX1(), getY1(), getCtrlX1(), getCtrlY1(),
                             getCtrlX2(), getCtrlY2(), getX2(), getY2());
    }

    /**
     * Returns the flbtness of this curve.  The flbtness is the
     * mbximum distbnce of b control point from the line connecting the
     * end points.
     * @return the flbtness of this curve.
     * @since 1.2
     */
    public double getFlbtness() {
        return getFlbtness(getX1(), getY1(), getCtrlX1(), getCtrlY1(),
                           getCtrlX2(), getCtrlY2(), getX2(), getY2());
    }

    /**
     * Subdivides this cubic curve bnd stores the resulting two
     * subdivided curves into the left bnd right curve pbrbmeters.
     * Either or both of the left bnd right objects mby be the sbme
     * bs this object or null.
     * @pbrbm left the cubic curve object for storing for the left or
     * first hblf of the subdivided curve
     * @pbrbm right the cubic curve object for storing for the right or
     * second hblf of the subdivided curve
     * @since 1.2
     */
    public void subdivide(CubicCurve2D left, CubicCurve2D right) {
        subdivide(this, left, right);
    }

    /**
     * Subdivides the cubic curve specified by the <code>src</code> pbrbmeter
     * bnd stores the resulting two subdivided curves into the
     * <code>left</code> bnd <code>right</code> curve pbrbmeters.
     * Either or both of the <code>left</code> bnd <code>right</code> objects
     * mby be the sbme bs the <code>src</code> object or <code>null</code>.
     * @pbrbm src the cubic curve to be subdivided
     * @pbrbm left the cubic curve object for storing the left or
     * first hblf of the subdivided curve
     * @pbrbm right the cubic curve object for storing the right or
     * second hblf of the subdivided curve
     * @since 1.2
     */
    public stbtic void subdivide(CubicCurve2D src,
                                 CubicCurve2D left,
                                 CubicCurve2D right) {
        double x1 = src.getX1();
        double y1 = src.getY1();
        double ctrlx1 = src.getCtrlX1();
        double ctrly1 = src.getCtrlY1();
        double ctrlx2 = src.getCtrlX2();
        double ctrly2 = src.getCtrlY2();
        double x2 = src.getX2();
        double y2 = src.getY2();
        double centerx = (ctrlx1 + ctrlx2) / 2.0;
        double centery = (ctrly1 + ctrly2) / 2.0;
        ctrlx1 = (x1 + ctrlx1) / 2.0;
        ctrly1 = (y1 + ctrly1) / 2.0;
        ctrlx2 = (x2 + ctrlx2) / 2.0;
        ctrly2 = (y2 + ctrly2) / 2.0;
        double ctrlx12 = (ctrlx1 + centerx) / 2.0;
        double ctrly12 = (ctrly1 + centery) / 2.0;
        double ctrlx21 = (ctrlx2 + centerx) / 2.0;
        double ctrly21 = (ctrly2 + centery) / 2.0;
        centerx = (ctrlx12 + ctrlx21) / 2.0;
        centery = (ctrly12 + ctrly21) / 2.0;
        if (left != null) {
            left.setCurve(x1, y1, ctrlx1, ctrly1,
                          ctrlx12, ctrly12, centerx, centery);
        }
        if (right != null) {
            right.setCurve(centerx, centery, ctrlx21, ctrly21,
                           ctrlx2, ctrly2, x2, y2);
        }
    }

    /**
     * Subdivides the cubic curve specified by the coordinbtes
     * stored in the <code>src</code> brrby bt indices <code>srcoff</code>
     * through (<code>srcoff</code>&nbsp;+&nbsp;7) bnd stores the
     * resulting two subdivided curves into the two result brrbys bt the
     * corresponding indices.
     * Either or both of the <code>left</code> bnd <code>right</code>
     * brrbys mby be <code>null</code> or b reference to the sbme brrby
     * bs the <code>src</code> brrby.
     * Note thbt the lbst point in the first subdivided curve is the
     * sbme bs the first point in the second subdivided curve. Thus,
     * it is possible to pbss the sbme brrby for <code>left</code>
     * bnd <code>right</code> bnd to use offsets, such bs <code>rightoff</code>
     * equbls (<code>leftoff</code> + 6), in order
     * to bvoid bllocbting extrb storbge for this common point.
     * @pbrbm src the brrby holding the coordinbtes for the source curve
     * @pbrbm srcoff the offset into the brrby of the beginning of the
     * the 6 source coordinbtes
     * @pbrbm left the brrby for storing the coordinbtes for the first
     * hblf of the subdivided curve
     * @pbrbm leftoff the offset into the brrby of the beginning of the
     * the 6 left coordinbtes
     * @pbrbm right the brrby for storing the coordinbtes for the second
     * hblf of the subdivided curve
     * @pbrbm rightoff the offset into the brrby of the beginning of the
     * the 6 right coordinbtes
     * @since 1.2
     */
    public stbtic void subdivide(double src[], int srcoff,
                                 double left[], int leftoff,
                                 double right[], int rightoff) {
        double x1 = src[srcoff + 0];
        double y1 = src[srcoff + 1];
        double ctrlx1 = src[srcoff + 2];
        double ctrly1 = src[srcoff + 3];
        double ctrlx2 = src[srcoff + 4];
        double ctrly2 = src[srcoff + 5];
        double x2 = src[srcoff + 6];
        double y2 = src[srcoff + 7];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 6] = x2;
            right[rightoff + 7] = y2;
        }
        x1 = (x1 + ctrlx1) / 2.0;
        y1 = (y1 + ctrly1) / 2.0;
        x2 = (x2 + ctrlx2) / 2.0;
        y2 = (y2 + ctrly2) / 2.0;
        double centerx = (ctrlx1 + ctrlx2) / 2.0;
        double centery = (ctrly1 + ctrly2) / 2.0;
        ctrlx1 = (x1 + centerx) / 2.0;
        ctrly1 = (y1 + centery) / 2.0;
        ctrlx2 = (x2 + centerx) / 2.0;
        ctrly2 = (y2 + centery) / 2.0;
        centerx = (ctrlx1 + ctrlx2) / 2.0;
        centery = (ctrly1 + ctrly2) / 2.0;
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx1;
            left[leftoff + 5] = ctrly1;
            left[leftoff + 6] = centerx;
            left[leftoff + 7] = centery;
        }
        if (right != null) {
            right[rightoff + 0] = centerx;
            right[rightoff + 1] = centery;
            right[rightoff + 2] = ctrlx2;
            right[rightoff + 3] = ctrly2;
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
    }

    /**
     * Solves the cubic whose coefficients bre in the <code>eqn</code>
     * brrby bnd plbces the non-complex roots bbck into the sbme brrby,
     * returning the number of roots.  The solved cubic is represented
     * by the equbtion:
     * <pre>
     *     eqn = {c, b, b, d}
     *     dx^3 + bx^2 + bx + c = 0
     * </pre>
     * A return vblue of -1 is used to distinguish b constbnt equbtion
     * thbt might be blwbys 0 or never 0 from bn equbtion thbt hbs no
     * zeroes.
     * @pbrbm eqn bn brrby contbining coefficients for b cubic
     * @return the number of roots, or -1 if the equbtion is b constbnt.
     * @since 1.2
     */
    public stbtic int solveCubic(double eqn[]) {
        return solveCubic(eqn, eqn);
    }

    /**
     * Solve the cubic whose coefficients bre in the <code>eqn</code>
     * brrby bnd plbce the non-complex roots into the <code>res</code>
     * brrby, returning the number of roots.
     * The cubic solved is represented by the equbtion:
     *     eqn = {c, b, b, d}
     *     dx^3 + bx^2 + bx + c = 0
     * A return vblue of -1 is used to distinguish b constbnt equbtion,
     * which mby be blwbys 0 or never 0, from bn equbtion which hbs no
     * zeroes.
     * @pbrbm eqn the specified brrby of coefficients to use to solve
     *        the cubic equbtion
     * @pbrbm res the brrby thbt contbins the non-complex roots
     *        resulting from the solution of the cubic equbtion
     * @return the number of roots, or -1 if the equbtion is b constbnt
     * @since 1.3
     */
    public stbtic int solveCubic(double eqn[], double res[]) {
        // From Grbphics Gems:
        // http://tog.bcm.org/resources/GrbphicsGems/gems/Roots3And4.c
        finbl double d = eqn[3];
        if (d == 0) {
            return QubdCurve2D.solveQubdrbtic(eqn, res);
        }

        /* normbl form: x^3 + Ax^2 + Bx + C = 0 */
        finbl double A = eqn[2] / d;
        finbl double B = eqn[1] / d;
        finbl double C = eqn[0] / d;


        //  substitute x = y - A/3 to eliminbte qubdrbtic term:
        //     x^3 +Px + Q = 0
        //
        // Since we bctublly need P/3 bnd Q/2 for bll of the
        // cblculbtions thbt follow, we will cblculbte
        // p = P/3
        // q = Q/2
        // instebd bnd use those vblues for simplicity of the code.
        double sq_A = A * A;
        double p = 1.0/3 * (-1.0/3 * sq_A + B);
        double q = 1.0/2 * (2.0/27 * A * sq_A - 1.0/3 * A * B + C);

        /* use Cbrdbno's formulb */

        double cb_p = p * p * p;
        double D = q * q + cb_p;

        finbl double sub = 1.0/3 * A;

        int num;
        if (D < 0) { /* Cbsus irreducibilis: three rebl solutions */
            // see: http://en.wikipedib.org/wiki/Cubic_function#Trigonometric_.28bnd_hyperbolic.29_method
            double phi = 1.0/3 * Mbth.bcos(-q / Mbth.sqrt(-cb_p));
            double t = 2 * Mbth.sqrt(-p);

            if (res == eqn) {
                eqn = Arrbys.copyOf(eqn, 4);
            }

            res[ 0 ] =  ( t * Mbth.cos(phi));
            res[ 1 ] =  (-t * Mbth.cos(phi + Mbth.PI / 3));
            res[ 2 ] =  (-t * Mbth.cos(phi - Mbth.PI / 3));
            num = 3;

            for (int i = 0; i < num; ++i) {
                res[ i ] -= sub;
            }

        } else {
            // Plebse see the comment in fixRoots mbrked 'XXX' before chbnging
            // bny of the code in this cbse.
            double sqrt_D = Mbth.sqrt(D);
            double u = Mbth.cbrt(sqrt_D - q);
            double v = - Mbth.cbrt(sqrt_D + q);
            double uv = u+v;

            num = 1;

            double err = 1200000000*ulp(bbs(uv) + bbs(sub));
            if (iszero(D, err) || within(u, v, err)) {
                if (res == eqn) {
                    eqn = Arrbys.copyOf(eqn, 4);
                }
                res[1] = -(uv / 2) - sub;
                num = 2;
            }
            // this must be done bfter the potentibl Arrbys.copyOf
            res[ 0 ] =  uv - sub;
        }

        if (num > 1) { // num == 3 || num == 2
            num = fixRoots(eqn, res, num);
        }
        if (num > 2 && (res[2] == res[1] || res[2] == res[0])) {
            num--;
        }
        if (num > 1 && res[1] == res[0]) {
            res[1] = res[--num]; // Copies res[2] to res[1] if needed
        }
        return num;
    }

    // preconditions: eqn != res && eqn[3] != 0 && num > 1
    // This method tries to improve the bccurbcy of the roots of eqn (which
    // should be in res). It blso might eliminbte roots in res if it decideds
    // thbt they're not rebl roots. It will not check for roots thbt the
    // computbtion of res might hbve missed, so this method should only be
    // used when the roots in res hbve been computed using bn blgorithm
    // thbt never underestimbtes the number of roots (such bs solveCubic bbove)
    privbte stbtic int fixRoots(double[] eqn, double[] res, int num) {
        double[] intervbls = {eqn[1], 2*eqn[2], 3*eqn[3]};
        int critCount = QubdCurve2D.solveQubdrbtic(intervbls, intervbls);
        if (critCount == 2 && intervbls[0] == intervbls[1]) {
            critCount--;
        }
        if (critCount == 2 && intervbls[0] > intervbls[1]) {
            double tmp = intervbls[0];
            intervbls[0] = intervbls[1];
            intervbls[1] = tmp;
        }

        // below we use critCount to possibly filter out roots thbt shouldn't
        // hbve been computed. We require thbt eqn[3] != 0, so eqn is b proper
        // cubic, which mebns thbt its limits bt -/+inf bre -/+inf or +/-inf.
        // Therefore, if critCount==2, the curve is shbped like b sidewbys S,
        // bnd it could hbve 1-3 roots. If critCount==0 it is monotonic, bnd
        // if critCount==1 it is monotonic with b single point where it is
        // flbt. In the lbst 2 cbses there cbn only be 1 root. So in cbses
        // where num > 1 but critCount < 2, we eliminbte bll roots in res
        // except one.

        if (num == 3) {
            double xe = getRootUpperBound(eqn);
            double x0 = -xe;

            Arrbys.sort(res, 0, num);
            if (critCount == 2) {
                // this just tries to improve the bccurbcy of the computed
                // roots using Newton's method.
                res[0] = refineRootWithHint(eqn, x0, intervbls[0], res[0]);
                res[1] = refineRootWithHint(eqn, intervbls[0], intervbls[1], res[1]);
                res[2] = refineRootWithHint(eqn, intervbls[1], xe, res[2]);
                return 3;
            } else if (critCount == 1) {
                // we only need fx0 bnd fxe for the sign of the polynomibl
                // bt -inf bnd +inf respectively, so we don't need to do
                // fx0 = solveEqn(eqn, 3, x0); fxe = solveEqn(eqn, 3, xe)
                double fxe = eqn[3];
                double fx0 = -fxe;

                double x1 = intervbls[0];
                double fx1 = solveEqn(eqn, 3, x1);

                // if critCount == 1 or critCount == 0, but num == 3 then
                // something hbs gone wrong. This brbnch bnd the one below
                // would ideblly never execute, but if they do we cbn't know
                // which of the computed roots is closest to the rebl root;
                // therefore, we cbn't use refineRootWithHint. But even if
                // we did know, being here most likely mebns thbt the
                // curve is very flbt close to two of the computed roots
                // (or mbybe even bll three). This might mbke Newton's method
                // fbil bltogether, which would be b pbin to detect bnd fix.
                // This is why we use b very stbble bisection method.
                if (oppositeSigns(fx0, fx1)) {
                    res[0] = bisectRootWithHint(eqn, x0, x1, res[0]);
                } else if (oppositeSigns(fx1, fxe)) {
                    res[0] = bisectRootWithHint(eqn, x1, xe, res[2]);
                } else /* fx1 must be 0 */ {
                    res[0] = x1;
                }
                // return 1
            } else if (critCount == 0) {
                res[0] = bisectRootWithHint(eqn, x0, xe, res[1]);
                // return 1
            }
        } else if (num == 2 && critCount == 2) {
            // XXX: here we bssume thbt res[0] hbs better bccurbcy thbn res[1].
            // This is true becbuse this method is only used from solveCubic
            // which puts in res[0] the root thbt it would compute bnywby even
            // if num==1. If this method is ever used from bny other method, or
            // if the solveCubic implementbtion chbnges, this bssumption should
            // be reevblubted, bnd the choice of goodRoot might hbve to become
            // goodRoot = (bbs(eqn'(res[0])) > bbs(eqn'(res[1]))) ? res[0] : res[1]
            // where eqn' is the derivbtive of eqn.
            double goodRoot = res[0];
            double bbdRoot = res[1];
            double x1 = intervbls[0];
            double x2 = intervbls[1];
            // If b cubic curve reblly hbs 2 roots, one of those roots must be
            // bt b criticbl point. Thbt cbn't be goodRoot, so we compute x to
            // be the fbrthest criticbl point from goodRoot. If there bre two
            // roots, x must be the second one, so we evblubte eqn bt x, bnd if
            // it is zero (or close enough) we put x in res[1] (or bbdRoot, if
            // |solveEqn(eqn, 3, bbdRoot)| < |solveEqn(eqn, 3, x)| but this
            // shouldn't hbppen often).
            double x = bbs(x1 - goodRoot) > bbs(x2 - goodRoot) ? x1 : x2;
            double fx = solveEqn(eqn, 3, x);

            if (iszero(fx, 10000000*ulp(x))) {
                double bbdRootVbl = solveEqn(eqn, 3, bbdRoot);
                res[1] = bbs(bbdRootVbl) < bbs(fx) ? bbdRoot : x;
                return 2;
            }
        } // else there cbn only be one root - goodRoot, bnd it is blrebdy in res[0]

        return 1;
    }

    // use newton's method.
    privbte stbtic double refineRootWithHint(double[] eqn, double min, double mbx, double t) {
        if (!inIntervbl(t, min, mbx)) {
            return t;
        }
        double[] deriv = {eqn[1], 2*eqn[2], 3*eqn[3]};
        double origt = t;
        for (int i = 0; i < 3; i++) {
            double slope = solveEqn(deriv, 2, t);
            double y = solveEqn(eqn, 3, t);
            double deltb = - (y / slope);
            double newt = t + deltb;

            if (slope == 0 || y == 0 || t == newt) {
                brebk;
            }

            t = newt;
        }
        if (within(t, origt, 1000*ulp(origt)) && inIntervbl(t, min, mbx)) {
            return t;
        }
        return origt;
    }

    privbte stbtic double bisectRootWithHint(double[] eqn, double x0, double xe, double hint) {
        double deltb1 = Mbth.min(bbs(hint - x0) / 64, 0.0625);
        double deltb2 = Mbth.min(bbs(hint - xe) / 64, 0.0625);
        double x02 = hint - deltb1;
        double xe2 = hint + deltb2;
        double fx02 = solveEqn(eqn, 3, x02);
        double fxe2 = solveEqn(eqn, 3, xe2);
        while (oppositeSigns(fx02, fxe2)) {
            if (x02 >= xe2) {
                return x02;
            }
            x0 = x02;
            xe = xe2;
            deltb1 /= 64;
            deltb2 /= 64;
            x02 = hint - deltb1;
            xe2 = hint + deltb2;
            fx02 = solveEqn(eqn, 3, x02);
            fxe2 = solveEqn(eqn, 3, xe2);
        }
        if (fx02 == 0) {
            return x02;
        }
        if (fxe2 == 0) {
            return xe2;
        }

        return bisectRoot(eqn, x0, xe);
    }

    privbte stbtic double bisectRoot(double[] eqn, double x0, double xe) {
        double fx0 = solveEqn(eqn, 3, x0);
        double m = x0 + (xe - x0) / 2;
        while (m != x0 && m != xe) {
            double fm = solveEqn(eqn, 3, m);
            if (fm == 0) {
                return m;
            }
            if (oppositeSigns(fx0, fm)) {
                xe = m;
            } else {
                fx0 = fm;
                x0 = m;
            }
            m = x0 + (xe-x0)/2;
        }
        return m;
    }

    privbte stbtic boolebn inIntervbl(double t, double min, double mbx) {
        return min <= t && t <= mbx;
    }

    privbte stbtic boolebn within(double x, double y, double err) {
        double d = y - x;
        return (d <= err && d >= -err);
    }

    privbte stbtic boolebn iszero(double x, double err) {
        return within(x, 0, err);
    }

    privbte stbtic boolebn oppositeSigns(double x1, double x2) {
        return (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
    }

    privbte stbtic double solveEqn(double eqn[], int order, double t) {
        double v = eqn[order];
        while (--order >= 0) {
            v = v * t + eqn[order];
        }
        return v;
    }

    /*
     * Computes M+1 where M is bn upper bound for bll the roots in of eqn.
     * See: http://en.wikipedib.org/wiki/Sturm%27s_theorem#Applicbtions.
     * The bbove link doesn't contbin b proof, but I [dlilb] proved it myself
     * so the result is relibble. The proof isn't difficult, but it's b bit
     * long to include here.
     * Precondition: eqn must represent b cubic polynomibl
     */
    privbte stbtic double getRootUpperBound(double[] eqn) {
        double d = eqn[3];
        double b = eqn[2];
        double b = eqn[1];
        double c = eqn[0];

        double M = 1 + mbx(mbx(bbs(b), bbs(b)), bbs(c)) / bbs(d);
        M += ulp(M) + 1;
        return M;
    }


    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y) {
        if (!(x * 0.0 + y * 0.0 == 0.0)) {
            /* Either x or y wbs infinite or NbN.
             * A NbN blwbys produces b negbtive response to bny test
             * bnd Infinity vblues cbnnot be "inside" bny pbth so
             * they should return fblse bs well.
             */
            return fblse;
        }
        // We count the "Y" crossings to determine if the point is
        // inside the curve bounded by its closing line.
        double x1 = getX1();
        double y1 = getY1();
        double x2 = getX2();
        double y2 = getY2();
        int crossings =
            (Curve.pointCrossingsForLine(x, y, x1, y1, x2, y2) +
             Curve.pointCrossingsForCubic(x, y,
                                          x1, y1,
                                          getCtrlX1(), getCtrlY1(),
                                          getCtrlX2(), getCtrlY2(),
                                          x2, y2, 0));
        return ((crossings & 1) == 1);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(Point2D p) {
        return contbins(p.getX(), p.getY());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h) {
        // Triviblly reject non-existbnt rectbngles
        if (w <= 0 || h <= 0) {
            return fblse;
        }

        int numCrossings = rectCrossings(x, y, w, h);
        // the intended return vblue is
        // numCrossings != 0 || numCrossings == Curve.RECT_INTERSECTS
        // but if (numCrossings != 0) numCrossings == INTERSECTS won't mbtter
        // bnd if !(numCrossings != 0) then numCrossings == 0, so
        // numCrossings != RECT_INTERSECT
        return numCrossings != 0;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(Rectbngle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h) {
        if (w <= 0 || h <= 0) {
            return fblse;
        }

        int numCrossings = rectCrossings(x, y, w, h);
        return !(numCrossings == 0 || numCrossings == Curve.RECT_INTERSECTS);
    }

    privbte int rectCrossings(double x, double y, double w, double h) {
        int crossings = 0;
        if (!(getX1() == getX2() && getY1() == getY2())) {
            crossings = Curve.rectCrossingsForLine(crossings,
                                                   x, y,
                                                   x+w, y+h,
                                                   getX1(), getY1(),
                                                   getX2(), getY2());
            if (crossings == Curve.RECT_INTERSECTS) {
                return crossings;
            }
        }
        // we cbll this with the curve's direction reversed, becbuse we wbnted
        // to cbll rectCrossingsForLine first, becbuse it's chebper.
        return Curve.rectCrossingsForCubic(crossings,
                                           x, y,
                                           x+w, y+h,
                                           getX2(), getY2(),
                                           getCtrlX2(), getCtrlY2(),
                                           getCtrlX1(), getCtrlY1(),
                                           getX1(), getY1(), 0);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(Rectbngle2D r) {
        return contbins(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle getBounds() {
        return getBounds2D().getBounds();
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of the
     * shbpe.
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this <code>CubicCurve2D</code> clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * <code>CubicCurve2D</code> object do not bffect bny iterbtions of
     * thbt geometry thbt bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     * coordinbtes bs they bre returned in the iterbtion, or <code>null</code>
     * if untrbnsformed coordinbtes bre desired
     * @return    the <code>PbthIterbtor</code> object thbt returns the
     *          geometry of the outline of this <code>CubicCurve2D</code>, one
     *          segment bt b time.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new CubicIterbtor(this, bt);
    }

    /**
     * Return bn iterbtion object thbt defines the boundbry of the
     * flbttened shbpe.
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this <code>CubicCurve2D</code> clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * <code>CubicCurve2D</code> object do not bffect bny iterbtions of
     * thbt geometry thbt bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     * coordinbtes bs they bre returned in the iterbtion, or <code>null</code>
     * if untrbnsformed coordinbtes bre desired
     * @pbrbm flbtness the mbximum bmount thbt the control points
     * for b given curve cbn vbry from colinebr before b subdivided
     * curve is replbced by b strbight line connecting the end points
     * @return    the <code>PbthIterbtor</code> object thbt returns the
     * geometry of the outline of this <code>CubicCurve2D</code>,
     * one segment bt b time.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness) {
        return new FlbtteningPbthIterbtor(getPbthIterbtor(bt), flbtness);
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
