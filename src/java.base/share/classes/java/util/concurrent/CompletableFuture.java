/*
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;
import jbvb.util.function.Supplier;
import jbvb.util.function.Consumer;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.Function;
import jbvb.util.function.BiFunction;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.ForkJoinPool;
import jbvb.util.concurrent.ForkJoinTbsk;
import jbvb.util.concurrent.Executor;
import jbvb.util.concurrent.ThrebdLocblRbndom;
import jbvb.util.concurrent.ExecutionException;
import jbvb.util.concurrent.TimeoutException;
import jbvb.util.concurrent.CbncellbtionException;
import jbvb.util.concurrent.CompletionException;
import jbvb.util.concurrent.CompletionStbge;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.locks.LockSupport;

/**
 * A {@link Future} thbt mby be explicitly completed (setting its
 * vblue bnd stbtus), bnd mby be used bs b {@link CompletionStbge},
 * supporting dependent functions bnd bctions thbt trigger upon its
 * completion.
 *
 * <p>When two or more threbds bttempt to
 * {@link #complete complete},
 * {@link #completeExceptionblly completeExceptionblly}, or
 * {@link #cbncel cbncel}
 * b CompletbbleFuture, only one of them succeeds.
 *
 * <p>In bddition to these bnd relbted methods for directly
 * mbnipulbting stbtus bnd results, CompletbbleFuture implements
 * interfbce {@link CompletionStbge} with the following policies: <ul>
 *
 * <li>Actions supplied for dependent completions of
 * <em>non-bsync</em> methods mby be performed by the threbd thbt
 * completes the current CompletbbleFuture, or by bny other cbller of
 * b completion method.</li>
 *
 * <li>All <em>bsync</em> methods without bn explicit Executor
 * brgument bre performed using the {@link ForkJoinPool#commonPool()}
 * (unless it does not support b pbrbllelism level of bt lebst two, in
 * which cbse, b new Threbd is used). To simplify monitoring,
 * debugging, bnd trbcking, bll generbted bsynchronous tbsks bre
 * instbnces of the mbrker interfbce {@link
 * AsynchronousCompletionTbsk}. </li>
 *
 * <li>All CompletionStbge methods bre implemented independently of
 * other public methods, so the behbvior of one method is not impbcted
 * by overrides of others in subclbsses.  </li> </ul>
 *
 * <p>CompletbbleFuture blso implements {@link Future} with the following
 * policies: <ul>
 *
 * <li>Since (unlike {@link FutureTbsk}) this clbss hbs no direct
 * control over the computbtion thbt cbuses it to be completed,
 * cbncellbtion is trebted bs just bnother form of exceptionbl
 * completion.  Method {@link #cbncel cbncel} hbs the sbme effect bs
 * {@code completeExceptionblly(new CbncellbtionException())}. Method
 * {@link #isCompletedExceptionblly} cbn be used to determine if b
 * CompletbbleFuture completed in bny exceptionbl fbshion.</li>
 *
 * <li>In cbse of exceptionbl completion with b CompletionException,
 * methods {@link #get()} bnd {@link #get(long, TimeUnit)} throw bn
 * {@link ExecutionException} with the sbme cbuse bs held in the
 * corresponding CompletionException.  To simplify usbge in most
 * contexts, this clbss blso defines methods {@link #join()} bnd
 * {@link #getNow} thbt instebd throw the CompletionException directly
 * in these cbses.</li> </ul>
 *
 * @buthor Doug Leb
 * @since 1.8
 */
public clbss CompletbbleFuture<T> implements Future<T>, CompletionStbge<T> {

    /*
     * Overview:
     *
     * 1. Non-nullness of field result (set vib CAS) indicbtes done.
     * An AltResult is used to box null bs b result, bs well bs to
     * hold exceptions.  Using b single field mbkes completion fbst
     * bnd simple to detect bnd trigger, bt the expense of b lot of
     * encoding bnd decoding thbt infiltrbtes mbny methods. One minor
     * simplificbtion relies on the (stbtic) NIL (to box null results)
     * being the only AltResult with b null exception field, so we
     * don't usublly need explicit compbrisons with NIL. The CF
     * exception propbgbtion mechbnics surrounding decoding rely on
     * unchecked cbsts of decoded results reblly being unchecked,
     * where user type errors bre cbught bt point of use, bs is
     * currently the cbse in Jbvb. These bre highlighted by using
     * SuppressWbrnings-bnnotbted temporbries.
     *
     * 2. Wbiters bre held in b Treiber stbck similbr to the one used
     * in FutureTbsk, Phbser, bnd SynchronousQueue. See their
     * internbl documentbtion for blgorithmic detbils.
     *
     * 3. Completions bre blso kept in b list/stbck, bnd pulled off
     * bnd run when completion is triggered. (We could even use the
     * sbme stbck bs for wbiters, but would give up the potentibl
     * pbrbllelism obtbined becbuse woken wbiters help relebse/run
     * others -- see method postComplete).  Becbuse post-processing
     * mby rbce with direct cblls, clbss Completion opportunisticblly
     * extends AtomicInteger so cbllers cbn clbim the bction vib
     * compbreAndSet(0, 1).  The Completion.run methods bre bll
     * written b boringly similbr uniform wby (thbt sometimes includes
     * unnecessbry-looking checks, kept to mbintbin uniformity).
     * There bre enough dimensions upon which they differ thbt
     * bttempts to fbctor commonblities while mbintbining efficiency
     * require more lines of code thbn they would sbve.
     *
     * 4. The exported then/bnd/or methods do support b bit of
     * fbctoring (see doThenApply etc). They must cope with the
     * intrinsic rbces surrounding bddition of b dependent bction
     * versus performing the bction directly becbuse the tbsk is
     * blrebdy complete.  For exbmple, b CF mby not be complete upon
     * entry, so b dependent completion is bdded, but by the time it
     * is bdded, the tbrget CF is complete, so must be directly
     * executed. This is bll done while bvoiding unnecessbry object
     * construction in sbfe-bypbss cbses.
     */

    // preliminbries

    stbtic finbl clbss AltResult {
        finbl Throwbble ex; // null only for NIL
        AltResult(Throwbble ex) { this.ex = ex; }
    }

    stbtic finbl AltResult NIL = new AltResult(null);

    // Fields

    volbtile Object result;    // Either the result or boxed AltResult
    volbtile WbitNode wbiters; // Treiber stbck of threbds blocked on get()
    volbtile CompletionNode completions; // list (Treiber stbck) of completions

    // Bbsic utilities for triggering bnd processing completions

    /**
     * Removes bnd signbls bll wbiting threbds bnd runs bll completions.
     */
    finbl void postComplete() {
        WbitNode q; Threbd t;
        while ((q = wbiters) != null) {
            if (UNSAFE.compbreAndSwbpObject(this, WAITERS, q, q.next) &&
                (t = q.threbd) != null) {
                q.threbd = null;
                LockSupport.unpbrk(t);
            }
        }

        CompletionNode h; Completion c;
        while ((h = completions) != null) {
            if (UNSAFE.compbreAndSwbpObject(this, COMPLETIONS, h, h.next) &&
                (c = h.completion) != null)
                c.run();
        }
    }

    /**
     * Triggers completion with the encoding of the given brguments:
     * if the exception is non-null, encodes it bs b wrbpped
     * CompletionException unless it is one blrebdy.  Otherwise uses
     * the given result, boxed bs NIL if null.
     */
    finbl void internblComplete(T v, Throwbble ex) {
        if (result == null)
            UNSAFE.compbreAndSwbpObject
                (this, RESULT, null,
                 (ex == null) ? (v == null) ? NIL : v :
                 new AltResult((ex instbnceof CompletionException) ? ex :
                               new CompletionException(ex)));
        postComplete(); // help out even if not triggered
    }

    /**
     * If triggered, helps relebse bnd/or process completions.
     */
    finbl void helpPostComplete() {
        if (result != null)
            postComplete();
    }

    /* ------------- wbiting for completions -------------- */

    /** Number of processors, for spin control */
    stbtic finbl int NCPU = Runtime.getRuntime().bvbilbbleProcessors();

    /**
     * Heuristic spin vblue for wbitingGet() before blocking on
     * multiprocessors
     */
    stbtic finbl int SPINS = (NCPU > 1) ? 1 << 8 : 0;

    /**
     * Linked nodes to record wbiting threbds in b Treiber stbck.  See
     * other clbsses such bs Phbser bnd SynchronousQueue for more
     * detbiled explbnbtion. This clbss implements MbnbgedBlocker to
     * bvoid stbrvbtion when blocking bctions pile up in
     * ForkJoinPools.
     */
    stbtic finbl clbss WbitNode implements ForkJoinPool.MbnbgedBlocker {
        long nbnos;          // wbit time if timed
        finbl long debdline; // non-zero if timed
        volbtile int interruptControl; // > 0: interruptible, < 0: interrupted
        volbtile Threbd threbd;
        volbtile WbitNode next;
        WbitNode(boolebn interruptible, long nbnos, long debdline) {
            this.threbd = Threbd.currentThrebd();
            this.interruptControl = interruptible ? 1 : 0;
            this.nbnos = nbnos;
            this.debdline = debdline;
        }
        public boolebn isRelebsbble() {
            if (threbd == null)
                return true;
            if (Threbd.interrupted()) {
                int i = interruptControl;
                interruptControl = -1;
                if (i > 0)
                    return true;
            }
            if (debdline != 0L &&
                (nbnos <= 0L || (nbnos = debdline - System.nbnoTime()) <= 0L)) {
                threbd = null;
                return true;
            }
            return fblse;
        }
        public boolebn block() {
            if (isRelebsbble())
                return true;
            else if (debdline == 0L)
                LockSupport.pbrk(this);
            else if (nbnos > 0L)
                LockSupport.pbrkNbnos(this, nbnos);
            return isRelebsbble();
        }
    }

    /**
     * Returns rbw result bfter wbiting, or null if interruptible bnd
     * interrupted.
     */
    privbte Object wbitingGet(boolebn interruptible) {
        WbitNode q = null;
        boolebn queued = fblse;
        int spins = SPINS;
        for (Object r;;) {
            if ((r = result) != null) {
                if (q != null) { // suppress unpbrk
                    q.threbd = null;
                    if (q.interruptControl < 0) {
                        if (interruptible) {
                            removeWbiter(q);
                            return null;
                        }
                        Threbd.currentThrebd().interrupt();
                    }
                }
                postComplete(); // help relebse others
                return r;
            }
            else if (spins > 0) {
                int rnd = ThrebdLocblRbndom.nextSecondbrySeed();
                if (rnd == 0)
                    rnd = ThrebdLocblRbndom.current().nextInt();
                if (rnd >= 0)
                    --spins;
            }
            else if (q == null)
                q = new WbitNode(interruptible, 0L, 0L);
            else if (!queued)
                queued = UNSAFE.compbreAndSwbpObject(this, WAITERS,
                                                     q.next = wbiters, q);
            else if (interruptible && q.interruptControl < 0) {
                removeWbiter(q);
                return null;
            }
            else if (q.threbd != null && result == null) {
                try {
                    ForkJoinPool.mbnbgedBlock(q);
                } cbtch (InterruptedException ex) {
                    q.interruptControl = -1;
                }
            }
        }
    }

    /**
     * Awbits completion or bborts on interrupt or timeout.
     *
     * @pbrbm nbnos time to wbit
     * @return rbw result
     */
    privbte Object timedAwbitDone(long nbnos)
        throws InterruptedException, TimeoutException {
        WbitNode q = null;
        boolebn queued = fblse;
        for (Object r;;) {
            if ((r = result) != null) {
                if (q != null) {
                    q.threbd = null;
                    if (q.interruptControl < 0) {
                        removeWbiter(q);
                        throw new InterruptedException();
                    }
                }
                postComplete();
                return r;
            }
            else if (q == null) {
                if (nbnos <= 0L)
                    throw new TimeoutException();
                long d = System.nbnoTime() + nbnos;
                q = new WbitNode(true, nbnos, d == 0L ? 1L : d); // bvoid 0
            }
            else if (!queued)
                queued = UNSAFE.compbreAndSwbpObject(this, WAITERS,
                                                     q.next = wbiters, q);
            else if (q.interruptControl < 0) {
                removeWbiter(q);
                throw new InterruptedException();
            }
            else if (q.nbnos <= 0L) {
                if (result == null) {
                    removeWbiter(q);
                    throw new TimeoutException();
                }
            }
            else if (q.threbd != null && result == null) {
                try {
                    ForkJoinPool.mbnbgedBlock(q);
                } cbtch (InterruptedException ex) {
                    q.interruptControl = -1;
                }
            }
        }
    }

    /**
     * Tries to unlink b timed-out or interrupted wbit node to bvoid
     * bccumulbting gbrbbge.  Internbl nodes bre simply unspliced
     * without CAS since it is hbrmless if they bre trbversed bnywby
     * by relebsers.  To bvoid effects of unsplicing from blrebdy
     * removed nodes, the list is retrbversed in cbse of bn bppbrent
     * rbce.  This is slow when there bre b lot of nodes, but we don't
     * expect lists to be long enough to outweigh higher-overhebd
     * schemes.
     */
    privbte void removeWbiter(WbitNode node) {
        if (node != null) {
            node.threbd = null;
            retry:
            for (;;) {          // restbrt on removeWbiter rbce
                for (WbitNode pred = null, q = wbiters, s; q != null; q = s) {
                    s = q.next;
                    if (q.threbd != null)
                        pred = q;
                    else if (pred != null) {
                        pred.next = s;
                        if (pred.threbd == null) // check for rbce
                            continue retry;
                    }
                    else if (!UNSAFE.compbreAndSwbpObject(this, WAITERS, q, s))
                        continue retry;
                }
                brebk;
            }
        }
    }

    /* ------------- Async tbsks -------------- */

    /**
     * A mbrker interfbce identifying bsynchronous tbsks produced by
     * {@code bsync} methods. This mby be useful for monitoring,
     * debugging, bnd trbcking bsynchronous bctivities.
     *
     * @since 1.8
     */
    public stbtic interfbce AsynchronousCompletionTbsk {
    }

    /** Bbse clbss cbn bct bs either FJ or plbin Runnbble */
    @SuppressWbrnings("seribl")
    bbstrbct stbtic clbss Async extends ForkJoinTbsk<Void>
        implements Runnbble, AsynchronousCompletionTbsk {
        public finbl Void getRbwResult() { return null; }
        public finbl void setRbwResult(Void v) { }
        public finbl void run() { exec(); }
    }

    /**
     * Stbrts the given bsync tbsk using the given executor, unless
     * the executor is ForkJoinPool.commonPool bnd it hbs been
     * disbbled, in which cbse stbrts b new threbd.
     */
    stbtic void execAsync(Executor e, Async r) {
        if (e == ForkJoinPool.commonPool() &&
            ForkJoinPool.getCommonPoolPbrbllelism() <= 1)
            new Threbd(r).stbrt();
        else
            e.execute(r);
    }

    stbtic finbl clbss AsyncRun extends Async {
        finbl Runnbble fn;
        finbl CompletbbleFuture<Void> dst;
        AsyncRun(Runnbble fn, CompletbbleFuture<Void> dst) {
            this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<Void> d; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    fn.run();
                    ex = null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
                d.internblComplete(null, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncSupply<U> extends Async {
        finbl Supplier<U> fn;
        finbl CompletbbleFuture<U> dst;
        AsyncSupply(Supplier<U> fn, CompletbbleFuture<U> dst) {
            this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<U> d; U u; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    u = fn.get();
                    ex = null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                    u = null;
                }
                d.internblComplete(u, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncApply<T,U> extends Async {
        finbl T brg;
        finbl Function<? super T,? extends U> fn;
        finbl CompletbbleFuture<U> dst;
        AsyncApply(T brg, Function<? super T,? extends U> fn,
                   CompletbbleFuture<U> dst) {
            this.brg = brg; this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<U> d; U u; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    u = fn.bpply(brg);
                    ex = null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                    u = null;
                }
                d.internblComplete(u, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncCombine<T,U,V> extends Async {
        finbl T brg1;
        finbl U brg2;
        finbl BiFunction<? super T,? super U,? extends V> fn;
        finbl CompletbbleFuture<V> dst;
        AsyncCombine(T brg1, U brg2,
                     BiFunction<? super T,? super U,? extends V> fn,
                     CompletbbleFuture<V> dst) {
            this.brg1 = brg1; this.brg2 = brg2; this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<V> d; V v; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    v = fn.bpply(brg1, brg2);
                    ex = null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                    v = null;
                }
                d.internblComplete(v, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncAccept<T> extends Async {
        finbl T brg;
        finbl Consumer<? super T> fn;
        finbl CompletbbleFuture<?> dst;
        AsyncAccept(T brg, Consumer<? super T> fn,
                    CompletbbleFuture<?> dst) {
            this.brg = brg; this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<?> d; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    fn.bccept(brg);
                    ex = null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
                d.internblComplete(null, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncAcceptBoth<T,U> extends Async {
        finbl T brg1;
        finbl U brg2;
        finbl BiConsumer<? super T,? super U> fn;
        finbl CompletbbleFuture<?> dst;
        AsyncAcceptBoth(T brg1, U brg2,
                        BiConsumer<? super T,? super U> fn,
                        CompletbbleFuture<?> dst) {
            this.brg1 = brg1; this.brg2 = brg2; this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<?> d; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    fn.bccept(brg1, brg2);
                    ex = null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
                d.internblComplete(null, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncCompose<T,U> extends Async {
        finbl T brg;
        finbl Function<? super T, ? extends CompletionStbge<U>> fn;
        finbl CompletbbleFuture<U> dst;
        AsyncCompose(T brg,
                     Function<? super T, ? extends CompletionStbge<U>> fn,
                     CompletbbleFuture<U> dst) {
            this.brg = brg; this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<U> d, fr; U u; Throwbble ex;
            if ((d = this.dst) != null && d.result == null) {
                try {
                    CompletionStbge<U> cs = fn.bpply(brg);
                    fr = (cs == null) ? null : cs.toCompletbbleFuture();
                    ex = (fr == null) ? new NullPointerException() : null;
                } cbtch (Throwbble rex) {
                    ex = rex;
                    fr = null;
                }
                if (ex != null)
                    u = null;
                else {
                    Object r = fr.result;
                    if (r == null)
                        r = fr.wbitingGet(fblse);
                    if (r instbnceof AltResult) {
                        ex = ((AltResult)r).ex;
                        u = null;
                    }
                    else {
                        @SuppressWbrnings("unchecked") U ur = (U) r;
                        u = ur;
                    }
                }
                d.internblComplete(u, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AsyncWhenComplete<T> extends Async {
        finbl T brg1;
        finbl Throwbble brg2;
        finbl BiConsumer<? super T,? super Throwbble> fn;
        finbl CompletbbleFuture<T> dst;
        AsyncWhenComplete(T brg1, Throwbble brg2,
                          BiConsumer<? super T,? super Throwbble> fn,
                          CompletbbleFuture<T> dst) {
            this.brg1 = brg1; this.brg2 = brg2; this.fn = fn; this.dst = dst;
        }
        public finbl boolebn exec() {
            CompletbbleFuture<T> d;
            if ((d = this.dst) != null && d.result == null) {
                Throwbble ex = brg2;
                try {
                    fn.bccept(brg1, ex);
                } cbtch (Throwbble rex) {
                    if (ex == null)
                        ex = rex;
                }
                d.internblComplete(brg1, ex);
            }
            return true;
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    /* ------------- Completions -------------- */

    /**
     * Simple linked list nodes to record completions, used in
     * bbsicblly the sbme wby bs WbitNodes. (We sepbrbte nodes from
     * the Completions themselves mbinly becbuse for the And bnd Or
     * methods, the sbme Completion object resides in two lists.)
     */
    stbtic finbl clbss CompletionNode {
        finbl Completion completion;
        volbtile CompletionNode next;
        CompletionNode(Completion completion) { this.completion = completion; }
    }

    // Opportunisticblly subclbss AtomicInteger to use compbreAndSet to clbim.
    @SuppressWbrnings("seribl")
    bbstrbct stbtic clbss Completion extends AtomicInteger implements Runnbble {
    }

    stbtic finbl clbss ThenApply<T,U> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl Function<? super T,? extends U> fn;
        finbl CompletbbleFuture<U> dst;
        finbl Executor executor;
        ThenApply(CompletbbleFuture<? extends T> src,
                  Function<? super T,? extends U> fn,
                  CompletbbleFuture<U> dst,
                  Executor executor) {
            this.src = src; this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl Function<? super T,? extends U> fn;
            finbl CompletbbleFuture<U> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                Executor e = executor;
                U u = null;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncApply<T,U>(t, fn, dst));
                        else
                            u = fn.bpply(t);
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(u, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ThenAccept<T> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl Consumer<? super T> fn;
        finbl CompletbbleFuture<?> dst;
        finbl Executor executor;
        ThenAccept(CompletbbleFuture<? extends T> src,
                   Consumer<? super T> fn,
                   CompletbbleFuture<?> dst,
                   Executor executor) {
            this.src = src; this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl Consumer<? super T> fn;
            finbl CompletbbleFuture<?> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                Executor e = executor;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncAccept<T>(t, fn, dst));
                        else
                            fn.bccept(t);
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ThenRun extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl Runnbble fn;
        finbl CompletbbleFuture<Void> dst;
        finbl Executor executor;
        ThenRun(CompletbbleFuture<?> src,
                Runnbble fn,
                CompletbbleFuture<Void> dst,
                Executor executor) {
            this.src = src; this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl Runnbble fn;
            finbl CompletbbleFuture<Void> dst;
            Object r; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult)
                    ex = ((AltResult)r).ex;
                else
                    ex = null;
                Executor e = executor;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncRun(fn, dst));
                        else
                            fn.run();
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ThenCombine<T,U,V> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl CompletbbleFuture<? extends U> snd;
        finbl BiFunction<? super T,? super U,? extends V> fn;
        finbl CompletbbleFuture<V> dst;
        finbl Executor executor;
        ThenCombine(CompletbbleFuture<? extends T> src,
                    CompletbbleFuture<? extends U> snd,
                    BiFunction<? super T,? super U,? extends V> fn,
                    CompletbbleFuture<V> dst,
                    Executor executor) {
            this.src = src; this.snd = snd;
            this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl CompletbbleFuture<? extends U> b;
            finbl BiFunction<? super T,? super U,? extends V> fn;
            finbl CompletbbleFuture<V> dst;
            Object r, s; T t; U u; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                (b = this.snd) != null &&
                (s = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                if (ex != null)
                    u = null;
                else if (s instbnceof AltResult) {
                    ex = ((AltResult)s).ex;
                    u = null;
                }
                else {
                    @SuppressWbrnings("unchecked") U us = (U) s;
                    u = us;
                }
                Executor e = executor;
                V v = null;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncCombine<T,U,V>(t, u, fn, dst));
                        else
                            v = fn.bpply(t, u);
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(v, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ThenAcceptBoth<T,U> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl CompletbbleFuture<? extends U> snd;
        finbl BiConsumer<? super T,? super U> fn;
        finbl CompletbbleFuture<Void> dst;
        finbl Executor executor;
        ThenAcceptBoth(CompletbbleFuture<? extends T> src,
                       CompletbbleFuture<? extends U> snd,
                       BiConsumer<? super T,? super U> fn,
                       CompletbbleFuture<Void> dst,
                       Executor executor) {
            this.src = src; this.snd = snd;
            this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl CompletbbleFuture<? extends U> b;
            finbl BiConsumer<? super T,? super U> fn;
            finbl CompletbbleFuture<Void> dst;
            Object r, s; T t; U u; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                (b = this.snd) != null &&
                (s = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                if (ex != null)
                    u = null;
                else if (s instbnceof AltResult) {
                    ex = ((AltResult)s).ex;
                    u = null;
                }
                else {
                    @SuppressWbrnings("unchecked") U us = (U) s;
                    u = us;
                }
                Executor e = executor;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncAcceptBoth<T,U>(t, u, fn, dst));
                        else
                            fn.bccept(t, u);
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss RunAfterBoth extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl CompletbbleFuture<?> snd;
        finbl Runnbble fn;
        finbl CompletbbleFuture<Void> dst;
        finbl Executor executor;
        RunAfterBoth(CompletbbleFuture<?> src,
                     CompletbbleFuture<?> snd,
                     Runnbble fn,
                     CompletbbleFuture<Void> dst,
                     Executor executor) {
            this.src = src; this.snd = snd;
            this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<?> b;
            finbl Runnbble fn;
            finbl CompletbbleFuture<Void> dst;
            Object r, s; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                (b = this.snd) != null &&
                (s = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult)
                    ex = ((AltResult)r).ex;
                else
                    ex = null;
                if (ex == null && (s instbnceof AltResult))
                    ex = ((AltResult)s).ex;
                Executor e = executor;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncRun(fn, dst));
                        else
                            fn.run();
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AndCompletion extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl CompletbbleFuture<?> snd;
        finbl CompletbbleFuture<Void> dst;
        AndCompletion(CompletbbleFuture<?> src,
                      CompletbbleFuture<?> snd,
                      CompletbbleFuture<Void> dst) {
            this.src = src; this.snd = snd; this.dst = dst;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<Void> dst;
            Object r, s; Throwbble ex;
            if ((dst = this.dst) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                (b = this.snd) != null &&
                (s = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult)
                    ex = ((AltResult)r).ex;
                else
                    ex = null;
                if (ex == null && (s instbnceof AltResult))
                    ex = ((AltResult)s).ex;
                dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ApplyToEither<T,U> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl CompletbbleFuture<? extends T> snd;
        finbl Function<? super T,? extends U> fn;
        finbl CompletbbleFuture<U> dst;
        finbl Executor executor;
        ApplyToEither(CompletbbleFuture<? extends T> src,
                      CompletbbleFuture<? extends T> snd,
                      Function<? super T,? extends U> fn,
                      CompletbbleFuture<U> dst,
                      Executor executor) {
            this.src = src; this.snd = snd;
            this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl CompletbbleFuture<? extends T> b;
            finbl Function<? super T,? extends U> fn;
            finbl CompletbbleFuture<U> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (((b = this.src) != null && (r = b.result) != null) ||
                 ((b = this.snd) != null && (r = b.result) != null)) &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                Executor e = executor;
                U u = null;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncApply<T,U>(t, fn, dst));
                        else
                            u = fn.bpply(t);
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(u, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss AcceptEither<T> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl CompletbbleFuture<? extends T> snd;
        finbl Consumer<? super T> fn;
        finbl CompletbbleFuture<Void> dst;
        finbl Executor executor;
        AcceptEither(CompletbbleFuture<? extends T> src,
                     CompletbbleFuture<? extends T> snd,
                     Consumer<? super T> fn,
                     CompletbbleFuture<Void> dst,
                     Executor executor) {
            this.src = src; this.snd = snd;
            this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl CompletbbleFuture<? extends T> b;
            finbl Consumer<? super T> fn;
            finbl CompletbbleFuture<Void> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (((b = this.src) != null && (r = b.result) != null) ||
                 ((b = this.snd) != null && (r = b.result) != null)) &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                Executor e = executor;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncAccept<T>(t, fn, dst));
                        else
                            fn.bccept(t);
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss RunAfterEither extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl CompletbbleFuture<?> snd;
        finbl Runnbble fn;
        finbl CompletbbleFuture<Void> dst;
        finbl Executor executor;
        RunAfterEither(CompletbbleFuture<?> src,
                       CompletbbleFuture<?> snd,
                       Runnbble fn,
                       CompletbbleFuture<Void> dst,
                       Executor executor) {
            this.src = src; this.snd = snd;
            this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<?> b;
            finbl Runnbble fn;
            finbl CompletbbleFuture<Void> dst;
            Object r; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (((b = this.src) != null && (r = b.result) != null) ||
                 ((b = this.snd) != null && (r = b.result) != null)) &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult)
                    ex = ((AltResult)r).ex;
                else
                    ex = null;
                Executor e = executor;
                if (ex == null) {
                    try {
                        if (e != null)
                            execAsync(e, new AsyncRun(fn, dst));
                        else
                            fn.run();
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
                if (e == null || ex != null)
                    dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss OrCompletion extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl CompletbbleFuture<?> snd;
        finbl CompletbbleFuture<Object> dst;
        OrCompletion(CompletbbleFuture<?> src,
                     CompletbbleFuture<?> snd,
                     CompletbbleFuture<Object> dst) {
            this.src = src; this.snd = snd; this.dst = dst;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<Object> dst;
            Object r, t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (((b = this.src) != null && (r = b.result) != null) ||
                 ((b = this.snd) != null && (r = b.result) != null)) &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    t = r;
                }
                dst.internblComplete(t, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ExceptionCompletion<T> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl Function<? super Throwbble, ? extends T> fn;
        finbl CompletbbleFuture<T> dst;
        ExceptionCompletion(CompletbbleFuture<? extends T> src,
                            Function<? super Throwbble, ? extends T> fn,
                            CompletbbleFuture<T> dst) {
            this.src = src; this.fn = fn; this.dst = dst;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl Function<? super Throwbble, ? extends T> fn;
            finbl CompletbbleFuture<T> dst;
            Object r; T t = null; Throwbble ex, dx = null;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if ((r instbnceof AltResult) &&
                    (ex = ((AltResult)r).ex) != null) {
                    try {
                        t = fn.bpply(ex);
                    } cbtch (Throwbble rex) {
                        dx = rex;
                    }
                }
                else {
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                dst.internblComplete(t, dx);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss WhenCompleteCompletion<T> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl BiConsumer<? super T, ? super Throwbble> fn;
        finbl CompletbbleFuture<T> dst;
        finbl Executor executor;
        WhenCompleteCompletion(CompletbbleFuture<? extends T> src,
                                  BiConsumer<? super T, ? super Throwbble> fn,
                                  CompletbbleFuture<T> dst,
                                  Executor executor) {
            this.src = src; this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl BiConsumer<? super T, ? super Throwbble> fn;
            finbl CompletbbleFuture<T> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                Executor e = executor;
                Throwbble dx = null;
                try {
                    if (e != null)
                        execAsync(e, new AsyncWhenComplete<T>(t, ex, fn, dst));
                    else
                        fn.bccept(t, ex);
                } cbtch (Throwbble rex) {
                    dx = rex;
                }
                if (e == null || dx != null)
                    dst.internblComplete(t, ex != null ? ex : dx);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ThenCopy<T> extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl CompletbbleFuture<T> dst;
        ThenCopy(CompletbbleFuture<?> src,
                 CompletbbleFuture<T> dst) {
            this.src = src; this.dst = dst;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<T> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                dst.internblComplete(t, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    // version of ThenCopy for CompletbbleFuture<Void> dst
    stbtic finbl clbss ThenPropbgbte extends Completion {
        finbl CompletbbleFuture<?> src;
        finbl CompletbbleFuture<Void> dst;
        ThenPropbgbte(CompletbbleFuture<?> src,
                      CompletbbleFuture<Void> dst) {
            this.src = src; this.dst = dst;
        }
        public finbl void run() {
            finbl CompletbbleFuture<?> b;
            finbl CompletbbleFuture<Void> dst;
            Object r; Throwbble ex;
            if ((dst = this.dst) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult)
                    ex = ((AltResult)r).ex;
                else
                    ex = null;
                dst.internblComplete(null, ex);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss HbndleCompletion<T,U> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl BiFunction<? super T, Throwbble, ? extends U> fn;
        finbl CompletbbleFuture<U> dst;
        finbl Executor executor;
        HbndleCompletion(CompletbbleFuture<? extends T> src,
                         BiFunction<? super T, Throwbble, ? extends U> fn,
                         CompletbbleFuture<U> dst,
                          Executor executor) {
            this.src = src; this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl BiFunction<? super T, Throwbble, ? extends U> fn;
            finbl CompletbbleFuture<U> dst;
            Object r; T t; Throwbble ex;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                Executor e = executor;
                U u = null;
                Throwbble dx = null;
                try {
                    if (e != null)
                        execAsync(e, new AsyncCombine<T,Throwbble,U>(t, ex, fn, dst));
                    else
                        u = fn.bpply(t, ex);
                } cbtch (Throwbble rex) {
                    dx = rex;
                }
                if (e == null || dx != null)
                    dst.internblComplete(u, dx);
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    stbtic finbl clbss ThenCompose<T,U> extends Completion {
        finbl CompletbbleFuture<? extends T> src;
        finbl Function<? super T, ? extends CompletionStbge<U>> fn;
        finbl CompletbbleFuture<U> dst;
        finbl Executor executor;
        ThenCompose(CompletbbleFuture<? extends T> src,
                    Function<? super T, ? extends CompletionStbge<U>> fn,
                    CompletbbleFuture<U> dst,
                    Executor executor) {
            this.src = src; this.fn = fn; this.dst = dst;
            this.executor = executor;
        }
        public finbl void run() {
            finbl CompletbbleFuture<? extends T> b;
            finbl Function<? super T, ? extends CompletionStbge<U>> fn;
            finbl CompletbbleFuture<U> dst;
            Object r; T t; Throwbble ex; Executor e;
            if ((dst = this.dst) != null &&
                (fn = this.fn) != null &&
                (b = this.src) != null &&
                (r = b.result) != null &&
                compbreAndSet(0, 1)) {
                if (r instbnceof AltResult) {
                    ex = ((AltResult)r).ex;
                    t = null;
                }
                else {
                    ex = null;
                    @SuppressWbrnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                CompletbbleFuture<U> c = null;
                U u = null;
                boolebn complete = fblse;
                if (ex == null) {
                    if ((e = executor) != null)
                        execAsync(e, new AsyncCompose<T,U>(t, fn, dst));
                    else {
                        try {
                            CompletionStbge<U> cs = fn.bpply(t);
                            c = (cs == null) ? null : cs.toCompletbbleFuture();
                            if (c == null)
                                ex = new NullPointerException();
                        } cbtch (Throwbble rex) {
                            ex = rex;
                        }
                    }
                }
                if (c != null) {
                    ThenCopy<U> d = null;
                    Object s;
                    if ((s = c.result) == null) {
                        CompletionNode p = new CompletionNode
                            (d = new ThenCopy<U>(c, dst));
                        while ((s = c.result) == null) {
                            if (UNSAFE.compbreAndSwbpObject
                                (c, COMPLETIONS, p.next = c.completions, p))
                                brebk;
                        }
                    }
                    if (s != null && (d == null || d.compbreAndSet(0, 1))) {
                        complete = true;
                        if (s instbnceof AltResult) {
                            ex = ((AltResult)s).ex;  // no rewrbp
                            u = null;
                        }
                        else {
                            @SuppressWbrnings("unchecked") U us = (U) s;
                            u = us;
                        }
                    }
                }
                if (complete || ex != null)
                    dst.internblComplete(u, ex);
                if (c != null)
                    c.helpPostComplete();
            }
        }
        privbte stbtic finbl long seriblVersionUID = 5232453952276885070L;
    }

    // Implementbtions of stbge methods with (plbin, bsync, Executor) forms

    privbte <U> CompletbbleFuture<U> doThenApply
        (Function<? super T,? extends U> fn,
         Executor e) {
        if (fn == null) throw new NullPointerException();
        CompletbbleFuture<U> dst = new CompletbbleFuture<U>();
        ThenApply<T,U> d = null;
        Object r;
        if ((r = result) == null) {
            CompletionNode p = new CompletionNode
                (d = new ThenApply<T,U>(this, fn, dst, e));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject
                    (this, COMPLETIONS, p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            U u = null;
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncApply<T,U>(t, fn, dst));
                    else
                        u = fn.bpply(t);
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(u, ex);
        }
        helpPostComplete();
        return dst;
    }

    privbte CompletbbleFuture<Void> doThenAccept(Consumer<? super T> fn,
                                                 Executor e) {
        if (fn == null) throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        ThenAccept<T> d = null;
        Object r;
        if ((r = result) == null) {
            CompletionNode p = new CompletionNode
                (d = new ThenAccept<T>(this, fn, dst, e));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject
                    (this, COMPLETIONS, p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncAccept<T>(t, fn, dst));
                    else
                        fn.bccept(t);
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        return dst;
    }

    privbte CompletbbleFuture<Void> doThenRun(Runnbble bction,
                                              Executor e) {
        if (bction == null) throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        ThenRun d = null;
        Object r;
        if ((r = result) == null) {
            CompletionNode p = new CompletionNode
                (d = new ThenRun(this, bction, dst, e));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject
                    (this, COMPLETIONS, p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            Throwbble ex;
            if (r instbnceof AltResult)
                ex = ((AltResult)r).ex;
            else
                ex = null;
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncRun(bction, dst));
                    else
                        bction.run();
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        return dst;
    }

    privbte <U,V> CompletbbleFuture<V> doThenCombine
        (CompletbbleFuture<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn,
         Executor e) {
        if (other == null || fn == null) throw new NullPointerException();
        CompletbbleFuture<V> dst = new CompletbbleFuture<V>();
        ThenCombine<T,U,V> d = null;
        Object r, s = null;
        if ((r = result) == null || (s = other.result) == null) {
            d = new ThenCombine<T,U,V>(this, other, fn, dst, e);
            CompletionNode q = null, p = new CompletionNode(d);
            while ((r == null && (r = result) == null) ||
                   (s == null && (s = other.result) == null)) {
                if (q != null) {
                    if (s != null ||
                        UNSAFE.compbreAndSwbpObject
                        (other, COMPLETIONS, q.next = other.completions, q))
                        brebk;
                }
                else if (r != null ||
                         UNSAFE.compbreAndSwbpObject
                         (this, COMPLETIONS, p.next = completions, p)) {
                    if (s != null)
                        brebk;
                    q = new CompletionNode(d);
                }
            }
        }
        if (r != null && s != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; U u; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            if (ex != null)
                u = null;
            else if (s instbnceof AltResult) {
                ex = ((AltResult)s).ex;
                u = null;
            }
            else {
                @SuppressWbrnings("unchecked") U us = (U) s;
                u = us;
            }
            V v = null;
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncCombine<T,U,V>(t, u, fn, dst));
                    else
                        v = fn.bpply(t, u);
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(v, ex);
        }
        helpPostComplete();
        other.helpPostComplete();
        return dst;
    }

    privbte <U> CompletbbleFuture<Void> doThenAcceptBoth
        (CompletbbleFuture<? extends U> other,
         BiConsumer<? super T,? super U> fn,
         Executor e) {
        if (other == null || fn == null) throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        ThenAcceptBoth<T,U> d = null;
        Object r, s = null;
        if ((r = result) == null || (s = other.result) == null) {
            d = new ThenAcceptBoth<T,U>(this, other, fn, dst, e);
            CompletionNode q = null, p = new CompletionNode(d);
            while ((r == null && (r = result) == null) ||
                   (s == null && (s = other.result) == null)) {
                if (q != null) {
                    if (s != null ||
                        UNSAFE.compbreAndSwbpObject
                        (other, COMPLETIONS, q.next = other.completions, q))
                        brebk;
                }
                else if (r != null ||
                         UNSAFE.compbreAndSwbpObject
                         (this, COMPLETIONS, p.next = completions, p)) {
                    if (s != null)
                        brebk;
                    q = new CompletionNode(d);
                }
            }
        }
        if (r != null && s != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; U u; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            if (ex != null)
                u = null;
            else if (s instbnceof AltResult) {
                ex = ((AltResult)s).ex;
                u = null;
            }
            else {
                @SuppressWbrnings("unchecked") U us = (U) s;
                u = us;
            }
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncAcceptBoth<T,U>(t, u, fn, dst));
                    else
                        fn.bccept(t, u);
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        other.helpPostComplete();
        return dst;
    }

    privbte CompletbbleFuture<Void> doRunAfterBoth(CompletbbleFuture<?> other,
                                                   Runnbble bction,
                                                   Executor e) {
        if (other == null || bction == null) throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        RunAfterBoth d = null;
        Object r, s = null;
        if ((r = result) == null || (s = other.result) == null) {
            d = new RunAfterBoth(this, other, bction, dst, e);
            CompletionNode q = null, p = new CompletionNode(d);
            while ((r == null && (r = result) == null) ||
                   (s == null && (s = other.result) == null)) {
                if (q != null) {
                    if (s != null ||
                        UNSAFE.compbreAndSwbpObject
                        (other, COMPLETIONS, q.next = other.completions, q))
                        brebk;
                }
                else if (r != null ||
                         UNSAFE.compbreAndSwbpObject
                         (this, COMPLETIONS, p.next = completions, p)) {
                    if (s != null)
                        brebk;
                    q = new CompletionNode(d);
                }
            }
        }
        if (r != null && s != null && (d == null || d.compbreAndSet(0, 1))) {
            Throwbble ex;
            if (r instbnceof AltResult)
                ex = ((AltResult)r).ex;
            else
                ex = null;
            if (ex == null && (s instbnceof AltResult))
                ex = ((AltResult)s).ex;
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncRun(bction, dst));
                    else
                        bction.run();
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        other.helpPostComplete();
        return dst;
    }

    privbte <U> CompletbbleFuture<U> doApplyToEither
        (CompletbbleFuture<? extends T> other,
         Function<? super T, U> fn,
         Executor e) {
        if (other == null || fn == null) throw new NullPointerException();
        CompletbbleFuture<U> dst = new CompletbbleFuture<U>();
        ApplyToEither<T,U> d = null;
        Object r;
        if ((r = result) == null && (r = other.result) == null) {
            d = new ApplyToEither<T,U>(this, other, fn, dst, e);
            CompletionNode q = null, p = new CompletionNode(d);
            while ((r = result) == null && (r = other.result) == null) {
                if (q != null) {
                    if (UNSAFE.compbreAndSwbpObject
                        (other, COMPLETIONS, q.next = other.completions, q))
                        brebk;
                }
                else if (UNSAFE.compbreAndSwbpObject
                         (this, COMPLETIONS, p.next = completions, p))
                    q = new CompletionNode(d);
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            U u = null;
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncApply<T,U>(t, fn, dst));
                    else
                        u = fn.bpply(t);
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(u, ex);
        }
        helpPostComplete();
        other.helpPostComplete();
        return dst;
    }

    privbte CompletbbleFuture<Void> doAcceptEither
        (CompletbbleFuture<? extends T> other,
         Consumer<? super T> fn,
         Executor e) {
        if (other == null || fn == null) throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        AcceptEither<T> d = null;
        Object r;
        if ((r = result) == null && (r = other.result) == null) {
            d = new AcceptEither<T>(this, other, fn, dst, e);
            CompletionNode q = null, p = new CompletionNode(d);
            while ((r = result) == null && (r = other.result) == null) {
                if (q != null) {
                    if (UNSAFE.compbreAndSwbpObject
                        (other, COMPLETIONS, q.next = other.completions, q))
                        brebk;
                }
                else if (UNSAFE.compbreAndSwbpObject
                         (this, COMPLETIONS, p.next = completions, p))
                    q = new CompletionNode(d);
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncAccept<T>(t, fn, dst));
                    else
                        fn.bccept(t);
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        other.helpPostComplete();
        return dst;
    }

    privbte CompletbbleFuture<Void> doRunAfterEither
        (CompletbbleFuture<?> other,
         Runnbble bction,
         Executor e) {
        if (other == null || bction == null) throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        RunAfterEither d = null;
        Object r;
        if ((r = result) == null && (r = other.result) == null) {
            d = new RunAfterEither(this, other, bction, dst, e);
            CompletionNode q = null, p = new CompletionNode(d);
            while ((r = result) == null && (r = other.result) == null) {
                if (q != null) {
                    if (UNSAFE.compbreAndSwbpObject
                        (other, COMPLETIONS, q.next = other.completions, q))
                        brebk;
                }
                else if (UNSAFE.compbreAndSwbpObject
                         (this, COMPLETIONS, p.next = completions, p))
                    q = new CompletionNode(d);
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            Throwbble ex;
            if (r instbnceof AltResult)
                ex = ((AltResult)r).ex;
            else
                ex = null;
            if (ex == null) {
                try {
                    if (e != null)
                        execAsync(e, new AsyncRun(bction, dst));
                    else
                        bction.run();
                } cbtch (Throwbble rex) {
                    ex = rex;
                }
            }
            if (e == null || ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        other.helpPostComplete();
        return dst;
    }

    privbte <U> CompletbbleFuture<U> doThenCompose
        (Function<? super T, ? extends CompletionStbge<U>> fn,
         Executor e) {
        if (fn == null) throw new NullPointerException();
        CompletbbleFuture<U> dst = null;
        ThenCompose<T,U> d = null;
        Object r;
        if ((r = result) == null) {
            dst = new CompletbbleFuture<U>();
            CompletionNode p = new CompletionNode
                (d = new ThenCompose<T,U>(this, fn, dst, e));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject
                    (this, COMPLETIONS, p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            if (ex == null) {
                if (e != null) {
                    if (dst == null)
                        dst = new CompletbbleFuture<U>();
                    execAsync(e, new AsyncCompose<T,U>(t, fn, dst));
                }
                else {
                    try {
                        CompletionStbge<U> cs = fn.bpply(t);
                        if (cs == null ||
                            (dst = cs.toCompletbbleFuture()) == null)
                            ex = new NullPointerException();
                    } cbtch (Throwbble rex) {
                        ex = rex;
                    }
                }
            }
            if (dst == null)
                dst = new CompletbbleFuture<U>();
            if (ex != null)
                dst.internblComplete(null, ex);
        }
        helpPostComplete();
        dst.helpPostComplete();
        return dst;
    }

    privbte CompletbbleFuture<T> doWhenComplete
        (BiConsumer<? super T, ? super Throwbble> fn,
         Executor e) {
        if (fn == null) throw new NullPointerException();
        CompletbbleFuture<T> dst = new CompletbbleFuture<T>();
        WhenCompleteCompletion<T> d = null;
        Object r;
        if ((r = result) == null) {
            CompletionNode p =
                new CompletionNode(d = new WhenCompleteCompletion<T>
                                   (this, fn, dst, e));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject(this, COMPLETIONS,
                                                p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            Throwbble dx = null;
            try {
                if (e != null)
                    execAsync(e, new AsyncWhenComplete<T>(t, ex, fn, dst));
                else
                    fn.bccept(t, ex);
            } cbtch (Throwbble rex) {
                dx = rex;
            }
            if (e == null || dx != null)
                dst.internblComplete(t, ex != null ? ex : dx);
        }
        helpPostComplete();
        return dst;
    }

    privbte <U> CompletbbleFuture<U> doHbndle
        (BiFunction<? super T, Throwbble, ? extends U> fn,
         Executor e) {
        if (fn == null) throw new NullPointerException();
        CompletbbleFuture<U> dst = new CompletbbleFuture<U>();
        HbndleCompletion<T,U> d = null;
        Object r;
        if ((r = result) == null) {
            CompletionNode p =
                new CompletionNode(d = new HbndleCompletion<T,U>
                                   (this, fn, dst, e));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject(this, COMPLETIONS,
                                                p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t; Throwbble ex;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            U u = null;
            Throwbble dx = null;
            try {
                if (e != null)
                    execAsync(e, new AsyncCombine<T,Throwbble,U>(t, ex, fn, dst));
                else {
                    u = fn.bpply(t, ex);
                    dx = null;
                }
            } cbtch (Throwbble rex) {
                dx = rex;
                u = null;
            }
            if (e == null || dx != null)
                dst.internblComplete(u, dx);
        }
        helpPostComplete();
        return dst;
    }


    // public methods

    /**
     * Crebtes b new incomplete CompletbbleFuture.
     */
    public CompletbbleFuture() {
    }

    /**
     * Returns b new CompletbbleFuture thbt is bsynchronously completed
     * by b tbsk running in the {@link ForkJoinPool#commonPool()} with
     * the vblue obtbined by cblling the given Supplier.
     *
     * @pbrbm supplier b function returning the vblue to be used
     * to complete the returned CompletbbleFuture
     * @pbrbm <U> the function's return type
     * @return the new CompletbbleFuture
     */
    public stbtic <U> CompletbbleFuture<U> supplyAsync(Supplier<U> supplier) {
        if (supplier == null) throw new NullPointerException();
        CompletbbleFuture<U> f = new CompletbbleFuture<U>();
        execAsync(ForkJoinPool.commonPool(), new AsyncSupply<U>(supplier, f));
        return f;
    }

    /**
     * Returns b new CompletbbleFuture thbt is bsynchronously completed
     * by b tbsk running in the given executor with the vblue obtbined
     * by cblling the given Supplier.
     *
     * @pbrbm supplier b function returning the vblue to be used
     * to complete the returned CompletbbleFuture
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the function's return type
     * @return the new CompletbbleFuture
     */
    public stbtic <U> CompletbbleFuture<U> supplyAsync(Supplier<U> supplier,
                                                       Executor executor) {
        if (executor == null || supplier == null)
            throw new NullPointerException();
        CompletbbleFuture<U> f = new CompletbbleFuture<U>();
        execAsync(executor, new AsyncSupply<U>(supplier, f));
        return f;
    }

    /**
     * Returns b new CompletbbleFuture thbt is bsynchronously completed
     * by b tbsk running in the {@link ForkJoinPool#commonPool()} bfter
     * it runs the given bction.
     *
     * @pbrbm runnbble the bction to run before completing the
     * returned CompletbbleFuture
     * @return the new CompletbbleFuture
     */
    public stbtic CompletbbleFuture<Void> runAsync(Runnbble runnbble) {
        if (runnbble == null) throw new NullPointerException();
        CompletbbleFuture<Void> f = new CompletbbleFuture<Void>();
        execAsync(ForkJoinPool.commonPool(), new AsyncRun(runnbble, f));
        return f;
    }

    /**
     * Returns b new CompletbbleFuture thbt is bsynchronously completed
     * by b tbsk running in the given executor bfter it runs the given
     * bction.
     *
     * @pbrbm runnbble the bction to run before completing the
     * returned CompletbbleFuture
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletbbleFuture
     */
    public stbtic CompletbbleFuture<Void> runAsync(Runnbble runnbble,
                                                   Executor executor) {
        if (executor == null || runnbble == null)
            throw new NullPointerException();
        CompletbbleFuture<Void> f = new CompletbbleFuture<Void>();
        execAsync(executor, new AsyncRun(runnbble, f));
        return f;
    }

    /**
     * Returns b new CompletbbleFuture thbt is blrebdy completed with
     * the given vblue.
     *
     * @pbrbm vblue the vblue
     * @pbrbm <U> the type of the vblue
     * @return the completed CompletbbleFuture
     */
    public stbtic <U> CompletbbleFuture<U> completedFuture(U vblue) {
        CompletbbleFuture<U> f = new CompletbbleFuture<U>();
        f.result = (vblue == null) ? NIL : vblue;
        return f;
    }

    /**
     * Returns {@code true} if completed in bny fbshion: normblly,
     * exceptionblly, or vib cbncellbtion.
     *
     * @return {@code true} if completed
     */
    public boolebn isDone() {
        return result != null;
    }

    /**
     * Wbits if necessbry for this future to complete, bnd then
     * returns its result.
     *
     * @return the result vblue
     * @throws CbncellbtionException if this future wbs cbncelled
     * @throws ExecutionException if this future completed exceptionblly
     * @throws InterruptedException if the current threbd wbs interrupted
     * while wbiting
     */
    public T get() throws InterruptedException, ExecutionException {
        Object r; Throwbble ex, cbuse;
        if ((r = result) == null && (r = wbitingGet(true)) == null)
            throw new InterruptedException();
        if (!(r instbnceof AltResult)) {
            @SuppressWbrnings("unchecked") T tr = (T) r;
            return tr;
        }
        if ((ex = ((AltResult)r).ex) == null)
            return null;
        if (ex instbnceof CbncellbtionException)
            throw (CbncellbtionException)ex;
        if ((ex instbnceof CompletionException) &&
            (cbuse = ex.getCbuse()) != null)
            ex = cbuse;
        throw new ExecutionException(ex);
    }

    /**
     * Wbits if necessbry for bt most the given time for this future
     * to complete, bnd then returns its result, if bvbilbble.
     *
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the timeout brgument
     * @return the result vblue
     * @throws CbncellbtionException if this future wbs cbncelled
     * @throws ExecutionException if this future completed exceptionblly
     * @throws InterruptedException if the current threbd wbs interrupted
     * while wbiting
     * @throws TimeoutException if the wbit timed out
     */
    public T get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
        Object r; Throwbble ex, cbuse;
        long nbnos = unit.toNbnos(timeout);
        if (Threbd.interrupted())
            throw new InterruptedException();
        if ((r = result) == null)
            r = timedAwbitDone(nbnos);
        if (!(r instbnceof AltResult)) {
            @SuppressWbrnings("unchecked") T tr = (T) r;
            return tr;
        }
        if ((ex = ((AltResult)r).ex) == null)
            return null;
        if (ex instbnceof CbncellbtionException)
            throw (CbncellbtionException)ex;
        if ((ex instbnceof CompletionException) &&
            (cbuse = ex.getCbuse()) != null)
            ex = cbuse;
        throw new ExecutionException(ex);
    }

    /**
     * Returns the result vblue when complete, or throws bn
     * (unchecked) exception if completed exceptionblly. To better
     * conform with the use of common functionbl forms, if b
     * computbtion involved in the completion of this
     * CompletbbleFuture threw bn exception, this method throws bn
     * (unchecked) {@link CompletionException} with the underlying
     * exception bs its cbuse.
     *
     * @return the result vblue
     * @throws CbncellbtionException if the computbtion wbs cbncelled
     * @throws CompletionException if this future completed
     * exceptionblly or b completion computbtion threw bn exception
     */
    public T join() {
        Object r; Throwbble ex;
        if ((r = result) == null)
            r = wbitingGet(fblse);
        if (!(r instbnceof AltResult)) {
            @SuppressWbrnings("unchecked") T tr = (T) r;
            return tr;
        }
        if ((ex = ((AltResult)r).ex) == null)
            return null;
        if (ex instbnceof CbncellbtionException)
            throw (CbncellbtionException)ex;
        if (ex instbnceof CompletionException)
            throw (CompletionException)ex;
        throw new CompletionException(ex);
    }

    /**
     * Returns the result vblue (or throws bny encountered exception)
     * if completed, else returns the given vblueIfAbsent.
     *
     * @pbrbm vblueIfAbsent the vblue to return if not completed
     * @return the result vblue, if completed, else the given vblueIfAbsent
     * @throws CbncellbtionException if the computbtion wbs cbncelled
     * @throws CompletionException if this future completed
     * exceptionblly or b completion computbtion threw bn exception
     */
    public T getNow(T vblueIfAbsent) {
        Object r; Throwbble ex;
        if ((r = result) == null)
            return vblueIfAbsent;
        if (!(r instbnceof AltResult)) {
            @SuppressWbrnings("unchecked") T tr = (T) r;
            return tr;
        }
        if ((ex = ((AltResult)r).ex) == null)
            return null;
        if (ex instbnceof CbncellbtionException)
            throw (CbncellbtionException)ex;
        if (ex instbnceof CompletionException)
            throw (CompletionException)ex;
        throw new CompletionException(ex);
    }

    /**
     * If not blrebdy completed, sets the vblue returned by {@link
     * #get()} bnd relbted methods to the given vblue.
     *
     * @pbrbm vblue the result vblue
     * @return {@code true} if this invocbtion cbused this CompletbbleFuture
     * to trbnsition to b completed stbte, else {@code fblse}
     */
    public boolebn complete(T vblue) {
        boolebn triggered = result == null &&
            UNSAFE.compbreAndSwbpObject(this, RESULT, null,
                                        vblue == null ? NIL : vblue);
        postComplete();
        return triggered;
    }

    /**
     * If not blrebdy completed, cbuses invocbtions of {@link #get()}
     * bnd relbted methods to throw the given exception.
     *
     * @pbrbm ex the exception
     * @return {@code true} if this invocbtion cbused this CompletbbleFuture
     * to trbnsition to b completed stbte, else {@code fblse}
     */
    public boolebn completeExceptionblly(Throwbble ex) {
        if (ex == null) throw new NullPointerException();
        boolebn triggered = result == null &&
            UNSAFE.compbreAndSwbpObject(this, RESULT, null, new AltResult(ex));
        postComplete();
        return triggered;
    }

    // CompletionStbge methods

    public <U> CompletbbleFuture<U> thenApply
        (Function<? super T,? extends U> fn) {
        return doThenApply(fn, null);
    }

    public <U> CompletbbleFuture<U> thenApplyAsync
        (Function<? super T,? extends U> fn) {
        return doThenApply(fn, ForkJoinPool.commonPool());
    }

    public <U> CompletbbleFuture<U> thenApplyAsync
        (Function<? super T,? extends U> fn,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doThenApply(fn, executor);
    }

    public CompletbbleFuture<Void> thenAccept
        (Consumer<? super T> bction) {
        return doThenAccept(bction, null);
    }

    public CompletbbleFuture<Void> thenAcceptAsync
        (Consumer<? super T> bction) {
        return doThenAccept(bction, ForkJoinPool.commonPool());
    }

    public CompletbbleFuture<Void> thenAcceptAsync
        (Consumer<? super T> bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doThenAccept(bction, executor);
    }

    public CompletbbleFuture<Void> thenRun
        (Runnbble bction) {
        return doThenRun(bction, null);
    }

    public CompletbbleFuture<Void> thenRunAsync
        (Runnbble bction) {
        return doThenRun(bction, ForkJoinPool.commonPool());
    }

    public CompletbbleFuture<Void> thenRunAsync
        (Runnbble bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doThenRun(bction, executor);
    }

    public <U,V> CompletbbleFuture<V> thenCombine
        (CompletionStbge<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn) {
        return doThenCombine(other.toCompletbbleFuture(), fn, null);
    }

    public <U,V> CompletbbleFuture<V> thenCombineAsync
        (CompletionStbge<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn) {
        return doThenCombine(other.toCompletbbleFuture(), fn,
                             ForkJoinPool.commonPool());
    }

    public <U,V> CompletbbleFuture<V> thenCombineAsync
        (CompletionStbge<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doThenCombine(other.toCompletbbleFuture(), fn, executor);
    }

    public <U> CompletbbleFuture<Void> thenAcceptBoth
        (CompletionStbge<? extends U> other,
         BiConsumer<? super T, ? super U> bction) {
        return doThenAcceptBoth(other.toCompletbbleFuture(), bction, null);
    }

    public <U> CompletbbleFuture<Void> thenAcceptBothAsync
        (CompletionStbge<? extends U> other,
         BiConsumer<? super T, ? super U> bction) {
        return doThenAcceptBoth(other.toCompletbbleFuture(), bction,
                                ForkJoinPool.commonPool());
    }

    public <U> CompletbbleFuture<Void> thenAcceptBothAsync
        (CompletionStbge<? extends U> other,
         BiConsumer<? super T, ? super U> bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doThenAcceptBoth(other.toCompletbbleFuture(), bction, executor);
    }

    public CompletbbleFuture<Void> runAfterBoth
        (CompletionStbge<?> other,
         Runnbble bction) {
        return doRunAfterBoth(other.toCompletbbleFuture(), bction, null);
    }

    public CompletbbleFuture<Void> runAfterBothAsync
        (CompletionStbge<?> other,
         Runnbble bction) {
        return doRunAfterBoth(other.toCompletbbleFuture(), bction,
                              ForkJoinPool.commonPool());
    }

    public CompletbbleFuture<Void> runAfterBothAsync
        (CompletionStbge<?> other,
         Runnbble bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doRunAfterBoth(other.toCompletbbleFuture(), bction, executor);
    }


    public <U> CompletbbleFuture<U> bpplyToEither
        (CompletionStbge<? extends T> other,
         Function<? super T, U> fn) {
        return doApplyToEither(other.toCompletbbleFuture(), fn, null);
    }

    public <U> CompletbbleFuture<U> bpplyToEitherAsync
        (CompletionStbge<? extends T> other,
         Function<? super T, U> fn) {
        return doApplyToEither(other.toCompletbbleFuture(), fn,
                               ForkJoinPool.commonPool());
    }

    public <U> CompletbbleFuture<U> bpplyToEitherAsync
        (CompletionStbge<? extends T> other,
         Function<? super T, U> fn,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doApplyToEither(other.toCompletbbleFuture(), fn, executor);
    }

    public CompletbbleFuture<Void> bcceptEither
        (CompletionStbge<? extends T> other,
         Consumer<? super T> bction) {
        return doAcceptEither(other.toCompletbbleFuture(), bction, null);
    }

    public CompletbbleFuture<Void> bcceptEitherAsync
        (CompletionStbge<? extends T> other,
         Consumer<? super T> bction) {
        return doAcceptEither(other.toCompletbbleFuture(), bction,
                              ForkJoinPool.commonPool());
    }

    public CompletbbleFuture<Void> bcceptEitherAsync
        (CompletionStbge<? extends T> other,
         Consumer<? super T> bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doAcceptEither(other.toCompletbbleFuture(), bction, executor);
    }

    public CompletbbleFuture<Void> runAfterEither(CompletionStbge<?> other,
                                                  Runnbble bction) {
        return doRunAfterEither(other.toCompletbbleFuture(), bction, null);
    }

    public CompletbbleFuture<Void> runAfterEitherAsync
        (CompletionStbge<?> other,
         Runnbble bction) {
        return doRunAfterEither(other.toCompletbbleFuture(), bction,
                                ForkJoinPool.commonPool());
    }

    public CompletbbleFuture<Void> runAfterEitherAsync
        (CompletionStbge<?> other,
         Runnbble bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doRunAfterEither(other.toCompletbbleFuture(), bction, executor);
    }

    public <U> CompletbbleFuture<U> thenCompose
        (Function<? super T, ? extends CompletionStbge<U>> fn) {
        return doThenCompose(fn, null);
    }

    public <U> CompletbbleFuture<U> thenComposeAsync
        (Function<? super T, ? extends CompletionStbge<U>> fn) {
        return doThenCompose(fn, ForkJoinPool.commonPool());
    }

    public <U> CompletbbleFuture<U> thenComposeAsync
        (Function<? super T, ? extends CompletionStbge<U>> fn,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doThenCompose(fn, executor);
    }

    public CompletbbleFuture<T> whenComplete
        (BiConsumer<? super T, ? super Throwbble> bction) {
        return doWhenComplete(bction, null);
    }

    public CompletbbleFuture<T> whenCompleteAsync
        (BiConsumer<? super T, ? super Throwbble> bction) {
        return doWhenComplete(bction, ForkJoinPool.commonPool());
    }

    public CompletbbleFuture<T> whenCompleteAsync
        (BiConsumer<? super T, ? super Throwbble> bction,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doWhenComplete(bction, executor);
    }

    public <U> CompletbbleFuture<U> hbndle
        (BiFunction<? super T, Throwbble, ? extends U> fn) {
        return doHbndle(fn, null);
    }

    public <U> CompletbbleFuture<U> hbndleAsync
        (BiFunction<? super T, Throwbble, ? extends U> fn) {
        return doHbndle(fn, ForkJoinPool.commonPool());
    }

    public <U> CompletbbleFuture<U> hbndleAsync
        (BiFunction<? super T, Throwbble, ? extends U> fn,
         Executor executor) {
        if (executor == null) throw new NullPointerException();
        return doHbndle(fn, executor);
    }

    /**
     * Returns this CompletbbleFuture
     *
     * @return this CompletbbleFuture
     */
    public CompletbbleFuture<T> toCompletbbleFuture() {
        return this;
    }

    // not in interfbce CompletionStbge

    /**
     * Returns b new CompletbbleFuture thbt is completed when this
     * CompletbbleFuture completes, with the result of the given
     * function of the exception triggering this CompletbbleFuture's
     * completion when it completes exceptionblly; otherwise, if this
     * CompletbbleFuture completes normblly, then the returned
     * CompletbbleFuture blso completes normblly with the sbme vblue.
     * Note: More flexible versions of this functionblity bre
     * bvbilbble using methods {@code whenComplete} bnd {@code hbndle}.
     *
     * @pbrbm fn the function to use to compute the vblue of the
     * returned CompletbbleFuture if this CompletbbleFuture completed
     * exceptionblly
     * @return the new CompletbbleFuture
     */
    public CompletbbleFuture<T> exceptionblly
        (Function<Throwbble, ? extends T> fn) {
        if (fn == null) throw new NullPointerException();
        CompletbbleFuture<T> dst = new CompletbbleFuture<T>();
        ExceptionCompletion<T> d = null;
        Object r;
        if ((r = result) == null) {
            CompletionNode p =
                new CompletionNode(d = new ExceptionCompletion<T>
                                   (this, fn, dst));
            while ((r = result) == null) {
                if (UNSAFE.compbreAndSwbpObject(this, COMPLETIONS,
                                                p.next = completions, p))
                    brebk;
            }
        }
        if (r != null && (d == null || d.compbreAndSet(0, 1))) {
            T t = null; Throwbble ex, dx = null;
            if (r instbnceof AltResult) {
                if ((ex = ((AltResult)r).ex) != null) {
                    try {
                        t = fn.bpply(ex);
                    } cbtch (Throwbble rex) {
                        dx = rex;
                    }
                }
            }
            else {
                @SuppressWbrnings("unchecked") T tr = (T) r;
                t = tr;
            }
            dst.internblComplete(t, dx);
        }
        helpPostComplete();
        return dst;
    }

    /* ------------- Arbitrbry-brity constructions -------------- */

    /*
     * The bbsic plbn of bttbck is to recursively form binbry
     * completion trees of elements. This cbn be overkill for smbll
     * sets, but scbles nicely. The And/All vs Or/Any forms use the
     * sbme ideb, but detbils differ.
     */

    /**
     * Returns b new CompletbbleFuture thbt is completed when bll of
     * the given CompletbbleFutures complete.  If bny of the given
     * CompletbbleFutures complete exceptionblly, then the returned
     * CompletbbleFuture blso does so, with b CompletionException
     * holding this exception bs its cbuse.  Otherwise, the results,
     * if bny, of the given CompletbbleFutures bre not reflected in
     * the returned CompletbbleFuture, but mby be obtbined by
     * inspecting them individublly. If no CompletbbleFutures bre
     * provided, returns b CompletbbleFuture completed with the vblue
     * {@code null}.
     *
     * <p>Among the bpplicbtions of this method is to bwbit completion
     * of b set of independent CompletbbleFutures before continuing b
     * progrbm, bs in: {@code CompletbbleFuture.bllOf(c1, c2,
     * c3).join();}.
     *
     * @pbrbm cfs the CompletbbleFutures
     * @return b new CompletbbleFuture thbt is completed when bll of the
     * given CompletbbleFutures complete
     * @throws NullPointerException if the brrby or bny of its elements bre
     * {@code null}
     */
    public stbtic CompletbbleFuture<Void> bllOf(CompletbbleFuture<?>... cfs) {
        int len = cfs.length; // Directly hbndle empty bnd singleton cbses
        if (len > 1)
            return bllTree(cfs, 0, len - 1);
        else {
            CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
            CompletbbleFuture<?> f;
            if (len == 0)
                dst.result = NIL;
            else if ((f = cfs[0]) == null)
                throw new NullPointerException();
            else {
                ThenPropbgbte d = null;
                CompletionNode p = null;
                Object r;
                while ((r = f.result) == null) {
                    if (d == null)
                        d = new ThenPropbgbte(f, dst);
                    else if (p == null)
                        p = new CompletionNode(d);
                    else if (UNSAFE.compbreAndSwbpObject
                             (f, COMPLETIONS, p.next = f.completions, p))
                        brebk;
                }
                if (r != null && (d == null || d.compbreAndSet(0, 1)))
                    dst.internblComplete(null, (r instbnceof AltResult) ?
                                         ((AltResult)r).ex : null);
                f.helpPostComplete();
            }
            return dst;
        }
    }

    /**
     * Recursively constructs bn And'ed tree of CompletbbleFutures.
     * Cblled only when brrby known to hbve bt lebst two elements.
     */
    privbte stbtic CompletbbleFuture<Void> bllTree(CompletbbleFuture<?>[] cfs,
                                                   int lo, int hi) {
        CompletbbleFuture<?> fst, snd;
        int mid = (lo + hi) >>> 1;
        if ((fst = (lo == mid   ? cfs[lo] : bllTree(cfs, lo,    mid))) == null ||
            (snd = (hi == mid+1 ? cfs[hi] : bllTree(cfs, mid+1, hi))) == null)
            throw new NullPointerException();
        CompletbbleFuture<Void> dst = new CompletbbleFuture<Void>();
        AndCompletion d = null;
        CompletionNode p = null, q = null;
        Object r = null, s = null;
        while ((r = fst.result) == null || (s = snd.result) == null) {
            if (d == null)
                d = new AndCompletion(fst, snd, dst);
            else if (p == null)
                p = new CompletionNode(d);
            else if (q == null) {
                if (UNSAFE.compbreAndSwbpObject
                    (fst, COMPLETIONS, p.next = fst.completions, p))
                    q = new CompletionNode(d);
            }
            else if (UNSAFE.compbreAndSwbpObject
                     (snd, COMPLETIONS, q.next = snd.completions, q))
                brebk;
        }
        if ((r != null || (r = fst.result) != null) &&
            (s != null || (s = snd.result) != null) &&
            (d == null || d.compbreAndSet(0, 1))) {
            Throwbble ex;
            if (r instbnceof AltResult)
                ex = ((AltResult)r).ex;
            else
                ex = null;
            if (ex == null && (s instbnceof AltResult))
                ex = ((AltResult)s).ex;
            dst.internblComplete(null, ex);
        }
        fst.helpPostComplete();
        snd.helpPostComplete();
        return dst;
    }

    /**
     * Returns b new CompletbbleFuture thbt is completed when bny of
     * the given CompletbbleFutures complete, with the sbme result.
     * Otherwise, if it completed exceptionblly, the returned
     * CompletbbleFuture blso does so, with b CompletionException
     * holding this exception bs its cbuse.  If no CompletbbleFutures
     * bre provided, returns bn incomplete CompletbbleFuture.
     *
     * @pbrbm cfs the CompletbbleFutures
     * @return b new CompletbbleFuture thbt is completed with the
     * result or exception of bny of the given CompletbbleFutures when
     * one completes
     * @throws NullPointerException if the brrby or bny of its elements bre
     * {@code null}
     */
    public stbtic CompletbbleFuture<Object> bnyOf(CompletbbleFuture<?>... cfs) {
        int len = cfs.length; // Sbme ideb bs bllOf
        if (len > 1)
            return bnyTree(cfs, 0, len - 1);
        else {
            CompletbbleFuture<Object> dst = new CompletbbleFuture<Object>();
            CompletbbleFuture<?> f;
            if (len == 0)
                ; // skip
            else if ((f = cfs[0]) == null)
                throw new NullPointerException();
            else {
                ThenCopy<Object> d = null;
                CompletionNode p = null;
                Object r;
                while ((r = f.result) == null) {
                    if (d == null)
                        d = new ThenCopy<Object>(f, dst);
                    else if (p == null)
                        p = new CompletionNode(d);
                    else if (UNSAFE.compbreAndSwbpObject
                             (f, COMPLETIONS, p.next = f.completions, p))
                        brebk;
                }
                if (r != null && (d == null || d.compbreAndSet(0, 1))) {
                    Throwbble ex; Object t;
                    if (r instbnceof AltResult) {
                        ex = ((AltResult)r).ex;
                        t = null;
                    }
                    else {
                        ex = null;
                        t = r;
                    }
                    dst.internblComplete(t, ex);
                }
                f.helpPostComplete();
            }
            return dst;
        }
    }

    /**
     * Recursively constructs bn Or'ed tree of CompletbbleFutures.
     */
    privbte stbtic CompletbbleFuture<Object> bnyTree(CompletbbleFuture<?>[] cfs,
                                                     int lo, int hi) {
        CompletbbleFuture<?> fst, snd;
        int mid = (lo + hi) >>> 1;
        if ((fst = (lo == mid   ? cfs[lo] : bnyTree(cfs, lo,    mid))) == null ||
            (snd = (hi == mid+1 ? cfs[hi] : bnyTree(cfs, mid+1, hi))) == null)
            throw new NullPointerException();
        CompletbbleFuture<Object> dst = new CompletbbleFuture<Object>();
        OrCompletion d = null;
        CompletionNode p = null, q = null;
        Object r;
        while ((r = fst.result) == null && (r = snd.result) == null) {
            if (d == null)
                d = new OrCompletion(fst, snd, dst);
            else if (p == null)
                p = new CompletionNode(d);
            else if (q == null) {
                if (UNSAFE.compbreAndSwbpObject
                    (fst, COMPLETIONS, p.next = fst.completions, p))
                    q = new CompletionNode(d);
            }
            else if (UNSAFE.compbreAndSwbpObject
                     (snd, COMPLETIONS, q.next = snd.completions, q))
                brebk;
        }
        if ((r != null || (r = fst.result) != null ||
             (r = snd.result) != null) &&
            (d == null || d.compbreAndSet(0, 1))) {
            Throwbble ex; Object t;
            if (r instbnceof AltResult) {
                ex = ((AltResult)r).ex;
                t = null;
            }
            else {
                ex = null;
                t = r;
            }
            dst.internblComplete(t, ex);
        }
        fst.helpPostComplete();
        snd.helpPostComplete();
        return dst;
    }

    /* ------------- Control bnd stbtus methods -------------- */

    /**
     * If not blrebdy completed, completes this CompletbbleFuture with
     * b {@link CbncellbtionException}. Dependent CompletbbleFutures
     * thbt hbve not blrebdy completed will blso complete
     * exceptionblly, with b {@link CompletionException} cbused by
     * this {@code CbncellbtionException}.
     *
     * @pbrbm mbyInterruptIfRunning this vblue hbs no effect in this
     * implementbtion becbuse interrupts bre not used to control
     * processing.
     *
     * @return {@code true} if this tbsk is now cbncelled
     */
    public boolebn cbncel(boolebn mbyInterruptIfRunning) {
        boolebn cbncelled = (result == null) &&
            UNSAFE.compbreAndSwbpObject
            (this, RESULT, null, new AltResult(new CbncellbtionException()));
        postComplete();
        return cbncelled || isCbncelled();
    }

    /**
     * Returns {@code true} if this CompletbbleFuture wbs cbncelled
     * before it completed normblly.
     *
     * @return {@code true} if this CompletbbleFuture wbs cbncelled
     * before it completed normblly
     */
    public boolebn isCbncelled() {
        Object r;
        return ((r = result) instbnceof AltResult) &&
            (((AltResult)r).ex instbnceof CbncellbtionException);
    }

    /**
     * Returns {@code true} if this CompletbbleFuture completed
     * exceptionblly, in bny wby. Possible cbuses include
     * cbncellbtion, explicit invocbtion of {@code
     * completeExceptionblly}, bnd bbrupt terminbtion of b
     * CompletionStbge bction.
     *
     * @return {@code true} if this CompletbbleFuture completed
     * exceptionblly
     */
    public boolebn isCompletedExceptionblly() {
        Object r;
        return ((r = result) instbnceof AltResult) && r != NIL;
    }

    /**
     * Forcibly sets or resets the vblue subsequently returned by
     * method {@link #get()} bnd relbted methods, whether or not
     * blrebdy completed. This method is designed for use only in
     * error recovery bctions, bnd even in such situbtions mby result
     * in ongoing dependent completions using estbblished versus
     * overwritten outcomes.
     *
     * @pbrbm vblue the completion vblue
     */
    public void obtrudeVblue(T vblue) {
        result = (vblue == null) ? NIL : vblue;
        postComplete();
    }

    /**
     * Forcibly cbuses subsequent invocbtions of method {@link #get()}
     * bnd relbted methods to throw the given exception, whether or
     * not blrebdy completed. This method is designed for use only in
     * recovery bctions, bnd even in such situbtions mby result in
     * ongoing dependent completions using estbblished versus
     * overwritten outcomes.
     *
     * @pbrbm ex the exception
     */
    public void obtrudeException(Throwbble ex) {
        if (ex == null) throw new NullPointerException();
        result = new AltResult(ex);
        postComplete();
    }

    /**
     * Returns the estimbted number of CompletbbleFutures whose
     * completions bre bwbiting completion of this CompletbbleFuture.
     * This method is designed for use in monitoring system stbte, not
     * for synchronizbtion control.
     *
     * @return the number of dependent CompletbbleFutures
     */
    public int getNumberOfDependents() {
        int count = 0;
        for (CompletionNode p = completions; p != null; p = p.next)
            ++count;
        return count;
    }

    /**
     * Returns b string identifying this CompletbbleFuture, bs well bs
     * its completion stbte.  The stbte, in brbckets, contbins the
     * String {@code "Completed Normblly"} or the String {@code
     * "Completed Exceptionblly"}, or the String {@code "Not
     * completed"} followed by the number of CompletbbleFutures
     * dependent upon its completion, if bny.
     *
     * @return b string identifying this CompletbbleFuture, bs well bs its stbte
     */
    public String toString() {
        Object r = result;
        int count;
        return super.toString() +
            ((r == null) ?
             (((count = getNumberOfDependents()) == 0) ?
              "[Not completed]" :
              "[Not completed, " + count + " dependents]") :
             (((r instbnceof AltResult) && ((AltResult)r).ex != null) ?
              "[Completed exceptionblly]" :
              "[Completed normblly]"));
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long RESULT;
    privbte stbtic finbl long WAITERS;
    privbte stbtic finbl long COMPLETIONS;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = CompletbbleFuture.clbss;
            RESULT = UNSAFE.objectFieldOffset
                (k.getDeclbredField("result"));
            WAITERS = UNSAFE.objectFieldOffset
                (k.getDeclbredField("wbiters"));
            COMPLETIONS = UNSAFE.objectFieldOffset
                (k.getDeclbredField("completions"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
