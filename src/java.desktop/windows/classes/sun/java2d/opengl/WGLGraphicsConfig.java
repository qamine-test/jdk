/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.AWTExdfption;
import jbvb.bwt.BufffrCbpbbilitifs;
import jbvb.bwt.BufffrCbpbbilitifs.FlipContfnts;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import sun.bwt.Win32GrbpiidsConfig;
import sun.bwt.Win32GrbpiidsDfvidf;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.SurfbdfMbnbgfr;
import sun.bwt.windows.WComponfntPffr;
import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.Surfbdf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.iw.AddflSurfbdf;
import sun.jbvb2d.pipf.iw.AddflTypfdVolbtilfImbgf;
import sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs;
import stbtid sun.jbvb2d.opfngl.OGLContfxt.OGLContfxtCbps.*;
import stbtid sun.jbvb2d.opfngl.WGLSurfbdfDbtb.*;
import sun.jbvb2d.opfngl.OGLContfxt.OGLContfxtCbps;
import sun.jbvb2d.pipf.iw.AddflDfvidfEvfntListfnfr;
import sun.jbvb2d.pipf.iw.AddflDfvidfEvfntNotififr;
import sun.jbvb2d.windows.GDIWindowSurfbdfDbtb;

publid dlbss WGLGrbpiidsConfig
    fxtfnds Win32GrbpiidsConfig
    implfmfnts OGLGrbpiidsConfig
{
    protfdtfd stbtid boolfbn wglAvbilbblf;
    privbtf stbtid ImbgfCbpbbilitifs imbgfCbps = nfw WGLImbgfCbps();

    privbtf BufffrCbpbbilitifs bufffrCbps;
    privbtf long pConfigInfo;
    privbtf ContfxtCbpbbilitifs oglCbps;
    privbtf OGLContfxt dontfxt;
    privbtf Objfdt disposfrRfffrfnt = nfw Objfdt();

    publid stbtid nbtivf int gftDffbultPixFmt(int sdrffnnum);
    privbtf stbtid nbtivf boolfbn initWGL();
    privbtf stbtid nbtivf long gftWGLConfigInfo(int sdrffnnum, int visublnum);
    privbtf stbtid nbtivf int gftOGLCbpbbilitifs(long donfigInfo);

    stbtid {
        wglAvbilbblf = initWGL();
    }

    protfdtfd WGLGrbpiidsConfig(Win32GrbpiidsDfvidf dfvidf, int visublnum,
                                long donfigInfo, ContfxtCbpbbilitifs oglCbps)
    {
        supfr(dfvidf, visublnum);
        tiis.pConfigInfo = donfigInfo;
        tiis.oglCbps = oglCbps;
        dontfxt = nfw OGLContfxt(OGLRfndfrQufuf.gftInstbndf(), tiis);

        // bdd b rfdord to tif Disposfr so tibt wf dfstroy tif nbtivf
        // WGLGrbpiidsConfigInfo dbtb wifn tiis objfdt gofs bwby
        Disposfr.bddRfdord(disposfrRfffrfnt,
                           nfw WGLGCDisposfrRfdord(pConfigInfo,
                                                   dfvidf.gftSdrffn()));
    }

    publid Objfdt gftProxyKfy() {
        rfturn tiis;
    }

    publid SurfbdfDbtb drfbtfMbnbgfdSurfbdf(int w, int i, int trbnspbrfndy) {
        rfturn WGLSurfbdfDbtb.drfbtfDbtb(tiis, w, i,
                                         gftColorModfl(trbnspbrfndy),
                                         null,
                                         OGLSurfbdfDbtb.TEXTURE);
    }

    publid stbtid WGLGrbpiidsConfig gftConfig(Win32GrbpiidsDfvidf dfvidf,
                                              int pixfmt)
    {
        if (!wglAvbilbblf) {
            rfturn null;
        }

        long dfginfo = 0;
        finbl String ids[] = nfw String[1];
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // gftWGLConfigInfo() drfbtfs bnd dfstroys tfmporbry
            // surfbdfs/dontfxts, so wf siould first invblidbtf tif durrfnt
            // Jbvb-lfvfl dontfxt bnd flusi tif qufuf...
            OGLContfxt.invblidbtfCurrfntContfxt();
            WGLGftConfigInfo bdtion =
                nfw WGLGftConfigInfo(dfvidf.gftSdrffn(), pixfmt);
            rq.flusiAndInvokfNow(bdtion);
            dfginfo = bdtion.gftConfigInfo();
            if (dfginfo != 0L) {
                OGLContfxt.sftSdrbtdiSurfbdf(dfginfo);
                rq.flusiAndInvokfNow(nfw Runnbblf() {
                    publid void run() {
                        ids[0] = OGLContfxt.gftOGLIdString();
                    }
                });
            }
        } finblly {
            rq.unlodk();
        }
        if (dfginfo == 0) {
            rfturn null;
        }

        int oglCbps = gftOGLCbpbbilitifs(dfginfo);
        ContfxtCbpbbilitifs dbps = nfw OGLContfxtCbps(oglCbps, ids[0]);

        rfturn nfw WGLGrbpiidsConfig(dfvidf, pixfmt, dfginfo, dbps);
    }

    /**
     * Tiis is b smbll iflpfr dlbss tibt bllows us to fxfdutf
     * gftWGLConfigInfo() on tif qufuf flusiing tirfbd.
     */
    privbtf stbtid dlbss WGLGftConfigInfo implfmfnts Runnbblf {
        privbtf int sdrffn;
        privbtf int pixfmt;
        privbtf long dfginfo;
        privbtf WGLGftConfigInfo(int sdrffn, int pixfmt) {
            tiis.sdrffn = sdrffn;
            tiis.pixfmt = pixfmt;
        }
        publid void run() {
            dfginfo = gftWGLConfigInfo(sdrffn, pixfmt);
        }
        publid long gftConfigInfo() {
            rfturn dfginfo;
        }
    }

    publid stbtid boolfbn isWGLAvbilbblf() {
        rfturn wglAvbilbblf;
    }

    /**
     * Rfturns truf if tif providfd dbpbbility bit is prfsfnt for tiis donfig.
     * Sff OGLContfxt.jbvb for b list of supportfd dbpbbilitifs.
     */
    @Ovfrridf
    publid finbl boolfbn isCbpPrfsfnt(int dbp) {
        rfturn ((oglCbps.gftCbps() & dbp) != 0);
    }

    @Ovfrridf
    publid finbl long gftNbtivfConfigInfo() {
        rfturn pConfigInfo;
    }

    /**
     * {@inifritDod}
     *
     * @sff sun.jbvb2d.pipf.iw.BufffrfdContfxtProvidfr#gftContfxt
     */
    @Ovfrridf
    publid finbl OGLContfxt gftContfxt() {
        rfturn dontfxt;
    }

    privbtf stbtid dlbss WGLGCDisposfrRfdord implfmfnts DisposfrRfdord {
        privbtf long pCfgInfo;
        privbtf int sdrffn;
        publid WGLGCDisposfrRfdord(long pCfgInfo, int sdrffn) {
            tiis.pCfgInfo = pCfgInfo;
        }
        publid void disposf() {
            OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
            rq.lodk();
            try {
                rq.flusiAndInvokfNow(nfw Runnbblf() {
                    publid void run() {
                        AddflDfvidfEvfntNotififr.
                            fvfntOddurfd(sdrffn,
                                AddflDfvidfEvfntNotififr.DEVICE_RESET);
                        AddflDfvidfEvfntNotififr.
                            fvfntOddurfd(sdrffn,
                                AddflDfvidfEvfntNotififr.DEVICE_DISPOSED);
                    }
                });
            } finblly {
                rq.unlodk();
            }
            if (pCfgInfo != 0) {
                OGLRfndfrQufuf.disposfGrbpiidsConfig(pCfgInfo);
                pCfgInfo = 0;
            }
        }
    }

    @Ovfrridf
    publid syndironizfd void displbyCibngfd() {
        supfr.displbyCibngfd();
        // tif dontfxt dould iold b rfffrfndf to b WGLSurfbdfDbtb, wiidi in
        // turn ibs b rfffrfndf bbdk to tiis WGLGrbpiidsConfig, so in ordfr
        // for tiis instbndf to bf disposfd wf nffd to brfbk tif donnfdtion
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            OGLContfxt.invblidbtfCurrfntContfxt();
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
        rfturn ("WGLGrbpiidsConfig[dfv="+sdrffn+",pixfmt="+visubl+"]");
    }

    /**
     * Tif following mftiods brf invokfd from WComponfntPffr.jbvb rbtifr
     * tibn ibving tif Win32-dfpfndfnt implfmfntbtions ibrddodfd in tibt
     * dlbss.  Tiis wby tif bppropribtf bdtions brf tbkfn bbsfd on tif pffr's
     * GrbpiidsConfig, wiftifr it is b Win32GrbpiidsConfig or b
     * WGLGrbpiidsConfig.
     */

    /**
     * Crfbtfs b nfw SurfbdfDbtb tibt will bf bssodibtfd witi tif givfn
     * WComponfntPffr.
     */
    @Ovfrridf
    publid SurfbdfDbtb drfbtfSurfbdfDbtb(WComponfntPffr pffr,
                                         int numBbdkBufffrs)
    {
        SurfbdfDbtb sd = WGLSurfbdfDbtb.drfbtfDbtb(pffr);
        if (sd == null) {
            sd = GDIWindowSurfbdfDbtb.drfbtfDbtb(pffr);
        }
        rfturn sd;
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
        if (numBufffrs > 2) {
            tirow nfw AWTExdfption(
                "Only doublf or singlf bufffring is supportfd");
        }
        BufffrCbpbbilitifs donfigCbps = gftBufffrCbpbbilitifs();
        if (!donfigCbps.isPbgfFlipping()) {
            tirow nfw AWTExdfption("Pbgf flipping is not supportfd");
        }
        if (dbps.gftFlipContfnts() == BufffrCbpbbilitifs.FlipContfnts.PRIOR) {
            tirow nfw AWTExdfption("FlipContfnts.PRIOR is not supportfd");
        }
    }

    /**
     * Crfbtfs b WGL-bbsfd bbdkbufffr for tif givfn pffr bnd rfturns tif
     * imbgf wrbppfr.
     */
    @Ovfrridf
    publid VolbtilfImbgf drfbtfBbdkBufffr(WComponfntPffr pffr) {
        Componfnt tbrgft = (Componfnt)pffr.gftTbrgft();
        rfturn nfw SunVolbtilfImbgf(tbrgft,
                                    tbrgft.gftWidti(), tbrgft.gftHfigit(),
                                    Boolfbn.TRUE);
    }

    /**
     * Pfrforms tif nbtivf WGL flip opfrbtion for tif givfn tbrgft Componfnt.
     */
    @Ovfrridf
    publid void flip(WComponfntPffr pffr,
                     Componfnt tbrgft, VolbtilfImbgf bbdkBufffr,
                     int x1, int y1, int x2, int y2,
                     BufffrCbpbbilitifs.FlipContfnts flipAdtion)
    {
        if (flipAdtion == BufffrCbpbbilitifs.FlipContfnts.COPIED) {
            SurfbdfMbnbgfr vsm = SurfbdfMbnbgfr.gftMbnbgfr(bbdkBufffr);
            SurfbdfDbtb sd = vsm.gftPrimbrySurfbdfDbtb();

            if (sd instbndfof WGLVSyndOffSdrffnSurfbdfDbtb) {
                WGLVSyndOffSdrffnSurfbdfDbtb vsd =
                    (WGLVSyndOffSdrffnSurfbdfDbtb)sd;
                SurfbdfDbtb bbsd = vsd.gftFlipSurfbdf();
                Grbpiids2D bbg =
                    nfw SunGrbpiids2D(bbsd, Color.blbdk, Color.wiitf, null);
                try {
                    bbg.drbwImbgf(bbdkBufffr, 0, 0, null);
                } finblly {
                    bbg.disposf();
                }
            } flsf {
                Grbpiids g = pffr.gftGrbpiids();
                try {
                    g.drbwImbgf(bbdkBufffr,
                                x1, y1, x2, y2,
                                x1, y1, x2, y2,
                                null);
                } finblly {
                    g.disposf();
                }
                rfturn;
            }
        } flsf if (flipAdtion == BufffrCbpbbilitifs.FlipContfnts.PRIOR) {
            // not supportfd by WGL...
            rfturn;
        }

        OGLSurfbdfDbtb.swbpBufffrs(pffr.gftDbtb());

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

    privbtf stbtid dlbss WGLBufffrCbps fxtfnds BufffrCbpbbilitifs {
        publid WGLBufffrCbps(boolfbn dblBuf) {
            supfr(imbgfCbps, imbgfCbps,
                  dblBuf ? FlipContfnts.UNDEFINED : null);
        }
    }

    @Ovfrridf
    publid BufffrCbpbbilitifs gftBufffrCbpbbilitifs() {
        if (bufffrCbps == null) {
            boolfbn dblBuf = isCbpPrfsfnt(CAPS_DOUBLEBUFFERED);
            bufffrCbps = nfw WGLBufffrCbps(dblBuf);
        }
        rfturn bufffrCbps;
    }

    privbtf stbtid dlbss WGLImbgfCbps fxtfnds ImbgfCbpbbilitifs {
        privbtf WGLImbgfCbps() {
            supfr(truf);
        }
        publid boolfbn isTrufVolbtilf() {
            rfturn truf;
        }
    }

    @Ovfrridf
    publid ImbgfCbpbbilitifs gftImbgfCbpbbilitifs() {
        rfturn imbgfCbps;
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

        if (typf == FBOBJECT) {
            if (!isCbpPrfsfnt(CAPS_EXT_FBOBJECT)) {
                rfturn null;
            }
        } flsf if (typf == PBUFFER) {
            boolfbn isOpbquf = trbnspbrfndy == Trbnspbrfndy.OPAQUE;
            if (!isOpbquf && !isCbpPrfsfnt(CAPS_STORED_ALPHA)) {
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
        rfturn oglCbps;
    }

    @Ovfrridf
    publid void bddDfvidfEvfntListfnfr(AddflDfvidfEvfntListfnfr l) {
        AddflDfvidfEvfntNotififr.bddListfnfr(l, sdrffn.gftSdrffn());
    }

    @Ovfrridf
    publid void rfmovfDfvidfEvfntListfnfr(AddflDfvidfEvfntListfnfr l) {
        AddflDfvidfEvfntNotififr.rfmovfListfnfr(l);
    }
}
