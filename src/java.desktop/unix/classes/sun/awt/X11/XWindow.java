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
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.imbgf.ColorModfl;

import jbvb.lbng.rff.WfbkRfffrfndf;

import jbvb.lbng.rfflfdt.Mftiod;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.*;

import sun.bwt.imbgf.PixflConvfrtfr;

import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;

publid dlbss XWindow fxtfnds XBbsfWindow implfmfnts X11ComponfntPffr {
    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XWindow");
    privbtf stbtid PlbtformLoggfr insLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.insfts.XWindow");
    privbtf stbtid PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fvfnt.XWindow");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fodus.XWindow");
    privbtf stbtid PlbtformLoggfr kfyEvfntLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.kyf.XWindow");
  /* If b motion domfs in wiilf b multi-dlidk is pfnding,
   * bllow b smudgf fbdtor so tibt moving tif mousf by b smbll
   * bmount dofs not wipf out tif multi-dlidk stbtf vbribblfs.
   */
    privbtf finbl stbtid int AWT_MULTICLICK_SMUDGE = 4;
    // ButtonXXX fvfnts stuff
    stbtid int rbutton = 0;
    stbtid int lbstX = 0, lbstY = 0;
    stbtid long lbstTimf = 0;
    stbtid long lbstButton = 0;
    stbtid WfbkRfffrfndf<XWindow> lbstWindowRff = null;
    stbtid int dlidkCount = 0;

    // usfd to difdk if wf nffd to rf-drfbtf surfbdfDbtb.
    int oldWidti = -1;
    int oldHfigit = -1;

    protfdtfd PropMwmHints mwm_iints;
    protfdtfd stbtid XAtom wm_protodols;
    protfdtfd stbtid XAtom wm_dflftf_window;
    protfdtfd stbtid XAtom wm_tbkf_fodus;

    privbtf boolfbn stbtfCibngfd; // Indidbtfs wiftifr tif vbluf on sbvfdStbtf is vblid
    privbtf int sbvfdStbtf; // Holds lbst known stbtf of tif top-lfvfl window

    XWindowAttributfsDbtb winAttr;

    protfdtfd X11GrbpiidsConfig grbpiidsConfig;
    protfdtfd AwtGrbpiidsConfigDbtb grbpiidsConfigDbtb;

    privbtf boolfbn rfpbrfntfd;

    XWindow pbrfnt;

    Componfnt tbrgft;

    privbtf stbtid int JAWT_LOCK_ERROR=0x00000001;
    privbtf stbtid int JAWT_LOCK_CLIP_CHANGED=0x00000002;
    privbtf stbtid int JAWT_LOCK_BOUNDS_CHANGED=0x00000004;
    privbtf stbtid int JAWT_LOCK_SURFACE_CHANGED=0x00000008;
    privbtf int drbwStbtf = JAWT_LOCK_CLIP_CHANGED |
    JAWT_LOCK_BOUNDS_CHANGED |
    JAWT_LOCK_SURFACE_CHANGED;

    publid stbtid finbl String TARGET = "tbrgft",
        REPARENTED = "rfpbrfntfd"; // wiftifr it is rfpbrfntfd by dffbult

    SurfbdfDbtb surfbdfDbtb;

    XRfpbintArfb pbintArfb;

    // fbllbbdk dffbult font objfdt
    privbtf stbtid Font dffbultFont;

    stbtid syndironizfd Font gftDffbultFont() {
        if (null == dffbultFont) {
            dffbultFont = nfw Font(Font.DIALOG, Font.PLAIN, 12);
        }
        rfturn dffbultFont;
    }

    /* A bitmbsk kffps tif button's numbfrs bs Button1Mbsk, Button2Mbsk, Button3Mbsk
     * wiidi brf bllowfd to
     * gfnfrbtf tif CLICK fvfnt bftfr tif RELEASE ibs ibppfnfd.
     * Tifrf brf donditions tibt must bf truf for tibt sfnding CLICK fvfnt:
     * 1) button wbs initiblly PRESSED
     * 2) no movfmfnt or drbg ibs ibppfnfd until RELEASE
    */
    privbtf int mousfButtonClidkAllowfd = 0;

    nbtivf int gftNbtivfColor(Color dlr, GrbpiidsConfigurbtion gd);
    nbtivf void gftWMInsfts(long window, long lfft, long top, long rigit, long bottom, long bordfr);
    nbtivf long gftTopWindow(long window, long rootWin);
    nbtivf void gftWindowBounds(long window, long x, long y, long widti, long ifigit);
    privbtf nbtivf stbtid void initIDs();

    stbtid {
        initIDs();
    }

    XWindow(XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms);
    }

    XWindow() {
    }

    XWindow(long pbrfntWindow, Rfdtbnglf bounds) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            BOUNDS, bounds,
            PARENT_WINDOW, Long.vblufOf(pbrfntWindow)}));
    }

    XWindow(Componfnt tbrgft, long pbrfntWindow, Rfdtbnglf bounds) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            BOUNDS, bounds,
            PARENT_WINDOW, Long.vblufOf(pbrfntWindow),
            TARGET, tbrgft}));
    }

    XWindow(Componfnt tbrgft, long pbrfntWindow) {
        tiis(tbrgft, pbrfntWindow, nfw Rfdtbnglf(tbrgft.gftBounds()));
    }

    XWindow(Componfnt tbrgft) {
        tiis(tbrgft, (tbrgft.gftPbrfnt() == null) ? 0 : gftPbrfntWindowID(tbrgft), nfw Rfdtbnglf(tbrgft.gftBounds()));
    }

    XWindow(Objfdt tbrgft) {
        tiis(null, 0, null);
    }

    /* Tiis drfbtf is usfd by tif XEmbfddfdFrbmfPffr sindf it ibs to drfbtf tif window
       bs b diild of tif nftsdbpf window. Tiis nftsdbpf window is pbssfd in bs wid */
    XWindow(long pbrfntWindow) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            PARENT_WINDOW, Long.vblufOf(pbrfntWindow),
            REPARENTED, Boolfbn.TRUE,
            EMBEDDED, Boolfbn.TRUE}));
    }

    protfdtfd void initGrbpiidsConfigurbtion() {
        grbpiidsConfig = (X11GrbpiidsConfig) tbrgft.gftGrbpiidsConfigurbtion();
        grbpiidsConfigDbtb = nfw AwtGrbpiidsConfigDbtb(grbpiidsConfig.gftADbtb());
    }

    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        rfpbrfntfd = Boolfbn.TRUE.fqubls(pbrbms.gft(REPARENTED));

        tbrgft = (Componfnt)pbrbms.gft(TARGET);

        initGrbpiidsConfigurbtion();

        AwtGrbpiidsConfigDbtb gDbtb = gftGrbpiidsConfigurbtionDbtb();
        X11GrbpiidsConfig donfig = (X11GrbpiidsConfig) gftGrbpiidsConfigurbtion();
        XVisublInfo visInfo = gDbtb.gft_bwt_visInfo();
        pbrbms.putIfNull(EVENT_MASK, XConstbnts.KfyPrfssMbsk | XConstbnts.KfyRflfbsfMbsk
            | XConstbnts.FodusCibngfMbsk | XConstbnts.ButtonPrfssMbsk | XConstbnts.ButtonRflfbsfMbsk
            | XConstbnts.EntfrWindowMbsk | XConstbnts.LfbvfWindowMbsk | XConstbnts.PointfrMotionMbsk
            | XConstbnts.ButtonMotionMbsk | XConstbnts.ExposurfMbsk | XConstbnts.StrudturfNotifyMbsk);

        if (tbrgft != null) {
            pbrbms.putIfNull(BOUNDS, nfw Rfdtbnglf(tbrgft.gftBounds()));
        } flsf {
            pbrbms.putIfNull(BOUNDS, nfw Rfdtbnglf(0, 0, MIN_SIZE, MIN_SIZE));
        }
        pbrbms.putIfNull(BORDER_PIXEL, Long.vblufOf(0));
        gftColorModfl(); // fix 4948833: tiis dbll fordfs tif dolor mbp to bf initiblizfd
        pbrbms.putIfNull(COLORMAP, gDbtb.gft_bwt_dmbp());
        pbrbms.putIfNull(DEPTH, gDbtb.gft_bwt_dfpti());
        pbrbms.putIfNull(VISUAL_CLASS, Intfgfr.vblufOf(XConstbnts.InputOutput));
        pbrbms.putIfNull(VISUAL, visInfo.gft_visubl());
        pbrbms.putIfNull(VALUE_MASK, XConstbnts.CWBordfrPixfl | XConstbnts.CWEvfntMbsk | XConstbnts.CWColormbp);
        Long pbrfntWindow = (Long)pbrbms.gft(PARENT_WINDOW);
        if (pbrfntWindow == null || pbrfntWindow.longVbluf() == 0) {
            XToolkit.bwtLodk();
            try {
                int sdrffn = visInfo.gft_sdrffn();
                if (sdrffn != -1) {
                    pbrbms.bdd(PARENT_WINDOW, XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), sdrffn));
                } flsf {
                    pbrbms.bdd(PARENT_WINDOW, XToolkit.gftDffbultRootWindow());
                }
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }

        pbintArfb = nfw XRfpbintArfb();
        if (tbrgft != null) {
            tiis.pbrfnt = gftPbrfntXWindowObjfdt(tbrgft.gftPbrfnt());
        }

        pbrbms.putIfNull(BACKING_STORE, XToolkit.gftBbdkingStorfTypf());

        XToolkit.bwtLodk();
        try {
            if (wm_protodols == null) {
                wm_protodols = XAtom.gft("WM_PROTOCOLS");
                wm_dflftf_window = XAtom.gft("WM_DELETE_WINDOW");
                wm_tbkf_fodus = XAtom.gft("WM_TAKE_FOCUS");
            }
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
        winAttr = nfw XWindowAttributfsDbtb();
        sbvfdStbtf = XUtilConstbnts.WitidrbwnStbtf;
    }

    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);

        sftWMClbss(gftWMClbss());

        surfbdfDbtb = grbpiidsConfig.drfbtfSurfbdfDbtb(tiis);
        Color d;
        if (tbrgft != null && (d = tbrgft.gftBbdkground()) != null) {
            // Wf nffd b vfrsion of sftBbdkground tibt dofs not dbll rfpbint !!
            // bnd onf tibt dofs not gft ovfrriddfn. Tif problfm is tibt in postInit
            // wf dbll sftBbdkground bnd wf don't ibvf bll tif stuff initiblizfd to
            // do b full pbint for most pffrs. So wf dbnnot dbll sftBbdkground in postInit.
            // instfbd wf nffd to dbll xSftBbdkground.
            xSftBbdkground(d);
        }
    }

    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
        if (grbpiidsConfig == null) {
            initGrbpiidsConfigurbtion();
        }
        rfturn grbpiidsConfig;
    }

    publid AwtGrbpiidsConfigDbtb gftGrbpiidsConfigurbtionDbtb() {
        if (grbpiidsConfigDbtb == null) {
            initGrbpiidsConfigurbtion();
        }
        rfturn grbpiidsConfigDbtb;
    }

    protfdtfd String[] gftWMClbss() {
        rfturn nfw String[] {XToolkit.gftCorrfdtXIDString(gftClbss().gftNbmf()), XToolkit.gftAWTAppClbssNbmf()};
    }

    void sftRfpbrfntfd(boolfbn nfwVbluf) {
        rfpbrfntfd = nfwVbluf;
    }

    boolfbn isRfpbrfntfd() {
        rfturn rfpbrfntfd;
    }

    stbtid long gftPbrfntWindowID(Componfnt tbrgft) {

        ComponfntPffr pffr = tbrgft.gftPbrfnt().gftPffr();
        Componfnt tfmp = tbrgft.gftPbrfnt();
        wiilf (!(pffr instbndfof XWindow))
        {
            tfmp = tfmp.gftPbrfnt();
            pffr = tfmp.gftPffr();
        }

        if (pffr != null && pffr instbndfof XWindow)
            rfturn ((XWindow)pffr).gftContfntWindow();
        flsf rfturn 0;
    }


    stbtid XWindow gftPbrfntXWindowObjfdt(Componfnt tbrgft) {
        if (tbrgft == null) rfturn null;
        Componfnt tfmp = tbrgft.gftPbrfnt();
        if (tfmp == null) rfturn null;
        ComponfntPffr pffr = tfmp.gftPffr();
        if (pffr == null) rfturn null;
        wiilf ((pffr != null) && !(pffr instbndfof XWindow))
        {
            tfmp = tfmp.gftPbrfnt();
            pffr = tfmp.gftPffr();
        }
        if (pffr != null && pffr instbndfof XWindow)
            rfturn (XWindow) pffr;
        flsf rfturn null;
    }


    boolfbn isPbrfntOf(XWindow win) {
        if (!(tbrgft instbndfof Contbinfr) || win == null || win.gftTbrgft() == null) {
            rfturn fblsf;
        }
        Contbinfr pbrfnt = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(win.tbrgft);
        wiilf (pbrfnt != null && pbrfnt != tbrgft) {
            pbrfnt = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(pbrfnt);
        }
        rfturn (pbrfnt == tbrgft);
    }

    publid Objfdt gftTbrgft() {
        rfturn tbrgft;
    }
    publid Componfnt gftEvfntSourdf() {
        rfturn tbrgft;
    }

    publid ColorModfl gftColorModfl(int trbnspbrfndy) {
        rfturn grbpiidsConfig.gftColorModfl (trbnspbrfndy);
    }

    publid ColorModfl gftColorModfl() {
        if (grbpiidsConfig != null) {
            rfturn grbpiidsConfig.gftColorModfl ();
        }
        flsf {
            rfturn XToolkit.gftStbtidColorModfl();
        }
    }

    Grbpiids gftGrbpiids(SurfbdfDbtb surfDbtb, Color bforf, Color bbbdk, Font bfont) {
        if (surfDbtb == null) rfturn null;

        Componfnt tbrgft = tiis.tbrgft;

        /* Fix for bug 4746122. Color bnd Font siouldn't bf null */
        Color bgColor = bbbdk;
        if (bgColor == null) {
            bgColor = SystfmColor.window;
        }
        Color fgColor = bforf;
        if (fgColor == null) {
            fgColor = SystfmColor.windowTfxt;
        }
        Font font = bfont;
        if (font == null) {
            font = XWindow.gftDffbultFont();
        }
        rfturn nfw SunGrbpiids2D(surfDbtb, fgColor, bgColor, font);
    }

    publid Grbpiids gftGrbpiids() {
        rfturn gftGrbpiids(surfbdfDbtb,
                           tbrgft.gftForfground(),
                           tbrgft.gftBbdkground(),
                           tbrgft.gftFont());
    }

    publid FontMftrids gftFontMftrids(Font font) {
        rfturn Toolkit.gftDffbultToolkit().gftFontMftrids(font);
    }

    publid Rfdtbnglf gftTbrgftBounds() {
        rfturn tbrgft.gftBounds();
    }

    /**
     * Rfturns truf if tif fvfnt ibs bffn ibndlfd bnd siould not bf
     * postfd to Jbvb.
     */
    boolfbn prfPostEvfnt(AWTEvfnt f) {
        rfturn fblsf;
    }

    stbtid Mftiod m_sfndMfssbgf;
    stbtid void sfndEvfnt(finbl AWTEvfnt f) {
        // Tif usfs of tiis mftiod imply tibt tif indoming fvfnt is systfm-gfnfrbtfd
        SunToolkit.sftSystfmGfnfrbtfd(f);
        PffrEvfnt pf = nfw PffrEvfnt(Toolkit.gftDffbultToolkit(), nfw Runnbblf() {
                publid void run() {
                    AWTAddfssor.gftAWTEvfntAddfssor().sftPostfd(f);
                    ((Componfnt)f.gftSourdf()).dispbtdiEvfnt(f);
                }
            }, PffrEvfnt.ULTIMATE_PRIORITY_EVENT);
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER) && (f instbndfof FodusEvfnt)) {
            fodusLog.finfr("Sfnding " + f);
        }
        XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(f.gftSourdf()), pf);
    }


/*
 * Post bn fvfnt to tif fvfnt qufuf.
 */
// NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
//       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void postEvfnt(AWTEvfnt fvfnt) {
        XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(fvfnt.gftSourdf()), fvfnt);
    }

    stbtid void postEvfntStbtid(AWTEvfnt fvfnt) {
        XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(fvfnt.gftSourdf()), fvfnt);
    }

    publid void postEvfntToEvfntQufuf(finbl AWTEvfnt fvfnt) {
        //fix for 6239938 : Cioidf drop-down dofs not disbppfbr wifn it losfs fodus, on XToolkit
        if (!prfPostEvfnt(fvfnt)) {
            //fvfnt ibsn't bffn ibndlfd bnd must bf postfd to EvfntQufuf
            postEvfnt(fvfnt);
        }
    }

    // ovfrridfn in XCbnvbsPffr
    protfdtfd boolfbn doErbsfBbdkground() {
        rfturn truf;
    }

    // Wf nffd b vfrsion of sftBbdkground tibt dofs not dbll rfpbint !!
    // bnd onf tibt dofs not gft ovfrriddfn. Tif problfm is tibt in postInit
    // wf dbll sftBbdkground bnd wf don't ibvf bll tif stuff initiblizfd to
    // do b full pbint for most pffrs. So wf dbnnot dbll sftBbdkground in postInit.
    finbl publid void xSftBbdkground(Color d) {
        XToolkit.bwtLodk();
        try {
            winBbdkground(d);
            // fix for 6558510: ibndlf sun.bwt.nofrbsfbbdkground flbg,
            // sff doErbsfBbdkground() bnd prfInit() mftiods in XCbnvbsPffr
            if (!doErbsfBbdkground()) {
                rfturn;
            }
            // 6304250: XAWT: Itfms in dioidf siow b bluf bordfr on OpfnGL + Solbris10 wifn bbdkground dolor is sft
            // Notf: Wifn OGL is fnbblfd, surfbdfDbtb.pixflFor() will not
            // rfturn b pixfl vbluf bppropribtf for pbssing to
            // XSftWindowBbdkground().  Tifrfforf, wf will usf tif ColorModfl
            // for tiis domponfnt in ordfr to dbldulbtf b pixfl vbluf from
            // tif givfn RGB vbluf.
            ColorModfl dm = gftColorModfl();
            int pixfl = PixflConvfrtfr.instbndf.rgbToPixfl(d.gftRGB(), dm);
            XlibWrbppfr.XSftWindowBbdkground(XToolkit.gftDisplby(), gftContfntWindow(), pixfl);
            XlibWrbppfr.XClfbrWindow(XToolkit.gftDisplby(), gftContfntWindow());
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void sftBbdkground(Color d) {
        xSftBbdkground(d);
    }

    Color bbdkgroundColor;
    void winBbdkground(Color d) {
        bbdkgroundColor = d;
    }

    publid Color gftWinBbdkground() {
        Color d = null;

        if (bbdkgroundColor != null) {
            d = bbdkgroundColor;
        } flsf if (pbrfnt != null) {
            d = pbrfnt.gftWinBbdkground();
        }

        if (d instbndfof SystfmColor) {
            d = nfw Color(d.gftRGB());
        }

        rfturn d;
    }

    publid boolfbn isEmbfddfd() {
        rfturn fmbfddfd;
    }

    publid finbl void rfpbint(int x, int y, int widti, int ifigit) {
        if (!isVisiblf() || gftWidti() == 0 || gftHfigit() == 0) {
            rfturn;
        }
        Grbpiids g = gftGrbpiids();
        if (g != null) {
            try {
                g.sftClip(x, y, widti, ifigit);
                if (SunToolkit.isDispbtdiTirfbdForAppContfxt(gftTbrgft())) {
                    pbint(g); // Tif nbtivf bnd tbrgft will bf pbintfd in plbdf.
                } flsf {
                    pbintPffr(g);
                    postPbintEvfnt(tbrgft, x, y, widti, ifigit);
                }
            } finblly {
                g.disposf();
            }
        }
    }

    void rfpbint() {
        rfpbint(0, 0, gftWidti(), gftHfigit());
    }

    publid void pbint(finbl Grbpiids g) {
        // pbint pffr
        pbintPffr(g);
    }

    void pbintPffr(finbl Grbpiids g) {
    }
    //usfd by Pffrs to bvoid flidkfring witiing pbint()
    protfdtfd void flusi(){
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void popup(int x, int y, int widti, int ifigit) {
        // TBD: grbb tif pointfr
        xSftBounds(x, y, widti, ifigit);
    }

    publid void ibndlfExposfEvfnt(XEvfnt xfv) {
        supfr.ibndlfExposfEvfnt(xfv);
        XExposfEvfnt xf = xfv.gft_xfxposf();
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        int x = xf.gft_x();
        int y = xf.gft_y();
        int w = xf.gft_widti();
        int i = xf.gft_ifigit();

        Componfnt tbrgft = gftEvfntSourdf();
        AWTAddfssor.ComponfntAddfssor dompAddfssor = AWTAddfssor.gftComponfntAddfssor();

        if (!dompAddfssor.gftIgnorfRfpbint(tbrgft)
            && dompAddfssor.gftWidti(tbrgft) != 0
            && dompAddfssor.gftHfigit(tbrgft) != 0)
        {
            postPbintEvfnt(tbrgft, x, y, w, i);
        }
    }

    publid void postPbintEvfnt(Componfnt tbrgft, int x, int y, int w, int i) {
        PbintEvfnt fvfnt = PbintEvfntDispbtdifr.gftPbintEvfntDispbtdifr().
            drfbtfPbintEvfnt(tbrgft, x, y, w, i);
        if (fvfnt != null) {
            postEvfntToEvfntQufuf(fvfnt);
        }
    }

    stbtid int gftModififrs(int stbtf, int button, int kfyCodf) {
        rfturn gftModififrs(stbtf, button, kfyCodf, 0,  fblsf);
    }

    stbtid int gftModififrs(int stbtf, int button, int kfyCodf, int typf, boolfbn wiffl_mousf) {
        int modififrs = 0;

        if (((stbtf & XConstbnts.SiiftMbsk) != 0) ^ (kfyCodf == KfyEvfnt.VK_SHIFT)) {
            modififrs |= InputEvfnt.SHIFT_DOWN_MASK;
        }
        if (((stbtf & XConstbnts.ControlMbsk) != 0) ^ (kfyCodf == KfyEvfnt.VK_CONTROL)) {
            modififrs |= InputEvfnt.CTRL_DOWN_MASK;
        }
        if (((stbtf & XToolkit.mftbMbsk) != 0) ^ (kfyCodf == KfyEvfnt.VK_META)) {
            modififrs |= InputEvfnt.META_DOWN_MASK;
        }
        if (((stbtf & XToolkit.bltMbsk) != 0) ^ (kfyCodf == KfyEvfnt.VK_ALT)) {
            modififrs |= InputEvfnt.ALT_DOWN_MASK;
        }
        if (((stbtf & XToolkit.modfSwitdiMbsk) != 0) ^ (kfyCodf == KfyEvfnt.VK_ALT_GRAPH)) {
            modififrs |= InputEvfnt.ALT_GRAPH_DOWN_MASK;
        }
        //InputEvfnt.BUTTON_DOWN_MASK brrby is stbrting from BUTTON1_DOWN_MASK on indfx == 0.
        // button durrfntly rfflfdts b rfbl button numbfr bnd stbrts from 1. (fxdfpt NOBUTTON wiidi is zfro )

        /* tiis is bn bttfmpt to rffbdtor button IDs in : MousfEvfnt, InputEvfnt, XlibWrbppfr bnd XWindow.*/

        //rfflfdts b button numbfr similbr to MousfEvfnt.BUTTON1, 2, 3 ftd.
        for (int i = 0; i < XConstbnts.buttons.lfngti; i ++){
            //modififr siould bf bddfd if :
            // 1) durrfnt button is now still in PRESSED stbtf (mfbns tibt usfr just prfssfd mousf but not rflfbsfd yft) or
            // 2) if Xsystfm rfports tibt "stbtf" rfprfsfnts tibt button wbs just rflfbsfd. Tiis only ibppfns on RELEASE witi 1,2,3 buttons.
            // ONLY onf of tifsf donditions siould bf TRUE to bdd tibt modififr.
            if (((stbtf & XlibUtil.gftButtonMbsk(i + 1)) != 0) != (button == XConstbnts.buttons[i])){
                //fxdludf wiffl buttons from bdding tifir numbfrs bs modififrs
                if (!wiffl_mousf) {
                    modififrs |= InputEvfnt.gftMbskForButton(i+1);
                }
            }
        }
        rfturn modififrs;
    }

    stbtid int gftXModififrs(AWTKfyStrokf strokf) {
        int mods = strokf.gftModififrs();
        int rfs = 0;
        if ((mods & (InputEvfnt.SHIFT_DOWN_MASK | InputEvfnt.SHIFT_MASK)) != 0) {
            rfs |= XConstbnts.SiiftMbsk;
        }
        if ((mods & (InputEvfnt.CTRL_DOWN_MASK | InputEvfnt.CTRL_MASK)) != 0) {
            rfs |= XConstbnts.ControlMbsk;
        }
        if ((mods & (InputEvfnt.ALT_DOWN_MASK | InputEvfnt.ALT_MASK)) != 0) {
            rfs |= XToolkit.bltMbsk;
        }
        if ((mods & (InputEvfnt.META_DOWN_MASK | InputEvfnt.META_MASK)) != 0) {
            rfs |= XToolkit.mftbMbsk;
        }
        if ((mods & (InputEvfnt.ALT_GRAPH_DOWN_MASK | InputEvfnt.ALT_GRAPH_MASK)) != 0) {
            rfs |= XToolkit.modfSwitdiMbsk;
        }
        rfturn rfs;
    }

    /**
     * Rfturns truf if tiis fvfnt is disbblfd bnd siouldn't bf pbssfd to Jbvb.
     * Dffbult implfmfntbtion rfturns fblsf for bll fvfnts.
     */
    stbtid int gftRigitButtonNumbfr() {
        if (rbutton == 0) { // not initiblizfd yft
            XToolkit.bwtLodk();
            try {
                rbutton = XlibWrbppfr.XGftPointfrMbpping(XToolkit.gftDisplby(), XlibWrbppfr.ibufffr, 3);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
        }
        rfturn rbutton;
    }

    stbtid int gftMousfMovfmfntSmudgf() {
        //TODO: It's possiblf to rfbd dorrfsponding sfttings
        rfturn AWT_MULTICLICK_SMUDGE;
    }

    publid void ibndlfButtonPrfssRflfbsf(XEvfnt xfv) {
        supfr.ibndlfButtonPrfssRflfbsf(xfv);
        XButtonEvfnt xbf = xfv.gft_xbutton();
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fvfntLog.finf(xbf.toString());
        }
        long wifn;
        int modififrs;
        boolfbn popupTriggfr = fblsf;
        int button=0;
        boolfbn wiffl_mousf = fblsf;
        int lbutton = xbf.gft_button();
        /*
         * Ignorf tif buttons bbovf 20 duf to tif bit limit for
         * InputEvfnt.BUTTON_DOWN_MASK.
         * Onf morf bit is rfsfrvfd for FIRST_HIGH_BIT.
         */
        if (lbutton > SunToolkit.MAX_BUTTONS_SUPPORTED) {
            rfturn;
        }
        int typf = xfv.gft_typf();
        wifn = xbf.gft_timf();
        long jWifn = XToolkit.nowMillisUTC_offsft(wifn);

        int x = xbf.gft_x();
        int y = xbf.gft_y();
        if (xfv.gft_xbny().gft_window() != window) {
            Point lodblXY = toLodbl(xbf.gft_x_root(), xbf.gft_y_root());
            x = lodblXY.x;
            y = lodblXY.y;
        }

        if (typf == XConstbnts.ButtonPrfss) {
            //Allow tiis mousf button to gfnfrbtf CLICK fvfnt on nfxt ButtonRflfbsf
            mousfButtonClidkAllowfd |= XlibUtil.gftButtonMbsk(lbutton);
            XWindow lbstWindow = (lbstWindowRff != null) ? (lbstWindowRff.gft()):(null);
            /*
               multidlidk difdking
            */
            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fvfntLog.finfst("lbstWindow = " + lbstWindow + ", lbstButton "
                + lbstButton + ", lbstTimf " + lbstTimf + ", multiClidkTimf "
                + XToolkit.gftMultiClidkTimf());
            }
            if (lbstWindow == tiis && lbstButton == lbutton && (wifn - lbstTimf) < XToolkit.gftMultiClidkTimf()) {
                dlidkCount++;
            } flsf {
                dlidkCount = 1;
                lbstWindowRff = nfw WfbkRfffrfndf<>(tiis);
                lbstButton = lbutton;
                lbstX = x;
                lbstY = y;
            }
            lbstTimf = wifn;


            /*
               Cifdk for popup triggfr !!
            */
            if (lbutton == gftRigitButtonNumbfr() || lbutton > 2) {
                popupTriggfr = truf;
            } flsf {
                popupTriggfr = fblsf;
            }
        }

        button = XConstbnts.buttons[lbutton - 1];
        // 4 bnd 5 buttons brf usublly donsidfrfd bssignfd to b first wiffl
        if (lbutton == XConstbnts.buttons[3] ||
            lbutton == XConstbnts.buttons[4]) {
            wiffl_mousf = truf;
        }

        // mbpping fxtrb buttons to numbfrs stbrting from 4.
        if ((button > XConstbnts.buttons[4]) && (!Toolkit.gftDffbultToolkit().brfExtrbMousfButtonsEnbblfd())){
            rfturn;
        }

        if (button > XConstbnts.buttons[4]){
            button -= 2;
        }
        modififrs = gftModififrs(xbf.gft_stbtf(),button,0, typf, wiffl_mousf);

        if (!wiffl_mousf) {
            MousfEvfnt mf = nfw MousfEvfnt(gftEvfntSourdf(),
                                           typf == XConstbnts.ButtonPrfss ? MousfEvfnt.MOUSE_PRESSED : MousfEvfnt.MOUSE_RELEASED,
                                           jWifn,modififrs, x, y,
                                           xbf.gft_x_root(),
                                           xbf.gft_y_root(),
                                           dlidkCount,popupTriggfr,button);

            postEvfntToEvfntQufuf(mf);

            if ((typf == XConstbnts.ButtonRflfbsf) &&
                ((mousfButtonClidkAllowfd & XlibUtil.gftButtonMbsk(lbutton)) != 0) ) // No up-button in tif drbg-stbtf
            {
                postEvfntToEvfntQufuf(mf = nfw MousfEvfnt(gftEvfntSourdf(),
                                                     MousfEvfnt.MOUSE_CLICKED,
                                                     jWifn,
                                                     modififrs,
                                                     x, y,
                                                     xbf.gft_x_root(),
                                                     xbf.gft_y_root(),
                                                     dlidkCount,
                                                     fblsf, button));
            }

        }
        flsf {
            if (xfv.gft_typf() == XConstbnts.ButtonPrfss) {
                MousfWifflEvfnt mwf = nfw MousfWifflEvfnt(gftEvfntSourdf(),MousfEvfnt.MOUSE_WHEEL, jWifn,
                                                          modififrs,
                                                          x, y,
                                                          xbf.gft_x_root(),
                                                          xbf.gft_y_root(),
                                                          1,fblsf,MousfWifflEvfnt.WHEEL_UNIT_SCROLL,
                                                          3,button==4 ?  -1 : 1);
                postEvfntToEvfntQufuf(mwf);
            }
        }

        /* Updbtf tif stbtf vbribblf AFTER tif CLICKED fvfnt post. */
        if (typf == XConstbnts.ButtonRflfbsf) {
            /* Exdludf tiis mousf button from bllowfd list.*/
            mousfButtonClidkAllowfd &= ~ XlibUtil.gftButtonMbsk(lbutton);
        }
    }

    publid void ibndlfMotionNotify(XEvfnt xfv) {
        supfr.ibndlfMotionNotify(xfv);
        XMotionEvfnt xmf = xfv.gft_xmotion();
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }

        int mousfKfyStbtf = 0; //(xmf.gft_stbtf() & (XConstbnts.buttonsMbsk[0] | XConstbnts.buttonsMbsk[1] | XConstbnts.buttonsMbsk[2]));

        //tiis dofsn't work for fxtrb buttons bfdbusf Xsystfm is sfnding stbtf==0 for fvfry fxtrb button fvfnt.
        // wf dbn't dorrfdt it in MousfEvfnt dlbss bs wf donf it witi modififrs, bfdbusf fxbdt typf (DRAG|MOVE)
        // siould bf pbssfd from XWindow.
        finbl int buttonsNumbfr = XToolkit.gftNumbfrOfButtonsForMbsk();

        for (int i = 0; i < buttonsNumbfr; i++){
            // TODO : ifrf is tif bug in WM: fxtrb buttons dofsn't ibvf stbtf!=0 bs tify siould.
            if ((i != 4) && (i != 5)) {
                mousfKfyStbtf = mousfKfyStbtf | (xmf.gft_stbtf() & XlibUtil.gftButtonMbsk(i + 1));
            }
        }

        boolfbn isDrbgging = (mousfKfyStbtf != 0);
        int mousfEvfntTypf = 0;

        if (isDrbgging) {
            mousfEvfntTypf = MousfEvfnt.MOUSE_DRAGGED;
        } flsf {
            mousfEvfntTypf = MousfEvfnt.MOUSE_MOVED;
        }

        /*
           Fix for 6176814 .  Add multidlidk difdking.
        */
        int x = xmf.gft_x();
        int y = xmf.gft_y();
        XWindow lbstWindow = (lbstWindowRff != null) ? (lbstWindowRff.gft()):(null);

        if (!(lbstWindow == tiis &&
              (xmf.gft_timf() - lbstTimf) < XToolkit.gftMultiClidkTimf()  &&
              (Mbti.bbs(lbstX - x) < AWT_MULTICLICK_SMUDGE &&
               Mbti.bbs(lbstY - y) < AWT_MULTICLICK_SMUDGE))) {
          dlidkCount = 0;
          lbstWindowRff = null;
          mousfButtonClidkAllowfd = 0;
          lbstTimf = 0;
          lbstX = 0;
          lbstY = 0;
        }

        long jWifn = XToolkit.nowMillisUTC_offsft(xmf.gft_timf());
        int modififrs = gftModififrs(xmf.gft_stbtf(), 0, 0);
        boolfbn popupTriggfr = fblsf;

        Componfnt sourdf = gftEvfntSourdf();

        if (xmf.gft_window() != window) {
            Point lodblXY = toLodbl(xmf.gft_x_root(), xmf.gft_y_root());
            x = lodblXY.x;
            y = lodblXY.y;
        }
        /* Fix for 5039416.
         * Addording to dbnvbs.d wf siouldn't post bny MousfEvfnt if mousf is drbgging bnd dlidkCount!=0.
         */
        if ((isDrbgging && dlidkCount == 0) || !isDrbgging) {
            MousfEvfnt mmf = nfw MousfEvfnt(sourdf, mousfEvfntTypf, jWifn,
                                            modififrs, x, y, xmf.gft_x_root(), xmf.gft_y_root(),
                                            dlidkCount, popupTriggfr, MousfEvfnt.NOBUTTON);
            postEvfntToEvfntQufuf(mmf);
        }
    }


    // REMIND: nffd to implfmfnt looking for disbblfd fvfnts
    publid nbtivf boolfbn x11inputMftiodLookupString(long fvfnt, long [] kfysymArrby);
    nbtivf boolfbn ibvfCurrfntX11InputMftiodInstbndf();

    privbtf boolfbn mousfAbovfMf;

    publid boolfbn isMousfAbovf() {
        syndironizfd (gftStbtfLodk()) {
            rfturn mousfAbovfMf;
        }
    }
    protfdtfd void sftMousfAbovf(boolfbn bbovf) {
        syndironizfd (gftStbtfLodk()) {
            mousfAbovfMf = bbovf;
        }
    }

    protfdtfd void fntfrNotify(long window) {
        if (window == gftWindow()) {
            sftMousfAbovf(truf);
        }
    }
    protfdtfd void lfbvfNotify(long window) {
        if (window == gftWindow()) {
            sftMousfAbovf(fblsf);
        }
    }

    publid void ibndlfXCrossingEvfnt(XEvfnt xfv) {
        supfr.ibndlfXCrossingEvfnt(xfv);
        XCrossingEvfnt xdf = xfv.gft_xdrossing();

        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fvfntLog.finfst(xdf.toString());
        }

        if (xdf.gft_typf() == XConstbnts.EntfrNotify) {
            fntfrNotify(xdf.gft_window());
        } flsf { // LfbvfNotify:
            lfbvfNotify(xdf.gft_window());
        }

        // Skip fvfnt If it wbs dbusfd by b grbb
        // Tiis is nffdfd bfdbusf on displbys witi fodus-follows-mousf on MousfPrfss X systfm gfnfrbtfs
        // two XCrossing fvfnts witi modf != NormblNotify. First of tifm notififs tibt tif mousf ibs lfft
        // durrfnt domponfnt. Sfdond onf notififs tibt it ibs fntfrfd into tif sbmf domponfnt.
        // Tiis looks likf tif window undfr tif mousf ibs bdtublly dibngfd bnd Jbvb ibndlf tifsf  fvfnts
        // bddordingly. Tiis lfbds to impossibility to mbkf b doublf dlidk on Componfnt (6404708)
        XWindowPffr toplfvfl = gftToplfvflXWindow();
        if (toplfvfl != null && !toplfvfl.isModblBlodkfd()){
            if (xdf.gft_modf() != XConstbnts.NotifyNormbl) {
                // 6404708 : nffd updbtf dursor in bddordbndf witi skipping Lfbvf/EntfrNotify fvfnt
                // wifrfbs it dofsn't nffd to ibndlfd furtifr.
                if (xdf.gft_typf() == XConstbnts.EntfrNotify) {
                    XAwtStbtf.sftComponfntMousfEntfrfd(gftEvfntSourdf());
                    XGlobblCursorMbnbgfr.nbtivfUpdbtfCursor(gftEvfntSourdf());
                } flsf { // LfbvfNotify:
                    XAwtStbtf.sftComponfntMousfEntfrfd(null);
                }
                rfturn;
            }
        }
        // X sfnds XCrossing to bll iifrbrdiy so if tif fdgf of diild fqubls to
        // bndfstor bnd mousf fntfrs diild, tif bndfstor will gft bn fvfnt too.
        // From jbvb point tif fvfnt is bogus bs bndfstor is obsdurfd, so if
        // tif diild dbn gft jbvb fvfnt itsflf, wf skip it on bndfstor.
        long diildWnd = xdf.gft_subwindow();
        if (diildWnd != XConstbnts.Nonf) {
            XBbsfWindow diild = XToolkit.windowToXWindow(diildWnd);
            if (diild != null && diild instbndfof XWindow &&
                !diild.isEvfntDisbblfd(xfv))
            {
                rfturn;
            }
        }

        // Rfmfmbfr old domponfnt witi mousf to ibvf tif opportunity to sfnd it MOUSE_EXITED.
        finbl Componfnt dompWitiMousf = XAwtStbtf.gftComponfntMousfEntfrfd();
        if (toplfvfl != null) {
            if(!toplfvfl.isModblBlodkfd()){
                if (xdf.gft_typf() == XConstbnts.EntfrNotify) {
                    // Cibngf XAwtStbtf's domponfnt mousf fntfrfd to tif up-to-dbtf onf bfforf rfqufsting
                    // to updbtf tif dursor sindf XAwtStbtf.gftComponfntMousfEntfrfd() is usfd wifn tif
                    // dursor is updbtfd (in XGlobblCursorMbnbgfr.findHfbvywfigitUndfrCursor()).
                    XAwtStbtf.sftComponfntMousfEntfrfd(gftEvfntSourdf());
                    XGlobblCursorMbnbgfr.nbtivfUpdbtfCursor(gftEvfntSourdf());
                } flsf { // LfbvfNotify:
                    XAwtStbtf.sftComponfntMousfEntfrfd(null);
                }
            } flsf {
                ((XComponfntPffr) AWTAddfssor.gftComponfntAddfssor().gftPffr(tbrgft))
                    .pSftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
            }
        }

        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }

        long jWifn = XToolkit.nowMillisUTC_offsft(xdf.gft_timf());
        int modififrs = gftModififrs(xdf.gft_stbtf(),0,0);
        int dlidkCount = 0;
        boolfbn popupTriggfr = fblsf;
        int x = xdf.gft_x();
        int y = xdf.gft_y();
        if (xdf.gft_window() != window) {
            Point lodblXY = toLodbl(xdf.gft_x_root(), xdf.gft_y_root());
            x = lodblXY.x;
            y = lodblXY.y;
        }

        // Tiis dodf trbdks boundbry drossing bnd fnsurfs MOUSE_ENTER/EXIT
        // brf postfd in bltfrnbtf pbirs
        if (dompWitiMousf != null) {
            MousfEvfnt mf = nfw MousfEvfnt(dompWitiMousf,
                MousfEvfnt.MOUSE_EXITED, jWifn, modififrs, xdf.gft_x(),
                xdf.gft_y(), xdf.gft_x_root(), xdf.gft_y_root(), dlidkCount, popupTriggfr,
                MousfEvfnt.NOBUTTON);
            postEvfntToEvfntQufuf(mf);
            fvfntLog.finfst("Clfbring lbst window rff");
            lbstWindowRff = null;
        }
        if (xdf.gft_typf() == XConstbnts.EntfrNotify) {
            MousfEvfnt mf = nfw MousfEvfnt(gftEvfntSourdf(), MousfEvfnt.MOUSE_ENTERED,
                jWifn, modififrs, xdf.gft_x(), xdf.gft_y(), xdf.gft_x_root(), xdf.gft_y_root(), dlidkCount,
                popupTriggfr, MousfEvfnt.NOBUTTON);
            postEvfntToEvfntQufuf(mf);
        }
    }

    publid void doLbyout(int x, int y, int widti, int ifigit) {}

    publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv) {
        Rfdtbnglf oldBounds = gftBounds();

        supfr.ibndlfConfigurfNotifyEvfnt(xfv);
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            insLog.finfr("Configurf, {0}, fvfnt disbblfd: {1}",
                     xfv.gft_xdonfigurf(), isEvfntDisbblfd(xfv));
        }
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }

//  if ( Cifdk if it's b rfsizf, b movf, or b stbdking ordfr dibngf )
//  {
        Rfdtbnglf bounds = gftBounds();
        if (!bounds.gftSizf().fqubls(oldBounds.gftSizf())) {
            postEvfntToEvfntQufuf(nfw ComponfntEvfnt(gftEvfntSourdf(), ComponfntEvfnt.COMPONENT_RESIZED));
        }
        if (!bounds.gftLodbtion().fqubls(oldBounds.gftLodbtion())) {
            postEvfntToEvfntQufuf(nfw ComponfntEvfnt(gftEvfntSourdf(), ComponfntEvfnt.COMPONENT_MOVED));
        }
//  }
    }

    publid void ibndlfMbpNotifyEvfnt(XEvfnt xfv) {
        supfr.ibndlfMbpNotifyEvfnt(xfv);
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Mbppfd {0}", tiis);
        }
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        ComponfntEvfnt df;

        df = nfw ComponfntEvfnt(gftEvfntSourdf(), ComponfntEvfnt.COMPONENT_SHOWN);
        postEvfntToEvfntQufuf(df);
    }

    publid void ibndlfUnmbpNotifyEvfnt(XEvfnt xfv) {
        supfr.ibndlfUnmbpNotifyEvfnt(xfv);
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        ComponfntEvfnt df;

        df = nfw ComponfntEvfnt(tbrgft, ComponfntEvfnt.COMPONENT_HIDDEN);
        postEvfntToEvfntQufuf(df);
    }

    privbtf void dumpKfysymArrby(XKfyEvfnt fv) {
        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            kfyEvfntLog.finf("  "+Long.toHfxString(XlibWrbppfr.XKfydodfToKfysym(XToolkit.gftDisplby(), fv.gft_kfydodf(), 0))+
                             "\n        "+Long.toHfxString(XlibWrbppfr.XKfydodfToKfysym(XToolkit.gftDisplby(), fv.gft_kfydodf(), 1))+
                             "\n        "+Long.toHfxString(XlibWrbppfr.XKfydodfToKfysym(XToolkit.gftDisplby(), fv.gft_kfydodf(), 2))+
                             "\n        "+Long.toHfxString(XlibWrbppfr.XKfydodfToKfysym(XToolkit.gftDisplby(), fv.gft_kfydodf(), 3)));
        }
    }
    /**
       Rfturn unidodf dibrbdtfr or 0 if no dorrfspondfnt dibrbdtfr found.
       Pbrbmftfr is b kfysym bbsidblly from kfysymdff.i
       XXX: iow bbout vfndor kfys? Is tifrf somf witi Unidodf vbluf bnd not in tif list?
    */
    int kfysymToUnidodf( long kfysym, int stbtf ) {
        rfturn XKfysym.donvfrtKfysym( kfysym, stbtf );
    }
    int kfyEvfntTypf2Id( int xEvfntTypf ) {
        rfturn xEvfntTypf == XConstbnts.KfyPrfss ? jbvb.bwt.fvfnt.KfyEvfnt.KEY_PRESSED :
               xEvfntTypf == XConstbnts.KfyRflfbsf ? jbvb.bwt.fvfnt.KfyEvfnt.KEY_RELEASED : 0;
    }
    stbtid privbtf long xkfydodfToKfysym(XKfyEvfnt fv) {
        rfturn XKfysym.gftKfysym( fv );
    }
    privbtf long xkfydodfToPrimbryKfysym(XKfyEvfnt fv) {
        rfturn XKfysym.xkfydodf2primbry_kfysym( fv );
    }
    stbtid privbtf int primbryUnidodf2JbvbKfydodf(int uni) {
        rfturn (uni > 0? sun.bwt.ExtfndfdKfyCodfs.gftExtfndfdKfyCodfForCibr(uni) : 0);
        //rfturn (uni > 0? uni + 0x01000000 : 0);
    }
    void logIndomingKfyEvfnt(XKfyEvfnt fv) {
        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            kfyEvfntLog.finf("--XWindow.jbvb:ibndlfKfyEvfnt:"+fv);
        }
        dumpKfysymArrby(fv);
        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            kfyEvfntLog.finf("XXXXXXXXXXXXXX jbvbkfydodf will bf most probbbly:0x"+ Intfgfr.toHfxString(XKfysym.gftJbvbKfydodfOnly(fv)));
        }
    }
    publid void ibndlfKfyPrfss(XEvfnt xfv) {
        supfr.ibndlfKfyPrfss(xfv);
        XKfyEvfnt fv = xfv.gft_xkfy();
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fvfntLog.finf(fv.toString());
        }
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        ibndlfKfyPrfss(fv);
    }
    // dbllfd dirfdtly from tiis pbdkbgf, unlikf ibndlfKfyRflfbsf.
    // un-finbl it if you nffd to ovfrridf it in b subdlbss.
    finbl void ibndlfKfyPrfss(XKfyEvfnt fv) {
        long kfysym[] = nfw long[2];
        int unidodfKfy = 0;
        kfysym[0] = XConstbnts.NoSymbol;

        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            logIndomingKfyEvfnt( fv );
        }
        if ( //TODO difdk if tifrf's bn bdtivf input mftiod instbndf
             // witiout dblling b nbtivf mftiod. Is it nfdfssbry tiougi?
            ibvfCurrfntX11InputMftiodInstbndf()) {
            if (x11inputMftiodLookupString(fv.pDbtb, kfysym)) {
                if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    kfyEvfntLog.finf("--XWindow.jbvb XIM did prodfss fvfnt; rfturn; dfd kfysym prodfssfd:"+(kfysym[0])+
                                   "; ifx kfysym prodfssfd:"+Long.toHfxString(kfysym[0])
                                   );
                }
                rfturn;
            }flsf {
                unidodfKfy = kfysymToUnidodf( kfysym[0], fv.gft_stbtf() );
                if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    kfyEvfntLog.finf("--XWindow.jbvb XIM did NOT prodfss fvfnt, ifx kfysym:"+Long.toHfxString(kfysym[0])+"\n"+
                                     "                                         unidodf kfy:"+Intfgfr.toHfxString(unidodfKfy));
                }
            }
        }flsf  {
            // No input mftiod instbndf found. For fxbmplf, tifrf's b Jbvb Input Mftiod.
            // Produdf do-it-yoursflf kfysym bnd pfribps unidodf dibrbdtfr.
            kfysym[0] = xkfydodfToKfysym(fv);
            unidodfKfy = kfysymToUnidodf( kfysym[0], fv.gft_stbtf() );
            if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                kfyEvfntLog.finf("--XWindow.jbvb XIM is bbsfnt;             ifx kfysym:"+Long.toHfxString(kfysym[0])+"\n"+
                                 "                                         unidodf kfy:"+Intfgfr.toHfxString(unidodfKfy));
            }
        }
        // Kfysym siould bf donvfrtfd to Unidodf, if possiblf bnd nfdfssbry,
        // bnd Jbvb KfyEvfnt kfydodf siould bf dbldulbtfd.
        // For prfss wf siould post prfssfd & typfd Jbvb fvfnts.
        //
        // Prfss fvfnt migit bf not prodfssfd to tiis timf bfdbusf
        //  (1) fitifr XIM dould not ibndlf it or
        //  (2) it wbs Lbtin 1:1 mbpping.
        //
        // Prfsfrvf modififrs to gft Jbvb kfy dodf for dfbd kfys
        boolfbn isDfbdKfy = isDfbdKfy(kfysym[0]);
        XKfysym.Kfysym2JbvbKfydodf jkd = isDfbdKfy ? XKfysym.gftJbvbKfydodf(kfysym[0])
                : XKfysym.gftJbvbKfydodf(fv);
        if( jkd == null ) {
            jkd = nfw XKfysym.Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_UNKNOWN);
        }

        // Tbkf tif first kfysym from b kfysym brrby bssodibtfd witi tif XKfyfvfnt
        // bnd donvfrt it to Unidodf. Tifn, fvfn if b Jbvb kfydodf for tif kfystrokf
        // is undffinfd, wf still ibvf b gufss of wibt ibs bffn fngrbvfd on b kfytop.
        int unidodfFromPrimbryKfysym = kfysymToUnidodf( xkfydodfToPrimbryKfysym(fv) ,0);

        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            kfyEvfntLog.finf(">>>Firf Evfnt:"+
               (fv.gft_typf() == XConstbnts.KfyPrfss ? "KEY_PRESSED; " : "KEY_RELEASED; ")+
               "jkfydodf:dfdimbl="+jkd.gftJbvbKfydodf()+
               ", ifx=0x"+Intfgfr.toHfxString(jkd.gftJbvbKfydodf())+"; "+
               " lfgbdy jkfydodf: dfdimbl="+XKfysym.gftLfgbdyJbvbKfydodfOnly(fv)+
               ", ifx=0x"+Intfgfr.toHfxString(XKfysym.gftLfgbdyJbvbKfydodfOnly(fv))+"; "
            );
        }

        int jkfyToRfturn = XKfysym.gftLfgbdyJbvbKfydodfOnly(fv); // somfwby bbdkwbrd dompbtiblf
        int jkfyExtfndfd = jkd.gftJbvbKfydodf() == jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED ?
                           primbryUnidodf2JbvbKfydodf( unidodfFromPrimbryKfysym ) :
                             jkd.gftJbvbKfydodf();
        postKfyEvfnt( jbvb.bwt.fvfnt.KfyEvfnt.KEY_PRESSED,
                          fv.gft_timf(),
                          isDfbdKfy ? jkfyExtfndfd : jkfyToRfturn,
                          (unidodfKfy == 0 ? jbvb.bwt.fvfnt.KfyEvfnt.CHAR_UNDEFINED : unidodfKfy),
                          jkd.gftKfyLodbtion(),
                          fv.gft_stbtf(),fv.gftPDbtb(), XKfyEvfnt.gftSizf(), (long)(fv.gft_kfydodf()),
                          unidodfFromPrimbryKfysym,
                          jkfyExtfndfd);


        if (unidodfKfy > 0 && !isDfbdKfy) {
                if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    kfyEvfntLog.finf("firf _TYPED on "+unidodfKfy);
                }
                postKfyEvfnt( jbvb.bwt.fvfnt.KfyEvfnt.KEY_TYPED,
                              fv.gft_timf(),
                              jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED,
                              unidodfKfy,
                              jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_UNKNOWN,
                              fv.gft_stbtf(),fv.gftPDbtb(), XKfyEvfnt.gftSizf(), (long)0,
                              unidodfFromPrimbryKfysym,
                              jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED);

        }


    }

    publid void ibndlfKfyRflfbsf(XEvfnt xfv) {
        supfr.ibndlfKfyRflfbsf(xfv);
        XKfyEvfnt fv = xfv.gft_xkfy();
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fvfntLog.finf(fv.toString());
        }
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        ibndlfKfyRflfbsf(fv);
    }
    // un-privbtf it if you nffd to dbll it from flsfwifrf
    privbtf void ibndlfKfyRflfbsf(XKfyEvfnt fv) {
        int unidodfKfy = 0;

        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            logIndomingKfyEvfnt( fv );
        }
        // Kfysym siould bf donvfrtfd to Unidodf, if possiblf bnd nfdfssbry,
        // bnd Jbvb KfyEvfnt kfydodf siould bf dbldulbtfd.
        // For rflfbsf wf siould post rflfbsfd fvfnt.
        //
        // Prfsfrvf modififrs to gft Jbvb kfy dodf for dfbd kfys
        long kfysym = xkfydodfToKfysym(fv);
        boolfbn isDfbdKfy = isDfbdKfy(kfysym);
        XKfysym.Kfysym2JbvbKfydodf jkd = isDfbdKfy ? XKfysym.gftJbvbKfydodf(kfysym)
                : XKfysym.gftJbvbKfydodf(fv);
        if( jkd == null ) {
            jkd = nfw XKfysym.Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_UNKNOWN);
        }
        if (kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            kfyEvfntLog.finf(">>>Firf Evfnt:"+
               (fv.gft_typf() == XConstbnts.KfyPrfss ? "KEY_PRESSED; " : "KEY_RELEASED; ")+
               "jkfydodf:dfdimbl="+jkd.gftJbvbKfydodf()+
               ", ifx=0x"+Intfgfr.toHfxString(jkd.gftJbvbKfydodf())+"; "+
               " lfgbdy jkfydodf: dfdimbl="+XKfysym.gftLfgbdyJbvbKfydodfOnly(fv)+
               ", ifx=0x"+Intfgfr.toHfxString(XKfysym.gftLfgbdyJbvbKfydodfOnly(fv))+"; "
            );
        }
        // Wf obtbin kfysym from IM bnd dfrivf unidodfKfy from it for KfyPrfss only.
        // Wf usfd to dbdif tibt vbluf bnd rftrifvf it on KfyRflfbsf,
        // but in dbsf for fxbmplf of b dfbd kfy+vowfl pbir, b vowfl bftfr b dfbdkfy
        // migit nfvfr bf dbdifd bfforf.
        // Also, switdiing bftwffn kfybobrd lbyouts, wf migit dbdif b wrong lfttfr.
        // Tibt's wiy wf usf tif sbmf prodfdurf bs if tifrf wbs no IM instbndf: do-it-yoursflf unidodf.
        unidodfKfy = kfysymToUnidodf( xkfydodfToKfysym(fv), fv.gft_stbtf() );

        // Tbkf b first kfysym from b kfysym brrby bssodibtfd witi tif XKfyfvfnt
        // bnd donvfrt it to Unidodf. Tifn, fvfn if Jbvb kfydodf for tif kfystrokf
        // is undffinfd, wf still will ibvf b gufss of wibt wbs fngrbvfd on b kfytop.
        int unidodfFromPrimbryKfysym = kfysymToUnidodf( xkfydodfToPrimbryKfysym(fv) ,0);

        int jkfyToRfturn = XKfysym.gftLfgbdyJbvbKfydodfOnly(fv); // somfwby bbdkwbrd dompbtiblf
        int jkfyExtfndfd = jkd.gftJbvbKfydodf() == jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED ?
                           primbryUnidodf2JbvbKfydodf( unidodfFromPrimbryKfysym ) :
                             jkd.gftJbvbKfydodf();
        postKfyEvfnt(  jbvb.bwt.fvfnt.KfyEvfnt.KEY_RELEASED,
                          fv.gft_timf(),
                          isDfbdKfy ? jkfyExtfndfd : jkfyToRfturn,
                          (unidodfKfy == 0 ? jbvb.bwt.fvfnt.KfyEvfnt.CHAR_UNDEFINED : unidodfKfy),
                          jkd.gftKfyLodbtion(),
                          fv.gft_stbtf(),fv.gftPDbtb(), XKfyEvfnt.gftSizf(), (long)(fv.gft_kfydodf()),
                          unidodfFromPrimbryKfysym,
                          jkfyExtfndfd);


    }


    privbtf boolfbn isDfbdKfy(long kfysym){
        rfturn XKfySymConstbnts.XK_dfbd_grbvf <= kfysym && kfysym <= XKfySymConstbnts.XK_dfbd_sfmivoidfd_sound;
    }

    /*
     * XmNidonid bnd Mbp/UnmbpNotify (tibt XmNidonid rflifs on) brf
     * unrflibblf, sindf mbpping dibngfs dbn ibppfn for b virtubl dfsktop
     * switdi or MbdOS stylf sibding tibt bfdbmf quitf populbr undfr X bs
     * wfll.  Yfs, it probbbly siould not bf tiis wby, bs it violbtfs
     * ICCCM, but rfblity is tibt quitf b lot of window mbnbgfrs bbusf
     * mbpping stbtf.
     */
    int gftWMStbtf() {
        if (stbtfCibngfd) {
            stbtfCibngfd = fblsf;
            WindowPropfrtyGfttfr gfttfr =
                nfw WindowPropfrtyGfttfr(window, XWM.XA_WM_STATE, 0, 1, fblsf,
                                         XWM.XA_WM_STATE);
            try {
                int stbtus = gfttfr.fxfdutf();
                if (stbtus != XConstbnts.Suddfss || gfttfr.gftDbtb() == 0) {
                    rfturn sbvfdStbtf = XUtilConstbnts.WitidrbwnStbtf;
                }

                if (gfttfr.gftAdtublTypf() != XWM.XA_WM_STATE.gftAtom() && gfttfr.gftAdtublFormbt() != 32) {
                    rfturn sbvfdStbtf = XUtilConstbnts.WitidrbwnStbtf;
                }
                sbvfdStbtf = (int)Nbtivf.gftCbrd32(gfttfr.gftDbtb());
            } finblly {
                gfttfr.disposf();
            }
        }
        rfturn sbvfdStbtf;
    }

    /**
     * Ovfrridf tiis mftiods to gft notifidbtions wifn top-lfvfl window stbtf dibngfs. Tif stbtf is
     * mfbnt in tfrms of ICCCM: WitidrbwnStbtf, IdonidStbtf, NormblStbtf
     */
    protfdtfd void stbtfCibngfd(long timf, int oldStbtf, int nfwStbtf) {
    }

    @Ovfrridf
    publid void ibndlfPropfrtyNotify(XEvfnt xfv) {
        supfr.ibndlfPropfrtyNotify(xfv);
        XPropfrtyEvfnt fv = xfv.gft_xpropfrty();
        if (fv.gft_btom() == XWM.XA_WM_STATE.gftAtom()) {
            // Stbtf ibs dibngfd, invblidbtf sbvfd vbluf
            stbtfCibngfd = truf;
            stbtfCibngfd(fv.gft_timf(), sbvfdStbtf, gftWMStbtf());
        }
    }

    publid void rfsibpf(Rfdtbnglf bounds) {
        rfsibpf(bounds.x, bounds.y, bounds.widti, bounds.ifigit);
    }

    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        if (widti <= 0) {
            widti = 1;
        }
        if (ifigit <= 0) {
            ifigit = 1;
        }
        tiis.x = x;
        tiis.y = y;
        tiis.widti = widti;
        tiis.ifigit = ifigit;
        xSftBounds(x, y, widti, ifigit);
        // Fixfd 6322593, 6304251, 6315137:
        // XWindow's SurfbdfDbtb siould bf invblidbtfd bnd rfdrfbtfd bs pbrt
        // of tif prodfss of rfsizing tif window
        // sff tif fvblubtion of tif bug 6304251 for morf informbtion
        vblidbtfSurfbdf();
        lbyout();
    }

    publid void lbyout() {}

    boolfbn isSiowing() {
        rfturn visiblf;
    }

    boolfbn isRfsizbblf() {
        rfturn truf;
    }

    boolfbn isLodbtionByPlbtform() {
        rfturn fblsf;
    }

    void updbtfSizfHints() {
        updbtfSizfHints(x, y, widti, ifigit);
    }

    void updbtfSizfHints(int x, int y, int widti, int ifigit) {
        long flbgs = XUtilConstbnts.PSizf | (isLodbtionByPlbtform() ? 0 : (XUtilConstbnts.PPosition | XUtilConstbnts.USPosition));
        if (!isRfsizbblf()) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Window {0} is not rfsizbblf", tiis);
            }
            flbgs |= XUtilConstbnts.PMinSizf | XUtilConstbnts.PMbxSizf;
        } flsf {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Window {0} is rfsizbblf", tiis);
            }
        }
        sftSizfHints(flbgs, x, y, widti, ifigit);
    }

    void updbtfSizfHints(int x, int y) {
        long flbgs = isLodbtionByPlbtform() ? 0 : (XUtilConstbnts.PPosition | XUtilConstbnts.USPosition);
        if (!isRfsizbblf()) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Window {0} is not rfsizbblf", tiis);
            }
            flbgs |= XUtilConstbnts.PMinSizf | XUtilConstbnts.PMbxSizf | XUtilConstbnts.PSizf;
        } flsf {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Window {0} is rfsizbblf", tiis);
            }
        }
        sftSizfHints(flbgs, x, y, widti, ifigit);
    }

    void vblidbtfSurfbdf() {
        if ((widti != oldWidti) || (ifigit != oldHfigit)) {
            doVblidbtfSurfbdf();

            oldWidti = widti;
            oldHfigit = ifigit;
        }
    }

    finbl void doVblidbtfSurfbdf() {
        SurfbdfDbtb oldDbtb = surfbdfDbtb;
        if (oldDbtb != null) {
            surfbdfDbtb = grbpiidsConfig.drfbtfSurfbdfDbtb(tiis);
            oldDbtb.invblidbtf();
        }
    }

    publid SurfbdfDbtb gftSurfbdfDbtb() {
        rfturn surfbdfDbtb;
    }

    publid void disposf() {
        SurfbdfDbtb oldDbtb = surfbdfDbtb;
        surfbdfDbtb = null;
        if (oldDbtb != null) {
            oldDbtb.invblidbtf();
        }
        XToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
        dfstroy();
    }

    publid Point gftLodbtionOnSdrffn() {
        syndironizfd (tbrgft.gftTrffLodk()) {
            Componfnt domp = tbrgft;

            wiilf (domp != null && !(domp instbndfof Window)) {
                domp = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(domp);
            }

            // bpplfts, fmbfddfd, ftd - trbnslbtf dirfdtly
            // XXX: ovfrridf in subdlbss?
            if (domp == null || domp instbndfof sun.bwt.EmbfddfdFrbmf) {
                rfturn toGlobbl(0, 0);
            }

            XToolkit.bwtLodk();
            try {
                Objfdt wpffr = XToolkit.tbrgftToPffr(domp);
                if (wpffr == null
                    || !(wpffr instbndfof XDfdorbtfdPffr)
                    || ((XDfdorbtfdPffr)wpffr).donfigurf_sffn)
                {
                    rfturn toGlobbl(0, 0);
                }

                // wpffr is bn XDfdorbtfdPffr not yft fully bdoptfd by WM
                Point pt = toOtifrWindow(gftContfntWindow(),
                                         ((XDfdorbtfdPffr)wpffr).gftContfntWindow(),
                                         0, 0);

                if (pt == null) {
                    pt = nfw Point(((XBbsfWindow)wpffr).gftAbsolutfX(), ((XBbsfWindow)wpffr).gftAbsolutfY());
                }
                pt.x += domp.gftX();
                pt.y += domp.gftY();
                rfturn pt;
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
    }


    stbtid void sftBDbtb(KfyEvfnt f, bytf[] dbtb) {
        AWTAddfssor.gftAWTEvfntAddfssor().sftBDbtb(f, dbtb);
    }

    publid void postKfyEvfnt(int id, long wifn, int kfyCodf, int kfyCibr,
        int kfyLodbtion, int stbtf, long fvfnt, int fvfntSizf, long rbwCodf,
        int unidodfFromPrimbryKfysym, int fxtfndfdKfyCodf)

    {
        long jWifn = XToolkit.nowMillisUTC_offsft(wifn);
        int modififrs = gftModififrs(stbtf, 0, kfyCodf);

        KfyEvfnt kf = nfw KfyEvfnt(gftEvfntSourdf(), id, jWifn,
                                   modififrs, kfyCodf, (dibr)kfyCibr, kfyLodbtion);
        if (fvfnt != 0) {
            bytf[] dbtb = Nbtivf.toBytfs(fvfnt, fvfntSizf);
            sftBDbtb(kf, dbtb);
        }

        AWTAddfssor.KfyEvfntAddfssor kfb = AWTAddfssor.gftKfyEvfntAddfssor();
        kfb.sftRbwCodf(kf, rbwCodf);
        kfb.sftPrimbryLfvflUnidodf(kf, (long)unidodfFromPrimbryKfysym);
        kfb.sftExtfndfdKfyCodf(kf, (long)fxtfndfdKfyCodf);
        postEvfntToEvfntQufuf(kf);
    }

    stbtid nbtivf int gftAWTKfyCodfForKfySym(int kfysym);
    stbtid nbtivf int gftKfySymForAWTKfyCodf(int kfydodf);

    /* Tifsf two mftiods brf bdtublly bpplidbblf to toplfvfl windows only.
     * Howfvfr, tif fundtionblity is rfquirfd by boti tif XWindowPffr bnd
     * XWbrningWindow, boti of wiidi ibvf tif XWindow bs b dommon bndfstor.
     * Sff XWM.sftMotifDfdor() for dftbils.
     */
    publid PropMwmHints gftMWMHints() {
        if (mwm_iints == null) {
            mwm_iints = nfw PropMwmHints();
            if (!XWM.XA_MWM_HINTS.gftAtomDbtb(gftWindow(), mwm_iints.pDbtb, MWMConstbnts.PROP_MWM_HINTS_ELEMENTS)) {
                mwm_iints.zfro();
            }
        }
        rfturn mwm_iints;
    }

    publid void sftMWMHints(PropMwmHints iints) {
        mwm_iints = iints;
        if (iints != null) {
            XWM.XA_MWM_HINTS.sftAtomDbtb(gftWindow(), mwm_iints.pDbtb, MWMConstbnts.PROP_MWM_HINTS_ELEMENTS);
        }
    }

    protfdtfd finbl void initWMProtodols() {
        wm_protodols.sftAtomListPropfrty(tiis, gftWMProtodols());
    }

    /**
     * Rfturns list of protodols wiidi siould bf instbllfd on tiis window.
     * Dfsdfndbnts dbn ovfrridf tiis mftiod to bdd dlbss-spfdifid protodols
     */
    protfdtfd XAtomList gftWMProtodols() {
        // No protodols on simplf window
        rfturn nfw XAtomList();
    }

    /**
     * Indidbtfs if tif window is durrfntly in tif FSEM.
     * Syndironizbtion: stbtf lodk.
     */
    privbtf boolfbn fullSdrffnExdlusivfModfStbtf = fblsf;

    // Implfmfntbtion of tif X11ComponfntPffr
    @Ovfrridf
    publid void sftFullSdrffnExdlusivfModfStbtf(boolfbn stbtf) {
        syndironizfd (gftStbtfLodk()) {
            fullSdrffnExdlusivfModfStbtf = stbtf;
        }
    }

    publid finbl boolfbn isFullSdrffnExdlusivfModf() {
        syndironizfd (gftStbtfLodk()) {
            rfturn fullSdrffnExdlusivfModfStbtf;
        }
    }

}
