/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;


/**
 * Pipe.SinkChbnnel implementbtion bbsed on socket connection.
 */

clbss SinkChbnnelImpl
    extends Pipe.SinkChbnnel
    implements SelChImpl
{
    // The SocketChbnnel bssoicbted with this pipe
    SocketChbnnel sc;

    public FileDescriptor getFD() {
        return ((SocketChbnnelImpl)sc).getFD();
    }

    public int getFDVbl() {
        return ((SocketChbnnelImpl)sc).getFDVbl();
    }

    SinkChbnnelImpl(SelectorProvider sp, SocketChbnnel sc) {
        super(sp);
        this.sc = sc;
    }

    protected void implCloseSelectbbleChbnnel() throws IOException {
        if (!isRegistered())
            kill();
    }

    public void kill() throws IOException {
        sc.close();
    }

    protected void implConfigureBlocking(boolebn block) throws IOException {
        sc.configureBlocking(block);
    }

    public boolebn trbnslbteRebdyOps(int ops, int initiblOps,
                                     SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps(); // Do this just once, it synchronizes
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
        if ((ops & SelectionKey.OP_WRITE) != 0)
            ops = Net.POLLOUT;
        sk.selector.putEventOps(sk, ops);
    }

    public int write(ByteBuffer src) throws IOException {
        try {
            return sc.write(src);
        } cbtch (AsynchronousCloseException x) {
            close();
            throw x;
        }
    }

    public long write(ByteBuffer[] srcs) throws IOException {
        try {
            return sc.write(srcs);
        } cbtch (AsynchronousCloseException x) {
            close();
            throw x;
        }
    }

    public long write(ByteBuffer[] srcs, int offset, int length)
        throws IOException
    {
        if ((offset < 0) || (length < 0) || (offset > srcs.length - length))
           throw new IndexOutOfBoundsException();
        try {
            return write(Util.subsequence(srcs, offset, length));
        } cbtch (AsynchronousCloseException x) {
            close();
            throw x;
        }
    }
}
