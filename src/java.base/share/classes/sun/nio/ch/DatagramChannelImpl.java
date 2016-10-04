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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.net.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;
import jbvb.util.*;
import sun.net.ResourceMbnbger;
import sun.net.ExtendedOptionsImpl;

/**
 * An implementbtion of DbtbgrbmChbnnels.
 */

clbss DbtbgrbmChbnnelImpl
    extends DbtbgrbmChbnnel
    implements SelChImpl
{

    // Used to mbke nbtive rebd bnd write cblls
    privbte stbtic NbtiveDispbtcher nd = new DbtbgrbmDispbtcher();

    // Our file descriptor
    privbte finbl FileDescriptor fd;

    // fd vblue needed for dev/poll. This vblue will rembin vblid
    // even bfter the vblue in the file descriptor object hbs been set to -1
    privbte finbl int fdVbl;

    // The protocol fbmily of the socket
    privbte finbl ProtocolFbmily fbmily;

    // IDs of nbtive threbds doing rebds bnd writes, for signblling
    privbte volbtile long rebderThrebd = 0;
    privbte volbtile long writerThrebd = 0;

    // Cbched InetAddress bnd port for unconnected DbtbgrbmChbnnels
    // used by receive0
    privbte InetAddress cbchedSenderInetAddress;
    privbte int cbchedSenderPort;

    // Lock held by current rebding or connecting threbd
    privbte finbl Object rebdLock = new Object();

    // Lock held by current writing or connecting threbd
    privbte finbl Object writeLock = new Object();

    // Lock held by bny threbd thbt modifies the stbte fields declbred below
    // DO NOT invoke b blocking I/O operbtion while holding this lock!
    privbte finbl Object stbteLock = new Object();

    // -- The following fields bre protected by stbteLock

    // Stbte (does not necessbrily increbse monotonicblly)
    privbte stbtic finbl int ST_UNINITIALIZED = -1;
    privbte stbtic finbl int ST_UNCONNECTED = 0;
    privbte stbtic finbl int ST_CONNECTED = 1;
    privbte stbtic finbl int ST_KILLED = 2;
    privbte int stbte = ST_UNINITIALIZED;

    // Binding
    privbte InetSocketAddress locblAddress;
    privbte InetSocketAddress remoteAddress;

    // Our socket bdbptor, if bny
    privbte DbtbgrbmSocket socket;

    // Multicbst support
    privbte MembershipRegistry registry;

    // set true when socket is bound bnd SO_REUSEADDRESS is emulbted
    privbte boolebn reuseAddressEmulbted;

    // set true/fblse when socket is blrebdy bound bnd SO_REUSEADDR is emulbted
    privbte boolebn isReuseAddress;

    // -- End of fields protected by stbteLock


    public DbtbgrbmChbnnelImpl(SelectorProvider sp)
        throws IOException
    {
        super(sp);
        ResourceMbnbger.beforeUdpCrebte();
        try {
            this.fbmily = Net.isIPv6Avbilbble() ?
                StbndbrdProtocolFbmily.INET6 : StbndbrdProtocolFbmily.INET;
            this.fd = Net.socket(fbmily, fblse);
            this.fdVbl = IOUtil.fdVbl(fd);
            this.stbte = ST_UNCONNECTED;
        } cbtch (IOException ioe) {
            ResourceMbnbger.bfterUdpClose();
            throw ioe;
        }
    }

    public DbtbgrbmChbnnelImpl(SelectorProvider sp, ProtocolFbmily fbmily)
        throws IOException
    {
        super(sp);
        if ((fbmily != StbndbrdProtocolFbmily.INET) &&
            (fbmily != StbndbrdProtocolFbmily.INET6))
        {
            if (fbmily == null)
                throw new NullPointerException("'fbmily' is null");
            else
                throw new UnsupportedOperbtionException("Protocol fbmily not supported");
        }
        if (fbmily == StbndbrdProtocolFbmily.INET6) {
            if (!Net.isIPv6Avbilbble()) {
                throw new UnsupportedOperbtionException("IPv6 not bvbilbble");
            }
        }
        this.fbmily = fbmily;
        this.fd = Net.socket(fbmily, fblse);
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ST_UNCONNECTED;
    }

    public DbtbgrbmChbnnelImpl(SelectorProvider sp, FileDescriptor fd)
        throws IOException
    {
        super(sp);
        this.fbmily = Net.isIPv6Avbilbble() ?
            StbndbrdProtocolFbmily.INET6 : StbndbrdProtocolFbmily.INET;
        this.fd = fd;
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ST_UNCONNECTED;
        this.locblAddress = Net.locblAddress(fd);
    }

    public DbtbgrbmSocket socket() {
        synchronized (stbteLock) {
            if (socket == null)
                socket = DbtbgrbmSocketAdbptor.crebte(this);
            return socket;
        }
    }

    @Override
    public SocketAddress getLocblAddress() throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            // Perform security check before returning bddress
            return Net.getRevebledLocblAddress(locblAddress);
        }
    }

    @Override
    public SocketAddress getRemoteAddress() throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            return remoteAddress;
        }
    }

    @Override
    public <T> DbtbgrbmChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException
    {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            ensureOpen();

            if (nbme == StbndbrdSocketOptions.IP_TOS ||
                nbme == StbndbrdSocketOptions.IP_MULTICAST_TTL ||
                nbme == StbndbrdSocketOptions.IP_MULTICAST_LOOP)
            {
                // options bre protocol dependent
                Net.setSocketOption(fd, fbmily, nbme, vblue);
                return this;
            }

            if (nbme == StbndbrdSocketOptions.IP_MULTICAST_IF) {
                if (vblue == null)
                    throw new IllegblArgumentException("Cbnnot set IP_MULTICAST_IF to 'null'");
                NetworkInterfbce interf = (NetworkInterfbce)vblue;
                if (fbmily == StbndbrdProtocolFbmily.INET6) {
                    int index = interf.getIndex();
                    if (index == -1)
                        throw new IOException("Network interfbce cbnnot be identified");
                    Net.setInterfbce6(fd, index);
                } else {
                    // need IPv4 bddress to identify interfbce
                    Inet4Address tbrget = Net.bnyInet4Address(interf);
                    if (tbrget == null)
                        throw new IOException("Network interfbce not configured for IPv4");
                    int tbrgetAddress = Net.inet4AsInt(tbrget);
                    Net.setInterfbce4(fd, tbrgetAddress);
                }
                return this;
            }
            if (nbme == StbndbrdSocketOptions.SO_REUSEADDR &&
                    Net.useExclusiveBind() && locblAddress != null)
            {
                reuseAddressEmulbted = true;
                this.isReuseAddress = (Boolebn)vblue;
            }

            // rembining options don't need bny specibl hbndling
            Net.setSocketOption(fd, Net.UNSPEC, nbme, vblue);
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
            ensureOpen();

            if (nbme == StbndbrdSocketOptions.IP_TOS ||
                nbme == StbndbrdSocketOptions.IP_MULTICAST_TTL ||
                nbme == StbndbrdSocketOptions.IP_MULTICAST_LOOP)
            {
                return (T) Net.getSocketOption(fd, fbmily, nbme);
            }

            if (nbme == StbndbrdSocketOptions.IP_MULTICAST_IF) {
                if (fbmily == StbndbrdProtocolFbmily.INET) {
                    int bddress = Net.getInterfbce4(fd);
                    if (bddress == 0)
                        return null;    // defbult interfbce

                    InetAddress ib = Net.inet4FromInt(bddress);
                    NetworkInterfbce ni = NetworkInterfbce.getByInetAddress(ib);
                    if (ni == null)
                        throw new IOException("Unbble to mbp bddress to interfbce");
                    return (T) ni;
                } else {
                    int index = Net.getInterfbce6(fd);
                    if (index == 0)
                        return null;    // defbult interfbce

                    NetworkInterfbce ni = NetworkInterfbce.getByIndex(index);
                    if (ni == null)
                        throw new IOException("Unbble to mbp index to interfbce");
                    return (T) ni;
                }
            }

            if (nbme == StbndbrdSocketOptions.SO_REUSEADDR &&
                    reuseAddressEmulbted)
            {
                return (T)Boolebn.vblueOf(isReuseAddress);
            }

            // no specibl hbndling
            return (T) Net.getSocketOption(fd, Net.UNSPEC, nbme);
        }
    }

    privbte stbtic clbss DefbultOptionsHolder {
        stbtic finbl Set<SocketOption<?>> defbultOptions = defbultOptions();

        privbte stbtic Set<SocketOption<?>> defbultOptions() {
            HbshSet<SocketOption<?>> set = new HbshSet<SocketOption<?>>(8);
            set.bdd(StbndbrdSocketOptions.SO_SNDBUF);
            set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
            set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
            set.bdd(StbndbrdSocketOptions.SO_BROADCAST);
            set.bdd(StbndbrdSocketOptions.IP_TOS);
            set.bdd(StbndbrdSocketOptions.IP_MULTICAST_IF);
            set.bdd(StbndbrdSocketOptions.IP_MULTICAST_TTL);
            set.bdd(StbndbrdSocketOptions.IP_MULTICAST_LOOP);
            if (ExtendedOptionsImpl.flowSupported()) {
                set.bdd(jdk.net.ExtendedSocketOptions.SO_FLOW_SLA);
            }
            return Collections.unmodifibbleSet(set);
        }
    }

    @Override
    public finbl Set<SocketOption<?>> supportedOptions() {
        return DefbultOptionsHolder.defbultOptions;
    }

    privbte void ensureOpen() throws ClosedChbnnelException {
        if (!isOpen())
            throw new ClosedChbnnelException();
    }

    privbte SocketAddress sender;       // Set by receive0 (## ugh)

    public SocketAddress receive(ByteBuffer dst) throws IOException {
        if (dst.isRebdOnly())
            throw new IllegblArgumentException("Rebd-only buffer");
        if (dst == null)
            throw new NullPointerException();
        synchronized (rebdLock) {
            ensureOpen();
            // Socket wbs not bound before bttempting receive
            if (locblAddress() == null)
                bind(null);
            int n = 0;
            ByteBuffer bb = null;
            try {
                begin();
                if (!isOpen())
                    return null;
                SecurityMbnbger security = System.getSecurityMbnbger();
                rebderThrebd = NbtiveThrebd.current();
                if (isConnected() || (security == null)) {
                    do {
                        n = receive(fd, dst);
                    } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                    if (n == IOStbtus.UNAVAILABLE)
                        return null;
                } else {
                    bb = Util.getTemporbryDirectBuffer(dst.rembining());
                    for (;;) {
                        do {
                            n = receive(fd, bb);
                        } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                        if (n == IOStbtus.UNAVAILABLE)
                            return null;
                        InetSocketAddress isb = (InetSocketAddress)sender;
                        try {
                            security.checkAccept(
                                isb.getAddress().getHostAddress(),
                                isb.getPort());
                        } cbtch (SecurityException se) {
                            // Ignore pbcket
                            bb.clebr();
                            n = 0;
                            continue;
                        }
                        bb.flip();
                        dst.put(bb);
                        brebk;
                    }
                }
                return sender;
            } finblly {
                if (bb != null)
                    Util.relebseTemporbryDirectBuffer(bb);
                rebderThrebd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    privbte int receive(FileDescriptor fd, ByteBuffer dst)
        throws IOException
    {
        int pos = dst.position();
        int lim = dst.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        if (dst instbnceof DirectBuffer && rem > 0)
            return receiveIntoNbtiveBuffer(fd, dst, rem, pos);

        // Substitute b nbtive buffer. If the supplied buffer is empty
        // we must instebd use b nonempty buffer, otherwise the cbll
        // will not block wbiting for b dbtbgrbm on some plbtforms.
        int newSize = Mbth.mbx(rem, 1);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(newSize);
        try {
            int n = receiveIntoNbtiveBuffer(fd, bb, newSize, 0);
            bb.flip();
            if (n > 0 && rem > 0)
                dst.put(bb);
            return n;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    privbte int receiveIntoNbtiveBuffer(FileDescriptor fd, ByteBuffer bb,
                                        int rem, int pos)
        throws IOException
    {
        int n = receive0(fd, ((DirectBuffer)bb).bddress() + pos, rem,
                         isConnected());
        if (n > 0)
            bb.position(pos + n);
        return n;
    }

    public int send(ByteBuffer src, SocketAddress tbrget)
        throws IOException
    {
        if (src == null)
            throw new NullPointerException();

        synchronized (writeLock) {
            ensureOpen();
            InetSocketAddress isb = Net.checkAddress(tbrget);
            InetAddress ib = isb.getAddress();
            if (ib == null)
                throw new IOException("Tbrget bddress not resolved");
            synchronized (stbteLock) {
                if (!isConnected()) {
                    if (tbrget == null)
                        throw new NullPointerException();
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        if (ib.isMulticbstAddress()) {
                            sm.checkMulticbst(ib);
                        } else {
                            sm.checkConnect(ib.getHostAddress(),
                                            isb.getPort());
                        }
                    }
                } else { // Connected cbse; Check bddress then write
                    if (!tbrget.equbls(remoteAddress)) {
                        throw new IllegblArgumentException(
                            "Connected bddress not equbl to tbrget bddress");
                    }
                    return write(src);
                }
            }

            int n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                writerThrebd = NbtiveThrebd.current();
                do {
                    n = send(fd, src, isb);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());

                synchronized (stbteLock) {
                    if (isOpen() && (locblAddress == null)) {
                        locblAddress = Net.locblAddress(fd);
                    }
                }
                return IOStbtus.normblize(n);
            } finblly {
                writerThrebd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    privbte int send(FileDescriptor fd, ByteBuffer src, InetSocketAddress tbrget)
        throws IOException
    {
        if (src instbnceof DirectBuffer)
            return sendFromNbtiveBuffer(fd, src, tbrget);

        // Substitute b nbtive buffer
        int pos = src.position();
        int lim = src.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        ByteBuffer bb = Util.getTemporbryDirectBuffer(rem);
        try {
            bb.put(src);
            bb.flip();
            // Do not updbte src until we see how mbny bytes were written
            src.position(pos);

            int n = sendFromNbtiveBuffer(fd, bb, tbrget);
            if (n > 0) {
                // now updbte src
                src.position(pos + n);
            }
            return n;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    privbte int sendFromNbtiveBuffer(FileDescriptor fd, ByteBuffer bb,
                                     InetSocketAddress tbrget)
        throws IOException
    {
        int pos = bb.position();
        int lim = bb.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        boolebn preferIPv6 = (fbmily != StbndbrdProtocolFbmily.INET);
        int written;
        try {
            written = send0(preferIPv6, fd, ((DirectBuffer)bb).bddress() + pos,
                            rem, tbrget.getAddress(), tbrget.getPort());
        } cbtch (PortUnrebchbbleException pue) {
            if (isConnected())
                throw pue;
            written = rem;
        }
        if (written > 0)
            bb.position(pos + written);
        return written;
    }

    public int rebd(ByteBuffer buf) throws IOException {
        if (buf == null)
            throw new NullPointerException();
        synchronized (rebdLock) {
            synchronized (stbteLock) {
                ensureOpen();
                if (!isConnected())
                    throw new NotYetConnectedException();
            }
            int n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                rebderThrebd = NbtiveThrebd.current();
                do {
                    n = IOUtil.rebd(fd, buf, -1, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                rebderThrebd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    public long rebd(ByteBuffer[] dsts, int offset, int length)
        throws IOException
    {
        if ((offset < 0) || (length < 0) || (offset > dsts.length - length))
            throw new IndexOutOfBoundsException();
        synchronized (rebdLock) {
            synchronized (stbteLock) {
                ensureOpen();
                if (!isConnected())
                    throw new NotYetConnectedException();
            }
            long n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                rebderThrebd = NbtiveThrebd.current();
                do {
                    n = IOUtil.rebd(fd, dsts, offset, length, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                rebderThrebd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    public int write(ByteBuffer buf) throws IOException {
        if (buf == null)
            throw new NullPointerException();
        synchronized (writeLock) {
            synchronized (stbteLock) {
                ensureOpen();
                if (!isConnected())
                    throw new NotYetConnectedException();
            }
            int n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                writerThrebd = NbtiveThrebd.current();
                do {
                    n = IOUtil.write(fd, buf, -1, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                writerThrebd = 0;
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
        synchronized (writeLock) {
            synchronized (stbteLock) {
                ensureOpen();
                if (!isConnected())
                    throw new NotYetConnectedException();
            }
            long n = 0;
            try {
                begin();
                if (!isOpen())
                    return 0;
                writerThrebd = NbtiveThrebd.current();
                do {
                    n = IOUtil.write(fd, srcs, offset, length, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                writerThrebd = 0;
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    protected void implConfigureBlocking(boolebn block) throws IOException {
        IOUtil.configureBlocking(fd, block);
    }

    public SocketAddress locblAddress() {
        synchronized (stbteLock) {
            return locblAddress;
        }
    }

    public SocketAddress remoteAddress() {
        synchronized (stbteLock) {
            return remoteAddress;
        }
    }

    @Override
    public DbtbgrbmChbnnel bind(SocketAddress locbl) throws IOException {
        synchronized (rebdLock) {
            synchronized (writeLock) {
                synchronized (stbteLock) {
                    ensureOpen();
                    if (locblAddress != null)
                        throw new AlrebdyBoundException();
                    InetSocketAddress isb;
                    if (locbl == null) {
                        // only Inet4Address bllowed with IPv4 socket
                        if (fbmily == StbndbrdProtocolFbmily.INET) {
                            isb = new InetSocketAddress(InetAddress.getByNbme("0.0.0.0"), 0);
                        } else {
                            isb = new InetSocketAddress(0);
                        }
                    } else {
                        isb = Net.checkAddress(locbl);

                        // only Inet4Address bllowed with IPv4 socket
                        if (fbmily == StbndbrdProtocolFbmily.INET) {
                            InetAddress bddr = isb.getAddress();
                            if (!(bddr instbnceof Inet4Address))
                                throw new UnsupportedAddressTypeException();
                        }
                    }
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        sm.checkListen(isb.getPort());
                    }
                    Net.bind(fbmily, fd, isb.getAddress(), isb.getPort());
                    locblAddress = Net.locblAddress(fd);
                }
            }
        }
        return this;
    }

    public boolebn isConnected() {
        synchronized (stbteLock) {
            return (stbte == ST_CONNECTED);
        }
    }

    void ensureOpenAndUnconnected() throws IOException { // pbckbge-privbte
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (stbte != ST_UNCONNECTED)
                throw new IllegblStbteException("Connect blrebdy invoked");
        }
    }

    @Override
    public DbtbgrbmChbnnel connect(SocketAddress sb) throws IOException {
        int locblPort = 0;

        synchronized(rebdLock) {
            synchronized(writeLock) {
                synchronized (stbteLock) {
                    ensureOpenAndUnconnected();
                    InetSocketAddress isb = Net.checkAddress(sb);
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null)
                        sm.checkConnect(isb.getAddress().getHostAddress(),
                                        isb.getPort());
                    int n = Net.connect(fbmily,
                                        fd,
                                        isb.getAddress(),
                                        isb.getPort());
                    if (n <= 0)
                        throw new Error();      // Cbn't hbppen

                    // Connection succeeded; disbllow further invocbtion
                    stbte = ST_CONNECTED;
                    remoteAddress = isb;
                    sender = isb;
                    cbchedSenderInetAddress = isb.getAddress();
                    cbchedSenderPort = isb.getPort();

                    // set or refresh locbl bddress
                    locblAddress = Net.locblAddress(fd);
                }
            }
        }
        return this;
    }

    public DbtbgrbmChbnnel disconnect() throws IOException {
        synchronized(rebdLock) {
            synchronized(writeLock) {
                synchronized (stbteLock) {
                    if (!isConnected() || !isOpen())
                        return this;
                    InetSocketAddress isb = remoteAddress;
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null)
                        sm.checkConnect(isb.getAddress().getHostAddress(),
                                        isb.getPort());
                    boolebn isIPv6 = (fbmily == StbndbrdProtocolFbmily.INET6);
                    disconnect0(fd, isIPv6);
                    remoteAddress = null;
                    stbte = ST_UNCONNECTED;

                    // refresh locbl bddress
                    locblAddress = Net.locblAddress(fd);
                }
            }
        }
        return this;
    }

    /**
     * Joins chbnnel's socket to the given group/interfbce bnd
     * optionbl source bddress.
     */
    privbte MembershipKey innerJoin(InetAddress group,
                                    NetworkInterfbce interf,
                                    InetAddress source)
        throws IOException
    {
        if (!group.isMulticbstAddress())
            throw new IllegblArgumentException("Group not b multicbst bddress");

        // check multicbst bddress is compbtible with this socket
        if (group instbnceof Inet4Address) {
            if (fbmily == StbndbrdProtocolFbmily.INET6 && !Net.cbnIPv6SocketJoinIPv4Group())
                throw new IllegblArgumentException("IPv6 socket cbnnot join IPv4 multicbst group");
        } else if (group instbnceof Inet6Address) {
            if (fbmily != StbndbrdProtocolFbmily.INET6)
                throw new IllegblArgumentException("Only IPv6 sockets cbn join IPv6 multicbst group");
        } else {
            throw new IllegblArgumentException("Address type not supported");
        }

        // check source bddress
        if (source != null) {
            if (source.isAnyLocblAddress())
                throw new IllegblArgumentException("Source bddress is b wildcbrd bddress");
            if (source.isMulticbstAddress())
                throw new IllegblArgumentException("Source bddress is multicbst bddress");
            if (source.getClbss() != group.getClbss())
                throw new IllegblArgumentException("Source bddress is different type to group");
        }

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkMulticbst(group);

        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();

            // check the registry to see if we bre blrebdy b member of the group
            if (registry == null) {
                registry = new MembershipRegistry();
            } else {
                // return existing membership key
                MembershipKey key = registry.checkMembership(group, interf, source);
                if (key != null)
                    return key;
            }

            MembershipKeyImpl key;
            if ((fbmily == StbndbrdProtocolFbmily.INET6) &&
                ((group instbnceof Inet6Address) || Net.cbnJoin6WithIPv4Group()))
            {
                int index = interf.getIndex();
                if (index == -1)
                    throw new IOException("Network interfbce cbnnot be identified");

                // need multicbst bnd source bddress bs byte brrbys
                byte[] groupAddress = Net.inet6AsByteArrby(group);
                byte[] sourceAddress = (source == null) ? null :
                    Net.inet6AsByteArrby(source);

                // join the group
                int n = Net.join6(fd, groupAddress, index, sourceAddress);
                if (n == IOStbtus.UNAVAILABLE)
                    throw new UnsupportedOperbtionException();

                key = new MembershipKeyImpl.Type6(this, group, interf, source,
                                                  groupAddress, index, sourceAddress);

            } else {
                // need IPv4 bddress to identify interfbce
                Inet4Address tbrget = Net.bnyInet4Address(interf);
                if (tbrget == null)
                    throw new IOException("Network interfbce not configured for IPv4");

                int groupAddress = Net.inet4AsInt(group);
                int tbrgetAddress = Net.inet4AsInt(tbrget);
                int sourceAddress = (source == null) ? 0 : Net.inet4AsInt(source);

                // join the group
                int n = Net.join4(fd, groupAddress, tbrgetAddress, sourceAddress);
                if (n == IOStbtus.UNAVAILABLE)
                    throw new UnsupportedOperbtionException();

                key = new MembershipKeyImpl.Type4(this, group, interf, source,
                                                  groupAddress, tbrgetAddress, sourceAddress);
            }

            registry.bdd(key);
            return key;
        }
    }

    @Override
    public MembershipKey join(InetAddress group,
                              NetworkInterfbce interf)
        throws IOException
    {
        return innerJoin(group, interf, null);
    }

    @Override
    public MembershipKey join(InetAddress group,
                              NetworkInterfbce interf,
                              InetAddress source)
        throws IOException
    {
        if (source == null)
            throw new NullPointerException("source bddress is null");
        return innerJoin(group, interf, source);
    }

    // pbckbge-privbte
    void drop(MembershipKeyImpl key) {
        bssert key.chbnnel() == this;

        synchronized (stbteLock) {
            if (!key.isVblid())
                return;

            try {
                if (key instbnceof MembershipKeyImpl.Type6) {
                    MembershipKeyImpl.Type6 key6 =
                        (MembershipKeyImpl.Type6)key;
                    Net.drop6(fd, key6.groupAddress(), key6.index(), key6.source());
                } else {
                    MembershipKeyImpl.Type4 key4 = (MembershipKeyImpl.Type4)key;
                    Net.drop4(fd, key4.groupAddress(), key4.interfbceAddress(),
                        key4.source());
                }
            } cbtch (IOException ioe) {
                // should not hbppen
                throw new AssertionError(ioe);
            }

            key.invblidbte();
            registry.remove(key);
        }
    }

    /**
     * Block dbtbgrbms from given source if b memory to receive bll
     * dbtbgrbms.
     */
    void block(MembershipKeyImpl key, InetAddress source)
        throws IOException
    {
        bssert key.chbnnel() == this;
        bssert key.sourceAddress() == null;

        synchronized (stbteLock) {
            if (!key.isVblid())
                throw new IllegblStbteException("key is no longer vblid");
            if (source.isAnyLocblAddress())
                throw new IllegblArgumentException("Source bddress is b wildcbrd bddress");
            if (source.isMulticbstAddress())
                throw new IllegblArgumentException("Source bddress is multicbst bddress");
            if (source.getClbss() != key.group().getClbss())
                throw new IllegblArgumentException("Source bddress is different type to group");

            int n;
            if (key instbnceof MembershipKeyImpl.Type6) {
                 MembershipKeyImpl.Type6 key6 =
                    (MembershipKeyImpl.Type6)key;
                n = Net.block6(fd, key6.groupAddress(), key6.index(),
                               Net.inet6AsByteArrby(source));
            } else {
                MembershipKeyImpl.Type4 key4 =
                    (MembershipKeyImpl.Type4)key;
                n = Net.block4(fd, key4.groupAddress(), key4.interfbceAddress(),
                               Net.inet4AsInt(source));
            }
            if (n == IOStbtus.UNAVAILABLE) {
                // bncient kernel
                throw new UnsupportedOperbtionException();
            }
        }
    }

    /**
     * Unblock given source.
     */
    void unblock(MembershipKeyImpl key, InetAddress source) {
        bssert key.chbnnel() == this;
        bssert key.sourceAddress() == null;

        synchronized (stbteLock) {
            if (!key.isVblid())
                throw new IllegblStbteException("key is no longer vblid");

            try {
                if (key instbnceof MembershipKeyImpl.Type6) {
                    MembershipKeyImpl.Type6 key6 =
                        (MembershipKeyImpl.Type6)key;
                    Net.unblock6(fd, key6.groupAddress(), key6.index(),
                                 Net.inet6AsByteArrby(source));
                } else {
                    MembershipKeyImpl.Type4 key4 =
                        (MembershipKeyImpl.Type4)key;
                    Net.unblock4(fd, key4.groupAddress(), key4.interfbceAddress(),
                                 Net.inet4AsInt(source));
                }
            } cbtch (IOException ioe) {
                // should not hbppen
                throw new AssertionError(ioe);
            }
        }
    }

    protected void implCloseSelectbbleChbnnel() throws IOException {
        synchronized (stbteLock) {
            if (stbte != ST_KILLED)
                nd.preClose(fd);
            ResourceMbnbger.bfterUdpClose();

            // if member of mulitcbst group then invblidbte bll keys
            if (registry != null)
                registry.invblidbteAll();

            long th;
            if ((th = rebderThrebd) != 0)
                NbtiveThrebd.signbl(th);
            if ((th = writerThrebd) != 0)
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

    protected void finblize() throws IOException {
        // fd is null if constructor threw exception
        if (fd != null)
            close();
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
            ((intOps & SelectionKey.OP_READ) != 0))
            newOps |= SelectionKey.OP_READ;

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

    // pbckbge-privbte
    int poll(int events, long timeout) throws IOException {
        bssert Threbd.holdsLock(blockingLock()) && !isBlocking();

        synchronized (rebdLock) {
            int n = 0;
            try {
                begin();
                synchronized (stbteLock) {
                    if (!isOpen())
                        return 0;
                    rebderThrebd = NbtiveThrebd.current();
                }
                n = Net.poll(fd, events, timeout);
            } finblly {
                rebderThrebd = 0;
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

        if ((ops & SelectionKey.OP_READ) != 0)
            newOps |= Net.POLLIN;
        if ((ops & SelectionKey.OP_WRITE) != 0)
            newOps |= Net.POLLOUT;
        if ((ops & SelectionKey.OP_CONNECT) != 0)
            newOps |= Net.POLLIN;
        sk.selector.putEventOps(sk, newOps);
    }

    public FileDescriptor getFD() {
        return fd;
    }

    public int getFDVbl() {
        return fdVbl;
    }


    // -- Nbtive methods --

    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive void disconnect0(FileDescriptor fd, boolebn isIPv6)
        throws IOException;

    privbte nbtive int receive0(FileDescriptor fd, long bddress, int len,
                                boolebn connected)
        throws IOException;

    privbte nbtive int send0(boolebn preferIPv6, FileDescriptor fd, long bddress,
                             int len, InetAddress bddr, int port)
        throws IOException;

    stbtic {
        IOUtil.lobd();
        initIDs();
    }

}
