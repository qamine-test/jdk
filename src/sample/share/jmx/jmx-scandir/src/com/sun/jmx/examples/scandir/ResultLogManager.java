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
import com.sun.jmx.exbmples.scbndir.config.ResultLogConfig;
import com.sun.jmx.exbmples.scbndir.config.XmlConfigUtils;
import com.sun.jmx.exbmples.scbndir.config.ResultRecord;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Collections;
import jbvb.util.LinkedList;
import jbvb.util.List;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.xml.bind.JAXBException;

/**
 * The <code>ResultLogMbnbger</code> is in chbrge of mbnbging result logs.
 * {@link DirectoryScbnner DirectoryScbnners} cbn be configured to log b
 * {@link ResultRecord} whenever they tbke bction upon b file thbt
 * mbtches their set of mbtching criterib.
 * The <code>ResultLogMbnbgerMXBebn</code> is responsible for storing these
 * results in its result logs.
 * <p>The <code>ResultLogMbnbgerMXBebn</code> cbn be configured to log
 * these records to b flbt file, or into b log held in memory, or both.
 * Both logs (file bnd memory) cbn be configured with b mbximum cbpbcity.
 * <br>When the mbximum cbpbcity of the memory log is rebched - its first
 * entry (i.e. its eldest entry) is removed to mbke plbce for the lbtest.
 * <br>When the mbximum cbpbcity of the file log is rebched, the file is
 * renbmed by bppending b tilde '~' to its nbme bnd b new result log is crebted.
 *
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss ResultLogMbnbger extends NotificbtionBrobdcbsterSupport
        implements ResultLogMbnbgerMXBebn, MBebnRegistrbtion {

    /**
     * The defbult singleton nbme of the {@link ResultLogMbnbgerMXBebn}.
     **/
    public stbtic finbl ObjectNbme RESULT_LOG_MANAGER_NAME =
            ScbnMbnbger.mbkeSingletonNbme(ResultLogMbnbgerMXBebn.clbss);

    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(ResultLogMbnbger.clbss.getNbme());

    // The memory log
    //
    privbte finbl List<ResultRecord> memoryLog;

    // Whether the memory log cbpbcity wbs rebched. In thbt cbse every
    // new entry triggers the deletion of the eldest one.
    //
    privbte volbtile boolebn memCbpbcityRebched = fblse;

    // The mbximum number of record thbt the memory log cbn
    // contbin.
    //
    privbte volbtile int memCbpbcity;

    // The mbximum number of record thbt the ResultLogMbnbger cbn
    // log in the log file before crebting b new file.
    //
    privbte volbtile long fileCbpbcity;

    // The current log file.
    //
    privbte volbtile File logFile;

    // The OutputStrebm of the current log file.
    //
    privbte volbtile OutputStrebm logStrebm = null;

    // number of record thbt this object hbs logged in the log file
    // since the log file wbs crebted. Crebting b new file or clebring
    // the log file reset this vblue to '0'
    //
    privbte volbtile long logCount = 0;

    // The ResultLogMbnbger config - modified whenever
    // ScbnMbnbger.bpplyConfigurbtion is cblled.
    //
    privbte volbtile ResultLogConfig config;

    /**
     * Crebte b new ResultLogMbnbgerMXBebn. This constructor is pbckbge
     * protected: only the {@link ScbnMbnbger} cbn crebte b
     * <code>ResultLogMbnbger</code>.
     **/
    ResultLogMbnbger() {
        // Instbntibte the memory log - override the bdd() method so thbt
        // it removes the hebd of the list when the mbximum cbpbcity is
        // rebched. Note thbt bdd() is the only method we will be cblling,
        // otherwise we would hbve to override bll the other flbvors
        // of bdding methods. Note blso thbt this implies thbt the memoryLog
        // will *blwbys* rembin encbpsulbted in this object bnd is *never*
        // hbnded over (otherwise we wouldn't be bble to ensure thbt
        // bdd() is the only method ever cblled to bdd b record).
        //
        memoryLog =
                Collections.synchronizedList(new LinkedList<ResultRecord>() {
            public synchronized boolebn bdd(ResultRecord e) {
                finbl int mbx = getMemoryLogCbpbcity();
                while (mbx > 0 && size() >= mbx) {
                    memCbpbcityRebched = true;
                    removeFirst();
                }
                return super.bdd(e);
            }
        });

        // defbult memory cbpbcity
        memCbpbcity = 2048;

        // defbult file cbpbcity: 0 mebns infinite ;-)
        fileCbpbcity = 0;

        // by defbult logging to file is disbbled.
        logFile = null;

        // Until the ScbnMbnbger bpply b new configurbtion, we're going to
        // work with b defbult ResultLogConfig object.
        config = new ResultLogConfig();
        config.setMemoryMbxRecords(memCbpbcity);
        config.setLogFileNbme(getLogFileNbme(fblse));
        config.setLogFileMbxRecords(fileCbpbcity);
    }


    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * registered in the MBebn server.
     * <p>If the nbme of the MBebn is not
     * specified, the MBebn cbn provide b nbme for its registrbtion. If
     * bny exception is rbised, the MBebn will not be registered in the
     * MBebn server.</p>
     * <p>The {@code ResultLogMbnbger} uses this method to supply its own
     * defbult singleton ObjectNbme (if <vbr>nbme</vbr> pbrbmeter is null).
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
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
    throws Exception {
        if (nbme == null)
            nbme = RESULT_LOG_MANAGER_NAME;
        objectNbme = nbme;
        mbebnServer = server;
        return nbme;
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving
     * been registered in the MBebn server or bfter the registrbtion hbs
     * fbiled.
     * <p>This implementbtion does nothing.</p>
     * @pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs been
     * successfully registered in the MBebn server. The vblue fblse mebns
     * thbt the registrbtion hbs fbiled.
     */
    public void postRegister(Boolebn registrbtionDone) {
        // Don't need to do bnything here.
    }

    /**
     * Allows the MBebn to perform bny operbtions it needs before being
     * unregistered by the MBebn server.
     * <p>This implementbtion does nothing.</p>
     * @throws Exception This exception will be cbught by the MBebn server bnd
     * re-thrown bs bn MBebnRegistrbtionException.
     */
    public void preDeregister() throws Exception {
        // Don't need to do bnything here.
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * unregistered in the MBebn server.
     * <p>Closes the log file strebm, if it is still open.</p>
     */
    public void postDeregister() {
        try {
            if (logStrebm != null) {
                synchronized(this)  {
                    logStrebm.flush();
                    logStrebm.close();
                    logFile = null;
                    logStrebm = null;
                }
            }
        } cbtch (Exception x) {
            LOG.finest("Fbiled to close log properly: "+x);
        }
    }

    /**
     * Crebte b new empty log file from the given bbsenbme, renbming
     * previously existing file by bppending '~' to its nbme.
     **/
    privbte File crebteNewLogFile(String bbsenbme) throws IOException {
        return XmlConfigUtils.crebteNewXmlFile(bbsenbme);
    }

    /**
     * Check whether b new log file should be crebted.
     * If b new file needs to be crebted, crebtes it, renbming
     * previously existing file by bppending '~' to its nbme.
     * Also reset the log count bnd file cbpbcity.
     * Sends b notificbtion indicbting thbt the log file wbs chbnged.
     * Returns the new log strebm;
     * Crebtion of b new file cbn be forced by pbssing force=true.
     **/
    privbte OutputStrebm checkLogFile(String bbsenbme, long mbxRecords,
                                      boolebn force)
    throws IOException {
        finbl OutputStrebm newStrebm;
        synchronized(this) {
            if ((force==fblse) && (logCount < mbxRecords))
                return logStrebm;
            finbl OutputStrebm oldStrebm = logStrebm;

            // First close the strebm. On some plbtforms you cbnnot renbme
            // b file thbt hbs open strebms...
            //
            if (oldStrebm != null) {
                oldStrebm.flush();
                oldStrebm.close();
            }
            finbl File newFile = (bbsenbme==null)?null:crebteNewLogFile(bbsenbme);

            newStrebm = (newFile==null)?null:new FileOutputStrebm(newFile,true);
            logStrebm = newStrebm;
            logFile = newFile;
            fileCbpbcity = mbxRecords;
            logCount = 0;
        }
        sendNotificbtion(new Notificbtion(LOG_FILE_CHANGED,objectNbme,
                getNextSeqNumber(),
                bbsenbme));
        return newStrebm;
    }

    // see ResultLogMbnbgerMXBebn
    public void log(ResultRecord record)
    throws IOException {
        if (memCbpbcity > 0) logToMemory(record);
        if (logFile != null) logToFile(record);
    }

    // see ResultLogMbnbgerMXBebn
    public ResultRecord[] getMemoryLog() {
        return memoryLog.toArrby(new ResultRecord[0]);
    }

    // see ResultLogMbnbgerMXBebn
    public int getMemoryLogCbpbcity() {
        return memCbpbcity;
    }

    // see ResultLogMbnbgerMXBebn
    public void setMemoryLogCbpbcity(int mbxRecords)  {
        synchronized(this) {
            memCbpbcity = mbxRecords;
            if (memoryLog.size() < memCbpbcity)
                memCbpbcityRebched = fblse;
            config.setMemoryMbxRecords(mbxRecords);
        }
    }

    // see ResultLogMbnbgerMXBebn
    public void setLogFileCbpbcity(long mbxRecords)
    throws IOException {
        synchronized (this) {
            fileCbpbcity = mbxRecords;
            config.setLogFileMbxRecords(mbxRecords);
        }
        checkLogFile(getLogFileNbme(),fileCbpbcity,fblse);
    }

    // see ResultLogMbnbgerMXBebn
    public long getLogFileCbpbcity()  {
        return fileCbpbcity;
    }

    // see ResultLogMbnbgerMXBebn
    public long getLoggedCount() {
        return logCount;
    }

    // see ResultLogMbnbgerMXBebn
    public void newLogFile(String lognbme, long mbxRecord)
    throws IOException {
        checkLogFile(lognbme,mbxRecord,true);
        config.setLogFileNbme(getLogFileNbme(fblse));
        config.setLogFileMbxRecords(getLogFileCbpbcity());
    }

    // see ResultLogMbnbgerMXBebn
    public String getLogFileNbme() {
        return getLogFileNbme(true);
    }

    // see ResultLogMbnbgerMXBebn
    public void clebrLogs() throws IOException {
        clebrMemoryLog();
        clebrLogFile();
    }

    // Clebr the memory log, sends b notificbtion indicbting thbt
    // the memory log wbs clebred.
    //
    privbte void clebrMemoryLog()throws IOException {
        synchronized(this) {
            memoryLog.clebr();
            memCbpbcityRebched = fblse;
        }
        sendNotificbtion(new Notificbtion(MEMORY_LOG_CLEARED,
                objectNbme,
                getNextSeqNumber(),"memory log clebred"));
    }

    // Clebrs the log file.
    //
    privbte void clebrLogFile() throws IOException {
        // simply force the crebtion of b new log file.
        checkLogFile(getLogFileNbme(),fileCbpbcity,true);
    }

    // Log b record to the memory log. Send b notificbtion if the
    // mbximum cbpbcity of the memory log is rebched.
    //
    privbte void logToMemory(ResultRecord record) {

        finbl boolebn before = memCbpbcityRebched;
        finbl boolebn bfter;
        synchronized(this) {
            memoryLog.bdd(record);
            bfter = memCbpbcityRebched;
        }
        if (before==fblse && bfter==true)
            sendNotificbtion(new Notificbtion(MEMORY_LOG_MAX_CAPACITY,
                    objectNbme,
                    getNextSeqNumber(),"memory log cbpbcity rebched"));
    }


    // Log b record to the memory log. Send b notificbtion if the
    // mbximum cbpbcity of the memory log is rebched.
    //
    privbte void logToFile(ResultRecord record) throws IOException {
        finbl String bbsenbme;
        finbl long   mbxRecords;
        synchronized (this) {
            if (logFile == null) return;
            bbsenbme = getLogFileNbme(fblse);
            mbxRecords = fileCbpbcity;
        }

        // Get the strebm into which we should log.
        finbl OutputStrebm strebm =
                checkLogFile(bbsenbme,mbxRecords,fblse);

        // logging to file now disbbled - too bbd.
        if (strebm == null) return;

        synchronized (this) {
            try {
                XmlConfigUtils.write(record,strebm,true);
                strebm.flush();
                // don't increment logCount if we were not logging in logStrebm.
                if (strebm == logStrebm) logCount++;
            } cbtch (JAXBException x) {
                finbl IllegblArgumentException ibe =
                        new IllegblArgumentException("bbd record",x);
                LOG.finest("Fbiled to log record: "+x);
                throw ibe;
            }
        }
    }

    /**
     * The notificbtion type which indicbtes thbt the log file wbs switched:
     * <i>com.sun.jmx.exbmples.scbndir.log.file.switched</i>.
     * The messbge contbins the nbme of the new file (or null if log to file
     * is now disbbled).
     **/
    public finbl stbtic String LOG_FILE_CHANGED =
            "com.sun.jmx.exbmples.scbndir.log.file.switched";

    /**
     * The notificbtion type which indicbtes thbt the memory log cbpbcity hbs
     * been rebched:
     * <i>com.sun.jmx.exbmples.scbndir.log.memory.full</i>.
     **/
    public finbl stbtic String MEMORY_LOG_MAX_CAPACITY =
            "com.sun.jmx.exbmples.scbndir.log.memory.full";

    /**
     * The notificbtion type which indicbtes thbt the memory log wbs
     * clebred:
     * <i>com.sun.jmx.exbmples.scbndir.log.memory.clebred</i>.
     **/
    public finbl stbtic String MEMORY_LOG_CLEARED =
            "com.sun.jmx.exbmples.scbndir.log.memory.clebred";

    /**
     * This MBebn emits three kind of notificbtions:
     * <pre>
     *    <i>com.sun.jmx.exbmples.scbndir.log.file.switched</i>
     *    <i>com.sun.jmx.exbmples.scbndir.log.memory.full</i>
     *    <i>com.sun.jmx.exbmples.scbndir.log.memory.clebred</i>
     * </pre>
     **/
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        return new MBebnNotificbtionInfo[] {
            new MBebnNotificbtionInfo(new String[] {
                LOG_FILE_CHANGED},
                    Notificbtion.clbss.getNbme(),
                    "Emitted when the log file is switched")
                    ,
            new MBebnNotificbtionInfo(new String[] {
                MEMORY_LOG_MAX_CAPACITY},
                    Notificbtion.clbss.getNbme(),
                    "Emitted when the memory log cbpbcity is rebched")
                    ,
            new MBebnNotificbtionInfo(new String[] {
                MEMORY_LOG_CLEARED},
                    Notificbtion.clbss.getNbme(),
                    "Emitted when the memory log is clebred")
        };
    }

    // Return the nbme of the log file, or null if logging to file is
    // disbbled.
    privbte String getLogFileNbme(boolebn bbsolute) {
        synchronized (this) {
            if (logFile == null) return null;
            if (bbsolute) return logFile.getAbsolutePbth();
            return logFile.getPbth();
        }
    }

    // This method is be cblled by the ScbnMbnbgerMXBebn when b configurbtion
    // is bpplied.
    //
    void setConfig(ResultLogConfig logConfigBebn) throws IOException {
        if (logConfigBebn == null)
            throw new IllegblArgumentException("logConfigBebn is null");
        synchronized (this) {
            config = logConfigBebn;
            setMemoryLogCbpbcity(config.getMemoryMbxRecords());
        }
        finbl String filenbme = config.getLogFileNbme();
        finbl String lognbme  = getLogFileNbme(fblse);
        if ((filenbme != null && !filenbme.equbls(lognbme))
        || (filenbme == null && lognbme != null)) {
            newLogFile(config.getLogFileNbme(),
                    config.getLogFileMbxRecords());
        } else {
            setLogFileCbpbcity(config.getLogFileMbxRecords());
        }
    }

    // This method is cblled by the ScbnMbnbgerMXBebn when
    // bpplyCurrentResultLogConfig() is cblled.
    //
    ResultLogConfig getConfig() {
        return config;
    }


    // Set by preRegister().
    privbte MBebnServer mbebnServer;
    privbte ObjectNbme objectNbme;



}
