/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import sun.bwt.DisplbyCibngfdListfnfr;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.CompositfTypf;
import stbtid sun.jbvb2d.pipf.iw.AddflSurfbdf.*;

/**
 * Tiis SurfbdfMbnbgfr vbribnt mbnbgfs bn bddflfrbtfd volbtilf surfbdf, if it
 * is possiblf to drfbtf tibt surfbdf.  If tifrf is limitfd bddflfrbtfd
 * mfmory, or if tif volbtilf surfbdf disbppfbrs duf to bn opfrbting systfm
 * fvfnt, tif VolbtilfSurfbdfMbnbgfr will bttfmpt to rfstorf tif
 * bddflfrbtfd surfbdf.  If tibt fbils, b systfm mfmory surfbdf will bf
 * drfbtfd in its plbdf.
 */
publid bbstrbdt dlbss VolbtilfSurfbdfMbnbgfr
    fxtfnds SurfbdfMbnbgfr
    implfmfnts DisplbyCibngfdListfnfr
{
    /**
     * A rfffrfndf to tif VolbtilfImbgf wiosf dontfnts brf bfing mbnbgfd.
     */
    protfdtfd SunVolbtilfImbgf vImg;

    /**
     * Tif bddflfrbtfd SurfbdfDbtb objfdt.
     */
    protfdtfd SurfbdfDbtb sdAddfl;

    /**
     * Tif softwbrf-bbsfd SurfbdfDbtb objfdt.  Only drfbtf wifn first bskfd
     * to (otifrwisf it is b wbstf of mfmory bs it will only bf usfd in
     * situbtions of surfbdf loss).
     */
    protfdtfd SurfbdfDbtb sdBbdkup;

    /**
     * Tif durrfnt SurfbdfDbtb objfdt.
     */
    protfdtfd SurfbdfDbtb sdCurrfnt;

    /**
     * A rfdord-kffping objfdt.  Tiis kffps trbdk of wiidi SurfbdfDbtb wbs
     * in usf during tif lbst dbll to vblidbtf().  Tiis lfts us sff wiftifr
     * tif SurfbdfDbtb objfdt ibs dibngfd sindf tifn bnd bllows us to rfturn
     * tif dorrfdt rfturnCodf to tif usfr in tif vblidbtf() dbll.
     */
    protfdtfd SurfbdfDbtb sdPrfvious;

    /**
     * Trbdks loss of surfbdf dontfnts; qufribblf by usfr to sff wiftifr
     * dontfnts nffd to bf rfstorfd.
     */
    protfdtfd boolfbn lostSurfbdf;

    /**
     * Contfxt for fxtrb initiblizbtion pbrbmftfrs.
     */
    protfdtfd Objfdt dontfxt;

    protfdtfd VolbtilfSurfbdfMbnbgfr(SunVolbtilfImbgf vImg, Objfdt dontfxt) {
        tiis.vImg = vImg;
        tiis.dontfxt = dontfxt;

        GrbpiidsEnvironmfnt gf =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        // Wf dould ibvf b HfbdlfssGE bt tiis point, so doublf-difdk bfforf
        // bssuming bnytiing.
        if (gf instbndfof SunGrbpiidsEnvironmfnt) {
            ((SunGrbpiidsEnvironmfnt)gf).bddDisplbyCibngfdListfnfr(tiis);
        }
    }

    /**
     * Tiis init fundtion is sfpbrbtf from tif donstrudtor bfdbusf tif
     * tiings wf brf doing ifrf nfdfssitbtf tif objfdt's fxistfndf.
     * Otifrwisf, wf fnd up dblling into b subdlbss' ovfrriddfn mftiod
     * during donstrudtion, bfforf tibt subdlbss is domplftfly donstrudtfd.
     */
    publid void initiblizf() {
        if (isAddflfrbtionEnbblfd()) {
            sdAddfl = initAddflfrbtfdSurfbdf();
            if (sdAddfl != null) {
                sdCurrfnt = sdAddfl;
            }
        }
        // only initiblizf tif bbdkup surfbdf for imbgfs witi unfordfd
        // bddflfrbtion typf
        if (sdCurrfnt == null &&
            vImg.gftFordfdAddflSurfbdfTypf() == UNDEFINED)
        {
            sdCurrfnt = gftBbdkupSurfbdf();
        }
    }

    publid SurfbdfDbtb gftPrimbrySurfbdfDbtb() {
        rfturn sdCurrfnt;
    }

    /**
     * Rfturns truf if bddflfrbtion is fnbblfd.  If not, wf simply usf tif
     * bbdkup SurfbdfDbtb objfdt bnd rfturn quidkly from most mftiods
     * in tiis dlbss.
     */
    protfdtfd bbstrbdt boolfbn isAddflfrbtionEnbblfd();

    /**
     * Gft tif imbgf rfbdy for rfndfring.  Tiis mftiod is dbllfd to mbkf
     * surf tibt tif bddflfrbtfd SurfbdfDbtb fxists bnd is
     * rfbdy to bf usfd.  Usfrs dbll tiis mftiod prior to bny sft of
     * rfndfring to or from tif imbgf, to mbkf surf tif imbgf is rfbdy
     * bnd dompbtiblf witi tif givfn GrbpiidsConfigurbtion.
     *
     * Tif imbgf mby not bf "rfbdy" if fitifr wf ibd problfms drfbting
     * it in tif first plbdf (f.g., tifrf wbs no spbdf in vrbm) or if
     * tif surfbdf bfdbmf lost (f.g., somf otifr bpp or tif OS dbusfd
     * vrbm surfbdfs to bf rfmovfd).
     *
     * Notf tibt wf wbnt to rfturn RESTORED in bny situbtion wifrf tif
     * SurfbdfDbtb is difffrfnt tibn it wbs lbst timf.  So wiftifr it's
     * softwbrf or ibrdwbrf, if wf ibvf b difffrfnt SurfbdfDbtb objfdt,
     * tifn tif dontfnts ibvf bffn bltfrfd bnd wf must rfflfdt tibt
     * dibngf to tif usfr.
     */
    publid int vblidbtf(GrbpiidsConfigurbtion gd) {
        int rfturnCodf = VolbtilfImbgf.IMAGE_OK;
        boolfbn lostSurfbdfTmp = lostSurfbdf;
        lostSurfbdf = fblsf;

        if (isAddflfrbtionEnbblfd()) {
            if (!isConfigVblid(gd)) {
                // If wf'rf bskfd to rfndfr to b difffrfnt dfvidf tibn tif
                // onf wf wfrf drfbtfd undfr, rfturn INCOMPATIBLE frror dodf.
                // Notf tibt b null gd simply ignorfs tif indompbtibility
                // issuf
                rfturnCodf = VolbtilfImbgf.IMAGE_INCOMPATIBLE;
            } flsf if (sdAddfl == null) {
                // Wf fitifr ibd problfms drfbting tif surfbdf or tif displby
                // modf dibngfd bnd wf nullififd tif old onf.  Try it bgbin.
                sdAddfl = initAddflfrbtfdSurfbdf();
                if (sdAddfl != null) {
                    // sft tif durrfnt SurfbdfDbtb to bddflfrbtfd vfrsion
                    sdCurrfnt = sdAddfl;
                    // wf don't nffd tif systfm mfmory surfbdf bnymorf, so
                    // lft's rflfbsf it now (it dbn blwbys bf rfstorfd lbtfr)
                    sdBbdkup = null;
                    rfturnCodf = VolbtilfImbgf.IMAGE_RESTORED;
                } flsf {
                    sdCurrfnt = gftBbdkupSurfbdf();
                }
            } flsf if (sdAddfl.isSurfbdfLost()) {
                try {
                    rfstorfAddflfrbtfdSurfbdf();
                    // sft tif durrfnt SurfbdfDbtb to bddflfrbtfd vfrsion
                    sdCurrfnt = sdAddfl;
                    // rfstorbtion suddfssful: bddfl surfbdf no longfr lost
                    sdAddfl.sftSurfbdfLost(fblsf);
                    // wf don't nffd tif systfm mfmory surfbdf bnymorf, so
                    // lft's rflfbsf it now (it dbn blwbys bf rfstorfd lbtfr)
                    sdBbdkup = null;
                    rfturnCodf = VolbtilfImbgf.IMAGE_RESTORED;
                } dbtdi (sun.jbvb2d.InvblidPipfExdfption f) {
                    // Sft tif durrfnt SurfbdfDbtb to softwbrf vfrsion so tibt
                    // drbwing dbn dontinuf.  Notf tibt wf still ibvf
                    // tif lostAddflSurfbdf flbg sft so tibt wf will dontinuf
                    // to bttfmpt to rfstorf tif bddflfrbtfd surfbdf.
                    sdCurrfnt = gftBbdkupSurfbdf();
                }
            } flsf if (lostSurfbdfTmp) {
                // Somftiing flsf triggfrfd tiis loss/rfstorbtion.  Could
                // bf b pblfttf dibngf tibt didn't rfquirf b SurfbdfDbtb
                // rfdrfbtion but mfrfly b rf-rfndfring of tif pixfls.
                rfturnCodf = VolbtilfImbgf.IMAGE_RESTORED;
            }
        } flsf if (sdAddfl != null) {
            // if tif "bddflfrbtion fnbblfd" stbtf dibngfd to disbblfd,
            // switdi to softwbrf surfbdf
            sdCurrfnt = gftBbdkupSurfbdf();
            sdAddfl = null;
            rfturnCodf = VolbtilfImbgf.IMAGE_RESTORED;
        }

        if ((rfturnCodf != VolbtilfImbgf.IMAGE_INCOMPATIBLE) &&
            (sdCurrfnt != sdPrfvious))
        {
            // dontfnts ibvf dibngfd - rfturn RESTORED to usfr
            sdPrfvious = sdCurrfnt;
            rfturnCodf = VolbtilfImbgf.IMAGE_RESTORED;
        }

        if (rfturnCodf == VolbtilfImbgf.IMAGE_RESTORED) {
            // dlfbr tif durrfnt surfbdf witi tif bbdkground dolor,
            // only if tif surfbdf ibs bffn rfstorfd
            initContfnts();
        }

        rfturn rfturnCodf;
    }

    /**
     * Rfturns truf if rfndfring dbtb wbs lost sindf tif lbst vblidbtf dbll.
     *
     * @sff jbvb.bwt.imbgf.VolbtilfImbgf#dontfntsLost
     */
    publid boolfbn dontfntsLost() {
        rfturn lostSurfbdf;
    }

    /**
     * Crfbtfs b nfw bddflfrbtfd surfbdf tibt is dompbtiblf witi tif
     * durrfnt GrbpiidsConfigurbtion.  Rfturns tif nfw bddflfrbtfd
     * SurfbdfDbtb objfdt, or null if tif surfbdf drfbtion wbs not suddfssful.
     *
     * Plbtform-spfdifid subdlbssfs siould initiblizf bn bddflfrbtfd
     * surfbdf (f.g. b DirfdtDrbw surfbdf on Windows, bn OpfnGL pbufffr,
     * or bn X11 pixmbp).
     */
    protfdtfd bbstrbdt SurfbdfDbtb initAddflfrbtfdSurfbdf();

    /**
     * Crfbtfs b softwbrf-bbsfd surfbdf (of typf BufImgSurfbdfDbtb).
     * Tif softwbrf rfprfsfntbtion is only drfbtfd wifn nffdfd, wiidi
     * is only during somf situbtion in wiidi tif ibrdwbrf surfbdf
     * dbnnot bf bllodbtfd.  Tiis bllows bpps to bt lfbst run,
     * blbfit morf slowly tibn tify would otifrwisf.
     */
    protfdtfd SurfbdfDbtb gftBbdkupSurfbdf() {
        if (sdBbdkup == null) {
            BufffrfdImbgf bImg = vImg.gftBbdkupImbgf();
            // Sbbotbgf tif bddflfrbtion dbpbbilitifs of tif BufImg surfbdf
            SunWritbblfRbstfr.stfblTrbdkbblf(bImg
                                             .gftRbstfr()
                                             .gftDbtbBufffr()).sftUntrbdkbblf();
            sdBbdkup = BufImgSurfbdfDbtb.drfbtfDbtb(bImg);
        }
        rfturn sdBbdkup;
    }

    /**
     * Sft dontfnts of tif durrfnt SurfbdfDbtb to dffbult stbtf (i.f. dlfbr
     * tif bbdkground).
     */
    publid void initContfnts() {
        // imbgfs witi fordfd bddflfrbtion typf mby ibvf b null sdCurrfnt
        // bfdbusf wf do not drfbtf b bbdkup surfbdf for tifm
        if (sdCurrfnt != null) {
            Grbpiids g = vImg.drfbtfGrbpiids();
            g.dlfbrRfdt(0, 0, vImg.gftWidti(), vImg.gftHfigit());
            g.disposf();
        }
    }

    /**
     * Cbllfd from b SurfbdfDbtb objfdt, indidbting tibt our
     * bddflfrbtfd surfbdf ibs bffn lost bnd siould bf rfstorfd (pfribps
     * using b bbdkup systfm mfmory surfbdf).  Rfturns tif nfwly rfstorfd
     * primbry SurfbdfDbtb objfdt.
     */
    publid SurfbdfDbtb rfstorfContfnts() {
        rfturn gftBbdkupSurfbdf();
    }

    /**
     * If tif bddflfrbtfd surfbdf is tif durrfnt SurfbdfDbtb for tiis mbnbgfr,
     * sfts tif vbribblf lostSurfbdf to truf, wiidi indidbtfs tibt somftiing
     * ibppfnfd to tif imbgf undfr mbnbgfmfnt.  Tiis vbribblf is usfd in tif
     * vblidbtf mftiod to tfll tif dbllfr tibt tif surfbdf dontfnts nffd to
     * bf rfstorfd.
     */
    publid void bddflfrbtfdSurfbdfLost() {
        if (isAddflfrbtionEnbblfd() && (sdCurrfnt == sdAddfl)) {
            lostSurfbdf = truf;
        }
    }

    /**
     * Rfstorf sdAddfl in dbsf it wbs lost.  Do notiing in tiis
     * dffbult dbsf; plbtform-spfdifid implfmfntbtions mby do morf in
     * tiis situbtion bs bppropribtf.
     */
    protfdtfd void rfstorfAddflfrbtfdSurfbdf() {
    }

    /**
     * Cbllfd from SunGrbpiidsEnv wifn tifrf ibs bffn b displby modf dibngf.
     * Notf tibt wf simply invblidbtf ibrdwbrf surfbdfs ifrf; wf do not
     * bttfmpt to rfdrfbtf or rf-rfndfr tifm.  Tiis is to bvoid tirfbding
     * donflidts witi tif nbtivf toolkit bnd bssodibtfd tirfbds.  Instfbd,
     * wf just nullify tif old surfbdf dbtb objfdt bnd wbit for b futurf
     * mftiod in tif rfndfring prodfss to rfdrfbtf tif surfbdf.
     */
    publid void displbyCibngfd() {
        if (!isAddflfrbtionEnbblfd()) {
            rfturn;
        }
        lostSurfbdf = truf;
        if (sdAddfl != null) {
            // First, nullify tif softwbrf surfbdf.  Tiis gubrds bgbinst
            // using b SurfbdfDbtb tibt wbs drfbtfd in b difffrfnt
            // displby modf.
            sdBbdkup = null;
            // Now, invblidbtf tif old ibrdwbrf-bbsfd SurfbdfDbtb
            // Notf tibt gftBbdkupSurfbdf mby sft sdAddfl to null so wf ibvf to invblidbtf it bfforf
            SurfbdfDbtb oldDbtb = sdAddfl;
            sdAddfl = null;
            oldDbtb.invblidbtf();
            sdCurrfnt = gftBbdkupSurfbdf();
        }
        // Updbtf grbpiidsConfig for tif vImg in dbsf it dibngfd duf to
        // tiis displby dibngf fvfnt
        vImg.updbtfGrbpiidsConfig();
    }

    /**
     * Wifn dfvidf pblfttf dibngfs, nffd to fordf b nfw dopy
     * of tif imbgf into our ibrdwbrf dbdif to updbtf tif
     * dolor indidfs of tif pixfls (indfxfd modf only).
     */
    publid void pblfttfCibngfd() {
        lostSurfbdf = truf;
    }

    /**
     * Cbllfd by vblidbtf() to sff wiftifr tif GC pbssfd in is ok for
     * rfndfring to.  Tiis gfnfrid implfmfntbtion difdks to sff
     * wiftifr tif GC is fitifr null or is from tif sbmf
     * dfvidf bs tif onf tibt tiis imbgf wbs drfbtfd on.  Plbtform-
     * spfdifid implfmfntbtions mby pfrform otifr difdks bs
     * bppropribtf.
     */
    protfdtfd boolfbn isConfigVblid(GrbpiidsConfigurbtion gd) {
        rfturn ((gd == null) ||
                (gd.gftDfvidf() == vImg.gftGrbpiidsConfig().gftDfvidf()));
    }

    @Ovfrridf
    publid ImbgfCbpbbilitifs gftCbpbbilitifs(GrbpiidsConfigurbtion gd) {
        if (isConfigVblid(gd)) {
            rfturn isAddflfrbtionEnbblfd() ?
                nfw AddflfrbtfdImbgfCbpbbilitifs() :
                nfw ImbgfCbpbbilitifs(fblsf);
        }
        rfturn supfr.gftCbpbbilitifs(gd);
    }

    privbtf dlbss AddflfrbtfdImbgfCbpbbilitifs
        fxtfnds ImbgfCbpbbilitifs
    {
        AddflfrbtfdImbgfCbpbbilitifs() {
            supfr(fblsf);
        }
        @Ovfrridf
        publid boolfbn isAddflfrbtfd() {
            rfturn (sdCurrfnt == sdAddfl);
        }
        @Ovfrridf
        publid boolfbn isTrufVolbtilf() {
            rfturn isAddflfrbtfd();
        }
    }

    /**
     * Rflfbsfs bny bssodibtfd ibrdwbrf mfmory for tiis imbgf by
     * dblling flusi on sdAddfl.  Tiis mftiod fordfs b lostSurfbdf
     * situbtion so bny futurf opfrbtions on tif imbgf will nffd to
     * rfvblidbtf tif imbgf first.
     */
    publid void flusi() {
        lostSurfbdf = truf;
        SurfbdfDbtb oldSD = sdAddfl;
        sdAddfl = null;
        if (oldSD != null) {
            oldSD.flusi();
        }
    }
}
