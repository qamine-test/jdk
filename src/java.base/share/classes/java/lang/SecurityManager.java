/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.security.*;
import jbvb.io.FileDescriptor;
import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.util.PropertyPermission;
import jbvb.lbng.RuntimePermission;
import jbvb.net.SocketPermission;
import jbvb.net.NetPermission;
import jbvb.util.Hbshtbble;
import jbvb.net.InetAddress;
import jbvb.lbng.reflect.*;
import jbvb.net.URL;

import sun.reflect.CbllerSensitive;
import sun.security.util.SecurityConstbnts;

/**
 * The security mbnbger is b clbss thbt bllows
 * bpplicbtions to implement b security policy. It bllows bn
 * bpplicbtion to determine, before performing b possibly unsbfe or
 * sensitive operbtion, whbt the operbtion is bnd whether
 * it is being bttempted in b security context thbt bllows the
 * operbtion to be performed. The
 * bpplicbtion cbn bllow or disbllow the operbtion.
 * <p>
 * The <code>SecurityMbnbger</code> clbss contbins mbny methods with
 * nbmes thbt begin with the word <code>check</code>. These methods
 * bre cblled by vbrious methods in the Jbvb librbries before those
 * methods perform certbin potentiblly sensitive operbtions. The
 * invocbtion of such b <code>check</code> method typicblly looks like this:
 * <blockquote><pre>
 *     SecurityMbnbger security = System.getSecurityMbnbger();
 *     if (security != null) {
 *         security.check<i>XXX</i>(brgument, &nbsp;.&nbsp;.&nbsp;.&nbsp;);
 *     }
 * </pre></blockquote>
 * <p>
 * The security mbnbger is thereby given bn opportunity to prevent
 * completion of the operbtion by throwing bn exception. A security
 * mbnbger routine simply returns if the operbtion is permitted, but
 * throws b <code>SecurityException</code> if the operbtion is not
 * permitted.
 * <p>
 * The current security mbnbger is set by the
 * <code>setSecurityMbnbger</code> method in clbss
 * <code>System</code>. The current security mbnbger is obtbined
 * by the <code>getSecurityMbnbger</code> method.
 * <p>
 * The specibl method
 * {@link SecurityMbnbger#checkPermission(jbvb.security.Permission)}
 * determines whether bn bccess request indicbted by b specified
 * permission should be grbnted or denied. The
 * defbult implementbtion cblls
 *
 * <pre>
 *   AccessController.checkPermission(perm);
 * </pre>
 *
 * <p>
 * If b requested bccess is bllowed,
 * <code>checkPermission</code> returns quietly. If denied, b
 * <code>SecurityException</code> is thrown.
 * <p>
 * As of Jbvb 2 SDK v1.2, the defbult implementbtion of ebch of the other
 * <code>check</code> methods in <code>SecurityMbnbger</code> is to
 * cbll the <code>SecurityMbnbger checkPermission</code> method
 * to determine if the cblling threbd hbs permission to perform the requested
 * operbtion.
 * <p>
 * Note thbt the <code>checkPermission</code> method with
 * just b single permission brgument blwbys performs security checks
 * within the context of the currently executing threbd.
 * Sometimes b security check thbt should be mbde within b given context
 * will bctublly need to be done from within b
 * <i>different</i> context (for exbmple, from within b worker threbd).
 * The {@link SecurityMbnbger#getSecurityContext getSecurityContext} method
 * bnd the {@link SecurityMbnbger#checkPermission(jbvb.security.Permission,
 * jbvb.lbng.Object) checkPermission}
 * method thbt includes b context brgument bre provided
 * for this situbtion. The
 * <code>getSecurityContext</code> method returns b "snbpshot"
 * of the current cblling context. (The defbult implementbtion
 * returns bn AccessControlContext object.) A sbmple cbll is
 * the following:
 *
 * <pre>
 *   Object context = null;
 *   SecurityMbnbger sm = System.getSecurityMbnbger();
 *   if (sm != null) context = sm.getSecurityContext();
 * </pre>
 *
 * <p>
 * The <code>checkPermission</code> method
 * thbt tbkes b context object in bddition to b permission
 * mbkes bccess decisions bbsed on thbt context,
 * rbther thbn on thbt of the current execution threbd.
 * Code within b different context cbn thus cbll thbt method,
 * pbssing the permission bnd the
 * previously-sbved context object. A sbmple cbll, using the
 * SecurityMbnbger <code>sm</code> obtbined bs in the previous exbmple,
 * is the following:
 *
 * <pre>
 *   if (sm != null) sm.checkPermission(permission, context);
 * </pre>
 *
 * <p>Permissions fbll into these cbtegories: File, Socket, Net,
 * Security, Runtime, Property, AWT, Reflect, bnd Seriblizbble.
 * The clbsses mbnbging these vbrious
 * permission cbtegories bre <code>jbvb.io.FilePermission</code>,
 * <code>jbvb.net.SocketPermission</code>,
 * <code>jbvb.net.NetPermission</code>,
 * <code>jbvb.security.SecurityPermission</code>,
 * <code>jbvb.lbng.RuntimePermission</code>,
 * <code>jbvb.util.PropertyPermission</code>,
 * <code>jbvb.bwt.AWTPermission</code>,
 * <code>jbvb.lbng.reflect.ReflectPermission</code>, bnd
 * <code>jbvb.io.SeriblizbblePermission</code>.
 *
 * <p>All but the first two (FilePermission bnd SocketPermission) bre
 * subclbsses of <code>jbvb.security.BbsicPermission</code>, which itself
 * is bn bbstrbct subclbss of the
 * top-level clbss for permissions, which is
 * <code>jbvb.security.Permission</code>. BbsicPermission defines the
 * functionblity needed for bll permissions thbt contbin b nbme
 * thbt follows the hierbrchicbl property nbming convention
 * (for exbmple, "exitVM", "setFbctory", "queuePrintJob", etc).
 * An bsterisk
 * mby bppebr bt the end of the nbme, following b ".", or by itself, to
 * signify b wildcbrd mbtch. For exbmple: "b.*" or "*" is vblid,
 * "*b" or "b*b" is not vblid.
 *
 * <p>FilePermission bnd SocketPermission bre subclbsses of the
 * top-level clbss for permissions
 * (<code>jbvb.security.Permission</code>). Clbsses like these
 * thbt hbve b more complicbted nbme syntbx thbn thbt used by
 * BbsicPermission subclbss directly from Permission rbther thbn from
 * BbsicPermission. For exbmple,
 * for b <code>jbvb.io.FilePermission</code> object, the permission nbme is
 * the pbth nbme of b file (or directory).
 *
 * <p>Some of the permission clbsses hbve bn "bctions" list thbt tells
 * the bctions thbt bre permitted for the object.  For exbmple,
 * for b <code>jbvb.io.FilePermission</code> object, the bctions list
 * (such bs "rebd, write") specifies which bctions bre grbnted for the
 * specified file (or for files in the specified directory).
 *
 * <p>Other permission clbsses bre for "nbmed" permissions -
 * ones thbt contbin b nbme but no bctions list; you either hbve the
 * nbmed permission or you don't.
 *
 * <p>Note: There is blso b <code>jbvb.security.AllPermission</code>
 * permission thbt implies bll permissions. It exists to simplify the work
 * of system bdministrbtors who might need to perform multiple
 * tbsks thbt require bll (or numerous) permissions.
 * <p>
 * See <b href ="../../../technotes/guides/security/permissions.html">
 * Permissions in the JDK</b> for permission-relbted informbtion.
 * This document includes, for exbmple, b tbble listing the vbrious SecurityMbnbger
 * <code>check</code> methods bnd the permission(s) the defbult
 * implementbtion of ebch such method requires.
 * It blso contbins b tbble of bll the version 1.2 methods
 * thbt require permissions, bnd for ebch such method tells
 * which permission it requires.
 * <p>
 * For more informbtion bbout <code>SecurityMbnbger</code> chbnges mbde in
 * the JDK bnd bdvice regbrding porting of 1.1-style security mbnbgers,
 * see the <b href="../../../technotes/guides/security/index.html">security documentbtion</b>.
 *
 * @buthor  Arthur vbn Hoff
 * @buthor  Rolbnd Schemers
 *
 * @see     jbvb.lbng.ClbssLobder
 * @see     jbvb.lbng.SecurityException
 * @see     jbvb.lbng.System#getSecurityMbnbger() getSecurityMbnbger
 * @see     jbvb.lbng.System#setSecurityMbnbger(jbvb.lbng.SecurityMbnbger)
 *  setSecurityMbnbger
 * @see     jbvb.security.AccessController AccessController
 * @see     jbvb.security.AccessControlContext AccessControlContext
 * @see     jbvb.security.AccessControlException AccessControlException
 * @see     jbvb.security.Permission
 * @see     jbvb.security.BbsicPermission
 * @see     jbvb.io.FilePermission
 * @see     jbvb.net.SocketPermission
 * @see     jbvb.util.PropertyPermission
 * @see     jbvb.lbng.RuntimePermission
 * @see     jbvb.bwt.AWTPermission
 * @see     jbvb.security.Policy Policy
 * @see     jbvb.security.SecurityPermission SecurityPermission
 * @see     jbvb.security.ProtectionDombin
 *
 * @since   1.0
 */
public
clbss SecurityMbnbger {

    /**
     * This field is <code>true</code> if there is b security check in
     * progress; <code>fblse</code> otherwise.
     *
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     */
    @Deprecbted
    protected boolebn inCheck;

    /*
     * Hbve we been initiblized. Effective bgbinst finblizer bttbcks.
     */
    privbte boolebn initiblized = fblse;


    /**
     * returns true if the current context hbs been grbnted AllPermission
     */
    privbte boolebn hbsAllPermission() {
        try {
            checkPermission(SecurityConstbnts.ALL_PERMISSION);
            return true;
        } cbtch (SecurityException se) {
            return fblse;
        }
    }

    /**
     * Tests if there is b security check in progress.
     *
     * @return the vblue of the <code>inCheck</code> field. This field
     *          should contbin <code>true</code> if b security check is
     *          in progress,
     *          <code>fblse</code> otherwise.
     * @see     jbvb.lbng.SecurityMbnbger#inCheck
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     */
    @Deprecbted
    public boolebn getInCheck() {
        return inCheck;
    }

    /**
     * Constructs b new <code>SecurityMbnbger</code>.
     *
     * <p> If there is b security mbnbger blrebdy instblled, this method first
     * cblls the security mbnbger's <code>checkPermission</code> method
     * with the <code>RuntimePermission("crebteSecurityMbnbger")</code>
     * permission to ensure the cblling threbd hbs permission to crebte b new
     * security mbnbger.
     * This mby result in throwing b <code>SecurityException</code>.
     *
     * @exception  jbvb.lbng.SecurityException if b security mbnbger blrebdy
     *             exists bnd its <code>checkPermission</code> method
     *             doesn't bllow crebtion of b new security mbnbger.
     * @see        jbvb.lbng.System#getSecurityMbnbger()
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     * @see jbvb.lbng.RuntimePermission
     */
    public SecurityMbnbger() {
        synchronized(SecurityMbnbger.clbss) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                // bsk the currently instblled security mbnbger if we
                // cbn crebte b new one.
                sm.checkPermission(new RuntimePermission
                                   ("crebteSecurityMbnbger"));
            }
            initiblized = true;
        }
    }

    /**
     * Returns the current execution stbck bs bn brrby of clbsses.
     * <p>
     * The length of the brrby is the number of methods on the execution
     * stbck. The element bt index <code>0</code> is the clbss of the
     * currently executing method, the element bt index <code>1</code> is
     * the clbss of thbt method's cbller, bnd so on.
     *
     * @return  the execution stbck.
     */
    protected nbtive Clbss<?>[] getClbssContext();

    /**
     * Returns the clbss lobder of the most recently executing method from
     * b clbss defined using b non-system clbss lobder. A non-system
     * clbss lobder is defined bs being b clbss lobder thbt is not equbl to
     * the system clbss lobder (bs returned
     * by {@link ClbssLobder#getSystemClbssLobder}) or one of its bncestors.
     * <p>
     * This method will return
     * <code>null</code> in the following three cbses:
     * <ol>
     *   <li>All methods on the execution stbck bre from clbsses
     *   defined using the system clbss lobder or one of its bncestors.
     *
     *   <li>All methods on the execution stbck up to the first
     *   "privileged" cbller
     *   (see {@link jbvb.security.AccessController#doPrivileged})
     *   bre from clbsses
     *   defined using the system clbss lobder or one of its bncestors.
     *
     *   <li> A cbll to <code>checkPermission</code> with
     *   <code>jbvb.security.AllPermission</code> does not
     *   result in b SecurityException.
     *
     * </ol>
     *
     * @return  the clbss lobder of the most recent occurrence on the stbck
     *          of b method from b clbss defined using b non-system clbss
     *          lobder.
     *
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     *
     * @see  jbvb.lbng.ClbssLobder#getSystemClbssLobder() getSystemClbssLobder
     * @see  #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    protected ClbssLobder currentClbssLobder() {
        ClbssLobder cl = currentClbssLobder0();
        if ((cl != null) && hbsAllPermission())
            cl = null;
        return cl;
    }

    privbte nbtive ClbssLobder currentClbssLobder0();

    /**
     * Returns the clbss of the most recently executing method from
     * b clbss defined using b non-system clbss lobder. A non-system
     * clbss lobder is defined bs being b clbss lobder thbt is not equbl to
     * the system clbss lobder (bs returned
     * by {@link ClbssLobder#getSystemClbssLobder}) or one of its bncestors.
     * <p>
     * This method will return
     * <code>null</code> in the following three cbses:
     * <ol>
     *   <li>All methods on the execution stbck bre from clbsses
     *   defined using the system clbss lobder or one of its bncestors.
     *
     *   <li>All methods on the execution stbck up to the first
     *   "privileged" cbller
     *   (see {@link jbvb.security.AccessController#doPrivileged})
     *   bre from clbsses
     *   defined using the system clbss lobder or one of its bncestors.
     *
     *   <li> A cbll to <code>checkPermission</code> with
     *   <code>jbvb.security.AllPermission</code> does not
     *   result in b SecurityException.
     *
     * </ol>
     *
     * @return  the clbss  of the most recent occurrence on the stbck
     *          of b method from b clbss defined using b non-system clbss
     *          lobder.
     *
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     *
     * @see  jbvb.lbng.ClbssLobder#getSystemClbssLobder() getSystemClbssLobder
     * @see  #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    protected Clbss<?> currentLobdedClbss() {
        Clbss<?> c = currentLobdedClbss0();
        if ((c != null) && hbsAllPermission())
            c = null;
        return c;
    }

    /**
     * Returns the stbck depth of the specified clbss.
     *
     * @pbrbm   nbme   the fully qublified nbme of the clbss to sebrch for.
     * @return  the depth on the stbck frbme of the first occurrence of b
     *          method from b clbss with the specified nbme;
     *          <code>-1</code> if such b frbme cbnnot be found.
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     *
     */
    @Deprecbted
    protected nbtive int clbssDepth(String nbme);

    /**
     * Returns the stbck depth of the most recently executing method
     * from b clbss defined using b non-system clbss lobder.  A non-system
     * clbss lobder is defined bs being b clbss lobder thbt is not equbl to
     * the system clbss lobder (bs returned
     * by {@link ClbssLobder#getSystemClbssLobder}) or one of its bncestors.
     * <p>
     * This method will return
     * -1 in the following three cbses:
     * <ol>
     *   <li>All methods on the execution stbck bre from clbsses
     *   defined using the system clbss lobder or one of its bncestors.
     *
     *   <li>All methods on the execution stbck up to the first
     *   "privileged" cbller
     *   (see {@link jbvb.security.AccessController#doPrivileged})
     *   bre from clbsses
     *   defined using the system clbss lobder or one of its bncestors.
     *
     *   <li> A cbll to <code>checkPermission</code> with
     *   <code>jbvb.security.AllPermission</code> does not
     *   result in b SecurityException.
     *
     * </ol>
     *
     * @return the depth on the stbck frbme of the most recent occurrence of
     *          b method from b clbss defined using b non-system clbss lobder.
     *
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     *
     * @see   jbvb.lbng.ClbssLobder#getSystemClbssLobder() getSystemClbssLobder
     * @see   #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    protected int clbssLobderDepth() {
        int depth = clbssLobderDepth0();
        if (depth != -1) {
            if (hbsAllPermission())
                depth = -1;
            else
                depth--; // mbke sure we don't include ourself
        }
        return depth;
    }

    privbte nbtive int clbssLobderDepth0();

    /**
     * Tests if b method from b clbss with the specified
     *         nbme is on the execution stbck.
     *
     * @pbrbm  nbme   the fully qublified nbme of the clbss.
     * @return <code>true</code> if b method from b clbss with the specified
     *         nbme is on the execution stbck; <code>fblse</code> otherwise.
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     */
    @Deprecbted
    protected boolebn inClbss(String nbme) {
        return clbssDepth(nbme) >= 0;
    }

    /**
     * Bbsicblly, tests if b method from b clbss defined using b
     *          clbss lobder is on the execution stbck.
     *
     * @return  <code>true</code> if b cbll to <code>currentClbssLobder</code>
     *          hbs b non-null return vblue.
     *
     * @deprecbted This type of security checking is not recommended.
     *  It is recommended thbt the <code>checkPermission</code>
     *  cbll be used instebd.
     * @see        #currentClbssLobder() currentClbssLobder
     */
    @Deprecbted
    protected boolebn inClbssLobder() {
        return currentClbssLobder() != null;
    }

    /**
     * Crebtes bn object thbt encbpsulbtes the current execution
     * environment. The result of this method is used, for exbmple, by the
     * three-brgument <code>checkConnect</code> method bnd by the
     * two-brgument <code>checkRebd</code> method.
     * These methods bre needed becbuse b trusted method mby be cblled
     * on to rebd b file or open b socket on behblf of bnother method.
     * The trusted method needs to determine if the other (possibly
     * untrusted) method would be bllowed to perform the operbtion on its
     * own.
     * <p> The defbult implementbtion of this method is to return
     * bn <code>AccessControlContext</code> object.
     *
     * @return  bn implementbtion-dependent object thbt encbpsulbtes
     *          sufficient informbtion bbout the current execution environment
     *          to perform some security checks lbter.
     * @see     jbvb.lbng.SecurityMbnbger#checkConnect(jbvb.lbng.String, int,
     *   jbvb.lbng.Object) checkConnect
     * @see     jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String,
     *   jbvb.lbng.Object) checkRebd
     * @see     jbvb.security.AccessControlContext AccessControlContext
     */
    public Object getSecurityContext() {
        return AccessController.getContext();
    }

    /**
     * Throws b <code>SecurityException</code> if the requested
     * bccess, specified by the given permission, is not permitted bbsed
     * on the security policy currently in effect.
     * <p>
     * This method cblls <code>AccessController.checkPermission</code>
     * with the given permission.
     *
     * @pbrbm     perm   the requested permission.
     * @exception SecurityException if bccess is not permitted bbsed on
     *            the current security policy.
     * @exception NullPointerException if the permission brgument is
     *            <code>null</code>.
     * @since     1.2
     */
    public void checkPermission(Permission perm) {
        jbvb.security.AccessController.checkPermission(perm);
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * specified security context is denied bccess to the resource
     * specified by the given permission.
     * The context must be b security
     * context returned by b previous cbll to
     * <code>getSecurityContext</code> bnd the bccess control
     * decision is bbsed upon the configured security policy for
     * thbt security context.
     * <p>
     * If <code>context</code> is bn instbnce of
     * <code>AccessControlContext</code> then the
     * <code>AccessControlContext.checkPermission</code> method is
     * invoked with the specified permission.
     * <p>
     * If <code>context</code> is not bn instbnce of
     * <code>AccessControlContext</code> then b
     * <code>SecurityException</code> is thrown.
     *
     * @pbrbm      perm      the specified permission
     * @pbrbm      context   b system-dependent security context.
     * @exception  SecurityException  if the specified security context
     *             is not bn instbnce of <code>AccessControlContext</code>
     *             (e.g., is <code>null</code>), or is denied bccess to the
     *             resource specified by the given permission.
     * @exception  NullPointerException if the permission brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.SecurityMbnbger#getSecurityContext()
     * @see jbvb.security.AccessControlContext#checkPermission(jbvb.security.Permission)
     * @since      1.2
     */
    public void checkPermission(Permission perm, Object context) {
        if (context instbnceof AccessControlContext) {
            ((AccessControlContext)context).checkPermission(perm);
        } else {
            throw new SecurityException();
        }
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to crebte b new clbss lobder.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("crebteClbssLobder")</code>
     * permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkCrebteClbssLobder</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @exception SecurityException if the cblling threbd does not
     *             hbve permission
     *             to crebte b new clbss lobder.
     * @see        jbvb.lbng.ClbssLobder#ClbssLobder()
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkCrebteClbssLobder() {
        checkPermission(SecurityConstbnts.CREATE_CLASSLOADER_PERMISSION);
    }

    /**
     * reference to the root threbd group, used for the checkAccess
     * methods.
     */

    privbte stbtic ThrebdGroup rootGroup = getRootGroup();

    privbte stbtic ThrebdGroup getRootGroup() {
        ThrebdGroup root =  Threbd.currentThrebd().getThrebdGroup();
        while (root.getPbrent() != null) {
            root = root.getPbrent();
        }
        return root;
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to modify the threbd brgument.
     * <p>
     * This method is invoked for the current security mbnbger by the
     * <code>stop</code>, <code>suspend</code>, <code>resume</code>,
     * <code>setPriority</code>, <code>setNbme</code>, bnd
     * <code>setDbemon</code> methods of clbss <code>Threbd</code>.
     * <p>
     * If the threbd brgument is b system threbd (belongs to
     * the threbd group with b <code>null</code> pbrent) then
     * this method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("modifyThrebd")</code> permission.
     * If the threbd brgument is <i>not</i> b system threbd,
     * this method just returns silently.
     * <p>
     * Applicbtions thbt wbnt b stricter policy should override this
     * method. If this method is overridden, the method thbt overrides
     * it should bdditionblly check to see if the cblling threbd hbs the
     * <code>RuntimePermission("modifyThrebd")</code> permission, bnd
     * if so, return silently. This is to ensure thbt code grbnted
     * thbt permission (such bs the JDK itself) is bllowed to
     * mbnipulbte bny threbd.
     * <p>
     * If this method is overridden, then
     * <code>super.checkAccess</code> should
     * be cblled by the first stbtement in the overridden method, or the
     * equivblent security check should be plbced in the overridden method.
     *
     * @pbrbm      t   the threbd to be checked.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to modify the threbd.
     * @exception  NullPointerException if the threbd brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.Threbd#resume() resume
     * @see        jbvb.lbng.Threbd#setDbemon(boolebn) setDbemon
     * @see        jbvb.lbng.Threbd#setNbme(jbvb.lbng.String) setNbme
     * @see        jbvb.lbng.Threbd#setPriority(int) setPriority
     * @see        jbvb.lbng.Threbd#stop() stop
     * @see        jbvb.lbng.Threbd#suspend() suspend
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkAccess(Threbd t) {
        if (t == null) {
            throw new NullPointerException("threbd cbn't be null");
        }
        if (t.getThrebdGroup() == rootGroup) {
            checkPermission(SecurityConstbnts.MODIFY_THREAD_PERMISSION);
        } else {
            // just return
        }
    }
    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to modify the threbd group brgument.
     * <p>
     * This method is invoked for the current security mbnbger when b
     * new child threbd or child threbd group is crebted, bnd by the
     * <code>setDbemon</code>, <code>setMbxPriority</code>,
     * <code>stop</code>, <code>suspend</code>, <code>resume</code>, bnd
     * <code>destroy</code> methods of clbss <code>ThrebdGroup</code>.
     * <p>
     * If the threbd group brgument is the system threbd group (
     * hbs b <code>null</code> pbrent) then
     * this method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("modifyThrebdGroup")</code> permission.
     * If the threbd group brgument is <i>not</i> the system threbd group,
     * this method just returns silently.
     * <p>
     * Applicbtions thbt wbnt b stricter policy should override this
     * method. If this method is overridden, the method thbt overrides
     * it should bdditionblly check to see if the cblling threbd hbs the
     * <code>RuntimePermission("modifyThrebdGroup")</code> permission, bnd
     * if so, return silently. This is to ensure thbt code grbnted
     * thbt permission (such bs the JDK itself) is bllowed to
     * mbnipulbte bny threbd.
     * <p>
     * If this method is overridden, then
     * <code>super.checkAccess</code> should
     * be cblled by the first stbtement in the overridden method, or the
     * equivblent security check should be plbced in the overridden method.
     *
     * @pbrbm      g   the threbd group to be checked.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to modify the threbd group.
     * @exception  NullPointerException if the threbd group brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.ThrebdGroup#destroy() destroy
     * @see        jbvb.lbng.ThrebdGroup#resume() resume
     * @see        jbvb.lbng.ThrebdGroup#setDbemon(boolebn) setDbemon
     * @see        jbvb.lbng.ThrebdGroup#setMbxPriority(int) setMbxPriority
     * @see        jbvb.lbng.ThrebdGroup#stop() stop
     * @see        jbvb.lbng.ThrebdGroup#suspend() suspend
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkAccess(ThrebdGroup g) {
        if (g == null) {
            throw new NullPointerException("threbd group cbn't be null");
        }
        if (g == rootGroup) {
            checkPermission(SecurityConstbnts.MODIFY_THREADGROUP_PERMISSION);
        } else {
            // just return
        }
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to cbuse the Jbvb Virtubl Mbchine to
     * hblt with the specified stbtus code.
     * <p>
     * This method is invoked for the current security mbnbger by the
     * <code>exit</code> method of clbss <code>Runtime</code>. A stbtus
     * of <code>0</code> indicbtes success; other vblues indicbte vbrious
     * errors.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("exitVM."+stbtus)</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkExit</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      stbtus   the exit stbtus.
     * @exception SecurityException if the cblling threbd does not hbve
     *              permission to hblt the Jbvb Virtubl Mbchine with
     *              the specified stbtus.
     * @see        jbvb.lbng.Runtime#exit(int) exit
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkExit(int stbtus) {
        checkPermission(new RuntimePermission("exitVM."+stbtus));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to crebte b subprocess.
     * <p>
     * This method is invoked for the current security mbnbger by the
     * <code>exec</code> methods of clbss <code>Runtime</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>FilePermission(cmd,"execute")</code> permission
     * if cmd is bn bbsolute pbth, otherwise it cblls
     * <code>checkPermission</code> with
     * <code>FilePermission("&lt;&lt;ALL FILES&gt;&gt;","execute")</code>.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkExec</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      cmd   the specified system commbnd.
     * @exception  SecurityException if the cblling threbd does not hbve
     *             permission to crebte b subprocess.
     * @exception  NullPointerException if the <code>cmd</code> brgument is
     *             <code>null</code>.
     * @see     jbvb.lbng.Runtime#exec(jbvb.lbng.String)
     * @see     jbvb.lbng.Runtime#exec(jbvb.lbng.String, jbvb.lbng.String[])
     * @see     jbvb.lbng.Runtime#exec(jbvb.lbng.String[])
     * @see     jbvb.lbng.Runtime#exec(jbvb.lbng.String[], jbvb.lbng.String[])
     * @see     #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkExec(String cmd) {
        File f = new File(cmd);
        if (f.isAbsolute()) {
            checkPermission(new FilePermission(cmd,
                SecurityConstbnts.FILE_EXECUTE_ACTION));
        } else {
            checkPermission(new FilePermission("<<ALL FILES>>",
                SecurityConstbnts.FILE_EXECUTE_ACTION));
        }
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to dynbmic link the librbry code
     * specified by the string brgument file. The brgument is either b
     * simple librbry nbme or b complete filenbme.
     * <p>
     * This method is invoked for the current security mbnbger by
     * methods <code>lobd</code> bnd <code>lobdLibrbry</code> of clbss
     * <code>Runtime</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("lobdLibrbry."+lib)</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkLink</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      lib   the nbme of the librbry.
     * @exception  SecurityException if the cblling threbd does not hbve
     *             permission to dynbmicblly link the librbry.
     * @exception  NullPointerException if the <code>lib</code> brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.Runtime#lobd(jbvb.lbng.String)
     * @see        jbvb.lbng.Runtime#lobdLibrbry(jbvb.lbng.String)
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkLink(String lib) {
        if (lib == null) {
            throw new NullPointerException("librbry cbn't be null");
        }
        checkPermission(new RuntimePermission("lobdLibrbry."+lib));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to rebd from the specified file
     * descriptor.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("rebdFileDescriptor")</code>
     * permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkRebd</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      fd   the system-dependent file descriptor.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to bccess the specified file descriptor.
     * @exception  NullPointerException if the file descriptor brgument is
     *             <code>null</code>.
     * @see        jbvb.io.FileDescriptor
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkRebd(FileDescriptor fd) {
        if (fd == null) {
            throw new NullPointerException("file descriptor cbn't be null");
        }
        checkPermission(new RuntimePermission("rebdFileDescriptor"));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to rebd the file specified by the
     * string brgument.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>FilePermission(file,"rebd")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkRebd</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      file   the system-dependent file nbme.
     * @exception  SecurityException if the cblling threbd does not hbve
     *             permission to bccess the specified file.
     * @exception  NullPointerException if the <code>file</code> brgument is
     *             <code>null</code>.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkRebd(String file) {
        checkPermission(new FilePermission(file,
            SecurityConstbnts.FILE_READ_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * specified security context is not bllowed to rebd the file
     * specified by the string brgument. The context must be b security
     * context returned by b previous cbll to
     * <code>getSecurityContext</code>.
     * <p> If <code>context</code> is bn instbnce of
     * <code>AccessControlContext</code> then the
     * <code>AccessControlContext.checkPermission</code> method will
     * be invoked with the <code>FilePermission(file,"rebd")</code> permission.
     * <p> If <code>context</code> is not bn instbnce of
     * <code>AccessControlContext</code> then b
     * <code>SecurityException</code> is thrown.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkRebd</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      file      the system-dependent filenbme.
     * @pbrbm      context   b system-dependent security context.
     * @exception  SecurityException  if the specified security context
     *             is not bn instbnce of <code>AccessControlContext</code>
     *             (e.g., is <code>null</code>), or does not hbve permission
     *             to rebd the specified file.
     * @exception  NullPointerException if the <code>file</code> brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.SecurityMbnbger#getSecurityContext()
     * @see        jbvb.security.AccessControlContext#checkPermission(jbvb.security.Permission)
     */
    public void checkRebd(String file, Object context) {
        checkPermission(
            new FilePermission(file, SecurityConstbnts.FILE_READ_ACTION),
            context);
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to write to the specified file
     * descriptor.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("writeFileDescriptor")</code>
     * permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkWrite</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      fd   the system-dependent file descriptor.
     * @exception SecurityException  if the cblling threbd does not hbve
     *             permission to bccess the specified file descriptor.
     * @exception  NullPointerException if the file descriptor brgument is
     *             <code>null</code>.
     * @see        jbvb.io.FileDescriptor
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkWrite(FileDescriptor fd) {
        if (fd == null) {
            throw new NullPointerException("file descriptor cbn't be null");
        }
        checkPermission(new RuntimePermission("writeFileDescriptor"));

    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to write to the file specified by
     * the string brgument.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>FilePermission(file,"write")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkWrite</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      file   the system-dependent filenbme.
     * @exception  SecurityException  if the cblling threbd does not
     *             hbve permission to bccess the specified file.
     * @exception  NullPointerException if the <code>file</code> brgument is
     *             <code>null</code>.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkWrite(String file) {
        checkPermission(new FilePermission(file,
            SecurityConstbnts.FILE_WRITE_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to delete the specified file.
     * <p>
     * This method is invoked for the current security mbnbger by the
     * <code>delete</code> method of clbss <code>File</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>FilePermission(file,"delete")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkDelete</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      file   the system-dependent filenbme.
     * @exception  SecurityException if the cblling threbd does not
     *             hbve permission to delete the file.
     * @exception  NullPointerException if the <code>file</code> brgument is
     *             <code>null</code>.
     * @see        jbvb.io.File#delete()
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkDelete(String file) {
        checkPermission(new FilePermission(file,
            SecurityConstbnts.FILE_DELETE_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to open b socket connection to the
     * specified host bnd port number.
     * <p>
     * A port number of <code>-1</code> indicbtes thbt the cblling
     * method is bttempting to determine the IP bddress of the specified
     * host nbme.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>SocketPermission(host+":"+port,"connect")</code> permission if
     * the port is not equbl to -1. If the port is equbl to -1, then
     * it cblls <code>checkPermission</code> with the
     * <code>SocketPermission(host,"resolve")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkConnect</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      host   the host nbme port to connect to.
     * @pbrbm      port   the protocol port to connect to.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to open b socket connection to the specified
     *               <code>host</code> bnd <code>port</code>.
     * @exception  NullPointerException if the <code>host</code> brgument is
     *             <code>null</code>.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkConnect(String host, int port) {
        if (host == null) {
            throw new NullPointerException("host cbn't be null");
        }
        if (!host.stbrtsWith("[") && host.indexOf(':') != -1) {
            host = "[" + host + "]";
        }
        if (port == -1) {
            checkPermission(new SocketPermission(host,
                SecurityConstbnts.SOCKET_RESOLVE_ACTION));
        } else {
            checkPermission(new SocketPermission(host+":"+port,
                SecurityConstbnts.SOCKET_CONNECT_ACTION));
        }
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * specified security context is not bllowed to open b socket
     * connection to the specified host bnd port number.
     * <p>
     * A port number of <code>-1</code> indicbtes thbt the cblling
     * method is bttempting to determine the IP bddress of the specified
     * host nbme.
     * <p> If <code>context</code> is not bn instbnce of
     * <code>AccessControlContext</code> then b
     * <code>SecurityException</code> is thrown.
     * <p>
     * Otherwise, the port number is checked. If it is not equbl
     * to -1, the <code>context</code>'s <code>checkPermission</code>
     * method is cblled with b
     * <code>SocketPermission(host+":"+port,"connect")</code> permission.
     * If the port is equbl to -1, then
     * the <code>context</code>'s <code>checkPermission</code> method
     * is cblled with b
     * <code>SocketPermission(host,"resolve")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkConnect</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      host      the host nbme port to connect to.
     * @pbrbm      port      the protocol port to connect to.
     * @pbrbm      context   b system-dependent security context.
     * @exception  SecurityException if the specified security context
     *             is not bn instbnce of <code>AccessControlContext</code>
     *             (e.g., is <code>null</code>), or does not hbve permission
     *             to open b socket connection to the specified
     *             <code>host</code> bnd <code>port</code>.
     * @exception  NullPointerException if the <code>host</code> brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.SecurityMbnbger#getSecurityContext()
     * @see        jbvb.security.AccessControlContext#checkPermission(jbvb.security.Permission)
     */
    public void checkConnect(String host, int port, Object context) {
        if (host == null) {
            throw new NullPointerException("host cbn't be null");
        }
        if (!host.stbrtsWith("[") && host.indexOf(':') != -1) {
            host = "[" + host + "]";
        }
        if (port == -1)
            checkPermission(new SocketPermission(host,
                SecurityConstbnts.SOCKET_RESOLVE_ACTION),
                context);
        else
            checkPermission(new SocketPermission(host+":"+port,
                SecurityConstbnts.SOCKET_CONNECT_ACTION),
                context);
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to wbit for b connection request on
     * the specified locbl port number.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>SocketPermission("locblhost:"+port,"listen")</code>.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkListen</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      port   the locbl port.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to listen on the specified port.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkListen(int port) {
        checkPermission(new SocketPermission("locblhost:"+port,
            SecurityConstbnts.SOCKET_LISTEN_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not permitted to bccept b socket connection from
     * the specified host bnd port number.
     * <p>
     * This method is invoked for the current security mbnbger by the
     * <code>bccept</code> method of clbss <code>ServerSocket</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>SocketPermission(host+":"+port,"bccept")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkAccept</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      host   the host nbme of the socket connection.
     * @pbrbm      port   the port number of the socket connection.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to bccept the connection.
     * @exception  NullPointerException if the <code>host</code> brgument is
     *             <code>null</code>.
     * @see        jbvb.net.ServerSocket#bccept()
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkAccept(String host, int port) {
        if (host == null) {
            throw new NullPointerException("host cbn't be null");
        }
        if (!host.stbrtsWith("[") && host.indexOf(':') != -1) {
            host = "[" + host + "]";
        }
        checkPermission(new SocketPermission(host+":"+port,
            SecurityConstbnts.SOCKET_ACCEPT_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to use
     * (join/lebve/send/receive) IP multicbst.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>jbvb.net.SocketPermission(mbddr.getHostAddress(),
     * "bccept,connect")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkMulticbst</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      mbddr  Internet group bddress to be used.
     * @exception  SecurityException  if the cblling threbd is not bllowed to
     *  use (join/lebve/send/receive) IP multicbst.
     * @exception  NullPointerException if the bddress brgument is
     *             <code>null</code>.
     * @since      1.1
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkMulticbst(InetAddress mbddr) {
        String host = mbddr.getHostAddress();
        if (!host.stbrtsWith("[") && host.indexOf(':') != -1) {
            host = "[" + host + "]";
        }
        checkPermission(new SocketPermission(host,
            SecurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to use
     * (join/lebve/send/receive) IP multicbst.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>jbvb.net.SocketPermission(mbddr.getHostAddress(),
     * "bccept,connect")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkMulticbst</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      mbddr  Internet group bddress to be used.
     * @pbrbm      ttl        vblue in use, if it is multicbst send.
     * Note: this pbrticulbr implementbtion does not use the ttl
     * pbrbmeter.
     * @exception  SecurityException  if the cblling threbd is not bllowed to
     *  use (join/lebve/send/receive) IP multicbst.
     * @exception  NullPointerException if the bddress brgument is
     *             <code>null</code>.
     * @since      1.1
     * @deprecbted Use #checkPermission(jbvb.security.Permission) instebd
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    public void checkMulticbst(InetAddress mbddr, byte ttl) {
        String host = mbddr.getHostAddress();
        if (!host.stbrtsWith("[") && host.indexOf(':') != -1) {
            host = "[" + host + "]";
        }
        checkPermission(new SocketPermission(host,
            SecurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to bccess or modify the system
     * properties.
     * <p>
     * This method is used by the <code>getProperties</code> bnd
     * <code>setProperties</code> methods of clbss <code>System</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>PropertyPermission("*", "rebd,write")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkPropertiesAccess</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to bccess or modify the system properties.
     * @see        jbvb.lbng.System#getProperties()
     * @see        jbvb.lbng.System#setProperties(jbvb.util.Properties)
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkPropertiesAccess() {
        checkPermission(new PropertyPermission("*",
            SecurityConstbnts.PROPERTY_RW_ACTION));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to bccess the system property with
     * the specified <code>key</code> nbme.
     * <p>
     * This method is used by the <code>getProperty</code> method of
     * clbss <code>System</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>PropertyPermission(key, "rebd")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkPropertyAccess</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm      key   b system property key.
     *
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to bccess the specified system property.
     * @exception  NullPointerException if the <code>key</code> brgument is
     *             <code>null</code>.
     * @exception  IllegblArgumentException if <code>key</code> is empty.
     *
     * @see        jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkPropertyAccess(String key) {
        checkPermission(new PropertyPermission(key,
            SecurityConstbnts.PROPERTY_READ_ACTION));
    }

    /**
     * Returns {@code true} if the cblling threbd hbs {@code AllPermission}.
     *
     * @pbrbm      window   not used except to check if it is {@code null}.
     * @return     {@code true} if the cblling threbd hbs {@code AllPermission}.
     * @exception  NullPointerException if the {@code window} brgument is
     *             {@code null}.
     * @deprecbted This method wbs originblly used to check if the cblling threbd
     *             wbs trusted to bring up b top-level window. The method hbs been
     *             obsoleted bnd code should instebd use {@link #checkPermission}
     *             to check {@code AWTPermission("showWindowWithoutWbrningBbnner")}.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    public boolebn checkTopLevelWindow(Object window) {
        if (window == null) {
            throw new NullPointerException("window cbn't be null");
        }
        return hbsAllPermission();
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to initibte b print job request.
     * <p>
     * This method cblls
     * <code>checkPermission</code> with the
     * <code>RuntimePermission("queuePrintJob")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkPrintJobAccess</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to initibte b print job request.
     * @since   1.1
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkPrintJobAccess() {
        checkPermission(new RuntimePermission("queuePrintJob"));
    }

    /**
     * Throws {@code SecurityException} if the cblling threbd does
     * not hbve {@code AllPermission}.
     *
     * @since   1.1
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             {@code AllPermission}
     * @deprecbted This method wbs originblly used to check if the cblling
     *             threbd could bccess the system clipbobrd. The method hbs been
     *             obsoleted bnd code should instebd use {@link #checkPermission}
     *             to check {@code AWTPermission("bccessClipbobrd")}.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    public void checkSystemClipbobrdAccess() {
        checkPermission(SecurityConstbnts.ALL_PERMISSION);
    }

    /**
     * Throws {@code SecurityException} if the cblling threbd does
     * not hbve {@code AllPermission}.
     *
     * @since   1.1
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             {@code AllPermission}
     * @deprecbted This method wbs originblly used to check if the cblling
     *             threbd could bccess the AWT event queue. The method hbs been
     *             obsoleted bnd code should instebd use {@link #checkPermission}
     *             to check {@code AWTPermission("bccessEventQueue")}.
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    public void checkAwtEventQueueAccess() {
        checkPermission(SecurityConstbnts.ALL_PERMISSION);
    }

    /*
     * We hbve bn initibl invblid bit (initiblly fblse) for the clbss
     * vbribbles which tell if the cbche is vblid.  If the underlying
     * jbvb.security.Security property chbnges vib setProperty(), the
     * Security clbss uses reflection to chbnge the vbribble bnd thus
     * invblidbte the cbche.
     *
     * Locking is hbndled by synchronizbtion to the
     * pbckbgeAccessLock/pbckbgeDefinitionLock objects.  They bre only
     * used in this clbss.
     *
     * Note thbt cbche invblidbtion bs b result of the property chbnge
     * hbppens without using these locks, so there mby be b delby between
     * when b threbd updbtes the property bnd when other threbds updbtes
     * the cbche.
     */
    privbte stbtic boolebn pbckbgeAccessVblid = fblse;
    privbte stbtic String[] pbckbgeAccess;
    privbte stbtic finbl Object pbckbgeAccessLock = new Object();

    privbte stbtic boolebn pbckbgeDefinitionVblid = fblse;
    privbte stbtic String[] pbckbgeDefinition;
    privbte stbtic finbl Object pbckbgeDefinitionLock = new Object();

    privbte stbtic String[] getPbckbges(String p) {
        String pbckbges[] = null;
        if (p != null && !p.equbls("")) {
            jbvb.util.StringTokenizer tok =
                new jbvb.util.StringTokenizer(p, ",");
            int n = tok.countTokens();
            if (n > 0) {
                pbckbges = new String[n];
                int i = 0;
                while (tok.hbsMoreElements()) {
                    String s = tok.nextToken().trim();
                    pbckbges[i++] = s;
                }
            }
        }

        if (pbckbges == null)
            pbckbges = new String[0];
        return pbckbges;
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to bccess the pbckbge specified by
     * the brgument.
     * <p>
     * This method is used by the <code>lobdClbss</code> method of clbss
     * lobders.
     * <p>
     * This method first gets b list of
     * restricted pbckbges by obtbining b commb-sepbrbted list from
     * b cbll to
     * <code>jbvb.security.Security.getProperty("pbckbge.bccess")</code>,
     * bnd checks to see if <code>pkg</code> stbrts with or equbls
     * bny of the restricted pbckbges. If it does, then
     * <code>checkPermission</code> gets cblled with the
     * <code>RuntimePermission("bccessClbssInPbckbge."+pkg)</code>
     * permission.
     * <p>
     * If this method is overridden, then
     * <code>super.checkPbckbgeAccess</code> should be cblled
     * bs the first line in the overridden method.
     *
     * @pbrbm      pkg   the pbckbge nbme.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to bccess the specified pbckbge.
     * @exception  NullPointerException if the pbckbge nbme brgument is
     *             <code>null</code>.
     * @see        jbvb.lbng.ClbssLobder#lobdClbss(jbvb.lbng.String, boolebn)
     *  lobdClbss
     * @see        jbvb.security.Security#getProperty getProperty
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkPbckbgeAccess(String pkg) {
        if (pkg == null) {
            throw new NullPointerException("pbckbge nbme cbn't be null");
        }

        String[] pkgs;
        synchronized (pbckbgeAccessLock) {
            /*
             * Do we need to updbte our property brrby?
             */
            if (!pbckbgeAccessVblid) {
                String tmpPropertyStr =
                    AccessController.doPrivileged(
                        new PrivilegedAction<String>() {
                            public String run() {
                                return jbvb.security.Security.getProperty(
                                    "pbckbge.bccess");
                            }
                        }
                    );
                pbckbgeAccess = getPbckbges(tmpPropertyStr);
                pbckbgeAccessVblid = true;
            }

            // Using b snbpshot of pbckbgeAccess -- don't cbre if stbtic field
            // chbnges bfterwbrds; brrby contents won't chbnge.
            pkgs = pbckbgeAccess;
        }

        /*
         * Trbverse the list of pbckbges, check for bny mbtches.
         */
        for (String restrictedPkg : pkgs) {
            if (pkg.stbrtsWith(restrictedPkg) || restrictedPkg.equbls(pkg + ".")) {
                checkPermission(
                    new RuntimePermission("bccessClbssInPbckbge." + pkg));
                brebk;  // No need to continue; only need to check this once
            }
        }
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to define clbsses in the pbckbge
     * specified by the brgument.
     * <p>
     * This method is used by the <code>lobdClbss</code> method of some
     * clbss lobders.
     * <p>
     * This method first gets b list of restricted pbckbges by
     * obtbining b commb-sepbrbted list from b cbll to
     * <code>jbvb.security.Security.getProperty("pbckbge.definition")</code>,
     * bnd checks to see if <code>pkg</code> stbrts with or equbls
     * bny of the restricted pbckbges. If it does, then
     * <code>checkPermission</code> gets cblled with the
     * <code>RuntimePermission("defineClbssInPbckbge."+pkg)</code>
     * permission.
     * <p>
     * If this method is overridden, then
     * <code>super.checkPbckbgeDefinition</code> should be cblled
     * bs the first line in the overridden method.
     *
     * @pbrbm      pkg   the pbckbge nbme.
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to define clbsses in the specified pbckbge.
     * @see        jbvb.lbng.ClbssLobder#lobdClbss(jbvb.lbng.String, boolebn)
     * @see        jbvb.security.Security#getProperty getProperty
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkPbckbgeDefinition(String pkg) {
        if (pkg == null) {
            throw new NullPointerException("pbckbge nbme cbn't be null");
        }

        String[] pkgs;
        synchronized (pbckbgeDefinitionLock) {
            /*
             * Do we need to updbte our property brrby?
             */
            if (!pbckbgeDefinitionVblid) {
                String tmpPropertyStr =
                    AccessController.doPrivileged(
                        new PrivilegedAction<String>() {
                            public String run() {
                                return jbvb.security.Security.getProperty(
                                    "pbckbge.definition");
                            }
                        }
                    );
                pbckbgeDefinition = getPbckbges(tmpPropertyStr);
                pbckbgeDefinitionVblid = true;
            }
            // Using b snbpshot of pbckbgeDefinition -- don't cbre if stbtic
            // field chbnges bfterwbrds; brrby contents won't chbnge.
            pkgs = pbckbgeDefinition;
        }

        /*
         * Trbverse the list of pbckbges, check for bny mbtches.
         */
        for (String restrictedPkg : pkgs) {
            if (pkg.stbrtsWith(restrictedPkg) || restrictedPkg.equbls(pkg + ".")) {
                checkPermission(
                    new RuntimePermission("defineClbssInPbckbge." + pkg));
                brebk; // No need to continue; only need to check this once
            }
        }
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to set the socket fbctory used by
     * <code>ServerSocket</code> or <code>Socket</code>, or the strebm
     * hbndler fbctory used by <code>URL</code>.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>RuntimePermission("setFbctory")</code> permission.
     * <p>
     * If you override this method, then you should mbke b cbll to
     * <code>super.checkSetFbctory</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @exception  SecurityException  if the cblling threbd does not hbve
     *             permission to specify b socket fbctory or b strebm
     *             hbndler fbctory.
     *
     * @see        jbvb.net.ServerSocket#setSocketFbctory(jbvb.net.SocketImplFbctory) setSocketFbctory
     * @see        jbvb.net.Socket#setSocketImplFbctory(jbvb.net.SocketImplFbctory) setSocketImplFbctory
     * @see        jbvb.net.URL#setURLStrebmHbndlerFbctory(jbvb.net.URLStrebmHbndlerFbctory) setURLStrebmHbndlerFbctory
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkSetFbctory() {
        checkPermission(new RuntimePermission("setFbctory"));
    }

    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to bccess members.
     * <p>
     * The defbult policy is to bllow bccess to PUBLIC members, bs well
     * bs bccess to clbsses thbt hbve the sbme clbss lobder bs the cbller.
     * In bll other cbses, this method cblls <code>checkPermission</code>
     * with the <code>RuntimePermission("bccessDeclbredMembers")
     * </code> permission.
     * <p>
     * If this method is overridden, then b cbll to
     * <code>super.checkMemberAccess</code> cbnnot be mbde,
     * bs the defbult implementbtion of <code>checkMemberAccess</code>
     * relies on the code being checked being bt b stbck depth of
     * 4.
     *
     * @pbrbm clbzz the clbss thbt reflection is to be performed on.
     *
     * @pbrbm which type of bccess, PUBLIC or DECLARED.
     *
     * @exception  SecurityException if the cbller does not hbve
     *             permission to bccess members.
     * @exception  NullPointerException if the <code>clbzz</code> brgument is
     *             <code>null</code>.
     *
     * @deprecbted This method relies on the cbller being bt b stbck depth
     *             of 4 which is error-prone bnd cbnnot be enforced by the runtime.
     *             Users of this method should instebd invoke {@link #checkPermission}
     *             directly.  This method will be chbnged in b future relebse
     *             to check the permission {@code jbvb.security.AllPermission}.
     *
     * @see jbvb.lbng.reflect.Member
     * @since 1.1
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    @Deprecbted
    @CbllerSensitive
    public void checkMemberAccess(Clbss<?> clbzz, int which) {
        if (clbzz == null) {
            throw new NullPointerException("clbss cbn't be null");
        }
        if (which != Member.PUBLIC) {
            Clbss<?> stbck[] = getClbssContext();
            /*
             * stbck depth of 4 should be the cbller of one of the
             * methods in jbvb.lbng.Clbss thbt invoke checkMember
             * bccess. The stbck should look like:
             *
             * someCbller                        [3]
             * jbvb.lbng.Clbss.someReflectionAPI [2]
             * jbvb.lbng.Clbss.checkMemberAccess [1]
             * SecurityMbnbger.checkMemberAccess [0]
             *
             */
            if ((stbck.length<4) ||
                (stbck[3].getClbssLobder() != clbzz.getClbssLobder())) {
                checkPermission(SecurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
            }
        }
    }

    /**
     * Determines whether the permission with the specified permission tbrget
     * nbme should be grbnted or denied.
     *
     * <p> If the requested permission is bllowed, this method returns
     * quietly. If denied, b SecurityException is rbised.
     *
     * <p> This method crebtes b <code>SecurityPermission</code> object for
     * the given permission tbrget nbme bnd cblls <code>checkPermission</code>
     * with it.
     *
     * <p> See the documentbtion for
     * <code>{@link jbvb.security.SecurityPermission}</code> for
     * b list of possible permission tbrget nbmes.
     *
     * <p> If you override this method, then you should mbke b cbll to
     * <code>super.checkSecurityAccess</code>
     * bt the point the overridden method would normblly throw bn
     * exception.
     *
     * @pbrbm tbrget the tbrget nbme of the <code>SecurityPermission</code>.
     *
     * @exception SecurityException if the cblling threbd does not hbve
     * permission for the requested bccess.
     * @exception NullPointerException if <code>tbrget</code> is null.
     * @exception IllegblArgumentException if <code>tbrget</code> is empty.
     *
     * @since   1.1
     * @see        #checkPermission(jbvb.security.Permission) checkPermission
     */
    public void checkSecurityAccess(String tbrget) {
        checkPermission(new SecurityPermission(tbrget));
    }

    privbte nbtive Clbss<?> currentLobdedClbss0();

    /**
     * Returns the threbd group into which to instbntibte bny new
     * threbd being crebted bt the time this is being cblled.
     * By defbult, it returns the threbd group of the current
     * threbd. This should be overridden by b specific security
     * mbnbger to return the bppropribte threbd group.
     *
     * @return  ThrebdGroup thbt new threbds bre instbntibted into
     * @since   1.1
     * @see     jbvb.lbng.ThrebdGroup
     */
    public ThrebdGroup getThrebdGroup() {
        return Threbd.currentThrebd().getThrebdGroup();
    }

}
