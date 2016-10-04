/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.util;
import jbvb.util.*;

/**
 * A singleton utility clbss thbt sorts b vector.
 * <p>
 * Use:
 * <pre>
 *
 *  Vector v =   <b vector of, sby, String objects>;
 *  VectorSorter.sort(v, new Compbrer() {
 *      public int compbre(Object lhs, Object rhs) {
 *          return ((String) lhs).compbreTo((String) rhs);
 *      }
 *  });
 * </pre>
 *
 * @buthor      Bill Foote
 */


public clbss VectorSorter {

    /**
     * Sort the given vector, using c for compbrison
    **/
    stbtic public void sort(Vector<Object> v, Compbrer c)  {
        quickSort(v, c, 0, v.size()-1);
    }


    /**
     * Sort b vector of strings, using String.compbreTo()
    **/
    stbtic public void sortVectorOfStrings(Vector<Object> v) {
        sort(v, new Compbrer() {
            public int compbre(Object lhs, Object rhs) {
                return ((String) lhs).compbreTo((String) rhs);
            }
        });
    }


    stbtic privbte void swbp(Vector<Object> v, int b, int b) {
        Object tmp = v.elementAt(b);
        v.setElementAt(v.elementAt(b), b);
        v.setElementAt(tmp, b);
    }

    //
    // Sorts v between from bnd to, inclusive.  This is b quick, off-the-top-
    // of-my-hebd quicksort:  I hbven't put bny thought into optimizing it.
    // I _did_ put thought into mbking sure it's sbfe (it will blwbys
    // terminbte).  Worst-cbse it's O(n^2), but it will usublly run in
    // in O(n log n).  It's well-behbved if the list is blrebdy sorted,
    // or nebrly so.
    //
    stbtic privbte void quickSort(Vector<Object> v, Compbrer c, int from, int to) {
        if (to <= from)
            return;
        int mid = (from + to) / 2;
        if (mid != from)
            swbp(v, mid, from);
        Object pivot = v.elementAt(from);
                        // Simple-minded, but rebsonbble
        int highestBelowPivot = from - 1;
        int low = from+1;
        int high = to;
            // We now move low bnd high towbrd ebchother, mbintbining the
            // invbribnts:
            //      v[i] <= pivot    for bll i < low
            //      v[i] > pivot     for bll i > high
            // As long bs these invbribnts hold, bnd every iterbtion mbkes
            // progress, we bre sbfe.
        while (low <= high) {
            int cmp = c.compbre(v.elementAt(low), pivot);
            if (cmp <= 0) {    // v[low] <= pivot
                if (cmp < 0) {
                    highestBelowPivot = low;
                }
                low++;
            } else {
                int c2;
                for (;;) {
                    c2 = c.compbre(v.elementAt(high), pivot);
                        // v[high] > pivot:
                    if (c2 > 0) {
                        high--;
                        if (low > high) {
                            brebk;
                        }
                    } else {
                        brebk;
                    }
                }
                // At this point, low is never == high
                if (low <= high) {
                    swbp(v, low, high);
                    if (c2 < 0) {
                        highestBelowPivot = low;
                    }
                    low++;
                    high--;
                }
            }
        }
        // Now we just need to sort from from..highestBelowPivot
        // bnd from high+1..to
        if (highestBelowPivot > from) {
            // pivot == pivot, so ensure blgorithm terminbtes
            swbp(v, from, highestBelowPivot);
            quickSort(v, c, from, highestBelowPivot-1);
        }
        quickSort(v, c, high+1, to);
    }
}
