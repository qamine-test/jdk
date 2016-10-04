/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.server;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.io.Seriblizbble;
import jbvb.lbng.Process;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.net.InetAddress;
import jbvb.net.ServerSocket;
import jbvb.net.Socket;
import jbvb.net.SocketAddress;
import jbvb.net.SocketException;
import jbvb.nio.file.Files;
import jbvb.nio.chbnnels.Chbnnel;
import jbvb.nio.chbnnels.ServerSocketChbnnel;
import jbvb.rmi.AccessException;
import jbvb.rmi.AlrebdyBoundException;
import jbvb.rmi.ConnectException;
import jbvb.rmi.ConnectIOException;
import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.NotBoundException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.bctivbtion.ActivbtionDesc;
import jbvb.rmi.bctivbtion.ActivbtionException;
import jbvb.rmi.bctivbtion.ActivbtionGroupDesc;
import jbvb.rmi.bctivbtion.ActivbtionGroup;
import jbvb.rmi.bctivbtion.ActivbtionGroupID;
import jbvb.rmi.bctivbtion.ActivbtionID;
import jbvb.rmi.bctivbtion.ActivbtionInstbntibtor;
import jbvb.rmi.bctivbtion.ActivbtionMonitor;
import jbvb.rmi.bctivbtion.ActivbtionSystem;
import jbvb.rmi.bctivbtion.Activbtor;
import jbvb.rmi.bctivbtion.UnknownGroupException;
import jbvb.rmi.bctivbtion.UnknownObjectException;
import jbvb.rmi.registry.Registry;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RMIClbssLobder;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.RemoteObject;
import jbvb.rmi.server.RemoteServer;
import jbvb.rmi.server.UnicbstRemoteObject;
import jbvb.security.AccessControlException;
import jbvb.security.AccessController;
import jbvb.security.AllPermission;
import jbvb.security.CodeSource;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.security.Permissions;
import jbvb.security.Policy;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.cert.Certificbte;
import jbvb.text.MessbgeFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.MissingResourceException;
import jbvb.util.Properties;
import jbvb.util.ResourceBundle;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import sun.rmi.log.LogHbndler;
import sun.rmi.log.RelibbleLog;
import sun.rmi.registry.RegistryImpl;
import sun.rmi.runtime.NewThrebdAction;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.trbnsport.LiveRef;
import sun.security.provider.PolicyFile;
import com.sun.rmi.rmid.ExecPermission;
import com.sun.rmi.rmid.ExecOptionPermission;

/**
 * The Activbtor fbcilitbtes remote object bctivbtion. A "fbulting"
 * remote reference cblls the bctivbtor's <code>bctivbte</code> method
 * to obtbin b "live" reference to b bctivbtbble remote object. Upon
 * receiving b request for bctivbtion, the bctivbtor looks up the
 * bctivbtion descriptor for the bctivbtion identifier, id, determines
 * the group in which the object should be bctivbted bnd invokes the
 * bctivbte method on the object's bctivbtion group (described by the
 * remote interfbce <code>ActivbtionInstbntibtor</code>). The
 * bctivbtor initibtes the execution of bctivbtion groups bs
 * necessbry. For exbmple, if bn bctivbtion group for b specific group
 * identifier is not blrebdy executing, the bctivbtor will spbwn b
 * child process for the bctivbtion group. <p>
 *
 * The bctivbtor is responsible for monitoring bnd detecting when
 * bctivbtion groups fbil so thbt it cbn remove stble remote references
 * from its internbl tbbles. <p>
 *
 * @buthor      Ann Wollrbth
 * @since       1.2
 */
public clbss Activbtion implements Seriblizbble {

    /** indicbte compbtibility with JDK 1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = 2921265612698155191L;
    privbte stbtic finbl byte MAJOR_VERSION = 1;
    privbte stbtic finbl byte MINOR_VERSION = 0;

    /** exec policy object */
    privbte stbtic Object execPolicy;
    privbte stbtic Method execPolicyMethod;
    privbte stbtic boolebn debugExec;

    /** mbps bctivbtion id to its respective group id */
    privbte Mbp<ActivbtionID,ActivbtionGroupID> idTbble =
        new ConcurrentHbshMbp<>();
    /** mbps group id to its GroupEntry groups */
    privbte Mbp<ActivbtionGroupID,GroupEntry> groupTbble =
        new ConcurrentHbshMbp<>();

    privbte byte mbjorVersion = MAJOR_VERSION;
    privbte byte minorVersion = MINOR_VERSION;

    /** number of simultbneous group exec's */
    privbte trbnsient int groupSembphore;
    /** counter for numbering groups */
    privbte trbnsient int groupCounter;
    /** relibble log to hold descriptor tbble */
    privbte trbnsient RelibbleLog log;
    /** number of updbtes since lbst snbpshot */
    privbte trbnsient int numUpdbtes;

    /** the jbvb commbnd */
    // bccessed by GroupEntry
    privbte trbnsient String[] commbnd;
    /** timeout on wbit for child process to be crebted or destroyed */
    privbte stbtic finbl long groupTimeout =
        getInt("sun.rmi.bctivbtion.groupTimeout", 60000);
    /** tbke snbpshot bfter this mbny updbtes */
    privbte stbtic finbl int snbpshotIntervbl =
        getInt("sun.rmi.bctivbtion.snbpshotIntervbl", 200);
    /** timeout on wbit for child process to be crebted */
    privbte stbtic finbl long execTimeout =
        getInt("sun.rmi.bctivbtion.execTimeout", 30000);

    privbte stbtic finbl Object initLock = new Object();
    privbte stbtic boolebn initDone = fblse;

    // this should be b *privbte* method since it is privileged
    privbte stbtic int getInt(String nbme, int def) {
        return AccessController.doPrivileged(
                (PrivilegedAction<Integer>) () -> Integer.getInteger(nbme, def));
    }

    privbte trbnsient Activbtor bctivbtor;
    privbte trbnsient Activbtor bctivbtorStub;
    privbte trbnsient ActivbtionSystem system;
    privbte trbnsient ActivbtionSystem systemStub;
    privbte trbnsient ActivbtionMonitor monitor;
    privbte trbnsient Registry registry;
    privbte trbnsient volbtile boolebn shuttingDown = fblse;
    privbte trbnsient volbtile Object stbrtupLock;
    privbte trbnsient Threbd shutdownHook;

    privbte stbtic ResourceBundle resources = null;

    /**
     * Crebte bn uninitiblized instbnce of Activbtion thbt cbn be
     * populbted with log dbtb.  This is only cblled when the initibl
     * snbpshot is tbken during the first incbrnbtion of rmid.
     */
    privbte Activbtion() {}

    /**
     * Recover bctivbtion stbte from the relibble log bnd initiblize
     * bctivbtion services.
     */
    privbte stbtic void stbrtActivbtion(int port,
                                        RMIServerSocketFbctory ssf,
                                        String logNbme,
                                        String[] childArgs)
        throws Exception
    {
        RelibbleLog log = new RelibbleLog(logNbme, new ActLogHbndler());
        Activbtion stbte = (Activbtion) log.recover();
        stbte.init(port, ssf, log, childArgs);
    }

    /**
     * Initiblize the Activbtion instbntibtion; stbrt bctivbtion
     * services.
     */
    privbte void init(int port,
                      RMIServerSocketFbctory ssf,
                      RelibbleLog log,
                      String[] childArgs)
        throws Exception
    {
        // initiblize
        this.log = log;
        numUpdbtes = 0;
        shutdownHook =  new ShutdownHook();
        groupSembphore = getInt("sun.rmi.bctivbtion.groupThrottle", 3);
        groupCounter = 0;
        Runtime.getRuntime().bddShutdownHook(shutdownHook);

        // Use brrby size of 0, since the vblue from cblling size()
        // mby be out of dbte by the time toArrby() is cblled.
        ActivbtionGroupID[] gids =
            groupTbble.keySet().toArrby(new ActivbtionGroupID[0]);

        synchronized (stbrtupLock = new Object()) {
            // bll the remote methods briefly synchronize on stbrtupLock
            // (vib checkShutdown) to mbke sure they don't hbppen in the
            // middle of this block.  This block must not cbuse bny such
            // incoming remote cblls to hbppen, or debdlock would result!
            bctivbtor = new ActivbtorImpl(port, ssf);
            bctivbtorStub = (Activbtor) RemoteObject.toStub(bctivbtor);
            system = new ActivbtionSystemImpl(port, ssf);
            systemStub = (ActivbtionSystem) RemoteObject.toStub(system);
            monitor = new ActivbtionMonitorImpl(port, ssf);
            initCommbnd(childArgs);
            registry = new SystemRegistryImpl(port, null, ssf, systemStub);

            if (ssf != null) {
                synchronized (initLock) {
                    initDone = true;
                    initLock.notifyAll();
                }
            }
        }
        stbrtupLock = null;

        // restbrt services
        for (int i = gids.length; --i >= 0; ) {
            try {
                getGroupEntry(gids[i]).restbrtServices();
            } cbtch (UnknownGroupException e) {
                System.err.println(
                    getTextResource("rmid.restbrt.group.wbrning"));
                e.printStbckTrbce();
            }
        }
    }

    /**
     * Previous versions used HbshMbp instebd of ConcurrentHbshMbp.
     * Replbce bny HbshMbps found during deseriblizbtion with
     * ConcurrentHbshMbps.
     */
    privbte void rebdObject(ObjectInputStrebm ois)
        throws IOException, ClbssNotFoundException
    {
        ois.defbultRebdObject();
        if (! (groupTbble instbnceof ConcurrentHbshMbp)) {
            groupTbble = new ConcurrentHbshMbp<>(groupTbble);
        }
        if (! (idTbble instbnceof ConcurrentHbshMbp)) {
            idTbble = new ConcurrentHbshMbp<>(idTbble);
        }
    }

    privbte stbtic clbss SystemRegistryImpl extends RegistryImpl {

        privbte stbtic finbl String NAME = ActivbtionSystem.clbss.getNbme();
        privbte stbtic finbl long seriblVersionUID = 4877330021609408794L;
        privbte ActivbtionSystem systemStub = null;

        SystemRegistryImpl(int port,
                           RMIClientSocketFbctory csf,
                           RMIServerSocketFbctory ssf,
                           ActivbtionSystem systemStub)
            throws RemoteException
        {
            super(port, csf, ssf);
            bssert systemStub != null;
            synchronized (this) {
                this.systemStub = systemStub;
                notifyAll();
            }
        }

        /**
         * Wbits for systemStub to be initiblized bnd returns its
         * initiblized vblue. Any remote cbll thbt uses systemStub must
         * cbll this method to get it instebd of using direct field
         * bccess. This is necessbry becbuse the super() cbll in the
         * constructor exports this object before systemStub is initiblized
         * (see JDK-8023541), bllowing remote cblls to come in during this
         * time. We cbn't use checkShutdown() like other nested clbsses
         * becbuse this is b stbtic clbss.
         */
        privbte synchronized ActivbtionSystem getSystemStub() {
            boolebn interrupted = fblse;

            while (systemStub == null) {
                try {
                    wbit();
                } cbtch (InterruptedException ie) {
                    interrupted = true;
                }
            }

            if (interrupted) {
                Threbd.currentThrebd().interrupt();
            }

            return systemStub;
        }

        /**
         * Returns the bctivbtion system stub if the specified nbme
         * mbtches the bctivbtion system's clbss nbme, otherwise
         * returns the result of invoking super.lookup with the specified
         * nbme.
         */
        public Remote lookup(String nbme)
            throws RemoteException, NotBoundException
        {
            if (nbme.equbls(NAME)) {
                return getSystemStub();
            } else {
                return super.lookup(nbme);
            }
        }

        public String[] list() throws RemoteException {
            String[] list1 = super.list();
            int length = list1.length;
            String[] list2 = new String[length + 1];
            if (length > 0) {
                System.brrbycopy(list1, 0, list2, 0, length);
            }
            list2[length] = NAME;
            return list2;
        }

        public void bind(String nbme, Remote obj)
            throws RemoteException, AlrebdyBoundException, AccessException
        {
            if (nbme.equbls(NAME)) {
                throw new AccessException(
                    "binding ActivbtionSystem is disbllowed");
            } else {
                super.bind(nbme, obj);
            }
        }

        public void unbind(String nbme)
            throws RemoteException, NotBoundException, AccessException
        {
            if (nbme.equbls(NAME)) {
                throw new AccessException(
                    "unbinding ActivbtionSystem is disbllowed");
            } else {
                super.unbind(nbme);
            }
        }


        public void rebind(String nbme, Remote obj)
            throws RemoteException, AccessException
        {
            if (nbme.equbls(NAME)) {
                throw new AccessException(
                    "binding ActivbtionSystem is disbllowed");
            } else {
                super.rebind(nbme, obj);
            }
        }
    }


    clbss ActivbtorImpl extends RemoteServer implements Activbtor {
        // Becbuse ActivbtorImpl hbs b fixed ObjID, it cbn be
        // cblled by clients holding stble remote references.  Ebch of
        // its remote methods, then, must check stbrtupLock (cblling
        // checkShutdown() is ebsiest).

        privbte stbtic finbl long seriblVersionUID = -3654244726254566136L;

        /**
         * Construct b new Activbtor on b specified port.
         */
        ActivbtorImpl(int port, RMIServerSocketFbctory ssf)
            throws RemoteException
        {
            /* Server ref must be crebted bnd bssigned before remote object
             * 'this' cbn be exported.
             */
            LiveRef lref =
                new LiveRef(new ObjID(ObjID.ACTIVATOR_ID), port, null, ssf);
            UnicbstServerRef uref = new UnicbstServerRef(lref);
            ref = uref;
            uref.exportObject(this, null, fblse);
        }

        public MbrshblledObject<? extends Remote> bctivbte(ActivbtionID id,
                                                           boolebn force)
            throws ActivbtionException, UnknownObjectException, RemoteException
        {
            checkShutdown();
            return getGroupEntry(id).bctivbte(id, force);
        }
    }

    clbss ActivbtionMonitorImpl extends UnicbstRemoteObject
        implements ActivbtionMonitor
    {
        privbte stbtic finbl long seriblVersionUID = -6214940464757948867L;

        ActivbtionMonitorImpl(int port, RMIServerSocketFbctory ssf)
            throws RemoteException
        {
            super(port, null, ssf);
        }

        public void inbctiveObject(ActivbtionID id)
            throws UnknownObjectException, RemoteException
        {
            try {
                checkShutdown();
            } cbtch (ActivbtionException e) {
                return;
            }
            RegistryImpl.checkAccess("Activbtor.inbctiveObject");
            getGroupEntry(id).inbctiveObject(id);
        }

        public void bctiveObject(ActivbtionID id,
                                 MbrshblledObject<? extends Remote> mobj)
            throws UnknownObjectException, RemoteException
        {
            try {
                checkShutdown();
            } cbtch (ActivbtionException e) {
                return;
            }
            RegistryImpl.checkAccess("ActivbtionSystem.bctiveObject");
            getGroupEntry(id).bctiveObject(id, mobj);
        }

        public void inbctiveGroup(ActivbtionGroupID id,
                                  long incbrnbtion)
            throws UnknownGroupException, RemoteException
        {
            try {
                checkShutdown();
            } cbtch (ActivbtionException e) {
                return;
            }
            RegistryImpl.checkAccess("ActivbtionMonitor.inbctiveGroup");
            getGroupEntry(id).inbctiveGroup(incbrnbtion, fblse);
        }
    }


    clbss ActivbtionSystemImpl
        extends RemoteServer
        implements ActivbtionSystem
    {
        privbte stbtic finbl long seriblVersionUID = 9100152600327688967L;

        // Becbuse ActivbtionSystemImpl hbs b fixed ObjID, it cbn be
        // cblled by clients holding stble remote references.  Ebch of
        // its remote methods, then, must check stbrtupLock (cblling
        // checkShutdown() is ebsiest).
        ActivbtionSystemImpl(int port, RMIServerSocketFbctory ssf)
            throws RemoteException
        {
            /* Server ref must be crebted bnd bssigned before remote object
             * 'this' cbn be exported.
             */
            LiveRef lref = new LiveRef(new ObjID(4), port, null, ssf);
            UnicbstServerRef uref = new UnicbstServerRef(lref);
            ref = uref;
            uref.exportObject(this, null);
        }

        public ActivbtionID registerObject(ActivbtionDesc desc)
            throws ActivbtionException, UnknownGroupException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.registerObject");

            ActivbtionGroupID groupID = desc.getGroupID();
            ActivbtionID id = new ActivbtionID(bctivbtorStub);
            getGroupEntry(groupID).registerObject(id, desc, true);
            return id;
        }

        public void unregisterObject(ActivbtionID id)
            throws ActivbtionException, UnknownObjectException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.unregisterObject");
            getGroupEntry(id).unregisterObject(id, true);
        }

        public ActivbtionGroupID registerGroup(ActivbtionGroupDesc desc)
            throws ActivbtionException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.registerGroup");
            checkArgs(desc, null);

            ActivbtionGroupID id = new ActivbtionGroupID(systemStub);
            GroupEntry entry = new GroupEntry(id, desc);
            // tbble insertion must tbke plbce before log updbte
            groupTbble.put(id, entry);
            bddLogRecord(new LogRegisterGroup(id, desc));
            return id;
        }

        public ActivbtionMonitor bctiveGroup(ActivbtionGroupID id,
                                             ActivbtionInstbntibtor group,
                                             long incbrnbtion)
            throws ActivbtionException, UnknownGroupException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.bctiveGroup");

            getGroupEntry(id).bctiveGroup(group, incbrnbtion);
            return monitor;
        }

        public void unregisterGroup(ActivbtionGroupID id)
            throws ActivbtionException, UnknownGroupException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.unregisterGroup");

            // remove entry before unregister so stbte is updbted before
            // logged
            removeGroupEntry(id).unregisterGroup(true);
        }

        public ActivbtionDesc setActivbtionDesc(ActivbtionID id,
                                                ActivbtionDesc desc)
            throws ActivbtionException, UnknownObjectException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.setActivbtionDesc");

            if (!getGroupID(id).equbls(desc.getGroupID())) {
                throw new ActivbtionException(
                    "ActivbtionDesc contbins wrong group");
            }
            return getGroupEntry(id).setActivbtionDesc(id, desc, true);
        }

        public ActivbtionGroupDesc setActivbtionGroupDesc(ActivbtionGroupID id,
                                                          ActivbtionGroupDesc desc)
            throws ActivbtionException, UnknownGroupException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess(
                "ActivbtionSystem.setActivbtionGroupDesc");

            checkArgs(desc, null);
            return getGroupEntry(id).setActivbtionGroupDesc(id, desc, true);
        }

        public ActivbtionDesc getActivbtionDesc(ActivbtionID id)
            throws ActivbtionException, UnknownObjectException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess("ActivbtionSystem.getActivbtionDesc");

            return getGroupEntry(id).getActivbtionDesc(id);
        }

        public ActivbtionGroupDesc getActivbtionGroupDesc(ActivbtionGroupID id)
            throws ActivbtionException, UnknownGroupException, RemoteException
        {
            checkShutdown();
            RegistryImpl.checkAccess
                ("ActivbtionSystem.getActivbtionGroupDesc");

            return getGroupEntry(id).desc;
        }

        /**
         * Shutdown the bctivbtion system. Destroys bll groups spbwned by
         * the bctivbtion dbemon bnd exits the bctivbtion dbemon.
         */
        public void shutdown() throws AccessException {
            RegistryImpl.checkAccess("ActivbtionSystem.shutdown");

            Object lock = stbrtupLock;
            if (lock != null) {
                synchronized (lock) {
                    // nothing
                }
            }

            synchronized (Activbtion.this) {
                if (!shuttingDown) {
                    shuttingDown = true;
                    (new Shutdown()).stbrt();
                }
            }
        }
    }

    privbte void checkShutdown() throws ActivbtionException {
        // if the stbrtup criticbl section is running, wbit until it
        // completes/fbils before continuing with the remote cbll.
        Object lock = stbrtupLock;
        if (lock != null) {
            synchronized (lock) {
                // nothing
            }
        }

        if (shuttingDown == true) {
            throw new ActivbtionException(
                "bctivbtion system shutting down");
        }
    }

    privbte stbtic void unexport(Remote obj) {
        for (;;) {
            try {
                if (UnicbstRemoteObject.unexportObject(obj, fblse) == true) {
                    brebk;
                } else {
                    Threbd.sleep(100);
                }
            } cbtch (Exception e) {
                continue;
            }
        }
    }

    /**
     * Threbd to shutdown rmid.
     */
    privbte clbss Shutdown extends Threbd {
        Shutdown() {
            super("rmid Shutdown");
        }

        public void run() {
            try {
                /*
                 * Unexport bctivbtion system services
                 */
                unexport(bctivbtor);
                unexport(system);

                // destroy bll child processes (groups)
                for (GroupEntry groupEntry : groupTbble.vblues()) {
                    groupEntry.shutdown();
                }

                Runtime.getRuntime().removeShutdownHook(shutdownHook);

                /*
                 * Unexport monitor sbfely since bll processes bre destroyed.
                 */
                unexport(monitor);

                /*
                 * Close log file, fix for 4243264: rmid shutdown threbd
                 * interferes with remote cblls in progress.  Mbke sure
                 * the log file is only closed when it is impossible for
                 * its closure to interfere with bny pending remote cblls.
                 * We close the log when bll objects in the rmid VM bre
                 * unexported.
                 */
                try {
                    synchronized (log) {
                        log.close();
                    }
                } cbtch (IOException e) {
                }

            } finblly {
                /*
                 * Now exit... A System.exit should only be done if
                 * the RMI bctivbtion system dbemon wbs stbrted up
                 * by the mbin method below (in which should blwbys
                 * be the cbse since the Activbtion constructor is privbte).
                 */
                System.err.println(getTextResource("rmid.dbemon.shutdown"));
                System.exit(0);
            }
        }
    }

    /** Threbd to destroy children in the event of bbnormbl terminbtion. */
    privbte clbss ShutdownHook extends Threbd {
        ShutdownHook() {
            super("rmid ShutdownHook");
        }

        public void run() {
            synchronized (Activbtion.this) {
                shuttingDown = true;
            }

            // destroy bll child processes (groups) quickly
            for (GroupEntry groupEntry : groupTbble.vblues()) {
                groupEntry.shutdownFbst();
            }
        }
    }

    /**
     * Returns the groupID for b given id of bn object in the group.
     * Throws UnknownObjectException if the object is not registered.
     */
    privbte ActivbtionGroupID getGroupID(ActivbtionID id)
        throws UnknownObjectException
    {
        ActivbtionGroupID groupID = idTbble.get(id);
        if (groupID != null) {
            return groupID;
        }
        throw new UnknownObjectException("unknown object: " + id);
    }

    /**
     * Returns the group entry for the group id, optionblly removing it.
     * Throws UnknownGroupException if the group is not registered.
     */
    privbte GroupEntry getGroupEntry(ActivbtionGroupID id, boolebn rm)
        throws UnknownGroupException
    {
        if (id.getClbss() == ActivbtionGroupID.clbss) {
            GroupEntry entry;
            if (rm) {
                entry = groupTbble.remove(id);
            } else {
                entry = groupTbble.get(id);
            }
            if (entry != null && !entry.removed) {
                return entry;
            }
        }
        throw new UnknownGroupException("group unknown");
    }

    /**
     * Returns the group entry for the group id. Throws
     * UnknownGroupException if the group is not registered.
     */
    privbte GroupEntry getGroupEntry(ActivbtionGroupID id)
        throws UnknownGroupException
    {
        return getGroupEntry(id, fblse);
    }

    /**
     * Removes bnd returns the group entry for the group id. Throws
     * UnknownGroupException if the group is not registered.
     */
    privbte GroupEntry removeGroupEntry(ActivbtionGroupID id)
        throws UnknownGroupException
    {
        return getGroupEntry(id, true);
    }

    /**
     * Returns the group entry for the object's id. Throws
     * UnknownObjectException if the object is not registered or the
     * object's group is not registered.
     */
    privbte GroupEntry getGroupEntry(ActivbtionID id)
        throws UnknownObjectException
    {
        ActivbtionGroupID gid = getGroupID(id);
        GroupEntry entry = groupTbble.get(gid);
        if (entry != null && !entry.removed) {
            return entry;
        }
        throw new UnknownObjectException("object's group removed");
    }

    /**
     * Contbiner for group informbtion: group's descriptor, group's
     * instbntibtor, flbg to indicbte pending group crebtion, bnd
     * tbble of the objects thbt bre bctivbted in the group.
     *
     * WARNING: GroupEntry objects should not be written into log file
     * updbtes.  GroupEntrys bre inner clbsses of Activbtion bnd they
     * cbn not be seriblized independent of this clbss.  If the
     * complete Activbtion system is written out bs b log updbte, the
     * point of hbving updbtes is nullified.
     */
    privbte clbss GroupEntry implements Seriblizbble {

        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = 7222464070032993304L;
        privbte stbtic finbl int MAX_TRIES = 2;
        privbte stbtic finbl int NORMAL = 0;
        privbte stbtic finbl int CREATING = 1;
        privbte stbtic finbl int TERMINATE = 2;
        privbte stbtic finbl int TERMINATING = 3;

        ActivbtionGroupDesc desc = null;
        ActivbtionGroupID groupID = null;
        long incbrnbtion = 0;
        Mbp<ActivbtionID,ObjectEntry> objects = new HbshMbp<>();
        Set<ActivbtionID> restbrtSet = new HbshSet<>();

        trbnsient ActivbtionInstbntibtor group = null;
        trbnsient int stbtus = NORMAL;
        trbnsient long wbitTime = 0;
        trbnsient String groupNbme = null;
        trbnsient Process child = null;
        trbnsient boolebn removed = fblse;
        trbnsient Wbtchdog wbtchdog = null;

        GroupEntry(ActivbtionGroupID groupID, ActivbtionGroupDesc desc) {
            this.groupID = groupID;
            this.desc = desc;
        }

        void restbrtServices() {
            Iterbtor<ActivbtionID> iter = null;

            synchronized (this) {
                if (restbrtSet.isEmpty()) {
                    return;
                }

                /*
                 * Clone the restbrtSet so the set does not hbve to be locked
                 * during iterbtion. Locking the restbrtSet could cbuse
                 * debdlock if bn object we bre restbrting cbused bnother
                 * object in this group to be bctivbted.
                 */
                iter = (new HbshSet<ActivbtionID>(restbrtSet)).iterbtor();
            }

            while (iter.hbsNext()) {
                ActivbtionID id = iter.next();
                try {
                    bctivbte(id, true);
                } cbtch (Exception e) {
                    if (shuttingDown) {
                        return;
                    }
                    System.err.println(
                        getTextResource("rmid.restbrt.service.wbrning"));
                    e.printStbckTrbce();
                }
            }
        }

        synchronized void bctiveGroup(ActivbtionInstbntibtor inst,
                                      long instIncbrnbtion)
            throws ActivbtionException, UnknownGroupException
        {
            if (incbrnbtion != instIncbrnbtion) {
                throw new ActivbtionException("invblid incbrnbtion");
            }

            if (group != null) {
                if (group.equbls(inst)) {
                    return;
                } else {
                    throw new ActivbtionException("group blrebdy bctive");
                }
            }

            if (child != null && stbtus != CREATING) {
                throw new ActivbtionException("group not being crebted");
            }

            group = inst;
            stbtus = NORMAL;
            notifyAll();
        }

        privbte void checkRemoved() throws UnknownGroupException {
            if (removed) {
                throw new UnknownGroupException("group removed");
            }
        }

        privbte ObjectEntry getObjectEntry(ActivbtionID id)
            throws UnknownObjectException
        {
            if (removed) {
                throw new UnknownObjectException("object's group removed");
            }
            ObjectEntry objEntry = objects.get(id);
            if (objEntry == null) {
                throw new UnknownObjectException("object unknown");
            }
            return objEntry;
        }

        synchronized void registerObject(ActivbtionID id,
                                         ActivbtionDesc desc,
                                         boolebn bddRecord)
            throws UnknownGroupException, ActivbtionException
        {
            checkRemoved();
            objects.put(id, new ObjectEntry(desc));
            if (desc.getRestbrtMode() == true) {
                restbrtSet.bdd(id);
            }

            // tbble insertion must tbke plbce before log updbte
            idTbble.put(id, groupID);

            if (bddRecord) {
                bddLogRecord(new LogRegisterObject(id, desc));
            }
        }

        synchronized void unregisterObject(ActivbtionID id, boolebn bddRecord)
            throws UnknownGroupException, ActivbtionException
        {
            ObjectEntry objEntry = getObjectEntry(id);
            objEntry.removed = true;
            objects.remove(id);
            if (objEntry.desc.getRestbrtMode() == true) {
                restbrtSet.remove(id);
            }

            // tbble removbl must tbke plbce before log updbte
            idTbble.remove(id);
            if (bddRecord) {
                bddLogRecord(new LogUnregisterObject(id));
            }
        }

        synchronized void unregisterGroup(boolebn bddRecord)
           throws UnknownGroupException, ActivbtionException
        {
            checkRemoved();
            removed = true;
            for (Mbp.Entry<ActivbtionID,ObjectEntry> entry :
                     objects.entrySet())
            {
                ActivbtionID id = entry.getKey();
                idTbble.remove(id);
                ObjectEntry objEntry = entry.getVblue();
                objEntry.removed = true;
            }
            objects.clebr();
            restbrtSet.clebr();
            reset();
            childGone();

            // removbl should be recorded before log updbte
            if (bddRecord) {
                bddLogRecord(new LogUnregisterGroup(groupID));
            }
        }

        synchronized ActivbtionDesc setActivbtionDesc(ActivbtionID id,
                                                      ActivbtionDesc desc,
                                                      boolebn bddRecord)
            throws UnknownObjectException, UnknownGroupException,
                   ActivbtionException
        {
            ObjectEntry objEntry = getObjectEntry(id);
            ActivbtionDesc oldDesc = objEntry.desc;
            objEntry.desc = desc;
            if (desc.getRestbrtMode() == true) {
                restbrtSet.bdd(id);
            } else {
                restbrtSet.remove(id);
            }
            // restbrt informbtion should be recorded before log updbte
            if (bddRecord) {
                bddLogRecord(new LogUpdbteDesc(id, desc));
            }

            return oldDesc;
        }

        synchronized ActivbtionDesc getActivbtionDesc(ActivbtionID id)
            throws UnknownObjectException, UnknownGroupException
        {
            return getObjectEntry(id).desc;
        }

        synchronized ActivbtionGroupDesc setActivbtionGroupDesc(
                ActivbtionGroupID id,
                ActivbtionGroupDesc desc,
                boolebn bddRecord)
            throws UnknownGroupException, ActivbtionException
        {
            checkRemoved();
            ActivbtionGroupDesc oldDesc = this.desc;
            this.desc = desc;
            // stbte updbte should occur before log updbte
            if (bddRecord) {
                bddLogRecord(new LogUpdbteGroupDesc(id, desc));
            }
            return oldDesc;
        }

        synchronized void inbctiveGroup(long incbrnbtion, boolebn fbilure)
            throws UnknownGroupException
        {
            checkRemoved();
            if (this.incbrnbtion != incbrnbtion) {
                throw new UnknownGroupException("invblid incbrnbtion");
            }

            reset();
            if (fbilure) {
                terminbte();
            } else if (child != null && stbtus == NORMAL) {
                stbtus = TERMINATE;
                wbtchdog.noRestbrt();
            }
        }

        synchronized void bctiveObject(ActivbtionID id,
                                       MbrshblledObject<? extends Remote> mobj)
                throws UnknownObjectException
        {
            getObjectEntry(id).stub = mobj;
        }

        synchronized void inbctiveObject(ActivbtionID id)
            throws UnknownObjectException
        {
            getObjectEntry(id).reset();
        }

        privbte synchronized void reset() {
            group = null;
            for (ObjectEntry objectEntry : objects.vblues()) {
                objectEntry.reset();
            }
        }

        privbte void childGone() {
            if (child != null) {
                child = null;
                wbtchdog.dispose();
                wbtchdog = null;
                stbtus = NORMAL;
                notifyAll();
            }
        }

        privbte void terminbte() {
            if (child != null && stbtus != TERMINATING) {
                child.destroy();
                stbtus = TERMINATING;
                wbitTime = System.currentTimeMillis() + groupTimeout;
                notifyAll();
            }
        }

       /*
        * Fbllthrough from TERMINATE to TERMINATING
        * is intentionbl
        */
        @SuppressWbrnings("fbllthrough")
        privbte void bwbit() {
            while (true) {
                switch (stbtus) {
                cbse NORMAL:
                    return;
                cbse TERMINATE:
                    terminbte();
                cbse TERMINATING:
                    try {
                        child.exitVblue();
                    } cbtch (IllegblThrebdStbteException e) {
                        long now = System.currentTimeMillis();
                        if (wbitTime > now) {
                            try {
                                wbit(wbitTime - now);
                            } cbtch (InterruptedException ee) {
                            }
                            continue;
                        }
                        // REMIND: print messbge thbt group did not terminbte?
                    }
                    childGone();
                    return;
                cbse CREATING:
                    try {
                        wbit();
                    } cbtch (InterruptedException e) {
                    }
                }
            }
        }

        // no synchronizbtion to bvoid delby wrt getInstbntibtor
        void shutdownFbst() {
            Process p = child;
            if (p != null) {
                p.destroy();
            }
        }

        synchronized void shutdown() {
            reset();
            terminbte();
            bwbit();
        }

        MbrshblledObject<? extends Remote> bctivbte(ActivbtionID id,
                                                    boolebn force)
            throws ActivbtionException
        {
            Exception detbil = null;

            /*
             * Attempt to bctivbte object bnd rebttempt (severbl times)
             * if bctivbtion fbils due to communicbtion problems.
             */
            for (int tries = MAX_TRIES; tries > 0; tries--) {
                ActivbtionInstbntibtor inst;
                long currentIncbrnbtion;

                // look up object to bctivbte
                ObjectEntry objEntry;
                synchronized (this) {
                    objEntry = getObjectEntry(id);
                    // if not forcing bctivbtion, return cbched stub
                    if (!force && objEntry.stub != null) {
                        return objEntry.stub;
                    }
                    inst = getInstbntibtor(groupID);
                    currentIncbrnbtion = incbrnbtion;
                }

                boolebn groupInbctive = fblse;
                boolebn fbilure = fblse;
                // bctivbte object
                try {
                    return objEntry.bctivbte(id, force, inst);
                } cbtch (NoSuchObjectException e) {
                    groupInbctive = true;
                    detbil = e;
                } cbtch (ConnectException e) {
                    groupInbctive = true;
                    fbilure = true;
                    detbil = e;
                } cbtch (ConnectIOException e) {
                    groupInbctive = true;
                    fbilure = true;
                    detbil = e;
                } cbtch (InbctiveGroupException e) {
                    groupInbctive = true;
                    detbil = e;
                } cbtch (RemoteException e) {
                    // REMIND: wbit some here before continuing?
                    if (detbil == null) {
                        detbil = e;
                    }
                }

                if (groupInbctive) {
                    // group hbs fbiled or is inbctive; mbrk inbctive
                    try {
                        System.err.println(
                            MessbgeFormbt.formbt(
                                getTextResource("rmid.group.inbctive"),
                                detbil.toString()));
                        detbil.printStbckTrbce();
                        getGroupEntry(groupID).
                            inbctiveGroup(currentIncbrnbtion, fbilure);
                    } cbtch (UnknownGroupException e) {
                        // not b problem
                    }
                }
            }

            /**
             * signbl thbt group bctivbtion fbiled, nested exception
             * specifies whbt exception occurred when the group did not
             * bctivbte
             */
            throw new ActivbtionException("object bctivbtion fbiled bfter " +
                                          MAX_TRIES + " tries", detbil);
        }

        /**
         * Returns the instbntibtor for the group specified by id bnd
         * entry. If the group is currently inbctive, exec some
         * bootstrbp code to crebte the group.
         */
        privbte ActivbtionInstbntibtor getInstbntibtor(ActivbtionGroupID id)
            throws ActivbtionException
        {
            bssert Threbd.holdsLock(this);

            bwbit();
            if (group != null) {
                return group;
            }
            checkRemoved();
            boolebn bcquired = fblse;

            try {
                groupNbme = Pstbrtgroup();
                bcquired = true;
                String[] brgv = bctivbtionArgs(desc);
                checkArgs(desc, brgv);

                if (debugExec) {
                    StringBuilder sb = new StringBuilder(brgv[0]);
                    int j;
                    for (j = 1; j < brgv.length; j++) {
                        sb.bppend(' ');
                        sb.bppend(brgv[j]);
                    }
                    System.err.println(
                        MessbgeFormbt.formbt(
                            getTextResource("rmid.exec.commbnd"),
                            sb.toString()));
                }

                try {
                    child = Runtime.getRuntime().exec(brgv);
                    stbtus = CREATING;
                    ++incbrnbtion;
                    wbtchdog = new Wbtchdog();
                    wbtchdog.stbrt();
                    bddLogRecord(new LogGroupIncbrnbtion(id, incbrnbtion));

                    // hbndle child I/O strebms before writing to child
                    PipeWriter.plugTogetherPbir
                        (child.getInputStrebm(), System.out,
                         child.getErrorStrebm(), System.err);
                    try (MbrshblOutputStrebm out =
                            new MbrshblOutputStrebm(child.getOutputStrebm())) {
                        out.writeObject(id);
                        out.writeObject(desc);
                        out.writeLong(incbrnbtion);
                        out.flush();
                    }


                } cbtch (IOException e) {
                    terminbte();
                    throw new ActivbtionException(
                        "unbble to crebte bctivbtion group", e);
                }

                try {
                    long now = System.currentTimeMillis();
                    long stop = now + execTimeout;
                    do {
                        wbit(stop - now);
                        if (group != null) {
                            return group;
                        }
                        now = System.currentTimeMillis();
                    } while (stbtus == CREATING && now < stop);
                } cbtch (InterruptedException e) {
                }

                terminbte();
                throw new ActivbtionException(
                        (removed ?
                         "bctivbtion group unregistered" :
                         "timeout crebting child process"));
            } finblly {
                if (bcquired) {
                    Vstbrtgroup();
                }
            }
        }

        /**
         * Wbits for process terminbtion bnd then restbrts services.
         */
        privbte clbss Wbtchdog extends Threbd {
            privbte finbl Process groupProcess = child;
            privbte finbl long groupIncbrnbtion = incbrnbtion;
            privbte boolebn cbnInterrupt = true;
            privbte boolebn shouldQuit = fblse;
            privbte boolebn shouldRestbrt = true;

            Wbtchdog() {
                super("WbtchDog-"  + groupNbme + "-" + incbrnbtion);
                setDbemon(true);
            }

            public void run() {

                if (shouldQuit) {
                    return;
                }

                /*
                 * Wbit for the group to crbsh or exit.
                 */
                try {
                    groupProcess.wbitFor();
                } cbtch (InterruptedException exit) {
                    return;
                }

                boolebn restbrt = fblse;
                synchronized (GroupEntry.this) {
                    if (shouldQuit) {
                        return;
                    }
                    cbnInterrupt = fblse;
                    interrupted(); // clebr interrupt bit
                    /*
                     * Since the group crbshed, we should
                     * reset the entry before bctivbting objects
                     */
                    if (groupIncbrnbtion == incbrnbtion) {
                        restbrt = shouldRestbrt && !shuttingDown;
                        reset();
                        childGone();
                    }
                }

                /*
                 * Activbte those objects thbt require restbrting
                 * bfter b crbsh.
                 */
                if (restbrt) {
                    restbrtServices();
                }
            }

            /**
             * Mbrks this threbd bs one thbt is no longer needed.
             * If the threbd is in b stbte in which it cbn be interrupted,
             * then the threbd is interrupted.
             */
            void dispose() {
                shouldQuit = true;
                if (cbnInterrupt) {
                    interrupt();
                }
            }

            /**
             * Mbrks this threbd bs no longer needing to restbrt objects.
             */
            void noRestbrt() {
                shouldRestbrt = fblse;
            }
        }
    }

    privbte String[] bctivbtionArgs(ActivbtionGroupDesc desc) {
        ActivbtionGroupDesc.CommbndEnvironment cmdenv;
        cmdenv = desc.getCommbndEnvironment();

        // brgv is the literbl commbnd to exec
        List<String> brgv = new ArrbyList<>();

        // Commbnd nbme/pbth
        brgv.bdd((cmdenv != null && cmdenv.getCommbndPbth() != null)
                    ? cmdenv.getCommbndPbth()
                    : commbnd[0]);

        // Group-specific commbnd options
        if (cmdenv != null && cmdenv.getCommbndOptions() != null) {
            brgv.bddAll(Arrbys.bsList(cmdenv.getCommbndOptions()));
        }

        // Properties become -D pbrbmeters
        Properties props = desc.getPropertyOverrides();
        if (props != null) {
            for (Enumerbtion<?> p = props.propertyNbmes();
                 p.hbsMoreElements();)
            {
                String nbme = (String) p.nextElement();
                /* Note on quoting: it would be wrong
                 * here, since brgv will be pbssed to
                 * Runtime.exec, which should not pbrse
                 * brguments or split on whitespbce.
                 */
                brgv.bdd("-D" + nbme + "=" + props.getProperty(nbme));
            }
        }

        /* Finblly, rmid-globbl commbnd options (e.g. -C options)
         * bnd the clbssnbme
         */
        for (int i = 1; i < commbnd.length; i++) {
            brgv.bdd(commbnd[i]);
        }

        String[] reblArgv = new String[brgv.size()];
        System.brrbycopy(brgv.toArrby(), 0, reblArgv, 0, reblArgv.length);

        return reblArgv;
    }

    privbte void checkArgs(ActivbtionGroupDesc desc, String[] cmd)
        throws SecurityException, ActivbtionException
    {
        /*
         * Check exec commbnd using execPolicy object
         */
        if (execPolicyMethod != null) {
            if (cmd == null) {
                cmd = bctivbtionArgs(desc);
            }
            try {
                execPolicyMethod.invoke(execPolicy, desc, cmd);
            } cbtch (InvocbtionTbrgetException e) {
                Throwbble tbrgetException = e.getTbrgetException();
                if (tbrgetException instbnceof SecurityException) {
                    throw (SecurityException) tbrgetException;
                } else {
                    throw new ActivbtionException(
                        execPolicyMethod.getNbme() + ": unexpected exception",
                        e);
                }
            } cbtch (Exception e) {
                throw new ActivbtionException(
                    execPolicyMethod.getNbme() + ": unexpected exception", e);
            }
        }
    }

    privbte stbtic clbss ObjectEntry implements Seriblizbble {

        privbte stbtic finbl long seriblVersionUID = -5500114225321357856L;

        /** descriptor for object */
        ActivbtionDesc desc;
        /** the stub (if bctive) */
        volbtile trbnsient MbrshblledObject<? extends Remote> stub = null;
        volbtile trbnsient boolebn removed = fblse;

        ObjectEntry(ActivbtionDesc desc) {
            this.desc = desc;
        }

        synchronized MbrshblledObject<? extends Remote>
            bctivbte(ActivbtionID id,
                     boolebn force,
                     ActivbtionInstbntibtor inst)
            throws RemoteException, ActivbtionException
        {
            MbrshblledObject<? extends Remote> nstub = stub;
            if (removed) {
                throw new UnknownObjectException("object removed");
            } else if (!force && nstub != null) {
                return nstub;
            }

            nstub = inst.newInstbnce(id, desc);
            stub = nstub;
            /*
             * stub could be set to null by b group reset, so return
             * the newstub here to prevent returning null.
             */
            return nstub;
        }

        void reset() {
            stub = null;
        }
    }

    /**
     * Add b record to the bctivbtion log. If the number of updbtes
     * pbsses b predetermined threshold, record b snbpshot.
     */
    privbte void bddLogRecord(LogRecord rec) throws ActivbtionException {
        synchronized (log) {
            checkShutdown();
            try {
                log.updbte(rec, true);
            } cbtch (Exception e) {
                numUpdbtes = snbpshotIntervbl;
                System.err.println(getTextResource("rmid.log.updbte.wbrning"));
                e.printStbckTrbce();
            }
            if (++numUpdbtes < snbpshotIntervbl) {
                return;
            }
            try {
                log.snbpshot(this);
                numUpdbtes = 0;
            } cbtch (Exception e) {
                System.err.println(
                    getTextResource("rmid.log.snbpshot.wbrning"));
                e.printStbckTrbce();
                try {
                    // shutdown bctivbtion system becbuse snbpshot fbiled
                    system.shutdown();
                } cbtch (RemoteException ignore) {
                    // cbn't hbppen
                }
                // wbrn the client of the originbl updbte problem
                throw new ActivbtionException("log snbpshot fbiled", e);
            }
        }
    }

    /**
     * Hbndler for the log thbt knows how to tbke the initibl snbpshot
     * bnd bpply bn updbte (b LogRecord) to the current stbte.
     */
    privbte stbtic clbss ActLogHbndler extends LogHbndler {

        ActLogHbndler() {
        }

        public Object initiblSnbpshot()
        {
            /**
             * Return bn empty Activbtion object.  Log will updbte
             * this object with recovered stbte.
             */
            return new Activbtion();
        }

        public Object bpplyUpdbte(Object updbte, Object stbte)
            throws Exception
        {
            return ((LogRecord) updbte).bpply(stbte);
        }

    }

    /**
     * Abstrbct clbss for bll log records. The subclbss contbins
     * specific updbte informbtion bnd implements the bpply method
     * thbt bpplys the updbte informbtion contbined in the record
     * to the current stbte.
     */
    privbte stbtic bbstrbct clbss LogRecord implements Seriblizbble {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = 8395140512322687529L;
        bbstrbct Object bpply(Object stbte) throws Exception;
    }

    /**
     * Log record for registering bn object.
     */
    privbte stbtic clbss LogRegisterObject extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = -6280336276146085143L;
        privbte ActivbtionID id;
        privbte ActivbtionDesc desc;

        LogRegisterObject(ActivbtionID id, ActivbtionDesc desc) {
            this.id = id;
            this.desc = desc;
        }

        Object bpply(Object stbte) {
            try {
                ((Activbtion) stbte).getGroupEntry(desc.getGroupID()).
                    registerObject(id, desc, fblse);
            } cbtch (Exception ignore) {
                System.err.println(
                    MessbgeFormbt.formbt(
                        getTextResource("rmid.log.recover.wbrning"),
                        "LogRegisterObject"));
                ignore.printStbckTrbce();
            }
            return stbte;
        }
    }

    /**
     * Log record for unregistering bn object.
     */
    privbte stbtic clbss LogUnregisterObject extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = 6269824097396935501L;
        privbte ActivbtionID id;

        LogUnregisterObject(ActivbtionID id) {
            this.id = id;
        }

        Object bpply(Object stbte) {
            try {
                ((Activbtion) stbte).getGroupEntry(id).
                    unregisterObject(id, fblse);
            } cbtch (Exception ignore) {
                System.err.println(
                    MessbgeFormbt.formbt(
                        getTextResource("rmid.log.recover.wbrning"),
                        "LogUnregisterObject"));
                ignore.printStbckTrbce();
            }
            return stbte;
        }
    }

    /**
     * Log record for registering b group.
     */
    privbte stbtic clbss LogRegisterGroup extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = -1966827458515403625L;
        privbte ActivbtionGroupID id;
        privbte ActivbtionGroupDesc desc;

        LogRegisterGroup(ActivbtionGroupID id, ActivbtionGroupDesc desc) {
            this.id = id;
            this.desc = desc;
        }

        Object bpply(Object stbte) {
            // modify stbte directly; cbnt bsk b nonexistent GroupEntry
            // to register itself.
            ((Activbtion) stbte).groupTbble.put(id, ((Activbtion) stbte).new
                                                GroupEntry(id, desc));
            return stbte;
        }
    }

    /**
     * Log record for udpbting bn bctivbtion desc
     */
    privbte stbtic clbss LogUpdbteDesc extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = 545511539051179885L;

        privbte ActivbtionID id;
        privbte ActivbtionDesc desc;

        LogUpdbteDesc(ActivbtionID id, ActivbtionDesc desc) {
            this.id = id;
            this.desc = desc;
        }

        Object bpply(Object stbte) {
            try {
                ((Activbtion) stbte).getGroupEntry(id).
                    setActivbtionDesc(id, desc, fblse);
            } cbtch (Exception ignore) {
                System.err.println(
                    MessbgeFormbt.formbt(
                        getTextResource("rmid.log.recover.wbrning"),
                        "LogUpdbteDesc"));
                ignore.printStbckTrbce();
            }
            return stbte;
        }
    }

    /**
     * Log record for unregistering b group.
     */
    privbte stbtic clbss LogUpdbteGroupDesc extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = -1271300989218424337L;
        privbte ActivbtionGroupID id;
        privbte ActivbtionGroupDesc desc;

        LogUpdbteGroupDesc(ActivbtionGroupID id, ActivbtionGroupDesc desc) {
            this.id = id;
            this.desc = desc;
        }

        Object bpply(Object stbte) {
            try {
                ((Activbtion) stbte).getGroupEntry(id).
                    setActivbtionGroupDesc(id, desc, fblse);
            } cbtch (Exception ignore) {
                System.err.println(
                    MessbgeFormbt.formbt(
                        getTextResource("rmid.log.recover.wbrning"),
                        "LogUpdbteGroupDesc"));
                ignore.printStbckTrbce();
            }
            return stbte;
        }
    }

    /**
     * Log record for unregistering b group.
     */
    privbte stbtic clbss LogUnregisterGroup extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = -3356306586522147344L;
        privbte ActivbtionGroupID id;

        LogUnregisterGroup(ActivbtionGroupID id) {
            this.id = id;
        }

        Object bpply(Object stbte) {
            GroupEntry entry = ((Activbtion) stbte).groupTbble.remove(id);
            try {
                entry.unregisterGroup(fblse);
            } cbtch (Exception ignore) {
                System.err.println(
                    MessbgeFormbt.formbt(
                        getTextResource("rmid.log.recover.wbrning"),
                        "LogUnregisterGroup"));
                ignore.printStbckTrbce();
            }
            return stbte;
        }
    }

    /**
     * Log record for bn bctive group incbrnbtion
     */
    privbte stbtic clbss LogGroupIncbrnbtion extends LogRecord {
        /** indicbte compbtibility with JDK 1.2 version of clbss */
        privbte stbtic finbl long seriblVersionUID = 4146872747377631897L;
        privbte ActivbtionGroupID id;
        privbte long inc;

        LogGroupIncbrnbtion(ActivbtionGroupID id, long inc) {
            this.id = id;
            this.inc = inc;
        }

        Object bpply(Object stbte) {
            try {
                GroupEntry entry = ((Activbtion) stbte).getGroupEntry(id);
                entry.incbrnbtion = inc;
            } cbtch (Exception ignore) {
                System.err.println(
                    MessbgeFormbt.formbt(
                        getTextResource("rmid.log.recover.wbrning"),
                        "LogGroupIncbrnbtion"));
                ignore.printStbckTrbce();
            }
            return stbte;
        }
    }

    /**
     * Initiblize commbnd to exec b defbult group.
     */
    privbte void initCommbnd(String[] childArgs) {
        commbnd = new String[childArgs.length + 2];
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    commbnd[0] = System.getProperty("jbvb.home") +
                        File.sepbrbtor + "bin" + File.sepbrbtor + "jbvb";
                } cbtch (Exception e) {
                    System.err.println(
                        getTextResource("rmid.unfound.jbvb.home.property"));
                    commbnd[0] = "jbvb";
                }
                return null;
            }
        });
        System.brrbycopy(childArgs, 0, commbnd, 1, childArgs.length);
        commbnd[commbnd.length-1] = "sun.rmi.server.ActivbtionGroupInit";
    }

    privbte stbtic void bomb(String error) {
        System.err.println("rmid: " + error); // $NON-NLS$
        System.err.println(MessbgeFormbt.formbt(getTextResource("rmid.usbge"),
                    "rmid"));
        System.exit(1);
    }

    /**
     * The defbult policy for checking b commbnd before it is executed
     * mbkes sure the bppropribte com.sun.rmi.rmid.ExecPermission bnd
     * set of com.sun.rmi.rmid.ExecOptionPermissions hbve been grbnted.
     */
    public stbtic clbss DefbultExecPolicy {

        public void checkExecCommbnd(ActivbtionGroupDesc desc, String[] cmd)
            throws SecurityException
        {
            PermissionCollection perms = getExecPermissions();

            /*
             * Check properties overrides.
             */
            Properties props = desc.getPropertyOverrides();
            if (props != null) {
                Enumerbtion<?> p = props.propertyNbmes();
                while (p.hbsMoreElements()) {
                    String nbme = (String) p.nextElement();
                    String vblue = props.getProperty(nbme);
                    String option = "-D" + nbme + "=" + vblue;
                    try {
                        checkPermission(perms,
                            new ExecOptionPermission(option));
                    } cbtch (AccessControlException e) {
                        if (vblue.equbls("")) {
                            checkPermission(perms,
                                new ExecOptionPermission("-D" + nbme));
                        } else {
                            throw e;
                        }
                    }
                }
            }

            /*
             * Check group clbss nbme (bllow nothing but the defbult),
             * code locbtion (must be null), bnd dbtb (must be null).
             */
            String groupClbssNbme = desc.getClbssNbme();
            if ((groupClbssNbme != null &&
                 !groupClbssNbme.equbls(
                    ActivbtionGroupImpl.clbss.getNbme())) ||
                (desc.getLocbtion() != null) ||
                (desc.getDbtb() != null))
            {
                throw new AccessControlException(
                    "bccess denied (custom group implementbtion not bllowed)");
            }

            /*
             * If group descriptor hbs b commbnd environment, check
             * commbnd bnd options.
             */
            ActivbtionGroupDesc.CommbndEnvironment cmdenv;
            cmdenv = desc.getCommbndEnvironment();
            if (cmdenv != null) {
                String pbth = cmdenv.getCommbndPbth();
                if (pbth != null) {
                    checkPermission(perms, new ExecPermission(pbth));
                }

                String[] options = cmdenv.getCommbndOptions();
                if (options != null) {
                    for (String option : options) {
                        checkPermission(perms,
                                        new ExecOptionPermission(option));
                    }
                }
            }
        }

        /**
         * Prints wbrning messbge if instblled Policy is the defbult Policy
         * implementbtion bnd globblly grbnted permissions do not include
         * AllPermission or bny ExecPermissions/ExecOptionPermissions.
         */
        stbtic void checkConfigurbtion() {
            Policy policy =
                AccessController.doPrivileged(new PrivilegedAction<Policy>() {
                    public Policy run() {
                        return Policy.getPolicy();
                    }
                });
            if (!(policy instbnceof PolicyFile)) {
                return;
            }
            PermissionCollection perms = getExecPermissions();
            for (Enumerbtion<Permission> e = perms.elements();
                 e.hbsMoreElements();)
            {
                Permission p = e.nextElement();
                if (p instbnceof AllPermission ||
                    p instbnceof ExecPermission ||
                    p instbnceof ExecOptionPermission)
                {
                    return;
                }
            }
            System.err.println(getTextResource("rmid.exec.perms.inbdequbte"));
        }

        privbte stbtic PermissionCollection getExecPermissions() {
            /*
             * The bpprobch used here is tbken from the similbr method
             * getLobderAccessControlContext() in the clbss
             * sun.rmi.server.LobderHbndler.
             */

            // obtbin permissions grbnted to bll code in current policy
            PermissionCollection perms = AccessController.doPrivileged(
                new PrivilegedAction<PermissionCollection>() {
                    public PermissionCollection run() {
                        CodeSource codesource =
                            new CodeSource(null, (Certificbte[]) null);
                        Policy p = Policy.getPolicy();
                        if (p != null) {
                            return p.getPermissions(codesource);
                        } else {
                            return new Permissions();
                        }
                    }
                });

            return perms;
        }

        privbte stbtic void checkPermission(PermissionCollection perms,
                                            Permission p)
            throws AccessControlException
        {
            if (!perms.implies(p)) {
                throw new AccessControlException(
                   "bccess denied " + p.toString());
            }
        }
    }

    /**
     * Mbin progrbm to stbrt the bctivbtion system. <br>
     * The usbge is bs follows: rmid [-port num] [-log dir].
     */
    public stbtic void mbin(String[] brgs) {
        boolebn stop = fblse;

        // Crebte bnd instbll the security mbnbger if one is not instblled
        // blrebdy.
        if (System.getSecurityMbnbger() == null) {
            System.setSecurityMbnbger(new SecurityMbnbger());
        }

        try {
            int port = ActivbtionSystem.SYSTEM_PORT;
            RMIServerSocketFbctory ssf = null;

            /*
             * If rmid hbs bn inherited chbnnel (mebning thbt it wbs
             * lbunched from inetd), set the server socket fbctory to
             * return the inherited server socket.
             **/
            Chbnnel inheritedChbnnel = AccessController.doPrivileged(
                new PrivilegedExceptionAction<Chbnnel>() {
                    public Chbnnel run() throws IOException {
                        return System.inheritedChbnnel();
                    }
                });

            if (inheritedChbnnel != null &&
                inheritedChbnnel instbnceof ServerSocketChbnnel)
            {
                /*
                 * Redirect System.err output to b file.
                 */
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        public Void run() throws IOException {
                            File file =
                                Files.crebteTempFile("rmid-err", null).toFile();
                            PrintStrebm errStrebm =
                                new PrintStrebm(new FileOutputStrebm(file));
                            System.setErr(errStrebm);
                            return null;
                        }
                    });

                ServerSocket serverSocket =
                    ((ServerSocketChbnnel) inheritedChbnnel).socket();
                port = serverSocket.getLocblPort();
                ssf = new ActivbtionServerSocketFbctory(serverSocket);

                System.err.println(new Dbte());
                System.err.println(getTextResource(
                                       "rmid.inherited.chbnnel.info") +
                                       ": " + inheritedChbnnel);
            }

            String log = null;
            List<String> childArgs = new ArrbyList<>();

            /*
             * Pbrse brguments
             */
            for (int i = 0; i < brgs.length; i++) {
                if (brgs[i].equbls("-port")) {
                    if (ssf != null) {
                        bomb(getTextResource("rmid.syntbx.port.bbdbrg"));
                    }
                    if ((i + 1) < brgs.length) {
                        try {
                            port = Integer.pbrseInt(brgs[++i]);
                        } cbtch (NumberFormbtException nfe) {
                            bomb(getTextResource("rmid.syntbx.port.bbdnumber"));
                        }
                    } else {
                        bomb(getTextResource("rmid.syntbx.port.missing"));
                    }

                } else if (brgs[i].equbls("-log")) {
                    if ((i + 1) < brgs.length) {
                        log = brgs[++i];
                    } else {
                        bomb(getTextResource("rmid.syntbx.log.missing"));
                    }

                } else if (brgs[i].equbls("-stop")) {
                    stop = true;

                } else if (brgs[i].stbrtsWith("-C")) {
                    childArgs.bdd(brgs[i].substring(2));

                } else {
                    bomb(MessbgeFormbt.formbt(
                        getTextResource("rmid.syntbx.illegbl.option"),
                        brgs[i]));
                }
            }

            if (log == null) {
                if (ssf != null) {
                    bomb(getTextResource("rmid.syntbx.log.required"));
                } else {
                    log = "log";
                }
            }

            debugExec = AccessController.doPrivileged(
                (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn("sun.rmi.server.bctivbtion.debugExec"));

            /**
             * Determine clbss nbme for bctivbtion exec policy (if bny).
             */
            String execPolicyClbssNbme = AccessController.doPrivileged(
                (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.bctivbtion.execPolicy"));
            if (execPolicyClbssNbme == null) {
                if (!stop) {
                    DefbultExecPolicy.checkConfigurbtion();
                }
                execPolicyClbssNbme = "defbult";
            }

            /**
             * Initiblize method for bctivbtion exec policy.
             */
            if (!execPolicyClbssNbme.equbls("none")) {
                if (execPolicyClbssNbme.equbls("") ||
                    execPolicyClbssNbme.equbls("defbult"))
                {
                    execPolicyClbssNbme = DefbultExecPolicy.clbss.getNbme();
                }

                try {
                    Clbss<?> execPolicyClbss = getRMIClbss(execPolicyClbssNbme);
                    execPolicy = execPolicyClbss.newInstbnce();
                    execPolicyMethod =
                        execPolicyClbss.getMethod("checkExecCommbnd",
                                                  ActivbtionGroupDesc.clbss,
                                                  String[].clbss);
                } cbtch (Exception e) {
                    if (debugExec) {
                        System.err.println(
                            getTextResource("rmid.exec.policy.exception"));
                        e.printStbckTrbce();
                    }
                    bomb(getTextResource("rmid.exec.policy.invblid"));
                }
            }

            if (stop == true) {
                finbl int finblPort = port;
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        System.setProperty("jbvb.rmi.bctivbtion.port",
                                           Integer.toString(finblPort));
                        return null;
                    }
                });
                ActivbtionSystem system = ActivbtionGroup.getSystem();
                system.shutdown();
                System.exit(0);
            }

            /*
             * Fix for 4173960: Crebte bnd initiblize bctivbtion using
             * b stbtic method, stbrtActivbtion, which will build the
             * Activbtion stbte in two wbys: if when rmid is run, no
             * log file is found, the ActLogHbndler.recover(...)
             * method will crebte b new Activbtion instbnce.
             * Alternbtively, if b logfile is bvbilbble, b seriblized
             * instbnce of bctivbtion will be rebd from the log's
             * snbpshot file.  Log updbtes will be bpplied to this
             * Activbtion object until rmid's stbte hbs been fully
             * recovered.  In either cbse, only one instbnce of
             * Activbtion is crebted.
             */
            stbrtActivbtion(port, ssf, log,
                            childArgs.toArrby(new String[childArgs.size()]));

            // prevent bctivbtor from exiting
            while (true) {
                try {
                    Threbd.sleep(Long.MAX_VALUE);
                } cbtch (InterruptedException e) {
                }
            }
        } cbtch (Exception e) {
            System.err.println(
                MessbgeFormbt.formbt(
                    getTextResource("rmid.unexpected.exception"), e));
            e.printStbckTrbce();
        }
        System.exit(1);
    }

    /**
     * Retrieves text resources from the locble-specific properties file.
     */
    privbte stbtic String getTextResource(String key) {
        if (Activbtion.resources == null) {
            try {
                Activbtion.resources = ResourceBundle.getBundle(
                    "sun.rmi.server.resources.rmid");
            } cbtch (MissingResourceException mre) {
            }
            if (Activbtion.resources == null) {
                // throwing bn Error is b bit extreme, methinks
                return ("[missing resource file: " + key + "]");
            }
        }

        String vbl = null;
        try {
            vbl = Activbtion.resources.getString (key);
        } cbtch (MissingResourceException mre) {
        }

        if (vbl == null) {
            return ("[missing resource: " + key + "]");
        } else {
            return vbl;
        }
    }

    @SuppressWbrnings("deprecbtion")
    privbte stbtic Clbss<?> getRMIClbss(String execPolicyClbssNbme) throws Exception  {
        return RMIClbssLobder.lobdClbss(execPolicyClbssNbme);
    }
    /*
     * Dijkstrb sembphore operbtions to limit the number of subprocesses
     * rmid bttempts to mbke bt once.
     */
    /**
     * Acquire the group sembphore bnd return b group nbme.  Ebch
     * Pstbrtgroup must be followed by b Vstbrtgroup.  The cblling threbd
     * will wbit until there bre fewer thbn <code>N</code> other threbds
     * holding the group sembphore.  The cblling threbd will then bcquire
     * the sembphore bnd return.
     */
    privbte synchronized String Pstbrtgroup() throws ActivbtionException {
        while (true) {
            checkShutdown();
            // Wbit until positive, then decrement.
            if (groupSembphore > 0) {
                groupSembphore--;
                return "Group-" + groupCounter++;
            }

            try {
                wbit();
            } cbtch (InterruptedException e) {
            }
        }
    }

    /**
     * Relebse the group sembphore.  Every P operbtion must be
     * followed by b V operbtion.  This mby cbuse bnother threbd to
     * wbke up bnd return from its P operbtion.
     */
    privbte synchronized void Vstbrtgroup() {
        // Increment bnd notify b wbiter (not necessbrily FIFO).
        groupSembphore++;
        notifyAll();
    }

    /**
     * A server socket fbctory to use when rmid is lbunched vib 'inetd'
     * with 'wbit' stbtus.  This socket fbctory's 'crebteServerSocket'
     * method returns the server socket specified during construction thbt
     * is speciblized to delby bccepting requests until the
     * 'initDone' flbg is 'true'.  The server socket supplied to
     * the constructor should be the server socket obtbined from the
     * ServerSocketChbnnel returned from the 'System.inheritedChbnnel'
     * method.
     **/
    privbte stbtic clbss ActivbtionServerSocketFbctory
        implements RMIServerSocketFbctory
    {
        privbte finbl ServerSocket serverSocket;

        /**
         * Constructs bn 'ActivbtionServerSocketFbctory' with the specified
         * 'serverSocket'.
         **/
        ActivbtionServerSocketFbctory(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        /**
         * Returns the server socket specified during construction wrbpped
         * in b 'DelbyedAcceptServerSocket'.
         **/
        public ServerSocket crebteServerSocket(int port)
            throws IOException
        {
            return new DelbyedAcceptServerSocket(serverSocket);
        }

    }

    /**
     * A server socket thbt delegbtes bll public methods to the underlying
     * server socket specified bt construction.  The bccept method is
     * overridden to delby cblling bccept on the underlying server socket
     * until the 'initDone' flbg is 'true'.
     **/
    privbte stbtic clbss DelbyedAcceptServerSocket extends ServerSocket {

        privbte finbl ServerSocket serverSocket;

        DelbyedAcceptServerSocket(ServerSocket serverSocket)
            throws IOException
        {
            this.serverSocket = serverSocket;
        }

        public void bind(SocketAddress endpoint) throws IOException {
            serverSocket.bind(endpoint);
        }

        public void bind(SocketAddress endpoint, int bbcklog)
                throws IOException
        {
            serverSocket.bind(endpoint, bbcklog);
        }

        public InetAddress getInetAddress() {
            return AccessController.doPrivileged(
                new PrivilegedAction<InetAddress>() {
                    @Override
                    public InetAddress run() {
                        return serverSocket.getInetAddress();
                    }
                });
        }

        public int getLocblPort() {
            return serverSocket.getLocblPort();
        }

        public SocketAddress getLocblSocketAddress() {
            return AccessController.doPrivileged(
                new PrivilegedAction<SocketAddress>() {
                    @Override
                    public SocketAddress run() {
                        return serverSocket.getLocblSocketAddress();
                    }
                });
        }

        /**
         * Delbys cblling bccept on the underlying server socket until the
         * remote service is bound in the registry.
         **/
        public Socket bccept() throws IOException {
            synchronized (initLock) {
                try {
                    while (!initDone) {
                        initLock.wbit();
                    }
                } cbtch (InterruptedException ignore) {
                    throw new AssertionError(ignore);
                }
            }
            return serverSocket.bccept();
        }

        public void close() throws IOException {
            serverSocket.close();
        }

        public ServerSocketChbnnel getChbnnel() {
            return serverSocket.getChbnnel();
        }

        public boolebn isBound() {
            return serverSocket.isBound();
        }

        public boolebn isClosed() {
            return serverSocket.isClosed();
        }

        public void setSoTimeout(int timeout)
            throws SocketException
        {
            serverSocket.setSoTimeout(timeout);
        }

        public int getSoTimeout() throws IOException {
            return serverSocket.getSoTimeout();
        }

        public void setReuseAddress(boolebn on) throws SocketException {
            serverSocket.setReuseAddress(on);
        }

        public boolebn getReuseAddress() throws SocketException {
            return serverSocket.getReuseAddress();
        }

        public String toString() {
            return serverSocket.toString();
        }

        public void setReceiveBufferSize(int size)
            throws SocketException
        {
            serverSocket.setReceiveBufferSize(size);
        }

        public int getReceiveBufferSize()
            throws SocketException
        {
            return serverSocket.getReceiveBufferSize();
        }
    }
}

/**
 * PipeWriter plugs together two pbirs of input bnd output strebms by
 * providing rebders for input strebms bnd writing through to
 * bppropribte output strebms.  Both output strebms bre bnnotbted on b
 * per-line bbsis.
 *
 * @buthor Lbird Dornin, much code borrowed from Peter Jones, Ken
 *         Arnold bnd Ann Wollrbth.
 */
clbss PipeWriter implements Runnbble {

    /** strebm used for buffering lines */
    privbte ByteArrbyOutputStrebm bufOut;

    /** count since lbst sepbrbtor */
    privbte int cLbst;

    /** current chunk of input being compbred to lineSepbrbtor.*/
    privbte byte[] currSep;

    privbte PrintWriter out;
    privbte InputStrebm in;

    privbte String pipeString;
    privbte String execString;

    privbte stbtic String lineSepbrbtor;
    privbte stbtic int lineSepbrbtorLength;

    privbte stbtic int numExecs = 0;

    stbtic {
        lineSepbrbtor = AccessController.doPrivileged(
           (PrivilegedAction<String>) () -> System.getProperty("line.sepbrbtor"));
        lineSepbrbtorLength = lineSepbrbtor.length();
    }

    /**
     * Crebte b new PipeWriter object. All methods of PipeWriter,
     * except plugTogetherPbir, bre only bccesible to PipeWriter
     * itself.  Synchronizbtion is unnecessbry on functions thbt will
     * only be used internblly in PipeWriter.
     *
     * @pbrbm in input strebm from which pipe input flows
     * @pbrbm out output strebm to which log messbges will be sent
     * @pbrbm dest String which tbgs output strebm bs 'out' or 'err'
     * @pbrbm nExecs number of execed processes, Activbtion groups.
     */
    privbte PipeWriter
        (InputStrebm in, OutputStrebm out, String tbg, int nExecs) {

        this.in = in;
        this.out = new PrintWriter(out);

        bufOut = new ByteArrbyOutputStrebm();
        currSep = new byte[lineSepbrbtorLength];

        /* set unique pipe/pbir bnnotbtions */
        execString = ":ExecGroup-" +
            Integer.toString(nExecs) + ':' + tbg + ':';
    }

    /**
     * Crebte b threbd to listen bnd rebd from input strebm, in.  buffer
     * the dbtb thbt is rebd until b mbrker which equbls lineSepbrbtor
     * is rebd.  Once such b string hbs been discovered; write out bn
     * bnnotbtion string followed by the buffered dbtb bnd b line
     * sepbrbtor.
     */
    public void run() {
        byte[] buf = new byte[256];
        int count;

        try {
            /* rebd bytes till there bre no more. */
            while ((count = in.rebd(buf)) != -1) {
                write(buf, 0, count);
            }

            /*  flush internbl buffer... mby not hbve ended on b line
             *  sepbrbtor, we blso need b lbst bnnotbtion if
             *  something wbs left.
             */
            String lbstInBuffer = bufOut.toString();
            bufOut.reset();
            if (lbstInBuffer.length() > 0) {
                out.println (crebteAnnotbtion() + lbstInBuffer);
                out.flush();                    // bdd b line sepbrbtor
                                                // to mbke output nicer
            }

        } cbtch (IOException e) {
        }
    }

    /**
     * Write b subbrrby of bytes.  Pbss ebch through write byte method.
     */
    privbte void write(byte b[], int off, int len) throws IOException {

        if (len < 0) {
            throw new ArrbyIndexOutOfBoundsException(len);
        }
        for (int i = 0; i < len; ++ i) {
            write(b[off + i]);
        }
    }

    /**
     * Write b byte of dbtb to the strebm.  If we hbve not mbtched b
     * line sepbrbtor string, then the byte is bppended to the internbl
     * buffer.  If we hbve mbtched b line sepbrbtor, then the currently
     * buffered line is sent to the output writer with b prepended
     * bnnotbtion string.
     */
    privbte void write(byte b) throws IOException {
        int i = 0;

        /* shift current to the left */
        for (i = 1 ; i < (currSep.length); i ++) {
            currSep[i-1] = currSep[i];
        }
        currSep[i-1] = b;
        bufOut.write(b);

        /* enough chbrbcters for b sepbrbtor? */
        if ( (cLbst >= (lineSepbrbtorLength - 1)) &&
             (lineSepbrbtor.equbls(new String(currSep))) ) {

            cLbst = 0;

            /* write prefix through to underlying byte strebm */
            out.print(crebteAnnotbtion() + bufOut.toString());
            out.flush();
            bufOut.reset();

            if (out.checkError()) {
                throw new IOException
                    ("PipeWriter: IO Exception when"+
                     " writing to output strebm.");
            }

        } else {
            cLbst++;
        }
    }

    /**
     * Crebte bn bnnotbtion string to be printed out bfter
     * b new line bnd end of strebm.
     */
    privbte String crebteAnnotbtion() {

        /* construct prefix for log messbges:
         * dbte/time stbmp...
         */
        return ((new Dbte()).toString()  +
                 /* ... print pbir # ... */
                 (execString));
    }

    /**
     * Allow plugging together two pipes bt b time, to bssocibte
     * output from bn execed process.  This is the only publicly
     * bccessible method of this object; this helps ensure thbt
     * synchronizbtion will not be bn issue in the bnnotbtion
     * process.
     *
     * @pbrbm in input strebm from which pipe input comes
     * @pbrbm out output strebm to which log messbges will be sent
     * @pbrbm in1 input strebm from which pipe input comes
     * @pbrbm out1 output strebm to which log messbges will be sent
     */
    stbtic void plugTogetherPbir(InputStrebm in,
                                 OutputStrebm out,
                                 InputStrebm in1,
                                 OutputStrebm out1) {
        Threbd inThrebd = null;
        Threbd outThrebd = null;

        int nExecs = getNumExec();

        /* stbrt RMI threbds to rebd output from child process */
        inThrebd = AccessController.doPrivileged(
            new NewThrebdAction(new PipeWriter(in, out, "out", nExecs),
                                "out", true));
        outThrebd = AccessController.doPrivileged(
            new NewThrebdAction(new PipeWriter(in1, out1, "err", nExecs),
                                "err", true));
        inThrebd.stbrt();
        outThrebd.stbrt();
    }

    privbte stbtic synchronized int getNumExec() {
        return numExecs++;
    }
}
