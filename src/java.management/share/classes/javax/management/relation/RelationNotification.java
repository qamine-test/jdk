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

pbckbge jbvbx.mbnbgement.relbtion;

import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.ObjectNbme;

import jbvb.io.InvblidObjectException;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Set;

import com.sun.jmx.mbebnserver.GetPropertyAction;
import stbtic com.sun.jmx.mbebnserver.Util.cbst;

/**
 * A notificbtion of b chbnge in the Relbtion Service.
 * A RelbtionNotificbtion notificbtion is sent when b relbtion is crebted vib
 * the Relbtion Service, or bn MBebn is bdded bs b relbtion in the Relbtion
 * Service, or b role is updbted in b relbtion, or b relbtion is removed from
 * the Relbtion Service.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-6871117877523310399L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss RelbtionNotificbtion extends Notificbtion {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -2126464566505527147L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -6871117877523310399L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
        new ObjectStrebmField("myNewRoleVblue", ArrbyList.clbss),
        new ObjectStrebmField("myOldRoleVblue", ArrbyList.clbss),
        new ObjectStrebmField("myRelId", String.clbss),
        new ObjectStrebmField("myRelObjNbme", ObjectNbme.clbss),
        new ObjectStrebmField("myRelTypeNbme", String.clbss),
        new ObjectStrebmField("myRoleNbme", String.clbss),
        new ObjectStrebmField("myUnregMBebnList", ArrbyList.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
        new ObjectStrebmField("newRoleVblue", List.clbss),
        new ObjectStrebmField("oldRoleVblue", List.clbss),
        new ObjectStrebmField("relbtionId", String.clbss),
        new ObjectStrebmField("relbtionObjNbme", ObjectNbme.clbss),
        new ObjectStrebmField("relbtionTypeNbme", String.clbss),
        new ObjectStrebmField("roleNbme", String.clbss),
        new ObjectStrebmField("unregisterMBebnList", List.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField relbtionId String Relbtion identifier of
     * crebted/removed/updbted relbtion
     * @seriblField relbtionTypeNbme String Relbtion type nbme of
     * crebted/removed/updbted relbtion
     * @seriblField relbtionObjNbme ObjectNbme {@link ObjectNbme} of
     * the relbtion MBebn of crebted/removed/updbted relbtion (only if
     * the relbtion is represented by bn MBebn)
     * @seriblField unregisterMBebnList List List of {@link
     * ObjectNbme}s of referenced MBebns to be unregistered due to
     * relbtion removbl
     * @seriblField roleNbme String Nbme of updbted role (only for role updbte)
     * @seriblField oldRoleVblue List Old role vblue ({@link
     * ArrbyList} of {@link ObjectNbme}s) (only for role updbte)
     * @seriblField newRoleVblue List New role vblue ({@link
     * ArrbyList} of {@link ObjectNbme}s) (only for role updbte)
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
    // Notificbtion types
    //

    /**
     * Type for the crebtion of bn internbl relbtion.
     */
    public stbtic finbl String RELATION_BASIC_CREATION = "jmx.relbtion.crebtion.bbsic";
    /**
     * Type for the relbtion MBebn bdded into the Relbtion Service.
     */
    public stbtic finbl String RELATION_MBEAN_CREATION = "jmx.relbtion.crebtion.mbebn";
    /**
     * Type for bn updbte of bn internbl relbtion.
     */
    public stbtic finbl String RELATION_BASIC_UPDATE = "jmx.relbtion.updbte.bbsic";
    /**
     * Type for the updbte of b relbtion MBebn.
     */
    public stbtic finbl String RELATION_MBEAN_UPDATE = "jmx.relbtion.updbte.mbebn";
    /**
     * Type for the removbl from the Relbtion Service of bn internbl relbtion.
     */
    public stbtic finbl String RELATION_BASIC_REMOVAL = "jmx.relbtion.removbl.bbsic";
    /**
     * Type for the removbl from the Relbtion Service of b relbtion MBebn.
     */
    public stbtic finbl String RELATION_MBEAN_REMOVAL = "jmx.relbtion.removbl.mbebn";

    //
    // Privbte members
    //

    /**
     * @seribl Relbtion identifier of crebted/removed/updbted relbtion
     */
    privbte String relbtionId = null;

    /**
     * @seribl Relbtion type nbme of crebted/removed/updbted relbtion
     */
    privbte String relbtionTypeNbme = null;

    /**
     * @seribl {@link ObjectNbme} of the relbtion MBebn of crebted/removed/updbted relbtion
     *         (only if the relbtion is represented by bn MBebn)
     */
    privbte ObjectNbme relbtionObjNbme = null;

    /**
     * @seribl List of {@link ObjectNbme}s of referenced MBebns to be unregistered due to
     *         relbtion removbl
     */
    privbte List<ObjectNbme> unregisterMBebnList = null;

    /**
     * @seribl Nbme of updbted role (only for role updbte)
     */
    privbte String roleNbme = null;

    /**
     * @seribl Old role vblue ({@link ArrbyList} of {@link ObjectNbme}s) (only for role updbte)
     */
    privbte List<ObjectNbme> oldRoleVblue = null;

    /**
     * @seribl New role vblue ({@link ArrbyList} of {@link ObjectNbme}s) (only for role updbte)
     */
    privbte List<ObjectNbme> newRoleVblue = null;

    //
    // Constructors
    //

    /**
     * Crebtes b notificbtion for either b relbtion crebtion (RelbtionSupport
     * object crebted internblly in the Relbtion Service, or bn MBebn bdded bs b
     * relbtion) or for b relbtion removbl from the Relbtion Service.
     *
     * @pbrbm notifType  type of the notificbtion; either:
     * <P>- RELATION_BASIC_CREATION
     * <P>- RELATION_MBEAN_CREATION
     * <P>- RELATION_BASIC_REMOVAL
     * <P>- RELATION_MBEAN_REMOVAL
     * @pbrbm sourceObj  source object, sending the notificbtion.  This is either
     * bn ObjectNbme or b RelbtionService object.  In the lbtter cbse it must be
     * the MBebn emitting the notificbtion; the MBebn Server will rewrite the
     * source to be the ObjectNbme under which thbt MBebn is registered.
     * @pbrbm sequence  sequence number to identify the notificbtion
     * @pbrbm timeStbmp  time stbmp
     * @pbrbm messbge  humbn-rebdbble messbge describing the notificbtion
     * @pbrbm id  relbtion id identifying the relbtion in the Relbtion
     * Service
     * @pbrbm typeNbme  nbme of the relbtion type
     * @pbrbm objectNbme  ObjectNbme of the relbtion object if it is bn MBebn
     * (null for relbtions internblly hbndled by the Relbtion Service)
     * @pbrbm unregMBebnList  list of ObjectNbmes of referenced MBebns
     * expected to be unregistered due to relbtion removbl (only for removbl,
     * due to CIM qublifiers, cbn be null)
     *
     * @exception IllegblArgumentException  if:
     * <P>- no vblue for the notificbtion type
     * <P>- the notificbtion type is not RELATION_BASIC_CREATION,
     * RELATION_MBEAN_CREATION, RELATION_BASIC_REMOVAL or
     * RELATION_MBEAN_REMOVAL
     * <P>- no source object
     * <P>- the source object is not b Relbtion Service
     * <P>- no relbtion id
     * <P>- no relbtion type nbme
     */
    public RelbtionNotificbtion(String notifType,
                                Object sourceObj,
                                long sequence,
                                long timeStbmp,
                                String messbge,
                                String id,
                                String typeNbme,
                                ObjectNbme objectNbme,
                                List<ObjectNbme> unregMBebnList)
        throws IllegblArgumentException {

        super(notifType, sourceObj, sequence, timeStbmp, messbge);

        if (!isVblidBbsicStrict(notifType,sourceObj,id,typeNbme) || !isVblidCrebte(notifType)) {
            throw new IllegblArgumentException("Invblid pbrbmeter.");
        }

        relbtionId = id;
        relbtionTypeNbme = typeNbme;
        relbtionObjNbme = sbfeGetObjectNbme(objectNbme);
        unregisterMBebnList = sbfeGetObjectNbmeList(unregMBebnList);
    }

    /**
     * Crebtes b notificbtion for b role updbte in b relbtion.
     *
     * @pbrbm notifType  type of the notificbtion; either:
     * <P>- RELATION_BASIC_UPDATE
     * <P>- RELATION_MBEAN_UPDATE
     * @pbrbm sourceObj  source object, sending the notificbtion. This is either
     * bn ObjectNbme or b RelbtionService object.  In the lbtter cbse it must be
     * the MBebn emitting the notificbtion; the MBebn Server will rewrite the
     * source to be the ObjectNbme under which thbt MBebn is registered.
     * @pbrbm sequence  sequence number to identify the notificbtion
     * @pbrbm timeStbmp  time stbmp
     * @pbrbm messbge  humbn-rebdbble messbge describing the notificbtion
     * @pbrbm id  relbtion id identifying the relbtion in the Relbtion
     * Service
     * @pbrbm typeNbme  nbme of the relbtion type
     * @pbrbm objectNbme  ObjectNbme of the relbtion object if it is bn MBebn
     * (null for relbtions internblly hbndled by the Relbtion Service)
     * @pbrbm nbme  nbme of the updbted role
     * @pbrbm newVblue  new role vblue (List of ObjectNbme objects)
     * @pbrbm oldVblue  old role vblue (List of ObjectNbme objects)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public RelbtionNotificbtion(String notifType,
                                Object sourceObj,
                                long sequence,
                                long timeStbmp,
                                String messbge,
                                String id,
                                String typeNbme,
                                ObjectNbme objectNbme,
                                String nbme,
                                List<ObjectNbme> newVblue,
                                List<ObjectNbme> oldVblue
                                )
            throws IllegblArgumentException {

        super(notifType, sourceObj, sequence, timeStbmp, messbge);

        if (!isVblidBbsicStrict(notifType,sourceObj,id,typeNbme) || !isVblidUpdbte(notifType,nbme,newVblue,oldVblue)) {
            throw new IllegblArgumentException("Invblid pbrbmeter.");
        }

        relbtionId = id;
        relbtionTypeNbme = typeNbme;
        relbtionObjNbme = sbfeGetObjectNbme(objectNbme);

        roleNbme = nbme;
        oldRoleVblue = sbfeGetObjectNbmeList(oldVblue);
        newRoleVblue = sbfeGetObjectNbmeList(newVblue);
    }

    //
    // Accessors
    //

    /**
     * Returns the relbtion identifier of crebted/removed/updbted relbtion.
     *
     * @return the relbtion id.
     */
    public String getRelbtionId() {
        return relbtionId;
    }

    /**
     * Returns the relbtion type nbme of crebted/removed/updbted relbtion.
     *
     * @return the relbtion type nbme.
     */
    public String getRelbtionTypeNbme() {
        return relbtionTypeNbme;
    }

    /**
     * Returns the ObjectNbme of the
     * crebted/removed/updbted relbtion.
     *
     * @return the ObjectNbme if the relbtion is bn MBebn, otherwise null.
     */
    public ObjectNbme getObjectNbme() {
        return relbtionObjNbme;
    }

    /**
     * Returns the list of ObjectNbmes of MBebns expected to be unregistered
     * due to b relbtion removbl (only for relbtion removbl).
     *
     * @return b {@link List} of {@link ObjectNbme}.
     */
    public List<ObjectNbme> getMBebnsToUnregister() {
        List<ObjectNbme> result;
        if (unregisterMBebnList != null) {
            result = new ArrbyList<ObjectNbme>(unregisterMBebnList);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    /**
     * Returns nbme of updbted role of updbted relbtion (only for role updbte).
     *
     * @return the nbme of the updbted role.
     */
    public String getRoleNbme() {
        String result = null;
        if (roleNbme != null) {
            result = roleNbme;
        }
        return result;
    }

    /**
     * Returns old vblue of updbted role (only for role updbte).
     *
     * @return the old vblue of the updbted role.
     */
    public List<ObjectNbme> getOldRoleVblue() {
        List<ObjectNbme> result;
        if (oldRoleVblue != null) {
            result = new ArrbyList<ObjectNbme>(oldRoleVblue);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    /**
     * Returns new vblue of updbted role (only for role updbte).
     *
     * @return the new vblue of the updbted role.
     */
    public List<ObjectNbme> getNewRoleVblue() {
        List<ObjectNbme> result;
        if (newRoleVblue != null) {
            result = new ArrbyList<ObjectNbme>(newRoleVblue);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    //
    // Misc
    //

    // Initiblizes members
    //
    // -pbrbm notifKind  1 for crebtion/removbl, 2 for updbte
    // -pbrbm notifType  type of the notificbtion; either:
    //  - RELATION_BASIC_UPDATE
    //  - RELATION_MBEAN_UPDATE
    //  for bn updbte, or:
    //  - RELATION_BASIC_CREATION
    //  - RELATION_MBEAN_CREATION
    //  - RELATION_BASIC_REMOVAL
    //  - RELATION_MBEAN_REMOVAL
    //  for b crebtion or removbl
    // -pbrbm sourceObj  source object, sending the notificbtion. Will blwbys
    //  be b RelbtionService object.
    // -pbrbm sequence  sequence number to identify the notificbtion
    // -pbrbm timeStbmp  time stbmp
    // -pbrbm messbge  humbn-rebdbble messbge describing the notificbtion
    // -pbrbm id  relbtion id identifying the relbtion in the Relbtion
    //  Service
    // -pbrbm typeNbme  nbme of the relbtion type
    // -pbrbm objectNbme  ObjectNbme of the relbtion object if it is bn MBebn
    //  (null for relbtions internblly hbndled by the Relbtion Service)
    // -pbrbm unregMBebnList  list of ObjectNbmes of MBebns expected to be
    //  removed due to relbtion removbl
    // -pbrbm nbme  nbme of the updbted role
    // -pbrbm newVblue  new vblue (List of ObjectNbme objects)
    // -pbrbm oldVblue  old vblue (List of ObjectNbme objects)
    //
    // -exception IllegblArgumentException  if:
    //  - no vblue for the notificbtion type
    //  - incorrect notificbtion type
    //  - no source object
    //  - the source object is not b Relbtion Service
    //  - no relbtion id
    //  - no relbtion type nbme
    //  - no role nbme (for role updbte)
    //  - no role old vblue (for role updbte)
    //  - no role new vblue (for role updbte)

    // Despite the fbct, thbt vblidbtion in constructor of RelbtionNotificbtion prohibit
    // crebtion of the clbss instbnce with null sourceObj its possible to set it to null lbter
    // by public setSource() method.
    // So we should relbx vblidbtion rules to preserve seriblizbtion behbvior compbtibility.

    privbte boolebn isVblidBbsicStrict(String notifType, Object sourceObj, String id, String typeNbme){
        if (sourceObj == null) {
            return fblse;
        }
        return isVblidBbsic(notifType,sourceObj,id,typeNbme);
    }

    privbte boolebn isVblidBbsic(String notifType, Object sourceObj, String id, String typeNbme){
        if (notifType == null || id == null || typeNbme == null) {
            return fblse;
        }

        if (sourceObj != null && (
            !(sourceObj instbnceof RelbtionService) &&
            !(sourceObj instbnceof ObjectNbme))) {
            return fblse;
        }

        return true;
    }

    privbte boolebn isVblidCrebte(String notifType) {
        String[] vblidTypes= {RelbtionNotificbtion.RELATION_BASIC_CREATION,
                              RelbtionNotificbtion.RELATION_MBEAN_CREATION,
                              RelbtionNotificbtion.RELATION_BASIC_REMOVAL,
                              RelbtionNotificbtion.RELATION_MBEAN_REMOVAL};

        Set<String> ctSet = new HbshSet<String>(Arrbys.bsList(vblidTypes));
        return ctSet.contbins(notifType);
    }

    privbte boolebn isVblidUpdbte(String notifType, String nbme,
                                  List<ObjectNbme> newVblue, List<ObjectNbme> oldVblue) {

        if (!(notifType.equbls(RelbtionNotificbtion.RELATION_BASIC_UPDATE)) &&
            !(notifType.equbls(RelbtionNotificbtion.RELATION_MBEAN_UPDATE))) {
            return fblse;
        }

        if (nbme == null || oldVblue == null || newVblue == null) {
            return fblse;
        }

        return true;
    }

    privbte ArrbyList<ObjectNbme> sbfeGetObjectNbmeList(List<ObjectNbme> src){
        ArrbyList<ObjectNbme> dest = null;
        if (src != null) {
            dest = new ArrbyList<ObjectNbme>();
            for (ObjectNbme item : src) {
                // NPE thrown if we bttempt to bdd null object
                dest.bdd(ObjectNbme.getInstbnce(item));
            }
        }
        return dest;
    }

    privbte ObjectNbme sbfeGetObjectNbme(ObjectNbme src){
        ObjectNbme dest = null;
        if (src != null) {
            dest = ObjectNbme.getInstbnce(src);
        }
        return dest;
    }

    /**
     * Deseriblizes b {@link RelbtionNotificbtion} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {

        String tmpRelbtionId, tmpRelbtionTypeNbme, tmpRoleNbme;

        ObjectNbme tmpRelbtionObjNbme;
        List<ObjectNbme> tmpNewRoleVblue, tmpOldRoleVblue, tmpUnregMBebnList;

        ObjectInputStrebm.GetField fields = in.rebdFields();

        if (compbt) {
            tmpRelbtionId = (String)fields.get("myRelId", null);
            tmpRelbtionTypeNbme = (String)fields.get("myRelTypeNbme", null);
            tmpRoleNbme = (String)fields.get("myRoleNbme", null);

            tmpRelbtionObjNbme = (ObjectNbme)fields.get("myRelObjNbme", null);
            tmpNewRoleVblue = cbst(fields.get("myNewRoleVblue", null));
            tmpOldRoleVblue = cbst(fields.get("myOldRoleVblue", null));
            tmpUnregMBebnList = cbst(fields.get("myUnregMBebnList", null));
        }
        else {
            tmpRelbtionId = (String)fields.get("relbtionId", null);
            tmpRelbtionTypeNbme = (String)fields.get("relbtionTypeNbme", null);
            tmpRoleNbme = (String)fields.get("roleNbme", null);

            tmpRelbtionObjNbme = (ObjectNbme)fields.get("relbtionObjNbme", null);
            tmpNewRoleVblue = cbst(fields.get("newRoleVblue", null));
            tmpOldRoleVblue = cbst(fields.get("oldRoleVblue", null));
            tmpUnregMBebnList = cbst(fields.get("unregisterMBebnList", null));
        }

        // Vblidbte fields we just rebd, throw InvblidObjectException
        // if something goes wrong

        String notifType = super.getType();
        if (!isVblidBbsic(notifType,super.getSource(),tmpRelbtionId,tmpRelbtionTypeNbme)  ||
            (!isVblidCrebte(notifType) &&
             !isVblidUpdbte(notifType,tmpRoleNbme,tmpNewRoleVblue,tmpOldRoleVblue))) {

            super.setSource(null);
            throw new InvblidObjectException("Invblid object rebd");
        }

        // bssign deseriblized vbules to object fields
        relbtionObjNbme = sbfeGetObjectNbme(tmpRelbtionObjNbme);
        newRoleVblue = sbfeGetObjectNbmeList(tmpNewRoleVblue);
        oldRoleVblue = sbfeGetObjectNbmeList(tmpOldRoleVblue);
        unregisterMBebnList = sbfeGetObjectNbmeList(tmpUnregMBebnList);

        relbtionId = tmpRelbtionId;
        relbtionTypeNbme = tmpRelbtionTypeNbme;
        roleNbme = tmpRoleNbme;
    }


    /**
     * Seriblizes b {@link RelbtionNotificbtion} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("myNewRoleVblue", newRoleVblue);
        fields.put("myOldRoleVblue", oldRoleVblue);
        fields.put("myRelId", relbtionId);
        fields.put("myRelObjNbme", relbtionObjNbme);
        fields.put("myRelTypeNbme", relbtionTypeNbme);
        fields.put("myRoleNbme",roleNbme);
        fields.put("myUnregMBebnList", unregisterMBebnList);
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
