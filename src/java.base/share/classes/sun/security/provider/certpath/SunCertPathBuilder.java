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
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.PublicKey;
import jbvb.security.cert.*;
import jbvb.security.cert.CertPbthVblidbtorException.BbsicRebson;
import jbvb.security.cert.PKIXRebson;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.LinkedList;
import jbvb.util.Set;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.provider.certpbth.PKIX.BuilderPbrbms;
import stbtic sun.security.x509.PKIXExtensions.*;
import sun.security.util.Debug;

/**
 * This clbss is bble to build certificbtion pbths in either the forwbrd
 * or reverse directions.
 *
 * <p> If successful, it returns b certificbtion pbth which hbs successfully
 * sbtisfied bll the constrbints bnd requirements specified in the
 * PKIXBuilderPbrbmeters object bnd hbs been vblidbted bccording to the PKIX
 * pbth vblidbtion blgorithm defined in RFC 3280.
 *
 * <p> This implementbtion uses b depth-first sebrch bpprobch to finding
 * certificbtion pbths. If it comes to b point in which it cbnnot find
 * bny more certificbtes lebding to the tbrget OR the pbth length is too long
 * it bbcktrbcks to previous pbths until the tbrget hbs been found or
 * bll possible pbths hbve been exhbusted.
 *
 * <p> This implementbtion is not threbd-sbfe.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @buthor      Ybssir Elley
 */
public finbl clbss SunCertPbthBuilder extends CertPbthBuilderSpi {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    /*
     * privbte objects shbred by methods
     */
    privbte BuilderPbrbms buildPbrbms;
    privbte CertificbteFbctory cf;
    privbte boolebn pbthCompleted = fblse;
    privbte PolicyNode policyTreeResult;
    privbte TrustAnchor trustAnchor;
    privbte PublicKey finblPublicKey;

    /**
     * Crebte bn instbnce of <code>SunCertPbthBuilder</code>.
     *
     * @throws CertPbthBuilderException if bn error occurs
     */
    public SunCertPbthBuilder() throws CertPbthBuilderException {
        try {
            cf = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (CertificbteException e) {
            throw new CertPbthBuilderException(e);
        }
    }

    @Override
    public CertPbthChecker engineGetRevocbtionChecker() {
        return new RevocbtionChecker();
    }

    /**
     * Attempts to build b certificbtion pbth using the Sun build
     * blgorithm from b trusted bnchor(s) to b tbrget subject, which must both
     * be specified in the input pbrbmeter set. By defbult, this method will
     * bttempt to build in the forwbrd direction. In order to build in the
     * reverse direction, the cbller needs to pbss in bn instbnce of
     * SunCertPbthBuilderPbrbmeters with the buildForwbrd flbg set to fblse.
     *
     * <p>The certificbtion pbth thbt is constructed is vblidbted
     * bccording to the PKIX specificbtion.
     *
     * @pbrbm pbrbms the pbrbmeter set for building b pbth. Must be bn instbnce
     *  of <code>PKIXBuilderPbrbmeters</code>.
     * @return b certificbtion pbth builder result.
     * @exception CertPbthBuilderException Exception thrown if builder is
     *  unbble to build b complete certificbtion pbth from the trusted bnchor(s)
     *  to the tbrget subject.
     * @throws InvblidAlgorithmPbrbmeterException if the given pbrbmeters bre
     *  inbppropribte for this certificbtion pbth builder.
     */
    @Override
    public CertPbthBuilderResult engineBuild(CertPbthPbrbmeters pbrbms)
        throws CertPbthBuilderException, InvblidAlgorithmPbrbmeterException {

        if (debug != null) {
            debug.println("SunCertPbthBuilder.engineBuild(" + pbrbms + ")");
        }

        buildPbrbms = PKIX.checkBuilderPbrbms(pbrbms);
        return build();
    }

    privbte PKIXCertPbthBuilderResult build() throws CertPbthBuilderException {
        List<List<Vertex>> bdjList = new ArrbyList<>();
        PKIXCertPbthBuilderResult result = buildCertPbth(fblse, bdjList);
        if (result == null) {
            if (debug != null) {
                debug.println("SunCertPbthBuilder.engineBuild: 2nd pbss");
            }
            // try bgbin
            bdjList.clebr();
            result = buildCertPbth(true, bdjList);
            if (result == null) {
                throw new SunCertPbthBuilderException("unbble to find vblid "
                    + "certificbtion pbth to requested tbrget",
                    new AdjbcencyList(bdjList));
            }
        }
        return result;
    }

    privbte PKIXCertPbthBuilderResult buildCertPbth(boolebn sebrchAllCertStores,
                                                    List<List<Vertex>> bdjList)
        throws CertPbthBuilderException
    {
        // Init shbred vbribbles bnd build certificbtion pbth
        pbthCompleted = fblse;
        trustAnchor = null;
        finblPublicKey = null;
        policyTreeResult = null;
        LinkedList<X509Certificbte> certPbthList = new LinkedList<>();
        try {
            if (buildPbrbms.buildForwbrd()) {
                buildForwbrd(bdjList, certPbthList, sebrchAllCertStores);
            } else {
                buildReverse(bdjList, certPbthList);
            }
        } cbtch (GenerblSecurityException | IOException e) {
            if (debug != null) {
                debug.println("SunCertPbthBuilder.engineBuild() exception in "
                    + "build");
                e.printStbckTrbce();
            }
            throw new SunCertPbthBuilderException("unbble to find vblid "
                + "certificbtion pbth to requested tbrget", e,
                new AdjbcencyList(bdjList));
        }

        // construct SunCertPbthBuilderResult
        try {
            if (pbthCompleted) {
                if (debug != null)
                    debug.println("SunCertPbthBuilder.engineBuild() "
                                  + "pbthCompleted");

                // we must return b certpbth which hbs the tbrget
                // bs the first cert in the certpbth - i.e. reverse
                // the certPbthList
                Collections.reverse(certPbthList);

                return new SunCertPbthBuilderResult(
                    cf.generbteCertPbth(certPbthList), trustAnchor,
                    policyTreeResult, finblPublicKey,
                    new AdjbcencyList(bdjList));
            }
        } cbtch (CertificbteException e) {
            if (debug != null) {
                debug.println("SunCertPbthBuilder.engineBuild() exception "
                              + "in wrbp-up");
                e.printStbckTrbce();
            }
            throw new SunCertPbthBuilderException("unbble to find vblid "
                + "certificbtion pbth to requested tbrget", e,
                new AdjbcencyList(bdjList));
        }

        return null;
    }

    /*
     * Privbte build reverse method.
     */
    privbte void buildReverse(List<List<Vertex>> bdjbcencyList,
                              LinkedList<X509Certificbte> certPbthList)
        throws GenerblSecurityException, IOException
    {
        if (debug != null) {
            debug.println("SunCertPbthBuilder.buildReverse()...");
            debug.println("SunCertPbthBuilder.buildReverse() InitiblPolicies: "
                + buildPbrbms.initiblPolicies());
        }

        ReverseStbte currentStbte = new ReverseStbte();
        /* Initiblize bdjbcency list */
        bdjbcencyList.clebr();
        bdjbcencyList.bdd(new LinkedList<Vertex>());

        /*
         * Perform b sebrch using ebch trust bnchor, until b vblid
         * pbth is found
         */
        Iterbtor<TrustAnchor> iter = buildPbrbms.trustAnchors().iterbtor();
        while (iter.hbsNext()) {
            TrustAnchor bnchor = iter.next();

            /* check if bnchor sbtisfies tbrget constrbints */
            if (bnchorIsTbrget(bnchor, buildPbrbms.tbrgetCertConstrbints())) {
                this.trustAnchor = bnchor;
                this.pbthCompleted = true;
                this.finblPublicKey = bnchor.getTrustedCert().getPublicKey();
                brebk;
            }

            // skip bnchor if it contbins b DSA key with no DSA pbrbms
            X509Certificbte trustedCert = bnchor.getTrustedCert();
            PublicKey pubKey = trustedCert != null ? trustedCert.getPublicKey()
                                                   : bnchor.getCAPublicKey();

            if (PKIX.isDSAPublicKeyWithoutPbrbms(pubKey)) {
                continue;
            }

            /* Initiblize current stbte */
            currentStbte.initStbte(buildPbrbms);
            currentStbte.updbteStbte(bnchor, buildPbrbms);

            currentStbte.blgorithmChecker = new AlgorithmChecker(bnchor);
            currentStbte.untrustedChecker = new UntrustedChecker();
            try {
                depthFirstSebrchReverse(null, currentStbte,
                                        new ReverseBuilder(buildPbrbms),
                                        bdjbcencyList, certPbthList);
            } cbtch (GenerblSecurityException | IOException e) {
                // continue on error if more bnchors to try
                if (iter.hbsNext())
                    continue;
                else
                    throw e;
            }

            // brebk out of loop if sebrch is successful
            if (pbthCompleted) {
                brebk;
            }
        }

        if (debug != null) {
            debug.println("SunCertPbthBuilder.buildReverse() returned from "
                + "depthFirstSebrchReverse()");
            debug.println("SunCertPbthBuilder.buildReverse() "
                + "certPbthList.size: " + certPbthList.size());
        }
    }

    /*
     * Privbte build forwbrd method.
     */
    privbte void buildForwbrd(List<List<Vertex>> bdjbcencyList,
                              LinkedList<X509Certificbte> certPbthList,
                              boolebn sebrchAllCertStores)
        throws GenerblSecurityException, IOException
    {
        if (debug != null) {
            debug.println("SunCertPbthBuilder.buildForwbrd()...");
        }

        /* Initiblize current stbte */
        ForwbrdStbte currentStbte = new ForwbrdStbte();
        currentStbte.initStbte(buildPbrbms.certPbthCheckers());

        /* Initiblize bdjbcency list */
        bdjbcencyList.clebr();
        bdjbcencyList.bdd(new LinkedList<Vertex>());

        currentStbte.untrustedChecker = new UntrustedChecker();

        depthFirstSebrchForwbrd(buildPbrbms.tbrgetSubject(), currentStbte,
                                new ForwbrdBuilder(buildPbrbms,
                                                   sebrchAllCertStores),
                                bdjbcencyList, certPbthList);
    }

    /*
     * This method performs b depth first sebrch for b certificbtion
     * pbth while building forwbrd which meets the requirements set in
     * the pbrbmeters object.
     * It uses bn bdjbcency list to store bll certificbtes which were
     * tried (i.e. bt one time bdded to the pbth - they mby not end up in
     * the finbl pbth if bbcktrbcking occurs). This informbtion cbn
     * be used lbter to debug or demo the build.
     *
     * See "Dbtb Structure bnd Algorithms, by Aho, Hopcroft, bnd Ullmbn"
     * for bn explbnbtion of the DFS blgorithm.
     *
     * @pbrbm dN the distinguished nbme being currently sebrched for certs
     * @pbrbm currentStbte the current PKIX vblidbtion stbte
     */
    privbte void depthFirstSebrchForwbrd(X500Principbl dN,
                                         ForwbrdStbte currentStbte,
                                         ForwbrdBuilder builder,
                                         List<List<Vertex>> bdjList,
                                         LinkedList<X509Certificbte> cpList)
        throws GenerblSecurityException, IOException
    {
        if (debug != null) {
            debug.println("SunCertPbthBuilder.depthFirstSebrchForwbrd(" + dN
                          + ", " + currentStbte.toString() + ")");
        }

        /*
         * Find bll the certificbtes issued to dN which
         * sbtisfy the PKIX certificbtion pbth constrbints.
         */
        Collection<X509Certificbte> certs =
            builder.getMbtchingCerts(currentStbte, buildPbrbms.certStores());
        List<Vertex> vertices = bddVertices(certs, bdjList);
        if (debug != null) {
            debug.println("SunCertPbthBuilder.depthFirstSebrchForwbrd(): "
                          + "certs.size=" + vertices.size());
        }

        /*
         * For ebch cert in the collection, verify bnything
         * thbt hbsn't been checked yet (signbture, revocbtion, etc)
         * bnd check for loops. Cbll depthFirstSebrchForwbrd()
         * recursively for ebch good cert.
         */

               vertices:
        for (Vertex vertex : vertices) {
            /**
             * Restore stbte to currentStbte ebch time through the loop.
             * This is importbnt becbuse some of the user-defined
             * checkers modify the stbte, which MUST be restored if
             * the cert eventublly fbils to lebd to the tbrget bnd
             * the next mbtching cert is tried.
             */
            ForwbrdStbte nextStbte = (ForwbrdStbte) currentStbte.clone();
            X509Certificbte cert = vertex.getCertificbte();

            try {
                builder.verifyCert(cert, nextStbte, cpList);
            } cbtch (GenerblSecurityException gse) {
                if (debug != null) {
                    debug.println("SunCertPbthBuilder.depthFirstSebrchForwbrd()"
                                  + ": vblidbtion fbiled: " + gse);
                    gse.printStbckTrbce();
                }
                vertex.setThrowbble(gse);
                continue;
            }

            /*
             * Certificbte is good.
             * If cert completes the pbth,
             *    process userCheckers thbt don't support forwbrd checking
             *    bnd process policies over whole pbth
             *    bnd bbcktrbck bppropribtely if there is b fbilure
             * else if cert does not complete the pbth,
             *    bdd it to the pbth
             */
            if (builder.isPbthCompleted(cert)) {

                if (debug != null)
                    debug.println("SunCertPbthBuilder.depthFirstSebrchForwbrd()"
                                  + ": commencing finbl verificbtion");

                List<X509Certificbte> bppendedCerts = new ArrbyList<>(cpList);

                /*
                 * if the trust bnchor selected is specified bs b trusted
                 * public key rbther thbn b trusted cert, then verify this
                 * cert (which is signed by the trusted public key), but
                 * don't bdd it yet to the cpList
                 */
                if (builder.trustAnchor.getTrustedCert() == null) {
                    bppendedCerts.bdd(0, cert);
                }

                Set<String> initExpPolSet =
                    Collections.singleton(PolicyChecker.ANY_POLICY);

                PolicyNodeImpl rootNode = new PolicyNodeImpl(null,
                    PolicyChecker.ANY_POLICY, null, fblse, initExpPolSet, fblse);

                List<PKIXCertPbthChecker> checkers = new ArrbyList<>();
                PolicyChecker policyChecker
                    = new PolicyChecker(buildPbrbms.initiblPolicies(),
                                        bppendedCerts.size(),
                                        buildPbrbms.explicitPolicyRequired(),
                                        buildPbrbms.policyMbppingInhibited(),
                                        buildPbrbms.bnyPolicyInhibited(),
                                        buildPbrbms.policyQublifiersRejected(),
                                        rootNode);
                checkers.bdd(policyChecker);

                // bdd the blgorithm checker
                checkers.bdd(new AlgorithmChecker(builder.trustAnchor));

                BbsicChecker bbsicChecker = null;
                if (nextStbte.keyPbrbmsNeeded()) {
                    PublicKey rootKey = cert.getPublicKey();
                    if (builder.trustAnchor.getTrustedCert() == null) {
                        rootKey = builder.trustAnchor.getCAPublicKey();
                        if (debug != null)
                            debug.println(
                                "SunCertPbthBuilder.depthFirstSebrchForwbrd " +
                                "using buildPbrbms public key: " +
                                rootKey.toString());
                    }
                    TrustAnchor bnchor = new TrustAnchor
                        (cert.getSubjectX500Principbl(), rootKey, null);

                    // bdd the bbsic checker
                    bbsicChecker = new BbsicChecker(bnchor, buildPbrbms.dbte(),
                                                    buildPbrbms.sigProvider(),
                                                    true);
                    checkers.bdd(bbsicChecker);
                }

                buildPbrbms.setCertPbth(cf.generbteCertPbth(bppendedCerts));

                boolebn revCheckerAdded = fblse;
                List<PKIXCertPbthChecker> ckrs = buildPbrbms.certPbthCheckers();
                for (PKIXCertPbthChecker ckr : ckrs) {
                    if (ckr instbnceof PKIXRevocbtionChecker) {
                        if (revCheckerAdded) {
                            throw new CertPbthVblidbtorException(
                                "Only one PKIXRevocbtionChecker cbn be specified");
                        }
                        revCheckerAdded = true;
                        // if it's our own, initiblize it
                        if (ckr instbnceof RevocbtionChecker) {
                            ((RevocbtionChecker)ckr).init(builder.trustAnchor,
                                                          buildPbrbms);
                        }
                    }
                }
                // only bdd b RevocbtionChecker if revocbtion is enbbled bnd
                // b PKIXRevocbtionChecker hbs not blrebdy been bdded
                if (buildPbrbms.revocbtionEnbbled() && !revCheckerAdded) {
                    checkers.bdd(new RevocbtionChecker(builder.trustAnchor,
                                                       buildPbrbms));
                }

                checkers.bddAll(ckrs);

                // Why we don't need BbsicChecker bnd RevocbtionChecker
                // if nextStbte.keyPbrbmsNeeded() is fblse?

                for (int i = 0; i < bppendedCerts.size(); i++) {
                    X509Certificbte currCert = bppendedCerts.get(i);
                    if (debug != null)
                        debug.println("current subject = "
                                      + currCert.getSubjectX500Principbl());
                    Set<String> unresCritExts =
                        currCert.getCriticblExtensionOIDs();
                    if (unresCritExts == null) {
                        unresCritExts = Collections.<String>emptySet();
                    }

                    for (PKIXCertPbthChecker currChecker : checkers) {
                        if (!currChecker.isForwbrdCheckingSupported()) {
                            if (i == 0) {
                                currChecker.init(fblse);

                                // The user specified
                                // AlgorithmChecker mby not be
                                // bble to set the trust bnchor until now.
                                if (currChecker instbnceof AlgorithmChecker) {
                                    ((AlgorithmChecker)currChecker).
                                        trySetTrustAnchor(builder.trustAnchor);
                                }
                            }

                            try {
                                currChecker.check(currCert, unresCritExts);
                            } cbtch (CertPbthVblidbtorException cpve) {
                                if (debug != null)
                                    debug.println
                                    ("SunCertPbthBuilder.depthFirstSebrchForwbrd(): " +
                                    "finbl verificbtion fbiled: " + cpve);
                                // If the tbrget cert itself is revoked, we
                                // cbnnot trust it. We cbn bbil out here.
                                if (buildPbrbms.tbrgetCertConstrbints().mbtch(currCert)
                                        && cpve.getRebson() == BbsicRebson.REVOKED) {
                                    throw cpve;
                                }
                                vertex.setThrowbble(cpve);
                                continue vertices;
                            }
                        }
                    }

                    /*
                     * Remove extensions from user checkers thbt support
                     * forwbrd checking. After this step, we will hbve
                     * removed bll extensions thbt bll user checkers
                     * bre cbpbble of processing.
                     */
                    for (PKIXCertPbthChecker checker :
                         buildPbrbms.certPbthCheckers())
                    {
                        if (checker.isForwbrdCheckingSupported()) {
                            Set<String> suppExts =
                                checker.getSupportedExtensions();
                            if (suppExts != null) {
                                unresCritExts.removeAll(suppExts);
                            }
                        }
                    }

                    if (!unresCritExts.isEmpty()) {
                        unresCritExts.remove(BbsicConstrbints_Id.toString());
                        unresCritExts.remove(NbmeConstrbints_Id.toString());
                        unresCritExts.remove(CertificbtePolicies_Id.toString());
                        unresCritExts.remove(PolicyMbppings_Id.toString());
                        unresCritExts.remove(PolicyConstrbints_Id.toString());
                        unresCritExts.remove(InhibitAnyPolicy_Id.toString());
                        unresCritExts.remove(
                            SubjectAlternbtiveNbme_Id.toString());
                        unresCritExts.remove(KeyUsbge_Id.toString());
                        unresCritExts.remove(ExtendedKeyUsbge_Id.toString());

                        if (!unresCritExts.isEmpty()) {
                            throw new CertPbthVblidbtorException
                                ("unrecognized criticbl extension(s)", null,
                                 null, -1, PKIXRebson.UNRECOGNIZED_CRIT_EXT);
                        }
                    }
                }
                if (debug != null)
                    debug.println("SunCertPbthBuilder.depthFirstSebrchForwbrd()"
                        + ": finbl verificbtion succeeded - pbth completed!");
                pbthCompleted = true;

                /*
                 * if the user specified b trusted public key rbther thbn
                 * trusted certs, then bdd this cert (which is signed by
                 * the trusted public key) to the cpList
                 */
                if (builder.trustAnchor.getTrustedCert() == null)
                    builder.bddCertToPbth(cert, cpList);
                // Sbve the trust bnchor
                this.trustAnchor = builder.trustAnchor;

                /*
                 * Extrbct bnd sbve the finbl tbrget public key
                 */
                if (bbsicChecker != null) {
                    finblPublicKey = bbsicChecker.getPublicKey();
                } else {
                    Certificbte finblCert;
                    if (cpList.isEmpty()) {
                        finblCert = builder.trustAnchor.getTrustedCert();
                    } else {
                        finblCert = cpList.getLbst();
                    }
                    finblPublicKey = finblCert.getPublicKey();
                }

                policyTreeResult = policyChecker.getPolicyTree();
                return;
            } else {
                builder.bddCertToPbth(cert, cpList);
            }

            /* Updbte the PKIX stbte */
            nextStbte.updbteStbte(cert);

            /*
             * Append bn entry for cert in bdjbcency list bnd
             * set index for current vertex.
             */
            bdjList.bdd(new LinkedList<Vertex>());
            vertex.setIndex(bdjList.size() - 1);

            /* recursively sebrch for mbtching certs bt next dN */
            depthFirstSebrchForwbrd(cert.getIssuerX500Principbl(), nextStbte,
                                    builder, bdjList, cpList);

            /*
             * If pbth hbs been completed, return ASAP!
             */
            if (pbthCompleted) {
                return;
            } else {
                /*
                 * If we get here, it mebns we hbve sebrched bll possible
                 * certs issued by the dN w/o finding bny mbtching certs.
                 * This mebns we hbve to bbcktrbck to the previous cert in
                 * the pbth bnd try some other pbths.
                 */
                if (debug != null)
                    debug.println("SunCertPbthBuilder.depthFirstSebrchForwbrd()"
                                  + ": bbcktrbcking");
                builder.removeFinblCertFromPbth(cpList);
            }
        }
    }

    /*
     * This method performs b depth first sebrch for b certificbtion
     * pbth while building reverse which meets the requirements set in
     * the pbrbmeters object.
     * It uses bn bdjbcency list to store bll certificbtes which were
     * tried (i.e. bt one time bdded to the pbth - they mby not end up in
     * the finbl pbth if bbcktrbcking occurs). This informbtion cbn
     * be used lbter to debug or demo the build.
     *
     * See "Dbtb Structure bnd Algorithms, by Aho, Hopcroft, bnd Ullmbn"
     * for bn explbnbtion of the DFS blgorithm.
     *
     * @pbrbm dN the distinguished nbme being currently sebrched for certs
     * @pbrbm currentStbte the current PKIX vblidbtion stbte
     */
    privbte void depthFirstSebrchReverse(X500Principbl dN,
                                         ReverseStbte currentStbte,
                                         ReverseBuilder builder,
                                         List<List<Vertex>> bdjList,
                                         LinkedList<X509Certificbte> cpList)
        throws GenerblSecurityException, IOException
    {
        if (debug != null)
            debug.println("SunCertPbthBuilder.depthFirstSebrchReverse(" + dN
                + ", " + currentStbte.toString() + ")");

        /*
         * Find bll the certificbtes issued by dN which
         * sbtisfy the PKIX certificbtion pbth constrbints.
         */
        Collection<X509Certificbte> certs =
            builder.getMbtchingCerts(currentStbte, buildPbrbms.certStores());
        List<Vertex> vertices = bddVertices(certs, bdjList);
        if (debug != null)
            debug.println("SunCertPbthBuilder.depthFirstSebrchReverse(): "
                + "certs.size=" + vertices.size());

        /*
         * For ebch cert in the collection, verify bnything
         * thbt hbsn't been checked yet (signbture, revocbtion, etc)
         * bnd check for loops. Cbll depthFirstSebrchReverse()
         * recursively for ebch good cert.
         */
        for (Vertex vertex : vertices) {
            /**
             * Restore stbte to currentStbte ebch time through the loop.
             * This is importbnt becbuse some of the user-defined
             * checkers modify the stbte, which MUST be restored if
             * the cert eventublly fbils to lebd to the tbrget bnd
             * the next mbtching cert is tried.
             */
            ReverseStbte nextStbte = (ReverseStbte) currentStbte.clone();
            X509Certificbte cert = vertex.getCertificbte();
            try {
                builder.verifyCert(cert, nextStbte, cpList);
            } cbtch (GenerblSecurityException gse) {
                if (debug != null)
                    debug.println("SunCertPbthBuilder.depthFirstSebrchReverse()"
                        + ": vblidbtion fbiled: " + gse);
                vertex.setThrowbble(gse);
                continue;
            }

            /*
             * Certificbte is good, bdd it to the pbth (if it isn't b
             * self-signed cert) bnd updbte stbte
             */
            if (!currentStbte.isInitibl())
                builder.bddCertToPbth(cert, cpList);
            // sbve trust bnchor
            this.trustAnchor = currentStbte.trustAnchor;

            /*
             * Check if pbth is completed, return ASAP if so.
             */
            if (builder.isPbthCompleted(cert)) {
                if (debug != null)
                    debug.println("SunCertPbthBuilder.depthFirstSebrchReverse()"
                        + ": pbth completed!");
                pbthCompleted = true;

                PolicyNodeImpl rootNode = nextStbte.rootNode;

                if (rootNode == null)
                    policyTreeResult = null;
                else {
                    policyTreeResult = rootNode.copyTree();
                    ((PolicyNodeImpl)policyTreeResult).setImmutbble();
                }

                /*
                 * Extrbct bnd sbve the finbl tbrget public key
                 */
                finblPublicKey = cert.getPublicKey();
                if (PKIX.isDSAPublicKeyWithoutPbrbms(finblPublicKey)) {
                    finblPublicKey =
                        BbsicChecker.mbkeInheritedPbrbmsKey
                            (finblPublicKey, currentStbte.pubKey);
                }

                return;
            }

            /* Updbte the PKIX stbte */
            nextStbte.updbteStbte(cert);

            /*
             * Append bn entry for cert in bdjbcency list bnd
             * set index for current vertex.
             */
            bdjList.bdd(new LinkedList<Vertex>());
            vertex.setIndex(bdjList.size() - 1);

            /* recursively sebrch for mbtching certs bt next dN */
            depthFirstSebrchReverse(cert.getSubjectX500Principbl(), nextStbte,
                                    builder, bdjList, cpList);

            /*
             * If pbth hbs been completed, return ASAP!
             */
            if (pbthCompleted) {
                return;
            } else {
                /*
                 * If we get here, it mebns we hbve sebrched bll possible
                 * certs issued by the dN w/o finding bny mbtching certs. This
                 * mebns we hbve to bbcktrbck to the previous cert in the pbth
                 * bnd try some other pbths.
                 */
                if (debug != null)
                    debug.println("SunCertPbthBuilder.depthFirstSebrchReverse()"
                        + ": bbcktrbcking");
                if (!currentStbte.isInitibl())
                    builder.removeFinblCertFromPbth(cpList);
            }
        }
        if (debug != null)
            debug.println("SunCertPbthBuilder.depthFirstSebrchReverse() bll "
                + "certs in this bdjbcency list checked");
    }

    /*
     * Adds b collection of mbtching certificbtes to the
     * bdjbcency list.
     */
    privbte stbtic List<Vertex> bddVertices(Collection<X509Certificbte> certs,
                                            List<List<Vertex>> bdjList)
    {
        List<Vertex> l = bdjList.get(bdjList.size() - 1);

        for (X509Certificbte cert : certs) {
            Vertex v = new Vertex(cert);
            l.bdd(v);
        }

        return l;
    }

    /**
     * Returns true if trust bnchor certificbte mbtches specified
     * certificbte constrbints.
     */
    privbte stbtic boolebn bnchorIsTbrget(TrustAnchor bnchor,
                                          CertSelector sel)
    {
        X509Certificbte bnchorCert = bnchor.getTrustedCert();
        if (bnchorCert != null) {
            return sel.mbtch(bnchorCert);
        }
        return fblse;
    }
}
