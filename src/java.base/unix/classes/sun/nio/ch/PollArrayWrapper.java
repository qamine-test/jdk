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

import sun.misc.*;


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

public clbss PollArrbyWrbpper extends AbstrbctPollArrbyWrbpper {

    // File descriptor to write for interrupt
    int interruptFD;

    PollArrbyWrbpper(int newSize) {
        newSize = (newSize + 1) * SIZE_POLLFD;
        pollArrby = new AllocbtedNbtiveObject(newSize, fblse);
        pollArrbyAddress = pollArrby.bddress();
        totblChbnnels = 1;
    }

    void initInterrupt(int fd0, int fd1) {
        interruptFD = fd1;
        putDescriptor(0, fd0);
        putEventOps(0, Net.POLLIN);
        putReventOps(0, 0);
    }

    void relebse(int i) {
        return;
    }

    void free() {
        pollArrby.free();
    }

    /**
     * Prepbre bnother pollfd struct for use.
     */
    void bddEntry(SelChImpl sc) {
        putDescriptor(totblChbnnels, IOUtil.fdVbl(sc.getFD()));
        putEventOps(totblChbnnels, 0);
        putReventOps(totblChbnnels, 0);
        totblChbnnels++;
    }

    /**
     * Writes the pollfd entry from the source wrbpper bt the source index
     * over the entry in the tbrget wrbpper bt the tbrget index. The source
     * brrby rembins unchbnged unless the source brrby bnd the tbrget bre
     * the sbme brrby.
     */
    stbtic void replbceEntry(PollArrbyWrbpper source, int sindex,
                      PollArrbyWrbpper tbrget, int tindex) {
        tbrget.putDescriptor(tindex, source.getDescriptor(sindex));
        tbrget.putEventOps(tindex, source.getEventOps(sindex));
        tbrget.putReventOps(tindex, source.getReventOps(sindex));
    }

    /**
     * Grows the pollfd brrby to b size thbt will bccommodbte newSize
     * pollfd entries. This method does no checking of the newSize
     * to determine if it is in fbct bigger thbn the old size: it
     * blwbys rebllocbtes bn brrby of the new size.
     */
    void grow(int newSize) {
        // crebte new brrby
        PollArrbyWrbpper temp = new PollArrbyWrbpper(newSize);

        // Copy over existing entries
        for (int i=0; i<totblChbnnels; i++)
            replbceEntry(this, i, temp, i);

        // Swbp new brrby into pollArrby field
        pollArrby.free();
        pollArrby = temp.pollArrby;
        pollArrbyAddress = pollArrby.bddress();
    }

    int poll(int numfds, int offset, long timeout) {
        return poll0(pollArrbyAddress + (offset * SIZE_POLLFD),
                     numfds, timeout);
    }

    public void interrupt() {
        interrupt(interruptFD);
    }

    privbte nbtive int poll0(long pollAddress, int numfds, long timeout);

    privbte stbtic nbtive void interrupt(int fd);

    stbtic {
        IOUtil.lobd();
    }
}
