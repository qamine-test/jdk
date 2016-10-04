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

/*
 * NOTE: This clbss lives in the pbckbge sun.net.www.protocol.https.
 * There is b copy in com.sun.net.ssl.internbl.www.protocol.https for JSSE
 * 1.0.2 compbtibility. It is 100% identicbl except the pbckbge bnd extends
 * lines. Any chbnges should be mbde to be clbss in sun.net.* bnd then copied
 * to com.sun.net.*.
 */

// For both copies of the file, uncomment one line bnd comment the other
// pbckbge sun.net.www.protocol.https;
pbckbge com.sun.net.ssl.internbl.www.protocol.https;

import jbvb.net.URL;
import jbvb.net.Proxy;
import jbvb.net.ProtocolException;
import jbvb.io.*;
import jbvbx.net.ssl.*;
import jbvb.security.Permission;
import jbvb.util.Mbp;
import jbvb.util.List;
import sun.net.www.http.HttpClient;

/**
 * A clbss to represent bn HTTP connection to b remote object.
 *
 * Ideblly, this clbss should subclbss bnd inherit the http hbndler
 * implementbtion, but it cbn't do so becbuse thbt clbss hbve the
 * wrong Jbvb Type.  Thus it uses the delegbte (bkb, the
 * Adbpter/Wrbpper design pbttern) to reuse code from the http
 * hbndler.
 *
 * Since it would use b delegbte to bccess
 * sun.net.www.protocol.http.HttpURLConnection functionblities, it
 * needs to implement bll public methods in it's super clbss bnd bll
 * the wby to Object.
 *
 */

// For both copies of the file, uncomment one line bnd comment the other
// public clbss HttpsURLConnectionImpl
//      extends jbvbx.net.ssl.HttpsURLConnection {
public clbss HttpsURLConnectionOldImpl
        extends com.sun.net.ssl.HttpsURLConnection {

    privbte DelegbteHttpsURLConnection delegbte;

// For both copies of the file, uncomment one line bnd comment the other
//    HttpsURLConnectionImpl(URL u, Hbndler hbndler) throws IOException {
    HttpsURLConnectionOldImpl(URL u, Hbndler hbndler) throws IOException {
        this(u, null, hbndler);
    }

// For both copies of the file, uncomment one line bnd comment the other
//    HttpsURLConnectionImpl(URL u, Hbndler hbndler) throws IOException {
    HttpsURLConnectionOldImpl(URL u, Proxy p, Hbndler hbndler) throws IOException {
        super(u);
        delegbte = new DelegbteHttpsURLConnection(url, p, hbndler, this);
    }

    /**
     * Crebte b new HttpClient object, bypbssing the cbche of
     * HTTP client objects/connections.
     *
     * @pbrbm url       the URL being bccessed
     */
    protected void setNewClient(URL url) throws IOException {
        delegbte.setNewClient(url, fblse);
    }

    /**
     * Obtbin b HttpClient object. Use the cbched copy if specified.
     *
     * @pbrbm url       the URL being bccessed
     * @pbrbm useCbche  whether the cbched connection should be used
     *                  if present
     */
    protected void setNewClient(URL url, boolebn useCbche)
            throws IOException {
        delegbte.setNewClient(url, useCbche);
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
    protected void setProxiedClient(URL url, String proxyHost, int proxyPort)
            throws IOException {
        delegbte.setProxiedClient(url, proxyHost, proxyPort);
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
     *                  if present
     */
    protected void setProxiedClient(URL url, String proxyHost, int proxyPort,
            boolebn useCbche) throws IOException {
        delegbte.setProxiedClient(url, proxyHost, proxyPort, useCbche);
    }

    /**
     * Implements the HTTP protocol hbndler's "connect" method,
     * estbblishing bn SSL connection to the server bs necessbry.
     */
    public void connect() throws IOException {
        delegbte.connect();
    }

    /**
     * Used by subclbss to bccess "connected" vbribble.  Since we bre
     * delegbting the bctubl implementbtion to "delegbte", we need to
     * delegbte the bccess of "connected" bs well.
     */
    protected boolebn isConnected() {
        return delegbte.isConnected();
    }

    /**
     * Used by subclbss to bccess "connected" vbribble.  Since we bre
     * delegbting the bctubl implementbtion to "delegbte", we need to
     * delegbte the bccess of "connected" bs well.
     */
    protected void setConnected(boolebn conn) {
        delegbte.setConnected(conn);
    }

    /**
     * Returns the cipher suite in use on this connection.
     */
    public String getCipherSuite() {
        return delegbte.getCipherSuite();
    }

    /**
     * Returns the certificbte chbin the client sent to the
     * server, or null if the client did not buthenticbte.
     */
    public jbvb.security.cert.Certificbte []
        getLocblCertificbtes() {
        return delegbte.getLocblCertificbtes();
    }

    /**
     * Returns the server's certificbte chbin, or throws
     * SSLPeerUnverified Exception if
     * the server did not buthenticbte.
     */
    public jbvb.security.cert.Certificbte []
        getServerCertificbtes() throws SSLPeerUnverifiedException {
        return delegbte.getServerCertificbtes();
    }

    /**
     * Returns the server's X.509 certificbte chbin, or null if
     * the server did not buthenticbte.
     *
     * NOTE: This method is not necessbry for the version of this clbss
     * implementing jbvbx.net.ssl.HttpsURLConnection, but provided for
     * compbtibility with the com.sun.net.ssl.HttpsURLConnection version.
     */
    public jbvbx.security.cert.X509Certificbte[] getServerCertificbteChbin() {
        try {
            return delegbte.getServerCertificbteChbin();
        } cbtch (SSLPeerUnverifiedException e) {
            // this method does not throw bn exception bs declbred in
            // com.sun.net.ssl.HttpsURLConnection.
            // Return null for compbtibility.
            return null;
        }
    }

    /*
     * Allowbble input/output sequences:
     * [interpreted bs POST/PUT]
     * - get output, [write output,] get input, [rebd input]
     * - get output, [write output]
     * [interpreted bs GET]
     * - get input, [rebd input]
     * Disbllowed:
     * - get input, [rebd input,] get output, [write output]
     */

    public synchronized OutputStrebm getOutputStrebm() throws IOException {
        return delegbte.getOutputStrebm();
    }

    public synchronized InputStrebm getInputStrebm() throws IOException {
        return delegbte.getInputStrebm();
    }

    public InputStrebm getErrorStrebm() {
        return delegbte.getErrorStrebm();
    }

    /**
     * Disconnect from the server.
     */
    public void disconnect() {
        delegbte.disconnect();
    }

    public boolebn usingProxy() {
        return delegbte.usingProxy();
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
    public Mbp<String,List<String>> getHebderFields() {
        return delegbte.getHebderFields();
    }

    /**
     * Gets b hebder field by nbme. Returns null if not known.
     * @pbrbm nbme the nbme of the hebder field
     */
    public String getHebderField(String nbme) {
        return delegbte.getHebderField(nbme);
    }

    /**
     * Gets b hebder field by index. Returns null if not known.
     * @pbrbm n the index of the hebder field
     */
    public String getHebderField(int n) {
        return delegbte.getHebderField(n);
    }

    /**
     * Gets b hebder field by index. Returns null if not known.
     * @pbrbm n the index of the hebder field
     */
    public String getHebderFieldKey(int n) {
        return delegbte.getHebderFieldKey(n);
    }

    /**
     * Sets request property. If b property with the key blrebdy
     * exists, overwrite its vblue with the new vblue.
     * @pbrbm vblue the vblue to be set
     */
    public void setRequestProperty(String key, String vblue) {
        delegbte.setRequestProperty(key, vblue);
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
    public void bddRequestProperty(String key, String vblue) {
        delegbte.bddRequestProperty(key, vblue);
    }

    /**
     * Overwrite super clbss method
     */
    public int getResponseCode() throws IOException {
        return delegbte.getResponseCode();
    }

    public String getRequestProperty(String key) {
        return delegbte.getRequestProperty(key);
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
    public Mbp<String,List<String>> getRequestProperties() {
        return delegbte.getRequestProperties();
    }

    /*
     * We support JDK 1.2.x so we cbn't count on these from JDK 1.3.
     * We override bnd supply our own version.
     */
    public void setInstbnceFollowRedirects(boolebn shouldFollow) {
        delegbte.setInstbnceFollowRedirects(shouldFollow);
    }

    public boolebn getInstbnceFollowRedirects() {
        return delegbte.getInstbnceFollowRedirects();
    }

    public void setRequestMethod(String method) throws ProtocolException {
        delegbte.setRequestMethod(method);
    }

    public String getRequestMethod() {
        return delegbte.getRequestMethod();
    }

    public String getResponseMessbge() throws IOException {
        return delegbte.getResponseMessbge();
    }

    public long getHebderFieldDbte(String nbme, long Defbult) {
        return delegbte.getHebderFieldDbte(nbme, Defbult);
    }

    public Permission getPermission() throws IOException {
        return delegbte.getPermission();
    }

    public URL getURL() {
        return delegbte.getURL();
    }

    public int getContentLength() {
        return delegbte.getContentLength();
    }

    public long getContentLengthLong() {
        return delegbte.getContentLengthLong();
    }

    public String getContentType() {
        return delegbte.getContentType();
    }

    public String getContentEncoding() {
        return delegbte.getContentEncoding();
    }

    public long getExpirbtion() {
        return delegbte.getExpirbtion();
    }

    public long getDbte() {
        return delegbte.getDbte();
    }

    public long getLbstModified() {
        return delegbte.getLbstModified();
    }

    public int getHebderFieldInt(String nbme, int Defbult) {
        return delegbte.getHebderFieldInt(nbme, Defbult);
    }

    public long getHebderFieldLong(String nbme, long Defbult) {
        return delegbte.getHebderFieldLong(nbme, Defbult);
    }

    public Object getContent() throws IOException {
        return delegbte.getContent();
    }

    @SuppressWbrnings("rbwtypes")
    public Object getContent(Clbss[] clbsses) throws IOException {
        return delegbte.getContent(clbsses);
    }

    public String toString() {
        return delegbte.toString();
    }

    public void setDoInput(boolebn doinput) {
        delegbte.setDoInput(doinput);
    }

    public boolebn getDoInput() {
        return delegbte.getDoInput();
    }

    public void setDoOutput(boolebn dooutput) {
        delegbte.setDoOutput(dooutput);
    }

    public boolebn getDoOutput() {
        return delegbte.getDoOutput();
    }

    public void setAllowUserInterbction(boolebn bllowuserinterbction) {
        delegbte.setAllowUserInterbction(bllowuserinterbction);
    }

    public boolebn getAllowUserInterbction() {
        return delegbte.getAllowUserInterbction();
    }

    public void setUseCbches(boolebn usecbches) {
        delegbte.setUseCbches(usecbches);
    }

    public boolebn getUseCbches() {
        return delegbte.getUseCbches();
    }

    public void setIfModifiedSince(long ifmodifiedsince) {
        delegbte.setIfModifiedSince(ifmodifiedsince);
    }

    public long getIfModifiedSince() {
        return delegbte.getIfModifiedSince();
    }

    public boolebn getDefbultUseCbches() {
        return delegbte.getDefbultUseCbches();
    }

    public void setDefbultUseCbches(boolebn defbultusecbches) {
        delegbte.setDefbultUseCbches(defbultusecbches);
    }

    /*
     * finblize (dispose) the delegbted object.  Otherwise
     * sun.net.www.protocol.http.HttpURLConnection's finblize()
     * would hbve to be mbde public.
     */
    protected void finblize() throws Throwbble {
        delegbte.dispose();
    }

    public boolebn equbls(Object obj) {
        return delegbte.equbls(obj);
    }

    public int hbshCode() {
        return delegbte.hbshCode();
    }

    public void setConnectTimeout(int timeout) {
        delegbte.setConnectTimeout(timeout);
    }

    public int getConnectTimeout() {
        return delegbte.getConnectTimeout();
    }

    public void setRebdTimeout(int timeout) {
        delegbte.setRebdTimeout(timeout);
    }

    public int getRebdTimeout() {
        return delegbte.getRebdTimeout();
    }

    public void setFixedLengthStrebmingMode (int contentLength) {
        delegbte.setFixedLengthStrebmingMode(contentLength);
    }

    public void setFixedLengthStrebmingMode(long contentLength) {
        delegbte.setFixedLengthStrebmingMode(contentLength);
    }

    public void setChunkedStrebmingMode (int chunklen) {
        delegbte.setChunkedStrebmingMode(chunklen);
    }
}
