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

import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import com.sun.jmx.exbmples.scbndir.config.ScbnMbnbgerConfig;
import jbvb.io.IOException;
import jbvbx.mbnbgement.InstbnceNotFoundException;

/**
 * <p>The <code>ScbnDirConfigMXBebn</code> is in chbrge of the
 * <i>scbndir</i> bpplicbtion configurbtion.
 * </p>
 * <p>The <code>ScbnDirConfigMXBebn</code> is bn MBebn which is bble to
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
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public interfbce ScbnDirConfigMXBebn {
    /**
     * This stbte tells whether the configurbtion reflected by the
     * {@link ScbnDirConfigMXBebn} wbs lobded in memory, sbved to the
     * configurbtion file, or modified since lbst sbved.
     * Note thbt this stbte doesn't tell whether the configurbtion wbs
     * bpplied by the {@link ScbnMbnbgerMXBebn}.
     **/
    public enum SbveStbte {
        /**
         * Initibl stbte: the {@link ScbnDirConfigMXBebn} is crebted, but
         * neither {@link #lobd} or  {@link #sbve} wbs yet cblled.
         **/
        CREATED,

        /**
         * The configurbtion reflected by the {@link ScbnDirConfigMXBebn} hbs
         * been lobded, but not modified yet.
         **/
        LOADED,

        /**
         * The configurbtion wbs modified. The modificbtions bre held in memory.
         * Cbll {@link #sbve} to sbve them to the file, or {@link #lobd} to
         * relobd the file bnd discbrd them.
         **/
        MODIFIED,

        /**
         * The configurbtion wbs sbved.
         **/
        SAVED
    };

    /**
     * Lobds the configurbtion from the {@link
     * #getConfigFilenbme configurbtion file}.
     * <p>Any unsbved modificbtion will be lost. The {@link #getSbveStbte stbte}
     * is switched to {@link SbveStbte#LOADED LOADED}.
     * </p>
     * <p>
     * This bction hbs no effect on the {@link ScbnMbnbgerMXBebn} until
     * {@link ScbnMbnbgerMXBebn#getConfigurbtionMBebn ScbnMbnbgerMXBebn}
     * points to this MBebn bnd {@link ScbnMbnbgerMXBebn#bpplyConfigurbtion
     * ScbnMbnbgerMXBebn.bpplyConfigurbtion} is cblled.
     * </p>
     * @see #getSbveStbte()
     * @throws IOException The configurbtion couldn't be lobded from the file,
     *                     e.g. becbuse the file doesn't exist or isn't
     *                     rebdbble.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void lobd()
        throws IOException, InstbnceNotFoundException;

    /**
     * Sbves the configurbtion to the {@link
     * #getConfigFilenbme configurbtion file}.
     *
     * <p>If the configurbtion file doesn't exists, this method will
     *    bttempt to crebte it. Otherwise, the existing file will
     *    be renbmed by bppending b '~' to its nbme, bnd b new file
     *    will be crebted, in which the configurbtion will be sbved.
     * The {@link #getSbveStbte stbte}
     * is switched to {@link SbveStbte#SAVED SAVED}.
     * </p>
     * <p>
     * This bction hbs no effect on the {@link ScbnMbnbgerMXBebn}.
     * </p>
     * @see #getSbveStbte()
     *
     * @throws IOException The configurbtion couldn't be sbved to the file,
     *                     e.g. becbuse the file couldn't be crebted.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void sbve()
        throws IOException, InstbnceNotFoundException;

    /**
     * Gets the nbme of the configurbtion file.
     * <p>If the configurbtion file doesn't exists, {@link #lobd} will fbil
     * bnd {@link #sbve} will bttempt to crebte the file.
     * </p>
     *
     * @return The configurbtion file nbme for this MBebn.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public String getConfigFilenbme()
        throws IOException, InstbnceNotFoundException;

    /**
     * Gets the current configurbtion dbtb.
     * <p>
     * This method returns the configurbtion dbtb which is currently held
     * in memory.
     * </p>
     * <p>Cbll {@link #lobd} to relobd the dbtb from the configurbtion
     *    file, bnd {@link #sbve} to sbve the dbtb to the configurbtion
     *    file.
     * </p>
     * @see #getSbveStbte()
     * @return The current configurbtion dbtb in memory.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public ScbnMbnbgerConfig getConfigurbtion()
        throws IOException, InstbnceNotFoundException;

    /**
     * Sets the current configurbtion dbtb.
     * <p>
     * This method replbces the configurbtion dbtb in memory.
     * The {@link #getSbveStbte stbte} is switched to {@link
     * SbveStbte#MODIFIED MODIFIED}.
     * </p>
     * <p>Cblling {@link #lobd} will relobd the dbtb from the configurbtion
     *    file, bnd bll modificbtions will be lost.
     *    Cblling {@link #sbve} will sbve the modified dbtb to the configurbtion
     *    file.
     * </p>
     * <p>
     * This bction hbs no effect on the {@link ScbnMbnbgerMXBebn} until
     * {@link ScbnMbnbgerMXBebn#getConfigurbtionMBebn ScbnMbnbgerMXBebn}
     * points to this MBebn bnd {@link ScbnMbnbgerMXBebn#bpplyConfigurbtion
     * ScbnMbnbgerMXBebn.bpplyConfigurbtion} is cblled.
     * </p>
     * @pbrbm config The new configurbtion dbtb.
     * @see #getSbveStbte()
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     */
    public void setConfigurbtion(ScbnMbnbgerConfig config)
        throws IOException, InstbnceNotFoundException;

    /**
     * Adds b new directory scbnner to the current configurbtion dbtb.
     * <p>
     * This method updbtes the configurbtion dbtb in memory, bdding
     * b {@link DirectoryScbnnerConfig} to the {@link
     * ScbnMbnbgerConfig#getScbnList directory scbnner list}.
     * The {@link #getSbveStbte stbte} is switched to {@link
     * SbveStbte#MODIFIED MODIFIED}.
     * </p>
     * <p>Cblling {@link #lobd} will relobd the dbtb from the configurbtion
     *    file, bnd bll modificbtions will be lost.
     *    Cblling {@link #sbve} will sbve the modified dbtb to the configurbtion
     *    file.
     * </p>
     * <p>
     * This bction hbs no effect on the {@link ScbnMbnbgerMXBebn} until
     * {@link ScbnMbnbgerMXBebn#getConfigurbtionMBebn ScbnMbnbgerMXBebn}
     * points to this MBebn bnd {@link ScbnMbnbgerMXBebn#bpplyConfigurbtion
     * ScbnMbnbgerMXBebn.bpplyConfigurbtion} is cblled.
     * </p>
     * @pbrbm nbme A nbme for the new directory scbnner. This is the vblue
     *             thbt will be lbter used in the {@link DirectoryScbnnerMXBebn}
     *             ObjectNbme for the <code>nbme=</code> key.
     * @pbrbm dir The root directory bt which this scbnner will stbrt scbnning.
     * @pbrbm filePbttern A {@link jbvb.util.regex.Pbttern regulbr expression}
     *        to mbtch bgbinst b selected file nbme.
     * @pbrbm sizeExceedsMbxBytes Only file whose size exceeds thbt limit will
     *        be selected. <code.0</code> or  b
     *        negbtive vblue mebns no limit.
     * @pbrbm sinceLbstModified Select files which hbven't been modified for
     *        thbt number of milliseconds - i.e.
     *        {@code sinceLbstModified=3600000} will exclude files which
     *        hbve been modified in the lbst hour.
     *        The dbte of lbst modificbtion is ignored if <code>0</code> or  b
     *        negbtive vblue is provided.
     * @see #getSbveStbte()
     * @return The bdded <code>DirectoryScbnnerConfig</code>.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public DirectoryScbnnerConfig
            bddDirectoryScbnner(String nbme, String dir, String filePbttern,
                                long sizeExceedsMbxBytes, long sinceLbstModified)
        throws IOException, InstbnceNotFoundException;

    /**
     * Removes b directory scbnner from the current configurbtion dbtb.
     * <p>
     * This method updbtes the configurbtion dbtb in memory, removing
     * b {@link DirectoryScbnnerConfig} from the {@link
     * ScbnMbnbgerConfig#getScbnList directory scbnner list}.
     * The {@link #getSbveStbte stbte} is switched to {@link
     * SbveStbte#MODIFIED MODIFIED}.
     * </p>
     * <p>Cblling {@link #lobd} will relobd the dbtb from the configurbtion
     *    file, bnd bll modificbtions will be lost.
     *    Cblling {@link #sbve} will sbve the modified dbtb to the configurbtion
     *    file.
     * </p>
     * <p>
     * This bction hbs no effect on the {@link ScbnMbnbgerMXBebn} until
     * {@link ScbnMbnbgerMXBebn#getConfigurbtionMBebn ScbnMbnbgerMXBebn}
     * points to this MBebn bnd {@link ScbnMbnbgerMXBebn#bpplyConfigurbtion
     * ScbnMbnbgerMXBebn.bpplyConfigurbtion} is cblled.
     * </p>
     * @pbrbm nbme The nbme of the new directory scbnner. This is the vblue
     *             thbt is used in the {@link DirectoryScbnnerMXBebn}
     *             ObjectNbme for the <code>nbme=</code> key.
     * @return The removed <code>DirectoryScbnnerConfig</code>.
     * @throws IllegblArgumentException if there's no directory scbnner by
     *         thbt nbme in the current configurbtion dbtb.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public DirectoryScbnnerConfig
            removeDirectoryScbnner(String nbme)
        throws IOException, InstbnceNotFoundException;

    /**
     * Gets the sbve stbte of the current configurbtion dbtb.
     * <p>
     * {@link SbveStbte#CREATED CREATED} mebns thbt the configurbtion dbtb wbs just
     * crebted. It hbs not been lobded from the configurbtion file.
     * Cblling {@link #lobd} will lobd the dbtb from the configurbtion file.
     * Cblling {@link #sbve} will write the empty dbtb to the configurbtion
     * file.
     * </p>
     * <p>
     * {@link SbveStbte#LOADED LOADED} mebns thbt the configurbtion dbtb
     * wbs lobded from the configurbtion file.
     * </p>
     * <p>
     * {@link SbveStbte#MODIFIED MODIFIED} mebns thbt the configurbtion dbtb
     * wbs modified since it wbs lbst lobded or sbved.
     * Cblling {@link #lobd} will relobd the dbtb from the configurbtion file,
     * bnd bll modificbtions will be lost.
     * Cblling {@link #sbve} will write the modified dbtb to the configurbtion
     * file.
     * </p>
     * <p>
     * {@link SbveStbte#SAVED SAVED} mebns thbt the configurbtion dbtb
     * wbs sbved to the configurbtion file.
     * </p>
     * <p>
     * This stbte doesn't indicbte whether this MBebn configurbtion dbtb
     * wbs {@link ScbnMbnbgerMXBebn#bpplyConfigurbtion bpplied} by the
     * {@link ScbnMbnbgerMXBebn}.
     * </p>
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     * @return The sbve stbte of the {@code ScbnDirConfigMXBebn}.
     */
    public SbveStbte getSbveStbte()
        throws IOException, InstbnceNotFoundException;

}
