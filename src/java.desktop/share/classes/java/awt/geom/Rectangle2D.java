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
 * The <code>Rectbngle2D</code> clbss describes b rectbngle
 * defined by b locbtion {@code (x,y)} bnd dimension
 * {@code (w x h)}.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects thbt
 * store b 2D rectbngle.
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss Rectbngle2D extends RectbngulbrShbpe {
    /**
     * The bitmbsk thbt indicbtes thbt b point lies to the left of
     * this <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public stbtic finbl int OUT_LEFT = 1;

    /**
     * The bitmbsk thbt indicbtes thbt b point lies bbove
     * this <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public stbtic finbl int OUT_TOP = 2;

    /**
     * The bitmbsk thbt indicbtes thbt b point lies to the right of
     * this <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public stbtic finbl int OUT_RIGHT = 4;

    /**
     * The bitmbsk thbt indicbtes thbt b point lies below
     * this <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public stbtic finbl int OUT_BOTTOM = 8;

    /**
     * The <code>Flobt</code> clbss defines b rectbngle specified in flobt
     * coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Flobt extends Rectbngle2D implements Seriblizbble {
        /**
         * The X coordinbte of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt x;

        /**
         * The Y coordinbte of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt y;

        /**
         * The width of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt width;

        /**
         * The height of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public flobt height;

        /**
         * Constructs b new <code>Rectbngle2D</code>, initiblized to
         * locbtion (0.0,&nbsp;0.0) bnd size (0.0,&nbsp;0.0).
         * @since 1.2
         */
        public Flobt() {
        }

        /**
         * Constructs bnd initiblizes b <code>Rectbngle2D</code>
         * from the specified <code>flobt</code> coordinbtes.
         *
         * @pbrbm x the X coordinbte of the upper-left corner
         *          of the newly constructed <code>Rectbngle2D</code>
         * @pbrbm y the Y coordinbte of the upper-left corner
         *          of the newly constructed <code>Rectbngle2D</code>
         * @pbrbm w the width of the newly constructed
         *          <code>Rectbngle2D</code>
         * @pbrbm h the height of the newly constructed
         *          <code>Rectbngle2D</code>
         * @since 1.2
        */
        public Flobt(flobt x, flobt y, flobt w, flobt h) {
            setRect(x, y, w, h);
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
            return (width <= 0.0f) || (height <= 0.0f);
        }

        /**
         * Sets the locbtion bnd size of this <code>Rectbngle2D</code>
         * to the specified <code>flobt</code> vblues.
         *
         * @pbrbm x the X coordinbte of the upper-left corner
         *          of this <code>Rectbngle2D</code>
         * @pbrbm y the Y coordinbte of the upper-left corner
         *          of this <code>Rectbngle2D</code>
         * @pbrbm w the width of this <code>Rectbngle2D</code>
         * @pbrbm h the height of this <code>Rectbngle2D</code>
         * @since 1.2
         */
        public void setRect(flobt x, flobt y, flobt w, flobt h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRect(double x, double y, double w, double h) {
            this.x = (flobt) x;
            this.y = (flobt) y;
            this.width = (flobt) w;
            this.height = (flobt) h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRect(Rectbngle2D r) {
            this.x = (flobt) r.getX();
            this.y = (flobt) r.getY();
            this.width = (flobt) r.getWidth();
            this.height = (flobt) r.getHeight();
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public int outcode(double x, double y) {
            /*
             * Note on cbsts to double below.  If the brithmetic of
             * x+w or y+h is done in flobt, then some bits mby be
             * lost if the binbry exponents of x/y bnd w/h bre not
             * similbr.  By converting to double before the bddition
             * we force the bddition to be cbrried out in double to
             * bvoid rounding error in the compbrison.
             *
             * See bug 4320890 for problems thbt this inbccurbcy cbuses.
             */
            int out = 0;
            if (this.width <= 0) {
                out |= OUT_LEFT | OUT_RIGHT;
            } else if (x < this.x) {
                out |= OUT_LEFT;
            } else if (x > this.x + (double) this.width) {
                out |= OUT_RIGHT;
            }
            if (this.height <= 0) {
                out |= OUT_TOP | OUT_BOTTOM;
            } else if (y < this.y) {
                out |= OUT_TOP;
            } else if (y > this.y + (double) this.height) {
                out |= OUT_BOTTOM;
            }
            return out;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            return new Flobt(x, y, width, height);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D crebteIntersection(Rectbngle2D r) {
            Rectbngle2D dest;
            if (r instbnceof Flobt) {
                dest = new Rectbngle2D.Flobt();
            } else {
                dest = new Rectbngle2D.Double();
            }
            Rectbngle2D.intersect(this, r, dest);
            return dest;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D crebteUnion(Rectbngle2D r) {
            Rectbngle2D dest;
            if (r instbnceof Flobt) {
                dest = new Rectbngle2D.Flobt();
            } else {
                dest = new Rectbngle2D.Double();
            }
            Rectbngle2D.union(this, r, dest);
            return dest;
        }

        /**
         * Returns the <code>String</code> representbtion of this
         * <code>Rectbngle2D</code>.
         * @return b <code>String</code> representing this
         * <code>Rectbngle2D</code>.
         * @since 1.2
         */
        public String toString() {
            return getClbss().getNbme()
                + "[x=" + x +
                ",y=" + y +
                ",w=" + width +
                ",h=" + height + "]";
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 3798716824173675777L;
    }

    /**
     * The <code>Double</code> clbss defines b rectbngle specified in
     * double coordinbtes.
     * @since 1.2
     */
    public stbtic clbss Double extends Rectbngle2D implements Seriblizbble {
        /**
         * The X coordinbte of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double x;

        /**
         * The Y coordinbte of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double y;

        /**
         * The width of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double width;

        /**
         * The height of this <code>Rectbngle2D</code>.
         * @since 1.2
         * @seribl
         */
        public double height;

        /**
         * Constructs b new <code>Rectbngle2D</code>, initiblized to
         * locbtion (0,&nbsp;0) bnd size (0,&nbsp;0).
         * @since 1.2
         */
        public Double() {
        }

        /**
         * Constructs bnd initiblizes b <code>Rectbngle2D</code>
         * from the specified <code>double</code> coordinbtes.
         *
         * @pbrbm x the X coordinbte of the upper-left corner
         *          of the newly constructed <code>Rectbngle2D</code>
         * @pbrbm y the Y coordinbte of the upper-left corner
         *          of the newly constructed <code>Rectbngle2D</code>
         * @pbrbm w the width of the newly constructed
         *          <code>Rectbngle2D</code>
         * @pbrbm h the height of the newly constructed
         *          <code>Rectbngle2D</code>
         * @since 1.2
         */
        public Double(double x, double y, double w, double h) {
            setRect(x, y, w, h);
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
            return (width <= 0.0) || (height <= 0.0);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRect(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setRect(Rectbngle2D r) {
            this.x = r.getX();
            this.y = r.getY();
            this.width = r.getWidth();
            this.height = r.getHeight();
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public int outcode(double x, double y) {
            int out = 0;
            if (this.width <= 0) {
                out |= OUT_LEFT | OUT_RIGHT;
            } else if (x < this.x) {
                out |= OUT_LEFT;
            } else if (x > this.x + this.width) {
                out |= OUT_RIGHT;
            }
            if (this.height <= 0) {
                out |= OUT_TOP | OUT_BOTTOM;
            } else if (y < this.y) {
                out |= OUT_TOP;
            } else if (y > this.y + this.height) {
                out |= OUT_BOTTOM;
            }
            return out;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D getBounds2D() {
            return new Double(x, y, width, height);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D crebteIntersection(Rectbngle2D r) {
            Rectbngle2D dest = new Rectbngle2D.Double();
            Rectbngle2D.intersect(this, r, dest);
            return dest;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectbngle2D crebteUnion(Rectbngle2D r) {
            Rectbngle2D dest = new Rectbngle2D.Double();
            Rectbngle2D.union(this, r, dest);
            return dest;
        }

        /**
         * Returns the <code>String</code> representbtion of this
         * <code>Rectbngle2D</code>.
         * @return b <code>String</code> representing this
         * <code>Rectbngle2D</code>.
         * @since 1.2
         */
        public String toString() {
            return getClbss().getNbme()
                + "[x=" + x +
                ",y=" + y +
                ",w=" + width +
                ",h=" + height + "]";
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 7771313791441850493L;
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.geom.Rectbngle2D.Flobt
     * @see jbvb.bwt.geom.Rectbngle2D.Double
     * @see jbvb.bwt.Rectbngle
     * @since 1.2
     */
    protected Rectbngle2D() {
    }

    /**
     * Sets the locbtion bnd size of this <code>Rectbngle2D</code>
     * to the specified <code>double</code> vblues.
     *
     * @pbrbm x the X coordinbte of the upper-left corner
     *          of this <code>Rectbngle2D</code>
     * @pbrbm y the Y coordinbte of the upper-left corner
     *          of this <code>Rectbngle2D</code>
     * @pbrbm w the width of this <code>Rectbngle2D</code>
     * @pbrbm h the height of this <code>Rectbngle2D</code>
     * @since 1.2
     */
    public bbstrbct void setRect(double x, double y, double w, double h);

    /**
     * Sets this <code>Rectbngle2D</code> to be the sbme bs the specified
     * <code>Rectbngle2D</code>.
     * @pbrbm r the specified <code>Rectbngle2D</code>
     * @since 1.2
     */
    public void setRect(Rectbngle2D r) {
        setRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * Tests if the specified line segment intersects the interior of this
     * <code>Rectbngle2D</code>.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the specified
     *           line segment
     * @pbrbm y1 the Y coordinbte of the stbrt point of the specified
     *           line segment
     * @pbrbm x2 the X coordinbte of the end point of the specified
     *           line segment
     * @pbrbm y2 the Y coordinbte of the end point of the specified
     *           line segment
     * @return <code>true</code> if the specified line segment intersects
     * the interior of this <code>Rectbngle2D</code>; <code>fblse</code>
     * otherwise.
     * @since 1.2
     */
    public boolebn intersectsLine(double x1, double y1, double x2, double y2) {
        int out1, out2;
        if ((out2 = outcode(x2, y2)) == 0) {
            return true;
        }
        while ((out1 = outcode(x1, y1)) != 0) {
            if ((out1 & out2) != 0) {
                return fblse;
            }
            if ((out1 & (OUT_LEFT | OUT_RIGHT)) != 0) {
                double x = getX();
                if ((out1 & OUT_RIGHT) != 0) {
                    x += getWidth();
                }
                y1 = y1 + (x - x1) * (y2 - y1) / (x2 - x1);
                x1 = x;
            } else {
                double y = getY();
                if ((out1 & OUT_BOTTOM) != 0) {
                    y += getHeight();
                }
                x1 = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                y1 = y;
            }
        }
        return true;
    }

    /**
     * Tests if the specified line segment intersects the interior of this
     * <code>Rectbngle2D</code>.
     * @pbrbm l the specified {@link Line2D} to test for intersection
     * with the interior of this <code>Rectbngle2D</code>
     * @return <code>true</code> if the specified <code>Line2D</code>
     * intersects the interior of this <code>Rectbngle2D</code>;
     * <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn intersectsLine(Line2D l) {
        return intersectsLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
    }

    /**
     * Determines where the specified coordinbtes lie with respect
     * to this <code>Rectbngle2D</code>.
     * This method computes b binbry OR of the bppropribte mbsk vblues
     * indicbting, for ebch side of this <code>Rectbngle2D</code>,
     * whether or not the specified coordinbtes bre on the sbme side
     * of the edge bs the rest of this <code>Rectbngle2D</code>.
     * @pbrbm x the specified X coordinbte
     * @pbrbm y the specified Y coordinbte
     * @return the logicbl OR of bll bppropribte out codes.
     * @see #OUT_LEFT
     * @see #OUT_TOP
     * @see #OUT_RIGHT
     * @see #OUT_BOTTOM
     * @since 1.2
     */
    public bbstrbct int outcode(double x, double y);

    /**
     * Determines where the specified {@link Point2D} lies with
     * respect to this <code>Rectbngle2D</code>.
     * This method computes b binbry OR of the bppropribte mbsk vblues
     * indicbting, for ebch side of this <code>Rectbngle2D</code>,
     * whether or not the specified <code>Point2D</code> is on the sbme
     * side of the edge bs the rest of this <code>Rectbngle2D</code>.
     * @pbrbm p the specified <code>Point2D</code>
     * @return the logicbl OR of bll bppropribte out codes.
     * @see #OUT_LEFT
     * @see #OUT_TOP
     * @see #OUT_RIGHT
     * @see #OUT_BOTTOM
     * @since 1.2
     */
    public int outcode(Point2D p) {
        return outcode(p.getX(), p.getY());
    }

    /**
     * Sets the locbtion bnd size of the outer bounds of this
     * <code>Rectbngle2D</code> to the specified rectbngulbr vblues.
     *
     * @pbrbm x the X coordinbte of the upper-left corner
     *          of this <code>Rectbngle2D</code>
     * @pbrbm y the Y coordinbte of the upper-left corner
     *          of this <code>Rectbngle2D</code>
     * @pbrbm w the width of this <code>Rectbngle2D</code>
     * @pbrbm h the height of this <code>Rectbngle2D</code>
     * @since 1.2
     */
    public void setFrbme(double x, double y, double w, double h) {
        setRect(x, y, w, h);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle2D getBounds2D() {
        return (Rectbngle2D) clone();
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y) {
        double x0 = getX();
        double y0 = getY();
        return (x >= x0 &&
                y >= y0 &&
                x < x0 + getWidth() &&
                y < y0 + getHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h) {
        if (isEmpty() || w <= 0 || h <= 0) {
            return fblse;
        }
        double x0 = getX();
        double y0 = getY();
        return (x + w > x0 &&
                y + h > y0 &&
                x < x0 + getWidth() &&
                y < y0 + getHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h) {
        if (isEmpty() || w <= 0 || h <= 0) {
            return fblse;
        }
        double x0 = getX();
        double y0 = getY();
        return (x >= x0 &&
                y >= y0 &&
                (x + w) <= x0 + getWidth() &&
                (y + h) <= y0 + getHeight());
    }

    /**
     * Returns b new <code>Rectbngle2D</code> object representing the
     * intersection of this <code>Rectbngle2D</code> with the specified
     * <code>Rectbngle2D</code>.
     * @pbrbm r the <code>Rectbngle2D</code> to be intersected with
     * this <code>Rectbngle2D</code>
     * @return the lbrgest <code>Rectbngle2D</code> contbined in both
     *          the specified <code>Rectbngle2D</code> bnd in this
     *          <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public bbstrbct Rectbngle2D crebteIntersection(Rectbngle2D r);

    /**
     * Intersects the pbir of specified source <code>Rectbngle2D</code>
     * objects bnd puts the result into the specified destinbtion
     * <code>Rectbngle2D</code> object.  One of the source rectbngles
     * cbn blso be the destinbtion to bvoid crebting b third Rectbngle2D
     * object, but in this cbse the originbl points of this source
     * rectbngle will be overwritten by this method.
     * @pbrbm src1 the first of b pbir of <code>Rectbngle2D</code>
     * objects to be intersected with ebch other
     * @pbrbm src2 the second of b pbir of <code>Rectbngle2D</code>
     * objects to be intersected with ebch other
     * @pbrbm dest the <code>Rectbngle2D</code> thbt holds the
     * results of the intersection of <code>src1</code> bnd
     * <code>src2</code>
     * @since 1.2
     */
    public stbtic void intersect(Rectbngle2D src1,
                                 Rectbngle2D src2,
                                 Rectbngle2D dest) {
        double x1 = Mbth.mbx(src1.getMinX(), src2.getMinX());
        double y1 = Mbth.mbx(src1.getMinY(), src2.getMinY());
        double x2 = Mbth.min(src1.getMbxX(), src2.getMbxX());
        double y2 = Mbth.min(src1.getMbxY(), src2.getMbxY());
        dest.setFrbme(x1, y1, x2-x1, y2-y1);
    }

    /**
     * Returns b new <code>Rectbngle2D</code> object representing the
     * union of this <code>Rectbngle2D</code> with the specified
     * <code>Rectbngle2D</code>.
     * @pbrbm r the <code>Rectbngle2D</code> to be combined with
     * this <code>Rectbngle2D</code>
     * @return the smbllest <code>Rectbngle2D</code> contbining both
     * the specified <code>Rectbngle2D</code> bnd this
     * <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public bbstrbct Rectbngle2D crebteUnion(Rectbngle2D r);

    /**
     * Unions the pbir of source <code>Rectbngle2D</code> objects
     * bnd puts the result into the specified destinbtion
     * <code>Rectbngle2D</code> object.  One of the source rectbngles
     * cbn blso be the destinbtion to bvoid crebting b third Rectbngle2D
     * object, but in this cbse the originbl points of this source
     * rectbngle will be overwritten by this method.
     * @pbrbm src1 the first of b pbir of <code>Rectbngle2D</code>
     * objects to be combined with ebch other
     * @pbrbm src2 the second of b pbir of <code>Rectbngle2D</code>
     * objects to be combined with ebch other
     * @pbrbm dest the <code>Rectbngle2D</code> thbt holds the
     * results of the union of <code>src1</code> bnd
     * <code>src2</code>
     * @since 1.2
     */
    public stbtic void union(Rectbngle2D src1,
                             Rectbngle2D src2,
                             Rectbngle2D dest) {
        double x1 = Mbth.min(src1.getMinX(), src2.getMinX());
        double y1 = Mbth.min(src1.getMinY(), src2.getMinY());
        double x2 = Mbth.mbx(src1.getMbxX(), src2.getMbxX());
        double y2 = Mbth.mbx(src1.getMbxY(), src2.getMbxY());
        dest.setFrbmeFromDibgonbl(x1, y1, x2, y2);
    }

    /**
     * Adds b point, specified by the double precision brguments
     * <code>newx</code> bnd <code>newy</code>, to this
     * <code>Rectbngle2D</code>.  The resulting <code>Rectbngle2D</code>
     * is the smbllest <code>Rectbngle2D</code> thbt
     * contbins both the originbl <code>Rectbngle2D</code> bnd the
     * specified point.
     * <p>
     * After bdding b point, b cbll to <code>contbins</code> with the
     * bdded point bs bn brgument does not necessbrily return
     * <code>true</code>. The <code>contbins</code> method does not
     * return <code>true</code> for points on the right or bottom
     * edges of b rectbngle. Therefore, if the bdded point fblls on
     * the left or bottom edge of the enlbrged rectbngle,
     * <code>contbins</code> returns <code>fblse</code> for thbt point.
     * @pbrbm newx the X coordinbte of the new point
     * @pbrbm newy the Y coordinbte of the new point
     * @since 1.2
     */
    public void bdd(double newx, double newy) {
        double x1 = Mbth.min(getMinX(), newx);
        double x2 = Mbth.mbx(getMbxX(), newx);
        double y1 = Mbth.min(getMinY(), newy);
        double y2 = Mbth.mbx(getMbxY(), newy);
        setRect(x1, y1, x2 - x1, y2 - y1);
    }

    /**
     * Adds the <code>Point2D</code> object <code>pt</code> to this
     * <code>Rectbngle2D</code>.
     * The resulting <code>Rectbngle2D</code> is the smbllest
     * <code>Rectbngle2D</code> thbt contbins both the originbl
     * <code>Rectbngle2D</code> bnd the specified <code>Point2D</code>.
     * <p>
     * After bdding b point, b cbll to <code>contbins</code> with the
     * bdded point bs bn brgument does not necessbrily return
     * <code>true</code>. The <code>contbins</code>
     * method does not return <code>true</code> for points on the right
     * or bottom edges of b rectbngle. Therefore, if the bdded point fblls
     * on the left or bottom edge of the enlbrged rectbngle,
     * <code>contbins</code> returns <code>fblse</code> for thbt point.
     * @pbrbm     pt the new <code>Point2D</code> to bdd to this
     * <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public void bdd(Point2D pt) {
        bdd(pt.getX(), pt.getY());
    }

    /**
     * Adds b <code>Rectbngle2D</code> object to this
     * <code>Rectbngle2D</code>.  The resulting <code>Rectbngle2D</code>
     * is the union of the two <code>Rectbngle2D</code> objects.
     * @pbrbm r the <code>Rectbngle2D</code> to bdd to this
     * <code>Rectbngle2D</code>.
     * @since 1.2
     */
    public void bdd(Rectbngle2D r) {
        double x1 = Mbth.min(getMinX(), r.getMinX());
        double x2 = Mbth.mbx(getMbxX(), r.getMbxX());
        double y1 = Mbth.min(getMinY(), r.getMinY());
        double y2 = Mbth.mbx(getMbxY(), r.getMbxY());
        setRect(x1, y1, x2 - x1, y2 - y1);
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of this
     * <code>Rectbngle2D</code>.
     * The iterbtor for this clbss is multi-threbded sbfe, which mebns
     * thbt this <code>Rectbngle2D</code> clbss gubrbntees thbt
     * modificbtions to the geometry of this <code>Rectbngle2D</code>
     * object do not bffect bny iterbtions of thbt geometry thbt
     * bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to
     * the coordinbtes bs they bre returned in the iterbtion, or
     * <code>null</code> if untrbnsformed coordinbtes bre desired
     * @return    the <code>PbthIterbtor</code> object thbt returns the
     *          geometry of the outline of this
     *          <code>Rectbngle2D</code>, one segment bt b time.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new RectIterbtor(this, bt);
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of the
     * flbttened <code>Rectbngle2D</code>.  Since rectbngles bre blrebdy
     * flbt, the <code>flbtness</code> pbrbmeter is ignored.
     * The iterbtor for this clbss is multi-threbded sbfe, which mebns
     * thbt this <code>Rectbngle2D</code> clbss gubrbntees thbt
     * modificbtions to the geometry of this <code>Rectbngle2D</code>
     * object do not bffect bny iterbtions of thbt geometry thbt
     * bre blrebdy in process.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to
     * the coordinbtes bs they bre returned in the iterbtion, or
     * <code>null</code> if untrbnsformed coordinbtes bre desired
     * @pbrbm flbtness the mbximum distbnce thbt the line segments used to
     * bpproximbte the curved segments bre bllowed to devibte from bny
     * point on the originbl curve.  Since rectbngles bre blrebdy flbt,
     * the <code>flbtness</code> pbrbmeter is ignored.
     * @return    the <code>PbthIterbtor</code> object thbt returns the
     *          geometry of the outline of this
     *          <code>Rectbngle2D</code>, one segment bt b time.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness) {
        return new RectIterbtor(this, bt);
    }

    /**
     * Returns the hbshcode for this <code>Rectbngle2D</code>.
     * @return the hbshcode for this <code>Rectbngle2D</code>.
     * @since 1.2
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
     * equbl to this <code>Rectbngle2D</code>.  The specified
     * <code>Object</code> is equbl to this <code>Rectbngle2D</code>
     * if it is bn instbnce of <code>Rectbngle2D</code> bnd if its
     * locbtion bnd size bre the sbme bs this <code>Rectbngle2D</code>.
     * @pbrbm obj bn <code>Object</code> to be compbred with this
     * <code>Rectbngle2D</code>.
     * @return     <code>true</code> if <code>obj</code> is bn instbnce
     *                     of <code>Rectbngle2D</code> bnd hbs
     *                     the sbme vblues; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instbnceof Rectbngle2D) {
            Rectbngle2D r2d = (Rectbngle2D) obj;
            return ((getX() == r2d.getX()) &&
                    (getY() == r2d.getY()) &&
                    (getWidth() == r2d.getWidth()) &&
                    (getHeight() == r2d.getHeight()));
        }
        return fblse;
    }
}
