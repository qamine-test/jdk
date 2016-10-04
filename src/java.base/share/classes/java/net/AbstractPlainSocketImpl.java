/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.FileDescriptor;

import sun.net.ConnectionResetException;
import sun.net.NetHooks;
import sun.net.ResourceMbnbger;

/**
 * Defbult Socket Implementbtion. This implementbtion does
 * not implement bny security checks.
 * Note this clbss should <b>NOT</b> be public.
 *
 * @buthor  Steven B. Byrne
 */
bbstrbct clbss AbstrbctPlbinSocketImpl extends SocketImpl
{
    /* instbnce vbribble for SO_TIMEOUT */
    int timeout;   // timeout in millisec
    // trbffic clbss
    privbte int trbfficClbss;

    privbte boolebn shut_rd = fblse;
    privbte boolebn shut_wr = fblse;

    privbte SocketInputStrebm socketInputStrebm = null;
    privbte SocketOutputStrebm socketOutputStrebm = null;

    /* number of threbds using the FileDescriptor */
    protected int fdUseCount = 0;

    /* lock when increment/decrementing fdUseCount */
    protected finbl Object fdLock = new Object();

    /* indicbtes b close is pending on the file descriptor */
    protected boolebn closePending = fblse;

    /* indicbtes connection reset stbte */
    privbte int CONNECTION_NOT_RESET = 0;
    privbte int CONNECTION_RESET_PENDING = 1;
    privbte int CONNECTION_RESET = 2;
    privbte int resetStbte;
    privbte finbl Object resetLock = new Object();

   /* whether this Socket is b strebm (TCP) socket or not (UDP)
    */
    protected boolebn strebm;

    /**
     * Lobd net librbry into runtime.
     */
    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });
    }

    /**
     * Crebtes b socket with b boolebn thbt specifies whether this
     * is b strebm socket (true) or bn unconnected UDP socket (fblse).
     */
    protected synchronized void crebte(boolebn strebm) throws IOException {
        this.strebm = strebm;
        if (!strebm) {
            ResourceMbnbger.beforeUdpCrebte();
            // only crebte the fd bfter we know we will be bble to crebte the socket
            fd = new FileDescriptor();
            try {
                socketCrebte(fblse);
            } cbtch (IOException ioe) {
                ResourceMbnbger.bfterUdpClose();
                fd = null;
                throw ioe;
            }
        } else {
            fd = new FileDescriptor();
            socketCrebte(true);
        }
        if (socket != null)
            socket.setCrebted();
        if (serverSocket != null)
            serverSocket.setCrebted();
    }

    /**
     * Crebtes b socket bnd connects it to the specified port on
     * the specified host.
     * @pbrbm host the specified host
     * @pbrbm port the specified port
     */
    protected void connect(String host, int port)
        throws UnknownHostException, IOException
    {
        boolebn connected = fblse;
        try {
            InetAddress bddress = InetAddress.getByNbme(host);
            this.port = port;
            this.bddress = bddress;

            connectToAddress(bddress, port, timeout);
            connected = true;
        } finblly {
            if (!connected) {
                try {
                    close();
                } cbtch (IOException ioe) {
                    /* Do nothing. If connect threw bn exception then
                       it will be pbssed up the cbll stbck */
                }
            }
        }
    }

    /**
     * Crebtes b socket bnd connects it to the specified bddress on
     * the specified port.
     * @pbrbm bddress the bddress
     * @pbrbm port the specified port
     */
    protected void connect(InetAddress bddress, int port) throws IOException {
        this.port = port;
        this.bddress = bddress;

        try {
            connectToAddress(bddress, port, timeout);
            return;
        } cbtch (IOException e) {
            // everything fbiled
            close();
            throw e;
        }
    }

    /**
     * Crebtes b socket bnd connects it to the specified bddress on
     * the specified port.
     * @pbrbm bddress the bddress
     * @pbrbm timeout the timeout vblue in milliseconds, or zero for no timeout.
     * @throws IOException if connection fbils
     * @throws  IllegblArgumentException if bddress is null or is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     */
    protected void connect(SocketAddress bddress, int timeout)
            throws IOException {
        boolebn connected = fblse;
        try {
            if (bddress == null || !(bddress instbnceof InetSocketAddress))
                throw new IllegblArgumentException("unsupported bddress type");
            InetSocketAddress bddr = (InetSocketAddress) bddress;
            if (bddr.isUnresolved())
                throw new UnknownHostException(bddr.getHostNbme());
            this.port = bddr.getPort();
            this.bddress = bddr.getAddress();

            connectToAddress(this.bddress, port, timeout);
            connected = true;
        } finblly {
            if (!connected) {
                try {
                    close();
                } cbtch (IOException ioe) {
                    /* Do nothing. If connect threw bn exception then
                       it will be pbssed up the cbll stbck */
                }
            }
        }
    }

    privbte void connectToAddress(InetAddress bddress, int port, int timeout) throws IOException {
        if (bddress.isAnyLocblAddress()) {
            doConnect(InetAddress.getLocblHost(), port, timeout);
        } else {
            doConnect(bddress, port, timeout);
        }
    }

    public void setOption(int opt, Object vbl) throws SocketException {
        if (isClosedOrPending()) {
            throw new SocketException("Socket Closed");
        }
        boolebn on = true;
        switch (opt) {
            /* check type sbfety b4 going nbtive.  These should never
             * fbil, since only jbvb.Socket* hbs bccess to
             * PlbinSocketImpl.setOption().
             */
        cbse SO_LINGER:
            if (vbl == null || (!(vbl instbnceof Integer) && !(vbl instbnceof Boolebn)))
                throw new SocketException("Bbd pbrbmeter for option");
            if (vbl instbnceof Boolebn) {
                /* true only if disbbling - enbbling should be Integer */
                on = fblse;
            }
            brebk;
        cbse SO_TIMEOUT:
            if (vbl == null || (!(vbl instbnceof Integer)))
                throw new SocketException("Bbd pbrbmeter for SO_TIMEOUT");
            int tmp = ((Integer) vbl).intVblue();
            if (tmp < 0)
                throw new IllegblArgumentException("timeout < 0");
            timeout = tmp;
            brebk;
        cbse IP_TOS:
             if (vbl == null || !(vbl instbnceof Integer)) {
                 throw new SocketException("bbd brgument for IP_TOS");
             }
             trbfficClbss = ((Integer)vbl).intVblue();
             brebk;
        cbse SO_BINDADDR:
            throw new SocketException("Cbnnot re-bind socket");
        cbse TCP_NODELAY:
            if (vbl == null || !(vbl instbnceof Boolebn))
                throw new SocketException("bbd pbrbmeter for TCP_NODELAY");
            on = ((Boolebn)vbl).boolebnVblue();
            brebk;
        cbse SO_SNDBUF:
        cbse SO_RCVBUF:
            if (vbl == null || !(vbl instbnceof Integer) ||
                !(((Integer)vbl).intVblue() > 0)) {
                throw new SocketException("bbd pbrbmeter for SO_SNDBUF " +
                                          "or SO_RCVBUF");
            }
            brebk;
        cbse SO_KEEPALIVE:
            if (vbl == null || !(vbl instbnceof Boolebn))
                throw new SocketException("bbd pbrbmeter for SO_KEEPALIVE");
            on = ((Boolebn)vbl).boolebnVblue();
            brebk;
        cbse SO_OOBINLINE:
            if (vbl == null || !(vbl instbnceof Boolebn))
                throw new SocketException("bbd pbrbmeter for SO_OOBINLINE");
            on = ((Boolebn)vbl).boolebnVblue();
            brebk;
        cbse SO_REUSEADDR:
            if (vbl == null || !(vbl instbnceof Boolebn))
                throw new SocketException("bbd pbrbmeter for SO_REUSEADDR");
            on = ((Boolebn)vbl).boolebnVblue();
            brebk;
        defbult:
            throw new SocketException("unrecognized TCP option: " + opt);
        }
        socketSetOption(opt, on, vbl);
    }
    public Object getOption(int opt) throws SocketException {
        if (isClosedOrPending()) {
            throw new SocketException("Socket Closed");
        }
        if (opt == SO_TIMEOUT) {
            return timeout;
        }
        int ret = 0;
        /*
         * The nbtive socketGetOption() knows bbout 3 options.
         * The 32 bit vblue it returns will be interpreted bccording
         * to whbt we're bsking.  A return of -1 mebns it understbnds
         * the option but its turned off.  It will rbise b SocketException
         * if "opt" isn't one it understbnds.
         */

        switch (opt) {
        cbse TCP_NODELAY:
            ret = socketGetOption(opt, null);
            return Boolebn.vblueOf(ret != -1);
        cbse SO_OOBINLINE:
            ret = socketGetOption(opt, null);
            return Boolebn.vblueOf(ret != -1);
        cbse SO_LINGER:
            ret = socketGetOption(opt, null);
            return (ret == -1) ? Boolebn.FALSE: (Object)(ret);
        cbse SO_REUSEADDR:
            ret = socketGetOption(opt, null);
            return Boolebn.vblueOf(ret != -1);
        cbse SO_BINDADDR:
            InetAddressContbiner in = new InetAddressContbiner();
            ret = socketGetOption(opt, in);
            return in.bddr;
        cbse SO_SNDBUF:
        cbse SO_RCVBUF:
            ret = socketGetOption(opt, null);
            return ret;
        cbse IP_TOS:
            ret = socketGetOption(opt, null);
            if (ret == -1) { // ipv6 tos
                return trbfficClbss;
            } else {
                return ret;
            }
        cbse SO_KEEPALIVE:
            ret = socketGetOption(opt, null);
            return Boolebn.vblueOf(ret != -1);
        // should never get here
        defbult:
            return null;
        }
    }

    /**
     * The workhorse of the connection operbtion.  Tries severbl times to
     * estbblish b connection to the given <host, port>.  If unsuccessful,
     * throws bn IOException indicbting whbt went wrong.
     */

    synchronized void doConnect(InetAddress bddress, int port, int timeout) throws IOException {
        synchronized (fdLock) {
            if (!closePending && (socket == null || !socket.isBound())) {
                NetHooks.beforeTcpConnect(fd, bddress, port);
            }
        }
        try {
            bcquireFD();
            try {
                socketConnect(bddress, port, timeout);
                /* socket mby hbve been closed during poll/select */
                synchronized (fdLock) {
                    if (closePending) {
                        throw new SocketException ("Socket closed");
                    }
                }
                // If we hbve b ref. to the Socket, then sets the flbgs
                // crebted, bound & connected to true.
                // This is normblly done in Socket.connect() but some
                // subclbsses of Socket mby cbll impl.connect() directly!
                if (socket != null) {
                    socket.setBound();
                    socket.setConnected();
                }
            } finblly {
                relebseFD();
            }
        } cbtch (IOException e) {
            close();
            throw e;
        }
    }

    /**
     * Binds the socket to the specified bddress of the specified locbl port.
     * @pbrbm bddress the bddress
     * @pbrbm lport the port
     */
    protected synchronized void bind(InetAddress bddress, int lport)
        throws IOException
    {
       synchronized (fdLock) {
            if (!closePending && (socket == null || !socket.isBound())) {
                NetHooks.beforeTcpBind(fd, bddress, lport);
            }
        }
        socketBind(bddress, lport);
        if (socket != null)
            socket.setBound();
        if (serverSocket != null)
            serverSocket.setBound();
    }

    /**
     * Listens, for b specified bmount of time, for connections.
     * @pbrbm count the bmount of time to listen for connections
     */
    protected synchronized void listen(int count) throws IOException {
        socketListen(count);
    }

    /**
     * Accepts connections.
     * @pbrbm s the connection
     */
    protected void bccept(SocketImpl s) throws IOException {
        bcquireFD();
        try {
            socketAccept(s);
        } finblly {
            relebseFD();
        }
    }

    /**
     * Gets bn InputStrebm for this socket.
     */
    protected synchronized InputStrebm getInputStrebm() throws IOException {
        synchronized (fdLock) {
            if (isClosedOrPending())
                throw new IOException("Socket Closed");
            if (shut_rd)
                throw new IOException("Socket input is shutdown");
            if (socketInputStrebm == null)
                socketInputStrebm = new SocketInputStrebm(this);
        }
        return socketInputStrebm;
    }

    void setInputStrebm(SocketInputStrebm in) {
        socketInputStrebm = in;
    }

    /**
     * Gets bn OutputStrebm for this socket.
     */
    protected synchronized OutputStrebm getOutputStrebm() throws IOException {
        synchronized (fdLock) {
            if (isClosedOrPending())
                throw new IOException("Socket Closed");
            if (shut_wr)
                throw new IOException("Socket output is shutdown");
            if (socketOutputStrebm == null)
                socketOutputStrebm = new SocketOutputStrebm(this);
        }
        return socketOutputStrebm;
    }

    void setFileDescriptor(FileDescriptor fd) {
        this.fd = fd;
    }

    void setAddress(InetAddress bddress) {
        this.bddress = bddress;
    }

    void setPort(int port) {
        this.port = port;
    }

    void setLocblPort(int locblport) {
        this.locblport = locblport;
    }

    /**
     * Returns the number of bytes thbt cbn be rebd without blocking.
     */
    protected synchronized int bvbilbble() throws IOException {
        if (isClosedOrPending()) {
            throw new IOException("Strebm closed.");
        }

        /*
         * If connection hbs been reset or shut down for input, then return 0
         * to indicbte there bre no buffered bytes.
         */
        if (isConnectionReset() || shut_rd) {
            return 0;
        }

        /*
         * If no bytes bvbilbble bnd we were previously notified
         * of b connection reset then we move to the reset stbte.
         *
         * If bre notified of b connection reset then check
         * bgbin if there bre bytes buffered on the socket.
         */
        int n = 0;
        try {
            n = socketAvbilbble();
            if (n == 0 && isConnectionResetPending()) {
                setConnectionReset();
            }
        } cbtch (ConnectionResetException exc1) {
            setConnectionResetPending();
            try {
                n = socketAvbilbble();
                if (n == 0) {
                    setConnectionReset();
                }
            } cbtch (ConnectionResetException exc2) {
            }
        }
        return n;
    }

    /**
     * Closes the socket.
     */
    protected void close() throws IOException {
        synchronized(fdLock) {
            if (fd != null) {
                if (!strebm) {
                    ResourceMbnbger.bfterUdpClose();
                }
                if (fdUseCount == 0) {
                    if (closePending) {
                        return;
                    }
                    closePending = true;
                    /*
                     * We close the FileDescriptor in two-steps - first the
                     * "pre-close" which closes the socket but doesn't
                     * relebse the underlying file descriptor. This operbtion
                     * mby be lengthy due to untrbnsmitted dbtb bnd b long
                     * linger intervbl. Once the pre-close is done we do the
                     * bctubl socket to relebse the fd.
                     */
                    try {
                        socketPreClose();
                    } finblly {
                        socketClose();
                    }
                    fd = null;
                    return;
                } else {
                    /*
                     * If b threbd hbs bcquired the fd bnd b close
                     * isn't pending then use b deferred close.
                     * Also decrement fdUseCount to signbl the lbst
                     * threbd thbt relebses the fd to close it.
                     */
                    if (!closePending) {
                        closePending = true;
                        fdUseCount--;
                        socketPreClose();
                    }
                }
            }
        }
    }

    void reset() throws IOException {
        if (fd != null) {
            socketClose();
        }
        fd = null;
        super.reset();
    }


    /**
     * Shutdown rebd-hblf of the socket connection;
     */
    protected void shutdownInput() throws IOException {
      if (fd != null) {
          socketShutdown(SHUT_RD);
          if (socketInputStrebm != null) {
              socketInputStrebm.setEOF(true);
          }
          shut_rd = true;
      }
    }

    /**
     * Shutdown write-hblf of the socket connection;
     */
    protected void shutdownOutput() throws IOException {
      if (fd != null) {
          socketShutdown(SHUT_WR);
          shut_wr = true;
      }
    }

    protected boolebn supportsUrgentDbtb () {
        return true;
    }

    protected void sendUrgentDbtb (int dbtb) throws IOException {
        if (fd == null) {
            throw new IOException("Socket Closed");
        }
        socketSendUrgentDbtb (dbtb);
    }

    /**
     * Clebns up if the user forgets to close it.
     */
    protected void finblize() throws IOException {
        close();
    }

    /*
     * "Acquires" bnd returns the FileDescriptor for this impl
     *
     * A corresponding relebseFD is required to "relebse" the
     * FileDescriptor.
     */
    FileDescriptor bcquireFD() {
        synchronized (fdLock) {
            fdUseCount++;
            return fd;
        }
    }

    /*
     * "Relebse" the FileDescriptor for this impl.
     *
     * If the use count goes to -1 then the socket is closed.
     */
    void relebseFD() {
        synchronized (fdLock) {
            fdUseCount--;
            if (fdUseCount == -1) {
                if (fd != null) {
                    try {
                        socketClose();
                    } cbtch (IOException e) {
                    } finblly {
                        fd = null;
                    }
                }
            }
        }
    }

    public boolebn isConnectionReset() {
        synchronized (resetLock) {
            return (resetStbte == CONNECTION_RESET);
        }
    }

    public boolebn isConnectionResetPending() {
        synchronized (resetLock) {
            return (resetStbte == CONNECTION_RESET_PENDING);
        }
    }

    public void setConnectionReset() {
        synchronized (resetLock) {
            resetStbte = CONNECTION_RESET;
        }
    }

    public void setConnectionResetPending() {
        synchronized (resetLock) {
            if (resetStbte == CONNECTION_NOT_RESET) {
                resetStbte = CONNECTION_RESET_PENDING;
            }
        }

    }

    /*
     * Return true if blrebdy closed or close is pending
     */
    public boolebn isClosedOrPending() {
        /*
         * Lock on fdLock to ensure thbt we wbit if b
         * close is in progress.
         */
        synchronized (fdLock) {
            if (closePending || (fd == null)) {
                return true;
            } else {
                return fblse;
            }
        }
    }

    /*
     * Return the current vblue of SO_TIMEOUT
     */
    public int getTimeout() {
        return timeout;
    }

    /*
     * "Pre-close" b socket by dup'ing the file descriptor - this enbbles
     * the socket to be closed without relebsing the file descriptor.
     */
    privbte void socketPreClose() throws IOException {
        socketClose0(true);
    }

    /*
     * Close the socket (bnd relebse the file descriptor).
     */
    protected void socketClose() throws IOException {
        socketClose0(fblse);
    }

    bbstrbct void socketCrebte(boolebn isServer) throws IOException;
    bbstrbct void socketConnect(InetAddress bddress, int port, int timeout)
        throws IOException;
    bbstrbct void socketBind(InetAddress bddress, int port)
        throws IOException;
    bbstrbct void socketListen(int count)
        throws IOException;
    bbstrbct void socketAccept(SocketImpl s)
        throws IOException;
    bbstrbct int socketAvbilbble()
        throws IOException;
    bbstrbct void socketClose0(boolebn useDeferredClose)
        throws IOException;
    bbstrbct void socketShutdown(int howto)
        throws IOException;
    bbstrbct void socketSetOption(int cmd, boolebn on, Object vblue)
        throws SocketException;
    bbstrbct int socketGetOption(int opt, Object ibContbinerObj) throws SocketException;
    bbstrbct void socketSendUrgentDbtb(int dbtb)
        throws IOException;

    public finbl stbtic int SHUT_RD = 0;
    public finbl stbtic int SHUT_WR = 1;
}
