/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.jmx.fxbmplfs.sdbndir.donfig;

import jbvb.util.Arrbys;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.Mbp;
import jbvbx.xml.bind.bnnotbtion.XmlAttributf;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfnt;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfntRff;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfntWrbppfr;
import jbvbx.xml.bind.bnnotbtion.XmlRootElfmfnt;


/**
 * Tif <dodf>SdbnMbnbgfrConfig</dodf> Jbvb Bfbn is usfd to modfl
 * tif donfigurbtion of tif {@link
 * dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn SdbnMbnbgfrMXBfbn}.
 *
 * Tif {@link
 * dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn SdbnMbnbgfrMXBfbn} will
 * usf tiis donfigurbtion to initiblizf tif {@link
 * dom.sun.jmx.fxbmplfs.sdbndir.RfsultLogMbnbgfrMXBfbn RfsultLogMbnbgfrMXBfbn}
 * bnd drfbtf tif {@link
 * dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfrMXBfbn DirfdtorySdbnnfrMXBfbns}
 * <p>
 * Tiis dlbss is bnnotbtfd for XML binding.
 * </p>
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 **/
@XmlRootElfmfnt(nbmf="SdbnMbnbgfr",
        nbmfspbdf="jmx:dom.sun.jmx.fxbmplfs.sdbndir.donfig")
publid dlbss SdbnMbnbgfrConfig {

    // A loggfr for tiis dlbss
    //
    // privbtf stbtid finbl Loggfr LOG =
    //        Loggfr.gftLoggfr(SdbnMbnbgfrConfig.dlbss.gftNbmf());

    /**
     * A sft of DirfdtorySdbnnfrConfig objfdts indfxfd by tifir nbmfs.
     **/
    privbtf finbl Mbp<String, DirfdtorySdbnnfrConfig> dirfdtorySdbnnfrs;

    /**
     * Tif initibl Rfsult Log donfigurbtion.
     */
    privbtf RfsultLogConfig initiblRfsultLogConfig;

    /**
     * Holds vbluf of propfrty nbmf. Tif nbmf of tif donfigurbtion
     *       usublly dorrfsponds to
     *       tif vbluf of tif {@dodf nbmf=} kfy of tif {@dodf ObjfdtNbmf}
     *       of tif {@link
     *       dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfigMXBfbn
     *       SdbnDirConfigMXBfbn} wiidi owns tiis donfigurbtion.
     **/
    privbtf String nbmf;

    /**
     * Crfbtfs b nfw instbndf of SdbnMbnbgfrConfig.
     * <p>You siould not usf tiis donstrudtor dirfdtly, but usf
     *    {@link #SdbnMbnbgfrConfig(String)} instfbd.
     * </p>
     * <p>Tiis donstrudtor is tbggfd dfprfdbtfd so tibt tif dompilfr
     *    will gfnfrbtf b wbrning if it is usfd by mistbkf.
     * </p>
     * @dfprfdbtfd Usf {@link #SdbnMbnbgfrConfig(String)} instfbd. Tiis
     *             donstrudtor is usfd tirougi rfflfdtion by tif XML
     *             binding frbmfwork.
     */
    publid SdbnMbnbgfrConfig() {
        tiis(null,truf);
    }

    /**
     * Crfbtfs b nfw instbndf of SdbnMbnbgfrConfig.
     * @pbrbm nbmf Tif nbmf of tif donfigurbtion wiidi usublly dorrfsponds to
     *       tif vbluf of tif {@dodf nbmf=} kfy of tif {@dodf ObjfdtNbmf}
     *       of tif {@link
     *       dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfigMXBfbn
     *       SdbnDirConfigMXBfbn} wiidi owns tiis donfigurbtion.
     **/
    publid SdbnMbnbgfrConfig(String nbmf) {
        tiis(nbmf,fblsf);
    }

    // Our privbtf donstrudtor...
    privbtf SdbnMbnbgfrConfig(String nbmf, boolfbn bllowsNull) {
        if (nbmf == null && bllowsNull==fblsf)
            tirow nfw IllfgblArgumfntExdfption("nbmf=null");
        tiis.nbmf = nbmf;
        dirfdtorySdbnnfrs = nfw LinkfdHbsiMbp<String,DirfdtorySdbnnfrConfig>();
        tiis.initiblRfsultLogConfig = nfw RfsultLogConfig();
        tiis.initiblRfsultLogConfig.sftMfmoryMbxRfdords(1024);
    }

    // Crfbtfs bn brrby for dffp fqublity.
    privbtf Objfdt[] toArrby() {
        finbl Objfdt[] tiisdonfig = {
            nbmf,dirfdtorySdbnnfrs,initiblRfsultLogConfig
        };
        rfturn tiisdonfig;
    }

    // fqubls
    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis) rfturn truf;
        if (!(o instbndfof SdbnMbnbgfrConfig)) rfturn fblsf;
        finbl SdbnMbnbgfrConfig otifr = (SdbnMbnbgfrConfig)o;
        if (tiis.dirfdtorySdbnnfrs.sizf() != otifr.dirfdtorySdbnnfrs.sizf())
            rfturn fblsf;
        rfturn Arrbys.dffpEqubls(toArrby(),otifr.toArrby());
    }

    @Ovfrridf
    publid int ibsiCodf() {
        finbl String kfy = nbmf;
        if (kfy == null) rfturn 0;
        flsf rfturn kfy.ibsiCodf();
    }

    /**
     * Gfts tif nbmf of tiis donfigurbtion. Tif nbmf of tif donfigurbtion
     *       usublly dorrfsponds to
     *       tif vbluf of tif {@dodf nbmf=} kfy of tif {@dodf ObjfdtNbmf}
     *       of tif {@link
     *       dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfigMXBfbn
     *       SdbnDirConfigMXBfbn} wiidi owns tiis donfigurbtion.
     * @rfturn Tif nbmf of tiis donfigurbtion.
     */
    @XmlAttributf(nbmf="nbmf",rfquirfd=truf)
    publid String gftNbmf() {
        rfturn tiis.nbmf;
    }

    /**
     * Sfts tif nbmf of tiis donfigurbtion. Tif nbmf of tif donfigurbtion
     *       usublly dorrfsponds to
     *       tif vbluf of tif {@dodf nbmf=} kfy of tif {@dodf ObjfdtNbmf}
     *       of tif {@link
     *       dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfigMXBfbn
     *       SdbnDirConfigMXBfbn} wiidi owns tiis donfigurbtion.
     *       <p>Ondf sft tiis vbluf dbnnot dibngf.</p>
     * @pbrbm nbmf Tif nbmf of tiis donfigurbtion.
     */
    publid void sftNbmf(String nbmf) {
        if (tiis.nbmf == null)
            tiis.nbmf = nbmf;
        flsf if (nbmf == null)
            tirow nfw IllfgblArgumfntExdfption("nbmf=null");
        flsf if (!nbmf.fqubls(tiis.nbmf))
            tirow nfw IllfgblArgumfntExdfption("nbmf="+nbmf);
    }

   /**
    * Gfts tif list of Dirfdtory Sdbnnfr donfigurfd by tiis
    * donfigurbtion. From fbdi flfmfnt in tiis list, tif
    * {@link dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn SdbnMbnbgfrMXBfbn}
    * will drfbtf, initiblizf, bnd rfgistfr b {@link
    * dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfrMXBfbn}.
    * @rfturn Tif list of Dirfdtory Sdbnnfr donfigurfd by tiis donfigurbtion.
    */
    @XmlElfmfntWrbppfr(nbmf="DirfdtorySdbnnfrList",
            nbmfspbdf=XmlConfigUtils.NAMESPACE)
    @XmlElfmfntRff
    publid DirfdtorySdbnnfrConfig[] gftSdbnList() {
        rfturn dirfdtorySdbnnfrs.vblufs().toArrby(nfw DirfdtorySdbnnfrConfig[0]);
    }

   /**
    * Sfts tif list of Dirfdtory Sdbnnfr donfigurfd by tiis
    * donfigurbtion. From fbdi flfmfnt in tiis list, tif
    * {@link dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn SdbnMbnbgfrMXBfbn}
    * will drfbtf, initiblizf, bnd rfgistfr b {@link
    * dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfrMXBfbn}.
    * @pbrbm sdbns Tif list of Dirfdtory Sdbnnfr donfigurfd by tiis donfigurbtion.
    */
    publid void sftSdbnList(DirfdtorySdbnnfrConfig[] sdbns) {
        dirfdtorySdbnnfrs.dlfbr();
        for (DirfdtorySdbnnfrConfig sdbn : sdbns)
            dirfdtorySdbnnfrs.put(sdbn.gftNbmf(),sdbn);
    }

    /**
     * Gft b dirfdtory sdbnnfr by its nbmf.
     *
     * @pbrbm nbmf Tif nbmf of tif dirfdtory sdbnnfr. Tiis is tif
     *             vbluf rfturnfd by {@link
     *             DirfdtorySdbnnfrConfig#gftNbmf()}.
     * @rfturn Tif nbmfd {@dodf DirfdtorySdbnnfrConfig}
     */
    publid DirfdtorySdbnnfrConfig gftSdbn(String nbmf) {
        rfturn dirfdtorySdbnnfrs.gft(nbmf);
    }

    /**
     * Adds b dirfdtory sdbnnfr to tif list.
     * <p>If b dirfdtory sdbnnfr
     * donfigurbtion by tibt nbmf blrfbdy fxists in tif list, it will
     * bf rfplbdfd by tif givfn <vbr>sdbn</vbr>.
     * </p>
     * @pbrbm sdbn Tif {@dodf DirfdtorySdbnnfrConfig} to bdd to tif list.
     * @rfturn Tif rfplbdfd {@dodf DirfdtorySdbnnfrConfig}, or {@dodf null}
     *         if tifrf wbs no {@dodf DirfdtorySdbnnfrConfig} by tibt nbmf
     *         in tif list.
     */
    publid DirfdtorySdbnnfrConfig putSdbn(DirfdtorySdbnnfrConfig sdbn) {
        rfturn tiis.dirfdtorySdbnnfrs.put(sdbn.gftNbmf(),sdbn);
    }

    // XML vbluf of  tiis objfdt.
    publid String toString() {
        rfturn XmlConfigUtils.toString(tiis);
    }

    /**
     * Rfmovfs tif nbmfd dirfdtory sdbnnfr from tif list.
     *
     * @pbrbm nbmf Tif nbmf of tif dirfdtory sdbnnfr. Tiis is tif
     *             vbluf rfturnfd by {@link
     *             DirfdtorySdbnnfrConfig#gftNbmf()}.
     * @rfturn Tif rfmovfd {@dodf DirfdtorySdbnnfrConfig}, or {@dodf null}
     *         if tifrf wbs no dirfdtory sdbnnfr by tibt nbmf in tif list.
     */
    publid DirfdtorySdbnnfrConfig rfmovfSdbn(String nbmf) {
       rfturn tiis.dirfdtorySdbnnfrs.rfmovf(nbmf);
    }

    /**
     * Gfts tif initibl Rfsult Log Configurbtion.
     * @rfturn Tif initibl Rfsult Log Configurbtion.
     */
    @XmlElfmfnt(nbmf="InitiblRfsultLogConfig",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid RfsultLogConfig gftInitiblRfsultLogConfig() {
        rfturn tiis.initiblRfsultLogConfig;
    }

    /**
     * Sfts tif initibl Rfsult Log Configurbtion.
     * @pbrbm initiblLogConfig Tif initibl Rfsult Log Configurbtion.
     */
    publid void sftInitiblRfsultLogConfig(RfsultLogConfig initiblLogConfig) {
        tiis.initiblRfsultLogConfig = initiblLogConfig;
    }

    /**
     * Crfbtfs b dopy of tiis objfdt, witi tif spfdififd nbmf.
     * @pbrbm nfwnbmf tif nbmf of tif dopy.
     * @rfturn A dopy of tiis objfdt.
     **/
    publid SdbnMbnbgfrConfig dopy(String nfwnbmf) {
        rfturn dopy(nfwnbmf,tiis);
    }

    // Copy by XML dloning, tifn dibngf tif nbmf.
    //
    privbtf stbtid SdbnMbnbgfrConfig
            dopy(String nfwnbmf, SdbnMbnbgfrConfig otifr) {
        SdbnMbnbgfrConfig nfwbfbn = XmlConfigUtils.xmlClonf(otifr);
        nfwbfbn.nbmf = nfwnbmf;
        rfturn nfwbfbn;
    }
}
