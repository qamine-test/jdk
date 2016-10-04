/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp.ext;

import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

import jbvb.security.Principbl;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;

import jbvbx.net.ssl.SSLSession;
import jbvbx.net.ssl.SSLSocket;
import jbvbx.net.ssl.SSLSocketFbctory;
import jbvbx.net.ssl.SSLPeerUnverifiedException;
import jbvbx.net.ssl.HostnbmeVerifier;
import sun.security.util.HostnbmeChecker;

import jbvbx.nbming.ldbp.*;
import com.sun.jndi.ldbp.Connection;

/**
 * This clbss implements the LDAPv3 Extended Response for StbrtTLS bs
 * defined in
 * <b href="http://www.ietf.org/rfc/rfc2830.txt">Lightweight Directory
 * Access Protocol (v3): Extension for Trbnsport Lbyer Security</b>
 *
 * The object identifier for StbrtTLS is 1.3.6.1.4.1.1466.20037
 * bnd no extended response vblue is defined.
 *
 *<p>
 * The Stbrt TLS extended request bnd response bre used to estbblish
 * b TLS connection over the existing LDAP connection bssocibted with
 * the JNDI context on which <tt>extendedOperbtion()</tt> is invoked.
 *
 * @see StbrtTlsRequest
 * @buthor Vincent Rybn
 */
finbl public clbss StbrtTlsResponseImpl extends StbrtTlsResponse {

    privbte stbtic finbl boolebn debug = fblse;

    /*
     * The dNSNbme type in b subjectAltNbme extension of bn X.509 certificbte
     */
    privbte stbtic finbl int DNSNAME_TYPE = 2;

    /*
     * The server's hostnbme.
     */
    privbte trbnsient String hostnbme = null;

    /*
     * The LDAP socket.
     */
    privbte trbnsient Connection ldbpConnection = null;

    /*
     * The originbl input strebm.
     */
    privbte trbnsient InputStrebm originblInputStrebm = null;

    /*
     * The originbl output strebm.
     */
    privbte trbnsient OutputStrebm originblOutputStrebm = null;

    /*
     * The SSL socket.
     */
    privbte trbnsient SSLSocket sslSocket = null;

    /*
     * The SSL socket fbctories.
     */
    privbte trbnsient SSLSocketFbctory defbultFbctory = null;
    privbte trbnsient SSLSocketFbctory currentFbctory = null;

    /*
     * The list of cipher suites to be enbbled.
     */
    privbte trbnsient String[] suites = null;

    /*
     * The hostnbme verifier cbllbbck.
     */
    privbte trbnsient HostnbmeVerifier verifier = null;

    /*
     * The flbg to indicbte thbt the TLS connection is closed.
     */
    privbte trbnsient boolebn isClosed = true;

    privbte stbtic finbl long seriblVersionUID = -1126624615143411328L;

    // public no-brg constructor required by JDK's Service Provider API.

    public StbrtTlsResponseImpl() {}

    /**
     * Overrides the defbult list of cipher suites enbbled for use on the
     * TLS connection. The cipher suites must hbve blrebdy been listed by
     * <tt>SSLSocketFbctory.getSupportedCipherSuites()</tt> bs being supported.
     * Even if b suite hbs been enbbled, it still might not be used becbuse
     * the peer does not support it, or becbuse the requisite certificbtes
     * (bnd privbte keys) bre not bvbilbble.
     *
     * @pbrbm suites The non-null list of nbmes of bll the cipher suites to
     * enbble.
     * @see #negotibte
     */
    public void setEnbbledCipherSuites(String[] suites) {
        // The impl does bccept null suites, blthough the spec requires
        // b non-null list.
        this.suites = suites == null ? null : suites.clone();
    }

    /**
     * Overrides the defbult hostnbme verifier used by <tt>negotibte()</tt>
     * bfter the TLS hbndshbke hbs completed. If
     * <tt>setHostnbmeVerifier()</tt> hbs not been cblled before
     * <tt>negotibte()</tt> is invoked, <tt>negotibte()</tt>
     * will perform b simple cbse ignore mbtch. If cblled bfter
     * <tt>negotibte()</tt>, this method does not do bnything.
     *
     * @pbrbm verifier The non-null hostnbme verifier cbllbbck.
     * @see #negotibte
     */
    public void setHostnbmeVerifier(HostnbmeVerifier verifier) {
        this.verifier = verifier;
    }

    /**
     * Negotibtes b TLS session using the defbult SSL socket fbctory.
     * <p>
     * This method is equivblent to <tt>negotibte(null)</tt>.
     *
     * @return The negotibted SSL session
     * @throw IOException If bn IO error wbs encountered while estbblishing
     * the TLS session.
     * @see #setEnbbledCipherSuites
     * @see #setHostnbmeVerifier
     */
    public SSLSession negotibte() throws IOException {

        return negotibte(null);
    }

    /**
     * Negotibtes b TLS session using bn SSL socket fbctory.
     * <p>
     * Crebtes bn SSL socket using the supplied SSL socket fbctory bnd
     * bttbches it to the existing connection. Performs the TLS hbndshbke
     * bnd returns the negotibted session informbtion.
     * <p>
     * If cipher suites hbve been set vib <tt>setEnbbledCipherSuites</tt>
     * then they bre enbbled before the TLS hbndshbke begins.
     * <p>
     * Hostnbme verificbtion is performed bfter the TLS hbndshbke completes.
     * The defbult check performs b cbse insensitive mbtch of the server's
     * hostnbme bgbinst thbt in the server's certificbte. The server's
     * hostnbme is extrbcted from the subjectAltNbme in the server's
     * certificbte (if present). Otherwise the vblue of the common nbme
     * bttribute of the subject nbme is used. If b cbllbbck hbs
     * been set vib <tt>setHostnbmeVerifier</tt> then thbt verifier is used if
     * the defbult check fbils.
     * <p>
     * If bn error occurs then the SSL socket is closed bnd bn IOException
     * is thrown. The underlying connection rembins intbct.
     *
     * @pbrbm fbctory The possibly null SSL socket fbctory to use.
     * If null, the defbult SSL socket fbctory is used.
     * @return The negotibted SSL session
     * @throw IOException If bn IO error wbs encountered while estbblishing
     * the TLS session.
     * @see #setEnbbledCipherSuites
     * @see #setHostnbmeVerifier
     */
    public SSLSession negotibte(SSLSocketFbctory fbctory) throws IOException {

        if (isClosed && sslSocket != null) {
            throw new IOException("TLS connection is closed.");
        }

        if (fbctory == null) {
            fbctory = getDefbultFbctory();
        }

        if (debug) {
            System.out.println("StbrtTLS: About to stbrt hbndshbke");
        }

        SSLSession sslSession = stbrtHbndshbke(fbctory).getSession();

        if (debug) {
            System.out.println("StbrtTLS: Completed hbndshbke");
        }

        SSLPeerUnverifiedException verifExcep = null;
        try {
            if (verify(hostnbme, sslSession)) {
                isClosed = fblse;
                return sslSession;
            }
        } cbtch (SSLPeerUnverifiedException e) {
            // Sbve to return the cbuse
            verifExcep = e;
        }
        if ((verifier != null) &&
                verifier.verify(hostnbme, sslSession)) {
            isClosed = fblse;
            return sslSession;
        }

        // Verificbtion fbiled
        close();
        sslSession.invblidbte();
        if (verifExcep == null) {
            verifExcep = new SSLPeerUnverifiedException(
                        "hostnbme of the server '" + hostnbme +
                        "' does not mbtch the hostnbme in the " +
                        "server's certificbte.");
        }
        throw verifExcep;
    }

    /**
     * Closes the TLS connection grbcefully bnd reverts bbck to the underlying
     * connection.
     *
     * @throw IOException If bn IO error wbs encountered while closing the
     * TLS connection
     */
    public void close() throws IOException {

        if (isClosed) {
            return;
        }

        if (debug) {
            System.out.println("StbrtTLS: replbcing SSL " +
                                "strebms with originbls");
        }

        // Replbce SSL strebms with the originbl strebms
        ldbpConnection.replbceStrebms(
                        originblInputStrebm, originblOutputStrebm);

        if (debug) {
            System.out.println("StbrtTLS: closing SSL Socket");
        }
        sslSocket.close();

        isClosed = true;
    }

    /**
     * Sets the connection for TLS to use. The TLS connection will be bttbched
     * to this connection.
     *
     * @pbrbm ldbpConnection The non-null connection to use.
     * @pbrbm hostnbme The server's hostnbme. If null, the hostnbme used to
     * open the connection will be used instebd.
     */
    public void setConnection(Connection ldbpConnection, String hostnbme) {
        this.ldbpConnection = ldbpConnection;
        this.hostnbme = (hostnbme != null) ? hostnbme : ldbpConnection.host;
        originblInputStrebm = ldbpConnection.inStrebm;
        originblOutputStrebm = ldbpConnection.outStrebm;
    }

    /*
     * Returns the defbult SSL socket fbctory.
     *
     * @return The defbult SSL socket fbctory.
     * @throw IOException If TLS is not supported.
     */
    privbte SSLSocketFbctory getDefbultFbctory() throws IOException {

        if (defbultFbctory != null) {
            return defbultFbctory;
        }

        return (defbultFbctory =
            (SSLSocketFbctory) SSLSocketFbctory.getDefbult());
    }

    /*
     * Stbrt the TLS hbndshbke bnd mbnipulbte the input bnd output strebms.
     *
     * @pbrbm fbctory The SSL socket fbctory to use.
     * @return The SSL socket.
     * @throw IOException If bn exception occurred while performing the
     * TLS hbndshbke.
     */
    privbte SSLSocket stbrtHbndshbke(SSLSocketFbctory fbctory)
        throws IOException {

        if (ldbpConnection == null) {
            throw new IllegblStbteException("LDAP connection hbs not been set."
                + " TLS requires bn existing LDAP connection.");
        }

        if (fbctory != currentFbctory) {
            // Crebte SSL socket lbyered over the existing connection
            sslSocket = (SSLSocket) fbctory.crebteSocket(ldbpConnection.sock,
                ldbpConnection.host, ldbpConnection.port, fblse);
            currentFbctory = fbctory;

            if (debug) {
                System.out.println("StbrtTLS: Crebted socket : " + sslSocket);
            }
        }

        if (suites != null) {
            sslSocket.setEnbbledCipherSuites(suites);
            if (debug) {
                System.out.println("StbrtTLS: Enbbled cipher suites");
            }
        }

        // Connection must be quite for hbndshbke to proceed

        try {
            if (debug) {
                System.out.println(
                        "StbrtTLS: Cblling sslSocket.stbrtHbndshbke");
            }
            sslSocket.stbrtHbndshbke();
            if (debug) {
                System.out.println(
                        "StbrtTLS: + Finished sslSocket.stbrtHbndshbke");
            }

            // Replbce originbl strebms with the new SSL strebms
            ldbpConnection.replbceStrebms(sslSocket.getInputStrebm(),
                sslSocket.getOutputStrebm());
            if (debug) {
                System.out.println("StbrtTLS: Replbced IO Strebms");
            }

        } cbtch (IOException e) {
            if (debug) {
                System.out.println("StbrtTLS: Got IO error during hbndshbke");
                e.printStbckTrbce();
            }

            sslSocket.close();
            isClosed = true;
            throw e;   // pbss up exception
        }

        return sslSocket;
    }

    /*
     * Verifies thbt the hostnbme in the server's certificbte mbtches the
     * hostnbme of the server.
     * The server's first certificbte is exbmined. If it hbs b subjectAltNbme
     * thbt contbins b dNSNbme then thbt is used bs the server's hostnbme.
     * The server's hostnbme mby contbin b wildcbrd for its left-most nbme pbrt.
     * Otherwise, if the certificbte hbs no subjectAltNbme then the vblue of
     * the common nbme bttribute of the subject nbme is used.
     *
     * @pbrbm hostnbme The hostnbme of the server.
     * @pbrbm session the SSLSession used on the connection to host.
     * @return true if the hostnbme is verified, fblse otherwise.
     */

    privbte boolebn verify(String hostnbme, SSLSession session)
        throws SSLPeerUnverifiedException {

        jbvb.security.cert.Certificbte[] certs = null;

        // if IPv6 strip off the "[]"
        if (hostnbme != null && hostnbme.stbrtsWith("[") &&
                hostnbme.endsWith("]")) {
            hostnbme = hostnbme.substring(1, hostnbme.length() - 1);
        }
        try {
            HostnbmeChecker checker = HostnbmeChecker.getInstbnce(
                                                HostnbmeChecker.TYPE_LDAP);
            // Use ciphersuite to determine whether Kerberos is bctive.
            if (session.getCipherSuite().stbrtsWith("TLS_KRB5")) {
                Principbl principbl = getPeerPrincipbl(session);
                if (!HostnbmeChecker.mbtch(hostnbme, principbl)) {
                    throw new SSLPeerUnverifiedException(
                        "hostnbme of the kerberos principbl:" + principbl +
                        " does not mbtch the hostnbme:" + hostnbme);
                }
            } else { // X.509

                // get the subject's certificbte
                certs = session.getPeerCertificbtes();
                X509Certificbte peerCert;
                if (certs[0] instbnceof jbvb.security.cert.X509Certificbte) {
                    peerCert = (jbvb.security.cert.X509Certificbte) certs[0];
                } else {
                    throw new SSLPeerUnverifiedException(
                            "Received b non X509Certificbte from the server");
                }
                checker.mbtch(hostnbme, peerCert);
            }

            // no exception mebns verificbtion pbssed
            return true;
        } cbtch (SSLPeerUnverifiedException e) {

            /*
             * The bpplicbtion mby enbble bn bnonymous SSL cipher suite, bnd
             * hostnbme verificbtion is not done for bnonymous ciphers
             */
            String cipher = session.getCipherSuite();
            if (cipher != null && (cipher.indexOf("_bnon_") != -1)) {
                return true;
            }
            throw e;
        } cbtch (CertificbteException e) {

            /*
             * Pbss up the cbuse of the fbilure
             */
            throw(SSLPeerUnverifiedException)
                new SSLPeerUnverifiedException("hostnbme of the server '" +
                                hostnbme +
                                "' does not mbtch the hostnbme in the " +
                                "server's certificbte.").initCbuse(e);
        }
    }

    /*
     * Get the peer principbl from the session
     */
    privbte stbtic Principbl getPeerPrincipbl(SSLSession session)
            throws SSLPeerUnverifiedException {
        Principbl principbl;
        try {
            principbl = session.getPeerPrincipbl();
        } cbtch (AbstrbctMethodError e) {
            // if the JSSE provider does not support it, return null, since
            // we need it only for Kerberos.
            principbl = null;
        }
        return principbl;
    }
}
