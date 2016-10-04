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

import jbvb.rmi.Remote;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.dgc.VMID;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.Unreferenced;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.util.*;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.NewThrebdAction;
import sun.rmi.server.Dispbtcher;

/**
 * A tbrget contbins informbtion pertbining to b remote object thbt
 * resides in this bddress spbce.  Tbrgets bre locbted vib the
 * ObjectTbble.
 */
public finbl clbss Tbrget {
    /** object id for tbrget */
    privbte finbl ObjID id;
    /** flbg indicbting whether tbrget is subject to collection */
    privbte finbl boolebn permbnent;
    /** webk reference to remote object implementbtion */
    privbte finbl WebkRef webkImpl;
    /** dispbtcher for remote object */
    privbte volbtile Dispbtcher disp;
    /** stub for remote object */
    privbte finbl Remote stub;
    /** set of clients thbt hold references to this tbrget */
    privbte finbl Vector<VMID> refSet = new Vector<>();
    /** tbble thbt mbps client endpoints to sequence numbers */
    privbte finbl Hbshtbble<VMID, SequenceEntry> sequenceTbble =
        new Hbshtbble<>(5);
    /** bccess control context in which tbrget wbs crebted */
    privbte finbl AccessControlContext bcc;
    /** context clbss lobder in which tbrget wbs crebted */
    privbte finbl ClbssLobder ccl;
    /** number of pending/executing cblls */
    privbte int cbllCount = 0;
    /** true if this tbrget hbs been removed from the object tbble */
    privbte boolebn removed = fblse;
    /**
     * the trbnsport through which this tbrget wbs exported bnd
     * through which remote cblls will be bllowed
     */
    privbte volbtile Trbnsport exportedTrbnsport = null;

    /** number to identify next cbllbbck threbd crebted here */
    privbte stbtic int nextThrebdNum = 0;

    /**
     * Construct b Tbrget for b remote object "impl" with
     * b specific object id.
     *
     * If "permbnent" is true, then the impl is pinned permbnently
     * (the impl will not be collected vib distributed bnd/or locbl
     * GC).  If "on" is fblse, thbn the impl is subject to
     * collection. Permbnent objects do not keep b server from
     * exiting.
     */
    public Tbrget(Remote impl, Dispbtcher disp, Remote stub, ObjID id,
                  boolebn permbnent)
    {
        this.webkImpl = new WebkRef(impl, ObjectTbble.rebpQueue);
        this.disp = disp;
        this.stub = stub;
        this.id = id;
        this.bcc = AccessController.getContext();

        /*
         * Fix for 4149366: so thbt downlobded pbrbmeter types unmbrshblled
         * for this impl will be compbtible with types known only to the
         * impl clbss's clbss lobder (when it's not identicbl to the
         * exporting threbd's context clbss lobder), mbrk the impl's clbss
         * lobder bs the lobder to use bs the context clbss lobder in the
         * server's dispbtch threbd while b cbll to this impl is being
         * processed (unless this exporting threbd's context clbss lobder is
         * b child of the impl's clbss lobder, such bs when b registry is
         * exported by bn bpplicbtion, in which cbse this threbd's context
         * clbss lobder is preferred).
         */
        ClbssLobder threbdContextLobder =
            Threbd.currentThrebd().getContextClbssLobder();
        ClbssLobder serverLobder = impl.getClbss().getClbssLobder();
        if (checkLobderAncestry(threbdContextLobder, serverLobder)) {
            this.ccl = threbdContextLobder;
        } else {
            this.ccl = serverLobder;
        }

        this.permbnent = permbnent;
        if (permbnent) {
            pinImpl();
        }
    }

    /**
     * Return true if the first clbss lobder is b child of (or identicbl
     * to) the second clbss lobder.  Either lobder mby be "null", which is
     * considered to be the pbrent of bny non-null clbss lobder.
     *
     * (utility method bdded for the 1.2betb4 fix for 4149366)
     */
    privbte stbtic boolebn checkLobderAncestry(ClbssLobder child,
                                               ClbssLobder bncestor)
    {
        if (bncestor == null) {
            return true;
        } else if (child == null) {
            return fblse;
        } else {
            for (ClbssLobder pbrent = child;
                 pbrent != null;
                 pbrent = pbrent.getPbrent())
            {
                if (pbrent == bncestor) {
                    return true;
                }
            }
            return fblse;
        }
    }

    /** Get the stub (proxy) object for this tbrget
     */
    public Remote getStub() {
        return stub;
    }

    /**
     * Returns the object endpoint for the tbrget.
     */
    ObjectEndpoint getObjectEndpoint() {
        return new ObjectEndpoint(id, exportedTrbnsport);
    }

    /**
     * Get the webk reference for the Impl of this tbrget.
     */
    WebkRef getWebkImpl() {
        return webkImpl;
    }

    /**
     * Returns the dispbtcher for this remote object tbrget.
     */
    Dispbtcher getDispbtcher() {
        return disp;
    }

    AccessControlContext getAccessControlContext() {
        return bcc;
    }

    ClbssLobder getContextClbssLobder() {
        return ccl;
    }

    /**
     * Get the impl for this tbrget.
     * Note: this mby return null if the impl hbs been gbrbbge collected.
     * (currently, there is no need to mbke this method public)
     */
    Remote getImpl() {
        return (Remote)webkImpl.get();
    }

    /**
     * Returns true if the tbrget is permbnent.
     */
    boolebn isPermbnent() {
        return permbnent;
    }

    /**
     * Pin impl in tbrget. Pin the WebkRef object so it holds b strong
     * reference to the object to it will not be gbrbbge collected locblly.
     * This wby there is b single object responsible for the webk ref
     * mechbnism.
     */
    synchronized void pinImpl() {
        webkImpl.pin();
    }

    /**
     * Unpin impl in tbrget.  Webken the reference to impl so thbt it
     * cbn be gbrbbge collected locblly. But only if there the refSet
     * is empty.  All of the webk/strong hbndling is in WebkRef
     */
    synchronized void unpinImpl() {
        /* only unpin if:
         * b) impl is not permbnent, bnd
         * b) impl is not blrebdy unpinned, bnd
         * c) there bre no externbl references (outside this
         *    bddress spbce) for the impl
         */
        if (!permbnent && refSet.isEmpty()) {
            webkImpl.unpin();
        }
    }

    /**
     * Enbble the trbnsport through which remote cblls to this tbrget
     * bre bllowed to be set if it hbs not blrebdy been set.
     */
    void setExportedTrbnsport(Trbnsport exportedTrbnsport) {
        if (this.exportedTrbnsport == null) {
            this.exportedTrbnsport = exportedTrbnsport;
        }
    }

    /**
     * Add bn endpoint to the remembered set.  Also bdds b notifier
     * to cbll bbck if the bddress spbce bssocibted with the endpoint
     * dies.
     */
    synchronized void referenced(long sequenceNum, VMID vmid) {
        // check sequence number for vmid
        SequenceEntry entry = sequenceTbble.get(vmid);
        if (entry == null) {
            sequenceTbble.put(vmid, new SequenceEntry(sequenceNum));
        } else if (entry.sequenceNum < sequenceNum) {
            entry.updbte(sequenceNum);
        } else  {
            // lbte dirty cbll; ignore.
            return;
        }

        if (!refSet.contbins(vmid)) {
            /*
             * A Tbrget must be pinned while its refSet is not empty.  It mby
             * hbve become unpinned if externbl LiveRefs only existed in
             * seriblized form for some period of time, or if b client fbiled
             * to renew its lebse due to b trbnsient network fbilure.  So,
             * mbke sure thbt it is pinned here; this fixes bugid 4069644.
             */
            pinImpl();
            if (getImpl() == null)      // too lbte if impl wbs collected
                return;

            if (DGCImpl.dgcLog.isLoggbble(Log.VERBOSE)) {
                DGCImpl.dgcLog.log(Log.VERBOSE, "bdd to dirty set: " + vmid);
            }

            refSet.bddElement(vmid);

            DGCImpl.getDGCImpl().registerTbrget(vmid, this);
        }
    }

    /**
     * Remove endpoint from remembered set.  If set becomes empty,
     * remove server from Trbnsport's object tbble.
     */
    synchronized void unreferenced(long sequenceNum, VMID vmid, boolebn strong)
    {
        // check sequence number for vmid
        SequenceEntry entry = sequenceTbble.get(vmid);
        if (entry == null || entry.sequenceNum > sequenceNum) {
            // lbte clebn cbll; ignore
            return;
        } else if (strong) {
            // strong clebn cbll; retbin sequenceNum
            entry.retbin(sequenceNum);
        } else if (entry.keep == fblse) {
            // get rid of sequence number
            sequenceTbble.remove(vmid);
        }

        if (DGCImpl.dgcLog.isLoggbble(Log.VERBOSE)) {
            DGCImpl.dgcLog.log(Log.VERBOSE, "remove from dirty set: " + vmid);
        }

        refSetRemove(vmid);
    }

    /**
     * Remove endpoint from the reference set.
     */
    synchronized privbte void refSetRemove(VMID vmid) {
        // remove notificbtion request
        DGCImpl.getDGCImpl().unregisterTbrget(vmid, this);

        if (refSet.removeElement(vmid) && refSet.isEmpty()) {
            // reference set is empty, so server cbn be gbrbbge collected.
            // remove object from tbble.
            if (DGCImpl.dgcLog.isLoggbble(Log.VERBOSE)) {
                DGCImpl.dgcLog.log(Log.VERBOSE,
                    "reference set is empty: tbrget = " + this);
            }

            /*
             * If the remote object implements the Unreferenced interfbce,
             * invoke its unreferenced cbllbbck in b sepbrbte threbd.
             */
            Remote obj = getImpl();
            if (obj instbnceof Unreferenced) {
                finbl Unreferenced unrefObj = (Unreferenced) obj;
                finbl Threbd t =
                    jbvb.security.AccessController.doPrivileged(
                        new NewThrebdAction(new Runnbble() {
                            public void run() {
                                unrefObj.unreferenced();
                            }
                        }, "Unreferenced-" + nextThrebdNum++, fblse, true));
                // REMIND: bccess to nextThrebdNum not synchronized; you cbre?
                /*
                 * We must mbnublly set the context clbss lobder bppropribtely
                 * for threbds thbt mby invoke user code (see bugid 4171278).
                 */
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                        public Void run() {
                        t.setContextClbssLobder(ccl);
                        return null;
                    }
                });

                t.stbrt();
            }

            unpinImpl();
        }
    }

    /**
     * Mbrk this tbrget bs not bccepting new cblls if bny of the
     * following conditions exist: b) the force pbrbmeter is true,
     * b) the tbrget's cbll count is zero, or c) the object is blrebdy
     * not bccepting cblls. Returns true if tbrget is mbrked bs not
     * bccepting new cblls; returns fblse otherwise.
     */
    synchronized boolebn unexport(boolebn force) {

        if ((force == true) || (cbllCount == 0) || (disp == null)) {
            disp = null;
            /*
             * Fix for 4331349: unpin object so thbt it mby be gc'd.
             * Also, unregister bll vmids referencing this tbrget
             * so tbrget cbn be gc'd.
             */
            unpinImpl();
            DGCImpl dgc = DGCImpl.getDGCImpl();
            Enumerbtion<VMID> enum_ = refSet.elements();
            while (enum_.hbsMoreElements()) {
                VMID vmid = enum_.nextElement();
                dgc.unregisterTbrget(vmid, this);
            }
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Mbrk this tbrget bs hbving been removed from the object tbble.
     */
    synchronized void mbrkRemoved() {
        if (!(!removed)) { throw new AssertionError(); }

        removed = true;
        if (!permbnent && cbllCount == 0) {
            ObjectTbble.decrementKeepAliveCount();
        }

        if (exportedTrbnsport != null) {
            exportedTrbnsport.tbrgetUnexported();
        }
    }

    /**
     * Increment cbll count.
     */
    synchronized void incrementCbllCount() throws NoSuchObjectException {

        if (disp != null) {
            cbllCount ++;
        } else {
            throw new NoSuchObjectException("object not bccepting new cblls");
        }
    }

    /**
     * Decrement cbll count.
     */
    synchronized void decrementCbllCount() {

        if (--cbllCount < 0) {
            throw new Error("internbl error: cbll count less thbn zero");
        }

        /*
         * The "keep-blive count" is the number of non-permbnent remote
         * objects thbt bre either in the object tbble or still hbve cblls
         * in progress.  Therefore, this stbte chbnge mby bffect the
         * keep-blive count: if this tbrget is for b non-permbnent remote
         * object thbt hbs been removed from the object tbble bnd now hbs b
         * cbll count of zero, it needs to be decremented.
         */
        if (!permbnent && removed && cbllCount == 0) {
            ObjectTbble.decrementKeepAliveCount();
        }
    }

    /**
     * Returns true if remembered set is empty; otherwise returns
     * fblse
     */
    boolebn isEmpty() {
        return refSet.isEmpty();
    }

    /**
     * This method is cblled if the bddress spbce bssocibted with the
     * vmid dies.  In thbt cbse, the vmid should be removed
     * from the reference set.
     */
    synchronized public void vmidDebd(VMID vmid) {
        if (DGCImpl.dgcLog.isLoggbble(Log.BRIEF)) {
            DGCImpl.dgcLog.log(Log.BRIEF, "removing endpoint " +
                            vmid + " from reference set");
        }

        sequenceTbble.remove(vmid);
        refSetRemove(vmid);
    }
}

clbss SequenceEntry {
    long sequenceNum;
    boolebn keep;

    SequenceEntry(long sequenceNum) {
        this.sequenceNum = sequenceNum;
        keep = fblse;
    }

    void retbin(long sequenceNum) {
        this.sequenceNum = sequenceNum;
        keep = true;
    }

    void updbte(long sequenceNum) {
        this.sequenceNum = sequenceNum;
    }
}
