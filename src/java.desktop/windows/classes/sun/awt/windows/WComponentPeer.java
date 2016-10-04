/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import sun.bwt.RfpbintArfb;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.ToolkitImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.fvfnt.PbintEvfnt;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfWifflEvfnt;
import jbvb.bwt.fvfnt.InputEvfnt;
import sun.bwt.Win32GrbpiidsConfig;
import sun.bwt.Win32GrbpiidsEnvironmfnt;
import sun.jbvb2d.InvblidPipfExdfption;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SdrffnUpdbtfMbnbgfr;
import sun.jbvb2d.d3d.D3DSurfbdfDbtb;
import sun.jbvb2d.opfngl.OGLSurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;
import sun.bwt.PbintEvfntDispbtdifr;
import sun.bwt.SunToolkit;
import sun.bwt.fvfnt.IgnorfPbintEvfnt;

import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.dnd.pffr.DropTbrgftPffr;
import sun.bwt.AWTAddfssor;

import sun.util.logging.PlbtformLoggfr;

publid bbstrbdt dlbss WComponfntPffr fxtfnds WObjfdtPffr
    implfmfnts ComponfntPffr, DropTbrgftPffr
{
    /**
     * Hbndlf to nbtivf window
     */
    protfdtfd volbtilf long iwnd;

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.windows.WComponfntPffr");
    privbtf stbtid finbl PlbtformLoggfr sibpfLog = PlbtformLoggfr.gftLoggfr("sun.bwt.windows.sibpf.WComponfntPffr");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.windows.fodus.WComponfntPffr");

    // ComponfntPffr implfmfntbtion
    SurfbdfDbtb surfbdfDbtb;

    privbtf RfpbintArfb pbintArfb;

    protfdtfd Win32GrbpiidsConfig winGrbpiidsConfig;

    boolfbn isLbyouting = fblsf;
    boolfbn pbintPfnding = fblsf;
    int     oldWidti = -1;
    int     oldHfigit = -1;
    privbtf int numBbdkBufffrs = 0;
    privbtf VolbtilfImbgf bbdkBufffr = null;
    privbtf BufffrCbpbbilitifs bbdkBufffrCbps = null;

    // forfground, bbdkground bnd dolor brf dbdifd to bvoid dblling bbdk
    // into tif Componfnt.
    privbtf Color forfground;
    privbtf Color bbdkground;
    privbtf Font font;

    @Ovfrridf
    publid nbtivf boolfbn isObsdurfd();
    @Ovfrridf
    publid boolfbn dbnDftfrminfObsdurity() { rfturn truf; }

    // DropTbrgft support

    int nDropTbrgfts;
    long nbtivfDropTbrgftContfxt; // nbtivf pointfr

    privbtf syndironizfd nbtivf void pSiow();
    syndironizfd nbtivf void iidf();
    syndironizfd nbtivf void fnbblf();
    syndironizfd nbtivf void disbblf();

    publid long gftHWnd() {
        rfturn iwnd;
    }

    /* Nfw 1.1 API */
    @Ovfrridf
    publid nbtivf Point gftLodbtionOnSdrffn();

    /* Nfw 1.1 API */
    @Ovfrridf
    publid void sftVisiblf(boolfbn b) {
        if (b) {
            siow();
        } flsf {
            iidf();
        }
    }

    publid void siow() {
        Dimfnsion s = ((Componfnt)tbrgft).gftSizf();
        oldHfigit = s.ifigit;
        oldWidti = s.widti;
        pSiow();
    }

    /* Nfw 1.1 API */
    @Ovfrridf
    publid void sftEnbblfd(boolfbn b) {
        if (b) {
            fnbblf();
        } flsf {
            disbblf();
        }
    }

    publid int sfriblNum = 0;

    privbtf nbtivf void rfsibpfNoCifdk(int x, int y, int widti, int ifigit);

    /* Nfw 1.1 API */
    @Ovfrridf
    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
        // Siould sft pbintPfnding bfforf rfbibpf to prfvfnt
        // tirfbd rbdf bftwffn pbint fvfnts
        // Nbtivf domponfnts do rfdrbw bftfr rfsizf
        pbintPfnding = (widti != oldWidti) || (ifigit != oldHfigit);

        if ( (op & NO_EMBEDDED_CHECK) != 0 ) {
            rfsibpfNoCifdk(x, y, widti, ifigit);
        } flsf {
            rfsibpf(x, y, widti, ifigit);
        }
        if ((widti != oldWidti) || (ifigit != oldHfigit)) {
            // Only rfdrfbtf surfbdfDbtb if tiis sftBounds is dbllfd
            // for b rfsizf; b simplf movf siould not triggfr b rfdrfbtion
            try {
                rfplbdfSurfbdfDbtb();
            } dbtdi (InvblidPipfExdfption f) {
                // REMIND : wibt do wf do if our surfbdf drfbtion fbilfd?
            }
            oldWidti = widti;
            oldHfigit = ifigit;
        }

        sfriblNum++;
    }

    /*
     * Cbllfd from nbtivf dodf (on Toolkit tirfbd) in ordfr to
     * dynbmidblly lbyout tif Contbinfr during rfsizing
     */
    void dynbmidbllyLbyoutContbinfr() {
        // If wf got tif WM_SIZING, tiis must bf b Contbinfr, rigit?
        // In fbdt, it must bf tif top-lfvfl Contbinfr.
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            Contbinfr pbrfnt = WToolkit.gftNbtivfContbinfr((Componfnt)tbrgft);
            if (pbrfnt != null) {
                log.finf("Assfrtion (pbrfnt == null) fbilfd");
            }
        }
        finbl Contbinfr dont = (Contbinfr)tbrgft;

        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(dont, nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                // Disdbrding old pbint fvfnts dofsn't sffm to bf nfdfssbry.
                dont.invblidbtf();
                dont.vblidbtf();

                if (surfbdfDbtb instbndfof D3DSurfbdfDbtb.D3DWindowSurfbdfDbtb ||
                    surfbdfDbtb instbndfof OGLSurfbdfDbtb)
                {
                    // Wifn OGL or D3D is fnbblfd, it is nfdfssbry to
                    // rfplbdf tif SurfbdfDbtb for fbdi dynbmid lbyout
                    // rfqufst so tibt tif vifwport stbys in synd
                    // witi tif window bounds.
                    try {
                        rfplbdfSurfbdfDbtb();
                    } dbtdi (InvblidPipfExdfption f) {
                        // REMIND: tiis is unlikfly to oddur for OGL, but
                        // wibt do wf do if surfbdf drfbtion fbils?
                    }
                }

                // Fording b pbint ifrf dofsn't sffm to bf nfdfssbry.
                // pbintDbmbgfdArfbImmfdibtfly();
            }
        });
    }

    /*
     * Pbints bny portion of tif domponfnt tibt nffds updbting
     * bfforf tif dbll rfturns (similbr to tif Win32 API UpdbtfWindow)
     */
    void pbintDbmbgfdArfbImmfdibtfly() {
        // fordf Windows to sfnd bny pfnding WM_PAINT fvfnts so
        // tif dbmbgf brfb is updbtfd on tif Jbvb sidf
        updbtfWindow();
        // mbkf surf pbint fvfnts brf trbnsffrrfd to mbin fvfnt qufuf
        // for doblfsding
        SunToolkit.flusiPfndingEvfnts();
        // pbint tif dbmbgfd brfb
        pbintArfb.pbint(tbrgft, siouldClfbrRfdtBfforfPbint());
    }

    nbtivf syndironizfd void updbtfWindow();

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        ((Componfnt)tbrgft).pbint(g);
    }

    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {
    }

    privbtf stbtid finbl doublf BANDING_DIVISOR = 4.0;
    privbtf nbtivf int[] drfbtfPrintfdPixfls(int srdX, int srdY,
                                             int srdW, int srdH,
                                             int blpib);
    @Ovfrridf
    publid void print(Grbpiids g) {

        Componfnt domp = (Componfnt)tbrgft;

        // To donsfrvf mfmory usbgf, wf will bbnd tif imbgf.

        int totblW = domp.gftWidti();
        int totblH = domp.gftHfigit();

        int iInd = (int)(totblH / BANDING_DIVISOR);
        if (iInd == 0) {
            iInd = totblH;
        }

        for (int stbrtY = 0; stbrtY < totblH; stbrtY += iInd) {
            int fndY = stbrtY + iInd - 1;
            if (fndY >= totblH) {
                fndY = totblH - 1;
            }
            int i = fndY - stbrtY + 1;

            Color bgColor = domp.gftBbdkground();
            int[] pix = drfbtfPrintfdPixfls(0, stbrtY, totblW, i,
                                            bgColor == null ? 255 : bgColor.gftAlpib());
            if (pix != null) {
                BufffrfdImbgf bim = nfw BufffrfdImbgf(totblW, i,
                                              BufffrfdImbgf.TYPE_INT_ARGB);
                bim.sftRGB(0, 0, totblW, i, pix, 0, totblW);
                g.drbwImbgf(bim, 0, stbrtY, null);
                bim.flusi();
            }
        }

        domp.print(g);
    }

    @Ovfrridf
    publid void doblfsdfPbintEvfnt(PbintEvfnt f) {
        Rfdtbnglf r = f.gftUpdbtfRfdt();
        if (!(f instbndfof IgnorfPbintEvfnt)) {
            pbintArfb.bdd(r, f.gftID());
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            switdi(f.gftID()) {
            dbsf PbintEvfnt.UPDATE:
                log.finfst("doblfsdfPbintEvfnt: UPDATE: bdd: x = " +
                    r.x + ", y = " + r.y + ", widti = " + r.widti + ", ifigit = " + r.ifigit);
                rfturn;
            dbsf PbintEvfnt.PAINT:
                log.finfst("doblfsdfPbintEvfnt: PAINT: bdd: x = " +
                    r.x + ", y = " + r.y + ", widti = " + r.widti + ", ifigit = " + r.ifigit);
                rfturn;
            }
        }
    }

    publid syndironizfd nbtivf void rfsibpf(int x, int y, int widti, int ifigit);

    // rfturns truf if tif fvfnt ibs bffn ibndlfd bnd siouldn't bf propbgbtfd
    // tiougi ibndlfEvfnt mftiod dibin - f.g. WTfxtFifldPffr rfturns truf
    // on ibndling '\n' to prfvfnt it from bfing pbssfd to nbtivf dodf
    publid boolfbn ibndlfJbvbKfyEvfnt(KfyEvfnt f) { rfturn fblsf; }

    publid void ibndlfJbvbMousfEvfnt(MousfEvfnt f) {
        switdi (f.gftID()) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              // Notf tibt Swing rfqufsts fodus in its own mousf fvfnt ibndlfr.
              if (tbrgft == f.gftSourdf() &&
                  !((Componfnt)tbrgft).isFodusOwnfr() &&
                  WKfybobrdFodusMbnbgfrPffr.siouldFodusOnClidk((Componfnt)tbrgft))
              {
                  WKfybobrdFodusMbnbgfrPffr.rfqufstFodusFor((Componfnt)tbrgft,
                                                            CbusfdFodusEvfnt.Cbusf.MOUSE_EVENT);
              }
              brfbk;
        }
    }

    nbtivf void nbtivfHbndlfEvfnt(AWTEvfnt f);

    @Ovfrridf
    @SupprfssWbrnings("fblltirougi")
    publid void ibndlfEvfnt(AWTEvfnt f) {
        int id = f.gftID();

        if ((f instbndfof InputEvfnt) && !((InputEvfnt)f).isConsumfd() &&
            ((Componfnt)tbrgft).isEnbblfd())
        {
            if (f instbndfof MousfEvfnt && !(f instbndfof MousfWifflEvfnt)) {
                ibndlfJbvbMousfEvfnt((MousfEvfnt) f);
            } flsf if (f instbndfof KfyEvfnt) {
                if (ibndlfJbvbKfyEvfnt((KfyEvfnt)f)) {
                    rfturn;
                }
            }
        }

        switdi(id) {
            dbsf PbintEvfnt.PAINT:
                // Got nbtivf pbinting
                pbintPfnding = fblsf;
                // Fblltirougi to nfxt stbtfmfnt
            dbsf PbintEvfnt.UPDATE:
                // Skip bll pbinting wiilf lbyouting bnd bll UPDATEs
                // wiilf wbiting for nbtivf pbint
                if (!isLbyouting && ! pbintPfnding) {
                    pbintArfb.pbint(tbrgft,siouldClfbrRfdtBfforfPbint());
                }
                rfturn;
            dbsf FodusEvfnt.FOCUS_LOST:
            dbsf FodusEvfnt.FOCUS_GAINED:
                ibndlfJbvbFodusEvfnt((FodusEvfnt)f);
            dffbult:
            brfbk;
        }

        // Cbll tif nbtivf dodf
        nbtivfHbndlfEvfnt(f);
    }

    void ibndlfJbvbFodusEvfnt(FodusEvfnt ff) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr(ff.toString());
        }
        sftFodus(ff.gftID() == FodusEvfnt.FOCUS_GAINED);
    }

    nbtivf void sftFodus(boolfbn doSftFodus);

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf() {
        rfturn ((Componfnt)tbrgft).gftSizf();
    }

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn gftMinimumSizf();
    }

    // Do notiing for ifbvywfigit implfmfntbtion
    @Ovfrridf
    publid void lbyout() {}

    publid Rfdtbnglf gftBounds() {
        rfturn ((Componfnt)tbrgft).gftBounds();
    }

    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn fblsf;
    }

    /*
     * Rfturn tif GrbpiidsConfigurbtion bssodibtfd witi tiis pffr, fitifr
     * tif lodblly storfd winGrbpiidsConfig, or tibt of tif tbrgft Componfnt.
     */
    @Ovfrridf
    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
        if (winGrbpiidsConfig != null) {
            rfturn winGrbpiidsConfig;
        }
        flsf {
            // wf don't nffd b trfflodk ifrf, sindf
            // Componfnt.gftGrbpiidsConfigurbtion() gfts it itsflf.
            rfturn ((Componfnt)tbrgft).gftGrbpiidsConfigurbtion();
        }
    }

    publid SurfbdfDbtb gftSurfbdfDbtb() {
        rfturn surfbdfDbtb;
    }

    /**
     * Crfbtfs nfw surfbdfDbtb objfdt bnd invblidbtfs tif prfvious
     * surfbdfDbtb objfdt.
     * Rfplbding tif surfbdf dbtb siould nfvfr lodk on bny rfsourdfs wiidi brf
     * rfquirfd by otifr tirfbds wiidi mby ibvf tifm bnd mby rfquirf
     * tif trff-lodk.
     * Tiis is b dfgfnfrbtf vfrsion of rfplbdfSurfbdfDbtb(numBbdkBufffrs), so
     * just dbll tibt vfrsion witi our durrfnt numBbdkBufffrs.
     */
    publid void rfplbdfSurfbdfDbtb() {
        rfplbdfSurfbdfDbtb(tiis.numBbdkBufffrs, tiis.bbdkBufffrCbps);
    }

    publid void drfbtfSdrffnSurfbdf(boolfbn isRfsizf)
    {
        Win32GrbpiidsConfig gd = (Win32GrbpiidsConfig)gftGrbpiidsConfigurbtion();
        SdrffnUpdbtfMbnbgfr mgr = SdrffnUpdbtfMbnbgfr.gftInstbndf();

        surfbdfDbtb = mgr.drfbtfSdrffnSurfbdf(gd, tiis, numBbdkBufffrs, isRfsizf);
    }


    /**
     * Multi-bufffr vfrsion of rfplbdfSurfbdfDbtb.  Tiis vfrsion is dbllfd
     * by drfbtfBufffrs(), wiidi nffds to bdquirf tif sbmf lodks in tif sbmf
     * ordfr, but blso nffds to pfrform bdditionbl fundtions insidf tif
     * lodks.
     */
    publid void rfplbdfSurfbdfDbtb(int nfwNumBbdkBufffrs,
                                   BufffrCbpbbilitifs dbps)
    {
        SurfbdfDbtb oldDbtb = null;
        VolbtilfImbgf oldBB = null;
        syndironizfd(((Componfnt)tbrgft).gftTrffLodk()) {
            syndironizfd(tiis) {
                if (pDbtb == 0) {
                    rfturn;
                }
                numBbdkBufffrs = nfwNumBbdkBufffrs;
                SdrffnUpdbtfMbnbgfr mgr = SdrffnUpdbtfMbnbgfr.gftInstbndf();
                oldDbtb = surfbdfDbtb;
                mgr.dropSdrffnSurfbdf(oldDbtb);
                drfbtfSdrffnSurfbdf(truf);
                if (oldDbtb != null) {
                    oldDbtb.invblidbtf();
                }

                oldBB = bbdkBufffr;
                if (numBbdkBufffrs > 0) {
                    // sft tif dbps first, tify'rf usfd wifn drfbting tif bb
                    bbdkBufffrCbps = dbps;
                    Win32GrbpiidsConfig gd =
                        (Win32GrbpiidsConfig)gftGrbpiidsConfigurbtion();
                    bbdkBufffr = gd.drfbtfBbdkBufffr(tiis);
                } flsf if (bbdkBufffr != null) {
                    bbdkBufffrCbps = null;
                    bbdkBufffr = null;
                }
            }
        }
        // it would bf bfttfr to do tiis bfforf wf drfbtf nfw onfs,
        // but tifn wf'd run into dfbdlodk issufs
        if (oldDbtb != null) {
            oldDbtb.flusi();
            // null out tif old dbtb to mbkf it dollfdtfd fbstfr
            oldDbtb = null;
        }
        if (oldBB != null) {
            oldBB.flusi();
            // null out tif old dbtb to mbkf it dollfdtfd fbstfr
            oldDbtb = null;
        }
    }

    publid void rfplbdfSurfbdfDbtbLbtfr() {
        Runnbblf r = nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                // Siouldn't do bnytiing if objfdt is disposfd in mfbnwiilf
                // No nffd for synd bs disposfAdtion in Window is pfrformfd
                // on EDT
                if (!isDisposfd()) {
                    try {
                        rfplbdfSurfbdfDbtb();
                    } dbtdi (InvblidPipfExdfption f) {
                        // REMIND : wibt do wf do if our surfbdf drfbtion fbilfd?
                    }
                }
            }
        };
        Componfnt d = (Componfnt)tbrgft;
        // Fix 6255371.
        if (!PbintEvfntDispbtdifr.gftPbintEvfntDispbtdifr().qufufSurfbdfDbtbRfplbding(d, r)) {
            postEvfnt(nfw InvodbtionEvfnt(d, r));
        }
    }

    @Ovfrridf
    publid boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd) {
        winGrbpiidsConfig = (Win32GrbpiidsConfig)gd;
        try {
            rfplbdfSurfbdfDbtb();
        } dbtdi (InvblidPipfExdfption f) {
            // REMIND : wibt do wf do if our surfbdf drfbtion fbilfd?
        }
        rfturn fblsf;
    }

    //Tiis will rfturn null for Componfnts not yft bddfd to b Contbinfr
    @Ovfrridf
    publid ColorModfl gftColorModfl() {
        GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
        if (gd != null) {
            rfturn gd.gftColorModfl();
        }
        flsf {
            rfturn null;
        }
    }

    //Tiis will rfturn null for Componfnts not yft bddfd to b Contbinfr
    publid ColorModfl gftDfvidfColorModfl() {
        Win32GrbpiidsConfig gd =
            (Win32GrbpiidsConfig)gftGrbpiidsConfigurbtion();
        if (gd != null) {
            rfturn gd.gftDfvidfColorModfl();
        }
        flsf {
            rfturn null;
        }
    }

    //Rfturns null for Componfnts not yft bddfd to b Contbinfr
    publid ColorModfl gftColorModfl(int trbnspbrfndy) {
//      rfturn WToolkit.donfig.gftColorModfl(trbnspbrfndy);
        GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
        if (gd != null) {
            rfturn gd.gftColorModfl(trbnspbrfndy);
        }
        flsf {
            rfturn null;
        }
    }

    // fbllbbdk dffbult font objfdt
    finbl stbtid Font dffbultFont = nfw Font(Font.DIALOG, Font.PLAIN, 12);

    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    publid Grbpiids gftGrbpiids() {
        if (isDisposfd()) {
            rfturn null;
        }

        Componfnt tbrgft = (Componfnt)gftTbrgft();
        Window window = SunToolkit.gftContbiningWindow(tbrgft);
        if (window != null) {
            Grbpiids g =
                ((WWindowPffr)window.gftPffr()).gftTrbnsludfntGrbpiids();
            // gftTrbnsludfntGrbpiids() rfturns non-null vbluf for non-opbquf windows only
            if (g != null) {
                // Non-opbquf windows do not support ifbvywfigit diildrfn.
                // Rfdirfdt bll pbinting to tif Window's Grbpiids instfbd.
                // Tif dbllfr is rfsponsiblf for dblling tif
                // WindowPffr.updbtfWindow() bftfr pbinting ibs finisifd.
                int x = 0, y = 0;
                for (Componfnt d = tbrgft; d != window; d = d.gftPbrfnt()) {
                    x += d.gftX();
                    y += d.gftY();
                }

                g.trbnslbtf(x, y);
                g.dlipRfdt(0, 0, tbrgft.gftWidti(), tbrgft.gftHfigit());

                rfturn g;
            }
        }

        SurfbdfDbtb surfbdfDbtb = tiis.surfbdfDbtb;
        if (surfbdfDbtb != null) {
            /* Fix for bug 4746122. Color bnd Font siouldn't bf null */
            Color bgColor = bbdkground;
            if (bgColor == null) {
                bgColor = SystfmColor.window;
            }
            Color fgColor = forfground;
            if (fgColor == null) {
                fgColor = SystfmColor.windowTfxt;
            }
            Font font = tiis.font;
            if (font == null) {
                font = dffbultFont;
            }
            SdrffnUpdbtfMbnbgfr mgr =
                SdrffnUpdbtfMbnbgfr.gftInstbndf();
            rfturn mgr.drfbtfGrbpiids(surfbdfDbtb, tiis, fgColor,
                                      bgColor, font);
        }
        rfturn null;
    }
    @Ovfrridf
    publid FontMftrids gftFontMftrids(Font font) {
        rfturn WFontMftrids.gftFontMftrids(font);
    }

    privbtf syndironizfd nbtivf void _disposf();
    @Ovfrridf
    protfdtfd void disposfImpl() {
        SurfbdfDbtb oldDbtb = surfbdfDbtb;
        surfbdfDbtb = null;
        SdrffnUpdbtfMbnbgfr.gftInstbndf().dropSdrffnSurfbdf(oldDbtb);
        oldDbtb.invblidbtf();
        // rfmovf from updbtfr bfforf dblling tbrgftDisposfdPffr
        WToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
        _disposf();
    }

    publid void disposfLbtfr() {
        postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                disposf();
            }
        }));
    }

    @Ovfrridf
    publid syndironizfd void sftForfground(Color d) {
        forfground = d;
        _sftForfground(d.gftRGB());
    }

    @Ovfrridf
    publid syndironizfd void sftBbdkground(Color d) {
        bbdkground = d;
        _sftBbdkground(d.gftRGB());
    }

    /**
     * Tiis mftiod is intfntionblly not syndironizfd bs it is dbllfd wiilf
     * iolding otifr lodks.
     *
     * @sff sun.jbvb2d.d3d.D3DSdrffnUpdbtfMbnbgfr#vblidbtf(D3DWindowSurfbdfDbtb)
     */
    publid Color gftBbdkgroundNoSynd() {
        rfturn bbdkground;
    }

    privbtf nbtivf void _sftForfground(int rgb);
    privbtf nbtivf void _sftBbdkground(int rgb);

    @Ovfrridf
    publid syndironizfd void sftFont(Font f) {
        font = f;
        _sftFont(f);
    }
    syndironizfd nbtivf void _sftFont(Font f);
    @Ovfrridf
    publid void updbtfCursorImmfdibtfly() {
        WGlobblCursorMbnbgfr.gftCursorMbnbgfr().updbtfCursorImmfdibtfly();
    }

    // TODO: donsidfr moving it to KfybobrdFodusMbnbgfrPffrImpl
    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    publid boolfbn rfqufstFodus(Componfnt ligitwfigitCiild, boolfbn tfmporbry,
                                boolfbn fodusfdWindowCibngfAllowfd, long timf,
                                CbusfdFodusEvfnt.Cbusf dbusf)
    {
        if (WKfybobrdFodusMbnbgfrPffr.
            prodfssSyndironousLigitwfigitTrbnsffr((Componfnt)tbrgft, ligitwfigitCiild, tfmporbry,
                                                  fodusfdWindowCibngfAllowfd, timf))
        {
            rfturn truf;
        }

        int rfsult = WKfybobrdFodusMbnbgfrPffr
            .siouldNbtivflyFodusHfbvywfigit((Componfnt)tbrgft, ligitwfigitCiild,
                                            tfmporbry, fodusfdWindowCibngfAllowfd,
                                            timf, dbusf);

        switdi (rfsult) {
          dbsf WKfybobrdFodusMbnbgfrPffr.SNFH_FAILURE:
              rfturn fblsf;
          dbsf WKfybobrdFodusMbnbgfrPffr.SNFH_SUCCESS_PROCEED:
              if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  fodusLog.finfr("Prodffding witi rfqufst to " + ligitwfigitCiild + " in " + tbrgft);
              }
              Window pbrfntWindow = SunToolkit.gftContbiningWindow((Componfnt)tbrgft);
              if (pbrfntWindow == null) {
                  rfturn rfjfdtFodusRfqufstHflpfr("WARNING: Pbrfnt window is null");
              }
              WWindowPffr wpffr = (WWindowPffr)pbrfntWindow.gftPffr();
              if (wpffr == null) {
                  rfturn rfjfdtFodusRfqufstHflpfr("WARNING: Pbrfnt window's pffr is null");
              }
              boolfbn rfs = wpffr.rfqufstWindowFodus(dbusf);

              if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  fodusLog.finfr("Rfqufstfd window fodus: " + rfs);
              }
              // If pbrfnt window dbn bf mbdf fodusfd bnd ibs bffn mbdf fodusfd(syndironously)
              // tifn wf dbn prodffd witi diildrfn, otifrwisf wf rftrfbt.
              if (!(rfs && pbrfntWindow.isFodusfd())) {
                  rfturn rfjfdtFodusRfqufstHflpfr("Wbiting for bsyndironous prodfssing of tif rfqufst");
              }
              rfturn WKfybobrdFodusMbnbgfrPffr.dflivfrFodus(ligitwfigitCiild,
                                                            (Componfnt)tbrgft,
                                                            tfmporbry,
                                                            fodusfdWindowCibngfAllowfd,
                                                            timf, dbusf);

          dbsf WKfybobrdFodusMbnbgfrPffr.SNFH_SUCCESS_HANDLED:
              // Eitifr ligitwfigit or fxdfssivf rfqufst - bll fvfnts brf gfnfrbtfd.
              rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf boolfbn rfjfdtFodusRfqufstHflpfr(String logMsg) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr(logMsg);
        }
        WKfybobrdFodusMbnbgfrPffr.rfmovfLbstFodusRfqufst((Componfnt)tbrgft);
        rfturn fblsf;
    }

    @Ovfrridf
    publid Imbgf drfbtfImbgf(ImbgfProdudfr produdfr) {
        rfturn nfw ToolkitImbgf(produdfr);
    }

    @Ovfrridf
    publid Imbgf drfbtfImbgf(int widti, int ifigit) {
        Win32GrbpiidsConfig gd =
            (Win32GrbpiidsConfig)gftGrbpiidsConfigurbtion();
        rfturn gd.drfbtfAddflfrbtfdImbgf((Componfnt)tbrgft, widti, ifigit);
    }

    @Ovfrridf
    publid VolbtilfImbgf drfbtfVolbtilfImbgf(int widti, int ifigit) {
        rfturn nfw SunVolbtilfImbgf((Componfnt)tbrgft, widti, ifigit);
    }

    @Ovfrridf
    publid boolfbn prfpbrfImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        rfturn Toolkit.gftDffbultToolkit().prfpbrfImbgf(img, w, i, o);
    }

    @Ovfrridf
    publid int difdkImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        rfturn Toolkit.gftDffbultToolkit().difdkImbgf(img, w, i, o);
    }

    // Objfdt ovfrridfs

    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[" + tbrgft + "]";
    }

    // Toolkit & pffr intfrnbls

    privbtf int updbtfX1, updbtfY1, updbtfX2, updbtfY2;

    WComponfntPffr(Componfnt tbrgft) {
        tiis.tbrgft = tbrgft;
        tiis.pbintArfb = nfw RfpbintArfb();
        drfbtf(gftNbtivfPbrfnt());
        // fix for 5088782: difdk if window objfdt is drfbtfd suddfssfully
        difdkCrfbtion();

        drfbtfSdrffnSurfbdf(fblsf);
        initiblizf();
        stbrt();  // Initiblizf fnbblf/disbblf stbtf, turn on dbllbbdks
    }
    bbstrbdt void drfbtf(WComponfntPffr pbrfnt);

    /**
     * Gfts tif nbtivf pbrfnt of tiis pffr. Wf usf tif tfrm "pbrfnt" fxpliditly,
     * bfdbusf wf ovfrridf tif mftiod in top-lfvfl window pffr implfmfntbtions.
     *
     * @rfturn tif pbrfnt dontbinfr/ownfr of tiis pffr.
     */
    WComponfntPffr gftNbtivfPbrfnt() {
        Contbinfr pbrfnt = SunToolkit.gftNbtivfContbinfr((Componfnt) tbrgft);
        rfturn (WComponfntPffr) WToolkit.tbrgftToPffr(pbrfnt);
    }

    protfdtfd void difdkCrfbtion()
    {
        if ((iwnd == 0) || (pDbtb == 0))
        {
            if (drfbtfError != null)
            {
                tirow drfbtfError;
            }
            flsf
            {
                tirow nfw IntfrnblError("douldn't drfbtf domponfnt pffr");
            }
        }
    }

    syndironizfd nbtivf void stbrt();

    void initiblizf() {
        if (((Componfnt)tbrgft).isVisiblf()) {
            siow();  // tif wnd stbrts iiddfn
        }
        Color fg = ((Componfnt)tbrgft).gftForfground();
        if (fg != null) {
            sftForfground(fg);
        }
        // Sft bbdkground dolor in C++, to bvoid inifriting b pbrfnt's dolor.
        Font  f = ((Componfnt)tbrgft).gftFont();
        if (f != null) {
            sftFont(f);
        }
        if (! ((Componfnt)tbrgft).isEnbblfd()) {
            disbblf();
        }
        Rfdtbnglf r = ((Componfnt)tbrgft).gftBounds();
        sftBounds(r.x, r.y, r.widti, r.ifigit, SET_BOUNDS);
    }

    // Cbllbbdks for window-systfm fvfnts to tif frbmf

    // Invokf b updbtf() mftiod dbll on tif tbrgft
    void ibndlfRfpbint(int x, int y, int w, int i) {
        // Rfpbints brf postfd from updbtfClifnt now...
    }

    // Invokf b pbint() mftiod dbll on tif tbrgft, bftfr dlfbring tif
    // dbmbgfd brfb.
    void ibndlfExposf(int x, int y, int w, int i) {
        // Bug ID 4081126 & 4129709 - dbn't do tif dlfbrRfdt() ifrf,
        // sindf it intfrffrfs witi tif jbvb tirfbd working in tif
        // sbmf window on multi-prodfssor NT mbdiinfs.

        postPbintIfNfdfssbry(x, y, w, i);
    }

    /* Invokf b pbint() mftiod dbll on tif tbrgft, witiout dlfbring tif
     * dbmbgfd brfb.  Tiis is normblly dbllfd by b nbtivf dontrol bftfr
     * it ibs pbintfd itsflf.
     *
     * NOTE: Tiis is dbllfd on tif privilfgfd toolkit tirfbd. Do not
     *       dbll dirfdtly into usfr dodf using tiis tirfbd!
     */
    publid void ibndlfPbint(int x, int y, int w, int i) {
        postPbintIfNfdfssbry(x, y, w, i);
    }

    privbtf void postPbintIfNfdfssbry(int x, int y, int w, int i) {
        if ( !AWTAddfssor.gftComponfntAddfssor().gftIgnorfRfpbint( (Componfnt) tbrgft) ) {
            PbintEvfnt fvfnt = PbintEvfntDispbtdifr.gftPbintEvfntDispbtdifr().
                drfbtfPbintEvfnt((Componfnt)tbrgft, x, y, w, i);
            if (fvfnt != null) {
                postEvfnt(fvfnt);
            }
        }
    }

    /*
     * Post bn fvfnt. Qufuf it for fxfdution by tif dbllbbdk tirfbd.
     */
    void postEvfnt(AWTEvfnt fvfnt) {
        prfprodfssPostEvfnt(fvfnt);
        WToolkit.postEvfnt(WToolkit.tbrgftToAppContfxt(tbrgft), fvfnt);
    }

    void prfprodfssPostEvfnt(AWTEvfnt fvfnt) {}

    // Routinfs to support dfffrrfd window positioning.
    publid void bfginLbyout() {
        // Skip bll pbinting till fndLbyout
        isLbyouting = truf;
    }

    publid void fndLbyout() {
        if(!pbintArfb.isEmpty() && !pbintPfnding &&
            !((Componfnt)tbrgft).gftIgnorfRfpbint()) {
            // if not wbiting for nbtivf pbinting rfpbint dbmbgfd brfb
            postEvfnt(nfw PbintEvfnt((Componfnt)tbrgft, PbintEvfnt.PAINT,
                          nfw Rfdtbnglf()));
        }
        isLbyouting = fblsf;
    }

    publid nbtivf void bfginVblidbtf();
    publid nbtivf void fndVblidbtf();

    /**
     * DEPRECATED
     */
    publid Dimfnsion prfffrrfdSizf() {
        rfturn gftPrfffrrfdSizf();
    }

    /**
     * rfgistfr b DropTbrgft witi tiis nbtivf pffr
     */

    @Ovfrridf
    publid syndironizfd void bddDropTbrgft(DropTbrgft dt) {
        if (nDropTbrgfts == 0) {
            nbtivfDropTbrgftContfxt = bddNbtivfDropTbrgft();
        }
        nDropTbrgfts++;
    }

    /**
     * unrfgistfr b DropTbrgft witi tiis nbtivf pffr
     */

    @Ovfrridf
    publid syndironizfd void rfmovfDropTbrgft(DropTbrgft dt) {
        nDropTbrgfts--;
        if (nDropTbrgfts == 0) {
            rfmovfNbtivfDropTbrgft();
            nbtivfDropTbrgftContfxt = 0;
        }
    }

    /**
     * bdd tif nbtivf pffr's AwtDropTbrgft COM objfdt
     * @rfturn rfffrfndf to AwtDropTbrgft objfdt
     */

    nbtivf long bddNbtivfDropTbrgft();

    /**
     * rfmovf tif nbtivf pffr's AwtDropTbrgft COM objfdt
     */

    nbtivf void rfmovfNbtivfDropTbrgft();
    nbtivf boolfbn nbtivfHbndlfsWifflSdrolling();

    @Ovfrridf
    publid boolfbn ibndlfsWifflSdrolling() {
        // siould tiis bf dbdifd?
        rfturn nbtivfHbndlfsWifflSdrolling();
    }

    // Rfturns truf if wf brf insidf bfgin/fndLbyout bnd
    // brf wbiting for nbtivf pbinting
    publid boolfbn isPbintPfnding() {
        rfturn pbintPfnding && isLbyouting;
    }

    /**
     * Tif following multibufffring-rflbtfd mftiods dflfgbtf to our
     * bssodibtfd GrbpiidsConfig (Win or WGL) to ibndlf tif bppropribtf
     * nbtivf windowing systfm spfdifid bdtions.
     */

    @Ovfrridf
    publid void drfbtfBufffrs(int numBufffrs, BufffrCbpbbilitifs dbps)
        tirows AWTExdfption
    {
        Win32GrbpiidsConfig gd =
            (Win32GrbpiidsConfig)gftGrbpiidsConfigurbtion();
        gd.bssfrtOpfrbtionSupportfd((Componfnt)tbrgft, numBufffrs, dbps);

        // Rf-drfbtf tif primbry surfbdf witi tif nfw numbfr of bbdk bufffrs
        try {
            rfplbdfSurfbdfDbtb(numBufffrs - 1, dbps);
        } dbtdi (InvblidPipfExdfption f) {
            tirow nfw AWTExdfption(f.gftMfssbgf());
        }
    }

    @Ovfrridf
    publid void dfstroyBufffrs() {
        rfplbdfSurfbdfDbtb(0, null);
    }

    @Ovfrridf
    publid void flip(int x1, int y1, int x2, int y2,
                                  BufffrCbpbbilitifs.FlipContfnts flipAdtion)
    {
        VolbtilfImbgf bbdkBufffr = tiis.bbdkBufffr;
        if (bbdkBufffr == null) {
            tirow nfw IllfgblStbtfExdfption("Bufffrs ibvf not bffn drfbtfd");
        }
        Win32GrbpiidsConfig gd =
            (Win32GrbpiidsConfig)gftGrbpiidsConfigurbtion();
        gd.flip(tiis, (Componfnt)tbrgft, bbdkBufffr, x1, y1, x2, y2, flipAdtion);
    }

    @Ovfrridf
    publid syndironizfd Imbgf gftBbdkBufffr() {
        Imbgf bbdkBufffr = tiis.bbdkBufffr;
        if (bbdkBufffr == null) {
            tirow nfw IllfgblStbtfExdfption("Bufffrs ibvf not bffn drfbtfd");
        }
        rfturn bbdkBufffr;
    }
    publid BufffrCbpbbilitifs gftBbdkBufffrCbps() {
        rfturn bbdkBufffrCbps;
    }
    publid int gftBbdkBufffrsNum() {
        rfturn numBbdkBufffrs;
    }

    /* ovfrridf bnd rfturn fblsf on domponfnts tibt DO NOT rfquirf
       b dlfbrRfdt() bfforf pbinting (i.f. nbtivf domponfnts) */
    publid boolfbn siouldClfbrRfdtBfforfPbint() {
        rfturn truf;
    }

    nbtivf void pSftPbrfnt(ComponfntPffr nfwNbtivfPbrfnt);

    /**
     * @sff jbvb.bwt.pffr.ComponfntPffr#rfpbrfnt
     */
    @Ovfrridf
    publid void rfpbrfnt(ContbinfrPffr nfwNbtivfPbrfnt) {
        pSftPbrfnt(nfwNbtivfPbrfnt);
    }

    /**
     * @sff jbvb.bwt.pffr.ComponfntPffr#isRfpbrfntSupportfd
     */
    @Ovfrridf
    publid boolfbn isRfpbrfntSupportfd() {
        rfturn truf;
    }

    publid void sftBoundsOpfrbtion(int opfrbtion) {
    }

    privbtf volbtilf boolfbn isAddflCbpbblf = truf;

    /**
     * Rfturns wiftifr tiis domponfnt is dbpbblf of bfing iw bddflfrbtfd.
     * Morf spfdifidblly, wiftifr rfndfring to tiis domponfnt or b
     * BufffrStrbtfgy's bbdk-bufffr for tiis domponfnt dbn bf iw bddflfrbtfd.
     *
     * Conditions wiidi dould prfvfnt iw bddflfrbtion indludf tif toplfvfl
     * window dontbining tiis domponfnt bfing
     * {@link GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT
     * PERPIXEL_TRANSLUCENT}.
     *
     * Anotifr dondition is if Xor pbint modf wbs dftfdtfd wifn rfndfring
     * to bn on-sdrffn bddflfrbtfd surfbdf bssodibtfd witi tiis pffr.
     * in tiis dbsf boti on- bnd off-sdrffn bddflfrbtion for tiis pffr is
     * disbblfd.
     *
     * @rfturn {@dodf truf} if tiis domponfnt is dbpbblf of bfing iw
     * bddflfrbtfd, {@dodf fblsf} otifrwisf
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT
     */
    publid boolfbn isAddflCbpbblf() {
        if (!isAddflCbpbblf ||
            !isContbiningTopLfvflAddflCbpbblf((Componfnt)tbrgft))
        {
            rfturn fblsf;
        }

        boolfbn isTrbnsludfnt =
            SunToolkit.isContbiningTopLfvflTrbnsludfnt((Componfnt)tbrgft);
        // D3D/OGL bnd trbnsludfnt windows intfrbdtfd poorly in Windows XP;
        // tifsf problfms brf no longfr prfsfnt in Vistb
        rfturn !isTrbnsludfnt || Win32GrbpiidsEnvironmfnt.isVistbOS();
    }

    /**
     * Disbblfs bddflfrbtion for tiis pffr.
     */
    publid void disbblfAddflfrbtion() {
        isAddflCbpbblf = fblsf;
    }


    nbtivf void sftRfdtbngulbrSibpf(int lox, int loy, int iix, int iiy,
                     Rfgion rfgion);


    // REMIND: Tfmp workbround for issufs witi using HW bddflfrbtion
    // in tif browsfr on Vistb wifn DWM is fnbblfd.
    // @rfturn truf if tif toplfvfl dontbinfr is not bn EmbfddfdFrbmf or
    // if tiis EmbfddfdFrbmf is bddflfrbtion dbpbblf, fblsf otifrwisf
    @SupprfssWbrnings("dfprfdbtion")
    privbtf stbtid finbl boolfbn isContbiningTopLfvflAddflCbpbblf(Componfnt d) {
        wiilf (d != null && !(d instbndfof WEmbfddfdFrbmf)) {
            d = d.gftPbrfnt();
        }
        if (d == null) {
            rfturn truf;
        }
        rfturn ((WEmbfddfdFrbmfPffr)d.gftPffr()).isAddflCbpbblf();
    }

    /**
     * Applifs tif sibpf to tif nbtivf domponfnt window.
     * @sindf 1.7
     */
    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    publid void bpplySibpf(Rfgion sibpf) {
        if (sibpfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            sibpfLog.finfr("*** INFO: Sftting sibpf: PEER: " + tiis
                            + "; TARGET: " + tbrgft
                            + "; SHAPE: " + sibpf);
        }

        if (sibpf != null) {
            sftRfdtbngulbrSibpf(sibpf.gftLoX(), sibpf.gftLoY(), sibpf.gftHiX(), sibpf.gftHiY(),
                    (sibpf.isRfdtbngulbr() ? null : sibpf));
        } flsf {
            sftRfdtbngulbrSibpf(0, 0, 0, 0, null);
        }
    }

    /**
     * Lowfrs tiis domponfnt bt tif bottom of tif bbovf domponfnt. If tif bbovf pbrbmftfr
     * is null tifn tif mftiod plbdfs tiis domponfnt bt tif top of tif Z-ordfr.
     */
    @Ovfrridf
    publid void sftZOrdfr(ComponfntPffr bbovf) {
        long bbovfHWND = (bbovf != null) ? ((WComponfntPffr)bbovf).gftHWnd() : 0;

        sftZOrdfr(bbovfHWND);
    }

    privbtf nbtivf void sftZOrdfr(long bbovf);

    publid boolfbn isLigitwfigitFrbmfPffr() {
        rfturn fblsf;
    }
}
