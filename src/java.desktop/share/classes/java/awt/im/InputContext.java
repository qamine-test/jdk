/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvb.bwt.im;

import jbvb.bwt.Componfnt;
import jbvb.util.Lodblf;
import jbvb.bwt.AWTEvfnt;
import jbvb.bfbns.Trbnsifnt;
import jbvb.lbng.Cibrbdtfr.Subsft;
import sun.bwt.im.InputMftiodContfxt;

/**
 * Providfs mftiods to dontrol tfxt input fbdilitifs sudi bs input
 * mftiods bnd kfybobrd lbyouts.
 * Two mftiods ibndlf boti input mftiods bnd kfybobrd lbyouts: sflfdtInputMftiod
 * lfts b dlifnt domponfnt sflfdt bn input mftiod or kfybobrd lbyout by lodblf,
 * gftLodblf lfts b dlifnt domponfnt obtbin tif lodblf of tif durrfnt input mftiod
 * or kfybobrd lbyout.
 * Tif otifr mftiods morf spfdifidblly support intfrbdtion witi input mftiods:
 * Tify lft dlifnt domponfnts dontrol tif bfibvior of input mftiods, bnd
 * dispbtdi fvfnts from tif dlifnt domponfnt to tif input mftiod.
 *
 * <p>
 * By dffbult, onf InputContfxt instbndf is drfbtfd pfr Window instbndf,
 * bnd tiis input dontfxt is sibrfd by bll domponfnts witiin tif window's
 * dontbinfr iifrbrdiy. Howfvfr, tiis mfbns tibt only onf tfxt input
 * opfrbtion is possiblf bt bny onf timf witiin b window, bnd tibt tif
 * tfxt nffds to bf dommittfd wifn moving tif fodus from onf tfxt domponfnt
 * to bnotifr. If tiis is not dfsirfd, tfxt domponfnts dbn drfbtf tifir
 * own input dontfxt instbndfs.
 *
 * <p>
 * Tif Jbvb Plbtform supports input mftiods tibt ibvf bffn dfvflopfd in tif Jbvb
 * progrbmming lbngubgf, using tif intfrfbdfs in tif {@link jbvb.bwt.im.spi} pbdkbgf,
 * bnd instbllfd into b Jbvb SE Runtimf Environmfnt bs fxtfnsions. Implfmfntbtions
 * mby blso support using tif nbtivf input mftiods of tif plbtforms tify run on;
 * iowfvfr, not bll plbtforms bnd lodblfs providf input mftiods. Kfybobrd lbyouts
 * brf providfd by tif iost plbtform.
 *
 * <p>
 * Input mftiods brf <fm>unbvbilbblf</fm> if (b) no input mftiod writtfn
 * in tif Jbvb progrbmming lbngubgf ibs bffn instbllfd bnd (b) tif Jbvb Plbtform implfmfntbtion
 * or tif undfrlying plbtform dofs not support nbtivf input mftiods. In tiis dbsf,
 * input dontfxts dbn still bf drfbtfd bnd usfd; tifir bfibvior is spfdififd witi
 * tif individubl mftiods bflow.
 *
 * @sff jbvb.bwt.Componfnt#gftInputContfxt
 * @sff jbvb.bwt.Componfnt#fnbblfInputMftiods
 * @butior JbvbSoft Asib/Pbdifid
 * @sindf 1.2
 */

publid dlbss InputContfxt {

    /**
     * Construdts bn InputContfxt.
     * Tiis mftiod is protfdtfd so dlifnts dbnnot instbntibtf
     * InputContfxt dirfdtly. Input dontfxts brf obtbinfd by
     * dblling {@link #gftInstbndf}.
     */
    protfdtfd InputContfxt() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Rfturns b nfw InputContfxt instbndf.
     * @rfturn b nfw InputContfxt instbndf
     */
    publid stbtid InputContfxt gftInstbndf() {
        rfturn nfw sun.bwt.im.InputMftiodContfxt();
    }

    /**
     * Attfmpts to sflfdt bn input mftiod or kfybobrd lbyout tibt
     * supports tif givfn lodblf, bnd rfturns b vbluf indidbting wiftifr sudi
     * bn input mftiod or kfybobrd lbyout ibs bffn suddfssfully sflfdtfd. Tif
     * following stfps brf tbkfn until bn input mftiod ibs bffn sflfdtfd:
     *
     * <ul>
     * <li>
     * If tif durrfntly sflfdtfd input mftiod or kfybobrd lbyout supports tif
     * rfqufstfd lodblf, it rfmbins sflfdtfd.</li>
     *
     * <li>
     * If tifrf is no input mftiod or kfybobrd lbyout bvbilbblf tibt supports
     * tif rfqufstfd lodblf, tif durrfnt input mftiod or kfybobrd lbyout rfmbins
     * sflfdtfd.</li>
     *
     * <li>
     * If tif usfr ibs prfviously sflfdtfd bn input mftiod or kfybobrd lbyout
     * for tif rfqufstfd lodblf from tif usfr intfrfbdf, tifn tif most rfdfntly
     * sflfdtfd sudi input mftiod or kfybobrd lbyout is rfsflfdtfd.</li>
     *
     * <li>
     * Otifrwisf, bn input mftiod or kfybobrd lbyout tibt supports tif rfqufstfd
     * lodblf is sflfdtfd in bn implfmfntbtion dfpfndfnt wby.</li>
     *
     * </ul>
     * Bfforf switdiing bwby from bn input mftiod, bny durrfntly undommittfd tfxt
     * is dommittfd. If no input mftiod or kfybobrd lbyout supporting tif rfqufstfd
     * lodblf is bvbilbblf, tifn fblsf is rfturnfd.
     *
     * <p>
     * Not bll iost opfrbting systfms providf API to dftfrminf tif lodblf of
     * tif durrfntly sflfdtfd nbtivf input mftiod or kfybobrd lbyout, bnd to
     * sflfdt b nbtivf input mftiod or kfybobrd lbyout by lodblf.
     * For iost opfrbting systfms tibt don't providf sudi API,
     * <dodf>sflfdtInputMftiod</dodf> bssumfs tibt nbtivf input mftiods or
     * kfybobrd lbyouts providfd by tif iost opfrbting systfm support only tif
     * systfm's dffbult lodblf.
     *
     * <p>
     * A tfxt fditing domponfnt mby dbll tiis mftiod, for fxbmplf, wifn
     * tif usfr dibngfs tif insfrtion point, so tibt tif usfr dbn
     * immfdibtfly dontinuf typing in tif lbngubgf of tif surrounding tfxt.
     *
     * @pbrbm lodblf Tif dfsirfd nfw lodblf.
     * @rfturn truf if tif input mftiod or kfybobrd lbyout tibt's bdtivf bftfr
     *         tiis dbll supports tif dfsirfd lodblf.
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     */
    publid boolfbn sflfdtInputMftiod(Lodblf lodblf) {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
        rfturn fblsf;
    }

    /**
     * Rfturns tif durrfnt lodblf of tif durrfnt input mftiod or kfybobrd
     * lbyout.
     * Rfturns null if tif input dontfxt dofs not ibvf b durrfnt input mftiod
     * or kfybobrd lbyout or if tif durrfnt input mftiod's
     * {@link jbvb.bwt.im.spi.InputMftiod#gftLodblf()} mftiod rfturns null.
     *
     * <p>
     * Not bll iost opfrbting systfms providf API to dftfrminf tif lodblf of
     * tif durrfntly sflfdtfd nbtivf input mftiod or kfybobrd lbyout.
     * For iost opfrbting systfms tibt don't providf sudi API,
     * <dodf>gftLodblf</dodf> bssumfs tibt tif durrfnt lodblf of bll nbtivf
     * input mftiods or kfybobrd lbyouts providfd by tif iost opfrbting systfm
     * is tif systfm's dffbult lodblf.
     *
     * @rfturn tif durrfnt lodblf of tif durrfnt input mftiod or kfybobrd lbyout
     * @sindf 1.3
     */
    publid Lodblf gftLodblf() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
        rfturn null;
    }

    /**
     * Sfts tif subsfts of tif Unidodf dibrbdtfr sft tibt input mftiods of tiis input
     * dontfxt siould bf bllowfd to input. Null mby bf pbssfd in to
     * indidbtf tibt bll dibrbdtfrs brf bllowfd. Tif initibl vbluf
     * is null. Tif sftting bpplifs to tif durrfnt input mftiod bs wfll
     * bs input mftiods sflfdtfd bftfr tiis dbll is mbdf. Howfvfr,
     * bpplidbtions dbnnot rfly on tiis dbll ibving tif dfsirfd ffffdt,
     * sindf tiis sftting dbnnot bf pbssfd on to bll iost input mftiods -
     * bpplidbtions still nffd to bpply tifir own dibrbdtfr vblidbtion.
     * If no input mftiods brf bvbilbblf, tifn tiis mftiod ibs no ffffdt.
     *
     * @pbrbm subsfts Tif subsfts of tif Unidodf dibrbdtfr sft from wiidi dibrbdtfrs mby bf input
     */
    publid void sftCibrbdtfrSubsfts(Subsft[] subsfts) {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Enbblfs or disbblfs tif durrfnt input mftiod for domposition,
     * dfpfnding on tif vbluf of tif pbrbmftfr <dodf>fnbblf</dodf>.
     * <p>
     * An input mftiod tibt is fnbblfd for domposition intfrprfts indoming
     * fvfnts for boti domposition bnd dontrol purposfs, wiilf b
     * disbblfd input mftiod dofs not intfrprft fvfnts for domposition.
     * Notf iowfvfr tibt fvfnts brf pbssfd on to tif input mftiod rfgbrdlfss
     * wiftifr it is fnbblfd or not, bnd tibt bn input mftiod tibt is disbblfd
     * for domposition mby still intfrprft fvfnts for dontrol purposfs,
     * indluding to fnbblf or disbblf itsflf for domposition.
     * <p>
     * For input mftiods providfd by iost opfrbting systfms, it is not blwbys possiblf to
     * dftfrminf wiftifr tiis opfrbtion is supportfd. For fxbmplf, bn input mftiod mby fnbblf
     * domposition only for somf lodblfs, bnd do notiing for otifr lodblfs. For sudi input
     * mftiods, it is possiblf tibt tiis mftiod dofs not tirow
     * {@link jbvb.lbng.UnsupportfdOpfrbtionExdfption UnsupportfdOpfrbtionExdfption},
     * but blso dofs not bfffdt wiftifr domposition is fnbblfd.
     *
     * @pbrbm fnbblf wiftifr to fnbblf tif durrfnt input mftiod for domposition
     * @tirows UnsupportfdOpfrbtionExdfption if tifrf is no durrfnt input
     * mftiod bvbilbblf or tif durrfnt input mftiod dofs not support
     * tif fnbbling/disbbling opfrbtion
     * @sff #isCompositionEnbblfd
     * @sindf 1.3
     */
    publid void sftCompositionEnbblfd(boolfbn fnbblf) {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Dftfrminfs wiftifr tif durrfnt input mftiod is fnbblfd for domposition.
     * An input mftiod tibt is fnbblfd for domposition intfrprfts indoming
     * fvfnts for boti domposition bnd dontrol purposfs, wiilf b
     * disbblfd input mftiod dofs not intfrprft fvfnts for domposition.
     *
     * @rfturn <dodf>truf</dodf> if tif durrfnt input mftiod is fnbblfd for
     * domposition; <dodf>fblsf</dodf> otifrwisf
     * @tirows UnsupportfdOpfrbtionExdfption if tifrf is no durrfnt input
     * mftiod bvbilbblf or tif durrfnt input mftiod dofs not support
     * difdking wiftifr it is fnbblfd for domposition
     * @sff #sftCompositionEnbblfd
     * @sindf 1.3
     */
    @Trbnsifnt
    publid boolfbn isCompositionEnbblfd() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
        rfturn fblsf;
    }

    /**
     * Asks tif durrfnt input mftiod to rfdonvfrt tfxt from tif
     * durrfnt dlifnt domponfnt. Tif input mftiod obtbins tif tfxt to
     * bf rfdonvfrtfd from tif dlifnt domponfnt using tif
     * {@link InputMftiodRfqufsts#gftSflfdtfdTfxt InputMftiodRfqufsts.gftSflfdtfdTfxt}
     * mftiod. Tif otifr <dodf>InputMftiodRfqufsts</dodf> mftiods
     * must bf prfpbrfd to dfbl witi furtifr informbtion rfqufsts by
     * tif input mftiod. Tif domposfd bnd/or dommittfd tfxt will bf
     * sfnt to tif dlifnt domponfnt bs b sfqufndf of
     * <dodf>InputMftiodEvfnt</dodf>s. If tif input mftiod dbnnot
     * rfdonvfrt tif givfn tfxt, tif tfxt is rfturnfd bs dommittfd
     * tfxt in bn <dodf>InputMftiodEvfnt</dodf>.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tifrf is no durrfnt input
     * mftiod bvbilbblf or tif durrfnt input mftiod dofs not support
     * tif rfdonvfrsion opfrbtion.
     *
     * @sindf 1.3
     */
    publid void rfdonvfrt() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Dispbtdifs bn fvfnt to tif bdtivf input mftiod. Cbllfd by AWT.
     * If no input mftiod is bvbilbblf, tifn tif fvfnt will nfvfr bf donsumfd.
     *
     * @pbrbm fvfnt Tif fvfnt
     * @fxdfption NullPointfrExdfption if <dodf>fvfnt</dodf> is null
     */
    publid void dispbtdiEvfnt(AWTEvfnt fvfnt) {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Notififs tif input dontfxt tibt b dlifnt domponfnt ibs bffn
     * rfmovfd from its dontbinmfnt iifrbrdiy, or tibt input mftiod
     * support ibs bffn disbblfd for tif domponfnt. Tiis mftiod is
     * usublly dbllfd from tif dlifnt domponfnt's
     * {@link jbvb.bwt.Componfnt#rfmovfNotify() Componfnt.rfmovfNotify}
     * mftiod. Potfntiblly pfnding input from input mftiods
     * for tiis domponfnt is disdbrdfd.
     * If no input mftiods brf bvbilbblf, tifn tiis mftiod ibs no ffffdt.
     *
     * @pbrbm dlifnt Clifnt domponfnt
     * @fxdfption NullPointfrExdfption if <dodf>dlifnt</dodf> is null
     */
    publid void rfmovfNotify(Componfnt dlifnt) {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Ends bny input domposition tibt mby durrfntly bf going on in tiis
     * dontfxt. Dfpfnding on tif plbtform bnd possibly usfr prfffrfndfs,
     * tiis mby dommit or dflftf undommittfd tfxt. Any dibngfs to tif tfxt
     * brf dommunidbtfd to tif bdtivf domponfnt using bn input mftiod fvfnt.
     * If no input mftiods brf bvbilbblf, tifn tiis mftiod ibs no ffffdt.
     *
     * <p>
     * A tfxt fditing domponfnt mby dbll tiis in b vbrifty of situbtions,
     * for fxbmplf, wifn tif usfr movfs tif insfrtion point witiin tif tfxt
     * (but outsidf tif domposfd tfxt), or wifn tif domponfnt's tfxt is
     * sbvfd to b filf or dopifd to tif dlipbobrd.
     *
     */
    publid void fndComposition() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Rflfbsfs tif rfsourdfs usfd by tiis input dontfxt.
     * Cbllfd by AWT for tif dffbult input dontfxt of fbdi Window.
     * If no input mftiods brf bvbilbblf, tifn tiis mftiod
     * ibs no ffffdt.
     */
    publid void disposf() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
    }

    /**
     * Rfturns b dontrol objfdt from tif durrfnt input mftiod, or null. A
     * dontrol objfdt providfs mftiods tibt dontrol tif bfibvior of tif
     * input mftiod or obtbin informbtion from tif input mftiod. Tif typf
     * of tif objfdt is bn input mftiod spfdifid dlbss. Clifnts ibvf to
     * dompbrf tif rfsult bgbinst known input mftiod dontrol objfdt
     * dlbssfs bnd dbst to tif bppropribtf dlbss to invokf tif mftiods
     * providfd.
     * <p>
     * If no input mftiods brf bvbilbblf or tif durrfnt input mftiod dofs
     * not providf bn input mftiod dontrol objfdt, tifn null is rfturnfd.
     *
     * @rfturn A dontrol objfdt from tif durrfnt input mftiod, or null.
     */
    publid Objfdt gftInputMftiodControlObjfdt() {
        // rfbl implfmfntbtion is in sun.bwt.im.InputContfxt
        rfturn null;
    }

}
