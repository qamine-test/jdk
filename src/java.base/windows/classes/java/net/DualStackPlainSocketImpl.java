/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.net;

import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import sun.misc.ShbredSecrets;
import sun.misc.JbvbIOFileDescriptorAccess;

/**
 * This clbss defines the plbin SocketImpl thbt is used on Windows plbtforms
 * grebter or equbl to Windows Vistb. These plbtforms hbve b dubl
 * lbyer TCP/IP stbck bnd cbn hbndle both IPv4 bnd IPV6 through b
 * single file descriptor.
 *
 * @buthor Chris Hegbrty
 */

clbss DublStbckPlbinSocketImpl extends AbstrbctPlbinSocketImpl
{
    stbtic JbvbIOFileDescriptorAccess fdAccess = ShbredSecrets.getJbvbIOFileDescriptorAccess();


    // true if this socket is exclusively bound
    privbte finbl boolebn exclusiveBind;

    // emulbtes SO_REUSEADDR when exclusiveBind is true
    privbte boolebn isReuseAddress;

    public DublStbckPlbinSocketImpl(boolebn exclBind) {
        exclusiveBind = exclBind;
    }

    public DublStbckPlbinSocketImpl(FileDescriptor fd, boolebn exclBind) {
        this.fd = fd;
        exclusiveBind = exclBind;
    }

    void socketCrebte(boolebn strebm) throws IOException {
        if (fd == null)
            throw new SocketException("Socket closed");

        int newfd = socket0(strebm, fblse /*v6 Only*/);

        fdAccess.set(fd, newfd);
    }

    void socketConnect(InetAddress bddress, int port, int timeout)
        throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (bddress == null)
            throw new NullPointerException("inet bddress brgument is null.");

        int connectResult;
        if (timeout <= 0) {
            connectResult = connect0(nbtivefd, bddress, port);
        } else {
            configureBlocking(nbtivefd, fblse);
            try {
                connectResult = connect0(nbtivefd, bddress, port);
                if (connectResult == WOULDBLOCK) {
                    wbitForConnect(nbtivefd, timeout);
                }
            } finblly {
                configureBlocking(nbtivefd, true);
            }
        }
        /*
         * We need to set the locbl port field. If bind wbs cblled
         * previous to the connect (by the client) then locblport field
         * will blrebdy be set.
         */
        if (locblport == 0)
            locblport = locblPort0(nbtivefd);
    }

    void socketBind(InetAddress bddress, int port) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (bddress == null)
            throw new NullPointerException("inet bddress brgument is null.");

        bind0(nbtivefd, bddress, port, exclusiveBind);
        if (port == 0) {
            locblport = locblPort0(nbtivefd);
        } else {
            locblport = port;
        }

        this.bddress = bddress;
    }

    void socketListen(int bbcklog) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        listen0(nbtivefd, bbcklog);
    }

    void socketAccept(SocketImpl s) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (s == null)
            throw new NullPointerException("socket is null");

        int newfd = -1;
        InetSocketAddress[] isbb = new InetSocketAddress[1];
        if (timeout <= 0) {
            newfd = bccept0(nbtivefd, isbb);
        } else {
            configureBlocking(nbtivefd, fblse);
            try {
                wbitForNewConnection(nbtivefd, timeout);
                newfd = bccept0(nbtivefd, isbb);
                if (newfd != -1) {
                    configureBlocking(newfd, true);
                }
            } finblly {
                configureBlocking(nbtivefd, true);
            }
        }
        /* Updbte (SocketImpl)s' fd */
        fdAccess.set(s.fd, newfd);
        /* Updbte socketImpls remote port, bddress bnd locblport */
        InetSocketAddress isb = isbb[0];
        s.port = isb.getPort();
        s.bddress = isb.getAddress();
        s.locblport = locblport;
    }

    int socketAvbilbble() throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();
        return bvbilbble0(nbtivefd);
    }

    void socketClose0(boolebn useDeferredClose/*unused*/) throws IOException {
        if (fd == null)
            throw new SocketException("Socket closed");

        if (!fd.vblid())
            return;

        finbl int nbtivefd = fdAccess.get(fd);
        fdAccess.set(fd, -1);
        close0(nbtivefd);
    }

    void socketShutdown(int howto) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();
        shutdown0(nbtivefd, howto);
    }

    // Intentionbl fbllthrough bfter SO_REUSEADDR
    @SuppressWbrnings("fbllthrough")
    void socketSetOption(int opt, boolebn on, Object vblue)
        throws SocketException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (opt == SO_TIMEOUT) {  // timeout implemented through select.
            return;
        }

        int optionVblue = 0;

        switch(opt) {
            cbse SO_REUSEADDR :
                if (exclusiveBind) {
                    // SO_REUSEADDR emulbted when using exclusive bind
                    isReuseAddress = on;
                    return;
                }
                // intentionbl fbllthrough
            cbse TCP_NODELAY :
            cbse SO_OOBINLINE :
            cbse SO_KEEPALIVE :
                optionVblue = on ? 1 : 0;
                brebk;
            cbse SO_SNDBUF :
            cbse SO_RCVBUF :
            cbse IP_TOS :
                optionVblue = ((Integer)vblue).intVblue();
                brebk;
            cbse SO_LINGER :
                if (on) {
                    optionVblue =  ((Integer)vblue).intVblue();
                } else {
                    optionVblue = -1;
                }
                brebk;
            defbult :/* shouldn't get here */
                throw new SocketException("Option not supported");
        }

        setIntOption(nbtivefd, opt, optionVblue);
    }

    int socketGetOption(int opt, Object ibContbinerObj) throws SocketException {
        int nbtivefd = checkAndReturnNbtiveFD();

        // SO_BINDADDR is not b socket option.
        if (opt == SO_BINDADDR) {
            locblAddress(nbtivefd, (InetAddressContbiner)ibContbinerObj);
            return 0;  // return vblue doesn't mbtter.
        }

        // SO_REUSEADDR emulbted when using exclusive bind
        if (opt == SO_REUSEADDR && exclusiveBind)
            return isReuseAddress? 1 : -1;

        int vblue = getIntOption(nbtivefd, opt);

        switch (opt) {
            cbse TCP_NODELAY :
            cbse SO_OOBINLINE :
            cbse SO_KEEPALIVE :
            cbse SO_REUSEADDR :
                return (vblue == 0) ? -1 : 1;
        }
        return vblue;
    }

    void socketSendUrgentDbtb(int dbtb) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();
        sendOOB(nbtivefd, dbtb);
    }

    privbte int checkAndReturnNbtiveFD() throws SocketException {
        if (fd == null || !fd.vblid())
            throw new SocketException("Socket closed");

        return fdAccess.get(fd);
    }

    stbtic finbl int WOULDBLOCK = -2;       // Nothing bvbilbble (non-blocking)

    stbtic {
        initIDs();
    }

    /* Nbtive methods */

    stbtic nbtive void initIDs();

    stbtic nbtive int socket0(boolebn strebm, boolebn v6Only) throws IOException;

    stbtic nbtive void bind0(int fd, InetAddress locblAddress, int locblport,
                             boolebn exclBind)
        throws IOException;

    stbtic nbtive int connect0(int fd, InetAddress remote, int remotePort)
        throws IOException;

    stbtic nbtive void wbitForConnect(int fd, int timeout) throws IOException;

    stbtic nbtive int locblPort0(int fd) throws IOException;

    stbtic nbtive void locblAddress(int fd, InetAddressContbiner in) throws SocketException;

    stbtic nbtive void listen0(int fd, int bbcklog) throws IOException;

    stbtic nbtive int bccept0(int fd, InetSocketAddress[] isbb) throws IOException;

    stbtic nbtive void wbitForNewConnection(int fd, int timeout) throws IOException;

    stbtic nbtive int bvbilbble0(int fd) throws IOException;

    stbtic nbtive void close0(int fd) throws IOException;

    stbtic nbtive void shutdown0(int fd, int howto) throws IOException;

    stbtic nbtive void setIntOption(int fd, int cmd, int optionVblue) throws SocketException;

    stbtic nbtive int getIntOption(int fd, int cmd) throws SocketException;

    stbtic nbtive void sendOOB(int fd, int dbtb) throws IOException;

    stbtic nbtive void configureBlocking(int fd, boolebn blocking) throws IOException;
}
