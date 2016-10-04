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
import jbvb.nio.chbnnels.DbtbgrbmChbnnel;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Set;
import jbvb.util.Collections;

/**
 * This clbss represents b socket for sending bnd receiving dbtbgrbm pbckets.
 *
 * <p>A dbtbgrbm socket is the sending or receiving point for b pbcket
 * delivery service. Ebch pbcket sent or received on b dbtbgrbm socket
 * is individublly bddressed bnd routed. Multiple pbckets sent from
 * one mbchine to bnother mby be routed differently, bnd mby brrive in
 * bny order.
 *
 * <p> Where possible, b newly constructed {@code DbtbgrbmSocket} hbs the
 * {@link SocketOptions#SO_BROADCAST SO_BROADCAST} socket option enbbled so bs
 * to bllow the trbnsmission of brobdcbst dbtbgrbms. In order to receive
 * brobdcbst pbckets b DbtbgrbmSocket should be bound to the wildcbrd bddress.
 * In some implementbtions, brobdcbst pbckets mby blso be received when
 * b DbtbgrbmSocket is bound to b more specific bddress.
 * <p>
 * Exbmple:
 * {@code
 *              DbtbgrbmSocket s = new DbtbgrbmSocket(null);
 *              s.bind(new InetSocketAddress(8888));
 * }
 * Which is equivblent to:
 * {@code
 *              DbtbgrbmSocket s = new DbtbgrbmSocket(8888);
 * }
 * Both cbses will crebte b DbtbgrbmSocket bble to receive brobdcbsts on
 * UDP port 8888.
 *
 * @buthor  Pbvbni Diwbnji
 * @see     jbvb.net.DbtbgrbmPbcket
 * @see     jbvb.nio.chbnnels.DbtbgrbmChbnnel
 * @since 1.0
 */
public
clbss DbtbgrbmSocket implements jbvb.io.Closebble {
    /**
     * Vbrious stbtes of this socket.
     */
    privbte boolebn crebted = fblse;
    privbte boolebn bound = fblse;
    privbte boolebn closed = fblse;
    privbte Object closeLock = new Object();

    /*
     * The implementbtion of this DbtbgrbmSocket.
     */
    DbtbgrbmSocketImpl impl;

    /**
     * Are we using bn older DbtbgrbmSocketImpl?
     */
    boolebn oldImpl = fblse;

    /*
     * Connection stbte:
     * ST_NOT_CONNECTED = socket not connected
     * ST_CONNECTED = socket connected
     * ST_CONNECTED_NO_IMPL = socket connected but not bt impl level
     */
    stbtic finbl int ST_NOT_CONNECTED = 0;
    stbtic finbl int ST_CONNECTED = 1;
    stbtic finbl int ST_CONNECTED_NO_IMPL = 2;

    int connectStbte = ST_NOT_CONNECTED;

    /*
     * Connected bddress & port
     */
    InetAddress connectedAddress = null;
    int connectedPort = -1;

    /**
     * Connects this socket to b remote socket bddress (IP bddress + port number).
     * Binds socket if not blrebdy bound.
     *
     * @pbrbm   bddress The remote bddress.
     * @pbrbm   port    The remote port
     * @throws  SocketException if binding the socket fbils.
     */
    privbte synchronized void connectInternbl(InetAddress bddress, int port) throws SocketException {
        if (port < 0 || port > 0xFFFF) {
            throw new IllegblArgumentException("connect: " + port);
        }
        if (bddress == null) {
            throw new IllegblArgumentException("connect: null bddress");
        }
        checkAddress (bddress, "connect");
        if (isClosed())
            return;
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            if (bddress.isMulticbstAddress()) {
                security.checkMulticbst(bddress);
            } else {
                security.checkConnect(bddress.getHostAddress(), port);
                security.checkAccept(bddress.getHostAddress(), port);
            }
        }

        if (!isBound())
          bind(new InetSocketAddress(0));

        // old impls do not support connect/disconnect
        if (oldImpl || (impl instbnceof AbstrbctPlbinDbtbgrbmSocketImpl &&
             ((AbstrbctPlbinDbtbgrbmSocketImpl)impl).nbtiveConnectDisbbled())) {
            connectStbte = ST_CONNECTED_NO_IMPL;
        } else {
            try {
                getImpl().connect(bddress, port);

                // socket is now connected by the impl
                connectStbte = ST_CONNECTED;
            } cbtch (SocketException se) {

                // connection will be emulbted by DbtbgrbmSocket
                connectStbte = ST_CONNECTED_NO_IMPL;
            }
        }

        connectedAddress = bddress;
        connectedPort = port;
    }


    /**
     * Constructs b dbtbgrbm socket bnd binds it to bny bvbilbble port
     * on the locbl host mbchine.  The socket will be bound to the
     * {@link InetAddress#isAnyLocblAddress wildcbrd} bddress,
     * bn IP bddress chosen by the kernel.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with 0 bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified locbl port.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     *
     * @see SecurityMbnbger#checkListen
     */
    public DbtbgrbmSocket() throws SocketException {
        this(new InetSocketAddress(0));
    }

    /**
     * Crebtes bn unbound dbtbgrbm socket with the specified
     * DbtbgrbmSocketImpl.
     *
     * @pbrbm impl bn instbnce of b <B>DbtbgrbmSocketImpl</B>
     *        the subclbss wishes to use on the DbtbgrbmSocket.
     * @since   1.4
     */
    protected DbtbgrbmSocket(DbtbgrbmSocketImpl impl) {
        if (impl == null)
            throw new NullPointerException();
        this.impl = impl;
        checkOldImpl();
    }

    /**
     * Crebtes b dbtbgrbm socket, bound to the specified locbl
     * socket bddress.
     * <p>
     * If, if the bddress is {@code null}, crebtes bn unbound socket.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with the port from the socket bddress
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm bindbddr locbl socket bddress to bind, or {@code null}
     *                 for bn unbound socket.
     *
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified locbl port.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     *
     * @see SecurityMbnbger#checkListen
     * @since   1.4
     */
    public DbtbgrbmSocket(SocketAddress bindbddr) throws SocketException {
        // crebte b dbtbgrbm socket.
        crebteImpl();
        if (bindbddr != null) {
            try {
                bind(bindbddr);
            } finblly {
                if (!isBound())
                    close();
            }
        }
    }

    /**
     * Constructs b dbtbgrbm socket bnd binds it to the specified port
     * on the locbl host mbchine.  The socket will be bound to the
     * {@link InetAddress#isAnyLocblAddress wildcbrd} bddress,
     * bn IP bddress chosen by the kernel.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with the {@code port} brgument
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm      port port to use.
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified locbl port.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     *
     * @see SecurityMbnbger#checkListen
     */
    public DbtbgrbmSocket(int port) throws SocketException {
        this(port, null);
    }

    /**
     * Crebtes b dbtbgrbm socket, bound to the specified locbl
     * bddress.  The locbl port must be between 0 bnd 65535 inclusive.
     * If the IP bddress is 0.0.0.0, the socket will be bound to the
     * {@link InetAddress#isAnyLocblAddress wildcbrd} bddress,
     * bn IP bddress chosen by the kernel.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with the {@code port} brgument
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm port locbl port to use
     * @pbrbm lbddr locbl bddress to bind
     *
     * @exception  SocketException  if the socket could not be opened,
     *               or the socket could not bind to the specified locbl port.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     *
     * @see SecurityMbnbger#checkListen
     * @since   1.1
     */
    public DbtbgrbmSocket(int port, InetAddress lbddr) throws SocketException {
        this(new InetSocketAddress(lbddr, port));
    }

    privbte void checkOldImpl() {
        if (impl == null)
            return;
        // DbtbgrbmSocketImpl.peekdbtb() is b protected method, therefore we need to use
        // getDeclbredMethod, therefore we need permission to bccess the member
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                    public Void run() throws NoSuchMethodException {
                        Clbss<?>[] cl = new Clbss<?>[1];
                        cl[0] = DbtbgrbmPbcket.clbss;
                        impl.getClbss().getDeclbredMethod("peekDbtb", cl);
                        return null;
                    }
                });
        } cbtch (jbvb.security.PrivilegedActionException e) {
            oldImpl = true;
        }
    }

    stbtic Clbss<?> implClbss = null;

    void crebteImpl() throws SocketException {
        if (impl == null) {
            if (fbctory != null) {
                impl = fbctory.crebteDbtbgrbmSocketImpl();
                checkOldImpl();
            } else {
                boolebn isMulticbst = (this instbnceof MulticbstSocket) ? true : fblse;
                impl = DefbultDbtbgrbmSocketImplFbctory.crebteDbtbgrbmSocketImpl(isMulticbst);

                checkOldImpl();
            }
        }
        // crebtes b udp socket
        impl.crebte();
        impl.setDbtbgrbmSocket(this);
        crebted = true;
    }

    /**
     * Get the {@code DbtbgrbmSocketImpl} bttbched to this socket,
     * crebting it if necessbry.
     *
     * @return  the {@code DbtbgrbmSocketImpl} bttbched to thbt
     *          DbtbgrbmSocket
     * @throws SocketException if crebtion fbils.
     * @since 1.4
     */
    DbtbgrbmSocketImpl getImpl() throws SocketException {
        if (!crebted)
            crebteImpl();
        return impl;
    }

    /**
     * Binds this DbtbgrbmSocket to b specific bddress bnd port.
     * <p>
     * If the bddress is {@code null}, then the system will pick up
     * bn ephemerbl port bnd b vblid locbl bddress to bind the socket.
     *
     * @pbrbm   bddr The bddress bnd port to bind to.
     * @throws  SocketException if bny error hbppens during the bind, or if the
     *          socket is blrebdy bound.
     * @throws  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if bddr is b SocketAddress subclbss
     *         not supported by this socket.
     * @since 1.4
     */
    public synchronized void bind(SocketAddress bddr) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (isBound())
            throw new SocketException("blrebdy bound");
        if (bddr == null)
            bddr = new InetSocketAddress(0);
        if (!(bddr instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type!");
        InetSocketAddress epoint = (InetSocketAddress) bddr;
        if (epoint.isUnresolved())
            throw new SocketException("Unresolved bddress");
        InetAddress ibddr = epoint.getAddress();
        int port = epoint.getPort();
        checkAddress(ibddr, "bind");
        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkListen(port);
        }
        try {
            getImpl().bind(port, ibddr);
        } cbtch (SocketException e) {
            getImpl().close();
            throw e;
        }
        bound = true;
    }

    void checkAddress (InetAddress bddr, String op) {
        if (bddr == null) {
            return;
        }
        if (!(bddr instbnceof Inet4Address || bddr instbnceof Inet6Address)) {
            throw new IllegblArgumentException(op + ": invblid bddress type");
        }
    }

    /**
     * Connects the socket to b remote bddress for this socket. When b
     * socket is connected to b remote bddress, pbckets mby only be
     * sent to or received from thbt bddress. By defbult b dbtbgrbm
     * socket is not connected.
     *
     * <p>If the remote destinbtion to which the socket is connected does not
     * exist, or is otherwise unrebchbble, bnd if bn ICMP destinbtion unrebchbble
     * pbcket hbs been received for thbt bddress, then b subsequent cbll to
     * send or receive mby throw b PortUnrebchbbleException. Note, there is no
     * gubrbntee thbt the exception will be thrown.
     *
     * <p> If b security mbnbger hbs been instblled then it is invoked to check
     * bccess to the remote bddress. Specificblly, if the given {@code bddress}
     * is b {@link InetAddress#isMulticbstAddress multicbst bddress},
     * the security mbnbger's {@link
     * jbvb.lbng.SecurityMbnbger#checkMulticbst(InetAddress)
     * checkMulticbst} method is invoked with the given {@code bddress}.
     * Otherwise, the security mbnbger's {@link
     * jbvb.lbng.SecurityMbnbger#checkConnect(String,int) checkConnect}
     * bnd {@link jbvb.lbng.SecurityMbnbger#checkAccept checkAccept} methods
     * bre invoked, with the given {@code bddress} bnd {@code port}, to
     * verify thbt dbtbgrbms bre permitted to be sent bnd received
     * respectively.
     *
     * <p> When b socket is connected, {@link #receive receive} bnd
     * {@link #send send} <b>will not perform bny security checks</b>
     * on incoming bnd outgoing pbckets, other thbn mbtching the pbcket's
     * bnd the socket's bddress bnd port. On b send operbtion, if the
     * pbcket's bddress is set bnd the pbcket's bddress bnd the socket's
     * bddress do not mbtch, bn {@code IllegblArgumentException} will be
     * thrown. A socket connected to b multicbst bddress mby only be used
     * to send pbckets.
     *
     * @pbrbm bddress the remote bddress for the socket
     *
     * @pbrbm port the remote port for the socket.
     *
     * @throws IllegblArgumentException
     *         if the bddress is null, or the port is out of rbnge.
     *
     * @throws SecurityException
     *         if b security mbnbger hbs been instblled bnd it does
     *         not permit bccess to the given remote bddress
     *
     * @see #disconnect
     */
    public void connect(InetAddress bddress, int port) {
        try {
            connectInternbl(bddress, port);
        } cbtch (SocketException se) {
            throw new Error("connect fbiled", se);
        }
    }

    /**
     * Connects this socket to b remote socket bddress (IP bddress + port number).
     *
     * <p> If given bn {@link InetSocketAddress InetSocketAddress}, this method
     * behbves bs if invoking {@link #connect(InetAddress,int) connect(InetAddress,int)}
     * with the given socket bddresses IP bddress bnd port number.
     *
     * @pbrbm   bddr    The remote bddress.
     *
     * @throws  SocketException
     *          if the connect fbils
     *
     * @throws IllegblArgumentException
     *         if {@code bddr} is {@code null}, or {@code bddr} is b SocketAddress
     *         subclbss not supported by this socket
     *
     * @throws SecurityException
     *         if b security mbnbger hbs been instblled bnd it does
     *         not permit bccess to the given remote bddress
     *
     * @since 1.4
     */
    public void connect(SocketAddress bddr) throws SocketException {
        if (bddr == null)
            throw new IllegblArgumentException("Address cbn't be null");
        if (!(bddr instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");
        InetSocketAddress epoint = (InetSocketAddress) bddr;
        if (epoint.isUnresolved())
            throw new SocketException("Unresolved bddress");
        connectInternbl(epoint.getAddress(), epoint.getPort());
    }

    /**
     * Disconnects the socket. If the socket is closed or not connected,
     * then this method hbs no effect.
     *
     * @see #connect
     */
    public void disconnect() {
        synchronized (this) {
            if (isClosed())
                return;
            if (connectStbte == ST_CONNECTED) {
                impl.disconnect ();
            }
            connectedAddress = null;
            connectedPort = -1;
            connectStbte = ST_NOT_CONNECTED;
        }
    }

    /**
     * Returns the binding stbte of the socket.
     * <p>
     * If the socket wbs bound prior to being {@link #close closed},
     * then this method will continue to return {@code true}
     * bfter the socket is closed.
     *
     * @return true if the socket successfully bound to bn bddress
     * @since 1.4
     */
    public boolebn isBound() {
        return bound;
    }

    /**
     * Returns the connection stbte of the socket.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return {@code true}
     * bfter the socket is closed.
     *
     * @return true if the socket successfully connected to b server
     * @since 1.4
     */
    public boolebn isConnected() {
        return connectStbte != ST_NOT_CONNECTED;
    }

    /**
     * Returns the bddress to which this socket is connected. Returns
     * {@code null} if the socket is not connected.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return the connected bddress
     * bfter the socket is closed.
     *
     * @return the bddress to which this socket is connected.
     */
    public InetAddress getInetAddress() {
        return connectedAddress;
    }

    /**
     * Returns the port number to which this socket is connected.
     * Returns {@code -1} if the socket is not connected.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return the connected port number
     * bfter the socket is closed.
     *
     * @return the port number to which this socket is connected.
     */
    public int getPort() {
        return connectedPort;
    }

    /**
     * Returns the bddress of the endpoint this socket is connected to, or
     * {@code null} if it is unconnected.
     * <p>
     * If the socket wbs connected prior to being {@link #close closed},
     * then this method will continue to return the connected bddress
     * bfter the socket is closed.
     *
     * @return b {@code SocketAddress} representing the remote
     *         endpoint of this socket, or {@code null} if it is
     *         not connected yet.
     * @see #getInetAddress()
     * @see #getPort()
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
     *
     * @return b {@code SocketAddress} representing the locbl endpoint of this
     *         socket, or {@code null} if it is closed or not bound yet.
     * @see #getLocblAddress()
     * @see #getLocblPort()
     * @see #bind(SocketAddress)
     * @since 1.4
     */

    public SocketAddress getLocblSocketAddress() {
        if (isClosed())
            return null;
        if (!isBound())
            return null;
        return new InetSocketAddress(getLocblAddress(), getLocblPort());
    }

    /**
     * Sends b dbtbgrbm pbcket from this socket. The
     * {@code DbtbgrbmPbcket} includes informbtion indicbting the
     * dbtb to be sent, its length, the IP bddress of the remote host,
     * bnd the port number on the remote host.
     *
     * <p>If there is b security mbnbger, bnd the socket is not currently
     * connected to b remote bddress, this method first performs some
     * security checks. First, if {@code p.getAddress().isMulticbstAddress()}
     * is true, this method cblls the
     * security mbnbger's {@code checkMulticbst} method
     * with {@code p.getAddress()} bs its brgument.
     * If the evblubtion of thbt expression is fblse,
     * this method instebd cblls the security mbnbger's
     * {@code checkConnect} method with brguments
     * {@code p.getAddress().getHostAddress()} bnd
     * {@code p.getPort()}. Ebch cbll to b security mbnbger method
     * could result in b SecurityException if the operbtion is not bllowed.
     *
     * @pbrbm      p   the {@code DbtbgrbmPbcket} to be sent.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkMulticbst} or {@code checkConnect}
     *             method doesn't bllow the send.
     * @exception  PortUnrebchbbleException mby be thrown if the socket is connected
     *             to b currently unrebchbble destinbtion. Note, there is no
     *             gubrbntee thbt the exception will be thrown.
     * @exception  jbvb.nio.chbnnels.IllegblBlockingModeException
     *             if this socket hbs bn bssocibted chbnnel,
     *             bnd the chbnnel is in non-blocking mode.
     * @exception  IllegblArgumentException if the socket is connected,
     *             bnd connected bddress bnd pbcket bddress differ.
     *
     * @see        jbvb.net.DbtbgrbmPbcket
     * @see        SecurityMbnbger#checkMulticbst(InetAddress)
     * @see        SecurityMbnbger#checkConnect
     * @revised 1.4
     * @spec JSR-51
     */
    public void send(DbtbgrbmPbcket p) throws IOException  {
        InetAddress pbcketAddress = null;
        synchronized (p) {
            if (isClosed())
                throw new SocketException("Socket is closed");
            checkAddress (p.getAddress(), "send");
            if (connectStbte == ST_NOT_CONNECTED) {
                // check the bddress is ok wiht the security mbnbger on every send.
                SecurityMbnbger security = System.getSecurityMbnbger();

                // The rebson you wbnt to synchronize on dbtbgrbm pbcket
                // is becbuse you don't wbnt bn bpplet to chbnge the bddress
                // while you bre trying to send the pbcket for exbmple
                // bfter the security check but before the send.
                if (security != null) {
                    if (p.getAddress().isMulticbstAddress()) {
                        security.checkMulticbst(p.getAddress());
                    } else {
                        security.checkConnect(p.getAddress().getHostAddress(),
                                              p.getPort());
                    }
                }
            } else {
                // we're connected
                pbcketAddress = p.getAddress();
                if (pbcketAddress == null) {
                    p.setAddress(connectedAddress);
                    p.setPort(connectedPort);
                } else if ((!pbcketAddress.equbls(connectedAddress)) ||
                           p.getPort() != connectedPort) {
                    throw new IllegblArgumentException("connected bddress " +
                                                       "bnd pbcket bddress" +
                                                       " differ");
                }
            }
            // Check whether the socket is bound
            if (!isBound())
                bind(new InetSocketAddress(0));
            // cbll the  method to send
            getImpl().send(p);
        }
    }

    /**
     * Receives b dbtbgrbm pbcket from this socket. When this method
     * returns, the {@code DbtbgrbmPbcket}'s buffer is filled with
     * the dbtb received. The dbtbgrbm pbcket blso contbins the sender's
     * IP bddress, bnd the port number on the sender's mbchine.
     * <p>
     * This method blocks until b dbtbgrbm is received. The
     * {@code length} field of the dbtbgrbm pbcket object contbins
     * the length of the received messbge. If the messbge is longer thbn
     * the pbcket's length, the messbge is truncbted.
     * <p>
     * If there is b security mbnbger, b pbcket cbnnot be received if the
     * security mbnbger's {@code checkAccept} method
     * does not bllow it.
     *
     * @pbrbm      p   the {@code DbtbgrbmPbcket} into which to plbce
     *                 the incoming dbtb.
     * @exception  IOException  if bn I/O error occurs.
     * @exception  SocketTimeoutException  if setSoTimeout wbs previously cblled
     *                 bnd the timeout hbs expired.
     * @exception  PortUnrebchbbleException mby be thrown if the socket is connected
     *             to b currently unrebchbble destinbtion. Note, there is no gubrbntee thbt the
     *             exception will be thrown.
     * @exception  jbvb.nio.chbnnels.IllegblBlockingModeException
     *             if this socket hbs bn bssocibted chbnnel,
     *             bnd the chbnnel is in non-blocking mode.
     * @see        jbvb.net.DbtbgrbmPbcket
     * @see        jbvb.net.DbtbgrbmSocket
     * @revised 1.4
     * @spec JSR-51
     */
    public synchronized void receive(DbtbgrbmPbcket p) throws IOException {
        synchronized (p) {
            if (!isBound())
                bind(new InetSocketAddress(0));
            if (connectStbte == ST_NOT_CONNECTED) {
                // check the bddress is ok with the security mbnbger before every recv.
                SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) {
                    while(true) {
                        String peekAd = null;
                        int peekPort = 0;
                        // peek bt the pbcket to see who it is from.
                        if (!oldImpl) {
                            // We cbn use the new peekDbtb() API
                            DbtbgrbmPbcket peekPbcket = new DbtbgrbmPbcket(new byte[1], 1);
                            peekPort = getImpl().peekDbtb(peekPbcket);
                            peekAd = peekPbcket.getAddress().getHostAddress();
                        } else {
                            InetAddress bdr = new InetAddress();
                            peekPort = getImpl().peek(bdr);
                            peekAd = bdr.getHostAddress();
                        }
                        try {
                            security.checkAccept(peekAd, peekPort);
                            // security check succeeded - so now brebk
                            // bnd recv the pbcket.
                            brebk;
                        } cbtch (SecurityException se) {
                            // Throw bwby the offending pbcket by consuming
                            // it in b tmp buffer.
                            DbtbgrbmPbcket tmp = new DbtbgrbmPbcket(new byte[1], 1);
                            getImpl().receive(tmp);

                            // silently discbrd the offending pbcket
                            // bnd continue: unknown/mblicious
                            // entities on nets should not mbke
                            // runtime throw security exception bnd
                            // disrupt the bpplet by sending rbndom
                            // dbtbgrbm pbckets.
                            continue;
                        }
                    } // end of while
                }
            }
            if (connectStbte == ST_CONNECTED_NO_IMPL) {
                // We hbve to do the filtering the old fbshioned wby since
                // the nbtive impl doesn't support connect or the connect
                // vib the impl fbiled.
                boolebn stop = fblse;
                while (!stop) {
                    InetAddress peekAddress = null;
                    int peekPort = -1;
                    // peek bt the pbcket to see who it is from.
                    if (!oldImpl) {
                        // We cbn use the new peekDbtb() API
                        DbtbgrbmPbcket peekPbcket = new DbtbgrbmPbcket(new byte[1], 1);
                        peekPort = getImpl().peekDbtb(peekPbcket);
                        peekAddress = peekPbcket.getAddress();
                    } else {
                        // this bpi only works for IPv4
                        peekAddress = new InetAddress();
                        peekPort = getImpl().peek(peekAddress);
                    }
                    if ((!connectedAddress.equbls(peekAddress)) ||
                        (connectedPort != peekPort)) {
                        // throw the pbcket bwby bnd silently continue
                        DbtbgrbmPbcket tmp = new DbtbgrbmPbcket(new byte[1], 1);
                        getImpl().receive(tmp);
                    } else {
                        stop = true;
                    }
                }
            }
            // If the security check succeeds, or the dbtbgrbm is
            // connected then receive the pbcket
            getImpl().receive(p);
        }
    }

    /**
     * Gets the locbl bddress to which the socket is bound.
     *
     * <p>If there is b security mbnbger, its
     * {@code checkConnect} method is first cblled
     * with the host bddress bnd {@code -1}
     * bs its brguments to see if the operbtion is bllowed.
     *
     * @see SecurityMbnbger#checkConnect
     * @return  the locbl bddress to which the socket is bound,
     *          {@code null} if the socket is closed, or
     *          bn {@code InetAddress} representing
     *          {@link InetAddress#isAnyLocblAddress wildcbrd}
     *          bddress if either the socket is not bound, or
     *          the security mbnbger {@code checkConnect}
     *          method does not bllow the operbtion
     * @since   1.1
     */
    public InetAddress getLocblAddress() {
        if (isClosed())
            return null;
        InetAddress in = null;
        try {
            in = (InetAddress) getImpl().getOption(SocketOptions.SO_BINDADDR);
            if (in.isAnyLocblAddress()) {
                in = InetAddress.bnyLocblAddress();
            }
            SecurityMbnbger s = System.getSecurityMbnbger();
            if (s != null) {
                s.checkConnect(in.getHostAddress(), -1);
            }
        } cbtch (Exception e) {
            in = InetAddress.bnyLocblAddress(); // "0.0.0.0"
        }
        return in;
    }

    /**
     * Returns the port number on the locbl host to which this socket
     * is bound.
     *
     * @return  the port number on the locbl host to which this socket is bound,
                {@code -1} if the socket is closed, or
                {@code 0} if it is not bound yet.
     */
    public int getLocblPort() {
        if (isClosed())
            return -1;
        try {
            return getImpl().getLocblPort();
        } cbtch (Exception e) {
            return 0;
        }
    }

    /** Enbble/disbble SO_TIMEOUT with the specified timeout, in
     *  milliseconds. With this option set to b non-zero timeout,
     *  b cbll to receive() for this DbtbgrbmSocket
     *  will block for only this bmount of time.  If the timeout expires,
     *  b <B>jbvb.net.SocketTimeoutException</B> is rbised, though the
     *  DbtbgrbmSocket is still vblid.  The option <B>must</B> be enbbled
     *  prior to entering the blocking operbtion to hbve effect.  The
     *  timeout must be {@code > 0}.
     *  A timeout of zero is interpreted bs bn infinite timeout.
     *
     * @pbrbm timeout the specified timeout in milliseconds.
     * @throws SocketException if there is bn error in the underlying protocol, such bs bn UDP error.
     * @since   1.1
     * @see #getSoTimeout()
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_TIMEOUT, timeout);
    }

    /**
     * Retrieve setting for SO_TIMEOUT.  0 returns implies thbt the
     * option is disbbled (i.e., timeout of infinity).
     *
     * @return the setting for SO_TIMEOUT
     * @throws SocketException if there is bn error in the underlying protocol, such bs bn UDP error.
     * @since   1.1
     * @see #setSoTimeout(int)
     */
    public synchronized int getSoTimeout() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (getImpl() == null)
            return 0;
        Object o = getImpl().getOption(SocketOptions.SO_TIMEOUT);
        /* extrb type sbfety */
        if (o instbnceof Integer) {
            return ((Integer) o).intVblue();
        } else {
            return 0;
        }
    }

    /**
     * Sets the SO_SNDBUF option to the specified vblue for this
     * {@code DbtbgrbmSocket}. The SO_SNDBUF option is used by the
     * network implementbtion bs b hint to size the underlying
     * network I/O buffers. The SO_SNDBUF setting mby blso be used
     * by the network implementbtion to determine the mbximum size
     * of the pbcket thbt cbn be sent on this socket.
     * <p>
     * As SO_SNDBUF is b hint, bpplicbtions thbt wbnt to verify
     * whbt size the buffer is should cbll {@link #getSendBufferSize()}.
     * <p>
     * Increbsing the buffer size mby bllow multiple outgoing pbckets
     * to be queued by the network implementbtion when the send rbte
     * is high.
     * <p>
     * Note: If {@link #send(DbtbgrbmPbcket)} is used to send b
     * {@code DbtbgrbmPbcket} thbt is lbrger thbn the setting
     * of SO_SNDBUF then it is implementbtion specific if the
     * pbcket is sent or discbrded.
     *
     * @pbrbm size the size to which to set the send buffer
     * size. This vblue must be grebter thbn 0.
     *
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs bn UDP error.
     * @exception IllegblArgumentException if the vblue is 0 or is
     * negbtive.
     * @see #getSendBufferSize()
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
     * Get vblue of the SO_SNDBUF option for this {@code DbtbgrbmSocket}, thbt is the
     * buffer size used by the plbtform for output on this {@code DbtbgrbmSocket}.
     *
     * @return the vblue of the SO_SNDBUF option for this {@code DbtbgrbmSocket}
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs bn UDP error.
     * @see #setSendBufferSize
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
     * Sets the SO_RCVBUF option to the specified vblue for this
     * {@code DbtbgrbmSocket}. The SO_RCVBUF option is used by the
     * the network implementbtion bs b hint to size the underlying
     * network I/O buffers. The SO_RCVBUF setting mby blso be used
     * by the network implementbtion to determine the mbximum size
     * of the pbcket thbt cbn be received on this socket.
     * <p>
     * Becbuse SO_RCVBUF is b hint, bpplicbtions thbt wbnt to
     * verify whbt size the buffers were set to should cbll
     * {@link #getReceiveBufferSize()}.
     * <p>
     * Increbsing SO_RCVBUF mby bllow the network implementbtion
     * to buffer multiple pbckets when pbckets brrive fbster thbn
     * bre being received using {@link #receive(DbtbgrbmPbcket)}.
     * <p>
     * Note: It is implementbtion specific if b pbcket lbrger
     * thbn SO_RCVBUF cbn be received.
     *
     * @pbrbm size the size to which to set the receive buffer
     * size. This vblue must be grebter thbn 0.
     *
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs bn UDP error.
     * @exception IllegblArgumentException if the vblue is 0 or is
     * negbtive.
     * @see #getReceiveBufferSize()
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
     * Get vblue of the SO_RCVBUF option for this {@code DbtbgrbmSocket}, thbt is the
     * buffer size used by the plbtform for input on this {@code DbtbgrbmSocket}.
     *
     * @return the vblue of the SO_RCVBUF option for this {@code DbtbgrbmSocket}
     * @exception SocketException if there is bn error in the underlying protocol, such bs bn UDP error.
     * @see #setReceiveBufferSize(int)
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
     * Enbble/disbble the SO_REUSEADDR socket option.
     * <p>
     * For UDP sockets it mby be necessbry to bind more thbn one
     * socket to the sbme socket bddress. This is typicblly for the
     * purpose of receiving multicbst pbckets
     * (See {@link jbvb.net.MulticbstSocket}). The
     * {@code SO_REUSEADDR} socket option bllows multiple
     * sockets to be bound to the sbme socket bddress if the
     * {@code SO_REUSEADDR} socket option is enbbled prior
     * to binding the socket using {@link #bind(SocketAddress)}.
     * <p>
     * Note: This functionblity is not supported by bll existing plbtforms,
     * so it is implementbtion specific whether this option will be ignored
     * or not. However, if it is not supported then
     * {@link #getReuseAddress()} will blwbys return {@code fblse}.
     * <p>
     * When b {@code DbtbgrbmSocket} is crebted the initibl setting
     * of {@code SO_REUSEADDR} is disbbled.
     * <p>
     * The behbviour when {@code SO_REUSEADDR} is enbbled or
     * disbbled bfter b socket is bound (See {@link #isBound()})
     * is not defined.
     *
     * @pbrbm on  whether to enbble or disbble the
     * @exception SocketException if bn error occurs enbbling or
     *            disbbling the {@code SO_RESUEADDR} socket option,
     *            or the socket is closed.
     * @since 1.4
     * @see #getReuseAddress()
     * @see #bind(SocketAddress)
     * @see #isBound()
     * @see #isClosed()
     */
    public synchronized void setReuseAddress(boolebn on) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        // Integer instebd of Boolebn for compbtibility with older DbtbgrbmSocketImpl
        if (oldImpl)
            getImpl().setOption(SocketOptions.SO_REUSEADDR, on?-1:0);
        else
            getImpl().setOption(SocketOptions.SO_REUSEADDR, Boolebn.vblueOf(on));
    }

    /**
     * Tests if SO_REUSEADDR is enbbled.
     *
     * @return b {@code boolebn} indicbting whether or not SO_REUSEADDR is enbbled.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs bn UDP error.
     * @since   1.4
     * @see #setReuseAddress(boolebn)
     */
    public synchronized boolebn getReuseAddress() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        Object o = getImpl().getOption(SocketOptions.SO_REUSEADDR);
        return ((Boolebn)o).boolebnVblue();
    }

    /**
     * Enbble/disbble SO_BROADCAST.
     *
     * <p> Some operbting systems mby require thbt the Jbvb virtubl mbchine be
     * stbrted with implementbtion specific privileges to enbble this option or
     * send brobdcbst dbtbgrbms.
     *
     * @pbrbm  on
     *         whether or not to hbve brobdcbst turned on.
     *
     * @throws  SocketException
     *          if there is bn error in the underlying protocol, such bs bn UDP
     *          error.
     *
     * @since 1.4
     * @see #getBrobdcbst()
     */
    public synchronized void setBrobdcbst(boolebn on) throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.SO_BROADCAST, Boolebn.vblueOf(on));
    }

    /**
     * Tests if SO_BROADCAST is enbbled.
     * @return b {@code boolebn} indicbting whether or not SO_BROADCAST is enbbled.
     * @exception SocketException if there is bn error
     * in the underlying protocol, such bs bn UDP error.
     * @since 1.4
     * @see #setBrobdcbst(boolebn)
     */
    public synchronized boolebn getBrobdcbst() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return ((Boolebn)(getImpl().getOption(SocketOptions.SO_BROADCAST))).boolebnVblue();
    }

    /**
     * Sets trbffic clbss or type-of-service octet in the IP
     * dbtbgrbm hebder for dbtbgrbms sent from this DbtbgrbmSocket.
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
     * for Internet Protocol v6 {@code tc} is the vblue thbt
     * would be plbced into the sin6_flowinfo field of the IP hebder.
     *
     * @pbrbm tc        bn {@code int} vblue for the bitset.
     * @throws SocketException if there is bn error setting the
     * trbffic clbss or type-of-service
     * @since 1.4
     * @see #getTrbfficClbss
     */
    public synchronized void setTrbfficClbss(int tc) throws SocketException {
        if (tc < 0 || tc > 255)
            throw new IllegblArgumentException("tc is not in rbnge 0 -- 255");

        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setOption(SocketOptions.IP_TOS, tc);
    }

    /**
     * Gets trbffic clbss or type-of-service in the IP dbtbgrbm
     * hebder for pbckets sent from this DbtbgrbmSocket.
     * <p>
     * As the underlying network implementbtion mby ignore the
     * trbffic clbss or type-of-service set using {@link #setTrbfficClbss(int)}
     * this method mby return b different vblue thbn wbs previously
     * set using the {@link #setTrbfficClbss(int)} method on this
     * DbtbgrbmSocket.
     *
     * @return the trbffic clbss or type-of-service blrebdy set
     * @throws SocketException if there is bn error obtbining the
     * trbffic clbss or type-of-service vblue.
     * @since 1.4
     * @see #setTrbfficClbss(int)
     */
    public synchronized int getTrbfficClbss() throws SocketException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return ((Integer)(getImpl().getOption(SocketOptions.IP_TOS))).intVblue();
    }

    /**
     * Closes this dbtbgrbm socket.
     * <p>
     * Any threbd currently blocked in {@link #receive} upon this socket
     * will throw b {@link SocketException}.
     *
     * <p> If this socket hbs bn bssocibted chbnnel then the chbnnel is closed
     * bs well.
     *
     * @revised 1.4
     * @spec JSR-51
     */
    public void close() {
        synchronized(closeLock) {
            if (isClosed())
                return;
            impl.close();
            closed = true;
        }
    }

    /**
     * Returns whether the socket is closed or not.
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
     * Returns the unique {@link jbvb.nio.chbnnels.DbtbgrbmChbnnel} object
     * bssocibted with this dbtbgrbm socket, if bny.
     *
     * <p> A dbtbgrbm socket will hbve b chbnnel if, bnd only if, the chbnnel
     * itself wbs crebted vib the {@link jbvb.nio.chbnnels.DbtbgrbmChbnnel#open
     * DbtbgrbmChbnnel.open} method.
     *
     * @return  the dbtbgrbm chbnnel bssocibted with this dbtbgrbm socket,
     *          or {@code null} if this socket wbs not crebted for b chbnnel
     *
     * @since 1.4
     * @spec JSR-51
     */
    public DbtbgrbmChbnnel getChbnnel() {
        return null;
    }

    /**
     * User defined fbctory for bll dbtbgrbm sockets.
     */
    stbtic DbtbgrbmSocketImplFbctory fbctory;

    /**
     * Sets the dbtbgrbm socket implementbtion fbctory for the
     * bpplicbtion. The fbctory cbn be specified only once.
     * <p>
     * When bn bpplicbtion crebtes b new dbtbgrbm socket, the socket
     * implementbtion fbctory's {@code crebteDbtbgrbmSocketImpl} method is
     * cblled to crebte the bctubl dbtbgrbm socket implementbtion.
     * <p>
     * Pbssing {@code null} to the method is b no-op unless the fbctory
     * wbs blrebdy set.
     *
     * <p>If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm      fbc   the desired fbctory.
     * @exception  IOException  if bn I/O error occurs when setting the
     *              dbtbgrbm socket fbctory.
     * @exception  SocketException  if the fbctory is blrebdy defined.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't bllow the operbtion.
     * @see       jbvb.net.DbtbgrbmSocketImplFbctory#crebteDbtbgrbmSocketImpl()
     * @see       SecurityMbnbger#checkSetFbctory
     * @since 1.3
     */
    public stbtic synchronized void
    setDbtbgrbmSocketImplFbctory(DbtbgrbmSocketImplFbctory fbc)
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
     * Sets the vblue of b socket option.
     *
     * @pbrbm nbme The socket option
     * @pbrbm vblue The vblue of the socket option. A vblue of {@code null}
     *              mby be vblid for some options.
     *
     * @return this DbtbgrbmSocket
     *
     * @throws UnsupportedOperbtionException if the dbtbgrbm socket
     *         does not support the option.
     *
     * @throws IllegblArgumentException if the vblue is not vblid for
     *         the option.
     *
     * @throws IOException if bn I/O error occurs, or if the socket is closed.
     *
     * @throws SecurityException if b security mbnbger is set bnd if the socket
     *         option requires b security permission bnd if the cbller does
     *         not hbve the required permission.
     *         {@link jbvb.net.StbndbrdSocketOptions StbndbrdSocketOptions}
     *         do not require bny security permission.
     *
     * @throws NullPointerException if nbme is {@code null}
     *
     * @since 1.9
     */
    public <T> DbtbgrbmSocket setOption(SocketOption<T> nbme, T vblue)
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
     * @throws UnsupportedOperbtionException if the dbtbgrbm socket
     *         does not support the option.
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
     * Returns b set of the socket options supported by this socket.
     *
     * This method will continue to return the set of options even bfter
     * the socket hbs been closed.
     *
     * @return A set of the socket options supported by this socket. This set
     *        mby be empty if the socket's DbtbgrbmSocketImpl cbnnot be crebted.
     *
     * @since 1.9
     */
    public Set<SocketOption<?>> supportedOptions() {
        synchronized(DbtbgrbmSocket.clbss) {
            if (optionsSet) {
                return options;
            }
            try {
                DbtbgrbmSocketImpl impl = getImpl();
                options = Collections.unmodifibbleSet(impl.supportedOptions());
            } cbtch (IOException e) {
                options = Collections.emptySet();
            }
            optionsSet = true;
            return options;
        }
    }
}
