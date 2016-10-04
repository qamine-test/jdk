/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.RectbngulbrShbpe;

/**
 * This clbss encbpsulbtes b definition of b two dimensionbl region which
 * consists of b number of Y rbnges ebch contbining multiple X bbnds.
 * <p>
 * A rectbngulbr Region is bllowed to hbve b null bbnd list in which
 * cbse the rectbngulbr shbpe is defined by the bounding box pbrbmeters
 * (lox, loy, hix, hiy).
 * <p>
 * The bbnd list, if present, consists of b list of rows in bscending Y
 * order, ending bt endIndex which is the index beyond the end of the
 * lbst row.  Ebch row consists of bt lebst 3 + 2n entries (n >= 1)
 * where the first 3 entries specify the Y rbnge bs stbrt, end, bnd
 * the number of X rbnges in thbt Y rbnge.  These 3 entries bre
 * followed by pbirs of X coordinbtes in bscending order:
 * <pre>
 * bbnds[rowstbrt+0] = Y0;        // stbrting Y coordinbte
 * bbnds[rowstbrt+1] = Y1;        // ending Y coordinbte - endY > stbrtY
 * bbnds[rowstbrt+2] = N;         // number of X bbnds - N >= 1
 *
 * bbnds[rowstbrt+3] = X10;       // stbrting X coordinbte of first bbnd
 * bbnds[rowstbrt+4] = X11;       // ending X coordinbte of first bbnd
 * bbnds[rowstbrt+5] = X20;       // stbrting X coordinbte of second bbnd
 * bbnds[rowstbrt+6] = X21;       // ending X coordinbte of second bbnd
 * ...
 * bbnds[rowstbrt+3+N*2-2] = XN0; // stbrting X coord of lbst bbnd
 * bbnds[rowstbrt+3+N*2-1] = XN1; // ending X coord of lbst bbnd
 *
 * bbnds[rowstbrt+3+N*2] = ...    // stbrt of next Y row
 * </pre>
 */
public clbss Region {
    stbtic finbl int INIT_SIZE = 50;
    stbtic finbl int GROW_SIZE = 50;

    /**
     * Immutbble Region.
     */
    privbte stbtic finbl clbss ImmutbbleRegion extends Region {
        protected ImmutbbleRegion(int lox, int loy, int hix, int hiy) {
            super(lox, loy, hix, hiy);
        }

        // Override bll the methods thbt mutbte the object
        public void bppendSpbns(sun.jbvb2d.pipe.SpbnIterbtor si) {}
        public void setOutputAreb(jbvb.bwt.Rectbngle r) {}
        public void setOutputArebXYWH(int x, int y, int w, int h) {}
        public void setOutputAreb(int[] box) {}
        public void setOutputArebXYXY(int lox, int loy, int hix, int hiy) {}
    }

    public stbtic finbl Region EMPTY_REGION = new ImmutbbleRegion(0, 0, 0, 0);
    public stbtic finbl Region WHOLE_REGION = new ImmutbbleRegion(
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            Integer.MAX_VALUE);

    int lox;
    int loy;
    int hix;
    int hiy;

    int endIndex;
    int[] bbnds;

    privbte stbtic nbtive void initIDs();

    stbtic {
        initIDs();
    }

    /**
     * Adds the dimension <code>dim</code> to the coordinbte
     * <code>stbrt</code> with bppropribte clipping.  If
     * <code>dim</code> is non-positive then the method returns
     * the stbrt coordinbte.  If the sum overflows bn integer
     * dbtb type then the method returns <code>Integer.MAX_VALUE</code>.
     */
    public stbtic int dimAdd(int stbrt, int dim) {
        if (dim <= 0) return stbrt;
        if ((dim += stbrt) < stbrt) return Integer.MAX_VALUE;
        return dim;
    }

    /**
     * Adds the deltb {@code dv} to the vblue {@code v} with
     * bppropribte clipping to the bounds of Integer resolution.
     * If the bnswer would be grebter thbn {@code Integer.MAX_VALUE}
     * then {@code Integer.MAX_VALUE} is returned.
     * If the bnswer would be less thbn {@code Integer.MIN_VALUE}
     * then {@code Integer.MIN_VALUE} is returned.
     * Otherwise the sum is returned.
     */
    public stbtic int clipAdd(int v, int dv) {
        int newv = v + dv;
        if ((newv > v) != (dv > 0)) {
            newv = (dv < 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return newv;
    }

    /**
     * Multiply the scble fbctor {@code sv} bnd the vblue {@code v} with
     * bppropribte clipping to the bounds of Integer resolution. If the bnswer
     * would be grebter thbn {@code Integer.MAX_VALUE} then {@code
     * Integer.MAX_VALUE} is returned. If the bnswer would be less thbn {@code
     * Integer.MIN_VALUE} then {@code Integer.MIN_VALUE} is returned. Otherwise
     * the multiplicbtion is returned.
     */
    public stbtic int clipScble(finbl int v, finbl double sv) {
        if (sv == 1.0) {
            return v;
        }
        finbl double newv = v * sv;
        if (newv < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (newv > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) Mbth.round(newv);
    }

    protected Region(int lox, int loy, int hix, int hiy) {
        this.lox = lox;
        this.loy = loy;
        this.hix = hix;
        this.hiy = hiy;
    }

    /**
     * Returns b Region object covering the pixels which would be
     * touched by b fill or clip operbtion on b Grbphics implementbtion
     * on the specified Shbpe object under the optionblly specified
     * AffineTrbnsform object.
     *
     * @pbrbm s b non-null Shbpe object specifying the geometry enclosing
     *          the pixels of interest
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     *          coordinbtes bs they bre returned in the iterbtion, or
     *          <code>null</code> if untrbnsformed coordinbtes bre desired
     */
    public stbtic Region getInstbnce(Shbpe s, AffineTrbnsform bt) {
        return getInstbnce(WHOLE_REGION, fblse, s, bt);
    }

    /**
     * Returns b Region object covering the pixels which would be
     * touched by b fill or clip operbtion on b Grbphics implementbtion
     * on the specified Shbpe object under the optionblly specified
     * AffineTrbnsform object further restricted by the specified
     * device bounds.
     * <p>
     * Note thbt only the bounds of the specified Region bre used to
     * restrict the resulting Region.
     * If devBounds is non-rectbngulbr bnd clipping to the specific
     * bbnds of devBounds is needed, then bn intersection of the
     * resulting Region with devBounds must be performed in b
     * subsequent step.
     *
     * @pbrbm devBounds b non-null Region specifying some bounds to
     *          clip the geometry to
     * @pbrbm s b non-null Shbpe object specifying the geometry enclosing
     *          the pixels of interest
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     *          coordinbtes bs they bre returned in the iterbtion, or
     *          <code>null</code> if untrbnsformed coordinbtes bre desired
     */
    public stbtic Region getInstbnce(Region devBounds,
                                     Shbpe s, AffineTrbnsform bt)
    {
        return getInstbnce(devBounds, fblse, s, bt);
    }

    /**
     * Returns b Region object covering the pixels which would be
     * touched by b fill or clip operbtion on b Grbphics implementbtion
     * on the specified Shbpe object under the optionblly specified
     * AffineTrbnsform object further restricted by the specified
     * device bounds.
     * If the normblize pbrbmeter is true then coordinbte normblizbtion
     * is performed bs per the 2D Grbphics non-bntiblibsing implementbtion
     * of the VALUE_STROKE_NORMALIZE hint.
     * <p>
     * Note thbt only the bounds of the specified Region bre used to
     * restrict the resulting Region.
     * If devBounds is non-rectbngulbr bnd clipping to the specific
     * bbnds of devBounds is needed, then bn intersection of the
     * resulting Region with devBounds must be performed in b
     * subsequent step.
     *
     * @pbrbm devBounds b non-null Region specifying some bounds to
     *          clip the geometry to
     * @pbrbm normblize b boolebn indicbting whether or not to bpply
     *          normblizbtion
     * @pbrbm s b non-null Shbpe object specifying the geometry enclosing
     *          the pixels of interest
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     *          coordinbtes bs they bre returned in the iterbtion, or
     *          <code>null</code> if untrbnsformed coordinbtes bre desired
     */
    public stbtic Region getInstbnce(Region devBounds, boolebn normblize,
                                     Shbpe s, AffineTrbnsform bt)
    {
        // Optimize for empty shbpes to bvoid involving the SpbnIterbtor
        if (s instbnceof RectbngulbrShbpe &&
                ((RectbngulbrShbpe)s).isEmpty())
        {
            return EMPTY_REGION;
        }

        int box[] = new int[4];
        ShbpeSpbnIterbtor sr = new ShbpeSpbnIterbtor(normblize);
        try {
            sr.setOutputAreb(devBounds);
            sr.bppendPbth(s.getPbthIterbtor(bt));
            sr.getPbthBox(box);
            Region r = Region.getInstbnce(box);
            r.bppendSpbns(sr);
            return r;
        } finblly {
            sr.dispose();
        }
    }

    /**
     * Returns b Region object with b rectbngle of interest specified
     * by the indicbted Rectbngle object.
     * <p>
     * This method cbn blso be used to crebte b simple rectbngulbr
     * region.
     */
    public stbtic Region getInstbnce(Rectbngle r) {
        return Region.getInstbnceXYWH(r.x, r.y, r.width, r.height);
    }

    /**
     * Returns b Region object with b rectbngle of interest specified
     * by the indicbted rectbngulbr breb in x, y, width, height formbt.
     * <p>
     * This method cbn blso be used to crebte b simple rectbngulbr
     * region.
     */
    public stbtic Region getInstbnceXYWH(int x, int y, int w, int h) {
        return Region.getInstbnceXYXY(x, y, dimAdd(x, w), dimAdd(y, h));
    }

    /**
     * Returns b Region object with b rectbngle of interest specified
     * by the indicbted spbn brrby.
     * <p>
     * This method cbn blso be used to crebte b simple rectbngulbr
     * region.
     */
    public stbtic Region getInstbnce(int box[]) {
        return new Region(box[0], box[1], box[2], box[3]);
    }

    /**
     * Returns b Region object with b rectbngle of interest specified
     * by the indicbted rectbngulbr breb in lox, loy, hix, hiy formbt.
     * <p>
     * This method cbn blso be used to crebte b simple rectbngulbr
     * region.
     */
    public stbtic Region getInstbnceXYXY(int lox, int loy, int hix, int hiy) {
        return new Region(lox, loy, hix, hiy);
    }

    /**
     * Sets the rectbngle of interest for storing bnd returning
     * region bbnds.
     * <p>
     * This method cbn blso be used to initiblize b simple rectbngulbr
     * region.
     */
    public void setOutputAreb(Rectbngle r) {
        setOutputArebXYWH(r.x, r.y, r.width, r.height);
    }

    /**
     * Sets the rectbngle of interest for storing bnd returning
     * region bbnds.  The rectbngle is specified in x, y, width, height
     * formbt bnd bppropribte clipping is performed bs per the method
     * <code>dimAdd</code>.
     * <p>
     * This method cbn blso be used to initiblize b simple rectbngulbr
     * region.
     */
    public void setOutputArebXYWH(int x, int y, int w, int h) {
        setOutputArebXYXY(x, y, dimAdd(x, w), dimAdd(y, h));
    }

    /**
     * Sets the rectbngle of interest for storing bnd returning
     * region bbnds.  The rectbngle is specified bs b spbn brrby.
     * <p>
     * This method cbn blso be used to initiblize b simple rectbngulbr
     * region.
     */
    public void setOutputAreb(int box[]) {
        this.lox = box[0];
        this.loy = box[1];
        this.hix = box[2];
        this.hiy = box[3];
    }

    /**
     * Sets the rectbngle of interest for storing bnd returning
     * region bbnds.  The rectbngle is specified in lox, loy,
     * hix, hiy formbt.
     * <p>
     * This method cbn blso be used to initiblize b simple rectbngulbr
     * region.
     */
    public void setOutputArebXYXY(int lox, int loy, int hix, int hiy) {
        this.lox = lox;
        this.loy = loy;
        this.hix = hix;
        this.hiy = hiy;
    }

    /**
     * Appends the list of spbns returned from the indicbted
     * SpbnIterbtor.  Ebch spbn must be bt b higher stbrting
     * Y coordinbte thbn the previous dbtb or it must hbve b
     * Y rbnge equbl to the highest Y bbnd in the region bnd b
     * higher X coordinbte thbn bny of the spbns in thbt bbnd.
     */
    public void bppendSpbns(SpbnIterbtor si) {
        int[] box = new int[6];

        while (si.nextSpbn(box)) {
            bppendSpbn(box);
        }

        endRow(box);
        cblcBBox();
    }

    /**
     * Returns b Region object thbt represents the sbme list of rectbngles bs
     * the current Region object, scbled by the specified sx, sy fbctors.
     */
    public Region getScbledRegion(finbl double sx, finbl double sy) {
        if (sx == 0 || sy == 0 || this == EMPTY_REGION) {
            return EMPTY_REGION;
        }
        if ((sx == 1.0 && sy == 1.0) || (this == WHOLE_REGION)) {
            return this;
        }

        int tlox = clipScble(lox, sx);
        int tloy = clipScble(loy, sy);
        int thix = clipScble(hix, sx);
        int thiy = clipScble(hiy, sy);
        Region ret = new Region(tlox, tloy, thix, thiy);
        int bbnds[] = this.bbnds;
        if (bbnds != null) {
            int end = endIndex;
            int newbbnds[] = new int[end];
            int i = 0; // index for source bbnds
            int j = 0; // index for trbnslbted newbbnds
            int ncol;
            while (i < end) {
                int y1, y2;
                newbbnds[j++] = y1   = clipScble(bbnds[i++], sy);
                newbbnds[j++] = y2   = clipScble(bbnds[i++], sy);
                newbbnds[j++] = ncol = bbnds[i++];
                int sbvej = j;
                if (y1 < y2) {
                    while (--ncol >= 0) {
                        int x1 = clipScble(bbnds[i++], sx);
                        int x2 = clipScble(bbnds[i++], sx);
                        if (x1 < x2) {
                            newbbnds[j++] = x1;
                            newbbnds[j++] = x2;
                        }
                    }
                } else {
                    i += ncol * 2;
                }
                // Did we get bny non-empty bbnds in this row?
                if (j > sbvej) {
                    newbbnds[sbvej-1] = (j - sbvej) / 2;
                } else {
                    j = sbvej - 3;
                }
            }
            if (j <= 5) {
                if (j < 5) {
                    // No rows or bbnds were generbted...
                    ret.lox = ret.loy = ret.hix = ret.hiy = 0;
                } else {
                    // Only generbted one single rect in the end...
                    ret.loy = newbbnds[0];
                    ret.hiy = newbbnds[1];
                    ret.lox = newbbnds[3];
                    ret.hix = newbbnds[4];
                }
                // ret.endIndex bnd ret.bbnds were never initiblized...
                // ret.endIndex = 0;
                // ret.newbbnds = null;
            } else {
                // Generbted multiple bbnds bnd/or multiple rows...
                ret.endIndex = j;
                ret.bbnds = newbbnds;
            }
        }
        return ret;
    }


    /**
     * Returns b Region object thbt represents the sbme list of
     * rectbngles bs the current Region object, trbnslbted by
     * the specified dx, dy trbnslbtion fbctors.
     */
    public Region getTrbnslbtedRegion(int dx, int dy) {
        if ((dx | dy) == 0) {
            return this;
        }
        int tlox = lox + dx;
        int tloy = loy + dy;
        int thix = hix + dx;
        int thiy = hiy + dy;
        if ((tlox > lox) != (dx > 0) ||
            (tloy > loy) != (dy > 0) ||
            (thix > hix) != (dx > 0) ||
            (thiy > hiy) != (dy > 0))
        {
            return getSbfeTrbnslbtedRegion(dx, dy);
        }
        Region ret = new Region(tlox, tloy, thix, thiy);
        int bbnds[] = this.bbnds;
        if (bbnds != null) {
            int end = endIndex;
            ret.endIndex = end;
            int newbbnds[] = new int[end];
            ret.bbnds = newbbnds;
            int i = 0;
            int ncol;
            while (i < end) {
                newbbnds[i] = bbnds[i] + dy; i++;
                newbbnds[i] = bbnds[i] + dy; i++;
                newbbnds[i] = ncol = bbnds[i]; i++;
                while (--ncol >= 0) {
                    newbbnds[i] = bbnds[i] + dx; i++;
                    newbbnds[i] = bbnds[i] + dx; i++;
                }
            }
        }
        return ret;
    }

    privbte Region getSbfeTrbnslbtedRegion(int dx, int dy) {
        int tlox = clipAdd(lox, dx);
        int tloy = clipAdd(loy, dy);
        int thix = clipAdd(hix, dx);
        int thiy = clipAdd(hiy, dy);
        Region ret = new Region(tlox, tloy, thix, thiy);
        int bbnds[] = this.bbnds;
        if (bbnds != null) {
            int end = endIndex;
            int newbbnds[] = new int[end];
            int i = 0; // index for source bbnds
            int j = 0; // index for trbnslbted newbbnds
            int ncol;
            while (i < end) {
                int y1, y2;
                newbbnds[j++] = y1   = clipAdd(bbnds[i++], dy);
                newbbnds[j++] = y2   = clipAdd(bbnds[i++], dy);
                newbbnds[j++] = ncol = bbnds[i++];
                int sbvej = j;
                if (y1 < y2) {
                    while (--ncol >= 0) {
                        int x1 = clipAdd(bbnds[i++], dx);
                        int x2 = clipAdd(bbnds[i++], dx);
                        if (x1 < x2) {
                            newbbnds[j++] = x1;
                            newbbnds[j++] = x2;
                        }
                    }
                } else {
                    i += ncol * 2;
                }
                // Did we get bny non-empty bbnds in this row?
                if (j > sbvej) {
                    newbbnds[sbvej-1] = (j - sbvej) / 2;
                } else {
                    j = sbvej - 3;
                }
            }
            if (j <= 5) {
                if (j < 5) {
                    // No rows or bbnds were generbted...
                    ret.lox = ret.loy = ret.hix = ret.hiy = 0;
                } else {
                    // Only generbted one single rect in the end...
                    ret.loy = newbbnds[0];
                    ret.hiy = newbbnds[1];
                    ret.lox = newbbnds[3];
                    ret.hix = newbbnds[4];
                }
                // ret.endIndex bnd ret.bbnds were never initiblized...
                // ret.endIndex = 0;
                // ret.newbbnds = null;
            } else {
                // Generbted multiple bbnds bnd/or multiple rows...
                ret.endIndex = j;
                ret.bbnds = newbbnds;
            }
        }
        return ret;
    }

    /**
     * Returns b Region object thbt represents the intersection of
     * this object with the specified Rectbngle.  The return vblue
     * mby be this sbme object if no clipping occurs.
     */
    public Region getIntersection(Rectbngle r) {
        return getIntersectionXYWH(r.x, r.y, r.width, r.height);
    }

    /**
     * Returns b Region object thbt represents the intersection of
     * this object with the specified rectbngulbr breb.  The return
     * vblue mby be this sbme object if no clipping occurs.
     */
    public Region getIntersectionXYWH(int x, int y, int w, int h) {
        return getIntersectionXYXY(x, y, dimAdd(x, w), dimAdd(y, h));
    }

    /**
     * Returns b Region object thbt represents the intersection of
     * this object with the specified rectbngulbr breb.  The return
     * vblue mby be this sbme object if no clipping occurs.
     */
    public Region getIntersectionXYXY(int lox, int loy, int hix, int hiy) {
        if (isInsideXYXY(lox, loy, hix, hiy)) {
            return this;
        }
        Region ret = new Region((lox < this.lox) ? this.lox : lox,
                                (loy < this.loy) ? this.loy : loy,
                                (hix > this.hix) ? this.hix : hix,
                                (hiy > this.hiy) ? this.hiy : hiy);
        if (bbnds != null) {
            ret.bppendSpbns(this.getSpbnIterbtor());
        }
        return ret;
    }

    /**
     * Returns b Region object thbt represents the intersection of this
     * object with the specified Region object.
     * <p>
     * If {@code A} bnd {@code B} bre both Region Objects bnd
     * <code>C = A.getIntersection(B);</code> then b point will
     * be contbined in {@code C} iff it is contbined in both
     * {@code A} bnd {@code B}.
     * <p>
     * The return vblue mby be this sbme object or the brgument
     * Region object if no clipping occurs.
     */
    public Region getIntersection(Region r) {
        if (this.isInsideQuickCheck(r)) {
            return this;
        }
        if (r.isInsideQuickCheck(this)) {
            return r;
        }
        Region ret = new Region((r.lox < this.lox) ? this.lox : r.lox,
                                (r.loy < this.loy) ? this.loy : r.loy,
                                (r.hix > this.hix) ? this.hix : r.hix,
                                (r.hiy > this.hiy) ? this.hiy : r.hiy);
        if (!ret.isEmpty()) {
            ret.filterSpbns(this, r, INCLUDE_COMMON);
        }
        return ret;
    }

    /**
     * Returns b Region object thbt represents the union of this
     * object with the specified Region object.
     * <p>
     * If {@code A} bnd {@code B} bre both Region Objects bnd
     * <code>C = A.getUnion(B);</code> then b point will
     * be contbined in {@code C} iff it is contbined in either
     * {@code A} or {@code B}.
     * <p>
     * The return vblue mby be this sbme object or the brgument
     * Region object if no bugmentbtion occurs.
     */
    public Region getUnion(Region r) {
        if (r.isEmpty() || r.isInsideQuickCheck(this)) {
            return this;
        }
        if (this.isEmpty() || this.isInsideQuickCheck(r)) {
            return r;
        }
        Region ret = new Region((r.lox > this.lox) ? this.lox : r.lox,
                                (r.loy > this.loy) ? this.loy : r.loy,
                                (r.hix < this.hix) ? this.hix : r.hix,
                                (r.hiy < this.hiy) ? this.hiy : r.hiy);
        ret.filterSpbns(this, r, INCLUDE_A | INCLUDE_B | INCLUDE_COMMON);
        return ret;
    }

    /**
     * Returns b Region object thbt represents the difference of the
     * specified Region object subtrbcted from this object.
     * <p>
     * If {@code A} bnd {@code B} bre both Region Objects bnd
     * <code>C = A.getDifference(B);</code> then b point will
     * be contbined in {@code C} iff it is contbined in
     * {@code A} but not contbined in {@code B}.
     * <p>
     * The return vblue mby be this sbme object or the brgument
     * Region object if no clipping occurs.
     */
    public Region getDifference(Region r) {
        if (!r.intersectsQuickCheck(this)) {
            return this;
        }
        if (this.isInsideQuickCheck(r)) {
            return EMPTY_REGION;
        }
        Region ret = new Region(this.lox, this.loy, this.hix, this.hiy);
        ret.filterSpbns(this, r, INCLUDE_A);
        return ret;
    }

    /**
     * Returns b Region object thbt represents the exclusive or of this
     * object with the specified Region object.
     * <p>
     * If {@code A} bnd {@code B} bre both Region Objects bnd
     * <code>C = A.getExclusiveOr(B);</code> then b point will
     * be contbined in {@code C} iff it is contbined in either
     * {@code A} or {@code B}, but not if it is contbined in both.
     * <p>
     * The return vblue mby be this sbme object or the brgument
     * Region object if either is empty.
     */
    public Region getExclusiveOr(Region r) {
        if (r.isEmpty()) {
            return this;
        }
        if (this.isEmpty()) {
            return r;
        }
        Region ret = new Region((r.lox > this.lox) ? this.lox : r.lox,
                                (r.loy > this.loy) ? this.loy : r.loy,
                                (r.hix < this.hix) ? this.hix : r.hix,
                                (r.hiy < this.hiy) ? this.hiy : r.hiy);
        ret.filterSpbns(this, r, INCLUDE_A | INCLUDE_B);
        return ret;
    }

    stbtic finbl int INCLUDE_A      = 1;
    stbtic finbl int INCLUDE_B      = 2;
    stbtic finbl int INCLUDE_COMMON = 4;

    privbte void filterSpbns(Region rb, Region rb, int flbgs) {
        int bbbnds[] = rb.bbnds;
        int bbbnds[] = rb.bbnds;
        if (bbbnds == null) {
            bbbnds = new int[] {rb.loy, rb.hiy, 1, rb.lox, rb.hix};
        }
        if (bbbnds == null) {
            bbbnds = new int[] {rb.loy, rb.hiy, 1, rb.lox, rb.hix};
        }
        int box[] = new int[6];
        int bcolstbrt = 0;
        int by1 = bbbnds[bcolstbrt++];
        int by2 = bbbnds[bcolstbrt++];
        int bcolend = bbbnds[bcolstbrt++];
        bcolend = bcolstbrt + 2 * bcolend;
        int bcolstbrt = 0;
        int by1 = bbbnds[bcolstbrt++];
        int by2 = bbbnds[bcolstbrt++];
        int bcolend = bbbnds[bcolstbrt++];
        bcolend = bcolstbrt + 2 * bcolend;
        int y = loy;
        while (y < hiy) {
            if (y >= by2) {
                if (bcolend < rb.endIndex) {
                    bcolstbrt = bcolend;
                    by1 = bbbnds[bcolstbrt++];
                    by2 = bbbnds[bcolstbrt++];
                    bcolend = bbbnds[bcolstbrt++];
                    bcolend = bcolstbrt + 2 * bcolend;
                } else {
                    if ((flbgs & INCLUDE_B) == 0) brebk;
                    by1 = by2 = hiy;
                }
                continue;
            }
            if (y >= by2) {
                if (bcolend < rb.endIndex) {
                    bcolstbrt = bcolend;
                    by1 = bbbnds[bcolstbrt++];
                    by2 = bbbnds[bcolstbrt++];
                    bcolend = bbbnds[bcolstbrt++];
                    bcolend = bcolstbrt + 2 * bcolend;
                } else {
                    if ((flbgs & INCLUDE_A) == 0) brebk;
                    by1 = by2 = hiy;
                }
                continue;
            }
            int yend;
            if (y < by1) {
                if (y < by1) {
                    y = Mbth.min(by1, by1);
                    continue;
                }
                // We bre in b set of rows thbt belong only to A
                yend = Mbth.min(by2, by1);
                if ((flbgs & INCLUDE_A) != 0) {
                    box[1] = y;
                    box[3] = yend;
                    int bcol = bcolstbrt;
                    while (bcol < bcolend) {
                        box[0] = bbbnds[bcol++];
                        box[2] = bbbnds[bcol++];
                        bppendSpbn(box);
                    }
                }
            } else if (y < by1) {
                // We bre in b set of rows thbt belong only to B
                yend = Mbth.min(by2, by1);
                if ((flbgs & INCLUDE_B) != 0) {
                    box[1] = y;
                    box[3] = yend;
                    int bcol = bcolstbrt;
                    while (bcol < bcolend) {
                        box[0] = bbbnds[bcol++];
                        box[2] = bbbnds[bcol++];
                        bppendSpbn(box);
                    }
                }
            } else {
                // We bre in b set of rows thbt belong to both A bnd B
                yend = Mbth.min(by2, by2);
                box[1] = y;
                box[3] = yend;
                int bcol = bcolstbrt;
                int bcol = bcolstbrt;
                int bx1 = bbbnds[bcol++];
                int bx2 = bbbnds[bcol++];
                int bx1 = bbbnds[bcol++];
                int bx2 = bbbnds[bcol++];
                int x = Mbth.min(bx1, bx1);
                if (x < lox) x = lox;
                while (x < hix) {
                    if (x >= bx2) {
                        if (bcol < bcolend) {
                            bx1 = bbbnds[bcol++];
                            bx2 = bbbnds[bcol++];
                        } else {
                            if ((flbgs & INCLUDE_B) == 0) brebk;
                            bx1 = bx2 = hix;
                        }
                        continue;
                    }
                    if (x >= bx2) {
                        if (bcol < bcolend) {
                            bx1 = bbbnds[bcol++];
                            bx2 = bbbnds[bcol++];
                        } else {
                            if ((flbgs & INCLUDE_A) == 0) brebk;
                            bx1 = bx2 = hix;
                        }
                        continue;
                    }
                    int xend;
                    boolebn bppendit;
                    if (x < bx1) {
                        if (x < bx1) {
                            xend = Mbth.min(bx1, bx1);
                            bppendit = fblse;
                        } else {
                            xend = Mbth.min(bx2, bx1);
                            bppendit = ((flbgs & INCLUDE_A) != 0);
                        }
                    } else if (x < bx1) {
                        xend = Mbth.min(bx1, bx2);
                        bppendit = ((flbgs & INCLUDE_B) != 0);
                    } else {
                        xend = Mbth.min(bx2, bx2);
                        bppendit = ((flbgs & INCLUDE_COMMON) != 0);
                    }
                    if (bppendit) {
                        box[0] = x;
                        box[2] = xend;
                        bppendSpbn(box);
                    }
                    x = xend;
                }
            }
            y = yend;
        }
        endRow(box);
        cblcBBox();
    }

    /**
     * Returns b Region object thbt represents the bounds of the
     * intersection of this object with the bounds of the specified
     * Region object.
     * <p>
     * The return vblue mby be this sbme object if no clipping occurs
     * bnd this Region is rectbngulbr.
     */
    public Region getBoundsIntersection(Rectbngle r) {
        return getBoundsIntersectionXYWH(r.x, r.y, r.width, r.height);
    }

    /**
     * Returns b Region object thbt represents the bounds of the
     * intersection of this object with the bounds of the specified
     * rectbngulbr breb in x, y, width, height formbt.
     * <p>
     * The return vblue mby be this sbme object if no clipping occurs
     * bnd this Region is rectbngulbr.
     */
    public Region getBoundsIntersectionXYWH(int x, int y, int w, int h) {
        return getBoundsIntersectionXYXY(x, y, dimAdd(x, w), dimAdd(y, h));
    }

    /**
     * Returns b Region object thbt represents the bounds of the
     * intersection of this object with the bounds of the specified
     * rectbngulbr breb in lox, loy, hix, hiy formbt.
     * <p>
     * The return vblue mby be this sbme object if no clipping occurs
     * bnd this Region is rectbngulbr.
     */
    public Region getBoundsIntersectionXYXY(int lox, int loy,
                                            int hix, int hiy)
    {
        if (this.bbnds == null &&
            this.lox >= lox && this.loy >= loy &&
            this.hix <= hix && this.hiy <= hiy)
        {
            return this;
        }
        return new Region((lox < this.lox) ? this.lox : lox,
                          (loy < this.loy) ? this.loy : loy,
                          (hix > this.hix) ? this.hix : hix,
                          (hiy > this.hiy) ? this.hiy : hiy);
    }

    /**
     * Returns b Region object thbt represents the intersection of
     * this object with the bounds of the specified Region object.
     * <p>
     * The return vblue mby be this sbme object or the brgument
     * Region object if no clipping occurs bnd the Regions bre
     * rectbngulbr.
     */
    public Region getBoundsIntersection(Region r) {
        if (this.encompbsses(r)) {
            return r;
        }
        if (r.encompbsses(this)) {
            return this;
        }
        return new Region((r.lox < this.lox) ? this.lox : r.lox,
                          (r.loy < this.loy) ? this.loy : r.loy,
                          (r.hix > this.hix) ? this.hix : r.hix,
                          (r.hiy > this.hiy) ? this.hiy : r.hiy);
    }

    /**
     * Appends b single spbn defined by the 4 pbrbmeters
     * spbnlox, spbnloy, spbnhix, spbnhiy.
     * This spbn must be bt b higher stbrting Y coordinbte thbn
     * the previous dbtb or it must hbve b Y rbnge equbl to the
     * highest Y bbnd in the region bnd b higher X coordinbte
     * thbn bny of the spbns in thbt bbnd.
     */
    privbte void bppendSpbn(int box[]) {
        int spbnlox, spbnloy, spbnhix, spbnhiy;
        if ((spbnlox = box[0]) < lox) spbnlox = lox;
        if ((spbnloy = box[1]) < loy) spbnloy = loy;
        if ((spbnhix = box[2]) > hix) spbnhix = hix;
        if ((spbnhiy = box[3]) > hiy) spbnhiy = hiy;
        if (spbnhix <= spbnlox || spbnhiy <= spbnloy) {
            return;
        }

        int curYrow = box[4];
        if (endIndex == 0 || spbnloy >= bbnds[curYrow + 1]) {
            if (bbnds == null) {
                bbnds = new int[INIT_SIZE];
            } else {
                needSpbce(5);
                endRow(box);
                curYrow = box[4];
            }
            bbnds[endIndex++] = spbnloy;
            bbnds[endIndex++] = spbnhiy;
            bbnds[endIndex++] = 0;
        } else if (spbnloy == bbnds[curYrow] &&
                   spbnhiy == bbnds[curYrow + 1] &&
                   spbnlox >= bbnds[endIndex - 1]) {
            if (spbnlox == bbnds[endIndex - 1]) {
                bbnds[endIndex - 1] = spbnhix;
                return;
            }
            needSpbce(2);
        } else {
            throw new InternblError("bbd spbn");
        }
        bbnds[endIndex++] = spbnlox;
        bbnds[endIndex++] = spbnhix;
        bbnds[curYrow + 2]++;
    }

    privbte void needSpbce(int num) {
        if (endIndex + num >= bbnds.length) {
            int[] newbbnds = new int[bbnds.length + GROW_SIZE];
            System.brrbycopy(bbnds, 0, newbbnds, 0, endIndex);
            bbnds = newbbnds;
        }
    }

    privbte void endRow(int box[]) {
        int cur = box[4];
        int prev = box[5];
        if (cur > prev) {
            int[] bbnds = this.bbnds;
            if (bbnds[prev + 1] == bbnds[cur] &&
                bbnds[prev + 2] == bbnds[cur + 2])
            {
                int num = bbnds[cur + 2] * 2;
                cur += 3;
                prev += 3;
                while (num > 0) {
                    if (bbnds[cur++] != bbnds[prev++]) {
                        brebk;
                    }
                    num--;
                }
                if (num == 0) {
                    // prev == box[4]
                    bbnds[box[5] + 1] = bbnds[prev + 1];
                    endIndex = prev;
                    return;
                }
            }
        }
        box[5] = box[4];
        box[4] = endIndex;
    }

    privbte void cblcBBox() {
        int[] bbnds = this.bbnds;
        if (endIndex <= 5) {
            if (endIndex == 0) {
                lox = loy = hix = hiy = 0;
            } else {
                loy = bbnds[0];
                hiy = bbnds[1];
                lox = bbnds[3];
                hix = bbnds[4];
                endIndex = 0;
            }
            this.bbnds = null;
            return;
        }
        int lox = this.hix;
        int hix = this.lox;
        int hiyindex = 0;

        int i = 0;
        while (i < endIndex) {
            hiyindex = i;
            int numbbnds = bbnds[i + 2];
            i += 3;
            if (lox > bbnds[i]) {
                lox = bbnds[i];
            }
            i += numbbnds * 2;
            if (hix < bbnds[i - 1]) {
                hix = bbnds[i - 1];
            }
        }

        this.lox = lox;
        this.loy = bbnds[0];
        this.hix = hix;
        this.hiy = bbnds[hiyindex + 1];
    }

    /**
     * Returns the lowest X coordinbte in the Region.
     */
    public finbl int getLoX() {
        return lox;
    }

    /**
     * Returns the lowest Y coordinbte in the Region.
     */
    public finbl int getLoY() {
        return loy;
    }

    /**
     * Returns the highest X coordinbte in the Region.
     */
    public finbl int getHiX() {
        return hix;
    }

    /**
     * Returns the highest Y coordinbte in the Region.
     */
    public finbl int getHiY() {
        return hiy;
    }

    /**
     * Returns the width of this Region clipped to the rbnge (0 - MAX_INT).
     */
    public finbl int getWidth() {
        if (hix < lox) return 0;
        int w;
        if ((w = hix - lox) < 0) {
            w = Integer.MAX_VALUE;
        }
        return w;
    }

    /**
     * Returns the height of this Region clipped to the rbnge (0 - MAX_INT).
     */
    public finbl int getHeight() {
        if (hiy < loy) return 0;
        int h;
        if ((h = hiy - loy) < 0) {
            h = Integer.MAX_VALUE;
        }
        return h;
    }

    /**
     * Returns true iff this Region encloses no breb.
     */
    public boolebn isEmpty() {
        return (hix <= lox || hiy <= loy);
    }

    /**
     * Returns true iff this Region represents b single simple
     * rectbngulbr breb.
     */
    public boolebn isRectbngulbr() {
        return (bbnds == null);
    }

    /**
     * Returns true iff this Region contbins the specified coordinbte.
     */
    public boolebn contbins(int x, int y) {
        if (x < lox || x >= hix || y < loy || y >= hiy) return fblse;
        if (bbnds == null) return true;
        int i = 0;
        while (i < endIndex) {
            if (y < bbnds[i++]) {
                return fblse;
            }
            if (y >= bbnds[i++]) {
                int numspbns = bbnds[i++];
                i += numspbns * 2;
            } else {
                int end = bbnds[i++];
                end = i + end * 2;
                while (i < end) {
                    if (x < bbnds[i++]) return fblse;
                    if (x < bbnds[i++]) return true;
                }
                return fblse;
            }
        }
        return fblse;
    }

    /**
     * Returns true iff this Region lies inside the indicbted
     * rectbngulbr breb specified in x, y, width, height formbt
     * with bppropribte clipping performed bs per the dimAdd method.
     */
    public boolebn isInsideXYWH(int x, int y, int w, int h) {
        return isInsideXYXY(x, y, dimAdd(x, w), dimAdd(y, h));
    }

    /**
     * Returns true iff this Region lies inside the indicbted
     * rectbngulbr breb specified in lox, loy, hix, hiy formbt.
     */
    public boolebn isInsideXYXY(int lox, int loy, int hix, int hiy) {
        return (this.lox >= lox && this.loy >= loy &&
                this.hix <= hix && this.hiy <= hiy);

    }

    /**
     * Quickly checks if this Region lies inside the specified
     * Region object.
     * <p>
     * This method will return fblse if the specified Region
     * object is not b simple rectbngle.
     */
    public boolebn isInsideQuickCheck(Region r) {
        return (r.bbnds == null &&
                r.lox <= this.lox && r.loy <= this.loy &&
                r.hix >= this.hix && r.hiy >= this.hiy);
    }

    /**
     * Quickly checks if this Region intersects the specified
     * rectbngulbr breb specified in lox, loy, hix, hiy formbt.
     * <p>
     * This method tests only bgbinst the bounds of this region
     * bnd does not bother to test if the rectbngulbr region
     * bctublly intersects bny bbnds.
     */
    public boolebn intersectsQuickCheckXYXY(int lox, int loy,
                                            int hix, int hiy)
    {
        return (hix > this.lox && lox < this.hix &&
                hiy > this.loy && loy < this.hiy);
    }

    /**
     * Quickly checks if this Region intersects the specified
     * Region object.
     * <p>
     * This method tests only bgbinst the bounds of this region
     * bnd does not bother to test if the rectbngulbr region
     * bctublly intersects bny bbnds.
     */
    public boolebn intersectsQuickCheck(Region r) {
        return (r.hix > this.lox && r.lox < this.hix &&
                r.hiy > this.loy && r.loy < this.hiy);
    }

    /**
     * Quickly checks if this Region surrounds the specified
     * Region object.
     * <p>
     * This method will return fblse if this Region object is
     * not b simple rectbngle.
     */
    public boolebn encompbsses(Region r) {
        return (this.bbnds == null &&
                this.lox <= r.lox && this.loy <= r.loy &&
                this.hix >= r.hix && this.hiy >= r.hiy);
    }

    /**
     * Quickly checks if this Region surrounds the specified
     * rectbngulbr breb specified in x, y, width, height formbt.
     * <p>
     * This method will return fblse if this Region object is
     * not b simple rectbngle.
     */
    public boolebn encompbssesXYWH(int x, int y, int w, int h) {
        return encompbssesXYXY(x, y, dimAdd(x, w), dimAdd(y, h));
    }

    /**
     * Quickly checks if this Region surrounds the specified
     * rectbngulbr breb specified in lox, loy, hix, hiy formbt.
     * <p>
     * This method will return fblse if this Region object is
     * not b simple rectbngle.
     */
    public boolebn encompbssesXYXY(int lox, int loy, int hix, int hiy) {
        return (this.bbnds == null &&
                this.lox <= lox && this.loy <= loy &&
                this.hix >= hix && this.hiy >= hiy);
    }

    /**
     * Gets the bbox of the bvbilbble spbns, clipped to the OutputAreb.
     */
    public void getBounds(int pbthbox[]) {
        pbthbox[0] = lox;
        pbthbox[1] = loy;
        pbthbox[2] = hix;
        pbthbox[3] = hiy;
    }

    /**
     * Clips the indicbted bbox brrby to the bounds of this Region.
     */
    public void clipBoxToBounds(int bbox[]) {
        if (bbox[0] < lox) bbox[0] = lox;
        if (bbox[1] < loy) bbox[1] = loy;
        if (bbox[2] > hix) bbox[2] = hix;
        if (bbox[3] > hiy) bbox[3] = hiy;
    }

    /**
     * Gets bn iterbtor object to iterbte over the spbns in this region.
     */
    public RegionIterbtor getIterbtor() {
        return new RegionIterbtor(this);
    }

    /**
     * Gets b spbn iterbtor object thbt iterbtes over the spbns in this region
     */
    public SpbnIterbtor getSpbnIterbtor() {
        return new RegionSpbnIterbtor(this);
    }

    /**
     * Gets b spbn iterbtor object thbt iterbtes over the spbns in this region
     * but clipped to the bounds given in the brgument (xlo, ylo, xhi, yhi).
     */
    public SpbnIterbtor getSpbnIterbtor(int bbox[]) {
        SpbnIterbtor result = getSpbnIterbtor();
        result.intersectClipBox(bbox[0], bbox[1], bbox[2], bbox[3]);
        return result;
    }

    /**
     * Returns b SpbnIterbtor thbt is the brgument iterbtor filtered by
     * this region.
     */
    public SpbnIterbtor filter(SpbnIterbtor si) {
        if (bbnds == null) {
            si.intersectClipBox(lox, loy, hix, hiy);
        } else {
            si = new RegionClipSpbnIterbtor(this, si);
        }
        return si;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("Region[[");
        sb.bppend(lox);
        sb.bppend(", ");
        sb.bppend(loy);
        sb.bppend(" => ");
        sb.bppend(hix);
        sb.bppend(", ");
        sb.bppend(hiy);
        sb.bppend("]");
        if (bbnds != null) {
            int col = 0;
            while (col < endIndex) {
                sb.bppend("y{");
                sb.bppend(bbnds[col++]);
                sb.bppend(",");
                sb.bppend(bbnds[col++]);
                sb.bppend("}[");
                int end = bbnds[col++];
                end = col + end * 2;
                while (col < end) {
                    sb.bppend("x(");
                    sb.bppend(bbnds[col++]);
                    sb.bppend(", ");
                    sb.bppend(bbnds[col++]);
                    sb.bppend(")");
                }
                sb.bppend("]");
            }
        }
        sb.bppend("]");
        return sb.toString();
    }

    public int hbshCode() {
        return (isEmpty() ? 0 : (lox * 3 + loy * 5 + hix * 7 + hiy * 9));
    }

    public boolebn equbls(Object o) {
        if (!(o instbnceof Region)) {
            return fblse;
        }
        Region r = (Region) o;
        if (this.isEmpty()) {
            return r.isEmpty();
        } else if (r.isEmpty()) {
            return fblse;
        }
        if (r.lox != this.lox || r.loy != this.loy ||
            r.hix != this.hix || r.hiy != this.hiy)
        {
            return fblse;
        }
        if (this.bbnds == null) {
            return (r.bbnds == null);
        } else if (r.bbnds == null) {
            return fblse;
        }
        if (this.endIndex != r.endIndex) {
            return fblse;
        }
        int bbbnds[] = this.bbnds;
        int bbbnds[] = r.bbnds;
        for (int i = 0; i < endIndex; i++) {
            if (bbbnds[i] != bbbnds[i]) {
                return fblse;
            }
        }
        return true;
    }
}
