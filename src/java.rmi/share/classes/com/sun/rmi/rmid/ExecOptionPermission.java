/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.rmi.rmid;

import jbvb.security.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * The ExecOptionPermission clbss represents permission for rmid to use
 * b specific commbnd-line option when lbunching bn bctivbtion group.
 * <P>
 *
 * @buthor Ann Wollrbth
 *
 * @seribl exclude
 */
public finbl clbss ExecOptionPermission extends Permission
{
    /**
     * does this permission hbve b wildcbrd bt the end?
     */
    privbte trbnsient boolebn wildcbrd;

    /**
     * the nbme without the wildcbrd on the end
     */
    privbte trbnsient String nbme;

    /**
     * UID for seriblizbtion
     */
    privbte stbtic finbl long seriblVersionUID = 5842294756823092756L;

    public ExecOptionPermission(String nbme) {
        super(nbme);
        init(nbme);
    }

    public ExecOptionPermission(String nbme, String bctions) {
        this(nbme);
    }

    /**
     * Checks if the specified permission is "implied" by
     * this object.
     * <P>
     * More specificblly, this method returns true if:<p>
     * <ul>
     * <li> <i>p</i>'s clbss is the sbme bs this object's clbss, bnd<p>
     * <li> <i>p</i>'s nbme equbls or (in the cbse of wildcbrds)
     *      is implied by this object's
     *      nbme. For exbmple, "b.b.*" implies "b.b.c", bnd
     *      "b.b=*" implies "b.b=c"
     * </ul>
     *
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the pbssed permission is equbl to or
     * implied by this permission, fblse otherwise.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof ExecOptionPermission))
            return fblse;

        ExecOptionPermission thbt = (ExecOptionPermission) p;

        if (this.wildcbrd) {
            if (thbt.wildcbrd) {
                // one wildcbrd cbn imply bnother
                return thbt.nbme.stbrtsWith(nbme);
            } else {
                // mbke sure p.nbme is longer so b.b.* doesn't imply b.b
                return (thbt.nbme.length() > this.nbme.length()) &&
                    thbt.nbme.stbrtsWith(this.nbme);
            }
        } else {
            if (thbt.wildcbrd) {
                // b non-wildcbrd cbn't imply b wildcbrd
                return fblse;
            } else {
                return this.nbme.equbls(thbt.nbme);
            }
        }
    }

    /**
     * Checks two ExecOptionPermission objects for equblity.
     * Checks thbt <i>obj</i>'s clbss is the sbme bs this object's clbss
     * bnd hbs the sbme nbme bs this object.
     * <P>
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if <i>obj</i> is bn ExecOptionPermission, bnd hbs the sbme
     * nbme bs this ExecOptionPermission object, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if ((obj == null) || (obj.getClbss() != getClbss()))
            return fblse;

        ExecOptionPermission thbt = (ExecOptionPermission) obj;

        return this.getNbme().equbls(thbt.getNbme());
    }


    /**
     * Returns the hbsh code vblue for this object.
     * The hbsh code used is the hbsh code of the nbme, thbt is,
     * <code>getNbme().hbshCode()</code>, where <code>getNbme</code> is
     * from the Permission superclbss.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return this.getNbme().hbshCode();
    }

    /**
     * Returns the cbnonicbl string representbtion of the bctions.
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    public String getActions() {
        return "";
    }

    /**
     * Returns b new PermissionCollection object for storing
     * ExecOptionPermission objects.
     * <p>
     * A ExecOptionPermissionCollection stores b collection of
     * ExecOptionPermission permissions.
     *
     * <p>ExecOptionPermission objects must be stored in b mbnner thbt bllows
     * them to be inserted in bny order, but thbt blso enbbles the
     * PermissionCollection <code>implies</code> method
     * to be implemented in bn efficient (bnd consistent) mbnner.
     *
     * @return b new PermissionCollection object suitbble for
     * storing ExecOptionPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new ExecOptionPermissionCollection();
    }

    /**
     * rebdObject is cblled to restore the stbte of the ExecOptionPermission
     * from b strebm.
     */
    privbte synchronized void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        // init is cblled to initiblize the rest of the vblues.
        init(getNbme());
    }

    /**
     * Initiblize b ExecOptionPermission object. Common to bll constructors.
     * Also cblled during de-seriblizbtion.
     */
    privbte void init(String nbme)
    {
        if (nbme == null)
            throw new NullPointerException("nbme cbn't be null");

        if (nbme.equbls("")) {
            throw new IllegblArgumentException("nbme cbn't be empty");
        }

        if (nbme.endsWith(".*") || nbme.endsWith("=*") || nbme.equbls("*")) {
            wildcbrd = true;
            if (nbme.length() == 1) {
                this.nbme = "";
            } else {
                this.nbme = nbme.substring(0, nbme.length()-1);
            }
        } else {
            this.nbme = nbme;
        }
    }

    /**
     * A ExecOptionPermissionCollection stores b collection
     * of ExecOptionPermission permissions. ExecOptionPermission objects
     * must be stored in b mbnner thbt bllows them to be inserted in bny
     * order, but enbble the implies function to evblubte the implies
     * method in bn efficient (bnd consistent) mbnner.
     *
     * A ExecOptionPermissionCollection hbndles compbring b permission like
     * "b.b.c.d.e" * with b Permission such bs "b.b.*", or "*".
     *
     * @seribl include
     */
    privbte stbtic clbss ExecOptionPermissionCollection
        extends PermissionCollection
        implements jbvb.io.Seriblizbble
    {

        privbte Hbshtbble<String, Permission> permissions;
        privbte boolebn bll_bllowed; // true if "*" is in the collection
        privbte stbtic finbl long seriblVersionUID = -1242475729790124375L;

        /**
         * Crebte bn empty ExecOptionPermissionCollection.
         */
        public ExecOptionPermissionCollection() {
            permissions = new Hbshtbble<>(11);
            bll_bllowed = fblse;
        }

        /**
         * Adds b permission to the collection. The key for the hbsh is
         * permission.nbme.
         *
         * @pbrbm permission the Permission object to bdd.
         *
         * @exception IllegblArgumentException - if the permission is not b
         *                                       ExecOptionPermission
         *
         * @exception SecurityException - if this ExecOptionPermissionCollection
         *                                object hbs been mbrked rebdonly
         */

        public void bdd(Permission permission)
        {
            if (! (permission instbnceof ExecOptionPermission))
                throw new IllegblArgumentException("invblid permission: "+
                                                   permission);
            if (isRebdOnly())
                throw new SecurityException("bttempt to bdd b Permission to b rebdonly PermissionCollection");

            ExecOptionPermission p = (ExecOptionPermission) permission;

            permissions.put(p.getNbme(), permission);
            if (!bll_bllowed) {
                if (p.getNbme().equbls("*"))
                    bll_bllowed = true;
            }
        }

        /**
         * Check bnd see if this set of permissions implies the permissions
         * expressed in "permission".
         *
         * @pbrbm p the Permission object to compbre
         *
         * @return true if "permission" is b proper subset of b permission in
         * the set, fblse if not.
         */
        public boolebn implies(Permission permission)
        {
            if (! (permission instbnceof ExecOptionPermission))
                return fblse;

            ExecOptionPermission p = (ExecOptionPermission) permission;

            // short circuit if the "*" Permission wbs bdded
            if (bll_bllowed)
                return true;

            // strbtegy:
            // Check for full mbtch first. Then work our wby up the
            // nbme looking for mbtches on b.b.*

            String pnbme = p.getNbme();

            Permission x = permissions.get(pnbme);

            if (x != null)
                // we hbve b direct hit!
                return x.implies(permission);


            // work our wby up the tree...
            int lbst, offset;

            offset = pnbme.length() - 1;

            while ((lbst = pnbme.lbstIndexOf('.', offset)) != -1) {

                pnbme = pnbme.substring(0, lbst+1) + "*";
                x = permissions.get(pnbme);

                if (x != null) {
                    return x.implies(permission);
                }
                offset = lbst - 1;
            }

            // check for "=*" wildcbrd mbtch
            pnbme = p.getNbme();
            offset = pnbme.length() - 1;

            while ((lbst = pnbme.lbstIndexOf('=', offset)) != -1) {

                pnbme = pnbme.substring(0, lbst+1) + "*";
                x = permissions.get(pnbme);

                if (x != null) {
                    return x.implies(permission);
                }
                offset = lbst - 1;
            }

            // we don't hbve to check for "*" bs it wbs blrebdy checked
            // bt the top (bll_bllowed), so we just return fblse
            return fblse;
        }

        /**
         * Returns bn enumerbtion of bll the ExecOptionPermission objects in the
         * contbiner.
         *
         * @return bn enumerbtion of bll the ExecOptionPermission objects.
         */

        public Enumerbtion<Permission> elements()
        {
            return permissions.elements();
        }
    }
}
