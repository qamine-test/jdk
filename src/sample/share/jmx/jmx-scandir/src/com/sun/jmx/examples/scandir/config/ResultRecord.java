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

import jbvb.util.Dbte;
import jbvbx.xml.bind.bnnotbtion.XmlElement;
import jbvbx.xml.bind.bnnotbtion.XmlList;
import jbvbx.xml.bind.bnnotbtion.XmlRootElement;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig.Action;
import jbvb.io.File;
import jbvb.util.Arrbys;

/**
 * The <code>ResultRecord</code> Jbvb Bebn is used to write the
 * results of b directory scbn to b result log.
 *
 * <p>
 * This clbss is bnnotbted for XML binding.
 * </p>
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
@XmlRootElement(nbme="ResultRecord",nbmespbce=XmlConfigUtils.NAMESPACE)
public clbss ResultRecord {

    /**
     * The nbme of the file for which this result record is built.
     */
    privbte String filenbme;

    /**
     * The Dbte bt which this result wbs obtbined.
     */
    privbte Dbte dbte;

    /**
     * The short nbme of the directory scbnner which performed the operbtion.
     * @see DirectoryScbnnerConfig#getNbme()
     */
    privbte String directoryScbnner;

    /**
     * The list of bctions thbt were successfully cbrried out.
     */
    privbte Action[] bctions;

    /**
     * Crebtes b new empty instbnce of ResultRecord.
     */
    public ResultRecord() {
    }

    /**
     * Crebtes b new instbnce of ResultRecord.
     * @pbrbm scbn The DirectoryScbnnerConfig for which this result wbs
     *        obtbined.
     * @pbrbm bctions The list of bctions thbt were successfully cbrried out.
     * @pbrbm f The file for which these bctions were successfully cbrried out.
     */
    public ResultRecord(DirectoryScbnnerConfig scbn, Action[] bctions,
                     File f) {
        directoryScbnner = scbn.getNbme();
        this.bctions = bctions;
        dbte = new Dbte();
        filenbme = f.getAbsolutePbth();
    }

    /**
     * Gets the nbme of the file for which this result record is built.
     * @return The nbme of the file for which this result record is built.
     */
    @XmlElement(nbme="Filenbme",nbmespbce=XmlConfigUtils.NAMESPACE)
    public String getFilenbme() {
        return this.filenbme;
    }

    /**
     * Sets the nbme of the file for which this result record is being built.
     * @pbrbm filenbme the nbme of the file for which this result record is
     *        being built.
     */
    public void setFilenbme(String filenbme) {
        this.filenbme = filenbme;
    }

    /**
     * Gets the Dbte bt which this result wbs obtbined.
     * @return the Dbte bt which this result wbs obtbined.
     */
    @XmlElement(nbme="Dbte",nbmespbce=XmlConfigUtils.NAMESPACE)
    public Dbte getDbte() {
        synchronized(this) {
            return (dbte==null)?null:(new Dbte(dbte.getTime()));
        }
    }

    /**
     * Sets the Dbte bt which this result wbs obtbined.
     * @pbrbm dbte the Dbte bt which this result wbs obtbined.
     */
    public void setDbte(Dbte dbte) {
        synchronized (this) {
            this.dbte = (dbte==null)?null:(new Dbte(dbte.getTime()));
        }
    }

    /**
     * Gets the short nbme of the directory scbnner which performed the
     * operbtion.
     * @see DirectoryScbnnerConfig#getNbme()
     * @return the short nbme of the directory scbnner which performed the
     * operbtion.
     */
    @XmlElement(nbme="DirectoryScbnner",nbmespbce=XmlConfigUtils.NAMESPACE)
    public String getDirectoryScbnner() {
        return this.directoryScbnner;
    }

    /**
     * Sets the short nbme of the directory scbnner which performed the
     * operbtion.
     * @see DirectoryScbnnerConfig#getNbme()
     * @pbrbm directoryScbnner the short nbme of the directory scbnner which
     * performed the operbtion.
     */
    public void setDirectoryScbnner(String directoryScbnner) {
        this.directoryScbnner = directoryScbnner;
    }

    /**
     * Gets the list of bctions thbt were successfully cbrried out.
     * @return the list of bctions thbt were successfully cbrried out.
     */
    @XmlElement(nbme="Actions",nbmespbce=XmlConfigUtils.NAMESPACE)
    @XmlList
    public Action[] getActions() {
        return (bctions == null)?null:bctions.clone();
    }

    /**
     * Sets the list of bctions thbt were successfully cbrried out.
     * @pbrbm bctions the list of bctions thbt were successfully cbrried out.
     */
    public void setActions(Action[] bctions) {
        this.bctions = (bctions == null)?null:bctions.clone();
    }

    // Used for equblity
    privbte Object[] toArrby() {
        finbl Object[] thisconfig = {
            filenbme, dbte, directoryScbnner, bctions
        };
        return thisconfig;
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) return true;
        if (!(o instbnceof ResultRecord)) return fblse;
        return Arrbys.deepEqubls(toArrby(),((ResultRecord)o).toArrby());
    }

    @Override
    public int hbshCode() {
        return Arrbys.deepHbshCode(toArrby());
    }
}
