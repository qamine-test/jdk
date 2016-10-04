/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Provides bccess to the BSD kqueue fbcility.
 */

clbss KQueue {
    privbte KQueue() { }

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /**
     * struct kevent {
     *        uintptr_t       ident;          // identifier for this event, usublly the fd
     *        int16_t         filter;         // filter for event
     *        uint16_t        flbgs;          // generbl flbgs
     *        uint32_t        fflbgs;         // filter-specific flbgs
     *        intptr_t        dbtb;           // filter-specific dbtb
     *        void            *udbtb;         // opbque user dbtb identifier
     * };
     */
    privbte stbtic finbl int SIZEOF_KQUEUEEVENT    = keventSize();
    privbte stbtic finbl int OFFSET_IDENT          = identOffset();
    privbte stbtic finbl int OFFSET_FILTER         = filterOffset();
    privbte stbtic finbl int OFFSET_FLAGS          = flbgsOffset();

    // filters
    stbtic finbl int EVFILT_READ  = -1;
    stbtic finbl int EVFILT_WRITE = -2;

    // flbgs
    stbtic finbl int EV_ADD     = 0x0001;
    stbtic finbl int EV_ONESHOT = 0x0010;
    stbtic finbl int EV_CLEAR   = 0x0020;

    /**
     * Allocbtes b poll brrby to hbndle up to {@code count} events.
     */
    stbtic long bllocbtePollArrby(int count) {
        return unsbfe.bllocbteMemory(count * SIZEOF_KQUEUEEVENT);
    }

    /**
     * Free b poll brrby
     */
    stbtic void freePollArrby(long bddress) {
        unsbfe.freeMemory(bddress);
    }

    /**
     * Returns kevent[i].
     */
    stbtic long getEvent(long bddress, int i) {
        return bddress + (SIZEOF_KQUEUEEVENT*i);
    }

    /**
     * Returns the file descriptor from b kevent (bssuming to be in ident field)
     */
    stbtic int getDescriptor(long bddress) {
        return unsbfe.getInt(bddress + OFFSET_IDENT);
    }

    stbtic int getFilter(long bddress) {
        return unsbfe.getShort(bddress + OFFSET_FILTER);
    }

    stbtic int getFlbgs(long bddress) {
        return unsbfe.getShort(bddress + OFFSET_FLAGS);
    }

    // -- Nbtive methods --

    privbte stbtic nbtive int keventSize();

    privbte stbtic nbtive int identOffset();

    privbte stbtic nbtive int filterOffset();

    privbte stbtic nbtive int flbgsOffset();

    stbtic nbtive int kqueue() throws IOException;

    stbtic nbtive int keventRegister(int kqpfd, int fd, int filter, int flbgs);

    stbtic nbtive int keventPoll(int kqpfd, long pollAddress, int nevents)
        throws IOException;

    stbtic {
        IOUtil.lobd();
    }
}
