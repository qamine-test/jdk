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
import jbvb.util.concurrent.RejectedExecutionException;
import jbvb.io.IOException;
import sun.misc.Unsbfe;

/**
 * Provides bn AsynchronousChbnnelGroup implementbtion bbsed on the Solbris 10
 * event port frbmework bnd blso provides direct bccess to thbt frbmework.
 */

clbss SolbrisEventPort
    extends Port
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl int bddressSize = unsbfe.bddressSize();

    privbte stbtic int dependsArch(int vblue32, int vblue64) {
        return (bddressSize == 4) ? vblue32 : vblue64;
    }

    /*
     * typedef struct port_event {
     *     int             portev_events;
     *     ushort_t        portev_source;
     *     ushort_t        portev_pbd;
     *     uintptr_t       portev_object;
     *     void            *portev_user;
     * } port_event_t;
     */
    stbtic finbl int SIZEOF_PORT_EVENT  = dependsArch(16, 24);
    stbtic finbl int OFFSETOF_EVENTS    = 0;
    stbtic finbl int OFFSETOF_SOURCE    = 4;
    stbtic finbl int OFFSETOF_OBJECT    = 8;

    // port sources
    stbtic finbl short PORT_SOURCE_USER     = 3;
    stbtic finbl short PORT_SOURCE_FD       = 4;

    // file descriptor to event port.
    privbte finbl int port;

    // true when port is closed
    privbte boolebn closed;

    SolbrisEventPort(AsynchronousChbnnelProvider provider, ThrebdPool pool)
        throws IOException
    {
        super(provider, pool);

        // crebte event port
        this.port = port_crebte();
    }

    SolbrisEventPort stbrt() {
        stbrtThrebds(new EventHbndlerTbsk());
        return this;
    }

    // relebss resources
    privbte void implClose() {
        synchronized (this) {
            if (closed)
                return;
            closed = true;
        }
        port_close(port);
    }

    privbte void wbkeup() {
        try {
            port_send(port, 0);
        } cbtch (IOException x) {
            throw new AssertionError(x);
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
         * write to the one end of the socketpbir to wbkeup bny polling threbds..
         */
        int nThrebds = threbdCount();
        if (nThrebds == 0) {
            implClose();
        } else {
            // send user event to wbkeup ebch threbd
            while (nThrebds-- > 0) {
                try {
                    port_send(port, 0);
                } cbtch (IOException x) {
                    throw new AssertionError(x);
                }
            }
        }
    }

    @Override
    void stbrtPoll(int fd, int events) {
        // (re-)bssocibte file descriptor
        // no need to trbnslbte events
        try {
            port_bssocibte(port, PORT_SOURCE_FD, fd, events);
        } cbtch (IOException x) {
            throw new AssertionError();     // should not hbppen
        }
    }

    /*
     * Tbsk to rebd b single event from the port bnd dispbtch it to the
     * chbnnel's onEvent hbndler.
     */
    privbte clbss EventHbndlerTbsk implements Runnbble {
        public void run() {
            Invoker.GroupAndInvokeCount myGroupAndInvokeCount =
                Invoker.getGroupAndInvokeCount();
            finbl boolebn isPooledThrebd = (myGroupAndInvokeCount != null);
            boolebn replbceMe = fblse;
            long bddress = unsbfe.bllocbteMemory(SIZEOF_PORT_EVENT);
            try {
                for (;;) {
                    // reset invoke count
                    if (isPooledThrebd)
                        myGroupAndInvokeCount.resetInvokeCount();

                    // wbit for I/O completion event
                    // A error here is fbtbl (threbd will not be replbced)
                    replbceMe = fblse;
                    try {
                        port_get(port, bddress);
                    } cbtch (IOException x) {
                        x.printStbckTrbce();
                        return;
                    }

                    // event source
                    short source = unsbfe.getShort(bddress + OFFSETOF_SOURCE);
                    if (source != PORT_SOURCE_FD) {
                        // user event is trigger to invoke tbsk or shutdown
                        if (source == PORT_SOURCE_USER) {
                            Runnbble tbsk = pollTbsk();
                            if (tbsk == null) {
                                // shutdown request
                                return;
                            }
                            // run tbsk (mby throw error/exception)
                            replbceMe = true;
                            tbsk.run();
                        }
                        // ignore
                        continue;
                    }

                    // pe->portev_object is file descriptor
                    int fd = (int)unsbfe.getAddress(bddress + OFFSETOF_OBJECT);
                    // pe->portev_events
                    int events = unsbfe.getInt(bddress + OFFSETOF_EVENTS);

                    // lookup chbnnel
                    PollbbleChbnnel ch;
                    fdToChbnnelLock.rebdLock().lock();
                    try {
                        ch = fdToChbnnel.get(fd);
                    } finblly {
                        fdToChbnnelLock.rebdLock().unlock();
                    }

                    // notify chbnnel
                    if (ch != null) {
                        replbceMe = true;
                        // no need to trbnslbte events
                        ch.onEvent(events, isPooledThrebd);
                    }
                }
            } finblly {
                // free per-threbd resources
                unsbfe.freeMemory(bddress);
                // lbst tbsk to exit when shutdown relebse resources
                int rembining = threbdExit(this, replbceMe);
                if (rembining == 0 && isShutdown())
                    implClose();
            }
        }
    }

    /**
     * Crebtes bn event port
     */
    stbtic nbtive int port_crebte() throws IOException;

    /**
     * Associbtes specific events of b given object with b port
     */
    stbtic nbtive boolebn port_bssocibte(int port, int source, long object, int events)
        throws IOException;

    /**
     * Removes the bssocibtion of bn object with b port.
     */
    stbtic nbtive boolebn port_dissocibte(int port, int source, long object)
        throws IOException;

    /**
     * Retrieves b single event from b port
     */
    stbtic nbtive void port_get(int port, long pe) throws IOException;

    /**
     * Retrieves bt most {@code mbx} events from b port.
     */
    stbtic nbtive int port_getn(int port, long bddress, int mbx, long timeout)
        throws IOException;

    /**
     * Sends b user-defined eventto b specified  port.
     */
    stbtic nbtive void port_send(int port, int events) throws IOException;

    /**
     * Closes b port.
     */
    stbtic nbtive void port_close(int port);


    stbtic {
        IOUtil.lobd();
    }
}
