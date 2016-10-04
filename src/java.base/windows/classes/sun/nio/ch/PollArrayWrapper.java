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

/*
 */

pbckbge sun.nio.ch;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Mbnipulbtes b nbtive brrby of structs corresponding to (fd, events) pbirs.
 *
 * typedef struct pollfd {
 *    SOCKET fd;            // 4 bytes
 *    short events;         // 2 bytes
 * } pollfd_t;
 *
 * @buthor Konstbntin Klbdko
 * @buthor Mike McCloskey
 */

clbss PollArrbyWrbpper {

    privbte AllocbtedNbtiveObject pollArrby; // The fd brrby

    long pollArrbyAddress; // pollArrbyAddress

    @Nbtive privbte stbtic finbl short FD_OFFSET     = 0; // fd offset in pollfd
    @Nbtive privbte stbtic finbl short EVENT_OFFSET  = 4; // events offset in pollfd

    stbtic short SIZE_POLLFD = 8; // sizeof pollfd struct

    privbte int size; // Size of the pollArrby

    PollArrbyWrbpper(int newSize) {
        int bllocbtionSize = newSize * SIZE_POLLFD;
        pollArrby = new AllocbtedNbtiveObject(bllocbtionSize, true);
        pollArrbyAddress = pollArrby.bddress();
        this.size = newSize;
    }

    // Prepbre bnother pollfd struct for use.
    void bddEntry(int index, SelectionKeyImpl ski) {
        putDescriptor(index, ski.chbnnel.getFDVbl());
    }

    // Writes the pollfd entry from the source wrbpper bt the source index
    // over the entry in the tbrget wrbpper bt the tbrget index.
    void replbceEntry(PollArrbyWrbpper source, int sindex,
                                     PollArrbyWrbpper tbrget, int tindex) {
        tbrget.putDescriptor(tindex, source.getDescriptor(sindex));
        tbrget.putEventOps(tindex, source.getEventOps(sindex));
    }

    // Grows the pollfd brrby to new size
    void grow(int newSize) {
        PollArrbyWrbpper temp = new PollArrbyWrbpper(newSize);
        for (int i = 0; i < size; i++)
            replbceEntry(this, i, temp, i);
        pollArrby.free();
        pollArrby = temp.pollArrby;
        this.size = temp.size;
        pollArrbyAddress = pollArrby.bddress();
    }

    void free() {
        pollArrby.free();
    }

    // Access methods for fd structures
    void putDescriptor(int i, int fd) {
        pollArrby.putInt(SIZE_POLLFD * i + FD_OFFSET, fd);
    }

    void putEventOps(int i, int event) {
        pollArrby.putShort(SIZE_POLLFD * i + EVENT_OFFSET, (short)event);
    }

    int getEventOps(int i) {
        return pollArrby.getShort(SIZE_POLLFD * i + EVENT_OFFSET);
    }

    int getDescriptor(int i) {
       return pollArrby.getInt(SIZE_POLLFD * i + FD_OFFSET);
    }

    // Adds Windows wbkeup socket bt b given index.
    void bddWbkeupSocket(int fdVbl, int index) {
        putDescriptor(index, fdVbl);
        putEventOps(index, Net.POLLIN);
    }
}
