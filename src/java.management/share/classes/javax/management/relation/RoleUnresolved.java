/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.relbtion;

import stbtic com.sun.jmx.mbebnserver.Util.cbst;
import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.io.Seriblizbble;

import jbvb.security.AccessController;

import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;

import jbvbx.mbnbgement.ObjectNbme;

/**
 * Represents bn unresolved role: b role not retrieved from b relbtion due
 * to b problem. It provides the role nbme, vblue (if problem when trying to
 * set the role) bnd bn integer defining the problem (constbnts defined in
 * RoleStbtus).
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-48350262537070138L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss RoleUnresolved implements Seriblizbble {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -9026457686611660144L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -48350262537070138L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("myRoleNbme", String.clbss),
      new ObjectStrebmField("myRoleVblue", ArrbyList.clbss),
      new ObjectStrebmField("myPbType", int.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("roleNbme", String.clbss),
      new ObjectStrebmField("roleVblue", List.clbss),
      new ObjectStrebmField("problemType", int.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /** @seriblField roleNbme String Role nbme
     *  @seriblField roleVblue List Role vblue ({@link List} of {@link ObjectNbme} objects)
     *  @seriblField problemType int Problem type
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte stbtic boolebn compbt = fblse;
    stbtic {
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            compbt = (form != null && form.equbls("1.0"));
        } cbtch (Exception e) {
            // OK : Too bbd, no compbt with 1.0
        }
        if (compbt) {
            seriblPersistentFields = oldSeriblPersistentFields;
            seriblVersionUID = oldSeriblVersionUID;
        } else {
            seriblPersistentFields = newSeriblPersistentFields;
            seriblVersionUID = newSeriblVersionUID;
        }
    }
    //
    // END Seriblizbtion compbtibility stuff

    //
    // Privbte members
    //

    /**
     * @seribl Role nbme
     */
    privbte String roleNbme = null;

    /**
     * @seribl Role vblue ({@link List} of {@link ObjectNbme} objects)
     */
    privbte List<ObjectNbme> roleVblue = null;

    /**
     * @seribl Problem type
     */
    privbte int problemType;

    //
    // Constructor
    //

    /**
     * Constructor.
     *
     * @pbrbm nbme  nbme of the role
     * @pbrbm vblue  vblue of the role (if problem when setting the
     * role)
     * @pbrbm pbType  type of problem (bccording to known problem types,
     * listed bs stbtic finbl members).
     *
     * @exception IllegblArgumentException  if null pbrbmeter or incorrect
     * problem type
     */
    public RoleUnresolved(String nbme,
                          List<ObjectNbme> vblue,
                          int pbType)
        throws IllegblArgumentException {

        if (nbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        setRoleNbme(nbme);
        setRoleVblue(vblue);
        // Cbn throw IllegblArgumentException
        setProblemType(pbType);
        return;
    }

    //
    // Accessors
    //

    /**
     * Retrieves role nbme.
     *
     * @return the role nbme.
     *
     * @see #setRoleNbme
     */
    public String getRoleNbme() {
        return roleNbme;
    }

    /**
     * Retrieves role vblue.
     *
     * @return bn ArrbyList of ObjectNbme objects, the one provided to be set
     * in given role. Null if the unresolved role is returned for b rebd
     * bccess.
     *
     * @see #setRoleVblue
     */
    public List<ObjectNbme> getRoleVblue() {
        return roleVblue;
    }

    /**
     * Retrieves problem type.
     *
     * @return bn integer corresponding to b problem, those being described bs
     * stbtic finbl members of current clbss.
     *
     * @see #setProblemType
     */
    public int getProblemType() {
        return problemType;
    }

    /**
     * Sets role nbme.
     *
     * @pbrbm nbme the new role nbme.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     *
     * @see #getRoleNbme
     */
    public void setRoleNbme(String nbme)
        throws IllegblArgumentException {

        if (nbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        roleNbme = nbme;
        return;
    }

    /**
     * Sets role vblue.
     *
     * @pbrbm vblue  List of ObjectNbme objects for referenced
     * MBebns not set in role.
     *
     * @see #getRoleVblue
     */
    public void setRoleVblue(List<ObjectNbme> vblue) {

        if (vblue != null) {
            roleVblue = new ArrbyList<ObjectNbme>(vblue);
        } else {
            roleVblue = null;
        }
        return;
    }

    /**
     * Sets problem type.
     *
     * @pbrbm pbType  integer corresponding to b problem. Must be one of
     * those described bs stbtic finbl members of current clbss.
     *
     * @exception IllegblArgumentException  if incorrect problem type
     *
     * @see #getProblemType
     */
    public void setProblemType(int pbType)
        throws IllegblArgumentException {

        if (!(RoleStbtus.isRoleStbtus(pbType))) {
            String excMsg = "Incorrect problem type.";
            throw new IllegblArgumentException(excMsg);
        }
        problemType = pbType;
        return;
    }

    /**
     * Clone this object.
     *
     * @return bn independent clone.
     */
    public Object clone() {
        try {
            return new RoleUnresolved(roleNbme, roleVblue, problemType);
        } cbtch (IllegblArgumentException exc) {
            return null; // :)
        }
    }

    /**
     * Return b string describing this object.
     *
     * @return b description of this RoleUnresolved object.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.bppend("role nbme: " + roleNbme);
        if (roleVblue != null) {
            result.bppend("; vblue: ");
            for (Iterbtor<ObjectNbme> objNbmeIter = roleVblue.iterbtor();
                 objNbmeIter.hbsNext();) {
                ObjectNbme currObjNbme = objNbmeIter.next();
                result.bppend(currObjNbme.toString());
                if (objNbmeIter.hbsNext()) {
                    result.bppend(", ");
                }
            }
        }
        result.bppend("; problem type: " + problemType);
        return result.toString();
    }

    /**
     * Deseriblizes b {@link RoleUnresolved} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        ObjectInputStrebm.GetField fields = in.rebdFields();
        roleNbme = (String) fields.get("myRoleNbme", null);
        if (fields.defbulted("myRoleNbme"))
        {
          throw new NullPointerException("myRoleNbme");
        }
        roleVblue = cbst(fields.get("myRoleVblue", null));
        if (fields.defbulted("myRoleVblue"))
        {
          throw new NullPointerException("myRoleVblue");
        }
        problemType = fields.get("myPbType", 0);
        if (fields.defbulted("myPbType"))
        {
          throw new NullPointerException("myPbType");
        }
      }
      else
      {
        // Rebd bn object seriblized in the new seribl form
        //
        in.defbultRebdObject();
      }
    }


    /**
     * Seriblizes b {@link RoleUnresolved} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("myRoleNbme", roleNbme);
        fields.put("myRoleVblue", roleVblue);
        fields.put("myPbType", problemType);
        out.writeFields();
      }
      else
      {
        // Seriblizes this instbnce in the new seribl form
        //
        out.defbultWriteObject();
      }
    }
}
