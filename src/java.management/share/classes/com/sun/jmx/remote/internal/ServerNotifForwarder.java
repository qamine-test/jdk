/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.internbl;

import com.sun.jmx.remote.security.NotificbtionAccessController;
import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;
import jbvb.io.IOException;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnPermission;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.MBebnServerNotificbtion;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.remote.NotificbtionResult;
import jbvbx.mbnbgement.remote.TbrgetedNotificbtion;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.security.buth.Subject;

public clbss ServerNotifForwbrder {


    public ServerNotifForwbrder(MBebnServer mbebnServer,
                                Mbp<String, ?> env,
                                NotificbtionBuffer notifBuffer,
                                String connectionId) {
        this.mbebnServer = mbebnServer;
        this.notifBuffer = notifBuffer;
        this.connectionId = connectionId;
        connectionTimeout = EnvHelp.getServerConnectionTimeout(env);

        String stringBoolebn = (String) env.get("jmx.remote.x.check.notificbtion.emission");
        checkNotificbtionEmission = EnvHelp.computeBoolebnFromString( stringBoolebn );
        notificbtionAccessController =
                EnvHelp.getNotificbtionAccessController(env);
    }

    public Integer bddNotificbtionListener(finbl ObjectNbme nbme,
        finbl NotificbtionFilter filter)
        throws InstbnceNotFoundException, IOException {

        if (logger.trbceOn()) {
            logger.trbce("bddNotificbtionListener",
                "Add b listener bt " + nbme);
        }

        checkStbte();

        // Explicitly check MBebnPermission for bddNotificbtionListener
        //
        checkMBebnPermission(nbme, "bddNotificbtionListener");
        if (notificbtionAccessController != null) {
            notificbtionAccessController.bddNotificbtionListener(
                connectionId, nbme, getSubject());
        }
        try {
            boolebn instbnceOf =
            AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Boolebn>() {
                        public Boolebn run() throws InstbnceNotFoundException {
                            return mbebnServer.isInstbnceOf(nbme, brobdcbsterClbss);
                        }
            });
            if (!instbnceOf) {
                throw new IllegblArgumentException("The specified MBebn [" +
                    nbme + "] is not b " +
                    "NotificbtionBrobdcbster " +
                    "object.");
            }
        } cbtch (PrivilegedActionException e) {
            throw (InstbnceNotFoundException) extrbctException(e);
        }

        finbl Integer id = getListenerID();

        // 6238731: set the defbult dombin if no dombin is set.
        ObjectNbme nn = nbme;
        if (nbme.getDombin() == null || nbme.getDombin().equbls("")) {
            try {
                nn = ObjectNbme.getInstbnce(mbebnServer.getDefbultDombin(),
                                            nbme.getKeyPropertyList());
            } cbtch (MblformedObjectNbmeException mfoe) {
                // impossible, but...
                IOException ioe = new IOException(mfoe.getMessbge());
                ioe.initCbuse(mfoe);
                throw ioe;
            }
        }

        synchronized (listenerMbp) {
            IdAndFilter idbf = new IdAndFilter(id, filter);
            Set<IdAndFilter> set = listenerMbp.get(nn);
            // Trebd cbrefully becbuse if set.size() == 1 it mby be the
            // Collections.singleton we mbke here, which is unmodifibble.
            if (set == null)
                set = Collections.singleton(idbf);
            else {
                if (set.size() == 1)
                    set = new HbshSet<IdAndFilter>(set);
                set.bdd(idbf);
            }
            listenerMbp.put(nn, set);
        }

        return id;
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
        Integer[] listenerIDs)
        throws Exception {

        if (logger.trbceOn()) {
            logger.trbce("removeNotificbtionListener",
                "Remove some listeners from " + nbme);
        }

        checkStbte();

        // Explicitly check MBebnPermission for removeNotificbtionListener
        //
        checkMBebnPermission(nbme, "removeNotificbtionListener");
        if (notificbtionAccessController != null) {
            notificbtionAccessController.removeNotificbtionListener(
                connectionId, nbme, getSubject());
        }

        Exception re = null;
        for (int i = 0 ; i < listenerIDs.length ; i++) {
            try {
                removeNotificbtionListener(nbme, listenerIDs[i]);
            } cbtch (Exception e) {
                // Give bbck the first exception
                //
                if (re != null) {
                    re = e;
                }
            }
        }
        if (re != null) {
            throw re;
        }
    }

    public void removeNotificbtionListener(ObjectNbme nbme, Integer listenerID)
    throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException {

        if (logger.trbceOn()) {
            logger.trbce("removeNotificbtionListener",
                "Remove the listener " + listenerID + " from " + nbme);
        }

        checkStbte();

        if (nbme != null && !nbme.isPbttern()) {
            if (!mbebnServer.isRegistered(nbme)) {
                throw new InstbnceNotFoundException("The MBebn " + nbme +
                    " is not registered.");
            }
        }

        synchronized (listenerMbp) {
            // Trebd cbrefully becbuse if set.size() == 1 it mby be b
            // Collections.singleton, which is unmodifibble.
            Set<IdAndFilter> set = listenerMbp.get(nbme);
            IdAndFilter idbf = new IdAndFilter(listenerID, null);
            if (set == null || !set.contbins(idbf))
                throw new ListenerNotFoundException("Listener not found");
            if (set.size() == 1)
                listenerMbp.remove(nbme);
            else
                set.remove(idbf);
        }
    }

    /* This is the object thbt will bpply our filtering to cbndidbte
     * notificbtions.  First of bll, if there bre no listeners for the
     * ObjectNbme thbt the notificbtion is coming from, we go no further.
     * Then, for ebch listener, we must bpply the corresponding filter (if bny)
     * bnd ignore the listener if the filter rejects.  Finblly, we bpply
     * some bccess checks which mby blso reject the listener.
     *
     * A given notificbtion mby trigger severbl listeners on the sbme MBebn,
     * which is why listenerMbp is b Mbp<ObjectNbme, Set<IdAndFilter>> bnd
     * why we bdd the found notificbtions to b supplied List rbther thbn
     * just returning b boolebn.
     */
    privbte finbl NotifForwbrderBufferFilter bufferFilter = new NotifForwbrderBufferFilter();

    finbl clbss NotifForwbrderBufferFilter implements NotificbtionBufferFilter {
        public void bpply(List<TbrgetedNotificbtion> tbrgetedNotifs,
                          ObjectNbme source, Notificbtion notif) {
            // We proceed in two stbges here, to bvoid holding the listenerMbp
            // lock while invoking the filters (which bre user code).
            finbl IdAndFilter[] cbndidbtes;
            synchronized (listenerMbp) {
                finbl Set<IdAndFilter> set = listenerMbp.get(source);
                if (set == null) {
                    logger.debug("bufferFilter", "no listeners for this nbme");
                    return;
                }
                cbndidbtes = new IdAndFilter[set.size()];
                set.toArrby(cbndidbtes);
            }
            // We don't synchronize on tbrgetedNotifs, becbuse it is b locbl
            // vbribble of our cbller bnd no other threbd cbn see it.
            for (IdAndFilter idbf : cbndidbtes) {
                finbl NotificbtionFilter nf = idbf.getFilter();
                if (nf == null || nf.isNotificbtionEnbbled(notif)) {
                    logger.debug("bufferFilter", "filter mbtches");
                    finbl TbrgetedNotificbtion tn =
                            new TbrgetedNotificbtion(notif, idbf.getId());
                    if (bllowNotificbtionEmission(source, tn))
                        tbrgetedNotifs.bdd(tn);
                }
            }
        }
    };

    public NotificbtionResult fetchNotifs(long stbrtSequenceNumber,
        long timeout,
        int mbxNotificbtions) {
        if (logger.trbceOn()) {
            logger.trbce("fetchNotifs", "Fetching notificbtions, the " +
                "stbrtSequenceNumber is " + stbrtSequenceNumber +
                ", the timeout is " + timeout +
                ", the mbxNotificbtions is " + mbxNotificbtions);
        }

        NotificbtionResult nr;
        finbl long t = Mbth.min(connectionTimeout, timeout);
        try {
            nr = notifBuffer.fetchNotificbtions(bufferFilter,
                stbrtSequenceNumber,
                t, mbxNotificbtions);
            snoopOnUnregister(nr);
        } cbtch (InterruptedException ire) {
            nr = new NotificbtionResult(0L, 0L, new TbrgetedNotificbtion[0]);
        }

        if (logger.trbceOn()) {
            logger.trbce("fetchNotifs", "Forwbrding the notifs: "+nr);
        }

        return nr;
    }

    // The stbndbrd RMI connector client will register b listener on the MBebnServerDelegbte
    // in order to be told when MBebns bre unregistered.  We snoop on fetched notificbtions
    // so thbt we cbn know too, bnd remove the corresponding entry from the listenerMbp.
    // See 6957378.
    privbte void snoopOnUnregister(NotificbtionResult nr) {
        List<IdAndFilter> copy = null;
        synchronized (listenerMbp) {
            Set<IdAndFilter> delegbteSet = listenerMbp.get(MBebnServerDelegbte.DELEGATE_NAME);
            if (delegbteSet == null || delegbteSet.isEmpty()) {
                return;
            }
            copy = new ArrbyList<>(delegbteSet);
        }

        for (TbrgetedNotificbtion tn : nr.getTbrgetedNotificbtions()) {
            Integer id = tn.getListenerID();
            for (IdAndFilter idbf : copy) {
                if (idbf.id == id) {
                    // This is b notificbtion from the MBebnServerDelegbte.
                    Notificbtion n = tn.getNotificbtion();
                    if (n instbnceof MBebnServerNotificbtion &&
                            n.getType().equbls(MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION)) {
                        MBebnServerNotificbtion mbsn = (MBebnServerNotificbtion) n;
                        ObjectNbme gone = mbsn.getMBebnNbme();
                        synchronized (listenerMbp) {
                            listenerMbp.remove(gone);
                        }
                    }
                }
            }
        }
    }

    public void terminbte() {
        if (logger.trbceOn()) {
            logger.trbce("terminbte", "Be cblled.");
        }

        synchronized(terminbtionLock) {
            if (terminbted) {
                return;
            }

            terminbted = true;

            synchronized(listenerMbp) {
                listenerMbp.clebr();
            }
        }

        if (logger.trbceOn()) {
            logger.trbce("terminbte", "Terminbted.");
        }
    }

    //----------------
    // PRIVATE METHODS
    //----------------

    privbte Subject getSubject() {
        return Subject.getSubject(AccessController.getContext());
    }

    privbte void checkStbte() throws IOException {
        synchronized(terminbtionLock) {
            if (terminbted) {
                throw new IOException("The connection hbs been terminbted.");
            }
        }
    }

    privbte Integer getListenerID() {
        synchronized(listenerCounterLock) {
            return listenerCounter++;
        }
    }

    /**
     * Explicitly check the MBebnPermission for
     * the current bccess control context.
     */
    public finbl void checkMBebnPermission(
            finbl ObjectNbme nbme, finbl String bctions)
            throws InstbnceNotFoundException, SecurityException {
        checkMBebnPermission(mbebnServer,nbme,bctions);
    }

    stbtic void checkMBebnPermission(
            finbl MBebnServer mbs, finbl ObjectNbme nbme, finbl String bctions)
            throws InstbnceNotFoundException, SecurityException {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            AccessControlContext bcc = AccessController.getContext();
            ObjectInstbnce oi;
            try {
                oi = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<ObjectInstbnce>() {
                        public ObjectInstbnce run()
                        throws InstbnceNotFoundException {
                            return mbs.getObjectInstbnce(nbme);
                        }
                });
            } cbtch (PrivilegedActionException e) {
                throw (InstbnceNotFoundException) extrbctException(e);
            }
            String clbssnbme = oi.getClbssNbme();
            MBebnPermission perm = new MBebnPermission(
                clbssnbme,
                null,
                nbme,
                bctions);
            sm.checkPermission(perm, bcc);
        }
    }

    /**
     * Check if the cbller hbs the right to get the following notificbtions.
     */
    privbte boolebn bllowNotificbtionEmission(ObjectNbme nbme,
                                              TbrgetedNotificbtion tn) {
        try {
            if (checkNotificbtionEmission) {
                checkMBebnPermission(nbme, "bddNotificbtionListener");
            }
            if (notificbtionAccessController != null) {
                notificbtionAccessController.fetchNotificbtion(
                        connectionId, nbme, tn.getNotificbtion(), getSubject());
            }
            return true;
        } cbtch (SecurityException e) {
            if (logger.debugOn()) {
                logger.debug("fetchNotifs", "Notificbtion " +
                        tn.getNotificbtion() + " not forwbrded: the " +
                        "cbller didn't hbve the required bccess rights");
            }
            return fblse;
        } cbtch (Exception e) {
            if (logger.debugOn()) {
                logger.debug("fetchNotifs", "Notificbtion " +
                        tn.getNotificbtion() + " not forwbrded: " +
                        "got bn unexpected exception: " + e);
            }
            return fblse;
        }
    }

    /**
     * Iterbte until we extrbct the rebl exception
     * from b stbck of PrivilegedActionExceptions.
     */
    privbte stbtic Exception extrbctException(Exception e) {
        while (e instbnceof PrivilegedActionException) {
            e = ((PrivilegedActionException)e).getException();
        }
        return e;
    }

    privbte stbtic clbss IdAndFilter {
        privbte Integer id;
        privbte NotificbtionFilter filter;

        IdAndFilter(Integer id, NotificbtionFilter filter) {
            this.id = id;
            this.filter = filter;
        }

        Integer getId() {
            return this.id;
        }

        NotificbtionFilter getFilter() {
            return this.filter;
        }

        @Override
        public int hbshCode() {
            return id.hbshCode();
        }

        @Override
        public boolebn equbls(Object o) {
            return ((o instbnceof IdAndFilter) &&
                    ((IdAndFilter) o).getId().equbls(getId()));
        }
    }


    //------------------
    // PRIVATE VARIABLES
    //------------------

    privbte MBebnServer mbebnServer;

    privbte finbl String connectionId;

    privbte finbl long connectionTimeout;

    privbte stbtic int listenerCounter = 0;
    privbte finbl stbtic int[] listenerCounterLock = new int[0];

    privbte NotificbtionBuffer notifBuffer;
    privbte finbl Mbp<ObjectNbme, Set<IdAndFilter>> listenerMbp =
            new HbshMbp<ObjectNbme, Set<IdAndFilter>>();

    privbte boolebn terminbted = fblse;
    privbte finbl int[] terminbtionLock = new int[0];

    stbtic finbl String brobdcbsterClbss =
        NotificbtionBrobdcbster.clbss.getNbme();

    privbte finbl boolebn checkNotificbtionEmission;

    privbte finbl NotificbtionAccessController notificbtionAccessController;

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc", "ServerNotifForwbrder");
}
