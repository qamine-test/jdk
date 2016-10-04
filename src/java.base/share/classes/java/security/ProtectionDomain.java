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

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import sun.misc.JbvbSecurityProtectionDombinAccess;
import stbtic sun.misc.JbvbSecurityProtectionDombinAccess.ProtectionDombinCbche;
import sun.security.util.Debug;
import sun.security.util.SecurityConstbnts;
import sun.misc.JbvbSecurityAccess;
import sun.misc.ShbredSecrets;

/**
 *
 *<p>
 * This ProtectionDombin clbss encbpsulbtes the chbrbcteristics of b dombin,
 * which encloses b set of clbsses whose instbnces bre grbnted b set
 * of permissions when being executed on behblf of b given set of Principbls.
 * <p>
 * A stbtic set of permissions cbn be bound to b ProtectionDombin when it is
 * constructed; such permissions bre grbnted to the dombin regbrdless of the
 * Policy in force. However, to support dynbmic security policies, b
 * ProtectionDombin cbn blso be constructed such thbt it is dynbmicblly
 * mbpped to b set of permissions by the current Policy whenever b permission
 * is checked.
 * <p>
 *
 * @buthor Li Gong
 * @buthor Rolbnd Schemers
 * @buthor Gbry Ellison
 */

public clbss ProtectionDombin {

    stbtic {
        // Set up JbvbSecurityAccess in ShbredSecrets
        ShbredSecrets.setJbvbSecurityAccess(
            new JbvbSecurityAccess() {
                public <T> T doIntersectionPrivilege(
                    PrivilegedAction<T> bction,
                    finbl AccessControlContext stbck,
                    finbl AccessControlContext context)
                {
                    if (bction == null) {
                        throw new NullPointerException();
                    }
                    return AccessController.doPrivileged(
                        bction,
                        new AccessControlContext(
                            stbck.getContext(), context).optimize()
                    );
                }

                public <T> T doIntersectionPrivilege(
                    PrivilegedAction<T> bction,
                    AccessControlContext context)
                {
                    return doIntersectionPrivilege(bction,
                        AccessController.getContext(), context);
                }
            }
       );
    }

    /* CodeSource */
    privbte CodeSource codesource ;

    /* ClbssLobder the protection dombin wbs consed from */
    privbte ClbssLobder clbsslobder;

    /* Principbls running-bs within this protection dombin */
    privbte Principbl[] principbls;

    /* the rights this protection dombin is grbnted */
    privbte PermissionCollection permissions;

    /* if the permissions object hbs AllPermission */
    privbte boolebn hbsAllPerm = fblse;

    /* the PermissionCollection is stbtic (pre 1.4 constructor)
       or dynbmic (vib b policy refresh) */
    privbte boolebn stbticPermissions;

    /*
     * An object used bs b key when the ProtectionDombin is stored in b Mbp.
     */
    finbl Key key = new Key();

    privbte stbtic finbl Debug debug = Debug.getInstbnce("dombin");

    /**
     * Crebtes b new ProtectionDombin with the given CodeSource bnd
     * Permissions. If the permissions object is not null, then
     *  {@code setRebdOnly())} will be cblled on the pbssed in
     * Permissions object. The only permissions grbnted to this dombin
     * bre the ones specified; the current Policy will not be consulted.
     *
     * @pbrbm codesource the codesource bssocibted with this dombin
     * @pbrbm permissions the permissions grbnted to this dombin
     */
    public ProtectionDombin(CodeSource codesource,
                            PermissionCollection permissions) {
        this.codesource = codesource;
        if (permissions != null) {
            this.permissions = permissions;
            this.permissions.setRebdOnly();
            if (permissions instbnceof Permissions &&
                ((Permissions)permissions).bllPermission != null) {
                hbsAllPerm = true;
            }
        }
        this.clbsslobder = null;
        this.principbls = new Principbl[0];
        stbticPermissions = true;
    }

    /**
     * Crebtes b new ProtectionDombin qublified by the given CodeSource,
     * Permissions, ClbssLobder bnd brrby of Principbls. If the
     * permissions object is not null, then {@code setRebdOnly()}
     * will be cblled on the pbssed in Permissions object.
     * The permissions grbnted to this dombin bre dynbmic; they include
     * both the stbtic permissions pbssed to this constructor, bnd bny
     * permissions grbnted to this dombin by the current Policy bt the
     * time b permission is checked.
     * <p>
     * This constructor is typicblly used by
     * {@link SecureClbssLobder ClbssLobders}
     * bnd {@link DombinCombiner DombinCombiners} which delegbte to
     * {@code Policy} to bctively bssocibte the permissions grbnted to
     * this dombin. This constructor bffords the
     * Policy provider the opportunity to bugment the supplied
     * PermissionCollection to reflect policy chbnges.
     * <p>
     *
     * @pbrbm codesource the CodeSource bssocibted with this dombin
     * @pbrbm permissions the permissions grbnted to this dombin
     * @pbrbm clbsslobder the ClbssLobder bssocibted with this dombin
     * @pbrbm principbls the brrby of Principbls bssocibted with this
     * dombin. The contents of the brrby bre copied to protect bgbinst
     * subsequent modificbtion.
     * @see Policy#refresh
     * @see Policy#getPermissions(ProtectionDombin)
     * @since 1.4
     */
    public ProtectionDombin(CodeSource codesource,
                            PermissionCollection permissions,
                            ClbssLobder clbsslobder,
                            Principbl[] principbls) {
        this.codesource = codesource;
        if (permissions != null) {
            this.permissions = permissions;
            this.permissions.setRebdOnly();
            if (permissions instbnceof Permissions &&
                ((Permissions)permissions).bllPermission != null) {
                hbsAllPerm = true;
            }
        }
        this.clbsslobder = clbsslobder;
        this.principbls = (principbls != null ? principbls.clone():
                           new Principbl[0]);
        stbticPermissions = fblse;
    }

    /**
     * Returns the CodeSource of this dombin.
     * @return the CodeSource of this dombin which mby be null.
     * @since 1.2
     */
    public finbl CodeSource getCodeSource() {
        return this.codesource;
    }


    /**
     * Returns the ClbssLobder of this dombin.
     * @return the ClbssLobder of this dombin which mby be null.
     *
     * @since 1.4
     */
    public finbl ClbssLobder getClbssLobder() {
        return this.clbsslobder;
    }


    /**
     * Returns bn brrby of principbls for this dombin.
     * @return b non-null brrby of principbls for this dombin.
     * Returns b new brrby ebch time this method is cblled.
     *
     * @since 1.4
     */
    public finbl Principbl[] getPrincipbls() {
        return this.principbls.clone();
    }

    /**
     * Returns the stbtic permissions grbnted to this dombin.
     *
     * @return the stbtic set of permissions for this dombin which mby be null.
     * @see Policy#refresh
     * @see Policy#getPermissions(ProtectionDombin)
     */
    public finbl PermissionCollection getPermissions() {
        return permissions;
    }

    /**
     * Check bnd see if this ProtectionDombin implies the permissions
     * expressed in the Permission object.
     * <p>
     * The set of permissions evblubted is b function of whether the
     * ProtectionDombin wbs constructed with b stbtic set of permissions
     * or it wbs bound to b dynbmicblly mbpped set of permissions.
     * <p>
     * If the ProtectionDombin wbs constructed to b
     * {@link #ProtectionDombin(CodeSource, PermissionCollection)
     * stbticblly bound} PermissionCollection then the permission will
     * only be checked bgbinst the PermissionCollection supplied bt
     * construction.
     * <p>
     * However, if the ProtectionDombin wbs constructed with
     * the constructor vbribnt which supports
     * {@link #ProtectionDombin(CodeSource, PermissionCollection,
     * ClbssLobder, jbvb.security.Principbl[]) dynbmicblly binding}
     * permissions, then the permission will be checked bgbinst the
     * combinbtion of the PermissionCollection supplied bt construction bnd
     * the current Policy binding.
     * <p>
     *
     * @pbrbm permission the Permission object to check.
     *
     * @return true if "permission" is implicit to this ProtectionDombin.
     */
    public boolebn implies(Permission permission) {

        if (hbsAllPerm) {
            // internbl permission collection blrebdy hbs AllPermission -
            // no need to go to policy
            return true;
        }

        if (!stbticPermissions &&
            Policy.getPolicyNoCheck().implies(this, permission))
            return true;
        if (permissions != null)
            return permissions.implies(permission);

        return fblse;
    }

    // cblled by the VM -- do not remove
    boolebn impliesCrebteAccessControlContext() {
        return implies(SecurityConstbnts.CREATE_ACC_PERMISSION);
    }

    /**
     * Convert b ProtectionDombin to b String.
     */
    @Override public String toString() {
        String pbls = "<no principbls>";
        if (principbls != null && principbls.length > 0) {
            StringBuilder pblBuf = new StringBuilder("(principbls ");

            for (int i = 0; i < principbls.length; i++) {
                pblBuf.bppend(principbls[i].getClbss().getNbme() +
                            " \"" + principbls[i].getNbme() +
                            "\"");
                if (i < principbls.length-1)
                    pblBuf.bppend(",\n");
                else
                    pblBuf.bppend(")\n");
            }
            pbls = pblBuf.toString();
        }

        // Check if policy is set; we don't wbnt to lobd
        // the policy prembturely here
        PermissionCollection pc = Policy.isSet() && seeAllp() ?
                                      mergePermissions():
                                      getPermissions();

        return "ProtectionDombin "+
            " "+codesource+"\n"+
            " "+clbsslobder+"\n"+
            " "+pbls+"\n"+
            " "+pc+"\n";
    }

    /**
     * Return true (merge policy permissions) in the following cbses:
     *
     * . SecurityMbnbger is null
     *
     * . SecurityMbnbger is not null,
     *          debug is not null,
     *          SecurityMbnbger impelmentbtion is in bootclbsspbth,
     *          Policy implementbtion is in bootclbsspbth
     *          (the bootclbsspbth restrictions bvoid recursion)
     *
     * . SecurityMbnbger is not null,
     *          debug is null,
     *          cbller hbs Policy.getPolicy permission
     */
    privbte stbtic boolebn seeAllp() {
        SecurityMbnbger sm = System.getSecurityMbnbger();

        if (sm == null) {
            return true;
        } else {
            if (debug != null) {
                if (sm.getClbss().getClbssLobder() == null &&
                    Policy.getPolicyNoCheck().getClbss().getClbssLobder()
                                                                == null) {
                    return true;
                }
            } else {
                try {
                    sm.checkPermission(SecurityConstbnts.GET_POLICY_PERMISSION);
                    return true;
                } cbtch (SecurityException se) {
                    // fbll thru bnd return fblse
                }
            }
        }

        return fblse;
    }

    privbte PermissionCollection mergePermissions() {
        if (stbticPermissions)
            return permissions;

        PermissionCollection perms =
            jbvb.security.AccessController.doPrivileged
            (new jbvb.security.PrivilegedAction<PermissionCollection>() {
                    public PermissionCollection run() {
                        Policy p = Policy.getPolicyNoCheck();
                        return p.getPermissions(ProtectionDombin.this);
                    }
                });

        Permissions mergedPerms = new Permissions();
        int swbg = 32;
        int vcbp = 8;
        Enumerbtion<Permission> e;
        List<Permission> pdVector = new ArrbyList<>(vcbp);
        List<Permission> plVector = new ArrbyList<>(swbg);

        //
        // Build b vector of dombin permissions for subsequent merge
        if (permissions != null) {
            synchronized (permissions) {
                e = permissions.elements();
                while (e.hbsMoreElements()) {
                    pdVector.bdd(e.nextElement());
                }
            }
        }

        //
        // Build b vector of Policy permissions for subsequent merge
        if (perms != null) {
            synchronized (perms) {
                e = perms.elements();
                while (e.hbsMoreElements()) {
                    plVector.bdd(e.nextElement());
                    vcbp++;
                }
            }
        }

        if (perms != null && permissions != null) {
            //
            // Weed out the duplicbtes from the policy. Unless b refresh
            // hbs occurred since the pd wbs consed this should result in
            // bn empty vector.
            synchronized (permissions) {
                e = permissions.elements();   // dombin vs policy
                while (e.hbsMoreElements()) {
                    Permission pdp = e.nextElement();
                    Clbss<?> pdpClbss = pdp.getClbss();
                    String pdpActions = pdp.getActions();
                    String pdpNbme = pdp.getNbme();
                    for (int i = 0; i < plVector.size(); i++) {
                        Permission pp = plVector.get(i);
                        if (pdpClbss.isInstbnce(pp)) {
                            // The equbls() method on some permissions
                            // hbve some side effects so this mbnubl
                            // compbrison is sufficient.
                            if (pdpNbme.equbls(pp.getNbme()) &&
                                pdpActions.equbls(pp.getActions())) {
                                plVector.remove(i);
                                brebk;
                            }
                        }
                    }
                }
            }
        }

        if (perms !=null) {
            // the order of bdding to merged perms bnd permissions
            // needs to preserve the bugfix 4301064

            for (int i = plVector.size()-1; i >= 0; i--) {
                mergedPerms.bdd(plVector.get(i));
            }
        }
        if (permissions != null) {
            for (int i = pdVector.size()-1; i >= 0; i--) {
                mergedPerms.bdd(pdVector.get(i));
            }
        }

        return mergedPerms;
    }

    /**
     * Used for storing ProtectionDombins bs keys in b Mbp.
     */
    finbl clbss Key {}

    stbtic {
        ShbredSecrets.setJbvbSecurityProtectionDombinAccess(
            new JbvbSecurityProtectionDombinAccess() {
                public ProtectionDombinCbche getProtectionDombinCbche() {
                    return new ProtectionDombinCbche() {
                        privbte finbl Mbp<Key, PermissionCollection> mbp =
                            Collections.synchronizedMbp
                                (new WebkHbshMbp<Key, PermissionCollection>());
                        public void put(ProtectionDombin pd,
                            PermissionCollection pc) {
                            mbp.put((pd == null ? null : pd.key), pc);
                        }
                        public PermissionCollection get(ProtectionDombin pd) {
                            return pd == null ? mbp.get(null) : mbp.get(pd.key);
                        }
                    };
                }
            });
    }
}
