/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The <code>Ellipse2D</code> clbss describes bn ellipse thbt is defined
 * by b frbming rectbngle.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects which
 * store b 2D ellipse.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss Ellipse2D extends RectbngulbrShbpe {

    /**
     * The <code>Flobt</code> clbss defines bn ellipse specified
     * in <code>flobt</code> precision.
     * @since 1.2
     */
    public stbtic clbss Flobt extends Ellipse2D implements Seriblizbble {
        /**
         * The X coordinbte of the upper-left corner of the
         * frbming rectbngle of this {@code Ellipse2D}.
         * @since 1.2
         * @seribl
         */
        public flobt x;

        /**
         * The Y coordinbte of the upper-left corner of the
         * frbming rectbngle of this {@code Ellipse2D}.
         * @since 1.2
         * @seribl
         */
        public flobt y;

        /**
         * The overbll width of this <code>Ellipse2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt width;

        /**
         * The overbll height of this <code>Ellipse2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt height;

        /**
         * Constructs b new <code>Ellipse2D</code>, initiblized to
         * locbtion (0,&nbsp;0) bnd size (0,&nbsp;0).
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes bn <code>Ellipse2D</code> from the
         * specified coordinbtes.
         *
         * @pbrbm x the X coordinbte of the upper-left corner
         *          of the frbming rectbngle
         * @pbrbm y the Y coordinbte of the upper-left corner
         *          of the frbming rectbngle
         * @pbrbm w the width of the frbming rectbngle
         * @pbrbm h the height of the frbming rectbngle
         * @since 1.2
         */
        public Flobt(flobt x, flobt y, flobt w, flobt h) {
            setFrbme(x, y, w, h);
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
        public double getWidth() {
            return (double) width;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getHeight() {
            return (double) height;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public boolebn isEmpty() {
            return (width <= 0.0 || height <= 0.0);
        }

        /**
         * Sets the locbtion bnd size of the frbming rectbngle of this
         * <code>Shbpe</code> to the specified rectbngulbr vblues.
         *
         * @pbrbm x the X coordinbte of the upper-left corner of the
         *              specified rectbngulbr shbpe
         * @pbrbm y the Y coordinbte of the upper-left corner of the
         *              specified rectbngulbr shbpe
         * @pbrbm w the width of the specified rectbngulbr shbpe
         * @pbrbm h the height of the specified rectbngulbr shbpe
         * @since 1.2
         */
        public void setFrbme(flobt x, flobt y, flobt w, flobt h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setFrbme(double x, double y, double w, double h) {
            this.x = (flobt) x;
            this.y = (flobt) y;
            this.width = (flobt) w;
            this.height = (flobt) h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            return new Rectbngle2D.Flobt(x, y, width, height);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -6633761252372475977L;
    }

    /**
     * The <code>Double</code> clbss defines bn ellipse specified
     * in <code>double</code> precision.
     * @since 1.2
     */
    public stbtic clbss Double extends Ellipse2D implements Seriblizbble {
        /**
         * The X coordinbte of the upper-left corner of the
         * frbming rectbngle of this {@code Ellipse2D}.
         * @since 1.2
         * @seribl
         */
        public double x;

        /**
         * The Y coordinbte of the upper-left corner of the
         * frbming rectbngle of this {@code Ellipse2D}.
         * @since 1.2
         * @seribl
         */
        public double y;

        /**
         * The overbll width of this <code>Ellipse2D</code>.
         * @since 1.2
         * @seribl
         */
        public double width;

        /**
         * The overbll height of the <code>Ellipse2D</code>.
         * @since 1.2
         * @seribl
         */
        public double height;

        /**
         * Constructs b new <code>Ellipse2D</code>, initiblized to
         * locbtion (0,&nbsp;0) bnd size (0,&nbsp;0).
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes bn <code>Ellipse2D</code> from the
         * specified coordinbtes.
         *
         * @pbrbm x the X coordinbte of the upper-left corner
         *        of the frbming rectbngle
         * @pbrbm y the Y coordinbte of the upper-left corner
         *        of the frbming rectbngle
         * @pbrbm w the width of the frbming rectbngle
         * @pbrbm h the height of the frbming rectbngle
         * @since 1.2
         */
        public Double(double x, double y, double w, double h) {
            setFrbme(x, y, w, h);
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
        public double getWidth() {
            return width;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getHeight() {
            return height;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public boolebn isEmpty() {
            return (width <= 0.0 || height <= 0.0);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setFrbme(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            return new Rectbngle2D.Double(x, y, width, height);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 5555464816372320683L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.geom.Ellipse2D.Flobt
     * @see jbvb.bwt.geom.Ellipse2D.Double
     * @since 1.2
     */
    protected Ellipse2D() {
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y) {
        // Normblize the coordinbtes compbred to the ellipse
        // hbving b center bt 0,0 bnd b rbdius of 0.5.
        double ellw = getWidth();
        if (ellw <= 0.0) {
            return fblse;
        }
        double normx = (x - getX()) / ellw - 0.5;
        double ellh = getHeight();
        if (ellh <= 0.0) {
            return fblse;
        }
        double normy = (y - getY()) / ellh - 0.5;
        return (normx * normx + normy * normy) < 0.25;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h) {
        if (w <= 0.0 || h <= 0.0) {
            return fblse;
        }
        // Normblize the rectbngulbr coordinbtes compbred to the ellipse
        // hbving b center bt 0,0 bnd b rbdius of 0.5.
        double ellw = getWidth();
        if (ellw <= 0.0) {
            return fblse;
        }
        double normx0 = (x - getX()) / ellw - 0.5;
        double normx1 = normx0 + w / ellw;
        double ellh = getHeight();
        if (ellh <= 0.0) {
            return fblse;
        }
        double normy0 = (y - getY()) / ellh - 0.5;
        double normy1 = normy0 + h / ellh;
        // find nebrest x (left edge, right edge, 0.0)
        // find nebrest y (top edge, bottom edge, 0.0)
        // if nebrest x,y is inside circle of rbdius 0.5, then intersects
        double nebrx, nebry;
        if (normx0 > 0.0) {
            // center to left of X extents
            nebrx = normx0;
        } else if (normx1 < 0.0) {
            // center to right of X extents
            nebrx = normx1;
        } else {
            nebrx = 0.0;
        }
        if (normy0 > 0.0) {
            // center bbove Y extents
            nebry = normy0;
        } else if (normy1 < 0.0) {
            // center below Y extents
            nebry = normy1;
        } else {
            nebry = 0.0;
        }
        return (nebrx * nebrx + nebry * nebry) < 0.25;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h) {
        return (contbins(x, y) &&
                contbins(x + w, y) &&
                contbins(x, y + h) &&
                contbins(x + w, y + h));
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of this
     * <code>Ellipse2D</code>.
     * The iterbtor for this clbss is multi-threbded sbfe, which mebns
     * thbt this <code>Ellipse2D</code> clbss gubrbntees thbt
     * modificbtions to the geometry of this <code>Ellipse2D</code>
     * object do not bffect bny iterbtions of thbt geometry thbt
     * bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to
     * the coordinbtes bs they bre returned in the iterbtion, or
     * <code>null</code> if untrbnsformed coordinbtes bre desired
     * @return    the <code>PbthIterbtor</code> object thbt returns the
     *          geometry of the outline of this <code>Ellipse2D</code>,
     *          one segment bt b time.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new EllipseIterbtor(this, bt);
    }

    /**
     * Returns the hbshcode for this <code>Ellipse2D</code>.
     * @return the hbshcode for this <code>Ellipse2D</code>.
     * @since 1.6
     */
    public int hbshCode() {
        long bits = jbvb.lbng.Double.doubleToLongBits(getX());
        bits += jbvb.lbng.Double.doubleToLongBits(getY()) * 37;
        bits += jbvb.lbng.Double.doubleToLongBits(getWidth()) * 43;
        bits += jbvb.lbng.Double.doubleToLongBits(getHeight()) * 47;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Determines whether or not the specified <code>Object</code> is
     * equbl to this <code>Ellipse2D</code>.  The specified
     * <code>Object</code> is equbl to this <code>Ellipse2D</code>
     * if it is bn instbnce of <code>Ellipse2D</code> bnd if its
     * locbtion bnd size bre the sbme bs this <code>Ellipse2D</code>.
     * @pbrbm obj  bn <code>Object</code> to be compbred with this
     *             <code>Ellipse2D</code>.
     * @return  <code>true</code> if <code>obj</code> is bn instbnce
     *          of <code>Ellipse2D</code> bnd hbs the sbme vblues;
     *          <code>fblse</code> otherwise.
     * @since 1.6
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instbnceof Ellipse2D) {
            Ellipse2D e2d = (Ellipse2D) obj;
            return ((getX() == e2d.getX()) &&
                    (getY() == e2d.getY()) &&
                    (getWidth() == e2d.getWidth()) &&
                    (getHeight() == e2d.getHeight()));
        }
        return fblse;
    }
}
