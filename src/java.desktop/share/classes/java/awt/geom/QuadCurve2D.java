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
import sun.bwt.geom.Curve;

/**
 * The <code>QubdCurve2D</code> clbss defines b qubdrbtic pbrbmetric curve
 * segment in {@code (x,y)} coordinbte spbce.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects thbt
 * store b 2D qubdrbtic curve segment.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss QubdCurve2D implements Shbpe, Clonebble {

    /**
     * A qubdrbtic pbrbmetric curve segment specified with
     * {@code flobt} coordinbtes.
     *
     * @since 1.2
     */
    public stbtic clbss Flobt extends QubdCurve2D implements Seriblizbble {
        /**
         * The X coordinbte of the stbrt point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public flobt x1;

        /**
         * The Y coordinbte of the stbrt point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public flobt y1;

        /**
         * The X coordinbte of the control point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public flobt ctrlx;

        /**
         * The Y coordinbte of the control point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public flobt ctrly;

        /**
         * The X coordinbte of the end point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public flobt x2;

        /**
         * The Y coordinbte of the end point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public flobt y2;

        /**
         * Constructs bnd initiblizes b <code>QubdCurve2D</code> with
         * coordinbtes (0, 0, 0, 0, 0, 0).
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes b <code>QubdCurve2D</code> from the
         * specified {@code flobt} coordinbtes.
         *
         * @pbrbm x1 the X coordinbte of the stbrt point
         * @pbrbm y1 the Y coordinbte of the stbrt point
         * @pbrbm ctrlx the X coordinbte of the control point
         * @pbrbm ctrly the Y coordinbte of the control point
         * @pbrbm x2 the X coordinbte of the end point
         * @pbrbm y2 the Y coordinbte of the end point
         * @since 1.2
         */
        public Flobt(flobt x1, flobt y1,
                     flobt ctrlx, flobt ctrly,
                     flobt x2, flobt y2)
        {
            setCurve(x1, y1, ctrlx, ctrly, x2, y2);
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
        public double getCtrlX() {
            return (double) ctrlx;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlY() {
            return (double) ctrly;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getCtrlPt() {
            return new Point2D.Flobt(ctrlx, ctrly);
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
                             double ctrlx, double ctrly,
                             double x2, double y2)
        {
            this.x1    = (flobt) x1;
            this.y1    = (flobt) y1;
            this.ctrlx = (flobt) ctrlx;
            this.ctrly = (flobt) ctrly;
            this.x2    = (flobt) x2;
            this.y2    = (flobt) y2;
        }

        /**
         * Sets the locbtion of the end points bnd control point of this curve
         * to the specified {@code flobt} coordinbtes.
         *
         * @pbrbm x1 the X coordinbte of the stbrt point
         * @pbrbm y1 the Y coordinbte of the stbrt point
         * @pbrbm ctrlx the X coordinbte of the control point
         * @pbrbm ctrly the Y coordinbte of the control point
         * @pbrbm x2 the X coordinbte of the end point
         * @pbrbm y2 the Y coordinbte of the end point
         * @since 1.2
         */
        public void setCurve(flobt x1, flobt y1,
                             flobt ctrlx, flobt ctrly,
                             flobt x2, flobt y2)
        {
            this.x1    = x1;
            this.y1    = y1;
            this.ctrlx = ctrlx;
            this.ctrly = ctrly;
            this.x2    = x2;
            this.y2    = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            flobt left   = Mbth.min(Mbth.min(x1, x2), ctrlx);
            flobt top    = Mbth.min(Mbth.min(y1, y2), ctrly);
            flobt right  = Mbth.mbx(Mbth.mbx(x1, x2), ctrlx);
            flobt bottom = Mbth.mbx(Mbth.mbx(y1, y2), ctrly);
            return new Rectbngle2D.Flobt(left, top,
                                         right - left, bottom - top);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -8511188402130719609L;
    }

    /**
     * A qubdrbtic pbrbmetric curve segment specified with
     * {@code double} coordinbtes.
     *
     * @since 1.2
     */
    public stbtic clbss Double extends QubdCurve2D implements Seriblizbble {
        /**
         * The X coordinbte of the stbrt point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public double x1;

        /**
         * The Y coordinbte of the stbrt point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public double y1;

        /**
         * The X coordinbte of the control point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public double ctrlx;

        /**
         * The Y coordinbte of the control point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public double ctrly;

        /**
         * The X coordinbte of the end point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public double x2;

        /**
         * The Y coordinbte of the end point of the qubdrbtic curve
         * segment.
         * @since 1.2
         * @seribl
         */
        public double y2;

        /**
         * Constructs bnd initiblizes b <code>QubdCurve2D</code> with
         * coordinbtes (0, 0, 0, 0, 0, 0).
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes b <code>QubdCurve2D</code> from the
         * specified {@code double} coordinbtes.
         *
         * @pbrbm x1 the X coordinbte of the stbrt point
         * @pbrbm y1 the Y coordinbte of the stbrt point
         * @pbrbm ctrlx the X coordinbte of the control point
         * @pbrbm ctrly the Y coordinbte of the control point
         * @pbrbm x2 the X coordinbte of the end point
         * @pbrbm y2 the Y coordinbte of the end point
         * @since 1.2
         */
        public Double(double x1, double y1,
                      double ctrlx, double ctrly,
                      double x2, double y2)
        {
            setCurve(x1, y1, ctrlx, ctrly, x2, y2);
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
        public double getCtrlX() {
            return ctrlx;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getCtrlY() {
            return ctrly;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Point2D getCtrlPt() {
            return new Point2D.Double(ctrlx, ctrly);
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
                             double ctrlx, double ctrly,
                             double x2, double y2)
        {
            this.x1    = x1;
            this.y1    = y1;
            this.ctrlx = ctrlx;
            this.ctrly = ctrly;
            this.x2    = x2;
            this.y2    = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            double left   = Mbth.min(Mbth.min(x1, x2), ctrlx);
            double top    = Mbth.min(Mbth.min(y1, y2), ctrly);
            double right  = Mbth.mbx(Mbth.mbx(x1, x2), ctrlx);
            double bottom = Mbth.mbx(Mbth.mbx(y1, y2), ctrly);
            return new Rectbngle2D.Double(left, top,
                                          right - left, bottom - top);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 4217149928428559721L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.geom.QubdCurve2D.Flobt
     * @see jbvb.bwt.geom.QubdCurve2D.Double
     * @since 1.2
     */
    protected QubdCurve2D() {
    }

    /**
     * Returns the X coordinbte of the stbrt point in
     * <code>double</code> in precision.
     * @return the X coordinbte of the stbrt point.
     * @since 1.2
     */
    public bbstrbct double getX1();

    /**
     * Returns the Y coordinbte of the stbrt point in
     * <code>double</code> precision.
     * @return the Y coordinbte of the stbrt point.
     * @since 1.2
     */
    public bbstrbct double getY1();

    /**
     * Returns the stbrt point.
     * @return b <code>Point2D</code> thbt is the stbrt point of this
     *          <code>QubdCurve2D</code>.
     * @since 1.2
     */
    public bbstrbct Point2D getP1();

    /**
     * Returns the X coordinbte of the control point in
     * <code>double</code> precision.
     * @return X coordinbte the control point
     * @since 1.2
     */
    public bbstrbct double getCtrlX();

    /**
     * Returns the Y coordinbte of the control point in
     * <code>double</code> precision.
     * @return the Y coordinbte of the control point.
     * @since 1.2
     */
    public bbstrbct double getCtrlY();

    /**
     * Returns the control point.
     * @return b <code>Point2D</code> thbt is the control point of this
     *          <code>Point2D</code>.
     * @since 1.2
     */
    public bbstrbct Point2D getCtrlPt();

    /**
     * Returns the X coordinbte of the end point in
     * <code>double</code> precision.
     * @return the x coordinbte of the end point.
     * @since 1.2
     */
    public bbstrbct double getX2();

    /**
     * Returns the Y coordinbte of the end point in
     * <code>double</code> precision.
     * @return the Y coordinbte of the end point.
     * @since 1.2
     */
    public bbstrbct double getY2();

    /**
     * Returns the end point.
     * @return b <code>Point</code> object thbt is the end point
     *          of this <code>Point2D</code>.
     * @since 1.2
     */
    public bbstrbct Point2D getP2();

    /**
     * Sets the locbtion of the end points bnd control point of this curve
     * to the specified <code>double</code> coordinbtes.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point
     * @pbrbm y1 the Y coordinbte of the stbrt point
     * @pbrbm ctrlx the X coordinbte of the control point
     * @pbrbm ctrly the Y coordinbte of the control point
     * @pbrbm x2 the X coordinbte of the end point
     * @pbrbm y2 the Y coordinbte of the end point
     * @since 1.2
     */
    public bbstrbct void setCurve(double x1, double y1,
                                  double ctrlx, double ctrly,
                                  double x2, double y2);

    /**
     * Sets the locbtion of the end points bnd control points of this
     * <code>QubdCurve2D</code> to the <code>double</code> coordinbtes bt
     * the specified offset in the specified brrby.
     * @pbrbm coords the brrby contbining coordinbte vblues
     * @pbrbm offset the index into the brrby from which to stbrt
     *          getting the coordinbte vblues bnd bssigning them to this
     *          <code>QubdCurve2D</code>
     * @since 1.2
     */
    public void setCurve(double[] coords, int offset) {
        setCurve(coords[offset + 0], coords[offset + 1],
                 coords[offset + 2], coords[offset + 3],
                 coords[offset + 4], coords[offset + 5]);
    }

    /**
     * Sets the locbtion of the end points bnd control point of this
     * <code>QubdCurve2D</code> to the specified <code>Point2D</code>
     * coordinbtes.
     * @pbrbm p1 the stbrt point
     * @pbrbm cp the control point
     * @pbrbm p2 the end point
     * @since 1.2
     */
    public void setCurve(Point2D p1, Point2D cp, Point2D p2) {
        setCurve(p1.getX(), p1.getY(),
                 cp.getX(), cp.getY(),
                 p2.getX(), p2.getY());
    }

    /**
     * Sets the locbtion of the end points bnd control points of this
     * <code>QubdCurve2D</code> to the coordinbtes of the
     * <code>Point2D</code> objects bt the specified offset in
     * the specified brrby.
     * @pbrbm pts bn brrby contbining <code>Point2D</code> thbt define
     *          coordinbte vblues
     * @pbrbm offset the index into <code>pts</code> from which to stbrt
     *          getting the coordinbte vblues bnd bssigning them to this
     *          <code>QubdCurve2D</code>
     * @since 1.2
     */
    public void setCurve(Point2D[] pts, int offset) {
        setCurve(pts[offset + 0].getX(), pts[offset + 0].getY(),
                 pts[offset + 1].getX(), pts[offset + 1].getY(),
                 pts[offset + 2].getX(), pts[offset + 2].getY());
    }

    /**
     * Sets the locbtion of the end points bnd control point of this
     * <code>QubdCurve2D</code> to the sbme bs those in the specified
     * <code>QubdCurve2D</code>.
     * @pbrbm c the specified <code>QubdCurve2D</code>
     * @since 1.2
     */
    public void setCurve(QubdCurve2D c) {
        setCurve(c.getX1(), c.getY1(),
                 c.getCtrlX(), c.getCtrlY(),
                 c.getX2(), c.getY2());
    }

    /**
     * Returns the squbre of the flbtness, or mbximum distbnce of b
     * control point from the line connecting the end points, of the
     * qubdrbtic curve specified by the indicbted control points.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point
     * @pbrbm y1 the Y coordinbte of the stbrt point
     * @pbrbm ctrlx the X coordinbte of the control point
     * @pbrbm ctrly the Y coordinbte of the control point
     * @pbrbm x2 the X coordinbte of the end point
     * @pbrbm y2 the Y coordinbte of the end point
     * @return the squbre of the flbtness of the qubdrbtic curve
     *          defined by the specified coordinbtes.
     * @since 1.2
     */
    public stbtic double getFlbtnessSq(double x1, double y1,
                                       double ctrlx, double ctrly,
                                       double x2, double y2) {
        return Line2D.ptSegDistSq(x1, y1, x2, y2, ctrlx, ctrly);
    }

    /**
     * Returns the flbtness, or mbximum distbnce of b
     * control point from the line connecting the end points, of the
     * qubdrbtic curve specified by the indicbted control points.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point
     * @pbrbm y1 the Y coordinbte of the stbrt point
     * @pbrbm ctrlx the X coordinbte of the control point
     * @pbrbm ctrly the Y coordinbte of the control point
     * @pbrbm x2 the X coordinbte of the end point
     * @pbrbm y2 the Y coordinbte of the end point
     * @return the flbtness of the qubdrbtic curve defined by the
     *          specified coordinbtes.
     * @since 1.2
     */
    public stbtic double getFlbtness(double x1, double y1,
                                     double ctrlx, double ctrly,
                                     double x2, double y2) {
        return Line2D.ptSegDist(x1, y1, x2, y2, ctrlx, ctrly);
    }

    /**
     * Returns the squbre of the flbtness, or mbximum distbnce of b
     * control point from the line connecting the end points, of the
     * qubdrbtic curve specified by the control points stored in the
     * indicbted brrby bt the indicbted index.
     * @pbrbm coords bn brrby contbining coordinbte vblues
     * @pbrbm offset the index into <code>coords</code> from which to
     *          to stbrt getting the vblues from the brrby
     * @return the flbtness of the qubdrbtic curve thbt is defined by the
     *          vblues in the specified brrby bt the specified index.
     * @since 1.2
     */
    public stbtic double getFlbtnessSq(double coords[], int offset) {
        return Line2D.ptSegDistSq(coords[offset + 0], coords[offset + 1],
                                  coords[offset + 4], coords[offset + 5],
                                  coords[offset + 2], coords[offset + 3]);
    }

    /**
     * Returns the flbtness, or mbximum distbnce of b
     * control point from the line connecting the end points, of the
     * qubdrbtic curve specified by the control points stored in the
     * indicbted brrby bt the indicbted index.
     * @pbrbm coords bn brrby contbining coordinbte vblues
     * @pbrbm offset the index into <code>coords</code> from which to
     *          stbrt getting the coordinbte vblues
     * @return the flbtness of b qubdrbtic curve defined by the
     *          specified brrby bt the specified offset.
     * @since 1.2
     */
    public stbtic double getFlbtness(double coords[], int offset) {
        return Line2D.ptSegDist(coords[offset + 0], coords[offset + 1],
                                coords[offset + 4], coords[offset + 5],
                                coords[offset + 2], coords[offset + 3]);
    }

    /**
     * Returns the squbre of the flbtness, or mbximum distbnce of b
     * control point from the line connecting the end points, of this
     * <code>QubdCurve2D</code>.
     * @return the squbre of the flbtness of this
     *          <code>QubdCurve2D</code>.
     * @since 1.2
     */
    public double getFlbtnessSq() {
        return Line2D.ptSegDistSq(getX1(), getY1(),
                                  getX2(), getY2(),
                                  getCtrlX(), getCtrlY());
    }

    /**
     * Returns the flbtness, or mbximum distbnce of b
     * control point from the line connecting the end points, of this
     * <code>QubdCurve2D</code>.
     * @return the flbtness of this <code>QubdCurve2D</code>.
     * @since 1.2
     */
    public double getFlbtness() {
        return Line2D.ptSegDist(getX1(), getY1(),
                                getX2(), getY2(),
                                getCtrlX(), getCtrlY());
    }

    /**
     * Subdivides this <code>QubdCurve2D</code> bnd stores the resulting
     * two subdivided curves into the <code>left</code> bnd
     * <code>right</code> curve pbrbmeters.
     * Either or both of the <code>left</code> bnd <code>right</code>
     * objects cbn be the sbme bs this <code>QubdCurve2D</code> or
     * <code>null</code>.
     * @pbrbm left the <code>QubdCurve2D</code> object for storing the
     * left or first hblf of the subdivided curve
     * @pbrbm right the <code>QubdCurve2D</code> object for storing the
     * right or second hblf of the subdivided curve
     * @since 1.2
     */
    public void subdivide(QubdCurve2D left, QubdCurve2D right) {
        subdivide(this, left, right);
    }

    /**
     * Subdivides the qubdrbtic curve specified by the <code>src</code>
     * pbrbmeter bnd stores the resulting two subdivided curves into the
     * <code>left</code> bnd <code>right</code> curve pbrbmeters.
     * Either or both of the <code>left</code> bnd <code>right</code>
     * objects cbn be the sbme bs the <code>src</code> object or
     * <code>null</code>.
     * @pbrbm src the qubdrbtic curve to be subdivided
     * @pbrbm left the <code>QubdCurve2D</code> object for storing the
     *          left or first hblf of the subdivided curve
     * @pbrbm right the <code>QubdCurve2D</code> object for storing the
     *          right or second hblf of the subdivided curve
     * @since 1.2
     */
    public stbtic void subdivide(QubdCurve2D src,
                                 QubdCurve2D left,
                                 QubdCurve2D right) {
        double x1 = src.getX1();
        double y1 = src.getY1();
        double ctrlx = src.getCtrlX();
        double ctrly = src.getCtrlY();
        double x2 = src.getX2();
        double y2 = src.getY2();
        double ctrlx1 = (x1 + ctrlx) / 2.0;
        double ctrly1 = (y1 + ctrly) / 2.0;
        double ctrlx2 = (x2 + ctrlx) / 2.0;
        double ctrly2 = (y2 + ctrly) / 2.0;
        ctrlx = (ctrlx1 + ctrlx2) / 2.0;
        ctrly = (ctrly1 + ctrly2) / 2.0;
        if (left != null) {
            left.setCurve(x1, y1, ctrlx1, ctrly1, ctrlx, ctrly);
        }
        if (right != null) {
            right.setCurve(ctrlx, ctrly, ctrlx2, ctrly2, x2, y2);
        }
    }

    /**
     * Subdivides the qubdrbtic curve specified by the coordinbtes
     * stored in the <code>src</code> brrby bt indices
     * <code>srcoff</code> through <code>srcoff</code>&nbsp;+&nbsp;5
     * bnd stores the resulting two subdivided curves into the two
     * result brrbys bt the corresponding indices.
     * Either or both of the <code>left</code> bnd <code>right</code>
     * brrbys cbn be <code>null</code> or b reference to the sbme brrby
     * bnd offset bs the <code>src</code> brrby.
     * Note thbt the lbst point in the first subdivided curve is the
     * sbme bs the first point in the second subdivided curve.  Thus,
     * it is possible to pbss the sbme brrby for <code>left</code> bnd
     * <code>right</code> bnd to use offsets such thbt
     * <code>rightoff</code> equbls <code>leftoff</code> + 4 in order
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
        double ctrlx = src[srcoff + 2];
        double ctrly = src[srcoff + 3];
        double x2 = src[srcoff + 4];
        double y2 = src[srcoff + 5];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
        x1 = (x1 + ctrlx) / 2.0;
        y1 = (y1 + ctrly) / 2.0;
        x2 = (x2 + ctrlx) / 2.0;
        y2 = (y2 + ctrly) / 2.0;
        ctrlx = (x1 + x2) / 2.0;
        ctrly = (y1 + y2) / 2.0;
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx;
            left[leftoff + 5] = ctrly;
        }
        if (right != null) {
            right[rightoff + 0] = ctrlx;
            right[rightoff + 1] = ctrly;
            right[rightoff + 2] = x2;
            right[rightoff + 3] = y2;
        }
    }

    /**
     * Solves the qubdrbtic whose coefficients bre in the <code>eqn</code>
     * brrby bnd plbces the non-complex roots bbck into the sbme brrby,
     * returning the number of roots.  The qubdrbtic solved is represented
     * by the equbtion:
     * <pre>
     *     eqn = {C, B, A};
     *     bx^2 + bx + c = 0
     * </pre>
     * A return vblue of <code>-1</code> is used to distinguish b constbnt
     * equbtion, which might be blwbys 0 or never 0, from bn equbtion thbt
     * hbs no zeroes.
     * @pbrbm eqn the brrby thbt contbins the qubdrbtic coefficients
     * @return the number of roots, or <code>-1</code> if the equbtion is
     *          b constbnt
     * @since 1.2
     */
    public stbtic int solveQubdrbtic(double eqn[]) {
        return solveQubdrbtic(eqn, eqn);
    }

    /**
     * Solves the qubdrbtic whose coefficients bre in the <code>eqn</code>
     * brrby bnd plbces the non-complex roots into the <code>res</code>
     * brrby, returning the number of roots.
     * The qubdrbtic solved is represented by the equbtion:
     * <pre>
     *     eqn = {C, B, A};
     *     bx^2 + bx + c = 0
     * </pre>
     * A return vblue of <code>-1</code> is used to distinguish b constbnt
     * equbtion, which might be blwbys 0 or never 0, from bn equbtion thbt
     * hbs no zeroes.
     * @pbrbm eqn the specified brrby of coefficients to use to solve
     *        the qubdrbtic equbtion
     * @pbrbm res the brrby thbt contbins the non-complex roots
     *        resulting from the solution of the qubdrbtic equbtion
     * @return the number of roots, or <code>-1</code> if the equbtion is
     *  b constbnt.
     * @since 1.3
     */
    public stbtic int solveQubdrbtic(double eqn[], double res[]) {
        double b = eqn[2];
        double b = eqn[1];
        double c = eqn[0];
        int roots = 0;
        if (b == 0.0) {
            // The qubdrbtic pbrbbolb hbs degenerbted to b line.
            if (b == 0.0) {
                // The line hbs degenerbted to b constbnt.
                return -1;
            }
            res[roots++] = -c / b;
        } else {
            // From Numericbl Recipes, 5.6, Qubdrbtic bnd Cubic Equbtions
            double d = b * b - 4.0 * b * c;
            if (d < 0.0) {
                // If d < 0.0, then there bre no roots
                return 0;
            }
            d = Mbth.sqrt(d);
            // For bccurbcy, cblculbte one root using:
            //     (-b +/- d) / 2b
            // bnd the other using:
            //     2c / (-b +/- d)
            // Choose the sign of the +/- so thbt b+d gets lbrger in mbgnitude
            if (b < 0.0) {
                d = -d;
            }
            double q = (b + d) / -2.0;
            // We blrebdy tested b for being 0 bbove
            res[roots++] = q / b;
            if (q != 0.0) {
                res[roots++] = c / q;
            }
        }
        return roots;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y) {

        double x1 = getX1();
        double y1 = getY1();
        double xc = getCtrlX();
        double yc = getCtrlY();
        double x2 = getX2();
        double y2 = getY2();

        /*
         * We hbve b convex shbpe bounded by qubd curve Pc(t)
         * bnd ine Pl(t).
         *
         *     P1 = (x1, y1) - stbrt point of curve
         *     P2 = (x2, y2) - end point of curve
         *     Pc = (xc, yc) - control point
         *
         *     Pq(t) = P1*(1 - t)^2 + 2*Pc*t*(1 - t) + P2*t^2 =
         *           = (P1 - 2*Pc + P2)*t^2 + 2*(Pc - P1)*t + P1
         *     Pl(t) = P1*(1 - t) + P2*t
         *     t = [0:1]
         *
         *     P = (x, y) - point of interest
         *
         * Let's look bt second derivbtive of qubd curve equbtion:
         *
         *     Pq''(t) = 2 * (P1 - 2 * Pc + P2) = Pq''
         *     It's constbnt vector.
         *
         * Let's drbw b line through P to be pbrbllel to this
         * vector bnd find the intersection of the qubd curve
         * bnd the line.
         *
         * Pq(t) is point of intersection if system of equbtions
         * below hbs the solution.
         *
         *     L(s) = P + Pq''*s == Pq(t)
         *     Pq''*s + (P - Pq(t)) == 0
         *
         *     | xq''*s + (x - xq(t)) == 0
         *     | yq''*s + (y - yq(t)) == 0
         *
         * This system hbs the solution if rbnk of its mbtrix equbls to 1.
         * Thbt is, determinbnt of the mbtrix should be zero.
         *
         *     (y - yq(t))*xq'' == (x - xq(t))*yq''
         *
         * Let's solve this equbtion with 't' vbribble.
         * Also let kx = x1 - 2*xc + x2
         *          ky = y1 - 2*yc + y2
         *
         *     t0q = (1/2)*((x - x1)*ky - (y - y1)*kx) /
         *                 ((xc - x1)*ky - (yc - y1)*kx)
         *
         * Let's do the sbme for our line Pl(t):
         *
         *     t0l = ((x - x1)*ky - (y - y1)*kx) /
         *           ((x2 - x1)*ky - (y2 - y1)*kx)
         *
         * It's ebsy to check thbt t0q == t0l. This fbct mebns
         * we cbn compute t0 only one time.
         *
         * In cbse t0 < 0 or t0 > 1, we hbve bn intersections outside
         * of shbpe bounds. So, P is definitely out of shbpe.
         *
         * In cbse t0 is inside [0:1], we should cblculbte Pq(t0)
         * bnd Pl(t0). We hbve three points for now, bnd bll of them
         * lie on one line. So, we just need to detect, is our point
         * of interest between points of intersections or not.
         *
         * If the denominbtor in the t0q bnd t0l equbtions is
         * zero, then the points must be collinebr bnd so the
         * curve is degenerbte bnd encloses no breb.  Thus the
         * result is fblse.
         */
        double kx = x1 - 2 * xc + x2;
        double ky = y1 - 2 * yc + y2;
        double dx = x - x1;
        double dy = y - y1;
        double dxl = x2 - x1;
        double dyl = y2 - y1;

        double t0 = (dx * ky - dy * kx) / (dxl * ky - dyl * kx);
        if (t0 < 0 || t0 > 1 || t0 != t0) {
            return fblse;
        }

        double xb = kx * t0 * t0 + 2 * (xc - x1) * t0 + x1;
        double yb = ky * t0 * t0 + 2 * (yc - y1) * t0 + y1;
        double xl = dxl * t0 + x1;
        double yl = dyl * t0 + y1;

        return (x >= xb && x < xl) ||
               (x >= xl && x < xb) ||
               (y >= yb && y < yl) ||
               (y >= yl && y < yb);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(Point2D p) {
        return contbins(p.getX(), p.getY());
    }

    /**
     * Fill bn brrby with the coefficients of the pbrbmetric equbtion
     * in t, rebdy for solving bgbinst vbl with solveQubdrbtic.
     * We currently hbve:
     *     vbl = Py(t) = C1*(1-t)^2 + 2*CP*t*(1-t) + C2*t^2
     *                 = C1 - 2*C1*t + C1*t^2 + 2*CP*t - 2*CP*t^2 + C2*t^2
     *                 = C1 + (2*CP - 2*C1)*t + (C1 - 2*CP + C2)*t^2
     *               0 = (C1 - vbl) + (2*CP - 2*C1)*t + (C1 - 2*CP + C2)*t^2
     *               0 = C + Bt + At^2
     *     C = C1 - vbl
     *     B = 2*CP - 2*C1
     *     A = C1 - 2*CP + C2
     */
    privbte stbtic void fillEqn(double eqn[], double vbl,
                                double c1, double cp, double c2) {
        eqn[0] = c1 - vbl;
        eqn[1] = cp + cp - c1 - c1;
        eqn[2] = c1 - cp - cp + c2;
        return;
    }

    /**
     * Evblubte the t vblues in the first num slots of the vbls[] brrby
     * bnd plbce the evblubted vblues bbck into the sbme brrby.  Only
     * evblubte t vblues thbt bre within the rbnge &lt;0, 1&gt;, including
     * the 0 bnd 1 ends of the rbnge iff the include0 or include1
     * boolebns bre true.  If bn "inflection" equbtion is hbnded in,
     * then bny points which represent b point of inflection for thbt
     * qubdrbtic equbtion bre blso ignored.
     */
    privbte stbtic int evblQubdrbtic(double vbls[], int num,
                                     boolebn include0,
                                     boolebn include1,
                                     double inflect[],
                                     double c1, double ctrl, double c2) {
        int j = 0;
        for (int i = 0; i < num; i++) {
            double t = vbls[i];
            if ((include0 ? t >= 0 : t > 0) &&
                (include1 ? t <= 1 : t < 1) &&
                (inflect == null ||
                 inflect[1] + 2*inflect[2]*t != 0))
            {
                double u = 1 - t;
                vbls[j++] = c1*u*u + 2*ctrl*t*u + c2*t*t;
            }
        }
        return j;
    }

    privbte stbtic finbl int BELOW = -2;
    privbte stbtic finbl int LOWEDGE = -1;
    privbte stbtic finbl int INSIDE = 0;
    privbte stbtic finbl int HIGHEDGE = 1;
    privbte stbtic finbl int ABOVE = 2;

    /**
     * Determine where coord lies with respect to the rbnge from
     * low to high.  It is bssumed thbt low &lt;= high.  The return
     * vblue is one of the 5 vblues BELOW, LOWEDGE, INSIDE, HIGHEDGE,
     * or ABOVE.
     */
    privbte stbtic int getTbg(double coord, double low, double high) {
        if (coord <= low) {
            return (coord < low ? BELOW : LOWEDGE);
        }
        if (coord >= high) {
            return (coord > high ? ABOVE : HIGHEDGE);
        }
        return INSIDE;
    }

    /**
     * Determine if the pttbg represents b coordinbte thbt is blrebdy
     * in its test rbnge, or is on the border with either of the two
     * opttbgs representing bnother coordinbte thbt is "towbrds the
     * inside" of thbt test rbnge.  In other words, bre either of the
     * two "opt" points "drbwing the pt inwbrd"?
     */
    privbte stbtic boolebn inwbrds(int pttbg, int opt1tbg, int opt2tbg) {
        switch (pttbg) {
        cbse BELOW:
        cbse ABOVE:
        defbult:
            return fblse;
        cbse LOWEDGE:
            return (opt1tbg >= INSIDE || opt2tbg >= INSIDE);
        cbse INSIDE:
            return true;
        cbse HIGHEDGE:
            return (opt1tbg <= INSIDE || opt2tbg <= INSIDE);
        }
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

        // Triviblly bccept if either endpoint is inside the rectbngle
        // (not on its border since it mby end there bnd not go inside)
        // Record where they lie with respect to the rectbngle.
        //     -1 => left, 0 => inside, 1 => right
        double x1 = getX1();
        double y1 = getY1();
        int x1tbg = getTbg(x1, x, x+w);
        int y1tbg = getTbg(y1, y, y+h);
        if (x1tbg == INSIDE && y1tbg == INSIDE) {
            return true;
        }
        double x2 = getX2();
        double y2 = getY2();
        int x2tbg = getTbg(x2, x, x+w);
        int y2tbg = getTbg(y2, y, y+h);
        if (x2tbg == INSIDE && y2tbg == INSIDE) {
            return true;
        }
        double ctrlx = getCtrlX();
        double ctrly = getCtrlY();
        int ctrlxtbg = getTbg(ctrlx, x, x+w);
        int ctrlytbg = getTbg(ctrly, y, y+h);

        // Triviblly reject if bll points bre entirely to one side of
        // the rectbngle.
        if (x1tbg < INSIDE && x2tbg < INSIDE && ctrlxtbg < INSIDE) {
            return fblse;       // All points left
        }
        if (y1tbg < INSIDE && y2tbg < INSIDE && ctrlytbg < INSIDE) {
            return fblse;       // All points bbove
        }
        if (x1tbg > INSIDE && x2tbg > INSIDE && ctrlxtbg > INSIDE) {
            return fblse;       // All points right
        }
        if (y1tbg > INSIDE && y2tbg > INSIDE && ctrlytbg > INSIDE) {
            return fblse;       // All points below
        }

        // Test for endpoints on the edge where either the segment
        // or the curve is hebded "inwbrds" from them
        // Note: These tests bre b superset of the fbst endpoint tests
        //       bbove bnd thus repebt those tests, but tbke more time
        //       bnd cover more cbses
        if (inwbrds(x1tbg, x2tbg, ctrlxtbg) &&
            inwbrds(y1tbg, y2tbg, ctrlytbg))
        {
            // First endpoint on border with either edge moving inside
            return true;
        }
        if (inwbrds(x2tbg, x1tbg, ctrlxtbg) &&
            inwbrds(y2tbg, y1tbg, ctrlytbg))
        {
            // Second endpoint on border with either edge moving inside
            return true;
        }

        // Triviblly bccept if endpoints spbn directly bcross the rectbngle
        boolebn xoverlbp = (x1tbg * x2tbg <= 0);
        boolebn yoverlbp = (y1tbg * y2tbg <= 0);
        if (x1tbg == INSIDE && x2tbg == INSIDE && yoverlbp) {
            return true;
        }
        if (y1tbg == INSIDE && y2tbg == INSIDE && xoverlbp) {
            return true;
        }

        // We now know thbt both endpoints bre outside the rectbngle
        // but the 3 points bre not bll on one side of the rectbngle.
        // Therefore the curve cbnnot be contbined inside the rectbngle,
        // but the rectbngle might be contbined inside the curve, or
        // the curve might intersect the boundbry of the rectbngle.

        double[] eqn = new double[3];
        double[] res = new double[3];
        if (!yoverlbp) {
            // Both Y coordinbtes for the closing segment bre bbove or
            // below the rectbngle which mebns thbt we cbn only intersect
            // if the curve crosses the top (or bottom) of the rectbngle
            // in more thbn one plbce bnd if those crossing locbtions
            // spbn the horizontbl rbnge of the rectbngle.
            fillEqn(eqn, (y1tbg < INSIDE ? y : y+h), y1, ctrly, y2);
            return (solveQubdrbtic(eqn, res) == 2 &&
                    evblQubdrbtic(res, 2, true, true, null,
                                  x1, ctrlx, x2) == 2 &&
                    getTbg(res[0], x, x+w) * getTbg(res[1], x, x+w) <= 0);
        }

        // Y rbnges overlbp.  Now we exbmine the X rbnges
        if (!xoverlbp) {
            // Both X coordinbtes for the closing segment bre left of
            // or right of the rectbngle which mebns thbt we cbn only
            // intersect if the curve crosses the left (or right) edge
            // of the rectbngle in more thbn one plbce bnd if those
            // crossing locbtions spbn the verticbl rbnge of the rectbngle.
            fillEqn(eqn, (x1tbg < INSIDE ? x : x+w), x1, ctrlx, x2);
            return (solveQubdrbtic(eqn, res) == 2 &&
                    evblQubdrbtic(res, 2, true, true, null,
                                  y1, ctrly, y2) == 2 &&
                    getTbg(res[0], y, y+h) * getTbg(res[1], y, y+h) <= 0);
        }

        // The X bnd Y rbnges of the endpoints overlbp the X bnd Y
        // rbnges of the rectbngle, now find out how the endpoint
        // line segment intersects the Y rbnge of the rectbngle
        double dx = x2 - x1;
        double dy = y2 - y1;
        double k = y2 * x1 - x2 * y1;
        int c1tbg, c2tbg;
        if (y1tbg == INSIDE) {
            c1tbg = x1tbg;
        } else {
            c1tbg = getTbg((k + dx * (y1tbg < INSIDE ? y : y+h)) / dy, x, x+w);
        }
        if (y2tbg == INSIDE) {
            c2tbg = x2tbg;
        } else {
            c2tbg = getTbg((k + dx * (y2tbg < INSIDE ? y : y+h)) / dy, x, x+w);
        }
        // If the pbrt of the line segment thbt intersects the Y rbnge
        // of the rectbngle crosses it horizontblly - triviblly bccept
        if (c1tbg * c2tbg <= 0) {
            return true;
        }

        // Now we know thbt both the X bnd Y rbnges intersect bnd thbt
        // the endpoint line segment does not directly cross the rectbngle.
        //
        // We cbn blmost trebt this cbse like one of the cbses bbove
        // where both endpoints bre to one side, except thbt we will
        // only get one intersection of the curve with the verticbl
        // side of the rectbngle.  This is becbuse the endpoint segment
        // bccounts for the other intersection.
        //
        // (Remember there is overlbp in both the X bnd Y rbnges which
        //  mebns thbt the segment must cross bt lebst one verticbl edge
        //  of the rectbngle - in pbrticulbr, the "nebr verticbl side" -
        //  lebving only one intersection for the curve.)
        //
        // Now we cblculbte the y tbgs of the two intersections on the
        // "nebr verticbl side" of the rectbngle.  We will hbve one with
        // the endpoint segment, bnd one with the curve.  If those two
        // verticbl intersections overlbp the Y rbnge of the rectbngle,
        // we hbve bn intersection.  Otherwise, we don't.

        // c1tbg = verticbl intersection clbss of the endpoint segment
        //
        // Choose the y tbg of the endpoint thbt wbs not on the sbme
        // side of the rectbngle bs the subsegment cblculbted bbove.
        // Note thbt we cbn "stebl" the existing Y tbg of thbt endpoint
        // since it will be provbbly the sbme bs the verticbl intersection.
        c1tbg = ((c1tbg * x1tbg <= 0) ? y1tbg : y2tbg);

        // c2tbg = verticbl intersection clbss of the curve
        //
        // We hbve to cblculbte this one the strbightforwbrd wby.
        // Note thbt the c2tbg cbn still tell us which verticbl edge
        // to test bgbinst.
        fillEqn(eqn, (c2tbg < INSIDE ? x : x+w), x1, ctrlx, x2);
        int num = solveQubdrbtic(eqn, res);

        // Note: We should be bble to bssert(num == 2); since the
        // X rbnge "crosses" (not touches) the verticbl boundbry,
        // but we pbss num to evblQubdrbtic for completeness.
        evblQubdrbtic(res, num, true, true, null, y1, ctrly, y2);

        // Note: We cbn bssert(num evbls == 1); since one of the
        // 2 crossings will be out of the [0,1] rbnge.
        c2tbg = getTbg(res[0], y, y+h);

        // Finblly, we hbve bn intersection if the two crossings
        // overlbp the Y rbnge of the rectbngle.
        return (c1tbg * c2tbg <= 0);
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
        // Assertion: Qubdrbtic curves closed by connecting their
        // endpoints bre blwbys convex.
        return (contbins(x, y) &&
                contbins(x + w, y) &&
                contbins(x + w, y + h) &&
                contbins(x, y + h));
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
     * shbpe of this <code>QubdCurve2D</code>.
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this <code>QubdCurve2D</code> clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * <code>QubdCurve2D</code> object do not bffect bny iterbtions of
     * thbt geometry thbt bre blrebdy in process.
     * @pbrbm bt bn optionbl {@link AffineTrbnsform} to bpply to the
     *          shbpe boundbry
     * @return b {@link PbthIterbtor} object thbt defines the boundbry
     *          of the shbpe.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new QubdIterbtor(this, bt);
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of the
     * flbttened shbpe of this <code>QubdCurve2D</code>.
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this <code>QubdCurve2D</code> clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * <code>QubdCurve2D</code> object do not bffect bny iterbtions of
     * thbt geometry thbt bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to bpply
     *          to the boundbry of the shbpe
     * @pbrbm flbtness the mbximum distbnce thbt the control points for b
     *          subdivided curve cbn be with respect to b line connecting
     *          the end points of this curve before this curve is
     *          replbced by b strbight line connecting the end points.
     * @return b <code>PbthIterbtor</code> object thbt defines the
     *          flbttened boundbry of the shbpe.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness) {
        return new FlbtteningPbthIterbtor(getPbthIterbtor(bt), flbtness);
    }

    /**
     * Crebtes b new object of the sbme clbss bnd with the sbme contents
     * bs this object.
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
