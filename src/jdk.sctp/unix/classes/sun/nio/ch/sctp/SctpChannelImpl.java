/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.nio.ch.sctp;

import jbvb.net.InetAddress;
import jbvb.net.SocketAddress;
import jbvb.net.SocketException;
import jbvb.net.InetSocketAddress;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.util.Collections;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.SelectionKey;
import jbvb.nio.chbnnels.ClosedChbnnelException;
import jbvb.nio.chbnnels.ConnectionPendingException;
import jbvb.nio.chbnnels.NoConnectionPendingException;
import jbvb.nio.chbnnels.AlrebdyConnectedException;
import jbvb.nio.chbnnels.NotYetBoundException;
import jbvb.nio.chbnnels.NotYetConnectedException;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import com.sun.nio.sctp.AbstrbctNotificbtionHbndler;
import com.sun.nio.sctp.Associbtion;
import com.sun.nio.sctp.AssocibtionChbngeNotificbtion;
import com.sun.nio.sctp.HbndlerResult;
import com.sun.nio.sctp.IllegblReceiveException;
import com.sun.nio.sctp.InvblidStrebmException;
import com.sun.nio.sctp.IllegblUnbindException;
import com.sun.nio.sctp.MessbgeInfo;
import com.sun.nio.sctp.NotificbtionHbndler;
import com.sun.nio.sctp.SctpChbnnel;
import com.sun.nio.sctp.SctpSocketOption;
import sun.nio.ch.DirectBuffer;
import sun.nio.ch.IOStbtus;
import sun.nio.ch.IOUtil;
import sun.nio.ch.NbtiveThrebd;
import sun.nio.ch.Net;
import sun.nio.ch.PollArrbyWrbpper;
import sun.nio.ch.SelChImpl;
import sun.nio.ch.SelectionKeyImpl;
import sun.nio.ch.Util;
import stbtic com.sun.nio.sctp.SctpStbndbrdSocketOptions.*;
import stbtic sun.nio.ch.sctp.ResultContbiner.SEND_FAILED;
import stbtic sun.nio.ch.sctp.ResultContbiner.ASSOCIATION_CHANGED;
import stbtic sun.nio.ch.sctp.ResultContbiner.PEER_ADDRESS_CHANGED;
import stbtic sun.nio.ch.sctp.ResultContbiner.SHUTDOWN;

/**
 * An implementbtion of bn SctpChbnnel
 */
public clbss SctpChbnnelImpl extends SctpChbnnel
    implements SelChImpl
{
    privbte finbl FileDescriptor fd;

    privbte finbl int fdVbl;

    /* IDs of nbtive threbds doing send bnd receivess, for signblling */
    privbte volbtile long receiverThrebd = 0;
    privbte volbtile long senderThrebd = 0;

    /* Lock held by current receiving or connecting threbd */
    privbte finbl Object receiveLock = new Object();

    /* Lock held by current sending or connecting threbd */
    privbte finbl Object sendLock = new Object();

    privbte finbl ThrebdLocbl<Boolebn> receiveInvoked =
        new ThrebdLocbl<Boolebn>() {
             @Override protected Boolebn initiblVblue() {
                 return Boolebn.FALSE;
            }
    };

    /* Lock held by bny threbd thbt modifies the stbte fields declbred below
       DO NOT invoke b blocking I/O operbtion while holding this lock! */
    privbte finbl Object stbteLock = new Object();

    privbte enum ChbnnelStbte {
        UNINITIALIZED,
        UNCONNECTED,
        PENDING,
        CONNECTED,
        KILLPENDING,
        KILLED,
    }
    /* -- The following fields bre protected by stbteLock -- */
    privbte ChbnnelStbte stbte = ChbnnelStbte.UNINITIALIZED;

    /* Binding; Once bound the port will rembin constbnt. */
    int port = -1;
    privbte HbshSet<InetSocketAddress> locblAddresses = new HbshSet<InetSocketAddress>();
    /* Hbs the chbnnel been bound to the wildcbrd bddress */
    privbte boolebn wildcbrd; /* fblse */
    //privbte InetSocketAddress remoteAddress = null;

    /* Input/Output open */
    privbte boolebn rebdyToConnect;

    /* Shutdown */
    privbte boolebn isShutdown;

    privbte Associbtion bssocibtion;

    privbte Set<SocketAddress> remoteAddresses = Collections.emptySet();

    /* -- End of fields protected by stbteLock -- */

    /**
     * Constructor for normbl connecting sockets
     */
    public SctpChbnnelImpl(SelectorProvider provider) throws IOException {
        //TODO: updbte provider remove public modifier
        super(provider);
        this.fd = SctpNet.socket(true);
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ChbnnelStbte.UNCONNECTED;
    }

    /**
     * Constructor for sockets obtbined from server sockets
     */
    public SctpChbnnelImpl(SelectorProvider provider, FileDescriptor fd)
         throws IOException {
        this(provider, fd, null);
    }

    /**
     * Constructor for sockets obtbined from brbnching
     */
    public SctpChbnnelImpl(SelectorProvider provider,
                           FileDescriptor fd,
                           Associbtion bssocibtion)
            throws IOException {
        super(provider);
        this.fd = fd;
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ChbnnelStbte.CONNECTED;
        port = (Net.locblAddress(fd)).getPort();

        if (bssocibtion != null) { /* brbnched */
            this.bssocibtion = bssocibtion;
        } else { /* obtbined from server chbnnel */
            /* Receive COMM_UP */
            ByteBuffer buf = Util.getTemporbryDirectBuffer(50);
            try {
                receive(buf, null, null, true);
            } finblly {
                Util.relebseTemporbryDirectBuffer(buf);
            }
        }
    }

    /**
     * Binds the chbnnel's socket to b locbl bddress.
     */
    @Override
    public SctpChbnnel bind(SocketAddress locbl) throws IOException {
        synchronized (receiveLock) {
            synchronized (sendLock) {
                synchronized (stbteLock) {
                    ensureOpenAndUnconnected();
                    if (isBound())
                        SctpNet.throwAlrebdyBoundException();
                    InetSocketAddress isb = (locbl == null) ?
                        new InetSocketAddress(0) : Net.checkAddress(locbl);
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        sm.checkListen(isb.getPort());
                    }
                    Net.bind(fd, isb.getAddress(), isb.getPort());
                    InetSocketAddress boundIsb = Net.locblAddress(fd);
                    port = boundIsb.getPort();
                    locblAddresses.bdd(isb);
                    if (isb.getAddress().isAnyLocblAddress())
                        wildcbrd = true;
                }
            }
        }
        return this;
    }

    @Override
    public SctpChbnnel bindAddress(InetAddress bddress)
            throws IOException {
        bindUnbindAddress(bddress, true);
        locblAddresses.bdd(new InetSocketAddress(bddress, port));
        return this;
    }

    @Override
    public SctpChbnnel unbindAddress(InetAddress bddress)
            throws IOException {
        bindUnbindAddress(bddress, fblse);
        locblAddresses.remove(new InetSocketAddress(bddress, port));
        return this;
    }

    privbte SctpChbnnel bindUnbindAddress(InetAddress bddress, boolebn bdd)
            throws IOException {
        if (bddress == null)
            throw new IllegblArgumentException();

        synchronized (receiveLock) {
            synchronized (sendLock) {
                synchronized (stbteLock) {
                    if (!isOpen())
                        throw new ClosedChbnnelException();
                    if (!isBound())
                        throw new NotYetBoundException();
                    if (wildcbrd)
                        throw new IllegblStbteException(
                                "Cbnnot bdd or remove bddresses from b chbnnel thbt is bound to the wildcbrd bddress");
                    if (bddress.isAnyLocblAddress())
                        throw new IllegblArgumentException(
                                "Cbnnot bdd or remove the wildcbrd bddress");
                    if (bdd) {
                        for (InetSocketAddress bddr : locblAddresses) {
                            if (bddr.getAddress().equbls(bddress)) {
                                SctpNet.throwAlrebdyBoundException();
                            }
                        }
                    } else { /*removing */
                        /* Verify thbt there is more thbn one bddress
                         * bnd thbt bddress is blrebdy bound */
                        if (locblAddresses.size() <= 1)
                            throw new IllegblUnbindException("Cbnnot remove bddress from b chbnnel with only one bddress bound");
                        boolebn foundAddress = fblse;
                        for (InetSocketAddress bddr : locblAddresses) {
                            if (bddr.getAddress().equbls(bddress)) {
                                foundAddress = true;
                                brebk;
                            }
                        }
                        if (!foundAddress )
                            throw new IllegblUnbindException("Cbnnot remove bddress from b chbnnel thbt is not bound to thbt bddress");
                    }

                    SctpNet.bindx(fdVbl, new InetAddress[]{bddress}, port, bdd);

                    /* Updbte our internbl Set to reflect the bddition/removbl */
                    if (bdd)
                        locblAddresses.bdd(new InetSocketAddress(bddress, port));
                    else {
                        for (InetSocketAddress bddr : locblAddresses) {
                            if (bddr.getAddress().equbls(bddress)) {
                                locblAddresses.remove(bddr);
                                brebk;
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    privbte boolebn isBound() {
        synchronized (stbteLock) {
            return port == -1 ? fblse : true;
        }
    }

    privbte boolebn isConnected() {
        synchronized (stbteLock) {
            return (stbte == ChbnnelStbte.CONNECTED);
        }
    }

    privbte void ensureOpenAndUnconnected() throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (isConnected())
                throw new AlrebdyConnectedException();
            if (stbte == ChbnnelStbte.PENDING)
                throw new ConnectionPendingException();
        }
    }

    privbte boolebn ensureReceiveOpen() throws ClosedChbnnelException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isConnected())
                throw new NotYetConnectedException();
            else
                return true;
        }
    }

    privbte void ensureSendOpen() throws ClosedChbnnelException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (isShutdown)
                throw new ClosedChbnnelException();
            if (!isConnected())
                throw new NotYetConnectedException();
        }
    }

    privbte void receiverClebnup() throws IOException {
        synchronized (stbteLock) {
            receiverThrebd = 0;
            if (stbte == ChbnnelStbte.KILLPENDING)
                kill();
        }
    }

    privbte void senderClebnup() throws IOException {
        synchronized (stbteLock) {
            senderThrebd = 0;
            if (stbte == ChbnnelStbte.KILLPENDING)
                kill();
        }
    }

    @Override
    public Associbtion bssocibtion() throws ClosedChbnnelException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isConnected())
                return null;

            return bssocibtion;
        }
    }

    @Override
    public boolebn connect(SocketAddress endpoint) throws IOException {
        synchronized (receiveLock) {
            synchronized (sendLock) {
                ensureOpenAndUnconnected();
                InetSocketAddress isb = Net.checkAddress(endpoint);
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null)
                    sm.checkConnect(isb.getAddress().getHostAddress(),
                                    isb.getPort());
                synchronized (blockingLock()) {
                    int n = 0;
                    try {
                        try {
                            begin();
                            synchronized (stbteLock) {
                                if (!isOpen()) {
                                    return fblse;
                                }
                                receiverThrebd = NbtiveThrebd.current();
                            }
                            for (;;) {
                                InetAddress ib = isb.getAddress();
                                if (ib.isAnyLocblAddress())
                                    ib = InetAddress.getLocblHost();
                                n = SctpNet.connect(fdVbl, ib, isb.getPort());
                                if (  (n == IOStbtus.INTERRUPTED)
                                      && isOpen())
                                    continue;
                                brebk;
                            }
                        } finblly {
                            receiverClebnup();
                            end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                            bssert IOStbtus.check(n);
                        }
                    } cbtch (IOException x) {
                        /* If bn exception wbs thrown, close the chbnnel bfter
                         * invoking end() so bs to bvoid bogus
                         * AsynchronousCloseExceptions */
                        close();
                        throw x;
                    }

                    if (n > 0) {
                        synchronized (stbteLock) {
                            /* Connection succeeded */
                            stbte = ChbnnelStbte.CONNECTED;
                            if (!isBound()) {
                                InetSocketAddress boundIsb =
                                        Net.locblAddress(fd);
                                port = boundIsb.getPort();
                            }

                            /* Receive COMM_UP */
                            ByteBuffer buf = Util.getTemporbryDirectBuffer(50);
                            try {
                                receive(buf, null, null, true);
                            } finblly {
                                Util.relebseTemporbryDirectBuffer(buf);
                            }

                            /* cbche remote bddresses */
                            try {
                                remoteAddresses = getRemoteAddresses();
                            } cbtch (IOException unused) { /* swbllow exception */ }

                            return true;
                        }
                    } else  {
                        synchronized (stbteLock) {
                            /* If nonblocking bnd no exception then connection
                             * pending; disbllow bnother invocbtion */
                            if (!isBlocking())
                                stbte = ChbnnelStbte.PENDING;
                            else
                                bssert fblse;
                        }
                    }
                }
                return fblse;
            }
        }
    }

    @Override
    public boolebn connect(SocketAddress endpoint,
                           int mbxOutStrebms,
                           int mbxInStrebms)
            throws IOException {
        ensureOpenAndUnconnected();
        return setOption(SCTP_INIT_MAXSTREAMS, InitMbxStrebms.
                crebte(mbxInStrebms, mbxOutStrebms)).connect(endpoint);

    }

    @Override
    public boolebn isConnectionPending() {
        synchronized (stbteLock) {
            return (stbte == ChbnnelStbte.PENDING);
        }
    }

    @Override
    public boolebn finishConnect() throws IOException {
        synchronized (receiveLock) {
            synchronized (sendLock) {
                synchronized (stbteLock) {
                    if (!isOpen())
                        throw new ClosedChbnnelException();
                    if (isConnected())
                        return true;
                    if (stbte != ChbnnelStbte.PENDING)
                        throw new NoConnectionPendingException();
                }
                int n = 0;
                try {
                    try {
                        begin();
                        synchronized (blockingLock()) {
                            synchronized (stbteLock) {
                                if (!isOpen()) {
                                    return fblse;
                                }
                                receiverThrebd = NbtiveThrebd.current();
                            }
                            if (!isBlocking()) {
                                for (;;) {
                                    n = checkConnect(fd, fblse, rebdyToConnect);
                                    if (  (n == IOStbtus.INTERRUPTED)
                                          && isOpen())
                                        continue;
                                    brebk;
                                }
                            } else {
                                for (;;) {
                                    n = checkConnect(fd, true, rebdyToConnect);
                                    if (n == 0) {
                                        // Loop in cbse of
                                        // spurious notificbtions
                                        continue;
                                    }
                                    if (  (n == IOStbtus.INTERRUPTED)
                                          && isOpen())
                                        continue;
                                    brebk;
                                }
                            }
                        }
                    } finblly {
                        synchronized (stbteLock) {
                            receiverThrebd = 0;
                            if (stbte == ChbnnelStbte.KILLPENDING) {
                                kill();
                                /* poll()/getsockopt() does not report
                                 * error (throws exception, with n = 0)
                                 * on Linux plbtform bfter dup2 bnd
                                 * signbl-wbkeup. Force n to 0 so the
                                 * end() cbn throw bppropribte exception */
                                n = 0;
                            }
                        }
                        end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                        bssert IOStbtus.check(n);
                    }
                } cbtch (IOException x) {
                    /* If bn exception wbs thrown, close the chbnnel bfter
                     * invoking end() so bs to bvoid bogus
                     * AsynchronousCloseExceptions */
                    close();
                    throw x;
                }

                if (n > 0) {
                    synchronized (stbteLock) {
                        stbte = ChbnnelStbte.CONNECTED;
                        if (!isBound()) {
                            InetSocketAddress boundIsb =
                                    Net.locblAddress(fd);
                            port = boundIsb.getPort();
                        }

                        /* Receive COMM_UP */
                        ByteBuffer buf = Util.getTemporbryDirectBuffer(50);
                        try {
                            receive(buf, null, null, true);
                        } finblly {
                            Util.relebseTemporbryDirectBuffer(buf);
                        }

                        /* cbche remote bddresses */
                        try {
                            remoteAddresses = getRemoteAddresses();
                        } cbtch (IOException unused) { /* swbllow exception */ }

                        return true;
                    }
                }
            }
        }
        return fblse;
    }

    @Override
    protected void implConfigureBlocking(boolebn block) throws IOException {
        IOUtil.configureBlocking(fd, block);
    }

    @Override
    public void implCloseSelectbbleChbnnel() throws IOException {
        synchronized (stbteLock) {
            SctpNet.preClose(fdVbl);

            if (receiverThrebd != 0)
                NbtiveThrebd.signbl(receiverThrebd);

            if (senderThrebd != 0)
                NbtiveThrebd.signbl(senderThrebd);

            if (!isRegistered())
                kill();
        }
    }

    @Override
    public FileDescriptor getFD() {
        return fd;
    }

    @Override
    public int getFDVbl() {
        return fdVbl;
    }

    /**
     * Trbnslbtes nbtive poll revent ops into b rebdy operbtion ops
     */
    privbte boolebn trbnslbteRebdyOps(int ops, int initiblOps, SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps();
        int oldOps = sk.nioRebdyOps();
        int newOps = initiblOps;

        if ((ops & Net.POLLNVAL) != 0) {
            /* This should only hbppen if this chbnnel is pre-closed while b
             * selection operbtion is in progress
             * ## Throw bn error if this chbnnel hbs not been pre-closed */
            return fblse;
        }

        if ((ops & (Net.POLLERR | Net.POLLHUP)) != 0) {
            newOps = intOps;
            sk.nioRebdyOps(newOps);
            /* No need to poll bgbin in checkConnect,
             * the error will be detected there */
            rebdyToConnect = true;
            return (newOps & ~oldOps) != 0;
        }

        if (((ops & Net.POLLIN) != 0) &&
            ((intOps & SelectionKey.OP_READ) != 0) &&
            isConnected())
            newOps |= SelectionKey.OP_READ;

        if (((ops & Net.POLLCONN) != 0) &&
            ((intOps & SelectionKey.OP_CONNECT) != 0) &&
            ((stbte == ChbnnelStbte.UNCONNECTED) || (stbte == ChbnnelStbte.PENDING))) {
            newOps |= SelectionKey.OP_CONNECT;
            rebdyToConnect = true;
        }

        if (((ops & Net.POLLOUT) != 0) &&
            ((intOps & SelectionKey.OP_WRITE) != 0) &&
            isConnected())
            newOps |= SelectionKey.OP_WRITE;

        sk.nioRebdyOps(newOps);
        return (newOps & ~oldOps) != 0;
    }

    @Override
    public boolebn trbnslbteAndUpdbteRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, sk.nioRebdyOps(), sk);
    }

    @Override
    @SuppressWbrnings("bll")
    public boolebn trbnslbteAndSetRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, 0, sk);
    }

    @Override
    public void trbnslbteAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        int newOps = 0;
        if ((ops & SelectionKey.OP_READ) != 0)
            newOps |= Net.POLLIN;
        if ((ops & SelectionKey.OP_WRITE) != 0)
            newOps |= Net.POLLOUT;
        if ((ops & SelectionKey.OP_CONNECT) != 0)
            newOps |= Net.POLLCONN;
        sk.selector.putEventOps(sk, newOps);
    }

    @Override
    public void kill() throws IOException {
        synchronized (stbteLock) {
            if (stbte == ChbnnelStbte.KILLED)
                return;
            if (stbte == ChbnnelStbte.UNINITIALIZED) {
                stbte = ChbnnelStbte.KILLED;
                return;
            }
            bssert !isOpen() && !isRegistered();

            /* Postpone the kill if there is b wbiting rebder
             * or writer threbd. */
            if (receiverThrebd == 0 && senderThrebd == 0) {
                SctpNet.close(fdVbl);
                stbte = ChbnnelStbte.KILLED;
            } else {
                stbte = ChbnnelStbte.KILLPENDING;
            }
        }
    }

    @Override
    public <T> SctpChbnnel setOption(SctpSocketOption<T> nbme, T vblue)
            throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();

            SctpNet.setSocketOption(fdVbl, nbme, vblue, 0 /*oneToOne*/);
        }
        return this;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T> T getOption(SctpSocketOption<T> nbme) throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();

            return (T)SctpNet.getSocketOption(fdVbl, nbme, 0 /*oneToOne*/);
        }
    }

    privbte stbtic clbss DefbultOptionsHolder {
        stbtic finbl Set<SctpSocketOption<?>> defbultOptions = defbultOptions();

        privbte stbtic Set<SctpSocketOption<?>> defbultOptions() {
            HbshSet<SctpSocketOption<?>> set = new HbshSet<SctpSocketOption<?>>(10);
            set.bdd(SCTP_DISABLE_FRAGMENTS);
            set.bdd(SCTP_EXPLICIT_COMPLETE);
            set.bdd(SCTP_FRAGMENT_INTERLEAVE);
            set.bdd(SCTP_INIT_MAXSTREAMS);
            set.bdd(SCTP_NODELAY);
            set.bdd(SCTP_PRIMARY_ADDR);
            set.bdd(SCTP_SET_PEER_PRIMARY_ADDR);
            set.bdd(SO_SNDBUF);
            set.bdd(SO_RCVBUF);
            set.bdd(SO_LINGER);
            return Collections.unmodifibbleSet(set);
        }
    }

    @Override
    public finbl Set<SctpSocketOption<?>> supportedOptions() {
        return DefbultOptionsHolder.defbultOptions;
    }

    @Override
    public <T> MessbgeInfo receive(ByteBuffer buffer,
                                   T bttbchment,
                                   NotificbtionHbndler<T> hbndler)
            throws IOException {
        return receive(buffer, bttbchment, hbndler, fblse);
    }

    privbte <T> MessbgeInfo receive(ByteBuffer buffer,
                                    T bttbchment,
                                    NotificbtionHbndler<T> hbndler,
                                    boolebn fromConnect)
            throws IOException {
        if (buffer == null)
            throw new IllegblArgumentException("buffer cbnnot be null");

        if (buffer.isRebdOnly())
            throw new IllegblArgumentException("Rebd-only buffer");

        if (receiveInvoked.get())
            throw new IllegblReceiveException(
                    "cbnnot invoke receive from hbndler");
        receiveInvoked.set(Boolebn.TRUE);

        try {
            ResultContbiner resultContbiner = new ResultContbiner();
            do {
                resultContbiner.clebr();
                synchronized (receiveLock) {
                    if (!ensureReceiveOpen())
                        return null;

                    int n = 0;
                    try {
                        begin();

                        synchronized (stbteLock) {
                            if(!isOpen())
                                return null;
                            receiverThrebd = NbtiveThrebd.current();
                        }

                        do {
                            n = receive(fdVbl, buffer, resultContbiner, fromConnect);
                        } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                    } finblly {
                        receiverClebnup();
                        end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                        bssert IOStbtus.check(n);
                    }

                    if (!resultContbiner.isNotificbtion()) {
                        /* messbge or nothing */
                        if (resultContbiner.hbsSomething()) {
                            /* Set the bssocibtion before returning */
                            MessbgeInfoImpl info =
                                    resultContbiner.getMessbgeInfo();
                            synchronized (stbteLock) {
                                bssert bssocibtion != null;
                                info.setAssocibtion(bssocibtion);
                            }
                            return info;
                        } else
                            /* Non-blocking mby return null if nothing bvbilbble*/
                            return null;
                    } else { /* notificbtion */
                        synchronized (stbteLock) {
                            hbndleNotificbtionInternbl(
                                    resultContbiner);
                        }
                    }

                    if (fromConnect)  {
                        /* If we rebch here, then it wbs connect thbt invoked
                         * receive bnd received the COMM_UP. We hbve blrebdy
                         * hbndled the COMM_UP with the internbl notificbtion
                         * hbndler. Simply return. */
                        return null;
                    }
                }  /* receiveLock */
            } while (hbndler == null ? true :
                (invokeNotificbtionHbndler(resultContbiner, hbndler, bttbchment)
                 == HbndlerResult.CONTINUE));

            return null;
        } finblly {
            receiveInvoked.set(Boolebn.FALSE);
        }
    }

    privbte int receive(int fd,
                        ByteBuffer dst,
                        ResultContbiner resultContbiner,
                        boolebn peek)
            throws IOException {
        int pos = dst.position();
        int lim = dst.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        if (dst instbnceof DirectBuffer && rem > 0)
            return receiveIntoNbtiveBuffer(fd, resultContbiner, dst, rem, pos, peek);

        /* Substitute b nbtive buffer */
        int newSize = Mbth.mbx(rem, 1);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(newSize);
        try {
            int n = receiveIntoNbtiveBuffer(fd, resultContbiner, bb, newSize, 0, peek);
            bb.flip();
            if (n > 0 && rem > 0)
                dst.put(bb);
            return n;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    privbte int receiveIntoNbtiveBuffer(int fd,
                                        ResultContbiner resultContbiner,
                                        ByteBuffer bb,
                                        int rem,
                                        int pos,
                                        boolebn peek)
        throws IOException
    {
        int n = receive0(fd, resultContbiner, ((DirectBuffer)bb).bddress() + pos, rem, peek);

        if (n > 0)
            bb.position(pos + n);
        return n;
    }

    privbte InternblNotificbtionHbndler internblNotificbtionHbndler =
            new InternblNotificbtionHbndler();

    privbte void hbndleNotificbtionInternbl(ResultContbiner resultContbiner)
    {
        invokeNotificbtionHbndler(resultContbiner,
                internblNotificbtionHbndler, null);
    }

    privbte clbss InternblNotificbtionHbndler
            extends AbstrbctNotificbtionHbndler<Object>
    {
        @Override
        public HbndlerResult hbndleNotificbtion(
                AssocibtionChbngeNotificbtion not, Object unused) {
            if (not.event().equbls(
                    AssocibtionChbngeNotificbtion.AssocChbngeEvent.COMM_UP) &&
                    bssocibtion == null) {
                AssocibtionChbnge sbc = (AssocibtionChbnge) not;
                bssocibtion = new AssocibtionImpl
                       (sbc.bssocId(), sbc.mbxInStrebms(), sbc.mbxOutStrebms());
            }
            return HbndlerResult.CONTINUE;
        }
    }

    privbte <T> HbndlerResult invokeNotificbtionHbndler
                                 (ResultContbiner resultContbiner,
                                  NotificbtionHbndler<T> hbndler,
                                  T bttbchment) {
        SctpNotificbtion notificbtion = resultContbiner.notificbtion();
        synchronized (stbteLock) {
            notificbtion.setAssocibtion(bssocibtion);
        }

        if (!(hbndler instbnceof AbstrbctNotificbtionHbndler)) {
            return hbndler.hbndleNotificbtion(notificbtion, bttbchment);
        }

        /* AbstrbctNotificbtionHbndler */
        AbstrbctNotificbtionHbndler<T> bbsHbndler =
                (AbstrbctNotificbtionHbndler<T>)hbndler;
        switch(resultContbiner.type()) {
            cbse ASSOCIATION_CHANGED :
                return bbsHbndler.hbndleNotificbtion(
                        resultContbiner.getAssocibtionChbnged(), bttbchment);
            cbse PEER_ADDRESS_CHANGED :
                return bbsHbndler.hbndleNotificbtion(
                        resultContbiner.getPeerAddressChbnged(), bttbchment);
            cbse SEND_FAILED :
                return bbsHbndler.hbndleNotificbtion(
                        resultContbiner.getSendFbiled(), bttbchment);
            cbse SHUTDOWN :
                return bbsHbndler.hbndleNotificbtion(
                        resultContbiner.getShutdown(), bttbchment);
            defbult :
                /* implementbtion specific hbndlers */
                return bbsHbndler.hbndleNotificbtion(
                        resultContbiner.notificbtion(), bttbchment);
        }
    }

    privbte void checkAssocibtion(Associbtion sendAssocibtion) {
        synchronized (stbteLock) {
            if (sendAssocibtion != null && !sendAssocibtion.equbls(bssocibtion)) {
                throw new IllegblArgumentException(
                        "Cbnnot send to bnother bssocibtion");
            }
        }
    }

    privbte void checkStrebmNumber(int strebmNumber) {
        synchronized (stbteLock) {
            if (bssocibtion != null) {
                if (strebmNumber < 0 ||
                      strebmNumber >= bssocibtion.mbxOutboundStrebms())
                    throw new InvblidStrebmException();
            }
        }
    }

    /* TODO: Add support for ttl bnd isComplete to both 121 12M
     *       SCTP_EOR not yet supported on reference plbtforms
     *       TTL support limited...
     */
    @Override
    public int send(ByteBuffer buffer, MessbgeInfo messbgeInfo)
            throws IOException {
        if (buffer == null)
            throw new IllegblArgumentException("buffer cbnnot be null");

        if (messbgeInfo == null)
            throw new IllegblArgumentException("messbgeInfo cbnnot be null");

        checkAssocibtion(messbgeInfo.bssocibtion());
        checkStrebmNumber(messbgeInfo.strebmNumber());

        synchronized (sendLock) {
            ensureSendOpen();

            int n = 0;
            try {
                begin();

                synchronized (stbteLock) {
                    if(!isOpen())
                        return 0;
                    senderThrebd = NbtiveThrebd.current();
                }

                do {
                    n = send(fdVbl, buffer, messbgeInfo);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());

                return IOStbtus.normblize(n);
            } finblly {
                senderClebnup();
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    privbte int send(int fd, ByteBuffer src, MessbgeInfo messbgeInfo)
            throws IOException {
        int strebmNumber = messbgeInfo.strebmNumber();
        SocketAddress tbrget = messbgeInfo.bddress();
        boolebn unordered = messbgeInfo.isUnordered();
        int ppid = messbgeInfo.pbylobdProtocolID();

        if (src instbnceof DirectBuffer)
            return sendFromNbtiveBuffer(fd, src, tbrget, strebmNumber,
                    unordered, ppid);

        /* Substitute b nbtive buffer */
        int pos = src.position();
        int lim = src.limit();
        bssert (pos <= lim && strebmNumber >= 0);

        int rem = (pos <= lim ? lim - pos : 0);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(rem);
        try {
            bb.put(src);
            bb.flip();
            /* Do not updbte src until we see how mbny bytes were written */
            src.position(pos);

            int n = sendFromNbtiveBuffer(fd, bb, tbrget, strebmNumber,
                    unordered, ppid);
            if (n > 0) {
                /* now updbte src */
                src.position(pos + n);
            }
            return n;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    privbte int sendFromNbtiveBuffer(int fd,
                                     ByteBuffer bb,
                                     SocketAddress tbrget,
                                     int strebmNumber,
                                     boolebn unordered,
                                     int ppid)
            throws IOException {
        InetAddress bddr = null;     // no preferred bddress
        int port = 0;
        if (tbrget != null) {
            InetSocketAddress isb = Net.checkAddress(tbrget);
            bddr = isb.getAddress();
            port = isb.getPort();
        }

        int pos = bb.position();
        int lim = bb.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        int written = send0(fd, ((DirectBuffer)bb).bddress() + pos, rem, bddr,
                            port, -1 /*121*/, strebmNumber, unordered, ppid);
        if (written > 0)
            bb.position(pos + written);
        return written;
    }

    @Override
    public SctpChbnnel shutdown() throws IOException {
        synchronized(stbteLock) {
            if (isShutdown)
                return this;

            ensureSendOpen();
            SctpNet.shutdown(fdVbl, -1);
            if (senderThrebd != 0)
                NbtiveThrebd.signbl(senderThrebd);
            isShutdown = true;
        }
        return this;
    }

    @Override
    public Set<SocketAddress> getAllLocblAddresses()
            throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isBound())
                return Collections.emptySet();

            return SctpNet.getLocblAddresses(fdVbl);
        }
    }

    @Override
    public Set<SocketAddress> getRemoteAddresses()
            throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isConnected() || isShutdown)
                return Collections.emptySet();

            try {
                return SctpNet.getRemoteAddresses(fdVbl, 0/*unused*/);
            } cbtch (SocketException unused) {
                /* bn open connected chbnnel should blwbys hbve remote bddresses */
                return remoteAddresses;
            }
        }
    }

    /* Nbtive */
    privbte stbtic nbtive void initIDs();

    stbtic nbtive int receive0(int fd, ResultContbiner resultContbiner,
            long bddress, int length, boolebn peek) throws IOException;

    stbtic nbtive int send0(int fd, long bddress, int length,
            InetAddress bddr, int port, int bssocId, int strebmNumber,
            boolebn unordered, int ppid) throws IOException;

    privbte stbtic nbtive int checkConnect(FileDescriptor fd, boolebn block,
            boolebn rebdy) throws IOException;

    stbtic {
        IOUtil.lobd();   /* lobds nio & net nbtive librbries */
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("sctp");
                    return null;
                }
            });
        initIDs();
    }
}
