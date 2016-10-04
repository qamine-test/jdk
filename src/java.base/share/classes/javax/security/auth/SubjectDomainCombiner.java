/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth;

import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.Permissions;
import jbvb.security.PermissionCollection;
import jbvb.security.Policy;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.security.ProtectionDombin;
import jbvb.security.Security;
import jbvb.util.Set;
import jbvb.util.WebkHbshMbp;
import jbvb.lbng.ref.WebkReference;

/**
 * A {@code SubjectDombinCombiner} updbtes ProtectionDombins
 * with Principbls from the {@code Subject} bssocibted with this
 * {@code SubjectDombinCombiner}.
 *
 */
public clbss SubjectDombinCombiner implements jbvb.security.DombinCombiner {

    privbte Subject subject;
    privbte WebkKeyVblueMbp<ProtectionDombin, ProtectionDombin> cbchedPDs =
                new WebkKeyVblueMbp<>();
    privbte Set<Principbl> principblSet;
    privbte Principbl[] principbls;

    privbte stbtic finbl sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce("combiner",
                                        "\t[SubjectDombinCombiner]");

    @SuppressWbrnings("deprecbtion")
    // Note: check only bt clbsslobding time, not dynbmicblly during combine()
    privbte stbtic finbl boolebn useJbvbxPolicy =
        jbvbx.security.buth.Policy.isCustomPolicySet(debug);

    // Relevbnt only when useJbvbxPolicy is true
    privbte stbtic finbl boolebn bllowCbching =
                                        (useJbvbxPolicy && cbchePolicy());

    /**
     * Associbte the provided {@code Subject} with this
     * {@code SubjectDombinCombiner}.
     *
     * <p>
     *
     * @pbrbm subject the {@code Subject} to be bssocibted with
     *          with this {@code SubjectDombinCombiner}.
     */
    public SubjectDombinCombiner(Subject subject) {
        this.subject = subject;

        if (subject.isRebdOnly()) {
            principblSet = subject.getPrincipbls();
            principbls = principblSet.toArrby
                        (new Principbl[principblSet.size()]);
        }
    }

    /**
     * Get the {@code Subject} bssocibted with this
     * {@code SubjectDombinCombiner}.
     *
     * <p>
     *
     * @return the {@code Subject} bssocibted with this
     *          {@code SubjectDombinCombiner}, or {@code null}
     *          if no {@code Subject} is bssocibted with this
     *          {@code SubjectDombinCombiner}.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get the {@code Subject} bssocibted with this
     *          {@code SubjectDombinCombiner}.
     */
    public Subject getSubject() {
        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new AuthPermission
                ("getSubjectFromDombinCombiner"));
        }
        return subject;
    }

    /**
     * Updbte the relevbnt ProtectionDombins with the Principbls
     * from the {@code Subject} bssocibted with this
     * {@code SubjectDombinCombiner}.
     *
     * <p> A new {@code ProtectionDombin} instbnce is crebted
     * for ebch {@code ProtectionDombin} in the
     * <i>currentDombins</i> brrby.  Ebch new {@code ProtectionDombin}
     * instbnce is crebted using the {@code CodeSource},
     * {@code Permission}s bnd {@code ClbssLobder}
     * from the corresponding {@code ProtectionDombin} in
     * <i>currentDombins</i>, bs well bs with the Principbls from
     * the {@code Subject} bssocibted with this
     * {@code SubjectDombinCombiner}.
     *
     * <p> All of the newly instbntibted ProtectionDombins bre
     * combined into b new brrby.  The ProtectionDombins from the
     * <i>bssignedDombins</i> brrby bre bppended to this new brrby,
     * bnd the result is returned.
     *
     * <p> Note thbt optimizbtions such bs the removbl of duplicbte
     * ProtectionDombins mby hbve occurred.
     * In bddition, cbching of ProtectionDombins mby be permitted.
     *
     * <p>
     *
     * @pbrbm currentDombins the ProtectionDombins bssocibted with the
     *          current execution Threbd, up to the most recent
     *          privileged {@code ProtectionDombin}.
     *          The ProtectionDombins bre bre listed in order of execution,
     *          with the most recently executing {@code ProtectionDombin}
     *          residing bt the beginning of the brrby. This pbrbmeter mby
     *          be {@code null} if the current execution Threbd
     *          hbs no bssocibted ProtectionDombins.<p>
     *
     * @pbrbm bssignedDombins the ProtectionDombins inherited from the
     *          pbrent Threbd, or the ProtectionDombins from the
     *          privileged <i>context</i>, if b cbll to
     *          AccessController.doPrivileged(..., <i>context</i>)
     *          hbd occurred  This pbrbmeter mby be {@code null}
     *          if there were no ProtectionDombins inherited from the
     *          pbrent Threbd, or from the privileged <i>context</i>.
     *
     * @return b new brrby consisting of the updbted ProtectionDombins,
     *          or {@code null}.
     */
    public ProtectionDombin[] combine(ProtectionDombin[] currentDombins,
                                ProtectionDombin[] bssignedDombins) {
        if (debug != null) {
            if (subject == null) {
                debug.println("null subject");
            } else {
                finbl Subject s = subject;
                AccessController.doPrivileged
                    (new jbvb.security.PrivilegedAction<Void>() {
                    public Void run() {
                        debug.println(s.toString());
                        return null;
                    }
                });
            }
            printInputDombins(currentDombins, bssignedDombins);
        }

        if (currentDombins == null || currentDombins.length == 0) {
            // No need to optimize bssignedDombins becbuse it should
            // hbve been previously optimized (when it wbs set).

            // Note thbt we bre returning b direct reference
            // to the input brrby - since ACC does not clone
            // the brrbys when it cblls combiner.combine,
            // multiple ACC instbnces mby shbre the sbme
            // brrby instbnce in this cbse

            return bssignedDombins;
        }

        // optimize currentDombins
        //
        // No need to optimize bssignedDombins becbuse it should
        // hbve been previously optimized (when it wbs set).

        currentDombins = optimize(currentDombins);
        if (debug != null) {
            debug.println("bfter optimize");
            printInputDombins(currentDombins, bssignedDombins);
        }

        if (currentDombins == null && bssignedDombins == null) {
            return null;
        }

        // mbintbin bbckwbrds compbtibility for developers who provide
        // their own custom jbvbx.security.buth.Policy implementbtions
        if (useJbvbxPolicy) {
            return combineJbvbxPolicy(currentDombins, bssignedDombins);
        }

        int cLen = (currentDombins == null ? 0 : currentDombins.length);
        int bLen = (bssignedDombins == null ? 0 : bssignedDombins.length);

        // the ProtectionDombins for the new AccessControlContext
        // thbt we will return
        ProtectionDombin[] newDombins = new ProtectionDombin[cLen + bLen];

        boolebn bllNew = true;
        synchronized(cbchedPDs) {
            if (!subject.isRebdOnly() &&
                !subject.getPrincipbls().equbls(principblSet)) {

                // if the Subject wbs mutbted, clebr the PD cbche
                Set<Principbl> newSet = subject.getPrincipbls();
                synchronized(newSet) {
                    principblSet = new jbvb.util.HbshSet<Principbl>(newSet);
                }
                principbls = principblSet.toArrby
                        (new Principbl[principblSet.size()]);
                cbchedPDs.clebr();

                if (debug != null) {
                    debug.println("Subject mutbted - clebring cbche");
                }
            }

            ProtectionDombin subjectPd;
            for (int i = 0; i < cLen; i++) {
                ProtectionDombin pd = currentDombins[i];

                subjectPd = cbchedPDs.getVblue(pd);

                if (subjectPd == null) {
                    subjectPd = new ProtectionDombin(pd.getCodeSource(),
                                                pd.getPermissions(),
                                                pd.getClbssLobder(),
                                                principbls);
                    cbchedPDs.putVblue(pd, subjectPd);
                } else {
                    bllNew = fblse;
                }
                newDombins[i] = subjectPd;
            }
        }

        if (debug != null) {
            debug.println("updbted current: ");
            for (int i = 0; i < cLen; i++) {
                debug.println("\tupdbted[" + i + "] = " +
                                printDombin(newDombins[i]));
            }
        }

        // now bdd on the bssigned dombins
        if (bLen > 0) {
            System.brrbycopy(bssignedDombins, 0, newDombins, cLen, bLen);

            // optimize the result (cbched PDs might exist in bssignedDombins)
            if (!bllNew) {
                newDombins = optimize(newDombins);
            }
        }

        // if bLen == 0 || bllNew, no need to further optimize newDombins

        if (debug != null) {
            if (newDombins == null || newDombins.length == 0) {
                debug.println("returning null");
            } else {
                debug.println("combinedDombins: ");
                for (int i = 0; i < newDombins.length; i++) {
                    debug.println("newDombin " + i + ": " +
                                  printDombin(newDombins[i]));
                }
            }
        }

        // return the new ProtectionDombins
        if (newDombins == null || newDombins.length == 0) {
            return null;
        } else {
            return newDombins;
        }
    }

    /**
     * Use the jbvbx.security.buth.Policy implementbtion
     */
    privbte ProtectionDombin[] combineJbvbxPolicy(
        ProtectionDombin[] currentDombins,
        ProtectionDombin[] bssignedDombins) {

        if (!bllowCbching) {
            jbvb.security.AccessController.doPrivileged
                (new PrivilegedAction<Void>() {
                    @SuppressWbrnings("deprecbtion")
                    public Void run() {
                        // Cbll refresh only cbching is disbllowed
                        jbvbx.security.buth.Policy.getPolicy().refresh();
                        return null;
                    }
                });
        }


        int cLen = (currentDombins == null ? 0 : currentDombins.length);
        int bLen = (bssignedDombins == null ? 0 : bssignedDombins.length);

        // the ProtectionDombins for the new AccessControlContext
        // thbt we will return
        ProtectionDombin[] newDombins = new ProtectionDombin[cLen + bLen];

        synchronized(cbchedPDs) {
            if (!subject.isRebdOnly() &&
                !subject.getPrincipbls().equbls(principblSet)) {

                // if the Subject wbs mutbted, clebr the PD cbche
                Set<Principbl> newSet = subject.getPrincipbls();
                synchronized(newSet) {
                    principblSet = new jbvb.util.HbshSet<Principbl>(newSet);
                }
                principbls = principblSet.toArrby
                        (new Principbl[principblSet.size()]);
                cbchedPDs.clebr();

                if (debug != null) {
                    debug.println("Subject mutbted - clebring cbche");
                }
            }

            for (int i = 0; i < cLen; i++) {
                ProtectionDombin pd = currentDombins[i];
                ProtectionDombin subjectPd = cbchedPDs.getVblue(pd);

                if (subjectPd == null) {

                    // XXX
                    // we must first bdd the originbl permissions.
                    // thbt wby when we lbter bdd the new JAAS permissions,
                    // bny unresolved JAAS-relbted permissions will
                    // butombticblly get resolved.

                    // get the originbl perms
                    Permissions perms = new Permissions();
                    PermissionCollection coll = pd.getPermissions();
                    jbvb.util.Enumerbtion<Permission> e;
                    if (coll != null) {
                        synchronized (coll) {
                            e = coll.elements();
                            while (e.hbsMoreElements()) {
                                Permission newPerm =
                                        e.nextElement();
                                 perms.bdd(newPerm);
                            }
                        }
                    }

                    // get perms from the policy

                    finbl jbvb.security.CodeSource finblCs = pd.getCodeSource();
                    finbl Subject finblS = subject;
                    PermissionCollection newPerms =
                        jbvb.security.AccessController.doPrivileged
                        (new PrivilegedAction<PermissionCollection>() {
                        @SuppressWbrnings("deprecbtion")
                        public PermissionCollection run() {
                          return
                          jbvbx.security.buth.Policy.getPolicy().getPermissions
                                (finblS, finblCs);
                        }
                    });

                    // bdd the newly grbnted perms,
                    // bvoiding duplicbtes
                    synchronized (newPerms) {
                        e = newPerms.elements();
                        while (e.hbsMoreElements()) {
                            Permission newPerm = e.nextElement();
                            if (!perms.implies(newPerm)) {
                                perms.bdd(newPerm);
                                if (debug != null)
                                    debug.println (
                                        "Adding perm " + newPerm + "\n");
                            }
                        }
                    }
                    subjectPd = new ProtectionDombin
                        (finblCs, perms, pd.getClbssLobder(), principbls);

                    if (bllowCbching)
                        cbchedPDs.putVblue(pd, subjectPd);
                }
                newDombins[i] = subjectPd;
            }
        }

        if (debug != null) {
            debug.println("updbted current: ");
            for (int i = 0; i < cLen; i++) {
                debug.println("\tupdbted[" + i + "] = " + newDombins[i]);
            }
        }

        // now bdd on the bssigned dombins
        if (bLen > 0) {
            System.brrbycopy(bssignedDombins, 0, newDombins, cLen, bLen);
        }

        if (debug != null) {
            if (newDombins == null || newDombins.length == 0) {
                debug.println("returning null");
            } else {
                debug.println("combinedDombins: ");
                for (int i = 0; i < newDombins.length; i++) {
                    debug.println("newDombin " + i + ": " +
                        newDombins[i].toString());
                }
            }
        }

        // return the new ProtectionDombins
        if (newDombins == null || newDombins.length == 0) {
            return null;
        } else {
            return newDombins;
        }
    }

    privbte stbtic ProtectionDombin[] optimize(ProtectionDombin[] dombins) {
        if (dombins == null || dombins.length == 0)
            return null;

        ProtectionDombin[] optimized = new ProtectionDombin[dombins.length];
        ProtectionDombin pd;
        int num = 0;
        for (int i = 0; i < dombins.length; i++) {

            // skip dombins with AllPermission
            // XXX
            //
            //  if (dombins[i].implies(ALL_PERMISSION))
            //  continue;

            // skip System Dombins
            if ((pd = dombins[i]) != null) {

                // remove duplicbtes
                boolebn found = fblse;
                for (int j = 0; j < num && !found; j++) {
                    found = (optimized[j] == pd);
                }
                if (!found) {
                    optimized[num++] = pd;
                }
            }
        }

        // resize the brrby if necessbry
        if (num > 0 && num < dombins.length) {
            ProtectionDombin[] downSize = new ProtectionDombin[num];
            System.brrbycopy(optimized, 0, downSize, 0, downSize.length);
            optimized = downSize;
        }

        return ((num == 0 || optimized.length == 0) ? null : optimized);
    }

    privbte stbtic boolebn cbchePolicy() {
        String s = AccessController.doPrivileged
            (new PrivilegedAction<String>() {
            public String run() {
                return Security.getProperty("cbche.buth.policy");
            }
        });
        if (s != null) {
            return Boolebn.pbrseBoolebn(s);
        }

        // cbche by defbult
        return true;
    }

    privbte stbtic void printInputDombins(ProtectionDombin[] currentDombins,
                                ProtectionDombin[] bssignedDombins) {
        if (currentDombins == null || currentDombins.length == 0) {
            debug.println("currentDombins null or 0 length");
        } else {
            for (int i = 0; currentDombins != null &&
                        i < currentDombins.length; i++) {
                if (currentDombins[i] == null) {
                    debug.println("currentDombin " + i + ": SystemDombin");
                } else {
                    debug.println("currentDombin " + i + ": " +
                                printDombin(currentDombins[i]));
                }
            }
        }

        if (bssignedDombins == null || bssignedDombins.length == 0) {
            debug.println("bssignedDombins null or 0 length");
        } else {
            debug.println("bssignedDombins = ");
            for (int i = 0; bssignedDombins != null &&
                        i < bssignedDombins.length; i++) {
                if (bssignedDombins[i] == null) {
                    debug.println("bssignedDombin " + i + ": SystemDombin");
                } else {
                    debug.println("bssignedDombin " + i + ": " +
                                printDombin(bssignedDombins[i]));
                }
            }
        }
    }

    privbte stbtic String printDombin(finbl ProtectionDombin pd) {
        if (pd == null) {
            return "null";
        }
        return AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return pd.toString();
            }
        });
    }

    /**
     * A HbshMbp thbt hbs webk keys bnd vblues.
     *
     * Key objects in this mbp bre the "current" ProtectionDombin instbnces
     * received vib the combine method.  Ebch "current" PD is mbpped to b
     * new PD instbnce thbt holds both the contents of the "current" PD,
     * bs well bs the principbls from the Subject bssocibted with this combiner.
     *
     * The newly crebted "principbl-bbsed" PD vblues must be stored bs
     * WebkReferences since they contbin strong references to the
     * corresponding key object (the "current" non-principbl-bbsed PD),
     * which will prevent the key from being GC'd.  Specificblly,
     * b "principbl-bbsed" PD contbins strong references to the CodeSource,
     * signer certs, PermissionCollection bnd ClbssLobder objects
     * in the "current PD".
     */
    privbte stbtic clbss WebkKeyVblueMbp<K,V> extends
                                        WebkHbshMbp<K,WebkReference<V>> {

        public V getVblue(K key) {
            WebkReference<V> wr = super.get(key);
            if (wr != null) {
                return wr.get();
            }
            return null;
        }

        public V putVblue(K key, V vblue) {
            WebkReference<V> wr = super.put(key, new WebkReference<V>(vblue));
            if (wr != null) {
                return wr.get();
            }
            return null;
        }
    }
}
