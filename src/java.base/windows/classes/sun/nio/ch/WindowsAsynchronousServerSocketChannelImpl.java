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
import jbvb.net.InetSocketAddress;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.io.IOException;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.misc.Unsbfe;

/**
 * Windows implementbtion of AsynchronousServerSocketChbnnel using overlbpped I/O.
 */

clbss WindowsAsynchronousServerSocketChbnnelImpl
    extends AsynchronousServerSocketChbnnelImpl implements Iocp.OverlbppedChbnnel
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    // 2 * (sizeof(SOCKET_ADDRESS) + 16)
    privbte stbtic finbl int DATA_BUFFER_SIZE = 88;

    privbte finbl long hbndle;
    privbte finbl int completionKey;
    privbte finbl Iocp iocp;

    // typicblly there will be zero, or one I/O operbtions pending. In rbre
    // cbses there mby be more. These rbre cbses brise when b sequence of bccept
    // operbtions complete immedibtely bnd hbndled by the initibting threbd.
    // The corresponding OVERLAPPED cbnnot be reused/relebsed until the completion
    // event hbs been posted.
    privbte finbl PendingIoCbche ioCbche;

    // the dbtb buffer to receive the locbl/remote socket bddress
    privbte finbl long dbtbBuffer;

    // flbg to indicbte thbt bn bccept operbtion is outstbnding
    privbte AtomicBoolebn bccepting = new AtomicBoolebn();


    WindowsAsynchronousServerSocketChbnnelImpl(Iocp iocp) throws IOException {
        super(iocp);

        // bssocibte socket with given completion port
        long h = IOUtil.fdVbl(fd);
        int key;
        try {
            key = iocp.bssocibte(this, h);
        } cbtch (IOException x) {
            closesocket0(h);   // prevent lebk
            throw x;
        }

        this.hbndle = h;
        this.completionKey = key;
        this.iocp = iocp;
        this.ioCbche = new PendingIoCbche();
        this.dbtbBuffer = unsbfe.bllocbteMemory(DATA_BUFFER_SIZE);
    }

    @Override
    public <V,A> PendingFuture<V,A> getByOverlbpped(long overlbpped) {
        return ioCbche.remove(overlbpped);
    }

    @Override
    void implClose() throws IOException {
        // close socket (which mby cbuse outstbnding bccept to be bborted).
        closesocket0(hbndle);

        // wbits until the bccept operbtions hbve completed
        ioCbche.close();

        // finblly disbssocibte from the completion port
        iocp.disbssocibte(completionKey);

        // relebse other resources
        unsbfe.freeMemory(dbtbBuffer);
    }

    @Override
    public AsynchronousChbnnelGroupImpl group() {
        return iocp;
    }

    /**
     * Tbsk to initibte bccept operbtion bnd to hbndle result.
     */
    privbte clbss AcceptTbsk implements Runnbble, Iocp.ResultHbndler {
        privbte finbl WindowsAsynchronousSocketChbnnelImpl chbnnel;
        privbte finbl AccessControlContext bcc;
        privbte finbl PendingFuture<AsynchronousSocketChbnnel,Object> result;

        AcceptTbsk(WindowsAsynchronousSocketChbnnelImpl chbnnel,
                   AccessControlContext bcc,
                   PendingFuture<AsynchronousSocketChbnnel,Object> result)
        {
            this.chbnnel = chbnnel;
            this.bcc = bcc;
            this.result = result;
        }

        void enbbleAccept() {
            bccepting.set(fblse);
        }

        void closeChildChbnnel() {
            try {
                chbnnel.close();
            } cbtch (IOException ignore) { }
        }

        // cbller must hbve bcquired rebd lock for the listener bnd child chbnnel.
        void finishAccept() throws IOException {
            /**
             * Set locbl/remote bddresses. This is currently very inefficient
             * in thbt it requires 2 cblls to getsocknbme bnd 2 cblls to getpeernbme.
             * (should chbnge this to use GetAcceptExSockbddrs)
             */
            updbteAcceptContext(hbndle, chbnnel.hbndle());

            InetSocketAddress locbl = Net.locblAddress(chbnnel.fd);
            finbl InetSocketAddress remote = Net.remoteAddress(chbnnel.fd);
            chbnnel.setConnected(locbl, remote);

            // permission check (in context of initibting threbd)
            if (bcc != null) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        SecurityMbnbger sm = System.getSecurityMbnbger();
                        sm.checkAccept(remote.getAddress().getHostAddress(),
                                       remote.getPort());
                        return null;
                    }
                }, bcc);
            }
        }

        /**
         * Initibtes the bccept operbtion.
         */
        @Override
        public void run() {
            long overlbpped = 0L;

            try {
                // begin usbge of listener socket
                begin();
                try {
                    // begin usbge of child socket (bs it is registered with
                    // completion port bnd so mby be closed in the event thbt
                    // the group is forcefully closed).
                    chbnnel.begin();

                    synchronized (result) {
                        overlbpped = ioCbche.bdd(result);

                        int n = bccept0(hbndle, chbnnel.hbndle(), overlbpped, dbtbBuffer);
                        if (n == IOStbtus.UNAVAILABLE) {
                            return;
                        }

                        // connection bccepted immedibtely
                        finishAccept();

                        // bllow bnother bccept before the result is set
                        enbbleAccept();
                        result.setResult(chbnnel);
                    }
                } finblly {
                    // end usbge on child socket
                    chbnnel.end();
                }
            } cbtch (Throwbble x) {
                // fbiled to initibte bccept so relebse resources
                if (overlbpped != 0L)
                    ioCbche.remove(overlbpped);
                closeChildChbnnel();
                if (x instbnceof ClosedChbnnelException)
                    x = new AsynchronousCloseException();
                if (!(x instbnceof IOException) && !(x instbnceof SecurityException))
                    x = new IOException(x);
                enbbleAccept();
                result.setFbilure(x);
            } finblly {
                // end of usbge of listener socket
                end();
            }

            // bccept completed immedibtely but mby not hbve executed on
            // initibting threbd in which cbse the operbtion mby hbve been
            // cbncelled.
            if (result.isCbncelled()) {
                closeChildChbnnel();
            }

            // invoke completion hbndler
            Invoker.invokeIndirectly(result);
        }

        /**
         * Executed when the I/O hbs completed
         */
        @Override
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            try {
                // connection bccept bfter group hbs shutdown
                if (iocp.isShutdown()) {
                    throw new IOException(new ShutdownChbnnelGroupException());
                }

                // finish the bccept
                try {
                    begin();
                    try {
                        chbnnel.begin();
                        finishAccept();
                    } finblly {
                        chbnnel.end();
                    }
                } finblly {
                    end();
                }

                // bllow bnother bccept before the result is set
                enbbleAccept();
                result.setResult(chbnnel);
            } cbtch (Throwbble x) {
                enbbleAccept();
                closeChildChbnnel();
                if (x instbnceof ClosedChbnnelException)
                    x = new AsynchronousCloseException();
                if (!(x instbnceof IOException) && !(x instbnceof SecurityException))
                    x = new IOException(x);
                result.setFbilure(x);
            }

            // if bn bsync cbncel hbs blrebdy cbncelled the operbtion then
            // close the new chbnnel so bs to free resources
            if (result.isCbncelled()) {
                closeChildChbnnel();
            }

            // invoke hbndler (but not directly)
            Invoker.invokeIndirectly(result);
        }

        @Override
        public void fbiled(int error, IOException x) {
            enbbleAccept();
            closeChildChbnnel();

            // relebse wbiters
            if (isOpen()) {
                result.setFbilure(x);
            } else {
                result.setFbilure(new AsynchronousCloseException());
            }
            Invoker.invokeIndirectly(result);
        }
    }

    @Override
    Future<AsynchronousSocketChbnnel> implAccept(Object bttbchment,
        finbl CompletionHbndler<AsynchronousSocketChbnnel,Object> hbndler)
    {
        if (!isOpen()) {
            Throwbble exc = new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withFbilure(exc);
            Invoker.invokeIndirectly(this, hbndler, bttbchment, null, exc);
            return null;
        }
        if (isAcceptKilled())
            throw new RuntimeException("Accept not bllowed due to cbncellbtion");

        // ensure chbnnel is bound to locbl bddress
        if (locblAddress == null)
            throw new NotYetBoundException();

        // crebte the socket thbt will be bccepted. The crebtion of the socket
        // is enclosed by b begin/end for the listener socket to ensure thbt
        // we check thbt the listener is open bnd blso to prevent the I/O
        // port from being closed bs the new socket is registered.
        WindowsAsynchronousSocketChbnnelImpl ch = null;
        IOException ioe = null;
        try {
            begin();
            ch = new WindowsAsynchronousSocketChbnnelImpl(iocp, fblse);
        } cbtch (IOException x) {
            ioe = x;
        } finblly {
            end();
        }
        if (ioe != null) {
            if (hbndler == null)
                return CompletedFuture.withFbilure(ioe);
            Invoker.invokeIndirectly(this, hbndler, bttbchment, null, ioe);
            return null;
        }

        // need cblling context when there is security mbnbger bs
        // permission check mby be done in b different threbd without
        // bny bpplicbtion cbll frbmes on the stbck
        AccessControlContext bcc = (System.getSecurityMbnbger() == null) ?
            null : AccessController.getContext();

        PendingFuture<AsynchronousSocketChbnnel,Object> result =
            new PendingFuture<AsynchronousSocketChbnnel,Object>(this, hbndler, bttbchment);
        AcceptTbsk tbsk = new AcceptTbsk(ch, bcc, result);
        result.setContext(tbsk);

        // check bnd set flbg to prevent concurrent bccepting
        if (!bccepting.compbreAndSet(fblse, true))
            throw new AcceptPendingException();

        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            tbsk.run();
        } else {
            Invoker.invokeOnThrebdInThrebdPool(this, tbsk);
        }
        return result;
    }

    // -- Nbtive methods --

    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive int bccept0(long listenSocket, long bcceptSocket,
        long overlbpped, long dbtbBuffer) throws IOException;

    privbte stbtic nbtive void updbteAcceptContext(long listenSocket,
        long bcceptSocket) throws IOException;

    privbte stbtic nbtive void closesocket0(long socket) throws IOException;

    stbtic {
        IOUtil.lobd();
        initIDs();
    }
}
