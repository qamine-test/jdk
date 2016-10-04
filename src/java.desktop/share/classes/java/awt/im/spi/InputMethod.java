/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.im.spi;

import jbvb.util.Lodblf;
import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.lbng.Cibrbdtfr.Subsft;


/**
 * Dffinfs tif intfrfbdf for bn input mftiod tibt supports domplfx tfxt input.
 * Input mftiods trbditionblly support tfxt input for lbngubgfs tibt ibvf
 * morf dibrbdtfrs tibn dbn bf rfprfsfntfd on b stbndbrd-sizf kfybobrd,
 * sudi bs Ciinfsf, Jbpbnfsf, bnd Korfbn. Howfvfr, tify mby blso bf usfd to
 * support pionftid tfxt input for Englisi or dibrbdtfr rfordfring for Tibi.
 * <p>
 * Subdlbssfs of InputMftiod dbn bf lobdfd by tif input mftiod frbmfwork; tify
 * dbn tifn bf sflfdtfd fitifr tirougi tif API
 * ({@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod})
 * or tif usfr intfrfbdf (tif input mftiod sflfdtion mfnu).
 *
 * @sindf 1.3
 *
 * @butior JbvbSoft Intfrnbtionbl
 */

publid intfrfbdf InputMftiod {

    /**
     * Sfts tif input mftiod dontfxt, wiidi is usfd to dispbtdi input mftiod
     * fvfnts to tif dlifnt domponfnt bnd to rfqufst informbtion from
     * tif dlifnt domponfnt.
     * <p>
     * Tiis mftiod is dbllfd ondf immfdibtfly bftfr instbntibting tiis input
     * mftiod.
     *
     * @pbrbm dontfxt tif input mftiod dontfxt for tiis input mftiod
     * @fxdfption NullPointfrExdfption if <dodf>dontfxt</dodf> is null
     */
    publid void sftInputMftiodContfxt(InputMftiodContfxt dontfxt);

    /**
     * Attfmpts to sft tif input lodblf. If tif input mftiod supports tif
     * dfsirfd lodblf, it dibngfs its bfibvior to support input for tif lodblf
     * bnd rfturns truf.
     * Otifrwisf, it rfturns fblsf bnd dofs not dibngf its bfibvior.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod},
     * <li>wifn switdiing to tiis input mftiod tirougi tif usfr intfrfbdf if tif usfr
     *     spfdififd b lodblf or if tif prfviously sflfdtfd input mftiod's
     *     {@link jbvb.bwt.im.spi.InputMftiod#gftLodblf gftLodblf} mftiod
     *     rfturns b non-null vbluf.
     * </ul>
     *
     * @pbrbm lodblf lodblf to input
     * @rfturn wiftifr tif spfdififd lodblf is supportfd
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     */
    publid boolfbn sftLodblf(Lodblf lodblf);

    /**
     * Rfturns tif durrfnt input lodblf. Migit rfturn null in fxdfptionbl dbsfs.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#gftLodblf InputContfxt.gftLodblf} bnd
     * <li>wifn switdiing from tiis input mftiod to b difffrfnt onf tirougi tif
     *     usfr intfrfbdf.
     * </ul>
     *
     * @rfturn tif durrfnt input lodblf, or null
     */
    publid Lodblf gftLodblf();

    /**
     * Sfts tif subsfts of tif Unidodf dibrbdtfr sft tibt tiis input mftiod
     * is bllowfd to input. Null mby bf pbssfd in to indidbtf tibt bll
     * dibrbdtfrs brf bllowfd.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>immfdibtfly bftfr instbntibting tiis input mftiod,
     * <li>wifn switdiing to tiis input mftiod from b difffrfnt onf, bnd
     * <li>by {@link jbvb.bwt.im.InputContfxt#sftCibrbdtfrSubsfts InputContfxt.sftCibrbdtfrSubsfts}.
     * </ul>
     *
     * @pbrbm subsfts tif subsfts of tif Unidodf dibrbdtfr sft from wiidi
     * dibrbdtfrs mby bf input
     */
    publid void sftCibrbdtfrSubsfts(Subsft[] subsfts);

    /**
     * Enbblfs or disbblfs tiis input mftiod for domposition,
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
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#sftCompositionEnbblfd InputContfxt.sftCompositionEnbblfd},
     * <li>wifn switdiing to tiis input mftiod from b difffrfnt onf using tif
     *     usfr intfrfbdf or
     *     {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod},
     *     if tif prfviously sflfdtfd input mftiod's
     *     {@link jbvb.bwt.im.spi.InputMftiod#isCompositionEnbblfd isCompositionEnbblfd}
     *     mftiod rfturns witiout tirowing bn fxdfption.
     * </ul>
     *
     * @pbrbm fnbblf wiftifr to fnbblf tif input mftiod for domposition
     * @tirows UnsupportfdOpfrbtionExdfption if tiis input mftiod dofs not
     * support tif fnbbling/disbbling opfrbtion
     * @sff #isCompositionEnbblfd
     */
    publid void sftCompositionEnbblfd(boolfbn fnbblf);

    /**
     * Dftfrminfs wiftifr tiis input mftiod is fnbblfd.
     * An input mftiod tibt is fnbblfd for domposition intfrprfts indoming
     * fvfnts for boti domposition bnd dontrol purposfs, wiilf b
     * disbblfd input mftiod dofs not intfrprft fvfnts for domposition.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#isCompositionEnbblfd InputContfxt.isCompositionEnbblfd} bnd
     * <li>wifn switdiing from tiis input mftiod to b difffrfnt onf using tif
     *     usfr intfrfbdf or
     *     {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod}.
     * </ul>
     *
     * @rfturn <dodf>truf</dodf> if tiis input mftiod is fnbblfd for
     * domposition; <dodf>fblsf</dodf> otifrwisf.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis input mftiod dofs not
     * support difdking wiftifr it is fnbblfd for domposition
     * @sff #sftCompositionEnbblfd
     */
    publid boolfbn isCompositionEnbblfd();

    /**
     * Stbrts tif rfdonvfrsion opfrbtion. Tif input mftiod obtbins tif
     * tfxt to bf rfdonvfrtfd from tif durrfnt dlifnt domponfnt using tif
     * {@link jbvb.bwt.im.InputMftiodRfqufsts#gftSflfdtfdTfxt InputMftiodRfqufsts.gftSflfdtfdTfxt}
     * mftiod. It dbn usf otifr <dodf>InputMftiodRfqufsts</dodf>
     * mftiods to rfqufst bdditionbl informbtion rfquirfd for tif
     * rfdonvfrsion opfrbtion. Tif domposfd bnd dommittfd tfxt
     * produdfd by tif opfrbtion is sfnt to tif dlifnt domponfnt bs b
     * sfqufndf of <dodf>InputMftiodEvfnt</dodf>s. If tif givfn tfxt
     * dbnnot bf rfdonvfrtfd, tif sbmf tfxt siould bf sfnt to tif
     * dlifnt domponfnt bs dommittfd tfxt.
     * <p>
     * Tiis mftiod is dbllfd by
     * {@link jbvb.bwt.im.InputContfxt#rfdonvfrt() InputContfxt.rfdonvfrt}.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif input mftiod dofs not
     * support tif rfdonvfrsion opfrbtion.
     */
    publid void rfdonvfrt();

    /**
     * Dispbtdifs tif fvfnt to tif input mftiod. If input mftiod support is
     * fnbblfd for tif fodussfd domponfnt, indoming fvfnts of dfrtbin typfs
     * brf dispbtdifd to tif durrfnt input mftiod for tiis domponfnt bfforf
     * tify brf dispbtdifd to tif domponfnt's mftiods or fvfnt listfnfrs.
     * Tif input mftiod dfdidfs wiftifr it nffds to ibndlf tif fvfnt. If it
     * dofs, it blso dblls tif fvfnt's <dodf>donsumf</dodf> mftiod; tiis
     * dbusfs tif fvfnt to not gft dispbtdifd to tif domponfnt's fvfnt
     * prodfssing mftiods or fvfnt listfnfrs.
     * <p>
     * Evfnts brf dispbtdifd if tify brf instbndfs of InputEvfnt or its
     * subdlbssfs.
     * Tiis indludfs instbndfs of tif AWT dlbssfs KfyEvfnt bnd MousfEvfnt.
     * <p>
     * Tiis mftiod is dbllfd by {@link jbvb.bwt.im.InputContfxt#dispbtdiEvfnt InputContfxt.dispbtdiEvfnt}.
     *
     * @pbrbm fvfnt tif fvfnt bfing dispbtdifd to tif input mftiod
     * @fxdfption NullPointfrExdfption if <dodf>fvfnt</dodf> is null
     */
    publid void dispbtdiEvfnt(AWTEvfnt fvfnt);

    /**
     * Notififs tiis input mftiod of dibngfs in tif dlifnt window
     * lodbtion or stbtf. Tiis mftiod is dbllfd wiilf tiis input
     * mftiod is tif durrfnt input mftiod of its input dontfxt bnd
     * notifidbtions for it brf fnbblfd (sff {@link
     * InputMftiodContfxt#fnbblfClifntWindowNotifidbtion
     * InputMftiodContfxt.fnbblfClifntWindowNotifidbtion}). Cblls
     * to tiis mftiod brf tfmporbrily suspfndfd if tif input dontfxt's
     * {@link jbvb.bwt.im.InputContfxt#rfmovfNotify rfmovfNotify}
     * mftiod is dbllfd, bnd rfsumf wifn tif input mftiod is bdtivbtfd
     * for b nfw dlifnt domponfnt. It is dbllfd in tif following
     * situbtions:
     * <ul>
     * <li>
     * wifn tif window dontbining tif durrfnt dlifnt domponfnt dibngfs
     * in lodbtion, sizf, visibility, idonifidbtion stbtf, or wifn tif
     * window is dlosfd.</li>
     * <li>
     * from <dodf> fnbblfClifntWindowNotifidbtion(inputMftiod,
     * truf)</dodf> if tif durrfnt dlifnt domponfnt fxists,</li>
     * <li>
     * wifn bdtivbting tif input mftiod for tif first timf bftfr it
     * dbllfd
     * <dodf>fnbblfClifntWindowNotifidbtion(inputMftiod,
     * truf)</dodf> if during tif dbll no durrfnt dlifnt domponfnt wbs
     * bvbilbblf,</li>
     * <li>
     * wifn bdtivbting tif input mftiod for b nfw dlifnt domponfnt
     * bftfr tif input dontfxt's rfmovfNotify mftiod ibs bffn
     * dbllfd.</li>
     * </ul>
     * @pbrbm bounds dlifnt window's {@link
     * jbvb.bwt.Componfnt#gftBounds bounds} on tif sdrffn; or null if
     * tif dlifnt window is idonififd or invisiblf
     */
    publid void notifyClifntWindowCibngf(Rfdtbnglf bounds);

    /**
     * Adtivbtfs tif input mftiod for immfdibtf input prodfssing.
     * <p>
     * If bn input mftiod providfs its own windows, it siould mbkf surf
     * bt tiis point tibt bll nfdfssbry windows brf opfn bnd visiblf.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#dispbtdiEvfnt InputContfxt.dispbtdiEvfnt}
     *     wifn b dlifnt domponfnt rfdfivfs b FOCUS_GAINED fvfnt,
     * <li>wifn switdiing to tiis input mftiod from b difffrfnt onf using tif
     *     usfr intfrfbdf or
     *     {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod}.
     * </ul>
     * Tif mftiod is only dbllfd wifn tif input mftiod is inbdtivf.
     * A nfwly instbntibtfd input mftiod is bssumfd to bf inbdtivf.
     */
    publid void bdtivbtf();

    /**
     * Dfbdtivbtfs tif input mftiod.
     * Tif isTfmporbry brgumfnt ibs tif sbmf mfbning bs in
     * {@link jbvb.bwt.fvfnt.FodusEvfnt#isTfmporbry FodusEvfnt.isTfmporbry}.
     * <p>
     * If bn input mftiod providfs its own windows, only windows tibt rflbtf
     * to tif durrfnt domposition (sudi bs b lookup dioidf window) siould bf
     * dlosfd bt tiis point.
     * It is possiblf tibt tif input mftiod will bf immfdibtfly bdtivbtfd bgbin
     * for b difffrfnt dlifnt domponfnt, bnd dlosing bnd rfopfning morf
     * pfrsistfnt windows (sudi bs b dontrol pbnfl) would drfbtf unnfdfssbry
     * sdrffn flidkfr.
     * Bfforf bn instbndf of b difffrfnt input mftiod dlbss is bdtivbtfd,
     * {@link #iidfWindows} is dbllfd on tif durrfnt input mftiod.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#dispbtdiEvfnt InputContfxt.dispbtdiEvfnt}
     *     wifn b dlifnt domponfnt rfdfivfs b FOCUS_LOST fvfnt,
     * <li>wifn switdiing from tiis input mftiod to b difffrfnt onf using tif
     *     usfr intfrfbdf or
     *     {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod},
     * <li>bfforf {@link #rfmovfNotify rfmovfNotify} if tif durrfnt dlifnt domponfnt is
     *     rfmovfd.
     * </ul>
     * Tif mftiod is only dbllfd wifn tif input mftiod is bdtivf.
     *
     * @pbrbm isTfmporbry wiftifr tif fodus dibngf is tfmporbry
     */
    publid void dfbdtivbtf(boolfbn isTfmporbry);

    /**
     * Closfs or iidfs bll windows opfnfd by tiis input mftiod instbndf or
     * its dlbss.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>bfforf dblling {@link #bdtivbtf bdtivbtf} on bn instbndf of b difffrfnt input
     *     mftiod dlbss,
     * <li>bfforf dblling {@link #disposf disposf} on tiis input mftiod.
     * </ul>
     * Tif mftiod is only dbllfd wifn tif input mftiod is inbdtivf.
     */
    publid void iidfWindows();

    /**
     * Notififs tif input mftiod tibt b dlifnt domponfnt ibs bffn
     * rfmovfd from its dontbinmfnt iifrbrdiy, or tibt input mftiod
     * support ibs bffn disbblfd for tif domponfnt.
     * <p>
     * Tiis mftiod is dbllfd by {@link jbvb.bwt.im.InputContfxt#rfmovfNotify InputContfxt.rfmovfNotify}.
     * <p>
     * Tif mftiod is only dbllfd wifn tif input mftiod is inbdtivf.
     */
    publid void rfmovfNotify();

    /**
     * Ends bny input domposition tibt mby durrfntly bf going on in tiis
     * dontfxt. Dfpfnding on tif plbtform bnd possibly usfr prfffrfndfs,
     * tiis mby dommit or dflftf undommittfd tfxt. Any dibngfs to tif tfxt
     * brf dommunidbtfd to tif bdtivf domponfnt using bn input mftiod fvfnt.
     *
     * <p>
     * A tfxt fditing domponfnt mby dbll tiis in b vbrifty of situbtions,
     * for fxbmplf, wifn tif usfr movfs tif insfrtion point witiin tif tfxt
     * (but outsidf tif domposfd tfxt), or wifn tif domponfnt's tfxt is
     * sbvfd to b filf or dopifd to tif dlipbobrd.
     * <p>
     * Tiis mftiod is dbllfd
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContfxt#fndComposition InputContfxt.fndComposition},
     * <li>by {@link jbvb.bwt.im.InputContfxt#dispbtdiEvfnt InputContfxt.dispbtdiEvfnt}
     *     wifn switdiing to b difffrfnt dlifnt domponfnt
     * <li>wifn switdiing from tiis input mftiod to b difffrfnt onf using tif
     *     usfr intfrfbdf or
     *     {@link jbvb.bwt.im.InputContfxt#sflfdtInputMftiod InputContfxt.sflfdtInputMftiod}.
     * </ul>
     */
    publid void fndComposition();

    /**
     * Rflfbsfs tif rfsourdfs usfd by tiis input mftiod.
     * In pbrtidulbr, tif input mftiod siould disposf windows bnd dlosf filfs tibt brf no
     * longfr nffdfd.
     * <p>
     * Tiis mftiod is dbllfd by {@link jbvb.bwt.im.InputContfxt#disposf InputContfxt.disposf}.
     * <p>
     * Tif mftiod is only dbllfd wifn tif input mftiod is inbdtivf.
     * No mftiod of tiis intfrfbdf is dbllfd on tiis instbndf bftfr disposf.
     */
    publid void disposf();

    /**
     * Rfturns b dontrol objfdt from tiis input mftiod, or null. A
     * dontrol objfdt providfs mftiods tibt dontrol tif bfibvior of tif
     * input mftiod or obtbin informbtion from tif input mftiod. Tif typf
     * of tif objfdt is bn input mftiod spfdifid dlbss. Clifnts ibvf to
     * dompbrf tif rfsult bgbinst known input mftiod dontrol objfdt
     * dlbssfs bnd dbst to tif bppropribtf dlbss to invokf tif mftiods
     * providfd.
     * <p>
     * Tiis mftiod is dbllfd by
     * {@link jbvb.bwt.im.InputContfxt#gftInputMftiodControlObjfdt InputContfxt.gftInputMftiodControlObjfdt}.
     *
     * @rfturn b dontrol objfdt from tiis input mftiod, or null
     */
    publid Objfdt gftControlObjfdt();

}
