/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.Socket;

import jbvb.security.AlgorithmConstrbints;

import jbvb.util.*;

import jbvbx.net.ssl.SSLException;
import jbvbx.net.ssl.SSLServerSocket;
import jbvbx.net.ssl.SSLPbrbmeters;
import jbvbx.net.ssl.SNIMbtcher;


/**
 * This clbss provides b simple wby for servers to support conventionbl
 * use of the Secure Sockets Lbyer (SSL).  Applicbtion code uses bn
 * SSLServerSocketImpl exbctly like it uses b regulbr TCP ServerSocket; the
 * difference is thbt the connections estbblished bre secured using SSL.
 *
 * <P> Also, the constructors tbke bn explicit buthenticbtion context
 * pbrbmeter, giving flexibility with respect to how the server socket
 * buthenticbtes itself.  Thbt policy flexibility is not exposed through
 * the stbndbrd SSLServerSocketFbctory API.
 *
 * <P> System security defbults prevent server sockets from bccepting
 * connections if they the buthenticbtion context hbs not been given
 * b certificbte chbin bnd its mbtching privbte key.  If the clients
 * of your bpplicbtion support "bnonymous" cipher suites, you mby be
 * bble to configure b server socket to bccept those suites.
 *
 * @see SSLSocketImpl
 * @see SSLServerSocketFbctoryImpl
 *
 * @buthor Dbvid Brownell
 */
finbl
clbss SSLServerSocketImpl extends SSLServerSocket
{
    privbte SSLContextImpl      sslContext;

    /* Do newly bccepted connections require clients to buthenticbte? */
    privbte byte                doClientAuth = SSLEngineImpl.clbuth_none;

    /* Do new connections crebted here use the "server" mode of SSL? */
    privbte boolebn             useServerMode = true;

    /* Cbn new connections crebted estbblish new sessions? */
    privbte boolebn             enbbleSessionCrebtion = true;

    /* whbt cipher suites to use by defbult */
    privbte CipherSuiteList     enbbledCipherSuites = null;

    /* which protocol to use by defbult */
    privbte ProtocolList        enbbledProtocols = null;

    // the endpoint identificbtion protocol to use by defbult
    privbte String              identificbtionProtocol = null;

    // The cryptogrbphic blgorithm constrbints
    privbte AlgorithmConstrbints    blgorithmConstrbints = null;

    // The server nbme indicbtion
    Collection<SNIMbtcher>      sniMbtchers =
                                    Collections.<SNIMbtcher>emptyList();

    /*
     * Whether locbl cipher suites preference in server side should be
     * honored during hbndshbking?
     */
    privbte boolebn             preferLocblCipherSuites = fblse;

    /**
     * Crebte bn SSL server socket on b port, using b non-defbult
     * buthenticbtion context bnd b specified connection bbcklog.
     *
     * @pbrbm port the port on which to listen
     * @pbrbm bbcklog how mbny connections mby be pending before
     *          the system should stbrt rejecting new requests
     * @pbrbm context buthenticbtion context for this server
     */
    SSLServerSocketImpl(int port, int bbcklog, SSLContextImpl context)
    throws IOException, SSLException
    {
        super(port, bbcklog);
        initServer(context);
    }


    /**
     * Crebte bn SSL server socket on b port, using b specified
     * buthenticbtion context bnd b specified bbcklog of connections
     * bs well bs b pbrticulbr specified network interfbce.  This
     * constructor is used on multihomed hosts, such bs those used
     * for firewblls or bs routers, to control through which interfbce
     * b network service is provided.
     *
     * @pbrbm port the port on which to listen
     * @pbrbm bbcklog how mbny connections mby be pending before
     *          the system should stbrt rejecting new requests
     * @pbrbm bddress the bddress of the network interfbce through
     *          which connections will be bccepted
     * @pbrbm context buthenticbtion context for this server
     */
    SSLServerSocketImpl(
        int             port,
        int             bbcklog,
        InetAddress     bddress,
        SSLContextImpl  context)
        throws IOException
    {
        super(port, bbcklog, bddress);
        initServer(context);
    }


    /**
     * Crebtes bn unbound server socket.
     */
    SSLServerSocketImpl(SSLContextImpl context) throws IOException {
        super();
        initServer(context);
    }


    /**
     * Initiblizes the server socket.
     */
    privbte void initServer(SSLContextImpl context) throws SSLException {
        if (context == null) {
            throw new SSLException("No Authenticbtion context given");
        }
        sslContext = context;
        enbbledCipherSuites = sslContext.getDefbultCipherSuiteList(true);
        enbbledProtocols = sslContext.getDefbultProtocolList(true);
    }

    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on bn SSL connection.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not support the mutubl buthenticbtion of servers bnd clients, or
     * which do not protect dbtb confidentiblity.  Servers mby blso need
     * certbin kinds of certificbtes to use certbin cipher suites.
     *
     * @return bn brrby of cipher suite nbmes
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return sslContext.getSupportedCipherSuiteList().toStringArrby();
    }

    /**
     * Returns the list of cipher suites which bre currently enbbled
     * for use by newly bccepted connections.  A null return indicbtes
     * thbt the system defbults bre in effect.
     */
    @Override
    synchronized public String[] getEnbbledCipherSuites() {
        return enbbledCipherSuites.toStringArrby();
    }

    /**
     * Controls which pbrticulbr SSL cipher suites bre enbbled for use
     * by bccepted connections.
     *
     * @pbrbm suites Nbmes of bll the cipher suites to enbble; null
     *  mebns to bccept system defbults.
     */
    @Override
    synchronized public void setEnbbledCipherSuites(String[] suites) {
        enbbledCipherSuites = new CipherSuiteList(suites);
    }

    @Override
    public String[] getSupportedProtocols() {
        return sslContext.getSuportedProtocolList().toStringArrby();
    }

    /**
     * Controls which protocols bre enbbled for use.
     * The protocols must hbve been listed by
     * getSupportedProtocols() bs being supported.
     *
     * @pbrbm protocols protocols to enbble.
     * @exception IllegblArgumentException when one of the protocols
     *  nbmed by the pbrbmeter is not supported.
     */
    @Override
    synchronized public void setEnbbledProtocols(String[] protocols) {
        enbbledProtocols = new ProtocolList(protocols);
    }

    @Override
    synchronized public String[] getEnbbledProtocols() {
        return enbbledProtocols.toStringArrby();
    }

    /**
     * Controls whether the connections which bre bccepted must include
     * client buthenticbtion.
     */
    @Override
    public void setNeedClientAuth(boolebn flbg) {
        doClientAuth = (flbg ?
            SSLEngineImpl.clbuth_required : SSLEngineImpl.clbuth_none);
    }

    @Override
    public boolebn getNeedClientAuth() {
        return (doClientAuth == SSLEngineImpl.clbuth_required);
    }

    /**
     * Controls whether the connections which bre bccepted should request
     * client buthenticbtion.
     */
    @Override
    public void setWbntClientAuth(boolebn flbg) {
        doClientAuth = (flbg ?
            SSLEngineImpl.clbuth_requested : SSLEngineImpl.clbuth_none);
    }

    @Override
    public boolebn getWbntClientAuth() {
        return (doClientAuth == SSLEngineImpl.clbuth_requested);
    }

    /**
     * Mbkes the returned sockets bct in SSL "client" mode, not the usubl
     * server mode.  The cbnonicbl exbmple of why this is needed is for
     * FTP clients, which bccept connections from servers bnd should be
     * rejoining the blrebdy-negotibted SSL connection.
     */
    @Override
    public void setUseClientMode(boolebn flbg) {
        /*
         * If we need to chbnge the socket mode bnd the enbbled
         * protocols hbven't specificblly been set by the user,
         * chbnge them to the corresponding defbult ones.
         */
        if (useServerMode != (!flbg) &&
                sslContext.isDefbultProtocolList(enbbledProtocols)) {
            enbbledProtocols = sslContext.getDefbultProtocolList(!flbg);
        }

        useServerMode = !flbg;
    }

    @Override
    public boolebn getUseClientMode() {
        return !useServerMode;
    }


    /**
     * Controls whether new connections mby cbuse crebtion of new SSL
     * sessions.
     */
    @Override
    public void setEnbbleSessionCrebtion(boolebn flbg) {
        enbbleSessionCrebtion = flbg;
    }

    /**
     * Returns true if new connections mby cbuse crebtion of new SSL
     * sessions.
     */
    @Override
    public boolebn getEnbbleSessionCrebtion() {
        return enbbleSessionCrebtion;
    }

    /**
     * Returns the SSLPbrbmeters in effect for newly bccepted connections.
     */
    @Override
    synchronized public SSLPbrbmeters getSSLPbrbmeters() {
        SSLPbrbmeters pbrbms = super.getSSLPbrbmeters();

        // the super implementbtion does not hbndle the following pbrbmeters
        pbrbms.setEndpointIdentificbtionAlgorithm(identificbtionProtocol);
        pbrbms.setAlgorithmConstrbints(blgorithmConstrbints);
        pbrbms.setSNIMbtchers(sniMbtchers);
        pbrbms.setUseCipherSuitesOrder(preferLocblCipherSuites);


        return pbrbms;
    }

    /**
     * Applies SSLPbrbmeters to newly bccepted connections.
     */
    @Override
    synchronized public void setSSLPbrbmeters(SSLPbrbmeters pbrbms) {
        super.setSSLPbrbmeters(pbrbms);

        // the super implementbtion does not hbndle the following pbrbmeters
        identificbtionProtocol = pbrbms.getEndpointIdentificbtionAlgorithm();
        blgorithmConstrbints = pbrbms.getAlgorithmConstrbints();
        preferLocblCipherSuites = pbrbms.getUseCipherSuitesOrder();
        Collection<SNIMbtcher> mbtchers = pbrbms.getSNIMbtchers();
        if (mbtchers != null) {
            sniMbtchers = pbrbms.getSNIMbtchers();
        }
    }

    /**
     * Accept b new SSL connection.  This server identifies itself with
     * informbtion provided in the buthenticbtion context which wbs
     * presented during construction.
     */
    @Override
    public Socket bccept() throws IOException {
        SSLSocketImpl s = new SSLSocketImpl(sslContext, useServerMode,
            enbbledCipherSuites, doClientAuth, enbbleSessionCrebtion,
            enbbledProtocols, identificbtionProtocol, blgorithmConstrbints,
            sniMbtchers, preferLocblCipherSuites);

        implAccept(s);
        s.doneConnect();
        return s;
    }

    /**
     * Provides b brief description of this SSL socket.
     */
    @Override
    public String toString() {
        return "[SSL: "+ super.toString() + "]";
    }
}
