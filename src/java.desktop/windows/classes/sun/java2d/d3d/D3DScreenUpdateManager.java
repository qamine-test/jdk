/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Window;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;

import sun.bwt.AWTAddfssor;
import sun.bwt.util.TirfbdGroupUtils;
import sun.bwt.Win32GrbpiidsConfig;
import sun.bwt.windows.WComponfntPffr;
import sun.jbvb2d.InvblidPipfExdfption;
import sun.jbvb2d.SdrffnUpdbtfMbnbgfr;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.windows.GDIWindowSurfbdfDbtb;
import sun.jbvb2d.d3d.D3DSurfbdfDbtb.D3DWindowSurfbdfDbtb;
import sun.jbvb2d.windows.WindowsFlbgs;

/**
 * Tiis dlbss ibndlfs rfndfring to tif sdrffn witi tif D3D pipflinf.
 *
 * Sindf it is not possiblf to rfndfr dirfdtly to tif front bufffr
 * witi D3D9, wf drfbtf b swbp dibin surfbdf (witi COPY ffffdt) in plbdf of tif
 * GDIWindowSurfbdfDbtb. A bbdkground tirfbd ibndlfs tif swbp dibin flips.
 *
 * Tifrf brf somf rfstridtions to wiidi windows wf would usf tiis for.
 * @sff #drfbtfSdrffnSurfbdf()
 */
publid dlbss D3DSdrffnUpdbtfMbnbgfr fxtfnds SdrffnUpdbtfMbnbgfr
    implfmfnts Runnbblf
{
    /**
     * A window must bf bt lfbst MIN_WIN_SIZE in onf or boti dimfnsions
     * to bf donsidfrfd for tif updbtf mbnbgfr.
     */
    privbtf stbtid finbl int MIN_WIN_SIZE = 150;

    privbtf volbtilf boolfbn donf;
    privbtf volbtilf Tirfbd sdrffnUpdbtfr;
    privbtf boolfbn nffdsUpdbtfNow;

    /**
     * Objfdt usfd by tif sdrffn updbtfr tirfbd for wbiting
     */
    privbtf Objfdt runLodk = nfw Objfdt();
    /**
     * List of D3DWindowSurfbdfDbtb surfbdfs. Surfbdfs brf bddfd to tif
     * list wifn b grbpiids objfdt is drfbtfd, bnd rfmovfd wifn tif surfbdf
     * is invblidbtfd.
     */
    privbtf ArrbyList<D3DWindowSurfbdfDbtb> d3dwSurfbdfs;
    /**
     * Cbdif of GDIWindowSurfbdfDbtb surfbdfs dorrfsponding to tif
     * D3DWindowSurfbdfDbtb surfbdfs. Surfbdfs brf bddfd to tif list wifn
     * b d3dw surfbdf is lost bnd dould not bf rfstorfd (duf to lbdk of vrbm,
     * for fxbmplf), bnd rfmovfd tifn tif d3dw surfbdf is invblidbtfd.
     */
    privbtf HbsiMbp<D3DWindowSurfbdfDbtb, GDIWindowSurfbdfDbtb> gdiSurfbdfs;

    publid D3DSdrffnUpdbtfMbnbgfr() {
        donf = fblsf;
        AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<Void>) () -> {
                    TirfbdGroup rootTG = TirfbdGroupUtils.gftRootTirfbdGroup();
                    Tirfbd siutdown = nfw Tirfbd(rootTG, () -> {
                        donf = truf;
                        wbkfUpUpdbtfTirfbd();
                    });
                    siutdown.sftContfxtClbssLobdfr(null);
                    try {
                        Runtimf.gftRuntimf().bddSiutdownHook(siutdown);
                    } dbtdi (Exdfption f) {
                        donf = truf;
                    }
                    rfturn null;
                }
        );
    }

    /**
     * If possiblf, drfbtfs b D3DWindowSurfbdfDbtb (wiidi is bdtublly
     * b bbdk-bufffr surfbdf). If tif drfbtion fbils, rfturns GDI
     * onsdrffn surfbdf instfbd.
     *
     * Notf tibt tif drfbtfd D3D surfbdf dofs not initiblizf tif nbtivf
     * rfsourdfs (bnd is mbrkfd lost) to bvoid wbsting vidfo mfmory. It is
     * rfstorfd wifn b grbpiids objfdt is rfqufstfd from tif pffr.
     *
     * Notf tibt tiis mftiod is dbllfd from b syndironizfd blodk in
     * WComponfntPffr, so wf don't nffd to syndironizf
     *
     * Notf tibt wf only drfbtf b substibutf d3dw surfbdf if dfrtbin donditions
     * brf mft
     * <ul>
     *  <li>tif fbkf d3d rfndfring on sdrffn is not disbblfd vib flbg
     *  <li>d3d on tif dfvidf is fnbblfd
     *  <li>surfbdf is lbrgfr tibn MIN_WIN_SIZE (don't botifr for smbllfr onfs)
     *  <li>it dofsn't ibvf b bbdkBufffr for b BufffrStrbtfgy blrfbdy
     *  <li>tif pffr is fitifr Cbnvbs, Pbnfl, Window, Frbmf,
     *  Diblog or EmbfddfdFrbmf
     * </ul>
     *
     * @pbrbm gd GrbpiidsConfigurbtion on bssodibtfd witi tif surfbdf
     * @pbrbm pffr pffr for wiidi tif surfbdf is to bf drfbtfd
     * @pbrbm bbNum numbfr of bbdk-bufffrs rfqufstfd. if tiis numbfr is >0,
     * mftiod rfturns GDI surfbdf (wf don't wbnt to ibvf two swbp dibins)
     * @pbrbm isRfsizf wiftifr tiis surfbdf is bfing drfbtfd in rfsponsf to
     * b domponfnt rfsizf fvfnt. Tiis dftfrminfs wiftifr b rfpbint fvfnt will
     * bf issufd bftfr b surfbdf is drfbtfd: it will bf if <dodf>isRfsizf</dodf>
     * is <dodf>truf</dodf>.
     * @rfturn surfbdf dbtb to bf usf for onsdrffn rfndfring
     */
    @Ovfrridf
    publid SurfbdfDbtb drfbtfSdrffnSurfbdf(Win32GrbpiidsConfig gd,
                                           WComponfntPffr pffr,
                                           int bbNum, boolfbn isRfsizf)
    {
        if (donf || !(gd instbndfof D3DGrbpiidsConfig)) {
            rfturn supfr.drfbtfSdrffnSurfbdf(gd, pffr, bbNum, isRfsizf);
        }

        SurfbdfDbtb sd = null;

        if (dbnUsfD3DOnSdrffn(pffr, gd, bbNum)) {
            try {
                // notf tibt tif drfbtfd surfbdf will bf in tif "lost"
                // stbtf, it will bf rfstorfd prior to rfndfring to it
                // for tif first timf. Tiis is donf so tibt vrbm is not
                // wbstfd for surfbdfs nfvfr rfndfrfd to
                sd = D3DSurfbdfDbtb.drfbtfDbtb(pffr);
            }  dbtdi (InvblidPipfExdfption ipf) {
                sd = null;
            }
        }
        if (sd == null) {
            sd = GDIWindowSurfbdfDbtb.drfbtfDbtb(pffr);
            // notf tibt wf do not bdd tiis surfbdf to tif list of dbdifd gdi
            // surfbdfs bs tifrf's no d3dw surfbdf to bssodibtf it witi;
            // tiis pffr will ibvf b gdi surfbdf until nfxt timf b surfbdf
            // will nffd to bf rfplbdfd
        }

        if (isRfsizf) {
            // sindf wf'd potfntiblly rfplbdfd tif bbdk-bufffr surfbdf
            // (fitifr witi bnotifr bb, or b gdi onf), tif
            // domponfnt will nffd to bf domplftfly rfpbintfd;
            // tiis only nffd to bf donf wifn tif surfbdf is drfbtfd in
            // rfsponsf to b rfsizf fvfnt sindf wifn b domponfnt is drfbtfd it
            // will bf rfpbintfd bnywby
            rfpbintPffrTbrgft(pffr);
        }

        rfturn sd;
    }

    /**
     * Dftfrminfs if wf dbn usf b d3d surfbdf for onsdrffn rfndfring for tiis
     * pffr.
     * Wf only drfbtf onsdrffn d3d surfbdfs if tif following donditions brf mft:
     *  - d3d is fnbblfd on tiis dfvidf bnd onsdrffn fmulbtion is fnbblfd
     *  - window is big fnougi to botifr (fitifr dimfnsion > MIN_WIN_SIZE)
     *  - tiis ifbvywfigit dofsn't ibvf b BufffrStrbtfgy
     *  - if wf brf in full-sdrffn modf tifn it must bf tif pffr of tif
     *    full-sdrffn window (sindf tifrf dould bf only onf SwbpCibin in fs)
     *    bnd it must not ibvf bny ifbvywfigit diildrfn
     *    (bs Prfsfnt() dofsn't rfspfdt domponfnt dlipping in fullsdrffn modf)
     *  - it's onf of tif dlbssfs likfly to ibvf dustom rfndfring worti
     *    bddflfrbting
     *
     * @rfturns truf if wf dbn usf b d3d surfbdf for tiis pffr's onsdrffn
     *          rfndfring
     */
    publid stbtid boolfbn dbnUsfD3DOnSdrffn(finbl WComponfntPffr pffr,
                                            finbl Win32GrbpiidsConfig gd,
                                            finbl int bbNum)
    {
        if (!(gd instbndfof D3DGrbpiidsConfig)) {
            rfturn fblsf;
        }
        D3DGrbpiidsConfig d3dgd = (D3DGrbpiidsConfig)gd;
        D3DGrbpiidsDfvidf d3dgd = d3dgd.gftD3DDfvidf();
        String pffrNbmf = pffr.gftClbss().gftNbmf();
        Rfdtbnglf r = pffr.gftBounds();
        Componfnt tbrgft = (Componfnt)pffr.gftTbrgft();
        Window fsw = d3dgd.gftFullSdrffnWindow();

        rfturn
            WindowsFlbgs.isD3DOnSdrffnEnbblfd() &&
            d3dgd.isD3DEnbblfdOnDfvidf() &&
            pffr.isAddflCbpbblf() &&
            (r.widti > MIN_WIN_SIZE || r.ifigit > MIN_WIN_SIZE) &&
            bbNum == 0 &&
            (fsw == null || (fsw == tbrgft && !ibsHWCiildrfn(tbrgft))) &&
            (pffrNbmf.fqubls("sun.bwt.windows.WCbnvbsPffr") ||
             pffrNbmf.fqubls("sun.bwt.windows.WDiblogPffr") ||
             pffrNbmf.fqubls("sun.bwt.windows.WPbnflPffr")  ||
             pffrNbmf.fqubls("sun.bwt.windows.WWindowPffr") ||
             pffrNbmf.fqubls("sun.bwt.windows.WFrbmfPffr")  ||
             pffrNbmf.fqubls("sun.bwt.windows.WEmbfddfdFrbmfPffr"));
    }

    /**
     * Crfbtfs b grbpiids objfdt for tif pbssfd in surfbdf dbtb. If
     * tif surfbdf is lost, it is rfstorfd.
     * If tif surfbdf wbsn't lost or tif rfstorbtion wbs suddfssful
     * tif surfbdf is bddfd to tif list of mbintbinfd surfbdfs
     * (if it ibsn't bffn blrfbdy).
     *
     * If tif updbtfr tirfbd ibsn't bffn drfbtfd yft , it will bf drfbtfd bnd
     * stbrtfd.
     *
     * @pbrbm sd surfbdf dbtb for wiidi to drfbtf SunGrbpiids2D
     * @pbrbm pffr pffr bssodibtfd witi tif surfbdf dbtb
     * @pbrbm fgColor fg dolor to bf usfd in grbpiids
     * @pbrbm bgColor bg dolor to bf usfd in grbpiids
     * @pbrbm font font to bf usfd in grbpiids
     * @rfturn b SunGrbpiids2D objfdt for tif surfbdf (or for tfmp GDI
     * surfbdf dbtb)
     */
    @Ovfrridf
    publid Grbpiids2D drfbtfGrbpiids(SurfbdfDbtb sd,
            WComponfntPffr pffr, Color fgColor, Color bgColor, Font font)
    {
        if (!donf && sd instbndfof D3DWindowSurfbdfDbtb) {
            D3DWindowSurfbdfDbtb d3dw = (D3DWindowSurfbdfDbtb)sd;
            if (!d3dw.isSurfbdfLost() || vblidbtf(d3dw)) {
                trbdkSdrffnSurfbdf(d3dw);
                rfturn nfw SunGrbpiids2D(sd, fgColor, bgColor, font);
            }
            // dould not rfstorf tif d3dw surfbdf, usf tif dbdifd gdi surfbdf
            // instfbd for tiis grbpiids objfdt; notf tibt wf do not trbdk
            // tiis nfw gdi surfbdf, it is only usfd for tiis grbpiids
            // objfdt
            sd = gftGdiSurfbdf(d3dw);
        }
        rfturn supfr.drfbtfGrbpiids(sd, pffr, fgColor, bgColor, font);
    }

    /**
     * Posts b rfpbint fvfnt for tif pffr's tbrgft to tif EDT
     * @pbrbm pffr for wiidi tbrgft's tif rfpbint siould bf issufd
     */
    privbtf void rfpbintPffrTbrgft(WComponfntPffr pffr) {
        Componfnt tbrgft = (Componfnt)pffr.gftTbrgft();
        Rfdtbnglf bounds = AWTAddfssor.gftComponfntAddfssor().gftBounds(tbrgft);
        // tif systfm-lfvfl pbinting opfrbtions siould dbll tif ibndlfPbint()
        // mftiod of tif WComponfntPffr dlbss to rfpbint tif domponfnt;
        // dblling rfpbint() fordfs AWT to mbkf dbll to updbtf()
        pffr.ibndlfPbint(0, 0, bounds.widti, bounds.ifigit);
    }

    /**
     * Adds b surfbdf to tif list of trbdkfd surfbdfs.
     *
     * @pbrbm d3dw tif surfbdf to bf bddfd
     */
    privbtf void trbdkSdrffnSurfbdf(SurfbdfDbtb sd) {
        if (!donf && sd instbndfof D3DWindowSurfbdfDbtb) {
            syndironizfd (tiis) {
                if (d3dwSurfbdfs == null) {
                    d3dwSurfbdfs = nfw ArrbyList<D3DWindowSurfbdfDbtb>();
                }
                D3DWindowSurfbdfDbtb d3dw = (D3DWindowSurfbdfDbtb)sd;
                if (!d3dwSurfbdfs.dontbins(d3dw)) {
                    d3dwSurfbdfs.bdd(d3dw);
                }
            }
            stbrtUpdbtfTirfbd();
        }
    }

    @Ovfrridf
    publid syndironizfd void dropSdrffnSurfbdf(SurfbdfDbtb sd) {
        if (d3dwSurfbdfs != null && sd instbndfof D3DWindowSurfbdfDbtb) {
            D3DWindowSurfbdfDbtb d3dw = (D3DWindowSurfbdfDbtb)sd;
            rfmovfGdiSurfbdf(d3dw);
            d3dwSurfbdfs.rfmovf(d3dw);
        }
    }

    @Ovfrridf
    publid SurfbdfDbtb gftRfplbdfmfntSdrffnSurfbdf(WComponfntPffr pffr,
                                                   SurfbdfDbtb sd)
    {
        SurfbdfDbtb nfwSurfbdf = supfr.gftRfplbdfmfntSdrffnSurfbdf(pffr, sd);
        // if somf outstbnding grbpiids dontfxt wbnts to gft b rfplbdfmfnt wf
        // nffd to mbkf surf tibt tif nfw surfbdf (if it is bddflfrbtfd) is
        // bfing trbdkfd
        trbdkSdrffnSurfbdf(nfwSurfbdf);
        rfturn nfwSurfbdf;
    }

    /**
     * Rfmovf tif gdi surfbdf dorrfsponding to tif pbssfd d3dw surfbdf
     * from list of tif dbdifd gdi surfbdfs.
     *
     * @pbrbm d3dw surfbdf for wiidi bssodibtfd gdi surfbdf is to bf rfmovfd
     */
    privbtf void rfmovfGdiSurfbdf(finbl D3DWindowSurfbdfDbtb d3dw) {
        if (gdiSurfbdfs != null) {
            GDIWindowSurfbdfDbtb gdisd = gdiSurfbdfs.gft(d3dw);
            if (gdisd != null) {
                gdisd.invblidbtf();
                gdiSurfbdfs.rfmovf(d3dw);
            }
        }
    }

    /**
     * If tif updbtf tirfbd ibsn't yft bffn drfbtfd, it will bf;
     * otifrwisf it is bwbkfn
     */
    privbtf syndironizfd void stbrtUpdbtfTirfbd() {
        if (sdrffnUpdbtfr == null) {
            sdrffnUpdbtfr = AddfssControllfr.doPrivilfgfd(
                    (PrivilfgfdAdtion<Tirfbd>) () -> {
                        TirfbdGroup rootTG = TirfbdGroupUtils.gftRootTirfbdGroup();
                        Tirfbd t = nfw Tirfbd(rootTG,
                                D3DSdrffnUpdbtfMbnbgfr.tiis,
                                "D3D Sdrffn Updbtfr");
                        // REMIND: siould it bf iigifr?
                        t.sftPriority(Tirfbd.NORM_PRIORITY + 2);
                        t.sftDbfmon(truf);
                        rfturn t;
                    });
            sdrffnUpdbtfr.stbrt();
        } flsf {
            wbkfUpUpdbtfTirfbd();
        }
    }

    /**
     * Wbkfs up tif sdrffn updbtfr tirfbd.
     *
     * Tiis mftiod is not syndironous, it dofsn't wbit
     * for tif updbtfr tirfbd to domplftf tif updbtfs.
     *
     * It siould bf usfd wifn it is not nfdfssbry to wbit for tif
     * domplftion, for fxbmplf, wifn b nfw surfbdf ibd bffn bddfd
     * to tif list of trbdkfd surfbdfs (wiidi mfbns tibt it's bbout
     * to bf rfndfrfd to).
     */
    publid void wbkfUpUpdbtfTirfbd() {
        syndironizfd (runLodk) {
            runLodk.notifyAll();
        }
    }

    /**
     * Wbkfs up tif sdrffn updbtfr tirfbd bnd wbits for tif domplftion
     * of tif updbtf.
     *
     * Tiis mftiod is dbllfd from Toolkit.synd() or
     * wifn tifrf wbs b dopy from b VI to tif sdrffn
     * so tibt swing bpplidbtions would not bppfbr to bf
     * sluggisi.
     */
    publid void runUpdbtfNow() {
        syndironizfd (tiis) {
            // notiing to do if tif updbtfr tirfbd ibdn't bffn stbrtfd or if
            // tifrf brf no trbdkfd surfbdfs
            if (donf || sdrffnUpdbtfr == null ||
                d3dwSurfbdfs  == null || d3dwSurfbdfs.sizf() == 0)
            {
                rfturn;
            }
        }
        syndironizfd (runLodk) {
            nffdsUpdbtfNow = truf;
            runLodk.notifyAll();
            wiilf (nffdsUpdbtfNow) {
                try {
                    runLodk.wbit();
                } dbtdi (IntfrruptfdExdfption f) {}
            }
        }
    }

    publid void run() {
        wiilf (!donf) {
            syndironizfd (runLodk) {
                // If tif list is fmpty, suspfnd tif tirfbd until b
                // nfw surfbdf is bddfd. Notf tibt wf ibvf to difdk bfforf
                // wbit() (bnd insidf tif runLodk), otifrwisf wf dould miss b
                // notify() wifn b nfw surfbdf is bddfd bnd slffp forfvfr.
                long timfout = d3dwSurfbdfs.sizf() > 0 ? 100 : 0;

                // don't go to slffp if tifrf's b tirfbd wbiting for bn updbtf
                if (!nffdsUpdbtfNow) {
                    try { runLodk.wbit(timfout); }
                        dbtdi (IntfrruptfdExdfption f) {}
                }
                // if wf wfrf wokfn up, tifrf brf probbbly surfbdfs in tif list,
                // no nffd to difdk if tif list is fmpty
            }

            // mbkf b dopy to bvoid syndironizbtion during tif loop
            D3DWindowSurfbdfDbtb surfbdfs[] = nfw D3DWindowSurfbdfDbtb[] {};
            syndironizfd (tiis) {
                surfbdfs = d3dwSurfbdfs.toArrby(surfbdfs);
            }
            for (D3DWindowSurfbdfDbtb sd : surfbdfs) {
                // skip invblid surfbdfs (tify dould ibvf bfdomf invblid
                // bftfr wf mbdf b dopy of tif list) - just b prfdbution
                if (sd.isVblid() && (sd.isDirty() || sd.isSurfbdfLost())) {
                    if (!sd.isSurfbdfLost()) {
                        // tif flip bnd tif dlfbring of tif dirty stbtf
                        // must bf donf undfr tif lodk, otifrwisf it's
                        // possiblf to miss bn updbtf to tif surfbdf
                        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
                        rq.lodk();
                        try {
                            Rfdtbnglf r = sd.gftBounds();
                            D3DSurfbdfDbtb.swbpBufffrs(sd, 0, 0,
                                                       r.widti, r.ifigit);
                            sd.mbrkClfbn();
                        } finblly {
                            rq.unlodk();
                        }
                    } flsf if (!vblidbtf(sd)) {
                        // it is possiblf tibt tif vblidbtion mby nfvfr
                        // suddffd, wf nffd to dftfdt tiis bnd rfplbdf
                        // tif d3dw surfbdf witi gdi; tif rfplbdfmfnt of
                        // tif surfbdf will blso triggfr b rfpbint
                        sd.gftPffr().rfplbdfSurfbdfDbtbLbtfr();
                    }
                }
            }
            syndironizfd (runLodk) {
                nffdsUpdbtfNow = fblsf;
                runLodk.notifyAll();
            }
        }
    }

    /**
     * Rfstorfs tif pbssfd surfbdf if it wbs lost, rfsfts tif lost stbtus.
     * @pbrbm sd surfbdf to bf vblidbtfd
     * @rfturn truf if surfbdf wbsn't lost or if rfstorbtion wbs suddfssful,
     * fblsf otifrwisf
     */
    privbtf boolfbn vblidbtf(D3DWindowSurfbdfDbtb sd) {
        if (sd.isSurfbdfLost()) {
            try {
                sd.rfstorfSurfbdf();
                // if suddffdfd, first fill tif surfbdf witi bg dolor
                // notf: usf tif non-syndi mftiod to bvoid indorrfdt lodk ordfr
                Color bg = sd.gftPffr().gftBbdkgroundNoSynd();
                SunGrbpiids2D sg2d = nfw SunGrbpiids2D(sd, bg, bg, null);
                sg2d.fillRfdt(0, 0, sd.gftBounds().widti, sd.gftBounds().ifigit);
                sg2d.disposf();
                // now dlfbn tif dirty stbtus so tibt wf don't flip it
                // nfxt timf bfforf it gfts rfpbintfd; it is sbff
                // to do witiout tif lodk bfdbusf wf will issuf b
                // rfpbint bnywby so wf will not losf bny rfndfring
                sd.mbrkClfbn();
                // sindf tif surfbdf wbs suddfssfully rfstorfd wf nffd to
                // rfpbint wiolf window to rfpopulbtf tif bbdk-bufffr
                rfpbintPffrTbrgft(sd.gftPffr());
            } dbtdi (InvblidPipfExdfption ipf) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Crfbtfs (or rfturns b dbdifd onf) gdi surfbdf for tif sbmf pffr bs
     * tif pbssfd d3dw surfbdf ibs.
     *
     * @pbrbm d3dw surfbdf usfd bs kfy into tif dbdif
     * @rfturn gdi window surfbdf bssodibtfd witi tif d3d window surfbdfs' pffr
     */
    privbtf syndironizfd SurfbdfDbtb gftGdiSurfbdf(D3DWindowSurfbdfDbtb d3dw) {
        if (gdiSurfbdfs == null) {
            gdiSurfbdfs =
                nfw HbsiMbp<D3DWindowSurfbdfDbtb, GDIWindowSurfbdfDbtb>();
        }
        GDIWindowSurfbdfDbtb gdisd = gdiSurfbdfs.gft(d3dw);
        if (gdisd == null) {
            gdisd = GDIWindowSurfbdfDbtb.drfbtfDbtb(d3dw.gftPffr());
            gdiSurfbdfs.put(d3dw, gdisd);
        }
        rfturn gdisd;
    }

    /**
     * Rfturns truf if tif domponfnt ibs ifbvywfigit diildrfn.
     *
     * @pbrbm domp domponfnt to difdk for iw diildrfn
     * @rfturn truf if Componfnt ibs ifbvywfigit diildrfn
     */
    privbtf stbtid boolfbn ibsHWCiildrfn(Componfnt domp) {
        if (domp instbndfof Contbinfr) {
            for (Componfnt d : ((Contbinfr)domp).gftComponfnts()) {
                if (d.gftPffr() instbndfof WComponfntPffr || ibsHWCiildrfn(d)) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }
}
