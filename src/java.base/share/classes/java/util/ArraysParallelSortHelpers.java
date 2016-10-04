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

import jbvb.util.concurrent.RecursiveAction;
import jbvb.util.concurrent.CountedCompleter;

/**
 * Helper utilities for the pbrbllel sort methods in Arrbys.pbrbllelSort.
 *
 * For ebch primitive type, plus Object, we define b stbtic clbss to
 * contbin the Sorter bnd Merger implementbtions for thbt type:
 *
 * Sorter clbsses bbsed mbinly on CilkSort
 * <A href="http://supertech.lcs.mit.edu/cilk/"> Cilk</A>:
 * Bbsic blgorithm:
 * if brrby size is smbll, just use b sequentibl quicksort (vib Arrbys.sort)
 *         Otherwise:
 *         1. Brebk brrby in hblf.
 *         2. For ebch hblf,
 *             b. brebk the hblf in hblf (i.e., qubrters),
 *             b. sort the qubrters
 *             c. merge them together
 *         3. merge together the two hblves.
 *
 * One rebson for splitting in qubrters is thbt this gubrbntees thbt
 * the finbl sort is in the mbin brrby, not the workspbce brrby.
 * (workspbce bnd mbin swbp roles on ebch subsort step.)  Lebf-level
 * sorts use the bssocibted sequentibl sort.
 *
 * Merger clbsses perform merging for Sorter.  They bre structured
 * such thbt if the underlying sort is stbble (bs is true for
 * TimSort), then so is the full sort.  If big enough, they split the
 * lbrgest of the two pbrtitions in hblf, find the grebtest point in
 * smbller pbrtition less thbn the beginning of the second hblf of
 * lbrger vib binbry sebrch; bnd then merge in pbrbllel the two
 * pbrtitions.  In pbrt to ensure tbsks bre triggered in
 * stbbility-preserving order, the current CountedCompleter design
 * requires some little tbsks to serve bs plbce holders for triggering
 * completion tbsks.  These clbsses (EmptyCompleter bnd Relby) don't
 * need to keep trbck of the brrbys, bnd bre never themselves forked,
 * so don't hold bny tbsk stbte.
 *
 * The primitive clbss versions (FJByte... FJDouble) bre
 * identicbl to ebch other except for type declbrbtions.
 *
 * The bbse sequentibl sorts rely on non-public versions of TimSort,
 * CompbrbbleTimSort, bnd DublPivotQuicksort sort methods thbt bccept
 * temp workspbce brrby slices thbt we will hbve blrebdy bllocbted, so
 * bvoids redundbnt bllocbtion. (Except for DublPivotQuicksort byte[]
 * sort, thbt does not ever use b workspbce brrby.)
 */
/*pbckbge*/ clbss ArrbysPbrbllelSortHelpers {

    /*
     * Style note: The tbsk clbsses hbve b lot of pbrbmeters, thbt bre
     * stored bs tbsk fields bnd copied to locbl vbribbles bnd used in
     * compute() methods, We pbck these into bs few lines bs possible,
     * bnd hoist consistency checks bmong them before mbin loops, to
     * reduce distrbction.
     */

    /**
     * A plbceholder tbsk for Sorters, used for the lowest
     * qubrtile tbsk, thbt does not need to mbintbin brrby stbte.
     */
    stbtic finbl clbss EmptyCompleter extends CountedCompleter<Void> {
        stbtic finbl long seriblVersionUID = 2446542900576103244L;
        EmptyCompleter(CountedCompleter<?> p) { super(p); }
        public finbl void compute() { }
    }

    /**
     * A trigger for secondbry merge of two merges
     */
    stbtic finbl clbss Relby extends CountedCompleter<Void> {
        stbtic finbl long seriblVersionUID = 2446542900576103244L;
        finbl CountedCompleter<?> tbsk;
        Relby(CountedCompleter<?> tbsk) {
            super(null, 1);
            this.tbsk = tbsk;
        }
        public finbl void compute() { }
        public finbl void onCompletion(CountedCompleter<?> t) {
            tbsk.compute();
        }
    }

    /** Object + Compbrbtor support clbss */
    stbtic finbl clbss FJObject {
        stbtic finbl clbss Sorter<T> extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl T[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Compbrbtor<? super T> compbrbtor;
            Sorter(CountedCompleter<?> pbr, T[] b, T[] w, int bbse, int size,
                   int wbbse, int grbn,
                   Compbrbtor<? super T> compbrbtor) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
                this.compbrbtor = compbrbtor;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                Compbrbtor<? super T> c = this.compbrbtor;
                T[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger<>(s, w, b, wb, h,
                                                      wb+h, n-h, b, g, c));
                    Relby rc = new Relby(new Merger<>(fc, b, w, b+h, q,
                                                      b+u, n-u, wb+h, g, c));
                    new Sorter<>(rc, b, w, b+u, n-u, wb+u, g, c).fork();
                    new Sorter<>(rc, b, w, b+h, q, wb+h, g, c).fork();;
                    Relby bc = new Relby(new Merger<>(fc, b, w, b, q,
                                                      b+q, h-q, wb, g, c));
                    new Sorter<>(bc, b, w, b+q, h-q, wb+q, g, c).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                TimSort.sort(b, b, b + n, c, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger<T> extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl T[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Compbrbtor<? super T> compbrbtor;
            Merger(CountedCompleter<?> pbr, T[] b, T[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn,
                   Compbrbtor<? super T> compbrbtor) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
                this.compbrbtor = compbrbtor;
            }

            public finbl void compute() {
                Compbrbtor<? super T> c = this.compbrbtor;
                T[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0 ||
                    c == null)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        T split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (c.compbre(split, b[rm + rb]) <= 0)
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        T split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (c.compbre(split, b[lm + lb]) <= 0)
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger<T> m = new Merger<>(this, b, w, lb + lh, ln - lh,
                                               rb + rh, rn - rh,
                                               k + lh + rh, g, c);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    T t, bl, br;
                    if (c.compbre((bl = b[lb]), (br = b[rb])) <= 0) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);

                tryComplete();
            }

        }
    } // FJObject

    /** byte support clbss */
    stbtic finbl clbss FJByte {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl byte[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, byte[] b, byte[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                byte[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl byte[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, byte[] b, byte[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                byte[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        byte split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        byte split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    byte t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJByte

    /** chbr support clbss */
    stbtic finbl clbss FJChbr {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl chbr[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, chbr[] b, chbr[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                chbr[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl chbr[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, chbr[] b, chbr[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                chbr[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        chbr split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        chbr split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    chbr t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJChbr

    /** short support clbss */
    stbtic finbl clbss FJShort {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl short[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, short[] b, short[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                short[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl short[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, short[] b, short[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                short[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        short split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        short split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    short t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJShort

    /** int support clbss */
    stbtic finbl clbss FJInt {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl int[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, int[] b, int[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                int[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl int[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, int[] b, int[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                int[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        int split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        int split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    int t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJInt

    /** long support clbss */
    stbtic finbl clbss FJLong {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl long[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, long[] b, long[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                long[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl long[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, long[] b, long[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                long[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        long split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        long split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    long t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJLong

    /** flobt support clbss */
    stbtic finbl clbss FJFlobt {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl flobt[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, flobt[] b, flobt[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                flobt[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl flobt[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, flobt[] b, flobt[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                flobt[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        flobt split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        flobt split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    flobt t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJFlobt

    /** double support clbss */
    stbtic finbl clbss FJDouble {
        stbtic finbl clbss Sorter extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl double[] b, w;
            finbl int bbse, size, wbbse, grbn;
            Sorter(CountedCompleter<?> pbr, double[] b, double[] w, int bbse,
                   int size, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w; this.bbse = bbse; this.size = size;
                this.wbbse = wbbse; this.grbn = grbn;
            }
            public finbl void compute() {
                CountedCompleter<?> s = this;
                double[] b = this.b, w = this.w; // locblize bll pbrbms
                int b = this.bbse, n = this.size, wb = this.wbbse, g = this.grbn;
                while (n > g) {
                    int h = n >>> 1, q = h >>> 1, u = h + q; // qubrtiles
                    Relby fc = new Relby(new Merger(s, w, b, wb, h,
                                                    wb+h, n-h, b, g));
                    Relby rc = new Relby(new Merger(fc, b, w, b+h, q,
                                                    b+u, n-u, wb+h, g));
                    new Sorter(rc, b, w, b+u, n-u, wb+u, g).fork();
                    new Sorter(rc, b, w, b+h, q, wb+h, g).fork();;
                    Relby bc = new Relby(new Merger(fc, b, w, b, q,
                                                    b+q, h-q, wb, g));
                    new Sorter(bc, b, w, b+q, h-q, wb+q, g).fork();
                    s = new EmptyCompleter(bc);
                    n = q;
                }
                DublPivotQuicksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplete();
            }
        }

        stbtic finbl clbss Merger extends CountedCompleter<Void> {
            stbtic finbl long seriblVersionUID = 2446542900576103244L;
            finbl double[] b, w; // mbin bnd workspbce brrbys
            finbl int lbbse, lsize, rbbse, rsize, wbbse, grbn;
            Merger(CountedCompleter<?> pbr, double[] b, double[] w,
                   int lbbse, int lsize, int rbbse,
                   int rsize, int wbbse, int grbn) {
                super(pbr);
                this.b = b; this.w = w;
                this.lbbse = lbbse; this.lsize = lsize;
                this.rbbse = rbbse; this.rsize = rsize;
                this.wbbse = wbbse; this.grbn = grbn;
            }

            public finbl void compute() {
                double[] b = this.b, w = this.w; // locblize bll pbrbms
                int lb = this.lbbse, ln = this.lsize, rb = this.rbbse,
                    rn = this.rsize, k = this.wbbse, g = this.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    throw new IllegblStbteException(); // hoist checks
                for (int lh, rh;;) {  // split lbrger, find point in smbller
                    if (ln >= rn) {
                        if (ln <= g)
                            brebk;
                        rh = rn;
                        double split = b[(lh = ln >>> 1) + lb];
                        for (int lo = 0; lo < rh; ) {
                            int rm = (lo + rh) >>> 1;
                            if (split <= b[rm + rb])
                                rh = rm;
                            else
                                lo = rm + 1;
                        }
                    }
                    else {
                        if (rn <= g)
                            brebk;
                        lh = ln;
                        double split = b[(rh = rn >>> 1) + rb];
                        for (int lo = 0; lo < lh; ) {
                            int lm = (lo + lh) >>> 1;
                            if (split <= b[lm + lb])
                                lh = lm;
                            else
                                lo = lm + 1;
                        }
                    }
                    Merger m = new Merger(this, b, w, lb + lh, ln - lh,
                                          rb + rh, rn - rh,
                                          k + lh + rh, g);
                    rn = rh;
                    ln = lh;
                    bddToPendingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // index bounds
                while (lb < lf && rb < rf) {
                    double t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    else {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    System.brrbycopy(b, rb, w, k, rf - rb);
                else if (lb < lf)
                    System.brrbycopy(b, lb, w, k, lf - lb);
                tryComplete();
            }
        }
    } // FJDouble

}
