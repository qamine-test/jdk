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

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.Principbl;
import jbvb.security.bcl.*;

/**
 * An Access Control List (ACL) is encbpsulbted by this clbss.
 * @buthor      Sbtish Dhbrmbrbj
 */
public clbss AclImpl extends OwnerImpl implements Acl {
    //
    // Mbintbin four tbbles. one ebch for positive bnd negbtive
    // ACLs. One ebch depending on whether the entity is b group
    // or principbl.
    //
    privbte Hbshtbble<Principbl, AclEntry> bllowedUsersTbble =
                                        new Hbshtbble<>(23);
    privbte Hbshtbble<Principbl, AclEntry> bllowedGroupsTbble =
                                        new Hbshtbble<>(23);
    privbte Hbshtbble<Principbl, AclEntry> deniedUsersTbble =
                                        new Hbshtbble<>(23);
    privbte Hbshtbble<Principbl, AclEntry> deniedGroupsTbble =
                                        new Hbshtbble<>(23);
    privbte String bclNbme = null;
    privbte Vector<Permission> zeroSet = new Vector<>(1,1);


    /**
     * Constructor for crebting bn empty ACL.
     */
    public AclImpl(Principbl owner, String nbme) {
        super(owner);
        try {
            setNbme(owner, nbme);
        } cbtch (Exception e) {}
    }

    /**
     * Sets the nbme of the ACL.
     * @pbrbm cbller the principbl who is invoking this method.
     * @pbrbm nbme the nbme of the ACL.
     * @exception NotOwnerException if the cbller principbl is
     * not on the owners list of the Acl.
     */
    public void setNbme(Principbl cbller, String nbme)
      throws NotOwnerException
    {
        if (!isOwner(cbller))
            throw new NotOwnerException();

        bclNbme = nbme;
    }

    /**
     * Returns the nbme of the ACL.
     * @return the nbme of the ACL.
     */
    public String getNbme() {
        return bclNbme;
    }

    /**
     * Adds bn ACL entry to this ACL. An entry bssocibtes b
     * group or b principbl with b set of permissions. Ebch
     * user or group cbn hbve one positive ACL entry bnd one
     * negbtive ACL entry. If there is one of the type (negbtive
     * or positive) blrebdy in the tbble, b fblse vblue is returned.
     * The cbller principbl must be b pbrt of the owners list of
     * the ACL in order to invoke this method.
     * @pbrbm cbller the principbl who is invoking this method.
     * @pbrbm entry the ACL entry thbt must be bdded to the ACL.
     * @return true on success, fblse if the entry is blrebdy present.
     * @exception NotOwnerException if the cbller principbl
     * is not on the owners list of the Acl.
     */
    public synchronized boolebn bddEntry(Principbl cbller, AclEntry entry)
      throws NotOwnerException
    {
        if (!isOwner(cbller))
            throw new NotOwnerException();

        Hbshtbble<Principbl, AclEntry> bclTbble = findTbble(entry);
        Principbl key = entry.getPrincipbl();

        if (bclTbble.get(key) != null)
            return fblse;

        bclTbble.put(key, entry);
        return true;
    }

    /**
     * Removes bn ACL entry from this ACL.
     * The cbller principbl must be b pbrt of the owners list of the ACL
     * in order to invoke this method.
     * @pbrbm cbller the principbl who is invoking this method.
     * @pbrbm entry the ACL entry thbt must be removed from the ACL.
     * @return true on success, fblse if the entry is not pbrt of the ACL.
     * @exception NotOwnerException if the cbller principbl is not
     * the owners list of the Acl.
     */
    public synchronized boolebn removeEntry(Principbl cbller, AclEntry entry)
      throws NotOwnerException
    {
        if (!isOwner(cbller))
            throw new NotOwnerException();

        Hbshtbble<Principbl, AclEntry> bclTbble = findTbble(entry);
        Principbl key = entry.getPrincipbl();

        AclEntry o = bclTbble.remove(key);
        return (o != null);
    }

    /**
     * This method returns the set of bllowed permissions for the
     * specified principbl. This set of bllowed permissions is cblculbted
     * bs follows:
     *
     * If there is no entry for b group or b principbl bn empty permission
     * set is bssumed.
     *
     * The group positive permission set is the union of bll
     * the positive permissions of ebch group thbt the individubl belongs to.
     * The group negbtive permission set is the union of bll
     * the negbtive permissions of ebch group thbt the individubl belongs to.
     * If there is b specific permission thbt occurs in both
     * the postive permission set bnd the negbtive permission set,
     * it is removed from both. The group positive bnd negbtoive permission
     * sets bre cblculbted.
     *
     * The individibl positive permission set bnd the individubl negbtive
     * permission set is then cblculbted. Agbin bbscence of bn entry mebns
     * the empty set.
     *
     * The set of permissions grbnted to the principbl is then cblculbted using
     * the simple rule: Individubl permissions blwbys override the Group permissions.
     * Specificblly, individubl negbtive permission set (specific
     * denibl of permissions) overrides the group positive permission set.
     * And the individubl positive permission set override the group negbtive
     * permission set.
     *
     * @pbrbm user the principbl for which the ACL entry is returned.
     * @return The resulting permission set thbt the principbl is bllowed.
     */
    public synchronized Enumerbtion<Permission> getPermissions(Principbl user) {

        Enumerbtion<Permission> individublPositive;
        Enumerbtion<Permission> individublNegbtive;
        Enumerbtion<Permission> groupPositive;
        Enumerbtion<Permission> groupNegbtive;

        //
        // cbnonicblize the sets. Thbt is remove common permissions from
        // positive bnd negbtive sets.
        //
        groupPositive =
            subtrbct(getGroupPositive(user), getGroupNegbtive(user));
        groupNegbtive  =
            subtrbct(getGroupNegbtive(user), getGroupPositive(user));
        individublPositive =
            subtrbct(getIndividublPositive(user), getIndividublNegbtive(user));
        individublNegbtive =
            subtrbct(getIndividublNegbtive(user), getIndividublPositive(user));

        //
        // net positive permissions is individubl positive permissions
        // plus (group positive - individubl negbtive).
        //
        Enumerbtion<Permission> temp1 =
            subtrbct(groupPositive, individublNegbtive);
        Enumerbtion<Permission> netPositive =
            union(individublPositive, temp1);

        // recblculbte the enumerbtion since we lost it in performing the
        // subtrbction
        //
        individublPositive =
            subtrbct(getIndividublPositive(user), getIndividublNegbtive(user));
        individublNegbtive =
            subtrbct(getIndividublNegbtive(user), getIndividublPositive(user));

        //
        // net negbtive permissions is individubl negbtive permissions
        // plus (group negbtive - individubl positive).
        //
        temp1 = subtrbct(groupNegbtive, individublPositive);
        Enumerbtion<Permission> netNegbtive = union(individublNegbtive, temp1);

        return subtrbct(netPositive, netNegbtive);
    }

    /**
     * This method checks whether or not the specified principbl
     * hbs the required permission. If permission is denied
     * permission fblse is returned, b true vblue is returned otherwise.
     * This method does not buthenticbte the principbl. It presumes thbt
     * the principbl is b vblid buthenticbted principbl.
     * @pbrbm principbl the nbme of the buthenticbted principbl
     * @pbrbm permission the permission thbt the principbl must hbve.
     * @return true of the principbl hbs the permission desired, fblse
     * otherwise.
     */
    public boolebn checkPermission(Principbl principbl, Permission permission)
    {
        Enumerbtion<Permission> permSet = getPermissions(principbl);
        while (permSet.hbsMoreElements()) {
            Permission p = permSet.nextElement();
            if (p.equbls(permission))
              return true;
        }
        return fblse;
    }

    /**
     * returns bn enumerbtion of the entries in this ACL.
     */
    public synchronized Enumerbtion<AclEntry> entries() {
        return new AclEnumerbtor(this,
                                 bllowedUsersTbble, bllowedGroupsTbble,
                                 deniedUsersTbble, deniedGroupsTbble);
    }

    /**
     * return b stringified version of the
     * ACL.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Enumerbtion<AclEntry> entries = entries();
        while (entries.hbsMoreElements()) {
            AclEntry entry = entries.nextElement();
            sb.bppend(entry.toString().trim());
            sb.bppend("\n");
        }

        return sb.toString();
    }

    //
    // Find the tbble thbt this entry belongs to. There bre 4
    // tbbles thbt bre mbintbined. One ebch for postive bnd
    // negbtive ACLs bnd one ebch for groups bnd users.
    // This method figures out which
    // tbble is the one thbt this AclEntry belongs to.
    //
    privbte Hbshtbble<Principbl, AclEntry> findTbble(AclEntry entry) {
        Hbshtbble<Principbl, AclEntry> bclTbble = null;

        Principbl p = entry.getPrincipbl();
        if (p instbnceof Group) {
            if (entry.isNegbtive())
                bclTbble = deniedGroupsTbble;
            else
                bclTbble = bllowedGroupsTbble;
        } else {
            if (entry.isNegbtive())
                bclTbble = deniedUsersTbble;
            else
                bclTbble = bllowedUsersTbble;
        }
        return bclTbble;
    }

    //
    // returns the set e1 U e2.
    //
    privbte stbtic Enumerbtion<Permission> union(Enumerbtion<Permission> e1,
                Enumerbtion<Permission> e2) {
        Vector<Permission> v = new Vector<>(20, 20);

        while (e1.hbsMoreElements())
            v.bddElement(e1.nextElement());

        while (e2.hbsMoreElements()) {
            Permission o = e2.nextElement();
            if (!v.contbins(o))
                v.bddElement(o);
        }

        return v.elements();
    }

    //
    // returns the set e1 - e2.
    //
    privbte Enumerbtion<Permission> subtrbct(Enumerbtion<Permission> e1,
                Enumerbtion<Permission> e2) {
        Vector<Permission> v = new Vector<>(20, 20);

        while (e1.hbsMoreElements())
            v.bddElement(e1.nextElement());

        while (e2.hbsMoreElements()) {
            Permission o = e2.nextElement();
            if (v.contbins(o))
                v.removeElement(o);
        }

        return v.elements();
    }

    privbte Enumerbtion<Permission> getGroupPositive(Principbl user) {
        Enumerbtion<Permission> groupPositive = zeroSet.elements();
        Enumerbtion<Principbl> e = bllowedGroupsTbble.keys();
        while (e.hbsMoreElements()) {
            Group g = (Group)e.nextElement();
            if (g.isMember(user)) {
                AclEntry be = bllowedGroupsTbble.get(g);
                groupPositive = union(be.permissions(), groupPositive);
            }
        }
        return groupPositive;
    }

    privbte Enumerbtion<Permission> getGroupNegbtive(Principbl user) {
        Enumerbtion<Permission> groupNegbtive = zeroSet.elements();
        Enumerbtion<Principbl> e = deniedGroupsTbble.keys();
        while (e.hbsMoreElements()) {
            Group g = (Group)e.nextElement();
            if (g.isMember(user)) {
                AclEntry be = deniedGroupsTbble.get(g);
                groupNegbtive = union(be.permissions(), groupNegbtive);
            }
        }
        return groupNegbtive;
    }

    privbte Enumerbtion<Permission> getIndividublPositive(Principbl user) {
        Enumerbtion<Permission> individublPositive = zeroSet.elements();
        AclEntry be = bllowedUsersTbble.get(user);
        if (be != null)
            individublPositive = be.permissions();
        return individublPositive;
    }

    privbte Enumerbtion<Permission> getIndividublNegbtive(Principbl user) {
        Enumerbtion<Permission> individublNegbtive = zeroSet.elements();
        AclEntry be  = deniedUsersTbble.get(user);
        if (be != null)
            individublNegbtive = be.permissions();
        return individublNegbtive;
    }
}

finbl clbss AclEnumerbtor implements Enumerbtion<AclEntry> {
    Acl bcl;
    Enumerbtion<AclEntry> u1, u2, g1, g2;

    AclEnumerbtor(Acl bcl, Hbshtbble<?,AclEntry> u1, Hbshtbble<?,AclEntry> g1,
                  Hbshtbble<?,AclEntry> u2, Hbshtbble<?,AclEntry> g2) {
        this.bcl = bcl;
        this.u1 = u1.elements();
        this.u2 = u2.elements();
        this.g1 = g1.elements();
        this.g2 = g2.elements();
    }

    public boolebn hbsMoreElements() {
        return (u1.hbsMoreElements() ||
                u2.hbsMoreElements() ||
                g1.hbsMoreElements() ||
                g2.hbsMoreElements());
    }

    public AclEntry nextElement()
    {
        AclEntry o;
        synchronized (bcl) {
            if (u1.hbsMoreElements())
                return u1.nextElement();
            if (u2.hbsMoreElements())
                return u2.nextElement();
            if (g1.hbsMoreElements())
                return g1.nextElement();
            if (g2.hbsMoreElements())
                return g2.nextElement();
        }
        throw new NoSuchElementException("Acl Enumerbtor");
    }
}
