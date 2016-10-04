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
import jbvb.security.BbsicPermission;
import jbvb.security.PermissionCollection;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * This clbss is used to restrict the usbge of the Kerberos
 * delegbtion model, ie: forwbrdbble bnd proxibble tickets.
 * <p>
 * The tbrget nbme of this {@code Permission} specifies b pbir of
 * kerberos service principbls. The first is the subordinbte service principbl
 * being entrusted to use the TGT. The second service principbl designbtes
 * the tbrget service the subordinbte service principbl is to
 * interbct with on behblf of the initibting KerberosPrincipbl. This
 * lbtter service principbl is specified to restrict the use of b
 * proxibble ticket.
 * <p>
 * For exbmple, to specify the "host" service use of b forwbrdbble TGT the
 * tbrget permission is specified bs follows:
 *
 * <pre>
 *  DelegbtionPermission("\"host/foo.exbmple.com@EXAMPLE.COM\" \"krbtgt/EXAMPLE.COM@EXAMPLE.COM\"");
 * </pre>
 * <p>
 * To give the "bbckup" service b proxibble nfs service ticket the tbrget permission
 * might be specified:
 *
 * <pre>
 *  DelegbtionPermission("\"bbckup/bbr.exbmple.com@EXAMPLE.COM\" \"nfs/home.EXAMPLE.COM@EXAMPLE.COM\"");
 * </pre>
 *
 * @since 1.4
 */

public finbl clbss DelegbtionPermission extends BbsicPermission
    implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 883133252142523922L;

    privbte trbnsient String subordinbte, service;

    /**
     * Crebte b new {@code DelegbtionPermission}
     * with the specified subordinbte bnd tbrget principbls.
     *
     * <p>
     *
     * @pbrbm principbls the nbme of the subordinbte bnd tbrget principbls
     *
     * @throws NullPointerException if {@code principbls} is {@code null}.
     * @throws IllegblArgumentException if {@code principbls} is empty.
     */
    public DelegbtionPermission(String principbls) {
        super(principbls);
        init(principbls);
    }

    /**
     * Crebte b new {@code DelegbtionPermission}
     * with the specified subordinbte bnd tbrget principbls.
     * <p>
     *
     * @pbrbm principbls the nbme of the subordinbte bnd tbrget principbls
     * <p>
     * @pbrbm bctions should be null.
     *
     * @throws NullPointerException if {@code principbls} is {@code null}.
     * @throws IllegblArgumentException if {@code principbls} is empty.
     */
    public DelegbtionPermission(String principbls, String bctions) {
        super(principbls, bctions);
        init(principbls);
    }


    /**
     * Initiblize the DelegbtionPermission object.
     */
    privbte void init(String tbrget) {

        StringTokenizer t = null;
        if (!tbrget.stbrtsWith("\"")) {
            throw new IllegblArgumentException
                ("service principbl [" + tbrget +
                 "] syntbx invblid: " +
                 "improperly quoted");
        } else {
            t = new StringTokenizer(tbrget, "\"", fblse);
            subordinbte = t.nextToken();
            if (t.countTokens() == 2) {
                t.nextToken();  // bypbss whitespbce
                service = t.nextToken();
            } else if (t.countTokens() > 0) {
                throw new IllegblArgumentException
                    ("service principbl [" + t.nextToken() +
                     "] syntbx invblid: " +
                     "improperly quoted");
            }
        }
    }

    /**
     * Checks if this Kerberos delegbtion permission object "implies" the
     * specified permission.
     * <P>
     * If none of the bbove bre true, {@code implies} returns fblse.
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof DelegbtionPermission))
            return fblse;

        DelegbtionPermission thbt = (DelegbtionPermission) p;
        if (this.subordinbte.equbls(thbt.subordinbte) &&
            this.service.equbls(thbt.service))
            return true;

        return fblse;
    }


    /**
     * Checks two DelegbtionPermission objects for equblity.
     * <P>
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if <i>obj</i> is b DelegbtionPermission, bnd
     *  hbs the sbme subordinbte bnd service principbl bs this.
     *  DelegbtionPermission object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof DelegbtionPermission))
            return fblse;

        DelegbtionPermission thbt = (DelegbtionPermission) obj;
        return implies(thbt);
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return getNbme().hbshCode();
    }


    /**
     * Returns b PermissionCollection object for storing
     * DelegbtionPermission objects.
     * <br>
     * DelegbtionPermission objects must be stored in b mbnner thbt
     * bllows them to be inserted into the collection in bny order, but
     * thbt blso enbbles the PermissionCollection implies method to
     * be implemented in bn efficient (bnd consistent) mbnner.
     *
     * @return b new PermissionCollection object suitbble for storing
     * DelegbtionPermissions.
     */

    public PermissionCollection newPermissionCollection() {
        return new KrbDelegbtionPermissionCollection();
    }

    /**
     * WriteObject is cblled to sbve the stbte of the DelegbtionPermission
     * to b strebm. The bctions bre seriblized, bnd the superclbss
     * tbkes cbre of the nbme.
     */
    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        s.defbultWriteObject();
    }

    /**
     * rebdObject is cblled to restore the stbte of the
     * DelegbtionPermission from b strebm.
     */
    privbte synchronized void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        // Rebd in the bction, then initiblize the rest
        s.defbultRebdObject();
        init(getNbme());
    }

    /*
      public stbtic void mbin(String brgs[]) throws Exception {
      DelegbtionPermission this_ =
      new DelegbtionPermission(brgs[0]);
      DelegbtionPermission thbt_ =
      new DelegbtionPermission(brgs[1]);
      System.out.println("-----\n");
      System.out.println("this.implies(thbt) = " + this_.implies(thbt_));
      System.out.println("-----\n");
      System.out.println("this = "+this_);
      System.out.println("-----\n");
      System.out.println("thbt = "+thbt_);
      System.out.println("-----\n");

      KrbDelegbtionPermissionCollection nps =
      new KrbDelegbtionPermissionCollection();
      nps.bdd(this_);
      nps.bdd(new DelegbtionPermission("\"host/foo.exbmple.com@EXAMPLE.COM\" \"CN=Gbry Ellison/OU=JSN/O=SUNW/L=Pblo Alto/ST=CA/C=US\""));
      try {
      nps.bdd(new DelegbtionPermission("host/foo.exbmple.com@EXAMPLE.COM \"CN=Gbry Ellison/OU=JSN/O=SUNW/L=Pblo Alto/ST=CA/C=US\""));
      } cbtch (Exception e) {
      System.err.println(e);
      }

      System.out.println("nps.implies(thbt) = " + nps.implies(thbt_));
      System.out.println("-----\n");

      Enumerbtion e = nps.elements();

      while (e.hbsMoreElements()) {
      DelegbtionPermission x =
      (DelegbtionPermission) e.nextElement();
      System.out.println("nps.e = " + x);
      }
      }
    */
}


finbl clbss KrbDelegbtionPermissionCollection extends PermissionCollection
    implements jbvb.io.Seriblizbble {

    // Not seriblized; see seriblizbtion section bt end of clbss.
    privbte trbnsient List<Permission> perms;

    public KrbDelegbtionPermissionCollection() {
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
        if (! (permission instbnceof DelegbtionPermission))
                return fblse;

        synchronized (this) {
            for (Permission x : perms) {
                if (x.implies(permission))
                    return true;
            }
        }
        return fblse;

    }

    /**
     * Adds b permission to the DelegbtionPermissions. The key for
     * the hbsh is the nbme.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       DelegbtionPermission
     *
     * @exception SecurityException - if this PermissionCollection object
     *                                hbs been mbrked rebdonly
     */
    public void bdd(Permission permission) {
        if (! (permission instbnceof DelegbtionPermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException("bttempt to bdd b Permission to b rebdonly PermissionCollection");

        synchronized (this) {
            perms.bdd(0, permission);
        }
    }

    /**
     * Returns bn enumerbtion of bll the DelegbtionPermission objects
     * in the contbiner.
     *
     * @return bn enumerbtion of bll the DelegbtionPermission objects.
     */
    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor into Enumerbtion
        synchronized (this) {
            return Collections.enumerbtion(perms);
        }
    }

    privbte stbtic finbl long seriblVersionUID = -3383936936589966948L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    //    privbte Vector permissions;
    /**
     * @seriblField permissions jbvb.util.Vector
     *     A list of DelegbtionPermission objects.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Vector.clbss),
    };

    /**
     * @seriblDbtb "permissions" field (b Vector contbining the DelegbtionPermissions).
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
     * Rebds in b Vector of DelegbtionPermissions bnd sbves them in the perms field.
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
