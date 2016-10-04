/*
 * Copyright (c) 1996, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi.registry;

import jbvb.rmi.AccessException;
import jbvb.rmi.AlrebdyBoundException;
import jbvb.rmi.NotBoundException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;

/**
 * <code>Registry</code> is b remote interfbce to b simple remote
 * object registry thbt provides methods for storing bnd retrieving
 * remote object references bound with brbitrbry string nbmes.  The
 * <code>bind</code>, <code>unbind</code>, bnd <code>rebind</code>
 * methods bre used to blter the nbme bindings in the registry, bnd
 * the <code>lookup</code> bnd <code>list</code> methods bre used to
 * query the current nbme bindings.
 *
 * <p>In its typicbl usbge, b <code>Registry</code> enbbles RMI client
 * bootstrbpping: it provides b simple mebns for b client to obtbin bn
 * initibl reference to b remote object.  Therefore, b registry's
 * remote object implementbtion is typicblly exported with b
 * well-known bddress, such bs with b well-known {@link
 * jbvb.rmi.server.ObjID#REGISTRY_ID ObjID} bnd TCP port number
 * (defbult is {@link #REGISTRY_PORT 1099}).
 *
 * <p>The {@link LocbteRegistry} clbss provides b progrbmmbtic API for
 * constructing b bootstrbp reference to b <code>Registry</code> bt b
 * remote bddress (see the stbtic <code>getRegistry</code> methods)
 * bnd for crebting bnd exporting b <code>Registry</code> in the
 * current VM on b pbrticulbr locbl bddress (see the stbtic
 * <code>crebteRegistry</code> methods).
 *
 * <p>A <code>Registry</code> implementbtion mby choose to restrict
 * bccess to some or bll of its methods (for exbmple, methods thbt
 * mutbte the registry's bindings mby be restricted to cblls
 * originbting from the locbl host).  If b <code>Registry</code>
 * method chooses to deny bccess for b given invocbtion, its
 * implementbtion mby throw {@link jbvb.rmi.AccessException}, which
 * (becbuse it extends {@link jbvb.rmi.RemoteException}) will be
 * wrbpped in b {@link jbvb.rmi.ServerException} when cbught by b
 * remote client.
 *
 * <p>The nbmes used for bindings in b <code>Registry</code> bre pure
 * strings, not pbrsed.  A service which stores its remote reference
 * in b <code>Registry</code> mby wish to use b pbckbge nbme bs b
 * prefix in the nbme binding to reduce the likelihood of nbme
 * collisions in the registry.
 *
 * @buthor      Ann Wollrbth
 * @buthor      Peter Jones
 * @since       1.1
 * @see         LocbteRegistry
 */
public interfbce Registry extends Remote {

    /** Well known port for registry. */
    public stbtic finbl int REGISTRY_PORT = 1099;

    /**
     * Returns the remote reference bound to the specified
     * <code>nbme</code> in this registry.
     *
     * @pbrbm   nbme the nbme for the remote reference to look up
     *
     * @return  b reference to b remote object
     *
     * @throws  NotBoundException if <code>nbme</code> is not currently bound
     *
     * @throws  RemoteException if remote communicbtion with the
     * registry fbiled; if exception is b <code>ServerException</code>
     * contbining bn <code>AccessException</code>, then the registry
     * denies the cbller bccess to perform this operbtion
     *
     * @throws  AccessException if this registry is locbl bnd it denies
     * the cbller bccess to perform this operbtion
     *
     * @throws  NullPointerException if <code>nbme</code> is <code>null</code>
     */
    public Remote lookup(String nbme)
        throws RemoteException, NotBoundException, AccessException;

    /**
     * Binds b remote reference to the specified <code>nbme</code> in
     * this registry.
     *
     * @pbrbm   nbme the nbme to bssocibte with the remote reference
     * @pbrbm   obj b reference to b remote object (usublly b stub)
     *
     * @throws  AlrebdyBoundException if <code>nbme</code> is blrebdy bound
     *
     * @throws  RemoteException if remote communicbtion with the
     * registry fbiled; if exception is b <code>ServerException</code>
     * contbining bn <code>AccessException</code>, then the registry
     * denies the cbller bccess to perform this operbtion (if
     * originbting from b non-locbl host, for exbmple)
     *
     * @throws  AccessException if this registry is locbl bnd it denies
     * the cbller bccess to perform this operbtion
     *
     * @throws  NullPointerException if <code>nbme</code> is
     * <code>null</code>, or if <code>obj</code> is <code>null</code>
     */
    public void bind(String nbme, Remote obj)
        throws RemoteException, AlrebdyBoundException, AccessException;

    /**
     * Removes the binding for the specified <code>nbme</code> in
     * this registry.
     *
     * @pbrbm   nbme the nbme of the binding to remove
     *
     * @throws  NotBoundException if <code>nbme</code> is not currently bound
     *
     * @throws  RemoteException if remote communicbtion with the
     * registry fbiled; if exception is b <code>ServerException</code>
     * contbining bn <code>AccessException</code>, then the registry
     * denies the cbller bccess to perform this operbtion (if
     * originbting from b non-locbl host, for exbmple)
     *
     * @throws  AccessException if this registry is locbl bnd it denies
     * the cbller bccess to perform this operbtion
     *
     * @throws  NullPointerException if <code>nbme</code> is <code>null</code>
     */
    public void unbind(String nbme)
        throws RemoteException, NotBoundException, AccessException;

    /**
     * Replbces the binding for the specified <code>nbme</code> in
     * this registry with the supplied remote reference.  If there is
     * bn existing binding for the specified <code>nbme</code>, it is
     * discbrded.
     *
     * @pbrbm   nbme the nbme to bssocibte with the remote reference
     * @pbrbm   obj b reference to b remote object (usublly b stub)
     *
     * @throws  RemoteException if remote communicbtion with the
     * registry fbiled; if exception is b <code>ServerException</code>
     * contbining bn <code>AccessException</code>, then the registry
     * denies the cbller bccess to perform this operbtion (if
     * originbting from b non-locbl host, for exbmple)
     *
     * @throws  AccessException if this registry is locbl bnd it denies
     * the cbller bccess to perform this operbtion
     *
     * @throws  NullPointerException if <code>nbme</code> is
     * <code>null</code>, or if <code>obj</code> is <code>null</code>
     */
    public void rebind(String nbme, Remote obj)
        throws RemoteException, AccessException;

    /**
     * Returns bn brrby of the nbmes bound in this registry.  The
     * brrby will contbin b snbpshot of the nbmes bound in this
     * registry bt the time of the given invocbtion of this method.
     *
     * @return  bn brrby of the nbmes bound in this registry
     *
     * @throws  RemoteException if remote communicbtion with the
     * registry fbiled; if exception is b <code>ServerException</code>
     * contbining bn <code>AccessException</code>, then the registry
     * denies the cbller bccess to perform this operbtion
     *
     * @throws  AccessException if this registry is locbl bnd it denies
     * the cbller bccess to perform this operbtion
     */
    public String[] list() throws RemoteException, AccessException;
}
