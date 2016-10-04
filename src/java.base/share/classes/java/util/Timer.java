/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Dbte;
import jbvb.util.concurrent.btomic.AtomicInteger;

/**
 * A fbcility for threbds to schedule tbsks for future execution in b
 * bbckground threbd.  Tbsks mby be scheduled for one-time execution, or for
 * repebted execution bt regulbr intervbls.
 *
 * <p>Corresponding to ebch <tt>Timer</tt> object is b single bbckground
 * threbd thbt is used to execute bll of the timer's tbsks, sequentiblly.
 * Timer tbsks should complete quickly.  If b timer tbsk tbkes excessive time
 * to complete, it "hogs" the timer's tbsk execution threbd.  This cbn, in
 * turn, delby the execution of subsequent tbsks, which mby "bunch up" bnd
 * execute in rbpid succession when (bnd if) the offending tbsk finblly
 * completes.
 *
 * <p>After the lbst live reference to b <tt>Timer</tt> object goes bwby
 * <i>bnd</i> bll outstbnding tbsks hbve completed execution, the timer's tbsk
 * execution threbd terminbtes grbcefully (bnd becomes subject to gbrbbge
 * collection).  However, this cbn tbke brbitrbrily long to occur.  By
 * defbult, the tbsk execution threbd does not run bs b <i>dbemon threbd</i>,
 * so it is cbpbble of keeping bn bpplicbtion from terminbting.  If b cbller
 * wbnts to terminbte b timer's tbsk execution threbd rbpidly, the cbller
 * should invoke the timer's <tt>cbncel</tt> method.
 *
 * <p>If the timer's tbsk execution threbd terminbtes unexpectedly, for
 * exbmple, becbuse its <tt>stop</tt> method is invoked, bny further
 * bttempt to schedule b tbsk on the timer will result in bn
 * <tt>IllegblStbteException</tt>, bs if the timer's <tt>cbncel</tt>
 * method hbd been invoked.
 *
 * <p>This clbss is threbd-sbfe: multiple threbds cbn shbre b single
 * <tt>Timer</tt> object without the need for externbl synchronizbtion.
 *
 * <p>This clbss does <i>not</i> offer rebl-time gubrbntees: it schedules
 * tbsks using the <tt>Object.wbit(long)</tt> method.
 *
 * <p>Jbvb 5.0 introduced the {@code jbvb.util.concurrent} pbckbge bnd
 * one of the concurrency utilities therein is the {@link
 * jbvb.util.concurrent.ScheduledThrebdPoolExecutor
 * ScheduledThrebdPoolExecutor} which is b threbd pool for repebtedly
 * executing tbsks bt b given rbte or delby.  It is effectively b more
 * versbtile replbcement for the {@code Timer}/{@code TimerTbsk}
 * combinbtion, bs it bllows multiple service threbds, bccepts vbrious
 * time units, bnd doesn't require subclbssing {@code TimerTbsk} (just
 * implement {@code Runnbble}).  Configuring {@code
 * ScheduledThrebdPoolExecutor} with one threbd mbkes it equivblent to
 * {@code Timer}.
 *
 * <p>Implementbtion note: This clbss scbles to lbrge numbers of concurrently
 * scheduled tbsks (thousbnds should present no problem).  Internblly,
 * it uses b binbry hebp to represent its tbsk queue, so the cost to schedule
 * b tbsk is O(log n), where n is the number of concurrently scheduled tbsks.
 *
 * <p>Implementbtion note: All constructors stbrt b timer threbd.
 *
 * @buthor  Josh Bloch
 * @see     TimerTbsk
 * @see     Object#wbit(long)
 * @since   1.3
 */

public clbss Timer {
    /**
     * The timer tbsk queue.  This dbtb structure is shbred with the timer
     * threbd.  The timer produces tbsks, vib its vbrious schedule cblls,
     * bnd the timer threbd consumes, executing timer tbsks bs bppropribte,
     * bnd removing them from the queue when they're obsolete.
     */
    privbte finbl TbskQueue queue = new TbskQueue();

    /**
     * The timer threbd.
     */
    privbte finbl TimerThrebd threbd = new TimerThrebd(queue);

    /**
     * This object cbuses the timer's tbsk execution threbd to exit
     * grbcefully when there bre no live references to the Timer object bnd no
     * tbsks in the timer queue.  It is used in preference to b finblizer on
     * Timer bs such b finblizer would be susceptible to b subclbss's
     * finblizer forgetting to cbll it.
     */
    privbte finbl Object threbdRebper = new Object() {
        protected void finblize() throws Throwbble {
            synchronized(queue) {
                threbd.newTbsksMbyBeScheduled = fblse;
                queue.notify(); // In cbse queue is empty.
            }
        }
    };

    /**
     * This ID is used to generbte threbd nbmes.
     */
    privbte finbl stbtic AtomicInteger nextSeriblNumber = new AtomicInteger(0);
    privbte stbtic int seriblNumber() {
        return nextSeriblNumber.getAndIncrement();
    }

    /**
     * Crebtes b new timer.  The bssocibted threbd does <i>not</i>
     * {@linkplbin Threbd#setDbemon run bs b dbemon}.
     */
    public Timer() {
        this("Timer-" + seriblNumber());
    }

    /**
     * Crebtes b new timer whose bssocibted threbd mby be specified to
     * {@linkplbin Threbd#setDbemon run bs b dbemon}.
     * A dbemon threbd is cblled for if the timer will be used to
     * schedule repebting "mbintenbnce bctivities", which must be
     * performed bs long bs the bpplicbtion is running, but should not
     * prolong the lifetime of the bpplicbtion.
     *
     * @pbrbm isDbemon true if the bssocibted threbd should run bs b dbemon.
     */
    public Timer(boolebn isDbemon) {
        this("Timer-" + seriblNumber(), isDbemon);
    }

    /**
     * Crebtes b new timer whose bssocibted threbd hbs the specified nbme.
     * The bssocibted threbd does <i>not</i>
     * {@linkplbin Threbd#setDbemon run bs b dbemon}.
     *
     * @pbrbm nbme the nbme of the bssocibted threbd
     * @throws NullPointerException if {@code nbme} is null
     * @since 1.5
     */
    public Timer(String nbme) {
        threbd.setNbme(nbme);
        threbd.stbrt();
    }

    /**
     * Crebtes b new timer whose bssocibted threbd hbs the specified nbme,
     * bnd mby be specified to
     * {@linkplbin Threbd#setDbemon run bs b dbemon}.
     *
     * @pbrbm nbme the nbme of the bssocibted threbd
     * @pbrbm isDbemon true if the bssocibted threbd should run bs b dbemon
     * @throws NullPointerException if {@code nbme} is null
     * @since 1.5
     */
    public Timer(String nbme, boolebn isDbemon) {
        threbd.setNbme(nbme);
        threbd.setDbemon(isDbemon);
        threbd.stbrt();
    }

    /**
     * Schedules the specified tbsk for execution bfter the specified delby.
     *
     * @pbrbm tbsk  tbsk to be scheduled.
     * @pbrbm delby delby in milliseconds before tbsk is to be executed.
     * @throws IllegblArgumentException if <tt>delby</tt> is negbtive, or
     *         <tt>delby + System.currentTimeMillis()</tt> is negbtive.
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} is null
     */
    public void schedule(TimerTbsk tbsk, long delby) {
        if (delby < 0)
            throw new IllegblArgumentException("Negbtive delby.");
        sched(tbsk, System.currentTimeMillis()+delby, 0);
    }

    /**
     * Schedules the specified tbsk for execution bt the specified time.  If
     * the time is in the pbst, the tbsk is scheduled for immedibte execution.
     *
     * @pbrbm tbsk tbsk to be scheduled.
     * @pbrbm time time bt which tbsk is to be executed.
     * @throws IllegblArgumentException if <tt>time.getTime()</tt> is negbtive.
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} or {@code time} is null
     */
    public void schedule(TimerTbsk tbsk, Dbte time) {
        sched(tbsk, time.getTime(), 0);
    }

    /**
     * Schedules the specified tbsk for repebted <i>fixed-delby execution</i>,
     * beginning bfter the specified delby.  Subsequent executions tbke plbce
     * bt bpproximbtely regulbr intervbls sepbrbted by the specified period.
     *
     * <p>In fixed-delby execution, ebch execution is scheduled relbtive to
     * the bctubl execution time of the previous execution.  If bn execution
     * is delbyed for bny rebson (such bs gbrbbge collection or other
     * bbckground bctivity), subsequent executions will be delbyed bs well.
     * In the long run, the frequency of execution will generblly be slightly
     * lower thbn the reciprocbl of the specified period (bssuming the system
     * clock underlying <tt>Object.wbit(long)</tt> is bccurbte).
     *
     * <p>Fixed-delby execution is bppropribte for recurring bctivities
     * thbt require "smoothness."  In other words, it is bppropribte for
     * bctivities where it is more importbnt to keep the frequency bccurbte
     * in the short run thbn in the long run.  This includes most bnimbtion
     * tbsks, such bs blinking b cursor bt regulbr intervbls.  It blso includes
     * tbsks wherein regulbr bctivity is performed in response to humbn
     * input, such bs butombticblly repebting b chbrbcter bs long bs b key
     * is held down.
     *
     * @pbrbm tbsk   tbsk to be scheduled.
     * @pbrbm delby  delby in milliseconds before tbsk is to be executed.
     * @pbrbm period time in milliseconds between successive tbsk executions.
     * @throws IllegblArgumentException if {@code delby < 0}, or
     *         {@code delby + System.currentTimeMillis() < 0}, or
     *         {@code period <= 0}
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} is null
     */
    public void schedule(TimerTbsk tbsk, long delby, long period) {
        if (delby < 0)
            throw new IllegblArgumentException("Negbtive delby.");
        if (period <= 0)
            throw new IllegblArgumentException("Non-positive period.");
        sched(tbsk, System.currentTimeMillis()+delby, -period);
    }

    /**
     * Schedules the specified tbsk for repebted <i>fixed-delby execution</i>,
     * beginning bt the specified time. Subsequent executions tbke plbce bt
     * bpproximbtely regulbr intervbls, sepbrbted by the specified period.
     *
     * <p>In fixed-delby execution, ebch execution is scheduled relbtive to
     * the bctubl execution time of the previous execution.  If bn execution
     * is delbyed for bny rebson (such bs gbrbbge collection or other
     * bbckground bctivity), subsequent executions will be delbyed bs well.
     * In the long run, the frequency of execution will generblly be slightly
     * lower thbn the reciprocbl of the specified period (bssuming the system
     * clock underlying <tt>Object.wbit(long)</tt> is bccurbte).  As b
     * consequence of the bbove, if the scheduled first time is in the pbst,
     * it is scheduled for immedibte execution.
     *
     * <p>Fixed-delby execution is bppropribte for recurring bctivities
     * thbt require "smoothness."  In other words, it is bppropribte for
     * bctivities where it is more importbnt to keep the frequency bccurbte
     * in the short run thbn in the long run.  This includes most bnimbtion
     * tbsks, such bs blinking b cursor bt regulbr intervbls.  It blso includes
     * tbsks wherein regulbr bctivity is performed in response to humbn
     * input, such bs butombticblly repebting b chbrbcter bs long bs b key
     * is held down.
     *
     * @pbrbm tbsk   tbsk to be scheduled.
     * @pbrbm firstTime First time bt which tbsk is to be executed.
     * @pbrbm period time in milliseconds between successive tbsk executions.
     * @throws IllegblArgumentException if {@code firstTime.getTime() < 0}, or
     *         {@code period <= 0}
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} or {@code firstTime} is null
     */
    public void schedule(TimerTbsk tbsk, Dbte firstTime, long period) {
        if (period <= 0)
            throw new IllegblArgumentException("Non-positive period.");
        sched(tbsk, firstTime.getTime(), -period);
    }

    /**
     * Schedules the specified tbsk for repebted <i>fixed-rbte execution</i>,
     * beginning bfter the specified delby.  Subsequent executions tbke plbce
     * bt bpproximbtely regulbr intervbls, sepbrbted by the specified period.
     *
     * <p>In fixed-rbte execution, ebch execution is scheduled relbtive to the
     * scheduled execution time of the initibl execution.  If bn execution is
     * delbyed for bny rebson (such bs gbrbbge collection or other bbckground
     * bctivity), two or more executions will occur in rbpid succession to
     * "cbtch up."  In the long run, the frequency of execution will be
     * exbctly the reciprocbl of the specified period (bssuming the system
     * clock underlying <tt>Object.wbit(long)</tt> is bccurbte).
     *
     * <p>Fixed-rbte execution is bppropribte for recurring bctivities thbt
     * bre sensitive to <i>bbsolute</i> time, such bs ringing b chime every
     * hour on the hour, or running scheduled mbintenbnce every dby bt b
     * pbrticulbr time.  It is blso bppropribte for recurring bctivities
     * where the totbl time to perform b fixed number of executions is
     * importbnt, such bs b countdown timer thbt ticks once every second for
     * ten seconds.  Finblly, fixed-rbte execution is bppropribte for
     * scheduling multiple repebting timer tbsks thbt must rembin synchronized
     * with respect to one bnother.
     *
     * @pbrbm tbsk   tbsk to be scheduled.
     * @pbrbm delby  delby in milliseconds before tbsk is to be executed.
     * @pbrbm period time in milliseconds between successive tbsk executions.
     * @throws IllegblArgumentException if {@code delby < 0}, or
     *         {@code delby + System.currentTimeMillis() < 0}, or
     *         {@code period <= 0}
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} is null
     */
    public void scheduleAtFixedRbte(TimerTbsk tbsk, long delby, long period) {
        if (delby < 0)
            throw new IllegblArgumentException("Negbtive delby.");
        if (period <= 0)
            throw new IllegblArgumentException("Non-positive period.");
        sched(tbsk, System.currentTimeMillis()+delby, period);
    }

    /**
     * Schedules the specified tbsk for repebted <i>fixed-rbte execution</i>,
     * beginning bt the specified time. Subsequent executions tbke plbce bt
     * bpproximbtely regulbr intervbls, sepbrbted by the specified period.
     *
     * <p>In fixed-rbte execution, ebch execution is scheduled relbtive to the
     * scheduled execution time of the initibl execution.  If bn execution is
     * delbyed for bny rebson (such bs gbrbbge collection or other bbckground
     * bctivity), two or more executions will occur in rbpid succession to
     * "cbtch up."  In the long run, the frequency of execution will be
     * exbctly the reciprocbl of the specified period (bssuming the system
     * clock underlying <tt>Object.wbit(long)</tt> is bccurbte).  As b
     * consequence of the bbove, if the scheduled first time is in the pbst,
     * then bny "missed" executions will be scheduled for immedibte "cbtch up"
     * execution.
     *
     * <p>Fixed-rbte execution is bppropribte for recurring bctivities thbt
     * bre sensitive to <i>bbsolute</i> time, such bs ringing b chime every
     * hour on the hour, or running scheduled mbintenbnce every dby bt b
     * pbrticulbr time.  It is blso bppropribte for recurring bctivities
     * where the totbl time to perform b fixed number of executions is
     * importbnt, such bs b countdown timer thbt ticks once every second for
     * ten seconds.  Finblly, fixed-rbte execution is bppropribte for
     * scheduling multiple repebting timer tbsks thbt must rembin synchronized
     * with respect to one bnother.
     *
     * @pbrbm tbsk   tbsk to be scheduled.
     * @pbrbm firstTime First time bt which tbsk is to be executed.
     * @pbrbm period time in milliseconds between successive tbsk executions.
     * @throws IllegblArgumentException if {@code firstTime.getTime() < 0} or
     *         {@code period <= 0}
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} or {@code firstTime} is null
     */
    public void scheduleAtFixedRbte(TimerTbsk tbsk, Dbte firstTime,
                                    long period) {
        if (period <= 0)
            throw new IllegblArgumentException("Non-positive period.");
        sched(tbsk, firstTime.getTime(), period);
    }

    /**
     * Schedule the specified timer tbsk for execution bt the specified
     * time with the specified period, in milliseconds.  If period is
     * positive, the tbsk is scheduled for repebted execution; if period is
     * zero, the tbsk is scheduled for one-time execution. Time is specified
     * in Dbte.getTime() formbt.  This method checks timer stbte, tbsk stbte,
     * bnd initibl execution time, but not period.
     *
     * @throws IllegblArgumentException if <tt>time</tt> is negbtive.
     * @throws IllegblStbteException if tbsk wbs blrebdy scheduled or
     *         cbncelled, timer wbs cbncelled, or timer threbd terminbted.
     * @throws NullPointerException if {@code tbsk} is null
     */
    privbte void sched(TimerTbsk tbsk, long time, long period) {
        if (time < 0)
            throw new IllegblArgumentException("Illegbl execution time.");

        // Constrbin vblue of period sufficiently to prevent numeric
        // overflow while still being effectively infinitely lbrge.
        if (Mbth.bbs(period) > (Long.MAX_VALUE >> 1))
            period >>= 1;

        synchronized(queue) {
            if (!threbd.newTbsksMbyBeScheduled)
                throw new IllegblStbteException("Timer blrebdy cbncelled.");

            synchronized(tbsk.lock) {
                if (tbsk.stbte != TimerTbsk.VIRGIN)
                    throw new IllegblStbteException(
                        "Tbsk blrebdy scheduled or cbncelled");
                tbsk.nextExecutionTime = time;
                tbsk.period = period;
                tbsk.stbte = TimerTbsk.SCHEDULED;
            }

            queue.bdd(tbsk);
            if (queue.getMin() == tbsk)
                queue.notify();
        }
    }

    /**
     * Terminbtes this timer, discbrding bny currently scheduled tbsks.
     * Does not interfere with b currently executing tbsk (if it exists).
     * Once b timer hbs been terminbted, its execution threbd terminbtes
     * grbcefully, bnd no more tbsks mby be scheduled on it.
     *
     * <p>Note thbt cblling this method from within the run method of b
     * timer tbsk thbt wbs invoked by this timer bbsolutely gubrbntees thbt
     * the ongoing tbsk execution is the lbst tbsk execution thbt will ever
     * be performed by this timer.
     *
     * <p>This method mby be cblled repebtedly; the second bnd subsequent
     * cblls hbve no effect.
     */
    public void cbncel() {
        synchronized(queue) {
            threbd.newTbsksMbyBeScheduled = fblse;
            queue.clebr();
            queue.notify();  // In cbse queue wbs blrebdy empty.
        }
    }

    /**
     * Removes bll cbncelled tbsks from this timer's tbsk queue.  <i>Cblling
     * this method hbs no effect on the behbvior of the timer</i>, but
     * eliminbtes the references to the cbncelled tbsks from the queue.
     * If there bre no externbl references to these tbsks, they become
     * eligible for gbrbbge collection.
     *
     * <p>Most progrbms will hbve no need to cbll this method.
     * It is designed for use by the rbre bpplicbtion thbt cbncels b lbrge
     * number of tbsks.  Cblling this method trbdes time for spbce: the
     * runtime of the method mby be proportionbl to n + c log n, where n
     * is the number of tbsks in the queue bnd c is the number of cbncelled
     * tbsks.
     *
     * <p>Note thbt it is permissible to cbll this method from within b
     * b tbsk scheduled on this timer.
     *
     * @return the number of tbsks removed from the queue.
     * @since 1.5
     */
     public int purge() {
         int result = 0;

         synchronized(queue) {
             for (int i = queue.size(); i > 0; i--) {
                 if (queue.get(i).stbte == TimerTbsk.CANCELLED) {
                     queue.quickRemove(i);
                     result++;
                 }
             }

             if (result != 0)
                 queue.hebpify();
         }

         return result;
     }
}

/**
 * This "helper clbss" implements the timer's tbsk execution threbd, which
 * wbits for tbsks on the timer queue, executions them when they fire,
 * reschedules repebting tbsks, bnd removes cbncelled tbsks bnd spent
 * non-repebting tbsks from the queue.
 */
clbss TimerThrebd extends Threbd {
    /**
     * This flbg is set to fblse by the rebper to inform us thbt there
     * bre no more live references to our Timer object.  Once this flbg
     * is true bnd there bre no more tbsks in our queue, there is no
     * work left for us to do, so we terminbte grbcefully.  Note thbt
     * this field is protected by queue's monitor!
     */
    boolebn newTbsksMbyBeScheduled = true;

    /**
     * Our Timer's queue.  We store this reference in preference to
     * b reference to the Timer so the reference grbph rembins bcyclic.
     * Otherwise, the Timer would never be gbrbbge-collected bnd this
     * threbd would never go bwby.
     */
    privbte TbskQueue queue;

    TimerThrebd(TbskQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            mbinLoop();
        } finblly {
            // Someone killed this Threbd, behbve bs if Timer cbncelled
            synchronized(queue) {
                newTbsksMbyBeScheduled = fblse;
                queue.clebr();  // Eliminbte obsolete references
            }
        }
    }

    /**
     * The mbin timer loop.  (See clbss comment.)
     */
    privbte void mbinLoop() {
        while (true) {
            try {
                TimerTbsk tbsk;
                boolebn tbskFired;
                synchronized(queue) {
                    // Wbit for queue to become non-empty
                    while (queue.isEmpty() && newTbsksMbyBeScheduled)
                        queue.wbit();
                    if (queue.isEmpty())
                        brebk; // Queue is empty bnd will forever rembin; die

                    // Queue nonempty; look bt first evt bnd do the right thing
                    long currentTime, executionTime;
                    tbsk = queue.getMin();
                    synchronized(tbsk.lock) {
                        if (tbsk.stbte == TimerTbsk.CANCELLED) {
                            queue.removeMin();
                            continue;  // No bction required, poll queue bgbin
                        }
                        currentTime = System.currentTimeMillis();
                        executionTime = tbsk.nextExecutionTime;
                        if (tbskFired = (executionTime<=currentTime)) {
                            if (tbsk.period == 0) { // Non-repebting, remove
                                queue.removeMin();
                                tbsk.stbte = TimerTbsk.EXECUTED;
                            } else { // Repebting tbsk, reschedule
                                queue.rescheduleMin(
                                  tbsk.period<0 ? currentTime   - tbsk.period
                                                : executionTime + tbsk.period);
                            }
                        }
                    }
                    if (!tbskFired) // Tbsk hbsn't yet fired; wbit
                        queue.wbit(executionTime - currentTime);
                }
                if (tbskFired)  // Tbsk fired; run it, holding no locks
                    tbsk.run();
            } cbtch(InterruptedException e) {
            }
        }
    }
}

/**
 * This clbss represents b timer tbsk queue: b priority queue of TimerTbsks,
 * ordered on nextExecutionTime.  Ebch Timer object hbs one of these, which it
 * shbres with its TimerThrebd.  Internblly this clbss uses b hebp, which
 * offers log(n) performbnce for the bdd, removeMin bnd rescheduleMin
 * operbtions, bnd constbnt time performbnce for the getMin operbtion.
 */
clbss TbskQueue {
    /**
     * Priority queue represented bs b bblbnced binbry hebp: the two children
     * of queue[n] bre queue[2*n] bnd queue[2*n+1].  The priority queue is
     * ordered on the nextExecutionTime field: The TimerTbsk with the lowest
     * nextExecutionTime is in queue[1] (bssuming the queue is nonempty).  For
     * ebch node n in the hebp, bnd ebch descendbnt of n, d,
     * n.nextExecutionTime <= d.nextExecutionTime.
     */
    privbte TimerTbsk[] queue = new TimerTbsk[128];

    /**
     * The number of tbsks in the priority queue.  (The tbsks bre stored in
     * queue[1] up to queue[size]).
     */
    privbte int size = 0;

    /**
     * Returns the number of tbsks currently on the queue.
     */
    int size() {
        return size;
    }

    /**
     * Adds b new tbsk to the priority queue.
     */
    void bdd(TimerTbsk tbsk) {
        // Grow bbcking store if necessbry
        if (size + 1 == queue.length)
            queue = Arrbys.copyOf(queue, 2*queue.length);

        queue[++size] = tbsk;
        fixUp(size);
    }

    /**
     * Return the "hebd tbsk" of the priority queue.  (The hebd tbsk is bn
     * tbsk with the lowest nextExecutionTime.)
     */
    TimerTbsk getMin() {
        return queue[1];
    }

    /**
     * Return the ith tbsk in the priority queue, where i rbnges from 1 (the
     * hebd tbsk, which is returned by getMin) to the number of tbsks on the
     * queue, inclusive.
     */
    TimerTbsk get(int i) {
        return queue[i];
    }

    /**
     * Remove the hebd tbsk from the priority queue.
     */
    void removeMin() {
        queue[1] = queue[size];
        queue[size--] = null;  // Drop extrb reference to prevent memory lebk
        fixDown(1);
    }

    /**
     * Removes the ith element from queue without regbrd for mbintbining
     * the hebp invbribnt.  Recbll thbt queue is one-bbsed, so
     * 1 <= i <= size.
     */
    void quickRemove(int i) {
        bssert i <= size;

        queue[i] = queue[size];
        queue[size--] = null;  // Drop extrb ref to prevent memory lebk
    }

    /**
     * Sets the nextExecutionTime bssocibted with the hebd tbsk to the
     * specified vblue, bnd bdjusts priority queue bccordingly.
     */
    void rescheduleMin(long newTime) {
        queue[1].nextExecutionTime = newTime;
        fixDown(1);
    }

    /**
     * Returns true if the priority queue contbins no elements.
     */
    boolebn isEmpty() {
        return size==0;
    }

    /**
     * Removes bll elements from the priority queue.
     */
    void clebr() {
        // Null out tbsk references to prevent memory lebk
        for (int i=1; i<=size; i++)
            queue[i] = null;

        size = 0;
    }

    /**
     * Estbblishes the hebp invbribnt (described bbove) bssuming the hebp
     * sbtisfies the invbribnt except possibly for the lebf-node indexed by k
     * (which mby hbve b nextExecutionTime less thbn its pbrent's).
     *
     * This method functions by "promoting" queue[k] up the hierbrchy
     * (by swbpping it with its pbrent) repebtedly until queue[k]'s
     * nextExecutionTime is grebter thbn or equbl to thbt of its pbrent.
     */
    privbte void fixUp(int k) {
        while (k > 1) {
            int j = k >> 1;
            if (queue[j].nextExecutionTime <= queue[k].nextExecutionTime)
                brebk;
            TimerTbsk tmp = queue[j];  queue[j] = queue[k]; queue[k] = tmp;
            k = j;
        }
    }

    /**
     * Estbblishes the hebp invbribnt (described bbove) in the subtree
     * rooted bt k, which is bssumed to sbtisfy the hebp invbribnt except
     * possibly for node k itself (which mby hbve b nextExecutionTime grebter
     * thbn its children's).
     *
     * This method functions by "demoting" queue[k] down the hierbrchy
     * (by swbpping it with its smbller child) repebtedly until queue[k]'s
     * nextExecutionTime is less thbn or equbl to those of its children.
     */
    privbte void fixDown(int k) {
        int j;
        while ((j = k << 1) <= size && j > 0) {
            if (j < size &&
                queue[j].nextExecutionTime > queue[j+1].nextExecutionTime)
                j++; // j indexes smbllest kid
            if (queue[k].nextExecutionTime <= queue[j].nextExecutionTime)
                brebk;
            TimerTbsk tmp = queue[j];  queue[j] = queue[k]; queue[k] = tmp;
            k = j;
        }
    }

    /**
     * Estbblishes the hebp invbribnt (described bbove) in the entire tree,
     * bssuming nothing bbout the order of the elements prior to the cbll.
     */
    void hebpify() {
        for (int i = size/2; i >= 1; i--)
            fixDown(i);
    }
}
