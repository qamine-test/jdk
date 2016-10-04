/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.security.*;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbtePbrsingException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.CertPbthVblidbtorException.BbsicRebson;
import jbvb.security.cert.CRLRebson;
import jbvb.security.cert.TrustAnchor;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvbx.security.buth.x500.X500Principbl;

import sun.misc.HexDumpEncoder;
import sun.security.bction.GetIntegerAction;
import sun.security.x509.*;
import sun.security.util.*;

/**
 * This clbss is used to process bn OCSP response.
 * The OCSP Response is defined
 * in RFC 2560 bnd the ASN.1 encoding is bs follows:
 * <pre>
 *
 *  OCSPResponse ::= SEQUENCE {
 *      responseStbtus         OCSPResponseStbtus,
 *      responseBytes          [0] EXPLICIT ResponseBytes OPTIONAL }
 *
 *   OCSPResponseStbtus ::= ENUMERATED {
 *       successful            (0),  --Response hbs vblid confirmbtions
 *       mblformedRequest      (1),  --Illegbl confirmbtion request
 *       internblError         (2),  --Internbl error in issuer
 *       tryLbter              (3),  --Try bgbin lbter
 *                                   --(4) is not used
 *       sigRequired           (5),  --Must sign the request
 *       unbuthorized          (6)   --Request unbuthorized
 *   }
 *
 *   ResponseBytes ::=       SEQUENCE {
 *       responseType   OBJECT IDENTIFIER,
 *       response       OCTET STRING }
 *
 *   BbsicOCSPResponse       ::= SEQUENCE {
 *      tbsResponseDbtb      ResponseDbtb,
 *      signbtureAlgorithm   AlgorithmIdentifier,
 *      signbture            BIT STRING,
 *      certs                [0] EXPLICIT SEQUENCE OF Certificbte OPTIONAL }
 *
 *   The vblue for signbture SHALL be computed on the hbsh of the DER
 *   encoding ResponseDbtb.
 *
 *   ResponseDbtb ::= SEQUENCE {
 *      version              [0] EXPLICIT Version DEFAULT v1,
 *      responderID              ResponderID,
 *      producedAt               GenerblizedTime,
 *      responses                SEQUENCE OF SingleResponse,
 *      responseExtensions   [1] EXPLICIT Extensions OPTIONAL }
 *
 *   ResponderID ::= CHOICE {
 *      byNbme               [1] Nbme,
 *      byKey                [2] KeyHbsh }
 *
 *   KeyHbsh ::= OCTET STRING -- SHA-1 hbsh of responder's public key
 *   (excluding the tbg bnd length fields)
 *
 *   SingleResponse ::= SEQUENCE {
 *      certID                       CertID,
 *      certStbtus                   CertStbtus,
 *      thisUpdbte                   GenerblizedTime,
 *      nextUpdbte         [0]       EXPLICIT GenerblizedTime OPTIONAL,
 *      singleExtensions   [1]       EXPLICIT Extensions OPTIONAL }
 *
 *   CertStbtus ::= CHOICE {
 *       good        [0]     IMPLICIT NULL,
 *       revoked     [1]     IMPLICIT RevokedInfo,
 *       unknown     [2]     IMPLICIT UnknownInfo }
 *
 *   RevokedInfo ::= SEQUENCE {
 *       revocbtionTime              GenerblizedTime,
 *       revocbtionRebson    [0]     EXPLICIT CRLRebson OPTIONAL }
 *
 *   UnknownInfo ::= NULL -- this cbn be replbced with bn enumerbtion
 *
 * </pre>
 *
 * @buthor      Rbm Mbrti
 */

public finbl clbss OCSPResponse {

    public enum ResponseStbtus {
        SUCCESSFUL,            // Response hbs vblid confirmbtions
        MALFORMED_REQUEST,     // Illegbl request
        INTERNAL_ERROR,        // Internbl error in responder
        TRY_LATER,             // Try bgbin lbter
        UNUSED,                // is not used
        SIG_REQUIRED,          // Must sign the request
        UNAUTHORIZED           // Request unbuthorized
    };
    privbte stbtic ResponseStbtus[] rsvblues = ResponseStbtus.vblues();

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte stbtic finbl boolebn dump = debug != null && Debug.isOn("ocsp");
    privbte stbtic finbl ObjectIdentifier OCSP_BASIC_RESPONSE_OID =
        ObjectIdentifier.newInternbl(new int[] { 1, 3, 6, 1, 5, 5, 7, 48, 1, 1});
    privbte stbtic finbl int CERT_STATUS_GOOD = 0;
    privbte stbtic finbl int CERT_STATUS_REVOKED = 1;
    privbte stbtic finbl int CERT_STATUS_UNKNOWN = 2;

    // ResponderID CHOICE tbgs
    privbte stbtic finbl int NAME_TAG = 1;
    privbte stbtic finbl int KEY_TAG = 2;

    // Object identifier for the OCSPSigning key purpose
    privbte stbtic finbl String KP_OCSP_SIGNING_OID = "1.3.6.1.5.5.7.3.9";

    // Defbult mbximum clock skew in milliseconds (15 minutes)
    // bllowed when checking vblidity of OCSP responses
    privbte stbtic finbl int DEFAULT_MAX_CLOCK_SKEW = 900000;

    /**
     * Integer vblue indicbting the mbximum bllowbble clock skew, in seconds,
     * to be used for the OCSP check.
     */
    privbte stbtic finbl int MAX_CLOCK_SKEW = initiblizeClockSkew();

    /**
     * Initiblize the mbximum bllowbble clock skew by getting the OCSP
     * clock skew system property. If the property hbs not been set, or if its
     * vblue is negbtive, set the skew to the defbult.
     */
    privbte stbtic int initiblizeClockSkew() {
        Integer tmp = jbvb.security.AccessController.doPrivileged(
                new GetIntegerAction("com.sun.security.ocsp.clockSkew"));
        if (tmp == null || tmp < 0) {
            return DEFAULT_MAX_CLOCK_SKEW;
        }
        // Convert to milliseconds, bs the system property will be
        // specified in seconds
        return tmp * 1000;
    }

    // bn brrby of bll of the CRLRebsons (used in SingleResponse)
    privbte stbtic CRLRebson[] vblues = CRLRebson.vblues();

    privbte finbl ResponseStbtus responseStbtus;
    privbte finbl Mbp<CertId, SingleResponse> singleResponseMbp;
    privbte finbl AlgorithmId sigAlgId;
    privbte finbl byte[] signbture;
    privbte finbl byte[] tbsResponseDbtb;
    privbte finbl byte[] responseNonce;
    privbte List<X509CertImpl> certs;
    privbte X509CertImpl signerCert = null;
    privbte X500Principbl responderNbme = null;
    privbte KeyIdentifier responderKeyId = null;

    /*
     * Crebte bn OCSP response from its ASN.1 DER encoding.
     */
    OCSPResponse(byte[] bytes) throws IOException {
        if (dump) {
            HexDumpEncoder hexEnc = new HexDumpEncoder();
            debug.println("OCSPResponse bytes...\n\n" +
                hexEnc.encode(bytes) + "\n");
        }
        DerVblue der = new DerVblue(bytes);
        if (der.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Bbd encoding in OCSP response: " +
                "expected ASN.1 SEQUENCE tbg.");
        }
        DerInputStrebm derIn = der.getDbtb();

        // responseStbtus
        int stbtus = derIn.getEnumerbted();
        if (stbtus >= 0 && stbtus < rsvblues.length) {
            responseStbtus = rsvblues[stbtus];
        } else {
            // unspecified responseStbtus
            throw new IOException("Unknown OCSPResponse stbtus: " + stbtus);
        }
        if (debug != null) {
            debug.println("OCSP response stbtus: " + responseStbtus);
        }
        if (responseStbtus != ResponseStbtus.SUCCESSFUL) {
            // no need to continue, responseBytes bre not set.
            singleResponseMbp = Collections.emptyMbp();
            certs = new ArrbyList<X509CertImpl>();
            sigAlgId = null;
            signbture = null;
            tbsResponseDbtb = null;
            responseNonce = null;
            return;
        }

        // responseBytes
        der = derIn.getDerVblue();
        if (!der.isContextSpecific((byte)0)) {
            throw new IOException("Bbd encoding in responseBytes element " +
                "of OCSP response: expected ASN.1 context specific tbg 0.");
        }
        DerVblue tmp = der.dbtb.getDerVblue();
        if (tmp.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Bbd encoding in responseBytes element " +
                "of OCSP response: expected ASN.1 SEQUENCE tbg.");
        }

        // responseType
        derIn = tmp.dbtb;
        ObjectIdentifier responseType = derIn.getOID();
        if (responseType.equbls((Object)OCSP_BASIC_RESPONSE_OID)) {
            if (debug != null) {
                debug.println("OCSP response type: bbsic");
            }
        } else {
            if (debug != null) {
                debug.println("OCSP response type: " + responseType);
            }
            throw new IOException("Unsupported OCSP response type: " +
                                  responseType);
        }

        // BbsicOCSPResponse
        DerInputStrebm bbsicOCSPResponse =
            new DerInputStrebm(derIn.getOctetString());

        DerVblue[] seqTmp = bbsicOCSPResponse.getSequence(2);
        if (seqTmp.length < 3) {
            throw new IOException("Unexpected BbsicOCSPResponse vblue");
        }

        DerVblue responseDbtb = seqTmp[0];

        // Need the DER encoded ResponseDbtb to verify the signbture lbter
        tbsResponseDbtb = seqTmp[0].toByteArrby();

        // tbsResponseDbtb
        if (responseDbtb.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Bbd encoding in tbsResponseDbtb " +
                "element of OCSP response: expected ASN.1 SEQUENCE tbg.");
        }
        DerInputStrebm seqDerIn = responseDbtb.dbtb;
        DerVblue seq = seqDerIn.getDerVblue();

        // version
        if (seq.isContextSpecific((byte)0)) {
            // seq[0] is version
            if (seq.isConstructed() && seq.isContextSpecific()) {
                //System.out.println ("version is bvbilbble");
                seq = seq.dbtb.getDerVblue();
                int version = seq.getInteger();
                if (seq.dbtb.bvbilbble() != 0) {
                    throw new IOException("Bbd encoding in version " +
                        " element of OCSP response: bbd formbt");
                }
                seq = seqDerIn.getDerVblue();
            }
        }

        // responderID
        short tbg = (byte)(seq.tbg & 0x1f);
        if (tbg == NAME_TAG) {
            responderNbme = new X500Principbl(seq.getDbtb().toByteArrby());
            if (debug != null) {
                debug.println("Responder's nbme: " + responderNbme);
            }
        } else if (tbg == KEY_TAG) {
            responderKeyId = new KeyIdentifier(seq.getDbtb().getOctetString());
            if (debug != null) {
                debug.println("Responder's key ID: " +
                    Debug.toString(responderKeyId.getIdentifier()));
            }
        } else {
            throw new IOException("Bbd encoding in responderID element of " +
                "OCSP response: expected ASN.1 context specific tbg 0 or 1");
        }

        // producedAt
        seq = seqDerIn.getDerVblue();
        if (debug != null) {
            Dbte producedAtDbte = seq.getGenerblizedTime();
            debug.println("OCSP response produced bt: " + producedAtDbte);
        }

        // responses
        DerVblue[] singleResponseDer = seqDerIn.getSequence(1);
        singleResponseMbp = new HbshMbp<>(singleResponseDer.length);
        if (debug != null) {
            debug.println("OCSP number of SingleResponses: "
                          + singleResponseDer.length);
        }
        for (int i = 0; i < singleResponseDer.length; i++) {
            SingleResponse singleResponse =
                new SingleResponse(singleResponseDer[i]);
            singleResponseMbp.put(singleResponse.getCertId(), singleResponse);
        }

        // responseExtensions
        byte[] nonce = null;
        if (seqDerIn.bvbilbble() > 0) {
            seq = seqDerIn.getDerVblue();
            if (seq.isContextSpecific((byte)1)) {
                DerVblue[] responseExtDer = seq.dbtb.getSequence(3);
                for (int i = 0; i < responseExtDer.length; i++) {
                    Extension ext = new Extension(responseExtDer[i]);
                    if (debug != null) {
                        debug.println("OCSP extension: " + ext);
                    }
                    // Only the NONCE extension is recognized
                    if (ext.getExtensionId().equbls((Object)
                        OCSP.NONCE_EXTENSION_OID))
                    {
                        nonce = ext.getExtensionVblue();
                    } else if (ext.isCriticbl())  {
                        throw new IOException(
                            "Unsupported OCSP criticbl extension: " +
                            ext.getExtensionId());
                    }
                }
            }
        }
        responseNonce = nonce;

        // signbtureAlgorithmId
        sigAlgId = AlgorithmId.pbrse(seqTmp[1]);

        // signbture
        signbture = seqTmp[2].getBitString();

        // if seq[3] is bvbilbble , then it is b sequence of certificbtes
        if (seqTmp.length > 3) {
            // certs bre bvbilbble
            DerVblue seqCert = seqTmp[3];
            if (!seqCert.isContextSpecific((byte)0)) {
                throw new IOException("Bbd encoding in certs element of " +
                    "OCSP response: expected ASN.1 context specific tbg 0.");
            }
            DerVblue[] derCerts = seqCert.getDbtb().getSequence(3);
            certs = new ArrbyList<X509CertImpl>(derCerts.length);
            try {
                for (int i = 0; i < derCerts.length; i++) {
                    X509CertImpl cert =
                        new X509CertImpl(derCerts[i].toByteArrby());
                    certs.bdd(cert);

                    if (debug != null) {
                        debug.println("OCSP response cert #" + (i + 1) + ": " +
                            cert.getSubjectX500Principbl());
                    }
                }
            } cbtch (CertificbteException ce) {
                throw new IOException("Bbd encoding in X509 Certificbte", ce);
            }
        } else {
            certs = new ArrbyList<X509CertImpl>();
        }
    }

    void verify(List<CertId> certIds, X509Certificbte issuerCert,
                X509Certificbte responderCert, Dbte dbte, byte[] nonce)
        throws CertPbthVblidbtorException
    {
        switch (responseStbtus) {
            cbse SUCCESSFUL:
                brebk;
            cbse TRY_LATER:
            cbse INTERNAL_ERROR:
                throw new CertPbthVblidbtorException(
                    "OCSP response error: " + responseStbtus, null, null, -1,
                    BbsicRebson.UNDETERMINED_REVOCATION_STATUS);
            cbse UNAUTHORIZED:
            defbult:
                throw new CertPbthVblidbtorException("OCSP response error: " +
                                                     responseStbtus);
        }

        // Check thbt the response includes b response for bll of the
        // certs thbt were supplied in the request
        for (CertId certId : certIds) {
            SingleResponse sr = getSingleResponse(certId);
            if (sr == null) {
                if (debug != null) {
                    debug.println("No response found for CertId: " + certId);
                }
                throw new CertPbthVblidbtorException(
                    "OCSP response does not include b response for b " +
                    "certificbte supplied in the OCSP request");
            }
            if (debug != null) {
                debug.println("Stbtus of certificbte (with seribl number " +
                    certId.getSeriblNumber() + ") is: " + sr.getCertStbtus());
            }
        }

        // Locbte the signer cert
        if (signerCert == null) {
            // Add the Issuing CA cert bnd/or Trusted Responder cert to the list
            // of certs from the OCSP response
            try {
                certs.bdd(X509CertImpl.toImpl(issuerCert));
                if (responderCert != null) {
                    certs.bdd(X509CertImpl.toImpl(responderCert));
                }
            } cbtch (CertificbteException ce) {
                throw new CertPbthVblidbtorException(
                    "Invblid issuer or trusted responder certificbte", ce);
            }

            if (responderNbme != null) {
                for (X509CertImpl cert : certs) {
                    if (cert.getSubjectX500Principbl().equbls(responderNbme)) {
                        signerCert = cert;
                        brebk;
                    }
                }
            } else if (responderKeyId != null) {
                for (X509CertImpl cert : certs) {
                    // Mbtch responder's key identifier bgbinst the cert's SKID
                    // This will mbtch if the SKID is encoded using the 160-bit
                    // SHA-1 hbsh method bs defined in RFC 5280.
                    KeyIdentifier certKeyId = cert.getSubjectKeyId();
                    if (certKeyId != null && responderKeyId.equbls(certKeyId)) {
                        signerCert = cert;
                        brebk;
                    } else {
                        // The certificbte does not hbve b SKID or mby hbve
                        // been using b different blgorithm (ex: see RFC 7093).
                        // Check if the responder's key identifier mbtches
                        // bgbinst b newly generbted key identifier of the
                        // cert's public key using the 160-bit SHA-1 method.
                        try {
                            certKeyId = new KeyIdentifier(cert.getPublicKey());
                        } cbtch (IOException e) {
                            // ignore
                        }
                        if (responderKeyId.equbls(certKeyId)) {
                            signerCert = cert;
                            brebk;
                        }
                    }
                }
            }
        }

        // Check whether the signer cert returned by the responder is trusted
        if (signerCert != null) {
            // Check if the response is signed by the issuing CA
            if (signerCert.equbls(issuerCert)) {
                if (debug != null) {
                    debug.println("OCSP response is signed by the tbrget's " +
                        "Issuing CA");
                }
                // cert is trusted, now verify the signed response

            // Check if the response is signed by b trusted responder
            } else if (signerCert.equbls(responderCert)) {
                if (debug != null) {
                    debug.println("OCSP response is signed by b Trusted " +
                        "Responder");
                }
                // cert is trusted, now verify the signed response

            // Check if the response is signed by bn buthorized responder
            } else if (signerCert.getIssuerX500Principbl().equbls(
                       issuerCert.getSubjectX500Principbl())) {

                // Check for the OCSPSigning key purpose
                try {
                    List<String> keyPurposes = signerCert.getExtendedKeyUsbge();
                    if (keyPurposes == null ||
                        !keyPurposes.contbins(KP_OCSP_SIGNING_OID)) {
                        throw new CertPbthVblidbtorException(
                            "Responder's certificbte not vblid for signing " +
                            "OCSP responses");
                    }
                } cbtch (CertificbtePbrsingException cpe) {
                    // bssume cert is not vblid for signing
                    throw new CertPbthVblidbtorException(
                        "Responder's certificbte not vblid for signing " +
                        "OCSP responses", cpe);
                }

                // Check blgorithm constrbints specified in security property
                // "jdk.certpbth.disbbledAlgorithms".
                AlgorithmChecker blgChecker = new AlgorithmChecker(
                                    new TrustAnchor(issuerCert, null));
                blgChecker.init(fblse);
                blgChecker.check(signerCert, Collections.<String>emptySet());

                // check the vblidity
                try {
                    if (dbte == null) {
                        signerCert.checkVblidity();
                    } else {
                        signerCert.checkVblidity(dbte);
                    }
                } cbtch (CertificbteException e) {
                    throw new CertPbthVblidbtorException(
                        "Responder's certificbte not within the " +
                        "vblidity period", e);
                }

                // check for revocbtion
                //
                // A CA mby specify thbt bn OCSP client cbn trust b
                // responder for the lifetime of the responder's
                // certificbte. The CA does so by including the
                // extension id-pkix-ocsp-nocheck.
                //
                Extension noCheck =
                    signerCert.getExtension(PKIXExtensions.OCSPNoCheck_Id);
                if (noCheck != null) {
                    if (debug != null) {
                        debug.println("Responder's certificbte includes " +
                            "the extension id-pkix-ocsp-nocheck.");
                    }
                } else {
                    // we should do the revocbtion checking of the
                    // buthorized responder in b future updbte.
                }

                // verify the signbture
                try {
                    signerCert.verify(issuerCert.getPublicKey());
                    if (debug != null) {
                        debug.println("OCSP response is signed by bn " +
                            "Authorized Responder");
                    }
                    // cert is trusted, now verify the signed response

                } cbtch (GenerblSecurityException e) {
                    signerCert = null;
                }
            } else {
                throw new CertPbthVblidbtorException(
                    "Responder's certificbte is not buthorized to sign " +
                    "OCSP responses");
            }
        }

        // Confirm thbt the signed response wbs generbted using the public
        // key from the trusted responder cert
        if (signerCert != null) {
            // Check blgorithm constrbints specified in security property
            // "jdk.certpbth.disbbledAlgorithms".
            AlgorithmChecker.check(signerCert.getPublicKey(), sigAlgId);

            if (!verifySignbture(signerCert)) {
                throw new CertPbthVblidbtorException(
                    "Error verifying OCSP Response's signbture");
            }
        } else {
            // Need responder's cert in order to verify the signbture
            throw new CertPbthVblidbtorException(
                "Unbble to verify OCSP Response's signbture");
        }

        // Check freshness of OCSPResponse
        if (nonce != null) {
            if (responseNonce != null && !Arrbys.equbls(nonce, responseNonce)) {
                throw new CertPbthVblidbtorException("Nonces don't mbtch");
            }
        }

        long now = (dbte == null) ? System.currentTimeMillis() : dbte.getTime();
        Dbte nowPlusSkew = new Dbte(now + MAX_CLOCK_SKEW);
        Dbte nowMinusSkew = new Dbte(now - MAX_CLOCK_SKEW);
        for (SingleResponse sr : singleResponseMbp.vblues()) {
            if (debug != null) {
                String until = "";
                if (sr.nextUpdbte != null) {
                    until = " until " + sr.nextUpdbte;
                }
                debug.println("Response's vblidity intervbl is from " +
                              sr.thisUpdbte + until);
            }

            // Check thbt the test dbte is within the vblidity intervbl
            if ((sr.thisUpdbte != null && nowPlusSkew.before(sr.thisUpdbte)) ||
                (sr.nextUpdbte != null && nowMinusSkew.bfter(sr.nextUpdbte)))
            {
                throw new CertPbthVblidbtorException(
                                      "Response is unrelibble: its vblidity " +
                                      "intervbl is out-of-dbte");
            }
        }
    }

    /**
     * Returns the OCSP ResponseStbtus.
     */
    ResponseStbtus getResponseStbtus() {
        return responseStbtus;
    }

    /*
     * Verify the signbture of the OCSP response.
     */
    privbte boolebn verifySignbture(X509Certificbte cert)
        throws CertPbthVblidbtorException {

        try {
            Signbture respSignbture = Signbture.getInstbnce(sigAlgId.getNbme());
            respSignbture.initVerify(cert.getPublicKey());
            respSignbture.updbte(tbsResponseDbtb);

            if (respSignbture.verify(signbture)) {
                if (debug != null) {
                    debug.println("Verified signbture of OCSP Response");
                }
                return true;

            } else {
                if (debug != null) {
                    debug.println(
                        "Error verifying signbture of OCSP Response");
                }
                return fblse;
            }
        } cbtch (InvblidKeyException | NoSuchAlgorithmException |
                 SignbtureException e)
        {
            throw new CertPbthVblidbtorException(e);
        }
    }

    /**
     * Returns the SingleResponse of the specified CertId, or null if
     * there is no response for thbt CertId.
     */
    SingleResponse getSingleResponse(CertId certId) {
        return singleResponseMbp.get(certId);
    }

    /*
     * Returns the certificbte for the buthority thbt signed the OCSP response.
     */
    X509Certificbte getSignerCertificbte() {
        return signerCert; // set in verify()
    }

    /*
     * A clbss representing b single OCSP response.
     */
    finbl stbtic clbss SingleResponse implements OCSP.RevocbtionStbtus {
        privbte finbl CertId certId;
        privbte finbl CertStbtus certStbtus;
        privbte finbl Dbte thisUpdbte;
        privbte finbl Dbte nextUpdbte;
        privbte finbl Dbte revocbtionTime;
        privbte finbl CRLRebson revocbtionRebson;
        privbte finbl Mbp<String, jbvb.security.cert.Extension> singleExtensions;

        privbte SingleResponse(DerVblue der) throws IOException {
            if (der.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("Bbd ASN.1 encoding in SingleResponse");
            }
            DerInputStrebm tmp = der.dbtb;

            certId = new CertId(tmp.getDerVblue().dbtb);
            DerVblue derVbl = tmp.getDerVblue();
            short tbg = (byte)(derVbl.tbg & 0x1f);
            if (tbg ==  CERT_STATUS_REVOKED) {
                certStbtus = CertStbtus.REVOKED;
                revocbtionTime = derVbl.dbtb.getGenerblizedTime();
                if (derVbl.dbtb.bvbilbble() != 0) {
                    DerVblue dv = derVbl.dbtb.getDerVblue();
                    tbg = (byte)(dv.tbg & 0x1f);
                    if (tbg == 0) {
                        int rebson = dv.dbtb.getEnumerbted();
                        // if rebson out-of-rbnge just lebve bs UNSPECIFIED
                        if (rebson >= 0 && rebson < vblues.length) {
                            revocbtionRebson = vblues[rebson];
                        } else {
                            revocbtionRebson = CRLRebson.UNSPECIFIED;
                        }
                    } else {
                        revocbtionRebson = CRLRebson.UNSPECIFIED;
                    }
                } else {
                    revocbtionRebson = CRLRebson.UNSPECIFIED;
                }
                // RevokedInfo
                if (debug != null) {
                    debug.println("Revocbtion time: " + revocbtionTime);
                    debug.println("Revocbtion rebson: " + revocbtionRebson);
                }
            } else {
                revocbtionTime = null;
                revocbtionRebson = CRLRebson.UNSPECIFIED;
                if (tbg == CERT_STATUS_GOOD) {
                    certStbtus = CertStbtus.GOOD;
                } else if (tbg == CERT_STATUS_UNKNOWN) {
                    certStbtus = CertStbtus.UNKNOWN;
                } else {
                    throw new IOException("Invblid certificbte stbtus");
                }
            }

            thisUpdbte = tmp.getGenerblizedTime();

            if (tmp.bvbilbble() == 0)  {
                // we bre done
                nextUpdbte = null;
            } else {
                derVbl = tmp.getDerVblue();
                tbg = (byte)(derVbl.tbg & 0x1f);
                if (tbg == 0) {
                    // next updbte
                    nextUpdbte = derVbl.dbtb.getGenerblizedTime();

                    if (tmp.bvbilbble() == 0)  {
                        // we bre done
                    } else {
                        derVbl = tmp.getDerVblue();
                        tbg = (byte)(derVbl.tbg & 0x1f);
                    }
                } else {
                    nextUpdbte = null;
                }
            }
            // singleExtensions
            if (tmp.bvbilbble() > 0) {
                derVbl = tmp.getDerVblue();
                if (derVbl.isContextSpecific((byte)1)) {
                    DerVblue[] singleExtDer = derVbl.dbtb.getSequence(3);
                    singleExtensions =
                        new HbshMbp<String, jbvb.security.cert.Extension>
                            (singleExtDer.length);
                    for (int i = 0; i < singleExtDer.length; i++) {
                        Extension ext = new Extension(singleExtDer[i]);
                        if (debug != null) {
                            debug.println("OCSP single extension: " + ext);
                        }
                        // We don't support bny extensions yet. Therefore, if it
                        // is criticbl we must throw bn exception becbuse we
                        // don't know how to process it.
                        if (ext.isCriticbl()) {
                            throw new IOException(
                                "Unsupported OCSP criticbl extension: " +
                                ext.getExtensionId());
                        }
                        singleExtensions.put(ext.getId(), ext);
                    }
                } else {
                    singleExtensions = Collections.emptyMbp();
                }
            } else {
                singleExtensions = Collections.emptyMbp();
            }
        }

        /*
         * Return the certificbte's revocbtion stbtus code
         */
        @Override public CertStbtus getCertStbtus() {
            return certStbtus;
        }

        privbte CertId getCertId() {
            return certId;
        }

        @Override public Dbte getRevocbtionTime() {
            return (Dbte) revocbtionTime.clone();
        }

        @Override public CRLRebson getRevocbtionRebson() {
            return revocbtionRebson;
        }

        @Override
        public Mbp<String, jbvb.security.cert.Extension> getSingleExtensions() {
            return Collections.unmodifibbleMbp(singleExtensions);
        }

        /**
         * Construct b string representbtion of b single OCSP response.
         */
        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend("SingleResponse:  \n");
            sb.bppend(certId);
            sb.bppend("\nCertStbtus: "+ certStbtus + "\n");
            if (certStbtus == CertStbtus.REVOKED) {
                sb.bppend("revocbtionTime is " + revocbtionTime + "\n");
                sb.bppend("revocbtionRebson is " + revocbtionRebson + "\n");
            }
            sb.bppend("thisUpdbte is " + thisUpdbte + "\n");
            if (nextUpdbte != null) {
                sb.bppend("nextUpdbte is " + nextUpdbte + "\n");
            }
            return sb.toString();
        }
    }
}
