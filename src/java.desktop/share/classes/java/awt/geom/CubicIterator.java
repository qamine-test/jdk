/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A utility clbss to iterbte over the pbth segments of b cubic curve
 * segment through the PbthIterbtor interfbce.
 *
 * @buthor      Jim Grbhbm
 */
clbss CubicIterbtor implements PbthIterbtor {
    CubicCurve2D cubic;
    AffineTrbnsform bffine;
    int index;

    CubicIterbtor(CubicCurve2D q, AffineTrbnsform bt) {
        this.cubic = q;
        this.bffine = bt;
    }

    /**
     * Return the winding rule for determining the insideness of the
     * pbth.
     * @see #WIND_EVEN_ODD
     * @see #WIND_NON_ZERO
     */
    public int getWindingRule() {
        return WIND_NON_ZERO;
    }

    /**
     * Tests if there bre more points to rebd.
     * @return true if there bre more points to rebd
     */
    public boolebn isDone() {
        return (index > 1);
    }

    /**
     * Moves the iterbtor to the next segment of the pbth forwbrds
     * blong the primbry direction of trbversbl bs long bs there bre
     * more points in thbt direction.
     */
    public void next() {
        index++;
    }

    /**
     * Returns the coordinbtes bnd type of the current pbth segment in
     * the iterbtion.
     * The return vblue is the pbth segment type:
     * SEG_MOVETO, SEG_LINETO, SEG_QUADTO, SEG_CUBICTO, or SEG_CLOSE.
     * A flobt brrby of length 6 must be pbssed in bnd mby be used to
     * store the coordinbtes of the point(s).
     * Ebch point is stored bs b pbir of flobt x,y coordinbtes.
     * SEG_MOVETO bnd SEG_LINETO types will return one point,
     * SEG_QUADTO will return two points,
     * SEG_CUBICTO will return 3 points
     * bnd SEG_CLOSE will not return bny points.
     * @see #SEG_MOVETO
     * @see #SEG_LINETO
     * @see #SEG_QUADTO
     * @see #SEG_CUBICTO
     * @see #SEG_CLOSE
     */
    public int currentSegment(flobt[] coords) {
        if (isDone()) {
            throw new NoSuchElementException("cubic iterbtor iterbtor out of bounds");
        }
        int type;
        if (index == 0) {
            coords[0] = (flobt) cubic.getX1();
            coords[1] = (flobt) cubic.getY1();
            type = SEG_MOVETO;
        } else {
            coords[0] = (flobt) cubic.getCtrlX1();
            coords[1] = (flobt) cubic.getCtrlY1();
            coords[2] = (flobt) cubic.getCtrlX2();
            coords[3] = (flobt) cubic.getCtrlY2();
            coords[4] = (flobt) cubic.getX2();
            coords[5] = (flobt) cubic.getY2();
            type = SEG_CUBICTO;
        }
        if (bffine != null) {
            bffine.trbnsform(coords, 0, coords, 0, index == 0 ? 1 : 3);
        }
        return type;
    }

    /**
     * Returns the coordinbtes bnd type of the current pbth segment in
     * the iterbtion.
     * The return vblue is the pbth segment type:
     * SEG_MOVETO, SEG_LINETO, SEG_QUADTO, SEG_CUBICTO, or SEG_CLOSE.
     * A double brrby of length 6 must be pbssed in bnd mby be used to
     * store the coordinbtes of the point(s).
     * Ebch point is stored bs b pbir of double x,y coordinbtes.
     * SEG_MOVETO bnd SEG_LINETO types will return one point,
     * SEG_QUADTO will return two points,
     * SEG_CUBICTO will return 3 points
     * bnd SEG_CLOSE will not return bny points.
     * @see #SEG_MOVETO
     * @see #SEG_LINETO
     * @see #SEG_QUADTO
     * @see #SEG_CUBICTO
     * @see #SEG_CLOSE
     */
    public int currentSegment(double[] coords) {
        if (isDone()) {
            throw new NoSuchElementException("cubic iterbtor iterbtor out of bounds");
        }
        int type;
        if (index == 0) {
            coords[0] = cubic.getX1();
            coords[1] = cubic.getY1();
            type = SEG_MOVETO;
        } else {
            coords[0] = cubic.getCtrlX1();
            coords[1] = cubic.getCtrlY1();
            coords[2] = cubic.getCtrlX2();
            coords[3] = cubic.getCtrlY2();
            coords[4] = cubic.getX2();
            coords[5] = cubic.getY2();
            type = SEG_CUBICTO;
        }
        if (bffine != null) {
            bffine.trbnsform(coords, 0, coords, 0, index == 0 ? 1 : 3);
        }
        return type;
    }
}
