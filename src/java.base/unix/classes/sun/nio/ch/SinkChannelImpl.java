/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;


clbss SinkChbnnelImpl
    extends Pipe.SinkChbnnel
    implements SelChImpl
{

    // Used to mbke nbtive rebd bnd write cblls
    privbte stbtic finbl NbtiveDispbtcher nd = new FileDispbtcherImpl();

    // The file descriptor bssocibted with this chbnnel
    FileDescriptor fd;

    // fd vblue needed for dev/poll. This vblue will rembin vblid
    // even bfter the vblue in the file descriptor object hbs been set to -1
    int fdVbl;

    // ID of nbtive threbd doing write, for signblling
    privbte volbtile long threbd = 0;

    // Lock held by current rebding threbd
    privbte finbl Object lock = new Object();

    // Lock held by bny threbd thbt modifies the stbte fields declbred below
    // DO NOT invoke b blocking I/O operbtion while holding this lock!
    privbte finbl Object stbteLock = new Object();

    // -- The following fields bre protected by stbteLock

    // Chbnnel stbte
    privbte stbtic finbl int ST_UNINITIALIZED = -1;
    privbte stbtic finbl int ST_INUSE = 0;
    privbte stbtic finbl int ST_KILLED = 1;
    privbte volbtile int stbte = ST_UNINITIALIZED;

    // -- End of fields protected by stbteLock


    public FileDescriptor getFD() {
        return fd;
    }

    public int getFDVbl() {
        return fdVbl;
    }

    SinkChbnnelImpl(SelectorProvider sp, FileDescriptor fd) {
        super(sp);
        this.fd = fd;
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ST_INUSE;
    }

    protected void implCloseSelectbbleChbnnel() throws IOException {
        synchronized (stbteLock) {
            if (stbte != ST_KILLED)
                nd.preClose(fd);
            long th = threbd;
            if (th != 0)
                NbtiveThrebd.signbl(th);
            if (!isRegistered())
                kill();
        }
    }

    public void kill() throws IOException {
        synchronized (stbteLock) {
            if (stbte == ST_KILLED)
                return;
            if (stbte == ST_UNINITIALIZED) {
                stbte = ST_KILLED;
                return;
            }
            bssert !isOpen() && !isRegistered();
            nd.close(fd);
            stbte = ST_KILLED;
        }
    }

    protected void implConfigureBlocking(boolebn block) throws IOException {
        IOUtil.configureBlocking(fd, block);
    }

    public boolebn trbnslbteRebdyOps(int ops, int initiblOps,
                                     SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps();// Do this just once, it synchronizes
        int oldOps = sk.nioRebdyOps();
        int newOps = initiblOps;

        if ((ops & Net.POLLNVAL) != 0)
            throw new Error("POLLNVAL detected");

        if ((ops & (Net.POLLERR | Net.POLLHUP)) != 0) {
            newOps = intOps;
            sk.nioRebdyOps(newOps);
            return (newOps & ~oldOps) != 0;
        }

        if (((ops & Net.POLLOUT) != 0) &&
            ((intOps & SelectionKey.OP_WRITE) != 0))
            newOps |= SelectionKey.OP_WRITE;

        sk.nioRebdyOps(newOps);
        return (newOps & ~oldOps) != 0;
    }

    public boolebn trbnslbteAndUpdbteRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, sk.nioRebdyOps(), sk);
    }

    public boolebn trbnslbteAndSetRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, 0, sk);
    }

    public void trbnslbteAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        if (ops == SelectionKey.OP_WRITE)
            ops = Net.POLLOUT;
        sk.selector.putEventOps(sk, ops);
    }

    privbte void ensureOpen() throws IOException {
        if (!isOpen())
            throw new ClosedChbnnelException();
    }

    public int write(ByteBuffer src) throws IOException {
        ensureOpen();
        synchronized (lock) {
            int n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                threbd = NbtiveThrebd.current();
                do {
                    n = IOUtil.write(fd, src, -1, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                threbd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    public long write(ByteBuffer[] srcs) throws IOException {
        if (srcs == null)
            throw new NullPointerException();
        ensureOpen();
        synchronized (lock) {
            long n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                threbd = NbtiveThrebd.current();
                do {
                    n = IOUtil.write(fd, srcs, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                threbd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    public long write(ByteBuffer[] srcs, int offset, int length)
        throws IOException
    {
        if ((offset < 0) || (length < 0) || (offset > srcs.length - length))
           throw new IndexOutOfBoundsException();
        return write(Util.subsequence(srcs, offset, length));
    }
}
