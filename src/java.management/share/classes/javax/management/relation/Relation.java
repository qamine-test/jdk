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

import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.mbnbgement.ObjectNbme;

/**
 * This interfbce hbs to be implemented by bny MBebn clbss expected to
 * represent b relbtion mbnbged using the Relbtion Service.
 * <P>Simple relbtions, i.e. hbving only roles, no properties or methods, cbn
 * be crebted directly by the Relbtion Service (represented bs RelbtionSupport
 * objects, internblly hbndled by the Relbtion Service).
 * <P>If the user wbnts to represent more complex relbtions, involving
 * properties bnd/or methods, he hbs to provide his own clbss implementing the
 * Relbtion interfbce. This cbn be bchieved either by inheriting from
 * RelbtionSupport clbss, or by implementing the interfbce (fully or delegbtion to
 * b RelbtionSupport object member).
 * <P>Specifying such user relbtion clbss is to introduce properties bnd/or
 * methods. Those hbve to be exposed for remote mbnbgement. So this mebns thbt
 * bny user relbtion clbss must be b MBebn clbss.
 *
 * @since 1.5
 */
public interfbce Relbtion {

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
               RelbtionServiceNotRegisteredException;

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
               RelbtionServiceNotRegisteredException;

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
               RoleNotFoundException;

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
        throws RelbtionServiceNotRegisteredException;

    /**
     * Returns bll roles in the relbtion without checking rebd mode.
     *
     * @return b RoleList.
     */
    public RoleList retrieveAllRoles();

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
     * <P>- b MBebn provided for thbt role does not exist.
     * @exception RelbtionServiceNotRegisteredException  if the Relbtion
     * Service is not registered in the MBebn Server
     * @exception RelbtionTypeNotFoundException  if the relbtion type hbs not
     * been declbred in the Relbtion Service.
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
               RelbtionNotFoundException;

    /**
     * Sets the given roles.
     * <P>Will check the role bccording to its corresponding role definition
     * provided in relbtion's relbtion type
     * <P>Will send one notificbtion (RelbtionNotificbtion with type
     * RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, depending if the
     * relbtion is b MBebn or not) per updbted role.
     *
     * @pbrbm roleList  list of roles to be set
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
    public RoleResult setRoles(RoleList roleList)
        throws IllegblArgumentException,
               RelbtionServiceNotRegisteredException,
               RelbtionTypeNotFoundException,
               RelbtionNotFoundException;

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
               RelbtionNotFoundException;

    /**
     * Retrieves MBebns referenced in the vbrious roles of the relbtion.
     *
     * @return b HbshMbp mbpping:
     * <P> ObjectNbme {@literbl ->} ArrbyList of String (role nbmes)
     */
    public Mbp<ObjectNbme,List<String>> getReferencedMBebns();

    /**
     * Returns nbme of bssocibted relbtion type.
     *
     * @return the nbme of the relbtion type.
     */
    public String getRelbtionTypeNbme();

    /**
     * Returns ObjectNbme of the Relbtion Service hbndling the relbtion.
     *
     * @return the ObjectNbme of the Relbtion Service.
     */
    public ObjectNbme getRelbtionServiceNbme();

    /**
     * Returns relbtion identifier (used to uniquely identify the relbtion
     * inside the Relbtion Service).
     *
     * @return the relbtion id.
     */
    public String getRelbtionId();
}
