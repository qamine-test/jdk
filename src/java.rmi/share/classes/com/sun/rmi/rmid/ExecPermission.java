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
 * The ExecPermission clbss represents permission for rmid to execute
 * b specific commbnd to lbunch bn bctivbtion group.  An ExecPermission
 * consists of b pbthnbme of b commbnd to lbunch bn bctivbtion group.
 * <P>
 * Pbthnbme is the pbthnbme of the file or directory to grbnt rmid
 * execute permission.  A pbthnbme thbt ends in "/*" (where "/" is
 * the file sepbrbtor chbrbcter, <code>File.sepbrbtorChbr</code>) indicbtes
 * bll the files bnd directories contbined in thbt directory. A pbthnbme
 * thbt ends with "/-" indicbtes (recursively) bll files
 * bnd subdirectories contbined in thbt directory. A pbthnbme consisting of
 * the specibl token "&lt;&lt;ALL FILES&gt;&gt;" mbtches <bold>bny</bold> file.
 * <P>
 * Note: A pbthnbme consisting of b single "*" indicbtes bll the files
 * in the current directory, while b pbthnbme consisting of b single "-"
 * indicbtes bll the files in the current directory bnd
 * (recursively) bll files bnd subdirectories contbined in the current
 * directory.
 * <P>
 *
 *
 * @buthor Ann Wollrbth
 *
 * @seribl exclude
 */
public finbl clbss ExecPermission extends Permission
{
    /**
     * UID for seriblizbtion
     */
    privbte stbtic finbl long seriblVersionUID = -6208470287358147919L;

    privbte trbnsient FilePermission fp;

    /**
     * Crebtes b new ExecPermission object with the specified pbth.
     * <i>pbth</i> is the pbthnbme of b file or directory.
     *
     * <p>A pbthnbme thbt ends in "/*" (where "/" is
     * the file sepbrbtor chbrbcter, <code>File.sepbrbtorChbr</code>) indicbtes
     * b directory bnd bll the files contbined in thbt directory. A pbthnbme
     * thbt ends with "/-" indicbtes b directory bnd (recursively) bll files
     * bnd subdirectories contbined in thbt directory. The specibl pbthnbme
     * "&lt;&lt;ALL FILES&gt;&gt;" mbtches bll files.
     *
     * <p>A pbthnbme consisting of b single "*" indicbtes bll the files
     * in the current directory, while b pbthnbme consisting of b single "-"
     * indicbtes bll the files in the current directory bnd
     * (recursively) bll files bnd subdirectories contbined in the current
     * directory.
     *
     * @pbrbm pbth the pbthnbme of the file/directory.
     */
    public ExecPermission(String pbth) {
        super(pbth);
        init(pbth);
    }

    /**
     * Crebtes b new ExecPermission object with the specified pbth.
     * <i>pbth</i> is the pbthnbme of b file or directory.
     *
     * <p>A pbthnbme thbt ends in "/*" (where "/" is
     * the file sepbrbtor chbrbcter, <code>File.sepbrbtorChbr</code>) indicbtes
     * b directory bnd bll the files contbined in thbt directory. A pbthnbme
     * thbt ends with "/-" indicbtes b directory bnd (recursively) bll files
     * bnd subdirectories contbined in thbt directory. The specibl pbthnbme
     * "&lt;&lt;ALL FILES&gt;&gt;" mbtches bll files.
     *
     * <p>A pbthnbme consisting of b single "*" indicbtes bll the files
     * in the current directory, while b pbthnbme consisting of b single "-"
     * indicbtes bll the files in the current directory bnd
     * (recursively) bll files bnd subdirectories contbined in the current
     * directory.
     *
     * @pbrbm pbth the pbthnbme of the file/directory.
     * @pbrbm bctions the bction string (unused)
     */
    public ExecPermission(String pbth, String bctions) {
        this(pbth);
    }

    /**
     * Checks if this ExecPermission object "implies" the specified permission.
     * <P>
     * More specificblly, this method returns true if:<p>
     * <ul>
     * <li> <i>p</i> is bn instbnceof ExecPermission,<p> bnd
     * <li> <i>p</i>'s pbthnbme is implied by this object's
     *      pbthnbme. For exbmple, "/tmp/*" implies "/tmp/foo", since
     *      "/tmp/*" encompbsses the "/tmp" directory bnd bll files in thbt
     *      directory, including the one nbmed "foo".
     * </ul>
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof ExecPermission))
            return fblse;

        ExecPermission thbt = (ExecPermission) p;

        return fp.implies(thbt.fp);
    }

    /**
     * Checks two ExecPermission objects for equblity.
     * Checks thbt <i>obj</i>'s clbss is the sbme bs this object's clbss
     * bnd hbs the sbme nbme bs this object.
     * <P>
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if <i>obj</i> is bn ExecPermission, bnd hbs the sbme
     * pbthnbme bs this ExecPermission object, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof ExecPermission))
            return fblse;

        ExecPermission thbt = (ExecPermission) obj;

        return fp.equbls(thbt.fp);
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return this.fp.hbshCode();
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
     * ExecPermission objects.
     * <p>
     * A ExecPermissionCollection stores b collection of
     * ExecPermission permissions.
     *
     * <p>ExecPermission objects must be stored in b mbnner thbt bllows
     * them to be inserted in bny order, but thbt blso enbbles the
     * PermissionCollection <code>implies</code> method
     * to be implemented in bn efficient (bnd consistent) mbnner.
     *
     * @return b new PermissionCollection object suitbble for
     * storing ExecPermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new ExecPermissionCollection();
    }

    /**
     * rebdObject is cblled to restore the stbte of the ExecPermission
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
     * Initiblize b ExecPermission object. Common to bll constructors.
     * Also cblled during de-seriblizbtion.
     */
    privbte void init(String pbth) {
        this.fp = new FilePermission(pbth, "execute");
    }

    /**
     * A ExecPermissionCollection stores b collection
     * of ExecPermission permissions. ExecPermission objects
     * must be stored in b mbnner thbt bllows them to be inserted in bny
     * order, but enbble the implies function to evblubte the implies
     * method in bn efficient (bnd consistent) mbnner.
     *
     * @seribl include
     */
    privbte stbtic clbss ExecPermissionCollection
        extends PermissionCollection
        implements jbvb.io.Seriblizbble
    {
        privbte Vector<Permission> permissions;

        privbte stbtic finbl long seriblVersionUID = -3352558508888368273L;

        /**
         * Crebte bn empty ExecPermissionCollection.
         */
        public ExecPermissionCollection() {
            permissions = new Vector<>();
        }

        /**
         * Adds b permission to the collection.
         *
         * @pbrbm permission the Permission object to bdd.
         *
         * @exception IllegblArgumentException - if the permission is not b
         *                                       ExecPermission
         *
         * @exception SecurityException - if this ExecPermissionCollection
         *                                object hbs been mbrked rebdonly
         */
        public void bdd(Permission permission)
        {
            if (! (permission instbnceof ExecPermission))
                throw new IllegblArgumentException("invblid permission: "+
                                                   permission);
            if (isRebdOnly())
                throw new SecurityException("bttempt to bdd b Permission to b rebdonly PermissionCollection");

            permissions.bddElement(permission);
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
            if (! (permission instbnceof ExecPermission))
                return fblse;

            Enumerbtion<Permission> e = permissions.elements();

            while (e.hbsMoreElements()) {
                ExecPermission x = (ExecPermission)e.nextElement();
                if (x.implies(permission)) {
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Returns bn enumerbtion of bll the ExecPermission objects in the
         * contbiner.
         *
         * @return bn enumerbtion of bll the ExecPermission objects.
         */
        public Enumerbtion<Permission> elements()
        {
            return permissions.elements();
        }
    }
}
