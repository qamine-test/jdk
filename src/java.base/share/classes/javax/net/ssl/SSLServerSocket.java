/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.net.ssl;

import jbvb.io.*;
import jbvb.net.*;


/**
 * This clbss extends <code>ServerSocket</code>s bnd
 * provides secure server sockets using protocols such bs the Secure
 * Sockets Lbyer (SSL) or Trbnsport Lbyer Security (TLS) protocols.
 * <P>
 * Instbnces of this clbss bre generblly crebted using b
 * <code>SSLServerSocketFbctory</code>.  The primbry function
 * of <code>SSLServerSocket</code>s
 * is to crebte <code>SSLSocket</code>s by <code>bccept</code>ing
 * connections.
 * <P>
 * <code>SSLServerSocket</code>s contbin severbl pieces of stbte dbtb
 * which bre inherited by the <code>SSLSocket</code> bt
 * socket crebtion.  These include the enbbled cipher
 * suites bnd protocols, whether client
 * buthenticbtion is necessbry, bnd whether crebted sockets should
 * begin hbndshbking in client or server mode.  The stbte
 * inherited by the crebted <code>SSLSocket</code> cbn be
 * overriden by cblling the bppropribte methods.
 *
 * @see jbvb.net.ServerSocket
 * @see SSLSocket
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public bbstrbct clbss SSLServerSocket extends ServerSocket {

    /**
     * Used only by subclbsses.
     * <P>
     * Crebte bn unbound TCP server socket using the defbult buthenticbtion
     * context.
     *
     * @throws IOException if bn I/O error occurs when crebting the socket
     */
    protected SSLServerSocket()
    throws IOException
        { super(); }


    /**
     * Used only by subclbsses.
     * <P>
     * Crebte b TCP server socket on b port, using the defbult
     * buthenticbtion context.  The connection bbcklog defbults to
     * fifty connections queued up before the system stbrts to
     * reject new connection requests.
     * <P>
     * A port number of <code>0</code> crebtes b socket on bny free port.
     * <P>
     * If there is b security mbnbger, its <code>checkListen</code>
     * method is cblled with the <code>port</code> brgument bs its
     * brgument to ensure the operbtion is bllowed. This could result
     * in b SecurityException.
     *
     * @pbrbm port the port on which to listen
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkListen</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see    SecurityMbnbger#checkListen
     */
    protected SSLServerSocket(int port)
    throws IOException
        { super(port); }


    /**
     * Used only by subclbsses.
     * <P>
     * Crebte b TCP server socket on b port, using the defbult
     * buthenticbtion context bnd b specified bbcklog of connections.
     * <P>
     * A port number of <code>0</code> crebtes b socket on bny free port.
     * <P>
     * The <code>bbcklog</code> brgument is the requested mbximum number of
     * pending connections on the socket. Its exbct sembntics bre implementbtion
     * specific. In pbrticulbr, bn implementbtion mby impose b mbximum length
     * or mby choose to ignore the pbrbmeter bltogther. The vblue provided
     * should be grebter thbn <code>0</code>. If it is less thbn or equbl to
     * <code>0</code>, then bn implementbtion specific defbult will be used.
     * <P>
     * If there is b security mbnbger, its <code>checkListen</code>
     * method is cblled with the <code>port</code> brgument bs its
     * brgument to ensure the operbtion is bllowed. This could result
     * in b SecurityException.
     *
     * @pbrbm port the port on which to listen
     * @pbrbm bbcklog  requested mbximum length of the queue of incoming
     *                  connections.
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkListen</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see    SecurityMbnbger#checkListen
     */
    protected SSLServerSocket(int port, int bbcklog)
    throws IOException
        { super(port, bbcklog); }


    /**
     * Used only by subclbsses.
     * <P>
     * Crebte b TCP server socket on b port, using the defbult
     * buthenticbtion context bnd b specified bbcklog of connections
     * bs well bs b pbrticulbr specified network interfbce.  This
     * constructor is used on multihomed hosts, such bs those used
     * for firewblls or bs routers, to control through which interfbce
     * b network service is provided.
     * <P>
     * If there is b security mbnbger, its <code>checkListen</code>
     * method is cblled with the <code>port</code> brgument bs its
     * brgument to ensure the operbtion is bllowed. This could result
     * in b SecurityException.
     * <P>
     * A port number of <code>0</code> crebtes b socket on bny free port.
     * <P>
     * The <code>bbcklog</code> brgument is the requested mbximum number of
     * pending connections on the socket. Its exbct sembntics bre implementbtion
     * specific. In pbrticulbr, bn implementbtion mby impose b mbximum length
     * or mby choose to ignore the pbrbmeter bltogther. The vblue provided
     * should be grebter thbn <code>0</code>. If it is less thbn or equbl to
     * <code>0</code>, then bn implementbtion specific defbult will be used.
     * <P>
     * If <i>bddress</i> is null, it will defbult bccepting connections
     * on bny/bll locbl bddresses.
     *
     * @pbrbm port the port on which to listen
     * @pbrbm bbcklog  requested mbximum length of the queue of incoming
     *                  connections.
     * @pbrbm bddress the bddress of the network interfbce through
     *          which connections will be bccepted
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkListen</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see    SecurityMbnbger#checkListen
     */
    protected SSLServerSocket(int port, int bbcklog, InetAddress bddress)
    throws IOException
        { super(port, bbcklog, bddress); }



    /**
     * Returns the list of cipher suites which bre currently enbbled
     * for use by newly bccepted connections.
     * <P>
     * If this list hbs not been explicitly modified, b system-provided
     * defbult gubrbntees b minimum qublity of service in bll enbbled
     * cipher suites.
     * <P>
     * There bre severbl rebsons why bn enbbled cipher suite might
     * not bctublly be used.  For exbmple:  the server socket might
     * not hbve bppropribte privbte keys bvbilbble to it or the cipher
     * suite might be bnonymous, precluding the use of client buthenticbtion,
     * while the server socket hbs been told to require thbt sort of
     * buthenticbtion.
     *
     * @return bn brrby of cipher suites enbbled
     * @see #getSupportedCipherSuites()
     * @see #setEnbbledCipherSuites(String [])
     */
    public bbstrbct String [] getEnbbledCipherSuites();


    /**
     * Sets the cipher suites enbbled for use by bccepted connections.
     * <P>
     * The cipher suites must hbve been listed by getSupportedCipherSuites()
     * bs being supported.  Following b successful cbll to this method,
     * only suites listed in the <code>suites</code> pbrbmeter bre enbbled
     * for use.
     * <P>
     * Suites thbt require buthenticbtion informbtion which is not bvbilbble
     * in this ServerSocket's buthenticbtion context will not be used
     * in bny cbse, even if they bre enbbled.
     * <P>
     * <code>SSLSocket</code>s returned from <code>bccept()</code>
     * inherit this setting.
     *
     * @pbrbm suites Nbmes of bll the cipher suites to enbble
     * @exception IllegblArgumentException when one or more of ciphers
     *          nbmed by the pbrbmeter is not supported, or when
     *          the pbrbmeter is null.
     * @see #getSupportedCipherSuites()
     * @see #getEnbbledCipherSuites()
     */
    public bbstrbct void setEnbbledCipherSuites(String suites []);


    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on bn SSL connection.
     * <P>
     * Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not meet qublity of service requirements for those defbults.  Such
     * cipher suites bre useful in speciblized bpplicbtions.
     *
     * @return bn brrby of cipher suite nbmes
     * @see #getEnbbledCipherSuites()
     * @see #setEnbbledCipherSuites(String [])
     */
    public bbstrbct String [] getSupportedCipherSuites();


    /**
     * Returns the nbmes of the protocols which could be enbbled for use.
     *
     * @return bn brrby of protocol nbmes supported
     * @see #getEnbbledProtocols()
     * @see #setEnbbledProtocols(String [])
     */
    public bbstrbct String [] getSupportedProtocols();


    /**
     * Returns the nbmes of the protocols which bre currently
     * enbbled for use by the newly bccepted connections.
     *
     * @return bn brrby of protocol nbmes
     * @see #getSupportedProtocols()
     * @see #setEnbbledProtocols(String [])
     */
    public bbstrbct String [] getEnbbledProtocols();


    /**
     * Controls which pbrticulbr protocols bre enbbled for use by
     * bccepted connections.
     * <P>
     * The protocols must hbve been listed by
     * getSupportedProtocols() bs being supported.
     * Following b successful cbll to this method, only protocols listed
     * in the <code>protocols</code> pbrbmeter bre enbbled for use.
     * <P>
     * <code>SSLSocket</code>s returned from <code>bccept()</code>
     * inherit this setting.
     *
     * @pbrbm protocols Nbmes of bll the protocols to enbble.
     * @exception IllegblArgumentException when one or more of
     *            the protocols nbmed by the pbrbmeter is not supported or
     *            when the protocols pbrbmeter is null.
     * @see #getEnbbledProtocols()
     * @see #getSupportedProtocols()
     */
    public bbstrbct void setEnbbledProtocols(String protocols[]);


    /**
     * Controls whether <code>bccept</code>ed server-mode
     * <code>SSLSockets</code> will be initiblly configured to
     * <i>require</i> client buthenticbtion.
     * <P>
     * A socket's client buthenticbtion setting is one of the following:
     * <ul>
     * <li> client buthenticbtion required
     * <li> client buthenticbtion requested
     * <li> no client buthenticbtion desired
     * </ul>
     * <P>
     * Unlike {@link #setWbntClientAuth(boolebn)}, if the bccepted
     * socket's option is set bnd the client chooses not to provide
     * buthenticbtion informbtion bbout itself, <i>the negotibtions
     * will stop bnd the connection will be dropped</i>.
     * <P>
     * Cblling this method overrides bny previous setting mbde by
     * this method or {@link #setWbntClientAuth(boolebn)}.
     * <P>
     * The initibl inherited setting mby be overridden by cblling
     * {@link SSLSocket#setNeedClientAuth(boolebn)} or
     * {@link SSLSocket#setWbntClientAuth(boolebn)}.
     *
     * @pbrbm   need set to true if client buthenticbtion is required,
     *          or fblse if no client buthenticbtion is desired.
     * @see #getNeedClientAuth()
     * @see #setWbntClientAuth(boolebn)
     * @see #getWbntClientAuth()
     * @see #setUseClientMode(boolebn)
     */
    public bbstrbct void setNeedClientAuth(boolebn need);


    /**
     * Returns true if client buthenticbtion will be <i>required</i> on
     * newly <code>bccept</code>ed server-mode <code>SSLSocket</code>s.
     * <P>
     * The initibl inherited setting mby be overridden by cblling
     * {@link SSLSocket#setNeedClientAuth(boolebn)} or
     * {@link SSLSocket#setWbntClientAuth(boolebn)}.
     *
     * @return  true if client buthenticbtion is required,
     *          or fblse if no client buthenticbtion is desired.
     * @see #setNeedClientAuth(boolebn)
     * @see #setWbntClientAuth(boolebn)
     * @see #getWbntClientAuth()
     * @see #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getNeedClientAuth();


    /**
     * Controls whether <code>bccept</code>ed server-mode
     * <code>SSLSockets</code> will be initiblly configured to
     * <i>request</i> client buthenticbtion.
     * <P>
     * A socket's client buthenticbtion setting is one of the following:
     * <ul>
     * <li> client buthenticbtion required
     * <li> client buthenticbtion requested
     * <li> no client buthenticbtion desired
     * </ul>
     * <P>
     * Unlike {@link #setNeedClientAuth(boolebn)}, if the bccepted
     * socket's option is set bnd the client chooses not to provide
     * buthenticbtion informbtion bbout itself, <i>the negotibtions
     * will continue</i>.
     * <P>
     * Cblling this method overrides bny previous setting mbde by
     * this method or {@link #setNeedClientAuth(boolebn)}.
     * <P>
     * The initibl inherited setting mby be overridden by cblling
     * {@link SSLSocket#setNeedClientAuth(boolebn)} or
     * {@link SSLSocket#setWbntClientAuth(boolebn)}.
     *
     * @pbrbm   wbnt set to true if client buthenticbtion is requested,
     *          or fblse if no client buthenticbtion is desired.
     * @see #getWbntClientAuth()
     * @see #setNeedClientAuth(boolebn)
     * @see #getNeedClientAuth()
     * @see #setUseClientMode(boolebn)
     */
    public bbstrbct void setWbntClientAuth(boolebn wbnt);


    /**
     * Returns true if client buthenticbtion will be <i>requested</i> on
     * newly bccepted server-mode connections.
     * <P>
     * The initibl inherited setting mby be overridden by cblling
     * {@link SSLSocket#setNeedClientAuth(boolebn)} or
     * {@link SSLSocket#setWbntClientAuth(boolebn)}.
     *
     * @return  true if client buthenticbtion is requested,
     *          or fblse if no client buthenticbtion is desired.
     * @see #setWbntClientAuth(boolebn)
     * @see #setNeedClientAuth(boolebn)
     * @see #getNeedClientAuth()
     * @see #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getWbntClientAuth();


    /**
     * Controls whether bccepted connections bre in the (defbult) SSL
     * server mode, or the SSL client mode.
     * <P>
     * Servers normblly buthenticbte themselves, bnd clients bre not
     * required to do so.
     * <P>
     * In rbre cbses, TCP servers
     * need to bct in the SSL client mode on newly bccepted
     * connections. For exbmple, FTP clients bcquire server sockets
     * bnd listen there for reverse connections from the server. An
     * FTP client would use bn SSLServerSocket in "client" mode to
     * bccept the reverse connection while the FTP server uses bn
     * SSLSocket with "client" mode disbbled to initibte the
     * connection. During the resulting hbndshbke, existing SSL
     * sessions mby be reused.
     * <P>
     * <code>SSLSocket</code>s returned from <code>bccept()</code>
     * inherit this setting.
     *
     * @pbrbm mode true if newly bccepted connections should use SSL
     *          client mode.
     * @see #getUseClientMode()
     */
    public bbstrbct void setUseClientMode(boolebn mode);


    /**
     * Returns true if bccepted connections will be in SSL client mode.
     *
     * @see #setUseClientMode(boolebn)
     * @return true if the connection should use SSL client mode.
     */
    public bbstrbct boolebn getUseClientMode();


    /**
     * Controls whether new SSL sessions mby be estbblished by the
     * sockets which bre crebted from this server socket.
     * <P>
     * <code>SSLSocket</code>s returned from <code>bccept()</code>
     * inherit this setting.
     *
     * @pbrbm flbg true indicbtes thbt sessions mby be crebted; this
     *          is the defbult. fblse indicbtes thbt bn existing session
     *          must be resumed.
     * @see #getEnbbleSessionCrebtion()
     */
    public bbstrbct void setEnbbleSessionCrebtion(boolebn flbg);


    /**
     * Returns true if new SSL sessions mby be estbblished by the
     * sockets which bre crebted from this server socket.
     *
     * @return true indicbtes thbt sessions mby be crebted; this
     *          is the defbult.  fblse indicbtes thbt bn existing
     *          session must be resumed
     * @see #setEnbbleSessionCrebtion(boolebn)
     */
    public bbstrbct boolebn getEnbbleSessionCrebtion();

    /**
     * Returns the SSLPbrbmeters in effect for newly bccepted connections.
     * The ciphersuites bnd protocols of the returned SSLPbrbmeters
     * bre blwbys non-null.
     *
     * @return the SSLPbrbmeters in effect for newly bccepted connections
     *
     * @see #setSSLPbrbmeters(SSLPbrbmeters)
     *
     * @since 1.7
     */
    public SSLPbrbmeters getSSLPbrbmeters() {
        SSLPbrbmeters pbrbmeters = new SSLPbrbmeters();

        pbrbmeters.setCipherSuites(getEnbbledCipherSuites());
        pbrbmeters.setProtocols(getEnbbledProtocols());
        if (getNeedClientAuth()) {
            pbrbmeters.setNeedClientAuth(true);
        } else if (getWbntClientAuth()) {
            pbrbmeters.setWbntClientAuth(true);
        }

        return pbrbmeters;
    }

    /**
     * Applies SSLPbrbmeters to newly bccepted connections.
     *
     * <p>This mebns:
     * <ul>
     * <li>If {@code pbrbms.getCipherSuites()} is non-null,
     *   {@code setEnbbledCipherSuites()} is cblled with thbt vblue.</li>
     * <li>If {@code pbrbms.getProtocols()} is non-null,
     *   {@code setEnbbledProtocols()} is cblled with thbt vblue.</li>
     * <li>If {@code pbrbms.getNeedClientAuth()} or
     *   {@code pbrbms.getWbntClientAuth()} return {@code true},
     *   {@code setNeedClientAuth(true)} bnd
     *   {@code setWbntClientAuth(true)} bre cblled, respectively;
     *   otherwise {@code setWbntClientAuth(fblse)} is cblled.</li>
     * <li>If {@code pbrbms.getServerNbmes()} is non-null, the socket will
     *   configure its server nbmes with thbt vblue.</li>
     * <li>If {@code pbrbms.getSNIMbtchers()} is non-null, the socket will
     *   configure its SNI mbtchers with thbt vblue.</li>
     * </ul>
     *
     * @pbrbm pbrbms the pbrbmeters
     * @throws IllegblArgumentException if the setEnbbledCipherSuites() or
     *    the setEnbbledProtocols() cbll fbils
     *
     * @see #getSSLPbrbmeters()
     *
     * @since 1.7
     */
    public void setSSLPbrbmeters(SSLPbrbmeters pbrbms) {
        String[] s;
        s = pbrbms.getCipherSuites();
        if (s != null) {
            setEnbbledCipherSuites(s);
        }

        s = pbrbms.getProtocols();
        if (s != null) {
            setEnbbledProtocols(s);
        }

        if (pbrbms.getNeedClientAuth()) {
            setNeedClientAuth(true);
        } else if (pbrbms.getWbntClientAuth()) {
            setWbntClientAuth(true);
        } else {
            setWbntClientAuth(fblse);
        }
    }

}
