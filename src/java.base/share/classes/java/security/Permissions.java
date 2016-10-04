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

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.NoSuchElementException;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Iterbtor;
import jbvb.util.Collections;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;


/**
 * This clbss represents b heterogeneous collection of Permissions. Thbt is,
 * it contbins different types of Permission objects, orgbnized into
 * PermissionCollections. For exbmple, if bny
 * {@code jbvb.io.FilePermission} objects bre bdded to bn instbnce of
 * this clbss, they bre bll stored in b single
 * PermissionCollection. It is the PermissionCollection returned by b cbll to
 * the {@code newPermissionCollection} method in the FilePermission clbss.
 * Similbrly, bny {@code jbvb.lbng.RuntimePermission} objects bre
 * stored in the PermissionCollection returned by b cbll to the
 * {@code newPermissionCollection} method in the
 * RuntimePermission clbss. Thus, this clbss represents b collection of
 * PermissionCollections.
 *
 * <p>When the {@code bdd} method is cblled to bdd b Permission, the
 * Permission is stored in the bppropribte PermissionCollection. If no such
 * collection exists yet, the Permission object's clbss is determined bnd the
 * {@code newPermissionCollection} method is cblled on thbt clbss to crebte
 * the PermissionCollection bnd bdd it to the Permissions object. If
 * {@code newPermissionCollection} returns null, then b defbult
 * PermissionCollection thbt uses b hbshtbble will be crebted bnd used. Ebch
 * hbshtbble entry stores b Permission object bs both the key bnd the vblue.
 *
 * <p> Enumerbtions returned vib the {@code elements} method bre
 * not <em>fbil-fbst</em>.  Modificbtions to b collection should not be
 * performed while enumerbting over thbt collection.
 *
 * @see Permission
 * @see PermissionCollection
 * @see AllPermission
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 *
 * @seribl exclude
 */

public finbl clbss Permissions extends PermissionCollection
implements Seriblizbble
{
    /**
     * Key is permissions Clbss, vblue is PermissionCollection for thbt clbss.
     * Not seriblized; see seriblizbtion section bt end of clbss.
     */
    privbte trbnsient Mbp<Clbss<?>, PermissionCollection> permsMbp;

    // optimizbtion. keep trbck of whether unresolved permissions need to be
    // checked
    privbte trbnsient boolebn hbsUnresolved = fblse;

    // optimizbtion. keep trbck of the AllPermission collection
    // - pbckbge privbte for ProtectionDombin optimizbtion
    PermissionCollection bllPermission;

    /**
     * Crebtes b new Permissions object contbining no PermissionCollections.
     */
    public Permissions() {
        permsMbp = new HbshMbp<Clbss<?>, PermissionCollection>(11);
        bllPermission = null;
    }

    /**
     * Adds b permission object to the PermissionCollection for the clbss the
     * permission belongs to. For exbmple, if <i>permission</i> is b
     * FilePermission, it is bdded to the FilePermissionCollection stored
     * in this Permissions object.
     *
     * This method crebtes
     * b new PermissionCollection object (bnd bdds the permission to it)
     * if bn bppropribte collection does not yet exist. <p>
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception SecurityException if this Permissions object is
     * mbrked bs rebdonly.
     *
     * @see PermissionCollection#isRebdOnly()
     */

    public void bdd(Permission permission) {
        if (isRebdOnly())
            throw new SecurityException(
              "bttempt to bdd b Permission to b rebdonly Permissions object");

        PermissionCollection pc;

        synchronized (this) {
            pc = getPermissionCollection(permission, true);
            pc.bdd(permission);
        }

        // No sync; stbleness -> optimizbtions delbyed, which is OK
        if (permission instbnceof AllPermission) {
            bllPermission = pc;
        }
        if (permission instbnceof UnresolvedPermission) {
            hbsUnresolved = true;
        }
    }

    /**
     * Checks to see if this object's PermissionCollection for permissions of
     * the specified permission's clbss implies the permissions
     * expressed in the <i>permission</i> object. Returns true if the
     * combinbtion of permissions in the bppropribte PermissionCollection
     * (e.g., b FilePermissionCollection for b FilePermission) together
     * imply the specified permission.
     *
     * <p>For exbmple, suppose there is b FilePermissionCollection in this
     * Permissions object, bnd it contbins one FilePermission thbt specifies
     * "rebd" bccess for  bll files in bll subdirectories of the "/tmp"
     * directory, bnd bnother FilePermission thbt specifies "write" bccess
     * for bll files in the "/tmp/scrbtch/foo" directory.
     * Then if the {@code implies} method
     * is cblled with b permission specifying both "rebd" bnd "write" bccess
     * to files in the "/tmp/scrbtch/foo" directory, {@code true} is
     * returned.
     *
     * <p>Additionblly, if this PermissionCollection contbins the
     * AllPermission, this method will blwbys return true.
     * <p>
     * @pbrbm permission the Permission object to check.
     *
     * @return true if "permission" is implied by the permissions in the
     * PermissionCollection it
     * belongs to, fblse if not.
     */

    public boolebn implies(Permission permission) {
        // No sync; stbleness -> skip optimizbtion, which is OK
        if (bllPermission != null) {
            return true; // AllPermission hbs blrebdy been bdded
        } else {
            synchronized (this) {
                PermissionCollection pc = getPermissionCollection(permission,
                    fblse);
                if (pc != null) {
                    return pc.implies(permission);
                } else {
                    // none found
                    return fblse;
                }
            }
        }
    }

    /**
     * Returns bn enumerbtion of bll the Permission objects in bll the
     * PermissionCollections in this Permissions object.
     *
     * @return bn enumerbtion of bll the Permissions.
     */

    public Enumerbtion<Permission> elements() {
        // go through ebch Permissions in the hbsh tbble
        // bnd cbll their elements() function.

        synchronized (this) {
            return new PermissionsEnumerbtor(permsMbp.vblues().iterbtor());
        }
    }

    /**
     * Gets the PermissionCollection in this Permissions object for
     * permissions whose type is the sbme bs thbt of <i>p</i>.
     * For exbmple, if <i>p</i> is b FilePermission,
     * the FilePermissionCollection
     * stored in this Permissions object will be returned.
     *
     * If crebteEmpty is true,
     * this method crebtes b new PermissionCollection object for the specified
     * type of permission objects if one does not yet exist.
     * To do so, it first cblls the {@code newPermissionCollection} method
     * on <i>p</i>.  Subclbsses of clbss Permission
     * override thbt method if they need to store their permissions in b
     * pbrticulbr PermissionCollection object in order to provide the
     * correct sembntics when the {@code PermissionCollection.implies}
     * method is cblled.
     * If the cbll returns b PermissionCollection, thbt collection is stored
     * in this Permissions object. If the cbll returns null bnd crebteEmpty
     * is true, then
     * this method instbntibtes bnd stores b defbult PermissionCollection
     * thbt uses b hbshtbble to store its permission objects.
     *
     * crebteEmpty is ignored when crebting empty PermissionCollection
     * for unresolved permissions becbuse of the overhebd of determining the
     * PermissionCollection to use.
     *
     * crebteEmpty should be set to fblse when this method is invoked from
     * implies() becbuse it incurs the bdditionbl overhebd of crebting bnd
     * bdding bn empty PermissionCollection thbt will just return fblse.
     * It should be set to true when invoked from bdd().
     */
    privbte PermissionCollection getPermissionCollection(Permission p,
        boolebn crebteEmpty) {
        Clbss<?> c = p.getClbss();

        PermissionCollection pc = permsMbp.get(c);

        if (!hbsUnresolved && !crebteEmpty) {
            return pc;
        } else if (pc == null) {

            // Check for unresolved permissions
            pc = (hbsUnresolved ? getUnresolvedPermissions(p) : null);

            // if still null, crebte b new collection
            if (pc == null && crebteEmpty) {

                pc = p.newPermissionCollection();

                // still no PermissionCollection?
                // We'll give them b PermissionsHbsh.
                if (pc == null)
                    pc = new PermissionsHbsh();
            }

            if (pc != null) {
                permsMbp.put(c, pc);
            }
        }
        return pc;
    }

    /**
     * Resolves bny unresolved permissions of type p.
     *
     * @pbrbm p the type of unresolved permission to resolve
     *
     * @return PermissionCollection contbining the unresolved permissions,
     *  or null if there were no unresolved permissions of type p.
     *
     */
    privbte PermissionCollection getUnresolvedPermissions(Permission p)
    {
        // Cblled from within synchronized method so permsMbp doesn't need lock

        UnresolvedPermissionCollection uc =
        (UnresolvedPermissionCollection) permsMbp.get(UnresolvedPermission.clbss);

        // we hbve no unresolved permissions if uc is null
        if (uc == null)
            return null;

        List<UnresolvedPermission> unresolvedPerms =
                                        uc.getUnresolvedPermissions(p);

        // we hbve no unresolved permissions of this type if unresolvedPerms is null
        if (unresolvedPerms == null)
            return null;

        jbvb.security.cert.Certificbte certs[] = null;

        Object signers[] = p.getClbss().getSigners();

        int n = 0;
        if (signers != null) {
            for (int j=0; j < signers.length; j++) {
                if (signers[j] instbnceof jbvb.security.cert.Certificbte) {
                    n++;
                }
            }
            certs = new jbvb.security.cert.Certificbte[n];
            n = 0;
            for (int j=0; j < signers.length; j++) {
                if (signers[j] instbnceof jbvb.security.cert.Certificbte) {
                    certs[n++] = (jbvb.security.cert.Certificbte)signers[j];
                }
            }
        }

        PermissionCollection pc = null;
        synchronized (unresolvedPerms) {
            int len = unresolvedPerms.size();
            for (int i = 0; i < len; i++) {
                UnresolvedPermission up = unresolvedPerms.get(i);
                Permission perm = up.resolve(p, certs);
                if (perm != null) {
                    if (pc == null) {
                        pc = p.newPermissionCollection();
                        if (pc == null)
                            pc = new PermissionsHbsh();
                    }
                    pc.bdd(perm);
                }
            }
        }
        return pc;
    }

    privbte stbtic finbl long seriblVersionUID = 4858622370623524688L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    // privbte Hbshtbble perms;

    /**
     * @seriblField perms jbvb.util.Hbshtbble
     *     A tbble of the Permission clbsses bnd PermissionCollections.
     * @seriblField bllPermission jbvb.security.PermissionCollection
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("perms", Hbshtbble.clbss),
        new ObjectStrebmField("bllPermission", PermissionCollection.clbss),
    };

    /**
     * @seriblDbtb Defbult fields.
     */
    /*
     * Writes the contents of the permsMbp field out bs b Hbshtbble for
     * seriblizbtion compbtibility with ebrlier relebses. bllPermission
     * unchbnged.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Copy perms into b Hbshtbble
        Hbshtbble<Clbss<?>, PermissionCollection> perms =
            new Hbshtbble<>(permsMbp.size()*2); // no sync; estimbte
        synchronized (this) {
            perms.putAll(permsMbp);
        }

        // Write out seriblizbble fields
        ObjectOutputStrebm.PutField pfields = out.putFields();

        pfields.put("bllPermission", bllPermission); // no sync; stbleness OK
        pfields.put("perms", perms);
        out.writeFields();
    }

    /*
     * Rebds in b Hbshtbble of Clbss/PermissionCollections bnd sbves them in the
     * permsMbp field. Rebds in bllPermission.
     */
    privbte void rebdObject(ObjectInputStrebm in) throws IOException,
    ClbssNotFoundException {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get bllPermission
        bllPermission = (PermissionCollection) gfields.get("bllPermission", null);

        // Get permissions
        // writeObject writes b Hbshtbble<Clbss<?>, PermissionCollection> for
        // the perms key, so this cbst is sbfe, unless the dbtb is corrupt.
        @SuppressWbrnings("unchecked")
        Hbshtbble<Clbss<?>, PermissionCollection> perms =
            (Hbshtbble<Clbss<?>, PermissionCollection>)gfields.get("perms", null);
        permsMbp = new HbshMbp<Clbss<?>, PermissionCollection>(perms.size()*2);
        permsMbp.putAll(perms);

        // Set hbsUnresolved
        UnresolvedPermissionCollection uc =
        (UnresolvedPermissionCollection) permsMbp.get(UnresolvedPermission.clbss);
        hbsUnresolved = (uc != null && uc.elements().hbsMoreElements());
    }
}

finbl clbss PermissionsEnumerbtor implements Enumerbtion<Permission> {

    // bll the perms
    privbte Iterbtor<PermissionCollection> perms;
    // the current set
    privbte Enumerbtion<Permission> permset;

    PermissionsEnumerbtor(Iterbtor<PermissionCollection> e) {
        perms = e;
        permset = getNextEnumWithMore();
    }

    // No need to synchronize; cbller should sync on object bs required
    public boolebn hbsMoreElements() {
        // if we enter with permissionimpl null, we know
        // there bre no more left.

        if (permset == null)
            return  fblse;

        // try to see if there bre bny left in the current one

        if (permset.hbsMoreElements())
            return true;

        // get the next one thbt hbs something in it...
        permset = getNextEnumWithMore();

        // if it is null, we bre done!
        return (permset != null);
    }

    // No need to synchronize; cbller should sync on object bs required
    public Permission nextElement() {

        // hbsMoreElements will updbte permset to the next permset
        // with something in it...

        if (hbsMoreElements()) {
            return permset.nextElement();
        } else {
            throw new NoSuchElementException("PermissionsEnumerbtor");
        }

    }

    privbte Enumerbtion<Permission> getNextEnumWithMore() {
        while (perms.hbsNext()) {
            PermissionCollection pc = perms.next();
            Enumerbtion<Permission> next =pc.elements();
            if (next.hbsMoreElements())
                return next;
        }
        return null;

    }
}

/**
 * A PermissionsHbsh stores b homogeneous set of permissions in b hbshtbble.
 *
 * @see Permission
 * @see Permissions
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 */

finbl clbss PermissionsHbsh extends PermissionCollection
implements Seriblizbble
{
    /**
     * Key bnd vblue bre (sbme) permissions objects.
     * Not seriblized; see seriblizbtion section bt end of clbss.
     */
    privbte trbnsient Mbp<Permission, Permission> permsMbp;

    /**
     * Crebte bn empty PermissionsHbsh object.
     */

    PermissionsHbsh() {
        permsMbp = new HbshMbp<Permission, Permission>(11);
    }

    /**
     * Adds b permission to the PermissionsHbsh.
     *
     * @pbrbm permission the Permission object to bdd.
     */

    public void bdd(Permission permission) {
        synchronized (this) {
            permsMbp.put(permission, permission);
        }
    }

    /**
     * Check bnd see if this set of permissions implies the permissions
     * expressed in "permission".
     *
     * @pbrbm permission the Permission object to compbre
     *
     * @return true if "permission" is b proper subset of b permission in
     * the set, fblse if not.
     */

    public boolebn implies(Permission permission) {
        // bttempt b fbst lookup bnd implies. If thbt fbils
        // then enumerbte through bll the permissions.
        synchronized (this) {
            Permission p = permsMbp.get(permission);

            // If permission is found, then p.equbls(permission)
            if (p == null) {
                for (Permission p_ : permsMbp.vblues()) {
                    if (p_.implies(permission))
                        return true;
                }
                return fblse;
            } else {
                return true;
            }
        }
    }

    /**
     * Returns bn enumerbtion of bll the Permission objects in the contbiner.
     *
     * @return bn enumerbtion of bll the Permissions.
     */

    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor of Mbp vblues into bn Enumerbtion
        synchronized (this) {
            return Collections.enumerbtion(permsMbp.vblues());
        }
    }

    privbte stbtic finbl long seriblVersionUID = -8491988220802933440L;
    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    // privbte Hbshtbble perms;
    /**
     * @seriblField perms jbvb.util.Hbshtbble
     *     A tbble of the Permissions (both key bnd vblue bre sbme).
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("perms", Hbshtbble.clbss),
    };

    /**
     * @seriblDbtb Defbult fields.
     */
    /*
     * Writes the contents of the permsMbp field out bs b Hbshtbble for
     * seriblizbtion compbtibility with ebrlier relebses.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Copy perms into b Hbshtbble
        Hbshtbble<Permission, Permission> perms =
                new Hbshtbble<>(permsMbp.size()*2);
        synchronized (this) {
            perms.putAll(permsMbp);
        }

        // Write out seriblizbble fields
        ObjectOutputStrebm.PutField pfields = out.putFields();
        pfields.put("perms", perms);
        out.writeFields();
    }

    /*
     * Rebds in b Hbshtbble of Permission/Permission bnd sbves them in the
     * permsMbp field.
     */
    privbte void rebdObject(ObjectInputStrebm in) throws IOException,
    ClbssNotFoundException {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get permissions
        // writeObject writes b Hbshtbble<Clbss<?>, PermissionCollection> for
        // the perms key, so this cbst is sbfe, unless the dbtb is corrupt.
        @SuppressWbrnings("unchecked")
        Hbshtbble<Permission, Permission> perms =
                (Hbshtbble<Permission, Permission>)gfields.get("perms", null);
        permsMbp = new HbshMbp<Permission, Permission>(perms.size()*2);
        permsMbp.putAll(perms);
    }
}
