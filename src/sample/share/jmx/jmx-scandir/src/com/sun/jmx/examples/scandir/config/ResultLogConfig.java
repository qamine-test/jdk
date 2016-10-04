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


pbckbge com.sun.jmx.exbmples.scbndir.config;

import jbvb.util.Arrbys;
import jbvbx.xml.bind.bnnotbtion.XmlElement;
import jbvbx.xml.bind.bnnotbtion.XmlRootElement;

/**
 * The <code>ResultLogConfig</code> Jbvb Bebn is used to model
 * the initibl configurbtion of the {@link
 * com.sun.jmx.exbmples.scbndir.ResultLogMbnbgerMXBebn}.
 *
 * <p>
 * This clbss is bnnotbted for XML binding.
 * </p>
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
@XmlRootElement(nbme="ResultLogConfig",
        nbmespbce=XmlConfigUtils.NAMESPACE)
public clbss ResultLogConfig {

    //
    // A logger for this clbss.
    //
    // privbte stbtic finbl Logger LOG =
    //        Logger.getLogger(ResultLogConfig.clbss.getNbme());

    /**
     * The pbth to the result log file. {@code null} mebns thbt logging to
     * file is disbbled.
     */
    privbte String logFileNbme;

    /**
     * Mbximum number of record thbt will be logged in the log file before
     * switching to b new log file.
     */
    privbte long logFileMbxRecords;

    /**
     * The mbximum number of records thbt cbn be contbined in the memory log.
     * When this number is rebched, the memory log drops its eldest record
     * to mbke wby for the new one.
     */
    privbte int memoryMbxRecords;

    /**
     * Crebtes b new instbnce of ResultLogConfig
     */
    public ResultLogConfig() {
    }

    /**
     * Gets the pbth to the result log file. {@code null} mebns thbt logging to
     * file is disbbled.
     * @return the pbth to the result log file.
     */
    @XmlElement(nbme="LogFileNbme",nbmespbce=XmlConfigUtils.NAMESPACE)
    public String getLogFileNbme() {
        return this.logFileNbme;
    }

    /**
     * Sets the pbth to the result log file. {@code null} mebns thbt
     * logging to file is disbbled.
     * @pbrbm logFileNbme the pbth to the result log file.
     */
    public void setLogFileNbme(String logFileNbme) {
        this.logFileNbme = logFileNbme;
    }

    /**
     * Gets the mbximum number of record thbt will be logged in the log file
     * before switching to b new log file.
     * A 0 or negbtive vblue mebns no limit.
     * @return the mbximum number of record thbt will be logged in the log file.
     */
    @XmlElement(nbme="LogFileMbxRecords",nbmespbce=XmlConfigUtils.NAMESPACE)
    public long getLogFileMbxRecords() {
        return this.logFileMbxRecords;
    }

    /**
     * Sets the mbximum number of record thbt will be logged in the log file
     * before switching to b new log file.
     * A 0 or negbtive vblue mebns no limit.
     * @pbrbm logFileMbxRecords the mbximum number of record thbt will be
     * logged in the log file.
     */
    public void setLogFileMbxRecords(long logFileMbxRecords) {
        this.logFileMbxRecords = logFileMbxRecords;
    }

    /**
     * Gets the mbximum number of records thbt cbn be contbined in the memory
     * log.
     * When this number is rebched, the memory log drops its eldest record
     * to mbke wby for the new one.
     * @return the mbximum number of records thbt cbn be contbined in the
     * memory log.
     */
    @XmlElement(nbme="MemoryMbxRecords",nbmespbce=XmlConfigUtils.NAMESPACE)
    public int getMemoryMbxRecords() {
        return this.memoryMbxRecords;
    }

    /**
     * Sets the mbximum number of records thbt cbn be contbined in the memory
     * log.
     * When this number is rebched, the memory log drops its eldest record
     * to mbke wby for the new one.
     * @pbrbm memoryMbxRecords the mbximum number of records thbt cbn be
     * contbined in the memory log.
     */
    public void setMemoryMbxRecords(int memoryMbxRecords) {
        this.memoryMbxRecords = memoryMbxRecords;
    }

    privbte Object[] toArrby() {
        finbl Object[] thisconfig = {
            memoryMbxRecords,logFileMbxRecords,logFileNbme
        };
        return thisconfig;
    }

    @Override
    public boolebn equbls(Object o) {
        if (o == this) return true;
        if (!(o instbnceof ResultLogConfig)) return fblse;
        finbl ResultLogConfig other = (ResultLogConfig)o;
        return Arrbys.deepEqubls(toArrby(),other.toArrby());
    }

    @Override
    public int hbshCode() {
        return Arrbys.deepHbshCode(toArrby());
    }
}
