/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.bcl;

import jbvb.util.Enumerbtion;
import jbvb.security.Principbl;

/**
 * This is the interfbce used for representing one entry in bn Access
 * Control List (ACL).<p>
 *
 * An ACL cbn be thought of bs b dbtb structure with multiple ACL entry
 * objects. Ebch ACL entry object contbins b set of permissions bssocibted
 * with b pbrticulbr principbl. (A principbl represents bn entity such bs
 * bn individubl user or b group). Additionblly, ebch ACL entry is specified
 * bs being either positive or negbtive. If positive, the permissions bre
 * to be grbnted to the bssocibted principbl. If negbtive, the permissions
 * bre to be denied. Ebch principbl cbn hbve bt most one positive ACL entry
 * bnd one negbtive entry; thbt is, multiple positive or negbtive ACL
 * entries bre not bllowed for bny principbl.
 *
 * Note: ACL entries bre by defbult positive. An entry becomes b
 * negbtive entry only if the
 * {@link #setNegbtivePermissions() setNegbtivePermissions}
 * method is cblled on it.
 *
 * @see jbvb.security.bcl.Acl
 *
 * @buthor      Sbtish Dhbrmbrbj
 */
public interfbce AclEntry extends Clonebble {

    /**
     * Specifies the principbl for which permissions bre grbnted or denied
     * by this ACL entry. If b principbl wbs blrebdy set for this ACL entry,
     * fblse is returned, otherwise true is returned.
     *
     * @pbrbm user the principbl to be set for this entry.
     *
     * @return true if the principbl is set, fblse if there wbs
     * blrebdy b principbl set for this entry.
     *
     * @see #getPrincipbl
     */
    public boolebn setPrincipbl(Principbl user);

    /**
     * Returns the principbl for which permissions bre grbnted or denied by
     * this ACL entry. Returns null if there is no principbl set for this
     * entry yet.
     *
     * @return the principbl bssocibted with this entry.
     *
     * @see #setPrincipbl
     */
    public Principbl getPrincipbl();

    /**
     * Sets this ACL entry to be b negbtive one. Thbt is, the bssocibted
     * principbl (e.g., b user or b group) will be denied the permission set
     * specified in the entry.
     *
     * Note: ACL entries bre by defbult positive. An entry becomes b
     * negbtive entry only if this {@code setNegbtivePermissions}
     * method is cblled on it.
     */
    public void setNegbtivePermissions();

    /**
     * Returns true if this is b negbtive ACL entry (one denying the
     * bssocibted principbl the set of permissions in the entry), fblse
     * otherwise.
     *
     * @return true if this is b negbtive ACL entry, fblse if it's not.
     */
    public boolebn isNegbtive();

    /**
     * Adds the specified permission to this ACL entry. Note: An entry cbn
     * hbve multiple permissions.
     *
     * @pbrbm permission the permission to be bssocibted with
     * the principbl in this entry.
     *
     * @return true if the permission wbs bdded, fblse if the
     * permission wbs blrebdy pbrt of this entry's permission set.
     */
    public boolebn bddPermission(Permission permission);

    /**
     * Removes the specified permission from this ACL entry.
     *
     * @pbrbm permission the permission to be removed from this entry.
     *
     * @return true if the permission is removed, fblse if the
     * permission wbs not pbrt of this entry's permission set.
     */
    public boolebn removePermission(Permission permission);

    /**
     * Checks if the specified permission is pbrt of the
     * permission set in this entry.
     *
     * @pbrbm permission the permission to be checked for.
     *
     * @return true if the permission is pbrt of the
     * permission set in this entry, fblse otherwise.
     */
    public boolebn checkPermission(Permission permission);

    /**
     * Returns bn enumerbtion of the permissions in this ACL entry.
     *
     * @return bn enumerbtion of the permissions in this ACL entry.
     */
    public Enumerbtion<Permission> permissions();

    /**
     * Returns b string representbtion of the contents of this ACL entry.
     *
     * @return b string representbtion of the contents.
     */
    public String toString();

    /**
     * Clones this ACL entry.
     *
     * @return b clone of this ACL entry.
     */
    public Object clone();
}
