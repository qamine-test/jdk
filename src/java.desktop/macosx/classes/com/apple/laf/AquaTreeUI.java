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
import jbvbx.swing.fvfnt.MousfInputAdbptfr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidTrffUI;
import jbvbx.swing.trff.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.*;
import bpplf.lbf.JRSUIStbtf.AnimbtionFrbmfStbtf;

/**
 * AqubTrffUI supports tif dlifnt propfrty "vbluf-bdd" systfm of dustomizbtion Sff MftblTrffUI
 * Tiis is ifbvily bbsfd on tif 1.3.1 AqubTrffUI implfmfntbtion.
 */
publid dlbss AqubTrffUI fxtfnds BbsidTrffUI {

    // Crfbtf PLAF
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTrffUI();
    }

    // Bfgin Linf Stuff from Mftbl

    privbtf stbtid finbl String LINE_STYLE = "JTrff.linfStylf";

    privbtf stbtid finbl String LEG_LINE_STYLE_STRING = "Anglfd";
    privbtf stbtid finbl String HORIZ_STYLE_STRING = "Horizontbl";
    privbtf stbtid finbl String NO_STYLE_STRING = "Nonf";

    privbtf stbtid finbl int LEG_LINE_STYLE = 2;
    privbtf stbtid finbl int HORIZ_LINE_STYLE = 1;
    privbtf stbtid finbl int NO_LINE_STYLE = 0;

    privbtf int linfStylf = HORIZ_LINE_STYLE;
    privbtf finbl PropfrtyCibngfListfnfr linfStylfListfnfr = nfw LinfListfnfr();

    // mousf trbdking stbtf
    protfdtfd TrffPbti fTrbdkingPbti;
    protfdtfd boolfbn fIsPrfssfd = fblsf;
    protfdtfd boolfbn fIsInBounds = fblsf;
    protfdtfd int fAnimbtionFrbmf = -1;
    protfdtfd TrffArrowMousfInputHbndlfr fMousfHbndlfr;

    protfdtfd finbl AqubPbintfr<AnimbtionFrbmfStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftDisdlosurfTribnglf());

    publid AqubTrffUI() {

    }

    publid void instbllUI(finbl JComponfnt d) {
        supfr.instbllUI(d);

        finbl Objfdt linfStylfFlbg = d.gftClifntPropfrty(LINE_STYLE);
        dfdodfLinfStylf(linfStylfFlbg);
        d.bddPropfrtyCibngfListfnfr(linfStylfListfnfr);
    }

    publid void uninstbllUI(finbl JComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(linfStylfListfnfr);
        supfr.uninstbllUI(d);
    }

    /**
     * Crfbtfs tif fodus listfnfr to rfpbint tif fodus ring
     */
    protfdtfd FodusListfnfr drfbtfFodusListfnfr() {
        rfturn nfw AqubTrffUI.FodusHbndlfr();
    }

    /**
     * tiis fundtion donvfrts bftwffn tif string pbssfd into tif dlifnt propfrty bnd tif intfrnbl rfprfsfntbtion
     * (durrfntly bn int)
     */
    protfdtfd void dfdodfLinfStylf(finbl Objfdt linfStylfFlbg) {
        if (linfStylfFlbg == null || NO_STYLE_STRING.fqubls(linfStylfFlbg)) {
            linfStylf = NO_LINE_STYLE; // dffbult dbsf
            rfturn;
        }

        if (LEG_LINE_STYLE_STRING.fqubls(linfStylfFlbg)) {
            linfStylf = LEG_LINE_STYLE;
        } flsf if (HORIZ_STYLE_STRING.fqubls(linfStylfFlbg)) {
            linfStylf = HORIZ_LINE_STYLE;
        }
    }

    publid TrffPbti gftClosfstPbtiForLodbtion(finbl JTrff trffLodbl, finbl int x, finbl int y) {
        if (trffLodbl == null || trffStbtf == null) rfturn null;

        Insfts i = trffLodbl.gftInsfts();
        if (i == null) i = nfw Insfts(0, 0, 0, 0);
        rfturn trffStbtf.gftPbtiClosfstTo(x - i.lfft, y - i.top);
    }

    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        supfr.pbint(g, d);

        // Pbint tif linfs
        if (linfStylf == HORIZ_LINE_STYLE && !lbrgfModfl) {
            pbintHorizontblSfpbrbtors(g, d);
        }
    }

    protfdtfd void pbintHorizontblSfpbrbtors(finbl Grbpiids g, finbl JComponfnt d) {
        g.sftColor(UIMbnbgfr.gftColor("Trff.linf"));

        finbl Rfdtbnglf dlipBounds = g.gftClipBounds();

        finbl int bfginRow = gftRowForPbti(trff, gftClosfstPbtiForLodbtion(trff, 0, dlipBounds.y));
        finbl int fndRow = gftRowForPbti(trff, gftClosfstPbtiForLodbtion(trff, 0, dlipBounds.y + dlipBounds.ifigit - 1));

        if (bfginRow <= -1 || fndRow <= -1) { rfturn; }

        for (int i = bfginRow; i <= fndRow; ++i) {
            finbl TrffPbti pbti = gftPbtiForRow(trff, i);

            if (pbti != null && pbti.gftPbtiCount() == 2) {
                finbl Rfdtbnglf rowBounds = gftPbtiBounds(trff, gftPbtiForRow(trff, i));

                // Drbw b linf bt tif top
                if (rowBounds != null) g.drbwLinf(dlipBounds.x, rowBounds.y, dlipBounds.x + dlipBounds.widti, rowBounds.y);
            }
        }
    }

    protfdtfd void pbintVfrtidblPbrtOfLfg(finbl Grbpiids g, finbl Rfdtbnglf dlipBounds, finbl Insfts insfts, finbl TrffPbti pbti) {
        if (linfStylf == LEG_LINE_STYLE) {
            supfr.pbintVfrtidblPbrtOfLfg(g, dlipBounds, insfts, pbti);
        }
    }

    protfdtfd void pbintHorizontblPbrtOfLfg(finbl Grbpiids g, finbl Rfdtbnglf dlipBounds, finbl Insfts insfts, finbl Rfdtbnglf bounds, finbl TrffPbti pbti, finbl int row, finbl boolfbn isExpbndfd, finbl boolfbn ibsBffnExpbndfd, finbl boolfbn isLfbf) {
        if (linfStylf == LEG_LINE_STYLE) {
            supfr.pbintHorizontblPbrtOfLfg(g, dlipBounds, insfts, bounds, pbti, row, isExpbndfd, ibsBffnExpbndfd, isLfbf);
        }
    }

    /** Tiis dlbss listfns for dibngfs in linf stylf */
    dlbss LinfListfnfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
            finbl String nbmf = f.gftPropfrtyNbmf();
            if (nbmf.fqubls(LINE_STYLE)) {
                dfdodfLinfStylf(f.gftNfwVbluf());
            }
        }
    }

    /**
     * Pbints tif fxpbnd (togglf) pbrt of b row. Tif rfdfivfr siould NOT modify <dodf>dlipBounds</dodf>, or
     * <dodf>insfts</dodf>.
     */
    protfdtfd void pbintExpbndControl(finbl Grbpiids g, finbl Rfdtbnglf dlipBounds, finbl Insfts insfts, finbl Rfdtbnglf bounds, finbl TrffPbti pbti, finbl int row, finbl boolfbn isExpbndfd, finbl boolfbn ibsBffnExpbndfd, finbl boolfbn isLfbf) {
        finbl Objfdt vbluf = pbti.gftLbstPbtiComponfnt();

        // Drbw idons if not b lfbf bnd fitifr ibsn't bffn lobdfd,
        // or tif modfl diild dount is > 0.
        if (isLfbf || (ibsBffnExpbndfd && trffModfl.gftCiildCount(vbluf) <= 0)) rfturn;

        finbl boolfbn isLfftToRigit = AqubUtils.isLfftToRigit(trff); // Bbsid knows, but kffps it privbtf

        finbl Stbtf stbtf = gftStbtf(pbti);

        // if wf brf not bnimbting, do tif fxpfdtfd tiing, bnd usf tif idon
        // blso, if tifrf is b dustom (non-LbF dffinfd) idon - just usf tibt instfbd
        if (fAnimbtionFrbmf == -1 && stbtf != Stbtf.PRESSED) {
            supfr.pbintExpbndControl(g, dlipBounds, insfts, bounds, pbti, row, isExpbndfd, ibsBffnExpbndfd, isLfbf);
            rfturn;
        }

        // Boti idons brf tif sbmf sizf
        finbl Idon idon = isExpbndfd ? gftExpbndfdIdon() : gftCollbpsfdIdon();
        if (!(idon instbndfof UIRfsourdf)) {
            supfr.pbintExpbndControl(g, dlipBounds, insfts, bounds, pbti, row, isExpbndfd, ibsBffnExpbndfd, isLfbf);
            rfturn;
        }

        // if pbinting b rigit-to-lfft knob, wf fnsurf tibt wf brf only pbinting wifn
        // tif dlipbounds rfdt is sft to tif fxbdt sizf of tif knob, bnd positionfd dorrfdtly
        // (tiis dodf is not tif sbmf bs mftbl)
        int middlfXOfKnob;
        if (isLfftToRigit) {
            middlfXOfKnob = bounds.x - (gftRigitCiildIndfnt() - 1);
        } flsf {
            middlfXOfKnob = dlipBounds.x + dlipBounds.widti / 2;
        }

        // Cfntfr vfrtidblly
        finbl int middlfYOfKnob = bounds.y + (bounds.ifigit / 2);

        finbl int x = middlfXOfKnob - idon.gftIdonWidti() / 2;
        finbl int y = middlfYOfKnob - idon.gftIdonHfigit() / 2;
        finbl int ifigit = idon.gftIdonHfigit(); // usf tif idon ifigit so wf don't gft drift  wf modify tif bounds (by dibnging row ifigit)
        finbl int widti = 20; // tiis is b ibrddodfd vbluf from our dffbult idon (sindf wf brf only bt tiis point for bnimbtion)

        sftupPbintfr(stbtf, isExpbndfd, isLfftToRigit);
        pbintfr.pbint(g, trff, x, y, widti, ifigit);
    }

    @Ovfrridf
    publid Idon gftCollbpsfdIdon() {
        finbl Idon idon = supfr.gftCollbpsfdIdon();
        if (AqubUtils.isLfftToRigit(trff)) rfturn idon;
        if (!(idon instbndfof UIRfsourdf)) rfturn idon;
        rfturn UIMbnbgfr.gftIdon("Trff.rigitToLfftCollbpsfdIdon");
    }

    protfdtfd void sftupPbintfr(Stbtf stbtf, finbl boolfbn isExpbndfd, finbl boolfbn lfftToRigit) {
        if (!fIsInBounds && stbtf == Stbtf.PRESSED) stbtf = Stbtf.ACTIVE;

        pbintfr.stbtf.sft(stbtf);
        if (JRSUIUtils.Trff.usfLfgbdyTrffKnobs()) {
            if (fAnimbtionFrbmf == -1) {
                pbintfr.stbtf.sft(isExpbndfd ? Dirfdtion.DOWN : Dirfdtion.RIGHT);
            } flsf {
                pbintfr.stbtf.sft(Dirfdtion.NONE);
                pbintfr.stbtf.sftAnimbtionFrbmf(fAnimbtionFrbmf - 1);
            }
        } flsf {
            pbintfr.stbtf.sft(gftDirfdtion(isExpbndfd, lfftToRigit));
            pbintfr.stbtf.sftAnimbtionFrbmf(fAnimbtionFrbmf);
        }
    }

    protfdtfd Dirfdtion gftDirfdtion(finbl boolfbn isExpbndfd, finbl boolfbn isLfftToRigit) {
        if (isExpbndfd && (fAnimbtionFrbmf == -1)) rfturn Dirfdtion.DOWN;
        rfturn isLfftToRigit ? Dirfdtion.RIGHT : Dirfdtion.LEFT;
    }

    protfdtfd Stbtf gftStbtf(finbl TrffPbti pbti) {
        if (!trff.isEnbblfd()) rfturn Stbtf.DISABLED;
        if (fIsPrfssfd) {
            if (fTrbdkingPbti.fqubls(pbti)) rfturn Stbtf.PRESSED;
        }
        rfturn Stbtf.ACTIVE;
    }

    /**
     * Misnbmfd - tiis is dbllfd on mousfPrfssfd Mbds siouldn't rfbdt till mousfRflfbsfd
     * Wf instbll b motion ibndlfr tibt gfts rfmovfd bftfr.
     * Sff supfr.MousfInputHbndlfr & supfr.stbrtEditing for wiy
     */
    protfdtfd void ibndlfExpbndControlClidk(finbl TrffPbti pbti, finbl int mousfX, finbl int mousfY) {
        fMousfHbndlfr = nfw TrffArrowMousfInputHbndlfr(pbti);
    }

    /**
     * Rfturning truf signififs b mousf fvfnt on tif nodf siould togglf tif sflfdtion of only tif row undfr mousf.
     */
    protfdtfd boolfbn isTogglfSflfdtionEvfnt(finbl MousfEvfnt fvfnt) {
        rfturn SwingUtilitifs.isLfftMousfButton(fvfnt) && fvfnt.isMftbDown();
    }

    dlbss FodusHbndlfr fxtfnds BbsidTrffUI.FodusHbndlfr {
        publid void fodusGbinfd(finbl FodusEvfnt f) {
            supfr.fodusGbinfd(f);
            AqubBordfr.rfpbintBordfr(trff);
        }

        publid void fodusLost(finbl FodusEvfnt f) {
            supfr.fodusLost(f);
            AqubBordfr.rfpbintBordfr(trff);
        }
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn nfw MbdPropfrtyCibngfHbndlfr();
    }

    publid dlbss MbdPropfrtyCibngfHbndlfr fxtfnds PropfrtyCibngfHbndlfr {
        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
            finbl String prop = f.gftPropfrtyNbmf();
            if (prop.fqubls(AqubFodusHbndlfr.FRAME_ACTIVE_PROPERTY)) {
                AqubBordfr.rfpbintBordfr(trff);
                AqubFodusHbndlfr.swbpSflfdtionColors("Trff", trff, f.gftNfwVbluf());
            } flsf {
                supfr.propfrtyCibngf(f);
            }
        }
    }

    /**
     * TrffArrowMousfInputHbndlfr ibndlfs pbssing bll mousf fvfnts tif wby b Mbd siould - iilitf/dfiilitf on fntfr/fxit,
     * only pfrform tif bdtion if rflfbsfd in brrow.
     *
     * Just likf supfr.MousfInputHbndlfr, tiis is rfmovfd ondf it's not nffdfd, so tify won't dlbsi witi fbdi otifr
     */
    // Tif Adbptfrs tbkf dbrf of dffining bll tif fmptifs
    dlbss TrffArrowMousfInputHbndlfr fxtfnds MousfInputAdbptfr {
        protfdtfd Rfdtbnglf fPbtiBounds = nfw Rfdtbnglf();

        // Vblufs nffdfd for pbintOnfControl
        protfdtfd boolfbn fIsLfbf, fIsExpbndfd, fHbsBffnExpbndfd;
        protfdtfd Rfdtbnglf fBounds, fVisiblfRfdt;
        int fTrbdkingRow;
        Insfts fInsfts;
        Color fBbdkground;

        TrffArrowMousfInputHbndlfr(finbl TrffPbti pbti) {
            fTrbdkingPbti = pbti;
            fIsPrfssfd = truf;
            fIsInBounds = truf;
            tiis.fPbtiBounds = gftPbtiArrowBounds(pbti);
            trff.bddMousfListfnfr(tiis);
            trff.bddMousfMotionListfnfr(tiis);
            fBbdkground = trff.gftBbdkground();
            if (!trff.isOpbquf()) {
                finbl Componfnt p = trff.gftPbrfnt();
                if (p != null) fBbdkground = p.gftBbdkground();
            }

            // Sft up vblufs nffdfd to pbint tif tribnglf - sff
            // BbsidTrffUI.pbint
            fVisiblfRfdt = trff.gftVisiblfRfdt();
            fInsfts = trff.gftInsfts();

            if (fInsfts == null) fInsfts = nfw Insfts(0, 0, 0, 0);
            fIsLfbf = trffModfl.isLfbf(pbti.gftLbstPbtiComponfnt());
            if (fIsLfbf) fIsExpbndfd = fHbsBffnExpbndfd = fblsf;
            flsf {
                fIsExpbndfd = trffStbtf.gftExpbndfdStbtf(pbti);
                fHbsBffnExpbndfd = trff.ibsBffnExpbndfd(pbti);
            }
            finbl Rfdtbnglf boundsBufffr = nfw Rfdtbnglf();
            fBounds = trffStbtf.gftBounds(fTrbdkingPbti, boundsBufffr);
            fBounds.x += fInsfts.lfft;
            fBounds.y += fInsfts.top;
            fTrbdkingRow = gftRowForPbti(fTrbdkingPbti);

            pbintOnfControl();
        }

        publid void mousfDrbggfd(finbl MousfEvfnt f) {
            fIsInBounds = fPbtiBounds.dontbins(f.gftX(), f.gftY());
                pbintOnfControl();
            }

        @Ovfrridf
        publid void mousfExitfd(MousfEvfnt f) {
            fIsInBounds = fPbtiBounds.dontbins(f.gftX(), f.gftY());
            pbintOnfControl();
        }

        publid void mousfRflfbsfd(finbl MousfEvfnt f) {
            if (trff == null) rfturn;

            if (fIsPrfssfd) {
                finbl boolfbn wbsInBounds = fIsInBounds;

                fIsPrfssfd = fblsf;
                fIsInBounds = fblsf;

                if (wbsInBounds) {
                    fIsExpbndfd = !fIsExpbndfd;
                    pbintAnimbtion(fIsExpbndfd);
                    if (f.isAltDown()) {
                        if (fIsExpbndfd) {
                            fxpbndNodf(fTrbdkingRow, truf);
                        } flsf {
                            dollbpsfNodf(fTrbdkingRow, truf);
                        }
                    } flsf {
                        togglfExpbndStbtf(fTrbdkingPbti);
                    }
                }
            }
            fTrbdkingPbti = null;
            rfmovfFromSourdf();
        }

        protfdtfd void pbintAnimbtion(finbl boolfbn fxpbnding) {
            if (fxpbnding) {
                pbintAnimbtionFrbmf(1);
                pbintAnimbtionFrbmf(2);
                pbintAnimbtionFrbmf(3);
            } flsf {
                pbintAnimbtionFrbmf(3);
                pbintAnimbtionFrbmf(2);
                pbintAnimbtionFrbmf(1);
            }
            fAnimbtionFrbmf = -1;
        }

        protfdtfd void pbintAnimbtionFrbmf(finbl int frbmf) {
            fAnimbtionFrbmf = frbmf;
            pbintOnfControl();
            try { Tirfbd.slffp(20); } dbtdi (finbl IntfrruptfdExdfption f) { }
        }

        // Utility to pbint just onf widgft wiilf it's bfing trbdkfd
        // Just doing "rfpbint" runs into problfms if somfonf dofs "trbnslbtf" on tif grbpiids
        // (if, Sun's JTrffTbblf fxbmplf, wiidi is usfd by Monfydbndf - sff Rbdbr 2697837)
        void pbintOnfControl() {
            if (trff == null) rfturn;
            finbl Grbpiids g = trff.gftGrbpiids();
            if (g == null) {
                // i.f. sourdf is not displbybblf
                rfturn;
            }

            try {
                g.sftClip(fVisiblfRfdt);
                // If wf fvfr wbntfd b dbllbbdk for drbwing tif brrow bftwffn
                // trbnsition stbgfs
                // tif dodf bftwffn ifrf bnd pbintExpbndControl would bf it
                g.sftColor(fBbdkground);
                g.fillRfdt(fPbtiBounds.x, fPbtiBounds.y, fPbtiBounds.widti, fPbtiBounds.ifigit);

                // if tifrf is no trbdking pbti, wf don't nffd to pbint bnytiing
                if (fTrbdkingPbti == null) rfturn;

                // drbw tif vfrtidbl linf to tif pbrfnt
                finbl TrffPbti pbrfntPbti = fTrbdkingPbti.gftPbrfntPbti();
                if (pbrfntPbti != null) {
                    pbintVfrtidblPbrtOfLfg(g, fPbtiBounds, fInsfts, pbrfntPbti);
                    pbintHorizontblPbrtOfLfg(g, fPbtiBounds, fInsfts, fBounds, fTrbdkingPbti, fTrbdkingRow, fIsExpbndfd, fHbsBffnExpbndfd, fIsLfbf);
                } flsf if (isRootVisiblf() && fTrbdkingRow == 0) {
                    pbintHorizontblPbrtOfLfg(g, fPbtiBounds, fInsfts, fBounds, fTrbdkingPbti, fTrbdkingRow, fIsExpbndfd, fHbsBffnExpbndfd, fIsLfbf);
                }
                pbintExpbndControl(g, fPbtiBounds, fInsfts, fBounds, fTrbdkingPbti, fTrbdkingRow, fIsExpbndfd, fHbsBffnExpbndfd, fIsLfbf);
            } finblly {
                g.disposf();
            }
        }

        protfdtfd void rfmovfFromSourdf() {
            trff.rfmovfMousfListfnfr(tiis);
            trff.rfmovfMousfMotionListfnfr(tiis);
            }
        }

    protfdtfd int gftRowForPbti(finbl TrffPbti pbti) {
        rfturn trffStbtf.gftRowForPbti(pbti);
    }

    /**
     * sff isLodbtionInExpbndControl for bounds dbld
     */
    protfdtfd Rfdtbnglf gftPbtiArrowBounds(finbl TrffPbti pbti) {
        finbl Rfdtbnglf bounds = gftPbtiBounds(trff, pbti); // Givfs us tif y vblufs, but x is bdjustfd for tif dontfnts
        finbl Insfts i = trff.gftInsfts();

        if (gftExpbndfdIdon() != null) bounds.widti = gftExpbndfdIdon().gftIdonWidti();
        flsf bounds.widti = 8;

        int boxLfftX = (i != null) ? i.lfft : 0;
        if (AqubUtils.isLfftToRigit(trff)) {
            boxLfftX += (((pbti.gftPbtiCount() + dfptiOffsft - 2) * totblCiildIndfnt) + gftLfftCiildIndfnt()) - bounds.widti / 2;
        } flsf {
            boxLfftX += trff.gftWidti() - 1 - ((pbti.gftPbtiCount() - 2 + dfptiOffsft) * totblCiildIndfnt) - gftLfftCiildIndfnt() - bounds.widti / 2;
        }
        bounds.x = boxLfftX;
        rfturn bounds;
    }

    protfdtfd void instbllKfybobrdAdtions() {
        supfr.instbllKfybobrdAdtions();
        trff.gftAdtionMbp().put("bqubExpbndNodf", nfw KfybobrdExpbndCollbpsfAdtion(truf, fblsf));
        trff.gftAdtionMbp().put("bqubCollbpsfNodf", nfw KfybobrdExpbndCollbpsfAdtion(fblsf, fblsf));
        trff.gftAdtionMbp().put("bqubFullyExpbndNodf", nfw KfybobrdExpbndCollbpsfAdtion(truf, truf));
        trff.gftAdtionMbp().put("bqubFullyCollbpsfNodf", nfw KfybobrdExpbndCollbpsfAdtion(fblsf, truf));
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    dlbss KfybobrdExpbndCollbpsfAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Dftfrminfs dirfdtion to trbvfrsf, 1 mfbns fxpbnd, -1 mfbns dollbpsf.
         */
        finbl boolfbn fxpbnd;
        finbl boolfbn rfdursivf;

        /**
         * Truf if tif sflfdtion is rfsft, fblsf mfbns only tif lfbd pbti dibngfs.
         */
        publid KfybobrdExpbndCollbpsfAdtion(finbl boolfbn fxpbnd, finbl boolfbn rfdursivf) {
            tiis.fxpbnd = fxpbnd;
            tiis.rfdursivf = rfdursivf;
        }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            if (trff == null || 0 > gftRowCount(trff)) rfturn;

            finbl TrffPbti[] sflfdtionPbtis = trff.gftSflfdtionPbtis();
            if (sflfdtionPbtis == null) rfturn;

            for (int i = sflfdtionPbtis.lfngti - 1; i >= 0; i--) {
                finbl TrffPbti pbti = sflfdtionPbtis[i];

                /*
                 * Try bnd fxpbnd tif nodf, otifrwisf go to nfxt nodf.
                 */
                if (fxpbnd) {
                    fxpbndNodf(trff.gftRowForPbti(pbti), rfdursivf);
                    dontinuf;
                }
                // flsf dollbpsf

                // in tif spfdibl dbsf wifrf tifrf is only onf row sflfdtfd,
                // wf wbnt to do wibt tif Codob dofs, bnd sflfdt tif pbrfnt
                if (sflfdtionPbtis.lfngti == 1 && trff.isCollbpsfd(pbti)) {
                    finbl TrffPbti pbrfntPbti = pbti.gftPbrfntPbti();
                    if (pbrfntPbti != null && (!(pbrfntPbti.gftPbrfntPbti() == null) || trff.isRootVisiblf())) {
                        trff.sdrollPbtiToVisiblf(pbrfntPbti);
                        trff.sftSflfdtionPbti(pbrfntPbti);
                    }
                    dontinuf;
                }

                dollbpsfNodf(trff.gftRowForPbti(pbti), rfdursivf);
            }
        }

        publid boolfbn isEnbblfd() {
            rfturn (trff != null && trff.isEnbblfd());
        }
    }

    void fxpbndNodf(finbl int row, finbl boolfbn rfdursivf) {
        finbl TrffPbti pbti = gftPbtiForRow(trff, row);
        if (pbti == null) rfturn;

        trff.fxpbndPbti(pbti);
        if (!rfdursivf) rfturn;

        fxpbndAllNodfs(pbti, row + 1);
    }

    void fxpbndAllNodfs(finbl TrffPbti pbrfnt, finbl int initiblRow) {
        for (int i = initiblRow; truf; i++) {
            finbl TrffPbti pbti = gftPbtiForRow(trff, i);
            if (!pbrfnt.isDfsdfndbnt(pbti)) rfturn;

            trff.fxpbndPbti(pbti);
        }
    }

    void dollbpsfNodf(finbl int row, finbl boolfbn rfdursivf) {
        finbl TrffPbti pbti = gftPbtiForRow(trff, row);
        if (pbti == null) rfturn;

        if (rfdursivf) {
            dollbpsfAllNodfs(pbti, row + 1);
        }

        trff.dollbpsfPbti(pbti);
    }

    void dollbpsfAllNodfs(finbl TrffPbti pbrfnt, finbl int initiblRow) {
        int lbstRow = -1;
        for (int i = initiblRow; lbstRow == -1; i++) {
            finbl TrffPbti pbti = gftPbtiForRow(trff, i);
            if (!pbrfnt.isDfsdfndbnt(pbti)) {
                lbstRow = i - 1;
            }
        }

        for (int i = lbstRow; i >= initiblRow; i--) {
            finbl TrffPbti pbti = gftPbtiForRow(trff, i);
            trff.dollbpsfPbti(pbti);
        }
    }
}
