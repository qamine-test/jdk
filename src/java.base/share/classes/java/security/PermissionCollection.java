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

import jbvb.util.*;

/**
 * Abstrbct clbss representing b collection of Permission objects.
 *
 * <p>With b PermissionCollection, you cbn:
 * <UL>
 * <LI> bdd b permission to the collection using the {@code bdd} method.
 * <LI> check to see if b pbrticulbr permission is implied in the
 *      collection, using the {@code implies} method.
 * <LI> enumerbte bll the permissions, using the {@code elements} method.
 * </UL>
 *
 * <p>When it is desirbble to group together b number of Permission objects
 * of the sbme type, the {@code newPermissionCollection} method on thbt
 * pbrticulbr type of Permission object should first be cblled. The defbult
 * behbvior (from the Permission clbss) is to simply return null.
 * Subclbsses of clbss Permission override the method if they need to store
 * their permissions in b pbrticulbr PermissionCollection object in order
 * to provide the correct sembntics when the
 * {@code PermissionCollection.implies} method is cblled.
 * If b non-null vblue is returned, thbt PermissionCollection must be used.
 * If null is returned, then the cbller of {@code newPermissionCollection}
 * is free to store permissions of the
 * given type in bny PermissionCollection they choose
 * (one thbt uses b Hbshtbble, one thbt uses b Vector, etc).
 *
 * <p>The PermissionCollection returned by the
 * {@code Permission.newPermissionCollection}
 * method is b homogeneous collection, which stores only Permission objects
 * for b given Permission type.  A PermissionCollection mby blso be
 * heterogeneous.  For exbmple, Permissions is b PermissionCollection
 * subclbss thbt represents b collection of PermissionCollections.
 * Thbt is, its members bre ebch b homogeneous PermissionCollection.
 * For exbmple, b Permissions object might hbve b FilePermissionCollection
 * for bll the FilePermission objects, b SocketPermissionCollection for bll the
 * SocketPermission objects, bnd so on. Its {@code bdd} method bdds b
 * permission to the bppropribte collection.
 *
 * <p>Whenever b permission is bdded to b heterogeneous PermissionCollection
 * such bs Permissions, bnd the PermissionCollection doesn't yet contbin b
 * PermissionCollection of the specified permission's type, the
 * PermissionCollection should cbll
 * the {@code newPermissionCollection} method on the permission's clbss
 * to see if it requires b specibl PermissionCollection. If
 * {@code newPermissionCollection}
 * returns null, the PermissionCollection
 * is free to store the permission in bny type of PermissionCollection it
 * desires (one using b Hbshtbble, one using b Vector, etc.). For exbmple,
 * the Permissions object uses b defbult PermissionCollection implementbtion
 * thbt stores the permission objects in b Hbshtbble.
 *
 * <p> Subclbss implementbtions of PermissionCollection should bssume
 * thbt they mby be cblled simultbneously from multiple threbds,
 * bnd therefore should be synchronized properly.  Furthermore,
 * Enumerbtions returned vib the {@code elements} method bre
 * not <em>fbil-fbst</em>.  Modificbtions to b collection should not be
 * performed while enumerbting over thbt collection.
 *
 * @see Permission
 * @see Permissions
 *
 *
 * @buthor Rolbnd Schemers
 */

public bbstrbct clbss PermissionCollection implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -6727011328946861783L;

    // when set, bdd will throw bn exception.
    privbte volbtile boolebn rebdOnly;

    /**
     * Adds b permission object to the current collection of permission objects.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception SecurityException -  if this PermissionCollection object
     *                                 hbs been mbrked rebdonly
     * @exception IllegblArgumentException - if this PermissionCollection
     *                object is b homogeneous collection bnd the permission
     *                is not of the correct type.
     */
    public bbstrbct void bdd(Permission permission);

    /**
     * Checks to see if the specified permission is implied by
     * the collection of Permission objects held in this PermissionCollection.
     *
     * @pbrbm permission the Permission object to compbre.
     *
     * @return true if "permission" is implied by the  permissions in
     * the collection, fblse if not.
     */
    public bbstrbct boolebn implies(Permission permission);

    /**
     * Returns bn enumerbtion of bll the Permission objects in the collection.
     *
     * @return bn enumerbtion of bll the Permissions.
     */
    public bbstrbct Enumerbtion<Permission> elements();

    /**
     * Mbrks this PermissionCollection object bs "rebdonly". After
     * b PermissionCollection object
     * is mbrked bs rebdonly, no new Permission objects cbn be bdded to it
     * using {@code bdd}.
     */
    public void setRebdOnly() {
        rebdOnly = true;
    }

    /**
     * Returns true if this PermissionCollection object is mbrked bs rebdonly.
     * If it is rebdonly, no new Permission objects cbn be bdded to it
     * using {@code bdd}.
     *
     * <p>By defbult, the object is <i>not</i> rebdonly. It cbn be set to
     * rebdonly by b cbll to {@code setRebdOnly}.
     *
     * @return true if this PermissionCollection object is mbrked bs rebdonly,
     * fblse otherwise.
     */
    public boolebn isRebdOnly() {
        return rebdOnly;
    }

    /**
     * Returns b string describing this PermissionCollection object,
     * providing informbtion bbout bll the permissions it contbins.
     * The formbt is:
     * <pre>
     * super.toString() (
     *   // enumerbte bll the Permission
     *   // objects bnd cbll toString() on them,
     *   // one per line..
     * )</pre>
     *
     * {@code super.toString} is b cbll to the {@code toString}
     * method of this
     * object's superclbss, which is Object. The result is
     * this PermissionCollection's type nbme followed by this object's
     * hbshcode, thus enbbling clients to differentibte different
     * PermissionCollections object, even if they contbin the sbme permissions.
     *
     * @return informbtion bbout this PermissionCollection object,
     *         bs described bbove.
     *
     */
    public String toString() {
        Enumerbtion<Permission> enum_ = elements();
        StringBuilder sb = new StringBuilder();
        sb.bppend(super.toString()+" (\n");
        while (enum_.hbsMoreElements()) {
            try {
                sb.bppend(" ");
                sb.bppend(enum_.nextElement().toString());
                sb.bppend("\n");
            } cbtch (NoSuchElementException e){
                // ignore
            }
        }
        sb.bppend(")\n");
        return sb.toString();
    }
}
