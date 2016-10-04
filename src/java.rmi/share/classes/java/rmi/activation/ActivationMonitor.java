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
import jbvb.rmi.bctivbtion.UnknownGroupException;
import jbvb.rmi.bctivbtion.UnknownObjectException;

/**
 * An <code>ActivbtionMonitor</code> is specific to bn
 * <code>ActivbtionGroup</code> bnd is obtbined when b group is
 * reported bctive vib b cbll to
 * <code>ActivbtionSystem.bctiveGroup</code> (this is done
 * internblly). An bctivbtion group is responsible for informing its
 * <code>ActivbtionMonitor</code> when either: its objects become bctive or
 * inbctive, or the group bs b whole becomes inbctive.
 *
 * @buthor      Ann Wollrbth
 * @see         Activbtor
 * @see         ActivbtionSystem
 * @see         ActivbtionGroup
 * @since       1.2
 */
public interfbce ActivbtionMonitor extends Remote {

   /**
     * An bctivbtion group cblls its monitor's
     * <code>inbctiveObject</code> method when bn object in its group
     * becomes inbctive (debctivbtes).  An bctivbtion group discovers
     * thbt bn object (thbt it pbrticipbted in bctivbting) in its VM
     * is no longer bctive, vib cblls to the bctivbtion group's
     * <code>inbctiveObject</code> method. <p>
     *
     * The <code>inbctiveObject</code> cbll informs the
     * <code>ActivbtionMonitor</code> thbt the remote object reference
     * it holds for the object with the bctivbtion identifier,
     * <code>id</code>, is no longer vblid. The monitor considers the
     * reference bssocibted with <code>id</code> bs b stble reference.
     * Since the reference is considered stble, b subsequent
     * <code>bctivbte</code> cbll for the sbme bctivbtion identifier
     * results in re-bctivbting the remote object.
     *
     * @pbrbm id the object's bctivbtion identifier
     * @exception UnknownObjectException if object is unknown
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public void inbctiveObject(ActivbtionID id)
        throws UnknownObjectException, RemoteException;

    /**
     * Informs thbt bn object is now bctive. An <code>ActivbtionGroup</code>
     * informs its monitor if bn object in its group becomes bctive by
     * other mebns thbn being bctivbted directly (i.e., the object
     * is registered bnd "bctivbted" itself).
     *
     * @pbrbm id the bctive object's id
     * @pbrbm obj the mbrshblled form of the object's stub
     * @exception UnknownObjectException if object is unknown
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public void bctiveObject(ActivbtionID id,
                             MbrshblledObject<? extends Remote> obj)
        throws UnknownObjectException, RemoteException;

    /**
     * Informs thbt the group is now inbctive. The group will be
     * recrebted upon b subsequent request to bctivbte bn object
     * within the group. A group becomes inbctive when bll objects
     * in the group report thbt they bre inbctive.
     *
     * @pbrbm id the group's id
     * @pbrbm incbrnbtion the group's incbrnbtion number
     * @exception UnknownGroupException if group is unknown
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public void inbctiveGroup(ActivbtionGroupID id,
                              long incbrnbtion)
        throws UnknownGroupException, RemoteException;

}
