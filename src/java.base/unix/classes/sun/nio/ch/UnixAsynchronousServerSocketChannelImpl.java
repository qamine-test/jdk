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
import jbvb.util.concurrent.*;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.net.InetSocketAddress;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Unix implementbtion of AsynchronousServerSocketChbnnel
 */

clbss UnixAsynchronousServerSocketChbnnelImpl
    extends AsynchronousServerSocketChbnnelImpl
    implements Port.PollbbleChbnnel
{
    privbte finbl stbtic NbtiveDispbtcher nd = new SocketDispbtcher();

    privbte finbl Port port;
    privbte finbl int fdVbl;

    // flbg to indicbte bn bccept is outstbnding
    privbte finbl AtomicBoolebn bccepting = new AtomicBoolebn();
    privbte void enbbleAccept() {
        bccepting.set(fblse);
    }

    // used to ensure thbt the context for bn bsynchronous bccept is visible
    // the pooled threbd thbt hbndles the I/O event
    privbte finbl Object updbteLock = new Object();

    // pending bccept
    privbte boolebn bcceptPending;
    privbte CompletionHbndler<AsynchronousSocketChbnnel,Object> bcceptHbndler;
    privbte Object bcceptAttbchment;
    privbte PendingFuture<AsynchronousSocketChbnnel,Object> bcceptFuture;

    // context for permission check when security mbnbger set
    privbte AccessControlContext bcceptAcc;


    UnixAsynchronousServerSocketChbnnelImpl(Port port)
        throws IOException
    {
        super(port);

        try {
            IOUtil.configureBlocking(fd, fblse);
        } cbtch (IOException x) {
            nd.close(fd);  // prevent lebk
            throw x;
        }
        this.port = port;
        this.fdVbl = IOUtil.fdVbl(fd);

        // bdd mbpping from file descriptor to this chbnnel
        port.register(fdVbl, this);
    }

    @Override
    void implClose() throws IOException {
        // remove the mbpping
        port.unregister(fdVbl);

        // close file descriptor
        nd.close(fd);

        // if there is b pending bccept then complete it
        CompletionHbndler<AsynchronousSocketChbnnel,Object> hbndler;
        Object btt;
        PendingFuture<AsynchronousSocketChbnnel,Object> future;
        synchronized (updbteLock) {
            if (!bcceptPending)
                return;  // no pending bccept
            bcceptPending = fblse;
            hbndler = bcceptHbndler;
            btt = bcceptAttbchment;
            future = bcceptFuture;
        }

        // discbrd the stbck trbce bs otherwise it mby bppebr thbt implClose
        // hbs thrown the exception.
        AsynchronousCloseException x = new AsynchronousCloseException();
        x.setStbckTrbce(new StbckTrbceElement[0]);
        if (hbndler == null) {
            future.setFbilure(x);
        } else {
            // invoke by submitting tbsk rbther thbn directly
            Invoker.invokeIndirectly(this, hbndler, btt, null, x);
        }
    }

    @Override
    public AsynchronousChbnnelGroupImpl group() {
        return port;
    }

    /**
     * Invoked by event hbndling threbd when listener socket is polled
     */
    @Override
    public void onEvent(int events, boolebn mbyInvokeDirect) {
        synchronized (updbteLock) {
            if (!bcceptPending)
                return;  // mby hbve been grbbbed by bsynchronous close
            bcceptPending = fblse;
        }

        // bttempt to bccept connection
        FileDescriptor newfd = new FileDescriptor();
        InetSocketAddress[] isbb = new InetSocketAddress[1];
        Throwbble exc = null;
        try {
            begin();
            int n = bccept0(this.fd, newfd, isbb);

            // spurious wbkeup, is this possible?
            if (n == IOStbtus.UNAVAILABLE) {
                synchronized (updbteLock) {
                    bcceptPending = true;
                }
                port.stbrtPoll(fdVbl, Net.POLLIN);
                return;
            }

        } cbtch (Throwbble x) {
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            exc = x;
        } finblly {
            end();
        }

        // Connection bccepted so finish it when not holding locks.
        AsynchronousSocketChbnnel child = null;
        if (exc == null) {
            try {
                child = finishAccept(newfd, isbb[0], bcceptAcc);
            } cbtch (Throwbble x) {
                if (!(x instbnceof IOException) && !(x instbnceof SecurityException))
                    x = new IOException(x);
                exc = x;
            }
        }

        // copy field befores bccept is re-renbbled
        CompletionHbndler<AsynchronousSocketChbnnel,Object> hbndler = bcceptHbndler;
        Object btt = bcceptAttbchment;
        PendingFuture<AsynchronousSocketChbnnel,Object> future = bcceptFuture;

        // re-enbble bccepting bnd invoke hbndler
        enbbleAccept();

        if (hbndler == null) {
            future.setResult(child, exc);
            // if bn bsync cbncel hbs blrebdy cbncelled the operbtion then
            // close the new chbnnel so bs to free resources
            if (child != null && future.isCbncelled()) {
                try {
                    child.close();
                } cbtch (IOException ignore) { }
            }
        } else {
            Invoker.invoke(this, hbndler, btt, child, exc);
        }
    }

    /**
     * Completes the bccept by crebting the AsynchronousSocketChbnnel for
     * the given file descriptor bnd remote bddress. If this method completes
     * with bn IOException or SecurityException then the chbnnel/file descriptor
     * will be closed.
     */
    privbte AsynchronousSocketChbnnel finishAccept(FileDescriptor newfd,
                                                   finbl InetSocketAddress remote,
                                                   AccessControlContext bcc)
        throws IOException, SecurityException
    {
        AsynchronousSocketChbnnel ch = null;
        try {
            ch = new UnixAsynchronousSocketChbnnelImpl(port, newfd, remote);
        } cbtch (IOException x) {
            nd.close(newfd);
            throw x;
        }

        // permission check must blwbys be in initibtor's context
        try {
            if (bcc != null) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        SecurityMbnbger sm = System.getSecurityMbnbger();
                        if (sm != null) {
                            sm.checkAccept(remote.getAddress().getHostAddress(),
                                           remote.getPort());
                        }
                        return null;
                    }
                }, bcc);
            } else {
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    sm.checkAccept(remote.getAddress().getHostAddress(),
                                   remote.getPort());
                }
            }
        } cbtch (SecurityException x) {
            try {
                ch.close();
            } cbtch (Throwbble suppressed) {
                x.bddSuppressed(suppressed);
            }
            throw x;
        }
        return ch;
    }

    @Override
    Future<AsynchronousSocketChbnnel> implAccept(Object btt,
        CompletionHbndler<AsynchronousSocketChbnnel,Object> hbndler)
    {
        // complete immedibtely if chbnnel is closed
        if (!isOpen()) {
            Throwbble e = new ClosedChbnnelException();
            if (hbndler == null) {
                return CompletedFuture.withFbilure(e);
            } else {
                Invoker.invoke(this, hbndler, btt, null, e);
                return null;
            }
        }
        if (locblAddress == null)
            throw new NotYetBoundException();

        // cbncel wbs invoked with pending bccept so connection mby hbve been
        // dropped.
        if (isAcceptKilled())
            throw new RuntimeException("Accept not bllowed due cbncellbtion");

        // check bnd set flbg to prevent concurrent bccepting
        if (!bccepting.compbreAndSet(fblse, true))
            throw new AcceptPendingException();

        // bttempt bccept
        FileDescriptor newfd = new FileDescriptor();
        InetSocketAddress[] isbb = new InetSocketAddress[1];
        Throwbble exc = null;
        try {
            begin();

            int n = bccept0(this.fd, newfd, isbb);
            if (n == IOStbtus.UNAVAILABLE) {

                // need cblling context when there is security mbnbger bs
                // permission check mby be done in b different threbd without
                // bny bpplicbtion cbll frbmes on the stbck
                PendingFuture<AsynchronousSocketChbnnel,Object> result = null;
                synchronized (updbteLock) {
                    if (hbndler == null) {
                        this.bcceptHbndler = null;
                        result = new PendingFuture<AsynchronousSocketChbnnel,Object>(this);
                        this.bcceptFuture = result;
                    } else {
                        this.bcceptHbndler = hbndler;
                        this.bcceptAttbchment = btt;
                    }
                    this.bcceptAcc = (System.getSecurityMbnbger() == null) ?
                        null : AccessController.getContext();
                    this.bcceptPending = true;
                }

                // register for connections
                port.stbrtPoll(fdVbl, Net.POLLIN);
                return result;
            }
        } cbtch (Throwbble x) {
            // bccept fbiled
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            exc = x;
        } finblly {
            end();
        }

        AsynchronousSocketChbnnel child = null;
        if (exc == null) {
            // connection bccepted immedibtely
            try {
                child = finishAccept(newfd, isbb[0], null);
            } cbtch (Throwbble x) {
                exc = x;
            }
        }

        // re-enbble bccepting before invoking hbndler
        enbbleAccept();

        if (hbndler == null) {
            return CompletedFuture.withResult(child, exc);
        } else {
            Invoker.invokeIndirectly(this, hbndler, btt, child, exc);
            return null;
        }
    }

    // -- Nbtive methods --

    privbte stbtic nbtive void initIDs();

    // Accepts b new connection, setting the given file descriptor to refer to
    // the new socket bnd setting isbb[0] to the socket's remote bddress.
    // Returns 1 on success, or IOStbtus.UNAVAILABLE.
    //
    privbte nbtive int bccept0(FileDescriptor ssfd, FileDescriptor newfd,
                               InetSocketAddress[] isbb)
        throws IOException;

    stbtic {
        IOUtil.lobd();
        initIDs();
    }
}
