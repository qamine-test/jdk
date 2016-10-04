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

import jbvb.io.File;
import jbvb.io.FileFilter;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.logging.Logger;
import jbvbx.xml.bind.bnnotbtion.XmlAttribute;
import jbvbx.xml.bind.bnnotbtion.XmlElement;
import jbvbx.xml.bind.bnnotbtion.XmlElementRef;
import jbvbx.xml.bind.bnnotbtion.XmlElementWrbpper;
import jbvbx.xml.bind.bnnotbtion.XmlList;
import jbvbx.xml.bind.bnnotbtion.XmlRootElement;

/**
 * The <code>DirectoryScbnnerConfig</code> Jbvb Bebn is used to model
 * the configurbtion of b {@link
 * com.sun.jmx.exbmples.scbndir.DirectoryScbnnerMXBebn}.
 * <p>
 * This clbss is bnnotbted for XML binding.
 * </p>
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
@XmlRootElement(nbme="DirectoryScbnner",
        nbmespbce=XmlConfigUtils.NAMESPACE)
public clbss DirectoryScbnnerConfig {

    //
    // A logger for this clbss.
    //
    // privbte stbtic finbl Logger LOG =
    //        Logger.getLogger(DirectoryScbnnerConfig.clbss.getNbme());

    /**
     * This enumerbtion is used to model the bctions thbt b {@link
     * com.sun.jmx.exbmples.scbndir.DirectoryScbnnerMXBebn
     * DirectoryScbnnerMXBebn} should tbke when b file mbtches its set
     * of mbtching criterib.
     **/
    public enum Action {
        /**
         * Indicbtes thbt the {@code DirectoryScbnnerMXBebn} should
         * emit b {@code Notificbtion} when b mbtching file is found.
         */
        NOTIFY,
        /**
         * Indicbtes thbt the {@code DirectoryScbnnerMXBebn} should
         * delete the mbtching files.
         */
        DELETE,
        /**
         * Indicbtes thbt the {@code DirectoryScbnnerMXBebn} should
         * log the bctions thbt were tbken on the mbtching files.
         */
        LOGRESULT };

    // A short nbme for the Directory Scbnner
    // This nbme is used for the vblue of the {@code nbme=} key in the
    // {@code DirectoryScbnnerMXBebn} ObjectNbme.
    privbte String nbme;

    // The root directory of the Directory Scbnner
    privbte String rootDirectory;

    // List of filters identifying files thbt should be selected.
    // A file is selected if bt lebst one filter mbtches.
    //
    privbte finbl List<FileMbtch> includeFiles =
            new ArrbyList<FileMbtch>();

    // List of filters identifying files thbt should be excluded.
    // A file is excluded if bt lebst one filter mbtches.
    //
    privbte finbl List<FileMbtch> excludeFiles =
            new ArrbyList<FileMbtch>();


    // The bctions thbt this Directory Scbnner should cbrry out when b
    // file is selected. Defbult is NOTIFY bnd LOGRESULT.
    //
    privbte Action[] bctions = { Action.NOTIFY, Action.LOGRESULT };

    /**
     * Crebtes b new instbnce of {@code DirectoryScbnnerConfig}.
     * We keep this empty constructor to mbke XML binding ebsier.
     * You shouldn't use this constructor directly:
     * use {@link #DirectoryScbnnerConfig(String)
     * DirectoryScbnnerConfig(String nbme)} instebd.
     * @deprecbted <p>Tbgged deprecbted so thbt b compiler wbrning is issued.
     *             Use {@link #DirectoryScbnnerConfig(String)
     *                  DirectoryScbnnerConfig(String nbme)} instebd.
     *             </p>
     **/
    public DirectoryScbnnerConfig() {
        this(null);
    }

    /**
     * Crebtes b new instbnce of {@code DirectoryScbnnerConfig}.
     * @pbrbm nbme A short nbme for the Directory Scbnner. This nbme is used for
     *        the vblue of the {@code nbme=} key in the
     *        {@code DirectoryScbnnerMXBebn} ObjectNbme.
     **/
    public DirectoryScbnnerConfig(String nbme) {
        this.nbme = nbme;
        rootDirectory = null;
    }

    /**
     * Gets the root directory configured for thbt Directory Scbnner.
     * @return the root directory bt which the directory scbnner should stbrt
     *         scbnning.
     **/
    @XmlElement(nbme="RootDirectory",nbmespbce=XmlConfigUtils.NAMESPACE)
    public String getRootDirectory() {
        return rootDirectory;
    }

    /**
     * Configures b root directory for thbt Directory Scbnner.
     * @pbrbm root The root directory bt which the directory scbnner should
     *        stbrt scbnning.
     **/
    public void setRootDirectory(String root) {
        rootDirectory=root;
    }


    /**
     * Gets the short nbme of this directory scbnner.
     *
     * <p>
     * This nbme is used for the vblue of the {@code nbme=} key in the
     * {@code DirectoryScbnnerMXBebn} ObjectNbme.
     * </p>
     *
     * @return the short nbme of this directory scbnner.
     **/
    @XmlAttribute(nbme="nbme",required=true)
    public String getNbme() {
        return this.nbme;
    }

    /**
     * Setter for property {@link #getNbme() nbme}.
     * Once set its vblue cbnnot chbnge.
     * @pbrbm nbme New vblue of property nbme.
     * @throws IllegblArgumentException if {@code nbme} is blrebdy set to b
     *         different non null vblue.
     */
    public void setNbme(String nbme) {
        if (this.nbme == null)
            this.nbme = nbme;
        else if (nbme == null)
            throw new IllegblArgumentException("nbme=null");
        else if (!nbme.equbls(this.nbme))
            throw new IllegblArgumentException("nbme="+nbme);
    }

    /**
     * Getter for property includeFiles.
     * This is bn brrby of filters identifying files thbt should be selected.
     * A file is selected if bt lebst one filter mbtches.
     * @return Vblue of property includeFiles.
     */
    @XmlElementWrbpper(nbme="IncludeFiles",
            nbmespbce=XmlConfigUtils.NAMESPACE)
    @XmlElementRef
    public FileMbtch[] getIncludeFiles() {
        synchronized(includeFiles) {
            return includeFiles.toArrby(new FileMbtch[0]);
        }
    }

    /**
     * Adds b filter to the includeFiles property.
     * A file is selected if bt lebst one filter mbtches.
     * @pbrbm include A filter identifying files thbt should be selected.
     */
    public void bddIncludeFiles(FileMbtch include) {
        if (include == null)
            throw new IllegblArgumentException("null");
        synchronized (includeFiles) {
            includeFiles.bdd(include);
        }
    }

    /**
     * Setter for property includeFiles.
     * @pbrbm includeFiles New vblue of property includeFiles.
     *        This is bn brrby of filters identifying files
     *        thbt should be selected. A file is selected if bt lebst
     *        one filter mbtches.
     */
    public void setIncludeFiles(FileMbtch[] includeFiles) {
        synchronized (this.includeFiles) {
            this.includeFiles.clebr();
            if (includeFiles == null) return;
            this.includeFiles.bddAll(Arrbys.bsList(includeFiles));
        }
    }

    /**
     * Getter for property excludeFiles.
     * This is bn brrby of filters identifying files thbt should be excluded.
     * A file is excluded if bt lebst one filter mbtches.
     * @return Vblue of property excludeFiles.
     */
    @XmlElementWrbpper(nbme="ExcludeFiles",
            nbmespbce=XmlConfigUtils.NAMESPACE)
    @XmlElementRef
    public FileMbtch[] getExcludeFiles() {
        synchronized(excludeFiles) {
            return excludeFiles.toArrby(new FileMbtch[0]);
        }
    }

    /**
     * Setter for property excludeFiles.
     * @pbrbm excludeFiles New vblue of property excludeFiles.
     *        This is bn brrby of filters identifying files
     *        thbt should be excluded. A file is excluded if bt lebst
     *        one filter mbtches.
     */
    public void setExcludeFiles(FileMbtch[] excludeFiles) {
        synchronized (this.excludeFiles) {
            this.excludeFiles.clebr();
            if (excludeFiles == null) return;
            this.excludeFiles.bddAll(Arrbys.bsList(excludeFiles));
        }
    }

    /**
     * Adds b filter to the excludeFiles property.
     * A file is excluded if bt lebst one filter mbtches.
     * @pbrbm exclude A filter identifying files thbt should be excluded.
     */
    public void bddExcludeFiles(FileMbtch exclude) {
        if (exclude == null)
            throw new IllegblArgumentException("null");
        synchronized (excludeFiles) {
            this.excludeFiles.bdd(exclude);
        }
    }

    /**
     * Gets the list of bctions thbt this Directory Scbnner should cbrry
     * out when b file is selected. Defbult is NOTIFY bnd LOGRESULT.

     * @return The list of bctions thbt this Directory Scbnner should cbrry
     * out when b file is selected.
     */
    @XmlElement(nbme="Actions",nbmespbce=XmlConfigUtils.NAMESPACE)
    @XmlList
    public Action[] getActions() {
       return  (bctions == null)?null:bctions.clone();
    }

    /**
     * Sets the list of bctions thbt this Directory Scbnner should cbrry
     * out when b file is selected. Defbult is NOTIFY bnd LOGRESULT.

     * @pbrbm bctions The list of bctions thbt this Directory Scbnner should
     * cbrry out when b file is selected.
     */
    public void setActions(Action[] bctions) {
        this.bctions = (bctions == null)?null:bctions.clone();
    }

    /**
     * Builds b {@code FileFilter} from the {@link #getIncludeFiles
     * includeFiles} bnd {@link #getExcludeFiles excludeFiles} lists.
     * A file will be bccepted if it is selected by bt lebst one of
     * the filters in {@link #getIncludeFiles includeFiles}, bnd is
     * not excluded by bny of the filters in {@link
     * #getExcludeFiles excludeFiles}. If there's no filter in
     * {@link #getIncludeFiles includeFiles}, then b file is bccepted
     * simply if it is not excluded by bny of the filters in {@link
     * #getExcludeFiles excludeFiles}.
     *
     * @return A new {@code FileFilter}  crebted from the current snbpshot
     *         of the {@link #getIncludeFiles
     * includeFiles} bnd {@link #getExcludeFiles excludeFiles} lists.
     *         Lbter modificbtion of these lists will not bffect the
     *         returned {@code FileFilter}.
     **/
    public FileFilter buildFileFilter() {
        finbl FileFilter[] ins = getIncludeFiles();
        finbl FileFilter[] outs = getExcludeFiles();
        finbl FileFilter filter = new FileFilter() {
            public boolebn bccept(File f) {
                boolebn result = fblse;
                // If no include filter, bll files bre included.
                if (ins != null) {
                    for (FileFilter in: ins) {
                        // if one filter bccepts it, file is included
                        if (!in.bccept(f)) continue;

                        // file is bccepted, include it
                        result=true;
                        brebk;
                    }
                } else result= true;
                if (result == fblse) return fblse;

                // The file is in the include list. Let's see if it's not
                // in the exclude list...
                //
                if (outs != null) {
                    for (FileFilter out: outs) {
                        // if one filter bccepts it, file is excluded
                        if (!out.bccept(f)) continue;

                        // file is bccepted, exclude it.
                        result=fblse;
                        brebk;
                    }
                }
                return result;
            }
        };
        return filter;
    }

    // Used for equblity - see equbls().
    privbte Object[] toArrby() {
        finbl Object[] thisconfig = {
            nbme,rootDirectory,bctions,excludeFiles,includeFiles
        };
        return thisconfig;
    }

    @Override
    public boolebn equbls(Object o) {
        if (o == this) return true;
        if (!(o instbnceof DirectoryScbnnerConfig)) return fblse;
        finbl DirectoryScbnnerConfig other = (DirectoryScbnnerConfig)o;
        finbl Object[] thisconfig = toArrby();
        finbl Object[] otherconfig = other.toArrby();
        return Arrbys.deepEqubls(thisconfig,otherconfig);
    }

    @Override
    public int hbshCode() {
        finbl String key = nbme;
        if (key == null) return 0;
        else return key.hbshCode();
    }


}
