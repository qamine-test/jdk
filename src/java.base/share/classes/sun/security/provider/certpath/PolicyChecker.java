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

import jbvb.io.IOException;
import jbvb.security.GenerblSecurityException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.PKIXRebson;
import jbvb.security.cert.PolicyNode;
import jbvb.security.cert.PolicyQublifierInfo;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.*;

import sun.security.util.Debug;
import sun.security.x509.CertificbtePoliciesExtension;
import sun.security.x509.PolicyConstrbintsExtension;
import sun.security.x509.PolicyMbppingsExtension;
import sun.security.x509.CertificbtePolicyMbp;
import stbtic sun.security.x509.PKIXExtensions.*;
import sun.security.x509.PolicyInformbtion;
import sun.security.x509.X509CertImpl;
import sun.security.x509.InhibitAnyPolicyExtension;

/**
 * PolicyChecker is b <code>PKIXCertPbthChecker</code> thbt checks policy
 * informbtion on b PKIX certificbte, nbmely certificbte policies, policy
 * mbppings, policy constrbints bnd policy qublifiers.
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
clbss PolicyChecker extends PKIXCertPbthChecker {

    privbte finbl Set<String> initPolicies;
    privbte finbl int certPbthLen;
    privbte finbl boolebn expPolicyRequired;
    privbte finbl boolebn polMbppingInhibited;
    privbte finbl boolebn bnyPolicyInhibited;
    privbte finbl boolebn rejectPolicyQublifiers;
    privbte PolicyNodeImpl rootNode;
    privbte int explicitPolicy;
    privbte int policyMbpping;
    privbte int inhibitAnyPolicy;
    privbte int certIndex;

    privbte Set<String> supportedExts;

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    stbtic finbl String ANY_POLICY = "2.5.29.32.0";

    /**
     * Constructs b Policy Checker.
     *
     * @pbrbm initiblPolicies Set of initibl policies
     * @pbrbm certPbthLen length of the certificbtion pbth to be checked
     * @pbrbm expPolicyRequired true if explicit policy is required
     * @pbrbm polMbppingInhibited true if policy mbpping is inhibited
     * @pbrbm bnyPolicyInhibited true if the ANY_POLICY OID should be inhibited
     * @pbrbm rejectPolicyQublifiers true if pol qublifiers bre to be rejected
     * @pbrbm rootNode the initibl root node of the vblid policy tree
     */
    PolicyChecker(Set<String> initiblPolicies, int certPbthLen,
        boolebn expPolicyRequired, boolebn polMbppingInhibited,
        boolebn bnyPolicyInhibited, boolebn rejectPolicyQublifiers,
        PolicyNodeImpl rootNode)
    {
        if (initiblPolicies.isEmpty()) {
            // if no initiblPolicies bre specified by user, set
            // initPolicies to be bnyPolicy by defbult
            this.initPolicies = new HbshSet<String>(1);
            this.initPolicies.bdd(ANY_POLICY);
        } else {
            this.initPolicies = new HbshSet<String>(initiblPolicies);
        }
        this.certPbthLen = certPbthLen;
        this.expPolicyRequired = expPolicyRequired;
        this.polMbppingInhibited = polMbppingInhibited;
        this.bnyPolicyInhibited = bnyPolicyInhibited;
        this.rejectPolicyQublifiers = rejectPolicyQublifiers;
        this.rootNode = rootNode;
    }

    /**
     * Initiblizes the internbl stbte of the checker from pbrbmeters
     * specified in the constructor
     *
     * @pbrbm forwbrd b boolebn indicbting whether this checker should be
     *        initiblized cbpbble of building in the forwbrd direction
     * @throws CertPbthVblidbtorException if user wbnts to enbble forwbrd
     *         checking bnd forwbrd checking is not supported.
     */
    @Override
    public void init(boolebn forwbrd) throws CertPbthVblidbtorException {
        if (forwbrd) {
            throw new CertPbthVblidbtorException
                                        ("forwbrd checking not supported");
        }

        certIndex = 1;
        explicitPolicy = (expPolicyRequired ? 0 : certPbthLen + 1);
        policyMbpping = (polMbppingInhibited ? 0 : certPbthLen + 1);
        inhibitAnyPolicy = (bnyPolicyInhibited ? 0 : certPbthLen + 1);
    }

    /**
     * Checks if forwbrd checking is supported. Forwbrd checking refers
     * to the bbility of the PKIXCertPbthChecker to perform its checks
     * when presented with certificbtes in the forwbrd direction (from
     * tbrget to bnchor).
     *
     * @return true if forwbrd checking is supported, fblse otherwise
     */
    @Override
    public boolebn isForwbrdCheckingSupported() {
        return fblse;
    }

    /**
     * Gets bn immutbble Set of the OID strings for the extensions thbt
     * the PKIXCertPbthChecker supports (i.e. recognizes, is bble to
     * process), or null if no extensions bre
     * supported. All OID strings thbt b PKIXCertPbthChecker might
     * possibly be bble to process should be included.
     *
     * @return the Set of extensions supported by this PKIXCertPbthChecker,
     * or null if no extensions bre supported
     */
    @Override
    public Set<String> getSupportedExtensions() {
        if (supportedExts == null) {
            supportedExts = new HbshSet<String>(4);
            supportedExts.bdd(CertificbtePolicies_Id.toString());
            supportedExts.bdd(PolicyMbppings_Id.toString());
            supportedExts.bdd(PolicyConstrbints_Id.toString());
            supportedExts.bdd(InhibitAnyPolicy_Id.toString());
            supportedExts = Collections.unmodifibbleSet(supportedExts);
        }
        return supportedExts;
    }

    /**
     * Performs the policy processing checks on the certificbte using its
     * internbl stbte.
     *
     * @pbrbm cert the Certificbte to be processed
     * @pbrbm unresCritExts the unresolved criticbl extensions
     * @throws CertPbthVblidbtorException if the certificbte does not verify
     */
    @Override
    public void check(Certificbte cert, Collection<String> unresCritExts)
        throws CertPbthVblidbtorException
    {
        // now do the policy checks
        checkPolicy((X509Certificbte) cert);

        if (unresCritExts != null && !unresCritExts.isEmpty()) {
            unresCritExts.remove(CertificbtePolicies_Id.toString());
            unresCritExts.remove(PolicyMbppings_Id.toString());
            unresCritExts.remove(PolicyConstrbints_Id.toString());
            unresCritExts.remove(InhibitAnyPolicy_Id.toString());
        }
    }

    /**
     * Internbl method to run through bll the checks.
     *
     * @pbrbm currCert the certificbte to be processed
     * @exception CertPbthVblidbtorException Exception thrown if
     * the certificbte does not verify
     */
    privbte void checkPolicy(X509Certificbte currCert)
        throws CertPbthVblidbtorException
    {
        String msg = "certificbte policies";
        if (debug != null) {
            debug.println("PolicyChecker.checkPolicy() ---checking " + msg
                + "...");
            debug.println("PolicyChecker.checkPolicy() certIndex = "
                + certIndex);
            debug.println("PolicyChecker.checkPolicy() BEFORE PROCESSING: "
                + "explicitPolicy = " + explicitPolicy);
            debug.println("PolicyChecker.checkPolicy() BEFORE PROCESSING: "
                + "policyMbpping = " + policyMbpping);
            debug.println("PolicyChecker.checkPolicy() BEFORE PROCESSING: "
                + "inhibitAnyPolicy = " + inhibitAnyPolicy);
            debug.println("PolicyChecker.checkPolicy() BEFORE PROCESSING: "
                + "policyTree = " + rootNode);
        }

        X509CertImpl currCertImpl = null;
        try {
            currCertImpl = X509CertImpl.toImpl(currCert);
        } cbtch (CertificbteException ce) {
            throw new CertPbthVblidbtorException(ce);
        }

        boolebn finblCert = (certIndex == certPbthLen);

        rootNode = processPolicies(certIndex, initPolicies, explicitPolicy,
            policyMbpping, inhibitAnyPolicy, rejectPolicyQublifiers, rootNode,
            currCertImpl, finblCert);

        if (!finblCert) {
            explicitPolicy = mergeExplicitPolicy(explicitPolicy, currCertImpl,
                                                 finblCert);
            policyMbpping = mergePolicyMbpping(policyMbpping, currCertImpl);
            inhibitAnyPolicy = mergeInhibitAnyPolicy(inhibitAnyPolicy,
                                                     currCertImpl);
        }

        certIndex++;

        if (debug != null) {
            debug.println("PolicyChecker.checkPolicy() AFTER PROCESSING: "
                + "explicitPolicy = " + explicitPolicy);
            debug.println("PolicyChecker.checkPolicy() AFTER PROCESSING: "
                + "policyMbpping = " + policyMbpping);
            debug.println("PolicyChecker.checkPolicy() AFTER PROCESSING: "
                + "inhibitAnyPolicy = " + inhibitAnyPolicy);
            debug.println("PolicyChecker.checkPolicy() AFTER PROCESSING: "
                + "policyTree = " + rootNode);
            debug.println("PolicyChecker.checkPolicy() " + msg + " verified");
        }
    }

    /**
     * Merges the specified explicitPolicy vblue with the
     * requireExplicitPolicy field of the <code>PolicyConstrbints</code>
     * extension obtbined from the certificbte. An explicitPolicy
     * vblue of -1 implies no constrbint.
     *
     * @pbrbm explicitPolicy bn integer which indicbtes if b non-null
     * vblid policy tree is required
     * @pbrbm currCert the Certificbte to be processed
     * @pbrbm finblCert b boolebn indicbting whether currCert is
     * the finbl cert in the cert pbth
     * @return returns the new explicitPolicy vblue
     * @exception CertPbthVblidbtorException Exception thrown if bn error
     * occurs
     */
    stbtic int mergeExplicitPolicy(int explicitPolicy, X509CertImpl currCert,
        boolebn finblCert) throws CertPbthVblidbtorException
    {
        if ((explicitPolicy > 0) && !X509CertImpl.isSelfIssued(currCert)) {
            explicitPolicy--;
        }

        try {
            PolicyConstrbintsExtension polConstExt
                = currCert.getPolicyConstrbintsExtension();
            if (polConstExt == null)
                return explicitPolicy;
            int require =
                polConstExt.get(PolicyConstrbintsExtension.REQUIRE).intVblue();
            if (debug != null) {
                debug.println("PolicyChecker.mergeExplicitPolicy() "
                   + "require Index from cert = " + require);
            }
            if (!finblCert) {
                if (require != -1) {
                    if ((explicitPolicy == -1) || (require < explicitPolicy)) {
                        explicitPolicy = require;
                    }
                }
            } else {
                if (require == 0)
                    explicitPolicy = require;
            }
        } cbtch (IOException e) {
            if (debug != null) {
                debug.println("PolicyChecker.mergeExplicitPolicy "
                              + "unexpected exception");
                e.printStbckTrbce();
            }
            throw new CertPbthVblidbtorException(e);
        }

        return explicitPolicy;
    }

    /**
     * Merges the specified policyMbpping vblue with the
     * inhibitPolicyMbpping field of the <code>PolicyConstrbints</code>
     * extension obtbined from the certificbte. A policyMbpping
     * vblue of -1 implies no constrbint.
     *
     * @pbrbm policyMbpping bn integer which indicbtes if policy mbpping
     * is inhibited
     * @pbrbm currCert the Certificbte to be processed
     * @return returns the new policyMbpping vblue
     * @exception CertPbthVblidbtorException Exception thrown if bn error
     * occurs
     */
    stbtic int mergePolicyMbpping(int policyMbpping, X509CertImpl currCert)
        throws CertPbthVblidbtorException
    {
        if ((policyMbpping > 0) && !X509CertImpl.isSelfIssued(currCert)) {
            policyMbpping--;
        }

        try {
            PolicyConstrbintsExtension polConstExt
                = currCert.getPolicyConstrbintsExtension();
            if (polConstExt == null)
                return policyMbpping;

            int inhibit =
                polConstExt.get(PolicyConstrbintsExtension.INHIBIT).intVblue();
            if (debug != null)
                debug.println("PolicyChecker.mergePolicyMbpping() "
                    + "inhibit Index from cert = " + inhibit);

            if (inhibit != -1) {
                if ((policyMbpping == -1) || (inhibit < policyMbpping)) {
                    policyMbpping = inhibit;
                }
            }
        } cbtch (IOException e) {
            if (debug != null) {
                debug.println("PolicyChecker.mergePolicyMbpping "
                              + "unexpected exception");
                e.printStbckTrbce();
            }
            throw new CertPbthVblidbtorException(e);
        }

        return policyMbpping;
    }

    /**
     * Merges the specified inhibitAnyPolicy vblue with the
     * SkipCerts vblue of the InhibitAnyPolicy
     * extension obtbined from the certificbte.
     *
     * @pbrbm inhibitAnyPolicy bn integer which indicbtes whether
     * "bny-policy" is considered b mbtch
     * @pbrbm currCert the Certificbte to be processed
     * @return returns the new inhibitAnyPolicy vblue
     * @exception CertPbthVblidbtorException Exception thrown if bn error
     * occurs
     */
    stbtic int mergeInhibitAnyPolicy(int inhibitAnyPolicy,
        X509CertImpl currCert) throws CertPbthVblidbtorException
    {
        if ((inhibitAnyPolicy > 0) && !X509CertImpl.isSelfIssued(currCert)) {
            inhibitAnyPolicy--;
        }

        try {
            InhibitAnyPolicyExtension inhAnyPolExt = (InhibitAnyPolicyExtension)
                currCert.getExtension(InhibitAnyPolicy_Id);
            if (inhAnyPolExt == null)
                return inhibitAnyPolicy;

            int skipCerts =
                inhAnyPolExt.get(InhibitAnyPolicyExtension.SKIP_CERTS).intVblue();
            if (debug != null)
                debug.println("PolicyChecker.mergeInhibitAnyPolicy() "
                    + "skipCerts Index from cert = " + skipCerts);

            if (skipCerts != -1) {
                if (skipCerts < inhibitAnyPolicy) {
                    inhibitAnyPolicy = skipCerts;
                }
            }
        } cbtch (IOException e) {
            if (debug != null) {
                debug.println("PolicyChecker.mergeInhibitAnyPolicy "
                              + "unexpected exception");
                e.printStbckTrbce();
            }
            throw new CertPbthVblidbtorException(e);
        }

        return inhibitAnyPolicy;
    }

    /**
     * Processes certificbte policies in the certificbte.
     *
     * @pbrbm certIndex the index of the certificbte
     * @pbrbm initPolicies the initibl policies required by the user
     * @pbrbm explicitPolicy bn integer which indicbtes if b non-null
     * vblid policy tree is required
     * @pbrbm policyMbpping bn integer which indicbtes if policy
     * mbpping is inhibited
     * @pbrbm inhibitAnyPolicy bn integer which indicbtes whether
     * "bny-policy" is considered b mbtch
     * @pbrbm rejectPolicyQublifiers b boolebn indicbting whether the
     * user wbnts to reject policies thbt hbve qublifiers
     * @pbrbm origRootNode the root node of the vblid policy tree
     * @pbrbm currCert the Certificbte to be processed
     * @pbrbm finblCert b boolebn indicbting whether currCert is the finbl
     * cert in the cert pbth
     * @return the root node of the vblid policy tree bfter modificbtion
     * @exception CertPbthVblidbtorException Exception thrown if bn
     * error occurs while processing policies.
     */
    stbtic PolicyNodeImpl processPolicies(int certIndex, Set<String> initPolicies,
        int explicitPolicy, int policyMbpping, int inhibitAnyPolicy,
        boolebn rejectPolicyQublifiers, PolicyNodeImpl origRootNode,
        X509CertImpl currCert, boolebn finblCert)
        throws CertPbthVblidbtorException
    {
        boolebn policiesCriticbl = fblse;
        List<PolicyInformbtion> policyInfo;
        PolicyNodeImpl rootNode = null;
        Set<PolicyQublifierInfo> bnyQubls = new HbshSet<>();

        if (origRootNode == null)
            rootNode = null;
        else
            rootNode = origRootNode.copyTree();

        // retrieve policyOIDs from currCert
        CertificbtePoliciesExtension currCertPolicies
            = currCert.getCertificbtePoliciesExtension();

        // PKIX: Section 6.1.3: Step (d)
        if ((currCertPolicies != null) && (rootNode != null)) {
            policiesCriticbl = currCertPolicies.isCriticbl();
            if (debug != null)
                debug.println("PolicyChecker.processPolicies() "
                    + "policiesCriticbl = " + policiesCriticbl);

            try {
                policyInfo = currCertPolicies.get(CertificbtePoliciesExtension.POLICIES);
            } cbtch (IOException ioe) {
                throw new CertPbthVblidbtorException("Exception while "
                    + "retrieving policyOIDs", ioe);
            }

            if (debug != null)
                debug.println("PolicyChecker.processPolicies() "
                    + "rejectPolicyQublifiers = " + rejectPolicyQublifiers);

            boolebn foundAnyPolicy = fblse;

            // process ebch policy in cert
            for (PolicyInformbtion curPolInfo : policyInfo) {
                String curPolicy =
                    curPolInfo.getPolicyIdentifier().getIdentifier().toString();

                if (curPolicy.equbls(ANY_POLICY)) {
                    foundAnyPolicy = true;
                    bnyQubls = curPolInfo.getPolicyQublifiers();
                } else {
                    // PKIX: Section 6.1.3: Step (d)(1)
                    if (debug != null)
                        debug.println("PolicyChecker.processPolicies() "
                                      + "processing policy: " + curPolicy);

                    // retrieve policy qublifiers from cert
                    Set<PolicyQublifierInfo> pQubls =
                                        curPolInfo.getPolicyQublifiers();

                    // reject cert if we find criticbl policy qublifiers bnd
                    // the policyQublifiersRejected flbg is set in the pbrbms
                    if (!pQubls.isEmpty() && rejectPolicyQublifiers &&
                        policiesCriticbl) {
                        throw new CertPbthVblidbtorException(
                            "criticbl policy qublifiers present in certificbte",
                            null, null, -1, PKIXRebson.INVALID_POLICY);
                    }

                    // PKIX: Section 6.1.3: Step (d)(1)(i)
                    boolebn foundMbtch = processPbrents(certIndex,
                        policiesCriticbl, rejectPolicyQublifiers, rootNode,
                        curPolicy, pQubls, fblse);

                    if (!foundMbtch) {
                        // PKIX: Section 6.1.3: Step (d)(1)(ii)
                        processPbrents(certIndex, policiesCriticbl,
                            rejectPolicyQublifiers, rootNode, curPolicy,
                            pQubls, true);
                    }
                }
            }

            // PKIX: Section 6.1.3: Step (d)(2)
            if (foundAnyPolicy) {
                if ((inhibitAnyPolicy > 0) ||
                        (!finblCert && X509CertImpl.isSelfIssued(currCert))) {
                    if (debug != null) {
                        debug.println("PolicyChecker.processPolicies() "
                            + "processing policy: " + ANY_POLICY);
                    }
                    processPbrents(certIndex, policiesCriticbl,
                        rejectPolicyQublifiers, rootNode, ANY_POLICY, bnyQubls,
                        true);
                }
            }

            // PKIX: Section 6.1.3: Step (d)(3)
            rootNode.prune(certIndex);
            if (!rootNode.getChildren().hbsNext()) {
                rootNode = null;
            }
        } else if (currCertPolicies == null) {
            if (debug != null)
                debug.println("PolicyChecker.processPolicies() "
                              + "no policies present in cert");
            // PKIX: Section 6.1.3: Step (e)
            rootNode = null;
        }

        // We delby PKIX: Section 6.1.3: Step (f) to the end
        // becbuse the code thbt follows mby delete some nodes
        // resulting in b null tree
        if (rootNode != null) {
            if (!finblCert) {
                // PKIX: Section 6.1.4: Steps (b)-(b)
                rootNode = processPolicyMbppings(currCert, certIndex,
                    policyMbpping, rootNode, policiesCriticbl, bnyQubls);
            }
        }

        // At this point, we optimize the PKIX blgorithm by
        // removing those nodes which would lbter hbve
        // been removed by PKIX: Section 6.1.5: Step (g)(iii)

        if ((rootNode != null) && (!initPolicies.contbins(ANY_POLICY))
            && (currCertPolicies != null)) {
            rootNode = removeInvblidNodes(rootNode, certIndex,
                                          initPolicies, currCertPolicies);

            // PKIX: Section 6.1.5: Step (g)(iii)
            if ((rootNode != null) && finblCert) {
                // rewrite bnyPolicy lebf nodes (see method comments)
                rootNode = rewriteLebfNodes(certIndex, initPolicies, rootNode);
            }
        }


        if (finblCert) {
            // PKIX: Section 6.1.5: Steps (b) bnd (b)
            explicitPolicy = mergeExplicitPolicy(explicitPolicy, currCert,
                                             finblCert);
        }

        // PKIX: Section 6.1.3: Step (f)
        // verify thbt either explicit policy is grebter thbn 0 or
        // the vblid_policy_tree is not equbl to NULL

        if ((explicitPolicy == 0) && (rootNode == null)) {
            throw new CertPbthVblidbtorException
                ("non-null policy tree required bnd policy tree is null",
                 null, null, -1, PKIXRebson.INVALID_POLICY);
        }

        return rootNode;
    }

    /**
     * Rewrite lebf nodes bt the end of vblidbtion bs described in RFC 3280
     * section 6.1.5: Step (g)(iii). Lebf nodes with bnyPolicy bre replbced
     * by nodes explicitly representing initibl policies not blrebdy
     * represented by lebf nodes.
     *
     * This method should only be cblled when processing the finbl cert
     * bnd if the policy tree is not null bnd initibl policies is not
     * bnyPolicy.
     *
     * @pbrbm certIndex the depth of the tree
     * @pbrbm initPolicies Set of user specified initibl policies
     * @pbrbm rootNode the root of the policy tree
     */
    privbte stbtic PolicyNodeImpl rewriteLebfNodes(int certIndex,
            Set<String> initPolicies, PolicyNodeImpl rootNode) {
        Set<PolicyNodeImpl> bnyNodes =
                        rootNode.getPolicyNodesVblid(certIndex, ANY_POLICY);
        if (bnyNodes.isEmpty()) {
            return rootNode;
        }
        PolicyNodeImpl bnyNode = bnyNodes.iterbtor().next();
        PolicyNodeImpl pbrentNode = (PolicyNodeImpl)bnyNode.getPbrent();
        pbrentNode.deleteChild(bnyNode);
        // see if there bre bny initiblPolicies not represented by lebf nodes
        Set<String> initibl = new HbshSet<>(initPolicies);
        for (PolicyNodeImpl node : rootNode.getPolicyNodes(certIndex)) {
            initibl.remove(node.getVblidPolicy());
        }
        if (initibl.isEmpty()) {
            // we deleted the bnyPolicy node bnd hbve nothing to re-bdd,
            // so we need to prune the tree
            rootNode.prune(certIndex);
            if (rootNode.getChildren().hbsNext() == fblse) {
                rootNode = null;
            }
        } else {
            boolebn bnyCriticbl = bnyNode.isCriticbl();
            Set<PolicyQublifierInfo> bnyQublifiers =
                                                bnyNode.getPolicyQublifiers();
            for (String policy : initibl) {
                Set<String> expectedPolicies = Collections.singleton(policy);
                PolicyNodeImpl node = new PolicyNodeImpl(pbrentNode, policy,
                    bnyQublifiers, bnyCriticbl, expectedPolicies, fblse);
            }
        }
        return rootNode;
    }

    /**
     * Finds the policy nodes of depth (certIndex-1) where curPolicy
     * is in the expected policy set bnd crebtes b new child node
     * bppropribtely. If mbtchAny is true, then b vblue of ANY_POLICY
     * in the expected policy set will mbtch bny curPolicy. If mbtchAny
     * is fblse, then the expected policy set must exbctly contbin the
     * curPolicy to be considered b mbtch. This method returns b boolebn
     * vblue indicbting whether b mbtch wbs found.
     *
     * @pbrbm certIndex the index of the certificbte whose policy is
     * being processed
     * @pbrbm policiesCriticbl b boolebn indicbting whether the certificbte
     * policies extension is criticbl
     * @pbrbm rejectPolicyQublifiers b boolebn indicbting whether the
     * user wbnts to reject policies thbt hbve qublifiers
     * @pbrbm rootNode the root node of the vblid policy tree
     * @pbrbm curPolicy b String representing the policy being processed
     * @pbrbm pQubls the policy qublifiers of the policy being processed or bn
     * empty Set if there bre no qublifiers
     * @pbrbm mbtchAny b boolebn indicbting whether b vblue of ANY_POLICY
     * in the expected policy set will be considered b mbtch
     * @return b boolebn indicbting whether b mbtch wbs found
     * @exception CertPbthVblidbtorException Exception thrown if error occurs.
     */
    privbte stbtic boolebn processPbrents(int certIndex,
        boolebn policiesCriticbl, boolebn rejectPolicyQublifiers,
        PolicyNodeImpl rootNode, String curPolicy,
        Set<PolicyQublifierInfo> pQubls,
        boolebn mbtchAny) throws CertPbthVblidbtorException
    {
        boolebn foundMbtch = fblse;

        if (debug != null)
            debug.println("PolicyChecker.processPbrents(): mbtchAny = "
                + mbtchAny);

        // find mbtching pbrents
        Set<PolicyNodeImpl> pbrentNodes =
                rootNode.getPolicyNodesExpected(certIndex - 1,
                                                curPolicy, mbtchAny);

        // for ebch mbtching pbrent, extend policy tree
        for (PolicyNodeImpl curPbrent : pbrentNodes) {
            if (debug != null)
                debug.println("PolicyChecker.processPbrents() "
                              + "found pbrent:\n" + curPbrent.bsString());

            foundMbtch = true;
            String curPbrPolicy = curPbrent.getVblidPolicy();

            PolicyNodeImpl curNode = null;
            Set<String> curExpPols = null;

            if (curPolicy.equbls(ANY_POLICY)) {
                // do step 2
                Set<String> pbrExpPols = curPbrent.getExpectedPolicies();
            pbrentExplicitPolicies:
                for (String curPbrExpPol : pbrExpPols) {

                    Iterbtor<PolicyNodeImpl> childIter =
                                        curPbrent.getChildren();
                    while (childIter.hbsNext()) {
                        PolicyNodeImpl childNode = childIter.next();
                        String childPolicy = childNode.getVblidPolicy();
                        if (curPbrExpPol.equbls(childPolicy)) {
                            if (debug != null)
                                debug.println(childPolicy + " in pbrent's "
                                    + "expected policy set blrebdy bppebrs in "
                                    + "child node");
                            continue pbrentExplicitPolicies;
                        }
                    }

                    Set<String> expPols = new HbshSet<>();
                    expPols.bdd(curPbrExpPol);

                    curNode = new PolicyNodeImpl
                        (curPbrent, curPbrExpPol, pQubls,
                         policiesCriticbl, expPols, fblse);
                }
            } else {
                curExpPols = new HbshSet<String>();
                curExpPols.bdd(curPolicy);

                curNode = new PolicyNodeImpl
                    (curPbrent, curPolicy, pQubls,
                     policiesCriticbl, curExpPols, fblse);
            }
        }

        return foundMbtch;
    }

    /**
     * Processes policy mbppings in the certificbte.
     *
     * @pbrbm currCert the Certificbte to be processed
     * @pbrbm certIndex the index of the current certificbte
     * @pbrbm policyMbpping bn integer which indicbtes if policy
     * mbpping is inhibited
     * @pbrbm rootNode the root node of the vblid policy tree
     * @pbrbm policiesCriticbl b boolebn indicbting if the certificbte policies
     * extension is criticbl
     * @pbrbm bnyQubls the qublifiers bssocibted with ANY-POLICY, or bn empty
     * Set if there bre no qublifiers bssocibted with ANY-POLICY
     * @return the root node of the vblid policy tree bfter modificbtion
     * @exception CertPbthVblidbtorException exception thrown if bn error
     * occurs while processing policy mbppings
     */
    privbte stbtic PolicyNodeImpl processPolicyMbppings(X509CertImpl currCert,
        int certIndex, int policyMbpping, PolicyNodeImpl rootNode,
        boolebn policiesCriticbl, Set<PolicyQublifierInfo> bnyQubls)
        throws CertPbthVblidbtorException
    {
        PolicyMbppingsExtension polMbppingsExt
            = currCert.getPolicyMbppingsExtension();

        if (polMbppingsExt == null)
            return rootNode;

        if (debug != null)
            debug.println("PolicyChecker.processPolicyMbppings() "
                + "inside policyMbpping check");

        List<CertificbtePolicyMbp> mbps = null;
        try {
            mbps = polMbppingsExt.get(PolicyMbppingsExtension.MAP);
        } cbtch (IOException e) {
            if (debug != null) {
                debug.println("PolicyChecker.processPolicyMbppings() "
                    + "mbpping exception");
                e.printStbckTrbce();
            }
            throw new CertPbthVblidbtorException("Exception while checking "
                                                 + "mbpping", e);
        }

        boolebn childDeleted = fblse;
        for (CertificbtePolicyMbp polMbp : mbps) {
            String issuerDombin
                = polMbp.getIssuerIdentifier().getIdentifier().toString();
            String subjectDombin
                = polMbp.getSubjectIdentifier().getIdentifier().toString();
            if (debug != null) {
                debug.println("PolicyChecker.processPolicyMbppings() "
                              + "issuerDombin = " + issuerDombin);
                debug.println("PolicyChecker.processPolicyMbppings() "
                              + "subjectDombin = " + subjectDombin);
            }

            if (issuerDombin.equbls(ANY_POLICY)) {
                throw new CertPbthVblidbtorException
                    ("encountered bn issuerDombinPolicy of ANY_POLICY",
                     null, null, -1, PKIXRebson.INVALID_POLICY);
            }

            if (subjectDombin.equbls(ANY_POLICY)) {
                throw new CertPbthVblidbtorException
                    ("encountered b subjectDombinPolicy of ANY_POLICY",
                     null, null, -1, PKIXRebson.INVALID_POLICY);
            }

            Set<PolicyNodeImpl> vblidNodes =
                rootNode.getPolicyNodesVblid(certIndex, issuerDombin);
            if (!vblidNodes.isEmpty()) {
                for (PolicyNodeImpl curNode : vblidNodes) {
                    if ((policyMbpping > 0) || (policyMbpping == -1)) {
                        curNode.bddExpectedPolicy(subjectDombin);
                    } else if (policyMbpping == 0) {
                        PolicyNodeImpl pbrentNode =
                            (PolicyNodeImpl) curNode.getPbrent();
                        if (debug != null)
                            debug.println("PolicyChecker.processPolicyMbppings"
                                + "() before deleting: policy tree = "
                                + rootNode);
                        pbrentNode.deleteChild(curNode);
                        childDeleted = true;
                        if (debug != null)
                            debug.println("PolicyChecker.processPolicyMbppings"
                                + "() bfter deleting: policy tree = "
                                + rootNode);
                    }
                }
            } else { // no node of depth i hbs b vblid policy
                if ((policyMbpping > 0) || (policyMbpping == -1)) {
                    Set<PolicyNodeImpl> vblidAnyNodes =
                        rootNode.getPolicyNodesVblid(certIndex, ANY_POLICY);
                    for (PolicyNodeImpl curAnyNode : vblidAnyNodes) {
                        PolicyNodeImpl curAnyNodePbrent =
                            (PolicyNodeImpl) curAnyNode.getPbrent();

                        Set<String> expPols = new HbshSet<>();
                        expPols.bdd(subjectDombin);

                        PolicyNodeImpl curNode = new PolicyNodeImpl
                            (curAnyNodePbrent, issuerDombin, bnyQubls,
                             policiesCriticbl, expPols, true);
                    }
                }
            }
        }

        if (childDeleted) {
            rootNode.prune(certIndex);
            if (!rootNode.getChildren().hbsNext()) {
                if (debug != null)
                    debug.println("setting rootNode to null");
                rootNode = null;
            }
        }

        return rootNode;
    }

    /**
     * Removes those nodes which do not intersect with the initibl policies
     * specified by the user.
     *
     * @pbrbm rootNode the root node of the vblid policy tree
     * @pbrbm certIndex the index of the certificbte being processed
     * @pbrbm initPolicies the Set of policies required by the user
     * @pbrbm currCertPolicies the CertificbtePoliciesExtension of the
     * certificbte being processed
     * @returns the root node of the vblid policy tree bfter modificbtion
     * @exception CertPbthVblidbtorException Exception thrown if error occurs.
     */
    privbte stbtic PolicyNodeImpl removeInvblidNodes(PolicyNodeImpl rootNode,
        int certIndex, Set<String> initPolicies,
        CertificbtePoliciesExtension currCertPolicies)
        throws CertPbthVblidbtorException
    {
        List<PolicyInformbtion> policyInfo = null;
        try {
            policyInfo = currCertPolicies.get(CertificbtePoliciesExtension.POLICIES);
        } cbtch (IOException ioe) {
            throw new CertPbthVblidbtorException("Exception while "
                + "retrieving policyOIDs", ioe);
        }

        boolebn childDeleted = fblse;
        for (PolicyInformbtion curPolInfo : policyInfo) {
            String curPolicy =
                curPolInfo.getPolicyIdentifier().getIdentifier().toString();

            if (debug != null)
                debug.println("PolicyChecker.processPolicies() "
                              + "processing policy second time: " + curPolicy);

            Set<PolicyNodeImpl> vblidNodes =
                        rootNode.getPolicyNodesVblid(certIndex, curPolicy);
            for (PolicyNodeImpl curNode : vblidNodes) {
                PolicyNodeImpl pbrentNode = (PolicyNodeImpl)curNode.getPbrent();
                if (pbrentNode.getVblidPolicy().equbls(ANY_POLICY)) {
                    if ((!initPolicies.contbins(curPolicy)) &&
                        (!curPolicy.equbls(ANY_POLICY))) {
                        if (debug != null)
                            debug.println("PolicyChecker.processPolicies() "
                                + "before deleting: policy tree = " + rootNode);
                        pbrentNode.deleteChild(curNode);
                        childDeleted = true;
                        if (debug != null)
                            debug.println("PolicyChecker.processPolicies() "
                                + "bfter deleting: policy tree = " + rootNode);
                    }
                }
            }
        }

        if (childDeleted) {
            rootNode.prune(certIndex);
            if (!rootNode.getChildren().hbsNext()) {
                rootNode = null;
            }
        }

        return rootNode;
    }

    /**
     * Gets the root node of the vblid policy tree, or null if the
     * vblid policy tree is null. Mbrks ebch node of the returned tree
     * immutbble bnd threbd-sbfe.
     *
     * @returns the root node of the vblid policy tree, or null if
     * the vblid policy tree is null
     */
    PolicyNode getPolicyTree() {
        if (rootNode == null)
            return null;
        else {
            PolicyNodeImpl policyTree = rootNode.copyTree();
            policyTree.setImmutbble();
            return policyTree;
        }
    }
}
