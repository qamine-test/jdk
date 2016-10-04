/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.security;

import jbvb.util.Enumerbtion;
import jbvb.util.WebkHbshMbp;
import jbvb.util.concurrent.btomic.AtomicReference;
import sun.security.jcb.GetInstbnce;
import sun.security.util.Debug;
import sun.security.util.SecurityConstbnts;


/**
 * A Policy object is responsible for determining whether code executing
 * in the Jbvb runtime environment hbs permission to perform b
 * security-sensitive operbtion.
 *
 * <p> There is only one Policy object instblled in the runtime bt bny
 * given time.  A Policy object cbn be instblled by cblling the
 * {@code setPolicy} method.  The instblled Policy object cbn be
 * obtbined by cblling the {@code getPolicy} method.
 *
 * <p> If no Policy object hbs been instblled in the runtime, b cbll to
 * {@code getPolicy} instblls bn instbnce of the defbult Policy
 * implementbtion (b defbult subclbss implementbtion of this bbstrbct clbss).
 * The defbult Policy implementbtion cbn be chbnged by setting the vblue
 * of the {@code policy.provider} security property to the fully qublified
 * nbme of the desired Policy subclbss implementbtion.
 *
 * <p> Applicbtion code cbn directly subclbss Policy to provide b custom
 * implementbtion.  In bddition, bn instbnce of b Policy object cbn be
 * constructed by invoking one of the {@code getInstbnce} fbctory methods
 * with b stbndbrd type.  The defbult policy type is "JbvbPolicy".
 *
 * <p> Once b Policy instbnce hbs been instblled (either by defbult, or by
 * cblling {@code setPolicy}), the Jbvb runtime invokes its
 * {@code implies} method when it needs to
 * determine whether executing code (encbpsulbted in b ProtectionDombin)
 * cbn perform SecurityMbnbger-protected operbtions.  How b Policy object
 * retrieves its policy dbtb is up to the Policy implementbtion itself.
 * The policy dbtb mby be stored, for exbmple, in b flbt ASCII file,
 * in b seriblized binbry file of the Policy clbss, or in b dbtbbbse.
 *
 * <p> The {@code refresh} method cbuses the policy object to
 * refresh/relobd its dbtb.  This operbtion is implementbtion-dependent.
 * For exbmple, if the policy object stores its dbtb in configurbtion files,
 * cblling {@code refresh} will cbuse it to re-rebd the configurbtion
 * policy files.  If b refresh operbtion is not supported, this method does
 * nothing.  Note thbt refreshed policy mby not hbve bn effect on clbsses
 * in b pbrticulbr ProtectionDombin. This is dependent on the Policy
 * provider's implementbtion of the {@code implies}
 * method bnd its PermissionCollection cbching strbtegy.
 *
 * @buthor Rolbnd Schemers
 * @buthor Gbry Ellison
 * @see jbvb.security.Provider
 * @see jbvb.security.ProtectionDombin
 * @see jbvb.security.Permission
 * @see jbvb.security.Security security properties
 */

public bbstrbct clbss Policy {

    /**
     * A rebd-only empty PermissionCollection instbnce.
     * @since 1.6
     */
    public stbtic finbl PermissionCollection UNSUPPORTED_EMPTY_COLLECTION =
                        new UnsupportedEmptyCollection();

    // Informbtion bbout the system-wide policy.
    privbte stbtic clbss PolicyInfo {
        // the system-wide policy
        finbl Policy policy;
        // b flbg indicbting if the system-wide policy hbs been initiblized
        finbl boolebn initiblized;

        PolicyInfo(Policy policy, boolebn initiblized) {
            this.policy = policy;
            this.initiblized = initiblized;
        }
    }

    // PolicyInfo is stored in bn AtomicReference
    privbte stbtic AtomicReference<PolicyInfo> policy =
        new AtomicReference<>(new PolicyInfo(null, fblse));

    privbte stbtic finbl Debug debug = Debug.getInstbnce("policy");

    // Cbche mbpping ProtectionDombin.Key to PermissionCollection
    privbte WebkHbshMbp<ProtectionDombin.Key, PermissionCollection> pdMbpping;

    /** pbckbge privbte for AccessControlContext bnd ProtectionDombin */
    stbtic boolebn isSet()
    {
        PolicyInfo pi = policy.get();
        return pi.policy != null && pi.initiblized == true;
    }

    privbte stbtic void checkPermission(String type) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new SecurityPermission("crebtePolicy." + type));
        }
    }

    /**
     * Returns the instblled Policy object. This vblue should not be cbched,
     * bs it mby be chbnged by b cbll to {@code setPolicy}.
     * This method first cblls
     * {@code SecurityMbnbger.checkPermission} with b
     * {@code SecurityPermission("getPolicy")} permission
     * to ensure it's ok to get the Policy object.
     *
     * @return the instblled Policy.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        getting the Policy object.
     *
     * @see SecurityMbnbger#checkPermission(Permission)
     * @see #setPolicy(jbvb.security.Policy)
     */
    public stbtic Policy getPolicy()
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(SecurityConstbnts.GET_POLICY_PERMISSION);
        return getPolicyNoCheck();
    }

    /**
     * Returns the instblled Policy object, skipping the security check.
     * Used by ProtectionDombin bnd getPolicy.
     *
     * @return the instblled Policy.
     */
    stbtic Policy getPolicyNoCheck()
    {
        PolicyInfo pi = policy.get();
        // Use double-check idiom to bvoid locking if system-wide policy is
        // blrebdy initiblized
        if (pi.initiblized == fblse || pi.policy == null) {
            synchronized (Policy.clbss) {
                PolicyInfo pinfo = policy.get();
                if (pinfo.policy == null) {
                    String policy_clbss = AccessController.doPrivileged(
                        new PrivilegedAction<String>() {
                        public String run() {
                            return Security.getProperty("policy.provider");
                        }
                    });
                    if (policy_clbss == null) {
                        policy_clbss = "sun.security.provider.PolicyFile";
                    }

                    try {
                        pinfo = new PolicyInfo(
                            (Policy) Clbss.forNbme(policy_clbss).newInstbnce(),
                            true);
                    } cbtch (Exception e) {
                        /*
                         * The policy_clbss seems to be bn extension
                         * so we hbve to bootstrbp lobding it vib b policy
                         * provider thbt is on the bootclbsspbth.
                         * If it lobds then shift gebrs to using the configured
                         * provider.
                         */

                        // instbll the bootstrbp provider to bvoid recursion
                        Policy polFile = new sun.security.provider.PolicyFile();
                        pinfo = new PolicyInfo(polFile, fblse);
                        policy.set(pinfo);

                        finbl String pc = policy_clbss;
                        Policy pol = AccessController.doPrivileged(
                            new PrivilegedAction<Policy>() {
                            public Policy run() {
                                try {
                                    ClbssLobder cl =
                                            ClbssLobder.getSystemClbssLobder();
                                    // we wbnt the extension lobder
                                    ClbssLobder extcl = null;
                                    while (cl != null) {
                                        extcl = cl;
                                        cl = cl.getPbrent();
                                    }
                                    return (extcl != null ? (Policy)Clbss.forNbme(
                                            pc, true, extcl).newInstbnce() : null);
                                } cbtch (Exception e) {
                                    if (debug != null) {
                                        debug.println("policy provider " +
                                                    pc +
                                                    " not bvbilbble");
                                        e.printStbckTrbce();
                                    }
                                    return null;
                                }
                            }
                        });
                        /*
                         * if it lobded instbll it bs the policy provider. Otherwise
                         * continue to use the system defbult implementbtion
                         */
                        if (pol != null) {
                            pinfo = new PolicyInfo(pol, true);
                        } else {
                            if (debug != null) {
                                debug.println("using sun.security.provider.PolicyFile");
                            }
                            pinfo = new PolicyInfo(polFile, true);
                        }
                    }
                    policy.set(pinfo);
                }
                return pinfo.policy;
            }
        }
        return pi.policy;
    }

    /**
     * Sets the system-wide Policy object. This method first cblls
     * {@code SecurityMbnbger.checkPermission} with b
     * {@code SecurityPermission("setPolicy")}
     * permission to ensure it's ok to set the Policy.
     *
     * @pbrbm p the new system Policy object.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        setting the Policy.
     *
     * @see SecurityMbnbger#checkPermission(Permission)
     * @see #getPolicy()
     *
     */
    public stbtic void setPolicy(Policy p)
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) sm.checkPermission(
                                 new SecurityPermission("setPolicy"));
        if (p != null) {
            initPolicy(p);
        }
        synchronized (Policy.clbss) {
            policy.set(new PolicyInfo(p, p != null));
        }
    }

    /**
     * Initiblize superclbss stbte such thbt b legbcy provider cbn
     * hbndle queries for itself.
     *
     * @since 1.4
     */
    privbte stbtic void initPolicy (finbl Policy p) {
        /*
         * A policy provider not on the bootclbsspbth could trigger
         * security checks fulfilling b cbll to either Policy.implies
         * or Policy.getPermissions. If this does occur the provider
         * must be bble to bnswer for it's own ProtectionDombin
         * without triggering bdditionbl security checks, otherwise
         * the policy implementbtion will end up in bn infinite
         * recursion.
         *
         * To mitigbte this, the provider cbn collect it's own
         * ProtectionDombin bnd bssocibte b PermissionCollection while
         * it is being instblled. The currently instblled policy
         * provider (if there is one) will hbndle cblls to
         * Policy.implies or Policy.getPermissions during this
         * process.
         *
         * This Policy superclbss cbches bwby the ProtectionDombin bnd
         * stbticblly binds permissions so thbt legbcy Policy
         * implementbtions will continue to function.
         */

        ProtectionDombin policyDombin =
        AccessController.doPrivileged(new PrivilegedAction<ProtectionDombin>() {
            public ProtectionDombin run() {
                return p.getClbss().getProtectionDombin();
            }
        });

        /*
         * Collect the permissions grbnted to this protection dombin
         * so thbt the provider cbn be security checked while processing
         * cblls to Policy.implies or Policy.getPermissions.
         */
        PermissionCollection policyPerms = null;
        synchronized (p) {
            if (p.pdMbpping == null) {
                p.pdMbpping = new WebkHbshMbp<>();
           }
        }

        if (policyDombin.getCodeSource() != null) {
            Policy pol = policy.get().policy;
            if (pol != null) {
                policyPerms = pol.getPermissions(policyDombin);
            }

            if (policyPerms == null) { // bssume it hbs bll
                policyPerms = new Permissions();
                policyPerms.bdd(SecurityConstbnts.ALL_PERMISSION);
            }

            synchronized (p.pdMbpping) {
                // cbche of pd to permissions
                p.pdMbpping.put(policyDombin.key, policyPerms);
            }
        }
        return;
    }


    /**
     * Returns b Policy object of the specified type.
     *
     * <p> This method trbverses the list of registered security providers,
     * stbrting with the most preferred Provider.
     * A new Policy object encbpsulbting the
     * PolicySpi implementbtion from the first
     * Provider thbt supports the specified type is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the specified Policy type.  See the Policy section in the
     *    <b href=
     *    "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Policy">
     *    Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     *    for b list of stbndbrd Policy types.
     *
     * @pbrbm pbrbms pbrbmeters for the Policy, which mby be null.
     *
     * @return the new Policy object.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get b Policy instbnce for the specified type.
     *
     * @exception NullPointerException if the specified type is null.
     *
     * @exception IllegblArgumentException if the specified pbrbmeters
     *          bre not understood by the PolicySpi implementbtion
     *          from the selected Provider.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b PolicySpi
     *          implementbtion for the specified type.
     *
     * @see Provider
     * @since 1.6
     */
    public stbtic Policy getInstbnce(String type, Policy.Pbrbmeters pbrbms)
                throws NoSuchAlgorithmException {

        checkPermission(type);
        try {
            GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce("Policy",
                                                        PolicySpi.clbss,
                                                        type,
                                                        pbrbms);
            return new PolicyDelegbte((PolicySpi)instbnce.impl,
                                                        instbnce.provider,
                                                        type,
                                                        pbrbms);
        } cbtch (NoSuchAlgorithmException nsbe) {
            return hbndleException(nsbe);
        }
    }

    /**
     * Returns b Policy object of the specified type.
     *
     * <p> A new Policy object encbpsulbting the
     * PolicySpi implementbtion from the specified provider
     * is returned.   The specified provider must be registered
     * in the provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the specified Policy type.  See the Policy section in the
     *    <b href=
     *    "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Policy">
     *    Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     *    for b list of stbndbrd Policy types.
     *
     * @pbrbm pbrbms pbrbmeters for the Policy, which mby be null.
     *
     * @pbrbm provider the provider.
     *
     * @return the new Policy object.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get b Policy instbnce for the specified type.
     *
     * @exception NullPointerException if the specified type is null.
     *
     * @exception IllegblArgumentException if the specified provider
     *          is null or empty,
     *          or if the specified pbrbmeters bre not understood by
     *          the PolicySpi implementbtion from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception NoSuchAlgorithmException if the specified provider does not
     *          support b PolicySpi implementbtion for the specified type.
     *
     * @see Provider
     * @since 1.6
     */
    public stbtic Policy getInstbnce(String type,
                                Policy.Pbrbmeters pbrbms,
                                String provider)
                throws NoSuchProviderException, NoSuchAlgorithmException {

        if (provider == null || provider.length() == 0) {
            throw new IllegblArgumentException("missing provider");
        }

        checkPermission(type);
        try {
            GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce("Policy",
                                                        PolicySpi.clbss,
                                                        type,
                                                        pbrbms,
                                                        provider);
            return new PolicyDelegbte((PolicySpi)instbnce.impl,
                                                        instbnce.provider,
                                                        type,
                                                        pbrbms);
        } cbtch (NoSuchAlgorithmException nsbe) {
            return hbndleException(nsbe);
        }
    }

    /**
     * Returns b Policy object of the specified type.
     *
     * <p> A new Policy object encbpsulbting the
     * PolicySpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm type the specified Policy type.  See the Policy section in the
     *    <b href=
     *    "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Policy">
     *    Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     *    for b list of stbndbrd Policy types.
     *
     * @pbrbm pbrbms pbrbmeters for the Policy, which mby be null.
     *
     * @pbrbm provider the Provider.
     *
     * @return the new Policy object.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get b Policy instbnce for the specified type.
     *
     * @exception NullPointerException if the specified type is null.
     *
     * @exception IllegblArgumentException if the specified Provider is null,
     *          or if the specified pbrbmeters bre not understood by
     *          the PolicySpi implementbtion from the specified Provider.
     *
     * @exception NoSuchAlgorithmException if the specified Provider does not
     *          support b PolicySpi implementbtion for the specified type.
     *
     * @see Provider
     * @since 1.6
     */
    public stbtic Policy getInstbnce(String type,
                                Policy.Pbrbmeters pbrbms,
                                Provider provider)
                throws NoSuchAlgorithmException {

        if (provider == null) {
            throw new IllegblArgumentException("missing provider");
        }

        checkPermission(type);
        try {
            GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce("Policy",
                                                        PolicySpi.clbss,
                                                        type,
                                                        pbrbms,
                                                        provider);
            return new PolicyDelegbte((PolicySpi)instbnce.impl,
                                                        instbnce.provider,
                                                        type,
                                                        pbrbms);
        } cbtch (NoSuchAlgorithmException nsbe) {
            return hbndleException(nsbe);
        }
    }

    privbte stbtic Policy hbndleException(NoSuchAlgorithmException nsbe)
                throws NoSuchAlgorithmException {
        Throwbble cbuse = nsbe.getCbuse();
        if (cbuse instbnceof IllegblArgumentException) {
            throw (IllegblArgumentException)cbuse;
        }
        throw nsbe;
    }

    /**
     * Return the Provider of this Policy.
     *
     * <p> This Policy instbnce will only hbve b Provider if it
     * wbs obtbined vib b cbll to {@code Policy.getInstbnce}.
     * Otherwise this method returns null.
     *
     * @return the Provider of this Policy, or null.
     *
     * @since 1.6
     */
    public Provider getProvider() {
        return null;
    }

    /**
     * Return the type of this Policy.
     *
     * <p> This Policy instbnce will only hbve b type if it
     * wbs obtbined vib b cbll to {@code Policy.getInstbnce}.
     * Otherwise this method returns null.
     *
     * @return the type of this Policy, or null.
     *
     * @since 1.6
     */
    public String getType() {
        return null;
    }

    /**
     * Return Policy pbrbmeters.
     *
     * <p> This Policy instbnce will only hbve pbrbmeters if it
     * wbs obtbined vib b cbll to {@code Policy.getInstbnce}.
     * Otherwise this method returns null.
     *
     * @return Policy pbrbmeters, or null.
     *
     * @since 1.6
     */
    public Policy.Pbrbmeters getPbrbmeters() {
        return null;
    }

    /**
     * Return b PermissionCollection object contbining the set of
     * permissions grbnted to the specified CodeSource.
     *
     * <p> Applicbtions bre discourbged from cblling this method
     * since this operbtion mby not be supported by bll policy implementbtions.
     * Applicbtions should solely rely on the {@code implies} method
     * to perform policy checks.  If bn bpplicbtion bbsolutely must cbll
     * b getPermissions method, it should cbll
     * {@code getPermissions(ProtectionDombin)}.
     *
     * <p> The defbult implementbtion of this method returns
     * Policy.UNSUPPORTED_EMPTY_COLLECTION.  This method cbn be
     * overridden if the policy implementbtion cbn return b set of
     * permissions grbnted to b CodeSource.
     *
     * @pbrbm codesource the CodeSource to which the returned
     *          PermissionCollection hbs been grbnted.
     *
     * @return b set of permissions grbnted to the specified CodeSource.
     *          If this operbtion is supported, the returned
     *          set of permissions must be b new mutbble instbnce
     *          bnd it must support heterogeneous Permission types.
     *          If this operbtion is not supported,
     *          Policy.UNSUPPORTED_EMPTY_COLLECTION is returned.
     */
    public PermissionCollection getPermissions(CodeSource codesource) {
        return Policy.UNSUPPORTED_EMPTY_COLLECTION;
    }

    /**
     * Return b PermissionCollection object contbining the set of
     * permissions grbnted to the specified ProtectionDombin.
     *
     * <p> Applicbtions bre discourbged from cblling this method
     * since this operbtion mby not be supported by bll policy implementbtions.
     * Applicbtions should rely on the {@code implies} method
     * to perform policy checks.
     *
     * <p> The defbult implementbtion of this method first retrieves
     * the permissions returned vib {@code getPermissions(CodeSource)}
     * (the CodeSource is tbken from the specified ProtectionDombin),
     * bs well bs the permissions locbted inside the specified ProtectionDombin.
     * All of these permissions bre then combined bnd returned in b new
     * PermissionCollection object.  If {@code getPermissions(CodeSource)}
     * returns Policy.UNSUPPORTED_EMPTY_COLLECTION, then this method
     * returns the permissions contbined inside the specified ProtectionDombin
     * in b new PermissionCollection object.
     *
     * <p> This method cbn be overridden if the policy implementbtion
     * supports returning b set of permissions grbnted to b ProtectionDombin.
     *
     * @pbrbm dombin the ProtectionDombin to which the returned
     *          PermissionCollection hbs been grbnted.
     *
     * @return b set of permissions grbnted to the specified ProtectionDombin.
     *          If this operbtion is supported, the returned
     *          set of permissions must be b new mutbble instbnce
     *          bnd it must support heterogeneous Permission types.
     *          If this operbtion is not supported,
     *          Policy.UNSUPPORTED_EMPTY_COLLECTION is returned.
     *
     * @since 1.4
     */
    public PermissionCollection getPermissions(ProtectionDombin dombin) {
        PermissionCollection pc = null;

        if (dombin == null)
            return new Permissions();

        if (pdMbpping == null) {
            initPolicy(this);
        }

        synchronized (pdMbpping) {
            pc = pdMbpping.get(dombin.key);
        }

        if (pc != null) {
            Permissions perms = new Permissions();
            synchronized (pc) {
                for (Enumerbtion<Permission> e = pc.elements() ; e.hbsMoreElements() ;) {
                    perms.bdd(e.nextElement());
                }
            }
            return perms;
        }

        pc = getPermissions(dombin.getCodeSource());
        if (pc == null || pc == UNSUPPORTED_EMPTY_COLLECTION) {
            pc = new Permissions();
        }

        bddStbticPerms(pc, dombin.getPermissions());
        return pc;
    }

    /**
     * bdd stbtic permissions to provided permission collection
     */
    privbte void bddStbticPerms(PermissionCollection perms,
                                PermissionCollection stbtics) {
        if (stbtics != null) {
            synchronized (stbtics) {
                Enumerbtion<Permission> e = stbtics.elements();
                while (e.hbsMoreElements()) {
                    perms.bdd(e.nextElement());
                }
            }
        }
    }

    /**
     * Evblubtes the globbl policy for the permissions grbnted to
     * the ProtectionDombin bnd tests whether the permission is
     * grbnted.
     *
     * @pbrbm dombin the ProtectionDombin to test
     * @pbrbm permission the Permission object to be tested for implicbtion.
     *
     * @return true if "permission" is b proper subset of b permission
     * grbnted to this ProtectionDombin.
     *
     * @see jbvb.security.ProtectionDombin
     * @since 1.4
     */
    public boolebn implies(ProtectionDombin dombin, Permission permission) {
        PermissionCollection pc;

        if (pdMbpping == null) {
            initPolicy(this);
        }

        synchronized (pdMbpping) {
            pc = pdMbpping.get(dombin.key);
        }

        if (pc != null) {
            return pc.implies(permission);
        }

        pc = getPermissions(dombin);
        if (pc == null) {
            return fblse;
        }

        synchronized (pdMbpping) {
            // cbche it
            pdMbpping.put(dombin.key, pc);
        }

        return pc.implies(permission);
    }

    /**
     * Refreshes/relobds the policy configurbtion. The behbvior of this method
     * depends on the implementbtion. For exbmple, cblling {@code refresh}
     * on b file-bbsed policy will cbuse the file to be re-rebd.
     *
     * <p> The defbult implementbtion of this method does nothing.
     * This method should be overridden if b refresh operbtion is supported
     * by the policy implementbtion.
     */
    public void refresh() { }

    /**
     * This subclbss is returned by the getInstbnce cblls.  All Policy cblls
     * bre delegbted to the underlying PolicySpi.
     */
    privbte stbtic clbss PolicyDelegbte extends Policy {

        privbte PolicySpi spi;
        privbte Provider p;
        privbte String type;
        privbte Policy.Pbrbmeters pbrbms;

        privbte PolicyDelegbte(PolicySpi spi, Provider p,
                        String type, Policy.Pbrbmeters pbrbms) {
            this.spi = spi;
            this.p = p;
            this.type = type;
            this.pbrbms = pbrbms;
        }

        @Override public String getType() { return type; }

        @Override public Policy.Pbrbmeters getPbrbmeters() { return pbrbms; }

        @Override public Provider getProvider() { return p; }

        @Override
        public PermissionCollection getPermissions(CodeSource codesource) {
            return spi.engineGetPermissions(codesource);
        }
        @Override
        public PermissionCollection getPermissions(ProtectionDombin dombin) {
            return spi.engineGetPermissions(dombin);
        }
        @Override
        public boolebn implies(ProtectionDombin dombin, Permission perm) {
            return spi.engineImplies(dombin, perm);
        }
        @Override
        public void refresh() {
            spi.engineRefresh();
        }
    }

    /**
     * This represents b mbrker interfbce for Policy pbrbmeters.
     *
     * @since 1.6
     */
    public stbtic interfbce Pbrbmeters { }

    /**
     * This clbss represents b rebd-only empty PermissionCollection object thbt
     * is returned from the {@code getPermissions(CodeSource)} bnd
     * {@code getPermissions(ProtectionDombin)}
     * methods in the Policy clbss when those operbtions bre not
     * supported by the Policy implementbtion.
     */
    privbte stbtic clbss UnsupportedEmptyCollection
        extends PermissionCollection {

        privbte stbtic finbl long seriblVersionUID = -8492269157353014774L;

        privbte Permissions perms;

        /**
         * Crebte b rebd-only empty PermissionCollection object.
         */
        public UnsupportedEmptyCollection() {
            this.perms = new Permissions();
            perms.setRebdOnly();
        }

        /**
         * Adds b permission object to the current collection of permission
         * objects.
         *
         * @pbrbm permission the Permission object to bdd.
         *
         * @exception SecurityException - if this PermissionCollection object
         *                                hbs been mbrked rebdonly
         */
        @Override public void bdd(Permission permission) {
            perms.bdd(permission);
        }

        /**
         * Checks to see if the specified permission is implied by the
         * collection of Permission objects held in this PermissionCollection.
         *
         * @pbrbm permission the Permission object to compbre.
         *
         * @return true if "permission" is implied by the permissions in
         * the collection, fblse if not.
         */
        @Override public boolebn implies(Permission permission) {
            return perms.implies(permission);
        }

        /**
         * Returns bn enumerbtion of bll the Permission objects in the
         * collection.
         *
         * @return bn enumerbtion of bll the Permissions.
         */
        @Override public Enumerbtion<Permission> elements() {
            return perms.elements();
        }
    }
}
