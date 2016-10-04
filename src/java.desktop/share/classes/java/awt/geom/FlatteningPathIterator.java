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

import jbvb.util.*;

/**
 * The <code>FlbtteningPbthIterbtor</code> clbss returns b flbttened view of
 * bnother {@link PbthIterbtor} object.  Other {@link jbvb.bwt.Shbpe Shbpe}
 * clbsses cbn use this clbss to provide flbttening behbvior for their pbths
 * without hbving to perform the interpolbtion cblculbtions themselves.
 *
 * @buthor Jim Grbhbm
 */
public clbss FlbtteningPbthIterbtor implements PbthIterbtor {
    stbtic finbl int GROW_SIZE = 24;    // Multiple of cubic & qubd curve size

    PbthIterbtor src;                   // The source iterbtor

    double squbreflbt;                  // Squbre of the flbtness pbrbmeter
                                        // for testing bgbinst squbred lengths

    int limit;                          // Mbximum number of recursion levels

    double hold[] = new double[14];     // The cbche of interpolbted coords
                                        // Note thbt this must be long enough
                                        // to store b full cubic segment bnd
                                        // b relbtive cubic segment to bvoid
                                        // blibsing when copying the coords
                                        // of b curve to the end of the brrby.
                                        // This is blso serendipitously equbl
                                        // to the size of b full qubd segment
                                        // bnd 2 relbtive qubd segments.

    double curx, cury;                  // The ending x,y of the lbst segment

    double movx, movy;                  // The x,y of the lbst move segment

    int holdType;                       // The type of the curve being held
                                        // for interpolbtion

    int holdEnd;                        // The index of the lbst curve segment
                                        // being held for interpolbtion

    int holdIndex;                      // The index of the curve segment
                                        // thbt wbs lbst interpolbted.  This
                                        // is the curve segment rebdy to be
                                        // returned in the next cbll to
                                        // currentSegment().

    int levels[];                       // The recursion level bt which
                                        // ebch curve being held in storbge
                                        // wbs generbted.

    int levelIndex;                     // The index of the entry in the
                                        // levels brrby of the curve segment
                                        // bt the holdIndex

    boolebn done;                       // True when iterbtion is done

    /**
     * Constructs b new <code>FlbtteningPbthIterbtor</code> object thbt
     * flbttens b pbth bs it iterbtes over it.  The iterbtor does not
     * subdivide bny curve rebd from the source iterbtor to more thbn
     * 10 levels of subdivision which yields b mbximum of 1024 line
     * segments per curve.
     * @pbrbm src the originbl unflbttened pbth being iterbted over
     * @pbrbm flbtness the mbximum bllowbble distbnce between the
     * control points bnd the flbttened curve
     */
    public FlbtteningPbthIterbtor(PbthIterbtor src, double flbtness) {
        this(src, flbtness, 10);
    }

    /**
     * Constructs b new <code>FlbtteningPbthIterbtor</code> object
     * thbt flbttens b pbth bs it iterbtes over it.
     * The <code>limit</code> pbrbmeter bllows you to control the
     * mbximum number of recursive subdivisions thbt the iterbtor
     * cbn mbke before it bssumes thbt the curve is flbt enough
     * without mebsuring bgbinst the <code>flbtness</code> pbrbmeter.
     * The flbttened iterbtion therefore never generbtes more thbn
     * b mbximum of <code>(2^limit)</code> line segments per curve.
     * @pbrbm src the originbl unflbttened pbth being iterbted over
     * @pbrbm flbtness the mbximum bllowbble distbnce between the
     * control points bnd the flbttened curve
     * @pbrbm limit the mbximum number of recursive subdivisions
     * bllowed for bny curved segment
     * @exception IllegblArgumentException if
     *          <code>flbtness</code> or <code>limit</code>
     *          is less thbn zero
     */
    public FlbtteningPbthIterbtor(PbthIterbtor src, double flbtness,
                                  int limit) {
        if (flbtness < 0.0) {
            throw new IllegblArgumentException("flbtness must be >= 0");
        }
        if (limit < 0) {
            throw new IllegblArgumentException("limit must be >= 0");
        }
        this.src = src;
        this.squbreflbt = flbtness * flbtness;
        this.limit = limit;
        this.levels = new int[limit + 1];
        // prime the first pbth segment
        next(fblse);
    }

    /**
     * Returns the flbtness of this iterbtor.
     * @return the flbtness of this <code>FlbtteningPbthIterbtor</code>.
     */
    public double getFlbtness() {
        return Mbth.sqrt(squbreflbt);
    }

    /**
     * Returns the recursion limit of this iterbtor.
     * @return the recursion limit of this
     * <code>FlbtteningPbthIterbtor</code>.
     */
    public int getRecursionLimit() {
        return limit;
    }

    /**
     * Returns the winding rule for determining the interior of the
     * pbth.
     * @return the winding rule of the originbl unflbttened pbth being
     * iterbted over.
     * @see PbthIterbtor#WIND_EVEN_ODD
     * @see PbthIterbtor#WIND_NON_ZERO
     */
    public int getWindingRule() {
        return src.getWindingRule();
    }

    /**
     * Tests if the iterbtion is complete.
     * @return <code>true</code> if bll the segments hbve
     * been rebd; <code>fblse</code> otherwise.
     */
    public boolebn isDone() {
        return done;
    }

    /*
     * Ensures thbt the hold brrby cbn hold up to (wbnt) more vblues.
     * It is currently holding (hold.length - holdIndex) vblues.
     */
    void ensureHoldCbpbcity(int wbnt) {
        if (holdIndex - wbnt < 0) {
            int hbve = hold.length - holdIndex;
            int newsize = hold.length + GROW_SIZE;
            double newhold[] = new double[newsize];
            System.brrbycopy(hold, holdIndex,
                             newhold, holdIndex + GROW_SIZE,
                             hbve);
            hold = newhold;
            holdIndex += GROW_SIZE;
            holdEnd += GROW_SIZE;
        }
    }

    /**
     * Moves the iterbtor to the next segment of the pbth forwbrds
     * blong the primbry direction of trbversbl bs long bs there bre
     * more points in thbt direction.
     */
    public void next() {
        next(true);
    }

    privbte void next(boolebn doNext) {
        int level;

        if (holdIndex >= holdEnd) {
            if (doNext) {
                src.next();
            }
            if (src.isDone()) {
                done = true;
                return;
            }
            holdType = src.currentSegment(hold);
            levelIndex = 0;
            levels[0] = 0;
        }

        switch (holdType) {
        cbse SEG_MOVETO:
        cbse SEG_LINETO:
            curx = hold[0];
            cury = hold[1];
            if (holdType == SEG_MOVETO) {
                movx = curx;
                movy = cury;
            }
            holdIndex = 0;
            holdEnd = 0;
            brebk;
        cbse SEG_CLOSE:
            curx = movx;
            cury = movy;
            holdIndex = 0;
            holdEnd = 0;
            brebk;
        cbse SEG_QUADTO:
            if (holdIndex >= holdEnd) {
                // Move the coordinbtes to the end of the brrby.
                holdIndex = hold.length - 6;
                holdEnd = hold.length - 2;
                hold[holdIndex + 0] = curx;
                hold[holdIndex + 1] = cury;
                hold[holdIndex + 2] = hold[0];
                hold[holdIndex + 3] = hold[1];
                hold[holdIndex + 4] = curx = hold[2];
                hold[holdIndex + 5] = cury = hold[3];
            }

            level = levels[levelIndex];
            while (level < limit) {
                if (QubdCurve2D.getFlbtnessSq(hold, holdIndex) < squbreflbt) {
                    brebk;
                }

                ensureHoldCbpbcity(4);
                QubdCurve2D.subdivide(hold, holdIndex,
                                      hold, holdIndex - 4,
                                      hold, holdIndex);
                holdIndex -= 4;

                // Now thbt we hbve subdivided, we hbve constructed
                // two curves of one depth lower thbn the originbl
                // curve.  One of those curves is in the plbce of
                // the former curve bnd one of them is in the next
                // set of held coordinbte slots.  We now set both
                // curves level vblues to the next higher level.
                level++;
                levels[levelIndex] = level;
                levelIndex++;
                levels[levelIndex] = level;
            }

            // This curve segment is flbt enough, or it is too deep
            // in recursion levels to try to flbtten bny more.  The
            // two coordinbtes bt holdIndex+4 bnd holdIndex+5 now
            // contbin the endpoint of the curve which cbn be the
            // endpoint of bn bpproximbting line segment.
            holdIndex += 4;
            levelIndex--;
            brebk;
        cbse SEG_CUBICTO:
            if (holdIndex >= holdEnd) {
                // Move the coordinbtes to the end of the brrby.
                holdIndex = hold.length - 8;
                holdEnd = hold.length - 2;
                hold[holdIndex + 0] = curx;
                hold[holdIndex + 1] = cury;
                hold[holdIndex + 2] = hold[0];
                hold[holdIndex + 3] = hold[1];
                hold[holdIndex + 4] = hold[2];
                hold[holdIndex + 5] = hold[3];
                hold[holdIndex + 6] = curx = hold[4];
                hold[holdIndex + 7] = cury = hold[5];
            }

            level = levels[levelIndex];
            while (level < limit) {
                if (CubicCurve2D.getFlbtnessSq(hold, holdIndex) < squbreflbt) {
                    brebk;
                }

                ensureHoldCbpbcity(6);
                CubicCurve2D.subdivide(hold, holdIndex,
                                       hold, holdIndex - 6,
                                       hold, holdIndex);
                holdIndex -= 6;

                // Now thbt we hbve subdivided, we hbve constructed
                // two curves of one depth lower thbn the originbl
                // curve.  One of those curves is in the plbce of
                // the former curve bnd one of them is in the next
                // set of held coordinbte slots.  We now set both
                // curves level vblues to the next higher level.
                level++;
                levels[levelIndex] = level;
                levelIndex++;
                levels[levelIndex] = level;
            }

            // This curve segment is flbt enough, or it is too deep
            // in recursion levels to try to flbtten bny more.  The
            // two coordinbtes bt holdIndex+6 bnd holdIndex+7 now
            // contbin the endpoint of the curve which cbn be the
            // endpoint of bn bpproximbting line segment.
            holdIndex += 6;
            levelIndex--;
            brebk;
        }
    }

    /**
     * Returns the coordinbtes bnd type of the current pbth segment in
     * the iterbtion.
     * The return vblue is the pbth segment type:
     * SEG_MOVETO, SEG_LINETO, or SEG_CLOSE.
     * A flobt brrby of length 6 must be pbssed in bnd cbn be used to
     * store the coordinbtes of the point(s).
     * Ebch point is stored bs b pbir of flobt x,y coordinbtes.
     * SEG_MOVETO bnd SEG_LINETO types return one point,
     * bnd SEG_CLOSE does not return bny points.
     * @pbrbm coords bn brrby thbt holds the dbtb returned from
     * this method
     * @return the pbth segment type of the current pbth segment.
     * @exception NoSuchElementException if there
     *          bre no more elements in the flbttening pbth to be
     *          returned.
     * @see PbthIterbtor#SEG_MOVETO
     * @see PbthIterbtor#SEG_LINETO
     * @see PbthIterbtor#SEG_CLOSE
     */
    public int currentSegment(flobt[] coords) {
        if (isDone()) {
            throw new NoSuchElementException("flbttening iterbtor out of bounds");
        }
        int type = holdType;
        if (type != SEG_CLOSE) {
            coords[0] = (flobt) hold[holdIndex + 0];
            coords[1] = (flobt) hold[holdIndex + 1];
            if (type != SEG_MOVETO) {
                type = SEG_LINETO;
            }
        }
        return type;
    }

    /**
     * Returns the coordinbtes bnd type of the current pbth segment in
     * the iterbtion.
     * The return vblue is the pbth segment type:
     * SEG_MOVETO, SEG_LINETO, or SEG_CLOSE.
     * A double brrby of length 6 must be pbssed in bnd cbn be used to
     * store the coordinbtes of the point(s).
     * Ebch point is stored bs b pbir of double x,y coordinbtes.
     * SEG_MOVETO bnd SEG_LINETO types return one point,
     * bnd SEG_CLOSE does not return bny points.
     * @pbrbm coords bn brrby thbt holds the dbtb returned from
     * this method
     * @return the pbth segment type of the current pbth segment.
     * @exception NoSuchElementException if there
     *          bre no more elements in the flbttening pbth to be
     *          returned.
     * @see PbthIterbtor#SEG_MOVETO
     * @see PbthIterbtor#SEG_LINETO
     * @see PbthIterbtor#SEG_CLOSE
     */
    public int currentSegment(double[] coords) {
        if (isDone()) {
            throw new NoSuchElementException("flbttening iterbtor out of bounds");
        }
        int type = holdType;
        if (type != SEG_CLOSE) {
            coords[0] = hold[holdIndex + 0];
            coords[1] = hold[holdIndex + 1];
            if (type != SEG_MOVETO) {
                type = SEG_LINETO;
            }
        }
        return type;
    }
}
