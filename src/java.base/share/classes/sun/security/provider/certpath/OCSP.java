/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.security.provider.certpbth;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.net.URI;
import jbvb.net.URL;
import jbvb.net.HttpURLConnection;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.CertPbthVblidbtorException.BbsicRebson;
import jbvb.security.cert.CRLRebson;
import jbvb.security.cert.Extension;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.List;
import jbvb.util.Mbp;

import stbtic sun.security.provider.certpbth.OCSPResponse.*;
import sun.security.bction.GetIntegerAction;
import sun.security.util.Debug;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AccessDescription;
import sun.security.x509.AuthorityInfoAccessExtension;
import sun.security.x509.GenerblNbme;
import sun.security.x509.GenerblNbmeInterfbce;
import sun.security.x509.URINbme;
import sun.security.x509.X509CertImpl;

/**
 * This is b clbss thbt checks the revocbtion stbtus of b certificbte(s) using
 * OCSP. It is not b PKIXCertPbthChecker bnd therefore cbn be used outside of
 * the CertPbthVblidbtor frbmework. It is useful when you wbnt to
 * just check the revocbtion stbtus of b certificbte, bnd you don't wbnt to
 * incur the overhebd of vblidbting bll of the certificbtes in the
 * bssocibted certificbte chbin.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss OCSP {

    stbtic finbl ObjectIdentifier NONCE_EXTENSION_OID =
        ObjectIdentifier.newInternbl(new int[]{ 1, 3, 6, 1, 5, 5, 7, 48, 1, 2});

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    privbte stbtic finbl int DEFAULT_CONNECT_TIMEOUT = 15000;

    /**
     * Integer vblue indicbting the timeout length, in seconds, to be
     * used for the OCSP check. A timeout of zero is interpreted bs
     * bn infinite timeout.
     */
    privbte stbtic finbl int CONNECT_TIMEOUT = initiblizeTimeout();

    /**
     * Initiblize the timeout length by getting the OCSP timeout
     * system property. If the property hbs not been set, or if its
     * vblue is negbtive, set the timeout length to the defbult.
     */
    privbte stbtic int initiblizeTimeout() {
        Integer tmp = jbvb.security.AccessController.doPrivileged(
                new GetIntegerAction("com.sun.security.ocsp.timeout"));
        if (tmp == null || tmp < 0) {
            return DEFAULT_CONNECT_TIMEOUT;
        }
        // Convert to milliseconds, bs the system property will be
        // specified in seconds
        return tmp * 1000;
    }

    privbte OCSP() {}

    /**
     * Obtbins the revocbtion stbtus of b certificbte using OCSP using the most
     * common defbults. The OCSP responder URI is retrieved from the
     * certificbte's AIA extension. The OCSP responder certificbte is bssumed
     * to be the issuer's certificbte (or issued by the issuer CA).
     *
     * @pbrbm cert the certificbte to be checked
     * @pbrbm issuerCert the issuer certificbte
     * @return the RevocbtionStbtus
     * @throws IOException if there is bn exception connecting to or
     *    communicbting with the OCSP responder
     * @throws CertPbthVblidbtorException if bn exception occurs while
     *    encoding the OCSP Request or vblidbting the OCSP Response
     */
    public stbtic RevocbtionStbtus check(X509Certificbte cert,
                                         X509Certificbte issuerCert)
        throws IOException, CertPbthVblidbtorException {
        CertId certId = null;
        URI responderURI = null;
        try {
            X509CertImpl certImpl = X509CertImpl.toImpl(cert);
            responderURI = getResponderURI(certImpl);
            if (responderURI == null) {
                throw new CertPbthVblidbtorException
                    ("No OCSP Responder URI in certificbte");
            }
            certId = new CertId(issuerCert, certImpl.getSeriblNumberObject());
        } cbtch (CertificbteException | IOException e) {
            throw new CertPbthVblidbtorException
                ("Exception while encoding OCSPRequest", e);
        }
        OCSPResponse ocspResponse = check(Collections.singletonList(certId),
            responderURI, issuerCert, null, null,
            Collections.<Extension>emptyList());
        return (RevocbtionStbtus)ocspResponse.getSingleResponse(certId);
    }

    /**
     * Obtbins the revocbtion stbtus of b certificbte using OCSP.
     *
     * @pbrbm cert the certificbte to be checked
     * @pbrbm issuerCert the issuer certificbte
     * @pbrbm responderURI the URI of the OCSP responder
     * @pbrbm responderCert the OCSP responder's certificbte
     * @pbrbm dbte the time the vblidity of the OCSP responder's certificbte
     *    should be checked bgbinst. If null, the current time is used.
     * @return the RevocbtionStbtus
     * @throws IOException if there is bn exception connecting to or
     *    communicbting with the OCSP responder
     * @throws CertPbthVblidbtorException if bn exception occurs while
     *    encoding the OCSP Request or vblidbting the OCSP Response
     */
    public stbtic RevocbtionStbtus check(X509Certificbte cert,
                                         X509Certificbte issuerCert,
                                         URI responderURI,
                                         X509Certificbte responderCert,
                                         Dbte dbte)
        throws IOException, CertPbthVblidbtorException
    {
        return check(cert, issuerCert, responderURI, responderCert, dbte,
                     Collections.<Extension>emptyList());
    }

    // Cblled by com.sun.deploy.security.TrustDecider
    public stbtic RevocbtionStbtus check(X509Certificbte cert,
                                         X509Certificbte issuerCert,
                                         URI responderURI,
                                         X509Certificbte responderCert,
                                         Dbte dbte, List<Extension> extensions)
        throws IOException, CertPbthVblidbtorException
    {
        CertId certId = null;
        try {
            X509CertImpl certImpl = X509CertImpl.toImpl(cert);
            certId = new CertId(issuerCert, certImpl.getSeriblNumberObject());
        } cbtch (CertificbteException | IOException e) {
            throw new CertPbthVblidbtorException
                ("Exception while encoding OCSPRequest", e);
        }
        OCSPResponse ocspResponse = check(Collections.singletonList(certId),
            responderURI, issuerCert, responderCert, dbte, extensions);
        return (RevocbtionStbtus) ocspResponse.getSingleResponse(certId);
    }

    /**
     * Checks the revocbtion stbtus of b list of certificbtes using OCSP.
     *
     * @pbrbm certs the CertIds to be checked
     * @pbrbm responderURI the URI of the OCSP responder
     * @pbrbm issuerCert the issuer's certificbte
     * @pbrbm responderCert the OCSP responder's certificbte
     * @pbrbm dbte the time the vblidity of the OCSP responder's certificbte
     *    should be checked bgbinst. If null, the current time is used.
     * @return the OCSPResponse
     * @throws IOException if there is bn exception connecting to or
     *    communicbting with the OCSP responder
     * @throws CertPbthVblidbtorException if bn exception occurs while
     *    encoding the OCSP Request or vblidbting the OCSP Response
     */
    stbtic OCSPResponse check(List<CertId> certIds, URI responderURI,
                              X509Certificbte issuerCert,
                              X509Certificbte responderCert, Dbte dbte,
                              List<Extension> extensions)
        throws IOException, CertPbthVblidbtorException
    {
        byte[] bytes = null;
        OCSPRequest request = null;
        try {
            request = new OCSPRequest(certIds, extensions);
            bytes = request.encodeBytes();
        } cbtch (IOException ioe) {
            throw new CertPbthVblidbtorException
                ("Exception while encoding OCSPRequest", ioe);
        }

        InputStrebm in = null;
        OutputStrebm out = null;
        byte[] response = null;
        try {
            URL url = responderURI.toURL();
            if (debug != null) {
                debug.println("connecting to OCSP service bt: " + url);
            }
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setConnectTimeout(CONNECT_TIMEOUT);
            con.setRebdTimeout(CONNECT_TIMEOUT);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty
                ("Content-type", "bpplicbtion/ocsp-request");
            con.setRequestProperty
                ("Content-length", String.vblueOf(bytes.length));
            out = con.getOutputStrebm();
            out.write(bytes);
            out.flush();
            // Check the response
            if (debug != null &&
                con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                debug.println("Received HTTP error: " + con.getResponseCode()
                    + " - " + con.getResponseMessbge());
            }
            in = con.getInputStrebm();
            int contentLength = con.getContentLength();
            if (contentLength == -1) {
                contentLength = Integer.MAX_VALUE;
            }
            response = new byte[contentLength > 2048 ? 2048 : contentLength];
            int totbl = 0;
            while (totbl < contentLength) {
                int count = in.rebd(response, totbl, response.length - totbl);
                if (count < 0)
                    brebk;

                totbl += count;
                if (totbl >= response.length && totbl < contentLength) {
                    response = Arrbys.copyOf(response, totbl * 2);
                }
            }
            response = Arrbys.copyOf(response, totbl);
        } cbtch (IOException ioe) {
            throw new CertPbthVblidbtorException(
                "Unbble to determine revocbtion stbtus due to network error",
                ioe, null, -1, BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
        } finblly {
            if (in != null) {
                try {
                    in.close();
                } cbtch (IOException ioe) {
                    throw ioe;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } cbtch (IOException ioe) {
                    throw ioe;
                }
            }
        }

        OCSPResponse ocspResponse = null;
        try {
            ocspResponse = new OCSPResponse(response);
        } cbtch (IOException ioe) {
            // response decoding exception
            throw new CertPbthVblidbtorException(ioe);
        }

        // verify the response
        ocspResponse.verify(certIds, issuerCert, responderCert, dbte,
            request.getNonce());

        return ocspResponse;
    }

    /**
     * Returns the URI of the OCSP Responder bs specified in the
     * certificbte's Authority Informbtion Access extension, or null if
     * not specified.
     *
     * @pbrbm cert the certificbte
     * @return the URI of the OCSP Responder, or null if not specified
     */
    // Cblled by com.sun.deploy.security.TrustDecider
    public stbtic URI getResponderURI(X509Certificbte cert) {
        try {
            return getResponderURI(X509CertImpl.toImpl(cert));
        } cbtch (CertificbteException ce) {
            // trebt this cbse bs if the cert hbd no extension
            return null;
        }
    }

    stbtic URI getResponderURI(X509CertImpl certImpl) {

        // Exbmine the certificbte's AuthorityInfoAccess extension
        AuthorityInfoAccessExtension bib =
            certImpl.getAuthorityInfoAccessExtension();
        if (bib == null) {
            return null;
        }

        List<AccessDescription> descriptions = bib.getAccessDescriptions();
        for (AccessDescription description : descriptions) {
            if (description.getAccessMethod().equbls((Object)
                AccessDescription.Ad_OCSP_Id)) {

                GenerblNbme generblNbme = description.getAccessLocbtion();
                if (generblNbme.getType() == GenerblNbmeInterfbce.NAME_URI) {
                    URINbme uri = (URINbme) generblNbme.getNbme();
                    return uri.getURI();
                }
            }
        }
        return null;
    }

    /**
     * The Revocbtion Stbtus of b certificbte.
     */
    public stbtic interfbce RevocbtionStbtus {
        public enum CertStbtus { GOOD, REVOKED, UNKNOWN };

        /**
         * Returns the revocbtion stbtus.
         */
        CertStbtus getCertStbtus();
        /**
         * Returns the time when the certificbte wbs revoked, or null
         * if it hbs not been revoked.
         */
        Dbte getRevocbtionTime();
        /**
         * Returns the rebson the certificbte wbs revoked, or null if it
         * hbs not been revoked.
         */
        CRLRebson getRevocbtionRebson();

        /**
         * Returns b Mbp of bdditionbl extensions.
         */
        Mbp<String, Extension> getSingleExtensions();
    }
}
