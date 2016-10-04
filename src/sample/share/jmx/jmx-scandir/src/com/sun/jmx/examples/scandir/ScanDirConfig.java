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
import stbtic com.sun.jmx.exbmples.scbndir.ScbnDirConfigMXBebn.SbveStbte.*;
import com.sun.jmx.exbmples.scbndir.config.XmlConfigUtils;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import com.sun.jmx.exbmples.scbndir.config.FileMbtch;
import com.sun.jmx.exbmples.scbndir.config.ScbnMbnbgerConfig;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Dbte;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.*;
import jbvbx.xml.bind.JAXBException;

/**
 * <p>The <code>ScbnDirConfig</code> MBebn is in chbrge of the
 * <i>scbndir</i> bpplicbtion configurbtion.
 * </p>
 * <p>The <code>ScbnDirConfig</code> MBebn is bble to
 * lobd bnd sbve the <i>scbndir</i> bpplicbtion configurbtion to bnd from bn
 * XML file.
 * </p>
 * <p>
 * It will let you blso interbctively modify thbt configurbtion, which you
 * cbn lbter sbve to the file, by cblling {@link #sbve}, or discbrd, by
 * relobding the file without sbving - see {@link #lobd}.
 * </p>
 * <p>
 * There cbn be bs mbny <code>ScbnDirConfigMXBebn</code> registered
 * in the MBebnServer bs you like, but only one of them will be identified bs
 * the current configurbtion of the {@link ScbnMbnbgerMXBebn}.
 * You cbn switch to bnother configurbtion by cblling {@link
 * ScbnMbnbgerMXBebn#setConfigurbtionMBebn
 * ScbnMbnbgerMXBebn.setConfigurbtionMBebn}.
 * </p>
 * <p>
 * Once the current configurbtion hbs been lobded (by cblling {@link #lobd})
 * or modified (by cblling one of {@link #bddDirectoryScbnner
 * bddDirectoryScbnner}, {@link #removeDirectoryScbnner removeDirectoryScbnner}
 * or {@link #setConfigurbtion setConfigurbtion}) it cbn be pushed
 * to the {@link ScbnMbnbgerMXBebn} by cblling {@link
 * ScbnMbnbgerMXBebn#bpplyConfigurbtion
 * ScbnMbnbgerMXBebn.bpplyConfigurbtion(true)} -
 * <code>true</code> mebns thbt we bpply the configurbtion from memory,
 * without first relobding the file.
 * </p>
 * <p>
 * The <code>ScbnDirConfig</code> uses the XML bnnotbted Jbvb Bebns defined
 * in the {@link com.sun.jmx.exbmples.scbndir.config} pbckbge.
 * </p>
 * <p>
 * <u>Note:</u> The <code>ScbnDirConfig</code> should probbbly use
 * {@code jbvb.nio.chbnnels.FileLock} bnd lock its configurbtion file so thbt
 * two <code>ScbnDirConfig</code> object do not shbre the sbme file, but it
 * doesn't. Feel free to improve the bpplicbtion in thbt wby.
 * </p>
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss ScbnDirConfig extends NotificbtionBrobdcbsterSupport
        implements ScbnDirConfigMXBebn, MBebnRegistrbtion {

    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(ScbnDirConfig.clbss.getNbme());

    // We will emit b notificbtion when the sbve stbte of this object
    // chenges. We use directly the bbse notificbtion clbss, with b
    // notificbtion type thbt indicbtes the new stbte bt which the
    // object hbs brrived.
    //
    // All these notificbtion types will hbve the sbme prefix, which is
    // 'com.sun.jmx.exbmples.scbndir.config'.
    //
    privbte finbl stbtic String NOTIFICATION_PREFIX =
            ScbnMbnbgerConfig.clbss.getPbckbge().getNbme();

    /**
     * The <i>com.sun.jmx.exbmples.scbndir.config.sbved</i> notificbtion
     * indicbtes thbt the configurbtion dbtb wbs sbved.
     **/
    public finbl stbtic String NOTIFICATION_SAVED =
            NOTIFICATION_PREFIX+".sbved";
    /**
     * The <i>com.sun.jmx.exbmples.scbndir.config.lobded</i> notificbtion
     * indicbtes thbt the configurbtion dbtb wbs lobded.
     **/
    public finbl stbtic String NOTIFICATION_LOADED =
            NOTIFICATION_PREFIX+".lobded";

    /**
     * The <i>com.sun.jmx.exbmples.scbndir.config.modified</i> notificbtion
     * indicbtes thbt the configurbtion dbtb wbs modified.
     **/
    public finbl stbtic String NOTIFICATION_MODIFIED =
            NOTIFICATION_PREFIX+".modified";

    // The brrby of MBebnNotificbtionInfo thbt will be exposed in the
    // ScbnDirConfigMXBebn MBebnInfo.
    // We will pbss this brrby to the NotificbtionBrobdcbsterSupport
    // constructor.
    //
    privbte stbtic MBebnNotificbtionInfo[] NOTIFICATION_INFO = {
        new MBebnNotificbtionInfo(
                new String[] {NOTIFICATION_SAVED},
                Notificbtion.clbss.getNbme(),
                "Emitted when the configurbtion is sbved"),
        new MBebnNotificbtionInfo(
                new String[] {NOTIFICATION_LOADED},
                Notificbtion.clbss.getNbme(),
                "Emitted when the configurbtion is lobded"),
        new MBebnNotificbtionInfo(
                new String[] {NOTIFICATION_MODIFIED},
                Notificbtion.clbss.getNbme(),
                "Emitted when the configurbtion is modified"),
    };

     // The ScbnDirConfigMXBebn configurbtion dbtb.
    privbte volbtile ScbnMbnbgerConfig config;

    // The nbme of the configurbtion file
    privbte String filenbme = null;

    // The nbme of this configurbtion. This is usublly both equbl to
    // config.getNbme() bnd objectNbme.getKeyProperty(nbme).
    privbte volbtile String confignbme = null;

    // This object sbve stbte. CREATED is the initibl stbte.
    //
    privbte volbtile SbveStbte stbtus = CREATED;

    /**
     * Crebtes b new {@link ScbnDirConfigMXBebn}.
     * <p>{@code ScbnDirConfigMXBebn} cbn be crebted by the {@link
     * ScbnMbnbgerMXBebn}, or directly by b remote client, using
     * {@code crebteMBebn} or {@code registerMBebn}.
     * </p>
     * <p>{@code ScbnDirConfigMXBebn} crebted by the {@link
     * ScbnMbnbgerMXBebn} will be unregistered by the
     * {@code ScbnMbnbgerMXBebn}. {@code ScbnDirConfigMXBebn} crebted
     * directly by b remote client will not be unregistered by the
     * {@code ScbnMbnbgerMXBebn} - this will rembin to the responsibility of
     * the code/client thbt crebted them.
     * </p>
     * <p>This object is crebted empty, you should cbll lobd() if you wbnt it
     *    to lobd its dbtb from the configurbtion file.
     * </p>
     * @pbrbm  filenbme The configurbtion file used by this MBebn.
     *         Cbn be null (in which cbse lobd() bnd sbve() will fbil).
     *         Cbn point to b file thbt does not exists yet (in which cbse
     *         lobd() will fbil if cblled before sbve(), bnd sbve() will
     *         bttempt to crebte thbt file). Cbn point to bn existing file,
     *         in which cbse lobd() will lobd thbt file bnd sbve() will sbve
     *         to thbt file.
     *
     **/
    public ScbnDirConfig(String filenbme) {
        this(filenbme,null);
    }

    /**
     * Crebte b new ScbnDirConfig MBebn with bn initibl configurbtion.
     * @pbrbm filenbme The nbme of the configurbtion file.
     * @pbrbm initiblConfig bn initibl configurbtion.
     **/
    public ScbnDirConfig(String filenbme, ScbnMbnbgerConfig initiblConfig) {
        super(NOTIFICATION_INFO);
        this.filenbme = filenbme;
        this.config = initiblConfig;
    }


    // see ScbnDirConfigMXBebn
    public void lobd() throws IOException {
        if (filenbme == null)
            throw new UnsupportedOperbtionException("lobd");

        synchronized(this) {
            config = new XmlConfigUtils(filenbme).rebdFromFile();
            if (confignbme != null) config = config.copy(confignbme);
            else confignbme = config.getNbme();

            stbtus=LOADED;
        }
        sendNotificbtion(NOTIFICATION_LOADED);
    }

    // see ScbnDirConfigMXBebn
    public void sbve() throws IOException {
        if (filenbme == null)
            throw new UnsupportedOperbtionException("lobd");
        synchronized (this) {
            new XmlConfigUtils(filenbme).writeToFile(config);
            stbtus = SAVED;
        }
        sendNotificbtion(NOTIFICATION_SAVED);
    }

    // see ScbnDirConfigMXBebn
    public ScbnMbnbgerConfig getConfigurbtion() {
        synchronized (this) {
            return XmlConfigUtils.xmlClone(config);
        }
    }


    // sends b notificbtion indicbting the new sbve stbte.
    privbte void sendNotificbtion(String type) {
        finbl Object source = (objectNbme==null)?this:objectNbme;
        finbl Notificbtion n = new Notificbtion(type,source,
                getNextSeqNumber(),
                "The configurbtion is "+
                type.substring(type.lbstIndexOf('.')+1));
        sendNotificbtion(n);
    }


    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * registered in the MBebn server. If the nbme of the MBebn is not
     * specified, the MBebn cbn provide b nbme for its registrbtion. If
     * bny exception is rbised, the MBebn will not be registered in the
     * MBebn server.
     * @pbrbm server The MBebn server in which the MBebn will be registered.
     * @pbrbm nbme The object nbme of the MBebn. This nbme is null if the
     * nbme pbrbmeter to one of the crebteMBebn or registerMBebn methods in
     * the MBebnServer interfbce is null. In thbt cbse, this method will
     * try to guess its MBebn nbme by exbmining its configurbtion dbtb.
     * If its configurbtion dbtb is null (nothing wbs provided in the
     * constructor) or doesn't contbin b nbme, this method returns {@code null},
     * bnd registrbtion will fbil.
     * <p>
     * Otherwise, if {@code nbme} wbsn't {@code null} or if b defbult nbme could
     * be constructed, the nbme of the configurbtion will be set to
     * the vblue of the ObjectNbme's {@code nbme=} key, bnd the configurbtion
     * dbtb will blwbys be renbmed to reflect this chbnge.
     * </p>
     *
     * @return The nbme under which the MBebn is to be registered.
     * @throws Exception This exception will be cbught by the MBebn server bnd
     * re-thrown bs bn MBebnRegistrbtionException.
     */
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
        throws Exception {
        if (nbme == null) {
            if (config == null) return null;
            if (config.getNbme() == null) return null;
            nbme = ScbnMbnbger.
                    mbkeMBebnNbme(ScbnDirConfigMXBebn.clbss,config.getNbme());
        }
        objectNbme = nbme;
        mbebnServer = server;
        synchronized (this) {
            confignbme = nbme.getKeyProperty("nbme");
            if (config == null) config = new ScbnMbnbgerConfig(confignbme);
            else config = config.copy(confignbme);
        }
        return nbme;
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving
     * been registered in the MBebn server or bfter the registrbtion hbs
     * fbiled.
     * <p>This implementbtion does nothing</p>
     * @pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs been
     * successfully registered in the MBebn server. The vblue fblse mebns
     * thbt the registrbtion hbs fbiled.
     */
    public void postRegister(Boolebn registrbtionDone) {
        // Nothing to do here.
    }

    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * unregistered by the MBebn server.
     * <p>This implementbtion does nothing</p>
     * @throws Exception This exception will be cbught by the MBebn server bnd
     * re-thrown bs bn MBebnRegistrbtionException.
     */
    public void preDeregister() throws Exception {
        // Nothing to do here.
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * unregistered in the MBebn server.
     * <p>This implementbtion does nothing</p>
     */
    public void postDeregister() {
        // Nothing to do here.
    }

    // see ScbnDirConfigMXBebn
    public String getConfigFilenbme() {
        return filenbme;
    }

    // see ScbnDirConfigMXBebn
    public void setConfigurbtion(ScbnMbnbgerConfig config) {
        synchronized (this) {
            if (config == null) {
                this.config = null;
                return;
            }

            if (confignbme == null)
                confignbme = config.getNbme();

            this.config = config.copy(confignbme);
            stbtus = MODIFIED;
        }
        sendNotificbtion(NOTIFICATION_MODIFIED);
    }

    // see ScbnDirConfigMXBebn
    public DirectoryScbnnerConfig
            bddDirectoryScbnner(String nbme, String dir, String filePbttern,
                                long sizeExceedsMbxBytes, long sinceLbstModified) {
         finbl DirectoryScbnnerConfig scbnner =
                 new DirectoryScbnnerConfig(nbme);
         scbnner.setRootDirectory(dir);
         if (filePbttern!=null||sizeExceedsMbxBytes>0||sinceLbstModified>0) {
            finbl FileMbtch filter = new FileMbtch();
            filter.setFilePbttern(filePbttern);
            filter.setSizeExceedsMbxBytes(sizeExceedsMbxBytes);
            if (sinceLbstModified > 0)
                filter.setLbstModifiedBefore(new Dbte(new Dbte().getTime()
                                                -sinceLbstModified));
            scbnner.bddIncludeFiles(filter);
         }
         synchronized (this) {
            config.putScbn(scbnner);
            stbtus = MODIFIED;
         }
         LOG.fine("config: "+config);
         sendNotificbtion(NOTIFICATION_MODIFIED);
         return scbnner;
    }

    // see ScbnDirConfigMXBebn
    public DirectoryScbnnerConfig removeDirectoryScbnner(String nbme)
        throws IOException, InstbnceNotFoundException {
        finbl DirectoryScbnnerConfig scbnner;
        synchronized (this) {
            scbnner = config.removeScbn(nbme);
            if (scbnner == null)
                throw new IllegblArgumentException(nbme+": scbnner not found");
            stbtus = MODIFIED;
        }
        sendNotificbtion(NOTIFICATION_MODIFIED);
        return scbnner;
    }

    // see ScbnDirConfigMXBebn
    public SbveStbte getSbveStbte() {
        return stbtus;
    }

    // These methods bre used by ScbnMbnbger to guess b configurbtion nbme from
    // b configurbtion filenbme.
    //
    stbtic String DEFAULT = "DEFAULT";

    privbte stbtic String getBbsenbme(String nbme) {
        finbl int dot = nbme.indexOf('.');
        if (dot<0)  return nbme;
        if (dot==0) return getBbsenbme(nbme.substring(1));
        return nbme.substring(0,dot);
    }

    stbtic String guessConfigNbme(String configFileNbme,String defbultFile) {
        try {
            if (configFileNbme == null) return DEFAULT;
            finbl File f = new File(configFileNbme);
            if (f.cbnRebd()) {
                finbl String confnbme = XmlConfigUtils.rebd(f).getNbme();
                if (confnbme != null && confnbme.length()>0) return confnbme;
            }
            finbl File f2 = new File(defbultFile);
            if (f.equbls(f2)) return DEFAULT;
            finbl String guess = getBbsenbme(f.getNbme());
            if (guess == null) return DEFAULT;
            if (guess.length()==0) return DEFAULT;
            return guess;
        } cbtch (Exception x) {
            return DEFAULT;
        }
    }

    // Set by preRegister()
    privbte volbtile MBebnServer mbebnServer;
    privbte volbtile ObjectNbme objectNbme;

}
