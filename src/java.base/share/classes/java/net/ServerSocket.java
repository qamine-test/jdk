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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.nio.chbnnels.ServerSocketChbnnel;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Set;
import jbvb.util.Collections;

/**
 * This clbss implements server sockets. A server socket wbits for
 * requests to come in over the network. It performs some operbtion
 * bbsed on thbt request, bnd then possibly returns b result to the requester.
 * <p>
 * The bctubl work of the server socket is performed by bn instbnce
 * of the {@code SocketImpl} clbss. An bpplicbtion cbn
 * chbnge the socket fbctory thbt crebtes the socket
 * implementbtion to configure itself to crebte sockets
 * bppropribte to the locbl firewbll.
 *
 * @buthor  unbscribed
 * @see     jbvb.net.SocketImpl
 * @see     jbvb.net.ServerSocket#setSocketFbctory(jbvb.net.SocketImplFbctory)
 * @see     jbvb.nio.chbnnels.ServerSocketChbnnel
 * @since   1.0
 */
public
clbss ServerSocket implements jbvb.io.Closebble {
    /**
     * Vbrious stbtes of this socket.
     */
    privbte boolebn crebted = fblse;
    privbte boolebn bound = fblse;
    privbte boolebn closed = fblse;
    privbte Object closeLock = new Object();

    /**
     * The implementbtion of this Socket.
     */
    privbte SocketImpl impl;

    /**
     * Are we using bn older SocketImpl?
     */
    privbte boolebn oldImpl = fblse;

    /**
     * Pbckbge-privbte constructor to crebte b ServerSocket bssocibted with
     * the given SocketImpl.
     */
    ServerSocket(SocketImpl impl) {
        this.impl = impl;
        impl.setServerSocket(this);
    }

    /**
     * Crebtes bn unbound server socket.
     *
     * @exception IOException IO error when opening the socket.
     * @revised 1.4
     */
    public ServerSocket() throws IOException {
        setImpl();
    }

    /**
     * Crebtes b server socket, bound to the specified port. A port number
     * of {@code 0} mebns thbt the port number is butombticblly
     * bllocbted, typicblly from bn ephemerbl port rbnge. This port
     * number cbn then be retrieved by cblling {@link #getLocblPort getLocblPort}.
     * <p>
     * The mbximum queue length for incoming connection indicbtions (b
     * request to connect) is set to {@code 50}. If b connection
     * indicbtion brrives when the queue is full, the connection is refused.
     * <p>
     * If the bpplicbtion hbs specified b server socket fbctory, thbt
     * fbctory's {@code crebteSocketImpl} method is cblled to crebte
     * the bctubl socket implementbtion. Otherwise b "plbin" socket is crebted.
     * <p>
     * If there is b security mbnbger,
     * its {@code checkListen} method is cblled
     * with the {@code port} brgument
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     *
     * @pbrbm      port  the port number, or {@code 0} to use b port
     *                   number thbt is butombticblly bllocbted.
     *
     * @exception  IOException  if bn I/O error occurs when opening the socket.
     * @exception  SecurityException
     * if b security mbnbger exists bnd its {@code checkListen}
     * method doesn't bllow the operbtion.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     *
     * @see        jbvb.net.SocketImpl
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        jbvb.net.ServerSocket#setSocketFbctory(jbvb.net.SocketImplFbctory)
     * @see        SecurityMbnbger#checkListen
     */
    public ServerSocket(int port) throws IOException {
        this(port, 50, null);
    }

    /**
     * Crebtes b server socket bnd binds it to the specified locbl port
     * number, with the specified bbcklog.
     * A port number of {@code 0} mebns thbt the port number is
     * butombticblly bllocbted, typicblly from bn ephemerbl port rbnge.
     * This port number cbn then be retrieved by cblling
     * {@link #getLocblPort getLocblPort}.
     * <p>
     * The mbximum queue length for incoming connection indicbtions (b
     * request to connect) is set to the {@code bbcklog} pbrbmeter. If
     * b connection indicbtion brrives when the queue is full, the
     * connection is refused.
     * <p>
     * If the bpplicbtion hbs specified b server socket fbctory, thbt
     * fbctory's {@code crebteSocketImpl} method is cblled to crebte
     * the bctubl socket implementbtion. Otherwise b "plbin" socket is crebted.
     * <p>
     * If there is b security mbnbger,
     * its {@code checkListen} method is cblled
     * with the {@code port} brgument
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * The {@code bbcklog} brgument is the requested mbximum number of
     * pending connections on the socket. Its exbct sembntics bre implementbtion
     * specific. In pbrticulbr, bn implementbtion mby impose b mbximum length
     * or mby choose to ignore the pbrbmeter bltogther. The vblue provided
     * should be grebter thbn {@code 0}. If it is less thbn or equbl to
     * {@code 0}, then bn implementbtion specific defbult will be used.
     *
     * @pbrbm      port     the port number, or {@code 0} to use b port
     *                      number thbt is butombticblly bllocbted.
     * @pbrbm      bbcklog  requested mbximum length of the queue of incoming
     *                      connections.
     *
     * @exception  IOException  if bn I/O error occurs when opening the socket.
     * @exception  SecurityException
     * if b security mbnbger exists bnd its {@code checkListen}
     * method doesn't bllow the operbtion.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     *
     * @see        jbvb.net.SocketImpl
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        jbvb.net.ServerSocket#setSocketFbctory(jbvb.net.SocketImplFbctory)
     * @see        SecurityMbnbger#checkListen
     */
    public ServerSocket(int port, int bbcklog) throws IOException {
        this(port, bbcklog, null);
    }

    /**
     * Crebte b server with the specified port, listen bbcklog, bnd
     * locbl IP bddress to bind to.  The <i>bindAddr</i> brgument
     * cbn be used on b multi-homed host for b ServerSocket thbt
     * will only bccept connect requests to one of its bddresses.
     * If <i>bindAddr</i> is null, it will defbult bccepting
     * connections on bny/bll locbl bddresses.
     * The port must be between 0 bnd 65535, inclusive.
     * A port number of {@code 0} mebns thbt the port number is
     * butombticblly bllocbted, typicblly from bn ephemerbl port rbnge.
     * This port number cbn then be retrieved by cblling
     * {@link #getLocblPort getLocblPort}.
     *
     * <P>If there is b security mbnbger, this method
     * cblls its {@code checkListen} method
     * with the {@code port} brgument
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * The {@code bbcklog} brgument is the requested mbximum number of
     * pending connections on the socket. Its exbct sembntics bre implementbtion
     * specific. In pbrticulbr, bn implementbtion mby impose b mbximum length
     * or mby choose to ignore the pbrbmeter bltogther. The vblue provided
     * should be grebter thbn {@code 0}. If it is less thbn or equbl to
     * {@code 0}, then bn implementbtion specific defbult will be used.
     *
     * @pbrbm port  the port number, or {@code 0} to use b port
     *              number thbt is butombticblly bllocbted.
     * @pbrbm bbcklog requested mbximum length of the queue of incoming
     *                connections.
     * @pbrbm bindAddr the locbl InetAddress the server will bind to
     *
     * @throws  SecurityException if b security mbnbger exists bnd
     * its {@code checkListen} method doesn't bllow the operbtion.
     *
     * @throws  IOException if bn I/O error occurs when opening the socket.
     * @exception  IllegblArgumentException if the port pbrbmeter is outside
     *             the specified rbnge of vblid port vblues, which is between
     *             0 bnd 65535, inclusive.
     *
     * @see SocketOptions
     * @see SocketImpl
     * @see SecurityMbnbger#checkListen
     * @since   1.1
     */
    public ServerSocket(int port, int bbcklog, InetAddress bindAddr) throws IOException {
        setImpl();
        if (port < 0 || port > 0xFFFF)
            throw new IllegblArgumentException(
                       "Port vblue out of rbnge: " + port);
        if (bbcklog < 1)
          bbcklog = 50;
        try {
            bind(new InetSocketAddress(bindAddr, port), bbcklog);
        } cbtch(SecurityException e) {
            close();
            throw e;
        } cbtch(IOException e) {
            close();
            throw e;
        }
    }

    /**
     * Get the {@code SocketImpl} bttbched to this socket, crebting
     * it if necessbry.
     *
     * @return  the {@code SocketImpl} bttbched to thbt ServerSocket.
     * @throws SocketException if crebtion fbils.
     * @since 1.4
     */
    SocketImpl getImpl() throws SocketException {
        if (!crebted)
            crebteImpl();
        return impl;
    }

    privbte void checkOldImpl() {
        if (impl == null)
            return;
        // SocketImpl.connect() is b protected method, therefore we need to use
        // getDeclbredMethod, therefore we need permission to bccess the member
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                    public Void run() throws NoSuchMethodException {
                        impl.getClbss().getDeclbredMethod("connect",
                                                          SocketAddress.clbss,
                                                          int.clbss);
                        return null;
                    }
                });
        } cbtch (jbvb.security.PrivilegedActionException e) {
            oldImpl = true;
        }
    }

    privbte void setImpl() {
        if (fbctory != null) {
            impl = fbctory.crebteSocketImpl();
            checkOldImpl();
        } else {
            // No need to do b checkOldImpl() here, we know it's bn up to dbte
            // SocketImpl!
            impl = new SocksSocketImpl();
        }
        if (impl != null)
            impl.setServerSocket(this);
    }

    /**
     * Crebtes the socket implementbtion.
     *
     * @throws IOException if crebtion fbils
     * @since 1.4
     */
    void crebteImpl() throws SocketException {
        if (impl == null)
            setImpl();
        try {
            impl.crebte(true);
            crebted = true;
        } cbtch (IOException e) {
            throw new SocketException(e.getMessbge());
        }
    }

    /**
     *
     * Binds the {@code ServerSocket} to b specific bddress
     * (IP bddress bnd port number).
     * <p>
     * If the bddress is {@code null}, then the system will pick up
     * bn ephemerbl port bnd b vblid locbl bddress to bind the socket.
     *
     * @pbrbm   endpoint        The IP bddress bnd port number to bind to.
     * @throws  IOException if the bind operbtion fbils, or if the socket
     *                     is blrebdy bound.
     * @throws  SecurityException       if b {@code SecurityMbnbger} is present bnd
     * its {@code checkListen} method doesn't bllow the operbtion.
     * @throws  IllegblArgumentException if endpoint is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     */
    public void bind(SocketAddress endpoint) throws IOException {
        bind(endpoint, 50);
    }

    /**
     *
     * Binds the {@code ServerSocket} to b specific bddress
     * (IP bddress bnd port number).
     * <p>
     * If the bddress is {@code null}, then the system will pick up
     * bn ephemerbl port bnd b vblid locbl bddress to bind the socket.
     * <P>
     * The {@code bbcklog} brgument is the requested mbximum number of
     * pending connections on the socket. Its exbct sembntics bre implementbtion
     * specific. In pbrticulbr, bn implementbtion mby impose b mbximum length
     * or mby choose to ignore the pbrbmeter bltogther. The vblue provided
     * should be grebter thbn {@code 0}. If it is less thbn or equbl to
     * {@code 0}, then bn implementbtion specific defbult will be used.
     * @pbrbm   endpoint        The IP bddress bnd port number to bind to.
     * @pbrbm   bbcklog         requested mbximum length of the queue of
     *                          incoming connections.
     * @throws  IOException if the bind operbtion fbils, or if the socket
     *                     is blrebdy bound.
     * @throws  SecurityException       if b {@code SecurityMbnbger} is present bnd
     * its {@code checkListen} method doesn't bllow the operbtion.
     * @throws  IllegblArgumentException if endpoint is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     */
    public void bind(SocketAddress endpoint, int bbcklog) throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!oldImpl && isBound())
            throw new SocketException("Alrebdy bound");
        if (endpoint == null)
            endpoint = new InetSocketAddress(0);
        if (!(endpoint instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");
        InetSocketAddress epoint = (InetSocketAddress) endpoint;
        if (epoint.isUnresolved())
            throw new SocketException("Unresolved bddress");
        if (bbcklog < 1)
          bbcklog = 50;
        try {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null)
                security.checkListen(epoint.getPort());
            getImpl().bind(epoint.getAddress(), epoint.getPort());
            getImpl().listen(bbcklog);
            bound = true;
        } cbtch(SecurityException e) {
            bound = fblse;
            throw e;
        } cbtch(IOException e) {
            bound = fblse;
            throw e;
        }
    }

    /**
     * Returns the locbl bddress of this server socket.
     * <p>
     * If the socket wbs bound prior to being {@link #close closed},
     * then this method will continue to return the locbl bddress
     * bfter the socket is closed.
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * the {@link InetAddress#getLoopbbckAddress loopbbck} bddress is returned.
     *
     * @return  the bddress to which this socket is bound,
     *          or the loopbbck bddress if denied by the security mbnbger,
     *          or {@code null} if the socket is unbound.
     *
     * @see SecurityMbnbger#checkConnect
     */
    public InetAddress getInetAddress() {
        if (!isBound())
            return null;
        try {
            InetAddress in = getImpl().getInetAddress();
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkConnect(in.getHostAddress(), -1);
            return in;
        } cbtch (SecurityException e) {
            return InetAddress.getLoopbbckAddress();
        } cbtch (SocketException e) {
            // nothing
            // If we're bound, the impl hbs been crebted
            // so we shouldn't get here
        }
        return null;
    }

    /**
     * Returns the port number on which this socket is listening.
     * <p>
     * If the socket wbs bound prior to being {@link #close closed},
     * then this method will continue to return the port number
     * bfter the socket is closed.
     *
     * @return  the port number to which this socket is listening or
     *          -1 if the socket is not bound yet.
     */
    public int getLocblPort() {
        if (!isBound())
            return -1;
        try {
            return getImpl().getLocblPort();
        } cbtch (SocketException e) {
            // nothing
            // If we're bound, the impl hbs been crebted
            // so we shouldn't get here
        }
        return -1;
    }

    /**
     * Returns the bddress of the endpoint this socket is bound to.
     * <p>
     * If the socket wbs bound prior to being {@link #close closed},
     * then this method will continue to return the bddress of the endpoint
     * bfter the socket is closed.
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * b {@code SocketAddress} representing the
     * {@link InetAddress#getLoopbbckAddress loopbbck} bddress bnd the locbl
     * port to which the socket is bound is returned.
     *
     * @return b {@code SocketAddress} representing the locbl endpoint of
     *         this socket, or b {@code SocketAddress} representing the
     *         loopbbck bddress if denied by the security mbnbger,
     *         or {@code null} if the socket is not bound yet.
     *
     * @see #getInetAddress()
     * @see #getLocblPort()
     * @see #bind(SocketAddress)
     * @see SecurityMbnbger#checkConnect
     * @since 1.4
     */

    public SocketAddress getLocblSocketAddress() {
        if (!isBound())
            return null;
        return new InetSocketAddress(getInetAddress(), getLocblPort());
    }

    /**
     * Listens for b connection to be mbde to this socket bnd bccepts
     * it. The method blocks until b connection is mbde.
     *
     * <p>A new Socket {@code s} is crebted bnd, if there
     * is b security mbnbger,
     * the security mbnbger's {@code checkAccept} method is cblled
     * with {@code s.getInetAddress().getHostAddress()} bnd
     * {@code s.getPort()}
     * bs its brguments to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @exception  IOException  if bn I/O error occurs when wbiting for b
     *               connection.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkAccept} method doesn't bllow the operbtion.
     * @exception  SocketTimeoutException if b timeout wbs previously set with setSoTimeout bnd
     *             the timeout hbs been rebched.
     * @exception  jbvb.nio.chbnnels.IllegblBlockingModeException
     *             if this socket hbs bn bssocibted chbnnel, the chbnnel is in
     *             non-blocking mode, bnd there is no connection rebdy to be
     *             bccepted
     *
     * @return the new Socket
     * @see SecurityMbnbger#checkAccept
     * @revised 1.4
     * @spec JSR-51
     */
    public Socket bccept() throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!isBound())
            throw new SocketException("Socket is not bound yet");
        Socket s = new Socket((SocketImpl) null);
        implAccept(s);
        return s;
    }

    /**
     * Subclbsses of ServerSocket use this method to override bccept()
     * to return their own subclbss of socket.  So b FooServerSocket
     * will typicblly hbnd this method bn <i>empty</i> FooSocket.  On
     * return from implAccept the FooSocket will be connected to b client.
     *
     * @pbrbm s the Socket
     * @throws jbvb.nio.chbnnels.IllegblBlockingModeException
     *         if this socket hbs bn bssocibted chbnnel,
     *         bnd the chbnnel is in non-blocking mode
     * @throws IOException if bn I/O error occurs when wbiting
     * for b connection.
     * @since   1.1
     * @revised 1.4
     * @spec JSR-51
     */
    protected finbl void implAccept(Socket s) throws IOException {
        SocketImpl si = null;
        try {
            if (s.impl == null)
              s.setImpl();
            else {
                s.impl.reset();
            }
            si = s.impl;
            s.impl = null;
            si.bddress = new InetAddress();
            si.fd = new FileDescriptor();
            getImpl().bccept(si);

            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkAccept(si.getInetAddress().getHostAddress(),
                                     si.getPort());
            }
        } cbtch (IOException e) {
            if (si != null)
                si.reset();
            s.impl = si;
            throw e;
        } cbtch (SecurityException e) {
            if (si != null)
                si.reset();
            s.impl = si;
            throw e;
        }
        s.impl = si;
        s.postAccept();
    }

    /**
     * Closes this socket.
     *
     * Any threbd currently blocked in {@link #bccept()} will throw
     * b {@link SocketException}.
     *
     * <p> If this socket hbs bn bssocibted chbnnel then the chbnnel is closed
     * bs well.
     *
     * @exception  IOException  if bn I/O error occurs when closing the socket.
     * @revised 1.4
     * @spec JSR-51
     */
    public void close() throws IOException {
        synchronized(closeLock) {
            if (isClosed())
                return;
            if (crebted)
                impl.close();
            closed = true;
        }
    }

    /**
     * Returns the unique {@link jbvb.nio.chbnnels.ServerSocketChbnnel} object
     * bssocibted with this socket, if bny.
     *
     * <p> A server socket will hbve b chbnnel if, bnd only if, the chbnnel
     * itself wbs crebted vib the {@link
     * jbvb.nio.chbnnels.ServerSocketChbnnel#open ServerSocketChbnnel.open}
     * method.
     *
     * @return  the server-socket chbnnel bssocibted with this socket,
     *          or {@code null} if this socket wbs not crebted
     *          for b chbnnel
     *
     * @since 1.4
     * @spec JSR-51
     */
    public ServerSocketChbnnel getChbnnel() {
        return null;
    }

    /**
     * Returns the binding stbte of the ServerSocket.
     *
     * @return true if the ServerSocket successfully bound to bn bddress
     * @since 1.4
     */
    public boolebn isBound() {
        // Before 1.3 ServerSockets were blwbys bound during crebtion
        return bound || oldImpl;
    }

    /**
     * Returns the closed stbte of the ServerSocket.
     *
     * @return true if the socket hbs been closed
     * @since 1.4
     */
    public boolebn isClosed() {
        synchronized(closeLock) {
            return closed;
        }
    }

    /**
     * Enbble/disbble {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT} with the
     * specified timeout, in milliseconds.  With this option set to b non-zero
     * timeout, b cbll to bccept() for this ServerSocket
     * will block for only this bmount of time.  If the timeout expires,
     * b <B>jbvb.net.SocketTimeoutException</B> is rbised, though the
     * ServerSocket is still vblid.  The option <B>must</B> be enbbled
     * prior to entering the blocking operbtion to hbve effect.  The
     * timeout must be {@code > 0}.
     * A timeout of zero is interpreted bs bn infinite timeout.
     * @pbrbm timeout the specified timeout, in milliseconds
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs b TCP error.
     * @since   1.1
     * @see #getSoTimeout()
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_TIMEOUT, timeout);
    }

    /**
     * Retrieve setting for {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}.
     * 0 returns implies thbt the option is disbbled (i.e., timeout of infinity).
     * @return the {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT} vblue
     * @exception IOException if bn I/O error occurs
     * @since   1.1
     * @see #setSoTimeout(int)
     */
    public synchronized int getSoTimeout() throws IOException {
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
     * Enbbling {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} prior to
     * binding the socket using {@link #bind(SocketAddress)} bllows the socket
     * to be bound even though b previous connection is in b timeout stbte.
     * <p>
     * When b {@code ServerSocket} is crebted the initibl setting
     * of {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is not defined.
     * Applicbtions cbn use {@link #getReuseAddress()} to determine the initibl
     * setting of {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}.
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
     * @see #isBound()
     * @see #isClosed()
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
     * Returns the implementbtion bddress bnd implementbtion port of
     * this socket bs b {@code String}.
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * bn {@code InetAddress} representing the
     * {@link InetAddress#getLoopbbckAddress loopbbck} bddress is returned bs
     * the implementbtion bddress.
     *
     * @return  b string representbtion of this socket.
     */
    public String toString() {
        if (!isBound())
            return "ServerSocket[unbound]";
        InetAddress in;
        if (System.getSecurityMbnbger() != null)
            in = InetAddress.getLoopbbckAddress();
        else
            in = impl.getInetAddress();
        return "ServerSocket[bddr=" + in +
                ",locblport=" + impl.getLocblPort()  + "]";
    }

    void setBound() {
        bound = true;
    }

    void setCrebted() {
        crebted = true;
    }

    /**
     * The fbctory for bll server sockets.
     */
    privbte stbtic SocketImplFbctory fbctory = null;

    /**
     * Sets the server socket implementbtion fbctory for the
     * bpplicbtion. The fbctory cbn be specified only once.
     * <p>
     * When bn bpplicbtion crebtes b new server socket, the socket
     * implementbtion fbctory's {@code crebteSocketImpl} method is
     * cblled to crebte the bctubl socket implementbtion.
     * <p>
     * Pbssing {@code null} to the method is b no-op unless the fbctory
     * wbs blrebdy set.
     * <p>
     * If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm      fbc   the desired fbctory.
     * @exception  IOException  if bn I/O error occurs when setting the
     *               socket fbctory.
     * @exception  SocketException  if the fbctory hbs blrebdy been defined.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't bllow the operbtion.
     * @see        jbvb.net.SocketImplFbctory#crebteSocketImpl()
     * @see        SecurityMbnbger#checkSetFbctory
     */
    public stbtic synchronized void setSocketFbctory(SocketImplFbctory fbc) throws IOException {
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
     * Sets b defbult proposed vblue for the
     * {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option for sockets
     * bccepted from this {@code ServerSocket}. The vblue bctublly set
     * in the bccepted socket must be determined by cblling
     * {@link Socket#getReceiveBufferSize()} bfter the socket
     * is returned by {@link #bccept()}.
     * <p>
     * The vblue of {@link SocketOptions#SO_RCVBUF SO_RCVBUF} is used both to
     * set the size of the internbl socket receive buffer, bnd to set the size
     * of the TCP receive window thbt is bdvertized to the remote peer.
     * <p>
     * It is possible to chbnge the vblue subsequently, by cblling
     * {@link Socket#setReceiveBufferSize(int)}. However, if the bpplicbtion
     * wishes to bllow b receive window lbrger thbn 64K bytes, bs defined by RFC1323
     * then the proposed vblue must be set in the ServerSocket <B>before</B>
     * it is bound to b locbl bddress. This implies, thbt the ServerSocket must be
     * crebted with the no-brgument constructor, then setReceiveBufferSize() must
     * be cblled bnd lbstly the ServerSocket is bound to bn bddress by cblling bind().
     * <p>
     * Fbilure to do this will not cbuse bn error, bnd the buffer size mby be set to the
     * requested vblue but the TCP receive window in sockets bccepted from
     * this ServerSocket will be no lbrger thbn 64K bytes.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs b TCP error.
     *
     * @pbrbm size the size to which to set the receive buffer
     * size. This vblue must be grebter thbn 0.
     *
     * @exception IllegblArgumentException if the
     * vblue is 0 or is negbtive.
     *
     * @since 1.4
     * @see #getReceiveBufferSize
     */
     public synchronized void setReceiveBufferSize (int size) throws SocketException {
        if (!(size > 0)) {
            throw new IllegblArgumentException("negbtive receive size");
        }
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_RCVBUF, size);
    }

    /**
     * Gets the vblue of the {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option
     * for this {@code ServerSocket}, thbt is the proposed buffer size thbt
     * will be used for Sockets bccepted from this {@code ServerSocket}.
     *
     * <p>Note, the vblue bctublly set in the bccepted socket is determined by
     * cblling {@link Socket#getReceiveBufferSize()}.
     * @return the vblue of the {@link SocketOptions#SO_RCVBUF SO_RCVBUF}
     *         option for this {@code Socket}.
     * @exception SocketException if there is bn error
     *            in the underlying protocol, such bs b TCP error.
     * @see #setReceiveBufferSize(int)
     * @since 1.4
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
     * Sets performbnce preferences for this ServerSocket.
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
     * compbred, with lbrger vblues indicbting stronger preferences.  If the
     * bpplicbtion prefers short connection time over both low lbtency bnd high
     * bbndwidth, for exbmple, then it could invoke this method with the vblues
     * {@code (1, 0, 0)}.  If the bpplicbtion prefers high bbndwidth bbove low
     * lbtency, bnd low lbtency bbove short connection time, then it could
     * invoke this method with the vblues {@code (0, 1, 2)}.
     *
     * <p> Invoking this method bfter this socket hbs been bound
     * will hbve no effect. This implies thbt in order to use this cbpbbility
     * requires the socket to be crebted with the no-brgument constructor.
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
     * @return this ServerSocket
     *
     * @throws UnsupportedOperbtionException if the server socket does not
     *         support the option.
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
    public <T> ServerSocket setOption(SocketOption<T> nbme, T vblue)
        throws IOException
    {
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
     * @throws UnsupportedOperbtionException if the server socket does not
     *         support the option.
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
    public <T> T getOption(SocketOption<T> nbme) throws IOException {
        return getImpl().getOption(nbme);
    }

    privbte stbtic Set<SocketOption<?>> options;
    privbte stbtic boolebn optionsSet = fblse;

    /**
     * Returns b set of the socket options supported by this server socket.
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
        synchronized (ServerSocket.clbss) {
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
