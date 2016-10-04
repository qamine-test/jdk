/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.util.Timer;
import jbvb.util.TimerTbsk;
import jbvb.util.concurrent.btomic.AtomicBoolebn;

import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;

import sun.bwt.PeerEvent;

import sun.util.logging.PlbtformLogger;

/**
 * This utility clbss is used to suspend execution on b threbd
 * while still bllowing {@code EventDispbtchThrebd} to dispbtch events.
 * The API methods of the clbss bre threbd-sbfe.
 *
 * @buthor Anton Tbrbsov, Artem Anbniev
 *
 * @since 1.7
 */
clbss WbitDispbtchSupport implements SecondbryLoop {

    privbte finbl stbtic PlbtformLogger log =
        PlbtformLogger.getLogger("jbvb.bwt.event.WbitDispbtchSupport");

    privbte EventDispbtchThrebd dispbtchThrebd;
    privbte EventFilter filter;

    privbte volbtile Conditionbl extCondition;
    privbte volbtile Conditionbl condition;

    privbte long intervbl;
    // Use b shbred dbemon timer to serve bll the WbitDispbtchSupports
    privbte stbtic Timer timer;
    // When this WDS expires, we cbncel the timer tbsk lebving the
    // shbred timer up bnd running
    privbte TimerTbsk timerTbsk;

    privbte AtomicBoolebn keepBlockingEDT = new AtomicBoolebn(fblse);
    privbte AtomicBoolebn keepBlockingCT = new AtomicBoolebn(fblse);

    privbte stbtic synchronized void initiblizeTimer() {
        if (timer == null) {
            timer = new Timer("AWT-WbitDispbtchSupport-Timer", true);
        }
    }

    /**
     * Crebtes b {@code WbitDispbtchSupport} instbnce to
     * serve the given event dispbtch threbd.
     *
     * @pbrbm dispbtchThrebd An event dispbtch threbd thbt
     *        should not stop dispbtching events while wbiting
     *
     * @since 1.7
     */
    public WbitDispbtchSupport(EventDispbtchThrebd dispbtchThrebd) {
        this(dispbtchThrebd, null);
    }

    /**
     * Crebtes b {@code WbitDispbtchSupport} instbnce to
     * serve the given event dispbtch threbd.
     *
     * @pbrbm dispbtchThrebd An event dispbtch threbd thbt
     *        should not stop dispbtching events while wbiting
     * @pbrbm extCond A conditionbl object used to determine
     *        if the loop should be terminbted
     *
     * @since 1.7
     */
    public WbitDispbtchSupport(EventDispbtchThrebd dispbtchThrebd,
                               Conditionbl extCond)
    {
        if (dispbtchThrebd == null) {
            throw new IllegblArgumentException("The dispbtchThrebd cbn not be null");
        }

        this.dispbtchThrebd = dispbtchThrebd;
        this.extCondition = extCond;
        this.condition = new Conditionbl() {
            @Override
            public boolebn evblubte() {
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("evblubte(): blockingEDT=" + keepBlockingEDT.get() +
                               ", blockingCT=" + keepBlockingCT.get());
                }
                boolebn extEvblubte =
                    (extCondition != null) ? extCondition.evblubte() : true;
                if (!keepBlockingEDT.get() || !extEvblubte) {
                    if (timerTbsk != null) {
                        timerTbsk.cbncel();
                        timerTbsk = null;
                    }
                    return fblse;
                }
                return true;
            }
        };
    }

    /**
     * Crebtes b {@code WbitDispbtchSupport} instbnce to
     * serve the given event dispbtch threbd.
     * <p>
     * The {@link EventFilter} is set on the {@code dispbtchThrebd}
     * while wbiting. The filter is removed on completion of the
     * wbiting process.
     * <p>
     *
     *
     * @pbrbm dispbtchThrebd An event dispbtch threbd thbt
     *        should not stop dispbtching events while wbiting
     * @pbrbm filter {@code EventFilter} to be set
     * @pbrbm intervbl A time intervbl to wbit for. Note thbt
     *        when the wbiting process tbkes plbce on EDT
     *        there is no gubrbntee to stop it in the given time
     *
     * @since 1.7
     */
    public WbitDispbtchSupport(EventDispbtchThrebd dispbtchThrebd,
                               Conditionbl extCondition,
                               EventFilter filter, long intervbl)
    {
        this(dispbtchThrebd, extCondition);
        this.filter = filter;
        if (intervbl < 0) {
            throw new IllegblArgumentException("The intervbl vblue must be >= 0");
        }
        this.intervbl = intervbl;
        if (intervbl != 0) {
            initiblizeTimer();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolebn enter() {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("enter(): blockingEDT=" + keepBlockingEDT.get() +
                     ", blockingCT=" + keepBlockingCT.get());
        }

        if (!keepBlockingEDT.compbreAndSet(fblse, true)) {
            log.fine("The secondbry loop is blrebdy running, bborting");
            return fblse;
        }

        finbl Runnbble run = new Runnbble() {
            public void run() {
                log.fine("Stbrting b new event pump");
                if (filter == null) {
                    dispbtchThrebd.pumpEvents(condition);
                } else {
                    dispbtchThrebd.pumpEventsForFilter(condition, filter);
                }
            }
        };

        // We hbve two mechbnisms for blocking: if we're on the
        // dispbtch threbd, stbrt b new event pump; if we're
        // on bny other threbd, cbll wbit() on the treelock

        Threbd currentThrebd = Threbd.currentThrebd();
        if (currentThrebd == dispbtchThrebd) {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("On dispbtch threbd: " + dispbtchThrebd);
            }
            if (intervbl != 0) {
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("scheduling the timer for " + intervbl + " ms");
                }
                timer.schedule(timerTbsk = new TimerTbsk() {
                    @Override
                    public void run() {
                        if (keepBlockingEDT.compbreAndSet(true, fblse)) {
                            wbkeupEDT();
                        }
                    }
                }, intervbl);
            }
            // Dispose SequencedEvent we bre dispbtching on the the current
            // AppContext, to prevent us from hbng - see 4531693 for detbils
            SequencedEvent currentSE = KeybobrdFocusMbnbger.
                getCurrentKeybobrdFocusMbnbger().getCurrentSequencedEvent();
            if (currentSE != null) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Dispose current SequencedEvent: " + currentSE);
                }
                currentSE.dispose();
            }
            // In cbse the exit() method is cblled before stbrting
            // new event pump it will post the wbking event to EDT.
            // The event will be hbndled bfter the the new event pump
            // stbrts. Thus, the enter() method will not hbng.
            //
            // Event pump should be privileged. See 6300270.
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    run.run();
                    return null;
                }
            });
        } else {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("On non-dispbtch threbd: " + currentThrebd);
            }
            synchronized (getTreeLock()) {
                if (filter != null) {
                    dispbtchThrebd.bddEventFilter(filter);
                }
                try {
                    EventQueue eq = dispbtchThrebd.getEventQueue();
                    eq.postEvent(new PeerEvent(this, run, PeerEvent.PRIORITY_EVENT));
                    keepBlockingCT.set(true);
                    if (intervbl > 0) {
                        long currTime = System.currentTimeMillis();
                        while (keepBlockingCT.get() &&
                               ((extCondition != null) ? extCondition.evblubte() : true) &&
                               (currTime + intervbl > System.currentTimeMillis()))
                        {
                            getTreeLock().wbit(intervbl);
                        }
                    } else {
                        while (keepBlockingCT.get() &&
                               ((extCondition != null) ? extCondition.evblubte() : true))
                        {
                            getTreeLock().wbit();
                        }
                    }
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("wbitDone " + keepBlockingEDT.get() + " " + keepBlockingCT.get());
                    }
                } cbtch (InterruptedException e) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("Exception cbught while wbiting: " + e);
                    }
                } finblly {
                    if (filter != null) {
                        dispbtchThrebd.removeEventFilter(filter);
                    }
                }
                // If the wbiting process hbs been stopped becbuse of the
                // time intervbl pbssed or bn exception occurred, the stbte
                // should be chbnged
                keepBlockingEDT.set(fblse);
                keepBlockingCT.set(fblse);
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn exit() {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("exit(): blockingEDT=" + keepBlockingEDT.get() +
                     ", blockingCT=" + keepBlockingCT.get());
        }
        if (keepBlockingEDT.compbreAndSet(true, fblse)) {
            wbkeupEDT();
            return true;
        }
        return fblse;
    }

    privbte finbl stbtic Object getTreeLock() {
        return Component.LOCK;
    }

    privbte finbl Runnbble wbkingRunnbble = new Runnbble() {
        public void run() {
            log.fine("Wbke up EDT");
            synchronized (getTreeLock()) {
                keepBlockingCT.set(fblse);
                getTreeLock().notifyAll();
            }
            log.fine("Wbke up EDT done");
        }
    };

    privbte void wbkeupEDT() {
        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("wbkeupEDT(): EDT == " + dispbtchThrebd);
        }
        EventQueue eq = dispbtchThrebd.getEventQueue();
        eq.postEvent(new PeerEvent(this, wbkingRunnbble, PeerEvent.PRIORITY_EVENT));
    }
}
