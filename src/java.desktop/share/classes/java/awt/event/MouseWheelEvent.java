/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.bwt.Componfnt;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * An fvfnt wiidi indidbtfs tibt tif mousf wiffl wbs rotbtfd in b domponfnt.
 * <P>
 * A wiffl mousf is b mousf wiidi ibs b wiffl in plbdf of tif middlf button.
 * Tiis wiffl dbn bf rotbtfd towbrds or bwby from tif usfr.  Mousf wiffls brf
 * most oftfn usfd for sdrolling, tiougi otifr usfs brf possiblf.
 * <P>
 * A MousfWifflEvfnt objfdt is pbssfd to fvfry <dodf>MousfWifflListfnfr</dodf>
 * objfdt wiidi rfgistfrfd to rfdfivf tif "intfrfsting" mousf fvfnts using tif
 * domponfnt's <dodf>bddMousfWifflListfnfr</dodf> mftiod.  Ebdi sudi listfnfr
 * objfdt gfts b <dodf>MousfEvfnt</dodf> dontbining tif mousf fvfnt.
 * <P>
 * Duf to tif mousf wiffl's spfdibl rflbtionsiip to sdrolling Componfnts,
 * MousfWifflEvfnts brf dflivfrfd somfwibt difffrfntly tibn otifr MousfEvfnts.
 * Tiis is bfdbusf wiilf otifr MousfEvfnts usublly bfffdt b dibngf on
 * tif Componfnt dirfdtly undfr tif mousf
 * dursor (for instbndf, wifn dlidking b button), MousfWifflEvfnts oftfn ibvf
 * bn ffffdt bwby from tif mousf dursor (moving tif wiffl wiilf
 * ovfr b Componfnt insidf b SdrollPbnf siould sdroll onf of tif
 * Sdrollbbrs on tif SdrollPbnf).
 * <P>
 * MousfWifflEvfnts stbrt dflivfry from tif Componfnt undfrnfbti tif
 * mousf dursor.  If MousfWifflEvfnts brf not fnbblfd on tif
 * Componfnt, tif fvfnt is dflivfrfd to tif first bndfstor
 * Contbinfr witi MousfWifflEvfnts fnbblfd.  Tiis will usublly bf
 * b SdrollPbnf witi wiffl sdrolling fnbblfd.  Tif sourdf
 * Componfnt bnd x,y doordinbtfs will bf rflbtivf to tif fvfnt's
 * finbl dfstinbtion (tif SdrollPbnf).  Tiis bllows b domplfx
 * GUI to bf instbllfd witiout modifidbtion into b SdrollPbnf, bnd
 * for bll MousfWifflEvfnts to bf dflivfrfd to tif SdrollPbnf for
 * sdrolling.
 * <P>
 * Somf AWT Componfnts brf implfmfntfd using nbtivf widgfts wiidi
 * displby tifir own sdrollbbrs bnd ibndlf tifir own sdrolling.
 * Tif pbrtidulbr Componfnts for wiidi tiis is truf will vbry from
 * plbtform to plbtform.  Wifn tif mousf wiffl is
 * movfd ovfr onf of tifsf Componfnts, tif fvfnt is dflivfrfd strbigit to
 * tif nbtivf widgft, bnd not propbgbtfd to bndfstors.
 * <P>
 * Plbtforms offfr dustomizbtion of tif bmount of sdrolling tibt
 * siould tbkf plbdf wifn tif mousf wiffl is movfd.  Tif two most
 * dommon sfttings brf to sdroll b dfrtbin numbfr of "units"
 * (dommonly linfs of tfxt in b tfxt-bbsfd domponfnt) or bn fntirf "blodk"
 * (similbr to pbgf-up/pbgf-down).  Tif MousfWifflEvfnt offfrs
 * mftiods for donforming to tif undfrlying plbtform sfttings.  Tifsf
 * plbtform sfttings dbn bf dibngfd bt bny timf by tif usfr.  MousfWifflEvfnts
 * rfflfdt tif most rfdfnt sfttings.
 * <P>
 * Tif <dodf>MousfWifflEvfnt</dodf> dlbss indludfs mftiods for
 * gftting tif numbfr of "dlidks" by wiidi tif mousf wiffl is rotbtfd.
 * Tif {@link #gftWifflRotbtion} mftiod rfturns tif intfgfr numbfr
 * of "dlidks" dorrfsponding to tif numbfr of notdifs by wiidi tif wiffl wbs
 * rotbtfd. In bddition to tiis mftiod, tif <dodf>MousfWifflEvfnt</dodf>
 * dlbss providfs tif {@link #gftPrfdisfWifflRotbtion} mftiod wiidi rfturns
 * b doublf numbfr of "dlidks" in dbsf b pbrtibl rotbtion oddurrfd.
 * Tif {@link #gftPrfdisfWifflRotbtion} mftiod is usfful if b mousf supports
 * b iigi-rfsolution wiffl, sudi bs b frffly rotbting wiffl witi no
 * notdifs. Applidbtions dbn bfnffit by using tiis mftiod to prodfss
 * mousf wiffl fvfnts morf prfdisfly, bnd tius, mbking visubl pfrdfption
 * smootifr.
 *
 * @butior Brfnt Ciristibn
 * @sff MousfWifflListfnfr
 * @sff jbvb.bwt.SdrollPbnf
 * @sff jbvb.bwt.SdrollPbnf#sftWifflSdrollingEnbblfd(boolfbn)
 * @sff jbvbx.swing.JSdrollPbnf
 * @sff jbvbx.swing.JSdrollPbnf#sftWifflSdrollingEnbblfd(boolfbn)
 * @sindf 1.4
 */

publid dlbss MousfWifflEvfnt fxtfnds MousfEvfnt {

    /**
     * Constbnt rfprfsfnting sdrolling by "units" (likf sdrolling witi tif
     * brrow kfys)
     *
     * @sff #gftSdrollTypf
     */
    @Nbtivf publid stbtid finbl int WHEEL_UNIT_SCROLL = 0;

    /**
     * Constbnt rfprfsfnting sdrolling by b "blodk" (likf sdrolling
     * witi pbgf-up, pbgf-down kfys)
     *
     * @sff #gftSdrollTypf
     */
    @Nbtivf publid stbtid finbl int WHEEL_BLOCK_SCROLL = 1;

    /**
     * Indidbtfs wibt sort of sdrolling siould tbkf plbdf in rfsponsf to tiis
     * fvfnt, bbsfd on plbtform sfttings.  Lfgbl vblufs brf:
     * <ul>
     * <li> WHEEL_UNIT_SCROLL
     * <li> WHEEL_BLOCK_SCROLL
     * </ul>
     *
     * @sff #gftSdrollTypf
     */
    int sdrollTypf;

    /**
     * Only vblid for sdrollTypf WHEEL_UNIT_SCROLL.
     * Indidbtfs numbfr of units tibt siould bf sdrollfd pfr
     * dlidk of mousf wiffl rotbtion, bbsfd on plbtform sfttings.
     *
     * @sff #gftSdrollAmount
     * @sff #gftSdrollTypf
     */
    int sdrollAmount;

    /**
     * Indidbtfs iow fbr tif mousf wiffl wbs rotbtfd.
     *
     * @sff #gftWifflRotbtion
     */
    int wifflRotbtion;

    /**
     * Indidbtfs iow fbr tif mousf wiffl wbs rotbtfd.
     *
     * @sff #gftPrfdisfWifflRotbtion
     */
    doublf prfdisfWifflRotbtion;

    /*
     * sfriblVfrsionUID
     */

    privbtf stbtid finbl long sfriblVfrsionUID = 6459879390515399677L;

    /**
     * Construdts b <dodf>MousfWifflEvfnt</dodf> objfdt witi tif
     * spfdififd sourdf domponfnt, typf, modififrs, doordinbtfs,
     * sdroll typf, sdroll bmount, bnd wiffl rotbtion.
     * <p>Absolutf doordinbtfs xAbs bnd yAbs brf sft to sourdf's lodbtion on sdrffn plus
     * rflbtivf doordinbtfs x bnd y. xAbs bnd yAbs brf sft to zfro if tif sourdf is not siowing.
     * <p>Notf tibt pbssing in bn invblid <dodf>id</dodf> rfsults in
     * unspfdififd bfibvior. Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf         tif <dodf>Componfnt</dodf> tibt originbtfd
     *                       tif fvfnt
     * @pbrbm id             tif intfgfr tibt idfntififs tif fvfnt
     * @pbrbm wifn           b long tibt givfs tif timf tif fvfnt oddurrfd
     * @pbrbm modififrs      tif modififr kfys down during fvfnt
     *                       (siift, dtrl, blt, mftb)
     * @pbrbm x              tif iorizontbl x doordinbtf for tif mousf lodbtion
     * @pbrbm y              tif vfrtidbl y doordinbtf for tif mousf lodbtion
     * @pbrbm dlidkCount     tif numbfr of mousf dlidks bssodibtfd witi fvfnt
     * @pbrbm popupTriggfr   b boolfbn, truf if tiis fvfnt is b triggfr for b
     *                       popup-mfnu
     * @pbrbm sdrollTypf     tif typf of sdrolling wiidi siould tbkf plbdf in
     *                       rfsponsf to tiis fvfnt;  vblid vblufs brf
     *                       <dodf>WHEEL_UNIT_SCROLL</dodf> bnd
     *                       <dodf>WHEEL_BLOCK_SCROLL</dodf>
     * @pbrbm  sdrollAmount  for sdrollTypf <dodf>WHEEL_UNIT_SCROLL</dodf>,
     *                       tif numbfr of units to bf sdrollfd
     * @pbrbm wifflRotbtion  tif intfgfr numbfr of "dlidks" by wiidi tif mousf
     *                       wiffl wbs rotbtfd
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff MousfEvfnt#MousfEvfnt(jbvb.bwt.Componfnt, int, long, int, int, int, int, boolfbn)
     * @sff MousfEvfnt#MousfEvfnt(jbvb.bwt.Componfnt, int, long, int, int, int, int, int, int, boolfbn, int)
     */
    publid MousfWifflEvfnt (Componfnt sourdf, int id, long wifn, int modififrs,
                      int x, int y, int dlidkCount, boolfbn popupTriggfr,
                      int sdrollTypf, int sdrollAmount, int wifflRotbtion) {

        tiis(sourdf, id, wifn, modififrs, x, y, 0, 0, dlidkCount,
             popupTriggfr, sdrollTypf, sdrollAmount, wifflRotbtion);
    }

    /**
     * Construdts b <dodf>MousfWifflEvfnt</dodf> objfdt witi tif
     * spfdififd sourdf domponfnt, typf, modififrs, doordinbtfs,
     * bbsolutf doordinbtfs, sdroll typf, sdroll bmount, bnd wiffl rotbtion.
     * <p>Notf tibt pbssing in bn invblid <dodf>id</dodf> rfsults in
     * unspfdififd bfibvior. Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.<p>
     * Evfn if indonsistfnt vblufs for rflbtivf bnd bbsolutf doordinbtfs brf
     * pbssfd to tif donstrudtor, tif MousfWifflEvfnt instbndf is still
     * drfbtfd bnd no fxdfption is tirown.
     *
     * @pbrbm sourdf         tif <dodf>Componfnt</dodf> tibt originbtfd
     *                       tif fvfnt
     * @pbrbm id             tif intfgfr tibt idfntififs tif fvfnt
     * @pbrbm wifn           b long tibt givfs tif timf tif fvfnt oddurrfd
     * @pbrbm modififrs      tif modififr kfys down during fvfnt
     *                       (siift, dtrl, blt, mftb)
     * @pbrbm x              tif iorizontbl x doordinbtf for tif mousf lodbtion
     * @pbrbm y              tif vfrtidbl y doordinbtf for tif mousf lodbtion
     * @pbrbm xAbs           tif bbsolutf iorizontbl x doordinbtf for tif mousf lodbtion
     * @pbrbm yAbs           tif bbsolutf vfrtidbl y doordinbtf for tif mousf lodbtion
     * @pbrbm dlidkCount     tif numbfr of mousf dlidks bssodibtfd witi fvfnt
     * @pbrbm popupTriggfr   b boolfbn, truf if tiis fvfnt is b triggfr for b
     *                       popup-mfnu
     * @pbrbm sdrollTypf     tif typf of sdrolling wiidi siould tbkf plbdf in
     *                       rfsponsf to tiis fvfnt;  vblid vblufs brf
     *                       <dodf>WHEEL_UNIT_SCROLL</dodf> bnd
     *                       <dodf>WHEEL_BLOCK_SCROLL</dodf>
     * @pbrbm  sdrollAmount  for sdrollTypf <dodf>WHEEL_UNIT_SCROLL</dodf>,
     *                       tif numbfr of units to bf sdrollfd
     * @pbrbm wifflRotbtion  tif intfgfr numbfr of "dlidks" by wiidi tif mousf
     *                       wiffl wbs rotbtfd
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff MousfEvfnt#MousfEvfnt(jbvb.bwt.Componfnt, int, long, int, int, int, int, boolfbn)
     * @sff MousfEvfnt#MousfEvfnt(jbvb.bwt.Componfnt, int, long, int, int, int, int, int, int, boolfbn, int)
     * @sindf 1.6
     */
    publid MousfWifflEvfnt (Componfnt sourdf, int id, long wifn, int modififrs,
                            int x, int y, int xAbs, int yAbs, int dlidkCount, boolfbn popupTriggfr,
                            int sdrollTypf, int sdrollAmount, int wifflRotbtion) {

        tiis(sourdf, id, wifn, modififrs, x, y, xAbs, yAbs, dlidkCount, popupTriggfr,
             sdrollTypf, sdrollAmount, wifflRotbtion, wifflRotbtion);

    }


    /**
     * Construdts b <dodf>MousfWifflEvfnt</dodf> objfdt witi tif spfdififd
     * sourdf domponfnt, typf, modififrs, doordinbtfs, bbsolutf doordinbtfs,
     * sdroll typf, sdroll bmount, bnd wiffl rotbtion.
     * <p>Notf tibt pbssing in bn invblid <dodf>id</dodf> pbrbmftfr rfsults
     * in unspfdififd bfibvior. Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf> fqubls
     * <dodf>null</dodf>.
     * <p>Evfn if indonsistfnt vblufs for rflbtivf bnd bbsolutf doordinbtfs
     * brf pbssfd to tif donstrudtor, b <dodf>MousfWifflEvfnt</dodf> instbndf
     * is still drfbtfd bnd no fxdfption is tirown.
     *
     * @pbrbm sourdf         tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id             tif intfgfr vbluf tibt idfntififs tif fvfnt
     * @pbrbm wifn           b long vbluf tibt givfs tif timf wifn tif fvfnt oddurrfd
     * @pbrbm modififrs      tif modififr kfys down during fvfnt
     *                       (siift, dtrl, blt, mftb)
     * @pbrbm x              tif iorizontbl <dodf>x</dodf> doordinbtf for tif
     *                       mousf lodbtion
     * @pbrbm y              tif vfrtidbl <dodf>y</dodf> doordinbtf for tif
     *                       mousf lodbtion
     * @pbrbm xAbs           tif bbsolutf iorizontbl <dodf>x</dodf> doordinbtf for
     *                       tif mousf lodbtion
     * @pbrbm yAbs           tif bbsolutf vfrtidbl <dodf>y</dodf> doordinbtf for
     *                       tif mousf lodbtion
     * @pbrbm dlidkCount     tif numbfr of mousf dlidks bssodibtfd witi tif fvfnt
     * @pbrbm popupTriggfr   b boolfbn vbluf, <dodf>truf</dodf> if tiis fvfnt is b triggfr
     *                       for b popup-mfnu
     * @pbrbm sdrollTypf     tif typf of sdrolling wiidi siould tbkf plbdf in
     *                       rfsponsf to tiis fvfnt;  vblid vblufs brf
     *                       <dodf>WHEEL_UNIT_SCROLL</dodf> bnd
     *                       <dodf>WHEEL_BLOCK_SCROLL</dodf>
     * @pbrbm  sdrollAmount  for sdrollTypf <dodf>WHEEL_UNIT_SCROLL</dodf>,
     *                       tif numbfr of units to bf sdrollfd
     * @pbrbm wifflRotbtion  tif intfgfr numbfr of "dlidks" by wiidi tif mousf wiffl
     *                       wbs rotbtfd
     * @pbrbm prfdisfWifflRotbtion tif doublf numbfr of "dlidks" by wiidi tif mousf wiffl
     *                       wbs rotbtfd
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff MousfEvfnt#MousfEvfnt(jbvb.bwt.Componfnt, int, long, int, int, int, int, boolfbn)
     * @sff MousfEvfnt#MousfEvfnt(jbvb.bwt.Componfnt, int, long, int, int, int, int, int, int, boolfbn, int)
     * @sindf 1.7
     */
    publid MousfWifflEvfnt (Componfnt sourdf, int id, long wifn, int modififrs,
                            int x, int y, int xAbs, int yAbs, int dlidkCount, boolfbn popupTriggfr,
                            int sdrollTypf, int sdrollAmount, int wifflRotbtion, doublf prfdisfWifflRotbtion) {

        supfr(sourdf, id, wifn, modififrs, x, y, xAbs, yAbs, dlidkCount,
              popupTriggfr, MousfEvfnt.NOBUTTON);

        tiis.sdrollTypf = sdrollTypf;
        tiis.sdrollAmount = sdrollAmount;
        tiis.wifflRotbtion = wifflRotbtion;
        tiis.prfdisfWifflRotbtion = prfdisfWifflRotbtion;

    }

    /**
     * Rfturns tif typf of sdrolling tibt siould tbkf plbdf in rfsponsf to tiis
     * fvfnt.  Tiis is dftfrminfd by tif nbtivf plbtform.  Lfgbl vblufs brf:
     * <ul>
     * <li> MousfWifflEvfnt.WHEEL_UNIT_SCROLL
     * <li> MousfWifflEvfnt.WHEEL_BLOCK_SCROLL
     * </ul>
     *
     * @rfturn fitifr MousfWifflEvfnt.WHEEL_UNIT_SCROLL or
     *  MousfWifflEvfnt.WHEEL_BLOCK_SCROLL, dfpfnding on tif donfigurbtion of
     *  tif nbtivf plbtform.
     * @sff jbvb.bwt.Adjustbblf#gftUnitIndrfmfnt
     * @sff jbvb.bwt.Adjustbblf#gftBlodkIndrfmfnt
     * @sff jbvbx.swing.Sdrollbblf#gftSdrollbblfUnitIndrfmfnt
     * @sff jbvbx.swing.Sdrollbblf#gftSdrollbblfBlodkIndrfmfnt
     */
    publid int gftSdrollTypf() {
        rfturn sdrollTypf;
    }

    /**
     * Rfturns tif numbfr of units tibt siould bf sdrollfd pfr
     * dlidk of mousf wiffl rotbtion.
     * Only vblid if <dodf>gftSdrollTypf</dodf> rfturns
     * <dodf>MousfWifflEvfnt.WHEEL_UNIT_SCROLL</dodf>
     *
     * @rfturn numbfr of units to sdroll, or bn undffinfd vbluf if
     *  <dodf>gftSdrollTypf</dodf> rfturns
     *  <dodf>MousfWifflEvfnt.WHEEL_BLOCK_SCROLL</dodf>
     * @sff #gftSdrollTypf
     */
    publid int gftSdrollAmount() {
        rfturn sdrollAmount;
    }

    /**
     * Rfturns tif numbfr of "dlidks" tif mousf wiffl wbs rotbtfd, bs bn intfgfr.
     * A pbrtibl rotbtion mby oddur if tif mousf supports b iigi-rfsolution wiffl.
     * In tiis dbsf, tif mftiod rfturns zfro until b full "dlidk" ibs bffn bddumulbtfd.
     *
     * @rfturn nfgbtivf vblufs if tif mousf wiffl wbs rotbtfd up/bwby from
     * tif usfr, bnd positivf vblufs if tif mousf wiffl wbs rotbtfd down/
     * towbrds tif usfr
     * @sff #gftPrfdisfWifflRotbtion
     */
    publid int gftWifflRotbtion() {
        rfturn wifflRotbtion;
    }

    /**
     * Rfturns tif numbfr of "dlidks" tif mousf wiffl wbs rotbtfd, bs b doublf.
     * A pbrtibl rotbtion mby oddur if tif mousf supports b iigi-rfsolution wiffl.
     * In tiis dbsf, tif rfturn vbluf will indludf b frbdtionbl "dlidk".
     *
     * @rfturn nfgbtivf vblufs if tif mousf wiffl wbs rotbtfd up or bwby from
     * tif usfr, bnd positivf vblufs if tif mousf wiffl wbs rotbtfd down or
     * towbrds tif usfr
     * @sff #gftWifflRotbtion
     * @sindf 1.7
     */
    publid doublf gftPrfdisfWifflRotbtion() {
        rfturn prfdisfWifflRotbtion;
    }

    /**
     * Tiis is b donvfnifndf mftiod to bid in tif implfmfntbtion of
     * tif dommon-dbsf MousfWifflListfnfr - to sdroll b SdrollPbnf or
     * JSdrollPbnf by bn bmount wiidi donforms to tif plbtform sfttings.
     * (Notf, iowfvfr, tibt <dodf>SdrollPbnf</dodf> bnd
     * <dodf>JSdrollPbnf</dodf> blrfbdy ibvf tiis fundtionblity built in.)
     * <P>
     * Tiis mftiod rfturns tif numbfr of units to sdroll wifn sdroll typf is
     * MousfWifflEvfnt.WHEEL_UNIT_SCROLL, bnd siould only bf dbllfd if
     * <dodf>gftSdrollTypf</dodf> rfturns MousfWifflEvfnt.WHEEL_UNIT_SCROLL.
     * <P>
     * Dirfdtion of sdroll, bmount of wiffl movfmfnt,
     * bnd plbtform sfttings for wiffl sdrolling brf bll bddountfd for.
     * Tiis mftiod dofs not bnd dbnnot tbkf into bddount vbluf of tif
     * Adjustbblf/Sdrollbblf unit indrfmfnt, bs tiis will vbry bmong
     * sdrolling domponfnts.
     * <P>
     * A simplififd fxbmplf of iow tiis mftiod migit bf usfd in b
     * listfnfr:
     * <prf>
     *  mousfWifflMovfd(MousfWifflEvfnt fvfnt) {
     *      SdrollPbnf sp = gftSdrollPbnfFromSomfwifrf();
     *      Adjustbblf bdj = sp.gftVAdjustbblf()
     *      if (MousfWifflEvfnt.gftSdrollTypf() == WHEEL_UNIT_SCROLL) {
     *          int totblSdrollAmount =
     *              fvfnt.gftUnitsToSdroll() *
     *              bdj.gftUnitIndrfmfnt();
     *          bdj.sftVbluf(bdj.gftVbluf() + totblSdrollAmount);
     *      }
     *  }
     * </prf>
     *
     * @rfturn tif numbfr of units to sdroll bbsfd on tif dirfdtion bnd bmount
     *  of mousf wiffl rotbtion, bnd on tif wiffl sdrolling sfttings of tif
     *  nbtivf plbtform
     * @sff #gftSdrollTypf
     * @sff #gftSdrollAmount
     * @sff MousfWifflListfnfr
     * @sff jbvb.bwt.Adjustbblf
     * @sff jbvb.bwt.Adjustbblf#gftUnitIndrfmfnt
     * @sff jbvbx.swing.Sdrollbblf
     * @sff jbvbx.swing.Sdrollbblf#gftSdrollbblfUnitIndrfmfnt
     * @sff jbvb.bwt.SdrollPbnf
     * @sff jbvb.bwt.SdrollPbnf#sftWifflSdrollingEnbblfd
     * @sff jbvbx.swing.JSdrollPbnf
     * @sff jbvbx.swing.JSdrollPbnf#sftWifflSdrollingEnbblfd
     */
    publid int gftUnitsToSdroll() {
        rfturn sdrollAmount * wifflRotbtion;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String sdrollTypfStr = null;

        if (gftSdrollTypf() == WHEEL_UNIT_SCROLL) {
            sdrollTypfStr = "WHEEL_UNIT_SCROLL";
        }
        flsf if (gftSdrollTypf() == WHEEL_BLOCK_SCROLL) {
            sdrollTypfStr = "WHEEL_BLOCK_SCROLL";
        }
        flsf {
            sdrollTypfStr = "unknown sdroll typf";
        }
        rfturn supfr.pbrbmString()+",sdrollTypf="+sdrollTypfStr+
         ",sdrollAmount="+gftSdrollAmount()+",wifflRotbtion="+
         gftWifflRotbtion()+",prfdisfWifflRotbtion="+gftPrfdisfWifflRotbtion();
    }
}
