/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic com.sun.jmx.defbults.JmxProperties.RELATION_LOGGER;
import stbtic com.sun.jmx.mbebnserver.Util.cbst;
import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;

import jbvb.security.AccessController;

import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.logging.Level;

/**
 * A RelbtionTypeSupport object implements the RelbtionType interfbce.
 * <P>It represents b relbtion type, providing role informbtion for ebch role
 * expected to be supported in every relbtion of thbt type.
 *
 * <P>A relbtion type includes b relbtion type nbme bnd b list of
 * role infos (represented by RoleInfo objects).
 *
 * <P>A relbtion type hbs to be declbred in the Relbtion Service:
 * <P>- either using the crebteRelbtionType() method, where b RelbtionTypeSupport
 * object will be crebted bnd kept in the Relbtion Service
 * <P>- either using the bddRelbtionType() method where the user hbs to crebte
 * bn object implementing the RelbtionType interfbce, bnd this object will be
 * used bs representing b relbtion type in the Relbtion Service.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>4611072955724144607L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss RelbtionTypeSupport implements RelbtionType {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -8179019472410837190L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 4611072955724144607L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("myTypeNbme", String.clbss),
      new ObjectStrebmField("myRoleNbme2InfoMbp", HbshMbp.clbss),
      new ObjectStrebmField("myIsInRelServFlg", boolebn.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("typeNbme", String.clbss),
      new ObjectStrebmField("roleNbme2InfoMbp", Mbp.clbss),
      new ObjectStrebmField("isInRelbtionService", boolebn.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField typeNbme String Relbtion type nbme
     * @seriblField roleNbme2InfoMbp Mbp {@link Mbp} holding the mbpping:
     *              &lt;role nbme ({@link String})&gt; -&gt; &lt;role info ({@link RoleInfo} object)&gt;
     * @seriblField isInRelbtionService boolebn Flbg specifying whether the relbtion type hbs been declbred in the
     *              Relbtion Service (so cbn no longer be updbted)
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
     * @seribl Relbtion type nbme
     */
    privbte String typeNbme = null;

    /**
     * @seribl {@link Mbp} holding the mbpping:
     *           &lt;role nbme ({@link String})&gt; -&gt; &lt;role info ({@link RoleInfo} object)&gt;
     */
    privbte Mbp<String,RoleInfo> roleNbme2InfoMbp =
        new HbshMbp<String,RoleInfo>();

    /**
     * @seribl Flbg specifying whether the relbtion type hbs been declbred in the
     *         Relbtion Service (so cbn no longer be updbted)
     */
    privbte boolebn isInRelbtionService = fblse;

    //
    // Constructors
    //

    /**
     * Constructor where bll role definitions bre dynbmicblly crebted bnd
     * pbssed bs pbrbmeter.
     *
     * @pbrbm relbtionTypeNbme  Nbme of relbtion type
     * @pbrbm roleInfoArrby  List of role definitions (RoleInfo objects)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception InvblidRelbtionTypeException  if:
     * <P>- the sbme nbme hbs been used for two different roles
     * <P>- no role info provided
     * <P>- one null role info provided
     */
    public RelbtionTypeSupport(String relbtionTypeNbme,
                            RoleInfo[] roleInfoArrby)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (relbtionTypeNbme == null || roleInfoArrby == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionTypeSupport.clbss.getNbme(),
                "RelbtionTypeSupport", relbtionTypeNbme);

        // Cbn throw InvblidRelbtionTypeException, ClbssNotFoundException
        // bnd NotComplibntMBebnException
        initMembers(relbtionTypeNbme, roleInfoArrby);

        RELATION_LOGGER.exiting(RelbtionTypeSupport.clbss.getNbme(),
                "RelbtionTypeSupport");
        return;
    }

    /**
     * Constructor to be used for subclbsses.
     *
     * @pbrbm relbtionTypeNbme  Nbme of relbtion type.
     *
     * @exception IllegblArgumentException  if null pbrbmeter.
     */
    protected RelbtionTypeSupport(String relbtionTypeNbme)
    {
        if (relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionTypeSupport.clbss.getNbme(),
                "RelbtionTypeSupport", relbtionTypeNbme);

        typeNbme = relbtionTypeNbme;

        RELATION_LOGGER.exiting(RelbtionTypeSupport.clbss.getNbme(),
                "RelbtionTypeSupport");
        return;
    }

    //
    // Accessors
    //

    /**
     * Returns the relbtion type nbme.
     *
     * @return the relbtion type nbme.
     */
    public String getRelbtionTypeNbme() {
        return typeNbme;
    }

    /**
     * Returns the list of role definitions (ArrbyList of RoleInfo objects).
     */
    public List<RoleInfo> getRoleInfos() {
        return new ArrbyList<RoleInfo>(roleNbme2InfoMbp.vblues());
    }

    /**
     * Returns the role info (RoleInfo object) for the given role info nbme
     * (null if not found).
     *
     * @pbrbm roleInfoNbme  role info nbme
     *
     * @return RoleInfo object providing role definition
     * does not exist
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RoleInfoNotFoundException  if no role info with thbt nbme in
     * relbtion type.
     */
    public RoleInfo getRoleInfo(String roleInfoNbme)
        throws IllegblArgumentException,
               RoleInfoNotFoundException {

        if (roleInfoNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionTypeSupport.clbss.getNbme(),
                "getRoleInfo", roleInfoNbme);

        // No null RoleInfo bllowed, so use get()
        RoleInfo result = roleNbme2InfoMbp.get(roleInfoNbme);

        if (result == null) {
            StringBuilder excMsgStrB = new StringBuilder();
            String excMsg = "No role info for role ";
            excMsgStrB.bppend(excMsg);
            excMsgStrB.bppend(roleInfoNbme);
            throw new RoleInfoNotFoundException(excMsgStrB.toString());
        }

        RELATION_LOGGER.exiting(RelbtionTypeSupport.clbss.getNbme(),
                "getRoleInfo");
        return result;
    }

    //
    // Misc
    //

    /**
     * Add b role info.
     * This method of course should not be used bfter the crebtion of the
     * relbtion type, becbuse updbting it would invblidbte thbt the relbtions
     * crebted bssocibted to thbt type still conform to it.
     * Cbn throw b RuntimeException if trying to updbte b relbtion type
     * declbred in the Relbtion Service.
     *
     * @pbrbm roleInfo  role info to be bdded.
     *
     * @exception IllegblArgumentException  if null pbrbmeter.
     * @exception InvblidRelbtionTypeException  if there is blrebdy b role
     *  info in current relbtion type with the sbme nbme.
     */
    protected void bddRoleInfo(RoleInfo roleInfo)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (roleInfo == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionTypeSupport.clbss.getNbme(),
                "bddRoleInfo", roleInfo);

        if (isInRelbtionService) {
            // Trying to updbte b declbred relbtion type
            String excMsg = "Relbtion type cbnnot be updbted bs it is declbred in the Relbtion Service.";
            throw new RuntimeException(excMsg);
        }

        String roleNbme = roleInfo.getNbme();

        // Checks if the role info hbs blrebdy been described
        if (roleNbme2InfoMbp.contbinsKey(roleNbme)) {
            StringBuilder excMsgStrB = new StringBuilder();
            String excMsg = "Two role infos provided for role ";
            excMsgStrB.bppend(excMsg);
            excMsgStrB.bppend(roleNbme);
            throw new InvblidRelbtionTypeException(excMsgStrB.toString());
        }

        roleNbme2InfoMbp.put(roleNbme, new RoleInfo(roleInfo));

        RELATION_LOGGER.exiting(RelbtionTypeSupport.clbss.getNbme(),
                "bddRoleInfo");
        return;
    }

    // Sets the internbl flbg to specify thbt the relbtion type hbs been
    // declbred in the Relbtion Service
    void setRelbtionServiceFlbg(boolebn flbg) {
        isInRelbtionService = flbg;
        return;
    }

    // Initiblizes the members, i.e. type nbme bnd role info list.
    //
    // -pbrbm relbtionTypeNbme  Nbme of relbtion type
    // -pbrbm roleInfoArrby  List of role definitions (RoleInfo objects)
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception InvblidRelbtionTypeException  If:
    //  - the sbme nbme hbs been used for two different roles
    //  - no role info provided
    //  - one null role info provided
    privbte void initMembers(String relbtionTypeNbme,
                             RoleInfo[] roleInfoArrby)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (relbtionTypeNbme == null || roleInfoArrby == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionTypeSupport.clbss.getNbme(),
                "initMembers", relbtionTypeNbme);

        typeNbme = relbtionTypeNbme;

        // Verifies role infos before setting them
        // Cbn throw InvblidRelbtionTypeException
        checkRoleInfos(roleInfoArrby);

        for (int i = 0; i < roleInfoArrby.length; i++) {
            RoleInfo currRoleInfo = roleInfoArrby[i];
            roleNbme2InfoMbp.put(currRoleInfo.getNbme(),
                                 new RoleInfo(currRoleInfo));
        }

        RELATION_LOGGER.exiting(RelbtionTypeSupport.clbss.getNbme(),
                "initMembers");
        return;
    }

    // Checks the given RoleInfo brrby to verify thbt:
    // - the brrby is not empty
    // - it does not contbin b null element
    // - b given role nbme is used only for one RoleInfo
    //
    // -pbrbm roleInfoArrby  brrby to be checked
    //
    // -exception IllegblArgumentException
    // -exception InvblidRelbtionTypeException  If:
    //  - the sbme nbme hbs been used for two different roles
    //  - no role info provided
    //  - one null role info provided
    stbtic void checkRoleInfos(RoleInfo[] roleInfoArrby)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (roleInfoArrby == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        if (roleInfoArrby.length == 0) {
            // No role info provided
            String excMsg = "No role info provided.";
            throw new InvblidRelbtionTypeException(excMsg);
        }


        Set<String> roleNbmes = new HbshSet<String>();

        for (int i = 0; i < roleInfoArrby.length; i++) {
            RoleInfo currRoleInfo = roleInfoArrby[i];

            if (currRoleInfo == null) {
                String excMsg = "Null role info provided.";
                throw new InvblidRelbtionTypeException(excMsg);
            }

            String roleNbme = currRoleInfo.getNbme();

            // Checks if the role info hbs blrebdy been described
            if (roleNbmes.contbins(roleNbme)) {
                StringBuilder excMsgStrB = new StringBuilder();
                String excMsg = "Two role infos provided for role ";
                excMsgStrB.bppend(excMsg);
                excMsgStrB.bppend(roleNbme);
                throw new InvblidRelbtionTypeException(excMsgStrB.toString());
            }
            roleNbmes.bdd(roleNbme);
        }

        return;
    }


    /**
     * Deseriblizes b {@link RelbtionTypeSupport} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        ObjectInputStrebm.GetField fields = in.rebdFields();
        typeNbme = (String) fields.get("myTypeNbme", null);
        if (fields.defbulted("myTypeNbme"))
        {
          throw new NullPointerException("myTypeNbme");
        }
        roleNbme2InfoMbp = cbst(fields.get("myRoleNbme2InfoMbp", null));
        if (fields.defbulted("myRoleNbme2InfoMbp"))
        {
          throw new NullPointerException("myRoleNbme2InfoMbp");
        }
        isInRelbtionService = fields.get("myIsInRelServFlg", fblse);
        if (fields.defbulted("myIsInRelServFlg"))
        {
          throw new NullPointerException("myIsInRelServFlg");
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
     * Seriblizes b {@link RelbtionTypeSupport} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("myTypeNbme", typeNbme);
        fields.put("myRoleNbme2InfoMbp", roleNbme2InfoMbp);
        fields.put("myIsInRelServFlg", isInRelbtionService);
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
