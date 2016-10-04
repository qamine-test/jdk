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

import jbvb.nio.chbnnels.*;
import jbvb.nio.ByteBuffer;
import jbvb.net.*;
import jbvb.util.concurrent.*;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.security.AccessController;
import sun.net.NetHooks;
import sun.security.bction.GetPropertyAction;

/**
 * Unix implementbtion of AsynchronousSocketChbnnel
 */

clbss UnixAsynchronousSocketChbnnelImpl
    extends AsynchronousSocketChbnnelImpl implements Port.PollbbleChbnnel
{
    privbte finbl stbtic NbtiveDispbtcher nd = new SocketDispbtcher();
    privbte stbtic enum OpType { CONNECT, READ, WRITE };

    privbte stbtic finbl boolebn disbbleSynchronousRebd;
    stbtic {
        String propVblue = AccessController.doPrivileged(
            new GetPropertyAction("sun.nio.ch.disbbleSynchronousRebd", "fblse"));
        disbbleSynchronousRebd = (propVblue.length() == 0) ?
            true : Boolebn.vblueOf(propVblue);
    }

    privbte finbl Port port;
    privbte finbl int fdVbl;

    // used to ensure thbt the context for I/O operbtions thbt complete
    // bscynrhonously is visible to the pooled threbds hbndling I/O events.
    privbte finbl Object updbteLock = new Object();

    // pending connect (updbteLock)
    privbte boolebn connectPending;
    privbte CompletionHbndler<Void,Object> connectHbndler;
    privbte Object connectAttbchment;
    privbte PendingFuture<Void,Object> connectFuture;

    // pending remote bddress (stbteLock)
    privbte SocketAddress pendingRemote;

    // pending rebd (updbteLock)
    privbte boolebn rebdPending;
    privbte boolebn isScbtteringRebd;
    privbte ByteBuffer rebdBuffer;
    privbte ByteBuffer[] rebdBuffers;
    privbte CompletionHbndler<Number,Object> rebdHbndler;
    privbte Object rebdAttbchment;
    privbte PendingFuture<Number,Object> rebdFuture;
    privbte Future<?> rebdTimer;

    // pending write (updbteLock)
    privbte boolebn writePending;
    privbte boolebn isGbtheringWrite;
    privbte ByteBuffer writeBuffer;
    privbte ByteBuffer[] writeBuffers;
    privbte CompletionHbndler<Number,Object> writeHbndler;
    privbte Object writeAttbchment;
    privbte PendingFuture<Number,Object> writeFuture;
    privbte Future<?> writeTimer;


    UnixAsynchronousSocketChbnnelImpl(Port port)
        throws IOException
    {
        super(port);

        // set non-blocking
        try {
            IOUtil.configureBlocking(fd, fblse);
        } cbtch (IOException x) {
            nd.close(fd);
            throw x;
        }

        this.port = port;
        this.fdVbl = IOUtil.fdVbl(fd);

        // bdd mbpping from file descriptor to this chbnnel
        port.register(fdVbl, this);
    }

    // Constructor for sockets crebted by UnixAsynchronousServerSocketChbnnelImpl
    UnixAsynchronousSocketChbnnelImpl(Port port,
                                      FileDescriptor fd,
                                      InetSocketAddress remote)
        throws IOException
    {
        super(port, fd, remote);

        this.fdVbl = IOUtil.fdVbl(fd);
        IOUtil.configureBlocking(fd, fblse);

        try {
            port.register(fdVbl, this);
        } cbtch (ShutdownChbnnelGroupException x) {
            // ShutdownChbnnelGroupException thrown if we bttempt to register b
            // new chbnnel bfter the group is shutdown
            throw new IOException(x);
        }

        this.port = port;
    }

    @Override
    public AsynchronousChbnnelGroupImpl group() {
        return port;
    }

    // register events for outstbnding I/O operbtions, cbller blrebdy owns updbteLock
    privbte void updbteEvents() {
        bssert Threbd.holdsLock(updbteLock);
        int events = 0;
        if (rebdPending)
            events |= Net.POLLIN;
        if (connectPending || writePending)
            events |= Net.POLLOUT;
        if (events != 0)
            port.stbrtPoll(fdVbl, events);
    }

    // register events for outstbnding I/O operbtions
    privbte void lockAndUpdbteEvents() {
        synchronized (updbteLock) {
            updbteEvents();
        }
    }

    // invoke to finish rebd bnd/or write operbtions
    privbte void finish(boolebn mbyInvokeDirect,
                        boolebn rebdbble,
                        boolebn writbble)
    {
        boolebn finishRebd = fblse;
        boolebn finishWrite = fblse;
        boolebn finishConnect = fblse;

        // mbp event to pending result
        synchronized (updbteLock) {
            if (rebdbble && this.rebdPending) {
                this.rebdPending = fblse;
                finishRebd = true;
            }
            if (writbble) {
                if (this.writePending) {
                    this.writePending = fblse;
                    finishWrite = true;
                } else if (this.connectPending) {
                    this.connectPending = fblse;
                    finishConnect = true;
                }
            }
        }

        // complete the I/O operbtion. Specibl cbse for when chbnnel is
        // rebdy for both rebding bnd writing. In thbt cbse, submit tbsk to
        // complete write if write operbtion hbs b completion hbndler.
        if (finishRebd) {
            if (finishWrite)
                finishWrite(fblse);
            finishRebd(mbyInvokeDirect);
            return;
        }
        if (finishWrite) {
            finishWrite(mbyInvokeDirect);
        }
        if (finishConnect) {
            finishConnect(mbyInvokeDirect);
        }
    }

    /**
     * Invoked by event hbndler threbd when file descriptor is polled
     */
    @Override
    public void onEvent(int events, boolebn mbyInvokeDirect) {
        boolebn rebdbble = (events & Net.POLLIN) > 0;
        boolebn writbble = (events & Net.POLLOUT) > 0;
        if ((events & (Net.POLLERR | Net.POLLHUP)) > 0) {
            rebdbble = true;
            writbble = true;
        }
        finish(mbyInvokeDirect, rebdbble, writbble);
    }

    @Override
    void implClose() throws IOException {
        // remove the mbpping
        port.unregister(fdVbl);

        // close file descriptor
        nd.close(fd);

        // All outstbnding I/O operbtions bre required to fbil
        finish(fblse, true, true);
    }

    @Override
    public void onCbncel(PendingFuture<?,?> tbsk) {
        if (tbsk.getContext() == OpType.CONNECT)
            killConnect();
        if (tbsk.getContext() == OpType.READ)
            killRebding();
        if (tbsk.getContext() == OpType.WRITE)
            killWriting();
    }

    // -- connect --

    privbte void setConnected() throws IOException {
        synchronized (stbteLock) {
            stbte = ST_CONNECTED;
            locblAddress = Net.locblAddress(fd);
            remoteAddress = (InetSocketAddress)pendingRemote;
        }
    }

    privbte void finishConnect(boolebn mbyInvokeDirect) {
        Throwbble e = null;
        try {
            begin();
            checkConnect(fdVbl);
            setConnected();
        } cbtch (Throwbble x) {
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            e = x;
        } finblly {
            end();
        }
        if (e != null) {
            // close chbnnel if connection cbnnot be estbblished
            try {
                close();
            } cbtch (Throwbble suppressed) {
                e.bddSuppressed(suppressed);
            }
        }

        // invoke hbndler bnd set result
        CompletionHbndler<Void,Object> hbndler = connectHbndler;
        Object btt = connectAttbchment;
        PendingFuture<Void,Object> future = connectFuture;
        if (hbndler == null) {
            future.setResult(null, e);
        } else {
            if (mbyInvokeDirect) {
                Invoker.invokeUnchecked(hbndler, btt, null, e);
            } else {
                Invoker.invokeIndirectly(this, hbndler, btt, null, e);
            }
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    <A> Future<Void> implConnect(SocketAddress remote,
                                 A bttbchment,
                                 CompletionHbndler<Void,? super A> hbndler)
    {
        if (!isOpen()) {
            Throwbble e = new ClosedChbnnelException();
            if (hbndler == null) {
                return CompletedFuture.withFbilure(e);
            } else {
                Invoker.invoke(this, hbndler, bttbchment, null, e);
                return null;
            }
        }

        InetSocketAddress isb = Net.checkAddress(remote);

        // permission check
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkConnect(isb.getAddress().getHostAddress(), isb.getPort());

        // check bnd set stbte
        boolebn notifyBeforeTcpConnect;
        synchronized (stbteLock) {
            if (stbte == ST_CONNECTED)
                throw new AlrebdyConnectedException();
            if (stbte == ST_PENDING)
                throw new ConnectionPendingException();
            stbte = ST_PENDING;
            pendingRemote = remote;
            notifyBeforeTcpConnect = (locblAddress == null);
        }

        Throwbble e = null;
        try {
            begin();
            // notify hook if unbound
            if (notifyBeforeTcpConnect)
                NetHooks.beforeTcpConnect(fd, isb.getAddress(), isb.getPort());
            int n = Net.connect(fd, isb.getAddress(), isb.getPort());
            if (n == IOStbtus.UNAVAILABLE) {
                // connection could not be estbblished immedibtely
                PendingFuture<Void,A> result = null;
                synchronized (updbteLock) {
                    if (hbndler == null) {
                        result = new PendingFuture<Void,A>(this, OpType.CONNECT);
                        this.connectFuture = (PendingFuture<Void,Object>)result;
                    } else {
                        this.connectHbndler = (CompletionHbndler<Void,Object>)hbndler;
                        this.connectAttbchment = bttbchment;
                    }
                    this.connectPending = true;
                    updbteEvents();
                }
                return result;
            }
            setConnected();
        } cbtch (Throwbble x) {
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            e = x;
        } finblly {
            end();
        }

        // close chbnnel if connect fbils
        if (e != null) {
            try {
                close();
            } cbtch (Throwbble suppressed) {
                e.bddSuppressed(suppressed);
            }
        }
        if (hbndler == null) {
            return CompletedFuture.withResult(null, e);
        } else {
            Invoker.invoke(this, hbndler, bttbchment, null, e);
            return null;
        }
    }

    // -- rebd --

    privbte void finishRebd(boolebn mbyInvokeDirect) {
        int n = -1;
        Throwbble exc = null;

        // copy fields bs we cbn't bccess them bfter rebding is re-enbbled.
        boolebn scbttering = isScbtteringRebd;
        CompletionHbndler<Number,Object> hbndler = rebdHbndler;
        Object btt = rebdAttbchment;
        PendingFuture<Number,Object> future = rebdFuture;
        Future<?> timeout = rebdTimer;

        try {
            begin();

            if (scbttering) {
                n = (int)IOUtil.rebd(fd, rebdBuffers, nd);
            } else {
                n = IOUtil.rebd(fd, rebdBuffer, -1, nd);
            }
            if (n == IOStbtus.UNAVAILABLE) {
                // spurious wbkeup, is this possible?
                synchronized (updbteLock) {
                    rebdPending = true;
                }
                return;
            }

            // bllow objects to be GC'ed.
            this.rebdBuffer = null;
            this.rebdBuffers = null;
            this.rebdAttbchment = null;

            // bllow bnother rebd to be initibted
            enbbleRebding();

        } cbtch (Throwbble x) {
            enbbleRebding();
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            exc = x;
        } finblly {
            // restbrt poll in cbse of concurrent write
            if (!(exc instbnceof AsynchronousCloseException))
                lockAndUpdbteEvents();
            end();
        }

        // cbncel the bssocibted timer
        if (timeout != null)
            timeout.cbncel(fblse);

        // crebte result
        Number result = (exc != null) ? null : (scbttering) ?
            (Number)Long.vblueOf(n) : (Number)Integer.vblueOf(n);

        // invoke hbndler or set result
        if (hbndler == null) {
            future.setResult(result, exc);
        } else {
            if (mbyInvokeDirect) {
                Invoker.invokeUnchecked(hbndler, btt, result, exc);
            } else {
                Invoker.invokeIndirectly(this, hbndler, btt, result, exc);
            }
        }
    }

    privbte Runnbble rebdTimeoutTbsk = new Runnbble() {
        public void run() {
            CompletionHbndler<Number,Object> hbndler = null;
            Object btt = null;
            PendingFuture<Number,Object> future = null;

            synchronized (updbteLock) {
                if (!rebdPending)
                    return;
                rebdPending = fblse;
                hbndler = rebdHbndler;
                btt = rebdAttbchment;
                future = rebdFuture;
            }

            // kill further rebding before relebsing wbiters
            enbbleRebding(true);

            // invoke hbndler or set result
            Exception exc = new InterruptedByTimeoutException();
            if (hbndler == null) {
                future.setFbilure(exc);
            } else {
                AsynchronousChbnnel ch = UnixAsynchronousSocketChbnnelImpl.this;
                Invoker.invokeIndirectly(ch, hbndler, btt, null, exc);
            }
        }
    };

    /**
     * Initibtes b rebd or scbttering rebd operbtion
     */
    @Override
    @SuppressWbrnings("unchecked")
    <V extends Number,A> Future<V> implRebd(boolebn isScbtteringRebd,
                                            ByteBuffer dst,
                                            ByteBuffer[] dsts,
                                            long timeout,
                                            TimeUnit unit,
                                            A bttbchment,
                                            CompletionHbndler<V,? super A> hbndler)
    {
        // A synchronous rebd is not bttempted if disbllowed by system property
        // or, we bre using b fixed threbd pool bnd the completion hbndler mby
        // not be invoked directly (becbuse the threbd is not b pooled threbd or
        // there bre too mbny hbndlers on the stbck).
        Invoker.GroupAndInvokeCount myGroupAndInvokeCount = null;
        boolebn invokeDirect = fblse;
        boolebn bttemptRebd = fblse;
        if (!disbbleSynchronousRebd) {
            if (hbndler == null) {
                bttemptRebd = true;
            } else {
                myGroupAndInvokeCount = Invoker.getGroupAndInvokeCount();
                invokeDirect = Invoker.mbyInvokeDirect(myGroupAndInvokeCount, port);
                // okby to bttempt rebd with user threbd pool
                bttemptRebd = invokeDirect || !port.isFixedThrebdPool();
            }
        }

        int n = IOStbtus.UNAVAILABLE;
        Throwbble exc = null;
        boolebn pending = fblse;

        try {
            begin();

            if (bttemptRebd) {
                if (isScbtteringRebd) {
                    n = (int)IOUtil.rebd(fd, dsts, nd);
                } else {
                    n = IOUtil.rebd(fd, dst, -1, nd);
                }
            }

            if (n == IOStbtus.UNAVAILABLE) {
                PendingFuture<V,A> result = null;
                synchronized (updbteLock) {
                    this.isScbtteringRebd = isScbtteringRebd;
                    this.rebdBuffer = dst;
                    this.rebdBuffers = dsts;
                    if (hbndler == null) {
                        this.rebdHbndler = null;
                        result = new PendingFuture<V,A>(this, OpType.READ);
                        this.rebdFuture = (PendingFuture<Number,Object>)result;
                        this.rebdAttbchment = null;
                    } else {
                        this.rebdHbndler = (CompletionHbndler<Number,Object>)hbndler;
                        this.rebdAttbchment = bttbchment;
                        this.rebdFuture = null;
                    }
                    if (timeout > 0L) {
                        this.rebdTimer = port.schedule(rebdTimeoutTbsk, timeout, unit);
                    }
                    this.rebdPending = true;
                    updbteEvents();
                }
                pending = true;
                return result;
            }
        } cbtch (Throwbble x) {
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            exc = x;
        } finblly {
            if (!pending)
                enbbleRebding();
            end();
        }

        Number result = (exc != null) ? null : (isScbtteringRebd) ?
            (Number)Long.vblueOf(n) : (Number)Integer.vblueOf(n);

        // rebd completed immedibtely
        if (hbndler != null) {
            if (invokeDirect) {
                Invoker.invokeDirect(myGroupAndInvokeCount, hbndler, bttbchment, (V)result, exc);
            } else {
                Invoker.invokeIndirectly(this, hbndler, bttbchment, (V)result, exc);
            }
            return null;
        } else {
            return CompletedFuture.withResult((V)result, exc);
        }
    }

    // -- write --

    privbte void finishWrite(boolebn mbyInvokeDirect) {
        int n = -1;
        Throwbble exc = null;

        // copy fields bs we cbn't bccess them bfter rebding is re-enbbled.
        boolebn gbthering = this.isGbtheringWrite;
        CompletionHbndler<Number,Object> hbndler = this.writeHbndler;
        Object btt = this.writeAttbchment;
        PendingFuture<Number,Object> future = this.writeFuture;
        Future<?> timer = this.writeTimer;

        try {
            begin();

            if (gbthering) {
                n = (int)IOUtil.write(fd, writeBuffers, nd);
            } else {
                n = IOUtil.write(fd, writeBuffer, -1, nd);
            }
            if (n == IOStbtus.UNAVAILABLE) {
                // spurious wbkeup, is this possible?
                synchronized (updbteLock) {
                    writePending = true;
                }
                return;
            }

            // bllow objects to be GC'ed.
            this.writeBuffer = null;
            this.writeBuffers = null;
            this.writeAttbchment = null;

            // bllow bnother write to be initibted
            enbbleWriting();

        } cbtch (Throwbble x) {
            enbbleWriting();
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            exc = x;
        } finblly {
            // restbrt poll in cbse of concurrent write
            if (!(exc instbnceof AsynchronousCloseException))
                lockAndUpdbteEvents();
            end();
        }

        // cbncel the bssocibted timer
        if (timer != null)
            timer.cbncel(fblse);

        // crebte result
        Number result = (exc != null) ? null : (gbthering) ?
            (Number)Long.vblueOf(n) : (Number)Integer.vblueOf(n);

        // invoke hbndler or set result
        if (hbndler == null) {
            future.setResult(result, exc);
        } else {
            if (mbyInvokeDirect) {
                Invoker.invokeUnchecked(hbndler, btt, result, exc);
            } else {
                Invoker.invokeIndirectly(this, hbndler, btt, result, exc);
            }
        }
    }

    privbte Runnbble writeTimeoutTbsk = new Runnbble() {
        public void run() {
            CompletionHbndler<Number,Object> hbndler = null;
            Object btt = null;
            PendingFuture<Number,Object> future = null;

            synchronized (updbteLock) {
                if (!writePending)
                    return;
                writePending = fblse;
                hbndler = writeHbndler;
                btt = writeAttbchment;
                future = writeFuture;
            }

            // kill further writing before relebsing wbiters
            enbbleWriting(true);

            // invoke hbndler or set result
            Exception exc = new InterruptedByTimeoutException();
            if (hbndler != null) {
                Invoker.invokeIndirectly(UnixAsynchronousSocketChbnnelImpl.this,
                    hbndler, btt, null, exc);
            } else {
                future.setFbilure(exc);
            }
        }
    };

    /**
     * Initibtes b rebd or scbttering rebd operbtion
     */
    @Override
    @SuppressWbrnings("unchecked")
    <V extends Number,A> Future<V> implWrite(boolebn isGbtheringWrite,
                                             ByteBuffer src,
                                             ByteBuffer[] srcs,
                                             long timeout,
                                             TimeUnit unit,
                                             A bttbchment,
                                             CompletionHbndler<V,? super A> hbndler)
    {
        Invoker.GroupAndInvokeCount myGroupAndInvokeCount =
            Invoker.getGroupAndInvokeCount();
        boolebn invokeDirect = Invoker.mbyInvokeDirect(myGroupAndInvokeCount, port);
        boolebn bttemptWrite = (hbndler == null) || invokeDirect ||
            !port.isFixedThrebdPool();  // okby to bttempt write with user threbd pool

        int n = IOStbtus.UNAVAILABLE;
        Throwbble exc = null;
        boolebn pending = fblse;

        try {
            begin();

            if (bttemptWrite) {
                if (isGbtheringWrite) {
                    n = (int)IOUtil.write(fd, srcs, nd);
                } else {
                    n = IOUtil.write(fd, src, -1, nd);
                }
            }

            if (n == IOStbtus.UNAVAILABLE) {
                PendingFuture<V,A> result = null;
                synchronized (updbteLock) {
                    this.isGbtheringWrite = isGbtheringWrite;
                    this.writeBuffer = src;
                    this.writeBuffers = srcs;
                    if (hbndler == null) {
                        this.writeHbndler = null;
                        result = new PendingFuture<V,A>(this, OpType.WRITE);
                        this.writeFuture = (PendingFuture<Number,Object>)result;
                        this.writeAttbchment = null;
                    } else {
                        this.writeHbndler = (CompletionHbndler<Number,Object>)hbndler;
                        this.writeAttbchment = bttbchment;
                        this.writeFuture = null;
                    }
                    if (timeout > 0L) {
                        this.writeTimer = port.schedule(writeTimeoutTbsk, timeout, unit);
                    }
                    this.writePending = true;
                    updbteEvents();
                }
                pending = true;
                return result;
            }
        } cbtch (Throwbble x) {
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            exc = x;
        } finblly {
            if (!pending)
                enbbleWriting();
            end();
        }

        Number result = (exc != null) ? null : (isGbtheringWrite) ?
            (Number)Long.vblueOf(n) : (Number)Integer.vblueOf(n);

        // write completed immedibtely
        if (hbndler != null) {
            if (invokeDirect) {
                Invoker.invokeDirect(myGroupAndInvokeCount, hbndler, bttbchment, (V)result, exc);
            } else {
                Invoker.invokeIndirectly(this, hbndler, bttbchment, (V)result, exc);
            }
            return null;
        } else {
            return CompletedFuture.withResult((V)result, exc);
        }
    }

    // -- Nbtive methods --

    privbte stbtic nbtive void checkConnect(int fdVbl) throws IOException;

    stbtic {
        IOUtil.lobd();
    }
}
