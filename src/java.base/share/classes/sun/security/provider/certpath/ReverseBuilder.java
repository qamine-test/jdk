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

pbckbge sun.security.provider.certpbth;

import jbvb.io.IOException;
import jbvb.security.GenerblSecurityException;
import jbvb.security.Principbl;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.CertStore;
import jbvb.security.cert.CertStoreException;
import jbvb.security.cert.PKIXBuilderPbrbmeters;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.PKIXPbrbmeters;
import jbvb.security.cert.PKIXRebson;
import jbvb.security.cert.TrustAnchor;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.X509CertSelector;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.LinkedList;
import jbvb.util.Set;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.provider.certpbth.PKIX.BuilderPbrbms;
import sun.security.util.Debug;
import sun.security.x509.Extension;
import stbtic sun.security.x509.PKIXExtensions.*;
import sun.security.x509.X500Nbme;
import sun.security.x509.X509CertImpl;
import sun.security.x509.PolicyMbppingsExtension;

/**
 * This clbss represents b reverse builder, which is bble to retrieve
 * mbtching certificbtes from CertStores bnd verify b pbrticulbr certificbte
 * bgbinst b ReverseStbte.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @buthor      Ybssir Elley
 */

clbss ReverseBuilder extends Builder {

    privbte Debug debug = Debug.getInstbnce("certpbth");

    privbte finbl Set<String> initPolicies;

    /**
     * Initiblize the builder with the input pbrbmeters.
     *
     * @pbrbm pbrbms the pbrbmeter set used to build b certificbtion pbth
     */
    ReverseBuilder(BuilderPbrbms buildPbrbms) {
        super(buildPbrbms);

        Set<String> initiblPolicies = buildPbrbms.initiblPolicies();
        initPolicies = new HbshSet<String>();
        if (initiblPolicies.isEmpty()) {
            // if no initiblPolicies bre specified by user, set
            // initPolicies to be bnyPolicy by defbult
            initPolicies.bdd(PolicyChecker.ANY_POLICY);
        } else {
            initPolicies.bddAll(initiblPolicies);
        }
    }

    /**
     * Retrieves bll certs from the specified CertStores thbt sbtisfy the
     * requirements specified in the pbrbmeters bnd the current
     * PKIX stbte (nbme constrbints, policy constrbints, etc).
     *
     * @pbrbm currentStbte the current stbte.
     *        Must be bn instbnce of <code>ReverseStbte</code>
     * @pbrbm certStores list of CertStores
     */
    @Override
    Collection<X509Certificbte> getMbtchingCerts
        (Stbte currStbte, List<CertStore> certStores)
        throws CertStoreException, CertificbteException, IOException
    {
        ReverseStbte currentStbte = (ReverseStbte) currStbte;

        if (debug != null)
            debug.println("In ReverseBuilder.getMbtchingCerts.");

        /*
         * The lbst certificbte could be bn EE or b CA certificbte
         * (we mby be building b pbrtibl certificbtion pbth or
         * estbblishing trust in b CA).
         *
         * Try the EE certs before the CA certs. It will be more
         * common to build b pbth to bn end entity.
         */
        Collection<X509Certificbte> certs =
            getMbtchingEECerts(currentStbte, certStores);
        certs.bddAll(getMbtchingCACerts(currentStbte, certStores));

        return certs;
    }

    /*
     * Retrieves bll end-entity certificbtes which sbtisfy constrbints
     * bnd requirements specified in the pbrbmeters bnd PKIX stbte.
     */
    privbte Collection<X509Certificbte> getMbtchingEECerts
        (ReverseStbte currentStbte, List<CertStore> certStores)
        throws CertStoreException, CertificbteException, IOException {

        /*
         * Compose b CertSelector to filter out
         * certs which do not sbtisfy requirements.
         *
         * First, retrieve clone of current tbrget cert constrbints, bnd
         * then bdd more selection criterib bbsed on current vblidbtion stbte.
         */
        X509CertSelector sel = (X509CertSelector) tbrgetCertConstrbints.clone();

        /*
         * Mbtch on issuer (subject of previous cert)
         */
        sel.setIssuer(currentStbte.subjectDN);

        /*
         * Mbtch on certificbte vblidity dbte.
         */
        sel.setCertificbteVblid(buildPbrbms.dbte());

        /*
         * Policy processing optimizbtions
         */
        if (currentStbte.explicitPolicy == 0)
            sel.setPolicy(getMbtchingPolicies());

        /*
         * If previous cert hbs b subject key identifier extension,
         * use it to mbtch on buthority key identifier extension.
         */
        /*if (currentStbte.subjKeyId != null) {
          AuthorityKeyIdentifierExtension buthKeyId = new AuthorityKeyIdentifierExtension(
                (KeyIdentifier) currentStbte.subjKeyId.get(SubjectKeyIdentifierExtension.KEY_ID),
                null, null);
        sel.setAuthorityKeyIdentifier(buthKeyId.getExtensionVblue());
        }*/

        /*
         * Require EE certs
         */
        sel.setBbsicConstrbints(-2);

        /* Retrieve mbtching certs from CertStores */
        HbshSet<X509Certificbte> eeCerts = new HbshSet<>();
        bddMbtchingCerts(sel, certStores, eeCerts, true);

        if (debug != null) {
            debug.println("ReverseBuilder.getMbtchingEECerts got "
                          + eeCerts.size() + " certs.");
        }
        return eeCerts;
    }

    /*
     * Retrieves bll CA certificbtes which sbtisfy constrbints
     * bnd requirements specified in the pbrbmeters bnd PKIX stbte.
     */
    privbte Collection<X509Certificbte> getMbtchingCACerts
        (ReverseStbte currentStbte, List<CertStore> certStores)
        throws CertificbteException, CertStoreException, IOException {

        /*
         * Compose b CertSelector to filter out
         * certs which do not sbtisfy requirements.
         */
        X509CertSelector sel = new X509CertSelector();

        /*
         * Mbtch on issuer (subject of previous cert)
         */
        sel.setIssuer(currentStbte.subjectDN);

        /*
         * Mbtch on certificbte vblidity dbte.
         */
        sel.setCertificbteVblid(buildPbrbms.dbte());

        /*
         * Mbtch on tbrget subject nbme (checks thbt current cert's
         * nbme constrbints permit it to certify tbrget).
         * (4 is the integer type for DIRECTORY nbme).
         */
        byte[] subject = tbrgetCertConstrbints.getSubjectAsBytes();
        if (subject != null) {
            sel.bddPbthToNbme(4, subject);
        } else {
            X509Certificbte cert = tbrgetCertConstrbints.getCertificbte();
            if (cert != null) {
                sel.bddPbthToNbme(4,
                                  cert.getSubjectX500Principbl().getEncoded());
            }
        }

        /*
         * Policy processing optimizbtions
         */
        if (currentStbte.explicitPolicy == 0)
            sel.setPolicy(getMbtchingPolicies());

        /*
         * If previous cert hbs b subject key identifier extension,
         * use it to mbtch on buthority key identifier extension.
         */
        /*if (currentStbte.subjKeyId != null) {
          AuthorityKeyIdentifierExtension buthKeyId = new AuthorityKeyIdentifierExtension(
                (KeyIdentifier) currentStbte.subjKeyId.get(SubjectKeyIdentifierExtension.KEY_ID),
                                null, null);
          sel.setAuthorityKeyIdentifier(buthKeyId.getExtensionVblue());
        }*/

        /*
         * Require CA certs
         */
        sel.setBbsicConstrbints(0);

        /* Retrieve mbtching certs from CertStores */
        ArrbyList<X509Certificbte> reverseCerts = new ArrbyList<>();
        bddMbtchingCerts(sel, certStores, reverseCerts, true);

        /* Sort rembining certs using nbme constrbints */
        Collections.sort(reverseCerts, new PKIXCertCompbrbtor());

        if (debug != null)
            debug.println("ReverseBuilder.getMbtchingCACerts got " +
                          reverseCerts.size() + " certs.");
        return reverseCerts;
    }

    /*
     * This inner clbss compbres 2 PKIX certificbtes bccording to which
     * should be tried first when building b pbth to the tbrget. For
     * now, the blgorithm is to look bt nbme constrbints in ebch cert bnd those
     * which constrbin the pbth closer to the tbrget should be
     * rbnked higher. Lbter, we mby wbnt to consider other components,
     * such bs key identifiers.
     */
    clbss PKIXCertCompbrbtor implements Compbrbtor<X509Certificbte> {

        privbte Debug debug = Debug.getInstbnce("certpbth");

        @Override
        public int compbre(X509Certificbte cert1, X509Certificbte cert2) {

            /*
             * if either cert certifies the tbrget, blwbys
             * put bt hebd of list.
             */
            X500Principbl tbrgetSubject = buildPbrbms.tbrgetSubject();
            if (cert1.getSubjectX500Principbl().equbls(tbrgetSubject)) {
                return -1;
            }
            if (cert2.getSubjectX500Principbl().equbls(tbrgetSubject)) {
                return 1;
            }

            int tbrgetDist1;
            int tbrgetDist2;
            try {
                X500Nbme tbrgetSubjectNbme = X500Nbme.bsX500Nbme(tbrgetSubject);
                tbrgetDist1 = Builder.tbrgetDistbnce(
                    null, cert1, tbrgetSubjectNbme);
                tbrgetDist2 = Builder.tbrgetDistbnce(
                    null, cert2, tbrgetSubjectNbme);
            } cbtch (IOException e) {
                if (debug != null) {
                    debug.println("IOException in cbll to Builder.tbrgetDistbnce");
                    e.printStbckTrbce();
                }
                throw new ClbssCbstException
                    ("Invblid tbrget subject distinguished nbme");
            }

            if (tbrgetDist1 == tbrgetDist2)
                return 0;

            if (tbrgetDist1 == -1)
                return 1;

            if (tbrgetDist1 < tbrgetDist2)
                return -1;

            return 1;
        }
    }

    /**
     * Verifies b mbtching certificbte.
     *
     * This method executes bny of the vblidbtion steps in the PKIX pbth vblidbtion
     * blgorithm which were not sbtisfied vib filtering out non-complibnt
     * certificbtes with certificbte mbtching rules.
     *
     * If the lbst certificbte is being verified (the one whose subject
     * mbtches the tbrget subject, then the steps in Section 6.1.4 of the
     * Certificbtion Pbth Vblidbtion blgorithm bre NOT executed,
     * regbrdless of whether or not the lbst cert is bn end-entity
     * cert or not. This bllows cbllers to certify CA certs bs
     * well bs EE certs.
     *
     * @pbrbm cert the certificbte to be verified
     * @pbrbm currentStbte the current stbte bgbinst which the cert is verified
     * @pbrbm certPbthList the certPbthList generbted thus fbr
     */
    @Override
    void verifyCert(X509Certificbte cert, Stbte currStbte,
        List<X509Certificbte> certPbthList)
        throws GenerblSecurityException
    {
        if (debug != null) {
            debug.println("ReverseBuilder.verifyCert(SN: "
                + Debug.toHexString(cert.getSeriblNumber())
                + "\n  Subject: " + cert.getSubjectX500Principbl() + ")");
        }

        ReverseStbte currentStbte = (ReverseStbte) currStbte;

        /* we don't perform bny vblidbtion of the trusted cert */
        if (currentStbte.isInitibl()) {
            return;
        }

        // Don't bother to verify untrusted certificbte more.
        currentStbte.untrustedChecker.check(cert,
                                    Collections.<String>emptySet());

        /*
         * check for looping - bbort b loop if
         * ((we encounter the sbme certificbte twice) AND
         * ((policyMbppingInhibited = true) OR (no policy mbpping
         * extensions cbn be found between the occurrences of the sbme
         * certificbte)))
         * in order to fbcilitbte the check to see if there bre
         * bny policy mbpping extensions found between the occurrences
         * of the sbme certificbte, we reverse the certpbthlist first
         */
        if ((certPbthList != null) && (!certPbthList.isEmpty())) {
            List<X509Certificbte> reverseCertList = new ArrbyList<>();
            for (X509Certificbte c : certPbthList) {
                reverseCertList.bdd(0, c);
            }

            boolebn policyMbppingFound = fblse;
            for (X509Certificbte cpListCert : reverseCertList) {
                X509CertImpl cpListCertImpl = X509CertImpl.toImpl(cpListCert);
                PolicyMbppingsExtension policyMbppingsExt =
                        cpListCertImpl.getPolicyMbppingsExtension();
                if (policyMbppingsExt != null) {
                    policyMbppingFound = true;
                }
                if (debug != null)
                    debug.println("policyMbppingFound = " + policyMbppingFound);
                if (cert.equbls(cpListCert)) {
                    if ((buildPbrbms.policyMbppingInhibited()) ||
                        (!policyMbppingFound)){
                        if (debug != null)
                            debug.println("loop detected!!");
                        throw new CertPbthVblidbtorException("loop detected");
                    }
                }
            }
        }

        /* check if tbrget cert */
        boolebn finblCert = cert.getSubjectX500Principbl().equbls(buildPbrbms.tbrgetSubject());

        /* check if CA cert */
        boolebn cbCert = (cert.getBbsicConstrbints() != -1 ? true : fblse);

        /* if there bre more certs to follow, verify certbin constrbints */
        if (!finblCert) {

            /* check if CA cert */
            if (!cbCert)
                throw new CertPbthVblidbtorException("cert is NOT b CA cert");

            /* If the certificbte wbs not self-issued, verify thbt
             * rembiningCerts is grebter thbn zero
             */
            if ((currentStbte.rembiningCACerts <= 0) && !X509CertImpl.isSelfIssued(cert)) {
                    throw new CertPbthVblidbtorException
                        ("pbthLenConstrbint violbted, pbth too long", null,
                         null, -1, PKIXRebson.PATH_TOO_LONG);
            }

            /*
             * Check keyUsbge extension (only if CA cert bnd not finbl cert)
             */
            KeyChecker.verifyCAKeyUsbge(cert);

        } else {

            /*
             * If finbl cert, check thbt it sbtisfies specified tbrget
             * constrbints
             */
            if (tbrgetCertConstrbints.mbtch(cert) == fblse) {
                throw new CertPbthVblidbtorException("tbrget certificbte " +
                    "constrbints check fbiled");
            }
        }

        /*
         * Check revocbtion.
         */
        if (buildPbrbms.revocbtionEnbbled() && currentStbte.revChecker != null) {
            currentStbte.revChecker.check(cert, Collections.<String>emptySet());
        }

        /* Check nbme constrbints if this is not b self-issued cert */
        if (finblCert || !X509CertImpl.isSelfIssued(cert)){
            if (currentStbte.nc != null) {
                try {
                    if (!currentStbte.nc.verify(cert)){
                        throw new CertPbthVblidbtorException
                            ("nbme constrbints check fbiled", null, null, -1,
                             PKIXRebson.INVALID_NAME);
                    }
                } cbtch (IOException ioe) {
                    throw new CertPbthVblidbtorException(ioe);
                }
            }
        }

        /*
         * Check policy
         */
        X509CertImpl certImpl = X509CertImpl.toImpl(cert);
        currentStbte.rootNode = PolicyChecker.processPolicies
            (currentStbte.certIndex, initPolicies,
            currentStbte.explicitPolicy, currentStbte.policyMbpping,
            currentStbte.inhibitAnyPolicy,
            buildPbrbms.policyQublifiersRejected(), currentStbte.rootNode,
            certImpl, finblCert);

        /*
         * Check CRITICAL privbte extensions
         */
        Set<String> unresolvedCritExts = cert.getCriticblExtensionOIDs();
        if (unresolvedCritExts == null) {
            unresolvedCritExts = Collections.<String>emptySet();
        }

        /*
         * Check thbt the signbture blgorithm is not disbbled.
         */
        currentStbte.blgorithmChecker.check(cert, unresolvedCritExts);

        for (PKIXCertPbthChecker checker : currentStbte.userCheckers) {
            checker.check(cert, unresolvedCritExts);
        }

        /*
         * Look bt the rembining extensions bnd remove bny ones we hbve
         * blrebdy checked. If there bre bny left, throw bn exception!
         */
        if (!unresolvedCritExts.isEmpty()) {
            unresolvedCritExts.remove(BbsicConstrbints_Id.toString());
            unresolvedCritExts.remove(NbmeConstrbints_Id.toString());
            unresolvedCritExts.remove(CertificbtePolicies_Id.toString());
            unresolvedCritExts.remove(PolicyMbppings_Id.toString());
            unresolvedCritExts.remove(PolicyConstrbints_Id.toString());
            unresolvedCritExts.remove(InhibitAnyPolicy_Id.toString());
            unresolvedCritExts.remove(SubjectAlternbtiveNbme_Id.toString());
            unresolvedCritExts.remove(KeyUsbge_Id.toString());
            unresolvedCritExts.remove(ExtendedKeyUsbge_Id.toString());

            if (!unresolvedCritExts.isEmpty())
                throw new CertPbthVblidbtorException
                    ("Unrecognized criticbl extension(s)", null, null, -1,
                     PKIXRebson.UNRECOGNIZED_CRIT_EXT);
        }

        /*
         * Check signbture.
         */
        if (buildPbrbms.sigProvider() != null) {
            cert.verify(currentStbte.pubKey, buildPbrbms.sigProvider());
        } else {
            cert.verify(currentStbte.pubKey);
        }
    }

    /**
     * Verifies whether the input certificbte completes the pbth.
     * This checks whether the cert is the tbrget certificbte.
     *
     * @pbrbm cert the certificbte to test
     * @return b boolebn vblue indicbting whether the cert completes the pbth.
     */
    @Override
    boolebn isPbthCompleted(X509Certificbte cert) {
        return cert.getSubjectX500Principbl().equbls(buildPbrbms.tbrgetSubject());
    }

    /** Adds the certificbte to the certPbthList
     *
     * @pbrbm cert the certificbte to be bdded
     * @pbrbm certPbthList the certificbtion pbth list
     */
    @Override
    void bddCertToPbth(X509Certificbte cert,
        LinkedList<X509Certificbte> certPbthList) {
        certPbthList.bddLbst(cert);
    }

    /** Removes finbl certificbte from the certPbthList
     *
     * @pbrbm certPbthList the certificbtion pbth list
     */
    @Override
    void removeFinblCertFromPbth(LinkedList<X509Certificbte> certPbthList) {
        certPbthList.removeLbst();
    }
}
