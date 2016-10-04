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



import jbvb.util.ArrbyList;

import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.List;

import jbvb.util.concurrent.btomic.AtomicBoolebn;
import stbtic com.sun.jmx.defbults.JmxProperties.RELATION_LOGGER;
import stbtic com.sun.jmx.mbebnserver.Util.cbst;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;

/**
 * A RelbtionSupport object is used internblly by the Relbtion Service to
 * represent simple relbtions (only roles, no properties or methods), with bn
 * unlimited number of roles, of bny relbtion type. As internbl representbtion,
 * it is not exposed to the user.
 * <P>RelbtionSupport clbss conforms to the design pbtterns of stbndbrd MBebn. So
 * the user cbn decide to instbntibte b RelbtionSupport object himself bs
 * b MBebn (bs it follows the MBebn design pbtterns), to register it in the
 * MBebn Server, bnd then to bdd it in the Relbtion Service.
 * <P>The user cbn blso, when crebting his own MBebn relbtion clbss, hbve it
 * extending RelbtionSupport, to retrieve the implementbtions of required
 * interfbces (see below).
 * <P>It is blso possible to hbve in b user relbtion MBebn clbss b member
 * being b RelbtionSupport object, bnd to implement the required interfbces by
 * delegbting bll to this member.
 * <P> RelbtionSupport implements the Relbtion interfbce (to be hbndled by the
 * Relbtion Service).
 * <P>It implements blso the MBebnRegistrbtion interfbce to be bble to retrieve
 * the MBebn Server where it is registered (if registered bs b MBebn) to bccess
 * to its Relbtion Service.
 *
 * @since 1.5
 */
public clbss RelbtionSupport
    implements RelbtionSupportMBebn, MBebnRegistrbtion {

    //
    // Privbte members
    //

    // Relbtion identifier (expected to be unique in the Relbtion Service where
    // the RelbtionSupport object will be bdded)
    privbte String myRelId = null;

    // ObjectNbme of the Relbtion Service where the relbtion will be bdded
    // REQUIRED if the RelbtionSupport is crebted by the user to be registered bs
    // b MBebn, bs it will hbve to bccess the Relbtion Service vib the MBebn
    // Server to perform the check regbrding the relbtion type.
    // Is null if current object is directly crebted by the Relbtion Service,
    // bs the object will directly bccess it.
    privbte ObjectNbme myRelServiceNbme = null;

    // Reference to the MBebn Server where the Relbtion Service is
    // registered
    // REQUIRED if the RelbtionSupport is crebted by the user to be registered bs
    // b MBebn, bs it will hbve to bccess the Relbtion Service vib the MBebn
    // Server to perform the check regbrding the relbtion type.
    // If the Relbtionbbse object is crebted by the Relbtion Service (use of
    // crebteRelbtion() method), this is null bs not needed, direct bccess to
    // the Relbtion Service.
    // If the Relbtionbbse object is crebted by the user bnd registered bs b
    // MBebn, this is set by the preRegister() method below.
    privbte MBebnServer myRelServiceMBebnServer = null;

    // Relbtion type nbme (must be known in the Relbtion Service where the
    // relbtion will be bdded)
    privbte String myRelTypeNbme = null;

    // Role mbp, mbpping <role-nbme> -> <Role>
    // Initiblized by role list in the constructor, then updbted:
    // - if the relbtion is b MBebn, vib setRole() bnd setRoles() methods, or
    //   vib Relbtion Service setRole() bnd setRoles() methods
    // - if the relbtion is internbl to the Relbtion Service, vib
    //   setRoleInt() bnd setRolesInt() methods.
    privbte finbl Mbp<String,Role> myRoleNbme2VblueMbp = new HbshMbp<String,Role>();

    // Flbg to indicbte if the object hbs been bdded in the Relbtion Service
    privbte finbl AtomicBoolebn myInRelServFlg = new AtomicBoolebn();

    //
    // Constructors
    //

    /**
     * Crebtes b {@code RelbtionSupport} object.
     * <P>This constructor hbs to be used when the RelbtionSupport object will
     * be registered bs b MBebn by the user, or when crebting b user relbtion
     * MBebn whose clbss extends RelbtionSupport.
     * <P>Nothing is done bt the Relbtion Service level, i.e.
     * the {@code RelbtionSupport} object is not bdded to the
     * {@code RelbtionService} bnd no checks bre performed to
     * see if the provided vblues bre correct.
     * The object is blwbys crebted, EXCEPT if:
     * <P>- bny of the required pbrbmeters is {@code null}.
     * <P>- the sbme nbme is used for two roles.
     * <P>To be hbndled bs b relbtion, the {@code RelbtionSupport} object hbs
     * to be bdded to the Relbtion Service using the Relbtion Service method
     * bddRelbtion().
     *
     * @pbrbm relbtionId  relbtion identifier, to identify the relbtion in the
     * Relbtion Service.
     * <P>Expected to be unique in the given Relbtion Service.
     * @pbrbm relbtionServiceNbme  ObjectNbme of the Relbtion Service where
     * the relbtion will be registered.
     * <P>This pbrbmeter is required bs it is the Relbtion Service thbt is
     * bwbre of the definition of the relbtion type of the given relbtion,
     * so thbt will be bble to check updbte operbtions (set).
     * @pbrbm relbtionTypeNbme  Nbme of relbtion type.
     * <P>Expected to hbve been crebted in the given Relbtion Service.
     * @pbrbm list  list of roles (Role objects) to initiblize the
     * relbtion. Cbn be {@code null}.
     * <P>Expected to conform to relbtion info in bssocibted relbtion type.
     *
     * @exception InvblidRoleVblueException  if the sbme nbme is used for two
     * roles.
     * @exception IllegblArgumentException  if bny of the required pbrbmeters
     * (relbtion id, relbtion service ObjectNbme, or relbtion type nbme) is
     * {@code null}.
     */
    public RelbtionSupport(String relbtionId,
                        ObjectNbme relbtionServiceNbme,
                        String relbtionTypeNbme,
                        RoleList list)
        throws InvblidRoleVblueException,
               IllegblArgumentException {

        super();

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "RelbtionSupport");

        // Cbn throw InvblidRoleVblueException bnd IllegblArgumentException
        initMembers(relbtionId,
                    relbtionServiceNbme,
                    null,
                    relbtionTypeNbme,
                    list);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "RelbtionSupport");
    }

    /**
     * Crebtes b {@code RelbtionSupport} object.
     * <P>This constructor hbs to be used when the user relbtion MBebn
     * implements the interfbces expected to be supported by b relbtion by
     * delegbting to b RelbtionSupport object.
     * <P>This object needs to know the Relbtion Service expected to hbndle the
     * relbtion. So it hbs to know the MBebn Server where the Relbtion Service
     * is registered.
     * <P>According to b limitbtion, b relbtion MBebn must be registered in the
     * sbme MBebn Server bs the Relbtion Service expected to hbndle it. So the
     * user relbtion MBebn hbs to be crebted bnd registered, bnd then the
     * wrbpped RelbtionSupport object cbn be crebted within the identified MBebn
     * Server.
     * <P>Nothing is done bt the Relbtion Service level, i.e.
     * the {@code RelbtionSupport} object is not bdded to the
     * {@code RelbtionService} bnd no checks bre performed to
     * see if the provided vblues bre correct.
     * The object is blwbys crebted, EXCEPT if:
     * <P>- bny of the required pbrbmeters is {@code null}.
     * <P>- the sbme nbme is used for two roles.
     * <P>To be hbndled bs b relbtion, the {@code RelbtionSupport} object hbs
     * to be bdded to the Relbtion Service using the Relbtion Service method
     * bddRelbtion().
     *
     * @pbrbm relbtionId  relbtion identifier, to identify the relbtion in the
     * Relbtion Service.
     * <P>Expected to be unique in the given Relbtion Service.
     * @pbrbm relbtionServiceNbme  ObjectNbme of the Relbtion Service where
     * the relbtion will be registered.
     * <P>This pbrbmeter is required bs it is the Relbtion Service thbt is
     * bwbre of the definition of the relbtion type of the given relbtion,
     * so thbt will be bble to check updbte operbtions (set).
     * @pbrbm relbtionServiceMBebnServer  MBebn Server where the wrbpping MBebn
     * is or will be registered.
     * <P>Expected to be the MBebn Server where the Relbtion Service is or will
     * be registered.
     * @pbrbm relbtionTypeNbme  Nbme of relbtion type.
     * <P>Expected to hbve been crebted in the given Relbtion Service.
     * @pbrbm list  list of roles (Role objects) to initiblize the
     * relbtion. Cbn be {@code null}.
     * <P>Expected to conform to relbtion info in bssocibted relbtion type.
     *
     * @exception InvblidRoleVblueException  if the sbme nbme is used for two
     * roles.
     * @exception IllegblArgumentException  if bny of the required pbrbmeters
     * (relbtion id, relbtion service ObjectNbme, relbtion service MBebnServer,
     * or relbtion type nbme) is {@code null}.
     */
    public RelbtionSupport(String relbtionId,
                        ObjectNbme relbtionServiceNbme,
                        MBebnServer relbtionServiceMBebnServer,
                        String relbtionTypeNbme,
                        RoleList list)
        throws InvblidRoleVblueException,
               IllegblArgumentException {

        super();

        if (relbtionServiceMBebnServer == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "RelbtionSupport");

        // Cbn throw InvblidRoleVblueException bnd
        // IllegblArgumentException
        initMembers(relbtionId,
                    relbtionServiceNbme,
                    relbtionServiceMBebnServer,
                    relbtionTypeNbme,
                    list);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "RelbtionSupport");
    }

    //
    // Relbtion Interfbce
    //

    /**
     * Retrieves role vblue for given role nbme.
     * <P>Checks if the role exists bnd is rebdbble bccording to the relbtion
     * type.
     *
     * @pbrbm roleNbme  nbme of role
     *
     * @return the ArrbyList of ObjectNbme objects being the role vblue
     *
     * @exception IllegblArgumentException  if null role nbme
     * @exception RoleNotFoundException  if:
     * <P>- there is no role with given nbme
     * <P>- the role is not rebdbble.
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     *
     * @see #setRole
     */
    public List<ObjectNbme> getRole(String roleNbme)
        throws IllegblArgumentException,
               RoleNotFoundException,
               RelbtionServiceNotRegisteredException {

        if (roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getRole", roleNbme);

        // Cbn throw RoleNotFoundException bnd
        // RelbtionServiceNotRegisteredException
        List<ObjectNbme> result = cbst(
            getRoleInt(roleNbme, fblse, null, fblse));

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "getRole");
        return result;
    }

    /**
     * Retrieves vblues of roles with given nbmes.
     * <P>Checks for ebch role if it exists bnd is rebdbble bccording to the
     * relbtion type.
     *
     * @pbrbm roleNbmeArrby  brrby of nbmes of roles to be retrieved
     *
     * @return b RoleResult object, including b RoleList (for roles
     * successfully retrieved) bnd b RoleUnresolvedList (for roles not
     * retrieved).
     *
     * @exception IllegblArgumentException  if null role nbme
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     *
     * @see #setRoles
     */
    public RoleResult getRoles(String[] roleNbmeArrby)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException {

        if (roleNbmeArrby == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(), "getRoles");

        // Cbn throw RelbtionServiceNotRegisteredException
        RoleResult result = getRolesInt(roleNbmeArrby, fblse, null);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "getRoles");
        return result;
    }

    /**
     * Returns bll roles present in the relbtion.
     *
     * @return b RoleResult object, including b RoleList (for roles
     * successfully retrieved) bnd b RoleUnresolvedList (for roles not
     * rebdbble).
     *
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     */
    public RoleResult getAllRoles()
        throws RelbtionServiceNotRegisteredException {

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getAllRoles");

        RoleResult result = null;
        try {
            result = getAllRolesInt(fblse, null);
        } cbtch (IllegblArgumentException exc) {
            // OK : Invblid pbrbmeters, ignore...
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "getAllRoles");
        return result;
    }

    /**
     * Returns bll roles in the relbtion without checking rebd mode.
     *
     * @return b RoleList
     */
    public RoleList retrieveAllRoles() {

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "retrieveAllRoles");

        RoleList result;
        synchronized(myRoleNbme2VblueMbp) {
            result =
                new RoleList(new ArrbyList<Role>(myRoleNbme2VblueMbp.vblues()));
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "retrieveAllRoles");
        return result;
    }

    /**
     * Returns the number of MBebns currently referenced in the given role.
     *
     * @pbrbm roleNbme  nbme of role
     *
     * @return the number of currently referenced MBebns in thbt role
     *
     * @exception IllegblArgumentException  if null role nbme
     * @exception RoleNotFoundException  if there is no role with given nbme
     */
    public Integer getRoleCbrdinblity(String roleNbme)
        throws IllegblArgumentException,
               RoleNotFoundException {

        if (roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getRoleCbrdinblity", roleNbme);

        // Try to retrieve the role
        Role role;
        synchronized(myRoleNbme2VblueMbp) {
            // No null Role is bllowed, so direct use of get()
            role = (myRoleNbme2VblueMbp.get(roleNbme));
        }
        if (role == null) {
            int pbType = RoleStbtus.NO_ROLE_WITH_NAME;
            // Will throw b RoleNotFoundException
            //
            // Will not throw InvblidRoleVblueException, so cbtch it for the
            // compiler
            try {
                RelbtionService.throwRoleProblemException(pbType,
                                                          roleNbme);
            } cbtch (InvblidRoleVblueException exc) {
                // OK : Do not throw InvblidRoleVblueException bs
                //      b RoleNotFoundException will be thrown.
            }
        }

        List<ObjectNbme> roleVblue = role.getRoleVblue();

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "getRoleCbrdinblity");
        return roleVblue.size();
    }

    /**
     * Sets the given role.
     * <P>Will check the role bccording to its corresponding role definition
     * provided in relbtion's relbtion type
     * <P>Will send b notificbtion (RelbtionNotificbtion with type
     * RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, depending if the
     * relbtion is b MBebn or not).
     *
     * @pbrbm role  role to be set (nbme bnd new vblue)
     *
     * @exception IllegblArgumentException  if null role
     * @exception RoleNotFoundException  if there is no role with the supplied
     * role's nbme or if the role is not writbble (no test on the write bccess
     * mode performed when initiblizing the role)
     * @exception InvblidRoleVblueException  if vblue provided for
     * role is not vblid, i.e.:
     * <P>- the number of referenced MBebns in given vblue is less thbn
     * expected minimum degree
     * <P>- the number of referenced MBebns in provided vblue exceeds expected
     * mbximum degree
     * <P>- one referenced MBebn in the vblue is not bn Object of the MBebn
     * clbss expected for thbt role
     * <P>- b MBebn provided for thbt role does not exist
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception RelbtionTypeNotFoundException  if the relbtion type hbs not
     * been declbred in the Relbtion Service
     * @exception RelbtionNotFoundException  if the relbtion hbs not been
     * bdded in the Relbtion Service.
     *
     * @see #getRole
     */
    public void setRole(Role role)
        throws IllegblArgumentException,
               RoleNotFoundException,
               RelbtionTypeNotFoundException,
               InvblidRoleVblueException,
               RelbtionServiceNotRegisteredException,
               RelbtionNotFoundException {

        if (role == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "setRole", role);

        // Will return null :)
        Object result = setRoleInt(role, fblse, null, fblse);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "setRole");
        return;
    }

    /**
     * Sets the given roles.
     * <P>Will check the role bccording to its corresponding role definition
     * provided in relbtion's relbtion type
     * <P>Will send one notificbtion (RelbtionNotificbtion with type
     * RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, depending if the
     * relbtion is b MBebn or not) per updbted role.
     *
     * @pbrbm list  list of roles to be set
     *
     * @return b RoleResult object, including b RoleList (for roles
     * successfully set) bnd b RoleUnresolvedList (for roles not
     * set).
     *
     * @exception IllegblArgumentException  if null role list
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception RelbtionTypeNotFoundException  if the relbtion type hbs not
     * been declbred in the Relbtion Service.
     * @exception RelbtionNotFoundException  if the relbtion MBebn hbs not been
     * bdded in the Relbtion Service.
     *
     * @see #getRoles
     */
    public RoleResult setRoles(RoleList list)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionTypeNotFoundException,
               RelbtionNotFoundException {

        if (list == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "setRoles", list);

        RoleResult result = setRolesInt(list, fblse, null);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "setRoles");
        return result;
    }

    /**
     * Cbllbbck used by the Relbtion Service when b MBebn referenced in b role
     * is unregistered.
     * <P>The Relbtion Service will cbll this method to let the relbtion
     * tbke bction to reflect the impbct of such unregistrbtion.
     * <P>BEWARE. the user is not expected to cbll this method.
     * <P>Current implementbtion is to set the role with its current vblue
     * (list of ObjectNbmes of referenced MBebns) without the unregistered
     * one.
     *
     * @pbrbm objectNbme  ObjectNbme of unregistered MBebn
     * @pbrbm roleNbme  nbme of role where the MBebn is referenced
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     * @exception RoleNotFoundException  if role does not exist in the
     * relbtion or is not writbble
     * @exception InvblidRoleVblueException  if role vblue does not conform to
     * the bssocibted role info (this will never hbppen when cblled from the
     * Relbtion Service)
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception RelbtionTypeNotFoundException  if the relbtion type hbs not
     * been declbred in the Relbtion Service.
     * @exception RelbtionNotFoundException  if this method is cblled for b
     * relbtion MBebn not bdded in the Relbtion Service.
     */
    public void hbndleMBebnUnregistrbtion(ObjectNbme objectNbme,
                                          String roleNbme)
        throws IllegblArgumentException,
               RoleNotFoundException,
               InvblidRoleVblueException,
               RelbtionServiceNotRegisteredException,
               RelbtionTypeNotFoundException,
               RelbtionNotFoundException {

        if (objectNbme == null || roleNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "hbndleMBebnUnregistrbtion",
                new Object[]{objectNbme, roleNbme});

        // Cbn throw RoleNotFoundException, InvblidRoleVblueException,
        // or RelbtionTypeNotFoundException
        hbndleMBebnUnregistrbtionInt(objectNbme,
                                     roleNbme,
                                     fblse,
                                     null);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "hbndleMBebnUnregistrbtion");
        return;
    }

    /**
     * Retrieves MBebns referenced in the vbrious roles of the relbtion.
     *
     * @return b HbshMbp mbpping:
     * <P> ObjectNbme {@literbl ->} ArrbyList of String (role nbmes)
     */
    public Mbp<ObjectNbme,List<String>> getReferencedMBebns() {

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getReferencedMBebns");

        Mbp<ObjectNbme,List<String>> refMBebnMbp =
            new HbshMbp<ObjectNbme,List<String>>();

        synchronized(myRoleNbme2VblueMbp) {

            for (Role currRole : myRoleNbme2VblueMbp.vblues()) {

                String currRoleNbme = currRole.getRoleNbme();
                // Retrieves ObjectNbmes of MBebns referenced in current role
                List<ObjectNbme> currRefMBebnList = currRole.getRoleVblue();

                for (ObjectNbme currRoleObjNbme : currRefMBebnList) {

                    // Sees if current MBebn hbs been blrebdy referenced in
                    // roles blrebdy seen
                    List<String> mbebnRoleNbmeList =
                        refMBebnMbp.get(currRoleObjNbme);

                    boolebn newRefFlg = fblse;
                    if (mbebnRoleNbmeList == null) {
                        newRefFlg = true;
                        mbebnRoleNbmeList = new ArrbyList<String>();
                    }
                    mbebnRoleNbmeList.bdd(currRoleNbme);
                    if (newRefFlg) {
                        refMBebnMbp.put(currRoleObjNbme, mbebnRoleNbmeList);
                    }
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "getReferencedMBebns");
        return refMBebnMbp;
    }

    /**
     * Returns nbme of bssocibted relbtion type.
     */
    public String getRelbtionTypeNbme() {
        return myRelTypeNbme;
    }

    /**
     * Returns ObjectNbme of the Relbtion Service hbndling the relbtion.
     *
     * @return the ObjectNbme of the Relbtion Service.
     */
    public ObjectNbme getRelbtionServiceNbme() {
        return myRelServiceNbme;
    }

    /**
     * Returns relbtion identifier (used to uniquely identify the relbtion
     * inside the Relbtion Service).
     *
     * @return the relbtion id.
     */
    public String getRelbtionId() {
        return myRelId;
    }

    //
    // MBebnRegistrbtion interfbce
    //

    // Pre-registrbtion: retrieves the MBebn Server (useful to bccess to the
    // Relbtion Service)
    // This is the wby to retrieve the MBebn Server when the relbtion object is
    // b MBebn crebted by the user outside of the Relbtion Service.
    //
    // No exception thrown.
    public ObjectNbme preRegister(MBebnServer server,
                                  ObjectNbme nbme)
        throws Exception {

        myRelServiceMBebnServer = server;
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
    // Others
    //

    /**
     * Returns bn internbl flbg specifying if the object is still hbndled by
     * the Relbtion Service.
     */
    public Boolebn isInRelbtionService() {
        return myInRelServFlg.get();
    }

    public void setRelbtionServiceMbnbgementFlbg(Boolebn flbg)
        throws IllegblArgumentException {

        if (flbg == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }
        myInRelServFlg.set(flbg);
    }

    //
    // Misc
    //

    // Gets the role with given nbme
    // Checks if the role exists bnd is rebdbble bccording to the relbtion
    // type.
    //
    // This method is cblled in getRole() bbove.
    // It is blso cblled in the Relbtion Service getRole() method.
    // It is blso cblled in getRolesInt() below (used for getRoles() bbove
    // bnd for Relbtion Service getRoles() method).
    //
    // Depending on pbrbmeters reflecting its use (either in the scope of
    // getting b single role or of getting severbl roles), will return:
    // - in cbse of success:
    //   - for single role retrievbl, the ArrbyList of ObjectNbmes being the
    //     role vblue
    //   - for multi-role retrievbl, the Role object itself
    // - in cbse of fbilure (except criticbl exceptions):
    //   - for single role retrievbl, if role does not exist or is not
    //     rebdbble, bn RoleNotFoundException exception is rbised
    //   - for multi-role retrievbl, b RoleUnresolved object
    //
    // -pbrbm roleNbme  nbme of role to be retrieved
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if object
    //  crebted by Relbtion Service.
    // -pbrbm multiRoleFlg  true if getting the role in the scope of b
    //  multiple retrievbl.
    //
    // -return:
    //  - for single role retrievbl (multiRoleFlg fblse):
    //    - ArrbyList of ObjectNbme objects, vblue of role with given nbme, if
    //      the role cbn be retrieved
    //    - rbise b RoleNotFoundException exception else
    //  - for multi-role retrievbl (multiRoleFlg true):
    //    - the Role object for given role nbme if role cbn be retrieved
    //    - b RoleUnresolved object with problem.
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RoleNotFoundException  if multiRoleFlg is fblse bnd:
    //  - there is no role with given nbme
    //  or
    //  - the role is not rebdbble.
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    Object getRoleInt(String roleNbme,
                      boolebn relbtionServCbllFlg,
                      RelbtionService relbtionServ,
                      boolebn multiRoleFlg)
        throws IllegblArgumentException,
               RoleNotFoundException,
               RelbtionServiceNotRegisteredException {

        if (roleNbme == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getRoleInt", roleNbme);

        int pbType = 0;

        Role role;
        synchronized(myRoleNbme2VblueMbp) {
            // No null Role is bllowed, so direct use of get()
            role = (myRoleNbme2VblueMbp.get(roleNbme));
        }

        if (role == null) {
                pbType = RoleStbtus.NO_ROLE_WITH_NAME;

        } else {
            // Checks if the role is rebdbble
            Integer stbtus;

            if (relbtionServCbllFlg) {

                // Cbll from the Relbtion Service, so direct bccess to it,
                // bvoiding MBebn Server
                // Shbll not throw b RelbtionTypeNotFoundException
                try {
                    stbtus = relbtionServ.checkRoleRebding(roleNbme,
                                                         myRelTypeNbme);
                } cbtch (RelbtionTypeNotFoundException exc) {
                    throw new RuntimeException(exc.getMessbge());
                }

            } else {

                // Cbll from getRole() method bbove
                // So we hbve b MBebn. We must bccess the Relbtion Service
                // vib the MBebn Server.
                Object[] pbrbms = new Object[2];
                pbrbms[0] = roleNbme;
                pbrbms[1] = myRelTypeNbme;
                String[] signbture = new String[2];
                signbture[0] = "jbvb.lbng.String";
                signbture[1] = "jbvb.lbng.String";
                // Cbn throw InstbnceNotFoundException if the Relbtion
                // Service is not registered (to be cbtched in bny cbse bnd
                // trbnsformed into RelbtionServiceNotRegisteredException).
                //
                // Shbll not throw b MBebnException, or b ReflectionException
                // or bn InstbnceNotFoundException
                try {
                    stbtus = (Integer)
                        (myRelServiceMBebnServer.invoke(myRelServiceNbme,
                                                        "checkRoleRebding",
                                                        pbrbms,
                                                        signbture));
                } cbtch (MBebnException exc1) {
                    throw new RuntimeException("incorrect relbtion type");
                } cbtch (ReflectionException exc2) {
                    throw new RuntimeException(exc2.getMessbge());
                } cbtch (InstbnceNotFoundException exc3) {
                    throw new RelbtionServiceNotRegisteredException(
                                                            exc3.getMessbge());
                }
            }

            pbType = stbtus.intVblue();
        }

        Object result;

        if (pbType == 0) {
            // Role cbn be retrieved

            if (!(multiRoleFlg)) {
                // Single role retrieved: returns its vblue
                // Note: no need to test if role vblue (list) not null before
                //       cloning, null vblue not bllowed, empty list if
                //       nothing.
                result = new ArrbyList<ObjectNbme>(role.getRoleVblue());

            } else {
                // Role retrieved during multi-role retrievbl: returns the
                // role
                result = (Role)(role.clone());
            }

        } else {
            // Role not retrieved

            if (!(multiRoleFlg)) {
                // Problem when retrieving b simple role: either role not
                // found or not rebdbble, so rbises b RoleNotFoundException.
                try {
                    RelbtionService.throwRoleProblemException(pbType,
                                                              roleNbme);
                    // To keep compiler hbppy :)
                    return null;
                } cbtch (InvblidRoleVblueException exc) {
                    throw new RuntimeException(exc.getMessbge());
                }

            } else {
                // Problem when retrieving b role in b multi-role retrievbl:
                // returns b RoleUnresolved object
                result = new RoleUnresolved(roleNbme, null, pbType);
            }
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "getRoleInt");
        return result;
    }

    // Gets the given roles
    // For ebch role, verifies if the role exists bnd is rebdbble bccording to
    // the relbtion type.
    //
    // This method is cblled in getRoles() bbove bnd in Relbtion Service
    // getRoles() method.
    //
    // -pbrbm roleNbmeArrby  brrby of nbmes of roles to be retrieved
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if object
    //  crebted by Relbtion Service.
    //
    // -return b RoleResult object
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    RoleResult getRolesInt(String[] roleNbmeArrby,
                           boolebn relbtionServCbllFlg,
                           RelbtionService relbtionServ)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException {

        if (roleNbmeArrby == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getRolesInt");

        RoleList roleList = new RoleList();
        RoleUnresolvedList roleUnresList = new RoleUnresolvedList();

        for (int i = 0; i < roleNbmeArrby.length; i++) {
            String currRoleNbme = roleNbmeArrby[i];

            Object currResult;

            // Cbn throw RelbtionServiceNotRegisteredException
            //
            // RoleNotFoundException: not possible but cbtch it for compiler :)
            try {
                currResult = getRoleInt(currRoleNbme,
                                        relbtionServCbllFlg,
                                        relbtionServ,
                                        true);

            } cbtch (RoleNotFoundException exc) {
                return null; // :)
            }

            if (currResult instbnceof Role) {
                // Cbn throw IllegblArgumentException if role is null
                // (normblly should not hbppen :(
                try {
                    roleList.bdd((Role)currResult);
                } cbtch (IllegblArgumentException exc) {
                    throw new RuntimeException(exc.getMessbge());
                }

            } else if (currResult instbnceof RoleUnresolved) {
                // Cbn throw IllegblArgumentException if role is null
                // (normblly should not hbppen :(
                try {
                    roleUnresList.bdd((RoleUnresolved)currResult);
                } cbtch (IllegblArgumentException exc) {
                    throw new RuntimeException(exc.getMessbge());
                }
            }
        }

        RoleResult result = new RoleResult(roleList, roleUnresList);
        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "getRolesInt");
        return result;
    }

    // Returns bll roles present in the relbtion
    //
    // -return b RoleResult object, including b RoleList (for roles
    //  successfully retrieved) bnd b RoleUnresolvedList (for roles not
    //  rebdbble).
    //
    // -exception IllegblArgumentException if null pbrbmeter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    //
    RoleResult getAllRolesInt(boolebn relbtionServCbllFlg,
                              RelbtionService relbtionServ)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException {

        if (relbtionServCbllFlg && relbtionServ == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "getAllRolesInt");

        List<String> roleNbmeList;
        synchronized(myRoleNbme2VblueMbp) {
            roleNbmeList =
                new ArrbyList<String>(myRoleNbme2VblueMbp.keySet());
        }
        String[] roleNbmes = new String[roleNbmeList.size()];
        roleNbmeList.toArrby(roleNbmes);

        RoleResult result = getRolesInt(roleNbmes,
                                        relbtionServCbllFlg,
                                        relbtionServ);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "getAllRolesInt");
        return result;
    }

    // Sets the role with given vblue
    //
    // This method is cblled in setRole() bbove.
    // It is blso cblled by the Relbtion Service setRole() method.
    // It is blso cblled in setRolesInt() method below (used in setRoles()
    // bbove bnd in RelbtionService setRoles() method).
    //
    // Will check the role bccording to its corresponding role definition
    // provided in relbtion's relbtion type
    // Will send b notificbtion (RelbtionNotificbtion with type
    // RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, depending if the
    // relbtion is b MBebn or not) if not initiblizbtion of role.
    //
    // -pbrbm bRole  role to be set (nbme bnd new vblue)
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if internbl
    //  relbtion
    // -pbrbm multiRoleFlg  true if getting the role in the scope of b
    //  multiple retrievbl.
    //
    // -return (except other "criticbl" exceptions):
    //  - for single role retrievbl (multiRoleFlg fblse):
    //    - null if the role hbs been set
    //    - rbise bn InvblidRoleVblueException
    // else
    //  - for multi-role retrievbl (multiRoleFlg true):
    //    - the Role object for given role nbme if role hbs been set
    //    - b RoleUnresolved object with problem else.
    //
    // -exception IllegblArgumentException if null pbrbmeter
    // -exception RoleNotFoundException  if multiRoleFlg is fblse bnd:
    //  - internbl relbtion bnd the role does not exist
    //  or
    //  - existing role (i.e. not initiblizing it) bnd the role is not
    //    writbble.
    // -exception InvblidRoleVblueException  ifmultiRoleFlg is fblse bnd
    //  vblue provided for:
    //   - the number of referenced MBebns in given vblue is less thbn
    //     expected minimum degree
    //   or
    //   - the number of referenced MBebns in provided vblue exceeds expected
    //     mbximum degree
    //   or
    //   - one referenced MBebn in the vblue is not bn Object of the MBebn
    //     clbss expected for thbt role
    //   or
    //   - b MBebn provided for thbt role does not exist
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RelbtionTypeNotFoundException  if relbtion type unknown
    // -exception RelbtionNotFoundException  if b relbtion MBebn hbs not been
    //  bdded in the Relbtion Service
    Object setRoleInt(Role bRole,
                      boolebn relbtionServCbllFlg,
                      RelbtionService relbtionServ,
                      boolebn multiRoleFlg)
        throws IllegblArgumentException,
               RoleNotFoundException,
               InvblidRoleVblueException,
               RelbtionServiceNotRegisteredException,
               RelbtionTypeNotFoundException,
               RelbtionNotFoundException {

        if (bRole == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "setRoleInt", new Object[] {bRole, relbtionServCbllFlg,
                relbtionServ, multiRoleFlg});

        String roleNbme = bRole.getRoleNbme();
        int pbType = 0;

        // Checks if role exists in the relbtion
        // No error if the role does not exist in the relbtion, to be bble to
        // hbndle initiblizbtion of role when crebting the relbtion
        // (roles provided in the RoleList pbrbmeter bre directly set but
        // roles butombticblly initiblized bre set using setRole())
        Role role;
        synchronized(myRoleNbme2VblueMbp) {
            role = (myRoleNbme2VblueMbp.get(roleNbme));
        }

        List<ObjectNbme> oldRoleVblue;
        Boolebn initFlg;

        if (role == null) {
            initFlg = true;
            oldRoleVblue = new ArrbyList<ObjectNbme>();

        } else {
            initFlg = fblse;
            oldRoleVblue = role.getRoleVblue();
        }

        // Checks if the role cbn be set: is writbble (except if
        // initiblizbtion) bnd correct vblue
        try {
            Integer stbtus;

            if (relbtionServCbllFlg) {

                // Cbll from the Relbtion Service, so direct bccess to it,
                // bvoiding MBebn Server
                //
                // Shbll not rbise b RelbtionTypeNotFoundException
                stbtus = relbtionServ.checkRoleWriting(bRole,
                                                     myRelTypeNbme,
                                                     initFlg);

            } else {

                // Cbll from setRole() method bbove
                // So we hbve b MBebn. We must bccess the Relbtion Service
                // vib the MBebn Server.
                Object[] pbrbms = new Object[3];
                pbrbms[0] = bRole;
                pbrbms[1] = myRelTypeNbme;
                pbrbms[2] = initFlg;
                String[] signbture = new String[3];
                signbture[0] = "jbvbx.mbnbgement.relbtion.Role";
                signbture[1] = "jbvb.lbng.String";
                signbture[2] = "jbvb.lbng.Boolebn";
                // Cbn throw InstbnceNotFoundException if the Relbtion Service
                // is not registered (to be trbnsformed into
                // RelbtionServiceNotRegisteredException in bny cbse).
                //
                // Cbn throw b MBebnException wrbpping b
                // RelbtionTypeNotFoundException:
                // throw wrbpped exception.
                //
                // Shbll not throw b ReflectionException
                stbtus = (Integer)
                    (myRelServiceMBebnServer.invoke(myRelServiceNbme,
                                                    "checkRoleWriting",
                                                    pbrbms,
                                                    signbture));
            }

            pbType = stbtus.intVblue();

        } cbtch (MBebnException exc2) {

            // Retrieves underlying exception
            Exception wrbppedExc = exc2.getTbrgetException();
            if (wrbppedExc instbnceof RelbtionTypeNotFoundException) {
                throw ((RelbtionTypeNotFoundException)wrbppedExc);

            } else {
                throw new RuntimeException(wrbppedExc.getMessbge());
            }

        } cbtch (ReflectionException exc3) {
            throw new RuntimeException(exc3.getMessbge());

        } cbtch (RelbtionTypeNotFoundException exc4) {
            throw new RuntimeException(exc4.getMessbge());

        } cbtch (InstbnceNotFoundException exc5) {
            throw new RelbtionServiceNotRegisteredException(exc5.getMessbge());
        }

        Object result = null;

        if (pbType == 0) {
            // Role cbn be set
            if (!(initFlg.boolebnVblue())) {

                // Not initiblizing the role
                // If role being initiblized:
                // - do not send bn updbte notificbtion
                // - do not try to updbte internbl mbp of Relbtion Service
                //   listing referenced MBebns, bs role is initiblized to bn
                //   empty list

                // Sends b notificbtion (RelbtionNotificbtion)
                // Cbn throw b RelbtionNotFoundException
                sendRoleUpdbteNotificbtion(bRole,
                                           oldRoleVblue,
                                           relbtionServCbllFlg,
                                           relbtionServ);

                // Updbtes the role mbp of the Relbtion Service
                // Cbn throw RelbtionNotFoundException
                updbteRelbtionServiceMbp(bRole,
                                         oldRoleVblue,
                                         relbtionServCbllFlg,
                                         relbtionServ);

            }

            // Sets the role
            synchronized(myRoleNbme2VblueMbp) {
                myRoleNbme2VblueMbp.put(roleNbme,
                                        (Role)(bRole.clone()));
            }

            // Single role set: returns null: nothing to set in result

            if (multiRoleFlg) {
                // Multi-roles retrievbl: returns the role
                result = bRole;
            }

        } else {

            // Role not set

            if (!(multiRoleFlg)) {
                // Problem when setting b simple role: either role not
                // found, not writbble, or incorrect vblue:
                // rbises bppropribte exception, RoleNotFoundException or
                // InvblidRoleVblueException
                RelbtionService.throwRoleProblemException(pbType,
                                                          roleNbme);
                // To keep compiler hbppy :)
                return null;

            } else {
                // Problem when retrieving b role in b multi-role retrievbl:
                // returns b RoleUnresolved object
                result = new RoleUnresolved(roleNbme,
                                            bRole.getRoleVblue(),
                                            pbType);
            }
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "setRoleInt");
        return result;
    }

    // Requires the Relbtion Service to send b notificbtion
    // RelbtionNotificbtion, with type being either:
    // - RelbtionNotificbtion.RELATION_BASIC_UPDATE if the updbted relbtion is
    //   b relbtion internbl to the Relbtion Service
    // - RelbtionNotificbtion.RELATION_MBEAN_UPDATE if the updbted relbtion is
    //   b relbtion MBebn.
    //
    // -pbrbm newRole  new role
    // -pbrbm oldRoleVblue  old role vblue (ArrbyList of ObjectNbmes)
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if object
    //  crebted by Relbtion Service.
    //
    // -exception IllegblArgumentException  if null pbrbmeter provided
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RelbtionNotFoundException if:
    //  - relbtion MBebn
    //  bnd
    //  - it hbs not been bdded into the Relbtion Service
    privbte void sendRoleUpdbteNotificbtion(Role newRole,
                                            List<ObjectNbme> oldRoleVblue,
                                            boolebn relbtionServCbllFlg,
                                            RelbtionService relbtionServ)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionNotFoundException {

        if (newRole == null ||
            oldRoleVblue == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "sendRoleUpdbteNotificbtion", new Object[] {newRole,
                oldRoleVblue, relbtionServCbllFlg, relbtionServ});

        if (relbtionServCbllFlg) {
            // Direct cbll to the Relbtion Service
            // Shbll not throw b RelbtionNotFoundException for bn internbl
            // relbtion
            try {
                relbtionServ.sendRoleUpdbteNotificbtion(myRelId,
                                                      newRole,
                                                      oldRoleVblue);
            } cbtch (RelbtionNotFoundException exc) {
                throw new RuntimeException(exc.getMessbge());
            }

        } else {

            Object[] pbrbms = new Object[3];
            pbrbms[0] = myRelId;
            pbrbms[1] = newRole;
            pbrbms[2] = oldRoleVblue;
            String[] signbture = new String[3];
            signbture[0] = "jbvb.lbng.String";
            signbture[1] = "jbvbx.mbnbgement.relbtion.Role";
            signbture[2] = "jbvb.util.List";

            // Cbn throw InstbnceNotFoundException if the Relbtion Service
            // is not registered (to be trbnsformed).
            //
            // Cbn throw b MBebnException wrbpping b
            // RelbtionNotFoundException (to be rbised in bny cbse): wrbpped
            // exception to be thrown
            //
            // Shbll not throw b ReflectionException
            try {
                myRelServiceMBebnServer.invoke(myRelServiceNbme,
                                               "sendRoleUpdbteNotificbtion",
                                               pbrbms,
                                               signbture);
            } cbtch (ReflectionException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (InstbnceNotFoundException exc2) {
                throw new RelbtionServiceNotRegisteredException(
                                                            exc2.getMessbge());
            } cbtch (MBebnException exc3) {
                Exception wrbppedExc = exc3.getTbrgetException();
                if (wrbppedExc instbnceof RelbtionNotFoundException) {
                    throw ((RelbtionNotFoundException)wrbppedExc);
                } else {
                    throw new RuntimeException(wrbppedExc.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "sendRoleUpdbteNotificbtion");
        return;
    }

    // Requires the Relbtion Service to updbte its internbl mbp hbndling
    // MBebns referenced in relbtions.
    // The Relbtion Service will blso updbte its recording bs b listener to
    // be informed bbout unregistrbtion of new referenced MBebns, bnd no longer
    // informed of MBebns no longer referenced.
    //
    // -pbrbm newRole  new role
    // -pbrbm oldRoleVblue  old role vblue (ArrbyList of ObjectNbmes)
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if object
    //  crebted by Relbtion Service.
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RelbtionNotFoundException if:
    //  - relbtion MBebn
    //  bnd
    //  - the relbtion is not bdded in the Relbtion Service
    privbte void updbteRelbtionServiceMbp(Role newRole,
                                          List<ObjectNbme> oldRoleVblue,
                                          boolebn relbtionServCbllFlg,
                                          RelbtionService relbtionServ)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionNotFoundException {

        if (newRole == null ||
            oldRoleVblue == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "updbteRelbtionServiceMbp", new Object[] {newRole,
                oldRoleVblue, relbtionServCbllFlg, relbtionServ});

        if (relbtionServCbllFlg) {
            // Direct cbll to the Relbtion Service
            // Shbll not throw b RelbtionNotFoundException
            try {
                relbtionServ.updbteRoleMbp(myRelId,
                                         newRole,
                                         oldRoleVblue);
            } cbtch (RelbtionNotFoundException exc) {
                throw new RuntimeException(exc.getMessbge());
            }

        } else {
            Object[] pbrbms = new Object[3];
            pbrbms[0] = myRelId;
            pbrbms[1] = newRole;
            pbrbms[2] = oldRoleVblue;
            String[] signbture = new String[3];
            signbture[0] = "jbvb.lbng.String";
            signbture[1] = "jbvbx.mbnbgement.relbtion.Role";
            signbture[2] = "jbvb.util.List";
            // Cbn throw InstbnceNotFoundException if the Relbtion Service
            // is not registered (to be trbnsformed).
            // Cbn throw b MBebnException wrbpping b RelbtionNotFoundException:
            // wrbpped exception to be thrown
            //
            // Shbll not throw b ReflectionException
            try {
                myRelServiceMBebnServer.invoke(myRelServiceNbme,
                                               "updbteRoleMbp",
                                               pbrbms,
                                               signbture);
            } cbtch (ReflectionException exc1) {
                throw new RuntimeException(exc1.getMessbge());
            } cbtch (InstbnceNotFoundException exc2) {
                throw new
                     RelbtionServiceNotRegisteredException(exc2.getMessbge());
            } cbtch (MBebnException exc3) {
                Exception wrbppedExc = exc3.getTbrgetException();
                if (wrbppedExc instbnceof RelbtionNotFoundException) {
                    throw ((RelbtionNotFoundException)wrbppedExc);
                } else {
                    throw new RuntimeException(wrbppedExc.getMessbge());
                }
            }
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "updbteRelbtionServiceMbp");
        return;
    }

    // Sets the given roles
    // For ebch role:
    // - will check the role bccording to its corresponding role definition
    //   provided in relbtion's relbtion type
    // - will send b notificbtion (RelbtionNotificbtion with type
    //   RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, depending if the
    //   relbtion is b MBebn or not) for ebch updbted role.
    //
    // This method is cblled in setRoles() bbove bnd in Relbtion Service
    // setRoles() method.
    //
    // -pbrbm list  list of roles to be set
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if object
    //  crebted by Relbtion Service.
    //
    // -return b RoleResult object
    //
    // -exception IllegblArgumentException  if null pbrbmeter
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RelbtionTypeNotFoundException if:
    //  - relbtion MBebn
    //  bnd
    //  - unknown relbtion type
    // -exception RelbtionNotFoundException if:
    //  - relbtion MBebn
    // bnd
    // - not bdded in the RS
    RoleResult setRolesInt(RoleList list,
                           boolebn relbtionServCbllFlg,
                           RelbtionService relbtionServ)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionTypeNotFoundException,
               RelbtionNotFoundException {

        if (list == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "setRolesInt",
                new Object[] {list, relbtionServCbllFlg, relbtionServ});

        RoleList roleList = new RoleList();
        RoleUnresolvedList roleUnresList = new RoleUnresolvedList();

        for (Role currRole : list.bsList()) {

            Object currResult = null;
            // Cbn throw:
            // RelbtionServiceNotRegisteredException,
            // RelbtionTypeNotFoundException
            //
            // Will not throw, due to pbrbmeters, RoleNotFoundException or
            // InvblidRoleVblueException, but cbtch them to keep compiler
            // hbppy
            try {
                currResult = setRoleInt(currRole,
                                        relbtionServCbllFlg,
                                        relbtionServ,
                                        true);
            } cbtch (RoleNotFoundException exc1) {
                // OK : Do not throw b RoleNotFoundException.
            } cbtch (InvblidRoleVblueException exc2) {
                // OK : Do not throw bn InvblidRoleVblueException.
            }

            if (currResult instbnceof Role) {
                // Cbn throw IllegblArgumentException if role is null
                // (normblly should not hbppen :(
                try {
                    roleList.bdd((Role)currResult);
                } cbtch (IllegblArgumentException exc) {
                    throw new RuntimeException(exc.getMessbge());
                }

            } else if (currResult instbnceof RoleUnresolved) {
                // Cbn throw IllegblArgumentException if role is null
                // (normblly should not hbppen :(
                try {
                    roleUnresList.bdd((RoleUnresolved)currResult);
                } cbtch (IllegblArgumentException exc) {
                    throw new RuntimeException(exc.getMessbge());
                }
            }
        }

        RoleResult result = new RoleResult(roleList, roleUnresList);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "setRolesInt");
        return result;
    }

    // Initiblizes bll members
    //
    // -pbrbm relbtionId  relbtion identifier, to identify the relbtion in the
    // Relbtion Service.
    // Expected to be unique in the given Relbtion Service.
    // -pbrbm relbtionServiceNbme  ObjectNbme of the Relbtion Service where
    // the relbtion will be registered.
    // It is required bs this is the Relbtion Service thbt is bwbre of the
    // definition of the relbtion type of given relbtion, so thbt will be bble
    // to check updbte operbtions (set). Direct bccess vib the Relbtion
    // Service (RelbtionService.setRole()) do not need this informbtion but
    // bs bny user relbtion is b MBebn, setRole() is pbrt of its mbnbgement
    // interfbce bnd cbn be cblled directly on the user relbtion MBebn. So the
    // user relbtion MBebn must be bwbre of the Relbtion Service where it will
    // be bdded.
    // -pbrbm relbtionTypeNbme  Nbme of relbtion type.
    // Expected to hbve been crebted in given Relbtion Service.
    // -pbrbm list  list of roles (Role objects) to initiblized the
    // relbtion. Cbn be null.
    // Expected to conform to relbtion info in bssocibted relbtion type.
    //
    // -exception InvblidRoleVblueException  if the sbme nbme is used for two
    //  roles.
    // -exception IllegblArgumentException  if b required vblue (Relbtion
    //  Service Object Nbme, etc.) is not provided bs pbrbmeter.
    privbte void initMembers(String relbtionId,
                             ObjectNbme relbtionServiceNbme,
                             MBebnServer relbtionServiceMBebnServer,
                             String relbtionTypeNbme,
                             RoleList list)
        throws InvblidRoleVblueException,
               IllegblArgumentException {

        if (relbtionId == null ||
            relbtionServiceNbme == null ||
            relbtionTypeNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "initMembers", new Object[] {relbtionId, relbtionServiceNbme,
                relbtionServiceMBebnServer, relbtionTypeNbme, list});

        myRelId = relbtionId;
        myRelServiceNbme = relbtionServiceNbme;
        myRelServiceMBebnServer = relbtionServiceMBebnServer;
        myRelTypeNbme = relbtionTypeNbme;
        // Cbn throw InvblidRoleVblueException
        initRoleMbp(list);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "initMembers");
        return;
    }

    // Initiblize the internbl role mbp from given RoleList pbrbmeter
    //
    // -pbrbm list  role list. Cbn be null.
    //  As it is b RoleList object, it cbnnot include null (rejected).
    //
    // -exception InvblidRoleVblueException  if the sbme role nbme is used for
    //  severbl roles.
    //
    privbte void initRoleMbp(RoleList list)
        throws InvblidRoleVblueException {

        if (list == null) {
            return;
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "initRoleMbp", list);

        synchronized(myRoleNbme2VblueMbp) {

            for (Role currRole : list.bsList()) {

                // No need to check if role is null, it is not bllowed to store
                // b null role in b RoleList :)
                String currRoleNbme = currRole.getRoleNbme();

                if (myRoleNbme2VblueMbp.contbinsKey(currRoleNbme)) {
                    // Role blrebdy provided in current list
                    StringBuilder excMsgStrB = new StringBuilder("Role nbme ");
                    excMsgStrB.bppend(currRoleNbme);
                    excMsgStrB.bppend(" used for two roles.");
                    throw new InvblidRoleVblueException(excMsgStrB.toString());
                }

                myRoleNbme2VblueMbp.put(currRoleNbme,
                                        (Role)(currRole.clone()));
            }
        }

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(), "initRoleMbp");
        return;
    }

    // Cbllbbck used by the Relbtion Service when b MBebn referenced in b role
    // is unregistered.
    // The Relbtion Service will cbll this method to let the relbtion
    // tbke bction to reflect the impbct of such unregistrbtion.
    // Current implementbtion is to set the role with its current vblue
    // (list of ObjectNbmes of referenced MBebns) without the unregistered
    // one.
    //
    // -pbrbm objectNbme  ObjectNbme of unregistered MBebn
    // -pbrbm roleNbme  nbme of role where the MBebn is referenced
    // -pbrbm relbtionServCbllFlg  true if cbll from the Relbtion Service; this
    //  will hbppen if the current RelbtionSupport object hbs been crebted by
    //  the Relbtion Service (vib crebteRelbtion()) method, so direct bccess is
    //  possible.
    // -pbrbm relbtionServ  reference to Relbtion Service object, if internbl
    //  relbtion
    //
    // -exception IllegblArgumentException if null pbrbmeter
    // -exception RoleNotFoundException  if:
    //  - the role does not exist
    //  or
    //  - role not writbble.
    // -exception InvblidRoleVblueException  if vblue provided for:
    //   - the number of referenced MBebns in given vblue is less thbn
    //     expected minimum degree
    //   or
    //   - the number of referenced MBebns in provided vblue exceeds expected
    //     mbximum degree
    //   or
    //   - one referenced MBebn in the vblue is not bn Object of the MBebn
    //     clbss expected for thbt role
    //   or
    //   - b MBebn provided for thbt role does not exist
    // -exception RelbtionServiceNotRegisteredException  if the Relbtion
    //  Service is not registered in the MBebn Server
    // -exception RelbtionTypeNotFoundException if unknown relbtion type
    // -exception RelbtionNotFoundException if current relbtion hbs not been
    //  bdded in the RS
    void hbndleMBebnUnregistrbtionInt(ObjectNbme objectNbme,
                                      String roleNbme,
                                      boolebn relbtionServCbllFlg,
                                      RelbtionService relbtionServ)
        throws IllegblArgumentException,
               RoleNotFoundException,
               InvblidRoleVblueException,
               RelbtionServiceNotRegisteredException,
               RelbtionTypeNotFoundException,
               RelbtionNotFoundException {

        if (objectNbme == null ||
            roleNbme == null ||
            (relbtionServCbllFlg && relbtionServ == null)) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(RelbtionSupport.clbss.getNbme(),
                "hbndleMBebnUnregistrbtionInt", new Object[] {objectNbme,
                roleNbme, relbtionServCbllFlg, relbtionServ});

        // Retrieves current role vblue
        Role role;
        synchronized(myRoleNbme2VblueMbp) {
            role = (myRoleNbme2VblueMbp.get(roleNbme));
        }

        if (role == null) {
            StringBuilder excMsgStrB = new StringBuilder();
            String excMsg = "No role with nbme ";
            excMsgStrB.bppend(excMsg);
            excMsgStrB.bppend(roleNbme);
            throw new RoleNotFoundException(excMsgStrB.toString());
        }
        List<ObjectNbme> currRoleVblue = role.getRoleVblue();

        // Note: no need to test if list not null before cloning, null vblue
        //       not bllowed for role vblue.
        List<ObjectNbme> newRoleVblue = new ArrbyList<ObjectNbme>(currRoleVblue);
        newRoleVblue.remove(objectNbme);
        Role newRole = new Role(roleNbme, newRoleVblue);

        // Cbn throw InvblidRoleVblueException,
        // RelbtionTypeNotFoundException
        // (RoleNotFoundException blrebdy detected)
        Object result =
            setRoleInt(newRole, relbtionServCbllFlg, relbtionServ, fblse);

        RELATION_LOGGER.exiting(RelbtionSupport.clbss.getNbme(),
                "hbndleMBebnUnregistrbtionInt");
        return;
    }

}
