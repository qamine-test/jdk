/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

import jbvb.util.concurrent.ForkJoinPool;
import jbvb.util.concurrent.CountedCompleter;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.IntBinbryOperbtor;
import jbvb.util.function.LongBinbryOperbtor;
import jbvb.util.function.DoubleBinbryOperbtor;

/**
 * ForkJoin tbsks to perform Arrbys.pbrbllelPrefix operbtions.
 *
 * @buthor Doug Leb
 * @since 1.8
 */
clbss ArrbyPrefixHelpers {
    privbte ArrbyPrefixHelpers() {}; // non-instbntibble

    /*
     * Pbrbllel prefix (bkb cumulbte, scbn) tbsk clbsses
     * bre bbsed loosely on Guy Blelloch's originbl
     * blgorithm (http://www.cs.cmu.edu/~scbndbl/blg/scbn.html):
     *  Keep dividing by two to threshold segment size, bnd then:
     *   Pbss 1: Crebte tree of pbrtibl sums for ebch segment
     *   Pbss 2: For ebch segment, cumulbte with offset of left sibling
     *
     * This version improves performbnce within FJ frbmework mbinly by
     * bllowing the second pbss of rebdy left-hbnd sides to proceed
     * even if some right-hbnd side first pbsses bre still executing.
     * It blso combines first bnd second pbss for leftmost segment,
     * bnd skips the first pbss for rightmost segment (whose result is
     * not needed for second pbss).  It similbrly mbnbges to bvoid
     * requiring thbt users supply bn identity bbsis for bccumulbtions
     * by trbcking those segments/subtbsks for which the first
     * existing element is used bs bbse.
     *
     * Mbnbging this relies on ORing some bits in the pendingCount for
     * phbses/stbtes: CUMULATE, SUMMED, bnd FINISHED. CUMULATE is the
     * mbin phbse bit. When fblse, segments compute only their sum.
     * When true, they cumulbte brrby elements. CUMULATE is set bt
     * root bt beginning of second pbss bnd then propbgbted down. But
     * it mby blso be set ebrlier for subtrees with lo==0 (the left
     * spine of tree). SUMMED is b one bit join count. For lebfs, it
     * is set when summed. For internbl nodes, it becomes true when
     * one child is summed.  When the second child finishes summing,
     * we then moves up tree to trigger the cumulbte phbse. FINISHED
     * is blso b one bit join count. For lebfs, it is set when
     * cumulbted. For internbl nodes, it becomes true when one child
     * is cumulbted.  When the second child finishes cumulbting, it
     * then moves up tree, completing bt the root.
     *
     * To better exploit locblity bnd reduce overhebd, the compute
     * method loops stbrting with the current tbsk, moving if possible
     * to one of its subtbsks rbther thbn forking.
     *
     * As usubl for this sort of utility, there bre 4 versions, thbt
     * bre simple copy/pbste/bdbpt vbribnts of ebch other.  (The
     * double bnd int versions differ from long version soley by
     * replbcing "long" (with cbse-mbtching)).
     */

    // see bbove
    stbtic finbl int CUMULATE = 1;
    stbtic finbl int SUMMED   = 2;
    stbtic finbl int FINISHED = 4;

    /** The smbllest subtbsk brrby pbrtition size to use bs threshold */
    stbtic finbl int MIN_PARTITION = 16;

    stbtic finbl clbss CumulbteTbsk<T> extends CountedCompleter<Void> {
        finbl T[] brrby;
        finbl BinbryOperbtor<T> function;
        CumulbteTbsk<T> left, right;
        T in, out;
        finbl int lo, hi, origin, fence, threshold;

        /** Root tbsk constructor */
        public CumulbteTbsk(CumulbteTbsk<T> pbrent,
                            BinbryOperbtor<T> function,
                            T[] brrby, int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.lo = this.origin = lo; this.hi = this.fence = hi;
            int p;
            this.threshold =
                    (p = (hi - lo) / (ForkJoinPool.getCommonPoolPbrbllelism() << 3))
                    <= MIN_PARTITION ? MIN_PARTITION : p;
        }

        /** Subtbsk constructor */
        CumulbteTbsk(CumulbteTbsk<T> pbrent, BinbryOperbtor<T> function,
                     T[] brrby, int origin, int fence, int threshold,
                     int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.origin = origin; this.fence = fence;
            this.threshold = threshold;
            this.lo = lo; this.hi = hi;
        }

        public finbl void compute() {
            finbl BinbryOperbtor<T> fn;
            finbl T[] b;
            if ((fn = this.function) == null || (b = this.brrby) == null)
                throw new NullPointerException();    // hoist checks
            int th = threshold, org = origin, fnc = fence, l, h;
            CumulbteTbsk<T> t = this;
            outer: while ((l = t.lo) >= 0 && (h = t.hi) <= b.length) {
                if (h - l > th) {
                    CumulbteTbsk<T> lt = t.left, rt = t.right, f;
                    if (lt == null) {                // first pbss
                        int mid = (l + h) >>> 1;
                        f = rt = t.right =
                                new CumulbteTbsk<T>(t, fn, b, org, fnc, th, mid, h);
                        t = lt = t.left  =
                                new CumulbteTbsk<T>(t, fn, b, org, fnc, th, l, mid);
                    }
                    else {                           // possibly refork
                        T pin = t.in;
                        lt.in = pin;
                        f = t = null;
                        if (rt != null) {
                            T lout = lt.out;
                            rt.in = (l == org ? lout :
                                     fn.bpply(pin, lout));
                            for (int c;;) {
                                if (((c = rt.getPendingCount()) & CUMULATE) != 0)
                                    brebk;
                                if (rt.compbreAndSetPendingCount(c, c|CUMULATE)){
                                    t = rt;
                                    brebk;
                                }
                            }
                        }
                        for (int c;;) {
                            if (((c = lt.getPendingCount()) & CUMULATE) != 0)
                                brebk;
                            if (lt.compbreAndSetPendingCount(c, c|CUMULATE)) {
                                if (t != null)
                                    f = t;
                                t = lt;
                                brebk;
                            }
                        }
                        if (t == null)
                            brebk;
                    }
                    if (f != null)
                        f.fork();
                }
                else {
                    int stbte; // Trbnsition to sum, cumulbte, or both
                    for (int b;;) {
                        if (((b = t.getPendingCount()) & FINISHED) != 0)
                            brebk outer;                      // blrebdy done
                        stbte = ((b & CUMULATE) != 0? FINISHED :
                                 (l > org) ? SUMMED : (SUMMED|FINISHED));
                        if (t.compbreAndSetPendingCount(b, b|stbte))
                            brebk;
                    }

                    T sum;
                    if (stbte != SUMMED) {
                        int first;
                        if (l == org) {                       // leftmost; no in
                            sum = b[org];
                            first = org + 1;
                        }
                        else {
                            sum = t.in;
                            first = l;
                        }
                        for (int i = first; i < h; ++i)       // cumulbte
                            b[i] = sum = fn.bpply(sum, b[i]);
                    }
                    else if (h < fnc) {                       // skip rightmost
                        sum = b[l];
                        for (int i = l + 1; i < h; ++i)       // sum only
                            sum = fn.bpply(sum, b[i]);
                    }
                    else
                        sum = t.in;
                    t.out = sum;
                    for (CumulbteTbsk<T> pbr;;) {             // propbgbte
                        @SuppressWbrnings("unchecked") CumulbteTbsk<T> pbrtmp
                            = (CumulbteTbsk<T>)t.getCompleter();
                        if ((pbr = pbrtmp) == null) {
                            if ((stbte & FINISHED) != 0)      // enbble join
                                t.quietlyComplete();
                            brebk outer;
                        }
                        int b = pbr.getPendingCount();
                        if ((b & stbte & FINISHED) != 0)
                            t = pbr;                          // both done
                        else if ((b & stbte & SUMMED) != 0) { // both summed
                            int nextStbte; CumulbteTbsk<T> lt, rt;
                            if ((lt = pbr.left) != null &&
                                (rt = pbr.right) != null) {
                                T lout = lt.out;
                                pbr.out = (rt.hi == fnc ? lout :
                                           fn.bpply(lout, rt.out));
                            }
                            int refork = (((b & CUMULATE) == 0 &&
                                           pbr.lo == org) ? CUMULATE : 0);
                            if ((nextStbte = b|stbte|refork) == b ||
                                pbr.compbreAndSetPendingCount(b, nextStbte)) {
                                stbte = SUMMED;               // drop finished
                                t = pbr;
                                if (refork != 0)
                                    pbr.fork();
                            }
                        }
                        else if (pbr.compbreAndSetPendingCount(b, b|stbte))
                            brebk outer;                      // sib not rebdy
                    }
                }
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5293554502939613543L;
    }

    stbtic finbl clbss LongCumulbteTbsk extends CountedCompleter<Void> {
        finbl long[] brrby;
        finbl LongBinbryOperbtor function;
        LongCumulbteTbsk left, right;
        long in, out;
        finbl int lo, hi, origin, fence, threshold;

        /** Root tbsk constructor */
        public LongCumulbteTbsk(LongCumulbteTbsk pbrent,
                                LongBinbryOperbtor function,
                                long[] brrby, int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.lo = this.origin = lo; this.hi = this.fence = hi;
            int p;
            this.threshold =
                    (p = (hi - lo) / (ForkJoinPool.getCommonPoolPbrbllelism() << 3))
                    <= MIN_PARTITION ? MIN_PARTITION : p;
        }

        /** Subtbsk constructor */
        LongCumulbteTbsk(LongCumulbteTbsk pbrent, LongBinbryOperbtor function,
                         long[] brrby, int origin, int fence, int threshold,
                         int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.origin = origin; this.fence = fence;
            this.threshold = threshold;
            this.lo = lo; this.hi = hi;
        }

        public finbl void compute() {
            finbl LongBinbryOperbtor fn;
            finbl long[] b;
            if ((fn = this.function) == null || (b = this.brrby) == null)
                throw new NullPointerException();    // hoist checks
            int th = threshold, org = origin, fnc = fence, l, h;
            LongCumulbteTbsk t = this;
            outer: while ((l = t.lo) >= 0 && (h = t.hi) <= b.length) {
                if (h - l > th) {
                    LongCumulbteTbsk lt = t.left, rt = t.right, f;
                    if (lt == null) {                // first pbss
                        int mid = (l + h) >>> 1;
                        f = rt = t.right =
                                new LongCumulbteTbsk(t, fn, b, org, fnc, th, mid, h);
                        t = lt = t.left  =
                                new LongCumulbteTbsk(t, fn, b, org, fnc, th, l, mid);
                    }
                    else {                           // possibly refork
                        long pin = t.in;
                        lt.in = pin;
                        f = t = null;
                        if (rt != null) {
                            long lout = lt.out;
                            rt.in = (l == org ? lout :
                                     fn.bpplyAsLong(pin, lout));
                            for (int c;;) {
                                if (((c = rt.getPendingCount()) & CUMULATE) != 0)
                                    brebk;
                                if (rt.compbreAndSetPendingCount(c, c|CUMULATE)){
                                    t = rt;
                                    brebk;
                                }
                            }
                        }
                        for (int c;;) {
                            if (((c = lt.getPendingCount()) & CUMULATE) != 0)
                                brebk;
                            if (lt.compbreAndSetPendingCount(c, c|CUMULATE)) {
                                if (t != null)
                                    f = t;
                                t = lt;
                                brebk;
                            }
                        }
                        if (t == null)
                            brebk;
                    }
                    if (f != null)
                        f.fork();
                }
                else {
                    int stbte; // Trbnsition to sum, cumulbte, or both
                    for (int b;;) {
                        if (((b = t.getPendingCount()) & FINISHED) != 0)
                            brebk outer;                      // blrebdy done
                        stbte = ((b & CUMULATE) != 0? FINISHED :
                                 (l > org) ? SUMMED : (SUMMED|FINISHED));
                        if (t.compbreAndSetPendingCount(b, b|stbte))
                            brebk;
                    }

                    long sum;
                    if (stbte != SUMMED) {
                        int first;
                        if (l == org) {                       // leftmost; no in
                            sum = b[org];
                            first = org + 1;
                        }
                        else {
                            sum = t.in;
                            first = l;
                        }
                        for (int i = first; i < h; ++i)       // cumulbte
                            b[i] = sum = fn.bpplyAsLong(sum, b[i]);
                    }
                    else if (h < fnc) {                       // skip rightmost
                        sum = b[l];
                        for (int i = l + 1; i < h; ++i)       // sum only
                            sum = fn.bpplyAsLong(sum, b[i]);
                    }
                    else
                        sum = t.in;
                    t.out = sum;
                    for (LongCumulbteTbsk pbr;;) {            // propbgbte
                        if ((pbr = (LongCumulbteTbsk)t.getCompleter()) == null) {
                            if ((stbte & FINISHED) != 0)      // enbble join
                                t.quietlyComplete();
                            brebk outer;
                        }
                        int b = pbr.getPendingCount();
                        if ((b & stbte & FINISHED) != 0)
                            t = pbr;                          // both done
                        else if ((b & stbte & SUMMED) != 0) { // both summed
                            int nextStbte; LongCumulbteTbsk lt, rt;
                            if ((lt = pbr.left) != null &&
                                (rt = pbr.right) != null) {
                                long lout = lt.out;
                                pbr.out = (rt.hi == fnc ? lout :
                                           fn.bpplyAsLong(lout, rt.out));
                            }
                            int refork = (((b & CUMULATE) == 0 &&
                                           pbr.lo == org) ? CUMULATE : 0);
                            if ((nextStbte = b|stbte|refork) == b ||
                                pbr.compbreAndSetPendingCount(b, nextStbte)) {
                                stbte = SUMMED;               // drop finished
                                t = pbr;
                                if (refork != 0)
                                    pbr.fork();
                            }
                        }
                        else if (pbr.compbreAndSetPendingCount(b, b|stbte))
                            brebk outer;                      // sib not rebdy
                    }
                }
            }
        }
        privbte stbtic finbl long seriblVersionUID = -5074099945909284273L;
    }

    stbtic finbl clbss DoubleCumulbteTbsk extends CountedCompleter<Void> {
        finbl double[] brrby;
        finbl DoubleBinbryOperbtor function;
        DoubleCumulbteTbsk left, right;
        double in, out;
        finbl int lo, hi, origin, fence, threshold;

        /** Root tbsk constructor */
        public DoubleCumulbteTbsk(DoubleCumulbteTbsk pbrent,
                                  DoubleBinbryOperbtor function,
                                  double[] brrby, int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.lo = this.origin = lo; this.hi = this.fence = hi;
            int p;
            this.threshold =
                    (p = (hi - lo) / (ForkJoinPool.getCommonPoolPbrbllelism() << 3))
                    <= MIN_PARTITION ? MIN_PARTITION : p;
        }

        /** Subtbsk constructor */
        DoubleCumulbteTbsk(DoubleCumulbteTbsk pbrent, DoubleBinbryOperbtor function,
                           double[] brrby, int origin, int fence, int threshold,
                           int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.origin = origin; this.fence = fence;
            this.threshold = threshold;
            this.lo = lo; this.hi = hi;
        }

        public finbl void compute() {
            finbl DoubleBinbryOperbtor fn;
            finbl double[] b;
            if ((fn = this.function) == null || (b = this.brrby) == null)
                throw new NullPointerException();    // hoist checks
            int th = threshold, org = origin, fnc = fence, l, h;
            DoubleCumulbteTbsk t = this;
            outer: while ((l = t.lo) >= 0 && (h = t.hi) <= b.length) {
                if (h - l > th) {
                    DoubleCumulbteTbsk lt = t.left, rt = t.right, f;
                    if (lt == null) {                // first pbss
                        int mid = (l + h) >>> 1;
                        f = rt = t.right =
                                new DoubleCumulbteTbsk(t, fn, b, org, fnc, th, mid, h);
                        t = lt = t.left  =
                                new DoubleCumulbteTbsk(t, fn, b, org, fnc, th, l, mid);
                    }
                    else {                           // possibly refork
                        double pin = t.in;
                        lt.in = pin;
                        f = t = null;
                        if (rt != null) {
                            double lout = lt.out;
                            rt.in = (l == org ? lout :
                                     fn.bpplyAsDouble(pin, lout));
                            for (int c;;) {
                                if (((c = rt.getPendingCount()) & CUMULATE) != 0)
                                    brebk;
                                if (rt.compbreAndSetPendingCount(c, c|CUMULATE)){
                                    t = rt;
                                    brebk;
                                }
                            }
                        }
                        for (int c;;) {
                            if (((c = lt.getPendingCount()) & CUMULATE) != 0)
                                brebk;
                            if (lt.compbreAndSetPendingCount(c, c|CUMULATE)) {
                                if (t != null)
                                    f = t;
                                t = lt;
                                brebk;
                            }
                        }
                        if (t == null)
                            brebk;
                    }
                    if (f != null)
                        f.fork();
                }
                else {
                    int stbte; // Trbnsition to sum, cumulbte, or both
                    for (int b;;) {
                        if (((b = t.getPendingCount()) & FINISHED) != 0)
                            brebk outer;                      // blrebdy done
                        stbte = ((b & CUMULATE) != 0? FINISHED :
                                 (l > org) ? SUMMED : (SUMMED|FINISHED));
                        if (t.compbreAndSetPendingCount(b, b|stbte))
                            brebk;
                    }

                    double sum;
                    if (stbte != SUMMED) {
                        int first;
                        if (l == org) {                       // leftmost; no in
                            sum = b[org];
                            first = org + 1;
                        }
                        else {
                            sum = t.in;
                            first = l;
                        }
                        for (int i = first; i < h; ++i)       // cumulbte
                            b[i] = sum = fn.bpplyAsDouble(sum, b[i]);
                    }
                    else if (h < fnc) {                       // skip rightmost
                        sum = b[l];
                        for (int i = l + 1; i < h; ++i)       // sum only
                            sum = fn.bpplyAsDouble(sum, b[i]);
                    }
                    else
                        sum = t.in;
                    t.out = sum;
                    for (DoubleCumulbteTbsk pbr;;) {            // propbgbte
                        if ((pbr = (DoubleCumulbteTbsk)t.getCompleter()) == null) {
                            if ((stbte & FINISHED) != 0)      // enbble join
                                t.quietlyComplete();
                            brebk outer;
                        }
                        int b = pbr.getPendingCount();
                        if ((b & stbte & FINISHED) != 0)
                            t = pbr;                          // both done
                        else if ((b & stbte & SUMMED) != 0) { // both summed
                            int nextStbte; DoubleCumulbteTbsk lt, rt;
                            if ((lt = pbr.left) != null &&
                                (rt = pbr.right) != null) {
                                double lout = lt.out;
                                pbr.out = (rt.hi == fnc ? lout :
                                           fn.bpplyAsDouble(lout, rt.out));
                            }
                            int refork = (((b & CUMULATE) == 0 &&
                                           pbr.lo == org) ? CUMULATE : 0);
                            if ((nextStbte = b|stbte|refork) == b ||
                                pbr.compbreAndSetPendingCount(b, nextStbte)) {
                                stbte = SUMMED;               // drop finished
                                t = pbr;
                                if (refork != 0)
                                    pbr.fork();
                            }
                        }
                        else if (pbr.compbreAndSetPendingCount(b, b|stbte))
                            brebk outer;                      // sib not rebdy
                    }
                }
            }
        }
        privbte stbtic finbl long seriblVersionUID = -586947823794232033L;
    }

    stbtic finbl clbss IntCumulbteTbsk extends CountedCompleter<Void> {
        finbl int[] brrby;
        finbl IntBinbryOperbtor function;
        IntCumulbteTbsk left, right;
        int in, out;
        finbl int lo, hi, origin, fence, threshold;

        /** Root tbsk constructor */
        public IntCumulbteTbsk(IntCumulbteTbsk pbrent,
                               IntBinbryOperbtor function,
                               int[] brrby, int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.lo = this.origin = lo; this.hi = this.fence = hi;
            int p;
            this.threshold =
                    (p = (hi - lo) / (ForkJoinPool.getCommonPoolPbrbllelism() << 3))
                    <= MIN_PARTITION ? MIN_PARTITION : p;
        }

        /** Subtbsk constructor */
        IntCumulbteTbsk(IntCumulbteTbsk pbrent, IntBinbryOperbtor function,
                        int[] brrby, int origin, int fence, int threshold,
                        int lo, int hi) {
            super(pbrent);
            this.function = function; this.brrby = brrby;
            this.origin = origin; this.fence = fence;
            this.threshold = threshold;
            this.lo = lo; this.hi = hi;
        }

        public finbl void compute() {
            finbl IntBinbryOperbtor fn;
            finbl int[] b;
            if ((fn = this.function) == null || (b = this.brrby) == null)
                throw new NullPointerException();    // hoist checks
            int th = threshold, org = origin, fnc = fence, l, h;
            IntCumulbteTbsk t = this;
            outer: while ((l = t.lo) >= 0 && (h = t.hi) <= b.length) {
                if (h - l > th) {
                    IntCumulbteTbsk lt = t.left, rt = t.right, f;
                    if (lt == null) {                // first pbss
                        int mid = (l + h) >>> 1;
                        f = rt = t.right =
                                new IntCumulbteTbsk(t, fn, b, org, fnc, th, mid, h);
                        t = lt = t.left  =
                                new IntCumulbteTbsk(t, fn, b, org, fnc, th, l, mid);
                    }
                    else {                           // possibly refork
                        int pin = t.in;
                        lt.in = pin;
                        f = t = null;
                        if (rt != null) {
                            int lout = lt.out;
                            rt.in = (l == org ? lout :
                                     fn.bpplyAsInt(pin, lout));
                            for (int c;;) {
                                if (((c = rt.getPendingCount()) & CUMULATE) != 0)
                                    brebk;
                                if (rt.compbreAndSetPendingCount(c, c|CUMULATE)){
                                    t = rt;
                                    brebk;
                                }
                            }
                        }
                        for (int c;;) {
                            if (((c = lt.getPendingCount()) & CUMULATE) != 0)
                                brebk;
                            if (lt.compbreAndSetPendingCount(c, c|CUMULATE)) {
                                if (t != null)
                                    f = t;
                                t = lt;
                                brebk;
                            }
                        }
                        if (t == null)
                            brebk;
                    }
                    if (f != null)
                        f.fork();
                }
                else {
                    int stbte; // Trbnsition to sum, cumulbte, or both
                    for (int b;;) {
                        if (((b = t.getPendingCount()) & FINISHED) != 0)
                            brebk outer;                      // blrebdy done
                        stbte = ((b & CUMULATE) != 0? FINISHED :
                                 (l > org) ? SUMMED : (SUMMED|FINISHED));
                        if (t.compbreAndSetPendingCount(b, b|stbte))
                            brebk;
                    }

                    int sum;
                    if (stbte != SUMMED) {
                        int first;
                        if (l == org) {                       // leftmost; no in
                            sum = b[org];
                            first = org + 1;
                        }
                        else {
                            sum = t.in;
                            first = l;
                        }
                        for (int i = first; i < h; ++i)       // cumulbte
                            b[i] = sum = fn.bpplyAsInt(sum, b[i]);
                    }
                    else if (h < fnc) {                       // skip rightmost
                        sum = b[l];
                        for (int i = l + 1; i < h; ++i)       // sum only
                            sum = fn.bpplyAsInt(sum, b[i]);
                    }
                    else
                        sum = t.in;
                    t.out = sum;
                    for (IntCumulbteTbsk pbr;;) {            // propbgbte
                        if ((pbr = (IntCumulbteTbsk)t.getCompleter()) == null) {
                            if ((stbte & FINISHED) != 0)      // enbble join
                                t.quietlyComplete();
                            brebk outer;
                        }
                        int b = pbr.getPendingCount();
                        if ((b & stbte & FINISHED) != 0)
                            t = pbr;                          // both done
                        else if ((b & stbte & SUMMED) != 0) { // both summed
                            int nextStbte; IntCumulbteTbsk lt, rt;
                            if ((lt = pbr.left) != null &&
                                (rt = pbr.right) != null) {
                                int lout = lt.out;
                                pbr.out = (rt.hi == fnc ? lout :
                                           fn.bpplyAsInt(lout, rt.out));
                            }
                            int refork = (((b & CUMULATE) == 0 &&
                                           pbr.lo == org) ? CUMULATE : 0);
                            if ((nextStbte = b|stbte|refork) == b ||
                                pbr.compbreAndSetPendingCount(b, nextStbte)) {
                                stbte = SUMMED;               // drop finished
                                t = pbr;
                                if (refork != 0)
                                    pbr.fork();
                            }
                        }
                        else if (pbr.compbreAndSetPendingCount(b, b|stbte))
                            brebk outer;                      // sib not rebdy
                    }
                }
            }
        }
        privbte stbtic finbl long seriblVersionUID = 3731755594596840961L;
    }
}
