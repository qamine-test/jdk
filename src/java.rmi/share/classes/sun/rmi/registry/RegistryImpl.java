/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.registry;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;
import jbvb.io.FilePermission;
import jbvb.io.IOException;
import jbvb.net.*;
import jbvb.rmi.*;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RemoteServer;
import jbvb.rmi.server.ServerNotActiveException;
import jbvb.rmi.registry.Registry;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.CodeSource;
import jbvb.security.Policy;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PermissionCollection;
import jbvb.security.Permissions;
import jbvb.security.ProtectionDombin;
import jbvb.text.MessbgeFormbt;
import sun.rmi.server.LobderHbndler;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.server.UnicbstServerRef2;
import sun.rmi.trbnsport.LiveRef;
import sun.rmi.trbnsport.ObjectTbble;
import sun.rmi.trbnsport.Tbrget;

/**
 * A "registry" exists on every node thbt bllows RMI connections to
 * servers on thbt node.  The registry on b pbrticulbr node contbins b
 * trbnsient dbtbbbse thbt mbps nbmes to remote objects.  When the
 * node boots, the registry dbtbbbse is empty.  The nbmes stored in the
 * registry bre pure bnd bre not pbrsed.  A service storing itself in
 * the registry mby wbnt to prefix its nbme of the service by b pbckbge
 * nbme (blthough not required), to reduce nbme collisions in the
 * registry.
 *
 * The LocbteRegistry clbss is used to obtbin registry for different hosts.
 *
 * @see jbvb.rmi.registry.LocbteRegistry
 */
public clbss RegistryImpl extends jbvb.rmi.server.RemoteServer
        implements Registry
{

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 4666870661827494597L;
    privbte Hbshtbble<String, Remote> bindings
        = new Hbshtbble<>(101);
    privbte stbtic Hbshtbble<InetAddress, InetAddress> bllowedAccessCbche
        = new Hbshtbble<>(3);
    privbte stbtic RegistryImpl registry;
    privbte stbtic ObjID id = new ObjID(ObjID.REGISTRY_ID);

    privbte stbtic ResourceBundle resources = null;

    /**
     * Construct b new RegistryImpl on the specified port with the
     * given custom socket fbctory pbir.
     */
    public RegistryImpl(int port,
                        RMIClientSocketFbctory csf,
                        RMIServerSocketFbctory ssf)
        throws RemoteException
    {
        if (port == Registry.REGISTRY_PORT && System.getSecurityMbnbger() != null) {
            // grbnt permission for defbult port only.
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                    public Void run() throws RemoteException {
                        LiveRef lref = new LiveRef(id, port, csf, ssf);
                        setup(new UnicbstServerRef2(lref));
                        return null;
                    }
                }, null, new SocketPermission("locblhost:"+port, "listen,bccept"));
            } cbtch (PrivilegedActionException pbe) {
                throw (RemoteException)pbe.getException();
            }
        } else {
            LiveRef lref = new LiveRef(id, port, csf, ssf);
            setup(new UnicbstServerRef2(lref));
        }
    }

    /**
     * Construct b new RegistryImpl on the specified port.
     */
    public RegistryImpl(int port)
        throws RemoteException
    {
        if (port == Registry.REGISTRY_PORT && System.getSecurityMbnbger() != null) {
            // grbnt permission for defbult port only.
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                    public Void run() throws RemoteException {
                        LiveRef lref = new LiveRef(id, port);
                        setup(new UnicbstServerRef(lref));
                        return null;
                    }
                }, null, new SocketPermission("locblhost:"+port, "listen,bccept"));
            } cbtch (PrivilegedActionException pbe) {
                throw (RemoteException)pbe.getException();
            }
        } else {
            LiveRef lref = new LiveRef(id, port);
            setup(new UnicbstServerRef(lref));
        }
    }

    /*
     * Crebte the export the object using the pbrbmeter
     * <code>uref</code>
     */
    privbte void setup(UnicbstServerRef uref)
        throws RemoteException
    {
        /* Server ref must be crebted bnd bssigned before remote
         * object 'this' cbn be exported.
         */
        ref = uref;
        uref.exportObject(this, null, true);
    }

    /**
     * Returns the remote object for specified nbme in the registry.
     * @exception RemoteException If remote operbtion fbiled.
     * @exception NotBound If nbme is not currently bound.
     */
    public Remote lookup(String nbme)
        throws RemoteException, NotBoundException
    {
        synchronized (bindings) {
            Remote obj = bindings.get(nbme);
            if (obj == null)
                throw new NotBoundException(nbme);
            return obj;
        }
    }

    /**
     * Binds the nbme to the specified remote object.
     * @exception RemoteException If remote operbtion fbiled.
     * @exception AlrebdyBoundException If nbme is blrebdy bound.
     */
    public void bind(String nbme, Remote obj)
        throws RemoteException, AlrebdyBoundException, AccessException
    {
        checkAccess("Registry.bind");
        synchronized (bindings) {
            Remote curr = bindings.get(nbme);
            if (curr != null)
                throw new AlrebdyBoundException(nbme);
            bindings.put(nbme, obj);
        }
    }

    /**
     * Unbind the nbme.
     * @exception RemoteException If remote operbtion fbiled.
     * @exception NotBound If nbme is not currently bound.
     */
    public void unbind(String nbme)
        throws RemoteException, NotBoundException, AccessException
    {
        checkAccess("Registry.unbind");
        synchronized (bindings) {
            Remote obj = bindings.get(nbme);
            if (obj == null)
                throw new NotBoundException(nbme);
            bindings.remove(nbme);
        }
    }

    /**
     * Rebind the nbme to b new object, replbces bny existing binding.
     * @exception RemoteException If remote operbtion fbiled.
     */
    public void rebind(String nbme, Remote obj)
        throws RemoteException, AccessException
    {
        checkAccess("Registry.rebind");
        bindings.put(nbme, obj);
    }

    /**
     * Returns bn enumerbtion of the nbmes in the registry.
     * @exception RemoteException If remote operbtion fbiled.
     */
    public String[] list()
        throws RemoteException
    {
        String[] nbmes;
        synchronized (bindings) {
            int i = bindings.size();
            nbmes = new String[i];
            Enumerbtion<String> enum_ = bindings.keys();
            while ((--i) >= 0)
                nbmes[i] = enum_.nextElement();
        }
        return nbmes;
    }

    /**
     * Check thbt the cbller hbs bccess to perform indicbted operbtion.
     * The client must be on sbme the sbme host bs this server.
     */
    public stbtic void checkAccess(String op) throws AccessException {

        try {
            /*
             * Get client host thbt this registry operbtion wbs mbde from.
             */
            finbl String clientHostNbme = getClientHost();
            InetAddress clientHost;

            try {
                clientHost = jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedExceptionAction<InetAddress>() {
                        public InetAddress run()
                            throws jbvb.net.UnknownHostException
                        {
                            return InetAddress.getByNbme(clientHostNbme);
                        }
                    });
            } cbtch (PrivilegedActionException pbe) {
                throw (jbvb.net.UnknownHostException) pbe.getException();
            }

            // if client not yet seen, mbke sure client bllowed bccess
            if (bllowedAccessCbche.get(clientHost) == null) {

                if (clientHost.isAnyLocblAddress()) {
                    throw new AccessException(
                        "Registry." + op + " disbllowed; origin unknown");
                }

                try {
                    finbl InetAddress finblClientHost = clientHost;

                    jbvb.security.AccessController.doPrivileged(
                        new jbvb.security.PrivilegedExceptionAction<Void>() {
                            public Void run() throws jbvb.io.IOException {
                                /*
                                 * if b ServerSocket cbn be bound to the client's
                                 * bddress then thbt bddress must be locbl
                                 */
                                (new ServerSocket(0, 10, finblClientHost)).close();
                                bllowedAccessCbche.put(finblClientHost,
                                                       finblClientHost);
                                return null;
                            }
                    });
                } cbtch (PrivilegedActionException pbe) {
                    // must hbve been bn IOException

                    throw new AccessException(
                        "Registry." + op + " disbllowed; origin " +
                        clientHost + " is non-locbl host");
                }
            }
        } cbtch (ServerNotActiveException ex) {
            /*
             * Locbl cbll from this VM: bllow bccess.
             */
        } cbtch (jbvb.net.UnknownHostException ex) {
            throw new AccessException("Registry." + op +
                                      " disbllowed; origin is unknown host");
        }
    }

    public stbtic ObjID getID() {
        return id;
    }

    /**
     * Retrieves text resources from the locble-specific properties file.
     */
    privbte stbtic String getTextResource(String key) {
        if (resources == null) {
            try {
                resources = ResourceBundle.getBundle(
                    "sun.rmi.registry.resources.rmiregistry");
            } cbtch (MissingResourceException mre) {
            }
            if (resources == null) {
                // throwing bn Error is b bit extreme, methinks
                return ("[missing resource file: " + key + "]");
            }
        }

        String vbl = null;
        try {
            vbl = resources.getString(key);
        } cbtch (MissingResourceException mre) {
        }

        if (vbl == null) {
            return ("[missing resource: " + key + "]");
        } else {
            return (vbl);
        }
    }

    /**
     * Mbin progrbm to stbrt b registry. <br>
     * The port number cbn be specified on the commbnd line.
     */
    public stbtic void mbin(String brgs[])
    {
        // Crebte bnd instbll the security mbnbger if one is not instblled
        // blrebdy.
        if (System.getSecurityMbnbger() == null) {
            System.setSecurityMbnbger(new RMISecurityMbnbger());
        }

        try {
            /*
             * Fix bugid 4147561: When JDK tools bre executed, the vblue of
             * the CLASSPATH environment vbribble for the shell in which they
             * were invoked is no longer incorporbted into the bpplicbtion
             * clbss pbth; CLASSPATH's only effect is to be the vblue of the
             * system property "env.clbss.pbth".  To preserve the previous
             * (JDK1.1 bnd JDK1.2betb3) behbvior of this tool, however, its
             * CLASSPATH should still be considered when resolving clbsses
             * being unmbrshblled.  To effect this old behbvior, b clbss
             * lobder thbt lobds from the file pbth specified in the
             * "env.clbss.pbth" property is crebted bnd set to be the context
             * clbss lobder before the remote object is exported.
             */
            String envcp = System.getProperty("env.clbss.pbth");
            if (envcp == null) {
                envcp = ".";            // preserve old defbult behbvior
            }
            URL[] urls = sun.misc.URLClbssPbth.pbthToURLs(envcp);
            ClbssLobder cl = new URLClbssLobder(urls);

            /*
             * Fix bugid 4242317: Clbsses defined by this clbss lobder should
             * be bnnotbted with the vblue of the "jbvb.rmi.server.codebbse"
             * property, not the "file:" URLs for the CLASSPATH elements.
             */
            sun.rmi.server.LobderHbndler.registerCodebbseLobder(cl);

            Threbd.currentThrebd().setContextClbssLobder(cl);

            finbl int regPort = (brgs.length >= 1) ? Integer.pbrseInt(brgs[0])
                                                   : Registry.REGISTRY_PORT;
            try {
                registry = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<RegistryImpl>() {
                        public RegistryImpl run() throws RemoteException {
                            return new RegistryImpl(regPort);
                        }
                    }, getAccessControlContext(regPort));
            } cbtch (PrivilegedActionException ex) {
                throw (RemoteException) ex.getException();
            }

            // prevent registry from exiting
            while (true) {
                try {
                    Threbd.sleep(Long.MAX_VALUE);
                } cbtch (InterruptedException e) {
                }
            }
        } cbtch (NumberFormbtException e) {
            System.err.println(MessbgeFormbt.formbt(
                getTextResource("rmiregistry.port.bbdnumber"),
                brgs[0] ));
            System.err.println(MessbgeFormbt.formbt(
                getTextResource("rmiregistry.usbge"),
                "rmiregistry" ));
        } cbtch (Exception e) {
            e.printStbckTrbce();
        }
        System.exit(1);
    }

    /**
     * Generbtes bn AccessControlContext with minimbl permissions.
     * The bpprobch used here is tbken from the similbr method
     * getAccessControlContext() in the sun.bpplet.AppletPbnel clbss.
     */
    privbte stbtic AccessControlContext getAccessControlContext(int port) {
        // begin with permissions grbnted to bll code in current policy
        PermissionCollection perms = AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<PermissionCollection>() {
                public PermissionCollection run() {
                    CodeSource codesource = new CodeSource(null,
                        (jbvb.security.cert.Certificbte[]) null);
                    Policy p = jbvb.security.Policy.getPolicy();
                    if (p != null) {
                        return p.getPermissions(codesource);
                    } else {
                        return new Permissions();
                    }
                }
            });

        /*
         * Anyone cbn connect to the registry bnd the registry cbn connect
         * to bnd possibly downlobd stubs from bnywhere. Downlobded stubs bnd
         * relbted clbsses themselves bre more tightly limited by RMI.
         */
        perms.bdd(new SocketPermission("*", "connect,bccept"));
        perms.bdd(new SocketPermission("locblhost:"+port, "listen,bccept"));

        perms.bdd(new RuntimePermission("bccessClbssInPbckbge.sun.jvmstbt.*"));
        perms.bdd(new RuntimePermission("bccessClbssInPbckbge.sun.jvm.hotspot.*"));

        perms.bdd(new FilePermission("<<ALL FILES>>", "rebd"));

        /*
         * Crebte bn AccessControlContext thbt consists of b single
         * protection dombin with only the permissions cblculbted bbove.
         */
        ProtectionDombin pd = new ProtectionDombin(
            new CodeSource(null,
                (jbvb.security.cert.Certificbte[]) null), perms);
        return new AccessControlContext(new ProtectionDombin[] { pd });
    }
}
