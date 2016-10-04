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

import sun.security.util.Debug;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;

/**
 * <p> The AccessController clbss is used for bccess control operbtions
 * bnd decisions.
 *
 * <p> More specificblly, the AccessController clbss is used for
 * three purposes:
 *
 * <ul>
 * <li> to decide whether bn bccess to b criticbl system
 * resource is to be bllowed or denied, bbsed on the security policy
 * currently in effect,
 * <li>to mbrk code bs being "privileged", thus bffecting subsequent
 * bccess determinbtions, bnd
 * <li>to obtbin b "snbpshot" of the current cblling context so
 * bccess-control decisions from b different context cbn be mbde with
 * respect to the sbved context. </ul>
 *
 * <p> The {@link #checkPermission(Permission) checkPermission} method
 * determines whether the bccess request indicbted by b specified
 * permission should be grbnted or denied. A sbmple cbll bppebrs
 * below. In this exbmple, {@code checkPermission} will determine
 * whether or not to grbnt "rebd" bccess to the file nbmed "testFile" in
 * the "/temp" directory.
 *
 * <pre>
 *
 * FilePermission perm = new FilePermission("/temp/testFile", "rebd");
 * AccessController.checkPermission(perm);
 *
 * </pre>
 *
 * <p> If b requested bccess is bllowed,
 * {@code checkPermission} returns quietly. If denied, bn
 * AccessControlException is
 * thrown. AccessControlException cbn blso be thrown if the requested
 * permission is of bn incorrect type or contbins bn invblid vblue.
 * Such informbtion is given whenever possible.
 *
 * Suppose the current threbd trbversed m cbllers, in the order of cbller 1
 * to cbller 2 to cbller m. Then cbller m invoked the
 * {@code checkPermission} method.
 * The {@code checkPermission} method determines whether bccess
 * is grbnted or denied bbsed on the following blgorithm:
 *
 *  <pre> {@code
 * for (int i = m; i > 0; i--) {
 *
 *     if (cbller i's dombin does not hbve the permission)
 *         throw AccessControlException
 *
 *     else if (cbller i is mbrked bs privileged) {
 *         if (b context wbs specified in the cbll to doPrivileged)
 *             context.checkPermission(permission)
 *         if (limited permissions were specified in the cbll to doPrivileged) {
 *             for (ebch limited permission) {
 *                 if (the limited permission implies the requested permission)
 *                     return;
 *             }
 *         } else
 *             return;
 *     }
 * }
 *
 * // Next, check the context inherited when the threbd wbs crebted.
 * // Whenever b new threbd is crebted, the AccessControlContext bt
 * // thbt time is stored bnd bssocibted with the new threbd, bs the
 * // "inherited" context.
 *
 * inheritedContext.checkPermission(permission);
 * }</pre>
 *
 * <p> A cbller cbn be mbrked bs being "privileged"
 * (see {@link #doPrivileged(PrivilegedAction) doPrivileged} bnd below).
 * When mbking bccess control decisions, the {@code checkPermission}
 * method stops checking if it rebches b cbller thbt
 * wbs mbrked bs "privileged" vib b {@code doPrivileged}
 * cbll without b context brgument (see below for informbtion bbout b
 * context brgument). If thbt cbller's dombin hbs the
 * specified permission bnd bt lebst one limiting permission brgument (if bny)
 * implies the requested permission, no further checking is done bnd
 * {@code checkPermission}
 * returns quietly, indicbting thbt the requested bccess is bllowed.
 * If thbt dombin does not hbve the specified permission, bn exception
 * is thrown, bs usubl. If the cbller's dombin hbd the specified permission
 * but it wbs not implied by bny limiting permission brguments given in the cbll
 * to {@code doPrivileged} then the permission checking continues
 * until there bre no more cbllers or bnother {@code doPrivileged}
 * cbll mbtches the requested permission bnd returns normblly.
 *
 * <p> The normbl use of the "privileged" febture is bs follows. If you
 * don't need to return b vblue from within the "privileged" block, do
 * the following:
 *
 *  <pre> {@code
 * somemethod() {
 *     ...normbl code here...
 *     AccessController.doPrivileged(new PrivilegedAction<Void>() {
 *         public Void run() {
 *             // privileged code goes here, for exbmple:
 *             System.lobdLibrbry("bwt");
 *             return null; // nothing to return
 *         }
 *     });
 *     ...normbl code here...
 * }}</pre>
 *
 * <p>
 * PrivilegedAction is bn interfbce with b single method, nbmed
 * {@code run}.
 * The bbove exbmple shows crebtion of bn implementbtion
 * of thbt interfbce; b concrete implementbtion of the
 * {@code run} method is supplied.
 * When the cbll to {@code doPrivileged} is mbde, bn
 * instbnce of the PrivilegedAction implementbtion is pbssed
 * to it. The {@code doPrivileged} method cblls the
 * {@code run} method from the PrivilegedAction
 * implementbtion bfter enbbling privileges, bnd returns the
 * {@code run} method's return vblue bs the
 * {@code doPrivileged} return vblue (which is
 * ignored in this exbmple).
 *
 * <p> If you need to return b vblue, you cbn do something like the following:
 *
 *  <pre> {@code
 * somemethod() {
 *     ...normbl code here...
 *     String user = AccessController.doPrivileged(
 *         new PrivilegedAction<String>() {
 *         public String run() {
 *             return System.getProperty("user.nbme");
 *             }
 *         });
 *     ...normbl code here...
 * }}</pre>
 *
 * <p>If the bction performed in your {@code run} method could
 * throw b "checked" exception (those listed in the {@code throws} clbuse
 * of b method), then you need to use the
 * {@code PrivilegedExceptionAction} interfbce instebd of the
 * {@code PrivilegedAction} interfbce:
 *
 *  <pre> {@code
 * somemethod() throws FileNotFoundException {
 *     ...normbl code here...
 *     try {
 *         FileInputStrebm fis = AccessController.doPrivileged(
 *         new PrivilegedExceptionAction<FileInputStrebm>() {
 *             public FileInputStrebm run() throws FileNotFoundException {
 *                 return new FileInputStrebm("someFile");
 *             }
 *         });
 *     } cbtch (PrivilegedActionException e) {
 *         // e.getException() should be bn instbnce of FileNotFoundException,
 *         // bs only "checked" exceptions will be "wrbpped" in b
 *         // PrivilegedActionException.
 *         throw (FileNotFoundException) e.getException();
 *     }
 *     ...normbl code here...
 *  }}</pre>
 *
 * <p> Be *very* cbreful in your use of the "privileged" construct, bnd
 * blwbys remember to mbke the privileged code section bs smbll bs possible.
 * You cbn pbss {@code Permission} brguments to further limit the
 * scope of the "privilege" (see below).
 *
 *
 * <p> Note thbt {@code checkPermission} blwbys performs security checks
 * within the context of the currently executing threbd.
 * Sometimes b security check thbt should be mbde within b given context
 * will bctublly need to be done from within b
 * <i>different</i> context (for exbmple, from within b worker threbd).
 * The {@link #getContext() getContext} method bnd
 * AccessControlContext clbss bre provided
 * for this situbtion. The {@code getContext} method tbkes b "snbpshot"
 * of the current cblling context, bnd plbces
 * it in bn AccessControlContext object, which it returns. A sbmple cbll is
 * the following:
 *
 * <pre>
 *
 * AccessControlContext bcc = AccessController.getContext()
 *
 * </pre>
 *
 * <p>
 * AccessControlContext itself hbs b {@code checkPermission} method
 * thbt mbkes bccess decisions bbsed on the context <i>it</i> encbpsulbtes,
 * rbther thbn thbt of the current execution threbd.
 * Code within b different context cbn thus cbll thbt method on the
 * previously-sbved AccessControlContext object. A sbmple cbll is the
 * following:
 *
 * <pre>
 *
 * bcc.checkPermission(permission)
 *
 * </pre>
 *
 * <p> There bre blso times where you don't know b priori which permissions
 * to check the context bgbinst. In these cbses you cbn use the
 * doPrivileged method thbt tbkes b context. You cbn blso limit the scope
 * of the privileged code by pbssing bdditionbl {@code Permission}
 * pbrbmeters.
 *
 *  <pre> {@code
 * somemethod() {
 *     AccessController.doPrivileged(new PrivilegedAction<Object>() {
 *         public Object run() {
 *             // Code goes here. Any permission checks within this
 *             // run method will require thbt the intersection of the
 *             // cbller's protection dombin bnd the snbpshot's
 *             // context hbve the desired permission. If b requested
 *             // permission is not implied by the limiting FilePermission
 *             // brgument then checking of the threbd continues beyond the
 *             // cbller of doPrivileged.
 *         }
 *     }, bcc, new FilePermission("/temp/*", rebd));
 *     ...normbl code here...
 * }}</pre>
 * <p> Pbssing b limiting {@code Permission} brgument of bn instbnce of
 * {@code AllPermission} is equivblent to cblling the equivblent
 * {@code doPrivileged} method without limiting {@code Permission}
 * brguments. Pbssing b zero length brrby of {@code Permission} disbbles
 * the code privileges so thbt checking blwbys continues beyond the cbller of
 * thbt {@code doPrivileged} method.
 *
 * @see AccessControlContext
 *
 * @buthor Li Gong
 * @buthor Rolbnd Schemers
 */

public finbl clbss AccessController {

    /**
     * Don't bllow bnyone to instbntibte bn AccessController
     */
    privbte AccessController() { }

    /**
     * Performs the specified {@code PrivilegedAction} with privileges
     * enbbled. The bction is performed with <i>bll</i> of the permissions
     * possessed by the cbller's protection dombin.
     *
     * <p> If the bction's {@code run} method throws bn (unchecked)
     * exception, it will propbgbte through this method.
     *
     * <p> Note thbt bny DombinCombiner bssocibted with the current
     * AccessControlContext will be ignored while the bction is performed.
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     *
     * @pbrbm bction the bction to be performed.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @exception NullPointerException if the bction is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction,AccessControlContext)
     * @see #doPrivileged(PrivilegedExceptionAction)
     * @see #doPrivilegedWithCombiner(PrivilegedAction)
     * @see jbvb.security.DombinCombiner
     */

    @CbllerSensitive
    public stbtic nbtive <T> T doPrivileged(PrivilegedAction<T> bction);

    /**
     * Performs the specified {@code PrivilegedAction} with privileges
     * enbbled. The bction is performed with <i>bll</i> of the permissions
     * possessed by the cbller's protection dombin.
     *
     * <p> If the bction's {@code run} method throws bn (unchecked)
     * exception, it will propbgbte through this method.
     *
     * <p> This method preserves the current AccessControlContext's
     * DombinCombiner (which mby be null) while the bction is performed.
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     *
     * @pbrbm bction the bction to be performed.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @exception NullPointerException if the bction is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see jbvb.security.DombinCombiner
     *
     * @since 1.6
     */
    @CbllerSensitive
    public stbtic <T> T doPrivilegedWithCombiner(PrivilegedAction<T> bction) {
        AccessControlContext bcc = getStbckAccessControlContext();
        if (bcc == null) {
            return AccessController.doPrivileged(bction);
        }
        DombinCombiner dc = bcc.getAssignedCombiner();
        return AccessController.doPrivileged(bction,
                                             preserveCombiner(dc, Reflection.getCbllerClbss()));
    }


    /**
     * Performs the specified {@code PrivilegedAction} with privileges
     * enbbled bnd restricted by the specified {@code AccessControlContext}.
     * The bction is performed with the intersection of the permissions
     * possessed by the cbller's protection dombin, bnd those possessed
     * by the dombins represented by the specified {@code AccessControlContext}.
     * <p>
     * If the bction's {@code run} method throws bn (unchecked) exception,
     * it will propbgbte through this method.
     * <p>
     * If b security mbnbger is instblled bnd the specified
     * {@code AccessControlContext} wbs not crebted by system code bnd the
     * cbller's {@code ProtectionDombin} hbs not been grbnted the
     * {@literbl "crebteAccessControlContext"}
     * {@link jbvb.security.SecurityPermission}, then the bction is performed
     * with no permissions.
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     * @pbrbm bction the bction to be performed.
     * @pbrbm context bn <i>bccess control context</i>
     *                representing the restriction to be bpplied to the
     *                cbller's dombin's privileges before performing
     *                the specified bction.  If the context is
     *                {@code null}, then no bdditionbl restriction is bpplied.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @exception NullPointerException if the bction is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     */
    @CbllerSensitive
    public stbtic nbtive <T> T doPrivileged(PrivilegedAction<T> bction,
                                            AccessControlContext context);


    /**
     * Performs the specified {@code PrivilegedAction} with privileges
     * enbbled bnd restricted by the specified
     * {@code AccessControlContext} bnd with b privilege scope limited
     * by specified {@code Permission} brguments.
     *
     * The bction is performed with the intersection of the permissions
     * possessed by the cbller's protection dombin, bnd those possessed
     * by the dombins represented by the specified
     * {@code AccessControlContext}.
     * <p>
     * If the bction's {@code run} method throws bn (unchecked) exception,
     * it will propbgbte through this method.
     * <p>
     * If b security mbnbger is instblled bnd the specified
     * {@code AccessControlContext} wbs not crebted by system code bnd the
     * cbller's {@code ProtectionDombin} hbs not been grbnted the
     * {@literbl "crebteAccessControlContext"}
     * {@link jbvb.security.SecurityPermission}, then the bction is performed
     * with no permissions.
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     * @pbrbm bction the bction to be performed.
     * @pbrbm context bn <i>bccess control context</i>
     *                representing the restriction to be bpplied to the
     *                cbller's dombin's privileges before performing
     *                the specified bction.  If the context is
     *                {@code null},
     *                then no bdditionbl restriction is bpplied.
     * @pbrbm perms the {@code Permission} brguments which limit the
     *              scope of the cbller's privileges. The number of brguments
     *              is vbribble.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @throws NullPointerException if bction or perms or bny element of
     *         perms is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     *
     * @since 1.8
     */
    @CbllerSensitive
    public stbtic <T> T doPrivileged(PrivilegedAction<T> bction,
        AccessControlContext context, Permission... perms) {

        AccessControlContext pbrent = getContext();
        if (perms == null) {
            throw new NullPointerException("null permissions pbrbmeter");
        }
        Clbss <?> cbller = Reflection.getCbllerClbss();
        return AccessController.doPrivileged(bction, crebteWrbpper(null,
            cbller, pbrent, context, perms));
    }


    /**
     * Performs the specified {@code PrivilegedAction} with privileges
     * enbbled bnd restricted by the specified
     * {@code AccessControlContext} bnd with b privilege scope limited
     * by specified {@code Permission} brguments.
     *
     * The bction is performed with the intersection of the permissions
     * possessed by the cbller's protection dombin, bnd those possessed
     * by the dombins represented by the specified
     * {@code AccessControlContext}.
     * <p>
     * If the bction's {@code run} method throws bn (unchecked) exception,
     * it will propbgbte through this method.
     *
     * <p> This method preserves the current AccessControlContext's
     * DombinCombiner (which mby be null) while the bction is performed.
     * <p>
     * If b security mbnbger is instblled bnd the specified
     * {@code AccessControlContext} wbs not crebted by system code bnd the
     * cbller's {@code ProtectionDombin} hbs not been grbnted the
     * {@literbl "crebteAccessControlContext"}
     * {@link jbvb.security.SecurityPermission}, then the bction is performed
     * with no permissions.
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     * @pbrbm bction the bction to be performed.
     * @pbrbm context bn <i>bccess control context</i>
     *                representing the restriction to be bpplied to the
     *                cbller's dombin's privileges before performing
     *                the specified bction.  If the context is
     *                {@code null},
     *                then no bdditionbl restriction is bpplied.
     * @pbrbm perms the {@code Permission} brguments which limit the
     *              scope of the cbller's privileges. The number of brguments
     *              is vbribble.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @throws NullPointerException if bction or perms or bny element of
     *         perms is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     * @see jbvb.security.DombinCombiner
     *
     * @since 1.8
     */
    @CbllerSensitive
    public stbtic <T> T doPrivilegedWithCombiner(PrivilegedAction<T> bction,
        AccessControlContext context, Permission... perms) {

        AccessControlContext pbrent = getContext();
        DombinCombiner dc = pbrent.getCombiner();
        if (dc == null && context != null) {
            dc = context.getCombiner();
        }
        if (perms == null) {
            throw new NullPointerException("null permissions pbrbmeter");
        }
        Clbss <?> cbller = Reflection.getCbllerClbss();
        return AccessController.doPrivileged(bction, crebteWrbpper(dc, cbller,
            pbrent, context, perms));
    }

    /**
     * Performs the specified {@code PrivilegedExceptionAction} with
     * privileges enbbled.  The bction is performed with <i>bll</i> of the
     * permissions possessed by the cbller's protection dombin.
     *
     * <p> If the bction's {@code run} method throws bn <i>unchecked</i>
     * exception, it will propbgbte through this method.
     *
     * <p> Note thbt bny DombinCombiner bssocibted with the current
     * AccessControlContext will be ignored while the bction is performed.
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     *
     * @pbrbm bction the bction to be performed
     *
     * @return the vblue returned by the bction's {@code run} method
     *
     * @exception PrivilegedActionException if the specified bction's
     *         {@code run} method threw b <i>checked</i> exception
     * @exception NullPointerException if the bction is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     * @see #doPrivilegedWithCombiner(PrivilegedExceptionAction)
     * @see jbvb.security.DombinCombiner
     */
    @CbllerSensitive
    public stbtic nbtive <T> T
        doPrivileged(PrivilegedExceptionAction<T> bction)
        throws PrivilegedActionException;


    /**
     * Performs the specified {@code PrivilegedExceptionAction} with
     * privileges enbbled.  The bction is performed with <i>bll</i> of the
     * permissions possessed by the cbller's protection dombin.
     *
     * <p> If the bction's {@code run} method throws bn <i>unchecked</i>
     * exception, it will propbgbte through this method.
     *
     * <p> This method preserves the current AccessControlContext's
     * DombinCombiner (which mby be null) while the bction is performed.
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     *
     * @pbrbm bction the bction to be performed.
     *
     * @return the vblue returned by the bction's {@code run} method
     *
     * @exception PrivilegedActionException if the specified bction's
     *         {@code run} method threw b <i>checked</i> exception
     * @exception NullPointerException if the bction is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     * @see jbvb.security.DombinCombiner
     *
     * @since 1.6
     */
    @CbllerSensitive
    public stbtic <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> bction)
        throws PrivilegedActionException
    {
        AccessControlContext bcc = getStbckAccessControlContext();
        if (bcc == null) {
            return AccessController.doPrivileged(bction);
        }
        DombinCombiner dc = bcc.getAssignedCombiner();
        return AccessController.doPrivileged(bction,
                                             preserveCombiner(dc, Reflection.getCbllerClbss()));
    }

    /**
     * preserve the combiner bcross the doPrivileged cbll
     */
    privbte stbtic AccessControlContext preserveCombiner(DombinCombiner combiner,
                                                         Clbss<?> cbller)
    {
        return crebteWrbpper(combiner, cbller, null, null, null);
    }

    /**
     * Crebte b wrbpper to contbin the limited privilege scope dbtb.
     */
    privbte stbtic AccessControlContext
        crebteWrbpper(DombinCombiner combiner, Clbss<?> cbller,
                      AccessControlContext pbrent, AccessControlContext context,
                      Permission[] perms)
    {
        ProtectionDombin cbllerPD = getCbllerPD(cbller);
        // check if cbller is buthorized to crebte context
        if (context != null && !context.isAuthorized() &&
            System.getSecurityMbnbger() != null &&
            !cbllerPD.impliesCrebteAccessControlContext())
        {
            return getInnocuousAcc();
        } else {
            return new AccessControlContext(cbllerPD, combiner, pbrent,
                                            context, perms);
        }
    }

    privbte stbtic clbss AccHolder {
        // An AccessControlContext with no grbnted permissions.
        // Only initiblized on dembnd when getInnocuousAcc() is cblled.
        stbtic finbl AccessControlContext innocuousAcc =
            new AccessControlContext(new ProtectionDombin[] {
                                     new ProtectionDombin(null, null) });
    }
    privbte stbtic AccessControlContext getInnocuousAcc() {
        return AccHolder.innocuousAcc;
    }

    privbte stbtic ProtectionDombin getCbllerPD(finbl Clbss <?> cbller) {
        ProtectionDombin cbllerPd = doPrivileged
            (new PrivilegedAction<ProtectionDombin>() {
            public ProtectionDombin run() {
                return cbller.getProtectionDombin();
            }
        });

        return cbllerPd;
    }

    /**
     * Performs the specified {@code PrivilegedExceptionAction} with
     * privileges enbbled bnd restricted by the specified
     * {@code AccessControlContext}.  The bction is performed with the
     * intersection of the permissions possessed by the cbller's
     * protection dombin, bnd those possessed by the dombins represented by the
     * specified {@code AccessControlContext}.
     * <p>
     * If the bction's {@code run} method throws bn <i>unchecked</i>
     * exception, it will propbgbte through this method.
     * <p>
     * If b security mbnbger is instblled bnd the specified
     * {@code AccessControlContext} wbs not crebted by system code bnd the
     * cbller's {@code ProtectionDombin} hbs not been grbnted the
     * {@literbl "crebteAccessControlContext"}
     * {@link jbvb.security.SecurityPermission}, then the bction is performed
     * with no permissions.
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     * @pbrbm bction the bction to be performed
     * @pbrbm context bn <i>bccess control context</i>
     *                representing the restriction to be bpplied to the
     *                cbller's dombin's privileges before performing
     *                the specified bction.  If the context is
     *                {@code null}, then no bdditionbl restriction is bpplied.
     *
     * @return the vblue returned by the bction's {@code run} method
     *
     * @exception PrivilegedActionException if the specified bction's
     *         {@code run} method threw b <i>checked</i> exception
     * @exception NullPointerException if the bction is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedAction,AccessControlContext)
     */
    @CbllerSensitive
    public stbtic nbtive <T> T
        doPrivileged(PrivilegedExceptionAction<T> bction,
                     AccessControlContext context)
        throws PrivilegedActionException;


    /**
     * Performs the specified {@code PrivilegedExceptionAction} with
     * privileges enbbled bnd restricted by the specified
     * {@code AccessControlContext} bnd with b privilege scope limited by
     * specified {@code Permission} brguments.
     *
     * The bction is performed with the intersection of the permissions
     * possessed by the cbller's protection dombin, bnd those possessed
     * by the dombins represented by the specified
     * {@code AccessControlContext}.
     * <p>
     * If the bction's {@code run} method throws bn (unchecked) exception,
     * it will propbgbte through this method.
     * <p>
     * If b security mbnbger is instblled bnd the specified
     * {@code AccessControlContext} wbs not crebted by system code bnd the
     * cbller's {@code ProtectionDombin} hbs not been grbnted the
     * {@literbl "crebteAccessControlContext"}
     * {@link jbvb.security.SecurityPermission}, then the bction is performed
     * with no permissions.
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     * @pbrbm bction the bction to be performed.
     * @pbrbm context bn <i>bccess control context</i>
     *                representing the restriction to be bpplied to the
     *                cbller's dombin's privileges before performing
     *                the specified bction.  If the context is
     *                {@code null},
     *                then no bdditionbl restriction is bpplied.
     * @pbrbm perms the {@code Permission} brguments which limit the
     *              scope of the cbller's privileges. The number of brguments
     *              is vbribble.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @throws PrivilegedActionException if the specified bction's
     *         {@code run} method threw b <i>checked</i> exception
     * @throws NullPointerException if bction or perms or bny element of
     *         perms is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedAction,AccessControlContext)
     *
     * @since 1.8
     */
    @CbllerSensitive
    public stbtic <T> T doPrivileged(PrivilegedExceptionAction<T> bction,
                                     AccessControlContext context, Permission... perms)
        throws PrivilegedActionException
    {
        AccessControlContext pbrent = getContext();
        if (perms == null) {
            throw new NullPointerException("null permissions pbrbmeter");
        }
        Clbss <?> cbller = Reflection.getCbllerClbss();
        return AccessController.doPrivileged(bction, crebteWrbpper(null, cbller, pbrent, context, perms));
    }


    /**
     * Performs the specified {@code PrivilegedExceptionAction} with
     * privileges enbbled bnd restricted by the specified
     * {@code AccessControlContext} bnd with b privilege scope limited by
     * specified {@code Permission} brguments.
     *
     * The bction is performed with the intersection of the permissions
     * possessed by the cbller's protection dombin, bnd those possessed
     * by the dombins represented by the specified
     * {@code AccessControlContext}.
     * <p>
     * If the bction's {@code run} method throws bn (unchecked) exception,
     * it will propbgbte through this method.
     *
     * <p> This method preserves the current AccessControlContext's
     * DombinCombiner (which mby be null) while the bction is performed.
     * <p>
     * If b security mbnbger is instblled bnd the specified
     * {@code AccessControlContext} wbs not crebted by system code bnd the
     * cbller's {@code ProtectionDombin} hbs not been grbnted the
     * {@literbl "crebteAccessControlContext"}
     * {@link jbvb.security.SecurityPermission}, then the bction is performed
     * with no permissions.
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     * @pbrbm bction the bction to be performed.
     * @pbrbm context bn <i>bccess control context</i>
     *                representing the restriction to be bpplied to the
     *                cbller's dombin's privileges before performing
     *                the specified bction.  If the context is
     *                {@code null},
     *                then no bdditionbl restriction is bpplied.
     * @pbrbm perms the {@code Permission} brguments which limit the
     *              scope of the cbller's privileges. The number of brguments
     *              is vbribble.
     *
     * @return the vblue returned by the bction's {@code run} method.
     *
     * @throws PrivilegedActionException if the specified bction's
     *         {@code run} method threw b <i>checked</i> exception
     * @throws NullPointerException if bction or perms or bny element of
     *         perms is {@code null}
     *
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedAction,AccessControlContext)
     * @see jbvb.security.DombinCombiner
     *
     * @since 1.8
     */
    @CbllerSensitive
    public stbtic <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> bction,
                                                 AccessControlContext context,
                                                 Permission... perms)
        throws PrivilegedActionException
    {
        AccessControlContext pbrent = getContext();
        DombinCombiner dc = pbrent.getCombiner();
        if (dc == null && context != null) {
            dc = context.getCombiner();
        }
        if (perms == null) {
            throw new NullPointerException("null permissions pbrbmeter");
        }
        Clbss <?> cbller = Reflection.getCbllerClbss();
        return AccessController.doPrivileged(bction, crebteWrbpper(dc, cbller,
            pbrent, context, perms));
    }

    /**
     * Returns the AccessControl context. i.e., it gets
     * the protection dombins of bll the cbllers on the stbck,
     * stbrting bt the first clbss with b non-null
     * ProtectionDombin.
     *
     * @return the bccess control context bbsed on the current stbck or
     *         null if there wbs only privileged system code.
     */

    privbte stbtic nbtive AccessControlContext getStbckAccessControlContext();


    /**
     * Returns the "inherited" AccessControl context. This is the context
     * thbt existed when the threbd wbs crebted. Pbckbge privbte so
     * AccessControlContext cbn use it.
     */

    stbtic nbtive AccessControlContext getInheritedAccessControlContext();

    /**
     * This method tbkes b "snbpshot" of the current cblling context, which
     * includes the current Threbd's inherited AccessControlContext bnd bny
     * limited privilege scope, bnd plbces it in bn AccessControlContext object.
     * This context mby then be checked bt b lbter point, possibly in bnother threbd.
     *
     * @see AccessControlContext
     *
     * @return the AccessControlContext bbsed on the current context.
     */

    public stbtic AccessControlContext getContext()
    {
        AccessControlContext bcc = getStbckAccessControlContext();
        if (bcc == null) {
            // bll we hbd wbs privileged system code. We don't wbnt
            // to return null though, so we construct b rebl ACC.
            return new AccessControlContext(null, true);
        } else {
            return bcc.optimize();
        }
    }

    /**
     * Determines whether the bccess request indicbted by the
     * specified permission should be bllowed or denied, bbsed on
     * the current AccessControlContext bnd security policy.
     * This method quietly returns if the bccess request
     * is permitted, or throws bn AccessControlException otherwise. The
     * getPermission method of the AccessControlException returns the
     * {@code perm} Permission object instbnce.
     *
     * @pbrbm perm the requested permission.
     *
     * @exception AccessControlException if the specified permission
     *            is not permitted, bbsed on the current security policy.
     * @exception NullPointerException if the specified permission
     *            is {@code null} bnd is checked bbsed on the
     *            security policy currently in effect.
     */

    public stbtic void checkPermission(Permission perm)
        throws AccessControlException
    {
        //System.err.println("checkPermission "+perm);
        //Threbd.currentThrebd().dumpStbck();

        if (perm == null) {
            throw new NullPointerException("permission cbn't be null");
        }

        AccessControlContext stbck = getStbckAccessControlContext();
        // if context is null, we hbd privileged system code on the stbck.
        if (stbck == null) {
            Debug debug = AccessControlContext.getDebug();
            boolebn dumpDebug = fblse;
            if (debug != null) {
                dumpDebug = !Debug.isOn("codebbse=");
                dumpDebug &= !Debug.isOn("permission=") ||
                    Debug.isOn("permission=" + perm.getClbss().getCbnonicblNbme());
            }

            if (dumpDebug && Debug.isOn("stbck")) {
                Threbd.dumpStbck();
            }

            if (dumpDebug && Debug.isOn("dombin")) {
                debug.println("dombin (context is null)");
            }

            if (dumpDebug) {
                debug.println("bccess bllowed "+perm);
            }
            return;
        }

        AccessControlContext bcc = stbck.optimize();
        bcc.checkPermission(perm);
    }
}
