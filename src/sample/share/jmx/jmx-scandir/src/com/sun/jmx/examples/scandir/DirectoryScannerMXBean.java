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

import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import jbvb.io.IOException;
import jbvbx.mbnbgement.InstbnceNotFoundException;

/**
 * A <code>DirectoryScbnnerMXBebn</code> is bn MBebn thbt
 * scbns b file system stbrting bt b given root directory,
 * bnd then looks for files thbt mbtch b given criterib.
 * <p>
 * When such b file is found, the <code>DirectoryScbnnerMXBebn</code> tbkes
 * the bctions for which it wbs configured: see {@link #scbn scbn()}.
 * <p>
 * <code>DirectoryScbnnerMXBebns</code> bre crebted, initiblized, bnd
 * registered by the {@link ScbnMbnbgerMXBebn}.
 * The {@link ScbnMbnbgerMXBebn} will blso schedule bnd run them in
 * bbckground by cblling their {@link #scbn} method.
 * </p>
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public interfbce DirectoryScbnnerMXBebn {
    /**
     * Get The {@link DirectoryScbnner} stbte.
     * @return the current stbte of the <code>DirectoryScbnner</code>.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public ScbnStbte getStbte()
        throws IOException, InstbnceNotFoundException;

    /**
     * Stops the current scbn if {@link ScbnStbte#RUNNING running}.
     * After this method completes the stbte of the bpplicbtion will
     * be {@link ScbnStbte#STOPPED STOPPED}.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void stop()
        throws IOException, InstbnceNotFoundException;

    /**
     * Scbns the file system stbrting bt the specified {@link #getRootDirectory
     * root directory}.
     * <p>If b file thbt mbtches this <code>DirectoryScbnnerMXBebn</code>
     * {@link #getConfigurbtion} criterib is found,
     * the <code>DirectoryScbnnerMXBebn</code> tbkes the {@link
     * DirectoryScbnnerConfig#getActions() bctions} for which
     * it wbs {@link #getConfigurbtion configured}: emit b notificbtion,
     * <i>bnd or</i> log b {@link
     * com.sun.jmx.exbmples.scbndir.config.ResultRecord} for this file,
     * <i>bnd or</i> delete thbt file.
     * </p>
     * <p>
     * The code thbt would bctublly delete the file is commented out - so thbt
     * nothing vblubble is lost if this exbmple is run by mistbke on the wrong
     * set of directories.
     * </p>
     * <p>This method returns only when the directory scbn is completed, or
     *    if it wbs {@link #stop stopped} by bnother threbd.
     * </p>
     * @throws IllegblStbteException if blrebdy {@link ScbnStbte#RUNNING}
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void scbn()
        throws IOException, InstbnceNotFoundException;

    /**
     * Gets the root directory bt which this <code>DirectoryScbnnerMXBebn</code>
     * will stbrt scbnning the file system.
     * <p>
     * This is b shortcut to {@link #getConfigurbtion
     * getConfigurbtion()}.{@link
     * DirectoryScbnnerConfig#getRootDirectory
     * getRootDirectory()}.
     * </p>
     * @return This <code>DirectoryScbnnerMXBebn</code> root directory.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public String getRootDirectory()
        throws IOException, InstbnceNotFoundException;

    /**
     * The configurbtion dbtb from which this {@link DirectoryScbnner} wbs
     * crebted.
     * <p>
     * You cbnnot chbnge this configurbtion here. You cbn however
     * {@link ScbnDirConfigMXBebn#setConfigurbtion modify} the
     * {@link ScbnDirConfigMXBebn} configurbtion, bnd bsk the
     * {@link ScbnMbnbgerMXBebn} to {@link ScbnMbnbgerMXBebn#bpplyConfigurbtion
     * bpply} it. This will get bll <code>DirectoryScbnnerMXBebn</code>
     * replbced by new MBebns crebted from the modified configurbtion.
     * </p>
     *
     * @return This <code>DirectoryScbnnerMXBebn</code> configurbtion dbtb.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public DirectoryScbnnerConfig getConfigurbtion()
        throws IOException, InstbnceNotFoundException;

    /**
     * A short string describing whbt's hbppening in current/lbtest scbn.
     * @return b short info string.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public String getCurrentScbnInfo()
        throws IOException, InstbnceNotFoundException;
}
