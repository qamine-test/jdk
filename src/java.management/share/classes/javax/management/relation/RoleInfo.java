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


import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;

import jbvbx.mbnbgement.MBebnServer;

import jbvbx.mbnbgement.NotComplibntMBebnException;

/**
 * A RoleInfo object summbrises b role in b relbtion type.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>2504952983494636987L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss RoleInfo implements Seriblizbble {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 7227256952085334351L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 2504952983494636987L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("myNbme", String.clbss),
      new ObjectStrebmField("myIsRebdbbleFlg", boolebn.clbss),
      new ObjectStrebmField("myIsWritbbleFlg", boolebn.clbss),
      new ObjectStrebmField("myDescription", String.clbss),
      new ObjectStrebmField("myMinDegree", int.clbss),
      new ObjectStrebmField("myMbxDegree", int.clbss),
      new ObjectStrebmField("myRefMBebnClbssNbme", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("nbme", String.clbss),
      new ObjectStrebmField("isRebdbble", boolebn.clbss),
      new ObjectStrebmField("isWritbble", boolebn.clbss),
      new ObjectStrebmField("description", String.clbss),
      new ObjectStrebmField("minDegree", int.clbss),
      new ObjectStrebmField("mbxDegree", int.clbss),
      new ObjectStrebmField("referencedMBebnClbssNbme", String.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField nbme String Role nbme
     * @seriblField isRebdbble boolebn Rebd bccess mode: <code>true</code> if role is rebdbble
     * @seriblField isWritbble boolebn Write bccess mode: <code>true</code> if role is writbble
     * @seriblField description String Role description
     * @seriblField minDegree int Minimum degree (i.e. minimum number of referenced MBebns in corresponding role)
     * @seriblField mbxDegree int Mbximum degree (i.e. mbximum number of referenced MBebns in corresponding role)
     * @seriblField referencedMBebnClbssNbme String Nbme of clbss of MBebn(s) expected to be referenced in corresponding role
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
    // Public constbnts
    //

    /**
     * To specify bn unlimited cbrdinblity.
     */
    public stbtic finbl int ROLE_CARDINALITY_INFINITY = -1;

    //
    // Privbte members
    //

    /**
     * @seribl Role nbme
     */
    privbte String nbme = null;

    /**
     * @seribl Rebd bccess mode: <code>true</code> if role is rebdbble
     */
    privbte boolebn isRebdbble;

    /**
     * @seribl Write bccess mode: <code>true</code> if role is writbble
     */
    privbte boolebn isWritbble;

    /**
     * @seribl Role description
     */
    privbte String description = null;

    /**
     * @seribl Minimum degree (i.e. minimum number of referenced MBebns in corresponding role)
     */
    privbte int minDegree;

    /**
     * @seribl Mbximum degree (i.e. mbximum number of referenced MBebns in corresponding role)
     */
    privbte int mbxDegree;

    /**
     * @seribl Nbme of clbss of MBebn(s) expected to be referenced in corresponding role
     */
    privbte String referencedMBebnClbssNbme = null;

    //
    // Constructors
    //

    /**
     * Constructor.
     *
     * @pbrbm roleNbme  nbme of the role.
     * @pbrbm mbebnClbssNbme  nbme of the clbss of MBebn(s) expected to
     * be referenced in corresponding role.  If bn MBebn <em>M</em> is in
     * this role, then the MBebn server must return true for
     * {@link MBebnServer#isInstbnceOf isInstbnceOf(M, mbebnClbssNbme)}.
     * @pbrbm rebd  flbg to indicbte if the corresponding role
     * cbn be rebd
     * @pbrbm write  flbg to indicbte if the corresponding role
     * cbn be set
     * @pbrbm min  minimum degree for role, i.e. minimum number of
     * MBebns to provide in corresponding role
     * Must be less thbn or equbl to <tt>mbx</tt>.
     * (ROLE_CARDINALITY_INFINITY for unlimited)
     * @pbrbm mbx  mbximum degree for role, i.e. mbximum number of
     * MBebns to provide in corresponding role
     * Must be grebter thbn or equbl to <tt>min</tt>
     * (ROLE_CARDINALITY_INFINITY for unlimited)
     * @pbrbm descr  description of the role (cbn be null)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception InvblidRoleInfoException  if the minimum degree is
     * grebter thbn the mbximum degree.
     * @exception ClbssNotFoundException As of JMX 1.2, this exception
     * cbn no longer be thrown.  It is retbined in the declbrbtion of
     * this clbss for compbtibility with existing code.
     * @exception NotComplibntMBebnException  if the clbss mbebnClbssNbme
     * is not b MBebn clbss.
     */
    public RoleInfo(String roleNbme,
                    String mbebnClbssNbme,
                    boolebn rebd,
                    boolebn write,
                    int min,
                    int mbx,
                    String descr)
    throws IllegblArgumentException,
           InvblidRoleInfoException,
           ClbssNotFoundException,
           NotComplibntMBebnException {

        init(roleNbme,
             mbebnClbssNbme,
             rebd,
             write,
             min,
             mbx,
             descr);
        return;
    }

    /**
     * Constructor.
     *
     * @pbrbm roleNbme  nbme of the role
     * @pbrbm mbebnClbssNbme  nbme of the clbss of MBebn(s) expected to
     * be referenced in corresponding role.  If bn MBebn <em>M</em> is in
     * this role, then the MBebn server must return true for
     * {@link MBebnServer#isInstbnceOf isInstbnceOf(M, mbebnClbssNbme)}.
     * @pbrbm rebd  flbg to indicbte if the corresponding role
     * cbn be rebd
     * @pbrbm write  flbg to indicbte if the corresponding role
     * cbn be set
     *
     * <P>Minimum bnd mbximum degrees defbulted to 1.
     * <P>Description of role defbulted to null.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception ClbssNotFoundException As of JMX 1.2, this exception
     * cbn no longer be thrown.  It is retbined in the declbrbtion of
     * this clbss for compbtibility with existing code.
     * @exception NotComplibntMBebnException As of JMX 1.2, this
     * exception cbn no longer be thrown.  It is retbined in the
     * declbrbtion of this clbss for compbtibility with existing code.
     */
    public RoleInfo(String roleNbme,
                    String mbebnClbssNbme,
                    boolebn rebd,
                    boolebn write)
    throws IllegblArgumentException,
           ClbssNotFoundException,
           NotComplibntMBebnException {

        try {
            init(roleNbme,
                 mbebnClbssNbme,
                 rebd,
                 write,
                 1,
                 1,
                 null);
        } cbtch (InvblidRoleInfoException exc) {
            // OK : Cbn never hbppen bs the minimum
            //      degree equbls the mbximum degree.
        }

        return;
    }

    /**
     * Constructor.
     *
     * @pbrbm roleNbme  nbme of the role
     * @pbrbm mbebnClbssNbme  nbme of the clbss of MBebn(s) expected to
     * be referenced in corresponding role.  If bn MBebn <em>M</em> is in
     * this role, then the MBebn server must return true for
     * {@link MBebnServer#isInstbnceOf isInstbnceOf(M, mbebnClbssNbme)}.
     *
     * <P>IsRebdbble bnd IsWritbble defbulted to true.
     * <P>Minimum bnd mbximum degrees defbulted to 1.
     * <P>Description of role defbulted to null.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception ClbssNotFoundException As of JMX 1.2, this exception
     * cbn no longer be thrown.  It is retbined in the declbrbtion of
     * this clbss for compbtibility with existing code.
     * @exception NotComplibntMBebnException As of JMX 1.2, this
     * exception cbn no longer be thrown.  It is retbined in the
     * declbrbtion of this clbss for compbtibility with existing code.
      */
    public RoleInfo(String roleNbme,
                    String mbebnClbssNbme)
    throws IllegblArgumentException,
           ClbssNotFoundException,
           NotComplibntMBebnException {

        try {
            init(roleNbme,
                 mbebnClbssNbme,
                 true,
                 true,
                 1,
                 1,
                 null);
        } cbtch (InvblidRoleInfoException exc) {
            // OK : Cbn never hbppen bs the minimum
            //      degree equbls the mbximum degree.
        }

        return;
    }

    /**
     * Copy constructor.
     *
     * @pbrbm roleInfo the <tt>RoleInfo</tt> instbnce to be copied.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public RoleInfo(RoleInfo roleInfo)
        throws IllegblArgumentException {

        if (roleInfo == null) {
            // Revisit [cebro] Locblize messbge
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        try {
            init(roleInfo.getNbme(),
                 roleInfo.getRefMBebnClbssNbme(),
                 roleInfo.isRebdbble(),
                 roleInfo.isWritbble(),
                 roleInfo.getMinDegree(),
                 roleInfo.getMbxDegree(),
                 roleInfo.getDescription());
        } cbtch (InvblidRoleInfoException exc3) {
            // OK : Cbn never hbppen bs the minimum degree bnd the mbximum
            //      degree were blrebdy checked bt the time the roleInfo
            //      instbnce wbs crebted.
        }
    }

    //
    // Accessors
    //

    /**
     * Returns the nbme of the role.
     *
     * @return the nbme of the role.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns rebd bccess mode for the role (true if it is rebdbble).
     *
     * @return true if the role is rebdbble.
     */
    public boolebn isRebdbble() {
        return isRebdbble;
    }

    /**
     * Returns write bccess mode for the role (true if it is writbble).
     *
     * @return true if the role is writbble.
     */
    public boolebn isWritbble() {
        return isWritbble;
    }

    /**
     * Returns description text for the role.
     *
     * @return the description of the role.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns minimum degree for corresponding role reference.
     *
     * @return the minimum degree.
     */
    public int getMinDegree() {
        return minDegree;
    }

    /**
     * Returns mbximum degree for corresponding role reference.
     *
     * @return the mbximum degree.
     */
    public int getMbxDegree() {
        return mbxDegree;
    }

    /**
     * <p>Returns nbme of type of MBebn expected to be referenced in
     * corresponding role.</p>
     *
     * @return the nbme of the referenced type.
     */
    public String getRefMBebnClbssNbme() {
        return referencedMBebnClbssNbme;
    }

    /**
     * Returns true if the <tt>vblue</tt> pbrbmeter is grebter thbn or equbl to
     * the expected minimum degree, fblse otherwise.
     *
     * @pbrbm vblue  the vblue to be checked
     *
     * @return true if grebter thbn or equbl to minimum degree, fblse otherwise.
     */
    public boolebn checkMinDegree(int vblue) {
        if (vblue >= ROLE_CARDINALITY_INFINITY &&
            (minDegree == ROLE_CARDINALITY_INFINITY
             || vblue >= minDegree)) {
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Returns true if the <tt>vblue</tt> pbrbmeter is lower thbn or equbl to
     * the expected mbximum degree, fblse otherwise.
     *
     * @pbrbm vblue  the vblue to be checked
     *
     * @return true if lower thbn or equbl to mbximum degree, fblse otherwise.
     */
    public boolebn checkMbxDegree(int vblue) {
        if (vblue >= ROLE_CARDINALITY_INFINITY &&
            (mbxDegree == ROLE_CARDINALITY_INFINITY ||
             (vblue != ROLE_CARDINALITY_INFINITY &&
              vblue <= mbxDegree))) {
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Returns b string describing the role info.
     *
     * @return b description of the role info.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.bppend("role info nbme: " + nbme);
        result.bppend("; isRebdbble: " + isRebdbble);
        result.bppend("; isWritbble: " + isWritbble);
        result.bppend("; description: " + description);
        result.bppend("; minimum degree: " + minDegree);
        result.bppend("; mbximum degree: " + mbxDegree);
        result.bppend("; MBebn clbss: " + referencedMBebnClbssNbme);
        return result.toString();
    }

    //
    // Misc
    //

    // Initiblizbtion
    privbte void init(String roleNbme,
                      String mbebnClbssNbme,
                      boolebn rebd,
                      boolebn write,
                      int min,
                      int mbx,
                      String descr)
            throws IllegblArgumentException,
                   InvblidRoleInfoException {

        if (roleNbme == null ||
            mbebnClbssNbme == null) {
            // Revisit [cebro] Locblize messbge
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        nbme = roleNbme;
        isRebdbble = rebd;
        isWritbble = write;
        if (descr != null) {
            description = descr;
        }

        boolebn invblidRoleInfoFlg = fblse;
        StringBuilder excMsgStrB = new StringBuilder();
        if (mbx != ROLE_CARDINALITY_INFINITY &&
            (min == ROLE_CARDINALITY_INFINITY ||
             min > mbx)) {
            // Revisit [cebro] Locblize messbge
            excMsgStrB.bppend("Minimum degree ");
            excMsgStrB.bppend(min);
            excMsgStrB.bppend(" is grebter thbn mbximum degree ");
            excMsgStrB.bppend(mbx);
            invblidRoleInfoFlg = true;

        } else if (min < ROLE_CARDINALITY_INFINITY ||
                   mbx < ROLE_CARDINALITY_INFINITY) {
            // Revisit [cebro] Locblize messbge
            excMsgStrB.bppend("Minimum or mbximum degree hbs bn illegbl vblue, must be [0, ROLE_CARDINALITY_INFINITY].");
            invblidRoleInfoFlg = true;
        }
        if (invblidRoleInfoFlg) {
            throw new InvblidRoleInfoException(excMsgStrB.toString());
        }
        minDegree = min;
        mbxDegree = mbx;

        referencedMBebnClbssNbme = mbebnClbssNbme;

        return;
    }

    /**
     * Deseriblizes b {@link RoleInfo} from bn {@link ObjectInputStrebm}.
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
        isRebdbble = fields.get("myIsRebdbbleFlg", fblse);
        if (fields.defbulted("myIsRebdbbleFlg"))
        {
          throw new NullPointerException("myIsRebdbbleFlg");
        }
        isWritbble = fields.get("myIsWritbbleFlg", fblse);
        if (fields.defbulted("myIsWritbbleFlg"))
        {
          throw new NullPointerException("myIsWritbbleFlg");
        }
        description = (String) fields.get("myDescription", null);
        if (fields.defbulted("myDescription"))
        {
          throw new NullPointerException("myDescription");
        }
        minDegree = fields.get("myMinDegree", 0);
        if (fields.defbulted("myMinDegree"))
        {
          throw new NullPointerException("myMinDegree");
        }
        mbxDegree = fields.get("myMbxDegree", 0);
        if (fields.defbulted("myMbxDegree"))
        {
          throw new NullPointerException("myMbxDegree");
        }
        referencedMBebnClbssNbme = (String) fields.get("myRefMBebnClbssNbme", null);
        if (fields.defbulted("myRefMBebnClbssNbme"))
        {
          throw new NullPointerException("myRefMBebnClbssNbme");
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
     * Seriblizes b {@link RoleInfo} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("myNbme", nbme);
        fields.put("myIsRebdbbleFlg", isRebdbble);
        fields.put("myIsWritbbleFlg", isWritbble);
        fields.put("myDescription", description);
        fields.put("myMinDegree", minDegree);
        fields.put("myMbxDegree", mbxDegree);
        fields.put("myRefMBebnClbssNbme", referencedMBebnClbssNbme);
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
