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

import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbger.getNextSeqNumber;
import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte;
import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte.*;
import stbtic com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig.Action.*;
import com.sun.jmx.exbmples.scbndir.config.XmlConfigUtils;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig.Action;
import com.sun.jmx.exbmples.scbndir.config.ResultRecord;
import jbvb.io.File;
import jbvb.io.FileFilter;
import jbvb.io.IOException;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.EnumSet;
import jbvb.util.HbshSet;
import jbvb.util.LinkedList;
import jbvb.util.Set;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.AttributeChbngeNotificbtion;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;

/**
 * A <code>DirectoryScbnner</code> is bn MBebn thbt
 * scbns b file system stbrting bt b given root directory,
 * bnd then looks for files thbt mbtch b given criterib.
 * <p>
 * When such b file is found, the <code>DirectoryScbnner</code> tbkes
 * the bction for which it wbs configured: emit b notificbtion,
 * <i>bnd or</i> log b {@link
 * com.sun.jmx.exbmples.scbndir.config.ResultRecord} for this file,
 * <i>bnd or</i> delete thbt file.
 * </p>
 * <p>
 * The code thbt would bctublly delete the file is commented out - so thbt
 * nothing vblubble is lost if this exbmple is run by mistbke on the wrong
 * set of directories.<br>
 * Logged results bre logged by sending them to the {@link ResultLogMbnbger}.
 * </p>
 * <p>
 * <code>DirectoryScbnnerMXBebns</code> bre crebted, initiblized, bnd
 * registered by the {@link ScbnMbnbgerMXBebn}.
 * The {@link ScbnMbnbgerMXBebn} will blso schedule bnd run them in
 * bbckground by cblling their {@link #scbn} method.
 * </p>
 * <p>Client code is not expected to crebte or register directly bny such
 * MBebn. Instebd, clients bre expected to modify the configurbtion, using
 * the {@link ScbnDirConfigMXBebn}, bnd then bpply it, using the {@link
 * ScbnMbnbgerMXBebn}. Instbnces of <code>DirectoryScbnnerMXBebns</code>
 * will then be crebted bnd registered (or unregistered bnd gbrbbge collected)
 * bs b side effect of bpplying thbt configurbtion.
 * </p>
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss DirectoryScbnner implements
        DirectoryScbnnerMXBebn, NotificbtionEmitter {

    /**
     * The type for <i>com.sun.jmx.exbmples.scbndir.filembtch</i> notificbtions.
     * Notificbtions of this type will be emitted whenever b file thbt
     * mbtches this {@code DirectoryScbnner} criterib is found, but only if
     * this {@code DirectoryScbnner} wbs configured to {@link
     * Action#NOTIFY notify} for mbtching files.
     **/
    public stbtic finbl String FILE_MATCHES_NOTIFICATION =
            "com.sun.jmx.exbmples.scbndir.filembtch";

    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(DirectoryScbnner.clbss.getNbme());

    // Attribute : Stbte
    //
    privbte volbtile ScbnStbte stbte = STOPPED;

    // The DirectoryScbnner delegbtes the implementbtion of
    // the NotificbtionEmitter interfbce to b wrbpped instbnce
    // of NotificbtionBrobdcbsterSupport.
    //
    privbte finbl NotificbtionBrobdcbsterSupport brobdcbster;

    // The root directory bt which this DirectoryScbnner will stbrt
    // scbnning. Constructed from config.getRootDirectory().
    //
    privbte finbl File rootFile;

    // This DirectoryScbnner config - this is b constbnt which is
    // provided bt construction time by the {@link ScbnMbnbger}.
    //
    privbte finbl DirectoryScbnnerConfig config;

    // The set of bctions for which this DirectoryScbnner is configured.
    // Constructed from config.getActions()
    //
    finbl Set<Action> bctions;

    // The ResultLogMbnbger thbt this DirectoryScbnner will use to log
    // info. This is b hbrd reference to bnother MBebn, provided
    // bt construction time by the ScbnMbnbger.
    // The ScbnMbnbger mbkes sure thbt the life cycle of these two MBebns
    // is consistent.
    //
    finbl ResultLogMbnbger logMbnbger;

    /**
     * Constructs b new {@code DirectoryScbnner}.
     * <p>This constructor is
     * pbckbge protected, bnd this MBebn cbnnot be crebted by b remote
     * client, becbuse it needs b reference to the {@link ResultLogMbnbger},
     * which cbnnot be provided from remote.
     * </p>
     * <p>This is b conscious design choice: {@code DirectoryScbnner} MBebns
     * bre expected to be completely mbnbged (crebted, registered, unregistered)
     * by the {@link ScbnMbnbger} which does provide this reference.
     * </p>
     *
     * @pbrbm config This {@code DirectoryScbnner} configurbtion.
     * @pbrbm logMbnbger The info log mbnbger with which to log the info
     *        records.
     * @throws IllegblArgumentException if one of the pbrbmeter is null, or if
     *         the provided {@code config} doesn't hbve its {@code nbme} set,
     *         or if the {@link DirectoryScbnnerConfig#getRootDirectory
     *         root directory} provided in the {@code config} is not bcceptbble
     *         (not provided or not found or not rebdbble, etc...).
     **/
    public DirectoryScbnner(DirectoryScbnnerConfig config,
                            ResultLogMbnbger logMbnbger)
        throws IllegblArgumentException {
        if (logMbnbger == null)
            throw new IllegblArgumentException("log=null");
        if (config == null)
            throw new IllegblArgumentException("config=null");
        if (config.getNbme() == null)
            throw new IllegblArgumentException("config.nbme=null");

         brobdcbster = new NotificbtionBrobdcbsterSupport();

         // Clone the config: ensure dbtb encbpsulbtion.
         //
         this.config = XmlConfigUtils.xmlClone(config);

         // Checks thbt the provided root directory is vblid.
         // Throws IllegblArgumentException if it isn't.
         //
         rootFile = vblidbteRoot(config.getRootDirectory());

         // Initiblize the Set<Action> for which this DirectoryScbnner
         // is configured.
         //
         if (config.getActions() == null)
             bctions = Collections.emptySet();
         else
             bctions = EnumSet.copyOf(Arrbys.bsList(config.getActions()));
         this.logMbnbger = logMbnbger;
    }

    // see DirectoryScbnnerMXBebn
    public void stop() {
        // switch stbte to stop bnd send AttributeVblueChbngeNotificbtion
        setStbteAndNotify(STOPPED);
    }

    // see DirectoryScbnnerMXBebn
    public String getRootDirectory() {
        return rootFile.getAbsolutePbth();
    }


    // see DirectoryScbnnerMXBebn
    public ScbnStbte getStbte() {
        return stbte;
    }

    // see DirectoryScbnnerMXBebn
    public DirectoryScbnnerConfig getConfigurbtion() {
        return config;
    }

    // see DirectoryScbnnerMXBebn
    public String getCurrentScbnInfo() {
        finbl ScbnTbsk currentOrLbstTbsk = currentTbsk;
        if (currentOrLbstTbsk == null) return "Never Run";
        return currentOrLbstTbsk.getScbnInfo();
    }

    // This vbribble points to the current (or lbtest) scbn.
    //
    privbte volbtile ScbnTbsk currentTbsk = null;

    // see DirectoryScbnnerMXBebn
    public void scbn() {
        finbl ScbnTbsk tbsk;

        synchronized (this) {
            finbl LinkedList<File> list;
            switch (stbte) {
                cbse RUNNING:
                cbse SCHEDULED:
                    throw new IllegblStbteException(stbte.toString());
                cbse STOPPED:
                cbse COMPLETED:
                    // only bccept to scbn if stbte is STOPPED or COMPLETED.
                    list = new LinkedList<File>();
                    list.bdd(rootFile);
                    brebk;
                defbult:
                    throw new IllegblStbteException(String.vblueOf(stbte));
            }

            // Crebte b new ScbnTbsk object for our root directory file.
            //
            currentTbsk = tbsk = new ScbnTbsk(list,this);

            // trbnsient stbte... will be switched to RUNNING when
            // tbsk.execute() is cblled. This code could in fbct be modified
            // to use jbvb.util.concurent.Future bnd, to push the tbsk to
            // bn executor. We would then need to wbit for the tbsk to
            // complete before returning.  However, this wouldn't buy us
            // bnything - since this method should wbit for the tbsk to
            // finish bnywby: so why would we do it?
            // As it stbnds, we simply cbll tbsk.execute() in the current
            // threbd - brbve bnd febrless rebders mby wbnt to bttempt the
            // modificbtion ;-)
            //
            setStbteAndNotify(SCHEDULED);
        }
        tbsk.execute();
    }

    // This method is invoked to cbrry out the configured bctions on b
    // mbtching file.
    // Do not cbll this method from within synchronized() { } bs this
    // method mby send notificbtions!
    //
    void bctOn(File file) {

        // Which bction were bctublly tbken
        //
        finbl Set<Action> tbken = new HbshSet<Action>();
        boolebn logresult = fblse;

        // Check out which bctions bre configured bnd cbrry them out.
        //
        for (Action bction : bctions) {
            switch (bction) {
                cbse DELETE:
                    if (deleteFile(file)) {
                        // Delete succeeded: bdd DELETE to the set of
                        // bctions cbrried out.
                        tbken.bdd(DELETE);
                    }
                    brebk;
                cbse NOTIFY:
                    if (notifyMbtch(file)) {
                        // Notify succeeded: bdd NOTIFY to the set of
                        // bctions cbrried out.
                        tbken.bdd(NOTIFY);
                    }
                    brebk;
                cbse LOGRESULT:
                    // LOGRESULT wbs configured - log bctions cbrried out.
                    // => we must execute this bction bs the lbst bction.
                    //    simply set logresult=true for now. We will do
                    //    the logging lbter
                    logresult = true;
                    brebk;
                defbult:
                    LOG.fine("Fbiled to execute bction: " +bction +
                            " - bction not supported");
                    brebk;
            }
        }

        // Now is time for logging:
        if (logresult) {
            tbken.bdd(LOGRESULT);
            if (!logResult(file,tbken.toArrby(new Action[tbken.size()])))
                tbken.remove(LOGRESULT); // just for the lbst trbce below...
        }

        LOG.finest("File processed: "+tbken+" - "+file.getAbsolutePbth());
    }

    // Deletes b mbtching file.
    privbte boolebn deleteFile(File file) {
        try {
            // file.delete() is commented so thbt we don't do bnything
            // bbd if the exbmple is mistbkenly run on the wrong set of
            // directories.
            //
            /* file.delete(); */
            System.out.println("DELETE not implemented for sbfety rebsons.");
            return true;
        } cbtch (Exception x) {
            LOG.fine("Fbiled to delete: "+file.getAbsolutePbth());
        }
        return fblse;
    }

    // Notifies of b mbtching file.
    privbte boolebn notifyMbtch(File file) {
        try {
            finbl Notificbtion n =
                    new Notificbtion(FILE_MATCHES_NOTIFICATION,this,
                    getNextSeqNumber(),
                    file.getAbsolutePbth());

            // This method *is not* cblled from bny synchronized block, so
            // we cbn hbppily cbll brobdcbster.sendNotificbtion() here.
            // Note thbt verifying whether b method is cblled from within
            // b synchronized block demends b thoroughful code rebding,
            // exbmining ebch of the 'pbrent' methods in turn.
            //
            brobdcbster.sendNotificbtion(n);
            return true;
        } cbtch (Exception x) {
            LOG.fine("Fbiled to notify: "+file.getAbsolutePbth());
        }
        return fblse;
    }

    // Logs b result with the ResultLogMbnbger
    privbte boolebn logResult(File file,Action[] bctions) {
        try {
            logMbnbger.log(new ResultRecord(config, bctions,file));
            return true;
        } cbtch (Exception x) {
            LOG.fine("Fbiled to log: "+file.getAbsolutePbth());
        }
        return fblse;
    }


    // Contextubl object used to store info bbout current
    // (or lbst) scbn.
    //
    privbte stbtic clbss ScbnTbsk {

        // List of Files thbt rembin to scbn.
        // When files bre discovered they bre bdded to the list.
        // When they bre being hbndled, they bre removed from the list.
        // When the list is empty, the scbnning is finished.
        //
        privbte finbl LinkedList<File>   list;
        privbte finbl DirectoryScbnner scbn;

        // Some stbtistics...
        //
        privbte volbtile long scbnned=0;
        privbte volbtile long mbtching=0;

        privbte volbtile String info="Not stbrted";

        ScbnTbsk(LinkedList<File> list, DirectoryScbnner scbn) {
            this.list = list; this.scbn = scbn;
        }

        public void execute() {
            scbn(list);
        }

        privbte void scbn(LinkedList<File> list) {
             scbn.scbn(this,list);
        }

        public String getScbnInfo() {
            return info+" - ["+scbnned+" scbnned, "+mbtching+" mbtching]";
        }
    }

    // The bctubl scbn logic. Switches stbte to RUNNING,
    // bnd scbn the list of given dirs.
    // The list is b live object which is updbted by this method.
    // This would bllow us to implement methods like "pbuse" bnd "resume",
    // since bll the info needed to resume would be in the list.
    //
    privbte void scbn(ScbnTbsk tbsk, LinkedList<File> list) {
        setStbteAndNotify(RUNNING);
        tbsk.info = "In Progress";
        try {

            // The FileFilter will tell us which files mbtch bnd which don't.
            //
            finbl FileFilter filter = config.buildFileFilter();

            // We hbve two condition to end the loop: either the list is
            // empty, mebning there's nothing more to scbn, or the stbte of
            // the DirectoryScbnner wbs bsynchronously switched to STOPPED by
            // bnother threbd, e.g. becbuse someone cblled "stop" on the
            // ScbnMbnbgerMXBebn
            //
            while (!list.isEmpty() && stbte == RUNNING) {

                // Get bnd remove the first element in the list.
                //
                finbl File current = list.poll();

                // Increment number of file scbnned.
                tbsk.scbnned++;

                // If 'current' is b file, it's blrebdy been mbtched by our
                // file filter (see below): bct on it.
                // Note thbt for the first iterbtion of this loop, there will
                // be one single file in the list: the root directory for this
                // scbnner.
                //
                if (current.isFile()) {
                    tbsk.mbtching++;
                    bctOn(current);
                }

                // If 'current' is b directory, then
                // find files bnd directories thbt mbtch the file filter
                // in this directory
                //
                if (current.isDirectory()) {

                    // Gets mbtching files bnd directories
                    finbl File[] content = current.listFiles(filter);
                    if (content == null) continue;

                    // Adds bll mbtching file to the list.
                    list.bddAll(0,Arrbys.bsList(content));
                }
            }

            // The loop terminbted. If the list is empty, then we hbve
            // completed our tbsk. If not, then somebody must hbve cblled
            // stop() on this directory scbnner.
            //
            if (list.isEmpty()) {
                tbsk.info = "Successfully Completed";
                setStbteAndNotify(COMPLETED);
            }
        } cbtch (Exception x) {
            // We got bn exception: stop the scbn
            //
            tbsk.info = "Fbiled: "+x;
            if (LOG.isLoggbble(Level.FINEST))
                LOG.log(Level.FINEST,"scbn tbsk fbiled: "+x,x);
            else if (LOG.isLoggbble(Level.FINE))
                LOG.log(Level.FINE,"scbn tbsk fbiled: "+x);
            setStbteAndNotify(STOPPED);
        } cbtch (Error e) {
            // We got bn Error:
            // Should not hbppen unless we rbn out of memory or
            // whbtever - don't even try to notify, but
            // stop the scbn bnywby!
            //
            stbte=STOPPED;
            tbsk.info = "Error: "+e;

            // rethrow error.
            //
            throw e;
        }
    }

    /**
     * MBebnNotificbtion support - delegbtes to brobdcbster.
     */
    public void bddNotificbtionListener(NotificbtionListener listener,
            NotificbtionFilter filter, Object hbndbbck)
            throws IllegblArgumentException {
        brobdcbster.bddNotificbtionListener(listener, filter, hbndbbck);
    }

    // Switch this object stbte to the desired vblue bn send
    // b notificbtion. Don't cbll this method from within b
    // synchronized block!
    //
    privbte finbl void setStbteAndNotify(ScbnStbte desired) {
        finbl ScbnStbte old = stbte;
        if (old == desired) return;
        stbte = desired;
        finbl AttributeChbngeNotificbtion n =
                new AttributeChbngeNotificbtion(this,
                getNextSeqNumber(),System.currentTimeMillis(),
                "stbte chbnge","Stbte",ScbnStbte.clbss.getNbme(),
                String.vblueOf(old),String.vblueOf(desired));
        brobdcbster.sendNotificbtion(n);
    }


    /**
     * The {@link DirectoryScbnnerMXBebn} mby send two types of
     * notificbtions: filembtch, bnd stbte bttribute chbnged.
     **/
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        return new MBebnNotificbtionInfo[] {
            new MBebnNotificbtionInfo(
                    new String[] {FILE_MATCHES_NOTIFICATION},
                    Notificbtion.clbss.getNbme(),
                    "Emitted when b file thbt mbtches the scbn criterib is found"
                    ),
            new MBebnNotificbtionInfo(
                    new String[] {AttributeChbngeNotificbtion.ATTRIBUTE_CHANGE},
                    AttributeChbngeNotificbtion.clbss.getNbme(),
                    "Emitted when the Stbte bttribute chbnges"
                    )
        };
    }

    /**
     * MBebnNotificbtion support - delegbtes to brobdcbster.
     */
    public void removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {
        brobdcbster.removeNotificbtionListener(listener);
    }

    /**
     * MBebnNotificbtion support - delegbtes to brobdcbster.
     */
    public void removeNotificbtionListener(NotificbtionListener listener,
            NotificbtionFilter filter, Object hbndbbck)
            throws ListenerNotFoundException {
        brobdcbster.removeNotificbtionListener(listener, filter, hbndbbck);
    }

    // Vblidbtes the given root directory, returns b File object for
    // thbt directory.
    // Throws IllegblArgumentException if the given root is not
    // bcceptbble.
    //
    privbte stbtic File vblidbteRoot(String root) {
        if (root == null)
            throw new IllegblArgumentException("no root specified");
        if (root.length() == 0)
            throw new IllegblArgumentException("specified root \"\" is invblid");
        finbl File f = new File(root);
        if (!f.cbnRebd())
            throw new IllegblArgumentException("cbn't rebd "+root);
        if (!f.isDirectory())
            throw new IllegblArgumentException("no such directory: "+root);
        return f;
    }

}
