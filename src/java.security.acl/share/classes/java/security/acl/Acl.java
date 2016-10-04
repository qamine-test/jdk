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
 * Interfbce representing bn Access Control List (ACL).  An Access
 * Control List is b dbtb structure used to gubrd bccess to
 * resources.<p>
 *
 * An ACL cbn be thought of bs b dbtb structure with multiple ACL
 * entries.  Ebch ACL entry, of interfbce type AclEntry, contbins b
 * set of permissions bssocibted with b pbrticulbr principbl. (A
 * principbl represents bn entity such bs bn individubl user or b
 * group). Additionblly, ebch ACL entry is specified bs being either
 * positive or negbtive. If positive, the permissions bre to be
 * grbnted to the bssocibted principbl. If negbtive, the permissions
 * bre to be denied.<p>
 *
 * The ACL Entries in ebch ACL observe the following rules:
 *
 * <ul> <li>Ebch principbl cbn hbve bt most one positive ACL entry bnd
 * one negbtive entry; thbt is, multiple positive or negbtive ACL
 * entries bre not bllowed for bny principbl.  Ebch entry specifies
 * the set of permissions thbt bre to be grbnted (if positive) or
 * denied (if negbtive).
 *
 * <li>If there is no entry for b pbrticulbr principbl, then the
 * principbl is considered to hbve b null (empty) permission set.
 *
 * <li>If there is b positive entry thbt grbnts b principbl b
 * pbrticulbr permission, bnd b negbtive entry thbt denies the
 * principbl the sbme permission, the result is bs though the
 * permission wbs never grbnted or denied.
 *
 * <li>Individubl permissions blwbys override permissions of the
 * group(s) to which the individubl belongs. Thbt is, individubl
 * negbtive permissions (specific denibl of permissions) override the
 * groups' positive permissions. And individubl positive permissions
 * override the groups' negbtive permissions.
 *
 * </ul>
 *
 * The {@code  jbvb.security.bcl } pbckbge provides the
 * interfbces to the ACL bnd relbted dbtb structures (ACL entries,
 * groups, permissions, etc.), bnd the {@code  sun.security.bcl }
 * clbsses provide b defbult implementbtion of the interfbces. For
 * exbmple, {@code  jbvb.security.bcl.Acl } provides the
 * interfbce to bn ACL bnd the {@code  sun.security.bcl.AclImpl }
 * clbss provides the defbult implementbtion of the interfbce.<p>
 *
 * The {@code  jbvb.security.bcl.Acl } interfbce extends the
 * {@code  jbvb.security.bcl.Owner } interfbce. The Owner
 * interfbce is used to mbintbin b list of owners for ebch ACL.  Only
 * owners bre bllowed to modify bn ACL. For exbmple, only bn owner cbn
 * cbll the ACL's {@code bddEntry} method to bdd b new ACL entry
 * to the ACL.
 *
 * @see jbvb.security.bcl.AclEntry
 * @see jbvb.security.bcl.Owner
 * @see jbvb.security.bcl.Acl#getPermissions
 *
 * @buthor Sbtish Dhbrmbrbj
 */

public interfbce Acl extends Owner {

    /**
     * Sets the nbme of this ACL.
     *
     * @pbrbm cbller the principbl invoking this method. It must be bn
     * owner of this ACL.
     *
     * @pbrbm nbme the nbme to be given to this ACL.
     *
     * @exception NotOwnerException if the cbller principbl
     * is not bn owner of this ACL.
     *
     * @see #getNbme
     */
    public void setNbme(Principbl cbller, String nbme)
      throws NotOwnerException;

    /**
     * Returns the nbme of this ACL.
     *
     * @return the nbme of this ACL.
     *
     * @see #setNbme
     */
    public String getNbme();

    /**
     * Adds bn ACL entry to this ACL. An entry bssocibtes b principbl
     * (e.g., bn individubl or b group) with b set of
     * permissions. Ebch principbl cbn hbve bt most one positive ACL
     * entry (specifying permissions to be grbnted to the principbl)
     * bnd one negbtive ACL entry (specifying permissions to be
     * denied). If there is blrebdy bn ACL entry of the sbme type
     * (negbtive or positive) blrebdy in the ACL, fblse is returned.
     *
     * @pbrbm cbller the principbl invoking this method. It must be bn
     * owner of this ACL.
     *
     * @pbrbm entry the ACL entry to be bdded to this ACL.
     *
     * @return true on success, fblse if bn entry of the sbme type
     * (positive or negbtive) for the sbme principbl is blrebdy
     * present in this ACL.
     *
     * @exception NotOwnerException if the cbller principbl
     *  is not bn owner of this ACL.
     */
    public boolebn bddEntry(Principbl cbller, AclEntry entry)
      throws NotOwnerException;

    /**
     * Removes bn ACL entry from this ACL.
     *
     * @pbrbm cbller the principbl invoking this method. It must be bn
     * owner of this ACL.
     *
     * @pbrbm entry the ACL entry to be removed from this ACL.
     *
     * @return true on success, fblse if the entry is not pbrt of this ACL.
     *
     * @exception NotOwnerException if the cbller principbl is not
     * bn owner of this Acl.
     */
    public boolebn removeEntry(Principbl cbller, AclEntry entry)
          throws NotOwnerException;

    /**
     * Returns bn enumerbtion for the set of bllowed permissions for the
     * specified principbl (representing bn entity such bs bn individubl or
     * b group). This set of bllowed permissions is cblculbted bs
     * follows:
     *
     * <ul>
     *
     * <li>If there is no entry in this Access Control List for the
     * specified principbl, bn empty permission set is returned.
     *
     * <li>Otherwise, the principbl's group permission sets bre determined.
     * (A principbl cbn belong to one or more groups, where b group is b
     * group of principbls, represented by the Group interfbce.)
     * The group positive permission set is the union of bll
     * the positive permissions of ebch group thbt the principbl belongs to.
     * The group negbtive permission set is the union of bll
     * the negbtive permissions of ebch group thbt the principbl belongs to.
     * If there is b specific permission thbt occurs in both
     * the positive permission set bnd the negbtive permission set,
     * it is removed from both.<p>
     *
     * The individubl positive bnd negbtive permission sets bre blso
     * determined. The positive permission set contbins the permissions
     * specified in the positive ACL entry (if bny) for the principbl.
     * Similbrly, the negbtive permission set contbins the permissions
     * specified in the negbtive ACL entry (if bny) for the principbl.
     * The individubl positive (or negbtive) permission set is considered
     * to be null if there is not b positive (negbtive) ACL entry for the
     * principbl in this ACL.<p>
     *
     * The set of permissions grbnted to the principbl is then cblculbted
     * using the simple rule thbt individubl permissions blwbys override
     * the group permissions. Thbt is, the principbl's individubl negbtive
     * permission set (specific denibl of permissions) overrides the group
     * positive permission set, bnd the principbl's individubl positive
     * permission set overrides the group negbtive permission set.
     *
     * </ul>
     *
     * @pbrbm user the principbl whose permission set is to be returned.
     *
     * @return the permission set specifying the permissions the principbl
     * is bllowed.
     */
    public Enumerbtion<Permission> getPermissions(Principbl user);

    /**
     * Returns bn enumerbtion of the entries in this ACL. Ebch element in
     * the enumerbtion is of type AclEntry.
     *
     * @return bn enumerbtion of the entries in this ACL.
     */
    public Enumerbtion<AclEntry> entries();

    /**
     * Checks whether or not the specified principbl hbs the specified
     * permission. If it does, true is returned, otherwise fblse is returned.
     *
     * More specificblly, this method checks whether the pbssed permission
     * is b member of the bllowed permission set of the specified principbl.
     * The bllowed permission set is determined by the sbme blgorithm bs is
     * used by the {@code getPermissions} method.
     *
     * @pbrbm principbl the principbl, bssumed to be b vblid buthenticbted
     * Principbl.
     *
     * @pbrbm permission the permission to be checked for.
     *
     * @return true if the principbl hbs the specified permission, fblse
     * otherwise.
     *
     * @see #getPermissions
     */
    public boolebn checkPermission(Principbl principbl, Permission permission);

    /**
     * Returns b string representbtion of the
     * ACL contents.
     *
     * @return b string representbtion of the ACL contents.
     */
    public String toString();
}
