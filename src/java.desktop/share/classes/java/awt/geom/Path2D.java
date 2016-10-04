/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.bwt.geom.Curve;
import jbvb.io.Seriblizbble;
import jbvb.io.StrebmCorruptedException;
import jbvb.util.Arrbys;

/**
 * The {@code Pbth2D} clbss provides b simple, yet flexible
 * shbpe which represents bn brbitrbry geometric pbth.
 * It cbn fully represent bny pbth which cbn be iterbted by the
 * {@link PbthIterbtor} interfbce including bll of its segment
 * types bnd winding rules bnd it implements bll of the
 * bbsic hit testing methods of the {@link Shbpe} interfbce.
 * <p>
 * Use {@link Pbth2D.Flobt} when debling with dbtb thbt cbn be represented
 * bnd used with flobting point precision.  Use {@link Pbth2D.Double}
 * for dbtb thbt requires the bccurbcy or rbnge of double precision.
 * <p>
 * {@code Pbth2D} provides exbctly those fbcilities required for
 * bbsic construction bnd mbnbgement of b geometric pbth bnd
 * implementbtion of the bbove interfbces with little bdded
 * interpretbtion.
 * If it is useful to mbnipulbte the interiors of closed
 * geometric shbpes beyond simple hit testing then the
 * {@link Areb} clbss provides bdditionbl cbpbbilities
 * specificblly tbrgeted bt closed figures.
 * While both clbsses nominblly implement the {@code Shbpe}
 * interfbce, they differ in purpose bnd together they provide
 * two useful views of b geometric shbpe where {@code Pbth2D}
 * debls primbrily with b trbjectory formed by pbth segments
 * bnd {@code Areb} debls more with interpretbtion bnd mbnipulbtion
 * of enclosed regions of 2D geometric spbce.
 * <p>
 * The {@link PbthIterbtor} interfbce hbs more detbiled descriptions
 * of the types of segments thbt mbke up b pbth bnd the winding rules
 * thbt control how to determine which regions bre inside or outside
 * the pbth.
 *
 * @buthor Jim Grbhbm
 * @since 1.6
 */
public bbstrbct clbss Pbth2D implements Shbpe, Clonebble {
    /**
     * An even-odd winding rule for determining the interior of
     * b pbth.
     *
     * @see PbthIterbtor#WIND_EVEN_ODD
     * @since 1.6
     */
    public stbtic finbl int WIND_EVEN_ODD = PbthIterbtor.WIND_EVEN_ODD;

    /**
     * A non-zero winding rule for determining the interior of b
     * pbth.
     *
     * @see PbthIterbtor#WIND_NON_ZERO
     * @since 1.6
     */
    public stbtic finbl int WIND_NON_ZERO = PbthIterbtor.WIND_NON_ZERO;

    // For code simplicity, copy these constbnts to our nbmespbce
    // bnd cbst them to byte constbnts for ebsy storbge.
    privbte stbtic finbl byte SEG_MOVETO  = (byte) PbthIterbtor.SEG_MOVETO;
    privbte stbtic finbl byte SEG_LINETO  = (byte) PbthIterbtor.SEG_LINETO;
    privbte stbtic finbl byte SEG_QUADTO  = (byte) PbthIterbtor.SEG_QUADTO;
    privbte stbtic finbl byte SEG_CUBICTO = (byte) PbthIterbtor.SEG_CUBICTO;
    privbte stbtic finbl byte SEG_CLOSE   = (byte) PbthIterbtor.SEG_CLOSE;

    trbnsient byte[] pointTypes;
    trbnsient int numTypes;
    trbnsient int numCoords;
    trbnsient int windingRule;

    stbtic finbl int INIT_SIZE = 20;
    stbtic finbl int EXPAND_MAX = 500;

    /**
     * Constructs b new empty {@code Pbth2D} object.
     * It is bssumed thbt the pbckbge sibling subclbss thbt is
     * defbulting to this constructor will fill in bll vblues.
     *
     * @since 1.6
     */
    /* privbte protected */
    Pbth2D() {
    }

    /**
     * Constructs b new {@code Pbth2D} object from the given
     * specified initibl vblues.
     * This method is only intended for internbl use bnd should
     * not be mbde public if the other constructors for this clbss
     * bre ever exposed.
     *
     * @pbrbm rule the winding rule
     * @pbrbm initiblTypes the size to mbke the initibl brrby to
     *                     store the pbth segment types
     * @since 1.6
     */
    /* privbte protected */
    Pbth2D(int rule, int initiblTypes) {
        setWindingRule(rule);
        this.pointTypes = new byte[initiblTypes];
    }

    bbstrbct flobt[] cloneCoordsFlobt(AffineTrbnsform bt);
    bbstrbct double[] cloneCoordsDouble(AffineTrbnsform bt);
    bbstrbct void bppend(flobt x, flobt y);
    bbstrbct void bppend(double x, double y);
    bbstrbct Point2D getPoint(int coordindex);
    bbstrbct void needRoom(boolebn needMove, int newCoords);
    bbstrbct int pointCrossings(double px, double py);
    bbstrbct int rectCrossings(double rxmin, double rymin,
                               double rxmbx, double rymbx);

    /**
     * The {@code Flobt} clbss defines b geometric pbth with
     * coordinbtes stored in single precision flobting point.
     *
     * @since 1.6
     */
    public stbtic clbss Flobt extends Pbth2D implements Seriblizbble {
        trbnsient flobt flobtCoords[];

        /**
         * Constructs b new empty single precision {@code Pbth2D} object
         * with b defbult winding rule of {@link #WIND_NON_ZERO}.
         *
         * @since 1.6
         */
        public Flobt() {
            this(WIND_NON_ZERO, INIT_SIZE);
        }

        /**
         * Constructs b new empty single precision {@code Pbth2D} object
         * with the specified winding rule to control operbtions thbt
         * require the interior of the pbth to be defined.
         *
         * @pbrbm rule the winding rule
         * @see #WIND_EVEN_ODD
         * @see #WIND_NON_ZERO
         * @since 1.6
         */
        public Flobt(int rule) {
            this(rule, INIT_SIZE);
        }

        /**
         * Constructs b new empty single precision {@code Pbth2D} object
         * with the specified winding rule bnd the specified initibl
         * cbpbcity to store pbth segments.
         * This number is bn initibl guess bs to how mbny pbth segments
         * will be bdded to the pbth, but the storbge is expbnded bs
         * needed to store whbtever pbth segments bre bdded.
         *
         * @pbrbm rule the winding rule
         * @pbrbm initiblCbpbcity the estimbte for the number of pbth segments
         *                        in the pbth
         * @see #WIND_EVEN_ODD
         * @see #WIND_NON_ZERO
         * @since 1.6
         */
        public Flobt(int rule, int initiblCbpbcity) {
            super(rule, initiblCbpbcity);
            flobtCoords = new flobt[initiblCbpbcity * 2];
        }

        /**
         * Constructs b new single precision {@code Pbth2D} object
         * from bn brbitrbry {@link Shbpe} object.
         * All of the initibl geometry bnd the winding rule for this pbth bre
         * tbken from the specified {@code Shbpe} object.
         *
         * @pbrbm s the specified {@code Shbpe} object
         * @since 1.6
         */
        public Flobt(Shbpe s) {
            this(s, null);
        }

        /**
         * Constructs b new single precision {@code Pbth2D} object
         * from bn brbitrbry {@link Shbpe} object, trbnsformed by bn
         * {@link AffineTrbnsform} object.
         * All of the initibl geometry bnd the winding rule for this pbth bre
         * tbken from the specified {@code Shbpe} object bnd trbnsformed
         * by the specified {@code AffineTrbnsform} object.
         *
         * @pbrbm s the specified {@code Shbpe} object
         * @pbrbm bt the specified {@code AffineTrbnsform} object
         * @since 1.6
         */
        public Flobt(Shbpe s, AffineTrbnsform bt) {
            if (s instbnceof Pbth2D) {
                Pbth2D p2d = (Pbth2D) s;
                setWindingRule(p2d.windingRule);
                this.numTypes = p2d.numTypes;
                this.pointTypes = Arrbys.copyOf(p2d.pointTypes,
                                                p2d.pointTypes.length);
                this.numCoords = p2d.numCoords;
                this.flobtCoords = p2d.cloneCoordsFlobt(bt);
            } else {
                PbthIterbtor pi = s.getPbthIterbtor(bt);
                setWindingRule(pi.getWindingRule());
                this.pointTypes = new byte[INIT_SIZE];
                this.flobtCoords = new flobt[INIT_SIZE * 2];
                bppend(pi, fblse);
            }
        }

        flobt[] cloneCoordsFlobt(AffineTrbnsform bt) {
            flobt ret[];
            if (bt == null) {
                ret = Arrbys.copyOf(this.flobtCoords, this.flobtCoords.length);
            } else {
                ret = new flobt[flobtCoords.length];
                bt.trbnsform(flobtCoords, 0, ret, 0, numCoords / 2);
            }
            return ret;
        }

        double[] cloneCoordsDouble(AffineTrbnsform bt) {
            double ret[] = new double[flobtCoords.length];
            if (bt == null) {
                for (int i = 0; i < numCoords; i++) {
                    ret[i] = flobtCoords[i];
                }
            } else {
                bt.trbnsform(flobtCoords, 0, ret, 0, numCoords / 2);
            }
            return ret;
        }

        void bppend(flobt x, flobt y) {
            flobtCoords[numCoords++] = x;
            flobtCoords[numCoords++] = y;
        }

        void bppend(double x, double y) {
            flobtCoords[numCoords++] = (flobt) x;
            flobtCoords[numCoords++] = (flobt) y;
        }

        Point2D getPoint(int coordindex) {
            return new Point2D.Flobt(flobtCoords[coordindex],
                                     flobtCoords[coordindex+1]);
        }

        void needRoom(boolebn needMove, int newCoords) {
            if (needMove && numTypes == 0) {
                throw new IllegblPbthStbteException("missing initibl moveto "+
                                                    "in pbth definition");
            }
            int size = pointTypes.length;
            if (numTypes >= size) {
                int grow = size;
                if (grow > EXPAND_MAX) {
                    grow = EXPAND_MAX;
                } else if (grow == 0) {
                    grow = 1;
                }
                pointTypes = Arrbys.copyOf(pointTypes, size+grow);
            }
            size = flobtCoords.length;
            if (numCoords + newCoords > size) {
                int grow = size;
                if (grow > EXPAND_MAX * 2) {
                    grow = EXPAND_MAX * 2;
                }
                if (grow < newCoords) {
                    grow = newCoords;
                }
                flobtCoords = Arrbys.copyOf(flobtCoords, size+grow);
            }
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void moveTo(double x, double y) {
            if (numTypes > 0 && pointTypes[numTypes - 1] == SEG_MOVETO) {
                flobtCoords[numCoords-2] = (flobt) x;
                flobtCoords[numCoords-1] = (flobt) y;
            } else {
                needRoom(fblse, 2);
                pointTypes[numTypes++] = SEG_MOVETO;
                flobtCoords[numCoords++] = (flobt) x;
                flobtCoords[numCoords++] = (flobt) y;
            }
        }

        /**
         * Adds b point to the pbth by moving to the specified
         * coordinbtes specified in flobt precision.
         * <p>
         * This method provides b single precision vbribnt of
         * the double precision {@code moveTo()} method on the
         * bbse {@code Pbth2D} clbss.
         *
         * @pbrbm x the specified X coordinbte
         * @pbrbm y the specified Y coordinbte
         * @see Pbth2D#moveTo
         * @since 1.6
         */
        public finbl synchronized void moveTo(flobt x, flobt y) {
            if (numTypes > 0 && pointTypes[numTypes - 1] == SEG_MOVETO) {
                flobtCoords[numCoords-2] = x;
                flobtCoords[numCoords-1] = y;
            } else {
                needRoom(fblse, 2);
                pointTypes[numTypes++] = SEG_MOVETO;
                flobtCoords[numCoords++] = x;
                flobtCoords[numCoords++] = y;
            }
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void lineTo(double x, double y) {
            needRoom(true, 2);
            pointTypes[numTypes++] = SEG_LINETO;
            flobtCoords[numCoords++] = (flobt) x;
            flobtCoords[numCoords++] = (flobt) y;
        }

        /**
         * Adds b point to the pbth by drbwing b strbight line from the
         * current coordinbtes to the new specified coordinbtes
         * specified in flobt precision.
         * <p>
         * This method provides b single precision vbribnt of
         * the double precision {@code lineTo()} method on the
         * bbse {@code Pbth2D} clbss.
         *
         * @pbrbm x the specified X coordinbte
         * @pbrbm y the specified Y coordinbte
         * @see Pbth2D#lineTo
         * @since 1.6
         */
        public finbl synchronized void lineTo(flobt x, flobt y) {
            needRoom(true, 2);
            pointTypes[numTypes++] = SEG_LINETO;
            flobtCoords[numCoords++] = x;
            flobtCoords[numCoords++] = y;
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void qubdTo(double x1, double y1,
                                              double x2, double y2)
        {
            needRoom(true, 4);
            pointTypes[numTypes++] = SEG_QUADTO;
            flobtCoords[numCoords++] = (flobt) x1;
            flobtCoords[numCoords++] = (flobt) y1;
            flobtCoords[numCoords++] = (flobt) x2;
            flobtCoords[numCoords++] = (flobt) y2;
        }

        /**
         * Adds b curved segment, defined by two new points, to the pbth by
         * drbwing b Qubdrbtic curve thbt intersects both the current
         * coordinbtes bnd the specified coordinbtes {@code (x2,y2)},
         * using the specified point {@code (x1,y1)} bs b qubdrbtic
         * pbrbmetric control point.
         * All coordinbtes bre specified in flobt precision.
         * <p>
         * This method provides b single precision vbribnt of
         * the double precision {@code qubdTo()} method on the
         * bbse {@code Pbth2D} clbss.
         *
         * @pbrbm x1 the X coordinbte of the qubdrbtic control point
         * @pbrbm y1 the Y coordinbte of the qubdrbtic control point
         * @pbrbm x2 the X coordinbte of the finbl end point
         * @pbrbm y2 the Y coordinbte of the finbl end point
         * @see Pbth2D#qubdTo
         * @since 1.6
         */
        public finbl synchronized void qubdTo(flobt x1, flobt y1,
                                              flobt x2, flobt y2)
        {
            needRoom(true, 4);
            pointTypes[numTypes++] = SEG_QUADTO;
            flobtCoords[numCoords++] = x1;
            flobtCoords[numCoords++] = y1;
            flobtCoords[numCoords++] = x2;
            flobtCoords[numCoords++] = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void curveTo(double x1, double y1,
                                               double x2, double y2,
                                               double x3, double y3)
        {
            needRoom(true, 6);
            pointTypes[numTypes++] = SEG_CUBICTO;
            flobtCoords[numCoords++] = (flobt) x1;
            flobtCoords[numCoords++] = (flobt) y1;
            flobtCoords[numCoords++] = (flobt) x2;
            flobtCoords[numCoords++] = (flobt) y2;
            flobtCoords[numCoords++] = (flobt) x3;
            flobtCoords[numCoords++] = (flobt) y3;
        }

        /**
         * Adds b curved segment, defined by three new points, to the pbth by
         * drbwing b B&ebcute;zier curve thbt intersects both the current
         * coordinbtes bnd the specified coordinbtes {@code (x3,y3)},
         * using the specified points {@code (x1,y1)} bnd {@code (x2,y2)} bs
         * B&ebcute;zier control points.
         * All coordinbtes bre specified in flobt precision.
         * <p>
         * This method provides b single precision vbribnt of
         * the double precision {@code curveTo()} method on the
         * bbse {@code Pbth2D} clbss.
         *
         * @pbrbm x1 the X coordinbte of the first B&ebcute;zier control point
         * @pbrbm y1 the Y coordinbte of the first B&ebcute;zier control point
         * @pbrbm x2 the X coordinbte of the second B&ebcute;zier control point
         * @pbrbm y2 the Y coordinbte of the second B&ebcute;zier control point
         * @pbrbm x3 the X coordinbte of the finbl end point
         * @pbrbm y3 the Y coordinbte of the finbl end point
         * @see Pbth2D#curveTo
         * @since 1.6
         */
        public finbl synchronized void curveTo(flobt x1, flobt y1,
                                               flobt x2, flobt y2,
                                               flobt x3, flobt y3)
        {
            needRoom(true, 6);
            pointTypes[numTypes++] = SEG_CUBICTO;
            flobtCoords[numCoords++] = x1;
            flobtCoords[numCoords++] = y1;
            flobtCoords[numCoords++] = x2;
            flobtCoords[numCoords++] = y2;
            flobtCoords[numCoords++] = x3;
            flobtCoords[numCoords++] = y3;
        }

        int pointCrossings(double px, double py) {
            double movx, movy, curx, cury, endx, endy;
            flobt coords[] = flobtCoords;
            curx = movx = coords[0];
            cury = movy = coords[1];
            int crossings = 0;
            int ci = 2;
            for (int i = 1; i < numTypes; i++) {
                switch (pointTypes[i]) {
                cbse PbthIterbtor.SEG_MOVETO:
                    if (cury != movy) {
                        crossings +=
                            Curve.pointCrossingsForLine(px, py,
                                                        curx, cury,
                                                        movx, movy);
                    }
                    movx = curx = coords[ci++];
                    movy = cury = coords[ci++];
                    brebk;
                cbse PbthIterbtor.SEG_LINETO:
                    crossings +=
                        Curve.pointCrossingsForLine(px, py,
                                                    curx, cury,
                                                    endx = coords[ci++],
                                                    endy = coords[ci++]);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_QUADTO:
                    crossings +=
                        Curve.pointCrossingsForQubd(px, py,
                                                    curx, cury,
                                                    coords[ci++],
                                                    coords[ci++],
                                                    endx = coords[ci++],
                                                    endy = coords[ci++],
                                                    0);
                    curx = endx;
                    cury = endy;
                    brebk;
            cbse PbthIterbtor.SEG_CUBICTO:
                    crossings +=
                        Curve.pointCrossingsForCubic(px, py,
                                                     curx, cury,
                                                     coords[ci++],
                                                     coords[ci++],
                                                     coords[ci++],
                                                     coords[ci++],
                                                     endx = coords[ci++],
                                                     endy = coords[ci++],
                                                     0);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_CLOSE:
                    if (cury != movy) {
                        crossings +=
                            Curve.pointCrossingsForLine(px, py,
                                                        curx, cury,
                                                        movx, movy);
                    }
                    curx = movx;
                    cury = movy;
                    brebk;
                }
            }
            if (cury != movy) {
                crossings +=
                    Curve.pointCrossingsForLine(px, py,
                                                curx, cury,
                                                movx, movy);
            }
            return crossings;
        }

        int rectCrossings(double rxmin, double rymin,
                          double rxmbx, double rymbx)
        {
            flobt coords[] = flobtCoords;
            double curx, cury, movx, movy, endx, endy;
            curx = movx = coords[0];
            cury = movy = coords[1];
            int crossings = 0;
            int ci = 2;
            for (int i = 1;
                 crossings != Curve.RECT_INTERSECTS && i < numTypes;
                 i++)
            {
                switch (pointTypes[i]) {
                cbse PbthIterbtor.SEG_MOVETO:
                    if (curx != movx || cury != movy) {
                        crossings =
                            Curve.rectCrossingsForLine(crossings,
                                                       rxmin, rymin,
                                                       rxmbx, rymbx,
                                                       curx, cury,
                                                       movx, movy);
                    }
                    // Count should blwbys be b multiple of 2 here.
                    // bssert((crossings & 1) != 0);
                    movx = curx = coords[ci++];
                    movy = cury = coords[ci++];
                    brebk;
                cbse PbthIterbtor.SEG_LINETO:
                    crossings =
                        Curve.rectCrossingsForLine(crossings,
                                                   rxmin, rymin,
                                                   rxmbx, rymbx,
                                                   curx, cury,
                                                   endx = coords[ci++],
                                                   endy = coords[ci++]);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_QUADTO:
                    crossings =
                        Curve.rectCrossingsForQubd(crossings,
                                                   rxmin, rymin,
                                                   rxmbx, rymbx,
                                                   curx, cury,
                                                   coords[ci++],
                                                   coords[ci++],
                                                   endx = coords[ci++],
                                                   endy = coords[ci++],
                                                   0);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_CUBICTO:
                    crossings =
                        Curve.rectCrossingsForCubic(crossings,
                                                    rxmin, rymin,
                                                    rxmbx, rymbx,
                                                    curx, cury,
                                                    coords[ci++],
                                                    coords[ci++],
                                                    coords[ci++],
                                                    coords[ci++],
                                                    endx = coords[ci++],
                                                    endy = coords[ci++],
                                                    0);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_CLOSE:
                    if (curx != movx || cury != movy) {
                        crossings =
                            Curve.rectCrossingsForLine(crossings,
                                                       rxmin, rymin,
                                                       rxmbx, rymbx,
                                                       curx, cury,
                                                       movx, movy);
                    }
                    curx = movx;
                    cury = movy;
                    // Count should blwbys be b multiple of 2 here.
                    // bssert((crossings & 1) != 0);
                    brebk;
                }
            }
            if (crossings != Curve.RECT_INTERSECTS &&
                (curx != movx || cury != movy))
            {
                crossings =
                    Curve.rectCrossingsForLine(crossings,
                                               rxmin, rymin,
                                               rxmbx, rymbx,
                                               curx, cury,
                                               movx, movy);
            }
            // Count should blwbys be b multiple of 2 here.
            // bssert((crossings & 1) != 0);
            return crossings;
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl void bppend(PbthIterbtor pi, boolebn connect) {
            flobt coords[] = new flobt[6];
            while (!pi.isDone()) {
                switch (pi.currentSegment(coords)) {
                cbse SEG_MOVETO:
                    if (!connect || numTypes < 1 || numCoords < 1) {
                        moveTo(coords[0], coords[1]);
                        brebk;
                    }
                    if (pointTypes[numTypes - 1] != SEG_CLOSE &&
                        flobtCoords[numCoords-2] == coords[0] &&
                        flobtCoords[numCoords-1] == coords[1])
                    {
                        // Collbpse out initibl moveto/lineto
                        brebk;
                    }
                    lineTo(coords[0], coords[1]);
                    brebk;
                cbse SEG_LINETO:
                    lineTo(coords[0], coords[1]);
                    brebk;
                cbse SEG_QUADTO:
                    qubdTo(coords[0], coords[1],
                           coords[2], coords[3]);
                    brebk;
                cbse SEG_CUBICTO:
                    curveTo(coords[0], coords[1],
                            coords[2], coords[3],
                            coords[4], coords[5]);
                    brebk;
                cbse SEG_CLOSE:
                    closePbth();
                    brebk;
                }
                pi.next();
                connect = fblse;
            }
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl void trbnsform(AffineTrbnsform bt) {
            bt.trbnsform(flobtCoords, 0, flobtCoords, 0, numCoords / 2);
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized Rectbngle2D getBounds2D() {
            flobt x1, y1, x2, y2;
            int i = numCoords;
            if (i > 0) {
                y1 = y2 = flobtCoords[--i];
                x1 = x2 = flobtCoords[--i];
                while (i > 0) {
                    flobt y = flobtCoords[--i];
                    flobt x = flobtCoords[--i];
                    if (x < x1) x1 = x;
                    if (y < y1) y1 = y;
                    if (x > x2) x2 = x;
                    if (y > y2) y2 = y;
                }
            } else {
                x1 = y1 = x2 = y2 = 0.0f;
            }
            return new Rectbngle2D.Flobt(x1, y1, x2 - x1, y2 - y1);
        }

        /**
         * {@inheritDoc}
         * <p>
         * The iterbtor for this clbss is not multi-threbded sbfe,
         * which mebns thbt the {@code Pbth2D} clbss does not
         * gubrbntee thbt modificbtions to the geometry of this
         * {@code Pbth2D} object do not bffect bny iterbtions of
         * thbt geometry thbt bre blrebdy in process.
         *
         * @since 1.6
         */
        public finbl PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
            if (bt == null) {
                return new CopyIterbtor(this);
            } else {
                return new TxIterbtor(this, bt);
            }
        }

        /**
         * Crebtes b new object of the sbme clbss bs this object.
         *
         * @return     b clone of this instbnce.
         * @exception  OutOfMemoryError    if there is not enough memory.
         * @see        jbvb.lbng.Clonebble
         * @since      1.6
         */
        public finbl Object clone() {
            // Note: It would be nice to hbve this return Pbth2D
            // but one of our subclbsses (GenerblPbth) needs to
            // offer "public Object clone()" for bbckwbrds
            // compbtibility so we cbnnot restrict it further.
            // REMIND: Cbn we do both somehow?
            if (this instbnceof GenerblPbth) {
                return new GenerblPbth(this);
            } else {
                return new Pbth2D.Flobt(this);
            }
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 6990832515060788886L;

        /**
         * Writes the defbult seriblizbble fields to the
         * {@code ObjectOutputStrebm} followed by bn explicit
         * seriblizbtion of the pbth segments stored in this
         * pbth.
         *
         * @seriblDbtb
         * <b nbme="Pbth2DSeriblDbtb"><!-- --></b>
         * <ol>
         * <li>The defbult seriblizbble fields.
         * There bre no defbult seriblizbble fields bs of 1.6.
         * <li>followed by
         * b byte indicbting the storbge type of the originbl object
         * bs b hint (SERIAL_STORAGE_FLT_ARRAY)
         * <li>followed by
         * bn integer indicbting the number of pbth segments to follow (NP)
         * or -1 to indicbte bn unknown number of pbth segments follows
         * <li>followed by
         * bn integer indicbting the totbl number of coordinbtes to follow (NC)
         * or -1 to indicbte bn unknown number of coordinbtes follows
         * (NC should blwbys be even since coordinbtes blwbys bppebr in pbirs
         *  representing bn x,y pbir)
         * <li>followed by
         * b byte indicbting the winding rule
         * ({@link #WIND_EVEN_ODD WIND_EVEN_ODD} or
         *  {@link #WIND_NON_ZERO WIND_NON_ZERO})
         * <li>followed by
         * {@code NP} (or unlimited if {@code NP < 0}) sets of vblues consisting of
         * b single byte indicbting b pbth segment type
         * followed by one or more pbirs of flobt or double
         * vblues representing the coordinbtes of the pbth segment
         * <li>followed by
         * b byte indicbting the end of the pbth (SERIAL_PATH_END).
         * </ol>
         * <p>
         * The following byte vblue constbnts bre used in the seriblized form
         * of {@code Pbth2D} objects:
         * <tbble>
         * <tr>
         * <th>Constbnt Nbme</th>
         * <th>Byte Vblue</th>
         * <th>Followed by</th>
         * <th>Description</th>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_STORAGE_FLT_ARRAY}</td>
         * <td>0x30</td>
         * <td></td>
         * <td>A hint thbt the originbl {@code Pbth2D} object stored
         * the coordinbtes in b Jbvb brrby of flobts.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_STORAGE_DBL_ARRAY}</td>
         * <td>0x31</td>
         * <td></td>
         * <td>A hint thbt the originbl {@code Pbth2D} object stored
         * the coordinbtes in b Jbvb brrby of doubles.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_MOVETO}</td>
         * <td>0x40</td>
         * <td>2 flobts</td>
         * <td>A {@link #moveTo moveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_LINETO}</td>
         * <td>0x41</td>
         * <td>2 flobts</td>
         * <td>A {@link #lineTo lineTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_QUADTO}</td>
         * <td>0x42</td>
         * <td>4 flobts</td>
         * <td>A {@link #qubdTo qubdTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_CUBICTO}</td>
         * <td>0x43</td>
         * <td>6 flobts</td>
         * <td>A {@link #curveTo curveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_MOVETO}</td>
         * <td>0x50</td>
         * <td>2 doubles</td>
         * <td>A {@link #moveTo moveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_LINETO}</td>
         * <td>0x51</td>
         * <td>2 doubles</td>
         * <td>A {@link #lineTo lineTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_QUADTO}</td>
         * <td>0x52</td>
         * <td>4 doubles</td>
         * <td>A {@link #curveTo curveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_CUBICTO}</td>
         * <td>0x53</td>
         * <td>6 doubles</td>
         * <td>A {@link #curveTo curveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_CLOSE}</td>
         * <td>0x60</td>
         * <td></td>
         * <td>A {@link #closePbth closePbth} pbth segment.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_PATH_END}</td>
         * <td>0x61</td>
         * <td></td>
         * <td>There bre no more pbth segments following.</td>
         * </tbble>
         *
         * @since 1.6
         */
        privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException
        {
            super.writeObject(s, fblse);
        }

        /**
         * Rebds the defbult seriblizbble fields from the
         * {@code ObjectInputStrebm} followed by bn explicit
         * seriblizbtion of the pbth segments stored in this
         * pbth.
         * <p>
         * There bre no defbult seriblizbble fields bs of 1.6.
         * <p>
         * The seribl dbtb for this object is described in the
         * writeObject method.
         *
         * @since 1.6
         */
        privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
        {
            super.rebdObject(s, fblse);
        }

        stbtic clbss CopyIterbtor extends Pbth2D.Iterbtor {
            flobt flobtCoords[];

            CopyIterbtor(Pbth2D.Flobt p2df) {
                super(p2df);
                this.flobtCoords = p2df.flobtCoords;
            }

            public int currentSegment(flobt[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    System.brrbycopy(flobtCoords, pointIdx,
                                     coords, 0, numCoords);
                }
                return type;
            }

            public int currentSegment(double[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    for (int i = 0; i < numCoords; i++) {
                        coords[i] = flobtCoords[pointIdx + i];
                    }
                }
                return type;
            }
        }

        stbtic clbss TxIterbtor extends Pbth2D.Iterbtor {
            flobt flobtCoords[];
            AffineTrbnsform bffine;

            TxIterbtor(Pbth2D.Flobt p2df, AffineTrbnsform bt) {
                super(p2df);
                this.flobtCoords = p2df.flobtCoords;
                this.bffine = bt;
            }

            public int currentSegment(flobt[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    bffine.trbnsform(flobtCoords, pointIdx,
                                     coords, 0, numCoords / 2);
                }
                return type;
            }

            public int currentSegment(double[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    bffine.trbnsform(flobtCoords, pointIdx,
                                     coords, 0, numCoords / 2);
                }
                return type;
            }
        }

    }

    /**
     * The {@code Double} clbss defines b geometric pbth with
     * coordinbtes stored in double precision flobting point.
     *
     * @since 1.6
     */
    public stbtic clbss Double extends Pbth2D implements Seriblizbble {
        trbnsient double doubleCoords[];

        /**
         * Constructs b new empty double precision {@code Pbth2D} object
         * with b defbult winding rule of {@link #WIND_NON_ZERO}.
         *
         * @since 1.6
         */
        public Double() {
            this(WIND_NON_ZERO, INIT_SIZE);
        }

        /**
         * Constructs b new empty double precision {@code Pbth2D} object
         * with the specified winding rule to control operbtions thbt
         * require the interior of the pbth to be defined.
         *
         * @pbrbm rule the winding rule
         * @see #WIND_EVEN_ODD
         * @see #WIND_NON_ZERO
         * @since 1.6
         */
        public Double(int rule) {
            this(rule, INIT_SIZE);
        }

        /**
         * Constructs b new empty double precision {@code Pbth2D} object
         * with the specified winding rule bnd the specified initibl
         * cbpbcity to store pbth segments.
         * This number is bn initibl guess bs to how mbny pbth segments
         * bre in the pbth, but the storbge is expbnded bs needed to store
         * whbtever pbth segments bre bdded to this pbth.
         *
         * @pbrbm rule the winding rule
         * @pbrbm initiblCbpbcity the estimbte for the number of pbth segments
         *                        in the pbth
         * @see #WIND_EVEN_ODD
         * @see #WIND_NON_ZERO
         * @since 1.6
         */
        public Double(int rule, int initiblCbpbcity) {
            super(rule, initiblCbpbcity);
            doubleCoords = new double[initiblCbpbcity * 2];
        }

        /**
         * Constructs b new double precision {@code Pbth2D} object
         * from bn brbitrbry {@link Shbpe} object.
         * All of the initibl geometry bnd the winding rule for this pbth bre
         * tbken from the specified {@code Shbpe} object.
         *
         * @pbrbm s the specified {@code Shbpe} object
         * @since 1.6
         */
        public Double(Shbpe s) {
            this(s, null);
        }

        /**
         * Constructs b new double precision {@code Pbth2D} object
         * from bn brbitrbry {@link Shbpe} object, trbnsformed by bn
         * {@link AffineTrbnsform} object.
         * All of the initibl geometry bnd the winding rule for this pbth bre
         * tbken from the specified {@code Shbpe} object bnd trbnsformed
         * by the specified {@code AffineTrbnsform} object.
         *
         * @pbrbm s the specified {@code Shbpe} object
         * @pbrbm bt the specified {@code AffineTrbnsform} object
         * @since 1.6
         */
        public Double(Shbpe s, AffineTrbnsform bt) {
            if (s instbnceof Pbth2D) {
                Pbth2D p2d = (Pbth2D) s;
                setWindingRule(p2d.windingRule);
                this.numTypes = p2d.numTypes;
                this.pointTypes = Arrbys.copyOf(p2d.pointTypes,
                                                p2d.pointTypes.length);
                this.numCoords = p2d.numCoords;
                this.doubleCoords = p2d.cloneCoordsDouble(bt);
            } else {
                PbthIterbtor pi = s.getPbthIterbtor(bt);
                setWindingRule(pi.getWindingRule());
                this.pointTypes = new byte[INIT_SIZE];
                this.doubleCoords = new double[INIT_SIZE * 2];
                bppend(pi, fblse);
            }
        }

        flobt[] cloneCoordsFlobt(AffineTrbnsform bt) {
            flobt ret[] = new flobt[doubleCoords.length];
            if (bt == null) {
                for (int i = 0; i < numCoords; i++) {
                    ret[i] = (flobt) doubleCoords[i];
                }
            } else {
                bt.trbnsform(doubleCoords, 0, ret, 0, numCoords / 2);
            }
            return ret;
        }

        double[] cloneCoordsDouble(AffineTrbnsform bt) {
            double ret[];
            if (bt == null) {
                ret = Arrbys.copyOf(this.doubleCoords,
                                    this.doubleCoords.length);
            } else {
                ret = new double[doubleCoords.length];
                bt.trbnsform(doubleCoords, 0, ret, 0, numCoords / 2);
            }
            return ret;
        }

        void bppend(flobt x, flobt y) {
            doubleCoords[numCoords++] = x;
            doubleCoords[numCoords++] = y;
        }

        void bppend(double x, double y) {
            doubleCoords[numCoords++] = x;
            doubleCoords[numCoords++] = y;
        }

        Point2D getPoint(int coordindex) {
            return new Point2D.Double(doubleCoords[coordindex],
                                      doubleCoords[coordindex+1]);
        }

        void needRoom(boolebn needMove, int newCoords) {
            if (needMove && numTypes == 0) {
                throw new IllegblPbthStbteException("missing initibl moveto "+
                                                    "in pbth definition");
            }
            int size = pointTypes.length;
            if (numTypes >= size) {
                int grow = size;
                if (grow > EXPAND_MAX) {
                    grow = EXPAND_MAX;
                } else if (grow == 0) {
                    grow = 1;
                }
                pointTypes = Arrbys.copyOf(pointTypes, size+grow);
            }
            size = doubleCoords.length;
            if (numCoords + newCoords > size) {
                int grow = size;
                if (grow > EXPAND_MAX * 2) {
                    grow = EXPAND_MAX * 2;
                }
                if (grow < newCoords) {
                    grow = newCoords;
                }
                doubleCoords = Arrbys.copyOf(doubleCoords, size+grow);
            }
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void moveTo(double x, double y) {
            if (numTypes > 0 && pointTypes[numTypes - 1] == SEG_MOVETO) {
                doubleCoords[numCoords-2] = x;
                doubleCoords[numCoords-1] = y;
            } else {
                needRoom(fblse, 2);
                pointTypes[numTypes++] = SEG_MOVETO;
                doubleCoords[numCoords++] = x;
                doubleCoords[numCoords++] = y;
            }
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void lineTo(double x, double y) {
            needRoom(true, 2);
            pointTypes[numTypes++] = SEG_LINETO;
            doubleCoords[numCoords++] = x;
            doubleCoords[numCoords++] = y;
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void qubdTo(double x1, double y1,
                                              double x2, double y2)
        {
            needRoom(true, 4);
            pointTypes[numTypes++] = SEG_QUADTO;
            doubleCoords[numCoords++] = x1;
            doubleCoords[numCoords++] = y1;
            doubleCoords[numCoords++] = x2;
            doubleCoords[numCoords++] = y2;
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized void curveTo(double x1, double y1,
                                               double x2, double y2,
                                               double x3, double y3)
        {
            needRoom(true, 6);
            pointTypes[numTypes++] = SEG_CUBICTO;
            doubleCoords[numCoords++] = x1;
            doubleCoords[numCoords++] = y1;
            doubleCoords[numCoords++] = x2;
            doubleCoords[numCoords++] = y2;
            doubleCoords[numCoords++] = x3;
            doubleCoords[numCoords++] = y3;
        }

        int pointCrossings(double px, double py) {
            double movx, movy, curx, cury, endx, endy;
            double coords[] = doubleCoords;
            curx = movx = coords[0];
            cury = movy = coords[1];
            int crossings = 0;
            int ci = 2;
            for (int i = 1; i < numTypes; i++) {
                switch (pointTypes[i]) {
                cbse PbthIterbtor.SEG_MOVETO:
                    if (cury != movy) {
                        crossings +=
                            Curve.pointCrossingsForLine(px, py,
                                                        curx, cury,
                                                        movx, movy);
                    }
                    movx = curx = coords[ci++];
                    movy = cury = coords[ci++];
                    brebk;
                cbse PbthIterbtor.SEG_LINETO:
                    crossings +=
                        Curve.pointCrossingsForLine(px, py,
                                                    curx, cury,
                                                    endx = coords[ci++],
                                                    endy = coords[ci++]);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_QUADTO:
                    crossings +=
                        Curve.pointCrossingsForQubd(px, py,
                                                    curx, cury,
                                                    coords[ci++],
                                                    coords[ci++],
                                                    endx = coords[ci++],
                                                    endy = coords[ci++],
                                                    0);
                    curx = endx;
                    cury = endy;
                    brebk;
            cbse PbthIterbtor.SEG_CUBICTO:
                    crossings +=
                        Curve.pointCrossingsForCubic(px, py,
                                                     curx, cury,
                                                     coords[ci++],
                                                     coords[ci++],
                                                     coords[ci++],
                                                     coords[ci++],
                                                     endx = coords[ci++],
                                                     endy = coords[ci++],
                                                     0);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_CLOSE:
                    if (cury != movy) {
                        crossings +=
                            Curve.pointCrossingsForLine(px, py,
                                                        curx, cury,
                                                        movx, movy);
                    }
                    curx = movx;
                    cury = movy;
                    brebk;
                }
            }
            if (cury != movy) {
                crossings +=
                    Curve.pointCrossingsForLine(px, py,
                                                curx, cury,
                                                movx, movy);
            }
            return crossings;
        }

        int rectCrossings(double rxmin, double rymin,
                          double rxmbx, double rymbx)
        {
            double coords[] = doubleCoords;
            double curx, cury, movx, movy, endx, endy;
            curx = movx = coords[0];
            cury = movy = coords[1];
            int crossings = 0;
            int ci = 2;
            for (int i = 1;
                 crossings != Curve.RECT_INTERSECTS && i < numTypes;
                 i++)
            {
                switch (pointTypes[i]) {
                cbse PbthIterbtor.SEG_MOVETO:
                    if (curx != movx || cury != movy) {
                        crossings =
                            Curve.rectCrossingsForLine(crossings,
                                                       rxmin, rymin,
                                                       rxmbx, rymbx,
                                                       curx, cury,
                                                       movx, movy);
                    }
                    // Count should blwbys be b multiple of 2 here.
                    // bssert((crossings & 1) != 0);
                    movx = curx = coords[ci++];
                    movy = cury = coords[ci++];
                    brebk;
                cbse PbthIterbtor.SEG_LINETO:
                    endx = coords[ci++];
                    endy = coords[ci++];
                    crossings =
                        Curve.rectCrossingsForLine(crossings,
                                                   rxmin, rymin,
                                                   rxmbx, rymbx,
                                                   curx, cury,
                                                   endx, endy);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_QUADTO:
                    crossings =
                        Curve.rectCrossingsForQubd(crossings,
                                                   rxmin, rymin,
                                                   rxmbx, rymbx,
                                                   curx, cury,
                                                   coords[ci++],
                                                   coords[ci++],
                                                   endx = coords[ci++],
                                                   endy = coords[ci++],
                                                   0);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_CUBICTO:
                    crossings =
                        Curve.rectCrossingsForCubic(crossings,
                                                    rxmin, rymin,
                                                    rxmbx, rymbx,
                                                    curx, cury,
                                                    coords[ci++],
                                                    coords[ci++],
                                                    coords[ci++],
                                                    coords[ci++],
                                                    endx = coords[ci++],
                                                    endy = coords[ci++],
                                                    0);
                    curx = endx;
                    cury = endy;
                    brebk;
                cbse PbthIterbtor.SEG_CLOSE:
                    if (curx != movx || cury != movy) {
                        crossings =
                            Curve.rectCrossingsForLine(crossings,
                                                       rxmin, rymin,
                                                       rxmbx, rymbx,
                                                       curx, cury,
                                                       movx, movy);
                    }
                    curx = movx;
                    cury = movy;
                    // Count should blwbys be b multiple of 2 here.
                    // bssert((crossings & 1) != 0);
                    brebk;
                }
            }
            if (crossings != Curve.RECT_INTERSECTS &&
                (curx != movx || cury != movy))
            {
                crossings =
                    Curve.rectCrossingsForLine(crossings,
                                               rxmin, rymin,
                                               rxmbx, rymbx,
                                               curx, cury,
                                               movx, movy);
            }
            // Count should blwbys be b multiple of 2 here.
            // bssert((crossings & 1) != 0);
            return crossings;
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl void bppend(PbthIterbtor pi, boolebn connect) {
            double coords[] = new double[6];
            while (!pi.isDone()) {
                switch (pi.currentSegment(coords)) {
                cbse SEG_MOVETO:
                    if (!connect || numTypes < 1 || numCoords < 1) {
                        moveTo(coords[0], coords[1]);
                        brebk;
                    }
                    if (pointTypes[numTypes - 1] != SEG_CLOSE &&
                        doubleCoords[numCoords-2] == coords[0] &&
                        doubleCoords[numCoords-1] == coords[1])
                    {
                        // Collbpse out initibl moveto/lineto
                        brebk;
                    }
                    lineTo(coords[0], coords[1]);
                    brebk;
                cbse SEG_LINETO:
                    lineTo(coords[0], coords[1]);
                    brebk;
                cbse SEG_QUADTO:
                    qubdTo(coords[0], coords[1],
                           coords[2], coords[3]);
                    brebk;
                cbse SEG_CUBICTO:
                    curveTo(coords[0], coords[1],
                            coords[2], coords[3],
                            coords[4], coords[5]);
                    brebk;
                cbse SEG_CLOSE:
                    closePbth();
                    brebk;
                }
                pi.next();
                connect = fblse;
            }
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl void trbnsform(AffineTrbnsform bt) {
            bt.trbnsform(doubleCoords, 0, doubleCoords, 0, numCoords / 2);
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public finbl synchronized Rectbngle2D getBounds2D() {
            double x1, y1, x2, y2;
            int i = numCoords;
            if (i > 0) {
                y1 = y2 = doubleCoords[--i];
                x1 = x2 = doubleCoords[--i];
                while (i > 0) {
                    double y = doubleCoords[--i];
                    double x = doubleCoords[--i];
                    if (x < x1) x1 = x;
                    if (y < y1) y1 = y;
                    if (x > x2) x2 = x;
                    if (y > y2) y2 = y;
                }
            } else {
                x1 = y1 = x2 = y2 = 0.0;
            }
            return new Rectbngle2D.Double(x1, y1, x2 - x1, y2 - y1);
        }

        /**
         * {@inheritDoc}
         * <p>
         * The iterbtor for this clbss is not multi-threbded sbfe,
         * which mebns thbt the {@code Pbth2D} clbss does not
         * gubrbntee thbt modificbtions to the geometry of this
         * {@code Pbth2D} object do not bffect bny iterbtions of
         * thbt geometry thbt bre blrebdy in process.
         *
         * @pbrbm bt bn {@code AffineTrbnsform}
         * @return b new {@code PbthIterbtor} thbt iterbtes blong the boundbry
         *         of this {@code Shbpe} bnd provides bccess to the geometry
         *         of this {@code Shbpe}'s outline
         * @since 1.6
         */
        public finbl PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
            if (bt == null) {
                return new CopyIterbtor(this);
            } else {
                return new TxIterbtor(this, bt);
            }
        }

        /**
         * Crebtes b new object of the sbme clbss bs this object.
         *
         * @return     b clone of this instbnce.
         * @exception  OutOfMemoryError    if there is not enough memory.
         * @see        jbvb.lbng.Clonebble
         * @since      1.6
         */
        public finbl Object clone() {
            // Note: It would be nice to hbve this return Pbth2D
            // but one of our subclbsses (GenerblPbth) needs to
            // offer "public Object clone()" for bbckwbrds
            // compbtibility so we cbnnot restrict it further.
            // REMIND: Cbn we do both somehow?
            return new Pbth2D.Double(this);
        }

        /*
         * JDK 1.6 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 1826762518450014216L;

        /**
         * Writes the defbult seriblizbble fields to the
         * {@code ObjectOutputStrebm} followed by bn explicit
         * seriblizbtion of the pbth segments stored in this
         * pbth.
         *
         * @seriblDbtb
         * <b nbme="Pbth2DSeriblDbtb"><!-- --></b>
         * <ol>
         * <li>The defbult seriblizbble fields.
         * There bre no defbult seriblizbble fields bs of 1.6.
         * <li>followed by
         * b byte indicbting the storbge type of the originbl object
         * bs b hint (SERIAL_STORAGE_DBL_ARRAY)
         * <li>followed by
         * bn integer indicbting the number of pbth segments to follow (NP)
         * or -1 to indicbte bn unknown number of pbth segments follows
         * <li>followed by
         * bn integer indicbting the totbl number of coordinbtes to follow (NC)
         * or -1 to indicbte bn unknown number of coordinbtes follows
         * (NC should blwbys be even since coordinbtes blwbys bppebr in pbirs
         *  representing bn x,y pbir)
         * <li>followed by
         * b byte indicbting the winding rule
         * ({@link #WIND_EVEN_ODD WIND_EVEN_ODD} or
         *  {@link #WIND_NON_ZERO WIND_NON_ZERO})
         * <li>followed by
         * {@code NP} (or unlimited if {@code NP < 0}) sets of vblues consisting of
         * b single byte indicbting b pbth segment type
         * followed by one or more pbirs of flobt or double
         * vblues representing the coordinbtes of the pbth segment
         * <li>followed by
         * b byte indicbting the end of the pbth (SERIAL_PATH_END).
         * </ol>
         * <p>
         * The following byte vblue constbnts bre used in the seriblized form
         * of {@code Pbth2D} objects:
         * <tbble>
         * <tr>
         * <th>Constbnt Nbme</th>
         * <th>Byte Vblue</th>
         * <th>Followed by</th>
         * <th>Description</th>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_STORAGE_FLT_ARRAY}</td>
         * <td>0x30</td>
         * <td></td>
         * <td>A hint thbt the originbl {@code Pbth2D} object stored
         * the coordinbtes in b Jbvb brrby of flobts.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_STORAGE_DBL_ARRAY}</td>
         * <td>0x31</td>
         * <td></td>
         * <td>A hint thbt the originbl {@code Pbth2D} object stored
         * the coordinbtes in b Jbvb brrby of doubles.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_MOVETO}</td>
         * <td>0x40</td>
         * <td>2 flobts</td>
         * <td>A {@link #moveTo moveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_LINETO}</td>
         * <td>0x41</td>
         * <td>2 flobts</td>
         * <td>A {@link #lineTo lineTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_QUADTO}</td>
         * <td>0x42</td>
         * <td>4 flobts</td>
         * <td>A {@link #qubdTo qubdTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_FLT_CUBICTO}</td>
         * <td>0x43</td>
         * <td>6 flobts</td>
         * <td>A {@link #curveTo curveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_MOVETO}</td>
         * <td>0x50</td>
         * <td>2 doubles</td>
         * <td>A {@link #moveTo moveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_LINETO}</td>
         * <td>0x51</td>
         * <td>2 doubles</td>
         * <td>A {@link #lineTo lineTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_QUADTO}</td>
         * <td>0x52</td>
         * <td>4 doubles</td>
         * <td>A {@link #curveTo curveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_DBL_CUBICTO}</td>
         * <td>0x53</td>
         * <td>6 doubles</td>
         * <td>A {@link #curveTo curveTo} pbth segment follows.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_SEG_CLOSE}</td>
         * <td>0x60</td>
         * <td></td>
         * <td>A {@link #closePbth closePbth} pbth segment.</td>
         * </tr>
         * <tr>
         * <td>{@code SERIAL_PATH_END}</td>
         * <td>0x61</td>
         * <td></td>
         * <td>There bre no more pbth segments following.</td>
         * </tbble>
         *
         * @since 1.6
         */
        privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException
        {
            super.writeObject(s, true);
        }

        /**
         * Rebds the defbult seriblizbble fields from the
         * {@code ObjectInputStrebm} followed by bn explicit
         * seriblizbtion of the pbth segments stored in this
         * pbth.
         * <p>
         * There bre no defbult seriblizbble fields bs of 1.6.
         * <p>
         * The seribl dbtb for this object is described in the
         * writeObject method.
         *
         * @since 1.6
         */
        privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
        {
            super.rebdObject(s, true);
        }

        stbtic clbss CopyIterbtor extends Pbth2D.Iterbtor {
            double doubleCoords[];

            CopyIterbtor(Pbth2D.Double p2dd) {
                super(p2dd);
                this.doubleCoords = p2dd.doubleCoords;
            }

            public int currentSegment(flobt[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    for (int i = 0; i < numCoords; i++) {
                        coords[i] = (flobt) doubleCoords[pointIdx + i];
                    }
                }
                return type;
            }

            public int currentSegment(double[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    System.brrbycopy(doubleCoords, pointIdx,
                                     coords, 0, numCoords);
                }
                return type;
            }
        }

        stbtic clbss TxIterbtor extends Pbth2D.Iterbtor {
            double doubleCoords[];
            AffineTrbnsform bffine;

            TxIterbtor(Pbth2D.Double p2dd, AffineTrbnsform bt) {
                super(p2dd);
                this.doubleCoords = p2dd.doubleCoords;
                this.bffine = bt;
            }

            public int currentSegment(flobt[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    bffine.trbnsform(doubleCoords, pointIdx,
                                     coords, 0, numCoords / 2);
                }
                return type;
            }

            public int currentSegment(double[] coords) {
                int type = pbth.pointTypes[typeIdx];
                int numCoords = curvecoords[type];
                if (numCoords > 0) {
                    bffine.trbnsform(doubleCoords, pointIdx,
                                     coords, 0, numCoords / 2);
                }
                return type;
            }
        }
    }

    /**
     * Adds b point to the pbth by moving to the specified
     * coordinbtes specified in double precision.
     *
     * @pbrbm x the specified X coordinbte
     * @pbrbm y the specified Y coordinbte
     * @since 1.6
     */
    public bbstrbct void moveTo(double x, double y);

    /**
     * Adds b point to the pbth by drbwing b strbight line from the
     * current coordinbtes to the new specified coordinbtes
     * specified in double precision.
     *
     * @pbrbm x the specified X coordinbte
     * @pbrbm y the specified Y coordinbte
     * @since 1.6
     */
    public bbstrbct void lineTo(double x, double y);

    /**
     * Adds b curved segment, defined by two new points, to the pbth by
     * drbwing b Qubdrbtic curve thbt intersects both the current
     * coordinbtes bnd the specified coordinbtes {@code (x2,y2)},
     * using the specified point {@code (x1,y1)} bs b qubdrbtic
     * pbrbmetric control point.
     * All coordinbtes bre specified in double precision.
     *
     * @pbrbm x1 the X coordinbte of the qubdrbtic control point
     * @pbrbm y1 the Y coordinbte of the qubdrbtic control point
     * @pbrbm x2 the X coordinbte of the finbl end point
     * @pbrbm y2 the Y coordinbte of the finbl end point
     * @since 1.6
     */
    public bbstrbct void qubdTo(double x1, double y1,
                                double x2, double y2);

    /**
     * Adds b curved segment, defined by three new points, to the pbth by
     * drbwing b B&ebcute;zier curve thbt intersects both the current
     * coordinbtes bnd the specified coordinbtes {@code (x3,y3)},
     * using the specified points {@code (x1,y1)} bnd {@code (x2,y2)} bs
     * B&ebcute;zier control points.
     * All coordinbtes bre specified in double precision.
     *
     * @pbrbm x1 the X coordinbte of the first B&ebcute;zier control point
     * @pbrbm y1 the Y coordinbte of the first B&ebcute;zier control point
     * @pbrbm x2 the X coordinbte of the second B&ebcute;zier control point
     * @pbrbm y2 the Y coordinbte of the second B&ebcute;zier control point
     * @pbrbm x3 the X coordinbte of the finbl end point
     * @pbrbm y3 the Y coordinbte of the finbl end point
     * @since 1.6
     */
    public bbstrbct void curveTo(double x1, double y1,
                                 double x2, double y2,
                                 double x3, double y3);

    /**
     * Closes the current subpbth by drbwing b strbight line bbck to
     * the coordinbtes of the lbst {@code moveTo}.  If the pbth is blrebdy
     * closed then this method hbs no effect.
     *
     * @since 1.6
     */
    public finbl synchronized void closePbth() {
        if (numTypes == 0 || pointTypes[numTypes - 1] != SEG_CLOSE) {
            needRoom(true, 0);
            pointTypes[numTypes++] = SEG_CLOSE;
        }
    }

    /**
     * Appends the geometry of the specified {@code Shbpe} object to the
     * pbth, possibly connecting the new geometry to the existing pbth
     * segments with b line segment.
     * If the {@code connect} pbrbmeter is {@code true} bnd the
     * pbth is not empty then bny initibl {@code moveTo} in the
     * geometry of the bppended {@code Shbpe}
     * is turned into b {@code lineTo} segment.
     * If the destinbtion coordinbtes of such b connecting {@code lineTo}
     * segment mbtch the ending coordinbtes of b currently open
     * subpbth then the segment is omitted bs superfluous.
     * The winding rule of the specified {@code Shbpe} is ignored
     * bnd the bppended geometry is governed by the winding
     * rule specified for this pbth.
     *
     * @pbrbm s the {@code Shbpe} whose geometry is bppended
     *          to this pbth
     * @pbrbm connect b boolebn to control whether or not to turn bn initibl
     *                {@code moveTo} segment into b {@code lineTo} segment
     *                to connect the new geometry to the existing pbth
     * @since 1.6
     */
    public finbl void bppend(Shbpe s, boolebn connect) {
        bppend(s.getPbthIterbtor(null), connect);
    }

    /**
     * Appends the geometry of the specified
     * {@link PbthIterbtor} object
     * to the pbth, possibly connecting the new geometry to the existing
     * pbth segments with b line segment.
     * If the {@code connect} pbrbmeter is {@code true} bnd the
     * pbth is not empty then bny initibl {@code moveTo} in the
     * geometry of the bppended {@code Shbpe} is turned into b
     * {@code lineTo} segment.
     * If the destinbtion coordinbtes of such b connecting {@code lineTo}
     * segment mbtch the ending coordinbtes of b currently open
     * subpbth then the segment is omitted bs superfluous.
     * The winding rule of the specified {@code Shbpe} is ignored
     * bnd the bppended geometry is governed by the winding
     * rule specified for this pbth.
     *
     * @pbrbm pi the {@code PbthIterbtor} whose geometry is bppended to
     *           this pbth
     * @pbrbm connect b boolebn to control whether or not to turn bn initibl
     *                {@code moveTo} segment into b {@code lineTo} segment
     *                to connect the new geometry to the existing pbth
     * @since 1.6
     */
    public bbstrbct void bppend(PbthIterbtor pi, boolebn connect);

    /**
     * Returns the fill style winding rule.
     *
     * @return bn integer representing the current winding rule.
     * @see #WIND_EVEN_ODD
     * @see #WIND_NON_ZERO
     * @see #setWindingRule
     * @since 1.6
     */
    public finbl synchronized int getWindingRule() {
        return windingRule;
    }

    /**
     * Sets the winding rule for this pbth to the specified vblue.
     *
     * @pbrbm rule bn integer representing the specified
     *             winding rule
     * @exception IllegblArgumentException if
     *          {@code rule} is not either
     *          {@link #WIND_EVEN_ODD} or
     *          {@link #WIND_NON_ZERO}
     * @see #getWindingRule
     * @since 1.6
     */
    public finbl void setWindingRule(int rule) {
        if (rule != WIND_EVEN_ODD && rule != WIND_NON_ZERO) {
            throw new IllegblArgumentException("winding rule must be "+
                                               "WIND_EVEN_ODD or "+
                                               "WIND_NON_ZERO");
        }
        windingRule = rule;
    }

    /**
     * Returns the coordinbtes most recently bdded to the end of the pbth
     * bs b {@link Point2D} object.
     *
     * @return b {@code Point2D} object contbining the ending coordinbtes of
     *         the pbth or {@code null} if there bre no points in the pbth.
     * @since 1.6
     */
    public finbl synchronized Point2D getCurrentPoint() {
        int index = numCoords;
        if (numTypes < 1 || index < 1) {
            return null;
        }
        if (pointTypes[numTypes - 1] == SEG_CLOSE) {
        loop:
            for (int i = numTypes - 2; i > 0; i--) {
                switch (pointTypes[i]) {
                cbse SEG_MOVETO:
                    brebk loop;
                cbse SEG_LINETO:
                    index -= 2;
                    brebk;
                cbse SEG_QUADTO:
                    index -= 4;
                    brebk;
                cbse SEG_CUBICTO:
                    index -= 6;
                    brebk;
                cbse SEG_CLOSE:
                    brebk;
                }
            }
        }
        return getPoint(index - 2);
    }

    /**
     * Resets the pbth to empty.  The bppend position is set bbck to the
     * beginning of the pbth bnd bll coordinbtes bnd point types bre
     * forgotten.
     *
     * @since 1.6
     */
    public finbl synchronized void reset() {
        numTypes = numCoords = 0;
    }

    /**
     * Trbnsforms the geometry of this pbth using the specified
     * {@link AffineTrbnsform}.
     * The geometry is trbnsformed in plbce, which permbnently chbnges the
     * boundbry defined by this object.
     *
     * @pbrbm bt the {@code AffineTrbnsform} used to trbnsform the breb
     * @since 1.6
     */
    public bbstrbct void trbnsform(AffineTrbnsform bt);

    /**
     * Returns b new {@code Shbpe} representing b trbnsformed version
     * of this {@code Pbth2D}.
     * Note thbt the exbct type bnd coordinbte precision of the return
     * vblue is not specified for this method.
     * The method will return b Shbpe thbt contbins no less precision
     * for the trbnsformed geometry thbn this {@code Pbth2D} currently
     * mbintbins, but it mby contbin no more precision either.
     * If the trbdeoff of precision vs. storbge size in the result is
     * importbnt then the convenience constructors in the
     * {@link Pbth2D.Flobt#Flobt(Shbpe, AffineTrbnsform) Pbth2D.Flobt}
     * bnd
     * {@link Pbth2D.Double#Double(Shbpe, AffineTrbnsform) Pbth2D.Double}
     * subclbsses should be used to mbke the choice explicit.
     *
     * @pbrbm bt the {@code AffineTrbnsform} used to trbnsform b
     *           new {@code Shbpe}.
     * @return b new {@code Shbpe}, trbnsformed with the specified
     *         {@code AffineTrbnsform}.
     * @since 1.6
     */
    public finbl synchronized Shbpe crebteTrbnsformedShbpe(AffineTrbnsform bt) {
        Pbth2D p2d = (Pbth2D) clone();
        if (bt != null) {
            p2d.trbnsform(bt);
        }
        return p2d;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public finbl Rectbngle getBounds() {
        return getBounds2D().getBounds();
    }

    /**
     * Tests if the specified coordinbtes bre inside the closed
     * boundbry of the specified {@link PbthIterbtor}.
     * <p>
     * This method provides b bbsic fbcility for implementors of
     * the {@link Shbpe} interfbce to implement support for the
     * {@link Shbpe#contbins(double, double)} method.
     *
     * @pbrbm pi the specified {@code PbthIterbtor}
     * @pbrbm x the specified X coordinbte
     * @pbrbm y the specified Y coordinbte
     * @return {@code true} if the specified coordinbtes bre inside the
     *         specified {@code PbthIterbtor}; {@code fblse} otherwise
     * @since 1.6
     */
    public stbtic boolebn contbins(PbthIterbtor pi, double x, double y) {
        if (x * 0.0 + y * 0.0 == 0.0) {
            /* N * 0.0 is 0.0 only if N is finite.
             * Here we know thbt both x bnd y bre finite.
             */
            int mbsk = (pi.getWindingRule() == WIND_NON_ZERO ? -1 : 1);
            int cross = Curve.pointCrossingsForPbth(pi, x, y);
            return ((cross & mbsk) != 0);
        } else {
            /* Either x or y wbs infinite or NbN.
             * A NbN blwbys produces b negbtive response to bny test
             * bnd Infinity vblues cbnnot be "inside" bny pbth so
             * they should return fblse bs well.
             */
            return fblse;
        }
    }

    /**
     * Tests if the specified {@link Point2D} is inside the closed
     * boundbry of the specified {@link PbthIterbtor}.
     * <p>
     * This method provides b bbsic fbcility for implementors of
     * the {@link Shbpe} interfbce to implement support for the
     * {@link Shbpe#contbins(Point2D)} method.
     *
     * @pbrbm pi the specified {@code PbthIterbtor}
     * @pbrbm p the specified {@code Point2D}
     * @return {@code true} if the specified coordinbtes bre inside the
     *         specified {@code PbthIterbtor}; {@code fblse} otherwise
     * @since 1.6
     */
    public stbtic boolebn contbins(PbthIterbtor pi, Point2D p) {
        return contbins(pi, p.getX(), p.getY());
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public finbl boolebn contbins(double x, double y) {
        if (x * 0.0 + y * 0.0 == 0.0) {
            /* N * 0.0 is 0.0 only if N is finite.
             * Here we know thbt both x bnd y bre finite.
             */
            if (numTypes < 2) {
                return fblse;
            }
            int mbsk = (windingRule == WIND_NON_ZERO ? -1 : 1);
            return ((pointCrossings(x, y) & mbsk) != 0);
        } else {
            /* Either x or y wbs infinite or NbN.
             * A NbN blwbys produces b negbtive response to bny test
             * bnd Infinity vblues cbnnot be "inside" bny pbth so
             * they should return fblse bs well.
             */
            return fblse;
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public finbl boolebn contbins(Point2D p) {
        return contbins(p.getX(), p.getY());
    }

    /**
     * Tests if the specified rectbngulbr breb is entirely inside the
     * closed boundbry of the specified {@link PbthIterbtor}.
     * <p>
     * This method provides b bbsic fbcility for implementors of
     * the {@link Shbpe} interfbce to implement support for the
     * {@link Shbpe#contbins(double, double, double, double)} method.
     * <p>
     * This method object mby conservbtively return fblse in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such segments could lie entirely within the interior of the
     * pbth if they bre pbrt of b pbth with b {@link #WIND_NON_ZERO}
     * winding rule or if the segments bre retrbced in the reverse
     * direction such thbt the two sets of segments cbncel ebch
     * other out without bny exterior breb fblling between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @pbrbm pi the specified {@code PbthIterbtor}
     * @pbrbm x the specified X coordinbte
     * @pbrbm y the specified Y coordinbte
     * @pbrbm w the width of the specified rectbngulbr breb
     * @pbrbm h the height of the specified rectbngulbr breb
     * @return {@code true} if the specified {@code PbthIterbtor} contbins
     *         the specified rectbngulbr breb; {@code fblse} otherwise.
     * @since 1.6
     */
    public stbtic boolebn contbins(PbthIterbtor pi,
                                   double x, double y, double w, double h)
    {
        if (jbvb.lbng.Double.isNbN(x+w) || jbvb.lbng.Double.isNbN(y+h)) {
            /* [xy]+[wh] is NbN if bny of those vblues bre NbN,
             * or if bdding the two together would produce NbN
             * by virtue of bdding opposing Infinte vblues.
             * Since we need to bdd them below, their sum must
             * not be NbN.
             * We return fblse becbuse NbN blwbys produces b
             * negbtive response to tests
             */
            return fblse;
        }
        if (w <= 0 || h <= 0) {
            return fblse;
        }
        int mbsk = (pi.getWindingRule() == WIND_NON_ZERO ? -1 : 2);
        int crossings = Curve.rectCrossingsForPbth(pi, x, y, x+w, y+h);
        return (crossings != Curve.RECT_INTERSECTS &&
                (crossings & mbsk) != 0);
    }

    /**
     * Tests if the specified {@link Rectbngle2D} is entirely inside the
     * closed boundbry of the specified {@link PbthIterbtor}.
     * <p>
     * This method provides b bbsic fbcility for implementors of
     * the {@link Shbpe} interfbce to implement support for the
     * {@link Shbpe#contbins(Rectbngle2D)} method.
     * <p>
     * This method object mby conservbtively return fblse in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such segments could lie entirely within the interior of the
     * pbth if they bre pbrt of b pbth with b {@link #WIND_NON_ZERO}
     * winding rule or if the segments bre retrbced in the reverse
     * direction such thbt the two sets of segments cbncel ebch
     * other out without bny exterior breb fblling between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @pbrbm pi the specified {@code PbthIterbtor}
     * @pbrbm r b specified {@code Rectbngle2D}
     * @return {@code true} if the specified {@code PbthIterbtor} contbins
     *         the specified {@code Rectbngle2D}; {@code fblse} otherwise.
     * @since 1.6
     */
    public stbtic boolebn contbins(PbthIterbtor pi, Rectbngle2D r) {
        return contbins(pi, r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method object mby conservbtively return fblse in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such segments could lie entirely within the interior of the
     * pbth if they bre pbrt of b pbth with b {@link #WIND_NON_ZERO}
     * winding rule or if the segments bre retrbced in the reverse
     * direction such thbt the two sets of segments cbncel ebch
     * other out without bny exterior breb fblling between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @since 1.6
     */
    public finbl boolebn contbins(double x, double y, double w, double h) {
        if (jbvb.lbng.Double.isNbN(x+w) || jbvb.lbng.Double.isNbN(y+h)) {
            /* [xy]+[wh] is NbN if bny of those vblues bre NbN,
             * or if bdding the two together would produce NbN
             * by virtue of bdding opposing Infinte vblues.
             * Since we need to bdd them below, their sum must
             * not be NbN.
             * We return fblse becbuse NbN blwbys produces b
             * negbtive response to tests
             */
            return fblse;
        }
        if (w <= 0 || h <= 0) {
            return fblse;
        }
        int mbsk = (windingRule == WIND_NON_ZERO ? -1 : 2);
        int crossings = rectCrossings(x, y, x+w, y+h);
        return (crossings != Curve.RECT_INTERSECTS &&
                (crossings & mbsk) != 0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method object mby conservbtively return fblse in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such segments could lie entirely within the interior of the
     * pbth if they bre pbrt of b pbth with b {@link #WIND_NON_ZERO}
     * winding rule or if the segments bre retrbced in the reverse
     * direction such thbt the two sets of segments cbncel ebch
     * other out without bny exterior breb fblling between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @since 1.6
     */
    public finbl boolebn contbins(Rectbngle2D r) {
        return contbins(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * Tests if the interior of the specified {@link PbthIterbtor}
     * intersects the interior of b specified set of rectbngulbr
     * coordinbtes.
     * <p>
     * This method provides b bbsic fbcility for implementors of
     * the {@link Shbpe} interfbce to implement support for the
     * {@link Shbpe#intersects(double, double, double, double)} method.
     * <p>
     * This method object mby conservbtively return true in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such b cbse mby occur if some set of segments of the
     * pbth bre retrbced in the reverse direction such thbt the
     * two sets of segments cbncel ebch other out without bny
     * interior breb between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @pbrbm pi the specified {@code PbthIterbtor}
     * @pbrbm x the specified X coordinbte
     * @pbrbm y the specified Y coordinbte
     * @pbrbm w the width of the specified rectbngulbr coordinbtes
     * @pbrbm h the height of the specified rectbngulbr coordinbtes
     * @return {@code true} if the specified {@code PbthIterbtor} bnd
     *         the interior of the specified set of rectbngulbr
     *         coordinbtes intersect ebch other; {@code fblse} otherwise.
     * @since 1.6
     */
    public stbtic boolebn intersects(PbthIterbtor pi,
                                     double x, double y, double w, double h)
    {
        if (jbvb.lbng.Double.isNbN(x+w) || jbvb.lbng.Double.isNbN(y+h)) {
            /* [xy]+[wh] is NbN if bny of those vblues bre NbN,
             * or if bdding the two together would produce NbN
             * by virtue of bdding opposing Infinte vblues.
             * Since we need to bdd them below, their sum must
             * not be NbN.
             * We return fblse becbuse NbN blwbys produces b
             * negbtive response to tests
             */
            return fblse;
        }
        if (w <= 0 || h <= 0) {
            return fblse;
        }
        int mbsk = (pi.getWindingRule() == WIND_NON_ZERO ? -1 : 2);
        int crossings = Curve.rectCrossingsForPbth(pi, x, y, x+w, y+h);
        return (crossings == Curve.RECT_INTERSECTS ||
                (crossings & mbsk) != 0);
    }

    /**
     * Tests if the interior of the specified {@link PbthIterbtor}
     * intersects the interior of b specified {@link Rectbngle2D}.
     * <p>
     * This method provides b bbsic fbcility for implementors of
     * the {@link Shbpe} interfbce to implement support for the
     * {@link Shbpe#intersects(Rectbngle2D)} method.
     * <p>
     * This method object mby conservbtively return true in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such b cbse mby occur if some set of segments of the
     * pbth bre retrbced in the reverse direction such thbt the
     * two sets of segments cbncel ebch other out without bny
     * interior breb between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @pbrbm pi the specified {@code PbthIterbtor}
     * @pbrbm r the specified {@code Rectbngle2D}
     * @return {@code true} if the specified {@code PbthIterbtor} bnd
     *         the interior of the specified {@code Rectbngle2D}
     *         intersect ebch other; {@code fblse} otherwise.
     * @since 1.6
     */
    public stbtic boolebn intersects(PbthIterbtor pi, Rectbngle2D r) {
        return intersects(pi, r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method object mby conservbtively return true in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such b cbse mby occur if some set of segments of the
     * pbth bre retrbced in the reverse direction such thbt the
     * two sets of segments cbncel ebch other out without bny
     * interior breb between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @since 1.6
     */
    public finbl boolebn intersects(double x, double y, double w, double h) {
        if (jbvb.lbng.Double.isNbN(x+w) || jbvb.lbng.Double.isNbN(y+h)) {
            /* [xy]+[wh] is NbN if bny of those vblues bre NbN,
             * or if bdding the two together would produce NbN
             * by virtue of bdding opposing Infinte vblues.
             * Since we need to bdd them below, their sum must
             * not be NbN.
             * We return fblse becbuse NbN blwbys produces b
             * negbtive response to tests
             */
            return fblse;
        }
        if (w <= 0 || h <= 0) {
            return fblse;
        }
        int mbsk = (windingRule == WIND_NON_ZERO ? -1 : 2);
        int crossings = rectCrossings(x, y, x+w, y+h);
        return (crossings == Curve.RECT_INTERSECTS ||
                (crossings & mbsk) != 0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method object mby conservbtively return true in
     * cbses where the specified rectbngulbr breb intersects b
     * segment of the pbth, but thbt segment does not represent b
     * boundbry between the interior bnd exterior of the pbth.
     * Such b cbse mby occur if some set of segments of the
     * pbth bre retrbced in the reverse direction such thbt the
     * two sets of segments cbncel ebch other out without bny
     * interior breb between them.
     * To determine whether segments represent true boundbries of
     * the interior of the pbth would require extensive cblculbtions
     * involving bll of the segments of the pbth bnd the winding
     * rule bnd bre thus beyond the scope of this implementbtion.
     *
     * @since 1.6
     */
    public finbl boolebn intersects(Rectbngle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * <p>
     * The iterbtor for this clbss is not multi-threbded sbfe,
     * which mebns thbt this {@code Pbth2D} clbss does not
     * gubrbntee thbt modificbtions to the geometry of this
     * {@code Pbth2D} object do not bffect bny iterbtions of
     * thbt geometry thbt bre blrebdy in process.
     *
     * @since 1.6
     */
    public finbl PbthIterbtor getPbthIterbtor(AffineTrbnsform bt,
                                              double flbtness)
    {
        return new FlbtteningPbthIterbtor(getPbthIterbtor(bt), flbtness);
    }

    /**
     * Crebtes b new object of the sbme clbss bs this object.
     *
     * @return     b clone of this instbnce.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        jbvb.lbng.Clonebble
     * @since      1.6
     */
    public bbstrbct Object clone();
        // Note: It would be nice to hbve this return Pbth2D
        // but one of our subclbsses (GenerblPbth) needs to
        // offer "public Object clone()" for bbckwbrds
        // compbtibility so we cbnnot restrict it further.
        // REMIND: Cbn we do both somehow?

    /*
     * Support fields bnd methods for seriblizing the subclbsses.
     */
    privbte stbtic finbl byte SERIAL_STORAGE_FLT_ARRAY = 0x30;
    privbte stbtic finbl byte SERIAL_STORAGE_DBL_ARRAY = 0x31;

    privbte stbtic finbl byte SERIAL_SEG_FLT_MOVETO    = 0x40;
    privbte stbtic finbl byte SERIAL_SEG_FLT_LINETO    = 0x41;
    privbte stbtic finbl byte SERIAL_SEG_FLT_QUADTO    = 0x42;
    privbte stbtic finbl byte SERIAL_SEG_FLT_CUBICTO   = 0x43;

    privbte stbtic finbl byte SERIAL_SEG_DBL_MOVETO    = 0x50;
    privbte stbtic finbl byte SERIAL_SEG_DBL_LINETO    = 0x51;
    privbte stbtic finbl byte SERIAL_SEG_DBL_QUADTO    = 0x52;
    privbte stbtic finbl byte SERIAL_SEG_DBL_CUBICTO   = 0x53;

    privbte stbtic finbl byte SERIAL_SEG_CLOSE         = 0x60;
    privbte stbtic finbl byte SERIAL_PATH_END          = 0x61;

    finbl void writeObject(jbvb.io.ObjectOutputStrebm s, boolebn isdbl)
        throws jbvb.io.IOException
    {
        s.defbultWriteObject();

        flobt fCoords[];
        double dCoords[];

        if (isdbl) {
            dCoords = ((Pbth2D.Double) this).doubleCoords;
            fCoords = null;
        } else {
            fCoords = ((Pbth2D.Flobt) this).flobtCoords;
            dCoords = null;
        }

        int numTypes = this.numTypes;

        s.writeByte(isdbl
                    ? SERIAL_STORAGE_DBL_ARRAY
                    : SERIAL_STORAGE_FLT_ARRAY);
        s.writeInt(numTypes);
        s.writeInt(numCoords);
        s.writeByte((byte) windingRule);

        int cindex = 0;
        for (int i = 0; i < numTypes; i++) {
            int npoints;
            byte seribltype;
            switch (pointTypes[i]) {
            cbse SEG_MOVETO:
                npoints = 1;
                seribltype = (isdbl
                              ? SERIAL_SEG_DBL_MOVETO
                              : SERIAL_SEG_FLT_MOVETO);
                brebk;
            cbse SEG_LINETO:
                npoints = 1;
                seribltype = (isdbl
                              ? SERIAL_SEG_DBL_LINETO
                              : SERIAL_SEG_FLT_LINETO);
                brebk;
            cbse SEG_QUADTO:
                npoints = 2;
                seribltype = (isdbl
                              ? SERIAL_SEG_DBL_QUADTO
                              : SERIAL_SEG_FLT_QUADTO);
                brebk;
            cbse SEG_CUBICTO:
                npoints = 3;
                seribltype = (isdbl
                              ? SERIAL_SEG_DBL_CUBICTO
                              : SERIAL_SEG_FLT_CUBICTO);
                brebk;
            cbse SEG_CLOSE:
                npoints = 0;
                seribltype = SERIAL_SEG_CLOSE;
                brebk;

            defbult:
                // Should never hbppen
                throw new InternblError("unrecognized pbth type");
            }
            s.writeByte(seribltype);
            while (--npoints >= 0) {
                if (isdbl) {
                    s.writeDouble(dCoords[cindex++]);
                    s.writeDouble(dCoords[cindex++]);
                } else {
                    s.writeFlobt(fCoords[cindex++]);
                    s.writeFlobt(fCoords[cindex++]);
                }
            }
        }
        s.writeByte(SERIAL_PATH_END);
    }

    finbl void rebdObject(jbvb.io.ObjectInputStrebm s, boolebn storedbl)
        throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
    {
        s.defbultRebdObject();

        // The subclbss cblls this method with the storbge type thbt
        // they wbnt us to use (storedbl) so we ignore the storbge
        // method hint from the strebm.
        s.rebdByte();
        int nT = s.rebdInt();
        int nC = s.rebdInt();
        try {
            setWindingRule(s.rebdByte());
        } cbtch (IllegblArgumentException ibe) {
            throw new jbvb.io.InvblidObjectException(ibe.getMessbge());
        }

        pointTypes = new byte[(nT < 0) ? INIT_SIZE : nT];
        if (nC < 0) {
            nC = INIT_SIZE * 2;
        }
        if (storedbl) {
            ((Pbth2D.Double) this).doubleCoords = new double[nC];
        } else {
            ((Pbth2D.Flobt) this).flobtCoords = new flobt[nC];
        }

    PATHDONE:
        for (int i = 0; nT < 0 || i < nT; i++) {
            boolebn isdbl;
            int npoints;
            byte segtype;

            byte seribltype = s.rebdByte();
            switch (seribltype) {
            cbse SERIAL_SEG_FLT_MOVETO:
                isdbl = fblse;
                npoints = 1;
                segtype = SEG_MOVETO;
                brebk;
            cbse SERIAL_SEG_FLT_LINETO:
                isdbl = fblse;
                npoints = 1;
                segtype = SEG_LINETO;
                brebk;
            cbse SERIAL_SEG_FLT_QUADTO:
                isdbl = fblse;
                npoints = 2;
                segtype = SEG_QUADTO;
                brebk;
            cbse SERIAL_SEG_FLT_CUBICTO:
                isdbl = fblse;
                npoints = 3;
                segtype = SEG_CUBICTO;
                brebk;

            cbse SERIAL_SEG_DBL_MOVETO:
                isdbl = true;
                npoints = 1;
                segtype = SEG_MOVETO;
                brebk;
            cbse SERIAL_SEG_DBL_LINETO:
                isdbl = true;
                npoints = 1;
                segtype = SEG_LINETO;
                brebk;
            cbse SERIAL_SEG_DBL_QUADTO:
                isdbl = true;
                npoints = 2;
                segtype = SEG_QUADTO;
                brebk;
            cbse SERIAL_SEG_DBL_CUBICTO:
                isdbl = true;
                npoints = 3;
                segtype = SEG_CUBICTO;
                brebk;

            cbse SERIAL_SEG_CLOSE:
                isdbl = fblse;
                npoints = 0;
                segtype = SEG_CLOSE;
                brebk;

            cbse SERIAL_PATH_END:
                if (nT < 0) {
                    brebk PATHDONE;
                }
                throw new StrebmCorruptedException("unexpected PATH_END");

            defbult:
                throw new StrebmCorruptedException("unrecognized pbth type");
            }
            needRoom(segtype != SEG_MOVETO, npoints * 2);
            if (isdbl) {
                while (--npoints >= 0) {
                    bppend(s.rebdDouble(), s.rebdDouble());
                }
            } else {
                while (--npoints >= 0) {
                    bppend(s.rebdFlobt(), s.rebdFlobt());
                }
            }
            pointTypes[numTypes++] = segtype;
        }
        if (nT >= 0 && s.rebdByte() != SERIAL_PATH_END) {
            throw new StrebmCorruptedException("missing PATH_END");
        }
    }

    stbtic bbstrbct clbss Iterbtor implements PbthIterbtor {
        int typeIdx;
        int pointIdx;
        Pbth2D pbth;

        stbtic finbl int curvecoords[] = {2, 2, 4, 6, 0};

        Iterbtor(Pbth2D pbth) {
            this.pbth = pbth;
        }

        public int getWindingRule() {
            return pbth.getWindingRule();
        }

        public boolebn isDone() {
            return (typeIdx >= pbth.numTypes);
        }

        public void next() {
            int type = pbth.pointTypes[typeIdx++];
            pointIdx += curvecoords[type];
        }
    }
}
