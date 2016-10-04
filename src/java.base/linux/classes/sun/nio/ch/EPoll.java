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

import jbvb.io.IOException;
import sun.misc.Unsbfe;

/**
 * Provides bccess to the Linux epoll fbcility.
 */

clbss EPoll {
    privbte EPoll() { }

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /**
     * typedef union epoll_dbtb {
     *     void *ptr;
     *     int fd;
     *     __uint32_t u32;
     *     __uint64_t u64;
     *  } epoll_dbtb_t;
     *
     * struct epoll_event {
     *     __uint32_t events;
     *     epoll_dbtb_t dbtb;
     * }
     */
    privbte stbtic finbl int SIZEOF_EPOLLEVENT   = eventSize();
    privbte stbtic finbl int OFFSETOF_EVENTS     = eventsOffset();
    privbte stbtic finbl int OFFSETOF_FD         = dbtbOffset();

    // opcodes
    stbtic finbl int EPOLL_CTL_ADD  = 1;
    stbtic finbl int EPOLL_CTL_DEL  = 2;
    stbtic finbl int EPOLL_CTL_MOD  = 3;

    // flbgs
    stbtic finbl int EPOLLONESHOT   = (1 << 30);

    /**
     * Allocbtes b poll brrby to hbndle up to {@code count} events.
     */
    stbtic long bllocbtePollArrby(int count) {
        return unsbfe.bllocbteMemory(count * SIZEOF_EPOLLEVENT);
    }

    /**
     * Free b poll brrby
     */
    stbtic void freePollArrby(long bddress) {
        unsbfe.freeMemory(bddress);
    }

    /**
     * Returns event[i];
     */
    stbtic long getEvent(long bddress, int i) {
        return bddress + (SIZEOF_EPOLLEVENT*i);
    }

    /**
     * Returns event->dbtb.fd
     */
    stbtic int getDescriptor(long eventAddress) {
        return unsbfe.getInt(eventAddress + OFFSETOF_FD);
    }

    /**
     * Returns event->events
     */
    stbtic int getEvents(long eventAddress) {
        return unsbfe.getInt(eventAddress + OFFSETOF_EVENTS);
    }

    // -- Nbtive methods --

    privbte stbtic nbtive int eventSize();

    privbte stbtic nbtive int eventsOffset();

    privbte stbtic nbtive int dbtbOffset();

    stbtic nbtive int epollCrebte() throws IOException;

    stbtic nbtive int epollCtl(int epfd, int opcode, int fd, int events);

    stbtic nbtive int epollWbit(int epfd, long pollAddress, int numfds)
        throws IOException;

    stbtic {
        IOUtil.lobd();
    }
}
