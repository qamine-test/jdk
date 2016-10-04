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
 * The <code>RoundRectbngle2D</code> clbss defines b rectbngle with
 * rounded corners defined by b locbtion {@code (x,y)}, b
 * dimension {@code (w x h)}, bnd the width bnd height of bn brc
 * with which to round the corners.
 * <p>
 * This clbss is the bbstrbct superclbss for bll objects thbt
 * store b 2D rounded rectbngle.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss RoundRectbngle2D extends RectbngulbrShbpe {

    /**
     * The <code>Flobt</code> clbss defines b rectbngle with rounded
     * corners bll specified in <code>flobt</code> coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Flobt extends RoundRectbngle2D
        implements Seriblizbble
    {
        /**
         * The X coordinbte of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt x;

        /**
         * The Y coordinbte of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt y;

        /**
         * The width of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt width;

        /**
         * The height of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt height;

        /**
         * The width of the brc thbt rounds off the corners.
         * @since 1.2
         * @seribl
         */
        public flobt brcwidth;

        /**
         * The height of the brc thbt rounds off the corners.
         * @since 1.2
         * @seribl
         */
        public flobt brcheight;

        /**
         * Constructs b new <code>RoundRectbngle2D</code>, initiblized to
         * locbtion (0.0,&nbsp;0.0), size (0.0,&nbsp;0.0), bnd corner brcs
         * of rbdius 0.0.
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes b <code>RoundRectbngle2D</code>
         * from the specified <code>flobt</code> coordinbtes.
         *
         * @pbrbm x the X coordinbte of the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm y the Y coordinbte of the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm w the width to which to set the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm h the height to which to set the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm brcw the width of the brc to use to round off the
         *             corners of the newly constructed
         *             <code>RoundRectbngle2D</code>
         * @pbrbm brch the height of the brc to use to round off the
         *             corners of the newly constructed
         *             <code>RoundRectbngle2D</code>
         * @since 1.2
         */
        public Flobt(flobt x, flobt y, flobt w, flobt h,
                     flobt brcw, flobt brch)
        {
            setRoundRect(x, y, w, h, brcw, brch);
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
        public double getArcWidth() {
            return (double) brcwidth;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getArcHeight() {
            return (double) brcheight;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public boolebn isEmpty() {
            return (width <= 0.0f) || (height <= 0.0f);
        }

        /**
         * Sets the locbtion, size, bnd corner rbdii of this
         * <code>RoundRectbngle2D</code> to the specified
         * <code>flobt</code> vblues.
         *
         * @pbrbm x the X coordinbte to which to set the
         *          locbtion of this <code>RoundRectbngle2D</code>
         * @pbrbm y the Y coordinbte to which to set the
         *          locbtion of this <code>RoundRectbngle2D</code>
         * @pbrbm w the width to which to set this
         *          <code>RoundRectbngle2D</code>
         * @pbrbm h the height to which to set this
         *          <code>RoundRectbngle2D</code>
         * @pbrbm brcw the width to which to set the brc of this
         *             <code>RoundRectbngle2D</code>
         * @pbrbm brch the height to which to set the brc of this
         *             <code>RoundRectbngle2D</code>
         * @since 1.2
         */
        public void setRoundRect(flobt x, flobt y, flobt w, flobt h,
                                 flobt brcw, flobt brch)
        {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.brcwidth = brcw;
            this.brcheight = brch;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRoundRect(double x, double y, double w, double h,
                                 double brcw, double brch)
        {
            this.x = (flobt) x;
            this.y = (flobt) y;
            this.width = (flobt) w;
            this.height = (flobt) h;
            this.brcwidth = (flobt) brcw;
            this.brcheight = (flobt) brch;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRoundRect(RoundRectbngle2D rr) {
            this.x = (flobt) rr.getX();
            this.y = (flobt) rr.getY();
            this.width = (flobt) rr.getWidth();
            this.height = (flobt) rr.getHeight();
            this.brcwidth = (flobt) rr.getArcWidth();
            this.brcheight = (flobt) rr.getArcHeight();
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
        privbte stbtic finbl long seriblVersionUID = -3423150618393866922L;
    }

    /**
     * The <code>Double</code> clbss defines b rectbngle with rounded
     * corners bll specified in <code>double</code> coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Double extends RoundRectbngle2D
        implements Seriblizbble
    {
        /**
         * The X coordinbte of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double x;

        /**
         * The Y coordinbte of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double y;

        /**
         * The width of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double width;

        /**
         * The height of this <code>RoundRectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double height;

        /**
         * The width of the brc thbt rounds off the corners.
         * @since 1.2
         * @seribl
         */
        public double brcwidth;

        /**
         * The height of the brc thbt rounds off the corners.
         * @since 1.2
         * @seribl
         */
        public double brcheight;

        /**
         * Constructs b new <code>RoundRectbngle2D</code>, initiblized to
         * locbtion (0.0,&nbsp;0.0), size (0.0,&nbsp;0.0), bnd corner brcs
         * of rbdius 0.0.
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes b <code>RoundRectbngle2D</code>
         * from the specified <code>double</code> coordinbtes.
         *
         * @pbrbm x the X coordinbte of the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm y the Y coordinbte of the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm w the width to which to set the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm h the height to which to set the newly
         *          constructed <code>RoundRectbngle2D</code>
         * @pbrbm brcw the width of the brc to use to round off the
         *             corners of the newly constructed
         *             <code>RoundRectbngle2D</code>
         * @pbrbm brch the height of the brc to use to round off the
         *             corners of the newly constructed
         *             <code>RoundRectbngle2D</code>
         * @since 1.2
         */
        public Double(double x, double y, double w, double h,
                      double brcw, double brch)
        {
            setRoundRect(x, y, w, h, brcw, brch);
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
        public double getArcWidth() {
            return brcwidth;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getArcHeight() {
            return brcheight;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public boolebn isEmpty() {
            return (width <= 0.0f) || (height <= 0.0f);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRoundRect(double x, double y, double w, double h,
                                 double brcw, double brch)
        {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.brcwidth = brcw;
            this.brcheight = brch;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRoundRect(RoundRectbngle2D rr) {
            this.x = rr.getX();
            this.y = rr.getY();
            this.width = rr.getWidth();
            this.height = rr.getHeight();
            this.brcwidth = rr.getArcWidth();
            this.brcheight = rr.getArcHeight();
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
        privbte stbtic finbl long seriblVersionUID = 1048939333485206117L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.geom.RoundRectbngle2D.Flobt
     * @see jbvb.bwt.geom.RoundRectbngle2D.Double
     * @since 1.2
     */
    protected RoundRectbngle2D() {
    }

    /**
     * Gets the width of the brc thbt rounds off the corners.
     * @return the width of the brc thbt rounds off the corners
     * of this <code>RoundRectbngle2D</code>.
     * @since 1.2
     */
    public bbstrbct double getArcWidth();

    /**
     * Gets the height of the brc thbt rounds off the corners.
     * @return the height of the brc thbt rounds off the corners
     * of this <code>RoundRectbngle2D</code>.
     * @since 1.2
     */
    public bbstrbct double getArcHeight();

    /**
     * Sets the locbtion, size, bnd corner rbdii of this
     * <code>RoundRectbngle2D</code> to the specified
     * <code>double</code> vblues.
     *
     * @pbrbm x the X coordinbte to which to set the
     *          locbtion of this <code>RoundRectbngle2D</code>
     * @pbrbm y the Y coordinbte to which to set the
     *          locbtion of this <code>RoundRectbngle2D</code>
     * @pbrbm w the width to which to set this
     *          <code>RoundRectbngle2D</code>
     * @pbrbm h the height to which to set this
     *          <code>RoundRectbngle2D</code>
     * @pbrbm brcWidth the width to which to set the brc of this
     *                 <code>RoundRectbngle2D</code>
     * @pbrbm brcHeight the height to which to set the brc of this
     *                  <code>RoundRectbngle2D</code>
     * @since 1.2
     */
    public bbstrbct void setRoundRect(double x, double y, double w, double h,
                                      double brcWidth, double brcHeight);

    /**
     * Sets this <code>RoundRectbngle2D</code> to be the sbme bs the
     * specified <code>RoundRectbngle2D</code>.
     * @pbrbm rr the specified <code>RoundRectbngle2D</code>
     * @since 1.2
     */
    public void setRoundRect(RoundRectbngle2D rr) {
        setRoundRect(rr.getX(), rr.getY(), rr.getWidth(), rr.getHeight(),
                     rr.getArcWidth(), rr.getArcHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public void setFrbme(double x, double y, double w, double h) {
        setRoundRect(x, y, w, h, getArcWidth(), getArcHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y) {
        if (isEmpty()) {
            return fblse;
        }
        double rrx0 = getX();
        double rry0 = getY();
        double rrx1 = rrx0 + getWidth();
        double rry1 = rry0 + getHeight();
        // Check for trivibl rejection - point is outside bounding rectbngle
        if (x < rrx0 || y < rry0 || x >= rrx1 || y >= rry1) {
            return fblse;
        }
        double bw = Mbth.min(getWidth(), Mbth.bbs(getArcWidth())) / 2.0;
        double bh = Mbth.min(getHeight(), Mbth.bbs(getArcHeight())) / 2.0;
        // Check which corner point is in bnd do circulbr contbinment
        // test - otherwise simple bcceptbnce
        if (x >= (rrx0 += bw) && x < (rrx0 = rrx1 - bw)) {
            return true;
        }
        if (y >= (rry0 += bh) && y < (rry0 = rry1 - bh)) {
            return true;
        }
        x = (x - rrx0) / bw;
        y = (y - rry0) / bh;
        return (x * x + y * y <= 1.0);
    }

    privbte int clbssify(double coord, double left, double right,
                         double brcsize)
    {
        if (coord < left) {
            return 0;
        } else if (coord < left + brcsize) {
            return 1;
        } else if (coord < right - brcsize) {
            return 2;
        } else if (coord < right) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h) {
        if (isEmpty() || w <= 0 || h <= 0) {
            return fblse;
        }
        double rrx0 = getX();
        double rry0 = getY();
        double rrx1 = rrx0 + getWidth();
        double rry1 = rry0 + getHeight();
        // Check for trivibl rejection - bounding rectbngles do not intersect
        if (x + w <= rrx0 || x >= rrx1 || y + h <= rry0 || y >= rry1) {
            return fblse;
        }
        double bw = Mbth.min(getWidth(), Mbth.bbs(getArcWidth())) / 2.0;
        double bh = Mbth.min(getHeight(), Mbth.bbs(getArcHeight())) / 2.0;
        int x0clbss = clbssify(x, rrx0, rrx1, bw);
        int x1clbss = clbssify(x + w, rrx0, rrx1, bw);
        int y0clbss = clbssify(y, rry0, rry1, bh);
        int y1clbss = clbssify(y + h, rry0, rry1, bh);
        // Triviblly bccept if bny point is inside inner rectbngle
        if (x0clbss == 2 || x1clbss == 2 || y0clbss == 2 || y1clbss == 2) {
            return true;
        }
        // Triviblly bccept if either edge spbns inner rectbngle
        if ((x0clbss < 2 && x1clbss > 2) || (y0clbss < 2 && y1clbss > 2)) {
            return true;
        }
        // Since neither edge spbns the center, then one of the corners
        // must be in one of the rounded edges.  We detect this cbse if
        // b [xy]0clbss is 3 or b [xy]1clbss is 1.  One of those two cbses
        // must be true for ebch direction.
        // We now find b "nebrest point" to test for being inside b rounded
        // corner.
        x = (x1clbss == 1) ? (x = x + w - (rrx0 + bw)) : (x = x - (rrx1 - bw));
        y = (y1clbss == 1) ? (y = y + h - (rry0 + bh)) : (y = y - (rry1 - bh));
        x = x / bw;
        y = y / bh;
        return (x * x + y * y <= 1.0);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h) {
        if (isEmpty() || w <= 0 || h <= 0) {
            return fblse;
        }
        return (contbins(x, y) &&
                contbins(x + w, y) &&
                contbins(x, y + h) &&
                contbins(x + w, y + h));
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of this
     * <code>RoundRectbngle2D</code>.
     * The iterbtor for this clbss is multi-threbded sbfe, which mebns
     * thbt this <code>RoundRectbngle2D</code> clbss gubrbntees thbt
     * modificbtions to the geometry of this <code>RoundRectbngle2D</code>
     * object do not bffect bny iterbtions of thbt geometry thbt
     * bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to
     * the coordinbtes bs they bre returned in the iterbtion, or
     * <code>null</code> if untrbnsformed coordinbtes bre desired
     * @return    the <code>PbthIterbtor</code> object thbt returns the
     *          geometry of the outline of this
     *          <code>RoundRectbngle2D</code>, one segment bt b time.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new RoundRectIterbtor(this, bt);
    }

    /**
     * Returns the hbshcode for this <code>RoundRectbngle2D</code>.
     * @return the hbshcode for this <code>RoundRectbngle2D</code>.
     * @since 1.6
     */
    public int hbshCode() {
        long bits = jbvb.lbng.Double.doubleToLongBits(getX());
        bits += jbvb.lbng.Double.doubleToLongBits(getY()) * 37;
        bits += jbvb.lbng.Double.doubleToLongBits(getWidth()) * 43;
        bits += jbvb.lbng.Double.doubleToLongBits(getHeight()) * 47;
        bits += jbvb.lbng.Double.doubleToLongBits(getArcWidth()) * 53;
        bits += jbvb.lbng.Double.doubleToLongBits(getArcHeight()) * 59;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Determines whether or not the specified <code>Object</code> is
     * equbl to this <code>RoundRectbngle2D</code>.  The specified
     * <code>Object</code> is equbl to this <code>RoundRectbngle2D</code>
     * if it is bn instbnce of <code>RoundRectbngle2D</code> bnd if its
     * locbtion, size, bnd corner brc dimensions bre the sbme bs this
     * <code>RoundRectbngle2D</code>.
     * @pbrbm obj  bn <code>Object</code> to be compbred with this
     *             <code>RoundRectbngle2D</code>.
     * @return  <code>true</code> if <code>obj</code> is bn instbnce
     *          of <code>RoundRectbngle2D</code> bnd hbs the sbme vblues;
     *          <code>fblse</code> otherwise.
     * @since 1.6
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instbnceof RoundRectbngle2D) {
            RoundRectbngle2D rr2d = (RoundRectbngle2D) obj;
            return ((getX() == rr2d.getX()) &&
                    (getY() == rr2d.getY()) &&
                    (getWidth() == rr2d.getWidth()) &&
                    (getHeight() == rr2d.getHeight()) &&
                    (getArcWidth() == rr2d.getArcWidth()) &&
                    (getArcHeight() == rr2d.getArcHeight()));
        }
        return fblse;
    }
}
