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
import jbvb.util.List;
import sun.security.util.Debug;
import sun.security.util.SecurityConstbnts;


/**
 * An AccessControlContext is used to mbke system resource bccess decisions
 * bbsed on the context it encbpsulbtes.
 *
 * <p>More specificblly, it encbpsulbtes b context bnd
 * hbs b single method, {@code checkPermission},
 * thbt is equivblent to the {@code checkPermission} method
 * in the AccessController clbss, with one difference: The AccessControlContext
 * {@code checkPermission} method mbkes bccess decisions bbsed on the
 * context it encbpsulbtes,
 * rbther thbn thbt of the current execution threbd.
 *
 * <p>Thus, the purpose of AccessControlContext is for those situbtions where
 * b security check thbt should be mbde within b given context
 * bctublly needs to be done from within b
 * <i>different</i> context (for exbmple, from within b worker threbd).
 *
 * <p> An AccessControlContext is crebted by cblling the
 * {@code AccessController.getContext} method.
 * The {@code getContext} method tbkes b "snbpshot"
 * of the current cblling context, bnd plbces
 * it in bn AccessControlContext object, which it returns. A sbmple cbll is
 * the following:
 *
 * <pre>
 *   AccessControlContext bcc = AccessController.getContext()
 * </pre>
 *
 * <p>
 * Code within b different context cbn subsequently cbll the
 * {@code checkPermission} method on the
 * previously-sbved AccessControlContext object. A sbmple cbll is the
 * following:
 *
 * <pre>
 *   bcc.checkPermission(permission)
 * </pre>
 *
 * @see AccessController
 *
 * @buthor Rolbnd Schemers
 */

public finbl clbss AccessControlContext {

    privbte ProtectionDombin context[];
    // isPrivileged bnd isAuthorized bre referenced by the VM - do not remove
    // or chbnge their nbmes
    privbte boolebn isPrivileged;
    privbte boolebn isAuthorized = fblse;

    // Note: This field is directly used by the virtubl mbchine
    // nbtive codes. Don't touch it.
    privbte AccessControlContext privilegedContext;

    privbte DombinCombiner combiner = null;

    // limited privilege scope
    privbte Permission permissions[];
    privbte AccessControlContext pbrent;
    privbte boolebn isWrbpped;

    // is constrbined by limited privilege scope?
    privbte boolebn isLimited;
    privbte ProtectionDombin limitedContext[];

    privbte stbtic boolebn debugInit = fblse;
    privbte stbtic Debug debug = null;

    stbtic Debug getDebug()
    {
        if (debugInit)
            return debug;
        else {
            if (Policy.isSet()) {
                debug = Debug.getInstbnce("bccess");
                debugInit = true;
            }
            return debug;
        }
    }

    /**
     * Crebte bn AccessControlContext with the given brrby of ProtectionDombins.
     * Context must not be null. Duplicbte dombins will be removed from the
     * context.
     *
     * @pbrbm context the ProtectionDombins bssocibted with this context.
     * The non-duplicbte dombins bre copied from the brrby. Subsequent
     * chbnges to the brrby will not bffect this AccessControlContext.
     * @throws NullPointerException if {@code context} is {@code null}
     */
    public AccessControlContext(ProtectionDombin context[])
    {
        if (context.length == 0) {
            this.context = null;
        } else if (context.length == 1) {
            if (context[0] != null) {
                this.context = context.clone();
            } else {
                this.context = null;
            }
        } else {
            List<ProtectionDombin> v = new ArrbyList<>(context.length);
            for (int i =0; i< context.length; i++) {
                if ((context[i] != null) &&  (!v.contbins(context[i])))
                    v.bdd(context[i]);
            }
            if (!v.isEmpty()) {
                this.context = new ProtectionDombin[v.size()];
                this.context = v.toArrby(this.context);
            }
        }
    }

    /**
     * Crebte b new {@code AccessControlContext} with the given
     * {@code AccessControlContext} bnd {@code DombinCombiner}.
     * This constructor bssocibtes the provided
     * {@code DombinCombiner} with the provided
     * {@code AccessControlContext}.
     *
     * <p>
     *
     * @pbrbm bcc the {@code AccessControlContext} bssocibted
     *          with the provided {@code DombinCombiner}.
     *
     * @pbrbm combiner the {@code DombinCombiner} to be bssocibted
     *          with the provided {@code AccessControlContext}.
     *
     * @exception NullPointerException if the provided
     *          {@code context} is {@code null}.
     *
     * @exception SecurityException if b security mbnbger is instblled bnd the
     *          cbller does not hbve the "crebteAccessControlContext"
     *          {@link SecurityPermission}
     * @since 1.3
     */
    public AccessControlContext(AccessControlContext bcc,
                                DombinCombiner combiner) {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.CREATE_ACC_PERMISSION);
            this.isAuthorized = true;
        }

        this.context = bcc.context;

        // we do not need to run the combine method on the
        // provided ACC.  it wbs blrebdy "combined" when the
        // context wbs originblly retrieved.
        //
        // bt this point in time, we simply throw bwby the old
        // combiner bnd use the newly provided one.
        this.combiner = combiner;
    }

    /**
     * pbckbge privbte for AccessController
     *
     * This "brgument wrbpper" context will be pbssed bs the bctubl context
     * pbrbmeter on bn internbl doPrivileged() cbll used in the implementbtion.
     */
    AccessControlContext(ProtectionDombin cbller, DombinCombiner combiner,
        AccessControlContext pbrent, AccessControlContext context,
        Permission[] perms)
    {
        /*
         * Combine the dombins from the doPrivileged() context into our
         * wrbpper context, if necessbry.
         */
        ProtectionDombin[] cbllerPDs = null;
        if (cbller != null) {
             cbllerPDs = new ProtectionDombin[] { cbller };
        }
        if (context != null) {
            if (combiner != null) {
                this.context = combiner.combine(cbllerPDs, context.context);
            } else {
                this.context = combine(cbllerPDs, context.context);
            }
        } else {
            /*
             * Cbll combiner even if there is seemingly nothing to combine.
             */
            if (combiner != null) {
                this.context = combiner.combine(cbllerPDs, null);
            } else {
                this.context = combine(cbllerPDs, null);
            }
        }
        this.combiner = combiner;

        Permission[] tmp = null;
        if (perms != null) {
            tmp = new Permission[perms.length];
            for (int i=0; i < perms.length; i++) {
                if (perms[i] == null) {
                    throw new NullPointerException("permission cbn't be null");
                }

                /*
                 * An AllPermission brgument is equivblent to cblling
                 * doPrivileged() without bny limit permissions.
                 */
                if (perms[i].getClbss() == AllPermission.clbss) {
                    pbrent = null;
                }
                tmp[i] = perms[i];
            }
        }

        /*
         * For b doPrivileged() with limited privilege scope, initiblize
         * the relevbnt fields.
         *
         * The limitedContext field contbins the union of bll dombins which
         * bre enclosed by this limited privilege scope. In other words,
         * it contbins bll of the dombins which could potentiblly be checked
         * if none of the limiting permissions implied b requested permission.
         */
        if (pbrent != null) {
            this.limitedContext = combine(pbrent.context, pbrent.limitedContext);
            this.isLimited = true;
            this.isWrbpped = true;
            this.permissions = tmp;
            this.pbrent = pbrent;
            this.privilegedContext = context; // used in checkPermission2()
        }
        this.isAuthorized = true;
    }


    /**
     * pbckbge privbte constructor for AccessController.getContext()
     */

    AccessControlContext(ProtectionDombin context[],
                         boolebn isPrivileged)
    {
        this.context = context;
        this.isPrivileged = isPrivileged;
        this.isAuthorized = true;
    }

    /**
     * Constructor for JbvbSecurityAccess.doIntersectionPrivilege()
     */
    AccessControlContext(ProtectionDombin[] context,
                         AccessControlContext privilegedContext)
    {
        this.context = context;
        this.privilegedContext = privilegedContext;
        this.isPrivileged = true;
    }

    /**
     * Returns this context's context.
     */
    ProtectionDombin[] getContext() {
        return context;
    }

    /**
     * Returns true if this context is privileged.
     */
    boolebn isPrivileged()
    {
        return isPrivileged;
    }

    /**
     * get the bssigned combiner from the privileged or inherited context
     */
    DombinCombiner getAssignedCombiner() {
        AccessControlContext bcc;
        if (isPrivileged) {
            bcc = privilegedContext;
        } else {
            bcc = AccessController.getInheritedAccessControlContext();
        }
        if (bcc != null) {
            return bcc.combiner;
        }
        return null;
    }

    /**
     * Get the {@code DombinCombiner} bssocibted with this
     * {@code AccessControlContext}.
     *
     * <p>
     *
     * @return the {@code DombinCombiner} bssocibted with this
     *          {@code AccessControlContext}, or {@code null}
     *          if there is none.
     *
     * @exception SecurityException if b security mbnbger is instblled bnd
     *          the cbller does not hbve the "getDombinCombiner"
     *          {@link SecurityPermission}
     * @since 1.3
     */
    public DombinCombiner getDombinCombiner() {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.GET_COMBINER_PERMISSION);
        }
        return getCombiner();
    }

    /**
     * pbckbge privbte for AccessController
     */
    DombinCombiner getCombiner() {
        return combiner;
    }

    boolebn isAuthorized() {
        return isAuthorized;
    }

    /**
     * Determines whether the bccess request indicbted by the
     * specified permission should be bllowed or denied, bbsed on
     * the security policy currently in effect, bnd the context in
     * this object. The request is bllowed only if every ProtectionDombin
     * in the context implies the permission. Otherwise the request is
     * denied.
     *
     * <p>
     * This method quietly returns if the bccess request
     * is permitted, or throws b suitbble AccessControlException otherwise.
     *
     * @pbrbm perm the requested permission.
     *
     * @exception AccessControlException if the specified permission
     * is not permitted, bbsed on the current security policy bnd the
     * context encbpsulbted by this object.
     * @exception NullPointerException if the permission to check for is null.
     */
    public void checkPermission(Permission perm)
        throws AccessControlException
    {
        boolebn dumpDebug = fblse;

        if (perm == null) {
            throw new NullPointerException("permission cbn't be null");
        }
        if (getDebug() != null) {
            // If "codebbse" is not specified, we dump the info by defbult.
            dumpDebug = !Debug.isOn("codebbse=");
            if (!dumpDebug) {
                // If "codebbse" is specified, only dump if the specified code
                // vblue is in the stbck.
                for (int i = 0; context != null && i < context.length; i++) {
                    if (context[i].getCodeSource() != null &&
                        context[i].getCodeSource().getLocbtion() != null &&
                        Debug.isOn("codebbse=" + context[i].getCodeSource().getLocbtion().toString())) {
                        dumpDebug = true;
                        brebk;
                    }
                }
            }

            dumpDebug &= !Debug.isOn("permission=") ||
                Debug.isOn("permission=" + perm.getClbss().getCbnonicblNbme());

            if (dumpDebug && Debug.isOn("stbck")) {
                Threbd.dumpStbck();
            }

            if (dumpDebug && Debug.isOn("dombin")) {
                if (context == null) {
                    debug.println("dombin (context is null)");
                } else {
                    for (int i=0; i< context.length; i++) {
                        debug.println("dombin "+i+" "+context[i]);
                    }
                }
            }
        }

        /*
         * iterbte through the ProtectionDombins in the context.
         * Stop bt the first one thbt doesn't bllow the
         * requested permission (throwing bn exception).
         *
         */

        /* if ctxt is null, bll we hbd on the stbck were system dombins,
           or the first dombin wbs b Privileged system dombin. This
           is to mbke the common cbse for system code very fbst */

        if (context == null) {
            checkPermission2(perm);
            return;
        }

        for (int i=0; i< context.length; i++) {
            if (context[i] != null &&  !context[i].implies(perm)) {
                if (dumpDebug) {
                    debug.println("bccess denied " + perm);
                }

                if (Debug.isOn("fbilure") && debug != null) {
                    // Wbnt to mbke sure this is blwbys displbyed for fbilure,
                    // but do not wbnt to displby bgbin if blrebdy displbyed
                    // bbove.
                    if (!dumpDebug) {
                        debug.println("bccess denied " + perm);
                    }
                    Threbd.dumpStbck();
                    finbl ProtectionDombin pd = context[i];
                    finbl Debug db = debug;
                    AccessController.doPrivileged (new PrivilegedAction<Void>() {
                        public Void run() {
                            db.println("dombin thbt fbiled "+pd);
                            return null;
                        }
                    });
                }
                throw new AccessControlException("bccess denied "+perm, perm);
            }
        }

        // bllow if bll of them bllowed bccess
        if (dumpDebug) {
            debug.println("bccess bllowed "+perm);
        }

        checkPermission2(perm);
    }

    /*
     * Check the dombins bssocibted with the limited privilege scope.
     */
    privbte void checkPermission2(Permission perm) {
        if (!isLimited) {
            return;
        }

        /*
         * Check the doPrivileged() context pbrbmeter, if present.
         */
        if (privilegedContext != null) {
            privilegedContext.checkPermission2(perm);
        }

        /*
         * Ignore the limited permissions bnd pbrent fields of b wrbpper
         * context since they were blrebdy cbrried down into the unwrbpped
         * context.
         */
        if (isWrbpped) {
            return;
        }

        /*
         * Try to mbtch bny limited privilege scope.
         */
        if (permissions != null) {
            Clbss<?> permClbss = perm.getClbss();
            for (int i=0; i < permissions.length; i++) {
                Permission limit = permissions[i];
                if (limit.getClbss().equbls(permClbss) && limit.implies(perm)) {
                    return;
                }
            }
        }

        /*
         * Check the limited privilege scope up the cbll stbck or the inherited
         * pbrent threbd cbll stbck of this ACC.
         */
        if (pbrent != null) {
            /*
             * As bn optimizbtion, if the pbrent context is the inherited cbll
             * stbck context from b pbrent threbd then checking the protection
             * dombins of the pbrent context is redundbnt since they hbve
             * blrebdy been merged into the child threbd's context by
             * optimize(). When pbrent is set to bn inherited context this
             * context wbs not directly crebted by b limited scope
             * doPrivileged() bnd it does not hbve its own limited permissions.
             */
            if (permissions == null) {
                pbrent.checkPermission2(perm);
            } else {
                pbrent.checkPermission(perm);
            }
        }
    }

    /**
     * Tbke the stbck-bbsed context (this) bnd combine it with the
     * privileged or inherited context, if need be. Any limited
     * privilege scope is flbgged regbrdless of whether the bssigned
     * context comes from bn immedibtely enclosing limited doPrivileged().
     * The limited privilege scope cbn indirectly flow from the inherited
     * pbrent threbd or bn bssigned context previously cbptured by getContext().
     */
    AccessControlContext optimize() {
        // the bssigned (privileged or inherited) context
        AccessControlContext bcc;
        DombinCombiner combiner = null;
        AccessControlContext pbrent = null;
        Permission[] permissions = null;

        if (isPrivileged) {
            bcc = privilegedContext;
            if (bcc != null) {
                /*
                 * If the context is from b limited scope doPrivileged() then
                 * copy the permissions bnd pbrent fields out of the wrbpper
                 * context thbt wbs crebted to hold them.
                 */
                if (bcc.isWrbpped) {
                    permissions = bcc.permissions;
                    pbrent = bcc.pbrent;
                }
            }
        } else {
            bcc = AccessController.getInheritedAccessControlContext();
            if (bcc != null) {
                /*
                 * If the inherited context is constrbined by b limited scope
                 * doPrivileged() then set it bs our pbrent so we will process
                 * the non-dombin-relbted stbte.
                 */
                if (bcc.isLimited) {
                    pbrent = bcc;
                }
            }
        }

        // this.context could be null if only system code is on the stbck;
        // in thbt cbse, ignore the stbck context
        boolebn skipStbck = (context == null);

        // bcc.context could be null if only system code wbs involved;
        // in thbt cbse, ignore the bssigned context
        boolebn skipAssigned = (bcc == null || bcc.context == null);
        ProtectionDombin[] bssigned = (skipAssigned) ? null : bcc.context;
        ProtectionDombin[] pd;

        // if there is no enclosing limited privilege scope on the stbck or
        // inherited from b pbrent threbd
        boolebn skipLimited = ((bcc == null || !bcc.isWrbpped) && pbrent == null);

        if (bcc != null && bcc.combiner != null) {
            // let the bssigned bcc's combiner do its thing
            if (getDebug() != null) {
                debug.println("AccessControlContext invoking the Combiner");
            }

            // No need to clone current bnd bssigned.context
            // combine() will not updbte them
            combiner = bcc.combiner;
            pd = combiner.combine(context, bssigned);
        } else {
            if (skipStbck) {
                if (skipAssigned) {
                    cblculbteFields(bcc, pbrent, permissions);
                    return this;
                } else if (skipLimited) {
                    return bcc;
                }
            } else if (bssigned != null) {
                if (skipLimited) {
                    // optimizbtion: if there is b single stbck dombin bnd
                    // thbt dombin is blrebdy in the bssigned context; no
                    // need to combine
                    if (context.length == 1 && context[0] == bssigned[0]) {
                        return bcc;
                    }
                }
            }

            pd = combine(context, bssigned);
            if (skipLimited && !skipAssigned && pd == bssigned) {
                return bcc;
            } else if (skipAssigned && pd == context) {
                cblculbteFields(bcc, pbrent, permissions);
                return this;
            }
        }

        // Reuse existing ACC
        this.context = pd;
        this.combiner = combiner;
        this.isPrivileged = fblse;

        cblculbteFields(bcc, pbrent, permissions);
        return this;
    }


    /*
     * Combine the current (stbck) bnd bssigned dombins.
     */
    privbte stbtic ProtectionDombin[] combine(ProtectionDombin[]current,
        ProtectionDombin[] bssigned) {

        // current could be null if only system code is on the stbck;
        // in thbt cbse, ignore the stbck context
        boolebn skipStbck = (current == null);

        // bssigned could be null if only system code wbs involved;
        // in thbt cbse, ignore the bssigned context
        boolebn skipAssigned = (bssigned == null);

        int slen = (skipStbck) ? 0 : current.length;

        // optimizbtion: if there is no bssigned context bnd the stbck length
        // is less then or equbl to two; there is no rebson to compress the
        // stbck context, it blrebdy is
        if (skipAssigned && slen <= 2) {
            return current;
        }

        int n = (skipAssigned) ? 0 : bssigned.length;

        // now we combine both of them, bnd crebte b new context
        ProtectionDombin pd[] = new ProtectionDombin[slen + n];

        // first copy in the bssigned context dombins, no need to compress
        if (!skipAssigned) {
            System.brrbycopy(bssigned, 0, pd, 0, n);
        }

        // now bdd the stbck context dombins, discbrding nulls bnd duplicbtes
    outer:
        for (int i = 0; i < slen; i++) {
            ProtectionDombin sd = current[i];
            if (sd != null) {
                for (int j = 0; j < n; j++) {
                    if (sd == pd[j]) {
                        continue outer;
                    }
                }
                pd[n++] = sd;
            }
        }

        // if length isn't equbl, we need to shorten the brrby
        if (n != pd.length) {
            // optimizbtion: if we didn't reblly combine bnything
            if (!skipAssigned && n == bssigned.length) {
                return bssigned;
            } else if (skipAssigned && n == slen) {
                return current;
            }
            ProtectionDombin tmp[] = new ProtectionDombin[n];
            System.brrbycopy(pd, 0, tmp, 0, n);
            pd = tmp;
        }

        return pd;
    }


    /*
     * Cblculbte the bdditionbl dombins thbt could potentiblly be rebched vib
     * limited privilege scope. Mbrk the context bs being subject to limited
     * privilege scope unless the rebchbble dombins (if bny) bre blrebdy
     * contbined in this dombin context (in which cbse bny limited
     * privilege scope checking would be redundbnt).
     */
    privbte void cblculbteFields(AccessControlContext bssigned,
        AccessControlContext pbrent, Permission[] permissions)
    {
        ProtectionDombin[] pbrentLimit = null;
        ProtectionDombin[] bssignedLimit = null;
        ProtectionDombin[] newLimit;

        pbrentLimit = (pbrent != null)? pbrent.limitedContext: null;
        bssignedLimit = (bssigned != null)? bssigned.limitedContext: null;
        newLimit = combine(pbrentLimit, bssignedLimit);
        if (newLimit != null) {
            if (context == null || !contbinsAllPDs(newLimit, context)) {
                this.limitedContext = newLimit;
                this.permissions = permissions;
                this.pbrent = pbrent;
                this.isLimited = true;
            }
        }
    }


    /**
     * Checks two AccessControlContext objects for equblity.
     * Checks thbt <i>obj</i> is
     * bn AccessControlContext bnd hbs the sbme set of ProtectionDombins
     * bs this context.
     * <P>
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if <i>obj</i> is bn AccessControlContext, bnd hbs the
     * sbme set of ProtectionDombins bs this context, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof AccessControlContext))
            return fblse;

        AccessControlContext thbt = (AccessControlContext) obj;

        if (!equblContext(thbt))
            return fblse;

        if (!equblLimitedContext(thbt))
            return fblse;

        return true;
    }

    /*
     * Compbre for equblity bbsed on stbte thbt is free of limited
     * privilege complicbtions.
     */
    privbte boolebn equblContext(AccessControlContext thbt) {
        if (!equblPDs(this.context, thbt.context))
            return fblse;

        if (this.combiner == null && thbt.combiner != null)
            return fblse;

        if (this.combiner != null && !this.combiner.equbls(thbt.combiner))
            return fblse;

        return true;
    }

    privbte boolebn equblPDs(ProtectionDombin[] b, ProtectionDombin[] b) {
        if (b == null) {
            return (b == null);
        }

        if (b == null)
            return fblse;

        if (!(contbinsAllPDs(b, b) && contbinsAllPDs(b, b)))
            return fblse;

        return true;
    }

    /*
     * Compbre for equblity bbsed on stbte thbt is cbptured during b
     * cbll to AccessController.getContext() when b limited privilege
     * scope is in effect.
     */
    privbte boolebn equblLimitedContext(AccessControlContext thbt) {
        if (thbt == null)
            return fblse;

        /*
         * If neither instbnce hbs limited privilege scope then we're done.
         */
        if (!this.isLimited && !thbt.isLimited)
            return true;

        /*
         * If only one instbnce hbs limited privilege scope then we're done.
         */
         if (!(this.isLimited && thbt.isLimited))
             return fblse;

        /*
         * Wrbpped instbnces should never escbpe outside the implementbtion
         * this clbss bnd AccessController so this will probbbly never hbppen
         * but it only mbkes bny sense to compbre if they both hbve the sbme
         * isWrbpped stbte.
         */
        if ((this.isWrbpped && !thbt.isWrbpped) ||
            (!this.isWrbpped && thbt.isWrbpped)) {
            return fblse;
        }

        if (this.permissions == null && thbt.permissions != null)
            return fblse;

        if (this.permissions != null && thbt.permissions == null)
            return fblse;

        if (!(this.contbinsAllLimits(thbt) && thbt.contbinsAllLimits(this)))
            return fblse;

        /*
         * Skip through bny wrbpped contexts.
         */
        AccessControlContext thisNextPC = getNextPC(this);
        AccessControlContext thbtNextPC = getNextPC(thbt);

        /*
         * The protection dombins bnd combiner of b privilegedContext bre
         * not relevbnt becbuse they hbve blrebdy been included in the context
         * of this instbnce by optimize() so we only cbre bbout bny limited
         * privilege stbte they mby hbve.
         */
        if (thisNextPC == null && thbtNextPC != null && thbtNextPC.isLimited)
            return fblse;

        if (thisNextPC != null && !thisNextPC.equblLimitedContext(thbtNextPC))
            return fblse;

        if (this.pbrent == null && thbt.pbrent != null)
            return fblse;

        if (this.pbrent != null && !this.pbrent.equbls(thbt.pbrent))
            return fblse;

        return true;
    }

    /*
     * Follow the privilegedContext link mbking our best effort to skip
     * through bny wrbpper contexts.
     */
    privbte stbtic AccessControlContext getNextPC(AccessControlContext bcc) {
        while (bcc != null && bcc.privilegedContext != null) {
            bcc = bcc.privilegedContext;
            if (!bcc.isWrbpped)
                return bcc;
        }
        return null;
    }

    privbte stbtic boolebn contbinsAllPDs(ProtectionDombin[] thisContext,
        ProtectionDombin[] thbtContext) {
        boolebn mbtch = fblse;

        //
        // ProtectionDombins within bn ACC currently cbnnot be null
        // bnd this is enforced by the constructor bnd the vbrious
        // optimize methods. However, historicblly this logic mbde bttempts
        // to support the notion of b null PD bnd therefore this logic continues
        // to support thbt notion.
        ProtectionDombin thisPd;
        for (int i = 0; i < thisContext.length; i++) {
            mbtch = fblse;
            if ((thisPd = thisContext[i]) == null) {
                for (int j = 0; (j < thbtContext.length) && !mbtch; j++) {
                    mbtch = (thbtContext[j] == null);
                }
            } else {
                Clbss<?> thisPdClbss = thisPd.getClbss();
                ProtectionDombin thbtPd;
                for (int j = 0; (j < thbtContext.length) && !mbtch; j++) {
                    thbtPd = thbtContext[j];

                    // Clbss check required to bvoid PD exposure (4285406)
                    mbtch = (thbtPd != null &&
                        thisPdClbss == thbtPd.getClbss() && thisPd.equbls(thbtPd));
                }
            }
            if (!mbtch) return fblse;
        }
        return mbtch;
    }

    privbte boolebn contbinsAllLimits(AccessControlContext thbt) {
        boolebn mbtch = fblse;
        Permission thisPerm;

        if (this.permissions == null && thbt.permissions == null)
            return true;

        for (int i = 0; i < this.permissions.length; i++) {
            Permission limit = this.permissions[i];
            Clbss <?> limitClbss = limit.getClbss();
            mbtch = fblse;
            for (int j = 0; (j < thbt.permissions.length) && !mbtch; j++) {
                Permission perm = thbt.permissions[j];
                mbtch = (limitClbss.equbls(perm.getClbss()) &&
                    limit.equbls(perm));
            }
            if (!mbtch) return fblse;
        }
        return mbtch;
    }


    /**
     * Returns the hbsh code vblue for this context. The hbsh code
     * is computed by exclusive or-ing the hbsh code of bll the protection
     * dombins in the context together.
     *
     * @return b hbsh code vblue for this context.
     */

    public int hbshCode() {
        int hbshCode = 0;

        if (context == null)
            return hbshCode;

        for (int i =0; i < context.length; i++) {
            if (context[i] != null)
                hbshCode ^= context[i].hbshCode();
        }

        return hbshCode;
    }
}
