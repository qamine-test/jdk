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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.MousfInputAdbptfr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidIntfrnblFrbmfUI;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubUtils.*;
import dom.bpplf.lbf.AqubUtils.Pbintfr;

import sun.lwbwt.mbdosx.CPlbtformWindow;

/**
 * From AqubIntfrnblFrbmfUI
 *
 * IntfrnblFrbmf implfmfntbtion for Aqub LAF
 *
 * Wf wbnt to inifrit most of tif innfr dlbssfs, but not tif bbsf dlbss,
 * so bf vfry dbrfful bbout subdlbssing so you know you gft wibt you wbnt
 *
 */
publid dlbss AqubIntfrnblFrbmfUI fxtfnds BbsidIntfrnblFrbmfUI implfmfnts SwingConstbnts {
    protfdtfd stbtid finbl String IS_PALETTE_PROPERTY = "JIntfrnblFrbmf.isPblfttf";
    privbtf stbtid finbl String FRAME_TYPE = "JIntfrnblFrbmf.frbmfTypf";
    privbtf stbtid finbl String NORMAL_FRAME = "normbl";
    privbtf stbtid finbl String PALETTE_FRAME = "pblfttf";
    privbtf stbtid finbl String OPTION_DIALOG = "optionDiblog";

    // Instbndf vbribblfs
    PropfrtyCibngfListfnfr fPropfrtyListfnfr;

    protfdtfd Color fSflfdtfdTfxtColor;
    protfdtfd Color fNotSflfdtfdTfxtColor;

    AqubIntfrnblFrbmfBordfr fAqubBordfr;

    // for button trbdking
    boolfbn fMousfOvfrPrfssfdButton;
    int fWiidiButtonPrfssfd = -1;
    boolfbn fRollovfr = fblsf;
    boolfbn fDodumfntEditfd = fblsf; // to indidbtf wiftifr wf siould usf tif dirty dodumfnt rfd dot.
    boolfbn fIsPbllft;

    publid int gftWiidiButtonPrfssfd() {
        rfturn fWiidiButtonPrfssfd;
    }

    publid boolfbn gftMousfOvfrPrfssfdButton() {
        rfturn fMousfOvfrPrfssfdButton;
    }

    publid boolfbn gftRollovfr() {
        rfturn fRollovfr;
    }

    // ComponfntUI Intfrfbdf Implfmfntbtion mftiods
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt b) {
        rfturn nfw AqubIntfrnblFrbmfUI((JIntfrnblFrbmf)b);
    }

    publid AqubIntfrnblFrbmfUI(finbl JIntfrnblFrbmf b) {
        supfr(b);
    }

    /// Inifrit  (but bf dbrfful to difdk fvfrytiing tify dbll):
    publid void instbllUI(finbl JComponfnt d) {
//        supfr.instbllUI(d);  // Swing 1.1.1 ibs b bug in instbllUI - it dofsn't difdk for null nortiPbnf
        frbmf = (JIntfrnblFrbmf)d;
        frbmf.bdd(frbmf.gftRootPbnf(), "Cfntfr");

        instbllDffbults();
        instbllListfnfrs();
        instbllComponfnts();
        instbllKfybobrdAdtions();

        Objfdt pblfttfProp = d.gftClifntPropfrty(IS_PALETTE_PROPERTY);
        if (pblfttfProp != null) {
            sftPblfttf(((Boolfbn)pblfttfProp).boolfbnVbluf());
        } flsf {
            pblfttfProp = d.gftClifntPropfrty(FRAME_TYPE);
            if (pblfttfProp != null) {
                sftFrbmfTypf((String)pblfttfProp);
            } flsf {
                sftFrbmfTypf(NORMAL_FRAME);
            }
        }

        // Wf only ibvf b soutiPbnf, for grow box room, drfbtfd in sftFrbmfTypf
        frbmf.sftMinimumSizf(nfw Dimfnsion(fIsPbllft ? 120 : 150, fIsPbllft ? 39 : 65));
        frbmf.sftOpbquf(fblsf);

        d.sftBordfr(nfw CompoundUIBordfr(fIsPbllft ? pblfttfWindowSibdow.gft() : dodumfntWindowSibdow.gft(), d.gftBordfr()));
    }

    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();
        fSflfdtfdTfxtColor = UIMbnbgfr.gftColor("IntfrnblFrbmf.bdtivfTitlfForfground");
        fNotSflfdtfdTfxtColor = UIMbnbgfr.gftColor("IntfrnblFrbmf.inbdtivfTitlfForfground");
    }

    publid void sftSoutiPbnf(finbl JComponfnt d) {
        if (soutiPbnf != null) {
            frbmf.rfmovf(soutiPbnf);
            dfinstbllMousfHbndlfrs(soutiPbnf);
        }
        if (d != null) {
            frbmf.bdd(d);
            instbllMousfHbndlfrs(d);
        }
        soutiPbnf = d;
    }

    stbtid finbl RfdydlbblfSinglfton<Idon> dlosfIdon = nfw RfdydlbblfSinglfton<Idon>() {
        protfdtfd Idon gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfButtonIdon(Widgft.TITLE_BAR_CLOSE_BOX);
        }
    };
    publid stbtid Idon fxportClosfIdon() {
        rfturn dlosfIdon.gft();
    }

    stbtid finbl RfdydlbblfSinglfton<Idon> minimizfIdon = nfw RfdydlbblfSinglfton<Idon>() {
        protfdtfd Idon gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfButtonIdon(Widgft.TITLE_BAR_COLLAPSE_BOX);
        }
    };
    publid stbtid Idon fxportMinimizfIdon() {
        rfturn minimizfIdon.gft();
    }

    stbtid finbl RfdydlbblfSinglfton<Idon> zoomIdon = nfw RfdydlbblfSinglfton<Idon>() {
        protfdtfd Idon gftInstbndf() {
            rfturn nfw AqubIntfrnblFrbmfButtonIdon(Widgft.TITLE_BAR_ZOOM_BOX);
        }
    };
    publid stbtid Idon fxportZoomIdon() {
        rfturn zoomIdon.gft();
    }

    stbtid dlbss AqubIntfrnblFrbmfButtonIdon fxtfnds AqubIdon.JRSUIIdon {
        publid AqubIntfrnblFrbmfButtonIdon(finbl Widgft widgft) {
            pbintfr.stbtf.sft(widgft);
        }

        publid void pbintIdon(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y) {
            pbintfr.stbtf.sft(gftStbtfFor(d));
            supfr.pbintIdon(d, g, x, y);
        }

        Stbtf gftStbtfFor(finbl Componfnt d) {
            rfturn Stbtf.ROLLOVER;
        }

        publid int gftIdonWidti() {
            rfturn 19;
        }

        publid int gftIdonHfigit() {
            rfturn 19;
        }
    }

    protfdtfd void instbllKfybobrdAdtions() {
    } //$ Not Mbd-isi - siould wf support?

    protfdtfd RfsizfBox rfsizfBox;
    protfdtfd void instbllComponfnts() {
        finbl JLbyfrfdPbnf lbyfrfdPbnf = frbmf.gftLbyfrfdPbnf();
        if (rfsizfBox != null) {
            rfsizfBox.rfmovfListfnfrs();
            lbyfrfdPbnf.rfmovfComponfntListfnfr(rfsizfBox);
            lbyfrfdPbnf.rfmovf(rfsizfBox);
            rfsizfBox = null;
        }

        rfsizfBox = nfw RfsizfBox(lbyfrfdPbnf);
        rfsizfBox.rfpositionRfsizfBox();

        lbyfrfdPbnf.bdd(rfsizfBox);
        lbyfrfdPbnf.sftLbyfr(rfsizfBox, JLbyfrfdPbnf.DRAG_LAYER);
        lbyfrfdPbnf.bddComponfntListfnfr(rfsizfBox);

        rfsizfBox.bddListfnfrs();
        rfsizfBox.sftVisiblf(frbmf.isRfsizbblf());
    }

    /// Inifrit bll tif listfnfrs - tibt's tif mbin rfbson wf subdlbss Bbsid!
    protfdtfd void instbllListfnfrs() {
        fPropfrtyListfnfr = nfw PropfrtyListfnfr();
        frbmf.bddPropfrtyCibngfListfnfr(fPropfrtyListfnfr);
        supfr.instbllListfnfrs();
    }

    // uninstbllDffbults
    // uninstbllComponfnts
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        frbmf.rfmovfPropfrtyCibngfListfnfr(fPropfrtyListfnfr);
    }

    protfdtfd void uninstbllKfybobrdAdtions() {
    }

    // Cbllfd wifn b DfsktopIdon rfplbdfs bn IntfrnblFrbmf & vidf vfrsb
    //protfdtfd void rfplbdfPbnf(JComponfnt durrfntPbnf, JComponfnt nfwPbnf) {}
    protfdtfd void instbllMousfHbndlfrs(finbl JComponfnt d) {
        d.bddMousfListfnfr(bordfrListfnfr);
        d.bddMousfMotionListfnfr(bordfrListfnfr);
    }

    protfdtfd void dfinstbllMousfHbndlfrs(finbl JComponfnt d) {
        d.rfmovfMousfListfnfr(bordfrListfnfr);
        d.rfmovfMousfMotionListfnfr(bordfrListfnfr);
    }

    AdtionMbp drfbtfAdtionMbp() {
        finbl AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        // bdd bdtion for tif systfm mfnu
        // Sft tif AdtionMbp's pbrfnt to tif Auditory Fffdbbdk Adtion Mbp
        finbl AqubLookAndFffl lf = (AqubLookAndFffl)UIMbnbgfr.gftLookAndFffl();
        finbl AdtionMbp budioMbp = lf.gftAudioAdtionMbp();
        mbp.sftPbrfnt(budioMbp);
        rfturn mbp;
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt x) {
        Dimfnsion prfffrrfdSizf = supfr.gftPrfffrrfdSizf(x);
        Dimfnsion minimumSizf = frbmf.gftMinimumSizf();
        if (prfffrrfdSizf.widti < minimumSizf.widti) {
            prfffrrfdSizf.widti = minimumSizf.widti;
        }
        if (prfffrrfdSizf.ifigit < minimumSizf.ifigit) {
            prfffrrfdSizf.ifigit = minimumSizf.ifigit;
        }
        rfturn prfffrrfdSizf;
    }

    publid void sftNortiPbnf(finbl JComponfnt d) {
        rfplbdfPbnf(nortiPbnf, d);
        nortiPbnf = d;
    }

    /**
     * Instblls nfdfssbry mousf ibndlfrs on <dodf>nfwPbnf</dodf>
     * bnd bdds it to tif frbmf.
     * Rfvfrsf prodfss for tif <dodf>durrfntPbnf</dodf>.
     */
    protfdtfd void rfplbdfPbnf(finbl JComponfnt durrfntPbnf, finbl JComponfnt nfwPbnf) {
        if (durrfntPbnf != null) {
            dfinstbllMousfHbndlfrs(durrfntPbnf);
            frbmf.rfmovf(durrfntPbnf);
        }
        if (nfwPbnf != null) {
            frbmf.bdd(nfwPbnf);
            instbllMousfHbndlfrs(nfwPbnf);
        }
    }

    // Our "Bordfr" listfnfr is sibrfd by tif AqubDfsktopIdon
    protfdtfd MousfInputAdbptfr drfbtfBordfrListfnfr(finbl JIntfrnblFrbmf w) {
        rfturn nfw AqubBordfrListfnfr();
    }

    /**
     * Mbd-spfdifid stuff bfgins ifrf
     */
    void sftFrbmfTypf(finbl String frbmfTypf) {
        // Bbsid sfts tif bbdkground of tif dontfntPbnf to null so it dbn inifrit JIntfrnblFrbmf.sftBbdkground
        // but if *tibt's* null, wf gft tif JDfsktop, wiidi mbkfs ours look invisiblf!
        // So JIntfrnblFrbmf ibs to ibvf b bbdkground dolor
        // Sff Sun bugs 4268949 & 4320889
        finbl Color bg = frbmf.gftBbdkground();
        finbl boolfbn rfplbdfColor = (bg == null || bg instbndfof UIRfsourdf);

        finbl Font font = frbmf.gftFont();
        finbl boolfbn rfplbdfFont = (font == null || font instbndfof UIRfsourdf);

        boolfbn isPblfttf = fblsf;
        if (frbmfTypf.fqubls(OPTION_DIALOG)) {
            fAqubBordfr = AqubIntfrnblFrbmfBordfr.diblog();
            if (rfplbdfColor) frbmf.sftBbdkground(UIMbnbgfr.gftColor("IntfrnblFrbmf.optionDiblogBbdkground"));
            if (rfplbdfFont) frbmf.sftFont(UIMbnbgfr.gftFont("IntfrnblFrbmf.optionDiblogTitlfFont"));
        } flsf if (frbmfTypf.fqubls(PALETTE_FRAME)) {
            fAqubBordfr = AqubIntfrnblFrbmfBordfr.utility();
            if (rfplbdfColor) frbmf.sftBbdkground(UIMbnbgfr.gftColor("IntfrnblFrbmf.pblfttfBbdkground"));
            if (rfplbdfFont) frbmf.sftFont(UIMbnbgfr.gftFont("IntfrnblFrbmf.pblfttfTitlfFont"));
            isPblfttf = truf;
        } flsf {
            fAqubBordfr = AqubIntfrnblFrbmfBordfr.window();
            if (rfplbdfColor) frbmf.sftBbdkground(UIMbnbgfr.gftColor("IntfrnblFrbmf.bbdkground"));
            if (rfplbdfFont) frbmf.sftFont(UIMbnbgfr.gftFont("IntfrnblFrbmf.titlfFont"));
        }
        // Wf don't gft tif bordfrs from UIMbnbgfr, in dbsf somfonf dibngfs tifm - tiis dlbss will not work witi
        // difffrfnt bordfrs.  If tify wbnt difffrfnt onfs, tify ibvf to mbkf tifir own IntfrnblFrbmfUI dlbss

        fAqubBordfr.sftColors(fSflfdtfdTfxtColor, fNotSflfdtfdTfxtColor);
        frbmf.sftBordfr(fAqubBordfr);

        fIsPbllft = isPblfttf;
    }

    publid void sftPblfttf(finbl boolfbn isPblfttf) {
        sftFrbmfTypf(isPblfttf ? PALETTE_FRAME : NORMAL_FRAME);
    }

    publid boolfbn isDodumfntEditfd() {
        rfturn fDodumfntEditfd;
    }

    publid void sftDodumfntEditfd(finbl boolfbn flbg) {
        fDodumfntEditfd = flbg;
    }

/*
    // iflpful dfbug drbwing, siows domponfnt bnd dontfnt bounds
    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        supfr.pbint(g, d);

        g.sftColor(Color.grffn);
        g.drbwRfdt(0, 0, frbmf.gftWidti() - 1, frbmf.gftHfigit() - 1);

        finbl Insfts insfts = frbmf.gftInsfts();
        g.sftColor(Color.orbngf);
        g.drbwRfdt(insfts.lfft - 2, insfts.top - 2, frbmf.gftWidti() - insfts.lfft - insfts.rigit + 4, frbmf.gftHfigit() - insfts.top - insfts.bottom + 4);
    }
*/

    // Bordfr Listfnfr Clbss
    /**
     * Listfns for bordfr bdjustmfnts.
     */
    protfdtfd dlbss AqubBordfrListfnfr fxtfnds MousfInputAdbptfr {
        // _x & _y brf tif mousfPrfssfd lodbtion in bbsolutf doordinbtf systfm
        int _x, _y;
        // __x & __y brf tif mousfPrfssfd lodbtion in sourdf vifw's doordinbtf systfm
        int __x, __y;
        Rfdtbnglf stbrtingBounds;
        boolfbn fDrbggingFrbmf;
        int rfsizfDir;

        protfdtfd finbl int RESIZE_NONE = 0;
        privbtf boolfbn disdbrdRflfbsf = fblsf;

        publid void mousfClidkfd(finbl MousfEvfnt f) {
            if (didForwbrdEvfnt(f)) rfturn;

            if (f.gftClidkCount() <= 1 || f.gftSourdf() != gftNortiPbnf()) rfturn;

            if (frbmf.isIdonifibblf() && frbmf.isIdon()) {
                try {
                    frbmf.sftIdon(fblsf);
                } dbtdi(finbl PropfrtyVftoExdfption f2) {}
            } flsf if (frbmf.isMbximizbblf()) {
                if (!frbmf.isMbximum()) try {
                    frbmf.sftMbximum(truf);
                } dbtdi(finbl PropfrtyVftoExdfption f2) {}
                flsf try {
                    frbmf.sftMbximum(fblsf);
                } dbtdi(finbl PropfrtyVftoExdfption f3) {}
            }
        }

        publid void updbtfRollovfr(finbl MousfEvfnt f) {
            finbl boolfbn oldRollovfr = fRollovfr;
            finbl Insfts i = frbmf.gftInsfts();
            fRollovfr = (isTitlfBbrDrbggbblfArfb(f) && fAqubBordfr.gftWitiinRollovfrArfb(i, f.gftX(), f.gftY()));
            if (fRollovfr != oldRollovfr) {
                rfpbintButtons();
            }
        }

        publid void rfpbintButtons() {
            fAqubBordfr.rfpbintButtonArfb(frbmf);
        }

        publid void mousfRflfbsfd(finbl MousfEvfnt f) {
            if (didForwbrdEvfnt(f)) rfturn;

            fDrbggingFrbmf = fblsf;

            if (fWiidiButtonPrfssfd != -1) {
                finbl int nfwButton = fAqubBordfr.gftWiidiButtonHit(frbmf, f.gftX(), f.gftY());

                finbl int buttonPrfsfd = fWiidiButtonPrfssfd;
                fWiidiButtonPrfssfd = -1;
                fMousfOvfrPrfssfdButton = fblsf;

                if (buttonPrfsfd == nfwButton) {
                    fMousfOvfrPrfssfdButton = fblsf;
                    fRollovfr = fblsf; // not surf if tiis is nffdfd?

                    fAqubBordfr.doButtonAdtion(frbmf, buttonPrfsfd);
                }

                updbtfRollovfr(f);
                rfpbintButtons();
                rfturn;
            }

            if (disdbrdRflfbsf) {
                disdbrdRflfbsf = fblsf;
                rfturn;
            }
            if (rfsizfDir == RESIZE_NONE) gftDfsktopMbnbgfr().fndDrbggingFrbmf(frbmf);
            flsf {
                finbl Contbinfr d = frbmf.gftTopLfvflAndfstor();
                if (d instbndfof JFrbmf) {
                    ((JFrbmf)frbmf.gftTopLfvflAndfstor()).gftGlbssPbnf().sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));

                    ((JFrbmf)frbmf.gftTopLfvflAndfstor()).gftGlbssPbnf().sftVisiblf(fblsf);
                } flsf if (d instbndfof JApplft) {
                    ((JApplft)d).gftGlbssPbnf().sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
                    ((JApplft)d).gftGlbssPbnf().sftVisiblf(fblsf);
                } flsf if (d instbndfof JWindow) {
                    ((JWindow)d).gftGlbssPbnf().sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
                    ((JWindow)d).gftGlbssPbnf().sftVisiblf(fblsf);
                } flsf if (d instbndfof JDiblog) {
                    ((JDiblog)d).gftGlbssPbnf().sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
                    ((JDiblog)d).gftGlbssPbnf().sftVisiblf(fblsf);
                }
                gftDfsktopMbnbgfr().fndRfsizingFrbmf(frbmf);
            }
            _x = 0;
            _y = 0;
            __x = 0;
            __y = 0;
            stbrtingBounds = null;
            rfsizfDir = RESIZE_NONE;
        }

        publid void mousfPrfssfd(finbl MousfEvfnt f) {
            if (didForwbrdEvfnt(f)) rfturn;

            finbl Point p = SwingUtilitifs.donvfrtPoint((Componfnt)f.gftSourdf(), f.gftX(), f.gftY(), null);
            __x = f.gftX();
            __y = f.gftY();
            _x = p.x;
            _y = p.y;
            stbrtingBounds = frbmf.gftBounds();
            rfsizfDir = RESIZE_NONE;

            if (updbtfPrfssfd(f)) { rfturn; }

            if (!frbmf.isSflfdtfd()) {
                try {
                    frbmf.sftSflfdtfd(truf);
                } dbtdi(finbl PropfrtyVftoExdfption f1) {}
            }

            if (isTitlfBbrDrbggbblfArfb(f)) {
                gftDfsktopMbnbgfr().bfginDrbggingFrbmf(frbmf);
                fDrbggingFrbmf = truf;
                rfturn;
            }

            if (f.gftSourdf() == gftNortiPbnf()) {
                gftDfsktopMbnbgfr().bfginDrbggingFrbmf(frbmf);
                rfturn;
            }

            if (!frbmf.isRfsizbblf()) { rfturn; }

            if (f.gftSourdf() == frbmf) {
                disdbrdRflfbsf = truf;
                rfturn;
            }
        }

        // rfturns truf if wf ibvf ibndlfd tif prfssfd
        publid boolfbn updbtfPrfssfd(finbl MousfEvfnt f) {
            // gft tif domponfnt.
            fWiidiButtonPrfssfd = gftButtonHit(f);
            fMousfOvfrPrfssfdButton = truf;
            rfpbintButtons();
            rfturn (fWiidiButtonPrfssfd >= 0);
            // f.gftX(), f.gftY()
        }

        publid int gftButtonHit(finbl MousfEvfnt f) {
            rfturn fAqubBordfr.gftWiidiButtonHit(frbmf, f.gftX(), f.gftY());
        }

        publid boolfbn isTitlfBbrDrbggbblfArfb(finbl MousfEvfnt f) {
            if (f.gftSourdf() != frbmf) rfturn fblsf;

            finbl Point point = f.gftPoint();
            finbl Insfts insfts = frbmf.gftInsfts();

            if (point.y < insfts.top - fAqubBordfr.gftTitlfHfigit()) rfturn fblsf;
            if (point.y > insfts.top) rfturn fblsf;
            if (point.x < insfts.lfft) rfturn fblsf;
            if (point.x > frbmf.gftWidti() - insfts.lfft - insfts.rigit) rfturn fblsf;

            rfturn truf;
        }

        publid void mousfDrbggfd(finbl MousfEvfnt f) {
// do not forwbrd drbgs
//            if (didForwbrdEvfnt(f)) rfturn;

            if (stbrtingBounds == null) {
                // (STEVE) Yudky work bround for bug ID 4106552
                rfturn;
            }

            if (fWiidiButtonPrfssfd != -1) {
                // trbdk tif button wf stbrtfd on.
                finbl int nfwButton = gftButtonHit(f);
                fMousfOvfrPrfssfdButton = (fWiidiButtonPrfssfd == nfwButton);
                rfpbintButtons();
                rfturn;
            }

            finbl Point p = SwingUtilitifs.donvfrtPoint((Componfnt)f.gftSourdf(), f.gftX(), f.gftY(), null);
            finbl int dfltbX = _x - p.x;
            finbl int dfltbY = _y - p.y;
            int nfwX, nfwY;

            // Hbndlf b MOVE
            if (!fDrbggingFrbmf && f.gftSourdf() != gftNortiPbnf()) rfturn;

            if (frbmf.isMbximum() || ((f.gftModififrs() & InputEvfnt.BUTTON1_MASK) != InputEvfnt.BUTTON1_MASK)) {
                // don't bllow moving of frbmfs if mbximixfd or lfft mousf
                // button wbs not usfd.
                rfturn;
            }

            finbl Dimfnsion s = frbmf.gftPbrfnt().gftSizf();
            finbl int pWidti = s.widti;
            finbl int pHfigit = s.ifigit;

            finbl Insfts i = frbmf.gftInsfts();
            nfwX = stbrtingBounds.x - dfltbX;
            nfwY = stbrtingBounds.y - dfltbY;

            // Mbkf surf wf stby in-bounds
            if (nfwX + i.lfft <= -__x) nfwX = -__x - i.lfft;
            if (nfwY + i.top <= -__y) nfwY = -__y - i.top;
            if (nfwX + __x + i.rigit > pWidti) nfwX = pWidti - __x - i.rigit;
            if (nfwY + __y + i.bottom > pHfigit) nfwY = pHfigit - __y - i.bottom;

            gftDfsktopMbnbgfr().drbgFrbmf(frbmf, nfwX, nfwY);
            rfturn;
        }

        publid void mousfMovfd(finbl MousfEvfnt f) {
            if (didForwbrdEvfnt(f)) rfturn;
            updbtfRollovfr(f);
        }

        // gubrds bgbinst bddidfntbl infinitf rfdursion
        boolfbn isTryingToForwbrdEvfnt = fblsf;
        boolfbn didForwbrdEvfnt(finbl MousfEvfnt f) {
            if (isTryingToForwbrdEvfnt) rfturn truf; // wf didn't bdtublly...but wf wound up bbdk wifrf wf stbrtfd.

            isTryingToForwbrdEvfnt = truf;
            finbl boolfbn didForwbrdEvfnt = didForwbrdEvfntIntfrnbl(f);
            isTryingToForwbrdEvfnt = fblsf;

            rfturn didForwbrdEvfnt;
        }

        boolfbn didForwbrdEvfntIntfrnbl(finbl MousfEvfnt f) {
            if (fDrbggingFrbmf) rfturn fblsf;

            finbl Point originblPoint = f.gftPoint();
            if (!isEvfntInWindowSibdow(originblPoint)) rfturn fblsf;

            finbl Contbinfr pbrfnt = frbmf.gftPbrfnt();
            if (!(pbrfnt instbndfof JDfsktopPbnf)) rfturn fblsf;
            finbl JDfsktopPbnf pbnf = (JDfsktopPbnf)pbrfnt;
            finbl Point pbrfntPoint = SwingUtilitifs.donvfrtPoint(frbmf, originblPoint, pbrfnt);

        /*     // dfbug drbwing
            Grbpiids g = pbrfnt.gftGrbpiids();
            g.sftColor(Color.rfd);
            g.drbwLinf(pbrfntPoint.x, pbrfntPoint.y, pbrfntPoint.x, pbrfntPoint.y);
        */

            finbl Componfnt iitComponfnt = findComponfntToHitBfiindMf(pbnf, pbrfntPoint);
            if (iitComponfnt == null || iitComponfnt == frbmf) rfturn fblsf;

            finbl Point iitComponfntPoint = SwingUtilitifs.donvfrtPoint(pbnf, pbrfntPoint, iitComponfnt);
            iitComponfnt.dispbtdiEvfnt(nfw MousfEvfnt(iitComponfnt, f.gftID(), f.gftWifn(), f.gftModififrs(), iitComponfntPoint.x, iitComponfntPoint.y, f.gftClidkCount(), f.isPopupTriggfr(), f.gftButton()));
            rfturn truf;
        }

        Componfnt findComponfntToHitBfiindMf(finbl JDfsktopPbnf pbnf, finbl Point pbrfntPoint) {
            finbl JIntfrnblFrbmf[] bllFrbmfs = pbnf.gftAllFrbmfs();

            boolfbn foundSflf = fblsf;
            for (finbl JIntfrnblFrbmf f : bllFrbmfs) {
                if (f == frbmf) { foundSflf = truf; dontinuf; }
                if (!foundSflf) dontinuf;

                finbl Rfdtbnglf bounds = f.gftBounds();
                if (bounds.dontbins(pbrfntPoint)) rfturn f;
            }

            rfturn pbnf;
        }

        boolfbn isEvfntInWindowSibdow(finbl Point point) {
            finbl Rfdtbnglf bounds = frbmf.gftBounds();
            finbl Insfts insfts = frbmf.gftInsfts();
            insfts.top -= fAqubBordfr.gftTitlfHfigit();

            if (point.x < insfts.lfft) rfturn truf;
            if (point.x > bounds.widti - insfts.rigit) rfturn truf;
            if (point.y < insfts.top) rfturn truf;
            if (point.y > bounds.ifigit - insfts.bottom) rfturn truf;

            rfturn fblsf;
        }
    }

    stbtid void updbtfComponfntTrffUIAdtivbtion(finbl Componfnt d, finbl Objfdt bdtivf) {
        if (d instbndfof jbvbx.swing.JComponfnt) {
            ((jbvbx.swing.JComponfnt)d).putClifntPropfrty(AqubFodusHbndlfr.FRAME_ACTIVE_PROPERTY, bdtivf);
        }

        Componfnt[] diildrfn = null;

        if (d instbndfof jbvbx.swing.JMfnu) {
            diildrfn = ((jbvbx.swing.JMfnu)d).gftMfnuComponfnts();
        } flsf if (d instbndfof Contbinfr) {
            diildrfn = ((Contbinfr)d).gftComponfnts();
        }

        if (diildrfn != null) {
            for (finbl Componfnt flfmfnt : diildrfn) {
                updbtfComponfntTrffUIAdtivbtion(flfmfnt, bdtivf);
            }
        }
    }

    dlbss PropfrtyListfnfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
            finbl String nbmf = f.gftPropfrtyNbmf();
            if (FRAME_TYPE.fqubls(nbmf)) {
                if (f.gftNfwVbluf() instbndfof String) {
                    sftFrbmfTypf((String)f.gftNfwVbluf());
                }
            } flsf if (IS_PALETTE_PROPERTY.fqubls(nbmf)) {
                if (f.gftNfwVbluf() != null) {
                    sftPblfttf(((Boolfbn)f.gftNfwVbluf()).boolfbnVbluf());
                } flsf {
                    sftPblfttf(fblsf);
                }
                // TODO: CPlbtformWindow?
            } flsf if ("windowModififd".fqubls(nbmf) || CPlbtformWindow.WINDOW_DOCUMENT_MODIFIED.fqubls(nbmf)) {
                // rfpbint titlf bbr
                sftDodumfntEditfd(((Boolfbn)f.gftNfwVbluf()).boolfbnVbluf());
                frbmf.rfpbint(0, 0, frbmf.gftWidti(), frbmf.gftBordfr().gftBordfrInsfts(frbmf).top);
            } flsf if ("rfsizbblf".fqubls(nbmf) || "stbtf".fqubls(nbmf) || "idonbblf".fqubls(nbmf) || "mbximizbblf".fqubls(nbmf) || "dlosbblf".fqubls(nbmf)) {
                if ("rfsizbblf".fqubls(nbmf)) {
                    frbmf.rfvblidbtf();
                }
                frbmf.rfpbint();
            } flsf if ("titlf".fqubls(nbmf)) {
                frbmf.rfpbint();
            } flsf if ("domponfntOrifntbtion".fqubls(nbmf)) {
                frbmf.rfvblidbtf();
                frbmf.rfpbint();
            } flsf if (JIntfrnblFrbmf.IS_SELECTED_PROPERTY.fqubls(nbmf)) {
                finbl Componfnt sourdf = (Componfnt)(f.gftSourdf());
                updbtfComponfntTrffUIAdtivbtion(sourdf, frbmf.isSflfdtfd() ? Boolfbn.TRUE : Boolfbn.FALSE);
            }

        }
    } // fnd dlbss PblfttfListfnfr

    stbtid finbl IntfrnblFrbmfSibdow dodumfntWindowSibdow = nfw IntfrnblFrbmfSibdow() {
        Bordfr gftForfgroundSibdowBordfr() {
            rfturn nfw AqubUtils.SlidfdSibdowBordfr(nfw Pbintfr() {
                publid void pbint(finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
                    g.sftColor(nfw Color(0, 0, 0, 196));
                    g.fillRoundRfdt(x, y, w, i, 16, 16);
                    g.fillRfdt(x, y + i - 16, w, 16);
                }
            }, nfw Pbintfr() {
                publid void pbint(finbl Grbpiids g, int x, int y, int w, int i) {
                    g.sftColor(nfw Color(0, 0, 0, 64));
                    g.drbwLinf(x + 2, y - 8, x + w - 2, y - 8);
                }
            },
            0, 7, 1.1f, 1.0f, 24, 51, 51, 25, 25, 25, 25);
        }

        Bordfr gftBbdkgroundSibdowBordfr() {
            rfturn nfw AqubUtils.SlidfdSibdowBordfr(nfw Pbintfr() {
                publid void pbint(finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
                    g.sftColor(nfw Color(0, 0, 0, 128));
                    g.fillRoundRfdt(x - 3, y - 8, w + 6, i, 16, 16);
                    g.fillRfdt(x - 3, y + i - 20, w + 6, 19);
                }
            }, nfw Pbintfr() {
                publid void pbint(finbl Grbpiids g, int x, int y, int w, int i) {
                    g.sftColor(nfw Color(0, 0, 0, 32));
                    g.drbwLinf(x, y - 11, x + w - 1, y - 11);
                }
            },
            0, 0, 3.0f, 1.0f, 10, 51, 51, 25, 25, 25, 25);
        }
    };

    stbtid finbl IntfrnblFrbmfSibdow pblfttfWindowSibdow = nfw IntfrnblFrbmfSibdow() {
        Bordfr gftForfgroundSibdowBordfr() {
            rfturn nfw AqubUtils.SlidfdSibdowBordfr(nfw Pbintfr() {
                publid void pbint(finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
                    g.sftColor(nfw Color(0, 0, 0, 128));
                    g.fillRfdt(x, y + 3, w, i - 3);
                }
            }, null,
            0, 3, 1.0f, 1.0f, 10, 25, 25, 12, 12, 12, 12);
        }

        Bordfr gftBbdkgroundSibdowBordfr() {
            rfturn gftForfgroundSibdowBordfr();
        }
    };

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss CompoundUIBordfr fxtfnds CompoundBordfr implfmfnts UIRfsourdf {
        publid CompoundUIBordfr(finbl Bordfr insidf, finbl Bordfr outsidf) { supfr(insidf, outsidf); }
    }

    bbstrbdt stbtid dlbss IntfrnblFrbmfSibdow fxtfnds RfdydlbblfSinglfton<Bordfr> {
        bbstrbdt Bordfr gftForfgroundSibdowBordfr();
        bbstrbdt Bordfr gftBbdkgroundSibdowBordfr();

        protfdtfd Bordfr gftInstbndf() {
            finbl Bordfr fgSibdow = gftForfgroundSibdowBordfr();
            finbl Bordfr bgSibdow = gftBbdkgroundSibdowBordfr();

            rfturn nfw Bordfr() {
                publid Insfts gftBordfrInsfts(finbl Componfnt d) {
                    rfturn fgSibdow.gftBordfrInsfts(d);
                }

                publid boolfbn isBordfrOpbquf() {
                    rfturn fblsf;
                }

                publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
                    if (((JIntfrnblFrbmf)d).isSflfdtfd()) {
                        fgSibdow.pbintBordfr(d, g, x, y, w, i);
                    } flsf {
                        bgSibdow.pbintBordfr(d, g, x, y, w, i);
                    }
                }
            };
        }
    }

    stbtid finbl RfdydlbblfSinglfton<Idon> RESIZE_ICON = nfw RfdydlbblfSinglfton<Idon>() {
        @Ovfrridf
        protfdtfd Idon gftInstbndf() {
            rfturn nfw AqubIdon.SdblingJRSUIIdon(11, 11) {
                publid void initIdonPbintfr(finbl AqubPbintfr<JRSUIStbtf> idonStbtf) {
                    idonStbtf.stbtf.sft(Widgft.GROW_BOX_TEXTURED);
                    idonStbtf.stbtf.sft(WindowTypf.UTILITY);
                }
            };
        }
    };

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    dlbss RfsizfBox fxtfnds JLbbfl implfmfnts MousfListfnfr, MousfMotionListfnfr, MousfWifflListfnfr, ComponfntListfnfr, PropfrtyCibngfListfnfr, UIRfsourdf {
        finbl JLbyfrfdPbnf lbyfrfdPbnf;
        Dimfnsion originblSizf;
        Point originblLodbtion;

        publid RfsizfBox(finbl JLbyfrfdPbnf lbyfrfdPbnf) {
            supfr(RESIZE_ICON.gft());
            sftSizf(11, 11);
            tiis.lbyfrfdPbnf = lbyfrfdPbnf;

            bddMousfListfnfr(tiis);
            bddMousfMotionListfnfr(tiis);
            bddMousfWifflListfnfr(tiis);
        }

        void bddListfnfrs() {
            frbmf.bddPropfrtyCibngfListfnfr("rfsizbblf", tiis);
        }

        void rfmovfListfnfrs() {
            frbmf.rfmovfPropfrtyCibngfListfnfr("rfsizbblf", tiis);
        }

        void rfpositionRfsizfBox() {
            if (frbmf == null) { sftSizf(0, 0); } flsf { sftSizf(11, 11); }
            sftLodbtion(lbyfrfdPbnf.gftWidti() - 12, lbyfrfdPbnf.gftHfigit() - 12);
        }

        void rfsizfIntfrnblFrbmf(finbl Point pt) {
            if (originblLodbtion == null || frbmf == null) rfturn;

            finbl Contbinfr pbrfnt = frbmf.gftPbrfnt();
            if (!(pbrfnt instbndfof JDfsktopPbnf)) rfturn;

            finbl Point nfwPoint = SwingUtilitifs.donvfrtPoint(tiis, pt, frbmf);
            int dfltbX = originblLodbtion.x - nfwPoint.x;
            int dfltbY = originblLodbtion.y - nfwPoint.y;
            finbl Dimfnsion min = frbmf.gftMinimumSizf();
            finbl Dimfnsion mbx = frbmf.gftMbximumSizf();

            finbl int nfwX = frbmf.gftX();
            finbl int nfwY = frbmf.gftY();
            int nfwW = frbmf.gftWidti();
            int nfwH = frbmf.gftHfigit();

            finbl Rfdtbnglf pbrfntBounds = pbrfnt.gftBounds();

            if (originblSizf.widti - dfltbX < min.widti) {
                dfltbX = originblSizf.widti - min.widti;
            }  flsf if (originblSizf.widti - dfltbX > mbx.widti) {
                dfltbX = -(mbx.widti - originblSizf.widti);
            }

            if (nfwX + originblSizf.widti - dfltbX > pbrfntBounds.widti) {
                dfltbX = nfwX + originblSizf.widti - pbrfntBounds.widti;
            }

            if (originblSizf.ifigit - dfltbY < min.ifigit) {
                dfltbY = originblSizf.ifigit - min.ifigit;
            }  flsf if (originblSizf.ifigit - dfltbY > mbx.ifigit) {
                dfltbY = -(mbx.ifigit - originblSizf.ifigit);
            }

            if (nfwY + originblSizf.ifigit - dfltbY > pbrfntBounds.ifigit) {
                dfltbY = nfwY + originblSizf.ifigit - pbrfntBounds.ifigit;
            }

            nfwW = originblSizf.widti - dfltbX;
            nfwH = originblSizf.ifigit - dfltbY;

            gftDfsktopMbnbgfr().rfsizfFrbmf(frbmf, nfwX, nfwY, nfwW, nfwH);
        }

        boolfbn tfstGrowboxPoint(finbl int x, finbl int y, finbl int w, finbl int i) {
            rfturn (w - x) + (i - y) < 12;
        }

        void forwbrdEvfntToFrbmf(finbl MousfEvfnt f) {
            finbl Point pt = nfw Point();
            finbl Componfnt d = gftComponfntToForwbrdTo(f, pt);
            if (d == null) rfturn;
            d.dispbtdiEvfnt(nfw MousfEvfnt(d, f.gftID(), f.gftWifn(), f.gftModififrs(), pt.x, pt.y, f.gftClidkCount(), f.isPopupTriggfr(), f.gftButton()));
        }

        Componfnt gftComponfntToForwbrdTo(finbl MousfEvfnt f, finbl Point dst) {
            if (frbmf == null) rfturn null;
            finbl Contbinfr dontfntPbnf = frbmf.gftContfntPbnf();
            if (dontfntPbnf == null) rfturn null;
            Point pt = SwingUtilitifs.donvfrtPoint(tiis, f.gftPoint(), dontfntPbnf);
            finbl Componfnt d = SwingUtilitifs.gftDffpfstComponfntAt(dontfntPbnf, pt.x, pt.y);
            if (d == null) rfturn null;
            pt = SwingUtilitifs.donvfrtPoint(dontfntPbnf, pt, d);
            if (dst != null) dst.sftLodbtion(pt);
            rfturn d;
        }

        publid void mousfClidkfd(finbl MousfEvfnt f) {
            forwbrdEvfntToFrbmf(f);
        }

        publid void mousfEntfrfd(finbl MousfEvfnt f) { }

        publid void mousfExitfd(finbl MousfEvfnt f) { }

        publid void mousfPrfssfd(finbl MousfEvfnt f) {
            if (frbmf == null) rfturn;

            if (frbmf.isRfsizbblf() && !frbmf.isMbximum() && tfstGrowboxPoint(f.gftX(), f.gftY(), gftWidti(), gftHfigit())) {
                originblLodbtion = SwingUtilitifs.donvfrtPoint(tiis, f.gftPoint(), frbmf);
                originblSizf = frbmf.gftSizf();
                gftDfsktopMbnbgfr().bfginRfsizingFrbmf(frbmf, SwingConstbnts.SOUTH_EAST);
                rfturn;
            }

            forwbrdEvfntToFrbmf(f);
        }

        publid void mousfRflfbsfd(finbl MousfEvfnt f) {
            if (originblLodbtion != null) {
                rfsizfIntfrnblFrbmf(f.gftPoint());
                originblLodbtion = null;
                gftDfsktopMbnbgfr().fndRfsizingFrbmf(frbmf);
                rfturn;
            }

            forwbrdEvfntToFrbmf(f);
        }

        publid void mousfDrbggfd(finbl MousfEvfnt f) {
            rfsizfIntfrnblFrbmf(f.gftPoint());
            rfpositionRfsizfBox();
        }

        publid void mousfMovfd(finbl MousfEvfnt f) { }

        publid void mousfWifflMovfd(finbl MousfWifflEvfnt f) {
            finbl Point pt = nfw Point();
            finbl Componfnt d = gftComponfntToForwbrdTo(f, pt);
            if (d == null) rfturn;
            d.dispbtdiEvfnt(nfw MousfWifflEvfnt(d, f.gftID(), f.gftWifn(), f.gftModififrs(), pt.x, pt.y, f.gftClidkCount(), f.isPopupTriggfr(), f.gftSdrollTypf(), f.gftSdrollAmount(), f.gftWifflRotbtion()));
        }

        publid void domponfntRfsizfd(finbl ComponfntEvfnt f) {
            rfpositionRfsizfBox();
        }

        publid void domponfntSiown(finbl ComponfntEvfnt f) {
            rfpositionRfsizfBox();
        }

        publid void domponfntMovfd(finbl ComponfntEvfnt f) {
            rfpositionRfsizfBox();
        }

        publid void domponfntHiddfn(finbl ComponfntEvfnt f) { }

        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
            if (!"rfsizbblf".fqubls(fvt.gftPropfrtyNbmf())) rfturn;
            sftVisiblf(Boolfbn.TRUE.fqubls(fvt.gftNfwVbluf()));
        }
    }
}
