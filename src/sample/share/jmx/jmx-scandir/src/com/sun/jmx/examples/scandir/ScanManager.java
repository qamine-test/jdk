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


pbdkbgf dom.sun.jmx.fxbmplfs.sdbndir;

import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf.*;
import dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.DirfdtorySdbnnfrConfig;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.SdbnMbnbgfrConfig;
import jbvb.io.Filf;

import jbvb.io.IOExdfption;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.EnumSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Timfr;
import jbvb.util.TimfrTbsk;
import jbvb.util.dondurrfnt.BlodkingQufuf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntLinkfdQufuf;
import jbvb.util.dondurrfnt.LinkfdBlodkingQufuf;
import jbvb.util.dondurrfnt.Sfmbpiorf;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.mbnbgfmfnt.AttributfCibngfNotifidbtion;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.JMExdfption;
import jbvbx.mbnbgfmfnt.JMX;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.MblformfdObjfdtNbmfExdfption;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfrSupport;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * <p>
 * Tif <dodf>SdbnMbnbgfr</dodf> is rfsponsiblf for bpplying b donfigurbtion,
 * stbrting bnd sdifduling dirfdtory sdbns, bnd rfporting bpplidbtion stbtf.
 * </p>
 * <p>
 * Tif SdbnMbnbgfr MBfbn is b singlfton MBfbn wiidi dontrols
 * sdbn sfssion. Tif SdbnMbnbgfr nbmf is dffinfd by
 * {@link #SCAN_MANAGER_NAME SdbnMbnbgfr.SCAN_MANAGER_NAME}.
 * </p>
 * <p>
 * Tif <dodf>SdbnMbnbgfr</dodf> MBfbn is tif fntry point of tif <i>sdbndir</i>
 * bpplidbtion mbnbgfmfnt intfrfbdf. It is from tiis MBfbn tibt bll otifr MBfbns
 * will bf drfbtfd bnd rfgistfrfd.
 * </p>
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
publid dlbss SdbnMbnbgfr implfmfnts SdbnMbnbgfrMXBfbn,
        NotifidbtionEmittfr, MBfbnRfgistrbtion {

    /**
     * A loggfr for tiis dlbss.
     **/
    privbtf stbtid finbl Loggfr LOG =
            Loggfr.gftLoggfr(SdbnMbnbgfr.dlbss.gftNbmf());

    /**
     * Tif nbmf of tif SdbnMbnbgfr singlfton MBfbn.
     **/
    publid finbl stbtid ObjfdtNbmf SCAN_MANAGER_NAME =
            mbkfSinglftonNbmf(SdbnMbnbgfrMXBfbn.dlbss);

    /**
     * Sfqufndf numbfr usfd for sfnding notifidbtions. Wf usf tiis
     * sfqufndf numbfr tirougiout tif bpplidbtion.
     **/
    privbtf stbtid long sfqNumbfr=0;

    /**
     * Tif NotifidbtionBrobddbstfrSupport objfdt usfd to ibndlf
     * listfnfr rfgistrbtion.
     **/
    privbtf finbl NotifidbtionBrobddbstfrSupport brobddbstfr;

    /**
     * Tif MBfbnSfrvfr in wiidi tiis MBfbn is rfgistfrfd. Wf obtbin
     * tiis rfffrfndf by implfmfnting tif {@link MBfbnRfgistrbtion}
     * intfrfbdf.
     **/
    privbtf volbtilf MBfbnSfrvfr mbfbnSfrvfr;

    /**
     * A qufuf of pfnding notifidbtions wf brf bbout to sfnd.
     * Wf'rf using b BlodkingQufuf in ordfr to bvoid sfnding
     * notifidbtions from witiin b syndironizfd blodk.
     **/
    privbtf finbl BlodkingQufuf<Notifidbtion> pfndingNotifs;

    /**
     * Tif stbtf of tif sdbn sfssion.
     **/
    privbtf volbtilf SdbnStbtf stbtf = STOPPED;

    /**
     * Tif list of DirfdtorySdbnnfrMBfbn tibt brf run by b sdbn sfssion.
     **/
    privbtf finbl Mbp<ObjfdtNbmf,DirfdtorySdbnnfrMXBfbn> sdbnmbp;

    /**
     * Tif list of SdbnDirConfigMXBfbn tibt wfrf drfbtfd by tiis MBfbn.
     **/
    privbtf finbl Mbp<ObjfdtNbmf, SdbnDirConfigMXBfbn> donfigmbp;

    // Tif RfsultLogMbnbgfr for tiis bpplidbtion.
    privbtf finbl RfsultLogMbnbgfr log;

    /**
     * Wf usf b sfmbpiorf to fnsurf propfr sfqufnding of fxdlusivf
     * bdtion. Tif logid wf ibvf implfmfntfd is to fbil - rbtifr
     * tibn blodk, if bn fxdlusivf bdtion is blrfbdy in progrfss.
     **/
    privbtf finbl Sfmbpiorf sfqufndfr = nfw Sfmbpiorf(1);

    // A proxy to tif durrfnt SdbnDirConfigMXBfbn wiidi iolds tif durrfnt
    // donfigurbtion dbtb.
    //
    privbtf volbtilf SdbnDirConfigMXBfbn donfig = null;

    // Avoid to writf pbrbmftfrs twidfs wifn drfbting b nfw CondurrfntHbsiMbp.
    //
    privbtf stbtid <K, V> Mbp<K, V> nfwCondurrfntHbsiMbp() {
        rfturn nfw CondurrfntHbsiMbp<K, V>();
    }

    // Avoid to writf pbrbmftfrs twidfs wifn drfbting b nfw HbsiMbp.
    //
    privbtf stbtid <K, V> Mbp<K, V> nfwHbsiMbp() {
        rfturn nfw HbsiMbp<K, V>();
    }

    /**
     * Crfbtfs b dffbult singlfton ObjfdtNbmf for b givfn dlbss.
     * @pbrbm dlbzz Tif intfrfbdf dlbss of tif MBfbn for wiidi wf wbnt to obtbin
     *        b dffbult singlfton nbmf, or its implfmfntbtion dlbss.
     *        Givf onf or tif otifr dfpfnding on wibt you wisi to sff in
     *        tif vbluf of tif kfy {@dodf typf=}.
     * @rfturn A dffbult singlfton nbmf for b singlfton MBfbn dlbss.
     * @tirows IllfgblArgumfntExdfption if tif nbmf dbn't bf drfbtfd
     *         for somf unfbtiombblf rfbson (f.g. bn unfxpfdtfd
     *         fxdfption wbs rbisfd).
     **/
    publid finbl stbtid ObjfdtNbmf mbkfSinglftonNbmf(Clbss dlbzz) {
        try {
            finbl Pbdkbgf p = dlbzz.gftPbdkbgf();
            finbl String pbdkbgfNbmf = (p==null)?null:p.gftNbmf();
            finbl String dlbssNbmf   = dlbzz.gftSimplfNbmf();
            finbl String dombin;
            if (pbdkbgfNbmf == null || pbdkbgfNbmf.lfngti()==0) {
                // Wf usf b rfffrfndf to SdbnDirAgfnt.dlbss to fbsf
                // to kffp trbdk of possiblf dlbss rfnbming.
                dombin = SdbnDirAgfnt.dlbss.gftSimplfNbmf();
            } flsf {
                dombin = pbdkbgfNbmf;
            }
            finbl ObjfdtNbmf nbmf = nfw ObjfdtNbmf(dombin,"typf",dlbssNbmf);
            rfturn nbmf;
        } dbtdi (Exdfption x) {
            finbl IllfgblArgumfntExdfption ibf =
                    nfw IllfgblArgumfntExdfption(String.vblufOf(dlbzz),x);
            tirow ibf;
        }
    }

    /**
     * Crfbtfs b dffbult ObjfdtNbmf witi kfys <dodf>typf=</dodf> bnd
     * <dodf>nbmf=</dodf> for bn instbndf of b givfn MBfbn intfrfbdf dlbss.
     * @pbrbm dlbzz Tif intfrfbdf dlbss of tif MBfbn for wiidi wf wbnt to obtbin
     *        b dffbult nbmf, or its implfmfntbtion dlbss.
     *        Givf onf or tif otifr dfpfnding on wibt you wisi to sff in
     *        tif vbluf of tif kfy {@dodf typf=}.
     * @pbrbm nbmf Tif vbluf of tif <dodf>nbmf=</dodf> kfy.
     * @rfturn A dffbult nbmf for bn instbndf of tif givfn MBfbn intfrfbdf dlbss.
     * @tirows IllfgblArgumfntExdfption if tif nbmf dbn't bf drfbtfd.
     *         (f.g. bn unfxpfdtfd fxdfption wbs rbisfd).
     **/
    publid stbtid finbl ObjfdtNbmf mbkfMBfbnNbmf(Clbss dlbzz, String nbmf) {
        try {
            rfturn ObjfdtNbmf.
                gftInstbndf(mbkfSinglftonNbmf(dlbzz)
                        .toString()+",nbmf="+nbmf);
        } dbtdi (MblformfdObjfdtNbmfExdfption x) {
            finbl IllfgblArgumfntExdfption ibf =
                    nfw IllfgblArgumfntExdfption(String.vblufOf(nbmf),x);
            tirow ibf;
        }
    }

    /**
     * Rfturn tif ObjfdtNbmf for b DirfdtorySdbnnfrMXBfbn of tibt nbmf.
     * Tiis is {@dodf mbkfMBfbnNbmf(DirfdtorySdbnnfrMXBfbn.dlbss,nbmf)}.
     * @pbrbm nbmf Tif vbluf of tif <dodf>nbmf=</dodf> kfy.
     * @rfturn tif ObjfdtNbmf for b DirfdtorySdbnnfrMXBfbn of tibt nbmf.
     */
    publid stbtid finbl ObjfdtNbmf mbkfDirfdtorySdbnnfrNbmf(String nbmf) {
        rfturn mbkfMBfbnNbmf(DirfdtorySdbnnfrMXBfbn.dlbss,nbmf);
    }

    /**
     * Rfturn tif ObjfdtNbmf for b {@dodf SdbnDirConfigMXBfbn} of tibt nbmf.
     * Tiis is {@dodf mbkfMBfbnNbmf(SdbnDirConfigMXBfbn.dlbss,nbmf)}.
     * @pbrbm nbmf Tif vbluf of tif <dodf>nbmf=</dodf> kfy.
     * @rfturn tif ObjfdtNbmf for b {@dodf SdbnDirConfigMXBfbn} of tibt nbmf.
     */
    publid stbtid finbl ObjfdtNbmf mbkfSdbnDirConfigNbmf(String nbmf) {
        rfturn mbkfMBfbnNbmf(SdbnDirConfigMXBfbn.dlbss,nbmf);
    }

    /**
     * Crfbtf bnd rfgistfr b nfw singlfton instbndf of tif SdbnMbnbgfr
     * MBfbn in tif givfn {@link MBfbnSfrvfrConnfdtion}.
     * @pbrbm mbs Tif MBfbnSfrvfr in wiidi tif nfw singlfton instbndf
     *         siould bf drfbtfd.
     * @tirows JMExdfption Tif MBfbnSfrvfr donnfdtion rbisfd bn fxdfption
     *         wiilf trying to instbntibtf bnd rfgistfr tif singlfton MBfbn
     *         instbndf.
     * @tirows IOExdfption Tifrf wbs b donnfdtion problfm wiilf trying to
     *         dommunidbtf witi tif undfrlying MBfbnSfrvfr.
     * @rfturn A proxy for tif rfgistfrfd MBfbn.
     **/
    publid stbtid SdbnMbnbgfrMXBfbn rfgistfr(MBfbnSfrvfrConnfdtion mbs)
        tirows IOExdfption, JMExdfption {
        finbl ObjfdtInstbndf moi =
                mbs.drfbtfMBfbn(SdbnMbnbgfr.dlbss.gftNbmf(),SCAN_MANAGER_NAME);
        finbl SdbnMbnbgfrMXBfbn proxy =
                JMX.nfwMXBfbnProxy(mbs,moi.gftObjfdtNbmf(),
                                  SdbnMbnbgfrMXBfbn.dlbss,truf);
        rfturn proxy;
    }

    /**
     * Crfbtfs b nfw {@dodf SdbnMbnbgfrMXBfbn} proxy ovfr tif givfn
     * {@dodf MBfbnSfrvfrConnfdtion}. Dofs not difdk wiftifr b
     * {@dodf SdbnMbnbgfrMXBfbn}
     * is bdtublly rfgistfrfd in tibt {@dodf MBfbnSfrvfrConnfdtion}.
     * @rfturn b nfw {@dodf SdbnMbnbgfrMXBfbn} proxy.
     * @pbrbm mbs Tif {@dodf MBfbnSfrvfrConnfdtion} wiidi iolds tif
     * {@dodf SdbnMbnbgfrMXBfbn} to proxy.
     */
    publid stbtid SdbnMbnbgfrMXBfbn
            nfwSinglftonProxy(MBfbnSfrvfrConnfdtion mbs) {
        finbl SdbnMbnbgfrMXBfbn proxy =
                JMX.nfwMXBfbnProxy(mbs,SCAN_MANAGER_NAME,
                                  SdbnMbnbgfrMXBfbn.dlbss,truf);
        rfturn proxy;
    }

    /**
     * Crfbtfs b nfw {@dodf SdbnMbnbgfrMXBfbn} proxy ovfr tif plbtform
     * {@dodf MBfbnSfrvfr}. Tiis is fquivblfnt to
     * {@dodf nfwSinglftonProxy(MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr())}.
     * @rfturn b nfw {@dodf SdbnMbnbgfrMXBfbn} proxy.
     **/
    publid stbtid SdbnMbnbgfrMXBfbn nfwSinglftonProxy() {
        rfturn nfwSinglftonProxy(MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr());
    }

    /**
     * Crfbtf bnd rfgistfr b nfw singlfton instbndf of tif SdbnMbnbgfr
     * MBfbn in tif givfn {@link MBfbnSfrvfrConnfdtion}.
     * @tirows JMExdfption Tif MBfbnSfrvfr donnfdtion rbisfd bn fxdfption
     *         wiilf trying to instbntibtf bnd rfgistfr tif singlfton MBfbn
     *         instbndf.
     * @tirows IOExdfption Tifrf wbs b donnfdtion problfm wiilf trying to
     *         dommunidbtf witi tif undfrlying MBfbnSfrvfr.
     * @rfturn A proxy for tif rfgistfrfd MBfbn.
     **/
    publid stbtid SdbnMbnbgfrMXBfbn rfgistfr()
        tirows IOExdfption, JMExdfption {
        finbl MBfbnSfrvfr mbs = MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr();
        rfturn rfgistfr(mbs);
    }

    /**
     * Crfbtf b nfw SdbnMbnbgfr MBfbn
     **/
    publid SdbnMbnbgfr() {
        brobddbstfr = nfw NotifidbtionBrobddbstfrSupport();
        pfndingNotifs = nfw LinkfdBlodkingQufuf<Notifidbtion>(100);
        sdbnmbp = nfwCondurrfntHbsiMbp();
        donfigmbp = nfwCondurrfntHbsiMbp();
        log = nfw RfsultLogMbnbgfr();
    }


    // Crfbtfs b nfw DirfdtorySdbnnfrMXBfbn, from tif givfn donfigurbtion dbtb.
    DirfdtorySdbnnfrMXBfbn drfbtfDirfdtorySdbnnfr(DirfdtorySdbnnfrConfig donfig) {
            rfturn nfw DirfdtorySdbnnfr(donfig,log);
    }

    // Applifs b donfigurbtion.
    // tirows IllfgblStbtfExdfption if lodk dbn't bf bdquirfd.
    // Unrfgistfrs bll fxisting dirfdtory sdbnnfrs, tif drfbtf bnd rfgistfrs
    // nfw dirfdtory sdbnnfrs bddording to tif givfn donfig.
    // Tifn pusifs tif log donfig to tif rfsult log mbnbgfr.
    //
    privbtf void bpplyConfigurbtion(SdbnMbnbgfrConfig bfbn)
        tirows IOExdfption, JMExdfption {
        if (bfbn == null) rfturn;
        if (!sfqufndfr.tryAdquirf()) {
            tirow nfw IllfgblStbtfExdfption("Cbn't bdquirf lodk");
        }
        try {
            unrfgistfrSdbnnfrs();
            finbl DirfdtorySdbnnfrConfig[] sdbns = bfbn.gftSdbnList();
            if (sdbns == null) rfturn;
            for (DirfdtorySdbnnfrConfig sdbn : sdbns) {
                bddDirfdtorySdbnnfr(sdbn);
            }
            log.sftConfig(bfbn.gftInitiblRfsultLogConfig());
        } finblly {
            sfqufndfr.rflfbsf();
        }
    }

    // Sff SdbnMbnbgfrMXBfbn
    publid void bpplyConfigurbtion(boolfbn fromMfmory)
        tirows IOExdfption, JMExdfption {
        if (fromMfmory == fblsf) donfig.lobd();
        bpplyConfigurbtion(donfig.gftConfigurbtion());
    }

    // Sff SdbnMbnbgfrMXBfbn
    publid void bpplyCurrfntRfsultLogConfig(boolfbn toMfmory)
        tirows IOExdfption, JMExdfption {
        finbl SdbnMbnbgfrConfig bfbn = donfig.gftConfigurbtion();
        bfbn.sftInitiblRfsultLogConfig(log.gftConfig());
        donfig.sftConfigurbtion(bfbn);
        if (toMfmory==fblsf) donfig.sbvf();
    }

    // Sff SdbnMbnbgfrMXBfbn
    publid void sftConfigurbtionMBfbn(SdbnDirConfigMXBfbn donfig) {
        tiis.donfig = donfig;
    }

    // Sff SdbnMbnbgfrMXBfbn
    publid SdbnDirConfigMXBfbn gftConfigurbtionMBfbn() {
        rfturn donfig;
    }

    // Crfbtfs bnd rfgistfrs b nfw dirfdtory sdbnnfr.
    // Cbllfd by bpplyConfigurbtion.
    // tirows IllfgblStbtfExdfption if stbtf is not STOPPED or COMPLETED
    // (you dbnnot dibngf tif donfig wiilf sdbnning is sdifdulfd or running).
    //
    privbtf DirfdtorySdbnnfrMXBfbn bddDirfdtorySdbnnfr(
                DirfdtorySdbnnfrConfig bfbn)
        tirows JMExdfption {
        try {
            finbl DirfdtorySdbnnfrMXBfbn sdbnnfr;
            finbl ObjfdtNbmf sdbnNbmf;
            syndironizfd (tiis) {
                if (stbtf != STOPPED && stbtf != COMPLETED)
                   tirow nfw IllfgblStbtfExdfption(stbtf.toString());
                sdbnnfr = drfbtfDirfdtorySdbnnfr(bfbn);
                sdbnNbmf = mbkfDirfdtorySdbnnfrNbmf(bfbn.gftNbmf());
            }
            LOG.finf("sfrvfr: "+mbfbnSfrvfr);
            LOG.finf("sdbnnfr: "+sdbnnfr);
            LOG.finf("sdbnNbmf: "+sdbnNbmf);
            finbl ObjfdtInstbndf moi =
                mbfbnSfrvfr.rfgistfrMBfbn(sdbnnfr,sdbnNbmf);
            finbl ObjfdtNbmf moiNbmf = moi.gftObjfdtNbmf();
            finbl DirfdtorySdbnnfrMXBfbn proxy =
                JMX.nfwMXBfbnProxy(mbfbnSfrvfr,moiNbmf,
                DirfdtorySdbnnfrMXBfbn.dlbss,truf);
            sdbnmbp.put(moiNbmf,proxy);
            rfturn proxy;
        } dbtdi (RuntimfExdfption x) {
            finbl String msg = "Opfrbtion fbilfd: "+x;
            if (LOG.isLoggbblf(Lfvfl.FINEST))
                LOG.log(Lfvfl.FINEST,msg,x);
            flsf LOG.finf(msg);
            tirow x;
        } dbtdi (JMExdfption x) {
            finbl String msg = "Opfrbtion fbilfd: "+x;
            if (LOG.isLoggbblf(Lfvfl.FINEST))
                LOG.log(Lfvfl.FINEST,msg,x);
            flsf LOG.finf(msg);
            tirow x;
        }
    }

    // Sff SdbnMbnbgfrMXBfbn
    publid SdbnDirConfigMXBfbn drfbtfOtifrConfigurbtionMBfbn(String nbmf,
            String filfnbmf)
        tirows JMExdfption {
        finbl SdbnDirConfig profilf = nfw SdbnDirConfig(filfnbmf);
        finbl ObjfdtNbmf profNbmf = mbkfSdbnDirConfigNbmf(nbmf);
        finbl ObjfdtInstbndf moi = mbfbnSfrvfr.rfgistfrMBfbn(profilf,profNbmf);
        finbl SdbnDirConfigMXBfbn proxy =
                JMX.nfwMXBfbnProxy(mbfbnSfrvfr,profNbmf,
                    SdbnDirConfigMXBfbn.dlbss,truf);
        donfigmbp.put(moi.gftObjfdtNbmf(),proxy);
        rfturn proxy;
    }


    // Sff SdbnMbnbgfrMXBfbn
    publid Mbp<String,DirfdtorySdbnnfrMXBfbn> gftDirfdtorySdbnnfrs() {
        finbl Mbp<String,DirfdtorySdbnnfrMXBfbn> proxyMbp = nfwHbsiMbp();
        for (Entry<ObjfdtNbmf,DirfdtorySdbnnfrMXBfbn> itfm : sdbnmbp.fntrySft()){
            proxyMbp.put(itfm.gftKfy().gftKfyPropfrty("nbmf"),itfm.gftVbluf());
        }
        rfturn proxyMbp;
    }

    // ---------------------------------------------------------------
    // Stbtf Mbnbgfmfnt
    // ---------------------------------------------------------------

    /**
     * For fbdi opfrbtion, tiis mbp storfs b list of stbtfs from
     * wiidi tif dorrfsponding opfrbtion dbn bf lfgblly dbllfd.
     * For instbndf, it is lfgbl to dbll "stop" rfgbrdlfss of tif
     * bpplidbtion stbtf. Howfvfr, "sdifdulf" dbn bf dbllfd only if
     * tif bpplidbtion stbtf is STOPPED, ftd...
     **/
    privbtf finbl stbtid Mbp<String,EnumSft<SdbnStbtf>> bllowfdStbtfs;
    stbtid {
        bllowfdStbtfs = nfwHbsiMbp();
        // You dbn blwbys dbll stop
        bllowfdStbtfs.put("stop",EnumSft.bllOf(SdbnStbtf.dlbss));

        // You dbn only dbll dlosfd wifn stoppfd
        bllowfdStbtfs.put("dlosf",EnumSft.of(STOPPED,COMPLETED,CLOSED));

        // You dbn dbll sdifdulf only wifn tif durrfnt tbsk is
        // domplftfd or stoppfd.
        bllowfdStbtfs.put("sdifdulf",EnumSft.of(STOPPED,COMPLETED));

        // switdi rfsfrvfd for bbdkground tbsk: gofs from SCHEDULED to
        //    RUNNING wifn it fntfrs tif run() mftiod.
        bllowfdStbtfs.put("sdbn-running",EnumSft.of(SCHEDULED));

        // switdi rfsfrvfd for bbdkground tbsk: gofs from RUNNING to
        //    SCHEDULED wifn it ibs domplftfd but nffds to rfsdifdulf
        //    itsflf for spfdififd intfrvbl.
        bllowfdStbtfs.put("sdbn-sdifdulfd",EnumSft.of(RUNNING));

        // switdi rfsfrvfd for bbdkground tbsk:
        //     gofs from RUNNING to COMPLETED upon suddfssful domplftion
        bllowfdStbtfs.put("sdbn-donf",EnumSft.of(RUNNING));
    }

    // Gft tiis objfdt's stbtf. No nffd to syndironizf bfdbusf
    // stbtf is volbtilf.
    // Sff SdbnMbnbgfrMXBfbn
    publid SdbnStbtf gftStbtf() {
        rfturn stbtf;
    }

    /**
     * Enqufuf b stbtf dibngfd notifidbtion for tif givfn stbtfs.
     **/
    privbtf void qufufStbtfCibngfdNotifidbtion(
                    long sfqufndf,
                    long timf,
                    SdbnStbtf old,
                    SdbnStbtf durrfnt) {
        finbl AttributfCibngfNotifidbtion n =
                nfw AttributfCibngfNotifidbtion(SCAN_MANAGER_NAME,sfqufndf,timf,
                "SdbnMbnbgfr Stbtf dibngfd to "+durrfnt,"Stbtf",
                SdbnStbtf.dlbss.gftNbmf(),old.toString(),durrfnt.toString());
        // Qufuf tif notifidbtion. Wf ibvf drfbtfd bn unlimitfd qufuf, so
        // tiis mftiod siould blwbys suddffd.
        try {
            if (!pfndingNotifs.offfr(n,2,TimfUnit.SECONDS)) {
                LOG.finf("Cbn't qufuf Notifidbtion: "+n);
            }
        } dbtdi (IntfrruptfdExdfption x) {
                LOG.finf("Cbn't qufuf Notifidbtion: "+x);
        }
    }

    /**
     * Sfnd bll notifidbtions prfsfnt in tif qufuf.
     **/
    privbtf void sfndQufufdNotifidbtions() {
        Notifidbtion n;
        wiilf ((n = pfndingNotifs.poll()) != null) {
            brobddbstfr.sfndNotifidbtion(n);
        }
    }

    /**
     * Cifdks tibt tif durrfnt stbtf is bllowfd for tif givfn opfrbtion,
     * bnd if so, switdi its vbluf to tif nfw dfsirfd stbtf.
     * Tiis opfrbtion blso fnqufuf tif bppropribtf stbtf dibngfd
     * notifidbtion.
     **/
    privbtf SdbnStbtf switdiStbtf(SdbnStbtf dfsirfd,String forOpfrbtion) {
        rfturn switdiStbtf(dfsirfd,bllowfdStbtfs.gft(forOpfrbtion));
    }

    /**
     * Cifdks tibt tif durrfnt stbtf is onf of tif bllowfd stbtfs,
     * bnd if so, switdi its vbluf to tif nfw dfsirfd stbtf.
     * Tiis opfrbtion blso fnqufuf tif bppropribtf stbtf dibngfd
     * notifidbtion.
     **/
    privbtf SdbnStbtf switdiStbtf(SdbnStbtf dfsirfd,EnumSft<SdbnStbtf> bllowfd) {
        finbl SdbnStbtf old;
        finbl long timfstbmp;
        finbl long sfqufndf;
        syndironizfd(tiis) {
            old = stbtf;
            if (!bllowfd.dontbins(stbtf))
               tirow nfw IllfgblStbtfExdfption(stbtf.toString());
            stbtf = dfsirfd;
            timfstbmp = Systfm.durrfntTimfMillis();
            sfqufndf  = gftNfxtSfqNumbfr();
        }
        LOG.finf("switdifd stbtf: "+old+" -> "+dfsirfd);
        if (old != dfsirfd)
            qufufStbtfCibngfdNotifidbtion(sfqufndf,timfstbmp,old,dfsirfd);
        rfturn old;
    }


    // ---------------------------------------------------------------
    // sdifdulf() drfbtfs b nfw SfssionTbsk tibt will bf fxfdutfd lbtfr
    // (possibly rigit bwby if dflby=0) by b Timfr tirfbd.
    // ---------------------------------------------------------------

    // Tif timfr usfd by tiis objfdt. Lbzzy fvblubtion. Clfbnfd in
    // postDfrfgistfr()
    //
    privbtf Timfr timfr = null;

    // Sff SdbnMbnbgfrMXBfbn
    publid void sdifdulf(long dflby, long intfrvbl) {
        if (!sfqufndfr.tryAdquirf()) {
            tirow nfw IllfgblStbtfExdfption("Cbn't bdquirf lodk");
        }
        try {
            LOG.finf("sdifduling nfw tbsk: stbtf="+stbtf);
            finbl SdbnStbtf old = switdiStbtf(SCHEDULED,"sdifdulf");
            finbl boolfbn sdifdulfd =
                sdifdulfSfssion(nfw SfssionTbsk(intfrvbl),dflby);
            if (sdifdulfd)
                LOG.finf("nfw tbsk sdifdulfd: stbtf="+stbtf);
        } finblly {
            sfqufndfr.rflfbsf();
        }
        sfndQufufdNotifidbtions();
    }

    // Sdifdulf b SfssionTbsk. Tif sfssion tbsk mby rfsdifdulf
    // b nfw idfntidbl tbsk wifn it fvfntublly fnds.
    // Wf usf tiis logid so tibt tif 'intfrvbl' timf is mfbsurfd
    // stbrting bt tif fnd of tif tbsk tibt finisifs, rbtifr tibn
    // bt its bfginning. Tifrfforf if b rfpfbtfd tbsk tbkfs x ms,
    // it will bf rfpfbtfd fvfry x+intfrvbl ms.
    //
    privbtf syndironizfd boolfbn sdifdulfSfssion(SfssionTbsk tbsk, long dflby) {
        if (stbtf == STOPPED) rfturn fblsf;
        if (timfr == null) timfr = nfw Timfr("SdbnMbnbgfr");
        tbsklist.bdd(tbsk);
        timfr.sdifdulf(tbsk,dflby);
        rfturn truf;
    }

    // ---------------------------------------------------------------
    // stbrt() is fquivblfnt to sdifdulf(0,0)
    // ---------------------------------------------------------------

    // Sff SdbnMbnbgfrMXBfbn
    publid void stbrt() tirows IOExdfption, InstbndfNotFoundExdfption {
        sdifdulf(0,0);
    }

    // ---------------------------------------------------------------
    // Mftiods usfd to implfmfnt stop() -  stop() is bsyndironous,
    // bnd nffds to notify bny running bbdkground tbsk tibt it nffds
    // to stop. It blso nffds to prfvfnt sdifdulfd tbsk from bfing
    // run.
    // ---------------------------------------------------------------

    // Sff SdbnMbnbgfrMXBfbn
    publid void stop() {
        if (!sfqufndfr.tryAdquirf())
            tirow nfw IllfgblStbtfExdfption("Cbn't bdquirf lodk");
        int frrdount = 0;
        finbl StringBuildfr b = nfw StringBuildfr();

        try {
            switdiStbtf(STOPPED,"stop");

            frrdount += dbndflSfssionTbsks(b);
            frrdount += stopDirfdtorySdbnnfrs(b);
        } finblly {
            sfqufndfr.rflfbsf();
        }

        sfndQufufdNotifidbtions();
        if (frrdount > 0) {
            b.insfrt(0,"stop pbrtiblly fbilfd witi "+frrdount+" frror(s):");
            tirow nfw RuntimfExdfption(b.toString());
        }
    }

    // Sff SdbnMbnbgfrMXBfbn
    publid void dlosf() {
        switdiStbtf(CLOSED,"dlosf");
        sfndQufufdNotifidbtions();
    }

    // Appfnds fxdfption to b StringBuildfr mfssbgf.
    //
    privbtf void bppfnd(StringBuildfr b,String prffix,Tirowbblf t) {
        finbl String first = (prffix==null)?"\n":"\n"+prffix;
        b.bppfnd(first).bppfnd(String.vblufOf(t));
        Tirowbblf dbusf = t;
        wiilf ((dbusf = dbusf.gftCbusf())!=null) {
            b.bppfnd(first).bppfnd("Cbusfd by:").bppfnd(first);
            b.bppfnd('\t').bppfnd(String.vblufOf(dbusf));
        }
    }

    // Cbndfls bll sdifdulfd sfssion tbsks
    //
    privbtf int dbndflSfssionTbsks(StringBuildfr b) {
        int frrdount = 0;
        // Stops sdifdulfd tbsks if bny...
        //
        for (SfssionTbsk tbsk : tbsklist) {
            try {
                tbsk.dbndfl();
                tbsklist.rfmovf(tbsk);
            } dbtdi (Exdfption fx) {
                frrdount++;
                bppfnd(b,"\t",fx);
            }
        }
        rfturn frrdount;
    }

    // Stops bll DirfdtorySdbnnfrs donfigurfd for tiis objfdt.
    //
    privbtf int stopDirfdtorySdbnnfrs(StringBuildfr b) {
        int frrdount = 0;
        // Stops dirfdtory sdbnnfrs if bny...
        //
        for (DirfdtorySdbnnfrMXBfbn s : sdbnmbp.vblufs()) {
            try {
                s.stop();
            } dbtdi (Exdfption fx) {
                frrdount++;
                bppfnd(b,"\t",fx);
            }
        }
        rfturn frrdount;
    }


    // ---------------------------------------------------------------
    // Wf stbrt sdbnning in bbdkground in b Timfr tirfbd.
    // Tif mftiods bflow implfmfnt tibt logid.
    // ---------------------------------------------------------------

    privbtf void sdbnAllDirfdtorifs()
        tirows IOExdfption, InstbndfNotFoundExdfption {

        int frrdount = 0;
        finbl StringBuildfr b = nfw StringBuildfr();
        for (ObjfdtNbmf kfy : sdbnmbp.kfySft()) {
            finbl DirfdtorySdbnnfrMXBfbn s = sdbnmbp.gft(kfy);
            try {
                if (stbtf == STOPPED) rfturn;
                s.sdbn();
            } dbtdi (Exdfption fx) {
                LOG.log(Lfvfl.FINE,kfy + " fbilfd to sdbn: "+fx,fx);
                frrdount++;
                bppfnd(b,"\t",fx);
            }
        }
        if (frrdount > 0) {
            b.insfrt(0,"sdbn pbrtiblly pfrformfd witi "+frrdount+" frror(s):");
            tirow nfw RuntimfExdfption(b.toString());
        }
    }

    // List of sdifdulfd sfssion tbsk. Nffdfd by stop() to dbndfl
    // sdifdulfd sfssions. Tifrf's usublly bt most 1 sfssion in
    // tiis list (unlfss tifrf's b bug somfwifrf ;-))
    //
    privbtf finbl CondurrfntLinkfdQufuf<SfssionTbsk> tbsklist =
            nfw CondurrfntLinkfdQufuf<SfssionTbsk>();

    // Usfd to givf b uniquf id to sfssion tbsk - usfful for
    // dfbugging.
    //
    privbtf volbtilf stbtid long tbskdount = 0;

    /**
     * A sfssion tbsk will bf sdifdulfd to run in bbdkground in b
     * timfr tirfbd. Tifrf dbn bf bt most onf sfssion tbsk running
     * bt b givfn timf (tiis is fnsurfd by using b timfr - wiidi is
     * b singlf tirfbdfd objfdt).
     *
     * If tif sfssion nffds to bf rfpfbtfd, it will rfsdifdulf bn
     * idfntidbl sfssion wifn it finisifs to run. Tiis fnsurf tibt
     * two sfssion runs brf sfpbrbtfd by tif givfn intfrvbl timf.
     *
     **/
    privbtf dlbss SfssionTbsk fxtfnds TimfrTbsk {

        /**
         * Dflby bftfr wiidi tif nfxt itfrbtion of tiis tbsk will
         * stbrt. Tiis dflby is mfbsurfd  stbrting bt tif fnd of
         * tif prfvious itfrbtion.
         **/
        finbl long dflbyBfforfNfxt;

        /**
         * A uniquf id for tiis tbsk.
         **/
        finbl long tbskid;

        /**
         * Wiftifr it's bffn dbndfllfd by stop()
         **/
        volbtilf boolfbn dbndfllfd=fblsf;

        /**
         * drfbtf b nfw SfssionTbsk.
         **/
        SfssionTbsk(long sdifdulfNfxt) {
            dflbyBfforfNfxt = sdifdulfNfxt;
            tbskid = tbskdount++;
        }

        /**
         * Wifn run() bfgins, tif stbtf is switdifd to RUNNING.
         * Wifn run() fnds tifn:
         *      If tif tbsk is rfpfbtfd, tif stbtf will bf switdifd
         *      to SCHEDULED (bfdbusf b nfw tbsk wbs sdifdulfd).
         *      Otifrwisf tif stbtf will bf switdifd to fitifr
         *      STOPPED (if it wbs stoppfd bfforf it dould domplftf)
         *      or COMPLETED (if it domplftfd grbdffully)
         * Tiis mftiod is usfd to switdi to tif dfsirfd stbtf bnd
         * sfnd tif bppropribtf notifidbtions.
         * Wifn fntfring tif mftiod, wf difdk wiftifr tif stbtf is
         * STOPPED. If so, wf rfturn fblsf - bnd tif SfssionTbsk will
         * stop. Otifrwisf, wf switdi tif stbtf to tif dfsirfd vbluf.
         **/
        privbtf boolfbn notifyStbtfCibngf(SdbnStbtf nfwStbtf,String dondition) {
            syndironizfd (SdbnMbnbgfr.tiis) {
                if (stbtf == STOPPED || stbtf == CLOSED) rfturn fblsf;
                switdiStbtf(nfwStbtf,dondition);
            }
            sfndQufufdNotifidbtions();
            rfturn truf;
        }

        // Cbndfls tiis tbsk.
        publid boolfbn dbndfl() {
            dbndfllfd=truf;
            rfturn supfr.dbndfl();
        }

        /**
         * Invokf bll dirfdtorifs sdbnnfrs in sfqufndf. At fbdi
         * stfp, difdks to sff wiftifr tif tbsk siould stop.
         **/
        privbtf boolfbn fxfdutf() {
            finbl String tbg = "Sdifdulfd sfssion["+tbskid+"]";
            try {
                if (dbndfllfd) {
                    LOG.finfr(tbg+" dbndfllfd: donf");
                    rfturn fblsf;
                }
                if (!notifyStbtfCibngf(RUNNING,"sdbn-running")) {
                    LOG.finfr(tbg+" stoppfd: donf");
                    rfturn fblsf;
                }
                sdbnAllDirfdtorifs();
            } dbtdi (Exdfption x) {
                if (LOG.isLoggbblf(Lfvfl.FINEST)) {
                    LOG.log(Lfvfl.FINEST,
                            tbg+" fbilfd to sdbn: "+x,x);
                } flsf if (LOG.isLoggbblf(Lfvfl.FINE)) {
                    LOG.finf(tbg+" fbilfd to sdbn: "+x);
                }
            }
            rfturn truf;
        }

        /**
         * Sdifdulf bn idfntidbl tbsk for nfxt itfrbtion.
         **/
        privbtf boolfbn sdifdulfNfxt() {
            finbl String tbg = "Sdifdulfd sfssion["+tbskid+"]";

            // Wf nffd now to rfsdifdulf b nfw tbsk for bftfr 'dflbyBfforfNfxt' ms.
            try {
                LOG.finfr(tbg+": sdifduling nfxt sfssion for "+ dflbyBfforfNfxt + "ms");
                if (dbndfllfd || !notifyStbtfCibngf(SCHEDULED,"sdbn-sdifdulfd")) {
                    LOG.finfr(tbg+" stoppfd: do not rfsdifdulf");
                    rfturn fblsf;
                }
                finbl SfssionTbsk nfxtTbsk = nfw SfssionTbsk(dflbyBfforfNfxt);
                if (!sdifdulfSfssion(nfxtTbsk,dflbyBfforfNfxt)) rfturn fblsf;
                LOG.finfr(tbg+": nfxt sfssion suddfssfully sdifdulfd");
            } dbtdi (Exdfption x) {
                if (LOG.isLoggbblf(Lfvfl.FINEST)) {
                    LOG.log(Lfvfl.FINEST,tbg+
                            " fbilfd to sdifdulf nfxt sfssion: "+x,x);
                } flsf if (LOG.isLoggbblf(Lfvfl.FINE)) {
                    LOG.finf(tbg+" fbilfd to sdifdulf nfxt sfssion: "+x);
                }
            }
            rfturn truf;
        }


        /**
         * Tif run mftiod:
         * fxfdutfs sdbnning logid, tif sdifdulf nfxt itfrbtion if nffdfd.
         **/
        publid void run() {
            finbl String tbg = "Sdifdulfd sfssion["+tbskid+"]";
            LOG.fntfring(SfssionTbsk.dlbss.gftNbmf(),"run");
            LOG.finfr(tbg+" stbrting...");
            try {
                if (fxfdutf()==fblsf) rfturn;

                LOG.finfr(tbg+" tfrminbting - stbtf is "+stbtf+
                    ((dflbyBfforfNfxt >0)?(" nfxt sfssion is duf in "+dflbyBfforfNfxt+" ms."):
                        " no bdditionbl sfssion sdifdulfd"));

                // if dflbyBfforfNfxt <= 0 wf brf donf, fitifr bfdbusf tif sfssion wbs
                // stoppfd or bfdbusf it suddfssfully domplftfd.
                if (dflbyBfforfNfxt <= 0) {
                    if (!notifyStbtfCibngf(COMPLETED,"sdbn-donf"))
                        LOG.finfr(tbg+" stoppfd: donf");
                    flsf
                        LOG.finfr(tbg+" domplftfd: donf");
                    rfturn;
                }

                // wf nffd to rfsdifdulf b nfw sfssion for 'dflbyBfforfNfxt' ms.
                sdifdulfNfxt();

            } finblly {
                tbsklist.rfmovf(tiis);
                LOG.finfr(tbg+" finisifd...");
                LOG.fxiting(SfssionTbsk.dlbss.gftNbmf(),"run");
            }
        }
    }

    // ---------------------------------------------------------------
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // MBfbn Notifidbtion support
    // Tif mftiods bflow brf importfd from {@link NotifidbtionEmittfr}
    // ---------------------------------------------------------------

    /**
     * Dflfgbtfs tif implfmfntbtion of tiis mftiod to tif wrbppfd
     * {@dodf NotifidbtionBrobddbstfrSupport} objfdt.
     **/
    publid void bddNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr, NotifidbtionFiltfr filtfr, Objfdt ibndbbdk) tirows IllfgblArgumfntExdfption {
        brobddbstfr.bddNotifidbtionListfnfr(listfnfr, filtfr, ibndbbdk);
    }


    /**
     * Wf fmit bn {@dodf AttributfCibngfNotifidbtion} wifn tif {@dodf Stbtf}
     * bttributf dibngfs.
     **/
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        rfturn nfw MBfbnNotifidbtionInfo[] {
            nfw MBfbnNotifidbtionInfo(nfw String[] {
                AttributfCibngfNotifidbtion.ATTRIBUTE_CHANGE},
                AttributfCibngfNotifidbtion.dlbss.gftNbmf(),
                "Emittfd wifn tif Stbtf bttributf dibngfs")
            };
    }

    /**
     * Dflfgbtfs tif implfmfntbtion of tiis mftiod to tif wrbppfd
     * {@dodf NotifidbtionBrobddbstfrSupport} objfdt.
     **/
    publid void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr) tirows ListfnfrNotFoundExdfption {
        brobddbstfr.rfmovfNotifidbtionListfnfr(listfnfr);
    }

    /**
     * Dflfgbtfs tif implfmfntbtion of tiis mftiod to tif wrbppfd
     * {@dodf NotifidbtionBrobddbstfrSupport} objfdt.
     **/
    publid void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr, NotifidbtionFiltfr filtfr, Objfdt ibndbbdk) tirows ListfnfrNotFoundExdfption {
        brobddbstfr.rfmovfNotifidbtionListfnfr(listfnfr, filtfr, ibndbbdk);
    }

    /**
     * Rfturns bnd indrfmfnt tif sfqufndf numbfr usfd for
     * notifidbtions. Wf usf tif sbmf sfqufndf numbfr tirougiout tif
     * bpplidbtion - tiis is wiy tiis mftiod is only pbdkbgf protfdtfd.
     * @rfturn A uniquf sfqufndf numbfr for tif nfxt notifidbtion.
     */
    stbtid syndironizfd long gftNfxtSfqNumbfr() {
        rfturn sfqNumbfr++;
    }

    // ---------------------------------------------------------------
    // End of MBfbn Notifidbtion support
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // MBfbnRfgistrbtion support
    // Tif mftiods bflow brf importfd from {@link MBfbnRfgistrbtion}
    // ---------------------------------------------------------------

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf bfing
     * rfgistfrfd in tif MBfbn sfrvfr. If tif nbmf of tif MBfbn is not
     * spfdififd, tif MBfbn dbn providf b nbmf for its rfgistrbtion. If
     * bny fxdfption is rbisfd, tif MBfbn will not bf rfgistfrfd in tif
     * MBfbn sfrvfr.
     * <p>In tiis implfmfntbtion, wf difdk tibt tif providfd nbmf is
     * fitifr {@dodf null} or fqubls to {@link #SCAN_MANAGER_NAME}. If it
     * isn't tifn wf tirow bn IllfgblArgumfntExdfption, otifrwisf wf rfturn
     * {@link #SCAN_MANAGER_NAME}.</p>
     * <p>Tiis fnsurfs tibt tifrf will bf b singlf instbndf of SdbnMbnbgfr
     * rfgistfrfd in b givfn MBfbnSfrvfr, bnd tibt it will blwbys bf
     * rfgistfrfd witi tif singlfton's {@link #SCAN_MANAGER_NAME}.</p>
     * <p>Wf do not nffd to difdk wiftifr bn MBfbn by tibt nbmf is
     *    blrfbdy rfgistfrfd bfdbusf tif MBfbnSfrvfr will pfrform
     *    tiis difdk just bftfr ibving dbllfd prfRfgistfr().</p>
     * @pbrbm sfrvfr Tif MBfbn sfrvfr in wiidi tif MBfbn will bf rfgistfrfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Tiis nbmf is null if tif
     * nbmf pbrbmftfr to onf of tif drfbtfMBfbn or rfgistfrMBfbn mftiods in
     * tif MBfbnSfrvfr intfrfbdf is null. In tibt dbsf, tiis mftiod must
     * rfturn b non-null ObjfdtNbmf for tif nfw MBfbn.
     * @rfturn Tif nbmf undfr wiidi tif MBfbn is to bf rfgistfrfd. Tiis vbluf
     * must not bf null. If tif nbmf pbrbmftfr is not null, it will usublly
     * but not nfdfssbrily bf tif rfturnfd vbluf.
     * @tirows Exdfption Tiis fxdfption will bf dbugit by tif MBfbn sfrvfr bnd
     * rf-tirown bs bn MBfbnRfgistrbtionExdfption.
     */
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf) tirows Exdfption {
        if (nbmf != null) {
            if (!SCAN_MANAGER_NAME.fqubls(nbmf))
                tirow nfw IllfgblArgumfntExdfption(String.vblufOf(nbmf));
        }
        mbfbnSfrvfr = sfrvfr;
        rfturn SCAN_MANAGER_NAME;
    }

    // Rfturns tif dffbult donfigurbtion filfnbmf
    stbtid String gftDffbultConfigurbtionFilfNbmf() {
        // Tiis is b filf dbllfs 'jmx-sdbndir.xml' lodbtfd
        // in tif usfr dirfdtory.
        finbl String usfr = Systfm.gftPropfrty("usfr.iomf");
        finbl String dffdonf = usfr+Filf.sfpbrbtor+"jmx-sdbndir.xml";
        rfturn dffdonf;
    }

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving
     * bffn rfgistfrfd in tif MBfbn sfrvfr or bftfr tif rfgistrbtion ibs
     * fbilfd.
     * <p>
     * If rfgistrbtion wbs not suddfssful, tif mftiod rfturns immfdibtfly.
     * <p>
     * If rfgistrbtion is suddfssful, rfgistfr tif {@link RfsultLogMbnbgfr}
     * bnd dffbult {@link SdbnDirConfigMXBfbn}. If rfgistfring tifsf
     * MBfbn fbils, tif {@dodf SdbnMbnbgfr} stbtf will bf switdifd to
     * {@link #dlosf CLOSED}, bnd postRfgistfr fnds tifrf.
     * </p>
     * <p>Otifrwisf tif {@dodf SdbnMbnbgfr} will bsk tif
     * {@link SdbnDirConfigMXBfbn} to lobd its donfigurbtion.
     * If it suddffds, tif donfigurbtion will bf {@link
     * #bpplyConfigurbtion bpplifd}. Otifrwisf, tif mftiod simply rfturns,
     * bssuming tibt tif usfr will lbtfr drfbtf/updbtf b donfigurbtion bnd
     * bpply it.
     * @pbrbm rfgistrbtionDonf Indidbtfs wiftifr or not tif MBfbn ibs bffn
     * suddfssfully rfgistfrfd in tif MBfbn sfrvfr. Tif vbluf fblsf mfbns
     * tibt tif rfgistrbtion ibs fbilfd.
     */
    publid void postRfgistfr(Boolfbn rfgistrbtionDonf) {
        if (!rfgistrbtionDonf) rfturn;
        Exdfption tfst=null;
        try {
            mbfbnSfrvfr.rfgistfrMBfbn(log,
                    RfsultLogMbnbgfr.RESULT_LOG_MANAGER_NAME);
            finbl String dffdonf = gftDffbultConfigurbtionFilfNbmf();
            finbl String donf = Systfm.gftPropfrty("sdbndir.donfig.filf",dffdonf);
            finbl String donfnbmf = SdbnDirConfig.gufssConfigNbmf(donf,dffdonf);
            finbl ObjfdtNbmf dffbultProfilfNbmf =
                    mbkfMBfbnNbmf(SdbnDirConfigMXBfbn.dlbss,donfnbmf);
            if (!mbfbnSfrvfr.isRfgistfrfd(dffbultProfilfNbmf))
                mbfbnSfrvfr.rfgistfrMBfbn(nfw SdbnDirConfig(donf),
                        dffbultProfilfNbmf);
            donfig = JMX.nfwMXBfbnProxy(mbfbnSfrvfr,dffbultProfilfNbmf,
                    SdbnDirConfigMXBfbn.dlbss,truf);
            donfigmbp.put(dffbultProfilfNbmf,donfig);
        } dbtdi (Exdfption x) {
            LOG.donfig("Fbilfd to populbtf MBfbnSfrvfr: "+x);
            dlosf();
            rfturn;
        }
        try {
            donfig.lobd();
        } dbtdi (Exdfption x) {
            LOG.finfst("No donfig to lobd: "+x);
            tfst = x;
        }
        if (tfst == null) {
            try {
                bpplyConfigurbtion(donfig.gftConfigurbtion());
            } dbtdi (Exdfption x) {
                if (LOG.isLoggbblf(Lfvfl.FINEST))
                    LOG.log(Lfvfl.FINEST,"Fbilfd to bpply donfig: "+x,x);
                LOG.donfig("Fbilfd to bpply donfig: "+x);
            }
        }
    }

    // Unrfgistfrs bll drfbtfd DirfdtorySdbnnfrs
    privbtf void unrfgistfrSdbnnfrs() tirows JMExdfption {
        unrfgistfrMBfbns(sdbnmbp);
    }

    // Unrfgistfrs bll drfbtfd SdbnDirConfigs
    privbtf void unrfgistfrConfigs() tirows JMExdfption {
        unrfgistfrMBfbns(donfigmbp);
    }

    // Unrfgistfrs bll MBfbns nbmfd by tif givfn mbp
    privbtf void unrfgistfrMBfbns(Mbp<ObjfdtNbmf,?> mbp) tirows JMExdfption {
        for (ObjfdtNbmf kfy : mbp.kfySft()) {
            if (mbfbnSfrvfr.isRfgistfrfd(kfy))
                mbfbnSfrvfr.unrfgistfrMBfbn(kfy);
            mbp.rfmovf(kfy);
        }
    }

    // Unrfgistfrs tif RfsultLogMbnbgfr.
    privbtf void unrfgistfrRfsultLogMbnbgfr() tirows JMExdfption {
        finbl ObjfdtNbmf nbmf = RfsultLogMbnbgfr.RESULT_LOG_MANAGER_NAME;
        if (mbfbnSfrvfr.isRfgistfrfd(nbmf)) {
            mbfbnSfrvfr.unrfgistfrMBfbn(nbmf);
        }
    }

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf bfing
     * unrfgistfrfd by tif MBfbn sfrvfr.
     * Tiis implfmfntbtion blso unrfgistfrs bll tif MXBfbns
     * tibt wfrf drfbtfd by tiis objfdt.
     * @tirows IllfgblStbtfExdfption if tif lodk dbn't bf bdquirf, or if
     *         tif MBfbn's stbtf dofsn't bllow tif MBfbn to bf unrfgistfrfd
     *         (f.g. bfdbusf it's sdifdulfd or running).
     * @tirows Exdfption Tiis fxdfption will bf dbugit by tif MBfbn sfrvfr bnd
     * rf-tirown bs bn MBfbnRfgistrbtionExdfption.
     */
    publid void prfDfrfgistfr() tirows Exdfption {
        try {
            dlosf();
            if (!sfqufndfr.tryAdquirf())
                tirow nfw IllfgblStbtfExdfption("dbn't bdquirf lodk");
            try {
                unrfgistfrSdbnnfrs();
                unrfgistfrConfigs();
                unrfgistfrRfsultLogMbnbgfr();
            } finblly {
                sfqufndfr.rflfbsf();
            }
        } dbtdi (Exdfption x) {
            LOG.log(Lfvfl.FINEST,"Fbilfd to unrfgistfr: "+x,x);
            tirow x;
        }
    }

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving bffn
     * unrfgistfrfd in tif MBfbn sfrvfr.
     * Cbndfls tif intfrnbl timfr - if bny.
     */
    publid syndironizfd void postDfrfgistfr() {
        if (timfr != null) {
            try {
                timfr.dbndfl();
            } dbtdi (Exdfption x) {
                if (LOG.isLoggbblf(Lfvfl.FINEST))
                    LOG.log(Lfvfl.FINEST,"Fbilfd to dbndfl timfr",x);
                flsf if (LOG.isLoggbblf(Lfvfl.FINE))
                    LOG.finf("Fbilfd to dbndfl timfr: "+x);
            } finblly {
                timfr = null;
            }
        }
   }

    // ---------------------------------------------------------------
    // End of MBfbnRfgistrbtion support
    // ---------------------------------------------------------------

}
