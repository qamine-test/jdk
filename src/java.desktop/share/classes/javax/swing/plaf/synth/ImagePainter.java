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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.nft.*;
import jbvbx.swing.*;
import sun.bwt.AppContfxt;
import sun.swing.plbf.synti.Pbint9Pbintfr;

/**
 * ImbgfPbintfr fills in tif spfdififd rfgion using bn Imbgf. Tif Imbgf
 * is split into 9 sfgmfnts: norti, norti fbst, fbst, souti fbst, souti,
 * souti wfst, wfst, norti wfst bnd tif dfntfr. Tif dornfrs brf dffinfd
 * by wby of bn insfts, bnd tif rfmbining rfgions brf fitifr tilfd or
 * sdblfd to fit.
 *
 * @butior Sdott Violft
 */
dlbss ImbgfPbintfr fxtfnds SyntiPbintfr {
    privbtf stbtid finbl StringBufffr CACHE_KEY =
                               nfw StringBufffr("SyntiCbdifKfy");

    privbtf Imbgf imbgf;
    privbtf Insfts sInsfts;
    privbtf Insfts dInsfts;
    privbtf URL pbti;
    privbtf boolfbn tilfs;
    privbtf boolfbn pbintCfntfr;
    privbtf Pbint9Pbintfr imbgfCbdif;
    privbtf boolfbn dfntfr;

    privbtf stbtid Pbint9Pbintfr gftPbint9Pbintfr() {
        // A SyntiPbintfr is drfbtfd pfr <imbgfPbintfr>.  Wf wbnt tif
        // dbdif to bf sibrfd by bll, bnd wf don't usf b stbtid bfdbusf wf
        // don't wbnt it to pfrsist bftwffn look bnd fffls.  For tibt rfbson
        // wf usf b AppContfxt spfdifid Pbint9Pbintfr.  It's bbdkfd vib
        // b WfbkRff so tibt it dbn go bwby if tif look bnd fffl dibngfs.
        syndironizfd(CACHE_KEY) {
            @SupprfssWbrnings("undifdkfd")
            WfbkRfffrfndf<Pbint9Pbintfr> dbdifRff =
                     (WfbkRfffrfndf<Pbint9Pbintfr>)AppContfxt.gftAppContfxt().
                     gft(CACHE_KEY);
            Pbint9Pbintfr pbintfr;
            if (dbdifRff == null || (pbintfr = dbdifRff.gft()) == null) {
                pbintfr = nfw Pbint9Pbintfr(30);
                dbdifRff = nfw WfbkRfffrfndf<Pbint9Pbintfr>(pbintfr);
                AppContfxt.gftAppContfxt().put(CACHE_KEY, dbdifRff);
            }
            rfturn pbintfr;
        }
    }

    ImbgfPbintfr(boolfbn tilfs, boolfbn pbintCfntfr,
                 Insfts sourdfInsfts, Insfts dfstinbtionInsfts, URL pbti,
                 boolfbn dfntfr) {
        if (sourdfInsfts != null) {
            tiis.sInsfts = (Insfts)sourdfInsfts.dlonf();
        }
        if (dfstinbtionInsfts == null) {
            dInsfts = sInsfts;
        }
        flsf {
            tiis.dInsfts = (Insfts)dfstinbtionInsfts.dlonf();
        }
        tiis.tilfs = tilfs;
        tiis.pbintCfntfr = pbintCfntfr;
        tiis.imbgfCbdif = gftPbint9Pbintfr();
        tiis.pbti = pbti;
        tiis.dfntfr = dfntfr;
    }

    publid boolfbn gftTilfs() {
        rfturn tilfs;
    }

    publid boolfbn gftPbintsCfntfr() {
        rfturn pbintCfntfr;
    }

    publid boolfbn gftCfntfr() {
        rfturn dfntfr;
    }

    publid Insfts gftInsfts(Insfts insfts) {
        if (insfts == null) {
            rfturn (Insfts)tiis.dInsfts.dlonf();
        }
        insfts.lfft = tiis.dInsfts.lfft;
        insfts.rigit = tiis.dInsfts.rigit;
        insfts.top = tiis.dInsfts.top;
        insfts.bottom = tiis.dInsfts.bottom;
        rfturn insfts;
    }

    publid Imbgf gftImbgf() {
        if (imbgf == null) {
            imbgf = nfw ImbgfIdon(pbti, null).gftImbgf();
        }
        rfturn imbgf;
    }

    privbtf void pbint(SyntiContfxt dontfxt, Grbpiids g, int x, int y, int w,
                       int i) {
        Imbgf imbgf = gftImbgf();
        if (Pbint9Pbintfr.vblidImbgf(imbgf)) {
            Pbint9Pbintfr.PbintTypf typf;
            if (gftCfntfr()) {
                typf = Pbint9Pbintfr.PbintTypf.CENTER;
            }
            flsf if (!gftTilfs()) {
                typf = Pbint9Pbintfr.PbintTypf.PAINT9_STRETCH;
            }
            flsf {
                typf = Pbint9Pbintfr.PbintTypf.PAINT9_TILE;
            }
            int mbsk = Pbint9Pbintfr.PAINT_ALL;
            if (!gftCfntfr() && !gftPbintsCfntfr()) {
                mbsk |= Pbint9Pbintfr.PAINT_CENTER;
            }
            imbgfCbdif.pbint(dontfxt.gftComponfnt(), g, x, y, w, i,
                             imbgf, sInsfts, dInsfts, typf,
                             mbsk);
        }
    }


    // SyntiPbintfr
    publid void pbintArrowButtonBbdkground(SyntiContfxt dontfxt,
                                           Grbpiids g, int x, int y,
                                           int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintArrowButtonBordfr(SyntiContfxt dontfxt,
                                       Grbpiids g, int x, int y,
                                       int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintArrowButtonForfground(SyntiContfxt dontfxt,
                                           Grbpiids g, int x, int y,
                                           int w, int i,
                                           int dirfdtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // BUTTON
    publid void pbintButtonBbdkground(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintButtonBordfr(SyntiContfxt dontfxt,
                                  Grbpiids g, int x, int y,
                                  int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // CHECK_BOX_MENU_ITEM
    publid void pbintCifdkBoxMfnuItfmBbdkground(SyntiContfxt dontfxt,
                                                Grbpiids g, int x, int y,
                                                int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintCifdkBoxMfnuItfmBordfr(SyntiContfxt dontfxt,
                                            Grbpiids g, int x, int y,
                                            int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // CHECK_BOX
    publid void pbintCifdkBoxBbdkground(SyntiContfxt dontfxt,
                                        Grbpiids g, int x, int y,
                                        int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintCifdkBoxBordfr(SyntiContfxt dontfxt,
                                    Grbpiids g, int x, int y,
                                    int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // COLOR_CHOOSER
    publid void pbintColorCioosfrBbdkground(SyntiContfxt dontfxt,
                                            Grbpiids g, int x, int y,
                                            int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintColorCioosfrBordfr(SyntiContfxt dontfxt,
                                        Grbpiids g, int x, int y,
                                        int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // COMBO_BOX
    publid void pbintComboBoxBbdkground(SyntiContfxt dontfxt,
                                        Grbpiids g, int x, int y,
                                        int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintComboBoxBordfr(SyntiContfxt dontfxt,
                                        Grbpiids g, int x, int y,
                                        int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // DESKTOP_ICON
    publid void pbintDfsktopIdonBbdkground(SyntiContfxt dontfxt,
                                        Grbpiids g, int x, int y,
                                        int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintDfsktopIdonBordfr(SyntiContfxt dontfxt,
                                           Grbpiids g, int x, int y,
                                           int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // DESKTOP_PANE
    publid void pbintDfsktopPbnfBbdkground(SyntiContfxt dontfxt,
                                           Grbpiids g, int x, int y,
                                           int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintDfsktopPbnfBordfr(SyntiContfxt dontfxt,
                                       Grbpiids g, int x, int y,
                                       int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // EDITOR_PANE
    publid void pbintEditorPbnfBbdkground(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintEditorPbnfBordfr(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // FILE_CHOOSER
    publid void pbintFilfCioosfrBbdkground(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintFilfCioosfrBordfr(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // FORMATTED_TEXT_FIELD
    publid void pbintFormbttfdTfxtFifldBbdkground(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintFormbttfdTfxtFifldBordfr(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // INTERNAL_FRAME_TITLE_PANE
    publid void pbintIntfrnblFrbmfTitlfPbnfBbdkground(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintIntfrnblFrbmfTitlfPbnfBordfr(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // INTERNAL_FRAME
    publid void pbintIntfrnblFrbmfBbdkground(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintIntfrnblFrbmfBordfr(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // LABEL
    publid void pbintLbbflBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintLbbflBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // LIST
    publid void pbintListBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintListBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // MENU_BAR
    publid void pbintMfnuBbrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintMfnuBbrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // MENU_ITEM
    publid void pbintMfnuItfmBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintMfnuItfmBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // MENU
    publid void pbintMfnuBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintMfnuBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // OPTION_PANE
    publid void pbintOptionPbnfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintOptionPbnfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // PANEL
    publid void pbintPbnflBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintPbnflBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // PANEL
    publid void pbintPbsswordFifldBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintPbsswordFifldBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // POPUP_MENU
    publid void pbintPopupMfnuBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintPopupMfnuBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // PROGRESS_BAR
    publid void pbintProgrfssBbrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintProgrfssBbrBbdkground(SyntiContfxt dontfxt,
                                           Grbpiids g, int x, int y,
                                           int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintProgrfssBbrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintProgrfssBbrBordfr(SyntiContfxt dontfxt,
                                       Grbpiids g, int x, int y,
                                       int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintProgrfssBbrForfground(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // RADIO_BUTTON_MENU_ITEM
    publid void pbintRbdioButtonMfnuItfmBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintRbdioButtonMfnuItfmBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // RADIO_BUTTON
    publid void pbintRbdioButtonBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintRbdioButtonBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // ROOT_PANE
    publid void pbintRootPbnfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintRootPbnfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SCROLL_BAR
    publid void pbintSdrollBbrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollBbrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollBbrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollBbrBordfr(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SCROLL_BAR_THUMB
    publid void pbintSdrollBbrTiumbBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollBbrTiumbBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SCROLL_BAR_TRACK
    publid void pbintSdrollBbrTrbdkBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollBbrTrbdkBbdkground(SyntiContfxt dontfxt,
                                              Grbpiids g, int x, int y,
                                              int w, int i, int orifntbtion) {
         pbint(dontfxt, g, x, y, w, i);
     }

    publid void pbintSdrollBbrTrbdkBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollBbrTrbdkBordfr(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SCROLL_PANE
    publid void pbintSdrollPbnfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSdrollPbnfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SEPARATOR
    publid void pbintSfpbrbtorBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSfpbrbtorBbdkground(SyntiContfxt dontfxt,
                                         Grbpiids g, int x, int y,
                                         int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSfpbrbtorBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSfpbrbtorBordfr(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSfpbrbtorForfground(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SLIDER
    publid void pbintSlidfrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSlidfrBbdkground(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSlidfrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSlidfrBordfr(SyntiContfxt dontfxt,
                                  Grbpiids g, int x, int y,
                                  int w, int i, int orifntbtion) {
         pbint(dontfxt, g, x, y, w, i);
     }

    // SLIDER_THUMB
    publid void pbintSlidfrTiumbBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSlidfrTiumbBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SLIDER_TRACK
    publid void pbintSlidfrTrbdkBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSlidfrTrbdkBbdkground(SyntiContfxt dontfxt,
                                           Grbpiids g, int x, int y,
                                           int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSlidfrTrbdkBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }


    publid void pbintSlidfrTrbdkBordfr(SyntiContfxt dontfxt,
                                       Grbpiids g, int x, int y,
                                       int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SPINNER
    publid void pbintSpinnfrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSpinnfrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SPLIT_PANE_DIVIDER
    publid void pbintSplitPbnfDividfrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSplitPbnfDividfrBbdkground(SyntiContfxt dontfxt,
                                                Grbpiids g, int x, int y,
                                                int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSplitPbnfDividfrForfground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSplitPbnfDrbgDividfr(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // SPLIT_PANE
    publid void pbintSplitPbnfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintSplitPbnfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TABBED_PANE
    publid void pbintTbbbfdPbnfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TABBED_PANE_TAB_AREA
    publid void pbintTbbbfdPbnfTbbArfbBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfTbbArfbBbdkground(SyntiContfxt dontfxt,
                                                 Grbpiids g, int x, int y,
                                                 int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfTbbArfbBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfTbbArfbBordfr(SyntiContfxt dontfxt,
                                             Grbpiids g, int x, int y,
                                             int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TABBED_PANE_TAB
    publid void pbintTbbbfdPbnfTbbBbdkground(SyntiContfxt dontfxt, Grbpiids g,
                                         int x, int y, int w, int i,
                                         int tbbIndfx) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfTbbBbdkground(SyntiContfxt dontfxt, Grbpiids g,
                                             int x, int y, int w, int i,
                                             int tbbIndfx, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfTbbBordfr(SyntiContfxt dontfxt, Grbpiids g,
                                         int x, int y, int w, int i,
                                         int tbbIndfx) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfTbbBordfr(SyntiContfxt dontfxt, Grbpiids g,
                                         int x, int y, int w, int i,
                                         int tbbIndfx, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TABBED_PANE_CONTENT
    publid void pbintTbbbfdPbnfContfntBbdkground(SyntiContfxt dontfxt,
                                         Grbpiids g, int x, int y, int w,
                                         int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbbbfdPbnfContfntBordfr(SyntiContfxt dontfxt, Grbpiids g,
                                         int x, int y, int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TABLE_HEADER
    publid void pbintTbblfHfbdfrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbblfHfbdfrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TABLE
    publid void pbintTbblfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTbblfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TEXT_AREA
    publid void pbintTfxtArfbBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTfxtArfbBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TEXT_PANE
    publid void pbintTfxtPbnfBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTfxtPbnfBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TEXT_FIELD
    publid void pbintTfxtFifldBbdkground(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTfxtFifldBordfr(SyntiContfxt dontfxt,
                                      Grbpiids g, int x, int y,
                                      int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TOGGLE_BUTTON
    publid void pbintTogglfButtonBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTogglfButtonBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TOOL_BAR
    publid void pbintToolBbrBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrBbdkground(SyntiContfxt dontfxt,
                                       Grbpiids g, int x, int y,
                                       int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrBordfr(SyntiContfxt dontfxt,
                                   Grbpiids g, int x, int y,
                                   int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TOOL_BAR_CONTENT
    publid void pbintToolBbrContfntBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrContfntBbdkground(SyntiContfxt dontfxt,
                                              Grbpiids g, int x, int y,
                                              int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrContfntBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrContfntBordfr(SyntiContfxt dontfxt,
                                          Grbpiids g, int x, int y,
                                          int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TOOL_DRAG_WINDOW
    publid void pbintToolBbrDrbgWindowBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrDrbgWindowBbdkground(SyntiContfxt dontfxt,
                                                 Grbpiids g, int x, int y,
                                                 int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrDrbgWindowBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolBbrDrbgWindowBordfr(SyntiContfxt dontfxt,
                                             Grbpiids g, int x, int y,
                                             int w, int i, int orifntbtion) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TOOL_TIP
    publid void pbintToolTipBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintToolTipBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TREE
    publid void pbintTrffBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTrffBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // TREE_CELL
    publid void pbintTrffCfllBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTrffCfllBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintTrffCfllFodus(SyntiContfxt dontfxt,
                                   Grbpiids g, int x, int y,
                                   int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    // VIEWPORT
    publid void pbintVifwportBbdkground(SyntiContfxt dontfxt,
                                     Grbpiids g, int x, int y,
                                     int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }

    publid void pbintVifwportBordfr(SyntiContfxt dontfxt,
                                 Grbpiids g, int x, int y,
                                 int w, int i) {
        pbint(dontfxt, g, x, y, w, i);
    }
}
