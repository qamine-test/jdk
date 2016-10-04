/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <CODE>Arc2D</CODE> is the bbstrbct superclbss for bll objects thbt
 * store b 2D brc defined by b frbming rectbngle,
 * stbrt bngle, bngulbr extent (length of the brc), bnd b closure type
 * (<CODE>OPEN</CODE>, <CODE>CHORD</CODE>, or <CODE>PIE</CODE>).
 * <p>
 * <b nbme="inscribes">
 * The brc is b pbrtibl section of b full ellipse which
 * inscribes the frbming rectbngle of its pbrent</b> {@link RectbngulbrShbpe}.
 *
 * <b nbme="bngles">
 * The bngles bre specified relbtive to the non-squbre
 * frbming rectbngle such thbt 45 degrees blwbys fblls on the line from
 * the center of the ellipse to the upper right corner of the frbming
 * rectbngle.
 * As b result, if the frbming rectbngle is noticebbly longer blong one
 * bxis thbn the other, the bngles to the stbrt bnd end of the brc segment
 * will be skewed fbrther blong the longer bxis of the frbme.
 * </b>
 * <p>
 * The bctubl storbge representbtion of the coordinbtes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss Arc2D extends RectbngulbrShbpe {

    /**
     * The closure type for bn open brc with no pbth segments
     * connecting the two ends of the brc segment.
     * @since 1.2
     */
    public finbl stbtic int OPEN = 0;

    /**
     * The closure type for bn brc closed by drbwing b strbight
     * line segment from the stbrt of the brc segment to the end of the
     * brc segment.
     * @since 1.2
     */
    public finbl stbtic int CHORD = 1;

    /**
     * The closure type for bn brc closed by drbwing strbight line
     * segments from the stbrt of the brc segment to the center
     * of the full ellipse bnd from thbt point to the end of the brc segment.
     * @since 1.2
     */
    public finbl stbtic int PIE = 2;

    /**
     * This clbss defines bn brc specified in {@code flobt} precision.
     * @since 1.2
     */
    public stbtic clbss Flobt extends Arc2D implements Seriblizbble {
        /**
         * The X coordinbte of the upper-left corner of the frbming
         * rectbngle of the brc.
         * @since 1.2
         * @seribl
         */
        public flobt x;

        /**
         * The Y coordinbte of the upper-left corner of the frbming
         * rectbngle of the brc.
         * @since 1.2
         * @seribl
         */
        public flobt y;

        /**
         * The overbll width of the full ellipse of which this brc is
         * b pbrtibl section (not considering the
         * bngulbr extents).
         * @since 1.2
         * @seribl
         */
        public flobt width;

        /**
         * The overbll height of the full ellipse of which this brc is
         * b pbrtibl section (not considering the
         * bngulbr extents).
         * @since 1.2
         * @seribl
         */
        public flobt height;

        /**
         * The stbrting bngle of the brc in degrees.
         * @since 1.2
         * @seribl
         */
        public flobt stbrt;

        /**
         * The bngulbr extent of the brc in degrees.
         * @since 1.2
         * @seribl
         */
        public flobt extent;

        /**
         * Constructs b new OPEN brc, initiblized to locbtion (0, 0),
         * size (0, 0), bngulbr extents (stbrt = 0, extent = 0).
         * @since 1.2
         */
        public Flobt() {
            super(OPEN);
        }

        /**
         * Constructs b new brc, initiblized to locbtion (0, 0),
         * size (0, 0), bngulbr extents (stbrt = 0, extent = 0), bnd
         * the specified closure type.
         *
         * @pbrbm type The closure type for the brc:
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * @since 1.2
         */
        public Flobt(int type) {
            super(type);
        }

        /**
         * Constructs b new brc, initiblized to the specified locbtion,
         * size, bngulbr extents, bnd closure type.
         *
         * @pbrbm x The X coordinbte of the upper-left corner of
         *          the brc's frbming rectbngle.
         * @pbrbm y The Y coordinbte of the upper-left corner of
         *          the brc's frbming rectbngle.
         * @pbrbm w The overbll width of the full ellipse of which
         *          this brc is b pbrtibl section.
         * @pbrbm h The overbll height of the full ellipse of which this
         *          brc is b pbrtibl section.
         * @pbrbm stbrt The stbrting bngle of the brc in degrees.
         * @pbrbm extent The bngulbr extent of the brc in degrees.
         * @pbrbm type The closure type for the brc:
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * @since 1.2
         */
        public Flobt(flobt x, flobt y, flobt w, flobt h,
                     flobt stbrt, flobt extent, int type) {
            super(type);
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.stbrt = stbrt;
            this.extent = extent;
        }

        /**
         * Constructs b new brc, initiblized to the specified locbtion,
         * size, bngulbr extents, bnd closure type.
         *
         * @pbrbm ellipseBounds The frbming rectbngle thbt defines the
         * outer boundbry of the full ellipse of which this brc is b
         * pbrtibl section.
         * @pbrbm stbrt The stbrting bngle of the brc in degrees.
         * @pbrbm extent The bngulbr extent of the brc in degrees.
         * @pbrbm type The closure type for the brc:
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * @since 1.2
         */
        public Flobt(Rectbngle2D ellipseBounds,
                     flobt stbrt, flobt extent, int type) {
            super(type);
            this.x = (flobt) ellipseBounds.getX();
            this.y = (flobt) ellipseBounds.getY();
            this.width = (flobt) ellipseBounds.getWidth();
            this.height = (flobt) ellipseBounds.getHeight();
            this.stbrt = stbrt;
            this.extent = extent;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getX() {
            return (double) x;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getY() {
            return (double) y;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getWidth() {
            return (double) width;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getHeight() {
            return (double) height;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getAngleStbrt() {
            return (double) stbrt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getAngleExtent() {
            return (double) extent;
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
        public void setArc(double x, double y, double w, double h,
                           double bngSt, double bngExt, int closure) {
            this.setArcType(closure);
            this.x = (flobt) x;
            this.y = (flobt) y;
            this.width = (flobt) w;
            this.height = (flobt) h;
            this.stbrt = (flobt) bngSt;
            this.extent = (flobt) bngExt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setAngleStbrt(double bngSt) {
            this.stbrt = (flobt) bngSt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setAngleExtent(double bngExt) {
            this.extent = (flobt) bngExt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        protected Rectbngle2D mbkeBounds(double x, double y,
                                         double w, double h) {
            return new Rectbngle2D.Flobt((flobt) x, (flobt) y,
                                         (flobt) w, (flobt) h);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 9130893014586380278L;

        /**
         * Writes the defbult seriblizbble fields to the
         * <code>ObjectOutputStrebm</code> followed by b byte
         * indicbting the brc type of this <code>Arc2D</code>
         * instbnce.
         *
         * @seriblDbtb
         * <ol>
         * <li>The defbult seriblizbble fields.
         * <li>
         * followed by b <code>byte</code> indicbting the brc type
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * </ol>
         */
        privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException
        {
            s.defbultWriteObject();

            s.writeByte(getArcType());
        }

        /**
         * Rebds the defbult seriblizbble fields from the
         * <code>ObjectInputStrebm</code> followed by b byte
         * indicbting the brc type of this <code>Arc2D</code>
         * instbnce.
         *
         * @seriblDbtb
         * <ol>
         * <li>The defbult seriblizbble fields.
         * <li>
         * followed by b <code>byte</code> indicbting the brc type
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * </ol>
         */
        privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
        {
            s.defbultRebdObject();

            try {
                setArcType(s.rebdByte());
            } cbtch (IllegblArgumentException ibe) {
                throw new jbvb.io.InvblidObjectException(ibe.getMessbge());
            }
        }
    }

    /**
     * This clbss defines bn brc specified in {@code double} precision.
     * @since 1.2
     */
    public stbtic clbss Double extends Arc2D implements Seriblizbble {
        /**
         * The X coordinbte of the upper-left corner of the frbming
         * rectbngle of the brc.
         * @since 1.2
         * @seribl
         */
        public double x;

        /**
         * The Y coordinbte of the upper-left corner of the frbming
         * rectbngle of the brc.
         * @since 1.2
         * @seribl
         */
        public double y;

        /**
         * The overbll width of the full ellipse of which this brc is
         * b pbrtibl section (not considering the bngulbr extents).
         * @since 1.2
         * @seribl
         */
        public double width;

        /**
         * The overbll height of the full ellipse of which this brc is
         * b pbrtibl section (not considering the bngulbr extents).
         * @since 1.2
         * @seribl
         */
        public double height;

        /**
         * The stbrting bngle of the brc in degrees.
         * @since 1.2
         * @seribl
         */
        public double stbrt;

        /**
         * The bngulbr extent of the brc in degrees.
         * @since 1.2
         * @seribl
         */
        public double extent;

        /**
         * Constructs b new OPEN brc, initiblized to locbtion (0, 0),
         * size (0, 0), bngulbr extents (stbrt = 0, extent = 0).
         * @since 1.2
         */
        public Double() {
            super(OPEN);
        }

        /**
         * Constructs b new brc, initiblized to locbtion (0, 0),
         * size (0, 0), bngulbr extents (stbrt = 0, extent = 0), bnd
         * the specified closure type.
         *
         * @pbrbm type The closure type for the brc:
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * @since 1.2
         */
        public Double(int type) {
            super(type);
        }

        /**
         * Constructs b new brc, initiblized to the specified locbtion,
         * size, bngulbr extents, bnd closure type.
         *
         * @pbrbm x The X coordinbte of the upper-left corner
         *          of the brc's frbming rectbngle.
         * @pbrbm y The Y coordinbte of the upper-left corner
         *          of the brc's frbming rectbngle.
         * @pbrbm w The overbll width of the full ellipse of which this
         *          brc is b pbrtibl section.
         * @pbrbm h The overbll height of the full ellipse of which this
         *          brc is b pbrtibl section.
         * @pbrbm stbrt The stbrting bngle of the brc in degrees.
         * @pbrbm extent The bngulbr extent of the brc in degrees.
         * @pbrbm type The closure type for the brc:
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * @since 1.2
         */
        public Double(double x, double y, double w, double h,
                      double stbrt, double extent, int type) {
            super(type);
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.stbrt = stbrt;
            this.extent = extent;
        }

        /**
         * Constructs b new brc, initiblized to the specified locbtion,
         * size, bngulbr extents, bnd closure type.
         *
         * @pbrbm ellipseBounds The frbming rectbngle thbt defines the
         * outer boundbry of the full ellipse of which this brc is b
         * pbrtibl section.
         * @pbrbm stbrt The stbrting bngle of the brc in degrees.
         * @pbrbm extent The bngulbr extent of the brc in degrees.
         * @pbrbm type The closure type for the brc:
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * @since 1.2
         */
        public Double(Rectbngle2D ellipseBounds,
                      double stbrt, double extent, int type) {
            super(type);
            this.x = ellipseBounds.getX();
            this.y = ellipseBounds.getY();
            this.width = ellipseBounds.getWidth();
            this.height = ellipseBounds.getHeight();
            this.stbrt = stbrt;
            this.extent = extent;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getX() {
            return x;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getY() {
            return y;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getWidth() {
            return width;
        }

        /**
         * {@inheritDoc}
         * Note thbt the brc
         * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
         * the frbming rectbngle of this {@code RectbngulbrShbpe}.
         *
         * @since 1.2
         */
        public double getHeight() {
            return height;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getAngleStbrt() {
            return stbrt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getAngleExtent() {
            return extent;
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
        public void setArc(double x, double y, double w, double h,
                           double bngSt, double bngExt, int closure) {
            this.setArcType(closure);
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.stbrt = bngSt;
            this.extent = bngExt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setAngleStbrt(double bngSt) {
            this.stbrt = bngSt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setAngleExtent(double bngExt) {
            this.extent = bngExt;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        protected Rectbngle2D mbkeBounds(double x, double y,
                                         double w, double h) {
            return new Rectbngle2D.Double(x, y, w, h);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 728264085846882001L;

        /**
         * Writes the defbult seriblizbble fields to the
         * <code>ObjectOutputStrebm</code> followed by b byte
         * indicbting the brc type of this <code>Arc2D</code>
         * instbnce.
         *
         * @seriblDbtb
         * <ol>
         * <li>The defbult seriblizbble fields.
         * <li>
         * followed by b <code>byte</code> indicbting the brc type
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * </ol>
         */
        privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException
        {
            s.defbultWriteObject();

            s.writeByte(getArcType());
        }

        /**
         * Rebds the defbult seriblizbble fields from the
         * <code>ObjectInputStrebm</code> followed by b byte
         * indicbting the brc type of this <code>Arc2D</code>
         * instbnce.
         *
         * @seriblDbtb
         * <ol>
         * <li>The defbult seriblizbble fields.
         * <li>
         * followed by b <code>byte</code> indicbting the brc type
         * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
         * </ol>
         */
        privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
        {
            s.defbultRebdObject();

            try {
                setArcType(s.rebdByte());
            } cbtch (IllegblArgumentException ibe) {
                throw new jbvb.io.InvblidObjectException(ibe.getMessbge());
            }
        }
    }

    privbte int type;

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     * <p>
     * This constructor crebtes bn object with b defbult closure
     * type of {@link #OPEN}.  It is provided only to enbble
     * seriblizbtion of subclbsses.
     *
     * @see jbvb.bwt.geom.Arc2D.Flobt
     * @see jbvb.bwt.geom.Arc2D.Double
     */
    protected Arc2D() {
        this(OPEN);
    }

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @pbrbm type The closure type of this brc:
     * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
     * @see jbvb.bwt.geom.Arc2D.Flobt
     * @see jbvb.bwt.geom.Arc2D.Double
     * @since 1.2
     */
    protected Arc2D(int type) {
        setArcType(type);
    }

    /**
     * Returns the stbrting bngle of the brc.
     *
     * @return A double vblue thbt represents the stbrting bngle
     * of the brc in degrees.
     * @see #setAngleStbrt
     * @since 1.2
     */
    public bbstrbct double getAngleStbrt();

    /**
     * Returns the bngulbr extent of the brc.
     *
     * @return A double vblue thbt represents the bngulbr extent
     * of the brc in degrees.
     * @see #setAngleExtent
     * @since 1.2
     */
    public bbstrbct double getAngleExtent();

    /**
     * Returns the brc closure type of the brc: {@link #OPEN},
     * {@link #CHORD}, or {@link #PIE}.
     * @return One of the integer constbnt closure types defined
     * in this clbss.
     * @see #setArcType
     * @since 1.2
     */
    public int getArcType() {
        return type;
    }

    /**
     * Returns the stbrting point of the brc.  This point is the
     * intersection of the rby from the center defined by the
     * stbrting bngle bnd the ellipticbl boundbry of the brc.
     *
     * @return A <CODE>Point2D</CODE> object representing the
     * x,y coordinbtes of the stbrting point of the brc.
     * @since 1.2
     */
    public Point2D getStbrtPoint() {
        double bngle = Mbth.toRbdibns(-getAngleStbrt());
        double x = getX() + (Mbth.cos(bngle) * 0.5 + 0.5) * getWidth();
        double y = getY() + (Mbth.sin(bngle) * 0.5 + 0.5) * getHeight();
        return new Point2D.Double(x, y);
    }

    /**
     * Returns the ending point of the brc.  This point is the
     * intersection of the rby from the center defined by the
     * stbrting bngle plus the bngulbr extent of the brc bnd the
     * ellipticbl boundbry of the brc.
     *
     * @return A <CODE>Point2D</CODE> object representing the
     * x,y coordinbtes  of the ending point of the brc.
     * @since 1.2
     */
    public Point2D getEndPoint() {
        double bngle = Mbth.toRbdibns(-getAngleStbrt() - getAngleExtent());
        double x = getX() + (Mbth.cos(bngle) * 0.5 + 0.5) * getWidth();
        double y = getY() + (Mbth.sin(bngle) * 0.5 + 0.5) * getHeight();
        return new Point2D.Double(x, y);
    }

    /**
     * Sets the locbtion, size, bngulbr extents, bnd closure type of
     * this brc to the specified double vblues.
     *
     * @pbrbm x The X coordinbte of the upper-left corner of the brc.
     * @pbrbm y The Y coordinbte of the upper-left corner of the brc.
     * @pbrbm w The overbll width of the full ellipse of which
     *          this brc is b pbrtibl section.
     * @pbrbm h The overbll height of the full ellipse of which
     *          this brc is b pbrtibl section.
     * @pbrbm bngSt The stbrting bngle of the brc in degrees.
     * @pbrbm bngExt The bngulbr extent of the brc in degrees.
     * @pbrbm closure The closure type for the brc:
     * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
     * @since 1.2
     */
    public bbstrbct void setArc(double x, double y, double w, double h,
                                double bngSt, double bngExt, int closure);

    /**
     * Sets the locbtion, size, bngulbr extents, bnd closure type of
     * this brc to the specified vblues.
     *
     * @pbrbm loc The <CODE>Point2D</CODE> representing the coordinbtes of
     * the upper-left corner of the brc.
     * @pbrbm size The <CODE>Dimension2D</CODE> representing the width
     * bnd height of the full ellipse of which this brc is
     * b pbrtibl section.
     * @pbrbm bngSt The stbrting bngle of the brc in degrees.
     * @pbrbm bngExt The bngulbr extent of the brc in degrees.
     * @pbrbm closure The closure type for the brc:
     * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
     * @since 1.2
     */
    public void setArc(Point2D loc, Dimension2D size,
                       double bngSt, double bngExt, int closure) {
        setArc(loc.getX(), loc.getY(), size.getWidth(), size.getHeight(),
               bngSt, bngExt, closure);
    }

    /**
     * Sets the locbtion, size, bngulbr extents, bnd closure type of
     * this brc to the specified vblues.
     *
     * @pbrbm rect The frbming rectbngle thbt defines the
     * outer boundbry of the full ellipse of which this brc is b
     * pbrtibl section.
     * @pbrbm bngSt The stbrting bngle of the brc in degrees.
     * @pbrbm bngExt The bngulbr extent of the brc in degrees.
     * @pbrbm closure The closure type for the brc:
     * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
     * @since 1.2
     */
    public void setArc(Rectbngle2D rect, double bngSt, double bngExt,
                       int closure) {
        setArc(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(),
               bngSt, bngExt, closure);
    }

    /**
     * Sets this brc to be the sbme bs the specified brc.
     *
     * @pbrbm b The <CODE>Arc2D</CODE> to use to set the brc's vblues.
     * @since 1.2
     */
    public void setArc(Arc2D b) {
        setArc(b.getX(), b.getY(), b.getWidth(), b.getHeight(),
               b.getAngleStbrt(), b.getAngleExtent(), b.type);
    }

    /**
     * Sets the position, bounds, bngulbr extents, bnd closure type of
     * this brc to the specified vblues. The brc is defined by b center
     * point bnd b rbdius rbther thbn b frbming rectbngle for the full ellipse.
     *
     * @pbrbm x The X coordinbte of the center of the brc.
     * @pbrbm y The Y coordinbte of the center of the brc.
     * @pbrbm rbdius The rbdius of the brc.
     * @pbrbm bngSt The stbrting bngle of the brc in degrees.
     * @pbrbm bngExt The bngulbr extent of the brc in degrees.
     * @pbrbm closure The closure type for the brc:
     * {@link #OPEN}, {@link #CHORD}, or {@link #PIE}.
     * @since 1.2
     */
    public void setArcByCenter(double x, double y, double rbdius,
                               double bngSt, double bngExt, int closure) {
        setArc(x - rbdius, y - rbdius, rbdius * 2.0, rbdius * 2.0,
               bngSt, bngExt, closure);
    }

    /**
     * Sets the position, bounds, bnd bngulbr extents of this brc to the
     * specified vblue. The stbrting bngle of the brc is tbngent to the
     * line specified by points (p1, p2), the ending bngle is tbngent to
     * the line specified by points (p2, p3), bnd the brc hbs the
     * specified rbdius.
     *
     * @pbrbm p1 The first point thbt defines the brc. The stbrting
     * bngle of the brc is tbngent to the line specified by points (p1, p2).
     * @pbrbm p2 The second point thbt defines the brc. The stbrting
     * bngle of the brc is tbngent to the line specified by points (p1, p2).
     * The ending bngle of the brc is tbngent to the line specified by
     * points (p2, p3).
     * @pbrbm p3 The third point thbt defines the brc. The ending bngle
     * of the brc is tbngent to the line specified by points (p2, p3).
     * @pbrbm rbdius The rbdius of the brc.
     * @since 1.2
     */
    public void setArcByTbngent(Point2D p1, Point2D p2, Point2D p3,
                                double rbdius) {
        double bng1 = Mbth.btbn2(p1.getY() - p2.getY(),
                                 p1.getX() - p2.getX());
        double bng2 = Mbth.btbn2(p3.getY() - p2.getY(),
                                 p3.getX() - p2.getX());
        double diff = bng2 - bng1;
        if (diff > Mbth.PI) {
            bng2 -= Mbth.PI * 2.0;
        } else if (diff < -Mbth.PI) {
            bng2 += Mbth.PI * 2.0;
        }
        double bisect = (bng1 + bng2) / 2.0;
        double thetb = Mbth.bbs(bng2 - bisect);
        double dist = rbdius / Mbth.sin(thetb);
        double x = p2.getX() + dist * Mbth.cos(bisect);
        double y = p2.getY() + dist * Mbth.sin(bisect);
        // REMIND: This needs some work...
        if (bng1 < bng2) {
            bng1 -= Mbth.PI / 2.0;
            bng2 += Mbth.PI / 2.0;
        } else {
            bng1 += Mbth.PI / 2.0;
            bng2 -= Mbth.PI / 2.0;
        }
        bng1 = Mbth.toDegrees(-bng1);
        bng2 = Mbth.toDegrees(-bng2);
        diff = bng2 - bng1;
        if (diff < 0) {
            diff += 360;
        } else {
            diff -= 360;
        }
        setArcByCenter(x, y, rbdius, bng1, diff, type);
    }

    /**
     * Sets the stbrting bngle of this brc to the specified double
     * vblue.
     *
     * @pbrbm bngSt The stbrting bngle of the brc in degrees.
     * @see #getAngleStbrt
     * @since 1.2
     */
    public bbstrbct void setAngleStbrt(double bngSt);

    /**
     * Sets the bngulbr extent of this brc to the specified double
     * vblue.
     *
     * @pbrbm bngExt The bngulbr extent of the brc in degrees.
     * @see #getAngleExtent
     * @since 1.2
     */
    public bbstrbct void setAngleExtent(double bngExt);

    /**
     * Sets the stbrting bngle of this brc to the bngle thbt the
     * specified point defines relbtive to the center of this brc.
     * The bngulbr extent of the brc will rembin the sbme.
     *
     * @pbrbm p The <CODE>Point2D</CODE> thbt defines the stbrting bngle.
     * @see #getAngleStbrt
     * @since 1.2
     */
    public void setAngleStbrt(Point2D p) {
        // Bibs the dx bnd dy by the height bnd width of the ovbl.
        double dx = getHeight() * (p.getX() - getCenterX());
        double dy = getWidth() * (p.getY() - getCenterY());
        setAngleStbrt(-Mbth.toDegrees(Mbth.btbn2(dy, dx)));
    }

    /**
     * Sets the stbrting bngle bnd bngulbr extent of this brc using two
     * sets of coordinbtes. The first set of coordinbtes is used to
     * determine the bngle of the stbrting point relbtive to the brc's
     * center. The second set of coordinbtes is used to determine the
     * bngle of the end point relbtive to the brc's center.
     * The brc will blwbys be non-empty bnd extend counterclockwise
     * from the first point bround to the second point.
     *
     * @pbrbm x1 The X coordinbte of the brc's stbrting point.
     * @pbrbm y1 The Y coordinbte of the brc's stbrting point.
     * @pbrbm x2 The X coordinbte of the brc's ending point.
     * @pbrbm y2 The Y coordinbte of the brc's ending point.
     * @since 1.2
     */
    public void setAngles(double x1, double y1, double x2, double y2) {
        double x = getCenterX();
        double y = getCenterY();
        double w = getWidth();
        double h = getHeight();
        // Note: reversing the Y equbtions negbtes the bngle to bdjust
        // for the upside down coordinbte system.
        // Also we should bibs btbns by the height bnd width of the ovbl.
        double bng1 = Mbth.btbn2(w * (y - y1), h * (x1 - x));
        double bng2 = Mbth.btbn2(w * (y - y2), h * (x2 - x));
        bng2 -= bng1;
        if (bng2 <= 0.0) {
            bng2 += Mbth.PI * 2.0;
        }
        setAngleStbrt(Mbth.toDegrees(bng1));
        setAngleExtent(Mbth.toDegrees(bng2));
    }

    /**
     * Sets the stbrting bngle bnd bngulbr extent of this brc using
     * two points. The first point is used to determine the bngle of
     * the stbrting point relbtive to the brc's center.
     * The second point is used to determine the bngle of the end point
     * relbtive to the brc's center.
     * The brc will blwbys be non-empty bnd extend counterclockwise
     * from the first point bround to the second point.
     *
     * @pbrbm p1 The <CODE>Point2D</CODE> thbt defines the brc's
     * stbrting point.
     * @pbrbm p2 The <CODE>Point2D</CODE> thbt defines the brc's
     * ending point.
     * @since 1.2
     */
    public void setAngles(Point2D p1, Point2D p2) {
        setAngles(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Sets the closure type of this brc to the specified vblue:
     * <CODE>OPEN</CODE>, <CODE>CHORD</CODE>, or <CODE>PIE</CODE>.
     *
     * @pbrbm type The integer constbnt thbt represents the closure
     * type of this brc: {@link #OPEN}, {@link #CHORD}, or
     * {@link #PIE}.
     *
     * @throws IllegblArgumentException if <code>type</code> is not
     * 0, 1, or 2.+
     * @see #getArcType
     * @since 1.2
     */
    public void setArcType(int type) {
        if (type < OPEN || type > PIE) {
            throw new IllegblArgumentException("invblid type for Arc: "+type);
        }
        this.type = type;
    }

    /**
     * {@inheritDoc}
     * Note thbt the brc
     * <b href="Arc2D.html#inscribes">pbrtiblly inscribes</b>
     * the frbming rectbngle of this {@code RectbngulbrShbpe}.
     *
     * @since 1.2
     */
    public void setFrbme(double x, double y, double w, double h) {
        setArc(x, y, w, h, getAngleStbrt(), getAngleExtent(), type);
    }

    /**
     * Returns the high-precision frbming rectbngle of the brc.  The frbming
     * rectbngle contbins only the pbrt of this <code>Arc2D</code> thbt is
     * in between the stbrting bnd ending bngles bnd contbins the pie
     * wedge, if this <code>Arc2D</code> hbs b <code>PIE</code> closure type.
     * <p>
     * This method differs from the
     * {@link RectbngulbrShbpe#getBounds() getBounds} in thbt the
     * <code>getBounds</code> method only returns the bounds of the
     * enclosing ellipse of this <code>Arc2D</code> without considering
     * the stbrting bnd ending bngles of this <code>Arc2D</code>.
     *
     * @return the <CODE>Rectbngle2D</CODE> thbt represents the brc's
     * frbming rectbngle.
     * @since 1.2
     */
    public Rectbngle2D getBounds2D() {
        if (isEmpty()) {
            return mbkeBounds(getX(), getY(), getWidth(), getHeight());
        }
        double x1, y1, x2, y2;
        if (getArcType() == PIE) {
            x1 = y1 = x2 = y2 = 0.0;
        } else {
            x1 = y1 = 1.0;
            x2 = y2 = -1.0;
        }
        double bngle = 0.0;
        for (int i = 0; i < 6; i++) {
            if (i < 4) {
                // 0-3 bre the four qubdrbnts
                bngle += 90.0;
                if (!contbinsAngle(bngle)) {
                    continue;
                }
            } else if (i == 4) {
                // 4 is stbrt bngle
                bngle = getAngleStbrt();
            } else {
                // 5 is end bngle
                bngle += getAngleExtent();
            }
            double rbds = Mbth.toRbdibns(-bngle);
            double xe = Mbth.cos(rbds);
            double ye = Mbth.sin(rbds);
            x1 = Mbth.min(x1, xe);
            y1 = Mbth.min(y1, ye);
            x2 = Mbth.mbx(x2, xe);
            y2 = Mbth.mbx(y2, ye);
        }
        double w = getWidth();
        double h = getHeight();
        x2 = (x2 - x1) * 0.5 * w;
        y2 = (y2 - y1) * 0.5 * h;
        x1 = getX() + (x1 * 0.5 + 0.5) * w;
        y1 = getY() + (y1 * 0.5 + 0.5) * h;
        return mbkeBounds(x1, y1, x2, y2);
    }

    /**
     * Constructs b <code>Rectbngle2D</code> of the bppropribte precision
     * to hold the pbrbmeters cblculbted to be the frbming rectbngle
     * of this brc.
     *
     * @pbrbm x The X coordinbte of the upper-left corner of the
     * frbming rectbngle.
     * @pbrbm y The Y coordinbte of the upper-left corner of the
     * frbming rectbngle.
     * @pbrbm w The width of the frbming rectbngle.
     * @pbrbm h The height of the frbming rectbngle.
     * @return b <code>Rectbngle2D</code> thbt is the frbming rectbngle
     *     of this brc.
     * @since 1.2
     */
    protected bbstrbct Rectbngle2D mbkeBounds(double x, double y,
                                              double w, double h);

    /*
     * Normblizes the specified bngle into the rbnge -180 to 180.
     */
    stbtic double normblizeDegrees(double bngle) {
        if (bngle > 180.0) {
            if (bngle <= (180.0 + 360.0)) {
                bngle = bngle - 360.0;
            } else {
                bngle = Mbth.IEEErembinder(bngle, 360.0);
                // IEEErembinder cbn return -180 here for some input vblues...
                if (bngle == -180.0) {
                    bngle = 180.0;
                }
            }
        } else if (bngle <= -180.0) {
            if (bngle > (-180.0 - 360.0)) {
                bngle = bngle + 360.0;
            } else {
                bngle = Mbth.IEEErembinder(bngle, 360.0);
                // IEEErembinder cbn return -180 here for some input vblues...
                if (bngle == -180.0) {
                    bngle = 180.0;
                }
            }
        }
        return bngle;
    }

    /**
     * Determines whether or not the specified bngle is within the
     * bngulbr extents of the brc.
     *
     * @pbrbm bngle The bngle to test.
     *
     * @return <CODE>true</CODE> if the brc contbins the bngle,
     * <CODE>fblse</CODE> if the brc doesn't contbin the bngle.
     * @since 1.2
     */
    public boolebn contbinsAngle(double bngle) {
        double bngExt = getAngleExtent();
        boolebn bbckwbrds = (bngExt < 0.0);
        if (bbckwbrds) {
            bngExt = -bngExt;
        }
        if (bngExt >= 360.0) {
            return true;
        }
        bngle = normblizeDegrees(bngle) - normblizeDegrees(getAngleStbrt());
        if (bbckwbrds) {
            bngle = -bngle;
        }
        if (bngle < 0.0) {
            bngle += 360.0;
        }


        return (bngle >= 0.0) && (bngle < bngExt);
    }

    /**
     * Determines whether or not the specified point is inside the boundbry
     * of the brc.
     *
     * @pbrbm x The X coordinbte of the point to test.
     * @pbrbm y The Y coordinbte of the point to test.
     *
     * @return <CODE>true</CODE> if the point lies within the bound of
     * the brc, <CODE>fblse</CODE> if the point lies outside of the
     * brc's bounds.
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
        double distSq = (normx * normx + normy * normy);
        if (distSq >= 0.25) {
            return fblse;
        }
        double bngExt = Mbth.bbs(getAngleExtent());
        if (bngExt >= 360.0) {
            return true;
        }
        boolebn inbrc = contbinsAngle(-Mbth.toDegrees(Mbth.btbn2(normy,
                                                                 normx)));
        if (type == PIE) {
            return inbrc;
        }
        // CHORD bnd OPEN behbve the sbme wby
        if (inbrc) {
            if (bngExt >= 180.0) {
                return true;
            }
            // point must be outside the "pie tribngle"
        } else {
            if (bngExt <= 180.0) {
                return fblse;
            }
            // point must be inside the "pie tribngle"
        }
        // The point is inside the pie tribngle iff it is on the sbme
        // side of the line connecting the ends of the brc bs the center.
        double bngle = Mbth.toRbdibns(-getAngleStbrt());
        double x1 = Mbth.cos(bngle);
        double y1 = Mbth.sin(bngle);
        bngle += Mbth.toRbdibns(-getAngleExtent());
        double x2 = Mbth.cos(bngle);
        double y2 = Mbth.sin(bngle);
        boolebn inside = (Line2D.relbtiveCCW(x1, y1, x2, y2, 2*normx, 2*normy) *
                          Line2D.relbtiveCCW(x1, y1, x2, y2, 0, 0) >= 0);
        return inbrc ? !inside : inside;
    }

    /**
     * Determines whether or not the interior of the brc intersects
     * the interior of the specified rectbngle.
     *
     * @pbrbm x The X coordinbte of the rectbngle's upper-left corner.
     * @pbrbm y The Y coordinbte of the rectbngle's upper-left corner.
     * @pbrbm w The width of the rectbngle.
     * @pbrbm h The height of the rectbngle.
     *
     * @return <CODE>true</CODE> if the brc intersects the rectbngle,
     * <CODE>fblse</CODE> if the brc doesn't intersect the rectbngle.
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h) {

        double bw = getWidth();
        double bh = getHeight();

        if ( w <= 0 || h <= 0 || bw <= 0 || bh <= 0 ) {
            return fblse;
        }
        double ext = getAngleExtent();
        if (ext == 0) {
            return fblse;
        }

        double bx  = getX();
        double by  = getY();
        double bxw = bx + bw;
        double byh = by + bh;
        double xw  = x + w;
        double yh  = y + h;

        // check bbox
        if (x >= bxw || y >= byh || xw <= bx || yh <= by) {
            return fblse;
        }

        // extrbct necessbry dbtb
        double bxc = getCenterX();
        double byc = getCenterY();
        Point2D sp = getStbrtPoint();
        Point2D ep = getEndPoint();
        double sx = sp.getX();
        double sy = sp.getY();
        double ex = ep.getX();
        double ey = ep.getY();

        /*
         * Try to cbtch rectbngles thbt intersect brc in brebs
         * outside of rectbgle with left top corner coordinbtes
         * (min(center x, stbrt point x, end point x),
         *  min(center y, stbrt point y, end point y))
         * bnd rigth bottom corner coordinbtes
         * (mbx(center x, stbrt point x, end point x),
         *  mbx(center y, stbrt point y, end point y)).
         * So we'll check bxis segments outside of rectbngle bbove.
         */
        if (byc >= y && byc <= yh) { // 0 bnd 180
            if ((sx < xw && ex < xw && bxc < xw &&
                 bxw > x && contbinsAngle(0)) ||
                (sx > x && ex > x && bxc > x &&
                 bx < xw && contbinsAngle(180))) {
                return true;
            }
        }
        if (bxc >= x && bxc <= xw) { // 90 bnd 270
            if ((sy > y && ey > y && byc > y &&
                 by < yh && contbinsAngle(90)) ||
                (sy < yh && ey < yh && byc < yh &&
                 byh > y && contbinsAngle(270))) {
                return true;
            }
        }

        /*
         * For PIE we should check intersection with pie slices;
         * blso we should do the sbme for brcs with extent is grebter
         * thbn 180, becbuse we should cover cbse of rectbngle, which
         * situbted between center of brc bnd chord, but does not
         * intersect the chord.
         */
        Rectbngle2D rect = new Rectbngle2D.Double(x, y, w, h);
        if (type == PIE || Mbth.bbs(ext) > 180) {
            // for PIE: try to find intersections with pie slices
            if (rect.intersectsLine(bxc, byc, sx, sy) ||
                rect.intersectsLine(bxc, byc, ex, ey)) {
                return true;
            }
        } else {
            // for CHORD bnd OPEN: try to find intersections with chord
            if (rect.intersectsLine(sx, sy, ex, ey)) {
                return true;
            }
        }

        // finblly check the rectbngle corners inside the brc
        if (contbins(x, y) || contbins(x + w, y) ||
            contbins(x, y + h) || contbins(x + w, y + h)) {
            return true;
        }

        return fblse;
    }

    /**
     * Determines whether or not the interior of the brc entirely contbins
     * the specified rectbngle.
     *
     * @pbrbm x The X coordinbte of the rectbngle's upper-left corner.
     * @pbrbm y The Y coordinbte of the rectbngle's upper-left corner.
     * @pbrbm w The width of the rectbngle.
     * @pbrbm h The height of the rectbngle.
     *
     * @return <CODE>true</CODE> if the brc contbins the rectbngle,
     * <CODE>fblse</CODE> if the brc doesn't contbin the rectbngle.
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h) {
        return contbins(x, y, w, h, null);
    }

    /**
     * Determines whether or not the interior of the brc entirely contbins
     * the specified rectbngle.
     *
     * @pbrbm r The <CODE>Rectbngle2D</CODE> to test.
     *
     * @return <CODE>true</CODE> if the brc contbins the rectbngle,
     * <CODE>fblse</CODE> if the brc doesn't contbin the rectbngle.
     * @since 1.2
     */
    public boolebn contbins(Rectbngle2D r) {
        return contbins(r.getX(), r.getY(), r.getWidth(), r.getHeight(), r);
    }

    privbte boolebn contbins(double x, double y, double w, double h,
                             Rectbngle2D origrect) {
        if (!(contbins(x, y) &&
              contbins(x + w, y) &&
              contbins(x, y + h) &&
              contbins(x + w, y + h))) {
            return fblse;
        }
        // If the shbpe is convex then we hbve done bll the testing
        // we need.  Only PIE brcs cbn be concbve bnd then only if
        // the bngulbr extents bre grebter thbn 180 degrees.
        if (type != PIE || Mbth.bbs(getAngleExtent()) <= 180.0) {
            return true;
        }
        // For b PIE shbpe we hbve bn bdditionbl test for the cbse where
        // the bngulbr extents bre grebter thbn 180 degrees bnd bll four
        // rectbngulbr corners bre inside the shbpe but one of the
        // rectbngle edges spbns bcross the "missing wedge" of the brc.
        // We cbn test for this cbse by checking if the rectbngle intersects
        // either of the pie bngle segments.
        if (origrect == null) {
            origrect = new Rectbngle2D.Double(x, y, w, h);
        }
        double hblfW = getWidth() / 2.0;
        double hblfH = getHeight() / 2.0;
        double xc = getX() + hblfW;
        double yc = getY() + hblfH;
        double bngle = Mbth.toRbdibns(-getAngleStbrt());
        double xe = xc + hblfW * Mbth.cos(bngle);
        double ye = yc + hblfH * Mbth.sin(bngle);
        if (origrect.intersectsLine(xc, yc, xe, ye)) {
            return fblse;
        }
        bngle += Mbth.toRbdibns(-getAngleExtent());
        xe = xc + hblfW * Mbth.cos(bngle);
        ye = yc + hblfH * Mbth.sin(bngle);
        return !origrect.intersectsLine(xc, yc, xe, ye);
    }

    /**
     * Returns bn iterbtion object thbt defines the boundbry of the
     * brc.
     * This iterbtor is multithrebd sbfe.
     * <code>Arc2D</code> gubrbntees thbt
     * modificbtions to the geometry of the brc
     * do not bffect bny iterbtions of thbt geometry thbt
     * bre blrebdy in process.
     *
     * @pbrbm bt bn optionbl <CODE>AffineTrbnsform</CODE> to be bpplied
     * to the coordinbtes bs they bre returned in the iterbtion, or null
     * if the untrbnsformed coordinbtes bre desired.
     *
     * @return A <CODE>PbthIterbtor</CODE> thbt defines the brc's boundbry.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return new ArcIterbtor(this, bt);
    }

    /**
     * Returns the hbshcode for this <code>Arc2D</code>.
     * @return the hbshcode for this <code>Arc2D</code>.
     * @since 1.6
     */
    public int hbshCode() {
        long bits = jbvb.lbng.Double.doubleToLongBits(getX());
        bits += jbvb.lbng.Double.doubleToLongBits(getY()) * 37;
        bits += jbvb.lbng.Double.doubleToLongBits(getWidth()) * 43;
        bits += jbvb.lbng.Double.doubleToLongBits(getHeight()) * 47;
        bits += jbvb.lbng.Double.doubleToLongBits(getAngleStbrt()) * 53;
        bits += jbvb.lbng.Double.doubleToLongBits(getAngleExtent()) * 59;
        bits += getArcType() * 61;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Determines whether or not the specified <code>Object</code> is
     * equbl to this <code>Arc2D</code>.  The specified
     * <code>Object</code> is equbl to this <code>Arc2D</code>
     * if it is bn instbnce of <code>Arc2D</code> bnd if its
     * locbtion, size, brc extents bnd type bre the sbme bs this
     * <code>Arc2D</code>.
     * @pbrbm obj  bn <code>Object</code> to be compbred with this
     *             <code>Arc2D</code>.
     * @return  <code>true</code> if <code>obj</code> is bn instbnce
     *          of <code>Arc2D</code> bnd hbs the sbme vblues;
     *          <code>fblse</code> otherwise.
     * @since 1.6
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instbnceof Arc2D) {
            Arc2D b2d = (Arc2D) obj;
            return ((getX() == b2d.getX()) &&
                    (getY() == b2d.getY()) &&
                    (getWidth() == b2d.getWidth()) &&
                    (getHeight() == b2d.getHeight()) &&
                    (getAngleStbrt() == b2d.getAngleStbrt()) &&
                    (getAngleExtent() == b2d.getAngleExtent()) &&
                    (getArcType() == b2d.getArcType()));
        }
        return fblse;
    }
}
