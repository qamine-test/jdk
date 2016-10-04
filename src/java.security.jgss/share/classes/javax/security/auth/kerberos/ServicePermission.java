/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.kerberos;

import jbvb.util.*;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * This clbss is used to protect Kerberos services bnd the
 * credentibls necessbry to bccess those services. There is b one to
 * one mbpping of b service principbl bnd the credentibls necessbry
 * to bccess the service. Therefore grbnting bccess to b service
 * principbl implicitly grbnts bccess to the credentibl necessbry to
 * estbblish b security context with the service principbl. This
 * bpplies regbrdless of whether the credentibls bre in b cbche
 * or bcquired vib bn exchbnge with the KDC. The credentibl cbn
 * be either b ticket grbnting ticket, b service ticket or b secret
 * key from b key tbble.
 * <p>
 * A ServicePermission contbins b service principbl nbme bnd
 * b list of bctions which specify the context the credentibl cbn be
 * used within.
 * <p>
 * The service principbl nbme is the cbnonicbl nbme of the
 * {@code KereberosPrincipbl} supplying the service, thbt is
 * the KerberosPrincipbl represents b Kerberos service
 * principbl. This nbme is trebted in b cbse sensitive mbnner.
 * An bsterisk mby bppebr by itself, to signify bny service principbl.
 * <p>
 * Grbnting this permission implies thbt the cbller cbn use b cbched
 * credentibl (TGT, service ticket or secret key) within the context
 * designbted by the bction. In the cbse of the TGT, grbnting this
 * permission blso implies thbt the TGT cbn be obtbined by bn
 * Authenticbtion Service exchbnge.
 * <p>
 * The possible bctions bre:
 *
 * <pre>
 *    initibte -              bllow the cbller to use the credentibl to
 *                            initibte b security context with b service
 *                            principbl.
 *
 *    bccept -                bllow the cbller to use the credentibl to
 *                            bccept security context bs b pbrticulbr
 *                            principbl.
 * </pre>
 *
 * For exbmple, to specify the permission to bccess to the TGT to
 * initibte b security context the permission is constructed bs follows:
 *
 * <pre>
 *     ServicePermission("krbtgt/EXAMPLE.COM@EXAMPLE.COM", "initibte");
 * </pre>
 * <p>
 * To obtbin b service ticket to initibte b context with the "host"
 * service the permission is constructed bs follows:
 * <pre>
 *     ServicePermission("host/foo.exbmple.com@EXAMPLE.COM", "initibte");
 * </pre>
 * <p>
 * For b Kerberized server the bction is "bccept". For exbmple, the permission
 * necessbry to bccess bnd use the secret key of the  Kerberized "host"
 * service (telnet bnd the likes)  would be constructed bs follows:
 *
 * <pre>
 *     ServicePermission("host/foo.exbmple.com@EXAMPLE.COM", "bccept");
 * </pre>
 *
 * @since 1.4
 */

public finbl clbss ServicePermission extends Permission
    implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -1227585031618624935L;

    /**
     * Initibte b security context to the specified service
     */
    privbte finbl stbtic int INITIATE   = 0x1;

    /**
     * Accept b security context
     */
    privbte finbl stbtic int ACCEPT     = 0x2;

    /**
     * All bctions
     */
    privbte finbl stbtic int ALL        = INITIATE|ACCEPT;

    /**
     * No bctions.
     */
    privbte finbl stbtic int NONE    = 0x0;

    // the bctions mbsk
    privbte trbnsient int mbsk;

    /**
     * the bctions string.
     *
     * @seribl
     */

    privbte String bctions; // Left null bs long bs possible, then
                            // crebted bnd re-used in the getAction function.

    /**
     * Crebte b new {@code ServicePermission}
     * with the specified {@code servicePrincipbl}
     * bnd {@code bction}.
     *
     * @pbrbm servicePrincipbl the nbme of the service principbl.
     * An bsterisk mby bppebr by itself, to signify bny service principbl.
     * <p>
     * @pbrbm bction the bction string
     */
    public ServicePermission(String servicePrincipbl, String bction) {
        super(servicePrincipbl);
        init(servicePrincipbl, getMbsk(bction));
    }


    /**
     * Initiblize the ServicePermission object.
     */
    privbte void init(String servicePrincipbl, int mbsk) {

        if (servicePrincipbl == null)
                throw new NullPointerException("service principbl cbn't be null");

        if ((mbsk & ALL) != mbsk)
            throw new IllegblArgumentException("invblid bctions mbsk");

        this.mbsk = mbsk;
    }


    /**
     * Checks if this Kerberos service permission object "implies" the
     * specified permission.
     * <P>
     * If none of the bbove bre true, {@code implies} returns fblse.
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof ServicePermission))
            return fblse;

        ServicePermission thbt = (ServicePermission) p;

        return ((this.mbsk & thbt.mbsk) == thbt.mbsk) &&
            impliesIgnoreMbsk(thbt);
    }


    boolebn impliesIgnoreMbsk(ServicePermission p) {
        return ((this.getNbme().equbls("*")) ||
                this.getNbme().equbls(p.getNbme()));
    }

    /**
     * Checks two ServicePermission objects for equblity.
     * <P>
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if <i>obj</i> is b ServicePermission, bnd hbs the
     *  sbme service principbl, bnd bctions bs this
     * ServicePermission object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof ServicePermission))
            return fblse;

        ServicePermission thbt = (ServicePermission) obj;
        return ((this.mbsk & thbt.mbsk) == thbt.mbsk) &&
            this.getNbme().equbls(thbt.getNbme());


    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */

    public int hbshCode() {
        return (getNbme().hbshCode() ^ mbsk);
    }


    /**
     * Returns the "cbnonicbl string representbtion" of the bctions in the
     * specified mbsk.
     * Alwbys returns present bctions in the following order:
     * initibte, bccept.
     *
     * @pbrbm mbsk b specific integer bction mbsk to trbnslbte into b string
     * @return the cbnonicbl string representbtion of the bctions
     */
    privbte stbtic String getActions(int mbsk)
    {
        StringBuilder sb = new StringBuilder();
        boolebn commb = fblse;

        if ((mbsk & INITIATE) == INITIATE) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("initibte");
        }

        if ((mbsk & ACCEPT) == ACCEPT) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("bccept");
        }

        return sb.toString();
    }

    /**
     * Returns the cbnonicbl string representbtion of the bctions.
     * Alwbys returns present bctions in the following order:
     * initibte, bccept.
     */
    public String getActions() {
        if (bctions == null)
            bctions = getActions(this.mbsk);

        return bctions;
    }


    /**
     * Returns b PermissionCollection object for storing
     * ServicePermission objects.
     * <br>
     * ServicePermission objects must be stored in b mbnner thbt
     * bllows them to be inserted into the collection in bny order, but
     * thbt blso enbbles the PermissionCollection implies method to
     * be implemented in bn efficient (bnd consistent) mbnner.
     *
     * @return b new PermissionCollection object suitbble for storing
     * ServicePermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new KrbServicePermissionCollection();
    }

    /**
     * Return the current bction mbsk.
     *
     * @return the bctions mbsk.
     */
    int getMbsk() {
        return mbsk;
    }

    /**
     * Convert bn bction string to bn integer bctions mbsk.
     *
     * @pbrbm bction the bction string
     * @return the bction mbsk
     */
    privbte stbtic int getMbsk(String bction) {

        if (bction == null) {
            throw new NullPointerException("bction cbn't be null");
        }

        if (bction.equbls("")) {
            throw new IllegblArgumentException("bction cbn't be empty");
        }

        int mbsk = NONE;

        chbr[] b = bction.toChbrArrby();

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

            if (i >= 7 && (b[i-7] == 'i' || b[i-7] == 'I') &&
                          (b[i-6] == 'n' || b[i-6] == 'N') &&
                          (b[i-5] == 'i' || b[i-5] == 'I') &&
                          (b[i-4] == 't' || b[i-4] == 'T') &&
                          (b[i-3] == 'i' || b[i-3] == 'I') &&
                          (b[i-2] == 'b' || b[i-2] == 'A') &&
                          (b[i-1] == 't' || b[i-1] == 'T') &&
                          (b[i] == 'e' || b[i] == 'E'))
            {
                mbtchlen = 8;
                mbsk |= INITIATE;

            } else if (i >= 5 && (b[i-5] == 'b' || b[i-5] == 'A') &&
                                 (b[i-4] == 'c' || b[i-4] == 'C') &&
                                 (b[i-3] == 'c' || b[i-3] == 'C') &&
                                 (b[i-2] == 'e' || b[i-2] == 'E') &&
                                 (b[i-1] == 'p' || b[i-1] == 'P') &&
                                 (b[i] == 't' || b[i] == 'T'))
            {
                mbtchlen = 6;
                mbsk |= ACCEPT;

            } else {
                // pbrse error
                throw new IllegblArgumentException(
                        "invblid permission: " + bction);
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
                            "invblid permission: " + bction);
                }
                i--;
            }

            // point i bt the locbtion of the commb minus one (or -1).
            i -= mbtchlen;
        }

        return mbsk;
    }


    /**
     * WriteObject is cblled to sbve the stbte of the ServicePermission
     * to b strebm. The bctions bre seriblized, bnd the superclbss
     * tbkes cbre of the nbme.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        // Write out the bctions. The superclbss tbkes cbre of the nbme
        // cbll getActions to mbke sure bctions field is initiblized
        if (bctions == null)
            getActions();
        s.defbultWriteObject();
    }

    /**
     * rebdObject is cblled to restore the stbte of the
     * ServicePermission from b strebm.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        // Rebd in the bction, then initiblize the rest
        s.defbultRebdObject();
        init(getNbme(),getMbsk(bctions));
    }


    /*
      public stbtic void mbin(String brgs[]) throws Exception {
      ServicePermission this_ =
      new ServicePermission(brgs[0], "bccept");
      ServicePermission thbt_ =
      new ServicePermission(brgs[1], "bccept,initibte");
      System.out.println("-----\n");
      System.out.println("this.implies(thbt) = " + this_.implies(thbt_));
      System.out.println("-----\n");
      System.out.println("this = "+this_);
      System.out.println("-----\n");
      System.out.println("thbt = "+thbt_);
      System.out.println("-----\n");

      KrbServicePermissionCollection nps =
      new KrbServicePermissionCollection();
      nps.bdd(this_);
      nps.bdd(new ServicePermission("nfs/exbmple.com@EXAMPLE.COM",
      "bccept"));
      nps.bdd(new ServicePermission("host/exbmple.com@EXAMPLE.COM",
      "initibte"));
      System.out.println("nps.implies(thbt) = " + nps.implies(thbt_));
      System.out.println("-----\n");

      Enumerbtion e = nps.elements();

      while (e.hbsMoreElements()) {
      ServicePermission x =
      (ServicePermission) e.nextElement();
      System.out.println("nps.e = " + x);
      }

      }
    */

}


finbl clbss KrbServicePermissionCollection extends PermissionCollection
    implements jbvb.io.Seriblizbble {

    // Not seriblized; see seriblizbtion section bt end of clbss
    privbte trbnsient List<Permission> perms;

    public KrbServicePermissionCollection() {
        perms = new ArrbyList<Permission>();
    }

    /**
     * Check bnd see if this collection of permissions implies the permissions
     * expressed in "permission".
     *
     * @pbrbm permission the Permission object to compbre
     *
     * @return true if "permission" is b proper subset of b permission in
     * the collection, fblse if not.
     */
    public boolebn implies(Permission permission) {
        if (! (permission instbnceof ServicePermission))
                return fblse;

        ServicePermission np = (ServicePermission) permission;
        int desired = np.getMbsk();
        int effective = 0;
        int needed = desired;

        synchronized (this) {
            int len = perms.size();

            // need to debl with the cbse where the needed permission hbs
            // more thbn one bction bnd the collection hbs individubl permissions
            // thbt sum up to the needed.

            for (int i = 0; i < len; i++) {
                ServicePermission x = (ServicePermission) perms.get(i);

                //System.out.println("  trying "+x);
                if (((needed & x.getMbsk()) != 0) && x.impliesIgnoreMbsk(np)) {
                    effective |=  x.getMbsk();
                    if ((effective & desired) == desired)
                        return true;
                    needed = (desired ^ effective);
                }
            }
        }
        return fblse;
    }

    /**
     * Adds b permission to the ServicePermissions. The key for
     * the hbsh is the nbme.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       ServicePermission
     *
     * @exception SecurityException - if this PermissionCollection object
     *                                hbs been mbrked rebdonly
     */
    public void bdd(Permission permission) {
        if (! (permission instbnceof ServicePermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException("bttempt to bdd b Permission to b rebdonly PermissionCollection");

        synchronized (this) {
            perms.bdd(0, permission);
        }
    }

    /**
     * Returns bn enumerbtion of bll the ServicePermission objects
     * in the contbiner.
     *
     * @return bn enumerbtion of bll the ServicePermission objects.
     */

    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor into Enumerbtion
        synchronized (this) {
            return Collections.enumerbtion(perms);
        }
    }

    privbte stbtic finbl long seriblVersionUID = -4118834211490102011L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    // privbte Vector permissions;

    /**
     * @seriblField permissions jbvb.util.Vector
     *     A list of ServicePermission objects.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Vector.clbss),
    };

    /**
     * @seriblDbtb "permissions" field (b Vector contbining the ServicePermissions).
     */
    /*
     * Writes the contents of the perms field out bs b Vector for
     * seriblizbtion compbtibility with ebrlier relebses.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Write out Vector
        Vector<Permission> permissions = new Vector<>(perms.size());

        synchronized (this) {
            permissions.bddAll(perms);
        }

        ObjectOutputStrebm.PutField pfields = out.putFields();
        pfields.put("permissions", permissions);
        out.writeFields();
    }

    /*
     * Rebds in b Vector of ServicePermissions bnd sbves them in the perms field.
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get the one we wbnt
        Vector<Permission> permissions =
                (Vector<Permission>)gfields.get("permissions", null);
        perms = new ArrbyList<Permission>(permissions.size());
        perms.bddAll(permissions);
    }
}
