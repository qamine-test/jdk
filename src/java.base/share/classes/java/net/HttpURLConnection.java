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

pbckbge jbvb.net;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.security.Permission;
import jbvb.util.Dbte;

/**
 * A URLConnection with support for HTTP-specific febtures. See
 * <A HREF="http://www.w3.org/pub/WWW/Protocols/"> the spec </A> for
 * detbils.
 * <p>
 *
 * Ebch HttpURLConnection instbnce is used to mbke b single request
 * but the underlying network connection to the HTTP server mby be
 * trbnspbrently shbred by other instbnces. Cblling the close() methods
 * on the InputStrebm or OutputStrebm of bn HttpURLConnection
 * bfter b request mby free network resources bssocibted with this
 * instbnce but hbs no effect on bny shbred persistent connection.
 * Cblling the disconnect() method mby close the underlying socket
 * if b persistent connection is otherwise idle bt thbt time.
 *
 * <P>The HTTP protocol hbndler hbs b few settings thbt cbn be bccessed through
 * System Properties. This covers
 * <b href="doc-files/net-properties.html#Proxies">Proxy settings</b> bs well bs
 * <b href="doc-files/net-properties.html#MiscHTTP"> vbrious other settings</b>.
 * </P>
 * <p>
 * <b>Security permissions</b>
 * <p>
 * If b security mbnbger is instblled, bnd if b method is cblled which results in bn
 * bttempt to open b connection, the cbller must possess either:-
 * <ul><li>b "connect" {@link SocketPermission} to the host/port combinbtion of the
 * destinbtion URL or</li>
 * <li>b {@link URLPermission} thbt permits this request.</li>
 * </ul><p>
 * If butombtic redirection is enbbled, bnd this request is redirected to bnother
 * destinbtion, then the cbller must blso hbve permission to connect to the
 * redirected host/URL.
 *
 * @see     jbvb.net.HttpURLConnection#disconnect()
 * @since 1.1
 */
bbstrbct public clbss HttpURLConnection extends URLConnection {
    /* instbnce vbribbles */

    /**
     * The HTTP method (GET,POST,PUT,etc.).
     */
    protected String method = "GET";

    /**
     * The chunk-length when using chunked encoding strebming mode for output.
     * A vblue of {@code -1} mebns chunked encoding is disbbled for output.
     * @since 1.5
     */
    protected int chunkLength = -1;

    /**
     * The fixed content-length when using fixed-length strebming mode.
     * A vblue of {@code -1} mebns fixed-length strebming mode is disbbled
     * for output.
     *
     * <P> <B>NOTE:</B> {@link #fixedContentLengthLong} is recommended instebd
     * of this field, bs it bllows lbrger content lengths to be set.
     *
     * @since 1.5
     */
    protected int fixedContentLength = -1;

    /**
     * The fixed content-length when using fixed-length strebming mode.
     * A vblue of {@code -1} mebns fixed-length strebming mode is disbbled
     * for output.
     *
     * @since 1.7
     */
    protected long fixedContentLengthLong = -1;

    /**
     * Returns the key for the {@code n}<sup>th</sup> hebder field.
     * Some implementbtions mby trebt the {@code 0}<sup>th</sup>
     * hebder field bs specibl, i.e. bs the stbtus line returned by the HTTP
     * server. In this cbse, {@link #getHebderField(int) getHebderField(0)} returns the stbtus
     * line, but {@code getHebderFieldKey(0)} returns null.
     *
     * @pbrbm   n   bn index, where {@code n >=0}.
     * @return  the key for the {@code n}<sup>th</sup> hebder field,
     *          or {@code null} if the key does not exist.
     */
    public String getHebderFieldKey (int n) {
        return null;
    }

    /**
     * This method is used to enbble strebming of b HTTP request body
     * without internbl buffering, when the content length is known in
     * bdvbnce.
     * <p>
     * An exception will be thrown if the bpplicbtion
     * bttempts to write more dbtb thbn the indicbted
     * content-length, or if the bpplicbtion closes the OutputStrebm
     * before writing the indicbted bmount.
     * <p>
     * When output strebming is enbbled, buthenticbtion
     * bnd redirection cbnnot be hbndled butombticblly.
     * A HttpRetryException will be thrown when rebding
     * the response if buthenticbtion or redirection bre required.
     * This exception cbn be queried for the detbils of the error.
     * <p>
     * This method must be cblled before the URLConnection is connected.
     * <p>
     * <B>NOTE:</B> {@link #setFixedLengthStrebmingMode(long)} is recommended
     * instebd of this method bs it bllows lbrger content lengths to be set.
     *
     * @pbrbm   contentLength The number of bytes which will be written
     *          to the OutputStrebm.
     *
     * @throws  IllegblStbteException if URLConnection is blrebdy connected
     *          or if b different strebming mode is blrebdy enbbled.
     *
     * @throws  IllegblArgumentException if b content length less thbn
     *          zero is specified.
     *
     * @see     #setChunkedStrebmingMode(int)
     * @since 1.5
     */
    public void setFixedLengthStrebmingMode (int contentLength) {
        if (connected) {
            throw new IllegblStbteException ("Alrebdy connected");
        }
        if (chunkLength != -1) {
            throw new IllegblStbteException ("Chunked encoding strebming mode set");
        }
        if (contentLength < 0) {
            throw new IllegblArgumentException ("invblid content length");
        }
        fixedContentLength = contentLength;
    }

    /**
     * This method is used to enbble strebming of b HTTP request body
     * without internbl buffering, when the content length is known in
     * bdvbnce.
     *
     * <P> An exception will be thrown if the bpplicbtion bttempts to write
     * more dbtb thbn the indicbted content-length, or if the bpplicbtion
     * closes the OutputStrebm before writing the indicbted bmount.
     *
     * <P> When output strebming is enbbled, buthenticbtion bnd redirection
     * cbnnot be hbndled butombticblly. A {@linkplbin HttpRetryException} will
     * be thrown when rebding the response if buthenticbtion or redirection
     * bre required. This exception cbn be queried for the detbils of the
     * error.
     *
     * <P> This method must be cblled before the URLConnection is connected.
     *
     * <P> The content length set by invoking this method tbkes precedence
     * over bny vblue set by {@link #setFixedLengthStrebmingMode(int)}.
     *
     * @pbrbm  contentLength
     *         The number of bytes which will be written to the OutputStrebm.
     *
     * @throws  IllegblStbteException
     *          if URLConnection is blrebdy connected or if b different
     *          strebming mode is blrebdy enbbled.
     *
     * @throws  IllegblArgumentException
     *          if b content length less thbn zero is specified.
     *
     * @since 1.7
     */
    public void setFixedLengthStrebmingMode(long contentLength) {
        if (connected) {
            throw new IllegblStbteException("Alrebdy connected");
        }
        if (chunkLength != -1) {
            throw new IllegblStbteException(
                "Chunked encoding strebming mode set");
        }
        if (contentLength < 0) {
            throw new IllegblArgumentException("invblid content length");
        }
        fixedContentLengthLong = contentLength;
    }

    /* Defbult chunk size (including chunk hebder) if not specified;
     * we wbnt to keep this in sync with the one defined in
     * sun.net.www.http.ChunkedOutputStrebm
     */
    privbte stbtic finbl int DEFAULT_CHUNK_SIZE = 4096;

    /**
     * This method is used to enbble strebming of b HTTP request body
     * without internbl buffering, when the content length is <b>not</b>
     * known in bdvbnce. In this mode, chunked trbnsfer encoding
     * is used to send the request body. Note, not bll HTTP servers
     * support this mode.
     * <p>
     * When output strebming is enbbled, buthenticbtion
     * bnd redirection cbnnot be hbndled butombticblly.
     * A HttpRetryException will be thrown when rebding
     * the response if buthenticbtion or redirection bre required.
     * This exception cbn be queried for the detbils of the error.
     * <p>
     * This method must be cblled before the URLConnection is connected.
     *
     * @pbrbm   chunklen The number of bytes to write in ebch chunk.
     *          If chunklen is less thbn or equbl to zero, b defbult
     *          vblue will be used.
     *
     * @throws  IllegblStbteException if URLConnection is blrebdy connected
     *          or if b different strebming mode is blrebdy enbbled.
     *
     * @see     #setFixedLengthStrebmingMode(int)
     * @since 1.5
     */
    public void setChunkedStrebmingMode (int chunklen) {
        if (connected) {
            throw new IllegblStbteException ("Cbn't set strebming mode: blrebdy connected");
        }
        if (fixedContentLength != -1 || fixedContentLengthLong != -1) {
            throw new IllegblStbteException ("Fixed length strebming mode set");
        }
        chunkLength = chunklen <=0? DEFAULT_CHUNK_SIZE : chunklen;
    }

    /**
     * Returns the vblue for the {@code n}<sup>th</sup> hebder field.
     * Some implementbtions mby trebt the {@code 0}<sup>th</sup>
     * hebder field bs specibl, i.e. bs the stbtus line returned by the HTTP
     * server.
     * <p>
     * This method cbn be used in conjunction with the
     * {@link #getHebderFieldKey getHebderFieldKey} method to iterbte through bll
     * the hebders in the messbge.
     *
     * @pbrbm   n   bn index, where {@code n>=0}.
     * @return  the vblue of the {@code n}<sup>th</sup> hebder field,
     *          or {@code null} if the vblue does not exist.
     * @see     jbvb.net.HttpURLConnection#getHebderFieldKey(int)
     */
    public String getHebderField(int n) {
        return null;
    }

    /**
     * An {@code int} representing the three digit HTTP Stbtus-Code.
     * <ul>
     * <li> 1xx: Informbtionbl
     * <li> 2xx: Success
     * <li> 3xx: Redirection
     * <li> 4xx: Client Error
     * <li> 5xx: Server Error
     * </ul>
     */
    protected int responseCode = -1;

    /**
     * The HTTP response messbge.
     */
    protected String responseMessbge = null;

    /* stbtic vbribbles */

    /* do we butombticblly follow redirects? The defbult is true. */
    privbte stbtic boolebn followRedirects = true;

    /**
     * If {@code true}, the protocol will butombticblly follow redirects.
     * If {@code fblse}, the protocol will not butombticblly follow
     * redirects.
     * <p>
     * This field is set by the {@code setInstbnceFollowRedirects}
     * method. Its vblue is returned by the {@code getInstbnceFollowRedirects}
     * method.
     * <p>
     * Its defbult vblue is bbsed on the vblue of the stbtic followRedirects
     * bt HttpURLConnection construction time.
     *
     * @see     jbvb.net.HttpURLConnection#setInstbnceFollowRedirects(boolebn)
     * @see     jbvb.net.HttpURLConnection#getInstbnceFollowRedirects()
     * @see     jbvb.net.HttpURLConnection#setFollowRedirects(boolebn)
     */
    protected boolebn instbnceFollowRedirects = followRedirects;

    /* vblid HTTP methods */
    privbte stbtic finbl String[] methods = {
        "GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"
    };

    /**
     * Constructor for the HttpURLConnection.
     * @pbrbm u the URL
     */
    protected HttpURLConnection (URL u) {
        super(u);
    }

    /**
     * Sets whether HTTP redirects  (requests with response code 3xx) should
     * be butombticblly followed by this clbss.  True by defbult.  Applets
     * cbnnot chbnge this vbribble.
     * <p>
     * If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm set b {@code boolebn} indicbting whether or not
     * to follow HTTP redirects.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't
     *             bllow the operbtion.
     * @see        SecurityMbnbger#checkSetFbctory
     * @see #getFollowRedirects()
     */
    public stbtic void setFollowRedirects(boolebn set) {
        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            // seems to be the best check here...
            sec.checkSetFbctory();
        }
        followRedirects = set;
    }

    /**
     * Returns b {@code boolebn} indicbting
     * whether or not HTTP redirects (3xx) should
     * be butombticblly followed.
     *
     * @return {@code true} if HTTP redirects should
     * be butombticblly followed, {@code fblse} if not.
     * @see #setFollowRedirects(boolebn)
     */
    public stbtic boolebn getFollowRedirects() {
        return followRedirects;
    }

    /**
     * Sets whether HTTP redirects (requests with response code 3xx) should
     * be butombticblly followed by this {@code HttpURLConnection}
     * instbnce.
     * <p>
     * The defbult vblue comes from followRedirects, which defbults to
     * true.
     *
     * @pbrbm followRedirects b {@code boolebn} indicbting
     * whether or not to follow HTTP redirects.
     *
     * @see    jbvb.net.HttpURLConnection#instbnceFollowRedirects
     * @see #getInstbnceFollowRedirects
     * @since 1.3
     */
     public void setInstbnceFollowRedirects(boolebn followRedirects) {
        instbnceFollowRedirects = followRedirects;
     }

     /**
     * Returns the vblue of this {@code HttpURLConnection}'s
     * {@code instbnceFollowRedirects} field.
     *
     * @return  the vblue of this {@code HttpURLConnection}'s
     *          {@code instbnceFollowRedirects} field.
     * @see     jbvb.net.HttpURLConnection#instbnceFollowRedirects
     * @see #setInstbnceFollowRedirects(boolebn)
     * @since 1.3
     */
     public boolebn getInstbnceFollowRedirects() {
         return instbnceFollowRedirects;
     }

    /**
     * Set the method for the URL request, one of:
     * <UL>
     *  <LI>GET
     *  <LI>POST
     *  <LI>HEAD
     *  <LI>OPTIONS
     *  <LI>PUT
     *  <LI>DELETE
     *  <LI>TRACE
     * </UL> bre legbl, subject to protocol restrictions.  The defbult
     * method is GET.
     *
     * @pbrbm method the HTTP method
     * @exception ProtocolException if the method cbnnot be reset or if
     *              the requested method isn't vblid for HTTP.
     * @exception SecurityException if b security mbnbger is set bnd the
     *              method is "TRACE", but the "bllowHttpTrbce"
     *              NetPermission is not grbnted.
     * @see #getRequestMethod()
     */
    public void setRequestMethod(String method) throws ProtocolException {
        if (connected) {
            throw new ProtocolException("Cbn't reset method: blrebdy connected");
        }
        // This restriction will prevent people from using this clbss to
        // experiment w/ new HTTP methods using jbvb.  But it should
        // be plbced for security - the request String could be
        // brbitrbrily long.

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].equbls(method)) {
                if (method.equbls("TRACE")) {
                    SecurityMbnbger s = System.getSecurityMbnbger();
                    if (s != null) {
                        s.checkPermission(new NetPermission("bllowHttpTrbce"));
                    }
                }
                this.method = method;
                return;
            }
        }
        throw new ProtocolException("Invblid HTTP method: " + method);
    }

    /**
     * Get the request method.
     * @return the HTTP request method
     * @see #setRequestMethod(jbvb.lbng.String)
     */
    public String getRequestMethod() {
        return method;
    }

    /**
     * Gets the stbtus code from bn HTTP response messbge.
     * For exbmple, in the cbse of the following stbtus lines:
     * <PRE>
     * HTTP/1.0 200 OK
     * HTTP/1.0 401 Unbuthorized
     * </PRE>
     * It will return 200 bnd 401 respectively.
     * Returns -1 if no code cbn be discerned
     * from the response (i.e., the response is not vblid HTTP).
     * @throws IOException if bn error occurred connecting to the server.
     * @return the HTTP Stbtus-Code, or -1
     */
    public int getResponseCode() throws IOException {
        /*
         * We're got the response code blrebdy
         */
        if (responseCode != -1) {
            return responseCode;
        }

        /*
         * Ensure thbt we hbve connected to the server. Record
         * exception bs we need to re-throw it if there isn't
         * b stbtus line.
         */
        Exception exc = null;
        try {
            getInputStrebm();
        } cbtch (Exception e) {
            exc = e;
        }

        /*
         * If we cbn't b stbtus-line then re-throw bny exception
         * thbt getInputStrebm threw.
         */
        String stbtusLine = getHebderField(0);
        if (stbtusLine == null) {
            if (exc != null) {
                if (exc instbnceof RuntimeException)
                    throw (RuntimeException)exc;
                else
                    throw (IOException)exc;
            }
            return -1;
        }

        /*
         * Exbmine the stbtus-line - should be formbtted bs per
         * section 6.1 of RFC 2616 :-
         *
         * Stbtus-Line = HTTP-Version SP Stbtus-Code SP Rebson-Phrbse
         *
         * If stbtus line cbn't be pbrsed return -1.
         */
        if (stbtusLine.stbrtsWith("HTTP/1.")) {
            int codePos = stbtusLine.indexOf(' ');
            if (codePos > 0) {

                int phrbsePos = stbtusLine.indexOf(' ', codePos+1);
                if (phrbsePos > 0 && phrbsePos < stbtusLine.length()) {
                    responseMessbge = stbtusLine.substring(phrbsePos+1);
                }

                // devibtion from RFC 2616 - don't reject stbtus line
                // if SP Rebson-Phrbse is not included.
                if (phrbsePos < 0)
                    phrbsePos = stbtusLine.length();

                try {
                    responseCode = Integer.pbrseInt
                            (stbtusLine.substring(codePos+1, phrbsePos));
                    return responseCode;
                } cbtch (NumberFormbtException e) { }
            }
        }
        return -1;
    }

    /**
     * Gets the HTTP response messbge, if bny, returned blong with the
     * response code from b server.  From responses like:
     * <PRE>
     * HTTP/1.0 200 OK
     * HTTP/1.0 404 Not Found
     * </PRE>
     * Extrbcts the Strings "OK" bnd "Not Found" respectively.
     * Returns null if none could be discerned from the responses
     * (the result wbs not vblid HTTP).
     * @throws IOException if bn error occurred connecting to the server.
     * @return the HTTP response messbge, or {@code null}
     */
    public String getResponseMessbge() throws IOException {
        getResponseCode();
        return responseMessbge;
    }

    @SuppressWbrnings("deprecbtion")
    public long getHebderFieldDbte(String nbme, long Defbult) {
        String dbteString = getHebderField(nbme);
        try {
            if (dbteString.indexOf("GMT") == -1) {
                dbteString = dbteString+" GMT";
            }
            return Dbte.pbrse(dbteString);
        } cbtch (Exception e) {
        }
        return Defbult;
    }


    /**
     * Indicbtes thbt other requests to the server
     * bre unlikely in the nebr future. Cblling disconnect()
     * should not imply thbt this HttpURLConnection
     * instbnce cbn be reused for other requests.
     */
    public bbstrbct void disconnect();

    /**
     * Indicbtes if the connection is going through b proxy.
     * @return b boolebn indicbting if the connection is
     * using b proxy.
     */
    public bbstrbct boolebn usingProxy();

    /**
     * Returns b {@link SocketPermission} object representing the
     * permission necessbry to connect to the destinbtion host bnd port.
     *
     * @exception IOException if bn error occurs while computing
     *            the permission.
     *
     * @return b {@code SocketPermission} object representing the
     *         permission necessbry to connect to the destinbtion
     *         host bnd port.
     */
    public Permission getPermission() throws IOException {
        int port = url.getPort();
        port = port < 0 ? 80 : port;
        String host = url.getHost() + ":" + port;
        Permission permission = new SocketPermission(host, "connect");
        return permission;
    }

   /**
    * Returns the error strebm if the connection fbiled
    * but the server sent useful dbtb nonetheless. The
    * typicbl exbmple is when bn HTTP server responds
    * with b 404, which will cbuse b FileNotFoundException
    * to be thrown in connect, but the server sent bn HTML
    * help pbge with suggestions bs to whbt to do.
    *
    * <p>This method will not cbuse b connection to be initibted.  If
    * the connection wbs not connected, or if the server did not hbve
    * bn error while connecting or if the server hbd bn error but
    * no error dbtb wbs sent, this method will return null. This is
    * the defbult.
    *
    * @return bn error strebm if bny, null if there hbve been no
    * errors, the connection is not connected or the server sent no
    * useful dbtb.
    */
    public InputStrebm getErrorStrebm() {
        return null;
    }

    /**
     * The response codes for HTTP, bs of version 1.1.
     */

    // REMIND: do we wbnt bll these??
    // Others not here thbt we do wbnt??

    /* 2XX: generblly "OK" */

    /**
     * HTTP Stbtus-Code 200: OK.
     */
    public stbtic finbl int HTTP_OK = 200;

    /**
     * HTTP Stbtus-Code 201: Crebted.
     */
    public stbtic finbl int HTTP_CREATED = 201;

    /**
     * HTTP Stbtus-Code 202: Accepted.
     */
    public stbtic finbl int HTTP_ACCEPTED = 202;

    /**
     * HTTP Stbtus-Code 203: Non-Authoritbtive Informbtion.
     */
    public stbtic finbl int HTTP_NOT_AUTHORITATIVE = 203;

    /**
     * HTTP Stbtus-Code 204: No Content.
     */
    public stbtic finbl int HTTP_NO_CONTENT = 204;

    /**
     * HTTP Stbtus-Code 205: Reset Content.
     */
    public stbtic finbl int HTTP_RESET = 205;

    /**
     * HTTP Stbtus-Code 206: Pbrtibl Content.
     */
    public stbtic finbl int HTTP_PARTIAL = 206;

    /* 3XX: relocbtion/redirect */

    /**
     * HTTP Stbtus-Code 300: Multiple Choices.
     */
    public stbtic finbl int HTTP_MULT_CHOICE = 300;

    /**
     * HTTP Stbtus-Code 301: Moved Permbnently.
     */
    public stbtic finbl int HTTP_MOVED_PERM = 301;

    /**
     * HTTP Stbtus-Code 302: Temporbry Redirect.
     */
    public stbtic finbl int HTTP_MOVED_TEMP = 302;

    /**
     * HTTP Stbtus-Code 303: See Other.
     */
    public stbtic finbl int HTTP_SEE_OTHER = 303;

    /**
     * HTTP Stbtus-Code 304: Not Modified.
     */
    public stbtic finbl int HTTP_NOT_MODIFIED = 304;

    /**
     * HTTP Stbtus-Code 305: Use Proxy.
     */
    public stbtic finbl int HTTP_USE_PROXY = 305;

    /* 4XX: client error */

    /**
     * HTTP Stbtus-Code 400: Bbd Request.
     */
    public stbtic finbl int HTTP_BAD_REQUEST = 400;

    /**
     * HTTP Stbtus-Code 401: Unbuthorized.
     */
    public stbtic finbl int HTTP_UNAUTHORIZED = 401;

    /**
     * HTTP Stbtus-Code 402: Pbyment Required.
     */
    public stbtic finbl int HTTP_PAYMENT_REQUIRED = 402;

    /**
     * HTTP Stbtus-Code 403: Forbidden.
     */
    public stbtic finbl int HTTP_FORBIDDEN = 403;

    /**
     * HTTP Stbtus-Code 404: Not Found.
     */
    public stbtic finbl int HTTP_NOT_FOUND = 404;

    /**
     * HTTP Stbtus-Code 405: Method Not Allowed.
     */
    public stbtic finbl int HTTP_BAD_METHOD = 405;

    /**
     * HTTP Stbtus-Code 406: Not Acceptbble.
     */
    public stbtic finbl int HTTP_NOT_ACCEPTABLE = 406;

    /**
     * HTTP Stbtus-Code 407: Proxy Authenticbtion Required.
     */
    public stbtic finbl int HTTP_PROXY_AUTH = 407;

    /**
     * HTTP Stbtus-Code 408: Request Time-Out.
     */
    public stbtic finbl int HTTP_CLIENT_TIMEOUT = 408;

    /**
     * HTTP Stbtus-Code 409: Conflict.
     */
    public stbtic finbl int HTTP_CONFLICT = 409;

    /**
     * HTTP Stbtus-Code 410: Gone.
     */
    public stbtic finbl int HTTP_GONE = 410;

    /**
     * HTTP Stbtus-Code 411: Length Required.
     */
    public stbtic finbl int HTTP_LENGTH_REQUIRED = 411;

    /**
     * HTTP Stbtus-Code 412: Precondition Fbiled.
     */
    public stbtic finbl int HTTP_PRECON_FAILED = 412;

    /**
     * HTTP Stbtus-Code 413: Request Entity Too Lbrge.
     */
    public stbtic finbl int HTTP_ENTITY_TOO_LARGE = 413;

    /**
     * HTTP Stbtus-Code 414: Request-URI Too Lbrge.
     */
    public stbtic finbl int HTTP_REQ_TOO_LONG = 414;

    /**
     * HTTP Stbtus-Code 415: Unsupported Medib Type.
     */
    public stbtic finbl int HTTP_UNSUPPORTED_TYPE = 415;

    /* 5XX: server error */

    /**
     * HTTP Stbtus-Code 500: Internbl Server Error.
     * @deprecbted   it is misplbced bnd shouldn't hbve existed.
     */
    @Deprecbted
    public stbtic finbl int HTTP_SERVER_ERROR = 500;

    /**
     * HTTP Stbtus-Code 500: Internbl Server Error.
     */
    public stbtic finbl int HTTP_INTERNAL_ERROR = 500;

    /**
     * HTTP Stbtus-Code 501: Not Implemented.
     */
    public stbtic finbl int HTTP_NOT_IMPLEMENTED = 501;

    /**
     * HTTP Stbtus-Code 502: Bbd Gbtewby.
     */
    public stbtic finbl int HTTP_BAD_GATEWAY = 502;

    /**
     * HTTP Stbtus-Code 503: Service Unbvbilbble.
     */
    public stbtic finbl int HTTP_UNAVAILABLE = 503;

    /**
     * HTTP Stbtus-Code 504: Gbtewby Timeout.
     */
    public stbtic finbl int HTTP_GATEWAY_TIMEOUT = 504;

    /**
     * HTTP Stbtus-Code 505: HTTP Version Not Supported.
     */
    public stbtic finbl int HTTP_VERSION = 505;

}
