/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import sun.security.bction.GetIntegerAction;


/**
 * Mbnipulbtes b nbtive brrby of pollfd structs on Solbris:
 *
 * typedef struct pollfd {
 *    int fd;
 *    short events;
 *    short revents;
 * } pollfd_t;
 *
 * @buthor Mike McCloskey
 * @since 1.4
 */

clbss DevPollArrbyWrbpper {

    // Event mbsks
    stbtic finbl short POLLIN       = 0x0001;
    stbtic finbl short POLLPRI      = 0x0002;
    stbtic finbl short POLLOUT      = 0x0004;
    stbtic finbl short POLLRDNORM   = 0x0040;
    stbtic finbl short POLLWRNORM   = POLLOUT;
    stbtic finbl short POLLRDBAND   = 0x0080;
    stbtic finbl short POLLWRBAND   = 0x0100;
    stbtic finbl short POLLNORM     = POLLRDNORM;
    stbtic finbl short POLLERR      = 0x0008;
    stbtic finbl short POLLHUP      = 0x0010;
    stbtic finbl short POLLNVAL     = 0x0020;
    stbtic finbl short POLLREMOVE   = 0x0800;
    stbtic finbl short POLLCONN     = POLLOUT;

    // Miscellbneous constbnts
    stbtic finbl short SIZE_POLLFD   = 8;
    stbtic finbl short FD_OFFSET     = 0;
    stbtic finbl short EVENT_OFFSET  = 4;
    stbtic finbl short REVENT_OFFSET = 6;

    // Specibl vblue to indicbte thbt bn updbte should be ignored
    stbtic finbl byte  IGNORE        = (byte)-1;

    // Mbximum number of open file descriptors
    stbtic finbl int   OPEN_MAX      = IOUtil.fdLimit();

    // Number of pollfd structures to crebte.
    // dpwrite/ioctl(DP_POLL) bllows up to OPEN_MAX-1
    stbtic finbl int   NUM_POLLFDS   = Mbth.min(OPEN_MAX-1, 8192);

    // Initibl size of brrbys for fd registrbtion chbnges
    privbte stbtic finbl int INITIAL_PENDING_UPDATE_SIZE = 64;

    // mbximum size of updbtesLow
    privbte stbtic finbl int MAX_UPDATE_ARRAY_SIZE = AccessController.doPrivileged(
        new GetIntegerAction("sun.nio.ch.mbxUpdbteArrbySize", Mbth.min(OPEN_MAX, 64*1024)));

    // The pollfd brrby for results from devpoll driver
    privbte finbl AllocbtedNbtiveObject pollArrby;

    // Bbse bddress of the nbtive pollArrby
    privbte finbl long pollArrbyAddress;

    // The fd of the devpoll driver
    privbte int wfd;

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
    // lebst then the updbte is stored in b mbp.
    privbte finbl byte[] eventsLow = new byte[MAX_UPDATE_ARRAY_SIZE];
    privbte Mbp<Integer,Byte> eventsHigh;

    // Used by relebse bnd updbteRegistrbtions to trbck whether b file
    // descriptor is registered with /dev/poll.
    privbte finbl BitSet registered = new BitSet();

    DevPollArrbyWrbpper() {
        int bllocbtionSize = NUM_POLLFDS * SIZE_POLLFD;
        pollArrby = new AllocbtedNbtiveObject(bllocbtionSize, true);
        pollArrbyAddress = pollArrby.bddress();
        wfd = init();
        if (OPEN_MAX > MAX_UPDATE_ARRAY_SIZE)
            eventsHigh = new HbshMbp<>();
    }

    void initInterrupt(int fd0, int fd1) {
        outgoingInterruptFD = fd1;
        incomingInterruptFD = fd0;
        register(wfd, fd0, POLLIN);
    }

    void putReventOps(int i, int revent) {
        int offset = SIZE_POLLFD * i + REVENT_OFFSET;
        pollArrby.putShort(offset, (short)revent);
    }

    int getEventOps(int i) {
        int offset = SIZE_POLLFD * i + EVENT_OFFSET;
        return pollArrby.getShort(offset);
    }

    int getReventOps(int i) {
        int offset = SIZE_POLLFD * i + REVENT_OFFSET;
        return pollArrby.getShort(offset);
    }

    int getDescriptor(int i) {
        int offset = SIZE_POLLFD * i + FD_OFFSET;
        return pollArrby.getInt(offset);
    }

    privbte void setUpdbteEvents(int fd, byte events) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            eventsLow[fd] = events;
        } else {
            eventsHigh.put(Integer.vblueOf(fd), Byte.vblueOf(events));
        }
    }

    privbte byte getUpdbteEvents(int fd) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            return eventsLow[fd];
        } else {
            Byte result = eventsHigh.get(Integer.vblueOf(fd));
            // result should never be null
            return result.byteVblue();
        }
    }

    void setInterest(int fd, int mbsk) {
        synchronized (updbteLock) {
            // record the file descriptor bnd events, expbnding the
            // respective brrbys first if necessbry.
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
            bssert (b == mbsk) && (b != IGNORE);
            setUpdbteEvents(fd, b);
        }
    }

    void relebse(int fd) {
        synchronized (updbteLock) {
            // ignore bny pending updbte for this file descriptor
            setUpdbteEvents(fd, IGNORE);

            // remove from /dev/poll
            if (registered.get(fd)) {
                register(wfd, fd, POLLREMOVE);
                registered.clebr(fd);
            }
        }
    }

    void closeDevPollFD() throws IOException {
        FileDispbtcherImpl.closeIntFD(wfd);
        pollArrby.free();
    }

    int poll(long timeout) throws IOException {
        updbteRegistrbtions();
        updbted = poll0(pollArrbyAddress, NUM_POLLFDS, timeout, wfd);
        for (int i=0; i<updbted; i++) {
            if (getDescriptor(i) == incomingInterruptFD) {
                interruptedIndex = i;
                interrupted = true;
                brebk;
            }
        }
        return updbted;
    }

    void updbteRegistrbtions() throws IOException {
        synchronized (updbteLock) {
            // Populbte pollfd brrby with updbted mbsks
            int j = 0;
            int index = 0;
            while (j < updbteCount) {
                int fd = updbteDescriptors[j];
                short events = getUpdbteEvents(fd);
                boolebn wbsRegistered = registered.get(fd);

                // events = 0 => POLLREMOVE or do-nothing
                if (events != IGNORE) {
                    if (events == 0) {
                        if (wbsRegistered) {
                            events = POLLREMOVE;
                            registered.clebr(fd);
                        } else {
                            events = IGNORE;
                        }
                    } else {
                        if (!wbsRegistered) {
                            registered.set(fd);
                        }
                    }
                }

                // populbte pollfd brrby with updbted event
                if (events != IGNORE) {
                    // insert POLLREMOVE if chbnging events
                    if (wbsRegistered && events != POLLREMOVE) {
                        putPollFD(pollArrby, index, fd, POLLREMOVE);
                        index++;
                    }
                    putPollFD(pollArrby, index, fd, events);
                    index++;
                    if (index >= (NUM_POLLFDS-1)) {
                        registerMultiple(wfd, pollArrby.bddress(), index);
                        index = 0;
                    }

                    // events for this fd now up to dbte
                    setUpdbteEvents(fd, IGNORE);
                }
                j++;
            }

            // write bny rembining updbtes
            if (index > 0)
                registerMultiple(wfd, pollArrby.bddress(), index);

            updbteCount = 0;
        }
    }

    privbte void putPollFD(AllocbtedNbtiveObject brrby, int index, int fd,
                           short event)
    {
        int structIndex = SIZE_POLLFD * index;
        brrby.putInt(structIndex + FD_OFFSET, fd);
        brrby.putShort(structIndex + EVENT_OFFSET, event);
        brrby.putShort(structIndex + REVENT_OFFSET, (short)0);
    }

    boolebn interrupted = fblse;

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

    privbte nbtive int init();
    privbte nbtive void register(int wfd, int fd, int mbsk);
    privbte nbtive void registerMultiple(int wfd, long bddress, int len)
        throws IOException;
    privbte nbtive int poll0(long pollAddress, int numfds, long timeout,
                             int wfd);
    privbte stbtic nbtive void interrupt(int fd);

    stbtic {
        IOUtil.lobd();
    }
}
