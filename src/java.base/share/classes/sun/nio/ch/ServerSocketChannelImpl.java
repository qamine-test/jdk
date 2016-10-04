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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.net.*;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;
import jbvb.util.*;
import sun.net.NetHooks;


/**
 * An implementbtion of ServerSocketChbnnels
 */

clbss ServerSocketChbnnelImpl
    extends ServerSocketChbnnel
    implements SelChImpl
{

    // Used to mbke nbtive close bnd configure cblls
    privbte stbtic NbtiveDispbtcher nd;

    // Our file descriptor
    privbte finbl FileDescriptor fd;

    // fd vblue needed for dev/poll. This vblue will rembin vblid
    // even bfter the vblue in the file descriptor object hbs been set to -1
    privbte int fdVbl;

    // ID of nbtive threbd currently blocked in this chbnnel, for signblling
    privbte volbtile long threbd = 0;

    // Lock held by threbd currently blocked in this chbnnel
    privbte finbl Object lock = new Object();

    // Lock held by bny threbd thbt modifies the stbte fields declbred below
    // DO NOT invoke b blocking I/O operbtion while holding this lock!
    privbte finbl Object stbteLock = new Object();

    // -- The following fields bre protected by stbteLock

    // Chbnnel stbte, increbses monotonicblly
    privbte stbtic finbl int ST_UNINITIALIZED = -1;
    privbte stbtic finbl int ST_INUSE = 0;
    privbte stbtic finbl int ST_KILLED = 1;
    privbte int stbte = ST_UNINITIALIZED;

    // Binding
    privbte InetSocketAddress locblAddress; // null => unbound

    // set true when exclusive binding is on bnd SO_REUSEADDR is emulbted
    privbte boolebn isReuseAddress;

    // Our socket bdbptor, if bny
    ServerSocket socket;

    // -- End of fields protected by stbteLock


    ServerSocketChbnnelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        this.fd =  Net.serverSocket(true);
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ST_INUSE;
    }

    ServerSocketChbnnelImpl(SelectorProvider sp,
                            FileDescriptor fd,
                            boolebn bound)
        throws IOException
    {
        super(sp);
        this.fd =  fd;
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ST_INUSE;
        if (bound)
            locblAddress = Net.locblAddress(fd);
    }

    public ServerSocket socket() {
        synchronized (stbteLock) {
            if (socket == null)
                socket = ServerSocketAdbptor.crebte(this);
            return socket;
        }
    }

    @Override
    public SocketAddress getLocblAddress() throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            return locblAddress == null ? locblAddress
                    : Net.getRevebledLocblAddress(
                          Net.bsInetSocketAddress(locblAddress));
        }
    }

    @Override
    public <T> ServerSocketChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException
    {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();

            if (nbme == StbndbrdSocketOptions.IP_TOS) {
                ProtocolFbmily fbmily = Net.isIPv6Avbilbble() ?
                    StbndbrdProtocolFbmily.INET6 : StbndbrdProtocolFbmily.INET;
                Net.setSocketOption(fd, fbmily, nbme, vblue);
                return this;
            }

            if (nbme == StbndbrdSocketOptions.SO_REUSEADDR &&
                    Net.useExclusiveBind())
            {
                // SO_REUSEADDR emulbted when using exclusive bind
                isReuseAddress = (Boolebn)vblue;
            } else {
                // no options thbt require specibl hbndling
                Net.setSocketOption(fd, Net.UNSPEC, nbme, vblue);
            }
            return this;
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T> T getOption(SocketOption<T> nbme)
        throws IOException
    {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (nbme == StbndbrdSocketOptions.SO_REUSEADDR &&
                    Net.useExclusiveBind())
            {
                // SO_REUSEADDR emulbted when using exclusive bind
                return (T)Boolebn.vblueOf(isReuseAddress);
            }
            // no options thbt require specibl hbndling
            return (T) Net.getSocketOption(fd, Net.UNSPEC, nbme);
        }
    }

    privbte stbtic clbss DefbultOptionsHolder {
        stbtic finbl Set<SocketOption<?>> defbultOptions = defbultOptions();

        privbte stbtic Set<SocketOption<?>> defbultOptions() {
            HbshSet<SocketOption<?>> set = new HbshSet<SocketOption<?>>(2);
            set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
            set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
            set.bdd(StbndbrdSocketOptions.IP_TOS);
            return Collections.unmodifibbleSet(set);
        }
    }

    @Override
    public finbl Set<SocketOption<?>> supportedOptions() {
        return DefbultOptionsHolder.defbultOptions;
    }

    public boolebn isBound() {
        synchronized (stbteLock) {
            return locblAddress != null;
        }
    }

    public InetSocketAddress locblAddress() {
        synchronized (stbteLock) {
            return locblAddress;
        }
    }

    @Override
    public ServerSocketChbnnel bind(SocketAddress locbl, int bbcklog) throws IOException {
        synchronized (lock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (isBound())
                throw new AlrebdyBoundException();
            InetSocketAddress isb = (locbl == null) ? new InetSocketAddress(0) :
                Net.checkAddress(locbl);
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkListen(isb.getPort());
            NetHooks.beforeTcpBind(fd, isb.getAddress(), isb.getPort());
            Net.bind(fd, isb.getAddress(), isb.getPort());
            Net.listen(fd, bbcklog < 1 ? 50 : bbcklog);
            synchronized (stbteLock) {
                locblAddress = Net.locblAddress(fd);
            }
        }
        return this;
    }

    public SocketChbnnel bccept() throws IOException {
        synchronized (lock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isBound())
                throw new NotYetBoundException();
            SocketChbnnel sc = null;

            int n = 0;
            FileDescriptor newfd = new FileDescriptor();
            InetSocketAddress[] isbb = new InetSocketAddress[1];

            try {
                begin();
                if (!isOpen())
                    return null;
                threbd = NbtiveThrebd.current();
                for (;;) {
                    n = bccept0(this.fd, newfd, isbb);
                    if ((n == IOStbtus.INTERRUPTED) && isOpen())
                        continue;
                    brebk;
                }
            } finblly {
                threbd = 0;
                end(n > 0);
                bssert IOStbtus.check(n);
            }

            if (n < 1)
                return null;

            IOUtil.configureBlocking(newfd, true);
            InetSocketAddress isb = isbb[0];
            sc = new SocketChbnnelImpl(provider(), newfd, isb);
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                try {
                    sm.checkAccept(isb.getAddress().getHostAddress(),
                                   isb.getPort());
                } cbtch (SecurityException x) {
                    sc.close();
                    throw x;
                }
            }
            return sc;

        }
    }

    protected void implConfigureBlocking(boolebn block) throws IOException {
        IOUtil.configureBlocking(fd, block);
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

    /**
     * Trbnslbtes nbtive poll revent set into b rebdy operbtion set
     */
    public boolebn trbnslbteRebdyOps(int ops, int initiblOps,
                                     SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps(); // Do this just once, it synchronizes
        int oldOps = sk.nioRebdyOps();
        int newOps = initiblOps;

        if ((ops & Net.POLLNVAL) != 0) {
            // This should only hbppen if this chbnnel is pre-closed while b
            // selection operbtion is in progress
            // ## Throw bn error if this chbnnel hbs not been pre-closed
            return fblse;
        }

        if ((ops & (Net.POLLERR | Net.POLLHUP)) != 0) {
            newOps = intOps;
            sk.nioRebdyOps(newOps);
            return (newOps & ~oldOps) != 0;
        }

        if (((ops & Net.POLLIN) != 0) &&
            ((intOps & SelectionKey.OP_ACCEPT) != 0))
                newOps |= SelectionKey.OP_ACCEPT;

        sk.nioRebdyOps(newOps);
        return (newOps & ~oldOps) != 0;
    }

    public boolebn trbnslbteAndUpdbteRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, sk.nioRebdyOps(), sk);
    }

    public boolebn trbnslbteAndSetRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, 0, sk);
    }

    // pbckbge-privbte
    int poll(int events, long timeout) throws IOException {
        bssert Threbd.holdsLock(blockingLock()) && !isBlocking();

        synchronized (lock) {
            int n = 0;
            try {
                begin();
                synchronized (stbteLock) {
                    if (!isOpen())
                        return 0;
                    threbd = NbtiveThrebd.current();
                }
                n = Net.poll(fd, events, timeout);
            } finblly {
                threbd = 0;
                end(n > 0);
            }
            return n;
        }
    }

    /**
     * Trbnslbtes bn interest operbtion set into b nbtive poll event set
     */
    public void trbnslbteAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        int newOps = 0;

        // Trbnslbte ops
        if ((ops & SelectionKey.OP_ACCEPT) != 0)
            newOps |= Net.POLLIN;
        // Plbce ops into pollfd brrby
        sk.selector.putEventOps(sk, newOps);
    }

    public FileDescriptor getFD() {
        return fd;
    }

    public int getFDVbl() {
        return fdVbl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(this.getClbss().getNbme());
        sb.bppend('[');
        if (!isOpen()) {
            sb.bppend("closed");
        } else {
            synchronized (stbteLock) {
                InetSocketAddress bddr = locblAddress();
                if (bddr == null) {
                    sb.bppend("unbound");
                } else {
                    sb.bppend(Net.getRevebledLocblAddressAsString(bddr));
                }
            }
        }
        sb.bppend(']');
        return sb.toString();
    }

    // -- Nbtive methods --

    // Accepts b new connection, setting the given file descriptor to refer to
    // the new socket bnd setting isbb[0] to the socket's remote bddress.
    // Returns 1 on success, or IOStbtus.UNAVAILABLE (if non-blocking bnd no
    // connections bre pending) or IOStbtus.INTERRUPTED.
    //
    privbte nbtive int bccept0(FileDescriptor ssfd, FileDescriptor newfd,
                               InetSocketAddress[] isbb)
        throws IOException;

    privbte stbtic nbtive void initIDs();

    stbtic {
        IOUtil.lobd();
        initIDs();
        nd = new SocketDispbtcher();
    }

}
