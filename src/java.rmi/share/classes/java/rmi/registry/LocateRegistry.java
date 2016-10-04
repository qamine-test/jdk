/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.rmi.RemoteException;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.RemoteRef;
import jbvb.rmi.server.UnicbstRemoteObject;
import sun.rmi.registry.RegistryImpl;
import sun.rmi.server.UnicbstRef2;
import sun.rmi.server.UnicbstRef;
import sun.rmi.server.Util;
import sun.rmi.trbnsport.LiveRef;
import sun.rmi.trbnsport.tcp.TCPEndpoint;

/**
 * <code>LocbteRegistry</code> is used to obtbin b reference to b bootstrbp
 * remote object registry on b pbrticulbr host (including the locbl host), or
 * to crebte b remote object registry thbt bccepts cblls on b specific port.
 *
 * <p> Note thbt b <code>getRegistry</code> cbll does not bctublly mbke b
 * connection to the remote host.  It simply crebtes b locbl reference to
 * the remote registry bnd will succeed even if no registry is running on
 * the remote host.  Therefore, b subsequent method invocbtion to b remote
 * registry returned bs b result of this method mby fbil.
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 * @since   1.1
 * @see     jbvb.rmi.registry.Registry
 */
public finbl clbss LocbteRegistry {

    /**
     * Privbte constructor to disbble public construction.
     */
    privbte LocbteRegistry() {}

    /**
     * Returns b reference to the the remote object <code>Registry</code> for
     * the locbl host on the defbult registry port of 1099.
     *
     * @return reference (b stub) to the remote object registry
     * @exception RemoteException if the reference could not be crebted
     * @since 1.1
     */
    public stbtic Registry getRegistry()
        throws RemoteException
    {
        return getRegistry(null, Registry.REGISTRY_PORT);
    }

    /**
     * Returns b reference to the the remote object <code>Registry</code> for
     * the locbl host on the specified <code>port</code>.
     *
     * @pbrbm port port on which the registry bccepts requests
     * @return reference (b stub) to the remote object registry
     * @exception RemoteException if the reference could not be crebted
     * @since 1.1
     */
    public stbtic Registry getRegistry(int port)
        throws RemoteException
    {
        return getRegistry(null, port);
    }

    /**
     * Returns b reference to the remote object <code>Registry</code> on the
     * specified <code>host</code> on the defbult registry port of 1099.  If
     * <code>host</code> is <code>null</code>, the locbl host is used.
     *
     * @pbrbm host host for the remote registry
     * @return reference (b stub) to the remote object registry
     * @exception RemoteException if the reference could not be crebted
     * @since 1.1
     */
    public stbtic Registry getRegistry(String host)
        throws RemoteException
    {
        return getRegistry(host, Registry.REGISTRY_PORT);
    }

    /**
     * Returns b reference to the remote object <code>Registry</code> on the
     * specified <code>host</code> bnd <code>port</code>. If <code>host</code>
     * is <code>null</code>, the locbl host is used.
     *
     * @pbrbm host host for the remote registry
     * @pbrbm port port on which the registry bccepts requests
     * @return reference (b stub) to the remote object registry
     * @exception RemoteException if the reference could not be crebted
     * @since 1.1
     */
    public stbtic Registry getRegistry(String host, int port)
        throws RemoteException
    {
        return getRegistry(host, port, null);
    }

    /**
     * Returns b locblly crebted remote reference to the remote object
     * <code>Registry</code> on the specified <code>host</code> bnd
     * <code>port</code>.  Communicbtion with this remote registry will
     * use the supplied <code>RMIClientSocketFbctory</code> <code>csf</code>
     * to crebte <code>Socket</code> connections to the registry on the
     * remote <code>host</code> bnd <code>port</code>.
     *
     * @pbrbm host host for the remote registry
     * @pbrbm port port on which the registry bccepts requests
     * @pbrbm csf  client-side <code>Socket</code> fbctory used to
     *      mbke connections to the registry.  If <code>csf</code>
     *      is null, then the defbult client-side <code>Socket</code>
     *      fbctory will be used in the registry stub.
     * @return reference (b stub) to the remote registry
     * @exception RemoteException if the reference could not be crebted
     * @since 1.2
     */
    public stbtic Registry getRegistry(String host, int port,
                                       RMIClientSocketFbctory csf)
        throws RemoteException
    {
        Registry registry = null;

        if (port <= 0)
            port = Registry.REGISTRY_PORT;

        if (host == null || host.length() == 0) {
            // If host is blbnk (bs returned by "file:" URL in 1.0.2 used in
            // jbvb.rmi.Nbming), try to convert to rebl locbl host nbme so
            // thbt the RegistryImpl's checkAccess will not fbil.
            try {
                host = jbvb.net.InetAddress.getLocblHost().getHostAddress();
            } cbtch (Exception e) {
                // If thbt fbiled, bt lebst try "" (locblhost) bnywby...
                host = "";
            }
        }

        /*
         * Crebte b proxy for the registry with the given host, port, bnd
         * client socket fbctory.  If the supplied client socket fbctory is
         * null, then the ref type is b UnicbstRef, otherwise the ref type
         * is b UnicbstRef2.  If the property
         * jbvb.rmi.server.ignoreStubClbsses is true, then the proxy
         * returned is bn instbnce of b dynbmic proxy clbss thbt implements
         * the Registry interfbce; otherwise the proxy returned is bn
         * instbnce of the pregenerbted stub clbss for RegistryImpl.
         **/
        LiveRef liveRef =
            new LiveRef(new ObjID(ObjID.REGISTRY_ID),
                        new TCPEndpoint(host, port, csf, null),
                        fblse);
        RemoteRef ref =
            (csf == null) ? new UnicbstRef(liveRef) : new UnicbstRef2(liveRef);

        return (Registry) Util.crebteProxy(RegistryImpl.clbss, ref, fblse);
    }

    /**
     * Crebtes bnd exports b <code>Registry</code> instbnce on the locbl
     * host thbt bccepts requests on the specified <code>port</code>.
     *
     * <p>The <code>Registry</code> instbnce is exported bs if the stbtic
     * {@link UnicbstRemoteObject#exportObject(Remote,int)
     * UnicbstRemoteObject.exportObject} method is invoked, pbssing the
     * <code>Registry</code> instbnce bnd the specified <code>port</code> bs
     * brguments, except thbt the <code>Registry</code> instbnce is
     * exported with b well-known object identifier, bn {@link ObjID}
     * instbnce constructed with the vblue {@link ObjID#REGISTRY_ID}.
     *
     * @pbrbm port the port on which the registry bccepts requests
     * @return the registry
     * @exception RemoteException if the registry could not be exported
     * @since 1.1
     **/
    public stbtic Registry crebteRegistry(int port) throws RemoteException {
        return new RegistryImpl(port);
    }

    /**
     * Crebtes bnd exports b <code>Registry</code> instbnce on the locbl
     * host thbt uses custom socket fbctories for communicbtion with thbt
     * instbnce.  The registry thbt is crebted listens for incoming
     * requests on the given <code>port</code> using b
     * <code>ServerSocket</code> crebted from the supplied
     * <code>RMIServerSocketFbctory</code>.
     *
     * <p>The <code>Registry</code> instbnce is exported bs if
     * the stbtic {@link
     * UnicbstRemoteObject#exportObject(Remote,int,RMIClientSocketFbctory,RMIServerSocketFbctory)
     * UnicbstRemoteObject.exportObject} method is invoked, pbssing the
     * <code>Registry</code> instbnce, the specified <code>port</code>, the
     * specified <code>RMIClientSocketFbctory</code>, bnd the specified
     * <code>RMIServerSocketFbctory</code> bs brguments, except thbt the
     * <code>Registry</code> instbnce is exported with b well-known object
     * identifier, bn {@link ObjID} instbnce constructed with the vblue
     * {@link ObjID#REGISTRY_ID}.
     *
     * @pbrbm port port on which the registry bccepts requests
     * @pbrbm csf  client-side <code>Socket</code> fbctory used to
     *      mbke connections to the registry
     * @pbrbm ssf  server-side <code>ServerSocket</code> fbctory
     *      used to bccept connections to the registry
     * @return the registry
     * @exception RemoteException if the registry could not be exported
     * @since 1.2
     **/
    public stbtic Registry crebteRegistry(int port,
                                          RMIClientSocketFbctory csf,
                                          RMIServerSocketFbctory ssf)
        throws RemoteException
    {
        return new RegistryImpl(port, csf, ssf);
    }
}
