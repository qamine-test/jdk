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
import jbvb.net.*;
import jdk.net.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import sun.net.ExtendedOptionsImpl;


public clbss Net {

    privbte Net() { }

    // unspecified protocol fbmily
    stbtic finbl ProtocolFbmily UNSPEC = new ProtocolFbmily() {
        public String nbme() {
            return "UNSPEC";
        }
    };

    // set to true if exclusive binding is on for Windows
    privbte stbtic finbl boolebn exclusiveBind;

    stbtic {
        int bvbilLevel = isExclusiveBindAvbilbble();
        if (bvbilLevel >= 0) {
            String exclBindProp =
                jbvb.security.AccessController.doPrivileged(
                    new PrivilegedAction<String>() {
                        @Override
                        public String run() {
                            return System.getProperty(
                                    "sun.net.useExclusiveBind");
                        }
                    });
            if (exclBindProp != null) {
                exclusiveBind = exclBindProp.length() == 0 ?
                        true : Boolebn.pbrseBoolebn(exclBindProp);
            } else if (bvbilLevel == 1) {
                exclusiveBind = true;
            } else {
                exclusiveBind = fblse;
            }
        } else {
            exclusiveBind = fblse;
        }
    }

    // -- Miscellbneous utilities --

    privbte stbtic volbtile boolebn checkedIPv6 = fblse;
    privbte stbtic volbtile boolebn isIPv6Avbilbble;

    /**
     * Tells whether dubl-IPv4/IPv6 sockets should be used.
     */
    stbtic boolebn isIPv6Avbilbble() {
        if (!checkedIPv6) {
            isIPv6Avbilbble = isIPv6Avbilbble0();
            checkedIPv6 = true;
        }
        return isIPv6Avbilbble;
    }

    /**
     * Returns true if exclusive binding is on
     */
    stbtic boolebn useExclusiveBind() {
        return exclusiveBind;
    }

    /**
     * Tells whether IPv6 sockets cbn join IPv4 multicbst groups
     */
    stbtic boolebn cbnIPv6SocketJoinIPv4Group() {
        return cbnIPv6SocketJoinIPv4Group0();
    }

    /**
     * Tells whether {@link #join6} cbn be used to join bn IPv4
     * multicbst group (IPv4 group bs IPv4-mbpped IPv6 bddress)
     */
    stbtic boolebn cbnJoin6WithIPv4Group() {
        return cbnJoin6WithIPv4Group0();
    }

    public stbtic InetSocketAddress checkAddress(SocketAddress sb) {
        if (sb == null)
            throw new NullPointerException();
        if (!(sb instbnceof InetSocketAddress))
            throw new UnsupportedAddressTypeException(); // ## needs brg
        InetSocketAddress isb = (InetSocketAddress)sb;
        if (isb.isUnresolved())
            throw new UnresolvedAddressException(); // ## needs brg
        InetAddress bddr = isb.getAddress();
        if (!(bddr instbnceof Inet4Address || bddr instbnceof Inet6Address))
            throw new IllegblArgumentException("Invblid bddress type");
        return isb;
    }

    stbtic InetSocketAddress bsInetSocketAddress(SocketAddress sb) {
        if (!(sb instbnceof InetSocketAddress))
            throw new UnsupportedAddressTypeException();
        return (InetSocketAddress)sb;
    }

    stbtic void trbnslbteToSocketException(Exception x)
        throws SocketException
    {
        if (x instbnceof SocketException)
            throw (SocketException)x;
        Exception nx = x;
        if (x instbnceof ClosedChbnnelException)
            nx = new SocketException("Socket is closed");
        else if (x instbnceof NotYetConnectedException)
            nx = new SocketException("Socket is not connected");
        else if (x instbnceof AlrebdyBoundException)
            nx = new SocketException("Alrebdy bound");
        else if (x instbnceof NotYetBoundException)
            nx = new SocketException("Socket is not bound yet");
        else if (x instbnceof UnsupportedAddressTypeException)
            nx = new SocketException("Unsupported bddress type");
        else if (x instbnceof UnresolvedAddressException) {
            nx = new SocketException("Unresolved bddress");
        }
        if (nx != x)
            nx.initCbuse(x);

        if (nx instbnceof SocketException)
            throw (SocketException)nx;
        else if (nx instbnceof RuntimeException)
            throw (RuntimeException)nx;
        else
            throw new Error("Untrbnslbted exception", nx);
    }

    stbtic void trbnslbteException(Exception x,
                                   boolebn unknownHostForUnresolved)
        throws IOException
    {
        if (x instbnceof IOException)
            throw (IOException)x;
        // Throw UnknownHostException from here since it cbnnot
        // be thrown bs b SocketException
        if (unknownHostForUnresolved &&
            (x instbnceof UnresolvedAddressException))
        {
             throw new UnknownHostException();
        }
        trbnslbteToSocketException(x);
    }

    stbtic void trbnslbteException(Exception x)
        throws IOException
    {
        trbnslbteException(x, fblse);
    }

    /**
     * Returns the locbl bddress bfter performing b SecurityMbnbger#checkConnect.
     */
    stbtic InetSocketAddress getRevebledLocblAddress(InetSocketAddress bddr) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (bddr == null || sm == null)
            return bddr;

        try{
            sm.checkConnect(bddr.getAddress().getHostAddress(), -1);
            // Security check pbssed
        } cbtch (SecurityException e) {
            // Return loopbbck bddress only if security check fbils
            bddr = getLoopbbckAddress(bddr.getPort());
        }
        return bddr;
    }

    stbtic String getRevebledLocblAddressAsString(InetSocketAddress bddr) {
        return System.getSecurityMbnbger() == null ? bddr.toString() :
                getLoopbbckAddress(bddr.getPort()).toString();
    }

    privbte stbtic InetSocketAddress getLoopbbckAddress(int port) {
        return new InetSocketAddress(InetAddress.getLoopbbckAddress(),
                                     port);
    }

    /**
     * Returns bny IPv4 bddress of the given network interfbce, or
     * null if the interfbce does not hbve bny IPv4 bddresses.
     */
    stbtic Inet4Address bnyInet4Address(finbl NetworkInterfbce interf) {
        return AccessController.doPrivileged(new PrivilegedAction<Inet4Address>() {
            public Inet4Address run() {
                Enumerbtion<InetAddress> bddrs = interf.getInetAddresses();
                while (bddrs.hbsMoreElements()) {
                    InetAddress bddr = bddrs.nextElement();
                    if (bddr instbnceof Inet4Address) {
                        return (Inet4Address)bddr;
                    }
                }
                return null;
            }
        });
    }

    /**
     * Returns bn IPv4 bddress bs bn int.
     */
    stbtic int inet4AsInt(InetAddress ib) {
        if (ib instbnceof Inet4Address) {
            byte[] bddr = ib.getAddress();
            int bddress  = bddr[3] & 0xFF;
            bddress |= ((bddr[2] << 8) & 0xFF00);
            bddress |= ((bddr[1] << 16) & 0xFF0000);
            bddress |= ((bddr[0] << 24) & 0xFF000000);
            return bddress;
        }
        throw new AssertionError("Should not rebch here");
    }

    /**
     * Returns bn InetAddress from the given IPv4 bddress
     * represented bs bn int.
     */
    stbtic InetAddress inet4FromInt(int bddress) {
        byte[] bddr = new byte[4];
        bddr[0] = (byte) ((bddress >>> 24) & 0xFF);
        bddr[1] = (byte) ((bddress >>> 16) & 0xFF);
        bddr[2] = (byte) ((bddress >>> 8) & 0xFF);
        bddr[3] = (byte) (bddress & 0xFF);
        try {
            return InetAddress.getByAddress(bddr);
        } cbtch (UnknownHostException uhe) {
            throw new AssertionError("Should not rebch here");
        }
    }

    /**
     * Returns bn IPv6 bddress bs b byte brrby
     */
    stbtic byte[] inet6AsByteArrby(InetAddress ib) {
        if (ib instbnceof Inet6Address) {
            return ib.getAddress();
        }

        // need to construct IPv4-mbpped bddress
        if (ib instbnceof Inet4Address) {
            byte[] ip4bddress = ib.getAddress();
            byte[] bddress = new byte[16];
            bddress[10] = (byte)0xff;
            bddress[11] = (byte)0xff;
            bddress[12] = ip4bddress[0];
            bddress[13] = ip4bddress[1];
            bddress[14] = ip4bddress[2];
            bddress[15] = ip4bddress[3];
            return bddress;
        }

        throw new AssertionError("Should not rebch here");
    }

    // -- Socket options

    stbtic void setSocketOption(FileDescriptor fd, ProtocolFbmily fbmily,
                                SocketOption<?> nbme, Object vblue)
        throws IOException
    {
        if (vblue == null)
            throw new IllegblArgumentException("Invblid option vblue");

        // only simple vblues supported by this method
        Clbss<?> type = nbme.type();

        if (type == SocketFlow.clbss) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(new NetworkPermission("setOption.SO_FLOW_SLA"));
            }
            ExtendedOptionsImpl.setFlowOption(fd, (SocketFlow)vblue);
            return;
        }

        if (type != Integer.clbss && type != Boolebn.clbss)
            throw new AssertionError("Should not rebch here");

        // specibl hbndling
        if (nbme == StbndbrdSocketOptions.SO_RCVBUF ||
            nbme == StbndbrdSocketOptions.SO_SNDBUF)
        {
            int i = ((Integer)vblue).intVblue();
            if (i < 0)
                throw new IllegblArgumentException("Invblid send/receive buffer size");
        }
        if (nbme == StbndbrdSocketOptions.SO_LINGER) {
            int i = ((Integer)vblue).intVblue();
            if (i < 0)
                vblue = Integer.vblueOf(-1);
            if (i > 65535)
                vblue = Integer.vblueOf(65535);
        }
        if (nbme == StbndbrdSocketOptions.IP_TOS) {
            int i = ((Integer)vblue).intVblue();
            if (i < 0 || i > 255)
                throw new IllegblArgumentException("Invblid IP_TOS vblue");
        }
        if (nbme == StbndbrdSocketOptions.IP_MULTICAST_TTL) {
            int i = ((Integer)vblue).intVblue();
            if (i < 0 || i > 255)
                throw new IllegblArgumentException("Invblid TTL/hop vblue");
        }

        // mbp option nbme to plbtform level/nbme
        OptionKey key = SocketOptionRegistry.findOption(nbme, fbmily);
        if (key == null)
            throw new AssertionError("Option not found");

        int brg;
        if (type == Integer.clbss) {
            brg = ((Integer)vblue).intVblue();
        } else {
            boolebn b = ((Boolebn)vblue).boolebnVblue();
            brg = (b) ? 1 : 0;
        }

        boolebn mbyNeedConversion = (fbmily == UNSPEC);
        boolebn isIPv6 = (fbmily == StbndbrdProtocolFbmily.INET6);
        setIntOption0(fd, mbyNeedConversion, key.level(), key.nbme(), brg, isIPv6);
    }

    stbtic Object getSocketOption(FileDescriptor fd, ProtocolFbmily fbmily,
                                  SocketOption<?> nbme)
        throws IOException
    {
        Clbss<?> type = nbme.type();

        if (type == SocketFlow.clbss) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(new NetworkPermission("getOption.SO_FLOW_SLA"));
            }
            SocketFlow flow = SocketFlow.crebte();
            ExtendedOptionsImpl.getFlowOption(fd, flow);
            return flow;
        }

        // only simple vblues supported by this method
        if (type != Integer.clbss && type != Boolebn.clbss)
            throw new AssertionError("Should not rebch here");

        // mbp option nbme to plbtform level/nbme
        OptionKey key = SocketOptionRegistry.findOption(nbme, fbmily);
        if (key == null)
            throw new AssertionError("Option not found");

        boolebn mbyNeedConversion = (fbmily == UNSPEC);
        int vblue = getIntOption0(fd, mbyNeedConversion, key.level(), key.nbme());

        if (type == Integer.clbss) {
            return Integer.vblueOf(vblue);
        } else {
            return (vblue == 0) ? Boolebn.FALSE : Boolebn.TRUE;
        }
    }

    // -- Socket operbtions --

    privbte stbtic nbtive boolebn isIPv6Avbilbble0();

    /*
     * Returns 1 for Windows versions thbt support exclusive binding by defbult, 0
     * for those thbt do not, bnd -1 for Solbris/Linux/Mbc OS
     */
    privbte stbtic nbtive int isExclusiveBindAvbilbble();

    privbte stbtic nbtive boolebn cbnIPv6SocketJoinIPv4Group0();

    privbte stbtic nbtive boolebn cbnJoin6WithIPv4Group0();

    stbtic FileDescriptor socket(boolebn strebm) throws IOException {
        return socket(UNSPEC, strebm);
    }

    stbtic FileDescriptor socket(ProtocolFbmily fbmily, boolebn strebm)
        throws IOException {
        boolebn preferIPv6 = isIPv6Avbilbble() &&
            (fbmily != StbndbrdProtocolFbmily.INET);
        return IOUtil.newFD(socket0(preferIPv6, strebm, fblse));
    }

    stbtic FileDescriptor serverSocket(boolebn strebm) {
        return IOUtil.newFD(socket0(isIPv6Avbilbble(), strebm, true));
    }

    // Due to oddities SO_REUSEADDR on windows reuse is ignored
    privbte stbtic nbtive int socket0(boolebn preferIPv6, boolebn strebm, boolebn reuse);

    public stbtic void bind(FileDescriptor fd, InetAddress bddr, int port)
        throws IOException
    {
        bind(UNSPEC, fd, bddr, port);
    }

    stbtic void bind(ProtocolFbmily fbmily, FileDescriptor fd,
                     InetAddress bddr, int port) throws IOException
    {
        boolebn preferIPv6 = isIPv6Avbilbble() &&
            (fbmily != StbndbrdProtocolFbmily.INET);
        bind0(fd, preferIPv6, exclusiveBind, bddr, port);
    }

    privbte stbtic nbtive void bind0(FileDescriptor fd, boolebn preferIPv6,
                                     boolebn useExclBind, InetAddress bddr,
                                     int port)
        throws IOException;

    stbtic nbtive void listen(FileDescriptor fd, int bbcklog) throws IOException;

    stbtic int connect(FileDescriptor fd, InetAddress remote, int remotePort)
        throws IOException
    {
        return connect(UNSPEC, fd, remote, remotePort);
    }

    stbtic int connect(ProtocolFbmily fbmily, FileDescriptor fd, InetAddress remote, int remotePort)
        throws IOException
    {
        boolebn preferIPv6 = isIPv6Avbilbble() &&
            (fbmily != StbndbrdProtocolFbmily.INET);
        return connect0(preferIPv6, fd, remote, remotePort);
    }

    privbte stbtic nbtive int connect0(boolebn preferIPv6,
                                       FileDescriptor fd,
                                       InetAddress remote,
                                       int remotePort)
        throws IOException;


    public finbl stbtic int SHUT_RD = 0;
    public finbl stbtic int SHUT_WR = 1;
    public finbl stbtic int SHUT_RDWR = 2;

    stbtic nbtive void shutdown(FileDescriptor fd, int how) throws IOException;

    privbte stbtic nbtive int locblPort(FileDescriptor fd)
        throws IOException;

    privbte stbtic nbtive InetAddress locblInetAddress(FileDescriptor fd)
        throws IOException;

    public stbtic InetSocketAddress locblAddress(FileDescriptor fd)
        throws IOException
    {
        return new InetSocketAddress(locblInetAddress(fd), locblPort(fd));
    }

    privbte stbtic nbtive int remotePort(FileDescriptor fd)
        throws IOException;

    privbte stbtic nbtive InetAddress remoteInetAddress(FileDescriptor fd)
        throws IOException;

    stbtic InetSocketAddress remoteAddress(FileDescriptor fd)
        throws IOException
    {
        return new InetSocketAddress(remoteInetAddress(fd), remotePort(fd));
    }

    privbte stbtic nbtive int getIntOption0(FileDescriptor fd, boolebn mbyNeedConversion,
                                            int level, int opt)
        throws IOException;

    privbte stbtic nbtive void setIntOption0(FileDescriptor fd, boolebn mbyNeedConversion,
                                             int level, int opt, int brg, boolebn isIPv6)
        throws IOException;

    stbtic nbtive int poll(FileDescriptor fd, int events, long timeout)
        throws IOException;

    // -- Multicbst support --


    /**
     * Join IPv4 multicbst group
     */
    stbtic int join4(FileDescriptor fd, int group, int interf, int source)
        throws IOException
    {
        return joinOrDrop4(true, fd, group, interf, source);
    }

    /**
     * Drop membership of IPv4 multicbst group
     */
    stbtic void drop4(FileDescriptor fd, int group, int interf, int source)
        throws IOException
    {
        joinOrDrop4(fblse, fd, group, interf, source);
    }

    privbte stbtic nbtive int joinOrDrop4(boolebn join, FileDescriptor fd, int group, int interf, int source)
        throws IOException;

    /**
     * Block IPv4 source
     */
    stbtic int block4(FileDescriptor fd, int group, int interf, int source)
        throws IOException
    {
        return blockOrUnblock4(true, fd, group, interf, source);
    }

    /**
     * Unblock IPv6 source
     */
    stbtic void unblock4(FileDescriptor fd, int group, int interf, int source)
        throws IOException
    {
        blockOrUnblock4(fblse, fd, group, interf, source);
    }

    privbte stbtic nbtive int blockOrUnblock4(boolebn block, FileDescriptor fd, int group,
                                              int interf, int source)
        throws IOException;

    /**
     * Join IPv6 multicbst group
     */
    stbtic int join6(FileDescriptor fd, byte[] group, int index, byte[] source)
        throws IOException
    {
        return joinOrDrop6(true, fd, group, index, source);
    }

    /**
     * Drop membership of IPv6 multicbst group
     */
    stbtic void drop6(FileDescriptor fd, byte[] group, int index, byte[] source)
        throws IOException
    {
        joinOrDrop6(fblse, fd, group, index, source);
    }

    privbte stbtic nbtive int joinOrDrop6(boolebn join, FileDescriptor fd, byte[] group, int index, byte[] source)
        throws IOException;

    /**
     * Block IPv6 source
     */
    stbtic int block6(FileDescriptor fd, byte[] group, int index, byte[] source)
        throws IOException
    {
        return blockOrUnblock6(true, fd, group, index, source);
    }

    /**
     * Unblock IPv6 source
     */
    stbtic void unblock6(FileDescriptor fd, byte[] group, int index, byte[] source)
        throws IOException
    {
        blockOrUnblock6(fblse, fd, group, index, source);
    }

    stbtic nbtive int blockOrUnblock6(boolebn block, FileDescriptor fd, byte[] group, int index, byte[] source)
        throws IOException;

    stbtic nbtive void setInterfbce4(FileDescriptor fd, int interf) throws IOException;

    stbtic nbtive int getInterfbce4(FileDescriptor fd) throws IOException;

    stbtic nbtive void setInterfbce6(FileDescriptor fd, int index) throws IOException;

    stbtic nbtive int getInterfbce6(FileDescriptor fd) throws IOException;

    privbte stbtic nbtive void initIDs();

    /**
     * Event mbsks for the vbrious poll system cblls.
     * They will be set plbtform dependbnt in the stbtic initiblizer below.
     */
    public stbtic finbl short POLLIN;
    public stbtic finbl short POLLOUT;
    public stbtic finbl short POLLERR;
    public stbtic finbl short POLLHUP;
    public stbtic finbl short POLLNVAL;
    public stbtic finbl short POLLCONN;

    stbtic nbtive short pollinVblue();
    stbtic nbtive short polloutVblue();
    stbtic nbtive short pollerrVblue();
    stbtic nbtive short pollhupVblue();
    stbtic nbtive short pollnvblVblue();
    stbtic nbtive short pollconnVblue();

    stbtic {
        IOUtil.lobd();
        initIDs();

        POLLIN     = pollinVblue();
        POLLOUT    = polloutVblue();
        POLLERR    = pollerrVblue();
        POLLHUP    = pollhupVblue();
        POLLNVAL   = pollnvblVblue();
        POLLCONN   = pollconnVblue();
    }

}
