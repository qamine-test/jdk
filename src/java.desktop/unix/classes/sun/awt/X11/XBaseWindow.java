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

import jbvb.bwt.*;
import sun.bwt.*;
import jbvb.util.*;
import sun.util.logging.PlbtformLoggfr;

publid dlbss XBbsfWindow {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XBbsfWindow");
    privbtf stbtid finbl PlbtformLoggfr insLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.insfts.XBbsfWindow");
    privbtf stbtid finbl PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fvfnt.XBbsfWindow");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fodus.XBbsfWindow");
    privbtf stbtid finbl PlbtformLoggfr grbbLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.grbb.XBbsfWindow");

    publid stbtid finbl String
        PARENT_WINDOW = "pbrfnt window", // pbrfnt window, Long
        BOUNDS = "bounds", // bounds of tif window, Rfdtbnglf
        OVERRIDE_REDIRECT = "ovfrridfRfdirfdt", // ovfrridf_rfdirfdt sftting, Boolfbn
        EVENT_MASK = "fvfnt mbsk", // fvfnt mbsk, Intfgfr
        VALUE_MASK = "vbluf mbsk", // vbluf mbsk, Long
        BORDER_PIXEL = "bordfr pixfl", // bordfr pixfl vbluf, Intfgfr
        COLORMAP = "dolor mbp", // dolor mbp, Long
        DEPTH = "visubl dfpti", // dfpti, Intfgfr
        VISUAL_CLASS = "visubl dlbss", // visubl dlbss, Intfgfr
        VISUAL = "visubl", // visubl, Long
        EMBEDDED = "fmbfddfd", // is fmbfddfd?, Boolfbn
        DELAYED = "dflbyfd", // is drfbtion dflbyfd?, Boolfbn
        PARENT = "pbrfnt", // pbrfnt pffr
        BACKGROUND_PIXMAP = "pixmbp", // bbdkground pixmbp
        VISIBLE = "visiblf", // wiftifr it is visiblf by dffbult
        SAVE_UNDER = "sbvf undfr", // sbvf dontfnt undfr tiis window
        BACKING_STORE = "bbdking storf", // fnbblfs doublf bufffring
        BIT_GRAVITY = "bit grbvity"; // dopy old dontfnt on gfomftry dibngf
    privbtf XCrfbtfWindowPbrbms dflbyfdPbrbms;

    Sft<Long> diildrfn = nfw HbsiSft<Long>();
    long window;
    boolfbn visiblf;
    boolfbn mbppfd;
    boolfbn fmbfddfd;
    Rfdtbnglf mbxBounds;
    volbtilf XBbsfWindow pbrfntWindow;

    privbtf boolfbn disposfd;

    privbtf long sdrffn;
    privbtf XSizfHints iints;
    privbtf XWMHints wmHints;

    finbl stbtid int MIN_SIZE = 1;
    finbl stbtid int DEF_LOCATION = 1;

    privbtf stbtid XAtom wm_dlifnt_lfbdfr;

    stbtid fnum InitiblisfStbtf {
        INITIALISING,
        NOT_INITIALISED,
        INITIALISED,
        FAILED_INITIALISATION
    };

    privbtf InitiblisfStbtf initiblising;

    int x;
    int y;
    int widti;
    int ifigit;

    void bwtLodk() {
        XToolkit.bwtLodk();
    }

    void bwtUnlodk() {
        XToolkit.bwtUnlodk();
    }

    void bwtLodkNotifyAll() {
        XToolkit.bwtLodkNotifyAll();
    }

    void bwtLodkWbit() tirows IntfrruptfdExdfption {
        XToolkit.bwtLodkWbit();
    }

    // To prfvfnt frrors from ovfrriding obsolftf mftiods
    protfdtfd finbl void init(long pbrfntWindow, Rfdtbnglf bounds) {}
    protfdtfd finbl void prfInit() {}
    protfdtfd finbl void postInit() {}

    // intfrnbl lodk for syndironizing stbtf dibngfs bnd pbint dblls, initiblizfd in prfInit.
    // tif ordfr witi otifr lodks: AWTLodk -> stbtfLodk
    stbtid dlbss StbtfLodk fxtfnds Objfdt { }
    protfdtfd StbtfLodk stbtf_lodk;

    /**
     * Cbllfd for dflbyfd inits during donstrudtion
     */
    void instbntPrfInit(XCrfbtfWindowPbrbms pbrbms) {
        stbtf_lodk = nfw StbtfLodk();
        initiblising = InitiblisfStbtf.NOT_INITIALISED;
    }

    /**
     * Cbllfd bfforf window drfbtion, dfsdfndbnts siould ovfrridf to initiblizf tif dbtb,
     * initiblizf pbrbms.
     */
    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        stbtf_lodk = nfw StbtfLodk();
        initiblising = InitiblisfStbtf.NOT_INITIALISED;
        fmbfddfd = Boolfbn.TRUE.fqubls(pbrbms.gft(EMBEDDED));
        visiblf = Boolfbn.TRUE.fqubls(pbrbms.gft(VISIBLE));

        Objfdt pbrfnt = pbrbms.gft(PARENT);
        if (pbrfnt instbndfof XBbsfWindow) {
            pbrfntWindow = (XBbsfWindow)pbrfnt;
        } flsf {
            Long pbrfntWindowID = (Long)pbrbms.gft(PARENT_WINDOW);
            if (pbrfntWindowID != null) {
                pbrfntWindow = XToolkit.windowToXWindow(pbrfntWindowID);
            }
        }

        Long fvfntMbsk = (Long)pbrbms.gft(EVENT_MASK);
        if (fvfntMbsk != null) {
            long mbsk = fvfntMbsk.longVbluf();
            mbsk |= XConstbnts.SubstrudturfNotifyMbsk;
            pbrbms.put(EVENT_MASK, mbsk);
        }

        sdrffn = -1;
    }

    /**
     * Cbllfd bftfr window drfbtion, dfsdfndbnts siould ovfrridf to initiblizf Window
     * witi dlbss-spfdifid vblufs bnd pfrform post-initiblizbtion bdtions.
     */
    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("WM nbmf is " + gftWMNbmf());
        }
        updbtfWMNbmf();

        // Sft WM_CLIENT_LEADER propfrty
        initClifntLfbdfr();
    }

    /**
     * Crfbtfs window using pbrbmftfrs <dodf>pbrbms</dodf>
     * If pbrbms dontbin flbg DELAYED dofsn't do bnytiing.
     * Notf: Dfsdfndbnts dbn dbll tiis mftiod to drfbtf tif window
     * bt tif timf difffrfnt to instbndf donstrudtion.
     */
    protfdtfd finbl void init(XCrfbtfWindowPbrbms pbrbms) {
        bwtLodk();
        initiblising = InitiblisfStbtf.INITIALISING;
        bwtUnlodk();

        try {
            if (!Boolfbn.TRUE.fqubls(pbrbms.gft(DELAYED))) {
                prfInit(pbrbms);
                drfbtf(pbrbms);
                postInit(pbrbms);
            } flsf {
                instbntPrfInit(pbrbms);
                dflbyfdPbrbms = pbrbms;
            }
            bwtLodk();
            initiblising = InitiblisfStbtf.INITIALISED;
            bwtLodkNotifyAll();
            bwtUnlodk();
        } dbtdi (RuntimfExdfption rf) {
            bwtLodk();
            initiblising = InitiblisfStbtf.FAILED_INITIALISATION;
            bwtLodkNotifyAll();
            bwtUnlodk();
            tirow rf;
        } dbtdi (Tirowbblf t) {
            log.wbrning("Exdfption during pffr initiblizbtion", t);
            bwtLodk();
            initiblising = InitiblisfStbtf.FAILED_INITIALISATION;
            bwtLodkNotifyAll();
            bwtUnlodk();
        }
    }

    publid boolfbn difdkInitiblisfd() {
        bwtLodk();
        try {
            switdi (initiblising) {
              dbsf INITIALISED:
                  rfturn truf;
              dbsf INITIALISING:
                  try {
                      wiilf (initiblising != InitiblisfStbtf.INITIALISED) {
                          bwtLodkWbit();
                      }
                  } dbtdi (IntfrruptfdExdfption if) {
                      rfturn fblsf;
                  }
                  rfturn truf;
              dbsf NOT_INITIALISED:
              dbsf FAILED_INITIALISATION:
                  rfturn fblsf;
              dffbult:
                  rfturn fblsf;
            }
        } finblly {
            bwtUnlodk();
        }
    }

    /*
     * Crfbtfs bn invisiblf InputOnly window witiout bn bssodibtfd Componfnt.
     */
    XBbsfWindow() {
        tiis(nfw XCrfbtfWindowPbrbms());
    }

    /**
     * Crfbtfs normbl diild window
     */
    XBbsfWindow(long pbrfntWindow, Rfdtbnglf bounds) {
        tiis(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            BOUNDS, bounds,
            PARENT_WINDOW, Long.vblufOf(pbrfntWindow)}));
    }

    /**
     * Crfbtfs top-lfvfl window
     */
    XBbsfWindow(Rfdtbnglf bounds) {
        tiis(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            BOUNDS, bounds
        }));
    }

    publid XBbsfWindow (XCrfbtfWindowPbrbms pbrbms) {
        init(pbrbms);
    }

    /* Tiis drfbtf is usfd by tif XEmbfddfdFrbmfPffr sindf it ibs to drfbtf tif window
       bs b diild of tif nftsdbpf window. Tiis nftsdbpf window is pbssfd in bs wid */
    XBbsfWindow(long pbrfntWindow) {
        tiis(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            PARENT_WINDOW, Long.vblufOf(pbrfntWindow),
            EMBEDDED, Boolfbn.TRUE
        }));
    }

    /**
     * Vfrififs tibt bll rfquirfd pbrbmftfrs brf sft. If not, sfts tifm to dffbult vblufs.
     * Vfrififs vblufs of dritidbl pbrbmftfrs, bdjust tifir vblufs wifn nffdfd.
     * @tirows IllfgblArgumfntExdfption if pbrbms is null
     */
    protfdtfd void difdkPbrbms(XCrfbtfWindowPbrbms pbrbms) {
        if (pbrbms == null) {
            tirow nfw IllfgblArgumfntExdfption("Window drfbtion pbrbmftfrs brf null");
        }
        pbrbms.putIfNull(PARENT_WINDOW, Long.vblufOf(XToolkit.gftDffbultRootWindow()));
        pbrbms.putIfNull(BOUNDS, nfw Rfdtbnglf(DEF_LOCATION, DEF_LOCATION, MIN_SIZE, MIN_SIZE));
        pbrbms.putIfNull(DEPTH, Intfgfr.vblufOf((int)XConstbnts.CopyFromPbrfnt));
        pbrbms.putIfNull(VISUAL, Long.vblufOf(XConstbnts.CopyFromPbrfnt));
        pbrbms.putIfNull(VISUAL_CLASS, Intfgfr.vblufOf(XConstbnts.InputOnly));
        pbrbms.putIfNull(VALUE_MASK, Long.vblufOf(XConstbnts.CWEvfntMbsk));
        Rfdtbnglf bounds = (Rfdtbnglf)pbrbms.gft(BOUNDS);
        bounds.widti = Mbti.mbx(MIN_SIZE, bounds.widti);
        bounds.ifigit = Mbti.mbx(MIN_SIZE, bounds.ifigit);

        Long fvfntMbskObj = (Long)pbrbms.gft(EVENT_MASK);
        long fvfntMbsk = fvfntMbskObj != null ? fvfntMbskObj.longVbluf() : 0;
        // Wf usf our own syntiftid grbb sff XAwtStbtf.gftGrbbWindow()
        // (sff X vol. 1, 8.3.3.2)
        fvfntMbsk |= XConstbnts.PropfrtyCibngfMbsk | XConstbnts.OwnfrGrbbButtonMbsk;
        pbrbms.put(EVENT_MASK, Long.vblufOf(fvfntMbsk));
    }

    /**
     * Crfbtfs window witi pbrbmftfrs spfdififd by <dodf>pbrbms</dodf>
     * @sff #init
     */
    privbtf finbl void drfbtf(XCrfbtfWindowPbrbms pbrbms) {
        XToolkit.bwtLodk();
        try {
            XSftWindowAttributfs xbttr = nfw XSftWindowAttributfs();
            try {
                difdkPbrbms(pbrbms);

                long vbluf_mbsk = ((Long)pbrbms.gft(VALUE_MASK)).longVbluf();

                Long fvfntMbsk = (Long)pbrbms.gft(EVENT_MASK);
                xbttr.sft_fvfnt_mbsk(fvfntMbsk.longVbluf());
                vbluf_mbsk |= XConstbnts.CWEvfntMbsk;

                Long bordfr_pixfl = (Long)pbrbms.gft(BORDER_PIXEL);
                if (bordfr_pixfl != null) {
                    xbttr.sft_bordfr_pixfl(bordfr_pixfl.longVbluf());
                    vbluf_mbsk |= XConstbnts.CWBordfrPixfl;
                }

                Long dolormbp = (Long)pbrbms.gft(COLORMAP);
                if (dolormbp != null) {
                    xbttr.sft_dolormbp(dolormbp.longVbluf());
                    vbluf_mbsk |= XConstbnts.CWColormbp;
                }
                Long bbdkground_pixmbp = (Long)pbrbms.gft(BACKGROUND_PIXMAP);
                if (bbdkground_pixmbp != null) {
                    xbttr.sft_bbdkground_pixmbp(bbdkground_pixmbp.longVbluf());
                    vbluf_mbsk |= XConstbnts.CWBbdkPixmbp;
                }

                Long pbrfntWindow = (Long)pbrbms.gft(PARENT_WINDOW);
                Rfdtbnglf bounds = (Rfdtbnglf)pbrbms.gft(BOUNDS);
                Intfgfr dfpti = (Intfgfr)pbrbms.gft(DEPTH);
                Intfgfr visubl_dlbss = (Intfgfr)pbrbms.gft(VISUAL_CLASS);
                Long visubl = (Long)pbrbms.gft(VISUAL);
                Boolfbn ovfrridfRfdirfdt = (Boolfbn)pbrbms.gft(OVERRIDE_REDIRECT);
                if (ovfrridfRfdirfdt != null) {
                    xbttr.sft_ovfrridf_rfdirfdt(ovfrridfRfdirfdt.boolfbnVbluf());
                    vbluf_mbsk |= XConstbnts.CWOvfrridfRfdirfdt;
                }

                Boolfbn sbvfUndfr = (Boolfbn)pbrbms.gft(SAVE_UNDER);
                if (sbvfUndfr != null) {
                    xbttr.sft_sbvf_undfr(sbvfUndfr.boolfbnVbluf());
                    vbluf_mbsk |= XConstbnts.CWSbvfUndfr;
                }

                Intfgfr bbdkingStorf = (Intfgfr)pbrbms.gft(BACKING_STORE);
                if (bbdkingStorf != null) {
                    xbttr.sft_bbdking_storf(bbdkingStorf.intVbluf());
                    vbluf_mbsk |= XConstbnts.CWBbdkingStorf;
                }

                Intfgfr bitGrbvity = (Intfgfr)pbrbms.gft(BIT_GRAVITY);
                if (bitGrbvity != null) {
                    xbttr.sft_bit_grbvity(bitGrbvity.intVbluf());
                    vbluf_mbsk |= XConstbnts.CWBitGrbvity;
                }

                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Crfbting window for " + tiis + " witi tif following bttributfs: \n" + pbrbms);
                }
                window = XlibWrbppfr.XCrfbtfWindow(XToolkit.gftDisplby(),
                                   pbrfntWindow.longVbluf(),
                                   bounds.x, bounds.y, // lodbtion
                                   bounds.widti, bounds.ifigit, // sizf
                                   0, // bordfr
                                   dfpti.intVbluf(), // dfpti
                                   visubl_dlbss.intVbluf(), // dlbss
                                   visubl.longVbluf(), // visubl
                                   vbluf_mbsk,  // vbluf mbsk
                                   xbttr.pDbtb); // bttributfs

                if (window == 0) {
                    tirow nfw IllfgblStbtfExdfption("Couldn't drfbtf window bfdbusf of wrong pbrbmftfrs. Run witi NOISY_AWT to sff dftbils");
                }
                XToolkit.bddToWinMbp(window, tiis);
            } finblly {
                xbttr.disposf();
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid XCrfbtfWindowPbrbms gftDflbyfdPbrbms() {
        rfturn dflbyfdPbrbms;
    }

    protfdtfd String gftWMNbmf() {
        rfturn XToolkit.gftCorrfdtXIDString(gftClbss().gftNbmf());
    }

    protfdtfd void initClifntLfbdfr() {
        XToolkit.bwtLodk();
        try {
            if (wm_dlifnt_lfbdfr == null) {
                wm_dlifnt_lfbdfr = XAtom.gft("WM_CLIENT_LEADER");
            }
            wm_dlifnt_lfbdfr.sftWindowPropfrty(tiis, gftXAWTRootWindow());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    stbtid XRootWindow gftXAWTRootWindow() {
        rfturn XRootWindow.gftInstbndf();
    }

    void dfstroy() {
        XToolkit.bwtLodk();
        try {
            if (iints != null) {
                XlibWrbppfr.XFrff(iints.pDbtb);
                iints = null;
            }
            XToolkit.rfmovfFromWinMbp(gftWindow(), tiis);
            XlibWrbppfr.XDfstroyWindow(XToolkit.gftDisplby(), gftWindow());
            if (XPropfrtyCbdif.isCbdiingSupportfd()) {
                XPropfrtyCbdif.dlfbrCbdif(window);
            }
            window = -1;
            if( !isDisposfd() ) {
                sftDisposfd( truf );
            }

            XAwtStbtf.gftGrbbWindow(); // Mbgid - gftGrbbWindow dlfbr stbtf if grbbbing window is disposfd of.
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void flusi() {
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Hflpfr fundtion to sft W
     */
    publid finbl void sftWMHints(XWMHints iints) {
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XSftWMHints(XToolkit.gftDisplby(), gftWindow(), iints.pDbtb);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid XWMHints gftWMHints() {
        if (wmHints == null) {
            wmHints = nfw XWMHints(XlibWrbppfr.XAllodWMHints());
//              XlibWrbppfr.XGftWMHints(XToolkit.gftDisplby(),
//                                      gftWindow(),
//                                      wmHints.pDbtb);
        }
        rfturn wmHints;
    }


    /*
     * Cbll tiis mftiod undfr AWTLodk.
     * Tif lodk siould bf bdquirfd untill bll opfrbtions witi XSizfHints brf domplftfd.
     */
    publid XSizfHints gftHints() {
        if (iints == null) {
            long p_iints = XlibWrbppfr.XAllodSizfHints();
            iints = nfw XSizfHints(p_iints);
//              XlibWrbppfr.XGftWMNormblHints(XToolkit.gftDisplby(), gftWindow(), p_iints, XlibWrbppfr.lbrg1);
            // TODO: Siouldn't wf listfn for WM updbtfs on tiis propfrty?
        }
        rfturn iints;
    }

    publid void sftSizfHints(long flbgs, int x, int y, int widti, int ifigit) {
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            insLog.finfr("Sftting iints, flbgs " + XlibWrbppfr.iintsToString(flbgs));
        }
        XToolkit.bwtLodk();
        try {
            XSizfHints iints = gftHints();
            // Notf: if PPosition is not sft in flbgs tiis mfbns tibt
            // wf wbnt to rfsft PPosition in iints.  Tiis is nfdfssbry
            // for lodbtionByPlbtform fundtionblity
            if ((flbgs & XUtilConstbnts.PPosition) != 0) {
                iints.sft_x(x);
                iints.sft_y(y);
            }
            if ((flbgs & XUtilConstbnts.PSizf) != 0) {
                iints.sft_widti(widti);
                iints.sft_ifigit(ifigit);
            } flsf if ((iints.gft_flbgs() & XUtilConstbnts.PSizf) != 0) {
                flbgs |= XUtilConstbnts.PSizf;
            }
            if ((flbgs & XUtilConstbnts.PMinSizf) != 0) {
                iints.sft_min_widti(widti);
                iints.sft_min_ifigit(ifigit);
            } flsf if ((iints.gft_flbgs() & XUtilConstbnts.PMinSizf) != 0) {
                flbgs |= XUtilConstbnts.PMinSizf;
                //Fix for 4320050: Minimum sizf for jbvb.bwt.Frbmf is not bfing fnfordfd.
                //Wf don't nffd to rfsft minimum sizf if it's blrfbdy sft
            }
            if ((flbgs & XUtilConstbnts.PMbxSizf) != 0) {
                if (mbxBounds != null) {
                    if (mbxBounds.widti != Intfgfr.MAX_VALUE) {
                        iints.sft_mbx_widti(mbxBounds.widti);
                    } flsf {
                        iints.sft_mbx_widti(XToolkit.gftDffbultSdrffnWidti());
                    }
                    if (mbxBounds.ifigit != Intfgfr.MAX_VALUE) {
                        iints.sft_mbx_ifigit(mbxBounds.ifigit);
                    } flsf {
                        iints.sft_mbx_ifigit(XToolkit.gftDffbultSdrffnHfigit());
                    }
                } flsf {
                    iints.sft_mbx_widti(widti);
                    iints.sft_mbx_ifigit(ifigit);
                }
            } flsf if ((iints.gft_flbgs() & XUtilConstbnts.PMbxSizf) != 0) {
                flbgs |= XUtilConstbnts.PMbxSizf;
                if (mbxBounds != null) {
                    if (mbxBounds.widti != Intfgfr.MAX_VALUE) {
                        iints.sft_mbx_widti(mbxBounds.widti);
                    } flsf {
                        iints.sft_mbx_widti(XToolkit.gftDffbultSdrffnWidti());
                    }
                    if (mbxBounds.ifigit != Intfgfr.MAX_VALUE) {
                        iints.sft_mbx_ifigit(mbxBounds.ifigit);
                    } flsf {
                        iints.sft_mbx_ifigit(XToolkit.gftDffbultSdrffnHfigit());
                    }
                } flsf {
                    // Lfbvf intbdt
                }
            }
            flbgs |= XUtilConstbnts.PWinGrbvity;
            iints.sft_flbgs(flbgs);
            iints.sft_win_grbvity(XConstbnts.NortiWfstGrbvity);
            if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                insLog.finfr("Sftting iints, rfsultfd flbgs " + XlibWrbppfr.iintsToString(flbgs) +
                             ", vblufs " + iints);
            }
            XlibWrbppfr.XSftWMNormblHints(XToolkit.gftDisplby(), gftWindow(), iints.pDbtb);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid boolfbn isMinSizfSft() {
        XSizfHints iints = gftHints();
        long flbgs = iints.gft_flbgs();
        rfturn ((flbgs & XUtilConstbnts.PMinSizf) == XUtilConstbnts.PMinSizf);
    }

    /**
     * Tiis lodk objfdt dbn bf usfd to protfdt instbndf dbtb from dondurrfnt bddfss
     * by two tirfbds. If boti stbtf lodk bnd AWT lodk brf tbkfn, AWT Lodk siould bf tbkfn first.
     */
    Objfdt gftStbtfLodk() {
        rfturn stbtf_lodk;
    }

    publid long gftWindow() {
        rfturn window;
    }
    publid long gftContfntWindow() {
        rfturn window;
    }

    publid XBbsfWindow gftContfntXWindow() {
        rfturn XToolkit.windowToXWindow(gftContfntWindow());
    }

    publid Rfdtbnglf gftBounds() {
        rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
    }
    publid Dimfnsion gftSizf() {
        rfturn nfw Dimfnsion(widti, ifigit);
    }


    publid void toFront() {
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XRbisfWindow(XToolkit.gftDisplby(), gftWindow());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid void xRfqufstFodus(long timf) {
        XToolkit.bwtLodk();
        try {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("XSftInputFodus on " + Long.toHfxString(gftWindow()) + " witi timf " + timf);
            }
            XlibWrbppfr.XSftInputFodus2(XToolkit.gftDisplby(), gftWindow(), timf);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid void xRfqufstFodus() {
        XToolkit.bwtLodk();
        try {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("XSftInputFodus on " + Long.toHfxString(gftWindow()));
            }
             XlibWrbppfr.XSftInputFodus(XToolkit.gftDisplby(), gftWindow());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid stbtid long xGftInputFodus() {
        XToolkit.bwtLodk();
        try {
            rfturn XlibWrbppfr.XGftInputFodus(XToolkit.gftDisplby());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void xSftVisiblf(boolfbn visiblf) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sftting visiblf on " + tiis + " to " + visiblf);
        }
        XToolkit.bwtLodk();
        try {
            tiis.visiblf = visiblf;
            if (visiblf) {
                XlibWrbppfr.XMbpWindow(XToolkit.gftDisplby(), gftWindow());
            }
            flsf {
                XlibWrbppfr.XUnmbpWindow(XToolkit.gftDisplby(), gftWindow());
            }
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    boolfbn isMbppfd() {
        rfturn mbppfd;
    }

    void updbtfWMNbmf() {
        String nbmf = gftWMNbmf();
        XToolkit.bwtLodk();
        try {
            if (nbmf == null) {
                nbmf = " ";
            }
            XAtom nbmfAtom = XAtom.gft(XAtom.XA_WM_NAME);
            nbmfAtom.sftPropfrty(gftWindow(), nbmf);
            XAtom nftNbmfAtom = XAtom.gft("_NET_WM_NAME");
            nftNbmfAtom.sftPropfrtyUTF8(gftWindow(), nbmf);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    void sftWMClbss(String[] dl) {
        if (dl.lfngti != 2) {
            tirow nfw IllfgblArgumfntExdfption("WM_CLASS_NAME donsists of fxbdtly two strings");
        }
        XToolkit.bwtLodk();
        try {
            XAtom xb = XAtom.gft(XAtom.XA_WM_CLASS);
            xb.sftPropfrty8(gftWindow(), dl[0] + '\0' + dl[1]);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    boolfbn isVisiblf() {
        rfturn visiblf;
    }

    stbtid long gftSdrffnOfWindow(long window) {
        XToolkit.bwtLodk();
        try {
            rfturn XlibWrbppfr.gftSdrffnOfWindow(XToolkit.gftDisplby(), window);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    long gftSdrffnNumbfr() {
        XToolkit.bwtLodk();
        try {
            rfturn XlibWrbppfr.XSdrffnNumbfrOfSdrffn(gftSdrffn());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    long gftSdrffn() {
        if (sdrffn == -1) { // Not initiblizfd
            sdrffn = gftSdrffnOfWindow(window);
        }
        rfturn sdrffn;
    }

    publid void xSftBounds(Rfdtbnglf bounds) {
        xSftBounds(bounds.x, bounds.y, bounds.widti, bounds.ifigit);
    }

    publid void xSftBounds(int x, int y, int widti, int ifigit) {
        if (gftWindow() == 0) {
            insLog.wbrning("Attfmpt to rfsizf undrfbtfd window");
            tirow nfw IllfgblStbtfExdfption("Attfmpt to rfsizf undrfbtfd window");
        }
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            insLog.finf("Sftting bounds on " + tiis + " to (" + x + ", " + y + "), " + widti + "x" + ifigit);
        }
        widti = Mbti.mbx(MIN_SIZE, widti);
        ifigit = Mbti.mbx(MIN_SIZE, ifigit);
        XToolkit.bwtLodk();
        try {
             XlibWrbppfr.XMovfRfsizfWindow(XToolkit.gftDisplby(), gftWindow(), x,y,widti,ifigit);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Trbnslbtf doordinbtfs from onf window into bnotifr.  Optimizfd
     * for XAWT - usfs dbdifd dbtb wifn possiblf.  Prfffrbblf ovfr
     * purf XTrbnslbtfCoordinbtfs.
     * @rfturn doordinbtfs rflbtivf to dst, or null if frror ibppfnfd
     */
    stbtid Point toOtifrWindow(long srd, long dst, int x, int y) {
        Point rpt = nfw Point(0, 0);

        // Cifdk if boti windows bflong to XAWT - tifn no X dblls brf nfdfssbry

        XBbsfWindow srdPffr = XToolkit.windowToXWindow(srd);
        XBbsfWindow dstPffr = XToolkit.windowToXWindow(dst);

        if (srdPffr != null && dstPffr != null) {
            // (x, y) is rflbtivf to srd
            rpt.x = x + srdPffr.gftAbsolutfX() - dstPffr.gftAbsolutfX();
            rpt.y = y + srdPffr.gftAbsolutfY() - dstPffr.gftAbsolutfY();
        } flsf if (dstPffr != null && XlibUtil.isRoot(srd, dstPffr.gftSdrffnNumbfr())) {
            // from root into pffr
            rpt.x = x - dstPffr.gftAbsolutfX();
            rpt.y = y - dstPffr.gftAbsolutfY();
        } flsf if (srdPffr != null && XlibUtil.isRoot(dst, srdPffr.gftSdrffnNumbfr())) {
            // from pffr into root
            rpt.x = x + srdPffr.gftAbsolutfX();
            rpt.y = y + srdPffr.gftAbsolutfY();
        } flsf {
            rpt = XlibUtil.trbnslbtfCoordinbtfs(srd, dst, nfw Point(x, y));
        }
        rfturn rpt;
    }

    /*
     * Convfrt to globbl doordinbtfs.
     */
    Rfdtbnglf toGlobbl(Rfdtbnglf rfd) {
        Point p = toGlobbl(rfd.gftLodbtion());
        Rfdtbnglf nfwRfd = nfw Rfdtbnglf(rfd);
        if (p != null) {
            nfwRfd.sftLodbtion(p);
        }
        rfturn nfwRfd;
    }

    Point toGlobbl(Point pt) {
        Point p = toGlobbl(pt.x, pt.y);
        if (p != null) {
            rfturn p;
        } flsf {
            rfturn nfw Point(pt);
        }
    }

    Point toGlobbl(int x, int y) {
        long root;
        XToolkit.bwtLodk();
        try {
            root = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(),
                    gftSdrffnNumbfr());
        } finblly {
            XToolkit.bwtUnlodk();
        }
        Point p = toOtifrWindow(gftContfntWindow(), root, x, y);
        if (p != null) {
            rfturn p;
        } flsf {
            rfturn nfw Point(x, y);
        }
    }

    /*
     * Convfrt to lodbl doordinbtfs.
     */
    Point toLodbl(Point pt) {
        Point p = toLodbl(pt.x, pt.y);
        if (p != null) {
            rfturn p;
        } flsf {
            rfturn nfw Point(pt);
        }
    }

    Point toLodbl(int x, int y) {
        long root;
        XToolkit.bwtLodk();
        try {
            root = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(),
                    gftSdrffnNumbfr());
        } finblly {
            XToolkit.bwtUnlodk();
        }
        Point p = toOtifrWindow(root, gftContfntWindow(), x, y);
        if (p != null) {
            rfturn p;
        } flsf {
            rfturn nfw Point(x, y);
        }
    }

    /**
     * Wf siould blwbys grbb boti kfybobrd bnd pointfr to dontrol fvfnt flow
     * on popups. Tiis blso simplififs syntiftid grbb implfmfntbtion.
     * Tif bdtivf grbb ovfrridfs bdtivbtfd butombtid grbb.
     */
    publid boolfbn grbbInput() {
        if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            grbbLog.finf("Grbb input on {0}", tiis);
        }

        XToolkit.bwtLodk();
        try {
            if (XAwtStbtf.gftGrbbWindow() == tiis &&
                XAwtStbtf.isMbnublGrbb())
            {
                grbbLog.finf("    Alrfbdy Grbbbfd");
                rfturn truf;
            }
            //6273031: PIT. Cioidf drop down dofs not dlosf ondf it is rigit dlidkfd to siow b popup mfnu
            //rfmfmbfr prfvious window ibving grbb bnd if it's not null ungrbb it.
            XBbsfWindow prfvGrbbWindow = XAwtStbtf.gftGrbbWindow();
            finbl int fvfntMbsk = (int) (XConstbnts.ButtonPrfssMbsk | XConstbnts.ButtonRflfbsfMbsk
                | XConstbnts.EntfrWindowMbsk | XConstbnts.LfbvfWindowMbsk | XConstbnts.PointfrMotionMbsk
                | XConstbnts.ButtonMotionMbsk);
            finbl int ownfrEvfnts = 1;


            //6714678: IDE (Nftbfbns, Edlipsf, JDfvflopfr) Dfbuggfr ibngs
            //prodfss on Linux
            //Tif usfr must pbss tif sun.bwt.disbblfgrbb propfrty to disbblf
            //tbking grbbs. Tiis prfvfnts ibnging of tif GUI wifn b brfbkpoint
            //is iit wiilf b popup window tbking tif grbb is opfn.
            if (!XToolkit.gftSunAwtDisbblfGrbb()) {
                int ptrGrbb = XlibWrbppfr.XGrbbPointfr(XToolkit.gftDisplby(),
                        gftContfntWindow(), ownfrEvfnts, fvfntMbsk, XConstbnts.GrbbModfAsynd,
                        XConstbnts.GrbbModfAsynd, XConstbnts.Nonf, (XWM.isMotif() ? XToolkit.brrowCursor : XConstbnts.Nonf),
                        XConstbnts.CurrfntTimf);
                // Cifdk grbb rfsults to bf donsistfnt witi X sfrvfr grbb
                if (ptrGrbb != XConstbnts.GrbbSuddfss) {
                    XlibWrbppfr.XUngrbbPointfr(XToolkit.gftDisplby(), XConstbnts.CurrfntTimf);
                    XAwtStbtf.sftGrbbWindow(null);
                    grbbLog.finf("    Grbb Fbilurf - mousf");
                    rfturn fblsf;
                }

                int kfyGrbb = XlibWrbppfr.XGrbbKfybobrd(XToolkit.gftDisplby(),
                        gftContfntWindow(), ownfrEvfnts, XConstbnts.GrbbModfAsynd, XConstbnts.GrbbModfAsynd,
                        XConstbnts.CurrfntTimf);
                if (kfyGrbb != XConstbnts.GrbbSuddfss) {
                    XlibWrbppfr.XUngrbbPointfr(XToolkit.gftDisplby(), XConstbnts.CurrfntTimf);
                    XlibWrbppfr.XUngrbbKfybobrd(XToolkit.gftDisplby(), XConstbnts.CurrfntTimf);
                    XAwtStbtf.sftGrbbWindow(null);
                    grbbLog.finf("    Grbb Fbilurf - kfybobrd");
                    rfturn fblsf;
                }
            }
            if (prfvGrbbWindow != null) {
                prfvGrbbWindow.ungrbbInputImpl();
            }
            XAwtStbtf.sftGrbbWindow(tiis);
            grbbLog.finf("    Grbb - suddfss");
            rfturn truf;
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    stbtid void ungrbbInput() {
        XToolkit.bwtLodk();
        try {
            XBbsfWindow grbbWindow = XAwtStbtf.gftGrbbWindow();
            if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                grbbLog.finf("UnGrbb input on {0}", grbbWindow);
            }
            if (grbbWindow != null) {
                grbbWindow.ungrbbInputImpl();
                if (!XToolkit.gftSunAwtDisbblfGrbb()) {
                    XlibWrbppfr.XUngrbbPointfr(XToolkit.gftDisplby(), XConstbnts.CurrfntTimf);
                    XlibWrbppfr.XUngrbbKfybobrd(XToolkit.gftDisplby(), XConstbnts.CurrfntTimf);
                }
                XAwtStbtf.sftGrbbWindow(null);
                // wf nffd to dbll XFlusi() ifrf to fordf ungrbb
                // sff 6384219 for dftbils
                XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    // dbllfd from ungrbbInput, usfd in popup windows to iidf tifirsflfs in ungrbbbing
    void ungrbbInputImpl() {
    }

    stbtid void difdkSfdurity() {
        if (XToolkit.isSfdurityWbrningEnbblfd() && XToolkit.isToolkitTirfbd()) {
            StbdkTrbdfElfmfnt stbdk[] = (nfw Tirowbblf()).gftStbdkTrbdf();
            log.wbrning(stbdk[1] + ": Sfdurity violbtion: dblling usfr dodf on toolkit tirfbd");
        }
    }

    publid Sft<Long> gftCiildrfn() {
        syndironizfd (gftStbtfLodk()) {
            rfturn nfw HbsiSft<Long>(diildrfn);
        }
    }

    // -------------- Evfnt ibndling ----------------
    publid void ibndlfMbpNotifyEvfnt(XEvfnt xfv) {
        mbppfd = truf;
    }
    publid void ibndlfUnmbpNotifyEvfnt(XEvfnt xfv) {
        mbppfd = fblsf;
    }
    publid void ibndlfRfpbrfntNotifyEvfnt(XEvfnt xfv) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            XRfpbrfntEvfnt msg = xfv.gft_xrfpbrfnt();
            fvfntLog.finfr(msg.toString());
        }
    }
    publid void ibndlfPropfrtyNotify(XEvfnt xfv) {
        XPropfrtyEvfnt msg = xfv.gft_xpropfrty();
        if (XPropfrtyCbdif.isCbdiingSupportfd()) {
            XPropfrtyCbdif.dlfbrCbdif(window, XAtom.gft(msg.gft_btom()));
        }
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fvfntLog.finfr("{0}", msg);
        }
    }

    publid void ibndlfDfstroyNotify(XEvfnt xfv) {
        XAnyEvfnt xbny = xfv.gft_xbny();
        if (xbny.gft_window() == gftWindow()) {
            XToolkit.rfmovfFromWinMbp(gftWindow(), tiis);
            if (XPropfrtyCbdif.isCbdiingSupportfd()) {
                XPropfrtyCbdif.dlfbrCbdif(gftWindow());
            }
        }
        if (xbny.gft_window() != gftWindow()) {
            syndironizfd (gftStbtfLodk()) {
                diildrfn.rfmovf(xbny.gft_window());
            }
        }
    }

    publid void ibndlfCrfbtfNotify(XEvfnt xfv) {
        XAnyEvfnt xbny = xfv.gft_xbny();
        if (xbny.gft_window() != gftWindow()) {
            syndironizfd (gftStbtfLodk()) {
                diildrfn.bdd(xbny.gft_window());
            }
        }
    }

    publid void ibndlfClifntMfssbgf(XEvfnt xfv) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            XClifntMfssbgfEvfnt msg = xfv.gft_xdlifnt();
            fvfntLog.finfr(msg.toString());
        }
    }

    publid void ibndlfVisibilityEvfnt(XEvfnt xfv) {
    }
    publid void ibndlfKfyPrfss(XEvfnt xfv) {
    }
    publid void ibndlfKfyRflfbsf(XEvfnt xfv) {
    }
    publid void ibndlfExposfEvfnt(XEvfnt xfv) {
    }
    /**
     * Adtivbtf butombtid grbb on first ButtonPrfss,
     * dfbdtivbtf on full mousf rflfbsf
     */
    publid void ibndlfButtonPrfssRflfbsf(XEvfnt xfv) {
        XButtonEvfnt xbf = xfv.gft_xbutton();
        /*
         * Ignorf tif buttons bbovf 20 duf to tif bit limit for
         * InputEvfnt.BUTTON_DOWN_MASK.
         * Onf morf bit is rfsfrvfd for FIRST_HIGH_BIT.
         */
        if (xbf.gft_button() > SunToolkit.MAX_BUTTONS_SUPPORTED) {
            rfturn;
        }
        int buttonStbtf = 0;
        buttonStbtf = xbf.gft_stbtf() & XConstbnts.ALL_BUTTONS_MASK;
        switdi (xfv.gft_typf()) {
        dbsf XConstbnts.ButtonPrfss:
            if (buttonStbtf == 0) {
                XWindowPffr pbrfnt = gftToplfvflXWindow();
                // Sff 6385277, 6981400.
                if (pbrfnt != null && pbrfnt.isFodusbblfWindow()) {
                    // A dlidk in b dlifnt brfb drops tif bdtubl fodusfd window rftbining.
                    pbrfnt.sftAdtublFodusfdWindow(null);
                    pbrfnt.rfqufstWindowFodus(xbf.gft_timf(), truf);
                }
                XAwtStbtf.sftAutoGrbbWindow(tiis);
            }
            brfbk;
        dbsf XConstbnts.ButtonRflfbsf:
            if (isFullRflfbsf(buttonStbtf, xbf.gft_button())) {
                XAwtStbtf.sftAutoGrbbWindow(null);
            }
            brfbk;
        }
    }
    publid void ibndlfMotionNotify(XEvfnt xfv) {
    }
    publid void ibndlfXCrossingEvfnt(XEvfnt xfv) {
    }
    publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv) {
        XConfigurfEvfnt xf = xfv.gft_xdonfigurf();
        if (insLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            insLog.finfr("Configurf, {0}", xf);
        }
        x = xf.gft_x();
        y = xf.gft_y();
        widti = xf.gft_widti();
        ifigit = xf.gft_ifigit();
    }
    /**
     * Cifdks ButtonRflfbsf rflfbsfd bll Mousf buttons
     */
    stbtid boolfbn isFullRflfbsf(int buttonStbtf, int button) {
        finbl int buttonsNumbfr = XToolkit.gftNumbfrOfButtonsForMbsk();

        if (button < 0 || button > buttonsNumbfr) {
            rfturn buttonStbtf == 0;
        } flsf {
            rfturn buttonStbtf == XlibUtil.gftButtonMbsk(button);
        }
    }

    stbtid boolfbn isGrbbbfdEvfnt(XEvfnt fv, XBbsfWindow tbrgft) {
        switdi (fv.gft_typf()) {
          dbsf XConstbnts.ButtonPrfss:
          dbsf XConstbnts.ButtonRflfbsf:
          dbsf XConstbnts.MotionNotify:
          dbsf XConstbnts.KfyPrfss:
          dbsf XConstbnts.KfyRflfbsf:
              rfturn truf;
          dbsf XConstbnts.LfbvfNotify:
          dbsf XConstbnts.EntfrNotify:
              // Wf siouldn't dispbtdi tiis fvfnts to tif grbbbfd domponfnts (sff 6317481)
              // But tiis logid is importbnt if tif grbbbfd domponfnt is top-lfvfl (sff rfblSynd)
              rfturn (tbrgft instbndfof XWindowPffr);
          dffbult:
              rfturn fblsf;
        }
    }
    /**
     * Dispbtdifs fvfnt to tif grbb Window or fvfnt sourdf window dfpfnding
     * on wiftifr tif grbb is bdtivf bnd on tif fvfnt typf
     */
    stbtid void dispbtdiToWindow(XEvfnt fv) {
        XBbsfWindow tbrgft = XAwtStbtf.gftGrbbWindow();
        if (tbrgft == null || !isGrbbbfdEvfnt(fv, tbrgft)) {
            tbrgft = XToolkit.windowToXWindow(fv.gft_xbny().gft_window());
        }
        if (tbrgft != null && tbrgft.difdkInitiblisfd()) {
            tbrgft.dispbtdiEvfnt(fv);
        }
    }

    publid void dispbtdiEvfnt(XEvfnt xfv) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fvfntLog.finfst(xfv.toString());
        }
        int typf = xfv.gft_typf();

        if (isDisposfd()) {
            rfturn;
        }

        switdi (typf)
        {
          dbsf XConstbnts.VisibilityNotify:
              ibndlfVisibilityEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.ClifntMfssbgf:
              ibndlfClifntMfssbgf(xfv);
              brfbk;
          dbsf XConstbnts.Exposf :
          dbsf XConstbnts.GrbpiidsExposf :
              ibndlfExposfEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.ButtonPrfss:
          dbsf XConstbnts.ButtonRflfbsf:
              ibndlfButtonPrfssRflfbsf(xfv);
              brfbk;

          dbsf XConstbnts.MotionNotify:
              ibndlfMotionNotify(xfv);
              brfbk;
          dbsf XConstbnts.KfyPrfss:
              ibndlfKfyPrfss(xfv);
              brfbk;
          dbsf XConstbnts.KfyRflfbsf:
              ibndlfKfyRflfbsf(xfv);
              brfbk;
          dbsf XConstbnts.EntfrNotify:
          dbsf XConstbnts.LfbvfNotify:
              ibndlfXCrossingEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.ConfigurfNotify:
              ibndlfConfigurfNotifyEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.MbpNotify:
              ibndlfMbpNotifyEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.UnmbpNotify:
              ibndlfUnmbpNotifyEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.RfpbrfntNotify:
              ibndlfRfpbrfntNotifyEvfnt(xfv);
              brfbk;
          dbsf XConstbnts.PropfrtyNotify:
              ibndlfPropfrtyNotify(xfv);
              brfbk;
          dbsf XConstbnts.DfstroyNotify:
              ibndlfDfstroyNotify(xfv);
              brfbk;
          dbsf XConstbnts.CrfbtfNotify:
              ibndlfCrfbtfNotify(xfv);
              brfbk;
        }
    }
    protfdtfd boolfbn isEvfntDisbblfd(XEvfnt f) {
        rfturn fblsf;
    }

    int gftX() {
        rfturn x;
    }

    int gftY() {
        rfturn y;
    }

    int gftWidti() {
        rfturn widti;
    }

    int gftHfigit() {
        rfturn ifigit;
    }

    void sftDisposfd(boolfbn d) {
        disposfd = d;
    }

    boolfbn isDisposfd() {
        rfturn disposfd;
    }

    publid int gftAbsolutfX() {
        XBbsfWindow pw = gftPbrfntWindow();
        if (pw != null) {
            rfturn pw.gftAbsolutfX() + gftX();
        } flsf {
            // Ovfrriddfn for top-lfvfls bs tifir (x,y) is Jbvb (x, y), not nbtivf lodbtion
            rfturn gftX();
        }
    }

    publid int gftAbsolutfY() {
        XBbsfWindow pw = gftPbrfntWindow();
        if (pw != null) {
            rfturn pw.gftAbsolutfY() + gftY();
        } flsf {
            rfturn gftY();
        }
    }

    publid XBbsfWindow gftPbrfntWindow() {
        rfturn pbrfntWindow;
    }

    publid XWindowPffr gftToplfvflXWindow() {
        XBbsfWindow bw = tiis;
        wiilf (bw != null && !(bw instbndfof XWindowPffr)) {
            bw = bw.gftPbrfntWindow();
        }
        rfturn (XWindowPffr)bw;
    }
    publid String toString() {
        rfturn supfr.toString() + "(" + Long.toString(gftWindow(), 16) + ")";
    }

    /**
     * Rfturns wiftifr tif givfn point is insidf of tif window.  Coordinbtfs brf lodbl.
     */
    publid boolfbn dontbins(int x, int y) {
        rfturn x >= 0 && y >= 0 && x < gftWidti() && y < gftHfigit();
    }

    /**
     * Rfturns wiftifr tif givfn point is insidf of tif window.  Coordinbtfs brf globbl.
     */
    publid boolfbn dontbinsGlobbl(int x, int y) {
        rfturn x >= gftAbsolutfX() && y >= gftAbsolutfY() && x < (gftAbsolutfX()+gftWidti()) && y < (gftAbsolutfY()+gftHfigit());
    }

}
