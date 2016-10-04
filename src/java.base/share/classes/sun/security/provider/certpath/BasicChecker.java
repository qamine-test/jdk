/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.mbth.BigInteger;
import jbvb.util.Collection;
import jbvb.util.Dbte;
import jbvb.util.Set;
import jbvb.security.GenerblSecurityException;
import jbvb.security.KeyFbctory;
import jbvb.security.PublicKey;
import jbvb.security.SignbtureException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteExpiredException;
import jbvb.security.cert.CertificbteNotYetVblidException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.CertPbthVblidbtorException.BbsicRebson;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.PKIXRebson;
import jbvb.security.cert.TrustAnchor;
import jbvb.security.interfbces.DSAPbrbms;
import jbvb.security.interfbces.DSAPublicKey;
import jbvb.security.spec.DSAPublicKeySpec;
import jbvbx.security.buth.x500.X500Principbl;
import sun.security.x509.X500Nbme;
import sun.security.util.Debug;

/**
 * BbsicChecker is b PKIXCertPbthChecker thbt checks the bbsic informbtion
 * on b PKIX certificbte, nbmely the signbture, timestbmp, bnd subject/issuer
 * nbme chbining.
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
clbss BbsicChecker extends PKIXCertPbthChecker {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte finbl PublicKey trustedPubKey;
    privbte finbl X500Principbl cbNbme;
    privbte finbl Dbte dbte;
    privbte finbl String sigProvider;
    privbte finbl boolebn sigOnly;
    privbte X500Principbl prevSubject;
    privbte PublicKey prevPubKey;

    /**
     * Constructor thbt initiblizes the input pbrbmeters.
     *
     * @pbrbm bnchor the bnchor selected to vblidbte the tbrget certificbte
     * @pbrbm testDbte the time for which the vblidity of the certificbte
     *        should be determined
     * @pbrbm sigProvider the nbme of the signbture provider
     * @pbrbm sigOnly true if only signbture checking is to be done;
     *        if fblse, bll checks bre done
     */
    BbsicChecker(TrustAnchor bnchor, Dbte dbte, String sigProvider,
                 boolebn sigOnly) {
        if (bnchor.getTrustedCert() != null) {
            this.trustedPubKey = bnchor.getTrustedCert().getPublicKey();
            this.cbNbme = bnchor.getTrustedCert().getSubjectX500Principbl();
        } else {
            this.trustedPubKey = bnchor.getCAPublicKey();
            this.cbNbme = bnchor.getCA();
        }
        this.dbte = dbte;
        this.sigProvider = sigProvider;
        this.sigOnly = sigOnly;
        this.prevPubKey = trustedPubKey;
    }

    /**
     * Initiblizes the internbl stbte of the checker from pbrbmeters
     * specified in the constructor.
     */
    @Override
    public void init(boolebn forwbrd) throws CertPbthVblidbtorException {
        if (!forwbrd) {
            prevPubKey = trustedPubKey;
            if (PKIX.isDSAPublicKeyWithoutPbrbms(prevPubKey)) {
                // If TrustAnchor is b DSA public key bnd it hbs no pbrbms, it
                // cbnnot be used to verify the signbture of the first cert,
                // so throw exception
                throw new CertPbthVblidbtorException("Key pbrbmeters missing");
            }
            prevSubject = cbNbme;
        } else {
            throw new
                CertPbthVblidbtorException("forwbrd checking not supported");
        }
    }

    @Override
    public boolebn isForwbrdCheckingSupported() {
        return fblse;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return null;
    }

    /**
     * Performs the signbture, timestbmp, bnd subject/issuer nbme chbining
     * checks on the certificbte using its internbl stbte. This method does
     * not remove bny criticbl extensions from the Collection.
     *
     * @pbrbm cert the Certificbte
     * @pbrbm unresolvedCritExts b Collection of the unresolved criticbl
     * extensions
     * @throws CertPbthVblidbtorException if certificbte does not verify
     */
    @Override
    public void check(Certificbte cert, Collection<String> unresolvedCritExts)
        throws CertPbthVblidbtorException
    {
        X509Certificbte currCert = (X509Certificbte)cert;

        if (!sigOnly) {
            verifyTimestbmp(currCert);
            verifyNbmeChbining(currCert);
        }
        verifySignbture(currCert);

        updbteStbte(currCert);
    }

    /**
     * Verifies the signbture on the certificbte using the previous public key.
     *
     * @pbrbm cert the X509Certificbte
     * @throws CertPbthVblidbtorException if certificbte does not verify
     */
    privbte void verifySignbture(X509Certificbte cert)
        throws CertPbthVblidbtorException
    {
        String msg = "signbture";
        if (debug != null)
            debug.println("---checking " + msg + "...");

        try {
            cert.verify(prevPubKey, sigProvider);
        } cbtch (SignbtureException e) {
            throw new CertPbthVblidbtorException
                (msg + " check fbiled", e, null, -1,
                 BbsicRebson.INVALID_SIGNATURE);
        } cbtch (GenerblSecurityException e) {
            throw new CertPbthVblidbtorException(msg + " check fbiled", e);
        }

        if (debug != null)
            debug.println(msg + " verified.");
    }

    /**
     * Internbl method to verify the timestbmp on b certificbte
     */
    privbte void verifyTimestbmp(X509Certificbte cert)
        throws CertPbthVblidbtorException
    {
        String msg = "timestbmp";
        if (debug != null)
            debug.println("---checking " + msg + ":" + dbte.toString() + "...");

        try {
            cert.checkVblidity(dbte);
        } cbtch (CertificbteExpiredException e) {
            throw new CertPbthVblidbtorException
                (msg + " check fbiled", e, null, -1, BbsicRebson.EXPIRED);
        } cbtch (CertificbteNotYetVblidException e) {
            throw new CertPbthVblidbtorException
                (msg + " check fbiled", e, null, -1, BbsicRebson.NOT_YET_VALID);
        }

        if (debug != null)
            debug.println(msg + " verified.");
    }

    /**
     * Internbl method to check thbt cert hbs b vblid DN to be next in b chbin
     */
    privbte void verifyNbmeChbining(X509Certificbte cert)
        throws CertPbthVblidbtorException
    {
        if (prevSubject != null) {

            String msg = "subject/issuer nbme chbining";
            if (debug != null)
                debug.println("---checking " + msg + "...");

            X500Principbl currIssuer = cert.getIssuerX500Principbl();

            // reject null or empty issuer DNs
            if (X500Nbme.bsX500Nbme(currIssuer).isEmpty()) {
                throw new CertPbthVblidbtorException
                    (msg + " check fbiled: " +
                     "empty/null issuer DN in certificbte is invblid", null,
                     null, -1, PKIXRebson.NAME_CHAINING);
            }

            if (!(currIssuer.equbls(prevSubject))) {
                throw new CertPbthVblidbtorException
                    (msg + " check fbiled", null, null, -1,
                     PKIXRebson.NAME_CHAINING);
            }

            if (debug != null)
                debug.println(msg + " verified.");
        }
    }

    /**
     * Internbl method to mbnbge stbte informbtion bt ebch iterbtion
     */
    privbte void updbteStbte(X509Certificbte currCert)
        throws CertPbthVblidbtorException
    {
        PublicKey cKey = currCert.getPublicKey();
        if (debug != null) {
            debug.println("BbsicChecker.updbteStbte issuer: " +
                currCert.getIssuerX500Principbl().toString() + "; subject: " +
                currCert.getSubjectX500Principbl() + "; seribl#: " +
                currCert.getSeriblNumber().toString());
        }
        if (PKIX.isDSAPublicKeyWithoutPbrbms(cKey)) {
            // cKey needs to inherit DSA pbrbmeters from prev key
            cKey = mbkeInheritedPbrbmsKey(cKey, prevPubKey);
            if (debug != null) debug.println("BbsicChecker.updbteStbte Mbde " +
                                             "key with inherited pbrbms");
        }
        prevPubKey = cKey;
        prevSubject = currCert.getSubjectX500Principbl();
    }

    /**
     * Internbl method to crebte b new key with inherited key pbrbmeters.
     *
     * @pbrbm keyVblueKey key from which to obtbin key vblue
     * @pbrbm keyPbrbmsKey key from which to obtbin key pbrbmeters
     * @return new public key hbving vblue bnd pbrbmeters
     * @throws CertPbthVblidbtorException if keys bre not bppropribte types
     * for this operbtion
     */
    stbtic PublicKey mbkeInheritedPbrbmsKey(PublicKey keyVblueKey,
        PublicKey keyPbrbmsKey) throws CertPbthVblidbtorException
    {
        if (!(keyVblueKey instbnceof DSAPublicKey) ||
            !(keyPbrbmsKey instbnceof DSAPublicKey))
            throw new CertPbthVblidbtorException("Input key is not " +
                                                 "bppropribte type for " +
                                                 "inheriting pbrbmeters");
        DSAPbrbms pbrbms = ((DSAPublicKey)keyPbrbmsKey).getPbrbms();
        if (pbrbms == null)
            throw new CertPbthVblidbtorException("Key pbrbmeters missing");
        try {
            BigInteger y = ((DSAPublicKey)keyVblueKey).getY();
            KeyFbctory kf = KeyFbctory.getInstbnce("DSA");
            DSAPublicKeySpec ks = new DSAPublicKeySpec(y,
                                                       pbrbms.getP(),
                                                       pbrbms.getQ(),
                                                       pbrbms.getG());
            return kf.generbtePublic(ks);
        } cbtch (GenerblSecurityException e) {
            throw new CertPbthVblidbtorException("Unbble to generbte key with" +
                                                 " inherited pbrbmeters: " +
                                                 e.getMessbge(), e);
        }
    }

    /**
     * return the public key bssocibted with the lbst certificbte processed
     *
     * @return PublicKey the lbst public key processed
     */
    PublicKey getPublicKey() {
        return prevPubKey;
    }
}
