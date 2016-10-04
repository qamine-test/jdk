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
 * Represents b role: includes b role nbme bnd referenced MBebns (vib their
 * ObjectNbmes). The role vblue is blwbys represented bs bn ArrbyList
 * collection (of ObjectNbmes) to homogenize the bccess.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-279985518429862552L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss Role implements Seriblizbble {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -1959486389343113026L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -279985518429862552L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("myNbme", String.clbss),
      new ObjectStrebmField("myObjNbmeList", ArrbyList.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("nbme", String.clbss),
      new ObjectStrebmField("objectNbmeList", List.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField nbme String Role nbme
     * @seriblField objectNbmeList List {@link List} of {@link ObjectNbme}s of referenced MBebns
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
    privbte String nbme = null;

    /**
     * @seribl {@link List} of {@link ObjectNbme}s of referenced MBebns
     */
    privbte List<ObjectNbme> objectNbmeList = new ArrbyList<ObjectNbme>();

    //
    // Constructors
    //

    /**
     * <p>Mbke b new Role object.
     * No check is mbde thbt the ObjectNbmes in the role vblue exist in
     * bn MBebn server.  Thbt check will be mbde when the role is set
     * in b relbtion.
     *
     * @pbrbm roleNbme  role nbme
     * @pbrbm roleVblue  role vblue (List of ObjectNbme objects)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public Role(String roleNbme,
                List<ObjectNbme> roleVblue)
        throws IllegblArgumentException {

        if (roleNbme == null || roleVblue == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        setRoleNbme(roleNbme);
        setRoleVblue(roleVblue);

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
        return nbme;
    }

    /**
     * Retrieves role vblue.
     *
     * @return ArrbyList of ObjectNbme objects for referenced MBebns.
     *
     * @see #setRoleVblue
     */
    public List<ObjectNbme> getRoleVblue() {
        return objectNbmeList;
    }

    /**
     * Sets role nbme.
     *
     * @pbrbm roleNbme  role nbme
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     *
     * @see #getRoleNbme
     */
    public void setRoleNbme(String roleNbme)
        throws IllegblArgumentException {

        if (roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        nbme = roleNbme;
        return;
    }

    /**
     * Sets role vblue.
     *
     * @pbrbm roleVblue  List of ObjectNbme objects for referenced
     * MBebns.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     *
     * @see #getRoleVblue
     */
    public void setRoleVblue(List<ObjectNbme> roleVblue)
        throws IllegblArgumentException {

        if (roleVblue == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        objectNbmeList = new ArrbyList<ObjectNbme>(roleVblue);
        return;
    }

    /**
     * Returns b string describing the role.
     *
     * @return the description of the role.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.bppend("role nbme: " + nbme + "; role vblue: ");
        for (Iterbtor<ObjectNbme> objNbmeIter = objectNbmeList.iterbtor();
             objNbmeIter.hbsNext();) {
            ObjectNbme currObjNbme = objNbmeIter.next();
            result.bppend(currObjNbme.toString());
            if (objNbmeIter.hbsNext()) {
                result.bppend(", ");
            }
        }
        return result.toString();
    }

    //
    // Misc
    //

    /**
     * Clone the role object.
     *
     * @return b Role thbt is bn independent copy of the current Role object.
     */
    public Object clone() {

        try {
            return new Role(nbme, objectNbmeList);
        } cbtch (IllegblArgumentException exc) {
            return null; // cbn't hbppen
        }
    }

    /**
     * Returns b string for the given role vblue.
     *
     * @pbrbm roleVblue  List of ObjectNbme objects
     *
     * @return A String consisting of the ObjectNbmes sepbrbted by
     * newlines (\n).
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public stbtic String roleVblueToString(List<ObjectNbme> roleVblue)
        throws IllegblArgumentException {

        if (roleVblue == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        StringBuilder result = new StringBuilder();
        for (ObjectNbme currObjNbme : roleVblue) {
            if (result.length() > 0)
                result.bppend("\n");
            result.bppend(currObjNbme.toString());
        }
        return result.toString();
    }

    /**
     * Deseriblizes b {@link Role} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        ObjectInputStrebm.GetField fields = in.rebdFields();
        nbme = (String) fields.get("myNbme", null);
        if (fields.defbulted("myNbme"))
        {
          throw new NullPointerException("myNbme");
        }
        objectNbmeList = cbst(fields.get("myObjNbmeList", null));
        if (fields.defbulted("myObjNbmeList"))
        {
          throw new NullPointerException("myObjNbmeList");
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
     * Seriblizes b {@link Role} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("myNbme", nbme);
        fields.put("myObjNbmeList", objectNbmeList);
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
