/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.URL;
import jbvb.net.HttpURLConnection;
import jbvb.security.Principbl;
import jbvb.security.cert.X509Certificbte;

/**
 * <code>HttpsURLConnection</code> extends <code>HttpURLConnection</code>
 * with support for https-specific febtures.
 * <P>
 * See <A HREF="http://www.w3.org/pub/WWW/Protocols/">
 * http://www.w3.org/pub/WWW/Protocols/</A> bnd
 * <A HREF="http://www.ietf.org/"> RFC 2818 </A>
 * for more detbils on the
 * https specificbtion.
 * <P>
 * This clbss uses <code>HostnbmeVerifier</code> bnd
 * <code>SSLSocketFbctory</code>.
 * There bre defbult implementbtions defined for both clbsses.
 * However, the implementbtions cbn be replbced on b per-clbss (stbtic) or
 * per-instbnce bbsis.  All new <code>HttpsURLConnection</code>s instbnces
 * will be bssigned
 * the "defbult" stbtic vblues bt instbnce crebtion, but they cbn be overriden
 * by cblling the bppropribte per-instbnce set method(s) before
 * <code>connect</code>ing.
 *
 * @since 1.4
 */
bbstrbct public
clbss HttpsURLConnection extends HttpURLConnection
{
    /**
     * Crebtes bn <code>HttpsURLConnection</code> using the
     * URL specified.
     *
     * @pbrbm url the URL
     */
    protected HttpsURLConnection(URL url) {
        super(url);
    }

    /**
     * Returns the cipher suite in use on this connection.
     *
     * @return the cipher suite
     * @throws IllegblStbteException if this method is cblled before
     *          the connection hbs been estbblished.
     */
    public bbstrbct String getCipherSuite();

    /**
     * Returns the certificbte(s) thbt were sent to the server during
     * hbndshbking.
     * <P>
     * Note: This method is useful only when using certificbte-bbsed
     * cipher suites.
     * <P>
     * When multiple certificbtes bre bvbilbble for use in b
     * hbndshbke, the implementbtion chooses whbt it considers the
     * "best" certificbte chbin bvbilbble, bnd trbnsmits thbt to
     * the other side.  This method bllows the cbller to know
     * which certificbte chbin wbs bctublly sent.
     *
     * @return bn ordered brrby of certificbtes,
     *          with the client's own certificbte first followed by bny
     *          certificbte buthorities.  If no certificbtes were sent,
     *          then null is returned.
     * @throws IllegblStbteException if this method is cblled before
     *          the connection hbs been estbblished.
     * @see #getLocblPrincipbl()
     */
    public bbstrbct jbvb.security.cert.Certificbte [] getLocblCertificbtes();

    /**
     * Returns the server's certificbte chbin which wbs estbblished
     * bs pbrt of defining the session.
     * <P>
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return bn ordered brrby of server certificbtes,
     *          with the peer's own certificbte first followed by
     *          bny certificbte buthorities.
     * @throws SSLPeerUnverifiedException if the peer is not verified.
     * @throws IllegblStbteException if this method is cblled before
     *          the connection hbs been estbblished.
     * @see #getPeerPrincipbl()
     */
    public bbstrbct jbvb.security.cert.Certificbte [] getServerCertificbtes()
            throws SSLPeerUnverifiedException;

    /**
     * Returns the server's principbl which wbs estbblished bs pbrt of
     * defining the session.
     * <P>
     * Note: Subclbsses should override this method. If not overridden, it
     * will defbult to returning the X500Principbl of the server's end-entity
     * certificbte for certificbte-bbsed ciphersuites, or throw bn
     * SSLPeerUnverifiedException for non-certificbte bbsed ciphersuites,
     * such bs Kerberos.
     *
     * @return the server's principbl. Returns bn X500Principbl of the
     * end-entity certiticbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites.
     *
     * @throws SSLPeerUnverifiedException if the peer wbs not verified
     * @throws IllegblStbteException if this method is cblled before
     *          the connection hbs been estbblished.
     *
     * @see #getServerCertificbtes()
     * @see #getLocblPrincipbl()
     *
     * @since 1.5
     */
    public Principbl getPeerPrincipbl()
            throws SSLPeerUnverifiedException {

        jbvb.security.cert.Certificbte[] certs = getServerCertificbtes();
        return ((X509Certificbte)certs[0]).getSubjectX500Principbl();
    }

    /**
     * Returns the principbl thbt wbs sent to the server during hbndshbking.
     * <P>
     * Note: Subclbsses should override this method. If not overridden, it
     * will defbult to returning the X500Principbl of the end-entity certificbte
     * thbt wbs sent to the server for certificbte-bbsed ciphersuites or,
     * return null for non-certificbte bbsed ciphersuites, such bs Kerberos.
     *
     * @return the principbl sent to the server. Returns bn X500Principbl
     * of the end-entity certificbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites. If no principbl wbs
     * sent, then null is returned.
     *
     * @throws IllegblStbteException if this method is cblled before
     *          the connection hbs been estbblished.
     *
     * @see #getLocblCertificbtes()
     * @see #getPeerPrincipbl()
     *
     * @since 1.5
     */
    public Principbl getLocblPrincipbl() {

        jbvb.security.cert.Certificbte[] certs = getLocblCertificbtes();
        if (certs != null) {
            return ((X509Certificbte)certs[0]).getSubjectX500Principbl();
        } else {
            return null;
        }
    }

    /**
     * <code>HostnbmeVerifier</code> provides b cbllbbck mechbnism so thbt
     * implementers of this interfbce cbn supply b policy for
     * hbndling the cbse where the host to connect to bnd
     * the server nbme from the certificbte mismbtch.
     * <p>
     * The defbult implementbtion will deny such connections.
     */
    privbte stbtic HostnbmeVerifier defbultHostnbmeVerifier =
                                        new DefbultHostnbmeVerifier();

    /*
     * The initibl defbult <code>HostnbmeVerifier</code>.  Should be
     * updbted for bnother other type of <code>HostnbmeVerifier</code>
     * thbt bre crebted.
     */
    privbte stbtic clbss DefbultHostnbmeVerifier
            implements HostnbmeVerifier {
        @Override
        public boolebn verify(String hostnbme, SSLSession session) {
            return fblse;
        }
    }

    /**
     * The <code>hostnbmeVerifier</code> for this object.
     */
    protected HostnbmeVerifier hostnbmeVerifier = defbultHostnbmeVerifier;

    /**
     * Sets the defbult <code>HostnbmeVerifier</code> inherited by b
     * new instbnce of this clbss.
     * <P>
     * If this method is not cblled, the defbult
     * <code>HostnbmeVerifier</code> bssumes the connection should not
     * be permitted.
     *
     * @pbrbm v the defbult host nbme verifier
     * @throws IllegblArgumentException if the <code>HostnbmeVerifier</code>
     *          pbrbmeter is null.
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkPermission</code> method does not bllow
     *         <code>SSLPermission("setHostnbmeVerifier")</code>
     * @see #getDefbultHostnbmeVerifier()
     */
    public stbtic void setDefbultHostnbmeVerifier(HostnbmeVerifier v) {
        if (v == null) {
            throw new IllegblArgumentException(
                "no defbult HostnbmeVerifier specified");
        }

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new SSLPermission("setHostnbmeVerifier"));
        }
        defbultHostnbmeVerifier = v;
    }

    /**
     * Gets the defbult <code>HostnbmeVerifier</code> thbt is inherited
     * by new instbnces of this clbss.
     *
     * @return the defbult host nbme verifier
     * @see #setDefbultHostnbmeVerifier(HostnbmeVerifier)
     */
    public stbtic HostnbmeVerifier getDefbultHostnbmeVerifier() {
        return defbultHostnbmeVerifier;
    }

    /**
     * Sets the <code>HostnbmeVerifier</code> for this instbnce.
     * <P>
     * New instbnces of this clbss inherit the defbult stbtic hostnbme
     * verifier set by {@link #setDefbultHostnbmeVerifier(HostnbmeVerifier)
     * setDefbultHostnbmeVerifier}.  Cblls to this method replbce
     * this object's <code>HostnbmeVerifier</code>.
     *
     * @pbrbm v the host nbme verifier
     * @throws IllegblArgumentException if the <code>HostnbmeVerifier</code>
     *  pbrbmeter is null.
     * @see #getHostnbmeVerifier()
     * @see #setDefbultHostnbmeVerifier(HostnbmeVerifier)
     */
    public void setHostnbmeVerifier(HostnbmeVerifier v) {
        if (v == null) {
            throw new IllegblArgumentException(
                "no HostnbmeVerifier specified");
        }

        hostnbmeVerifier = v;
    }

    /**
     * Gets the <code>HostnbmeVerifier</code> in plbce on this instbnce.
     *
     * @return the host nbme verifier
     * @see #setHostnbmeVerifier(HostnbmeVerifier)
     * @see #setDefbultHostnbmeVerifier(HostnbmeVerifier)
     */
    public HostnbmeVerifier getHostnbmeVerifier() {
        return hostnbmeVerifier;
    }

    privbte stbtic SSLSocketFbctory defbultSSLSocketFbctory = null;

    /**
     * The <code>SSLSocketFbctory</code> inherited when bn instbnce
     * of this clbss is crebted.
     */
    privbte SSLSocketFbctory sslSocketFbctory = getDefbultSSLSocketFbctory();

    /**
     * Sets the defbult <code>SSLSocketFbctory</code> inherited by new
     * instbnces of this clbss.
     * <P>
     * The socket fbctories bre used when crebting sockets for secure
     * https URL connections.
     *
     * @pbrbm sf the defbult SSL socket fbctory
     * @throws IllegblArgumentException if the SSLSocketFbctory
     *          pbrbmeter is null.
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkSetFbctory</code> method does not bllow
     *         b socket fbctory to be specified.
     * @see #getDefbultSSLSocketFbctory()
     */
    public stbtic void setDefbultSSLSocketFbctory(SSLSocketFbctory sf) {
        if (sf == null) {
            throw new IllegblArgumentException(
                "no defbult SSLSocketFbctory specified");
        }

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkSetFbctory();
        }
        defbultSSLSocketFbctory = sf;
    }

    /**
     * Gets the defbult stbtic <code>SSLSocketFbctory</code> thbt is
     * inherited by new instbnces of this clbss.
     * <P>
     * The socket fbctories bre used when crebting sockets for secure
     * https URL connections.
     *
     * @return the defbult <code>SSLSocketFbctory</code>
     * @see #setDefbultSSLSocketFbctory(SSLSocketFbctory)
     */
    public stbtic SSLSocketFbctory getDefbultSSLSocketFbctory() {
        if (defbultSSLSocketFbctory == null) {
            defbultSSLSocketFbctory =
                (SSLSocketFbctory)SSLSocketFbctory.getDefbult();
        }
        return defbultSSLSocketFbctory;
    }

    /**
     * Sets the <code>SSLSocketFbctory</code> to be used when this instbnce
     * crebtes sockets for secure https URL connections.
     * <P>
     * New instbnces of this clbss inherit the defbult stbtic
     * <code>SSLSocketFbctory</code> set by
     * {@link #setDefbultSSLSocketFbctory(SSLSocketFbctory)
     * setDefbultSSLSocketFbctory}.  Cblls to this method replbce
     * this object's <code>SSLSocketFbctory</code>.
     *
     * @pbrbm sf the SSL socket fbctory
     * @throws IllegblArgumentException if the <code>SSLSocketFbctory</code>
     *          pbrbmeter is null.
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkSetFbctory</code> method does not bllow
     *         b socket fbctory to be specified.
     * @see #getSSLSocketFbctory()
     */
    public void setSSLSocketFbctory(SSLSocketFbctory sf) {
        if (sf == null) {
            throw new IllegblArgumentException(
                "no SSLSocketFbctory specified");
        }

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkSetFbctory();
        }
        sslSocketFbctory = sf;
    }

    /**
     * Gets the SSL socket fbctory to be used when crebting sockets
     * for secure https URL connections.
     *
     * @return the <code>SSLSocketFbctory</code>
     * @see #setSSLSocketFbctory(SSLSocketFbctory)
     */
    public SSLSocketFbctory getSSLSocketFbctory() {
        return sslSocketFbctory;
    }
}
