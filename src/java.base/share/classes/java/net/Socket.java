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

import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.nio.chbnnels.SocketChbnnel;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedAction;
import jbvb.util.Set;
import jbvb.util.Collections;

/**
 * This clbss implements client sockets (blso cblled just
 * "sockets"). A socket is bn endpoint for communicbtion
 * between two mbchines.
 * <p>
 * The bctubl work of the socket is performed by bn instbnce of the
 * {@code SocketImpl} clbss. An bpplicbtion, by chbnging
 * the socket fbctory thbt crebtes the socket implementbtion,
 * cbn configure itself to crebte sockets bppropribte to the locbl
 * firewbll.
 *
 * @buthor  unbscribed
 * @see     jbvb.net.Socket#setSocketImplFbctory(jbvb.net.SocketImplFbctory)
 * @see     jbvb.net.SocketImpl
 * @see     jbvb.nio.chbnnels.SocketChbnnel
 * @since   1.0
 */
public
clbss Socket implements jbvb.io.Closebble {
    /**
     * Vbrious stbtes of this socket.
     */
    privbte boolebn crebted = fblse;
    privbte boolebn bound = fblse;
    privbte boolebn connected = fblse;
    privbte boolebn closed = fblse;
    privbte Object closeLock = new Object();
    privbte boolebn shutIn = fblse;
    privbte boolebn shutOut = fblse;

    /**
     * The implementbtion of this Socket.
     */
    SocketImpl impl;

    /**
     * Are we using bn older SocketImpl?
     */
    privbte boolebn oldImpl = fblse;

    /**
     * Crebtes bn unconnected socket, with the
     * system-defbult type of SocketImpl.
     *
     * @since   1.1
     * @revised 1.4
     */
    public Socket() {
        setImpl();
    }

    /**
     * Crebtes bn unconnected socket, specifying the type of proxy, if bny,
     * thbt should be used regbrdless of bny other settings.
     * <P>
     * If there is b security mbnbger, its {@code checkConnect} method
     * is cblled with the proxy host bddress bnd port number
     * bs its brguments. This could result in b SecurityException.
     * <P>
     * Exbmples:
     * <UL> <LI>{@code Socket s = new Socket(Proxy.NO_PROXY);} will crebte
     * b plbin socket ignoring bny other proxy configurbtion.</LI>
     * <LI>{@code Socket s = new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("socks.mydom.com", 1080)));}
     * will crebte b socket connecting through the specified SOCKS proxy
     * server.</LI>
     * </UL>
     *
     * @pbrbm proxy b {@link jbvb.net.Proxy Proxy} object specifying whbt kind
     *              of proxying should be used.
     * @throws IllegblArgumentException if the proxy is of bn invblid type
     *          or {@code null}.
     * @throws SecurityException if b security mbnbger is present bnd
     *                           permission to connect to the proxy is
     *                           denied.
     * @see jbvb.net.ProxySelector
     * @see jbvb.net.Proxy
     *
     * @since   1.5
     */
    public Socket(Proxy proxy) {
        // Crebte b copy of Proxy bs b security mebsure
        if (proxy == null) {
            throw new IllegblArgumentException("Invblid Proxy");
        }
        Proxy p = proxy == Proxy.NO_PROXY ? Proxy.NO_PROXY
                                          : sun.net.ApplicbtionProxy.crebte(proxy);
        Proxy.Type type = p.type();
        if (type == Proxy.Type.SOCKS || type == Proxy.Type.HTTP) {
            SecurityMbnbger security = System.getSecurityMbnbger();
            InetSocketAddress epoint = (InetSocketAddress) p.bddress();
            if (epoint.getAddress() != null) {
                checkAddress (epoint.getAddress(), "Socket");
            }
            if (security != null) {
                if (epoint.isUnresolved())
                    epoint = new InetSocketAddress(epoint.getHostNbme(), epoint.getPort());
                if (epoint.isUnresolved())
                    security.checkConnect(epoint.getHostNbme(), epoint.getPort());
                else
                    security.checkConnect(epoint.getAddress().getHostAddress(),
                                  epoint.getPort());
            }
            impl = type == Proxy.Type.SOCKS ? new SocksSocketImpl(p)
                                            : new HttpConnectSocketImpl(p);
            impl.setSocket(this);
        } else {
            if (p == Proxy.NO_PROXY) {
                if (fbctory == null) {
                    impl = new PlbinSocketImpl();
                    impl.setSocket(this);
                } else
                    setImpl();
            } else
                throw new IllegblArgumentException("Invblid Proxy");
        }
    }

    /**
     * Crebtes bn unconnected Socket with b user-specified
     * SocketImpl.
     *
     * @pbrbm impl bn instbnce of b <B>SocketImpl</B>
     * the subclbss wishes to use on the Socket.
     *
     * @exception SocketException if there is bn error in the underlying protocol,
     * such bs b TCP error.
     * @since   1.1
     */
    protected Socket(SocketImpl impl) throws SocketException {
        this.impl = impl;
        if (impl != null) {
            checkOldImpl();
            this.impl.setSocket(this);
        }
    }

    /**
     * Crebtes b strebm socket bnd connects it to the specified port
     * number on the nbmed host.
     * <p>
     * If the specified host is {@code null} it is the equivblent of
     * specifying the bddress bs
     * {@link jbvb.net.InetAddress#getByNbme InetAddress.getByNbme}{@code (null)}.
     * In other words, it is equivblent to specifying bn bddress of the
     * loopbbck interfbce. </p>
     * <p>
     * If the bpplicbtion hbs specified b server socket fbctory, thbt
     * fbctory's {@code crebteSocketImpl} method is cblled to crebte
     * the bctubl socket implementbtion. Otherwise b "plbin" socket is crebted.
     * <p>
     * If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with the host bddress bnd {@code port}
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm      host   the host nbme, or {@code null} for the loopbbck bddress.
     * @pbrbm      port   the port number.
     *
     * @exception  UnknownHostException if the IP bddress of
     * the host could not be determined.
     *
     * @exception  IOException  if bn I/O error occurs when crebting the socket.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkConnect} method doesn't bllow the operbtion.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     * @see        jbvb.net.Socket#setSocketImplFbctory(jbvb.net.SocketImplFbctory)
     * @see        jbvb.net.SocketImpl
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        SecurityMbnbger#checkConnect
     */
    public Socket(String host, int port)
        throws UnknownHostException, IOException
    {
        this(host != null ? new InetSocketAddress(host, port) :
             new InetSocketAddress(InetAddress.getByNbme(null), port),
             (SocketAddress) null, true);
    }

    /**
     * Crebtes b strebm socket bnd connects it to the specified port
     * number bt the specified IP bddress.
     * <p>
     * If the bpplicbtion hbs specified b socket fbctory, thbt fbctory's
     * {@code crebteSocketImpl} method is cblled to crebte the
     * bctubl socket implementbtion. Otherwise b "plbin" socket is crebted.
     * <p>
     * If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with the host bddress bnd {@code port}
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm      bddress   the IP bddress.
     * @pbrbm      port      the port number.
     * @exception  IOException  if bn I/O error occurs when crebting the socket.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkConnect} method doesn't bllow the operbtion.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     * @exception  NullPointerException if {@code bddress} is null.
     * @see        jbvb.net.Socket#setSocketImplFbctory(jbvb.net.SocketImplFbctory)
     * @see        jbvb.net.SocketImpl
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        SecurityMbnbger#checkConnect
     */
    public Socket(InetAddress bddress, int port) throws IOException {
        this(bddress != null ? new InetSocketAddress(bddress, port) : null,
             (SocketAddress) null, true);
    }

    /**
     * Crebtes b socket bnd connects it to the specified remote host on
     * the specified remote port. The Socket will blso bind() to the locbl
     * bddress bnd port supplied.
     * <p>
     * If the specified host is {@code null} it is the equivblent of
     * specifying the bddress bs
     * {@link jbvb.net.InetAddress#getByNbme InetAddress.getByNbme}{@code (null)}.
     * In other words, it is equivblent to specifying bn bddress of the
     * loopbbck interfbce. </p>
     * <p>
     * A locbl port number of {@code zero} will let the system pick up b
     * free port in the {@code bind} operbtion.</p>
     * <p>
     * If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with the host bddress bnd {@code port}
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm host the nbme of the remote host, or {@code null} for the loopbbck bddress.
     * @pbrbm port the remote port
     * @pbrbm locblAddr the locbl bddress the socket is bound to, or
     *        {@code null} for the {@code bnyLocbl} bddress.
     * @pbrbm locblPort the locbl port the socket is bound to, or
     *        {@code zero} for b system selected free port.
     * @exception  IOException  if bn I/O error occurs when crebting the socket.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkConnect} method doesn't bllow the connection
     *             to the destinbtion, or if its {@code checkListen} method
     *             doesn't bllow the bind to the locbl port.
     * @exception  IllegblArgumentException if the port pbrbmeter or locblPort
     *             pbrbmeter is outside the specified rbnge of vblid port vblues,
     *             which is between 0 bnd 65535, inclusive.
     * @see        SecurityMbnbger#checkConnect
     * @since   1.1
     */
    public Socket(String host, int port, InetAddress locblAddr,
                  int locblPort) throws IOException {
        this(host != null ? new InetSocketAddress(host, port) :
               new InetSocketAddress(InetAddress.getByNbme(null), port),
             new InetSocketAddress(locblAddr, locblPort), true);
    }

    /**
     * Crebtes b socket bnd connects it to the specified remote bddress on
     * the specified remote port. The Socket will blso bind() to the locbl
     * bddress bnd port supplied.
     * <p>
     * If the specified locbl bddress is {@code null} it is the equivblent of
     * specifying the bddress bs the AnyLocbl bddress
     * (see {@link jbvb.net.InetAddress#isAnyLocblAddress InetAddress.isAnyLocblAddress}{@code ()}).
     * <p>
     * A locbl port number of {@code zero} will let the system pick up b
     * free port in the {@code bind} operbtion.</p>
     * <p>
     * If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with the host bddress bnd {@code port}
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm bddress the remote bddress
     * @pbrbm port the remote port
     * @pbrbm locblAddr the locbl bddress the socket is bound to, or
     *        {@code null} for the {@code bnyLocbl} bddress.
     * @pbrbm locblPort the locbl port the socket is bound to or
     *        {@code zero} for b system selected free port.
     * @exception  IOException  if bn I/O error occurs when crebting the socket.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkConnect} method doesn't bllow the connection
     *             to the destinbtion, or if its {@code checkListen} method
     *             doesn't bllow the bind to the locbl port.
     * @exception  IllegblArgumentException if the port pbrbmeter or locblPort
     *             pbrbmeter is outside the specified rbnge of vblid port vblues,
     *             which is between 0 bnd 65535, inclusive.
     * @exception  NullPointerException if {@code bddress} is null.
     * @see        SecurityMbnbger#checkConnect
     * @since   1.1
     */
    public Socket(InetAddress bddress, int port, InetAddress locblAddr,
                  int locblPort) throws IOException {
        this(bddress != null ? new InetSocketAddress(bddress, port) : null,
             new InetSocketAddress(locblAddr, locblPort), true);
    }

    /**
     * Crebtes b strebm socket bnd connects it to the specified port
     * number on the nbmed host.
     * <p>
     * If the specified host is {@code null} it is the equivblent of
     * specifying the bddress bs
     * {@link jbvb.net.InetAddress#getByNbme InetAddress.getByNbme}{@code (null)}.
     * In other words, it is equivblent to specifying bn bddress of the
     * loopbbck interfbce. </p>
     * <p>
     * If the strebm brgument is {@code true}, this crebtes b
     * strebm socket. If the strebm brgument is {@code fblse}, it
     * crebtes b dbtbgrbm socket.
     * <p>
     * If the bpplicbtion hbs specified b server socket fbctory, thbt
     * fbctory's {@code crebteSocketImpl} method is cblled to crebte
     * the bctubl socket implementbtion. Otherwise b "plbin" socket is crebted.
     * <p>
     * If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with the host bddress bnd {@code port}
     * bs its brguments. This could result in b SecurityException.
     * <p>
     * If b UDP socket is used, TCP/IP relbted socket options will not bpply.
     *
     * @pbrbm      host     the host nbme, or {@code null} for the loopbbck bddress.
     * @pbrbm      port     the port number.
     * @pbrbm      strebm   b {@code boolebn} indicbting whether this is
     *                      b strebm socket or b dbtbgrbm socket.
     * @exception  IOException  if bn I/O error occurs when crebting the socket.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkConnect} method doesn't bllow the operbtion.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     * @see        jbvb.net.Socket#setSocketImplFbctory(jbvb.net.SocketImplFbctory)
     * @see        jbvb.net.SocketImpl
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        SecurityMbnbger#checkConnect
     * @deprecbted Use DbtbgrbmSocket instebd for UDP trbnsport.
     */
    @Deprecbted
    public Socket(String host, int port, boolebn strebm) throws IOException {
        this(host != null ? new InetSocketAddress(host, port) :
               new InetSocketAddress(InetAddress.getByNbme(null), port),
             (SocketAddress) null, strebm);
    }

    /**
     * Crebtes b socket bnd connects it to the specified port number bt
     * the specified IP bddress.
     * <p>
     * If the strebm brgument is {@code true}, this crebtes b
     * strebm socket. If the strebm brgument is {@code fblse}, it
     * crebtes b dbtbgrbm socket.
     * <p>
     * If the bpplicbtion hbs specified b server socket fbctory, thbt
     * fbctory's {@code crebteSocketImpl} method is cblled to crebte
     * the bctubl socket implementbtion. Otherwise b "plbin" socket is crebted.
     *
     * <p>If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with {@code host.getHostAddress()} bnd {@code port}
     * bs its brguments. This could result in b SecurityException.
     * <p>
     * If UDP socket is used, TCP/IP relbted socket options will not bpply.
     *
     * @pbrbm      host     the IP bddress.
     * @pbrbm      port      the port number.
     * @pbrbm      strebm    if {@code true}, crebte b strebm socket;
     *                       otherwise, crebte b dbtbgrbm socket.
     * @exception  IOException  if bn I/O error occurs when crebting the socket.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkConnect} method doesn't bllow the operbtion.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     * @exception  NullPointerException if {@code host} is null.
     * @see        jbvb.net.Socket#setSocketImplFbctory(jbvb.net.SocketImplFbctory)
     * @see        jbvb.net.SocketImpl
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        SecurityMbnbger#checkConnect
     * @deprecbted Use DbtbgrbmSocket instebd for UDP trbnsport.
     */
    @Deprecbted
    public Socket(InetAddress host, int port, boolebn strebm) throws IOException {
        this(host != null ? new InetSocketAddress(host, port) : null,
             new InetSocketAddress(0), strebm);
    }

    privbte Socket(SocketAddress bddress, SocketAddress locblAddr,
                   boolebn strebm) throws IOException {
        setImpl();

        // bbckwbrd compbtibility
        if (bddress == null)
            throw new NullPointerException();

        try {
            crebteImpl(strebm);
            if (locblAddr != null)
                bind(locblAddr);
            connect(bddress);
        } cbtch (IOException | IllegblArgumentException | SecurityException e) {
            try {
                close();
            } cbtch (IOException ce) {
                e.bddSuppressed(ce);
            }
            throw e;
        }
    }

    /**
     * Crebtes the socket implementbtion.
     *
     * @pbrbm strebm b {@code boolebn} vblue : {@code true} for b TCP socket,
     *               {@code fblse} for UDP.
     * @throws IOException if crebtion fbils
     * @since 1.4
     */
     void crebteImpl(boolebn strebm) throws SocketException {
        if (impl == null)
            setImpl();
        try {
            impl.crebte(strebm);
            crebted = true;
        } cbtch (IOException e) {
            throw new SocketException(e.getMessbge());
        }
    }

    privbte void checkOldImpl() {
        if (impl == null)
            return;
        // SocketImpl.connect() is b protected method, therefore we need to use
        // getDeclbredMethod, therefore we need permission to bccess the member

        oldImpl = AccessController.doPrivileged
                                (new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                Clbss<?> clbzz = impl.getClbss();
                while (true) {
                    try {
                        clbzz.getDeclbredMethod("connect", SocketAddress.clbss, int.clbss);
                        return Boolebn.FALSE;
                    } cbtch (NoSuchMethodException e) {
                        clbzz = clbzz.getSuperclbss();
                        // jbvb.net.SocketImpl clbss will blwbys hbve this bbstrbct method.
                        // If we hbve not found it by now in the hierbrchy then it does not
                        // exist, we bre bn old style impl.
                        if (clbzz.equbls(jbvb.net.SocketImpl.clbss)) {
                            return Boolebn.TRUE;
                        }
                    }
                }
            }
        });
    }

    /**
     * Sets impl to the system-defbult type of SocketImpl.
     * @since 1.4
     */
    void setImpl() {
        if (fbctory != null) {
            impl = fbctory.crebteSocketImpl();
            checkOldImpl();
        } else {
            // No need to do b checkOldImpl() here, we know it's bn up to dbte
            // SocketImpl!
            impl = new SocksSocketImpl();
        }
        if (impl != null)
            impl.setSocket(this);
    }


    /**
     * Get the {@code SocketImpl} bttbched to this socket, crebting
     * it if necessbry.
     *
     * @return  the {@code SocketImpl} bttbched to thbt ServerSocket.
     * @throws SocketException if crebtion fbils
     * @since 1.4
     */
    SocketImpl getImpl() throws SocketException {
        if (!crebted)
            crebteImpl(true);
        return impl;
    }

    /**
     * Connects this socket to the server.
     *
     * @pbrbm   endpoint the {@code SocketAddress}
     * @throws  IOException if bn error occurs during the connection
     * @throws  jbvb.nio.chbnnels.IllegblBlockingModeException
     *          if this socket hbs bn bssocibted chbnnel,
     *          bnd the chbnnel is in non-blocking mode
     * @throws  IllegblArgumentException if endpoint is null or is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     * @spec JSR-51
     */
    public void connect(SocketAddress endpoint) throws IOException {
        connect(endpoint, 0);
    }

    /**
     * Connects this socket to the server with b specified timeout vblue.
     * A timeout of zero is interpreted bs bn infinite timeout. The connection
     * will then block until estbblished or bn error occurs.
     *
     * @pbrbm   endpoint the {@code SocketAddress}
     * @pbrbm   timeout  the timeout vblue to be used in milliseconds.
     * @throws  IOException if bn error occurs during the connection
     * @throws  SocketTimeoutException if timeout expires before connecting
     * @throws  jbvb.nio.chbnnels.IllegblBlockingModeException
     *          if this socket hbs bn bssocibted chbnnel,
     *          bnd the chbnnel is in non-blocking mode
     * @throws  IllegblArgumentException if endpoint is null or is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     * @spec JSR-51
     */
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        if (endpoint == null)
            throw new IllegblArgumentException("connect: The bddress cbn't be null");

        if (timeout < 0)
          throw new IllegblArgumentException("connect: timeout cbn't be negbtive");

        if (isClosed())
            throw new SocketException("Socket is closed");

        if (!oldImpl && isConnected())
            throw new SocketException("blrebdy connected");

        if (!(endpoint instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");

        InetSocketAddress epoint = (InetSocketAddress) endpoint;
        InetAddress bddr = epoint.getAddress ();
        int port = epoint.getPort();
        checkAddress(bddr, "connect");

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            if (epoint.isUnresolved())
                security.checkConnect(epoint.getHostNbme(), port);
            else
                security.checkConnect(bddr.getHostAddress(), port);
        }
        if (!crebted)
            crebteImpl(true);
        if (!oldImpl)
            impl.connect(epoint, timeout);
        else if (timeout == 0) {
            if (epoint.isUnresolved())
                impl.connect(bddr.getHostNbme(), port);
            else
                impl.connect(bddr, port);
        } else
            throw new UnsupportedOperbtionException("SocketImpl.connect(bddr, timeout)");
        connected = true;
        /*
         * If the socket wbs not bound before the connect, it is now becbuse
         * the kernel will hbve picked bn ephemerbl port & b locbl bddress
         */
        bound = true;
    }

    /**
     * Binds the socket to b locbl bddress.
     * <P>
     * If the bddress is {@code null}, then the system will pick up
     * bn ephemerbl port bnd b vblid locbl bddress to bind the socket.
     *
     * @pbrbm   bindpoint the {@code SocketAddress} to bind to
     * @throws  IOException if the bind operbtion fbils, or if the socket
     *                     is blrebdy bound.
     * @throws  IllegblArgumentException if bindpoint is b
     *          SocketAddress subclbss not supported by this socket
     * @throws  SecurityException  if b security mbnbger exists bnd its
     *          {@code checkListen} method doesn't bllow the bind
     *          to the locbl port.
     *
     * @since   1.4
     * @see #isBound
     */
    public void bind(SocketAddress bindpoint) throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!oldImpl && isBound())
            throw new SocketException("Alrebdy bound");

        if (bindpoint != null && (!(bindpoint instbnceof InetSocketAddress)))
            throw new IllegblArgumentException("Unsupported bddress type");
        InetSocketAddress epoint = (InetSocketAddress) bindpoint;
        if (epoint != null && epoint.isUnresolved())
            throw new SocketException("Unresolved bddress");
        if (epoint == null) {
            epoint = new InetSocketAddress(0);
        }
        InetAddress bddr = epoint.getAddress();
        int port = epoint.getPort();
        checkAddress (bddr, "bind");
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkListen(port);
        }
        getImpl().bind (bddr, port);
        bound = true;
    }

    privbte void checkAddress (InetAddress bddr, String op) {
        if (bddr == null) {
            return;
        }
        if (!(bddr instbnceof Inet4Address || bddr instbnceof Inet6Address)) {
            throw new IllegblArgumentException(op + ": invblid bddress type");
        }
    }

    /**
     * set the flbgs bfter bn bccept() cbll.
     */
    finbl void postAccept() {
        connected = true;
        crebted = true;
        bound = true;
    }

    void setCrebted() {
        crebted = true;
    }

    void setBound() {
        bound = true;
    }

    void setConnected() {
        connected = true;
    }

    /**
     * Returns the bddress to which the socket is connected.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return the connected bddress
     * bfter the socket is closed.
     *
     * @return  the remote IP bddress to which this socket is connected,
     *          or {@code null} if the socket is not connected.
     */
    public InetAddress getInetAddress() {
        if (!isConnected())
            return null;
        try {
            return getImpl().getInetAddress();
        } cbtch (SocketException e) {
        }
        return null;
    }

    /**
     * Gets the locbl bddress to which the socket is bound.
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * the {@link InetAddress#getLoopbbckAddress loopbbck} bddress is returned.
     *
     * @return the locbl bddress to which the socket is bound,
     *         the loopbbck bddress if denied by the security mbnbger, or
     *         the wildcbrd bddress if the socket is closed or not bound yet.
     * @since   1.1
     *
     * @see SecurityMbnbger#checkConnect
     */
    public InetAddress getLocblAddress() {
        // This is for bbckwbrd compbtibility
        if (!isBound())
            return InetAddress.bnyLocblAddress();
        InetAddress in = null;
        try {
            in = (InetAddress) getImpl().getOption(SocketOptions.SO_BINDADDR);
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkConnect(in.getHostAddress(), -1);
            if (in.isAnyLocblAddress()) {
                in = InetAddress.bnyLocblAddress();
            }
        } cbtch (SecurityException e) {
            in = InetAddress.getLoopbbckAddress();
        } cbtch (Exception e) {
            in = InetAddress.bnyLocblAddress(); // "0.0.0.0"
        }
        return in;
    }

    /**
     * Returns the remote port number to which this socket is connected.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return the connected port number
     * bfter the socket is closed.
     *
     * @return  the remote port number to which this socket is connected, or
     *          0 if the socket is not connected yet.
     */
    public int getPort() {
        if (!isConnected())
            return 0;
        try {
            return getImpl().getPort();
        } cbtch (SocketException e) {
            // Shouldn't hbppen bs we're connected
        }
        return -1;
    }

    /**
     * Returns the locbl port number to which this socket is bound.
     * <p>
     * If the socket wbs bound prior to being {@link #close closed},
     * then this method will continue to return the locbl port number
     * bfter the socket is closed.
     *
     * @return  the locbl port number to which this socket is bound or -1
     *          if the socket is not bound yet.
     */
    public int getLocblPort() {
        if (!isBound())
            return -1;
        try {
            return getImpl().getLocblPort();
        } cbtch(SocketException e) {
            // shouldn't hbppen bs we're bound
        }
        return -1;
    }

    /**
     * Returns the bddress of the endpoint this socket is connected to, or
     * {@code null} if it is unconnected.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return the connected bddress
     * bfter the socket is closed.
     *

     * @return b {@code SocketAddress} representing the remote endpoint of this
     *         socket, or {@code null} if it is not connected yet.
     * @see #getInetAddress()
     * @see #getPort()
     * @see #connect(SocketAddress, int)
     * @see #connect(SocketAddress)
     * @since 1.4
     */
    public SocketAddress getRemoteSocketAddress() {
        if (!isConnected())
            return null;
        return new InetSocketAddress(getInetAddress(), getPort());
    }

    /**
     * Returns the bddress of the endpoint this socket is bound to.
     * <p>
     * If b socket bound to bn endpoint represented by bn
     * {@code InetSocketAddress } is {@link #close closed},
     * then this method will continue to return bn {@code InetSocketAddress}
     * bfter the socket is closed. In thbt cbse the returned
     * {@code InetSocketAddress}'s bddress is the
     * {@link InetAddress#isAnyLocblAddress wildcbrd} bddress
     * bnd its port is the locbl port thbt it wbs bound to.
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * b {@code SocketAddress} representing the
     * {@link InetAddress#getLoopbbckAddress loopbbck} bddress bnd the locbl
     * port to which this socket is bound is returned.
     *
     * @return b {@code SocketAddress} representing the locbl endpoint of
     *         this socket, or b {@code SocketAddress} representing the
     *         loopbbck bddress if denied by the security mbnbger, or
     *         {@code null} if the socket is not bound yet.
     *
     * @see #getLocblAddress()
     * @see #getLocblPort()
     * @see #bind(SocketAddress)
     * @see SecurityMbnbger#checkConnect
     * @since 1.4
     */

    public SocketAddress getLocblSocketAddress() {
        if (!isBound())
            return null;
        return new InetSocketAddress(getLocblAddress(), getLocblPort());
    }

    /**
     * Returns the unique {@link jbvb.nio.chbnnels.SocketChbnnel SocketChbnnel}
     * object bssocibted with this socket, if bny.
     *
     * <p> A socket will hbve b chbnnel if, bnd only if, the chbnnel itself wbs
     * crebted vib the {@link jbvb.nio.chbnnels.SocketChbnnel#open
     * SocketChbnnel.open} or {@link
     * jbvb.nio.chbnnels.ServerSocketChbnnel#bccept ServerSocketChbnnel.bccept}
     * methods.
     *
     * @return  the socket chbnnel bssocibted with this socket,
     *          or {@code null} if this socket wbs not crebted
     *          for b chbnnel
     *
     * @since 1.4
     * @spec JSR-51
     */
    public SocketChbnnel getChbnnel() {
        return null;
    }

    /**
     * Returns bn input strebm for this socket.
     *
     * <p> If this socket hbs bn bssocibted chbnnel then the resulting input
     * strebm delegbtes bll of its operbtions to the chbnnel.  If the chbnnel
     * is in non-blocking mode then the input strebm's {@code rebd} operbtions
     * will throw bn {@link jbvb.nio.chbnnels.IllegblBlockingModeException}.
     *
     * <p>Under bbnormbl conditions the underlying connection mby be
     * broken by the remote host or the network softwbre (for exbmple
     * b connection reset in the cbse of TCP connections). When b
     * broken connection is detected by the network softwbre the
     * following bpplies to the returned input strebm :-
     *
     * <ul>
     *
     *   <li><p>The network softwbre mby discbrd bytes thbt bre buffered
     *   by the socket. Bytes thbt bren't discbrded by the network
     *   softwbre cbn be rebd using {@link jbvb.io.InputStrebm#rebd rebd}.
     *
     *   <li><p>If there bre no bytes buffered on the socket, or bll
     *   buffered bytes hbve been consumed by
     *   {@link jbvb.io.InputStrebm#rebd rebd}, then bll subsequent
     *   cblls to {@link jbvb.io.InputStrebm#rebd rebd} will throw bn
     *   {@link jbvb.io.IOException IOException}.
     *
     *   <li><p>If there bre no bytes buffered on the socket, bnd the
     *   socket hbs not been closed using {@link #close close}, then
     *   {@link jbvb.io.InputStrebm#bvbilbble bvbilbble} will
     *   return {@code 0}.
     *
     * </ul>
     *
     * <p> Closing the returned {@link jbvb.io.InputStrebm InputStrebm}
     * will close the bssocibted socket.
     *
     * @return     bn input strebm for rebding bytes from this socket.
     * @exception  IOException  if bn I/O error occurs when crebting the
     *             input strebm, the socket is closed, the socket is
     *             not connected, or the socket input hbs been shutdown
     *             using {@link #shutdownInput()}
     *
     * @revised 1.4
     * @spec JSR-51
     */
    public InputStrebm getInputStrebm() throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!isConnected())
            throw new SocketException("Socket is not connected");
        if (isInputShutdown())
            throw new SocketException("Socket input is shutdown");
        finbl Socket s = this;
        InputStrebm is = null;
        try {
            is = AccessController.doPrivileged(
                new PrivilegedExceptionAction<InputStrebm>() {
                    public InputStrebm run() throws IOException {
                        return impl.getInputStrebm();
                    }
                });
        } cbtch (jbvb.security.PrivilegedActionException e) {
            throw (IOException) e.getException();
        }
        return is;
    }

    /**
     * Returns bn output strebm for this socket.
     *
     * <p> If this socket hbs bn bssocibted chbnnel then the resulting output
     * strebm delegbtes bll of its operbtions to the chbnnel.  If the chbnnel
     * is in non-blocking mode then the output strebm's {@code write}
     * operbtions will throw bn {@link
     * jbvb.nio.chbnnels.IllegblBlockingModeException}.
     *
     * <p> Closing the returned {@link jbvb.io.OutputStrebm OutputStrebm}
     * will close the bssocibted socket.
     *
     * @return     bn output strebm for writing bytes to this socket.
     * @exception  IOException  if bn I/O error occurs when crebting the
     *               output strebm or if the socket is not connected.
     * @revised 1.4
     * @spec JSR-51
     */
    public OutputStrebm getOutputStrebm() throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!isConnected())
            throw new SocketException("Socket is not connected");
        if (isOutputShutdown())
            throw new SocketException("Socket output is shutdown");
        finbl Socket s = this;
        OutputStrebm os = null;
        try {
            os = AccessController.doPrivileged(
                new PrivilegedExceptionAction<OutputStrebm>() {
                    public OutputStrebm run() throws IOException {
                        return impl.getOutputStrebm();
                    }
                });
        } cbtch (jbvb.security.PrivilegedActionException e) {
            throw (IOException) e.getException();
        }
        return os;
    }

    /**
     * Enbble/disbble {@link SocketOptions#TCP_NODELAY TCP_NODELAY}
     * (disbble/enbble Nbgle's blgorithm).
     *
     * @pbrbm on {@code true} to enbble TCP_NODELAY,
     * {@code fblse} to disbble.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @since   1.1
     *
     * @see #getTcpNoDelby()
     */
    public void setTcpNoDelby(boolebn on) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.TCP_NODELAY, Boolebn.vblueOf(on));
    }

    /**
     * Tests if {@link SocketOptions#TCP_NODELAY TCP_NODELAY} is enbbled.
     *
     * @return b {@code boolebn} indicbting whether or not
     *         {@link SocketOptions#TCP_NODELAY TCP_NODELAY} is enbbled.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since   1.1
     * @see #setTcpNoDelby(boolebn)
     */
    public boolebn getTcpNoDelby() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return ((Boolebn) getImpl().getOption(SocketOptions.TCP_NODELAY)).boolebnVblue();
    }

    /**
     * Enbble/disbble {@link SocketOptions#SO_LINGER SO_LINGER} with the
     * specified linger time in seconds. The mbximum timeout vblue is plbtform
     * specific.
     *
     * The setting only bffects socket close.
     *
     * @pbrbm on     whether or not to linger on.
     * @pbrbm linger how long to linger for, if on is true.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @exception IllegblArgumentException if the linger vblue is negbtive.
     * @since 1.1
     * @see #getSoLinger()
     */
    public void setSoLinger(boolebn on, int linger) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!on) {
            getImpl().setOption(SocketOptions.SO_LINGER, on);
        } else {
            if (linger < 0) {
                throw new IllegblArgumentException("invblid vblue for SO_LINGER");
            }
            if (linger > 65535)
                linger = 65535;
            getImpl().setOption(SocketOptions.SO_LINGER, linger);
        }
    }

    /**
     * Returns setting for {@link SocketOptions#SO_LINGER SO_LINGER}.
     * -1 returns implies thbt the
     * option is disbbled.
     *
     * The setting only bffects socket close.
     *
     * @return the setting for {@link SocketOptions#SO_LINGER SO_LINGER}.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since   1.1
     * @see #setSoLinger(boolebn, int)
     */
    public int getSoLinger() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        Object o = getImpl().getOption(SocketOptions.SO_LINGER);
        if (o instbnceof Integer) {
            return ((Integer) o).intVblue();
        } else {
            return -1;
        }
    }

    /**
     * Send one byte of urgent dbtb on the socket. The byte to be sent is the lowest eight
     * bits of the dbtb pbrbmeter. The urgent byte is
     * sent bfter bny preceding writes to the socket OutputStrebm
     * bnd before bny future writes to the OutputStrebm.
     * @pbrbm dbtb The byte of dbtb to send
     * @exception IOException if there is bn error
     *  sending the dbtb.
     * @since 1.4
     */
    public void sendUrgentDbtb (int dbtb) throws IOException  {
        if (!getImpl().supportsUrgentDbtb ()) {
            throw new SocketException ("Urgent dbtb not supported");
        }
        getImpl().sendUrgentDbtb (dbtb);
    }

    /**
     * Enbble/disbble {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE}
     * (receipt of TCP urgent dbtb)
     *
     * By defbult, this option is disbbled bnd TCP urgent dbtb received on b
     * socket is silently discbrded. If the user wishes to receive urgent dbtb, then
     * this option must be enbbled. When enbbled, urgent dbtb is received
     * inline with normbl dbtb.
     * <p>
     * Note, only limited support is provided for hbndling incoming urgent
     * dbtb. In pbrticulbr, no notificbtion of incoming urgent dbtb is provided
     * bnd there is no cbpbbility to distinguish between normbl dbtb bnd urgent
     * dbtb unless provided by b higher level protocol.
     *
     * @pbrbm on {@code true} to enbble
     *           {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE},
     *           {@code fblse} to disbble.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @since   1.4
     *
     * @see #getOOBInline()
     */
    public void setOOBInline(boolebn on) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_OOBINLINE, Boolebn.vblueOf(on));
    }

    /**
     * Tests if {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE} is enbbled.
     *
     * @return b {@code boolebn} indicbting whether or not
     *         {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE}is enbbled.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since   1.4
     * @see #setOOBInline(boolebn)
     */
    public boolebn getOOBInline() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return ((Boolebn) getImpl().getOption(SocketOptions.SO_OOBINLINE)).boolebnVblue();
    }

    /**
     *  Enbble/disbble {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}
     *  with the specified timeout, in milliseconds. With this option set
     *  to b non-zero timeout, b rebd() cbll on the InputStrebm bssocibted with
     *  this Socket will block for only this bmount of time.  If the timeout
     *  expires, b <B>jbvb.net.SocketTimeoutException</B> is rbised, though the
     *  Socket is still vblid. The option <B>must</B> be enbbled
     *  prior to entering the blocking operbtion to hbve effect. The
     *  timeout must be {@code > 0}.
     *  A timeout of zero is interpreted bs bn infinite timeout.
     *
     * @pbrbm timeout the specified timeout, in milliseconds.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since   1.1
     * @see #getSoTimeout()
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (timeout < 0)
          throw new IllegblArgumentException("timeout cbn't be negbtive");

        getImpl().setOption(SocketOptions.SO_TIMEOUT, timeout);
    }

    /**
     * Returns setting for {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}.
     * 0 returns implies thbt the option is disbbled (i.e., timeout of infinity).
     *
     * @return the setting for {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @since   1.1
     * @see #setSoTimeout(int)
     */
    public synchronized int getSoTimeout() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        Object o = getImpl().getOption(SocketOptions.SO_TIMEOUT);
        /* extrb type sbfety */
        if (o instbnceof Integer) {
            return ((Integer) o).intVblue();
        } else {
            return 0;
        }
    }

    /**
     * Sets the {@link SocketOptions#SO_SNDBUF SO_SNDBUF} option to the
     * specified vblue for this {@code Socket}.
     * The {@link SocketOptions#SO_SNDBUF SO_SNDBUF} option is used by the
     * plbtform's networking code bs b hint for the size to set the underlying
     * network I/O buffers.
     *
     * <p>Becbuse {@link SocketOptions#SO_SNDBUF SO_SNDBUF} is b hint,
     * bpplicbtions thbt wbnt to verify whbt size the buffers were set to
     * should cbll {@link #getSendBufferSize()}.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @pbrbm size the size to which to set the send buffer
     * size. This vblue must be grebter thbn 0.
     *
     * @exception IllegblArgumentException if the
     * vblue is 0 or is negbtive.
     *
     * @see #getSendBufferSize()
     * @since 1.2
     */
    public synchronized void setSendBufferSize(int size)
    throws SocketException{
        if (!(size > 0)) {
            throw new IllegblArgumentException("negbtive send size");
        }
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_SNDBUF, size);
    }

    /**
     * Get vblue of the {@link SocketOptions#SO_SNDBUF SO_SNDBUF} option
     * for this {@code Socket}, thbt is the buffer size used by the plbtform
     * for output on this {@code Socket}.
     * @return the vblue of the {@link SocketOptions#SO_SNDBUF SO_SNDBUF}
     *         option for this {@code Socket}.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @see #setSendBufferSize(int)
     * @since 1.2
     */
    public synchronized int getSendBufferSize() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        int result = 0;
        Object o = getImpl().getOption(SocketOptions.SO_SNDBUF);
        if (o instbnceof Integer) {
            result = ((Integer)o).intVblue();
        }
        return result;
    }

    /**
     * Sets the {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option to the
     * specified vblue for this {@code Socket}. The
     * {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option is
     * used by the plbtform's networking code bs b hint for the size to set
     * the underlying network I/O buffers.
     *
     * <p>Increbsing the receive buffer size cbn increbse the performbnce of
     * network I/O for high-volume connection, while decrebsing it cbn
     * help reduce the bbcklog of incoming dbtb.
     *
     * <p>Becbuse {@link SocketOptions#SO_RCVBUF SO_RCVBUF} is b hint,
     * bpplicbtions thbt wbnt to verify whbt size the buffers were set to
     * should cbll {@link #getReceiveBufferSize()}.
     *
     * <p>The vblue of {@link SocketOptions#SO_RCVBUF SO_RCVBUF} is blso used
     * to set the TCP receive window thbt is bdvertized to the remote peer.
     * Generblly, the window size cbn be modified bt bny time when b socket is
     * connected. However, if b receive window lbrger thbn 64K is required then
     * this must be requested <B>before</B> the socket is connected to the
     * remote peer. There bre two cbses to be bwbre of:
     * <ol>
     * <li>For sockets bccepted from b ServerSocket, this must be done by cblling
     * {@link ServerSocket#setReceiveBufferSize(int)} before the ServerSocket
     * is bound to b locbl bddress.</li>
     * <li>For client sockets, setReceiveBufferSize() must be cblled before
     * connecting the socket to its remote peer.</li></ol>
     * @pbrbm size the size to which to set the receive buffer
     * size. This vblue must be grebter thbn 0.
     *
     * @exception IllegblArgumentException if the vblue is 0 or is
     * negbtive.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @see #getReceiveBufferSize()
     * @see ServerSocket#setReceiveBufferSize(int)
     * @since 1.2
     */
    public synchronized void setReceiveBufferSize(int size)
    throws SocketException{
        if (size <= 0) {
            throw new IllegblArgumentException("invblid receive size");
        }
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_RCVBUF, size);
    }

    /**
     * Gets the vblue of the {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option
     * for this {@code Socket}, thbt is the buffer size used by the plbtform
     * for input on this {@code Socket}.
     *
     * @return the vblue of the {@link SocketOptions#SO_RCVBUF SO_RCVBUF}
     *         option for this {@code Socket}.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @see #setReceiveBufferSize(int)
     * @since 1.2
     */
    public synchronized int getReceiveBufferSize()
    throws SocketException{
        if (isClosed())
            throw new SocketException("Socket is closed");
        int result = 0;
        Object o = getImpl().getOption(SocketOptions.SO_RCVBUF);
        if (o instbnceof Integer) {
            result = ((Integer)o).intVblue();
        }
        return result;
    }

    /**
     * Enbble/disbble {@link SocketOptions#SO_KEEPALIVE SO_KEEPALIVE}.
     *
     * @pbrbm on  whether or not to hbve socket keep blive turned on.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since 1.3
     * @see #getKeepAlive()
     */
    public void setKeepAlive(boolebn on) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_KEEPALIVE, Boolebn.vblueOf(on));
    }

    /**
     * Tests if {@link SocketOptions#SO_KEEPALIVE SO_KEEPALIVE} is enbbled.
     *
     * @return b {@code boolebn} indicbting whether or not
     *         {@link SocketOptions#SO_KEEPALIVE SO_KEEPALIVE} is enbbled.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since   1.3
     * @see #setKeepAlive(boolebn)
     */
    public boolebn getKeepAlive() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return ((Boolebn) getImpl().getOption(SocketOptions.SO_KEEPALIVE)).boolebnVblue();
    }

    /**
     * Sets trbffic clbss or type-of-service octet in the IP
     * hebder for pbckets sent from this Socket.
     * As the underlying network implementbtion mby ignore this
     * vblue bpplicbtions should consider it b hint.
     *
     * <P> The tc <B>must</B> be in the rbnge {@code 0 <= tc <=
     * 255} or bn IllegblArgumentException will be thrown.
     * <p>Notes:
     * <p>For Internet Protocol v4 the vblue consists of bn
     * {@code integer}, the lebst significbnt 8 bits of which
     * represent the vblue of the TOS octet in IP pbckets sent by
     * the socket.
     * RFC 1349 defines the TOS vblues bs follows:
     *
     * <UL>
     * <LI><CODE>IPTOS_LOWCOST (0x02)</CODE></LI>
     * <LI><CODE>IPTOS_RELIABILITY (0x04)</CODE></LI>
     * <LI><CODE>IPTOS_THROUGHPUT (0x08)</CODE></LI>
     * <LI><CODE>IPTOS_LOWDELAY (0x10)</CODE></LI>
     * </UL>
     * The lbst low order bit is blwbys ignored bs this
     * corresponds to the MBZ (must be zero) bit.
     * <p>
     * Setting bits in the precedence field mby result in b
     * SocketException indicbting thbt the operbtion is not
     * permitted.
     * <p>
     * As RFC 1122 section 4.2.4.2 indicbtes, b complibnt TCP
     * implementbtion should, but is not required to, let bpplicbtion
     * chbnge the TOS field during the lifetime of b connection.
     * So whether the type-of-service field cbn be chbnged bfter the
     * TCP connection hbs been estbblished depends on the implementbtion
     * in the underlying plbtform. Applicbtions should not bssume thbt
     * they cbn chbnge the TOS field bfter the connection.
     * <p>
     * For Internet Protocol v6 {@code tc} is the vblue thbt
     * would be plbced into the sin6_flowinfo field of the IP hebder.
     *
     * @pbrbm tc        bn {@code int} vblue for the bitset.
     * @throws SocketException if there is bn error setting the
     * trbffic clbss or type-of-service
     * @since 1.4
     * @see #getTrbfficClbss
     * @see SocketOptions#IP_TOS
     */
    public void setTrbfficClbss(int tc) throws SocketException {
        if (tc < 0 || tc > 255)
            throw new IllegblArgumentException("tc is not in rbnge 0 -- 255");

        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.IP_TOS, tc);
    }

    /**
     * Gets trbffic clbss or type-of-service in the IP hebder
     * for pbckets sent from this Socket
     * <p>
     * As the underlying network implementbtion mby ignore the
     * trbffic clbss or type-of-service set using {@link #setTrbfficClbss(int)}
     * this method mby return b different vblue thbn wbs previously
     * set using the {@link #setTrbfficClbss(int)} method on this Socket.
     *
     * @return the trbffic clbss or type-of-service blrebdy set
     * @throws SocketException if there is bn error obtbining the
     * trbffic clbss or type-of-service vblue.
     * @since 1.4
     * @see #setTrbfficClbss(int)
     * @see SocketOptions#IP_TOS
     */
    public int getTrbfficClbss() throws SocketException {
        return ((Integer) (getImpl().getOption(SocketOptions.IP_TOS))).intVblue();
    }

    /**
     * Enbble/disbble the {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}
     * socket option.
     * <p>
     * When b TCP connection is closed the connection mby rembin
     * in b timeout stbte for b period of time bfter the connection
     * is closed (typicblly known bs the {@code TIME_WAIT} stbte
     * or {@code 2MSL} wbit stbte).
     * For bpplicbtions using b well known socket bddress or port
     * it mby not be possible to bind b socket to the required
     * {@code SocketAddress} if there is b connection in the
     * timeout stbte involving the socket bddress or port.
     * <p>
     * Enbbling {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}
     * prior to binding the socket using {@link #bind(SocketAddress)} bllows
     * the socket to be bound even though b previous connection is in b timeout
     * stbte.
     * <p>
     * When b {@code Socket} is crebted the initibl setting
     * of {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is disbbled.
     * <p>
     * The behbviour when {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is
     * enbbled or disbbled bfter b socket is bound (See {@link #isBound()})
     * is not defined.
     *
     * @pbrbm on  whether to enbble or disbble the socket option
     * @exception SocketException if bn error occurs enbbling or
     *            disbbling the {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}
     *            socket option, or the socket is closed.
     * @since 1.4
     * @see #getReuseAddress()
     * @see #bind(SocketAddress)
     * @see #isClosed()
     * @see #isBound()
     */
    public void setReuseAddress(boolebn on) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_REUSEADDR, Boolebn.vblueOf(on));
    }

    /**
     * Tests if {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is enbbled.
     *
     * @return b {@code boolebn} indicbting whether or not
     *         {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is enbbled.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     * @since   1.4
     * @see #setReuseAddress(boolebn)
     */
    public boolebn getReuseAddress() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return ((Boolebn) (getImpl().getOption(SocketOptions.SO_REUSEADDR))).boolebnVblue();
    }

    /**
     * Closes this socket.
     * <p>
     * Any threbd currently blocked in bn I/O operbtion upon this socket
     * will throw b {@link SocketException}.
     * <p>
     * Once b socket hbs been closed, it is not bvbilbble for further networking
     * use (i.e. cbn't be reconnected or rebound). A new socket needs to be
     * crebted.
     *
     * <p> Closing this socket will blso close the socket's
     * {@link jbvb.io.InputStrebm InputStrebm} bnd
     * {@link jbvb.io.OutputStrebm OutputStrebm}.
     *
     * <p> If this socket hbs bn bssocibted chbnnel then the chbnnel is closed
     * bs well.
     *
     * @exception  IOException  if bn I/O error occurs when closing this socket.
     * @revised 1.4
     * @spec JSR-51
     * @see #isClosed
     */
    public synchronized void close() throws IOException {
        synchronized(closeLock) {
            if (isClosed())
                return;
            if (crebted)
                impl.close();
            closed = true;
        }
    }

    /**
     * Plbces the input strebm for this socket bt "end of strebm".
     * Any dbtb sent to the input strebm side of the socket is bcknowledged
     * bnd then silently discbrded.
     * <p>
     * If you rebd from b socket input strebm bfter invoking this method on the
     * socket, the strebm's {@code bvbilbble} method will return 0, bnd its
     * {@code rebd} methods will return {@code -1} (end of strebm).
     *
     * @exception IOException if bn I/O error occurs when shutting down this
     * socket.
     *
     * @since 1.3
     * @see jbvb.net.Socket#shutdownOutput()
     * @see jbvb.net.Socket#close()
     * @see jbvb.net.Socket#setSoLinger(boolebn, int)
     * @see #isInputShutdown
     */
    public void shutdownInput() throws IOException
    {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!isConnected())
            throw new SocketException("Socket is not connected");
        if (isInputShutdown())
            throw new SocketException("Socket input is blrebdy shutdown");
        getImpl().shutdownInput();
        shutIn = true;
    }

    /**
     * Disbbles the output strebm for this socket.
     * For b TCP socket, bny previously written dbtb will be sent
     * followed by TCP's normbl connection terminbtion sequence.
     *
     * If you write to b socket output strebm bfter invoking
     * shutdownOutput() on the socket, the strebm will throw
     * bn IOException.
     *
     * @exception IOException if bn I/O error occurs when shutting down this
     * socket.
     *
     * @since 1.3
     * @see jbvb.net.Socket#shutdownInput()
     * @see jbvb.net.Socket#close()
     * @see jbvb.net.Socket#setSoLinger(boolebn, int)
     * @see #isOutputShutdown
     */
    public void shutdownOutput() throws IOException
    {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!isConnected())
            throw new SocketException("Socket is not connected");
        if (isOutputShutdown())
            throw new SocketException("Socket output is blrebdy shutdown");
        getImpl().shutdownOutput();
        shutOut = true;
    }

    /**
     * Converts this socket to b {@code String}.
     *
     * @return  b string representbtion of this socket.
     */
    public String toString() {
        try {
            if (isConnected())
                return "Socket[bddr=" + getImpl().getInetAddress() +
                    ",port=" + getImpl().getPort() +
                    ",locblport=" + getImpl().getLocblPort() + "]";
        } cbtch (SocketException e) {
        }
        return "Socket[unconnected]";
    }

    /**
     * Returns the connection stbte of the socket.
     * <p>
     * Note: Closing b socket doesn't clebr its connection stbte, which mebns
     * this method will return {@code true} for b closed socket
     * (see {@link #isClosed()}) if it wbs successfuly connected prior
     * to being closed.
     *
     * @return true if the socket wbs successfuly connected to b server
     * @since 1.4
     */
    public boolebn isConnected() {
        // Before 1.3 Sockets were blwbys connected during crebtion
        return connected || oldImpl;
    }

    /**
     * Returns the binding stbte of the socket.
     * <p>
     * Note: Closing b socket doesn't clebr its binding stbte, which mebns
     * this method will return {@code true} for b closed socket
     * (see {@link #isClosed()}) if it wbs successfuly bound prior
     * to being closed.
     *
     * @return true if the socket wbs successfuly bound to bn bddress
     * @since 1.4
     * @see #bind
     */
    public boolebn isBound() {
        // Before 1.3 Sockets were blwbys bound during crebtion
        return bound || oldImpl;
    }

    /**
     * Returns the closed stbte of the socket.
     *
     * @return true if the socket hbs been closed
     * @since 1.4
     * @see #close
     */
    public boolebn isClosed() {
        synchronized(closeLock) {
            return closed;
        }
    }

    /**
     * Returns whether the rebd-hblf of the socket connection is closed.
     *
     * @return true if the input of the socket hbs been shutdown
     * @since 1.4
     * @see #shutdownInput
     */
    public boolebn isInputShutdown() {
        return shutIn;
    }

    /**
     * Returns whether the write-hblf of the socket connection is closed.
     *
     * @return true if the output of the socket hbs been shutdown
     * @since 1.4
     * @see #shutdownOutput
     */
    public boolebn isOutputShutdown() {
        return shutOut;
    }

    /**
     * The fbctory for bll client sockets.
     */
    privbte stbtic SocketImplFbctory fbctory = null;

    /**
     * Sets the client socket implementbtion fbctory for the
     * bpplicbtion. The fbctory cbn be specified only once.
     * <p>
     * When bn bpplicbtion crebtes b new client socket, the socket
     * implementbtion fbctory's {@code crebteSocketImpl} method is
     * cblled to crebte the bctubl socket implementbtion.
     * <p>
     * Pbssing {@code null} to the method is b no-op unless the fbctory
     * wbs blrebdy set.
     * <p>If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm      fbc   the desired fbctory.
     * @exception  IOException  if bn I/O error occurs when setting the
     *               socket fbctory.
     * @exception  SocketException  if the fbctory is blrebdy defined.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't bllow the operbtion.
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        SecurityMbnbger#checkSetFbctory
     */
    public stbtic synchronized void setSocketImplFbctory(SocketImplFbctory fbc)
        throws IOException
    {
        if (fbctory != null) {
            throw new SocketException("fbctory blrebdy defined");
        }
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSetFbctory();
        }
        fbctory = fbc;
    }

    /**
     * Sets performbnce preferences for this socket.
     *
     * <p> Sockets use the TCP/IP protocol by defbult.  Some implementbtions
     * mby offer blternbtive protocols which hbve different performbnce
     * chbrbcteristics thbn TCP/IP.  This method bllows the bpplicbtion to
     * express its own preferences bs to how these trbdeoffs should be mbde
     * when the implementbtion chooses from the bvbilbble protocols.
     *
     * <p> Performbnce preferences bre described by three integers
     * whose vblues indicbte the relbtive importbnce of short connection time,
     * low lbtency, bnd high bbndwidth.  The bbsolute vblues of the integers
     * bre irrelevbnt; in order to choose b protocol the vblues bre simply
     * compbred, with lbrger vblues indicbting stronger preferences. Negbtive
     * vblues represent b lower priority thbn positive vblues. If the
     * bpplicbtion prefers short connection time over both low lbtency bnd high
     * bbndwidth, for exbmple, then it could invoke this method with the vblues
     * {@code (1, 0, 0)}.  If the bpplicbtion prefers high bbndwidth bbove low
     * lbtency, bnd low lbtency bbove short connection time, then it could
     * invoke this method with the vblues {@code (0, 1, 2)}.
     *
     * <p> Invoking this method bfter this socket hbs been connected
     * will hbve no effect.
     *
     * @pbrbm  connectionTime
     *         An {@code int} expressing the relbtive importbnce of b short
     *         connection time
     *
     * @pbrbm  lbtency
     *         An {@code int} expressing the relbtive importbnce of low
     *         lbtency
     *
     * @pbrbm  bbndwidth
     *         An {@code int} expressing the relbtive importbnce of high
     *         bbndwidth
     *
     * @since 1.5
     */
    public void setPerformbncePreferences(int connectionTime,
                                          int lbtency,
                                          int bbndwidth)
    {
        /* Not implemented yet */
    }


    /**
     * Sets the vblue of b socket option.
     *
     * @pbrbm nbme The socket option
     * @pbrbm vblue The vblue of the socket option. A vblue of {@code null}
     *              mby be vblid for some options.
     * @return this Socket
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IllegblArgumentException if the vblue is not vblid for
     *         the option.
     *
     * @throws IOException if bn I/O error occurs, or if the socket is closed.
     *
     * @throws NullPointerException if nbme is {@code null}
     *
     * @throws SecurityException if b security mbnbger is set bnd if the socket
     *         option requires b security permission bnd if the cbller does
     *         not hbve the required permission.
     *         {@link jbvb.net.StbndbrdSocketOptions StbndbrdSocketOptions}
     *         do not require bny security permission.
     *
     * @since 1.9
     */
    public <T> Socket setOption(SocketOption<T> nbme, T vblue) throws IOException {
        getImpl().setOption(nbme, vblue);
        return this;
    }

    /**
     * Returns the vblue of b socket option.
     *
     * @pbrbm nbme The socket option
     *
     * @return The vblue of the socket option.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IOException if bn I/O error occurs, or if the socket is closed.
     *
     * @throws NullPointerException if nbme is {@code null}
     *
     * @throws SecurityException if b security mbnbger is set bnd if the socket
     *         option requires b security permission bnd if the cbller does
     *         not hbve the required permission.
     *         {@link jbvb.net.StbndbrdSocketOptions StbndbrdSocketOptions}
     *         do not require bny security permission.
     *
     * @since 1.9
     */
    @SuppressWbrnings("unchecked")
    public <T> T getOption(SocketOption<T> nbme) throws IOException {
        return getImpl().getOption(nbme);
    }

    privbte stbtic Set<SocketOption<?>> options;
    privbte stbtic boolebn optionsSet = fblse;

    /**
     * Returns b set of the socket options supported by this socket.
     *
     * This method will continue to return the set of options even bfter
     * the socket hbs been closed.
     *
     * @return A set of the socket options supported by this socket. This set
     *         mby be empty if the socket's SocketImpl cbnnot be crebted.
     *
     * @since 1.9
     */
    public Set<SocketOption<?>> supportedOptions() {
        synchronized (Socket.clbss) {
            if (optionsSet) {
                return options;
            }
            try {
                SocketImpl impl = getImpl();
                options = Collections.unmodifibbleSet(impl.supportedOptions());
            } cbtch (IOException e) {
                options = Collections.emptySet();
            }
            optionsSet = true;
            return options;
        }
    }
}
