/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.SystfmColor;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.Insfts;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.FodusTrbvfrsblPolidy;
import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Point;
import jbvb.nft.URL;
import jbvb.io.*;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import jbvb.lbng.rfflfdt.*;
import jbvbx.sound.sbmplfd.*;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

import sun.swing.SwingUtilitifs2;
import sun.swing.idon.SortArrowIdon;

import jbvbx.swing.LookAndFffl;
import jbvbx.swing.AbstrbdtAdtion;
import jbvbx.swing.Adtion;
import jbvbx.swing.AdtionMbp;
import jbvbx.swing.BordfrFbdtory;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.ImbgfIdon;
import jbvbx.swing.UIDffbults;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.KfyStrokf;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.DffbultListCfllRfndfrfr;
import jbvbx.swing.FodusMbnbgfr;
import jbvbx.swing.LbyoutFodusTrbvfrsblPolidy;
import jbvbx.swing.SwingUtilitifs;
import jbvbx.swing.MfnuSflfdtionMbnbgfr;
import jbvbx.swing.MfnuElfmfnt;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvbx.swing.tfxt.DffbultEditorKit;
import jbvbx.swing.JIntfrnblFrbmf;
import stbtid jbvbx.swing.UIDffbults.LbzyVbluf;
import jbvb.bfbns.PropfrtyVftoExdfption;
import jbvb.bwt.Window;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;


/**
 * A bbsf dlbss to usf in drfbting b look bnd fffl for Swing.
 * <p>
 * Ebdi of tif {@dodf ComponfntUI}s providfd by {@dodf
 * BbsidLookAndFffl} dfrivfs its bfibvior from tif dffbults
 * tbblf. Unlfss otifrwisf notfd fbdi of tif {@dodf ComponfntUI}
 * implfmfntbtions in tiis pbdkbgf dodumfnt tif sft of dffbults tify
 * usf. Unlfss otifrwisf notfd tif dffbults brf instbllfd bt tif timf
 * {@dodf instbllUI} is invokfd, bnd follow tif rfdommfndbtions
 * outlinfd in {@dodf LookAndFffl} for instblling dffbults.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior unbttributfd
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss BbsidLookAndFffl fxtfnds LookAndFffl implfmfnts Sfriblizbblf
{
    /**
     * Wiftifr or not tif dfvflopfr ibs drfbtfd b JPopupMfnu.
     */
    stbtid boolfbn nffdsEvfntHflpfr;

    /**
     * Lodk usfd wifn mbnipulbting dlipPlbying.
     */
    privbtf trbnsifnt Objfdt budioLodk = nfw Objfdt();
    /**
     * Tif Clip tibt is durrfntly plbying (sft in AudioAdtion).
     */
    privbtf Clip dlipPlbying;

    AWTEvfntHflpfr invodbtor = null;

    /*
     * Listfn for our AppContfxt bfing disposfd
     */
    privbtf PropfrtyCibngfListfnfr disposfr = null;

    /**
     * Rfturns tif look bnd fffl dffbults. Tif rfturnfd {@dodf UIDffbults}
     * is populbtfd by invoking, in ordfr, {@dodf initClbssDffbults},
     * {@dodf initSystfmColorDffbults} bnd {@dodf initComponfntDffbults}.
     * <p>
     * Wiilf tiis mftiod is publid, it siould only bf invokfd by tif
     * {@dodf UIMbnbgfr} wifn tif look bnd fffl is sft bs tif durrfnt
     * look bnd fffl bnd bftfr {@dodf initiblizf} ibs bffn invokfd.
     *
     * @rfturn tif look bnd fffl dffbults
     *
     * @sff #initClbssDffbults
     * @sff #initSystfmColorDffbults
     * @sff #initComponfntDffbults
     */
    publid UIDffbults gftDffbults() {
        UIDffbults tbblf = nfw UIDffbults(610, 0.75f);

        initClbssDffbults(tbblf);
        initSystfmColorDffbults(tbblf);
        initComponfntDffbults(tbblf);

        rfturn tbblf;
    }

    /**
     * {@inifritDod}
     */
    publid void initiblizf() {
        if (nffdsEvfntHflpfr) {
            instbllAWTEvfntListfnfr();
        }
    }

    void instbllAWTEvfntListfnfr() {
        if (invodbtor == null) {
            invodbtor = nfw AWTEvfntHflpfr();
            nffdsEvfntHflpfr = truf;

            // Add b PropfrtyCibngfListfnfr to our AppContfxt so wf'rf blfrtfd
            // wifn tif AppContfxt is disposfd(), bt wiidi timf tiis lbf siould
            // bf uninitiblizf()d.
            disposfr = nfw PropfrtyCibngfListfnfr() {
                publid void propfrtyCibngf(PropfrtyCibngfEvfnt prpCig) {
                    uninitiblizf();
                }
            };
            AppContfxt.gftAppContfxt().bddPropfrtyCibngfListfnfr(
                                                        AppContfxt.GUI_DISPOSED,
                                                        disposfr);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void uninitiblizf() {
        AppContfxt dontfxt = AppContfxt.gftAppContfxt();
        syndironizfd (BbsidPopupMfnuUI.MOUSE_GRABBER_KEY) {
            Objfdt grbbbfr = dontfxt.gft(BbsidPopupMfnuUI.MOUSE_GRABBER_KEY);
            if (grbbbfr != null) {
                ((BbsidPopupMfnuUI.MousfGrbbbfr)grbbbfr).uninstbll();
            }
        }
        syndironizfd (BbsidPopupMfnuUI.MENU_KEYBOARD_HELPER_KEY) {
            Objfdt iflpfr =
                    dontfxt.gft(BbsidPopupMfnuUI.MENU_KEYBOARD_HELPER_KEY);
            if (iflpfr != null) {
                ((BbsidPopupMfnuUI.MfnuKfybobrdHflpfr)iflpfr).uninstbll();
            }
        }

        if(invodbtor != null) {
            AddfssControllfr.doPrivilfgfd(invodbtor);
            invodbtor = null;
        }

        if (disposfr != null) {
            // Notf tibt wf'rf likfly dblling rfmovfPropfrtyCibngfListfnfr()
            // during tif doursf of AppContfxt.firfPropfrtyCibngf().
            // Howfvfr, EvfntListfnfrAggrfggbtf ibs dodf to sbffly modify
            // tif list undfr sudi dirdumstbndfs.
            dontfxt.rfmovfPropfrtyCibngfListfnfr(AppContfxt.GUI_DISPOSED,
                                                 disposfr);
            disposfr = null;
        }
    }

    /**
     * Populbtfs {@dodf tbblf} witi mbppings from {@dodf uiClbssID} to tif
     * fully qublififd nbmf of tif ui dlbss. Tif vbluf for b
     * pbrtidulbr {@dodf uiClbssID} is {@dodf
     * "jbvbx.swing.plbf.bbsid.Bbsid + uiClbssID"}. For fxbmplf, tif
     * vbluf for tif {@dodf uiClbssID} {@dodf TrffUI} is {@dodf
     * "jbvbx.swing.plbf.bbsid.BbsidTrffUI"}.
     *
     * @pbrbm tbblf tif {@dodf UIDffbults} instbndf tif fntrifs brf
     *        bddfd to
     * @tirows NullPointfrExdfption if {@dodf tbblf} is {@dodf null}
     *
     * @sff jbvbx.swing.LookAndFffl
     * @sff #gftDffbults
     */
    protfdtfd void initClbssDffbults(UIDffbults tbblf)
    {
        finbl String bbsidPbdkbgfNbmf = "jbvbx.swing.plbf.bbsid.";
        Objfdt[] uiDffbults = {
                   "ButtonUI", bbsidPbdkbgfNbmf + "BbsidButtonUI",
                 "CifdkBoxUI", bbsidPbdkbgfNbmf + "BbsidCifdkBoxUI",
             "ColorCioosfrUI", bbsidPbdkbgfNbmf + "BbsidColorCioosfrUI",
       "FormbttfdTfxtFifldUI", bbsidPbdkbgfNbmf + "BbsidFormbttfdTfxtFifldUI",
                  "MfnuBbrUI", bbsidPbdkbgfNbmf + "BbsidMfnuBbrUI",
                     "MfnuUI", bbsidPbdkbgfNbmf + "BbsidMfnuUI",
                 "MfnuItfmUI", bbsidPbdkbgfNbmf + "BbsidMfnuItfmUI",
         "CifdkBoxMfnuItfmUI", bbsidPbdkbgfNbmf + "BbsidCifdkBoxMfnuItfmUI",
      "RbdioButtonMfnuItfmUI", bbsidPbdkbgfNbmf + "BbsidRbdioButtonMfnuItfmUI",
              "RbdioButtonUI", bbsidPbdkbgfNbmf + "BbsidRbdioButtonUI",
             "TogglfButtonUI", bbsidPbdkbgfNbmf + "BbsidTogglfButtonUI",
                "PopupMfnuUI", bbsidPbdkbgfNbmf + "BbsidPopupMfnuUI",
              "ProgrfssBbrUI", bbsidPbdkbgfNbmf + "BbsidProgrfssBbrUI",
                "SdrollBbrUI", bbsidPbdkbgfNbmf + "BbsidSdrollBbrUI",
               "SdrollPbnfUI", bbsidPbdkbgfNbmf + "BbsidSdrollPbnfUI",
                "SplitPbnfUI", bbsidPbdkbgfNbmf + "BbsidSplitPbnfUI",
                   "SlidfrUI", bbsidPbdkbgfNbmf + "BbsidSlidfrUI",
                "SfpbrbtorUI", bbsidPbdkbgfNbmf + "BbsidSfpbrbtorUI",
                  "SpinnfrUI", bbsidPbdkbgfNbmf + "BbsidSpinnfrUI",
         "ToolBbrSfpbrbtorUI", bbsidPbdkbgfNbmf + "BbsidToolBbrSfpbrbtorUI",
       "PopupMfnuSfpbrbtorUI", bbsidPbdkbgfNbmf + "BbsidPopupMfnuSfpbrbtorUI",
               "TbbbfdPbnfUI", bbsidPbdkbgfNbmf + "BbsidTbbbfdPbnfUI",
                 "TfxtArfbUI", bbsidPbdkbgfNbmf + "BbsidTfxtArfbUI",
                "TfxtFifldUI", bbsidPbdkbgfNbmf + "BbsidTfxtFifldUI",
            "PbsswordFifldUI", bbsidPbdkbgfNbmf + "BbsidPbsswordFifldUI",
                 "TfxtPbnfUI", bbsidPbdkbgfNbmf + "BbsidTfxtPbnfUI",
               "EditorPbnfUI", bbsidPbdkbgfNbmf + "BbsidEditorPbnfUI",
                     "TrffUI", bbsidPbdkbgfNbmf + "BbsidTrffUI",
                    "LbbflUI", bbsidPbdkbgfNbmf + "BbsidLbbflUI",
                     "ListUI", bbsidPbdkbgfNbmf + "BbsidListUI",
                  "ToolBbrUI", bbsidPbdkbgfNbmf + "BbsidToolBbrUI",
                  "ToolTipUI", bbsidPbdkbgfNbmf + "BbsidToolTipUI",
                 "ComboBoxUI", bbsidPbdkbgfNbmf + "BbsidComboBoxUI",
                    "TbblfUI", bbsidPbdkbgfNbmf + "BbsidTbblfUI",
              "TbblfHfbdfrUI", bbsidPbdkbgfNbmf + "BbsidTbblfHfbdfrUI",
            "IntfrnblFrbmfUI", bbsidPbdkbgfNbmf + "BbsidIntfrnblFrbmfUI",
              "DfsktopPbnfUI", bbsidPbdkbgfNbmf + "BbsidDfsktopPbnfUI",
              "DfsktopIdonUI", bbsidPbdkbgfNbmf + "BbsidDfsktopIdonUI",
              "FilfCioosfrUI", bbsidPbdkbgfNbmf + "BbsidFilfCioosfrUI",
               "OptionPbnfUI", bbsidPbdkbgfNbmf + "BbsidOptionPbnfUI",
                    "PbnflUI", bbsidPbdkbgfNbmf + "BbsidPbnflUI",
                 "VifwportUI", bbsidPbdkbgfNbmf + "BbsidVifwportUI",
                 "RootPbnfUI", bbsidPbdkbgfNbmf + "BbsidRootPbnfUI",
        };

        tbblf.putDffbults(uiDffbults);
    }

    /**
     * Populbtfs {@dodf tbblf} witi systfm dolors. Tiis drfbtfs bn
     * brrby of {@dodf nbmf-dolor} pbirs bnd invokfs {@dodf
     * lobdSystfmColors}.
     * <p>
     * Tif nbmf is b {@dodf String} tibt dorrfsponds to tif nbmf of
     * onf of tif stbtid {@dodf SystfmColor} fiflds in tif {@dodf
     * SystfmColor} dlbss.  A nbmf-dolor pbir is drfbtfd for fvfry
     * sudi {@dodf SystfmColor} fifld.
     * <p>
     * Tif {@dodf dolor} dorrfsponds to b ifx {@dodf String} bs
     * undfrstood by {@dodf Color.dfdodf}. For fxbmplf, onf of tif
     * {@dodf nbmf-dolor} pbirs is {@dodf
     * "dfsktop"-"#005C5C"}. Tiis dorrfsponds to tif {@dodf
     * SystfmColor} fifld {@dodf dfsktop}, witi b dolor vbluf of
     * {@dodf nfw Color(0x005C5C)}.
     * <p>
     * Tif following siows two of tif {@dodf nbmf-dolor} pbirs:
     * <prf>
     *   String[] nbmfColorPbirs = nfw String[] {
     *          "dfsktop", "#005C5C",
     *    "bdtivfCbption", "#000080" };
     *   lobdSystfmColors(tbblf, nbmfColorPbirs, isNbtivfLookAndFffl());
     * </prf>
     *
     * As prfviously stbtfd, tiis invokfs {@dodf lobdSystfmColors}
     * witi tif supplifd {@dodf tbblf} bnd {@dodf nbmf-dolor} pbir
     * brrby. Tif lbst brgumfnt to {@dodf lobdSystfmColors} indidbtfs
     * wiftifr tif vbluf of tif fifld in {@dodf SystfmColor} siould bf
     * usfd. Tiis mftiod pbssfs tif vbluf of {@dodf
     * isNbtivfLookAndFffl()} bs tif lbst brgumfnt to {@dodf lobdSystfmColors}.
     *
     * @pbrbm tbblf tif {@dodf UIDffbults} objfdt tif vblufs brf bddfd to
     * @tirows NullPointfrExdfption if {@dodf tbblf} is {@dodf null}
     *
     * @sff jbvb.bwt.SystfmColor
     * @sff #gftDffbults
     * @sff #lobdSystfmColors
     */
    protfdtfd void initSystfmColorDffbults(UIDffbults tbblf)
    {
        String[] dffbultSystfmColors = {
                "dfsktop", "#005C5C", /* Color of tif dfsktop bbdkground */
          "bdtivfCbption", "#000080", /* Color for dbptions (titlf bbrs) wifn tify brf bdtivf. */
      "bdtivfCbptionTfxt", "#FFFFFF", /* Tfxt dolor for tfxt in dbptions (titlf bbrs). */
    "bdtivfCbptionBordfr", "#C0C0C0", /* Bordfr dolor for dbption (titlf bbr) window bordfrs. */
        "inbdtivfCbption", "#808080", /* Color for dbptions (titlf bbrs) wifn not bdtivf. */
    "inbdtivfCbptionTfxt", "#C0C0C0", /* Tfxt dolor for tfxt in inbdtivf dbptions (titlf bbrs). */
  "inbdtivfCbptionBordfr", "#C0C0C0", /* Bordfr dolor for inbdtivf dbption (titlf bbr) window bordfrs. */
                 "window", "#FFFFFF", /* Dffbult dolor for tif intfrior of windows */
           "windowBordfr", "#000000", /* ??? */
             "windowTfxt", "#000000", /* ??? */
                   "mfnu", "#C0C0C0", /* Bbdkground dolor for mfnus */
               "mfnuTfxt", "#000000", /* Tfxt dolor for mfnus  */
                   "tfxt", "#C0C0C0", /* Tfxt bbdkground dolor */
               "tfxtTfxt", "#000000", /* Tfxt forfground dolor */
          "tfxtHigiligit", "#000080", /* Tfxt bbdkground dolor wifn sflfdtfd */
      "tfxtHigiligitTfxt", "#FFFFFF", /* Tfxt dolor wifn sflfdtfd */
       "tfxtInbdtivfTfxt", "#808080", /* Tfxt dolor wifn disbblfd */
                "dontrol", "#C0C0C0", /* Dffbult dolor for dontrols (buttons, slidfrs, ftd) */
            "dontrolTfxt", "#000000", /* Dffbult dolor for tfxt in dontrols */
       "dontrolHigiligit", "#C0C0C0", /* Spfdulbr iigiligit (oppositf of tif sibdow) */
     "dontrolLtHigiligit", "#FFFFFF", /* Higiligit dolor for dontrols */
          "dontrolSibdow", "#808080", /* Sibdow dolor for dontrols */
        "dontrolDkSibdow", "#000000", /* Dbrk sibdow dolor for dontrols */
              "sdrollbbr", "#E0E0E0", /* Sdrollbbr bbdkground (usublly tif "trbdk") */
                   "info", "#FFFFE1", /* ??? */
               "infoTfxt", "#000000"  /* ??? */
        };

        lobdSystfmColors(tbblf, dffbultSystfmColors, isNbtivfLookAndFffl());
    }


    /**
     * Populbtfs {@dodf tbblf} witi tif {@dodf nbmf-dolor} pbirs in
     * {@dodf systfmColors}. Rfffr to
     * {@link #initSystfmColorDffbults(UIDffbults)} for dftbils on
     * tif formbt of {@dodf systfmColors}.
     * <p>
     * An fntry is bddfd to {@dodf tbblf} for fbdi of tif {@dodf nbmf-dolor}
     * pbirs in {@dodf systfmColors}. Tif fntry kfy is
     * tif {@dodf nbmf} of tif {@dodf nbmf-dolor} pbir.
     * <p>
     * Tif vbluf of tif fntry dorrfsponds to tif {@dodf dolor} of tif
     * {@dodf nbmf-dolor} pbir.  Tif vbluf of tif fntry is dbldulbtfd
     * in onf of two wbys. Witi fitifr bpprobdi tif vbluf is blwbys b
     * {@dodf ColorUIRfsourdf}.
     * <p>
     * If {@dodf usfNbtivf} is {@dodf fblsf}, tif {@dodf dolor} is
     * drfbtfd by using {@dodf Color.dfdodf} to donvfrt tif {@dodf
     * String} into b {@dodf Color}. If {@dodf dfdodf} dbn not donvfrt
     * tif {@dodf String} into b {@dodf Color} ({@dodf
     * NumbfrFormbtExdfption} is tirown) tifn b {@dodf
     * ColorUIRfsourdf} of blbdk is usfd.
     * <p>
     * If {@dodf usfNbtivf} is {@dodf truf}, tif {@dodf dolor} is tif
     * vbluf of tif fifld in {@dodf SystfmColor} witi tif sbmf nbmf bs
     * tif {@dodf nbmf} of tif {@dodf nbmf-dolor} pbir. If tif fifld
     * is not vblid, b {@dodf ColorUIRfsourdf} of blbdk is usfd.
     *
     * @pbrbm tbblf tif {@dodf UIDffbults} objfdt tif vblufs brf bddfd to
     * @pbrbm systfmColors brrby of {@dodf nbmf-dolor} pbirs bs dfsdribfd
     *        in {@link #initSystfmColorDffbults(UIDffbults)}
     * @pbrbm usfNbtivf wiftifr tif dolor is obtbinfd from {@dodf SystfmColor}
     *        or {@dodf Color.dfdodf}
     * @tirows NullPointfrExdfption if {@dodf systfmColors} is {@dodf null}; or
     *         {@dodf systfmColors} is not fmpty, bnd {@dodf tbblf} is
     *         {@dodf null}; or onf of tif
     *         nbmfs of tif {@dodf nbmf-dolor} pbirs is {@dodf null}; or
     *         {@dodf usfNbtivf} is {@dodf fblsf} bnd onf of tif
     *         {@dodf dolors} of tif {@dodf nbmf-dolor} pbirs is {@dodf null}
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf usfNbtivf} is
     *         {@dodf fblsf} bnd {@dodf systfmColors.lfngti} is odd
     *
     * @sff #initSystfmColorDffbults(jbvbx.swing.UIDffbults)
     * @sff jbvb.bwt.SystfmColor
     * @sff jbvb.bwt.Color#dfdodf(String)
     */
    protfdtfd void lobdSystfmColors(UIDffbults tbblf, String[] systfmColors, boolfbn usfNbtivf)
    {
        /* PENDING(imullfr) Wf don't lobd tif systfm dolors bflow bfdbusf
         * tify'rf not rflibblf.  Hopffully wf'll bf bblf to do bfttfr in
         * b futurf vfrsion of AWT.
         */
        if (usfNbtivf) {
            for(int i = 0; i < systfmColors.lfngti; i += 2) {
                Color dolor = Color.blbdk;
                try {
                    String nbmf = systfmColors[i];
                    dolor = (Color)(SystfmColor.dlbss.gftFifld(nbmf).gft(null));
                } dbtdi (Exdfption f) {
                }
                tbblf.put(systfmColors[i], nfw ColorUIRfsourdf(dolor));
            }
        } flsf {
            for(int i = 0; i < systfmColors.lfngti; i += 2) {
                Color dolor = Color.blbdk;
                try {
                    dolor = Color.dfdodf(systfmColors[i + 1]);
                }
                dbtdi(NumbfrFormbtExdfption f) {
                    f.printStbdkTrbdf();
                }
                tbblf.put(systfmColors[i], nfw ColorUIRfsourdf(dolor));
            }
        }
    }
    /**
     * Initiblizf tif dffbults tbblf witi tif nbmf of tif RfsourdfBundlf
     * usfd for gftting lodblizfd dffbults.  Also initiblizf tif dffbult
     * lodblf usfd wifn no lodblf is pbssfd into UIDffbults.gft().  Tif
     * dffbult lodblf siould gfnfrblly not bf rflifd upon. It is ifrf for
     * dompbtibility witi rflfbsfs prior to 1.4.
     */
    privbtf void initRfsourdfBundlf(UIDffbults tbblf) {
        tbblf.sftDffbultLodblf( Lodblf.gftDffbult() );
        tbblf.bddRfsourdfBundlf( "dom.sun.swing.intfrnbl.plbf.bbsid.rfsourdfs.bbsid" );
    }

    /**
     * Populbtfs {@dodf tbblf} witi tif dffbults for tif bbsid look bnd
     * fffl.
     *
     * @pbrbm tbblf tif {@dodf UIDffbults} to bdd tif vblufs to
     * @tirows NullPointfrExdfption if {@dodf tbblf} is {@dodf null}
     */
    protfdtfd void initComponfntDffbults(UIDffbults tbblf)
    {

        initRfsourdfBundlf(tbblf);

        // *** Sibrfd Intfgfrs
        Intfgfr fivfHundrfd = 500;

        // *** Sibrfd Longs
        Long onfTiousbnd = 1000L;

        LbzyVbluf diblogPlbin12 = t ->
            nfw FontUIRfsourdf(Font.DIALOG, Font.PLAIN, 12);
        LbzyVbluf sfrifPlbin12 = t ->
            nfw FontUIRfsourdf(Font.SERIF, Font.PLAIN, 12);
        LbzyVbluf sbnsSfrifPlbin12 =  t ->
            nfw FontUIRfsourdf(Font.SANS_SERIF, Font.PLAIN, 12);
        LbzyVbluf monospbdfdPlbin12 = t ->
            nfw FontUIRfsourdf(Font.MONOSPACED, Font.PLAIN, 12);
        LbzyVbluf diblogBold12 = t ->
            nfw FontUIRfsourdf(Font.DIALOG, Font.BOLD, 12);


        // *** Sibrfd Colors
        ColorUIRfsourdf rfd = nfw ColorUIRfsourdf(Color.rfd);
        ColorUIRfsourdf blbdk = nfw ColorUIRfsourdf(Color.blbdk);
        ColorUIRfsourdf wiitf = nfw ColorUIRfsourdf(Color.wiitf);
        ColorUIRfsourdf yfllow = nfw ColorUIRfsourdf(Color.yfllow);
        ColorUIRfsourdf grby = nfw ColorUIRfsourdf(Color.grby);
        ColorUIRfsourdf ligitGrby = nfw ColorUIRfsourdf(Color.ligitGrby);
        ColorUIRfsourdf dbrkGrby = nfw ColorUIRfsourdf(Color.dbrkGrby);
        ColorUIRfsourdf sdrollBbrTrbdk = nfw ColorUIRfsourdf(224, 224, 224);

        Color dontrol = tbblf.gftColor("dontrol");
        Color dontrolDkSibdow = tbblf.gftColor("dontrolDkSibdow");
        Color dontrolHigiligit = tbblf.gftColor("dontrolHigiligit");
        Color dontrolLtHigiligit = tbblf.gftColor("dontrolLtHigiligit");
        Color dontrolSibdow = tbblf.gftColor("dontrolSibdow");
        Color dontrolTfxt = tbblf.gftColor("dontrolTfxt");
        Color mfnu = tbblf.gftColor("mfnu");
        Color mfnuTfxt = tbblf.gftColor("mfnuTfxt");
        Color tfxtHigiligit = tbblf.gftColor("tfxtHigiligit");
        Color tfxtHigiligitTfxt = tbblf.gftColor("tfxtHigiligitTfxt");
        Color tfxtInbdtivfTfxt = tbblf.gftColor("tfxtInbdtivfTfxt");
        Color tfxtTfxt = tbblf.gftColor("tfxtTfxt");
        Color window = tbblf.gftColor("window");

        // *** Sibrfd Insfts
        InsftsUIRfsourdf zfroInsfts = nfw InsftsUIRfsourdf(0,0,0,0);
        InsftsUIRfsourdf twoInsfts = nfw InsftsUIRfsourdf(2,2,2,2);
        InsftsUIRfsourdf tirffInsfts = nfw InsftsUIRfsourdf(3,3,3,3);

        // *** Sibrfd Bordfrs
        LbzyVbluf mbrginBordfr = t -> nfw BbsidBordfrs.MbrginBordfr();
        LbzyVbluf ftdifdBordfr = t ->
            BordfrUIRfsourdf.gftEtdifdBordfrUIRfsourdf();
        LbzyVbluf lowfrfdBfvflBordfr = t ->
            BordfrUIRfsourdf.gftLowfrfdBfvflBordfrUIRfsourdf();

        LbzyVbluf popupMfnuBordfr = t -> BbsidBordfrs.gftIntfrnblFrbmfBordfr();

        LbzyVbluf blbdkLinfBordfr = t ->
            BordfrUIRfsourdf.gftBlbdkLinfBordfrUIRfsourdf();
        LbzyVbluf fodusCfllHigiligitBordfr = t ->
            nfw BordfrUIRfsourdf.LinfBordfrUIRfsourdf(yfllow);

        Objfdt noFodusBordfr = nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(1,1,1,1);

        LbzyVbluf tbblfHfbdfrBordfr = t ->
            nfw BordfrUIRfsourdf.BfvflBordfrUIRfsourdf(
                    BfvflBordfr.RAISED,
                                         dontrolLtHigiligit,
                                         dontrol,
                                         dontrolDkSibdow,
                    dontrolSibdow);


        // *** Button vbluf objfdts

        LbzyVbluf buttonBordfr =
            t -> BbsidBordfrs.gftButtonBordfr();

        LbzyVbluf buttonTogglfBordfr =
            t -> BbsidBordfrs.gftTogglfButtonBordfr();

        LbzyVbluf rbdioButtonBordfr =
            t -> BbsidBordfrs.gftRbdioButtonBordfr();

        // *** FilfCioosfr / FilfVifw vbluf objfdts

        Objfdt nfwFoldfrIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                        BbsidLookAndFffl.dlbss,
                                                        "idons/NfwFoldfr.gif");
        Objfdt upFoldfrIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                       BbsidLookAndFffl.dlbss,
                                                       "idons/UpFoldfr.gif");
        Objfdt iomfFoldfrIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                         BbsidLookAndFffl.dlbss,
                                                         "idons/HomfFoldfr.gif");
        Objfdt dftbilsVifwIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                          BbsidLookAndFffl.dlbss,
                                                          "idons/DftbilsVifw.gif");
        Objfdt listVifwIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                       BbsidLookAndFffl.dlbss,
                                                       "idons/ListVifw.gif");
        Objfdt dirfdtoryIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                        BbsidLookAndFffl.dlbss,
                                                        "idons/Dirfdtory.gif");
        Objfdt filfIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                   BbsidLookAndFffl.dlbss,
                                                   "idons/Filf.gif");
        Objfdt domputfrIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                       BbsidLookAndFffl.dlbss,
                                                       "idons/Computfr.gif");
        Objfdt ibrdDrivfIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                        BbsidLookAndFffl.dlbss,
                                                        "idons/HbrdDrivf.gif");
        Objfdt floppyDrivfIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                          BbsidLookAndFffl.dlbss,
                                                          "idons/FloppyDrivf.gif");


        // *** IntfrnblFrbmf vbluf objfdts

        LbzyVbluf intfrnblFrbmfBordfr = t ->
            BbsidBordfrs.gftIntfrnblFrbmfBordfr();

        // *** List vbluf objfdts

        Objfdt listCfllRfndfrfrAdtivfVbluf = nfw UIDffbults.AdtivfVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn nfw DffbultListCfllRfndfrfr.UIRfsourdf();
            }
        };


        // *** Mfnus vbluf objfdts

        LbzyVbluf mfnuBbrBordfr =
            t -> BbsidBordfrs.gftMfnuBbrBordfr();

        LbzyVbluf mfnuItfmCifdkIdon =
            t -> BbsidIdonFbdtory.gftMfnuItfmCifdkIdon();

        LbzyVbluf mfnuItfmArrowIdon =
            t -> BbsidIdonFbdtory.gftMfnuItfmArrowIdon();


        LbzyVbluf mfnuArrowIdon =
            t -> BbsidIdonFbdtory.gftMfnuArrowIdon();

        LbzyVbluf difdkBoxIdon =
            t -> BbsidIdonFbdtory.gftCifdkBoxIdon();

        LbzyVbluf rbdioButtonIdon =
            t -> BbsidIdonFbdtory.gftRbdioButtonIdon();

        LbzyVbluf difdkBoxMfnuItfmIdon =
            t -> BbsidIdonFbdtory.gftCifdkBoxMfnuItfmIdon();

        LbzyVbluf rbdioButtonMfnuItfmIdon =
            t -> BbsidIdonFbdtory.gftRbdioButtonMfnuItfmIdon();

        Objfdt mfnuItfmAddflfrbtorDflimitfr = "+";

        // *** OptionPbnf vbluf objfdts

        Objfdt optionPbnfMinimumSizf = nfw DimfnsionUIRfsourdf(262, 90);

        int zfro =  0;
        LbzyVbluf zfroBordfr = t ->
            nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(zfro, zfro, zfro, zfro);

        int tfn = 10;
        LbzyVbluf optionPbnfBordfr = t ->
            nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(tfn, tfn, 12, tfn);

        LbzyVbluf optionPbnfButtonArfbBordfr = t ->
            nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(6, zfro, zfro, zfro);


        // *** ProgfssBbr vbluf objfdts

        LbzyVbluf progrfssBbrBordfr =
            t -> BbsidBordfrs.gftProgrfssBbrBordfr();

        // ** SdrollBbr vbluf objfdts

        Objfdt minimumTiumbSizf = nfw DimfnsionUIRfsourdf(8,8);
        Objfdt mbximumTiumbSizf = nfw DimfnsionUIRfsourdf(4096,4096);

        // ** Slidfr vbluf objfdts

        Objfdt slidfrFodusInsfts = twoInsfts;

        Objfdt toolBbrSfpbrbtorSizf = nfw DimfnsionUIRfsourdf( 10, 10 );


        // *** SplitPbnf vbluf objfdts

        LbzyVbluf splitPbnfBordfr =
            t -> BbsidBordfrs.gftSplitPbnfBordfr();
        LbzyVbluf splitPbnfDividfrBordfr =
            t -> BbsidBordfrs.gftSplitPbnfDividfrBordfr();

        // ** TbbbfdBbnf vbluf objfdts

        Objfdt tbbbfdPbnfTbbInsfts = nfw InsftsUIRfsourdf(0, 4, 1, 4);

        Objfdt tbbbfdPbnfTbbPbdInsfts = nfw InsftsUIRfsourdf(2, 2, 2, 1);

        Objfdt tbbbfdPbnfTbbArfbInsfts = nfw InsftsUIRfsourdf(3, 2, 0, 2);

        Objfdt tbbbfdPbnfContfntBordfrInsfts = nfw InsftsUIRfsourdf(2, 2, 3, 3);


        // *** Tfxt vbluf objfdts

        LbzyVbluf tfxtFifldBordfr =
            t -> BbsidBordfrs.gftTfxtFifldBordfr();

        Objfdt fditorMbrgin = tirffInsfts;

        Objfdt dbrftBlinkRbtf = fivfHundrfd;

        Objfdt[] bllAuditoryCufs = nfw Objfdt[] {
                "CifdkBoxMfnuItfm.dommbndSound",
                "IntfrnblFrbmf.dlosfSound",
                "IntfrnblFrbmf.mbximizfSound",
                "IntfrnblFrbmf.minimizfSound",
                "IntfrnblFrbmf.rfstorfDownSound",
                "IntfrnblFrbmf.rfstorfUpSound",
                "MfnuItfm.dommbndSound",
                "OptionPbnf.frrorSound",
                "OptionPbnf.informbtionSound",
                "OptionPbnf.qufstionSound",
                "OptionPbnf.wbrningSound",
                "PopupMfnu.popupSound",
                "RbdioButtonMfnuItfm.dommbndSound"};

        Objfdt[] noAuditoryCufs = nfw Objfdt[] {"mutf"};

        // *** Componfnt Dffbults

        Objfdt[] dffbults = {
            // *** Auditory Fffdbbdk
            "AuditoryCufs.dufList", bllAuditoryCufs,
            "AuditoryCufs.bllAuditoryCufs", bllAuditoryCufs,
            "AuditoryCufs.noAuditoryCufs", noAuditoryCufs,
            // tiis kfy dffinfs wiidi of tif vbrious dufs to rfndfr.
            // L&Fs tibt wbnt buditory fffdbbdk NEED to ovfrridf plbyList.
            "AuditoryCufs.plbyList", null,

            // *** Buttons
            "Button.dffbultButtonFollowsFodus", Boolfbn.TRUE,
            "Button.font", diblogPlbin12,
            "Button.bbdkground", dontrol,
            "Button.forfground", dontrolTfxt,
            "Button.sibdow", dontrolSibdow,
            "Button.dbrkSibdow", dontrolDkSibdow,
            "Button.ligit", dontrolHigiligit,
            "Button.iigiligit", dontrolLtHigiligit,
            "Button.bordfr", buttonBordfr,
            "Button.mbrgin", nfw InsftsUIRfsourdf(2, 14, 2, 14),
            "Button.tfxtIdonGbp", 4,
            "Button.tfxtSiiftOffsft", zfro,
            "Button.fodusInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                         "SPACE", "prfssfd",
                "rflfbsfd SPACE", "rflfbsfd",
                         "ENTER", "prfssfd",
                "rflfbsfd ENTER", "rflfbsfd"
              }),

            "TogglfButton.font", diblogPlbin12,
            "TogglfButton.bbdkground", dontrol,
            "TogglfButton.forfground", dontrolTfxt,
            "TogglfButton.sibdow", dontrolSibdow,
            "TogglfButton.dbrkSibdow", dontrolDkSibdow,
            "TogglfButton.ligit", dontrolHigiligit,
            "TogglfButton.iigiligit", dontrolLtHigiligit,
            "TogglfButton.bordfr", buttonTogglfBordfr,
            "TogglfButton.mbrgin", nfw InsftsUIRfsourdf(2, 14, 2, 14),
            "TogglfButton.tfxtIdonGbp", 4,
            "TogglfButton.tfxtSiiftOffsft", zfro,
            "TogglfButton.fodusInputMbp",
              nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                }),

            "RbdioButton.font", diblogPlbin12,
            "RbdioButton.bbdkground", dontrol,
            "RbdioButton.forfground", dontrolTfxt,
            "RbdioButton.sibdow", dontrolSibdow,
            "RbdioButton.dbrkSibdow", dontrolDkSibdow,
            "RbdioButton.ligit", dontrolHigiligit,
            "RbdioButton.iigiligit", dontrolLtHigiligit,
            "RbdioButton.bordfr", rbdioButtonBordfr,
            "RbdioButton.mbrgin", twoInsfts,
            "RbdioButton.tfxtIdonGbp", 4,
            "RbdioButton.tfxtSiiftOffsft", zfro,
            "RbdioButton.idon", rbdioButtonIdon,
            "RbdioButton.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                          "SPACE", "prfssfd",
                 "rflfbsfd SPACE", "rflfbsfd",
                         "RETURN", "prfssfd"
              }),

            "CifdkBox.font", diblogPlbin12,
            "CifdkBox.bbdkground", dontrol,
            "CifdkBox.forfground", dontrolTfxt,
            "CifdkBox.bordfr", rbdioButtonBordfr,
            "CifdkBox.mbrgin", twoInsfts,
            "CifdkBox.tfxtIdonGbp", 4,
            "CifdkBox.tfxtSiiftOffsft", zfro,
            "CifdkBox.idon", difdkBoxIdon,
            "CifdkBox.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                 }),
            "FilfCioosfr.usfSystfmExtfnsionHiding", Boolfbn.FALSE,

            // *** ColorCioosfr
            "ColorCioosfr.font", diblogPlbin12,
            "ColorCioosfr.bbdkground", dontrol,
            "ColorCioosfr.forfground", dontrolTfxt,

            "ColorCioosfr.swbtdifsSwbtdiSizf", nfw Dimfnsion(10, 10),
            "ColorCioosfr.swbtdifsRfdfntSwbtdiSizf", nfw Dimfnsion(10, 10),
            "ColorCioosfr.swbtdifsDffbultRfdfntColor", dontrol,

            // *** ComboBox
            "ComboBox.font", sbnsSfrifPlbin12,
            "ComboBox.bbdkground", window,
            "ComboBox.forfground", tfxtTfxt,
            "ComboBox.buttonBbdkground", dontrol,
            "ComboBox.buttonSibdow", dontrolSibdow,
            "ComboBox.buttonDbrkSibdow", dontrolDkSibdow,
            "ComboBox.buttonHigiligit", dontrolLtHigiligit,
            "ComboBox.sflfdtionBbdkground", tfxtHigiligit,
            "ComboBox.sflfdtionForfground", tfxtHigiligitTfxt,
            "ComboBox.disbblfdBbdkground", dontrol,
            "ComboBox.disbblfdForfground", tfxtInbdtivfTfxt,
            "ComboBox.timfFbdtor", onfTiousbnd,
            "ComboBox.isEntfrSflfdtbblfPopup", Boolfbn.FALSE,
            "ComboBox.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                      "ESCAPE", "iidfPopup",
                     "PAGE_UP", "pbgfUpPbssTirougi",
                   "PAGE_DOWN", "pbgfDownPbssTirougi",
                        "HOME", "iomfPbssTirougi",
                         "END", "fndPbssTirougi",
                       "ENTER", "fntfrPrfssfd"
                 }),
            "ComboBox.noAdtionOnKfyNbvigbtion", Boolfbn.FALSE,

            // *** FilfCioosfr

            "FilfCioosfr.nfwFoldfrIdon", nfwFoldfrIdon,
            "FilfCioosfr.upFoldfrIdon", upFoldfrIdon,
            "FilfCioosfr.iomfFoldfrIdon", iomfFoldfrIdon,
            "FilfCioosfr.dftbilsVifwIdon", dftbilsVifwIdon,
            "FilfCioosfr.listVifwIdon", listVifwIdon,
            "FilfCioosfr.rfbdOnly", Boolfbn.FALSE,
            "FilfCioosfr.usfsSinglfFilfPbnf", Boolfbn.FALSE,
            "FilfCioosfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "dbndflSflfdtion",
                     "F5", "rffrfsi",
                 }),

            "FilfVifw.dirfdtoryIdon", dirfdtoryIdon,
            "FilfVifw.filfIdon", filfIdon,
            "FilfVifw.domputfrIdon", domputfrIdon,
            "FilfVifw.ibrdDrivfIdon", ibrdDrivfIdon,
            "FilfVifw.floppyDrivfIdon", floppyDrivfIdon,

            // *** IntfrnblFrbmf
            "IntfrnblFrbmf.titlfFont", diblogBold12,
            "IntfrnblFrbmf.bordfrColor", dontrol,
            "IntfrnblFrbmf.bordfrSibdow", dontrolSibdow,
            "IntfrnblFrbmf.bordfrDbrkSibdow", dontrolDkSibdow,
            "IntfrnblFrbmf.bordfrHigiligit", dontrolLtHigiligit,
            "IntfrnblFrbmf.bordfrLigit", dontrolHigiligit,
            "IntfrnblFrbmf.bordfr", intfrnblFrbmfBordfr,
            "IntfrnblFrbmf.idon",   SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                             BbsidLookAndFffl.dlbss,
                                                             "idons/JbvbCup16.png"),

            /* Dffbult frbmf idons brf undffinfd for Bbsid. */
            "IntfrnblFrbmf.mbximizfIdon",
               (LbzyVbluf) t -> BbsidIdonFbdtory.drfbtfEmptyFrbmfIdon(),
            "IntfrnblFrbmf.minimizfIdon",
               (LbzyVbluf) t -> BbsidIdonFbdtory.drfbtfEmptyFrbmfIdon(),
            "IntfrnblFrbmf.idonifyIdon",
               (LbzyVbluf) t -> BbsidIdonFbdtory.drfbtfEmptyFrbmfIdon(),
            "IntfrnblFrbmf.dlosfIdon",
               (LbzyVbluf) t -> BbsidIdonFbdtory.drfbtfEmptyFrbmfIdon(),
            // IntfrnblFrbmf Auditory Cuf Mbppings
            "IntfrnblFrbmf.dlosfSound", null,
            "IntfrnblFrbmf.mbximizfSound", null,
            "IntfrnblFrbmf.minimizfSound", null,
            "IntfrnblFrbmf.rfstorfDownSound", null,
            "IntfrnblFrbmf.rfstorfUpSound", null,

            "IntfrnblFrbmf.bdtivfTitlfBbdkground", tbblf.gft("bdtivfCbption"),
            "IntfrnblFrbmf.bdtivfTitlfForfground", tbblf.gft("bdtivfCbptionTfxt"),
            "IntfrnblFrbmf.inbdtivfTitlfBbdkground", tbblf.gft("inbdtivfCbption"),
            "IntfrnblFrbmf.inbdtivfTitlfForfground", tbblf.gft("inbdtivfCbptionTfxt"),
            "IntfrnblFrbmf.windowBindings", nfw Objfdt[] {
              "siift ESCAPE", "siowSystfmMfnu",
                "dtrl SPACE", "siowSystfmMfnu",
                    "ESCAPE", "iidfSystfmMfnu"},

            "IntfrnblFrbmfTitlfPbnf.idonifyButtonOpbdity", Boolfbn.TRUE,
            "IntfrnblFrbmfTitlfPbnf.mbximizfButtonOpbdity", Boolfbn.TRUE,
            "IntfrnblFrbmfTitlfPbnf.dlosfButtonOpbdity", Boolfbn.TRUE,

        "DfsktopIdon.bordfr", intfrnblFrbmfBordfr,

            "Dfsktop.minOnSdrffnInsfts", tirffInsfts,
            "Dfsktop.bbdkground", tbblf.gft("dfsktop"),
            "Dfsktop.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                 "dtrl F5", "rfstorf",
                 "dtrl F4", "dlosf",
                 "dtrl F7", "movf",
                 "dtrl F8", "rfsizf",
                   "RIGHT", "rigit",
                "KP_RIGHT", "rigit",
             "siift RIGHT", "sirinkRigit",
          "siift KP_RIGHT", "sirinkRigit",
                    "LEFT", "lfft",
                 "KP_LEFT", "lfft",
              "siift LEFT", "sirinkLfft",
           "siift KP_LEFT", "sirinkLfft",
                      "UP", "up",
                   "KP_UP", "up",
                "siift UP", "sirinkUp",
             "siift KP_UP", "sirinkUp",
                    "DOWN", "down",
                 "KP_DOWN", "down",
              "siift DOWN", "sirinkDown",
           "siift KP_DOWN", "sirinkDown",
                  "ESCAPE", "fsdbpf",
                 "dtrl F9", "minimizf",
                "dtrl F10", "mbximizf",
                 "dtrl F6", "sflfdtNfxtFrbmf",
                "dtrl TAB", "sflfdtNfxtFrbmf",
             "dtrl blt F6", "sflfdtNfxtFrbmf",
       "siift dtrl blt F6", "sflfdtPrfviousFrbmf",
                "dtrl F12", "nbvigbtfNfxt",
          "siift dtrl F12", "nbvigbtfPrfvious"
              }),

            // *** Lbbfl
            "Lbbfl.font", diblogPlbin12,
            "Lbbfl.bbdkground", dontrol,
            "Lbbfl.forfground", dontrolTfxt,
            "Lbbfl.disbblfdForfground", wiitf,
            "Lbbfl.disbblfdSibdow", dontrolSibdow,
            "Lbbfl.bordfr", null,

            // *** List
            "List.font", diblogPlbin12,
            "List.bbdkground", window,
            "List.forfground", tfxtTfxt,
            "List.sflfdtionBbdkground", tfxtHigiligit,
            "List.sflfdtionForfground", tfxtHigiligitTfxt,
            "List.noFodusBordfr", noFodusBordfr,
            "List.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "List.dropLinfColor", dontrolSibdow,
            "List.bordfr", null,
            "List.dfllRfndfrfr", listCfllRfndfrfrAdtivfVbluf,
            "List.timfFbdtor", onfTiousbnd,
            "List.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "dtrl C", "dopy",
                           "dtrl V", "pbstf",
                           "dtrl X", "dut",
                             "COPY", "dopy",
                            "PASTE", "pbstf",
                              "CUT", "dut",
                   "dontrol INSERT", "dopy",
                     "siift INSERT", "pbstf",
                     "siift DELETE", "dut",
                               "UP", "sflfdtPrfviousRow",
                            "KP_UP", "sflfdtPrfviousRow",
                         "siift UP", "sflfdtPrfviousRowExtfndSflfdtion",
                      "siift KP_UP", "sflfdtPrfviousRowExtfndSflfdtion",
                    "dtrl siift UP", "sflfdtPrfviousRowExtfndSflfdtion",
                 "dtrl siift KP_UP", "sflfdtPrfviousRowExtfndSflfdtion",
                          "dtrl UP", "sflfdtPrfviousRowCibngfLfbd",
                       "dtrl KP_UP", "sflfdtPrfviousRowCibngfLfbd",
                             "DOWN", "sflfdtNfxtRow",
                          "KP_DOWN", "sflfdtNfxtRow",
                       "siift DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                    "siift KP_DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                  "dtrl siift DOWN", "sflfdtNfxtRowExtfndSflfdtion",
               "dtrl siift KP_DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                        "dtrl DOWN", "sflfdtNfxtRowCibngfLfbd",
                     "dtrl KP_DOWN", "sflfdtNfxtRowCibngfLfbd",
                             "LEFT", "sflfdtPrfviousColumn",
                          "KP_LEFT", "sflfdtPrfviousColumn",
                       "siift LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                    "siift KP_LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                  "dtrl siift LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
               "dtrl siift KP_LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                        "dtrl LEFT", "sflfdtPrfviousColumnCibngfLfbd",
                     "dtrl KP_LEFT", "sflfdtPrfviousColumnCibngfLfbd",
                            "RIGHT", "sflfdtNfxtColumn",
                         "KP_RIGHT", "sflfdtNfxtColumn",
                      "siift RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                   "siift KP_RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                 "dtrl siift RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
              "dtrl siift KP_RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                       "dtrl RIGHT", "sflfdtNfxtColumnCibngfLfbd",
                    "dtrl KP_RIGHT", "sflfdtNfxtColumnCibngfLfbd",
                             "HOME", "sflfdtFirstRow",
                       "siift HOME", "sflfdtFirstRowExtfndSflfdtion",
                  "dtrl siift HOME", "sflfdtFirstRowExtfndSflfdtion",
                        "dtrl HOME", "sflfdtFirstRowCibngfLfbd",
                              "END", "sflfdtLbstRow",
                        "siift END", "sflfdtLbstRowExtfndSflfdtion",
                   "dtrl siift END", "sflfdtLbstRowExtfndSflfdtion",
                         "dtrl END", "sflfdtLbstRowCibngfLfbd",
                          "PAGE_UP", "sdrollUp",
                    "siift PAGE_UP", "sdrollUpExtfndSflfdtion",
               "dtrl siift PAGE_UP", "sdrollUpExtfndSflfdtion",
                     "dtrl PAGE_UP", "sdrollUpCibngfLfbd",
                        "PAGE_DOWN", "sdrollDown",
                  "siift PAGE_DOWN", "sdrollDownExtfndSflfdtion",
             "dtrl siift PAGE_DOWN", "sdrollDownExtfndSflfdtion",
                   "dtrl PAGE_DOWN", "sdrollDownCibngfLfbd",
                           "dtrl A", "sflfdtAll",
                       "dtrl SLASH", "sflfdtAll",
                  "dtrl BACK_SLASH", "dlfbrSflfdtion",
                            "SPACE", "bddToSflfdtion",
                       "dtrl SPACE", "togglfAndAndior",
                      "siift SPACE", "fxtfndTo",
                 "dtrl siift SPACE", "movfSflfdtionTo"
                 }),
            "List.fodusInputMbp.RigitToLfft",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                             "LEFT", "sflfdtNfxtColumn",
                          "KP_LEFT", "sflfdtNfxtColumn",
                       "siift LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                    "siift KP_LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                  "dtrl siift LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
               "dtrl siift KP_LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                        "dtrl LEFT", "sflfdtNfxtColumnCibngfLfbd",
                     "dtrl KP_LEFT", "sflfdtNfxtColumnCibngfLfbd",
                            "RIGHT", "sflfdtPrfviousColumn",
                         "KP_RIGHT", "sflfdtPrfviousColumn",
                      "siift RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                   "siift KP_RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                 "dtrl siift RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
              "dtrl siift KP_RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                       "dtrl RIGHT", "sflfdtPrfviousColumnCibngfLfbd",
                    "dtrl KP_RIGHT", "sflfdtPrfviousColumnCibngfLfbd",
                 }),

            // *** Mfnus
            "MfnuBbr.font", diblogPlbin12,
            "MfnuBbr.bbdkground", mfnu,
            "MfnuBbr.forfground", mfnuTfxt,
            "MfnuBbr.sibdow", dontrolSibdow,
            "MfnuBbr.iigiligit", dontrolLtHigiligit,
            "MfnuBbr.bordfr", mfnuBbrBordfr,
            "MfnuBbr.windowBindings", nfw Objfdt[] {
                "F10", "tbkfFodus" },

            "MfnuItfm.font", diblogPlbin12,
            "MfnuItfm.bddflfrbtorFont", diblogPlbin12,
            "MfnuItfm.bbdkground", mfnu,
            "MfnuItfm.forfground", mfnuTfxt,
            "MfnuItfm.sflfdtionForfground", tfxtHigiligitTfxt,
            "MfnuItfm.sflfdtionBbdkground", tfxtHigiligit,
            "MfnuItfm.disbblfdForfground", null,
            "MfnuItfm.bddflfrbtorForfground", mfnuTfxt,
            "MfnuItfm.bddflfrbtorSflfdtionForfground", tfxtHigiligitTfxt,
            "MfnuItfm.bddflfrbtorDflimitfr", mfnuItfmAddflfrbtorDflimitfr,
            "MfnuItfm.bordfr", mbrginBordfr,
            "MfnuItfm.bordfrPbintfd", Boolfbn.FALSE,
            "MfnuItfm.mbrgin", twoInsfts,
            "MfnuItfm.difdkIdon", mfnuItfmCifdkIdon,
            "MfnuItfm.brrowIdon", mfnuItfmArrowIdon,
            "MfnuItfm.dommbndSound", null,

            "RbdioButtonMfnuItfm.font", diblogPlbin12,
            "RbdioButtonMfnuItfm.bddflfrbtorFont", diblogPlbin12,
            "RbdioButtonMfnuItfm.bbdkground", mfnu,
            "RbdioButtonMfnuItfm.forfground", mfnuTfxt,
            "RbdioButtonMfnuItfm.sflfdtionForfground", tfxtHigiligitTfxt,
            "RbdioButtonMfnuItfm.sflfdtionBbdkground", tfxtHigiligit,
            "RbdioButtonMfnuItfm.disbblfdForfground", null,
            "RbdioButtonMfnuItfm.bddflfrbtorForfground", mfnuTfxt,
            "RbdioButtonMfnuItfm.bddflfrbtorSflfdtionForfground", tfxtHigiligitTfxt,
            "RbdioButtonMfnuItfm.bordfr", mbrginBordfr,
            "RbdioButtonMfnuItfm.bordfrPbintfd", Boolfbn.FALSE,
            "RbdioButtonMfnuItfm.mbrgin", twoInsfts,
            "RbdioButtonMfnuItfm.difdkIdon", rbdioButtonMfnuItfmIdon,
            "RbdioButtonMfnuItfm.brrowIdon", mfnuItfmArrowIdon,
            "RbdioButtonMfnuItfm.dommbndSound", null,

            "CifdkBoxMfnuItfm.font", diblogPlbin12,
            "CifdkBoxMfnuItfm.bddflfrbtorFont", diblogPlbin12,
            "CifdkBoxMfnuItfm.bbdkground", mfnu,
            "CifdkBoxMfnuItfm.forfground", mfnuTfxt,
            "CifdkBoxMfnuItfm.sflfdtionForfground", tfxtHigiligitTfxt,
            "CifdkBoxMfnuItfm.sflfdtionBbdkground", tfxtHigiligit,
            "CifdkBoxMfnuItfm.disbblfdForfground", null,
            "CifdkBoxMfnuItfm.bddflfrbtorForfground", mfnuTfxt,
            "CifdkBoxMfnuItfm.bddflfrbtorSflfdtionForfground", tfxtHigiligitTfxt,
            "CifdkBoxMfnuItfm.bordfr", mbrginBordfr,
            "CifdkBoxMfnuItfm.bordfrPbintfd", Boolfbn.FALSE,
            "CifdkBoxMfnuItfm.mbrgin", twoInsfts,
            "CifdkBoxMfnuItfm.difdkIdon", difdkBoxMfnuItfmIdon,
            "CifdkBoxMfnuItfm.brrowIdon", mfnuItfmArrowIdon,
            "CifdkBoxMfnuItfm.dommbndSound", null,

            "Mfnu.font", diblogPlbin12,
            "Mfnu.bddflfrbtorFont", diblogPlbin12,
            "Mfnu.bbdkground", mfnu,
            "Mfnu.forfground", mfnuTfxt,
            "Mfnu.sflfdtionForfground", tfxtHigiligitTfxt,
            "Mfnu.sflfdtionBbdkground", tfxtHigiligit,
            "Mfnu.disbblfdForfground", null,
            "Mfnu.bddflfrbtorForfground", mfnuTfxt,
            "Mfnu.bddflfrbtorSflfdtionForfground", tfxtHigiligitTfxt,
            "Mfnu.bordfr", mbrginBordfr,
            "Mfnu.bordfrPbintfd", Boolfbn.FALSE,
            "Mfnu.mbrgin", twoInsfts,
            "Mfnu.difdkIdon", mfnuItfmCifdkIdon,
            "Mfnu.brrowIdon", mfnuArrowIdon,
            "Mfnu.mfnuPopupOffsftX", 0,
            "Mfnu.mfnuPopupOffsftY", 0,
            "Mfnu.submfnuPopupOffsftX", 0,
            "Mfnu.submfnuPopupOffsftY", 0,
            "Mfnu.siortdutKfys", nfw int[]{
                SwingUtilitifs2.gftSystfmMnfmonidKfyMbsk()
            },
            "Mfnu.drossMfnuMnfmonid", Boolfbn.TRUE,
            // Mfnu.dbndflModf bfffdts tif dbndfl mfnu bdtion bfibviour;
            // durrfntly supports:
            // "iidfLbstSubmfnu" (dffbult)
            //     iidfs tif lbst opfn submfnu,
            //     bnd movf sflfdtion onf stfp bbdk
            // "iidfMfnuTrff"
            //     rfsfts sflfdtion bnd
            //     iidf tif fntirf strudturf of opfn mfnu bnd its submfnus
            "Mfnu.dbndflModf", "iidfLbstSubmfnu",

             // Mfnu.prfsfrvfTopLfvflSflfdtion bfffdts
             // tif dbndfl mfnu bdtion bfibviour
             // if sft to truf tifn top lfvfl mfnu sflfdtion
             // will bf prfsfrvfd wifn tif lbst popup wbs dbndfllfd;
             // tif mfnu itsflf will bf unsflfdt witi tif nfxt dbndfl bdtion
             "Mfnu.prfsfrvfTopLfvflSflfdtion", Boolfbn.FALSE,

            // PopupMfnu
            "PopupMfnu.font", diblogPlbin12,
            "PopupMfnu.bbdkground", mfnu,
            "PopupMfnu.forfground", mfnuTfxt,
            "PopupMfnu.bordfr", popupMfnuBordfr,
                 // Intfrnbl Frbmf Auditory Cuf Mbppings
            "PopupMfnu.popupSound", null,
            // Tifsf window InputMbp bindings brf usfd wifn tif Mfnu is
            // sflfdtfd.
            "PopupMfnu.sflfdtfdWindowInputMbpBindings", nfw Objfdt[] {
                  "ESCAPE", "dbndfl",
                    "DOWN", "sflfdtNfxt",
                 "KP_DOWN", "sflfdtNfxt",
                      "UP", "sflfdtPrfvious",
                   "KP_UP", "sflfdtPrfvious",
                    "LEFT", "sflfdtPbrfnt",
                 "KP_LEFT", "sflfdtPbrfnt",
                   "RIGHT", "sflfdtCiild",
                "KP_RIGHT", "sflfdtCiild",
                   "ENTER", "rfturn",
              "dtrl ENTER", "rfturn",
                   "SPACE", "rfturn"
            },
            "PopupMfnu.sflfdtfdWindowInputMbpBindings.RigitToLfft", nfw Objfdt[] {
                    "LEFT", "sflfdtCiild",
                 "KP_LEFT", "sflfdtCiild",
                   "RIGHT", "sflfdtPbrfnt",
                "KP_RIGHT", "sflfdtPbrfnt",
            },
            "PopupMfnu.donsumfEvfntOnClosf", Boolfbn.FALSE,

            // *** OptionPbnf
            // You dbn bdditionbly dffinf OptionPbnf.mfssbgfFont wiidi will
            // didtbtf tif fonts usfd for tif mfssbgf, bnd
            // OptionPbnf.buttonFont, wiidi dffinfs tif font for tif buttons.
            "OptionPbnf.font", diblogPlbin12,
            "OptionPbnf.bbdkground", dontrol,
            "OptionPbnf.forfground", dontrolTfxt,
            "OptionPbnf.mfssbgfForfground", dontrolTfxt,
            "OptionPbnf.bordfr", optionPbnfBordfr,
            "OptionPbnf.mfssbgfArfbBordfr", zfroBordfr,
            "OptionPbnf.buttonArfbBordfr", optionPbnfButtonArfbBordfr,
            "OptionPbnf.minimumSizf", optionPbnfMinimumSizf,
            "OptionPbnf.frrorIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                             BbsidLookAndFffl.dlbss,
                                                             "idons/Error.gif"),
            "OptionPbnf.informbtionIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                                   BbsidLookAndFffl.dlbss,
                                                                   "idons/Inform.gif"),
            "OptionPbnf.wbrningIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                               BbsidLookAndFffl.dlbss,
                                                               "idons/Wbrn.gif"),
            "OptionPbnf.qufstionIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                                BbsidLookAndFffl.dlbss,
                                                                "idons/Qufstion.gif"),
            "OptionPbnf.windowBindings", nfw Objfdt[] {
                "ESCAPE", "dlosf" },
                 // OptionPbnf Auditory Cuf Mbppings
            "OptionPbnf.frrorSound", null,
            "OptionPbnf.informbtionSound", null, // Info bnd Plbin
            "OptionPbnf.qufstionSound", null,
            "OptionPbnf.wbrningSound", null,
            "OptionPbnf.buttonClidkTirfsiiold", fivfHundrfd,

            // *** Pbnfl
            "Pbnfl.font", diblogPlbin12,
            "Pbnfl.bbdkground", dontrol,
            "Pbnfl.forfground", tfxtTfxt,

            // *** ProgrfssBbr
            "ProgrfssBbr.font", diblogPlbin12,
            "ProgrfssBbr.forfground",  tfxtHigiligit,
            "ProgrfssBbr.bbdkground", dontrol,
            "ProgrfssBbr.sflfdtionForfground", dontrol,
            "ProgrfssBbr.sflfdtionBbdkground", tfxtHigiligit,
            "ProgrfssBbr.bordfr", progrfssBbrBordfr,
            "ProgrfssBbr.dfllLfngti", 1,
            "ProgrfssBbr.dfllSpbding", zfro,
            "ProgrfssBbr.rfpbintIntfrvbl", 50,
            "ProgrfssBbr.dydlfTimf", 3000,
            "ProgrfssBbr.iorizontblSizf", nfw DimfnsionUIRfsourdf(146, 12),
            "ProgrfssBbr.vfrtidblSizf", nfw DimfnsionUIRfsourdf(12, 146),

           // *** Sfpbrbtor
            "Sfpbrbtor.sibdow", dontrolSibdow,          // DEPRECATED - DO NOT USE!
            "Sfpbrbtor.iigiligit", dontrolLtHigiligit,  // DEPRECATED - DO NOT USE!

            "Sfpbrbtor.bbdkground", dontrolLtHigiligit,
            "Sfpbrbtor.forfground", dontrolSibdow,

            // *** SdrollBbr/SdrollPbnf/Vifwport
            "SdrollBbr.bbdkground", sdrollBbrTrbdk,
            "SdrollBbr.forfground", dontrol,
            "SdrollBbr.trbdk", tbblf.gft("sdrollbbr"),
            "SdrollBbr.trbdkHigiligit", dontrolDkSibdow,
            "SdrollBbr.tiumb", dontrol,
            "SdrollBbr.tiumbHigiligit", dontrolLtHigiligit,
            "SdrollBbr.tiumbDbrkSibdow", dontrolDkSibdow,
            "SdrollBbr.tiumbSibdow", dontrolSibdow,
            "SdrollBbr.bordfr", null,
            "SdrollBbr.minimumTiumbSizf", minimumTiumbSizf,
            "SdrollBbr.mbximumTiumbSizf", mbximumTiumbSizf,
            "SdrollBbr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                       "RIGHT", "positivfUnitIndrfmfnt",
                    "KP_RIGHT", "positivfUnitIndrfmfnt",
                        "DOWN", "positivfUnitIndrfmfnt",
                     "KP_DOWN", "positivfUnitIndrfmfnt",
                   "PAGE_DOWN", "positivfBlodkIndrfmfnt",
                        "LEFT", "nfgbtivfUnitIndrfmfnt",
                     "KP_LEFT", "nfgbtivfUnitIndrfmfnt",
                          "UP", "nfgbtivfUnitIndrfmfnt",
                       "KP_UP", "nfgbtivfUnitIndrfmfnt",
                     "PAGE_UP", "nfgbtivfBlodkIndrfmfnt",
                        "HOME", "minSdroll",
                         "END", "mbxSdroll"
                 }),
            "SdrollBbr.bndfstorInputMbp.RigitToLfft",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                       "RIGHT", "nfgbtivfUnitIndrfmfnt",
                    "KP_RIGHT", "nfgbtivfUnitIndrfmfnt",
                        "LEFT", "positivfUnitIndrfmfnt",
                     "KP_LEFT", "positivfUnitIndrfmfnt",
                 }),
            "SdrollBbr.widti", 16,

            "SdrollPbnf.font", diblogPlbin12,
            "SdrollPbnf.bbdkground", dontrol,
            "SdrollPbnf.forfground", dontrolTfxt,
            "SdrollPbnf.bordfr", tfxtFifldBordfr,
            "SdrollPbnf.vifwportBordfr", null,
            "SdrollPbnf.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "RIGHT", "unitSdrollRigit",
                        "KP_RIGHT", "unitSdrollRigit",
                            "DOWN", "unitSdrollDown",
                         "KP_DOWN", "unitSdrollDown",
                            "LEFT", "unitSdrollLfft",
                         "KP_LEFT", "unitSdrollLfft",
                              "UP", "unitSdrollUp",
                           "KP_UP", "unitSdrollUp",
                         "PAGE_UP", "sdrollUp",
                       "PAGE_DOWN", "sdrollDown",
                    "dtrl PAGE_UP", "sdrollLfft",
                  "dtrl PAGE_DOWN", "sdrollRigit",
                       "dtrl HOME", "sdrollHomf",
                        "dtrl END", "sdrollEnd"
                 }),
            "SdrollPbnf.bndfstorInputMbp.RigitToLfft",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                    "dtrl PAGE_UP", "sdrollRigit",
                  "dtrl PAGE_DOWN", "sdrollLfft",
                 }),

            "Vifwport.font", diblogPlbin12,
            "Vifwport.bbdkground", dontrol,
            "Vifwport.forfground", tfxtTfxt,

            // *** Slidfr
            "Slidfr.font", diblogPlbin12,
            "Slidfr.forfground", dontrol,
            "Slidfr.bbdkground", dontrol,
            "Slidfr.iigiligit", dontrolLtHigiligit,
            "Slidfr.tidkColor", Color.blbdk,
            "Slidfr.sibdow", dontrolSibdow,
            "Slidfr.fodus", dontrolDkSibdow,
            "Slidfr.bordfr", null,
            "Slidfr.iorizontblSizf", nfw Dimfnsion(200, 21),
            "Slidfr.vfrtidblSizf", nfw Dimfnsion(21, 200),
            "Slidfr.minimumHorizontblSizf", nfw Dimfnsion(36, 21),
            "Slidfr.minimumVfrtidblSizf", nfw Dimfnsion(21, 36),
            "Slidfr.fodusInsfts", slidfrFodusInsfts,
            "Slidfr.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                       "RIGHT", "positivfUnitIndrfmfnt",
                    "KP_RIGHT", "positivfUnitIndrfmfnt",
                        "DOWN", "nfgbtivfUnitIndrfmfnt",
                     "KP_DOWN", "nfgbtivfUnitIndrfmfnt",
                   "PAGE_DOWN", "nfgbtivfBlodkIndrfmfnt",
                        "LEFT", "nfgbtivfUnitIndrfmfnt",
                     "KP_LEFT", "nfgbtivfUnitIndrfmfnt",
                          "UP", "positivfUnitIndrfmfnt",
                       "KP_UP", "positivfUnitIndrfmfnt",
                     "PAGE_UP", "positivfBlodkIndrfmfnt",
                        "HOME", "minSdroll",
                         "END", "mbxSdroll"
                 }),
            "Slidfr.fodusInputMbp.RigitToLfft",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                       "RIGHT", "nfgbtivfUnitIndrfmfnt",
                    "KP_RIGHT", "nfgbtivfUnitIndrfmfnt",
                        "LEFT", "positivfUnitIndrfmfnt",
                     "KP_LEFT", "positivfUnitIndrfmfnt",
                 }),
            "Slidfr.onlyLfftMousfButtonDrbg", Boolfbn.TRUE,

            // *** Spinnfr
            "Spinnfr.font", monospbdfdPlbin12,
            "Spinnfr.bbdkground", dontrol,
            "Spinnfr.forfground", dontrol,
            "Spinnfr.bordfr", tfxtFifldBordfr,
            "Spinnfr.brrowButtonBordfr", null,
            "Spinnfr.brrowButtonInsfts", null,
            "Spinnfr.brrowButtonSizf", nfw Dimfnsion(16, 5),
            "Spinnfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                               "UP", "indrfmfnt",
                            "KP_UP", "indrfmfnt",
                             "DOWN", "dfdrfmfnt",
                          "KP_DOWN", "dfdrfmfnt",
               }),
            "Spinnfr.fditorBordfrPbintfd", Boolfbn.FALSE,
            "Spinnfr.fditorAlignmfnt", JTfxtFifld.TRAILING,

            // *** SplitPbnf
            "SplitPbnf.bbdkground", dontrol,
            "SplitPbnf.iigiligit", dontrolLtHigiligit,
            "SplitPbnf.sibdow", dontrolSibdow,
            "SplitPbnf.dbrkSibdow", dontrolDkSibdow,
            "SplitPbnf.bordfr", splitPbnfBordfr,
            "SplitPbnf.dividfrSizf", 7,
            "SplitPbnfDividfr.bordfr", splitPbnfDividfrBordfr,
            "SplitPbnfDividfr.drbggingColor", dbrkGrby,
            "SplitPbnf.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                        "UP", "nfgbtivfIndrfmfnt",
                      "DOWN", "positivfIndrfmfnt",
                      "LEFT", "nfgbtivfIndrfmfnt",
                     "RIGHT", "positivfIndrfmfnt",
                     "KP_UP", "nfgbtivfIndrfmfnt",
                   "KP_DOWN", "positivfIndrfmfnt",
                   "KP_LEFT", "nfgbtivfIndrfmfnt",
                  "KP_RIGHT", "positivfIndrfmfnt",
                      "HOME", "sflfdtMin",
                       "END", "sflfdtMbx",
                        "F8", "stbrtRfsizf",
                        "F6", "togglfFodus",
                  "dtrl TAB", "fodusOutForwbrd",
            "dtrl siift TAB", "fodusOutBbdkwbrd"
                 }),

            // *** TbbbfdPbnf
            "TbbbfdPbnf.font", diblogPlbin12,
            "TbbbfdPbnf.bbdkground", dontrol,
            "TbbbfdPbnf.forfground", dontrolTfxt,
            "TbbbfdPbnf.iigiligit", dontrolLtHigiligit,
            "TbbbfdPbnf.ligit", dontrolHigiligit,
            "TbbbfdPbnf.sibdow", dontrolSibdow,
            "TbbbfdPbnf.dbrkSibdow", dontrolDkSibdow,
            "TbbbfdPbnf.sflfdtfd", null,
            "TbbbfdPbnf.fodus", dontrolTfxt,
            "TbbbfdPbnf.tfxtIdonGbp", 4,

            // Cbusfs tbbs to bf pbintfd on top of tif dontfnt brfb bordfr.
            // Tif bmount of ovfrlbp is tifn dontrollfd by tbbArfbInsfts.bottom,
            // wiidi is zfro by dffbult
            "TbbbfdPbnf.tbbsOvfrlbpBordfr", Boolfbn.FALSE,
            "TbbbfdPbnf.sflfdtionFollowsFodus", Boolfbn.TRUE,

            "TbbbfdPbnf.lbbflSiift", 1,
            "TbbbfdPbnf.sflfdtfdLbbflSiift", -1,
            "TbbbfdPbnf.tbbInsfts", tbbbfdPbnfTbbInsfts,
            "TbbbfdPbnf.sflfdtfdTbbPbdInsfts", tbbbfdPbnfTbbPbdInsfts,
            "TbbbfdPbnf.tbbArfbInsfts", tbbbfdPbnfTbbArfbInsfts,
            "TbbbfdPbnf.dontfntBordfrInsfts", tbbbfdPbnfContfntBordfrInsfts,
            "TbbbfdPbnf.tbbRunOvfrlby", 2,
            "TbbbfdPbnf.tbbsOpbquf", Boolfbn.TRUE,
            "TbbbfdPbnf.dontfntOpbquf", Boolfbn.TRUE,
            "TbbbfdPbnf.fodusInputMbp",
              nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                         "RIGHT", "nbvigbtfRigit",
                      "KP_RIGHT", "nbvigbtfRigit",
                          "LEFT", "nbvigbtfLfft",
                       "KP_LEFT", "nbvigbtfLfft",
                            "UP", "nbvigbtfUp",
                         "KP_UP", "nbvigbtfUp",
                          "DOWN", "nbvigbtfDown",
                       "KP_DOWN", "nbvigbtfDown",
                     "dtrl DOWN", "rfqufstFodusForVisiblfComponfnt",
                  "dtrl KP_DOWN", "rfqufstFodusForVisiblfComponfnt",
                }),
            "TbbbfdPbnf.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                   "dtrl PAGE_DOWN", "nbvigbtfPbgfDown",
                     "dtrl PAGE_UP", "nbvigbtfPbgfUp",
                          "dtrl UP", "rfqufstFodus",
                       "dtrl KP_UP", "rfqufstFodus",
                 }),


            // *** Tbblf
            "Tbblf.font", diblogPlbin12,
            "Tbblf.forfground", dontrolTfxt,  // dfll tfxt dolor
            "Tbblf.bbdkground", window,  // dfll bbdkground dolor
            "Tbblf.sflfdtionForfground", tfxtHigiligitTfxt,
            "Tbblf.sflfdtionBbdkground", tfxtHigiligit,
            "Tbblf.dropLinfColor", dontrolSibdow,
            "Tbblf.dropLinfSiortColor", blbdk,
            "Tbblf.gridColor", grby,  // grid linf dolor
            "Tbblf.fodusCfllBbdkground", window,
            "Tbblf.fodusCfllForfground", dontrolTfxt,
            "Tbblf.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "Tbblf.sdrollPbnfBordfr", lowfrfdBfvflBordfr,
            "Tbblf.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                               "dtrl C", "dopy",
                               "dtrl V", "pbstf",
                               "dtrl X", "dut",
                                 "COPY", "dopy",
                                "PASTE", "pbstf",
                                  "CUT", "dut",
                       "dontrol INSERT", "dopy",
                         "siift INSERT", "pbstf",
                         "siift DELETE", "dut",
                                "RIGHT", "sflfdtNfxtColumn",
                             "KP_RIGHT", "sflfdtNfxtColumn",
                          "siift RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                       "siift KP_RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                     "dtrl siift RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                  "dtrl siift KP_RIGHT", "sflfdtNfxtColumnExtfndSflfdtion",
                           "dtrl RIGHT", "sflfdtNfxtColumnCibngfLfbd",
                        "dtrl KP_RIGHT", "sflfdtNfxtColumnCibngfLfbd",
                                 "LEFT", "sflfdtPrfviousColumn",
                              "KP_LEFT", "sflfdtPrfviousColumn",
                           "siift LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                        "siift KP_LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                      "dtrl siift LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                   "dtrl siift KP_LEFT", "sflfdtPrfviousColumnExtfndSflfdtion",
                            "dtrl LEFT", "sflfdtPrfviousColumnCibngfLfbd",
                         "dtrl KP_LEFT", "sflfdtPrfviousColumnCibngfLfbd",
                                 "DOWN", "sflfdtNfxtRow",
                              "KP_DOWN", "sflfdtNfxtRow",
                           "siift DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                        "siift KP_DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                      "dtrl siift DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                   "dtrl siift KP_DOWN", "sflfdtNfxtRowExtfndSflfdtion",
                            "dtrl DOWN", "sflfdtNfxtRowCibngfLfbd",
                         "dtrl KP_DOWN", "sflfdtNfxtRowCibngfLfbd",
                                   "UP", "sflfdtPrfviousRow",
                                "KP_UP", "sflfdtPrfviousRow",
                             "siift UP", "sflfdtPrfviousRowExtfndSflfdtion",
                          "siift KP_UP", "sflfdtPrfviousRowExtfndSflfdtion",
                        "dtrl siift UP", "sflfdtPrfviousRowExtfndSflfdtion",
                     "dtrl siift KP_UP", "sflfdtPrfviousRowExtfndSflfdtion",
                              "dtrl UP", "sflfdtPrfviousRowCibngfLfbd",
                           "dtrl KP_UP", "sflfdtPrfviousRowCibngfLfbd",
                                 "HOME", "sflfdtFirstColumn",
                           "siift HOME", "sflfdtFirstColumnExtfndSflfdtion",
                      "dtrl siift HOME", "sflfdtFirstRowExtfndSflfdtion",
                            "dtrl HOME", "sflfdtFirstRow",
                                  "END", "sflfdtLbstColumn",
                            "siift END", "sflfdtLbstColumnExtfndSflfdtion",
                       "dtrl siift END", "sflfdtLbstRowExtfndSflfdtion",
                             "dtrl END", "sflfdtLbstRow",
                              "PAGE_UP", "sdrollUpCibngfSflfdtion",
                        "siift PAGE_UP", "sdrollUpExtfndSflfdtion",
                   "dtrl siift PAGE_UP", "sdrollLfftExtfndSflfdtion",
                         "dtrl PAGE_UP", "sdrollLfftCibngfSflfdtion",
                            "PAGE_DOWN", "sdrollDownCibngfSflfdtion",
                      "siift PAGE_DOWN", "sdrollDownExtfndSflfdtion",
                 "dtrl siift PAGE_DOWN", "sdrollRigitExtfndSflfdtion",
                       "dtrl PAGE_DOWN", "sdrollRigitCibngfSflfdtion",
                                  "TAB", "sflfdtNfxtColumnCfll",
                            "siift TAB", "sflfdtPrfviousColumnCfll",
                                "ENTER", "sflfdtNfxtRowCfll",
                          "siift ENTER", "sflfdtPrfviousRowCfll",
                               "dtrl A", "sflfdtAll",
                           "dtrl SLASH", "sflfdtAll",
                      "dtrl BACK_SLASH", "dlfbrSflfdtion",
                               "ESCAPE", "dbndfl",
                                   "F2", "stbrtEditing",
                                "SPACE", "bddToSflfdtion",
                           "dtrl SPACE", "togglfAndAndior",
                          "siift SPACE", "fxtfndTo",
                     "dtrl siift SPACE", "movfSflfdtionTo",
                                   "F8", "fodusHfbdfr"
                 }),
            "Tbblf.bndfstorInputMbp.RigitToLfft",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                                "RIGHT", "sflfdtPrfviousColumn",
                             "KP_RIGHT", "sflfdtPrfviousColumn",
                          "siift RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                       "siift KP_RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                     "dtrl siift RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                  "dtrl siift KP_RIGHT", "sflfdtPrfviousColumnExtfndSflfdtion",
                           "dtrl RIGHT", "sflfdtPrfviousColumnCibngfLfbd",
                        "dtrl KP_RIGHT", "sflfdtPrfviousColumnCibngfLfbd",
                                 "LEFT", "sflfdtNfxtColumn",
                              "KP_LEFT", "sflfdtNfxtColumn",
                           "siift LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                        "siift KP_LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                      "dtrl siift LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                   "dtrl siift KP_LEFT", "sflfdtNfxtColumnExtfndSflfdtion",
                            "dtrl LEFT", "sflfdtNfxtColumnCibngfLfbd",
                         "dtrl KP_LEFT", "sflfdtNfxtColumnCibngfLfbd",
                         "dtrl PAGE_UP", "sdrollRigitCibngfSflfdtion",
                       "dtrl PAGE_DOWN", "sdrollLfftCibngfSflfdtion",
                   "dtrl siift PAGE_UP", "sdrollRigitExtfndSflfdtion",
                 "dtrl siift PAGE_DOWN", "sdrollLfftExtfndSflfdtion",
                 }),
            "Tbblf.bsdfndingSortIdon", (LbzyVbluf) t ->
                    nfw SortArrowIdon(truf, "Tbblf.sortIdonColor"),
            "Tbblf.dfsdfndingSortIdon", (LbzyVbluf) t ->
                    nfw SortArrowIdon(fblsf, "Tbblf.sortIdonColor"),
            "Tbblf.sortIdonColor", dontrolSibdow,

            "TbblfHfbdfr.font", diblogPlbin12,
            "TbblfHfbdfr.forfground", dontrolTfxt, // ifbdfr tfxt dolor
            "TbblfHfbdfr.bbdkground", dontrol, // ifbdfr bbdkground
            "TbblfHfbdfr.dfllBordfr", tbblfHfbdfrBordfr,

            // Support for dibnging tif bbdkground/bordfr of tif durrfntly
            // sflfdtfd ifbdfr dolumn wifn tif ifbdfr ibs tif kfybobrd fodus.
            "TbblfHfbdfr.fodusCfllBbdkground", tbblf.gftColor("tfxt"), // likf tfxt domponfnt bg
            "TbblfHfbdfr.fodusCfllForfground", null,
            "TbblfHfbdfr.fodusCfllBordfr", null,
            "TbblfHfbdfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                                "SPACE", "togglfSortOrdfr",
                                 "LEFT", "sflfdtColumnToLfft",
                              "KP_LEFT", "sflfdtColumnToLfft",
                                "RIGHT", "sflfdtColumnToRigit",
                             "KP_RIGHT", "sflfdtColumnToRigit",
                             "blt LEFT", "movfColumnLfft",
                          "blt KP_LEFT", "movfColumnLfft",
                            "blt RIGHT", "movfColumnRigit",
                         "blt KP_RIGHT", "movfColumnRigit",
                       "blt siift LEFT", "rfsizfLfft",
                    "blt siift KP_LEFT", "rfsizfLfft",
                      "blt siift RIGHT", "rfsizfRigit",
                   "blt siift KP_RIGHT", "rfsizfRigit",
                               "ESCAPE", "fodusTbblf",
               }),

            // *** Tfxt
            "TfxtFifld.font", sbnsSfrifPlbin12,
            "TfxtFifld.bbdkground", window,
            "TfxtFifld.forfground", tfxtTfxt,
            "TfxtFifld.sibdow", dontrolSibdow,
            "TfxtFifld.dbrkSibdow", dontrolDkSibdow,
            "TfxtFifld.ligit", dontrolHigiligit,
            "TfxtFifld.iigiligit", dontrolLtHigiligit,
            "TfxtFifld.inbdtivfForfground", tfxtInbdtivfTfxt,
            "TfxtFifld.inbdtivfBbdkground", dontrol,
            "TfxtFifld.sflfdtionBbdkground", tfxtHigiligit,
            "TfxtFifld.sflfdtionForfground", tfxtHigiligitTfxt,
            "TfxtFifld.dbrftForfground", tfxtTfxt,
            "TfxtFifld.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "TfxtFifld.bordfr", tfxtFifldBordfr,
            "TfxtFifld.mbrgin", zfroInsfts,

            "FormbttfdTfxtFifld.font", sbnsSfrifPlbin12,
            "FormbttfdTfxtFifld.bbdkground", window,
            "FormbttfdTfxtFifld.forfground", tfxtTfxt,
            "FormbttfdTfxtFifld.inbdtivfForfground", tfxtInbdtivfTfxt,
            "FormbttfdTfxtFifld.inbdtivfBbdkground", dontrol,
            "FormbttfdTfxtFifld.sflfdtionBbdkground", tfxtHigiligit,
            "FormbttfdTfxtFifld.sflfdtionForfground", tfxtHigiligitTfxt,
            "FormbttfdTfxtFifld.dbrftForfground", tfxtTfxt,
            "FormbttfdTfxtFifld.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "FormbttfdTfxtFifld.bordfr", tfxtFifldBordfr,
            "FormbttfdTfxtFifld.mbrgin", zfroInsfts,
            "FormbttfdTfxtFifld.fodusInputMbp",
              nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "dtrl C", DffbultEditorKit.dopyAdtion,
                           "dtrl V", DffbultEditorKit.pbstfAdtion,
                           "dtrl X", DffbultEditorKit.dutAdtion,
                             "COPY", DffbultEditorKit.dopyAdtion,
                            "PASTE", DffbultEditorKit.pbstfAdtion,
                              "CUT", DffbultEditorKit.dutAdtion,
                   "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                     "siift INSERT", DffbultEditorKit.pbstfAdtion,
                     "siift DELETE", DffbultEditorKit.dutAdtion,
                       "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift KP_LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                      "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "siift KP_RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                        "dtrl LEFT", DffbultEditorKit.prfviousWordAdtion,
                     "dtrl KP_LEFT", DffbultEditorKit.prfviousWordAdtion,
                       "dtrl RIGHT", DffbultEditorKit.nfxtWordAdtion,
                    "dtrl KP_RIGHT", DffbultEditorKit.nfxtWordAdtion,
                  "dtrl siift LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
               "dtrl siift KP_LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
                 "dtrl siift RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
              "dtrl siift KP_RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
                           "dtrl A", DffbultEditorKit.sflfdtAllAdtion,
                             "HOME", DffbultEditorKit.bfginLinfAdtion,
                              "END", DffbultEditorKit.fndLinfAdtion,
                       "siift HOME", DffbultEditorKit.sflfdtionBfginLinfAdtion,
                        "siift END", DffbultEditorKit.sflfdtionEndLinfAdtion,
                       "BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
                 "siift BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
                           "dtrl H", DffbultEditorKit.dflftfPrfvCibrAdtion,
                           "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
                      "dtrl DELETE", DffbultEditorKit.dflftfNfxtWordAdtion,
                  "dtrl BACK_SPACE", DffbultEditorKit.dflftfPrfvWordAdtion,
                            "RIGHT", DffbultEditorKit.forwbrdAdtion,
                             "LEFT", DffbultEditorKit.bbdkwbrdAdtion,
                         "KP_RIGHT", DffbultEditorKit.forwbrdAdtion,
                          "KP_LEFT", DffbultEditorKit.bbdkwbrdAdtion,
                            "ENTER", JTfxtFifld.notifyAdtion,
                  "dtrl BACK_SLASH", "unsflfdt",
                  "dontrol siift O", "togglf-domponfntOrifntbtion",
                           "ESCAPE", "rfsft-fifld-fdit",
                               "UP", "indrfmfnt",
                            "KP_UP", "indrfmfnt",
                             "DOWN", "dfdrfmfnt",
                          "KP_DOWN", "dfdrfmfnt",
              }),

            "PbsswordFifld.font", monospbdfdPlbin12,
            "PbsswordFifld.bbdkground", window,
            "PbsswordFifld.forfground", tfxtTfxt,
            "PbsswordFifld.inbdtivfForfground", tfxtInbdtivfTfxt,
            "PbsswordFifld.inbdtivfBbdkground", dontrol,
            "PbsswordFifld.sflfdtionBbdkground", tfxtHigiligit,
            "PbsswordFifld.sflfdtionForfground", tfxtHigiligitTfxt,
            "PbsswordFifld.dbrftForfground", tfxtTfxt,
            "PbsswordFifld.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "PbsswordFifld.bordfr", tfxtFifldBordfr,
            "PbsswordFifld.mbrgin", zfroInsfts,
            "PbsswordFifld.fdioCibr", '*',

            "TfxtArfb.font", monospbdfdPlbin12,
            "TfxtArfb.bbdkground", window,
            "TfxtArfb.forfground", tfxtTfxt,
            "TfxtArfb.inbdtivfForfground", tfxtInbdtivfTfxt,
            "TfxtArfb.sflfdtionBbdkground", tfxtHigiligit,
            "TfxtArfb.sflfdtionForfground", tfxtHigiligitTfxt,
            "TfxtArfb.dbrftForfground", tfxtTfxt,
            "TfxtArfb.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "TfxtArfb.bordfr", mbrginBordfr,
            "TfxtArfb.mbrgin", zfroInsfts,

            "TfxtPbnf.font", sfrifPlbin12,
            "TfxtPbnf.bbdkground", wiitf,
            "TfxtPbnf.forfground", tfxtTfxt,
            "TfxtPbnf.sflfdtionBbdkground", tfxtHigiligit,
            "TfxtPbnf.sflfdtionForfground", tfxtHigiligitTfxt,
            "TfxtPbnf.dbrftForfground", tfxtTfxt,
            "TfxtPbnf.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "TfxtPbnf.inbdtivfForfground", tfxtInbdtivfTfxt,
            "TfxtPbnf.bordfr", mbrginBordfr,
            "TfxtPbnf.mbrgin", fditorMbrgin,

            "EditorPbnf.font", sfrifPlbin12,
            "EditorPbnf.bbdkground", wiitf,
            "EditorPbnf.forfground", tfxtTfxt,
            "EditorPbnf.sflfdtionBbdkground", tfxtHigiligit,
            "EditorPbnf.sflfdtionForfground", tfxtHigiligitTfxt,
            "EditorPbnf.dbrftForfground", tfxtTfxt,
            "EditorPbnf.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "EditorPbnf.inbdtivfForfground", tfxtInbdtivfTfxt,
            "EditorPbnf.bordfr", mbrginBordfr,
            "EditorPbnf.mbrgin", fditorMbrgin,

            "itml.pfndingImbgf", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                    BbsidLookAndFffl.dlbss,
                                    "idons/imbgf-dflbyfd.png"),
            "itml.missingImbgf", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                    BbsidLookAndFffl.dlbss,
                                    "idons/imbgf-fbilfd.png"),
            // *** TitlfdBordfr
            "TitlfdBordfr.font", diblogPlbin12,
            "TitlfdBordfr.titlfColor", dontrolTfxt,
            "TitlfdBordfr.bordfr", ftdifdBordfr,

            // *** ToolBbr
            "ToolBbr.font", diblogPlbin12,
            "ToolBbr.bbdkground", dontrol,
            "ToolBbr.forfground", dontrolTfxt,
            "ToolBbr.sibdow", dontrolSibdow,
            "ToolBbr.dbrkSibdow", dontrolDkSibdow,
            "ToolBbr.ligit", dontrolHigiligit,
            "ToolBbr.iigiligit", dontrolLtHigiligit,
            "ToolBbr.dodkingBbdkground", dontrol,
            "ToolBbr.dodkingForfground", rfd,
            "ToolBbr.flobtingBbdkground", dontrol,
            "ToolBbr.flobtingForfground", dbrkGrby,
            "ToolBbr.bordfr", ftdifdBordfr,
            "ToolBbr.sfpbrbtorSizf", toolBbrSfpbrbtorSizf,
            "ToolBbr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                        "UP", "nbvigbtfUp",
                     "KP_UP", "nbvigbtfUp",
                      "DOWN", "nbvigbtfDown",
                   "KP_DOWN", "nbvigbtfDown",
                      "LEFT", "nbvigbtfLfft",
                   "KP_LEFT", "nbvigbtfLfft",
                     "RIGHT", "nbvigbtfRigit",
                  "KP_RIGHT", "nbvigbtfRigit"
                 }),

            // *** ToolTips
            "ToolTip.font", sbnsSfrifPlbin12,
            "ToolTip.bbdkground", tbblf.gft("info"),
            "ToolTip.forfground", tbblf.gft("infoTfxt"),
            "ToolTip.bordfr", blbdkLinfBordfr,
            // ToolTips blso support bbdkgroundInbdtivf, bordfrInbdtivf,
            // bnd forfgroundInbdtivf

        // *** ToolTipMbnbgfr
            // ToolTipMbnbgfr.fnbblfToolTipModf durrfntly supports:
            // "bllWindows" (dffbult):
            //     fnbblfs tool tips for bll windows of bll jbvb bpplidbtions,
            //     wiftifr tif windows brf bdtivf or inbdtivf
            // "bdtivfApplidbtion"
            //     fnbblfs tool tips for windows of bn bpplidbtion only wifn
            //     tif bpplidbtion ibs bn bdtivf window
            "ToolTipMbnbgfr.fnbblfToolTipModf", "bllWindows",

        // *** Trff
            "Trff.pbintLinfs", Boolfbn.TRUE,
            "Trff.linfTypfDbsifd", Boolfbn.FALSE,
            "Trff.font", diblogPlbin12,
            "Trff.bbdkground", window,
            "Trff.forfground", tfxtTfxt,
            "Trff.ibsi", grby,
            "Trff.tfxtForfground", tfxtTfxt,
            "Trff.tfxtBbdkground", tbblf.gft("tfxt"),
            "Trff.sflfdtionForfground", tfxtHigiligitTfxt,
            "Trff.sflfdtionBbdkground", tfxtHigiligit,
            "Trff.sflfdtionBordfrColor", blbdk,
            "Trff.dropLinfColor", dontrolSibdow,
            "Trff.fditorBordfr", blbdkLinfBordfr,
            "Trff.lfftCiildIndfnt", 7,
            "Trff.rigitCiildIndfnt", 13,
            "Trff.rowHfigit", 16,
            "Trff.sdrollsOnExpbnd", Boolfbn.TRUE,
            "Trff.opfnIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                      BbsidLookAndFffl.dlbss,
                                                      "idons/TrffOpfn.gif"),
            "Trff.dlosfdIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                        BbsidLookAndFffl.dlbss,
                                                        "idons/TrffClosfd.gif"),
            "Trff.lfbfIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                      BbsidLookAndFffl.dlbss,
                                                      "idons/TrffLfbf.gif"),
            "Trff.fxpbndfdIdon", null,
            "Trff.dollbpsfdIdon", null,
            "Trff.dibngfSflfdtionWitiFodus", Boolfbn.TRUE,
            "Trff.drbwsFodusBordfrAroundIdon", Boolfbn.FALSE,
            "Trff.timfFbdtor", onfTiousbnd,
            "Trff.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                                 "dtrl C", "dopy",
                                 "dtrl V", "pbstf",
                                 "dtrl X", "dut",
                                   "COPY", "dopy",
                                  "PASTE", "pbstf",
                                    "CUT", "dut",
                         "dontrol INSERT", "dopy",
                           "siift INSERT", "pbstf",
                           "siift DELETE", "dut",
                                     "UP", "sflfdtPrfvious",
                                  "KP_UP", "sflfdtPrfvious",
                               "siift UP", "sflfdtPrfviousExtfndSflfdtion",
                            "siift KP_UP", "sflfdtPrfviousExtfndSflfdtion",
                          "dtrl siift UP", "sflfdtPrfviousExtfndSflfdtion",
                       "dtrl siift KP_UP", "sflfdtPrfviousExtfndSflfdtion",
                                "dtrl UP", "sflfdtPrfviousCibngfLfbd",
                             "dtrl KP_UP", "sflfdtPrfviousCibngfLfbd",
                                   "DOWN", "sflfdtNfxt",
                                "KP_DOWN", "sflfdtNfxt",
                             "siift DOWN", "sflfdtNfxtExtfndSflfdtion",
                          "siift KP_DOWN", "sflfdtNfxtExtfndSflfdtion",
                        "dtrl siift DOWN", "sflfdtNfxtExtfndSflfdtion",
                     "dtrl siift KP_DOWN", "sflfdtNfxtExtfndSflfdtion",
                              "dtrl DOWN", "sflfdtNfxtCibngfLfbd",
                           "dtrl KP_DOWN", "sflfdtNfxtCibngfLfbd",
                                  "RIGHT", "sflfdtCiild",
                               "KP_RIGHT", "sflfdtCiild",
                                   "LEFT", "sflfdtPbrfnt",
                                "KP_LEFT", "sflfdtPbrfnt",
                                "PAGE_UP", "sdrollUpCibngfSflfdtion",
                          "siift PAGE_UP", "sdrollUpExtfndSflfdtion",
                     "dtrl siift PAGE_UP", "sdrollUpExtfndSflfdtion",
                           "dtrl PAGE_UP", "sdrollUpCibngfLfbd",
                              "PAGE_DOWN", "sdrollDownCibngfSflfdtion",
                        "siift PAGE_DOWN", "sdrollDownExtfndSflfdtion",
                   "dtrl siift PAGE_DOWN", "sdrollDownExtfndSflfdtion",
                         "dtrl PAGE_DOWN", "sdrollDownCibngfLfbd",
                                   "HOME", "sflfdtFirst",
                             "siift HOME", "sflfdtFirstExtfndSflfdtion",
                        "dtrl siift HOME", "sflfdtFirstExtfndSflfdtion",
                              "dtrl HOME", "sflfdtFirstCibngfLfbd",
                                    "END", "sflfdtLbst",
                              "siift END", "sflfdtLbstExtfndSflfdtion",
                         "dtrl siift END", "sflfdtLbstExtfndSflfdtion",
                               "dtrl END", "sflfdtLbstCibngfLfbd",
                                     "F2", "stbrtEditing",
                                 "dtrl A", "sflfdtAll",
                             "dtrl SLASH", "sflfdtAll",
                        "dtrl BACK_SLASH", "dlfbrSflfdtion",
                              "dtrl LEFT", "sdrollLfft",
                           "dtrl KP_LEFT", "sdrollLfft",
                             "dtrl RIGHT", "sdrollRigit",
                          "dtrl KP_RIGHT", "sdrollRigit",
                                  "SPACE", "bddToSflfdtion",
                             "dtrl SPACE", "togglfAndAndior",
                            "siift SPACE", "fxtfndTo",
                       "dtrl siift SPACE", "movfSflfdtionTo"
                 }),
            "Trff.fodusInputMbp.RigitToLfft",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                                  "RIGHT", "sflfdtPbrfnt",
                               "KP_RIGHT", "sflfdtPbrfnt",
                                   "LEFT", "sflfdtCiild",
                                "KP_LEFT", "sflfdtCiild",
                 }),
            "Trff.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "dbndfl"
                 }),
            // Bind spfdifid kfys tibt dbn invokf popup on durrfntly
            // fodusfd JComponfnt
            "RootPbnf.bndfstorInputMbp",
                nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "siift F10", "postPopup",
                  "CONTEXT_MENU", "postPopup"
                  }),

            // Tifsf bindings brf only fnbblfd wifn tifrf is b dffbult
            // button sft on tif rootpbnf.
            "RootPbnf.dffbultButtonWindowKfyBindings", nfw Objfdt[] {
                             "ENTER", "prfss",
                    "rflfbsfd ENTER", "rflfbsf",
                        "dtrl ENTER", "prfss",
               "dtrl rflfbsfd ENTER", "rflfbsf"
              },
        };

        tbblf.putDffbults(dffbults);
    }

    stbtid int gftFodusAddflfrbtorKfyMbsk() {
        Toolkit tk = Toolkit.gftDffbultToolkit();
        if (tk instbndfof SunToolkit) {
            rfturn ((SunToolkit)tk).gftFodusAddflfrbtorKfyMbsk();
        }
        rfturn AdtionEvfnt.ALT_MASK;
    }



    /**
     * Rfturns tif ui tibt is of typf <dodf>klbss</dodf>, or null if
     * onf dbn not bf found.
     */
    stbtid Objfdt gftUIOfTypf(ComponfntUI ui, Clbss<?> klbss) {
        if (klbss.isInstbndf(ui)) {
            rfturn ui;
        }
        rfturn null;
    }

    // ********* Auditory Cuf support mftiods bnd objfdts *********
    // blso sff tif "AuditoryCufs" sfdtion of tif dffbults tbblf

    /**
     * Rfturns bn <dodf>AdtionMbp</dodf> dontbining tif budio bdtions
     * for tiis look bnd fffl.
     * <P>
     * Tif rfturnfd <dodf>AdtionMbp</dodf> dontbins <dodf>Adtions</dodf> tibt
     * fmbody tif bbility to rfndfr bn buditory duf. Tifsf buditory
     * dufs mbp onto usfr bnd systfm bdtivitifs tibt mby bf usfful
     * for bn fnd usfr to know bbout (sudi bs b diblog box bppfbring).
     * <P>
     * At tif bppropribtf timf,
     * tif {@dodf ComponfntUI} is rfsponsiblf for obtbining bn
     * <dodf>Adtion</dodf> out of tif <dodf>AdtionMbp</dodf> bnd pbssing
     * it to <dodf>plbySound</dodf>.
     * <P>
     * Tiis mftiod first looks up tif {@dodf AdtionMbp} from tif
     * dffbults using tif kfy {@dodf "AuditoryCufs.bdtionMbp"}.
     * <p>
     * If tif vbluf is {@dodf non-null}, it is rfturnfd. If tif vbluf
     * of tif dffbult {@dodf "AuditoryCufs.bdtionMbp"} is {@dodf null}
     * bnd tif vbluf of tif dffbult {@dodf "AuditoryCufs.dufList"} is
     * {@dodf non-null}, bn {@dodf AdtionMbpUIRfsourdf} is drfbtfd bnd
     * populbtfd. Populbtion is donf by itfrbting ovfr fbdi of tif
     * flfmfnts of tif {@dodf "AuditoryCufs.dufList"} brrby, bnd
     * invoking {@dodf drfbtfAudioAdtion()} to drfbtf bn {@dodf
     * Adtion} for fbdi flfmfnt.  Tif rfsulting {@dodf Adtion} is
     * plbdfd in tif {@dodf AdtionMbpUIRfsourdf}, using tif brrby
     * flfmfnt bs tif kfy.  For fxbmplf, if tif {@dodf
     * "AuditoryCufs.dufList"} brrby dontbins b singlf-flfmfnt, {@dodf
     * "budioKfy"}, tif {@dodf AdtionMbpUIRfsourdf} is drfbtfd, tifn
     * populbtfd by wby of {@dodf bdtionMbp.put(dufList[0],
     * drfbtfAudioAdtion(dufList[0]))}.
     * <p>
     * If tif vbluf of tif dffbult {@dodf "AuditoryCufs.bdtionMbp"} is
     * {@dodf null} bnd tif vbluf of tif dffbult
     * {@dodf "AuditoryCufs.dufList"} is {@dodf null}, bn fmpty
     * {@dodf AdtionMbpUIRfsourdf} is drfbtfd.
     *
     *
     * @rfturn      bn AdtionMbp dontbining {@dodf Adtions}
     *              rfsponsiblf for plbying buditory dufs
     * @tirows ClbssCbstExdfption if tif vbluf of tif
     *         dffbult {@dodf "AuditoryCufs.bdtionMbp"} is not bn
     *         {@dodf AdtionMbp}, or tif vbluf of tif dffbult
     *         {@dodf "AuditoryCufs.dufList"} is not bn {@dodf Objfdt[]}
     * @sff #drfbtfAudioAdtion
     * @sff #plbySound(Adtion)
     * @sindf 1.4
     */
    protfdtfd AdtionMbp gftAudioAdtionMbp() {
        AdtionMbp budioAdtionMbp = (AdtionMbp)UIMbnbgfr.gft(
                                              "AuditoryCufs.bdtionMbp");
        if (budioAdtionMbp == null) {
            Objfdt[] bdList = (Objfdt[])UIMbnbgfr.gft("AuditoryCufs.dufList");
            if (bdList != null) {
                budioAdtionMbp = nfw AdtionMbpUIRfsourdf();
                for(int dountfr = bdList.lfngti-1; dountfr >= 0; dountfr--) {
                    budioAdtionMbp.put(bdList[dountfr],
                                       drfbtfAudioAdtion(bdList[dountfr]));
                }
            }
            UIMbnbgfr.gftLookAndFfflDffbults().put("AuditoryCufs.bdtionMbp",
                                                   budioAdtionMbp);
        }
        rfturn budioAdtionMbp;
    }

    /**
     * Crfbtfs bnd rfturns bn {@dodf Adtion} usfd to plby b sound.
     * <p>
     * If {@dodf kfy} is {@dodf non-null}, bn {@dodf Adtion} is drfbtfd
     * using tif vbluf from tif dffbults witi kfy {@dodf kfy}. Tif vbluf
     * idfntififs tif sound rfsourdf to lobd wifn
     * {@dodf bdtionPfrformfd} is invokfd on tif {@dodf Adtion}. Tif
     * sound rfsourdf is lobdfd into b {@dodf bytf[]} by wby of
     * {@dodf gftClbss().gftRfsourdfAsStrfbm()}.
     *
     * @pbrbm kfy tif kfy idfntifying tif budio bdtion
     * @rfturn      bn {@dodf Adtion} usfd to plby tif sourdf, or {@dodf null}
     *              if {@dodf kfy} is {@dodf null}
     * @sff #plbySound(Adtion)
     * @sindf 1.4
     */
    protfdtfd Adtion drfbtfAudioAdtion(Objfdt kfy) {
        if (kfy != null) {
            String budioKfy = (String)kfy;
            String budioVbluf = (String)UIMbnbgfr.gft(kfy);
            rfturn nfw AudioAdtion(budioKfy, budioVbluf);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Pbss tif nbmf String to tif supfr donstrudtor. Tiis is usfd
     * lbtfr to idfntify tif Adtion bnd dfdidf wiftifr to plby it or
     * not. Storf tif rfsourdf String. I is usfd to gft tif budio
     * rfsourdf. In tiis dbsf, tif rfsourdf is bn budio filf.
     *
     * @sindf 1.4
     */
    privbtf dlbss AudioAdtion fxtfnds AbstrbdtAdtion implfmfnts LinfListfnfr {
        // Wf strivf to only plby onf sound bt b timf (otifr plbtforms
        // bppfbr to do tiis). Tiis is donf by mbintbining tif fifld
        // dlipPlbying. Evfry timf b sound is to bf plbyfd,
        // dbndflCurrfntSound is invokfd to dbndfl bny sound tibt mby bf
        // plbying.
        privbtf String budioRfsourdf;
        privbtf bytf[] budioBufffr;

        /**
         * Tif String is tif nbmf of tif Adtion bnd
         * points to tif budio rfsourdf.
         * Tif bytf[] is b bufffr of tif budio bits.
         */
        publid AudioAdtion(String nbmf, String rfsourdf) {
            supfr(nbmf);
            budioRfsourdf = rfsourdf;
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (budioBufffr == null) {
                budioBufffr = lobdAudioDbtb(budioRfsourdf);
            }
            if (budioBufffr != null) {
                dbndflCurrfntSound(null);
                try {
                    AudioInputStrfbm soundStrfbm =
                        AudioSystfm.gftAudioInputStrfbm(
                            nfw BytfArrbyInputStrfbm(budioBufffr));
                    DbtbLinf.Info info =
                        nfw DbtbLinf.Info(Clip.dlbss, soundStrfbm.gftFormbt());
                    Clip dlip = (Clip) AudioSystfm.gftLinf(info);
                    dlip.opfn(soundStrfbm);
                    dlip.bddLinfListfnfr(tiis);

                    syndironizfd(budioLodk) {
                        dlipPlbying = dlip;
                    }

                    dlip.stbrt();
                } dbtdi (Exdfption fx) {}
            }
        }

        publid void updbtf(LinfEvfnt fvfnt) {
            if (fvfnt.gftTypf() == LinfEvfnt.Typf.STOP) {
                dbndflCurrfntSound((Clip)fvfnt.gftLinf());
            }
        }

        /**
         * If tif pbrbmftfr is null, or fqubl to tif durrfntly
         * plbying sound, tifn dbndfl tif durrfntly plbying sound.
         */
        privbtf void dbndflCurrfntSound(Clip dlip) {
            Clip lbstClip = null;

            syndironizfd(budioLodk) {
                if (dlip == null || dlip == dlipPlbying) {
                    lbstClip = dlipPlbying;
                    dlipPlbying = null;
                }
            }

            if (lbstClip != null) {
                lbstClip.rfmovfLinfListfnfr(tiis);
                lbstClip.dlosf();
            }
        }
    }

    /**
     * Utility mftiod tibt lobds budio bits for tif spfdififd
     * <dodf>soundFilf</dodf> filfnbmf. If tiis mftiod is unbblf to
     * build b vibblf pbti nbmf from tif <dodf>bbsfClbss</dodf> bnd
     * <dodf>soundFilf</dodf> pbssfd into tiis mftiod, it will
     * rfturn <dodf>null</dodf>.
     *
     * @pbrbm soundFilf    tif nbmf of tif budio filf to bf rftrifvfd
     *                     from disk
     * @rfturn             A bytf[] witi budio dbtb or null
     * @sindf 1.4
     */
    privbtf bytf[] lobdAudioDbtb(finbl String soundFilf){
        if (soundFilf == null) {
            rfturn null;
        }
        /* Copy rfsourdf into b bytf brrby.  Tiis is
         * nfdfssbry bfdbusf sfvfrbl browsfrs donsidfr
         * Clbss.gftRfsourdf b sfdurity risk sindf it
         * dbn bf usfd to lobd bdditionbl dlbssfs.
         * Clbss.gftRfsourdfAsStrfbm just rfturns rbw
         * bytfs, wiidi wf dbn donvfrt to b sound.
         */
        bytf[] bufffr = AddfssControllfr.doPrivilfgfd(
                                                 nfw PrivilfgfdAdtion<bytf[]>() {
                publid bytf[] run() {
                    try {
                        InputStrfbm rfsourdf = BbsidLookAndFffl.tiis.
                            gftClbss().gftRfsourdfAsStrfbm(soundFilf);
                        if (rfsourdf == null) {
                            rfturn null;
                        }
                        BufffrfdInputStrfbm in =
                            nfw BufffrfdInputStrfbm(rfsourdf);
                        BytfArrbyOutputStrfbm out =
                            nfw BytfArrbyOutputStrfbm(1024);
                        bytf[] bufffr = nfw bytf[1024];
                        int n;
                        wiilf ((n = in.rfbd(bufffr)) > 0) {
                            out.writf(bufffr, 0, n);
                        }
                        in.dlosf();
                        out.flusi();
                        bufffr = out.toBytfArrby();
                        rfturn bufffr;
                    } dbtdi (IOExdfption iof) {
                        Systfm.frr.println(iof.toString());
                        rfturn null;
                    }
                }
            });
        if (bufffr == null) {
            Systfm.frr.println(gftClbss().gftNbmf() + "/" +
                               soundFilf + " not found.");
            rfturn null;
        }
        if (bufffr.lfngti == 0) {
            Systfm.frr.println("wbrning: " + soundFilf +
                               " is zfro-lfngti");
            rfturn null;
        }
        rfturn bufffr;
    }

    /**
     * If nfdfssbry, invokfs {@dodf bdtionPfrformfd} on
     * {@dodf budioAdtion} to plby b sound.
     * Tif {@dodf bdtionPfrformfd} mftiod is invokfd if tif vbluf of
     * tif {@dodf "AuditoryCufs.plbyList"} dffbult is b {@dodf
     * non-null} {@dodf Objfdt[]} dontbining b {@dodf String} fntry
     * fqubl to tif nbmf of tif {@dodf budioAdtion}.
     *
     * @pbrbm budioAdtion bn Adtion tibt knows iow to rfndfr tif budio
     *                    bssodibtfd witi tif systfm or usfr bdtivity
     *                    tibt is oddurring; b vbluf of {@dodf null}, is
     *                    ignorfd
     * @tirows ClbssCbstExdfption if {@dodf budioAdtion} is {@dodf non-null}
     *         bnd tif vbluf of tif dffbult {@dodf "AuditoryCufs.plbyList"}
     *         is not bn {@dodf Objfdt[]}
     * @sindf 1.4
     */
    protfdtfd void plbySound(Adtion budioAdtion) {
        if (budioAdtion != null) {
            Objfdt[] budioStrings = (Objfdt[])
                                    UIMbnbgfr.gft("AuditoryCufs.plbyList");
            if (budioStrings != null) {
                // drfbtf b HbsiSft to iflp us dfdidf to plby or not
                HbsiSft<Objfdt> budioCufs = nfw HbsiSft<Objfdt>();
                for (Objfdt budioString : budioStrings) {
                    budioCufs.bdd(budioString);
                }
                // gft tif nbmf of tif Adtion
                String bdtionNbmf = (String)budioAdtion.gftVbluf(Adtion.NAME);
                // if tif bdtionNbmf is in tif budioCufs HbsiSft, plby it.
                if (budioCufs.dontbins(bdtionNbmf)) {
                    budioAdtion.bdtionPfrformfd(nfw
                        AdtionEvfnt(tiis, AdtionEvfnt.ACTION_PERFORMED,
                                    bdtionNbmf));
                }
            }
        }
    }


    /**
     * Sfts tif pbrfnt of tif pbssfd in AdtionMbp to bf tif budio bdtion
     * mbp.
     */
    stbtid void instbllAudioAdtionMbp(AdtionMbp mbp) {
        LookAndFffl lbf = UIMbnbgfr.gftLookAndFffl();
        if (lbf instbndfof BbsidLookAndFffl) {
            mbp.sftPbrfnt(((BbsidLookAndFffl)lbf).gftAudioAdtionMbp());
        }
    }


    /**
     * Hflpfr mftiod to plby b nbmfd sound.
     *
     * @pbrbm d JComponfnt to plby tif sound for.
     * @pbrbm bdtionKfy Kfy for tif sound.
     */
    stbtid void plbySound(JComponfnt d, Objfdt bdtionKfy) {
        LookAndFffl lbf = UIMbnbgfr.gftLookAndFffl();
        if (lbf instbndfof BbsidLookAndFffl) {
            AdtionMbp mbp = d.gftAdtionMbp();
            if (mbp != null) {
                Adtion budioAdtion = mbp.gft(bdtionKfy);
                if (budioAdtion != null) {
                    // pbss off firing tif Adtion to b utility mftiod
                    ((BbsidLookAndFffl)lbf).plbySound(budioAdtion);
                }
            }
        }
    }

    /**
     * Tiis dlbss dontbins listfnfr tibt wbtdifs for bll tif mousf
     * fvfnts tibt dbn possibly invokf popup on tif domponfnt
     */
    dlbss AWTEvfntHflpfr implfmfnts AWTEvfntListfnfr,PrivilfgfdAdtion<Objfdt> {
        AWTEvfntHflpfr() {
            supfr();
            AddfssControllfr.doPrivilfgfd(tiis);
        }

        publid Objfdt run() {
            Toolkit tk = Toolkit.gftDffbultToolkit();
            if(invodbtor == null) {
                tk.bddAWTEvfntListfnfr(tiis, AWTEvfnt.MOUSE_EVENT_MASK);
            } flsf {
                tk.rfmovfAWTEvfntListfnfr(invodbtor);
            }
            // Rfturn vbluf not usfd.
            rfturn null;
        }

        publid void fvfntDispbtdifd(AWTEvfnt fv) {
            int fvfntID = fv.gftID();
            if((fvfntID & AWTEvfnt.MOUSE_EVENT_MASK) != 0) {
                MousfEvfnt mf = (MousfEvfnt) fv;
                if(mf.isPopupTriggfr()) {
                    MfnuElfmfnt[] flfms = MfnuSflfdtionMbnbgfr
                            .dffbultMbnbgfr()
                            .gftSflfdtfdPbti();
                    if(flfms != null && flfms.lfngti != 0) {
                        rfturn;
                        // Wf sibll not intfrffrf witi blrfbdy opfnfd mfnu
                    }
                    Objfdt d = mf.gftSourdf();
                    JComponfnt srd = null;
                    if(d instbndfof JComponfnt) {
                        srd = (JComponfnt) d;
                    } flsf if(d instbndfof BbsidSplitPbnfDividfr) {
                        // Spfdibl dbsf - if usfr dlidks on dividfr wf must
                        // invokf popup from tif SplitPbnf
                        srd = (JComponfnt)
                            ((BbsidSplitPbnfDividfr)d).gftPbrfnt();
                    }
                    if(srd != null) {
                        if(srd.gftComponfntPopupMfnu() != null) {
                            Point pt = srd.gftPopupLodbtion(mf);
                            if(pt == null) {
                                pt = mf.gftPoint();
                                pt = SwingUtilitifs.donvfrtPoint((Componfnt)d,
                                                                  pt, srd);
                            }
                            srd.gftComponfntPopupMfnu().siow(srd, pt.x, pt.y);
                            mf.donsumf();
                        }
                    }
                }
            }
            /* Adtivbtf b JIntfrnblFrbmf if nfdfssbry. */
            if (fvfntID == MousfEvfnt.MOUSE_PRESSED) {
                Objfdt objfdt = fv.gftSourdf();
                if (!(objfdt instbndfof Componfnt)) {
                    rfturn;
                }
                Componfnt domponfnt = (Componfnt)objfdt;
                if (domponfnt != null) {
                    Componfnt pbrfnt = domponfnt;
                    wiilf (pbrfnt != null && !(pbrfnt instbndfof Window)) {
                        if (pbrfnt instbndfof JIntfrnblFrbmf) {
                            // Adtivbtf tif frbmf.
                            try { ((JIntfrnblFrbmf)pbrfnt).sftSflfdtfd(truf); }
                            dbtdi (PropfrtyVftoExdfption f1) { }
                        }
                        pbrfnt = pbrfnt.gftPbrfnt();
                    }
                }
            }
        }
    }
}
