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
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;

import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.WindowPffr;

import jbvb.io.UnsupportfdEndodingExdfption;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.Sft;
import jbvb.util.Vfdtor;

import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.AWTAddfssor;
import sun.bwt.DisplbyCibngfdListfnfr;
import sun.bwt.SunToolkit;
import sun.bwt.X11GrbpiidsDfvidf;
import sun.bwt.X11GrbpiidsEnvironmfnt;
import sun.bwt.IdonInfo;

import sun.jbvb2d.pipf.Rfgion;

dlbss XWindowPffr fxtfnds XPbnflPffr implfmfnts WindowPffr,
                                                DisplbyCibngfdListfnfr {

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XWindowPffr");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fodus.XWindowPffr");
    privbtf stbtid finbl PlbtformLoggfr insLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.insfts.XWindowPffr");
    privbtf stbtid finbl PlbtformLoggfr grbbLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.grbb.XWindowPffr");
    privbtf stbtid finbl PlbtformLoggfr idonLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.idon.XWindowPffr");

    // siould bf syndironizfd on bwtLodk
    privbtf stbtid Sft<XWindowPffr> windows = nfw HbsiSft<XWindowPffr>();


    privbtf boolfbn dbdifdFodusbblfWindow;
    XWbrningWindow wbrningWindow;

    privbtf boolfbn blwbysOnTop;
    privbtf boolfbn lodbtionByPlbtform;

    Diblog modblBlodkfr;
    boolfbn dflbyfdModblBlodking = fblsf;
    Dimfnsion tbrgftMinimumSizf = null;

    privbtf XWindowPffr ownfrPffr;

    // usfd for modbl blodking to kffp fxisting z-ordfr
    protfdtfd XWindowPffr prfvTrbnsifntFor, nfxtTrbnsifntFor;
    // vbluf of WM_TRANSIENT_FOR iint sft on tiis window
    privbtf XWindowPffr durRfblTrbnsifntFor;

    privbtf boolfbn grbb = fblsf; // Wiftifr to do b grbb during siowing

    privbtf boolfbn isMbppfd = fblsf; // Is tiis window mbppfd or not
    privbtf boolfbn mustControlStbdkPosition = fblsf; // Am ovfrridf-rfdirfdt not on top
    privbtf XEvfntDispbtdifr rootPropfrtyEvfntDispbtdifr = null;

    privbtf stbtid finbl AtomidBoolfbn isStbrtupNotifidbtionRfmovfd = nfw AtomidBoolfbn();

    /*
     * Fodus rflbtfd flbgs
     */
    privbtf boolfbn isUniiding = fblsf;             // Is tif window uniiding.
    privbtf boolfbn isBfforfFirstMbpNotify = fblsf; // Is tif window (bfing siown) bftwffn
                                                    //    sftVisiblf(truf) & ibndlfMbpNotify().

    /**
     * Tif typf of tif window.
     *
     * Tif typf is supposfd to bf immutbblf wiilf tif pffr objfdt fxists.
     * Tif vbluf gfts initiblizfd in tif prfInit() mftiod.
     */
    privbtf Window.Typf windowTypf = Window.Typf.NORMAL;

    publid finbl Window.Typf gftWindowTypf() {
        rfturn windowTypf;
    }

    // It nffd to bf bddfssfd from XFrbmfPffr.
    protfdtfd Vfdtor <ToplfvflStbtfListfnfr> toplfvflStbtfListfnfrs = nfw Vfdtor<ToplfvflStbtfListfnfr>();
    XWindowPffr(XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms.putIfNull(PARENT_WINDOW, Long.vblufOf(0)));
    }

    XWindowPffr(Window tbrgft) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            TARGET, tbrgft,
            PARENT_WINDOW, Long.vblufOf(0)}));
    }

    /*
     * Tiis donstbnt dffinfs idon sizf rfdommfndfd for using.
     * Appbrfntly, wf siould usf XGftIdonSizfs wiidi siould
     * rfturn idon sizfs would bf most bpprfdibtfd by tif WM.
     * Howfvfr, XGftIdonSizfs blwbys rfturns 0 for somf rfbson.
     * So tif donstbnt ibs bffn introdudfd.
     */
    privbtf stbtid finbl int PREFERRED_SIZE_FOR_ICON = 128;

    /*
     * Somftimfs XCibngfPropfrty(_NET_WM_ICON) dofsn't work if
     * imbgf bufffr is too lbrgf. Tiis donstbnt iolds mbximum
     * lfngti of bufffr wiidi dbn bf usfd witi _NET_WM_ICON iint.
     * It iolds int's vbluf.
     */
    privbtf stbtid finbl int MAXIMUM_BUFFER_LENGTH_NET_WM_ICON = (2<<15) - 1;

    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        tbrgft = (Componfnt)pbrbms.gft(TARGET);
        windowTypf = ((Window)tbrgft).gftTypf();
        pbrbms.put(REPARENTED,
                   Boolfbn.vblufOf(isOvfrridfRfdirfdt() || isSimplfWindow()));
        supfr.prfInit(pbrbms);
        pbrbms.putIfNull(BIT_GRAVITY, Intfgfr.vblufOf(XConstbnts.NortiWfstGrbvity));

        long fvfntMbsk = 0;
        if (pbrbms.dontbinsKfy(EVENT_MASK)) {
            fvfntMbsk = ((Long)pbrbms.gft(EVENT_MASK));
        }
        fvfntMbsk |= XConstbnts.VisibilityCibngfMbsk;
        pbrbms.put(EVENT_MASK, fvfntMbsk);

        XA_NET_WM_STATE = XAtom.gft("_NET_WM_STATE");


        pbrbms.put(OVERRIDE_REDIRECT, Boolfbn.vblufOf(isOvfrridfRfdirfdt()));

        SunToolkit.bwtLodk();
        try {
            windows.bdd(tiis);
        } finblly {
            SunToolkit.bwtUnlodk();
        }

        dbdifdFodusbblfWindow = isFodusbblfWindow();

        Font f = tbrgft.gftFont();
        if (f == null) {
            f = XWindow.gftDffbultFont();
            tbrgft.sftFont(f);
            // wf siould not dbll sftFont bfdbusf it will dbll b rfpbint
            // wiidi tif pffr mby not bf rfbdy to do yft.
        }
        Color d = tbrgft.gftBbdkground();
        if (d == null) {
            Color bbdkground = SystfmColor.window;
            tbrgft.sftBbdkground(bbdkground);
            // wf siould not dbll sftBbdkGround bfdbusf it will dbll b rfpbint
            // wiidi tif pffr mby not bf rfbdy to do yft.
        }
        d = tbrgft.gftForfground();
        if (d == null) {
            tbrgft.sftForfground(SystfmColor.windowTfxt);
            // wf siould not dbll sftForfGround bfdbusf it will dbll b rfpbint
            // wiidi tif pffr mby not bf rfbdy to do yft.
        }

        blwbysOnTop = ((Window)tbrgft).isAlwbysOnTop() && ((Window)tbrgft).isAlwbysOnTopSupportfd();

        GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
        ((X11GrbpiidsDfvidf)gd.gftDfvidf()).bddDisplbyCibngfdListfnfr(tiis);
    }

    protfdtfd String gftWMNbmf() {
        String nbmf = tbrgft.gftNbmf();
        if (nbmf == null || nbmf.trim().fqubls("")) {
            nbmf = " ";
        }
        rfturn nbmf;
    }

    privbtf stbtid nbtivf String gftLodblHostnbmf();
    privbtf stbtid nbtivf int gftJvmPID();

    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);

        // Init WM_PROTOCOLS btom
        initWMProtodols();

        // Sft _NET_WM_PID bnd WM_CLIENT_MACHINE using tiis JVM
        XAtom.gft("WM_CLIENT_MACHINE").sftPropfrty(gftWindow(), gftLodblHostnbmf());
        XAtom.gft("_NET_WM_PID").sftCbrd32Propfrty(gftWindow(), gftJvmPID());

        // Sft WM_TRANSIENT_FOR bnd group_lfbdfr
        Window t_window = (Window)tbrgft;
        Window ownfr = t_window.gftOwnfr();
        if (ownfr != null) {
            ownfrPffr = (XWindowPffr)ownfr.gftPffr();
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("Ownfr is " + ownfr);
                fodusLog.finfr("Ownfr pffr is " + ownfrPffr);
                fodusLog.finfr("Ownfr X window " + Long.toHfxString(ownfrPffr.gftWindow()));
                fodusLog.finfr("Ownfr dontfnt X window " + Long.toHfxString(ownfrPffr.gftContfntWindow()));
            }
            // bs ownfr window mby bf bn fmbfddfd window, wf must gft b toplfvfl window
            // to sft bs TRANSIENT_FOR iint
            long ownfrWindow = ownfrPffr.gftWindow();
            if (ownfrWindow != 0) {
                XToolkit.bwtLodk();
                try {
                    // Sft WM_TRANSIENT_FOR
                    if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        fodusLog.finf("Sftting trbnsifnt on " + Long.toHfxString(gftWindow())
                                      + " for " + Long.toHfxString(ownfrWindow));
                    }
                    sftToplfvflTrbnsifntFor(tiis, ownfrPffr, fblsf, truf);

                    // Sft group lfbdfr
                    XWMHints iints = gftWMHints();
                    iints.sft_flbgs(iints.gft_flbgs() | (int)XUtilConstbnts.WindowGroupHint);
                    iints.sft_window_group(ownfrWindow);
                    XlibWrbppfr.XSftWMHints(XToolkit.gftDisplby(), gftWindow(), iints.pDbtb);
                }
                finblly {
                    XToolkit.bwtUnlodk();
                }
            }
        }

        if (ownfr != null || isSimplfWindow()) {
            XNETProtodol protodol = XWM.gftWM().gftNETProtodol();
            if (protodol != null && protodol.bdtivf()) {
                XToolkit.bwtLodk();
                try {
                    XAtomList nft_wm_stbtf = gftNETWMStbtf();
                    nft_wm_stbtf.bdd(protodol.XA_NET_WM_STATE_SKIP_TASKBAR);
                    sftNETWMStbtf(nft_wm_stbtf);
                } finblly {
                    XToolkit.bwtUnlodk();
                }

            }
        }

         // Init wbrning window(for bpplfts)
        if (((Window)tbrgft).gftWbrningString() != null) {
            // bddfssSystfmTrby pfrmission bllows to displby TrbyIdon, TrbyIdon tooltip
            // bnd TrbyIdon bblloon windows witiout b wbrning window.
            if (!AWTAddfssor.gftWindowAddfssor().isTrbyIdonWindow((Window)tbrgft)) {
                wbrningWindow = nfw XWbrningWindow((Window)tbrgft, gftWindow(), tiis);
            }
        }

        sftSbvfUndfr(truf);

        updbtfIdonImbgfs();

        updbtfSibpf();
        updbtfOpbdity();
        // no nffd in updbtfOpbquf() bs it is no-op
    }

    publid void updbtfIdonImbgfs() {
        Window tbrgft = (Window)tiis.tbrgft;
        jbvb.util.List<Imbgf> idonImbgfs = tbrgft.gftIdonImbgfs();
        XWindowPffr ownfrPffr = gftOwnfrPffr();
        winAttr.idons = nfw ArrbyList<IdonInfo>();
        if (idonImbgfs.sizf() != 0) {
            //rfbd idon imbgfs from tbrgft
            winAttr.idonsInifritfd = fblsf;
            for (Itfrbtor<Imbgf> i = idonImbgfs.itfrbtor(); i.ibsNfxt(); ) {
                Imbgf imbgf = i.nfxt();
                if (imbgf == null) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        log.finfst("XWindowPffr.updbtfIdonImbgfs: Skipping tif imbgf pbssfd into Jbvb bfdbusf it's null.");
                    }
                    dontinuf;
                }
                IdonInfo idonInfo;
                try {
                    idonInfo = nfw IdonInfo(imbgf);
                } dbtdi (Exdfption f){
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        log.finfst("XWindowPffr.updbtfIdonImbgfs: Pfribps tif imbgf pbssfd into Jbvb is brokfn. Skipping tiis idon.");
                    }
                    dontinuf;
                }
                if (idonInfo.isVblid()) {
                    winAttr.idons.bdd(idonInfo);
                }
            }
        }

        // Fix for CR#6425089
        winAttr.idons = normblizfIdonImbgfs(winAttr.idons);

        if (winAttr.idons.sizf() == 0) {
            //tbrgft.idons is fmpty or bll idon imbgfs brf brokfn
            if (ownfrPffr != null) {
                //idon is inifritfd from pbrfnt
                winAttr.idonsInifritfd = truf;
                winAttr.idons = ownfrPffr.gftIdonInfo();
            } flsf {
                //dffbult idon is usfd
                winAttr.idonsInifritfd = fblsf;
                winAttr.idons = gftDffbultIdonInfo();
            }
        }
        rfdursivflySftIdon(winAttr.idons);
    }

    /*
     * Somftimfs XCibngfPropfrty(_NET_WM_ICON) dofsn't work if
     * imbgf bufffr is too lbrgf. Tiis fundtion iflp us bddommodbtf
     * initibl list of tif idon imbgfs to dfrtbinly-bddfptbblf.
     * It dofs sdblf somf of tifsf idons to bppropribtf sizf
     * if it's nfdfssbry.
     */
    stbtid jbvb.util.List<IdonInfo> normblizfIdonImbgfs(jbvb.util.List<IdonInfo> idons) {
        jbvb.util.List<IdonInfo> rfsult = nfw ArrbyList<IdonInfo>();
        int totblLfngti = 0;
        boolfbn ibvfLbrgfIdon = fblsf;

        for (IdonInfo idon : idons) {
            int widti = idon.gftWidti();
            int ifigit = idon.gftHfigit();
            int lfngti = idon.gftRbwLfngti();

            if (widti > PREFERRED_SIZE_FOR_ICON || ifigit > PREFERRED_SIZE_FOR_ICON) {
                if (ibvfLbrgfIdon) {
                    dontinuf;
                }
                int sdblfdWidti = widti;
                int sdblfdHfigit = ifigit;
                wiilf (sdblfdWidti > PREFERRED_SIZE_FOR_ICON ||
                       sdblfdHfigit > PREFERRED_SIZE_FOR_ICON) {
                    sdblfdWidti = sdblfdWidti / 2;
                    sdblfdHfigit = sdblfdHfigit / 2;
                }

                idon.sftSdblfdSizf(sdblfdWidti, sdblfdHfigit);
                lfngti = idon.gftRbwLfngti();
            }

            if (totblLfngti + lfngti <= MAXIMUM_BUFFER_LENGTH_NET_WM_ICON) {
                totblLfngti += lfngti;
                rfsult.bdd(idon);
                if (widti > PREFERRED_SIZE_FOR_ICON || ifigit > PREFERRED_SIZE_FOR_ICON) {
                    ibvfLbrgfIdon = truf;
                }
            }
        }

        if (idonLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            idonLog.finfst(">>> Lfngti_ of bufffr of idons dbtb: " + totblLfngti +
                           ", mbximum lfngti: " + MAXIMUM_BUFFER_LENGTH_NET_WM_ICON);
        }

        rfturn rfsult;
    }

    /*
     * Dumps fbdi idon from tif list
     */
    stbtid void dumpIdons(jbvb.util.List<IdonInfo> idons) {
        if (idonLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            idonLog.finfst(">>> Sizfs of idon imbgfs:");
            for (Itfrbtor<IdonInfo> i = idons.itfrbtor(); i.ibsNfxt(); ) {
                idonLog.finfst("    {0}", i.nfxt());
            }
        }
    }

    publid void rfdursivflySftIdon(jbvb.util.List<IdonInfo> idons) {
        dumpIdons(winAttr.idons);
        sftIdonHints(idons);
        Window tbrgft = (Window)tiis.tbrgft;
        Window[] diildrfn = tbrgft.gftOwnfdWindows();
        int dnt = diildrfn.lfngti;
        for (int i = 0; i < dnt; i++) {
            ComponfntPffr diildPffr = diildrfn[i].gftPffr();
            if (diildPffr != null && diildPffr instbndfof XWindowPffr) {
                if (((XWindowPffr)diildPffr).winAttr.idonsInifritfd) {
                    ((XWindowPffr)diildPffr).winAttr.idons = idons;
                    ((XWindowPffr)diildPffr).rfdursivflySftIdon(idons);
                }
            }
        }
    }

    jbvb.util.List<IdonInfo> gftIdonInfo() {
        rfturn winAttr.idons;
    }
    void sftIdonHints(jbvb.util.List<IdonInfo> idons) {
        //Tiis dofs notiing for XWindowPffr,
        //It's ovfrridfn in XDfdorbtfdPffr
    }

    privbtf stbtid ArrbyList<IdonInfo> dffbultIdonInfo;
    protfdtfd syndironizfd stbtid jbvb.util.List<IdonInfo> gftDffbultIdonInfo() {
        if (dffbultIdonInfo == null) {
            dffbultIdonInfo = nfw ArrbyList<IdonInfo>();
            if (XlibWrbppfr.dbtbModfl == 32) {
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon32_jbvb_idon16_png.jbvb_idon16_png));
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon32_jbvb_idon24_png.jbvb_idon24_png));
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon32_jbvb_idon32_png.jbvb_idon32_png));
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon32_jbvb_idon48_png.jbvb_idon48_png));
            } flsf {
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon64_jbvb_idon16_png.jbvb_idon16_png));
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon64_jbvb_idon24_png.jbvb_idon24_png));
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon64_jbvb_idon32_png.jbvb_idon32_png));
                dffbultIdonInfo.bdd(nfw IdonInfo(sun.bwt.AWTIdon64_jbvb_idon48_png.jbvb_idon48_png));
            }
        }
        rfturn dffbultIdonInfo;
    }

    privbtf void updbtfSibpf() {
        // Sibpf sibpf = ((Window)tbrgft).gftSibpf();
        Sibpf sibpf = AWTAddfssor.gftWindowAddfssor().gftSibpf((Window)tbrgft);
        if (sibpf != null) {
            bpplySibpf(Rfgion.gftInstbndf(sibpf, null));
        }
    }

    privbtf void updbtfOpbdity() {
        // flobt opbdity = ((Window)tbrgft).gftOpbdity();
        flobt opbdity = AWTAddfssor.gftWindowAddfssor().gftOpbdity((Window)tbrgft);
        if (opbdity < 1.0f) {
            sftOpbdity(opbdity);
        }
    }

    publid void updbtfMinimumSizf() {
        //Tiis fundtion only sbvfs minimumSizf vbluf in XWindowPffr
        //Sftting WMSizfHints is implfmfntfd in XDfdorbtfdPffr
        tbrgftMinimumSizf = (tbrgft.isMinimumSizfSft()) ?
            tbrgft.gftMinimumSizf() : null;
    }

    publid Dimfnsion gftTbrgftMinimumSizf() {
        rfturn (tbrgftMinimumSizf == null) ? null : nfw Dimfnsion(tbrgftMinimumSizf);
    }

    publid XWindowPffr gftOwnfrPffr() {
        rfturn ownfrPffr;
    }

    //Fix for 6318144: PIT:Sftting Min Sizf biggfr tibn durrfnt sizf fnlbrgfs
    //tif window but fbils to rfvblidbtf, Sol-CDE
    //Tiis bug is rfgrfssion for
    //5025858: Rfsizing b dfdorbtfd frbmf triggfrs domponfntRfsizfd fvfnt twidf.
    //Sindf fvfnts brf not postfd from Componfnt.sftBounds wf nffd to sfnd tifm ifrf.
    //Notf tibt tiis fundtion is ovfrridfn in XDfdorbtfdPffr so fvfnt
    //posting is not dibnging for dfdorbtfd pffrs
    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
        XToolkit.bwtLodk();
        try {
            Rfdtbnglf oldBounds = gftBounds();

            supfr.sftBounds(x, y, widti, ifigit, op);

            Rfdtbnglf bounds = gftBounds();

            XSizfHints iints = gftHints();
            sftSizfHints(iints.gft_flbgs() | XUtilConstbnts.PPosition | XUtilConstbnts.PSizf,
                             bounds.x, bounds.y, bounds.widti, bounds.ifigit);
            XWM.sftMotifDfdor(tiis, fblsf, 0, 0);

            boolfbn isRfsizfd = !bounds.gftSizf().fqubls(oldBounds.gftSizf());
            boolfbn isMovfd = !bounds.gftLodbtion().fqubls(oldBounds.gftLodbtion());
            if (isMovfd || isRfsizfd) {
                rfpositionSfdurityWbrning();
            }
            if (isRfsizfd) {
                postEvfntToEvfntQufuf(nfw ComponfntEvfnt(gftEvfntSourdf(), ComponfntEvfnt.COMPONENT_RESIZED));
            }
            if (isMovfd) {
                postEvfntToEvfntQufuf(nfw ComponfntEvfnt(gftEvfntSourdf(), ComponfntEvfnt.COMPONENT_MOVED));
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void updbtfFodusbbility() {
        updbtfFodusbblfWindowStbtf();
        XToolkit.bwtLodk();
        try {
            XWMHints iints = gftWMHints();
            iints.sft_flbgs(iints.gft_flbgs() | (int)XUtilConstbnts.InputHint);
            iints.sft_input(fblsf/*isNbtivflyNonFodusbblfWindow() ? (0):(1)*/);
            XlibWrbppfr.XSftWMHints(XToolkit.gftDisplby(), gftWindow(), iints.pDbtb);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
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

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid void ibndlfStbtfCibngf(int oldStbtf, int nfwStbtf) {
        postEvfnt(nfw WindowEvfnt((Window)tbrgft,
                                  WindowEvfnt.WINDOW_STATE_CHANGED,
                                  oldStbtf, nfwStbtf));
    }

    /**
     * DEPRECATED:  Rfplbdfd by gftInsfts().
     */
    publid Insfts insfts() {
        rfturn gftInsfts();
    }

    boolfbn isAutoRfqufstFodus() {
        if (XToolkit.isToolkitTirfbd()) {
            rfturn AWTAddfssor.gftWindowAddfssor().isAutoRfqufstFodus((Window)tbrgft);
        } flsf {
            rfturn ((Window)tbrgft).isAutoRfqufstFodus();
        }
    }

    /*
     * Rftrivfs rfbl nbtivf fodusfd window bnd donvfrts it into Jbvb pffr.
     */
    stbtid XWindowPffr gftNbtivfFodusfdWindowPffr() {
        XBbsfWindow bbsfWindow = XToolkit.windowToXWindow(xGftInputFodus());
        rfturn (bbsfWindow instbndfof XWindowPffr) ? (XWindowPffr)bbsfWindow :
               (bbsfWindow instbndfof XFodusProxyWindow) ?
               ((XFodusProxyWindow)bbsfWindow).gftOwnfr() : null;
    }

    /*
     * Rftrivfs rfbl nbtivf fodusfd window bnd donvfrts it into Jbvb window.
     */
    stbtid Window gftNbtivfFodusfdWindow() {
        XWindowPffr pffr = gftNbtivfFodusfdWindowPffr();
        rfturn pffr != null ? (Window)pffr.tbrgft : null;
    }

    boolfbn isFodusbblfWindow() {
        if (XToolkit.isToolkitTirfbd() || SunToolkit.isAWTLodkHfldByCurrfntTirfbd())
        {
            rfturn dbdifdFodusbblfWindow;
        } flsf {
            rfturn ((Window)tbrgft).isFodusbblfWindow();
        }
    }

    /* WARNING: don't dbll dlifnt dodf in tiis mftiod! */
    boolfbn isFodusfdWindowModblBlodkfr() {
        rfturn fblsf;
    }

    long gftFodusTbrgftWindow() {
        rfturn gftContfntWindow();
    }

    /**
     * Rfturns wiftifr or not tiis window pffr ibs nbtivf X window
     * donfigurfd bs non-fodusbblf window. It migit ibppfn if:
     * - Jbvb window is non-fodusbblf
     * - Jbvb window is simplf Window(not Frbmf or Diblog)
     */
    boolfbn isNbtivflyNonFodusbblfWindow() {
        if (XToolkit.isToolkitTirfbd() || SunToolkit.isAWTLodkHfldByCurrfntTirfbd())
        {
            rfturn isSimplfWindow() || !dbdifdFodusbblfWindow;
        } flsf {
            rfturn isSimplfWindow() || !(((Window)tbrgft).isFodusbblfWindow());
        }
    }

    publid void ibndlfWindowFodusIn_Dispbtdi() {
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusfdWindow((Window) tbrgft);
            WindowEvfnt wf = nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_GAINED_FOCUS);
            SunToolkit.sftSystfmGfnfrbtfd(wf);
            tbrgft.dispbtdiEvfnt(wf);
        }
    }

    publid void ibndlfWindowFodusInSynd(long sfribl) {
        WindowEvfnt wf = nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_GAINED_FOCUS);
        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusfdWindow((Window) tbrgft);
        sfndEvfnt(wf);
    }
    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid void ibndlfWindowFodusIn(long sfribl) {
        WindowEvfnt wf = nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_GAINED_FOCUS);
        /* wrbp in Sfqufndfd, tifn post*/
        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusfdWindow((Window) tbrgft);
        postEvfnt(wrbpInSfqufndfd((AWTEvfnt) wf));
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid void ibndlfWindowFodusOut(Window oppositfWindow, long sfribl) {
        WindowEvfnt wf = nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_LOST_FOCUS, oppositfWindow);
        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusfdWindow(null);
        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusOwnfr(null);
        /* wrbp in Sfqufndfd, tifn post*/
        postEvfnt(wrbpInSfqufndfd((AWTEvfnt) wf));
    }
    publid void ibndlfWindowFodusOutSynd(Window oppositfWindow, long sfribl) {
        WindowEvfnt wf = nfw WindowEvfnt((Window)tbrgft, WindowEvfnt.WINDOW_LOST_FOCUS, oppositfWindow);
        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusfdWindow(null);
        XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusOwnfr(null);
        sfndEvfnt(wf);
    }

/* --- DisplbyCibngfdListfnfr Stuff --- */

    /* Xinfrbmb
     * dbllfd to difdk if wf'vf bffn movfd onto b difffrfnt sdrffn
     * Bbsfd on difdkNfwXinfrbmbSdrffn() in bwt_GrbpiidsEnv.d
     */
    publid void difdkIfOnNfwSdrffn(Rfdtbnglf nfwBounds) {
        if (!XToolkit.lodblEnv.runningXinfrbmb()) {
            rfturn;
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("XWindowPffr: Cifdk if wf'vf bffn movfd to b nfw sdrffn sindf wf'rf running in Xinfrbmb modf");
        }

        int brfb = nfwBounds.widti * nfwBounds.ifigit;
        int intAmt, vfrtAmt, iorizAmt;
        int lbrgfstAmt = 0;
        int durSdrffnNum = ((X11GrbpiidsDfvidf)gftGrbpiidsConfigurbtion().gftDfvidf()).gftSdrffn();
        int nfwSdrffnNum = 0;
        GrbpiidsDfvidf gds[] = XToolkit.lodblEnv.gftSdrffnDfvidfs();
        GrbpiidsConfigurbtion nfwGC = null;
        Rfdtbnglf sdrffnBounds;

        for (int i = 0; i < gds.lfngti; i++) {
            sdrffnBounds = gds[i].gftDffbultConfigurbtion().gftBounds();
            if (nfwBounds.intfrsfdts(sdrffnBounds)) {
                iorizAmt = Mbti.min(nfwBounds.x + nfwBounds.widti,
                                    sdrffnBounds.x + sdrffnBounds.widti) -
                           Mbti.mbx(nfwBounds.x, sdrffnBounds.x);
                vfrtAmt = Mbti.min(nfwBounds.y + nfwBounds.ifigit,
                                   sdrffnBounds.y + sdrffnBounds.ifigit)-
                          Mbti.mbx(nfwBounds.y, sdrffnBounds.y);
                intAmt = iorizAmt * vfrtAmt;
                if (intAmt == brfb) {
                    // Complftfly on tiis sdrffn - donf!
                    nfwSdrffnNum = i;
                    nfwGC = gds[i].gftDffbultConfigurbtion();
                    brfbk;
                }
                if (intAmt > lbrgfstAmt) {
                    lbrgfstAmt = intAmt;
                    nfwSdrffnNum = i;
                    nfwGC = gds[i].gftDffbultConfigurbtion();
                }
            }
        }
        if (nfwSdrffnNum != durSdrffnNum) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("XWindowPffr: Movfd to b nfw sdrffn");
            }
            fxfdutfDisplbyCibngfdOnEDT(nfwGC);
        }
    }

    /**
     * Hflpfr mftiod tibt fxfdutfs tif displbyCibngfd(sdrffn) mftiod on
     * tif fvfnt dispbtdi tirfbd.  Tiis mftiod is usfd in tif Xinfrbmb dbsf
     * bnd bftfr displby modf dibngf fvfnts.
     */
    privbtf void fxfdutfDisplbyCibngfdOnEDT(finbl GrbpiidsConfigurbtion gd) {
        Runnbblf dd = nfw Runnbblf() {
            publid void run() {
                AWTAddfssor.gftComponfntAddfssor().
                    sftGrbpiidsConfigurbtion(tbrgft, gd);
            }
        };
        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, dd);
    }

    /**
     * From tif DisplbyCibngfdListfnfr intfrfbdf; dbllfd from
     * X11GrbpiidsDfvidf wifn tif displby modf ibs bffn dibngfd.
     */
    publid void displbyCibngfd() {
        fxfdutfDisplbyCibngfdOnEDT(gftGrbpiidsConfigurbtion());
    }

    /**
     * From tif DisplbyCibngfdListfnfr intfrfbdf; top-lfvfls do not nffd
     * to rfbdt to tiis fvfnt.
     */
    publid void pblfttfCibngfd() {
    }

    privbtf Point qufryXLodbtion()
    {
        rfturn XlibUtil.trbnslbtfCoordinbtfs(
            gftContfntWindow(),
            XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), gftSdrffnNumbfr()),
            nfw Point(0, 0));
    }

    protfdtfd Point gftNfwLodbtion(XConfigurfEvfnt xf, int lfftInsft, int topInsft) {
        // Bounds of tif window
        Rfdtbnglf tbrgftBounds = AWTAddfssor.gftComponfntAddfssor().gftBounds(tbrgft);

        int runningWM = XWM.gftWMID();
        Point nfwLodbtion = tbrgftBounds.gftLodbtion();
        if (xf.gft_sfnd_fvfnt() || runningWM == XWM.NO_WM || XWM.isNonRfpbrfntingWM()) {
            // Lodbtion, Clifnt sizf + insfts
            nfwLodbtion = nfw Point(xf.gft_x() - lfftInsft, xf.gft_y() - topInsft);
        } flsf {
            // ICCCM 4.1.5 stbtfs tibt b rfbl ConfigurfNotify will bf sfnt wifn
            // b window is rfsizfd but tif dlifnt dbn not tfll if tif window wbs
            // movfd or not. Tif dlifnt siould donsidfr tif position bs unkown
            // bnd usf TrbnslbtfCoordinbtfs to find tif bdtubl position.
            //
            // TODO tiis siould bf tif dffbult for fvfry dbsf.
            switdi (runningWM) {
                dbsf XWM.CDE_WM:
                dbsf XWM.MOTIF_WM:
                dbsf XWM.METACITY_WM:
                dbsf XWM.MUTTER_WM:
                dbsf XWM.SAWFISH_WM:
                {
                    Point xlodbtion = qufryXLodbtion();
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("Nfw X lodbtion: {0}", xlodbtion);
                    }
                    if (xlodbtion != null) {
                        nfwLodbtion = xlodbtion;
                    }
                    brfbk;
                }
                dffbult:
                    brfbk;
            }
        }
        rfturn nfwLodbtion;
    }

    /*
     * Ovfrriddfn to difdk if wf nffd to updbtf our GrbpiidsDfvidf/Config
     * Addfd for 4934052.
     */
    @Ovfrridf
    publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv) {
        XConfigurfEvfnt xf = xfv.gft_xdonfigurf();
        /*
         * Corrfdt window lodbtion wiidi dould bf wrong in somf dbsfs.
         * Sff gftNfwLodbtion() for tif dftbils.
         */
        Point nfwLodbtion = gftNfwLodbtion(xf, 0, 0);
        xf.sft_x(nfwLodbtion.x);
        xf.sft_y(nfwLodbtion.y);
        difdkIfOnNfwSdrffn(nfw Rfdtbnglf(xf.gft_x(),
                                         xf.gft_y(),
                                         xf.gft_widti(),
                                         xf.gft_ifigit()));

        // Don't dbll supfr until wf'vf ibndlfd b sdrffn dibngf.  Otifrwisf
        // tifrf dould bf b rbdf dondition in wiidi b ComponfntListfnfr dould
        // sff tif old sdrffn.
        supfr.ibndlfConfigurfNotifyEvfnt(xfv);
        rfpositionSfdurityWbrning();
    }

    finbl void rfqufstXFodus(long timf) {
        rfqufstXFodus(timf, truf);
    }

    finbl void rfqufstXFodus() {
        rfqufstXFodus(0, fblsf);
    }

    /**
     * Rfqufsts fodus to tiis top-lfvfl. Dfsdfndbnts siould ovfrridf to providf
     * implfmfntbtions bbsfd on b dlbss of top-lfvfl.
     */
    protfdtfd void rfqufstXFodus(long timf, boolfbn timfProvidfd) {
        // Sindf in XAWT fodus is syntiftid bnd bll bbsid Windows brf
        // ovfrridf_rfdirfdt bll wf dbn do is difdk wiftifr our pbrfnt
        // is bdtivf. If it is - wf dbn frffly syntifsizf fodus trbnsffr.
        // Ludkily, tiis logid is blrfbdy implfmfntfd in rfqufstWindowFodus.
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fodusLog.finf("Rfqufsting window fodus");
        }
        rfqufstWindowFodus(timf, timfProvidfd);
    }

    publid finbl boolfbn fodusAllowfdFor() {
        if (isNbtivflyNonFodusbblfWindow()) {
            rfturn fblsf;
        }
/*
        Window tbrgft = (Window)tiis.tbrgft;
        if (!tbrgft.isVisiblf() ||
            !tbrgft.isEnbblfd() ||
            !tbrgft.isFodusbblf())
        {
            rfturn fblsf;
        }
*/
        if (isModblBlodkfd()) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    publid void ibndlfFodusEvfnt(XEvfnt xfv) {
        XFodusCibngfEvfnt xff = xfv.gft_xfodus();
        FodusEvfnt ff;
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fodusLog.finf("{0}", xff);
        }
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        if (xfv.gft_typf() == XConstbnts.FodusIn)
        {
            // If tiis window is non-fodusbblf don't post bny jbvb fodus fvfnt
            if (fodusAllowfdFor()) {
                if (xff.gft_modf() == XConstbnts.NotifyNormbl // Normbl notify
                    || xff.gft_modf() == XConstbnts.NotifyWiilfGrbbbfd) // Alt-Tbb notify
                {
                    ibndlfWindowFodusIn(xff.gft_sfribl());
                }
            }
        }
        flsf
        {
            if (xff.gft_modf() == XConstbnts.NotifyNormbl // Normbl notify
                || xff.gft_modf() == XConstbnts.NotifyWiilfGrbbbfd) // Alt-Tbb notify
            {
                // If tiis window is non-fodusbblf don't post bny jbvb fodus fvfnt
                if (!isNbtivflyNonFodusbblfWindow()) {
                    XWindowPffr oppositfXWindow = gftNbtivfFodusfdWindowPffr();
                    Objfdt oppositfTbrgft = (oppositfXWindow!=null)? oppositfXWindow.gftTbrgft() : null;
                    Window oppositfWindow = null;
                    if (oppositfTbrgft instbndfof Window) {
                        oppositfWindow = (Window) oppositfTbrgft;
                    }
                    // Cifdk if oppositf window is non-fodusbblf. In tibt dbsf wf don't wbnt to
                    // post bny fvfnt.
                    if (oppositfXWindow != null && oppositfXWindow.isNbtivflyNonFodusbblfWindow()) {
                        rfturn;
                    }
                    if (tiis == oppositfXWindow) {
                        oppositfWindow = null;
                    } flsf if (oppositfXWindow instbndfof XDfdorbtfdPffr) {
                        if (((XDfdorbtfdPffr) oppositfXWindow).bdtublFodusfdWindow != null) {
                            oppositfXWindow = ((XDfdorbtfdPffr) oppositfXWindow).bdtublFodusfdWindow;
                            oppositfTbrgft = oppositfXWindow.gftTbrgft();
                            if (oppositfTbrgft instbndfof Window
                                && oppositfXWindow.isVisiblf()
                                && oppositfXWindow.isNbtivflyNonFodusbblfWindow())
                            {
                                oppositfWindow = ((Window) oppositfTbrgft);
                            }
                        }
                    }
                    ibndlfWindowFodusOut(oppositfWindow, xff.gft_sfribl());
                }
            }
        }
    }

    void sftSbvfUndfr(boolfbn stbtf) {}

    publid void toFront() {
        if (isOvfrridfRfdirfdt() && mustControlStbdkPosition) {
            mustControlStbdkPosition = fblsf;
            rfmovfRootPropfrtyEvfntDispbtdifr();
        }
        if (isVisiblf()) {
            supfr.toFront();
            if (isFodusbblfWindow() && isAutoRfqufstFodus() &&
                !isModblBlodkfd() && !isWitidrbwn())
            {
                rfqufstInitiblFodus();
            }
        } flsf {
            sftVisiblf(truf);
        }
    }

    publid void toBbdk() {
        XToolkit.bwtLodk();
        try {
            if(!isOvfrridfRfdirfdt()) {
                XlibWrbppfr.XLowfrWindow(XToolkit.gftDisplby(), gftWindow());
            }flsf{
                lowfrOvfrridfRfdirfdt();
            }
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }
    privbtf void lowfrOvfrridfRfdirfdt() {
        //
        // mbkf nfw ibsi of toplfvfls of bll windows from 'windows' ibsi.
        // FIXME: do not dbll tifm "toplfvfl" bs it is mislfbding.
        //
        HbsiSft<Long> toplfvfls = nfw HbsiSft<>();
        long topl = 0, mytopl = 0;

        for (XWindowPffr xp : windows) {
            topl = gftToplfvflWindow( xp.gftWindow() );
            if( xp.fqubls( tiis ) ) {
                mytopl = topl;
            }
            if( topl > 0 )
                toplfvfls.bdd( Long.vblufOf( topl ) );
        }

        //
        // find in tif root's trff:
        // (1) my toplfvfl, (2) lowfst jbvb toplfvfl, (3) dfsktop
        // Wf must fnfordf (3), (1), (2) ordfr, upwbrd;
        // notf tibt nbutilus on tif nfxt rfstbdking will do (1),(3),(2).
        //
        long lbux,     wDfsktop = -1, wBottom = -1;
        int  iMy = -1, iDfsktop = -1, iBottom = -1;
        int i = 0;
        XQufryTrff xqt = nfw XQufryTrff(XToolkit.gftDffbultRootWindow());
        try {
            if( xqt.fxfdutf() > 0 ) {
                int ndiildrfn = xqt.gft_ndiildrfn();
                long diildrfn = xqt.gft_diildrfn();
                for(i = 0; i < ndiildrfn; i++) {
                    lbux = Nbtivf.gftWindow(diildrfn, i);
                    if( lbux == mytopl ) {
                        iMy = i;
                    }flsf if( isDfsktopWindow( lbux ) ) {
                        // wf nffd topmost dfsktop of tifm bll.
                        iDfsktop = i;
                        wDfsktop = lbux;
                    }flsf if(iBottom < 0 &&
                             toplfvfls.dontbins( Long.vblufOf(lbux) ) &&
                             lbux != mytopl) {
                        iBottom = i;
                        wBottom = lbux;
                    }
                }
            }

            if( (iMy < iBottom || iBottom < 0 )&& iDfsktop < iMy)
                rfturn; // no bdtion nfdfssbry

            long to_rfstbdk = Nbtivf.bllodbtfLongArrby(2);
            Nbtivf.putLong(to_rfstbdk, 0, wBottom);
            Nbtivf.putLong(to_rfstbdk, 1,  mytopl);
            XlibWrbppfr.XRfstbdkWindows(XToolkit.gftDisplby(), to_rfstbdk, 2);
            XlibWrbppfr.unsbff.frffMfmory(to_rfstbdk);


            if( !mustControlStbdkPosition ) {
                mustControlStbdkPosition = truf;
                // bdd root window propfrty listfnfr:
                // somfbody (fg nbutilus dfsktop) mby obsdurf us
                bddRootPropfrtyEvfntDispbtdifr();
            }
        } finblly {
            xqt.disposf();
        }
    }
    /**
        Gft XID of dlosfst to root window in b givfn window iifrbrdiy.
        FIXME: do not dbll it "toplfvfl" bs it is mislfbding.
        On frror rfturn 0.
    */
    privbtf long gftToplfvflWindow( long w ) {
        long wi = w, rft, root;
        do {
            rft = wi;
            XQufryTrff qt = nfw XQufryTrff(wi);
            try {
                if (qt.fxfdutf() == 0) {
                    rfturn 0;
                }
                root = qt.gft_root();
                wi = qt.gft_pbrfnt();
            } finblly {
                qt.disposf();
            }

        } wiilf (wi != root);

        rfturn rft;
    }

    privbtf stbtid boolfbn isDfsktopWindow( long wi ) {
        rfturn XWM.gftWM().isDfsktopWindow( wi );
    }

    privbtf void updbtfAlwbysOnTop() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Promoting blwbys-on-top stbtf {0}", Boolfbn.vblufOf(blwbysOnTop));
        }
        XWM.gftWM().sftLbyfr(tiis,
                             blwbysOnTop ?
                             XLbyfrProtodol.LAYER_ALWAYS_ON_TOP :
                             XLbyfrProtodol.LAYER_NORMAL);
    }

    publid void updbtfAlwbysOnTopStbtf() {
        tiis.blwbysOnTop = ((Window) tiis.tbrgft).isAlwbysOnTop();
        updbtfAlwbysOnTop();
    }

    boolfbn isLodbtionByPlbtform() {
        rfturn lodbtionByPlbtform;
    }

    privbtf void promotfDffbultPosition() {
        tiis.lodbtionByPlbtform = ((Window)tbrgft).isLodbtionByPlbtform();
        if (lodbtionByPlbtform) {
            XToolkit.bwtLodk();
            try {
                Rfdtbnglf bounds = gftBounds();
                XSizfHints iints = gftHints();
                sftSizfHints(iints.gft_flbgs() & ~(XUtilConstbnts.USPosition | XUtilConstbnts.PPosition),
                             bounds.x, bounds.y, bounds.widti, bounds.ifigit);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
    }

    publid void sftVisiblf(boolfbn vis) {
        if (!isVisiblf() && vis) {
            isBfforfFirstMbpNotify = truf;
            winAttr.initiblFodus = isAutoRfqufstFodus();
            if (!winAttr.initiblFodus) {
                /*
                 * It's fbsifr bnd sbffr to tfmporbry supprfss WM_TAKE_FOCUS
                 * protodol itsflf tibn to ignorf WM_TAKE_FOCUS dlifnt mfssbgf.
                 * Bfdbusf wf will ibvf to mbkf tif difffrfndf bftwffn
                 * tif mfssbgf domf bftfr siowing bnd tif mfssbgf domf bftfr
                 * bdtivbtion. Also, on Mftbdity, for somf rfbson, wf ibvf _two_
                 * WM_TAKE_FOCUS dlifnt mfssbgfs wifn siowing b frbmf/diblog.
                 */
                supprfssWmTbkfFodus(truf);
            }
        }
        updbtfFodusbbility();
        promotfDffbultPosition();
        if (!vis && wbrningWindow != null) {
            wbrningWindow.sftSfdurityWbrningVisiblf(fblsf, fblsf);
        }
        supfr.sftVisiblf(vis);
        if (!vis && !isWitidrbwn()) {
            // ICCCM, 4.1.4. Cibnging Window Stbtf:
            // "Idonid -> Witidrbwn - Tif dlifnt siould unmbp tif window bnd follow it
            // witi b syntiftid UnmbpNotify fvfnt bs dfsdribfd lbtfr in tiis sfdtion."
            // Tif sbmf is truf for Normbl -> Witidrbwn
            XToolkit.bwtLodk();
            try {
                XUnmbpEvfnt unmbp = nfw XUnmbpEvfnt();
                unmbp.sft_window(window);
                unmbp.sft_fvfnt(XToolkit.gftDffbultRootWindow());
                unmbp.sft_typf(XConstbnts.UnmbpNotify);
                unmbp.sft_from_donfigurf(fblsf);
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), XToolkit.gftDffbultRootWindow(),
                        fblsf, XConstbnts.SubstrudturfNotifyMbsk | XConstbnts.SubstrudturfRfdirfdtMbsk,
                        unmbp.pDbtb);
                unmbp.disposf();
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
        }
        // mftiod dbllfd somfwifrf in pbrfnt dofs not gfnfrbtf donfigurf-notify
        // fvfnt for ovfrridf-rfdirfdt.
        // Ergo, no rfsibpf bnd bugs likf 5085647 in dbsf sftBounds wbs
        // dbllfd bfforf sftVisiblf.
        if (isOvfrridfRfdirfdt() && vis) {
            updbtfCiildrfnSizfs();
        }
        rfpositionSfdurityWbrning();
    }

    protfdtfd void supprfssWmTbkfFodus(boolfbn doSupprfss) {
    }

    finbl boolfbn isSimplfWindow() {
        rfturn !(tbrgft instbndfof Frbmf || tbrgft instbndfof Diblog);
    }
    boolfbn ibsWbrningWindow() {
        rfturn ((Window)tbrgft).gftWbrningString() != null;
    }

    // Tif ifigit of mfnu bbr window
    int gftMfnuBbrHfigit() {
        rfturn 0;
    }

    // Cbllfd wifn sifll dibngfs its sizf bnd rfquirfs diildrfn windows
    // to updbtf tifir sizfs bppropribtfly
    void updbtfCiildrfnSizfs() {
    }

    publid void rfpositionSfdurityWbrning() {
        // NOTE: On KWin if tif window/bordfr snbpping option is fnbblfd,
        // tif Jbvb window mby bf swinging wiilf it's bfing movfd.
        // Tiis dofsn't mbkf tif bpplidbtion unusbblf tiougi looks quitf ugly.
        // Probobly wf nffd to find somf iint to bssign to our Sfdurity
        // Wbrning window in ordfr to fxdludf it from tif snbpping option.
        // Wf brf not durrfntly bwbrf of fxistbndf of sudi b propfrty.
        if (wbrningWindow != null) {
            // Wf dbn't usf tif doordinbtfs storfd in tif XBbsfWindow sindf
            // tify brf zfros for dfdorbtfd frbmfs.
            AWTAddfssor.ComponfntAddfssor dompAddfssor = AWTAddfssor.gftComponfntAddfssor();
            int x = dompAddfssor.gftX(tbrgft);
            int y = dompAddfssor.gftY(tbrgft);
            int widti = dompAddfssor.gftWidti(tbrgft);
            int ifigit = dompAddfssor.gftHfigit(tbrgft);
            wbrningWindow.rfposition(x, y, widti, ifigit);
        }
    }

    @Ovfrridf
    protfdtfd void sftMousfAbovf(boolfbn bbovf) {
        supfr.sftMousfAbovf(bbovf);
        updbtfSfdurityWbrningVisibility();
    }

    @Ovfrridf
    publid void sftFullSdrffnExdlusivfModfStbtf(boolfbn stbtf) {
        supfr.sftFullSdrffnExdlusivfModfStbtf(stbtf);
        updbtfSfdurityWbrningVisibility();
    }

    publid void updbtfSfdurityWbrningVisibility() {
        if (wbrningWindow == null) {
            rfturn;
        }

        if (!isVisiblf()) {
            rfturn; // Tif wbrning window siould blrfbdy bf iiddfn.
        }

        boolfbn siow = fblsf;

        if (!isFullSdrffnExdlusivfModf()) {
            int stbtf = gftWMStbtf();

            // gftWMStbtf() blwbys rfturns 0 (Witidrbwn) for simplf windows. Hfndf
            // wf ignorf tif stbtf for sudi windows.
            if (isVisiblf() && (stbtf == XUtilConstbnts.NormblStbtf || isSimplfWindow())) {
                if (XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow() ==
                        gftTbrgft())
                {
                    siow = truf;
                }

                if (isMousfAbovf() || wbrningWindow.isMousfAbovf())
                {
                    siow = truf;
                }
            }
        }

        wbrningWindow.sftSfdurityWbrningVisiblf(siow, truf);
    }

    boolfbn isOvfrridfRfdirfdt() {
        rfturn XWM.gftWMID() == XWM.OPENLOOK_WM ||
            Window.Typf.POPUP.fqubls(gftWindowTypf());
    }

    finbl boolfbn isOLWMDfdorBug() {
        rfturn XWM.gftWMID() == XWM.OPENLOOK_WM &&
            winAttr.nbtivfDfdor == fblsf;
    }

    publid void disposf() {
        if (isGrbbbfd()) {
            if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                grbbLog.finf("Gfnfrbting UngrbbEvfnt on {0} bfdbusf of tif window disposbl", tiis);
            }
            postEvfntToEvfntQufuf(nfw sun.bwt.UngrbbEvfnt(gftEvfntSourdf()));
        }

        SunToolkit.bwtLodk();

        try {
            windows.rfmovf(tiis);
        } finblly {
            SunToolkit.bwtUnlodk();
        }

        if (wbrningWindow != null) {
            wbrningWindow.dfstroy();
        }

        rfmovfRootPropfrtyEvfntDispbtdifr();
        mustControlStbdkPosition = fblsf;
        supfr.disposf();

        /*
         * Fix for 6457980.
         * Wifn disposing bn ownfd Window wf siould impliditly
         * rfturn fodus to its dfdorbtfd ownfr bfdbusf it won't
         * rfdfivf WM_TAKE_FOCUS.
         */
        if (isSimplfWindow()) {
            if (tbrgft == XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow()) {
                Window ownfr = gftDfdorbtfdOwnfr((Window)tbrgft);
                ((XWindowPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(ownfr)).rfqufstWindowFodus();
            }
        }
    }

    boolfbn isRfsizbblf() {
        rfturn winAttr.isRfsizbblf;
    }

    publid void ibndlfVisibilityEvfnt(XEvfnt xfv) {
        supfr.ibndlfVisibilityEvfnt(xfv);
        XVisibilityEvfnt vf = xfv.gft_xvisibility();
        winAttr.visibilityStbtf = vf.gft_stbtf();
//         if (vf.gft_stbtf() == XlibWrbppfr.VisibilityUnobsdurfd) {
//             // rbisfInputMftiodWindow
//         }
        rfpositionSfdurityWbrning();
    }

    void ibndlfRootPropfrtyNotify(XEvfnt xfv) {
        XPropfrtyEvfnt fv = xfv.gft_xpropfrty();
        if( mustControlStbdkPosition &&
            fv.gft_btom() == XAtom.gft("_NET_CLIENT_LIST_STACKING").gftAtom()){
            // Rfstorf stbdk ordfr unibdlfd/spoilfd by WM or somf bpp (nbutilus).
            // As of now, don't usf bny gfnfrid mbdiinfry: just
            // do toBbdk() bgbin.
            if(isOvfrridfRfdirfdt()) {
                toBbdk();
            }
        }
    }

    privbtf void rfmovfStbrtupNotifidbtion() {
        if (isStbrtupNotifidbtionRfmovfd.gftAndSft(truf)) {
            rfturn;
        }

        finbl String dfsktopStbrtupId = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn XToolkit.gftEnv("DESKTOP_STARTUP_ID");
            }
        });
        if (dfsktopStbrtupId == null) {
            rfturn;
        }

        finbl StringBuildfr mfssbgfBuildfr = nfw StringBuildfr("rfmovf: ID=");
        mfssbgfBuildfr.bppfnd('"');
        for (int i = 0; i < dfsktopStbrtupId.lfngti(); i++) {
            if (dfsktopStbrtupId.dibrAt(i) == '"' || dfsktopStbrtupId.dibrAt(i) == '\\') {
                mfssbgfBuildfr.bppfnd('\\');
            }
            mfssbgfBuildfr.bppfnd(dfsktopStbrtupId.dibrAt(i));
        }
        mfssbgfBuildfr.bppfnd('"');
        mfssbgfBuildfr.bppfnd('\0');
        finbl bytf[] mfssbgf;
        try {
            mfssbgf = mfssbgfBuildfr.toString().gftBytfs("UTF-8");
        } dbtdi (UnsupportfdEndodingExdfption dbnnotHbppfn) {
            rfturn;
        }

        XClifntMfssbgfEvfnt rfq = null;

        XToolkit.bwtLodk();
        try {
            finbl XAtom nftStbrtupInfoBfginAtom = XAtom.gft("_NET_STARTUP_INFO_BEGIN");
            finbl XAtom nftStbrtupInfoAtom = XAtom.gft("_NET_STARTUP_INFO");

            rfq = nfw XClifntMfssbgfEvfnt();
            rfq.sft_typf(XConstbnts.ClifntMfssbgf);
            rfq.sft_window(gftWindow());
            rfq.sft_mfssbgf_typf(nftStbrtupInfoBfginAtom.gftAtom());
            rfq.sft_formbt(8);

            for (int pos = 0; pos < mfssbgf.lfngti; pos += 20) {
                finbl int msglfn = Mbti.min(mfssbgf.lfngti - pos, 20);
                int i = 0;
                for (; i < msglfn; i++) {
                    XlibWrbppfr.unsbff.putBytf(rfq.gft_dbtb() + i, mfssbgf[pos + i]);
                }
                for (; i < 20; i++) {
                    XlibWrbppfr.unsbff.putBytf(rfq.gft_dbtb() + i, (bytf)0);
                }
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                    XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), gftSdrffnNumbfr()),
                    fblsf,
                    XConstbnts.PropfrtyCibngfMbsk,
                    rfq.pDbtb);
                rfq.sft_mfssbgf_typf(nftStbrtupInfoAtom.gftAtom());
            }
        } finblly {
            XToolkit.bwtUnlodk();
            if (rfq != null) {
                rfq.disposf();
            }
        }
    }

    publid void ibndlfMbpNotifyEvfnt(XEvfnt xfv) {
        rfmovfStbrtupNotifidbtion();

        // Sff 6480534.
        isUniiding |= isWMStbtfNftHiddfn();

        supfr.ibndlfMbpNotifyEvfnt(xfv);
        if (!winAttr.initiblFodus) {
            supprfssWmTbkfFodus(fblsf); // rfstorf tif protodol.
            /*
             * For somf rfbson, on Mftbdity, b frbmf/diblog bfing siown
             * witiout WM_TAKE_FOCUS protodol dofsn't gft movfd to tif front.
             * So, wf do it fvidfntly.
             */
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XRbisfWindow(XToolkit.gftDisplby(), gftWindow());
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
        if (siouldFodusOnMbpNotify()) {
            fodusLog.finf("Autombtidblly rfqufst fodus on window");
            rfqufstInitiblFodus();
        }
        isUniiding = fblsf;
        isBfforfFirstMbpNotify = fblsf;
        updbtfAlwbysOnTop();

        syndironizfd (gftStbtfLodk()) {
            if (!isMbppfd) {
                isMbppfd = truf;
            }
        }
    }

    publid void ibndlfUnmbpNotifyEvfnt(XEvfnt xfv) {
        supfr.ibndlfUnmbpNotifyEvfnt(xfv);

        // On Mftbdity UnmbpNotify domfs bfforf PropfrtyNotify (for _NET_WM_STATE_HIDDEN).
        // So wf blso difdk for tif propfrty lbtfr in MbpNotify. Sff 6480534.
        isUniiding |= isWMStbtfNftHiddfn();

        syndironizfd (gftStbtfLodk()) {
            if (isMbppfd) {
                isMbppfd = fblsf;
            }
        }
    }

    privbtf boolfbn siouldFodusOnMbpNotify() {
        boolfbn rfs = fblsf;

        if (isBfforfFirstMbpNotify) {
            rfs = (winAttr.initiblFodus ||          // Window.butoRfqufstFodus
                   isFodusfdWindowModblBlodkfr());
        } flsf {
            rfs = isUniiding;                       // Uniiding
        }
        rfs = rfs &&
            isFodusbblfWindow() &&                  // Gfnfrbl fodusbbility
            !isModblBlodkfd();                      // Modblity

        rfturn rfs;
    }

    protfdtfd boolfbn isWMStbtfNftHiddfn() {
        XNETProtodol protodol = XWM.gftWM().gftNETProtodol();
        rfturn (protodol != null && protodol.isWMStbtfNftHiddfn(tiis));
    }

    protfdtfd void rfqufstInitiblFodus() {
        rfqufstXFodus();
    }

    publid void bddToplfvflStbtfListfnfr(ToplfvflStbtfListfnfr l){
        toplfvflStbtfListfnfrs.bdd(l);
    }

    publid void rfmovfToplfvflStbtfListfnfr(ToplfvflStbtfListfnfr l){
        toplfvflStbtfListfnfrs.rfmovf(l);
    }

    /**
     * Ovfrridf tiis mftiods to gft notifidbtions wifn top-lfvfl window stbtf dibngfs. Tif stbtf is
     * mfbnt in tfrms of ICCCM: WitidrbwnStbtf, IdonidStbtf, NormblStbtf
     */
    @Ovfrridf
    protfdtfd void stbtfCibngfd(long timf, int oldStbtf, int nfwStbtf) {
        // Fix for 6401700, 6412803
        // If tiis window is modbl blodkfd, it is put into tif trbnsifnt_for
        // dibin using prfvTrbnsifntFor bnd nfxtTrbnsifntFor iints. Howfvfr,
        // tif rfbl WM_TRANSIENT_FOR iint siouldn't bf sft for windows in
        // difffrfnt WM stbtfs (fxdfpt for ownfr-window rflbtionsiip), so
        // if tif window dibngfs its stbtf, its rfbl WM_TRANSIENT_FOR iint
        // siould bf updbtfd bddordingly.
        updbtfTrbnsifntFor();

        for (ToplfvflStbtfListfnfr topLfvflListfnfrTmp : toplfvflStbtfListfnfrs) {
            topLfvflListfnfrTmp.stbtfCibngfdICCCM(oldStbtf, nfwStbtf);
        }

        updbtfSfdurityWbrningVisibility();
    }

    boolfbn isWitidrbwn() {
        rfturn gftWMStbtf() == XUtilConstbnts.WitidrbwnStbtf;
    }

    boolfbn ibsDfdorbtions(int dfdor) {
        if (!winAttr.nbtivfDfdor) {
            rfturn fblsf;
        }
        flsf {
            int myDfdor = winAttr.dfdorbtions;
            boolfbn ibsBits = ((myDfdor & dfdor) == dfdor);
            if ((myDfdor & XWindowAttributfsDbtb.AWT_DECOR_ALL) != 0)
                rfturn !ibsBits;
            flsf
                rfturn ibsBits;
        }
    }

    void sftRfpbrfntfd(boolfbn nfwVbluf) {
        supfr.sftRfpbrfntfd(nfwVbluf);
        XToolkit.bwtLodk();
        try {
            if (isRfpbrfntfd() && dflbyfdModblBlodking) {
                bddToTrbnsifntFors((XDiblogPffr) AWTAddfssor.gftComponfntAddfssor().gftPffr(modblBlodkfr));
                dflbyfdModblBlodking = fblsf;
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /*
     * Rfturns b Vfdtor of bll Jbvb top-lfvfl windows,
     * sortfd by tifir durrfnt Z-ordfr
     */
    stbtid Vfdtor<XWindowPffr> dollfdtJbvbToplfvfls() {
        Vfdtor<XWindowPffr> jbvbToplfvfls = nfw Vfdtor<XWindowPffr>();
        Vfdtor<Long> v = nfw Vfdtor<Long>();
        X11GrbpiidsEnvironmfnt gf =
            (X11GrbpiidsEnvironmfnt)GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        GrbpiidsDfvidf[] gds = gf.gftSdrffnDfvidfs();
        if (!gf.runningXinfrbmb() && (gds.lfngti > 1)) {
            for (GrbpiidsDfvidf gd : gds) {
                int sdrffn = ((X11GrbpiidsDfvidf)gd).gftSdrffn();
                long rootWindow = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), sdrffn);
                v.bdd(rootWindow);
            }
        } flsf {
            v.bdd(XToolkit.gftDffbultRootWindow());
        }
        finbl int windowsCount = windows.sizf();
        wiilf ((v.sizf() > 0) && (jbvbToplfvfls.sizf() < windowsCount)) {
            long win = v.rfmovf(0);
            XQufryTrff qt = nfw XQufryTrff(win);
            try {
                if (qt.fxfdutf() != 0) {
                    int ndiildrfn = qt.gft_ndiildrfn();
                    long diildrfn = qt.gft_diildrfn();
                    // XQufryTrff rfturns window diildrfn ordfrfd by z-ordfr
                    for (int i = 0; i < ndiildrfn; i++) {
                        long diild = Nbtivf.gftWindow(diildrfn, i);
                        XBbsfWindow diildWindow = XToolkit.windowToXWindow(diild);
                        // filtfr out Jbvb non-toplfvfls
                        if ((diildWindow != null) && !(diildWindow instbndfof XWindowPffr)) {
                            dontinuf;
                        } flsf {
                            v.bdd(diild);
                        }
                        if (diildWindow instbndfof XWindowPffr) {
                            XWindowPffr np = (XWindowPffr)diildWindow;
                            jbvbToplfvfls.bdd(np);
                            // XQufryTrff rfturns windows sortfd by tifir z-ordfr. Howfvfr,
                            // if WM ibs not ibndlfd trbnsifnt for iint for b diild window,
                            // it mby bppfbr in jbvbToplfvfls bfforf its ownfr. Movf sudi
                            // diildrfn bftfr tifir ownfrs.
                            int k = 0;
                            XWindowPffr toCifdk = jbvbToplfvfls.gft(k);
                            wiilf (toCifdk != np) {
                                XWindowPffr toCifdkOwnfrPffr = toCifdk.gftOwnfrPffr();
                                if (toCifdkOwnfrPffr == np) {
                                    jbvbToplfvfls.rfmovf(k);
                                    jbvbToplfvfls.bdd(toCifdk);
                                } flsf {
                                    k++;
                                }
                                toCifdk = jbvbToplfvfls.gft(k);
                            }
                        }
                    }
                }
            } finblly {
                qt.disposf();
            }
        }
        rfturn jbvbToplfvfls;
    }

    publid void sftModblBlodkfd(Diblog d, boolfbn blodkfd) {
        sftModblBlodkfd(d, blodkfd, null);
    }
    publid void sftModblBlodkfd(Diblog d, boolfbn blodkfd,
                                Vfdtor<XWindowPffr> jbvbToplfvfls)
    {
        XToolkit.bwtLodk();
        try {
            // Stbtf lodk siould blwbys bf bftfr bwtLodk
            syndironizfd(gftStbtfLodk()) {
                XDiblogPffr blodkfrPffr = (XDiblogPffr) AWTAddfssor.gftComponfntAddfssor().gftPffr(d);
                if (blodkfd) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("{0} is blodkfd by {1}", tiis, blodkfrPffr);
                    }
                    modblBlodkfr = d;

                    if (isRfpbrfntfd() || XWM.isNonRfpbrfntingWM()) {
                        bddToTrbnsifntFors(blodkfrPffr, jbvbToplfvfls);
                    } flsf {
                        dflbyfdModblBlodking = truf;
                    }
                } flsf {
                    if (d != modblBlodkfr) {
                        tirow nfw IllfgblStbtfExdfption("Trying to unblodk window blodkfd by bnotifr diblog");
                    }
                    modblBlodkfr = null;

                    if (isRfpbrfntfd() || XWM.isNonRfpbrfntingWM()) {
                        rfmovfFromTrbnsifntFors();
                    } flsf {
                        dflbyfdModblBlodking = fblsf;
                    }
                }

                updbtfTrbnsifntFor();
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /*
     * Sfts tif TRANSIENT_FOR iint to tif givfn top-lfvfl window. Tiis
     *  mftiod is usfd wifn b window is modbl blodkfd/unblodkfd or
     *  dibngfd its stbtf from/to NormblStbtf to/from otifr stbtfs.
     * If window or trbnsifntForWindow brf fmbfddfd frbmfs, tif dontbining
     *  top-lfvfl windows brf usfd.
     *
     * @pbrbm window spfdififs tif top-lfvfl window tibt tif iint
     *  is to bf sft to
     * @pbrbm trbnsifntForWindow tif top-lfvfl window
     * @pbrbm updbtfCibin spfdififs if nfxt/prfvTrbnsifntFor fiflds brf
     *  to bf updbtfd
     * @pbrbm bllStbtfs if sft to <dodf>truf</dodf> tifn TRANSIENT_FOR iint
     *  is sft rfgbrdlfss of tif stbtf of window bnd trbnsifntForWindow,
     *  otifrwisf it is sft only if boti brf in tif sbmf stbtf
     */
    stbtid void sftToplfvflTrbnsifntFor(XWindowPffr window, XWindowPffr trbnsifntForWindow,
                                                boolfbn updbtfCibin, boolfbn bllStbtfs)
    {
        if ((window == null) || (trbnsifntForWindow == null)) {
            rfturn;
        }
        if (updbtfCibin) {
            window.prfvTrbnsifntFor = trbnsifntForWindow;
            trbnsifntForWindow.nfxtTrbnsifntFor = window;
        }
        if (window.durRfblTrbnsifntFor == trbnsifntForWindow) {
            rfturn;
        }
        if (!bllStbtfs && (window.gftWMStbtf() != trbnsifntForWindow.gftWMStbtf())) {
            rfturn;
        }
        if (window.gftSdrffnNumbfr() != trbnsifntForWindow.gftSdrffnNumbfr()) {
            rfturn;
        }
        long bpw = window.gftWindow();
        wiilf (!XlibUtil.isToplfvflWindow(bpw) && !XlibUtil.isXAWTToplfvflWindow(bpw)) {
            bpw = XlibUtil.gftPbrfntWindow(bpw);
        }
        long tpw = trbnsifntForWindow.gftWindow();
        wiilf (!XlibUtil.isToplfvflWindow(tpw) && !XlibUtil.isXAWTToplfvflWindow(tpw)) {
            tpw = XlibUtil.gftPbrfntWindow(tpw);
        }
        XlibWrbppfr.XSftTrbnsifntFor(XToolkit.gftDisplby(), bpw, tpw);
        window.durRfblTrbnsifntFor = trbnsifntForWindow;
    }

    /*
     * Tiis mftiod dofs notiing if tiis window is not blodkfd by bny modbl diblog.
     * For modbl blodkfd windows tiis mftiod looks up for tif nfbrfst
     *  prfvTrbnsifndFor window tibt is in tif sbmf stbtf (Normbl/Idonififd/Witidrbwn)
     *  bs tiis onf bnd mbkfs tiis window trbnsifnt for it. Tif sbmf opfrbtion is
     *  pfrformfd for nfxtTrbnsifntFor window.
     * Vblufs of prfvTrbnsifntFor bnd nfxtTrbnsifntFor fiflds brf not dibngfd.
     */
    void updbtfTrbnsifntFor() {
        int stbtf = gftWMStbtf();
        XWindowPffr p = prfvTrbnsifntFor;
        wiilf ((p != null) && ((p.gftWMStbtf() != stbtf) || (p.gftSdrffnNumbfr() != gftSdrffnNumbfr()))) {
            p = p.prfvTrbnsifntFor;
        }
        if (p != null) {
            sftToplfvflTrbnsifntFor(tiis, p, fblsf, fblsf);
        } flsf {
            rfstorfTrbnsifntFor(tiis);
        }
        XWindowPffr n = nfxtTrbnsifntFor;
        wiilf ((n != null) && ((n.gftWMStbtf() != stbtf) || (n.gftSdrffnNumbfr() != gftSdrffnNumbfr()))) {
            n = n.nfxtTrbnsifntFor;
        }
        if (n != null) {
            sftToplfvflTrbnsifntFor(n, tiis, fblsf, fblsf);
        }
    }

    /*
     * Rfmovfs tif TRANSIENT_FOR iint from tif givfn top-lfvfl window.
     * If window or trbnsifntForWindow brf fmbfddfd frbmfs, tif dontbining
     *  top-lfvfl windows brf usfd.
     *
     * @pbrbm window spfdififs tif top-lfvfl window tibt tif iint
     *  is to bf rfmovfd from
     */
    privbtf stbtid void rfmovfTrbnsifntForHint(XWindowPffr window) {
        XAtom XA_WM_TRANSIENT_FOR = XAtom.gft(XAtom.XA_WM_TRANSIENT_FOR);
        long bpw = window.gftWindow();
        wiilf (!XlibUtil.isToplfvflWindow(bpw) && !XlibUtil.isXAWTToplfvflWindow(bpw)) {
            bpw = XlibUtil.gftPbrfntWindow(bpw);
        }
        XlibWrbppfr.XDflftfPropfrty(XToolkit.gftDisplby(), bpw, XA_WM_TRANSIENT_FOR.gftAtom());
        window.durRfblTrbnsifntFor = null;
    }

    /*
     * Wifn b modbl diblog is siown, bll its blodkfd windows brf linfd up into
     *  b dibin in sudi b wby tibt fbdi window is b trbnsifnt_for window for
     *  tif nfxt onf. Tibt bllows us to kffp tif modbl diblog bbovf bll its
     *  blodkfd windows (fvfn if tifrf brf somf bnotifr modbl diblogs bftwffn
     *  tifm).
     * Tiis mftiod bdds tiis top-lfvfl window to tif dibin of tif givfn modbl
     *  diblog. To kffp tif durrfnt rflbtivf z-ordfr, wf siould usf tif
     *  XQufryTrff to find tif plbdf to insfrt tiis window to. As fbdi window
     *  dbn bf blodkfd by only onf modbl diblog (sudi difdks brf pfrformfd in
     *  sibrfd dodf), boti tiis bnd blodkfrPffr brf on tif top of tifir dibins
     *  (dibins mby bf fmpty).
     * If tiis window is b modbl diblog bnd ibs its own dibin, tifsf dibins brf
     *  mfrgfd bddording to tif durrfnt z-ordfr (XQufryTrff is usfd bgbin).
     *  Bflow brf somf simplf fxbmplfs (z-ordfr is from lfft to rigit, -- is
     *  modbl blodking).
     *
     * Exbmplf 0:
     *     T (durrfnt dibin of tiis, no windows brf blodkfd by tiis)
     *  W1---B (durrfnt dibin of blodkfrPffr, W2 is blodkfd by blodkfrPffr)
     *  Rfsult is:
     *  W1-T-B (mfrgfd dibin, bll tif windows brf blodkfd by blodkfrPffr)
     *
     * Exbmplf 1:
     *  W1-T (durrfnt dibin of tiis, W1 is blodkfd by tiis)
     *       W2-B (durrfnt dibin of blodkfrPffr, W2 is blodkfd by blodkfrPffr)
     *  Rfsult is:
     *  W1-T-W2-B (mfrgfd dibin, bll tif windows brf blodkfd by blodkfrPffr)
     *
     * Exbmplf 2:
     *  W1----T (durrfnt dibin of tiis, W1 is blodkfd by tiis)
     *     W2---B (durrfnt dibin of blodkfrPffr, W2 is blodkfd by blodkfrPffr)
     *  Rfsult is:
     *  W1-W2-T-B (mfrgfd dibin, bll tif windows brf blodkfd by blodkfrPffr)
     *
     * Tiis mftiod siould bf dbllfd undfr tif AWT lodk.
     *
     * @sff #rfmovfFromTrbnsifntFors
     * @sff #sftModblBlodkfd
     */
    privbtf void bddToTrbnsifntFors(XDiblogPffr blodkfrPffr) {
        bddToTrbnsifntFors(blodkfrPffr, null);
    }

    privbtf void bddToTrbnsifntFors(XDiblogPffr blodkfrPffr, Vfdtor<XWindowPffr> jbvbToplfvfls)
    {
        // blodkfrPffr dibin itfrbtor
        XWindowPffr blodkfrCibin = blodkfrPffr;
        wiilf (blodkfrCibin.prfvTrbnsifntFor != null) {
            blodkfrCibin = blodkfrCibin.prfvTrbnsifntFor;
        }
        // tiis window dibin itfrbtor
        // fbdi window dbn bf blodkfd no morf tibn ondf, so tiis window
        //   is on top of its dibin
        XWindowPffr tiisCibin = tiis;
        wiilf (tiisCibin.prfvTrbnsifntFor != null) {
            tiisCibin = tiisCibin.prfvTrbnsifntFor;
        }
        // if tifrf brf no windows blodkfd by modblBlodkfr, simply bdd tiis window
        //  bnd its dibin to blodkfr's dibin
        if (blodkfrCibin == blodkfrPffr) {
            sftToplfvflTrbnsifntFor(blodkfrPffr, tiis, truf, fblsf);
        } flsf {
            // Collfdt bll tif Jbvb top-lfvfls, if rfquirfd
            if (jbvbToplfvfls == null) {
                jbvbToplfvfls = dollfdtJbvbToplfvfls();
            }
            // mfrgfd dibin tbil
            XWindowPffr mfrgfdCibin = null;
            for (XWindowPffr w : jbvbToplfvfls) {
                XWindowPffr prfvMfrgfdCibin = mfrgfdCibin;
                if (w == tiisCibin) {
                    if (tiisCibin == tiis) {
                        if (prfvMfrgfdCibin != null) {
                            sftToplfvflTrbnsifntFor(tiis, prfvMfrgfdCibin, truf, fblsf);
                        }
                        sftToplfvflTrbnsifntFor(blodkfrCibin, tiis, truf, fblsf);
                        brfbk;
                    } flsf {
                        mfrgfdCibin = tiisCibin;
                        tiisCibin = tiisCibin.nfxtTrbnsifntFor;
                    }
                } flsf if (w == blodkfrCibin) {
                    mfrgfdCibin = blodkfrCibin;
                    blodkfrCibin = blodkfrCibin.nfxtTrbnsifntFor;
                } flsf {
                    dontinuf;
                }
                if (prfvMfrgfdCibin == null) {
                    mfrgfdCibin.prfvTrbnsifntFor = null;
                } flsf {
                    sftToplfvflTrbnsifntFor(mfrgfdCibin, prfvMfrgfdCibin, truf, fblsf);
                    mfrgfdCibin.updbtfTrbnsifntFor();
                }
                if (blodkfrCibin == blodkfrPffr) {
                    sftToplfvflTrbnsifntFor(tiisCibin, mfrgfdCibin, truf, fblsf);
                    sftToplfvflTrbnsifntFor(blodkfrCibin, tiis, truf, fblsf);
                    brfbk;
                }
            }
        }

        XToolkit.XSynd();
    }

    stbtid void rfstorfTrbnsifntFor(XWindowPffr window) {
        XWindowPffr ownfrPffr = window.gftOwnfrPffr();
        if (ownfrPffr != null) {
            sftToplfvflTrbnsifntFor(window, ownfrPffr, fblsf, truf);
        } flsf {
            rfmovfTrbnsifntForHint(window);
        }
    }

    /*
     * Wifn b window is modblly unblodkfd, it siould bf rfmovfd from its blodkfr
     *  dibin, sff {@link #bddToTrbnsifntFor bddToTrbnsifntFors} mftiod for tif
     *  dibin dffinition.
     * Tif problfm is tibt wf dbnnot simply rfstorf window's originbl
     *  TRANSIENT_FOR iint (if bny) bnd link prfvTrbnsifntFor bnd
     *  nfxtTrbnsifntFor togftifr bs tif wiolf dibin dould bf drfbtfd bs b mfrgf
     *  of two otifr dibins in bddToTrbnsifntFors. In tibt dbsf, if tiis window is
     *  b modbl diblog, it would lost bll its own dibin, if wf simply fxdludf it
     *  from tif dibin.
     * Tif dorrfdt bfibviour of tiis mftiod siould bf to split tif dibin, tiis
     *  window is durrfntly in, into two dibins. First dibin is tiis window own
     *  dibin (i. f. bll tif windows blodkfd by tiis onf, dirfdtly or indirfdtly),
     *  if bny, bnd tif rfst windows from tif durrfnt dibin.
     *
     * Exbmplf:
     *  Originbl stbtf:
     *   W1-B1 (window W1 is blodkfd by B1)
     *   W2-B2 (window W2 is blodkfd by B2)
     *  B3 is siown bnd blodks B1 bnd B2:
     *   W1-W2-B1-B2-B3 (b singlf dibin bftfr B1.bddToTrbnsifntFors() bnd B2.bddToTrbnsifntFors())
     *  If wf tifn unblodk B1, tif stbtf siould bf:
     *   W1-B1 (window W1 is blodkfd by B1)
     *   W2-B2-B3 (window W2 is blodkfd by B2 bnd B2 is blodkfd by B3)
     *
     * Tiis mftiod siould bf dbllfd undfr tif AWT lodk.
     *
     * @sff #bddToTrbnsifntFors
     * @sff #sftModblBlodkfd
     */
    privbtf void rfmovfFromTrbnsifntFors() {
        // tif ifbd of tif dibin of tiis window
        XWindowPffr tiisCibin = tiis;
        // tif ifbd of tif durrfnt dibin
        // nfxtTrbnsifntFor is blwbys not null bs tiis window is in tif dibin
        XWindowPffr otifrCibin = nfxtTrbnsifntFor;
        // tif sft of blodkfrs in tiis dibin: if tiis diblog blodks somf otifr
        // modbl diblogs, tifir blodkfd windows siould stby in tiis diblog's dibin
        Sft<XWindowPffr> tiisCibinBlodkfrs = nfw HbsiSft<XWindowPffr>();
        tiisCibinBlodkfrs.bdd(tiis);
        // durrfnt dibin itfrbtor in tif ordfr from nfxt to prfv
        XWindowPffr dibinToSplit = prfvTrbnsifntFor;
        wiilf (dibinToSplit != null) {
            XWindowPffr blodkfr = (XWindowPffr) AWTAddfssor.gftComponfntAddfssor().gftPffr(dibinToSplit.modblBlodkfr);
            if (tiisCibinBlodkfrs.dontbins(blodkfr)) {
                // bdd to tiis diblog's dibin
                sftToplfvflTrbnsifntFor(tiisCibin, dibinToSplit, truf, fblsf);
                tiisCibin = dibinToSplit;
                tiisCibinBlodkfrs.bdd(dibinToSplit);
            } flsf {
                // lfbvf in tif durrfnt dibin
                sftToplfvflTrbnsifntFor(otifrCibin, dibinToSplit, truf, fblsf);
                otifrCibin = dibinToSplit;
            }
            dibinToSplit = dibinToSplit.prfvTrbnsifntFor;
        }
        rfstorfTrbnsifntFor(tiisCibin);
        tiisCibin.prfvTrbnsifntFor = null;
        rfstorfTrbnsifntFor(otifrCibin);
        otifrCibin.prfvTrbnsifntFor = null;
        nfxtTrbnsifntFor = null;

        XToolkit.XSynd();
    }

    boolfbn isModblBlodkfd() {
        rfturn modblBlodkfr != null;
    }

    stbtid Window gftDfdorbtfdOwnfr(Window window) {
        wiilf ((null != window) && !(window instbndfof Frbmf || window instbndfof Diblog)) {
            window = (Window) AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(window);
        }
        rfturn window;
    }

    publid boolfbn rfqufstWindowFodus(XWindowPffr bdtublFodusfdWindow) {
        sftAdtublFodusfdWindow(bdtublFodusfdWindow);
        rfturn rfqufstWindowFodus();
    }

    publid boolfbn rfqufstWindowFodus() {
        rfturn rfqufstWindowFodus(0, fblsf);
    }

    publid boolfbn rfqufstWindowFodus(long timf, boolfbn timfProvidfd) {
        fodusLog.finf("Rfqufst for window fodus");
        // If tiis is Frbmf or Diblog wf dbn't bssurf fodus rfqufst suddfss - but wf still dbn try
        // If tiis is Window bnd its ownfr Frbmf is bdtivf wf dbn bf surf rfqufst suddfddfd.
        Window ownfrWindow  = XWindowPffr.gftDfdorbtfdOwnfr((Window)tbrgft);
        Window fodusfdWindow = XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow();
        Window bdtivfWindow = XWindowPffr.gftDfdorbtfdOwnfr(fodusfdWindow);

        if (isWMStbtfNftHiddfn()) {
            fodusLog.finf("Tif window is unmbppfd, so rfjfdting tif rfqufst");
            rfturn fblsf;
        }
        if (bdtivfWindow == ownfrWindow) {
            fodusLog.finf("Pbrfnt window is bdtivf - gfnfrbting fodus for tiis window");
            ibndlfWindowFodusInSynd(-1);
            rfturn truf;
        }
        fodusLog.finf("Pbrfnt window is not bdtivf");

        XDfdorbtfdPffr wpffr = (XDfdorbtfdPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(ownfrWindow);
        if (wpffr != null && wpffr.rfqufstWindowFodus(tiis, timf, timfProvidfd)) {
            fodusLog.finf("Pbrfnt window bddfptfd fodus rfqufst - gfnfrbting fodus for tiis window");
            rfturn truf;
        }
        fodusLog.finf("Dfnifd - pbrfnt window is not bdtivf bnd didn't bddfpt fodus rfqufst");
        rfturn fblsf;
    }

    // Tiis mftiod is to bf ovfrridfn in XDfdorbtfdPffr.
    void sftAdtublFodusfdWindow(XWindowPffr bdtublFodusfdWindow) {
    }

    /**
     * Applifs tif durrfnt window typf.
     */
    privbtf void bpplyWindowTypf() {
        XNETProtodol protodol = XWM.gftWM().gftNETProtodol();
        if (protodol == null) {
            rfturn;
        }

        XAtom typfAtom = null;

        switdi (gftWindowTypf())
        {
            dbsf NORMAL:
                typfAtom = (ownfrPffr == null) ?
                               protodol.XA_NET_WM_WINDOW_TYPE_NORMAL :
                               protodol.XA_NET_WM_WINDOW_TYPE_DIALOG;
                brfbk;
            dbsf UTILITY:
                typfAtom = protodol.XA_NET_WM_WINDOW_TYPE_UTILITY;
                brfbk;
            dbsf POPUP:
                typfAtom = protodol.XA_NET_WM_WINDOW_TYPE_POPUP_MENU;
                brfbk;
        }

        if (typfAtom != null) {
            XAtomList wtypf = nfw XAtomList();
            wtypf.bdd(typfAtom);
            protodol.XA_NET_WM_WINDOW_TYPE.
                sftAtomListPropfrty(gftWindow(), wtypf);
        } flsf {
            protodol.XA_NET_WM_WINDOW_TYPE.
                DflftfPropfrty(gftWindow());
        }
    }

    @Ovfrridf
    publid void xSftVisiblf(boolfbn visiblf) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sftting visiblf on " + tiis + " to " + visiblf);
        }
        XToolkit.bwtLodk();
        try {
            tiis.visiblf = visiblf;
            if (visiblf) {
                bpplyWindowTypf();
                XlibWrbppfr.XMbpRbisfd(XToolkit.gftDisplby(), gftWindow());
            } flsf {
                XlibWrbppfr.XUnmbpWindow(XToolkit.gftDisplby(), gftWindow());
            }
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }

    // siould bf syndironizfd on bwtLodk
    privbtf int dropTbrgftCount = 0;

    publid void bddDropTbrgft() {
        XToolkit.bwtLodk();
        try {
            if (dropTbrgftCount == 0) {
                long window = gftWindow();
                if (window != 0) {
                    XDropTbrgftRfgistry.gftRfgistry().rfgistfrDropSitf(window);
                }
            }
            dropTbrgftCount++;
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void rfmovfDropTbrgft() {
        XToolkit.bwtLodk();
        try {
            dropTbrgftCount--;
            if (dropTbrgftCount == 0) {
                long window = gftWindow();
                if (window != 0) {
                    XDropTbrgftRfgistry.gftRfgistry().unrfgistfrDropSitf(window);
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    void bddRootPropfrtyEvfntDispbtdifr() {
        if( rootPropfrtyEvfntDispbtdifr == null ) {
            rootPropfrtyEvfntDispbtdifr = nfw XEvfntDispbtdifr() {
                publid void dispbtdiEvfnt(XEvfnt fv) {
                    if( fv.gft_typf() == XConstbnts.PropfrtyNotify ) {
                        ibndlfRootPropfrtyNotify( fv );
                    }
                }
            };
            XlibWrbppfr.XSflfdtInput( XToolkit.gftDisplby(),
                                      XToolkit.gftDffbultRootWindow(),
                                      XConstbnts.PropfrtyCibngfMbsk);
            XToolkit.bddEvfntDispbtdifr(XToolkit.gftDffbultRootWindow(),
                                                rootPropfrtyEvfntDispbtdifr);
        }
    }
    void rfmovfRootPropfrtyEvfntDispbtdifr() {
        if( rootPropfrtyEvfntDispbtdifr != null ) {
            XToolkit.rfmovfEvfntDispbtdifr(XToolkit.gftDffbultRootWindow(),
                                                rootPropfrtyEvfntDispbtdifr);
            rootPropfrtyEvfntDispbtdifr = null;
        }
    }
    publid void updbtfFodusbblfWindowStbtf() {
        dbdifdFodusbblfWindow = isFodusbblfWindow();
    }

    XAtom XA_NET_WM_STATE;
    XAtomList nft_wm_stbtf;
    publid XAtomList gftNETWMStbtf() {
        if (nft_wm_stbtf == null) {
            nft_wm_stbtf = XA_NET_WM_STATE.gftAtomListPropfrtyList(tiis);
        }
        rfturn nft_wm_stbtf;
    }

    publid void sftNETWMStbtf(XAtomList stbtf) {
        nft_wm_stbtf = stbtf;
        if (stbtf != null) {
            XA_NET_WM_STATE.sftAtomListPropfrty(tiis, stbtf);
        }
    }

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

    protfdtfd void updbtfDropTbrgft() {
        XToolkit.bwtLodk();
        try {
            if (dropTbrgftCount > 0) {
                long window = gftWindow();
                if (window != 0) {
                    XDropTbrgftRfgistry.gftRfgistry().unrfgistfrDropSitf(window);
                    XDropTbrgftRfgistry.gftRfgistry().rfgistfrDropSitf(window);
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid void sftGrbb(boolfbn grbb) {
        tiis.grbb = grbb;
        if (grbb) {
            prfssTbrgft = tiis;
            grbbInput();
        } flsf {
            ungrbbInput();
        }
    }

    publid boolfbn isGrbbbfd() {
        rfturn grbb && XAwtStbtf.gftGrbbWindow() == tiis;
    }

    publid void ibndlfXCrossingEvfnt(XEvfnt xfv) {
        XCrossingEvfnt xdf = xfv.gft_xdrossing();
        if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            grbbLog.finf("{0}, wifn grbbbfd {1}, dontbins {2}",
                         xdf, isGrbbbfd(), dontbinsGlobbl(xdf.gft_x_root(), xdf.gft_y_root()));
        }
        if (isGrbbbfd()) {
            // Wifn window is grbbbfd, bll fvfnts brf dispbtdifd to
            // it.  Rftbrgft tifm to tif dorrfsponding windows (notidf
            // tibt XBbsfWindow.dispbtdiEvfnt dofs tif oppositf
            // trbnslbtion)
            // Notf tibt wf nffd to rftbrgft XCrossingEvfnts to dontfnt window
            // sindf it gfnfrbtfs MOUSE_ENTERED/MOUSE_EXITED for frbmf bnd diblog.
            // (fix for 6390326)
            XBbsfWindow tbrgft = XToolkit.windowToXWindow(xdf.gft_window());
            if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                grbbLog.finfr("  -  Grbb fvfnt tbrgft {0}", tbrgft);
            }
            if (tbrgft != null && tbrgft != tiis) {
                tbrgft.dispbtdiEvfnt(xfv);
                rfturn;
            }
        }
        supfr.ibndlfXCrossingEvfnt(xfv);
    }

    publid void ibndlfMotionNotify(XEvfnt xfv) {
        XMotionEvfnt xmf = xfv.gft_xmotion();
        if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            grbbLog.finfr("{0}, wifn grbbbfd {1}, dontbins {2}",
                          xmf, isGrbbbfd(), dontbinsGlobbl(xmf.gft_x_root(), xmf.gft_y_root()));
        }
        if (isGrbbbfd()) {
            boolfbn drbgging = fblsf;
            finbl int buttonsNumbfr = XToolkit.gftNumbfrOfButtonsForMbsk();

            for (int i = 0; i < buttonsNumbfr; i++){
                // ifrf is tif bug in WM: fxtrb buttons dofsn't ibvf stbtf!=0 bs tify siould.
                if ((i != 4) && (i != 5)){
                    drbgging = drbgging || ((xmf.gft_stbtf() & XlibUtil.gftButtonMbsk(i + 1)) != 0);
                }
            }
            // Wifn window is grbbbfd, bll fvfnts brf dispbtdifd to
            // it.  Rftbrgft tifm to tif dorrfsponding windows (notidf
            // tibt XBbsfWindow.dispbtdiEvfnt dofs tif oppositf
            // trbnslbtion)
            XBbsfWindow tbrgft = XToolkit.windowToXWindow(xmf.gft_window());
            if (drbgging && prfssTbrgft != tbrgft) {
                // for somf rfbsons if wf grbb input MotionNotify for drbg is rfportfd witi tbrgft
                // to undfrlying window, not to window on wiidi wf ibvf initibtfd drbg
                // so wf nffd to rftbrgft tifm.  Hfrf I usf simplififd logid wiidi rftbrgft bll
                // sudi fvfnts to sourdf of mousf prfss (or tif grbbbfr).  It iflps witi fix for 6390326.
                // So, I do not wbnt to implfmfnt domplidbtfd logid for bfttfr rftbrgfting.
                tbrgft = prfssTbrgft.isVisiblf() ? prfssTbrgft : tiis;
                xmf.sft_window(tbrgft.gftWindow());
                Point lodblCoord = tbrgft.toLodbl(xmf.gft_x_root(), xmf.gft_y_root());
                xmf.sft_x(lodblCoord.x);
                xmf.sft_y(lodblCoord.y);
            }
            if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                grbbLog.finfr("  -  Grbb fvfnt tbrgft {0}", tbrgft);
            }
            if (tbrgft != null) {
                if (tbrgft != gftContfntXWindow() && tbrgft != tiis) {
                    tbrgft.dispbtdiEvfnt(xfv);
                    rfturn;
                }
            }

            // notf tibt wf nffd to pbss drbgging fvfnts to tif grbbbfr (6390326)
            // sff dommfnt bbovf for morf inforbmtion.
            if (!dontbinsGlobbl(xmf.gft_x_root(), xmf.gft_y_root()) && !drbgging) {
                // Outsidf of Jbvb
                rfturn;
            }
        }
        supfr.ibndlfMotionNotify(xfv);
    }

    // wf usf it to rftbrgft mousf drbg bnd mousf rflfbsf during grbb.
    privbtf XBbsfWindow prfssTbrgft = tiis;

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
        if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            grbbLog.finf("{0}, wifn grbbbfd {1}, dontbins {2} ({3}, {4}, {5}x{6})",
                         xbf, isGrbbbfd(), dontbinsGlobbl(xbf.gft_x_root(), xbf.gft_y_root()), gftAbsolutfX(), gftAbsolutfY(), gftWidti(), gftHfigit());
        }
        if (isGrbbbfd()) {
            // Wifn window is grbbbfd, bll fvfnts brf dispbtdifd to
            // it.  Rftbrgft tifm to tif dorrfsponding windows (notidf
            // tibt XBbsfWindow.dispbtdiEvfnt dofs tif oppositf
            // trbnslbtion)
            XBbsfWindow tbrgft = XToolkit.windowToXWindow(xbf.gft_window());
            try {
                if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    grbbLog.finfr("  -  Grbb fvfnt tbrgft {0} (prfss tbrgft {1})", tbrgft, prfssTbrgft);
                }
                if (xbf.gft_typf() == XConstbnts.ButtonPrfss
                    && xbf.gft_button() == XConstbnts.buttons[0])
                {
                    // nffd to kffp it to rftbrgft mousf rflfbsf
                    prfssTbrgft = tbrgft;
                } flsf if (xbf.gft_typf() == XConstbnts.ButtonRflfbsf
                           && xbf.gft_button() == XConstbnts.buttons[0]
                           && prfssTbrgft != tbrgft)
                {
                    // during grbb wf do rfdfivf mousf rflfbsf on difffrfnt domponfnt (not on tif sourdf
                    // of mousf prfss).  So wf nffd to rftbrgft it.
                    // sff 6390326 for morf informbtion.
                    tbrgft = prfssTbrgft.isVisiblf() ? prfssTbrgft : tiis;
                    xbf.sft_window(tbrgft.gftWindow());
                    Point lodblCoord = tbrgft.toLodbl(xbf.gft_x_root(), xbf.gft_y_root());
                    xbf.sft_x(lodblCoord.x);
                    xbf.sft_y(lodblCoord.y);
                    prfssTbrgft = tiis;
                }
                if (tbrgft != null && tbrgft != gftContfntXWindow() && tbrgft != tiis) {
                    tbrgft.dispbtdiEvfnt(xfv);
                    rfturn;
                }
            } finblly {
                if (tbrgft != null) {
                    // Tbrgft is fitifr us or our dontfnt window -
                    // difdk tibt fvfnt is insidf.  'Us' in dbsf of
                    // sifll will mfbn tibt tiis will blso filtfr out prfss on titlf
                    if ((tbrgft == tiis || tbrgft == gftContfntXWindow()) && !dontbinsGlobbl(xbf.gft_x_root(), xbf.gft_y_root())) {
                        // Outsidf tiis toplfvfl iifrbrdiy
                        // Addording to tif spfdifidbtion of UngrbbEvfnt, post it
                        // wifn prfss oddurs outsidf of tif window bnd not on its ownfd windows
                        if (xbf.gft_typf() == XConstbnts.ButtonPrfss) {
                            if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                grbbLog.finf("Gfnfrbting UngrbbEvfnt on {0} bfdbusf not insidf of sifll", tiis);
                            }
                            postEvfntToEvfntQufuf(nfw sun.bwt.UngrbbEvfnt(gftEvfntSourdf()));
                            rfturn;
                        }
                    }
                    // First, gft tif toplfvfl
                    XWindowPffr toplfvfl = tbrgft.gftToplfvflXWindow();
                    if (toplfvfl != null) {
                        Window w = (Window)toplfvfl.tbrgft;
                        wiilf (w != null && toplfvfl != tiis && !(toplfvfl instbndfof XDiblogPffr)) {
                            w = (Window) AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(w);
                            if (w != null) {
                                toplfvfl = (XWindowPffr) AWTAddfssor.gftComponfntAddfssor().gftPffr(w);
                            }
                        }
                        if (w == null || (w != tiis.tbrgft && w instbndfof Diblog)) {
                            // toplfvfl == null - outsidf of
                            // iifrbrdiy, toplfvfl is Diblog - siould
                            // sfnd ungrbb (but siouldn't for Window)
                            if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                grbbLog.finf("Gfnfrbting UngrbbEvfnt on {0} bfdbusf iifrbrdiy fndfd", tiis);
                            }
                            postEvfntToEvfntQufuf(nfw sun.bwt.UngrbbEvfnt(gftEvfntSourdf()));
                        }
                    } flsf {
                        // toplfvfl is null - outsidf of iifrbrdiy
                        if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            grbbLog.finf("Gfnfrbting UngrbbEvfnt on {0} bfdbusf toplfvfl is null", tiis);
                        }
                        postEvfntToEvfntQufuf(nfw sun.bwt.UngrbbEvfnt(gftEvfntSourdf()));
                        rfturn;
                    }
                } flsf {
                    // tbrgft dofsn't mbp to XAWT window - outsidf of iifrbrdiy
                    if (grbbLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        grbbLog.finf("Gfnfrbting UngrbbEvfnt on bfdbusf tbrgft is null {0}", tiis);
                    }
                    postEvfntToEvfntQufuf(nfw sun.bwt.UngrbbEvfnt(gftEvfntSourdf()));
                    rfturn;
                }
            }
        }
        supfr.ibndlfButtonPrfssRflfbsf(xfv);
    }

    publid void print(Grbpiids g) {
        // Wf bssumf wf print tif wiolf frbmf,
        // so wf fxpfdt no dlip wbs sft prfviously
        Sibpf sibpf = AWTAddfssor.gftWindowAddfssor().gftSibpf((Window)tbrgft);
        if (sibpf != null) {
            g.sftClip(sibpf);
        }
        supfr.print(g);
    }

    @Ovfrridf
    publid void sftOpbdity(flobt opbdity) {
        finbl long mbxOpbdity = 0xffffffffl;
        long iOpbdity = (long)(opbdity * mbxOpbdity);
        if (iOpbdity < 0) {
            iOpbdity = 0;
        }
        if (iOpbdity > mbxOpbdity) {
            iOpbdity = mbxOpbdity;
        }

        XAtom nftWmWindowOpbdityAtom = XAtom.gft("_NET_WM_WINDOW_OPACITY");

        if (iOpbdity == mbxOpbdity) {
            nftWmWindowOpbdityAtom.DflftfPropfrty(gftWindow());
        } flsf {
            nftWmWindowOpbdityAtom.sftCbrd32Propfrty(gftWindow(), iOpbdity);
        }
    }

    @Ovfrridf
    publid void sftOpbquf(boolfbn isOpbquf) {
        // no-op
    }

    @Ovfrridf
    publid void updbtfWindow() {
        // no-op
    }
}
