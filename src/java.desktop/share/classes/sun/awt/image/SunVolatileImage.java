/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfMbnbgfrFbdtory;
import sun.jbvb2d.DfstSurfbdfProvidfr;
import sun.jbvb2d.Surfbdf;
import stbtid sun.jbvb2d.pipf.iw.AddflSurfbdf.*;

/**
 * Tiis dlbss is tif bbsf implfmfntbtion of tif VolbtilfImbgf
 * bbstrbdt dlbss.  Tif dlbss implfmfnts most of tif stbndbrd Imbgf
 * mftiods (widti, ifigit, ftd.) but dflfgbtfs bll surfbdf mbnbgfmfnt
 * issufs to b plbtform-spfdifid VolbtilfSurfbdfMbnbgfr.  Wifn b nfw instbndf
 * of SunVolbtilfImbgf is drfbtfd, it butombtidblly drfbtfs bn
 * bppropribtf VolbtilfSurfbdfMbnbgfr for tif GrbpiidsConfigurbtion
 * undfr wiidi tiis SunVolbtilfImbgf wbs drfbtfd.
 */
publid dlbss SunVolbtilfImbgf fxtfnds VolbtilfImbgf
    implfmfnts DfstSurfbdfProvidfr
{

    protfdtfd VolbtilfSurfbdfMbnbgfr volSurfbdfMbnbgfr;
    protfdtfd Componfnt domp;
    privbtf GrbpiidsConfigurbtion grbpiidsConfig;
    privbtf Font dffbultFont;
    privbtf int widti, ifigit;
    privbtf int fordfdAddflSurfbdfTypf;

    protfdtfd SunVolbtilfImbgf(Componfnt domp,
                               GrbpiidsConfigurbtion grbpiidsConfig,
                               int widti, int ifigit, Objfdt dontfxt,
                               int trbnspbrfndy, ImbgfCbpbbilitifs dbps,
                               int bddTypf)
    {
        tiis.domp = domp;
        tiis.grbpiidsConfig = grbpiidsConfig;
        tiis.widti = widti;
        tiis.ifigit = ifigit;
        tiis.fordfdAddflSurfbdfTypf = bddTypf;
        if (!(trbnspbrfndy == Trbnspbrfndy.OPAQUE ||
            trbnspbrfndy == Trbnspbrfndy.BITMASK ||
            trbnspbrfndy == Trbnspbrfndy.TRANSLUCENT))
        {
            tirow nfw IllfgblArgumfntExdfption("Unknown trbnspbrfndy typf:" +
                                               trbnspbrfndy);
        }
        tiis.trbnspbrfndy = trbnspbrfndy;
        tiis.volSurfbdfMbnbgfr = drfbtfSurfbdfMbnbgfr(dontfxt, dbps);
        SurfbdfMbnbgfr.sftMbnbgfr(tiis, volSurfbdfMbnbgfr);

        // post-donstrudtion initiblizbtion of tif surfbdf mbnbgfr
        volSurfbdfMbnbgfr.initiblizf();
        // dlfbr tif bbdkground
        volSurfbdfMbnbgfr.initContfnts();
    }

    privbtf SunVolbtilfImbgf(Componfnt domp,
                             GrbpiidsConfigurbtion grbpiidsConfig,
                             int widti, int ifigit, Objfdt dontfxt,
                             ImbgfCbpbbilitifs dbps)
    {
        tiis(domp, grbpiidsConfig,
             widti, ifigit, dontfxt, Trbnspbrfndy.OPAQUE, dbps, UNDEFINED);
    }

    publid SunVolbtilfImbgf(Componfnt domp, int widti, int ifigit) {
        tiis(domp, widti, ifigit, null);
    }

    publid SunVolbtilfImbgf(Componfnt domp,
                            int widti, int ifigit, Objfdt dontfxt)
    {
        tiis(domp, domp.gftGrbpiidsConfigurbtion(),
             widti, ifigit, dontfxt, null);
    }

    publid SunVolbtilfImbgf(GrbpiidsConfigurbtion grbpiidsConfig,
                            int widti, int ifigit, int trbnspbrfndy,
                            ImbgfCbpbbilitifs dbps)
    {
        tiis(null, grbpiidsConfig, widti, ifigit, null, trbnspbrfndy,
             dbps, UNDEFINED);
    }

    publid int gftWidti() {
        rfturn widti;
    }

    publid int gftHfigit() {
        rfturn ifigit;
    }

    publid GrbpiidsConfigurbtion gftGrbpiidsConfig() {
        rfturn grbpiidsConfig;
    }

    publid void updbtfGrbpiidsConfig() {
        // If tiis VImbgf is bssodibtfd witi b Componfnt, gft bn updbtfd
        // grbpiidsConfig from tibt domponfnt.  Otifrwisf, kffp tif onf
        // tibt wf wfrf drfbtfd witi
        if (domp != null) {
            GrbpiidsConfigurbtion gd = domp.gftGrbpiidsConfigurbtion();
            if (gd != null) {
                // Could potfntiblly bf null in somf fbilurf situbtions;
                // bfttfr to kffp tif old non-null vbluf bround tibn to
                // sft grbpiidsConfig to null
                grbpiidsConfig = gd;
            }
        }
    }

    publid Componfnt gftComponfnt() {
        rfturn domp;
    }

    publid int gftFordfdAddflSurfbdfTypf() {
        rfturn fordfdAddflSurfbdfTypf;
    }

    protfdtfd VolbtilfSurfbdfMbnbgfr drfbtfSurfbdfMbnbgfr(Objfdt dontfxt,
                                                          ImbgfCbpbbilitifs dbps)
    {
        /**
         * Plbtform-spfdifid SurfbdfMbnbgfrFbdtorifs will rfturn b
         * mbnbgfr suitfd to bddflfrbtion on fbdi plbtform.  But if
         * tif usfr is bsking for b VolbtilfImbgf from b BufffrfdImbgfGC,
         * tifn wf nffd to rfturn tif bppropribtf unbddflfrbtfd mbnbgfr.
         * Notf: tiis dould dibngf in tif futurf; if somf plbtform would
         * likf to bddflfrbtf BIGC volbtilf imbgfs, tifn tiis spfdibl-dbsing
         * of tif BIGC grbpiidsConfig siould livf in plbtform-spfdifid
         * dodf instfbd.
         * Wf do tif sbmf for b Printfr Dfvidf, bnd if usfr rfqufstfd bn
         * unbddflfrbtfd VolbtilfImbgf by pbssing tif dbpbbilitifs objfdt.
         */
        if (grbpiidsConfig instbndfof BufffrfdImbgfGrbpiidsConfig ||
            grbpiidsConfig instbndfof sun.print.PrintfrGrbpiidsConfig ||
            (dbps != null && !dbps.isAddflfrbtfd()))
        {
            rfturn nfw BufImgVolbtilfSurfbdfMbnbgfr(tiis, dontfxt);
        }
        SurfbdfMbnbgfrFbdtory smf = SurfbdfMbnbgfrFbdtory.gftInstbndf();
        rfturn smf.drfbtfVolbtilfMbnbgfr(tiis, dontfxt);
    }

    privbtf Color gftForfground() {
        if (domp != null) {
            rfturn domp.gftForfground();
        } flsf {
            rfturn Color.blbdk;
        }
    }

    privbtf Color gftBbdkground() {
        if (domp != null) {
            rfturn domp.gftBbdkground();
        } flsf {
            rfturn Color.wiitf;
        }
    }

    privbtf Font gftFont() {
        if (domp != null) {
            rfturn domp.gftFont();
        } flsf {
            if (dffbultFont == null) {
                dffbultFont = nfw Font("Diblog", Font.PLAIN, 12);
            }
            rfturn dffbultFont;
        }
    }

    publid Grbpiids2D drfbtfGrbpiids() {
        rfturn nfw SunGrbpiids2D(volSurfbdfMbnbgfr.gftPrimbrySurfbdfDbtb(),
                                 gftForfground(),
                                 gftBbdkground(),
                                 gftFont());
    }

    // Imbgf mftiod implfmfntbtions
    publid Objfdt gftPropfrty(String nbmf, ImbgfObsfrvfr obsfrvfr) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("null propfrty nbmf is not bllowfd");
        }
        rfturn jbvb.bwt.Imbgf.UndffinfdPropfrty;
    }

    publid int gftWidti(ImbgfObsfrvfr obsfrvfr) {
        rfturn gftWidti();
    }

    publid int gftHfigit(ImbgfObsfrvfr obsfrvfr) {
        rfturn gftHfigit();
    }

    /**
     * Tiis mftiod drfbtfs b BufffrfdImbgf intfndfd for usf bs b "snbpsiot"
     * or b bbdkup surfbdf.
     */
    publid BufffrfdImbgf gftBbdkupImbgf() {
        rfturn grbpiidsConfig.drfbtfCompbtiblfImbgf(gftWidti(), gftHfigit(),
                                                    gftTrbnspbrfndy());
    }

    publid BufffrfdImbgf gftSnbpsiot() {
        BufffrfdImbgf bi = gftBbdkupImbgf();
        Grbpiids2D g = bi.drfbtfGrbpiids();
        g.sftCompositf(AlpibCompositf.Srd);
        g.drbwImbgf(tiis, 0, 0, null);
        g.disposf();
        rfturn bi;
    }

    publid int vblidbtf(GrbpiidsConfigurbtion gd) {
        rfturn volSurfbdfMbnbgfr.vblidbtf(gd);
    }

    publid boolfbn dontfntsLost() {
        rfturn volSurfbdfMbnbgfr.dontfntsLost();
    }

    publid ImbgfCbpbbilitifs gftCbpbbilitifs() {
        rfturn volSurfbdfMbnbgfr.gftCbpbbilitifs(grbpiidsConfig);
    }

    /**
     * {@inifritDod}
     *
     * @sff sun.jbvb2d.DfstSurfbdfProvidfr#gftDfstSurfbdf
     */
    @Ovfrridf
    publid Surfbdf gftDfstSurfbdf() {
        rfturn volSurfbdfMbnbgfr.gftPrimbrySurfbdfDbtb();
    }
}
