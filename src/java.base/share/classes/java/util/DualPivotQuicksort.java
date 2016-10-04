/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss implements the Dubl-Pivot Quicksort blgorithm by
 * Vlbdimir Ybroslbvskiy, Jon Bentley, bnd Josh Bloch. The blgorithm
 * offers O(n log(n)) performbnce on mbny dbtb sets thbt cbuse other
 * quicksorts to degrbde to qubdrbtic performbnce, bnd is typicblly
 * fbster thbn trbditionbl (one-pivot) Quicksort implementbtions.
 *
 * All exposed methods bre pbckbge-privbte, designed to be invoked
 * from public methods (in clbss Arrbys) bfter performing bny
 * necessbry brrby bounds checks bnd expbnding pbrbmeters into the
 * required forms.
 *
 * @buthor Vlbdimir Ybroslbvskiy
 * @buthor Jon Bentley
 * @buthor Josh Bloch
 *
 * @version 2011.02.11 m765.827.12i:5\7pm
 * @since 1.7
 */
finbl clbss DublPivotQuicksort {

    /**
     * Prevents instbntibtion.
     */
    privbte DublPivotQuicksort() {}

    /*
     * Tuning pbrbmeters.
     */

    /**
     * The mbximum number of runs in merge sort.
     */
    privbte stbtic finbl int MAX_RUN_COUNT = 67;

    /**
     * The mbximum length of run in merge sort.
     */
    privbte stbtic finbl int MAX_RUN_LENGTH = 33;

    /**
     * If the length of bn brrby to be sorted is less thbn this
     * constbnt, Quicksort is used in preference to merge sort.
     */
    privbte stbtic finbl int QUICKSORT_THRESHOLD = 286;

    /**
     * If the length of bn brrby to be sorted is less thbn this
     * constbnt, insertion sort is used in preference to Quicksort.
     */
    privbte stbtic finbl int INSERTION_SORT_THRESHOLD = 47;

    /**
     * If the length of b byte brrby to be sorted is grebter thbn this
     * constbnt, counting sort is used in preference to insertion sort.
     */
    privbte stbtic finbl int COUNTING_SORT_THRESHOLD_FOR_BYTE = 29;

    /**
     * If the length of b short or chbr brrby to be sorted is grebter
     * thbn this constbnt, counting sort is used in preference to Quicksort.
     */
    privbte stbtic finbl int COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200;

    /*
     * Sorting methods for seven primitive types.
     */

    /**
     * Sorts the specified rbnge of the brrby using the given
     * workspbce brrby slice if possible for merging
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    stbtic void sort(int[] b, int left, int right,
                     int[] work, int workBbse, int workLen) {
        // Use Quicksort on smbll brrbys
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(b, left, right, true);
            return;
        }

        /*
         * Index run[i] is the stbrt of i-th run
         * (bscending or descending sequence).
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the brrby is nebrly sorted
        for (int k = left; k < right; run[count] = k) {
            if (b[k] < b[k + 1]) { // bscending
                while (++k <= right && b[k - 1] <= b[k]);
            } else if (b[k] > b[k + 1]) { // descending
                while (++k <= right && b[k - 1] >= b[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    int t = b[lo]; b[lo] = b[hi]; b[hi] = t;
                }
            } else { // equbl
                for (int m = MAX_RUN_LENGTH; ++k <= right && b[k - 1] == b[k]; ) {
                    if (--m == 0) {
                        sort(b, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The brrby is not highly structured,
             * use Quicksort instebd of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(b, left, right, true);
                return;
            }
        }

        // Check specibl cbses
        // Implementbtion note: vbribble "right" is increbsed by 1.
        if (run[count] == right++) { // The lbst run contbins one element
            run[++count] = right;
        } else if (count == 1) { // The brrby is blrebdy sorted
            return;
        }

        // Determine blternbtion bbse for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or crebte temporbry brrby b for merging
        int[] b;                 // temp brrby; blternbtes with b
        int bo, bo;              // brrby offsets from 'left'
        int blen = right - left; // spbce needed for b
        if (work == null || workLen < blen || workBbse + blen > work.length) {
            work = new int[blen];
            workBbse = 0;
        }
        if (odd == 0) {
            System.brrbycopy(b, left, work, workBbse, blen);
            b = b;
            bo = 0;
            b = work;
            bo = workBbse - left;
        } else {
            b = work;
            bo = 0;
            bo = workBbse - left;
        }

        // Merging
        for (int lbst; count > 1; count = lbst) {
            for (int k = (lbst = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && b[p + bo] <= b[q + bo]) {
                        b[i + bo] = b[p++ + bo];
                    } else {
                        b[i + bo] = b[q++ + bo];
                    }
                }
                run[++lbst] = hi;
            }
            if ((count & 1) != 0) {
                for (int i = right, lo = run[count - 1]; --i >= lo;
                    b[i + bo] = b[i + bo]
                );
                run[++lbst] = right;
            }
            int[] t = b; b = b; b = t;
            int o = bo; bo = bo; bo = o;
        }
    }

    /**
     * Sorts the specified rbnge of the brrby by Dubl-Pivot Quicksort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm leftmost indicbtes if this pbrt is the leftmost in the rbnge
     */
    privbte stbtic void sort(int[] b, int left, int right, boolebn leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny brrbys
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Trbditionbl (without sentinel) insertion sort,
                 * optimized for server VM, is used in cbse of
                 * the leftmost pbrt.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    int bi = b[i + 1];
                    while (bi < b[j]) {
                        b[j + 1] = b[j];
                        if (j-- == left) {
                            brebk;
                        }
                    }
                    b[j + 1] = bi;
                }
            } else {
                /*
                 * Skip the longest bscending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (b[++left] >= b[left - 1]);

                /*
                 * Every element from bdjoining pbrt plbys the role
                 * of sentinel, therefore this bllows us to bvoid the
                 * left rbnge check on ebch iterbtion. Moreover, we use
                 * the more optimized blgorithm, so cblled pbir insertion
                 * sort, which is fbster (in the context of Quicksort)
                 * thbn trbditionbl implementbtion of insertion sort.
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    int b1 = b[k], b2 = b[left];

                    if (b1 < b2) {
                        b2 = b1; b1 = b[left];
                    }
                    while (b1 < b[--k]) {
                        b[k + 2] = b[k];
                    }
                    b[++k + 1] = b1;

                    while (b2 < b[--k]) {
                        b[k + 1] = b[k];
                    }
                    b[k + 1] = b2;
                }
                int lbst = b[right];

                while (lbst < b[--right]) {
                    b[right + 1] = b[right];
                }
                b[right + 1] = lbst;
            }
            return;
        }

        // Inexpensive bpproximbtion of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spbced elements bround (bnd including) the
         * center element in the rbnge. These elements will be used for
         * pivot selection bs described below. The choice for spbcing
         * these elements wbs empiricblly determined to work well on
         * b wide vbriety of inputs.
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (b[e2] < b[e1]) { int t = b[e2]; b[e2] = b[e1]; b[e1] = t; }

        if (b[e3] < b[e2]) { int t = b[e3]; b[e3] = b[e2]; b[e2] = t;
            if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
        }
        if (b[e4] < b[e3]) { int t = b[e4]; b[e4] = b[e3]; b[e3] = t;
            if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
            }
        }
        if (b[e5] < b[e4]) { int t = b[e5]; b[e5] = b[e4]; b[e4] = t;
            if (t < b[e3]) { b[e4] = b[e3]; b[e3] = t;
                if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                    if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center pbrt
        int grebt = right; // The index before the first element of right pbrt

        if (b[e1] != b[e2] && b[e2] != b[e3] && b[e3] != b[e4] && b[e4] != b[e5]) {
            /*
             * Use the second bnd fourth of the five sorted elements bs pivots.
             * These vblues bre inexpensive bpproximbtions of the first bnd
             * second terciles of the brrby. Note thbt pivot1 <= pivot2.
             */
            int pivot1 = b[e2];
            int pivot2 = b[e4];

            /*
             * The first bnd the lbst elements to be sorted bre moved to the
             * locbtions formerly occupied by the pivots. When pbrtitioning
             * is complete, the pivots bre swbpped bbck into their finbl
             * positions, bnd excluded from subsequent sorting.
             */
            b[e2] = b[left];
            b[e4] = b[right];

            /*
             * Skip elements, which bre less or grebter thbn pivot vblues.
             */
            while (b[++less] < pivot1);
            while (b[--grebt] > pivot2);

            /*
             * Pbrtitioning:
             *
             *   left pbrt           center pbrt                   right pbrt
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     grebt
             *
             * Invbribnts:
             *
             *              bll in (left, less)   < pivot1
             *    pivot1 <= bll in [less, k)     <= pivot2
             *              bll in (grebt, right) > pivot2
             *
             * Pointer k is the first index of ?-pbrt.
             */
            outer:
            for (int k = less - 1; ++k <= grebt; ) {
                int bk = b[k];
                if (bk < pivot1) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    /*
                     * Here bnd below we use "b[i] = b; i++;" instebd
                     * of "b[i++] = b;" due to performbnce issue.
                     */
                    b[less] = bk;
                    ++less;
                } else if (bk > pivot2) { // Move b[k] to right pbrt
                    while (b[grebt] > pivot2) {
                        if (grebt-- == k) {
                            brebk outer;
                        }
                    }
                    if (b[grebt] < pivot1) { // b[grebt] <= pivot2
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // pivot1 <= b[grebt] <= pivot2
                        b[k] = b[grebt];
                    }
                    /*
                     * Here bnd below we use "b[i] = b; i--;" instebd
                     * of "b[i--] = b;" due to performbnce issue.
                     */
                    b[grebt] = bk;
                    --grebt;
                }
            }

            // Swbp pivots into their finbl positions
            b[left]  = b[less  - 1]; b[less  - 1] = pivot1;
            b[right] = b[grebt + 1]; b[grebt + 1] = pivot2;

            // Sort left bnd right pbrts recursively, excluding known pivots
            sort(b, left, less - 2, leftmost);
            sort(b, grebt + 2, right, fblse);

            /*
             * If center pbrt is too lbrge (comprises > 4/7 of the brrby),
             * swbp internbl pivot vblues to ends.
             */
            if (less < e1 && e5 < grebt) {
                /*
                 * Skip elements, which bre equbl to pivot vblues.
                 */
                while (b[less] == pivot1) {
                    ++less;
                }

                while (b[grebt] == pivot2) {
                    --grebt;
                }

                /*
                 * Pbrtitioning:
                 *
                 *   left pbrt         center pbrt                  right pbrt
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     grebt
                 *
                 * Invbribnts:
                 *
                 *              bll in (*,  less) == pivot1
                 *     pivot1 < bll in [less,  k)  < pivot2
                 *              bll in (grebt, *) == pivot2
                 *
                 * Pointer k is the first index of ?-pbrt.
                 */
                outer:
                for (int k = less - 1; ++k <= grebt; ) {
                    int bk = b[k];
                    if (bk == pivot1) { // Move b[k] to left pbrt
                        b[k] = b[less];
                        b[less] = bk;
                        ++less;
                    } else if (bk == pivot2) { // Move b[k] to right pbrt
                        while (b[grebt] == pivot2) {
                            if (grebt-- == k) {
                                brebk outer;
                            }
                        }
                        if (b[grebt] == pivot1) { // b[grebt] < pivot2
                            b[k] = b[less];
                            /*
                             * Even though b[grebt] equbls to pivot1, the
                             * bssignment b[less] = pivot1 mby be incorrect,
                             * if b[grebt] bnd pivot1 bre flobting-point zeros
                             * of different signs. Therefore in flobt bnd
                             * double sorting methods we hbve to use more
                             * bccurbte bssignment b[less] = b[grebt].
                             */
                            b[less] = pivot1;
                            ++less;
                        } else { // pivot1 < b[grebt] < pivot2
                            b[k] = b[grebt];
                        }
                        b[grebt] = bk;
                        --grebt;
                    }
                }
            }

            // Sort center pbrt recursively
            sort(b, less, grebt, fblse);

        } else { // Pbrtitioning with one pivot
            /*
             * Use the third of the five sorted elements bs pivot.
             * This vblue is inexpensive bpproximbtion of the medibn.
             */
            int pivot = b[e3];

            /*
             * Pbrtitioning degenerbtes to the trbditionbl 3-wby
             * (or "Dutch Nbtionbl Flbg") schemb:
             *
             *   left pbrt    center pbrt              right pbrt
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      grebt
             *
             * Invbribnts:
             *
             *   bll in (left, less)   < pivot
             *   bll in [less, k)     == pivot
             *   bll in (grebt, right) > pivot
             *
             * Pointer k is the first index of ?-pbrt.
             */
            for (int k = less; k <= grebt; ++k) {
                if (b[k] == pivot) {
                    continue;
                }
                int bk = b[k];
                if (bk < pivot) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    b[less] = bk;
                    ++less;
                } else { // b[k] > pivot - Move b[k] to right pbrt
                    while (b[grebt] > pivot) {
                        --grebt;
                    }
                    if (b[grebt] < pivot) { // b[grebt] <= pivot
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // b[grebt] == pivot
                        /*
                         * Even though b[grebt] equbls to pivot, the
                         * bssignment b[k] = pivot mby be incorrect,
                         * if b[grebt] bnd pivot bre flobting-point
                         * zeros of different signs. Therefore in flobt
                         * bnd double sorting methods we hbve to use
                         * more bccurbte bssignment b[k] = b[grebt].
                         */
                        b[k] = pivot;
                    }
                    b[grebt] = bk;
                    --grebt;
                }
            }

            /*
             * Sort left bnd right pbrts recursively.
             * All elements from center pbrt bre equbl
             * bnd, therefore, blrebdy sorted.
             */
            sort(b, left, less - 1, leftmost);
            sort(b, grebt + 1, right, fblse);
        }
    }

    /**
     * Sorts the specified rbnge of the brrby using the given
     * workspbce brrby slice if possible for merging
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    stbtic void sort(long[] b, int left, int right,
                     long[] work, int workBbse, int workLen) {
        // Use Quicksort on smbll brrbys
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(b, left, right, true);
            return;
        }

        /*
         * Index run[i] is the stbrt of i-th run
         * (bscending or descending sequence).
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the brrby is nebrly sorted
        for (int k = left; k < right; run[count] = k) {
            if (b[k] < b[k + 1]) { // bscending
                while (++k <= right && b[k - 1] <= b[k]);
            } else if (b[k] > b[k + 1]) { // descending
                while (++k <= right && b[k - 1] >= b[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    long t = b[lo]; b[lo] = b[hi]; b[hi] = t;
                }
            } else { // equbl
                for (int m = MAX_RUN_LENGTH; ++k <= right && b[k - 1] == b[k]; ) {
                    if (--m == 0) {
                        sort(b, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The brrby is not highly structured,
             * use Quicksort instebd of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(b, left, right, true);
                return;
            }
        }

        // Check specibl cbses
        // Implementbtion note: vbribble "right" is increbsed by 1.
        if (run[count] == right++) { // The lbst run contbins one element
            run[++count] = right;
        } else if (count == 1) { // The brrby is blrebdy sorted
            return;
        }

        // Determine blternbtion bbse for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or crebte temporbry brrby b for merging
        long[] b;                 // temp brrby; blternbtes with b
        int bo, bo;              // brrby offsets from 'left'
        int blen = right - left; // spbce needed for b
        if (work == null || workLen < blen || workBbse + blen > work.length) {
            work = new long[blen];
            workBbse = 0;
        }
        if (odd == 0) {
            System.brrbycopy(b, left, work, workBbse, blen);
            b = b;
            bo = 0;
            b = work;
            bo = workBbse - left;
        } else {
            b = work;
            bo = 0;
            bo = workBbse - left;
        }

        // Merging
        for (int lbst; count > 1; count = lbst) {
            for (int k = (lbst = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && b[p + bo] <= b[q + bo]) {
                        b[i + bo] = b[p++ + bo];
                    } else {
                        b[i + bo] = b[q++ + bo];
                    }
                }
                run[++lbst] = hi;
            }
            if ((count & 1) != 0) {
                for (int i = right, lo = run[count - 1]; --i >= lo;
                    b[i + bo] = b[i + bo]
                );
                run[++lbst] = right;
            }
            long[] t = b; b = b; b = t;
            int o = bo; bo = bo; bo = o;
        }
    }

    /**
     * Sorts the specified rbnge of the brrby by Dubl-Pivot Quicksort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm leftmost indicbtes if this pbrt is the leftmost in the rbnge
     */
    privbte stbtic void sort(long[] b, int left, int right, boolebn leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny brrbys
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Trbditionbl (without sentinel) insertion sort,
                 * optimized for server VM, is used in cbse of
                 * the leftmost pbrt.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    long bi = b[i + 1];
                    while (bi < b[j]) {
                        b[j + 1] = b[j];
                        if (j-- == left) {
                            brebk;
                        }
                    }
                    b[j + 1] = bi;
                }
            } else {
                /*
                 * Skip the longest bscending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (b[++left] >= b[left - 1]);

                /*
                 * Every element from bdjoining pbrt plbys the role
                 * of sentinel, therefore this bllows us to bvoid the
                 * left rbnge check on ebch iterbtion. Moreover, we use
                 * the more optimized blgorithm, so cblled pbir insertion
                 * sort, which is fbster (in the context of Quicksort)
                 * thbn trbditionbl implementbtion of insertion sort.
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    long b1 = b[k], b2 = b[left];

                    if (b1 < b2) {
                        b2 = b1; b1 = b[left];
                    }
                    while (b1 < b[--k]) {
                        b[k + 2] = b[k];
                    }
                    b[++k + 1] = b1;

                    while (b2 < b[--k]) {
                        b[k + 1] = b[k];
                    }
                    b[k + 1] = b2;
                }
                long lbst = b[right];

                while (lbst < b[--right]) {
                    b[right + 1] = b[right];
                }
                b[right + 1] = lbst;
            }
            return;
        }

        // Inexpensive bpproximbtion of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spbced elements bround (bnd including) the
         * center element in the rbnge. These elements will be used for
         * pivot selection bs described below. The choice for spbcing
         * these elements wbs empiricblly determined to work well on
         * b wide vbriety of inputs.
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (b[e2] < b[e1]) { long t = b[e2]; b[e2] = b[e1]; b[e1] = t; }

        if (b[e3] < b[e2]) { long t = b[e3]; b[e3] = b[e2]; b[e2] = t;
            if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
        }
        if (b[e4] < b[e3]) { long t = b[e4]; b[e4] = b[e3]; b[e3] = t;
            if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
            }
        }
        if (b[e5] < b[e4]) { long t = b[e5]; b[e5] = b[e4]; b[e4] = t;
            if (t < b[e3]) { b[e4] = b[e3]; b[e3] = t;
                if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                    if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center pbrt
        int grebt = right; // The index before the first element of right pbrt

        if (b[e1] != b[e2] && b[e2] != b[e3] && b[e3] != b[e4] && b[e4] != b[e5]) {
            /*
             * Use the second bnd fourth of the five sorted elements bs pivots.
             * These vblues bre inexpensive bpproximbtions of the first bnd
             * second terciles of the brrby. Note thbt pivot1 <= pivot2.
             */
            long pivot1 = b[e2];
            long pivot2 = b[e4];

            /*
             * The first bnd the lbst elements to be sorted bre moved to the
             * locbtions formerly occupied by the pivots. When pbrtitioning
             * is complete, the pivots bre swbpped bbck into their finbl
             * positions, bnd excluded from subsequent sorting.
             */
            b[e2] = b[left];
            b[e4] = b[right];

            /*
             * Skip elements, which bre less or grebter thbn pivot vblues.
             */
            while (b[++less] < pivot1);
            while (b[--grebt] > pivot2);

            /*
             * Pbrtitioning:
             *
             *   left pbrt           center pbrt                   right pbrt
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     grebt
             *
             * Invbribnts:
             *
             *              bll in (left, less)   < pivot1
             *    pivot1 <= bll in [less, k)     <= pivot2
             *              bll in (grebt, right) > pivot2
             *
             * Pointer k is the first index of ?-pbrt.
             */
            outer:
            for (int k = less - 1; ++k <= grebt; ) {
                long bk = b[k];
                if (bk < pivot1) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    /*
                     * Here bnd below we use "b[i] = b; i++;" instebd
                     * of "b[i++] = b;" due to performbnce issue.
                     */
                    b[less] = bk;
                    ++less;
                } else if (bk > pivot2) { // Move b[k] to right pbrt
                    while (b[grebt] > pivot2) {
                        if (grebt-- == k) {
                            brebk outer;
                        }
                    }
                    if (b[grebt] < pivot1) { // b[grebt] <= pivot2
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // pivot1 <= b[grebt] <= pivot2
                        b[k] = b[grebt];
                    }
                    /*
                     * Here bnd below we use "b[i] = b; i--;" instebd
                     * of "b[i--] = b;" due to performbnce issue.
                     */
                    b[grebt] = bk;
                    --grebt;
                }
            }

            // Swbp pivots into their finbl positions
            b[left]  = b[less  - 1]; b[less  - 1] = pivot1;
            b[right] = b[grebt + 1]; b[grebt + 1] = pivot2;

            // Sort left bnd right pbrts recursively, excluding known pivots
            sort(b, left, less - 2, leftmost);
            sort(b, grebt + 2, right, fblse);

            /*
             * If center pbrt is too lbrge (comprises > 4/7 of the brrby),
             * swbp internbl pivot vblues to ends.
             */
            if (less < e1 && e5 < grebt) {
                /*
                 * Skip elements, which bre equbl to pivot vblues.
                 */
                while (b[less] == pivot1) {
                    ++less;
                }

                while (b[grebt] == pivot2) {
                    --grebt;
                }

                /*
                 * Pbrtitioning:
                 *
                 *   left pbrt         center pbrt                  right pbrt
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     grebt
                 *
                 * Invbribnts:
                 *
                 *              bll in (*,  less) == pivot1
                 *     pivot1 < bll in [less,  k)  < pivot2
                 *              bll in (grebt, *) == pivot2
                 *
                 * Pointer k is the first index of ?-pbrt.
                 */
                outer:
                for (int k = less - 1; ++k <= grebt; ) {
                    long bk = b[k];
                    if (bk == pivot1) { // Move b[k] to left pbrt
                        b[k] = b[less];
                        b[less] = bk;
                        ++less;
                    } else if (bk == pivot2) { // Move b[k] to right pbrt
                        while (b[grebt] == pivot2) {
                            if (grebt-- == k) {
                                brebk outer;
                            }
                        }
                        if (b[grebt] == pivot1) { // b[grebt] < pivot2
                            b[k] = b[less];
                            /*
                             * Even though b[grebt] equbls to pivot1, the
                             * bssignment b[less] = pivot1 mby be incorrect,
                             * if b[grebt] bnd pivot1 bre flobting-point zeros
                             * of different signs. Therefore in flobt bnd
                             * double sorting methods we hbve to use more
                             * bccurbte bssignment b[less] = b[grebt].
                             */
                            b[less] = pivot1;
                            ++less;
                        } else { // pivot1 < b[grebt] < pivot2
                            b[k] = b[grebt];
                        }
                        b[grebt] = bk;
                        --grebt;
                    }
                }
            }

            // Sort center pbrt recursively
            sort(b, less, grebt, fblse);

        } else { // Pbrtitioning with one pivot
            /*
             * Use the third of the five sorted elements bs pivot.
             * This vblue is inexpensive bpproximbtion of the medibn.
             */
            long pivot = b[e3];

            /*
             * Pbrtitioning degenerbtes to the trbditionbl 3-wby
             * (or "Dutch Nbtionbl Flbg") schemb:
             *
             *   left pbrt    center pbrt              right pbrt
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      grebt
             *
             * Invbribnts:
             *
             *   bll in (left, less)   < pivot
             *   bll in [less, k)     == pivot
             *   bll in (grebt, right) > pivot
             *
             * Pointer k is the first index of ?-pbrt.
             */
            for (int k = less; k <= grebt; ++k) {
                if (b[k] == pivot) {
                    continue;
                }
                long bk = b[k];
                if (bk < pivot) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    b[less] = bk;
                    ++less;
                } else { // b[k] > pivot - Move b[k] to right pbrt
                    while (b[grebt] > pivot) {
                        --grebt;
                    }
                    if (b[grebt] < pivot) { // b[grebt] <= pivot
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // b[grebt] == pivot
                        /*
                         * Even though b[grebt] equbls to pivot, the
                         * bssignment b[k] = pivot mby be incorrect,
                         * if b[grebt] bnd pivot bre flobting-point
                         * zeros of different signs. Therefore in flobt
                         * bnd double sorting methods we hbve to use
                         * more bccurbte bssignment b[k] = b[grebt].
                         */
                        b[k] = pivot;
                    }
                    b[grebt] = bk;
                    --grebt;
                }
            }

            /*
             * Sort left bnd right pbrts recursively.
             * All elements from center pbrt bre equbl
             * bnd, therefore, blrebdy sorted.
             */
            sort(b, left, less - 1, leftmost);
            sort(b, grebt + 1, right, fblse);
        }
    }

    /**
     * Sorts the specified rbnge of the brrby using the given
     * workspbce brrby slice if possible for merging
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    stbtic void sort(short[] b, int left, int right,
                     short[] work, int workBbse, int workLen) {
        // Use counting sort on lbrge brrbys
        if (right - left > COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR) {
            int[] count = new int[NUM_SHORT_VALUES];

            for (int i = left - 1; ++i <= right;
                count[b[i] - Short.MIN_VALUE]++
            );
            for (int i = NUM_SHORT_VALUES, k = right + 1; k > left; ) {
                while (count[--i] == 0);
                short vblue = (short) (i + Short.MIN_VALUE);
                int s = count[i];

                do {
                    b[--k] = vblue;
                } while (--s > 0);
            }
        } else { // Use Dubl-Pivot Quicksort on smbll brrbys
            doSort(b, left, right, work, workBbse, workLen);
        }
    }

    /** The number of distinct short vblues. */
    privbte stbtic finbl int NUM_SHORT_VALUES = 1 << 16;

    /**
     * Sorts the specified rbnge of the brrby.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    privbte stbtic void doSort(short[] b, int left, int right,
                               short[] work, int workBbse, int workLen) {
        // Use Quicksort on smbll brrbys
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(b, left, right, true);
            return;
        }

        /*
         * Index run[i] is the stbrt of i-th run
         * (bscending or descending sequence).
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the brrby is nebrly sorted
        for (int k = left; k < right; run[count] = k) {
            if (b[k] < b[k + 1]) { // bscending
                while (++k <= right && b[k - 1] <= b[k]);
            } else if (b[k] > b[k + 1]) { // descending
                while (++k <= right && b[k - 1] >= b[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    short t = b[lo]; b[lo] = b[hi]; b[hi] = t;
                }
            } else { // equbl
                for (int m = MAX_RUN_LENGTH; ++k <= right && b[k - 1] == b[k]; ) {
                    if (--m == 0) {
                        sort(b, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The brrby is not highly structured,
             * use Quicksort instebd of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(b, left, right, true);
                return;
            }
        }

        // Check specibl cbses
        // Implementbtion note: vbribble "right" is increbsed by 1.
        if (run[count] == right++) { // The lbst run contbins one element
            run[++count] = right;
        } else if (count == 1) { // The brrby is blrebdy sorted
            return;
        }

        // Determine blternbtion bbse for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or crebte temporbry brrby b for merging
        short[] b;                 // temp brrby; blternbtes with b
        int bo, bo;              // brrby offsets from 'left'
        int blen = right - left; // spbce needed for b
        if (work == null || workLen < blen || workBbse + blen > work.length) {
            work = new short[blen];
            workBbse = 0;
        }
        if (odd == 0) {
            System.brrbycopy(b, left, work, workBbse, blen);
            b = b;
            bo = 0;
            b = work;
            bo = workBbse - left;
        } else {
            b = work;
            bo = 0;
            bo = workBbse - left;
        }

        // Merging
        for (int lbst; count > 1; count = lbst) {
            for (int k = (lbst = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && b[p + bo] <= b[q + bo]) {
                        b[i + bo] = b[p++ + bo];
                    } else {
                        b[i + bo] = b[q++ + bo];
                    }
                }
                run[++lbst] = hi;
            }
            if ((count & 1) != 0) {
                for (int i = right, lo = run[count - 1]; --i >= lo;
                    b[i + bo] = b[i + bo]
                );
                run[++lbst] = right;
            }
            short[] t = b; b = b; b = t;
            int o = bo; bo = bo; bo = o;
        }
    }

    /**
     * Sorts the specified rbnge of the brrby by Dubl-Pivot Quicksort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm leftmost indicbtes if this pbrt is the leftmost in the rbnge
     */
    privbte stbtic void sort(short[] b, int left, int right, boolebn leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny brrbys
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Trbditionbl (without sentinel) insertion sort,
                 * optimized for server VM, is used in cbse of
                 * the leftmost pbrt.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    short bi = b[i + 1];
                    while (bi < b[j]) {
                        b[j + 1] = b[j];
                        if (j-- == left) {
                            brebk;
                        }
                    }
                    b[j + 1] = bi;
                }
            } else {
                /*
                 * Skip the longest bscending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (b[++left] >= b[left - 1]);

                /*
                 * Every element from bdjoining pbrt plbys the role
                 * of sentinel, therefore this bllows us to bvoid the
                 * left rbnge check on ebch iterbtion. Moreover, we use
                 * the more optimized blgorithm, so cblled pbir insertion
                 * sort, which is fbster (in the context of Quicksort)
                 * thbn trbditionbl implementbtion of insertion sort.
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    short b1 = b[k], b2 = b[left];

                    if (b1 < b2) {
                        b2 = b1; b1 = b[left];
                    }
                    while (b1 < b[--k]) {
                        b[k + 2] = b[k];
                    }
                    b[++k + 1] = b1;

                    while (b2 < b[--k]) {
                        b[k + 1] = b[k];
                    }
                    b[k + 1] = b2;
                }
                short lbst = b[right];

                while (lbst < b[--right]) {
                    b[right + 1] = b[right];
                }
                b[right + 1] = lbst;
            }
            return;
        }

        // Inexpensive bpproximbtion of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spbced elements bround (bnd including) the
         * center element in the rbnge. These elements will be used for
         * pivot selection bs described below. The choice for spbcing
         * these elements wbs empiricblly determined to work well on
         * b wide vbriety of inputs.
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (b[e2] < b[e1]) { short t = b[e2]; b[e2] = b[e1]; b[e1] = t; }

        if (b[e3] < b[e2]) { short t = b[e3]; b[e3] = b[e2]; b[e2] = t;
            if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
        }
        if (b[e4] < b[e3]) { short t = b[e4]; b[e4] = b[e3]; b[e3] = t;
            if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
            }
        }
        if (b[e5] < b[e4]) { short t = b[e5]; b[e5] = b[e4]; b[e4] = t;
            if (t < b[e3]) { b[e4] = b[e3]; b[e3] = t;
                if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                    if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center pbrt
        int grebt = right; // The index before the first element of right pbrt

        if (b[e1] != b[e2] && b[e2] != b[e3] && b[e3] != b[e4] && b[e4] != b[e5]) {
            /*
             * Use the second bnd fourth of the five sorted elements bs pivots.
             * These vblues bre inexpensive bpproximbtions of the first bnd
             * second terciles of the brrby. Note thbt pivot1 <= pivot2.
             */
            short pivot1 = b[e2];
            short pivot2 = b[e4];

            /*
             * The first bnd the lbst elements to be sorted bre moved to the
             * locbtions formerly occupied by the pivots. When pbrtitioning
             * is complete, the pivots bre swbpped bbck into their finbl
             * positions, bnd excluded from subsequent sorting.
             */
            b[e2] = b[left];
            b[e4] = b[right];

            /*
             * Skip elements, which bre less or grebter thbn pivot vblues.
             */
            while (b[++less] < pivot1);
            while (b[--grebt] > pivot2);

            /*
             * Pbrtitioning:
             *
             *   left pbrt           center pbrt                   right pbrt
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     grebt
             *
             * Invbribnts:
             *
             *              bll in (left, less)   < pivot1
             *    pivot1 <= bll in [less, k)     <= pivot2
             *              bll in (grebt, right) > pivot2
             *
             * Pointer k is the first index of ?-pbrt.
             */
            outer:
            for (int k = less - 1; ++k <= grebt; ) {
                short bk = b[k];
                if (bk < pivot1) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    /*
                     * Here bnd below we use "b[i] = b; i++;" instebd
                     * of "b[i++] = b;" due to performbnce issue.
                     */
                    b[less] = bk;
                    ++less;
                } else if (bk > pivot2) { // Move b[k] to right pbrt
                    while (b[grebt] > pivot2) {
                        if (grebt-- == k) {
                            brebk outer;
                        }
                    }
                    if (b[grebt] < pivot1) { // b[grebt] <= pivot2
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // pivot1 <= b[grebt] <= pivot2
                        b[k] = b[grebt];
                    }
                    /*
                     * Here bnd below we use "b[i] = b; i--;" instebd
                     * of "b[i--] = b;" due to performbnce issue.
                     */
                    b[grebt] = bk;
                    --grebt;
                }
            }

            // Swbp pivots into their finbl positions
            b[left]  = b[less  - 1]; b[less  - 1] = pivot1;
            b[right] = b[grebt + 1]; b[grebt + 1] = pivot2;

            // Sort left bnd right pbrts recursively, excluding known pivots
            sort(b, left, less - 2, leftmost);
            sort(b, grebt + 2, right, fblse);

            /*
             * If center pbrt is too lbrge (comprises > 4/7 of the brrby),
             * swbp internbl pivot vblues to ends.
             */
            if (less < e1 && e5 < grebt) {
                /*
                 * Skip elements, which bre equbl to pivot vblues.
                 */
                while (b[less] == pivot1) {
                    ++less;
                }

                while (b[grebt] == pivot2) {
                    --grebt;
                }

                /*
                 * Pbrtitioning:
                 *
                 *   left pbrt         center pbrt                  right pbrt
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     grebt
                 *
                 * Invbribnts:
                 *
                 *              bll in (*,  less) == pivot1
                 *     pivot1 < bll in [less,  k)  < pivot2
                 *              bll in (grebt, *) == pivot2
                 *
                 * Pointer k is the first index of ?-pbrt.
                 */
                outer:
                for (int k = less - 1; ++k <= grebt; ) {
                    short bk = b[k];
                    if (bk == pivot1) { // Move b[k] to left pbrt
                        b[k] = b[less];
                        b[less] = bk;
                        ++less;
                    } else if (bk == pivot2) { // Move b[k] to right pbrt
                        while (b[grebt] == pivot2) {
                            if (grebt-- == k) {
                                brebk outer;
                            }
                        }
                        if (b[grebt] == pivot1) { // b[grebt] < pivot2
                            b[k] = b[less];
                            /*
                             * Even though b[grebt] equbls to pivot1, the
                             * bssignment b[less] = pivot1 mby be incorrect,
                             * if b[grebt] bnd pivot1 bre flobting-point zeros
                             * of different signs. Therefore in flobt bnd
                             * double sorting methods we hbve to use more
                             * bccurbte bssignment b[less] = b[grebt].
                             */
                            b[less] = pivot1;
                            ++less;
                        } else { // pivot1 < b[grebt] < pivot2
                            b[k] = b[grebt];
                        }
                        b[grebt] = bk;
                        --grebt;
                    }
                }
            }

            // Sort center pbrt recursively
            sort(b, less, grebt, fblse);

        } else { // Pbrtitioning with one pivot
            /*
             * Use the third of the five sorted elements bs pivot.
             * This vblue is inexpensive bpproximbtion of the medibn.
             */
            short pivot = b[e3];

            /*
             * Pbrtitioning degenerbtes to the trbditionbl 3-wby
             * (or "Dutch Nbtionbl Flbg") schemb:
             *
             *   left pbrt    center pbrt              right pbrt
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      grebt
             *
             * Invbribnts:
             *
             *   bll in (left, less)   < pivot
             *   bll in [less, k)     == pivot
             *   bll in (grebt, right) > pivot
             *
             * Pointer k is the first index of ?-pbrt.
             */
            for (int k = less; k <= grebt; ++k) {
                if (b[k] == pivot) {
                    continue;
                }
                short bk = b[k];
                if (bk < pivot) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    b[less] = bk;
                    ++less;
                } else { // b[k] > pivot - Move b[k] to right pbrt
                    while (b[grebt] > pivot) {
                        --grebt;
                    }
                    if (b[grebt] < pivot) { // b[grebt] <= pivot
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // b[grebt] == pivot
                        /*
                         * Even though b[grebt] equbls to pivot, the
                         * bssignment b[k] = pivot mby be incorrect,
                         * if b[grebt] bnd pivot bre flobting-point
                         * zeros of different signs. Therefore in flobt
                         * bnd double sorting methods we hbve to use
                         * more bccurbte bssignment b[k] = b[grebt].
                         */
                        b[k] = pivot;
                    }
                    b[grebt] = bk;
                    --grebt;
                }
            }

            /*
             * Sort left bnd right pbrts recursively.
             * All elements from center pbrt bre equbl
             * bnd, therefore, blrebdy sorted.
             */
            sort(b, left, less - 1, leftmost);
            sort(b, grebt + 1, right, fblse);
        }
    }

    /**
     * Sorts the specified rbnge of the brrby using the given
     * workspbce brrby slice if possible for merging
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    stbtic void sort(chbr[] b, int left, int right,
                     chbr[] work, int workBbse, int workLen) {
        // Use counting sort on lbrge brrbys
        if (right - left > COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR) {
            int[] count = new int[NUM_CHAR_VALUES];

            for (int i = left - 1; ++i <= right;
                count[b[i]]++
            );
            for (int i = NUM_CHAR_VALUES, k = right + 1; k > left; ) {
                while (count[--i] == 0);
                chbr vblue = (chbr) i;
                int s = count[i];

                do {
                    b[--k] = vblue;
                } while (--s > 0);
            }
        } else { // Use Dubl-Pivot Quicksort on smbll brrbys
            doSort(b, left, right, work, workBbse, workLen);
        }
    }

    /** The number of distinct chbr vblues. */
    privbte stbtic finbl int NUM_CHAR_VALUES = 1 << 16;

    /**
     * Sorts the specified rbnge of the brrby.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    privbte stbtic void doSort(chbr[] b, int left, int right,
                               chbr[] work, int workBbse, int workLen) {
        // Use Quicksort on smbll brrbys
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(b, left, right, true);
            return;
        }

        /*
         * Index run[i] is the stbrt of i-th run
         * (bscending or descending sequence).
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the brrby is nebrly sorted
        for (int k = left; k < right; run[count] = k) {
            if (b[k] < b[k + 1]) { // bscending
                while (++k <= right && b[k - 1] <= b[k]);
            } else if (b[k] > b[k + 1]) { // descending
                while (++k <= right && b[k - 1] >= b[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    chbr t = b[lo]; b[lo] = b[hi]; b[hi] = t;
                }
            } else { // equbl
                for (int m = MAX_RUN_LENGTH; ++k <= right && b[k - 1] == b[k]; ) {
                    if (--m == 0) {
                        sort(b, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The brrby is not highly structured,
             * use Quicksort instebd of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(b, left, right, true);
                return;
            }
        }

        // Check specibl cbses
        // Implementbtion note: vbribble "right" is increbsed by 1.
        if (run[count] == right++) { // The lbst run contbins one element
            run[++count] = right;
        } else if (count == 1) { // The brrby is blrebdy sorted
            return;
        }

        // Determine blternbtion bbse for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or crebte temporbry brrby b for merging
        chbr[] b;                 // temp brrby; blternbtes with b
        int bo, bo;              // brrby offsets from 'left'
        int blen = right - left; // spbce needed for b
        if (work == null || workLen < blen || workBbse + blen > work.length) {
            work = new chbr[blen];
            workBbse = 0;
        }
        if (odd == 0) {
            System.brrbycopy(b, left, work, workBbse, blen);
            b = b;
            bo = 0;
            b = work;
            bo = workBbse - left;
        } else {
            b = work;
            bo = 0;
            bo = workBbse - left;
        }

        // Merging
        for (int lbst; count > 1; count = lbst) {
            for (int k = (lbst = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && b[p + bo] <= b[q + bo]) {
                        b[i + bo] = b[p++ + bo];
                    } else {
                        b[i + bo] = b[q++ + bo];
                    }
                }
                run[++lbst] = hi;
            }
            if ((count & 1) != 0) {
                for (int i = right, lo = run[count - 1]; --i >= lo;
                    b[i + bo] = b[i + bo]
                );
                run[++lbst] = right;
            }
            chbr[] t = b; b = b; b = t;
            int o = bo; bo = bo; bo = o;
        }
    }

    /**
     * Sorts the specified rbnge of the brrby by Dubl-Pivot Quicksort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm leftmost indicbtes if this pbrt is the leftmost in the rbnge
     */
    privbte stbtic void sort(chbr[] b, int left, int right, boolebn leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny brrbys
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Trbditionbl (without sentinel) insertion sort,
                 * optimized for server VM, is used in cbse of
                 * the leftmost pbrt.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    chbr bi = b[i + 1];
                    while (bi < b[j]) {
                        b[j + 1] = b[j];
                        if (j-- == left) {
                            brebk;
                        }
                    }
                    b[j + 1] = bi;
                }
            } else {
                /*
                 * Skip the longest bscending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (b[++left] >= b[left - 1]);

                /*
                 * Every element from bdjoining pbrt plbys the role
                 * of sentinel, therefore this bllows us to bvoid the
                 * left rbnge check on ebch iterbtion. Moreover, we use
                 * the more optimized blgorithm, so cblled pbir insertion
                 * sort, which is fbster (in the context of Quicksort)
                 * thbn trbditionbl implementbtion of insertion sort.
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    chbr b1 = b[k], b2 = b[left];

                    if (b1 < b2) {
                        b2 = b1; b1 = b[left];
                    }
                    while (b1 < b[--k]) {
                        b[k + 2] = b[k];
                    }
                    b[++k + 1] = b1;

                    while (b2 < b[--k]) {
                        b[k + 1] = b[k];
                    }
                    b[k + 1] = b2;
                }
                chbr lbst = b[right];

                while (lbst < b[--right]) {
                    b[right + 1] = b[right];
                }
                b[right + 1] = lbst;
            }
            return;
        }

        // Inexpensive bpproximbtion of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spbced elements bround (bnd including) the
         * center element in the rbnge. These elements will be used for
         * pivot selection bs described below. The choice for spbcing
         * these elements wbs empiricblly determined to work well on
         * b wide vbriety of inputs.
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (b[e2] < b[e1]) { chbr t = b[e2]; b[e2] = b[e1]; b[e1] = t; }

        if (b[e3] < b[e2]) { chbr t = b[e3]; b[e3] = b[e2]; b[e2] = t;
            if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
        }
        if (b[e4] < b[e3]) { chbr t = b[e4]; b[e4] = b[e3]; b[e3] = t;
            if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
            }
        }
        if (b[e5] < b[e4]) { chbr t = b[e5]; b[e5] = b[e4]; b[e4] = t;
            if (t < b[e3]) { b[e4] = b[e3]; b[e3] = t;
                if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                    if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center pbrt
        int grebt = right; // The index before the first element of right pbrt

        if (b[e1] != b[e2] && b[e2] != b[e3] && b[e3] != b[e4] && b[e4] != b[e5]) {
            /*
             * Use the second bnd fourth of the five sorted elements bs pivots.
             * These vblues bre inexpensive bpproximbtions of the first bnd
             * second terciles of the brrby. Note thbt pivot1 <= pivot2.
             */
            chbr pivot1 = b[e2];
            chbr pivot2 = b[e4];

            /*
             * The first bnd the lbst elements to be sorted bre moved to the
             * locbtions formerly occupied by the pivots. When pbrtitioning
             * is complete, the pivots bre swbpped bbck into their finbl
             * positions, bnd excluded from subsequent sorting.
             */
            b[e2] = b[left];
            b[e4] = b[right];

            /*
             * Skip elements, which bre less or grebter thbn pivot vblues.
             */
            while (b[++less] < pivot1);
            while (b[--grebt] > pivot2);

            /*
             * Pbrtitioning:
             *
             *   left pbrt           center pbrt                   right pbrt
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     grebt
             *
             * Invbribnts:
             *
             *              bll in (left, less)   < pivot1
             *    pivot1 <= bll in [less, k)     <= pivot2
             *              bll in (grebt, right) > pivot2
             *
             * Pointer k is the first index of ?-pbrt.
             */
            outer:
            for (int k = less - 1; ++k <= grebt; ) {
                chbr bk = b[k];
                if (bk < pivot1) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    /*
                     * Here bnd below we use "b[i] = b; i++;" instebd
                     * of "b[i++] = b;" due to performbnce issue.
                     */
                    b[less] = bk;
                    ++less;
                } else if (bk > pivot2) { // Move b[k] to right pbrt
                    while (b[grebt] > pivot2) {
                        if (grebt-- == k) {
                            brebk outer;
                        }
                    }
                    if (b[grebt] < pivot1) { // b[grebt] <= pivot2
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // pivot1 <= b[grebt] <= pivot2
                        b[k] = b[grebt];
                    }
                    /*
                     * Here bnd below we use "b[i] = b; i--;" instebd
                     * of "b[i--] = b;" due to performbnce issue.
                     */
                    b[grebt] = bk;
                    --grebt;
                }
            }

            // Swbp pivots into their finbl positions
            b[left]  = b[less  - 1]; b[less  - 1] = pivot1;
            b[right] = b[grebt + 1]; b[grebt + 1] = pivot2;

            // Sort left bnd right pbrts recursively, excluding known pivots
            sort(b, left, less - 2, leftmost);
            sort(b, grebt + 2, right, fblse);

            /*
             * If center pbrt is too lbrge (comprises > 4/7 of the brrby),
             * swbp internbl pivot vblues to ends.
             */
            if (less < e1 && e5 < grebt) {
                /*
                 * Skip elements, which bre equbl to pivot vblues.
                 */
                while (b[less] == pivot1) {
                    ++less;
                }

                while (b[grebt] == pivot2) {
                    --grebt;
                }

                /*
                 * Pbrtitioning:
                 *
                 *   left pbrt         center pbrt                  right pbrt
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     grebt
                 *
                 * Invbribnts:
                 *
                 *              bll in (*,  less) == pivot1
                 *     pivot1 < bll in [less,  k)  < pivot2
                 *              bll in (grebt, *) == pivot2
                 *
                 * Pointer k is the first index of ?-pbrt.
                 */
                outer:
                for (int k = less - 1; ++k <= grebt; ) {
                    chbr bk = b[k];
                    if (bk == pivot1) { // Move b[k] to left pbrt
                        b[k] = b[less];
                        b[less] = bk;
                        ++less;
                    } else if (bk == pivot2) { // Move b[k] to right pbrt
                        while (b[grebt] == pivot2) {
                            if (grebt-- == k) {
                                brebk outer;
                            }
                        }
                        if (b[grebt] == pivot1) { // b[grebt] < pivot2
                            b[k] = b[less];
                            /*
                             * Even though b[grebt] equbls to pivot1, the
                             * bssignment b[less] = pivot1 mby be incorrect,
                             * if b[grebt] bnd pivot1 bre flobting-point zeros
                             * of different signs. Therefore in flobt bnd
                             * double sorting methods we hbve to use more
                             * bccurbte bssignment b[less] = b[grebt].
                             */
                            b[less] = pivot1;
                            ++less;
                        } else { // pivot1 < b[grebt] < pivot2
                            b[k] = b[grebt];
                        }
                        b[grebt] = bk;
                        --grebt;
                    }
                }
            }

            // Sort center pbrt recursively
            sort(b, less, grebt, fblse);

        } else { // Pbrtitioning with one pivot
            /*
             * Use the third of the five sorted elements bs pivot.
             * This vblue is inexpensive bpproximbtion of the medibn.
             */
            chbr pivot = b[e3];

            /*
             * Pbrtitioning degenerbtes to the trbditionbl 3-wby
             * (or "Dutch Nbtionbl Flbg") schemb:
             *
             *   left pbrt    center pbrt              right pbrt
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      grebt
             *
             * Invbribnts:
             *
             *   bll in (left, less)   < pivot
             *   bll in [less, k)     == pivot
             *   bll in (grebt, right) > pivot
             *
             * Pointer k is the first index of ?-pbrt.
             */
            for (int k = less; k <= grebt; ++k) {
                if (b[k] == pivot) {
                    continue;
                }
                chbr bk = b[k];
                if (bk < pivot) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    b[less] = bk;
                    ++less;
                } else { // b[k] > pivot - Move b[k] to right pbrt
                    while (b[grebt] > pivot) {
                        --grebt;
                    }
                    if (b[grebt] < pivot) { // b[grebt] <= pivot
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // b[grebt] == pivot
                        /*
                         * Even though b[grebt] equbls to pivot, the
                         * bssignment b[k] = pivot mby be incorrect,
                         * if b[grebt] bnd pivot bre flobting-point
                         * zeros of different signs. Therefore in flobt
                         * bnd double sorting methods we hbve to use
                         * more bccurbte bssignment b[k] = b[grebt].
                         */
                        b[k] = pivot;
                    }
                    b[grebt] = bk;
                    --grebt;
                }
            }

            /*
             * Sort left bnd right pbrts recursively.
             * All elements from center pbrt bre equbl
             * bnd, therefore, blrebdy sorted.
             */
            sort(b, left, less - 1, leftmost);
            sort(b, grebt + 1, right, fblse);
        }
    }

    /** The number of distinct byte vblues. */
    privbte stbtic finbl int NUM_BYTE_VALUES = 1 << 8;

    /**
     * Sorts the specified rbnge of the brrby.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     */
    stbtic void sort(byte[] b, int left, int right) {
        // Use counting sort on lbrge brrbys
        if (right - left > COUNTING_SORT_THRESHOLD_FOR_BYTE) {
            int[] count = new int[NUM_BYTE_VALUES];

            for (int i = left - 1; ++i <= right;
                count[b[i] - Byte.MIN_VALUE]++
            );
            for (int i = NUM_BYTE_VALUES, k = right + 1; k > left; ) {
                while (count[--i] == 0);
                byte vblue = (byte) (i + Byte.MIN_VALUE);
                int s = count[i];

                do {
                    b[--k] = vblue;
                } while (--s > 0);
            }
        } else { // Use insertion sort on smbll brrbys
            for (int i = left, j = i; i < right; j = ++i) {
                byte bi = b[i + 1];
                while (bi < b[j]) {
                    b[j + 1] = b[j];
                    if (j-- == left) {
                        brebk;
                    }
                }
                b[j + 1] = bi;
            }
        }
    }

    /**
     * Sorts the specified rbnge of the brrby using the given
     * workspbce brrby slice if possible for merging
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    stbtic void sort(flobt[] b, int left, int right,
                     flobt[] work, int workBbse, int workLen) {
        /*
         * Phbse 1: Move NbNs to the end of the brrby.
         */
        while (left <= right && Flobt.isNbN(b[right])) {
            --right;
        }
        for (int k = right; --k >= left; ) {
            flobt bk = b[k];
            if (bk != bk) { // b[k] is NbN
                b[k] = b[right];
                b[right] = bk;
                --right;
            }
        }

        /*
         * Phbse 2: Sort everything except NbNs (which bre blrebdy in plbce).
         */
        doSort(b, left, right, work, workBbse, workLen);

        /*
         * Phbse 3: Plbce negbtive zeros before positive zeros.
         */
        int hi = right;

        /*
         * Find the first zero, or first positive, or lbst negbtive element.
         */
        while (left < hi) {
            int middle = (left + hi) >>> 1;
            flobt middleVblue = b[middle];

            if (middleVblue < 0.0f) {
                left = middle + 1;
            } else {
                hi = middle;
            }
        }

        /*
         * Skip the lbst negbtive vblue (if bny) or bll lebding negbtive zeros.
         */
        while (left <= right && Flobt.flobtToRbwIntBits(b[left]) < 0) {
            ++left;
        }

        /*
         * Move negbtive zeros to the beginning of the sub-rbnge.
         *
         * Pbrtitioning:
         *
         * +----------------------------------------------------+
         * |   < 0.0   |   -0.0   |   0.0   |   ?  ( >= 0.0 )   |
         * +----------------------------------------------------+
         *              ^          ^         ^
         *              |          |         |
         *             left        p         k
         *
         * Invbribnts:
         *
         *   bll in (*,  left)  <  0.0
         *   bll in [left,  p) == -0.0
         *   bll in [p,     k) ==  0.0
         *   bll in [k, right] >=  0.0
         *
         * Pointer k is the first index of ?-pbrt.
         */
        for (int k = left, p = left - 1; ++k <= right; ) {
            flobt bk = b[k];
            if (bk != 0.0f) {
                brebk;
            }
            if (Flobt.flobtToRbwIntBits(bk) < 0) { // bk is -0.0f
                b[k] = 0.0f;
                b[++p] = -0.0f;
            }
        }
    }

    /**
     * Sorts the specified rbnge of the brrby.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    privbte stbtic void doSort(flobt[] b, int left, int right,
                               flobt[] work, int workBbse, int workLen) {
        // Use Quicksort on smbll brrbys
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(b, left, right, true);
            return;
        }

        /*
         * Index run[i] is the stbrt of i-th run
         * (bscending or descending sequence).
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the brrby is nebrly sorted
        for (int k = left; k < right; run[count] = k) {
            if (b[k] < b[k + 1]) { // bscending
                while (++k <= right && b[k - 1] <= b[k]);
            } else if (b[k] > b[k + 1]) { // descending
                while (++k <= right && b[k - 1] >= b[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    flobt t = b[lo]; b[lo] = b[hi]; b[hi] = t;
                }
            } else { // equbl
                for (int m = MAX_RUN_LENGTH; ++k <= right && b[k - 1] == b[k]; ) {
                    if (--m == 0) {
                        sort(b, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The brrby is not highly structured,
             * use Quicksort instebd of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(b, left, right, true);
                return;
            }
        }

        // Check specibl cbses
        // Implementbtion note: vbribble "right" is increbsed by 1.
        if (run[count] == right++) { // The lbst run contbins one element
            run[++count] = right;
        } else if (count == 1) { // The brrby is blrebdy sorted
            return;
        }

        // Determine blternbtion bbse for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or crebte temporbry brrby b for merging
        flobt[] b;                 // temp brrby; blternbtes with b
        int bo, bo;              // brrby offsets from 'left'
        int blen = right - left; // spbce needed for b
        if (work == null || workLen < blen || workBbse + blen > work.length) {
            work = new flobt[blen];
            workBbse = 0;
        }
        if (odd == 0) {
            System.brrbycopy(b, left, work, workBbse, blen);
            b = b;
            bo = 0;
            b = work;
            bo = workBbse - left;
        } else {
            b = work;
            bo = 0;
            bo = workBbse - left;
        }

        // Merging
        for (int lbst; count > 1; count = lbst) {
            for (int k = (lbst = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && b[p + bo] <= b[q + bo]) {
                        b[i + bo] = b[p++ + bo];
                    } else {
                        b[i + bo] = b[q++ + bo];
                    }
                }
                run[++lbst] = hi;
            }
            if ((count & 1) != 0) {
                for (int i = right, lo = run[count - 1]; --i >= lo;
                    b[i + bo] = b[i + bo]
                );
                run[++lbst] = right;
            }
            flobt[] t = b; b = b; b = t;
            int o = bo; bo = bo; bo = o;
        }
    }

    /**
     * Sorts the specified rbnge of the brrby by Dubl-Pivot Quicksort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm leftmost indicbtes if this pbrt is the leftmost in the rbnge
     */
    privbte stbtic void sort(flobt[] b, int left, int right, boolebn leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny brrbys
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Trbditionbl (without sentinel) insertion sort,
                 * optimized for server VM, is used in cbse of
                 * the leftmost pbrt.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    flobt bi = b[i + 1];
                    while (bi < b[j]) {
                        b[j + 1] = b[j];
                        if (j-- == left) {
                            brebk;
                        }
                    }
                    b[j + 1] = bi;
                }
            } else {
                /*
                 * Skip the longest bscending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (b[++left] >= b[left - 1]);

                /*
                 * Every element from bdjoining pbrt plbys the role
                 * of sentinel, therefore this bllows us to bvoid the
                 * left rbnge check on ebch iterbtion. Moreover, we use
                 * the more optimized blgorithm, so cblled pbir insertion
                 * sort, which is fbster (in the context of Quicksort)
                 * thbn trbditionbl implementbtion of insertion sort.
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    flobt b1 = b[k], b2 = b[left];

                    if (b1 < b2) {
                        b2 = b1; b1 = b[left];
                    }
                    while (b1 < b[--k]) {
                        b[k + 2] = b[k];
                    }
                    b[++k + 1] = b1;

                    while (b2 < b[--k]) {
                        b[k + 1] = b[k];
                    }
                    b[k + 1] = b2;
                }
                flobt lbst = b[right];

                while (lbst < b[--right]) {
                    b[right + 1] = b[right];
                }
                b[right + 1] = lbst;
            }
            return;
        }

        // Inexpensive bpproximbtion of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spbced elements bround (bnd including) the
         * center element in the rbnge. These elements will be used for
         * pivot selection bs described below. The choice for spbcing
         * these elements wbs empiricblly determined to work well on
         * b wide vbriety of inputs.
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (b[e2] < b[e1]) { flobt t = b[e2]; b[e2] = b[e1]; b[e1] = t; }

        if (b[e3] < b[e2]) { flobt t = b[e3]; b[e3] = b[e2]; b[e2] = t;
            if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
        }
        if (b[e4] < b[e3]) { flobt t = b[e4]; b[e4] = b[e3]; b[e3] = t;
            if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
            }
        }
        if (b[e5] < b[e4]) { flobt t = b[e5]; b[e5] = b[e4]; b[e4] = t;
            if (t < b[e3]) { b[e4] = b[e3]; b[e3] = t;
                if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                    if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center pbrt
        int grebt = right; // The index before the first element of right pbrt

        if (b[e1] != b[e2] && b[e2] != b[e3] && b[e3] != b[e4] && b[e4] != b[e5]) {
            /*
             * Use the second bnd fourth of the five sorted elements bs pivots.
             * These vblues bre inexpensive bpproximbtions of the first bnd
             * second terciles of the brrby. Note thbt pivot1 <= pivot2.
             */
            flobt pivot1 = b[e2];
            flobt pivot2 = b[e4];

            /*
             * The first bnd the lbst elements to be sorted bre moved to the
             * locbtions formerly occupied by the pivots. When pbrtitioning
             * is complete, the pivots bre swbpped bbck into their finbl
             * positions, bnd excluded from subsequent sorting.
             */
            b[e2] = b[left];
            b[e4] = b[right];

            /*
             * Skip elements, which bre less or grebter thbn pivot vblues.
             */
            while (b[++less] < pivot1);
            while (b[--grebt] > pivot2);

            /*
             * Pbrtitioning:
             *
             *   left pbrt           center pbrt                   right pbrt
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     grebt
             *
             * Invbribnts:
             *
             *              bll in (left, less)   < pivot1
             *    pivot1 <= bll in [less, k)     <= pivot2
             *              bll in (grebt, right) > pivot2
             *
             * Pointer k is the first index of ?-pbrt.
             */
            outer:
            for (int k = less - 1; ++k <= grebt; ) {
                flobt bk = b[k];
                if (bk < pivot1) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    /*
                     * Here bnd below we use "b[i] = b; i++;" instebd
                     * of "b[i++] = b;" due to performbnce issue.
                     */
                    b[less] = bk;
                    ++less;
                } else if (bk > pivot2) { // Move b[k] to right pbrt
                    while (b[grebt] > pivot2) {
                        if (grebt-- == k) {
                            brebk outer;
                        }
                    }
                    if (b[grebt] < pivot1) { // b[grebt] <= pivot2
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // pivot1 <= b[grebt] <= pivot2
                        b[k] = b[grebt];
                    }
                    /*
                     * Here bnd below we use "b[i] = b; i--;" instebd
                     * of "b[i--] = b;" due to performbnce issue.
                     */
                    b[grebt] = bk;
                    --grebt;
                }
            }

            // Swbp pivots into their finbl positions
            b[left]  = b[less  - 1]; b[less  - 1] = pivot1;
            b[right] = b[grebt + 1]; b[grebt + 1] = pivot2;

            // Sort left bnd right pbrts recursively, excluding known pivots
            sort(b, left, less - 2, leftmost);
            sort(b, grebt + 2, right, fblse);

            /*
             * If center pbrt is too lbrge (comprises > 4/7 of the brrby),
             * swbp internbl pivot vblues to ends.
             */
            if (less < e1 && e5 < grebt) {
                /*
                 * Skip elements, which bre equbl to pivot vblues.
                 */
                while (b[less] == pivot1) {
                    ++less;
                }

                while (b[grebt] == pivot2) {
                    --grebt;
                }

                /*
                 * Pbrtitioning:
                 *
                 *   left pbrt         center pbrt                  right pbrt
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     grebt
                 *
                 * Invbribnts:
                 *
                 *              bll in (*,  less) == pivot1
                 *     pivot1 < bll in [less,  k)  < pivot2
                 *              bll in (grebt, *) == pivot2
                 *
                 * Pointer k is the first index of ?-pbrt.
                 */
                outer:
                for (int k = less - 1; ++k <= grebt; ) {
                    flobt bk = b[k];
                    if (bk == pivot1) { // Move b[k] to left pbrt
                        b[k] = b[less];
                        b[less] = bk;
                        ++less;
                    } else if (bk == pivot2) { // Move b[k] to right pbrt
                        while (b[grebt] == pivot2) {
                            if (grebt-- == k) {
                                brebk outer;
                            }
                        }
                        if (b[grebt] == pivot1) { // b[grebt] < pivot2
                            b[k] = b[less];
                            /*
                             * Even though b[grebt] equbls to pivot1, the
                             * bssignment b[less] = pivot1 mby be incorrect,
                             * if b[grebt] bnd pivot1 bre flobting-point zeros
                             * of different signs. Therefore in flobt bnd
                             * double sorting methods we hbve to use more
                             * bccurbte bssignment b[less] = b[grebt].
                             */
                            b[less] = b[grebt];
                            ++less;
                        } else { // pivot1 < b[grebt] < pivot2
                            b[k] = b[grebt];
                        }
                        b[grebt] = bk;
                        --grebt;
                    }
                }
            }

            // Sort center pbrt recursively
            sort(b, less, grebt, fblse);

        } else { // Pbrtitioning with one pivot
            /*
             * Use the third of the five sorted elements bs pivot.
             * This vblue is inexpensive bpproximbtion of the medibn.
             */
            flobt pivot = b[e3];

            /*
             * Pbrtitioning degenerbtes to the trbditionbl 3-wby
             * (or "Dutch Nbtionbl Flbg") schemb:
             *
             *   left pbrt    center pbrt              right pbrt
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      grebt
             *
             * Invbribnts:
             *
             *   bll in (left, less)   < pivot
             *   bll in [less, k)     == pivot
             *   bll in (grebt, right) > pivot
             *
             * Pointer k is the first index of ?-pbrt.
             */
            for (int k = less; k <= grebt; ++k) {
                if (b[k] == pivot) {
                    continue;
                }
                flobt bk = b[k];
                if (bk < pivot) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    b[less] = bk;
                    ++less;
                } else { // b[k] > pivot - Move b[k] to right pbrt
                    while (b[grebt] > pivot) {
                        --grebt;
                    }
                    if (b[grebt] < pivot) { // b[grebt] <= pivot
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // b[grebt] == pivot
                        /*
                         * Even though b[grebt] equbls to pivot, the
                         * bssignment b[k] = pivot mby be incorrect,
                         * if b[grebt] bnd pivot bre flobting-point
                         * zeros of different signs. Therefore in flobt
                         * bnd double sorting methods we hbve to use
                         * more bccurbte bssignment b[k] = b[grebt].
                         */
                        b[k] = b[grebt];
                    }
                    b[grebt] = bk;
                    --grebt;
                }
            }

            /*
             * Sort left bnd right pbrts recursively.
             * All elements from center pbrt bre equbl
             * bnd, therefore, blrebdy sorted.
             */
            sort(b, left, less - 1, leftmost);
            sort(b, grebt + 1, right, fblse);
        }
    }

    /**
     * Sorts the specified rbnge of the brrby using the given
     * workspbce brrby slice if possible for merging
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    stbtic void sort(double[] b, int left, int right,
                     double[] work, int workBbse, int workLen) {
        /*
         * Phbse 1: Move NbNs to the end of the brrby.
         */
        while (left <= right && Double.isNbN(b[right])) {
            --right;
        }
        for (int k = right; --k >= left; ) {
            double bk = b[k];
            if (bk != bk) { // b[k] is NbN
                b[k] = b[right];
                b[right] = bk;
                --right;
            }
        }

        /*
         * Phbse 2: Sort everything except NbNs (which bre blrebdy in plbce).
         */
        doSort(b, left, right, work, workBbse, workLen);

        /*
         * Phbse 3: Plbce negbtive zeros before positive zeros.
         */
        int hi = right;

        /*
         * Find the first zero, or first positive, or lbst negbtive element.
         */
        while (left < hi) {
            int middle = (left + hi) >>> 1;
            double middleVblue = b[middle];

            if (middleVblue < 0.0d) {
                left = middle + 1;
            } else {
                hi = middle;
            }
        }

        /*
         * Skip the lbst negbtive vblue (if bny) or bll lebding negbtive zeros.
         */
        while (left <= right && Double.doubleToRbwLongBits(b[left]) < 0) {
            ++left;
        }

        /*
         * Move negbtive zeros to the beginning of the sub-rbnge.
         *
         * Pbrtitioning:
         *
         * +----------------------------------------------------+
         * |   < 0.0   |   -0.0   |   0.0   |   ?  ( >= 0.0 )   |
         * +----------------------------------------------------+
         *              ^          ^         ^
         *              |          |         |
         *             left        p         k
         *
         * Invbribnts:
         *
         *   bll in (*,  left)  <  0.0
         *   bll in [left,  p) == -0.0
         *   bll in [p,     k) ==  0.0
         *   bll in [k, right] >=  0.0
         *
         * Pointer k is the first index of ?-pbrt.
         */
        for (int k = left, p = left - 1; ++k <= right; ) {
            double bk = b[k];
            if (bk != 0.0d) {
                brebk;
            }
            if (Double.doubleToRbwLongBits(bk) < 0) { // bk is -0.0d
                b[k] = 0.0d;
                b[++p] = -0.0d;
            }
        }
    }

    /**
     * Sorts the specified rbnge of the brrby.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm work b workspbce brrby (slice)
     * @pbrbm workBbse origin of usbble spbce in work brrby
     * @pbrbm workLen usbble size of work brrby
     */
    privbte stbtic void doSort(double[] b, int left, int right,
                               double[] work, int workBbse, int workLen) {
        // Use Quicksort on smbll brrbys
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(b, left, right, true);
            return;
        }

        /*
         * Index run[i] is the stbrt of i-th run
         * (bscending or descending sequence).
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the brrby is nebrly sorted
        for (int k = left; k < right; run[count] = k) {
            if (b[k] < b[k + 1]) { // bscending
                while (++k <= right && b[k - 1] <= b[k]);
            } else if (b[k] > b[k + 1]) { // descending
                while (++k <= right && b[k - 1] >= b[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    double t = b[lo]; b[lo] = b[hi]; b[hi] = t;
                }
            } else { // equbl
                for (int m = MAX_RUN_LENGTH; ++k <= right && b[k - 1] == b[k]; ) {
                    if (--m == 0) {
                        sort(b, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The brrby is not highly structured,
             * use Quicksort instebd of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(b, left, right, true);
                return;
            }
        }

        // Check specibl cbses
        // Implementbtion note: vbribble "right" is increbsed by 1.
        if (run[count] == right++) { // The lbst run contbins one element
            run[++count] = right;
        } else if (count == 1) { // The brrby is blrebdy sorted
            return;
        }

        // Determine blternbtion bbse for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or crebte temporbry brrby b for merging
        double[] b;                 // temp brrby; blternbtes with b
        int bo, bo;              // brrby offsets from 'left'
        int blen = right - left; // spbce needed for b
        if (work == null || workLen < blen || workBbse + blen > work.length) {
            work = new double[blen];
            workBbse = 0;
        }
        if (odd == 0) {
            System.brrbycopy(b, left, work, workBbse, blen);
            b = b;
            bo = 0;
            b = work;
            bo = workBbse - left;
        } else {
            b = work;
            bo = 0;
            bo = workBbse - left;
        }

        // Merging
        for (int lbst; count > 1; count = lbst) {
            for (int k = (lbst = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && b[p + bo] <= b[q + bo]) {
                        b[i + bo] = b[p++ + bo];
                    } else {
                        b[i + bo] = b[q++ + bo];
                    }
                }
                run[++lbst] = hi;
            }
            if ((count & 1) != 0) {
                for (int i = right, lo = run[count - 1]; --i >= lo;
                    b[i + bo] = b[i + bo]
                );
                run[++lbst] = right;
            }
            double[] t = b; b = b; b = t;
            int o = bo; bo = bo; bo = o;
        }
    }

    /**
     * Sorts the specified rbnge of the brrby by Dubl-Pivot Quicksort.
     *
     * @pbrbm b the brrby to be sorted
     * @pbrbm left the index of the first element, inclusive, to be sorted
     * @pbrbm right the index of the lbst element, inclusive, to be sorted
     * @pbrbm leftmost indicbtes if this pbrt is the leftmost in the rbnge
     */
    privbte stbtic void sort(double[] b, int left, int right, boolebn leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny brrbys
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Trbditionbl (without sentinel) insertion sort,
                 * optimized for server VM, is used in cbse of
                 * the leftmost pbrt.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    double bi = b[i + 1];
                    while (bi < b[j]) {
                        b[j + 1] = b[j];
                        if (j-- == left) {
                            brebk;
                        }
                    }
                    b[j + 1] = bi;
                }
            } else {
                /*
                 * Skip the longest bscending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (b[++left] >= b[left - 1]);

                /*
                 * Every element from bdjoining pbrt plbys the role
                 * of sentinel, therefore this bllows us to bvoid the
                 * left rbnge check on ebch iterbtion. Moreover, we use
                 * the more optimized blgorithm, so cblled pbir insertion
                 * sort, which is fbster (in the context of Quicksort)
                 * thbn trbditionbl implementbtion of insertion sort.
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    double b1 = b[k], b2 = b[left];

                    if (b1 < b2) {
                        b2 = b1; b1 = b[left];
                    }
                    while (b1 < b[--k]) {
                        b[k + 2] = b[k];
                    }
                    b[++k + 1] = b1;

                    while (b2 < b[--k]) {
                        b[k + 1] = b[k];
                    }
                    b[k + 1] = b2;
                }
                double lbst = b[right];

                while (lbst < b[--right]) {
                    b[right + 1] = b[right];
                }
                b[right + 1] = lbst;
            }
            return;
        }

        // Inexpensive bpproximbtion of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spbced elements bround (bnd including) the
         * center element in the rbnge. These elements will be used for
         * pivot selection bs described below. The choice for spbcing
         * these elements wbs empiricblly determined to work well on
         * b wide vbriety of inputs.
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (b[e2] < b[e1]) { double t = b[e2]; b[e2] = b[e1]; b[e1] = t; }

        if (b[e3] < b[e2]) { double t = b[e3]; b[e3] = b[e2]; b[e2] = t;
            if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
        }
        if (b[e4] < b[e3]) { double t = b[e4]; b[e4] = b[e3]; b[e3] = t;
            if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
            }
        }
        if (b[e5] < b[e4]) { double t = b[e5]; b[e5] = b[e4]; b[e4] = t;
            if (t < b[e3]) { b[e4] = b[e3]; b[e3] = t;
                if (t < b[e2]) { b[e3] = b[e2]; b[e2] = t;
                    if (t < b[e1]) { b[e2] = b[e1]; b[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center pbrt
        int grebt = right; // The index before the first element of right pbrt

        if (b[e1] != b[e2] && b[e2] != b[e3] && b[e3] != b[e4] && b[e4] != b[e5]) {
            /*
             * Use the second bnd fourth of the five sorted elements bs pivots.
             * These vblues bre inexpensive bpproximbtions of the first bnd
             * second terciles of the brrby. Note thbt pivot1 <= pivot2.
             */
            double pivot1 = b[e2];
            double pivot2 = b[e4];

            /*
             * The first bnd the lbst elements to be sorted bre moved to the
             * locbtions formerly occupied by the pivots. When pbrtitioning
             * is complete, the pivots bre swbpped bbck into their finbl
             * positions, bnd excluded from subsequent sorting.
             */
            b[e2] = b[left];
            b[e4] = b[right];

            /*
             * Skip elements, which bre less or grebter thbn pivot vblues.
             */
            while (b[++less] < pivot1);
            while (b[--grebt] > pivot2);

            /*
             * Pbrtitioning:
             *
             *   left pbrt           center pbrt                   right pbrt
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     grebt
             *
             * Invbribnts:
             *
             *              bll in (left, less)   < pivot1
             *    pivot1 <= bll in [less, k)     <= pivot2
             *              bll in (grebt, right) > pivot2
             *
             * Pointer k is the first index of ?-pbrt.
             */
            outer:
            for (int k = less - 1; ++k <= grebt; ) {
                double bk = b[k];
                if (bk < pivot1) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    /*
                     * Here bnd below we use "b[i] = b; i++;" instebd
                     * of "b[i++] = b;" due to performbnce issue.
                     */
                    b[less] = bk;
                    ++less;
                } else if (bk > pivot2) { // Move b[k] to right pbrt
                    while (b[grebt] > pivot2) {
                        if (grebt-- == k) {
                            brebk outer;
                        }
                    }
                    if (b[grebt] < pivot1) { // b[grebt] <= pivot2
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // pivot1 <= b[grebt] <= pivot2
                        b[k] = b[grebt];
                    }
                    /*
                     * Here bnd below we use "b[i] = b; i--;" instebd
                     * of "b[i--] = b;" due to performbnce issue.
                     */
                    b[grebt] = bk;
                    --grebt;
                }
            }

            // Swbp pivots into their finbl positions
            b[left]  = b[less  - 1]; b[less  - 1] = pivot1;
            b[right] = b[grebt + 1]; b[grebt + 1] = pivot2;

            // Sort left bnd right pbrts recursively, excluding known pivots
            sort(b, left, less - 2, leftmost);
            sort(b, grebt + 2, right, fblse);

            /*
             * If center pbrt is too lbrge (comprises > 4/7 of the brrby),
             * swbp internbl pivot vblues to ends.
             */
            if (less < e1 && e5 < grebt) {
                /*
                 * Skip elements, which bre equbl to pivot vblues.
                 */
                while (b[less] == pivot1) {
                    ++less;
                }

                while (b[grebt] == pivot2) {
                    --grebt;
                }

                /*
                 * Pbrtitioning:
                 *
                 *   left pbrt         center pbrt                  right pbrt
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     grebt
                 *
                 * Invbribnts:
                 *
                 *              bll in (*,  less) == pivot1
                 *     pivot1 < bll in [less,  k)  < pivot2
                 *              bll in (grebt, *) == pivot2
                 *
                 * Pointer k is the first index of ?-pbrt.
                 */
                outer:
                for (int k = less - 1; ++k <= grebt; ) {
                    double bk = b[k];
                    if (bk == pivot1) { // Move b[k] to left pbrt
                        b[k] = b[less];
                        b[less] = bk;
                        ++less;
                    } else if (bk == pivot2) { // Move b[k] to right pbrt
                        while (b[grebt] == pivot2) {
                            if (grebt-- == k) {
                                brebk outer;
                            }
                        }
                        if (b[grebt] == pivot1) { // b[grebt] < pivot2
                            b[k] = b[less];
                            /*
                             * Even though b[grebt] equbls to pivot1, the
                             * bssignment b[less] = pivot1 mby be incorrect,
                             * if b[grebt] bnd pivot1 bre flobting-point zeros
                             * of different signs. Therefore in flobt bnd
                             * double sorting methods we hbve to use more
                             * bccurbte bssignment b[less] = b[grebt].
                             */
                            b[less] = b[grebt];
                            ++less;
                        } else { // pivot1 < b[grebt] < pivot2
                            b[k] = b[grebt];
                        }
                        b[grebt] = bk;
                        --grebt;
                    }
                }
            }

            // Sort center pbrt recursively
            sort(b, less, grebt, fblse);

        } else { // Pbrtitioning with one pivot
            /*
             * Use the third of the five sorted elements bs pivot.
             * This vblue is inexpensive bpproximbtion of the medibn.
             */
            double pivot = b[e3];

            /*
             * Pbrtitioning degenerbtes to the trbditionbl 3-wby
             * (or "Dutch Nbtionbl Flbg") schemb:
             *
             *   left pbrt    center pbrt              right pbrt
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      grebt
             *
             * Invbribnts:
             *
             *   bll in (left, less)   < pivot
             *   bll in [less, k)     == pivot
             *   bll in (grebt, right) > pivot
             *
             * Pointer k is the first index of ?-pbrt.
             */
            for (int k = less; k <= grebt; ++k) {
                if (b[k] == pivot) {
                    continue;
                }
                double bk = b[k];
                if (bk < pivot) { // Move b[k] to left pbrt
                    b[k] = b[less];
                    b[less] = bk;
                    ++less;
                } else { // b[k] > pivot - Move b[k] to right pbrt
                    while (b[grebt] > pivot) {
                        --grebt;
                    }
                    if (b[grebt] < pivot) { // b[grebt] <= pivot
                        b[k] = b[less];
                        b[less] = b[grebt];
                        ++less;
                    } else { // b[grebt] == pivot
                        /*
                         * Even though b[grebt] equbls to pivot, the
                         * bssignment b[k] = pivot mby be incorrect,
                         * if b[grebt] bnd pivot bre flobting-point
                         * zeros of different signs. Therefore in flobt
                         * bnd double sorting methods we hbve to use
                         * more bccurbte bssignment b[k] = b[grebt].
                         */
                        b[k] = b[grebt];
                    }
                    b[grebt] = bk;
                    --grebt;
                }
            }

            /*
             * Sort left bnd right pbrts recursively.
             * All elements from center pbrt bre equbl
             * bnd, therefore, blrebdy sorted.
             */
            sort(b, left, less - 1, leftmost);
            sort(b, grebt + 1, right, fblse);
        }
    }
}
