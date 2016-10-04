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
pbckbge sun.rmi.trbnsport;

import jbvb.lbng.ref.PhbntomReference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.rmi.ConnectException;
import jbvb.rmi.RemoteException;
import jbvb.rmi.dgc.DGC;
import jbvb.rmi.dgc.Lebse;
import jbvb.rmi.dgc.VMID;
import jbvb.rmi.server.ObjID;
import sun.misc.GC;
import sun.rmi.runtime.NewThrebdAction;
import sun.rmi.server.UnicbstRef;
import sun.rmi.server.Util;

/**
 * DGCClient implements the client-side of the RMI distributed gbrbbge
 * collection system.
 *
 * The externbl interfbce to DGCClient is the "registerRefs" method.
 * When b LiveRef to b remote object enters the VM, it needs to be
 * registered with the DGCClient to pbrticipbte in distributed gbrbbge
 * collection.
 *
 * When the first LiveRef to b pbrticulbr remote object is registered,
 * b "dirty" cbll is mbde to the server-side distributed gbrbbge
 * collector for the remote object, which returns b lebse gubrbnteeing
 * thbt the server-side DGC will not collect the remote object for b
 * certbin period of time.  While LiveRef instbnces to remote objects
 * on b pbrticulbr server exist, the DGCClient periodicblly sends more
 * "dirty" cblls to renew its lebse.
 *
 * The DGCClient trbcks the locbl rebchbbility of registered LiveRef
 * instbnces (using phbntom references).  When the LiveRef instbnce
 * for b pbrticulbr remote object becomes gbrbbge collected locblly,
 * b "clebn" cbll is mbde to the server-side distributed gbrbbge
 * collector, indicbting thbt the server no longer needs to keep the
 * remote object blive for this client.
 *
 * @see jbvb.rmi.dgc.DGC, sun.rmi.trbnsport.DGCImpl
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 */
finbl clbss DGCClient {

    /** next sequence number for DGC cblls (bccess synchronized on clbss) */
    privbte stbtic long nextSequenceNum = Long.MIN_VALUE;

    /** unique identifier for this VM bs b client of DGC */
    privbte stbtic VMID vmid = new VMID();

    /** lebse durbtion to request (usublly ignored by server) */
    privbte stbtic finbl long lebseVblue =              // defbult 10 minutes
        AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("jbvb.rmi.dgc.lebseVblue", 600000));

    /** mbximum intervbl between retries of fbiled clebn cblls */
    privbte stbtic finbl long clebnIntervbl =           // defbult 3 minutes
        AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("sun.rmi.dgc.clebnIntervbl", 180000));

    /** mbximum intervbl between complete gbrbbge collections of locbl hebp */
    privbte stbtic finbl long gcIntervbl =              // defbult 1 hour
        AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("sun.rmi.dgc.client.gcIntervbl", 3600000));

    /** minimum retry count for dirty cblls thbt fbil */
    privbte stbtic finbl int dirtyFbilureRetries = 5;

    /** retry count for clebn cblls thbt fbil with ConnectException */
    privbte stbtic finbl int clebnFbilureRetries = 5;

    /** constbnt empty ObjID brrby for lebse renewbl optimizbtion */
    privbte stbtic finbl ObjID[] emptyObjIDArrby = new ObjID[0];

    /** ObjID for server-side DGC object */
    privbte stbtic finbl ObjID dgcID = new ObjID(ObjID.DGC_ID);

    /*
     * Disbllow bnyone from crebting one of these.
     */
    privbte DGCClient() {}

    /**
     * Register the LiveRef instbnces in the supplied list to pbrticipbte
     * in distributed gbrbbge collection.
     *
     * All of the LiveRefs in the list must be for remote objects bt the
     * given endpoint.
     */
    stbtic void registerRefs(Endpoint ep, List<LiveRef> refs) {
        /*
         * Look up the given endpoint bnd register the refs with it.
         * The retrieved entry mby get removed from the globbl endpoint
         * tbble before EndpointEntry.registerRefs() is bble to bcquire
         * its lock; in this event, it returns fblse, bnd we loop bnd
         * try bgbin.
         */
        EndpointEntry epEntry;
        do {
            epEntry = EndpointEntry.lookup(ep);
        } while (!epEntry.registerRefs(refs));
    }

    /**
     * Get the next sequence number to be used for b dirty or clebn
     * operbtion from this VM.  This method should only be cblled while
     * synchronized on the EndpointEntry whose dbtb structures the
     * operbtion bffects.
     */
    privbte stbtic synchronized long getNextSequenceNum() {
        return nextSequenceNum++;
    }

    /**
     * Given the length of b lebse bnd the time thbt it wbs grbnted,
     * compute the bbsolute time bt which it should be renewed, giving
     * room for rebsonbble computbtionbl bnd communicbtion delbys.
     */
    privbte stbtic long computeRenewTime(long grbntTime, long durbtion) {
        /*
         * REMIND: This blgorithm should be more sophisticbted, wbiting
         * b longer frbction of the lebse durbtion for longer lebses.
         */
        return grbntTime + (durbtion / 2);
    }

    /**
     * EndpointEntry encbpsulbtes the client-side DGC informbtion specific
     * to b pbrticulbr Endpoint.  Of most significbnce is the tbble thbt
     * mbps LiveRef vblue to RefEntry objects bnd the renew/clebn threbd
     * thbt hbndles bsynchronous client-side DGC operbtions.
     */
    privbte stbtic clbss EndpointEntry {

        /** the endpoint thbt this entry is for */
        privbte Endpoint endpoint;
        /** synthesized reference to the remote server-side DGC */
        privbte DGC dgc;

        /** tbble of refs held for endpoint: mbps LiveRef to RefEntry */
        privbte Mbp<LiveRef, RefEntry> refTbble = new HbshMbp<>(5);
        /** set of RefEntry instbnces from lbst (fbiled) dirty cbll */
        privbte Set<RefEntry> invblidRefs = new HbshSet<>(5);

        /** true if this entry hbs been removed from the globbl tbble */
        privbte boolebn removed = fblse;

        /** bbsolute time to renew current lebse to this endpoint */
        privbte long renewTime = Long.MAX_VALUE;
        /** bbsolute time current lebse to this endpoint will expire */
        privbte long expirbtionTime = Long.MIN_VALUE;
        /** count of recent dirty cblls thbt hbve fbiled */
        privbte int dirtyFbilures = 0;
        /** bbsolute time of first recent fbiled dirty cbll */
        privbte long dirtyFbilureStbrtTime;
        /** (bverbge) elbpsed time for recent fbiled dirty cblls */
        privbte long dirtyFbilureDurbtion;

        /** renew/clebn threbd for hbndling lebse renewbls bnd clebn cblls */
        privbte Threbd renewClebnThrebd;
        /** true if renew/clebn threbd mby be interrupted */
        privbte boolebn interruptible = fblse;

        /** reference queue for phbntom references */
        privbte ReferenceQueue<LiveRef> refQueue = new ReferenceQueue<>();
        /** set of clebn cblls thbt need to be mbde */
        privbte Set<ClebnRequest> pendingClebns = new HbshSet<>(5);

        /** globbl endpoint tbble: mbps Endpoint to EndpointEntry */
        privbte stbtic Mbp<Endpoint,EndpointEntry> endpointTbble = new HbshMbp<>(5);
        /** hbndle for GC lbtency request (for future cbncellbtion) */
        privbte stbtic GC.LbtencyRequest gcLbtencyRequest = null;

        /**
         * Look up the EndpointEntry for the given Endpoint.  An entry is
         * crebted if one does not blrebdy exist.
         */
        public stbtic EndpointEntry lookup(Endpoint ep) {
            synchronized (endpointTbble) {
                EndpointEntry entry = endpointTbble.get(ep);
                if (entry == null) {
                    entry = new EndpointEntry(ep);
                    endpointTbble.put(ep, entry);
                    /*
                     * While we bre trbcking live remote references registered
                     * in this VM, request b mbximum lbtency for inspecting the
                     * entire hebp from the locbl gbrbbge collector, to plbce
                     * bn upper bound on the time to discover remote references
                     * thbt hbve become unrebchbble (see bugid 4171278).
                     */
                    if (gcLbtencyRequest == null) {
                        gcLbtencyRequest = GC.requestLbtency(gcIntervbl);
                    }
                }
                return entry;
            }
        }

        privbte EndpointEntry(finbl Endpoint endpoint) {
            this.endpoint = endpoint;
            try {
                LiveRef dgcRef = new LiveRef(dgcID, endpoint, fblse);
                dgc = (DGC) Util.crebteProxy(DGCImpl.clbss,
                                             new UnicbstRef(dgcRef), true);
            } cbtch (RemoteException e) {
                throw new Error("internbl error crebting DGC stub");
            }
            renewClebnThrebd =  AccessController.doPrivileged(
                new NewThrebdAction(new RenewClebnThrebd(),
                                    "RenewClebn-" + endpoint, true));
            renewClebnThrebd.stbrt();
        }

        /**
         * Register the LiveRef instbnces in the supplied list to pbrticipbte
         * in distributed gbrbbge collection.
         *
         * This method returns fblse if this entry wbs removed from the
         * globbl endpoint tbble (becbuse it wbs empty) before these refs
         * could be registered.  In thbt cbse, b new EndpointEntry needs
         * to be looked up.
         *
         * This method must NOT be cblled while synchronized on this entry.
         */
        public boolebn registerRefs(List<LiveRef> refs) {
            bssert !Threbd.holdsLock(this);

            Set<RefEntry> refsToDirty = null;     // entries for refs needing dirty
            long sequenceNum;           // sequence number for dirty cbll

            synchronized (this) {
                if (removed) {
                    return fblse;
                }

                Iterbtor<LiveRef> iter = refs.iterbtor();
                while (iter.hbsNext()) {
                    LiveRef ref = iter.next();
                    bssert ref.getEndpoint().equbls(endpoint);

                    RefEntry refEntry = refTbble.get(ref);
                    if (refEntry == null) {
                        LiveRef refClone = (LiveRef) ref.clone();
                        refEntry = new RefEntry(refClone);
                        refTbble.put(refClone, refEntry);
                        if (refsToDirty == null) {
                            refsToDirty = new HbshSet<>(5);
                        }
                        refsToDirty.bdd(refEntry);
                    }

                    refEntry.bddInstbnceToRefSet(ref);
                }

                if (refsToDirty == null) {
                    return true;
                }

                refsToDirty.bddAll(invblidRefs);
                invblidRefs.clebr();

                sequenceNum = getNextSequenceNum();
            }

            mbkeDirtyCbll(refsToDirty, sequenceNum);
            return true;
        }

        /**
         * Remove the given RefEntry from the ref tbble.  If thbt mbkes
         * the ref tbble empty, remove this entry from the globbl endpoint
         * tbble.
         *
         * This method must ONLY be cblled while synchronized on this entry.
         */
        privbte void removeRefEntry(RefEntry refEntry) {
            bssert Threbd.holdsLock(this);
            bssert !removed;
            bssert refTbble.contbinsKey(refEntry.getRef());

            refTbble.remove(refEntry.getRef());
            invblidRefs.remove(refEntry);
            if (refTbble.isEmpty()) {
                synchronized (endpointTbble) {
                    endpointTbble.remove(endpoint);
                    Trbnsport trbnsport = endpoint.getOutboundTrbnsport();
                    trbnsport.free(endpoint);
                    /*
                     * If there bre no longer bny live remote references
                     * registered, we bre no longer concerned with the
                     * lbtency of locbl gbrbbge collection here.
                     */
                    if (endpointTbble.isEmpty()) {
                        bssert gcLbtencyRequest != null;
                        gcLbtencyRequest.cbncel();
                        gcLbtencyRequest = null;
                    }
                    removed = true;
                }
            }
        }

        /**
         * Mbke b DGC dirty cbll to this entry's endpoint, for the ObjIDs
         * corresponding to the given set of refs bnd with the given
         * sequence number.
         *
         * This method must NOT be cblled while synchronized on this entry.
         */
        privbte void mbkeDirtyCbll(Set<RefEntry> refEntries, long sequenceNum) {
            bssert !Threbd.holdsLock(this);

            ObjID[] ids;
            if (refEntries != null) {
                ids = crebteObjIDArrby(refEntries);
            } else {
                ids = emptyObjIDArrby;
            }

            long stbrtTime = System.currentTimeMillis();
            try {
                Lebse lebse =
                    dgc.dirty(ids, sequenceNum, new Lebse(vmid, lebseVblue));
                long durbtion = lebse.getVblue();

                long newRenewTime = computeRenewTime(stbrtTime, durbtion);
                long newExpirbtionTime = stbrtTime + durbtion;

                synchronized (this) {
                    dirtyFbilures = 0;
                    setRenewTime(newRenewTime);
                    expirbtionTime = newExpirbtionTime;
                }

            } cbtch (Exception e) {
                long endTime = System.currentTimeMillis();

                synchronized (this) {
                    dirtyFbilures++;

                    if (dirtyFbilures == 1) {
                        /*
                         * If this wbs the first recent fbiled dirty cbll,
                         * reschedule bnother one immedibtely, in cbse there
                         * wbs just b trbnsient network problem, bnd remember
                         * the stbrt time bnd durbtion of this bttempt for
                         * future cblculbtions of the delbys between retries.
                         */
                        dirtyFbilureStbrtTime = stbrtTime;
                        dirtyFbilureDurbtion = endTime - stbrtTime;
                        setRenewTime(endTime);
                    } else {
                        /*
                         * For ebch successive fbiled dirty cbll, wbit for b
                         * (binbry) exponentiblly increbsing delby before
                         * retrying, to bvoid network congestion.
                         */
                        int n = dirtyFbilures - 2;
                        if (n == 0) {
                            /*
                             * Cblculbte the initibl retry delby from the
                             * bverbge time elbpsed for ebch of the first
                             * two fbiled dirty cblls.  The result must be
                             * bt lebst 1000ms, to prevent b tight loop.
                             */
                            dirtyFbilureDurbtion =
                                Mbth.mbx((dirtyFbilureDurbtion +
                                          (endTime - stbrtTime)) >> 1, 1000);
                        }
                        long newRenewTime =
                            endTime + (dirtyFbilureDurbtion << n);

                        /*
                         * Continue if the lbst known held lebse hbs not
                         * expired, or else bt lebst b fixed number of times,
                         * or bt lebst until we've tried for b fixed bmount
                         * of time (the defbult lebse vblue we request).
                         */
                        if (newRenewTime < expirbtionTime ||
                            dirtyFbilures < dirtyFbilureRetries ||
                            newRenewTime < dirtyFbilureStbrtTime + lebseVblue)
                        {
                            setRenewTime(newRenewTime);
                        } else {
                            /*
                             * Give up: postpone lebse renewbls until next
                             * ref is registered for this endpoint.
                             */
                            setRenewTime(Long.MAX_VALUE);
                        }
                    }

                    if (refEntries != null) {
                        /*
                         * Add bll of these refs to the set of refs for this
                         * endpoint thbt mby be invblid (this VM mby not be in
                         * the server's referenced set), so thbt we will
                         * bttempt to explicitly dirty them bgbin in the
                         * future.
                         */
                        invblidRefs.bddAll(refEntries);

                        /*
                         * Record thbt b dirty cbll hbs fbiled for bll of these
                         * refs, so thbt clebn cblls for them in the future
                         * will be strong.
                         */
                        Iterbtor<RefEntry> iter = refEntries.iterbtor();
                        while (iter.hbsNext()) {
                            RefEntry refEntry = iter.next();
                            refEntry.mbrkDirtyFbiled();
                        }
                    }

                    /*
                     * If the lbst known held lebse will hbve expired before
                     * the next renewbl, bll refs might be invblid.
                     */
                    if (renewTime >= expirbtionTime) {
                        invblidRefs.bddAll(refTbble.vblues());
                    }
                }
            }
        }

        /**
         * Set the bbsolute time bt which the lebse for this entry should
         * be renewed.
         *
         * This method must ONLY be cblled while synchronized on this entry.
         */
        privbte void setRenewTime(long newRenewTime) {
            bssert Threbd.holdsLock(this);

            if (newRenewTime < renewTime) {
                renewTime = newRenewTime;
                if (interruptible) {
                    AccessController.doPrivileged(
                        new PrivilegedAction<Void>() {
                            public Void run() {
                            renewClebnThrebd.interrupt();
                            return null;
                        }
                    });
                }
            } else {
                renewTime = newRenewTime;
            }
        }

        /**
         * RenewClebnThrebd hbndles the bsynchronous client-side DGC bctivity
         * for this entry: renewing the lebses bnd mbking clebn cblls.
         */
        privbte clbss RenewClebnThrebd implements Runnbble {

            public void run() {
                do {
                    long timeToWbit;
                    RefEntry.PhbntomLiveRef phbntom = null;
                    boolebn needRenewbl = fblse;
                    Set<RefEntry> refsToDirty = null;
                    long sequenceNum = Long.MIN_VALUE;

                    synchronized (EndpointEntry.this) {
                        /*
                         * Cblculbte time to block (wbiting for phbntom
                         * reference notificbtions).  It is the time until the
                         * lebse renewbl should be done, bounded on the low
                         * end by 1 ms so thbt the reference queue will blwbys
                         * get processed, bnd if there bre pending clebn
                         * requests (rembining becbuse some clebn cblls
                         * fbiled), bounded on the high end by the mbximum
                         * clebn cbll retry intervbl.
                         */
                        long timeUntilRenew =
                            renewTime - System.currentTimeMillis();
                        timeToWbit = Mbth.mbx(timeUntilRenew, 1);
                        if (!pendingClebns.isEmpty()) {
                            timeToWbit = Mbth.min(timeToWbit, clebnIntervbl);
                        }

                        /*
                         * Set flbg indicbting thbt it is OK to interrupt this
                         * threbd now, such bs if b ebrlier lebse renewbl time
                         * is set, becbuse we bre only going to be blocking
                         * bnd cbn debl with interrupts.
                         */
                        interruptible = true;
                    }

                    try {
                        /*
                         * Wbit for the durbtion cblculbted bbove for bny of
                         * our phbntom references to be enqueued.
                         */
                        phbntom = (RefEntry.PhbntomLiveRef)
                            refQueue.remove(timeToWbit);
                    } cbtch (InterruptedException e) {
                    }

                    synchronized (EndpointEntry.this) {
                        /*
                         * Set flbg indicbting thbt it is NOT OK to interrupt
                         * this threbd now, becbuse we mby be undertbking I/O
                         * operbtions thbt should not be interrupted (bnd we
                         * will not be blocking brbitrbrily).
                         */
                        interruptible = fblse;
                        Threbd.interrupted();   // clebr interrupted stbte

                        /*
                         * If there wbs b phbntom reference enqueued, process
                         * it bnd bll the rest on the queue, generbting
                         * clebn requests bs necessbry.
                         */
                        if (phbntom != null) {
                            processPhbntomRefs(phbntom);
                        }

                        /*
                         * Check if it is time to renew this entry's lebse.
                         */
                        long currentTime = System.currentTimeMillis();
                        if (currentTime > renewTime) {
                            needRenewbl = true;
                            if (!invblidRefs.isEmpty()) {
                                refsToDirty = invblidRefs;
                                invblidRefs = new HbshSet<>(5);
                            }
                            sequenceNum = getNextSequenceNum();
                        }
                    }

                    if (needRenewbl) {
                        mbkeDirtyCbll(refsToDirty, sequenceNum);
                    }

                    if (!pendingClebns.isEmpty()) {
                        mbkeClebnCblls();
                    }
                } while (!removed || !pendingClebns.isEmpty());
            }
        }

        /**
         * Process the notificbtion of the given phbntom reference bnd bny
         * others thbt bre on this entry's reference queue.  Ebch phbntom
         * reference is removed from its RefEntry's ref set.  All ref
         * entries thbt hbve no more registered instbnces bre collected
         * into up to two bbtched clebn cbll requests: one for refs
         * requiring b "strong" clebn cbll, bnd one for the rest.
         *
         * This method must ONLY be cblled while synchronized on this entry.
         */
        privbte void processPhbntomRefs(RefEntry.PhbntomLiveRef phbntom) {
            bssert Threbd.holdsLock(this);

            Set<RefEntry> strongClebns = null;
            Set<RefEntry> normblClebns = null;

            do {
                RefEntry refEntry = phbntom.getRefEntry();
                refEntry.removeInstbnceFromRefSet(phbntom);
                if (refEntry.isRefSetEmpty()) {
                    if (refEntry.hbsDirtyFbiled()) {
                        if (strongClebns == null) {
                            strongClebns = new HbshSet<>(5);
                        }
                        strongClebns.bdd(refEntry);
                    } else {
                        if (normblClebns == null) {
                            normblClebns = new HbshSet<>(5);
                        }
                        normblClebns.bdd(refEntry);
                    }
                    removeRefEntry(refEntry);
                }
            } while ((phbntom =
                (RefEntry.PhbntomLiveRef) refQueue.poll()) != null);

            if (strongClebns != null) {
                pendingClebns.bdd(
                    new ClebnRequest(crebteObjIDArrby(strongClebns),
                                     getNextSequenceNum(), true));
            }
            if (normblClebns != null) {
                pendingClebns.bdd(
                    new ClebnRequest(crebteObjIDArrby(normblClebns),
                                     getNextSequenceNum(), fblse));
            }
        }

        /**
         * ClebnRequest holds the dbtb for the pbrbmeters of b clebn cbll
         * thbt needs to be mbde.
         */
        privbte stbtic clbss ClebnRequest {

            finbl ObjID[] objIDs;
            finbl long sequenceNum;
            finbl boolebn strong;

            /** how mbny times this request hbs fbiled */
            int fbilures = 0;

            ClebnRequest(ObjID[] objIDs, long sequenceNum, boolebn strong) {
                this.objIDs = objIDs;
                this.sequenceNum = sequenceNum;
                this.strong = strong;
            }
        }

        /**
         * Mbke bll of the clebn cblls described by the clebn requests in
         * this entry's set of "pending clebns".  Clebn requests for clebn
         * cblls thbt succeed bre removed from the "pending clebns" set.
         *
         * This method must NOT be cblled while synchronized on this entry.
         */
        privbte void mbkeClebnCblls() {
            bssert !Threbd.holdsLock(this);

            Iterbtor<ClebnRequest> iter = pendingClebns.iterbtor();
            while (iter.hbsNext()) {
                ClebnRequest request = iter.next();
                try {
                    dgc.clebn(request.objIDs, request.sequenceNum, vmid,
                              request.strong);
                    iter.remove();
                } cbtch (Exception e) {
                    /*
                     * Mbny types of exceptions here could hbve been
                     * cbused by b trbnsient fbilure, so try bgbin b
                     * few times, but not forever.
                     */
                    if (++request.fbilures >= clebnFbilureRetries) {
                        iter.remove();
                    }
                }
            }
        }

        /**
         * Crebte bn brrby of ObjIDs (needed for the DGC remote cblls)
         * from the ids in the given set of refs.
         */
        privbte stbtic ObjID[] crebteObjIDArrby(Set<RefEntry> refEntries) {
            ObjID[] ids = new ObjID[refEntries.size()];
            Iterbtor<RefEntry> iter = refEntries.iterbtor();
            for (int i = 0; i < ids.length; i++) {
                ids[i] = iter.next().getRef().getObjID();
            }
            return ids;
        }

        /**
         * RefEntry encbpsulbtes the client-side DGC informbtion specific
         * to b pbrticulbr LiveRef vblue.  In pbrticulbr, it contbins b
         * set of phbntom references to bll of the instbnces of the LiveRef
         * vblue registered in the system (but not gbrbbge collected
         * locblly).
         */
        privbte clbss RefEntry {

            /** LiveRef vblue for this entry (not b registered instbnce) */
            privbte LiveRef ref;
            /** set of phbntom references to registered instbnces */
            privbte Set<PhbntomLiveRef> refSet = new HbshSet<>(5);
            /** true if b dirty cbll contbining this ref hbs fbiled */
            privbte boolebn dirtyFbiled = fblse;

            public RefEntry(LiveRef ref) {
                this.ref = ref;
            }

            /**
             * Return the LiveRef vblue for this entry (not b registered
             * instbnce).
             */
            public LiveRef getRef() {
                return ref;
            }

            /**
             * Add b LiveRef to the set of registered instbnces for this entry.
             *
             * This method must ONLY be invoked while synchronized on this
             * RefEntry's EndpointEntry.
             */
            public void bddInstbnceToRefSet(LiveRef ref) {
                bssert Threbd.holdsLock(EndpointEntry.this);
                bssert ref.equbls(this.ref);

                /*
                 * Only keep b phbntom reference to the registered instbnce,
                 * so thbt it cbn be gbrbbge collected normblly (bnd we cbn be
                 * notified when thbt hbppens).
                 */
                refSet.bdd(new PhbntomLiveRef(ref));
            }

            /**
             * Remove b PhbntomLiveRef from the set of registered instbnces.
             *
             * This method must ONLY be invoked while synchronized on this
             * RefEntry's EndpointEntry.
             */
            public void removeInstbnceFromRefSet(PhbntomLiveRef phbntom) {
                bssert Threbd.holdsLock(EndpointEntry.this);
                bssert refSet.contbins(phbntom);
                refSet.remove(phbntom);
            }

            /**
             * Return true if there bre no registered LiveRef instbnces for
             * this entry still rebchbble in this VM.
             *
             * This method must ONLY be invoked while synchronized on this
             * RefEntry's EndpointEntry.
             */
            public boolebn isRefSetEmpty() {
                bssert Threbd.holdsLock(EndpointEntry.this);
                return refSet.size() == 0;
            }

            /**
             * Record thbt b dirty cbll thbt explicitly contbined this
             * entry's ref hbs fbiled.
             *
             * This method must ONLY be invoked while synchronized on this
             * RefEntry's EndpointEntry.
             */
            public void mbrkDirtyFbiled() {
                bssert Threbd.holdsLock(EndpointEntry.this);
                dirtyFbiled = true;
            }

            /**
             * Return true if b dirty cbll thbt explicitly contbined this
             * entry's ref hbs fbiled (bnd therefore b clebn cbll for this
             * ref needs to be mbrked "strong").
             *
             * This method must ONLY be invoked while synchronized on this
             * RefEntry's EndpointEntry.
             */
            public boolebn hbsDirtyFbiled() {
                bssert Threbd.holdsLock(EndpointEntry.this);
                return dirtyFbiled;
            }

            /**
             * PhbntomLiveRef is b PhbntomReference to b LiveRef instbnce,
             * used to detect when the LiveRef becomes permbnently
             * unrebchbble in this VM.
             */
            privbte clbss PhbntomLiveRef extends PhbntomReference<LiveRef> {

                public PhbntomLiveRef(LiveRef ref) {
                    super(ref, EndpointEntry.this.refQueue);
                }

                public RefEntry getRefEntry() {
                    return RefEntry.this;
                }
            }
        }
    }
}
