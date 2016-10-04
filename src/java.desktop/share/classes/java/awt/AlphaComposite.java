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

pbdkbgf jbvb.bwt;

import jbvb.bwt.imbgf.ColorModfl;
import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.jbvb2d.SunCompositfContfxt;

/**
 * Tif <dodf>AlpibCompositf</dodf> dlbss implfmfnts bbsid blpib
 * dompositing rulfs for dombining sourdf bnd dfstinbtion dolors
 * to bdiifvf blfnding bnd trbnspbrfndy ffffdts witi grbpiids bnd
 * imbgfs.
 * Tif spfdifid rulfs implfmfntfd by tiis dlbss brf tif bbsid sft
 * of 12 rulfs dfsdribfd in
 * T. Portfr bnd T. Duff, "Compositing Digitbl Imbgfs", SIGGRAPH 84,
 * 253-259.
 * Tif rfst of tiis dodumfntbtion bssumfs somf fbmilibrity witi tif
 * dffinitions bnd dondfpts outlinfd in tibt pbpfr.
 *
 * <p>
 * Tiis dlbss fxtfnds tif stbndbrd fqubtions dffinfd by Portfr bnd
 * Duff to indludf onf bdditionbl fbdtor.
 * An instbndf of tif <dodf>AlpibCompositf</dodf> dlbss dbn dontbin
 * bn blpib vbluf tibt is usfd to modify tif opbdity or dovfrbgf of
 * fvfry sourdf pixfl bfforf it is usfd in tif blfnding fqubtions.
 *
 * <p>
 * It is importbnt to notf tibt tif fqubtions dffinfd by tif Portfr
 * bnd Duff pbpfr brf bll dffinfd to opfrbtf on dolor domponfnts
 * tibt brf prfmultiplifd by tifir dorrfsponding blpib domponfnts.
 * Sindf tif <dodf>ColorModfl</dodf> bnd <dodf>Rbstfr</dodf> dlbssfs
 * bllow tif storbgf of pixfl dbtb in fitifr prfmultiplifd or
 * non-prfmultiplifd form, bll input dbtb must bf normblizfd into
 * prfmultiplifd form bfforf bpplying tif fqubtions bnd bll rfsults
 * migit nffd to bf bdjustfd bbdk to tif form rfquirfd by tif dfstinbtion
 * bfforf tif pixfl vblufs brf storfd.
 *
 * <p>
 * Also notf tibt tiis dlbss dffinfs only tif fqubtions
 * for dombining dolor bnd blpib vblufs in b purfly mbtifmbtidbl
 * sfnsf. Tif bddurbtf bpplidbtion of its fqubtions dfpfnds
 * on tif wby tif dbtb is rftrifvfd from its sourdfs bnd storfd
 * in its dfstinbtions.
 * Sff <b irff="#dbvfbts">Implfmfntbtion Cbvfbts</b>
 * for furtifr informbtion.
 *
 * <p>
 * Tif following fbdtors brf usfd in tif dfsdription of tif blfnding
 * fqubtion in tif Portfr bnd Duff pbpfr:
 *
 * <blodkquotf>
 * <tbblf summbry="lbyout">
 * <tr><ti blign=lfft>Fbdtor&nbsp;&nbsp;<ti blign=lfft>Dffinition
 * <tr><td><fm>A<sub>s</sub></fm><td>tif blpib domponfnt of tif sourdf pixfl
 * <tr><td><fm>C<sub>s</sub></fm><td>b dolor domponfnt of tif sourdf pixfl in prfmultiplifd form
 * <tr><td><fm>A<sub>d</sub></fm><td>tif blpib domponfnt of tif dfstinbtion pixfl
 * <tr><td><fm>C<sub>d</sub></fm><td>b dolor domponfnt of tif dfstinbtion pixfl in prfmultiplifd form
 * <tr><td><fm>F<sub>s</sub></fm><td>tif frbdtion of tif sourdf pixfl tibt dontributfs to tif output
 * <tr><td><fm>F<sub>d</sub></fm><td>tif frbdtion of tif dfstinbtion pixfl tibt dontributfs
 * to tif output
 * <tr><td><fm>A<sub>r</sub></fm><td>tif blpib domponfnt of tif rfsult
 * <tr><td><fm>C<sub>r</sub></fm><td>b dolor domponfnt of tif rfsult in prfmultiplifd form
 * </tbblf>
 * </blodkquotf>
 *
 * <p>
 * Using tifsf fbdtors, Portfr bnd Duff dffinf 12 wbys of dioosing
 * tif blfnding fbdtors <fm>F<sub>s</sub></fm> bnd <fm>F<sub>d</sub></fm> to
 * produdf fbdi of 12 dfsirbblf visubl ffffdts.
 * Tif fqubtions for dftfrmining <fm>F<sub>s</sub></fm> bnd <fm>F<sub>d</sub></fm>
 * brf givfn in tif dfsdriptions of tif 12 stbtid fiflds
 * tibt spfdify visubl ffffdts.
 * For fxbmplf,
 * tif dfsdription for
 * <b irff="#SRC_OVER"><dodf>SRC_OVER</dodf></b>
 * spfdififs tibt <fm>F<sub>s</sub></fm> = 1 bnd <fm>F<sub>d</sub></fm> = (1-<fm>A<sub>s</sub></fm>).
 * Ondf b sft of fqubtions for dftfrmining tif blfnding fbdtors is
 * known tify dbn tifn bf bpplifd to fbdi pixfl to produdf b rfsult
 * using tif following sft of fqubtions:
 *
 * <prf>
 *      <fm>F<sub>s</sub></fm> = <fm>f</fm>(<fm>A<sub>d</sub></fm>)
 *      <fm>F<sub>d</sub></fm> = <fm>f</fm>(<fm>A<sub>s</sub></fm>)
 *      <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*<fm>F<sub>s</sub></fm> + <fm>A<sub>d</sub></fm>*<fm>F<sub>d</sub></fm>
 *      <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*<fm>F<sub>s</sub></fm> + <fm>C<sub>d</sub></fm>*<fm>F<sub>d</sub></fm></prf>
 *
 * <p>
 * Tif following fbdtors will bf usfd to disduss our fxtfnsions to
 * tif blfnding fqubtion in tif Portfr bnd Duff pbpfr:
 *
 * <blodkquotf>
 * <tbblf summbry="lbyout">
 * <tr><ti blign=lfft>Fbdtor&nbsp;&nbsp;<ti blign=lfft>Dffinition
 * <tr><td><fm>C<sub>sr</sub></fm> <td>onf of tif rbw dolor domponfnts of tif sourdf pixfl
 * <tr><td><fm>C<sub>dr</sub></fm> <td>onf of tif rbw dolor domponfnts of tif dfstinbtion pixfl
 * <tr><td><fm>A<sub>bd</sub></fm>  <td>tif "fxtrb" blpib domponfnt from tif AlpibCompositf instbndf
 * <tr><td><fm>A<sub>sr</sub></fm> <td>tif rbw blpib domponfnt of tif sourdf pixfl
 * <tr><td><fm>A<sub>dr</sub></fm><td>tif rbw blpib domponfnt of tif dfstinbtion pixfl
 * <tr><td><fm>A<sub>df</sub></fm> <td>tif finbl blpib domponfnt storfd in tif dfstinbtion
 * <tr><td><fm>C<sub>df</sub></fm> <td>tif finbl rbw dolor domponfnt storfd in tif dfstinbtion
 * </tbblf>
 *</blodkquotf>
 *
 * <i3>Prfpbring Inputs</i3>
 *
 * <p>
 * Tif <dodf>AlpibCompositf</dodf> dlbss dffinfs bn bdditionbl blpib
 * vbluf tibt is bpplifd to tif sourdf blpib.
 * Tiis vbluf is bpplifd bs if bn implidit SRC_IN rulf wfrf first
 * bpplifd to tif sourdf pixfl bgbinst b pixfl witi tif indidbtfd
 * blpib by multiplying boti tif rbw sourdf blpib bnd tif rbw
 * sourdf dolors by tif blpib in tif <dodf>AlpibCompositf</dodf>.
 * Tiis lfbds to tif following fqubtion for produding tif blpib
 * usfd in tif Portfr bnd Duff blfnding fqubtion:
 *
 * <prf>
 *      <fm>A<sub>s</sub></fm> = <fm>A<sub>sr</sub></fm> * <fm>A<sub>bd</sub></fm> </prf>
 *
 * All of tif rbw sourdf dolor domponfnts nffd to bf multiplifd
 * by tif blpib in tif <dodf>AlpibCompositf</dodf> instbndf.
 * Additionblly, if tif sourdf wbs not in prfmultiplifd form
 * tifn tif dolor domponfnts blso nffd to bf multiplifd by tif
 * sourdf blpib.
 * Tius, tif fqubtion for produding tif sourdf dolor domponfnts
 * for tif Portfr bnd Duff fqubtion dfpfnds on wiftifr tif sourdf
 * pixfls brf prfmultiplifd or not:
 *
 * <prf>
 *      <fm>C<sub>s</sub></fm> = <fm>C<sub>sr</sub></fm> * <fm>A<sub>sr</sub></fm> * <fm>A<sub>bd</sub></fm>     (if sourdf is not prfmultiplifd)
 *      <fm>C<sub>s</sub></fm> = <fm>C<sub>sr</sub></fm> * <fm>A<sub>bd</sub></fm>           (if sourdf is prfmultiplifd) </prf>
 *
 * No bdjustmfnt nffds to bf mbdf to tif dfstinbtion blpib:
 *
 * <prf>
 *      <fm>A<sub>d</sub></fm> = <fm>A<sub>dr</sub></fm> </prf>
 *
 * <p>
 * Tif dfstinbtion dolor domponfnts nffd to bf bdjustfd only if
 * tify brf not in prfmultiplifd form:
 *
 * <prf>
 *      <fm>C<sub>d</sub></fm> = <fm>C<sub>dr</sub></fm> * <fm>A<sub>d</sub></fm>    (if dfstinbtion is not prfmultiplifd)
 *      <fm>C<sub>d</sub></fm> = <fm>C<sub>dr</sub></fm>         (if dfstinbtion is prfmultiplifd) </prf>
 *
 * <i3>Applying tif Blfnding Equbtion</i3>
 *
 * <p>
 * Tif bdjustfd <fm>A<sub>s</sub></fm>, <fm>A<sub>d</sub></fm>,
 * <fm>C<sub>s</sub></fm>, bnd <fm>C<sub>d</sub></fm> brf usfd in tif stbndbrd
 * Portfr bnd Duff fqubtions to dbldulbtf tif blfnding fbdtors
 * <fm>F<sub>s</sub></fm> bnd <fm>F<sub>d</sub></fm> bnd tifn tif rfsulting
 * prfmultiplifd domponfnts <fm>A<sub>r</sub></fm> bnd <fm>C<sub>r</sub></fm>.
 *
 * <i3>Prfpbring Rfsults</i3>
 *
 * <p>
 * Tif rfsults only nffd to bf bdjustfd if tify brf to bf storfd
 * bbdk into b dfstinbtion bufffr tibt iolds dbtb tibt is not
 * prfmultiplifd, using tif following fqubtions:
 *
 * <prf>
 *      <fm>A<sub>df</sub></fm> = <fm>A<sub>r</sub></fm>
 *      <fm>C<sub>df</sub></fm> = <fm>C<sub>r</sub></fm>                 (if dfst is prfmultiplifd)
 *      <fm>C<sub>df</sub></fm> = <fm>C<sub>r</sub></fm> / <fm>A<sub>r</sub></fm>            (if dfst is not prfmultiplifd) </prf>
 *
 * Notf tibt sindf tif division is undffinfd if tif rfsulting blpib
 * is zfro, tif division in tibt dbsf is omittfd to bvoid tif "dividf
 * by zfro" bnd tif dolor domponfnts brf lfft bs
 * bll zfros.
 *
 * <i3>Pfrformbndf Considfrbtions</i3>
 *
 * <p>
 * For pfrformbndf rfbsons, it is prfffrbblf tibt
 * <dodf>Rbstfr</dodf> objfdts pbssfd to tif <dodf>domposf</dodf>
 * mftiod of b {@link CompositfContfxt} objfdt drfbtfd by tif
 * <dodf>AlpibCompositf</dodf> dlbss ibvf prfmultiplifd dbtb.
 * If fitifr tif sourdf <dodf>Rbstfr</dodf>
 * or tif dfstinbtion <dodf>Rbstfr</dodf>
 * is not prfmultiplifd, iowfvfr,
 * bppropribtf donvfrsions brf pfrformfd bfforf bnd bftfr tif dompositing
 * opfrbtion.
 *
 * <i3><b nbmf="dbvfbts">Implfmfntbtion Cbvfbts</b></i3>
 *
 * <ul>
 * <li>
 * Mbny sourdfs, sudi bs somf of tif opbquf imbgf typfs listfd
 * in tif <dodf>BufffrfdImbgf</dodf> dlbss, do not storf blpib vblufs
 * for tifir pixfls.  Sudi sourdfs supply bn blpib of 1.0 for
 * bll of tifir pixfls.
 *
 * <li>
 * Mbny dfstinbtions blso ibvf no plbdf to storf tif blpib vblufs
 * tibt rfsult from tif blfnding dbldulbtions pfrformfd by tiis dlbss.
 * Sudi dfstinbtions tius impliditly disdbrd tif rfsulting
 * blpib vblufs tibt tiis dlbss produdfs.
 * It is rfdommfndfd tibt sudi dfstinbtions siould trfbt tifir storfd
 * dolor vblufs bs non-prfmultiplifd bnd dividf tif rfsulting dolor
 * vblufs by tif rfsulting blpib vbluf bfforf storing tif dolor
 * vblufs bnd disdbrding tif blpib vbluf.
 *
 * <li>
 * Tif bddurbdy of tif rfsults dfpfnds on tif mbnnfr in wiidi pixfls
 * brf storfd in tif dfstinbtion.
 * An imbgf formbt tibt providfs bt lfbst 8 bits of storbgf pfr dolor
 * bnd blpib domponfnt is bt lfbst bdfqubtf for usf bs b dfstinbtion
 * for b sfqufndf of b ffw to b dozfn dompositing opfrbtions.
 * An imbgf formbt witi ffwfr tibn 8 bits of storbgf pfr domponfnt
 * is of limitfd usf for just onf or two dompositing opfrbtions
 * bfforf tif rounding frrors dominbtf tif rfsults.
 * An imbgf formbt
 * tibt dofs not sfpbrbtfly storf
 * dolor domponfnts is not b
 * good dbndidbtf for bny typf of trbnsludfnt blfnding.
 * For fxbmplf, <dodf>BufffrfdImbgf.TYPE_BYTE_INDEXED</dodf>
 * siould not bf usfd bs b dfstinbtion for b blfnding opfrbtion
 * bfdbusf fvfry opfrbtion
 * dbn introdudf lbrgf frrors, duf to
 * tif nffd to dioosf b pixfl from b limitfd pblfttf to mbtdi tif
 * rfsults of tif blfnding fqubtions.
 *
 * <li>
 * Nfbrly bll formbts storf pixfls bs disdrftf intfgfrs rbtifr tibn
 * tif flobting point vblufs usfd in tif rfffrfndf fqubtions bbovf.
 * Tif implfmfntbtion dbn fitifr sdblf tif intfgfr pixfl
 * vblufs into flobting point vblufs in tif rbngf 0.0 to 1.0 or
 * usf sligitly modififd vfrsions of tif fqubtions
 * tibt opfrbtf fntirfly in tif intfgfr dombin bnd yft produdf
 * bnblogous rfsults to tif rfffrfndf fqubtions.
 *
 * <p>
 * Typidblly tif intfgfr vblufs brf rflbtfd to tif flobting point
 * vblufs in sudi b wby tibt tif intfgfr 0 is fqubtfd
 * to tif flobting point vbluf 0.0 bnd tif intfgfr
 * 2^<fm>n</fm>-1 (wifrf <fm>n</fm> is tif numbfr of bits
 * in tif rfprfsfntbtion) is fqubtfd to 1.0.
 * For 8-bit rfprfsfntbtions, tiis mfbns tibt 0x00
 * rfprfsfnts 0.0 bnd 0xff rfprfsfnts
 * 1.0.
 *
 * <li>
 * Tif intfrnbl implfmfntbtion dbn bpproximbtf somf of tif fqubtions
 * bnd it dbn blso fliminbtf somf stfps to bvoid unnfdfssbry opfrbtions.
 * For fxbmplf, donsidfr b disdrftf intfgfr imbgf witi non-prfmultiplifd
 * blpib vblufs tibt usfs 8 bits pfr domponfnt for storbgf.
 * Tif storfd vblufs for b
 * nfbrly trbnspbrfnt dbrkfnfd rfd migit bf:
 *
 * <prf>
 *    (A, R, G, B) = (0x01, 0xb0, 0x00, 0x00)</prf>
 *
 * <p>
 * If intfgfr mbti wfrf bfing usfd bnd tiis vbluf wfrf bfing
 * dompositfd in
 * <b irff="#SRC"><dodf>SRC</dodf></b>
 * modf witi no fxtrb blpib, tifn tif mbti would
 * indidbtf tibt tif rfsults wfrf (in intfgfr formbt):
 *
 * <prf>
 *    (A, R, G, B) = (0x01, 0x01, 0x00, 0x00)</prf>
 *
 * <p>
 * Notf tibt tif intfrmfdibtf vblufs, wiidi brf blwbys in prfmultiplifd
 * form, would only bllow tif intfgfr rfd domponfnt to bf fitifr 0x00
 * or 0x01.  Wifn wf try to storf tiis rfsult bbdk into b dfstinbtion
 * tibt is not prfmultiplifd, dividing out tif blpib will givf us
 * vfry ffw dioidfs for tif non-prfmultiplifd rfd vbluf.
 * In tiis dbsf bn implfmfntbtion tibt pfrforms tif mbti in intfgfr
 * spbdf witiout siortduts is likfly to fnd up witi tif finbl pixfl
 * vblufs of:
 *
 * <prf>
 *    (A, R, G, B) = (0x01, 0xff, 0x00, 0x00)</prf>
 *
 * <p>
 * (Notf tibt 0x01 dividfd by 0x01 givfs you 1.0, wiidi is fquivblfnt
 * to tif vbluf 0xff in bn 8-bit storbgf formbt.)
 *
 * <p>
 * Altfrnbtfly, bn implfmfntbtion tibt usfs flobting point mbti
 * migit produdf morf bddurbtf rfsults bnd fnd up rfturning to tif
 * originbl pixfl vbluf witi littlf, if bny, roundoff frror.
 * Or, bn implfmfntbtion using intfgfr mbti migit dfdidf tibt sindf
 * tif fqubtions boil down to b virtubl NOP on tif dolor vblufs
 * if pfrformfd in b flobting point spbdf, it dbn trbnsffr tif
 * pixfl untoudifd to tif dfstinbtion bnd bvoid bll tif mbti fntirfly.
 *
 * <p>
 * Tifsf implfmfntbtions bll bttfmpt to ionor tif
 * sbmf fqubtions, but usf difffrfnt trbdfoffs of intfgfr bnd
 * flobting point mbti bnd rfdudfd or full fqubtions.
 * To bddount for sudi difffrfndfs, it is probbbly bfst to
 * fxpfdt only tibt tif prfmultiplifd form of tif rfsults to
 * mbtdi bftwffn implfmfntbtions bnd imbgf formbts.  In tiis
 * dbsf boti bnswfrs, fxprfssfd in prfmultiplifd form would
 * fqubtf to:
 *
 * <prf>
 *    (A, R, G, B) = (0x01, 0x01, 0x00, 0x00)</prf>
 *
 * <p>
 * bnd tius tify would bll mbtdi.
 *
 * <li>
 * Bfdbusf of tif tfdiniquf of simplifying tif fqubtions for
 * dbldulbtion fffidifndy, somf implfmfntbtions migit pfrform
 * difffrfntly wifn fndountfring rfsult blpib vblufs of 0.0
 * on b non-prfmultiplifd dfstinbtion.
 * Notf tibt tif simplifidbtion of rfmoving tif dividf by blpib
 * in tif dbsf of tif SRC rulf is tfdinidblly not vblid if tif
 * dfnominbtor (blpib) is 0.
 * But, sindf tif rfsults siould only bf fxpfdtfd to bf bddurbtf
 * wifn vifwfd in prfmultiplifd form, b rfsulting blpib of 0
 * fssfntiblly rfndfrs tif rfsulting dolor domponfnts irrflfvbnt
 * bnd so fxbdt bfibvior in tiis dbsf siould not bf fxpfdtfd.
 * </ul>
 * @sff Compositf
 * @sff CompositfContfxt
 */

publid finbl dlbss AlpibCompositf implfmfnts Compositf {
    /**
     * Boti tif dolor bnd tif blpib of tif dfstinbtion brf dlfbrfd
     * (Portfr-Duff Clfbr rulf).
     * Nfitifr tif sourdf nor tif dfstinbtion is usfd bs input.
     *<p>
     * <fm>F<sub>s</sub></fm> = 0 bnd <fm>F<sub>d</sub></fm> = 0, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = 0
     *  <fm>C<sub>r</sub></fm> = 0
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     CLEAR           = 1;

    /**
     * Tif sourdf is dopifd to tif dfstinbtion
     * (Portfr-Duff Sourdf rulf).
     * Tif dfstinbtion is not usfd bs input.
     *<p>
     * <fm>F<sub>s</sub></fm> = 1 bnd <fm>F<sub>d</sub></fm> = 0, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     SRC             = 2;

    /**
     * Tif dfstinbtion is lfft untoudifd
     * (Portfr-Duff Dfstinbtion rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = 0 bnd <fm>F<sub>d</sub></fm> = 1, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>d</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>d</sub></fm>
     *</prf>
     * @sindf 1.4
     */
    @Nbtivf publid stbtid finbl int     DST             = 9;
    // Notf tibt DST wbs bddfd in 1.4 so it is numbfrfd out of ordfr...

    /**
     * Tif sourdf is dompositfd ovfr tif dfstinbtion
     * (Portfr-Duff Sourdf Ovfr Dfstinbtion rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = 1 bnd <fm>F<sub>d</sub></fm> = (1-<fm>A<sub>s</sub></fm>), tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm> + <fm>A<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm> + <fm>C<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     SRC_OVER        = 3;

    /**
     * Tif dfstinbtion is dompositfd ovfr tif sourdf bnd
     * tif rfsult rfplbdfs tif dfstinbtion
     * (Portfr-Duff Dfstinbtion Ovfr Sourdf rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = (1-<fm>A<sub>d</sub></fm>) bnd <fm>F<sub>d</sub></fm> = 1, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>) + <fm>A<sub>d</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>) + <fm>C<sub>d</sub></fm>
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     DST_OVER        = 4;

    /**
     * Tif pbrt of tif sourdf lying insidf of tif dfstinbtion rfplbdfs
     * tif dfstinbtion
     * (Portfr-Duff Sourdf In Dfstinbtion rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = <fm>A<sub>d</sub></fm> bnd <fm>F<sub>d</sub></fm> = 0, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*<fm>A<sub>d</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*<fm>A<sub>d</sub></fm>
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     SRC_IN          = 5;

    /**
     * Tif pbrt of tif dfstinbtion lying insidf of tif sourdf
     * rfplbdfs tif dfstinbtion
     * (Portfr-Duff Dfstinbtion In Sourdf rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = 0 bnd <fm>F<sub>d</sub></fm> = <fm>A<sub>s</sub></fm>, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>d</sub></fm>*<fm>A<sub>s</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>d</sub></fm>*<fm>A<sub>s</sub></fm>
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     DST_IN          = 6;

    /**
     * Tif pbrt of tif sourdf lying outsidf of tif dfstinbtion
     * rfplbdfs tif dfstinbtion
     * (Portfr-Duff Sourdf Hfld Out By Dfstinbtion rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = (1-<fm>A<sub>d</sub></fm>) bnd <fm>F<sub>d</sub></fm> = 0, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>)
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>)
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     SRC_OUT         = 7;

    /**
     * Tif pbrt of tif dfstinbtion lying outsidf of tif sourdf
     * rfplbdfs tif dfstinbtion
     * (Portfr-Duff Dfstinbtion Hfld Out By Sourdf rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = 0 bnd <fm>F<sub>d</sub></fm> = (1-<fm>A<sub>s</sub></fm>), tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *</prf>
     */
    @Nbtivf publid stbtid finbl int     DST_OUT         = 8;

    // Rulf 9 is DST wiidi is dffinfd bbovf wifrf it fits into tif
    // list logidblly, rbtifr tibn numfridblly
    //
    // publid stbtid finbl int  DST             = 9;

    /**
     * Tif pbrt of tif sourdf lying insidf of tif dfstinbtion
     * is dompositfd onto tif dfstinbtion
     * (Portfr-Duff Sourdf Atop Dfstinbtion rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = <fm>A<sub>d</sub></fm> bnd <fm>F<sub>d</sub></fm> = (1-<fm>A<sub>s</sub></fm>), tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*<fm>A<sub>d</sub></fm> + <fm>A<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>) = <fm>A<sub>d</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*<fm>A<sub>d</sub></fm> + <fm>C<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *</prf>
     * @sindf 1.4
     */
    @Nbtivf publid stbtid finbl int     SRC_ATOP        = 10;

    /**
     * Tif pbrt of tif dfstinbtion lying insidf of tif sourdf
     * is dompositfd ovfr tif sourdf bnd rfplbdfs tif dfstinbtion
     * (Portfr-Duff Dfstinbtion Atop Sourdf rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = (1-<fm>A<sub>d</sub></fm>) bnd <fm>F<sub>d</sub></fm> = <fm>A<sub>s</sub></fm>, tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>) + <fm>A<sub>d</sub></fm>*<fm>A<sub>s</sub></fm> = <fm>A<sub>s</sub></fm>
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>) + <fm>C<sub>d</sub></fm>*<fm>A<sub>s</sub></fm>
     *</prf>
     * @sindf 1.4
     */
    @Nbtivf publid stbtid finbl int     DST_ATOP        = 11;

    /**
     * Tif pbrt of tif sourdf tibt lifs outsidf of tif dfstinbtion
     * is dombinfd witi tif pbrt of tif dfstinbtion tibt lifs outsidf
     * of tif sourdf
     * (Portfr-Duff Sourdf Xor Dfstinbtion rulf).
     *<p>
     * <fm>F<sub>s</sub></fm> = (1-<fm>A<sub>d</sub></fm>) bnd <fm>F<sub>d</sub></fm> = (1-<fm>A<sub>s</sub></fm>), tius:
     *<prf>
     *  <fm>A<sub>r</sub></fm> = <fm>A<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>) + <fm>A<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *  <fm>C<sub>r</sub></fm> = <fm>C<sub>s</sub></fm>*(1-<fm>A<sub>d</sub></fm>) + <fm>C<sub>d</sub></fm>*(1-<fm>A<sub>s</sub></fm>)
     *</prf>
     * @sindf 1.4
     */
    @Nbtivf publid stbtid finbl int     XOR             = 12;

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf CLEAR rulf
     * witi bn blpib of 1.0f.
     * @sff #CLEAR
     */
    publid stbtid finbl AlpibCompositf Clfbr    = nfw AlpibCompositf(CLEAR);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf SRC rulf
     * witi bn blpib of 1.0f.
     * @sff #SRC
     */
    publid stbtid finbl AlpibCompositf Srd      = nfw AlpibCompositf(SRC);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf DST rulf
     * witi bn blpib of 1.0f.
     * @sff #DST
     * @sindf 1.4
     */
    publid stbtid finbl AlpibCompositf Dst      = nfw AlpibCompositf(DST);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf SRC_OVER rulf
     * witi bn blpib of 1.0f.
     * @sff #SRC_OVER
     */
    publid stbtid finbl AlpibCompositf SrdOvfr  = nfw AlpibCompositf(SRC_OVER);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf DST_OVER rulf
     * witi bn blpib of 1.0f.
     * @sff #DST_OVER
     */
    publid stbtid finbl AlpibCompositf DstOvfr  = nfw AlpibCompositf(DST_OVER);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf SRC_IN rulf
     * witi bn blpib of 1.0f.
     * @sff #SRC_IN
     */
    publid stbtid finbl AlpibCompositf SrdIn    = nfw AlpibCompositf(SRC_IN);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf DST_IN rulf
     * witi bn blpib of 1.0f.
     * @sff #DST_IN
     */
    publid stbtid finbl AlpibCompositf DstIn    = nfw AlpibCompositf(DST_IN);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf SRC_OUT rulf
     * witi bn blpib of 1.0f.
     * @sff #SRC_OUT
     */
    publid stbtid finbl AlpibCompositf SrdOut   = nfw AlpibCompositf(SRC_OUT);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf DST_OUT rulf
     * witi bn blpib of 1.0f.
     * @sff #DST_OUT
     */
    publid stbtid finbl AlpibCompositf DstOut   = nfw AlpibCompositf(DST_OUT);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf SRC_ATOP rulf
     * witi bn blpib of 1.0f.
     * @sff #SRC_ATOP
     * @sindf 1.4
     */
    publid stbtid finbl AlpibCompositf SrdAtop  = nfw AlpibCompositf(SRC_ATOP);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf DST_ATOP rulf
     * witi bn blpib of 1.0f.
     * @sff #DST_ATOP
     * @sindf 1.4
     */
    publid stbtid finbl AlpibCompositf DstAtop  = nfw AlpibCompositf(DST_ATOP);

    /**
     * <dodf>AlpibCompositf</dodf> objfdt tibt implfmfnts tif opbquf XOR rulf
     * witi bn blpib of 1.0f.
     * @sff #XOR
     * @sindf 1.4
     */
    publid stbtid finbl AlpibCompositf Xor      = nfw AlpibCompositf(XOR);

    @Nbtivf privbtf stbtid finbl int MIN_RULE = CLEAR;
    @Nbtivf privbtf stbtid finbl int MAX_RULE = XOR;

    flobt fxtrbAlpib;
    int rulf;

    privbtf AlpibCompositf(int rulf) {
        tiis(rulf, 1.0f);
    }

    privbtf AlpibCompositf(int rulf, flobt blpib) {
        if (rulf < MIN_RULE || rulf > MAX_RULE) {
            tirow nfw IllfgblArgumfntExdfption("unknown dompositf rulf");
        }
        if (blpib >= 0.0f && blpib <= 1.0f) {
            tiis.rulf = rulf;
            tiis.fxtrbAlpib = blpib;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("blpib vbluf out of rbngf");
        }
    }

    /**
     * Crfbtfs bn <dodf>AlpibCompositf</dodf> objfdt witi tif spfdififd rulf.
     *
     * @pbrbm rulf tif dompositing rulf
     * @rfturn tif {@dodf AlpibCompositf} objfdt drfbtfd
     * @tirows IllfgblArgumfntExdfption if <dodf>rulf</dodf> is not onf of
     *         tif following:  {@link #CLEAR}, {@link #SRC}, {@link #DST},
     *         {@link #SRC_OVER}, {@link #DST_OVER}, {@link #SRC_IN},
     *         {@link #DST_IN}, {@link #SRC_OUT}, {@link #DST_OUT},
     *         {@link #SRC_ATOP}, {@link #DST_ATOP}, or {@link #XOR}
     */
    publid stbtid AlpibCompositf gftInstbndf(int rulf) {
        switdi (rulf) {
        dbsf CLEAR:
            rfturn Clfbr;
        dbsf SRC:
            rfturn Srd;
        dbsf DST:
            rfturn Dst;
        dbsf SRC_OVER:
            rfturn SrdOvfr;
        dbsf DST_OVER:
            rfturn DstOvfr;
        dbsf SRC_IN:
            rfturn SrdIn;
        dbsf DST_IN:
            rfturn DstIn;
        dbsf SRC_OUT:
            rfturn SrdOut;
        dbsf DST_OUT:
            rfturn DstOut;
        dbsf SRC_ATOP:
            rfturn SrdAtop;
        dbsf DST_ATOP:
            rfturn DstAtop;
        dbsf XOR:
            rfturn Xor;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("unknown dompositf rulf");
        }
    }

    /**
     * Crfbtfs bn <dodf>AlpibCompositf</dodf> objfdt witi tif spfdififd rulf bnd
     * tif donstbnt blpib to multiply witi tif blpib of tif sourdf.
     * Tif sourdf is multiplifd witi tif spfdififd blpib bfforf bfing dompositfd
     * witi tif dfstinbtion.
     *
     * @pbrbm rulf tif dompositing rulf
     * @pbrbm blpib tif donstbnt blpib to bf multiplifd witi tif blpib of
     * tif sourdf. <dodf>blpib</dodf> must bf b flobting point numbfr in tif
     * indlusivf rbngf [0.0,&nbsp;1.0].
     * @rfturn tif {@dodf AlpibCompositf} objfdt drfbtfd
     * @tirows IllfgblArgumfntExdfption if
     *         <dodf>blpib</dodf> is lfss tibn 0.0 or grfbtfr tibn 1.0, or if
     *         <dodf>rulf</dodf> is not onf of
     *         tif following:  {@link #CLEAR}, {@link #SRC}, {@link #DST},
     *         {@link #SRC_OVER}, {@link #DST_OVER}, {@link #SRC_IN},
     *         {@link #DST_IN}, {@link #SRC_OUT}, {@link #DST_OUT},
     *         {@link #SRC_ATOP}, {@link #DST_ATOP}, or {@link #XOR}
     */
    publid stbtid AlpibCompositf gftInstbndf(int rulf, flobt blpib) {
        if (blpib == 1.0f) {
            rfturn gftInstbndf(rulf);
        }
        rfturn nfw AlpibCompositf(rulf, blpib);
    }

    /**
     * Crfbtfs b dontfxt for tif dompositing opfrbtion.
     * Tif dontfxt dontbins stbtf tibt is usfd in pfrforming
     * tif dompositing opfrbtion.
     * @pbrbm srdColorModfl  tif {@link ColorModfl} of tif sourdf
     * @pbrbm dstColorModfl  tif <dodf>ColorModfl</dodf> of tif dfstinbtion
     * @rfturn tif <dodf>CompositfContfxt</dodf> objfdt to bf usfd to pfrform
     * dompositing opfrbtions.
     */
    publid CompositfContfxt drfbtfContfxt(ColorModfl srdColorModfl,
                                          ColorModfl dstColorModfl,
                                          RfndfringHints iints) {
        rfturn nfw SunCompositfContfxt(tiis, srdColorModfl, dstColorModfl);
    }

    /**
     * Rfturns tif blpib vbluf of tiis <dodf>AlpibCompositf</dodf>.  If tiis
     * <dodf>AlpibCompositf</dodf> dofs not ibvf bn blpib vbluf, 1.0 is rfturnfd.
     * @rfturn tif blpib vbluf of tiis <dodf>AlpibCompositf</dodf>.
     */
    publid flobt gftAlpib() {
        rfturn fxtrbAlpib;
    }

    /**
     * Rfturns tif dompositing rulf of tiis <dodf>AlpibCompositf</dodf>.
     * @rfturn tif dompositing rulf of tiis <dodf>AlpibCompositf</dodf>.
     */
    publid int gftRulf() {
        rfturn rulf;
    }

    /**
     * Rfturns b similbr <dodf>AlpibCompositf</dodf> objfdt tibt usfs
     * tif spfdififd dompositing rulf.
     * If tiis objfdt blrfbdy usfs tif spfdififd dompositing rulf,
     * tiis objfdt is rfturnfd.
     * @rfturn bn <dodf>AlpibCompositf</dodf> objfdt dfrivfd from
     * tiis objfdt tibt usfs tif spfdififd dompositing rulf.
     * @pbrbm rulf tif dompositing rulf
     * @tirows IllfgblArgumfntExdfption if
     *         <dodf>rulf</dodf> is not onf of
     *         tif following:  {@link #CLEAR}, {@link #SRC}, {@link #DST},
     *         {@link #SRC_OVER}, {@link #DST_OVER}, {@link #SRC_IN},
     *         {@link #DST_IN}, {@link #SRC_OUT}, {@link #DST_OUT},
     *         {@link #SRC_ATOP}, {@link #DST_ATOP}, or {@link #XOR}
     * @sindf 1.6
     */
    publid AlpibCompositf dfrivf(int rulf) {
        rfturn (tiis.rulf == rulf)
            ? tiis
            : gftInstbndf(rulf, tiis.fxtrbAlpib);
    }

    /**
     * Rfturns b similbr <dodf>AlpibCompositf</dodf> objfdt tibt usfs
     * tif spfdififd blpib vbluf.
     * If tiis objfdt blrfbdy ibs tif spfdififd blpib vbluf,
     * tiis objfdt is rfturnfd.
     * @rfturn bn <dodf>AlpibCompositf</dodf> objfdt dfrivfd from
     * tiis objfdt tibt usfs tif spfdififd blpib vbluf.
     * @pbrbm blpib tif donstbnt blpib to bf multiplifd witi tif blpib of
     * tif sourdf. <dodf>blpib</dodf> must bf b flobting point numbfr in tif
     * indlusivf rbngf [0.0,&nbsp;1.0].
     * @tirows IllfgblArgumfntExdfption if
     *         <dodf>blpib</dodf> is lfss tibn 0.0 or grfbtfr tibn 1.0
     * @sindf 1.6
     */
    publid AlpibCompositf dfrivf(flobt blpib) {
        rfturn (tiis.fxtrbAlpib == blpib)
            ? tiis
            : gftInstbndf(tiis.rulf, blpib);
    }

    /**
     * Rfturns tif ibsidodf for tiis dompositf.
     * @rfturn      b ibsi dodf for tiis dompositf.
     */
    publid int ibsiCodf() {
        rfturn (Flobt.flobtToIntBits(fxtrbAlpib) * 31 + rulf);
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd objfdt is fqubl to tiis
     * <dodf>AlpibCompositf</dodf>.
     * <p>
     * Tif rfsult is <dodf>truf</dodf> if bnd only if
     * tif brgumfnt is not <dodf>null</dodf> bnd is bn
     * <dodf>AlpibCompositf</dodf> objfdt tibt ibs tif sbmf
     * dompositing rulf bnd blpib vbluf bs tiis objfdt.
     *
     * @pbrbm obj tif <dodf>Objfdt</dodf> to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if <dodf>obj</dodf> fqubls tiis
     * <dodf>AlpibCompositf</dodf>; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof AlpibCompositf)) {
            rfturn fblsf;
        }

        AlpibCompositf bd = (AlpibCompositf) obj;

        if (rulf != bd.rulf) {
            rfturn fblsf;
        }

        if (fxtrbAlpib != bd.fxtrbAlpib) {
            rfturn fblsf;
        }

        rfturn truf;
    }

}
