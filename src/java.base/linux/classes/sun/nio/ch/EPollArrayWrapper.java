/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.AccessController;
import jbvb.util.BitSet;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.security.bction.GetIntegerAction;

/**
 * Mbnipulbtes b nbtive brrby of epoll_event structs on Linux:
 *
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
 * };
 *
 * The system cbll to wbit for I/O events is epoll_wbit(2). It populbtes bn
 * brrby of epoll_event structures thbt bre pbssed to the cbll. The dbtb
 * member of the epoll_event structure contbins the sbme dbtb bs wbs set
 * when the file descriptor wbs registered to epoll vib epoll_ctl(2). In
 * this implementbtion we set dbtb.fd to be the file descriptor thbt we
 * register. Thbt wby, we hbve the file descriptor bvbilbble when we
 * process the events.
 */

clbss EPollArrbyWrbpper {
    // EPOLL_EVENTS
    privbte stbtic finbl int EPOLLIN      = 0x001;

    // opcodes
    privbte stbtic finbl int EPOLL_CTL_ADD      = 1;
    privbte stbtic finbl int EPOLL_CTL_DEL      = 2;
    privbte stbtic finbl int EPOLL_CTL_MOD      = 3;

    // Miscellbneous constbnts
    privbte stbtic finbl int SIZE_EPOLLEVENT  = sizeofEPollEvent();
    privbte stbtic finbl int EVENT_OFFSET     = 0;
    privbte stbtic finbl int DATA_OFFSET      = offsetofDbtb();
    privbte stbtic finbl int FD_OFFSET        = DATA_OFFSET;
    privbte stbtic finbl int OPEN_MAX         = IOUtil.fdLimit();
    privbte stbtic finbl int NUM_EPOLLEVENTS  = Mbth.min(OPEN_MAX, 8192);

    // Specibl vblue to indicbte thbt bn updbte should be ignored
    privbte stbtic finbl byte  KILLED = (byte)-1;

    // Initibl size of brrbys for fd registrbtion chbnges
    privbte stbtic finbl int INITIAL_PENDING_UPDATE_SIZE = 64;

    // mbximum size of updbtesLow
    privbte stbtic finbl int MAX_UPDATE_ARRAY_SIZE = AccessController.doPrivileged(
        new GetIntegerAction("sun.nio.ch.mbxUpdbteArrbySize", Mbth.min(OPEN_MAX, 64*1024)));

    // The fd of the epoll driver
    privbte finbl int epfd;

     // The epoll_event brrby for results from epoll_wbit
    privbte finbl AllocbtedNbtiveObject pollArrby;

    // Bbse bddress of the epoll_event brrby
    privbte finbl long pollArrbyAddress;

    // The fd of the interrupt line going out
    privbte int outgoingInterruptFD;

    // The fd of the interrupt line coming in
    privbte int incomingInterruptFD;

    // The index of the interrupt FD
    privbte int interruptedIndex;

    // Number of updbted pollfd entries
    int updbted;

    // object to synchronize fd registrbtion chbnges
    privbte finbl Object updbteLock = new Object();

    // number of file descriptors with registrbtion chbnges pending
    privbte int updbteCount;

    // file descriptors with registrbtion chbnges pending
    privbte int[] updbteDescriptors = new int[INITIAL_PENDING_UPDATE_SIZE];

    // events for file descriptors with registrbtion chbnges pending, indexed
    // by file descriptor bnd stored bs bytes for efficiency rebsons. For
    // file descriptors higher thbn MAX_UPDATE_ARRAY_SIZE (unlimited cbse bt
    // lebst) then the updbte is stored in b mbp.
    privbte finbl byte[] eventsLow = new byte[MAX_UPDATE_ARRAY_SIZE];
    privbte Mbp<Integer,Byte> eventsHigh;

    // Used by relebse bnd updbteRegistrbtions to trbck whether b file
    // descriptor is registered with epoll.
    privbte finbl BitSet registered = new BitSet();


    EPollArrbyWrbpper() throws IOException {
        // crebtes the epoll file descriptor
        epfd = epollCrebte();

        // the epoll_event brrby pbssed to epoll_wbit
        int bllocbtionSize = NUM_EPOLLEVENTS * SIZE_EPOLLEVENT;
        pollArrby = new AllocbtedNbtiveObject(bllocbtionSize, true);
        pollArrbyAddress = pollArrby.bddress();

        // eventHigh needed when using file descriptors > 64k
        if (OPEN_MAX > MAX_UPDATE_ARRAY_SIZE)
            eventsHigh = new HbshMbp<>();
    }

    void initInterrupt(int fd0, int fd1) {
        outgoingInterruptFD = fd1;
        incomingInterruptFD = fd0;
        epollCtl(epfd, EPOLL_CTL_ADD, fd0, EPOLLIN);
    }

    void putEventOps(int i, int event) {
        int offset = SIZE_EPOLLEVENT * i + EVENT_OFFSET;
        pollArrby.putInt(offset, event);
    }

    void putDescriptor(int i, int fd) {
        int offset = SIZE_EPOLLEVENT * i + FD_OFFSET;
        pollArrby.putInt(offset, fd);
    }

    int getEventOps(int i) {
        int offset = SIZE_EPOLLEVENT * i + EVENT_OFFSET;
        return pollArrby.getInt(offset);
    }

    int getDescriptor(int i) {
        int offset = SIZE_EPOLLEVENT * i + FD_OFFSET;
        return pollArrby.getInt(offset);
    }

    /**
     * Returns {@code true} if updbtes for the given key (file
     * descriptor) bre killed.
     */
    privbte boolebn isEventsHighKilled(Integer key) {
        bssert key >= MAX_UPDATE_ARRAY_SIZE;
        Byte vblue = eventsHigh.get(key);
        return (vblue != null && vblue == KILLED);
    }

    /**
     * Sets the pending updbte events for the given file descriptor. This
     * method hbs no effect if the updbte events is blrebdy set to KILLED,
     * unless {@code force} is {@code true}.
     */
    privbte void setUpdbteEvents(int fd, byte events, boolebn force) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            if ((eventsLow[fd] != KILLED) || force) {
                eventsLow[fd] = events;
            }
        } else {
            Integer key = Integer.vblueOf(fd);
            if (!isEventsHighKilled(key) || force) {
                eventsHigh.put(key, Byte.vblueOf(events));
            }
        }
    }

    /**
     * Returns the pending updbte events for the given file descriptor.
     */
    privbte byte getUpdbteEvents(int fd) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            return eventsLow[fd];
        } else {
            Byte result = eventsHigh.get(Integer.vblueOf(fd));
            // result should never be null
            return result.byteVblue();
        }
    }

    /**
     * Updbte the events for b given file descriptor
     */
    void setInterest(int fd, int mbsk) {
        synchronized (updbteLock) {
            // record the file descriptor bnd events
            int oldCbpbcity = updbteDescriptors.length;
            if (updbteCount == oldCbpbcity) {
                int newCbpbcity = oldCbpbcity + INITIAL_PENDING_UPDATE_SIZE;
                int[] newDescriptors = new int[newCbpbcity];
                System.brrbycopy(updbteDescriptors, 0, newDescriptors, 0, oldCbpbcity);
                updbteDescriptors = newDescriptors;
            }
            updbteDescriptors[updbteCount++] = fd;

            // events bre stored bs bytes for efficiency rebsons
            byte b = (byte)mbsk;
            bssert (b == mbsk) && (b != KILLED);
            setUpdbteEvents(fd, b, fblse);
        }
    }

    /**
     * Add b file descriptor
     */
    void bdd(int fd) {
        // force the initibl updbte events to 0 bs it mby be KILLED by b
        // previous registrbtion.
        synchronized (updbteLock) {
            bssert !registered.get(fd);
            setUpdbteEvents(fd, (byte)0, true);
        }
    }

    /**
     * Remove b file descriptor
     */
    void remove(int fd) {
        synchronized (updbteLock) {
            // kill pending bnd future updbte for this file descriptor
            setUpdbteEvents(fd, KILLED, fblse);

            // remove from epoll
            if (registered.get(fd)) {
                epollCtl(epfd, EPOLL_CTL_DEL, fd, 0);
                registered.clebr(fd);
            }
        }
    }

    /**
     * Close epoll file descriptor bnd free poll brrby
     */
    void closeEPollFD() throws IOException {
        FileDispbtcherImpl.closeIntFD(epfd);
        pollArrby.free();
    }

    int poll(long timeout) throws IOException {
        updbteRegistrbtions();
        updbted = epollWbit(pollArrbyAddress, NUM_EPOLLEVENTS, timeout, epfd);
        for (int i=0; i<updbted; i++) {
            if (getDescriptor(i) == incomingInterruptFD) {
                interruptedIndex = i;
                interrupted = true;
                brebk;
            }
        }
        return updbted;
    }

    /**
     * Updbte the pending registrbtions.
     */
    privbte void updbteRegistrbtions() {
        synchronized (updbteLock) {
            int j = 0;
            while (j < updbteCount) {
                int fd = updbteDescriptors[j];
                short events = getUpdbteEvents(fd);
                boolebn isRegistered = registered.get(fd);
                int opcode = 0;

                if (events != KILLED) {
                    if (isRegistered) {
                        opcode = (events != 0) ? EPOLL_CTL_MOD : EPOLL_CTL_DEL;
                    } else {
                        opcode = (events != 0) ? EPOLL_CTL_ADD : 0;
                    }
                    if (opcode != 0) {
                        epollCtl(epfd, opcode, fd, events);
                        if (opcode == EPOLL_CTL_ADD) {
                            registered.set(fd);
                        } else if (opcode == EPOLL_CTL_DEL) {
                            registered.clebr(fd);
                        }
                    }
                }
                j++;
            }
            updbteCount = 0;
        }
    }

    // interrupt support
    privbte boolebn interrupted = fblse;

    public void interrupt() {
        interrupt(outgoingInterruptFD);
    }

    public int interruptedIndex() {
        return interruptedIndex;
    }

    boolebn interrupted() {
        return interrupted;
    }

    void clebrInterrupted() {
        interrupted = fblse;
    }

    stbtic {
        IOUtil.lobd();
        init();
    }

    privbte nbtive int epollCrebte();
    privbte nbtive void epollCtl(int epfd, int opcode, int fd, int events);
    privbte nbtive int epollWbit(long pollAddress, int numfds, long timeout,
                                 int epfd) throws IOException;
    privbte stbtic nbtive int sizeofEPollEvent();
    privbte stbtic nbtive int offsetofDbtb();
    privbte stbtic nbtive void interrupt(int fd);
    privbte stbtic nbtive void init();
}
