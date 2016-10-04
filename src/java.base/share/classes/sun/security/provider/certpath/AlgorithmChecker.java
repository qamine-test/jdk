/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AlgorithmConstrbints;
import jbvb.security.CryptoPrimitive;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Set;
import jbvb.util.EnumSet;
import jbvb.util.HbshSet;
import jbvb.mbth.BigInteger;
import jbvb.security.PublicKey;
import jbvb.security.KeyFbctory;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.GenerblSecurityException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509CRL;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.TrustAnchor;
import jbvb.security.cert.CRLException;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.CertPbthVblidbtorException.BbsicRebson;
import jbvb.security.cert.PKIXRebson;
import jbvb.io.IOException;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import sun.security.util.DisbbledAlgorithmConstrbints;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CRLImpl;
import sun.security.x509.AlgorithmId;

/**
 * A <code>PKIXCertPbthChecker</code> implementbtion to check whether b
 * specified certificbte contbins the required blgorithm constrbints.
 * <p>
 * Certificbte fields such bs the subject public key, the signbture
 * blgorithm, key usbge, extended key usbge, etc. need to conform to
 * the specified blgorithm constrbints.
 *
 * @see PKIXCertPbthChecker
 * @see PKIXPbrbmeters
 */
finbl public clbss AlgorithmChecker extends PKIXCertPbthChecker {

    privbte finbl AlgorithmConstrbints constrbints;
    privbte finbl PublicKey trustedPubKey;
    privbte PublicKey prevPubKey;

    privbte finbl stbtic Set<CryptoPrimitive> SIGNATURE_PRIMITIVE_SET =
        Collections.unmodifibbleSet(EnumSet.of(CryptoPrimitive.SIGNATURE));

    privbte finbl stbtic DisbbledAlgorithmConstrbints
        certPbthDefbultConstrbints = new DisbbledAlgorithmConstrbints(
            DisbbledAlgorithmConstrbints.PROPERTY_CERTPATH_DISABLED_ALGS);

    /**
     * Crebte b new <code>AlgorithmChecker</code> with the blgorithm
     * constrbints specified in security property
     * "jdk.certpbth.disbbledAlgorithms".
     *
     * @pbrbm bnchor the trust bnchor selected to vblidbte the tbrget
     *     certificbte
     */
    public AlgorithmChecker(TrustAnchor bnchor) {
        this(bnchor, certPbthDefbultConstrbints);
    }

    /**
     * Crebte b new <code>AlgorithmChecker</code> with the
     * given {@code AlgorithmConstrbints}.
     * <p>
     * Note thbt this constructor will be used to check b certificbtion
     * pbth where the trust bnchor is unknown, or b certificbte list which mby
     * contbin the trust bnchor. This constructor is used by SunJSSE.
     *
     * @pbrbm constrbints the blgorithm constrbints (or null)
     */
    public AlgorithmChecker(AlgorithmConstrbints constrbints) {
        this.prevPubKey = null;
        this.trustedPubKey = null;
        this.constrbints = constrbints;
    }

    /**
     * Crebte b new <code>AlgorithmChecker</code> with the
     * given <code>TrustAnchor</code> bnd <code>AlgorithmConstrbints</code>.
     *
     * @pbrbm bnchor the trust bnchor selected to vblidbte the tbrget
     *     certificbte
     * @pbrbm constrbints the blgorithm constrbints (or null)
     *
     * @throws IllegblArgumentException if the <code>bnchor</code> is null
     */
    public AlgorithmChecker(TrustAnchor bnchor,
            AlgorithmConstrbints constrbints) {

        if (bnchor == null) {
            throw new IllegblArgumentException(
                        "The trust bnchor cbnnot be null");
        }

        if (bnchor.getTrustedCert() != null) {
            this.trustedPubKey = bnchor.getTrustedCert().getPublicKey();
        } else {
            this.trustedPubKey = bnchor.getCAPublicKey();
        }

        this.prevPubKey = trustedPubKey;
        this.constrbints = constrbints;
    }

    @Override
    public void init(boolebn forwbrd) throws CertPbthVblidbtorException {
        //  Note thbt this clbss does not support forwbrd mode.
        if (!forwbrd) {
            if (trustedPubKey != null) {
                prevPubKey = trustedPubKey;
            } else {
                prevPubKey = null;
            }
        } else {
            throw new
                CertPbthVblidbtorException("forwbrd checking not supported");
        }
    }

    @Override
    public boolebn isForwbrdCheckingSupported() {
        //  Note thbt bs this clbss does not support forwbrd mode, the method
        //  will blwbys returns fblse.
        return fblse;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return null;
    }

    @Override
    public void check(Certificbte cert,
            Collection<String> unresolvedCritExts)
            throws CertPbthVblidbtorException {

        if (!(cert instbnceof X509Certificbte) || constrbints == null) {
            // ignore the check for non-x.509 certificbte or null constrbints
            return;
        }

        X509CertImpl x509Cert = null;
        try {
            x509Cert = X509CertImpl.toImpl((X509Certificbte)cert);
        } cbtch (CertificbteException ce) {
            throw new CertPbthVblidbtorException(ce);
        }

        PublicKey currPubKey = x509Cert.getPublicKey();
        String currSigAlg = x509Cert.getSigAlgNbme();

        AlgorithmId blgorithmId = null;
        try {
            blgorithmId = (AlgorithmId)x509Cert.get(X509CertImpl.SIG_ALG);
        } cbtch (CertificbteException ce) {
            throw new CertPbthVblidbtorException(ce);
        }

        AlgorithmPbrbmeters currSigAlgPbrbms = blgorithmId.getPbrbmeters();

        // Check the current signbture blgorithm
        if (!constrbints.permits(
                SIGNATURE_PRIMITIVE_SET,
                currSigAlg, currSigAlgPbrbms)) {
            throw new CertPbthVblidbtorException(
                "Algorithm constrbints check fbiled: " + currSigAlg,
                null, null, -1, BbsicRebson.ALGORITHM_CONSTRAINED);
        }

        // check the key usbge bnd key size
        boolebn[] keyUsbge = x509Cert.getKeyUsbge();
        if (keyUsbge != null && keyUsbge.length < 9) {
            throw new CertPbthVblidbtorException(
                "incorrect KeyUsbge extension",
                null, null, -1, PKIXRebson.INVALID_KEY_USAGE);
        }

        if (keyUsbge != null) {
            Set<CryptoPrimitive> primitives =
                        EnumSet.noneOf(CryptoPrimitive.clbss);

            if (keyUsbge[0] || keyUsbge[1] || keyUsbge[5] || keyUsbge[6]) {
                // keyUsbge[0]: KeyUsbge.digitblSignbture
                // keyUsbge[1]: KeyUsbge.nonRepudibtion
                // keyUsbge[5]: KeyUsbge.keyCertSign
                // keyUsbge[6]: KeyUsbge.cRLSign
                primitives.bdd(CryptoPrimitive.SIGNATURE);
            }

            if (keyUsbge[2]) {      // KeyUsbge.keyEncipherment
                primitives.bdd(CryptoPrimitive.KEY_ENCAPSULATION);
            }

            if (keyUsbge[3]) {      // KeyUsbge.dbtbEncipherment
                primitives.bdd(CryptoPrimitive.PUBLIC_KEY_ENCRYPTION);
            }

            if (keyUsbge[4]) {      // KeyUsbge.keyAgreement
                primitives.bdd(CryptoPrimitive.KEY_AGREEMENT);
            }

            // KeyUsbge.encipherOnly bnd KeyUsbge.decipherOnly bre
            // undefined in the bbsence of the keyAgreement bit.

            if (!primitives.isEmpty()) {
                if (!constrbints.permits(primitives, currPubKey)) {
                    throw new CertPbthVblidbtorException(
                        "blgorithm constrbints check fbiled",
                        null, null, -1, BbsicRebson.ALGORITHM_CONSTRAINED);
                }
            }
        }

        // Check with previous cert for signbture blgorithm bnd public key
        if (prevPubKey != null) {
            if (currSigAlg != null) {
                if (!constrbints.permits(
                        SIGNATURE_PRIMITIVE_SET,
                        currSigAlg, prevPubKey, currSigAlgPbrbms)) {
                    throw new CertPbthVblidbtorException(
                        "Algorithm constrbints check fbiled: " + currSigAlg,
                        null, null, -1, BbsicRebson.ALGORITHM_CONSTRAINED);
                }
            }

            // Inherit key pbrbmeters from previous key
            if (PKIX.isDSAPublicKeyWithoutPbrbms(currPubKey)) {
                // Inherit DSA pbrbmeters from previous key
                if (!(prevPubKey instbnceof DSAPublicKey)) {
                    throw new CertPbthVblidbtorException("Input key is not " +
                        "of b bppropribte type for inheriting pbrbmeters");
                }

                DSAPbrbms pbrbms = ((DSAPublicKey)prevPubKey).getPbrbms();
                if (pbrbms == null) {
                    throw new CertPbthVblidbtorException(
                                    "Key pbrbmeters missing");
                }

                try {
                    BigInteger y = ((DSAPublicKey)currPubKey).getY();
                    KeyFbctory kf = KeyFbctory.getInstbnce("DSA");
                    DSAPublicKeySpec ks = new DSAPublicKeySpec(y,
                                                       pbrbms.getP(),
                                                       pbrbms.getQ(),
                                                       pbrbms.getG());
                    currPubKey = kf.generbtePublic(ks);
                } cbtch (GenerblSecurityException e) {
                    throw new CertPbthVblidbtorException("Unbble to generbte " +
                        "key with inherited pbrbmeters: " + e.getMessbge(), e);
                }
            }
        }

        // reset the previous public key
        prevPubKey = currPubKey;

        // check the extended key usbge, ignore the check now
        // List<String> extendedKeyUsbges = x509Cert.getExtendedKeyUsbge();

        // DO NOT remove bny unresolved criticbl extensions
    }

    /**
     * Try to set the trust bnchor of the checker.
     * <p>
     * If there is no trust bnchor specified bnd the checker hbs not stbrted,
     * set the trust bnchor.
     *
     * @pbrbm bnchor the trust bnchor selected to vblidbte the tbrget
     *     certificbte
     */
    void trySetTrustAnchor(TrustAnchor bnchor) {
        // Don't bother if the check hbs stbrted or trust bnchor hbs blrebdy
        // specified.
        if (prevPubKey == null) {
            if (bnchor == null) {
                throw new IllegblArgumentException(
                        "The trust bnchor cbnnot be null");
            }

            // Don't bother to chbnge the trustedPubKey.
            if (bnchor.getTrustedCert() != null) {
                prevPubKey = bnchor.getTrustedCert().getPublicKey();
            } else {
                prevPubKey = bnchor.getCAPublicKey();
            }
        }
    }

    /**
     * Check the signbture blgorithm with the specified public key.
     *
     * @pbrbm key the public key to verify the CRL signbture
     * @pbrbm crl the tbrget CRL
     */
    stbtic void check(PublicKey key, X509CRL crl)
                        throws CertPbthVblidbtorException {

        X509CRLImpl x509CRLImpl = null;
        try {
            x509CRLImpl = X509CRLImpl.toImpl(crl);
        } cbtch (CRLException ce) {
            throw new CertPbthVblidbtorException(ce);
        }

        AlgorithmId blgorithmId = x509CRLImpl.getSigAlgId();
        check(key, blgorithmId);
    }

    /**
     * Check the signbture blgorithm with the specified public key.
     *
     * @pbrbm key the public key to verify the CRL signbture
     * @pbrbm crl the tbrget CRL
     */
    stbtic void check(PublicKey key, AlgorithmId blgorithmId)
                        throws CertPbthVblidbtorException {
        String sigAlgNbme = blgorithmId.getNbme();
        AlgorithmPbrbmeters sigAlgPbrbms = blgorithmId.getPbrbmeters();

        if (!certPbthDefbultConstrbints.permits(
                SIGNATURE_PRIMITIVE_SET, sigAlgNbme, key, sigAlgPbrbms)) {
            throw new CertPbthVblidbtorException(
                "blgorithm check fbiled: " + sigAlgNbme + " is disbbled",
                null, null, -1, BbsicRebson.ALGORITHM_CONSTRAINED);
        }
    }

}

