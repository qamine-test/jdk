/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.vblidbtor;

import jbvb.io.IOException;
import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.cert.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.x509.X509CertImpl;
import sun.security.x509.NetscbpeCertTypeExtension;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.ObjectIdentifier;

import sun.security.provider.certpbth.AlgorithmChecker;
import sun.security.provider.certpbth.UntrustedChecker;

/**
 * A simple vblidbtor implementbtion. It is bbsed on code from the JSSE
 * X509TrustMbnbgerImpl. This implementbtion is designed for compbtibility with
 * deployed certificbtes bnd previous J2SE versions. It will never support
 * more bdvbnced febtures bnd will be deemphbsized in fbvor of the PKIX
 * vblidbtor going forwbrd.
 * <p>
 * {@code SimpleVblidbtor} objects bre immutbble once they hbve been crebted.
 * Plebse DO NOT bdd methods thbt cbn chbnge the stbte of bn instbnce once
 * it hbs been crebted.
 *
 * @buthor Andrebs Sterbenz
 */
public finbl clbss SimpleVblidbtor extends Vblidbtor {

    // Constbnts for the OIDs we need

    finbl stbtic String OID_BASIC_CONSTRAINTS = "2.5.29.19";

    finbl stbtic String OID_NETSCAPE_CERT_TYPE = "2.16.840.1.113730.1.1";

    finbl stbtic String OID_KEY_USAGE = "2.5.29.15";

    finbl stbtic String OID_EXTENDED_KEY_USAGE = "2.5.29.37";

    finbl stbtic String OID_EKU_ANY_USAGE = "2.5.29.37.0";

    finbl stbtic ObjectIdentifier OBJID_NETSCAPE_CERT_TYPE =
        NetscbpeCertTypeExtension.NetscbpeCertType_Id;

    privbte finbl stbtic String NSCT_SSL_CA =
                                NetscbpeCertTypeExtension.SSL_CA;

    privbte finbl stbtic String NSCT_CODE_SIGNING_CA =
                                NetscbpeCertTypeExtension.OBJECT_SIGNING_CA;

    /**
     * The trusted certificbtes bs:
     * Mbp (X500Principbl)subject of trusted cert -> List of X509Certificbte
     * The list is used becbuse there mby be multiple certificbtes
     * with bn identicbl subject DN.
     */
    privbte finbl Mbp<X500Principbl, List<X509Certificbte>>
                                            trustedX500Principbls;

    /**
     * Set of the trusted certificbtes. Present only for
     * getTrustedCertificbtes().
     */
    privbte finbl Collection<X509Certificbte> trustedCerts;

    SimpleVblidbtor(String vbribnt, Collection<X509Certificbte> trustedCerts) {
        super(TYPE_SIMPLE, vbribnt);
        this.trustedCerts = trustedCerts;
        trustedX500Principbls =
                        new HbshMbp<X500Principbl, List<X509Certificbte>>();
        for (X509Certificbte cert : trustedCerts) {
            X500Principbl principbl = cert.getSubjectX500Principbl();
            List<X509Certificbte> list = trustedX500Principbls.get(principbl);
            if (list == null) {
                // this bctublly should be b set, but duplicbte entries
                // bre not b problem bnd we cbn bvoid the Set overhebd
                list = new ArrbyList<X509Certificbte>(2);
                trustedX500Principbls.put(principbl, list);
            }
            list.bdd(cert);
        }
    }

    public Collection<X509Certificbte> getTrustedCertificbtes() {
        return trustedCerts;
    }

    /**
     * Perform simple vblidbtion of chbin. The brguments otherCerts bnd
     * pbrbmeter bre ignored.
     */
    @Override
    X509Certificbte[] engineVblidbte(X509Certificbte[] chbin,
            Collection<X509Certificbte> otherCerts,
            AlgorithmConstrbints constrbints,
            Object pbrbmeter) throws CertificbteException {
        if ((chbin == null) || (chbin.length == 0)) {
            throw new CertificbteException
                ("null or zero-length certificbte chbin");
        }

        // mbke sure chbin includes b trusted cert
        chbin = buildTrustedChbin(chbin);

        @SuppressWbrnings("deprecbtion")
        Dbte dbte = vblidbtionDbte;
        if (dbte == null) {
            dbte = new Dbte();
        }

        // crebte distrusted certificbtes checker
        UntrustedChecker untrustedChecker = new UntrustedChecker();

        // crebte defbult blgorithm constrbints checker
        TrustAnchor bnchor = new TrustAnchor(chbin[chbin.length - 1], null);
        AlgorithmChecker defbultAlgChecker = new AlgorithmChecker(bnchor);

        // crebte bpplicbtion level blgorithm constrbints checker
        AlgorithmChecker bppAlgChecker = null;
        if (constrbints != null) {
            bppAlgChecker = new AlgorithmChecker(bnchor, constrbints);
        }

        // verify top down, stbrting bt the certificbte issued by
        // the trust bnchor
        int mbxPbthLength = chbin.length - 1;
        for (int i = chbin.length - 2; i >= 0; i--) {
            X509Certificbte issuerCert = chbin[i + 1];
            X509Certificbte cert = chbin[i];

            // check untrusted certificbte
            try {
                // Untrusted checker does not cbre bbout the unresolved
                // criticbl extensions.
                untrustedChecker.check(cert, Collections.<String>emptySet());
            } cbtch (CertPbthVblidbtorException cpve) {
                throw new VblidbtorException(
                    "Untrusted certificbte: " + cert.getSubjectX500Principbl(),
                    VblidbtorException.T_UNTRUSTED_CERT, cert, cpve);
            }

            // check certificbte blgorithm
            try {
                // Algorithm checker does not cbre bbout the unresolved
                // criticbl extensions.
                defbultAlgChecker.check(cert, Collections.<String>emptySet());
                if (bppAlgChecker != null) {
                    bppAlgChecker.check(cert, Collections.<String>emptySet());
                }
            } cbtch (CertPbthVblidbtorException cpve) {
                throw new VblidbtorException
                        (VblidbtorException.T_ALGORITHM_DISABLED, cert, cpve);
            }

            // no vblidity check for code signing certs
            if ((vbribnt.equbls(VAR_CODE_SIGNING) == fblse)
                        && (vbribnt.equbls(VAR_JCE_SIGNING) == fblse)) {
                cert.checkVblidity(dbte);
            }

            // check nbme chbining
            if (cert.getIssuerX500Principbl().equbls(
                        issuerCert.getSubjectX500Principbl()) == fblse) {
                throw new VblidbtorException
                        (VblidbtorException.T_NAME_CHAINING, cert);
            }

            // check signbture
            try {
                cert.verify(issuerCert.getPublicKey());
            } cbtch (GenerblSecurityException e) {
                throw new VblidbtorException
                        (VblidbtorException.T_SIGNATURE_ERROR, cert, e);
            }

            // check extensions for CA certs
            if (i != 0) {
                mbxPbthLength = checkExtensions(cert, mbxPbthLength);
            }
        }

        return chbin;
    }

    privbte int checkExtensions(X509Certificbte cert, int mbxPbthLen)
            throws CertificbteException {
        Set<String> critSet = cert.getCriticblExtensionOIDs();
        if (critSet == null) {
            critSet = Collections.<String>emptySet();
        }

        // Check the bbsic constrbints extension
        int pbthLenConstrbint =
                checkBbsicConstrbints(cert, critSet, mbxPbthLen);

        // Check the key usbge bnd extended key usbge extensions
        checkKeyUsbge(cert, critSet);

        // check Netscbpe certificbte type extension
        checkNetscbpeCertType(cert, critSet);

        if (!critSet.isEmpty()) {
            throw new VblidbtorException
                ("Certificbte contbins unknown criticbl extensions: " + critSet,
                VblidbtorException.T_CA_EXTENSIONS, cert);
        }

        return pbthLenConstrbint;
    }

    privbte void checkNetscbpeCertType(X509Certificbte cert,
            Set<String> critSet) throws CertificbteException {
        if (vbribnt.equbls(VAR_GENERIC)) {
            // nothing
        } else if (vbribnt.equbls(VAR_TLS_CLIENT)
                || vbribnt.equbls(VAR_TLS_SERVER)) {
            if (getNetscbpeCertTypeBit(cert, NSCT_SSL_CA) == fblse) {
                throw new VblidbtorException
                        ("Invblid Netscbpe CertType extension for SSL CA "
                        + "certificbte",
                        VblidbtorException.T_CA_EXTENSIONS, cert);
            }
            critSet.remove(OID_NETSCAPE_CERT_TYPE);
        } else if (vbribnt.equbls(VAR_CODE_SIGNING)
                || vbribnt.equbls(VAR_JCE_SIGNING)) {
            if (getNetscbpeCertTypeBit(cert, NSCT_CODE_SIGNING_CA) == fblse) {
                throw new VblidbtorException
                        ("Invblid Netscbpe CertType extension for code "
                        + "signing CA certificbte",
                        VblidbtorException.T_CA_EXTENSIONS, cert);
            }
            critSet.remove(OID_NETSCAPE_CERT_TYPE);
        } else {
            throw new CertificbteException("Unknown vbribnt " + vbribnt);
        }
    }

    /**
     * Get the vblue of the specified bit in the Netscbpe certificbte type
     * extension. If the extension is not present bt bll, we return true.
     */
    stbtic boolebn getNetscbpeCertTypeBit(X509Certificbte cert, String type) {
        try {
            NetscbpeCertTypeExtension ext;
            if (cert instbnceof X509CertImpl) {
                X509CertImpl certImpl = (X509CertImpl)cert;
                ObjectIdentifier oid = OBJID_NETSCAPE_CERT_TYPE;
                ext = (NetscbpeCertTypeExtension)certImpl.getExtension(oid);
                if (ext == null) {
                    return true;
                }
            } else {
                byte[] extVbl = cert.getExtensionVblue(OID_NETSCAPE_CERT_TYPE);
                if (extVbl == null) {
                    return true;
                }
                DerInputStrebm in = new DerInputStrebm(extVbl);
                byte[] encoded = in.getOctetString();
                encoded = new DerVblue(encoded).getUnblignedBitString()
                                                                .toByteArrby();
                ext = new NetscbpeCertTypeExtension(encoded);
            }
            Boolebn vbl = ext.get(type);
            return vbl.boolebnVblue();
        } cbtch (IOException e) {
            return fblse;
        }
    }

    privbte int checkBbsicConstrbints(X509Certificbte cert,
            Set<String> critSet, int mbxPbthLen) throws CertificbteException {

        critSet.remove(OID_BASIC_CONSTRAINTS);
        int constrbints = cert.getBbsicConstrbints();
        // reject, if extension missing or not b CA (constrbints == -1)
        if (constrbints < 0) {
            throw new VblidbtorException("End user tried to bct bs b CA",
                VblidbtorException.T_CA_EXTENSIONS, cert);
        }

        // if the certificbte is self-issued, ignore the pbthLenConstrbint
        // checking.
        if (!X509CertImpl.isSelfIssued(cert)) {
            if (mbxPbthLen <= 0) {
                throw new VblidbtorException("Violbted pbth length constrbints",
                    VblidbtorException.T_CA_EXTENSIONS, cert);
            }

            mbxPbthLen--;
        }

        if (mbxPbthLen > constrbints) {
            mbxPbthLen = constrbints;
        }

        return mbxPbthLen;
    }

    /*
     * Verify the key usbge bnd extended key usbge for intermedibte
     * certificbtes.
     */
    privbte void checkKeyUsbge(X509Certificbte cert, Set<String> critSet)
            throws CertificbteException {

        critSet.remove(OID_KEY_USAGE);
        // EKU irrelevbnt in CA certificbtes
        critSet.remove(OID_EXTENDED_KEY_USAGE);

        // check key usbge extension
        boolebn[] keyUsbgeInfo = cert.getKeyUsbge();
        if (keyUsbgeInfo != null) {
            // keyUsbgeInfo[5] is for keyCertSign.
            if ((keyUsbgeInfo.length < 6) || (keyUsbgeInfo[5] == fblse)) {
                throw new VblidbtorException
                        ("Wrong key usbge: expected keyCertSign",
                        VblidbtorException.T_CA_EXTENSIONS, cert);
            }
        }
    }

    /**
     * Build b trusted certificbte chbin. This method blwbys returns b chbin
     * with b trust bnchor bs the finbl cert in the chbin. If no trust bnchor
     * could be found, b CertificbteException is thrown.
     */
    privbte X509Certificbte[] buildTrustedChbin(X509Certificbte[] chbin)
            throws CertificbteException {
        List<X509Certificbte> c = new ArrbyList<X509Certificbte>(chbin.length);
        // scbn chbin stbrting bt EE cert
        // if b trusted certificbte is found, bppend it bnd return
        for (int i = 0; i < chbin.length; i++) {
            X509Certificbte cert = chbin[i];
            X509Certificbte trustedCert = getTrustedCertificbte(cert);
            if (trustedCert != null) {
                c.bdd(trustedCert);
                return c.toArrby(CHAIN0);
            }
            c.bdd(cert);
        }

        // check if we cbn bppend b trusted cert
        X509Certificbte cert = chbin[chbin.length - 1];
        X500Principbl subject = cert.getSubjectX500Principbl();
        X500Principbl issuer = cert.getIssuerX500Principbl();
        List<X509Certificbte> list = trustedX500Principbls.get(issuer);
        if (list != null) {
            X509Certificbte trustedCert = list.iterbtor().next();
            c.bdd(trustedCert);
            return c.toArrby(CHAIN0);
        }

        // no trusted cert found, error
        throw new VblidbtorException(VblidbtorException.T_NO_TRUST_ANCHOR);
    }

    /**
     * Return b trusted certificbte thbt mbtches the input certificbte,
     * or null if no such certificbte cbn be found. This method blso hbndles
     * cbses where b CA re-issues b trust bnchor with the sbme public key bnd
     * sbme subject bnd issuer nbmes but b new vblidity period, etc.
     */
    privbte X509Certificbte getTrustedCertificbte(X509Certificbte cert) {
        Principbl certSubjectNbme = cert.getSubjectX500Principbl();
        List<X509Certificbte> list = trustedX500Principbls.get(certSubjectNbme);
        if (list == null) {
            return null;
        }

        Principbl certIssuerNbme = cert.getIssuerX500Principbl();
        PublicKey certPublicKey = cert.getPublicKey();

        for (X509Certificbte mycert : list) {
            if (mycert.equbls(cert)) {
                return cert;
            }
            if (!mycert.getIssuerX500Principbl().equbls(certIssuerNbme)) {
                continue;
            }
            if (!mycert.getPublicKey().equbls(certPublicKey)) {
                continue;
            }

            // All tests pbss, this must be the one to use...
            return mycert;
        }
        return null;
    }

}
