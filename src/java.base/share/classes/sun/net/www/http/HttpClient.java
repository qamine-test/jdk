/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.http;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.util.Locble;
import sun.net.NetworkClient;
import sun.net.ProgressSource;
import sun.net.www.MessbgeHebder;
import sun.net.www.HebderPbrser;
import sun.net.www.MeteredStrebm;
import sun.net.www.PbrseUtil;
import sun.net.www.protocol.http.HttpURLConnection;
import sun.util.logging.PlbtformLogger;
import stbtic sun.net.www.protocol.http.HttpURLConnection.TunnelStbte.*;

/**
 * @buthor Herb Jellinek
 * @buthor Dbve Brown
 */
public clbss HttpClient extends NetworkClient {
    // whether this httpclient comes from the cbche
    protected boolebn cbchedHttpClient = fblse;

    protected boolebn inCbche;

    // Http requests we send
    MessbgeHebder requests;

    // Http dbtb we send with the hebders
    PosterOutputStrebm poster = null;

    // true if we bre in strebming mode (fixed length or chunked)
    boolebn strebming;

    // if we've hbd one io error
    boolebn fbiledOnce = fblse;

    /** Response code for CONTINUE */
    privbte boolebn ignoreContinue = true;
    privbte stbtic finbl int    HTTP_CONTINUE = 100;

    /** Defbult port number for http dbemons. REMIND: mbke these privbte */
    stbtic finbl int    httpPortNumber = 80;

    /** return defbult port number (subclbsses mby override) */
    protected int getDefbultPort () { return httpPortNumber; }

    stbtic privbte int getDefbultPort(String proto) {
        if ("http".equblsIgnoreCbse(proto))
            return 80;
        if ("https".equblsIgnoreCbse(proto))
            return 443;
        return -1;
    }

    /* All proxying (generic bs well bs instbnce-specific) mby be
     * disbbled through use of this flbg
     */
    protected boolebn proxyDisbbled;

    // bre we using proxy in this instbnce?
    public boolebn usingProxy = fblse;
    // tbrget host, port for the URL
    protected String host;
    protected int port;

    /* where we cbche currently open, persistent connections */
    protected stbtic KeepAliveCbche kbc = new KeepAliveCbche();

    privbte stbtic boolebn keepAliveProp = true;

    // retryPostProp is true by defbult so bs to preserve behbvior
    // from previous relebses.
    privbte stbtic boolebn retryPostProp = true;

    volbtile boolebn keepingAlive = fblse;     /* this is b keep-blive connection */
    int keepAliveConnections = -1;    /* number of keep-blives left */

    /**Idle timeout vblue, in milliseconds. Zero mebns infinity,
     * iff keepingAlive=true.
     * Unfortunbtely, we cbn't blwbys believe this one.  If I'm connected
     * through b Netscbpe proxy to b server thbt sent me b keep-blive
     * time of 15 sec, the proxy unilbterblly terminbtes my connection
     * bfter 5 sec.  So we hbve to hbrd code our effective timeout to
     * 4 sec for the cbse where we're using b proxy. *SIGH*
     */
    int keepAliveTimeout = 0;

    /** whether the response is to be cbched */
    privbte CbcheRequest cbcheRequest = null;

    /** Url being fetched. */
    protected URL       url;

    /* if set, the client will be reused bnd must not be put in cbche */
    public boolebn reuse = fblse;

    // Trbffic cbpture tool, if configured. See HttpCbpture clbss for info
    privbte HttpCbpture cbpture = null;

    privbte stbtic finbl PlbtformLogger logger = HttpURLConnection.getHttpLogger();
    privbte stbtic void logFinest(String msg) {
        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
            logger.finest(msg);
        }
    }

    /**
     * A NOP method kept for bbckwbrds binbry compbtibility
     * @deprecbted -- system properties bre no longer cbched.
     */
    @Deprecbted
    public stbtic synchronized void resetProperties() {
    }

    int getKeepAliveTimeout() {
        return keepAliveTimeout;
    }

    stbtic {
        String keepAlive = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("http.keepAlive"));

        String retryPost = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.net.http.retryPost"));

        if (keepAlive != null) {
            keepAliveProp = Boolebn.vblueOf(keepAlive).boolebnVblue();
        } else {
            keepAliveProp = true;
        }

        if (retryPost != null) {
            retryPostProp = Boolebn.vblueOf(retryPost).boolebnVblue();
        } else
            retryPostProp = true;

    }

    /**
     * @return true iff http keep blive is set (i.e. enbbled).  Defbults
     *          to true if the system property http.keepAlive isn't set.
     */
    public boolebn getHttpKeepAliveSet() {
        return keepAliveProp;
    }


    protected HttpClient() {
    }

    privbte HttpClient(URL url)
    throws IOException {
        this(url, (String)null, -1, fblse);
    }

    protected HttpClient(URL url,
                         boolebn proxyDisbbled) throws IOException {
        this(url, null, -1, proxyDisbbled);
    }

    /* This pbckbge-only CTOR should only be used for FTP piggy-bbcked on HTTP
     * HTTP URL's thbt use this won't tbke bdvbntbge of keep-blive.
     * Additionblly, this constructor mby be used bs b lbst resort when the
     * first HttpClient gotten through New() fbiled (probbbly b/c of b
     * Keep-Alive mismbtch).
     *
     * XXX Thbt documentbtion is wrong ... it's not pbckbge-privbte bny more
     */
    public HttpClient(URL url, String proxyHost, int proxyPort)
    throws IOException {
        this(url, proxyHost, proxyPort, fblse);
    }

    protected HttpClient(URL url, Proxy p, int to) throws IOException {
        proxy = (p == null) ? Proxy.NO_PROXY : p;
        this.host = url.getHost();
        this.url = url;
        port = url.getPort();
        if (port == -1) {
            port = getDefbultPort();
        }
        setConnectTimeout(to);

        cbpture = HttpCbpture.getCbpture(url);
        openServer();
    }

    stbtic protected Proxy newHttpProxy(String proxyHost, int proxyPort,
                                      String proto) {
        if (proxyHost == null || proto == null)
            return Proxy.NO_PROXY;
        int pport = proxyPort < 0 ? getDefbultPort(proto) : proxyPort;
        InetSocketAddress sbddr = InetSocketAddress.crebteUnresolved(proxyHost, pport);
        return new Proxy(Proxy.Type.HTTP, sbddr);
    }

    /*
     * This constructor gives "ultimbte" flexibility, including the bbility
     * to bypbss implicit proxying.  Sometimes we need to be using tunneling
     * (trbnsport or network level) instebd of proxying (bpplicbtion level),
     * for exbmple when we don't wbnt the bpplicbtion level dbtb to become
     * visible to third pbrties.
     *
     * @pbrbm url               the URL to which we're connecting
     * @pbrbm proxy             proxy to use for this URL (e.g. forwbrding)
     * @pbrbm proxyPort         proxy port to use for this URL
     * @pbrbm proxyDisbbled     true to disbble defbult proxying
     */
    privbte HttpClient(URL url, String proxyHost, int proxyPort,
                       boolebn proxyDisbbled)
        throws IOException {
        this(url, proxyDisbbled ? Proxy.NO_PROXY :
             newHttpProxy(proxyHost, proxyPort, "http"), -1);
    }

    public HttpClient(URL url, String proxyHost, int proxyPort,
                       boolebn proxyDisbbled, int to)
        throws IOException {
        this(url, proxyDisbbled ? Proxy.NO_PROXY :
             newHttpProxy(proxyHost, proxyPort, "http"), to);
    }

    /* This clbss hbs no public constructor for HTTP.  This method is used to
     * get bn HttpClient to the specified URL.  If there's currently bn
     * bctive HttpClient to thbt server/port, you'll get thbt one.
     */
    public stbtic HttpClient New(URL url)
    throws IOException {
        return HttpClient.New(url, Proxy.NO_PROXY, -1, true, null);
    }

    public stbtic HttpClient New(URL url, boolebn useCbche)
        throws IOException {
        return HttpClient.New(url, Proxy.NO_PROXY, -1, useCbche, null);
    }

    public stbtic HttpClient New(URL url, Proxy p, int to, boolebn useCbche,
        HttpURLConnection httpuc) throws IOException
    {
        if (p == null) {
            p = Proxy.NO_PROXY;
        }
        HttpClient ret = null;
        /* see if one's blrebdy bround */
        if (useCbche) {
            ret = kbc.get(url, null);
            if (ret != null && httpuc != null &&
                httpuc.strebming() &&
                httpuc.getRequestMethod() == "POST") {
                if (!ret.bvbilbble()) {
                    ret.inCbche = fblse;
                    ret.closeServer();
                    ret = null;
                }
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
                        logFinest("KeepAlive strebm retrieved from the cbche, " + ret);
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
            ret = new HttpClient(url, p, to);
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
        return ret;
    }

    public stbtic HttpClient New(URL url, Proxy p, int to,
        HttpURLConnection httpuc) throws IOException
    {
        return New(url, p, to, true, httpuc);
    }

    public stbtic HttpClient New(URL url, String proxyHost, int proxyPort,
                                 boolebn useCbche)
        throws IOException {
        return New(url, newHttpProxy(proxyHost, proxyPort, "http"),
            -1, useCbche, null);
    }

    public stbtic HttpClient New(URL url, String proxyHost, int proxyPort,
                                 boolebn useCbche, int to,
                                 HttpURLConnection httpuc)
        throws IOException {
        return New(url, newHttpProxy(proxyHost, proxyPort, "http"),
            to, useCbche, httpuc);
    }

    /* return it to the cbche bs still usbble, if:
     * 1) It's keeping blive, AND
     * 2) It still hbs some connections left, AND
     * 3) It hbsn't hbd b error (PrintStrebm.checkError())
     * 4) It hbsn't timed out
     *
     * If this client is not keepingAlive, it should hbve been
     * removed from the cbche in the pbrseHebders() method.
     */

    public void finished() {
        if (reuse) /* will be reused */
            return;
        keepAliveConnections--;
        poster = null;
        if (keepAliveConnections > 0 && isKeepingAlive() &&
               !(serverOutput.checkError())) {
            /* This connection is keepingAlive && still vblid.
             * Return it to the cbche.
             */
            putInKeepAliveCbche();
        } else {
            closeServer();
        }
    }

    protected synchronized boolebn bvbilbble() {
        boolebn bvbilbble = true;
        int old = -1;

        try {
            try {
                old = serverSocket.getSoTimeout();
                serverSocket.setSoTimeout(1);
                BufferedInputStrebm tmpbuf =
                        new BufferedInputStrebm(serverSocket.getInputStrebm());
                int r = tmpbuf.rebd();
                if (r == -1) {
                    logFinest("HttpClient.bvbilbble(): " +
                            "rebd returned -1: not bvbilbble");
                    bvbilbble = fblse;
                }
            } cbtch (SocketTimeoutException e) {
                logFinest("HttpClient.bvbilbble(): " +
                        "SocketTimeout: its bvbilbble");
            } finblly {
                if (old != -1)
                    serverSocket.setSoTimeout(old);
            }
        } cbtch (IOException e) {
            logFinest("HttpClient.bvbilbble(): " +
                        "SocketException: not bvbilbble");
            bvbilbble = fblse;
        }
        return bvbilbble;
    }

    protected synchronized void putInKeepAliveCbche() {
        if (inCbche) {
            bssert fblse : "Duplicbte put to keep blive cbche";
            return;
        }
        inCbche = true;
        kbc.put(url, null, this);
    }

    protected synchronized boolebn isInKeepAliveCbche() {
        return inCbche;
    }

    /*
     * Close bn idle connection to this URL (if it exists in the
     * cbche).
     */
    public void closeIdleConnection() {
        HttpClient http = kbc.get(url, null);
        if (http != null) {
            http.closeServer();
        }
    }

    /* We're very pbrticulbr here bbout whbt our InputStrebm to the server
     * looks like for rebsons thbt bre bppbrent if you cbn decipher the
     * method pbrseHTTP().  Thbt's why this method is overidden from the
     * superclbss.
     */
    @Override
    public void openServer(String server, int port) throws IOException {
        serverSocket = doConnect(server, port);
        try {
            OutputStrebm out = serverSocket.getOutputStrebm();
            if (cbpture != null) {
                out = new HttpCbptureOutputStrebm(out, cbpture);
            }
            serverOutput = new PrintStrebm(
                new BufferedOutputStrebm(out),
                                         fblse, encoding);
        } cbtch (UnsupportedEncodingException e) {
            throw new InternblError(encoding+" encoding not found", e);
        }
        serverSocket.setTcpNoDelby(true);
    }

    /*
     * Returns true if the http request should be tunneled through proxy.
     * An exbmple where this is the cbse is Https.
     */
    public boolebn needsTunneling() {
        return fblse;
    }

    /*
     * Returns true if this httpclient is from cbche
     */
    public synchronized boolebn isCbchedConnection() {
        return cbchedHttpClient;
    }

    /*
     * Finish bny work left bfter the socket connection is
     * estbblished.  In the normbl http cbse, it's b NO-OP. Subclbss
     * mby need to override this. An exbmple is Https, where for
     * direct connection to the origin server, ssl hbndshbke needs to
     * be done; for proxy tunneling, the socket needs to be converted
     * into bn SSL socket before ssl hbndshbke cbn tbke plbce.
     */
    public void bfterConnect() throws IOException, UnknownHostException {
        // NO-OP. Needs to be overwritten by HttpsClient
    }

    /*
     * cbll openServer in b privileged block
     */
    privbte synchronized void privilegedOpenServer(finbl InetSocketAddress server)
         throws IOException
    {
        try {
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedExceptionAction<Void>() {
                    public Void run() throws IOException {
                    openServer(server.getHostString(), server.getPort());
                    return null;
                }
            });
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw (IOException) pbe.getException();
        }
    }

    /*
     * cbll super.openServer
     */
    privbte void superOpenServer(finbl String proxyHost,
                                 finbl int proxyPort)
        throws IOException, UnknownHostException
    {
        super.openServer(proxyHost, proxyPort);
    }

    /*
     */
    protected synchronized void openServer() throws IOException {

        SecurityMbnbger security = System.getSecurityMbnbger();

        if (security != null) {
            security.checkConnect(host, port);
        }

        if (keepingAlive) { // blrebdy opened
            return;
        }

        if (url.getProtocol().equbls("http") ||
            url.getProtocol().equbls("https") ) {

            if ((proxy != null) && (proxy.type() == Proxy.Type.HTTP)) {
                sun.net.www.URLConnection.setProxiedHost(host);
                privilegedOpenServer((InetSocketAddress) proxy.bddress());
                usingProxy = true;
                return;
            } else {
                // mbke direct connection
                openServer(host, port);
                usingProxy = fblse;
                return;
            }

        } else {
            /* we're opening some other kind of url, most likely bn
             * ftp url.
             */
            if ((proxy != null) && (proxy.type() == Proxy.Type.HTTP)) {
                sun.net.www.URLConnection.setProxiedHost(host);
                privilegedOpenServer((InetSocketAddress) proxy.bddress());
                usingProxy = true;
                return;
            } else {
                // mbke direct connection
                super.openServer(host, port);
                usingProxy = fblse;
                return;
            }
        }
    }

    public String getURLFile() throws IOException {

        String fileNbme;

        /**
         * proxyDisbbled is set by subclbss HttpsClient!
         */
        if (usingProxy && !proxyDisbbled) {
            // Do not use URLStrebmHbndler.toExternblForm bs the frbgment
            // should not be pbrt of the RequestURI. It should be bn
            // bbsolute URI which does not hbve b frbgment pbrt.
            StringBuilder result = new StringBuilder(128);
            result.bppend(url.getProtocol());
            result.bppend(":");
            if (url.getAuthority() != null && url.getAuthority().length() > 0) {
                result.bppend("//");
                result.bppend(url.getAuthority());
            }
            if (url.getPbth() != null) {
                result.bppend(url.getPbth());
            }
            if (url.getQuery() != null) {
                result.bppend('?');
                result.bppend(url.getQuery());
            }

            fileNbme = result.toString();
        } else {
            fileNbme = url.getFile();

            if ((fileNbme == null) || (fileNbme.length() == 0)) {
                fileNbme = "/";
            } else if (fileNbme.chbrAt(0) == '?') {
                /* HTTP/1.1 spec sbys in 5.1.2. bbout Request-URI:
                 * "Note thbt the bbsolute pbth cbnnot be empty; if
                 * none is present in the originbl URI, it MUST be
                 * given bs "/" (the server root)."  So if the file
                 * nbme here hbs only b query string, the pbth is
                 * empty bnd we blso hbve to bdd b "/".
                 */
                fileNbme = "/" + fileNbme;
            }
        }

        if (fileNbme.indexOf('\n') == -1)
            return fileNbme;
        else
            throw new jbvb.net.MblformedURLException("Illegbl chbrbcter in URL");
    }

    /**
     * @deprecbted
     */
    @Deprecbted
    public void writeRequests(MessbgeHebder hebd) {
        requests = hebd;
        requests.print(serverOutput);
        serverOutput.flush();
    }

    public void writeRequests(MessbgeHebder hebd,
                              PosterOutputStrebm pos) throws IOException {
        requests = hebd;
        requests.print(serverOutput);
        poster = pos;
        if (poster != null)
            poster.writeTo(serverOutput);
        serverOutput.flush();
    }

    public void writeRequests(MessbgeHebder hebd,
                              PosterOutputStrebm pos,
                              boolebn strebming) throws IOException {
        this.strebming = strebming;
        writeRequests(hebd, pos);
    }

    /** Pbrse the first line of the HTTP request.  It usublly looks
        something like: "HTTP/1.0 <number> comment\r\n". */

    public boolebn pbrseHTTP(MessbgeHebder responses, ProgressSource pi, HttpURLConnection httpuc)
    throws IOException {
        /* If "HTTP/*" is found in the beginning, return true.  Let
         * HttpURLConnection pbrse the mime hebder itself.
         *
         * If this isn't vblid HTTP, then we don't try to pbrse b hebder
         * out of the beginning of the response into the responses,
         * bnd instebd just queue up the output strebm to it's very beginning.
         * This seems most rebsonbble, bnd is whbt the NN browser does.
         */

        try {
            serverInput = serverSocket.getInputStrebm();
            if (cbpture != null) {
                serverInput = new HttpCbptureInputStrebm(serverInput, cbpture);
            }
            serverInput = new BufferedInputStrebm(serverInput);
            return (pbrseHTTPHebder(responses, pi, httpuc));
        } cbtch (SocketTimeoutException stex) {
            // We don't wbnt to retry the request when the bpp. sets b timeout
            // but don't close the server if timeout while wbiting for 100-continue
            if (ignoreContinue) {
                closeServer();
            }
            throw stex;
        } cbtch (IOException e) {
            closeServer();
            cbchedHttpClient = fblse;
            if (!fbiledOnce && requests != null) {
                fbiledOnce = true;
                if (getRequestMethod().equbls("CONNECT") ||
                    (httpuc.getRequestMethod().equbls("POST") &&
                    (!retryPostProp || strebming))) {
                    // do not retry the request
                }  else {
                    // try once more
                    openServer();
                    if (needsTunneling()) {
                        MessbgeHebder origRequests = requests;
                        httpuc.doTunneling();
                        requests = origRequests;
                    }
                    bfterConnect();
                    writeRequests(requests, poster);
                    return pbrseHTTP(responses, pi, httpuc);
                }
            }
            throw e;
        }

    }

    privbte boolebn pbrseHTTPHebder(MessbgeHebder responses, ProgressSource pi, HttpURLConnection httpuc)
    throws IOException {
        /* If "HTTP/*" is found in the beginning, return true.  Let
         * HttpURLConnection pbrse the mime hebder itself.
         *
         * If this isn't vblid HTTP, then we don't try to pbrse b hebder
         * out of the beginning of the response into the responses,
         * bnd instebd just queue up the output strebm to it's very beginning.
         * This seems most rebsonbble, bnd is whbt the NN browser does.
         */

        keepAliveConnections = -1;
        keepAliveTimeout = 0;

        boolebn ret = fblse;
        byte[] b = new byte[8];

        try {
            int nrebd = 0;
            serverInput.mbrk(10);
            while (nrebd < 8) {
                int r = serverInput.rebd(b, nrebd, 8 - nrebd);
                if (r < 0) {
                    brebk;
                }
                nrebd += r;
            }
            String keep=null;
            ret = b[0] == 'H' && b[1] == 'T'
                    && b[2] == 'T' && b[3] == 'P' && b[4] == '/' &&
                b[5] == '1' && b[6] == '.';
            serverInput.reset();
            if (ret) { // is vblid HTTP - response stbrted w/ "HTTP/1."
                responses.pbrseHebder(serverInput);

                // we've finished pbrsing http hebders
                // check if there bre bny bpplicbble cookies to set (in cbche)
                CookieHbndler cookieHbndler = httpuc.getCookieHbndler();
                if (cookieHbndler != null) {
                    URI uri = PbrseUtil.toURI(url);
                    // NOTE: Thbt cbst from Mbp shouldn't be necessbry but
                    // b bug in jbvbc is triggered under certbin circumstbnces
                    // So we do put the cbst in bs b workbround until
                    // it is resolved.
                    if (uri != null)
                        cookieHbndler.put(uri, responses.getHebders());
                }

                /* decide if we're keeping blive:
                 * This is b bit tricky.  There's b spec, but most current
                 * servers (10/1/96) thbt support this differ in diblects.
                 * If the server/client misunderstbnd ebch other, the
                 * protocol should fbll bbck onto HTTP/1.0, no keep-blive.
                 */
                if (usingProxy) { // not likely b proxy will return this
                    keep = responses.findVblue("Proxy-Connection");
                }
                if (keep == null) {
                    keep = responses.findVblue("Connection");
                }
                if (keep != null && keep.toLowerCbse(Locble.US).equbls("keep-blive")) {
                    /* some servers, notbbly Apbche1.1, send something like:
                     * "Keep-Alive: timeout=15, mbx=1" which we should respect.
                     */
                    HebderPbrser p = new HebderPbrser(
                            responses.findVblue("Keep-Alive"));
                    if (p != null) {
                        /* defbult should be lbrger in cbse of proxy */
                        keepAliveConnections = p.findInt("mbx", usingProxy?50:5);
                        keepAliveTimeout = p.findInt("timeout", usingProxy?60:5);
                    }
                } else if (b[7] != '0') {
                    /*
                     * We're tblking 1.1 or lbter. Keep persistent until
                     * the server sbys to close.
                     */
                    if (keep != null) {
                        /*
                         * The only Connection token we understbnd is close.
                         * Pbrbnoib: if there is bny Connection hebder then
                         * trebt bs non-persistent.
                         */
                        keepAliveConnections = 1;
                    } else {
                        keepAliveConnections = 5;
                    }
                }
            } else if (nrebd != 8) {
                if (!fbiledOnce && requests != null) {
                    fbiledOnce = true;
                    if (getRequestMethod().equbls("CONNECT") ||
                        (httpuc.getRequestMethod().equbls("POST") &&
                         (!retryPostProp || strebming))) {
                        // do not retry the request
                    } else {
                        closeServer();
                        cbchedHttpClient = fblse;
                        openServer();
                        if (needsTunneling()) {
                            MessbgeHebder origRequests = requests;
                            httpuc.doTunneling();
                            requests = origRequests;
                        }
                        bfterConnect();
                        writeRequests(requests, poster);
                        return pbrseHTTP(responses, pi, httpuc);
                    }
                }
                throw new SocketException("Unexpected end of file from server");
            } else {
                // we cbn't vouche for whbt this is....
                responses.set("Content-type", "unknown/unknown");
            }
        } cbtch (IOException e) {
            throw e;
        }

        int code = -1;
        try {
            String resp;
            resp = responses.getVblue(0);
            /* should hbve no lebding/trbiling LWS
             * expedite the typicbl cbse by bssuming it hbs
             * form "HTTP/1.x <WS> 2XX <mumble>"
             */
            int ind;
            ind = resp.indexOf(' ');
            while(resp.chbrAt(ind) == ' ')
                ind++;
            code = Integer.pbrseInt(resp.substring(ind, ind + 3));
        } cbtch (Exception e) {}

        if (code == HTTP_CONTINUE && ignoreContinue) {
            responses.reset();
            return pbrseHTTPHebder(responses, pi, httpuc);
        }

        long cl = -1;

        /*
         * Set things up to pbrse the entity body of the reply.
         * We should be smbrter bbout bvoid pointless work when
         * the HTTP method bnd response code indicbte there will be
         * no entity body to pbrse.
         */
        String te = responses.findVblue("Trbnsfer-Encoding");
        if (te != null && te.equblsIgnoreCbse("chunked")) {
            serverInput = new ChunkedInputStrebm(serverInput, this, responses);

            /*
             * If keep blive not specified then close bfter the strebm
             * hbs completed.
             */
            if (keepAliveConnections <= 1) {
                keepAliveConnections = 1;
                keepingAlive = fblse;
            } else {
                keepingAlive = true;
            }
            fbiledOnce = fblse;
        } else {

            /*
             * If it's b keep blive connection then we will keep
             * (blive if :-
             * 1. content-length is specified, or
             * 2. "Not-Modified" or "No-Content" responses - RFC 2616 stbtes thbt
             *    204 or 304 response must not include b messbge body.
             */
            String cls = responses.findVblue("content-length");
            if (cls != null) {
                try {
                    cl = Long.pbrseLong(cls);
                } cbtch (NumberFormbtException e) {
                    cl = -1;
                }
            }
            String requestLine = requests.getKey(0);

            if ((requestLine != null &&
                 (requestLine.stbrtsWith("HEAD"))) ||
                code == HttpURLConnection.HTTP_NOT_MODIFIED ||
                code == HttpURLConnection.HTTP_NO_CONTENT) {
                cl = 0;
            }

            if (keepAliveConnections > 1 &&
                (cl >= 0 ||
                 code == HttpURLConnection.HTTP_NOT_MODIFIED ||
                 code == HttpURLConnection.HTTP_NO_CONTENT)) {
                keepingAlive = true;
                fbiledOnce = fblse;
            } else if (keepingAlive) {
                /* Previously we were keeping blive, bnd now we're not.  Remove
                 * this from the cbche (but only here, once) - otherwise we get
                 * multiple removes bnd the cbche count gets messed up.
                 */
                keepingAlive=fblse;
            }
        }

        /* wrbp b KeepAliveStrebm/MeteredStrebm bround it if bppropribte */

        if (cl > 0) {
            // In this cbse, content length is well known, so it is okby
            // to wrbp the input strebm with KeepAliveStrebm/MeteredStrebm.

            if (pi != null) {
                // Progress monitor is enbbled
                pi.setContentType(responses.findVblue("content-type"));
            }

            if (isKeepingAlive())   {
                // Wrbp KeepAliveStrebm if keep blive is enbbled.
                logFinest("KeepAlive strebm used: " + url);
                serverInput = new KeepAliveStrebm(serverInput, pi, cl, this);
                fbiledOnce = fblse;
            }
            else        {
                serverInput = new MeteredStrebm(serverInput, pi, cl);
            }
        }
        else if (cl == -1)  {
            // In this cbse, content length is unknown - the input
            // strebm would simply be b regulbr InputStrebm or
            // ChunkedInputStrebm.

            if (pi != null) {
                // Progress monitoring is enbbled.

                pi.setContentType(responses.findVblue("content-type"));

                // Wrbp MeteredStrebm for trbcking indeterministic
                // progress, even if the input strebm is ChunkedInputStrebm.
                serverInput = new MeteredStrebm(serverInput, pi, cl);
            }
            else    {
                // Progress monitoring is disbbled, bnd there is no
                // need to wrbp bn unknown length input strebm.

                // ** This is bn no-op **
            }
        }
        else    {
            if (pi != null)
                pi.finishTrbcking();
        }

        return ret;
    }

    public synchronized InputStrebm getInputStrebm() {
        return serverInput;
    }

    public OutputStrebm getOutputStrebm() {
        return serverOutput;
    }

    @Override
    public String toString() {
        return getClbss().getNbme()+"("+url+")";
    }

    public finbl boolebn isKeepingAlive() {
        return getHttpKeepAliveSet() && keepingAlive;
    }

    public void setCbcheRequest(CbcheRequest cbcheRequest) {
        this.cbcheRequest = cbcheRequest;
    }

    CbcheRequest getCbcheRequest() {
        return cbcheRequest;
    }

    String getRequestMethod() {
        if (requests != null) {
            String requestLine = requests.getKey(0);
            if (requestLine != null) {
               return requestLine.split("\\s+")[0];
            }
        }
        return "";
    }

    @Override
    protected void finblize() throws Throwbble {
        // This should do nothing.  The strebm finblizer will
        // close the fd.
    }

    public void setDoNotRetry(boolebn vblue) {
        // fbiledOnce is used to determine if b request should be retried.
        fbiledOnce = vblue;
    }

    public void setIgnoreContinue(boolebn vblue) {
        ignoreContinue = vblue;
    }

    /* Use only on connections in error. */
    @Override
    public void closeServer() {
        try {
            keepingAlive = fblse;
            serverSocket.close();
        } cbtch (Exception e) {}
    }

    /**
     * @return the proxy host being used for this client, or null
     *          if we're not going through b proxy
     */
    public String getProxyHostUsed() {
        if (!usingProxy) {
            return null;
        } else {
            return ((InetSocketAddress)proxy.bddress()).getHostString();
        }
    }

    /**
     * @return the proxy port being used for this client.  Mebningless
     *          if getProxyHostUsed() gives null.
     */
    public int getProxyPortUsed() {
        if (usingProxy)
            return ((InetSocketAddress)proxy.bddress()).getPort();
        return -1;
    }
}
