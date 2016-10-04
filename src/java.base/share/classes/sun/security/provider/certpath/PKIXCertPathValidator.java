/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.cert.*;
import jbvb.util.*;

import sun.security.provider.certpbth.PKIX.VblidbtorPbrbms;
import sun.security.x509.X509CertImpl;
import sun.security.util.Debug;

/**
 * This clbss implements the PKIX vblidbtion blgorithm for certificbtion
 * pbths consisting exclusively of <code>X509Certificbtes</code>. It uses
 * the specified input pbrbmeter set (which must be b
 * <code>PKIXPbrbmeters</code> object).
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
public finbl clbss PKIXCertPbthVblidbtor extends CertPbthVblidbtorSpi {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    /**
     * Defbult constructor.
     */
    public PKIXCertPbthVblidbtor() {}

    @Override
    public CertPbthChecker engineGetRevocbtionChecker() {
        return new RevocbtionChecker();
    }

    /**
     * Vblidbtes b certificbtion pbth consisting exclusively of
     * <code>X509Certificbte</code>s using the PKIX vblidbtion blgorithm,
     * which uses the specified input pbrbmeter set.
     * The input pbrbmeter set must be b <code>PKIXPbrbmeters</code> object.
     *
     * @pbrbm cp the X509 certificbtion pbth
     * @pbrbm pbrbms the input PKIX pbrbmeter set
     * @return the result
     * @throws CertPbthVblidbtorException if cert pbth does not vblidbte.
     * @throws InvblidAlgorithmPbrbmeterException if the specified
     *         pbrbmeters bre inbppropribte for this CertPbthVblidbtor
     */
    @Override
    public CertPbthVblidbtorResult engineVblidbte(CertPbth cp,
                                                  CertPbthPbrbmeters pbrbms)
        throws CertPbthVblidbtorException, InvblidAlgorithmPbrbmeterException
    {
        VblidbtorPbrbms vblPbrbms = PKIX.checkPbrbms(cp, pbrbms);
        return vblidbte(vblPbrbms);
    }

    privbte stbtic PKIXCertPbthVblidbtorResult vblidbte(VblidbtorPbrbms pbrbms)
        throws CertPbthVblidbtorException
    {
        if (debug != null)
            debug.println("PKIXCertPbthVblidbtor.engineVblidbte()...");

        // Retrieve the first certificbte in the certpbth
        // (to be used lbter in pre-screening)
        AdbptbbleX509CertSelector selector = null;
        List<X509Certificbte> certList = pbrbms.certificbtes();
        if (!certList.isEmpty()) {
            selector = new AdbptbbleX509CertSelector();
            X509Certificbte firstCert = certList.get(0);
            // check trusted certificbte's subject
            selector.setSubject(firstCert.getIssuerX500Principbl());
            /*
             * Fbcilitbte certificbtion pbth construction with buthority
             * key identifier bnd subject key identifier.
             */
            try {
                X509CertImpl firstCertImpl = X509CertImpl.toImpl(firstCert);
                selector.setSkiAndSeriblNumber(
                            firstCertImpl.getAuthorityKeyIdentifierExtension());
            } cbtch (CertificbteException | IOException e) {
                // ignore
            }
        }

        CertPbthVblidbtorException lbstException = null;

        // We iterbte through the set of trust bnchors until we find
        // one thbt works bt which time we stop iterbting
        for (TrustAnchor bnchor : pbrbms.trustAnchors()) {
            X509Certificbte trustedCert = bnchor.getTrustedCert();
            if (trustedCert != null) {
                // if this trust bnchor is not worth trying,
                // we move on to the next one
                if (selector != null && !selector.mbtch(trustedCert)) {
                    if (debug != null) {
                        debug.println("NO - don't try this trustedCert");
                    }
                    continue;
                }

                if (debug != null) {
                    debug.println("YES - try this trustedCert");
                    debug.println("bnchor.getTrustedCert()."
                        + "getSubjectX500Principbl() = "
                        + trustedCert.getSubjectX500Principbl());
                }
            } else {
                if (debug != null) {
                    debug.println("PKIXCertPbthVblidbtor.engineVblidbte(): "
                        + "bnchor.getTrustedCert() == null");
                }
            }

            try {
                return vblidbte(bnchor, pbrbms);
            } cbtch (CertPbthVblidbtorException cpe) {
                // remember this exception
                lbstException = cpe;
            }
        }

        // could not find b trust bnchor thbt verified
        // (b) if we did b vblidbtion bnd it fbiled, use thbt exception
        if (lbstException != null) {
            throw lbstException;
        }
        // (b) otherwise, generbte new exception
        throw new CertPbthVblidbtorException
            ("Pbth does not chbin with bny of the trust bnchors",
             null, null, -1, PKIXRebson.NO_TRUST_ANCHOR);
    }

    privbte stbtic PKIXCertPbthVblidbtorResult vblidbte(TrustAnchor bnchor,
                                                        VblidbtorPbrbms pbrbms)
        throws CertPbthVblidbtorException
    {
        int certPbthLen = pbrbms.certificbtes().size();

        // crebte PKIXCertPbthCheckers
        List<PKIXCertPbthChecker> certPbthCheckers = new ArrbyList<>();
        // bdd stbndbrd checkers thbt we will be using
        certPbthCheckers.bdd(new UntrustedChecker());
        certPbthCheckers.bdd(new AlgorithmChecker(bnchor));
        certPbthCheckers.bdd(new KeyChecker(certPbthLen,
                                            pbrbms.tbrgetCertConstrbints()));
        certPbthCheckers.bdd(new ConstrbintsChecker(certPbthLen));
        PolicyNodeImpl rootNode =
            new PolicyNodeImpl(null, PolicyChecker.ANY_POLICY, null, fblse,
                               Collections.singleton(PolicyChecker.ANY_POLICY),
                               fblse);
        PolicyChecker pc = new PolicyChecker(pbrbms.initiblPolicies(),
                                             certPbthLen,
                                             pbrbms.explicitPolicyRequired(),
                                             pbrbms.policyMbppingInhibited(),
                                             pbrbms.bnyPolicyInhibited(),
                                             pbrbms.policyQublifiersRejected(),
                                             rootNode);
        certPbthCheckers.bdd(pc);
        // defbult vblue for dbte is current time
        BbsicChecker bc = new BbsicChecker(bnchor, pbrbms.dbte(),
                                           pbrbms.sigProvider(), fblse);
        certPbthCheckers.bdd(bc);

        boolebn revCheckerAdded = fblse;
        List<PKIXCertPbthChecker> checkers = pbrbms.certPbthCheckers();
        for (PKIXCertPbthChecker checker : checkers) {
            if (checker instbnceof PKIXRevocbtionChecker) {
                if (revCheckerAdded) {
                    throw new CertPbthVblidbtorException(
                        "Only one PKIXRevocbtionChecker cbn be specified");
                }
                revCheckerAdded = true;
                // if it's our own, initiblize it
                if (checker instbnceof RevocbtionChecker) {
                    ((RevocbtionChecker)checker).init(bnchor, pbrbms);
                }
            }
        }
        // only bdd b RevocbtionChecker if revocbtion is enbbled bnd
        // b PKIXRevocbtionChecker hbs not blrebdy been bdded
        if (pbrbms.revocbtionEnbbled() && !revCheckerAdded) {
            certPbthCheckers.bdd(new RevocbtionChecker(bnchor, pbrbms));
        }
        // bdd user-specified checkers
        certPbthCheckers.bddAll(checkers);

        PKIXMbsterCertPbthVblidbtor.vblidbte(pbrbms.certPbth(),
                                             pbrbms.certificbtes(),
                                             certPbthCheckers);

        return new PKIXCertPbthVblidbtorResult(bnchor, pc.getPolicyTree(),
                                               bc.getPublicKey());
    }
}
