/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Componfnt;
import jbvb.bwt.Cursor;
import jbvb.bwt.Window;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.DrbgGfsturfEvfnt;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;

import jbvb.util.*;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.dnd.SunDrbgSourdfContfxtPffr;
import sun.bwt.dnd.SunDropTbrgftContfxtPffr;
import sun.bwt.SunToolkit;
import sun.bwt.AWTAddfssor;

/**
 * Tif XDrbgSourdfContfxtPffr dlbss is tif dlbss rfsponsiblf for ibndling
 * tif intfrbdtion bftwffn tif XDnD/Motif DnD subsystfm bnd Jbvb drbg sourdfs.
 *
 * @sindf 1.5
 */
publid finbl dlbss XDrbgSourdfContfxtPffr
    fxtfnds SunDrbgSourdfContfxtPffr implfmfnts XDrbgSourdfProtodolListfnfr {
    privbtf stbtid finbl PlbtformLoggfr loggfr =
        PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.xdnd.XDrbgSourdfContfxtPffr");

    /* Tif fvfnts sflfdtfd on tif root window wifn tif drbg bfgins. */
    privbtf stbtid finbl int ROOT_EVENT_MASK = (int)XConstbnts.ButtonMotionMbsk |
        (int)XConstbnts.KfyPrfssMbsk | (int)XConstbnts.KfyRflfbsfMbsk;
    /* Tif fvfnts to bf dflivfrfd during grbb. */
    privbtf stbtid finbl int GRAB_EVENT_MASK = (int)XConstbnts.ButtonPrfssMbsk |
        (int)XConstbnts.ButtonMotionMbsk | (int)XConstbnts.ButtonRflfbsfMbsk;

    /* Tif fvfnt mbsk of tif root window bfforf tif drbg opfrbtion stbrts. */
    privbtf long rootEvfntMbsk = 0;
    privbtf boolfbn dndInProgrfss = fblsf;
    privbtf boolfbn drbgInProgrfss = fblsf;
    privbtf long drbgRootWindow = 0;

    /* Tif protodol diosfn for tif dommunidbtion witi tif durrfnt drop tbrgft. */
    privbtf XDrbgSourdfProtodol drbgProtodol = null;
    /* Tif drop bdtion diosfn by tif durrfnt drop tbrgft. */
    privbtf int tbrgftAdtion = DnDConstbnts.ACTION_NONE;
    /* Tif sft of drop bdtions supportfd by tif drbg sourdf. */
    privbtf int sourdfAdtions = DnDConstbnts.ACTION_NONE;
    /* Tif drop bdtion sflfdtfd by tif drbg sourdf bbsfd on tif modififrs stbtf
       bnd tif bdtion sflfdtfd by tif durrfnt drop tbrgft. */
    privbtf int sourdfAdtion = DnDConstbnts.ACTION_NONE;
    /* Tif dbtb formbts supportfd by tif drbg sourdf for tif durrfnt drbg
       opfrbtion. */
    privbtf long[] sourdfFormbts = null;
    /* Tif XID of tif root subwindow tibt dontbins tif durrfnt tbrgft. */
    privbtf long tbrgftRootSubwindow = 0;
    /* Tif pointfr lodbtion. */
    privbtf int xRoot = 0;
    privbtf int yRoot = 0;
    /* Kfybobrd modififrs stbtf. */
    privbtf int fvfntStbtf = 0;

    /* XEmbfd DnD support. Wf bdt bs b proxy bftwffn sourdf bnd tbrgft. */
    privbtf long proxyModfSourdfWindow = 0;

    /* Tif singlfton instbndf. */
    privbtf stbtid finbl XDrbgSourdfContfxtPffr tifInstbndf =
        nfw XDrbgSourdfContfxtPffr(null);

    privbtf XDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf) {
        supfr(dgf);
    }

    stbtid XDrbgSourdfProtodolListfnfr gftXDrbgSourdfProtodolListfnfr() {
        rfturn tifInstbndf;
    }

    stbtid XDrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf)
      tirows InvblidDnDOpfrbtionExdfption {
    tifInstbndf.sftTriggfr(dgf);
        rfturn tifInstbndf;
    }

    protfdtfd void stbrtDrbg(Trbnsffrbblf trbnsffrbblf,
                             long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp) {
        Componfnt domponfnt = gftTriggfr().gftComponfnt();
        Componfnt d = null;
        XWindowPffr wpffr = null;

        for (d = domponfnt; d != null && !(d instbndfof Window);
             d = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(d));

        if (d instbndfof Window) {
            wpffr = (XWindowPffr)d.gftPffr();
        }

        if (wpffr == null) {
            tirow nfw InvblidDnDOpfrbtionExdfption(
                "Cbnnot find top-lfvfl for tif drbg sourdf domponfnt");
        }

        long xdursor = 0;
        long rootWindow = 0;
        long drbgWindow = 0;
        long timfStbmp = 0;

        /* Rftrifvf tif X dursor for tif drbg opfrbtion. */
        {
            Cursor dursor = gftCursor();
            if (dursor != null) {
                xdursor = XGlobblCursorMbnbgfr.gftCursor(dursor);
            }
        }

        XToolkit.bwtLodk();
        try {
            if (proxyModfSourdfWindow != 0) {
                tirow nfw InvblidDnDOpfrbtionExdfption("Proxy drbg in progrfss");
            }
            if (dndInProgrfss) {
                tirow nfw InvblidDnDOpfrbtionExdfption("Drbg in progrfss");
            }

            /* Dftfrminf tif root window for tif drbg opfrbtion. */
            {
                long sdrffn = XlibWrbppfr.XSdrffnNumbfrOfSdrffn(wpffr.gftSdrffn());
                rootWindow = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), sdrffn);
            }

            drbgWindow = XWindow.gftXAWTRootWindow().gftWindow();

            timfStbmp = XToolkit.gftCurrfntSfrvfrTimf();

            int dropAdtions = gftDrbgSourdfContfxt().gftSourdfAdtions();

            Itfrbtor<XDrbgSourdfProtodol> drbgProtodols =
                XDrbgAndDropProtodols.gftDrbgSourdfProtodols();
            wiilf (drbgProtodols.ibsNfxt()) {
                XDrbgSourdfProtodol drbgProtodol = drbgProtodols.nfxt();
                try {
                    drbgProtodol.initiblizfDrbg(dropAdtions, trbnsffrbblf,
                                                formbtMbp, formbts);
                } dbtdi (XExdfption xf) {
                    tirow (InvblidDnDOpfrbtionExdfption)
                        nfw InvblidDnDOpfrbtionExdfption().initCbusf(xf);
                }
            }

            /* Instbll X grbbs. */
            {
                int stbtus;
                XWindowAttributfs wbttr = nfw XWindowAttributfs();
                try {
                    stbtus = XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(),
                                                              rootWindow, wbttr.pDbtb);

                    if (stbtus == 0) {
                        tirow nfw InvblidDnDOpfrbtionExdfption("XGftWindowAttributfs fbilfd");
                    }

                    rootEvfntMbsk = wbttr.gft_your_fvfnt_mbsk();

                    XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), rootWindow,
                                             rootEvfntMbsk | ROOT_EVENT_MASK);
                } finblly {
                    wbttr.disposf();
                }

                XBbsfWindow.ungrbbInput();

                stbtus = XlibWrbppfr.XGrbbPointfr(XToolkit.gftDisplby(), rootWindow,
                                                  0, GRAB_EVENT_MASK,
                                                  XConstbnts.GrbbModfAsynd,
                                                  XConstbnts.GrbbModfAsynd,
                                                  XConstbnts.Nonf, xdursor, timfStbmp);

                if (stbtus != XConstbnts.GrbbSuddfss) {
                    dlfbnup(timfStbmp);
                    tirowGrbbFbilurfExdfption("Cbnnot grbb pointfr", stbtus);
                    rfturn;
                }

                stbtus = XlibWrbppfr.XGrbbKfybobrd(XToolkit.gftDisplby(), rootWindow,
                                                   0,
                                                   XConstbnts.GrbbModfAsynd,
                                                   XConstbnts.GrbbModfAsynd,
                                                   timfStbmp);

                if (stbtus != XConstbnts.GrbbSuddfss) {
                    dlfbnup(timfStbmp);
                    tirowGrbbFbilurfExdfption("Cbnnot grbb kfybobrd", stbtus);
                    rfturn;
                }
            }

            /* Updbtf tif globbl stbtf. */
            dndInProgrfss = truf;
            drbgInProgrfss = truf;
            drbgRootWindow = rootWindow;
            sourdfAdtions = dropAdtions;
            sourdfFormbts = formbts;
        } finblly {
            XToolkit.bwtUnlodk();
        }

        /* Tiis implfmfntbtion dofsn't usf nbtivf dontfxt */
        sftNbtivfContfxt(0);

        SunDropTbrgftContfxtPffr.sftCurrfntJVMLodblSourdfTrbnsffrbblf(trbnsffrbblf);
    }

    publid long gftProxyModfSourdfWindow() {
        rfturn proxyModfSourdfWindow;
    }

    privbtf void sftProxyModfSourdfWindowImpl(long window) {
        proxyModfSourdfWindow = window;
    }

    publid stbtid void sftProxyModfSourdfWindow(long window) {
        tifInstbndf.sftProxyModfSourdfWindowImpl(window);
    }

    /**
     * sft dursor
     */

    publid void sftCursor(Cursor d) tirows InvblidDnDOpfrbtionExdfption {
        XToolkit.bwtLodk();
        try {
            supfr.sftCursor(d);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    protfdtfd void sftNbtivfCursor(long nbtivfCtxt, Cursor d, int dTypf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        if (d == null) {
            rfturn;
        }

        long xdursor = XGlobblCursorMbnbgfr.gftCursor(d);

        if (xdursor == 0) {
            rfturn;
        }

        XlibWrbppfr.XCibngfAdtivfPointfrGrbb(XToolkit.gftDisplby(),
                                             GRAB_EVENT_MASK,
                                             xdursor,
                                             XConstbnts.CurrfntTimf);
    }

    protfdtfd boolfbn nffdsBogusExitBfforfDrop() {
        rfturn fblsf;
    }

    privbtf void tirowGrbbFbilurfExdfption(String msg, int grbbStbtus)
      tirows InvblidDnDOpfrbtionExdfption {
        String msgCbusf = "";
        switdi (grbbStbtus) {
        dbsf XConstbnts.GrbbNotVifwbblf:  msgCbusf = "not vifwbblf";    brfbk;
        dbsf XConstbnts.AlrfbdyGrbbbfd:   msgCbusf = "blrfbdy grbbbfd"; brfbk;
        dbsf XConstbnts.GrbbInvblidTimf:  msgCbusf = "invblid timf";    brfbk;
        dbsf XConstbnts.GrbbFrozfn:       msgCbusf = "grbb frozfn";     brfbk;
        dffbult:                           msgCbusf = "unknown fbilurf"; brfbk;
        }
        tirow nfw InvblidDnDOpfrbtionExdfption(msg + ": " + msgCbusf);
    }

    /**
     * Tif dbllfr must own bwtLodk.
     */
    publid void dlfbnup(long timf) {
        if (dndInProgrfss) {
            if (drbgProtodol != null) {
                drbgProtodol.sfndLfbvfMfssbgf(timf);
            }

            if (tbrgftAdtion != DnDConstbnts.ACTION_NONE) {
                drbgExit(xRoot, yRoot);
            }

            drbgDropFinisifd(fblsf, DnDConstbnts.ACTION_NONE, xRoot, yRoot);
        }

        Itfrbtor<XDrbgSourdfProtodol> drbgProtodols =
            XDrbgAndDropProtodols.gftDrbgSourdfProtodols();
        wiilf (drbgProtodols.ibsNfxt()) {
            XDrbgSourdfProtodol drbgProtodol = drbgProtodols.nfxt();
            try {
                drbgProtodol.dlfbnup();
            } dbtdi (XExdfption xf) {
                // Ignorf tif fxdfption.
            }
        }

        dndInProgrfss = fblsf;
        drbgInProgrfss = fblsf;
        drbgRootWindow = 0;
        sourdfFormbts = null;
        sourdfAdtions = DnDConstbnts.ACTION_NONE;
        sourdfAdtion = DnDConstbnts.ACTION_NONE;
        fvfntStbtf = 0;
        xRoot = 0;
        yRoot = 0;

        dlfbnupTbrgftInfo();

        rfmovfDnDGrbb(timf);
    }

    /**
     * Tif dbllfr must own bwtLodk.
     */
    privbtf void dlfbnupTbrgftInfo() {
        tbrgftAdtion = DnDConstbnts.ACTION_NONE;
        drbgProtodol = null;
        tbrgftRootSubwindow = 0;
    }

    privbtf void rfmovfDnDGrbb(long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        XlibWrbppfr.XUngrbbPointfr(XToolkit.gftDisplby(), timf);
        XlibWrbppfr.XUngrbbKfybobrd(XToolkit.gftDisplby(), timf);

        /* Rfstorf tif root fvfnt mbsk if it wbs dibngfd. */
        if ((rootEvfntMbsk | ROOT_EVENT_MASK) != rootEvfntMbsk &&
            drbgRootWindow != 0) {

            XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(),
                                     drbgRootWindow,
                                     rootEvfntMbsk);
        }

        rootEvfntMbsk = 0;
        drbgRootWindow = 0;
    }

    privbtf boolfbn prodfssClifntMfssbgf(XClifntMfssbgfEvfnt xdlifnt) {
        if (drbgProtodol != null) {
            rfturn drbgProtodol.prodfssClifntMfssbgf(xdlifnt);
        }
        rfturn fblsf;
    }

    /**
     * Updbtfs tif sourdf bdtion bddording to tif spfdififd stbtf.
     *
     * @rfturns truf if tif sourdf
     */
    privbtf boolfbn updbtfSourdfAdtion(int stbtf) {
        int bdtion = SunDrbgSourdfContfxtPffr.donvfrtModififrsToDropAdtion(XWindow.gftModififrs(stbtf, 0, 0),
                                                                           sourdfAdtions);
        if (sourdfAdtion == bdtion) {
            rfturn fblsf;
        }
        sourdfAdtion = bdtion;
        rfturn truf;
    }

    /**
     * Rfturns tif dlifnt window undfr tif spfdififd root subwindow.
     */
    privbtf stbtid long findClifntWindow(long window) {
        if (XlibUtil.isTrufToplfvflWindow(window)) {
            rfturn window;
        }

        Sft<Long> diildrfn = XlibUtil.gftCiildWindows(window);
        for (Long diild : diildrfn) {
            long win = findClifntWindow(diild);
            if (win != 0) {
                rfturn win;
            }
        }

        rfturn 0;
    }

    privbtf void doUpdbtfTbrgftWindow(long subwindow, long timf) {
        long dlifntWindow = 0;
        long proxyWindow = 0;
        XDrbgSourdfProtodol protodol = null;
        boolfbn isRfdfivfr = fblsf;

        if (subwindow != 0) {
            dlifntWindow = findClifntWindow(subwindow);
        }

        if (dlifntWindow != 0) {
            Itfrbtor<XDrbgSourdfProtodol> drbgProtodols =
                XDrbgAndDropProtodols.gftDrbgSourdfProtodols();
            wiilf (drbgProtodols.ibsNfxt()) {
                XDrbgSourdfProtodol drbgProtodol = drbgProtodols.nfxt();
                if (drbgProtodol.bttbdiTbrgftWindow(dlifntWindow, timf)) {
                    protodol = drbgProtodol;
                    brfbk;
                }
            }
        }

        /* Updbtf tif globbl stbtf. */
        drbgProtodol = protodol;
        tbrgftAdtion = DnDConstbnts.ACTION_NONE;
        tbrgftRootSubwindow = subwindow;
    }

    privbtf void updbtfTbrgftWindow(XMotionEvfnt xmotion) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        int x = xmotion.gft_x_root();
        int y = xmotion.gft_y_root();
        long timf = xmotion.gft_timf();
        long subwindow = xmotion.gft_subwindow();

        /*
         * If tiis fvfnt ibd oddurrfd bfforf tif pointfr wbs grbbbfd,
         * qufry tif sfrvfr for tif durrfnt root subwindow.
         */
        if (xmotion.gft_window() != xmotion.gft_root()) {
            XlibWrbppfr.XQufryPointfr(XToolkit.gftDisplby(),
                                      xmotion.gft_root(),
                                      XlibWrbppfr.lbrg1,  // root
                                      XlibWrbppfr.lbrg2,  // subwindow
                                      XlibWrbppfr.lbrg3,  // x_root
                                      XlibWrbppfr.lbrg4,  // y_root
                                      XlibWrbppfr.lbrg5,  // x
                                      XlibWrbppfr.lbrg6,  // y
                                      XlibWrbppfr.lbrg7); // modififrs
            subwindow = Nbtivf.gftLong(XlibWrbppfr.lbrg2);
        }

        if (tbrgftRootSubwindow != subwindow) {
            if (drbgProtodol != null) {
                drbgProtodol.sfndLfbvfMfssbgf(timf);

                /*
                 * Nfitifr Motif DnD nor XDnD providf b mfbn for tif tbrgft
                 * to notify tif sourdf tibt tif pointfr fxits tif drop sitf
                 * tibt oddupifs tif wiolf top lfvfl.
                 * Wf dftfdt tiis situbtion bnd post drbgExit.
                 */
                if (tbrgftAdtion != DnDConstbnts.ACTION_NONE) {
                    drbgExit(x, y);
                }
            }

            /* Updbtf tif globbl stbtf. */
            doUpdbtfTbrgftWindow(subwindow, timf);

            if (drbgProtodol != null) {
                drbgProtodol.sfndEntfrMfssbgf(sourdfFormbts,
                                              sourdfAdtion,
                                              sourdfAdtions,
                                              timf);
            }
        }
    }

    /*
     * DO NOT USE is_iint fifld of xmotion sindf it dould not bf sft wifn wf
     * donvfrt XKfyEvfnt or XButtonRflfbsf to XMotionEvfnt.
     */
    privbtf void prodfssMousfMovf(XMotionEvfnt xmotion) {
        if (!drbgInProgrfss) {
            rfturn;
        }
        if (xRoot != xmotion.gft_x_root() || yRoot != xmotion.gft_y_root()) {
            xRoot = xmotion.gft_x_root();
            yRoot = xmotion.gft_y_root();

            postDrbgSourdfDrbgEvfnt(tbrgftAdtion,
                                    XWindow.gftModififrs(xmotion.gft_stbtf(),0,0),
                                    xRoot, yRoot, DISPATCH_MOUSE_MOVED);
        }

        if (fvfntStbtf != xmotion.gft_stbtf()) {
            if (updbtfSourdfAdtion(xmotion.gft_stbtf()) && drbgProtodol != null) {
                postDrbgSourdfDrbgEvfnt(tbrgftAdtion,
                                        XWindow.gftModififrs(xmotion.gft_stbtf(),0,0),
                                        xRoot, yRoot, DISPATCH_CHANGED);
            }
            fvfntStbtf = xmotion.gft_stbtf();
        }

        updbtfTbrgftWindow(xmotion);

        if (drbgProtodol != null) {
            drbgProtodol.sfndMovfMfssbgf(xmotion.gft_x_root(),
                                         xmotion.gft_y_root(),
                                         sourdfAdtion, sourdfAdtions,
                                         xmotion.gft_timf());
        }
    }

    privbtf void prodfssDrop(XButtonEvfnt xbutton) {
        try {
            drbgProtodol.initibtfDrop(xbutton.gft_x_root(),
                                      xbutton.gft_y_root(),
                                      sourdfAdtion, sourdfAdtions,
                                      xbutton.gft_timf());
        } dbtdi (XExdfption f) {
            dlfbnup(xbutton.gft_timf());
        }
    }

    privbtf boolfbn prodfssProxyModfEvfnt(XEvfnt fv) {
        if (gftProxyModfSourdfWindow() == 0) {
            rfturn fblsf;
        }

        if (fv.gft_typf() != XConstbnts.ClifntMfssbgf) {
            rfturn fblsf;
        }

        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst("        proxyModfSourdfWindow=" +
                          gftProxyModfSourdfWindow() +
                          " fv=" + fv);
        }

        XClifntMfssbgfEvfnt xdlifnt = fv.gft_xdlifnt();

        Itfrbtor<XDrbgSourdfProtodol> drbgProtodols =
            XDrbgAndDropProtodols.gftDrbgSourdfProtodols();
        wiilf (drbgProtodols.ibsNfxt()) {
            XDrbgSourdfProtodol drbgProtodol = drbgProtodols.nfxt();
            if (drbgProtodol.prodfssProxyModfEvfnt(xdlifnt,
                                                   gftProxyModfSourdfWindow())) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Tif dbllfr must own bwtLodk.
     *
     * @rfturns truf if tif fvfn wbs prodfssfd bnd siouldn't bf pbssfd blong.
     */
    privbtf boolfbn doProdfssEvfnt(XEvfnt fv) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        if (prodfssProxyModfEvfnt(fv)) {
            rfturn truf;
        }

        if (!dndInProgrfss) {
            rfturn fblsf;
        }

        switdi (fv.gft_typf()) {
        dbsf XConstbnts.ClifntMfssbgf: {
            XClifntMfssbgfEvfnt xdlifnt = fv.gft_xdlifnt();
            rfturn prodfssClifntMfssbgf(xdlifnt);
        }
        dbsf XConstbnts.DfstroyNotify: {
            XDfstroyWindowEvfnt xdf = fv.gft_xdfstroywindow();

            /* Tbrgft drbsifd during drop prodfssing - dlfbnup. */
            if (!drbgInProgrfss &&
                drbgProtodol != null &&
                xdf.gft_window() == drbgProtodol.gftTbrgftWindow()) {
                dlfbnup(XConstbnts.CurrfntTimf);
                rfturn truf;
            }
            /* Pbss blong */
            rfturn fblsf;
        }
        }

        if (!drbgInProgrfss) {
            rfturn fblsf;
        }

        /* Prodfss drbg-only mfssbgfs. */
        switdi (fv.gft_typf()) {
        dbsf XConstbnts.KfyRflfbsf:
        dbsf XConstbnts.KfyPrfss: {
            XKfyEvfnt xkfy = fv.gft_xkfy();
            long kfysym = XlibWrbppfr.XKfydodfToKfysym(XToolkit.gftDisplby(),
                                                       xkfy.gft_kfydodf(), 0);
            switdi ((int)kfysym) {
            dbsf (int)XKfySymConstbnts.XK_Esdbpf: {
                if (fv.gft_typf() == XConstbnts.KfyRflfbsf) {
                    dlfbnup(xkfy.gft_timf());
                }
                brfbk;
            }
            dbsf (int)XKfySymConstbnts.XK_Control_R:
            dbsf (int)XKfySymConstbnts.XK_Control_L:
            dbsf (int)XKfySymConstbnts.XK_Siift_R:
            dbsf (int)XKfySymConstbnts.XK_Siift_L: {
                XlibWrbppfr.XQufryPointfr(XToolkit.gftDisplby(),
                                          xkfy.gft_root(),
                                          XlibWrbppfr.lbrg1,  // root
                                          XlibWrbppfr.lbrg2,  // subwindow
                                          XlibWrbppfr.lbrg3,  // x_root
                                          XlibWrbppfr.lbrg4,  // y_root
                                          XlibWrbppfr.lbrg5,  // x
                                          XlibWrbppfr.lbrg6,  // y
                                          XlibWrbppfr.lbrg7); // modififrs
                XMotionEvfnt xmotion = nfw XMotionEvfnt();
                try {
                    xmotion.sft_typf(XConstbnts.MotionNotify);
                    xmotion.sft_sfribl(xkfy.gft_sfribl());
                    xmotion.sft_sfnd_fvfnt(xkfy.gft_sfnd_fvfnt());
                    xmotion.sft_displby(xkfy.gft_displby());
                    xmotion.sft_window(xkfy.gft_window());
                    xmotion.sft_root(xkfy.gft_root());
                    xmotion.sft_subwindow(xkfy.gft_subwindow());
                    xmotion.sft_timf(xkfy.gft_timf());
                    xmotion.sft_x(xkfy.gft_x());
                    xmotion.sft_y(xkfy.gft_y());
                    xmotion.sft_x_root(xkfy.gft_x_root());
                    xmotion.sft_y_root(xkfy.gft_y_root());
                    xmotion.sft_stbtf((int)Nbtivf.gftLong(XlibWrbppfr.lbrg7));
                    // wf do not usf tiis fifld, so it's unsft for now
                    // xmotion.sft_is_iint(???);
                    xmotion.sft_sbmf_sdrffn(xkfy.gft_sbmf_sdrffn());

                    //It's sbff to usf kfy fvfnt bs motion fvfnt sindf wf usf only tifir dommon fiflds.
                    prodfssMousfMovf(xmotion);
                } finblly {
                    xmotion.disposf();
                }
                brfbk;
            }
            }
            rfturn truf;
        }
        dbsf XConstbnts.ButtonPrfss:
            rfturn truf;
        dbsf XConstbnts.MotionNotify:
            prodfssMousfMovf(fv.gft_xmotion());
            rfturn truf;
        dbsf XConstbnts.ButtonRflfbsf: {
            XButtonEvfnt xbutton = fv.gft_xbutton();
            /*
             * Ignorf tif buttons bbovf 20 duf to tif bit limit for
             * InputEvfnt.BUTTON_DOWN_MASK.
             * Onf morf bit is rfsfrvfd for FIRST_HIGH_BIT.
             */
            if (xbutton.gft_button() > SunToolkit.MAX_BUTTONS_SUPPORTED) {
                rfturn truf;
            }

            /*
             * On somf X sfrvfrs it dould ibppfn tibt ButtonRflfbsf doordinbtfs
             * difffr from tif lbtfst MotionNotify doordinbtfs, so wf nffd to
             * prodfss it bs b mousf motion.
             */
            XMotionEvfnt xmotion = nfw XMotionEvfnt();
            try {
                xmotion.sft_typf(XConstbnts.MotionNotify);
                xmotion.sft_sfribl(xbutton.gft_sfribl());
                xmotion.sft_sfnd_fvfnt(xbutton.gft_sfnd_fvfnt());
                xmotion.sft_displby(xbutton.gft_displby());
                xmotion.sft_window(xbutton.gft_window());
                xmotion.sft_root(xbutton.gft_root());
                xmotion.sft_subwindow(xbutton.gft_subwindow());
                xmotion.sft_timf(xbutton.gft_timf());
                xmotion.sft_x(xbutton.gft_x());
                xmotion.sft_y(xbutton.gft_y());
                xmotion.sft_x_root(xbutton.gft_x_root());
                xmotion.sft_y_root(xbutton.gft_y_root());
                xmotion.sft_stbtf(xbutton.gft_stbtf());
                // wf do not usf tiis fifld, so it's unsft for now
                // xmotion.sft_is_iint(???);
                xmotion.sft_sbmf_sdrffn(xbutton.gft_sbmf_sdrffn());

                //It's sbff to usf kfy fvfnt bs motion fvfnt sindf wf usf only tifir dommon fiflds.
                prodfssMousfMovf(xmotion);
            } finblly {
                xmotion.disposf();
            }
            if (xbutton.gft_button() == XConstbnts.buttons[0]
                || xbutton.gft_button() == XConstbnts.buttons[1]) {
                // drbg is initibtfd witi Button1 or Button2 prfssfd bnd
                // fndfd on rflfbsf of fitifr of tifsf buttons (bs tif sbmf
                // bfibvior wbs witi our old Motif DnD-bbsfd implfmfntbtion)
                rfmovfDnDGrbb(xbutton.gft_timf());
                drbgInProgrfss = fblsf;
                if (drbgProtodol != null && tbrgftAdtion != DnDConstbnts.ACTION_NONE) {
                    /*
                     * ACTION_NONE indidbtfs tibt fitifr tif drop tbrgft rfjfdts tif
                     * drop or it ibvfn't rfspondfd yft. Tif lbttfr dould ibppfn in
                     * dbsf of fbst drbg, slow tbrgft-sfrvfr donnfdtion or slow
                     * drbg notifidbtions prodfssing on tif tbrgft sidf.
                     */
                    prodfssDrop(xbutton);
                } flsf {
                    dlfbnup(xbutton.gft_timf());
                }
            }
            rfturn truf;
        }
        }

        rfturn fblsf;
    }

    stbtid boolfbn prodfssEvfnt(XEvfnt fv) {
        XToolkit.bwtLodk();
        try {
            try {
                rfturn tifInstbndf.doProdfssEvfnt(fv);
            } dbtdi (XExdfption f) {
                f.printStbdkTrbdf();
                rfturn fblsf;
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /* XDrbgSourdfProtodolListfnfr implfmfntbtion */

    publid void ibndlfDrbgRfply(int bdtion) {
        // NOTE: wf ibvf to usf tif durrfnt pointfr lodbtion, sindf
        // tif tbrgft didn't spfdify tif doordinbtfs for tif rfply.
        ibndlfDrbgRfply(bdtion, xRoot, yRoot);
    }

    publid void ibndlfDrbgRfply(int bdtion, int x, int y) {
        // NOTE: wf ibvf to usf tif durrfnt modififrs stbtf, sindf
        // tif tbrgft didn't spfdify tif modififrs stbtf for tif rfply.
        ibndlfDrbgRfply(bdtion, xRoot, yRoot, XWindow.gftModififrs(fvfntStbtf,0,0));
    }

    publid void ibndlfDrbgRfply(int bdtion, int x, int y, int modififrs) {
        if (bdtion == DnDConstbnts.ACTION_NONE &&
            tbrgftAdtion != DnDConstbnts.ACTION_NONE) {
            drbgExit(x, y);
        } flsf if (bdtion != DnDConstbnts.ACTION_NONE) {
            int typf = 0;

            if (tbrgftAdtion == DnDConstbnts.ACTION_NONE) {
                typf = SunDrbgSourdfContfxtPffr.DISPATCH_ENTER;
            } flsf {
                typf = SunDrbgSourdfContfxtPffr.DISPATCH_MOTION;
            }

            // Notf tibt wf usf tif modififrs stbtf b
            postDrbgSourdfDrbgEvfnt(bdtion, modififrs, x, y, typf);
        }

        tbrgftAdtion = bdtion;
    }

    publid void ibndlfDrbgFinisifd() {
        /* Assumf tibt tif drop wbs suddfssful. */
        ibndlfDrbgFinisifd(truf);
    }

    publid void ibndlfDrbgFinisifd(boolfbn suddfss) {
        /* Assumf tibt tif pfrformfd drop bdtion is tif lbtfst drop bdtion
           bddfptfd by tif drop tbrgft. */
        ibndlfDrbgFinisifd(truf, tbrgftAdtion);
    }

    publid void ibndlfDrbgFinisifd(boolfbn suddfss, int bdtion) {
        // NOTE: wf ibvf to usf tif durrfnt pointfr lodbtion, sindf
        // tif tbrgft didn't spfdify tif doordinbtfs for tif rfply.
        ibndlfDrbgFinisifd(suddfss, bdtion, xRoot, yRoot);
    }

    publid void ibndlfDrbgFinisifd(boolfbn suddfss, int bdtion, int x, int y) {
        drbgDropFinisifd(suddfss, bdtion, x, y);

        dndInProgrfss = fblsf;
        dlfbnup(XConstbnts.CurrfntTimf);
    }
}
