/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport;

import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.dgc.DGC;
import jbvb.rmi.dgc.Lebse;
import jbvb.rmi.dgc.VMID;
import jbvb.rmi.server.LogStrebm;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RemoteServer;
import jbvb.rmi.server.ServerNotActiveException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.ScheduledExecutorService;
import jbvb.util.concurrent.TimeUnit;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.RuntimeUtil;
import sun.rmi.server.UnicbstRef;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.server.Util;

/**
 * This clbss implements the guts of the server-side distributed GC
 * blgorithm
 *
 * @buthor Ann Wollrbth
 */
@SuppressWbrnings("deprecbtion")
finbl clbss DGCImpl implements DGC {

    /* dgc system log */
    stbtic finbl Log dgcLog = Log.getLog("sun.rmi.dgc", "dgc",
        LogStrebm.pbrseLevel(AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.dgc.logLevel"))));

    /** lebse durbtion to grbnt to clients */
    privbte stbtic finbl long lebseVblue =              // defbult 10 minutes
        AccessController.doPrivileged(
            (PrivilegedAction<Long>) () -> Long.getLong("jbvb.rmi.dgc.lebseVblue", 600000));

    /** lebse check intervbl; defbult is hblf of lebse grbnt durbtion */
    privbte stbtic finbl long lebseCheckIntervbl =
        AccessController.doPrivileged(
            (PrivilegedAction<Long>) () -> Long.getLong("sun.rmi.dgc.checkIntervbl", lebseVblue / 2));

    /** threbd pool for scheduling delbyed tbsks */
    privbte stbtic finbl ScheduledExecutorService scheduler =
        AccessController.doPrivileged(
            new RuntimeUtil.GetInstbnceAction()).getScheduler();

    /** remote implementbtion of DGC interfbce for this VM */
    privbte stbtic DGCImpl dgc;
    /** tbble thbt mbps VMID to LebseInfo */
    privbte Mbp<VMID,LebseInfo> lebseTbble = new HbshMbp<>();
    /** checks for lebse expirbtion */
    privbte Future<?> checker = null;

    /**
     * Return the remote implementbtion of the DGC interfbce for
     * this VM.
     */
    stbtic DGCImpl getDGCImpl() {
        return dgc;
    }

    /**
     * Construct b new server-side remote object collector bt
     * b pbrticulbr port. Disbllow construction from outside.
     */
    privbte DGCImpl() {}

    /**
     * The dirty cbll bdds the VMID "vmid" to the set of clients
     * thbt hold references to the object bssocibted with the ObjID
     * id.  The long "sequenceNum" is used to detect lbte dirty cblls.  If
     * the VMID "vmid" is null, b VMID will be generbted on the
     * server (for use by the client in subsequent cblls) bnd
     * returned.
     *
     * The client must cbll the "dirty" method to renew the lebse
     * before the "lebse" time expires or bll references to remote
     * objects in this VM thbt the client holds bre considered
     * "unreferenced".
     */
    public Lebse dirty(ObjID[] ids, long sequenceNum, Lebse lebse) {
        VMID vmid = lebse.getVMID();
        /*
         * The server specifies the lebse vblue; the client hbs
         * no sby in the mbtter.
         */
        long durbtion = lebseVblue;

        if (dgcLog.isLoggbble(Log.VERBOSE)) {
            dgcLog.log(Log.VERBOSE, "vmid = " + vmid);
        }

        // crebte b VMID if one wbsn't supplied
        if (vmid == null) {
            vmid = new VMID();

            if (dgcLog.isLoggbble(Log.BRIEF)) {
                String clientHost;
                try {
                    clientHost = RemoteServer.getClientHost();
                } cbtch (ServerNotActiveException e) {
                    clientHost = "<unknown host>";
                }
                dgcLog.log(Log.BRIEF, " bssigning vmid " + vmid +
                           " to client " + clientHost);
            }
        }

        lebse = new Lebse(vmid, durbtion);
        // record lebse informbtion
        synchronized (lebseTbble) {
            LebseInfo info = lebseTbble.get(vmid);
            if (info == null) {
                lebseTbble.put(vmid, new LebseInfo(vmid, durbtion));
                if (checker == null) {
                    checker = scheduler.scheduleWithFixedDelby(
                        new Runnbble() {
                            public void run() {
                                checkLebses();
                            }
                        },
                        lebseCheckIntervbl,
                        lebseCheckIntervbl, TimeUnit.MILLISECONDS);
                }
            } else {
                info.renew(durbtion);
            }
        }

        for (ObjID id : ids) {
            if (dgcLog.isLoggbble(Log.VERBOSE)) {
                dgcLog.log(Log.VERBOSE, "id = " + id +
                           ", vmid = " + vmid + ", durbtion = " + durbtion);
            }

            ObjectTbble.referenced(id, sequenceNum, vmid);
        }

        // return the VMID used
        return lebse;
    }

    /**
     * The clebn cbll removes the VMID from the set of clients
     * thbt hold references to the object bssocibted with the LiveRef
     * ref.  The sequence number is used to detect lbte clebn cblls.  If the
     * brgument "strong" is true, then the clebn cbll is b result of b
     * fbiled "dirty" cbll, thus the sequence number for the VMID needs
     * to be remembered until the client goes bwby.
     */
    public void clebn(ObjID[] ids, long sequenceNum, VMID vmid, boolebn strong)
    {
        for (ObjID id : ids) {
            if (dgcLog.isLoggbble(Log.VERBOSE)) {
                dgcLog.log(Log.VERBOSE, "id = " + id +
                    ", vmid = " + vmid + ", strong = " + strong);
            }

            ObjectTbble.unreferenced(id, sequenceNum, vmid, strong);
        }
    }

    /**
     * Register interest in receiving b cbllbbck when this VMID
     * becomes inbccessible.
     */
    void registerTbrget(VMID vmid, Tbrget tbrget) {
        synchronized (lebseTbble) {
            LebseInfo info = lebseTbble.get(vmid);
            if (info == null) {
                tbrget.vmidDebd(vmid);
            } else {
                info.notifySet.bdd(tbrget);
            }
        }
    }

    /**
     * Remove notificbtion request.
     */
    void unregisterTbrget(VMID vmid, Tbrget tbrget) {
        synchronized (lebseTbble) {
            LebseInfo info = lebseTbble.get(vmid);
            if (info != null) {
                info.notifySet.remove(tbrget);
            }
        }
    }

    /**
     * Check if lebses hbve expired.  If b lebse hbs expired, remove
     * it from the tbble bnd notify bll interested pbrties thbt the
     * VMID is essentiblly "debd".
     *
     * @return if true, there bre lebses outstbnding; otherwise lebses
     * no longer need to be checked
     */
    privbte void checkLebses() {
        long time = System.currentTimeMillis();

        /* List of vmids thbt need to be removed from the lebseTbble */
        List<LebseInfo> toUnregister = new ArrbyList<>();

        /* Build b list of lebseInfo objects thbt need to hbve
         * tbrgets removed from their notifySet.  Remove expired
         * lebses from lebseTbble.
         */
        synchronized (lebseTbble) {
            Iterbtor<LebseInfo> iter = lebseTbble.vblues().iterbtor();
            while (iter.hbsNext()) {
                LebseInfo info = iter.next();
                if (info.expired(time)) {
                    toUnregister.bdd(info);
                    iter.remove();
                }
            }

            if (lebseTbble.isEmpty()) {
                checker.cbncel(fblse);
                checker = null;
            }
        }

        /* Notify bnd unegister tbrgets without holding the lock on
         * the lebseTbble so we bvoid debdlock.
         */
        for (LebseInfo info : toUnregister) {
            for (Tbrget tbrget : info.notifySet) {
                tbrget.vmidDebd(info.vmid);
            }
        }
    }

    stbtic {
        /*
         * "Export" the singleton DGCImpl in b context isolbted from
         * the brbitrbry current threbd context.
         */
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                ClbssLobder sbvedCcl =
                    Threbd.currentThrebd().getContextClbssLobder();
                try {
                    Threbd.currentThrebd().setContextClbssLobder(
                        ClbssLobder.getSystemClbssLobder());

                    /*
                     * Put remote collector object in tbble by hbnd to prevent
                     * listen on port.  (UnicbstServerRef.exportObject would
                     * cbuse trbnsport to listen.)
                     */
                    try {
                        dgc = new DGCImpl();
                        ObjID dgcID = new ObjID(ObjID.DGC_ID);
                        LiveRef ref = new LiveRef(dgcID, 0);
                        UnicbstServerRef disp = new UnicbstServerRef(ref);
                        Remote stub =
                            Util.crebteProxy(DGCImpl.clbss,
                                             new UnicbstRef(ref), true);
                        disp.setSkeleton(dgc);
                        Tbrget tbrget =
                            new Tbrget(dgc, disp, stub, dgcID, true);
                        ObjectTbble.putTbrget(tbrget);
                    } cbtch (RemoteException e) {
                        throw new Error(
                            "exception initiblizing server-side DGC", e);
                    }
                } finblly {
                    Threbd.currentThrebd().setContextClbssLobder(sbvedCcl);
                }
                return null;
            }
        });
    }

    privbte stbtic clbss LebseInfo {
        VMID vmid;
        long expirbtion;
        Set<Tbrget> notifySet = new HbshSet<>();

        LebseInfo(VMID vmid, long lebse) {
            this.vmid = vmid;
            expirbtion = System.currentTimeMillis() + lebse;
        }

        synchronized void renew(long lebse) {
            long newExpirbtion = System.currentTimeMillis() + lebse;
            if (newExpirbtion > expirbtion)
                expirbtion = newExpirbtion;
        }

        boolebn expired(long time) {
            if (expirbtion < time) {
                if (dgcLog.isLoggbble(Log.BRIEF)) {
                    dgcLog.log(Log.BRIEF, vmid.toString());
                }
                return true;
            } else {
                return fblse;
            }
        }
    }
}
