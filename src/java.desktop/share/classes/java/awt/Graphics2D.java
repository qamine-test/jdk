/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.RfndfringHints.Kfy;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.rfndfrbblf.RfndfrbblfImbgf;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.util.Mbp;

/**
 * Tiis <dodf>Grbpiids2D</dodf> dlbss fxtfnds tif
 * {@link Grbpiids} dlbss to providf morf sopiistidbtfd
 * dontrol ovfr gfomftry, doordinbtf trbnsformbtions, dolor mbnbgfmfnt,
 * bnd tfxt lbyout.  Tiis is tif fundbmfntbl dlbss for rfndfring
 * 2-dimfnsionbl sibpfs, tfxt bnd imbgfs on tif  Jbvb(tm) plbtform.
 *
 * <i2>Coordinbtf Spbdfs</i2>
 * All doordinbtfs pbssfd to b <dodf>Grbpiids2D</dodf> objfdt brf spfdififd
 * in b dfvidf-indfpfndfnt doordinbtf systfm dbllfd Usfr Spbdf, wiidi is
 * usfd by bpplidbtions.  Tif <dodf>Grbpiids2D</dodf> objfdt dontbins
 * bn {@link AffinfTrbnsform} objfdt bs pbrt of its rfndfring stbtf
 * tibt dffinfs iow to donvfrt doordinbtfs from usfr spbdf to
 * dfvidf-dfpfndfnt doordinbtfs in Dfvidf Spbdf.
 * <p>
 * Coordinbtfs in dfvidf spbdf usublly rfffr to individubl dfvidf pixfls
 * bnd brf blignfd on tif infinitfly tiin gbps bftwffn tifsf pixfls.
 * Somf <dodf>Grbpiids2D</dodf> objfdts dbn bf usfd to dbpturf rfndfring
 * opfrbtions for storbgf into b grbpiids mftbfilf for plbybbdk on b
 * dondrftf dfvidf of unknown piysidbl rfsolution bt b lbtfr timf.  Sindf
 * tif rfsolution migit not bf known wifn tif rfndfring opfrbtions brf
 * dbpturfd, tif <dodf>Grbpiids2D</dodf> <dodf>Trbnsform</dodf> is sft up
 * to trbnsform usfr doordinbtfs to b virtubl dfvidf spbdf tibt
 * bpproximbtfs tif fxpfdtfd rfsolution of tif tbrgft dfvidf. Furtifr
 * trbnsformbtions migit nffd to bf bpplifd bt plbybbdk timf if tif
 * fstimbtf is indorrfdt.
 * <p>
 * Somf of tif opfrbtions pfrformfd by tif rfndfring bttributf objfdts
 * oddur in tif dfvidf spbdf, but bll <dodf>Grbpiids2D</dodf> mftiods tbkf
 * usfr spbdf doordinbtfs.
 * <p>
 * Evfry <dodf>Grbpiids2D</dodf> objfdt is bssodibtfd witi b tbrgft tibt
 * dffinfs wifrf rfndfring tbkfs plbdf. A
 * {@link GrbpiidsConfigurbtion} objfdt dffinfs tif dibrbdtfristids
 * of tif rfndfring tbrgft, sudi bs pixfl formbt bnd rfsolution.
 * Tif sbmf rfndfring tbrgft is usfd tirougiout tif liff of b
 * <dodf>Grbpiids2D</dodf> objfdt.
 * <p>
 * Wifn drfbting b <dodf>Grbpiids2D</dodf> objfdt,  tif
 * <dodf>GrbpiidsConfigurbtion</dodf>
 * spfdififs tif <b nbmf="dfftrbnsform">dffbult trbnsform</b> for
 * tif tbrgft of tif <dodf>Grbpiids2D</dodf> (b
 * {@link Componfnt} or {@link Imbgf}).  Tiis dffbult trbnsform mbps tif
 * usfr spbdf doordinbtf systfm to sdrffn bnd printfr dfvidf doordinbtfs
 * sudi tibt tif origin mbps to tif uppfr lfft ibnd dornfr of tif
 * tbrgft rfgion of tif dfvidf witi indrfbsing X doordinbtfs fxtfnding
 * to tif rigit bnd indrfbsing Y doordinbtfs fxtfnding downwbrd.
 * Tif sdbling of tif dffbult trbnsform is sft to idfntity for tiosf dfvidfs
 * tibt brf dlosf to 72 dpi, sudi bs sdrffn dfvidfs.
 * Tif sdbling of tif dffbult trbnsform is sft to bpproximbtfly 72 usfr
 * spbdf doordinbtfs pfr squbrf indi for iigi rfsolution dfvidfs, sudi bs
 * printfrs.  For imbgf bufffrs, tif dffbult trbnsform is tif
 * <dodf>Idfntity</dodf> trbnsform.
 *
 * <i2>Rfndfring Prodfss</i2>
 * Tif Rfndfring Prodfss dbn bf brokfn down into four pibsfs tibt brf
 * dontrollfd by tif <dodf>Grbpiids2D</dodf> rfndfring bttributfs.
 * Tif rfndfrfr dbn optimizf mbny of tifsf stfps, fitifr by dbdiing tif
 * rfsults for futurf dblls, by dollbpsing multiplf virtubl stfps into
 * b singlf opfrbtion, or by rfdognizing vbrious bttributfs bs dommon
 * simplf dbsfs tibt dbn bf fliminbtfd by modifying otifr pbrts of tif
 * opfrbtion.
 * <p>
 * Tif stfps in tif rfndfring prodfss brf:
 * <ol>
 * <li>
 * Dftfrminf wibt to rfndfr.
 * <li>
 * Constrbin tif rfndfring opfrbtion to tif durrfnt <dodf>Clip</dodf>.
 * Tif <dodf>Clip</dodf> is spfdififd by b {@link Sibpf} in usfr
 * spbdf bnd is dontrollfd by tif progrbm using tif vbrious dlip
 * mbnipulbtion mftiods of <dodf>Grbpiids</dodf> bnd
 * <dodf>Grbpiids2D</dodf>.  Tiis <i>usfr dlip</i>
 * is trbnsformfd into dfvidf spbdf by tif durrfnt
 * <dodf>Trbnsform</dodf> bnd dombinfd witi tif
 * <i>dfvidf dlip</i>, wiidi is dffinfd by tif visibility of windows bnd
 * dfvidf fxtfnts.  Tif dombinbtion of tif usfr dlip bnd dfvidf dlip
 * dffinfs tif <i>dompositf dlip</i>, wiidi dftfrminfs tif finbl dlipping
 * rfgion.  Tif usfr dlip is not modififd by tif rfndfring
 * systfm to rfflfdt tif rfsulting dompositf dlip.
 * <li>
 * Dftfrminf wibt dolors to rfndfr.
 * <li>
 * Apply tif dolors to tif dfstinbtion drbwing surfbdf using tif durrfnt
 * {@link Compositf} bttributf in tif <dodf>Grbpiids2D</dodf> dontfxt.
 * </ol>
 * <br>
 * Tif tirff typfs of rfndfring opfrbtions, blong witi dftbils of fbdi
 * of tifir pbrtidulbr rfndfring prodfssfs brf:
 * <ol>
 * <li>
 * <b><b nbmf="rfndfrsibpf"><dodf>Sibpf</dodf> opfrbtions</b></b>
 * <ol>
 * <li>
 * If tif opfrbtion is b <dodf>drbw(Sibpf)</dodf> opfrbtion, tifn
 * tif  {@link Strokf#drfbtfStrokfdSibpf(Sibpf) drfbtfStrokfdSibpf}
 * mftiod on tif durrfnt {@link Strokf} bttributf in tif
 * <dodf>Grbpiids2D</dodf> dontfxt is usfd to donstrudt b nfw
 * <dodf>Sibpf</dodf> objfdt tibt dontbins tif outlinf of tif spfdififd
 * <dodf>Sibpf</dodf>.
 * <li>
 * Tif <dodf>Sibpf</dodf> is trbnsformfd from usfr spbdf to dfvidf spbdf
 * using tif durrfnt <dodf>Trbnsform</dodf>
 * in tif <dodf>Grbpiids2D</dodf> dontfxt.
 * <li>
 * Tif outlinf of tif <dodf>Sibpf</dodf> is fxtrbdtfd using tif
 * {@link Sibpf#gftPbtiItfrbtor(AffinfTrbnsform) gftPbtiItfrbtor} mftiod of
 * <dodf>Sibpf</dodf>, wiidi rfturns b
 * {@link jbvb.bwt.gfom.PbtiItfrbtor PbtiItfrbtor}
 * objfdt tibt itfrbtfs blong tif boundbry of tif <dodf>Sibpf</dodf>.
 * <li>
 * If tif <dodf>Grbpiids2D</dodf> objfdt dbnnot ibndlf tif durvfd sfgmfnts
 * tibt tif <dodf>PbtiItfrbtor</dodf> objfdt rfturns tifn it dbn dbll tif
 * bltfrnbtf
 * {@link Sibpf#gftPbtiItfrbtor(AffinfTrbnsform, doublf) gftPbtiItfrbtor}
 * mftiod of <dodf>Sibpf</dodf>, wiidi flbttfns tif <dodf>Sibpf</dodf>.
 * <li>
 * Tif durrfnt {@link Pbint} in tif <dodf>Grbpiids2D</dodf> dontfxt
 * is qufrifd for b {@link PbintContfxt}, wiidi spfdififs tif
 * dolors to rfndfr in dfvidf spbdf.
 * </ol>
 * <li>
 * <b><b nbmf=rfndfrtfxt>Tfxt opfrbtions</b></b>
 * <ol>
 * <li>
 * Tif following stfps brf usfd to dftfrminf tif sft of glypis rfquirfd
 * to rfndfr tif indidbtfd <dodf>String</dodf>:
 * <ol>
 * <li>
 * If tif brgumfnt is b <dodf>String</dodf>, tifn tif durrfnt
 * <dodf>Font</dodf> in tif <dodf>Grbpiids2D</dodf> dontfxt is bskfd to
 * donvfrt tif Unidodf dibrbdtfrs in tif <dodf>String</dodf> into b sft of
 * glypis for prfsfntbtion witi wibtfvfr bbsid lbyout bnd sibping
 * blgoritims tif font implfmfnts.
 * <li>
 * If tif brgumfnt is bn
 * {@link AttributfdCibrbdtfrItfrbtor},
 * tif itfrbtor is bskfd to donvfrt itsflf to b
 * {@link jbvb.bwt.font.TfxtLbyout TfxtLbyout}
 * using its fmbfddfd font bttributfs. Tif <dodf>TfxtLbyout</dodf>
 * implfmfnts morf sopiistidbtfd glypi lbyout blgoritims tibt
 * pfrform Unidodf bi-dirfdtionbl lbyout bdjustmfnts butombtidblly
 * for multiplf fonts of difffring writing dirfdtions.
  * <li>
 * If tif brgumfnt is b
 * {@link GlypiVfdtor}, tifn tif
 * <dodf>GlypiVfdtor</dodf> objfdt blrfbdy dontbins tif bppropribtf
 * font-spfdifid glypi dodfs witi fxplidit doordinbtfs for tif position of
 * fbdi glypi.
 * </ol>
 * <li>
 * Tif durrfnt <dodf>Font</dodf> is qufrifd to obtbin outlinfs for tif
 * indidbtfd glypis.  Tifsf outlinfs brf trfbtfd bs sibpfs in usfr spbdf
 * rflbtivf to tif position of fbdi glypi tibt wbs dftfrminfd in stfp 1.
 * <li>
 * Tif dibrbdtfr outlinfs brf fillfd bs indidbtfd bbovf
 * undfr <b irff="#rfndfrsibpf"><dodf>Sibpf</dodf> opfrbtions</b>.
 * <li>
 * Tif durrfnt <dodf>Pbint</dodf> is qufrifd for b
 * <dodf>PbintContfxt</dodf>, wiidi spfdififs
 * tif dolors to rfndfr in dfvidf spbdf.
 * </ol>
 * <li>
 * <b><b nbmf= rfndfringimbgf><dodf>Imbgf</dodf> Opfrbtions</b></b>
 * <ol>
 * <li>
 * Tif rfgion of intfrfst is dffinfd by tif bounding box of tif sourdf
 * <dodf>Imbgf</dodf>.
 * Tiis bounding box is spfdififd in Imbgf Spbdf, wiidi is tif
 * <dodf>Imbgf</dodf> objfdt's lodbl doordinbtf systfm.
 * <li>
 * If bn <dodf>AffinfTrbnsform</dodf> is pbssfd to
 * {@link #drbwImbgf(jbvb.bwt.Imbgf, jbvb.bwt.gfom.AffinfTrbnsform, jbvb.bwt.imbgf.ImbgfObsfrvfr) drbwImbgf(Imbgf, AffinfTrbnsform, ImbgfObsfrvfr)},
 * tif <dodf>AffinfTrbnsform</dodf> is usfd to trbnsform tif bounding
 * box from imbgf spbdf to usfr spbdf. If no <dodf>AffinfTrbnsform</dodf>
 * is supplifd, tif bounding box is trfbtfd bs if it is blrfbdy in usfr spbdf.
 * <li>
 * Tif bounding box of tif sourdf <dodf>Imbgf</dodf> is trbnsformfd from usfr
 * spbdf into dfvidf spbdf using tif durrfnt <dodf>Trbnsform</dodf>.
 * Notf tibt tif rfsult of trbnsforming tif bounding box dofs not
 * nfdfssbrily rfsult in b rfdtbngulbr rfgion in dfvidf spbdf.
 * <li>
 * Tif <dodf>Imbgf</dodf> objfdt dftfrminfs wibt dolors to rfndfr,
 * sbmplfd bddording to tif sourdf to dfstinbtion
 * doordinbtf mbpping spfdififd by tif durrfnt <dodf>Trbnsform</dodf> bnd tif
 * optionbl imbgf trbnsform.
 * </ol>
 * </ol>
 *
 * <i2>Dffbult Rfndfring Attributfs</i2>
 * Tif dffbult vblufs for tif <dodf>Grbpiids2D</dodf> rfndfring bttributfs brf:
 * <dl>
 * <dt><i><dodf>Pbint</dodf></i>
 * <dd>Tif dolor of tif <dodf>Componfnt</dodf>.
 * <dt><i><dodf>Font</dodf></i>
 * <dd>Tif <dodf>Font</dodf> of tif <dodf>Componfnt</dodf>.
 * <dt><i><dodf>Strokf</dodf></i>
 * <dd>A squbrf pfn witi b linfwidti of 1, no dbsiing, mitfr sfgmfnt joins
 * bnd squbrf fnd dbps.
 * <dt><i><dodf>Trbnsform</dodf></i>
 * <dd>Tif
 * {@link GrbpiidsConfigurbtion#gftDffbultTrbnsform() gftDffbultTrbnsform}
 * for tif <dodf>GrbpiidsConfigurbtion</dodf> of tif <dodf>Componfnt</dodf>.
 * <dt><i><dodf>Compositf</dodf></i>
 * <dd>Tif {@link AlpibCompositf#SRC_OVER} rulf.
 * <dt><i><dodf>Clip</dodf></i>
 * <dd>No rfndfring <dodf>Clip</dodf>, tif output is dlippfd to tif
 * <dodf>Componfnt</dodf>.
 * </dl>
 *
 * <i2>Rfndfring Compbtibility Issufs</i2>
 * Tif JDK(tm) 1.1 rfndfring modfl is bbsfd on b pixflizbtion modfl
 * tibt spfdififs tibt doordinbtfs
 * brf infinitfly tiin, lying bftwffn tif pixfls.  Drbwing opfrbtions brf
 * pfrformfd using b onf-pixfl widf pfn tibt fills tif
 * pixfl bflow bnd to tif rigit of tif bndior point on tif pbti.
 * Tif JDK 1.1 rfndfring modfl is donsistfnt witi tif
 * dbpbbilitifs of most of tif fxisting dlbss of plbtform
 * rfndfrfrs tibt nffd  to rfsolvf intfgfr doordinbtfs to b
 * disdrftf pfn tibt must fbll domplftfly on b spfdififd numbfr of pixfls.
 * <p>
 * Tif Jbvb 2D(tm) (Jbvb(tm) 2 plbtform) API supports bntiblibsing rfndfrfrs.
 * A pfn witi b widti of onf pixfl dofs not nffd to fbll
 * domplftfly on pixfl N bs opposfd to pixfl N+1.  Tif pfn dbn fbll
 * pbrtiblly on boti pixfls. It is not nfdfssbry to dioosf b bibs
 * dirfdtion for b widf pfn sindf tif blfnding tibt oddurs blong tif
 * pfn trbvfrsbl fdgfs mbkfs tif sub-pixfl position of tif pfn
 * visiblf to tif usfr.  On tif otifr ibnd, wifn bntiblibsing is
 * turnfd off by sftting tif
 * {@link RfndfringHints#KEY_ANTIALIASING KEY_ANTIALIASING} iint kfy
 * to tif
 * {@link RfndfringHints#VALUE_ANTIALIAS_OFF VALUE_ANTIALIAS_OFF}
 * iint vbluf, tif rfndfrfr migit nffd
 * to bpply b bibs to dftfrminf wiidi pixfl to modify wifn tif pfn
 * is strbddling b pixfl boundbry, sudi bs wifn it is drbwn
 * blong bn intfgfr doordinbtf in dfvidf spbdf.  Wiilf tif dbpbbilitifs
 * of bn bntiblibsing rfndfrfr mbkf it no longfr nfdfssbry for tif
 * rfndfring modfl to spfdify b bibs for tif pfn, it is dfsirbblf for tif
 * bntiblibsing bnd non-bntiblibsing rfndfrfrs to pfrform similbrly for
 * tif dommon dbsfs of drbwing onf-pixfl widf iorizontbl bnd vfrtidbl
 * linfs on tif sdrffn.  To fnsurf tibt turning on bntiblibsing by
 * sftting tif
 * {@link RfndfringHints#KEY_ANTIALIASING KEY_ANTIALIASING} iint
 * kfy to
 * {@link RfndfringHints#VALUE_ANTIALIAS_ON VALUE_ANTIALIAS_ON}
 * dofs not dbusf sudi linfs to suddfnly bfdomf twidf bs widf bnd iblf
 * bs opbquf, it is dfsirbblf to ibvf tif modfl spfdify b pbti for sudi
 * linfs so tibt tify domplftfly dovfr b pbrtidulbr sft of pixfls to iflp
 * indrfbsf tifir drispnfss.
 * <p>
 * Jbvb 2D API mbintbins dompbtibility witi JDK 1.1 rfndfring
 * bfibvior, sudi tibt lfgbdy opfrbtions bnd fxisting rfndfrfr
 * bfibvior is undibngfd undfr Jbvb 2D API.  Lfgbdy
 * mftiods tibt mbp onto gfnfrbl <dodf>drbw</dodf> bnd
 * <dodf>fill</dodf> mftiods brf dffinfd, wiidi dlfbrly indidbtfs
 * iow <dodf>Grbpiids2D</dodf> fxtfnds <dodf>Grbpiids</dodf> bbsfd
 * on sfttings of <dodf>Strokf</dodf> bnd <dodf>Trbnsform</dodf>
 * bttributfs bnd rfndfring iints.  Tif dffinition
 * pfrforms idfntidblly undfr dffbult bttributf sfttings.
 * For fxbmplf, tif dffbult <dodf>Strokf</dodf> is b
 * <dodf>BbsidStrokf</dodf> witi b widti of 1 bnd no dbsiing bnd tif
 * dffbult Trbnsform for sdrffn drbwing is bn Idfntity trbnsform.
 * <p>
 * Tif following two rulfs providf prfdidtbblf rfndfring bfibvior wiftifr
 * blibsing or bntiblibsing is bfing usfd.
 * <ul>
 * <li> Dfvidf doordinbtfs brf dffinfd to bf bftwffn dfvidf pixfls wiidi
 * bvoids bny indonsistfnt rfsults bftwffn blibsfd bnd bntiblibsfd
 * rfndfring.  If doordinbtfs wfrf dffinfd to bf bt b pixfl's dfntfr, somf
 * of tif pixfls dovfrfd by b sibpf, sudi bs b rfdtbnglf, would only bf
 * iblf dovfrfd.
 * Witi blibsfd rfndfring, tif iblf dovfrfd pixfls would fitifr bf
 * rfndfrfd insidf tif sibpf or outsidf tif sibpf.  Witi bnti-blibsfd
 * rfndfring, tif pixfls on tif fntirf fdgf of tif sibpf would bf iblf
 * dovfrfd.  On tif otifr ibnd, sindf doordinbtfs brf dffinfd to bf
 * bftwffn pixfls, b sibpf likf b rfdtbnglf would ibvf no iblf dovfrfd
 * pixfls, wiftifr or not it is rfndfrfd using bntiblibsing.
 * <li> Linfs bnd pbtis strokfd using tif <dodf>BbsidStrokf</dodf>
 * objfdt mby bf "normblizfd" to providf donsistfnt rfndfring of tif
 * outlinfs wifn positionfd bt vbrious points on tif drbwbblf bnd
 * wiftifr drbwn witi blibsfd or bntiblibsfd rfndfring.  Tiis
 * normblizbtion prodfss is dontrollfd by tif
 * {@link RfndfringHints#KEY_STROKE_CONTROL KEY_STROKE_CONTROL} iint.
 * Tif fxbdt normblizbtion blgoritim is not spfdififd, but tif gobls
 * of tiis normblizbtion brf to fnsurf tibt linfs brf rfndfrfd witi
 * donsistfnt visubl bppfbrbndf rfgbrdlfss of iow tify fbll on tif
 * pixfl grid bnd to promotf morf solid iorizontbl bnd vfrtidbl
 * linfs in bntiblibsfd modf so tibt tify rfsfmblf tifir non-bntiblibsfd
 * dountfrpbrts morf dlosfly.  A typidbl normblizbtion stfp migit
 * promotf bntiblibsfd linf fndpoints to pixfl dfntfrs to rfdudf tif
 * bmount of blfnding or bdjust tif subpixfl positioning of
 * non-bntiblibsfd linfs so tibt tif flobting point linf widtis
 * round to fvfn or odd pixfl dounts witi fqubl likfliiood.  Tiis
 * prodfss dbn movf fndpoints by up to iblf b pixfl (usublly towbrds
 * positivf infinity blong boti bxfs) to promotf tifsf donsistfnt
 * rfsults.
 * </ul>
 * <p>
 * Tif following dffinitions of gfnfrbl lfgbdy mftiods
 * pfrform idfntidblly to prfviously spfdififd bfibvior undfr dffbult
 * bttributf sfttings:
 * <ul>
 * <li>
 * For <dodf>fill</dodf> opfrbtions, indluding <dodf>fillRfdt</dodf>,
 * <dodf>fillRoundRfdt</dodf>, <dodf>fillOvbl</dodf>,
 * <dodf>fillArd</dodf>, <dodf>fillPolygon</dodf>, bnd
 * <dodf>dlfbrRfdt</dodf>, {@link #fill(Sibpf) fill} dbn now bf dbllfd
 * witi tif dfsirfd <dodf>Sibpf</dodf>.  For fxbmplf, wifn filling b
 * rfdtbnglf:
 * <prf>
 * fill(nfw Rfdtbnglf(x, y, w, i));
 * </prf>
 * is dbllfd.
 *
 * <li>
 * Similbrly, for drbw opfrbtions, indluding <dodf>drbwLinf</dodf>,
 * <dodf>drbwRfdt</dodf>, <dodf>drbwRoundRfdt</dodf>,
 * <dodf>drbwOvbl</dodf>, <dodf>drbwArd</dodf>, <dodf>drbwPolylinf</dodf>,
 * bnd <dodf>drbwPolygon</dodf>, {@link #drbw(Sibpf) drbw} dbn now bf
 * dbllfd witi tif dfsirfd <dodf>Sibpf</dodf>.  For fxbmplf, wifn drbwing b
 * rfdtbnglf:
 * <prf>
 * drbw(nfw Rfdtbnglf(x, y, w, i));
 * </prf>
 * is dbllfd.
 *
 * <li>
 * Tif <dodf>drbw3DRfdt</dodf> bnd <dodf>fill3DRfdt</dodf> mftiods wfrf
 * implfmfntfd in tfrms of tif <dodf>drbwLinf</dodf> bnd
 * <dodf>fillRfdt</dodf> mftiods in tif <dodf>Grbpiids</dodf> dlbss wiidi
 * would prfdidbtf tifir bfibvior upon tif durrfnt <dodf>Strokf</dodf>
 * bnd <dodf>Pbint</dodf> objfdts in b <dodf>Grbpiids2D</dodf> dontfxt.
 * Tiis dlbss ovfrridfs tiosf implfmfntbtions witi vfrsions tibt usf
 * tif durrfnt <dodf>Color</dodf> fxdlusivfly, ovfrriding tif durrfnt
 * <dodf>Pbint</dodf> bnd wiidi usfs <dodf>fillRfdt</dodf> to dfsdribf
 * tif fxbdt sbmf bfibvior bs tif prffxisting mftiods rfgbrdlfss of tif
 * sftting of tif durrfnt <dodf>Strokf</dodf>.
 * </ul>
 * Tif <dodf>Grbpiids</dodf> dlbss dffinfs only tif <dodf>sftColor</dodf>
 * mftiod to dontrol tif dolor to bf pbintfd.  Sindf tif Jbvb 2D API fxtfnds
 * tif <dodf>Color</dodf> objfdt to implfmfnt tif nfw <dodf>Pbint</dodf>
 * intfrfbdf, tif fxisting
 * <dodf>sftColor</dodf> mftiod is now b donvfnifndf mftiod for sftting tif
 * durrfnt <dodf>Pbint</dodf> bttributf to b <dodf>Color</dodf> objfdt.
 * <dodf>sftColor(d)</dodf> is fquivblfnt to <dodf>sftPbint(d)</dodf>.
 * <p>
 * Tif <dodf>Grbpiids</dodf> dlbss dffinfs two mftiods for dontrolling
 * iow dolors brf bpplifd to tif dfstinbtion.
 * <ol>
 * <li>
 * Tif <dodf>sftPbintModf</dodf> mftiod is implfmfntfd bs b donvfnifndf
 * mftiod to sft tif dffbult <dodf>Compositf</dodf>, fquivblfnt to
 * <dodf>sftCompositf(nfw AlpibCompositf.SrdOvfr)</dodf>.
 * <li>
 * Tif <dodf>sftXORModf(Color xordolor)</dodf> mftiod is implfmfntfd
 * bs b donvfnifndf mftiod to sft b spfdibl <dodf>Compositf</dodf> objfdt tibt
 * ignorfs tif <dodf>Alpib</dodf> domponfnts of sourdf dolors bnd sfts tif
 * dfstinbtion dolor to tif vbluf:
 * <prf>
 * dstpixfl = (PixflOf(srddolor) ^ PixflOf(xordolor) ^ dstpixfl);
 * </prf>
 * </ol>
 *
 * @butior Jim Grbibm
 * @sff jbvb.bwt.RfndfringHints
 */
publid bbstrbdt dlbss Grbpiids2D fxtfnds Grbpiids {

    /**
     * Construdts b nfw <dodf>Grbpiids2D</dodf> objfdt.  Sindf
     * <dodf>Grbpiids2D</dodf> is bn bbstrbdt dlbss, bnd sindf it must bf
     * dustomizfd by subdlbssfs for difffrfnt output dfvidfs,
     * <dodf>Grbpiids2D</dodf> objfdts dbnnot bf drfbtfd dirfdtly.
     * Instfbd, <dodf>Grbpiids2D</dodf> objfdts must bf obtbinfd from bnotifr
     * <dodf>Grbpiids2D</dodf> objfdt, drfbtfd by b
     * <dodf>Componfnt</dodf>, or obtbinfd from imbgfs sudi bs
     * {@link BufffrfdImbgf} objfdts.
     * @sff jbvb.bwt.Componfnt#gftGrbpiids
     * @sff jbvb.bwt.Grbpiids#drfbtf
     */
    protfdtfd Grbpiids2D() {
    }

    /**
     * Drbws b 3-D iigiligitfd outlinf of tif spfdififd rfdtbnglf.
     * Tif fdgfs of tif rfdtbnglf brf iigiligitfd so tibt tify
     * bppfbr to bf bfvflfd bnd lit from tif uppfr lfft dornfr.
     * <p>
     * Tif dolors usfd for tif iigiligiting ffffdt brf dftfrminfd
     * bbsfd on tif durrfnt dolor.
     * Tif rfsulting rfdtbnglf dovfrs bn brfb tibt is
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * by <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.  Tiis mftiod
     * usfs tif durrfnt <dodf>Color</dodf> fxdlusivfly bnd ignorfs
     * tif durrfnt <dodf>Pbint</dodf>.
     * @pbrbm x tif x doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm y tif y doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm widti tif widti of tif rfdtbnglf to bf drbwn.
     * @pbrbm ifigit tif ifigit of tif rfdtbnglf to bf drbwn.
     * @pbrbm rbisfd b boolfbn tibt dftfrminfs wiftifr tif rfdtbnglf
     *                      bppfbrs to bf rbisfd bbovf tif surfbdf
     *                      or sunk into tif surfbdf.
     * @sff         jbvb.bwt.Grbpiids#fill3DRfdt
     */
    publid void drbw3DRfdt(int x, int y, int widti, int ifigit,
                           boolfbn rbisfd) {
        Pbint p = gftPbint();
        Color d = gftColor();
        Color brigitfr = d.brigitfr();
        Color dbrkfr = d.dbrkfr();

        sftColor(rbisfd ? brigitfr : dbrkfr);
        //drbwLinf(x, y, x, y + ifigit);
        fillRfdt(x, y, 1, ifigit + 1);
        //drbwLinf(x + 1, y, x + widti - 1, y);
        fillRfdt(x + 1, y, widti - 1, 1);
        sftColor(rbisfd ? dbrkfr : brigitfr);
        //drbwLinf(x + 1, y + ifigit, x + widti, y + ifigit);
        fillRfdt(x + 1, y + ifigit, widti, 1);
        //drbwLinf(x + widti, y, x + widti, y + ifigit - 1);
        fillRfdt(x + widti, y, 1, ifigit);
        sftPbint(p);
    }

    /**
     * Pbints b 3-D iigiligitfd rfdtbnglf fillfd witi tif durrfnt dolor.
     * Tif fdgfs of tif rfdtbnglf brf iigiligitfd so tibt it bppfbrs
     * bs if tif fdgfs wfrf bfvflfd bnd lit from tif uppfr lfft dornfr.
     * Tif dolors usfd for tif iigiligiting ffffdt bnd for filling brf
     * dftfrminfd from tif durrfnt <dodf>Color</dodf>.  Tiis mftiod usfs
     * tif durrfnt <dodf>Color</dodf> fxdlusivfly bnd ignorfs tif durrfnt
     * <dodf>Pbint</dodf>.
     * @pbrbm x tif x doordinbtf of tif rfdtbnglf to bf fillfd.
     * @pbrbm y tif y doordinbtf of tif rfdtbnglf to bf fillfd.
     * @pbrbm       widti tif widti of tif rfdtbnglf to bf fillfd.
     * @pbrbm       ifigit tif ifigit of tif rfdtbnglf to bf fillfd.
     * @pbrbm       rbisfd b boolfbn vbluf tibt dftfrminfs wiftifr tif
     *                      rfdtbnglf bppfbrs to bf rbisfd bbovf tif surfbdf
     *                      or ftdifd into tif surfbdf.
     * @sff         jbvb.bwt.Grbpiids#drbw3DRfdt
     */
    publid void fill3DRfdt(int x, int y, int widti, int ifigit,
                           boolfbn rbisfd) {
        Pbint p = gftPbint();
        Color d = gftColor();
        Color brigitfr = d.brigitfr();
        Color dbrkfr = d.dbrkfr();

        if (!rbisfd) {
            sftColor(dbrkfr);
        } flsf if (p != d) {
            sftColor(d);
        }
        fillRfdt(x+1, y+1, widti-2, ifigit-2);
        sftColor(rbisfd ? brigitfr : dbrkfr);
        //drbwLinf(x, y, x, y + ifigit - 1);
        fillRfdt(x, y, 1, ifigit);
        //drbwLinf(x + 1, y, x + widti - 2, y);
        fillRfdt(x + 1, y, widti - 2, 1);
        sftColor(rbisfd ? dbrkfr : brigitfr);
        //drbwLinf(x + 1, y + ifigit - 1, x + widti - 1, y + ifigit - 1);
        fillRfdt(x + 1, y + ifigit - 1, widti - 1, 1);
        //drbwLinf(x + widti - 1, y, x + widti - 1, y + ifigit - 2);
        fillRfdt(x + widti - 1, y, 1, ifigit - 1);
        sftPbint(p);
    }

    /**
     * Strokfs tif outlinf of b <dodf>Sibpf</dodf> using tif sfttings of tif
     * durrfnt <dodf>Grbpiids2D</dodf> dontfxt.  Tif rfndfring bttributfs
     * bpplifd indludf tif <dodf>Clip</dodf>, <dodf>Trbnsform</dodf>,
     * <dodf>Pbint</dodf>, <dodf>Compositf</dodf> bnd
     * <dodf>Strokf</dodf> bttributfs.
     * @pbrbm s tif <dodf>Sibpf</dodf> to bf rfndfrfd
     * @sff #sftStrokf
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     * @sff #sftCompositf
     */
    publid bbstrbdt void drbw(Sibpf s);

    /**
     * Rfndfrs bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt <dodf>Trbnsform</dodf> in tif <dodf>Grbpiids2D</dodf>.
     * Tif spfdififd trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif <dodf>Grbpiids2D</dodf> dontfxt is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, bnd <dodf>Compositf</dodf> bttributfs.
     * Notf tibt no rfndfring is donf if tif spfdififd trbnsform is
     * noninvfrtiblf.
     * @pbrbm img tif spfdififd imbgf to bf rfndfrfd.
     *            Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm xform tif trbnsformbtion from imbgf spbdf into usfr spbdf
     * @pbrbm obs tif {@link ImbgfObsfrvfr}
     * to bf notififd bs morf of tif <dodf>Imbgf</dodf>
     * is donvfrtfd
     * @rfturn <dodf>truf</dodf> if tif <dodf>Imbgf</dodf> is
     * fully lobdfd bnd domplftfly rfndfrfd, or if it's null;
     * <dodf>fblsf</dodf> if tif <dodf>Imbgf</dodf> is still bfing lobdfd.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid bbstrbdt boolfbn drbwImbgf(Imbgf img,
                                      AffinfTrbnsform xform,
                                      ImbgfObsfrvfr obs);

    /**
     * Rfndfrs b <dodf>BufffrfdImbgf</dodf> tibt is
     * filtfrfd witi b
     * {@link BufffrfdImbgfOp}.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>
     * bnd <dodf>Compositf</dodf> bttributfs.  Tiis is fquivblfnt to:
     * <prf>
     * img1 = op.filtfr(img, null);
     * drbwImbgf(img1, nfw AffinfTrbnsform(1f,0f,0f,1f,x,y), null);
     * </prf>
     * @pbrbm op tif filtfr to bf bpplifd to tif imbgf bfforf rfndfring
     * @pbrbm img tif spfdififd <dodf>BufffrfdImbgf</dodf> to bf rfndfrfd.
     *            Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm x tif x doordinbtf of tif lodbtion in usfr spbdf wifrf
     * tif uppfr lfft dornfr of tif imbgf is rfndfrfd
     * @pbrbm y tif y doordinbtf of tif lodbtion in usfr spbdf wifrf
     * tif uppfr lfft dornfr of tif imbgf is rfndfrfd
     *
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid bbstrbdt void drbwImbgf(BufffrfdImbgf img,
                                   BufffrfdImbgfOp op,
                                   int x,
                                   int y);

    /**
     * Rfndfrs b {@link RfndfrfdImbgf},
     * bpplying b trbnsform from imbgf
     * spbdf into usfr spbdf bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt <dodf>Trbnsform</dodf> in tif <dodf>Grbpiids2D</dodf>.
     * Tif spfdififd trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif <dodf>Grbpiids2D</dodf> dontfxt is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, bnd <dodf>Compositf</dodf> bttributfs. Notf
     * tibt no rfndfring is donf if tif spfdififd trbnsform is
     * noninvfrtiblf.
     * @pbrbm img tif imbgf to bf rfndfrfd. Tiis mftiod dofs
     *            notiing if <dodf>img</dodf> is null.
     * @pbrbm xform tif trbnsformbtion from imbgf spbdf into usfr spbdf
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid bbstrbdt void drbwRfndfrfdImbgf(RfndfrfdImbgf img,
                                           AffinfTrbnsform xform);

    /**
     * Rfndfrs b
     * {@link RfndfrbblfImbgf},
     * bpplying b trbnsform from imbgf spbdf into usfr spbdf bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt <dodf>Trbnsform</dodf> in tif <dodf>Grbpiids2D</dodf>.
     * Tif spfdififd trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif <dodf>Grbpiids2D</dodf> dontfxt is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, bnd <dodf>Compositf</dodf> bttributfs. Notf
     * tibt no rfndfring is donf if tif spfdififd trbnsform is
     * noninvfrtiblf.
     *<p>
     * Rfndfring iints sft on tif <dodf>Grbpiids2D</dodf> objfdt migit
     * bf usfd in rfndfring tif <dodf>RfndfrbblfImbgf</dodf>.
     * If fxplidit dontrol is rfquirfd ovfr spfdifid iints rfdognizfd by b
     * spfdifid <dodf>RfndfrbblfImbgf</dodf>, or if knowlfdgf of wiidi iints
     * brf usfd is rfquirfd, tifn b <dodf>RfndfrfdImbgf</dodf> siould bf
     * obtbinfd dirfdtly from tif <dodf>RfndfrbblfImbgf</dodf>
     * bnd rfndfrfd using
     *{@link #drbwRfndfrfdImbgf(RfndfrfdImbgf, AffinfTrbnsform) drbwRfndfrfdImbgf}.
     * @pbrbm img tif imbgf to bf rfndfrfd. Tiis mftiod dofs
     *            notiing if <dodf>img</dodf> is null.
     * @pbrbm xform tif trbnsformbtion from imbgf spbdf into usfr spbdf
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     * @sff #drbwRfndfrfdImbgf
     */
    publid bbstrbdt void drbwRfndfrbblfImbgf(RfndfrbblfImbgf img,
                                             AffinfTrbnsform xform);

    /**
     * Rfndfrs tif tfxt of tif spfdififd <dodf>String</dodf>, using tif
     * durrfnt tfxt bttributf stbtf in tif <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in
     * tif Usfr Spbdf.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, <dodf>Pbint</dodf>, <dodf>Font</dodf> bnd
     * <dodf>Compositf</dodf> bttributfs.  For dibrbdtfrs in sdript
     * systfms sudi bs Hfbrfw bnd Arbbid, tif glypis dbn bf rfndfrfd from
     * rigit to lfft, in wiidi dbsf tif doordinbtf supplifd is tif
     * lodbtion of tif lfftmost dibrbdtfr on tif bbsflinf.
     * @pbrbm str tif string to bf rfndfrfd
     * @pbrbm x tif x doordinbtf of tif lodbtion wifrf tif
     * <dodf>String</dodf> siould bf rfndfrfd
     * @pbrbm y tif y doordinbtf of tif lodbtion wifrf tif
     * <dodf>String</dodf> siould bf rfndfrfd
     * @tirows NullPointfrExdfption if <dodf>str</dodf> is
     *         <dodf>null</dodf>
     * @sff         jbvb.bwt.Grbpiids#drbwBytfs
     * @sff         jbvb.bwt.Grbpiids#drbwCibrs
     * @sindf       1.0
     */
    publid bbstrbdt void drbwString(String str, int x, int y);

    /**
     * Rfndfrs tif tfxt spfdififd by tif spfdififd <dodf>String</dodf>,
     * using tif durrfnt tfxt bttributf stbtf in tif <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif bbsflinf of tif first dibrbdtfr is bt position
     * (<i>x</i>,&nbsp;<i>y</i>) in tif Usfr Spbdf.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, <dodf>Pbint</dodf>, <dodf>Font</dodf> bnd
     * <dodf>Compositf</dodf> bttributfs. For dibrbdtfrs in sdript systfms
     * sudi bs Hfbrfw bnd Arbbid, tif glypis dbn bf rfndfrfd from rigit to
     * lfft, in wiidi dbsf tif doordinbtf supplifd is tif lodbtion of tif
     * lfftmost dibrbdtfr on tif bbsflinf.
     * @pbrbm str tif <dodf>String</dodf> to bf rfndfrfd
     * @pbrbm x tif x doordinbtf of tif lodbtion wifrf tif
     * <dodf>String</dodf> siould bf rfndfrfd
     * @pbrbm y tif y doordinbtf of tif lodbtion wifrf tif
     * <dodf>String</dodf> siould bf rfndfrfd
     * @tirows NullPointfrExdfption if <dodf>str</dodf> is
     *         <dodf>null</dodf>
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff jbvb.bwt.Grbpiids#sftFont
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid bbstrbdt void drbwString(String str, flobt x, flobt y);

    /**
     * Rfndfrs tif tfxt of tif spfdififd itfrbtor bpplying its bttributfs
     * in bddordbndf witi tif spfdifidbtion of tif {@link TfxtAttributf} dlbss.
     * <p>
     * Tif bbsflinf of tif first dibrbdtfr is bt position
     * (<i>x</i>,&nbsp;<i>y</i>) in Usfr Spbdf.
     * For dibrbdtfrs in sdript systfms sudi bs Hfbrfw bnd Arbbid,
     * tif glypis dbn bf rfndfrfd from rigit to lfft, in wiidi dbsf tif
     * doordinbtf supplifd is tif lodbtion of tif lfftmost dibrbdtfr
     * on tif bbsflinf.
     * @pbrbm itfrbtor tif itfrbtor wiosf tfxt is to bf rfndfrfd
     * @pbrbm x tif x doordinbtf wifrf tif itfrbtor's tfxt is to bf
     * rfndfrfd
     * @pbrbm y tif y doordinbtf wifrf tif itfrbtor's tfxt is to bf
     * rfndfrfd
     * @tirows NullPointfrExdfption if <dodf>itfrbtor</dodf> is
     *         <dodf>null</dodf>
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid bbstrbdt void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                                    int x, int y);

    /**
     * Rfndfrs tif tfxt of tif spfdififd itfrbtor bpplying its bttributfs
     * in bddordbndf witi tif spfdifidbtion of tif {@link TfxtAttributf} dlbss.
     * <p>
     * Tif bbsflinf of tif first dibrbdtfr is bt position
     * (<i>x</i>,&nbsp;<i>y</i>) in Usfr Spbdf.
     * For dibrbdtfrs in sdript systfms sudi bs Hfbrfw bnd Arbbid,
     * tif glypis dbn bf rfndfrfd from rigit to lfft, in wiidi dbsf tif
     * doordinbtf supplifd is tif lodbtion of tif lfftmost dibrbdtfr
     * on tif bbsflinf.
     * @pbrbm itfrbtor tif itfrbtor wiosf tfxt is to bf rfndfrfd
     * @pbrbm x tif x doordinbtf wifrf tif itfrbtor's tfxt is to bf
     * rfndfrfd
     * @pbrbm y tif y doordinbtf wifrf tif itfrbtor's tfxt is to bf
     * rfndfrfd
     * @tirows NullPointfrExdfption if <dodf>itfrbtor</dodf> is
     *         <dodf>null</dodf>
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid bbstrbdt void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                                    flobt x, flobt y);

    /**
     * Rfndfrs tif tfxt of tif spfdififd
     * {@link GlypiVfdtor} using
     * tif <dodf>Grbpiids2D</dodf> dontfxt's rfndfring bttributfs.
     * Tif rfndfring bttributfs bpplifd indludf tif <dodf>Clip</dodf>,
     * <dodf>Trbnsform</dodf>, <dodf>Pbint</dodf>, bnd
     * <dodf>Compositf</dodf> bttributfs.  Tif <dodf>GlypiVfdtor</dodf>
     * spfdififs individubl glypis from b {@link Font}.
     * Tif <dodf>GlypiVfdtor</dodf> dbn blso dontbin tif glypi positions.
     * Tiis is tif fbstfst wby to rfndfr b sft of dibrbdtfrs to tif
     * sdrffn.
     * @pbrbm g tif <dodf>GlypiVfdtor</dodf> to bf rfndfrfd
     * @pbrbm x tif x position in Usfr Spbdf wifrf tif glypis siould
     * bf rfndfrfd
     * @pbrbm y tif y position in Usfr Spbdf wifrf tif glypis siould
     * bf rfndfrfd
     * @tirows NullPointfrExdfption if <dodf>g</dodf> is <dodf>null</dodf>.
     *
     * @sff jbvb.bwt.Font#drfbtfGlypiVfdtor
     * @sff jbvb.bwt.font.GlypiVfdtor
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #sftClip
     */
    publid bbstrbdt void drbwGlypiVfdtor(GlypiVfdtor g, flobt x, flobt y);

    /**
     * Fills tif intfrior of b <dodf>Sibpf</dodf> using tif sfttings of tif
     * <dodf>Grbpiids2D</dodf> dontfxt. Tif rfndfring bttributfs bpplifd
     * indludf tif <dodf>Clip</dodf>, <dodf>Trbnsform</dodf>,
     * <dodf>Pbint</dodf>, bnd <dodf>Compositf</dodf>.
     * @pbrbm s tif <dodf>Sibpf</dodf> to bf fillfd
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid bbstrbdt void fill(Sibpf s);

    /**
     * Cifdks wiftifr or not tif spfdififd <dodf>Sibpf</dodf> intfrsfdts
     * tif spfdififd {@link Rfdtbnglf}, wiidi is in dfvidf
     * spbdf. If <dodf>onStrokf</dodf> is fblsf, tiis mftiod difdks
     * wiftifr or not tif intfrior of tif spfdififd <dodf>Sibpf</dodf>
     * intfrsfdts tif spfdififd <dodf>Rfdtbnglf</dodf>.  If
     * <dodf>onStrokf</dodf> is <dodf>truf</dodf>, tiis mftiod difdks
     * wiftifr or not tif <dodf>Strokf</dodf> of tif spfdififd
     * <dodf>Sibpf</dodf> outlinf intfrsfdts tif spfdififd
     * <dodf>Rfdtbnglf</dodf>.
     * Tif rfndfring bttributfs tbkfn into bddount indludf tif
     * <dodf>Clip</dodf>, <dodf>Trbnsform</dodf>, bnd <dodf>Strokf</dodf>
     * bttributfs.
     * @pbrbm rfdt tif brfb in dfvidf spbdf to difdk for b iit
     * @pbrbm s tif <dodf>Sibpf</dodf> to difdk for b iit
     * @pbrbm onStrokf flbg usfd to dioosf bftwffn tfsting tif
     * strokfd or tif fillfd sibpf.  If tif flbg is <dodf>truf</dodf>, tif
     * <dodf>Strokf</dodf> outlinf is tfstfd.  If tif flbg is
     * <dodf>fblsf</dodf>, tif fillfd <dodf>Sibpf</dodf> is tfstfd.
     * @rfturn <dodf>truf</dodf> if tifrf is b iit; <dodf>fblsf</dodf>
     * otifrwisf.
     * @sff #sftStrokf
     * @sff #fill
     * @sff #drbw
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     */
    publid bbstrbdt boolfbn iit(Rfdtbnglf rfdt,
                                Sibpf s,
                                boolfbn onStrokf);

    /**
     * Rfturns tif dfvidf donfigurbtion bssodibtfd witi tiis
     * <dodf>Grbpiids2D</dodf>.
     * @rfturn tif dfvidf donfigurbtion of tiis <dodf>Grbpiids2D</dodf>.
     */
    publid bbstrbdt GrbpiidsConfigurbtion gftDfvidfConfigurbtion();

    /**
     * Sfts tif <dodf>Compositf</dodf> for tif <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif <dodf>Compositf</dodf> is usfd in bll drbwing mftiods sudi bs
     * <dodf>drbwImbgf</dodf>, <dodf>drbwString</dodf>, <dodf>drbw</dodf>,
     * bnd <dodf>fill</dodf>.  It spfdififs iow nfw pixfls brf to bf dombinfd
     * witi tif fxisting pixfls on tif grbpiids dfvidf during tif rfndfring
     * prodfss.
     * <p>If tiis <dodf>Grbpiids2D</dodf> dontfxt is drbwing to b
     * <dodf>Componfnt</dodf> on tif displby sdrffn bnd tif
     * <dodf>Compositf</dodf> is b dustom objfdt rbtifr tibn bn
     * instbndf of tif <dodf>AlpibCompositf</dodf> dlbss, bnd if
     * tifrf is b sfdurity mbnbgfr, its <dodf>difdkPfrmission</dodf>
     * mftiod is dbllfd witi bn <dodf>AWTPfrmission("rfbdDisplbyPixfls")</dodf>
     * pfrmission.
     * @tirows SfdurityExdfption
     *         if b dustom <dodf>Compositf</dodf> objfdt is bfing
     *         usfd to rfndfr to tif sdrffn bnd b sfdurity mbnbgfr
     *         is sft bnd its <dodf>difdkPfrmission</dodf> mftiod
     *         dofs not bllow tif opfrbtion.
     * @pbrbm domp tif <dodf>Compositf</dodf> objfdt to bf usfd for rfndfring
     * @sff jbvb.bwt.Grbpiids#sftXORModf
     * @sff jbvb.bwt.Grbpiids#sftPbintModf
     * @sff #gftCompositf
     * @sff AlpibCompositf
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.bwt.AWTPfrmission
     */
    publid bbstrbdt void sftCompositf(Compositf domp);

    /**
     * Sfts tif <dodf>Pbint</dodf> bttributf for tif
     * <dodf>Grbpiids2D</dodf> dontfxt.  Cblling tiis mftiod
     * witi b <dodf>null</dodf> <dodf>Pbint</dodf> objfdt dofs
     * not ibvf bny ffffdt on tif durrfnt <dodf>Pbint</dodf> bttributf
     * of tiis <dodf>Grbpiids2D</dodf>.
     * @pbrbm pbint tif <dodf>Pbint</dodf> objfdt to bf usfd to gfnfrbtf
     * dolor during tif rfndfring prodfss, or <dodf>null</dodf>
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #gftPbint
     * @sff GrbdifntPbint
     * @sff TfxturfPbint
     */
    publid bbstrbdt void sftPbint( Pbint pbint );

    /**
     * Sfts tif <dodf>Strokf</dodf> for tif <dodf>Grbpiids2D</dodf> dontfxt.
     * @pbrbm s tif <dodf>Strokf</dodf> objfdt to bf usfd to strokf b
     * <dodf>Sibpf</dodf> during tif rfndfring prodfss
     * @sff BbsidStrokf
     * @sff #gftStrokf
     */
    publid bbstrbdt void sftStrokf(Strokf s);

    /**
     * Sfts tif vbluf of b singlf prfffrfndf for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd ovfrbll
     * timf/qublity trbdf-off in tif rfndfring prodfss.  Rfffr to tif
     * <dodf>RfndfringHints</dodf> dlbss for dffinitions of somf dommon
     * kfys bnd vblufs.
     * @pbrbm iintKfy tif kfy of tif iint to bf sft.
     * @pbrbm iintVbluf tif vbluf indidbting prfffrfndfs for tif spfdififd
     * iint dbtfgory.
     * @sff #gftRfndfringHint(RfndfringHints.Kfy)
     * @sff RfndfringHints
     */
    publid bbstrbdt void sftRfndfringHint(Kfy iintKfy, Objfdt iintVbluf);

    /**
     * Rfturns tif vbluf of b singlf prfffrfndf for tif rfndfring blgoritims.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd ovfrbll
     * timf/qublity trbdf-off in tif rfndfring prodfss.  Rfffr to tif
     * <dodf>RfndfringHints</dodf> dlbss for dffinitions of somf dommon
     * kfys bnd vblufs.
     * @pbrbm iintKfy tif kfy dorrfsponding to tif iint to gft.
     * @rfturn bn objfdt rfprfsfnting tif vbluf for tif spfdififd iint kfy.
     * Somf of tif kfys bnd tifir bssodibtfd vblufs brf dffinfd in tif
     * <dodf>RfndfringHints</dodf> dlbss.
     * @sff RfndfringHints
     * @sff #sftRfndfringHint(RfndfringHints.Kfy, Objfdt)
     */
    publid bbstrbdt Objfdt gftRfndfringHint(Kfy iintKfy);

    /**
     * Rfplbdfs tif vblufs of bll prfffrfndfs for tif rfndfring
     * blgoritims witi tif spfdififd <dodf>iints</dodf>.
     * Tif fxisting vblufs for bll rfndfring iints brf disdbrdfd bnd
     * tif nfw sft of known iints bnd vblufs brf initiblizfd from tif
     * spfdififd {@link Mbp} objfdt.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * Rfffr to tif <dodf>RfndfringHints</dodf> dlbss for dffinitions of
     * somf dommon kfys bnd vblufs.
     * @pbrbm iints tif rfndfring iints to bf sft
     * @sff #gftRfndfringHints
     * @sff RfndfringHints
     */
    publid bbstrbdt void sftRfndfringHints(Mbp<?,?> iints);

    /**
     * Sfts tif vblufs of bn brbitrbry numbfr of prfffrfndfs for tif
     * rfndfring blgoritims.
     * Only vblufs for tif rfndfring iints tibt brf prfsfnt in tif
     * spfdififd <dodf>Mbp</dodf> objfdt brf modififd.
     * All otifr prfffrfndfs not prfsfnt in tif spfdififd
     * objfdt brf lfft unmodififd.
     * Hint dbtfgorifs indludf dontrols for rfndfring qublity bnd
     * ovfrbll timf/qublity trbdf-off in tif rfndfring prodfss.
     * Rfffr to tif <dodf>RfndfringHints</dodf> dlbss for dffinitions of
     * somf dommon kfys bnd vblufs.
     * @pbrbm iints tif rfndfring iints to bf sft
     * @sff RfndfringHints
     */
    publid bbstrbdt void bddRfndfringHints(Mbp<?,?> iints);

    /**
     * Gfts tif prfffrfndfs for tif rfndfring blgoritims.  Hint dbtfgorifs
     * indludf dontrols for rfndfring qublity bnd ovfrbll timf/qublity
     * trbdf-off in tif rfndfring prodfss.
     * Rfturns bll of tif iint kfy/vbluf pbirs tibt wfrf fvfr spfdififd in
     * onf opfrbtion.  Rfffr to tif
     * <dodf>RfndfringHints</dodf> dlbss for dffinitions of somf dommon
     * kfys bnd vblufs.
     * @rfturn b rfffrfndf to bn instbndf of <dodf>RfndfringHints</dodf>
     * tibt dontbins tif durrfnt prfffrfndfs.
     * @sff RfndfringHints
     * @sff #sftRfndfringHints(Mbp)
     */
    publid bbstrbdt RfndfringHints gftRfndfringHints();

    /**
     * Trbnslbtfs tif origin of tif <dodf>Grbpiids2D</dodf> dontfxt to tif
     * point (<i>x</i>,&nbsp;<i>y</i>) in tif durrfnt doordinbtf systfm.
     * Modififs tif <dodf>Grbpiids2D</dodf> dontfxt so tibt its nfw origin
     * dorrfsponds to tif point (<i>x</i>,&nbsp;<i>y</i>) in tif
     * <dodf>Grbpiids2D</dodf> dontfxt's formfr doordinbtf systfm.  All
     * doordinbtfs usfd in subsfqufnt rfndfring opfrbtions on tiis grbpiids
     * dontfxt brf rflbtivf to tiis nfw origin.
     * @pbrbm  x tif spfdififd x doordinbtf
     * @pbrbm  y tif spfdififd y doordinbtf
     * @sindf   1.0
     */
    publid bbstrbdt void trbnslbtf(int x, int y);

    /**
     * Condbtfnbtfs tif durrfnt
     * <dodf>Grbpiids2D</dodf> <dodf>Trbnsform</dodf>
     * witi b trbnslbtion trbnsform.
     * Subsfqufnt rfndfring is trbnslbtfd by tif spfdififd
     * distbndf rflbtivf to tif prfvious position.
     * Tiis is fquivblfnt to dblling trbnsform(T), wifrf T is bn
     * <dodf>AffinfTrbnsform</dodf> rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </prf>
     * @pbrbm tx tif distbndf to trbnslbtf blong tif x-bxis
     * @pbrbm ty tif distbndf to trbnslbtf blong tif y-bxis
     */
    publid bbstrbdt void trbnslbtf(doublf tx, doublf ty);

    /**
     * Condbtfnbtfs tif durrfnt <dodf>Grbpiids2D</dodf>
     * <dodf>Trbnsform</dodf> witi b rotbtion trbnsform.
     * Subsfqufnt rfndfring is rotbtfd by tif spfdififd rbdibns rflbtivf
     * to tif prfvious origin.
     * Tiis is fquivblfnt to dblling <dodf>trbnsform(R)</dodf>, wifrf R is bn
     * <dodf>AffinfTrbnsform</dodf> rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   dos(tiftb)    -sin(tiftb)    0   ]
     *          [   sin(tiftb)     dos(tiftb)    0   ]
     *          [       0              0         1   ]
     * </prf>
     * Rotbting witi b positivf bnglf tiftb rotbtfs points on tif positivf
     * x bxis towbrd tif positivf y bxis.
     * @pbrbm tiftb tif bnglf of rotbtion in rbdibns
     */
    publid bbstrbdt void rotbtf(doublf tiftb);

    /**
     * Condbtfnbtfs tif durrfnt <dodf>Grbpiids2D</dodf>
     * <dodf>Trbnsform</dodf> witi b trbnslbtfd rotbtion
     * trbnsform.  Subsfqufnt rfndfring is trbnsformfd by b trbnsform
     * wiidi is donstrudtfd by trbnslbting to tif spfdififd lodbtion,
     * rotbting by tif spfdififd rbdibns, bnd trbnslbting bbdk by tif sbmf
     * bmount bs tif originbl trbnslbtion.  Tiis is fquivblfnt to tif
     * following sfqufndf of dblls:
     * <prf>
     *          trbnslbtf(x, y);
     *          rotbtf(tiftb);
     *          trbnslbtf(-x, -y);
     * </prf>
     * Rotbting witi b positivf bnglf tiftb rotbtfs points on tif positivf
     * x bxis towbrd tif positivf y bxis.
     * @pbrbm tiftb tif bnglf of rotbtion in rbdibns
     * @pbrbm x tif x doordinbtf of tif origin of tif rotbtion
     * @pbrbm y tif y doordinbtf of tif origin of tif rotbtion
     */
    publid bbstrbdt void rotbtf(doublf tiftb, doublf x, doublf y);

    /**
     * Condbtfnbtfs tif durrfnt <dodf>Grbpiids2D</dodf>
     * <dodf>Trbnsform</dodf> witi b sdbling trbnsformbtion
     * Subsfqufnt rfndfring is rfsizfd bddording to tif spfdififd sdbling
     * fbdtors rflbtivf to tif prfvious sdbling.
     * Tiis is fquivblfnt to dblling <dodf>trbnsform(S)</dodf>, wifrf S is bn
     * <dodf>AffinfTrbnsform</dodf> rfprfsfntfd by tif following mbtrix:
     * <prf>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </prf>
     * @pbrbm sx tif bmount by wiidi X doordinbtfs in subsfqufnt
     * rfndfring opfrbtions brf multiplifd rflbtivf to prfvious
     * rfndfring opfrbtions.
     * @pbrbm sy tif bmount by wiidi Y doordinbtfs in subsfqufnt
     * rfndfring opfrbtions brf multiplifd rflbtivf to prfvious
     * rfndfring opfrbtions.
     */
    publid bbstrbdt void sdblf(doublf sx, doublf sy);

    /**
     * Condbtfnbtfs tif durrfnt <dodf>Grbpiids2D</dodf>
     * <dodf>Trbnsform</dodf> witi b sifbring trbnsform.
     * Subsfqufnt rfndfrings brf sifbrfd by tif spfdififd
     * multiplifr rflbtivf to tif prfvious position.
     * Tiis is fquivblfnt to dblling <dodf>trbnsform(SH)</dodf>, wifrf SH
     * is bn <dodf>AffinfTrbnsform</dodf> rfprfsfntfd by tif following
     * mbtrix:
     * <prf>
     *          [   1   six   0   ]
     *          [  siy   1    0   ]
     *          [   0    0    1   ]
     * </prf>
     * @pbrbm six tif multiplifr by wiidi doordinbtfs brf siiftfd in
     * tif positivf X bxis dirfdtion bs b fundtion of tifir Y doordinbtf
     * @pbrbm siy tif multiplifr by wiidi doordinbtfs brf siiftfd in
     * tif positivf Y bxis dirfdtion bs b fundtion of tifir X doordinbtf
     */
    publid bbstrbdt void sifbr(doublf six, doublf siy);

    /**
     * Composfs bn <dodf>AffinfTrbnsform</dodf> objfdt witi tif
     * <dodf>Trbnsform</dodf> in tiis <dodf>Grbpiids2D</dodf> bddording
     * to tif rulf lbst-spfdififd-first-bpplifd.  If tif durrfnt
     * <dodf>Trbnsform</dodf> is Cx, tif rfsult of domposition
     * witi Tx is b nfw <dodf>Trbnsform</dodf> Cx'.  Cx' bfdomfs tif
     * durrfnt <dodf>Trbnsform</dodf> for tiis <dodf>Grbpiids2D</dodf>.
     * Trbnsforming b point p by tif updbtfd <dodf>Trbnsform</dodf> Cx' is
     * fquivblfnt to first trbnsforming p by Tx bnd tifn trbnsforming
     * tif rfsult by tif originbl <dodf>Trbnsform</dodf> Cx.  In otifr
     * words, Cx'(p) = Cx(Tx(p)).  A dopy of tif Tx is mbdf, if nfdfssbry,
     * so furtifr modifidbtions to Tx do not bfffdt rfndfring.
     * @pbrbm Tx tif <dodf>AffinfTrbnsform</dodf> objfdt to bf domposfd witi
     * tif durrfnt <dodf>Trbnsform</dodf>
     * @sff #sftTrbnsform
     * @sff AffinfTrbnsform
     */
    publid bbstrbdt void trbnsform(AffinfTrbnsform Tx);

    /**
     * Ovfrwritfs tif Trbnsform in tif <dodf>Grbpiids2D</dodf> dontfxt.
     * WARNING: Tiis mftiod siould <b>nfvfr</b> bf usfd to bpply b nfw
     * doordinbtf trbnsform on top of bn fxisting trbnsform bfdbusf tif
     * <dodf>Grbpiids2D</dodf> migit blrfbdy ibvf b trbnsform tibt is
     * nffdfd for otifr purposfs, sudi bs rfndfring Swing
     * domponfnts or bpplying b sdbling trbnsformbtion to bdjust for tif
     * rfsolution of b printfr.
     * <p>To bdd b doordinbtf trbnsform, usf tif
     * <dodf>trbnsform</dodf>, <dodf>rotbtf</dodf>, <dodf>sdblf</dodf>,
     * or <dodf>sifbr</dodf> mftiods.  Tif <dodf>sftTrbnsform</dodf>
     * mftiod is intfndfd only for rfstoring tif originbl
     * <dodf>Grbpiids2D</dodf> trbnsform bftfr rfndfring, bs siown in tiis
     * fxbmplf:
     * <prf>
     * // Gft tif durrfnt trbnsform
     * AffinfTrbnsform sbvfAT = g2.gftTrbnsform();
     * // Pfrform trbnsformbtion
     * g2d.trbnsform(...);
     * // Rfndfr
     * g2d.drbw(...);
     * // Rfstorf originbl trbnsform
     * g2d.sftTrbnsform(sbvfAT);
     * </prf>
     *
     * @pbrbm Tx tif <dodf>AffinfTrbnsform</dodf> tibt wbs rftrifvfd
     *           from tif <dodf>gftTrbnsform</dodf> mftiod
     * @sff #trbnsform
     * @sff #gftTrbnsform
     * @sff AffinfTrbnsform
     */
    publid bbstrbdt void sftTrbnsform(AffinfTrbnsform Tx);

    /**
     * Rfturns b dopy of tif durrfnt <dodf>Trbnsform</dodf> in tif
     * <dodf>Grbpiids2D</dodf> dontfxt.
     * @rfturn tif durrfnt <dodf>AffinfTrbnsform</dodf> in tif
     *             <dodf>Grbpiids2D</dodf> dontfxt.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     */
    publid bbstrbdt AffinfTrbnsform gftTrbnsform();

    /**
     * Rfturns tif durrfnt <dodf>Pbint</dodf> of tif
     * <dodf>Grbpiids2D</dodf> dontfxt.
     * @rfturn tif durrfnt <dodf>Grbpiids2D</dodf> <dodf>Pbint</dodf>,
     * wiidi dffinfs b dolor or pbttfrn.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     */
    publid bbstrbdt Pbint gftPbint();

    /**
     * Rfturns tif durrfnt <dodf>Compositf</dodf> in tif
     * <dodf>Grbpiids2D</dodf> dontfxt.
     * @rfturn tif durrfnt <dodf>Grbpiids2D</dodf> <dodf>Compositf</dodf>,
     *              wiidi dffinfs b dompositing stylf.
     * @sff #sftCompositf
     */
    publid bbstrbdt Compositf gftCompositf();

    /**
     * Sfts tif bbdkground dolor for tif <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif bbdkground dolor is usfd for dlfbring b rfgion.
     * Wifn b <dodf>Grbpiids2D</dodf> is donstrudtfd for b
     * <dodf>Componfnt</dodf>, tif bbdkground dolor is
     * inifritfd from tif <dodf>Componfnt</dodf>. Sftting tif bbdkground dolor
     * in tif <dodf>Grbpiids2D</dodf> dontfxt only bfffdts tif subsfqufnt
     * <dodf>dlfbrRfdt</dodf> dblls bnd not tif bbdkground dolor of tif
     * <dodf>Componfnt</dodf>.  To dibngf tif bbdkground
     * of tif <dodf>Componfnt</dodf>, usf bppropribtf mftiods of
     * tif <dodf>Componfnt</dodf>.
     * @pbrbm dolor tif bbdkground dolor tibt is usfd in
     * subsfqufnt dblls to <dodf>dlfbrRfdt</dodf>
     * @sff #gftBbdkground
     * @sff jbvb.bwt.Grbpiids#dlfbrRfdt
     */
    publid bbstrbdt void sftBbdkground(Color dolor);

    /**
     * Rfturns tif bbdkground dolor usfd for dlfbring b rfgion.
     * @rfturn tif durrfnt <dodf>Grbpiids2D</dodf> <dodf>Color</dodf>,
     * wiidi dffinfs tif bbdkground dolor.
     * @sff #sftBbdkground
     */
    publid bbstrbdt Color gftBbdkground();

    /**
     * Rfturns tif durrfnt <dodf>Strokf</dodf> in tif
     * <dodf>Grbpiids2D</dodf> dontfxt.
     * @rfturn tif durrfnt <dodf>Grbpiids2D</dodf> <dodf>Strokf</dodf>,
     *                 wiidi dffinfs tif linf stylf.
     * @sff #sftStrokf
     */
    publid bbstrbdt Strokf gftStrokf();

    /**
     * Intfrsfdts tif durrfnt <dodf>Clip</dodf> witi tif intfrior of tif
     * spfdififd <dodf>Sibpf</dodf> bnd sfts tif <dodf>Clip</dodf> to tif
     * rfsulting intfrsfdtion.  Tif spfdififd <dodf>Sibpf</dodf> is
     * trbnsformfd witi tif durrfnt <dodf>Grbpiids2D</dodf>
     * <dodf>Trbnsform</dodf> bfforf bfing intfrsfdtfd witi tif durrfnt
     * <dodf>Clip</dodf>.  Tiis mftiod is usfd to mbkf tif durrfnt
     * <dodf>Clip</dodf> smbllfr.
     * To mbkf tif <dodf>Clip</dodf> lbrgfr, usf <dodf>sftClip</dodf>.
     * Tif <i>usfr dlip</i> modififd by tiis mftiod is indfpfndfnt of tif
     * dlipping bssodibtfd witi dfvidf bounds bnd visibility.  If no dlip ibs
     * prfviously bffn sft, or if tif dlip ibs bffn dlfbrfd using
     * {@link Grbpiids#sftClip(Sibpf) sftClip} witi b <dodf>null</dodf>
     * brgumfnt, tif spfdififd <dodf>Sibpf</dodf> bfdomfs tif nfw
     * usfr dlip.
     * @pbrbm s tif <dodf>Sibpf</dodf> to bf intfrsfdtfd witi tif durrfnt
     *          <dodf>Clip</dodf>.  If <dodf>s</dodf> is <dodf>null</dodf>,
     *          tiis mftiod dlfbrs tif durrfnt <dodf>Clip</dodf>.
     */
     publid bbstrbdt void dlip(Sibpf s);

     /**
     * Gft tif rfndfring dontfxt of tif <dodf>Font</dodf> witiin tiis
     * <dodf>Grbpiids2D</dodf> dontfxt.
     * Tif {@link FontRfndfrContfxt}
     * fndbpsulbtfs bpplidbtion iints sudi bs bnti-blibsing bnd
     * frbdtionbl mftrids, bs wfll bs tbrgft dfvidf spfdifid informbtion
     * sudi bs dots-pfr-indi.  Tiis informbtion siould bf providfd by tif
     * bpplidbtion wifn using objfdts tibt pfrform typogrbpiidbl
     * formbtting, sudi bs <dodf>Font</dodf> bnd
     * <dodf>TfxtLbyout</dodf>.  Tiis informbtion siould blso bf providfd
     * by bpplidbtions tibt pfrform tifir own lbyout bnd nffd bddurbtf
     * mfbsurfmfnts of vbrious dibrbdtfristids of glypis sudi bs bdvbndf
     * bnd linf ifigit wifn vbrious rfndfring iints ibvf bffn bpplifd to
     * tif tfxt rfndfring.
     *
     * @rfturn b rfffrfndf to bn instbndf of FontRfndfrContfxt.
     * @sff jbvb.bwt.font.FontRfndfrContfxt
     * @sff jbvb.bwt.Font#drfbtfGlypiVfdtor
     * @sff jbvb.bwt.font.TfxtLbyout
     * @sindf     1.2
     */

    publid bbstrbdt FontRfndfrContfxt gftFontRfndfrContfxt();

}
