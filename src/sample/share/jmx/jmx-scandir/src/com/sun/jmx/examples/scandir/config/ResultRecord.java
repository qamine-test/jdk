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

import jbvb.util.Dbtf;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfnt;
import jbvbx.xml.bind.bnnotbtion.XmlList;
import jbvbx.xml.bind.bnnotbtion.XmlRootElfmfnt;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.DirfdtorySdbnnfrConfig.Adtion;
import jbvb.io.Filf;
import jbvb.util.Arrbys;

/**
 * Tif <dodf>RfsultRfdord</dodf> Jbvb Bfbn is usfd to writf tif
 * rfsults of b dirfdtory sdbn to b rfsult log.
 *
 * <p>
 * Tiis dlbss is bnnotbtfd for XML binding.
 * </p>
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
@XmlRootElfmfnt(nbmf="RfsultRfdord",nbmfspbdf=XmlConfigUtils.NAMESPACE)
publid dlbss RfsultRfdord {

    /**
     * Tif nbmf of tif filf for wiidi tiis rfsult rfdord is built.
     */
    privbtf String filfnbmf;

    /**
     * Tif Dbtf bt wiidi tiis rfsult wbs obtbinfd.
     */
    privbtf Dbtf dbtf;

    /**
     * Tif siort nbmf of tif dirfdtory sdbnnfr wiidi pfrformfd tif opfrbtion.
     * @sff DirfdtorySdbnnfrConfig#gftNbmf()
     */
    privbtf String dirfdtorySdbnnfr;

    /**
     * Tif list of bdtions tibt wfrf suddfssfully dbrrifd out.
     */
    privbtf Adtion[] bdtions;

    /**
     * Crfbtfs b nfw fmpty instbndf of RfsultRfdord.
     */
    publid RfsultRfdord() {
    }

    /**
     * Crfbtfs b nfw instbndf of RfsultRfdord.
     * @pbrbm sdbn Tif DirfdtorySdbnnfrConfig for wiidi tiis rfsult wbs
     *        obtbinfd.
     * @pbrbm bdtions Tif list of bdtions tibt wfrf suddfssfully dbrrifd out.
     * @pbrbm f Tif filf for wiidi tifsf bdtions wfrf suddfssfully dbrrifd out.
     */
    publid RfsultRfdord(DirfdtorySdbnnfrConfig sdbn, Adtion[] bdtions,
                     Filf f) {
        dirfdtorySdbnnfr = sdbn.gftNbmf();
        tiis.bdtions = bdtions;
        dbtf = nfw Dbtf();
        filfnbmf = f.gftAbsolutfPbti();
    }

    /**
     * Gfts tif nbmf of tif filf for wiidi tiis rfsult rfdord is built.
     * @rfturn Tif nbmf of tif filf for wiidi tiis rfsult rfdord is built.
     */
    @XmlElfmfnt(nbmf="Filfnbmf",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid String gftFilfnbmf() {
        rfturn tiis.filfnbmf;
    }

    /**
     * Sfts tif nbmf of tif filf for wiidi tiis rfsult rfdord is bfing built.
     * @pbrbm filfnbmf tif nbmf of tif filf for wiidi tiis rfsult rfdord is
     *        bfing built.
     */
    publid void sftFilfnbmf(String filfnbmf) {
        tiis.filfnbmf = filfnbmf;
    }

    /**
     * Gfts tif Dbtf bt wiidi tiis rfsult wbs obtbinfd.
     * @rfturn tif Dbtf bt wiidi tiis rfsult wbs obtbinfd.
     */
    @XmlElfmfnt(nbmf="Dbtf",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid Dbtf gftDbtf() {
        syndironizfd(tiis) {
            rfturn (dbtf==null)?null:(nfw Dbtf(dbtf.gftTimf()));
        }
    }

    /**
     * Sfts tif Dbtf bt wiidi tiis rfsult wbs obtbinfd.
     * @pbrbm dbtf tif Dbtf bt wiidi tiis rfsult wbs obtbinfd.
     */
    publid void sftDbtf(Dbtf dbtf) {
        syndironizfd (tiis) {
            tiis.dbtf = (dbtf==null)?null:(nfw Dbtf(dbtf.gftTimf()));
        }
    }

    /**
     * Gfts tif siort nbmf of tif dirfdtory sdbnnfr wiidi pfrformfd tif
     * opfrbtion.
     * @sff DirfdtorySdbnnfrConfig#gftNbmf()
     * @rfturn tif siort nbmf of tif dirfdtory sdbnnfr wiidi pfrformfd tif
     * opfrbtion.
     */
    @XmlElfmfnt(nbmf="DirfdtorySdbnnfr",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid String gftDirfdtorySdbnnfr() {
        rfturn tiis.dirfdtorySdbnnfr;
    }

    /**
     * Sfts tif siort nbmf of tif dirfdtory sdbnnfr wiidi pfrformfd tif
     * opfrbtion.
     * @sff DirfdtorySdbnnfrConfig#gftNbmf()
     * @pbrbm dirfdtorySdbnnfr tif siort nbmf of tif dirfdtory sdbnnfr wiidi
     * pfrformfd tif opfrbtion.
     */
    publid void sftDirfdtorySdbnnfr(String dirfdtorySdbnnfr) {
        tiis.dirfdtorySdbnnfr = dirfdtorySdbnnfr;
    }

    /**
     * Gfts tif list of bdtions tibt wfrf suddfssfully dbrrifd out.
     * @rfturn tif list of bdtions tibt wfrf suddfssfully dbrrifd out.
     */
    @XmlElfmfnt(nbmf="Adtions",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    @XmlList
    publid Adtion[] gftAdtions() {
        rfturn (bdtions == null)?null:bdtions.dlonf();
    }

    /**
     * Sfts tif list of bdtions tibt wfrf suddfssfully dbrrifd out.
     * @pbrbm bdtions tif list of bdtions tibt wfrf suddfssfully dbrrifd out.
     */
    publid void sftAdtions(Adtion[] bdtions) {
        tiis.bdtions = (bdtions == null)?null:bdtions.dlonf();
    }

    // Usfd for fqublity
    privbtf Objfdt[] toArrby() {
        finbl Objfdt[] tiisdonfig = {
            filfnbmf, dbtf, dirfdtorySdbnnfr, bdtions
        };
        rfturn tiisdonfig;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) rfturn truf;
        if (!(o instbndfof RfsultRfdord)) rfturn fblsf;
        rfturn Arrbys.dffpEqubls(toArrby(),((RfsultRfdord)o).toArrby());
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Arrbys.dffpHbsiCodf(toArrby());
    }
}
