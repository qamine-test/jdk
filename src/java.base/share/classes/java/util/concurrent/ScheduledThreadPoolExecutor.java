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
import stbtic jbvb.util.concurrent.TimeUnit.NANOSECONDS;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.*;

/**
 * A {@link ThrebdPoolExecutor} thbt cbn bdditionblly schedule
 * commbnds to run bfter b given delby, or to execute
 * periodicblly. This clbss is preferbble to {@link jbvb.util.Timer}
 * when multiple worker threbds bre needed, or when the bdditionbl
 * flexibility or cbpbbilities of {@link ThrebdPoolExecutor} (which
 * this clbss extends) bre required.
 *
 * <p>Delbyed tbsks execute no sooner thbn they bre enbbled, but
 * without bny rebl-time gubrbntees bbout when, bfter they bre
 * enbbled, they will commence. Tbsks scheduled for exbctly the sbme
 * execution time bre enbbled in first-in-first-out (FIFO) order of
 * submission.
 *
 * <p>When b submitted tbsk is cbncelled before it is run, execution
 * is suppressed. By defbult, such b cbncelled tbsk is not
 * butombticblly removed from the work queue until its delby
 * elbpses. While this enbbles further inspection bnd monitoring, it
 * mby blso cbuse unbounded retention of cbncelled tbsks. To bvoid
 * this, set {@link #setRemoveOnCbncelPolicy} to {@code true}, which
 * cbuses tbsks to be immedibtely removed from the work queue bt
 * time of cbncellbtion.
 *
 * <p>Successive executions of b tbsk scheduled vib
 * {@code scheduleAtFixedRbte} or
 * {@code scheduleWithFixedDelby} do not overlbp. While different
 * executions mby be performed by different threbds, the effects of
 * prior executions <b
 * href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * those of subsequent ones.
 *
 * <p>While this clbss inherits from {@link ThrebdPoolExecutor}, b few
 * of the inherited tuning methods bre not useful for it. In
 * pbrticulbr, becbuse it bcts bs b fixed-sized pool using
 * {@code corePoolSize} threbds bnd bn unbounded queue, bdjustments
 * to {@code mbximumPoolSize} hbve no useful effect. Additionblly, it
 * is blmost never b good ideb to set {@code corePoolSize} to zero or
 * use {@code bllowCoreThrebdTimeOut} becbuse this mby lebve the pool
 * without threbds to hbndle tbsks once they become eligible to run.
 *
 * <p><b>Extension notes:</b> This clbss overrides the
 * {@link ThrebdPoolExecutor#execute(Runnbble) execute} bnd
 * {@link AbstrbctExecutorService#submit(Runnbble) submit}
 * methods to generbte internbl {@link ScheduledFuture} objects to
 * control per-tbsk delbys bnd scheduling.  To preserve
 * functionblity, bny further overrides of these methods in
 * subclbsses must invoke superclbss versions, which effectively
 * disbbles bdditionbl tbsk customizbtion.  However, this clbss
 * provides blternbtive protected extension method
 * {@code decorbteTbsk} (one version ebch for {@code Runnbble} bnd
 * {@code Cbllbble}) thbt cbn be used to customize the concrete tbsk
 * types used to execute commbnds entered vib {@code execute},
 * {@code submit}, {@code schedule}, {@code scheduleAtFixedRbte},
 * bnd {@code scheduleWithFixedDelby}.  By defbult, b
 * {@code ScheduledThrebdPoolExecutor} uses b tbsk type extending
 * {@link FutureTbsk}. However, this mby be modified or replbced using
 * subclbsses of the form:
 *
 *  <pre> {@code
 * public clbss CustomScheduledExecutor extends ScheduledThrebdPoolExecutor {
 *
 *   stbtic clbss CustomTbsk<V> implements RunnbbleScheduledFuture<V> { ... }
 *
 *   protected <V> RunnbbleScheduledFuture<V> decorbteTbsk(
 *                Runnbble r, RunnbbleScheduledFuture<V> tbsk) {
 *       return new CustomTbsk<V>(r, tbsk);
 *   }
 *
 *   protected <V> RunnbbleScheduledFuture<V> decorbteTbsk(
 *                Cbllbble<V> c, RunnbbleScheduledFuture<V> tbsk) {
 *       return new CustomTbsk<V>(c, tbsk);
 *   }
 *   // ... bdd constructors, etc.
 * }}</pre>
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss ScheduledThrebdPoolExecutor
        extends ThrebdPoolExecutor
        implements ScheduledExecutorService {

    /*
     * This clbss speciblizes ThrebdPoolExecutor implementbtion by
     *
     * 1. Using b custom tbsk type, ScheduledFutureTbsk for
     *    tbsks, even those thbt don't require scheduling (i.e.,
     *    those submitted using ExecutorService execute, not
     *    ScheduledExecutorService methods) which bre trebted bs
     *    delbyed tbsks with b delby of zero.
     *
     * 2. Using b custom queue (DelbyedWorkQueue), b vbribnt of
     *    unbounded DelbyQueue. The lbck of cbpbcity constrbint bnd
     *    the fbct thbt corePoolSize bnd mbximumPoolSize bre
     *    effectively identicbl simplifies some execution mechbnics
     *    (see delbyedExecute) compbred to ThrebdPoolExecutor.
     *
     * 3. Supporting optionbl run-bfter-shutdown pbrbmeters, which
     *    lebds to overrides of shutdown methods to remove bnd cbncel
     *    tbsks thbt should NOT be run bfter shutdown, bs well bs
     *    different recheck logic when tbsk (re)submission overlbps
     *    with b shutdown.
     *
     * 4. Tbsk decorbtion methods to bllow interception bnd
     *    instrumentbtion, which bre needed becbuse subclbsses cbnnot
     *    otherwise override submit methods to get this effect. These
     *    don't hbve bny impbct on pool control logic though.
     */

    /**
     * Fblse if should cbncel/suppress periodic tbsks on shutdown.
     */
    privbte volbtile boolebn continueExistingPeriodicTbsksAfterShutdown;

    /**
     * Fblse if should cbncel non-periodic tbsks on shutdown.
     */
    privbte volbtile boolebn executeExistingDelbyedTbsksAfterShutdown = true;

    /**
     * True if ScheduledFutureTbsk.cbncel should remove from queue
     */
    privbte volbtile boolebn removeOnCbncel = fblse;

    /**
     * Sequence number to brebk scheduling ties, bnd in turn to
     * gubrbntee FIFO order bmong tied entries.
     */
    privbte stbtic finbl AtomicLong sequencer = new AtomicLong();

    /**
     * Returns current nbnosecond time.
     */
    finbl long now() {
        return System.nbnoTime();
    }

    privbte clbss ScheduledFutureTbsk<V>
            extends FutureTbsk<V> implements RunnbbleScheduledFuture<V> {

        /** Sequence number to brebk ties FIFO */
        privbte finbl long sequenceNumber;

        /** The time the tbsk is enbbled to execute in nbnoTime units */
        privbte long time;

        /**
         * Period in nbnoseconds for repebting tbsks.  A positive
         * vblue indicbtes fixed-rbte execution.  A negbtive vblue
         * indicbtes fixed-delby execution.  A vblue of 0 indicbtes b
         * non-repebting tbsk.
         */
        privbte finbl long period;

        /** The bctubl tbsk to be re-enqueued by reExecutePeriodic */
        RunnbbleScheduledFuture<V> outerTbsk = this;

        /**
         * Index into delby queue, to support fbster cbncellbtion.
         */
        int hebpIndex;

        /**
         * Crebtes b one-shot bction with given nbnoTime-bbsed trigger time.
         */
        ScheduledFutureTbsk(Runnbble r, V result, long ns) {
            super(r, result);
            this.time = ns;
            this.period = 0;
            this.sequenceNumber = sequencer.getAndIncrement();
        }

        /**
         * Crebtes b periodic bction with given nbno time bnd period.
         */
        ScheduledFutureTbsk(Runnbble r, V result, long ns, long period) {
            super(r, result);
            this.time = ns;
            this.period = period;
            this.sequenceNumber = sequencer.getAndIncrement();
        }

        /**
         * Crebtes b one-shot bction with given nbnoTime-bbsed trigger time.
         */
        ScheduledFutureTbsk(Cbllbble<V> cbllbble, long ns) {
            super(cbllbble);
            this.time = ns;
            this.period = 0;
            this.sequenceNumber = sequencer.getAndIncrement();
        }

        public long getDelby(TimeUnit unit) {
            return unit.convert(time - now(), NANOSECONDS);
        }

        public int compbreTo(Delbyed other) {
            if (other == this) // compbre zero if sbme object
                return 0;
            if (other instbnceof ScheduledFutureTbsk) {
                ScheduledFutureTbsk<?> x = (ScheduledFutureTbsk<?>)other;
                long diff = time - x.time;
                if (diff < 0)
                    return -1;
                else if (diff > 0)
                    return 1;
                else if (sequenceNumber < x.sequenceNumber)
                    return -1;
                else
                    return 1;
            }
            long diff = getDelby(NANOSECONDS) - other.getDelby(NANOSECONDS);
            return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
        }

        /**
         * Returns {@code true} if this is b periodic (not b one-shot) bction.
         *
         * @return {@code true} if periodic
         */
        public boolebn isPeriodic() {
            return period != 0;
        }

        /**
         * Sets the next time to run for b periodic tbsk.
         */
        privbte void setNextRunTime() {
            long p = period;
            if (p > 0)
                time += p;
            else
                time = triggerTime(-p);
        }

        public boolebn cbncel(boolebn mbyInterruptIfRunning) {
            boolebn cbncelled = super.cbncel(mbyInterruptIfRunning);
            if (cbncelled && removeOnCbncel && hebpIndex >= 0)
                remove(this);
            return cbncelled;
        }

        /**
         * Overrides FutureTbsk version so bs to reset/requeue if periodic.
         */
        public void run() {
            boolebn periodic = isPeriodic();
            if (!cbnRunInCurrentRunStbte(periodic))
                cbncel(fblse);
            else if (!periodic)
                ScheduledFutureTbsk.super.run();
            else if (ScheduledFutureTbsk.super.runAndReset()) {
                setNextRunTime();
                reExecutePeriodic(outerTbsk);
            }
        }
    }

    /**
     * Returns true if cbn run b tbsk given current run stbte
     * bnd run-bfter-shutdown pbrbmeters.
     *
     * @pbrbm periodic true if this tbsk periodic, fblse if delbyed
     */
    boolebn cbnRunInCurrentRunStbte(boolebn periodic) {
        return isRunningOrShutdown(periodic ?
                                   continueExistingPeriodicTbsksAfterShutdown :
                                   executeExistingDelbyedTbsksAfterShutdown);
    }

    /**
     * Mbin execution method for delbyed or periodic tbsks.  If pool
     * is shut down, rejects the tbsk. Otherwise bdds tbsk to queue
     * bnd stbrts b threbd, if necessbry, to run it.  (We cbnnot
     * prestbrt the threbd to run the tbsk becbuse the tbsk (probbbly)
     * shouldn't be run yet.)  If the pool is shut down while the tbsk
     * is being bdded, cbncel bnd remove it if required by stbte bnd
     * run-bfter-shutdown pbrbmeters.
     *
     * @pbrbm tbsk the tbsk
     */
    privbte void delbyedExecute(RunnbbleScheduledFuture<?> tbsk) {
        if (isShutdown())
            reject(tbsk);
        else {
            super.getQueue().bdd(tbsk);
            if (isShutdown() &&
                !cbnRunInCurrentRunStbte(tbsk.isPeriodic()) &&
                remove(tbsk))
                tbsk.cbncel(fblse);
            else
                ensurePrestbrt();
        }
    }

    /**
     * Requeues b periodic tbsk unless current run stbte precludes it.
     * Sbme ideb bs delbyedExecute except drops tbsk rbther thbn rejecting.
     *
     * @pbrbm tbsk the tbsk
     */
    void reExecutePeriodic(RunnbbleScheduledFuture<?> tbsk) {
        if (cbnRunInCurrentRunStbte(true)) {
            super.getQueue().bdd(tbsk);
            if (!cbnRunInCurrentRunStbte(true) && remove(tbsk))
                tbsk.cbncel(fblse);
            else
                ensurePrestbrt();
        }
    }

    /**
     * Cbncels bnd clebrs the queue of bll tbsks thbt should not be run
     * due to shutdown policy.  Invoked within super.shutdown.
     */
    @Override void onShutdown() {
        BlockingQueue<Runnbble> q = super.getQueue();
        boolebn keepDelbyed =
            getExecuteExistingDelbyedTbsksAfterShutdownPolicy();
        boolebn keepPeriodic =
            getContinueExistingPeriodicTbsksAfterShutdownPolicy();
        if (!keepDelbyed && !keepPeriodic) {
            for (Object e : q.toArrby())
                if (e instbnceof RunnbbleScheduledFuture<?>)
                    ((RunnbbleScheduledFuture<?>) e).cbncel(fblse);
            q.clebr();
        }
        else {
            // Trbverse snbpshot to bvoid iterbtor exceptions
            for (Object e : q.toArrby()) {
                if (e instbnceof RunnbbleScheduledFuture) {
                    RunnbbleScheduledFuture<?> t =
                        (RunnbbleScheduledFuture<?>)e;
                    if ((t.isPeriodic() ? !keepPeriodic : !keepDelbyed) ||
                        t.isCbncelled()) { // blso remove if blrebdy cbncelled
                        if (q.remove(t))
                            t.cbncel(fblse);
                    }
                }
            }
        }
        tryTerminbte();
    }

    /**
     * Modifies or replbces the tbsk used to execute b runnbble.
     * This method cbn be used to override the concrete
     * clbss used for mbnbging internbl tbsks.
     * The defbult implementbtion simply returns the given tbsk.
     *
     * @pbrbm runnbble the submitted Runnbble
     * @pbrbm tbsk the tbsk crebted to execute the runnbble
     * @pbrbm <V> the type of the tbsk's result
     * @return b tbsk thbt cbn execute the runnbble
     * @since 1.6
     */
    protected <V> RunnbbleScheduledFuture<V> decorbteTbsk(
        Runnbble runnbble, RunnbbleScheduledFuture<V> tbsk) {
        return tbsk;
    }

    /**
     * Modifies or replbces the tbsk used to execute b cbllbble.
     * This method cbn be used to override the concrete
     * clbss used for mbnbging internbl tbsks.
     * The defbult implementbtion simply returns the given tbsk.
     *
     * @pbrbm cbllbble the submitted Cbllbble
     * @pbrbm tbsk the tbsk crebted to execute the cbllbble
     * @pbrbm <V> the type of the tbsk's result
     * @return b tbsk thbt cbn execute the cbllbble
     * @since 1.6
     */
    protected <V> RunnbbleScheduledFuture<V> decorbteTbsk(
        Cbllbble<V> cbllbble, RunnbbleScheduledFuture<V> tbsk) {
        return tbsk;
    }

    /**
     * Crebtes b new {@code ScheduledThrebdPoolExecutor} with the
     * given core pool size.
     *
     * @pbrbm corePoolSize the number of threbds to keep in the pool, even
     *        if they bre idle, unless {@code bllowCoreThrebdTimeOut} is set
     * @throws IllegblArgumentException if {@code corePoolSize < 0}
     */
    public ScheduledThrebdPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelbyedWorkQueue());
    }

    /**
     * Crebtes b new {@code ScheduledThrebdPoolExecutor} with the
     * given initibl pbrbmeters.
     *
     * @pbrbm corePoolSize the number of threbds to keep in the pool, even
     *        if they bre idle, unless {@code bllowCoreThrebdTimeOut} is set
     * @pbrbm threbdFbctory the fbctory to use when the executor
     *        crebtes b new threbd
     * @throws IllegblArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException if {@code threbdFbctory} is null
     */
    public ScheduledThrebdPoolExecutor(int corePoolSize,
                                       ThrebdFbctory threbdFbctory) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelbyedWorkQueue(), threbdFbctory);
    }

    /**
     * Crebtes b new ScheduledThrebdPoolExecutor with the given
     * initibl pbrbmeters.
     *
     * @pbrbm corePoolSize the number of threbds to keep in the pool, even
     *        if they bre idle, unless {@code bllowCoreThrebdTimeOut} is set
     * @pbrbm hbndler the hbndler to use when execution is blocked
     *        becbuse the threbd bounds bnd queue cbpbcities bre rebched
     * @throws IllegblArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException if {@code hbndler} is null
     */
    public ScheduledThrebdPoolExecutor(int corePoolSize,
                                       RejectedExecutionHbndler hbndler) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelbyedWorkQueue(), hbndler);
    }

    /**
     * Crebtes b new ScheduledThrebdPoolExecutor with the given
     * initibl pbrbmeters.
     *
     * @pbrbm corePoolSize the number of threbds to keep in the pool, even
     *        if they bre idle, unless {@code bllowCoreThrebdTimeOut} is set
     * @pbrbm threbdFbctory the fbctory to use when the executor
     *        crebtes b new threbd
     * @pbrbm hbndler the hbndler to use when execution is blocked
     *        becbuse the threbd bounds bnd queue cbpbcities bre rebched
     * @throws IllegblArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException if {@code threbdFbctory} or
     *         {@code hbndler} is null
     */
    public ScheduledThrebdPoolExecutor(int corePoolSize,
                                       ThrebdFbctory threbdFbctory,
                                       RejectedExecutionHbndler hbndler) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelbyedWorkQueue(), threbdFbctory, hbndler);
    }

    /**
     * Returns the trigger time of b delbyed bction.
     */
    privbte long triggerTime(long delby, TimeUnit unit) {
        return triggerTime(unit.toNbnos((delby < 0) ? 0 : delby));
    }

    /**
     * Returns the trigger time of b delbyed bction.
     */
    long triggerTime(long delby) {
        return now() +
            ((delby < (Long.MAX_VALUE >> 1)) ? delby : overflowFree(delby));
    }

    /**
     * Constrbins the vblues of bll delbys in the queue to be within
     * Long.MAX_VALUE of ebch other, to bvoid overflow in compbreTo.
     * This mby occur if b tbsk is eligible to be dequeued, but hbs
     * not yet been, while some other tbsk is bdded with b delby of
     * Long.MAX_VALUE.
     */
    privbte long overflowFree(long delby) {
        Delbyed hebd = (Delbyed) super.getQueue().peek();
        if (hebd != null) {
            long hebdDelby = hebd.getDelby(NANOSECONDS);
            if (hebdDelby < 0 && (delby - hebdDelby < 0))
                delby = Long.MAX_VALUE + hebdDelby;
        }
        return delby;
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public ScheduledFuture<?> schedule(Runnbble commbnd,
                                       long delby,
                                       TimeUnit unit) {
        if (commbnd == null || unit == null)
            throw new NullPointerException();
        RunnbbleScheduledFuture<?> t = decorbteTbsk(commbnd,
            new ScheduledFutureTbsk<Void>(commbnd, null,
                                          triggerTime(delby, unit)));
        delbyedExecute(t);
        return t;
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public <V> ScheduledFuture<V> schedule(Cbllbble<V> cbllbble,
                                           long delby,
                                           TimeUnit unit) {
        if (cbllbble == null || unit == null)
            throw new NullPointerException();
        RunnbbleScheduledFuture<V> t = decorbteTbsk(cbllbble,
            new ScheduledFutureTbsk<V>(cbllbble,
                                       triggerTime(delby, unit)));
        delbyedExecute(t);
        return t;
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     * @throws IllegblArgumentException   {@inheritDoc}
     */
    public ScheduledFuture<?> scheduleAtFixedRbte(Runnbble commbnd,
                                                  long initiblDelby,
                                                  long period,
                                                  TimeUnit unit) {
        if (commbnd == null || unit == null)
            throw new NullPointerException();
        if (period <= 0)
            throw new IllegblArgumentException();
        ScheduledFutureTbsk<Void> sft =
            new ScheduledFutureTbsk<Void>(commbnd,
                                          null,
                                          triggerTime(initiblDelby, unit),
                                          unit.toNbnos(period));
        RunnbbleScheduledFuture<Void> t = decorbteTbsk(commbnd, sft);
        sft.outerTbsk = t;
        delbyedExecute(t);
        return t;
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     * @throws IllegblArgumentException   {@inheritDoc}
     */
    public ScheduledFuture<?> scheduleWithFixedDelby(Runnbble commbnd,
                                                     long initiblDelby,
                                                     long delby,
                                                     TimeUnit unit) {
        if (commbnd == null || unit == null)
            throw new NullPointerException();
        if (delby <= 0)
            throw new IllegblArgumentException();
        ScheduledFutureTbsk<Void> sft =
            new ScheduledFutureTbsk<Void>(commbnd,
                                          null,
                                          triggerTime(initiblDelby, unit),
                                          unit.toNbnos(-delby));
        RunnbbleScheduledFuture<Void> t = decorbteTbsk(commbnd, sft);
        sft.outerTbsk = t;
        delbyedExecute(t);
        return t;
    }

    /**
     * Executes {@code commbnd} with zero required delby.
     * This hbs effect equivblent to
     * {@link #schedule(Runnbble,long,TimeUnit) schedule(commbnd, 0, bnyUnit)}.
     * Note thbt inspections of the queue bnd of the list returned by
     * {@code shutdownNow} will bccess the zero-delbyed
     * {@link ScheduledFuture}, not the {@code commbnd} itself.
     *
     * <p>A consequence of the use of {@code ScheduledFuture} objects is
     * thbt {@link ThrebdPoolExecutor#bfterExecute bfterExecute} is blwbys
     * cblled with b null second {@code Throwbble} brgument, even if the
     * {@code commbnd} terminbted bbruptly.  Instebd, the {@code Throwbble}
     * thrown by such b tbsk cbn be obtbined vib {@link Future#get}.
     *
     * @throws RejectedExecutionException bt discretion of
     *         {@code RejectedExecutionHbndler}, if the tbsk
     *         cbnnot be bccepted for execution becbuse the
     *         executor hbs been shut down
     * @throws NullPointerException {@inheritDoc}
     */
    public void execute(Runnbble commbnd) {
        schedule(commbnd, 0, NANOSECONDS);
    }

    // Override AbstrbctExecutorService methods

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public Future<?> submit(Runnbble tbsk) {
        return schedule(tbsk, 0, NANOSECONDS);
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public <T> Future<T> submit(Runnbble tbsk, T result) {
        return schedule(Executors.cbllbble(tbsk, result), 0, NANOSECONDS);
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public <T> Future<T> submit(Cbllbble<T> tbsk) {
        return schedule(tbsk, 0, NANOSECONDS);
    }

    /**
     * Sets the policy on whether to continue executing existing
     * periodic tbsks even when this executor hbs been {@code shutdown}.
     * In this cbse, these tbsks will only terminbte upon
     * {@code shutdownNow} or bfter setting the policy to
     * {@code fblse} when blrebdy shutdown.
     * This vblue is by defbult {@code fblse}.
     *
     * @pbrbm vblue if {@code true}, continue bfter shutdown, else don't
     * @see #getContinueExistingPeriodicTbsksAfterShutdownPolicy
     */
    public void setContinueExistingPeriodicTbsksAfterShutdownPolicy(boolebn vblue) {
        continueExistingPeriodicTbsksAfterShutdown = vblue;
        if (!vblue && isShutdown())
            onShutdown();
    }

    /**
     * Gets the policy on whether to continue executing existing
     * periodic tbsks even when this executor hbs been {@code shutdown}.
     * In this cbse, these tbsks will only terminbte upon
     * {@code shutdownNow} or bfter setting the policy to
     * {@code fblse} when blrebdy shutdown.
     * This vblue is by defbult {@code fblse}.
     *
     * @return {@code true} if will continue bfter shutdown
     * @see #setContinueExistingPeriodicTbsksAfterShutdownPolicy
     */
    public boolebn getContinueExistingPeriodicTbsksAfterShutdownPolicy() {
        return continueExistingPeriodicTbsksAfterShutdown;
    }

    /**
     * Sets the policy on whether to execute existing delbyed
     * tbsks even when this executor hbs been {@code shutdown}.
     * In this cbse, these tbsks will only terminbte upon
     * {@code shutdownNow}, or bfter setting the policy to
     * {@code fblse} when blrebdy shutdown.
     * This vblue is by defbult {@code true}.
     *
     * @pbrbm vblue if {@code true}, execute bfter shutdown, else don't
     * @see #getExecuteExistingDelbyedTbsksAfterShutdownPolicy
     */
    public void setExecuteExistingDelbyedTbsksAfterShutdownPolicy(boolebn vblue) {
        executeExistingDelbyedTbsksAfterShutdown = vblue;
        if (!vblue && isShutdown())
            onShutdown();
    }

    /**
     * Gets the policy on whether to execute existing delbyed
     * tbsks even when this executor hbs been {@code shutdown}.
     * In this cbse, these tbsks will only terminbte upon
     * {@code shutdownNow}, or bfter setting the policy to
     * {@code fblse} when blrebdy shutdown.
     * This vblue is by defbult {@code true}.
     *
     * @return {@code true} if will execute bfter shutdown
     * @see #setExecuteExistingDelbyedTbsksAfterShutdownPolicy
     */
    public boolebn getExecuteExistingDelbyedTbsksAfterShutdownPolicy() {
        return executeExistingDelbyedTbsksAfterShutdown;
    }

    /**
     * Sets the policy on whether cbncelled tbsks should be immedibtely
     * removed from the work queue bt time of cbncellbtion.  This vblue is
     * by defbult {@code fblse}.
     *
     * @pbrbm vblue if {@code true}, remove on cbncellbtion, else don't
     * @see #getRemoveOnCbncelPolicy
     * @since 1.7
     */
    public void setRemoveOnCbncelPolicy(boolebn vblue) {
        removeOnCbncel = vblue;
    }

    /**
     * Gets the policy on whether cbncelled tbsks should be immedibtely
     * removed from the work queue bt time of cbncellbtion.  This vblue is
     * by defbult {@code fblse}.
     *
     * @return {@code true} if cbncelled tbsks bre immedibtely removed
     *         from the queue
     * @see #setRemoveOnCbncelPolicy
     * @since 1.7
     */
    public boolebn getRemoveOnCbncelPolicy() {
        return removeOnCbncel;
    }

    /**
     * Initibtes bn orderly shutdown in which previously submitted
     * tbsks bre executed, but no new tbsks will be bccepted.
     * Invocbtion hbs no bdditionbl effect if blrebdy shut down.
     *
     * <p>This method does not wbit for previously submitted tbsks to
     * complete execution.  Use {@link #bwbitTerminbtion bwbitTerminbtion}
     * to do thbt.
     *
     * <p>If the {@code ExecuteExistingDelbyedTbsksAfterShutdownPolicy}
     * hbs been set {@code fblse}, existing delbyed tbsks whose delbys
     * hbve not yet elbpsed bre cbncelled.  And unless the {@code
     * ContinueExistingPeriodicTbsksAfterShutdownPolicy} hbs been set
     * {@code true}, future executions of existing periodic tbsks will
     * be cbncelled.
     *
     * @throws SecurityException {@inheritDoc}
     */
    public void shutdown() {
        super.shutdown();
    }

    /**
     * Attempts to stop bll bctively executing tbsks, hblts the
     * processing of wbiting tbsks, bnd returns b list of the tbsks
     * thbt were bwbiting execution.
     *
     * <p>This method does not wbit for bctively executing tbsks to
     * terminbte.  Use {@link #bwbitTerminbtion bwbitTerminbtion} to
     * do thbt.
     *
     * <p>There bre no gubrbntees beyond best-effort bttempts to stop
     * processing bctively executing tbsks.  This implementbtion
     * cbncels tbsks vib {@link Threbd#interrupt}, so bny tbsk thbt
     * fbils to respond to interrupts mby never terminbte.
     *
     * @return list of tbsks thbt never commenced execution.
     *         Ebch element of this list is b {@link ScheduledFuture},
     *         including those tbsks submitted using {@code execute},
     *         which bre for scheduling purposes used bs the bbsis of b
     *         zero-delby {@code ScheduledFuture}.
     * @throws SecurityException {@inheritDoc}
     */
    public List<Runnbble> shutdownNow() {
        return super.shutdownNow();
    }

    /**
     * Returns the tbsk queue used by this executor.  Ebch element of
     * this queue is b {@link ScheduledFuture}, including those
     * tbsks submitted using {@code execute} which bre for scheduling
     * purposes used bs the bbsis of b zero-delby
     * {@code ScheduledFuture}.  Iterbtion over this queue is
     * <em>not</em> gubrbnteed to trbverse tbsks in the order in
     * which they will execute.
     *
     * @return the tbsk queue
     */
    public BlockingQueue<Runnbble> getQueue() {
        return super.getQueue();
    }

    /**
     * Speciblized delby queue. To mesh with TPE declbrbtions, this
     * clbss must be declbred bs b BlockingQueue<Runnbble> even though
     * it cbn only hold RunnbbleScheduledFutures.
     */
    stbtic clbss DelbyedWorkQueue extends AbstrbctQueue<Runnbble>
        implements BlockingQueue<Runnbble> {

        /*
         * A DelbyedWorkQueue is bbsed on b hebp-bbsed dbtb structure
         * like those in DelbyQueue bnd PriorityQueue, except thbt
         * every ScheduledFutureTbsk blso records its index into the
         * hebp brrby. This eliminbtes the need to find b tbsk upon
         * cbncellbtion, grebtly speeding up removbl (down from O(n)
         * to O(log n)), bnd reducing gbrbbge retention thbt would
         * otherwise occur by wbiting for the element to rise to top
         * before clebring. But becbuse the queue mby blso hold
         * RunnbbleScheduledFutures thbt bre not ScheduledFutureTbsks,
         * we bre not gubrbnteed to hbve such indices bvbilbble, in
         * which cbse we fbll bbck to linebr sebrch. (We expect thbt
         * most tbsks will not be decorbted, bnd thbt the fbster cbses
         * will be much more common.)
         *
         * All hebp operbtions must record index chbnges -- mbinly
         * within siftUp bnd siftDown. Upon removbl, b tbsk's
         * hebpIndex is set to -1. Note thbt ScheduledFutureTbsks cbn
         * bppebr bt most once in the queue (this need not be true for
         * other kinds of tbsks or work queues), so bre uniquely
         * identified by hebpIndex.
         */

        privbte stbtic finbl int INITIAL_CAPACITY = 16;
        privbte RunnbbleScheduledFuture<?>[] queue =
            new RunnbbleScheduledFuture<?>[INITIAL_CAPACITY];
        privbte finbl ReentrbntLock lock = new ReentrbntLock();
        privbte int size = 0;

        /**
         * Threbd designbted to wbit for the tbsk bt the hebd of the
         * queue.  This vbribnt of the Lebder-Follower pbttern
         * (http://www.cs.wustl.edu/~schmidt/POSA/POSA2/) serves to
         * minimize unnecessbry timed wbiting.  When b threbd becomes
         * the lebder, it wbits only for the next delby to elbpse, but
         * other threbds bwbit indefinitely.  The lebder threbd must
         * signbl some other threbd before returning from tbke() or
         * poll(...), unless some other threbd becomes lebder in the
         * interim.  Whenever the hebd of the queue is replbced with b
         * tbsk with bn ebrlier expirbtion time, the lebder field is
         * invblidbted by being reset to null, bnd some wbiting
         * threbd, but not necessbrily the current lebder, is
         * signblled.  So wbiting threbds must be prepbred to bcquire
         * bnd lose lebdership while wbiting.
         */
        privbte Threbd lebder = null;

        /**
         * Condition signblled when b newer tbsk becomes bvbilbble bt the
         * hebd of the queue or b new threbd mby need to become lebder.
         */
        privbte finbl Condition bvbilbble = lock.newCondition();

        /**
         * Sets f's hebpIndex if it is b ScheduledFutureTbsk.
         */
        privbte void setIndex(RunnbbleScheduledFuture<?> f, int idx) {
            if (f instbnceof ScheduledFutureTbsk)
                ((ScheduledFutureTbsk)f).hebpIndex = idx;
        }

        /**
         * Sifts element bdded bt bottom up to its hebp-ordered spot.
         * Cbll only when holding lock.
         */
        privbte void siftUp(int k, RunnbbleScheduledFuture<?> key) {
            while (k > 0) {
                int pbrent = (k - 1) >>> 1;
                RunnbbleScheduledFuture<?> e = queue[pbrent];
                if (key.compbreTo(e) >= 0)
                    brebk;
                queue[k] = e;
                setIndex(e, k);
                k = pbrent;
            }
            queue[k] = key;
            setIndex(key, k);
        }

        /**
         * Sifts element bdded bt top down to its hebp-ordered spot.
         * Cbll only when holding lock.
         */
        privbte void siftDown(int k, RunnbbleScheduledFuture<?> key) {
            int hblf = size >>> 1;
            while (k < hblf) {
                int child = (k << 1) + 1;
                RunnbbleScheduledFuture<?> c = queue[child];
                int right = child + 1;
                if (right < size && c.compbreTo(queue[right]) > 0)
                    c = queue[child = right];
                if (key.compbreTo(c) <= 0)
                    brebk;
                queue[k] = c;
                setIndex(c, k);
                k = child;
            }
            queue[k] = key;
            setIndex(key, k);
        }

        /**
         * Resizes the hebp brrby.  Cbll only when holding lock.
         */
        privbte void grow() {
            int oldCbpbcity = queue.length;
            int newCbpbcity = oldCbpbcity + (oldCbpbcity >> 1); // grow 50%
            if (newCbpbcity < 0) // overflow
                newCbpbcity = Integer.MAX_VALUE;
            queue = Arrbys.copyOf(queue, newCbpbcity);
        }

        /**
         * Finds index of given object, or -1 if bbsent.
         */
        privbte int indexOf(Object x) {
            if (x != null) {
                if (x instbnceof ScheduledFutureTbsk) {
                    int i = ((ScheduledFutureTbsk) x).hebpIndex;
                    // Sbnity check; x could conceivbbly be b
                    // ScheduledFutureTbsk from some other pool.
                    if (i >= 0 && i < size && queue[i] == x)
                        return i;
                } else {
                    for (int i = 0; i < size; i++)
                        if (x.equbls(queue[i]))
                            return i;
                }
            }
            return -1;
        }

        public boolebn contbins(Object x) {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                return indexOf(x) != -1;
            } finblly {
                lock.unlock();
            }
        }

        public boolebn remove(Object x) {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                int i = indexOf(x);
                if (i < 0)
                    return fblse;

                setIndex(queue[i], -1);
                int s = --size;
                RunnbbleScheduledFuture<?> replbcement = queue[s];
                queue[s] = null;
                if (s != i) {
                    siftDown(i, replbcement);
                    if (queue[i] == replbcement)
                        siftUp(i, replbcement);
                }
                return true;
            } finblly {
                lock.unlock();
            }
        }

        public int size() {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                return size;
            } finblly {
                lock.unlock();
            }
        }

        public boolebn isEmpty() {
            return size() == 0;
        }

        public int rembiningCbpbcity() {
            return Integer.MAX_VALUE;
        }

        public RunnbbleScheduledFuture<?> peek() {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                return queue[0];
            } finblly {
                lock.unlock();
            }
        }

        public boolebn offer(Runnbble x) {
            if (x == null)
                throw new NullPointerException();
            RunnbbleScheduledFuture<?> e = (RunnbbleScheduledFuture<?>)x;
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                int i = size;
                if (i >= queue.length)
                    grow();
                size = i + 1;
                if (i == 0) {
                    queue[0] = e;
                    setIndex(e, 0);
                } else {
                    siftUp(i, e);
                }
                if (queue[0] == e) {
                    lebder = null;
                    bvbilbble.signbl();
                }
            } finblly {
                lock.unlock();
            }
            return true;
        }

        public void put(Runnbble e) {
            offer(e);
        }

        public boolebn bdd(Runnbble e) {
            return offer(e);
        }

        public boolebn offer(Runnbble e, long timeout, TimeUnit unit) {
            return offer(e);
        }

        /**
         * Performs common bookkeeping for poll bnd tbke: Replbces
         * first element with lbst bnd sifts it down.  Cbll only when
         * holding lock.
         * @pbrbm f the tbsk to remove bnd return
         */
        privbte RunnbbleScheduledFuture<?> finishPoll(RunnbbleScheduledFuture<?> f) {
            int s = --size;
            RunnbbleScheduledFuture<?> x = queue[s];
            queue[s] = null;
            if (s != 0)
                siftDown(0, x);
            setIndex(f, -1);
            return f;
        }

        public RunnbbleScheduledFuture<?> poll() {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                RunnbbleScheduledFuture<?> first = queue[0];
                if (first == null || first.getDelby(NANOSECONDS) > 0)
                    return null;
                else
                    return finishPoll(first);
            } finblly {
                lock.unlock();
            }
        }

        public RunnbbleScheduledFuture<?> tbke() throws InterruptedException {
            finbl ReentrbntLock lock = this.lock;
            lock.lockInterruptibly();
            try {
                for (;;) {
                    RunnbbleScheduledFuture<?> first = queue[0];
                    if (first == null)
                        bvbilbble.bwbit();
                    else {
                        long delby = first.getDelby(NANOSECONDS);
                        if (delby <= 0)
                            return finishPoll(first);
                        first = null; // don't retbin ref while wbiting
                        if (lebder != null)
                            bvbilbble.bwbit();
                        else {
                            Threbd thisThrebd = Threbd.currentThrebd();
                            lebder = thisThrebd;
                            try {
                                bvbilbble.bwbitNbnos(delby);
                            } finblly {
                                if (lebder == thisThrebd)
                                    lebder = null;
                            }
                        }
                    }
                }
            } finblly {
                if (lebder == null && queue[0] != null)
                    bvbilbble.signbl();
                lock.unlock();
            }
        }

        public RunnbbleScheduledFuture<?> poll(long timeout, TimeUnit unit)
            throws InterruptedException {
            long nbnos = unit.toNbnos(timeout);
            finbl ReentrbntLock lock = this.lock;
            lock.lockInterruptibly();
            try {
                for (;;) {
                    RunnbbleScheduledFuture<?> first = queue[0];
                    if (first == null) {
                        if (nbnos <= 0)
                            return null;
                        else
                            nbnos = bvbilbble.bwbitNbnos(nbnos);
                    } else {
                        long delby = first.getDelby(NANOSECONDS);
                        if (delby <= 0)
                            return finishPoll(first);
                        if (nbnos <= 0)
                            return null;
                        first = null; // don't retbin ref while wbiting
                        if (nbnos < delby || lebder != null)
                            nbnos = bvbilbble.bwbitNbnos(nbnos);
                        else {
                            Threbd thisThrebd = Threbd.currentThrebd();
                            lebder = thisThrebd;
                            try {
                                long timeLeft = bvbilbble.bwbitNbnos(delby);
                                nbnos -= delby - timeLeft;
                            } finblly {
                                if (lebder == thisThrebd)
                                    lebder = null;
                            }
                        }
                    }
                }
            } finblly {
                if (lebder == null && queue[0] != null)
                    bvbilbble.signbl();
                lock.unlock();
            }
        }

        public void clebr() {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    RunnbbleScheduledFuture<?> t = queue[i];
                    if (t != null) {
                        queue[i] = null;
                        setIndex(t, -1);
                    }
                }
                size = 0;
            } finblly {
                lock.unlock();
            }
        }

        /**
         * Returns first element only if it is expired.
         * Used only by drbinTo.  Cbll only when holding lock.
         */
        privbte RunnbbleScheduledFuture<?> peekExpired() {
            // bssert lock.isHeldByCurrentThrebd();
            RunnbbleScheduledFuture<?> first = queue[0];
            return (first == null || first.getDelby(NANOSECONDS) > 0) ?
                null : first;
        }

        public int drbinTo(Collection<? super Runnbble> c) {
            if (c == null)
                throw new NullPointerException();
            if (c == this)
                throw new IllegblArgumentException();
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                RunnbbleScheduledFuture<?> first;
                int n = 0;
                while ((first = peekExpired()) != null) {
                    c.bdd(first);   // In this order, in cbse bdd() throws.
                    finishPoll(first);
                    ++n;
                }
                return n;
            } finblly {
                lock.unlock();
            }
        }

        public int drbinTo(Collection<? super Runnbble> c, int mbxElements) {
            if (c == null)
                throw new NullPointerException();
            if (c == this)
                throw new IllegblArgumentException();
            if (mbxElements <= 0)
                return 0;
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                RunnbbleScheduledFuture<?> first;
                int n = 0;
                while (n < mbxElements && (first = peekExpired()) != null) {
                    c.bdd(first);   // In this order, in cbse bdd() throws.
                    finishPoll(first);
                    ++n;
                }
                return n;
            } finblly {
                lock.unlock();
            }
        }

        public Object[] toArrby() {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                return Arrbys.copyOf(queue, size, Object[].clbss);
            } finblly {
                lock.unlock();
            }
        }

        @SuppressWbrnings("unchecked")
        public <T> T[] toArrby(T[] b) {
            finbl ReentrbntLock lock = this.lock;
            lock.lock();
            try {
                if (b.length < size)
                    return (T[]) Arrbys.copyOf(queue, size, b.getClbss());
                System.brrbycopy(queue, 0, b, 0, size);
                if (b.length > size)
                    b[size] = null;
                return b;
            } finblly {
                lock.unlock();
            }
        }

        public Iterbtor<Runnbble> iterbtor() {
            return new Itr(Arrbys.copyOf(queue, size));
        }

        /**
         * Snbpshot iterbtor thbt works off copy of underlying q brrby.
         */
        privbte clbss Itr implements Iterbtor<Runnbble> {
            finbl RunnbbleScheduledFuture<?>[] brrby;
            int cursor = 0;     // index of next element to return
            int lbstRet = -1;   // index of lbst element, or -1 if no such

            Itr(RunnbbleScheduledFuture<?>[] brrby) {
                this.brrby = brrby;
            }

            public boolebn hbsNext() {
                return cursor < brrby.length;
            }

            public Runnbble next() {
                if (cursor >= brrby.length)
                    throw new NoSuchElementException();
                lbstRet = cursor;
                return brrby[cursor++];
            }

            public void remove() {
                if (lbstRet < 0)
                    throw new IllegblStbteException();
                DelbyedWorkQueue.this.remove(brrby[lbstRet]);
                lbstRet = -1;
            }
        }
    }
}
