/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.BbsidPfrmission;

/**
 * Tiis dlbss is for AWT pfrmissions.
 * An <dodf>AWTPfrmission</dodf> dontbins b tbrgft nbmf but
 * no bdtions list; you fitifr ibvf tif nbmfd pfrmission
 * or you don't.
 *
 * <P>
 * Tif tbrgft nbmf is tif nbmf of tif AWT pfrmission (sff bflow). Tif nbming
 * donvfntion follows tif iifrbrdiidbl propfrty nbming donvfntion.
 * Also, bn bstfrisk dould bf usfd to rfprfsfnt bll AWT pfrmissions.
 *
 * <P>
 * Tif following tbblf lists bll tif possiblf <dodf>AWTPfrmission</dodf>
 * tbrgft nbmfs, bnd for fbdi providfs b dfsdription of wibt tif
 * pfrmission bllows bnd b disdussion of tif risks of grbnting dodf
 * tif pfrmission.
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="AWTPfrmission tbrgft nbmfs, dfsdriptions, bnd bssodibtfd risks.">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 *
 * <tr>
 *   <td>bddfssClipbobrd</td>
 *   <td>Posting bnd rftrifvbl of informbtion to bnd from tif AWT dlipbobrd</td>
 *   <td>Tiis would bllow mblffbsbnt dodf to sibrf
 * potfntiblly sfnsitivf or donfidfntibl informbtion.</td>
 * </tr>
 *
 * <tr>
 *   <td>bddfssEvfntQufuf</td>
 *   <td>Addfss to tif AWT fvfnt qufuf</td>
 *   <td>Aftfr rftrifving tif AWT fvfnt qufuf,
 * mblidious dodf mby pffk bt bnd fvfn rfmovf fxisting fvfnts
 * from its fvfnt qufuf, bs wfll bs post bogus fvfnts wiidi mby purposffully
 * dbusf tif bpplidbtion or bpplft to misbfibvf in bn insfdurf mbnnfr.</td>
 * </tr>
 *
 * <tr>
 *   <td>bddfssSystfmTrby</td>
 *   <td>Addfss to tif AWT SystfmTrby instbndf</td>
 *   <td>Tiis would bllow mblidious dodf to bdd trby idons to tif systfm trby.
 * First, sudi bn idon mby look likf tif idon of somf known bpplidbtion
 * (sudi bs b firfwbll or bnti-virus) bnd ordfr b usfr to do somftiing unsbff
 * (witi iflp of bblloon mfssbgfs). Sfdond, tif systfm trby mby bf gluttfd witi
 * trby idons so tibt no onf dould bdd b trby idon bnymorf.</td>
 * </tr>
 *
 * <tr>
 *   <td>drfbtfRobot</td>
 *   <td>Crfbtf jbvb.bwt.Robot objfdts</td>
 *   <td>Tif jbvb.bwt.Robot objfdt bllows dodf to gfnfrbtf nbtivf-lfvfl
 * mousf bnd kfybobrd fvfnts bs wfll bs rfbd tif sdrffn. It dould bllow
 * mblidious dodf to dontrol tif systfm, run otifr progrbms, rfbd tif
 * displby, bnd dfny mousf bnd kfybobrd bddfss to tif usfr.</td>
 * </tr>
 *
 * <tr>
 *   <td>fullSdrffnExdlusivf</td>
 *   <td>Entfr full-sdrffn fxdlusivf modf</td>
 *   <td>Entfring full-sdrffn fxdlusivf modf bllows dirfdt bddfss to
 * low-lfvfl grbpiids dbrd mfmory.  Tiis dould bf usfd to spoof tif
 * systfm, sindf tif progrbm is in dirfdt dontrol of rfndfring. Dfpfnding on
 * tif implfmfntbtion, tif sfdurity wbrning mby not bf siown for tif windows
 * usfd to fntfr tif full-sdrffn fxdlusivf modf (bssuming tibt tif {@dodf
 * fullSdrffnExdlusivf} pfrmission ibs bffn grbntfd to tiis bpplidbtion). Notf
 * tibt tiis bfibvior dofs not mfbn tibt tif {@dodf
 * siowWindowWitioutWbrningBbnnfr} pfrmission will bf butombtidblly grbntfd to
 * tif bpplidbtion wiidi ibs tif {@dodf fullSdrffnExdlusivf} pfrmission:
 * non-full-sdrffn windows will dontinuf to bf siown witi tif sfdurity
 * wbrning.</td>
 * </tr>
 *
 * <tr>
 *   <td>listfnToAllAWTEvfnts</td>
 *   <td>Listfn to bll AWT fvfnts, systfm-widf</td>
 *   <td>Aftfr bdding bn AWT fvfnt listfnfr,
 * mblidious dodf mby sdbn bll AWT fvfnts dispbtdifd in tif systfm,
 * bllowing it to rfbd bll usfr input (sudi bs pbsswords).  Ebdi
 * AWT fvfnt listfnfr is dbllfd from witiin tif dontfxt of tibt
 * fvfnt qufuf's EvfntDispbtdiTirfbd, so if tif bddfssEvfntQufuf
 * pfrmission is blso fnbblfd, mblidious dodf dould modify tif
 * dontfnts of AWT fvfnt qufufs systfm-widf, dbusing tif bpplidbtion
 * or bpplft to misbfibvf in bn insfdurf mbnnfr.</td>
 * </tr>
 *
 * <tr>
 *   <td>rfbdDisplbyPixfls</td>
 *   <td>Rfbdbbdk of pixfls from tif displby sdrffn</td>
 *   <td>Intfrfbdfs sudi bs tif jbvb.bwt.Compositf intfrfbdf or tif
 * jbvb.bwt.Robot dlbss bllow brbitrbry dodf to fxbminf pixfls on tif
 * displby fnbblf mblidious dodf to snoop on tif bdtivitifs of tif usfr.</td>
 * </tr>
 *
 * <tr>
 *   <td>rfplbdfKfybobrdFodusMbnbgfr</td>
 *   <td>Sfts tif <dodf>KfybobrdFodusMbnbgfr</dodf> for
 *       b pbrtidulbr tirfbd.
 *   <td>Wifn <dodf>SfdurityMbnbgfr</dodf> is instbllfd, tif invoking
 *       tirfbd must bf grbntfd tiis pfrmission in ordfr to rfplbdf
 *       tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf>.  If pfrmission
 *       is not grbntfd, b <dodf>SfdurityExdfption</dodf> will bf tirown.
 * </tr>
 *
 * <tr>
 *   <td>sftApplftStub</td>
 *   <td>Sftting tif stub wiidi implfmfnts Applft dontbinfr sfrvidfs</td>
 *   <td>Mblidious dodf dould sft bn bpplft's stub bnd rfsult in unfxpfdtfd
 * bfibvior or dfnibl of sfrvidf to bn bpplft.</td>
 * </tr>
 *
 * <tr>
 *   <td>sftWindowAlwbysOnTop</td>
 *   <td>Sftting blwbys-on-top propfrty of tif window: {@link Window#sftAlwbysOnTop}</td>
 *   <td>Tif mblidious window migit mbkf itsflf look bnd bfibvf likf b rfbl full dfsktop, so tibt
 * informbtion fntfrfd by tif unsuspfdting usfr is dbpturfd bnd subsfqufntly misusfd </td>
 * </tr>
 *
 * <tr>
 *   <td>siowWindowWitioutWbrningBbnnfr</td>
 *   <td>Displby of b window witiout blso displbying b bbnnfr wbrning
 * tibt tif window wbs drfbtfd by bn bpplft</td>
 *   <td>Witiout tiis wbrning,
 * bn bpplft mby pop up windows witiout tif usfr knowing tibt tify
 * bflong to bn bpplft.  Sindf usfrs mby mbkf sfdurity-sfnsitivf
 * dfdisions bbsfd on wiftifr or not tif window bflongs to bn bpplft
 * (fntfring b usfrnbmf bnd pbssword into b diblog box, for fxbmplf),
 * disbbling tiis wbrning bbnnfr mby bllow bpplfts to tridk tif usfr
 * into fntfring sudi informbtion.</td>
 * </tr>
 *
 * <tr>
 *   <td>toolkitModblity</td>
 *   <td>Crfbting {@link Diblog.ModblityTypf#TOOLKIT_MODAL TOOLKIT_MODAL} diblogs
 *       bnd sftting tif {@link Diblog.ModblExdlusionTypf#TOOLKIT_EXCLUDE
 *       TOOLKIT_EXCLUDE} window propfrty.</td>
 *   <td>Wifn b toolkit-modbl diblog is siown from bn bpplft, it blodks bll otifr
 * bpplfts in tif browsfr. Wifn lbundiing bpplidbtions from Jbvb Wfb Stbrt,
 * its windows (sudi bs tif sfdurity diblog) mby blso bf blodkfd by toolkit-modbl
 * diblogs, siown from tifsf bpplidbtions.</td>
 * </tr>
 *
 * <tr>
 *   <td>wbtdiMousfPointfr</td>
 *   <td>Gftting tif informbtion bbout tif mousf pointfr position bt bny
 * timf</td>
 *   <td>Constbntly wbtdiing tif mousf pointfr,
 * bn bpplft dbn mbkf gufssfs bbout wibt tif usfr is doing, i.f. moving
 * tif mousf to tif lowfr lfft dornfr of tif sdrffn most likfly mfbns tibt
 * tif usfr is bbout to lbundi bn bpplidbtion. If b virtubl kfypbd is usfd
 * so tibt kfybobrd is fmulbtfd using tif mousf, bn bpplft mby gufss wibt
 * is bfing typfd.</td>
 * </tr>
 * </tbblf>
 *
 * @sff jbvb.sfdurity.BbsidPfrmission
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.lbng.SfdurityMbnbgfr
 *
 *
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 */

publid finbl dlbss AWTPfrmission fxtfnds BbsidPfrmission {

    /** usf sfriblVfrsionUID from tif Jbvb 2 plbtform for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 8890392402588814465L;

    /**
     * Crfbtfs b nfw <dodf>AWTPfrmission</dodf> witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif <dodf>AWTPfrmission</dodf>,
     * sudi bs "topLfvflWindow", "systfmClipbobrd", ftd. An bstfrisk
     * mby bf usfd to indidbtf bll AWT pfrmissions.
     *
     * @pbrbm nbmf tif nbmf of tif AWTPfrmission
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */

    publid AWTPfrmission(String nbmf)
    {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b nfw <dodf>AWTPfrmission</dodf> objfdt witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif <dodf>AWTPfrmission</dodf>, bnd tif
     * bdtions string is durrfntly unusfd bnd siould bf <dodf>null</dodf>.
     *
     * @pbrbm nbmf tif nbmf of tif <dodf>AWTPfrmission</dodf>
     * @pbrbm bdtions siould bf <dodf>null</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */

    publid AWTPfrmission(String nbmf, String bdtions)
    {
        supfr(nbmf, bdtions);
    }
}
