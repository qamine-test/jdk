/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Copyright 2012 SAP AG. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider;
import jbvb.io.IOException;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.concurrent.ArrbyBlockingQueue;
import jbvb.util.concurrent.RejectedExecutionException;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.locks.ReentrbntLock;
import sun.misc.Unsbfe;

/**
 * AsynchronousChbnnelGroup implementbtion bbsed on the AIX pollset frbmework.
 */
finbl clbss AixPollPort
    extends Port
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    stbtic {
        IOUtil.lobd();
        init();
    }

    /**
     * struct pollfd {
     *     int fd;
     *     short events;
     *     short revents;
     * }
     */
    privbte stbtic finbl int SIZEOF_POLLFD    = eventSize();
    privbte stbtic finbl int OFFSETOF_EVENTS  = eventsOffset();
    privbte stbtic finbl int OFFSETOF_REVENTS = reventsOffset();
    privbte stbtic finbl int OFFSETOF_FD      = fdOffset();

    // opcodes
    privbte stbtic finbl int PS_ADD     = 0x0;
    privbte stbtic finbl int PS_MOD     = 0x1;
    privbte stbtic finbl int PS_DELETE  = 0x2;

    // mbximum number of events to poll bt b time
    privbte stbtic finbl int MAX_POLL_EVENTS = 512;

    // pollset ID
    privbte finbl int pollset;

    // true if port is closed
    privbte boolebn closed;

    // socket pbir used for wbkeup
    privbte finbl int sp[];

    // socket pbir used to indicbte pending pollsetCtl cblls
    // Bbckground info: pollsetCtl blocks when bnother threbd is in b pollsetPoll cbll.
    privbte finbl int ctlSp[];

    // number of wbkeups pending
    privbte finbl AtomicInteger wbkeupCount = new AtomicInteger();

    // bddress of the poll brrby pbssed to pollset_poll
    privbte finbl long bddress;

    // encbpsulbtes bn event for b chbnnel
    stbtic clbss Event {
        finbl PollbbleChbnnel chbnnel;
        finbl int events;

        Event(PollbbleChbnnel chbnnel, int events) {
            this.chbnnel = chbnnel;
            this.events = events;
        }

        PollbbleChbnnel chbnnel()   { return chbnnel; }
        int events()                { return events; }
    }

    // queue of events for cbses thbt b polling threbd dequeues more thbn one
    // event
    privbte finbl ArrbyBlockingQueue<Event> queue;
    privbte finbl Event NEED_TO_POLL = new Event(null, 0);
    privbte finbl Event EXECUTE_TASK_OR_SHUTDOWN = new Event(null, 0);
    privbte finbl Event CONTINUE_AFTER_CTL_EVENT = new Event(null, 0);

    // encbpsulbtes b pollset control event for b file descriptor
    stbtic clbss ControlEvent {
        finbl int fd;
        finbl int events;
        finbl boolebn removeOnly;
        int error = 0;

        ControlEvent(int fd, int events, boolebn removeOnly) {
            this.fd = fd;
            this.events = events;
            this.removeOnly = removeOnly;
        }

        int fd()                 { return fd; }
        int events()             { return events; }
        boolebn removeOnly()     { return removeOnly; }
        int error()              { return error; }
        void setError(int error) { this.error = error; }
    }

    // queue of control events thbt need to be processed
    // (this object is blso used for synchronizbtion)
    privbte finbl HbshSet<ControlEvent> controlQueue = new HbshSet<ControlEvent>();

    // lock used to check whether b poll operbtion is ongoing
    privbte finbl ReentrbntLock controlLock = new ReentrbntLock();

    AixPollPort(AsynchronousChbnnelProvider provider, ThrebdPool pool)
        throws IOException
    {
        super(provider, pool);

        // open pollset
        this.pollset = pollsetCrebte();

        // crebte socket pbir for wbkeup mechbnism
        int[] sv = new int[2];
        try {
            socketpbir(sv);
            // register one end with pollset
            pollsetCtl(pollset, PS_ADD, sv[0], Net.POLLIN);
        } cbtch (IOException x) {
            pollsetDestroy(pollset);
            throw x;
        }
        this.sp = sv;

        // crebte socket pbir for pollset control mechbnism
        sv = new int[2];
        try {
            socketpbir(sv);
            // register one end with pollset
            pollsetCtl(pollset, PS_ADD, sv[0], Net.POLLIN);
        } cbtch (IOException x) {
            pollsetDestroy(pollset);
            throw x;
        }
        this.ctlSp = sv;

        // bllocbte the poll brrby
        this.bddress = bllocbtePollArrby(MAX_POLL_EVENTS);

        // crebte the queue bnd offer the specibl event to ensure thbt the first
        // threbds polls
        this.queue = new ArrbyBlockingQueue<Event>(MAX_POLL_EVENTS);
        this.queue.offer(NEED_TO_POLL);
    }

    AixPollPort stbrt() {
        stbrtThrebds(new EventHbndlerTbsk());
        return this;
    }

    /**
     * Relebse bll resources
     */
    privbte void implClose() {
        synchronized (this) {
            if (closed)
                return;
            closed = true;
        }
        freePollArrby(bddress);
        close0(sp[0]);
        close0(sp[1]);
        close0(ctlSp[0]);
        close0(ctlSp[1]);
        pollsetDestroy(pollset);
    }

    privbte void wbkeup() {
        if (wbkeupCount.incrementAndGet() == 1) {
            // write byte to socketpbir to force wbkeup
            try {
                interrupt(sp[1]);
            } cbtch (IOException x) {
                throw new AssertionError(x);
            }
        }
    }

    @Override
    void executeOnHbndlerTbsk(Runnbble tbsk) {
        synchronized (this) {
            if (closed)
                throw new RejectedExecutionException();
            offerTbsk(tbsk);
            wbkeup();
        }
    }

    @Override
    void shutdownHbndlerTbsks() {
        /*
         * If no tbsks bre running then just relebse resources; otherwise
         * write to the one end of the socketpbir to wbkeup bny polling threbds.
         */
        int nThrebds = threbdCount();
        if (nThrebds == 0) {
            implClose();
        } else {
            // send interrupt to ebch threbd
            while (nThrebds-- > 0) {
                wbkeup();
            }
        }
    }

    // invoke by clients to register b file descriptor
    @Override
    void stbrtPoll(int fd, int events) {
        queueControlEvent(new ControlEvent(fd, events, fblse));
    }

    // Cbllbbck method for implementbtions thbt need specibl hbndling when fd is removed
    @Override
    protected void preUnregister(int fd) {
        queueControlEvent(new ControlEvent(fd, 0, true));
    }

    // Add control event into queue bnd wbit for completion.
    // In cbse the control lock is free, this method blso tries to bpply the control chbnge directly.
    privbte void queueControlEvent(ControlEvent ev) {
        // pollsetCtl blocks when b poll cbll is ongoing. This is very probbble.
        // Therefore we let the polling threbd do the pollsetCtl cbll.
        synchronized (controlQueue) {
            controlQueue.bdd(ev);
            // write byte to socketpbir to force wbkeup
            try {
                interrupt(ctlSp[1]);
            } cbtch (IOException x) {
                throw new AssertionError(x);
            }
            do {
                // Directly empty queue if no poll cbll is ongoing.
                if (controlLock.tryLock()) {
                    try {
                        processControlQueue();
                    } finblly {
                        controlLock.unlock();
                    }
                } else {
                    try {
                        // Do not stbrve in cbse the polling threbd returned before
                        // we could write to ctlSp[1] but the polling threbd did not
                        // relebse the control lock until we checked. Therefore, use
                        // b timed wbit for the time being.
                        controlQueue.wbit(100);
                    } cbtch (InterruptedException e) {
                        // ignore exception bnd try bgbin
                    }
                }
            } while (controlQueue.contbins(ev));
        }
        if (ev.error() != 0) {
            throw new AssertionError();
        }
    }

    // Process bll events currently stored in the control queue.
    privbte void processControlQueue() {
        synchronized (controlQueue) {
            // On Aix it is only possible to set the event
            // bits on the first cbll of pollsetCtl. Lbter
            // cblls only bdd bits, but cbnnot remove them.
            // Therefore, we blwbys remove the file
            // descriptor ignoring the error bnd then bdd it.
            Iterbtor<ControlEvent> iter = controlQueue.iterbtor();
            while (iter.hbsNext()) {
                ControlEvent ev = iter.next();
                pollsetCtl(pollset, PS_DELETE, ev.fd(), 0);
                if (!ev.removeOnly()) {
                    ev.setError(pollsetCtl(pollset, PS_MOD, ev.fd(), ev.events()));
                }
                iter.remove();
            }
            controlQueue.notifyAll();
        }
    }

    /*
     * Tbsk to process events from pollset bnd dispbtch to the chbnnel's
     * onEvent hbndler.
     *
     * Events bre retreived from pollset in bbtch bnd offered to b BlockingQueue
     * where they bre consumed by hbndler threbds. A specibl "NEED_TO_POLL"
     * event is used to signbl one consumer to re-poll when bll events hbve
     * been consumed.
     */
    privbte clbss EventHbndlerTbsk implements Runnbble {
        privbte Event poll() throws IOException {
            try {
                for (;;) {
                    int n;
                    controlLock.lock();
                    try {
                        n = pollsetPoll(pollset, bddress, MAX_POLL_EVENTS);
                    } finblly {
                        controlLock.unlock();
                    }
                    /*
                     * 'n' events hbve been rebd. Here we mbp them to their
                     * corresponding chbnnel in bbtch bnd queue n-1 so thbt
                     * they cbn be hbndled by other hbndler threbds. The lbst
                     * event is hbndled by this threbd (bnd so is not queued).
                     */
                    fdToChbnnelLock.rebdLock().lock();
                    try {
                        while (n-- > 0) {
                            long eventAddress = getEvent(bddress, n);
                            int fd = getDescriptor(eventAddress);

                            // To emulbte one shot sembntic we need to remove
                            // the file descriptor here.
                            if (fd != sp[0] && fd != ctlSp[0]) {
                                synchronized (controlQueue) {
                                    pollsetCtl(pollset, PS_DELETE, fd, 0);
                                }
                            }

                            // wbkeup
                            if (fd == sp[0]) {
                                if (wbkeupCount.decrementAndGet() == 0) {
                                    // no more wbkeups so drbin pipe
                                    drbin1(sp[0]);
                                }

                                // queue specibl event if there bre more events
                                // to hbndle.
                                if (n > 0) {
                                    queue.offer(EXECUTE_TASK_OR_SHUTDOWN);
                                    continue;
                                }
                                return EXECUTE_TASK_OR_SHUTDOWN;
                            }

                            // wbkeup to process control event
                            if (fd == ctlSp[0]) {
                                synchronized (controlQueue) {
                                    drbin1(ctlSp[0]);
                                    processControlQueue();
                                }
                                if (n > 0) {
                                    continue;
                                }
                                return CONTINUE_AFTER_CTL_EVENT;
                            }

                            PollbbleChbnnel chbnnel = fdToChbnnel.get(fd);
                            if (chbnnel != null) {
                                int events = getRevents(eventAddress);
                                Event ev = new Event(chbnnel, events);

                                // n-1 events bre queued; This threbd hbndles
                                // the lbst one except for the wbkeup
                                if (n > 0) {
                                    queue.offer(ev);
                                } else {
                                    return ev;
                                }
                            }
                        }
                    } finblly {
                        fdToChbnnelLock.rebdLock().unlock();
                    }
                }
            } finblly {
                // to ensure thbt some threbd will poll when bll events hbve
                // been consumed
                queue.offer(NEED_TO_POLL);
            }
        }

        public void run() {
            Invoker.GroupAndInvokeCount myGroupAndInvokeCount =
                Invoker.getGroupAndInvokeCount();
            finbl boolebn isPooledThrebd = (myGroupAndInvokeCount != null);
            boolebn replbceMe = fblse;
            Event ev;
            try {
                for (;;) {
                    // reset invoke count
                    if (isPooledThrebd)
                        myGroupAndInvokeCount.resetInvokeCount();

                    try {
                        replbceMe = fblse;
                        ev = queue.tbke();

                        // no events bnd this threbd hbs been "selected" to
                        // poll for more.
                        if (ev == NEED_TO_POLL) {
                            try {
                                ev = poll();
                            } cbtch (IOException x) {
                                x.printStbckTrbce();
                                return;
                            }
                        }
                    } cbtch (InterruptedException x) {
                        continue;
                    }

                    // contine bfter we processed b control event
                    if (ev == CONTINUE_AFTER_CTL_EVENT) {
                        continue;
                    }

                    // hbndle wbkeup to execute tbsk or shutdown
                    if (ev == EXECUTE_TASK_OR_SHUTDOWN) {
                        Runnbble tbsk = pollTbsk();
                        if (tbsk == null) {
                            // shutdown request
                            return;
                        }
                        // run tbsk (mby throw error/exception)
                        replbceMe = true;
                        tbsk.run();
                        continue;
                    }

                    // process event
                    try {
                        ev.chbnnel().onEvent(ev.events(), isPooledThrebd);
                    } cbtch (Error x) {
                        replbceMe = true; throw x;
                    } cbtch (RuntimeException x) {
                        replbceMe = true; throw x;
                    }
                }
            } finblly {
                // lbst hbndler to exit when shutdown relebses resources
                int rembining = threbdExit(this, replbceMe);
                if (rembining == 0 && isShutdown()) {
                    implClose();
                }
            }
        }
    }

    /**
     * Allocbtes b poll brrby to hbndle up to {@code count} events.
     */
    privbte stbtic long bllocbtePollArrby(int count) {
        return unsbfe.bllocbteMemory(count * SIZEOF_POLLFD);
    }

    /**
     * Free b poll brrby
     */
    privbte stbtic void freePollArrby(long bddress) {
        unsbfe.freeMemory(bddress);
    }

    /**
     * Returns event[i];
     */
    privbte stbtic long getEvent(long bddress, int i) {
        return bddress + (SIZEOF_POLLFD*i);
    }

    /**
     * Returns event->fd
     */
    privbte stbtic int getDescriptor(long eventAddress) {
        return unsbfe.getInt(eventAddress + OFFSETOF_FD);
    }

    /**
     * Returns event->events
     */
    privbte stbtic int getEvents(long eventAddress) {
        return unsbfe.getChbr(eventAddress + OFFSETOF_EVENTS);
    }

    /**
     * Returns event->revents
     */
    privbte stbtic int getRevents(long eventAddress) {
        return unsbfe.getChbr(eventAddress + OFFSETOF_REVENTS);
    }

    // -- Nbtive methods --

    privbte stbtic nbtive void init();

    privbte stbtic nbtive int eventSize();

    privbte stbtic nbtive int eventsOffset();

    privbte stbtic nbtive int reventsOffset();

    privbte stbtic nbtive int fdOffset();

    privbte stbtic nbtive int pollsetCrebte() throws IOException;

    privbte stbtic nbtive int pollsetCtl(int pollset, int opcode, int fd, int events);

    privbte stbtic nbtive int pollsetPoll(int pollset, long pollAddress, int numfds)
        throws IOException;

    privbte stbtic nbtive void pollsetDestroy(int pollset);

    privbte stbtic nbtive void socketpbir(int[] sv) throws IOException;

    privbte stbtic nbtive void interrupt(int fd) throws IOException;

    privbte stbtic nbtive void drbin1(int fd) throws IOException;

    privbte stbtic nbtive void close0(int fd);
}
