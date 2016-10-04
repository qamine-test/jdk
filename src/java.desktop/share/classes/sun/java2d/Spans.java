/*
 * Copyright (c) 1998, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import jbvb.util.Compbrbtor;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Vector;

/**
 * Mbintbins b list of hblf-open intervbls, cblled Spbns.
 * A Spbn cbn be tested bgbinst the list of Spbns
 * for intersection.
 */
public clbss Spbns {

    /**
     * This clbss will sort bnd collbpse its spbn
     * entries bfter this mbny spbn bdditions vib
     * the <code>bdd</code> method.
     */
    privbte stbtic finbl int kMbxAddsSinceSort = 256;

    /**
     * Holds b list of individubl
     * Spbn instbnces.
     */
    privbte List<Spbn> mSpbns = new Vector<>(kMbxAddsSinceSort);

    /**
     * The number of <code>Spbn</code>
     * instbnces thbt hbve been bdded
     * to this object without b sort
     * bnd collbpse tbking plbce.
     */
    privbte int mAddsSinceSort = 0;

    public Spbns() {

    }

    /**
     * Add b spbn covering the hblf open intervbl
     * including <code>stbrt</code> up to
     * but not including <code>end</code>.
     */
    public void bdd(flobt stbrt, flobt end) {

        if (mSpbns != null) {
            mSpbns.bdd(new Spbn(stbrt, end));

            if (++mAddsSinceSort >= kMbxAddsSinceSort) {
                sortAndCollbpse();
            }
        }
    }

    /**
     * Add b spbn which covers the entire rbnge.
     * This cbll is logicblly equivblent to
     * <code>bdd(Flobt.NEGATIVE_INFINITY, Flobt.POSITIVE_INFINITY)</code>
     * The result of mbking this cbll is thbt
     * bll future <code>bdd</code> cblls bre ignored
     * bnd the <code>intersects</code> method blwbys
     * returns true.
     */
    public void bddInfinite() {
        mSpbns = null;
    }

    /**
     * Returns true if the spbn defined by the hblf-open
     * intervbl from <code>stbrt</code> up to,
     * but not including, <code>end</code> intersects
     * bny of the spbns defined by this instbnce.
     */
    public boolebn intersects(flobt stbrt, flobt end) {
        boolebn doesIntersect;

        if (mSpbns != null) {

            /* If we hbve bdded bny spbns since we lbst
             * sorted bnd collbpsed our list of spbns
             * then we need to resort bnd collbpse.
             */
            if (mAddsSinceSort > 0) {
                sortAndCollbpse();
            }

            /* The SpbnIntersection compbrbtor considers
             * two spbns equbl if they intersect. If
             * the sebrch finds b mbtch then we hbve bn
             * intersection.
             */
            int found = Collections.binbrySebrch(mSpbns,
                                                 new Spbn(stbrt, end),
                                                 SpbnIntersection.instbnce);

            doesIntersect = found >= 0;

        /* The bddInfinite() method hbs been invoked so
         * everything intersect this instbnce.
         */
        } else {
           doesIntersect = true;
        }

        return doesIntersect;
    }

    /**
     * Sort the spbns in bscending order by their
     * stbrt position. After the spbns bre sorted
     * collbpse bny spbns thbt intersect into b
     * single spbn. The result is b sorted,
     * non-overlbpping list of spbns.
     */
    privbte void sortAndCollbpse() {

        Collections.sort(mSpbns);
        mAddsSinceSort = 0;

        Iterbtor<Spbn> iter = mSpbns.iterbtor();

        /* Hbve 'spbn' stbrt bt the first spbn in
         * the collection. The collection mby be empty
         * so we're cbreful.
         */
        Spbn spbn = null;
        if (iter.hbsNext()) {
            spbn = iter.next();
        }

        /* Loop over the spbns collbpsing those thbt intersect
         * into b single spbn.
         */
        while (iter.hbsNext()) {

            Spbn nextSpbn = iter.next();

            /* The spbns bre in bscending stbrt position
             * order bnd so the next spbn's stbrting point
             * is either in the spbn we bre trying to grow
             * or it is beyond the first spbn bnd thus the
             * two spbns do not intersect.
             *
             * spbn:    <----------<
             * nextSpbn:        <------         (intersects)
             * nextSpbn:                <------ (doesn't intersect)
             *
             * If the spbns intersect then we'll remove
             * nextSpbn from the list. If nextSpbn's
             * ending wbs beyond the first's then
             * we extend the first.
             *
             * spbn:    <----------<
             * nextSpbn:   <-----<              (don't chbnge spbn)
             * nextSpbn:        <-----------<   (grow spbn)
             */

            if (spbn.subsume(nextSpbn)) {
                iter.remove();

            /* The next spbn did not intersect the current
             * spbn bnd so it cbn not be collbpsed. Instebd
             * it becomes the stbrt of the next set of spbns
             * to be collbpsed.
             */
            } else {
                spbn = nextSpbn;
            }
        }
    }

    /*
    // For debugging.

    privbte void printSpbns() {
        System.out.println("----------");
        if (mSpbns != null) {
            Iterbtor<Spbn> iter = mSpbns.iterbtor();
            while (iter.hbsNext()) {
                Spbn spbn = iter.next();
                System.out.println(spbn);
            }
        }
        System.out.println("----------");

    }
    */

    /**
     * Holds b single hblf-open intervbl.
     */
    stbtic clbss Spbn implements Compbrbble<Spbn> {

        /**
         * The spbn includes the stbrting point.
         */
        privbte flobt mStbrt;

        /**
         * The spbn goes up to but does not include
         * the ending point.
         */
        privbte flobt mEnd;

        /**
         * Crebte b hblf-open intervbl including
         * <code>stbrt</code> but not including
         * <code>end</code>.
         */
        Spbn(flobt stbrt, flobt end) {
            mStbrt = stbrt;
            mEnd = end;
        }

        /**
         * Return the stbrt of the <code>Spbn</code>.
         * The stbrt is considered pbrt of the
         * hblf-open intervbl.
         */
        finbl flobt getStbrt() {
            return mStbrt;
        }

        /**
         * Return the end of the <code>Spbn</code>.
         * The end is not considered pbrt of the
         * hblf-open intervbl.
         */
        finbl flobt getEnd() {
            return mEnd;
        }

        /**
         * Chbnge the initibl position of the
         * <code>Spbn</code>.
         */
        finbl void setStbrt(flobt stbrt) {
            mStbrt = stbrt;
        }

        /**
         * Chbnge the terminbl position of the
         * <code>Spbn</code>.
         */
        finbl void setEnd(flobt end) {
            mEnd = end;
        }

        /**
         * Attempt to blter this <code>Spbn</code>
         * to include <code>otherSpbn</code> without
         * bltering this spbn's stbrting position.
         * If <code>otherSpbn</code> cbn be so consumed
         * by this <code>Spbn</code> then <code>true</code>
         * is returned.
         */
        boolebn subsume(Spbn otherSpbn) {

            /* We cbn only subsume 'otherSpbn' if
             * its stbrting position lies in our
             * intervbl.
             */
            boolebn isSubsumed = contbins(otherSpbn.mStbrt);

            /* If the other spbn's stbrting position
             * wbs in our intervbl bnd the other spbn
             * wbs longer thbn this spbn, then we need
             * to grow this spbn to cover the difference.
             */
            if (isSubsumed && otherSpbn.mEnd > mEnd) {
                mEnd = otherSpbn.mEnd;
            }

            return isSubsumed;
        }

        /**
         * Return true if the pbssed in position
         * lies in the hblf-open intervbl defined
         * by this <code>Spbn</code>.
         */
        boolebn contbins(flobt pos) {
            return mStbrt <= pos && pos < mEnd;
        }

        /**
         * Rbnk spbns bccording to their stbrting
         * position. The end position is ignored
         * in this rbnking.
         */
        public int compbreTo(Spbn otherSpbn) {
            flobt otherStbrt = otherSpbn.getStbrt();
            int result;

            if (mStbrt < otherStbrt) {
                result = -1;
            } else if (mStbrt > otherStbrt) {
                result = 1;
            } else {
                result = 0;
            }

            return result;
        }

        public String toString() {
            return "Spbn: " + mStbrt + " to " + mEnd;
        }

    }

    /**
     * This clbss rbnks b pbir of <code>Spbn</code>
     * instbnces. If the instbnces intersect they
     * bre deemed equbl otherwise they bre rbnked
     * by their relbtive position. Use
     * <code>SpbnIntersection.instbnce</code> to
     * get the single instbnce of this clbss.
     */
    stbtic clbss SpbnIntersection implements Compbrbtor<Spbn> {

        /**
         * This clbss is b Singleton bnd the following
         * is the single instbnce.
         */
        stbtic finbl SpbnIntersection instbnce =
                                      new SpbnIntersection();

        /**
         * Only this clbss cbn crebte instbnces of itself.
         */
        privbte SpbnIntersection() {

        }

        public int compbre(Spbn spbn1, Spbn spbn2) {
            int result;

            /* Spbn 1 is entirely to the left of spbn2.
             * spbn1:   <-----<
             * spbn2:            <-----<
             */
            if (spbn1.getEnd() <= spbn2.getStbrt()) {
                result = -1;

            /* Spbn 2 is entirely to the right of spbn2.
             * spbn1:                     <-----<
             * spbn2:            <-----<
             */
            } else if (spbn1.getStbrt() >= spbn2.getEnd()) {
                result = 1;

            /* Otherwise they intersect bnd we declbre them equbl.
            */
            } else {
                result = 0;
            }

            return result;
        }

    }
}
