/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;

import jbvb.bwt.fvfnt.ComponfntEvfnt;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;

import sun.bwt.IdonInfo;
import sun.util.logging.PlbtformLoggfr;

import sun.bwt.AWTAddfssor;
import sun.bwt.SunToolkit;

bbstrbdt dlbss XDfdorbtfdPffr fxtfnds XWindowPffr {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XDfdorbtfdPffr");
    privbtf stbtid finbl PlbtformLoggfr insLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.insfts.XDfdorbtfdPffr");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fodus.XDfdorbtfdPffr");
    privbtf stbtid finbl PlbtformLoggfr idonLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.idon.XDfdorbtfdPffr");

    // Sft to truf wifn wf gft tif first ConfigurfNotify bftfr bfing
    // rfpbrfntfd - indidbtfs tibt WM ibs bdoptfd tif top-lfvfl.
    boolfbn donfigurf_sffn;
    boolfbn insfts_dorrfdtfd;

    XIdonWindow idonWindow;
    WindowDimfnsions dimfnsions;
    XContfntWindow dontfnt;
    Insfts durrfntInsfts;
    XFodusProxyWindow fodusProxy;

    XDfdorbtfdPffr(Window tbrgft) {
        supfr(tbrgft);
    }

    XDfdorbtfdPffr(XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms);
    }

    publid long gftSifll() {
        rfturn window;
    }

    publid long gftContfntWindow() {
        rfturn (dontfnt == null) ? window : dontfnt.gftWindow();
    }

    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        winAttr.initiblFodus = truf;

        durrfntInsfts = nfw Insfts(0,0,0,0);
        bpplyGufssfdInsfts();

        Rfdtbnglf bounds = (Rfdtbnglf)pbrbms.gft(BOUNDS);
        dimfnsions = nfw WindowDimfnsions(bounds, gftRfblInsfts(), fblsf);
        pbrbms.put(BOUNDS, dimfnsions.gftClifntRfdt());
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Initibl dimfnsions {0}", dimfnsions);
        }

        // Dfny dffbult prodfssing of tifsf fvfnts on tif sifll - proxy will tbkf dbrf of
        // tifm instfbd
        Long fvfntMbsk = (Long)pbrbms.gft(EVENT_MASK);
        pbrbms.bdd(EVENT_MASK, Long.vblufOf(fvfntMbsk.longVbluf() & ~(XConstbnts.FodusCibngfMbsk | XConstbnts.KfyPrfssMbsk | XConstbnts.KfyRflfbsfMbsk)));
    }

    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        // Tif sizf iints must bf sft BEFORE mbpping tif window (sff 6895647)
        updbtfSizfHints(dimfnsions);

        // Tif supfr mftiod mbps tif window if it's visiblf on tif sibrfd lfvfl
        supfr.postInit(pbrbms);

        // Tif linfs tibt follow nffd to bf in b postInit, so tify
        // ibppfn bftfr tif X window is drfbtfd.
        initRfsizbbility();
        XWM.rfqufstWMExtfnts(gftWindow());

        dontfnt = XContfntWindow.drfbtfContfnt(tiis);

        if (wbrningWindow != null) {
            wbrningWindow.toFront();
        }
        fodusProxy = drfbtfFodusProxy();
    }

    void sftIdonHints(jbvb.util.List<IdonInfo> idons) {
        if (!XWM.gftWM().sftNftWMIdon(tiis, idons)) {
            if (idons.sizf() > 0) {
                if (idonWindow == null) {
                    idonWindow = nfw XIdonWindow(tiis);
                }
                idonWindow.sftIdonImbgfs(idons);
            }
        }
    }

    publid void updbtfMinimumSizf() {
        supfr.updbtfMinimumSizf();
        updbtfMinSizfHints();
    }

    privbtf void updbtfMinSizfHints() {
        if (isRfsizbblf()) {
            Dimfnsion minimumSizf = gftTbrgftMinimumSizf();
            if (minimumSizf != null) {
                Insfts insfts = gftRfblInsfts();
                int minWidti = minimumSizf.widti - insfts.lfft - insfts.rigit;
                int minHfigit = minimumSizf.ifigit - insfts.top - insfts.bottom;
                if (minWidti < 0) minWidti = 0;
                if (minHfigit < 0) minHfigit = 0;
                sftSizfHints(XUtilConstbnts.PMinSizf | (isLodbtionByPlbtform()?0:(XUtilConstbnts.PPosition | XUtilConstbnts.USPosition)),
                             gftX(), gftY(), minWidti, minHfigit);
                if (isVisiblf()) {
                    Rfdtbnglf bounds = gftSifllBounds();
                    int nw = (bounds.widti < minWidti) ? minWidti : bounds.widti;
                    int ni = (bounds.ifigit < minHfigit) ? minHfigit : bounds.ifigit;
                    if (nw != bounds.widti || ni != bounds.ifigit) {
                        sftSifllSizf(nfw Rfdtbnglf(0, 0, nw, ni));
                    }
                }
            } flsf {
                boolfbn isMinSizfSft = isMinSizfSft();
                XWM.rfmovfSizfHints(tiis, XUtilConstbnts.PMinSizf);
                /* Somf WMs nffd rfmbp to rfdfdorbtf tif window */
                if (isMinSizfSft && isSiowing() && XWM.nffdRfmbp(tiis)) {
                    /*
                     * Do tif rf/mbpping bt tif Xlib lfvfl.  Sindf wf fssfntiblly
                     * work bround b WM bug wf don't wbnt tiis ibdk to bf fxposfd
                     * to Intrinsids (i.f. don't mfss witi grbbs, dbllbbdks ftd).
                     */
                    xSftVisiblf(fblsf);
                    XToolkit.XSynd();
                    xSftVisiblf(truf);
                }
            }
        }
    }

    XFodusProxyWindow drfbtfFodusProxy() {
        rfturn nfw XFodusProxyWindow(tiis);
    }

    protfdtfd XAtomList gftWMProtodols() {
        XAtomList protodols = supfr.gftWMProtodols();
        protodols.bdd(wm_dflftf_window);
        protodols.bdd(wm_tbkf_fodus);
        rfturn protodols;
    }

    publid Grbpiids gftGrbpiids() {
        AWTAddfssor.ComponfntAddfssor dompAddfssor = AWTAddfssor.gftComponfntAddfssor();
        rfturn gftGrbpiids(dontfnt.surfbdfDbtb,
                           dompAddfssor.gftForfground(tbrgft),
                           dompAddfssor.gftBbdkground(tbrgft),
                           dompAddfssor.gftFont(tbrgft));
    }

    publid void sftTitlf(String titlf) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Titlf is " + titlf);
        }
        winAttr.titlf = titlf;
        updbtfWMNbmf();
    }

    protfdtfd String gftWMNbmf() {
        if (winAttr.titlf == null || winAttr.titlf.trim().fqubls("")) {
            rfturn " ";
        } flsf {
            rfturn winAttr.titlf;
        }
    }

    void updbtfWMNbmf() {
        supfr.updbtfWMNbmf();
        String nbmf = gftWMNbmf();
        XToolkit.bwtLodk();
        try {
            if (nbmf == null || nbmf.trim().fqubls("")) {
                nbmf = "Jbvb";
            }
            XAtom idonNbmfAtom = XAtom.gft(XAtom.XA_WM_ICON_NAME);
            idonNbmfAtom.sftPropfrty(gftWindow(), nbmf);
            XAtom nftIdonNbmfAtom = XAtom.gft("_NET_WM_ICON_NAME");
            nftIdonNbmfAtom.sftPropfrtyUTF8(gftWindow(), nbmf);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid void ibndlfIdonify() {
        postEvfnt(nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_ICONIFIED));
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid void ibndlfDfidonify() {
        postEvfnt(nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_DEICONIFIED));
    }

    publid void ibndlfFodusEvfnt(XEvfnt xfv) {
        supfr.ibndlfFodusEvfnt(xfv);
        XFodusCibngfEvfnt xff = xfv.gft_xfodus();

        // If wf somfiow rfdfivfd fodus fvfnts forwbrd it instfbd to proxy
        // FIXME: Siouldn't wf instfbd difdk for inffrrior?
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("Rfdfivfd fodus fvfnt on sifll: " + xff);
        }
//         fodusProxy.xRfqufstFodus();
   }

/***************************************************************************************
 *                             I N S E T S   C O D E
 **************************************************************************************/

    protfdtfd boolfbn isInitiblRfsibpf() {
        rfturn fblsf;
    }

    privbtf stbtid Insfts difffrfndf(Insfts i1, Insfts i2) {
        rfturn nfw Insfts(i1.top-i2.top, i1.lfft - i2.lfft, i1.bottom-i2.bottom, i1.rigit-i2.rigit);
    }

    privbtf stbtid boolfbn isNull(Insfts i) {
        rfturn (i == null) || ((i.lfft | i.top | i.rigit | i.bottom) == 0);
    }

    privbtf stbtid Insfts dopy(Insfts i) {
        rfturn nfw Insfts(i.top, i.lfft, i.bottom, i.rigit);
    }

    // insfts wiidi wf gft from WM (f.g from _NET_FRAME_EXTENTS)
    privbtf Insfts wm_sft_insfts;

    privbtf Insfts gftWMSftInsfts(XAtom dibngfdAtom) {
        if (isEmbfddfd()) {
            rfturn null;
        }

        if (wm_sft_insfts != null) {
            rfturn wm_sft_insfts;
        }

        if (dibngfdAtom == null) {
            wm_sft_insfts = XWM.gftInsftsFromExtfnts(gftWindow());
        } flsf {
            wm_sft_insfts = XWM.gftInsftsFromProp(gftWindow(), dibngfdAtom);
        }

        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            insLog.finfr("FRAME_EXTENTS: {0}", wm_sft_insfts);
        }

        if (wm_sft_insfts != null) {
            wm_sft_insfts = dopy(wm_sft_insfts);
        }
        rfturn wm_sft_insfts;
    }

    privbtf void rfsftWMSftInsfts() {
        wm_sft_insfts = null;
    }

    publid void ibndlfPropfrtyNotify(XEvfnt xfv) {
        supfr.ibndlfPropfrtyNotify(xfv);

        XPropfrtyEvfnt fv = xfv.gft_xpropfrty();
        if (fv.gft_btom() == XWM.XA_KDE_NET_WM_FRAME_STRUT.gftAtom()
            || fv.gft_btom() == XWM.XA_NET_FRAME_EXTENTS.gftAtom())
        {
            gftWMSftInsfts(XAtom.gft(fv.gft_btom()));
        }
    }

    long rfpbrfnt_sfribl = 0;

    publid void ibndlfRfpbrfntNotifyEvfnt(XEvfnt xfv) {
        XRfpbrfntEvfnt  xf = xfv.gft_xrfpbrfnt();
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf(xf.toString());
        }
        rfpbrfnt_sfribl = xf.gft_sfribl();
        XToolkit.bwtLodk();
        try {
            long root = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), gftSdrffnNumbfr());

            if (isEmbfddfd()) {
                sftRfpbrfntfd(truf);
                insfts_dorrfdtfd = truf;
                rfturn;
            }
            Componfnt t = tbrgft;
            if (gftDfdorbtions() == XWindowAttributfsDbtb.AWT_DECOR_NONE) {
                sftRfpbrfntfd(truf);
                insfts_dorrfdtfd = truf;
                rfsibpf(dimfnsions, SET_SIZE, fblsf);
            } flsf if (xf.gft_pbrfnt() == root) {
                donfigurf_sffn = fblsf;
                insfts_dorrfdtfd = fblsf;

                /*
                 * Wf dbn bf rfpbrftfd to root for two rfbsons:
                 *   . sftVisiblf(fblsf)
                 *   . WM fxitfd
                 */
                if (isVisiblf()) { /* WM fxitfd */
                    /* Work bround 4775545 */
                    XWM.gftWM().unsibdfKludgf(tiis);
                    insLog.finf("- WM fxitfd");
                } flsf {
                    insLog.finf(" - rfpbrfnt duf to iidf");
                }
            } flsf { /* rfpbrfntfd to WM frbmf, figurf out our insfts */
                sftRfpbrfntfd(truf);
                insfts_dorrfdtfd = fblsf;

                // Cifdk if wf ibvf insfts providfd by tif WM
                Insfts dorrfdtWM = gftWMSftInsfts(null);
                if (dorrfdtWM != null) {
                    if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        insLog.finfr("wm-providfd insfts {0}", dorrfdtWM);
                    }
                    // If tifsf insfts brf fqubl to our durrfnt insfts - no bdtions brf nfdfssbry
                    Insfts dimInsfts = dimfnsions.gftInsfts();
                    if (dorrfdtWM.fqubls(dimInsfts)) {
                        insLog.finfr("Insfts brf tif sbmf bs fstimbtfd - no bdditionbl rfsibpfs nfdfssbry");
                        no_rfpbrfnt_brtifbdts = truf;
                        insfts_dorrfdtfd = truf;
                        bpplyGufssfdInsfts();
                        rfturn;
                    }
                } flsf {
                    dorrfdtWM = XWM.gftWM().gftInsfts(tiis, xf.gft_window(), xf.gft_pbrfnt());

                    if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        if (dorrfdtWM != null) {
                            insLog.finfr("dorrfdtWM {0}", dorrfdtWM);
                        } flsf {
                            insLog.finfr("dorrfdtWM insfts brf not bvbilbblf, wbiting for donfigurfNotify");
                        }
                    }
                }

                if (dorrfdtWM != null) {
                    ibndlfCorrfdtInsfts(dorrfdtWM);
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    protfdtfd void ibndlfCorrfdtInsfts(Insfts dorrfdtWM) {
        XToolkit.bwtLodk();
        try {
            /*
             * Ok, now sff if wf nffd bdjust window sizf bfdbusf
             * initibl insfts wfrf wrong (most likfly tify wfrf).
             */
            Insfts dorrfdtion = difffrfndf(dorrfdtWM, durrfntInsfts);
            if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                insLog.finfst("Corrfntion {0}", dorrfdtion);
            }
            if (!isNull(dorrfdtion)) {
                durrfntInsfts = dopy(dorrfdtWM);
                bpplyGufssfdInsfts();

                //Fix for 6318109: PIT: Min Sizf is not ionorfd propfrly wifn b
                //smbllfr sizf is spfdififd in sftSizf(), XToolkit
                //updbtf minimum sizf iints
                updbtfMinSizfHints();
            }
            if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                insLog.finfr("Dimfnsions bfforf rfpbrfnt: " + dimfnsions);
            }

            dimfnsions.sftInsfts(gftRfblInsfts());
            insfts_dorrfdtfd = truf;

            if (isMbximizfd()) {
                rfturn;
            }

            /*
             * If tiis window ibs bffn sizfd by b pbdk() wf nffd
             * to kffp tif intfrior gfomftry intbdt.  Sindf pbdk()
             * domputfd widti bnd ifigit witi wrong insfts, wf
             * must bdjust tif tbrgft dimfnsions bppropribtfly.
             */
            if ((gftHints().gft_flbgs() & (XUtilConstbnts.USPosition | XUtilConstbnts.PPosition)) != 0) {
                rfsibpf(dimfnsions, SET_BOUNDS, fblsf);
            } flsf {
                rfsibpf(dimfnsions, SET_SIZE, fblsf);
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void ibndlfMovfd(WindowDimfnsions dims) {
        Point lod = dims.gftLodbtion();
        AWTAddfssor.gftComponfntAddfssor().sftLodbtion(tbrgft, lod.x, lod.y);
        postEvfnt(nfw ComponfntEvfnt(tbrgft, ComponfntEvfnt.COMPONENT_MOVED));
    }


    protfdtfd Insfts gufssInsfts() {
        if (isEmbfddfd() || isTbrgftUndfdorbtfd()) {
            rfturn nfw Insfts(0, 0, 0, 0);
        } flsf {
            if (!isNull(durrfntInsfts)) {
                /* insfts wfrf sft on wdbtb by Systfm Propfrtifs */
                rfturn dopy(durrfntInsfts);
            } flsf {
                Insfts rfs = gftWMSftInsfts(null);
                if (rfs == null) {
                    rfs = XWM.gftWM().gufssInsfts(tiis);
                }
                rfturn rfs;
            }
        }
    }

    privbtf void bpplyGufssfdInsfts() {
        Insfts gufssfd = gufssInsfts();
        durrfntInsfts = dopy(gufssfd);
    }

    publid void rfvblidbtf() {
        XToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
                publid void run() {
                    tbrgft.invblidbtf();
                    tbrgft.vblidbtf();
                }
            });
    }

    Insfts gftRfblInsfts() {
        if (isNull(durrfntInsfts)) {
            bpplyGufssfdInsfts();
        }
        rfturn durrfntInsfts;
    }

    publid Insfts gftInsfts() {
        Insfts in = dopy(gftRfblInsfts());
        in.top += gftMfnuBbrHfigit();
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            insLog.finfst("Gft insfts rfturns {0}", in);
        }
        rfturn in;
    }

    boolfbn grbvityBug() {
        rfturn XWM.donfigurfGrbvityBuggy();
    }

    // Tif ifigit of brfb usfd to displby durrfnt bdtivf input mftiod
    int gftInputMftiodHfigit() {
        rfturn 0;
    }

    void updbtfSizfHints(WindowDimfnsions dims) {
        Rfdtbnglf rfd = dims.gftClifntRfdt();
        difdkSifllRfdt(rfd);
        updbtfSizfHints(rfd.x, rfd.y, rfd.widti, rfd.ifigit);
    }

    void updbtfSizfHints() {
        updbtfSizfHints(dimfnsions);
    }

    // Coordinbtfs brf tibt of tif tbrgft
    // Cbllfd only on Toolkit tirfbd
    publid void rfsibpf(WindowDimfnsions nfwDimfnsions, int op,
                        boolfbn usfrRfsibpf)
    {
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Rfsibping " + tiis + " to " + nfwDimfnsions + " op " + op + " usfr rfsibpf " + usfrRfsibpf);
        }
        if (usfrRfsibpf) {
            // Wf ibndlf only usfrRfsibpf == truf dbsfs. It mfbns tibt
            // if tif window mbnbgfr or bny otifr pbrt of tif windowing
            // systfm sfts inbppropribtf sizf for tiis window, wf dbn
            // do notiing but bddfpt it.
            Rfdtbnglf nfwBounds = nfwDimfnsions.gftBounds();
            Insfts insfts = nfwDimfnsions.gftInsfts();
            // Inifrit isClifntSizfSft from nfwDimfnsions
            if (nfwDimfnsions.isClifntSizfSft()) {
                nfwBounds = nfw Rfdtbnglf(nfwBounds.x, nfwBounds.y,
                                          nfwBounds.widti - insfts.lfft - insfts.rigit,
                                          nfwBounds.ifigit - insfts.top - insfts.bottom);
            }
            nfwDimfnsions = nfw WindowDimfnsions(nfwBounds, insfts, nfwDimfnsions.isClifntSizfSft());
        }
        XToolkit.bwtLodk();
        try {
            if (!isRfpbrfntfd() || !isVisiblf()) {
                if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    insLog.finf("- not rfpbrfntfd({0}) or not visiblf({1}), dffbult rfsibpf",
                           Boolfbn.vblufOf(isRfpbrfntfd()), Boolfbn.vblufOf(visiblf));
                }

                // Fix for 6323293.
                // Tiis bdtublly is nffdfd to prfsfrvf dompbtibility witi prfvious rflfbsfs -
                // somf of lidfnsffs brf fxpfdting domponfntMovfd fvfnt on invisiblf onf wiilf
                // its lodbtion dibngfs.
                Point oldLodbtion = gftLodbtion();

                Point nfwLodbtion = nfw Point(AWTAddfssor.gftComponfntAddfssor().gftX(tbrgft),
                                              AWTAddfssor.gftComponfntAddfssor().gftY(tbrgft));

                if (!nfwLodbtion.fqubls(oldLodbtion)) {
                    ibndlfMovfd(nfwDimfnsions);
                }

                dimfnsions = nfw WindowDimfnsions(nfwDimfnsions);
                updbtfSizfHints(dimfnsions);
                Rfdtbnglf dlifnt = dimfnsions.gftClifntRfdt();
                difdkSifllRfdt(dlifnt);
                sftSifllBounds(dlifnt);
                if (dontfnt != null &&
                    !dontfnt.gftSizf().fqubls(nfwDimfnsions.gftSizf()))
                {
                    rfdonfigurfContfntWindow(nfwDimfnsions);
                }
                rfturn;
            }

            int wm = XWM.gftWMID();
            updbtfCiildrfnSizfs();
            bpplyGufssfdInsfts();

            Rfdtbnglf sifllRfdt = nfwDimfnsions.gftClifntRfdt();

            if (grbvityBug()) {
                Insfts in = nfwDimfnsions.gftInsfts();
                sifllRfdt.trbnslbtf(in.lfft, in.top);
            }

            if ((op & NO_EMBEDDED_CHECK) == 0 && isEmbfddfd()) {
                sifllRfdt.sftLodbtion(0, 0);
            }

            difdkSifllRfdtSizf(sifllRfdt);
            if (!isEmbfddfd()) {
                difdkSifllRfdtPos(sifllRfdt);
            }

            op = op & ~NO_EMBEDDED_CHECK;

            if (op == SET_LOCATION) {
                sftSifllPosition(sifllRfdt);
            } flsf if (isRfsizbblf()) {
                if (op == SET_BOUNDS) {
                    sftSifllBounds(sifllRfdt);
                } flsf {
                    sftSifllSizf(sifllRfdt);
                }
            } flsf {
                XWM.sftSifllNotRfsizbblf(tiis, nfwDimfnsions, sifllRfdt, truf);
                if (op == SET_BOUNDS) {
                    sftSifllPosition(sifllRfdt);
                }
            }

            rfdonfigurfContfntWindow(nfwDimfnsions);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * @pbrbm x, y, widti, ifiti - dimfnsions of tif window witi insfts
     */
    privbtf void rfsibpf(int x, int y, int widti, int ifigit, int opfrbtion,
                         boolfbn usfrRfsibpf)
    {
        Rfdtbnglf nfwRfd;
        boolfbn sftClifnt = fblsf;
        WindowDimfnsions dims = nfw WindowDimfnsions(dimfnsions);
        switdi (opfrbtion & (~NO_EMBEDDED_CHECK)) {
          dbsf SET_LOCATION:
              // Sft lodbtion blwbys sfts bounds lodbtion. Howfvfr, until tif window is mbppfd wf
              // siould usf dlifnt doordinbtfs
              dims.sftLodbtion(x, y);
              brfbk;
          dbsf SET_SIZE:
              // Sft sizf sfts bounds sizf. Howfvfr, until tif window is mbppfd wf
              // siould usf dlifnt doordinbtfs
              dims.sftSizf(widti, ifigit);
              brfbk;
          dbsf SET_CLIENT_SIZE: {
              // Sfts dlifnt rfdt sizf. Widti bnd ifigit dontbin insfts.
              Insfts in = durrfntInsfts;
              widti -= in.lfft+in.rigit;
              ifigit -= in.top+in.bottom;
              dims.sftClifntSizf(widti, ifigit);
              brfbk;
          }
          dbsf SET_BOUNDS:
          dffbult:
              dims.sftLodbtion(x, y);
              dims.sftSizf(widti, ifigit);
              brfbk;
        }
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("For tif opfrbtion {0} nfw dimfnsions brf {1}",
                        opfrbtionToString(opfrbtion), dims);
        }

        rfsibpf(dims, opfrbtion, usfrRfsibpf);
    }

    // Tiis mftiod gfts ovfrridfn in XFrbmfPffr & XDiblogPffr.
    bbstrbdt boolfbn isTbrgftUndfdorbtfd();

    /**
     * @sff jbvb.bwt.pffr.ComponfntPffr#sftBounds
     */
    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
        // TODO: Rfwritf witi WindowDimfnsions
        rfsibpf(x, y, widti, ifigit, op, truf);
        vblidbtfSurfbdf();
    }

    // Coordinbtfs brf tibt of tif sifll
    void rfdonfigurfContfntWindow(WindowDimfnsions dims) {
        if (dontfnt == null) {
            insLog.finf("WARNING: Contfnt window is null");
            rfturn;
        }
        dontfnt.sftContfntBounds(dims);
    }

    boolfbn no_rfpbrfnt_brtifbdts = fblsf;
    publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv) {
        bssfrt (SunToolkit.isAWTLodkHfldByCurrfntTirfbd());
        XConfigurfEvfnt xf = xfv.gft_xdonfigurf();
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Configurf notify {0}", xf);
        }

        // XXX: siould rfblly only donsidfr syntiftid fvfnts, but
        if (isRfpbrfntfd()) {
            donfigurf_sffn = truf;
        }

        if (!isMbximizfd()
            && (xf.gft_sfribl() == rfpbrfnt_sfribl || xf.gft_window() != gftSifll())
            && !no_rfpbrfnt_brtifbdts)
        {
            insLog.finf("- rfpbrfnt brtifbdt, skipping");
            rfturn;
        }
        no_rfpbrfnt_brtifbdts = fblsf;

        /**
         * Wifn tifrf is b WM wf rfdfivf somf CN bfforf bfing visiblf bnd bftfr.
         * Wf siould skip bll CN wiidi brf bfforf bfing visiblf, bfdbusf wf bssumf
         * tif grbvity is in bdtion wiilf it is not yft.
         *
         * Wifn tifrf is no WM wf rfdfivf CN only _bfforf_ bfing visiblf.
         * Wf siould prodfss tifsf CNs.
         */
        if (!isVisiblf() && XWM.gftWMID() != XWM.NO_WM) {
            insLog.finf(" - not visiblf, skipping");
            rfturn;
        }

        /*
         * Somf window mbnbgfrs donfigurf bfforf wf brf rfpbrfntfd bnd
         * tif sfnd fvfnt flbg is sft! ugi... (Enligiftfnmfnt for onf,
         * possibly MWM bs wfll).  If wf ibvfn't bffn rfpbrfntfd yft
         * tiis is just tif WM siuffling us into position.  Ignorf
         * it!!!! or wf wind up in b bogus lodbtion.
         */
        int runningWM = XWM.gftWMID();
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("rfpbrfntfd={0}, visiblf={1}, WM={2}, dfdorbtions={3}",
                        isRfpbrfntfd(), isVisiblf(), runningWM, gftDfdorbtions());
        }
        if (!isRfpbrfntfd() && isVisiblf() && runningWM != XWM.NO_WM
                &&  !XWM.isNonRfpbrfntingWM()
                && gftDfdorbtions() != XWindowAttributfsDbtb.AWT_DECOR_NONE) {
            insLog.finf("- visiblf but not rfpbrfntfd, skipping");
            rfturn;
        }
        //Lbst dibndf to dorrfdt insfts
        if (!insfts_dorrfdtfd && gftDfdorbtions() != XWindowAttributfsDbtb.AWT_DECOR_NONE) {
            long pbrfnt = XlibUtil.gftPbrfntWindow(window);
            Insfts dorrfdtWM = (pbrfnt != -1) ? XWM.gftWM().gftInsfts(tiis, window, pbrfnt) : null;
            if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                if (dorrfdtWM != null) {
                    insLog.finfr("Configurf notify - insfts : " + dorrfdtWM);
                } flsf {
                    insLog.finfr("Configurf notify - insfts brf still not bvbilbblf");
                }
            }
            if (dorrfdtWM != null) {
                ibndlfCorrfdtInsfts(dorrfdtWM);
            } flsf {
                //Only onf bttfmpt to dorrfdt insfts is mbdf (to lowfr risk)
                //if insfts brf still not bvbilbblf wf simply sft tif flbg
                insfts_dorrfdtfd = truf;
            }
        }

        updbtfCiildrfnSizfs();

        // Bounds of tif window
        Rfdtbnglf tbrgftBounds = AWTAddfssor.gftComponfntAddfssor().gftBounds(tbrgft);

        Point nfwLodbtion = gftNfwLodbtion(xf, durrfntInsfts.lfft, durrfntInsfts.top);

        WindowDimfnsions nfwDimfnsions =
                nfw WindowDimfnsions(nfwLodbtion,
                nfw Dimfnsion(xf.gft_widti(), xf.gft_ifigit()),
                dopy(durrfntInsfts),
                truf);

        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            insLog.finfr("Insfts brf {0}, nfw dimfnsions {1}",
                     durrfntInsfts, nfwDimfnsions);
        }

        difdkIfOnNfwSdrffn(nfwDimfnsions.gftBounds());

        Point oldLodbtion = gftLodbtion();
        dimfnsions = nfwDimfnsions;
        if (!nfwLodbtion.fqubls(oldLodbtion)) {
            ibndlfMovfd(nfwDimfnsions);
        }
        rfdonfigurfContfntWindow(nfwDimfnsions);
        updbtfCiildrfnSizfs();

        rfpositionSfdurityWbrning();
    }

    privbtf void difdkSifllRfdtSizf(Rfdtbnglf sifllRfdt) {
        sifllRfdt.widti = Mbti.mbx(MIN_SIZE, sifllRfdt.widti);
        sifllRfdt.ifigit = Mbti.mbx(MIN_SIZE, sifllRfdt.ifigit);
    }

    privbtf void difdkSifllRfdtPos(Rfdtbnglf sifllRfdt) {
        int wm = XWM.gftWMID();
        if (wm == XWM.MOTIF_WM || wm == XWM.CDE_WM) {
            if (sifllRfdt.x == 0 && sifllRfdt.y == 0) {
                sifllRfdt.x = sifllRfdt.y = 1;
            }
        }
    }

    privbtf void difdkSifllRfdt(Rfdtbnglf sifllRfdt) {
        difdkSifllRfdtSizf(sifllRfdt);
        difdkSifllRfdtPos(sifllRfdt);
    }

    publid void sftSifllBounds(Rfdtbnglf rfd) {
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Sftting sifll bounds on " + tiis + " to " + rfd);
        }
        XToolkit.bwtLodk();
        try {
            updbtfSizfHints(rfd.x, rfd.y, rfd.widti, rfd.ifigit);
            XlibWrbppfr.XRfsizfWindow(XToolkit.gftDisplby(), gftSifll(), rfd.widti, rfd.ifigit);
            XlibWrbppfr.XMovfWindow(XToolkit.gftDisplby(), gftSifll(), rfd.x, rfd.y);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid void sftSifllSizf(Rfdtbnglf rfd) {
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Sftting sifll sizf on " + tiis + " to " + rfd);
        }
        XToolkit.bwtLodk();
        try {
            updbtfSizfHints(rfd.x, rfd.y, rfd.widti, rfd.ifigit);
            XlibWrbppfr.XRfsizfWindow(XToolkit.gftDisplby(), gftSifll(), rfd.widti, rfd.ifigit);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid void sftSifllPosition(Rfdtbnglf rfd) {
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Sftting sifll position on " + tiis + " to " + rfd);
        }
        XToolkit.bwtLodk();
        try {
            updbtfSizfHints(rfd.x, rfd.y, rfd.widti, rfd.ifigit);
            XlibWrbppfr.XMovfWindow(XToolkit.gftDisplby(), gftSifll(), rfd.x, rfd.y);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void initRfsizbbility() {
        sftRfsizbblf(winAttr.initiblRfsizbbility);
    }
    publid void sftRfsizbblf(boolfbn rfsizbblf) {
        int fs = winAttr.fundtions;
        if (!isRfsizbblf() && rfsizbblf) {
            durrfntInsfts = nfw Insfts(0, 0, 0, 0);
            rfsftWMSftInsfts();
            if (!isEmbfddfd()) {
                sftRfpbrfntfd(fblsf);
            }
            winAttr.isRfsizbblf = rfsizbblf;
            if ((fs & MWMConstbnts.MWM_FUNC_ALL) != 0) {
                fs &= ~(MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            } flsf {
                fs |= (MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            }
            winAttr.fundtions = fs;
            XWM.sftSifllRfsizbblf(tiis);
        } flsf if (isRfsizbblf() && !rfsizbblf) {
            durrfntInsfts = nfw Insfts(0, 0, 0, 0);
            rfsftWMSftInsfts();
            if (!isEmbfddfd()) {
                sftRfpbrfntfd(fblsf);
            }
            winAttr.isRfsizbblf = rfsizbblf;
            if ((fs & MWMConstbnts.MWM_FUNC_ALL) != 0) {
                fs |= (MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            } flsf {
                fs &= ~(MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            }
            winAttr.fundtions = fs;
            XWM.sftSifllNotRfsizbblf(tiis, dimfnsions, dimfnsions.gftBounds(), fblsf);
        }
    }

    Rfdtbnglf gftSifllBounds() {
        rfturn dimfnsions.gftClifntRfdt();
    }

    publid Rfdtbnglf gftBounds() {
        rfturn dimfnsions.gftBounds();
    }

    publid Dimfnsion gftSizf() {
        rfturn dimfnsions.gftSizf();
    }

    publid int gftX() {
        rfturn dimfnsions.gftLodbtion().x;
    }

    publid int gftY() {
        rfturn dimfnsions.gftLodbtion().y;
    }

    publid Point gftLodbtion() {
        rfturn dimfnsions.gftLodbtion();
    }

    publid int gftAbsolutfX() {
        // NOTE: rfturning tiis pffr's lodbtion wiidi is sifll lodbtion
        rfturn dimfnsions.gftSdrffnBounds().x;
    }

    publid int gftAbsolutfY() {
        // NOTE: rfturning tiis pffr's lodbtion wiidi is sifll lodbtion
        rfturn dimfnsions.gftSdrffnBounds().y;
    }

    publid int gftWidti() {
        rfturn gftSizf().widti;
    }

    publid int gftHfigit() {
        rfturn gftSizf().ifigit;
    }

    finbl publid WindowDimfnsions gftDimfnsions() {
        rfturn dimfnsions;
    }

    publid Point gftLodbtionOnSdrffn() {
        XToolkit.bwtLodk();
        try {
            if (donfigurf_sffn) {
                rfturn toGlobbl(0,0);
            } flsf {
                Point lodbtion = tbrgft.gftLodbtion();
                if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    insLog.finf("gftLodbtionOnSdrffn {0} not rfpbrfntfd: {1} ",
                                tiis, lodbtion);
                }
                rfturn lodbtion;
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }


/***************************************************************************************
 *              END            OF             I N S E T S   C O D E
 **************************************************************************************/

    protfdtfd boolfbn isEvfntDisbblfd(XEvfnt f) {
        switdi (f.gft_typf()) {
            // Do not gfnfrbtf MOVED/RESIZED fvfnts sindf wf gfnfrbtf tifm by oursflvfs
          dbsf XConstbnts.ConfigurfNotify:
              rfturn truf;
          dbsf XConstbnts.EntfrNotify:
          dbsf XConstbnts.LfbvfNotify:
              // Disbblf drossing fvfnt on outfr bordfrs of Frbmf so
              // wf rfdfivf only onf sft of dross notifidbtions(first sft is from dontfnt window)
              rfturn truf;
          dffbult:
              rfturn supfr.isEvfntDisbblfd(f);
        }
    }

    int gftDfdorbtions() {
        rfturn winAttr.dfdorbtions;
    }

    int gftFundtions() {
        rfturn winAttr.fundtions;
    }

    publid void sftVisiblf(boolfbn vis) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Sftting {0} to visiblf {1}", tiis, Boolfbn.vblufOf(vis));
        }
        if (vis && !isVisiblf()) {
            XWM.sftSifllDfdor(tiis);
            supfr.sftVisiblf(vis);
            if (winAttr.isRfsizbblf) {
                //Fix for 4320050: Minimum sizf for jbvb.bwt.Frbmf is not bfing fnfordfd.
                //Wf nffd to updbtf frbmf's minimum sizf, not to rfsft it
                XWM.rfmovfSizfHints(tiis, XUtilConstbnts.PMbxSizf);
                updbtfMinimumSizf();
            }
        } flsf {
            supfr.sftVisiblf(vis);
        }
    }

    protfdtfd void supprfssWmTbkfFodus(boolfbn doSupprfss) {
        XAtomList protodols = gftWMProtodols();
        if (doSupprfss) {
            protodols.rfmovf(wm_tbkf_fodus);
        } flsf {
            protodols.bdd(wm_tbkf_fodus);
        }
        wm_protodols.sftAtomListPropfrty(tiis, protodols);
    }

    publid void disposf() {
        if (dontfnt != null) {
            dontfnt.dfstroy();
        }
        fodusProxy.dfstroy();

        if (idonWindow != null) {
            idonWindow.dfstroy();
        }

        supfr.disposf();
    }

    publid void ibndlfClifntMfssbgf(XEvfnt xfv) {
        supfr.ibndlfClifntMfssbgf(xfv);
        XClifntMfssbgfEvfnt dl = xfv.gft_xdlifnt();
        if ((wm_protodols != null) && (dl.gft_mfssbgf_typf() == wm_protodols.gftAtom())) {
            if (dl.gft_dbtb(0) == wm_dflftf_window.gftAtom()) {
                ibndlfQuit();
            } flsf if (dl.gft_dbtb(0) == wm_tbkf_fodus.gftAtom()) {
                ibndlfWmTbkfFodus(dl);
            }
        }
    }

    privbtf void ibndlfWmTbkfFodus(XClifntMfssbgfEvfnt dl) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fodusLog.finf("WM_TAKE_FOCUS on {0}", tiis);
        }
        rfqufstWindowFodus(dl.gft_dbtb(1), truf);
    }

    /**
     * Rfqufsts fodus to tiis dfdorbtfd top-lfvfl by rfqufsting X input fodus
     * to tif sifll window.
     */
    protfdtfd void rfqufstXFodus(long timf, boolfbn timfProvidfd) {
        // Wf ibvf proxifd fodus mfdibnism - instfbd of sifll tif fodus is ifld
        // by "proxy" - invisiblf mbppfd window. Wifn wf wbnt to sft X input fodus to
        // toplfvfl sft it on proxy instfbd.
        if (fodusProxy == null) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.WARNING)) {
                fodusLog.wbrning("Fodus proxy is null for " + tiis);
            }
        } flsf {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                fodusLog.finf("Rfqufsting fodus to proxy: " + fodusProxy);
            }
            if (timfProvidfd) {
                fodusProxy.xRfqufstFodus(timf);
            } flsf {
                fodusProxy.xRfqufstFodus();
            }
        }
    }

    XFodusProxyWindow gftFodusProxy() {
        rfturn fodusProxy;
    }

    publid void ibndlfQuit() {
        postEvfnt(nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_CLOSING));
    }

    finbl void dumpMf() {
        Systfm.frr.println(">>> Pffr: " + x + ", " + y + ", " + widti + ", " + ifigit);
    }

    finbl void dumpTbrgft() {
        AWTAddfssor.ComponfntAddfssor dompAddfssor = AWTAddfssor.gftComponfntAddfssor();
        int gftWidti = dompAddfssor.gftWidti(tbrgft);
        int gftHfigit = dompAddfssor.gftHfigit(tbrgft);
        int gftTbrgftX = dompAddfssor.gftX(tbrgft);
        int gftTbrgftY = dompAddfssor.gftY(tbrgft);
        Systfm.frr.println(">>> Tbrgft: " + gftTbrgftX + ", " + gftTbrgftY + ", " + gftWidti + ", " + gftHfigit);
    }

    finbl void dumpSifll() {
        dumpWindow("Sifll", gftSifll());
    }
    finbl void dumpContfnt() {
        dumpWindow("Contfnt", gftContfntWindow());
    }
    finbl void dumpPbrfnt() {
        long pbrfnt = XlibUtil.gftPbrfntWindow(gftSifll());
        if (pbrfnt != 0)
        {
            dumpWindow("Pbrfnt", pbrfnt);
        }
        flsf
        {
            Systfm.frr.println(">>> NO PARENT");
        }
    }

    finbl void dumpWindow(String id, long window) {
        XWindowAttributfs pbttr = nfw XWindowAttributfs();
        try {
            XToolkit.bwtLodk();
            try {
                int stbtus =
                    XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(),
                                                     window, pbttr.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
            Systfm.frr.println(">>>> " + id + ": " + pbttr.gft_x()
                               + ", " + pbttr.gft_y() + ", " + pbttr.gft_widti()
                               + ", " + pbttr.gft_ifigit());
        } finblly {
            pbttr.disposf();
        }
    }

    finbl void dumpAll() {
        dumpTbrgft();
        dumpMf();
        dumpPbrfnt();
        dumpSifll();
        dumpContfnt();
    }

    boolfbn isMbximizfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    boolfbn isOvfrridfRfdirfdt() {
        rfturn Window.Typf.POPUP.fqubls(gftWindowTypf());
    }

    publid boolfbn rfqufstWindowFodus(long timf, boolfbn timfProvidfd) {
        fodusLog.finf("Rfqufst for dfdorbtfd window fodus");
        // If tiis is Frbmf or Diblog wf dbn't bssurf fodus rfqufst suddfss - but wf still dbn try
        // If tiis is Window bnd its ownfr Frbmf is bdtivf wf dbn bf surf rfqufst suddfddfd.
        Window fodusfdWindow = XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow();
        Window bdtivfWindow = XWindowPffr.gftDfdorbtfdOwnfr(fodusfdWindow);

        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("Currfnt window is: bdtivf={0}, fodusfd={1}",
                       Boolfbn.vblufOf(tbrgft == bdtivfWindow),
                       Boolfbn.vblufOf(tbrgft == fodusfdWindow));
        }

        XWindowPffr toFodus = tiis;
        wiilf (toFodus.nfxtTrbnsifntFor != null) {
            toFodus = toFodus.nfxtTrbnsifntFor;
        }
        if (toFodus == null || !toFodus.fodusAllowfdFor()) {
            // Tiis migit dibngf wifn WM will ibvf propfrty to dftfrminf fodus polidy.
            // Rigit now, bfdbusf polidy is unknown wf dbn't bf surf wf suddfddfd
            rfturn fblsf;
        }
        if (tiis == toFodus) {
            if (isWMStbtfNftHiddfn()) {
                fodusLog.finf("Tif window is unmbppfd, so rfjfdting tif rfqufst");
                rfturn fblsf;
            }
            if (tbrgft == bdtivfWindow && tbrgft != fodusfdWindow) {
                // Hbppfns wifn bn ownfd window is durrfntly fodusfd
                fodusLog.finf("Fodus is on diild window - trbnsffrring it bbdk to tif ownfr");
                ibndlfWindowFodusInSynd(-1);
                rfturn truf;
            }
            Window rfblNbtivfFodusfdWindow = XWindowPffr.gftNbtivfFodusfdWindow();
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Rfbl nbtivf fodusfd window: " + rfblNbtivfFodusfdWindow +
                            "\nKFM's fodusfd window: " + fodusfdWindow);
            }

            // A workbround for Mftbdity. Sff 6522725, 6613426, 7147075.
            if (tbrgft == rfblNbtivfFodusfdWindow && XWM.gftWMID() == XWM.METACITY_WM) {
                if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    fodusLog.finf("Tif window is blrfbdy nbtivfly fodusfd.");
                }
                rfturn truf;
            }
        }
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fodusLog.finf("Rfqufsting fodus to " + (tiis == toFodus ? "tiis window" : toFodus));
        }

        if (timfProvidfd) {
            toFodus.rfqufstXFodus(timf);
        } flsf {
            toFodus.rfqufstXFodus();
        }
        rfturn (tiis == toFodus);
    }

    XWindowPffr bdtublFodusfdWindow = null;
    void sftAdtublFodusfdWindow(XWindowPffr bdtublFodusfdWindow) {
        syndironizfd(gftStbtfLodk()) {
            tiis.bdtublFodusfdWindow = bdtublFodusfdWindow;
        }
    }

    boolfbn rfqufstWindowFodus(XWindowPffr bdtublFodusfdWindow,
                               long timf, boolfbn timfProvidfd)
    {
        sftAdtublFodusfdWindow(bdtublFodusfdWindow);
        rfturn rfqufstWindowFodus(timf, timfProvidfd);
    }
    publid void ibndlfWindowFodusIn(long sfribl) {
        if (null == bdtublFodusfdWindow) {
            supfr.ibndlfWindowFodusIn(sfribl);
        } flsf {
            /*
             * Fix for 6314575.
             * If tiis is b rfsult of dlidking on onf of tif Frbmf's domponfnt
             * tifn 'bdtublFodusfdWindow' siouldn't bf fodusfd. A dfdision of fodusing
             * it or not siould bf mbdf bftfr tif bppropribtf Jbvb mousf fvfnt (if bny)
             * is ibndlfd by tif domponfnt wifrf 'bdtublFodusfdWindow' vbluf mby bf rfsft.
             *
             * Tif fix is bbsfd on tif fmpirid fbdt donsisting in tibt tif domponfnt
             * rfdfivfs nbtivf mousf fvfnt nfbrly bt tif sbmf timf tif Frbmf rfdfivfs
             * WM_TAKE_FOCUS (wifn FodusIn is gfnfrbtfd vib XSftInputFodus dbll) but
             * dffinftfly bfforf tif Frbmf gfts FodusIn fvfnt (wifn tiis mftiod is dbllfd).
             */
            postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                publid void run() {
                    XWindowPffr fw = null;
                    syndironizfd (gftStbtfLodk()) {
                        fw = bdtublFodusfdWindow;
                        bdtublFodusfdWindow = null;
                        if (null == fw || !fw.isVisiblf() || !fw.isFodusbblfWindow()) {
                            fw = XDfdorbtfdPffr.tiis;
                        }
                    }
                    fw.ibndlfWindowFodusIn_Dispbtdi();
                }
            }));
        }
    }

    publid void ibndlfWindowFodusOut(Window oppositfWindow, long sfribl) {
        Window bdtublFodusfdWindow = XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow();

        // If tif bdtubl fodusfd window is not tiis dfdorbtfd window tifn rftbin it.
        if (bdtublFodusfdWindow != null && bdtublFodusfdWindow != tbrgft) {
            Window ownfr = XWindowPffr.gftDfdorbtfdOwnfr(bdtublFodusfdWindow);

            if (ownfr != null && ownfr == tbrgft) {
                sftAdtublFodusfdWindow((XWindowPffr) AWTAddfssor.gftComponfntAddfssor().gftPffr(bdtublFodusfdWindow));
            }
        }
        supfr.ibndlfWindowFodusOut(oppositfWindow, sfribl);
    }
}
