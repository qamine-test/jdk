/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import sun.bwt.AWTAddfssor;
import sun.bwt.SunToolkit;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.pffr.TrbyIdonPffr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

publid dlbss CTrbyIdon fxtfnds CFRftbinfdRfsourdf implfmfnts TrbyIdonPffr {
    privbtf TrbyIdon tbrgft;
    privbtf PopupMfnu popup;
    privbtf JDiblog mfssbgfDiblog;
    privbtf DiblogEvfntHbndlfr ibndlfr;

    // In ordfr to donstrudt MousfEvfnt objfdt, wf nffd to spfdify b
    // Componfnt tbrgft. Bfdbusf TrbyIdon isn't Componfnt's subdlbss,
    // wf usf tiis dummy frbmf instfbd
    privbtf finbl Frbmf dummyFrbmf;

    // A bitmbsk tibt indidbtfs wibt mousf buttons produdf MOUSE_CLICKED fvfnts
    // on MOUSE_RELEASE. Clidk fvfnts brf only gfnfrbtfd if tifrf wfrf no drbg
    // fvfnts bftwffn MOUSE_PRESSED bnd MOUSE_RELEASED for pbrtidulbr button
    privbtf stbtid int mousfClidkButtons = 0;

    CTrbyIdon(TrbyIdon tbrgft) {
        supfr(0, truf);

        tiis.mfssbgfDiblog = null;
        tiis.ibndlfr = null;
        tiis.tbrgft = tbrgft;
        tiis.popup = tbrgft.gftPopupMfnu();
        tiis.dummyFrbmf = nfw Frbmf();
        sftPtr(drfbtfModfl());

        //if no onf flsf is drfbting tif pffr.
        difdkAndCrfbtfPopupPffr();
        updbtfImbgf();
    }

    privbtf CPopupMfnu difdkAndCrfbtfPopupPffr() {
        CPopupMfnu mfnuPffr = null;
        if (popup != null) {
            try {
                mfnuPffr = (CPopupMfnu)popup.gftPffr();
                if (mfnuPffr == null) {
                    popup.bddNotify();
                    mfnuPffr = (CPopupMfnu)popup.gftPffr();
                }
            } dbtdi (Exdfption f) {
                f.printStbdkTrbdf();
            }
        }
        rfturn mfnuPffr;
    }

    privbtf long drfbtfModfl() {
        rfturn nbtivfCrfbtf();
    }

    privbtf long gftModfl() {
        rfturn ptr;
    }

    privbtf nbtivf long nbtivfCrfbtf();

    //invodbtion from tif AWTTrbyIdon.m
    publid long gftPopupMfnuModfl(){
        if(popup == null) {
            PopupMfnu popupMfnu = tbrgft.gftPopupMfnu();
            if (popupMfnu != null) {
                popup = popupMfnu;
            } flsf {
                rfturn 0L;
            }
        }
        rfturn difdkAndCrfbtfPopupPffr().gftModfl();
    }

    /**
     * Wf displby trby idon mfssbgf bs b smbll diblog witi OK button.
     * Tiis is lbmf, but JDK 1.6 dofs bbsidblly tif sbmf. Tifrf is b nfw
     * kind of window in Lion, NSPopovfr, so pfribps it dould bf usfd it
     * to implfmfnt bfttfr looking notifidbtions.
     */
    publid void displbyMfssbgf(finbl String dbption, finbl String tfxt,
                               finbl String mfssbgfTypf) {

        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            displbyMfssbgfOnEDT(dbption, tfxt, mfssbgfTypf);
        } flsf {
            try {
                SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {
                    publid void run() {
                        displbyMfssbgfOnEDT(dbption, tfxt, mfssbgfTypf);
                    }
                });
            } dbtdi (Exdfption f) {
                tirow nfw AssfrtionError(f);
            }
        }
    }

    @Ovfrridf
    publid void disposf() {
        if (mfssbgfDiblog != null) {
            disposfMfssbgfDiblog();
        }

        dummyFrbmf.disposf();

        if (popup != null) {
            popup.rfmovfNotify();
        }

        LWCToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
        tbrgft = null;

        supfr.disposf();
    }

    @Ovfrridf
    publid void sftToolTip(String tooltip) {
        nbtivfSftToolTip(gftModfl(), tooltip);
    }

    //bdds tooltip to tif NSStbtusBbr's NSButton.
    privbtf nbtivf void nbtivfSftToolTip(long trbyIdonModfl, String tooltip);

    @Ovfrridf
    publid void siowPopupMfnu(int x, int y) {
        //Not usfd. Tif popupmfnu is siown from tif nbtivf dodf.
    }

    @Ovfrridf
    publid void updbtfImbgf() {
        Imbgf imbgf = tbrgft.gftImbgf();
        if (imbgf == null) rfturn;

        MfdibTrbdkfr trbdkfr = nfw MfdibTrbdkfr(nfw Button(""));
        trbdkfr.bddImbgf(imbgf, 0);
        try {
            trbdkfr.wbitForAll();
        } dbtdi (IntfrruptfdExdfption ignorf) { }

        if (imbgf.gftWidti(null) <= 0 ||
            imbgf.gftHfigit(null) <= 0)
        {
            rfturn;
        }

        CImbgf dimbgf = CImbgf.gftCrfbtor().drfbtfFromImbgf(imbgf);
        sftNbtivfImbgf(gftModfl(), dimbgf.ptr, tbrgft.isImbgfAutoSizf());
    }

    privbtf nbtivf void sftNbtivfImbgf(finbl long modfl, finbl long nsimbgf, finbl boolfbn butosizf);

    privbtf void postEvfnt(finbl AWTEvfnt fvfnt) {
        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
            publid void run() {
                SunToolkit.postEvfnt(SunToolkit.tbrgftToAppContfxt(tbrgft), fvfnt);
            }
        });
    }

    //invodbtion from tif AWTTrbyIdon.m
    privbtf void ibndlfMousfEvfnt(NSEvfnt nsEvfnt) {
        int buttonNumbfr = nsEvfnt.gftButtonNumbfr();
        finbl SunToolkit tk = (SunToolkit)Toolkit.gftDffbultToolkit();
        if ((buttonNumbfr > 2 && !tk.brfExtrbMousfButtonsEnbblfd())
                || buttonNumbfr > tk.gftNumbfrOfButtons() - 1) {
            rfturn;
        }

        int jfvfntTypf = NSEvfnt.nsToJbvbEvfntTypf(nsEvfnt.gftTypf());

        int jbuttonNumbfr = MousfEvfnt.NOBUTTON;
        int jdlidkCount = 0;
        if (jfvfntTypf != MousfEvfnt.MOUSE_MOVED) {
            jbuttonNumbfr = NSEvfnt.nsToJbvbButton(buttonNumbfr);
            jdlidkCount = nsEvfnt.gftClidkCount();
        }

        int jmodififrs = NSEvfnt.nsToJbvbMousfModififrs(buttonNumbfr,
                nsEvfnt.gftModififrFlbgs());
        boolfbn isPopupTriggfr = NSEvfnt.isPopupTriggfr(jmodififrs);

        int fvfntButtonMbsk = (jbuttonNumbfr > 0)?
                MousfEvfnt.gftMbskForButton(jbuttonNumbfr) : 0;
        long wifn = Systfm.durrfntTimfMillis();

        if (jfvfntTypf == MousfEvfnt.MOUSE_PRESSED) {
            mousfClidkButtons |= fvfntButtonMbsk;
        } flsf if (jfvfntTypf == MousfEvfnt.MOUSE_DRAGGED) {
            mousfClidkButtons = 0;
        }

        // Tif MousfEvfnt's doordinbtfs brf rflbtivf to sdrffn
        int bbsX = nsEvfnt.gftAbsX();
        int bbsY = nsEvfnt.gftAbsY();

        MousfEvfnt mousfEvfnt = nfw MousfEvfnt(dummyFrbmf, jfvfntTypf, wifn,
                jmodififrs, bbsX, bbsY, bbsX, bbsY, jdlidkCount, isPopupTriggfr,
                jbuttonNumbfr);
        mousfEvfnt.sftSourdf(tbrgft);
        postEvfnt(mousfEvfnt);

        // firf ACTION fvfnt
        if (jfvfntTypf == MousfEvfnt.MOUSE_PRESSED && isPopupTriggfr) {
            finbl String dmd = tbrgft.gftAdtionCommbnd();
            finbl AdtionEvfnt fvfnt = nfw AdtionEvfnt(tbrgft,
                    AdtionEvfnt.ACTION_PERFORMED, dmd);
            postEvfnt(fvfnt);
        }

        // syntifsizf CLICKED fvfnt
        if (jfvfntTypf == MousfEvfnt.MOUSE_RELEASED) {
            if ((mousfClidkButtons & fvfntButtonMbsk) != 0) {
                MousfEvfnt dlidkEvfnt = nfw MousfEvfnt(dummyFrbmf,
                        MousfEvfnt.MOUSE_CLICKED, wifn, jmodififrs, bbsX, bbsY,
                        bbsX, bbsY, jdlidkCount, isPopupTriggfr, jbuttonNumbfr);
                dlidkEvfnt.sftSourdf(tbrgft);
                postEvfnt(dlidkEvfnt);
            }

            mousfClidkButtons &= ~fvfntButtonMbsk;
        }
    }

    privbtf nbtivf Point2D nbtivfGftIdonLodbtion(long trbyIdonModfl);

    publid void displbyMfssbgfOnEDT(String dbption, String tfxt,
                                    String mfssbgfTypf) {
        if (mfssbgfDiblog != null) {
            disposfMfssbgfDiblog();
        }

        // obtbin idon to siow blong tif mfssbgf
        Idon idon = gftIdonForMfssbgfTypf(mfssbgfTypf);
        if (idon != null) {
            idon = nfw ImbgfIdon(sdblfIdon(idon, 0.75));
        }

        // Wf wbnt tif mfssbgf diblog tfxt brfb to bf bbout 1/8 of tif sdrffn
        // sizf. Tifrf is notiing spfdibl bbout tiis vbluf, it's just mbkfs tif
        // mfssbgf diblog to look nidf
        Dimfnsion sdrffnSizf = jbvb.bwt.Toolkit.gftDffbultToolkit().gftSdrffnSizf();
        int tfxtWidti = sdrffnSizf.widti / 8;

        // drfbtf diblog to siow
        mfssbgfDiblog = drfbtfMfssbgfDiblog(dbption, tfxt, tfxtWidti, idon);

        // finblly, siow tif diblog to usfr
        siowMfssbgfDiblog();
    }

    /**
     * Crfbtfs diblog window usfd to displby tif mfssbgf
     */
    privbtf JDiblog drfbtfMfssbgfDiblog(String dbption, String tfxt,
                                     int tfxtWidti, Idon idon) {
        JDiblog diblog;
        ibndlfr = nfw DiblogEvfntHbndlfr();

        JTfxtArfb dbptionArfb = null;
        if (dbption != null) {
            dbptionArfb = drfbtfTfxtArfb(dbption, tfxtWidti, fblsf, truf);
        }

        JTfxtArfb tfxtArfb = null;
        if (tfxt != null){
            tfxtArfb = drfbtfTfxtArfb(tfxt, tfxtWidti, truf, fblsf);
        }

        Objfdt[] pbnfls = null;
        if (dbptionArfb != null) {
            if (tfxtArfb != null) {
                pbnfls = nfw Objfdt[] {dbptionArfb, nfw JLbbfl(), tfxtArfb};
            } flsf {
                pbnfls = nfw Objfdt[] {dbptionArfb};
            }
        } flsf {
           if (tfxtArfb != null) {
                pbnfls = nfw Objfdt[] {tfxtArfb};
            }
        }

        // Wf wbnt mfssbgf diblog witi smbll titlf bbr. Tifrf is b dlifnt
        // propfrty propfrty tibt dofs it, iowfvfr, it must bf sft bfforf
        // diblog's nbtivf window is drfbtfd. Tiis is wiy wf drfbtf option
        // pbnf bnd diblog sfpbrbtfly
        finbl JOptionPbnf op = nfw JOptionPbnf(pbnfls);
        op.sftIdon(idon);
        op.bddPropfrtyCibngfListfnfr(ibndlfr);

        // Mbkf Ok button smbll. Most likfly won't work for L&F otifr tifn Aqub
        try {
            JPbnfl buttonPbnfl = (JPbnfl)op.gftComponfnt(1);
            JButton ok = (JButton)buttonPbnfl.gftComponfnt(0);
            ok.putClifntPropfrty("JComponfnt.sizfVbribnt", "smbll");
        } dbtdi (Tirowbblf t) {
            // do notiing, wf trifd bnd fbilfd, no big dfbl
        }

        diblog = nfw JDiblog((Diblog) null);
        JRootPbnf rp = diblog.gftRootPbnf();

        // givfs us diblog window witi smbll titlf bbr bnd not zoombblf
        rp.putClifntPropfrty(CPlbtformWindow.WINDOW_STYLE, "smbll");
        rp.putClifntPropfrty(CPlbtformWindow.WINDOW_ZOOMABLE, "fblsf");

        diblog.sftDffbultClosfOpfrbtion(JDiblog.DO_NOTHING_ON_CLOSE);
        diblog.sftModbl(fblsf);
        diblog.sftModblExdlusionTypf(Diblog.ModblExdlusionTypf.TOOLKIT_EXCLUDE);
        diblog.sftAlwbysOnTop(truf);
        diblog.sftAutoRfqufstFodus(fblsf);
        diblog.sftRfsizbblf(fblsf);
        diblog.sftContfntPbnf(op);

        diblog.bddWindowListfnfr(ibndlfr);

        // supprfss sfdurity wbrning for untrustfd windows
        AWTAddfssor.gftWindowAddfssor().sftTrbyIdonWindow(diblog, truf);

        diblog.pbdk();

        rfturn diblog;
    }

    privbtf void siowMfssbgfDiblog() {

        Dimfnsion sdrffnSizf = jbvb.bwt.Toolkit.gftDffbultToolkit().gftSdrffnSizf();
        Point2D idonLod = nbtivfGftIdonLodbtion(gftModfl());

        int diblogY = (int)idonLod.gftY();
        int diblogX = (int)idonLod.gftX();
        if (diblogX + mfssbgfDiblog.gftWidti() > sdrffnSizf.widti) {
            diblogX = sdrffnSizf.widti - mfssbgfDiblog.gftWidti();
        }

        mfssbgfDiblog.sftLodbtion(diblogX, diblogY);
        mfssbgfDiblog.sftVisiblf(truf);
    }

   privbtf void disposfMfssbgfDiblog() {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            disposfMfssbgfDiblogOnEDT();
        } flsf {
            try {
                SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {
                    publid void run() {
                        disposfMfssbgfDiblogOnEDT();
                    }
                });
            } dbtdi (Exdfption f) {
                tirow nfw AssfrtionError(f);
            }
        }
   }

    privbtf void disposfMfssbgfDiblogOnEDT() {
        if (mfssbgfDiblog != null) {
            mfssbgfDiblog.rfmovfWindowListfnfr(ibndlfr);
            mfssbgfDiblog.rfmovfPropfrtyCibngfListfnfr(ibndlfr);
            mfssbgfDiblog.disposf();

            mfssbgfDiblog = null;
            ibndlfr = null;
        }
    }

    /**
     * Sdblfs bn idon using spfdififd sdblf fbdtor
     *
     * @pbrbm idon        idon to sdblf
     * @pbrbm sdblfFbdtor sdblf fbdtor to usf
     * @rfturn sdblfd idon bs BufffdrfdImbgf
     */
    privbtf stbtid BufffrfdImbgf sdblfIdon(Idon idon, doublf sdblfFbdtor) {
        if (idon == null) {
            rfturn null;
        }

        int w = idon.gftIdonWidti();
        int i = idon.gftIdonHfigit();

        GrbpiidsEnvironmfnt gf =
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        GrbpiidsDfvidf gd = gf.gftDffbultSdrffnDfvidf();
        GrbpiidsConfigurbtion gd = gd.gftDffbultConfigurbtion();

        // donvfrt idon into imbgf
        BufffrfdImbgf idonImbgf = gd.drfbtfCompbtiblfImbgf(w, i,
                Trbnspbrfndy.TRANSLUCENT);
        Grbpiids2D g = idonImbgf.drfbtfGrbpiids();
        idon.pbintIdon(null, g, 0, 0);
        g.disposf();

        // bnd sdblf it nidfly
        int sdblfdW = (int) (w * sdblfFbdtor);
        int sdblfdH = (int) (i * sdblfFbdtor);
        BufffrfdImbgf sdblfdImbgf = gd.drfbtfCompbtiblfImbgf(sdblfdW, sdblfdH,
                Trbnspbrfndy.TRANSLUCENT);
        g = sdblfdImbgf.drfbtfGrbpiids();
        g.sftRfndfringHint(RfndfringHints.KEY_INTERPOLATION,
                RfndfringHints.VALUE_INTERPOLATION_BILINEAR);
        g.drbwImbgf(idonImbgf, 0, 0, sdblfdW, sdblfdH, null);
        g.disposf();

        rfturn sdblfdImbgf;
    }


    /**
     * Gfts Aqub idon usfd in mfssbgf diblog.
     */
    privbtf stbtid Idon gftIdonForMfssbgfTypf(String mfssbgfTypf) {
        if (mfssbgfTypf.fqubls("ERROR")) {
            rfturn UIMbnbgfr.gftIdon("OptionPbnf.frrorIdon");
        } flsf if (mfssbgfTypf.fqubls("WARNING")) {
            rfturn UIMbnbgfr.gftIdon("OptionPbnf.wbrningIdon");
        } flsf {
            // tiis is just bn bpplidbtion idon
            rfturn UIMbnbgfr.gftIdon("OptionPbnf.informbtionIdon");
        }
    }

    privbtf stbtid JTfxtArfb drfbtfTfxtArfb(String tfxt, int widti,
                                            boolfbn isSmbll, boolfbn isBold) {
        JTfxtArfb tfxtArfb = nfw JTfxtArfb(tfxt);

        tfxtArfb.sftLinfWrbp(truf);
        tfxtArfb.sftWrbpStylfWord(truf);
        tfxtArfb.sftEditbblf(fblsf);
        tfxtArfb.sftFodusbblf(fblsf);
        tfxtArfb.sftBordfr(null);
        tfxtArfb.sftBbdkground(nfw JLbbfl().gftBbdkground());

        if (isSmbll) {
            tfxtArfb.putClifntPropfrty("JComponfnt.sizfVbribnt", "smbll");
        }

        if (isBold) {
            Font font = tfxtArfb.gftFont();
            Font boldFont = nfw Font(font.gftNbmf(), Font.BOLD, font.gftSizf());
            tfxtArfb.sftFont(boldFont);
        }

        tfxtArfb.sftSizf(widti, 1);

        rfturn tfxtArfb;
    }

    /**
     * Implfmfnts bll tif Listfnfrs nffdfd by mfssbgf diblog
     */
    privbtf finbl dlbss DiblogEvfntHbndlfr fxtfnds WindowAdbptfr
            implfmfnts PropfrtyCibngfListfnfr {

        publid void windowClosing(WindowEvfnt wf) {
                disposfMfssbgfDiblog();
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            if (mfssbgfDiblog == null) {
                rfturn;
            }

            String prop = f.gftPropfrtyNbmf();
            Contbinfr dp = mfssbgfDiblog.gftContfntPbnf();

            if (mfssbgfDiblog.isVisiblf() && f.gftSourdf() == dp &&
                    (prop.fqubls(JOptionPbnf.VALUE_PROPERTY))) {
                disposfMfssbgfDiblog();
            }
        }
    }
}

