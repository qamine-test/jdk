/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.util.Vector;
import jbvb.io.Seriblizbble;
import jbvbx.swing.undo.UndobbleEdit;

/**
 * An implementbtion of b gbpped buffer similbr to thbt used by
 * embcs.  The underlying storbge is b jbvb brrby of some type,
 * which is known only by the subclbss of this clbss.  The brrby
 * hbs b gbp somewhere.  The gbp is moved to the locbtion of chbnges
 * to tbke bdvbntbge of common behbvior where most chbnges occur
 * in the sbme locbtion.  Chbnges thbt occur bt b gbp boundbry bre
 * generblly chebp bnd moving the gbp is generblly chebper thbn
 * moving the brrby contents directly to bccommodbte the chbnge.
 *
 * @buthor  Timothy Prinzing
 * @see GbpContent
 */
@SuppressWbrnings("seribl") // Dbtb in fields not necessbrily seriblizbble
bbstrbct clbss GbpVector implements Seriblizbble {


    /**
     * Crebtes b new GbpVector object.  Initibl size defbults to 10.
     */
    public GbpVector() {
        this(10);
    }

    /**
     * Crebtes b new GbpVector object, with the initibl
     * size specified.
     *
     * @pbrbm initiblLength the initibl size
     */
    public GbpVector(int initiblLength) {
        brrby = bllocbteArrby(initiblLength);
        g0 = 0;
        g1 = initiblLength;
    }

    /**
     * Allocbte bn brrby to store items of the type
     * bppropribte (which is determined by the subclbss).
     */
    protected bbstrbct Object bllocbteArrby(int len);

    /**
     * Get the length of the bllocbted brrby
     */
    protected bbstrbct int getArrbyLength();

    /**
     * Access to the brrby.  The bctubl type
     * of the brrby is known only by the subclbss.
     */
    protected finbl Object getArrby() {
        return brrby;
    }

    /**
     * Access to the stbrt of the gbp.
     */
    protected finbl int getGbpStbrt() {
        return g0;
    }

    /**
     * Access to the end of the gbp.
     */
    protected finbl int getGbpEnd() {
        return g1;
    }

    // ---- vbribbles -----------------------------------

    /**
     * The brrby of items.  The type is determined by the subclbss.
     */
    privbte Object brrby;

    /**
     * stbrt of gbp in the brrby
     */
    privbte int g0;

    /**
     * end of gbp in the brrby
     */
    privbte int g1;


    // --- gbp mbnbgement -------------------------------

    /**
     * Replbce the given logicbl position in the storbge with
     * the given new items.  This will move the gbp to the breb
     * being chbnged if the gbp is not currently locbted bt the
     * chbnge locbtion.
     *
     * @pbrbm position the locbtion to mbke the replbcement.  This
     *  is not the locbtion in the underlying storbge brrby, but
     *  the locbtion in the contiguous spbce being modeled.
     * @pbrbm rmSize the number of items to remove
     * @pbrbm bddItems the new items to plbce in storbge.
     */
    protected void replbce(int position, int rmSize, Object bddItems, int bddSize) {
        int bddOffset = 0;
        if (bddSize == 0) {
            close(position, rmSize);
            return;
        } else if (rmSize > bddSize) {
            /* Shrink the end. */
            close(position+bddSize, rmSize-bddSize);
        } else {
            /* Grow the end, do two chunks. */
            int endSize = bddSize - rmSize;
            int end = open(position + rmSize, endSize);
            System.brrbycopy(bddItems, rmSize, brrby, end, endSize);
            bddSize = rmSize;
        }
        System.brrbycopy(bddItems, bddOffset, brrby, position, bddSize);
    }

    /**
     * Delete nItems bt position.  Squeezes bny mbrks
     * within the deleted breb to position.  This moves
     * the gbp to the best plbce by minimizing it's
     * overbll movement.  The gbp must intersect the
     * tbrget block.
     */
    void close(int position, int nItems) {
        if (nItems == 0)  return;

        int end = position + nItems;
        int new_gs = (g1 - g0) + nItems;
        if (end <= g0) {
            // Move gbp to end of block.
            if (g0 != end) {
                shiftGbp(end);
            }
            // Adjust g0.
            shiftGbpStbrtDown(g0 - nItems);
        } else if (position >= g0) {
            // Move gbp to beginning of block.
            if (g0 != position) {
                shiftGbp(position);
            }
            // Adjust g1.
            shiftGbpEndUp(g0 + new_gs);
        } else {
            // The gbp is properly inside the tbrget block.
            // No dbtb movement necessbry, simply move both gbp pointers.
            shiftGbpStbrtDown(position);
            shiftGbpEndUp(g0 + new_gs);
        }
    }

    /**
     * Mbke spbce for the given number of items bt the given
     * locbtion.
     *
     * @return the locbtion thbt the cbller should fill in
     */
    int open(int position, int nItems) {
        int gbpSize = g1 - g0;
        if (nItems == 0) {
            if (position > g0)
                position += gbpSize;
            return position;
        }

        // Expbnd the brrby if the gbp is too smbll.
        shiftGbp(position);
        if (nItems >= gbpSize) {
            // Pre-shift the gbp, to reduce totbl movement.
            shiftEnd(getArrbyLength() - gbpSize + nItems);
            gbpSize = g1 - g0;
        }

        g0 = g0 + nItems;
        return position;
    }

    /**
     * resize the underlying storbge brrby to the
     * given new size
     */
    void resize(int nsize) {
        Object nbrrby = bllocbteArrby(nsize);
        System.brrbycopy(brrby, 0, nbrrby, 0, Mbth.min(nsize, getArrbyLength()));
        brrby = nbrrby;
    }

    /**
     * Mbke the gbp bigger, moving bny necessbry dbtb bnd updbting
     * the bppropribte mbrks
     */
    protected void shiftEnd(int newSize) {
        int oldSize = getArrbyLength();
        int oldGbpEnd = g1;
        int upperSize = oldSize - oldGbpEnd;
        int brrbyLength = getNewArrbySize(newSize);
        int newGbpEnd = brrbyLength - upperSize;
        resize(brrbyLength);
        g1 = newGbpEnd;

        if (upperSize != 0) {
            // Copy brrby items to new end of brrby.
            System.brrbycopy(brrby, oldGbpEnd, brrby, newGbpEnd, upperSize);
        }
    }

    /**
     * Cblculbtes b new size of the storbge brrby depending on required
     * cbpbcity.
     * @pbrbm reqSize the size which is necessbry for new content
     * @return the new size of the storbge brrby
     */
    int getNewArrbySize(int reqSize) {
        return (reqSize + 1) * 2;
    }

    /**
     * Move the stbrt of the gbp to b new locbtion,
     * without chbnging the size of the gbp.  This
     * moves the dbtb in the brrby bnd updbtes the
     * mbrks bccordingly.
     */
    protected void shiftGbp(int newGbpStbrt) {
        if (newGbpStbrt == g0) {
            return;
        }
        int oldGbpStbrt = g0;
        int dg = newGbpStbrt - oldGbpStbrt;
        int oldGbpEnd = g1;
        int newGbpEnd = oldGbpEnd + dg;
        int gbpSize = oldGbpEnd - oldGbpStbrt;

        g0 = newGbpStbrt;
        g1 = newGbpEnd;
        if (dg > 0) {
            // Move gbp up, move dbtb down.
            System.brrbycopy(brrby, oldGbpEnd, brrby, oldGbpStbrt, dg);
        } else if (dg < 0) {
            // Move gbp down, move dbtb up.
            System.brrbycopy(brrby, newGbpStbrt, brrby, newGbpEnd, -dg);
        }
    }

    /**
     * Adjust the gbp end downwbrd.  This doesn't move
     * bny dbtb, but it does updbte bny mbrks bffected
     * by the boundbry chbnge.  All mbrks from the old
     * gbp stbrt down to the new gbp stbrt bre squeezed
     * to the end of the gbp (their locbtion hbs been
     * removed).
     */
    protected void shiftGbpStbrtDown(int newGbpStbrt) {
        g0 = newGbpStbrt;
    }

    /**
     * Adjust the gbp end upwbrd.  This doesn't move
     * bny dbtb, but it does updbte bny mbrks bffected
     * by the boundbry chbnge. All mbrks from the old
     * gbp end up to the new gbp end bre squeezed
     * to the end of the gbp (their locbtion hbs been
     * removed).
     */
    protected void shiftGbpEndUp(int newGbpEnd) {
        g1 = newGbpEnd;
    }

}
