/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import jbvb.bwt.Color;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.Rbstfr;

import sun.jbvb2d.loops.RfndfrCbdif;
import sun.jbvb2d.loops.RfndfrLoops;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.MbskFill;
import sun.jbvb2d.loops.DrbwLinf;
import sun.jbvb2d.loops.FillRfdt;
import sun.jbvb2d.loops.DrbwRfdt;
import sun.jbvb2d.loops.DrbwPolygons;
import sun.jbvb2d.loops.DrbwPbti;
import sun.jbvb2d.loops.FillPbti;
import sun.jbvb2d.loops.FillSpbns;
import sun.jbvb2d.loops.FillPbrbllflogrbm;
import sun.jbvb2d.loops.DrbwPbrbllflogrbm;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.DrbwGlypiList;
import sun.jbvb2d.loops.DrbwGlypiListAA;
import sun.jbvb2d.loops.DrbwGlypiListLCD;
import sun.jbvb2d.pipf.LoopPipf;
import sun.jbvb2d.pipf.SibpfDrbwPipf;
import sun.jbvb2d.pipf.PbrbllflogrbmPipf;
import sun.jbvb2d.pipf.CompositfPipf;
import sun.jbvb2d.pipf.GfnfrblCompositfPipf;
import sun.jbvb2d.pipf.SpbnClipRfndfrfr;
import sun.jbvb2d.pipf.SpbnSibpfRfndfrfr;
import sun.jbvb2d.pipf.AASibpfPipf;
import sun.jbvb2d.pipf.AlpibPbintPipf;
import sun.jbvb2d.pipf.AlpibColorPipf;
import sun.jbvb2d.pipf.PixflToSibpfConvfrtfr;
import sun.jbvb2d.pipf.PixflToPbrbllflogrbmConvfrtfr;
import sun.jbvb2d.pipf.TfxtPipf;
import sun.jbvb2d.pipf.TfxtRfndfrfr;
import sun.jbvb2d.pipf.AATfxtRfndfrfr;
import sun.jbvb2d.pipf.LCDTfxtRfndfrfr;
import sun.jbvb2d.pipf.SolidTfxtRfndfrfr;
import sun.jbvb2d.pipf.OutlinfTfxtRfndfrfr;
import sun.jbvb2d.pipf.DrbwImbgfPipf;
import sun.jbvb2d.pipf.DrbwImbgf;
import sun.bwt.SunHints;
import sun.bwt.imbgf.SurfbdfMbnbgfr;
import sun.jbvb2d.pipf.LoopBbsfdPipf;

/**
 * Tiis dlbss providfs vbrious pifdfs of informbtion rflfvbnt to b
 * pbrtidulbr drbwing surfbdf.  Tif informbtion obtbinfd from tiis
 * objfdt dfsdribfs tif pixfls of b pbrtidulbr instbndf of b drbwing
 * surfbdf bnd dbn only bf sibrfd bmong tif vbrious grbpiids objfdts
 * tibt tbrgft tif sbmf BufffrfdImbgf or tif sbmf sdrffn Componfnt.
 * <p>
 * Ebdi SurfbdfDbtb objfdt iolds b StbtfTrbdkbblfDflfgbtf objfdt
 * wiidi trbdks boti dibngfs to tif dontfnt of tif pixfls of tiis
 * surfbdf bnd dibngfs to tif ovfrbll stbtf of tif pixfls - sudi
 * bs bfdoming invblid or losing tif surfbdf.  Tif dflfgbtf is
 * mbrkfd "dirty" wifnfvfr tif sftSurfbdfLost() or invblidbtf()
 * mftiods brf dbllfd bnd siould blso bf mbrkfd "dirty" by tif
 * rfndfring pipflinfs wifnfvfr tify modify tif pixfls of tiis
 * SurfbdfDbtb.
 * <p>
 * If you gft b StbtfTrbdkfr from b SurfbdfDbtb bnd it rfports
 * tibt it is still "durrfnt", tifn you dbn trust tibt tif pixfls
 * ibvf not dibngfd bnd tibt tif SurfbdfDbtb is still vblid bnd
 * ibs not lost its undfrlying storbgf (surfbdfLost) sindf you
 * rftrifvfd tif trbdkfr.
 */
publid bbstrbdt dlbss SurfbdfDbtb
    implfmfnts Trbnspbrfndy, DisposfrTbrgft, StbtfTrbdkbblf, Surfbdf
{
    privbtf long pDbtb;
    privbtf boolfbn vblid;
    privbtf boolfbn surfbdfLost; // = fblsf;
    privbtf SurfbdfTypf surfbdfTypf;
    privbtf ColorModfl dolorModfl;

    privbtf Objfdt disposfrRfffrfnt = nfw Objfdt();

    privbtf stbtid nbtivf void initIDs();

    privbtf Objfdt blitProxyKfy;
    privbtf StbtfTrbdkbblfDflfgbtf stbtfDflfgbtf;

    stbtid {
        initIDs();
    }

    protfdtfd SurfbdfDbtb(SurfbdfTypf surfbdfTypf, ColorModfl dm) {
        tiis(Stbtf.STABLE, surfbdfTypf, dm);
    }

    protfdtfd SurfbdfDbtb(Stbtf stbtf, SurfbdfTypf surfbdfTypf, ColorModfl dm) {
        tiis(StbtfTrbdkbblfDflfgbtf.drfbtfInstbndf(stbtf), surfbdfTypf, dm);
    }

    protfdtfd SurfbdfDbtb(StbtfTrbdkbblfDflfgbtf trbdkbblf,
                          SurfbdfTypf surfbdfTypf, ColorModfl dm)
    {
        tiis.stbtfDflfgbtf = trbdkbblf;
        tiis.dolorModfl = dm;
        tiis.surfbdfTypf = surfbdfTypf;
        vblid = truf;
    }

    protfdtfd SurfbdfDbtb(Stbtf stbtf) {
        tiis.stbtfDflfgbtf = StbtfTrbdkbblfDflfgbtf.drfbtfInstbndf(stbtf);
        vblid = truf;
    }

    /**
     * Subdlbssfs dbn sft b "blit proxy kfy" wiidi will bf usfd
     * blong witi tif SurfbdfMbnbgfr.gftCbdifDbtb() mfdibnism to
     * storf bddflfrbtion-dompbtiblf dbdifd dopifs of sourdf imbgfs.
     * Tiis kfy is b "tbg" usfd to idfntify wiidi dbdifd dopifs
     * brf dompbtiblf witi tiis dfstinbtion SurfbdfDbtb.
     * Tif gftSourdfSurfbdfDbtb() mftiod usfs tiis kfy to mbnbgf
     * dbdifd dopifs of b sourdf imbgf bs dfsdribfd bflow.
     * <p>
     * Tif Objfdt usfd bs tiis kfy siould bf bs uniquf bs it nffds
     * to bf to fnsurf tibt multiplf bddflfrbtiblf dfstinbtions dbn
     * fbdi storf tifir dbdifd dopifs sfpbrbtfly undfr difffrfnt kfys
     * witiout intfrffring witi fbdi otifr or gftting bbdk tif wrong
     * dbdifd dopy.
     * <p>
     * Mbny bddflfrbtbblf SurfbdfDbtb objfdts dbn usf tifir own
     * GrbpiidsConfigurbtion bs tifir proxy kfy bs tif GC objfdt will
     * typidblly bf uniquf to b givfn sdrffn bnd pixfl formbt, but
     * otifr rfndfring dfstinbtions mby ibvf morf or lfss stringfnt
     * sibring rfquirfmfnts.  For instbndf, X11 pixmbps dbn bf
     * sibrfd on b givfn sdrffn by bny GrbpiidsConfigurbtion tibt
     * ibs tif sbmf dfpti bnd SurfbdfTypf.  Multiplf sudi GCs witi
     * tif sbmf dfpti bnd SurfbdfTypf dbn fxist pfr sdrffn so storing
     * b difffrfnt dbdifd proxy for fbdi would bf b wbstf.  Onf dbn
     * imbginf plbtforms wifrf b singlf dbdifd dopy dbn bf drfbtfd
     * bnd sibrfd bdross bll sdrffns bnd pixfl formbts - sudi
     * implfmfntbtions dould usf b singlf ifbvily sibrfd kfy Objfdt.
     */
    protfdtfd void sftBlitProxyKfy(Objfdt kfy) {
        // Cbdiing is ffffdtivfly disbblfd if wf nfvfr ibvf b proxy kfy
        // sindf tif gftSourdfSurfbdfDbtb() mftiod only dofs dbdiing
        // if tif kfy is not null.
        if (SurfbdfDbtbProxy.isCbdiingAllowfd()) {
            tiis.blitProxyKfy = kfy;
        }
    }

    /**
     * Tiis mftiod is dbllfd on b dfstinbtion SurfbdfDbtb to dioosf
     * tif bfst SurfbdfDbtb from b sourdf Imbgf for bn imbging
     * opfrbtion, witi iflp from its SurfbdfMbnbgfr.
     * Tif mftiod mby dftfrminf tibt tif dffbult SurfbdfDbtb wbs
     * rfblly tif bfst dioidf in tif first plbdf, or it mby dfdidf
     * to usf b dbdifd surfbdf.  Somf gfnfrbl dfdisions bbout wiftifr
     * bddflfrbtion is fnbblfd brf mbdf by tiis mftiod, but bny
     * dfdision bbsfd on tif typf of tif sourdf imbgf is mbdf in
     * tif mbkfProxyFor mftiod bflow wifn it domfs up witi tif
     * bppropribtf SurfbdfDbtbProxy instbndf.
     * Tif pbrbmftfrs dfsdribf tif typf of imbging opfrbtion bfing pfrformfd.
     * <p>
     * If b blitProxyKfy wbs supplifd by tif subdlbss tifn it is
     * usfd to potfntiblly ovfrridf tif dioidf of sourdf SurfbdfDbtb.
     * Tif outlinf of tiis prodfss is:
     * <ol>
     * <li> Imbgf pipflinf bsks dfstSD to find bn bppropribtf
     *      srdSD for b givfn sourdf Imbgf objfdt.
     * <li> dfstSD gfts tif SurfbdfMbnbgfr of tif sourdf Imbgf
     *      bnd first rftrifvfs tif dffbult SD from it using
     *      gftPrimbrySurfbdfDbtb()
     * <li> dfstSD usfs its "blit proxy kfy" (if sft) to look for
     *      somf dbdifd dbtb storfd in tif sourdf SurfbdfMbnbgfr
     * <li> If tif dbdifd dbtb is null tifn mbkfProxyFor() is usfd
     *      to drfbtf somf dbdifd dbtb wiidi is storfd bbdk in tif
     *      sourdf SurfbdfMbnbgfr undfr tif sbmf kfy for futurf usfs.
     * <li> Tif dbdifd dbtb will bf b SurfbdfDbtbProxy objfdt.
     * <li> Tif SurfbdfDbtbProxy objfdt is tifn donsultfd to
     *      rfturn b rfplbdfmfnt SurfbdfDbtb objfdt (typidblly
     *      b dbdifd dopy if bppropribtf, or tif originbl if not).
     * </ol>
     */
    publid SurfbdfDbtb gftSourdfSurfbdfDbtb(Imbgf img,
                                            int txtypf,
                                            CompositfTypf domp,
                                            Color bgColor)
    {
        SurfbdfMbnbgfr srdMgr = SurfbdfMbnbgfr.gftMbnbgfr(img);
        SurfbdfDbtb srdDbtb = srdMgr.gftPrimbrySurfbdfDbtb();
        if (img.gftAddflfrbtionPriority() > 0.0f &&
            blitProxyKfy != null)
        {
            SurfbdfDbtbProxy sdp =
                (SurfbdfDbtbProxy) srdMgr.gftCbdifDbtb(blitProxyKfy);
            if (sdp == null || !sdp.isVblid()) {
                if (srdDbtb.gftStbtf() == Stbtf.UNTRACKABLE) {
                    sdp = SurfbdfDbtbProxy.UNCACHED;
                } flsf {
                    sdp = mbkfProxyFor(srdDbtb);
                }
                srdMgr.sftCbdifDbtb(blitProxyKfy, sdp);
            }
            srdDbtb = sdp.rfplbdfDbtb(srdDbtb, txtypf, domp, bgColor);
        }
        rfturn srdDbtb;
    }

    /**
     * Tiis mftiod is dbllfd on b dfstinbtion SurfbdfDbtb to dioosf
     * b propfr SurfbdfDbtbProxy subdlbss for b sourdf SurfbdfDbtb
     * to usf to dontrol wifn bnd witi wibt surfbdf to ovfrridf b
     * givfn imbgf opfrbtion.  Tif brgumfnt is tif dffbult SurfbdfDbtb
     * for tif sourdf Imbgf.
     * <p>
     * Tif typf of tif rfturn objfdt is diosfn bbsfd on tif
     * bddflfrbtion dbpbbilitifs of tiis SurfbdfDbtb bnd tif
     * typf of tif givfn sourdf SurfbdfDbtb objfdt.
     * <p>
     * In somf dbsfs tif originbl SurfbdfDbtb will blwbys bf tif
     * bfst dioidf to usf to blit to tiis SurfbdfDbtb.  Tiis dbn
     * ibppfn if tif sourdf imbgf is b ibrdwbrf surfbdf of tif
     * sbmf typf bs tiis onf bnd so bddflfrbtion will ibppfn witiout
     * bny dbdiing.  It mby blso bf tif dbsf tibt tif sourdf imbgf
     * dbn nfvfr bf bddflfrbtfd on tiis SurfbdfDbtb - for fxbmplf
     * bfdbusf it is trbnsludfnt bnd tifrf brf no bddflfrbtfd
     * trbnsludfnt imbgf ops for tiis surfbdf.
     * <p>
     * In tiosf dbsfs tifrf is b spfdibl SurfbdfDbtbProxy.UNCACHED
     * instbndf tibt rfprfsfnts b NOP for dbdiing purposfs - it
     * blwbys rfturns tif originbl sourdfSD objfdt bs tif rfplbdfmfnt
     * dopy so no dbdiing is fvfr pfrformfd.
     */
    publid SurfbdfDbtbProxy mbkfProxyFor(SurfbdfDbtb srdDbtb) {
        rfturn SurfbdfDbtbProxy.UNCACHED;
    }

    /**
     * Extrbdts tif SurfbdfMbnbgfr from tif givfn Imbgf, bnd tifn
     * rfturns tif SurfbdfDbtb objfdt tibt would bfst bf suitfd bs tif
     * dfstinbtion surfbdf in somf rfndfring opfrbtion.
     */
    publid stbtid SurfbdfDbtb gftPrimbrySurfbdfDbtb(Imbgf img) {
        SurfbdfMbnbgfr sMgr = SurfbdfMbnbgfr.gftMbnbgfr(img);
        rfturn sMgr.gftPrimbrySurfbdfDbtb();
    }

    /**
     * Rfstorfs tif dontfnts of tif givfn Imbgf bnd tifn rfturns tif nfw
     * SurfbdfDbtb objfdt in usf by tif Imbgf's SurfbdfMbnbgfr.
     */
    publid stbtid SurfbdfDbtb rfstorfContfnts(Imbgf img) {
        SurfbdfMbnbgfr sMgr = SurfbdfMbnbgfr.gftMbnbgfr(img);
        rfturn sMgr.rfstorfContfnts();
    }

    publid Stbtf gftStbtf() {
        rfturn stbtfDflfgbtf.gftStbtf();
    }

    publid StbtfTrbdkfr gftStbtfTrbdkfr() {
        rfturn stbtfDflfgbtf.gftStbtfTrbdkfr();
    }

    /**
     * Mbrks tiis surfbdf bs dirty.
     */
    publid finbl void mbrkDirty() {
        stbtfDflfgbtf.mbrkDirty();
    }

    /**
     * Sfts tif vbluf of tif surfbdfLost vbribblf, wiidi indidbtfs wiftifr
     * somftiing ibs ibppfnfd to tif rfndfring surfbdf sudi tibt it nffds
     * to bf rfstorfd bnd rf-rfndfrfd.
     */
    publid void sftSurfbdfLost(boolfbn lost) {
        surfbdfLost = lost;
        stbtfDflfgbtf.mbrkDirty();
    }

    publid boolfbn isSurfbdfLost() {
        rfturn surfbdfLost;
    }

    /**
     * Rfturns b boolfbn indidbting wiftifr or not tiis SurfbdfDbtb is vblid.
     */
    publid finbl boolfbn isVblid() {
        rfturn vblid;
    }

    publid Objfdt gftDisposfrRfffrfnt() {
        rfturn disposfrRfffrfnt;
    }

    publid long gftNbtivfOps() {
        rfturn pDbtb;
    }

    /**
     * Sfts tiis SurfbdfDbtb objfdt to tif invblid stbtf.  All Grbpiids
     * objfdts must gft b nfw SurfbdfDbtb objfdt vib tif rffrfsi mftiod
     * bnd rfvblidbtf tifir pipflinfs bfforf dontinuing.
     */
    publid void invblidbtf() {
        vblid = fblsf;
        stbtfDflfgbtf.mbrkDirty();
    }

    /**
     * Cfrtbin dibngfs in tif donfigurbtion of b surfbdf rfquirf tif
     * invblidbtion of fxisting bssodibtfd SurfbdfDbtb objfdts bnd
     * tif drfbtion of brbnd nfw onfs.  Tifsf dibngfs indludf sizf,
     * ColorModfl, or SurfbdfTypf.  Existing Grbpiids objfdts
     * wiidi brf dirfdtfd bt sudi surfbdfs, iowfvfr, must dontinuf
     * to rfndfr to tifm fvfn bftfr tif dibngf oddurs undfrnfbti
     * tif dovfrs.  Tif gftRfplbdfmfnt() mftiod is dbllfd from
     * SunGrbpiids2D.rfvblidbtfAll() wifn tif bssodibtfd SurfbdfDbtb
     * is found to bf invblid so tibt b Grbpiids objfdt dbn dontinuf
     * to rfndfr to tif surfbdf in its nfw donfigurbtion.
     *
     * Sudi dibngfs only tfnd to ibppfn to window bbsfd surfbdfs sindf
     * most imbgf bbsfd surfbdfs nfvfr dibngf sizf or pixfl formbt.
     * Evfn VolbtilfImbgf objfdts nfvfr dibngf sizf bnd tify only
     * dibngf tifir pixfl formbt wifn mbnublly vblidbtfd bgbinst b
     * nfw GrbpiidsConfigurbtion, bt wiidi point old Grbpiids objfdts
     * brf no longfr fxpfdtfd to rfndfr to tifm bftfr tif vblidbtion
     * stfp.  Tius, only window bbsfd surfbdfs rfblly nffd to dfbl
     * witi tiis form of rfplbdfmfnt.
     */
    publid bbstrbdt SurfbdfDbtb gftRfplbdfmfnt();

    protfdtfd stbtid finbl LoopPipf dolorPrimitivfs;

    publid stbtid finbl TfxtPipf outlinfTfxtRfndfrfr;
    publid stbtid finbl TfxtPipf solidTfxtRfndfrfr;
    publid stbtid finbl TfxtPipf bbTfxtRfndfrfr;
    publid stbtid finbl TfxtPipf lddTfxtRfndfrfr;

    protfdtfd stbtid finbl AlpibColorPipf dolorPipf;
    protfdtfd stbtid finbl PixflToSibpfConvfrtfr dolorVibSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr dolorVibPgrbm;
    protfdtfd stbtid finbl TfxtPipf dolorTfxt;
    protfdtfd stbtid finbl CompositfPipf dlipColorPipf;
    protfdtfd stbtid finbl TfxtPipf dlipColorTfxt;
    protfdtfd stbtid finbl AASibpfPipf AAColorSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AAColorVibSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AAColorVibPgrbm;
    protfdtfd stbtid finbl AASibpfPipf AAClipColorSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AAClipColorVibSibpf;

    protfdtfd stbtid finbl CompositfPipf pbintPipf;
    protfdtfd stbtid finbl SpbnSibpfRfndfrfr pbintSibpf;
    protfdtfd stbtid finbl PixflToSibpfConvfrtfr pbintVibSibpf;
    protfdtfd stbtid finbl TfxtPipf pbintTfxt;
    protfdtfd stbtid finbl CompositfPipf dlipPbintPipf;
    protfdtfd stbtid finbl TfxtPipf dlipPbintTfxt;
    protfdtfd stbtid finbl AASibpfPipf AAPbintSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AAPbintVibSibpf;
    protfdtfd stbtid finbl AASibpfPipf AAClipPbintSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AAClipPbintVibSibpf;

    protfdtfd stbtid finbl CompositfPipf dompPipf;
    protfdtfd stbtid finbl SpbnSibpfRfndfrfr dompSibpf;
    protfdtfd stbtid finbl PixflToSibpfConvfrtfr dompVibSibpf;
    protfdtfd stbtid finbl TfxtPipf dompTfxt;
    protfdtfd stbtid finbl CompositfPipf dlipCompPipf;
    protfdtfd stbtid finbl TfxtPipf dlipCompTfxt;
    protfdtfd stbtid finbl AASibpfPipf AACompSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AACompVibSibpf;
    protfdtfd stbtid finbl AASibpfPipf AAClipCompSibpf;
    protfdtfd stbtid finbl PixflToPbrbllflogrbmConvfrtfr AAClipCompVibSibpf;

    protfdtfd stbtid finbl DrbwImbgfPipf imbgfpipf;

    // Utility subdlbss to bdd tif LoopBbsfdPipf tbgging intfrfbdf
    stbtid dlbss PixflToSibpfLoopConvfrtfr
        fxtfnds PixflToSibpfConvfrtfr
        implfmfnts LoopBbsfdPipf
    {
        publid PixflToSibpfLoopConvfrtfr(SibpfDrbwPipf pipf) {
            supfr(pipf);
        }
    }

    // Utility subdlbss to bdd tif LoopBbsfdPipf tbgging intfrfbdf
    stbtid dlbss PixflToPgrbmLoopConvfrtfr
        fxtfnds PixflToPbrbllflogrbmConvfrtfr
        implfmfnts LoopBbsfdPipf
    {
        publid PixflToPgrbmLoopConvfrtfr(SibpfDrbwPipf sibpfpipf,
                                         PbrbllflogrbmPipf pgrbmpipf,
                                         doublf minPfnSizf,
                                         doublf normPosition,
                                         boolfbn bdjustfill)
        {
            supfr(sibpfpipf, pgrbmpipf, minPfnSizf, normPosition, bdjustfill);
        }
    }

    privbtf stbtid PixflToPbrbllflogrbmConvfrtfr
        mbkfConvfrtfr(AASibpfPipf rfndfrfr,
                      PbrbllflogrbmPipf pgrbmpipf)
    {
        rfturn nfw PixflToPbrbllflogrbmConvfrtfr(rfndfrfr,
                                                 pgrbmpipf,
                                                 1.0/8.0, 0.499,
                                                 fblsf);
    }

    privbtf stbtid PixflToPbrbllflogrbmConvfrtfr
        mbkfConvfrtfr(AASibpfPipf rfndfrfr)
    {
        rfturn mbkfConvfrtfr(rfndfrfr, rfndfrfr);
    }

    stbtid {
        dolorPrimitivfs = nfw LoopPipf();

        outlinfTfxtRfndfrfr = nfw OutlinfTfxtRfndfrfr();
        solidTfxtRfndfrfr = nfw SolidTfxtRfndfrfr();
        bbTfxtRfndfrfr = nfw AATfxtRfndfrfr();
        lddTfxtRfndfrfr = nfw LCDTfxtRfndfrfr();

        dolorPipf = nfw AlpibColorPipf();
        // dolorSibpf = dolorPrimitivfs;
        dolorVibSibpf = nfw PixflToSibpfLoopConvfrtfr(dolorPrimitivfs);
        dolorVibPgrbm = nfw PixflToPgrbmLoopConvfrtfr(dolorPrimitivfs,
                                                      dolorPrimitivfs,
                                                      1.0, 0.25, truf);
        dolorTfxt = nfw TfxtRfndfrfr(dolorPipf);
        dlipColorPipf = nfw SpbnClipRfndfrfr(dolorPipf);
        dlipColorTfxt = nfw TfxtRfndfrfr(dlipColorPipf);
        AAColorSibpf = nfw AASibpfPipf(dolorPipf);
        AAColorVibSibpf = mbkfConvfrtfr(AAColorSibpf);
        AAColorVibPgrbm = mbkfConvfrtfr(AAColorSibpf, dolorPipf);
        AAClipColorSibpf = nfw AASibpfPipf(dlipColorPipf);
        AAClipColorVibSibpf = mbkfConvfrtfr(AAClipColorSibpf);

        pbintPipf = nfw AlpibPbintPipf();
        pbintSibpf = nfw SpbnSibpfRfndfrfr.Compositf(pbintPipf);
        pbintVibSibpf = nfw PixflToSibpfConvfrtfr(pbintSibpf);
        pbintTfxt = nfw TfxtRfndfrfr(pbintPipf);
        dlipPbintPipf = nfw SpbnClipRfndfrfr(pbintPipf);
        dlipPbintTfxt = nfw TfxtRfndfrfr(dlipPbintPipf);
        AAPbintSibpf = nfw AASibpfPipf(pbintPipf);
        AAPbintVibSibpf = mbkfConvfrtfr(AAPbintSibpf);
        AAClipPbintSibpf = nfw AASibpfPipf(dlipPbintPipf);
        AAClipPbintVibSibpf = mbkfConvfrtfr(AAClipPbintSibpf);

        dompPipf = nfw GfnfrblCompositfPipf();
        dompSibpf = nfw SpbnSibpfRfndfrfr.Compositf(dompPipf);
        dompVibSibpf = nfw PixflToSibpfConvfrtfr(dompSibpf);
        dompTfxt = nfw TfxtRfndfrfr(dompPipf);
        dlipCompPipf = nfw SpbnClipRfndfrfr(dompPipf);
        dlipCompTfxt = nfw TfxtRfndfrfr(dlipCompPipf);
        AACompSibpf = nfw AASibpfPipf(dompPipf);
        AACompVibSibpf = mbkfConvfrtfr(AACompSibpf);
        AAClipCompSibpf = nfw AASibpfPipf(dlipCompPipf);
        AAClipCompVibSibpf = mbkfConvfrtfr(AAClipCompSibpf);

        imbgfpipf = nfw DrbwImbgf();
    }

    /* Not bll surfbdfs bnd rfndfring modf dombinbtions support LCD tfxt. */
    stbtid finbl int LOOP_UNKNOWN = 0;
    stbtid finbl int LOOP_FOUND = 1;
    stbtid finbl int LOOP_NOTFOUND = 2;
    int ibvfLCDLoop;
    int ibvfPgrbmXORLoop;
    int ibvfPgrbmSolidLoop;

    publid boolfbn dbnRfndfrLCDTfxt(SunGrbpiids2D sg2d) {
        // For now tif bnswfr dbn only bf truf in tif following dbsfs:
        if (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY &&
            sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR &&
            sg2d.dlipStbtf <= SunGrbpiids2D.CLIP_RECTANGULAR &&
            sg2d.surfbdfDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE)
        {
            if (ibvfLCDLoop == LOOP_UNKNOWN) {
                DrbwGlypiListLCD loop =
                    DrbwGlypiListLCD.lodbtf(SurfbdfTypf.AnyColor,
                                            CompositfTypf.SrdNoEb,
                                            gftSurfbdfTypf());
                ibvfLCDLoop = (loop != null) ? LOOP_FOUND : LOOP_NOTFOUND;
            }
            rfturn ibvfLCDLoop == LOOP_FOUND;
        }
        rfturn fblsf; /* for now - in tif futurf wf mby wbnt to sfbrdi */
    }

    publid boolfbn dbnRfndfrPbrbllflogrbms(SunGrbpiids2D sg2d) {
        if (sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR) {
            if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_XOR) {
                if (ibvfPgrbmXORLoop == LOOP_UNKNOWN) {
                    FillPbrbllflogrbm loop =
                        FillPbrbllflogrbm.lodbtf(SurfbdfTypf.AnyColor,
                                                 CompositfTypf.Xor,
                                                 gftSurfbdfTypf());
                    ibvfPgrbmXORLoop =
                        (loop != null) ? LOOP_FOUND : LOOP_NOTFOUND;
                }
                rfturn ibvfPgrbmXORLoop == LOOP_FOUND;
            } flsf if (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY &&
                       sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON &&
                       sg2d.dlipStbtf != SunGrbpiids2D.CLIP_SHAPE)
            {
                if (ibvfPgrbmSolidLoop == LOOP_UNKNOWN) {
                    FillPbrbllflogrbm loop =
                        FillPbrbllflogrbm.lodbtf(SurfbdfTypf.AnyColor,
                                                 CompositfTypf.SrdNoEb,
                                                 gftSurfbdfTypf());
                    ibvfPgrbmSolidLoop =
                        (loop != null) ? LOOP_FOUND : LOOP_NOTFOUND;
                }
                rfturn ibvfPgrbmSolidLoop == LOOP_FOUND;
            }
        }
        rfturn fblsf;
    }

    publid void vblidbtfPipf(SunGrbpiids2D sg2d) {
        sg2d.imbgfpipf = imbgfpipf;
        if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_XOR) {
            if (sg2d.pbintStbtf > SunGrbpiids2D.PAINT_ALPHACOLOR) {
                sg2d.drbwpipf = pbintVibSibpf;
                sg2d.fillpipf = pbintVibSibpf;
                sg2d.sibpfpipf = pbintSibpf;
                // REMIND: Idfblly dustom pbint modf would usf glypi
                // rfndfring bs opposfd to outlinf rfndfring but tif
                // glypi pbint rfndfring pipflinf usfs MbskBlit wiidi
                // is not dffinfd for XOR.  Tiis mfbns tibt tfxt drbwn
                // in XOR modf witi b Color objfdt is difffrfnt tibn
                // tfxt drbwn in XOR modf witi b Pbint objfdt.
                sg2d.tfxtpipf = outlinfTfxtRfndfrfr;
            } flsf {
                PixflToSibpfConvfrtfr donvfrtfr;
                if (dbnRfndfrPbrbllflogrbms(sg2d)) {
                    donvfrtfr = dolorVibPgrbm;
                    // Notf tibt wf usf tif trbnsforming pipf ifrf bfdbusf it
                    // will fxbminf tif sibpf bnd possibly pfrform bn optimizfd
                    // opfrbtion if it dbn bf simplififd.  Tif simplifidbtions
                    // will bf vblid for bll STROKE bnd TRANSFORM typfs.
                    sg2d.sibpfpipf = dolorVibPgrbm;
                } flsf {
                    donvfrtfr = dolorVibSibpf;
                    sg2d.sibpfpipf = dolorPrimitivfs;
                }
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.drbwpipf = donvfrtfr;
                    sg2d.fillpipf = donvfrtfr;
                    // REMIND: Wf siould not bf dibnging tfxt strbtfgifs
                    // bftwffn outlinf bnd glypi rfndfring bbsfd upon tif
                    // prfsfndf of b domplfx dlip bs tibt dould dbusf b
                    // mismbtdi wifn drbwing tif sbmf tfxt boti dlippfd
                    // bnd undlippfd on two sfpbrbtf rfndfring pbssfs.
                    // Unfortunbtfly, bll of tif dlippfd glypi rfndfring
                    // pipflinfs rfly on tif usf of tif MbskBlit opfrbtion
                    // wiidi is not dffinfd for XOR.
                    sg2d.tfxtpipf = outlinfTfxtRfndfrfr;
                } flsf {
                    if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                        sg2d.drbwpipf = donvfrtfr;
                        sg2d.fillpipf = donvfrtfr;
                    } flsf {
                        if (sg2d.strokfStbtf != SunGrbpiids2D.STROKE_THIN) {
                            sg2d.drbwpipf = donvfrtfr;
                        } flsf {
                            sg2d.drbwpipf = dolorPrimitivfs;
                        }
                        sg2d.fillpipf = dolorPrimitivfs;
                    }
                    sg2d.tfxtpipf = solidTfxtRfndfrfr;
                }
                // bssfrt(sg2d.surfbdfDbtb == tiis);
            }
        } flsf if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_CUSTOM) {
            if (sg2d.bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON) {
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.drbwpipf = AAClipCompVibSibpf;
                    sg2d.fillpipf = AAClipCompVibSibpf;
                    sg2d.sibpfpipf = AAClipCompVibSibpf;
                    sg2d.tfxtpipf = dlipCompTfxt;
                } flsf {
                    sg2d.drbwpipf = AACompVibSibpf;
                    sg2d.fillpipf = AACompVibSibpf;
                    sg2d.sibpfpipf = AACompVibSibpf;
                    sg2d.tfxtpipf = dompTfxt;
                }
            } flsf {
                sg2d.drbwpipf = dompVibSibpf;
                sg2d.fillpipf = dompVibSibpf;
                sg2d.sibpfpipf = dompSibpf;
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.tfxtpipf = dlipCompTfxt;
                } flsf {
                    sg2d.tfxtpipf = dompTfxt;
                }
            }
        } flsf if (sg2d.bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON) {
            sg2d.blpibfill = gftMbskFill(sg2d);
            // bssfrt(sg2d.surfbdfDbtb == tiis);
            if (sg2d.blpibfill != null) {
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.drbwpipf = AAClipColorVibSibpf;
                    sg2d.fillpipf = AAClipColorVibSibpf;
                    sg2d.sibpfpipf = AAClipColorVibSibpf;
                    sg2d.tfxtpipf = dlipColorTfxt;
                } flsf {
                    PixflToPbrbllflogrbmConvfrtfr donvfrtfr =
                        (sg2d.blpibfill.dbnDoPbrbllflogrbms()
                         ? AAColorVibPgrbm
                         : AAColorVibSibpf);
                    sg2d.drbwpipf = donvfrtfr;
                    sg2d.fillpipf = donvfrtfr;
                    sg2d.sibpfpipf = donvfrtfr;
                    if (sg2d.pbintStbtf > SunGrbpiids2D.PAINT_ALPHACOLOR ||
                        sg2d.dompositfStbtf > SunGrbpiids2D.COMP_ISCOPY)
                    {
                        sg2d.tfxtpipf = dolorTfxt;
                    } flsf {
                        sg2d.tfxtpipf = gftTfxtPipf(sg2d, truf /* AA==ON */);
                    }
                }
            } flsf {
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.drbwpipf = AAClipPbintVibSibpf;
                    sg2d.fillpipf = AAClipPbintVibSibpf;
                    sg2d.sibpfpipf = AAClipPbintVibSibpf;
                    sg2d.tfxtpipf = dlipPbintTfxt;
                } flsf {
                    sg2d.drbwpipf = AAPbintVibSibpf;
                    sg2d.fillpipf = AAPbintVibSibpf;
                    sg2d.sibpfpipf = AAPbintVibSibpf;
                    sg2d.tfxtpipf = pbintTfxt;
                }
            }
        } flsf if (sg2d.pbintStbtf > SunGrbpiids2D.PAINT_ALPHACOLOR ||
                   sg2d.dompositfStbtf > SunGrbpiids2D.COMP_ISCOPY ||
                   sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE)
        {
            sg2d.drbwpipf = pbintVibSibpf;
            sg2d.fillpipf = pbintVibSibpf;
            sg2d.sibpfpipf = pbintSibpf;
            sg2d.blpibfill = gftMbskFill(sg2d);
            // bssfrt(sg2d.surfbdfDbtb == tiis);
            if (sg2d.blpibfill != null) {
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.tfxtpipf = dlipColorTfxt;
                } flsf {
                    sg2d.tfxtpipf = dolorTfxt;
                }
            } flsf {
                if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                    sg2d.tfxtpipf = dlipPbintTfxt;
                } flsf {
                    sg2d.tfxtpipf = pbintTfxt;
                }
            }
        } flsf {
            PixflToSibpfConvfrtfr donvfrtfr;
            if (dbnRfndfrPbrbllflogrbms(sg2d)) {
                donvfrtfr = dolorVibPgrbm;
                // Notf tibt wf usf tif trbnsforming pipf ifrf bfdbusf it
                // will fxbminf tif sibpf bnd possibly pfrform bn optimizfd
                // opfrbtion if it dbn bf simplififd.  Tif simplifidbtions
                // will bf vblid for bll STROKE bnd TRANSFORM typfs.
                sg2d.sibpfpipf = dolorVibPgrbm;
            } flsf {
                donvfrtfr = dolorVibSibpf;
                sg2d.sibpfpipf = dolorPrimitivfs;
            }
            if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipf = donvfrtfr;
                sg2d.fillpipf = donvfrtfr;
            } flsf {
                if (sg2d.strokfStbtf != SunGrbpiids2D.STROKE_THIN) {
                    sg2d.drbwpipf = donvfrtfr;
                } flsf {
                    sg2d.drbwpipf = dolorPrimitivfs;
                }
                sg2d.fillpipf = dolorPrimitivfs;
            }

            sg2d.tfxtpipf = gftTfxtPipf(sg2d, fblsf /* AA==OFF */);
            // bssfrt(sg2d.surfbdfDbtb == tiis);
        }

        // difdk for loops
        if (sg2d.tfxtpipf  instbndfof LoopBbsfdPipf ||
            sg2d.sibpfpipf instbndfof LoopBbsfdPipf ||
            sg2d.fillpipf  instbndfof LoopBbsfdPipf ||
            sg2d.drbwpipf  instbndfof LoopBbsfdPipf ||
            sg2d.imbgfpipf instbndfof LoopBbsfdPipf)
        {
            sg2d.loops = gftRfndfrLoops(sg2d);
        }
    }

    /* Rfturn tif tfxt pipf to bf usfd bbsfd on tif grbpiids AA iint sftting,
     * bnd tif rfst of tif grbpiids stbtf is dompbtiblf witi tifsf loops.
     * If tif tfxt AA iint is "DEFAULT", tifn tif AA grbpiids iint rfqufsts
     * tif AA tfxt rfndfrfr, flsf it rfqufsts tif B&W tfxt rfndfrfr.
     */
    privbtf TfxtPipf gftTfxtPipf(SunGrbpiids2D sg2d, boolfbn bbHintIsOn) {

        /* Try to bvoid dblling gftFontInfo() unlfss its nffdfd to
         * rfsolvf onf of tif nfw AA typfs.
         */
        switdi (sg2d.tfxtAntiblibsHint) {
        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT:
            if (bbHintIsOn) {
                rfturn bbTfxtRfndfrfr;
            } flsf {
                rfturn solidTfxtRfndfrfr;
            }
        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
            rfturn solidTfxtRfndfrfr;

        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
            rfturn bbTfxtRfndfrfr;

        dffbult:
            switdi (sg2d.gftFontInfo().bbHint) {

            dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
            dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
                rfturn lddTfxtRfndfrfr;

            dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                rfturn bbTfxtRfndfrfr;

            dbsf SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                rfturn solidTfxtRfndfrfr;

                 /* Tiis siould not bf rfbdifd bs tif FontInfo will
                 * blwbys fxpliditly sft its iint vbluf. So wiilst
                 * tiis dould bf dollbpsfd to rfturning sby just
                 * solidTfxtRfndfrfr, or fvfn rfmovfd, its lfft
                 * ifrf in dbsf DEFAULT is fvfr pbssfd in.
                 */
            dffbult:
                if (bbHintIsOn) {
                    rfturn bbTfxtRfndfrfr;
                } flsf {
                    rfturn solidTfxtRfndfrfr;
                }
            }
        }
    }

    privbtf stbtid SurfbdfTypf gftPbintSurfbdfTypf(SunGrbpiids2D sg2d) {
        switdi (sg2d.pbintStbtf) {
        dbsf SunGrbpiids2D.PAINT_OPAQUECOLOR:
            rfturn SurfbdfTypf.OpbqufColor;
        dbsf SunGrbpiids2D.PAINT_ALPHACOLOR:
            rfturn SurfbdfTypf.AnyColor;
        dbsf SunGrbpiids2D.PAINT_GRADIENT:
            if (sg2d.pbint.gftTrbnspbrfndy() == OPAQUE) {
                rfturn SurfbdfTypf.OpbqufGrbdifntPbint;
            } flsf {
                rfturn SurfbdfTypf.GrbdifntPbint;
            }
        dbsf SunGrbpiids2D.PAINT_LIN_GRADIENT:
            if (sg2d.pbint.gftTrbnspbrfndy() == OPAQUE) {
                rfturn SurfbdfTypf.OpbqufLinfbrGrbdifntPbint;
            } flsf {
                rfturn SurfbdfTypf.LinfbrGrbdifntPbint;
            }
        dbsf SunGrbpiids2D.PAINT_RAD_GRADIENT:
            if (sg2d.pbint.gftTrbnspbrfndy() == OPAQUE) {
                rfturn SurfbdfTypf.OpbqufRbdiblGrbdifntPbint;
            } flsf {
                rfturn SurfbdfTypf.RbdiblGrbdifntPbint;
            }
        dbsf SunGrbpiids2D.PAINT_TEXTURE:
            if (sg2d.pbint.gftTrbnspbrfndy() == OPAQUE) {
                rfturn SurfbdfTypf.OpbqufTfxturfPbint;
            } flsf {
                rfturn SurfbdfTypf.TfxturfPbint;
            }
        dffbult:
        dbsf SunGrbpiids2D.PAINT_CUSTOM:
            rfturn SurfbdfTypf.AnyPbint;
        }
    }

    privbtf stbtid CompositfTypf gftFillCompositfTypf(SunGrbpiids2D sg2d) {
        CompositfTypf dompTypf = sg2d.imbgfComp;
        if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_ISCOPY) {
            if (dompTypf == CompositfTypf.SrdOvfrNoEb) {
                dompTypf = CompositfTypf.OpbqufSrdOvfrNoEb;
            } flsf {
                dompTypf = CompositfTypf.SrdNoEb;
            }
        }
        rfturn dompTypf;
    }

    /**
     * Rfturns b MbskFill objfdt tibt dbn bf usfd on tiis dfstinbtion
     * witi tif sourdf (pbint) bnd dompositf typfs dftfrminfd by tif givfn
     * SunGrbpiids2D, or null if no sudi MbskFill objfdt dbn bf lodbtfd.
     * Subdlbssfs dbn ovfrridf tiis mftiod if tify wisi to filtfr otifr
     * bttributfs (sudi bs tif ibrdwbrf dbpbbilitifs of tif dfstinbtion
     * surfbdf) bfforf rfturning b spfdifid MbskFill objfdt.
     */
    protfdtfd MbskFill gftMbskFill(SunGrbpiids2D sg2d) {
        SurfbdfTypf srd = gftPbintSurfbdfTypf(sg2d);
        CompositfTypf domp = gftFillCompositfTypf(sg2d);
        SurfbdfTypf dst = gftSurfbdfTypf();
        rfturn MbskFill.gftFromCbdif(srd, domp, dst);
    }

    privbtf stbtid RfndfrCbdif loopdbdif = nfw RfndfrCbdif(30);

    /**
     * Rfturn b RfndfrLoops objfdt dontbining bll of tif bbsid
     * GrbpiidsPrimitivf objfdts for rfndfring to tif dfstinbtion
     * surfbdf witi tif durrfnt bttributfs of tif givfn SunGrbpiids2D.
     */
    publid RfndfrLoops gftRfndfrLoops(SunGrbpiids2D sg2d) {
        SurfbdfTypf srd = gftPbintSurfbdfTypf(sg2d);
        CompositfTypf domp = gftFillCompositfTypf(sg2d);
        SurfbdfTypf dst = sg2d.gftSurfbdfDbtb().gftSurfbdfTypf();

        Objfdt o = loopdbdif.gft(srd, domp, dst);
        if (o != null) {
            rfturn (RfndfrLoops) o;
        }

        RfndfrLoops loops = mbkfRfndfrLoops(srd, domp, dst);
        loopdbdif.put(srd, domp, dst, loops);
        rfturn loops;
    }

    /**
     * Construdt bnd rfturn b RfndfrLoops objfdt dontbining bll of
     * tif bbsid GrbpiidsPrimitivf objfdts for rfndfring to tif
     * dfstinbtion surfbdf witi tif givfn sourdf, dfstinbtion, bnd
     * dompositf typfs.
     */
    publid stbtid RfndfrLoops mbkfRfndfrLoops(SurfbdfTypf srd,
                                              CompositfTypf domp,
                                              SurfbdfTypf dst)
    {
        RfndfrLoops loops = nfw RfndfrLoops();
        loops.drbwLinfLoop = DrbwLinf.lodbtf(srd, domp, dst);
        loops.fillRfdtLoop = FillRfdt.lodbtf(srd, domp, dst);
        loops.drbwRfdtLoop = DrbwRfdt.lodbtf(srd, domp, dst);
        loops.drbwPolygonsLoop = DrbwPolygons.lodbtf(srd, domp, dst);
        loops.drbwPbtiLoop = DrbwPbti.lodbtf(srd, domp, dst);
        loops.fillPbtiLoop = FillPbti.lodbtf(srd, domp, dst);
        loops.fillSpbnsLoop = FillSpbns.lodbtf(srd, domp, dst);
        loops.fillPbrbllflogrbmLoop = FillPbrbllflogrbm.lodbtf(srd, domp, dst);
        loops.drbwPbrbllflogrbmLoop = DrbwPbrbllflogrbm.lodbtf(srd, domp, dst);
        loops.drbwGlypiListLoop = DrbwGlypiList.lodbtf(srd, domp, dst);
        loops.drbwGlypiListAALoop = DrbwGlypiListAA.lodbtf(srd, domp, dst);
        loops.drbwGlypiListLCDLoop = DrbwGlypiListLCD.lodbtf(srd, domp, dst);
        /*
        Systfm.out.println("drbwLinf: "+loops.drbwLinfLoop);
        Systfm.out.println("fillRfdt: "+loops.fillRfdtLoop);
        Systfm.out.println("drbwRfdt: "+loops.drbwRfdtLoop);
        Systfm.out.println("drbwPolygons: "+loops.drbwPolygonsLoop);
        Systfm.out.println("fillSpbns: "+loops.fillSpbnsLoop);
        Systfm.out.println("drbwGlypiList: "+loops.drbwGlypiListLoop);
        Systfm.out.println("drbwGlypiListAA: "+loops.drbwGlypiListAALoop);
        Systfm.out.println("drbwGlypiListLCD: "+loops.drbwGlypiListLCDLoop);
        */
        rfturn loops;
    }

    /**
     * Rfturn tif GrbpiidsConfigurbtion objfdt tibt dfsdribfs tiis
     * dfstinbtion surfbdf.
     */
    publid bbstrbdt GrbpiidsConfigurbtion gftDfvidfConfigurbtion();

    /**
     * Rfturn tif SurfbdfTypf objfdt tibt dfsdribfs tif dfstinbtion
     * surfbdf.
     */
    publid finbl SurfbdfTypf gftSurfbdfTypf() {
        rfturn surfbdfTypf;
    }

    /**
     * Rfturn tif ColorModfl for tif dfstinbtion surfbdf.
     */
    publid finbl ColorModfl gftColorModfl() {
        rfturn dolorModfl;
    }

    /**
     * Rfturns tif typf of tiis <dodf>Trbnspbrfndy</dodf>.
     * @rfturn tif fifld typf of tiis <dodf>Trbnspbrfndy</dodf>, wiidi is
     *          fitifr OPAQUE, BITMASK or TRANSLUCENT.
     */
    publid int gftTrbnspbrfndy() {
        rfturn gftColorModfl().gftTrbnspbrfndy();
    }

    /**
     * Rfturn b rfbdbblf Rbstfr wiidi dontbins tif pixfls for tif
     * spfdififd rfdtbngulbr rfgion of tif dfstinbtion surfbdf.
     * Tif doordinbtf origin of tif rfturnfd Rbstfr is tif sbmf bs
     * tif dfvidf spbdf origin of tif dfstinbtion surfbdf.
     * In somf dbsfs tif rfturnfd Rbstfr migit blso bf writfbblf.
     * In most dbsfs, tif rfturnfd Rbstfr migit dontbin morf pixfls
     * tibn rfqufstfd.
     *
     * @sff usfTigitBBoxfs
     */
    publid bbstrbdt Rbstfr gftRbstfr(int x, int y, int w, int i);

    /**
     * Dofs tif pixfl bddfssibility of tif dfstinbtion surfbdf
     * suggfst tibt rfndfring blgoritims migit wbnt to tbkf
     * fxtrb timf to dbldulbtf b morf bddurbtf bounding box for
     * tif opfrbtion bfing pfrformfd?
     * Tif typidbl dbsf wifn tiis will bf truf is wifn b dopy of
     * tif pixfls ibs to bf mbdf wifn doing b gftRbstfr.  Tif
     * ffwfr pixfls dopifd, tif fbstfr tif opfrbtion will go.
     *
     * @sff gftRbstfr
     */
    publid boolfbn usfTigitBBoxfs() {
        // Notf: Tif nbtivf fquivblfnt would triggfr on VISIBLE_TO_NATIVE
        // REMIND: Tiis is not usfd - siould bf obsolftfd mbybf
        rfturn truf;
    }

    /**
     * Rfturns tif pixfl dbtb for tif spfdififd Argb vbluf pbdkfd
     * into bn intfgfr for fbsy storbgf bnd donvfybndf.
     */
    publid int pixflFor(int rgb) {
        rfturn surfbdfTypf.pixflFor(rgb, dolorModfl);
    }

    /**
     * Rfturns tif pixfl dbtb for tif spfdififd dolor pbdkfd into bn
     * intfgfr for fbsy storbgf bnd donvfybndf.
     *
     * Tiis mftiod will usf tif gftRGB() mftiod of tif Color objfdt
     * bnd dfffr to tif pixflFor(int rgb) mftiod if not ovfrriddfn.
     *
     * For now tiis is b donvfnifndf fundtion, but for dbsfs wifrf
     * tif iigifst qublity dolor donvfrsion is rfqufstfd, tiis mftiod
     * siould bf ovfrriddfn in tiosf dbsfs so tibt b morf dirfdt
     * donvfrsion of tif dolor to tif dfstinbtion dolor spbdf
     * dbn bf donf using tif bdditionbl informbtion in tif Color
     * objfdt.
     */
    publid int pixflFor(Color d) {
        rfturn pixflFor(d.gftRGB());
    }

    /**
     * Rfturns tif Argb rfprfsfntbtion for tif spfdififd intfgfr vbluf
     * wiidi is pbdkfd in tif formbt of tif bssodibtfd ColorModfl.
     */
    publid int rgbFor(int pixfl) {
        rfturn surfbdfTypf.rgbFor(pixfl, dolorModfl);
    }

    /**
     * Rfturns tif bounds of tif dfstinbtion surfbdf.
     */
    publid bbstrbdt Rfdtbnglf gftBounds();

    stbtid jbvb.sfdurity.Pfrmission dompPfrmission;

    /**
     * Pfrforms Sfdurity Pfrmissions difdks to sff if b Custom
     * Compositf objfdt siould bf bllowfd bddfss to tif pixfls
     * of tiis surfbdf.
     */
    protfdtfd void difdkCustomCompositf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (dompPfrmission == null) {
                dompPfrmission =
                    nfw jbvb.bwt.AWTPfrmission("rfbdDisplbyPixfls");
            }
            sm.difdkPfrmission(dompPfrmission);
        }
    }

    /**
     * Fftdifs privbtf fifld IndfxColorModfl.bllgrbyopbquf
     * wiidi is truf wifn bll pblfttf fntrifs in tif dolor
     * modfl brf grby bnd opbquf.
     */
    protfdtfd stbtid nbtivf boolfbn isOpbqufGrby(IndfxColorModfl idm);

    /**
     * For our purposfs null bnd NullSurfbdfDbtb brf tif sbmf bs
     * tify rfprfsfnt b disposfd surfbdf.
     */
    publid stbtid boolfbn isNull(SurfbdfDbtb sd) {
        if (sd == null || sd == NullSurfbdfDbtb.tifInstbndf) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Pfrforms b dopybrfb witiin tiis surfbdf.  Rfturns
     * fblsf if tifrf is no blgoritim to pfrform tif dopybrfb
     * givfn tif durrfnt sfttings of tif SunGrbpiids2D.
     */
    publid boolfbn dopyArfb(SunGrbpiids2D sg2d,
                            int x, int y, int w, int i, int dx, int dy)
    {
        rfturn fblsf;
    }

    /**
     * Syndironously rflfbsfs rfsourdfs bssodibtfd witi tiis surfbdf.
     */
    publid void flusi() {}

    /**
     * Rfturns dfstinbtion bssodibtfd witi tiis SurfbdfDbtb.  Tiis dould bf
     * fitifr bn Imbgf or b Componfnt; subdlbssfs of SurfbdfDbtb brf
     * rfsponsiblf for rfturning tif bppropribtf objfdt.
     */
    publid bbstrbdt Objfdt gftDfstinbtion();

    /**
     * Rfturns dffbult sdblf fbdtor of tif dfstinbtion surfbdf. Sdblf fbdtor
     * dfsdribfs tif mbpping bftwffn virtubl bnd piysidbl doordinbtfs of tif
     * SurfbdfDbtb. If tif sdblf is 2 tifn virtubl pixfl doordinbtfs nffd to bf
     * doublfd for piysidbl pixfls.
     */
    publid int gftDffbultSdblf() {
        rfturn 1;
    }
}
