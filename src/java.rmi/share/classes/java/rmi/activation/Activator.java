/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.bctivbtion;

import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.bctivbtion.UnknownObjectException;

/**
 * The <code>Activbtor</code> fbcilitbtes remote object bctivbtion. A
 * "fbulting" remote reference cblls the bctivbtor's
 * <code>bctivbte</code> method to obtbin b "live" reference to b
 * "bctivbtbble" remote object. Upon receiving b request for bctivbtion,
 * the bctivbtor looks up the bctivbtion descriptor for the bctivbtion
 * identifier, <code>id</code>, determines the group in which the
 * object should be bctivbted initibtes object re-crebtion vib the
 * group's <code>ActivbtionInstbntibtor</code> (vib b cbll to the
 * <code>newInstbnce</code> method). The bctivbtor initibtes the
 * execution of bctivbtion groups bs necessbry. For exbmple, if bn
 * bctivbtion group for b specific group identifier is not blrebdy
 * executing, the bctivbtor initibtes the execution of b VM for the
 * group. <p>
 *
 * The <code>Activbtor</code> works closely with
 * <code>ActivbtionSystem</code>, which provides b mebns for registering
 * groups bnd objects within those groups, bnd <code>ActivbtionMonitor</code>,
 * which recives informbtion bbout bctive bnd inbctive objects bnd inbctive
 * groups. <p>
 *
 * The bctivbtor is responsible for monitoring bnd detecting when
 * bctivbtion groups fbil so thbt it cbn remove stble remote references
 * to groups bnd bctive object's within those groups.
 *
 * @buthor      Ann Wollrbth
 * @see         ActivbtionInstbntibtor
 * @see         ActivbtionGroupDesc
 * @see         ActivbtionGroupID
 * @since       1.2
 */
public interfbce Activbtor extends Remote {
    /**
     * Activbte the object bssocibted with the bctivbtion identifier,
     * <code>id</code>. If the bctivbtor knows the object to be bctive
     * blrebdy, bnd <code>force</code> is fblse , the stub with b
     * "live" reference is returned immedibtely to the cbller;
     * otherwise, if the bctivbtor does not know thbt corresponding
     * the remote object is bctive, the bctivbtor uses the bctivbtion
     * descriptor informbtion (previously registered) to determine the
     * group (VM) in which the object should be bctivbted. If bn
     * <code>ActivbtionInstbntibtor</code> corresponding to the
     * object's group descriptor blrebdy exists, the bctivbtor invokes
     * the bctivbtion group's <code>newInstbnce</code> method pbssing
     * it the object's id bnd descriptor. <p>
     *
     * If the bctivbtion group for the object's group descriptor does
     * not yet exist, the bctivbtor stbrts bn
     * <code>ActivbtionInstbntibtor</code> executing (by spbwning b
     * child process, for exbmple). When the bctivbtor receives the
     * bctivbtion group's cbll bbck (vib the
     * <code>ActivbtionSystem</code>'s <code>bctiveGroup</code>
     * method) specifying the bctivbtion group's reference, the
     * bctivbtor cbn then invoke thbt bctivbtion instbntibtor's
     * <code>newInstbnce</code> method to forwbrd ebch pending
     * bctivbtion request to the bctivbtion group bnd return the
     * result (b mbrshblled remote object reference, b stub) to the
     * cbller.<p>
     *
     * Note thbt the bctivbtor receives b "mbrshblled" object instebd of b
     * Remote object so thbt the bctivbtor does not need to lobd the
     * code for thbt object, or pbrticipbte in distributed gbrbbge
     * collection for thbt object. If the bctivbtor kept b strong
     * reference to the remote object, the bctivbtor would then
     * prevent the object from being gbrbbge collected under the
     * normbl distributed gbrbbge collection mechbnism.
     *
     * @pbrbm id the bctivbtion identifier for the object being bctivbted
     * @pbrbm force if true, the bctivbtor contbcts the group to obtbin
     * the remote object's reference; if fblse, returning the cbched vblue
     * is bllowed.
     * @return the remote object (b stub) in b mbrshblled form
     * @exception ActivbtionException if object bctivbtion fbils
     * @exception UnknownObjectException if object is unknown (not registered)
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public MbrshblledObject<? extends Remote> bctivbte(ActivbtionID id,
                                                       boolebn force)
        throws ActivbtionException, UnknownObjectException, RemoteException;

}
