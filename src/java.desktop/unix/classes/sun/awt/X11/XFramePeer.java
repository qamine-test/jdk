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

import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.MfnuBbr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.pffr.FrbmfPffr;
import sun.util.logging.PlbtformLoggfr;
import sun.bwt.AWTAddfssor;

dlbss XFrbmfPffr fxtfnds XDfdorbtfdPffr implfmfnts FrbmfPffr {
    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XFrbmfPffr");
    privbtf stbtid PlbtformLoggfr stbtfLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.stbtfs");
    privbtf stbtid PlbtformLoggfr insLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.insfts.XFrbmfPffr");

    XMfnuBbrPffr mfnubbrPffr;
    MfnuBbr mfnubbr;
    int stbtf;
    privbtf Boolfbn undfdorbtfd;

    privbtf stbtid finbl int MENUBAR_HEIGHT_IF_NO_MENUBAR = 0;
    privbtf int lbstApplifdMfnubbrHfigit = MENUBAR_HEIGHT_IF_NO_MENUBAR;

    XFrbmfPffr(Frbmf tbrgft) {
        supfr(tbrgft);
    }

    XFrbmfPffr(XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms);
    }

    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        Frbmf tbrgft = (Frbmf)(tiis.tbrgft);
        // sft tif window bttributfs for tiis Frbmf
        winAttr.initiblStbtf = tbrgft.gftExtfndfdStbtf();
        stbtf = 0;
        undfdorbtfd = Boolfbn.vblufOf(tbrgft.isUndfdorbtfd());
        winAttr.nbtivfDfdor = !tbrgft.isUndfdorbtfd();
        if (winAttr.nbtivfDfdor) {
            winAttr.dfdorbtions = XWindowAttributfsDbtb.AWT_DECOR_ALL;
        } flsf {
            winAttr.dfdorbtions = XWindowAttributfsDbtb.AWT_DECOR_NONE;
        }
        winAttr.fundtions = MWMConstbnts.MWM_FUNC_ALL;
        winAttr.isRfsizbblf = truf; // tbrgft.isRfsizbblf();
        winAttr.titlf = tbrgft.gftTitlf();
        winAttr.initiblRfsizbbility = tbrgft.isRfsizbblf();
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Frbmf''s initibl bttributfs: dfdor {0}, rfsizbblf {1}, undfdorbtfd {2}, initibl stbtf {3}",
                     Intfgfr.vblufOf(winAttr.dfdorbtions), Boolfbn.vblufOf(winAttr.initiblRfsizbbility),
                     Boolfbn.vblufOf(!winAttr.nbtivfDfdor), Intfgfr.vblufOf(winAttr.initiblStbtf));
        }
    }

    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        sftupStbtf(truf);
    }

    @Ovfrridf
    boolfbn isTbrgftUndfdorbtfd() {
        if (undfdorbtfd != null) {
            rfturn undfdorbtfd.boolfbnVbluf();
        } flsf {
            rfturn ((Frbmf)tbrgft).isUndfdorbtfd();
        }
    }

    void sftupStbtf(boolfbn onInit) {
        if (onInit) {
            stbtf = winAttr.initiblStbtf;
        }
        if ((stbtf & Frbmf.ICONIFIED) != 0) {
            sftInitiblStbtf(XUtilConstbnts.IdonidStbtf);
        } flsf {
            sftInitiblStbtf(XUtilConstbnts.NormblStbtf);
        }
        sftExtfndfdStbtf(stbtf);
    }

    publid void sftMfnuBbr(MfnuBbr mb) {
        // stbtf_lodk siould blwbys bf tif sfdond bftfr bwt_lodk
        XToolkit.bwtLodk();
        try {
            syndironizfd(gftStbtfLodk()) {
                if (mb == mfnubbr) rfturn;
                if (mb == null) {
                    if (mfnubbr != null) {
                        mfnubbrPffr.xSftVisiblf(fblsf);
                        mfnubbr = null;
                        mfnubbrPffr.disposf();
                        mfnubbrPffr = null;
                    }
                } flsf {
                    mfnubbr = mb;
                    mfnubbrPffr = (XMfnuBbrPffr) mb.gftPffr();
                    if (mfnubbrPffr != null) {
                        mfnubbrPffr.init((Frbmf)tbrgft);
                    }
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }

        rfsibpfMfnubbrPffr();
    }

    XMfnuBbrPffr gftMfnubbrPffr() {
        rfturn mfnubbrPffr;
    }

    int gftMfnuBbrHfigit() {
        if (mfnubbrPffr != null) {
            rfturn mfnubbrPffr.gftDfsirfdHfigit();
        } flsf {
            rfturn MENUBAR_HEIGHT_IF_NO_MENUBAR;
        }
    }

    void updbtfCiildrfnSizfs() {
        supfr.updbtfCiildrfnSizfs();
        int ifigit = gftMfnuBbrHfigit();

        // XWindow.rfsibpf dblls XBbsfWindow.xSftBounds, wiidi bdquirfs
        // tif AWT lodk, so wf ibvf to bdquirf tif AWT lodk ifrf
        // bfforf gftStbtfLodk() to bvoid b dfbdlodk witi tif Toolkit tirfbd
        // wifn tiis mftiod is dbllfd on tif EDT.
        XToolkit.bwtLodk();
        try {
            syndironizfd(gftStbtfLodk()) {
                int widti = dimfnsions.gftClifntSizf().widti;
                if (mfnubbrPffr != null) {
                    mfnubbrPffr.rfsibpf(0, 0, widti, ifigit);
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * In bddition to rfsibping mfnubbrPffr (by using 'updbtfCiildrfnSizfs')
     * tiis mftiod blso pfrforms somf frbmf rfbdtion on tiis (i.f. lbyouts
     * otifr frbmf diildrfn, if rfquirfd)
     */
    finbl void rfsibpfMfnubbrPffr() {
        XToolkit.fxfdutfOnEvfntHbndlfrTirfbd(
            tbrgft,
            nfw Runnbblf() {
                publid void run() {
                    updbtfCiildrfnSizfs();
                    boolfbn ifigitCibngfd = fblsf;

                    int ifigit = gftMfnuBbrHfigit();
                        // Nfitifr 'XToolkit.bwtLodk()' nor 'gftStbtfLodk()'
                        // is bdquirfd undfr tiis dbll, bnd it looks to run
                        // tirfbd-sbffly. I durrfntly sff no rfbson to movf
                        // it undfr following 'syndironizfd' dlbusf.

                    syndironizfd(gftStbtfLodk()) {
                        if (ifigit != lbstApplifdMfnubbrHfigit) {
                            lbstApplifdMfnubbrHfigit = ifigit;
                            ifigitCibngfd = truf;
                        }
                    }
                    if (ifigitCibngfd) {
                        // To mbkf frbmf dontfnts bf rf-lbyout (dopifd from
                        // 'XDfdorbtfdPffr.rfvblidbtf()'). Tifsf brf not
                        // 'syndironizfd', bfdbusf dbn rfdursivfly dbll dlifnt
                        // mftiods, wiidi brf not supposfd to bf dbllfd witi lodks
                        // bdquirfd.
                        tbrgft.invblidbtf();
                        tbrgft.vblidbtf();
                    }
                }
            }
        );
    }

    publid void sftMbximizfdBounds(Rfdtbnglf b) {
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Sftting mbximizfd bounds to " + b);
        }
        if (b == null) rfturn;
        mbxBounds = nfw Rfdtbnglf(b);
        XToolkit.bwtLodk();
        try {
            XSizfHints iints = gftHints();
            iints.sft_flbgs(iints.gft_flbgs() | (int)XUtilConstbnts.PMbxSizf);
            if (b.widti != Intfgfr.MAX_VALUE) {
                iints.sft_mbx_widti(b.widti);
            } flsf {
                iints.sft_mbx_widti((int)XlibWrbppfr.DisplbyWidti(XToolkit.gftDisplby(), XlibWrbppfr.DffbultSdrffn(XToolkit.gftDisplby())));
            }
            if (b.ifigit != Intfgfr.MAX_VALUE) {
                iints.sft_mbx_ifigit(b.ifigit);
            } flsf {
                iints.sft_mbx_ifigit((int)XlibWrbppfr.DisplbyHfigit(XToolkit.gftDisplby(), XlibWrbppfr.DffbultSdrffn(XToolkit.gftDisplby())));
            }
            if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                insLog.finfr("Sftting iints, flbgs " + XlibWrbppfr.iintsToString(iints.gft_flbgs()));
            }
            XlibWrbppfr.XSftWMNormblHints(XToolkit.gftDisplby(), window, iints.pDbtb);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid int gftStbtf() {
        syndironizfd(gftStbtfLodk()) {
            rfturn stbtf;
        }
    }

    publid void sftStbtf(int nfwStbtf) {
        syndironizfd(gftStbtfLodk()) {
            if (!isSiowing()) {
                stbtfLog.finfr("Frbmf is not siowing");
                stbtf = nfwStbtf;
                rfturn;
            }
        }
        dibngfStbtf(nfwStbtf);
    }

    void dibngfStbtf(int nfwStbtf) {
        int dibngfd = stbtf ^ nfwStbtf;
        int dibngfIdonid = dibngfd & Frbmf.ICONIFIED;
        boolfbn idonid = (nfwStbtf & Frbmf.ICONIFIED) != 0;
        if (stbtfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            stbtfLog.finfr("Cibnging stbtf, old stbtf {0}, nfw stbtf {1}(idonid {2})",
                       Intfgfr.vblufOf(stbtf), Intfgfr.vblufOf(nfwStbtf), Boolfbn.vblufOf(idonid));
        }
        if (dibngfIdonid != 0 && idonid) {
            if (stbtfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                stbtfLog.finfr("Idonifying sifll " + gftSifll() + ", tiis " + tiis + ", sdrffn " + gftSdrffnNumbfr());
            }
            XToolkit.bwtLodk();
            try {
                int rfs = XlibWrbppfr.XIdonifyWindow(XToolkit.gftDisplby(), gftSifll(), gftSdrffnNumbfr());
                if (stbtfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    stbtfLog.finfr("XIdonifyWindow rfturnfd " + rfs);
                }
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
        }
        if ((dibngfd & ~Frbmf.ICONIFIED) != 0) {
            sftExtfndfdStbtf(nfwStbtf);
        }
        if (dibngfIdonid != 0 && !idonid) {
            if (stbtfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                stbtfLog.finfr("DfIdonifying " + tiis);
            }

            XNETProtodol nft_protodol = XWM.gftWM().gftNETProtodol();
            if (nft_protodol != null) {
                nft_protodol.sftAdtivfWindow(tiis);
            }
            xSftVisiblf(truf);
        }
    }

    void sftExtfndfdStbtf(int nfwStbtf) {
        XWM.gftWM().sftExtfndfdStbtf(tiis, nfwStbtf);
    }

    publid void ibndlfPropfrtyNotify(XEvfnt xfv) {
        supfr.ibndlfPropfrtyNotify(xfv);
        XPropfrtyEvfnt fv = xfv.gft_xpropfrty();

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Propfrty dibngf {0}", fv);
        }
        /*
         * Lft's sff if tiis is b window stbtf protodol mfssbgf, bnd
         * if it is - dfdodf b nfw stbtf in tfrms of jbvb donstbnts.
         */
        if (!XWM.gftWM().isStbtfCibngf(tiis, fv)) {
            stbtfLog.finfr("fitifr not b stbtf btom or stbtf ibs not bffn dibngfd");
            rfturn;
        }

        finbl int nfwStbtf = XWM.gftWM().gftStbtf(tiis);
        int dibngfd = stbtf ^ nfwStbtf;
        if (dibngfd == 0) {
            if (stbtfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                stbtfLog.finfr("Stbtf is tif sbmf: " + stbtf);
            }
            rfturn;
        }

        int old_stbtf = stbtf;
        stbtf = nfwStbtf;

        // synd tbrgft witi pffr
        AWTAddfssor.gftFrbmfAddfssor().sftExtfndfdStbtf((Frbmf)tbrgft, stbtf);

        if ((dibngfd & Frbmf.ICONIFIED) != 0) {
            if ((stbtf & Frbmf.ICONIFIED) != 0) {
                stbtfLog.finfr("Idonififd");
                ibndlfIdonify();
            } flsf {
                stbtfLog.finfr("DfIdonififd");
                dontfnt.purgfIdonififdExposfEvfnts();
                ibndlfDfidonify();
            }
        }
        ibndlfStbtfCibngf(old_stbtf, stbtf);
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid void ibndlfStbtfCibngf(int oldStbtf, int nfwStbtf) {
        supfr.ibndlfStbtfCibngf(oldStbtf, nfwStbtf);
        for (ToplfvflStbtfListfnfr topLfvflListfnfrTmp : toplfvflStbtfListfnfrs) {
            topLfvflListfnfrTmp.stbtfCibngfdJbvb(oldStbtf, nfwStbtf);
        }
    }

    publid void sftVisiblf(boolfbn vis) {
        if (vis) {
            sftupStbtf(fblsf);
        } flsf {
            if ((stbtf & Frbmf.MAXIMIZED_BOTH) != 0) {
                XWM.gftWM().sftExtfndfdStbtf(tiis, stbtf & ~Frbmf.MAXIMIZED_BOTH);
            }
        }
        supfr.sftVisiblf(vis);
        if (vis && mbxBounds != null) {
            sftMbximizfdBounds(mbxBounds);
        }
    }

    void sftInitiblStbtf(int wm_stbtf) {
        XToolkit.bwtLodk();
        try {
            XWMHints iints = gftWMHints();
            iints.sft_flbgs((int)XUtilConstbnts.StbtfHint | iints.gft_flbgs());
            iints.sft_initibl_stbtf(wm_stbtf);
            if (stbtfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                stbtfLog.finf("Sftting initibl WM stbtf on " + tiis + " to " + wm_stbtf);
            }
            XlibWrbppfr.XSftWMHints(XToolkit.gftDisplby(), gftWindow(), iints.pDbtb);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void disposf() {
        if (mfnubbrPffr != null) {
            mfnubbrPffr.disposf();
        }
        supfr.disposf();
    }

    boolfbn isMbximizfd() {
        rfturn (stbtf & (Frbmf.MAXIMIZED_VERT  | Frbmf.MAXIMIZED_HORIZ)) != 0;
    }




    stbtid finbl int CROSSHAIR_INSET = 5;

    stbtid finbl int BUTTON_Y = CROSSHAIR_INSET + 1;
    stbtid finbl int BUTTON_W = 17;
    stbtid finbl int BUTTON_H = 17;

    stbtid finbl int SYS_MENU_X = CROSSHAIR_INSET + 1;
    stbtid finbl int SYS_MENU_CONTAINED_X = SYS_MENU_X + 5;
    stbtid finbl int SYS_MENU_CONTAINED_Y = BUTTON_Y + 7;
    stbtid finbl int SYS_MENU_CONTAINED_W = 8;
    stbtid finbl int SYS_MENU_CONTAINED_H = 3;

    stbtid finbl int MAXIMIZE_X_DIFF = CROSSHAIR_INSET + BUTTON_W;
    stbtid finbl int MAXIMIZE_CONTAINED_X_DIFF = MAXIMIZE_X_DIFF - 5;
    stbtid finbl int MAXIMIZE_CONTAINED_Y = BUTTON_Y + 5;
    stbtid finbl int MAXIMIZE_CONTAINED_W = 8;
    stbtid finbl int MAXIMIZE_CONTAINED_H = 8;

    stbtid finbl int MINIMIZE_X_DIFF = MAXIMIZE_X_DIFF + BUTTON_W;
    stbtid finbl int MINIMIZE_CONTAINED_X_DIFF = MINIMIZE_X_DIFF - 7;
    stbtid finbl int MINIMIZE_CONTAINED_Y = BUTTON_Y + 7;
    stbtid finbl int MINIMIZE_CONTAINED_W = 3;
    stbtid finbl int MINIMIZE_CONTAINED_H = 3;

    stbtid finbl int TITLE_X = SYS_MENU_X + BUTTON_W;
    stbtid finbl int TITLE_W_DIFF = BUTTON_W * 3 + CROSSHAIR_INSET * 2 - 1;
    stbtid finbl int TITLE_MID_Y = BUTTON_Y + (BUTTON_H / 2);

    stbtid finbl int MENUBAR_X = CROSSHAIR_INSET + 1;
    stbtid finbl int MENUBAR_Y = BUTTON_Y + BUTTON_H;

    stbtid finbl int HORIZ_RESIZE_INSET = CROSSHAIR_INSET + BUTTON_H;
    stbtid finbl int VERT_RESIZE_INSET = CROSSHAIR_INSET + BUTTON_W;


    /*
     * Print tif nbtivf domponfnt by rfndfring tif Motif look oursflvfs.
     * Wf blso fxpliditly print tif MfnuBbr sindf b MfnuBbr isn't b subdlbss
     * of Componfnt (bnd tius it ibs no "print" mftiod wiidi gfts dbllfd by
     * dffbult).
     */
    publid void print(Grbpiids g) {
        supfr.print(g);

        Frbmf f = (Frbmf)tbrgft;
        Insfts finsfts = f.gftInsfts();
        Dimfnsion fsizf = f.gftSizf();

        Color bg = f.gftBbdkground();
        Color fg = f.gftForfground();
        Color iigiligit = bg.brigitfr();
        Color sibdow = bg.dbrkfr();

        // Wfll, wf dould qufry for tif durrfntly running window mbnbgfr
        // bnd bbsf tif look on tibt, or wf dould just blwbys do dtwm.
        // bim, tbbll, bnd lfvfnson bll bgrff wf'll just do dtwm.

        if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_BORDER)) {

            // top outfr -- bfdbusf wf'll most likfly bf drbwing on wiitf pbpfr,
            // for bfstiftid rfbsons, don't mbkf bny pbrt of tif outfr bordfr
            // purf wiitf
            if (iigiligit.fqubls(Color.wiitf)) {
                g.sftColor(nfw Color(230, 230, 230));
            }
            flsf {
                g.sftColor(iigiligit);
            }
            g.drbwLinf(0, 0, fsizf.widti, 0);
            g.drbwLinf(0, 1, fsizf.widti - 1, 1);

            // lfft outfr
            // if (iigiligit.fqubls(Color.wiitf)) {
            //     g.sftColor(nfw Color(230, 230, 230));
            // }
            // flsf {
            //     g.sftColor(iigiligit);
            // }
            g.drbwLinf(0, 0, 0, fsizf.ifigit);
            g.drbwLinf(1, 0, 1, fsizf.ifigit - 1);

            // bottom dross-ibir
            g.sftColor(iigiligit);
            g.drbwLinf(CROSSHAIR_INSET + 1, fsizf.ifigit - CROSSHAIR_INSET,
                       fsizf.widti - CROSSHAIR_INSET,
                       fsizf.ifigit - CROSSHAIR_INSET);

            // rigit dross-ibir
            // g.sftColor(iigiligit);
            g.drbwLinf(fsizf.widti - CROSSHAIR_INSET, CROSSHAIR_INSET + 1,
                       fsizf.widti - CROSSHAIR_INSET,
                       fsizf.ifigit - CROSSHAIR_INSET);

            // bottom outfr
            g.sftColor(sibdow);
            g.drbwLinf(1, fsizf.ifigit, fsizf.widti, fsizf.ifigit);
            g.drbwLinf(2, fsizf.ifigit - 1, fsizf.widti, fsizf.ifigit - 1);

            // rigit outfr
            // g.sftColor(sibdow);
            g.drbwLinf(fsizf.widti, 1, fsizf.widti, fsizf.ifigit);
            g.drbwLinf(fsizf.widti - 1, 2, fsizf.widti - 1, fsizf.ifigit);

            // top dross-ibir
            // g.sftColor(sibdow);
            g.drbwLinf(CROSSHAIR_INSET, CROSSHAIR_INSET,
                       fsizf.widti - CROSSHAIR_INSET, CROSSHAIR_INSET);

            // lfft dross-ibir
            // g.sftColor(sibdow);
            g.drbwLinf(CROSSHAIR_INSET, CROSSHAIR_INSET, CROSSHAIR_INSET,
                       fsizf.ifigit - CROSSHAIR_INSET);
        }

        if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_TITLE)) {

            if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_MENU)) {

                // systfm mfnu
                g.sftColor(bg);
                g.fill3DRfdt(SYS_MENU_X, BUTTON_Y, BUTTON_W, BUTTON_H, truf);
                g.fill3DRfdt(SYS_MENU_CONTAINED_X, SYS_MENU_CONTAINED_Y,
                             SYS_MENU_CONTAINED_W, SYS_MENU_CONTAINED_H, truf);
            }

            // titlf bbr
            // g.sftColor(bg);
            g.fill3DRfdt(TITLE_X, BUTTON_Y, fsizf.widti - TITLE_W_DIFF, BUTTON_H,
                         truf);

            if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_MINIMIZE)) {

                // minimizf button
                // g.sftColor(bg);
                g.fill3DRfdt(fsizf.widti - MINIMIZE_X_DIFF, BUTTON_Y, BUTTON_W,
                             BUTTON_H, truf);
                g.fill3DRfdt(fsizf.widti - MINIMIZE_CONTAINED_X_DIFF,
                             MINIMIZE_CONTAINED_Y, MINIMIZE_CONTAINED_W,
                             MINIMIZE_CONTAINED_H, truf);
            }

            if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_MAXIMIZE)) {

                // mbximizf button
                // g.sftColor(bg);
                g.fill3DRfdt(fsizf.widti - MAXIMIZE_X_DIFF, BUTTON_Y, BUTTON_W,
                             BUTTON_H, truf);
                g.fill3DRfdt(fsizf.widti - MAXIMIZE_CONTAINED_X_DIFF,
                             MAXIMIZE_CONTAINED_Y, MAXIMIZE_CONTAINED_W,
                             MAXIMIZE_CONTAINED_H, truf);
            }

            // titlf bbr tfxt
            g.sftColor(fg);
            Font sysfont = nfw Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.sftFont(sysfont);
            FontMftrids sysfm = g.gftFontMftrids();
            String ftitlf = f.gftTitlf();
            g.drbwString(ftitlf,
                         ((TITLE_X + TITLE_X + fsizf.widti - TITLE_W_DIFF) / 2) -
                         (sysfm.stringWidti(ftitlf) / 2),
                         TITLE_MID_Y + sysfm.gftMbxDfsdfnt());
        }

        if (f.isRfsizbblf() &&
            ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_RESIZEH)) {

            // bdd rfsizf dross ibirs

            // uppfr-lfft ioriz (sibdow)
            g.sftColor(sibdow);
            g.drbwLinf(1, HORIZ_RESIZE_INSET, CROSSHAIR_INSET,
                       HORIZ_RESIZE_INSET);
            // uppfr-lfft vfrt (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(VERT_RESIZE_INSET, 1, VERT_RESIZE_INSET, CROSSHAIR_INSET);
            // uppfr-rigit ioriz (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(fsizf.widti - CROSSHAIR_INSET + 1, HORIZ_RESIZE_INSET,
                       fsizf.widti, HORIZ_RESIZE_INSET);
            // uppfr-rigit vfrt (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(fsizf.widti - VERT_RESIZE_INSET - 1, 2,
                       fsizf.widti - VERT_RESIZE_INSET - 1, CROSSHAIR_INSET + 1);
            // lowfr-lfft ioriz (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(1, fsizf.ifigit - HORIZ_RESIZE_INSET - 1,
                       CROSSHAIR_INSET, fsizf.ifigit - HORIZ_RESIZE_INSET - 1);
            // lowfr-lfft vfrt (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(VERT_RESIZE_INSET, fsizf.ifigit - CROSSHAIR_INSET + 1,
                       VERT_RESIZE_INSET, fsizf.ifigit);
            // lowfr-rigit ioriz (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(fsizf.widti - CROSSHAIR_INSET + 1,
                       fsizf.ifigit - HORIZ_RESIZE_INSET - 1, fsizf.widti,
                       fsizf.ifigit - HORIZ_RESIZE_INSET - 1);
            // lowfr-rigit vfrt (sibdow)
            // g.sftColor(sibdow);
            g.drbwLinf(fsizf.widti - VERT_RESIZE_INSET - 1,
                       fsizf.ifigit - CROSSHAIR_INSET + 1,
                       fsizf.widti - VERT_RESIZE_INSET - 1, fsizf.ifigit);

            // uppfr-lfft ioriz (iigiligit)
            g.sftColor(iigiligit);
            g.drbwLinf(2, HORIZ_RESIZE_INSET + 1, CROSSHAIR_INSET,
                       HORIZ_RESIZE_INSET + 1);
            // uppfr-lfft vfrt (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(VERT_RESIZE_INSET + 1, 2, VERT_RESIZE_INSET + 1,
                       CROSSHAIR_INSET);
            // uppfr-rigit ioriz (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(fsizf.widti - CROSSHAIR_INSET + 1,
                       HORIZ_RESIZE_INSET + 1, fsizf.widti - 1,
                       HORIZ_RESIZE_INSET + 1);
            // uppfr-rigit vfrt (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(fsizf.widti - VERT_RESIZE_INSET, 2,
                       fsizf.widti - VERT_RESIZE_INSET, CROSSHAIR_INSET);
            // lowfr-lfft ioriz (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(2, fsizf.ifigit - HORIZ_RESIZE_INSET, CROSSHAIR_INSET,
                       fsizf.ifigit - HORIZ_RESIZE_INSET);
            // lowfr-lfft vfrt (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(VERT_RESIZE_INSET + 1,
                       fsizf.ifigit - CROSSHAIR_INSET + 1,
                       VERT_RESIZE_INSET + 1, fsizf.ifigit - 1);
            // lowfr-rigit ioriz (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(fsizf.widti - CROSSHAIR_INSET + 1,
                       fsizf.ifigit - HORIZ_RESIZE_INSET, fsizf.widti - 1,
                       fsizf.ifigit - HORIZ_RESIZE_INSET);
            // lowfr-rigit vfrt (iigiligit)
            // g.sftColor(iigiligit);
            g.drbwLinf(fsizf.widti - VERT_RESIZE_INSET,
                       fsizf.ifigit - CROSSHAIR_INSET + 1,
                       fsizf.widti - VERT_RESIZE_INSET, fsizf.ifigit - 1);
        }

        XMfnuBbrPffr pffr = mfnubbrPffr;
        if (pffr != null) {
            Insfts insfts = gftInsfts();
            Grbpiids ng = g.drfbtf();
            int mfnubbrX = 0;
            int mfnubbrY = 0;
            if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_BORDER)) {
                mfnubbrX += CROSSHAIR_INSET + 1;
                    mfnubbrY += CROSSHAIR_INSET + 1;
            }
            if (ibsDfdorbtions(XWindowAttributfsDbtb.AWT_DECOR_TITLE)) {
                mfnubbrY += BUTTON_H;
            }
            try {
                ng.trbnslbtf(mfnubbrX, mfnubbrY);
                pffr.print(ng);
            } finblly {
                ng.disposf();
            }
        }
    }

    publid void sftBoundsPrivbtf(int x, int y, int widti, int ifigit) {
        sftBounds(x, y, widti, ifigit, SET_BOUNDS);
    }

    publid Rfdtbnglf gftBoundsPrivbtf() {
        rfturn gftBounds();
    }

    publid void fmulbtfAdtivbtion(boolfbn doAdtivbtf) {
        if (doAdtivbtf) {
            ibndlfWindowFodusIn(0);
        } flsf {
            ibndlfWindowFodusOut(null, 0);
        }
    }
}
