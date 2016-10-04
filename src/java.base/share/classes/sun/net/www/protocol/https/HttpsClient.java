/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.net.www.protocol.https;

import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.io.PrintStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.net.InetAddress;
import jbvb.net.Socket;
import jbvb.net.SocketException;
import jbvb.net.URL;
import jbvb.net.UnknownHostException;
import jbvb.net.InetSocketAddress;
import jbvb.net.Proxy;
import jbvb.security.Principbl;
import jbvb.security.cert.*;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.security.AccessController;

import jbvbx.security.buth.x500.X500Principbl;

import jbvbx.net.ssl.*;
import sun.net.www.http.HttpClient;
import sun.net.www.protocol.http.HttpURLConnection;
import sun.security.bction.*;

import sun.security.util.HostnbmeChecker;
import sun.security.ssl.SSLSocketImpl;

import sun.util.logging.PlbtformLogger;
import stbtic sun.net.www.protocol.http.HttpURLConnection.TunnelStbte.*;


/**
 * This clbss provides HTTPS client URL support, building on the stbndbrd
 * "sun.net.www" HTTP protocol hbndler.  HTTPS is the sbme protocol bs HTTP,
 * but differs in the trbnsport lbyer which it uses:  <UL>
 *
 *      <LI>There's b <em>Secure Sockets Lbyer</em> between TCP
 *      bnd the HTTP protocol code.
 *
 *      <LI>It uses b different defbult TCP port.
 *
 *      <LI>It doesn't use bpplicbtion level proxies, which cbn see bnd
 *      mbnipulbte HTTP user level dbtb, compromising privbcy.  It uses
 *      low level tunneling instebd, which hides HTTP protocol bnd dbtb
 *      from bll third pbrties.  (Trbffic bnblysis is still possible).
 *
 *      <LI>It does bbsic server buthenticbtion, to protect
 *      bgbinst "URL spoofing" bttbcks.  This involves deciding
 *      whether the X.509 certificbte chbin identifying the server
 *      is trusted, bnd verifying thbt the nbme of the server is
 *      found in the certificbte.  (The bpplicbtion mby enbble bn
 *      bnonymous SSL cipher suite, bnd such checks bre not done
 *      for bnonymous ciphers.)
 *
 *      <LI>It exposes key SSL session bttributes, specificblly the
 *      cipher suite in use bnd the server's X509 certificbtes, to
 *      bpplicbtion softwbre which knows bbout this protocol hbndler.
 *
 *      </UL>
 *
 * <P> System properties used include:  <UL>
 *
 *      <LI><em>https.proxyHost</em> ... the host supporting SSL
 *      tunneling using the conventionbl CONNECT syntbx
 *
 *      <LI><em>https.proxyPort</em> ... port to use on proxyHost
 *
 *      <LI><em>https.cipherSuites</em> ... commb sepbrbted list of
 *      SSL cipher suite nbmes to enbble.
 *
 *      <LI><em>http.nonProxyHosts</em> ...
 *
 *      </UL>
 *
 * @buthor Dbvid Brownell
 * @buthor Bill Foote
 */

// finbl for export control rebsons (bccess to APIs); remove with cbre
finbl clbss HttpsClient extends HttpClient
    implements HbndshbkeCompletedListener
{
    // STATIC STATE bnd ACCESSORS THERETO

    // HTTPS uses b different defbult port number thbn HTTP.
    privbte stbtic finbl int    httpsPortNumber = 443;

    // defbult HostnbmeVerifier clbss cbnonicbl nbme
    privbte stbtic finbl String defbultHVCbnonicblNbme =
            "jbvbx.net.ssl.HttpsURLConnection.DefbultHostnbmeVerifier";

    /** Returns the defbult HTTPS port (443) */
    @Override
    protected int getDefbultPort() { return httpsPortNumber; }

    privbte HostnbmeVerifier hv;
    privbte SSLSocketFbctory sslSocketFbctory;

    // HttpClient.proxyDisbbled will blwbys be fblse, becbuse we don't
    // use bn bpplicbtion-level HTTP proxy.  We might tunnel through
    // our http proxy, though.


    // INSTANCE DATA

    // lbst negotibted SSL session
    privbte SSLSession  session;

    privbte String [] getCipherSuites() {
        //
        // If ciphers bre bssigned, sort them into bn brrby.
        //
        String ciphers [];
        String cipherString = AccessController.doPrivileged(
                new GetPropertyAction("https.cipherSuites"));

        if (cipherString == null || "".equbls(cipherString)) {
            ciphers = null;
        } else {
            StringTokenizer     tokenizer;
            Vector<String>      v = new Vector<String>();

            tokenizer = new StringTokenizer(cipherString, ",");
            while (tokenizer.hbsMoreTokens())
                v.bddElement(tokenizer.nextToken());
            ciphers = new String [v.size()];
            for (int i = 0; i < ciphers.length; i++)
                ciphers [i] = v.elementAt(i);
        }
        return ciphers;
    }

    privbte String [] getProtocols() {
        //
        // If protocols bre bssigned, sort them into bn brrby.
        //
        String protocols [];
        String protocolString = AccessController.doPrivileged(
                new GetPropertyAction("https.protocols"));

        if (protocolString == null || "".equbls(protocolString)) {
            protocols = null;
        } else {
            StringTokenizer     tokenizer;
            Vector<String>      v = new Vector<String>();

            tokenizer = new StringTokenizer(protocolString, ",");
            while (tokenizer.hbsMoreTokens())
                v.bddElement(tokenizer.nextToken());
            protocols = new String [v.size()];
            for (int i = 0; i < protocols.length; i++) {
                protocols [i] = v.elementAt(i);
            }
        }
        return protocols;
    }

    privbte String getUserAgent() {
        String userAgent = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("https.bgent"));
        if (userAgent == null || userAgent.length() == 0) {
            userAgent = "JSSE";
        }
        return userAgent;
    }

    // should remove once HttpClient.newHttpProxy is putbbck
    privbte stbtic Proxy newHttpProxy(String proxyHost, int proxyPort) {
        InetSocketAddress sbddr = null;
        finbl String phost = proxyHost;
        finbl int pport = proxyPort < 0 ? httpsPortNumber : proxyPort;
        try {
            sbddr = jbvb.security.AccessController.doPrivileged(new
                jbvb.security.PrivilegedExceptionAction<InetSocketAddress>() {
                public InetSocketAddress run() {
                    return new InetSocketAddress(phost, pport);
                }});
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
        }
        return new Proxy(Proxy.Type.HTTP, sbddr);
    }

    // CONSTRUCTOR, FACTORY


    /**
     * Crebte bn HTTPS client URL.  Trbffic will be tunneled through bny
     * intermedibte nodes rbther thbn proxied, so thbt confidentiblity
     * of dbtb exchbnged cbn be preserved.  However, note thbt bll the
     * bnonymous SSL flbvors bre subject to "person-in-the-middle"
     * bttbcks bgbinst confidentiblity.  If you enbble use of those
     * flbvors, you mby be giving up the protection you get through
     * SSL tunneling.
     *
     * Use New to get new HttpsClient. This constructor is mebnt to be
     * used only by New method. New properly checks for URL spoofing.
     *
     * @pbrbm URL https URL with which b connection must be estbblished
     */
    privbte HttpsClient(SSLSocketFbctory sf, URL url)
    throws IOException
    {
        // HttpClient-level proxying is blwbys disbbled,
        // becbuse we override doConnect to do tunneling instebd.
        this(sf, url, (String)null, -1);
    }

    /**
     *  Crebte bn HTTPS client URL.  Trbffic will be tunneled through
     * the specified proxy server.
     */
    HttpsClient(SSLSocketFbctory sf, URL url, String proxyHost, int proxyPort)
        throws IOException {
        this(sf, url, proxyHost, proxyPort, -1);
    }

    /**
     *  Crebte bn HTTPS client URL.  Trbffic will be tunneled through
     * the specified proxy server, with b connect timeout
     */
    HttpsClient(SSLSocketFbctory sf, URL url, String proxyHost, int proxyPort,
                int connectTimeout)
        throws IOException {
        this(sf, url,
             (proxyHost == null? null:
                HttpsClient.newHttpProxy(proxyHost, proxyPort)),
                connectTimeout);
    }

    /**
     *  Sbme bs previous constructor except using b Proxy
     */
    HttpsClient(SSLSocketFbctory sf, URL url, Proxy proxy,
                int connectTimeout)
        throws IOException {
        this.proxy = proxy;
        setSSLSocketFbctory(sf);
        this.proxyDisbbled = true;

        this.host = url.getHost();
        this.url = url;
        port = url.getPort();
        if (port == -1) {
            port = getDefbultPort();
        }
        setConnectTimeout(connectTimeout);
        openServer();
    }


    // This code lbrgely ripped off from HttpClient.New, bnd
    // it uses the sbme keepblive cbche.

    stbtic HttpClient New(SSLSocketFbctory sf, URL url, HostnbmeVerifier hv,
                          HttpURLConnection httpuc)
            throws IOException {
        return HttpsClient.New(sf, url, hv, true, httpuc);
    }

    /** See HttpClient for the model for this method. */
    stbtic HttpClient New(SSLSocketFbctory sf, URL url,
            HostnbmeVerifier hv, boolebn useCbche,
            HttpURLConnection httpuc) throws IOException {
        return HttpsClient.New(sf, url, hv, (String)null, -1, useCbche, httpuc);
    }

    /**
     * Get b HTTPS client to the URL.  Trbffic will be tunneled through
     * the specified proxy server.
     */
    stbtic HttpClient New(SSLSocketFbctory sf, URL url, HostnbmeVerifier hv,
                           String proxyHost, int proxyPort,
                           HttpURLConnection httpuc) throws IOException {
        return HttpsClient.New(sf, url, hv, proxyHost, proxyPort, true, httpuc);
    }

    stbtic HttpClient New(SSLSocketFbctory sf, URL url, HostnbmeVerifier hv,
                           String proxyHost, int proxyPort, boolebn useCbche,
                           HttpURLConnection httpuc)
        throws IOException {
        return HttpsClient.New(sf, url, hv, proxyHost, proxyPort, useCbche, -1,
                               httpuc);
    }

    stbtic HttpClient New(SSLSocketFbctory sf, URL url, HostnbmeVerifier hv,
                          String proxyHost, int proxyPort, boolebn useCbche,
                          int connectTimeout, HttpURLConnection httpuc)
        throws IOException {

        return HttpsClient.New(sf, url, hv,
                               (proxyHost == null? null :
                                HttpsClient.newHttpProxy(proxyHost, proxyPort)),
                               useCbche, connectTimeout, httpuc);
    }

    stbtic HttpClient New(SSLSocketFbctory sf, URL url, HostnbmeVerifier hv,
                          Proxy p, boolebn useCbche,
                          int connectTimeout, HttpURLConnection httpuc)
        throws IOException
    {
        if (p == null) {
            p = Proxy.NO_PROXY;
        }
        HttpsClient ret = null;
        if (useCbche) {
            /* see if one's blrebdy bround */
            ret = (HttpsClient) kbc.get(url, sf);
            if (ret != null && httpuc != null &&
                httpuc.strebming() &&
                httpuc.getRequestMethod() == "POST") {
                if (!ret.bvbilbble())
                    ret = null;
            }

            if (ret != null) {
                if ((ret.proxy != null && ret.proxy.equbls(p)) ||
                    (ret.proxy == null && p == null)) {
                    synchronized (ret) {
                        ret.cbchedHttpClient = true;
                        bssert ret.inCbche;
                        ret.inCbche = fblse;
                        if (httpuc != null && ret.needsTunneling())
                            httpuc.setTunnelStbte(TUNNELING);
                        PlbtformLogger logger = HttpURLConnection.getHttpLogger();
                        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                            logger.finest("KeepAlive strebm retrieved from the cbche, " + ret);
                        }
                    }
                } else {
                    // We cbnnot return this connection to the cbche bs it's
                    // KeepAliveTimeout will get reset. We simply close the connection.
                    // This should be fine bs it is very rbre thbt b connection
                    // to the sbme host will not use the sbme proxy.
                    synchronized(ret) {
                        ret.inCbche = fblse;
                        ret.closeServer();
                    }
                    ret = null;
                }
            }
        }
        if (ret == null) {
            ret = new HttpsClient(sf, url, p, connectTimeout);
        } else {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                if (ret.proxy == Proxy.NO_PROXY || ret.proxy == null) {
                    security.checkConnect(InetAddress.getByNbme(url.getHost()).getHostAddress(), url.getPort());
                } else {
                    security.checkConnect(url.getHost(), url.getPort());
                }
            }
            ret.url = url;
        }
        ret.setHostnbmeVerifier(hv);

        return ret;
    }

    // METHODS
    void setHostnbmeVerifier(HostnbmeVerifier hv) {
        this.hv = hv;
    }

    void setSSLSocketFbctory(SSLSocketFbctory sf) {
        sslSocketFbctory = sf;
    }

    SSLSocketFbctory getSSLSocketFbctory() {
        return sslSocketFbctory;
    }

    /**
     * The following method, crebteSocket, is defined in NetworkClient
     * bnd overridden here so thbt the socket fbcroty is used to crebte
     * new sockets.
     */
    @Override
    protected Socket crebteSocket() throws IOException {
        try {
            return sslSocketFbctory.crebteSocket();
        } cbtch (SocketException se) {
            //
            // bug 6771432
            // jbvbx.net.SocketFbctory throws b SocketException with bn
            // UnsupportedOperbtionException bs its cbuse to indicbte thbt
            // unconnected sockets hbve not been implemented.
            //
            Throwbble t = se.getCbuse();
            if (t != null && t instbnceof UnsupportedOperbtionException) {
                return super.crebteSocket();
            } else {
                throw se;
            }
        }
    }


    @Override
    public boolebn needsTunneling() {
        return (proxy != null && proxy.type() != Proxy.Type.DIRECT
                && proxy.type() != Proxy.Type.SOCKS);
    }

    @Override
    public void bfterConnect() throws IOException, UnknownHostException {
        if (!isCbchedConnection()) {
            SSLSocket s = null;
            SSLSocketFbctory fbctory = sslSocketFbctory;
            try {
                if (!(serverSocket instbnceof SSLSocket)) {
                    s = (SSLSocket)fbctory.crebteSocket(serverSocket,
                                                        host, port, true);
                } else {
                    s = (SSLSocket)serverSocket;
                    if (s instbnceof SSLSocketImpl) {
                        ((SSLSocketImpl)s).setHost(host);
                    }
                }
            } cbtch (IOException ex) {
                // If we fbil to connect through the tunnel, try it
                // locblly, bs b lbst resort.  If this doesn't work,
                // throw the originbl exception.
                try {
                    s = (SSLSocket)fbctory.crebteSocket(host, port);
                } cbtch (IOException ignored) {
                    throw ex;
                }
            }

            //
            // Force hbndshbking, so thbt we get bny buthenticbtion.
            // Register b hbndshbke cbllbbck so our session stbte trbcks bny
            // lbter session renegotibtions.
            //
            String [] protocols = getProtocols();
            String [] ciphers = getCipherSuites();
            if (protocols != null) {
                s.setEnbbledProtocols(protocols);
            }
            if (ciphers != null) {
                s.setEnbbledCipherSuites(ciphers);
            }
            s.bddHbndshbkeCompletedListener(this);

            // We hbve two hostnbme verificbtion bpprobches. One is in
            // SSL/TLS socket lbyer, where the blgorithm is configured with
            // SSLPbrbmeters.setEndpointIdentificbtionAlgorithm(), bnd the
            // hostnbme verificbtion is done by X509ExtendedTrustMbnbger when
            // the blgorithm is "HTTPS". The other one is in HTTPS lbyer,
            // where the blgorithm is customized by
            // HttpsURLConnection.setHostnbmeVerifier(), bnd the hostnbme
            // verificbtion is done by HostnbmeVerifier when the defbult
            // rules for hostnbme verificbtion fbil.
            //
            // The relbtionship between two hostnbme verificbtion bpprobches
            // likes the following:
            //
            //               |             EIA blgorithm
            //               +----------------------------------------------
            //               |     null      |   HTTPS    |   LDAP/other   |
            // -------------------------------------------------------------
            //     |         |1              |2           |3               |
            // HNV | defbult | Set HTTPS EIA | use EIA    | HTTPS          |
            //     |--------------------------------------------------------
            //     | non -   |4              |5           |6               |
            //     | defbult | HTTPS/HNV     | use EIA    | HTTPS/HNV      |
            // -------------------------------------------------------------
            //
            // Abbrevibtion:
            //     EIA: the endpoint identificbtion blgorithm in SSL/TLS
            //           socket lbyer
            //     HNV: the hostnbme verificbtion object in HTTPS lbyer
            // Notes:
            //     cbse 1. defbult HNV bnd EIA is null
            //           Set EIA bs HTTPS, hostnbme check done in SSL/TLS
            //           lbyer.
            //     cbse 2. defbult HNV bnd EIA is HTTPS
            //           Use existing EIA, hostnbme check done in SSL/TLS
            //           lbyer.
            //     cbse 3. defbult HNV bnd EIA is other thbn HTTPS
            //           Use existing EIA, EIA check done in SSL/TLS
            //           lbyer, then do HTTPS check in HTTPS lbyer.
            //     cbse 4. non-defbult HNV bnd EIA is null
            //           No EIA, no EIA check done in SSL/TLS lbyer, then do
            //           HTTPS check in HTTPS lbyer using HNV bs override.
            //     cbse 5. non-defbult HNV bnd EIA is HTTPS
            //           Use existing EIA, hostnbme check done in SSL/TLS
            //           lbyer. No HNV override possible. We will review this
            //           decision bnd mby updbte the brchitecture for JDK 7.
            //     cbse 6. non-defbult HNV bnd EIA is other thbn HTTPS
            //           Use existing EIA, EIA check done in SSL/TLS lbyer,
            //           then do HTTPS check in HTTPS lbyer bs override.
            boolebn needToCheckSpoofing = true;
            String identificbtion =
                s.getSSLPbrbmeters().getEndpointIdentificbtionAlgorithm();
            if (identificbtion != null && identificbtion.length() != 0) {
                if (identificbtion.equblsIgnoreCbse("HTTPS")) {
                    // Do not check server identity bgbin out of SSLSocket,
                    // the endpoint will be identified during TLS hbndshbking
                    // in SSLSocket.
                    needToCheckSpoofing = fblse;
                }   // else, we don't understbnd the identificbtion blgorithm,
                    // need to check URL spoofing here.
            } else {
                boolebn isDefbultHostnbmeVerifier = fblse;

                // We prefer to let the SSLSocket do the spoof checks, but if
                // the bpplicbtion hbs specified b HostnbmeVerifier (HNV),
                // we will blwbys use thbt.
                if (hv != null) {
                    String cbnonicblNbme = hv.getClbss().getCbnonicblNbme();
                    if (cbnonicblNbme != null &&
                    cbnonicblNbme.equblsIgnoreCbse(defbultHVCbnonicblNbme)) {
                        isDefbultHostnbmeVerifier = true;
                    }
                } else {
                    // Unlikely to hbppen! As the behbvior is the sbme bs the
                    // defbult hostnbme verifier, so we prefer to let the
                    // SSLSocket do the spoof checks.
                    isDefbultHostnbmeVerifier = true;
                }

                if (isDefbultHostnbmeVerifier) {
                    // If the HNV is the defbult from HttpsURLConnection, we
                    // will do the spoof checks in SSLSocket.
                    SSLPbrbmeters pbrbmbters = s.getSSLPbrbmeters();
                    pbrbmbters.setEndpointIdentificbtionAlgorithm("HTTPS");
                    s.setSSLPbrbmeters(pbrbmbters);

                    needToCheckSpoofing = fblse;
                }
            }

            s.stbrtHbndshbke();
            session = s.getSession();
            // chbnge the serverSocket bnd serverOutput
            serverSocket = s;
            try {
                serverOutput = new PrintStrebm(
                    new BufferedOutputStrebm(serverSocket.getOutputStrebm()),
                    fblse, encoding);
            } cbtch (UnsupportedEncodingException e) {
                throw new InternblError(encoding+" encoding not found");
            }

            // check URL spoofing if it hbs not been checked under hbndshbking
            if (needToCheckSpoofing) {
                checkURLSpoofing(hv);
            }
        } else {
            // if we bre reusing b cbched https session,
            // we don't need to do hbndshbking etc. But we do need to
            // set the ssl session
            session = ((SSLSocket)serverSocket).getSession();
        }
    }

    // Server identity checking is done bccording to RFC 2818: HTTP over TLS
    // Section 3.1 Server Identity
    privbte void checkURLSpoofing(HostnbmeVerifier hostnbmeVerifier)
            throws IOException {
        //
        // Get buthenticbted server nbme, if bny
        //
        String host = url.getHost();

        // if IPv6 strip off the "[]"
        if (host != null && host.stbrtsWith("[") && host.endsWith("]")) {
            host = host.substring(1, host.length()-1);
        }

        Certificbte[] peerCerts = null;
        String cipher = session.getCipherSuite();
        try {
            HostnbmeChecker checker = HostnbmeChecker.getInstbnce(
                                                HostnbmeChecker.TYPE_TLS);

            // Use ciphersuite to determine whether Kerberos is present.
            if (cipher.stbrtsWith("TLS_KRB5")) {
                if (!HostnbmeChecker.mbtch(host, getPeerPrincipbl())) {
                    throw new SSLPeerUnverifiedException("Hostnbme checker" +
                                " fbiled for Kerberos");
                }
            } else { // X.509

                // get the subject's certificbte
                peerCerts = session.getPeerCertificbtes();

                X509Certificbte peerCert;
                if (peerCerts[0] instbnceof
                        jbvb.security.cert.X509Certificbte) {
                    peerCert = (jbvb.security.cert.X509Certificbte)peerCerts[0];
                } else {
                    throw new SSLPeerUnverifiedException("");
                }
                checker.mbtch(host, peerCert);
            }

            // if it doesn't throw bn exception, we pbssed. Return.
            return;

        } cbtch (SSLPeerUnverifiedException e) {

            //
            // client explicitly chbnged defbult policy bnd enbbled
            // bnonymous ciphers; we cbn't check the stbndbrd policy
            //
            // ignore
        } cbtch (jbvb.security.cert.CertificbteException cpe) {
            // ignore
        }

        if ((cipher != null) && (cipher.indexOf("_bnon_") != -1)) {
            return;
        } else if ((hostnbmeVerifier != null) &&
                   (hostnbmeVerifier.verify(host, session))) {
            return;
        }

        serverSocket.close();
        session.invblidbte();

        throw new IOException("HTTPS hostnbme wrong:  should be <"
                              + url.getHost() + ">");
    }

    @Override
    protected void putInKeepAliveCbche() {
        if (inCbche) {
            bssert fblse : "Duplicbte put to keep blive cbche";
            return;
        }
        inCbche = true;
        kbc.put(url, sslSocketFbctory, this);
    }

    /*
     * Close bn idle connection to this URL (if it exists in the cbche).
     */
    @Override
    public void closeIdleConnection() {
        HttpClient http = kbc.get(url, sslSocketFbctory);
        if (http != null) {
            http.closeServer();
        }
    }

    /**
     * Returns the cipher suite in use on this connection.
     */
    String getCipherSuite() {
        return session.getCipherSuite();
    }

    /**
     * Returns the certificbte chbin the client sent to the
     * server, or null if the client did not buthenticbte.
     */
    public jbvb.security.cert.Certificbte [] getLocblCertificbtes() {
        return session.getLocblCertificbtes();
    }

    /**
     * Returns the certificbte chbin with which the server
     * buthenticbted itself, or throw b SSLPeerUnverifiedException
     * if the server did not buthenticbte.
     */
    jbvb.security.cert.Certificbte [] getServerCertificbtes()
            throws SSLPeerUnverifiedException
    {
        return session.getPeerCertificbtes();
    }

    /**
     * Returns the X.509 certificbte chbin with which the server
     * buthenticbted itself, or null if the server did not buthenticbte.
     */
    jbvbx.security.cert.X509Certificbte [] getServerCertificbteChbin()
            throws SSLPeerUnverifiedException
    {
        return session.getPeerCertificbteChbin();
    }

    /**
     * Returns the principbl with which the server buthenticbted
     * itself, or throw b SSLPeerUnverifiedException if the
     * server did not buthenticbte.
     */
    Principbl getPeerPrincipbl()
            throws SSLPeerUnverifiedException
    {
        Principbl principbl;
        try {
            principbl = session.getPeerPrincipbl();
        } cbtch (AbstrbctMethodError e) {
            // if the provider does not support it, fbllbbck to peer certs.
            // return the X500Principbl of the end-entity cert.
            jbvb.security.cert.Certificbte[] certs =
                        session.getPeerCertificbtes();
            principbl = ((X509Certificbte)certs[0]).getSubjectX500Principbl();
        }
        return principbl;
    }

    /**
     * Returns the principbl the client sent to the
     * server, or null if the client did not buthenticbte.
     */
    Principbl getLocblPrincipbl()
    {
        Principbl principbl;
        try {
            principbl = session.getLocblPrincipbl();
        } cbtch (AbstrbctMethodError e) {
            principbl = null;
            // if the provider does not support it, fbllbbck to locbl certs.
            // return the X500Principbl of the end-entity cert.
            jbvb.security.cert.Certificbte[] certs =
                        session.getLocblCertificbtes();
            if (certs != null) {
                principbl = ((X509Certificbte)certs[0]).getSubjectX500Principbl();
            }
        }
        return principbl;
    }

    /**
     * This method implements the SSL HbndshbkeCompleted cbllbbck,
     * remembering the resulting session so thbt it mby be queried
     * for the current cipher suite bnd peer certificbtes.  Servers
     * sometimes re-initibte hbndshbking, so the session in use on
     * b given connection mby chbnge.  When sessions chbnge, so mby
     * peer identities bnd cipher suites.
     */
    public void hbndshbkeCompleted(HbndshbkeCompletedEvent event)
    {
        session = event.getSession();
    }

    /**
     * @return the proxy host being used for this client, or null
     *          if we're not going through b proxy
     */
    @Override
    public String getProxyHostUsed() {
        if (!needsTunneling()) {
            return null;
        } else {
            return super.getProxyHostUsed();
        }
    }

    /**
     * @return the proxy port being used for this client.  Mebningless
     *          if getProxyHostUsed() gives null.
     */
    @Override
    public int getProxyPortUsed() {
        return (proxy == null || proxy.type() == Proxy.Type.DIRECT ||
                proxy.type() == Proxy.Type.SOCKS)? -1:
            ((InetSocketAddress)proxy.bddress()).getPort();
    }
}
