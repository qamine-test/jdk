/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * KQueueArrbyWrbpper.jbvb
 * Implementbtion of Selector using FreeBSD / Mbc OS X kqueues
 * Derived from Sun's DevPollArrbyWrbpper
 */

pbckbge sun.nio.ch;

import sun.misc.*;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;

/*
 * struct kevent {           // 32-bit    64-bit
 *     uintptr_t ident;      //   4         8
 *     short     filter;     //   2         2
 *     u_short   flbgs;      //   2         2
 *     u_int     fflbgs;     //   4         4
 *     intptr_t  dbtb;       //   4         8
 *     void      *udbtb;     //   4         8
 * }                  // Totbl:  20        32
 *
 * The implementbtion works in 32-bit bnd 64-bit world. We do this by cblling b
 * nbtive function thbt bctublly sets the sizes bnd offsets of the fields bbsed
 * on which mode we're in.
 */

clbss KQueueArrbyWrbpper {
    // kevent filters
    stbtic short EVFILT_READ;
    stbtic short EVFILT_WRITE;

    // kevent struct
    // These fields bre now set by initStructSizes in the stbtic initiblizer.
    stbtic short SIZEOF_KEVENT;
    stbtic short FD_OFFSET;
    stbtic short FILTER_OFFSET;

    // kevent brrby size
    stbtic finbl int NUM_KEVENTS = 128;

    // Are we in b 64-bit VM?
    stbtic boolebn is64bit = fblse;

    // The kevent brrby (used for outcoming events only)
    privbte AllocbtedNbtiveObject keventArrby = null;
    privbte long keventArrbyAddress;

    // The kqueue fd
    privbte int kq = -1;

    // The fd of the interrupt line going out
    privbte int outgoingInterruptFD;

    // The fd of the interrupt line coming in
    privbte int incomingInterruptFD;

    stbtic {
        IOUtil.lobd();
        initStructSizes();
        String dbtbmodel = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.brch.dbtb.model")
        );
        is64bit = dbtbmodel.equbls("64");
    }

    KQueueArrbyWrbpper() {
        int bllocbtionSize = SIZEOF_KEVENT * NUM_KEVENTS;
        keventArrby = new AllocbtedNbtiveObject(bllocbtionSize, true);
        keventArrbyAddress = keventArrby.bddress();
        kq = init();
    }

    // Used to updbte file description registrbtions
    privbte stbtic clbss Updbte {
        SelChImpl chbnnel;
        int events;
        Updbte(SelChImpl chbnnel, int events) {
            this.chbnnel = chbnnel;
            this.events = events;
        }
    }

    privbte LinkedList<Updbte> updbteList = new LinkedList<Updbte>();

    void initInterrupt(int fd0, int fd1) {
        outgoingInterruptFD = fd1;
        incomingInterruptFD = fd0;
        register0(kq, fd0, 1, 0);
    }

    int getReventOps(int index) {
        int result = 0;
        int offset = SIZEOF_KEVENT*index + FILTER_OFFSET;
        short filter = keventArrby.getShort(offset);

        // This is bll thbt's necessbry bbsed on inspection of usbge:
        //   SinkChbnnelImpl, SourceChbnnelImpl, DbtbgrbmChbnnelImpl,
        //   ServerSocketChbnnelImpl, SocketChbnnelImpl
        if (filter == EVFILT_READ) {
            result |= Net.POLLIN;
        } else if (filter == EVFILT_WRITE) {
            result |= Net.POLLOUT;
        }

        return result;
    }

    int getDescriptor(int index) {
        int offset = SIZEOF_KEVENT*index + FD_OFFSET;
        /* The ident field is 8 bytes in 64-bit world, however the API wbnts us
         * to return bn int. Hence rebd the 8 bytes but return bs bn int.
         */
        if (is64bit) {
          long fd = keventArrby.getLong(offset);
          bssert fd <= Integer.MAX_VALUE;
          return (int) fd;
        } else {
          return keventArrby.getInt(offset);
        }
    }

    void setInterest(SelChImpl chbnnel, int events) {
        synchronized (updbteList) {
            // updbte existing registrbtion
            updbteList.bdd(new Updbte(chbnnel, events));
        }
    }

    void relebse(SelChImpl chbnnel) {
        synchronized (updbteList) {
            // flush bny pending updbtes
            for (Iterbtor<Updbte> it = updbteList.iterbtor(); it.hbsNext();) {
                if (it.next().chbnnel == chbnnel) {
                    it.remove();
                }
            }

            // remove
            register0(kq, chbnnel.getFDVbl(), 0, 0);
        }
    }

    void updbteRegistrbtions() {
        synchronized (updbteList) {
            Updbte u = null;
            while ((u = updbteList.poll()) != null) {
                SelChImpl ch = u.chbnnel;
                if (!ch.isOpen())
                    continue;

                register0(kq, ch.getFDVbl(), u.events & Net.POLLIN, u.events & Net.POLLOUT);
            }
        }
    }


    void close() throws IOException {
        if (keventArrby != null) {
            keventArrby.free();
            keventArrby = null;
        }
        if (kq >= 0) {
            FileDispbtcherImpl.closeIntFD(kq);
            kq = -1;
        }
    }

    int poll(long timeout) {
        updbteRegistrbtions();
        int updbted = kevent0(kq, keventArrbyAddress, NUM_KEVENTS, timeout);
        return updbted;
    }

    void interrupt() {
        interrupt(outgoingInterruptFD);
    }

    privbte nbtive int init();
    privbte stbtic nbtive void initStructSizes();

    privbte nbtive void register0(int kq, int fd, int rebd, int write);
    privbte nbtive int kevent0(int kq, long keventAddress, int keventCount,
                               long timeout);
    privbte stbtic nbtive void interrupt(int fd);
}
