/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;
import sun.security.util.SecurityConstbnts;

/**
 * The AllPermission is b permission thbt implies bll other permissions.
 * <p>
 * <b>Note:</b> Grbnting AllPermission should be done with extreme cbre,
 * bs it implies bll other permissions. Thus, it grbnts code the bbility
 * to run with security
 * disbbled.  Extreme cbution should be tbken before grbnting such
 * b permission to code.  This permission should be used only during testing,
 * or in extremely rbre cbses where bn bpplicbtion or bpplet is
 * completely trusted bnd bdding the necessbry permissions to the policy
 * is prohibitively cumbersome.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.AccessController
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl exclude
 */

public finbl clbss AllPermission extends Permission {

    privbte stbtic finbl long seriblVersionUID = -2916474571451318075L;

    /**
     * Crebtes b new AllPermission object.
     */
    public AllPermission() {
        super("<bll permissions>");
    }


    /**
     * Crebtes b new AllPermission object. This
     * constructor exists for use by the {@code Policy} object
     * to instbntibte new Permission objects.
     *
     * @pbrbm nbme ignored
     * @pbrbm bctions ignored.
     */
    public AllPermission(String nbme, String bctions) {
        this();
    }

    /**
     * Checks if the specified permission is "implied" by
     * this object. This method blwbys returns true.
     *
     * @pbrbm p the permission to check bgbinst.
     *
     * @return return
     */
    public boolebn implies(Permission p) {
         return true;
    }

    /**
     * Checks two AllPermission objects for equblity. Two AllPermission
     * objects bre blwbys equbl.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if <i>obj</i> is bn AllPermission, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        return (obj instbnceof AllPermission);
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */

    public int hbshCode() {
        return 1;
    }

    /**
     * Returns the cbnonicbl string representbtion of the bctions.
     *
     * @return the bctions.
     */
    public String getActions() {
        return "<bll bctions>";
    }

    /**
     * Returns b new PermissionCollection object for storing AllPermission
     * objects.
     * <p>
     *
     * @return b new PermissionCollection object suitbble for
     * storing AllPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new AllPermissionCollection();
    }

}

/**
 * A AllPermissionCollection stores b collection
 * of AllPermission permissions. AllPermission objects
 * must be stored in b mbnner thbt bllows them to be inserted in bny
 * order, but enbble the implies function to evblubte the implies
 * method in bn efficient (bnd consistent) mbnner.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 */

finbl clbss AllPermissionCollection
    extends PermissionCollection
    implements jbvb.io.Seriblizbble
{

    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = -4023755556366636806L;

    privbte boolebn bll_bllowed; // true if bny bll permissions hbve been bdded

    /**
     * Crebte bn empty AllPermissions object.
     *
     */

    public AllPermissionCollection() {
        bll_bllowed = fblse;
    }

    /**
     * Adds b permission to the AllPermissions. The key for the hbsh is
     * permission.pbth.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       AllPermission
     *
     * @exception SecurityException - if this AllPermissionCollection object
     *                                hbs been mbrked rebdonly
     */

    public void bdd(Permission permission) {
        if (! (permission instbnceof AllPermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException("bttempt to bdd b Permission to b rebdonly PermissionCollection");

        bll_bllowed = true; // No sync; stbleness OK
    }

    /**
     * Check bnd see if this set of permissions implies the permissions
     * expressed in "permission".
     *
     * @pbrbm permission the Permission object to compbre
     *
     * @return blwbys returns true.
     */

    public boolebn implies(Permission permission) {
        return bll_bllowed; // No sync; stbleness OK
    }

    /**
     * Returns bn enumerbtion of bll the AllPermission objects in the
     * contbiner.
     *
     * @return bn enumerbtion of bll the AllPermission objects.
     */
    public Enumerbtion<Permission> elements() {
        return new Enumerbtion<Permission>() {
            privbte boolebn hbsMore = bll_bllowed;

            public boolebn hbsMoreElements() {
                return hbsMore;
            }

            public Permission nextElement() {
                hbsMore = fblse;
                return SecurityConstbnts.ALL_PERMISSION;
            }
        };
    }
}
