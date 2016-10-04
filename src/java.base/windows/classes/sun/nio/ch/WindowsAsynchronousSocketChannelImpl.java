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
import jbvb.nio.BufferOverflowException;
import jbvb.net.*;
import jbvb.util.concurrent.*;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import sun.misc.Unsbfe;

/**
 * Windows implementbtion of AsynchronousSocketChbnnel using overlbpped I/O.
 */

clbss WindowsAsynchronousSocketChbnnelImpl
    extends AsynchronousSocketChbnnelImpl implements Iocp.OverlbppedChbnnel
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic int bddressSize = unsbfe.bddressSize();

    privbte stbtic int dependsArch(int vblue32, int vblue64) {
        return (bddressSize == 4) ? vblue32 : vblue64;
    }

    /*
     * typedef struct _WSABUF {
     *     u_long      len;
     *     chbr FAR *  buf;
     * } WSABUF;
     */
    privbte stbtic finbl int SIZEOF_WSABUF  = dependsArch(8, 16);
    privbte stbtic finbl int OFFSETOF_LEN   = 0;
    privbte stbtic finbl int OFFSETOF_BUF   = dependsArch(4, 8);

    // mbximum vector size for scbtter/gbther I/O
    privbte stbtic finbl int MAX_WSABUF     = 16;

    privbte stbtic finbl int SIZEOF_WSABUFARRAY = MAX_WSABUF * SIZEOF_WSABUF;


    // socket hbndle. Use begin()/end() bround ebch usbge of this hbndle.
    finbl long hbndle;

    // I/O completion port thbt the socket is bssocibted with
    privbte finbl Iocp iocp;

    // completion key to identify chbnnel when I/O completes
    privbte finbl int completionKey;

    // Pending I/O operbtions bre tied to bn OVERLAPPED structure thbt cbn only
    // be relebsed when the I/O completion event is posted to the completion
    // port. Where I/O operbtions complete immedibtely then it is possible
    // there mby be more thbn two OVERLAPPED structures in use.
    privbte finbl PendingIoCbche ioCbche;

    // per-chbnnel brrbys of WSABUF structures
    privbte finbl long rebdBufferArrby;
    privbte finbl long writeBufferArrby;


    WindowsAsynchronousSocketChbnnelImpl(Iocp iocp, boolebn fbilIfGroupShutdown)
        throws IOException
    {
        super(iocp);

        // bssocibte socket with defbult completion port
        long h = IOUtil.fdVbl(fd);
        int key = 0;
        try {
            key = iocp.bssocibte(this, h);
        } cbtch (ShutdownChbnnelGroupException x) {
            if (fbilIfGroupShutdown) {
                closesocket0(h);
                throw x;
            }
        } cbtch (IOException x) {
            closesocket0(h);
            throw x;
        }

        this.hbndle = h;
        this.iocp = iocp;
        this.completionKey = key;
        this.ioCbche = new PendingIoCbche();

        // bllocbte WSABUF brrbys
        this.rebdBufferArrby = unsbfe.bllocbteMemory(SIZEOF_WSABUFARRAY);
        this.writeBufferArrby = unsbfe.bllocbteMemory(SIZEOF_WSABUFARRAY);
    }

    WindowsAsynchronousSocketChbnnelImpl(Iocp iocp) throws IOException {
        this(iocp, true);
    }

    @Override
    public AsynchronousChbnnelGroupImpl group() {
        return iocp;
    }

    /**
     * Invoked by Iocp when bn I/O operbtion competes.
     */
    @Override
    public <V,A> PendingFuture<V,A> getByOverlbpped(long overlbpped) {
        return ioCbche.remove(overlbpped);
    }

    // invoked by WindowsAsynchronousServerSocketChbnnelImpl
    long hbndle() {
        return hbndle;
    }

    // invoked by WindowsAsynchronousServerSocketChbnnelImpl when new connection
    // bccept
    void setConnected(InetSocketAddress locblAddress,
                      InetSocketAddress remoteAddress)
    {
        synchronized (stbteLock) {
            stbte = ST_CONNECTED;
            this.locblAddress = locblAddress;
            this.remoteAddress = remoteAddress;
        }
    }

    @Override
    void implClose() throws IOException {
        // close socket (mby cbuse outstbnding bsync I/O operbtions to fbil).
        closesocket0(hbndle);

        // wbits until bll I/O operbtions hbve completed
        ioCbche.close();

        // relebse brrbys of WSABUF structures
        unsbfe.freeMemory(rebdBufferArrby);
        unsbfe.freeMemory(writeBufferArrby);

        // finblly disbssocibte from the completion port (key cbn be 0 if
        // chbnnel crebted when group is shutdown)
        if (completionKey != 0)
            iocp.disbssocibte(completionKey);
    }

    @Override
    public void onCbncel(PendingFuture<?,?> tbsk) {
        if (tbsk.getContext() instbnceof ConnectTbsk)
            killConnect();
        if (tbsk.getContext() instbnceof RebdTbsk)
            killRebding();
        if (tbsk.getContext() instbnceof WriteTbsk)
            killWriting();
    }

    /**
     * Implements the tbsk to initibte b connection bnd the hbndler to
     * consume the result when the connection is estbblished (or fbils).
     */
    privbte clbss ConnectTbsk<A> implements Runnbble, Iocp.ResultHbndler {
        privbte finbl InetSocketAddress remote;
        privbte finbl PendingFuture<Void,A> result;

        ConnectTbsk(InetSocketAddress remote, PendingFuture<Void,A> result) {
            this.remote = remote;
            this.result = result;
        }

        privbte void closeChbnnel() {
            try {
                close();
            } cbtch (IOException ignore) { }
        }

        privbte IOException toIOException(Throwbble x) {
            if (x instbnceof IOException) {
                if (x instbnceof ClosedChbnnelException)
                    x = new AsynchronousCloseException();
                return (IOException)x;
            }
            return new IOException(x);
        }

        /**
         * Invoke bfter b connection is successfully estbblished.
         */
        privbte void bfterConnect() throws IOException {
            updbteConnectContext(hbndle);
            synchronized (stbteLock) {
                stbte = ST_CONNECTED;
                remoteAddress = remote;
            }
        }

        /**
         * Tbsk to initibte b connection.
         */
        @Override
        public void run() {
            long overlbpped = 0L;
            Throwbble exc = null;
            try {
                begin();

                // synchronize on result to bllow this threbd hbndle the cbse
                // where the connection is estbblished immedibtely.
                synchronized (result) {
                    overlbpped = ioCbche.bdd(result);
                    // initibte the connection
                    int n = connect0(hbndle, Net.isIPv6Avbilbble(), remote.getAddress(),
                                     remote.getPort(), overlbpped);
                    if (n == IOStbtus.UNAVAILABLE) {
                        // connection is pending
                        return;
                    }

                    // connection estbblished immedibtely
                    bfterConnect();
                    result.setResult(null);
                }
            } cbtch (Throwbble x) {
                if (overlbpped != 0L)
                    ioCbche.remove(overlbpped);
                exc = x;
            } finblly {
                end();
            }

            if (exc != null) {
                closeChbnnel();
                result.setFbilure(toIOException(exc));
            }
            Invoker.invoke(result);
        }

        /**
         * Invoked by hbndler threbd when connection estbblished.
         */
        @Override
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            Throwbble exc = null;
            try {
                begin();
                bfterConnect();
                result.setResult(null);
            } cbtch (Throwbble x) {
                // chbnnel is closed or unbble to finish connect
                exc = x;
            } finblly {
                end();
            }

            // cbn't close chbnnel while in begin/end block
            if (exc != null) {
                closeChbnnel();
                result.setFbilure(toIOException(exc));
            }

            if (cbnInvokeDirect) {
                Invoker.invokeUnchecked(result);
            } else {
                Invoker.invoke(result);
            }
        }

        /**
         * Invoked by hbndler threbd when fbiled to estbblish connection.
         */
        @Override
        public void fbiled(int error, IOException x) {
            if (isOpen()) {
                closeChbnnel();
                result.setFbilure(x);
            } else {
                result.setFbilure(new AsynchronousCloseException());
            }
            Invoker.invoke(result);
        }
    }

    privbte void doPrivilegedBind(finbl SocketAddress sb) throws IOException {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws IOException {
                    bind(sb);
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw (IOException) e.getException();
        }
    }

    @Override
    <A> Future<Void> implConnect(SocketAddress remote,
                                 A bttbchment,
                                 CompletionHbndler<Void,? super A> hbndler)
    {
        if (!isOpen()) {
            Throwbble exc = new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withFbilure(exc);
            Invoker.invoke(this, hbndler, bttbchment, null, exc);
            return null;
        }

        InetSocketAddress isb = Net.checkAddress(remote);

        // permission check
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkConnect(isb.getAddress().getHostAddress(), isb.getPort());

        // check bnd updbte stbte
        // ConnectEx requires the socket to be bound to b locbl bddress
        IOException bindException = null;
        synchronized (stbteLock) {
            if (stbte == ST_CONNECTED)
                throw new AlrebdyConnectedException();
            if (stbte == ST_PENDING)
                throw new ConnectionPendingException();
            if (locblAddress == null) {
                try {
                    SocketAddress bny = new InetSocketAddress(0);
                    if (sm == null) {
                        bind(bny);
                    } else {
                        doPrivilegedBind(bny);
                    }
                } cbtch (IOException x) {
                    bindException = x;
                }
            }
            if (bindException == null)
                stbte = ST_PENDING;
        }

        // hbndle bind fbilure
        if (bindException != null) {
            try {
                close();
            } cbtch (IOException ignore) { }
            if (hbndler == null)
                return CompletedFuture.withFbilure(bindException);
            Invoker.invoke(this, hbndler, bttbchment, null, bindException);
            return null;
        }

        // setup tbsk
        PendingFuture<Void,A> result =
            new PendingFuture<Void,A>(this, hbndler, bttbchment);
        ConnectTbsk<A> tbsk = new ConnectTbsk<A>(isb, result);
        result.setContext(tbsk);

        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            tbsk.run();
        } else {
            Invoker.invokeOnThrebdInThrebdPool(this, tbsk);
        }
        return result;
    }

    /**
     * Implements the tbsk to initibte b rebd bnd the hbndler to consume the
     * result when the rebd completes.
     */
    privbte clbss RebdTbsk<V,A> implements Runnbble, Iocp.ResultHbndler {
        privbte finbl ByteBuffer[] bufs;
        privbte finbl int numBufs;
        privbte finbl boolebn scbtteringRebd;
        privbte finbl PendingFuture<V,A> result;

        // set by run method
        privbte ByteBuffer[] shbdow;

        RebdTbsk(ByteBuffer[] bufs,
                 boolebn scbtteringRebd,
                 PendingFuture<V,A> result)
        {
            this.bufs = bufs;
            this.numBufs = (bufs.length > MAX_WSABUF) ? MAX_WSABUF : bufs.length;
            this.scbtteringRebd = scbtteringRebd;
            this.result = result;
        }

        /**
         * Invoked prior to rebd to prepbre the WSABUF brrby. Where necessbry,
         * it substitutes non-direct buffers with direct buffers.
         */
        void prepbreBuffers() {
            shbdow = new ByteBuffer[numBufs];
            long bddress = rebdBufferArrby;
            for (int i=0; i<numBufs; i++) {
                ByteBuffer dst = bufs[i];
                int pos = dst.position();
                int lim = dst.limit();
                bssert (pos <= lim);
                int rem = (pos <= lim ? lim - pos : 0);
                long b;
                if (!(dst instbnceof DirectBuffer)) {
                    // substitute with direct buffer
                    ByteBuffer bb = Util.getTemporbryDirectBuffer(rem);
                    shbdow[i] = bb;
                    b = ((DirectBuffer)bb).bddress();
                } else {
                    shbdow[i] = dst;
                    b = ((DirectBuffer)dst).bddress() + pos;
                }
                unsbfe.putAddress(bddress + OFFSETOF_BUF, b);
                unsbfe.putInt(bddress + OFFSETOF_LEN, rem);
                bddress += SIZEOF_WSABUF;
            }
        }

        /**
         * Invoked bfter b rebd hbs completed to updbte the buffer positions
         * bnd relebse bny substituted buffers.
         */
        void updbteBuffers(int bytesRebd) {
            for (int i=0; i<numBufs; i++) {
                ByteBuffer nextBuffer = shbdow[i];
                int pos = nextBuffer.position();
                int len = nextBuffer.rembining();
                if (bytesRebd >= len) {
                    bytesRebd -= len;
                    int newPosition = pos + len;
                    try {
                        nextBuffer.position(newPosition);
                    } cbtch (IllegblArgumentException x) {
                        // position chbnged by bnother
                    }
                } else { // Buffers not completely filled
                    if (bytesRebd > 0) {
                        bssert(pos + bytesRebd < (long)Integer.MAX_VALUE);
                        int newPosition = pos + bytesRebd;
                        try {
                            nextBuffer.position(newPosition);
                        } cbtch (IllegblArgumentException x) {
                            // position chbnged by bnother
                        }
                    }
                    brebk;
                }
            }

            // Put results from shbdow into the slow buffers
            for (int i=0; i<numBufs; i++) {
                if (!(bufs[i] instbnceof DirectBuffer)) {
                    shbdow[i].flip();
                    try {
                        bufs[i].put(shbdow[i]);
                    } cbtch (BufferOverflowException x) {
                        // position chbnged by bnother
                    }
                }
            }
        }

        void relebseBuffers() {
            for (int i=0; i<numBufs; i++) {
                if (!(bufs[i] instbnceof DirectBuffer)) {
                    Util.relebseTemporbryDirectBuffer(shbdow[i]);
                }
            }
        }

        @Override
        @SuppressWbrnings("unchecked")
        public void run() {
            long overlbpped = 0L;
            boolebn prepbred = fblse;
            boolebn pending = fblse;

            try {
                begin();

                // substitute non-direct buffers
                prepbreBuffers();
                prepbred = true;

                // get bn OVERLAPPED structure (from the cbche or bllocbte)
                overlbpped = ioCbche.bdd(result);

                // initibte rebd
                int n = rebd0(hbndle, numBufs, rebdBufferArrby, overlbpped);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pending
                    pending = true;
                    return;
                }
                if (n == IOStbtus.EOF) {
                    // input shutdown
                    enbbleRebding();
                    if (scbtteringRebd) {
                        result.setResult((V)Long.vblueOf(-1L));
                    } else {
                        result.setResult((V)Integer.vblueOf(-1));
                    }
                } else {
                    throw new InternblError("Rebd completed immedibtely");
                }
            } cbtch (Throwbble x) {
                // fbiled to initibte rebd
                // reset rebd flbg before relebsing wbiters
                enbbleRebding();
                if (x instbnceof ClosedChbnnelException)
                    x = new AsynchronousCloseException();
                if (!(x instbnceof IOException))
                    x = new IOException(x);
                result.setFbilure(x);
            } finblly {
                // relebse resources if I/O not pending
                if (!pending) {
                    if (overlbpped != 0L)
                        ioCbche.remove(overlbpped);
                    if (prepbred)
                        relebseBuffers();
                }
                end();
            }

            // invoke completion hbndler
            Invoker.invoke(result);
        }

        /**
         * Executed when the I/O hbs completed
         */
        @Override
        @SuppressWbrnings("unchecked")
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            if (bytesTrbnsferred == 0) {
                bytesTrbnsferred = -1;  // EOF
            } else {
                updbteBuffers(bytesTrbnsferred);
            }

            // return direct buffer to cbche if substituted
            relebseBuffers();

            // relebse wbiters if not blrebdy relebsed by timeout
            synchronized (result) {
                if (result.isDone())
                    return;
                enbbleRebding();
                if (scbtteringRebd) {
                    result.setResult((V)Long.vblueOf(bytesTrbnsferred));
                } else {
                    result.setResult((V)Integer.vblueOf(bytesTrbnsferred));
                }
            }
            if (cbnInvokeDirect) {
                Invoker.invokeUnchecked(result);
            } else {
                Invoker.invoke(result);
            }
        }

        @Override
        public void fbiled(int error, IOException x) {
            // return direct buffer to cbche if substituted
            relebseBuffers();

            // relebse wbiters if not blrebdy relebsed by timeout
            if (!isOpen())
                x = new AsynchronousCloseException();

            synchronized (result) {
                if (result.isDone())
                    return;
                enbbleRebding();
                result.setFbilure(x);
            }
            Invoker.invoke(result);
        }

        /**
         * Invoked if timeout expires before it is cbncelled
         */
        void timeout() {
            // synchronize on result bs the I/O could complete/fbil
            synchronized (result) {
                if (result.isDone())
                    return;

                // kill further rebding before relebsing wbiters
                enbbleRebding(true);
                result.setFbilure(new InterruptedByTimeoutException());
            }

            // invoke hbndler without bny locks
            Invoker.invoke(result);
        }
    }

    @Override
    <V extends Number,A> Future<V> implRebd(boolebn isScbtteringRebd,
                                            ByteBuffer dst,
                                            ByteBuffer[] dsts,
                                            long timeout,
                                            TimeUnit unit,
                                            A bttbchment,
                                            CompletionHbndler<V,? super A> hbndler)
    {
        // setup tbsk
        PendingFuture<V,A> result =
            new PendingFuture<V,A>(this, hbndler, bttbchment);
        ByteBuffer[] bufs;
        if (isScbtteringRebd) {
            bufs = dsts;
        } else {
            bufs = new ByteBuffer[1];
            bufs[0] = dst;
        }
        finbl RebdTbsk<V,A> rebdTbsk =
                new RebdTbsk<V,A>(bufs, isScbtteringRebd, result);
        result.setContext(rebdTbsk);

        // schedule timeout
        if (timeout > 0L) {
            Future<?> timeoutTbsk = iocp.schedule(new Runnbble() {
                public void run() {
                    rebdTbsk.timeout();
                }
            }, timeout, unit);
            result.setTimeoutTbsk(timeoutTbsk);
        }

        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            rebdTbsk.run();
        } else {
            Invoker.invokeOnThrebdInThrebdPool(this, rebdTbsk);
        }
        return result;
    }

    /**
     * Implements the tbsk to initibte b write bnd the hbndler to consume the
     * result when the write completes.
     */
    privbte clbss WriteTbsk<V,A> implements Runnbble, Iocp.ResultHbndler {
        privbte finbl ByteBuffer[] bufs;
        privbte finbl int numBufs;
        privbte finbl boolebn gbtheringWrite;
        privbte finbl PendingFuture<V,A> result;

        // set by run method
        privbte ByteBuffer[] shbdow;

        WriteTbsk(ByteBuffer[] bufs,
                  boolebn gbtheringWrite,
                  PendingFuture<V,A> result)
        {
            this.bufs = bufs;
            this.numBufs = (bufs.length > MAX_WSABUF) ? MAX_WSABUF : bufs.length;
            this.gbtheringWrite = gbtheringWrite;
            this.result = result;
        }

        /**
         * Invoked prior to write to prepbre the WSABUF brrby. Where necessbry,
         * it substitutes non-direct buffers with direct buffers.
         */
        void prepbreBuffers() {
            shbdow = new ByteBuffer[numBufs];
            long bddress = writeBufferArrby;
            for (int i=0; i<numBufs; i++) {
                ByteBuffer src = bufs[i];
                int pos = src.position();
                int lim = src.limit();
                bssert (pos <= lim);
                int rem = (pos <= lim ? lim - pos : 0);
                long b;
                if (!(src instbnceof DirectBuffer)) {
                    // substitute with direct buffer
                    ByteBuffer bb = Util.getTemporbryDirectBuffer(rem);
                    bb.put(src);
                    bb.flip();
                    src.position(pos);  // lebve hebp buffer untouched for now
                    shbdow[i] = bb;
                    b = ((DirectBuffer)bb).bddress();
                } else {
                    shbdow[i] = src;
                    b = ((DirectBuffer)src).bddress() + pos;
                }
                unsbfe.putAddress(bddress + OFFSETOF_BUF, b);
                unsbfe.putInt(bddress + OFFSETOF_LEN, rem);
                bddress += SIZEOF_WSABUF;
            }
        }

        /**
         * Invoked bfter b write hbs completed to updbte the buffer positions
         * bnd relebse bny substituted buffers.
         */
        void updbteBuffers(int bytesWritten) {
            // Notify the buffers how mbny bytes were tbken
            for (int i=0; i<numBufs; i++) {
                ByteBuffer nextBuffer = bufs[i];
                int pos = nextBuffer.position();
                int lim = nextBuffer.limit();
                int len = (pos <= lim ? lim - pos : lim);
                if (bytesWritten >= len) {
                    bytesWritten -= len;
                    int newPosition = pos + len;
                    try {
                        nextBuffer.position(newPosition);
                    } cbtch (IllegblArgumentException x) {
                        // position chbnged by someone else
                    }
                } else { // Buffers not completely filled
                    if (bytesWritten > 0) {
                        bssert(pos + bytesWritten < (long)Integer.MAX_VALUE);
                        int newPosition = pos + bytesWritten;
                        try {
                            nextBuffer.position(newPosition);
                        } cbtch (IllegblArgumentException x) {
                            // position chbnged by someone else
                        }
                    }
                    brebk;
                }
            }
        }

        void relebseBuffers() {
            for (int i=0; i<numBufs; i++) {
                if (!(bufs[i] instbnceof DirectBuffer)) {
                    Util.relebseTemporbryDirectBuffer(shbdow[i]);
                }
            }
        }

        @Override
        //@SuppressWbrnings("unchecked")
        public void run() {
            long overlbpped = 0L;
            boolebn prepbred = fblse;
            boolebn pending = fblse;
            boolebn shutdown = fblse;

            try {
                begin();

                // substitute non-direct buffers
                prepbreBuffers();
                prepbred = true;

                // get bn OVERLAPPED structure (from the cbche or bllocbte)
                overlbpped = ioCbche.bdd(result);
                int n = write0(hbndle, numBufs, writeBufferArrby, overlbpped);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pending
                    pending = true;
                    return;
                }
                if (n == IOStbtus.EOF) {
                    // specibl cbse for shutdown output
                    shutdown = true;
                    throw new ClosedChbnnelException();
                }
                // write completed immedibtely
                throw new InternblError("Write completed immedibtely");
            } cbtch (Throwbble x) {
                // write fbiled. Enbble writing before relebsing wbiters.
                enbbleWriting();
                if (!shutdown && (x instbnceof ClosedChbnnelException))
                    x = new AsynchronousCloseException();
                if (!(x instbnceof IOException))
                    x = new IOException(x);
                result.setFbilure(x);
            } finblly {
                // relebse resources if I/O not pending
                if (!pending) {
                    if (overlbpped != 0L)
                        ioCbche.remove(overlbpped);
                    if (prepbred)
                        relebseBuffers();
                }
                end();
            }

            // invoke completion hbndler
            Invoker.invoke(result);
        }

        /**
         * Executed when the I/O hbs completed
         */
        @Override
        @SuppressWbrnings("unchecked")
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            updbteBuffers(bytesTrbnsferred);

            // return direct buffer to cbche if substituted
            relebseBuffers();

            // relebse wbiters if not blrebdy relebsed by timeout
            synchronized (result) {
                if (result.isDone())
                    return;
                enbbleWriting();
                if (gbtheringWrite) {
                    result.setResult((V)Long.vblueOf(bytesTrbnsferred));
                } else {
                    result.setResult((V)Integer.vblueOf(bytesTrbnsferred));
                }
            }
            if (cbnInvokeDirect) {
                Invoker.invokeUnchecked(result);
            } else {
                Invoker.invoke(result);
            }
        }

        @Override
        public void fbiled(int error, IOException x) {
            // return direct buffer to cbche if substituted
            relebseBuffers();

            // relebse wbiters if not blrebdy relebsed by timeout
            if (!isOpen())
                x = new AsynchronousCloseException();

            synchronized (result) {
                if (result.isDone())
                    return;
                enbbleWriting();
                result.setFbilure(x);
            }
            Invoker.invoke(result);
        }

        /**
         * Invoked if timeout expires before it is cbncelled
         */
        void timeout() {
            // synchronize on result bs the I/O could complete/fbil
            synchronized (result) {
                if (result.isDone())
                    return;

                // kill further writing before relebsing wbiters
                enbbleWriting(true);
                result.setFbilure(new InterruptedByTimeoutException());
            }

            // invoke hbndler without bny locks
            Invoker.invoke(result);
        }
    }

    @Override
    <V extends Number,A> Future<V> implWrite(boolebn gbtheringWrite,
                                             ByteBuffer src,
                                             ByteBuffer[] srcs,
                                             long timeout,
                                             TimeUnit unit,
                                             A bttbchment,
                                             CompletionHbndler<V,? super A> hbndler)
    {
        // setup tbsk
        PendingFuture<V,A> result =
            new PendingFuture<V,A>(this, hbndler, bttbchment);
        ByteBuffer[] bufs;
        if (gbtheringWrite) {
            bufs = srcs;
        } else {
            bufs = new ByteBuffer[1];
            bufs[0] = src;
        }
        finbl WriteTbsk<V,A> writeTbsk =
                new WriteTbsk<V,A>(bufs, gbtheringWrite, result);
        result.setContext(writeTbsk);

        // schedule timeout
        if (timeout > 0L) {
            Future<?> timeoutTbsk = iocp.schedule(new Runnbble() {
                public void run() {
                    writeTbsk.timeout();
                }
            }, timeout, unit);
            result.setTimeoutTbsk(timeoutTbsk);
        }

        // initibte I/O (cbn only be done from threbd in threbd pool)
        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            writeTbsk.run();
        } else {
            Invoker.invokeOnThrebdInThrebdPool(this, writeTbsk);
        }
        return result;
    }

    // -- Nbtive methods --

    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive int connect0(long socket, boolebn preferIPv6,
        InetAddress remote, int remotePort, long overlbpped) throws IOException;

    privbte stbtic nbtive void updbteConnectContext(long socket) throws IOException;

    privbte stbtic nbtive int rebd0(long socket, int count, long bddres, long overlbpped)
        throws IOException;

    privbte stbtic nbtive int write0(long socket, int count, long bddress,
        long overlbpped) throws IOException;

    privbte stbtic nbtive void shutdown0(long socket, int how) throws IOException;

    privbte stbtic nbtive void closesocket0(long socket) throws IOException;

    stbtic {
        IOUtil.lobd();
        initIDs();
    }
}
