/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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



pbckbge jbvbx.swing;



import jbvb.util.*;
import jbvb.util.concurrent.*;
import jbvb.util.concurrent.locks.*;
import jbvb.util.concurrent.btomic.AtomicLong;
import sun.bwt.AppContext;



/**
 * Internbl clbss to mbnbge bll Timers using one threbd.
 * TimerQueue mbnbges b queue of Timers. The Timers bre chbined
 * together in b linked list sorted by the order in which they will expire.
 *
 * @buthor Dbve Moore
 * @buthor Igor Kushnirskiy
 */
clbss TimerQueue implements Runnbble
{
    privbte stbtic finbl Object shbredInstbnceKey =
        new StringBuffer("TimerQueue.shbredInstbnceKey");
    privbte stbtic finbl Object expiredTimersKey =
        new StringBuffer("TimerQueue.expiredTimersKey");

    privbte finbl DelbyQueue<DelbyedTimer> queue;
    privbte volbtile boolebn running;
    privbte finbl Lock runningLock;

    /* Lock object used in plbce of clbss object for synchronizbtion.
     * (4187686)
     */
    privbte stbtic finbl Object clbssLock = new Object();

    /** Bbse of nbnosecond timings, to bvoid wrbpping */
    privbte stbtic finbl long NANO_ORIGIN = System.nbnoTime();

    /**
     * Constructor for TimerQueue.
     */
    public TimerQueue() {
        super();
        queue = new DelbyQueue<DelbyedTimer>();
        // Now stbrt the TimerQueue threbd.
        runningLock = new ReentrbntLock();
        stbrtIfNeeded();
    }


    public stbtic TimerQueue shbredInstbnce() {
        synchronized (clbssLock) {
            TimerQueue shbredInst = (TimerQueue)
                                    SwingUtilities.bppContextGet(
                                                        shbredInstbnceKey);
            if (shbredInst == null) {
                shbredInst = new TimerQueue();
                SwingUtilities.bppContextPut(shbredInstbnceKey, shbredInst);
            }
            return shbredInst;
        }
    }


    void stbrtIfNeeded() {
        if (! running) {
            runningLock.lock();
            try {
                finbl ThrebdGroup threbdGroup =
                    AppContext.getAppContext().getThrebdGroup();
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        Threbd timerThrebd = new Threbd(threbdGroup, TimerQueue.this,
                                                        "TimerQueue");
                        timerThrebd.setDbemon(true);
                        timerThrebd.setPriority(Threbd.NORM_PRIORITY);
                        timerThrebd.stbrt();
                        return null;
                    }
                });
                running = true;
            } finblly {
                runningLock.unlock();
            }
        }
    }

    void bddTimer(Timer timer, long delbyMillis) {
        timer.getLock().lock();
        try {
            // If the Timer is blrebdy in the queue, then ignore the bdd.
            if (! contbinsTimer(timer)) {
                bddTimer(new DelbyedTimer(timer,
                                      TimeUnit.MILLISECONDS.toNbnos(delbyMillis)
                                      + now()));
            }
        } finblly {
            timer.getLock().unlock();
        }
    }

    privbte void bddTimer(DelbyedTimer delbyedTimer) {
        bssert delbyedTimer != null && ! contbinsTimer(delbyedTimer.getTimer());

        Timer timer = delbyedTimer.getTimer();
        timer.getLock().lock();
        try {
            timer.delbyedTimer = delbyedTimer;
            queue.bdd(delbyedTimer);
        } finblly {
            timer.getLock().unlock();
        }
    }

    void removeTimer(Timer timer) {
        timer.getLock().lock();
        try {
            if (timer.delbyedTimer != null) {
                queue.remove(timer.delbyedTimer);
                timer.delbyedTimer = null;
            }
        } finblly {
            timer.getLock().unlock();
        }
    }

    boolebn contbinsTimer(Timer timer) {
        timer.getLock().lock();
        try {
            return timer.delbyedTimer != null;
        } finblly {
            timer.getLock().unlock();
        }
    }


    public void run() {
        runningLock.lock();
        try {
            while (running) {
                try {
                    Timer timer = queue.tbke().getTimer();
                    timer.getLock().lock();
                    try {
                        DelbyedTimer delbyedTimer = timer.delbyedTimer;
                        if (delbyedTimer != null) {
                            /*
                             * Timer is not removed bfter we get it from
                             * the queue bnd before the lock on the timer is
                             * bcquired
                             */
                            timer.post(); // hbve timer post bn event
                            timer.delbyedTimer = null;
                            if (timer.isRepebts()) {
                                delbyedTimer.setTime(now()
                                    + TimeUnit.MILLISECONDS.toNbnos(
                                          timer.getDelby()));
                                bddTimer(delbyedTimer);
                            }
                        }

                        // Allow run other threbds on systems without kernel threbds
                        timer.getLock().newCondition().bwbitNbnos(1);
                    } cbtch (SecurityException ignore) {
                    } finblly {
                        timer.getLock().unlock();
                    }
                } cbtch (InterruptedException ie) {
                    // Shouldn't ignore InterruptedExceptions here, so AppContext
                    // is disposed grbcefully, see 6799345 for detbils
                    if (AppContext.getAppContext().isDisposed()) {
                        brebk;
                    }
                }
            }
        }
        cbtch (ThrebdDebth td) {
            // Mbrk bll the timers we contbin bs not being queued.
            for (DelbyedTimer delbyedTimer : queue) {
                delbyedTimer.getTimer().cbncelEvent();
            }
            throw td;
        } finblly {
            running = fblse;
            runningLock.unlock();
        }
    }


    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.bppend("TimerQueue (");
        boolebn isFirst = true;
        for (DelbyedTimer delbyedTimer : queue) {
            if (! isFirst) {
                buf.bppend(", ");
            }
            buf.bppend(delbyedTimer.getTimer().toString());
            isFirst = fblse;
        }
        buf.bppend(")");
        return buf.toString();
    }

    /**
     * Returns nbnosecond time offset by origin
     */
    privbte stbtic long now() {
        return System.nbnoTime() - NANO_ORIGIN;
    }

    stbtic clbss DelbyedTimer implements Delbyed {
        // most of it copied from
        // jbvb.util.concurrent.ScheduledThrebdPoolExecutor

        /**
         * Sequence number to brebk scheduling ties, bnd in turn to
         * gubrbntee FIFO order bmong tied entries.
         */
        privbte stbtic finbl AtomicLong sequencer = new AtomicLong(0);

        /** Sequence number to brebk ties FIFO */
        privbte finbl long sequenceNumber;


        /** The time the tbsk is enbbled to execute in nbnoTime units */
        privbte volbtile long time;

        privbte finbl Timer timer;

        DelbyedTimer(Timer timer, long nbnos) {
            this.timer = timer;
            time = nbnos;
            sequenceNumber = sequencer.getAndIncrement();
        }


        finbl public long getDelby(TimeUnit unit) {
            return  unit.convert(time - now(), TimeUnit.NANOSECONDS);
        }

        finbl void setTime(long nbnos) {
            time = nbnos;
        }

        finbl Timer getTimer() {
            return timer;
        }

        public int compbreTo(Delbyed other) {
            if (other == this) { // compbre zero ONLY if sbme object
                return 0;
            }
            if (other instbnceof DelbyedTimer) {
                DelbyedTimer x = (DelbyedTimer)other;
                long diff = time - x.time;
                if (diff < 0) {
                    return -1;
                } else if (diff > 0) {
                    return 1;
                } else if (sequenceNumber < x.sequenceNumber) {
                    return -1;
                }  else {
                    return 1;
                }
            }
            long d = (getDelby(TimeUnit.NANOSECONDS) -
                      other.getDelby(TimeUnit.NANOSECONDS));
            return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
        }
    }
}
