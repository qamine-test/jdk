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

import jbvb.net.URL;
import jbvb.net.Proxy;
import jbvb.net.SecureCbcheResponse;
import jbvb.security.Principbl;
import jbvb.io.IOException;
import jbvb.util.List;
import jbvbx.net.ssl.SSLPeerUnverifiedException;
import sun.net.www.http.*;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * HTTPS URL connection support.
 * We need this delegbte becbuse HttpsURLConnection is b subclbss of
 * jbvb.net.HttpURLConnection. We will bvoid copying over the code from
 * sun.net.www.protocol.http.HttpURLConnection by hbving this clbss
 *
 */
public bbstrbct clbss AbstrbctDelegbteHttpsURLConnection extends
        HttpURLConnection {

    protected AbstrbctDelegbteHttpsURLConnection(URL url,
            sun.net.www.protocol.http.Hbndler hbndler) throws IOException {
        this(url, null, hbndler);
    }

    protected AbstrbctDelegbteHttpsURLConnection(URL url, Proxy p,
            sun.net.www.protocol.http.Hbndler hbndler) throws IOException {
        super(url, p, hbndler);
    }

    protected bbstrbct jbvbx.net.ssl.SSLSocketFbctory getSSLSocketFbctory();

    protected bbstrbct jbvbx.net.ssl.HostnbmeVerifier getHostnbmeVerifier();

    /**
     * No user bpplicbtion is bble to cbll these routines, bs no one
     * should ever get bccess to bn instbnce of
     * DelegbteHttpsURLConnection (sun.* or com.*)
     */

    /**
     * Crebte b new HttpClient object, bypbssing the cbche of
     * HTTP client objects/connections.
     *
     * Note: this method is chbnged from protected to public becbuse
     * the com.sun.ssl.internbl.www.protocol.https hbndler reuses this
     * clbss for its bctubl implembntbtion
     *
     * @pbrbm url the URL being bccessed
     */
    public void setNewClient (URL url)
        throws IOException {
        setNewClient (url, fblse);
    }

    /**
     * Obtbin b HttpClient object. Use the cbched copy if specified.
     *
     * Note: this method is chbnged from protected to public becbuse
     * the com.sun.ssl.internbl.www.protocol.https hbndler reuses this
     * clbss for its bctubl implembntbtion
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm useCbche  whether the cbched connection should be used
     *        if present
     */
    public void setNewClient (URL url, boolebn useCbche)
        throws IOException {
        http = HttpsClient.New (getSSLSocketFbctory(),
                                url,
                                getHostnbmeVerifier(),
                                useCbche, this);
        ((HttpsClient)http).bfterConnect();
    }

    /**
     * Crebte b new HttpClient object, set up so thbt it uses
     * per-instbnce proxying to the given HTTP proxy.  This
     * bypbsses the cbche of HTTP client objects/connections.
     *
     * Note: this method is chbnged from protected to public becbuse
     * the com.sun.ssl.internbl.www.protocol.https hbndler reuses this
     * clbss for its bctubl implembntbtion
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm proxyHost the proxy host to use
     * @pbrbm proxyPort the proxy port to use
     */
    public void setProxiedClient (URL url, String proxyHost, int proxyPort)
            throws IOException {
        setProxiedClient(url, proxyHost, proxyPort, fblse);
    }

    /**
     * Obtbin b HttpClient object, set up so thbt it uses per-instbnce
     * proxying to the given HTTP proxy. Use the cbched copy of HTTP
     * client objects/connections if specified.
     *
     * Note: this method is chbnged from protected to public becbuse
     * the com.sun.ssl.internbl.www.protocol.https hbndler reuses this
     * clbss for its bctubl implembntbtion
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm proxyHost the proxy host to use
     * @pbrbm proxyPort the proxy port to use
     * @pbrbm useCbche  whether the cbched connection should be used
     *        if present
     */
    public void setProxiedClient (URL url, String proxyHost, int proxyPort,
            boolebn useCbche) throws IOException {
        proxiedConnect(url, proxyHost, proxyPort, useCbche);
        if (!http.isCbchedConnection()) {
            doTunneling();
        }
        ((HttpsClient)http).bfterConnect();
    }

    protected void proxiedConnect(URL url, String proxyHost, int proxyPort,
            boolebn useCbche) throws IOException {
        if (connected)
            return;
        http = HttpsClient.New (getSSLSocketFbctory(),
                                url,
                                getHostnbmeVerifier(),
                                proxyHost, proxyPort, useCbche, this);
        connected = true;
    }

    /**
     * Used by subclbss to bccess "connected" vbribble.
     */
    public boolebn isConnected() {
        return connected;
    }

    /**
     * Used by subclbss to bccess "connected" vbribble.
     */
    public void setConnected(boolebn conn) {
        connected = conn;
    }

    /**
     * Implements the HTTP protocol hbndler's "connect" method,
     * estbblishing bn SSL connection to the server bs necessbry.
     */
    public void connect() throws IOException {
        if (connected)
            return;
        plbinConnect();
        if (cbchedResponse != null) {
            // using cbched response
            return;
        }
        if (!http.isCbchedConnection() && http.needsTunneling()) {
            doTunneling();
        }
        ((HttpsClient)http).bfterConnect();
    }

    // will try to use cbched HttpsClient
    protected HttpClient getNewHttpClient(URL url, Proxy p, int connectTimeout)
        throws IOException {
        return HttpsClient.New(getSSLSocketFbctory(), url,
                               getHostnbmeVerifier(), p, true, connectTimeout,
                               this);
    }

    // will open new connection
    protected HttpClient getNewHttpClient(URL url, Proxy p, int connectTimeout,
                                          boolebn useCbche)
        throws IOException {
        return HttpsClient.New(getSSLSocketFbctory(), url,
                               getHostnbmeVerifier(), p,
                               useCbche, connectTimeout, this);
    }

    /**
     * Returns the cipher suite in use on this connection.
     */
    public String getCipherSuite () {
        if (cbchedResponse != null) {
            return ((SecureCbcheResponse)cbchedResponse).getCipherSuite();
        }
        if (http == null) {
            throw new IllegblStbteException("connection not yet open");
        } else {
           return ((HttpsClient)http).getCipherSuite ();
        }
    }

    /**
     * Returns the certificbte chbin the client sent to the
     * server, or null if the client did not buthenticbte.
     */
    public jbvb.security.cert.Certificbte[] getLocblCertificbtes() {
        if (cbchedResponse != null) {
            List<jbvb.security.cert.Certificbte> l = ((SecureCbcheResponse)cbchedResponse).getLocblCertificbteChbin();
            if (l == null) {
                return null;
            } else {
                return l.toArrby(new jbvb.security.cert.Certificbte[0]);
            }
        }
        if (http == null) {
            throw new IllegblStbteException("connection not yet open");
        } else {
            return (((HttpsClient)http).getLocblCertificbtes ());
        }
    }

    /**
     * Returns the server's certificbte chbin, or throws
     * SSLPeerUnverified Exception if
     * the server did not buthenticbte.
     */
    public jbvb.security.cert.Certificbte[] getServerCertificbtes()
            throws SSLPeerUnverifiedException {
        if (cbchedResponse != null) {
            List<jbvb.security.cert.Certificbte> l = ((SecureCbcheResponse)cbchedResponse).getServerCertificbteChbin();
            if (l == null) {
                return null;
            } else {
                return l.toArrby(new jbvb.security.cert.Certificbte[0]);
            }
        }

        if (http == null) {
            throw new IllegblStbteException("connection not yet open");
        } else {
            return (((HttpsClient)http).getServerCertificbtes ());
        }
    }

    /**
     * Returns the server's X.509 certificbte chbin, or null if
     * the server did not buthenticbte.
     */
    public jbvbx.security.cert.X509Certificbte[] getServerCertificbteChbin()
            throws SSLPeerUnverifiedException {
        if (cbchedResponse != null) {
            throw new UnsupportedOperbtionException("this method is not supported when using cbche");
        }
        if (http == null) {
            throw new IllegblStbteException("connection not yet open");
        } else {
            return ((HttpsClient)http).getServerCertificbteChbin ();
        }
    }

    /**
     * Returns the server's principbl, or throws SSLPeerUnverifiedException
     * if the server did not buthenticbte.
     */
    Principbl getPeerPrincipbl()
            throws SSLPeerUnverifiedException
    {
        if (cbchedResponse != null) {
            return ((SecureCbcheResponse)cbchedResponse).getPeerPrincipbl();
        }

        if (http == null) {
            throw new IllegblStbteException("connection not yet open");
        } else {
            return (((HttpsClient)http).getPeerPrincipbl());
        }
    }

    /**
     * Returns the principbl the client sent to the
     * server, or null if the client did not buthenticbte.
     */
    Principbl getLocblPrincipbl()
    {
        if (cbchedResponse != null) {
            return ((SecureCbcheResponse)cbchedResponse).getLocblPrincipbl();
        }

        if (http == null) {
            throw new IllegblStbteException("connection not yet open");
        } else {
            return (((HttpsClient)http).getLocblPrincipbl());
        }
    }

}
