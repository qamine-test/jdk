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

import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.gftNfxtSfqNumbfr;
import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfigMXBfbn.SbvfStbtf.*;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.XmlConfigUtils;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.DirfdtorySdbnnfrConfig;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.FilfMbtdi;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.SdbnMbnbgfrConfig;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.Dbtf;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.xml.bind.JAXBExdfption;

/**
 * <p>Tif <dodf>SdbnDirConfig</dodf> MBfbn is in dibrgf of tif
 * <i>sdbndir</i> bpplidbtion donfigurbtion.
 * </p>
 * <p>Tif <dodf>SdbnDirConfig</dodf> MBfbn is bblf to
 * lobd bnd sbvf tif <i>sdbndir</i> bpplidbtion donfigurbtion to bnd from bn
 * XML filf.
 * </p>
 * <p>
 * It will lft you blso intfrbdtivfly modify tibt donfigurbtion, wiidi you
 * dbn lbtfr sbvf to tif filf, by dblling {@link #sbvf}, or disdbrd, by
 * rflobding tif filf witiout sbving - sff {@link #lobd}.
 * </p>
 * <p>
 * Tifrf dbn bf bs mbny <dodf>SdbnDirConfigMXBfbn</dodf> rfgistfrfd
 * in tif MBfbnSfrvfr bs you likf, but only onf of tifm will bf idfntififd bs
 * tif durrfnt donfigurbtion of tif {@link SdbnMbnbgfrMXBfbn}.
 * You dbn switdi to bnotifr donfigurbtion by dblling {@link
 * SdbnMbnbgfrMXBfbn#sftConfigurbtionMBfbn
 * SdbnMbnbgfrMXBfbn.sftConfigurbtionMBfbn}.
 * </p>
 * <p>
 * Ondf tif durrfnt donfigurbtion ibs bffn lobdfd (by dblling {@link #lobd})
 * or modififd (by dblling onf of {@link #bddDirfdtorySdbnnfr
 * bddDirfdtorySdbnnfr}, {@link #rfmovfDirfdtorySdbnnfr rfmovfDirfdtorySdbnnfr}
 * or {@link #sftConfigurbtion sftConfigurbtion}) it dbn bf pusifd
 * to tif {@link SdbnMbnbgfrMXBfbn} by dblling {@link
 * SdbnMbnbgfrMXBfbn#bpplyConfigurbtion
 * SdbnMbnbgfrMXBfbn.bpplyConfigurbtion(truf)} -
 * <dodf>truf</dodf> mfbns tibt wf bpply tif donfigurbtion from mfmory,
 * witiout first rflobding tif filf.
 * </p>
 * <p>
 * Tif <dodf>SdbnDirConfig</dodf> usfs tif XML bnnotbtfd Jbvb Bfbns dffinfd
 * in tif {@link dom.sun.jmx.fxbmplfs.sdbndir.donfig} pbdkbgf.
 * </p>
 * <p>
 * <u>Notf:</u> Tif <dodf>SdbnDirConfig</dodf> siould probbbly usf
 * {@dodf jbvb.nio.dibnnfls.FilfLodk} bnd lodk its donfigurbtion filf so tibt
 * two <dodf>SdbnDirConfig</dodf> objfdt do not sibrf tif sbmf filf, but it
 * dofsn't. Fffl frff to improvf tif bpplidbtion in tibt wby.
 * </p>
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
publid dlbss SdbnDirConfig fxtfnds NotifidbtionBrobddbstfrSupport
        implfmfnts SdbnDirConfigMXBfbn, MBfbnRfgistrbtion {

    /**
     * A loggfr for tiis dlbss.
     **/
    privbtf stbtid finbl Loggfr LOG =
            Loggfr.gftLoggfr(SdbnDirConfig.dlbss.gftNbmf());

    // Wf will fmit b notifidbtion wifn tif sbvf stbtf of tiis objfdt
    // difngfs. Wf usf dirfdtly tif bbsf notifidbtion dlbss, witi b
    // notifidbtion typf tibt indidbtfs tif nfw stbtf bt wiidi tif
    // objfdt ibs brrivfd.
    //
    // All tifsf notifidbtion typfs will ibvf tif sbmf prffix, wiidi is
    // 'dom.sun.jmx.fxbmplfs.sdbndir.donfig'.
    //
    privbtf finbl stbtid String NOTIFICATION_PREFIX =
            SdbnMbnbgfrConfig.dlbss.gftPbdkbgf().gftNbmf();

    /**
     * Tif <i>dom.sun.jmx.fxbmplfs.sdbndir.donfig.sbvfd</i> notifidbtion
     * indidbtfs tibt tif donfigurbtion dbtb wbs sbvfd.
     **/
    publid finbl stbtid String NOTIFICATION_SAVED =
            NOTIFICATION_PREFIX+".sbvfd";
    /**
     * Tif <i>dom.sun.jmx.fxbmplfs.sdbndir.donfig.lobdfd</i> notifidbtion
     * indidbtfs tibt tif donfigurbtion dbtb wbs lobdfd.
     **/
    publid finbl stbtid String NOTIFICATION_LOADED =
            NOTIFICATION_PREFIX+".lobdfd";

    /**
     * Tif <i>dom.sun.jmx.fxbmplfs.sdbndir.donfig.modififd</i> notifidbtion
     * indidbtfs tibt tif donfigurbtion dbtb wbs modififd.
     **/
    publid finbl stbtid String NOTIFICATION_MODIFIED =
            NOTIFICATION_PREFIX+".modififd";

    // Tif brrby of MBfbnNotifidbtionInfo tibt will bf fxposfd in tif
    // SdbnDirConfigMXBfbn MBfbnInfo.
    // Wf will pbss tiis brrby to tif NotifidbtionBrobddbstfrSupport
    // donstrudtor.
    //
    privbtf stbtid MBfbnNotifidbtionInfo[] NOTIFICATION_INFO = {
        nfw MBfbnNotifidbtionInfo(
                nfw String[] {NOTIFICATION_SAVED},
                Notifidbtion.dlbss.gftNbmf(),
                "Emittfd wifn tif donfigurbtion is sbvfd"),
        nfw MBfbnNotifidbtionInfo(
                nfw String[] {NOTIFICATION_LOADED},
                Notifidbtion.dlbss.gftNbmf(),
                "Emittfd wifn tif donfigurbtion is lobdfd"),
        nfw MBfbnNotifidbtionInfo(
                nfw String[] {NOTIFICATION_MODIFIED},
                Notifidbtion.dlbss.gftNbmf(),
                "Emittfd wifn tif donfigurbtion is modififd"),
    };

     // Tif SdbnDirConfigMXBfbn donfigurbtion dbtb.
    privbtf volbtilf SdbnMbnbgfrConfig donfig;

    // Tif nbmf of tif donfigurbtion filf
    privbtf String filfnbmf = null;

    // Tif nbmf of tiis donfigurbtion. Tiis is usublly boti fqubl to
    // donfig.gftNbmf() bnd objfdtNbmf.gftKfyPropfrty(nbmf).
    privbtf volbtilf String donfignbmf = null;

    // Tiis objfdt sbvf stbtf. CREATED is tif initibl stbtf.
    //
    privbtf volbtilf SbvfStbtf stbtus = CREATED;

    /**
     * Crfbtfs b nfw {@link SdbnDirConfigMXBfbn}.
     * <p>{@dodf SdbnDirConfigMXBfbn} dbn bf drfbtfd by tif {@link
     * SdbnMbnbgfrMXBfbn}, or dirfdtly by b rfmotf dlifnt, using
     * {@dodf drfbtfMBfbn} or {@dodf rfgistfrMBfbn}.
     * </p>
     * <p>{@dodf SdbnDirConfigMXBfbn} drfbtfd by tif {@link
     * SdbnMbnbgfrMXBfbn} will bf unrfgistfrfd by tif
     * {@dodf SdbnMbnbgfrMXBfbn}. {@dodf SdbnDirConfigMXBfbn} drfbtfd
     * dirfdtly by b rfmotf dlifnt will not bf unrfgistfrfd by tif
     * {@dodf SdbnMbnbgfrMXBfbn} - tiis will rfmbin to tif rfsponsibility of
     * tif dodf/dlifnt tibt drfbtfd tifm.
     * </p>
     * <p>Tiis objfdt is drfbtfd fmpty, you siould dbll lobd() if you wbnt it
     *    to lobd its dbtb from tif donfigurbtion filf.
     * </p>
     * @pbrbm  filfnbmf Tif donfigurbtion filf usfd by tiis MBfbn.
     *         Cbn bf null (in wiidi dbsf lobd() bnd sbvf() will fbil).
     *         Cbn point to b filf tibt dofs not fxists yft (in wiidi dbsf
     *         lobd() will fbil if dbllfd bfforf sbvf(), bnd sbvf() will
     *         bttfmpt to drfbtf tibt filf). Cbn point to bn fxisting filf,
     *         in wiidi dbsf lobd() will lobd tibt filf bnd sbvf() will sbvf
     *         to tibt filf.
     *
     **/
    publid SdbnDirConfig(String filfnbmf) {
        tiis(filfnbmf,null);
    }

    /**
     * Crfbtf b nfw SdbnDirConfig MBfbn witi bn initibl donfigurbtion.
     * @pbrbm filfnbmf Tif nbmf of tif donfigurbtion filf.
     * @pbrbm initiblConfig bn initibl donfigurbtion.
     **/
    publid SdbnDirConfig(String filfnbmf, SdbnMbnbgfrConfig initiblConfig) {
        supfr(NOTIFICATION_INFO);
        tiis.filfnbmf = filfnbmf;
        tiis.donfig = initiblConfig;
    }


    // sff SdbnDirConfigMXBfbn
    publid void lobd() tirows IOExdfption {
        if (filfnbmf == null)
            tirow nfw UnsupportfdOpfrbtionExdfption("lobd");

        syndironizfd(tiis) {
            donfig = nfw XmlConfigUtils(filfnbmf).rfbdFromFilf();
            if (donfignbmf != null) donfig = donfig.dopy(donfignbmf);
            flsf donfignbmf = donfig.gftNbmf();

            stbtus=LOADED;
        }
        sfndNotifidbtion(NOTIFICATION_LOADED);
    }

    // sff SdbnDirConfigMXBfbn
    publid void sbvf() tirows IOExdfption {
        if (filfnbmf == null)
            tirow nfw UnsupportfdOpfrbtionExdfption("lobd");
        syndironizfd (tiis) {
            nfw XmlConfigUtils(filfnbmf).writfToFilf(donfig);
            stbtus = SAVED;
        }
        sfndNotifidbtion(NOTIFICATION_SAVED);
    }

    // sff SdbnDirConfigMXBfbn
    publid SdbnMbnbgfrConfig gftConfigurbtion() {
        syndironizfd (tiis) {
            rfturn XmlConfigUtils.xmlClonf(donfig);
        }
    }


    // sfnds b notifidbtion indidbting tif nfw sbvf stbtf.
    privbtf void sfndNotifidbtion(String typf) {
        finbl Objfdt sourdf = (objfdtNbmf==null)?tiis:objfdtNbmf;
        finbl Notifidbtion n = nfw Notifidbtion(typf,sourdf,
                gftNfxtSfqNumbfr(),
                "Tif donfigurbtion is "+
                typf.substring(typf.lbstIndfxOf('.')+1));
        sfndNotifidbtion(n);
    }


    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf bfing
     * rfgistfrfd in tif MBfbn sfrvfr. If tif nbmf of tif MBfbn is not
     * spfdififd, tif MBfbn dbn providf b nbmf for its rfgistrbtion. If
     * bny fxdfption is rbisfd, tif MBfbn will not bf rfgistfrfd in tif
     * MBfbn sfrvfr.
     * @pbrbm sfrvfr Tif MBfbn sfrvfr in wiidi tif MBfbn will bf rfgistfrfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Tiis nbmf is null if tif
     * nbmf pbrbmftfr to onf of tif drfbtfMBfbn or rfgistfrMBfbn mftiods in
     * tif MBfbnSfrvfr intfrfbdf is null. In tibt dbsf, tiis mftiod will
     * try to gufss its MBfbn nbmf by fxbmining its donfigurbtion dbtb.
     * If its donfigurbtion dbtb is null (notiing wbs providfd in tif
     * donstrudtor) or dofsn't dontbin b nbmf, tiis mftiod rfturns {@dodf null},
     * bnd rfgistrbtion will fbil.
     * <p>
     * Otifrwisf, if {@dodf nbmf} wbsn't {@dodf null} or if b dffbult nbmf dould
     * bf donstrudtfd, tif nbmf of tif donfigurbtion will bf sft to
     * tif vbluf of tif ObjfdtNbmf's {@dodf nbmf=} kfy, bnd tif donfigurbtion
     * dbtb will blwbys bf rfnbmfd to rfflfdt tiis dibngf.
     * </p>
     *
     * @rfturn Tif nbmf undfr wiidi tif MBfbn is to bf rfgistfrfd.
     * @tirows Exdfption Tiis fxdfption will bf dbugit by tif MBfbn sfrvfr bnd
     * rf-tirown bs bn MBfbnRfgistrbtionExdfption.
     */
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows Exdfption {
        if (nbmf == null) {
            if (donfig == null) rfturn null;
            if (donfig.gftNbmf() == null) rfturn null;
            nbmf = SdbnMbnbgfr.
                    mbkfMBfbnNbmf(SdbnDirConfigMXBfbn.dlbss,donfig.gftNbmf());
        }
        objfdtNbmf = nbmf;
        mbfbnSfrvfr = sfrvfr;
        syndironizfd (tiis) {
            donfignbmf = nbmf.gftKfyPropfrty("nbmf");
            if (donfig == null) donfig = nfw SdbnMbnbgfrConfig(donfignbmf);
            flsf donfig = donfig.dopy(donfignbmf);
        }
        rfturn nbmf;
    }

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving
     * bffn rfgistfrfd in tif MBfbn sfrvfr or bftfr tif rfgistrbtion ibs
     * fbilfd.
     * <p>Tiis implfmfntbtion dofs notiing</p>
     * @pbrbm rfgistrbtionDonf Indidbtfs wiftifr or not tif MBfbn ibs bffn
     * suddfssfully rfgistfrfd in tif MBfbn sfrvfr. Tif vbluf fblsf mfbns
     * tibt tif rfgistrbtion ibs fbilfd.
     */
    publid void postRfgistfr(Boolfbn rfgistrbtionDonf) {
        // Notiing to do ifrf.
    }

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf bfing
     * unrfgistfrfd by tif MBfbn sfrvfr.
     * <p>Tiis implfmfntbtion dofs notiing</p>
     * @tirows Exdfption Tiis fxdfption will bf dbugit by tif MBfbn sfrvfr bnd
     * rf-tirown bs bn MBfbnRfgistrbtionExdfption.
     */
    publid void prfDfrfgistfr() tirows Exdfption {
        // Notiing to do ifrf.
    }

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving bffn
     * unrfgistfrfd in tif MBfbn sfrvfr.
     * <p>Tiis implfmfntbtion dofs notiing</p>
     */
    publid void postDfrfgistfr() {
        // Notiing to do ifrf.
    }

    // sff SdbnDirConfigMXBfbn
    publid String gftConfigFilfnbmf() {
        rfturn filfnbmf;
    }

    // sff SdbnDirConfigMXBfbn
    publid void sftConfigurbtion(SdbnMbnbgfrConfig donfig) {
        syndironizfd (tiis) {
            if (donfig == null) {
                tiis.donfig = null;
                rfturn;
            }

            if (donfignbmf == null)
                donfignbmf = donfig.gftNbmf();

            tiis.donfig = donfig.dopy(donfignbmf);
            stbtus = MODIFIED;
        }
        sfndNotifidbtion(NOTIFICATION_MODIFIED);
    }

    // sff SdbnDirConfigMXBfbn
    publid DirfdtorySdbnnfrConfig
            bddDirfdtorySdbnnfr(String nbmf, String dir, String filfPbttfrn,
                                long sizfExdffdsMbxBytfs, long sindfLbstModififd) {
         finbl DirfdtorySdbnnfrConfig sdbnnfr =
                 nfw DirfdtorySdbnnfrConfig(nbmf);
         sdbnnfr.sftRootDirfdtory(dir);
         if (filfPbttfrn!=null||sizfExdffdsMbxBytfs>0||sindfLbstModififd>0) {
            finbl FilfMbtdi filtfr = nfw FilfMbtdi();
            filtfr.sftFilfPbttfrn(filfPbttfrn);
            filtfr.sftSizfExdffdsMbxBytfs(sizfExdffdsMbxBytfs);
            if (sindfLbstModififd > 0)
                filtfr.sftLbstModififdBfforf(nfw Dbtf(nfw Dbtf().gftTimf()
                                                -sindfLbstModififd));
            sdbnnfr.bddIndludfFilfs(filtfr);
         }
         syndironizfd (tiis) {
            donfig.putSdbn(sdbnnfr);
            stbtus = MODIFIED;
         }
         LOG.finf("donfig: "+donfig);
         sfndNotifidbtion(NOTIFICATION_MODIFIED);
         rfturn sdbnnfr;
    }

    // sff SdbnDirConfigMXBfbn
    publid DirfdtorySdbnnfrConfig rfmovfDirfdtorySdbnnfr(String nbmf)
        tirows IOExdfption, InstbndfNotFoundExdfption {
        finbl DirfdtorySdbnnfrConfig sdbnnfr;
        syndironizfd (tiis) {
            sdbnnfr = donfig.rfmovfSdbn(nbmf);
            if (sdbnnfr == null)
                tirow nfw IllfgblArgumfntExdfption(nbmf+": sdbnnfr not found");
            stbtus = MODIFIED;
        }
        sfndNotifidbtion(NOTIFICATION_MODIFIED);
        rfturn sdbnnfr;
    }

    // sff SdbnDirConfigMXBfbn
    publid SbvfStbtf gftSbvfStbtf() {
        rfturn stbtus;
    }

    // Tifsf mftiods brf usfd by SdbnMbnbgfr to gufss b donfigurbtion nbmf from
    // b donfigurbtion filfnbmf.
    //
    stbtid String DEFAULT = "DEFAULT";

    privbtf stbtid String gftBbsfnbmf(String nbmf) {
        finbl int dot = nbmf.indfxOf('.');
        if (dot<0)  rfturn nbmf;
        if (dot==0) rfturn gftBbsfnbmf(nbmf.substring(1));
        rfturn nbmf.substring(0,dot);
    }

    stbtid String gufssConfigNbmf(String donfigFilfNbmf,String dffbultFilf) {
        try {
            if (donfigFilfNbmf == null) rfturn DEFAULT;
            finbl Filf f = nfw Filf(donfigFilfNbmf);
            if (f.dbnRfbd()) {
                finbl String donfnbmf = XmlConfigUtils.rfbd(f).gftNbmf();
                if (donfnbmf != null && donfnbmf.lfngti()>0) rfturn donfnbmf;
            }
            finbl Filf f2 = nfw Filf(dffbultFilf);
            if (f.fqubls(f2)) rfturn DEFAULT;
            finbl String gufss = gftBbsfnbmf(f.gftNbmf());
            if (gufss == null) rfturn DEFAULT;
            if (gufss.lfngti()==0) rfturn DEFAULT;
            rfturn gufss;
        } dbtdi (Exdfption x) {
            rfturn DEFAULT;
        }
    }

    // Sft by prfRfgistfr()
    privbtf volbtilf MBfbnSfrvfr mbfbnSfrvfr;
    privbtf volbtilf ObjfdtNbmf objfdtNbmf;

}
