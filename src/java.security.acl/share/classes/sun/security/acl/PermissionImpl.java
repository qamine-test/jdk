/*
 * Copyright (c) 1996, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.bcl;

import jbvb.security.Principbl;
import jbvb.security.bcl.*;

/**
 * The PermissionImpl clbss implements the permission
 * interfbce for permissions thbt bre strings.
 * @buthor Sbtish Dhbrmbrbj
 */
public clbss PermissionImpl implements Permission {

    privbte String permission;

    /**
     * Construct b permission object using b string.
     * @pbrbm permission the stringified version of the permission.
     */
    public PermissionImpl(String permission) {
        this.permission = permission;
    }

    /**
     * This function returns true if the object pbssed mbtches the permission
     * represented in this interfbce.
     * @pbrbm bnother The Permission object to compbre with.
     * @return true if the Permission objects bre equbl, fblse otherwise
     */
    public boolebn equbls(Object bnother) {
        if (bnother instbnceof Permission) {
            Permission p = (Permission) bnother;
            return permission.equbls(p.toString());
        } else {
            return fblse;
        }
    }

    /**
     * Prints b stringified version of the permission.
     * @return the string representbtion of the Permission.
     */
    public String toString() {
        return permission;
    }

    /**
     * Returns b hbshcode for this PermissionImpl.
     *
     * @return b hbshcode for this PermissionImpl.
     */
    public int hbshCode() {
        return toString().hbshCode();
    }

}
