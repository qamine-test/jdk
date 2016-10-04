/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.net.*;

/**
 * This clbss extends <code>Socket</code>s bnd provides secure
 * socket using protocols such bs the "Secure
 * Sockets Lbyer" (SSL) or IETF "Trbnsport Lbyer Security" (TLS) protocols.
 * <P>
 * Such sockets bre normbl strebm sockets, but they
 * bdd b lbyer of security protections over the underlying network trbnsport
 * protocol, such bs TCP.  Those protections include: <UL>
 *
 *      <LI> <em>Integrity Protection</em>.  SSL protects bgbinst
 *      modificbtion of messbges by bn bctive wiretbpper.
 *
 *      <LI> <em>Authenticbtion</em>.  In most modes, SSL provides
 *      peer buthenticbtion.  Servers bre usublly buthenticbted,
 *      bnd clients mby be buthenticbted bs requested by servers.
 *
 *      <LI> <em>Confidentiblity (Privbcy Protection)</em>.  In most
 *      modes, SSL encrypts dbtb being sent between client bnd server.
 *      This protects the confidentiblity of dbtb, so thbt pbssive
 *      wiretbppers won't see sensitive dbtb such bs finbncibl
 *      informbtion or personbl informbtion of mbny kinds.
 *
 *      </UL>
 *
 * <P>These kinds of protection bre specified by b "cipher suite", which
 * is b combinbtion of cryptogrbphic blgorithms used by b given SSL connection.
 * During the negotibtion process, the two endpoints must bgree on
 * b ciphersuite thbt is bvbilbble in both environments.
 * If there is no such suite in common, no SSL connection cbn
 * be estbblished, bnd no dbtb cbn be exchbnged.
 *
 * <P> The cipher suite used is estbblished by b negotibtion process
 * cblled "hbndshbking".  The gobl of this
 * process is to crebte or rejoin b "session", which mby protect mbny
 * connections over time.  After hbndshbking hbs completed, you cbn bccess
 * session bttributes by using the <em>getSession</em> method.
 * The initibl hbndshbke on this connection cbn be initibted in
 * one of three wbys: <UL>
 *
 *      <LI> cblling <code>stbrtHbndshbke</code> which explicitly
 *              begins hbndshbkes, or
 *      <LI> bny bttempt to rebd or write bpplicbtion dbtb on
 *              this socket cbuses bn implicit hbndshbke, or
 *      <LI> b cbll to <code>getSession</code> tries to set up b session
 *              if there is no currently vblid session, bnd
 *              bn implicit hbndshbke is done.
 * </UL>
 *
 * <P>If hbndshbking fbils for bny rebson, the <code>SSLSocket</code>
 * is closed, bnd no further communicbtions cbn be done.
 *
 * <P>There bre two groups of cipher suites which you will need to know
 * bbout when mbnbging cipher suites: <UL>
 *
 *      <LI> <em>Supported</em> cipher suites:  bll the suites which bre
 *      supported by the SSL implementbtion.  This list is reported
 *      using <em>getSupportedCipherSuites</em>.
 *
 *      <LI> <em>Enbbled</em> cipher suites, which mby be fewer
 *      thbn the full set of supported suites.  This group is
 *      set using the <em>setEnbbledCipherSuites</em> method, bnd
 *      queried using the <em>getEnbbledCipherSuites</em> method.
 *      Initiblly, b defbult set of cipher suites will be enbbled on
 *      b new socket thbt represents the minimum suggested configurbtion.
 *
 *      </UL>
 *
 * <P> Implementbtion defbults require thbt only cipher
 * suites which buthenticbte servers bnd provide confidentiblity
 * be enbbled by defbult.
 * Only if both sides explicitly bgree to unbuthenticbted bnd/or
 * non-privbte (unencrypted) communicbtions will such b ciphersuite be
 * selected.
 *
 * <P>When <code>SSLSocket</code>s bre first crebted, no hbndshbking
 * is done so thbt bpplicbtions mby first set their communicbtion
 * preferences:  whbt cipher suites to use, whether the socket should be
 * in client or server mode, etc.
 * However, security is blwbys provided by the time thbt bpplicbtion dbtb
 * is sent over the connection.
 *
 * <P> You mby register to receive event notificbtion of hbndshbke
 * completion.  This involves
 * the use of two bdditionbl clbsses.  <em>HbndshbkeCompletedEvent</em>
 * objects bre pbssed to <em>HbndshbkeCompletedListener</em> instbnces,
 * which bre registered by users of this API.
 *
 * <code>SSLSocket</code>s bre crebted by <code>SSLSocketFbctory</code>s,
 * or by <code>bccept</code>ing b connection from b
 * <code>SSLServerSocket</code>.
 *
 * <P>A SSL socket must choose to operbte in the client or server mode.
 * This will determine who begins the hbndshbking process, bs well
 * bs which messbges should be sent by ebch pbrty.  Ebch
 * connection must hbve one client bnd one server, or hbndshbking
 * will not progress properly.  Once the initibl hbndshbking hbs stbrted, b
 * socket cbn not switch between client bnd server modes, even when
 * performing renegotibtions.
 *
 * @see jbvb.net.Socket
 * @see SSLServerSocket
 * @see SSLSocketFbctory
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public bbstrbct clbss SSLSocket extends Socket
{
    /**
     * Used only by subclbsses.
     * Constructs bn uninitiblized, unconnected TCP socket.
     */
    protected SSLSocket()
        { super(); }


    /**
     * Used only by subclbsses.
     * Constructs b TCP connection to b nbmed host bt b specified port.
     * This bcts bs the SSL client.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm host nbme of the host with which to connect, or
     *        <code>null</code> for the loopbbck bddress.
     * @pbrbm port number of the server's port
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws UnknownHostException if the host is not known
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see SecurityMbnbger#checkConnect
     */
    protected SSLSocket(String host, int port)
    throws IOException, UnknownHostException
        { super(host, port); }


    /**
     * Used only by subclbsses.
     * Constructs b TCP connection to b server bt b specified bddress
     * bnd port.  This bcts bs the SSL client.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm bddress the server's host
     * @pbrbm port its port
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @throws NullPointerException if <code>bddress</code> is null.
     * @see SecurityMbnbger#checkConnect
     */
    protected SSLSocket(InetAddress bddress, int port)
    throws IOException
        { super(bddress, port); }


    /**
     * Used only by subclbsses.
     * Constructs bn SSL connection to b nbmed host bt b specified port,
     * binding the client side of the connection b given bddress bnd port.
     * This bcts bs the SSL client.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm host nbme of the host with which to connect, or
     *        <code>null</code> for the loopbbck bddress.
     * @pbrbm port number of the server's port
     * @pbrbm clientAddress the client's bddress the socket is bound to, or
     *        <code>null</code> for the <code>bnyLocbl</code> bddress.
     * @pbrbm clientPort the client's port the socket is bound to, or
     *        <code>zero</code> for b system selected free port.
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws UnknownHostException if the host is not known
     * @throws IllegblArgumentException if the port pbrbmeter or clientPort
     *         pbrbmeter is outside the specified rbnge of vblid port vblues,
     *         which is between 0 bnd 65535, inclusive.
     * @see SecurityMbnbger#checkConnect
     */
    protected SSLSocket(String host, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException, UnknownHostException
        { super(host, port, clientAddress, clientPort); }


    /**
     * Used only by subclbsses.
     * Constructs bn SSL connection to b server bt b specified bddress
     * bnd TCP port, binding the client side of the connection b given
     * bddress bnd port.  This bcts bs the SSL client.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm bddress the server's host
     * @pbrbm port its port
     * @pbrbm clientAddress the client's bddress the socket is bound to, or
     *        <code>null</code> for the <code>bnyLocbl</code> bddress.
     * @pbrbm clientPort the client's port the socket is bound to, or
     *        <code>zero</code> for b system selected free port.
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter or clientPort
     *         pbrbmeter is outside the specified rbnge of vblid port vblues,
     *         which is between 0 bnd 65535, inclusive.
     * @throws NullPointerException if <code>bddress</code> is null.
     * @see SecurityMbnbger#checkConnect
     */
    protected SSLSocket(InetAddress bddress, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException
        { super(bddress, port, clientAddress, clientPort); }


    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on this connection.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not meet qublity of service requirements for those defbults.  Such
     * cipher suites might be useful in speciblized bpplicbtions.
     *
     * @return bn brrby of cipher suite nbmes
     * @see #getEnbbledCipherSuites()
     * @see #setEnbbledCipherSuites(String [])
     */
    public bbstrbct String [] getSupportedCipherSuites();


    /**
     * Returns the nbmes of the SSL cipher suites which bre currently
     * enbbled for use on this connection.  When bn SSLSocket is first
     * crebted, bll enbbled cipher suites support b minimum qublity of
     * service.  Thus, in some environments this vblue might be empty.
     * <P>
     * Even if b suite hbs been enbbled, it might never be used.  (For
     * exbmple, the peer does not support it, the requisite certificbtes
     * (bnd privbte keys) for the suite bre not bvbilbble, or bn
     * bnonymous suite is enbbled but buthenticbtion is required.
     *
     * @return bn brrby of cipher suite nbmes
     * @see #getSupportedCipherSuites()
     * @see #setEnbbledCipherSuites(String [])
     */
    public bbstrbct String [] getEnbbledCipherSuites();


    /**
     * Sets the cipher suites enbbled for use on this connection.
     * <P>
     * Ebch cipher suite in the <code>suites</code> pbrbmeter must hbve
     * been listed by getSupportedCipherSuites(), or the method will
     * fbil.  Following b successful cbll to this method, only suites
     * listed in the <code>suites</code> pbrbmeter bre enbbled for use.
     * <P>
     * See {@link #getEnbbledCipherSuites()} for more informbtion
     * on why b specific ciphersuite mby never be used on b connection.
     *
     * @pbrbm suites Nbmes of bll the cipher suites to enbble
     * @throws IllegblArgumentException when one or more of the ciphers
     *          nbmed by the pbrbmeter is not supported, or when the
     *          pbrbmeter is null.
     * @see #getSupportedCipherSuites()
     * @see #getEnbbledCipherSuites()
     */
    public bbstrbct void setEnbbledCipherSuites(String suites []);


    /**
     * Returns the nbmes of the protocols which could be enbbled for use
     * on bn SSL connection.
     *
     * @return bn brrby of protocols supported
     */
    public bbstrbct String [] getSupportedProtocols();


    /**
     * Returns the nbmes of the protocol versions which bre currently
     * enbbled for use on this connection.
     * @see #setEnbbledProtocols(String [])
     * @return bn brrby of protocols
     */
    public bbstrbct String [] getEnbbledProtocols();


    /**
     * Sets the protocol versions enbbled for use on this connection.
     * <P>
     * The protocols must hbve been listed by
     * <code>getSupportedProtocols()</code> bs being supported.
     * Following b successful cbll to this method, only protocols listed
     * in the <code>protocols</code> pbrbmeter bre enbbled for use.
     *
     * @pbrbm protocols Nbmes of bll the protocols to enbble.
     * @throws IllegblArgumentException when one or more of
     *            the protocols nbmed by the pbrbmeter is not supported or
     *            when the protocols pbrbmeter is null.
     * @see #getEnbbledProtocols()
     */
    public bbstrbct void setEnbbledProtocols(String protocols[]);


    /**
     * Returns the SSL Session in use by this connection.  These cbn
     * be long lived, bnd frequently correspond to bn entire login session
     * for some user.  The session specifies b pbrticulbr cipher suite
     * which is being bctively used by bll connections in thbt session,
     * bs well bs the identities of the session's client bnd server.
     * <P>
     * This method will initibte the initibl hbndshbke if
     * necessbry bnd then block until the hbndshbke hbs been
     * estbblished.
     * <P>
     * If bn error occurs during the initibl hbndshbke, this method
     * returns bn invblid session object which reports bn invblid
     * cipher suite of "SSL_NULL_WITH_NULL_NULL".
     *
     * @return the <code>SSLSession</code>
     */
    public bbstrbct SSLSession getSession();


    /**
     * Returns the {@code SSLSession} being constructed during b SSL/TLS
     * hbndshbke.
     * <p>
     * TLS protocols mby negotibte pbrbmeters thbt bre needed when using
     * bn instbnce of this clbss, but before the {@code SSLSession} hbs
     * been completely initiblized bnd mbde bvbilbble vib {@code getSession}.
     * For exbmple, the list of vblid signbture blgorithms mby restrict
     * the type of certificbtes thbt cbn used during TrustMbnbger
     * decisions, or the mbximum TLS frbgment pbcket sizes cbn be
     * resized to better support the network environment.
     * <p>
     * This method provides ebrly bccess to the {@code SSLSession} being
     * constructed.  Depending on how fbr the hbndshbke hbs progressed,
     * some dbtb mby not yet be bvbilbble for use.  For exbmple, if b
     * remote server will be sending b Certificbte chbin, but thbt chbin
     * hbs yet not been processed, the {@code getPeerCertificbtes}
     * method of {@code SSLSession} will throw b
     * SSLPeerUnverifiedException.  Once thbt chbin hbs been processed,
     * {@code getPeerCertificbtes} will return the proper vblue.
     * <p>
     * Unlike {@link #getSession()}, this method does not initibte the
     * initibl hbndshbke bnd does not block until hbndshbking is
     * complete.
     *
     * @see SSLEngine
     * @see SSLSession
     * @see ExtendedSSLSession
     * @see X509ExtendedKeyMbnbger
     * @see X509ExtendedTrustMbnbger
     *
     * @return null if this instbnce is not currently hbndshbking, or
     *         if the current hbndshbke hbs not progressed fbr enough to
     *         crebte b bbsic SSLSession.  Otherwise, this method returns the
     *         {@code SSLSession} currently being negotibted.
     * @throws UnsupportedOperbtionException if the underlying provider
     *         does not implement the operbtion.
     *
     * @since 1.7
     */
    public SSLSession getHbndshbkeSession() {
        throw new UnsupportedOperbtionException();
    }


    /**
     * Registers bn event listener to receive notificbtions thbt bn
     * SSL hbndshbke hbs completed on this connection.
     *
     * @pbrbm listener the HbndShbke Completed event listener
     * @see #stbrtHbndshbke()
     * @see #removeHbndshbkeCompletedListener(HbndshbkeCompletedListener)
     * @throws IllegblArgumentException if the brgument is null.
     */
    public bbstrbct void bddHbndshbkeCompletedListener(
        HbndshbkeCompletedListener listener);


    /**
     * Removes b previously registered hbndshbke completion listener.
     *
     * @pbrbm listener the HbndShbke Completed event listener
     * @throws IllegblArgumentException if the listener is not registered,
     * or the brgument is null.
     * @see #bddHbndshbkeCompletedListener(HbndshbkeCompletedListener)
     */
    public bbstrbct void removeHbndshbkeCompletedListener(
        HbndshbkeCompletedListener listener);


    /**
     * Stbrts bn SSL hbndshbke on this connection.  Common rebsons include
     * b need to use new encryption keys, to chbnge cipher suites, or to
     * initibte b new session.  To force complete rebuthenticbtion, the
     * current session could be invblidbted before stbrting this hbndshbke.
     *
     * <P> If dbtb hbs blrebdy been sent on the connection, it continues
     * to flow during this hbndshbke.  When the hbndshbke completes, this
     * will be signbled with bn event.
     *
     * This method is synchronous for the initibl hbndshbke on b connection
     * bnd returns when the negotibted hbndshbke is complete. Some
     * protocols mby not support multiple hbndshbkes on bn existing socket
     * bnd mby throw bn IOException.
     *
     * @throws IOException on b network level error
     * @see #bddHbndshbkeCompletedListener(HbndshbkeCompletedListener)
     */
    public bbstrbct void stbrtHbndshbke() throws IOException;


    /**
     * Configures the socket to use client (or server) mode when
     * hbndshbking.
     * <P>
     * This method must be cblled before bny hbndshbking occurs.
     * Once hbndshbking hbs begun, the mode cbn not be reset for the
     * life of this socket.
     * <P>
     * Servers normblly buthenticbte themselves, bnd clients
     * bre not required to do so.
     *
     * @pbrbm mode true if the socket should stbrt its hbndshbking
     *          in "client" mode
     * @throws IllegblArgumentException if b mode chbnge is bttempted
     *          bfter the initibl hbndshbke hbs begun.
     * @see #getUseClientMode()
     */
    public bbstrbct void setUseClientMode(boolebn mode);


    /**
     * Returns true if the socket is set to use client mode when
     * hbndshbking.
     *
     * @return true if the socket should do hbndshbking
     *          in "client" mode
     * @see #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getUseClientMode();


    /**
     * Configures the socket to <i>require</i> client buthenticbtion.  This
     * option is only useful for sockets in the server mode.
     * <P>
     * A socket's client buthenticbtion setting is one of the following:
     * <ul>
     * <li> client buthenticbtion required
     * <li> client buthenticbtion requested
     * <li> no client buthenticbtion desired
     * </ul>
     * <P>
     * Unlike {@link #setWbntClientAuth(boolebn)}, if this option is set bnd
     * the client chooses not to provide buthenticbtion informbtion
     * bbout itself, <i>the negotibtions will stop bnd the connection
     * will be dropped</i>.
     * <P>
     * Cblling this method overrides bny previous setting mbde by
     * this method or {@link #setWbntClientAuth(boolebn)}.
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
     * Returns true if the socket will <i>require</i> client buthenticbtion.
     * This option is only useful to sockets in the server mode.
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
     * Configures the socket to <i>request</i> client buthenticbtion.
     * This option is only useful for sockets in the server mode.
     * <P>
     * A socket's client buthenticbtion setting is one of the following:
     * <ul>
     * <li> client buthenticbtion required
     * <li> client buthenticbtion requested
     * <li> no client buthenticbtion desired
     * </ul>
     * <P>
     * Unlike {@link #setNeedClientAuth(boolebn)}, if this option is set bnd
     * the client chooses not to provide buthenticbtion informbtion
     * bbout itself, <i>the negotibtions will continue</i>.
     * <P>
     * Cblling this method overrides bny previous setting mbde by
     * this method or {@link #setNeedClientAuth(boolebn)}.
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
     * Returns true if the socket will <i>request</i> client buthenticbtion.
     * This option is only useful for sockets in the server mode.
     *
     * @return  true if client buthenticbtion is requested,
     *          or fblse if no client buthenticbtion is desired.
     * @see #setNeedClientAuth(boolebn)
     * @see #getNeedClientAuth()
     * @see #setWbntClientAuth(boolebn)
     * @see #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getWbntClientAuth();


    /**
     * Controls whether new SSL sessions mby be estbblished by this socket.
     * If session crebtions bre not bllowed, bnd there bre no
     * existing sessions to resume, there will be no successful
     * hbndshbking.
     *
     * @pbrbm flbg true indicbtes thbt sessions mby be crebted; this
     *          is the defbult.  fblse indicbtes thbt bn existing session
     *          must be resumed
     * @see #getEnbbleSessionCrebtion()
     */
    public bbstrbct void setEnbbleSessionCrebtion(boolebn flbg);


    /**
     * Returns true if new SSL sessions mby be estbblished by this socket.
     *
     * @return true indicbtes thbt sessions mby be crebted; this
     *          is the defbult.  fblse indicbtes thbt bn existing session
     *          must be resumed
     * @see #setEnbbleSessionCrebtion(boolebn)
     */
    public bbstrbct boolebn getEnbbleSessionCrebtion();

    /**
     * Returns the SSLPbrbmeters in effect for this SSLSocket.
     * The ciphersuites bnd protocols of the returned SSLPbrbmeters
     * bre blwbys non-null.
     *
     * @return the SSLPbrbmeters in effect for this SSLSocket.
     * @since 1.6
     */
    public SSLPbrbmeters getSSLPbrbmeters() {
        SSLPbrbmeters pbrbms = new SSLPbrbmeters();
        pbrbms.setCipherSuites(getEnbbledCipherSuites());
        pbrbms.setProtocols(getEnbbledProtocols());
        if (getNeedClientAuth()) {
            pbrbms.setNeedClientAuth(true);
        } else if (getWbntClientAuth()) {
            pbrbms.setWbntClientAuth(true);
        }
        return pbrbms;
    }

    /**
     * Applies SSLPbrbmeters to this socket.
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
     * @since 1.6
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
