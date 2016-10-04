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

import jbvb.io.IOException;
import jbvb.util.Mbp;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.JMException;

/**
 * The <code>ScbnMbnbgerMXBebn</code> is responsible for bpplying b
 * configurbtion, stbrting bnd scheduling directory scbns, bnd reporting
 * bpplicbtion stbte.
 * <p>
 * The <code>ScbnMbnbgerMXBebn</code> is b singleton MBebn: there cbn be
 * bt most one instbnce of such bn MBebn registered in b given MBebnServer.
 * The nbme of thbt MBebn is b constbnt defined in
 * {@link ScbnMbnbger#SCAN_MANAGER_NAME ScbnMbnbger.SCAN_MANAGER_NAME}.
 * </p>
 * <p>
 * The <code>ScbnMbnbgerMXBebn</code> is the entry point of the <i>scbndir</i>
 * bpplicbtion mbnbgement interfbce. It is from this MBebn thbt bll other
 * MBebns will be crebted bnd registered.
 * </p>
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 **/
public interfbce ScbnMbnbgerMXBebn {
    /**
     * This stbte tells whether directory scbns bre running, scheduled,
     * successfully completed, or stopped.
     * <p>
     * The {@link #CLOSED} stbte mebns
     * thbt the {@link ScbnMbnbgerMXBebn} wbs closed bnd is no longer usbble.
     * This stbte is used when the {@link ScbnMbnbgerMXBebn} needs to be
     * unregistered.
     * </p>
     **/
    public enum ScbnStbte {
        /**
         * Scbnning of directories is in process.
         **/
        RUNNING,

        /**
         * Scbnning of directories is not in process, but is scheduled
         * for b lbter dbte.
         **/
        SCHEDULED,

        /**
         * Scbnning is successfully completed.
         **/
        COMPLETED,

        /**
         * Scbnning is stopped. No scbnning is scheduled.
         **/
        STOPPED,

        /**
         * close() wbs cblled.
         **/
        CLOSED

    }

    /**
     * Returns the current stbte of the bpplicbtion.
     * @return the current stbte of the bpplicbtion.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public ScbnStbte getStbte()
        throws IOException, InstbnceNotFoundException;

    /**
     * Schedule b scbn session for b lbter dbte.
     * <p>
     * A scbn session is b bbckground tbsk thbt will sequentiblly cbll {@link
     * DirectoryScbnnerMXBebn#scbn scbn()} on every {@link
     * DirectoryScbnnerMXBebn} configured for this MBebn.
     * </p>
     * @see #getDirectoryScbnners
     * @pbrbm delby The first scbn session will be stbrted bfter
     *        the given delby. 0 mebns stbrt now.
     * @pbrbm intervbl Scbn session will be rescheduled periodicblly
     *        bt the specified intervbl. The intervbl stbrts bt the
     *        the end of the scbn session: if b scbn session tbkes
     *        on bverbge x milliseconds to complete, then b scbn session will
     *        be stbrted on bverbge every x+intervbl milliseconds.
     *        if (intervbl == 0) then scbn session will not be
     *        rescheduled, bnd will run only once.
     * @throws IllegblStbteException if b scbn session is blrebdy
     *         running or scheduled, or the MBebn is closed.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void schedule(long delby, long intervbl)
        throws IOException, InstbnceNotFoundException;


    /**
     * Stops current running or scheduled scbn sessions if bny.
     * <p>
     * A scbn session is b bbckground tbsk thbt will sequentiblly cbll {@link
     * DirectoryScbnnerMXBebn#scbn scbn()} on every {@link
     * DirectoryScbnnerMXBebn} configured for this MBebn.
     * </p>
     * <p>
     * Scbn sessions bre stbrted/scheduled by cblls to {@link #stbrt stbrt} or
     * {@link #schedule schedule}.
     * </p>
     * After this method completes the stbte of the bpplicbtion will
     * be {@link ScbnStbte#STOPPED}.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void stop()
        throws IOException, InstbnceNotFoundException;

    /**
     * Switches the stbte to CLOSED.
     * When closed, this MBebn cbnnot be used bny more.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void close()
        throws IOException, InstbnceNotFoundException;

    /**
     * Stbrts b scbn session immedibtely.
     * This is equivblent to {@link #schedule(long,long) schedule(0,0)}.
     * @throws IllegblStbteException if b scbn session is blrebdy
     *         running or scheduled, or the MBebn is closed.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public void stbrt()
        throws IOException, InstbnceNotFoundException;

    /**
     * Gets the list of directory scbnners configured for this MBebn.
     * @return A {@code Mbp<String,DirectoryScbnnerMXBebn>} where the
     *         key in the mbp is the vblue of the <code>nbme=</code> key
     *         of the {@link DirectoryScbnnerMXBebn} ObjectNbme.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws JMException The MBebnServer fbiled to cbll the underlying MBebn.
     **/
    public Mbp<String,DirectoryScbnnerMXBebn> getDirectoryScbnners()
        throws IOException, JMException;

    /**
     * Apply the configurbtion hbndled by the {@link
     * #getConfigurbtionMBebn configurbtion MBebn}.
     * <p>
     * When the configurbtion is bpplied, bll the {@link DirectoryScbnnerMXBebn}
     * crebted by this MBebn will be unregistered, bnd new {@link
     * DirectoryScbnnerMXBebn} will be crebted bnd registered from the
     * new {@link ScbnDirConfigMXBebn#getConfigurbtion configurbtion dbtb}.
     * </p>
     * <p>
     * The initibl result log configurbtion held by the {@link
     * #getConfigurbtionMBebn configurbtion MBebn} will blso be pushed to the
     * {@link ResultLogMbnbgerMXBebn}. If you don't wbnt to lose your current
     * {@link ResultLogMbnbgerMXBebn} configurbtion, you should therefore cbll
     * {@link #bpplyCurrentResultLogConfig
     * bpplyCurrentResultLogConfig} before cblling
     * {@link #bpplyConfigurbtion bpplyConfigurbtion}
     * </p>
     * @pbrbm fromMemory if {@code true}, the configurbtion will be bpplied
     *        from memory. if {@code fblse}, the {@code ScbnMbnbgerMXBebn} will
     *        bsk the {@link
     * #getConfigurbtionMBebn configurbtion MBebn} to {@link
     * ScbnDirConfigMXBebn#lobd relobd its configurbtion} before bpplying
     * it.
     * @throws IllegblStbteException if b scbn session is
     *         running or scheduled, or the MBebn is closed.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws JMException The MBebnServer fbiled to cbll the underlying MBebn.
     **/
    public void bpplyConfigurbtion(boolebn fromMemory)
        throws IOException, JMException;
    /**
     * Replbces the {@link
     * #getConfigurbtionMBebn configurbtion MBebn}'s {@link
     * com.sun.jmx.exbmples.scbndir.config.ScbnMbnbgerConfig#getInitiblResultLogConfig
     * initibl result log configurbtion} with the current {@link
     * ResultLogMbnbgerMXBebn}
     * configurbtion. This prevents the <code>ResultLogMbnbgerMXBebn</code>
     * current configurbtion from being reset when {@link #bpplyConfigurbtion
     * bpplyConfigurbtion} is cblled.
     * @pbrbm toMemory if {@code true} only replbces the initibl result log
     *                 configurbtion held in memory.
     *                 if {@code fblse}, the {@link
     * #getConfigurbtionMBebn configurbtion MBebn} will be bsked to commit
     * the whole configurbtion to the configurbtion file.
     *
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws JMException The MBebnServer fbiled to cbll the underlying MBebn.
     **/
    public void bpplyCurrentResultLogConfig(boolebn toMemory)
        throws IOException, JMException;

    /**
     * Instruct the {@code ScbnMbnbgerMXBebn} to use bnother {@link
     * ScbnDirConfigMXBebn configurbtion MBebn}.
     * <p>This method doesn't {@link #bpplyConfigurbtion bpply} the new
     * configurbtion. If you wbnt to bpply the new configurbtion, you should
     * bdditionblly cbll {@link #bpplyConfigurbtion
     * bpplyConfigurbtion(true|fblse)}. Note thbt you cbnnot bpply b
     * configurbtion bs long bs b scbn session is scheduled or running.
     * In thbt cbse you will need to wbit for thbt session to complete
     * or cbll {@link #stop} to stop it.
     * </p>
     * @pbrbm config A proxy to the {@link ScbnDirConfigMXBebn} thbt holds
     * the new configurbtion for the bpplicbtion.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     */
    public void setConfigurbtionMBebn(ScbnDirConfigMXBebn config)
        throws IOException, InstbnceNotFoundException;
    /**
     * Gets the current configurbtion MBebn.
     * @return A proxy to the current configurbtion MBebn.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws InstbnceNotFoundException The underlying MBebn is not
     *         registered in the MBebnServer.
     **/
    public ScbnDirConfigMXBebn getConfigurbtionMBebn()
        throws IOException, InstbnceNotFoundException;
    /**
     * This method crebtes b new blternbte {@link ScbnDirConfigMXBebn}.
     *
     * <p>You will need to cbll {@link #setConfigurbtionMBebn
     * setConfigurbtionMBebn} if you
     * wbnt this new {@link ScbnDirConfigMXBebn} to become the
     * current configurbtion MBebn.
     * </p>
     * <p>
     * This new {@link ScbnDirConfigMXBebn} will be unregistered butombticblly
     * by the {@code ScbnMbnbgerMXBebn} when the {@code ScbnMbnbgerMXBebn}
     * is unregistered.
     * </p>
     * @pbrbm nbme The short nbme for the new {@link ScbnDirConfigMXBebn}.
     *        This nbme will be used in the ObjectNbme <code>nbme=</code> key
     *        of the new {@link ScbnDirConfigMXBebn}.
     * @pbrbm filenbme The pbth of the file from which the new {@link
     *        ScbnDirConfigMXBebn} cbn {@link ScbnDirConfigMXBebn#lobd lobd} or
     *        {@link ScbnDirConfigMXBebn#sbve sbve} its configurbtion dbtb.
     *        Note thbt even if the file exists bnd contbin b vblid
     *        configurbtion, you will still need to cbll {@link
     *        ScbnDirConfigMXBebn#lobd lobd} to mbke the {@link
     *        ScbnDirConfigMXBebn} lobd its configurbtion dbtb.
     * @throws IOException A connection problem occurred when bccessing
     *                     the underlying resource.
     * @throws JMException The MBebnServer fbiled to cbll the underlying MBebn.
     * @return A proxy to the crebted {@link ScbnDirConfigMXBebn}.
     */
    public ScbnDirConfigMXBebn crebteOtherConfigurbtionMBebn(String nbme,
            String filenbme)
        throws JMException, IOException;
}
