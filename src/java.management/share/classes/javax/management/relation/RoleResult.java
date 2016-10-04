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


import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.io.Seriblizbble;

import jbvb.security.AccessController;
import jbvb.util.Iterbtor;

/**
 * Represents the result of b multiple bccess to severbl roles of b relbtion
 * (either for rebding or writing).
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-6304063118040985512L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")
public clbss RoleResult implements Seriblizbble {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 3786616013762091099L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -6304063118040985512L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("myRoleList", RoleList.clbss),
      new ObjectStrebmField("myRoleUnresList", RoleUnresolvedList.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("roleList", RoleList.clbss),
      new ObjectStrebmField("unresolvedRoleList", RoleUnresolvedList.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField roleList RoleList List of roles successfully bccessed
     * @seriblField unresolvedRoleList RoleUnresolvedList List of roles unsuccessfully bccessed
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
     * @seribl List of roles successfully bccessed
     */
    privbte RoleList roleList = null;

    /**
     * @seribl List of roles unsuccessfully bccessed
     */
    privbte RoleUnresolvedList unresolvedRoleList = null;

    //
    // Constructor
    //

    /**
     * Constructor.
     *
     * @pbrbm list  list of roles successfully bccessed.
     * @pbrbm unresolvedList  list of roles not bccessed (with problem
     * descriptions).
     */
    public RoleResult(RoleList list,
                      RoleUnresolvedList unresolvedList) {

        setRoles(list);
        setRolesUnresolved(unresolvedList);
        return;
    }

    //
    // Accessors
    //

    /**
     * Retrieves list of roles successfully bccessed.
     *
     * @return b RoleList
     *
     * @see #setRoles
     */
    public RoleList getRoles() {
        return roleList;
    }

    /**
     * Retrieves list of roles unsuccessfully bccessed.
     *
     * @return b RoleUnresolvedList.
     *
     * @see #setRolesUnresolved
     */
    public RoleUnresolvedList getRolesUnresolved() {
        return unresolvedRoleList;
    }

    /**
     * Sets list of roles successfully bccessed.
     *
     * @pbrbm list  list of roles successfully bccessed
     *
     * @see #getRoles
     */
    public void setRoles(RoleList list) {
        if (list != null) {

            roleList = new RoleList();

            for (Iterbtor<?> roleIter = list.iterbtor();
                 roleIter.hbsNext();) {
                Role currRole = (Role)(roleIter.next());
                roleList.bdd((Role)(currRole.clone()));
            }
        } else {
            roleList = null;
        }
        return;
    }

    /**
     * Sets list of roles unsuccessfully bccessed.
     *
     * @pbrbm unresolvedList  list of roles unsuccessfully bccessed
     *
     * @see #getRolesUnresolved
     */
    public void setRolesUnresolved(RoleUnresolvedList unresolvedList) {
        if (unresolvedList != null) {

            unresolvedRoleList = new RoleUnresolvedList();

            for (Iterbtor<?> roleUnresIter = unresolvedList.iterbtor();
                 roleUnresIter.hbsNext();) {
                RoleUnresolved currRoleUnres =
                    (RoleUnresolved)(roleUnresIter.next());
                unresolvedRoleList.bdd((RoleUnresolved)(currRoleUnres.clone()));
            }
        } else {
            unresolvedRoleList = null;
        }
        return;
    }

    /**
     * Deseriblizes b {@link RoleResult} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        ObjectInputStrebm.GetField fields = in.rebdFields();
        roleList = (RoleList) fields.get("myRoleList", null);
        if (fields.defbulted("myRoleList"))
        {
          throw new NullPointerException("myRoleList");
        }
        unresolvedRoleList = (RoleUnresolvedList) fields.get("myRoleUnresList", null);
        if (fields.defbulted("myRoleUnresList"))
        {
          throw new NullPointerException("myRoleUnresList");
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
     * Seriblizes b {@link RoleResult} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("myRoleList", roleList);
        fields.put("myRoleUnresList", unresolvedRoleList);
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
