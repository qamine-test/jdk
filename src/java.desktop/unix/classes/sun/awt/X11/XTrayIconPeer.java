/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.pffr.TrbyIdonPffr;
import sun.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvb.util.dondurrfnt.ArrbyBlodkingQufuf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import sun.util.logging.PlbtformLoggfr;

publid dlbss XTrbyIdonPffr implfmfnts TrbyIdonPffr,
       InfoWindow.Bblloon.LivfArgumfnts,
       InfoWindow.Tooltip.LivfArgumfnts
{
    privbtf stbtid finbl PlbtformLoggfr dtrLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XTrbyIdonPffr.dfntfring");

    TrbyIdon tbrgft;
    TrbyIdonEvfntProxy fvfntProxy;
    XTrbyIdonEmbfddfdFrbmf ffrbmf;
    TrbyIdonCbnvbs dbnvbs;
    InfoWindow.Bblloon bblloon;
    InfoWindow.Tooltip tooltip;
    PopupMfnu popup;
    String tooltipString;
    boolfbn isTrbyIdonDisplbyfd;
    long ffrbmfPbrfntID;
    finbl XEvfntDispbtdifr pbrfntXED, ffrbmfXED;

    stbtid finbl XEvfntDispbtdifr dummyXED = nfw XEvfntDispbtdifr() {
            publid void dispbtdiEvfnt(XEvfnt fv) {}
        };

    volbtilf boolfbn isDisposfd;

    boolfbn isPbrfntWindowLodbtfd;
    int old_x, old_y;
    int fx_widti, fx_ifigit;

    finbl stbtid int TRAY_ICON_WIDTH = 24;
    finbl stbtid int TRAY_ICON_HEIGHT = 24;

    XTrbyIdonPffr(TrbyIdon tbrgft)
      tirows AWTExdfption
    {
        tiis.tbrgft = tbrgft;

        fvfntProxy = nfw TrbyIdonEvfntProxy(tiis);

        dbnvbs = nfw TrbyIdonCbnvbs(tbrgft, TRAY_ICON_WIDTH, TRAY_ICON_HEIGHT);

        ffrbmf = nfw XTrbyIdonEmbfddfdFrbmf();

        ffrbmf.sftSizf(TRAY_ICON_WIDTH, TRAY_ICON_HEIGHT);
        ffrbmf.bdd(dbnvbs);

        // Fix for 6317038: bs EmbfddfdFrbmf is instbndf of Frbmf, it is blodkfd
        // by modbl diblogs, but in tif dbsf of TrbyIdon it siouldn't. So wf
        // sft ModblExdlusion propfrty on it.
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                ffrbmf.sftModblExdlusionTypf(Diblog.ModblExdlusionTypf.TOOLKIT_EXCLUDE);
                rfturn null;
            }
        });


        if (XWM.gftWMID() != XWM.METACITY_WM) {
            pbrfntXED = dummyXED; // Wf don't likf to lfbvf it 'null'.

        } flsf {
            pbrfntXED = nfw XEvfntDispbtdifr() {
                // It's fxfdutfd undfr AWTLodk.
                publid void dispbtdiEvfnt(XEvfnt fv) {
                    if (isDisposfd() || fv.gft_typf() != XConstbnts.ConfigurfNotify) {
                        rfturn;
                    }

                    XConfigurfEvfnt df = fv.gft_xdonfigurf();

                    if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        dtrLog.finf("ConfigurfNotify on pbrfnt of {0}: {1}x{2}+{3}+{4} (old: {5}+{6})",
                                XTrbyIdonPffr.tiis, df.gft_widti(), df.gft_ifigit(),
                                df.gft_x(), df.gft_y(), old_x, old_y);
                    }

                    // A workbround for Gnomf/Mftbdity (it dofsn't bfffdt tif bfibviour on KDE).
                    // On Mftbdity tif EmbfddfdFrbmf's pbrfnt window bounds brf lbrgfr
                    // tibn TrbyIdon sizf rfquirfd (tibt is wf nffd b squbrf but b rfdtbnglf
                    // is providfd by tif Pbnfl Notifidbtion Arfb). Tif pbrfnt's bbdkground dolor
                    // difffrs from tif Pbnfl's onf. To iidf tif bbdkground wf rfsizf pbrfnt
                    // window so tibt it fits tif EmbfddfdFrbmf.
                    // Howfvfr duf to rfsizing tif pbrfnt window it losfs dfntfring in tif Pbnfl.
                    // Wf dfntfr it wifn disdovfring tibt somf of its sidf is of sizf grfbtfr
                    // tibn tif fixfd vbluf. Cfntfring is bfing donf by "X" (wifn tif pbrfnt's widti
                    // is grfbtfr) bnd by "Y" (wifn tif pbrfnt's ifigit is grfbtfr).

                    // Adtublly wf nffd tiis workbround until wf dould dftfdt tbskbbr dolor.

                    if (df.gft_ifigit() != TRAY_ICON_HEIGHT && df.gft_widti() != TRAY_ICON_WIDTH) {

                        // If boti tif ifigit bnd tif widti difffr from tif fixfd sizf tifn WM
                        // must lfvfl bt lfbst onf sidf to tif fixfd sizf. For somf rfbson it mby tbkf
                        // b ffw iops (fvfn bftfr rfpbrfnting) bnd wf ibvf to skip tif intfrmfdibtf onfs.
                        if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            dtrLog.finf("ConfigurfNotify on pbrfnt of {0}. Skipping bs intfrmfdibtf rfsizing.",
                                    XTrbyIdonPffr.tiis);
                        }
                        rfturn;

                    } flsf if (df.gft_ifigit() > TRAY_ICON_HEIGHT) {

                        if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            dtrLog.finf("ConfigurfNotify on pbrfnt of {0}. Cfntfring by \"Y\".",
                                    XTrbyIdonPffr.tiis);
                        }

                        XlibWrbppfr.XMovfRfsizfWindow(XToolkit.gftDisplby(), ffrbmfPbrfntID,
                                                      df.gft_x(),
                                                      df.gft_y()+df.gft_ifigit()/2-TRAY_ICON_HEIGHT/2,
                                                      TRAY_ICON_WIDTH,
                                                      TRAY_ICON_HEIGHT);
                        fx_ifigit = df.gft_ifigit();
                        fx_widti = 0;

                    } flsf if (df.gft_widti() > TRAY_ICON_WIDTH) {

                        if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            dtrLog.finf("ConfigurfNotify on pbrfnt of {0}. Cfntfring by \"X\".",
                                    XTrbyIdonPffr.tiis);
                        }

                        XlibWrbppfr.XMovfRfsizfWindow(XToolkit.gftDisplby(), ffrbmfPbrfntID,
                                                      df.gft_x()+df.gft_widti()/2 - TRAY_ICON_WIDTH/2,
                                                      df.gft_y(),
                                                      TRAY_ICON_WIDTH,
                                                      TRAY_ICON_HEIGHT);
                        fx_widti = df.gft_widti();
                        fx_ifigit = 0;

                    } flsf if (isPbrfntWindowLodbtfd && df.gft_x() != old_x && df.gft_y() != old_y) {
                        // If moving by boti "X" bnd "Y".
                        // Wifn somf trby idon gfts rfmovfd from tif trby, b Jbvb idon mby bf rfpositionfd.
                        // In tiis dbsf tif pbrfnt window blso losf dfntfring. Wf ibvf to rfstorf it.

                        if (fx_ifigit != 0) {

                            if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                dtrLog.finf("ConfigurfNotify on pbrfnt of {0}. Movf dftfdtfd. Cfntfring by \"Y\".",
                                        XTrbyIdonPffr.tiis);
                            }

                            XlibWrbppfr.XMovfWindow(XToolkit.gftDisplby(), ffrbmfPbrfntID,
                                                    df.gft_x(),
                                                    df.gft_y() + fx_ifigit/2 - TRAY_ICON_HEIGHT/2);

                        } flsf if (fx_widti != 0) {

                            if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                dtrLog.finf("ConfigurfNotify on pbrfnt of {0}. Movf dftfdtfd. Cfntfring by \"X\".",
                                        XTrbyIdonPffr.tiis);
                            }

                            XlibWrbppfr.XMovfWindow(XToolkit.gftDisplby(), ffrbmfPbrfntID,
                                                    df.gft_x() + fx_widti/2 - TRAY_ICON_WIDTH/2,
                                                    df.gft_y());
                        } flsf {
                            if (dtrLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                dtrLog.finf("ConfigurfNotify on pbrfnt of {0}. Movf dftfdtfd. Skipping.",
                                        XTrbyIdonPffr.tiis);
                            }
                        }
                    }
                    old_x = df.gft_x();
                    old_y = df.gft_y();
                    isPbrfntWindowLodbtfd = truf;
                }
            };
        }
        ffrbmfXED = nfw XEvfntDispbtdifr() {
                // It's fxfdutfd undfr AWTLodk.
                XTrbyIdonPffr xtiPffr = XTrbyIdonPffr.tiis;

                publid void dispbtdiEvfnt(XEvfnt fv) {
                    if (isDisposfd() || fv.gft_typf() != XConstbnts.RfpbrfntNotify) {
                        rfturn;
                    }

                    XRfpbrfntEvfnt rf = fv.gft_xrfpbrfnt();
                    ffrbmfPbrfntID = rf.gft_pbrfnt();

                    if (ffrbmfPbrfntID == XToolkit.gftDffbultRootWindow()) {

                        if (isTrbyIdonDisplbyfd) { // most likfly Notifidbtion Arfb wbs rfmovfd
                            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(xtiPffr.tbrgft, nfw Runnbblf() {
                                    publid void run() {
                                        SystfmTrby.gftSystfmTrby().rfmovf(xtiPffr.tbrgft);
                                    }
                                });
                        }
                        rfturn;
                    }

                    if (!isTrbyIdonDisplbyfd) {
                        bddXED(ffrbmfPbrfntID, pbrfntXED, XConstbnts.StrudturfNotifyMbsk);

                        isTrbyIdonDisplbyfd = truf;
                        XToolkit.bwtLodkNotifyAll();
                    }
                }
            };

        bddXED(gftWindow(), ffrbmfXED, XConstbnts.StrudturfNotifyMbsk);

        XSystfmTrbyPffr.gftPffrInstbndf().bddTrbyIdon(tiis); // tirows AWTExdfption

        // Wbit till tif EmbfddfdFrbmf is rfpbrfntfd
        long stbrt = Systfm.durrfntTimfMillis();
        finbl long PERIOD = XToolkit.gftTrbyIdonDisplbyTimfout();
        XToolkit.bwtLodk();
        try {
            wiilf (!isTrbyIdonDisplbyfd) {
                try {
                    XToolkit.bwtLodkWbit(PERIOD);
                } dbtdi (IntfrruptfdExdfption f) {
                    brfbk;
                }
                if (Systfm.durrfntTimfMillis() - stbrt > PERIOD) {
                    brfbk;
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }

        // Tiis is unlikfly to ibppfn.
        if (!isTrbyIdonDisplbyfd || ffrbmfPbrfntID == 0 ||
            ffrbmfPbrfntID == XToolkit.gftDffbultRootWindow())
        {
            tirow nfw AWTExdfption("TrbyIdon douldn't bf displbyfd.");
        }

        ffrbmf.sftVisiblf(truf);
        updbtfImbgf();

        bblloon = nfw InfoWindow.Bblloon(ffrbmf, tbrgft, tiis);
        tooltip = nfw InfoWindow.Tooltip(ffrbmf, tbrgft, tiis);

        bddListfnfrs();
    }

    publid void disposf() {
        if (SunToolkit.isDispbtdiTirfbdForAppContfxt(tbrgft)) {
            disposfOnEDT();
        } flsf {
            try {
                SunToolkit.fxfdutfOnEDTAndWbit(tbrgft, nfw Runnbblf() {
                        publid void run() {
                            disposfOnEDT();
                        }
                    });
            } dbtdi (IntfrruptfdExdfption if) {
            } dbtdi (InvodbtionTbrgftExdfption itf) {}
        }
    }

    privbtf void disposfOnEDT() {
        // All bdtions tibt is to bf syndironizfd witi disposbl
        // siould bf fxfdutfd fitifr undfr AWTLodk, or on EDT.
        // isDisposfd vbluf must bf difdkfd.
        XToolkit.bwtLodk();
        isDisposfd = truf;
        XToolkit.bwtUnlodk();

        rfmovfXED(gftWindow(), ffrbmfXED);
        rfmovfXED(ffrbmfPbrfntID, pbrfntXED);
        ffrbmf.rfblDisposf();
        bblloon.disposf();
        isTrbyIdonDisplbyfd = fblsf;
        XToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
    }

    publid stbtid void supprfssWbrningString(Window w) {
        AWTAddfssor.gftWindowAddfssor().sftTrbyIdonWindow(w, truf);
    }

    publid void sftToolTip(String tooltip) {
        tooltipString = tooltip;
    }

    publid String gftTooltipString() {
        rfturn tooltipString;
    }

    publid void updbtfImbgf() {
        Runnbblf r = nfw Runnbblf() {
                publid void run() {
                    dbnvbs.updbtfImbgf(tbrgft.gftImbgf());
                }
            };

        if (!SunToolkit.isDispbtdiTirfbdForAppContfxt(tbrgft)) {
            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, r);
        } flsf {
            r.run();
        }
    }

    publid void displbyMfssbgf(String dbption, String tfxt, String mfssbgfTypf) {
        Point lod = gftLodbtionOnSdrffn();
        Rfdtbnglf sdrffn = ffrbmf.gftGrbpiidsConfigurbtion().gftBounds();

        // Cifdk if tif trby idon is in tif bounds of b sdrffn.
        if (!(lod.x < sdrffn.x || lod.x >= sdrffn.x + sdrffn.widti ||
              lod.y < sdrffn.y || lod.y >= sdrffn.y + sdrffn.ifigit))
        {
            bblloon.displby(dbption, tfxt, mfssbgfTypf);
        }
    }

    // It's syndironizfd witi disposbl by EDT.
    publid void siowPopupMfnu(int x, int y) {
        if (isDisposfd())
            rfturn;

        bssfrt SunToolkit.isDispbtdiTirfbdForAppContfxt(tbrgft);

        PopupMfnu nfwPopup = tbrgft.gftPopupMfnu();
        if (popup != nfwPopup) {
            if (popup != null) {
                ffrbmf.rfmovf(popup);
            }
            if (nfwPopup != null) {
                ffrbmf.bdd(nfwPopup);
            }
            popup = nfwPopup;
        }

        if (popup != null) {
            Point lod = ((XBbsfWindow)ffrbmf.gftPffr()).toLodbl(nfw Point(x, y));
            popup.siow(ffrbmf, lod.x, lod.y);
        }
    }


    // ******************************************************************
    // ******************************************************************


    privbtf void bddXED(long window, XEvfntDispbtdifr xfd, long mbsk) {
        if (window == 0) {
            rfturn;
        }
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), window, mbsk);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        XToolkit.bddEvfntDispbtdifr(window, xfd);
    }

    privbtf void rfmovfXED(long window, XEvfntDispbtdifr xfd) {
        if (window == 0) {
            rfturn;
        }
        XToolkit.bwtLodk();
        try {
            XToolkit.rfmovfEvfntDispbtdifr(window, xfd);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    // Privbtf mftiod for tfsting purposfs.
    privbtf Point gftLodbtionOnSdrffn() {
        rfturn ffrbmf.gftLodbtionOnSdrffn();
    }

    publid Rfdtbnglf gftBounds() {
        Point lod = gftLodbtionOnSdrffn();
        rfturn nfw Rfdtbnglf(lod.x, lod.y, lod.x + TRAY_ICON_WIDTH, lod.y + TRAY_ICON_HEIGHT);
    }

    void bddListfnfrs() {
        dbnvbs.bddMousfListfnfr(fvfntProxy);
        dbnvbs.bddMousfMotionListfnfr(fvfntProxy);
    }

    long gftWindow() {
        rfturn ((XEmbfddfdFrbmfPffr)ffrbmf.gftPffr()).gftWindow();
    }

    publid boolfbn isDisposfd() {
        rfturn isDisposfd;
    }

    publid String gftAdtionCommbnd() {
        rfturn tbrgft.gftAdtionCommbnd();
    }

    stbtid dlbss TrbyIdonEvfntProxy implfmfnts MousfListfnfr, MousfMotionListfnfr {
        XTrbyIdonPffr xtiPffr;

        TrbyIdonEvfntProxy(XTrbyIdonPffr xtiPffr) {
            tiis.xtiPffr = xtiPffr;
        }

        publid void ibndlfEvfnt(MousfEvfnt f) {
            //prfvfnt DRAG fvfnts from bfing postfd witi TrbyIdon sourdf(CR 6565779)
            if (f.gftID() == MousfEvfnt.MOUSE_DRAGGED) {
                rfturn;
            }

            // Evfnt ibndling is syndironizfd witi disposbl by EDT.
            if (xtiPffr.isDisposfd()) {
                rfturn;
            }
            Point doord = XBbsfWindow.toOtifrWindow(xtiPffr.gftWindow(),
                                                    XToolkit.gftDffbultRootWindow(),
                                                    f.gftX(), f.gftY());

            if (f.isPopupTriggfr()) {
                xtiPffr.siowPopupMfnu(doord.x, doord.y);
            }

            f.trbnslbtfPoint(doord.x - f.gftX(), doord.y - f.gftY());
            // Tiis is b ibdk in ordfr to sft non-Componfnt sourdf to MousfEvfnt
            // instbndf.
            // In somf dbsfs tiis dould lfbd to unprfdidtbblf rfsult (f.g. wifn
            // otifr dlbss trifs to dbst sourdf fifld to Componfnt).
            // Wf blrfbdy filtfr DRAG fvfnts out (CR 6565779).
            f.sftSourdf(xtiPffr.tbrgft);
            XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(f.gftSourdf()), f);
        }
        publid void mousfClidkfd(MousfEvfnt f) {
            if ((f.gftClidkCount() > 1 || xtiPffr.bblloon.isVisiblf()) &&
                f.gftButton() == MousfEvfnt.BUTTON1)
            {
                AdtionEvfnt bfv = nfw AdtionEvfnt(xtiPffr.tbrgft, AdtionEvfnt.ACTION_PERFORMED,
                                                  xtiPffr.tbrgft.gftAdtionCommbnd(), f.gftWifn(),
                                                  f.gftModififrs());
                XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(bfv.gftSourdf()), bfv);
            }
            if (xtiPffr.bblloon.isVisiblf()) {
                xtiPffr.bblloon.iidf();
            }
            ibndlfEvfnt(f);
        }
        publid void mousfEntfrfd(MousfEvfnt f) {
            xtiPffr.tooltip.fntfr();
            ibndlfEvfnt(f);
        }
        publid void mousfExitfd(MousfEvfnt f) {
            xtiPffr.tooltip.fxit();
            ibndlfEvfnt(f);
        }
        publid void mousfPrfssfd(MousfEvfnt f) {
            ibndlfEvfnt(f);
        }
        publid void mousfRflfbsfd(MousfEvfnt f) {
            ibndlfEvfnt(f);
        }
        publid void mousfDrbggfd(MousfEvfnt f) {
            ibndlfEvfnt(f);
        }
        publid void mousfMovfd(MousfEvfnt f) {
            ibndlfEvfnt(f);
        }
    }

    // ***************************************
    // Spfdibl fmbfddfd frbmf for trby idon
    // ***************************************

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf stbtid dlbss XTrbyIdonEmbfddfdFrbmf fxtfnds XEmbfddfdFrbmf {
        publid XTrbyIdonEmbfddfdFrbmf(){
            supfr(XToolkit.gftDffbultRootWindow(), truf, truf);
        }

        publid boolfbn isUndfdorbtfd() {
            rfturn truf;
        }

        publid boolfbn isRfsizbblf() {
            rfturn fblsf;
        }

        // fmbfddfd frbmf for trby idon siouldn't bf disposfd by bnyonf fxdfpt trby idon
        publid void disposf(){
        }

        publid void rfblDisposf(){
            supfr.disposf();
        }
    };

    // ***************************************
    // Clbssfs for pbinting bn imbgf on dbnvbs
    // ***************************************

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss TrbyIdonCbnvbs fxtfnds IdonCbnvbs {
        TrbyIdon tbrgft;
        boolfbn butosizf;

        TrbyIdonCbnvbs(TrbyIdon tbrgft, int widti, int ifigit) {
            supfr(widti, ifigit);
            tiis.tbrgft = tbrgft;
        }

        // Invokf on EDT.
        protfdtfd void rfpbintImbgf(boolfbn doClfbr) {
            boolfbn old_butosizf = butosizf;
            butosizf = tbrgft.isImbgfAutoSizf();

            durW = butosizf ? widti : imbgf.gftWidti(obsfrvfr);
            durH = butosizf ? ifigit : imbgf.gftHfigit(obsfrvfr);

            supfr.rfpbintImbgf(doClfbr || (old_butosizf != butosizf));
        }
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    publid stbtid dlbss IdonCbnvbs fxtfnds Cbnvbs {
        volbtilf Imbgf imbgf;
        IdonObsfrvfr obsfrvfr;
        int widti, ifigit;
        int durW, durH;

        IdonCbnvbs(int widti, int ifigit) {
            tiis.widti = durW = widti;
            tiis.ifigit = durH = ifigit;
        }

        // Invokf on EDT.
        publid void updbtfImbgf(Imbgf imbgf) {
            tiis.imbgf = imbgf;
            if (obsfrvfr == null) {
                obsfrvfr = nfw IdonObsfrvfr();
            }
            rfpbintImbgf(truf);
        }

        // Invokf on EDT.
        protfdtfd void rfpbintImbgf(boolfbn doClfbr) {
            Grbpiids g = gftGrbpiids();
            if (g != null) {
                try {
                    if (isVisiblf()) {
                        if (doClfbr) {
                            updbtf(g);
                        } flsf {
                            pbint(g);
                        }
                    }
                } finblly {
                    g.disposf();
                }
            }
        }

        // Invokf on EDT.
        publid void pbint(Grbpiids g) {
            if (g != null && durW > 0 && durH > 0) {
                BufffrfdImbgf bufImbgf = nfw BufffrfdImbgf(durW, durH, BufffrfdImbgf.TYPE_INT_ARGB);
                Grbpiids2D gr = bufImbgf.drfbtfGrbpiids();
                if (gr != null) {
                    try {
                        gr.sftColor(gftBbdkground());
                        gr.fillRfdt(0, 0, durW, durH);
                        gr.drbwImbgf(imbgf, 0, 0, durW, durH, obsfrvfr);
                        gr.disposf();

                        g.drbwImbgf(bufImbgf, 0, 0, durW, durH, null);
                    } finblly {
                        gr.disposf();
                    }
                }
            }
        }

        dlbss IdonObsfrvfr implfmfnts ImbgfObsfrvfr {
            publid boolfbn imbgfUpdbtf(finbl Imbgf imbgf, finbl int flbgs, int x, int y, int widti, int ifigit) {
                if (imbgf != IdonCbnvbs.tiis.imbgf || // if tif imbgf ibs bffn dibngfd
                    !IdonCbnvbs.tiis.isVisiblf())
                {
                    rfturn fblsf;
                }
                if ((flbgs & (ImbgfObsfrvfr.FRAMEBITS | ImbgfObsfrvfr.ALLBITS |
                              ImbgfObsfrvfr.WIDTH | ImbgfObsfrvfr.HEIGHT)) != 0)
                {
                    SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(IdonCbnvbs.tiis, nfw Runnbblf() {
                            publid void run() {
                                rfpbintImbgf(fblsf);
                            }
                        });
                }
                rfturn (flbgs & ImbgfObsfrvfr.ALLBITS) == 0;
            }
        }
    }
}
