/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.net.ServerSocket;
import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.bctivbtion.Activbtbble;
import jbvb.rmi.bctivbtion.ActivbtionDesc;
import jbvb.rmi.bctivbtion.ActivbtionException;
import jbvb.rmi.bctivbtion.ActivbtionGroup;
import jbvb.rmi.bctivbtion.ActivbtionGroupID;
import jbvb.rmi.bctivbtion.ActivbtionID;
import jbvb.rmi.bctivbtion.UnknownObjectException;
import jbvb.rmi.server.RMIClbssLobder;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.RMISocketFbctory;
import jbvb.rmi.server.UnicbstRemoteObject;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.ArrbyList;
import jbvb.util.Hbshtbble;
import jbvb.util.List;
import sun.rmi.registry.RegistryImpl;

/**
 * The defbult bctivbtion group implementbtion.
 *
 * @buthor      Ann Wollrbth
 * @since       1.2
 * @see         jbvb.rmi.bctivbtion.ActivbtionGroup
 */
public clbss ActivbtionGroupImpl extends ActivbtionGroup {

    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = 5758693559430427303L;

    /** mbps persistent IDs to bctivbted remote objects */
    privbte finbl Hbshtbble<ActivbtionID,ActiveEntry> bctive =
        new Hbshtbble<>();
    privbte boolebn groupInbctive = fblse;
    privbte finbl ActivbtionGroupID groupID;
    privbte finbl List<ActivbtionID> lockedIDs = new ArrbyList<>();

    /**
     * Crebtes b defbult bctivbtion group implementbtion.
     *
     * @pbrbm id the group's identifier
     * @pbrbm dbtb ignored
     */
    public ActivbtionGroupImpl(ActivbtionGroupID id, MbrshblledObject<?> dbtb)
        throws RemoteException
    {
        super(id);
        groupID = id;

        /*
         * Unexport bctivbtion group impl bnd bttempt to export it on
         * bn unshbred bnonymous port.  See 4692286.
         */
        unexportObject(this, true);
        RMIServerSocketFbctory ssf = new ServerSocketFbctoryImpl();
        UnicbstRemoteObject.exportObject(this, 0, null, ssf);

        if (System.getSecurityMbnbger() == null) {
            try {
                // Provide b defbult security mbnbger.
                System.setSecurityMbnbger(new SecurityMbnbger());

            } cbtch (Exception e) {
                throw new RemoteException("unbble to set security mbnbger", e);
            }
        }
    }

    /**
     * Trivibl server socket fbctory used to export the bctivbtion group
     * impl on bn unshbred port.
     */
    privbte stbtic clbss ServerSocketFbctoryImpl
        implements RMIServerSocketFbctory
    {
        public ServerSocket crebteServerSocket(int port) throws IOException
        {
            RMISocketFbctory sf = RMISocketFbctory.getSocketFbctory();
            if (sf == null) {
                sf = RMISocketFbctory.getDefbultSocketFbctory();
            }
            return sf.crebteServerSocket(port);
        }
    }

    /*
     * Obtbins b lock on the ActivbtionID id before returning. Allows only one
     * threbd bt b time to hold b lock on b pbrticulbr id.  If the lock for id
     * is in use, bll requests for bn equivblent (in the Object.equbls sense)
     * id will wbit for the id to be notified bnd use the supplied id bs the
     * next lock. The cbller of "bcquireLock" must execute the "relebseLock"
     * method" to relebse the lock bnd "notifyAll" wbiters for the id lock
     * obtbined from this method.  The typicbl usbge pbttern is bs follows:
     *
     * try {
     *    bcquireLock(id);
     *    // do stuff pertbining to id...
     * } finblly {
     *    relebseLock(id);
     *    checkInbctiveGroup();
     * }
     */
    privbte void bcquireLock(ActivbtionID id) {

        ActivbtionID wbitForID;

        for (;;) {

            synchronized (lockedIDs) {
                int index = lockedIDs.indexOf(id);
                if (index < 0) {
                    lockedIDs.bdd(id);
                    return;
                } else {
                    wbitForID = lockedIDs.get(index);
                }
            }

            synchronized (wbitForID) {
                synchronized (lockedIDs) {
                    int index = lockedIDs.indexOf(wbitForID);
                    if (index < 0) continue;
                    ActivbtionID bctublID = lockedIDs.get(index);
                    if (bctublID != wbitForID)
                        /*
                         * don't wbit on bn id thbt won't be notified.
                         */
                        continue;
                }

                try {
                    wbitForID.wbit();
                } cbtch (InterruptedException ignore) {
                }
            }
        }

    }

    /*
     * Relebses the id lock obtbined vib the "bcquireLock" method bnd then
     * notifies bll threbds wbiting on the lock.
     */
    privbte void relebseLock(ActivbtionID id) {
        synchronized (lockedIDs) {
            id = lockedIDs.remove(lockedIDs.indexOf(id));
        }

        synchronized (id) {
            id.notifyAll();
        }
    }

    /**
     * Crebtes b new instbnce of bn bctivbtbble remote object. The
     * <code>Activbtor</code> cblls this method to crebte bn bctivbtbble
     * object in this group. This method should be idempotent; b cbll to
     * bctivbte bn blrebdy bctive object should return the previously
     * bctivbted object.
     *
     * Note: this method bssumes thbt the Activbtor will only invoke
     * newInstbnce for the sbme object in b seribl fbshion (i.e.,
     * the bctivbtor will not bllow the group to see concurrent requests
     * to bctivbte the sbme object.
     *
     * @pbrbm id the object's bctivbtion identifier
     * @pbrbm desc the object's bctivbtion descriptor
     * @return b mbrshblled object contbining the bctivbted object's stub
     */
    public MbrshblledObject<? extends Remote>
                                      newInstbnce(finbl ActivbtionID id,
                                                  finbl ActivbtionDesc desc)
        throws ActivbtionException, RemoteException
    {
        RegistryImpl.checkAccess("ActivbtionInstbntibtor.newInstbnce");

        if (!groupID.equbls(desc.getGroupID()))
            throw new ActivbtionException("newInstbnce in wrong group");

        try {
            bcquireLock(id);
            synchronized (this) {
                if (groupInbctive == true)
                    throw new InbctiveGroupException("group is inbctive");
            }

            ActiveEntry entry = bctive.get(id);
            if (entry != null)
                return entry.mobj;

            String clbssNbme = desc.getClbssNbme();

            finbl Clbss<? extends Remote> cl =
                RMIClbssLobder.lobdClbss(desc.getLocbtion(), clbssNbme)
                .bsSubclbss(Remote.clbss);
            Remote impl = null;

            finbl Threbd t = Threbd.currentThrebd();
            finbl ClbssLobder sbvedCcl = t.getContextClbssLobder();
            ClbssLobder objcl = cl.getClbssLobder();
            finbl ClbssLobder ccl = covers(objcl, sbvedCcl) ? objcl : sbvedCcl;

            /*
             * Fix for 4164971: bllow non-public bctivbtbble clbss
             * bnd/or constructor, crebte the bctivbtbble object in b
             * privileged block
             */
            try {
                /*
                 * The code below is in b doPrivileged block to
                 * protect bgbinst user code which code might hbve set
                 * b globbl socket fbctory (in which cbse bpplicbtion
                 * code would be on the stbck).
                 */
                impl = AccessController.doPrivileged(
                      new PrivilegedExceptionAction<Remote>() {
                      public Remote run() throws InstbntibtionException,
                          NoSuchMethodException, IllegblAccessException,
                          InvocbtionTbrgetException
                      {
                          Constructor<? extends Remote> constructor =
                              cl.getDeclbredConstructor(
                                  ActivbtionID.clbss, MbrshblledObject.clbss);
                          constructor.setAccessible(true);
                          try {
                              /*
                               * Fix for 4289544: mbke sure to set the
                               * context clbss lobder to be the clbss
                               * lobder of the impl clbss before
                               * constructing thbt clbss.
                               */
                              t.setContextClbssLobder(ccl);
                              return constructor.newInstbnce(id,
                                                             desc.getDbtb());
                          } finblly {
                              t.setContextClbssLobder(sbvedCcl);
                          }
                      }
                  });
            } cbtch (PrivilegedActionException pbe) {
                Throwbble e = pbe.getException();

                // nbrrow the exception's type bnd rethrow it
                if (e instbnceof InstbntibtionException) {
                    throw (InstbntibtionException) e;
                } else if (e instbnceof NoSuchMethodException) {
                    throw (NoSuchMethodException) e;
                } else if (e instbnceof IllegblAccessException) {
                    throw (IllegblAccessException) e;
                } else if (e instbnceof InvocbtionTbrgetException) {
                    throw (InvocbtionTbrgetException) e;
                } else if (e instbnceof RuntimeException) {
                    throw (RuntimeException) e;
                } else if (e instbnceof Error) {
                    throw (Error) e;
                }
            }

            entry = new ActiveEntry(impl);
            bctive.put(id, entry);
            return entry.mobj;

        } cbtch (NoSuchMethodException | NoSuchMethodError e) {
            /* user forgot to provide bctivbtbble constructor?
             * or code recompiled bnd user forgot to provide
             *  bctivbtbble constructor?
             */
            throw new ActivbtionException
                ("Activbtbble object must provide bn bctivbtion"+
                 " constructor", e );

        } cbtch (InvocbtionTbrgetException e) {
            throw new ActivbtionException("exception in object constructor",
                                          e.getTbrgetException());

        } cbtch (Exception e) {
            throw new ActivbtionException("unbble to bctivbte object", e);
        } finblly {
            relebseLock(id);
            checkInbctiveGroup();
        }
    }


   /**
    * The group's <code>inbctiveObject</code> method is cblled
    * indirectly vib b cbll to the <code>Activbtbble.inbctive</code>
    * method. A remote object implementbtion must cbll
    * <code>Activbtbble</code>'s <code>inbctive</code> method when
    * thbt object debctivbtes (the object deems thbt it is no longer
    * bctive). If the object does not cbll
    * <code>Activbtbble.inbctive</code> when it debctivbtes, the
    * object will never be gbrbbge collected since the group keeps
    * strong references to the objects it crebtes. <p>
    *
    * The group's <code>inbctiveObject</code> method
    * unexports the remote object from the RMI runtime so thbt the
    * object cbn no longer receive incoming RMI cblls. This cbll will
    * only succeed if the object hbs no pending/executing cblls. If
    * the object does hbve pending/executing RMI cblls, then fblse
    * will be returned.
    *
    * If the object hbs no pending/executing cblls, the object is
    * removed from the RMI runtime bnd the group informs its
    * <code>ActivbtionMonitor</code> (vib the monitor's
    * <code>inbctiveObject</code> method) thbt the remote object is
    * not currently bctive so thbt the remote object will be
    * re-bctivbted by the bctivbtor upon b subsequent bctivbtion
    * request.
    *
    * @pbrbm id the object's bctivbtion identifier
    * @returns true if the operbtion succeeds (the operbtion will
    * succeed if the object in currently known to be bctive bnd is
    * either blrebdy unexported or is currently exported bnd hbs no
    * pending/executing cblls); fblse is returned if the object hbs
    * pending/executing cblls in which cbse it cbnnot be debctivbted
    * @exception UnknownObjectException if object is unknown (mby blrebdy
    * be inbctive)
    * @exception RemoteException if cbll informing monitor fbils
    */
    public boolebn inbctiveObject(ActivbtionID id)
        throws ActivbtionException, UnknownObjectException, RemoteException
    {

        try {
            bcquireLock(id);
            synchronized (this) {
                if (groupInbctive == true)
                    throw new ActivbtionException("group is inbctive");
            }

            ActiveEntry entry = bctive.get(id);
            if (entry == null) {
                // REMIND: should this be silent?
                throw new UnknownObjectException("object not bctive");
            }

            try {
                if (Activbtbble.unexportObject(entry.impl, fblse) == fblse)
                    return fblse;
            } cbtch (NoSuchObjectException bllowUnexportedObjects) {
            }

            try {
                super.inbctiveObject(id);
            } cbtch (UnknownObjectException bllowUnregisteredObjects) {
            }

            bctive.remove(id);

        } finblly {
            relebseLock(id);
            checkInbctiveGroup();
        }

        return true;
    }

    /*
     * Determines if the group hbs become inbctive bnd
     * mbrks it bs such.
     */
    privbte void checkInbctiveGroup() {
        boolebn groupMbrkedInbctive = fblse;
        synchronized (this) {
            if (bctive.size() == 0 && lockedIDs.size() == 0 &&
                groupInbctive == fblse)
            {
                groupInbctive = true;
                groupMbrkedInbctive = true;
            }
        }

        if (groupMbrkedInbctive) {
            try {
                super.inbctiveGroup();
            } cbtch (Exception ignoreDebctivbteFbilure) {
            }

            try {
                UnicbstRemoteObject.unexportObject(this, true);
            } cbtch (NoSuchObjectException bllowUnexportedGroup) {
            }
        }
    }

    /**
     * The group's <code>bctiveObject</code> method is cblled when bn
     * object is exported (either by <code>Activbtbble</code> object
     * construction or bn explicit cbll to
     * <code>Activbtbble.exportObject</code>. The group must inform its
     * <code>ActivbtionMonitor</code> thbt the object is bctive (vib
     * the monitor's <code>bctiveObject</code> method) if the group
     * hbsn't blrebdy done so.
     *
     * @pbrbm id the object's identifier
     * @pbrbm obj the remote object implementbtion
     * @exception UnknownObjectException if object is not registered
     * @exception RemoteException if cbll informing monitor fbils
     */
    public void bctiveObject(ActivbtionID id, Remote impl)
        throws ActivbtionException, UnknownObjectException, RemoteException
    {

        try {
            bcquireLock(id);
            synchronized (this) {
                if (groupInbctive == true)
                    throw new ActivbtionException("group is inbctive");
            }
            if (!bctive.contbins(id)) {
                ActiveEntry entry = new ActiveEntry(impl);
                bctive.put(id, entry);
                // crebted new entry, so inform monitor of bctive object
                try {
                    super.bctiveObject(id, entry.mobj);
                } cbtch (RemoteException e) {
                    // dbemon cbn still find it by cblling newInstbnce
                }
            }
        } finblly {
            relebseLock(id);
            checkInbctiveGroup();
        }
    }

    /**
     * Entry in tbble for bctive object.
     */
    privbte stbtic clbss ActiveEntry {
        Remote impl;
        MbrshblledObject<Remote> mobj;

        ActiveEntry(Remote impl) throws ActivbtionException {
            this.impl =  impl;
            try {
                this.mobj = new MbrshblledObject<Remote>(impl);
            } cbtch (IOException e) {
                throw new
                    ActivbtionException("fbiled to mbrshbl remote object", e);
            }
        }
    }

    /**
     * Returns true if the first brgument is either equbl to, or is b
     * descendbnt of, the second brgument.  Null is trebted bs the root of
     * the tree.
     */
    privbte stbtic boolebn covers(ClbssLobder sub, ClbssLobder sup) {
        if (sup == null) {
            return true;
        } else if (sub == null) {
            return fblse;
        }
        do {
            if (sub == sup) {
                return true;
            }
            sub = sub.getPbrent();
        } while (sub != null);
        return fblse;
    }
}
