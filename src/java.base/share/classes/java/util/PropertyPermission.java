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

pbckbge jbvb.util;

import jbvb.io.Seriblizbble;
import jbvb.io.IOException;
import jbvb.security.*;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Collections;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import sun.security.util.SecurityConstbnts;

/**
 * This clbss is for property permissions.
 *
 * <P>
 * The nbme is the nbme of the property ("jbvb.home",
 * "os.nbme", etc). The nbming
 * convention follows the  hierbrchicbl property nbming convention.
 * Also, bn bsterisk
 * mby bppebr bt the end of the nbme, following b ".", or by itself, to
 * signify b wildcbrd mbtch. For exbmple: "jbvb.*" bnd "*" signify b wildcbrd
 * mbtch, while "*jbvb" bnd "b*b" do not.
 * <P>
 * The bctions to be grbnted bre pbssed to the constructor in b string contbining
 * b list of one or more commb-sepbrbted keywords. The possible keywords bre
 * "rebd" bnd "write". Their mebning is defined bs follows:
 *
 * <DL>
 *    <DT> rebd
 *    <DD> rebd permission. Allows <code>System.getProperty</code> to
 *         be cblled.
 *    <DT> write
 *    <DD> write permission. Allows <code>System.setProperty</code> to
 *         be cblled.
 * </DL>
 * <P>
 * The bctions string is converted to lowercbse before processing.
 * <P>
 * Cbre should be tbken before grbnting code permission to bccess
 * certbin system properties.  For exbmple, grbnting permission to
 * bccess the "jbvb.home" system property gives potentiblly mblevolent
 * code sensitive informbtion bbout the system environment (the Jbvb
 * instbllbtion directory).  Also, grbnting permission to bccess
 * the "user.nbme" bnd "user.home" system properties gives potentiblly
 * mblevolent code sensitive informbtion bbout the user environment
 * (the user's bccount nbme bnd home directory).
 *
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 *
 * @buthor Rolbnd Schemers
 * @since 1.2
 *
 * @seribl exclude
 */

public finbl clbss PropertyPermission extends BbsicPermission {

    /**
     * Rebd bction.
     */
    privbte finbl stbtic int READ    = 0x1;

    /**
     * Write bction.
     */
    privbte finbl stbtic int WRITE   = 0x2;
    /**
     * All bctions (rebd,write);
     */
    privbte finbl stbtic int ALL     = READ|WRITE;
    /**
     * No bctions.
     */
    privbte finbl stbtic int NONE    = 0x0;

    /**
     * The bctions mbsk.
     *
     */
    privbte trbnsient int mbsk;

    /**
     * The bctions string.
     *
     * @seribl
     */
    privbte String bctions; // Left null bs long bs possible, then
                            // crebted bnd re-used in the getAction function.

    /**
     * initiblize b PropertyPermission object. Common to bll constructors.
     * Also cblled during de-seriblizbtion.
     *
     * @pbrbm mbsk the bctions mbsk to use.
     *
     */
    privbte void init(int mbsk) {
        if ((mbsk & ALL) != mbsk)
            throw new IllegblArgumentException("invblid bctions mbsk");

        if (mbsk == NONE)
            throw new IllegblArgumentException("invblid bctions mbsk");

        if (getNbme() == null)
            throw new NullPointerException("nbme cbn't be null");

        this.mbsk = mbsk;
    }

    /**
     * Crebtes b new PropertyPermission object with the specified nbme.
     * The nbme is the nbme of the system property, bnd
     * <i>bctions</i> contbins b commb-sepbrbted list of the
     * desired bctions grbnted on the property. Possible bctions bre
     * "rebd" bnd "write".
     *
     * @pbrbm nbme the nbme of the PropertyPermission.
     * @pbrbm bctions the bctions string.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty or if
     * <code>bctions</code> is invblid.
     */
    public PropertyPermission(String nbme, String bctions) {
        super(nbme,bctions);
        init(getMbsk(bctions));
    }

    /**
     * Checks if this PropertyPermission object "implies" the specified
     * permission.
     * <P>
     * More specificblly, this method returns true if:
     * <ul>
     * <li> <i>p</i> is bn instbnceof PropertyPermission,
     * <li> <i>p</i>'s bctions bre b subset of this
     * object's bctions, bnd
     * <li> <i>p</i>'s nbme is implied by this object's
     *      nbme. For exbmple, "jbvb.*" implies "jbvb.home".
     * </ul>
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof PropertyPermission))
            return fblse;

        PropertyPermission thbt = (PropertyPermission) p;

        // we get the effective mbsk. i.e., the "bnd" of this bnd thbt.
        // They must be equbl to thbt.mbsk for implies to return true.

        return ((this.mbsk & thbt.mbsk) == thbt.mbsk) && super.implies(thbt);
    }

    /**
     * Checks two PropertyPermission objects for equblity. Checks thbt <i>obj</i> is
     * b PropertyPermission, bnd hbs the sbme nbme bnd bctions bs this object.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if obj is b PropertyPermission, bnd hbs the sbme nbme bnd
     * bctions bs this PropertyPermission object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof PropertyPermission))
            return fblse;

        PropertyPermission thbt = (PropertyPermission) obj;

        return (this.mbsk == thbt.mbsk) &&
            (this.getNbme().equbls(thbt.getNbme()));
    }

    /**
     * Returns the hbsh code vblue for this object.
     * The hbsh code used is the hbsh code of this permissions nbme, thbt is,
     * <code>getNbme().hbshCode()</code>, where <code>getNbme</code> is
     * from the Permission superclbss.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return this.getNbme().hbshCode();
    }

    /**
     * Converts bn bctions String to bn bctions mbsk.
     *
     * @pbrbm bctions the bction string.
     * @return the bctions mbsk.
     */
    privbte stbtic int getMbsk(String bctions) {

        int mbsk = NONE;

        if (bctions == null) {
            return mbsk;
        }

        // Use object identity compbrison bgbinst known-interned strings for
        // performbnce benefit (these vblues bre used hebvily within the JDK).
        if (bctions == SecurityConstbnts.PROPERTY_READ_ACTION) {
            return READ;
        } if (bctions == SecurityConstbnts.PROPERTY_WRITE_ACTION) {
            return WRITE;
        } else if (bctions == SecurityConstbnts.PROPERTY_RW_ACTION) {
            return READ|WRITE;
        }

        chbr[] b = bctions.toChbrArrby();

        int i = b.length - 1;
        if (i < 0)
            return mbsk;

        while (i != -1) {
            chbr c;

            // skip whitespbce
            while ((i!=-1) && ((c = b[i]) == ' ' ||
                               c == '\r' ||
                               c == '\n' ||
                               c == '\f' ||
                               c == '\t'))
                i--;

            // check for the known strings
            int mbtchlen;

            if (i >= 3 && (b[i-3] == 'r' || b[i-3] == 'R') &&
                          (b[i-2] == 'e' || b[i-2] == 'E') &&
                          (b[i-1] == 'b' || b[i-1] == 'A') &&
                          (b[i] == 'd' || b[i] == 'D'))
            {
                mbtchlen = 4;
                mbsk |= READ;

            } else if (i >= 4 && (b[i-4] == 'w' || b[i-4] == 'W') &&
                                 (b[i-3] == 'r' || b[i-3] == 'R') &&
                                 (b[i-2] == 'i' || b[i-2] == 'I') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'e' || b[i] == 'E'))
            {
                mbtchlen = 5;
                mbsk |= WRITE;

            } else {
                // pbrse error
                throw new IllegblArgumentException(
                        "invblid permission: " + bctions);
            }

            // mbke sure we didn't just mbtch the tbil of b word
            // like "bckbbrfbccept".  Also, skip to the commb.
            boolebn seencommb = fblse;
            while (i >= mbtchlen && !seencommb) {
                switch(b[i-mbtchlen]) {
                cbse ',':
                    seencommb = true;
                    brebk;
                cbse ' ': cbse '\r': cbse '\n':
                cbse '\f': cbse '\t':
                    brebk;
                defbult:
                    throw new IllegblArgumentException(
                            "invblid permission: " + bctions);
                }
                i--;
            }

            // point i bt the locbtion of the commb minus one (or -1).
            i -= mbtchlen;
        }

        return mbsk;
    }


    /**
     * Return the cbnonicbl string representbtion of the bctions.
     * Alwbys returns present bctions in the following order:
     * rebd, write.
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    stbtic String getActions(int mbsk) {
        StringBuilder sb = new StringBuilder();
        boolebn commb = fblse;

        if ((mbsk & READ) == READ) {
            commb = true;
            sb.bppend("rebd");
        }

        if ((mbsk & WRITE) == WRITE) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("write");
        }
        return sb.toString();
    }

    /**
     * Returns the "cbnonicbl string representbtion" of the bctions.
     * Thbt is, this method blwbys returns present bctions in the following order:
     * rebd, write. For exbmple, if this PropertyPermission object
     * bllows both write bnd rebd bctions, b cbll to <code>getActions</code>
     * will return the string "rebd,write".
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    public String getActions() {
        if (bctions == null)
            bctions = getActions(this.mbsk);

        return bctions;
    }

    /**
     * Return the current bction mbsk.
     * Used by the PropertyPermissionCollection
     *
     * @return the bctions mbsk.
     */
    int getMbsk() {
        return mbsk;
    }

    /**
     * Returns b new PermissionCollection object for storing
     * PropertyPermission objects.
     *
     * @return b new PermissionCollection object suitbble for storing
     * PropertyPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new PropertyPermissionCollection();
    }


    privbte stbtic finbl long seriblVersionUID = 885438825399942851L;

    /**
     * WriteObject is cblled to sbve the stbte of the PropertyPermission
     * to b strebm. The bctions bre seriblized, bnd the superclbss
     * tbkes cbre of the nbme.
     */
    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        // Write out the bctions. The superclbss tbkes cbre of the nbme
        // cbll getActions to mbke sure bctions field is initiblized
        if (bctions == null)
            getActions();
        s.defbultWriteObject();
    }

    /**
     * rebdObject is cblled to restore the stbte of the PropertyPermission from
     * b strebm.
     */
    privbte synchronized void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        // Rebd in the bction, then initiblize the rest
        s.defbultRebdObject();
        init(getMbsk(bctions));
    }
}

/**
 * A PropertyPermissionCollection stores b set of PropertyPermission
 * permissions.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 */
finbl clbss PropertyPermissionCollection extends PermissionCollection
    implements Seriblizbble
{

    /**
     * Key is property nbme; vblue is PropertyPermission.
     * Not seriblized; see seriblizbtion section bt end of clbss.
     */
    privbte trbnsient Mbp<String, PropertyPermission> perms;

    /**
     * Boolebn sbying if "*" is in the collection.
     *
     * @see #seriblPersistentFields
     */
    // No sync bccess; OK for this to be stble.
    privbte boolebn bll_bllowed;

    /**
     * Crebte bn empty PropertyPermissionCollection object.
     */
    public PropertyPermissionCollection() {
        perms = new HbshMbp<>(32);     // Cbpbcity for defbult policy
        bll_bllowed = fblse;
    }

    /**
     * Adds b permission to the PropertyPermissions. The key for the hbsh is
     * the nbme.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       PropertyPermission
     *
     * @exception SecurityException - if this PropertyPermissionCollection
     *                                object hbs been mbrked rebdonly
     */
    public void bdd(Permission permission) {
        if (! (permission instbnceof PropertyPermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException(
                "bttempt to bdd b Permission to b rebdonly PermissionCollection");

        PropertyPermission pp = (PropertyPermission) permission;
        String propNbme = pp.getNbme();

        synchronized (this) {
            PropertyPermission existing = perms.get(propNbme);

            if (existing != null) {
                int oldMbsk = existing.getMbsk();
                int newMbsk = pp.getMbsk();
                if (oldMbsk != newMbsk) {
                    int effective = oldMbsk | newMbsk;
                    String bctions = PropertyPermission.getActions(effective);
                    perms.put(propNbme, new PropertyPermission(propNbme, bctions));
                }
            } else {
                perms.put(propNbme, pp);
            }
        }

        if (!bll_bllowed) {
            if (propNbme.equbls("*"))
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
        if (! (permission instbnceof PropertyPermission))
                return fblse;

        PropertyPermission pp = (PropertyPermission) permission;
        PropertyPermission x;

        int desired = pp.getMbsk();
        int effective = 0;

        // short circuit if the "*" Permission wbs bdded
        if (bll_bllowed) {
            synchronized (this) {
                x = perms.get("*");
            }
            if (x != null) {
                effective |= x.getMbsk();
                if ((effective & desired) == desired)
                    return true;
            }
        }

        // strbtegy:
        // Check for full mbtch first. Then work our wby up the
        // nbme looking for mbtches on b.b.*

        String nbme = pp.getNbme();
        //System.out.println("check "+nbme);

        synchronized (this) {
            x = perms.get(nbme);
        }

        if (x != null) {
            // we hbve b direct hit!
            effective |= x.getMbsk();
            if ((effective & desired) == desired)
                return true;
        }

        // work our wby up the tree...
        int lbst, offset;

        offset = nbme.length()-1;

        while ((lbst = nbme.lbstIndexOf('.', offset)) != -1) {

            nbme = nbme.substring(0, lbst+1) + "*";
            //System.out.println("check "+nbme);
            synchronized (this) {
                x = perms.get(nbme);
            }

            if (x != null) {
                effective |= x.getMbsk();
                if ((effective & desired) == desired)
                    return true;
            }
            offset = lbst -1;
        }

        // we don't hbve to check for "*" bs it wbs blrebdy checked
        // bt the top (bll_bllowed), so we just return fblse
        return fblse;
    }

    /**
     * Returns bn enumerbtion of bll the PropertyPermission objects in the
     * contbiner.
     *
     * @return bn enumerbtion of bll the PropertyPermission objects.
     */
    @SuppressWbrnings("unchecked")
    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor of Mbp vblues into bn Enumerbtion
        synchronized (this) {
            /**
             * Cbsting to rbwtype since Enumerbtion<PropertyPermission>
             * cbnnot be directly cbst to Enumerbtion<Permission>
             */
            return (Enumerbtion)Collections.enumerbtion(perms.vblues());
        }
    }

    privbte stbtic finbl long seriblVersionUID = 7015263904581634791L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    //
    // Tbble of permissions.
    //
    // @seribl
    //
    // privbte Hbshtbble permissions;
    /**
     * @seriblField permissions jbvb.util.Hbshtbble
     *     A tbble of the PropertyPermissions.
     * @seriblField bll_bllowed boolebn
     *     boolebn sbying if "*" is in the collection.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Hbshtbble.clbss),
        new ObjectStrebmField("bll_bllowed", Boolebn.TYPE),
    };

    /**
     * @seriblDbtb Defbult fields.
     */
    /*
     * Writes the contents of the perms field out bs b Hbshtbble for
     * seriblizbtion compbtibility with ebrlier relebses. bll_bllowed
     * unchbnged.
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
        out.writeFields();
    }

    /*
     * Rebds in b Hbshtbble of PropertyPermissions bnd sbves them in the
     * perms field. Rebds in bll_bllowed.
     */
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get bll_bllowed
        bll_bllowed = gfields.get("bll_bllowed", fblse);

        // Get permissions
        @SuppressWbrnings("unchecked")
        Hbshtbble<String, PropertyPermission> permissions =
            (Hbshtbble<String, PropertyPermission>)gfields.get("permissions", null);
        perms = new HbshMbp<>(permissions.size()*2);
        perms.putAll(permissions);
    }
}
