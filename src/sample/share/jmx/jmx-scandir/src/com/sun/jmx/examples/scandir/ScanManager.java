/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.jmx.exbmples.scbndir;

import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte.*;
import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import com.sun.jmx.exbmples.scbndir.config.ScbnMbnbgerConfig;
import jbvb.io.File;

import jbvb.io.IOException;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.EnumSet;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Timer;
import jbvb.util.TimerTbsk;
import jbvb.util.concurrent.BlockingQueue;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentLinkedQueue;
import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvb.util.concurrent.Sembphore;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.AttributeChbngeNotificbtion;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.JMException;
import jbvbx.mbnbgement.JMX;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * <p>
 * The <code>ScbnMbnbger</code> is responsible for bpplying b configurbtion,
 * stbrting bnd scheduling directory scbns, bnd reporting bpplicbtion stbte.
 * </p>
 * <p>
 * The ScbnMbnbger MBebn is b singleton MBebn which controls
 * scbn session. The ScbnMbnbger nbme is defined by
 * {@link #SCAN_MANAGER_NAME ScbnMbnbger.SCAN_MANAGER_NAME}.
 * </p>
 * <p>
 * The <code>ScbnMbnbger</code> MBebn is the entry point of the <i>scbndir</i>
 * bpplicbtion mbnbgement interfbce. It is from this MBebn thbt bll other MBebns
 * will be crebted bnd registered.
 * </p>
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss ScbnMbnbger implements ScbnMbnbgerMXBebn,
        NotificbtionEmitter, MBebnRegistrbtion {

    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(ScbnMbnbger.clbss.getNbme());

    /**
     * The nbme of the ScbnMbnbger singleton MBebn.
     **/
    public finbl stbtic ObjectNbme SCAN_MANAGER_NAME =
            mbkeSingletonNbme(ScbnMbnbgerMXBebn.clbss);

    /**
     * Sequence number used for sending notificbtions. We use this
     * sequence number throughout the bpplicbtion.
     **/
    privbte stbtic long seqNumber=0;

    /**
     * The NotificbtionBrobdcbsterSupport object used to hbndle
     * listener registrbtion.
     **/
    privbte finbl NotificbtionBrobdcbsterSupport brobdcbster;

    /**
     * The MBebnServer in which this MBebn is registered. We obtbin
     * this reference by implementing the {@link MBebnRegistrbtion}
     * interfbce.
     **/
    privbte volbtile MBebnServer mbebnServer;

    /**
     * A queue of pending notificbtions we bre bbout to send.
     * We're using b BlockingQueue in order to bvoid sending
     * notificbtions from within b synchronized block.
     **/
    privbte finbl BlockingQueue<Notificbtion> pendingNotifs;

    /**
     * The stbte of the scbn session.
     **/
    privbte volbtile ScbnStbte stbte = STOPPED;

    /**
     * The list of DirectoryScbnnerMBebn thbt bre run by b scbn session.
     **/
    privbte finbl Mbp<ObjectNbme,DirectoryScbnnerMXBebn> scbnmbp;

    /**
     * The list of ScbnDirConfigMXBebn thbt were crebted by this MBebn.
     **/
    privbte finbl Mbp<ObjectNbme, ScbnDirConfigMXBebn> configmbp;

    // The ResultLogMbnbger for this bpplicbtion.
    privbte finbl ResultLogMbnbger log;

    /**
     * We use b sembphore to ensure proper sequencing of exclusive
     * bction. The logic we hbve implemented is to fbil - rbther
     * thbn block, if bn exclusive bction is blrebdy in progress.
     **/
    privbte finbl Sembphore sequencer = new Sembphore(1);

    // A proxy to the current ScbnDirConfigMXBebn which holds the current
    // configurbtion dbtb.
    //
    privbte volbtile ScbnDirConfigMXBebn config = null;

    // Avoid to write pbrbmeters twices when crebting b new ConcurrentHbshMbp.
    //
    privbte stbtic <K, V> Mbp<K, V> newConcurrentHbshMbp() {
        return new ConcurrentHbshMbp<K, V>();
    }

    // Avoid to write pbrbmeters twices when crebting b new HbshMbp.
    //
    privbte stbtic <K, V> Mbp<K, V> newHbshMbp() {
        return new HbshMbp<K, V>();
    }

    /**
     * Crebtes b defbult singleton ObjectNbme for b given clbss.
     * @pbrbm clbzz The interfbce clbss of the MBebn for which we wbnt to obtbin
     *        b defbult singleton nbme, or its implementbtion clbss.
     *        Give one or the other depending on whbt you wish to see in
     *        the vblue of the key {@code type=}.
     * @return A defbult singleton nbme for b singleton MBebn clbss.
     * @throws IllegblArgumentException if the nbme cbn't be crebted
     *         for some unfbthombble rebson (e.g. bn unexpected
     *         exception wbs rbised).
     **/
    public finbl stbtic ObjectNbme mbkeSingletonNbme(Clbss clbzz) {
        try {
            finbl Pbckbge p = clbzz.getPbckbge();
            finbl String pbckbgeNbme = (p==null)?null:p.getNbme();
            finbl String clbssNbme   = clbzz.getSimpleNbme();
            finbl String dombin;
            if (pbckbgeNbme == null || pbckbgeNbme.length()==0) {
                // We use b reference to ScbnDirAgent.clbss to ebse
                // to keep trbck of possible clbss renbming.
                dombin = ScbnDirAgent.clbss.getSimpleNbme();
            } else {
                dombin = pbckbgeNbme;
            }
            finbl ObjectNbme nbme = new ObjectNbme(dombin,"type",clbssNbme);
            return nbme;
        } cbtch (Exception x) {
            finbl IllegblArgumentException ibe =
                    new IllegblArgumentException(String.vblueOf(clbzz),x);
            throw ibe;
        }
    }

    /**
     * Crebtes b defbult ObjectNbme with keys <code>type=</code> bnd
     * <code>nbme=</code> for bn instbnce of b given MBebn interfbce clbss.
     * @pbrbm clbzz The interfbce clbss of the MBebn for which we wbnt to obtbin
     *        b defbult nbme, or its implementbtion clbss.
     *        Give one or the other depending on whbt you wish to see in
     *        the vblue of the key {@code type=}.
     * @pbrbm nbme The vblue of the <code>nbme=</code> key.
     * @return A defbult nbme for bn instbnce of the given MBebn interfbce clbss.
     * @throws IllegblArgumentException if the nbme cbn't be crebted.
     *         (e.g. bn unexpected exception wbs rbised).
     **/
    public stbtic finbl ObjectNbme mbkeMBebnNbme(Clbss clbzz, String nbme) {
        try {
            return ObjectNbme.
                getInstbnce(mbkeSingletonNbme(clbzz)
                        .toString()+",nbme="+nbme);
        } cbtch (MblformedObjectNbmeException x) {
            finbl IllegblArgumentException ibe =
                    new IllegblArgumentException(String.vblueOf(nbme),x);
            throw ibe;
        }
    }

    /**
     * Return the ObjectNbme for b DirectoryScbnnerMXBebn of thbt nbme.
     * This is {@code mbkeMBebnNbme(DirectoryScbnnerMXBebn.clbss,nbme)}.
     * @pbrbm nbme The vblue of the <code>nbme=</code> key.
     * @return the ObjectNbme for b DirectoryScbnnerMXBebn of thbt nbme.
     */
    public stbtic finbl ObjectNbme mbkeDirectoryScbnnerNbme(String nbme) {
        return mbkeMBebnNbme(DirectoryScbnnerMXBebn.clbss,nbme);
    }

    /**
     * Return the ObjectNbme for b {@code ScbnDirConfigMXBebn} of thbt nbme.
     * This is {@code mbkeMBebnNbme(ScbnDirConfigMXBebn.clbss,nbme)}.
     * @pbrbm nbme The vblue of the <code>nbme=</code> key.
     * @return the ObjectNbme for b {@code ScbnDirConfigMXBebn} of thbt nbme.
     */
    public stbtic finbl ObjectNbme mbkeScbnDirConfigNbme(String nbme) {
        return mbkeMBebnNbme(ScbnDirConfigMXBebn.clbss,nbme);
    }

    /**
     * Crebte bnd register b new singleton instbnce of the ScbnMbnbger
     * MBebn in the given {@link MBebnServerConnection}.
     * @pbrbm mbs The MBebnServer in which the new singleton instbnce
     *         should be crebted.
     * @throws JMException The MBebnServer connection rbised bn exception
     *         while trying to instbntibte bnd register the singleton MBebn
     *         instbnce.
     * @throws IOException There wbs b connection problem while trying to
     *         communicbte with the underlying MBebnServer.
     * @return A proxy for the registered MBebn.
     **/
    public stbtic ScbnMbnbgerMXBebn register(MBebnServerConnection mbs)
        throws IOException, JMException {
        finbl ObjectInstbnce moi =
                mbs.crebteMBebn(ScbnMbnbger.clbss.getNbme(),SCAN_MANAGER_NAME);
        finbl ScbnMbnbgerMXBebn proxy =
                JMX.newMXBebnProxy(mbs,moi.getObjectNbme(),
                                  ScbnMbnbgerMXBebn.clbss,true);
        return proxy;
    }

    /**
     * Crebtes b new {@code ScbnMbnbgerMXBebn} proxy over the given
     * {@code MBebnServerConnection}. Does not check whether b
     * {@code ScbnMbnbgerMXBebn}
     * is bctublly registered in thbt {@code MBebnServerConnection}.
     * @return b new {@code ScbnMbnbgerMXBebn} proxy.
     * @pbrbm mbs The {@code MBebnServerConnection} which holds the
     * {@code ScbnMbnbgerMXBebn} to proxy.
     */
    public stbtic ScbnMbnbgerMXBebn
            newSingletonProxy(MBebnServerConnection mbs) {
        finbl ScbnMbnbgerMXBebn proxy =
                JMX.newMXBebnProxy(mbs,SCAN_MANAGER_NAME,
                                  ScbnMbnbgerMXBebn.clbss,true);
        return proxy;
    }

    /**
     * Crebtes b new {@code ScbnMbnbgerMXBebn} proxy over the plbtform
     * {@code MBebnServer}. This is equivblent to
     * {@code newSingletonProxy(MbnbgementFbctory.getPlbtformMBebnServer())}.
     * @return b new {@code ScbnMbnbgerMXBebn} proxy.
     **/
    public stbtic ScbnMbnbgerMXBebn newSingletonProxy() {
        return newSingletonProxy(MbnbgementFbctory.getPlbtformMBebnServer());
    }

    /**
     * Crebte bnd register b new singleton instbnce of the ScbnMbnbger
     * MBebn in the given {@link MBebnServerConnection}.
     * @throws JMException The MBebnServer connection rbised bn exception
     *         while trying to instbntibte bnd register the singleton MBebn
     *         instbnce.
     * @throws IOException There wbs b connection problem while trying to
     *         communicbte with the underlying MBebnServer.
     * @return A proxy for the registered MBebn.
     **/
    public stbtic ScbnMbnbgerMXBebn register()
        throws IOException, JMException {
        finbl MBebnServer mbs = MbnbgementFbctory.getPlbtformMBebnServer();
        return register(mbs);
    }

    /**
     * Crebte b new ScbnMbnbger MBebn
     **/
    public ScbnMbnbger() {
        brobdcbster = new NotificbtionBrobdcbsterSupport();
        pendingNotifs = new LinkedBlockingQueue<Notificbtion>(100);
        scbnmbp = newConcurrentHbshMbp();
        configmbp = newConcurrentHbshMbp();
        log = new ResultLogMbnbger();
    }


    // Crebtes b new DirectoryScbnnerMXBebn, from the given configurbtion dbtb.
    DirectoryScbnnerMXBebn crebteDirectoryScbnner(DirectoryScbnnerConfig config) {
            return new DirectoryScbnner(config,log);
    }

    // Applies b configurbtion.
    // throws IllegblStbteException if lock cbn't be bcquired.
    // Unregisters bll existing directory scbnners, the crebte bnd registers
    // new directory scbnners bccording to the given config.
    // Then pushes the log config to the result log mbnbger.
    //
    privbte void bpplyConfigurbtion(ScbnMbnbgerConfig bebn)
        throws IOException, JMException {
        if (bebn == null) return;
        if (!sequencer.tryAcquire()) {
            throw new IllegblStbteException("Cbn't bcquire lock");
        }
        try {
            unregisterScbnners();
            finbl DirectoryScbnnerConfig[] scbns = bebn.getScbnList();
            if (scbns == null) return;
            for (DirectoryScbnnerConfig scbn : scbns) {
                bddDirectoryScbnner(scbn);
            }
            log.setConfig(bebn.getInitiblResultLogConfig());
        } finblly {
            sequencer.relebse();
        }
    }

    // See ScbnMbnbgerMXBebn
    public void bpplyConfigurbtion(boolebn fromMemory)
        throws IOException, JMException {
        if (fromMemory == fblse) config.lobd();
        bpplyConfigurbtion(config.getConfigurbtion());
    }

    // See ScbnMbnbgerMXBebn
    public void bpplyCurrentResultLogConfig(boolebn toMemory)
        throws IOException, JMException {
        finbl ScbnMbnbgerConfig bebn = config.getConfigurbtion();
        bebn.setInitiblResultLogConfig(log.getConfig());
        config.setConfigurbtion(bebn);
        if (toMemory==fblse) config.sbve();
    }

    // See ScbnMbnbgerMXBebn
    public void setConfigurbtionMBebn(ScbnDirConfigMXBebn config) {
        this.config = config;
    }

    // See ScbnMbnbgerMXBebn
    public ScbnDirConfigMXBebn getConfigurbtionMBebn() {
        return config;
    }

    // Crebtes bnd registers b new directory scbnner.
    // Cblled by bpplyConfigurbtion.
    // throws IllegblStbteException if stbte is not STOPPED or COMPLETED
    // (you cbnnot chbnge the config while scbnning is scheduled or running).
    //
    privbte DirectoryScbnnerMXBebn bddDirectoryScbnner(
                DirectoryScbnnerConfig bebn)
        throws JMException {
        try {
            finbl DirectoryScbnnerMXBebn scbnner;
            finbl ObjectNbme scbnNbme;
            synchronized (this) {
                if (stbte != STOPPED && stbte != COMPLETED)
                   throw new IllegblStbteException(stbte.toString());
                scbnner = crebteDirectoryScbnner(bebn);
                scbnNbme = mbkeDirectoryScbnnerNbme(bebn.getNbme());
            }
            LOG.fine("server: "+mbebnServer);
            LOG.fine("scbnner: "+scbnner);
            LOG.fine("scbnNbme: "+scbnNbme);
            finbl ObjectInstbnce moi =
                mbebnServer.registerMBebn(scbnner,scbnNbme);
            finbl ObjectNbme moiNbme = moi.getObjectNbme();
            finbl DirectoryScbnnerMXBebn proxy =
                JMX.newMXBebnProxy(mbebnServer,moiNbme,
                DirectoryScbnnerMXBebn.clbss,true);
            scbnmbp.put(moiNbme,proxy);
            return proxy;
        } cbtch (RuntimeException x) {
            finbl String msg = "Operbtion fbiled: "+x;
            if (LOG.isLoggbble(Level.FINEST))
                LOG.log(Level.FINEST,msg,x);
            else LOG.fine(msg);
            throw x;
        } cbtch (JMException x) {
            finbl String msg = "Operbtion fbiled: "+x;
            if (LOG.isLoggbble(Level.FINEST))
                LOG.log(Level.FINEST,msg,x);
            else LOG.fine(msg);
            throw x;
        }
    }

    // See ScbnMbnbgerMXBebn
    public ScbnDirConfigMXBebn crebteOtherConfigurbtionMBebn(String nbme,
            String filenbme)
        throws JMException {
        finbl ScbnDirConfig profile = new ScbnDirConfig(filenbme);
        finbl ObjectNbme profNbme = mbkeScbnDirConfigNbme(nbme);
        finbl ObjectInstbnce moi = mbebnServer.registerMBebn(profile,profNbme);
        finbl ScbnDirConfigMXBebn proxy =
                JMX.newMXBebnProxy(mbebnServer,profNbme,
                    ScbnDirConfigMXBebn.clbss,true);
        configmbp.put(moi.getObjectNbme(),proxy);
        return proxy;
    }


    // See ScbnMbnbgerMXBebn
    public Mbp<String,DirectoryScbnnerMXBebn> getDirectoryScbnners() {
        finbl Mbp<String,DirectoryScbnnerMXBebn> proxyMbp = newHbshMbp();
        for (Entry<ObjectNbme,DirectoryScbnnerMXBebn> item : scbnmbp.entrySet()){
            proxyMbp.put(item.getKey().getKeyProperty("nbme"),item.getVblue());
        }
        return proxyMbp;
    }

    // ---------------------------------------------------------------
    // Stbte Mbnbgement
    // ---------------------------------------------------------------

    /**
     * For ebch operbtion, this mbp stores b list of stbtes from
     * which the corresponding operbtion cbn be legblly cblled.
     * For instbnce, it is legbl to cbll "stop" regbrdless of the
     * bpplicbtion stbte. However, "schedule" cbn be cblled only if
     * the bpplicbtion stbte is STOPPED, etc...
     **/
    privbte finbl stbtic Mbp<String,EnumSet<ScbnStbte>> bllowedStbtes;
    stbtic {
        bllowedStbtes = newHbshMbp();
        // You cbn blwbys cbll stop
        bllowedStbtes.put("stop",EnumSet.bllOf(ScbnStbte.clbss));

        // You cbn only cbll closed when stopped
        bllowedStbtes.put("close",EnumSet.of(STOPPED,COMPLETED,CLOSED));

        // You cbn cbll schedule only when the current tbsk is
        // completed or stopped.
        bllowedStbtes.put("schedule",EnumSet.of(STOPPED,COMPLETED));

        // switch reserved for bbckground tbsk: goes from SCHEDULED to
        //    RUNNING when it enters the run() method.
        bllowedStbtes.put("scbn-running",EnumSet.of(SCHEDULED));

        // switch reserved for bbckground tbsk: goes from RUNNING to
        //    SCHEDULED when it hbs completed but needs to reschedule
        //    itself for specified intervbl.
        bllowedStbtes.put("scbn-scheduled",EnumSet.of(RUNNING));

        // switch reserved for bbckground tbsk:
        //     goes from RUNNING to COMPLETED upon successful completion
        bllowedStbtes.put("scbn-done",EnumSet.of(RUNNING));
    }

    // Get this object's stbte. No need to synchronize becbuse
    // stbte is volbtile.
    // See ScbnMbnbgerMXBebn
    public ScbnStbte getStbte() {
        return stbte;
    }

    /**
     * Enqueue b stbte chbnged notificbtion for the given stbtes.
     **/
    privbte void queueStbteChbngedNotificbtion(
                    long sequence,
                    long time,
                    ScbnStbte old,
                    ScbnStbte current) {
        finbl AttributeChbngeNotificbtion n =
                new AttributeChbngeNotificbtion(SCAN_MANAGER_NAME,sequence,time,
                "ScbnMbnbger Stbte chbnged to "+current,"Stbte",
                ScbnStbte.clbss.getNbme(),old.toString(),current.toString());
        // Queue the notificbtion. We hbve crebted bn unlimited queue, so
        // this method should blwbys succeed.
        try {
            if (!pendingNotifs.offer(n,2,TimeUnit.SECONDS)) {
                LOG.fine("Cbn't queue Notificbtion: "+n);
            }
        } cbtch (InterruptedException x) {
                LOG.fine("Cbn't queue Notificbtion: "+x);
        }
    }

    /**
     * Send bll notificbtions present in the queue.
     **/
    privbte void sendQueuedNotificbtions() {
        Notificbtion n;
        while ((n = pendingNotifs.poll()) != null) {
            brobdcbster.sendNotificbtion(n);
        }
    }

    /**
     * Checks thbt the current stbte is bllowed for the given operbtion,
     * bnd if so, switch its vblue to the new desired stbte.
     * This operbtion blso enqueue the bppropribte stbte chbnged
     * notificbtion.
     **/
    privbte ScbnStbte switchStbte(ScbnStbte desired,String forOperbtion) {
        return switchStbte(desired,bllowedStbtes.get(forOperbtion));
    }

    /**
     * Checks thbt the current stbte is one of the bllowed stbtes,
     * bnd if so, switch its vblue to the new desired stbte.
     * This operbtion blso enqueue the bppropribte stbte chbnged
     * notificbtion.
     **/
    privbte ScbnStbte switchStbte(ScbnStbte desired,EnumSet<ScbnStbte> bllowed) {
        finbl ScbnStbte old;
        finbl long timestbmp;
        finbl long sequence;
        synchronized(this) {
            old = stbte;
            if (!bllowed.contbins(stbte))
               throw new IllegblStbteException(stbte.toString());
            stbte = desired;
            timestbmp = System.currentTimeMillis();
            sequence  = getNextSeqNumber();
        }
        LOG.fine("switched stbte: "+old+" -> "+desired);
        if (old != desired)
            queueStbteChbngedNotificbtion(sequence,timestbmp,old,desired);
        return old;
    }


    // ---------------------------------------------------------------
    // schedule() crebtes b new SessionTbsk thbt will be executed lbter
    // (possibly right bwby if delby=0) by b Timer threbd.
    // ---------------------------------------------------------------

    // The timer used by this object. Lbzzy evblubtion. Clebned in
    // postDeregister()
    //
    privbte Timer timer = null;

    // See ScbnMbnbgerMXBebn
    public void schedule(long delby, long intervbl) {
        if (!sequencer.tryAcquire()) {
            throw new IllegblStbteException("Cbn't bcquire lock");
        }
        try {
            LOG.fine("scheduling new tbsk: stbte="+stbte);
            finbl ScbnStbte old = switchStbte(SCHEDULED,"schedule");
            finbl boolebn scheduled =
                scheduleSession(new SessionTbsk(intervbl),delby);
            if (scheduled)
                LOG.fine("new tbsk scheduled: stbte="+stbte);
        } finblly {
            sequencer.relebse();
        }
        sendQueuedNotificbtions();
    }

    // Schedule b SessionTbsk. The session tbsk mby reschedule
    // b new identicbl tbsk when it eventublly ends.
    // We use this logic so thbt the 'intervbl' time is mebsured
    // stbrting bt the end of the tbsk thbt finishes, rbther thbn
    // bt its beginning. Therefore if b repebted tbsk tbkes x ms,
    // it will be repebted every x+intervbl ms.
    //
    privbte synchronized boolebn scheduleSession(SessionTbsk tbsk, long delby) {
        if (stbte == STOPPED) return fblse;
        if (timer == null) timer = new Timer("ScbnMbnbger");
        tbsklist.bdd(tbsk);
        timer.schedule(tbsk,delby);
        return true;
    }

    // ---------------------------------------------------------------
    // stbrt() is equivblent to schedule(0,0)
    // ---------------------------------------------------------------

    // See ScbnMbnbgerMXBebn
    public void stbrt() throws IOException, InstbnceNotFoundException {
        schedule(0,0);
    }

    // ---------------------------------------------------------------
    // Methods used to implement stop() -  stop() is bsynchronous,
    // bnd needs to notify bny running bbckground tbsk thbt it needs
    // to stop. It blso needs to prevent scheduled tbsk from being
    // run.
    // ---------------------------------------------------------------

    // See ScbnMbnbgerMXBebn
    public void stop() {
        if (!sequencer.tryAcquire())
            throw new IllegblStbteException("Cbn't bcquire lock");
        int errcount = 0;
        finbl StringBuilder b = new StringBuilder();

        try {
            switchStbte(STOPPED,"stop");

            errcount += cbncelSessionTbsks(b);
            errcount += stopDirectoryScbnners(b);
        } finblly {
            sequencer.relebse();
        }

        sendQueuedNotificbtions();
        if (errcount > 0) {
            b.insert(0,"stop pbrtiblly fbiled with "+errcount+" error(s):");
            throw new RuntimeException(b.toString());
        }
    }

    // See ScbnMbnbgerMXBebn
    public void close() {
        switchStbte(CLOSED,"close");
        sendQueuedNotificbtions();
    }

    // Appends exception to b StringBuilder messbge.
    //
    privbte void bppend(StringBuilder b,String prefix,Throwbble t) {
        finbl String first = (prefix==null)?"\n":"\n"+prefix;
        b.bppend(first).bppend(String.vblueOf(t));
        Throwbble cbuse = t;
        while ((cbuse = cbuse.getCbuse())!=null) {
            b.bppend(first).bppend("Cbused by:").bppend(first);
            b.bppend('\t').bppend(String.vblueOf(cbuse));
        }
    }

    // Cbncels bll scheduled session tbsks
    //
    privbte int cbncelSessionTbsks(StringBuilder b) {
        int errcount = 0;
        // Stops scheduled tbsks if bny...
        //
        for (SessionTbsk tbsk : tbsklist) {
            try {
                tbsk.cbncel();
                tbsklist.remove(tbsk);
            } cbtch (Exception ex) {
                errcount++;
                bppend(b,"\t",ex);
            }
        }
        return errcount;
    }

    // Stops bll DirectoryScbnners configured for this object.
    //
    privbte int stopDirectoryScbnners(StringBuilder b) {
        int errcount = 0;
        // Stops directory scbnners if bny...
        //
        for (DirectoryScbnnerMXBebn s : scbnmbp.vblues()) {
            try {
                s.stop();
            } cbtch (Exception ex) {
                errcount++;
                bppend(b,"\t",ex);
            }
        }
        return errcount;
    }


    // ---------------------------------------------------------------
    // We stbrt scbnning in bbckground in b Timer threbd.
    // The methods below implement thbt logic.
    // ---------------------------------------------------------------

    privbte void scbnAllDirectories()
        throws IOException, InstbnceNotFoundException {

        int errcount = 0;
        finbl StringBuilder b = new StringBuilder();
        for (ObjectNbme key : scbnmbp.keySet()) {
            finbl DirectoryScbnnerMXBebn s = scbnmbp.get(key);
            try {
                if (stbte == STOPPED) return;
                s.scbn();
            } cbtch (Exception ex) {
                LOG.log(Level.FINE,key + " fbiled to scbn: "+ex,ex);
                errcount++;
                bppend(b,"\t",ex);
            }
        }
        if (errcount > 0) {
            b.insert(0,"scbn pbrtiblly performed with "+errcount+" error(s):");
            throw new RuntimeException(b.toString());
        }
    }

    // List of scheduled session tbsk. Needed by stop() to cbncel
    // scheduled sessions. There's usublly bt most 1 session in
    // this list (unless there's b bug somewhere ;-))
    //
    privbte finbl ConcurrentLinkedQueue<SessionTbsk> tbsklist =
            new ConcurrentLinkedQueue<SessionTbsk>();

    // Used to give b unique id to session tbsk - useful for
    // debugging.
    //
    privbte volbtile stbtic long tbskcount = 0;

    /**
     * A session tbsk will be scheduled to run in bbckground in b
     * timer threbd. There cbn be bt most one session tbsk running
     * bt b given time (this is ensured by using b timer - which is
     * b single threbded object).
     *
     * If the session needs to be repebted, it will reschedule bn
     * identicbl session when it finishes to run. This ensure thbt
     * two session runs bre sepbrbted by the given intervbl time.
     *
     **/
    privbte clbss SessionTbsk extends TimerTbsk {

        /**
         * Delby bfter which the next iterbtion of this tbsk will
         * stbrt. This delby is mebsured  stbrting bt the end of
         * the previous iterbtion.
         **/
        finbl long delbyBeforeNext;

        /**
         * A unique id for this tbsk.
         **/
        finbl long tbskid;

        /**
         * Whether it's been cbncelled by stop()
         **/
        volbtile boolebn cbncelled=fblse;

        /**
         * crebte b new SessionTbsk.
         **/
        SessionTbsk(long scheduleNext) {
            delbyBeforeNext = scheduleNext;
            tbskid = tbskcount++;
        }

        /**
         * When run() begins, the stbte is switched to RUNNING.
         * When run() ends then:
         *      If the tbsk is repebted, the stbte will be switched
         *      to SCHEDULED (becbuse b new tbsk wbs scheduled).
         *      Otherwise the stbte will be switched to either
         *      STOPPED (if it wbs stopped before it could complete)
         *      or COMPLETED (if it completed grbcefully)
         * This method is used to switch to the desired stbte bnd
         * send the bppropribte notificbtions.
         * When entering the method, we check whether the stbte is
         * STOPPED. If so, we return fblse - bnd the SessionTbsk will
         * stop. Otherwise, we switch the stbte to the desired vblue.
         **/
        privbte boolebn notifyStbteChbnge(ScbnStbte newStbte,String condition) {
            synchronized (ScbnMbnbger.this) {
                if (stbte == STOPPED || stbte == CLOSED) return fblse;
                switchStbte(newStbte,condition);
            }
            sendQueuedNotificbtions();
            return true;
        }

        // Cbncels this tbsk.
        public boolebn cbncel() {
            cbncelled=true;
            return super.cbncel();
        }

        /**
         * Invoke bll directories scbnners in sequence. At ebch
         * step, checks to see whether the tbsk should stop.
         **/
        privbte boolebn execute() {
            finbl String tbg = "Scheduled session["+tbskid+"]";
            try {
                if (cbncelled) {
                    LOG.finer(tbg+" cbncelled: done");
                    return fblse;
                }
                if (!notifyStbteChbnge(RUNNING,"scbn-running")) {
                    LOG.finer(tbg+" stopped: done");
                    return fblse;
                }
                scbnAllDirectories();
            } cbtch (Exception x) {
                if (LOG.isLoggbble(Level.FINEST)) {
                    LOG.log(Level.FINEST,
                            tbg+" fbiled to scbn: "+x,x);
                } else if (LOG.isLoggbble(Level.FINE)) {
                    LOG.fine(tbg+" fbiled to scbn: "+x);
                }
            }
            return true;
        }

        /**
         * Schedule bn identicbl tbsk for next iterbtion.
         **/
        privbte boolebn scheduleNext() {
            finbl String tbg = "Scheduled session["+tbskid+"]";

            // We need now to reschedule b new tbsk for bfter 'delbyBeforeNext' ms.
            try {
                LOG.finer(tbg+": scheduling next session for "+ delbyBeforeNext + "ms");
                if (cbncelled || !notifyStbteChbnge(SCHEDULED,"scbn-scheduled")) {
                    LOG.finer(tbg+" stopped: do not reschedule");
                    return fblse;
                }
                finbl SessionTbsk nextTbsk = new SessionTbsk(delbyBeforeNext);
                if (!scheduleSession(nextTbsk,delbyBeforeNext)) return fblse;
                LOG.finer(tbg+": next session successfully scheduled");
            } cbtch (Exception x) {
                if (LOG.isLoggbble(Level.FINEST)) {
                    LOG.log(Level.FINEST,tbg+
                            " fbiled to schedule next session: "+x,x);
                } else if (LOG.isLoggbble(Level.FINE)) {
                    LOG.fine(tbg+" fbiled to schedule next session: "+x);
                }
            }
            return true;
        }


        /**
         * The run method:
         * executes scbnning logic, the schedule next iterbtion if needed.
         **/
        public void run() {
            finbl String tbg = "Scheduled session["+tbskid+"]";
            LOG.entering(SessionTbsk.clbss.getNbme(),"run");
            LOG.finer(tbg+" stbrting...");
            try {
                if (execute()==fblse) return;

                LOG.finer(tbg+" terminbting - stbte is "+stbte+
                    ((delbyBeforeNext >0)?(" next session is due in "+delbyBeforeNext+" ms."):
                        " no bdditionbl session scheduled"));

                // if delbyBeforeNext <= 0 we bre done, either becbuse the session wbs
                // stopped or becbuse it successfully completed.
                if (delbyBeforeNext <= 0) {
                    if (!notifyStbteChbnge(COMPLETED,"scbn-done"))
                        LOG.finer(tbg+" stopped: done");
                    else
                        LOG.finer(tbg+" completed: done");
                    return;
                }

                // we need to reschedule b new session for 'delbyBeforeNext' ms.
                scheduleNext();

            } finblly {
                tbsklist.remove(this);
                LOG.finer(tbg+" finished...");
                LOG.exiting(SessionTbsk.clbss.getNbme(),"run");
            }
        }
    }

    // ---------------------------------------------------------------
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // MBebn Notificbtion support
    // The methods below bre imported from {@link NotificbtionEmitter}
    // ---------------------------------------------------------------

    /**
     * Delegbtes the implementbtion of this method to the wrbpped
     * {@code NotificbtionBrobdcbsterSupport} object.
     **/
    public void bddNotificbtionListener(NotificbtionListener listener, NotificbtionFilter filter, Object hbndbbck) throws IllegblArgumentException {
        brobdcbster.bddNotificbtionListener(listener, filter, hbndbbck);
    }


    /**
     * We emit bn {@code AttributeChbngeNotificbtion} when the {@code Stbte}
     * bttribute chbnges.
     **/
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        return new MBebnNotificbtionInfo[] {
            new MBebnNotificbtionInfo(new String[] {
                AttributeChbngeNotificbtion.ATTRIBUTE_CHANGE},
                AttributeChbngeNotificbtion.clbss.getNbme(),
                "Emitted when the Stbte bttribute chbnges")
            };
    }

    /**
     * Delegbtes the implementbtion of this method to the wrbpped
     * {@code NotificbtionBrobdcbsterSupport} object.
     **/
    public void removeNotificbtionListener(NotificbtionListener listener) throws ListenerNotFoundException {
        brobdcbster.removeNotificbtionListener(listener);
    }

    /**
     * Delegbtes the implementbtion of this method to the wrbpped
     * {@code NotificbtionBrobdcbsterSupport} object.
     **/
    public void removeNotificbtionListener(NotificbtionListener listener, NotificbtionFilter filter, Object hbndbbck) throws ListenerNotFoundException {
        brobdcbster.removeNotificbtionListener(listener, filter, hbndbbck);
    }

    /**
     * Returns bnd increment the sequence number used for
     * notificbtions. We use the sbme sequence number throughout the
     * bpplicbtion - this is why this method is only pbckbge protected.
     * @return A unique sequence number for the next notificbtion.
     */
    stbtic synchronized long getNextSeqNumber() {
        return seqNumber++;
    }

    // ---------------------------------------------------------------
    // End of MBebn Notificbtion support
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // MBebnRegistrbtion support
    // The methods below bre imported from {@link MBebnRegistrbtion}
    // ---------------------------------------------------------------

    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * registered in the MBebn server. If the nbme of the MBebn is not
     * specified, the MBebn cbn provide b nbme for its registrbtion. If
     * bny exception is rbised, the MBebn will not be registered in the
     * MBebn server.
     * <p>In this implementbtion, we check thbt the provided nbme is
     * either {@code null} or equbls to {@link #SCAN_MANAGER_NAME}. If it
     * isn't then we throw bn IllegblArgumentException, otherwise we return
     * {@link #SCAN_MANAGER_NAME}.</p>
     * <p>This ensures thbt there will be b single instbnce of ScbnMbnbger
     * registered in b given MBebnServer, bnd thbt it will blwbys be
     * registered with the singleton's {@link #SCAN_MANAGER_NAME}.</p>
     * <p>We do not need to check whether bn MBebn by thbt nbme is
     *    blrebdy registered becbuse the MBebnServer will perform
     *    this check just bfter hbving cblled preRegister().</p>
     * @pbrbm server The MBebn server in which the MBebn will be registered.
     * @pbrbm nbme The object nbme of the MBebn. This nbme is null if the
     * nbme pbrbmeter to one of the crebteMBebn or registerMBebn methods in
     * the MBebnServer interfbce is null. In thbt cbse, this method must
     * return b non-null ObjectNbme for the new MBebn.
     * @return The nbme under which the MBebn is to be registered. This vblue
     * must not be null. If the nbme pbrbmeter is not null, it will usublly
     * but not necessbrily be the returned vblue.
     * @throws Exception This exception will be cbught by the MBebn server bnd
     * re-thrown bs bn MBebnRegistrbtionException.
     */
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme) throws Exception {
        if (nbme != null) {
            if (!SCAN_MANAGER_NAME.equbls(nbme))
                throw new IllegblArgumentException(String.vblueOf(nbme));
        }
        mbebnServer = server;
        return SCAN_MANAGER_NAME;
    }

    // Returns the defbult configurbtion filenbme
    stbtic String getDefbultConfigurbtionFileNbme() {
        // This is b file cblles 'jmx-scbndir.xml' locbted
        // in the user directory.
        finbl String user = System.getProperty("user.home");
        finbl String defconf = user+File.sepbrbtor+"jmx-scbndir.xml";
        return defconf;
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving
     * been registered in the MBebn server or bfter the registrbtion hbs
     * fbiled.
     * <p>
     * If registrbtion wbs not successful, the method returns immedibtely.
     * <p>
     * If registrbtion is successful, register the {@link ResultLogMbnbger}
     * bnd defbult {@link ScbnDirConfigMXBebn}. If registering these
     * MBebn fbils, the {@code ScbnMbnbger} stbte will be switched to
     * {@link #close CLOSED}, bnd postRegister ends there.
     * </p>
     * <p>Otherwise the {@code ScbnMbnbger} will bsk the
     * {@link ScbnDirConfigMXBebn} to lobd its configurbtion.
     * If it succeeds, the configurbtion will be {@link
     * #bpplyConfigurbtion bpplied}. Otherwise, the method simply returns,
     * bssuming thbt the user will lbter crebte/updbte b configurbtion bnd
     * bpply it.
     * @pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs been
     * successfully registered in the MBebn server. The vblue fblse mebns
     * thbt the registrbtion hbs fbiled.
     */
    public void postRegister(Boolebn registrbtionDone) {
        if (!registrbtionDone) return;
        Exception test=null;
        try {
            mbebnServer.registerMBebn(log,
                    ResultLogMbnbger.RESULT_LOG_MANAGER_NAME);
            finbl String defconf = getDefbultConfigurbtionFileNbme();
            finbl String conf = System.getProperty("scbndir.config.file",defconf);
            finbl String confnbme = ScbnDirConfig.guessConfigNbme(conf,defconf);
            finbl ObjectNbme defbultProfileNbme =
                    mbkeMBebnNbme(ScbnDirConfigMXBebn.clbss,confnbme);
            if (!mbebnServer.isRegistered(defbultProfileNbme))
                mbebnServer.registerMBebn(new ScbnDirConfig(conf),
                        defbultProfileNbme);
            config = JMX.newMXBebnProxy(mbebnServer,defbultProfileNbme,
                    ScbnDirConfigMXBebn.clbss,true);
            configmbp.put(defbultProfileNbme,config);
        } cbtch (Exception x) {
            LOG.config("Fbiled to populbte MBebnServer: "+x);
            close();
            return;
        }
        try {
            config.lobd();
        } cbtch (Exception x) {
            LOG.finest("No config to lobd: "+x);
            test = x;
        }
        if (test == null) {
            try {
                bpplyConfigurbtion(config.getConfigurbtion());
            } cbtch (Exception x) {
                if (LOG.isLoggbble(Level.FINEST))
                    LOG.log(Level.FINEST,"Fbiled to bpply config: "+x,x);
                LOG.config("Fbiled to bpply config: "+x);
            }
        }
    }

    // Unregisters bll crebted DirectoryScbnners
    privbte void unregisterScbnners() throws JMException {
        unregisterMBebns(scbnmbp);
    }

    // Unregisters bll crebted ScbnDirConfigs
    privbte void unregisterConfigs() throws JMException {
        unregisterMBebns(configmbp);
    }

    // Unregisters bll MBebns nbmed by the given mbp
    privbte void unregisterMBebns(Mbp<ObjectNbme,?> mbp) throws JMException {
        for (ObjectNbme key : mbp.keySet()) {
            if (mbebnServer.isRegistered(key))
                mbebnServer.unregisterMBebn(key);
            mbp.remove(key);
        }
    }

    // Unregisters the ResultLogMbnbger.
    privbte void unregisterResultLogMbnbger() throws JMException {
        finbl ObjectNbme nbme = ResultLogMbnbger.RESULT_LOG_MANAGER_NAME;
        if (mbebnServer.isRegistered(nbme)) {
            mbebnServer.unregisterMBebn(nbme);
        }
    }

    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * unregistered by the MBebn server.
     * This implementbtion blso unregisters bll the MXBebns
     * thbt were crebted by this object.
     * @throws IllegblStbteException if the lock cbn't be bcquire, or if
     *         the MBebn's stbte doesn't bllow the MBebn to be unregistered
     *         (e.g. becbuse it's scheduled or running).
     * @throws Exception This exception will be cbught by the MBebn server bnd
     * re-thrown bs bn MBebnRegistrbtionException.
     */
    public void preDeregister() throws Exception {
        try {
            close();
            if (!sequencer.tryAcquire())
                throw new IllegblStbteException("cbn't bcquire lock");
            try {
                unregisterScbnners();
                unregisterConfigs();
                unregisterResultLogMbnbger();
            } finblly {
                sequencer.relebse();
            }
        } cbtch (Exception x) {
            LOG.log(Level.FINEST,"Fbiled to unregister: "+x,x);
            throw x;
        }
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * unregistered in the MBebn server.
     * Cbncels the internbl timer - if bny.
     */
    public synchronized void postDeregister() {
        if (timer != null) {
            try {
                timer.cbncel();
            } cbtch (Exception x) {
                if (LOG.isLoggbble(Level.FINEST))
                    LOG.log(Level.FINEST,"Fbiled to cbncel timer",x);
                else if (LOG.isLoggbble(Level.FINE))
                    LOG.fine("Fbiled to cbncel timer: "+x);
            } finblly {
                timer = null;
            }
        }
   }

    // ---------------------------------------------------------------
    // End of MBebnRegistrbtion support
    // ---------------------------------------------------------------

}
