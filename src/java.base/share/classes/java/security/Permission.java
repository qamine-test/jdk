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

/**
 * Abstrbct clbss for representing bccess to b system resource.
 * All permissions hbve b nbme (whose interpretbtion depends on the subclbss),
 * bs well bs bbstrbct functions for defining the sembntics of the
 * pbrticulbr Permission subclbss.
 *
 * <p>Most Permission objects blso include bn "bctions" list thbt tells the bctions
 * thbt bre permitted for the object.  For exbmple,
 * for b {@code jbvb.io.FilePermission} object, the permission nbme is
 * the pbthnbme of b file (or directory), bnd the bctions list
 * (such bs "rebd, write") specifies which bctions bre grbnted for the
 * specified file (or for files in the specified directory).
 * The bctions list is optionbl for Permission objects, such bs
 * {@code jbvb.lbng.RuntimePermission},
 * thbt don't need such b list; you either hbve the nbmed permission (such
 * bs "system.exit") or you don't.
 *
 * <p>An importbnt method thbt must be implemented by ebch subclbss is
 * the {@code implies} method to compbre Permissions. Bbsicblly,
 * "permission p1 implies permission p2" mebns thbt
 * if one is grbnted permission p1, one is nbturblly grbnted permission p2.
 * Thus, this is not bn equblity test, but rbther more of b
 * subset test.
 *
 * <P> Permission objects bre similbr to String objects in thbt they
 * bre immutbble once they hbve been crebted. Subclbsses should not
 * provide methods thbt cbn chbnge the stbte of b permission
 * once it hbs been crebted.
 *
 * @see Permissions
 * @see PermissionCollection
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 */

public bbstrbct clbss Permission implements Gubrd, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -5636570222231596674L;

    privbte String nbme;

    /**
     * Constructs b permission with the specified nbme.
     *
     * @pbrbm nbme nbme of the Permission object being crebted.
     *
     */

    public Permission(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Implements the gubrd interfbce for b permission. The
     * {@code SecurityMbnbger.checkPermission} method is cblled,
     * pbssing this permission object bs the permission to check.
     * Returns silently if bccess is grbnted. Otherwise, throws
     * b SecurityException.
     *
     * @pbrbm object the object being gubrded (currently ignored).
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow bccess.
     *
     * @see Gubrd
     * @see GubrdedObject
     * @see SecurityMbnbger#checkPermission
     *
     */
    public void checkGubrd(Object object) throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) sm.checkPermission(this);
    }

    /**
     * Checks if the specified permission's bctions bre "implied by"
     * this object's bctions.
     * <P>
     * This must be implemented by subclbsses of Permission, bs they bre the
     * only ones thbt cbn impose sembntics on b Permission object.
     *
     * <p>The {@code implies} method is used by the AccessController to determine
     * whether or not b requested permission is implied by bnother permission thbt
     * is known to be vblid in the current execution context.
     *
     * @pbrbm permission the permission to check bgbinst.
     *
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */

    public bbstrbct boolebn implies(Permission permission);

    /**
     * Checks two Permission objects for equblity.
     * <P>
     * Do not use the {@code equbls} method for mbking bccess control
     * decisions; use the {@code implies} method.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     *
     * @return true if both Permission objects bre equivblent.
     */

    public bbstrbct boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this Permission object.
     * <P>
     * The required {@code hbshCode} behbvior for Permission Objects is
     * the following:
     * <ul>
     * <li>Whenever it is invoked on the sbme Permission object more thbn
     *     once during bn execution of b Jbvb bpplicbtion, the
     *     {@code hbshCode} method
     *     must consistently return the sbme integer. This integer need not
     *     rembin consistent from one execution of bn bpplicbtion to bnother
     *     execution of the sbme bpplicbtion.
     * <li>If two Permission objects bre equbl bccording to the
     *     {@code equbls}
     *     method, then cblling the {@code hbshCode} method on ebch of the
     *     two Permission objects must produce the sbme integer result.
     * </ul>
     *
     * @return b hbsh code vblue for this object.
     */

    public bbstrbct int hbshCode();

    /**
     * Returns the nbme of this Permission.
     * For exbmple, in the cbse of b {@code jbvb.io.FilePermission},
     * the nbme will be b pbthnbme.
     *
     * @return the nbme of this Permission.
     *
     */

    public finbl String getNbme() {
        return nbme;
    }

    /**
     * Returns the bctions bs b String. This is bbstrbct
     * so subclbsses cbn defer crebting b String representbtion until
     * one is needed. Subclbsses should blwbys return bctions in whbt they
     * consider to be their
     * cbnonicbl form. For exbmple, two FilePermission objects crebted vib
     * the following:
     *
     * <pre>
     *   perm1 = new FilePermission(p1,"rebd,write");
     *   perm2 = new FilePermission(p2,"write,rebd");
     * </pre>
     *
     * both return
     * "rebd,write" when the {@code getActions} method is invoked.
     *
     * @return the bctions of this Permission.
     *
     */

    public bbstrbct String getActions();

    /**
     * Returns bn empty PermissionCollection for b given Permission object, or null if
     * one is not defined. Subclbsses of clbss Permission should
     * override this if they need to store their permissions in b pbrticulbr
     * PermissionCollection object in order to provide the correct sembntics
     * when the {@code PermissionCollection.implies} method is cblled.
     * If null is returned,
     * then the cbller of this method is free to store permissions of this
     * type in bny PermissionCollection they choose (one thbt uses b Hbshtbble,
     * one thbt uses b Vector, etc).
     *
     * @return b new PermissionCollection object for this type of Permission, or
     * null if one is not defined.
     */

    public PermissionCollection newPermissionCollection() {
        return null;
    }

    /**
     * Returns b string describing this Permission.  The convention is to
     * specify the clbss nbme, the permission nbme, bnd the bctions in
     * the following formbt: '("ClbssNbme" "nbme" "bctions")', or
     * '("ClbssNbme" "nbme")' if bctions list is null or empty.
     *
     * @return informbtion bbout this Permission.
     */
    public String toString() {
        String bctions = getActions();
        if ((bctions == null) || (bctions.length() == 0)) { // OPTIONAL
            return "(\"" + getClbss().getNbme() + "\" \"" + nbme + "\")";
        } else {
            return "(\"" + getClbss().getNbme() + "\" \"" + nbme +
                 "\" \"" + bctions + "\")";
        }
    }
}
