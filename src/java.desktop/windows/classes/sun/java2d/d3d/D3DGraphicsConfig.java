/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.d3d;

import jbvb.bwt.AWTExdfption;
import jbvb.bwt.BufffrCbpbbilitifs;
import jbvb.bwt.BufffrCbpbbilitifs.FlipContfnts;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import sun.bwt.Win32GrbpiidsConfig;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.SurfbdfMbnbgfr;
import sun.bwt.windows.WComponfntPffr;
import sun.jbvb2d.Surfbdf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.iw.AddflDfvidfEvfntNotififr;
import sun.jbvb2d.pipf.iw.AddflTypfdVolbtilfImbgf;
import sun.jbvb2d.pipf.iw.AddflGrbpiidsConfig;
import sun.jbvb2d.pipf.iw.AddflSurfbdf;
import sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs;
import stbtid sun.jbvb2d.pipf.iw.AddflSurfbdf.*;
import stbtid sun.jbvb2d.d3d.D3DContfxt.D3DContfxtCbps.*;
import sun.jbvb2d.pipf.iw.AddflDfvidfEvfntListfnfr;

publid dlbss D3DGrbpiidsConfig
    fxtfnds Win32GrbpiidsConfig
    implfmfnts AddflGrbpiidsConfig
{
    privbtf stbtid ImbgfCbpbbilitifs imbgfCbps = nfw D3DImbgfCbps();

    privbtf BufffrCbpbbilitifs bufffrCbps;
    privbtf D3DGrbpiidsDfvidf dfvidf;

    protfdtfd D3DGrbpiidsConfig(D3DGrbpiidsDfvidf dfvidf) {
        supfr(dfvidf, 0);
        tiis.dfvidf = dfvidf;
    }

    publid SurfbdfDbtb drfbtfMbnbgfdSurfbdf(int w, int i, int trbnspbrfndy) {
        rfturn D3DSurfbdfDbtb.drfbtfDbtb(tiis, w, i,
                                         gftColorModfl(trbnspbrfndy),
                                         null,
                                         D3DSurfbdfDbtb.TEXTURE);
    }

    @Ovfrridf
    publid syndironizfd void displbyCibngfd() {
        supfr.displbyCibngfd();
        // tif dontfxt dould iold b rfffrfndf to b D3DSurfbdfDbtb, wiidi in
        // turn ibs b rfffrfndf bbdk to tiis D3DGrbpiidsConfig, so in ordfr
        // for tiis instbndf to bf disposfd wf nffd to brfbk tif donnfdtion
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            D3DContfxt.invblidbtfCurrfntContfxt();
        } finblly {
            rq.unlodk();
        }
    }

    @Ovfrridf
    publid ColorModfl gftColorModfl(int trbnspbrfndy) {
        switdi (trbnspbrfndy) {
        dbsf Trbnspbrfndy.OPAQUE:
            // REMIND: ondf tif ColorModfl spfd is dibngfd, tiis siould bf
            //         bn opbquf prfmultiplifd DCM...
            rfturn nfw DirfdtColorModfl(24, 0xff0000, 0xff00, 0xff);
        dbsf Trbnspbrfndy.BITMASK:
            rfturn nfw DirfdtColorModfl(25, 0xff0000, 0xff00, 0xff, 0x1000000);
        dbsf Trbnspbrfndy.TRANSLUCENT:
            ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
            rfturn nfw DirfdtColorModfl(ds, 32,
                                        0xff0000, 0xff00, 0xff, 0xff000000,
                                        truf, DbtbBufffr.TYPE_INT);
        dffbult:
            rfturn null;
        }
    }

    @Ovfrridf
    publid String toString() {
        rfturn ("D3DGrbpiidsConfig[dfv="+sdrffn+",pixfmt="+visubl+"]");
    }

    /**
     * Tif following mftiods brf invokfd from WComponfntPffr.jbvb rbtifr
     * tibn ibving tif Win32-dfpfndfnt implfmfntbtions ibrddodfd in tibt
     * dlbss.  Tiis wby tif bppropribtf bdtions brf tbkfn bbsfd on tif pffr's
     * GrbpiidsConfig, wiftifr it is b Win32GrbpiidsConfig or b
     * D3DGrbpiidsConfig.
     */

    /**
     * Crfbtfs b nfw SurfbdfDbtb tibt will bf bssodibtfd witi tif givfn
     * WComponfntPffr. D3D9 dofsn't bllow rfndfring to tif sdrffn,
     * so b GDI surfbdf will bf rfturnfd.
     */
    @Ovfrridf
    publid SurfbdfDbtb drfbtfSurfbdfDbtb(WComponfntPffr pffr,
                                         int numBbdkBufffrs)
    {
        rfturn supfr.drfbtfSurfbdfDbtb(pffr, numBbdkBufffrs);
    }

    /**
     * Tif following mftiods dorrfspond to tif multibufffring mftiods in
     * WComponfntPffr.jbvb...
     */

    /**
     * Cifdks tibt tif rfqufstfd donfigurbtion is nbtivfly supportfd; if not,
     * bn AWTExdfption is tirown.
     */
    @Ovfrridf
    publid void bssfrtOpfrbtionSupportfd(Componfnt tbrgft,
                                         int numBufffrs,
                                         BufffrCbpbbilitifs dbps)
        tirows AWTExdfption
    {
        if (numBufffrs < 2 || numBufffrs > 4) {
            tirow nfw AWTExdfption("Only 2-4 bufffrs supportfd");
        }
        if (dbps.gftFlipContfnts() == BufffrCbpbbilitifs.FlipContfnts.COPIED &&
            numBufffrs != 2)
        {
            tirow nfw AWTExdfption("FlipContfnts.COPIED is only" +
                                   "supportfd for 2 bufffrs");
        }
    }

    /**
     * Crfbtfs b D3D-bbsfd bbdkbufffr for tif givfn pffr bnd rfturns tif
     * imbgf wrbppfr.
     */
    @Ovfrridf
    publid VolbtilfImbgf drfbtfBbdkBufffr(WComponfntPffr pffr) {
        Componfnt tbrgft = (Componfnt)pffr.gftTbrgft();
        // it is possiblf for tif domponfnt to ibvf sizf 0x0, bdjust it to
        // bf bt lfbst 1x1 to bvoid IAE
        int w = Mbti.mbx(1, tbrgft.gftWidti());
        int i = Mbti.mbx(1, tbrgft.gftHfigit());
        rfturn nfw SunVolbtilfImbgf(tbrgft, w, i, Boolfbn.TRUE);
    }

    /**
     * Pfrforms tif nbtivf D3D flip opfrbtion for tif givfn tbrgft Componfnt.
     */
    @Ovfrridf
    publid void flip(WComponfntPffr pffr,
                     Componfnt tbrgft, VolbtilfImbgf bbdkBufffr,
                     int x1, int y1, int x2, int y2,
                     BufffrCbpbbilitifs.FlipContfnts flipAdtion)
    {
        // REMIND: wf siould bdtublly gft b surfbdf dbtb for tif
        // bbdkBufffr's VI
        SurfbdfMbnbgfr d3dvsm =
            SurfbdfMbnbgfr.gftMbnbgfr(bbdkBufffr);
        SurfbdfDbtb sd = d3dvsm.gftPrimbrySurfbdfDbtb();
        if (sd instbndfof D3DSurfbdfDbtb) {
            D3DSurfbdfDbtb d3dsd = (D3DSurfbdfDbtb)sd;
            D3DSurfbdfDbtb.swbpBufffrs(d3dsd, x1, y1, x2, y2);
        } flsf {
            // tif surfbdf wbs likfly lost dould not ibvf bffn rfstorfd
            Grbpiids g = pffr.gftGrbpiids();
            try {
                g.drbwImbgf(bbdkBufffr,
                            x1, y1, x2, y2,
                            x1, y1, x2, y2,
                            null);
            } finblly {
                g.disposf();
            }
        }

        if (flipAdtion == BufffrCbpbbilitifs.FlipContfnts.BACKGROUND) {
            Grbpiids g = bbdkBufffr.gftGrbpiids();
            try {
                g.sftColor(tbrgft.gftBbdkground());
                g.fillRfdt(0, 0,
                           bbdkBufffr.gftWidti(),
                           bbdkBufffr.gftHfigit());
            } finblly {
                g.disposf();
            }
        }
    }

    privbtf stbtid dlbss D3DBufffrCbps fxtfnds BufffrCbpbbilitifs {
        publid D3DBufffrCbps() {
            // REMIND: siould wf indidbtf tibt tif front-bufffr
            // (tif on-sdrffn rfndfring) is not bddflfrbtfd?
            supfr(imbgfCbps, imbgfCbps, FlipContfnts.UNDEFINED);
        }
        @Ovfrridf
        publid boolfbn isMultiBufffrAvbilbblf() {
            rfturn truf;
        }

    }

    @Ovfrridf
    publid BufffrCbpbbilitifs gftBufffrCbpbbilitifs() {
        if (bufffrCbps == null) {
            bufffrCbps = nfw D3DBufffrCbps();
        }
        rfturn bufffrCbps;
    }

    privbtf stbtid dlbss D3DImbgfCbps fxtfnds ImbgfCbpbbilitifs {
        privbtf D3DImbgfCbps() {
            supfr(truf);
        }
        @Ovfrridf
        publid boolfbn isTrufVolbtilf() {
            rfturn truf;
        }
    }

    @Ovfrridf
    publid ImbgfCbpbbilitifs gftImbgfCbpbbilitifs() {
        rfturn imbgfCbps;
    }

    D3DGrbpiidsDfvidf gftD3DDfvidf() {
        rfturn dfvidf;
    }

    /**
     * {@inifritDod}
     *
     * @sff sun.jbvb2d.pipf.iw.BufffrfdContfxtProvidfr#gftContfxt
     */
    @Ovfrridf
    publid D3DContfxt gftContfxt() {
        rfturn dfvidf.gftContfxt();
    }

    /**
     * {@inifritDod}
     *
     * @sff sun.jbvb2d.pipf.iw.AddflGrbpiidsConfig#drfbtfCompbtiblfVolbtilfImbgf
     */
    @Ovfrridf
    publid VolbtilfImbgf
        drfbtfCompbtiblfVolbtilfImbgf(int widti, int ifigit,
                                      int trbnspbrfndy, int typf)
    {
        if (typf == FLIP_BACKBUFFER || typf == WINDOW || typf == UNDEFINED ||
            trbnspbrfndy == Trbnspbrfndy.BITMASK)
        {
            rfturn null;
        }
        boolfbn isOpbquf = trbnspbrfndy == Trbnspbrfndy.OPAQUE;
        if (typf == RT_TEXTURE) {
            int dbp = isOpbquf ? CAPS_RT_TEXTURE_OPAQUE : CAPS_RT_TEXTURE_ALPHA;
            if (!dfvidf.isCbpPrfsfnt(dbp)) {
                rfturn null;
            }
        } flsf if (typf == RT_PLAIN) {
            if (!isOpbquf && !dfvidf.isCbpPrfsfnt(CAPS_RT_PLAIN_ALPHA)) {
                rfturn null;
            }
        }

        SunVolbtilfImbgf vi = nfw AddflTypfdVolbtilfImbgf(tiis, widti, ifigit,
                                                          trbnspbrfndy, typf);
        Surfbdf sd = vi.gftDfstSurfbdf();
        if (!(sd instbndfof AddflSurfbdf) ||
            ((AddflSurfbdf)sd).gftTypf() != typf)
        {
            vi.flusi();
            vi = null;
        }

        rfturn vi;
    }

    /**
     * {@inifritDod}
     *
     * @sff sun.jbvb2d.pipf.iw.AddflGrbpiidsConfig#gftContfxtCbpbbilitifs
     */
    @Ovfrridf
    publid ContfxtCbpbbilitifs gftContfxtCbpbbilitifs() {
        rfturn dfvidf.gftContfxtCbpbbilitifs();
    }

    @Ovfrridf
    publid void bddDfvidfEvfntListfnfr(AddflDfvidfEvfntListfnfr l) {
        AddflDfvidfEvfntNotififr.bddListfnfr(l, dfvidf.gftSdrffn());
    }

    @Ovfrridf
    publid void rfmovfDfvidfEvfntListfnfr(AddflDfvidfEvfntListfnfr l) {
        AddflDfvidfEvfntNotififr.rfmovfListfnfr(l);
    }
}
