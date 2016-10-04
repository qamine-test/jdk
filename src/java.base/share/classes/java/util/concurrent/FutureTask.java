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
import jbvb.util.concurrent.locks.LockSupport;

/**
 * A cbncellbble bsynchronous computbtion.  This clbss provides b bbse
 * implementbtion of {@link Future}, with methods to stbrt bnd cbncel
 * b computbtion, query to see if the computbtion is complete, bnd
 * retrieve the result of the computbtion.  The result cbn only be
 * retrieved when the computbtion hbs completed; the {@code get}
 * methods will block if the computbtion hbs not yet completed.  Once
 * the computbtion hbs completed, the computbtion cbnnot be restbrted
 * or cbncelled (unless the computbtion is invoked using
 * {@link #runAndReset}).
 *
 * <p>A {@code FutureTbsk} cbn be used to wrbp b {@link Cbllbble} or
 * {@link Runnbble} object.  Becbuse {@code FutureTbsk} implements
 * {@code Runnbble}, b {@code FutureTbsk} cbn be submitted to bn
 * {@link Executor} for execution.
 *
 * <p>In bddition to serving bs b stbndblone clbss, this clbss provides
 * {@code protected} functionblity thbt mby be useful when crebting
 * customized tbsk clbsses.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <V> The result type returned by this FutureTbsk's {@code get} methods
 */
public clbss FutureTbsk<V> implements RunnbbleFuture<V> {
    /*
     * Revision notes: This differs from previous versions of this
     * clbss thbt relied on AbstrbctQueuedSynchronizer, mbinly to
     * bvoid surprising users bbout retbining interrupt stbtus during
     * cbncellbtion rbces. Sync control in the current design relies
     * on b "stbte" field updbted vib CAS to trbck completion, blong
     * with b simple Treiber stbck to hold wbiting threbds.
     *
     * Style note: As usubl, we bypbss overhebd of using
     * AtomicXFieldUpdbters bnd instebd directly use Unsbfe intrinsics.
     */

    /**
     * The run stbte of this tbsk, initiblly NEW.  The run stbte
     * trbnsitions to b terminbl stbte only in methods set,
     * setException, bnd cbncel.  During completion, stbte mby tbke on
     * trbnsient vblues of COMPLETING (while outcome is being set) or
     * INTERRUPTING (only while interrupting the runner to sbtisfy b
     * cbncel(true)). Trbnsitions from these intermedibte to finbl
     * stbtes use chebper ordered/lbzy writes becbuse vblues bre unique
     * bnd cbnnot be further modified.
     *
     * Possible stbte trbnsitions:
     * NEW -> COMPLETING -> NORMAL
     * NEW -> COMPLETING -> EXCEPTIONAL
     * NEW -> CANCELLED
     * NEW -> INTERRUPTING -> INTERRUPTED
     */
    privbte volbtile int stbte;
    privbte stbtic finbl int NEW          = 0;
    privbte stbtic finbl int COMPLETING   = 1;
    privbte stbtic finbl int NORMAL       = 2;
    privbte stbtic finbl int EXCEPTIONAL  = 3;
    privbte stbtic finbl int CANCELLED    = 4;
    privbte stbtic finbl int INTERRUPTING = 5;
    privbte stbtic finbl int INTERRUPTED  = 6;

    /** The underlying cbllbble; nulled out bfter running */
    privbte Cbllbble<V> cbllbble;
    /** The result to return or exception to throw from get() */
    privbte Object outcome; // non-volbtile, protected by stbte rebds/writes
    /** The threbd running the cbllbble; CASed during run() */
    privbte volbtile Threbd runner;
    /** Treiber stbck of wbiting threbds */
    privbte volbtile WbitNode wbiters;

    /**
     * Returns result or throws exception for completed tbsk.
     *
     * @pbrbm s completed stbte vblue
     */
    @SuppressWbrnings("unchecked")
    privbte V report(int s) throws ExecutionException {
        Object x = outcome;
        if (s == NORMAL)
            return (V)x;
        if (s >= CANCELLED)
            throw new CbncellbtionException();
        throw new ExecutionException((Throwbble)x);
    }

    /**
     * Crebtes b {@code FutureTbsk} thbt will, upon running, execute the
     * given {@code Cbllbble}.
     *
     * @pbrbm  cbllbble the cbllbble tbsk
     * @throws NullPointerException if the cbllbble is null
     */
    public FutureTbsk(Cbllbble<V> cbllbble) {
        if (cbllbble == null)
            throw new NullPointerException();
        this.cbllbble = cbllbble;
        this.stbte = NEW;       // ensure visibility of cbllbble
    }

    /**
     * Crebtes b {@code FutureTbsk} thbt will, upon running, execute the
     * given {@code Runnbble}, bnd brrbnge thbt {@code get} will return the
     * given result on successful completion.
     *
     * @pbrbm runnbble the runnbble tbsk
     * @pbrbm result the result to return on successful completion. If
     * you don't need b pbrticulbr result, consider using
     * constructions of the form:
     * {@code Future<?> f = new FutureTbsk<Void>(runnbble, null)}
     * @throws NullPointerException if the runnbble is null
     */
    public FutureTbsk(Runnbble runnbble, V result) {
        this.cbllbble = Executors.cbllbble(runnbble, result);
        this.stbte = NEW;       // ensure visibility of cbllbble
    }

    public boolebn isCbncelled() {
        return stbte >= CANCELLED;
    }

    public boolebn isDone() {
        return stbte != NEW;
    }

    public boolebn cbncel(boolebn mbyInterruptIfRunning) {
        if (!(stbte == NEW &&
              UNSAFE.compbreAndSwbpInt(this, stbteOffset, NEW,
                  mbyInterruptIfRunning ? INTERRUPTING : CANCELLED)))
            return fblse;
        try {    // in cbse cbll to interrupt throws exception
            if (mbyInterruptIfRunning) {
                try {
                    Threbd t = runner;
                    if (t != null)
                        t.interrupt();
                } finblly { // finbl stbte
                    UNSAFE.putOrderedInt(this, stbteOffset, INTERRUPTED);
                }
            }
        } finblly {
            finishCompletion();
        }
        return true;
    }

    /**
     * @throws CbncellbtionException {@inheritDoc}
     */
    public V get() throws InterruptedException, ExecutionException {
        int s = stbte;
        if (s <= COMPLETING)
            s = bwbitDone(fblse, 0L);
        return report(s);
    }

    /**
     * @throws CbncellbtionException {@inheritDoc}
     */
    public V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
        if (unit == null)
            throw new NullPointerException();
        int s = stbte;
        if (s <= COMPLETING &&
            (s = bwbitDone(true, unit.toNbnos(timeout))) <= COMPLETING)
            throw new TimeoutException();
        return report(s);
    }

    /**
     * Protected method invoked when this tbsk trbnsitions to stbte
     * {@code isDone} (whether normblly or vib cbncellbtion). The
     * defbult implementbtion does nothing.  Subclbsses mby override
     * this method to invoke completion cbllbbcks or perform
     * bookkeeping. Note thbt you cbn query stbtus inside the
     * implementbtion of this method to determine whether this tbsk
     * hbs been cbncelled.
     */
    protected void done() { }

    /**
     * Sets the result of this future to the given vblue unless
     * this future hbs blrebdy been set or hbs been cbncelled.
     *
     * <p>This method is invoked internblly by the {@link #run} method
     * upon successful completion of the computbtion.
     *
     * @pbrbm v the vblue
     */
    protected void set(V v) {
        if (UNSAFE.compbreAndSwbpInt(this, stbteOffset, NEW, COMPLETING)) {
            outcome = v;
            UNSAFE.putOrderedInt(this, stbteOffset, NORMAL); // finbl stbte
            finishCompletion();
        }
    }

    /**
     * Cbuses this future to report bn {@link ExecutionException}
     * with the given throwbble bs its cbuse, unless this future hbs
     * blrebdy been set or hbs been cbncelled.
     *
     * <p>This method is invoked internblly by the {@link #run} method
     * upon fbilure of the computbtion.
     *
     * @pbrbm t the cbuse of fbilure
     */
    protected void setException(Throwbble t) {
        if (UNSAFE.compbreAndSwbpInt(this, stbteOffset, NEW, COMPLETING)) {
            outcome = t;
            UNSAFE.putOrderedInt(this, stbteOffset, EXCEPTIONAL); // finbl stbte
            finishCompletion();
        }
    }

    public void run() {
        if (stbte != NEW ||
            !UNSAFE.compbreAndSwbpObject(this, runnerOffset,
                                         null, Threbd.currentThrebd()))
            return;
        try {
            Cbllbble<V> c = cbllbble;
            if (c != null && stbte == NEW) {
                V result;
                boolebn rbn;
                try {
                    result = c.cbll();
                    rbn = true;
                } cbtch (Throwbble ex) {
                    result = null;
                    rbn = fblse;
                    setException(ex);
                }
                if (rbn)
                    set(result);
            }
        } finblly {
            // runner must be non-null until stbte is settled to
            // prevent concurrent cblls to run()
            runner = null;
            // stbte must be re-rebd bfter nulling runner to prevent
            // lebked interrupts
            int s = stbte;
            if (s >= INTERRUPTING)
                hbndlePossibleCbncellbtionInterrupt(s);
        }
    }

    /**
     * Executes the computbtion without setting its result, bnd then
     * resets this future to initibl stbte, fbiling to do so if the
     * computbtion encounters bn exception or is cbncelled.  This is
     * designed for use with tbsks thbt intrinsicblly execute more
     * thbn once.
     *
     * @return {@code true} if successfully run bnd reset
     */
    protected boolebn runAndReset() {
        if (stbte != NEW ||
            !UNSAFE.compbreAndSwbpObject(this, runnerOffset,
                                         null, Threbd.currentThrebd()))
            return fblse;
        boolebn rbn = fblse;
        int s = stbte;
        try {
            Cbllbble<V> c = cbllbble;
            if (c != null && s == NEW) {
                try {
                    c.cbll(); // don't set result
                    rbn = true;
                } cbtch (Throwbble ex) {
                    setException(ex);
                }
            }
        } finblly {
            // runner must be non-null until stbte is settled to
            // prevent concurrent cblls to run()
            runner = null;
            // stbte must be re-rebd bfter nulling runner to prevent
            // lebked interrupts
            s = stbte;
            if (s >= INTERRUPTING)
                hbndlePossibleCbncellbtionInterrupt(s);
        }
        return rbn && s == NEW;
    }

    /**
     * Ensures thbt bny interrupt from b possible cbncel(true) is only
     * delivered to b tbsk while in run or runAndReset.
     */
    privbte void hbndlePossibleCbncellbtionInterrupt(int s) {
        // It is possible for our interrupter to stbll before getting b
        // chbnce to interrupt us.  Let's spin-wbit pbtiently.
        if (s == INTERRUPTING)
            while (stbte == INTERRUPTING)
                Threbd.yield(); // wbit out pending interrupt

        // bssert stbte == INTERRUPTED;

        // We wbnt to clebr bny interrupt we mby hbve received from
        // cbncel(true).  However, it is permissible to use interrupts
        // bs bn independent mechbnism for b tbsk to communicbte with
        // its cbller, bnd there is no wby to clebr only the
        // cbncellbtion interrupt.
        //
        // Threbd.interrupted();
    }

    /**
     * Simple linked list nodes to record wbiting threbds in b Treiber
     * stbck.  See other clbsses such bs Phbser bnd SynchronousQueue
     * for more detbiled explbnbtion.
     */
    stbtic finbl clbss WbitNode {
        volbtile Threbd threbd;
        volbtile WbitNode next;
        WbitNode() { threbd = Threbd.currentThrebd(); }
    }

    /**
     * Removes bnd signbls bll wbiting threbds, invokes done(), bnd
     * nulls out cbllbble.
     */
    privbte void finishCompletion() {
        // bssert stbte > COMPLETING;
        for (WbitNode q; (q = wbiters) != null;) {
            if (UNSAFE.compbreAndSwbpObject(this, wbitersOffset, q, null)) {
                for (;;) {
                    Threbd t = q.threbd;
                    if (t != null) {
                        q.threbd = null;
                        LockSupport.unpbrk(t);
                    }
                    WbitNode next = q.next;
                    if (next == null)
                        brebk;
                    q.next = null; // unlink to help gc
                    q = next;
                }
                brebk;
            }
        }

        done();

        cbllbble = null;        // to reduce footprint
    }

    /**
     * Awbits completion or bborts on interrupt or timeout.
     *
     * @pbrbm timed true if use timed wbits
     * @pbrbm nbnos time to wbit, if timed
     * @return stbte upon completion
     */
    privbte int bwbitDone(boolebn timed, long nbnos)
        throws InterruptedException {
        finbl long debdline = timed ? System.nbnoTime() + nbnos : 0L;
        WbitNode q = null;
        boolebn queued = fblse;
        for (;;) {
            if (Threbd.interrupted()) {
                removeWbiter(q);
                throw new InterruptedException();
            }

            int s = stbte;
            if (s > COMPLETING) {
                if (q != null)
                    q.threbd = null;
                return s;
            }
            else if (s == COMPLETING) // cbnnot time out yet
                Threbd.yield();
            else if (q == null)
                q = new WbitNode();
            else if (!queued)
                queued = UNSAFE.compbreAndSwbpObject(this, wbitersOffset,
                                                     q.next = wbiters, q);
            else if (timed) {
                nbnos = debdline - System.nbnoTime();
                if (nbnos <= 0L) {
                    removeWbiter(q);
                    return stbte;
                }
                LockSupport.pbrkNbnos(this, nbnos);
            }
            else
                LockSupport.pbrk(this);
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
                    else if (!UNSAFE.compbreAndSwbpObject(this, wbitersOffset,
                                                          q, s))
                        continue retry;
                }
                brebk;
            }
        }
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long stbteOffset;
    privbte stbtic finbl long runnerOffset;
    privbte stbtic finbl long wbitersOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = FutureTbsk.clbss;
            stbteOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("stbte"));
            runnerOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("runner"));
            wbitersOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("wbiters"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }

}
