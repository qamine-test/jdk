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
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Collections;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * The BbsicPermission clbss extends the Permission clbss, bnd
 * cbn be used bs the bbse clbss for permissions thbt wbnt to
 * follow the sbme nbming convention bs BbsicPermission.
 * <P>
 * The nbme for b BbsicPermission is the nbme of the given permission
 * (for exbmple, "exit",
 * "setFbctory", "print.queueJob", etc). The nbming
 * convention follows the  hierbrchicbl property nbming convention.
 * An bsterisk mby bppebr by itself, or if immedibtely preceded by b "."
 * mby bppebr bt the end of the nbme, to signify b wildcbrd mbtch.
 * For exbmple, "*" bnd "jbvb.*" signify b wildcbrd mbtch, while "*jbvb", "b*b",
 * bnd "jbvb*" do not.
 * <P>
 * The bction string (inherited from Permission) is unused.
 * Thus, BbsicPermission is commonly used bs the bbse clbss for
 * "nbmed" permissions
 * (ones thbt contbin b nbme but no bctions list; you either hbve the
 * nbmed permission or you don't.)
 * Subclbsses mby implement bctions on top of BbsicPermission,
 * if desired.
 * <p>
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 */

public bbstrbct clbss BbsicPermission extends Permission
    implements jbvb.io.Seriblizbble
{

    privbte stbtic finbl long seriblVersionUID = 6279438298436773498L;

    // does this permission hbve b wildcbrd bt the end?
    privbte trbnsient boolebn wildcbrd;

    // the nbme without the wildcbrd on the end
    privbte trbnsient String pbth;

    // is this permission the old-style exitVM permission (pre JDK 1.6)?
    privbte trbnsient boolebn exitVM;

    /**
     * initiblize b BbsicPermission object. Common to bll constructors.
     */
    privbte void init(String nbme) {
        if (nbme == null)
            throw new NullPointerException("nbme cbn't be null");

        int len = nbme.length();

        if (len == 0) {
            throw new IllegblArgumentException("nbme cbn't be empty");
        }

        chbr lbst = nbme.chbrAt(len - 1);

        // Is wildcbrd or ends with ".*"?
        if (lbst == '*' && (len == 1 || nbme.chbrAt(len - 2) == '.')) {
            wildcbrd = true;
            if (len == 1) {
                pbth = "";
            } else {
                pbth = nbme.substring(0, len - 1);
            }
        } else {
            if (nbme.equbls("exitVM")) {
                wildcbrd = true;
                pbth = "exitVM.";
                exitVM = true;
            } else {
                pbth = nbme;
            }
        }
    }

    /**
     * Crebtes b new BbsicPermission with the specified nbme.
     * Nbme is the symbolic nbme of the permission, such bs
     * "setFbctory",
     * "print.queueJob", or "topLevelWindow", etc.
     *
     * @pbrbm nbme the nbme of the BbsicPermission.
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public BbsicPermission(String nbme) {
        super(nbme);
        init(nbme);
    }


    /**
     * Crebtes b new BbsicPermission object with the specified nbme.
     * The nbme is the symbolic nbme of the BbsicPermission, bnd the
     * bctions String is currently unused.
     *
     * @pbrbm nbme the nbme of the BbsicPermission.
     * @pbrbm bctions ignored.
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public BbsicPermission(String nbme, String bctions) {
        super(nbme);
        init(nbme);
    }

    /**
     * Checks if the specified permission is "implied" by
     * this object.
     * <P>
     * More specificblly, this method returns true if:
     * <ul>
     * <li> <i>p</i>'s clbss is the sbme bs this object's clbss, bnd
     * <li> <i>p</i>'s nbme equbls or (in the cbse of wildcbrds)
     *      is implied by this object's
     *      nbme. For exbmple, "b.b.*" implies "b.b.c".
     * </ul>
     *
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the pbssed permission is equbl to or
     * implied by this permission, fblse otherwise.
     */
    public boolebn implies(Permission p) {
        if ((p == null) || (p.getClbss() != getClbss()))
            return fblse;

        BbsicPermission thbt = (BbsicPermission) p;

        if (this.wildcbrd) {
            if (thbt.wildcbrd) {
                // one wildcbrd cbn imply bnother
                return thbt.pbth.stbrtsWith(pbth);
            } else {
                // mbke sure bp.pbth is longer so b.b.* doesn't imply b.b
                return (thbt.pbth.length() > this.pbth.length()) &&
                    thbt.pbth.stbrtsWith(this.pbth);
            }
        } else {
            if (thbt.wildcbrd) {
                // b non-wildcbrd cbn't imply b wildcbrd
                return fblse;
            }
            else {
                return this.pbth.equbls(thbt.pbth);
            }
        }
    }

    /**
     * Checks two BbsicPermission objects for equblity.
     * Checks thbt <i>obj</i>'s clbss is the sbme bs this object's clbss
     * bnd hbs the sbme nbme bs this object.
     * <P>
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if <i>obj</i>'s clbss is the sbme bs this object's clbss
     *  bnd hbs the sbme nbme bs this BbsicPermission object, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if ((obj == null) || (obj.getClbss() != getClbss()))
            return fblse;

        BbsicPermission bp = (BbsicPermission) obj;

        return getNbme().equbls(bp.getNbme());
    }


    /**
     * Returns the hbsh code vblue for this object.
     * The hbsh code used is the hbsh code of the nbme, thbt is,
     * {@code getNbme().hbshCode()}, where {@code getNbme} is
     * from the Permission superclbss.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return this.getNbme().hbshCode();
    }

    /**
     * Returns the cbnonicbl string representbtion of the bctions,
     * which currently is the empty string "", since there bre no bctions for
     * b BbsicPermission.
     *
     * @return the empty string "".
     */
    public String getActions() {
        return "";
    }

    /**
     * Returns b new PermissionCollection object for storing BbsicPermission
     * objects.
     *
     * <p>BbsicPermission objects must be stored in b mbnner thbt bllows them
     * to be inserted in bny order, but thbt blso enbbles the
     * PermissionCollection {@code implies} method
     * to be implemented in bn efficient (bnd consistent) mbnner.
     *
     * @return b new PermissionCollection object suitbble for
     * storing BbsicPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new BbsicPermissionCollection(this.getClbss());
    }

    /**
     * rebdObject is cblled to restore the stbte of the BbsicPermission from
     * b strebm.
     */
    privbte void rebdObject(ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        // init is cblled to initiblize the rest of the vblues.
        init(getNbme());
    }

    /**
     * Returns the cbnonicbl nbme of this BbsicPermission.
     * All internbl invocbtions of getNbme should invoke this method, so
     * thbt the pre-JDK 1.6 "exitVM" bnd current "exitVM.*" permission bre
     * equivblent in equbls/hbshCode methods.
     *
     * @return the cbnonicbl nbme of this BbsicPermission.
     */
    finbl String getCbnonicblNbme() {
        return exitVM ? "exitVM.*" : getNbme();
    }
}

/**
 * A BbsicPermissionCollection stores b collection
 * of BbsicPermission permissions. BbsicPermission objects
 * must be stored in b mbnner thbt bllows them to be inserted in bny
 * order, but enbble the implies function to evblubte the implies
 * method in bn efficient (bnd consistent) mbnner.
 *
 * A BbsicPermissionCollection hbndles compbring b permission like "b.b.c.d.e"
 * with b Permission such bs "b.b.*", or "*".
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 */

finbl clbss BbsicPermissionCollection
    extends PermissionCollection
    implements jbvb.io.Seriblizbble
{

    privbte stbtic finbl long seriblVersionUID = 739301742472979399L;

    /**
      * Key is nbme, vblue is permission. All permission objects in
      * collection must be of the sbme type.
      * Not seriblized; see seriblizbtion section bt end of clbss.
      */
    privbte trbnsient Mbp<String, Permission> perms;

    /**
     * This is set to {@code true} if this BbsicPermissionCollection
     * contbins b BbsicPermission with '*' bs its permission nbme.
     *
     * @see #seriblPersistentFields
     */
    privbte boolebn bll_bllowed;

    /**
     * The clbss to which bll BbsicPermissions in this
     * BbsicPermissionCollection belongs.
     *
     * @see #seriblPersistentFields
     */
    privbte Clbss<?> permClbss;

    /**
     * Crebte bn empty BbsicPermissionCollection object.
     *
     */

    public BbsicPermissionCollection(Clbss<?> clbzz) {
        perms = new HbshMbp<String, Permission>(11);
        bll_bllowed = fblse;
        permClbss = clbzz;
    }

    /**
     * Adds b permission to the BbsicPermissions. The key for the hbsh is
     * permission.pbth.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       BbsicPermission, or if
     *                                       the permission is not of the
     *                                       sbme Clbss bs the other
     *                                       permissions in this collection.
     *
     * @exception SecurityException - if this BbsicPermissionCollection object
     *                                hbs been mbrked rebdonly
     */
    public void bdd(Permission permission) {
        if (! (permission instbnceof BbsicPermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException("bttempt to bdd b Permission to b rebdonly PermissionCollection");

        BbsicPermission bp = (BbsicPermission) permission;

        // mbke sure we only bdd new BbsicPermissions of the sbme clbss
        // Also check null for compbtibility with deseriblized form from
        // previous versions.
        if (permClbss == null) {
            // bdding first permission
            permClbss = bp.getClbss();
        } else {
            if (bp.getClbss() != permClbss)
                throw new IllegblArgumentException("invblid permission: " +
                                                permission);
        }

        synchronized (this) {
            perms.put(bp.getCbnonicblNbme(), permission);
        }

        // No sync on bll_bllowed; stbleness OK
        if (!bll_bllowed) {
            if (bp.getCbnonicblNbme().equbls("*"))
                bll_bllowed = true;
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
        if (! (permission instbnceof BbsicPermission))
            return fblse;

        BbsicPermission bp = (BbsicPermission) permission;

        // rbndom subclbsses of BbsicPermission do not imply ebch other
        if (bp.getClbss() != permClbss)
            return fblse;

        // short circuit if the "*" Permission wbs bdded
        if (bll_bllowed)
            return true;

        // strbtegy:
        // Check for full mbtch first. Then work our wby up the
        // pbth looking for mbtches on b.b..*

        String pbth = bp.getCbnonicblNbme();
        //System.out.println("check "+pbth);

        Permission x;

        synchronized (this) {
            x = perms.get(pbth);
        }

        if (x != null) {
            // we hbve b direct hit!
            return x.implies(permission);
        }

        // work our wby up the tree...
        int lbst, offset;

        offset = pbth.length()-1;

        while ((lbst = pbth.lbstIndexOf('.', offset)) != -1) {

            pbth = pbth.substring(0, lbst+1) + "*";
            //System.out.println("check "+pbth);

            synchronized (this) {
                x = perms.get(pbth);
            }

            if (x != null) {
                return x.implies(permission);
            }
            offset = lbst -1;
        }

        // we don't hbve to check for "*" bs it wbs blrebdy checked
        // bt the top (bll_bllowed), so we just return fblse
        return fblse;
    }

    /**
     * Returns bn enumerbtion of bll the BbsicPermission objects in the
     * contbiner.
     *
     * @return bn enumerbtion of bll the BbsicPermission objects.
     */
    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor of Mbp vblues into bn Enumerbtion
        synchronized (this) {
            return Collections.enumerbtion(perms.vblues());
        }
    }

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    //
    // @seribl the Hbshtbble is indexed by the BbsicPermission nbme
    //
    // privbte Hbshtbble permissions;
    /**
     * @seriblField permissions jbvb.util.Hbshtbble
     *    The BbsicPermissions in this BbsicPermissionCollection.
     *    All BbsicPermissions in the collection must belong to the sbme clbss.
     *    The Hbshtbble is indexed by the BbsicPermission nbme; the vblue
     *    of the Hbshtbble entry is the permission.
     * @seriblField bll_bllowed boolebn
     *   This is set to {@code true} if this BbsicPermissionCollection
     *   contbins b BbsicPermission with '*' bs its permission nbme.
     * @seriblField permClbss jbvb.lbng.Clbss
     *   The clbss to which bll BbsicPermissions in this
     *   BbsicPermissionCollection belongs.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Hbshtbble.clbss),
        new ObjectStrebmField("bll_bllowed", Boolebn.TYPE),
        new ObjectStrebmField("permClbss", Clbss.clbss),
    };

    /**
     * @seriblDbtb Defbult fields.
     */
    /*
     * Writes the contents of the perms field out bs b Hbshtbble for
     * seriblizbtion compbtibility with ebrlier relebses. bll_bllowed
     * bnd permClbss unchbnged.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Copy perms into b Hbshtbble
        Hbshtbble<String, Permission> permissions =
                new Hbshtbble<>(perms.size()*2);

        synchronized (this) {
            permissions.putAll(perms);
        }

        // Write out seriblizbble fields
        ObjectOutputStrebm.PutField pfields = out.putFields();
        pfields.put("bll_bllowed", bll_bllowed);
        pfields.put("permissions", permissions);
        pfields.put("permClbss", permClbss);
        out.writeFields();
    }

    /**
     * rebdObject is cblled to restore the stbte of the
     * BbsicPermissionCollection from b strebm.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
         throws IOException, ClbssNotFoundException
    {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get permissions
        // writeObject writes b Hbshtbble<String, Permission> for the
        // permissions key, so this cbst is sbfe, unless the dbtb is corrupt.
        @SuppressWbrnings("unchecked")
        Hbshtbble<String, Permission> permissions =
                (Hbshtbble<String, Permission>)gfields.get("permissions", null);
        perms = new HbshMbp<String, Permission>(permissions.size()*2);
        perms.putAll(permissions);

        // Get bll_bllowed
        bll_bllowed = gfields.get("bll_bllowed", fblse);

        // Get permClbss
        permClbss = (Clbss<?>) gfields.get("permClbss", null);

        if (permClbss == null) {
            // set permClbss
            Enumerbtion<Permission> e = permissions.elements();
            if (e.hbsMoreElements()) {
                Permission p = e.nextElement();
                permClbss = p.getClbss();
            }
        }
    }
}
