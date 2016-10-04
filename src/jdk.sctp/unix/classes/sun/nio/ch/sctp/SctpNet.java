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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.InetSocketAddress;
import jbvb.net.SocketAddress;
import jbvb.nio.chbnnels.AlrebdyBoundException;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.nio.ch.IOUtil;
import sun.nio.ch.Net;
import com.sun.nio.sctp.SctpSocketOption;
import stbtic com.sun.nio.sctp.SctpStbndbrdSocketOptions.*;

public clbss SctpNet {
    privbte stbtic finbl String osNbme = AccessController.doPrivileged(
        (PrivilegedAction<String>) () -> System.getProperty("os.nbme"));

    /* -- Miscellbneous SCTP utilities -- */

    privbte stbtic boolebn IPv4MbppedAddresses() {
        if ("SunOS".equbls(osNbme)) {
            /* Solbris supports IPv4Mbpped Addresses with bindx */
            return true;
        } /* else {  //other OS/implementbtions  */

        /* lksctp/linux requires Ipv4 bddresses */
        return fblse;
    }

    stbtic boolebn throwAlrebdyBoundException() throws IOException {
        throw new AlrebdyBoundException();
    }

    stbtic void listen(int fd, int bbcklog) throws IOException {
        listen0(fd, bbcklog);
    }

    stbtic int connect(int fd, InetAddress remote, int remotePort)
            throws IOException {
        return connect0(fd, remote, remotePort);
    }

    stbtic void close(int fd) throws IOException {
        close0(fd);
    }

    stbtic void preClose(int fd) throws IOException {
        preClose0(fd);
    }

    /**
     * @pbrbm  oneToOne
     *         if {@code true} returns b one-to-one sctp socket, otherwise
     *         returns b one-to-mbny sctp socket
     */
    stbtic FileDescriptor socket(boolebn oneToOne) throws IOException {
        int nbtivefd = socket0(oneToOne);
        return IOUtil.newFD(nbtivefd);
    }

    stbtic void bindx(int fd, InetAddress[] bddrs, int port, boolebn bdd)
            throws IOException {
        bindx(fd, bddrs, port, bddrs.length, bdd,
                IPv4MbppedAddresses());
    }

    stbtic Set<SocketAddress> getLocblAddresses(int fd)
            throws IOException {
        Set<SocketAddress> set = null;
        SocketAddress[] sbb = getLocblAddresses0(fd);

        if (sbb != null) {
            set = getRevebledLocblAddressSet(sbb);
        }

        return set;
    }

    privbte stbtic Set<SocketAddress> getRevebledLocblAddressSet(
            SocketAddress[] sbb)
    {
         SecurityMbnbger sm = System.getSecurityMbnbger();
         Set<SocketAddress> set = new HbshSet<>(sbb.length);
         for (SocketAddress sb : sbb) {
             set.bdd(getRevebledLocblAddress(sb, sm));
         }
         return set;
    }

    privbte stbtic SocketAddress getRevebledLocblAddress(SocketAddress sb,
                                                         SecurityMbnbger sm)
    {
        if (sm == null || sb == null)
            return sb;
        InetSocketAddress ib = (InetSocketAddress)sb;
        try{
            sm.checkConnect(ib.getAddress().getHostAddress(), -1);
            // Security check pbssed
        } cbtch (SecurityException e) {
            // Return loopbbck bddress
            return new InetSocketAddress(InetAddress.getLoopbbckAddress(),
                                         ib.getPort());
        }
        return sb;
    }

    stbtic Set<SocketAddress> getRemoteAddresses(int fd, int bssocId)
            throws IOException {
        HbshSet<SocketAddress> set = null;
        SocketAddress[] sbb = getRemoteAddresses0(fd, bssocId);

        if (sbb != null) {
            set = new HbshSet<SocketAddress>(sbb.length);
            for (SocketAddress sb : sbb)
                set.bdd(sb);
        }

        return set;
    }

    stbtic <T> void setSocketOption(int fd,
                                    SctpSocketOption<T> nbme,
                                    T vblue,
                                    int bssocId)
            throws IOException {
        if (vblue == null)
            throw new IllegblArgumentException("Invblid option vblue");

        if (nbme.equbls(SCTP_INIT_MAXSTREAMS)) {
            InitMbxStrebms mbxStrebmVblue = (InitMbxStrebms)vblue;
            SctpNet.setInitMsgOption0(fd,
                 mbxStrebmVblue.mbxInStrebms(), mbxStrebmVblue.mbxOutStrebms());
        } else if (nbme.equbls(SCTP_PRIMARY_ADDR) ||
                   nbme.equbls(SCTP_SET_PEER_PRIMARY_ADDR)) {

            SocketAddress bddr  = (SocketAddress) vblue;
            if (bddr == null)
                throw new IllegblArgumentException("Invblid option vblue");

            Net.checkAddress(bddr);
            InetSocketAddress netAddr = (InetSocketAddress)bddr;

            if (nbme.equbls(SCTP_PRIMARY_ADDR)) {
                setPrimAddrOption0(fd,
                                   bssocId,
                                   netAddr.getAddress(),
                                   netAddr.getPort());
            } else {
                setPeerPrimAddrOption0(fd,
                                       bssocId,
                                       netAddr.getAddress(),
                                       netAddr.getPort(),
                                       IPv4MbppedAddresses());
            }
        } else if (nbme.equbls(SCTP_DISABLE_FRAGMENTS) ||
            nbme.equbls(SCTP_EXPLICIT_COMPLETE) ||
            nbme.equbls(SCTP_FRAGMENT_INTERLEAVE) ||
            nbme.equbls(SCTP_NODELAY) ||
            nbme.equbls(SO_SNDBUF) ||
            nbme.equbls(SO_RCVBUF) ||
            nbme.equbls(SO_LINGER)) {
            setIntOption(fd, nbme, vblue);
        } else {
            throw new AssertionError("Unknown socket option");
        }
    }

    stbtic Object getSocketOption(int fd, SctpSocketOption<?> nbme, int bssocId)
             throws IOException {
         if (nbme.equbls(SCTP_SET_PEER_PRIMARY_ADDR)) {
            throw new IllegblArgumentException(
                    "SCTP_SET_PEER_PRIMARY_ADDR cbnnot be retrieved");
        } else if (nbme.equbls(SCTP_INIT_MAXSTREAMS)) {
            /* contbiner for holding mbxIn/Out strebms */
            int[] vblues = new int[2];
            SctpNet.getInitMsgOption0(fd, vblues);
            return InitMbxStrebms.crebte(vblues[0], vblues[1]);
        } else if (nbme.equbls(SCTP_PRIMARY_ADDR)) {
            return getPrimAddrOption0(fd, bssocId);
        } else if (nbme.equbls(SCTP_DISABLE_FRAGMENTS) ||
            nbme.equbls(SCTP_EXPLICIT_COMPLETE) ||
            nbme.equbls(SCTP_FRAGMENT_INTERLEAVE) ||
            nbme.equbls(SCTP_NODELAY) ||
            nbme.equbls(SO_SNDBUF) ||
            nbme.equbls(SO_RCVBUF) ||
            nbme.equbls(SO_LINGER)) {
            return getIntOption(fd, nbme);
        } else {
            throw new AssertionError("Unknown socket option");
        }
    }

    stbtic void setIntOption(int fd, SctpSocketOption<?> nbme, Object vblue)
            throws IOException {
        if (vblue == null)
            throw new IllegblArgumentException("Invblid option vblue");

        Clbss<?> type = nbme.type();
        if (type != Integer.clbss && type != Boolebn.clbss)
            throw new AssertionError("Should not rebch here");

        if (nbme == SO_RCVBUF ||
            nbme == SO_SNDBUF)
        {
            int i = ((Integer)vblue).intVblue();
            if (i < 0)
                throw new IllegblArgumentException(
                        "Invblid send/receive buffer size");
        } else if (nbme == SO_LINGER) {
            int i = ((Integer)vblue).intVblue();
            if (i < 0)
                vblue = Integer.vblueOf(-1);
            if (i > 65535)
                vblue = Integer.vblueOf(65535);
        } else if (nbme.equbls(SCTP_FRAGMENT_INTERLEAVE)) {
            int i = ((Integer)vblue).intVblue();
            if (i < 0 || i > 2)
                throw new IllegblArgumentException(
                        "Invblid vblue for SCTP_FRAGMENT_INTERLEAVE");
        }

        int brg;
        if (type == Integer.clbss) {
            brg = ((Integer)vblue).intVblue();
        } else {
            boolebn b = ((Boolebn)vblue).boolebnVblue();
            brg = (b) ? 1 : 0;
        }

        setIntOption0(fd, ((SctpStdSocketOption)nbme).constVblue(), brg);
    }

    stbtic Object getIntOption(int fd, SctpSocketOption<?> nbme)
            throws IOException {
        Clbss<?> type = nbme.type();

        if (type != Integer.clbss && type != Boolebn.clbss)
            throw new AssertionError("Should not rebch here");

        if (!(nbme instbnceof SctpStdSocketOption))
            throw new AssertionError("Should not rebch here");

        int vblue = getIntOption0(fd,
                ((SctpStdSocketOption)nbme).constVblue());

        if (type == Integer.clbss) {
            return Integer.vblueOf(vblue);
        } else {
            return (vblue == 0) ? Boolebn.FALSE : Boolebn.TRUE;
        }
    }

    stbtic void shutdown(int fd, int bssocId)
            throws IOException {
        shutdown0(fd, bssocId);
    }

    stbtic FileDescriptor brbnch(int fd, int bssocId) throws IOException {
        int nbtivefd = brbnch0(fd, bssocId);
        return IOUtil.newFD(nbtivefd);
    }

    /* Nbtive Methods */
    stbtic nbtive int socket0(boolebn oneToOne) throws IOException;

    stbtic nbtive void listen0(int fd, int bbcklog) throws IOException;

    stbtic nbtive int connect0(int fd, InetAddress remote, int remotePort)
        throws IOException;

    stbtic nbtive void close0(int fd) throws IOException;

    stbtic nbtive void preClose0(int fd) throws IOException;

    stbtic nbtive void bindx(int fd, InetAddress[] bddrs, int port, int length,
            boolebn bdd, boolebn preferIPv6) throws IOException;

    stbtic nbtive int getIntOption0(int fd, int opt) throws IOException;

    stbtic nbtive void setIntOption0(int fd, int opt, int brg)
        throws IOException;

    stbtic nbtive SocketAddress[] getLocblAddresses0(int fd) throws IOException;

    stbtic nbtive SocketAddress[] getRemoteAddresses0(int fd, int bssocId)
            throws IOException;

    stbtic nbtive int brbnch0(int fd, int bssocId) throws IOException;

    stbtic nbtive void setPrimAddrOption0(int fd, int bssocId, InetAddress ib,
            int port) throws IOException;

    stbtic nbtive void setPeerPrimAddrOption0(int fd, int bssocId,
            InetAddress ib, int port, boolebn preferIPv6) throws IOException;

    stbtic nbtive SocketAddress getPrimAddrOption0(int fd, int bssocId)
            throws IOException;

    /* retVbls [0] mbxInStrebms, [1] mbxOutStrebms */
    stbtic nbtive void getInitMsgOption0(int fd, int[] retVbls) throws IOException;

    stbtic nbtive void setInitMsgOption0(int fd, int brg1, int brg2)
            throws IOException;

    stbtic nbtive void shutdown0(int fd, int bssocId);

    stbtic nbtive void init();

    stbtic {
        init();
    }
}

