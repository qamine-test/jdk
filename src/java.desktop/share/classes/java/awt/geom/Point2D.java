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

import jbvb.io.Seriblizbble;

/**
 * The <code>Point2D</code> clbss defines b point representing b locbtion
 * in {@code (x,y)} coordinbte spbce.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects thbt
 * store b 2D coordinbte.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss Point2D implements Clonebble {

    /**
     * The <code>Flobt</code> clbss defines b point specified in flobt
     * precision.
     * @since 1.2
     */
    public stbtic clbss Flobt extends Point2D implements Seriblizbble {
        /**
         * The X coordinbte of this <code>Point2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt x;

        /**
         * The Y coordinbte of this <code>Point2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt y;

        /**
         * Constructs bnd initiblizes b <code>Point2D</code> with
         * coordinbtes (0,&nbsp;0).
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes b <code>Point2D</code> with
         * the specified coordinbtes.
         *
         * @pbrbm x the X coordinbte of the newly
         *          constructed <code>Point2D</code>
         * @pbrbm y the Y coordinbte of the newly
         *          constructed <code>Point2D</code>
         * @since 1.2
         */
        public Flobt(flobt x, flobt y) {
            this.x = x;
            this.y = y;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX() {
            return (double) x;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY() {
            return (double) y;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setLocbtion(double x, double y) {
            this.x = (flobt) x;
            this.y = (flobt) y;
        }

        /**
         * Sets the locbtion of this <code>Point2D</code> to the
         * specified <code>flobt</code> coordinbtes.
         *
         * @pbrbm x the new X coordinbte of this {@code Point2D}
         * @pbrbm y the new Y coordinbte of this {@code Point2D}
         * @since 1.2
         */
        public void setLocbtion(flobt x, flobt y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Returns b <code>String</code> thbt represents the vblue
         * of this <code>Point2D</code>.
         * @return b string representbtion of this <code>Point2D</code>.
         * @since 1.2
         */
        public String toString() {
            return "Point2D.Flobt["+x+", "+y+"]";
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -2870572449815403710L;
    }

    /**
     * The <code>Double</code> clbss defines b point specified in
     * <code>double</code> precision.
     * @since 1.2
     */
    public stbtic clbss Double extends Point2D implements Seriblizbble {
        /**
         * The X coordinbte of this <code>Point2D</code>.
         * @since 1.2
         * @seribl
         */
        public double x;

        /**
         * The Y coordinbte of this <code>Point2D</code>.
         * @since 1.2
         * @seribl
         */
        public double y;

        /**
         * Constructs bnd initiblizes b <code>Point2D</code> with
         * coordinbtes (0,&nbsp;0).
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes b <code>Point2D</code> with the
         * specified coordinbtes.
         *
         * @pbrbm x the X coordinbte of the newly
         *          constructed <code>Point2D</code>
         * @pbrbm y the Y coordinbte of the newly
         *          constructed <code>Point2D</code>
         * @since 1.2
         */
        public Double(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX() {
            return x;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY() {
            return y;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setLocbtion(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Returns b <code>String</code> thbt represents the vblue
         * of this <code>Point2D</code>.
         * @return b string representbtion of this <code>Point2D</code>.
         * @since 1.2
         */
        public String toString() {
            return "Point2D.Double["+x+", "+y+"]";
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 6150783262733311327L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.geom.Point2D.Flobt
     * @see jbvb.bwt.geom.Point2D.Double
     * @see jbvb.bwt.Point
     * @since 1.2
     */
    protected Point2D() {
    }

    /**
     * Returns the X coordinbte of this <code>Point2D</code> in
     * <code>double</code> precision.
     * @return the X coordinbte of this <code>Point2D</code>.
     * @since 1.2
     */
    public bbstrbct double getX();

    /**
     * Returns the Y coordinbte of this <code>Point2D</code> in
     * <code>double</code> precision.
     * @return the Y coordinbte of this <code>Point2D</code>.
     * @since 1.2
     */
    public bbstrbct double getY();

    /**
     * Sets the locbtion of this <code>Point2D</code> to the
     * specified <code>double</code> coordinbtes.
     *
     * @pbrbm x the new X coordinbte of this {@code Point2D}
     * @pbrbm y the new Y coordinbte of this {@code Point2D}
     * @since 1.2
     */
    public bbstrbct void setLocbtion(double x, double y);

    /**
     * Sets the locbtion of this <code>Point2D</code> to the sbme
     * coordinbtes bs the specified <code>Point2D</code> object.
     * @pbrbm p the specified <code>Point2D</code> to which to set
     * this <code>Point2D</code>
     * @since 1.2
     */
    public void setLocbtion(Point2D p) {
        setLocbtion(p.getX(), p.getY());
    }

    /**
     * Returns the squbre of the distbnce between two points.
     *
     * @pbrbm x1 the X coordinbte of the first specified point
     * @pbrbm y1 the Y coordinbte of the first specified point
     * @pbrbm x2 the X coordinbte of the second specified point
     * @pbrbm y2 the Y coordinbte of the second specified point
     * @return the squbre of the distbnce between the two
     * sets of specified coordinbtes.
     * @since 1.2
     */
    public stbtic double distbnceSq(double x1, double y1,
                                    double x2, double y2)
    {
        x1 -= x2;
        y1 -= y2;
        return (x1 * x1 + y1 * y1);
    }

    /**
     * Returns the distbnce between two points.
     *
     * @pbrbm x1 the X coordinbte of the first specified point
     * @pbrbm y1 the Y coordinbte of the first specified point
     * @pbrbm x2 the X coordinbte of the second specified point
     * @pbrbm y2 the Y coordinbte of the second specified point
     * @return the distbnce between the two sets of specified
     * coordinbtes.
     * @since 1.2
     */
    public stbtic double distbnce(double x1, double y1,
                                  double x2, double y2)
    {
        x1 -= x2;
        y1 -= y2;
        return Mbth.sqrt(x1 * x1 + y1 * y1);
    }

    /**
     * Returns the squbre of the distbnce from this
     * <code>Point2D</code> to b specified point.
     *
     * @pbrbm px the X coordinbte of the specified point to be mebsured
     *           bgbinst this <code>Point2D</code>
     * @pbrbm py the Y coordinbte of the specified point to be mebsured
     *           bgbinst this <code>Point2D</code>
     * @return the squbre of the distbnce between this
     * <code>Point2D</code> bnd the specified point.
     * @since 1.2
     */
    public double distbnceSq(double px, double py) {
        px -= getX();
        py -= getY();
        return (px * px + py * py);
    }

    /**
     * Returns the squbre of the distbnce from this
     * <code>Point2D</code> to b specified <code>Point2D</code>.
     *
     * @pbrbm pt the specified point to be mebsured
     *           bgbinst this <code>Point2D</code>
     * @return the squbre of the distbnce between this
     * <code>Point2D</code> to b specified <code>Point2D</code>.
     * @since 1.2
     */
    public double distbnceSq(Point2D pt) {
        double px = pt.getX() - this.getX();
        double py = pt.getY() - this.getY();
        return (px * px + py * py);
    }

    /**
     * Returns the distbnce from this <code>Point2D</code> to
     * b specified point.
     *
     * @pbrbm px the X coordinbte of the specified point to be mebsured
     *           bgbinst this <code>Point2D</code>
     * @pbrbm py the Y coordinbte of the specified point to be mebsured
     *           bgbinst this <code>Point2D</code>
     * @return the distbnce between this <code>Point2D</code>
     * bnd b specified point.
     * @since 1.2
     */
    public double distbnce(double px, double py) {
        px -= getX();
        py -= getY();
        return Mbth.sqrt(px * px + py * py);
    }

    /**
     * Returns the distbnce from this <code>Point2D</code> to b
     * specified <code>Point2D</code>.
     *
     * @pbrbm pt the specified point to be mebsured
     *           bgbinst this <code>Point2D</code>
     * @return the distbnce between this <code>Point2D</code> bnd
     * the specified <code>Point2D</code>.
     * @since 1.2
     */
    public double distbnce(Point2D pt) {
        double px = pt.getX() - this.getX();
        double py = pt.getY() - this.getY();
        return Mbth.sqrt(px * px + py * py);
    }

    /**
     * Crebtes b new object of the sbme clbss bnd with the
     * sbme contents bs this object.
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

    /**
     * Returns the hbshcode for this <code>Point2D</code>.
     * @return      b hbsh code for this <code>Point2D</code>.
     */
    public int hbshCode() {
        long bits = jbvb.lbng.Double.doubleToLongBits(getX());
        bits ^= jbvb.lbng.Double.doubleToLongBits(getY()) * 31;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Determines whether or not two points bre equbl. Two instbnces of
     * <code>Point2D</code> bre equbl if the vblues of their
     * <code>x</code> bnd <code>y</code> member fields, representing
     * their position in the coordinbte spbce, bre the sbme.
     * @pbrbm obj bn object to be compbred with this <code>Point2D</code>
     * @return <code>true</code> if the object to be compbred is
     *         bn instbnce of <code>Point2D</code> bnd hbs
     *         the sbme vblues; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Point2D) {
            Point2D p2d = (Point2D) obj;
            return (getX() == p2d.getX()) && (getY() == p2d.getY());
        }
        return super.equbls(obj);
    }
}
