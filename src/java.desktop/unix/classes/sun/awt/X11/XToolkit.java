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
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.dbtbtrbnsffr.Clipbobrd;
import jbvb.bwt.dnd.DrbgSourdf;
import jbvb.bwt.dnd.DrbgGfsturfListfnfr;
import jbvb.bwt.dnd.DrbgGfsturfEvfnt;
import jbvb.bwt.dnd.DrbgGfsturfRfdognizfr;
import jbvb.bwt.dnd.MousfDrbgGfsturfRfdognizfr;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;
import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.bwt.im.spi.InputMftiodDfsdriptor;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.pffr.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import jbvbx.swing.LookAndFffl;
import jbvbx.swing.UIDffbults;
import sun.bwt.*;
import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.font.FontConfigMbnbgfr;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.misd.*;
import sun.bwt.util.TirfbdGroupUtils;
import sun.print.PrintJob2D;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;
import sun.util.logging.PlbtformLoggfr;

publid finbl dlbss XToolkit fxtfnds UNIXToolkit implfmfnts Runnbblf {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XToolkit");
    privbtf stbtid finbl PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fvfnt.XToolkit");
    privbtf stbtid finbl PlbtformLoggfr timfoutTbskLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.timfoutTbsk.XToolkit");
    privbtf stbtid finbl PlbtformLoggfr kfyEvfntLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.kyf.XToolkit");
    privbtf stbtid finbl PlbtformLoggfr bbdkingStorfLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.bbdkingStorf.XToolkit");

    //Tifrf is 400 ms is sft by dffbult on Windows bnd 500 by dffbult on KDE bnd GNOME.
    //Wf usf tif sbmf ibrddodfd donstbnt.
    privbtf finbl stbtid int AWT_MULTICLICK_DEFAULT_TIME = 500;

    stbtid finbl boolfbn PRIMARY_LOOP = fblsf;
    stbtid finbl boolfbn SECONDARY_LOOP = truf;

    privbtf stbtid String bwtAppClbssNbmf = null;

    // tif systfm dlipbobrd - CLIPBOARD sflfdtion
    XClipbobrd dlipbobrd;
    // tif systfm sflfdtion - PRIMARY sflfdtion
    XClipbobrd sflfdtion;

    // Dynbmid Lbyout Rfsizf dlifnt dodf sftting
    protfdtfd stbtid boolfbn dynbmidLbyoutSftting = fblsf;

    //Is it bllowfd to gfnfrbtf fvfnts bssignfd to fxtrb mousf buttons.
    //Sft to truf by dffbult.
    privbtf stbtid boolfbn brfExtrbMousfButtonsEnbblfd = truf;

    /**
     * Truf wifn tif x sfttings ibvf bffn lobdfd.
     */
    privbtf boolfbn lobdfdXSfttings;

    /**
    * XSETTINGS for tif dffbult sdrffn.
     * <p>
     */
    privbtf XSfttings xs;

    privbtf FontConfigMbnbgfr fdMbnbgfr = nfw FontConfigMbnbgfr();

    stbtid int brrowCursor;
    stbtid TrffMbp<Long, XBbsfWindow> winMbp = nfw TrffMbp<>();
    stbtid HbsiMbp<Objfdt, Objfdt> spfdiblPffrMbp = nfw HbsiMbp<>();
    stbtid HbsiMbp<Long, Collfdtion<XEvfntDispbtdifr>> winToDispbtdifr = nfw HbsiMbp<>();
    privbtf stbtid long _displby;
    stbtid UIDffbults uidffbults;
    stbtid X11GrbpiidsEnvironmfnt lodblEnv;
    stbtid X11GrbpiidsDfvidf dfvidf;
    stbtid finbl X11GrbpiidsConfig donfig;
    stbtid int bwt_multidlidk_timf;
    stbtid boolfbn sfdurityWbrningEnbblfd;

    privbtf stbtid volbtilf int sdrffnWidti = -1, sdrffnHfigit = -1; // Dimfnsions of dffbult sdrffn
    stbtid long bwt_dffbultFg; // Pixfl
    privbtf stbtid XMousfInfoPffr xPffr;

    stbtid {
        initSfdurityWbrning();
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            donfig = null;
        } flsf {
            lodblEnv = (X11GrbpiidsEnvironmfnt) GrbpiidsEnvironmfnt
                .gftLodblGrbpiidsEnvironmfnt();
            dfvidf = (X11GrbpiidsDfvidf) lodblEnv.gftDffbultSdrffnDfvidf();
            donfig = (X11GrbpiidsConfig) (dfvidf.gftDffbultConfigurbtion());
            if (dfvidf != null) {
                _displby = dfvidf.gftDisplby();
            }
            sftupModififrMbp();
            initIDs();
            sftBbdkingStorfTypf();
        }
    }

    /*
     * Rfturn (potfntiblly) plbtform spfdifid displby timfout for tif
     * trby idon
     */
    stbtid nbtivf long gftTrbyIdonDisplbyTimfout();

    privbtf nbtivf stbtid void initIDs();
    nbtivf stbtid void wbitForEvfnts(long nfxtTbskTimf);
    stbtid Tirfbd toolkitTirfbd;
    stbtid boolfbn isToolkitTirfbd() {
        rfturn Tirfbd.durrfntTirfbd() == toolkitTirfbd;
    }

    stbtid void initSfdurityWbrning() {
        // Enbblf wbrning only for intfrnbl builds
        String runtimf = AddfssControllfr.doPrivilfgfd(
                             nfw GftPropfrtyAdtion("jbvb.runtimf.vfrsion"));
        sfdurityWbrningEnbblfd = (runtimf != null && runtimf.dontbins("intfrnbl"));
    }

    stbtid boolfbn isSfdurityWbrningEnbblfd() {
        rfturn sfdurityWbrningEnbblfd;
    }

    stbtid nbtivf void bwt_output_flusi();

    stbtid finbl void  bwtFUnlodk() {
        bwtUnlodk();
        bwt_output_flusi();
    }


    publid nbtivf void nbtivfLobdSystfmColors(int[] systfmColors);

    stbtid UIDffbults gftUIDffbults() {
        if (uidffbults == null) {
            initUIDffbults();
        }
        rfturn uidffbults;
    }

    publid void lobdSystfmColors(int[] systfmColors) {
        nbtivfLobdSystfmColors(systfmColors);
        MotifColorUtilitifs.lobdSystfmColors(systfmColors);
    }



    stbtid void initUIDffbults() {
        try {
            // Lobd Dffbults from MotifLookAndFffl

            // Tiis dummy lobd is nfdfssbry to gft SystfmColor initiblizfd. !!!!!!
            Color d = SystfmColor.tfxt;

            LookAndFffl lnf = nfw XAWTLookAndFffl();
            uidffbults = lnf.gftDffbults();
        }
        dbtdi (Exdfption f)
        {
            f.printStbdkTrbdf();
        }
    }

    stbtid Objfdt displbyLodk = nfw Objfdt();

    publid stbtid long gftDisplby() {
        rfturn _displby;
    }

    publid stbtid long gftDffbultRootWindow() {
        bwtLodk();
        try {
            long rfs = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(),
                XlibWrbppfr.DffbultSdrffn(XToolkit.gftDisplby()));

            if (rfs == 0) {
               tirow nfw IllfgblStbtfExdfption("Root window must not bf null");
            }
            rfturn rfs;
        } finblly {
            bwtUnlodk();
        }
    }

    void init() {
        bwtLodk();
        try {
            XlibWrbppfr.XSupportsLodblf();
            if (XlibWrbppfr.XSftLodblfModififrs("") == null) {
                log.finfr("X lodblf modififrs brf not supportfd, using dffbult");
            }
            tryXKB();

            AwtSdrffnDbtb dffbultSdrffn = nfw AwtSdrffnDbtb(XToolkit.gftDffbultSdrffnDbtb());
            bwt_dffbultFg = dffbultSdrffn.gft_blbdkpixfl();

            brrowCursor = XlibWrbppfr.XCrfbtfFontCursor(XToolkit.gftDisplby(),
                XCursorFontConstbnts.XC_brrow);
            brfExtrbMousfButtonsEnbblfd = Boolfbn.pbrsfBoolfbn(Systfm.gftPropfrty("sun.bwt.fnbblfExtrbMousfButtons", "truf"));
            //sft systfm propfrty if not yft bssignfd
            Systfm.sftPropfrty("sun.bwt.fnbblfExtrbMousfButtons", ""+brfExtrbMousfButtonsEnbblfd);

            // Dftfdt displby modf dibngfs
            XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), XToolkit.gftDffbultRootWindow(), XConstbnts.StrudturfNotifyMbsk);
            XToolkit.bddEvfntDispbtdifr(XToolkit.gftDffbultRootWindow(), nfw XEvfntDispbtdifr() {
                @Ovfrridf
                publid void dispbtdiEvfnt(XEvfnt fv) {
                    if (fv.gft_typf() == XConstbnts.ConfigurfNotify) {
                        bwtUnlodk();
                        try {
                            ((X11GrbpiidsEnvironmfnt)GrbpiidsEnvironmfnt.
                             gftLodblGrbpiidsEnvironmfnt()).
                                displbyCibngfd();
                        } finblly {
                            bwtLodk();
                        }
                    }
                }
            });
        } finblly {
            bwtUnlodk();
        }
        PrivilfgfdAdtion<Void> b = () -> {
            Tirfbd siutdownTirfbd = nfw Tirfbd(TirfbdGroupUtils.gftRootTirfbdGroup(), "XToolkt-Siutdown-Tirfbd") {
                    publid void run() {
                        XSystfmTrbyPffr pffr = XSystfmTrbyPffr.gftPffrInstbndf();
                        if (pffr != null) {
                            pffr.disposf();
                        }
                        if (xs != null) {
                            ((XAWTXSfttings)xs).disposf();
                        }
                        frffXKB();
                        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            dumpPffrs();
                        }
                    }
                };
            siutdownTirfbd.sftContfxtClbssLobdfr(null);
            Runtimf.gftRuntimf().bddSiutdownHook(siutdownTirfbd);
            rfturn null;
        };
        AddfssControllfr.doPrivilfgfd(b);
    }

    stbtid String gftCorrfdtXIDString(String vbl) {
        if (vbl != null) {
            rfturn vbl.rfplbdf('.', '-');
        } flsf {
            rfturn vbl;
        }
    }

    stbtid nbtivf String gftEnv(String kfy);


    stbtid String gftAWTAppClbssNbmf() {
        rfturn bwtAppClbssNbmf;
    }

    publid XToolkit() {
        supfr();
        if (PfrformbndfLoggfr.loggingEnbblfd()) {
            PfrformbndfLoggfr.sftTimf("XToolkit donstrudtion");
        }

        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            String mbinClbssNbmf = null;

            StbdkTrbdfElfmfnt trbdf[] = (nfw Tirowbblf()).gftStbdkTrbdf();
            int bottom = trbdf.lfngti - 1;
            if (bottom >= 0) {
                mbinClbssNbmf = trbdf[bottom].gftClbssNbmf();
            }
            if (mbinClbssNbmf == null || mbinClbssNbmf.fqubls("")) {
                mbinClbssNbmf = "AWT";
            }
            bwtAppClbssNbmf = gftCorrfdtXIDString(mbinClbssNbmf);

            init();
            XWM.init();

            toolkitTirfbd = AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Tirfbd>) () -> {
                Tirfbd tirfbd = nfw Tirfbd(TirfbdGroupUtils.gftRootTirfbdGroup(), XToolkit.tiis, "AWT-XAWT");
                tirfbd.sftContfxtClbssLobdfr(null);
                tirfbd.sftPriority(Tirfbd.NORM_PRIORITY + 1);
                tirfbd.sftDbfmon(truf);
                rfturn tirfbd;
            });
            toolkitTirfbd.stbrt();
        }
    }

    publid ButtonPffr drfbtfButton(Button tbrgft) {
        ButtonPffr pffr = nfw XButtonPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid FrbmfPffr drfbtfLigitwfigitFrbmf(LigitwfigitFrbmf tbrgft) {
        FrbmfPffr pffr = nfw XLigitwfigitFrbmfPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid FrbmfPffr drfbtfFrbmf(Frbmf tbrgft) {
        FrbmfPffr pffr = nfw XFrbmfPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    stbtid void bddToWinMbp(long window, XBbsfWindow xwin)
    {
        syndironizfd(winMbp) {
            winMbp.put(Long.vblufOf(window),xwin);
        }
    }

    stbtid void rfmovfFromWinMbp(long window, XBbsfWindow xwin) {
        syndironizfd(winMbp) {
            winMbp.rfmovf(Long.vblufOf(window));
        }
    }
    stbtid XBbsfWindow windowToXWindow(long window) {
        syndironizfd(winMbp) {
            rfturn winMbp.gft(Long.vblufOf(window));
        }
    }

    stbtid void bddEvfntDispbtdifr(long window, XEvfntDispbtdifr dispbtdifr) {
        syndironizfd(winToDispbtdifr) {
            Long kfy = Long.vblufOf(window);
            Collfdtion<XEvfntDispbtdifr> dispbtdifrs = winToDispbtdifr.gft(kfy);
            if (dispbtdifrs == null) {
                dispbtdifrs = nfw Vfdtor<>();
                winToDispbtdifr.put(kfy, dispbtdifrs);
            }
            dispbtdifrs.bdd(dispbtdifr);
        }
    }
    stbtid void rfmovfEvfntDispbtdifr(long window, XEvfntDispbtdifr dispbtdifr) {
        syndironizfd(winToDispbtdifr) {
            Long kfy = Long.vblufOf(window);
            Collfdtion<XEvfntDispbtdifr> dispbtdifrs = winToDispbtdifr.gft(kfy);
            if (dispbtdifrs != null) {
                dispbtdifrs.rfmovf(dispbtdifr);
            }
        }
    }

    privbtf Point lbstCursorPos;

    /**
     * Rfturns wiftifr tifrf is lbst rfmfmbfrfd dursor position.  Tif
     * position is rfmfmbfrfd from X mousf fvfnts on our pffrs.  Tif
     * position is storfd in <dodf>p</dodf>.
     * @rfturn truf, if tifrf is rfmfmbfrfd lbst dursor position,
     * fblsf otifrwisf
     */
    boolfbn gftLbstCursorPos(Point p) {
        bwtLodk();
        try {
            if (lbstCursorPos == null) {
                rfturn fblsf;
            }
            p.sftLodbtion(lbstCursorPos);
            rfturn truf;
        } finblly {
            bwtUnlodk();
        }
    }

    privbtf void prodfssGlobblMotionEvfnt(XEvfnt f) {
        // Only our windows gubrbntffly gfnfrbtf MotionNotify, so wf
        // siould trbdk fntfr/lfbvf, to dbtdi tif momfnt wifn to
        // switdi to XQufryPointfr
        if (f.gft_typf() == XConstbnts.MotionNotify) {
            XMotionEvfnt fv = f.gft_xmotion();
            bwtLodk();
            try {
                if (lbstCursorPos == null) {
                    lbstCursorPos = nfw Point(fv.gft_x_root(), fv.gft_y_root());
                } flsf {
                    lbstCursorPos.sftLodbtion(fv.gft_x_root(), fv.gft_y_root());
                }
            } finblly {
                bwtUnlodk();
            }
        } flsf if (f.gft_typf() == XConstbnts.LfbvfNotify) {
            // Lfbvf from our window
            bwtLodk();
            try {
                lbstCursorPos = null;
            } finblly {
                bwtUnlodk();
            }
        } flsf if (f.gft_typf() == XConstbnts.EntfrNotify) {
            // Entrbndf into our window
            XCrossingEvfnt fv = f.gft_xdrossing();
            bwtLodk();
            try {
                if (lbstCursorPos == null) {
                    lbstCursorPos = nfw Point(fv.gft_x_root(), fv.gft_y_root());
                } flsf {
                    lbstCursorPos.sftLodbtion(fv.gft_x_root(), fv.gft_y_root());
                }
            } finblly {
                bwtUnlodk();
            }
        }
    }

    publid intfrfbdf XEvfntListfnfr {
        publid void fvfntProdfssfd(XEvfnt f);
    }

    privbtf Collfdtion<XEvfntListfnfr> listfnfrs = nfw LinkfdList<XEvfntListfnfr>();

    publid void bddXEvfntListfnfr(XEvfntListfnfr listfnfr) {
        syndironizfd (listfnfrs) {
            listfnfrs.bdd(listfnfr);
        }
    }

    privbtf void notifyListfnfrs(XEvfnt xfv) {
        syndironizfd (listfnfrs) {
            if (listfnfrs.sizf() == 0) rfturn;

            XEvfnt dopy = xfv.dlonf();
            try {
                for (XEvfntListfnfr listfnfr : listfnfrs) {
                    listfnfr.fvfntProdfssfd(dopy);
                }
            } finblly {
                dopy.disposf();
            }
        }
    }

    privbtf void dispbtdiEvfnt(XEvfnt fv) {
        finbl XAnyEvfnt xbny = fv.gft_xbny();

        if (windowToXWindow(xbny.gft_window()) != null &&
             (fv.gft_typf() == XConstbnts.MotionNotify || fv.gft_typf() == XConstbnts.EntfrNotify || fv.gft_typf() == XConstbnts.LfbvfNotify))
        {
            prodfssGlobblMotionEvfnt(fv);
        }

        if( fv.gft_typf() == XConstbnts.MbppingNotify ) {
            // Tif 'window' fifld in tiis fvfnt is unusfd.
            // Tiis bpplidbtion itsflf dofs notiing to initibtf sudi bn fvfnt
            // (no dblls of XCibngfKfybobrdMbpping ftd.).
            // SunRby sfrvfr sfnds tiis fvfnt to tif bpplidbtion ondf on fvfry
            // kfybobrd (not just lbyout) dibngf wiidi mfbns, quitf sfldom.
            XlibWrbppfr.XRffrfsiKfybobrdMbpping(fv.pDbtb);
            rfsftKfybobrdSnifffr();
            sftupModififrMbp();
        }
        XBbsfWindow.dispbtdiToWindow(fv);

        Collfdtion<XEvfntDispbtdifr> dispbtdifrs = null;
        syndironizfd(winToDispbtdifr) {
            Long kfy = Long.vblufOf(xbny.gft_window());
            dispbtdifrs = winToDispbtdifr.gft(kfy);
            if (dispbtdifrs != null) { // Clonf it to bvoid syndironizbtion during dispbtdiing
                dispbtdifrs = nfw Vfdtor<>(dispbtdifrs);
            }
        }
        if (dispbtdifrs != null) {
            Itfrbtor<XEvfntDispbtdifr> itfr = dispbtdifrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                XEvfntDispbtdifr disp = itfr.nfxt();
                disp.dispbtdiEvfnt(fv);
            }
        }
        notifyListfnfrs(fv);
    }

    stbtid void prodfssExdfption(Tirowbblf tir) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.WARNING)) {
            log.wbrning("Exdfption on Toolkit tirfbd", tir);
        }
    }

    stbtid nbtivf void bwt_toolkit_init();

    publid void run() {
        bwt_toolkit_init();
        run(PRIMARY_LOOP);
    }

    publid void run(boolfbn loop)
    {
        XEvfnt fv = nfw XEvfnt();
        wiilf(truf) {
            // Fix for 6829923: wf siould grbdffully ibndlf toolkit tirfbd intfrruption
            if (Tirfbd.durrfntTirfbd().isIntfrruptfd()) {
                // Wf fxpfdt intfrruption from tif AppContfxt.disposf() mftiod only.
                // If tif tirfbd is intfrruptfd from bnotifr plbdf, lft's skip it
                // for dompbtibility rfbsons. Probbbly somf timf lbtfr wf'll rfmovf
                // tif difdk for AppContfxt.isDisposfd() bnd will undonditionblly
                // brfbk tif loop ifrf.
                if (AppContfxt.gftAppContfxt().isDisposfd()) {
                    brfbk;
                }
            }
            bwtLodk();
            try {
                if (loop == SECONDARY_LOOP) {
                    // In tif sfdondbry loop wf mby ibvf blrfbdy bdquirfd bwt_lodk
                    // sfvfrbl timfs, so wbitForEvfnts() migit bf unbblf to rflfbsf
                    // tif bwt_lodk bnd tiis dbusfs lodk up.
                    // For now, wf just bvoid wbitForEvfnts in tif sfdondbry loop.
                    if (!XlibWrbppfr.XNfxtSfdondbryLoopEvfnt(gftDisplby(),fv.pDbtb)) {
                        brfbk;
                    }
                } flsf {
                    dbllTimfoutTbsks();
                    // If no fvfnts brf qufufd, wbitForEvfnts() dbusfs dblls to
                    // bwtUnlodk(), bwtJNI_TirfbdYifld, poll, bwtLodk(),
                    // so it spfnds most of its timf in poll, witiout iolding tif lodk.
                    wiilf ((XlibWrbppfr.XEvfntsQufufd(gftDisplby(), XConstbnts.QufufdAftfrRfbding) == 0) &&
                           (XlibWrbppfr.XEvfntsQufufd(gftDisplby(), XConstbnts.QufufdAftfrFlusi) == 0)) {
                        dbllTimfoutTbsks();
                        wbitForEvfnts(gftNfxtTbskTimf());
                    }
                    XlibWrbppfr.XNfxtEvfnt(gftDisplby(),fv.pDbtb);
                }

                if (fv.gft_typf() != XConstbnts.NoExposf) {
                    fvfntNumbfr++;
                }
                if (bwt_UsfXKB_Cblls && fv.gft_typf() ==  bwt_XKBBbsfEvfntCodf) {
                    prodfssXkbCibngfs(fv);
                }

                if (XDropTbrgftEvfntProdfssor.prodfssEvfnt(fv) ||
                    XDrbgSourdfContfxtPffr.prodfssEvfnt(fv)) {
                    dontinuf;
                }

                if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    fvfntLog.finfr("{0}", fv);
                }

                // Cifdk if input mftiod donsumfs tif fvfnt
                long w = 0;
                if (windowToXWindow(fv.gft_xbny().gft_window()) != null) {
                    Componfnt ownfr =
                        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusOwnfr();
                    if (ownfr != null) {
                        XWindow ownfrWindow = (XWindow) AWTAddfssor.gftComponfntAddfssor().gftPffr(ownfr);
                        if (ownfrWindow != null) {
                            w = ownfrWindow.gftContfntWindow();
                        }
                    }
                }
                if( kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE) && (fv.gft_typf() == XConstbnts.KfyPrfss || fv.gft_typf() == XConstbnts.KfyRflfbsf) ) {
                    kfyEvfntLog.finf("bfforf XFiltfrEvfnt:"+fv);
                }
                if (XlibWrbppfr.XFiltfrEvfnt(fv.gftPDbtb(), w)) {
                    dontinuf;
                }
                if( kfyEvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE) && (fv.gft_typf() == XConstbnts.KfyPrfss || fv.gft_typf() == XConstbnts.KfyRflfbsf) ) {
                    kfyEvfntLog.finf("bftfr XFiltfrEvfnt:"+fv); // IS THIS CORRECT?
                }

                dispbtdiEvfnt(fv);
            } dbtdi (TirfbdDfbti td) {
                XBbsfWindow.ungrbbInput();
                rfturn;
            } dbtdi (Tirowbblf tir) {
                XBbsfWindow.ungrbbInput();
                prodfssExdfption(tir);
            } finblly {
                bwtUnlodk();
            }
        }
    }

    stbtid {
        GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        if (gf instbndfof SunGrbpiidsEnvironmfnt) {
            ((SunGrbpiidsEnvironmfnt)gf).bddDisplbyCibngfdListfnfr(
                nfw DisplbyCibngfdListfnfr() {
                    @Ovfrridf
                    publid void displbyCibngfd() {
                        // 7045370: Rfsft tif dbdifd vblufs
                        XToolkit.sdrffnWidti = -1;
                        XToolkit.sdrffnHfigit = -1;
                    }

                    @Ovfrridf
                    publid void pblfttfCibngfd() {}
            });
        }
    }

    privbtf stbtid void initSdrffnSizf() {
        if (sdrffnWidti == -1 || sdrffnHfigit == -1) {
            bwtLodk();
            try {
                XWindowAttributfs pbttr = nfw XWindowAttributfs();
                try {
                    XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(), XToolkit.gftDffbultRootWindow(), pbttr.pDbtb);
                    sdrffnWidti  = pbttr.gft_widti();
                    sdrffnHfigit = pbttr.gft_ifigit();
                } finblly {
                    pbttr.disposf();
                }
            } finblly {
                bwtUnlodk();
            }
        }
    }

    stbtid int gftDffbultSdrffnWidti() {
        initSdrffnSizf();
        rfturn sdrffnWidti;
    }

    stbtid int gftDffbultSdrffnHfigit() {
        initSdrffnSizf();
        rfturn sdrffnHfigit;
    }

    protfdtfd int gftSdrffnWidti() {
        rfturn gftDffbultSdrffnWidti();
    }

    protfdtfd int gftSdrffnHfigit() {
        rfturn gftDffbultSdrffnHfigit();
    }

    privbtf stbtid Rfdtbnglf gftWorkArfb(long root)
    {
        XAtom XA_NET_WORKAREA = XAtom.gft("_NET_WORKAREA");

        long nbtivf_ptr = Nbtivf.bllodbtfLongArrby(4);
        try
        {
            boolfbn workbrfbPrfsfnt = XA_NET_WORKAREA.gftAtomDbtb(root,
                XAtom.XA_CARDINAL, nbtivf_ptr, 4);
            if (workbrfbPrfsfnt)
            {
                int rootX = (int)Nbtivf.gftLong(nbtivf_ptr, 0);
                int rootY = (int)Nbtivf.gftLong(nbtivf_ptr, 1);
                int rootWidti = (int)Nbtivf.gftLong(nbtivf_ptr, 2);
                int rootHfigit = (int)Nbtivf.gftLong(nbtivf_ptr, 3);

                rfturn nfw Rfdtbnglf(rootX, rootY, rootWidti, rootHfigit);
            }
        }
        finblly
        {
            XlibWrbppfr.unsbff.frffMfmory(nbtivf_ptr);
        }

        rfturn null;
    }

    /*
     * If wf'rf running in non-Xinfrbmb fnvironmfnt bnd tif durrfnt
     * window mbnbgfr supports _NET protodol tifn tif sdrffn insfts
     * brf dbldulbtfd using _NET_WM_WORKAREA propfrty of tif root
     * window.
     * Otifrwisf, i. f. if Xinfrbmb is on or _NET_WM_WORKAREA is
     * not sft, wf try to dbldulbtf tif insfts oursflvfs using
     * gftSdrffnInsftsMbnublly mftiod.
     */
    publid Insfts gftSdrffnInsfts(GrbpiidsConfigurbtion gd)
    {
        XNETProtodol nftProto = XWM.gftWM().gftNETProtodol();
        if ((nftProto == null) || !nftProto.bdtivf())
        {
            rfturn supfr.gftSdrffnInsfts(gd);
        }

        XToolkit.bwtLodk();
        try
        {
            X11GrbpiidsConfig x11gd = (X11GrbpiidsConfig)gd;
            X11GrbpiidsDfvidf x11gd = (X11GrbpiidsDfvidf)x11gd.gftDfvidf();
            long root = XlibUtil.gftRootWindow(x11gd.gftSdrffn());
            Rfdtbnglf rootBounds = XlibUtil.gftWindowGfomftry(root);

            X11GrbpiidsEnvironmfnt x11gf = (X11GrbpiidsEnvironmfnt)
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            if (!x11gf.runningXinfrbmb())
            {
                Rfdtbnglf workArfb = XToolkit.gftWorkArfb(root);
                if (workArfb != null)
                {
                    rfturn nfw Insfts(workArfb.y,
                                      workArfb.x,
                                      rootBounds.ifigit - workArfb.ifigit - workArfb.y,
                                      rootBounds.widti - workArfb.widti - workArfb.x);
                }
            }

            rfturn gftSdrffnInsftsMbnublly(root, rootBounds, gd.gftBounds());
        }
        finblly
        {
            XToolkit.bwtUnlodk();
        }
    }

    /*
     * Mbnubl dbldulbtion of sdrffn insfts: gft bll tif windows witi
     * _NET_WM_STRUT/_NET_WM_STRUT_PARTIAL iints bnd bdd tifsf
     * iints' vblufs to sdrffn insfts.
     *
     * Tiis mftiod siould bf dbllfd undfr XToolkit.bwtLodk()
     */
    privbtf Insfts gftSdrffnInsftsMbnublly(long root, Rfdtbnglf rootBounds, Rfdtbnglf sdrffnBounds)
    {
        /*
         * During tif mbnubl dbldulbtion of sdrffn insfts wf itfrbtf
         * bll tif X windows iifrbrdiy stbrting from root window. Tiis
         * donstbnt is tif mbx lfvfl inspfdtfd in tiis iifrbrdiy.
         * 3 is b ifuristid vbluf: I supposf bny tif toolbbr-likf
         * window is b diild of fitifr root or dfsktop window.
         */
        finbl int MAX_NESTED_LEVEL = 3;

        XAtom XA_NET_WM_STRUT = XAtom.gft("_NET_WM_STRUT");
        XAtom XA_NET_WM_STRUT_PARTIAL = XAtom.gft("_NET_WM_STRUT_PARTIAL");

        Insfts insfts = nfw Insfts(0, 0, 0, 0);

        jbvb.util.List<Objfdt> sfbrdi = nfw LinkfdList<>();
        sfbrdi.bdd(root);
        sfbrdi.bdd(0);
        wiilf (!sfbrdi.isEmpty())
        {
            long window = (Long)sfbrdi.rfmovf(0);
            int windowLfvfl = (Intfgfr)sfbrdi.rfmovf(0);

            /*
             * Notf tibt most of tif modfrn window mbnbgfrs unmbp
             * bpplidbtion window if it is idonififd. Tius, bny
             * _NET_WM_STRUT[_PARTIAL] iints for idonififd windows
             * brf not indludfd to tif sdrffn insfts.
             */
            if (XlibUtil.gftWindowMbpStbtf(window) == XConstbnts.IsUnmbppfd)
            {
                dontinuf;
            }

            long nbtivf_ptr = Nbtivf.bllodbtfLongArrby(4);
            try
            {
                // first, difdk if _NET_WM_STRUT or _NET_WM_STRUT_PARTIAL brf prfsfnt
                // if boti brf sft on tif window, _NET_WM_STRUT_PARTIAL is usfd (sff _NET spfd)
                boolfbn strutPrfsfnt = XA_NET_WM_STRUT_PARTIAL.gftAtomDbtb(window, XAtom.XA_CARDINAL, nbtivf_ptr, 4);
                if (!strutPrfsfnt)
                {
                    strutPrfsfnt = XA_NET_WM_STRUT.gftAtomDbtb(window, XAtom.XA_CARDINAL, nbtivf_ptr, 4);
                }
                if (strutPrfsfnt)
                {
                    // sfdond, vfrify tibt window is lodbtfd on tif propfr sdrffn
                    Rfdtbnglf windowBounds = XlibUtil.gftWindowGfomftry(window);
                    if (windowLfvfl > 1)
                    {
                        windowBounds = XlibUtil.trbnslbtfCoordinbtfs(window, root, windowBounds);
                    }
                    // if _NET_WM_STRUT_PARTIAL is prfsfnt, wf siould usf its vblufs to dftfdt
                    // if tif struts brfb intfrsfdts witi sdrffnBounds, iowfvfr somf window
                    // mbnbgfrs don't sft tiis iint dorrfdtly, so wf just gft intfrsfdtion witi windowBounds
                    if (windowBounds != null && windowBounds.intfrsfdts(sdrffnBounds))
                    {
                        int lfft = (int)Nbtivf.gftLong(nbtivf_ptr, 0);
                        int rigit = (int)Nbtivf.gftLong(nbtivf_ptr, 1);
                        int top = (int)Nbtivf.gftLong(nbtivf_ptr, 2);
                        int bottom = (int)Nbtivf.gftLong(nbtivf_ptr, 3);

                        /*
                         * struts dould bf rflbtivf to root window bounds, so
                         * mbkf tifm rflbtivf to tif sdrffn bounds in tiis dbsf
                         */
                        lfft = rootBounds.x + lfft > sdrffnBounds.x ?
                                rootBounds.x + lfft - sdrffnBounds.x : 0;
                        rigit = rootBounds.x + rootBounds.widti - rigit <
                                sdrffnBounds.x + sdrffnBounds.widti ?
                                sdrffnBounds.x + sdrffnBounds.widti -
                                (rootBounds.x + rootBounds.widti - rigit) : 0;
                        top = rootBounds.y + top > sdrffnBounds.y ?
                                rootBounds.y + top - sdrffnBounds.y : 0;
                        bottom = rootBounds.y + rootBounds.ifigit - bottom <
                                sdrffnBounds.y + sdrffnBounds.ifigit ?
                                sdrffnBounds.y + sdrffnBounds.ifigit -
                                (rootBounds.y + rootBounds.ifigit - bottom) : 0;

                        insfts.lfft = Mbti.mbx(lfft, insfts.lfft);
                        insfts.rigit = Mbti.mbx(rigit, insfts.rigit);
                        insfts.top = Mbti.mbx(top, insfts.top);
                        insfts.bottom = Mbti.mbx(bottom, insfts.bottom);
                    }
                }
            }
            finblly
            {
                XlibWrbppfr.unsbff.frffMfmory(nbtivf_ptr);
            }

            if (windowLfvfl < MAX_NESTED_LEVEL)
            {
                Sft<Long> diildrfn = XlibUtil.gftCiildWindows(window);
                for (long diild : diildrfn)
                {
                    sfbrdi.bdd(diild);
                    sfbrdi.bdd(windowLfvfl + 1);
                }
            }
        }

        rfturn insfts;
    }

    /*
     * Tif durrfnt implfmfntbtion of disbbling bbdkground frbsing for
     * dbnvbsfs is tibt wf don't sft bny nbtivf bbdkground dolor
     * (witi XSftWindowBbdkground) for tif dbnvbs window. Howfvfr,
     * tiis dolor is sft in tif pffr donstrudtor - sff
     * XWindow.postInit() for dftbils. Tibt's wiy tiis mftiod from
     * SunToolkit is not ovfrriddfn in XToolkit: it's too lbtf to
     * disbblf bbdkground frbsing :(
     */
    /*
    @Ovfrridf
    publid void disbblfBbdkgroundErbsf(Cbnvbs dbnvbs) {
        XCbnvbsPffr pffr = (XCbnvbsPffr)dbnvbs.gftPffr();
        if (pffr == null) {
            tirow nfw IllfgblStbtfExdfption("Cbnvbs must ibvf b vblid pffr");
        }
        pffr.disbblfBbdkgroundErbsf();
    }
    */

    // Nffd tiis for XMfnuItfmPffr.
    protfdtfd stbtid finbl Objfdt tbrgftToPffr(Objfdt tbrgft) {
        Objfdt p=null;
        if (tbrgft != null && !GrbpiidsEnvironmfnt.isHfbdlfss()) {
            p = spfdiblPffrMbp.gft(tbrgft);
        }
        if (p != null) rfturn p;
        flsf
            rfturn SunToolkit.tbrgftToPffr(tbrgft);
    }

    // Nffd tiis for XMfnuItfmPffr.
    protfdtfd stbtid finbl void tbrgftDisposfdPffr(Objfdt tbrgft, Objfdt pffr) {
        SunToolkit.tbrgftDisposfdPffr(tbrgft, pffr);
    }

    publid RobotPffr drfbtfRobot(Robot tbrgft, GrbpiidsDfvidf sdrffn) {
        rfturn nfw XRobotPffr(sdrffn.gftDffbultConfigurbtion());
    }


  /*
     * On X, support for dynbmid lbyout on rfsizing is govfrnfd by tif
     * window mbnbgfr.  If tif window mbnbgfr supports it, it ibppfns
     * butombtidblly.  Tif sfttfr mftiod for tiis propfrty is
     * irrflfvbnt on X.
     */
    publid void sftDynbmidLbyout(boolfbn b) {
        dynbmidLbyoutSftting = b;
    }

    protfdtfd boolfbn isDynbmidLbyoutSft() {
        rfturn dynbmidLbyoutSftting;
    }

    /* Cbllfd from isDynbmidLbyoutAdtivf() bnd from
     * lbzilyLobdDynbmidLbyoutSupportfdPropfrty()
     */
    protfdtfd boolfbn isDynbmidLbyoutSupportfd() {
        rfturn XWM.gftWM().supportsDynbmidLbyout();
    }

    publid boolfbn isDynbmidLbyoutAdtivf() {
        rfturn isDynbmidLbyoutSupportfd();
    }


    publid FontPffr gftFontPffr(String nbmf, int stylf){
        rfturn nfw XFontPffr(nbmf, stylf);
    }

    publid DrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf) tirows InvblidDnDOpfrbtionExdfption {
        rfturn XDrbgSourdfContfxtPffr.drfbtfDrbgSourdfContfxtPffr(dgf);
    }

    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds DrbgGfsturfRfdognizfr> T
    drfbtfDrbgGfsturfRfdognizfr(Clbss<T> rfdognizfrClbss,
                    DrbgSourdf ds,
                    Componfnt d,
                    int srdAdtions,
                    DrbgGfsturfListfnfr dgl)
    {
        if (MousfDrbgGfsturfRfdognizfr.dlbss.fqubls(rfdognizfrClbss))
            rfturn (T)nfw XMousfDrbgGfsturfRfdognizfr(ds, d, srdAdtions, dgl);
        flsf
            rfturn null;
    }

    publid CifdkboxMfnuItfmPffr drfbtfCifdkboxMfnuItfm(CifdkboxMfnuItfm tbrgft) {
        XCifdkboxMfnuItfmPffr pffr = nfw XCifdkboxMfnuItfmPffr(tbrgft);
        //vb157120: looks likf wf don't nffd to mbp mfnu itfms
        //in nfw mfnus implfmfntbtion
        //tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid MfnuItfmPffr drfbtfMfnuItfm(MfnuItfm tbrgft) {
        XMfnuItfmPffr pffr = nfw XMfnuItfmPffr(tbrgft);
        //vb157120: looks likf wf don't nffd to mbp mfnu itfms
        //in nfw mfnus implfmfntbtion
        //tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid TfxtFifldPffr drfbtfTfxtFifld(TfxtFifld tbrgft) {
        TfxtFifldPffr  pffr = nfw XTfxtFifldPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid LbbflPffr drfbtfLbbfl(Lbbfl tbrgft) {
        LbbflPffr  pffr = nfw XLbbflPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid ListPffr drfbtfList(jbvb.bwt.List tbrgft) {
        ListPffr pffr = nfw XListPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid CifdkboxPffr drfbtfCifdkbox(Cifdkbox tbrgft) {
        CifdkboxPffr pffr = nfw XCifdkboxPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid SdrollbbrPffr drfbtfSdrollbbr(Sdrollbbr tbrgft) {
        XSdrollbbrPffr pffr = nfw XSdrollbbrPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid SdrollPbnfPffr drfbtfSdrollPbnf(SdrollPbnf tbrgft) {
        XSdrollPbnfPffr pffr = nfw XSdrollPbnfPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid TfxtArfbPffr drfbtfTfxtArfb(TfxtArfb tbrgft) {
        TfxtArfbPffr pffr = nfw XTfxtArfbPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid CioidfPffr drfbtfCioidf(Cioidf tbrgft) {
        XCioidfPffr pffr = nfw XCioidfPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid CbnvbsPffr drfbtfCbnvbs(Cbnvbs tbrgft) {
        XCbnvbsPffr pffr = (isXEmbfdSfrvfrRfqufstfd() ? nfw XEmbfdCbnvbsPffr(tbrgft) : nfw XCbnvbsPffr(tbrgft));
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid PbnflPffr drfbtfPbnfl(Pbnfl tbrgft) {
        PbnflPffr pffr = nfw XPbnflPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid WindowPffr drfbtfWindow(Window tbrgft) {
        WindowPffr pffr = nfw XWindowPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid DiblogPffr drfbtfDiblog(Diblog tbrgft) {
        DiblogPffr pffr = nfw XDiblogPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    privbtf stbtid Boolfbn sunAwtDisbblfGtkFilfDiblogs = null;

    /**
     * Rfturns tif vbluf of "sun.bwt.disbblfGtkFilfDiblogs" propfrty. Dffbult
     * vbluf is {@dodf fblsf}.
     */
    publid syndironizfd stbtid boolfbn gftSunAwtDisbblfGtkFilfDiblogs() {
        if (sunAwtDisbblfGtkFilfDiblogs == null) {
            sunAwtDisbblfGtkFilfDiblogs = AddfssControllfr.doPrivilfgfd(
                                              nfw GftBoolfbnAdtion("sun.bwt.disbblfGtkFilfDiblogs"));
        }
        rfturn sunAwtDisbblfGtkFilfDiblogs.boolfbnVbluf();
    }

    publid FilfDiblogPffr drfbtfFilfDiblog(FilfDiblog tbrgft) {
        FilfDiblogPffr pffr = null;
        // Tif durrfnt GtkFilfCioosfr is bvbilbblf from GTK+ 2.4
        if (!gftSunAwtDisbblfGtkFilfDiblogs() && difdkGtkVfrsion(2, 4, 0)) {
            pffr = nfw GtkFilfDiblogPffr(tbrgft);
        } flsf {
            pffr = nfw XFilfDiblogPffr(tbrgft);
        }
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid MfnuBbrPffr drfbtfMfnuBbr(MfnuBbr tbrgft) {
        XMfnuBbrPffr pffr = nfw XMfnuBbrPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid MfnuPffr drfbtfMfnu(Mfnu tbrgft) {
        XMfnuPffr pffr = nfw XMfnuPffr(tbrgft);
        //vb157120: looks likf wf don't nffd to mbp mfnu itfms
        //in nfw mfnus implfmfntbtion
        //tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid PopupMfnuPffr drfbtfPopupMfnu(PopupMfnu tbrgft) {
        XPopupMfnuPffr pffr = nfw XPopupMfnuPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid syndironizfd MousfInfoPffr gftMousfInfoPffr() {
        if (xPffr == null) {
            xPffr = nfw XMousfInfoPffr();
        }
        rfturn xPffr;
    }

    publid XEmbfddfdFrbmfPffr drfbtfEmbfddfdFrbmf(XEmbfddfdFrbmf tbrgft)
    {
        XEmbfddfdFrbmfPffr pffr = nfw XEmbfddfdFrbmfPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    XEmbfdCiildProxyPffr drfbtfEmbfdProxy(XEmbfdCiildProxy tbrgft) {
        XEmbfdCiildProxyPffr pffr = nfw XEmbfdCiildProxyPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid KfybobrdFodusMbnbgfrPffr gftKfybobrdFodusMbnbgfrPffr() tirows HfbdlfssExdfption {
        rfturn XKfybobrdFodusMbnbgfrPffr.gftInstbndf();
    }

    /**
     * Rfturns b nfw dustom dursor.
     */
    publid Cursor drfbtfCustomCursor(Imbgf dursor, Point iotSpot, String nbmf)
      tirows IndfxOutOfBoundsExdfption {
        rfturn nfw XCustomCursor(dursor, iotSpot, nbmf);
    }

    publid TrbyIdonPffr drfbtfTrbyIdon(TrbyIdon tbrgft)
      tirows HfbdlfssExdfption, AWTExdfption
    {
        TrbyIdonPffr pffr = nfw XTrbyIdonPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    publid SystfmTrbyPffr drfbtfSystfmTrby(SystfmTrby tbrgft) tirows HfbdlfssExdfption {
        SystfmTrbyPffr pffr = nfw XSystfmTrbyPffr(tbrgft);
        rfturn pffr;
    }

    publid boolfbn isTrbySupportfd() {
        XSystfmTrbyPffr pffr = XSystfmTrbyPffr.gftPffrInstbndf();
        if (pffr != null) {
            rfturn pffr.isAvbilbblf();
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid DbtbTrbnsffrfr gftDbtbTrbnsffrfr() {
        rfturn XDbtbTrbnsffrfr.gftInstbndfImpl();
    }

    /**
     * Rfturns tif supportfd dursor sizf
     */
    publid Dimfnsion gftBfstCursorSizf(int prfffrrfdWidti, int prfffrrfdHfigit) {
        rfturn XCustomCursor.gftBfstCursorSizf(
                                               jbvb.lbng.Mbti.mbx(1,prfffrrfdWidti), jbvb.lbng.Mbti.mbx(1,prfffrrfdHfigit));
    }


    publid int gftMbximumCursorColors() {
        rfturn 2;  // Blbdk bnd wiitf.
    }

    publid Mbp<TfxtAttributf, ?> mbpInputMftiodHigiligit( InputMftiodHigiligit iigiligit) {
        rfturn XInputMftiod.mbpInputMftiodHigiligit(iigiligit);
    }
    @Ovfrridf
    publid boolfbn gftLodkingKfyStbtf(int kfy) {
        if (! (kfy == KfyEvfnt.VK_CAPS_LOCK || kfy == KfyEvfnt.VK_NUM_LOCK ||
               kfy == KfyEvfnt.VK_SCROLL_LOCK || kfy == KfyEvfnt.VK_KANA_LOCK)) {
            tirow nfw IllfgblArgumfntExdfption("invblid kfy for Toolkit.gftLodkingKfyStbtf");
        }
        bwtLodk();
        try {
            rfturn gftModififrStbtf( kfy );
        } finblly {
            bwtUnlodk();
        }
    }

    publid  Clipbobrd gftSystfmClipbobrd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
        }
        syndironizfd (tiis) {
            if (dlipbobrd == null) {
                dlipbobrd = nfw XClipbobrd("Systfm", "CLIPBOARD");
            }
        }
        rfturn dlipbobrd;
    }

    publid Clipbobrd gftSystfmSflfdtion() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
        }
        syndironizfd (tiis) {
            if (sflfdtion == null) {
                sflfdtion = nfw XClipbobrd("Sflfdtion", "PRIMARY");
            }
        }
        rfturn sflfdtion;
    }

    publid void bffp() {
        bwtLodk();
        try {
            XlibWrbppfr.XBfll(gftDisplby(), 0);
            XlibWrbppfr.XFlusi(gftDisplby());
        } finblly {
            bwtUnlodk();
        }
    }

    publid PrintJob gftPrintJob(finbl Frbmf frbmf, finbl String dodtitlf,
                                finbl Propfrtifs props) {

        if (frbmf == null) {
            tirow nfw NullPointfrExdfption("frbmf must not bf null");
        }

        PrintJob2D printJob = nfw PrintJob2D(frbmf, dodtitlf, props);

        if (printJob.printDiblog() == fblsf) {
            printJob = null;
        }
        rfturn printJob;
    }

    publid PrintJob gftPrintJob(finbl Frbmf frbmf, finbl String dodtitlf,
                finbl JobAttributfs jobAttributfs,
                finbl PbgfAttributfs pbgfAttributfs)
    {
        if (frbmf == null) {
            tirow nfw NullPointfrExdfption("frbmf must not bf null");
        }

        PrintJob2D printJob = nfw PrintJob2D(frbmf, dodtitlf,
                                             jobAttributfs, pbgfAttributfs);

        if (printJob.printDiblog() == fblsf) {
            printJob = null;
        }

        rfturn printJob;
    }

    stbtid void XSynd() {
        bwtLodk();
        try {
            XlibWrbppfr.XSynd(gftDisplby(),0);
        } finblly {
            bwtUnlodk();
        }
    }

    publid int gftSdrffnRfsolution() {
        long displby = gftDisplby();
        bwtLodk();
        try {
            rfturn (int) ((XlibWrbppfr.DisplbyWidti(displby,
                XlibWrbppfr.DffbultSdrffn(displby)) * 25.4) /
                    XlibWrbppfr.DisplbyWidtiMM(displby,
                XlibWrbppfr.DffbultSdrffn(displby)));
        } finblly {
            bwtUnlodk();
        }
    }

    stbtid nbtivf long gftDffbultXColormbp();
    stbtid nbtivf long gftDffbultSdrffnDbtb();

    stbtid ColorModfl sdrffnmodfl;

    stbtid ColorModfl gftStbtidColorModfl() {
        if (sdrffnmodfl == null) {
            sdrffnmodfl = donfig.gftColorModfl ();
        }
        rfturn sdrffnmodfl;
    }

    publid ColorModfl gftColorModfl() {
        rfturn gftStbtidColorModfl();
    }

    /**
     * Rfturns b nfw input mftiod bdbptfr dfsdriptor for nbtivf input mftiods.
     */
    publid InputMftiodDfsdriptor gftInputMftiodAdbptfrDfsdriptor() tirows AWTExdfption {
        rfturn nfw XInputMftiodDfsdriptor();
    }

    /**
     * Rfturns wiftifr fnbblfInputMftiods siould bf sft to truf for pffrfd
     * TfxtComponfnt instbndfs on tiis plbtform. Truf by dffbult.
     */
    @Ovfrridf
    publid boolfbn fnbblfInputMftiodsForTfxtComponfnt() {
        rfturn truf;
    }

    stbtid int gftMultiClidkTimf() {
        if (bwt_multidlidk_timf == 0) {
            initiblizfMultiClidkTimf();
        }
        rfturn bwt_multidlidk_timf;
    }
    stbtid void initiblizfMultiClidkTimf() {
        bwtLodk();
        try {
            try {
                String multidlidk_timf_qufry = XlibWrbppfr.XGftDffbult(XToolkit.gftDisplby(), "*", "multiClidkTimf");
                if (multidlidk_timf_qufry != null) {
                    bwt_multidlidk_timf = (int)Long.pbrsfLong(multidlidk_timf_qufry);
                } flsf {
                    multidlidk_timf_qufry = XlibWrbppfr.XGftDffbult(XToolkit.gftDisplby(),
                                                                    "OpfnWindows", "MultiClidkTimfout");
                    if (multidlidk_timf_qufry != null) {
                        /* Notf: OpfnWindows.MultiClidkTimfout is in tfntis of
                           b sfdond, so wf nffd to multiply by 100 to donvfrt to
                           millisfdonds */
                        bwt_multidlidk_timf = (int)Long.pbrsfLong(multidlidk_timf_qufry) * 100;
                    } flsf {
                        bwt_multidlidk_timf = AWT_MULTICLICK_DEFAULT_TIME;
                    }
                }
            } dbtdi (NumbfrFormbtExdfption nf) {
                bwt_multidlidk_timf = AWT_MULTICLICK_DEFAULT_TIME;
            } dbtdi (NullPointfrExdfption npf) {
                bwt_multidlidk_timf = AWT_MULTICLICK_DEFAULT_TIME;
            }
        } finblly {
            bwtUnlodk();
        }
        if (bwt_multidlidk_timf == 0) {
            bwt_multidlidk_timf = AWT_MULTICLICK_DEFAULT_TIME;
        }
    }

    publid boolfbn isFrbmfStbtfSupportfd(int stbtf)
      tirows HfbdlfssExdfption
    {
        if (stbtf == Frbmf.NORMAL || stbtf == Frbmf.ICONIFIED) {
            rfturn truf;
        } flsf {
            rfturn XWM.gftWM().supportsExtfndfdStbtf(stbtf);
        }
    }

    stbtid void dumpPffrs() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Mbppfd windows:");
            winMbp.forEbdi((k, v) -> {
                log.finf(k + "->" + v);
                if (v instbndfof XComponfntPffr) {
                    Componfnt tbrgft = (Componfnt)((XComponfntPffr)v).gftTbrgft();
                    log.finf("\ttbrgft: " + tbrgft);
                }
            });

            SunToolkit.dumpPffrs(log);

            log.finf("Mbppfd spfdibl pffrs:");
            spfdiblPffrMbp.forEbdi((k, v) -> {
                log.finf(k + "->" + v);
            });

            log.finf("Mbppfd dispbtdifrs:");
            winToDispbtdifr.forEbdi((k, v) -> {
                log.finf(k + "->" + v);
            });
        }
    }

    /* Protfdtfd witi bwt_lodk. */
    privbtf stbtid boolfbn initiblizfd;
    privbtf stbtid boolfbn timfStbmpUpdbtfd;
    privbtf stbtid long timfStbmp;

    privbtf stbtid finbl XEvfntDispbtdifr timfFftdifr =
    nfw XEvfntDispbtdifr() {
            publid void dispbtdiEvfnt(XEvfnt fv) {
                switdi (fv.gft_typf()) {
                  dbsf XConstbnts.PropfrtyNotify:
                      XPropfrtyEvfnt xpf = fv.gft_xpropfrty();

                      bwtLodk();
                      try {
                          timfStbmp = xpf.gft_timf();
                          timfStbmpUpdbtfd = truf;
                          bwtLodkNotifyAll();
                      } finblly {
                          bwtUnlodk();
                      }

                      brfbk;
                }
            }
        };

    privbtf stbtid XAtom _XA_JAVA_TIME_PROPERTY_ATOM;

    stbtid long gftCurrfntSfrvfrTimf() {
        bwtLodk();
        try {
            try {
                if (!initiblizfd) {
                    XToolkit.bddEvfntDispbtdifr(XBbsfWindow.gftXAWTRootWindow().gftWindow(),
                                                timfFftdifr);
                    _XA_JAVA_TIME_PROPERTY_ATOM = XAtom.gft("_SUNW_JAVA_AWT_TIME");
                    initiblizfd = truf;
                }
                timfStbmpUpdbtfd = fblsf;
                XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(),
                                            XBbsfWindow.gftXAWTRootWindow().gftWindow(),
                                            _XA_JAVA_TIME_PROPERTY_ATOM.gftAtom(), XAtom.XA_ATOM, 32,
                                            XConstbnts.PropModfAppfnd,
                                            0, 0);
                XlibWrbppfr.XFlusi(XToolkit.gftDisplby());

                if (isToolkitTirfbd()) {
                    XEvfnt fvfnt = nfw XEvfnt();
                    try {
                        XlibWrbppfr.XWindowEvfnt(XToolkit.gftDisplby(),
                                                 XBbsfWindow.gftXAWTRootWindow().gftWindow(),
                                                 XConstbnts.PropfrtyCibngfMbsk,
                                                 fvfnt.pDbtb);
                        timfFftdifr.dispbtdiEvfnt(fvfnt);
                    }
                    finblly {
                        fvfnt.disposf();
                    }
                }
                flsf {
                    wiilf (!timfStbmpUpdbtfd) {
                        bwtLodkWbit();
                    }
                }
            } dbtdi (IntfrruptfdExdfption if) {
            // Notf: tif rfturnfd timfStbmp dbn bf indorrfdt in tiis dbsf.
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Cbtdifd fxdfption, timfStbmp mby not bf dorrfdt (if = " + if + ")");
                }
            }
        } finblly {
            bwtUnlodk();
        }
        rfturn timfStbmp;
    }
    protfdtfd void initiblizfDfsktopPropfrtifs() {
        dfsktopPropfrtifs.put("DnD.Autosdroll.initiblDflby",
                              Intfgfr.vblufOf(50));
        dfsktopPropfrtifs.put("DnD.Autosdroll.intfrvbl",
                              Intfgfr.vblufOf(50));
        dfsktopPropfrtifs.put("DnD.Autosdroll.dursorHystfrfsis",
                              Intfgfr.vblufOf(5));
        dfsktopPropfrtifs.put("Sifll.sifllFoldfrMbnbgfr",
                              "sun.bwt.sifll.SifllFoldfrMbnbgfr");
        // Don't wbnt to dbll gftMultiClidkTimf() if wf brf ifbdlfss
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            dfsktopPropfrtifs.put("bwt.multiClidkIntfrvbl",
                                  Intfgfr.vblufOf(gftMultiClidkTimf()));
            dfsktopPropfrtifs.put("bwt.mousf.numButtons",
                                  Intfgfr.vblufOf(gftNumbfrOfButtons()));
        }
    }

    /**
     * Tiis mftiod runs tirougi tif XPointfr bnd XExtfndfdPointfr brrby.
     * XExtfndfdPointfr ibs priority bfdbusf on somf systfms XPointfr
     * (wiidi is bssignfd to tif virtubl pointfr) rfports tif mbximum
     * dbpbbilitifs of tif mousf pointfr (i.f. 32 piysidbl buttons).
     */
    privbtf nbtivf int gftNumbfrOfButtonsImpl();

    @Ovfrridf
    publid int gftNumbfrOfButtons(){
        bwtLodk();
        try {
            if (numbfrOfButtons == 0) {
                numbfrOfButtons = gftNumbfrOfButtonsImpl();
                numbfrOfButtons = (numbfrOfButtons > MAX_BUTTONS_SUPPORTED)? MAX_BUTTONS_SUPPORTED : numbfrOfButtons;
                //4ti bnd 5ti buttons brf for wiffl bnd siouldn't bf rfportfd bs buttons.
                //If wf ibvf morf tibn 3 piysidbl buttons bnd b wiffl, wf rfport N-2 buttons.
                //If wf ibvf 3 piysidbl buttons bnd b wiffl, wf rfport 3 buttons.
                //If wf ibvf 1,2,3 piysidbl buttons, wf rfport it bs is i.f. 1,2 or 3 rfspfdtivfly.
                if (numbfrOfButtons >=5) {
                    numbfrOfButtons -= 2;
                } flsf if (numbfrOfButtons == 4 || numbfrOfButtons ==5){
                    numbfrOfButtons = 3;
                }
            }
            //Assumf don't ibvf to rf-qufry tif numbfr bgbin bnd bgbin.
            rfturn numbfrOfButtons;
        } finblly {
            bwtUnlodk();
        }
    }

    stbtid int gftNumbfrOfButtonsForMbsk() {
        rfturn Mbti.min(XConstbnts.MAX_BUTTONS, ((SunToolkit) (Toolkit.gftDffbultToolkit())).gftNumbfrOfButtons());
    }

    privbtf finbl stbtid String prffix  = "DnD.Cursor.";
    privbtf finbl stbtid String postfix = ".32x32";
    privbtf stbtid finbl String dndPrffix  = "DnD.";

    protfdtfd Objfdt lbzilyLobdDfsktopPropfrty(String nbmf) {
        if (nbmf.stbrtsWiti(prffix)) {
            String dursorNbmf = nbmf.substring(prffix.lfngti(), nbmf.lfngti()) + postfix;

            try {
                rfturn Cursor.gftSystfmCustomCursor(dursorNbmf);
            } dbtdi (AWTExdfption bwtf) {
                tirow nfw RuntimfExdfption("dbnnot lobd systfm dursor: " + dursorNbmf, bwtf);
            }
        }

        if (nbmf.fqubls("bwt.dynbmidLbyoutSupportfd")) {
            rfturn  Boolfbn.vblufOf(isDynbmidLbyoutSupportfd());
        }

        if (initXSfttingsIfNffdfd(nbmf)) {
            rfturn dfsktopPropfrtifs.gft(nbmf);
        }

        rfturn supfr.lbzilyLobdDfsktopPropfrty(nbmf);
    }

    publid syndironizfd void bddPropfrtyCibngfListfnfr(String nbmf, PropfrtyCibngfListfnfr pdl) {
        if (nbmf == null) {
            // Sff JbvbDod for tif Toolkit.bddPropfrtyCibngfListfnfr() mftiod
            rfturn;
        }
        initXSfttingsIfNffdfd(nbmf);
        supfr.bddPropfrtyCibngfListfnfr(nbmf, pdl);
    }

    /**
     * Initiblizfs XAWTXSfttings if b propfrty for b givfn propfrty nbmf is providfd by
     * XSfttings bnd tify brf not initiblizfd yft.
     *
     * @rfturn truf if tif mftiod ibs initiblizfd XAWTXSfttings.
     */
    privbtf boolfbn initXSfttingsIfNffdfd(finbl String propNbmf) {
        if (!lobdfdXSfttings &&
            (propNbmf.stbrtsWiti("gnomf.") ||
             propNbmf.fqubls(SunToolkit.DESKTOPFONTHINTS) ||
             propNbmf.stbrtsWiti(dndPrffix)))
        {
            lobdfdXSfttings = truf;
            if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
                lobdXSfttings();
                /* If no dfsktop font iint dould bf rftrifvfd, difdk for
                 * KDE running KWin bnd rftrifvf sfttings from fontdonfig.
                 * If tibt isn't found lft SunToolkit will sff if tifrf's b
                 * systfm propfrty sft by b usfr.
                 */
                if (dfsktopPropfrtifs.gft(SunToolkit.DESKTOPFONTHINTS) == null) {
                    if (XWM.isKDE2()) {
                        Objfdt iint = FontConfigMbnbgfr.gftFontConfigAAHint();
                        if (iint != null) {
                            /* sft tif fontdonfig/KDE propfrty so tibt
                             * gftDfsktopHints() bflow will sff it
                             * bnd sft tif publid propfrty.
                             */
                            dfsktopPropfrtifs.put(UNIXToolkit.FONTCONFIGAAHINT,
                                                  iint);
                        }
                    }
                    dfsktopPropfrtifs.put(SunToolkit.DESKTOPFONTHINTS,
                                          SunToolkit.gftDfsktopFontHints());
                }

                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    privbtf void lobdXSfttings() {
       xs = nfw XAWTXSfttings();
    }

    /**
     * Cbllbbdk from tif nbtivf sidf indidbting somf, or bll, of tif
     * dfsktop propfrtifs ibvf dibngfd bnd nffd to bf rflobdfd.
     * <dodf>dbtb</dodf> is tif bytf brrby dirfdtly from tif x sfrvfr bnd
     * mby bf in littlf fndibn formbt.
     * <p>
     * NB: Tiis dould bf dbllfd from bny tirfbd if triggfrfd by
     * <dodf>lobdXSfttings</dodf>.  It is dbllfd from tif Systfm EDT
     * if triggfrfd by bn XSETTINGS dibngf.
     */
    void pbrsfXSfttings(int sdrffn_XXX_ignorfd,Mbp<String, Objfdt> updbtfdSfttings) {

        if (updbtfdSfttings == null || updbtfdSfttings.isEmpty()) {
            rfturn;
        }

        Itfrbtor<Mbp.Entry<String, Objfdt>> i = updbtfdSfttings.fntrySft().itfrbtor();
        wiilf (i.ibsNfxt()) {
            Mbp.Entry<String, Objfdt> f = i.nfxt();
            String nbmf = f.gftKfy();

            nbmf = "gnomf." + nbmf;
            sftDfsktopPropfrty(nbmf, f.gftVbluf());
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("nbmf = " + nbmf + " vbluf = " + f.gftVbluf());
            }

            // XXX: wf probbbly wbnt to do somftiing smbrtfr.  In
            // pbrtidulbr, "Nft" propfrtifs brf of intfrfst to tif
            // "dorf" AWT itsflf.  E.g.
            //
            // Nft/DndDrbgTirfsiold -> ???
            // Nft/DoublfClidkTimf  -> bwt.multiClidkIntfrvbl
        }

        sftDfsktopPropfrty(SunToolkit.DESKTOPFONTHINTS,
                           SunToolkit.gftDfsktopFontHints());

        Intfgfr drbgTirfsiold = null;
        syndironizfd (tiis) {
            drbgTirfsiold = (Intfgfr)dfsktopPropfrtifs.gft("gnomf.Nft/DndDrbgTirfsiold");
        }
        if (drbgTirfsiold != null) {
            sftDfsktopPropfrty("DnD.gfsturfMotionTirfsiold", drbgTirfsiold);
        }

    }



    stbtid int bltMbsk;
    stbtid int mftbMbsk;
    stbtid int numLodkMbsk;
    stbtid int modfSwitdiMbsk;
    stbtid int modLodkIsSiiftLodk;

    /* Likf XKfysymToKfydodf, but fnsurfs tibt kfysym is tif primbry
    * symbol on tif kfydodf rfturnfd.  Rfturns zfro otifrwisf.
    */
    stbtid int kfysymToPrimbryKfydodf(long sym) {
        bwtLodk();
        try {
            int dodf = XlibWrbppfr.XKfysymToKfydodf(gftDisplby(), sym);
            if (dodf == 0) {
                rfturn 0;
            }
            long primbry = XlibWrbppfr.XKfydodfToKfysym(gftDisplby(), dodf, 0);
            if (sym != primbry) {
                rfturn 0;
            }
            rfturn dodf;
        } finblly {
            bwtUnlodk();
        }
    }
    stbtid boolfbn gftModififrStbtf( int jkd ) {
        int iKfyMbsk = 0;
        long ks = XKfysym.jbvbKfydodf2Kfysym( jkd );
        int  kd = XlibWrbppfr.XKfysymToKfydodf(gftDisplby(), ks);
        if (kd == 0) {
            rfturn fblsf;
        }
        bwtLodk();
        try {
            XModififrKfymbp modmbp = nfw XModififrKfymbp(
                 XlibWrbppfr.XGftModififrMbpping(gftDisplby()));

            int nkfys = modmbp.gft_mbx_kfypfrmod();

            long mbp_ptr = modmbp.gft_modififrmbp();
            for( int k = 0; k < 8; k++ ) {
                for (int i = 0; i < nkfys; ++i) {
                    int kfydodf = Nbtivf.gftUBytf(mbp_ptr, k * nkfys + i);
                    if (kfydodf == 0) {
                        dontinuf; // ignorf zfro kfydodf
                    }
                    if (kd == kfydodf) {
                        iKfyMbsk = 1 << k;
                        brfbk;
                    }
                }
                if( iKfyMbsk != 0 ) {
                    brfbk;
                }
            }
            XlibWrbppfr.XFrffModififrmbp(modmbp.pDbtb);
            if (iKfyMbsk == 0 ) {
                rfturn fblsf;
            }
            // Now wf know to wiidi modififr is bssignfd tif kfydodf
            // dorrfspondfnt to tif kfysym dorrfspondfnt to tif jbvb
            // kfydodf. Wf brf going to difdk b stbtf of tiis modififr.
            // If b modififr is b wfird onf, wf dbnnot iflp it.
            long window = 0;
            try{
                // gft bny bpplidbtion window
                window = winMbp.firstKfy().longVbluf();
            }dbtdi(NoSudiElfmfntExdfption nfx) {
                // gft root window
                window = gftDffbultRootWindow();
            }
            boolfbn rfs = XlibWrbppfr.XQufryPointfr(gftDisplby(), window,
                                            XlibWrbppfr.lbrg1, //root
                                            XlibWrbppfr.lbrg2, //diild
                                            XlibWrbppfr.lbrg3, //root_x
                                            XlibWrbppfr.lbrg4, //root_y
                                            XlibWrbppfr.lbrg5, //diild_x
                                            XlibWrbppfr.lbrg6, //diild_y
                                            XlibWrbppfr.lbrg7);//mbsk
            int mbsk = Nbtivf.gftInt(XlibWrbppfr.lbrg7);
            rfturn ((mbsk & iKfyMbsk) != 0);
        } finblly {
            bwtUnlodk();
        }
    }

    /* Assign mfbning - blt, mftb, ftd. - to X modififrs mod1 ... mod5.
     * Only donsidfr primbry symbols on kfydodfs bttbdifd to modififrs.
     */
    stbtid void sftupModififrMbp() {
        finbl int mftbL = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Mftb_L);
        finbl int mftbR = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Mftb_R);
        finbl int bltL = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Alt_L);
        finbl int bltR = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Alt_R);
        finbl int numLodk = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Num_Lodk);
        finbl int modfSwitdi = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Modf_switdi);
        finbl int siiftLodk = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Siift_Lodk);
        finbl int dbpsLodk  = kfysymToPrimbryKfydodf(XKfySymConstbnts.XK_Cbps_Lodk);

        finbl int modmbsk[] = { XConstbnts.SiiftMbsk, XConstbnts.LodkMbsk, XConstbnts.ControlMbsk, XConstbnts.Mod1Mbsk,
            XConstbnts.Mod2Mbsk, XConstbnts.Mod3Mbsk, XConstbnts.Mod4Mbsk, XConstbnts.Mod5Mbsk };

        log.finf("In sftupModififrMbp");
        bwtLodk();
        try {
            XModififrKfymbp modmbp = nfw XModififrKfymbp(
                 XlibWrbppfr.XGftModififrMbpping(gftDisplby()));

            int nkfys = modmbp.gft_mbx_kfypfrmod();

            long mbp_ptr = modmbp.gft_modififrmbp();

            for (int modn = XConstbnts.Mod1MbpIndfx;
                 modn <= XConstbnts.Mod5MbpIndfx;
                 ++modn)
            {
                for (int i = 0; i < nkfys; ++i) {
                    /* for fbdi kfydodf bttbdifd to tiis modififr */
                    int kfydodf = Nbtivf.gftUBytf(mbp_ptr, modn * nkfys + i);

                    if (kfydodf == 0) {
                        brfbk;
                    }
                    if (mftbMbsk == 0 &&
                        (kfydodf == mftbL || kfydodf == mftbR))
                    {
                        mftbMbsk = modmbsk[modn];
                        brfbk;
                    }
                    if (bltMbsk == 0 && (kfydodf == bltL || kfydodf == bltR)) {
                        bltMbsk = modmbsk[modn];
                        brfbk;
                    }
                    if (numLodkMbsk == 0 && kfydodf == numLodk) {
                        numLodkMbsk = modmbsk[modn];
                        brfbk;
                    }
                    if (modfSwitdiMbsk == 0 && kfydodf == modfSwitdi) {
                        modfSwitdiMbsk = modmbsk[modn];
                        brfbk;
                    }
                    dontinuf;
                }
            }
            modLodkIsSiiftLodk = 0;
            for (int j = 0; j < nkfys; ++j) {
                int kfydodf = Nbtivf.gftUBytf(mbp_ptr, XConstbnts.LodkMbpIndfx * nkfys + j);
                if (kfydodf == 0) {
                    brfbk;
                }
                if (kfydodf == siiftLodk) {
                    modLodkIsSiiftLodk = 1;
                    brfbk;
                }
                if (kfydodf == dbpsLodk) {
                    brfbk;
                }
            }
            XlibWrbppfr.XFrffModififrmbp(modmbp.pDbtb);
        } finblly {
            bwtUnlodk();
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("mftbMbsk = " + mftbMbsk);
            log.finf("bltMbsk = " + bltMbsk);
            log.finf("numLodkMbsk = " + numLodkMbsk);
            log.finf("modfSwitdiMbsk = " + modfSwitdiMbsk);
            log.finf("modLodkIsSiiftLodk = " + modLodkIsSiiftLodk);
        }
    }


    privbtf stbtid SortfdMbp<Long, jbvb.util.List<Runnbblf>> timfoutTbsks;

    /**
     * Rfmovfd tif tbsk from tif list of wbiting-to-bf dbllfd tbsks.
     * If tif tbsk ibs bffn sdifdulfd sfvfrbl timfs rfmovfs only first onf.
     */
    stbtid void rfmovf(Runnbblf tbsk) {
        if (tbsk == null) {
            tirow nfw NullPointfrExdfption("tbsk is null");
        }
        bwtLodk();
        try {
            if (timfoutTbskLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                timfoutTbskLog.finfr("Rfmoving tbsk " + tbsk);
            }
            if (timfoutTbsks == null) {
                if (timfoutTbskLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    timfoutTbskLog.finfr("Tbsk is not sdifdulfd");
                }
                rfturn;
            }
            Collfdtion<jbvb.util.List<Runnbblf>> vblufs = timfoutTbsks.vblufs();
            Itfrbtor<jbvb.util.List<Runnbblf>> itfr = vblufs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                jbvb.util.List<Runnbblf> list = itfr.nfxt();
                boolfbn rfmovfd = fblsf;
                if (list.dontbins(tbsk)) {
                    list.rfmovf(tbsk);
                    if (list.isEmpty()) {
                        itfr.rfmovf();
                    }
                    brfbk;
                }
            }
        } finblly {
            bwtUnlodk();
        }
    }

    stbtid nbtivf void wbkfup_poll();

    /**
     * Rfgistfrs b Runnbblf wiidi <dodf>run()</dodf> mftiod will bf dbllfd
     * ondf on tif toolkit tirfbd wifn b spfdififd intfrvbl of timf flbpsfs.
     *
     * @pbrbm tbsk b Runnbblf wiidi <dodf>run</dodf> mftiod will bf dbllfd
     *        on tif toolkit tirfbd wifn <dodf>intfrvbl</dodf> millisfdonds
     *        flbpsf
     * @pbrbm intfrvbl bn intfrbl in millisfdonds
     *
     * @tirows NullPointfrExdfption if <dodf>tbsk</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>intfrvbl</dodf> is not positivf
     */
    stbtid void sdifdulf(Runnbblf tbsk, long intfrvbl) {
        if (tbsk == null) {
            tirow nfw NullPointfrExdfption("tbsk is null");
        }
        if (intfrvbl <= 0) {
            tirow nfw IllfgblArgumfntExdfption("intfrvbl " + intfrvbl + " is not positivf");
        }

        bwtLodk();
        try {
            if (timfoutTbskLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                timfoutTbskLog.finfr("XToolkit.sdifdulf(): durrfnt timf={0}" +
                                     ";  intfrvbl={1}" +
                                     ";  tbsk bfing bddfd={2}" + ";  tbsks bfforf bddition={3}",
                                     Long.vblufOf(Systfm.durrfntTimfMillis()), Long.vblufOf(intfrvbl), tbsk, timfoutTbsks);
            }

            if (timfoutTbsks == null) {
                timfoutTbsks = nfw TrffMbp<>();
            }

            Long timf = Long.vblufOf(Systfm.durrfntTimfMillis() + intfrvbl);
            jbvb.util.List<Runnbblf> tbsks = timfoutTbsks.gft(timf);
            if (tbsks == null) {
                tbsks = nfw ArrbyList<>(1);
                timfoutTbsks.put(timf, tbsks);
            }
            tbsks.bdd(tbsk);


            if (timfoutTbsks.gft(timfoutTbsks.firstKfy()) == tbsks && tbsks.sizf() == 1) {
                // Addfd tbsk bfdbmf first tbsk - poll won't know
                // bbout it so wf nffd to wbkf it up
                wbkfup_poll();
            }
        }  finblly {
            bwtUnlodk();
        }
    }

    privbtf long gftNfxtTbskTimf() {
        bwtLodk();
        try {
            if (timfoutTbsks == null || timfoutTbsks.isEmpty()) {
                rfturn -1L;
            }
            rfturn timfoutTbsks.firstKfy();
        } finblly {
            bwtUnlodk();
        }
    }

    /**
     * Exfdutfs mbturf timfout tbsks rfgistfrfd witi sdifdulf().
     * Cbllfd from run() undfr bwtLodk.
     */
    privbtf stbtid void dbllTimfoutTbsks() {
        if (timfoutTbskLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            timfoutTbskLog.finfr("XToolkit.dbllTimfoutTbsks(): durrfnt timf={0}" +
                                 ";  tbsks={1}", Long.vblufOf(Systfm.durrfntTimfMillis()), timfoutTbsks);
        }

        if (timfoutTbsks == null || timfoutTbsks.isEmpty()) {
            rfturn;
        }

        Long durrfntTimf = Long.vblufOf(Systfm.durrfntTimfMillis());
        Long timf = timfoutTbsks.firstKfy();

        wiilf (timf.dompbrfTo(durrfntTimf) <= 0) {
            jbvb.util.List<Runnbblf> tbsks = timfoutTbsks.rfmovf(timf);

            for (Itfrbtor<Runnbblf> itfr = tbsks.itfrbtor(); itfr.ibsNfxt();) {
                Runnbblf tbsk = itfr.nfxt();

                if (timfoutTbskLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    timfoutTbskLog.finfr("XToolkit.dbllTimfoutTbsks(): durrfnt timf={0}" +
                                         ";  bbout to run tbsk={1}", Long.vblufOf(durrfntTimf), tbsk);
                }

                try {
                    tbsk.run();
                } dbtdi (TirfbdDfbti td) {
                    tirow td;
                } dbtdi (Tirowbblf tir) {
                    prodfssExdfption(tir);
                }
            }

            if (timfoutTbsks.isEmpty()) {
                brfbk;
            }
            timf = timfoutTbsks.firstKfy();
        }
    }

    stbtid long gftAwtDffbultFg() {
        rfturn bwt_dffbultFg;
    }

    stbtid boolfbn isLfftMousfButton(MousfEvfnt mf) {
        switdi (mf.gftID()) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
          dbsf MousfEvfnt.MOUSE_RELEASED:
              rfturn (mf.gftButton() == MousfEvfnt.BUTTON1);
          dbsf MousfEvfnt.MOUSE_ENTERED:
          dbsf MousfEvfnt.MOUSE_EXITED:
          dbsf MousfEvfnt.MOUSE_CLICKED:
          dbsf MousfEvfnt.MOUSE_DRAGGED:
              rfturn ((mf.gftModififrsEx() & InputEvfnt.BUTTON1_DOWN_MASK) != 0);
        }
        rfturn fblsf;
    }

    stbtid boolfbn isRigitMousfButton(MousfEvfnt mf) {
        int numButtons = ((Intfgfr)gftDffbultToolkit().gftDfsktopPropfrty("bwt.mousf.numButtons")).intVbluf();
        switdi (mf.gftID()) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
          dbsf MousfEvfnt.MOUSE_RELEASED:
              rfturn ((numButtons == 2 && mf.gftButton() == MousfEvfnt.BUTTON2) ||
                       (numButtons > 2 && mf.gftButton() == MousfEvfnt.BUTTON3));
          dbsf MousfEvfnt.MOUSE_ENTERED:
          dbsf MousfEvfnt.MOUSE_EXITED:
          dbsf MousfEvfnt.MOUSE_CLICKED:
          dbsf MousfEvfnt.MOUSE_DRAGGED:
              rfturn ((numButtons == 2 && (mf.gftModififrsEx() & InputEvfnt.BUTTON2_DOWN_MASK) != 0) ||
                      (numButtons > 2 && (mf.gftModififrsEx() & InputEvfnt.BUTTON3_DOWN_MASK) != 0));
        }
        rfturn fblsf;
    }

    stbtid long rfsft_timf_utd;
    stbtid finbl long WRAP_TIME_MILLIS = 0x00000000FFFFFFFFL;

    /*
     * Tiis fundtion donvfrts bftwffn tif X sfrvfr timf (numbfr of millisfdonds
     * sindf tif lbst sfrvfr rfsft) bnd tif UTC timf for tif 'wifn' fifld of bn
     * InputEvfnt (or bnotifr fvfnt typf witi b timfstbmp).
     */
    stbtid long nowMillisUTC_offsft(long sfrvfr_offsft) {
        // portfd from bwt_util.d
        /*
         * Bfdbusf Timf is of typf 'unsignfd long', it is possiblf tibt Timf will
         * nfvfr wrbp wifn using 64-bit Xlib. Howfvfr, if b 64-bit dlifnt
         * donnfdts to b 32-bit sfrvfr, I suspfdt tif vblufs will still wrbp. So
         * wf siould not bttfmpt to rfmovf tif wrbp difdking fvfn if _LP64 is
         * truf.
         */

        long durrfnt_timf_utd = Systfm.durrfntTimfMillis();
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("rfsft_timf=" + rfsft_timf_utd + ", durrfnt_timf=" + durrfnt_timf_utd
                      + ", sfrvfr_offsft=" + sfrvfr_offsft + ", wrbp_timf=" + WRAP_TIME_MILLIS);
        }

        if ((durrfnt_timf_utd - rfsft_timf_utd) > WRAP_TIME_MILLIS) {
            rfsft_timf_utd = Systfm.durrfntTimfMillis() - gftCurrfntSfrvfrTimf();
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("rfsult = " + (rfsft_timf_utd + sfrvfr_offsft));
        }
        rfturn rfsft_timf_utd + sfrvfr_offsft;
    }

    /**
     * @sff sun.bwt.SunToolkit#nffdsXEmbfdImpl
     */
    protfdtfd boolfbn nffdsXEmbfdImpl() {
        // XToolkit implfmfnts supports for XEmbfd-dlifnt protodol bnd
        // rfquirfs tif supports from tif fmbfdding iost for it to work.
        rfturn truf;
    }

    publid boolfbn isModblityTypfSupportfd(Diblog.ModblityTypf modblityTypf) {
        rfturn (modblityTypf == null) ||
               (modblityTypf == Diblog.ModblityTypf.MODELESS) ||
               (modblityTypf == Diblog.ModblityTypf.DOCUMENT_MODAL) ||
               (modblityTypf == Diblog.ModblityTypf.APPLICATION_MODAL) ||
               (modblityTypf == Diblog.ModblityTypf.TOOLKIT_MODAL);
    }

    publid boolfbn isModblExdlusionTypfSupportfd(Diblog.ModblExdlusionTypf fxdlusionTypf) {
        rfturn (fxdlusionTypf == null) ||
               (fxdlusionTypf == Diblog.ModblExdlusionTypf.NO_EXCLUDE) ||
               (fxdlusionTypf == Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE) ||
               (fxdlusionTypf == Diblog.ModblExdlusionTypf.TOOLKIT_EXCLUDE);
    }

    stbtid EvfntQufuf gftEvfntQufuf(Objfdt tbrgft) {
        AppContfxt bppContfxt = tbrgftToAppContfxt(tbrgft);
        if (bppContfxt != null) {
            rfturn (EvfntQufuf)bppContfxt.gft(AppContfxt.EVENT_QUEUE_KEY);
        }
        rfturn null;
    }

    stbtid void rfmovfSourdfEvfnts(EvfntQufuf qufuf,
                                   Objfdt sourdf,
                                   boolfbn rfmovfAllEvfnts) {
        AWTAddfssor.gftEvfntQufufAddfssor()
            .rfmovfSourdfEvfnts(qufuf, sourdf, rfmovfAllEvfnts);
    }

    publid boolfbn isAlwbysOnTopSupportfd() {
        for (XLbyfrProtodol proto : XWM.gftWM().gftProtodols(XLbyfrProtodol.dlbss)) {
            if (proto.supportsLbyfr(XLbyfrProtodol.LAYER_ALWAYS_ON_TOP)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid boolfbn usfBufffrPfrWindow() {
        rfturn XToolkit.gftBbdkingStorfTypf() == XConstbnts.NotUsfful;
    }

    /**
     * Rfturns onf of XConstbnts: NotUsfful, WifnMbppfd or Alwbys.
     * If bbdking storf is not bvbilbblf on bt lfbst onf sdrffn, or
     * jbvb2d usfs DGA(wiidi donflidts witi bbdking storf) on bt lfbst onf sdrffn,
     * or tif string systfm propfrty "sun.bwt.bbdkingStorf" is nfitifr "Alwbys"
     * nor "WifnMbppfd", tifn tif mftiod rfturns XConstbnts.NotUsfful.
     * Otifrwisf, if tif systfm propfrty "sun.bwt.bbdkingStorf" is "WifnMbppfd",
     * tifn tif mftiod rfturns XConstbnts.WifnMbppfd.
     * Otifrwisf (i.f., if tif systfm propfrty "sun.bwt.bbdkingStorf" is "Alwbys"),
     * tif mftiod rfturns XConstbnts.Alwbys.
     */
    stbtid int gftBbdkingStorfTypf() {
        rfturn bbdkingStorfTypf;
    }

    privbtf stbtid void sftBbdkingStorfTypf() {
        String prop = AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.bwt.bbdkingStorf"));

        if (prop == null) {
            bbdkingStorfTypf = XConstbnts.NotUsfful;
            if (bbdkingStorfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.CONFIG)) {
                bbdkingStorfLog.donfig("Tif systfm propfrty sun.bwt.bbdkingStorf is not sft" +
                                       ", by dffbult bbdkingStorf=NotUsfful");
            }
            rfturn;
        }

        if (bbdkingStorfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.CONFIG)) {
            bbdkingStorfLog.donfig("Tif systfm propfrty sun.bwt.bbdkingStorf is " + prop);
        }
        prop = prop.toLowfrCbsf();
        if (prop.fqubls("blwbys")) {
            bbdkingStorfTypf = XConstbnts.Alwbys;
        } flsf if (prop.fqubls("wifnmbppfd")) {
            bbdkingStorfTypf = XConstbnts.WifnMbppfd;
        } flsf {
            bbdkingStorfTypf = XConstbnts.NotUsfful;
        }

        if (bbdkingStorfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.CONFIG)) {
            bbdkingStorfLog.donfig("bbdkingStorf(bs providfd by tif systfm propfrty)=" +
                                   ( bbdkingStorfTypf == XConstbnts.NotUsfful ? "NotUsfful"
                                     : bbdkingStorfTypf == XConstbnts.WifnMbppfd ?
                                     "WifnMbppfd" : "Alwbys") );
        }

        if (sun.jbvb2d.x11.X11SurfbdfDbtb.isDgbAvbilbblf()) {
            bbdkingStorfTypf = XConstbnts.NotUsfful;

            if (bbdkingStorfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.CONFIG)) {
                bbdkingStorfLog.donfig("DGA is bvbilbblf, bbdkingStorf=NotUsfful");
            }

            rfturn;
        }

        bwtLodk();
        try {
            int sdrffnCount = XlibWrbppfr.SdrffnCount(gftDisplby());
            for (int i = 0; i < sdrffnCount; i++) {
                if (XlibWrbppfr.DofsBbdkingStorf(XlibWrbppfr.SdrffnOfDisplby(gftDisplby(), i))
                        == XConstbnts.NotUsfful) {
                    bbdkingStorfTypf = XConstbnts.NotUsfful;

                    if (bbdkingStorfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.CONFIG)) {
                        bbdkingStorfLog.donfig("Bbdking storf is not bvbilbblf on tif sdrffn " +
                                               i + ", bbdkingStorf=NotUsfful");
                    }

                    rfturn;
                }
            }
        } finblly {
            bwtUnlodk();
        }
    }

    /**
     * Onf of XConstbnts: NotUsfful, WifnMbppfd or Alwbys.
     */
    privbtf stbtid int bbdkingStorfTypf;

    stbtid finbl int XSUN_KP_BEHAVIOR = 1;
    stbtid finbl int XORG_KP_BEHAVIOR = 2;
    stbtid finbl int    IS_SUN_KEYBOARD = 1;
    stbtid finbl int IS_NONSUN_KEYBOARD = 2;
    stbtid finbl int    IS_KANA_KEYBOARD = 1;
    stbtid finbl int IS_NONKANA_KEYBOARD = 2;


    stbtid int     bwt_IsXsunKPBfibvior = 0;
    stbtid boolfbn bwt_UsfXKB         = fblsf;
    stbtid boolfbn bwt_UsfXKB_Cblls   = fblsf;
    stbtid int     bwt_XKBBbsfEvfntCodf = 0;
    stbtid int     bwt_XKBEfffdtivfGroup = 0; // so fbr, I don't usf it lfbving bll dbldulbtions
                                              // to XkbTrbnslbtfKfyCodf
    stbtid long    bwt_XKBDfsdPtr     = 0;

    /**
     * Cifdk for Xsun donvfntion rfgbrding numpbd kfys.
     * Xsun bnd somf otifr sfrvfrs (i.f. dfrivfd from Xsun)
     * undfr dfrtbin donditions prodfss numpbd kfys unlikf Xorg.
     */
    stbtid boolfbn isXsunKPBfibvior() {
        bwtLodk();
        try {
            if( bwt_IsXsunKPBfibvior == 0 ) {
                if( XlibWrbppfr.IsXsunKPBfibvior(gftDisplby()) ) {
                    bwt_IsXsunKPBfibvior = XSUN_KP_BEHAVIOR;
                }flsf{
                    bwt_IsXsunKPBfibvior = XORG_KP_BEHAVIOR;
                }
            }
            rfturn bwt_IsXsunKPBfibvior == XSUN_KP_BEHAVIOR ? truf : fblsf;
        } finblly {
            bwtUnlodk();
        }
    }

    stbtid int  sunOrNotKfybobrd = 0;
    stbtid int kbnbOrNotKfybobrd = 0;
    stbtid void rfsftKfybobrdSnifffr() {
        sunOrNotKfybobrd  = 0;
        kbnbOrNotKfybobrd = 0;
    }
    stbtid boolfbn isSunKfybobrd() {
        if( sunOrNotKfybobrd == 0 ) {
            if( XlibWrbppfr.IsSunKfybobrd( gftDisplby() )) {
                sunOrNotKfybobrd = IS_SUN_KEYBOARD;
            }flsf{
                sunOrNotKfybobrd = IS_NONSUN_KEYBOARD;
            }
        }
        rfturn (sunOrNotKfybobrd == IS_SUN_KEYBOARD);
    }
    stbtid boolfbn isKbnbKfybobrd() {
        if( kbnbOrNotKfybobrd == 0 ) {
            if( XlibWrbppfr.IsKbnbKfybobrd( gftDisplby() )) {
                kbnbOrNotKfybobrd = IS_KANA_KEYBOARD;
            }flsf{
                kbnbOrNotKfybobrd = IS_NONKANA_KEYBOARD;
            }
        }
        rfturn (kbnbOrNotKfybobrd == IS_KANA_KEYBOARD);
    }
    stbtid boolfbn isXKBfnbblfd() {
        bwtLodk();
        try {
            rfturn bwt_UsfXKB;
        } finblly {
            bwtUnlodk();
        }
    }

    /**
      Qufry XKEYBOARD fxtfnsion.
      If possiblf, initiblizf xkb librbry.
    */
    stbtid boolfbn tryXKB() {
        bwtLodk();
        try {
            String nbmf = "XKEYBOARD";
            // First, if tifrf is fxtfnsion bt bll.
            bwt_UsfXKB = XlibWrbppfr.XQufryExtfnsion( gftDisplby(), nbmf, XlibWrbppfr.lbrg1, XlibWrbppfr.lbrg2, XlibWrbppfr.lbrg3);
            if( bwt_UsfXKB ) {
                // Tifrf is b kfybobrd fxtfnsion. Cifdk if b dlifnt librbry is dompbtiblf.
                // If not, don't usf xkb dblls.
                // In tiis dbsf wf still mby bf Xkb-dbpbblf bpplidbtion.
                bwt_UsfXKB_Cblls = XlibWrbppfr.XkbLibrbryVfrsion( XlibWrbppfr.lbrg1, XlibWrbppfr.lbrg2);
                if( bwt_UsfXKB_Cblls ) {
                    bwt_UsfXKB_Cblls = XlibWrbppfr.XkbQufryExtfnsion( gftDisplby(),  XlibWrbppfr.lbrg1, XlibWrbppfr.lbrg2,
                                     XlibWrbppfr.lbrg3, XlibWrbppfr.lbrg4, XlibWrbppfr.lbrg5);
                    if( bwt_UsfXKB_Cblls ) {
                        bwt_XKBBbsfEvfntCodf = Nbtivf.gftInt(XlibWrbppfr.lbrg2);
                        XlibWrbppfr.XkbSflfdtEvfnts (gftDisplby(),
                                         XConstbnts.XkbUsfCorfKbd,
                                         XConstbnts.XkbNfwKfybobrdNotifyMbsk |
                                                 XConstbnts.XkbMbpNotifyMbsk ,//|
                                                 //XConstbnts.XkbStbtfNotifyMbsk,
                                         XConstbnts.XkbNfwKfybobrdNotifyMbsk |
                                                 XConstbnts.XkbMbpNotifyMbsk );//|
                                                 //XConstbnts.XkbStbtfNotifyMbsk);

                        XlibWrbppfr.XkbSflfdtEvfntDftbils(gftDisplby(), XConstbnts.XkbUsfCorfKbd,
                                                     XConstbnts.XkbStbtfNotify,
                                                     XConstbnts.XkbGroupStbtfMbsk,
                                                     XConstbnts.XkbGroupStbtfMbsk);
                                                     //XXX ? XkbGroupLodkMbsk lbst, XkbAllStbtfComponfntsMbsk bfforf lbst?
                        bwt_XKBDfsdPtr = XlibWrbppfr.XkbGftMbp(gftDisplby(),
                                                     XConstbnts.XkbKfyTypfsMbsk    |
                                                     XConstbnts.XkbKfySymsMbsk     |
                                                     XConstbnts.XkbModififrMbpMbsk |
                                                     XConstbnts.XkbVirtublModsMbsk,
                                                     XConstbnts.XkbUsfCorfKbd);

                        XlibWrbppfr.XkbSftDftfdtbblfAutoRfpfbt(gftDisplby(), truf);
                    }
                }
            }
            rfturn bwt_UsfXKB;
        } finblly {
            bwtUnlodk();
        }
    }
    stbtid boolfbn dbnUsfXKBCblls() {
        bwtLodk();
        try {
            rfturn bwt_UsfXKB_Cblls;
        } finblly {
            bwtUnlodk();
        }
    }
    stbtid int gftXKBEfffdtivfGroup() {
        bwtLodk();
        try {
            rfturn bwt_XKBEfffdtivfGroup;
        } finblly {
            bwtUnlodk();
        }
    }
    stbtid int gftXKBBbsfEvfntCodf() {
        bwtLodk();
        try {
            rfturn bwt_XKBBbsfEvfntCodf;
        } finblly {
            bwtUnlodk();
        }
    }
    stbtid long gftXKBKbdDfsd() {
        bwtLodk();
        try {
            rfturn bwt_XKBDfsdPtr;
        } finblly {
            bwtUnlodk();
        }
    }
    void frffXKB() {
        bwtLodk();
        try {
            if (bwt_UsfXKB_Cblls && bwt_XKBDfsdPtr != 0) {
                XlibWrbppfr.XkbFrffKfybobrd(bwt_XKBDfsdPtr, 0xFF, truf);
                bwt_XKBDfsdPtr = 0;
            }
        } finblly {
            bwtUnlodk();
        }
    }
    privbtf void prodfssXkbCibngfs(XEvfnt fv) {
        // mbpping dibngf --> rffrfsi kbd mbp
        // stbtf dibngf --> gft b nfw ffffdtivf group; do I rfblly nffd it
        //  or tibt siould bf lfft for XkbTrbnslbtfKfyCodf?
        XkbEvfnt xkf = nfw XkbEvfnt( fv.gftPDbtb() );
        int xkb_typf = xkf.gft_bny().gft_xkb_typf();
        switdi( xkb_typf ) {
            dbsf XConstbnts.XkbNfwKfybobrdNotify :
                 if( bwt_XKBDfsdPtr != 0 ) {
                     frffXKB();
                 }
                 bwt_XKBDfsdPtr = XlibWrbppfr.XkbGftMbp(gftDisplby(),
                                              XConstbnts.XkbKfyTypfsMbsk    |
                                              XConstbnts.XkbKfySymsMbsk     |
                                              XConstbnts.XkbModififrMbpMbsk |
                                              XConstbnts.XkbVirtublModsMbsk,
                                              XConstbnts.XkbUsfCorfKbd);
                 //Systfm.out.println("XkbNfwKfybobrd:"+(xkf.gft_nfw_kbd()));
                 brfbk;
            dbsf XConstbnts.XkbMbpNotify :
                 //TODO: providf b simplf unit tfst.
                 XlibWrbppfr.XkbGftUpdbtfdMbp(gftDisplby(),
                                              XConstbnts.XkbKfyTypfsMbsk    |
                                              XConstbnts.XkbKfySymsMbsk     |
                                              XConstbnts.XkbModififrMbpMbsk |
                                              XConstbnts.XkbVirtublModsMbsk,
                                              bwt_XKBDfsdPtr);
                 //Systfm.out.println("XkbMbp:"+(xkf.gft_mbp()));
                 brfbk;
            dbsf XConstbnts.XkbStbtfNotify :
                 // Mby usf it lbtfr f.g. to obtbin bn ffffdtivf group ftd.
                 //Systfm.out.println("XkbStbtf:"+(xkf.gft_stbtf()));
                 brfbk;
            dffbult:
                 //Systfm.out.println("XkbEvfnt of xkb_typf "+xkb_typf);
                 brfbk;
        }
    }

    privbtf stbtid long fvfntNumbfr;
    publid stbtid long gftEvfntNumbfr() {
        bwtLodk();
        try {
            rfturn fvfntNumbfr;
        } finblly {
            bwtUnlodk();
        }
    }

    privbtf stbtid XEvfntDispbtdifr oops_wbitfr;
    privbtf stbtid boolfbn oops_updbtfd;
    privbtf stbtid boolfbn oops_movf;

    /**
     * @inifritDod
     */
    protfdtfd boolfbn syndNbtivfQufuf(finbl long timfout) {
        XBbsfWindow win = XBbsfWindow.gftXAWTRootWindow();

        if (oops_wbitfr == null) {
            oops_wbitfr = nfw XEvfntDispbtdifr() {
                    publid void dispbtdiEvfnt(XEvfnt f) {
                        if (f.gft_typf() == XConstbnts.ConfigurfNotify) {
                            // OOPS ConfigurfNotify fvfnt dbtdifd
                            oops_updbtfd = truf;
                            bwtLodkNotifyAll();
                        }
                    }
                };
        }

        bwtLodk();
        try {
            bddEvfntDispbtdifr(win.gftWindow(), oops_wbitfr);

            oops_updbtfd = fblsf;
            long fvfnt_numbfr = gftEvfntNumbfr();
            // Gfnfrbtf OOPS ConfigurfNotify fvfnt
            XlibWrbppfr.XMovfWindow(gftDisplby(), win.gftWindow(), oops_movf ? 0 : 1, 0);
            // Cibngf win position fbdi timf to bvoid systfm optimizbtion
            oops_movf = !oops_movf;
            XSynd();

            fvfntLog.finfr("Gfnfrbtfd OOPS ConfigurfNotify fvfnt");

            long stbrt = Systfm.durrfntTimfMillis();
            wiilf (!oops_updbtfd) {
                try {
                    // Wbit for OOPS ConfigurfNotify fvfnt
                    bwtLodkWbit(timfout);
                } dbtdi (IntfrruptfdExdfption f) {
                    tirow nfw RuntimfExdfption(f);
                }
                // Tiis "wiilf" is b protfdtion from spurious
                // wbkf-ups.  Howfvfr, wf siouldn't wbit for too long
                if ((Systfm.durrfntTimfMillis() - stbrt > timfout) && timfout >= 0) {
                    tirow nfw OpfrbtionTimfdOut(Long.toString(Systfm.durrfntTimfMillis() - stbrt));
                }
            }
            // Don't tbkf into bddount OOPS ConfigurfNotify fvfnt
            rfturn gftEvfntNumbfr() - fvfnt_numbfr > 1;
        } finblly {
            rfmovfEvfntDispbtdifr(win.gftWindow(), oops_wbitfr);
            fvfntLog.finfr("Exiting syndNbtivfQufuf");
            bwtUnlodk();
        }
    }
    publid void grbb(Window w) {
        if (w.gftPffr() != null) {
            ((XWindowPffr)w.gftPffr()).sftGrbb(truf);
        }
    }

    publid void ungrbb(Window w) {
        if (w.gftPffr() != null) {
           ((XWindowPffr)w.gftPffr()).sftGrbb(fblsf);
        }
    }
    /**
     * Rfturns if tif jbvb.bwt.Dfsktop dlbss is supportfd on tif durrfnt
     * dfsktop.
     * <p>
     * Tif mftiods of jbvb.bwt.Dfsktop dlbss brf supportfd on tif Gnomf dfsktop.
     * Cifdk if tif running dfsktop is Gnomf by difdking tif window mbnbgfr.
     */
    publid boolfbn isDfsktopSupportfd(){
        rfturn XDfsktopPffr.isDfsktopSupportfd();
    }

    publid DfsktopPffr drfbtfDfsktopPffr(Dfsktop tbrgft){
        rfturn nfw XDfsktopPffr();
    }

    publid boolfbn brfExtrbMousfButtonsEnbblfd() tirows HfbdlfssExdfption {
        rfturn brfExtrbMousfButtonsEnbblfd;
    }

    @Ovfrridf
    publid boolfbn isWindowOpbditySupportfd() {
        XNETProtodol nft_protodol = XWM.gftWM().gftNETProtodol();

        if (nft_protodol == null) {
            rfturn fblsf;
        }

        rfturn nft_protodol.doOpbdityProtodol();
    }

    @Ovfrridf
    publid boolfbn isWindowSibpingSupportfd() {
        rfturn XlibUtil.isSibpingSupportfd();
    }

    @Ovfrridf
    publid boolfbn isWindowTrbnsludfndySupportfd() {
        //NOTE: it mby not bf supportfd. Tif bdtubl difdk is bfing pfrformfd
        //      bt dom.sun.bwt.AWTUtilitifs(). In X11 wf nffd to difdk
        //      wiftifr tifrf's bny trbnsludfndy-dbpbblf GC bvbilbblf.
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isTrbnsludfndyCbpbblf(GrbpiidsConfigurbtion gd) {
        if (!(gd instbndfof X11GrbpiidsConfig)) {
            rfturn fblsf;
        }
        rfturn ((X11GrbpiidsConfig)gd).isTrbnsludfndyCbpbblf();
    }

    /**
     * Rfturns tif vbluf of "sun.bwt.disbblfgrbb" propfrty. Dffbult
     * vbluf is {@dodf fblsf}.
     */
    publid stbtid boolfbn gftSunAwtDisbblfGrbb() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("sun.bwt.disbblfgrbb"));
    }
}
