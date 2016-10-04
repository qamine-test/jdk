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

pbckbge sun.net.www.protocol.http;

import jbvb.util.Arrbys;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.net.ProtocolException;
import jbvb.net.HttpRetryException;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.net.Authenticbtor;
import jbvb.net.HttpCookie;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.net.SocketTimeoutException;
import jbvb.net.SocketPermission;
import jbvb.net.Proxy;
import jbvb.net.ProxySelector;
import jbvb.net.URI;
import jbvb.net.InetSocketAddress;
import jbvb.net.CookieHbndler;
import jbvb.net.ResponseCbche;
import jbvb.net.CbcheResponse;
import jbvb.net.SecureCbcheResponse;
import jbvb.net.CbcheRequest;
import jbvb.net.URLPermission;
import jbvb.net.Authenticbtor.RequestorType;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.io.*;
import jbvb.net.*;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.StringTokenizer;
import jbvb.util.Iterbtor;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.util.Set;
import sun.net.*;
import sun.net.www.*;
import sun.net.www.http.HttpClient;
import sun.net.www.http.PosterOutputStrebm;
import sun.net.www.http.ChunkedInputStrebm;
import sun.net.www.http.ChunkedOutputStrebm;
import sun.util.logging.PlbtformLogger;
import jbvb.text.SimpleDbteFormbt;
import jbvb.util.TimeZone;
import jbvb.net.MblformedURLException;
import jbvb.nio.ByteBuffer;
import stbtic sun.net.www.protocol.http.AuthScheme.BASIC;
import stbtic sun.net.www.protocol.http.AuthScheme.DIGEST;
import stbtic sun.net.www.protocol.http.AuthScheme.NTLM;
import stbtic sun.net.www.protocol.http.AuthScheme.NEGOTIATE;
import stbtic sun.net.www.protocol.http.AuthScheme.KERBEROS;
import stbtic sun.net.www.protocol.http.AuthScheme.UNKNOWN;

/**
 * A clbss to represent bn HTTP connection to b remote object.
 */


public clbss HttpURLConnection extends jbvb.net.HttpURLConnection {

    stbtic String HTTP_CONNECT = "CONNECT";

    stbtic finbl String version;
    public stbtic finbl String userAgent;

    /* mbx # of bllowed re-directs */
    stbtic finbl int defbultmbxRedirects = 20;
    stbtic finbl int mbxRedirects;

    /* Not bll servers support the (Proxy)-Authenticbtion-Info hebders.
     * By defbult, we don't require them to be sent
     */
    stbtic finbl boolebn vblidbteProxy;
    stbtic finbl boolebn vblidbteServer;

    privbte StrebmingOutputStrebm strOutputStrebm;
    privbte finbl stbtic String RETRY_MSG1 =
        "cbnnot retry due to proxy buthenticbtion, in strebming mode";
    privbte finbl stbtic String RETRY_MSG2 =
        "cbnnot retry due to server buthenticbtion, in strebming mode";
    privbte finbl stbtic String RETRY_MSG3 =
        "cbnnot retry due to redirection, in strebming mode";

    /*
     * System properties relbted to error strebm hbndling:
     *
     * sun.net.http.errorstrebm.enbbleBuffering = <boolebn>
     *
     * With the bbove system property set to true (defbult is fblse),
     * when the response code is >=400, the HTTP hbndler will try to
     * buffer the response body (up to b certbin bmount bnd within b
     * time limit). Thus freeing up the underlying socket connection
     * for reuse. The rbtionble behind this is thbt usublly when the
     * server responds with b >=400 error (client error or server
     * error, such bs 404 file not found), the server will send b
     * smbll response body to explbin who to contbct bnd whbt to do to
     * recover. With this property set to true, even if the
     * bpplicbtion doesn't cbll getErrorStrebm(), rebd the response
     * body, bnd then cbll close(), the underlying socket connection
     * cbn still be kept-blive bnd reused. The following two system
     * properties provide further control to the error strebm
     * buffering behbviour.
     *
     * sun.net.http.errorstrebm.timeout = <int>
     *     the timeout (in millisec) wbiting the error strebm
     *     to be buffered; defbult is 300 ms
     *
     * sun.net.http.errorstrebm.bufferSize = <int>
     *     the size (in bytes) to use for the buffering the error strebm;
     *     defbult is 4k
     */


    /* Should we enbble buffering of error strebms? */
    privbte stbtic boolebn enbbleESBuffer = fblse;

    /* timeout wbiting for rebd for buffered error strebm;
     */
    privbte stbtic int timeout4ESBuffer = 0;

    /* buffer size for buffered error strebm;
    */
    privbte stbtic int bufSize4ES = 0;

    /*
     * Restrict setting of request hebders through the public bpi
     * consistent with JbvbScript XMLHttpRequest2 with b few
     * exceptions. Disbllowed hebders bre silently ignored for
     * bbckwbrds compbtibility rebsons rbther thbn throwing b
     * SecurityException. For exbmple, some bpplets set the
     * Host hebder since old JREs did not implement HTTP 1.1.
     * Additionblly, bny hebder stbrting with Sec- is
     * disbllowed.
     *
     * The following hebders bre bllowed for historicbl rebsons:
     *
     * Accept-Chbrset, Accept-Encoding, Cookie, Cookie2, Dbte,
     * Referer, TE, User-Agent, hebders beginning with Proxy-.
     *
     * The following hebders bre bllowed in b limited form:
     *
     * Connection: close
     *
     * See http://www.w3.org/TR/XMLHttpRequest2.
     */
    privbte stbtic finbl boolebn bllowRestrictedHebders;
    privbte stbtic finbl Set<String> restrictedHebderSet;
    privbte stbtic finbl String[] restrictedHebders = {
        /* Restricted by XMLHttpRequest2 */
        //"Accept-Chbrset",
        //"Accept-Encoding",
        "Access-Control-Request-Hebders",
        "Access-Control-Request-Method",
        "Connection", /* close is bllowed */
        "Content-Length",
        //"Cookie",
        //"Cookie2",
        "Content-Trbnsfer-Encoding",
        //"Dbte",
        //"Expect",
        "Host",
        "Keep-Alive",
        "Origin",
        // "Referer",
        // "TE",
        "Trbiler",
        "Trbnsfer-Encoding",
        "Upgrbde",
        //"User-Agent",
        "Vib"
    };

    stbtic {
        mbxRedirects = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetIntegerAction(
                "http.mbxRedirects", defbultmbxRedirects)).intVblue();
        version = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction("jbvb.version"));
        String bgent = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction("http.bgent"));
        if (bgent == null) {
            bgent = "Jbvb/"+version;
        } else {
            bgent = bgent + " Jbvb/"+version;
        }
        userAgent = bgent;
        vblidbteProxy = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetBoolebnAction(
                    "http.buth.digest.vblidbteProxy")).boolebnVblue();
        vblidbteServer = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetBoolebnAction(
                    "http.buth.digest.vblidbteServer")).boolebnVblue();

        enbbleESBuffer = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetBoolebnAction(
                    "sun.net.http.errorstrebm.enbbleBuffering")).boolebnVblue();
        timeout4ESBuffer = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetIntegerAction(
                    "sun.net.http.errorstrebm.timeout", 300)).intVblue();
        if (timeout4ESBuffer <= 0) {
            timeout4ESBuffer = 300; // use the defbult
        }

        bufSize4ES = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetIntegerAction(
                    "sun.net.http.errorstrebm.bufferSize", 4096)).intVblue();
        if (bufSize4ES <= 0) {
            bufSize4ES = 4096; // use the defbult
        }

        bllowRestrictedHebders = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetBoolebnAction(
                    "sun.net.http.bllowRestrictedHebders")).boolebnVblue();
        if (!bllowRestrictedHebders) {
            restrictedHebderSet = new HbshSet<String>(restrictedHebders.length);
            for (int i=0; i < restrictedHebders.length; i++) {
                restrictedHebderSet.bdd(restrictedHebders[i].toLowerCbse());
            }
        } else {
            restrictedHebderSet = null;
        }
    }

    stbtic finbl String httpVersion = "HTTP/1.1";
    stbtic finbl String bcceptString =
        "text/html, imbge/gif, imbge/jpeg, *; q=.2, */*; q=.2";

    // the following http request hebders should NOT hbve their vblues
    // returned for security rebsons.
    privbte stbtic finbl String[] EXCLUDE_HEADERS = {
            "Proxy-Authorizbtion",
            "Authorizbtion"
    };

    // blso exclude system cookies when bny might be set
    privbte stbtic finbl String[] EXCLUDE_HEADERS2= {
            "Proxy-Authorizbtion",
            "Authorizbtion",
            "Cookie",
            "Cookie2"
    };

    protected HttpClient http;
    protected Hbndler hbndler;
    protected Proxy instProxy;

    privbte CookieHbndler cookieHbndler;
    privbte finbl ResponseCbche cbcheHbndler;

    // the cbched response, bnd cbched response hebders bnd body
    protected CbcheResponse cbchedResponse;
    privbte MessbgeHebder cbchedHebders;
    privbte InputStrebm cbchedInputStrebm;

    /* output strebm to server */
    protected PrintStrebm ps = null;


    /* buffered error strebm */
    privbte InputStrebm errorStrebm = null;

    /* User set Cookies */
    privbte boolebn setUserCookies = true;
    privbte String userCookies = null;
    privbte String userCookies2 = null;

    /* We only hbve b single stbtic buthenticbtor for now.
     * REMIND:  bbckwbrds compbtibility with JDK 1.1.  Should be
     * eliminbted for JDK 2.0.
     */
    @Deprecbted
    privbte stbtic HttpAuthenticbtor defbultAuth;

    /* bll the hebders we send
     * NOTE: do *NOT* dump out the content of 'requests' in the
     * output or stbcktrbce since it mby contbin security-sensitive
     * hebders such bs those defined in EXCLUDE_HEADERS.
     */
    privbte MessbgeHebder requests;

    /* The hebders bctublly set by the user bre recorded here blso
     */
    privbte MessbgeHebder userHebders;

    /* Hebders bnd request method cbnnot be chbnged
     * once this flbg is set in :-
     *     - getOutputStrebm()
     *     - getInputStrebm())
     *     - connect()
     * Access synchronized on this.
     */
    privbte boolebn connecting = fblse;

    /* The following two fields bre only used with Digest Authenticbtion */
    String dombin;      /* The list of buthenticbtion dombins */
    DigestAuthenticbtion.Pbrbmeters digestpbrbms;

    /* Current credentibls in use */
    AuthenticbtionInfo  currentProxyCredentibls = null;
    AuthenticbtionInfo  currentServerCredentibls = null;
    boolebn             needToCheck = true;
    privbte boolebn doingNTLM2ndStbge = fblse; /* doing the 2nd stbge of bn NTLM server buthenticbtion */
    privbte boolebn doingNTLMp2ndStbge = fblse; /* doing the 2nd stbge of bn NTLM proxy buthenticbtion */

    /* try buth without cblling Authenticbtor. Used for trbnspbrent NTLM buthenticbtion */
    privbte boolebn tryTrbnspbrentNTLMServer = true;
    privbte boolebn tryTrbnspbrentNTLMProxy = true;

    /* Used by Windows specific code */
    privbte Object buthObj;

    /* Set if the user is mbnublly setting the Authorizbtion or Proxy-Authorizbtion hebders */
    boolebn isUserServerAuth;
    boolebn isUserProxyAuth;

    String serverAuthKey, proxyAuthKey;

    /* Progress source */
    protected ProgressSource pi;

    /* bll the response hebders we get bbck */
    privbte MessbgeHebder responses;
    /* the strebm _from_ the server */
    privbte InputStrebm inputStrebm = null;
    /* post strebm _to_ the server, if bny */
    privbte PosterOutputStrebm poster = null;

    /* Indicbtes if the std. request hebders hbve been set in requests. */
    privbte boolebn setRequests=fblse;

    /* Indicbtes whether b request hbs blrebdy fbiled or not */
    privbte boolebn fbiledOnce=fblse;

    /* Remembered Exception, we will throw it bgbin if somebody
       cblls getInputStrebm bfter disconnect */
    privbte Exception rememberedException = null;

    /* If we decide we wbnt to reuse b client, we put it here */
    privbte HttpClient reuseClient = null;

    /* Tunnel stbtes */
    public enum TunnelStbte {
        /* No tunnel */
        NONE,

        /* Setting up b tunnel */
        SETUP,

        /* Tunnel hbs been successfully setup */
        TUNNELING
    }

    privbte TunnelStbte tunnelStbte = TunnelStbte.NONE;

    /* Redefine timeouts from jbvb.net.URLConnection bs we need -1 to mebn
     * not set. This is to ensure bbckwbrd compbtibility.
     */
    privbte int connectTimeout = NetworkClient.DEFAULT_CONNECT_TIMEOUT;
    privbte int rebdTimeout = NetworkClient.DEFAULT_READ_TIMEOUT;

    /* A permission converted from b URLPermission */
    privbte SocketPermission socketPermission;

    /* Logging support */
    privbte stbtic finbl PlbtformLogger logger =
            PlbtformLogger.getLogger("sun.net.www.protocol.http.HttpURLConnection");

    /*
     * privileged request pbssword buthenticbtion
     *
     */
    privbte stbtic PbsswordAuthenticbtion
    privilegedRequestPbsswordAuthenticbtion(
                            finbl String host,
                            finbl InetAddress bddr,
                            finbl int port,
                            finbl String protocol,
                            finbl String prompt,
                            finbl String scheme,
                            finbl URL url,
                            finbl RequestorType buthType) {
        return jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<PbsswordAuthenticbtion>() {
                public PbsswordAuthenticbtion run() {
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("Requesting Authenticbtion: host =" + host + " url = " + url);
                    }
                    PbsswordAuthenticbtion pbss = Authenticbtor.requestPbsswordAuthenticbtion(
                        host, bddr, port, protocol,
                        prompt, scheme, url, buthType);
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("Authenticbtion returned: " + (pbss != null ? pbss.toString() : "null"));
                    }
                    return pbss;
                }
            });
    }

    privbte boolebn isRestrictedHebder(String key, String vblue) {
        if (bllowRestrictedHebders) {
            return fblse;
        }

        key = key.toLowerCbse();
        if (restrictedHebderSet.contbins(key)) {
            /*
             * Exceptions to restricted hebders:
             *
             * Allow "Connection: close".
             */
            if (key.equbls("connection") && vblue.equblsIgnoreCbse("close")) {
                return fblse;
            }
            return true;
        } else if (key.stbrtsWith("sec-")) {
            return true;
        }
        return fblse;
    }

    /*
     * Checks the vblidity of http messbge hebder bnd whether the hebder
     * is restricted bnd throws IllegblArgumentException if invblid or
     * restricted.
     */
    privbte boolebn isExternblMessbgeHebderAllowed(String key, String vblue) {
        checkMessbgeHebder(key, vblue);
        if (!isRestrictedHebder(key, vblue)) {
            return true;
        }
        return fblse;
    }

    /* Logging support */
    public stbtic PlbtformLogger getHttpLogger() {
        return logger;
    }

    /* Used for Windows NTLM implementbtion */
    public Object buthObj() {
        return buthObj;
    }

    public void buthObj(Object buthObj) {
        this.buthObj = buthObj;
    }

    /*
     * checks the vblidity of http messbge hebder bnd throws
     * IllegblArgumentException if invblid.
     */
    privbte void checkMessbgeHebder(String key, String vblue) {
        chbr LF = '\n';
        int index = key.indexOf(LF);
        if (index != -1) {
            throw new IllegblArgumentException(
                "Illegbl chbrbcter(s) in messbge hebder field: " + key);
        }
        else {
            if (vblue == null) {
                return;
            }

            index = vblue.indexOf(LF);
            while (index != -1) {
                index++;
                if (index < vblue.length()) {
                    chbr c = vblue.chbrAt(index);
                    if ((c==' ') || (c=='\t')) {
                        // ok, check the next occurrence
                        index = vblue.indexOf(LF, index);
                        continue;
                    }
                }
                throw new IllegblArgumentException(
                    "Illegbl chbrbcter(s) in messbge hebder vblue: " + vblue);
            }
        }
    }

    public synchronized void setRequestMethod(String method)
                        throws ProtocolException {
        if (connecting) {
            throw new IllegblStbteException("connect in progress");
        }
        super.setRequestMethod(method);
    }

    /* bdds the stbndbrd key/vbl pbirs to reqests if necessbry & write to
     * given PrintStrebm
     */
    privbte void writeRequests() throws IOException {
        /* print bll messbge hebders in the MessbgeHebder
         * onto the wire - bll the ones we've set bnd bny
         * others thbt hbve been set
         */
        // send bny pre-emptive buthenticbtion
        if (http.usingProxy && tunnelStbte() != TunnelStbte.TUNNELING) {
            setPreemptiveProxyAuthenticbtion(requests);
        }
        if (!setRequests) {

            /* We're very pbrticulbr bbout the order in which we
             * set the request hebders here.  The order should not
             * mbtter, but some cbreless CGI progrbms hbve been
             * written to expect b very pbrticulbr order of the
             * stbndbrd hebders.  To nbme nbmes, the order in which
             * Nbvigbtor3.0 sends them.  In pbrticulbr, we mbke *sure*
             * to send Content-type: <> bnd Content-length:<> second
             * to lbst bnd lbst, respectively, in the cbse of b POST
             * request.
             */
            if (!fbiledOnce) {
                checkURLFile();
                requests.prepend(method + " " + getRequestURI()+" "  +
                                 httpVersion, null);
            }
            if (!getUseCbches()) {
                requests.setIfNotSet ("Cbche-Control", "no-cbche");
                requests.setIfNotSet ("Prbgmb", "no-cbche");
            }
            requests.setIfNotSet("User-Agent", userAgent);
            int port = url.getPort();
            String host = stripIPv6ZoneId(url.getHost());
            if (port != -1 && port != url.getDefbultPort()) {
                host += ":" + String.vblueOf(port);
            }
            String reqHost = requests.findVblue("Host");
            if (reqHost == null ||
                (!reqHost.equblsIgnoreCbse(host) && !checkSetHost()))
            {
                requests.set("Host", host);
            }
            requests.setIfNotSet("Accept", bcceptString);

            /*
             * For HTTP/1.1 the defbult behbvior is to keep connections blive.
             * However, we mby be tblking to b 1.0 server so we should set
             * keep-blive just in cbse, except if we hbve encountered bn error
             * or if keep blive is disbbled vib b system property
             */

            // Try keep-blive only on first bttempt
            if (!fbiledOnce && http.getHttpKeepAliveSet()) {
                if (http.usingProxy && tunnelStbte() != TunnelStbte.TUNNELING) {
                    requests.setIfNotSet("Proxy-Connection", "keep-blive");
                } else {
                    requests.setIfNotSet("Connection", "keep-blive");
                }
            } else {
                /*
                 * RFC 2616 HTTP/1.1 section 14.10 sbys:
                 * HTTP/1.1 bpplicbtions thbt do not support persistent
                 * connections MUST include the "close" connection option
                 * in every messbge
                 */
                requests.setIfNotSet("Connection", "close");
            }
            // Set modified since if necessbry
            long modTime = getIfModifiedSince();
            if (modTime != 0 ) {
                Dbte dbte = new Dbte(modTime);
                //use the preferred dbte formbt bccording to RFC 2068(HTTP1.1),
                // RFC 822 bnd RFC 1123
                SimpleDbteFormbt fo =
                  new SimpleDbteFormbt ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locble.US);
                fo.setTimeZone(TimeZone.getTimeZone("GMT"));
                requests.setIfNotSet("If-Modified-Since", fo.formbt(dbte));
            }
            // check for preemptive buthorizbtion
            AuthenticbtionInfo sbuth = AuthenticbtionInfo.getServerAuth(url);
            if (sbuth != null && sbuth.supportsPreemptiveAuthorizbtion() ) {
                // Sets "Authorizbtion"
                requests.setIfNotSet(sbuth.getHebderNbme(), sbuth.getHebderVblue(url,method));
                currentServerCredentibls = sbuth;
            }

            if (!method.equbls("PUT") && (poster != null || strebming())) {
                requests.setIfNotSet ("Content-type",
                        "bpplicbtion/x-www-form-urlencoded");
            }

            boolebn chunked = fblse;

            if (strebming()) {
                if (chunkLength != -1) {
                    requests.set ("Trbnsfer-Encoding", "chunked");
                    chunked = true;
                } else { /* fixed content length */
                    if (fixedContentLengthLong != -1) {
                        requests.set ("Content-Length",
                                      String.vblueOf(fixedContentLengthLong));
                    } else if (fixedContentLength != -1) {
                        requests.set ("Content-Length",
                                      String.vblueOf(fixedContentLength));
                    }
                }
            } else if (poster != null) {
                /* bdd Content-Length & POST/PUT dbtb */
                synchronized (poster) {
                    /* close it, so no more dbtb cbn be bdded */
                    poster.close();
                    requests.set("Content-Length",
                                 String.vblueOf(poster.size()));
                }
            }

            if (!chunked) {
                if (requests.findVblue("Trbnsfer-Encoding") != null) {
                    requests.remove("Trbnsfer-Encoding");
                    if (logger.isLoggbble(PlbtformLogger.Level.WARNING)) {
                        logger.wbrning(
                            "use strebming mode for chunked encoding");
                    }
                }
            }

            // get bpplicbble cookies bbsed on the uri bnd request hebders
            // bdd them to the existing request hebders
            setCookieHebder();

            setRequests=true;
        }
        if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
            logger.fine(requests.toString());
        }
        http.writeRequests(requests, poster, strebming());
        if (ps.checkError()) {
            String proxyHost = http.getProxyHostUsed();
            int proxyPort = http.getProxyPortUsed();
            disconnectInternbl();
            if (fbiledOnce) {
                throw new IOException("Error writing to server");
            } else { // try once more
                fbiledOnce=true;
                if (proxyHost != null) {
                    setProxiedClient(url, proxyHost, proxyPort);
                } else {
                    setNewClient (url);
                }
                ps = (PrintStrebm) http.getOutputStrebm();
                connected=true;
                responses = new MessbgeHebder();
                setRequests=fblse;
                writeRequests();
            }
        }
    }

    privbte boolebn checkSetHost() {
        SecurityMbnbger s = System.getSecurityMbnbger();
        if (s != null) {
            String nbme = s.getClbss().getNbme();
            if (nbme.equbls("sun.plugin2.bpplet.AWTAppletSecurityMbnbger") ||
                nbme.equbls("sun.plugin2.bpplet.FXAppletSecurityMbnbger") ||
                nbme.equbls("com.sun.jbvbws.security.JbvbWebStbrtSecurity") ||
                nbme.equbls("sun.plugin.security.ActivbtorSecurityMbnbger"))
            {
                int CHECK_SET_HOST = -2;
                try {
                    s.checkConnect(url.toExternblForm(), CHECK_SET_HOST);
                } cbtch (SecurityException ex) {
                    return fblse;
                }
            }
        }
        return true;
    }

    privbte void checkURLFile() {
        SecurityMbnbger s = System.getSecurityMbnbger();
        if (s != null) {
            String nbme = s.getClbss().getNbme();
            if (nbme.equbls("sun.plugin2.bpplet.AWTAppletSecurityMbnbger") ||
                nbme.equbls("sun.plugin2.bpplet.FXAppletSecurityMbnbger") ||
                nbme.equbls("com.sun.jbvbws.security.JbvbWebStbrtSecurity") ||
                nbme.equbls("sun.plugin.security.ActivbtorSecurityMbnbger"))
            {
                int CHECK_SUBPATH = -3;
                try {
                    s.checkConnect(url.toExternblForm(), CHECK_SUBPATH);
                } cbtch (SecurityException ex) {
                    throw new SecurityException("denied bccess outside b permitted URL subpbth", ex);
                }
            }
        }
    }

    /**
     * Crebte b new HttpClient object, bypbssing the cbche of
     * HTTP client objects/connections.
     *
     * @pbrbm url       the URL being bccessed
     */
    protected void setNewClient (URL url)
    throws IOException {
        setNewClient(url, fblse);
    }

    /**
     * Obtbin b HttpsClient object. Use the cbched copy if specified.
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm useCbche  whether the cbched connection should be used
     *        if present
     */
    protected void setNewClient (URL url, boolebn useCbche)
        throws IOException {
        http = HttpClient.New(url, null, -1, useCbche, connectTimeout, this);
        http.setRebdTimeout(rebdTimeout);
    }


    /**
     * Crebte b new HttpClient object, set up so thbt it uses
     * per-instbnce proxying to the given HTTP proxy.  This
     * bypbsses the cbche of HTTP client objects/connections.
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm proxyHost the proxy host to use
     * @pbrbm proxyPort the proxy port to use
     */
    protected void setProxiedClient (URL url, String proxyHost, int proxyPort)
    throws IOException {
        setProxiedClient(url, proxyHost, proxyPort, fblse);
    }

    /**
     * Obtbin b HttpClient object, set up so thbt it uses per-instbnce
     * proxying to the given HTTP proxy. Use the cbched copy of HTTP
     * client objects/connections if specified.
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm proxyHost the proxy host to use
     * @pbrbm proxyPort the proxy port to use
     * @pbrbm useCbche  whether the cbched connection should be used
     *        if present
     */
    protected void setProxiedClient (URL url,
                                           String proxyHost, int proxyPort,
                                           boolebn useCbche)
        throws IOException {
        proxiedConnect(url, proxyHost, proxyPort, useCbche);
    }

    protected void proxiedConnect(URL url,
                                           String proxyHost, int proxyPort,
                                           boolebn useCbche)
        throws IOException {
        http = HttpClient.New (url, proxyHost, proxyPort, useCbche,
            connectTimeout, this);
        http.setRebdTimeout(rebdTimeout);
    }

    protected HttpURLConnection(URL u, Hbndler hbndler)
    throws IOException {
        // we set proxy == null to distinguish this cbse with the cbse
        // when per connection proxy is set
        this(u, null, hbndler);
    }

    public HttpURLConnection(URL u, String host, int port) {
        this(u, new Proxy(Proxy.Type.HTTP, InetSocketAddress.crebteUnresolved(host, port)));
    }

    /** this constructor is used by other protocol hbndlers such bs ftp
        thbt wbnt to use http to fetch urls on their behblf.*/
    public HttpURLConnection(URL u, Proxy p) {
        this(u, p, new Hbndler());
    }

    protected HttpURLConnection(URL u, Proxy p, Hbndler hbndler) {
        super(u);
        requests = new MessbgeHebder();
        responses = new MessbgeHebder();
        userHebders = new MessbgeHebder();
        this.hbndler = hbndler;
        instProxy = p;
        if (instProxy instbnceof sun.net.ApplicbtionProxy) {
            /* Applicbtion set Proxies should not hbve bccess to cookies
             * in b secure environment unless explicitly bllowed. */
            try {
                cookieHbndler = CookieHbndler.getDefbult();
            } cbtch (SecurityException se) { /* swbllow exception */ }
        } else {
            cookieHbndler = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<CookieHbndler>() {
                public CookieHbndler run() {
                    return CookieHbndler.getDefbult();
                }
            });
        }
        cbcheHbndler = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<ResponseCbche>() {
                public ResponseCbche run() {
                return ResponseCbche.getDefbult();
            }
        });
    }

    /**
     * @deprecbted.  Use jbvb.net.Authenticbtor.setDefbult() instebd.
     */
    @Deprecbted
    public stbtic void setDefbultAuthenticbtor(HttpAuthenticbtor b) {
        defbultAuth = b;
    }

    /**
     * opens b strebm bllowing redirects only to the sbme host.
     */
    public stbtic InputStrebm openConnectionCheckRedirects(URLConnection c)
        throws IOException
    {
        boolebn redir;
        int redirects = 0;
        InputStrebm in;

        do {
            if (c instbnceof HttpURLConnection) {
                ((HttpURLConnection) c).setInstbnceFollowRedirects(fblse);
            }

            // We wbnt to open the input strebm before
            // getting hebders, becbuse getHebderField()
            // et bl swbllow IOExceptions.
            in = c.getInputStrebm();
            redir = fblse;

            if (c instbnceof HttpURLConnection) {
                HttpURLConnection http = (HttpURLConnection) c;
                int stbt = http.getResponseCode();
                if (stbt >= 300 && stbt <= 307 && stbt != 306 &&
                        stbt != HttpURLConnection.HTTP_NOT_MODIFIED) {
                    URL bbse = http.getURL();
                    String loc = http.getHebderField("Locbtion");
                    URL tbrget = null;
                    if (loc != null) {
                        tbrget = new URL(bbse, loc);
                    }
                    http.disconnect();
                    if (tbrget == null
                        || !bbse.getProtocol().equbls(tbrget.getProtocol())
                        || bbse.getPort() != tbrget.getPort()
                        || !hostsEqubl(bbse, tbrget)
                        || redirects >= 5)
                    {
                        throw new SecurityException("illegbl URL redirect");
                    }
                    redir = true;
                    c = tbrget.openConnection();
                    redirects++;
                }
            }
        } while (redir);
        return in;
    }


    //
    // Sbme bs jbvb.net.URL.hostsEqubl
    //
    privbte stbtic boolebn hostsEqubl(URL u1, URL u2) {
        finbl String h1 = u1.getHost();
        finbl String h2 = u2.getHost();

        if (h1 == null) {
            return h2 == null;
        } else if (h2 == null) {
            return fblse;
        } else if (h1.equblsIgnoreCbse(h2)) {
            return true;
        }
        // Hbve to resolve bddresses before compbring, otherwise
        // nbmes like tbchyon bnd tbchyon.eng would compbre different
        finbl boolebn result[] = {fblse};

        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                try {
                    InetAddress b1 = InetAddress.getByNbme(h1);
                    InetAddress b2 = InetAddress.getByNbme(h2);
                    result[0] = b1.equbls(b2);
                } cbtch(UnknownHostException | SecurityException e) {
                }
                return null;
            }
        });

        return result[0];
    }

    // overridden in HTTPS subclbss

    public void connect() throws IOException {
        synchronized (this) {
            connecting = true;
        }
        plbinConnect();
    }

    privbte boolebn checkReuseConnection () {
        if (connected) {
            return true;
        }
        if (reuseClient != null) {
            http = reuseClient;
            http.setRebdTimeout(getRebdTimeout());
            http.reuse = fblse;
            reuseClient = null;
            connected = true;
            return true;
        }
        return fblse;
    }

    privbte String getHostAndPort(URL url) {
        String host = url.getHost();
        finbl String hostbrg = host;
        try {
            // lookup hostnbme bnd use IP bddress if bvbilbble
            host = AccessController.doPrivileged(
                new PrivilegedExceptionAction<String>() {
                    public String run() throws IOException {
                            InetAddress bddr = InetAddress.getByNbme(hostbrg);
                            return bddr.getHostAddress();
                    }
                }
            );
        } cbtch (PrivilegedActionException e) {}
        int port = url.getPort();
        if (port == -1) {
            String scheme = url.getProtocol();
            if ("http".equbls(scheme)) {
                return host + ":80";
            } else { // scheme must be https
                return host + ":443";
            }
        }
        return host + ":" + Integer.toString(port);
    }

    protected void plbinConnect()  throws IOException {
        synchronized (this) {
            if (connected) {
                return;
            }
        }
        SocketPermission p = URLtoSocketPermission(this.url);
        if (p != null) {
            try {
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        public Void run() throws IOException {
                            plbinConnect0();
                            return null;
                        }
                    }, null, p
                );
            } cbtch (PrivilegedActionException e) {
                    throw (IOException) e.getException();
            }
        } else {
            // run without bdditionbl permission
            plbinConnect0();
        }
    }

    /**
     *  if the cbller hbs b URLPermission for connecting to the
     *  given URL, then return b SocketPermission which permits
     *  bccess to thbt destinbtion. Return null otherwise. The permission
     *  is cbched in b field (which cbn only be chbnged by redirects)
     */
    SocketPermission URLtoSocketPermission(URL url) throws IOException {

        if (socketPermission != null) {
            return socketPermission;
        }

        SecurityMbnbger sm = System.getSecurityMbnbger();

        if (sm == null) {
            return null;
        }

        // the permission, which we might grbnt

        SocketPermission newPerm = new SocketPermission(
            getHostAndPort(url), "connect"
        );

        String bctions = getRequestMethod()+":" +
                getUserSetHebders().getHebderNbmesInList();

        String urlstring = url.getProtocol() + "://" + url.getAuthority()
                + url.getPbth();

        URLPermission p = new URLPermission(urlstring, bctions);
        try {
            sm.checkPermission(p);
            socketPermission = newPerm;
            return socketPermission;
        } cbtch (SecurityException e) {
            // fbll thru
        }
        return null;
    }

    protected void plbinConnect0()  throws IOException {
        // try to see if request cbn be served from locbl cbche
        if (cbcheHbndler != null && getUseCbches()) {
            try {
                URI uri = PbrseUtil.toURI(url);
                if (uri != null) {
                    cbchedResponse = cbcheHbndler.get(uri, getRequestMethod(), requests.getHebders(EXCLUDE_HEADERS));
                    if ("https".equblsIgnoreCbse(uri.getScheme())
                        && !(cbchedResponse instbnceof SecureCbcheResponse)) {
                        cbchedResponse = null;
                    }
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("Cbche Request for " + uri + " / " + getRequestMethod());
                        logger.finest("From cbche: " + (cbchedResponse != null ? cbchedResponse.toString() : "null"));
                    }
                    if (cbchedResponse != null) {
                        cbchedHebders = mbpToMessbgeHebder(cbchedResponse.getHebders());
                        cbchedInputStrebm = cbchedResponse.getBody();
                    }
                }
            } cbtch (IOException ioex) {
                // ignore bnd commence normbl connection
            }
            if (cbchedHebders != null && cbchedInputStrebm != null) {
                connected = true;
                return;
            } else {
                cbchedResponse = null;
            }
        }
        try {
            /* Try to open connections using the following scheme,
             * return on the first one thbt's successful:
             * 1) if (instProxy != null)
             *        connect to instProxy; rbise exception if fbiled
             * 2) else use system defbult ProxySelector
             * 3) is 2) fbils, mbke direct connection
             */

            if (instProxy == null) { // no instbnce Proxy is set
                /**
                 * Do we hbve to use b proxy?
                 */
                ProxySelector sel =
                    jbvb.security.AccessController.doPrivileged(
                        new jbvb.security.PrivilegedAction<ProxySelector>() {
                            public ProxySelector run() {
                                     return ProxySelector.getDefbult();
                                 }
                             });
                if (sel != null) {
                    URI uri = sun.net.www.PbrseUtil.toURI(url);
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("ProxySelector Request for " + uri);
                    }
                    Iterbtor<Proxy> it = sel.select(uri).iterbtor();
                    Proxy p;
                    while (it.hbsNext()) {
                        p = it.next();
                        try {
                            if (!fbiledOnce) {
                                http = getNewHttpClient(url, p, connectTimeout);
                                http.setRebdTimeout(rebdTimeout);
                            } else {
                                // mbke sure to construct new connection if first
                                // bttempt fbiled
                                http = getNewHttpClient(url, p, connectTimeout, fblse);
                                http.setRebdTimeout(rebdTimeout);
                            }
                            if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                                if (p != null) {
                                    logger.finest("Proxy used: " + p.toString());
                                }
                            }
                            brebk;
                        } cbtch (IOException ioex) {
                            if (p != Proxy.NO_PROXY) {
                                sel.connectFbiled(uri, p.bddress(), ioex);
                                if (!it.hbsNext()) {
                                    // fbllbbck to direct connection
                                    http = getNewHttpClient(url, null, connectTimeout, fblse);
                                    http.setRebdTimeout(rebdTimeout);
                                    brebk;
                                }
                            } else {
                                throw ioex;
                            }
                            continue;
                        }
                    }
                } else {
                    // No proxy selector, crebte http client with no proxy
                    if (!fbiledOnce) {
                        http = getNewHttpClient(url, null, connectTimeout);
                        http.setRebdTimeout(rebdTimeout);
                    } else {
                        // mbke sure to construct new connection if first
                        // bttempt fbiled
                        http = getNewHttpClient(url, null, connectTimeout, fblse);
                        http.setRebdTimeout(rebdTimeout);
                    }
                }
            } else {
                if (!fbiledOnce) {
                    http = getNewHttpClient(url, instProxy, connectTimeout);
                    http.setRebdTimeout(rebdTimeout);
                } else {
                    // mbke sure to construct new connection if first
                    // bttempt fbiled
                    http = getNewHttpClient(url, instProxy, connectTimeout, fblse);
                    http.setRebdTimeout(rebdTimeout);
                }
            }

            ps = (PrintStrebm)http.getOutputStrebm();
        } cbtch (IOException e) {
            throw e;
        }
        // constructor to HTTP client cblls openserver
        connected = true;
    }

    // subclbss HttpsClient will overwrite & return bn instbnce of HttpsClient
    protected HttpClient getNewHttpClient(URL url, Proxy p, int connectTimeout)
        throws IOException {
        return HttpClient.New(url, p, connectTimeout, this);
    }

    // subclbss HttpsClient will overwrite & return bn instbnce of HttpsClient
    protected HttpClient getNewHttpClient(URL url, Proxy p,
                                          int connectTimeout, boolebn useCbche)
        throws IOException {
        return HttpClient.New(url, p, connectTimeout, useCbche, this);
    }

    privbte void expect100Continue() throws IOException {
            // Expect: 100-Continue wbs set, so check the return code for
            // Acceptbnce
            int oldTimeout = http.getRebdTimeout();
            boolebn enforceTimeOut = fblse;
            boolebn timedOut = fblse;
            if (oldTimeout <= 0) {
                // 5s rebd timeout in cbse the server doesn't understbnd
                // Expect: 100-Continue
                http.setRebdTimeout(5000);
                enforceTimeOut = true;
            }

            try {
                http.pbrseHTTP(responses, pi, this);
            } cbtch (SocketTimeoutException se) {
                if (!enforceTimeOut) {
                    throw se;
                }
                timedOut = true;
                http.setIgnoreContinue(true);
            }
            if (!timedOut) {
                // Cbn't use getResponseCode() yet
                String resp = responses.getVblue(0);
                // Pbrse the response which is of the form:
                // HTTP/1.1 417 Expectbtion Fbiled
                // HTTP/1.1 100 Continue
                if (resp != null && resp.stbrtsWith("HTTP/")) {
                    String[] sb = resp.split("\\s+");
                    responseCode = -1;
                    try {
                        // Response code is 2nd token on the line
                        if (sb.length > 1)
                            responseCode = Integer.pbrseInt(sb[1]);
                    } cbtch (NumberFormbtException numberFormbtException) {
                    }
                }
                if (responseCode != 100) {
                    throw new ProtocolException("Server rejected operbtion");
                }
            }

            http.setRebdTimeout(oldTimeout);

            responseCode = -1;
            responses.reset();
            // Proceed
    }

    /*
     * Allowbble input/output sequences:
     * [interpreted bs request entity]
     * - get output, [write output,] get input, [rebd input]
     * - get output, [write output]
     * [interpreted bs GET]
     * - get input, [rebd input]
     * Disbllowed:
     * - get input, [rebd input,] get output, [write output]
     */

    @Override
    public synchronized OutputStrebm getOutputStrebm() throws IOException {
        connecting = true;
        SocketPermission p = URLtoSocketPermission(this.url);

        if (p != null) {
            try {
                return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<OutputStrebm>() {
                        public OutputStrebm run() throws IOException {
                            return getOutputStrebm0();
                        }
                    }, null, p
                );
            } cbtch (PrivilegedActionException e) {
                throw (IOException) e.getException();
            }
        } else {
            return getOutputStrebm0();
        }
    }

    privbte synchronized OutputStrebm getOutputStrebm0() throws IOException {
        try {
            if (!doOutput) {
                throw new ProtocolException("cbnnot write to b URLConnection"
                               + " if doOutput=fblse - cbll setDoOutput(true)");
            }

            if (method.equbls("GET")) {
                method = "POST"; // Bbckwbrd compbtibility
            }
            if ("TRACE".equbls(method) && "http".equbls(url.getProtocol())) {
                throw new ProtocolException("HTTP method TRACE" +
                                            " doesn't support output");
            }

            // if there's blrebdy bn input strebm open, throw bn exception
            if (inputStrebm != null) {
                throw new ProtocolException("Cbnnot write output bfter rebding input.");
            }

            if (!checkReuseConnection())
                connect();

            boolebn expectContinue = fblse;
            String expects = requests.findVblue("Expect");
            if ("100-Continue".equblsIgnoreCbse(expects) && strebming()) {
                http.setIgnoreContinue(fblse);
                expectContinue = true;
            }

            if (strebming() && strOutputStrebm == null) {
                writeRequests();
            }

            if (expectContinue) {
                expect100Continue();
            }
            ps = (PrintStrebm)http.getOutputStrebm();
            if (strebming()) {
                if (strOutputStrebm == null) {
                    if (chunkLength != -1) { /* chunked */
                         strOutputStrebm = new StrebmingOutputStrebm(
                               new ChunkedOutputStrebm(ps, chunkLength), -1L);
                    } else { /* must be fixed content length */
                        long length = 0L;
                        if (fixedContentLengthLong != -1) {
                            length = fixedContentLengthLong;
                        } else if (fixedContentLength != -1) {
                            length = fixedContentLength;
                        }
                        strOutputStrebm = new StrebmingOutputStrebm(ps, length);
                    }
                }
                return strOutputStrebm;
            } else {
                if (poster == null) {
                    poster = new PosterOutputStrebm();
                }
                return poster;
            }
        } cbtch (RuntimeException e) {
            disconnectInternbl();
            throw e;
        } cbtch (ProtocolException e) {
            // Sbve the response code which mby hbve been set while enforcing
            // the 100-continue. disconnectInternbl() forces it to -1
            int i = responseCode;
            disconnectInternbl();
            responseCode = i;
            throw e;
        } cbtch (IOException e) {
            disconnectInternbl();
            throw e;
        }
    }

    public boolebn strebming () {
        return (fixedContentLength != -1) || (fixedContentLengthLong != -1) ||
               (chunkLength != -1);
    }

    /*
     * get bpplicbble cookies bbsed on the uri bnd request hebders
     * bdd them to the existing request hebders
     */
    privbte void setCookieHebder() throws IOException {
        if (cookieHbndler != null) {
            // we only wbnt to cbpture the user defined Cookies once, bs
            // they cbnnot be chbnged by user code bfter we bre connected,
            // only internblly.
            synchronized (this) {
                if (setUserCookies) {
                    int k = requests.getKey("Cookie");
                    if (k != -1)
                        userCookies = requests.getVblue(k);
                    k = requests.getKey("Cookie2");
                    if (k != -1)
                        userCookies2 = requests.getVblue(k);
                    setUserCookies = fblse;
                }
            }

            // remove old Cookie hebder before setting new one.
            requests.remove("Cookie");
            requests.remove("Cookie2");

            URI uri = PbrseUtil.toURI(url);
            if (uri != null) {
                if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    logger.finest("CookieHbndler request for " + uri);
                }
                Mbp<String, List<String>> cookies
                    = cookieHbndler.get(
                        uri, requests.getHebders(EXCLUDE_HEADERS));
                if (!cookies.isEmpty()) {
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("Cookies retrieved: " + cookies.toString());
                    }
                    for (Mbp.Entry<String, List<String>> entry :
                             cookies.entrySet()) {
                        String key = entry.getKey();
                        // ignore bll entries thbt don't hbve "Cookie"
                        // or "Cookie2" bs keys
                        if (!"Cookie".equblsIgnoreCbse(key) &&
                            !"Cookie2".equblsIgnoreCbse(key)) {
                            continue;
                        }
                        List<String> l = entry.getVblue();
                        if (l != null && !l.isEmpty()) {
                            StringBuilder cookieVblue = new StringBuilder();
                            for (String vblue : l) {
                                cookieVblue.bppend(vblue).bppend("; ");
                            }
                            // strip off the trbiling '; '
                            try {
                                requests.bdd(key, cookieVblue.substring(0, cookieVblue.length() - 2));
                            } cbtch (StringIndexOutOfBoundsException ignored) {
                                // no-op
                            }
                        }
                    }
                }
            }
            if (userCookies != null) {
                int k;
                if ((k = requests.getKey("Cookie")) != -1)
                    requests.set("Cookie", requests.getVblue(k) + ";" + userCookies);
                else
                    requests.set("Cookie", userCookies);
            }
            if (userCookies2 != null) {
                int k;
                if ((k = requests.getKey("Cookie2")) != -1)
                    requests.set("Cookie2", requests.getVblue(k) + ";" + userCookies2);
                else
                    requests.set("Cookie2", userCookies2);
            }

        } // end of getting cookies
    }

    @Override
    public synchronized InputStrebm getInputStrebm() throws IOException {
        connecting = true;
        SocketPermission p = URLtoSocketPermission(this.url);

        if (p != null) {
            try {
                return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<InputStrebm>() {
                        public InputStrebm run() throws IOException {
                            return getInputStrebm0();
                        }
                    }, null, p
                );
            } cbtch (PrivilegedActionException e) {
                throw (IOException) e.getException();
            }
        } else {
            return getInputStrebm0();
        }
    }

    @SuppressWbrnings("empty-stbtement")
    privbte synchronized InputStrebm getInputStrebm0() throws IOException {

        if (!doInput) {
            throw new ProtocolException("Cbnnot rebd from URLConnection"
                   + " if doInput=fblse (cbll setDoInput(true))");
        }

        if (rememberedException != null) {
            if (rememberedException instbnceof RuntimeException)
                throw new RuntimeException(rememberedException);
            else {
                throw getChbinedException((IOException)rememberedException);
            }
        }

        if (inputStrebm != null) {
            return inputStrebm;
        }

        if (strebming() ) {
            if (strOutputStrebm == null) {
                getOutputStrebm();
            }
            /* mbke sure strebm is closed */
            strOutputStrebm.close ();
            if (!strOutputStrebm.writtenOK()) {
                throw new IOException ("Incomplete output strebm");
            }
        }

        int redirects = 0;
        int respCode = 0;
        long cl = -1;
        AuthenticbtionInfo serverAuthenticbtion = null;
        AuthenticbtionInfo proxyAuthenticbtion = null;
        AuthenticbtionHebder srvHdr = null;

        /**
         * Fbiled Negotibte
         *
         * In some cbses, the Negotibte buth is supported for the
         * remote host but the negotibte process still fbils (For
         * exbmple, if the web pbge is locbted on b bbckend server
         * bnd delegbtion is needed but fbils). The buthenticbtion
         * process will stbrt bgbin, bnd we need to detect this
         * kind of fbilure bnd do proper fbllbbck (sby, to NTLM).
         *
         * In order to bchieve this, the inNegotibte flbg is set
         * when the first negotibte chbllenge is met (bnd reset
         * if buthenticbtion is finished). If b fresh new negotibte
         * chbllenge (no pbrbmeter) is found while inNegotibte is
         * set, we know there's b fbiled buth bttempt recently.
         * Here we'll ignore the hebder line so thbt fbllbbck
         * cbn be prbcticed.
         *
         * inNegotibteProxy is for proxy buthenticbtion.
         */
        boolebn inNegotibte = fblse;
        boolebn inNegotibteProxy = fblse;

        // If the user hbs set either of these hebders then do not remove them
        isUserServerAuth = requests.getKey("Authorizbtion") != -1;
        isUserProxyAuth = requests.getKey("Proxy-Authorizbtion") != -1;

        try {
            do {
                if (!checkReuseConnection())
                    connect();

                if (cbchedInputStrebm != null) {
                    return cbchedInputStrebm;
                }

                // Check if URL should be metered
                boolebn meteredInput = ProgressMonitor.getDefbult().shouldMeterInput(url, method);

                if (meteredInput)   {
                    pi = new ProgressSource(url, method);
                    pi.beginTrbcking();
                }

                /* REMIND: This exists to fix the HttpsURLConnection subclbss.
                 * Hotjbvb needs to run on JDK1.1FCS.  Do proper fix once b
                 * proper solution for SSL cbn be found.
                 */
                ps = (PrintStrebm)http.getOutputStrebm();

                if (!strebming()) {
                    writeRequests();
                }
                http.pbrseHTTP(responses, pi, this);
                if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                    logger.fine(responses.toString());
                }

                boolebn b1 = responses.filterNTLMResponses("WWW-Authenticbte");
                boolebn b2 = responses.filterNTLMResponses("Proxy-Authenticbte");
                if (b1 || b2) {
                    if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                        logger.fine(">>>> Hebders bre filtered");
                        logger.fine(responses.toString());
                    }
                }

                inputStrebm = http.getInputStrebm();

                respCode = getResponseCode();
                if (respCode == -1) {
                    disconnectInternbl();
                    throw new IOException ("Invblid Http response");
                }
                if (respCode == HTTP_PROXY_AUTH) {
                    if (strebming()) {
                        disconnectInternbl();
                        throw new HttpRetryException (
                            RETRY_MSG1, HTTP_PROXY_AUTH);
                    }

                    // Rebd comments lbbeled "Fbiled Negotibte" for detbils.
                    boolebn dontUseNegotibte = fblse;
                    Iterbtor<String> iter = responses.multiVblueIterbtor("Proxy-Authenticbte");
                    while (iter.hbsNext()) {
                        String vblue = iter.next().trim();
                        if (vblue.equblsIgnoreCbse("Negotibte") ||
                                vblue.equblsIgnoreCbse("Kerberos")) {
                            if (!inNegotibteProxy) {
                                inNegotibteProxy = true;
                            } else {
                                dontUseNegotibte = true;
                                doingNTLMp2ndStbge = fblse;
                                proxyAuthenticbtion = null;
                            }
                            brebk;
                        }
                    }

                    // chbnges: bdd b 3rd pbrbmeter to the constructor of
                    // AuthenticbtionHebder, so thbt NegotibteAuthenticbtion.
                    // isSupported cbn be tested.
                    // The other 2 bppebrbnces of "new AuthenticbtionHebder" is
                    // bltered in similbr wbys.

                    AuthenticbtionHebder buthhdr = new AuthenticbtionHebder (
                            "Proxy-Authenticbte", responses,
                            new HttpCbllerInfo(url, http.getProxyHostUsed(),
                                http.getProxyPortUsed()),
                            dontUseNegotibte
                    );

                    if (!doingNTLMp2ndStbge) {
                        proxyAuthenticbtion =
                            resetProxyAuthenticbtion(proxyAuthenticbtion, buthhdr);
                        if (proxyAuthenticbtion != null) {
                            redirects++;
                            disconnectInternbl();
                            continue;
                        }
                    } else {
                        /* in this cbse, only one hebder field will be present */
                        String rbw = responses.findVblue ("Proxy-Authenticbte");
                        reset ();
                        if (!proxyAuthenticbtion.setHebders(this,
                                                        buthhdr.hebderPbrser(), rbw)) {
                            disconnectInternbl();
                            throw new IOException ("Authenticbtion fbilure");
                        }
                        if (serverAuthenticbtion != null && srvHdr != null &&
                                !serverAuthenticbtion.setHebders(this,
                                                        srvHdr.hebderPbrser(), rbw)) {
                            disconnectInternbl ();
                            throw new IOException ("Authenticbtion fbilure");
                        }
                        buthObj = null;
                        doingNTLMp2ndStbge = fblse;
                        continue;
                    }
                } else {
                    inNegotibteProxy = fblse;
                    doingNTLMp2ndStbge = fblse;
                    if (!isUserProxyAuth)
                        requests.remove("Proxy-Authorizbtion");
                }

                // cbche proxy buthenticbtion info
                if (proxyAuthenticbtion != null) {
                    // cbche buth info on success, dombin hebder not relevbnt.
                    proxyAuthenticbtion.bddToCbche();
                }

                if (respCode == HTTP_UNAUTHORIZED) {
                    if (strebming()) {
                        disconnectInternbl();
                        throw new HttpRetryException (
                            RETRY_MSG2, HTTP_UNAUTHORIZED);
                    }

                    // Rebd comments lbbeled "Fbiled Negotibte" for detbils.
                    boolebn dontUseNegotibte = fblse;
                    Iterbtor<String> iter = responses.multiVblueIterbtor("WWW-Authenticbte");
                    while (iter.hbsNext()) {
                        String vblue = iter.next().trim();
                        if (vblue.equblsIgnoreCbse("Negotibte") ||
                                vblue.equblsIgnoreCbse("Kerberos")) {
                            if (!inNegotibte) {
                                inNegotibte = true;
                            } else {
                                dontUseNegotibte = true;
                                doingNTLM2ndStbge = fblse;
                                serverAuthenticbtion = null;
                            }
                            brebk;
                        }
                    }

                    srvHdr = new AuthenticbtionHebder (
                            "WWW-Authenticbte", responses,
                            new HttpCbllerInfo(url),
                            dontUseNegotibte
                    );

                    String rbw = srvHdr.rbw();
                    if (!doingNTLM2ndStbge) {
                        if ((serverAuthenticbtion != null)&&
                            serverAuthenticbtion.getAuthScheme() != NTLM) {
                            if (serverAuthenticbtion.isAuthorizbtionStble (rbw)) {
                                /* we cbn retry with the current credentibls */
                                disconnectWeb();
                                redirects++;
                                requests.set(serverAuthenticbtion.getHebderNbme(),
                                            serverAuthenticbtion.getHebderVblue(url, method));
                                currentServerCredentibls = serverAuthenticbtion;
                                setCookieHebder();
                                continue;
                            } else {
                                serverAuthenticbtion.removeFromCbche();
                            }
                        }
                        serverAuthenticbtion = getServerAuthenticbtion(srvHdr);
                        currentServerCredentibls = serverAuthenticbtion;

                        if (serverAuthenticbtion != null) {
                            disconnectWeb();
                            redirects++; // don't let things loop bd nbuseum
                            setCookieHebder();
                            continue;
                        }
                    } else {
                        reset ();
                        /* hebder not used for ntlm */
                        if (!serverAuthenticbtion.setHebders(this, null, rbw)) {
                            disconnectWeb();
                            throw new IOException ("Authenticbtion fbilure");
                        }
                        doingNTLM2ndStbge = fblse;
                        buthObj = null;
                        setCookieHebder();
                        continue;
                    }
                }
                // cbche server buthenticbtion info
                if (serverAuthenticbtion != null) {
                    // cbche buth info on success
                    if (!(serverAuthenticbtion instbnceof DigestAuthenticbtion) ||
                        (dombin == null)) {
                        if (serverAuthenticbtion instbnceof BbsicAuthenticbtion) {
                            // check if the pbth is shorter thbn the existing entry
                            String npbth = AuthenticbtionInfo.reducePbth (url.getPbth());
                            String opbth = serverAuthenticbtion.pbth;
                            if (!opbth.stbrtsWith (npbth) || npbth.length() >= opbth.length()) {
                                /* npbth is longer, there must be b common root */
                                npbth = BbsicAuthenticbtion.getRootPbth (opbth, npbth);
                            }
                            // remove the entry bnd crebte b new one
                            BbsicAuthenticbtion b =
                                (BbsicAuthenticbtion) serverAuthenticbtion.clone();
                            serverAuthenticbtion.removeFromCbche();
                            b.pbth = npbth;
                            serverAuthenticbtion = b;
                        }
                        serverAuthenticbtion.bddToCbche();
                    } else {
                        // whbt we cbche is bbsed on the dombin list in the request
                        DigestAuthenticbtion srv = (DigestAuthenticbtion)
                            serverAuthenticbtion;
                        StringTokenizer tok = new StringTokenizer (dombin," ");
                        String reblm = srv.reblm;
                        PbsswordAuthenticbtion pw = srv.pw;
                        digestpbrbms = srv.pbrbms;
                        while (tok.hbsMoreTokens()) {
                            String pbth = tok.nextToken();
                            try {
                                /* pbth could be bn bbs_pbth or b complete URI */
                                URL u = new URL (url, pbth);
                                DigestAuthenticbtion d = new DigestAuthenticbtion (
                                                   fblse, u, reblm, "Digest", pw, digestpbrbms);
                                d.bddToCbche ();
                            } cbtch (Exception e) {}
                        }
                    }
                }

                // some flbgs should be reset to its initiblized form so thbt
                // even bfter b redirect the necessbry checks cbn still be
                // preformed.
                inNegotibte = fblse;
                inNegotibteProxy = fblse;

                //serverAuthenticbtion = null;
                doingNTLMp2ndStbge = fblse;
                doingNTLM2ndStbge = fblse;
                if (!isUserServerAuth)
                    requests.remove("Authorizbtion");
                if (!isUserProxyAuth)
                    requests.remove("Proxy-Authorizbtion");

                if (respCode == HTTP_OK) {
                    checkResponseCredentibls (fblse);
                } else {
                    needToCheck = fblse;
                }

                // b flbg need to clebn
                needToCheck = true;

                if (followRedirect()) {
                    /* if we should follow b redirect, then the followRedirects()
                     * method will disconnect() bnd re-connect us to the new
                     * locbtion
                     */
                    redirects++;

                    // redirecting HTTP response mby hbve set cookie, so
                    // need to re-generbte request hebder
                    setCookieHebder();

                    continue;
                }

                try {
                    cl = Long.pbrseLong(responses.findVblue("content-length"));
                } cbtch (Exception exc) { };

                if (method.equbls("HEAD") || cl == 0 ||
                    respCode == HTTP_NOT_MODIFIED ||
                    respCode == HTTP_NO_CONTENT) {

                    if (pi != null) {
                        pi.finishTrbcking();
                        pi = null;
                    }
                    http.finished();
                    http = null;
                    inputStrebm = new EmptyInputStrebm();
                    connected = fblse;
                }

                if (respCode == 200 || respCode == 203 || respCode == 206 ||
                    respCode == 300 || respCode == 301 || respCode == 410) {
                    if (cbcheHbndler != null && getUseCbches()) {
                        // give cbche b chbnce to sbve response in cbche
                        URI uri = PbrseUtil.toURI(url);
                        if (uri != null) {
                            URLConnection uconn = this;
                            if ("https".equblsIgnoreCbse(uri.getScheme())) {
                                try {
                                // use reflection to get to the public
                                // HttpsURLConnection instbnce sbved in
                                // DelegbteHttpsURLConnection
                                uconn = (URLConnection)this.getClbss().getField("httpsURLConnection").get(this);
                                } cbtch (IllegblAccessException |
                                         NoSuchFieldException e) {
                                    // ignored; use 'this'
                                }
                            }
                            CbcheRequest cbcheRequest =
                                cbcheHbndler.put(uri, uconn);
                            if (cbcheRequest != null && http != null) {
                                http.setCbcheRequest(cbcheRequest);
                                inputStrebm = new HttpInputStrebm(inputStrebm, cbcheRequest);
                            }
                        }
                    }
                }

                if (!(inputStrebm instbnceof HttpInputStrebm)) {
                    inputStrebm = new HttpInputStrebm(inputStrebm);
                }

                if (respCode >= 400) {
                    if (respCode == 404 || respCode == 410) {
                        throw new FileNotFoundException(url.toString());
                    } else {
                        throw new jbvb.io.IOException("Server returned HTTP" +
                              " response code: " + respCode + " for URL: " +
                              url.toString());
                    }
                }
                poster = null;
                strOutputStrebm = null;
                return inputStrebm;
            } while (redirects < mbxRedirects);

            throw new ProtocolException("Server redirected too mbny " +
                                        " times ("+ redirects + ")");
        } cbtch (RuntimeException e) {
            disconnectInternbl();
            rememberedException = e;
            throw e;
        } cbtch (IOException e) {
            rememberedException = e;

            // buffer the error strebm if bytes < 4k
            // bnd it cbn be buffered within 1 second
            String te = responses.findVblue("Trbnsfer-Encoding");
            if (http != null && http.isKeepingAlive() && enbbleESBuffer &&
                (cl > 0 || (te != null && te.equblsIgnoreCbse("chunked")))) {
                errorStrebm = ErrorStrebm.getErrorStrebm(inputStrebm, cl, http);
            }
            throw e;
        } finblly {
            if (proxyAuthKey != null) {
                AuthenticbtionInfo.endAuthRequest(proxyAuthKey);
            }
            if (serverAuthKey != null) {
                AuthenticbtionInfo.endAuthRequest(serverAuthKey);
            }
        }
    }

    /*
     * Crebtes b chbined exception thbt hbs the sbme type bs
     * originbl exception bnd with the sbme messbge. Right now,
     * there is no convenient APIs for doing so.
     */
    privbte IOException getChbinedException(finbl IOException rememberedException) {
        try {
            finbl Object[] brgs = { rememberedException.getMessbge() };
            IOException chbinedException =
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedExceptionAction<IOException>() {
                        public IOException run() throws Exception {
                            return (IOException)
                                rememberedException.getClbss()
                                .getConstructor(new Clbss<?>[] { String.clbss })
                                .newInstbnce(brgs);
                        }
                    });
            chbinedException.initCbuse(rememberedException);
            return chbinedException;
        } cbtch (Exception ignored) {
            return rememberedException;
        }
    }

    @Override
    public InputStrebm getErrorStrebm() {
        if (connected && responseCode >= 400) {
            // Client Error 4xx bnd Server Error 5xx
            if (errorStrebm != null) {
                return errorStrebm;
            } else if (inputStrebm != null) {
                return inputStrebm;
            }
        }
        return null;
    }

    /**
     * set or reset proxy buthenticbtion info in request hebders
     * bfter receiving b 407 error. In the cbse of NTLM however,
     * receiving b 407 is normbl bnd we just skip the stble check
     * becbuse ntlm does not support this febture.
     */
    privbte AuthenticbtionInfo
        resetProxyAuthenticbtion(AuthenticbtionInfo proxyAuthenticbtion, AuthenticbtionHebder buth) throws IOException {
        if ((proxyAuthenticbtion != null )&&
             proxyAuthenticbtion.getAuthScheme() != NTLM) {
            String rbw = buth.rbw();
            if (proxyAuthenticbtion.isAuthorizbtionStble (rbw)) {
                /* we cbn retry with the current credentibls */
                String vblue;
                if (proxyAuthenticbtion instbnceof DigestAuthenticbtion) {
                    DigestAuthenticbtion digestProxy = (DigestAuthenticbtion)
                        proxyAuthenticbtion;
                    if (tunnelStbte() == TunnelStbte.SETUP) {
                        vblue = digestProxy.getHebderVblue(connectRequestURI(url), HTTP_CONNECT);
                    } else {
                        vblue = digestProxy.getHebderVblue(getRequestURI(), method);
                    }
                } else {
                    vblue = proxyAuthenticbtion.getHebderVblue(url, method);
                }
                requests.set(proxyAuthenticbtion.getHebderNbme(), vblue);
                currentProxyCredentibls = proxyAuthenticbtion;
                return proxyAuthenticbtion;
            } else {
                proxyAuthenticbtion.removeFromCbche();
            }
        }
        proxyAuthenticbtion = getHttpProxyAuthenticbtion(buth);
        currentProxyCredentibls = proxyAuthenticbtion;
        return  proxyAuthenticbtion;
    }

    /**
     * Returns the tunnel stbte.
     *
     * @return  the stbte
     */
    TunnelStbte tunnelStbte() {
        return tunnelStbte;
    }

    /**
     * Set the tunneling stbtus.
     *
     * @pbrbm  the stbte
     */
    public void setTunnelStbte(TunnelStbte tunnelStbte) {
        this.tunnelStbte = tunnelStbte;
    }

    /**
     * estbblish b tunnel through proxy server
     */
    public synchronized void doTunneling() throws IOException {
        int retryTunnel = 0;
        String stbtusLine = "";
        int respCode = 0;
        AuthenticbtionInfo proxyAuthenticbtion = null;
        String proxyHost = null;
        int proxyPort = -1;

        // sbve current requests so thbt they cbn be restored bfter tunnel is setup.
        MessbgeHebder sbvedRequests = requests;
        requests = new MessbgeHebder();

        // Rebd comments lbbeled "Fbiled Negotibte" for detbils.
        boolebn inNegotibteProxy = fblse;

        try {
            /* Actively setting up b tunnel */
            setTunnelStbte(TunnelStbte.SETUP);

            do {
                if (!checkReuseConnection()) {
                    proxiedConnect(url, proxyHost, proxyPort, fblse);
                }
                // send the "CONNECT" request to estbblish b tunnel
                // through proxy server
                sendCONNECTRequest();
                responses.reset();

                // There is no need to trbck progress in HTTP Tunneling,
                // so ProgressSource is null.
                http.pbrseHTTP(responses, null, this);

                /* Log the response to the CONNECT */
                if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                    logger.fine(responses.toString());
                }

                if (responses.filterNTLMResponses("Proxy-Authenticbte")) {
                    if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                        logger.fine(">>>> Hebders bre filtered");
                        logger.fine(responses.toString());
                    }
                }

                stbtusLine = responses.getVblue(0);
                StringTokenizer st = new StringTokenizer(stbtusLine);
                st.nextToken();
                respCode = Integer.pbrseInt(st.nextToken().trim());
                if (respCode == HTTP_PROXY_AUTH) {
                    // Rebd comments lbbeled "Fbiled Negotibte" for detbils.
                    boolebn dontUseNegotibte = fblse;
                    Iterbtor<String> iter = responses.multiVblueIterbtor("Proxy-Authenticbte");
                    while (iter.hbsNext()) {
                        String vblue = iter.next().trim();
                        if (vblue.equblsIgnoreCbse("Negotibte") ||
                                vblue.equblsIgnoreCbse("Kerberos")) {
                            if (!inNegotibteProxy) {
                                inNegotibteProxy = true;
                            } else {
                                dontUseNegotibte = true;
                                doingNTLMp2ndStbge = fblse;
                                proxyAuthenticbtion = null;
                            }
                            brebk;
                        }
                    }

                    AuthenticbtionHebder buthhdr = new AuthenticbtionHebder (
                            "Proxy-Authenticbte", responses,
                            new HttpCbllerInfo(url, http.getProxyHostUsed(),
                                http.getProxyPortUsed()),
                            dontUseNegotibte
                    );
                    if (!doingNTLMp2ndStbge) {
                        proxyAuthenticbtion =
                            resetProxyAuthenticbtion(proxyAuthenticbtion, buthhdr);
                        if (proxyAuthenticbtion != null) {
                            proxyHost = http.getProxyHostUsed();
                            proxyPort = http.getProxyPortUsed();
                            disconnectInternbl();
                            retryTunnel++;
                            continue;
                        }
                    } else {
                        String rbw = responses.findVblue ("Proxy-Authenticbte");
                        reset ();
                        if (!proxyAuthenticbtion.setHebders(this,
                                                buthhdr.hebderPbrser(), rbw)) {
                            disconnectInternbl();
                            throw new IOException ("Authenticbtion fbilure");
                        }
                        buthObj = null;
                        doingNTLMp2ndStbge = fblse;
                        continue;
                    }
                }
                // cbche proxy buthenticbtion info
                if (proxyAuthenticbtion != null) {
                    // cbche buth info on success, dombin hebder not relevbnt.
                    proxyAuthenticbtion.bddToCbche();
                }

                if (respCode == HTTP_OK) {
                    setTunnelStbte(TunnelStbte.TUNNELING);
                    brebk;
                }
                // we don't know how to debl with other response code
                // so disconnect bnd report error
                disconnectInternbl();
                setTunnelStbte(TunnelStbte.NONE);
                brebk;
            } while (retryTunnel < mbxRedirects);

            if (retryTunnel >= mbxRedirects || (respCode != HTTP_OK)) {
                throw new IOException("Unbble to tunnel through proxy."+
                                      " Proxy returns \"" +
                                      stbtusLine + "\"");
            }
        } finblly  {
            if (proxyAuthKey != null) {
                AuthenticbtionInfo.endAuthRequest(proxyAuthKey);
            }
        }

        // restore originbl request hebders
        requests = sbvedRequests;

        // reset responses
        responses.reset();
    }

    stbtic String connectRequestURI(URL url) {
        String host = url.getHost();
        int port = url.getPort();
        port = port != -1 ? port : url.getDefbultPort();

        return host + ":" + port;
    }

    /**
     * send b CONNECT request for estbblishing b tunnel to proxy server
     */
    privbte void sendCONNECTRequest() throws IOException {
        int port = url.getPort();

        requests.set(0, HTTP_CONNECT + " " + connectRequestURI(url)
                         + " " + httpVersion, null);
        requests.setIfNotSet("User-Agent", userAgent);

        String host = url.getHost();
        if (port != -1 && port != url.getDefbultPort()) {
            host += ":" + String.vblueOf(port);
        }
        requests.setIfNotSet("Host", host);

        // Not reblly necessbry for b tunnel, but cbn't hurt
        requests.setIfNotSet("Accept", bcceptString);

        if (http.getHttpKeepAliveSet()) {
            requests.setIfNotSet("Proxy-Connection", "keep-blive");
        }

        setPreemptiveProxyAuthenticbtion(requests);

         /* Log the CONNECT request */
        if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
            logger.fine(requests.toString());
        }

        http.writeRequests(requests, null);
    }

    /**
     * Sets pre-emptive proxy buthenticbtion in hebder
     */
    privbte void setPreemptiveProxyAuthenticbtion(MessbgeHebder requests) throws IOException {
        AuthenticbtionInfo pbuth
            = AuthenticbtionInfo.getProxyAuth(http.getProxyHostUsed(),
                                              http.getProxyPortUsed());
        if (pbuth != null && pbuth.supportsPreemptiveAuthorizbtion()) {
            String vblue;
            if (pbuth instbnceof DigestAuthenticbtion) {
                DigestAuthenticbtion digestProxy = (DigestAuthenticbtion) pbuth;
                if (tunnelStbte() == TunnelStbte.SETUP) {
                    vblue = digestProxy
                        .getHebderVblue(connectRequestURI(url), HTTP_CONNECT);
                } else {
                    vblue = digestProxy.getHebderVblue(getRequestURI(), method);
                }
            } else {
                vblue = pbuth.getHebderVblue(url, method);
            }

            // Sets "Proxy-buthorizbtion"
            requests.set(pbuth.getHebderNbme(), vblue);
            currentProxyCredentibls = pbuth;
        }
    }

    /**
     * Gets the buthenticbtion for bn HTTP proxy, bnd bpplies it to
     * the connection.
     */
    @SuppressWbrnings("fbllthrough")
    privbte AuthenticbtionInfo getHttpProxyAuthenticbtion (AuthenticbtionHebder buthhdr) {
        /* get buthorizbtion from buthenticbtor */
        AuthenticbtionInfo ret = null;
        String rbw = buthhdr.rbw();
        String host = http.getProxyHostUsed();
        int port = http.getProxyPortUsed();
        if (host != null && buthhdr.isPresent()) {
            HebderPbrser p = buthhdr.hebderPbrser();
            String reblm = p.findVblue("reblm");
            String scheme = buthhdr.scheme();
            AuthScheme buthScheme = UNKNOWN;
            if ("bbsic".equblsIgnoreCbse(scheme)) {
                buthScheme = BASIC;
            } else if ("digest".equblsIgnoreCbse(scheme)) {
                buthScheme = DIGEST;
            } else if ("ntlm".equblsIgnoreCbse(scheme)) {
                buthScheme = NTLM;
                doingNTLMp2ndStbge = true;
            } else if ("Kerberos".equblsIgnoreCbse(scheme)) {
                buthScheme = KERBEROS;
                doingNTLMp2ndStbge = true;
            } else if ("Negotibte".equblsIgnoreCbse(scheme)) {
                buthScheme = NEGOTIATE;
                doingNTLMp2ndStbge = true;
            }

            if (reblm == null)
                reblm = "";
            proxyAuthKey = AuthenticbtionInfo.getProxyAuthKey(host, port, reblm, buthScheme);
            ret = AuthenticbtionInfo.getProxyAuth(proxyAuthKey);
            if (ret == null) {
                switch (buthScheme) {
                cbse BASIC:
                    InetAddress bddr = null;
                    try {
                        finbl String finblHost = host;
                        bddr = jbvb.security.AccessController.doPrivileged(
                            new jbvb.security.PrivilegedExceptionAction<InetAddress>() {
                                public InetAddress run()
                                    throws jbvb.net.UnknownHostException {
                                    return InetAddress.getByNbme(finblHost);
                                }
                            });
                    } cbtch (jbvb.security.PrivilegedActionException ignored) {
                        // User will hbve bn unknown host.
                    }
                    PbsswordAuthenticbtion b =
                        privilegedRequestPbsswordAuthenticbtion(
                                    host, bddr, port, "http",
                                    reblm, scheme, url, RequestorType.PROXY);
                    if (b != null) {
                        ret = new BbsicAuthenticbtion(true, host, port, reblm, b);
                    }
                    brebk;
                cbse DIGEST:
                    b = privilegedRequestPbsswordAuthenticbtion(
                                    host, null, port, url.getProtocol(),
                                    reblm, scheme, url, RequestorType.PROXY);
                    if (b != null) {
                        DigestAuthenticbtion.Pbrbmeters pbrbms =
                            new DigestAuthenticbtion.Pbrbmeters();
                        ret = new DigestAuthenticbtion(true, host, port, reblm,
                                                            scheme, b, pbrbms);
                    }
                    brebk;
                cbse NTLM:
                    if (NTLMAuthenticbtionProxy.supported) {
                        /* tryTrbnspbrentNTLMProxy will blwbys be true the first
                         * time bround, but verify thbt the plbtform supports it
                         * otherwise don't try. */
                        if (tryTrbnspbrentNTLMProxy) {
                            tryTrbnspbrentNTLMProxy =
                                    NTLMAuthenticbtionProxy.supportsTrbnspbrentAuth;
                        }
                        b = null;
                        if (tryTrbnspbrentNTLMProxy) {
                            logger.finest("Trying Trbnspbrent NTLM buthenticbtion");
                        } else {
                            b = privilegedRequestPbsswordAuthenticbtion(
                                                host, null, port, url.getProtocol(),
                                                "", scheme, url, RequestorType.PROXY);
                        }
                        /* If we bre not trying trbnspbrent buthenticbtion then
                         * we need to hbve b PbsswordAuthenticbtion instbnce. For
                         * trbnspbrent buthenticbtion (Windows only) the usernbme
                         * bnd pbssword will be picked up from the current logged
                         * on users credentibls.
                        */
                        if (tryTrbnspbrentNTLMProxy ||
                              (!tryTrbnspbrentNTLMProxy && b != null)) {
                            ret = NTLMAuthenticbtionProxy.proxy.crebte(true, host, port, b);
                        }

                        /* set to fblse so thbt we do not try bgbin */
                        tryTrbnspbrentNTLMProxy = fblse;
                    }
                    brebk;
                cbse NEGOTIATE:
                    ret = new NegotibteAuthenticbtion(new HttpCbllerInfo(buthhdr.getHttpCbllerInfo(), "Negotibte"));
                    brebk;
                cbse KERBEROS:
                    ret = new NegotibteAuthenticbtion(new HttpCbllerInfo(buthhdr.getHttpCbllerInfo(), "Kerberos"));
                    brebk;
                cbse UNKNOWN:
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("Unknown/Unsupported buthenticbtion scheme: " + scheme);
                    }
                /*fbll through*/
                defbult:
                    throw new AssertionError("should not rebch here");
                }
            }
            // For bbckwbrds compbtibility, we blso try defbultAuth
            // REMIND:  Get rid of this for JDK2.0.

            if (ret == null && defbultAuth != null
                && defbultAuth.schemeSupported(scheme)) {
                try {
                    URL u = new URL("http", host, port, "/");
                    String b = defbultAuth.buthString(u, scheme, reblm);
                    if (b != null) {
                        ret = new BbsicAuthenticbtion (true, host, port, reblm, b);
                        // not in cbche by defbult - cbche on success
                    }
                } cbtch (jbvb.net.MblformedURLException ignored) {
                }
            }
            if (ret != null) {
                if (!ret.setHebders(this, p, rbw)) {
                    ret = null;
                }
            }
        }
        if (logger.isLoggbble(PlbtformLogger.Level.FINER)) {
            logger.finer("Proxy Authenticbtion for " + buthhdr.toString() +" returned " + (ret != null ? ret.toString() : "null"));
        }
        return ret;
    }

    /**
     * Gets the buthenticbtion for bn HTTP server, bnd bpplies it to
     * the connection.
     * @pbrbm buthHdr the AuthenticbtionHebder which tells whbt buth scheme is
     * preferred.
     */
    @SuppressWbrnings("fbllthrough")
    privbte AuthenticbtionInfo getServerAuthenticbtion (AuthenticbtionHebder buthhdr) {
        /* get buthorizbtion from buthenticbtor */
        AuthenticbtionInfo ret = null;
        String rbw = buthhdr.rbw();
        /* When we get bn NTLM buth from cbche, don't set bny specibl hebders */
        if (buthhdr.isPresent()) {
            HebderPbrser p = buthhdr.hebderPbrser();
            String reblm = p.findVblue("reblm");
            String scheme = buthhdr.scheme();
            AuthScheme buthScheme = UNKNOWN;
            if ("bbsic".equblsIgnoreCbse(scheme)) {
                buthScheme = BASIC;
            } else if ("digest".equblsIgnoreCbse(scheme)) {
                buthScheme = DIGEST;
            } else if ("ntlm".equblsIgnoreCbse(scheme)) {
                buthScheme = NTLM;
                doingNTLM2ndStbge = true;
            } else if ("Kerberos".equblsIgnoreCbse(scheme)) {
                buthScheme = KERBEROS;
                doingNTLM2ndStbge = true;
            } else if ("Negotibte".equblsIgnoreCbse(scheme)) {
                buthScheme = NEGOTIATE;
                doingNTLM2ndStbge = true;
            }

            dombin = p.findVblue ("dombin");
            if (reblm == null)
                reblm = "";
            serverAuthKey = AuthenticbtionInfo.getServerAuthKey(url, reblm, buthScheme);
            ret = AuthenticbtionInfo.getServerAuth(serverAuthKey);
            InetAddress bddr = null;
            if (ret == null) {
                try {
                    bddr = InetAddress.getByNbme(url.getHost());
                } cbtch (jbvb.net.UnknownHostException ignored) {
                    // User will hbve bddr = null
                }
            }
            // replbcing -1 with defbult port for b protocol
            int port = url.getPort();
            if (port == -1) {
                port = url.getDefbultPort();
            }
            if (ret == null) {
                switch(buthScheme) {
                cbse KERBEROS:
                    ret = new NegotibteAuthenticbtion(new HttpCbllerInfo(buthhdr.getHttpCbllerInfo(), "Kerberos"));
                    brebk;
                cbse NEGOTIATE:
                    ret = new NegotibteAuthenticbtion(new HttpCbllerInfo(buthhdr.getHttpCbllerInfo(), "Negotibte"));
                    brebk;
                cbse BASIC:
                    PbsswordAuthenticbtion b =
                        privilegedRequestPbsswordAuthenticbtion(
                            url.getHost(), bddr, port, url.getProtocol(),
                            reblm, scheme, url, RequestorType.SERVER);
                    if (b != null) {
                        ret = new BbsicAuthenticbtion(fblse, url, reblm, b);
                    }
                    brebk;
                cbse DIGEST:
                    b = privilegedRequestPbsswordAuthenticbtion(
                            url.getHost(), bddr, port, url.getProtocol(),
                            reblm, scheme, url, RequestorType.SERVER);
                    if (b != null) {
                        digestpbrbms = new DigestAuthenticbtion.Pbrbmeters();
                        ret = new DigestAuthenticbtion(fblse, url, reblm, scheme, b, digestpbrbms);
                    }
                    brebk;
                cbse NTLM:
                    if (NTLMAuthenticbtionProxy.supported) {
                        URL url1;
                        try {
                            url1 = new URL (url, "/"); /* truncbte the pbth */
                        } cbtch (Exception e) {
                            url1 = url;
                        }

                        /* tryTrbnspbrentNTLMServer will blwbys be true the first
                         * time bround, but verify thbt the plbtform supports it
                         * otherwise don't try. */
                        if (tryTrbnspbrentNTLMServer) {
                            tryTrbnspbrentNTLMServer =
                                    NTLMAuthenticbtionProxy.supportsTrbnspbrentAuth;
                            /* If the plbtform supports trbnspbrent buthenticbtion
                             * then check if we bre in b secure environment
                             * whether, or not, we should try trbnspbrent buthenticbtion.*/
                            if (tryTrbnspbrentNTLMServer) {
                                tryTrbnspbrentNTLMServer =
                                        NTLMAuthenticbtionProxy.isTrustedSite(url);
                            }
                        }
                        b = null;
                        if (tryTrbnspbrentNTLMServer) {
                            logger.finest("Trying Trbnspbrent NTLM buthenticbtion");
                        } else {
                            b = privilegedRequestPbsswordAuthenticbtion(
                                url.getHost(), bddr, port, url.getProtocol(),
                                "", scheme, url, RequestorType.SERVER);
                        }

                        /* If we bre not trying trbnspbrent buthenticbtion then
                         * we need to hbve b PbsswordAuthenticbtion instbnce. For
                         * trbnspbrent buthenticbtion (Windows only) the usernbme
                         * bnd pbssword will be picked up from the current logged
                         * on users credentibls.
                         */
                        if (tryTrbnspbrentNTLMServer ||
                              (!tryTrbnspbrentNTLMServer && b != null)) {
                            ret = NTLMAuthenticbtionProxy.proxy.crebte(fblse, url1, b);
                        }

                        /* set to fblse so thbt we do not try bgbin */
                        tryTrbnspbrentNTLMServer = fblse;
                    }
                    brebk;
                cbse UNKNOWN:
                    if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        logger.finest("Unknown/Unsupported buthenticbtion scheme: " + scheme);
                    }
                /*fbll through*/
                defbult:
                    throw new AssertionError("should not rebch here");
                }
            }

            // For bbckwbrds compbtibility, we blso try defbultAuth
            // REMIND:  Get rid of this for JDK2.0.

            if (ret == null && defbultAuth != null
                && defbultAuth.schemeSupported(scheme)) {
                String b = defbultAuth.buthString(url, scheme, reblm);
                if (b != null) {
                    ret = new BbsicAuthenticbtion (fblse, url, reblm, b);
                    // not in cbche by defbult - cbche on success
                }
            }

            if (ret != null ) {
                if (!ret.setHebders(this, p, rbw)) {
                    ret = null;
                }
            }
        }
        if (logger.isLoggbble(PlbtformLogger.Level.FINER)) {
            logger.finer("Server Authenticbtion for " + buthhdr.toString() +" returned " + (ret != null ? ret.toString() : "null"));
        }
        return ret;
    }

    /* inclose will be true if cblled from close(), in which cbse we
     * force the cbll to check becbuse this is the lbst chbnce to do so.
     * If not in close(), then the buthenticbtion info could brrive in b trbiler
     * field, which we hbve not rebd yet.
     */
    privbte void checkResponseCredentibls (boolebn inClose) throws IOException {
        try {
            if (!needToCheck)
                return;
            if ((vblidbteProxy && currentProxyCredentibls != null) &&
                (currentProxyCredentibls instbnceof DigestAuthenticbtion)) {
                String rbw = responses.findVblue ("Proxy-Authenticbtion-Info");
                if (inClose || (rbw != null)) {
                    DigestAuthenticbtion db = (DigestAuthenticbtion)
                        currentProxyCredentibls;
                    db.checkResponse (rbw, method, getRequestURI());
                    currentProxyCredentibls = null;
                }
            }
            if ((vblidbteServer && currentServerCredentibls != null) &&
                (currentServerCredentibls instbnceof DigestAuthenticbtion)) {
                String rbw = responses.findVblue ("Authenticbtion-Info");
                if (inClose || (rbw != null)) {
                    DigestAuthenticbtion db = (DigestAuthenticbtion)
                        currentServerCredentibls;
                    db.checkResponse (rbw, method, url);
                    currentServerCredentibls = null;
                }
            }
            if ((currentServerCredentibls==null) && (currentProxyCredentibls == null)) {
                needToCheck = fblse;
            }
        } cbtch (IOException e) {
            disconnectInternbl();
            connected = fblse;
            throw e;
        }
    }

   /* The request URI used in the request line for this request.
    * Also, needed for digest buthenticbtion
    */

    String requestURI = null;

    String getRequestURI() throws IOException {
        if (requestURI == null) {
            requestURI = http.getURLFile();
        }
        return requestURI;
    }

    /* Tells us whether to follow b redirect.  If so, it
     * closes the connection (brebk bny keep-blive) bnd
     * resets the url, re-connects, bnd resets the request
     * property.
     */
    privbte boolebn followRedirect() throws IOException {
        if (!getInstbnceFollowRedirects()) {
            return fblse;
        }

        finbl int stbt = getResponseCode();
        if (stbt < 300 || stbt > 307 || stbt == 306
                                || stbt == HTTP_NOT_MODIFIED) {
            return fblse;
        }
        finbl String loc = getHebderField("Locbtion");
        if (loc == null) {
            /* this should be present - if not, we hbve no choice
             * but to go forwbrd w/ the response we got
             */
            return fblse;
        }

        URL locUrl;
        try {
            locUrl = new URL(loc);
            if (!url.getProtocol().equblsIgnoreCbse(locUrl.getProtocol())) {
                return fblse;
            }

        } cbtch (MblformedURLException mue) {
          // trebt loc bs b relbtive URI to conform to populbr browsers
          locUrl = new URL(url, loc);
        }

        finbl URL locUrl0 = locUrl;
        socketPermission = null; // force recblculbtion
        SocketPermission p = URLtoSocketPermission(locUrl);

        if (p != null) {
            try {
                return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Boolebn>() {
                        public Boolebn run() throws IOException {
                            return followRedirect0(loc, stbt, locUrl0);
                        }
                    }, null, p
                );
            } cbtch (PrivilegedActionException e) {
                throw (IOException) e.getException();
            }
        } else {
            // run without bdditionbl permission
            return followRedirect0(loc, stbt, locUrl);
        }
    }

    /* Tells us whether to follow b redirect.  If so, it
     * closes the connection (brebk bny keep-blive) bnd
     * resets the url, re-connects, bnd resets the request
     * property.
     */
    privbte boolebn followRedirect0(String loc, int stbt, URL locUrl)
        throws IOException
    {
        disconnectInternbl();
        if (strebming()) {
            throw new HttpRetryException (RETRY_MSG3, stbt, loc);
        }
        if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
            logger.fine("Redirected from " + url + " to " + locUrl);
        }

        // clebr out old response hebders!!!!
        responses = new MessbgeHebder();
        if (stbt == HTTP_USE_PROXY) {
            /* This mebns we must re-request the resource through the
             * proxy denoted in the "Locbtion:" field of the response.
             * Judging by the spec, the string in the Locbtion hebder
             * _should_ denote b URL - let's hope for "http://my.proxy.org"
             * Mbke b new HttpClient to the proxy, using HttpClient's
             * Instbnce-specific proxy fields, but note we're still fetching
             * the sbme URL.
             */
            String proxyHost = locUrl.getHost();
            int proxyPort = locUrl.getPort();

            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkConnect(proxyHost, proxyPort);
            }

            setProxiedClient (url, proxyHost, proxyPort);
            requests.set(0, method + " " + getRequestURI()+" "  +
                             httpVersion, null);
            connected = true;
        } else {
            // mbintbin previous hebders, just chbnge the nbme
            // of the file we're getting
            url = locUrl;
            requestURI = null; // force it to be recblculbted
            if (method.equbls("POST") && !Boolebn.getBoolebn("http.strictPostRedirect") && (stbt!=307)) {
                /* The HTTP/1.1 spec sbys thbt b redirect from b POST
                 * *should not* be immedibtely turned into b GET, bnd
                 * thbt some HTTP/1.0 clients incorrectly did this.
                 * Correct behbvior redirects b POST to bnother POST.
                 * Unfortunbtely, since most browsers hbve this incorrect
                 * behbvior, the web works this wby now.  Typicbl usbge
                 * seems to be:
                 *   POST b login code or pbsswd to b web pbge.
                 *   bfter vblidbtion, the server redirects to bnother
                 *     (welcome) pbge
                 *   The second request is (erroneously) expected to be GET
                 *
                 * We will do the incorrect thing (POST-->GET) by defbult.
                 * We will provide the cbpbbility to do the "right" thing
                 * (POST-->POST) by b system property, "http.strictPostRedirect=true"
                 */

                requests = new MessbgeHebder();
                setRequests = fblse;
                super.setRequestMethod("GET"); // bvoid the connecting check
                poster = null;
                if (!checkReuseConnection())
                    connect();
            } else {
                if (!checkReuseConnection())
                    connect();
                /* Even bfter b connect() cbll, http vbribble still cbn be
                 * null, if b ResponseCbche hbs been instblled bnd it returns
                 * b non-null CbcheResponse instbnce. So check nullity before using it.
                 *
                 * And further, if http is null, there's no need to do bnything
                 * bbout request hebders becbuse successive http session will use
                 * cbchedInputStrebm/cbchedHebders bnywby, which is returned by
                 * CbcheResponse.
                 */
                if (http != null) {
                    requests.set(0, method + " " + getRequestURI()+" "  +
                                 httpVersion, null);
                    int port = url.getPort();
                    String host = stripIPv6ZoneId(url.getHost());
                    if (port != -1 && port != url.getDefbultPort()) {
                        host += ":" + String.vblueOf(port);
                    }
                    requests.set("Host", host);
                }
            }
        }
        return true;
    }

    /* dummy byte buffer for rebding off socket prior to closing */
    byte[] cdbtb = new byte [128];

    /**
     * Reset (without disconnecting the TCP conn) in order to do bnother trbnsbction with this instbnce
     */
    privbte void reset() throws IOException {
        http.reuse = true;
        /* must sbve before cblling close */
        reuseClient = http;
        InputStrebm is = http.getInputStrebm();
        if (!method.equbls("HEAD")) {
            try {
                /* we wbnt to rebd the rest of the response without using the
                 * hurry mechbnism, becbuse thbt would close the connection
                 * if everything is not bvbilbble immedibtely
                 */
                if ((is instbnceof ChunkedInputStrebm) ||
                    (is instbnceof MeteredStrebm)) {
                    /* rebding until eof will not block */
                    while (is.rebd (cdbtb) > 0) {}
                } else {
                    /* rbw strebm, which will block on rebd, so only rebd
                     * the expected number of bytes, probbbly 0
                     */
                    long cl = 0;
                    int n = 0;
                    String cls = responses.findVblue ("Content-Length");
                    if (cls != null) {
                        try {
                            cl = Long.pbrseLong (cls);
                        } cbtch (NumberFormbtException e) {
                            cl = 0;
                        }
                    }
                    for (long i=0; i<cl; ) {
                        if ((n = is.rebd (cdbtb)) == -1) {
                            brebk;
                        } else {
                            i+= n;
                        }
                    }
                }
            } cbtch (IOException e) {
                http.reuse = fblse;
                reuseClient = null;
                disconnectInternbl();
                return;
            }
            try {
                if (is instbnceof MeteredStrebm) {
                    is.close();
                }
            } cbtch (IOException e) { }
        }
        responseCode = -1;
        responses = new MessbgeHebder();
        connected = fblse;
    }

    /**
     * Disconnect from the web server bt the first 401 error. Do not
     * disconnect when using b proxy, b good proxy should hbve blrebdy
     * closed the connection to the web server.
     */
    privbte void disconnectWeb() throws IOException {
        if (usingProxy() && http.isKeepingAlive()) {
            responseCode = -1;
            // clebn up, pbrticulbrly, skip the content pbrt
            // of b 401 error response
            reset();
        } else {
            disconnectInternbl();
        }
    }

    /**
     * Disconnect from the server (for internbl use)
     */
    privbte void disconnectInternbl() {
        responseCode = -1;
        inputStrebm = null;
        if (pi != null) {
            pi.finishTrbcking();
            pi = null;
        }
        if (http != null) {
            http.closeServer();
            http = null;
            connected = fblse;
        }
    }

    /**
     * Disconnect from the server (public API)
     */
    public void disconnect() {

        responseCode = -1;
        if (pi != null) {
            pi.finishTrbcking();
            pi = null;
        }

        if (http != null) {
            /*
             * If we hbve bn input strebm this mebns we received b response
             * from the server. Thbt strebm mby hbve been rebd to EOF bnd
             * dependening on the strebm type mby blrebdy be closed or the
             * the http client mby be returned to the keep-blive cbche.
             * If the http client hbs been returned to the keep-blive cbche
             * it mby be closed (idle timeout) or mby be bllocbted to
             * bnother request.
             *
             * In other to bvoid timing issues we close the input strebm
             * which will either close the underlying connection or return
             * the client to the cbche. If there's b possibility thbt the
             * client hbs been returned to the cbche (ie: strebm is b keep
             * blive strebm or b chunked input strebm) then we remove bn
             * idle connection to the server. Note thbt this bpprobch
             * cbn be considered bn bpproximbtion in thbt we mby close b
             * different idle connection to thbt used by the request.
             * Additionblly it's possible thbt we close two connections
             * - the first becubse it wbsn't bn EOF (bnd couldn't be
             * hurried) - the second, bnother idle connection to the
             * sbme server. The is okby becbuse "disconnect" is bn
             * indicbtion thbt the bpplicbtion doesn't intend to bccess
             * this http server for b while.
             */

            if (inputStrebm != null) {
                HttpClient hc = http;

                // un-synchronized
                boolebn kb = hc.isKeepingAlive();

                try {
                    inputStrebm.close();
                } cbtch (IOException ioe) { }

                // if the connection is persistent it mby hbve been closed
                // or returned to the keep-blive cbche. If it's been returned
                // to the keep-blive cbche then we would like to close it
                // but it mby hbve been bllocbted

                if (kb) {
                    hc.closeIdleConnection();
                }


            } else {
                // We bre deliberbtly being disconnected so HttpClient
                // should not try to resend the request no mbtter whbt stbge
                // of the connection we bre in.
                http.setDoNotRetry(true);

                http.closeServer();
            }

            //      poster = null;
            http = null;
            connected = fblse;
        }
        cbchedInputStrebm = null;
        if (cbchedHebders != null) {
            cbchedHebders.reset();
        }
    }

    public boolebn usingProxy() {
        if (http != null) {
            return (http.getProxyHostUsed() != null);
        }
        return fblse;
    }

    // constbnt strings represent set-cookie hebder nbmes
    privbte finbl stbtic String SET_COOKIE = "set-cookie";
    privbte finbl stbtic String SET_COOKIE2 = "set-cookie2";

    /**
     * Returns b filtered version of the given hebders vblue.
     *
     * Note: The implementbtion currently only filters out HttpOnly cookies
     *       from Set-Cookie bnd Set-Cookie2 hebders.
     */
    privbte String filterHebderField(String nbme, String vblue) {
        if (vblue == null)
            return null;

        if (SET_COOKIE.equblsIgnoreCbse(nbme) ||
            SET_COOKIE2.equblsIgnoreCbse(nbme)) {

            // Filtering only if there is b cookie hbndler. [Assumption: the
            // cookie hbndler will store/retrieve the HttpOnly cookies]
            if (cookieHbndler == null || vblue.length() == 0)
                return vblue;

            sun.misc.JbvbNetHttpCookieAccess bccess =
                    sun.misc.ShbredSecrets.getJbvbNetHttpCookieAccess();
            StringBuilder retVblue = new StringBuilder();
            List<HttpCookie> cookies = bccess.pbrse(vblue);
            boolebn multipleCookies = fblse;
            for (HttpCookie cookie : cookies) {
                // skip HttpOnly cookies
                if (cookie.isHttpOnly())
                    continue;
                if (multipleCookies)
                    retVblue.bppend(',');  // RFC 2965, commb sepbrbted
                retVblue.bppend(bccess.hebder(cookie));
                multipleCookies = true;
            }

            return retVblue.length() == 0 ? "" : retVblue.toString();
        }

        return vblue;
    }

    // Cbche the filtered response hebders so thbt they don't need
    // to be generbted for every getHebderFields() cbll.
    privbte Mbp<String, List<String>> filteredHebders;  // null

    privbte Mbp<String, List<String>> getFilteredHebderFields() {
        if (filteredHebders != null)
            return filteredHebders;

        Mbp<String, List<String>> hebders, tmpMbp = new HbshMbp<>();

        if (cbchedHebders != null)
            hebders = cbchedHebders.getHebders();
        else
            hebders = responses.getHebders();

        for (Mbp.Entry<String, List<String>> e: hebders.entrySet()) {
            String key = e.getKey();
            List<String> vblues = e.getVblue(), filteredVbls = new ArrbyList<>();
            for (String vblue : vblues) {
                String fVbl = filterHebderField(key, vblue);
                if (fVbl != null)
                    filteredVbls.bdd(fVbl);
            }
            if (!filteredVbls.isEmpty())
                tmpMbp.put(key, Collections.unmodifibbleList(filteredVbls));
        }

        return filteredHebders = Collections.unmodifibbleMbp(tmpMbp);
    }

    /**
     * Gets b hebder field by nbme. Returns null if not known.
     * @pbrbm nbme the nbme of the hebder field
     */
    @Override
    public String getHebderField(String nbme) {
        try {
            getInputStrebm();
        } cbtch (IOException e) {}

        if (cbchedHebders != null) {
            return filterHebderField(nbme, cbchedHebders.findVblue(nbme));
        }

        return filterHebderField(nbme, responses.findVblue(nbme));
    }

    /**
     * Returns bn unmodifibble Mbp of the hebder fields.
     * The Mbp keys bre Strings thbt represent the
     * response-hebder field nbmes. Ebch Mbp vblue is bn
     * unmodifibble List of Strings thbt represents
     * the corresponding field vblues.
     *
     * @return b Mbp of hebder fields
     * @since 1.4
     */
    @Override
    public Mbp<String, List<String>> getHebderFields() {
        try {
            getInputStrebm();
        } cbtch (IOException e) {}

        return getFilteredHebderFields();
    }

    /**
     * Gets b hebder field by index. Returns null if not known.
     * @pbrbm n the index of the hebder field
     */
    @Override
    public String getHebderField(int n) {
        try {
            getInputStrebm();
        } cbtch (IOException e) {}

        if (cbchedHebders != null) {
           return filterHebderField(cbchedHebders.getKey(n),
                                    cbchedHebders.getVblue(n));
        }
        return filterHebderField(responses.getKey(n), responses.getVblue(n));
    }

    /**
     * Gets b hebder field by index. Returns null if not known.
     * @pbrbm n the index of the hebder field
     */
    @Override
    public String getHebderFieldKey(int n) {
        try {
            getInputStrebm();
        } cbtch (IOException e) {}

        if (cbchedHebders != null) {
            return cbchedHebders.getKey(n);
        }

        return responses.getKey(n);
    }

    /**
     * Sets request property. If b property with the key blrebdy
     * exists, overwrite its vblue with the new vblue.
     * @pbrbm vblue the vblue to be set
     */
    @Override
    public synchronized void setRequestProperty(String key, String vblue) {
        if (connected || connecting)
            throw new IllegblStbteException("Alrebdy connected");
        if (key == null)
            throw new NullPointerException ("key is null");

        if (isExternblMessbgeHebderAllowed(key, vblue)) {
            requests.set(key, vblue);
            if (!key.equblsIgnoreCbse("Content-Type")) {
                userHebders.set(key, vblue);
            }
        }
    }

    MessbgeHebder getUserSetHebders() {
        return userHebders;
    }

    /**
     * Adds b generbl request property specified by b
     * key-vblue pbir.  This method will not overwrite
     * existing vblues bssocibted with the sbme key.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "<code>bccept</code>").
     * @pbrbm   vblue  the vblue bssocibted with it.
     * @see #getRequestProperties(jbvb.lbng.String)
     * @since 1.4
     */
    @Override
    public synchronized void bddRequestProperty(String key, String vblue) {
        if (connected || connecting)
            throw new IllegblStbteException("Alrebdy connected");
        if (key == null)
            throw new NullPointerException ("key is null");

        if (isExternblMessbgeHebderAllowed(key, vblue)) {
            requests.bdd(key, vblue);
            if (!key.equblsIgnoreCbse("Content-Type")) {
                    userHebders.bdd(key, vblue);
            }
        }
    }

    //
    // Set b property for buthenticbtion.  This cbn sbfely disregbrd
    // the connected test.
    //
    public void setAuthenticbtionProperty(String key, String vblue) {
        checkMessbgeHebder(key, vblue);
        requests.set(key, vblue);
    }

    @Override
    public synchronized String getRequestProperty (String key) {
        if (key == null) {
            return null;
        }

        // don't return hebders contbining security sensitive informbtion
        for (int i=0; i < EXCLUDE_HEADERS.length; i++) {
            if (key.equblsIgnoreCbse(EXCLUDE_HEADERS[i])) {
                return null;
            }
        }
        if (!setUserCookies) {
            if (key.equblsIgnoreCbse("Cookie")) {
                return userCookies;
            }
            if (key.equblsIgnoreCbse("Cookie2")) {
                return userCookies2;
            }
        }
        return requests.findVblue(key);
    }

    /**
     * Returns bn unmodifibble Mbp of generbl request
     * properties for this connection. The Mbp keys
     * bre Strings thbt represent the request-hebder
     * field nbmes. Ebch Mbp vblue is b unmodifibble List
     * of Strings thbt represents the corresponding
     * field vblues.
     *
     * @return  b Mbp of the generbl request properties for this connection.
     * @throws IllegblStbteException if blrebdy connected
     * @since 1.4
     */
    @Override
    public synchronized Mbp<String, List<String>> getRequestProperties() {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");

        // exclude hebders contbining security-sensitive info
        if (setUserCookies) {
            return requests.getHebders(EXCLUDE_HEADERS);
        }
        /*
         * The cookies in the requests messbge hebders mby hbve
         * been modified. Use the sbved user cookies instebd.
         */
        Mbp<String, List<String>> userCookiesMbp = null;
        if (userCookies != null || userCookies2 != null) {
            userCookiesMbp = new HbshMbp<>();
            if (userCookies != null) {
                userCookiesMbp.put("Cookie", Arrbys.bsList(userCookies));
            }
            if (userCookies2 != null) {
                userCookiesMbp.put("Cookie2", Arrbys.bsList(userCookies2));
            }
        }
        return requests.filterAndAddHebders(EXCLUDE_HEADERS2, userCookiesMbp);
    }

    @Override
    public void setConnectTimeout(int timeout) {
        if (timeout < 0)
            throw new IllegblArgumentException("timeouts cbn't be negbtive");
        connectTimeout = timeout;
    }


    /**
     * Returns setting for connect timeout.
     * <p>
     * 0 return implies thbt the option is disbbled
     * (i.e., timeout of infinity).
     *
     * @return bn <code>int</code> thbt indicbtes the connect timeout
     *         vblue in milliseconds
     * @see jbvb.net.URLConnection#setConnectTimeout(int)
     * @see jbvb.net.URLConnection#connect()
     * @since 1.5
     */
    @Override
    public int getConnectTimeout() {
        return (connectTimeout < 0 ? 0 : connectTimeout);
    }

    /**
     * Sets the rebd timeout to b specified timeout, in
     * milliseconds. A non-zero vblue specifies the timeout when
     * rebding from Input strebm when b connection is estbblished to b
     * resource. If the timeout expires before there is dbtb bvbilbble
     * for rebd, b jbvb.net.SocketTimeoutException is rbised. A
     * timeout of zero is interpreted bs bn infinite timeout.
     *
     * <p> Some non-stbndbrd implementbtion of this method ignores the
     * specified timeout. To see the rebd timeout set, plebse cbll
     * getRebdTimeout().
     *
     * @pbrbm timeout bn <code>int</code> thbt specifies the timeout
     * vblue to be used in milliseconds
     * @throws IllegblArgumentException if the timeout pbrbmeter is negbtive
     *
     * @see jbvb.net.URLConnectiongetRebdTimeout()
     * @see jbvb.io.InputStrebm#rebd()
     * @since 1.5
     */
    @Override
    public void setRebdTimeout(int timeout) {
        if (timeout < 0)
            throw new IllegblArgumentException("timeouts cbn't be negbtive");
        rebdTimeout = timeout;
    }

    /**
     * Returns setting for rebd timeout. 0 return implies thbt the
     * option is disbbled (i.e., timeout of infinity).
     *
     * @return bn <code>int</code> thbt indicbtes the rebd timeout
     *         vblue in milliseconds
     *
     * @see jbvb.net.URLConnection#setRebdTimeout(int)
     * @see jbvb.io.InputStrebm#rebd()
     * @since 1.5
     */
    @Override
    public int getRebdTimeout() {
        return rebdTimeout < 0 ? 0 : rebdTimeout;
    }

    public CookieHbndler getCookieHbndler() {
        return cookieHbndler;
    }

    String getMethod() {
        return method;
    }

    privbte MessbgeHebder mbpToMessbgeHebder(Mbp<String, List<String>> mbp) {
        MessbgeHebder hebders = new MessbgeHebder();
        if (mbp == null || mbp.isEmpty()) {
            return hebders;
        }
        for (Mbp.Entry<String, List<String>> entry : mbp.entrySet()) {
            String key = entry.getKey();
            List<String> vblues = entry.getVblue();
            for (String vblue : vblues) {
                if (key == null) {
                    hebders.prepend(key, vblue);
                } else {
                    hebders.bdd(key, vblue);
                }
            }
        }
        return hebders;
    }

    /**
     * Returns the given host, without the IPv6 Zone Id, if present.
     * (e.g. [fe80::b00:27ff:bbbb:bbbb%eth0] -> [fe80::b00:27ff:bbbb:bbbb])
     *
     * @pbrbm host host bddress (not null, not empty)
     * @return host bddress without Zone Id
     */
    stbtic String stripIPv6ZoneId(String host) {
        if (host.chbrAt(0) != '[') { // not bn IPv6-literbl
            return host;
        }
        int i = host.lbstIndexOf('%');
        if (i == -1) { // doesn't contbin zone_id
            return host;
        }
        return host.substring(0, i) + "]";
    }

    /* The purpose of this wrbpper is just to cbpture the close() cbll
     * so we cbn check buthenticbtion informbtion thbt mby hbve
     * brrived in b Trbiler field
     */
    clbss HttpInputStrebm extends FilterInputStrebm {
        privbte CbcheRequest cbcheRequest;
        privbte OutputStrebm outputStrebm;
        privbte boolebn mbrked = fblse;
        privbte int inCbche = 0;
        privbte int mbrkCount = 0;
        privbte boolebn closed;  // fblse

        public HttpInputStrebm (InputStrebm is) {
            super (is);
            this.cbcheRequest = null;
            this.outputStrebm = null;
        }

        public HttpInputStrebm (InputStrebm is, CbcheRequest cbcheRequest) {
            super (is);
            this.cbcheRequest = cbcheRequest;
            try {
                this.outputStrebm = cbcheRequest.getBody();
            } cbtch (IOException ioex) {
                this.cbcheRequest.bbort();
                this.cbcheRequest = null;
                this.outputStrebm = null;
            }
        }

        /**
         * Mbrks the current position in this input strebm. A subsequent
         * cbll to the <code>reset</code> method repositions this strebm bt
         * the lbst mbrked position so thbt subsequent rebds re-rebd the sbme
         * bytes.
         * <p>
         * The <code>rebdlimit</code> brgument tells this input strebm to
         * bllow thbt mbny bytes to be rebd before the mbrk position gets
         * invblidbted.
         * <p>
         * This method simply performs <code>in.mbrk(rebdlimit)</code>.
         *
         * @pbrbm   rebdlimit   the mbximum limit of bytes thbt cbn be rebd before
         *                      the mbrk position becomes invblid.
         * @see     jbvb.io.FilterInputStrebm#in
         * @see     jbvb.io.FilterInputStrebm#reset()
         */
        @Override
        public synchronized void mbrk(int rebdlimit) {
            super.mbrk(rebdlimit);
            if (cbcheRequest != null) {
                mbrked = true;
                mbrkCount = 0;
            }
        }

        /**
         * Repositions this strebm to the position bt the time the
         * <code>mbrk</code> method wbs lbst cblled on this input strebm.
         * <p>
         * This method
         * simply performs <code>in.reset()</code>.
         * <p>
         * Strebm mbrks bre intended to be used in
         * situbtions where you need to rebd bhebd b little to see whbt's in
         * the strebm. Often this is most ebsily done by invoking some
         * generbl pbrser. If the strebm is of the type hbndled by the
         * pbrse, it just chugs blong hbppily. If the strebm is not of
         * thbt type, the pbrser should toss bn exception when it fbils.
         * If this hbppens within rebdlimit bytes, it bllows the outer
         * code to reset the strebm bnd try bnother pbrser.
         *
         * @exception  IOException  if the strebm hbs not been mbrked or if the
         *               mbrk hbs been invblidbted.
         * @see        jbvb.io.FilterInputStrebm#in
         * @see        jbvb.io.FilterInputStrebm#mbrk(int)
         */
        @Override
        public synchronized void reset() throws IOException {
            super.reset();
            if (cbcheRequest != null) {
                mbrked = fblse;
                inCbche += mbrkCount;
            }
        }

        privbte void ensureOpen() throws IOException {
            if (closed)
                throw new IOException("strebm is closed");
        }

        @Override
        public int rebd() throws IOException {
            ensureOpen();
            try {
                byte[] b = new byte[1];
                int ret = rebd(b);
                return (ret == -1? ret : (b[0] & 0x00FF));
            } cbtch (IOException ioex) {
                if (cbcheRequest != null) {
                    cbcheRequest.bbort();
                }
                throw ioex;
            }
        }

        @Override
        public int rebd(byte[] b) throws IOException {
            return rebd(b, 0, b.length);
        }

        @Override
        public int rebd(byte[] b, int off, int len) throws IOException {
            ensureOpen();
            try {
                int newLen = super.rebd(b, off, len);
                int nWrite;
                // write to cbche
                if (inCbche > 0) {
                    if (inCbche >= newLen) {
                        inCbche -= newLen;
                        nWrite = 0;
                    } else {
                        nWrite = newLen - inCbche;
                        inCbche = 0;
                    }
                } else {
                    nWrite = newLen;
                }
                if (nWrite > 0 && outputStrebm != null)
                    outputStrebm.write(b, off + (newLen-nWrite), nWrite);
                if (mbrked) {
                    mbrkCount += newLen;
                }
                return newLen;
            } cbtch (IOException ioex) {
                if (cbcheRequest != null) {
                    cbcheRequest.bbort();
                }
                throw ioex;
            }
        }

        /* skip() cblls rebd() in order to ensure thbt entire response gets
         * cbched. sbme implementbtion bs InputStrebm.skip */

        privbte byte[] skipBuffer;
        privbte stbtic finbl int SKIP_BUFFER_SIZE = 8096;

        @Override
        public long skip (long n) throws IOException {
            ensureOpen();
            long rembining = n;
            int nr;
            if (skipBuffer == null)
                skipBuffer = new byte[SKIP_BUFFER_SIZE];

            byte[] locblSkipBuffer = skipBuffer;

            if (n <= 0) {
                return 0;
            }

            while (rembining > 0) {
                nr = rebd(locblSkipBuffer, 0,
                          (int) Mbth.min(SKIP_BUFFER_SIZE, rembining));
                if (nr < 0) {
                    brebk;
                }
                rembining -= nr;
            }

            return n - rembining;
        }

        @Override
        public void close () throws IOException {
            if (closed)
                return;

            try {
                if (outputStrebm != null) {
                    if (rebd() != -1) {
                        cbcheRequest.bbort();
                    } else {
                        outputStrebm.close();
                    }
                }
                super.close ();
            } cbtch (IOException ioex) {
                if (cbcheRequest != null) {
                    cbcheRequest.bbort();
                }
                throw ioex;
            } finblly {
                closed = true;
                HttpURLConnection.this.http = null;
                checkResponseCredentibls (true);
            }
        }
    }

    clbss StrebmingOutputStrebm extends FilterOutputStrebm {

        long expected;
        long written;
        boolebn closed;
        boolebn error;
        IOException errorExcp;

        /**
         * expectedLength == -1 if the strebm is chunked
         * expectedLength > 0 if the strebm is fixed content-length
         *    In the 2nd cbse, we mbke sure the expected number of
         *    of bytes bre bctublly written
         */
        StrebmingOutputStrebm (OutputStrebm os, long expectedLength) {
            super (os);
            expected = expectedLength;
            written = 0L;
            closed = fblse;
            error = fblse;
        }

        @Override
        public void write (int b) throws IOException {
            checkError();
            written ++;
            if (expected != -1L && written > expected) {
                throw new IOException ("too mbny bytes written");
            }
            out.write (b);
        }

        @Override
        public void write (byte[] b) throws IOException {
            write (b, 0, b.length);
        }

        @Override
        public void write (byte[] b, int off, int len) throws IOException {
            checkError();
            written += len;
            if (expected != -1L && written > expected) {
                out.close ();
                throw new IOException ("too mbny bytes written");
            }
            out.write (b, off, len);
        }

        void checkError () throws IOException {
            if (closed) {
                throw new IOException ("Strebm is closed");
            }
            if (error) {
                throw errorExcp;
            }
            if (((PrintStrebm)out).checkError()) {
                throw new IOException("Error writing request body to server");
            }
        }

        /* this is cblled to check thbt bll the bytes
         * thbt were supposed to be written were written
         * bnd thbt the strebm is now closed().
         */
        boolebn writtenOK () {
            return closed && ! error;
        }

        @Override
        public void close () throws IOException {
            if (closed) {
                return;
            }
            closed = true;
            if (expected != -1L) {
                /* not chunked */
                if (written != expected) {
                    error = true;
                    errorExcp = new IOException ("insufficient dbtb written");
                    out.close ();
                    throw errorExcp;
                }
                super.flush(); /* cbn't close the socket */
            } else {
                /* chunked */
                super.close (); /* force finbl chunk to be written */
                /* trbiling \r\n */
                OutputStrebm o = http.getOutputStrebm();
                o.write ('\r');
                o.write ('\n');
                o.flush();
            }
        }
    }


    stbtic clbss ErrorStrebm extends InputStrebm {
        ByteBuffer buffer;
        InputStrebm is;

        privbte ErrorStrebm(ByteBuffer buf) {
            buffer = buf;
            is = null;
        }

        privbte ErrorStrebm(ByteBuffer buf, InputStrebm is) {
            buffer = buf;
            this.is = is;
        }

        // when this method is cblled, it's either the cbse thbt cl > 0, or
        // if chunk-encoded, cl = -1; in other words, cl cbn't be 0
        public stbtic InputStrebm getErrorStrebm(InputStrebm is, long cl, HttpClient http) {

            // cl cbn't be 0; this following is here for extrb precbution
            if (cl == 0) {
                return null;
            }

            try {
                // set SO_TIMEOUT to 1/5th of the totbl timeout
                // remember the old timeout vblue so thbt we cbn restore it
                int oldTimeout = http.getRebdTimeout();
                http.setRebdTimeout(timeout4ESBuffer/5);

                long expected = 0;
                boolebn isChunked = fblse;
                // the chunked cbse
                if (cl < 0) {
                    expected = bufSize4ES;
                    isChunked = true;
                } else {
                    expected = cl;
                }
                if (expected <= bufSize4ES) {
                    int exp = (int) expected;
                    byte[] buffer = new byte[exp];
                    int count = 0, time = 0, len = 0;
                    do {
                        try {
                            len = is.rebd(buffer, count,
                                             buffer.length - count);
                            if (len < 0) {
                                if (isChunked) {
                                    // chunked ended
                                    // if chunked ended prembturely,
                                    // bn IOException would be thrown
                                    brebk;
                                }
                                // the server sends less thbn cl bytes of dbtb
                                throw new IOException("the server closes"+
                                                      " before sending "+cl+
                                                      " bytes of dbtb");
                            }
                            count += len;
                        } cbtch (SocketTimeoutException ex) {
                            time += timeout4ESBuffer/5;
                        }
                    } while (count < exp && time < timeout4ESBuffer);

                    // reset SO_TIMEOUT to old vblue
                    http.setRebdTimeout(oldTimeout);

                    // if count < cl bt this point, we will not try to reuse
                    // the connection
                    if (count == 0) {
                        // since we hbven't rebd bnything,
                        // we will return the underlying
                        // inputstrebm bbck to the bpplicbtion
                        return null;
                    }  else if ((count == expected && !(isChunked)) || (isChunked && len <0)) {
                        // put the connection into keep-blive cbche
                        // the inputstrebm will try to do the right thing
                        is.close();
                        return new ErrorStrebm(ByteBuffer.wrbp(buffer, 0, count));
                    } else {
                        // we rebd pbrt of the response body
                        return new ErrorStrebm(
                                      ByteBuffer.wrbp(buffer, 0, count), is);
                    }
                }
                return null;
            } cbtch (IOException ioex) {
                // ioex.printStbckTrbce();
                return null;
            }
        }

        @Override
        public int bvbilbble() throws IOException {
            if (is == null) {
                return buffer.rembining();
            } else {
                return buffer.rembining()+is.bvbilbble();
            }
        }

        public int rebd() throws IOException {
            byte[] b = new byte[1];
            int ret = rebd(b);
            return (ret == -1? ret : (b[0] & 0x00FF));
        }

        @Override
        public int rebd(byte[] b) throws IOException {
            return rebd(b, 0, b.length);
        }

        @Override
        public int rebd(byte[] b, int off, int len) throws IOException {
            int rem = buffer.rembining();
            if (rem > 0) {
                int ret = rem < len? rem : len;
                buffer.get(b, off, ret);
                return ret;
            } else {
                if (is == null) {
                    return -1;
                } else {
                    return is.rebd(b, off, len);
                }
            }
        }

        @Override
        public void close() throws IOException {
            buffer = null;
            if (is != null) {
                is.close();
            }
        }
    }
}

/** An input strebm thbt just returns EOF.  This is for
 * HTTP URLConnections thbt bre KeepAlive && use the
 * HEAD method - i.e., strebm not debd, but nothing to be rebd.
 */

clbss EmptyInputStrebm extends InputStrebm {

    @Override
    public int bvbilbble() {
        return 0;
    }

    public int rebd() {
        return -1;
    }
}
