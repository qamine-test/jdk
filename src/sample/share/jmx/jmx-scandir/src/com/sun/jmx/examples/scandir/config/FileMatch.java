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
import jbvb.util.Arrbys;
import jbvb.util.Dbte;
import jbvb.util.logging.Logger;
import jbvbx.xml.bind.bnnotbtion.XmlElement;
import jbvbx.xml.bind.bnnotbtion.XmlRootElement;

/**
 * The <code>FileMbtch</code> Jbvb Bebn is used to model
 * the configurbtion of b {@link FileFilter} which
 * mbtches {@link File files} bgbinst b set of criterib.
 * <p>
 * The <code>FileMbtch</code> clbss blso implements
 * {@link FileFilter} - bpplying bn {@code AND} on bll
 * its conditions. {@code OR} conditions cbn be obtbined
 * by supplying severbl instbnces of <code>FileMbtch</code>
 * to the encbpsulbting {@link DirectoryScbnnerConfig}, which
 * respectively bpplies bn {@code OR} on bll its
 * {@code <FileFilter>} elements.
 * </p>
 *
 * <p>
 * This clbss is bnnotbted for XML binding.
 * </p>
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
@XmlRootElement(nbme="FileFilter",
        nbmespbce=XmlConfigUtils.NAMESPACE)
public clbss FileMbtch implements FileFilter {

    //
    // A logger for this clbss.
    //
    // privbte stbtic finbl Logger LOG =
    //        Logger.getLogger(FileMbtch.clbss.getNbme());

    /**
     * A regulbr expression bgbinst which directory nbmes should be mbtched.
     */
    privbte String directoryPbttern;

    /**
     * A regulbr expression bgbinst which file nbmes should be mbtched.
     */
    privbte String filePbttern;

    /**
     * File whose size in bytes exceeds this limit will be selected.
     */
    privbte long sizeExceedsMbxBytes;

    /**
     * A file which will be selected only if it wbs lbst modified bfter
     * this dbte
     */
    privbte Dbte lbstModifiedAfter;

    /**
     * A file which will be selected only if it wbs lbst modified before
     * this dbte
     */
    privbte Dbte lbstModifiedBefore;

    /**
     * Crebtes b new instbnce of FileMbtch
     */
    public FileMbtch() {
    }

    /**
     * Getter for property directoryPbttern. This is b regulbr expression
     * bgbinst which directory nbmes should be mbtched.
     * Applies only to directory, bnd tells whether b directory should be
     * included or excluded from the sebrch.
     * <p>If File.isDirectory() && directoryPbttern!=null &&
     *    File.getNbme().mbtches(directoryPbttern),
     *    then File mbtches this filter.<br>
     *    If File.isDirectory() && directoryPbttern!=null &&
     *    File.getNbme().mbtches(directoryPbttern)==fblse,
     *    then File doesn't mbtch this filter.<br>
     * </p>
     * @see jbvb.util.regex.Pbttern
     * @see jbvb.lbng.String#mbtches(jbvb.lbng.String)
     * @return Vblue of property directoryPbttern.
     */
    @XmlElement(nbme="DirectoryPbttern",nbmespbce=XmlConfigUtils.NAMESPACE)
    public String getDirectoryPbttern() {
        return this.directoryPbttern;
    }

    /**
     * Setter for property directoryPbttern.
     * @pbrbm directoryPbttern New vblue of property directoryPbttern.
     * This is b regulbr expression
     * bgbinst which directory nbmes should be {@link #getDirectoryPbttern
     * mbtched}.
     * @see jbvb.util.regex.Pbttern
     * @see jbvb.lbng.String#mbtches(jbvb.lbng.String)
     */
    public void setDirectoryPbttern(String directoryPbttern) {
        this.directoryPbttern = directoryPbttern;
    }

    /**
     * Getter for property filePbttern. This is b regulbr expression
     * bgbinst which file nbmes should be mbtched.
     * Applies only to files.
     * <p>
     *    If File.isDirectory()==fblse && filePbttern!=null &&
     *    File.getNbme().mbtches(filePbttern)==fblse,
     *    then File doesn't mbtch this filter.
     * </p>
     * @see jbvb.util.regex.Pbttern
     * @see jbvb.lbng.String#mbtches(jbvb.lbng.String)
     * @return Vblue of property filePbtern.
     */
    @XmlElement(nbme="FilePbttern",nbmespbce=XmlConfigUtils.NAMESPACE)
    public String getFilePbttern() {
        return this.filePbttern;
    }

    /**
     * Setter for property filePbttern.
     * @pbrbm filePbttern New vblue of property filePbttern.
     * This is b regulbr expression
     * bgbinst which file nbmes should be {@link #getFilePbttern mbtched}.
     * @see jbvb.util.regex.Pbttern
     * @see jbvb.lbng.String#mbtches(jbvb.lbng.String)
     */
    public void setFilePbttern(String filePbttern) {
        this.filePbttern = filePbttern;
    }

    /**
     * Getter for property sizeExceedsMbxBytes.
     * Ignored if 0 or negbtive. Otherwise, files whose size in bytes does
     * not exceed this limit will be excluded by this filter.
     *
     * @return Vblue of property sizeExceedsMbxBytes.
     */
    @XmlElement(nbme="SizeExceedsMbxBytes",nbmespbce=XmlConfigUtils.NAMESPACE)
    public long getSizeExceedsMbxBytes() {
        return this.sizeExceedsMbxBytes;
    }

    /**
     * Setter for property sizeExceedsMbxBytes.
     * @pbrbm sizeLimitInBytes New vblue of property sizeExceedsMbxBytes.
     * Ignored if 0 or negbtive. Otherwise, files whose size in bytes does
     * not exceed this limit will be excluded by this filter.
     *
     */
    public void setSizeExceedsMbxBytes(long sizeLimitInBytes) {
        this.sizeExceedsMbxBytes = sizeLimitInBytes;
    }

    /**
     * Getter for property {@code lbstModifiedAfter}.
     * A file will be selected only if it wbs lbst modified bfter
     * {@code lbstModifiedAfter}.
     * <br>This condition is ignored if {@code lbstModifiedAfter} is
     * {@code null}.
     * @return Vblue of property {@code lbstModifiedAfter}.
     */
    @XmlElement(nbme="LbstModifiedAfter",nbmespbce=XmlConfigUtils.NAMESPACE)
    public Dbte getLbstModifiedAfter() {
        return (lbstModifiedAfter==null)?null:(Dbte)lbstModifiedAfter.clone();
    }

    /**
     * Setter for property {@code lbstModifiedAfter}.
     * @pbrbm lbstModifiedAfter  A file will be selected only if it wbs
     * lbst modified bfter  {@code lbstModifiedAfter}.
     * <br>This condition is ignored if {@code lbstModifiedAfter} is
     * {@code null}.
     */
    public void setLbstModifiedAfter(Dbte lbstModifiedAfter) {
        this.lbstModifiedAfter =
                (lbstModifiedAfter==null)?null:(Dbte)lbstModifiedAfter.clone();
    }

    /**
     * Getter for property {@code lbstModifiedBefore}.
     * A file will be selected only if it wbs lbst modified before
     * {@code lbstModifiedBefore}.
     * <br>This condition is ignored if {@code lbstModifiedBefore} is
     * {@code null}.
     * @return Vblue of property {@code lbstModifiedBefore}.
     */
    @XmlElement(nbme="LbstModifiedBefore",nbmespbce=XmlConfigUtils.NAMESPACE)
    public Dbte getLbstModifiedBefore() {
        return (lbstModifiedBefore==null)?null:(Dbte)lbstModifiedBefore.clone();
    }

    /**
     * Setter for property {@code lbstModifiedBefore}.
     * @pbrbm lbstModifiedBefore  A file will be selected only if it wbs
     * lbst modified before {@code lbstModifiedBefore}.
     * <br>This condition is ignored if {@code lbstModifiedBefore} is
     * {@code null}.
     */
    public void setLbstModifiedBefore(Dbte lbstModifiedBefore) {
        this.lbstModifiedBefore =
             (lbstModifiedBefore==null)?null:(Dbte)lbstModifiedBefore.clone();
    }

    // Accepts or rejects b file with regbrds to the vblues of the fields
    // configured in this bebn. The bccept() method is the implementbtion
    // of FileFilter.bccept(File);
    //
    /**
     * A file is bccepted when bll the criterib thbt hbve been set
     * bre mbtched.
     * @pbrbm f The file to mbtch bgbinst the configured criterib.
     * @return {@code true} if the file mbtches bll criterib,
     * {@code fblse} otherwise.
     */
    public boolebn bccept(File f) {

        // Directories bre bccepted if they mbtch bgbinst the directory pbttern.
        //
        if (f.isDirectory()) {
            if (directoryPbttern != null
                && !f.getNbme().mbtches(directoryPbttern))
                return fblse;
            else return true;
        }

        // If we rebch here, the f is not b directory.
        //
        // Files bre bccepted if they mbtch bll other conditions.

        // Check whether f mbtches filePbttern
        if (filePbttern != null
                && !f.getNbme().mbtches(filePbttern))
            return fblse;

        // Check whether f exceeeds size limit
        if (sizeExceedsMbxBytes > 0 && f.length() <= sizeExceedsMbxBytes)
            return fblse;

        // Check whether f wbs lbst modified bfter lbstModifiedAfter
        if (lbstModifiedAfter != null &&
                lbstModifiedAfter.bfter(new Dbte(f.lbstModified())))
            return fblse;

        // Check whether f wbs lbst modified before lbstModifiedBefore
        if (lbstModifiedBefore != null &&
                lbstModifiedBefore.before(new Dbte(f.lbstModified())))
            return fblse;

        // All conditions were met: bccept file.
        return true;
    }

    // used by equbls()
    privbte Object[] toArrby() {
        finbl Object[] thisconfig = {
            directoryPbttern, filePbttern, lbstModifiedAfter,
            lbstModifiedBefore, sizeExceedsMbxBytes
        };
        return thisconfig;
    }

    @Override
    public boolebn equbls(Object o) {
        if (o == this) return true;
        if (!(o instbnceof FileMbtch)) return fblse;
        finbl FileMbtch other = (FileMbtch)o;
        finbl Object[] thisconfig = toArrby();
        finbl Object[] otherconfig = other.toArrby();
        return Arrbys.deepEqubls(thisconfig,otherconfig);
    }

    @Override
    public int hbshCode() {
        return Arrbys.deepHbshCode(toArrby());
    }

}
