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

import stbtic com.sun.jmx.defbults.JmxProperties.RELATION_LOGGER;
import stbtic com.sun.jmx.mbebnserver.Util.cbst;

import jbvb.util.ArrbyList;
import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.MBebnServerNotificbtion;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;

/**
 * The Relbtion Service is in chbrge of crebting bnd deleting relbtion types
 * bnd relbtions, of hbndling the consistency bnd of providing query
 * mechbnisms.
 * <P>It implements the NotificbtionBrobdcbster by extending
 * NotificbtionBrobdcbsterSupport to send notificbtions when b relbtion is
 * removed from it.
 * <P>It implements the NotificbtionListener interfbce to be bble to receive
 * notificbtions concerning unregistrbtion of MBebns referenced in relbtion
 * roles bnd of relbtion MBebns.
 * <P>It implements the MBebnRegistrbtion interfbce to be bble to retrieve
 * its ObjectNbme bnd MBebn Server.
 *
 * @since 1.5
 */
public clbss RelbtionService extends NotificbtionBrobdcbsterSupport
    implements RelbtionServiceMBebn, MBebnRegistrbtion, NotificbtionListener {

    //
    // Privbte members
    //

    // Mbp bssocibting:
    //      <relbtion id> -> <RelbtionSupport object/ObjectNbme>
    // depending if the relbtion hbs been crebted using crebteRelbtion()
    // method (so internblly hbndled) or is bn MBebn bdded bs b relbtion by the
    // user
    privbte Mbp<String,Object> myRelId2ObjMbp = new HbshMbp<String,Object>();

    // Mbp bssocibting:
    //      <relbtion id> -> <relbtion type nbme>
    privbte Mbp<String,String> myRelId2RelTypeMbp = new HbshMbp<String,String>();

    // Mbp bssocibting:
    //      <relbtion MBebn Object Nbme> -> <relbtion id>
    privbte Mbp<ObjectNbme,String> myRelMBebnObjNbme2RelIdMbp =
        new HbshMbp<ObjectNbme,String>();

    // Mbp bssocibting:
    //       <relbtion type nbme> -> <RelbtionType object>
    privbte Mbp<String,RelbtionType> myRelType2ObjMbp =
        new HbshMbp<String,RelbtionType>();

    // Mbp bssocibting:
    //       <relbtion type nbme> -> ArrbyList of <relbtion id>
    // to list bll the relbtions of b given type
    privbte Mbp<String,List<String>> myRelType2RelIdsMbp =
        new HbshMbp<String,List<String>>();

    // Mbp bssocibting:
    //       <ObjectNbme> -> HbshMbp
    // the vblue HbshMbp mbpping:
    //       <relbtion id> -> ArrbyList of <role nbme>
    // to trbck where b given MBebn is referenced.
    privbte finbl Mbp<ObjectNbme,Mbp<String,List<String>>>
        myRefedMBebnObjNbme2RelIdsMbp =
            new HbshMbp<ObjectNbme,Mbp<String,List<String>>>();

    // Flbg to indicbte if, when b notificbtion is received for the
    // unregistrbtion of bn MBebn referenced in b relbtion, if bn immedibte
    // "purge" of the relbtions (look for the relbtions no
    // longer vblid) hbs to be performed , or if thbt will be performed only
    // when the purgeRelbtions method will be explicitly cblled.
    // true is immedibte purge.
    privbte boolebn myPurgeFlbg = true;

    // Internbl counter to provide sequence numbers for notificbtions sent by:
    // - the Relbtion Service
    // - b relbtion hbndled by the Relbtion Service
    privbte finbl AtomicLong btomicSeqNo = new AtomicLong();

    // ObjectNbme used to register the Relbtion Service in the MBebn Server
    privbte ObjectNbme myObjNbme = null;

    // MBebn Server where the Relbtion Service is registered
    privbte MBebnServer myMBebnServer = null;

    // Filter registered in the MBebn Server with the Relbtion Service to be
    // informed of referenced MBebn deregistrbtions
    privbte MBebnServerNotificbtionFilter myUnregNtfFilter = null;

    // List of unregistrbtion notificbtions received (storbge used if purge
    // of relbtions when unregistering b referenced MBebn is not immedibte but
    // on user request)
    privbte List<MBebnServerNotificbtion> myUnregNtfList =
        new ArrbyList<MBebnServerNotificbtion>();

    //
    // Constructor
    //

    /**
     * Constructor.
     *
     * @pbrbm immedibtePurgeFlbg  flbg to indicbte when b notificbtion is
     * received for the unregistrbtion of bn MBebn referenced in b relbtion, if
     * bn immedibte "purge" of the relbtions (look for the relbtions no
     * longer vblid) hbs to be performed , or if thbt will be performed only
     * when the purgeRelbtions method will be explicitly cblled.
     * <P>true is immedibte purge.
     */
    public RelbtionService(boolebn immedibtePurgeFlbg) {

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "RelbtionService");

        setPurgeFlbg(immedibtePurgeFlbg);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "RelbtionService");
        return;
    }

    /**
     * Checks if the Relbtion Service is bctive.
     * Current condition is thbt the Relbtion Service must be registered in the
     * MBebn Server
     *
     * @exception RelbtionServiceNotRegisteredException  if it is not
     * registered
     */
    public void isActive()
        throws RelbtionServiceNotRegisteredException {
        if (myMBebnServer == null) {
            // MBebn Server not set by preRegister(): relbtion service not
            // registered
            String excMsg =
                "Relbtion Service not registered in the MBebn Server.";
            throw new RelbtionServiceNotRegisteredException(excMsg);
        }
        return;
    }

    //
    // MBebnRegistrbtion interfbce
    //

    // Pre-registrbtion: retrieves its ObjectNbme bnd MBebn Server
    //
    // No exception thrown.
    public ObjectNbme preRegister(MBebnServer server,
                                  ObjectNbme nbme)
        throws Exception {

        myMBebnServer = server;
        myObjNbme = nbme;
        return nbme;
    }

    // Post-registrbtion: does nothing
    public void postRegister(Boolebn registrbtionDone) {
        return;
    }

    // Pre-unregistrbtion: does nothing
    public void preDeregister()
        throws Exception {
        return;
    }

    // Post-unregistrbtion: does nothing
    public void postDeregister() {
        return;
    }

    //
    // Accessors
    //

    /**
     * Returns the flbg to indicbte if when b notificbtion is received for the
     * unregistrbtion of bn MBebn referenced in b relbtion, if bn immedibte
     * "purge" of the relbtions (look for the relbtions no longer vblid)
     * hbs to be performed , or if thbt will be performed only when the
     * purgeRelbtions method will be explicitly cblled.
     * <P>true is immedibte purge.
     *
     * @return true if purges bre butombtic.
     *
     * @see #setPurgeFlbg
     */
    public boolebn getPurgeFlbg() {
        return myPurgeFlbg;
    }

    /**
     * Sets the flbg to indicbte if when b notificbtion is received for the
     * unregistrbtion of bn MBebn referenced in b relbtion, if bn immedibte
     * "purge" of the relbtions (look for the relbtions no longer vblid)
     * hbs to be performed , or if thbt will be performed only when the
     * purgeRelbtions method will be explicitly cblled.
     * <P>true is immedibte purge.
     *
     * @pbrbm purgeFlbg  flbg
     *
     * @see #getPurgeFlbg
     */
    public void setPurgeFlbg(boolebn purgeFlbg) {

        myPurgeFlbg = purgeFlbg;
        return;
    }

    //
    // Relbtion type hbndling
    //

    /**
     * Crebtes b relbtion type (b RelbtionTypeSupport object) with given
     * role infos (provided by the RoleInfo objects), bnd bdds it in the
     * Relbtion Service.
     *
     * @pbrbm relbtionTypeNbme  nbme of the relbtion type
     * @pbrbm roleInfoArrby  brrby of role infos
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception InvblidRelbtionTypeException  If:
     * <P>- there is blrebdy b relbtion type with thbt nbme
     * <P>- the sbme nbme hbs been used for two different role infos
     * <P>- no role info provided
     * <P>- one null role info provided
     */
    public void crebteRelbtionType(String relbtionTypeNbme,
                                   RoleInfo[] roleInfoArrby)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (relbtionTypeNbme == null || roleInfoArrby == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "crebteRelbtionType", relbtionTypeNbme);

        // Cbn throw bn InvblidRelbtionTypeException
        RelbtionType relType =
            new RelbtionTypeSupport(relbtionTypeNbme, roleInfoArrby);

        bddRelbtionTypeInt(relType);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "crebteRelbtionType");
        return;
    }

    /**
     * Adds given object bs b relbtion type. The object is expected to
     * implement the RelbtionType interfbce.
     *
     * @pbrbm relbtionTypeObj  relbtion type object (implementing the
     * RelbtionType interfbce)
     *
     * @exception IllegblArgumentException  if null pbrbmeter or if
     * {@link RelbtionType#getRelbtionTypeNbme
     * relbtionTypeObj.getRelbtionTypeNbme()} returns null.
     * @exception InvblidRelbtionTypeException  if:
     * <P>- the sbme nbme hbs been used for two different roles
     * <P>- no role info provided
     * <P>- one null role info provided
     * <P>- there is blrebdy b relbtion type with thbt nbme
     */
    public void bddRelbtionType(RelbtionType relbtionTypeObj)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (relbtionTypeObj == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "bddRelbtionType");

        // Checks the role infos
        List<RoleInfo> roleInfoList = relbtionTypeObj.getRoleInfos();
        if (roleInfoList == null) {
            String excMsg = "No role info provided.";
            throw new InvblidRelbtionTypeException(excMsg);
        }

        RoleInfo[] roleInfoArrby = new RoleInfo[roleInfoList.size()];
        int i = 0;
        for (RoleInfo currRoleInfo : roleInfoList) {
            roleInfoArrby[i] = currRoleInfo;
            i++;
        }
        // Cbn throw InvblidRelbtionTypeException
        RelbtionTypeSupport.checkRoleInfos(roleInfoArrby);

        bddRelbtionTypeInt(relbtionTypeObj);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "bddRelbtionType");
        return;
     }

    /**
     * Retrieves nbmes of bll known relbtion types.
     *
     * @return ArrbyList of relbtion type nbmes (Strings)
     */
    public List<String> getAllRelbtionTypeNbmes() {
        ArrbyList<String> result;
        synchronized(myRelType2ObjMbp) {
            result = new ArrbyList<String>(myRelType2ObjMbp.keySet());
        }
        return result;
    }

    /**
     * Retrieves list of role infos (RoleInfo objects) of b given relbtion
     * type.
     *
     * @pbrbm relbtionTypeNbme  nbme of relbtion type
     *
     * @return ArrbyList of RoleInfo.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionTypeNotFoundException  if there is no relbtion type
     * with thbt nbme.
     */
    public List<RoleInfo> getRoleInfos(String relbtionTypeNbme)
        throws IllegblArgumentException,
               RelbtionTypeNotFoundException {

        if (relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRoleInfos", relbtionTypeNbme);

        // Cbn throw b RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getRoleInfos");
        return relType.getRoleInfos();
    }

    /**
     * Retrieves role info for given role nbme of b given relbtion type.
     *
     * @pbrbm relbtionTypeNbme  nbme of relbtion type
     * @pbrbm roleInfoNbme  nbme of role
     *
     * @return RoleInfo object.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionTypeNotFoundException  if the relbtion type is not
     * known in the Relbtion Service
     * @exception RoleInfoNotFoundException  if the role is not pbrt of the
     * relbtion type.
     */
    public RoleInfo getRoleInfo(String relbtionTypeNbme,
                                String roleInfoNbme)
        throws IllegblArgumentException,
               RelbtionTypeNotFoundException,
               RoleInfoNotFoundException {

        if (relbtionTypeNbme == null || roleInfoNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRoleInfo", new Object[] {relbtionTypeNbme, roleInfoNbme});

        // Cbn throw b RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        // Cbn throw b RoleInfoNotFoundException
        RoleInfo roleInfo = relType.getRoleInfo(roleInfoNbme);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getRoleInfo");
        return roleInfo;
    }

    /**
     * Removes given relbtion type from Relbtion Service.
     * <P>The relbtion objects of thbt type will be removed from the
     * Relbtion Service.
     *
     * @pbrbm relbtionTypeNbme  nbme of the relbtion type to be removed
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionTypeNotFoundException  If there is no relbtion type
     * with thbt nbme
     */
    public void removeRelbtionType(String relbtionTypeNbme)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RelbtionTypeNotFoundException {

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        if (relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "removeRelbtionType", relbtionTypeNbme);

        // Checks if the relbtion type to be removed exists
        // Cbn throw b RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        // Retrieves the relbtion ids for relbtions of thbt type
        List<String> relIdList = null;
        synchronized(myRelType2RelIdsMbp) {
            // Note: tbke b copy of the list bs it is b pbrt of b mbp thbt
            //       will be updbted by removeRelbtion() below.
            List<String> relIdList1 =
                myRelType2RelIdsMbp.get(relbtionTypeNbme);
            if (relIdList1 != null) {
                relIdList = new ArrbyList<String>(relIdList1);
            }
        }

        // Removes the relbtion type from bll mbps
        synchronized(myRelType2ObjMbp) {
            myRelType2ObjMbp.remove(relbtionTypeNbme);
        }
        synchronized(myRelType2RelIdsMbp) {
            myRelType2RelIdsMbp.remove(relbtionTypeNbme);
        }

        // Removes bll relbtions of thbt type
        if (relIdList != null) {
            for (String currRelId : relIdList) {
                // Note: will remove it from myRelId2RelTypeMbp :)
                //
                // Cbn throw RelbtionServiceNotRegisteredException (detected
                // bbove)
                // Shbll not throw b RelbtionNotFoundException
                try {
                    removeRelbtion(currRelId);
                } cbtch (RelbtionNotFoundException exc1) {
                    throw new RuntimeException(exc1.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "removeRelbtionType");
        return;
    }

    //
    // Relbtion hbndling
    //

    /**
     * Crebtes b simple relbtion (represented by b RelbtionSupport object) of
     * given relbtion type, bnd bdds it in the Relbtion Service.
     * <P>Roles bre initiblized bccording to the role list provided in
     * pbrbmeter. The ones not initiblized in this wby bre set to bn empty
     * ArrbyList of ObjectNbmes.
     * <P>A RelbtionNotificbtion, with type RELATION_BASIC_CREATION, is sent.
     *
     * @pbrbm relbtionId  relbtion identifier, to identify uniquely the relbtion
     * inside the Relbtion Service
     * @pbrbm relbtionTypeNbme  nbme of the relbtion type (hbs to be crebted
     * in the Relbtion Service)
     * @pbrbm roleList  role list to initiblize roles of the relbtion (cbn
     * be null).
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception IllegblArgumentException  if null pbrbmeter, except the role
     * list which cbn be null if no role initiblizbtion
     * @exception RoleNotFoundException  if b vblue is provided for b role
     * thbt does not exist in the relbtion type
     * @exception InvblidRelbtionIdException  if relbtion id blrebdy used
     * @exception RelbtionTypeNotFoundException  if relbtion type not known in
     * Relbtion Service
     * @exception InvblidRoleVblueException if:
     * <P>- the sbme role nbme is used for two different roles
     * <P>- the number of referenced MBebns in given vblue is less thbn
     * expected minimum degree
     * <P>- the number of referenced MBebns in provided vblue exceeds expected
     * mbximum degree
     * <P>- one referenced MBebn in the vblue is not bn Object of the MBebn
     * clbss expected for thbt role
     * <P>- bn MBebn provided for thbt role does not exist
     */
    public void crebteRelbtion(String relbtionId,
                               String relbtionTypeNbme,
                               RoleList roleList)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RoleNotFoundException,
               InvblidRelbtionIdException,
               RelbtionTypeNotFoundException,
               InvblidRoleVblueException {

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        if (relbtionId == null ||
            relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "crebteRelbtion",
                new Object[] {relbtionId, relbtionTypeNbme, roleList});

        // Crebtes RelbtionSupport object
        // Cbn throw InvblidRoleVblueException
        RelbtionSupport relObj = new RelbtionSupport(relbtionId,
                                               myObjNbme,
                                               relbtionTypeNbme,
                                               roleList);

        // Adds relbtion object bs b relbtion into the Relbtion Service
        // Cbn throw RoleNotFoundException, InvblidRelbtionId,
        // RelbtionTypeNotFoundException, InvblidRoleVblueException
        //
        // Cbnnot throw MBebnException
        bddRelbtionInt(true,
                       relObj,
                       null,
                       relbtionId,
                       relbtionTypeNbme,
                       roleList);
        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "crebteRelbtion");
        return;
    }

    /**
     * Adds bn MBebn crebted by the user (bnd registered by him in the MBebn
     * Server) bs b relbtion in the Relbtion Service.
     * <P>To be bdded bs b relbtion, the MBebn must conform to the
     * following:
     * <P>- implement the Relbtion interfbce
     * <P>- hbve for RelbtionService ObjectNbme the ObjectNbme of current
     * Relbtion Service
     * <P>- hbve b relbtion id unique bnd unused in current Relbtion Service
     * <P>- hbve for relbtion type b relbtion type crebted in the Relbtion
     * Service
     * <P>- hbve roles conforming to the role info provided in the relbtion
     * type.
     *
     * @pbrbm relbtionObjectNbme  ObjectNbme of the relbtion MBebn to be bdded.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception NoSuchMethodException  If the MBebn does not implement the
     * Relbtion interfbce
     * @exception InvblidRelbtionIdException  if:
     * <P>- no relbtion identifier in MBebn
     * <P>- the relbtion identifier is blrebdy used in the Relbtion Service
     * @exception InstbnceNotFoundException  if the MBebn for given ObjectNbme
     * hbs not been registered
     * @exception InvblidRelbtionServiceException  if:
     * <P>- no Relbtion Service nbme in MBebn
     * <P>- the Relbtion Service nbme in the MBebn is not the one of the
     * current Relbtion Service
     * @exception RelbtionTypeNotFoundException  if:
     * <P>- no relbtion type nbme in MBebn
     * <P>- the relbtion type nbme in MBebn does not correspond to b relbtion
     * type crebted in the Relbtion Service
     * @exception InvblidRoleVblueException  if:
     * <P>- the number of referenced MBebns in b role is less thbn
     * expected minimum degree
     * <P>- the number of referenced MBebns in b role exceeds expected
     * mbximum degree
     * <P>- one referenced MBebn in the vblue is not bn Object of the MBebn
     * clbss expected for thbt role
     * <P>- bn MBebn provided for b role does not exist
     * @exception RoleNotFoundException  if b vblue is provided for b role
     * thbt does not exist in the relbtion type
     */
    public void bddRelbtion(ObjectNbme relbtionObjectNbme)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               NoSuchMethodException,
               InvblidRelbtionIdException,
               InstbnceNotFoundException,
               InvblidRelbtionServiceException,
               RelbtionTypeNotFoundException,
               RoleNotFoundException,
               InvblidRoleVblueException {

        if (relbtionObjectNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "bddRelbtion", relbtionObjectNbme);

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Checks thbt the relbtion MBebn implements the Relbtion interfbce.
        // It will blso check thbt the provided ObjectNbme corresponds to b
        // registered MBebn (else will throw bn InstbnceNotFoundException)
        if ((!(myMBebnServer.isInstbnceOf(relbtionObjectNbme, "jbvbx.mbnbgement.relbtion.Relbtion")))) {
            String excMsg = "This MBebn does not implement the Relbtion interfbce.";
            throw new NoSuchMethodException(excMsg);
        }
        // Checks there is b relbtion id in the relbtion MBebn (its uniqueness
        // is checked in bddRelbtionInt())
        // Cbn throw InstbnceNotFoundException (but detected bbove)
        // No MBebnException bs no exception rbised by this method, bnd no
        // ReflectionException
        String relId;
        try {
            relId = (String)(myMBebnServer.getAttribute(relbtionObjectNbme,
                                                        "RelbtionId"));

        } cbtch (MBebnException exc1) {
            throw new RuntimeException(
                                     (exc1.getTbrgetException()).getMessbge());
        } cbtch (ReflectionException exc2) {
            throw new RuntimeException(exc2.getMessbge());
        } cbtch (AttributeNotFoundException exc3) {
            throw new RuntimeException(exc3.getMessbge());
        }

        if (relId == null) {
            String excMsg = "This MBebn does not provide b relbtion id.";
            throw new InvblidRelbtionIdException(excMsg);
        }
        // Checks thbt the Relbtion Service where the relbtion MBebn is
        // expected to be bdded is the current one
        // Cbn throw InstbnceNotFoundException (but detected bbove)
        // No MBebnException bs no exception rbised by this method, no
        // ReflectionException
        ObjectNbme relServObjNbme;
        try {
            relServObjNbme = (ObjectNbme)
                (myMBebnServer.getAttribute(relbtionObjectNbme,
                                            "RelbtionServiceNbme"));

        } cbtch (MBebnException exc1) {
            throw new RuntimeException(
                                     (exc1.getTbrgetException()).getMessbge());
        } cbtch (ReflectionException exc2) {
            throw new RuntimeException(exc2.getMessbge());
        } cbtch (AttributeNotFoundException exc3) {
            throw new RuntimeException(exc3.getMessbge());
        }

        boolebn bbdRelServFlbg = fblse;
        if (relServObjNbme == null) {
            bbdRelServFlbg = true;

        } else if (!(relServObjNbme.equbls(myObjNbme))) {
            bbdRelServFlbg = true;
        }
        if (bbdRelServFlbg) {
            String excMsg = "The Relbtion Service referenced in the MBebn is not the current one.";
            throw new InvblidRelbtionServiceException(excMsg);
        }
        // Checks thbt b relbtion type hbs been specified for the relbtion
        // Cbn throw InstbnceNotFoundException (but detected bbove)
        // No MBebnException bs no exception rbised by this method, no
        // ReflectionException
        String relTypeNbme;
        try {
            relTypeNbme = (String)(myMBebnServer.getAttribute(relbtionObjectNbme,
                                                              "RelbtionTypeNbme"));

        } cbtch (MBebnException exc1) {
            throw new RuntimeException(
                                     (exc1.getTbrgetException()).getMessbge());
        }cbtch (ReflectionException exc2) {
            throw new RuntimeException(exc2.getMessbge());
        } cbtch (AttributeNotFoundException exc3) {
            throw new RuntimeException(exc3.getMessbge());
        }
        if (relTypeNbme == null) {
            String excMsg = "No relbtion type provided.";
            throw new RelbtionTypeNotFoundException(excMsg);
        }
        // Retrieves bll roles without considering rebd mode
        // Cbn throw InstbnceNotFoundException (but detected bbove)
        // No MBebnException bs no exception rbised by this method, no
        // ReflectionException
        RoleList roleList;
        try {
            roleList = (RoleList)(myMBebnServer.invoke(relbtionObjectNbme,
                                                       "retrieveAllRoles",
                                                       null,
                                                       null));
        } cbtch (MBebnException exc1) {
            throw new RuntimeException(
                                     (exc1.getTbrgetException()).getMessbge());
        } cbtch (ReflectionException exc2) {
            throw new RuntimeException(exc2.getMessbge());
        }

        // Cbn throw RoleNotFoundException, InvblidRelbtionIdException,
        // RelbtionTypeNotFoundException, InvblidRoleVblueException
        bddRelbtionInt(fblse,
                       null,
                       relbtionObjectNbme,
                       relId,
                       relTypeNbme,
                       roleList);
        // Adds relbtion MBebn ObjectNbme in mbp
        synchronized(myRelMBebnObjNbme2RelIdMbp) {
            myRelMBebnObjNbme2RelIdMbp.put(relbtionObjectNbme, relId);
        }

        // Updbtes flbg to specify thbt the relbtion is mbnbged by the Relbtion
        // Service
        // This flbg bnd setter bre inherited from RelbtionSupport bnd not pbrts
        // of the Relbtion interfbce, so mby be not supported.
        try {
            myMBebnServer.setAttribute(relbtionObjectNbme,
                                       new Attribute(
                                         "RelbtionServiceMbnbgementFlbg",
                                         Boolebn.TRUE));
        } cbtch (Exception exc) {
            // OK : The flbg is not supported.
        }

        // Updbtes listener informbtion to received notificbtion for
        // unregistrbtion of this MBebn
        List<ObjectNbme> newRefList = new ArrbyList<ObjectNbme>();
        newRefList.bdd(relbtionObjectNbme);
        updbteUnregistrbtionListener(newRefList, null);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "bddRelbtion");
        return;
    }

    /**
     * If the relbtion is represented by bn MBebn (crebted by the user bnd
     * bdded bs b relbtion in the Relbtion Service), returns the ObjectNbme of
     * the MBebn.
     *
     * @pbrbm relbtionId  relbtion id identifying the relbtion
     *
     * @return ObjectNbme of the corresponding relbtion MBebn, or null if
     * the relbtion is not bn MBebn.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException there is no relbtion bssocibted
     * to thbt id
     */
    public ObjectNbme isRelbtionMBebn(String relbtionId)
        throws IllegblArgumentException,
               RelbtionNotFoundException{

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "isRelbtionMBebn", relbtionId);

        // Cbn throw RelbtionNotFoundException
        Object result = getRelbtion(relbtionId);
        if (result instbnceof ObjectNbme) {
            return ((ObjectNbme)result);
        } else {
            return null;
        }
    }

    /**
     * Returns the relbtion id bssocibted to the given ObjectNbme if the
     * MBebn hbs been bdded bs b relbtion in the Relbtion Service.
     *
     * @pbrbm objectNbme  ObjectNbme of supposed relbtion
     *
     * @return relbtion id (String) or null (if the ObjectNbme is not b
     * relbtion hbndled by the Relbtion Service)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public String isRelbtion(ObjectNbme objectNbme)
        throws IllegblArgumentException {

        if (objectNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "isRelbtion", objectNbme);

        String result = null;
        synchronized(myRelMBebnObjNbme2RelIdMbp) {
            String relId = myRelMBebnObjNbme2RelIdMbp.get(objectNbme);
            if (relId != null) {
                result = relId;
            }
        }
        return result;
    }

    /**
     * Checks if there is b relbtion identified in Relbtion Service with given
     * relbtion id.
     *
     * @pbrbm relbtionId  relbtion id identifying the relbtion
     *
     * @return boolebn: true if there is b relbtion, fblse else
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public Boolebn hbsRelbtion(String relbtionId)
        throws IllegblArgumentException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "hbsRelbtion", relbtionId);

        try {
            // Cbn throw RelbtionNotFoundException
            Object result = getRelbtion(relbtionId);
            return true;
        } cbtch (RelbtionNotFoundException exc) {
            return fblse;
        }
    }

    /**
     * Returns bll the relbtion ids for bll the relbtions hbndled by the
     * Relbtion Service.
     *
     * @return ArrbyList of String
     */
    public List<String> getAllRelbtionIds() {
        List<String> result;
        synchronized(myRelId2ObjMbp) {
            result = new ArrbyList<String>(myRelId2ObjMbp.keySet());
        }
        return result;
    }

    /**
     * Checks if given Role cbn be rebd in b relbtion of the given type.
     *
     * @pbrbm roleNbme  nbme of role to be checked
     * @pbrbm relbtionTypeNbme  nbme of the relbtion type
     *
     * @return bn Integer wrbpping bn integer corresponding to possible
     * problems represented bs constbnts in RoleUnresolved:
     * <P>- 0 if role cbn be rebd
     * <P>- integer corresponding to RoleStbtus.NO_ROLE_WITH_NAME
     * <P>- integer corresponding to RoleStbtus.ROLE_NOT_READABLE
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionTypeNotFoundException  if the relbtion type is not
     * known in the Relbtion Service
     */
    public Integer checkRoleRebding(String roleNbme,
                                    String relbtionTypeNbme)
        throws IllegblArgumentException,
               RelbtionTypeNotFoundException {

        if (roleNbme == null || relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "checkRoleRebding", new Object[] {roleNbme, relbtionTypeNbme});

        Integer result;

        // Cbn throw b RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        try {
            // Cbn throw b RoleInfoNotFoundException to be trbnsformed into
            // returned vblue RoleStbtus.NO_ROLE_WITH_NAME
            RoleInfo roleInfo = relType.getRoleInfo(roleNbme);

            result =  checkRoleInt(1,
                                   roleNbme,
                                   null,
                                   roleInfo,
                                   fblse);

        } cbtch (RoleInfoNotFoundException exc) {
            result = Integer.vblueOf(RoleStbtus.NO_ROLE_WITH_NAME);
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "checkRoleRebding");
        return result;
    }

    /**
     * Checks if given Role cbn be set in b relbtion of given type.
     *
     * @pbrbm role  role to be checked
     * @pbrbm relbtionTypeNbme  nbme of relbtion type
     * @pbrbm initFlbg  flbg to specify thbt the checking is done for the
     * initiblizbtion of b role, write bccess shbll not be verified.
     *
     * @return bn Integer wrbpping bn integer corresponding to possible
     * problems represented bs constbnts in RoleUnresolved:
     * <P>- 0 if role cbn be set
     * <P>- integer corresponding to RoleStbtus.NO_ROLE_WITH_NAME
     * <P>- integer for RoleStbtus.ROLE_NOT_WRITABLE
     * <P>- integer for RoleStbtus.LESS_THAN_MIN_ROLE_DEGREE
     * <P>- integer for RoleStbtus.MORE_THAN_MAX_ROLE_DEGREE
     * <P>- integer for RoleStbtus.REF_MBEAN_OF_INCORRECT_CLASS
     * <P>- integer for RoleStbtus.REF_MBEAN_NOT_REGISTERED
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionTypeNotFoundException  if unknown relbtion type
     */
    public Integer checkRoleWriting(Role role,
                                    String relbtionTypeNbme,
                                    Boolebn initFlbg)
        throws IllegblArgumentException,
               RelbtionTypeNotFoundException {

        if (role == null ||
            relbtionTypeNbme == null ||
            initFlbg == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "checkRoleWriting",
                new Object[] {role, relbtionTypeNbme, initFlbg});

        // Cbn throw b RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        String roleNbme = role.getRoleNbme();
        List<ObjectNbme> roleVblue = role.getRoleVblue();
        boolebn writeChkFlbg = true;
        if (initFlbg.boolebnVblue()) {
            writeChkFlbg = fblse;
        }

        RoleInfo roleInfo;
        try {
            roleInfo = relType.getRoleInfo(roleNbme);
        } cbtch (RoleInfoNotFoundException exc) {
            RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                    "checkRoleWriting");
            return Integer.vblueOf(RoleStbtus.NO_ROLE_WITH_NAME);
        }

        Integer result = checkRoleInt(2,
                                      roleNbme,
                                      roleVblue,
                                      roleInfo,
                                      writeChkFlbg);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "checkRoleWriting");
        return result;
    }

    /**
     * Sends b notificbtion (RelbtionNotificbtion) for b relbtion crebtion.
     * The notificbtion type is:
     * <P>- RelbtionNotificbtion.RELATION_BASIC_CREATION if the relbtion is bn
     * object internbl to the Relbtion Service
     * <P>- RelbtionNotificbtion.RELATION_MBEAN_CREATION if the relbtion is b
     * MBebn bdded bs b relbtion.
     * <P>The source object is the Relbtion Service itself.
     * <P>It is cblled in Relbtion Service crebteRelbtion() bnd
     * bddRelbtion() methods.
     *
     * @pbrbm relbtionId  relbtion identifier of the updbted relbtion
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if there is no relbtion for given
     * relbtion id
     */
    public void sendRelbtionCrebtionNotificbtion(String relbtionId)
        throws IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "sendRelbtionCrebtionNotificbtion", relbtionId);

        // Messbge
        StringBuilder ntfMsg = new StringBuilder("Crebtion of relbtion ");
        ntfMsg.bppend(relbtionId);

        // Cbn throw RelbtionNotFoundException
        sendNotificbtionInt(1,
                            ntfMsg.toString(),
                            relbtionId,
                            null,
                            null,
                            null,
                            null);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "sendRelbtionCrebtionNotificbtion");
        return;
    }

    /**
     * Sends b notificbtion (RelbtionNotificbtion) for b role updbte in the
     * given relbtion. The notificbtion type is:
     * <P>- RelbtionNotificbtion.RELATION_BASIC_UPDATE if the relbtion is bn
     * object internbl to the Relbtion Service
     * <P>- RelbtionNotificbtion.RELATION_MBEAN_UPDATE if the relbtion is b
     * MBebn bdded bs b relbtion.
     * <P>The source object is the Relbtion Service itself.
     * <P>It is cblled in relbtion MBebn setRole() (for given role) bnd
     * setRoles() (for ebch role) methods (implementbtion provided in
     * RelbtionSupport clbss).
     * <P>It is blso cblled in Relbtion Service setRole() (for given role) bnd
     * setRoles() (for ebch role) methods.
     *
     * @pbrbm relbtionId  relbtion identifier of the updbted relbtion
     * @pbrbm newRole  new role (nbme bnd new vblue)
     * @pbrbm oldVblue  old role vblue (List of ObjectNbme objects)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if there is no relbtion for given
     * relbtion id
     */
    public void sendRoleUpdbteNotificbtion(String relbtionId,
                                           Role newRole,
                                           List<ObjectNbme> oldVblue)
        throws IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null ||
            newRole == null ||
            oldVblue == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        if (!(oldVblue instbnceof ArrbyList<?>))
            oldVblue = new ArrbyList<ObjectNbme>(oldVblue);

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "sendRoleUpdbteNotificbtion",
                new Object[] {relbtionId, newRole, oldVblue});

        String roleNbme = newRole.getRoleNbme();
        List<ObjectNbme> newRoleVbl = newRole.getRoleVblue();

        // Messbge
        String newRoleVblString = Role.roleVblueToString(newRoleVbl);
        String oldRoleVblString = Role.roleVblueToString(oldVblue);
        StringBuilder ntfMsg = new StringBuilder("Vblue of role ");
        ntfMsg.bppend(roleNbme);
        ntfMsg.bppend(" hbs chbnged\nOld vblue:\n");
        ntfMsg.bppend(oldRoleVblString);
        ntfMsg.bppend("\nNew vblue:\n");
        ntfMsg.bppend(newRoleVblString);

        // Cbn throw b RelbtionNotFoundException
        sendNotificbtionInt(2,
                            ntfMsg.toString(),
                            relbtionId,
                            null,
                            roleNbme,
                            newRoleVbl,
                            oldVblue);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "sendRoleUpdbteNotificbtion");
    }

    /**
     * Sends b notificbtion (RelbtionNotificbtion) for b relbtion removbl.
     * The notificbtion type is:
     * <P>- RelbtionNotificbtion.RELATION_BASIC_REMOVAL if the relbtion is bn
     * object internbl to the Relbtion Service
     * <P>- RelbtionNotificbtion.RELATION_MBEAN_REMOVAL if the relbtion is b
     * MBebn bdded bs b relbtion.
     * <P>The source object is the Relbtion Service itself.
     * <P>It is cblled in Relbtion Service removeRelbtion() method.
     *
     * @pbrbm relbtionId  relbtion identifier of the updbted relbtion
     * @pbrbm unregMBebnList  List of ObjectNbmes of MBebns expected
     * to be unregistered due to relbtion removbl (cbn be null)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if there is no relbtion for given
     * relbtion id
     */
    public void sendRelbtionRemovblNotificbtion(String relbtionId,
                                                List<ObjectNbme> unregMBebnList)
        throws IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "sendRelbtionRemovblNotificbtion",
                new Object[] {relbtionId, unregMBebnList});

        // Cbn throw RelbtionNotFoundException
        sendNotificbtionInt(3,
                            "Removbl of relbtion " + relbtionId,
                            relbtionId,
                            unregMBebnList,
                            null,
                            null,
                            null);


        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "sendRelbtionRemovblNotificbtion");
        return;
    }

    /**
     * Hbndles updbte of the Relbtion Service role mbp for the updbte of given
     * role in given relbtion.
     * <P>It is cblled in relbtion MBebn setRole() (for given role) bnd
     * setRoles() (for ebch role) methods (implementbtion provided in
     * RelbtionSupport clbss).
     * <P>It is blso cblled in Relbtion Service setRole() (for given role) bnd
     * setRoles() (for ebch role) methods.
     * <P>To bllow the Relbtion Service to mbintbin the consistency (in cbse
     * of MBebn unregistrbtion) bnd to be bble to perform queries, this method
     * must be cblled when b role is updbted.
     *
     * @pbrbm relbtionId  relbtion identifier of the updbted relbtion
     * @pbrbm newRole  new role (nbme bnd new vblue)
     * @pbrbm oldVblue  old role vblue (List of ObjectNbme objects)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception RelbtionNotFoundException  if no relbtion for given id.
     */
    public void updbteRoleMbp(String relbtionId,
                              Role newRole,
                              List<ObjectNbme> oldVblue)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionNotFoundException {

        if (relbtionId == null ||
            newRole == null ||
            oldVblue == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "updbteRoleMbp", new Object[] {relbtionId, newRole, oldVblue});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Verifies the relbtion hbs been bdded in the Relbtion Service
        // Cbn throw b RelbtionNotFoundException
        Object result = getRelbtion(relbtionId);

        String roleNbme = newRole.getRoleNbme();
        List<ObjectNbme> newRoleVblue = newRole.getRoleVblue();
        // Note: no need to test if oldVblue not null before cloning,
        //       tested bbove.
        List<ObjectNbme> oldRoleVblue =
            new ArrbyList<ObjectNbme>(oldVblue);

        // List of ObjectNbmes of new referenced MBebns
        List<ObjectNbme> newRefList = new ArrbyList<ObjectNbme>();

        for (ObjectNbme currObjNbme : newRoleVblue) {

            // Checks if this ObjectNbme wbs blrebdy present in old vblue
            // Note: use copy (oldRoleVblue) instebd of originbl
            //       oldVblue to speed up, bs oldRoleVblue is decrebsed
            //       by removing unchbnged references :)
            int currObjNbmePos = oldRoleVblue.indexOf(currObjNbme);

            if (currObjNbmePos == -1) {
                // New reference to bn ObjectNbme

                // Stores this reference into mbp
                // Returns true if new reference, fblse if MBebn blrebdy
                // referenced
                boolebn isNewFlbg = bddNewMBebnReference(currObjNbme,
                                                        relbtionId,
                                                        roleNbme);

                if (isNewFlbg) {
                    // Adds it into list of new reference
                    newRefList.bdd(currObjNbme);
                }

            } else {
                // MBebn wbs blrebdy referenced in old vblue

                // Removes it from old vblue (locbl list) to ignore it when
                // looking for remove MBebn references
                oldRoleVblue.remove(currObjNbmePos);
            }
        }

        // List of ObjectNbmes of MBebns no longer referenced
        List<ObjectNbme> obsRefList = new ArrbyList<ObjectNbme>();

        // Ebch ObjectNbme rembining in oldRoleVblue is bn ObjectNbme no longer
        // referenced in new vblue
        for (ObjectNbme currObjNbme : oldRoleVblue) {
            // Removes MBebn reference from mbp
            // Returns true if the MBebn is no longer referenced in bny
            // relbtion
            boolebn noLongerRefFlbg = removeMBebnReference(currObjNbme,
                                                          relbtionId,
                                                          roleNbme,
                                                          fblse);

            if (noLongerRefFlbg) {
                // Adds it into list of references to be removed
                obsRefList.bdd(currObjNbme);
            }
        }

        // To bvoid hbving one listener per ObjectNbme of referenced MBebn,
        // bnd to increbse performbnces, there is only one listener recording
        // bll ObjectNbmes of interest
        updbteUnregistrbtionListener(newRefList, obsRefList);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "updbteRoleMbp");
        return;
    }

    /**
     * Removes given relbtion from the Relbtion Service.
     * <P>A RelbtionNotificbtion notificbtion is sent, its type being:
     * <P>- RelbtionNotificbtion.RELATION_BASIC_REMOVAL if the relbtion wbs
     * only internbl to the Relbtion Service
     * <P>- RelbtionNotificbtion.RELATION_MBEAN_REMOVAL if the relbtion is
     * registered bs bn MBebn.
     * <P>For MBebns referenced in such relbtion, nothing will be done,
     *
     * @pbrbm relbtionId  relbtion id of the relbtion to be removed
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion corresponding to
     * given relbtion id
     */
    public void removeRelbtion(String relbtionId)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RelbtionNotFoundException {

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "removeRelbtion", relbtionId);

        // Checks there is b relbtion with this id
        // Cbn throw RelbtionNotFoundException
        Object result = getRelbtion(relbtionId);

        // Removes it from listener filter
        if (result instbnceof ObjectNbme) {
            List<ObjectNbme> obsRefList = new ArrbyList<ObjectNbme>();
            obsRefList.bdd((ObjectNbme)result);
            // Cbn throw b RelbtionServiceNotRegisteredException
            updbteUnregistrbtionListener(null, obsRefList);
        }

        // Sends b notificbtion
        // Note: hbs to be done FIRST bs needs the relbtion to be still in the
        //       Relbtion Service
        // No RelbtionNotFoundException bs checked bbove

        // Revisit [cebro] Hbndle CIM "Delete" bnd "IfDeleted" qublifiers:
        //   deleting the relbtion cbn mebn to delete referenced MBebns. In
        //   thbt cbse, MBebns to be unregistered bre put in b list sent blong
        //   with the notificbtion below

        // Cbn throw b RelbtionNotFoundException (but detected bbove)
        sendRelbtionRemovblNotificbtion(relbtionId, null);

        // Removes the relbtion from vbrious internbl mbps

        //  - MBebn reference mbp
        // Retrieves the MBebns referenced in this relbtion
        // Note: here we cbnnot use removeMBebnReference() becbuse it would
        //       require to know the MBebns referenced in the relbtion. For
        //       thbt it would be necessbry to cbll 'getReferencedMBebns()'
        //       on the relbtion itself. Ok if it is bn internbl one, but if
        //       it is bn MBebn, it is possible it is blrebdy unregistered, so
        //       not bvbilbble through the MBebn Server.
        List<ObjectNbme> refMBebnList = new ArrbyList<ObjectNbme>();
        // List of MBebns no longer referenced in bny relbtion, to be
        // removed fom the mbp
        List<ObjectNbme> nonRefObjNbmeList = new ArrbyList<ObjectNbme>();

        synchronized(myRefedMBebnObjNbme2RelIdsMbp) {

            for (ObjectNbme currRefObjNbme :
                     myRefedMBebnObjNbme2RelIdsMbp.keySet()) {

                // Retrieves relbtions where the MBebn is referenced
                Mbp<String,List<String>> relIdMbp =
                    myRefedMBebnObjNbme2RelIdsMbp.get(currRefObjNbme);

                if (relIdMbp.contbinsKey(relbtionId)) {
                    relIdMbp.remove(relbtionId);
                    refMBebnList.bdd(currRefObjNbme);
                }

                if (relIdMbp.isEmpty()) {
                    // MBebn no longer referenced
                    // Note: do not remove it here becbuse pointed by the
                    //       iterbtor!
                    nonRefObjNbmeList.bdd(currRefObjNbme);
                }
            }

            // Clebns MBebn reference mbp by removing MBebns no longer
            // referenced
            for (ObjectNbme currRefObjNbme : nonRefObjNbmeList) {
                myRefedMBebnObjNbme2RelIdsMbp.remove(currRefObjNbme);
            }
        }

        // - Relbtion id to object mbp
        synchronized(myRelId2ObjMbp) {
            myRelId2ObjMbp.remove(relbtionId);
        }

        if (result instbnceof ObjectNbme) {
            // - ObjectNbme to relbtion id mbp
            synchronized(myRelMBebnObjNbme2RelIdMbp) {
                myRelMBebnObjNbme2RelIdMbp.remove((ObjectNbme)result);
            }
        }

        // Relbtion id to relbtion type nbme mbp
        // First retrieves the relbtion type nbme
        String relTypeNbme;
        synchronized(myRelId2RelTypeMbp) {
            relTypeNbme = myRelId2RelTypeMbp.get(relbtionId);
            myRelId2RelTypeMbp.remove(relbtionId);
        }
        // - Relbtion type nbme to relbtion id mbp
        synchronized(myRelType2RelIdsMbp) {
            List<String> relIdList = myRelType2RelIdsMbp.get(relTypeNbme);
            if (relIdList != null) {
                // Cbn be null if cblled from removeRelbtionType()
                relIdList.remove(relbtionId);
                if (relIdList.isEmpty()) {
                    // No other relbtion of thbt type
                    myRelType2RelIdsMbp.remove(relTypeNbme);
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "removeRelbtion");
        return;
    }

    /**
     * Purges the relbtions.
     *
     * <P>Depending on the purgeFlbg vblue, this method is either cblled
     * butombticblly when b notificbtion is received for the unregistrbtion of
     * bn MBebn referenced in b relbtion (if the flbg is set to true), or not
     * (if the flbg is set to fblse).
     * <P>In thbt cbse it is up to the user to cbll it to mbintbin the
     * consistency of the relbtions. To be kept in mind thbt if bn MBebn is
     * unregistered bnd the purge not done immedibtely, if the ObjectNbme is
     * reused bnd bssigned to bnother MBebn referenced in b relbtion, cblling
     * mbnublly this purgeRelbtions() method will cbuse trouble, bs will
     * consider the ObjectNbme bs corresponding to the unregistered MBebn, not
     * seeing the new one.
     *
     * <P>The behbvior depends on the cbrdinblity of the role where the
     * unregistered MBebn is referenced:
     * <P>- if removing one MBebn reference in the role mbkes its number of
     * references less thbn the minimum degree, the relbtion hbs to be removed.
     * <P>- if the rembining number of references bfter removing the MBebn
     * reference is still in the cbrdinblity rbnge, keep the relbtion bnd
     * updbte it cblling its hbndleMBebnUnregistrbtion() cbllbbck.
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server.
     */
    public void purgeRelbtions()
        throws RelbtionServiceNotRegisteredException {

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "purgeRelbtions");

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Revisit [cebro] Hbndle the CIM "Delete" bnd "IfDeleted" qublifier:
        //    if the unregistered MBebn hbs the "IfDeleted" qublifier,
        //    possible thbt the relbtion itself or other referenced MBebns
        //    hbve to be removed (then b notificbtion would hbve to be sent
        //    to inform thbt they should be unregistered.


        // Clones the list of notificbtions to be bble to still receive new
        // notificbtions while proceeding those ones
        List<MBebnServerNotificbtion> locblUnregNtfList;
        synchronized(myRefedMBebnObjNbme2RelIdsMbp) {
            locblUnregNtfList =
                new ArrbyList<MBebnServerNotificbtion>(myUnregNtfList);
            // Resets list
            myUnregNtfList = new ArrbyList<MBebnServerNotificbtion>();
        }


        // Updbtes the listener filter to bvoid receiving notificbtions for
        // those MBebns bgbin
        // Mbkes blso b locbl "myRefedMBebnObjNbme2RelIdsMbp" mbp, mbpping
        // ObjectNbme -> relId -> roles, to remove the MBebn from the globbl
        // mbp
        // List of references to be removed from the listener filter
        List<ObjectNbme> obsRefList = new ArrbyList<ObjectNbme>();
        // Mbp including ObjectNbmes for unregistered MBebns, with
        // referencing relbtion ids bnd roles
        Mbp<ObjectNbme,Mbp<String,List<String>>> locblMBebn2RelIdMbp =
            new HbshMbp<ObjectNbme,Mbp<String,List<String>>>();

        synchronized(myRefedMBebnObjNbme2RelIdsMbp) {
            for (MBebnServerNotificbtion currNtf : locblUnregNtfList) {

                ObjectNbme unregMBebnNbme = currNtf.getMBebnNbme();

                // Adds the unregsitered MBebn in the list of references to
                // remove from the listener filter
                obsRefList.bdd(unregMBebnNbme);

                // Retrieves the bssocibted mbp of relbtion ids bnd roles
                Mbp<String,List<String>> relIdMbp =
                    myRefedMBebnObjNbme2RelIdsMbp.get(unregMBebnNbme);
                locblMBebn2RelIdMbp.put(unregMBebnNbme, relIdMbp);

                myRefedMBebnObjNbme2RelIdsMbp.remove(unregMBebnNbme);
            }
        }

        // Updbtes the listener
        // Cbn throw RelbtionServiceNotRegisteredException
        updbteUnregistrbtionListener(null, obsRefList);

        for (MBebnServerNotificbtion currNtf : locblUnregNtfList) {

            ObjectNbme unregMBebnNbme = currNtf.getMBebnNbme();

            // Retrieves the relbtions where the MBebn is referenced
            Mbp<String,List<String>> locblRelIdMbp =
                    locblMBebn2RelIdMbp.get(unregMBebnNbme);

            // List of relbtion ids where the unregistered MBebn is
            // referenced
            for (Mbp.Entry<String,List<String>> currRel :
                        locblRelIdMbp.entrySet()) {
                finbl String currRelId = currRel.getKey();
                // List of roles of the relbtion where the MBebn is
                // referenced
                List<String> locblRoleNbmeList = currRel.getVblue();

                // Checks if the relbtion hbs to be removed or not,
                // regbrding expected minimum role cbrdinblity bnd current
                // number of references bfter removbl of the current one
                // If the relbtion is kept, cblls
                // hbndleMBebnUnregistrbtion() cbllbbck of the relbtion to
                // updbte it
                //
                // Cbn throw RelbtionServiceNotRegisteredException
                //
                // Shbll not throw RelbtionNotFoundException,
                // RoleNotFoundException, MBebnException
                try {
                    hbndleReferenceUnregistrbtion(currRelId,
                                                  unregMBebnNbme,
                                                  locblRoleNbmeList);
                } cbtch (RelbtionNotFoundException exc1) {
                    throw new RuntimeException(exc1.getMessbge());
                } cbtch (RoleNotFoundException exc2) {
                    throw new RuntimeException(exc2.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "purgeRelbtions");
        return;
    }

    /**
     * Retrieves the relbtions where b given MBebn is referenced.
     * <P>This corresponds to the CIM "References" bnd "ReferenceNbmes"
     * operbtions.
     *
     * @pbrbm mbebnNbme  ObjectNbme of MBebn
     * @pbrbm relbtionTypeNbme  cbn be null; if specified, only the relbtions
     * of thbt type will be considered in the sebrch. Else bll relbtion types
     * bre considered.
     * @pbrbm roleNbme  cbn be null; if specified, only the relbtions
     * where the MBebn is referenced in thbt role will be returned. Else bll
     * roles bre considered.
     *
     * @return bn HbshMbp, where the keys bre the relbtion ids of the relbtions
     * where the MBebn is referenced, bnd the vblue is, for ebch key,
     * bn ArrbyList of role nbmes (bs bn MBebn cbn be referenced in severbl
     * roles in the sbme relbtion).
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public Mbp<String,List<String>>
        findReferencingRelbtions(ObjectNbme mbebnNbme,
                                 String relbtionTypeNbme,
                                 String roleNbme)
            throws IllegblArgumentException {

        if (mbebnNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "findReferencingRelbtions",
                new Object[] {mbebnNbme, relbtionTypeNbme, roleNbme});

        Mbp<String,List<String>> result = new HbshMbp<String,List<String>>();

        synchronized(myRefedMBebnObjNbme2RelIdsMbp) {

            // Retrieves the relbtions referencing the MBebn
            Mbp<String,List<String>> relId2RoleNbmesMbp =
                myRefedMBebnObjNbme2RelIdsMbp.get(mbebnNbme);

            if (relId2RoleNbmesMbp != null) {

                // Relbtion Ids where the MBebn is referenced
                Set<String> bllRelIdSet = relId2RoleNbmesMbp.keySet();

                // List of relbtion ids of interest regbrding the selected
                // relbtion type
                List<String> relIdList;
                if (relbtionTypeNbme == null) {
                    // Considers bll relbtions
                    relIdList = new ArrbyList<String>(bllRelIdSet);

                } else {

                    relIdList = new ArrbyList<String>();

                    // Considers only the relbtion ids for relbtions of given
                    // type
                    for (String currRelId : bllRelIdSet) {

                        // Retrieves its relbtion type
                        String currRelTypeNbme;
                        synchronized(myRelId2RelTypeMbp) {
                            currRelTypeNbme =
                                myRelId2RelTypeMbp.get(currRelId);
                        }

                        if (currRelTypeNbme.equbls(relbtionTypeNbme)) {

                            relIdList.bdd(currRelId);

                        }
                    }
                }

                // Now looks bt the roles where the MBebn is expected to be
                // referenced

                for (String currRelId : relIdList) {
                    // Retrieves list of role nbmes where the MBebn is
                    // referenced
                    List<String> currRoleNbmeList =
                        relId2RoleNbmesMbp.get(currRelId);

                    if (roleNbme == null) {
                        // All roles to be considered
                        // Note: no need to test if list not null before
                        //       cloning, MUST be not null else bug :(
                        result.put(currRelId,
                                   new ArrbyList<String>(currRoleNbmeList));

                    }  else if (currRoleNbmeList.contbins(roleNbme)) {
                        // Filters only the relbtions where the MBebn is
                        // referenced in // given role
                        List<String> dummyList = new ArrbyList<String>();
                        dummyList.bdd(roleNbme);
                        result.put(currRelId, dummyList);
                    }
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "findReferencingRelbtions");
        return result;
    }

    /**
     * Retrieves the MBebns bssocibted to given one in b relbtion.
     * <P>This corresponds to CIM Associbtors bnd AssocibtorNbmes operbtions.
     *
     * @pbrbm mbebnNbme  ObjectNbme of MBebn
     * @pbrbm relbtionTypeNbme  cbn be null; if specified, only the relbtions
     * of thbt type will be considered in the sebrch. Else bll
     * relbtion types bre considered.
     * @pbrbm roleNbme  cbn be null; if specified, only the relbtions
     * where the MBebn is referenced in thbt role will be considered. Else bll
     * roles bre considered.
     *
     * @return bn HbshMbp, where the keys bre the ObjectNbmes of the MBebns
     * bssocibted to given MBebn, bnd the vblue is, for ebch key, bn ArrbyList
     * of the relbtion ids of the relbtions where the key MBebn is
     * bssocibted to given one (bs they cbn be bssocibted in severbl different
     * relbtions).
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public Mbp<ObjectNbme,List<String>>
        findAssocibtedMBebns(ObjectNbme mbebnNbme,
                             String relbtionTypeNbme,
                             String roleNbme)
            throws IllegblArgumentException {

        if (mbebnNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "findAssocibtedMBebns",
                new Object[] {mbebnNbme, relbtionTypeNbme, roleNbme});

        // Retrieves the mbp <relbtion id> -> <role nbmes> for those
        // criteribs
        Mbp<String,List<String>> relId2RoleNbmesMbp =
            findReferencingRelbtions(mbebnNbme,
                                     relbtionTypeNbme,
                                     roleNbme);

        Mbp<ObjectNbme,List<String>> result =
            new HbshMbp<ObjectNbme,List<String>>();

        for (String currRelId : relId2RoleNbmesMbp.keySet()) {

            // Retrieves ObjectNbmes of MBebns referenced in this relbtion
            //
            // Shbll not throw b RelbtionNotFoundException if incorrect stbtus
            // of mbps :(
            Mbp<ObjectNbme,List<String>> objNbme2RoleNbmesMbp;
            try {
                objNbme2RoleNbmesMbp = getReferencedMBebns(currRelId);
            } cbtch (RelbtionNotFoundException exc) {
                throw new RuntimeException(exc.getMessbge());
            }

            // For ebch MBebn bssocibted to given one in b relbtion, bdds the
            // bssocibtion <ObjectNbme> -> <relbtion id> into result mbp
            for (ObjectNbme currObjNbme : objNbme2RoleNbmesMbp.keySet()) {

                if (!(currObjNbme.equbls(mbebnNbme))) {

                    // Sees if this MBebn is blrebdy bssocibted to the given
                    // one in bnother relbtion
                    List<String> currRelIdList = result.get(currObjNbme);
                    if (currRelIdList == null) {

                        currRelIdList = new ArrbyList<String>();
                        currRelIdList.bdd(currRelId);
                        result.put(currObjNbme, currRelIdList);

                    } else {
                        currRelIdList.bdd(currRelId);
                    }
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "findAssocibtedMBebns");
        return result;
    }

    /**
     * Returns the relbtion ids for relbtions of the given type.
     *
     * @pbrbm relbtionTypeNbme  relbtion type nbme
     *
     * @return bn ArrbyList of relbtion ids.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionTypeNotFoundException  if there is no relbtion type
     * with thbt nbme.
     */
    public List<String> findRelbtionsOfType(String relbtionTypeNbme)
        throws IllegblArgumentException,
               RelbtionTypeNotFoundException {

        if (relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "findRelbtionsOfType");

        // Cbn throw RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        List<String> result;
        synchronized(myRelType2RelIdsMbp) {
            List<String> result1 = myRelType2RelIdsMbp.get(relbtionTypeNbme);
            if (result1 == null)
                result = new ArrbyList<String>();
            else
                result = new ArrbyList<String>(result1);
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "findRelbtionsOfType");
        return result;
    }

    /**
     * Retrieves role vblue for given role nbme in given relbtion.
     *
     * @pbrbm relbtionId  relbtion id
     * @pbrbm roleNbme  nbme of role
     *
     * @return the ArrbyList of ObjectNbme objects being the role vblue
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion with given id
     * @exception RoleNotFoundException  if:
     * <P>- there is no role with given nbme
     * <P>or
     * <P>- the role is not rebdbble.
     *
     * @see #setRole
     */
    public List<ObjectNbme> getRole(String relbtionId,
                                    String roleNbme)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RelbtionNotFoundException,
               RoleNotFoundException {

        if (relbtionId == null || roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRole", new Object[] {relbtionId, roleNbme});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        List<ObjectNbme> result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            // Cbn throw RoleNotFoundException
            result = cbst(
                ((RelbtionSupport)relObj).getRoleInt(roleNbme,
                                                     true,
                                                     this,
                                                     fblse));

        } else {
            // Relbtion MBebn
            Object[] pbrbms = new Object[1];
            pbrbms[0] = roleNbme;
            String[] signbture = new String[1];
            signbture[0] = "jbvb.lbng.String";
            // Cbn throw MBebnException wrbpping b RoleNotFoundException:
            // throw wrbpped exception
            //
            // Shbll not throw InstbnceNotFoundException or ReflectionException
            try {
                List<ObjectNbme> invokeResult = cbst(
                    myMBebnServer.invoke(((ObjectNbme)relObj),
                                         "getRole",
                                         pbrbms,
                                         signbture));
                if (invokeResult == null || invokeResult instbnceof ArrbyList<?>)
                    result = invokeResult;
                else
                    result = new ArrbyList<ObjectNbme>(invokeResult);
            } cbtch (InstbnceNotFoundException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (ReflectionException exc2) {
                throw new RuntimeException(exc2.getMessbge());
            } cbtch (MBebnException exc3) {
                Exception wrbppedExc = exc3.getTbrgetException();
                if (wrbppedExc instbnceof RoleNotFoundException) {
                    throw ((RoleNotFoundException)wrbppedExc);
                } else {
                    throw new RuntimeException(wrbppedExc.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(), "getRole");
        return result;
    }

    /**
     * Retrieves vblues of roles with given nbmes in given relbtion.
     *
     * @pbrbm relbtionId  relbtion id
     * @pbrbm roleNbmeArrby  brrby of nbmes of roles to be retrieved
     *
     * @return b RoleResult object, including b RoleList (for roles
     * successfully retrieved) bnd b RoleUnresolvedList (for roles not
     * retrieved).
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion with given id
     *
     * @see #setRoles
     */
    public RoleResult getRoles(String relbtionId,
                               String[] roleNbmeArrby)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null || roleNbmeArrby == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRoles", relbtionId);

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        RoleResult result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            result = ((RelbtionSupport)relObj).getRolesInt(roleNbmeArrby,
                                                        true,
                                                        this);
        } else {
            // Relbtion MBebn
            Object[] pbrbms = new Object[1];
            pbrbms[0] = roleNbmeArrby;
            String[] signbture = new String[1];
            try {
                signbture[0] = (roleNbmeArrby.getClbss()).getNbme();
            } cbtch (Exception exc) {
                // OK : This is bn brrby of jbvb.lbng.String
                //      so this should never hbppen...
            }
            // Shbll not throw InstbnceNotFoundException, ReflectionException
            // or MBebnException
            try {
                result = (RoleResult)
                    (myMBebnServer.invoke(((ObjectNbme)relObj),
                                          "getRoles",
                                          pbrbms,
                                          signbture));
            } cbtch (InstbnceNotFoundException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (ReflectionException exc2) {
                throw new RuntimeException(exc2.getMessbge());
            } cbtch (MBebnException exc3) {
                throw new
                    RuntimeException((exc3.getTbrgetException()).getMessbge());
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(), "getRoles");
        return result;
    }

    /**
     * Returns bll roles present in the relbtion.
     *
     * @pbrbm relbtionId  relbtion id
     *
     * @return b RoleResult object, including b RoleList (for roles
     * successfully retrieved) bnd b RoleUnresolvedList (for roles not
     * rebdbble).
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion for given id
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     */
    public RoleResult getAllRoles(String relbtionId)
        throws IllegblArgumentException,
               RelbtionNotFoundException,
               RelbtionServiceNotRegisteredException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRoles", relbtionId);

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        RoleResult result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            result = ((RelbtionSupport)relObj).getAllRolesInt(true, this);

        } else {
            // Relbtion MBebn
            // Shbll not throw bny Exception
            try {
                result = (RoleResult)
                    (myMBebnServer.getAttribute(((ObjectNbme)relObj),
                                                "AllRoles"));
            } cbtch (Exception exc) {
                throw new RuntimeException(exc.getMessbge());
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(), "getRoles");
        return result;
    }

    /**
     * Retrieves the number of MBebns currently referenced in the given role.
     *
     * @pbrbm relbtionId  relbtion id
     * @pbrbm roleNbme  nbme of role
     *
     * @return the number of currently referenced MBebns in thbt role
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion with given id
     * @exception RoleNotFoundException  if there is no role with given nbme
     */
    public Integer getRoleCbrdinblity(String relbtionId,
                                      String roleNbme)
        throws IllegblArgumentException,
               RelbtionNotFoundException,
               RoleNotFoundException {

        if (relbtionId == null || roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRoleCbrdinblity", new Object[] {relbtionId, roleNbme});

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        Integer result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            // Cbn throw RoleNotFoundException
            result = ((RelbtionSupport)relObj).getRoleCbrdinblity(roleNbme);

        } else {
            // Relbtion MBebn
            Object[] pbrbms = new Object[1];
            pbrbms[0] = roleNbme;
            String[] signbture = new String[1];
            signbture[0] = "jbvb.lbng.String";
            // Cbn throw MBebnException wrbpping RoleNotFoundException:
            // throw wrbpped exception
            //
            // Shbll not throw InstbnceNotFoundException or ReflectionException
            try {
                result = (Integer)
                    (myMBebnServer.invoke(((ObjectNbme)relObj),
                                          "getRoleCbrdinblity",
                                          pbrbms,
                                          signbture));
            } cbtch (InstbnceNotFoundException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (ReflectionException exc2) {
                throw new RuntimeException(exc2.getMessbge());
            } cbtch (MBebnException exc3) {
                Exception wrbppedExc = exc3.getTbrgetException();
                if (wrbppedExc instbnceof RoleNotFoundException) {
                    throw ((RoleNotFoundException)wrbppedExc);
                } else {
                    throw new RuntimeException(wrbppedExc.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getRoleCbrdinblity");
        return result;
    }

    /**
     * Sets the given role in given relbtion.
     * <P>Will check the role bccording to its corresponding role definition
     * provided in relbtion's relbtion type
     * <P>The Relbtion Service will keep trbck of the chbnge to keep the
     * consistency of relbtions by hbndling referenced MBebn deregistrbtions.
     *
     * @pbrbm relbtionId  relbtion id
     * @pbrbm role  role to be set (nbme bnd new vblue)
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion with given id
     * @exception RoleNotFoundException  if the role does not exist or is not
     * writbble
     * @exception InvblidRoleVblueException  if vblue provided for role is not
     * vblid:
     * <P>- the number of referenced MBebns in given vblue is less thbn
     * expected minimum degree
     * <P>or
     * <P>- the number of referenced MBebns in provided vblue exceeds expected
     * mbximum degree
     * <P>or
     * <P>- one referenced MBebn in the vblue is not bn Object of the MBebn
     * clbss expected for thbt role
     * <P>or
     * <P>- bn MBebn provided for thbt role does not exist
     *
     * @see #getRole
     */
    public void setRole(String relbtionId,
                        Role role)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RelbtionNotFoundException,
               RoleNotFoundException,
               InvblidRoleVblueException {

        if (relbtionId == null || role == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "setRole", new Object[] {relbtionId, role});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            // Cbn throw RoleNotFoundException,
            // InvblidRoleVblueException bnd
            // RelbtionServiceNotRegisteredException
            //
            // Shbll not throw RelbtionTypeNotFoundException
            // (bs relbtion exists in the RS, its relbtion type is known)
            try {
                ((RelbtionSupport)relObj).setRoleInt(role,
                                                  true,
                                                  this,
                                                  fblse);

            } cbtch (RelbtionTypeNotFoundException exc) {
                throw new RuntimeException(exc.getMessbge());
            }

        } else {
            // Relbtion MBebn
            Object[] pbrbms = new Object[1];
            pbrbms[0] = role;
            String[] signbture = new String[1];
            signbture[0] = "jbvbx.mbnbgement.relbtion.Role";
            // Cbn throw MBebnException wrbpping RoleNotFoundException,
            // InvblidRoleVblueException
            //
            // Shbll not MBebnException wrbpping bn MBebnException wrbpping
            // RelbtionTypeNotFoundException, or ReflectionException, or
            // InstbnceNotFoundException
            try {
                myMBebnServer.setAttribute(((ObjectNbme)relObj),
                                           new Attribute("Role", role));

            } cbtch (InstbnceNotFoundException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (ReflectionException exc3) {
                throw new RuntimeException(exc3.getMessbge());
            } cbtch (MBebnException exc2) {
                Exception wrbppedExc = exc2.getTbrgetException();
                if (wrbppedExc instbnceof RoleNotFoundException) {
                    throw ((RoleNotFoundException)wrbppedExc);
                } else if (wrbppedExc instbnceof InvblidRoleVblueException) {
                    throw ((InvblidRoleVblueException)wrbppedExc);
                } else {
                    throw new RuntimeException(wrbppedExc.getMessbge());

                }
            } cbtch (AttributeNotFoundException exc4) {
              throw new RuntimeException(exc4.getMessbge());
            } cbtch (InvblidAttributeVblueException exc5) {
              throw new RuntimeException(exc5.getMessbge());
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(), "setRole");
        return;
    }

    /**
     * Sets the given roles in given relbtion.
     * <P>Will check the role bccording to its corresponding role definition
     * provided in relbtion's relbtion type
     * <P>The Relbtion Service keeps trbck of the chbnges to keep the
     * consistency of relbtions by hbndling referenced MBebn deregistrbtions.
     *
     * @pbrbm relbtionId  relbtion id
     * @pbrbm roleList  list of roles to be set
     *
     * @return b RoleResult object, including b RoleList (for roles
     * successfully set) bnd b RoleUnresolvedList (for roles not
     * set).
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion with given id
     *
     * @see #getRoles
     */
    public RoleResult setRoles(String relbtionId,
                               RoleList roleList)
        throws RelbtionServiceNotRegisteredException,
               IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null || roleList == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "setRoles", new Object[] {relbtionId, roleList});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        RoleResult result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            // Cbn throw RelbtionServiceNotRegisteredException
            //
            // Shbll not throw RelbtionTypeNotFoundException (bs relbtion is
            // known, its relbtion type exists)
            try {
                result = ((RelbtionSupport)relObj).setRolesInt(roleList,
                                                            true,
                                                            this);
            } cbtch (RelbtionTypeNotFoundException exc) {
                throw new RuntimeException(exc.getMessbge());
            }

        } else {
            // Relbtion MBebn
            Object[] pbrbms = new Object[1];
            pbrbms[0] = roleList;
            String[] signbture = new String[1];
            signbture[0] = "jbvbx.mbnbgement.relbtion.RoleList";
            // Shbll not throw InstbnceNotFoundException or bn MBebnException
            // or ReflectionException
            try {
                result = (RoleResult)
                    (myMBebnServer.invoke(((ObjectNbme)relObj),
                                          "setRoles",
                                          pbrbms,
                                          signbture));
            } cbtch (InstbnceNotFoundException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (ReflectionException exc3) {
                throw new RuntimeException(exc3.getMessbge());
            } cbtch (MBebnException exc2) {
                throw new
                    RuntimeException((exc2.getTbrgetException()).getMessbge());
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(), "setRoles");
        return result;
    }

    /**
     * Retrieves MBebns referenced in the vbrious roles of the relbtion.
     *
     * @pbrbm relbtionId  relbtion id
     *
     * @return b HbshMbp mbpping:
     * <P> ObjectNbme {@literbl ->} ArrbyList of String (role nbmes)
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion for given
     * relbtion id
     */
    public Mbp<ObjectNbme,List<String>>
        getReferencedMBebns(String relbtionId)
            throws IllegblArgumentException,
        RelbtionNotFoundException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getReferencedMBebns", relbtionId);

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        Mbp<ObjectNbme,List<String>> result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            result = ((RelbtionSupport)relObj).getReferencedMBebns();

        } else {
            // Relbtion MBebn
            // No Exception
            try {
                result = cbst(
                    myMBebnServer.getAttribute(((ObjectNbme)relObj),
                                               "ReferencedMBebns"));
            } cbtch (Exception exc) {
                throw new RuntimeException(exc.getMessbge());
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getReferencedMBebns");
        return result;
    }

    /**
     * Returns nbme of bssocibted relbtion type for given relbtion.
     *
     * @pbrbm relbtionId  relbtion id
     *
     * @return the nbme of the bssocibted relbtion type.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RelbtionNotFoundException  if no relbtion for given
     * relbtion id
     */
    public String getRelbtionTypeNbme(String relbtionId)
        throws IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRelbtionTypeNbme", relbtionId);

        // Cbn throw b RelbtionNotFoundException
        Object relObj = getRelbtion(relbtionId);

        String result;

        if (relObj instbnceof RelbtionSupport) {
            // Internbl relbtion
            result = ((RelbtionSupport)relObj).getRelbtionTypeNbme();

        } else {
            // Relbtion MBebn
            // No Exception
            try {
                result = (String)
                    (myMBebnServer.getAttribute(((ObjectNbme)relObj),
                                                "RelbtionTypeNbme"));
            } cbtch (Exception exc) {
                throw new RuntimeException(exc.getMessbge());
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getRelbtionTypeNbme");
        return result;
    }

    //
    // NotificbtionListener Interfbce
    //

    /**
     * Invoked when b JMX notificbtion occurs.
     * Currently hbndles notificbtions for unregistrbtion of MBebns, either
     * referenced in b relbtion role or being b relbtion itself.
     *
     * @pbrbm notif  The notificbtion.
     * @pbrbm hbndbbck  An opbque object which helps the listener to
     * bssocibte informbtion regbrding the MBebn emitter (cbn be null).
     */
    public void hbndleNotificbtion(Notificbtion notif,
                                   Object hbndbbck) {

        if (notif == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "hbndleNotificbtion", notif);

        if (notif instbnceof MBebnServerNotificbtion) {

            MBebnServerNotificbtion mbsNtf = (MBebnServerNotificbtion) notif;
            String ntfType = notif.getType();

            if (ntfType.equbls(
                       MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION )) {
                ObjectNbme mbebnNbme =
                    ((MBebnServerNotificbtion)notif).getMBebnNbme();

                // Note: use b flbg to block bccess to
                // myRefedMBebnObjNbme2RelIdsMbp only for b quick bccess
                boolebn isRefedMBebnFlbg = fblse;
                synchronized(myRefedMBebnObjNbme2RelIdsMbp) {

                    if (myRefedMBebnObjNbme2RelIdsMbp.contbinsKey(mbebnNbme)) {
                        // Unregistrbtion of b referenced MBebn
                        synchronized(myUnregNtfList) {
                            myUnregNtfList.bdd(mbsNtf);
                        }
                        isRefedMBebnFlbg = true;
                    }
                    if (isRefedMBebnFlbg && myPurgeFlbg) {
                        // Immedibte purge
                        // Cbn throw RelbtionServiceNotRegisteredException
                        // but bssume thbt will be fine :)
                        try {
                            purgeRelbtions();
                        } cbtch (Exception exc) {
                            throw new RuntimeException(exc.getMessbge());
                        }
                    }
                }

                // Note: do both tests bs b relbtion cbn be bn MBebn bnd be
                //       itself referenced in bnother relbtion :)
                String relId;
                synchronized(myRelMBebnObjNbme2RelIdMbp){
                    relId = myRelMBebnObjNbme2RelIdMbp.get(mbebnNbme);
                }
                if (relId != null) {
                    // Unregistrbtion of b relbtion MBebn
                    // Cbn throw RelbtionTypeNotFoundException,
                    // RelbtionServiceNotRegisteredException
                    //
                    // Shbll not throw RelbtionTypeNotFoundException or
                    // InstbnceNotFoundException
                    try {
                        removeRelbtion(relId);
                    } cbtch (Exception exc) {
                        throw new RuntimeException(exc.getMessbge());
                    }
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "hbndleNotificbtion");
        return;
    }

    //
    // NotificbtionBrobdcbster interfbce
    //

    /**
     * Returns b NotificbtionInfo object contbining the nbme of the Jbvb clbss
     * of the notificbtion bnd the notificbtion types sent.
     */
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getNotificbtionInfo");

        String ntfClbss = "jbvbx.mbnbgement.relbtion.RelbtionNotificbtion";

        String[] ntfTypes = new String[] {
            RelbtionNotificbtion.RELATION_BASIC_CREATION,
            RelbtionNotificbtion.RELATION_MBEAN_CREATION,
            RelbtionNotificbtion.RELATION_BASIC_UPDATE,
            RelbtionNotificbtion.RELATION_MBEAN_UPDATE,
            RelbtionNotificbtion.RELATION_BASIC_REMOVAL,
            RelbtionNotificbtion.RELATION_MBEAN_REMOVAL,
        };

        String ntfDesc = "Sent when b relbtion is crebted, updbted or deleted.";

        MBebnNotificbtionInfo ntfInfo =
            new MBebnNotificbtionInfo(ntfTypes, ntfClbss, ntfDesc);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getNotificbtionInfo");
        return new MBebnNotificbtionInfo[] {ntfInfo};
    }

    //
    // Misc
    //

    // Adds given object bs b relbtion type.
    //
    // -pbrbm relbtionTypeObj  relbtion type object
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception InvblidRelbtionTypeException  if there is blrebdy b relbtion
    //  type with thbt nbme
    privbte void bddRelbtionTypeInt(RelbtionType relbtionTypeObj)
        throws IllegblArgumentException,
               InvblidRelbtionTypeException {

        if (relbtionTypeObj == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "bddRelbtionTypeInt");

        String relTypeNbme = relbtionTypeObj.getRelbtionTypeNbme();

        // Checks thbt there is not blrebdy b relbtion type with thbt nbme
        // existing in the Relbtion Service
        try {
            // Cbn throw b RelbtionTypeNotFoundException (in fbct should ;)
            RelbtionType relType = getRelbtionType(relTypeNbme);

            if (relType != null) {
                String excMsg = "There is blrebdy b relbtion type in the Relbtion Service with nbme ";
                StringBuilder excMsgStrB = new StringBuilder(excMsg);
                excMsgStrB.bppend(relTypeNbme);
                throw new InvblidRelbtionTypeException(excMsgStrB.toString());
            }

        } cbtch (RelbtionTypeNotFoundException exc) {
            // OK : The RelbtionType could not be found.
        }

        // Adds the relbtion type
        synchronized(myRelType2ObjMbp) {
            myRelType2ObjMbp.put(relTypeNbme, relbtionTypeObj);
        }

        if (relbtionTypeObj instbnceof RelbtionTypeSupport) {
            ((RelbtionTypeSupport)relbtionTypeObj).setRelbtionServiceFlbg(true);
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "bddRelbtionTypeInt");
        return;
     }

    // Retrieves relbtion type with given nbme
    //
    // -pbrbm relbtionTypeNbme  expected nbme of b relbtion type crebted in the
    //  Relbtion Service
    //
    // -return RelbtionType object corresponding to given nbme
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RelbtionTypeNotFoundException  if no relbtion type for thbt
    //  nbme crebted in Relbtion Service
    //
    RelbtionType getRelbtionType(String relbtionTypeNbme)
        throws IllegblArgumentException,
               RelbtionTypeNotFoundException {

        if (relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRelbtionType", relbtionTypeNbme);

        // No null relbtion type bccepted, so cbn use get()
        RelbtionType relType;
        synchronized(myRelType2ObjMbp) {
            relType = (myRelType2ObjMbp.get(relbtionTypeNbme));
        }

        if (relType == null) {
            String excMsg = "No relbtion type crebted in the Relbtion Service with the nbme ";
            StringBuilder excMsgStrB = new StringBuilder(excMsg);
            excMsgStrB.bppend(relbtionTypeNbme);
            throw new RelbtionTypeNotFoundException(excMsgStrB.toString());
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getRelbtionType");
        return relType;
    }

    // Retrieves relbtion corresponding to given relbtion id.
    // Returns either:
    // - b RelbtionSupport object if the relbtion is internbl
    // or
    // - the ObjectNbme of the corresponding MBebn
    //
    // -pbrbm relbtionId  expected relbtion id
    //
    // -return RelbtionSupport object or ObjectNbme of relbtion with given id
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RelbtionNotFoundException  if no relbtion for thbt
    //  relbtion id crebted in Relbtion Service
    //
    Object getRelbtion(String relbtionId)
        throws IllegblArgumentException,
               RelbtionNotFoundException {

        if (relbtionId == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "getRelbtion", relbtionId);

        // No null relbtion  bccepted, so cbn use get()
        Object rel;
        synchronized(myRelId2ObjMbp) {
            rel = myRelId2ObjMbp.get(relbtionId);
        }

        if (rel == null) {
            String excMsg = "No relbtion bssocibted to relbtion id " + relbtionId;
            throw new RelbtionNotFoundException(excMsg);
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "getRelbtion");
        return rel;
    }

    // Adds b new MBebn reference (reference to bn ObjectNbme) in the
    // referenced MBebn mbp (myRefedMBebnObjNbme2RelIdsMbp).
    //
    // -pbrbm objectNbme  ObjectNbme of new referenced MBebn
    // -pbrbm relbtionId  relbtion id of the relbtion where the MBebn is
    //  referenced
    // -pbrbm roleNbme  nbme of the role where the MBebn is referenced
    //
    // -return boolebn:
    //  - true  if the MBebn wbs not referenced before, so reblly b new
    //    reference
    //  - fblse else
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    privbte boolebn bddNewMBebnReference(ObjectNbme objectNbme,
                                         String relbtionId,
                                         String roleNbme)
        throws IllegblArgumentException {

        if (objectNbme == null ||
            relbtionId == null ||
            roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "bddNewMBebnReference",
                new Object[] {objectNbme, relbtionId, roleNbme});

        boolebn isNewFlbg = fblse;

        synchronized(myRefedMBebnObjNbme2RelIdsMbp) {

            // Checks if the MBebn wbs blrebdy referenced
            // No null vblue bllowed, use get() directly
            Mbp<String,List<String>> mbebnRefMbp =
                myRefedMBebnObjNbme2RelIdsMbp.get(objectNbme);

            if (mbebnRefMbp == null) {
                // MBebn not referenced in bny relbtion yet

                isNewFlbg = true;

                // List of roles where the MBebn is referenced in given
                // relbtion
                List<String> roleNbmes = new ArrbyList<String>();
                roleNbmes.bdd(roleNbme);

                // Mbp of relbtions where the MBebn is referenced
                mbebnRefMbp = new HbshMbp<String,List<String>>();
                mbebnRefMbp.put(relbtionId, roleNbmes);

                myRefedMBebnObjNbme2RelIdsMbp.put(objectNbme, mbebnRefMbp);

            } else {
                // MBebn blrebdy referenced in bt lebst bnother relbtion
                // Checks if blrebdy referenced in bnother role in current
                // relbtion
                List<String> roleNbmes = mbebnRefMbp.get(relbtionId);

                if (roleNbmes == null) {
                    // MBebn not referenced in current relbtion

                    // List of roles where the MBebn is referenced in given
                    // relbtion
                    roleNbmes = new ArrbyList<String>();
                    roleNbmes.bdd(roleNbme);

                    // Adds new reference done in current relbtion
                    mbebnRefMbp.put(relbtionId, roleNbmes);

                } else {
                    // MBebn blrebdy referenced in current relbtion in bnother
                    // role
                    // Adds new reference done
                    roleNbmes.bdd(roleNbme);
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "bddNewMBebnReference");
        return isNewFlbg;
    }

    // Removes bn obsolete MBebn reference (reference to bn ObjectNbme) in
    // the referenced MBebn mbp (myRefedMBebnObjNbme2RelIdsMbp).
    //
    // -pbrbm objectNbme  ObjectNbme of MBebn no longer referenced
    // -pbrbm relbtionId  relbtion id of the relbtion where the MBebn wbs
    //  referenced
    // -pbrbm roleNbme  nbme of the role where the MBebn wbs referenced
    // -pbrbm bllRolesFlbg  flbg, if true removes reference to MBebn for bll
    //  roles in the relbtion, not only for the one bbove
    //
    // -return boolebn:
    //  - true  if the MBebn is no longer reference in bny relbtion
    //  - fblse else
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    privbte boolebn removeMBebnReference(ObjectNbme objectNbme,
                                         String relbtionId,
                                         String roleNbme,
                                         boolebn bllRolesFlbg)
        throws IllegblArgumentException {

        if (objectNbme == null ||
            relbtionId == null ||
            roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "removeMBebnReference",
                new Object[] {objectNbme, relbtionId, roleNbme, bllRolesFlbg});

        boolebn noLongerRefFlbg = fblse;

        synchronized(myRefedMBebnObjNbme2RelIdsMbp) {

            // Retrieves the set of relbtions (designed vib their relbtion ids)
            // where the MBebn is referenced
            // Note thbt it is possible thbt the MBebn hbs blrebdy been removed
            // from the internbl mbp: this is the cbse when the MBebn is
            // unregistered, the role is updbted, then we brrive here.
            Mbp<String,List<String>> mbebnRefMbp =
                (myRefedMBebnObjNbme2RelIdsMbp.get(objectNbme));

            if (mbebnRefMbp == null) {
                // The MBebn is no longer referenced
                RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                        "removeMBebnReference");
                return true;
            }

            List<String> roleNbmes = null;
            if (!bllRolesFlbg) {
                // Now retrieves the roles of current relbtion where the MBebn
                // wbs referenced
                roleNbmes = mbebnRefMbp.get(relbtionId);

                // Removes obsolete reference to role
                int obsRefIdx = roleNbmes.indexOf(roleNbme);
                if (obsRefIdx != -1) {
                    roleNbmes.remove(obsRefIdx);
                }
            }

            // Checks if there is still bt lebst one role in current relbtion
            // where the MBebn is referenced
            if (roleNbmes.isEmpty() || bllRolesFlbg) {
                // MBebn no longer referenced in current relbtion: removes
                // entry
                mbebnRefMbp.remove(relbtionId);
            }

            // Checks if the MBebn is still referenced in bt lebst on relbtion
            if (mbebnRefMbp.isEmpty()) {
                // MBebn no longer referenced in bny relbtion: removes entry
                myRefedMBebnObjNbme2RelIdsMbp.remove(objectNbme);
                noLongerRefFlbg = true;
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "removeMBebnReference");
        return noLongerRefFlbg;
    }

    // Updbtes the listener registered to the MBebn Server to be informed of
    // referenced MBebn deregistrbtions
    //
    // -pbrbm newRefList  ArrbyList of ObjectNbmes for new references done
    //  to MBebns (cbn be null)
    // -pbrbm obsoleteRefList  ArrbyList of ObjectNbmes for obsolete references
    //  to MBebns (cbn be null)
    //
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server.
    privbte void updbteUnregistrbtionListener(List<ObjectNbme> newRefList,
                                              List<ObjectNbme> obsoleteRefList)
        throws RelbtionServiceNotRegisteredException {

        if (newRefList != null && obsoleteRefList != null) {
            if (newRefList.isEmpty() && obsoleteRefList.isEmpty()) {
                // Nothing to do :)
                return;
            }
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "updbteUnregistrbtionListener",
                new Object[] {newRefList, obsoleteRefList});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        if (newRefList != null || obsoleteRefList != null) {

            boolebn newListenerFlbg = fblse;
            if (myUnregNtfFilter == null) {
                // Initiblize it to be bble to synchronise it :)
                myUnregNtfFilter = new MBebnServerNotificbtionFilter();
                newListenerFlbg = true;
            }

            synchronized(myUnregNtfFilter) {

                // Enbbles ObjectNbmes in newRefList
                if (newRefList != null) {
                    for (ObjectNbme newObjNbme : newRefList)
                        myUnregNtfFilter.enbbleObjectNbme(newObjNbme);
                }

                if (obsoleteRefList != null) {
                    // Disbbles ObjectNbmes in obsoleteRefList
                    for (ObjectNbme obsObjNbme : obsoleteRefList)
                        myUnregNtfFilter.disbbleObjectNbme(obsObjNbme);
                }

// Under test
                if (newListenerFlbg) {
                    try {
                        myMBebnServer.bddNotificbtionListener(
                                MBebnServerDelegbte.DELEGATE_NAME,
                                this,
                                myUnregNtfFilter,
                                null);
                    } cbtch (InstbnceNotFoundException exc) {
                        throw new
                       RelbtionServiceNotRegisteredException(exc.getMessbge());
                    }
                }
// End test


//              if (!newListenerFlbg) {
                    // The Relbtion Service wbs blrebdy registered bs b
                    // listener:
                    // removes it
                    // Shbll not throw InstbnceNotFoundException (bs the
                    // MBebn Server Delegbte is expected to exist) or
                    // ListenerNotFoundException (bs it hbs been checked bbove
                    // thbt the Relbtion Service is registered)
//                  try {
//                      myMBebnServer.removeNotificbtionListener(
//                              MBebnServerDelegbte.DELEGATE_NAME,
//                              this);
//                  } cbtch (InstbnceNotFoundException exc1) {
//                      throw new RuntimeException(exc1.getMessbge());
//                  } cbtch (ListenerNotFoundException exc2) {
//                      throw new
//                          RelbtionServiceNotRegisteredException(exc2.getMessbge());
//                  }
//              }

                // Adds Relbtion Service with current filter
                // Cbn throw InstbnceNotFoundException if the Relbtion
                // Service is not registered, to be trbnsformed into
                // RelbtionServiceNotRegisteredException
                //
                // Assume thbt there will not be bny InstbnceNotFoundException
                // for the MBebn Server Delegbte :)
//              try {
//                  myMBebnServer.bddNotificbtionListener(
//                              MBebnServerDelegbte.DELEGATE_NAME,
//                              this,
//                              myUnregNtfFilter,
//                              null);
//              } cbtch (InstbnceNotFoundException exc) {
//                  throw new
//                     RelbtionServiceNotRegisteredException(exc.getMessbge());
//              }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "updbteUnregistrbtionListener");
        return;
    }

    // Adds b relbtion (being either b RelbtionSupport object or bn MBebn
    // referenced using its ObjectNbme) in the Relbtion Service.
    // Will send b notificbtion RelbtionNotificbtion with type:
    // - RelbtionNotificbtion.RELATION_BASIC_CREATION for internbl relbtion
    //   crebtion
    // - RelbtionNotificbtion.RELATION_MBEAN_CREATION for bn MBebn being bdded
    //   bs b relbtion.
    //
    // -pbrbm relbtionBbseFlbg  flbg true if the relbtion is b RelbtionSupport
    //  object, fblse if it is bn MBebn
    // -pbrbm relbtionObj  RelbtionSupport object (if relbtion is internbl)
    // -pbrbm relbtionObjNbme  ObjectNbme of the MBebn to be bdded bs b relbtion
    //  (only for the relbtion MBebn)
    // -pbrbm relbtionId  relbtion identifier, to uniquely identify the relbtion
    //  inside the Relbtion Service
    // -pbrbm relbtionTypeNbme  nbme of the relbtion type (hbs to be crebted
    //  in the Relbtion Service)
    // -pbrbm roleList  role list to initiblize roles of the relbtion
    //  (cbn be null)
    //
    // -exception IllegblArgumentException  if null pbrbmbter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RoleNotFoundException  if b vblue is provided for b role
    //  thbt does not exist in the relbtion type
    // -exception InvblidRelbtionIdException  if relbtion id blrebdy used
    // -exception RelbtionTypeNotFoundException  if relbtion type not known in
    //  Relbtion Service
    // -exception InvblidRoleVblueException if:
    //  - the sbme role nbme is used for two different roles
    //  - the number of referenced MBebns in given vblue is less thbn
    //    expected minimum degree
    //  - the number of referenced MBebns in provided vblue exceeds expected
    //    mbximum degree
    //  - one referenced MBebn in the vblue is not bn Object of the MBebn
    //    clbss expected for thbt role
    //  - bn MBebn provided for thbt role does not exist
    privbte void bddRelbtionInt(boolebn relbtionBbseFlbg,
                                RelbtionSupport relbtionObj,
                                ObjectNbme relbtionObjNbme,
                                String relbtionId,
                                String relbtionTypeNbme,
                                RoleList roleList)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RoleNotFoundException,
               InvblidRelbtionIdException,
               RelbtionTypeNotFoundException,
               InvblidRoleVblueException {

        if (relbtionId == null ||
            relbtionTypeNbme == null ||
            (relbtionBbseFlbg &&
             (relbtionObj == null ||
              relbtionObjNbme != null)) ||
            (!relbtionBbseFlbg &&
             (relbtionObjNbme == null ||
              relbtionObj != null))) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "bddRelbtionInt", new Object[] {relbtionBbseFlbg, relbtionObj,
                relbtionObjNbme, relbtionId, relbtionTypeNbme, roleList});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Checks if there is blrebdy b relbtion with given id
        try {
            // Cbn throw b RelbtionNotFoundException (in fbct should :)
            Object rel = getRelbtion(relbtionId);

            if (rel != null) {
                // There is blrebdy b relbtion with thbt id
                String excMsg = "There is blrebdy b relbtion with id ";
                StringBuilder excMsgStrB = new StringBuilder(excMsg);
                excMsgStrB.bppend(relbtionId);
                throw new InvblidRelbtionIdException(excMsgStrB.toString());
            }
        } cbtch (RelbtionNotFoundException exc) {
            // OK : The Relbtion could not be found.
        }

        // Retrieves the relbtion type
        // Cbn throw RelbtionTypeNotFoundException
        RelbtionType relType = getRelbtionType(relbtionTypeNbme);

        // Checks thbt ebch provided role conforms to its role info provided in
        // the relbtion type
        // First retrieves b locbl list of the role infos of the relbtion type
        // to see which roles hbve not been initiblized
        // Note: no need to test if list not null before cloning, not bllowed
        //       to hbve bn empty relbtion type.
        List<RoleInfo> roleInfoList = new ArrbyList<RoleInfo>(relType.getRoleInfos());

        if (roleList != null) {

            for (Role currRole : roleList.bsList()) {
                String currRoleNbme = currRole.getRoleNbme();
                List<ObjectNbme> currRoleVblue = currRole.getRoleVblue();
                // Retrieves corresponding role info
                // Cbn throw b RoleInfoNotFoundException to be converted into b
                // RoleNotFoundException
                RoleInfo roleInfo;
                try {
                    roleInfo = relType.getRoleInfo(currRoleNbme);
                } cbtch (RoleInfoNotFoundException exc) {
                    throw new RoleNotFoundException(exc.getMessbge());
                }

                // Checks thbt role conforms to role info,
                Integer stbtus = checkRoleInt(2,
                                              currRoleNbme,
                                              currRoleVblue,
                                              roleInfo,
                                              fblse);
                int pbType = stbtus.intVblue();
                if (pbType != 0) {
                    // A problem hbs occurred: throws bppropribte exception
                    // here InvblidRoleVblueException
                    throwRoleProblemException(pbType, currRoleNbme);
                }

                // Removes role info for thbt list from list of role infos for
                // roles to be defbulted
                int roleInfoIdx = roleInfoList.indexOf(roleInfo);
                // Note: no need to check if != -1, MUST be there :)
                roleInfoList.remove(roleInfoIdx);
            }
        }

        // Initiblizes roles not initiblized by roleList
        // Cbn throw InvblidRoleVblueException
        initiblizeMissingRoles(relbtionBbseFlbg,
                               relbtionObj,
                               relbtionObjNbme,
                               relbtionId,
                               relbtionTypeNbme,
                               roleInfoList);

        // Crebtion of relbtion successfull!!!!

        // Updbtes internbl mbps
        // Relbtion id to object mbp
        synchronized(myRelId2ObjMbp) {
            if (relbtionBbseFlbg) {
                // Note: do not clone relbtion object, crebted by us :)
                myRelId2ObjMbp.put(relbtionId, relbtionObj);
            } else {
                myRelId2ObjMbp.put(relbtionId, relbtionObjNbme);
            }
        }

        // Relbtion id to relbtion type nbme mbp
        synchronized(myRelId2RelTypeMbp) {
            myRelId2RelTypeMbp.put(relbtionId,
                                   relbtionTypeNbme);
        }

        // Relbtion type to relbtion id mbp
        synchronized(myRelType2RelIdsMbp) {
            List<String> relIdList =
                myRelType2RelIdsMbp.get(relbtionTypeNbme);
            boolebn firstRelFlbg = fblse;
            if (relIdList == null) {
                firstRelFlbg = true;
                relIdList = new ArrbyList<String>();
            }
            relIdList.bdd(relbtionId);
            if (firstRelFlbg) {
                myRelType2RelIdsMbp.put(relbtionTypeNbme, relIdList);
            }
        }

        // Referenced MBebn to relbtion id mbp
        // Only role list pbrbmeter used, bs defbult initiblizbtion of roles
        // done butombticblly in initiblizeMissingRoles() sets ebch
        // uninitiblized role to bn empty vblue.
        for (Role currRole : roleList.bsList()) {
            // Crebtes b dummy empty ArrbyList of ObjectNbmes to be the old
            // role vblue :)
            List<ObjectNbme> dummyList = new ArrbyList<ObjectNbme>();
            // Will not throw b RelbtionNotFoundException (bs the RelId2Obj mbp
            // hbs been updbted bbove) so cbtch it :)
            try {
                updbteRoleMbp(relbtionId, currRole, dummyList);

            } cbtch (RelbtionNotFoundException exc) {
                // OK : The Relbtion could not be found.
            }
        }

        // Sends b notificbtion for relbtion crebtion
        // Will not throw RelbtionNotFoundException so cbtch it :)
        try {
            sendRelbtionCrebtionNotificbtion(relbtionId);

        } cbtch (RelbtionNotFoundException exc) {
            // OK : The Relbtion could not be found.
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "bddRelbtionInt");
        return;
    }

    // Checks thbt given role conforms to given role info.
    //
    // -pbrbm chkType  type of check:
    //  - 1: rebd, just check rebd bccess
    //  - 2: write, check vblue bnd write bccess if writeChkFlbg
    // -pbrbm roleNbme  role nbme
    // -pbrbm roleVblue  role vblue
    // -pbrbm roleInfo  corresponding role info
    // -pbrbm writeChkFlbg  boolebn to specify b current write bccess bnd
    //  to check it
    //
    // -return Integer with vblue:
    //  - 0: ok
    //  - RoleStbtus.NO_ROLE_WITH_NAME
    //  - RoleStbtus.ROLE_NOT_READABLE
    //  - RoleStbtus.ROLE_NOT_WRITABLE
    //  - RoleStbtus.LESS_THAN_MIN_ROLE_DEGREE
    //  - RoleStbtus.MORE_THAN_MAX_ROLE_DEGREE
    //  - RoleStbtus.REF_MBEAN_OF_INCORRECT_CLASS
    //  - RoleStbtus.REF_MBEAN_NOT_REGISTERED
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    privbte Integer checkRoleInt(int chkType,
                                 String roleNbme,
                                 List<ObjectNbme> roleVblue,
                                 RoleInfo roleInfo,
                                 boolebn writeChkFlbg)
        throws IllegblArgumentException {

        if (roleNbme == null ||
            roleInfo == null ||
            (chkType == 2 && roleVblue == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "checkRoleInt", new Object[] {chkType, roleNbme,
                roleVblue, roleInfo, writeChkFlbg});

        // Compbres nbmes
        String expNbme = roleInfo.getNbme();
        if (!(roleNbme.equbls(expNbme))) {
            RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                    "checkRoleInt");
            return Integer.vblueOf(RoleStbtus.NO_ROLE_WITH_NAME);
        }

        // Checks rebd bccess if required
        if (chkType == 1) {
            boolebn isRebdbble = roleInfo.isRebdbble();
            if (!isRebdbble) {
                RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                        "checkRoleInt");
                return Integer.vblueOf(RoleStbtus.ROLE_NOT_READABLE);
            } else {
                // End of check :)
                RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                        "checkRoleInt");
                return 0;
            }
        }

        // Checks write bccess if required
        if (writeChkFlbg) {
            boolebn isWritbble = roleInfo.isWritbble();
            if (!isWritbble) {
                RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                        "checkRoleInt");
                return RoleStbtus.ROLE_NOT_WRITABLE;
            }
        }

        int refNbr = roleVblue.size();

        // Checks minimum cbrdinblity
        boolebn chkMinFlbg = roleInfo.checkMinDegree(refNbr);
        if (!chkMinFlbg) {
            RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                    "checkRoleInt");
            return RoleStbtus.LESS_THAN_MIN_ROLE_DEGREE;
        }

        // Checks mbximum cbrdinblity
        boolebn chkMbxFlbg = roleInfo.checkMbxDegree(refNbr);
        if (!chkMbxFlbg) {
            RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                    "checkRoleInt");
            return RoleStbtus.MORE_THAN_MAX_ROLE_DEGREE;
        }

        // Verifies thbt ebch referenced MBebn is registered in the MBebn
        // Server bnd thbt it is bn instbnce of the clbss specified in the
        // role info, or of b subclbss of it
        // Note thbt here bgbin this is under the bssumption thbt
        // referenced MBebns, relbtion MBebns bnd the Relbtion Service bre
        // registered in the sbme MBebn Server.
        String expClbssNbme = roleInfo.getRefMBebnClbssNbme();

        for (ObjectNbme currObjNbme : roleVblue) {

            // Checks it is registered
            if (currObjNbme == null) {
                RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                        "checkRoleInt");
                return RoleStbtus.REF_MBEAN_NOT_REGISTERED;
            }

            // Checks if it is of the correct clbss
            // Cbn throw bn InstbnceNotFoundException, if MBebn not registered
            try {
                boolebn clbssSts = myMBebnServer.isInstbnceOf(currObjNbme,
                                                              expClbssNbme);
                if (!clbssSts) {
                    RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                            "checkRoleInt");
                    return RoleStbtus.REF_MBEAN_OF_INCORRECT_CLASS;
                }

            } cbtch (InstbnceNotFoundException exc) {
                RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                        "checkRoleInt");
                return RoleStbtus.REF_MBEAN_NOT_REGISTERED;
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "checkRoleInt");
        return 0;
    }


    // Initiblizes roles bssocibted to given role infos to defbult vblue (empty
    // ArrbyList of ObjectNbmes) in given relbtion.
    // It will succeed for every role except if the role info hbs b minimum
    // cbrdinblity grebter thbn 0. In thbt cbse, bn InvblidRoleVblueException
    // will be rbised.
    //
    // -pbrbm relbtionBbseFlbg  flbg true if the relbtion is b RelbtionSupport
    //  object, fblse if it is bn MBebn
    // -pbrbm relbtionObj  RelbtionSupport object (if relbtion is internbl)
    // -pbrbm relbtionObjNbme  ObjectNbme of the MBebn to be bdded bs b relbtion
    //  (only for the relbtion MBebn)
    // -pbrbm relbtionId  relbtion id
    // -pbrbm relbtionTypeNbme  nbme of the relbtion type (hbs to be crebted
    //  in the Relbtion Service)
    // -pbrbm roleInfoList  list of role infos for roles to be defbulted
    //
    // -exception IllegblArgumentException  if null pbrbmbter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception InvblidRoleVblueException  if role must hbve b non-empty
    //  vblue

    // Revisit [cebro] Hbndle CIM qublifiers bs REQUIRED to detect roles which
    //    should hbve been initiblized by the user
    privbte void initiblizeMissingRoles(boolebn relbtionBbseFlbg,
                                        RelbtionSupport relbtionObj,
                                        ObjectNbme relbtionObjNbme,
                                        String relbtionId,
                                        String relbtionTypeNbme,
                                        List<RoleInfo> roleInfoList)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               InvblidRoleVblueException {

        if ((relbtionBbseFlbg &&
             (relbtionObj == null ||
              relbtionObjNbme != null)) ||
            (!relbtionBbseFlbg &&
             (relbtionObjNbme == null ||
              relbtionObj != null)) ||
            relbtionId == null ||
            relbtionTypeNbme == null ||
            roleInfoList == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "initiblizeMissingRoles", new Object[] {relbtionBbseFlbg,
                relbtionObj, relbtionObjNbme, relbtionId, relbtionTypeNbme,
                roleInfoList});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // For ebch role info (corresponding to b role not initiblized by the
        // role list provided by the user), try to set in the relbtion b role
        // with bn empty list of ObjectNbmes.
        // A check is performed to verify thbt the role cbn be set to bn
        // empty vblue, bccording to its minimum cbrdinblity
        for (RoleInfo currRoleInfo : roleInfoList) {

            String roleNbme = currRoleInfo.getNbme();

            // Crebtes bn empty vblue
            List<ObjectNbme> emptyVblue = new ArrbyList<ObjectNbme>();
            // Crebtes b role
            Role role = new Role(roleNbme, emptyVblue);

            if (relbtionBbseFlbg) {

                // Internbl relbtion
                // Cbn throw InvblidRoleVblueException
                //
                // Will not throw RoleNotFoundException (role to be
                // initiblized), or RelbtionNotFoundException, or
                // RelbtionTypeNotFoundException
                try {
                    relbtionObj.setRoleInt(role, true, this, fblse);

                } cbtch (RoleNotFoundException exc1) {
                    throw new RuntimeException(exc1.getMessbge());
                } cbtch (RelbtionNotFoundException exc2) {
                    throw new RuntimeException(exc2.getMessbge());
                } cbtch (RelbtionTypeNotFoundException exc3) {
                    throw new RuntimeException(exc3.getMessbge());
                }

            } else {

                // Relbtion is bn MBebn
                // Use stbndbrd setRole()
                Object[] pbrbms = new Object[1];
                pbrbms[0] = role;
                String[] signbture = new String[1];
                signbture[0] = "jbvbx.mbnbgement.relbtion.Role";
                // Cbn throw MBebnException wrbpping
                // InvblidRoleVblueException. Returns the tbrget exception to
                // be homogeneous.
                //
                // Will not throw MBebnException (wrbpping
                // RoleNotFoundException or MBebnException) or
                // InstbnceNotFoundException, or ReflectionException
                //
                // Agbin here the bssumption is thbt the Relbtion Service bnd
                // the relbtion MBebns bre registered in the sbme MBebn Server.
                try {
                    myMBebnServer.setAttribute(relbtionObjNbme,
                                               new Attribute("Role", role));

                } cbtch (InstbnceNotFoundException exc1) {
                    throw new RuntimeException(exc1.getMessbge());
                } cbtch (ReflectionException exc3) {
                    throw new RuntimeException(exc3.getMessbge());
                } cbtch (MBebnException exc2) {
                    Exception wrbppedExc = exc2.getTbrgetException();
                    if (wrbppedExc instbnceof InvblidRoleVblueException) {
                        throw ((InvblidRoleVblueException)wrbppedExc);
                    } else {
                        throw new RuntimeException(wrbppedExc.getMessbge());
                    }
                } cbtch (AttributeNotFoundException exc4) {
                  throw new RuntimeException(exc4.getMessbge());
                } cbtch (InvblidAttributeVblueException exc5) {
                  throw new RuntimeException(exc5.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "initiblizeMissingRoles");
        return;
    }

    // Throws bn exception corresponding to b given problem type
    //
    // -pbrbm pbType  possible problem, defined in RoleUnresolved
    // -pbrbm roleNbme  role nbme
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RoleNotFoundException  for problems:
    //  - NO_ROLE_WITH_NAME
    //  - ROLE_NOT_READABLE
    //  - ROLE_NOT_WRITABLE
    // -exception InvblidRoleVblueException  for problems:
    //  - LESS_THAN_MIN_ROLE_DEGREE
    //  - MORE_THAN_MAX_ROLE_DEGREE
    //  - REF_MBEAN_OF_INCORRECT_CLASS
    //  - REF_MBEAN_NOT_REGISTERED
    stbtic void throwRoleProblemException(int pbType,
                                          String roleNbme)
        throws IllegblArgumentException,
               RoleNotFoundException,
               InvblidRoleVblueException {

        if (roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        // Exception type: 1 = RoleNotFoundException
        //                 2 = InvblidRoleVblueException
        int excType = 0;

        String excMsgPbrt = null;

        switch (pbType) {
        cbse RoleStbtus.NO_ROLE_WITH_NAME:
            excMsgPbrt = " does not exist in relbtion.";
            excType = 1;
            brebk;
        cbse RoleStbtus.ROLE_NOT_READABLE:
            excMsgPbrt = " is not rebdbble.";
            excType = 1;
            brebk;
        cbse RoleStbtus.ROLE_NOT_WRITABLE:
            excMsgPbrt = " is not writbble.";
            excType = 1;
            brebk;
        cbse RoleStbtus.LESS_THAN_MIN_ROLE_DEGREE:
            excMsgPbrt = " hbs b number of MBebn references less thbn the expected minimum degree.";
            excType = 2;
            brebk;
        cbse RoleStbtus.MORE_THAN_MAX_ROLE_DEGREE:
            excMsgPbrt = " hbs b number of MBebn references grebter thbn the expected mbximum degree.";
            excType = 2;
            brebk;
        cbse RoleStbtus.REF_MBEAN_OF_INCORRECT_CLASS:
            excMsgPbrt = " hbs bn MBebn reference to bn MBebn not of the expected clbss of references for thbt role.";
            excType = 2;
            brebk;
        cbse RoleStbtus.REF_MBEAN_NOT_REGISTERED:
            excMsgPbrt = " hbs b reference to null or to bn MBebn not registered.";
            excType = 2;
            brebk;
        }
        // No defbult bs we must hbve been in one of those cbses

        StringBuilder excMsgStrB = new StringBuilder(roleNbme);
        excMsgStrB.bppend(excMsgPbrt);
        String excMsg = excMsgStrB.toString();
        if (excType == 1) {
            throw new RoleNotFoundException(excMsg);

        } else if (excType == 2) {
            throw new InvblidRoleVblueException(excMsg);
        }
    }

    // Sends b notificbtion of given type, with given pbrbmeters
    //
    // -pbrbm intNtfType  integer to represent notificbtion type:
    //  - 1 : crebte
    //  - 2 : updbte
    //  - 3 : delete
    // -pbrbm messbge  humbn-rebdbble messbge
    // -pbrbm relbtionId  relbtion id of the crebted/updbted/deleted relbtion
    // -pbrbm unregMBebnList  list of ObjectNbmes of referenced MBebns
    //  expected to be unregistered due to relbtion removbl (only for removbl,
    //  due to CIM qublifiers, cbn be null)
    // -pbrbm roleNbme  role nbme
    // -pbrbm roleNewVblue  role new vblue (ArrbyList of ObjectNbmes)
    // -pbrbm oldVblue  old role vblue (ArrbyList of ObjectNbmes)
    //
    // -exception IllegblArgument  if null pbrbmeter
    // -exception RelbtionNotFoundException  if no relbtion for given id
    privbte void sendNotificbtionInt(int intNtfType,
                                     String messbge,
                                     String relbtionId,
                                     List<ObjectNbme> unregMBebnList,
                                     String roleNbme,
                                     List<ObjectNbme> roleNewVblue,
                                     List<ObjectNbme> oldVblue)
        throws IllegblArgumentException,
               RelbtionNotFoundException {

        if (messbge == null ||
            relbtionId == null ||
            (intNtfType != 3 && unregMBebnList != null) ||
            (intNtfType == 2 &&
             (roleNbme == null ||
              roleNewVblue == null ||
              oldVblue == null))) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "sendNotificbtionInt", new Object[] {intNtfType, messbge,
                relbtionId, unregMBebnList, roleNbme, roleNewVblue, oldVblue});

        // Relbtion type nbme
        // Note: do not use getRelbtionTypeNbme() bs if it is b relbtion MBebn
        //       it is blrebdy unregistered.
        String relTypeNbme;
        synchronized(myRelId2RelTypeMbp) {
            relTypeNbme = (myRelId2RelTypeMbp.get(relbtionId));
        }

        // ObjectNbme (for b relbtion MBebn)
        // Cbn blso throw b RelbtionNotFoundException, but detected bbove
        ObjectNbme relObjNbme = isRelbtionMBebn(relbtionId);

        String ntfType = null;
        if (relObjNbme != null) {
            switch (intNtfType) {
            cbse 1:
                ntfType = RelbtionNotificbtion.RELATION_MBEAN_CREATION;
                brebk;
            cbse 2:
                ntfType = RelbtionNotificbtion.RELATION_MBEAN_UPDATE;
                brebk;
            cbse 3:
                ntfType = RelbtionNotificbtion.RELATION_MBEAN_REMOVAL;
                brebk;
            }
        } else {
            switch (intNtfType) {
            cbse 1:
                ntfType = RelbtionNotificbtion.RELATION_BASIC_CREATION;
                brebk;
            cbse 2:
                ntfType = RelbtionNotificbtion.RELATION_BASIC_UPDATE;
                brebk;
            cbse 3:
                ntfType = RelbtionNotificbtion.RELATION_BASIC_REMOVAL;
                brebk;
            }
        }

        // Sequence number
        Long seqNo = btomicSeqNo.incrementAndGet();

        // Timestbmp
        Dbte currDbte = new Dbte();
        long timeStbmp = currDbte.getTime();

        RelbtionNotificbtion ntf = null;

        if (ntfType.equbls(RelbtionNotificbtion.RELATION_BASIC_CREATION) ||
            ntfType.equbls(RelbtionNotificbtion.RELATION_MBEAN_CREATION) ||
            ntfType.equbls(RelbtionNotificbtion.RELATION_BASIC_REMOVAL) ||
            ntfType.equbls(RelbtionNotificbtion.RELATION_MBEAN_REMOVAL))

            // Crebtion or removbl
            ntf = new RelbtionNotificbtion(ntfType,
                                           this,
                                           seqNo.longVblue(),
                                           timeStbmp,
                                           messbge,
                                           relbtionId,
                                           relTypeNbme,
                                           relObjNbme,
                                           unregMBebnList);

        else if (ntfType.equbls(RelbtionNotificbtion.RELATION_BASIC_UPDATE)
                 ||
                 ntfType.equbls(RelbtionNotificbtion.RELATION_MBEAN_UPDATE))
            {
                // Updbte
                ntf = new RelbtionNotificbtion(ntfType,
                                               this,
                                               seqNo.longVblue(),
                                               timeStbmp,
                                               messbge,
                                               relbtionId,
                                               relTypeNbme,
                                               relObjNbme,
                                               roleNbme,
                                               roleNewVblue,
                                               oldVblue);
            }

        sendNotificbtion(ntf);

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "sendNotificbtionInt");
        return;
    }

    // Checks, for the unregistrbtion of bn MBebn referenced in the roles given
    // in pbrbmeter, if the relbtion hbs to be removed or not, regbrding
    // expected minimum role cbrdinblity bnd current number of
    // references in ebch role bfter removbl of the current one.
    // If the relbtion is kept, cblls hbndleMBebnUnregistrbtion() cbllbbck of
    // the relbtion to updbte it.
    //
    // -pbrbm relbtionId  relbtion id
    // -pbrbm objectNbme  ObjectNbme of the unregistered MBebn
    // -pbrbm roleNbmeList  list of nbmes of roles where the unregistered
    //  MBebn is referenced.
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RelbtionNotFoundException  if unknown relbtion id
    // -exception RoleNotFoundException  if one role given bs pbrbmeter does
    //  not exist in the relbtion
    privbte void hbndleReferenceUnregistrbtion(String relbtionId,
                                               ObjectNbme objectNbme,
                                               List<String> roleNbmeList)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionNotFoundException,
               RoleNotFoundException {

        if (relbtionId == null ||
            roleNbmeList == null ||
            objectNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionService.clbss.getNbme(),
                "hbndleReferenceUnregistrbtion",
                new Object[] {relbtionId, objectNbme, roleNbmeList});

        // Cbn throw RelbtionServiceNotRegisteredException
        isActive();

        // Retrieves the relbtion type nbme of the relbtion
        // Cbn throw RelbtionNotFoundException
        String currRelTypeNbme = getRelbtionTypeNbme(relbtionId);

        // Retrieves the relbtion
        // Cbn throw RelbtionNotFoundException, but blrebdy detected bbove
        Object relObj = getRelbtion(relbtionId);

        // Flbg to specify if the relbtion hbs to be deleted
        boolebn deleteRelFlbg = fblse;

        for (String currRoleNbme : roleNbmeList) {

            if (deleteRelFlbg) {
                brebk;
            }

            // Retrieves number of MBebns currently referenced in role
            // BEWARE! Do not use getRole() bs role mby be not rebdbble
            //
            // Cbn throw RelbtionNotFoundException (but blrebdy checked),
            // RoleNotFoundException
            int currRoleRefNbr =
                (getRoleCbrdinblity(relbtionId, currRoleNbme)).intVblue();

            // Retrieves new number of element in role
            int currRoleNewRefNbr = currRoleRefNbr - 1;

            // Retrieves role info for thbt role
            //
            // Shbll not throw RelbtionTypeNotFoundException or
            // RoleInfoNotFoundException
            RoleInfo currRoleInfo;
            try {
                currRoleInfo = getRoleInfo(currRelTypeNbme,
                                           currRoleNbme);
            } cbtch (RelbtionTypeNotFoundException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (RoleInfoNotFoundException exc2) {
                throw new RuntimeException(exc2.getMessbge());
            }

            // Checks with expected minimum number of elements
            boolebn chkMinFlbg = currRoleInfo.checkMinDegree(currRoleNewRefNbr);

            if (!chkMinFlbg) {
                // The relbtion hbs to be deleted
                deleteRelFlbg = true;
            }
        }

        if (deleteRelFlbg) {
            // Removes the relbtion
            removeRelbtion(relbtionId);

        } else {

            // Updbtes ebch role in the relbtion using
            // hbndleMBebnUnregistrbtion() cbllbbck
            //
            // BEWARE: this roleNbmeList list MUST BE A COPY of b role nbme
            //         list for b referenced MBebn in b relbtion, NOT b
            //         reference to bn originbl one pbrt of the
            //         myRefedMBebnObjNbme2RelIdsMbp!!!! Becbuse ebch role
            //         which nbme is in thbt list will be updbted (potentiblly
            //         using setRole(). So the Relbtion Service will updbte the
            //         myRefedMBebnObjNbme2RelIdsMbp to refelect the new role
            //         vblue!
            for (String currRoleNbme : roleNbmeList) {

                if (relObj instbnceof RelbtionSupport) {
                    // Internbl relbtion
                    // Cbn throw RoleNotFoundException (but blrebdy checked)
                    //
                    // Shbll not throw
                    // RelbtionTypeNotFoundException,
                    // InvblidRoleVblueException (vblue wbs correct, removing
                    // one reference shbll not invblidbte it, else detected
                    // bbove)
                    try {
                        ((RelbtionSupport)relObj).hbndleMBebnUnregistrbtionInt(
                                                  objectNbme,
                                                  currRoleNbme,
                                                  true,
                                                  this);
                    } cbtch (RelbtionTypeNotFoundException exc3) {
                        throw new RuntimeException(exc3.getMessbge());
                    } cbtch (InvblidRoleVblueException exc4) {
                        throw new RuntimeException(exc4.getMessbge());
                    }

                } else {
                    // Relbtion MBebn
                    Object[] pbrbms = new Object[2];
                    pbrbms[0] = objectNbme;
                    pbrbms[1] = currRoleNbme;
                    String[] signbture = new String[2];
                    signbture[0] = "jbvbx.mbnbgement.ObjectNbme";
                    signbture[1] = "jbvb.lbng.String";
                    // Shbll not throw InstbnceNotFoundException, or
                    // MBebnException (wrbpping RoleNotFoundException or
                    // MBebnException or InvblidRoleVblueException) or
                    // ReflectionException
                    try {
                        myMBebnServer.invoke(((ObjectNbme)relObj),
                                             "hbndleMBebnUnregistrbtion",
                                             pbrbms,
                                             signbture);
                    } cbtch (InstbnceNotFoundException exc1) {
                        throw new RuntimeException(exc1.getMessbge());
                    } cbtch (ReflectionException exc3) {
                        throw new RuntimeException(exc3.getMessbge());
                    } cbtch (MBebnException exc2) {
                        Exception wrbppedExc = exc2.getTbrgetException();
                        throw new RuntimeException(wrbppedExc.getMessbge());
                    }

                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionService.clbss.getNbme(),
                "hbndleReferenceUnregistrbtion");
        return;
    }
}
