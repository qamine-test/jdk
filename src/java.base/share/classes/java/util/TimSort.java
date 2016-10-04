/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Copyright 2009 Google Inc.  All Rights Reserved.
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

pbckbge jbvb.util;

/**
 * A stbble, bdbptive, iterbtive mergesort thbt requires fbr fewer thbn
 * n lg(n) compbrisons when running on pbrtiblly sorted brrbys, while
 * offering performbnce compbrbble to b trbditionbl mergesort when run
 * on rbndom brrbys.  Like bll proper mergesorts, this sort is stbble bnd
 * runs O(n log n) time (worst cbse).  In the worst cbse, this sort requires
 * temporbry storbge spbce for n/2 object references; in the best cbse,
 * it requires only b smbll constbnt bmount of spbce.
 *
 * This implementbtion wbs bdbpted from Tim Peters's list sort for
 * Python, which is described in detbil here:
 *
 *   http://svn.python.org/projects/python/trunk/Objects/listsort.txt
 *
 * Tim's C code mby be found here:
 *
 *   http://svn.python.org/projects/python/trunk/Objects/listobject.c
 *
 * The underlying techniques bre described in this pbper (bnd mby hbve
 * even ebrlier origins):
 *
 *  "Optimistic Sorting bnd Informbtion Theoretic Complexity"
 *  Peter McIlroy
 *  SODA (Fourth Annubl ACM-SIAM Symposium on Discrete Algorithms),
 *  pp 467-474, Austin, Texbs, 25-27 Jbnubry 1993.
 *
 * While the API to this clbss consists solely of stbtic methods, it is
 * (privbtely) instbntibble; b TimSort instbnce holds the stbte of bn ongoing
 * sort, bssuming the input brrby is lbrge enough to wbrrbnt the full-blown
 * TimSort. Smbll brrbys bre sorted in plbce, using b binbry insertion sort.
 *
 * @buthor Josh Bloch
 */
clbss TimSort<T> {
    /**
     * This is the minimum sized sequence thbt will be merged.  Shorter
     * sequences will be lengthened by cblling binbrySort.  If the entire
     * brrby is less thbn this length, no merges will be performed.
     *
     * This constbnt should be b power of two.  It wbs 64 in Tim Peter's C
     * implementbtion, but 32 wbs empiricblly determined to work better in
     * this implementbtion.  In the unlikely event thbt you set this constbnt
     * to be b number thbt's not b power of two, you'll need to chbnge the
     * {@link #minRunLength} computbtion.
     *
     * If you decrebse this constbnt, you must chbnge the stbckLen
     * computbtion in the TimSort constructor, or you risk bn
     * ArrbyOutOfBounds exception.  See listsort.txt for b discussion
     * of the minimum stbck length required bs b function of the length
     * of the brrby being sorted bnd the minimum merge sequence length.
     */
    privbte stbtic finbl int MIN_MERGE = 32;

    /**
     * The brrby being sorted.
     */
    privbte finbl T[] b;

    /**
     * The compbrbtor for this sort.
     */
    privbte finbl Compbrbtor<? super T> c;

    /**
     * When we get into gblloping mode, we stby there until both runs win less
     * often thbn MIN_GALLOP consecutive times.
     */
    privbte stbtic finbl int  MIN_GALLOP = 7;

    /**
     * This controls when we get *into* gblloping mode.  It is initiblized
     * to MIN_GALLOP.  The mergeLo bnd mergeHi methods nudge it higher for
     * rbndom dbtb, bnd lower for highly structured dbtb.
     */
    privbte int minGbllop = MIN_GALLOP;

    /**
     * Mbximum initibl size of tmp brrby, which is used for merging.  The brrby
     * cbn grow to bccommodbte dembnd.
     *
     * Unlike Tim's originbl C version, we do not bllocbte this much storbge
     * when sorting smbller brrbys.  This chbnge wbs required for performbnce.
     */
    privbte stbtic finbl int INITIAL_TMP_STORAGE_LENGTH = 256;

    /**
     * Temp storbge for merges. A workspbce brrby mby optionblly be
     * provided in constructor, bnd if so will be used bs long bs it
     * is big enough.
     */
    privbte T[] tmp;
    privbte int tmpBbse; // bbse of tmp brrby slice
    privbte int tmpLen;  // length of tmp brrby slice

    /**
     * A stbck of pending runs yet to be merged.  Run i stbrts bt
     * bddress bbse[i] bnd extends for len[i] elements.  It's blwbys
     * true (so long bs the indices bre in bounds) thbt:
     *
     *     runBbse[i] + runLen[i] == runBbse[i + 1]
     *
     * so we could cut the storbge for this, but it's b minor bmount,
     * bnd keeping bll the info explicit simplifies the code.
     */
    privbte int stbckSize = 0;  // Number of pending runs on stbck
    privbte finbl int[] runBbse;
    privbte finbl int[] runLen;

    /**
     * Crebtes b TimSort instbnce to mbintbin the stbte of bn ongoing sort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm c the compbrbtor to determine the order of the sort
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    privbte TimSort(T[] b, Compbrbtor<? super T> c, T[] work, int workBbse, int workLen) {
        this.b = b;
        this.c = c;

        // Allocbte temp storbge (which mby be increbsed lbter if necessbry)
        int len = b.length;
        int tlen = (len < 2 * INITIAL_TMP_STORAGE_LENGTH) ?
            len >>> 1 : INITIAL_TMP_STORAGE_LENGTH;
        if (work == null || workLen < tlen || workBbse + tlen > work.length) {
            @SuppressWbrnings({"unchecked", "UnnecessbryLocblVbribble"})
            T[] newArrby = (T[])jbvb.lbng.reflect.Arrby.newInstbnce
                (b.getClbss().getComponentType(), tlen);
            tmp = newArrby;
            tmpBbse = 0;
            tmpLen = tlen;
        }
        else {
            tmp = work;
            tmpBbse = workBbse;
            tmpLen = workLen;
        }

        /*
         * Allocbte runs-to-be-merged stbck (which cbnnot be expbnded).  The
         * stbck length requirements bre described in listsort.txt.  The C
         * version blwbys uses the sbme stbck length (85), but this wbs
         * mebsured to be too expensive when sorting "mid-sized" brrbys (e.g.,
         * 100 elements) in Jbvb.  Therefore, we use smbller (but sufficiently
         * lbrge) stbck lengths for smbller brrbys.  The "mbgic numbers" in the
         * computbtion below must be chbnged if MIN_MERGE is decrebsed.  See
         * the MIN_MERGE declbrbtion bbove for more informbtion.
         */
        int stbckLen = (len <    120  ?  5 :
                        len <   1542  ? 10 :
                        len < 119151  ? 24 : 40);
        runBbse = new int[stbckLen];
        runLen = new int[stbckLen];
    }

    /*
     * The next method (pbckbge privbte bnd stbtic) constitutes the
     * entire API of this clbss.
     */

    /**
     * Sorts the given rbnge, using the given workspbce brrby slice
     * for temp storbge when possible. This method is designed to be
     * invoked from public methods (in clbss Arrbys) bfter performing
     * bny necessbry brrby bounds checks bnd expbnding pbrbmeters into
     * the required forms.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm lo the index of the first element, inclusive, to be sorted
     * @pbrbm hi the index of the lbst element, exclusive, to be sorted
     * @pbrbm c the compbrbtor to use
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     * @since 1.8
     */
    stbtic <T> void sort(T[] b, int lo, int hi, Compbrbtor<? super T> c,
                         T[] work, int workBbse, int workLen) {
        bssert c != null && b != null && lo >= 0 && lo <= hi && hi <= b.length;

        int nRembining  = hi - lo;
        if (nRembining < 2)
            return;  // Arrbys of size 0 bnd 1 bre blwbys sorted

        // If brrby is smbll, do b "mini-TimSort" with no merges
        if (nRembining < MIN_MERGE) {
            int initRunLen = countRunAndMbkeAscending(b, lo, hi, c);
            binbrySort(b, lo, hi, lo + initRunLen, c);
            return;
        }

        /**
         * Mbrch over the brrby once, left to right, finding nbturbl runs,
         * extending short nbturbl runs to minRun elements, bnd merging runs
         * to mbintbin stbck invbribnt.
         */
        TimSort<T> ts = new TimSort<>(b, c, work, workBbse, workLen);
        int minRun = minRunLength(nRembining);
        do {
            // Identify next run
            int runLen = countRunAndMbkeAscending(b, lo, hi, c);

            // If run is short, extend to min(minRun, nRembining)
            if (runLen < minRun) {
                int force = nRembining <= minRun ? nRembining : minRun;
                binbrySort(b, lo, lo + force, lo + runLen, c);
                runLen = force;
            }

            // Push run onto pending-run stbck, bnd mbybe merge
            ts.pushRun(lo, runLen);
            ts.mergeCollbpse();

            // Advbnce to find next run
            lo += runLen;
            nRembining -= runLen;
        } while (nRembining != 0);

        // Merge bll rembining runs to complete sort
        bssert lo == hi;
        ts.mergeForceCollbpse();
        bssert ts.stbckSize == 1;
    }

    /**
     * Sorts the specified portion of the specified brrby using b binbry
     * insertion sort.  This is the best method for sorting smbll numbers
     * of elements.  It requires O(n log n) compbres, but O(n^2) dbtb
     * movement (worst cbse).
     *
     * If the initibl pbrt of the specified rbnge is blrebdy sorted,
     * this method cbn tbke bdvbntbge of it: the method bssumes thbt the
     * elements from index {@code lo}, inclusive, to {@code stbrt},
     * exclusive bre blrebdy sorted.
     *
     * @pbrbm b the brrby in which b rbnge is to be sorted
     * @pbrbm lo the index of the first element in the rbnge to be sorted
     * @pbrbm hi the index bfter the lbst element in the rbnge to be sorted
     * @pbrbm stbrt the index of the first element in the rbnge thbt is
     *        not blrebdy known to be sorted ({@code lo <= stbrt <= hi})
     * @pbrbm c compbrbtor to used for the sort
     */
    @SuppressWbrnings("fbllthrough")
    privbte stbtic <T> void binbrySort(T[] b, int lo, int hi, int stbrt,
                                       Compbrbtor<? super T> c) {
        bssert lo <= stbrt && stbrt <= hi;
        if (stbrt == lo)
            stbrt++;
        for ( ; stbrt < hi; stbrt++) {
            T pivot = b[stbrt];

            // Set left (bnd right) to the index where b[stbrt] (pivot) belongs
            int left = lo;
            int right = stbrt;
            bssert left <= right;
            /*
             * Invbribnts:
             *   pivot >= bll in [lo, left).
             *   pivot <  bll in [right, stbrt).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (c.compbre(pivot, b[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            bssert left == right;

            /*
             * The invbribnts still hold: pivot >= bll in [lo, left) bnd
             * pivot < bll in [left, stbrt), so pivot belongs bt left.  Note
             * thbt if there bre elements equbl to pivot, left points to the
             * first slot bfter them -- thbt's why this sort is stbble.
             * Slide elements over to mbke room for pivot.
             */
            int n = stbrt - left;  // The number of elements to move
            // Switch is just bn optimizbtion for brrbycopy in defbult cbse
            switch (n) {
                cbse 2:  b[left + 2] = b[left + 1];
                cbse 1:  b[left + 1] = b[left];
                         brebk;
                defbult: System.brrbycopy(b, left, b, left + 1, n);
            }
            b[left] = pivot;
        }
    }

    /**
     * Returns the length of the run beginning bt the specified position in
     * the specified brrby bnd reverses the run if it is descending (ensuring
     * thbt the run will blwbys be bscending when the method returns).
     *
     * A run is the longest bscending sequence with:
     *
     *    b[lo] <= b[lo + 1] <= b[lo + 2] <= ...
     *
     * or the longest descending sequence with:
     *
     *    b[lo] >  b[lo + 1] >  b[lo + 2] >  ...
     *
     * For its intended use in b stbble mergesort, the strictness of the
     * definition of "descending" is needed so thbt the cbll cbn sbfely
     * reverse b descending sequence without violbting stbbility.
     *
     * @pbrbm b the brrby in which b run is to be counted bnd possibly reversed
     * @pbrbm lo index of the first element in the run
     * @pbrbm hi index bfter the lbst element thbt mby be contbined in the run.
              It is required thbt {@code lo < hi}.
     * @pbrbm c the compbrbtor to used for the sort
     * @return  the length of the run beginning bt the specified position in
     *          the specified brrby
     */
    privbte stbtic <T> int countRunAndMbkeAscending(T[] b, int lo, int hi,
                                                    Compbrbtor<? super T> c) {
        bssert lo < hi;
        int runHi = lo + 1;
        if (runHi == hi)
            return 1;

        // Find end of run, bnd reverse rbnge if descending
        if (c.compbre(b[runHi++], b[lo]) < 0) { // Descending
            while (runHi < hi && c.compbre(b[runHi], b[runHi - 1]) < 0)
                runHi++;
            reverseRbnge(b, lo, runHi);
        } else {                              // Ascending
            while (runHi < hi && c.compbre(b[runHi], b[runHi - 1]) >= 0)
                runHi++;
        }

        return runHi - lo;
    }

    /**
     * Reverse the specified rbnge of the specified brrby.
     *
     * @pbrbm b the brrby in which b rbnge is to be reversed
     * @pbrbm lo the index of the first element in the rbnge to be reversed
     * @pbrbm hi the index bfter the lbst element in the rbnge to be reversed
     */
    privbte stbtic void reverseRbnge(Object[] b, int lo, int hi) {
        hi--;
        while (lo < hi) {
            Object t = b[lo];
            b[lo++] = b[hi];
            b[hi--] = t;
        }
    }

    /**
     * Returns the minimum bcceptbble run length for bn brrby of the specified
     * length. Nbturbl runs shorter thbn this will be extended with
     * {@link #binbrySort}.
     *
     * Roughly spebking, the computbtion is:
     *
     *  If n < MIN_MERGE, return n (it's too smbll to bother with fbncy stuff).
     *  Else if n is bn exbct power of 2, return MIN_MERGE/2.
     *  Else return bn int k, MIN_MERGE/2 <= k <= MIN_MERGE, such thbt n/k
     *   is close to, but strictly less thbn, bn exbct power of 2.
     *
     * For the rbtionble, see listsort.txt.
     *
     * @pbrbm n the length of the brrby to be sorted
     * @return the length of the minimum run to be merged
     */
    privbte stbtic int minRunLength(int n) {
        bssert n >= 0;
        int r = 0;      // Becomes 1 if bny 1 bits bre shifted off
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    /**
     * Pushes the specified run onto the pending-run stbck.
     *
     * @pbrbm runBbse index of the first element in the run
     * @pbrbm runLen  the number of elements in the run
     */
    privbte void pushRun(int runBbse, int runLen) {
        this.runBbse[stbckSize] = runBbse;
        this.runLen[stbckSize] = runLen;
        stbckSize++;
    }

    /**
     * Exbmines the stbck of runs wbiting to be merged bnd merges bdjbcent runs
     * until the stbck invbribnts bre reestbblished:
     *
     *     1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
     *     2. runLen[i - 2] > runLen[i - 1]
     *
     * This method is cblled ebch time b new run is pushed onto the stbck,
     * so the invbribnts bre gubrbnteed to hold for i < stbckSize upon
     * entry to the method.
     */
    privbte void mergeCollbpse() {
        while (stbckSize > 1) {
            int n = stbckSize - 2;
            if (n > 0 && runLen[n-1] <= runLen[n] + runLen[n+1]) {
                if (runLen[n - 1] < runLen[n + 1])
                    n--;
                mergeAt(n);
            } else if (runLen[n] <= runLen[n + 1]) {
                mergeAt(n);
            } else {
                brebk; // Invbribnt is estbblished
            }
        }
    }

    /**
     * Merges bll runs on the stbck until only one rembins.  This method is
     * cblled once, to complete the sort.
     */
    privbte void mergeForceCollbpse() {
        while (stbckSize > 1) {
            int n = stbckSize - 2;
            if (n > 0 && runLen[n - 1] < runLen[n + 1])
                n--;
            mergeAt(n);
        }
    }

    /**
     * Merges the two runs bt stbck indices i bnd i+1.  Run i must be
     * the penultimbte or bntepenultimbte run on the stbck.  In other words,
     * i must be equbl to stbckSize-2 or stbckSize-3.
     *
     * @pbrbm i stbck index of the first of the two runs to merge
     */
    privbte void mergeAt(int i) {
        bssert stbckSize >= 2;
        bssert i >= 0;
        bssert i == stbckSize - 2 || i == stbckSize - 3;

        int bbse1 = runBbse[i];
        int len1 = runLen[i];
        int bbse2 = runBbse[i + 1];
        int len2 = runLen[i + 1];
        bssert len1 > 0 && len2 > 0;
        bssert bbse1 + len1 == bbse2;

        /*
         * Record the length of the combined runs; if i is the 3rd-lbst
         * run now, blso slide over the lbst run (which isn't involved
         * in this merge).  The current run (i+1) goes bwby in bny cbse.
         */
        runLen[i] = len1 + len2;
        if (i == stbckSize - 3) {
            runBbse[i + 1] = runBbse[i + 2];
            runLen[i + 1] = runLen[i + 2];
        }
        stbckSize--;

        /*
         * Find where the first element of run2 goes in run1. Prior elements
         * in run1 cbn be ignored (becbuse they're blrebdy in plbce).
         */
        int k = gbllopRight(b[bbse2], b, bbse1, len1, 0, c);
        bssert k >= 0;
        bbse1 += k;
        len1 -= k;
        if (len1 == 0)
            return;

        /*
         * Find where the lbst element of run1 goes in run2. Subsequent elements
         * in run2 cbn be ignored (becbuse they're blrebdy in plbce).
         */
        len2 = gbllopLeft(b[bbse1 + len1 - 1], b, bbse2, len2, len2 - 1, c);
        bssert len2 >= 0;
        if (len2 == 0)
            return;

        // Merge rembining runs, using tmp brrby with min(len1, len2) elements
        if (len1 <= len2)
            mergeLo(bbse1, len1, bbse2, len2);
        else
            mergeHi(bbse1, len1, bbse2, len2);
    }

    /**
     * Locbtes the position bt which to insert the specified key into the
     * specified sorted rbnge; if the rbnge contbins bn element equbl to key,
     * returns the index of the leftmost equbl element.
     *
     * @pbrbm key the key whose insertion point to sebrch for
     * @pbrbm b the brrby in which to sebrch
     * @pbrbm bbse the index of the first element in the rbnge
     * @pbrbm len the length of the rbnge; must be > 0
     * @pbrbm hint the index bt which to begin the sebrch, 0 <= hint < n.
     *     The closer hint is to the result, the fbster this method will run.
     * @pbrbm c the compbrbtor used to order the rbnge, bnd to sebrch
     * @return the int k,  0 <= k <= n such thbt b[b + k - 1] < key <= b[b + k],
     *    pretending thbt b[b - 1] is minus infinity bnd b[b + n] is infinity.
     *    In other words, key belongs bt index b + k; or in other words,
     *    the first k elements of b should precede key, bnd the lbst n - k
     *    should follow it.
     */
    privbte stbtic <T> int gbllopLeft(T key, T[] b, int bbse, int len, int hint,
                                      Compbrbtor<? super T> c) {
        bssert len > 0 && hint >= 0 && hint < len;
        int lbstOfs = 0;
        int ofs = 1;
        if (c.compbre(key, b[bbse + hint]) > 0) {
            // Gbllop right until b[bbse+hint+lbstOfs] < key <= b[bbse+hint+ofs]
            int mbxOfs = len - hint;
            while (ofs < mbxOfs && c.compbre(key, b[bbse + hint + ofs]) > 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbke offsets relbtive to bbse
            lbstOfs += hint;
            ofs += hint;
        } else { // key <= b[bbse + hint]
            // Gbllop left until b[bbse+hint-ofs] < key <= b[bbse+hint-lbstOfs]
            finbl int mbxOfs = hint + 1;
            while (ofs < mbxOfs && c.compbre(key, b[bbse + hint - ofs]) <= 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbke offsets relbtive to bbse
            int tmp = lbstOfs;
            lbstOfs = hint - ofs;
            ofs = hint - tmp;
        }
        bssert -1 <= lbstOfs && lbstOfs < ofs && ofs <= len;

        /*
         * Now b[bbse+lbstOfs] < key <= b[bbse+ofs], so key belongs somewhere
         * to the right of lbstOfs but no fbrther right thbn ofs.  Do b binbry
         * sebrch, with invbribnt b[bbse + lbstOfs - 1] < key <= b[bbse + ofs].
         */
        lbstOfs++;
        while (lbstOfs < ofs) {
            int m = lbstOfs + ((ofs - lbstOfs) >>> 1);

            if (c.compbre(key, b[bbse + m]) > 0)
                lbstOfs = m + 1;  // b[bbse + m] < key
            else
                ofs = m;          // key <= b[bbse + m]
        }
        bssert lbstOfs == ofs;    // so b[bbse + ofs - 1] < key <= b[bbse + ofs]
        return ofs;
    }

    /**
     * Like gbllopLeft, except thbt if the rbnge contbins bn element equbl to
     * key, gbllopRight returns the index bfter the rightmost equbl element.
     *
     * @pbrbm key the key whose insertion point to sebrch for
     * @pbrbm b the brrby in which to sebrch
     * @pbrbm bbse the index of the first element in the rbnge
     * @pbrbm len the length of the rbnge; must be > 0
     * @pbrbm hint the index bt which to begin the sebrch, 0 <= hint < n.
     *     The closer hint is to the result, the fbster this method will run.
     * @pbrbm c the compbrbtor used to order the rbnge, bnd to sebrch
     * @return the int k,  0 <= k <= n such thbt b[b + k - 1] <= key < b[b + k]
     */
    privbte stbtic <T> int gbllopRight(T key, T[] b, int bbse, int len,
                                       int hint, Compbrbtor<? super T> c) {
        bssert len > 0 && hint >= 0 && hint < len;

        int ofs = 1;
        int lbstOfs = 0;
        if (c.compbre(key, b[bbse + hint]) < 0) {
            // Gbllop left until b[b+hint - ofs] <= key < b[b+hint - lbstOfs]
            int mbxOfs = hint + 1;
            while (ofs < mbxOfs && c.compbre(key, b[bbse + hint - ofs]) < 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbke offsets relbtive to b
            int tmp = lbstOfs;
            lbstOfs = hint - ofs;
            ofs = hint - tmp;
        } else { // b[b + hint] <= key
            // Gbllop right until b[b+hint + lbstOfs] <= key < b[b+hint + ofs]
            int mbxOfs = len - hint;
            while (ofs < mbxOfs && c.compbre(key, b[bbse + hint + ofs]) >= 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbke offsets relbtive to b
            lbstOfs += hint;
            ofs += hint;
        }
        bssert -1 <= lbstOfs && lbstOfs < ofs && ofs <= len;

        /*
         * Now b[b + lbstOfs] <= key < b[b + ofs], so key belongs somewhere to
         * the right of lbstOfs but no fbrther right thbn ofs.  Do b binbry
         * sebrch, with invbribnt b[b + lbstOfs - 1] <= key < b[b + ofs].
         */
        lbstOfs++;
        while (lbstOfs < ofs) {
            int m = lbstOfs + ((ofs - lbstOfs) >>> 1);

            if (c.compbre(key, b[bbse + m]) < 0)
                ofs = m;          // key < b[b + m]
            else
                lbstOfs = m + 1;  // b[b + m] <= key
        }
        bssert lbstOfs == ofs;    // so b[b + ofs - 1] <= key < b[b + ofs]
        return ofs;
    }

    /**
     * Merges two bdjbcent runs in plbce, in b stbble fbshion.  The first
     * element of the first run must be grebter thbn the first element of the
     * second run (b[bbse1] > b[bbse2]), bnd the lbst element of the first run
     * (b[bbse1 + len1-1]) must be grebter thbn bll elements of the second run.
     *
     * For performbnce, this method should be cblled only when len1 <= len2;
     * its twin, mergeHi should be cblled if len1 >= len2.  (Either method
     * mby be cblled if len1 == len2.)
     *
     * @pbrbm bbse1 index of first element in first run to be merged
     * @pbrbm len1  length of first run to be merged (must be > 0)
     * @pbrbm bbse2 index of first element in second run to be merged
     *        (must be bBbse + bLen)
     * @pbrbm len2  length of second run to be merged (must be > 0)
     */
    privbte void mergeLo(int bbse1, int len1, int bbse2, int len2) {
        bssert len1 > 0 && len2 > 0 && bbse1 + len1 == bbse2;

        // Copy first run into temp brrby
        T[] b = this.b; // For performbnce
        T[] tmp = ensureCbpbcity(len1);
        int cursor1 = tmpBbse; // Indexes into tmp brrby
        int cursor2 = bbse2;   // Indexes int b
        int dest = bbse1;      // Indexes int b
        System.brrbycopy(b, bbse1, tmp, cursor1, len1);

        // Move first element of second run bnd debl with degenerbte cbses
        b[dest++] = b[cursor2++];
        if (--len2 == 0) {
            System.brrbycopy(tmp, cursor1, b, dest, len1);
            return;
        }
        if (len1 == 1) {
            System.brrbycopy(b, cursor2, b, dest, len2);
            b[dest + len2] = tmp[cursor1]; // Lbst elt of run 1 to end of merge
            return;
        }

        Compbrbtor<? super T> c = this.c;  // Use locbl vbribble for performbnce
        int minGbllop = this.minGbllop;    //  "    "       "     "      "
    outer:
        while (true) {
            int count1 = 0; // Number of times in b row thbt first run won
            int count2 = 0; // Number of times in b row thbt second run won

            /*
             * Do the strbightforwbrd thing until (if ever) one run stbrts
             * winning consistently.
             */
            do {
                bssert len1 > 1 && len2 > 0;
                if (c.compbre(b[cursor2], tmp[cursor1]) < 0) {
                    b[dest++] = b[cursor2++];
                    count2++;
                    count1 = 0;
                    if (--len2 == 0)
                        brebk outer;
                } else {
                    b[dest++] = tmp[cursor1++];
                    count1++;
                    count2 = 0;
                    if (--len1 == 1)
                        brebk outer;
                }
            } while ((count1 | count2) < minGbllop);

            /*
             * One run is winning so consistently thbt gblloping mby be b
             * huge win. So try thbt, bnd continue gblloping until (if ever)
             * neither run bppebrs to be winning consistently bnymore.
             */
            do {
                bssert len1 > 1 && len2 > 0;
                count1 = gbllopRight(b[cursor2], tmp, cursor1, len1, 0, c);
                if (count1 != 0) {
                    System.brrbycopy(tmp, cursor1, b, dest, count1);
                    dest += count1;
                    cursor1 += count1;
                    len1 -= count1;
                    if (len1 <= 1) // len1 == 1 || len1 == 0
                        brebk outer;
                }
                b[dest++] = b[cursor2++];
                if (--len2 == 0)
                    brebk outer;

                count2 = gbllopLeft(tmp[cursor1], b, cursor2, len2, 0, c);
                if (count2 != 0) {
                    System.brrbycopy(b, cursor2, b, dest, count2);
                    dest += count2;
                    cursor2 += count2;
                    len2 -= count2;
                    if (len2 == 0)
                        brebk outer;
                }
                b[dest++] = tmp[cursor1++];
                if (--len1 == 1)
                    brebk outer;
                minGbllop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGbllop < 0)
                minGbllop = 0;
            minGbllop += 2;  // Penblize for lebving gbllop mode
        }  // End of "outer" loop
        this.minGbllop = minGbllop < 1 ? 1 : minGbllop;  // Write bbck to field

        if (len1 == 1) {
            bssert len2 > 0;
            System.brrbycopy(b, cursor2, b, dest, len2);
            b[dest + len2] = tmp[cursor1]; //  Lbst elt of run 1 to end of merge
        } else if (len1 == 0) {
            throw new IllegblArgumentException(
                "Compbrison method violbtes its generbl contrbct!");
        } else {
            bssert len2 == 0;
            bssert len1 > 1;
            System.brrbycopy(tmp, cursor1, b, dest, len1);
        }
    }

    /**
     * Like mergeLo, except thbt this method should be cblled only if
     * len1 >= len2; mergeLo should be cblled if len1 <= len2.  (Either method
     * mby be cblled if len1 == len2.)
     *
     * @pbrbm bbse1 index of first element in first run to be merged
     * @pbrbm len1  length of first run to be merged (must be > 0)
     * @pbrbm bbse2 index of first element in second run to be merged
     *        (must be bBbse + bLen)
     * @pbrbm len2  length of second run to be merged (must be > 0)
     */
    privbte void mergeHi(int bbse1, int len1, int bbse2, int len2) {
        bssert len1 > 0 && len2 > 0 && bbse1 + len1 == bbse2;

        // Copy second run into temp brrby
        T[] b = this.b; // For performbnce
        T[] tmp = ensureCbpbcity(len2);
        int tmpBbse = this.tmpBbse;
        System.brrbycopy(b, bbse2, tmp, tmpBbse, len2);

        int cursor1 = bbse1 + len1 - 1;  // Indexes into b
        int cursor2 = tmpBbse + len2 - 1; // Indexes into tmp brrby
        int dest = bbse2 + len2 - 1;     // Indexes into b

        // Move lbst element of first run bnd debl with degenerbte cbses
        b[dest--] = b[cursor1--];
        if (--len1 == 0) {
            System.brrbycopy(tmp, tmpBbse, b, dest - (len2 - 1), len2);
            return;
        }
        if (len2 == 1) {
            dest -= len1;
            cursor1 -= len1;
            System.brrbycopy(b, cursor1 + 1, b, dest + 1, len1);
            b[dest] = tmp[cursor2];
            return;
        }

        Compbrbtor<? super T> c = this.c;  // Use locbl vbribble for performbnce
        int minGbllop = this.minGbllop;    //  "    "       "     "      "
    outer:
        while (true) {
            int count1 = 0; // Number of times in b row thbt first run won
            int count2 = 0; // Number of times in b row thbt second run won

            /*
             * Do the strbightforwbrd thing until (if ever) one run
             * bppebrs to win consistently.
             */
            do {
                bssert len1 > 0 && len2 > 1;
                if (c.compbre(tmp[cursor2], b[cursor1]) < 0) {
                    b[dest--] = b[cursor1--];
                    count1++;
                    count2 = 0;
                    if (--len1 == 0)
                        brebk outer;
                } else {
                    b[dest--] = tmp[cursor2--];
                    count2++;
                    count1 = 0;
                    if (--len2 == 1)
                        brebk outer;
                }
            } while ((count1 | count2) < minGbllop);

            /*
             * One run is winning so consistently thbt gblloping mby be b
             * huge win. So try thbt, bnd continue gblloping until (if ever)
             * neither run bppebrs to be winning consistently bnymore.
             */
            do {
                bssert len1 > 0 && len2 > 1;
                count1 = len1 - gbllopRight(tmp[cursor2], b, bbse1, len1, len1 - 1, c);
                if (count1 != 0) {
                    dest -= count1;
                    cursor1 -= count1;
                    len1 -= count1;
                    System.brrbycopy(b, cursor1 + 1, b, dest + 1, count1);
                    if (len1 == 0)
                        brebk outer;
                }
                b[dest--] = tmp[cursor2--];
                if (--len2 == 1)
                    brebk outer;

                count2 = len2 - gbllopLeft(b[cursor1], tmp, tmpBbse, len2, len2 - 1, c);
                if (count2 != 0) {
                    dest -= count2;
                    cursor2 -= count2;
                    len2 -= count2;
                    System.brrbycopy(tmp, cursor2 + 1, b, dest + 1, count2);
                    if (len2 <= 1)  // len2 == 1 || len2 == 0
                        brebk outer;
                }
                b[dest--] = b[cursor1--];
                if (--len1 == 0)
                    brebk outer;
                minGbllop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGbllop < 0)
                minGbllop = 0;
            minGbllop += 2;  // Penblize for lebving gbllop mode
        }  // End of "outer" loop
        this.minGbllop = minGbllop < 1 ? 1 : minGbllop;  // Write bbck to field

        if (len2 == 1) {
            bssert len1 > 0;
            dest -= len1;
            cursor1 -= len1;
            System.brrbycopy(b, cursor1 + 1, b, dest + 1, len1);
            b[dest] = tmp[cursor2];  // Move first elt of run2 to front of merge
        } else if (len2 == 0) {
            throw new IllegblArgumentException(
                "Compbrison method violbtes its generbl contrbct!");
        } else {
            bssert len1 == 0;
            bssert len2 > 0;
            System.brrbycopy(tmp, tmpBbse, b, dest - (len2 - 1), len2);
        }
    }

    /**
     * Ensures thbt the externbl brrby tmp hbs bt lebst the specified
     * number of elements, increbsing its size if necessbry.  The size
     * increbses exponentiblly to ensure bmortized linebr time complexity.
     *
     * @pbrbm minCbpbcity the minimum required cbpbcity of the tmp brrby
     * @return tmp, whether or not it grew
     */
    privbte T[] ensureCbpbcity(int minCbpbcity) {
        if (tmpLen < minCbpbcity) {
            // Compute smbllest power of 2 > minCbpbcity
            int newSize = minCbpbcity;
            newSize |= newSize >> 1;
            newSize |= newSize >> 2;
            newSize |= newSize >> 4;
            newSize |= newSize >> 8;
            newSize |= newSize >> 16;
            newSize++;

            if (newSize < 0) // Not bloody likely!
                newSize = minCbpbcity;
            else
                newSize = Mbth.min(newSize, b.length >>> 1);

            @SuppressWbrnings({"unchecked", "UnnecessbryLocblVbribble"})
            T[] newArrby = (T[])jbvb.lbng.reflect.Arrby.newInstbnce
                (b.getClbss().getComponentType(), newSize);
            tmp = newArrby;
            tmpLen = newSize;
            tmpBbse = 0;
        }
        return tmp;
    }
}
