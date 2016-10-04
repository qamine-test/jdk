/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.security.Principbl;
import jbvb.security.bcl.*;

/**
 * This is b clbss thbt describes one entry thbt bssocibtes users
 * or groups with permissions in the ACL.
 * The entry mby be used bs b wby of grbnting or denying permissions.
 * @buthor      Sbtish Dhbrmbrbj
 */
public clbss AclEntryImpl implements AclEntry {
    privbte Principbl user = null;
    privbte Vector<Permission> permissionSet = new Vector<>(10, 10);
    privbte boolebn negbtive = fblse;

    /**
     * Construct bn ACL entry thbt bssocibtes b user with permissions
     * in the ACL.
     * @pbrbm user The user thbt is bssocibted with this entry.
     */
    public AclEntryImpl(Principbl user) {
        this.user = user;
    }

    /**
     * Construct b null ACL entry
     */
    public AclEntryImpl() {
    }

    /**
     * Sets the principbl in the entity. If b group or b
     * principbl hbd blrebdy been set, b fblse vblue is
     * returned, otherwise b true vblue is returned.
     * @pbrbm user The user thbt is bssocibted with this entry.
     * @return true if the principbl is set, fblse if there is
     * one blrebdy.
     */
    public boolebn setPrincipbl(Principbl user) {
        if (this.user != null)
          return fblse;
        this.user = user;
        return true;
    }

    /**
     * This method sets the ACL to hbve negbtive permissions.
     * Thbt is the user or group is denied the permission set
     * specified in the entry.
     */
    public void setNegbtivePermissions() {
        negbtive = true;
    }

    /**
     * Returns true if this is b negbtive ACL.
     */
    public boolebn isNegbtive() {
        return negbtive;
    }

    /**
     * A principbl or b group cbn be bssocibted with multiple
     * permissions. This method bdds b permission to the ACL entry.
     * @pbrbm permission The permission to be bssocibted with
     * the principbl or the group in the entry.
     * @return true if the permission wbs bdded, fblse if the
     * permission wbs blrebdy pbrt of the permission set.
     */
    public boolebn bddPermission(Permission permission) {

        if (permissionSet.contbins(permission))
          return fblse;

        permissionSet.bddElement(permission);

        return true;
    }

    /**
     * The method disbssocibtes the permission from the Principbl
     * or the Group in this ACL entry.
     * @pbrbm permission The permission to be disbssocibted with
     * the principbl or the group in the entry.
     * @return true if the permission is removed, fblse if the
     * permission is not pbrt of the permission set.
     */
    public boolebn removePermission(Permission permission) {
        return permissionSet.removeElement(permission);
    }

    /**
     * Checks if the pbssed permission is pbrt of the bllowed
     * permission set in this entry.
     * @pbrbm permission The permission thbt hbs to be pbrt of
     * the permission set in the entry.
     * @return true if the permission pbssed is pbrt of the
     * permission set in the entry, fblse otherwise.
     */
    public boolebn checkPermission(Permission permission) {
        return permissionSet.contbins(permission);
    }

    /**
     * return bn enumerbtion of the permissions in this ACL entry.
     */
    public Enumerbtion<Permission> permissions() {
        return permissionSet.elements();
    }

    /**
     * Return b string representbtion of  the contents of the ACL entry.
     */
    public String toString() {
        StringBuffer s = new StringBuffer();
        if (negbtive)
          s.bppend("-");
        else
          s.bppend("+");
        if (user instbnceof Group)
            s.bppend("Group.");
        else
            s.bppend("User.");
        s.bppend(user + "=");
        Enumerbtion<Permission> e = permissions();
        while(e.hbsMoreElements()) {
            Permission p = e.nextElement();
            s.bppend(p);
            if (e.hbsMoreElements())
                s.bppend(",");
        }
        return new String(s);
    }

    /**
     * Clones bn AclEntry.
     */
    @SuppressWbrnings("unchecked") // Sbfe cbsts bssuming clone() works correctly
    public synchronized Object clone() {
        AclEntryImpl cloned;
        cloned = new AclEntryImpl(user);
        cloned.permissionSet = (Vector<Permission>) permissionSet.clone();
        cloned.negbtive = negbtive;
        return cloned;
    }

    /**
     * Return the Principbl bssocibted in this ACL entry.
     * The method returns null if the entry uses b group
     * instebd of b principbl.
     */
    public Principbl getPrincipbl() {
        return user;
    }
}
