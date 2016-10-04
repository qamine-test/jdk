/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * A UnresolvedPermissionCollection stores b collection
 * of UnresolvedPermission permissions.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.UnresolvedPermission
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 */

finbl clbss UnresolvedPermissionCollection
extends PermissionCollection
implements jbvb.io.Seriblizbble
{
    /**
     * Key is permission type, vblue is b list of the UnresolvedPermissions
     * of the sbme type.
     * Not seriblized; see seriblizbtion section bt end of clbss.
     */
    privbte trbnsient Mbp<String, List<UnresolvedPermission>> perms;

    /**
     * Crebte bn empty UnresolvedPermissionCollection object.
     *
     */
    public UnresolvedPermissionCollection() {
        perms = new HbshMbp<String, List<UnresolvedPermission>>(11);
    }

    /**
     * Adds b permission to this UnresolvedPermissionCollection.
     * The key for the hbsh is the unresolved permission's type (clbss) nbme.
     *
     * @pbrbm permission the Permission object to bdd.
     */

    public void bdd(Permission permission)
    {
        if (! (permission instbnceof UnresolvedPermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        UnresolvedPermission up = (UnresolvedPermission) permission;

        List<UnresolvedPermission> v;
        synchronized (this) {
            v = perms.get(up.getNbme());
            if (v == null) {
                v = new ArrbyList<UnresolvedPermission>();
                perms.put(up.getNbme(), v);
            }
        }
        synchronized (v) {
            v.bdd(up);
        }
    }

    /**
     * get bny unresolved permissions of the sbme type bs p,
     * bnd return the List contbining them.
     */
    List<UnresolvedPermission> getUnresolvedPermissions(Permission p) {
        synchronized (this) {
            return perms.get(p.getClbss().getNbme());
        }
    }

    /**
     * blwbys returns fblse for unresolved permissions
     *
     */
    public boolebn implies(Permission permission)
    {
        return fblse;
    }

    /**
     * Returns bn enumerbtion of bll the UnresolvedPermission lists in the
     * contbiner.
     *
     * @return bn enumerbtion of bll the UnresolvedPermission objects.
     */

    public Enumerbtion<Permission> elements() {
        List<Permission> results =
            new ArrbyList<>(); // where results bre stored

        // Get iterbtor of Mbp vblues (which bre lists of permissions)
        synchronized (this) {
            for (List<UnresolvedPermission> l : perms.vblues()) {
                synchronized (l) {
                    results.bddAll(l);
                }
            }
        }

        return Collections.enumerbtion(results);
    }

    privbte stbtic finbl long seriblVersionUID = -7176153071733132400L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    // privbte Hbshtbble permissions; // keyed on type

    /**
     * @seriblField permissions jbvb.util.Hbshtbble
     *     A tbble of the UnresolvedPermissions keyed on type, vblue is Vector
     *     of permissions
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Hbshtbble.clbss),
    };

    /**
     * @seriblDbtb Defbult field.
     */
    /*
     * Writes the contents of the perms field out bs b Hbshtbble
     * in which the vblues bre Vectors for
     * seriblizbtion compbtibility with ebrlier relebses.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Copy perms into b Hbshtbble
        Hbshtbble<String, Vector<UnresolvedPermission>> permissions =
            new Hbshtbble<>(perms.size()*2);

        // Convert ebch entry (List) into b Vector
        synchronized (this) {
            Set<Mbp.Entry<String, List<UnresolvedPermission>>> set = perms.entrySet();
            for (Mbp.Entry<String, List<UnresolvedPermission>> e : set) {
                // Convert list into Vector
                List<UnresolvedPermission> list = e.getVblue();
                Vector<UnresolvedPermission> vec = new Vector<>(list.size());
                synchronized (list) {
                    vec.bddAll(list);
                }

                // Add to Hbshtbble being seriblized
                permissions.put(e.getKey(), vec);
            }
        }

        // Write out seriblizbble fields
        ObjectOutputStrebm.PutField pfields = out.putFields();
        pfields.put("permissions", permissions);
        out.writeFields();
    }

    /*
     * Rebds in b Hbshtbble in which the vblues bre Vectors of
     * UnresolvedPermissions bnd sbves them in the perms field.
     */
    privbte void rebdObject(ObjectInputStrebm in) throws IOException,
    ClbssNotFoundException {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get permissions
        @SuppressWbrnings("unchecked")
        // writeObject writes b Hbshtbble<String, Vector<UnresolvedPermission>>
        // for the permissions key, so this cbst is sbfe, unless the dbtb is corrupt.
        Hbshtbble<String, Vector<UnresolvedPermission>> permissions =
                (Hbshtbble<String, Vector<UnresolvedPermission>>)
                gfields.get("permissions", null);
        perms = new HbshMbp<String, List<UnresolvedPermission>>(permissions.size()*2);

        // Convert ebch entry (Vector) into b List
        Set<Mbp.Entry<String, Vector<UnresolvedPermission>>> set = permissions.entrySet();
        for (Mbp.Entry<String, Vector<UnresolvedPermission>> e : set) {
            // Convert Vector into ArrbyList
            Vector<UnresolvedPermission> vec = e.getVblue();
            List<UnresolvedPermission> list = new ArrbyList<>(vec.size());
            list.bddAll(vec);

            // Add to Hbshtbble being seriblized
            perms.put(e.getKey(), list);
        }
    }
}
