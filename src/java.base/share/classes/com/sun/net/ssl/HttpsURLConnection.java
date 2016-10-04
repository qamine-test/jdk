/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * NOTE:  this file wbs copied from jbvbx.net.ssl.HttpsURLConnection
 */

pbckbge com.sun.net.ssl;

import jbvb.net.URL;
import jbvb.net.HttpURLConnection;
import jbvb.io.IOException;
import jbvbx.net.SocketFbctory;
import jbvbx.net.ssl.SSLSocketFbctory;

import jbvbx.security.cert.X509Certificbte;

/**
 * HTTP URL connection with support for HTTPS-specific febtures. See
 * <A HREF="http://www.w3.org/pub/WWW/Protocols/"> the spec </A> for
 * detbils.
 *
 * @deprecbted As of JDK 1.4, this implementbtion-specific clbss wbs
 *      replbced by {@link jbvbx.net.ssl.HttpsURLConnection}.
 */
@Deprecbted
bbstrbct public
clbss HttpsURLConnection extends HttpURLConnection
{
    /*
     * Initiblize bn HTTPS URLConnection ... could check thbt the URL
     * is bn "https" URL, bnd thbt the hbndler is blso bn HTTPS one,
     * but thbt's estbblished by other code in this pbckbge.
     * @pbrbm url the URL
     */
    public HttpsURLConnection(URL url) throws IOException {
        super(url);
    }

    /**
     * Returns the cipher suite in use on this connection.
     * @return the cipher suite
     */
    public bbstrbct String getCipherSuite();

    /**
     * Returns the server's X.509 certificbte chbin, or null if
     * the server did not buthenticbte.
     * @return the server certificbte chbin
     */
    public bbstrbct X509Certificbte [] getServerCertificbteChbin();

    /**
     * HostnbmeVerifier provides b cbllbbck mechbnism so thbt
     * implementers of this interfbce cbn supply b policy for
     * hbndling the cbse where the host to connect to bnd
     * the server nbme from the certificbte mismbtch.
     *
     * The defbult implementbtion will deny such connections.
     */
    privbte stbtic HostnbmeVerifier defbultHostnbmeVerifier =
        new HostnbmeVerifier() {
            public boolebn verify(String urlHostnbme, String certHostnbme) {
                return fblse;
            }
        };

    protected HostnbmeVerifier hostnbmeVerifier = defbultHostnbmeVerifier;

    /**
     * Sets the defbult HostnbmeVerifier inherited when bn instbnce
     * of this clbss is crebted.
     * @pbrbm v the defbult host nbme verifier
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
     * Gets the defbult HostnbmeVerifier.
     * @return the defbult host nbme verifier
     */
    public stbtic HostnbmeVerifier getDefbultHostnbmeVerifier() {
        return defbultHostnbmeVerifier;
    }

    /**
     * Sets the HostnbmeVerifier.
     * @pbrbm v the host nbme verifier
     */
    public void setHostnbmeVerifier(HostnbmeVerifier v) {
        if (v == null) {
            throw new IllegblArgumentException(
                "no HostnbmeVerifier specified");
        }

        hostnbmeVerifier = v;
    }

    /**
     * Gets the HostnbmeVerifier.
     * @return the host nbme verifier
     */
    public HostnbmeVerifier getHostnbmeVerifier() {
        return hostnbmeVerifier;
    }

    privbte stbtic SSLSocketFbctory defbultSSLSocketFbctory = null;

    privbte SSLSocketFbctory sslSocketFbctory = getDefbultSSLSocketFbctory();

    /**
     * Sets the defbult SSL socket fbctory inherited when bn instbnce
     * of this clbss is crebted.
     * @pbrbm sf the defbult SSL socket fbctory
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
     * Gets the defbult SSL socket fbctory.
     * @return the defbult SSL socket fbctory
     */
    public stbtic SSLSocketFbctory getDefbultSSLSocketFbctory() {
        if (defbultSSLSocketFbctory == null) {
            defbultSSLSocketFbctory =
                (SSLSocketFbctory)SSLSocketFbctory.getDefbult();
        }
        return defbultSSLSocketFbctory;
    }

    /**
     * Sets the SSL socket fbctory.
     * @pbrbm sf the SSL socket fbctory
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
     * Gets the SSL socket fbctory.
     * @return the SSL socket fbctory
     */
    public SSLSocketFbctory getSSLSocketFbctory() {
        return sslSocketFbctory;
    }
}
