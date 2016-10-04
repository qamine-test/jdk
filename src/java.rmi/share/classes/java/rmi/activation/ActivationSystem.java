/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.bctivbtion.UnknownGroupException;
import jbvb.rmi.bctivbtion.UnknownObjectException;

/**
 * The <code>ActivbtionSystem</code> provides b mebns for registering
 * groups bnd "bctivbtbble" objects to be bctivbted within those groups.
 * The <code>ActivbtionSystem</code> works closely with the
 * <code>Activbtor</code>, which bctivbtes objects registered vib the
 * <code>ActivbtionSystem</code>, bnd the <code>ActivbtionMonitor</code>,
 * which obtbins informbtion bbout bctive bnd inbctive objects,
 * bnd inbctive groups.
 *
 * @buthor      Ann Wollrbth
 * @see         Activbtor
 * @see         ActivbtionMonitor
 * @since       1.2
 */
public interfbce ActivbtionSystem extends Remote {

    /** The port to lookup the bctivbtion system. */
    public stbtic finbl int SYSTEM_PORT = 1098;

    /**
     * The <code>registerObject</code> method is used to register bn
     * bctivbtion descriptor, <code>desc</code>, bnd obtbin bn
     * bctivbtion identifier for b bctivbtbble remote object. The
     * <code>ActivbtionSystem</code> crebtes bn
     * <code>ActivbtionID</code> (b bctivbtion identifier) for the
     * object specified by the descriptor, <code>desc</code>, bnd
     * records, in stbble storbge, the bctivbtion descriptor bnd its
     * bssocibted identifier for lbter use. When the <code>Activbtor</code>
     * receives bn <code>bctivbte</code> request for b specific identifier, it
     * looks up the bctivbtion descriptor (registered previously) for
     * the specified identifier bnd uses thbt informbtion to bctivbte
     * the object.
     *
     * @pbrbm desc the object's bctivbtion descriptor
     * @return the bctivbtion id thbt cbn be used to bctivbte the object
     * @exception ActivbtionException if registrbtion fbils (e.g., dbtbbbse
     * updbte fbilure, etc).
     * @exception UnknownGroupException if group referred to in
     * <code>desc</code> is not registered with this system
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public ActivbtionID registerObject(ActivbtionDesc desc)
        throws ActivbtionException, UnknownGroupException, RemoteException;

    /**
     * Remove the bctivbtion id bnd bssocibted descriptor previously
     * registered with the <code>ActivbtionSystem</code>; the object
     * cbn no longer be bctivbted vib the object's bctivbtion id.
     *
     * @pbrbm id the object's bctivbtion id (from previous registrbtion)
     * @exception ActivbtionException if unregister fbils (e.g., dbtbbbse
     * updbte fbilure, etc).
     * @exception UnknownObjectException if object is unknown (not registered)
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public void unregisterObject(ActivbtionID id)
        throws ActivbtionException, UnknownObjectException, RemoteException;

    /**
     * Register the bctivbtion group. An bctivbtion group must be
     * registered with the <code>ActivbtionSystem</code> before objects
     * cbn be registered within thbt group.
     *
     * @pbrbm desc the group's descriptor
     * @return bn identifier for the group
     * @exception ActivbtionException if group registrbtion fbils
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public ActivbtionGroupID registerGroup(ActivbtionGroupDesc desc)
        throws ActivbtionException, RemoteException;

    /**
     * Cbllbbck to inform bctivbtion system thbt group is now
     * bctive. This cbll is mbde internblly by the
     * <code>ActivbtionGroup.crebteGroup</code> method to inform
     * the <code>ActivbtionSystem</code> thbt the group is now
     * bctive.
     *
     * @pbrbm id the bctivbtion group's identifier
     * @pbrbm group the group's instbntibtor
     * @pbrbm incbrnbtion the group's incbrnbtion number
     * @return monitor for bctivbtion group
     * @exception UnknownGroupException if group is not registered
     * @exception ActivbtionException if b group for the specified
     * <code>id</code> is blrebdy bctive bnd thbt group is not equbl
     * to the specified <code>group</code> or thbt group hbs b different
     * <code>incbrnbtion</code> thbn the specified <code>group</code>
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public ActivbtionMonitor bctiveGroup(ActivbtionGroupID id,
                                         ActivbtionInstbntibtor group,
                                         long incbrnbtion)
        throws UnknownGroupException, ActivbtionException, RemoteException;

    /**
     * Remove the bctivbtion group. An bctivbtion group mbkes this cbll bbck
     * to inform the bctivbtor thbt the group should be removed (destroyed).
     * If this cbll completes successfully, objects cbn no longer be
     * registered or bctivbted within the group. All informbtion of the
     * group bnd its bssocibted objects is removed from the system.
     *
     * @pbrbm id the bctivbtion group's identifier
     * @exception ActivbtionException if unregister fbils (e.g., dbtbbbse
     * updbte fbilure, etc).
     * @exception UnknownGroupException if group is not registered
     * @exception RemoteException if remote cbll fbils
     * @since 1.2
     */
    public void unregisterGroup(ActivbtionGroupID id)
        throws ActivbtionException, UnknownGroupException, RemoteException;

    /**
     * Shutdown the bctivbtion system. Destroys bll groups spbwned by
     * the bctivbtion dbemon bnd exits the bctivbtion dbemon.
     * @exception RemoteException if fbiled to contbct/shutdown the bctivbtion
     * dbemon
     * @since 1.2
     */
    public void shutdown() throws RemoteException;

    /**
     * Set the bctivbtion descriptor, <code>desc</code> for the object with
     * the bctivbtion identifier, <code>id</code>. The chbnge will tbke
     * effect upon subsequent bctivbtion of the object.
     *
     * @pbrbm id the bctivbtion identifier for the bctivbtbble object
     * @pbrbm desc the bctivbtion descriptor for the bctivbtbble object
     * @exception UnknownGroupException the group bssocibted with
     * <code>desc</code> is not b registered group
     * @exception UnknownObjectException the bctivbtion <code>id</code>
     * is not registered
     * @exception ActivbtionException for generbl fbilure (e.g., unbble
     * to updbte log)
     * @exception RemoteException if remote cbll fbils
     * @return the previous vblue of the bctivbtion descriptor
     * @see #getActivbtionDesc
     * @since 1.2
     */
    public ActivbtionDesc setActivbtionDesc(ActivbtionID id,
                                            ActivbtionDesc desc)
        throws ActivbtionException, UnknownObjectException,
            UnknownGroupException, RemoteException;

    /**
     * Set the bctivbtion group descriptor, <code>desc</code> for the object
     * with the bctivbtion group identifier, <code>id</code>. The chbnge will
     * tbke effect upon subsequent bctivbtion of the group.
     *
     * @pbrbm id the bctivbtion group identifier for the bctivbtion group
     * @pbrbm desc the bctivbtion group descriptor for the bctivbtion group
     * @exception UnknownGroupException the group bssocibted with
     * <code>id</code> is not b registered group
     * @exception ActivbtionException for generbl fbilure (e.g., unbble
     * to updbte log)
     * @exception RemoteException if remote cbll fbils
     * @return the previous vblue of the bctivbtion group descriptor
     * @see #getActivbtionGroupDesc
     * @since 1.2
     */
    public ActivbtionGroupDesc setActivbtionGroupDesc(ActivbtionGroupID id,
                                                      ActivbtionGroupDesc desc)
       throws ActivbtionException, UnknownGroupException, RemoteException;

    /**
     * Returns the bctivbtion descriptor, for the object with the bctivbtion
     * identifier, <code>id</code>.
     *
     * @pbrbm id the bctivbtion identifier for the bctivbtbble object
     * @exception UnknownObjectException if <code>id</code> is not registered
     * @exception ActivbtionException for generbl fbilure
     * @exception RemoteException if remote cbll fbils
     * @return the bctivbtion descriptor
     * @see #setActivbtionDesc
     * @since 1.2
     */
    public ActivbtionDesc getActivbtionDesc(ActivbtionID id)
       throws ActivbtionException, UnknownObjectException, RemoteException;

    /**
     * Returns the bctivbtion group descriptor, for the group
     * with the bctivbtion group identifier, <code>id</code>.
     *
     * @pbrbm id the bctivbtion group identifier for the group
     * @exception UnknownGroupException if <code>id</code> is not registered
     * @exception ActivbtionException for generbl fbilure
     * @exception RemoteException if remote cbll fbils
     * @return the bctivbtion group descriptor
     * @see #setActivbtionGroupDesc
     * @since 1.2
     */
    public ActivbtionGroupDesc getActivbtionGroupDesc(ActivbtionGroupID id)
       throws ActivbtionException, UnknownGroupException, RemoteException;
}
