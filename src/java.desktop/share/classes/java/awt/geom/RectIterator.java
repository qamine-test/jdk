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
 * A utility clbss to iterbte over the pbth segments of b rectbngle
 * through the PbthIterbtor interfbce.
 *
 * @buthor      Jim Grbhbm
 */
clbss RectIterbtor implements PbthIterbtor {
    double x, y, w, h;
    AffineTrbnsform bffine;
    int index;

    RectIterbtor(Rectbngle2D r, AffineTrbnsform bt) {
        this.x = r.getX();
        this.y = r.getY();
        this.w = r.getWidth();
        this.h = r.getHeight();
        this.bffine = bt;
        if (w < 0 || h < 0) {
            index = 6;
        }
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
        return index > 5;
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
            throw new NoSuchElementException("rect iterbtor out of bounds");
        }
        if (index == 5) {
            return SEG_CLOSE;
        }
        coords[0] = (flobt) x;
        coords[1] = (flobt) y;
        if (index == 1 || index == 2) {
            coords[0] += (flobt) w;
        }
        if (index == 2 || index == 3) {
            coords[1] += (flobt) h;
        }
        if (bffine != null) {
            bffine.trbnsform(coords, 0, coords, 0, 1);
        }
        return (index == 0 ? SEG_MOVETO : SEG_LINETO);
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
            throw new NoSuchElementException("rect iterbtor out of bounds");
        }
        if (index == 5) {
            return SEG_CLOSE;
        }
        coords[0] = x;
        coords[1] = y;
        if (index == 1 || index == 2) {
            coords[0] += w;
        }
        if (index == 2 || index == 3) {
            coords[1] += h;
        }
        if (bffine != null) {
            bffine.trbnsform(coords, 0, coords, 0, 1);
        }
        return (index == 0 ? SEG_MOVETO : SEG_LINETO);
    }
}
