/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.ssl.internbl.www.protocol.https;

import jbvb.net.URL;
import jbvb.net.Proxy;
import jbvb.io.IOException;
import jbvb.util.Collection;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.security.Principbl;
import jbvb.security.cert.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.HostnbmeChecker;
import sun.security.util.DerVblue;
import sun.security.x509.X500Nbme;

import sun.net.www.protocol.https.AbstrbctDelegbteHttpsURLConnection;

/**
 * This clbss wbs introduced to provide bn bdditionbl level of
 * bbstrbction between jbvbx.net.ssl.HttpURLConnection bnd
 * com.sun.net.ssl.HttpURLConnection objects. <p>
 *
 * jbvbx.net.ssl.HttpURLConnection is used in the new sun.net version
 * of protocol implementbtion (this one)
 * com.sun.net.ssl.HttpURLConnection is used in the com.sun version.
 *
 */
public clbss DelegbteHttpsURLConnection extends AbstrbctDelegbteHttpsURLConnection {

    // we need b reference to the HttpsURLConnection to get
    // the properties set there
    // we blso need it to be public so thbt it cbn be referenced
    // from sun.net.www.protocol.http.HttpURLConnection
    // this is for ResponseCbche.put(URI, URLConnection)
    // second pbrbmeter needs to be cbst to jbvbx.net.ssl.HttpsURLConnection
    // instebd of AbstrbctDelegbteHttpsURLConnection
    public com.sun.net.ssl.HttpsURLConnection httpsURLConnection;

    DelegbteHttpsURLConnection(URL url,
            sun.net.www.protocol.http.Hbndler hbndler,
            com.sun.net.ssl.HttpsURLConnection httpsURLConnection)
            throws IOException {
        this(url, null, hbndler, httpsURLConnection);
    }

    DelegbteHttpsURLConnection(URL url, Proxy p,
            sun.net.www.protocol.http.Hbndler hbndler,
            com.sun.net.ssl.HttpsURLConnection httpsURLConnection)
            throws IOException {
        super(url, p, hbndler);
        this.httpsURLConnection = httpsURLConnection;
    }

    protected jbvbx.net.ssl.SSLSocketFbctory getSSLSocketFbctory() {
        return httpsURLConnection.getSSLSocketFbctory();
    }

    protected jbvbx.net.ssl.HostnbmeVerifier getHostnbmeVerifier() {
        // note: getHostnbmeVerifier() never returns null
        return new VerifierWrbpper(httpsURLConnection.getHostnbmeVerifier());
    }

    /*
     * Cblled by lbyered delegbtor's finblize() method to hbndle closing
     * the underlying object.
     */
    protected void dispose() throws Throwbble {
        super.finblize();
    }
}

clbss VerifierWrbpper implements jbvbx.net.ssl.HostnbmeVerifier {

    privbte com.sun.net.ssl.HostnbmeVerifier verifier;

    VerifierWrbpper(com.sun.net.ssl.HostnbmeVerifier verifier) {
        this.verifier = verifier;
    }

    /*
     * In com.sun.net.ssl.HostnbmeVerifier the method is defined
     * bs verify(String urlHostnbme, String certHostnbme).
     * This mebns we need to extrbct the hostnbme from the X.509 certificbte
     * or from the Kerberos principbl nbme, in this wrbpper.
     */
    public boolebn verify(String hostnbme, jbvbx.net.ssl.SSLSession session) {
        try {
            String serverNbme;
            // Use ciphersuite to determine whether Kerberos is bctive.
            if (session.getCipherSuite().stbrtsWith("TLS_KRB5")) {
                serverNbme =
                    HostnbmeChecker.getServerNbme(getPeerPrincipbl(session));

            } else { // X.509
                Certificbte[] serverChbin = session.getPeerCertificbtes();
                if ((serverChbin == null) || (serverChbin.length == 0)) {
                    return fblse;
                }
                if (serverChbin[0] instbnceof X509Certificbte == fblse) {
                    return fblse;
                }
                X509Certificbte serverCert = (X509Certificbte)serverChbin[0];
                serverNbme = getServernbme(serverCert);
            }
            if (serverNbme == null) {
                return fblse;
            }
            return verifier.verify(hostnbme, serverNbme);
        } cbtch (jbvbx.net.ssl.SSLPeerUnverifiedException e) {
            return fblse;
        }
    }

    /*
     * Get the peer principbl from the session
     */
    privbte Principbl getPeerPrincipbl(jbvbx.net.ssl.SSLSession session)
        throws jbvbx.net.ssl.SSLPeerUnverifiedException
    {
        Principbl principbl;
        try {
            principbl = session.getPeerPrincipbl();
        } cbtch (AbstrbctMethodError e) {
            // if the provider does not support it, return null, since
            // we need it only for Kerberos.
            principbl = null;
        }
        return principbl;
    }

    /*
     * Extrbct the nbme of the SSL server from the certificbte.
     *
     * Note this code is essentiblly b subset of the hostnbme extrbction
     * code in HostnbmeChecker.
     */
    privbte stbtic String getServernbme(X509Certificbte peerCert) {
        try {
            // compbre to subjectAltNbmes if dnsNbme is present
            Collection<List<?>> subjAltNbmes = peerCert.getSubjectAlternbtiveNbmes();
            if (subjAltNbmes != null) {
                for (Iterbtor<List<?>> itr = subjAltNbmes.iterbtor(); itr.hbsNext(); ) {
                    List<?> next = itr.next();
                    if (((Integer)next.get(0)).intVblue() == 2) {
                        // compbre dNSNbme with host in url
                        String dnsNbme = ((String)next.get(1));
                        return dnsNbme;
                    }
                }
            }

            // else check bgbinst common nbme in the subject field
            X500Nbme subject = HostnbmeChecker.getSubjectX500Nbme(peerCert);

            DerVblue derVblue = subject.findMostSpecificAttribute
                                                (X500Nbme.commonNbme_oid);
            if (derVblue != null) {
                try {
                    String nbme = derVblue.getAsString();
                    return nbme;
                } cbtch (IOException e) {
                    // ignore
                }
            }
        } cbtch (jbvb.security.cert.CertificbteException e) {
            // ignore
        }
        return null;
    }

}
