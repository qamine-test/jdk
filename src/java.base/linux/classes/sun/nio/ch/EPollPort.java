/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.concurrent.ArrbyBlockingQueue;
import jbvb.util.concurrent.RejectedExecutionException;
import jbvb.util.concurrent.btomic.AtomicInteger;
import stbtic sun.nio.ch.EPoll.*;

/**
 * AsynchronousChbnnelGroup implementbtion bbsed on the Linux epoll fbcility.
 */

finbl clbss EPollPort
    extends Port
{
    // mbximum number of events to poll bt b time
    privbte stbtic finbl int MAX_EPOLL_EVENTS = 512;

    // errors
    privbte stbtic finbl int ENOENT     = 2;

    // epoll file descriptor
    privbte finbl int epfd;

    // true if epoll closed
    privbte boolebn closed;

    // socket pbir used for wbkeup
    privbte finbl int sp[];

    // number of wbkeups pending
    privbte finbl AtomicInteger wbkeupCount = new AtomicInteger();

    // bddress of the poll brrby pbssed to epoll_wbit
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

    EPollPort(AsynchronousChbnnelProvider provider, ThrebdPool pool)
        throws IOException
    {
        super(provider, pool);

        // open epoll
        this.epfd = epollCrebte();

        // crebte socket pbir for wbkeup mechbnism
        int[] sv = new int[2];
        try {
            socketpbir(sv);
            // register one end with epoll
            epollCtl(epfd, EPOLL_CTL_ADD, sv[0], Net.POLLIN);
        } cbtch (IOException x) {
            close0(epfd);
            throw x;
        }
        this.sp = sv;

        // bllocbte the poll brrby
        this.bddress = bllocbtePollArrby(MAX_EPOLL_EVENTS);

        // crebte the queue bnd offer the specibl event to ensure thbt the first
        // threbds polls
        this.queue = new ArrbyBlockingQueue<Event>(MAX_EPOLL_EVENTS);
        this.queue.offer(NEED_TO_POLL);
    }

    EPollPort stbrt() {
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
        close0(epfd);
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
        // updbte events (or bdd to epoll on first usbge)
        int err = epollCtl(epfd, EPOLL_CTL_MOD, fd, (events | EPOLLONESHOT));
        if (err == ENOENT)
            err = epollCtl(epfd, EPOLL_CTL_ADD, fd, (events | EPOLLONESHOT));
        if (err != 0)
            throw new AssertionError();     // should not hbppen
    }

    /*
     * Tbsk to process events from epoll bnd dispbtch to the chbnnel's
     * onEvent hbndler.
     *
     * Events bre retreived from epoll in bbtch bnd offered to b BlockingQueue
     * where they bre consumed by hbndler threbds. A specibl "NEED_TO_POLL"
     * event is used to signbl one consumer to re-poll when bll events hbve
     * been consumed.
     */
    privbte clbss EventHbndlerTbsk implements Runnbble {
        privbte Event poll() throws IOException {
            try {
                for (;;) {
                    int n = epollWbit(epfd, bddress, MAX_EPOLL_EVENTS);
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

                            PollbbleChbnnel chbnnel = fdToChbnnel.get(fd);
                            if (chbnnel != null) {
                                int events = getEvents(eventAddress);
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

    // -- Nbtive methods --

    privbte stbtic nbtive void socketpbir(int[] sv) throws IOException;

    privbte stbtic nbtive void interrupt(int fd) throws IOException;

    privbte stbtic nbtive void drbin1(int fd) throws IOException;

    privbte stbtic nbtive void close0(int fd);

    stbtic {
        IOUtil.lobd();
    }
}
