/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Insfts;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvbx.swing.tfxt.DffbultEditorKit;

import jbvbx.swing.plbf.bbsid.BbsidLookAndFffl;
import jbvbx.swing.plbf.bbsid.BbsidBordfrs;
import jbvbx.swing.plbf.bbsid.BbsidComboBoxRfndfrfr;
import jbvbx.swing.plbf.bbsid.BbsidComboBoxEditor;

import sun.swing.SwingUtilitifs2;
import sun.bwt.OSInfo;

/**
 * Implfmfnts tif Motif Look bnd Fffl.
 * UI dlbssfs not implfmfntfd spfdifidblly for Motif will
 * dffbult to tiosf implfmfntfd in Bbsid.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior unbttributfd
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss MotifLookAndFffl fxtfnds BbsidLookAndFffl
{
    publid String gftNbmf() {
        rfturn "CDE/Motif";
    }

    publid String gftID() {
        rfturn "Motif";
    }

    publid String gftDfsdription() {
        rfturn "Tif CDE/Motif Look bnd Fffl";
    }


    publid boolfbn isNbtivfLookAndFffl() {
        rfturn OSInfo.gftOSTypf() == OSInfo.OSTypf.SOLARIS;
    }


    publid boolfbn isSupportfdLookAndFffl() {
        rfturn truf;
    }


    /**
     * Lobd tif SystfmColors into tif dffbults tbblf.  Tif kfys
     * for SystfmColor dffbults brf tif sbmf bs tif nbmfs of
     * tif publid fiflds in SystfmColor.  If tif tbblf is bfing
     * drfbtfd on b nbtivf Motif plbtform wf usf tif SystfmColor
     * vblufs, otifrwisf wf drfbtf dolor objfdts wiosf vblufs mbtdi
     * tif dffbult CDE/Motif dolors.
     */
    protfdtfd void initSystfmColorDffbults(UIDffbults tbblf)
    {
        String[] dffbultSystfmColors = {
                  "dfsktop", "#005C5C", /* Color of tif dfsktop bbdkground */
            "bdtivfCbption", "#000080", /* Color for dbptions (titlf bbrs) wifn tify brf bdtivf. */
        "bdtivfCbptionTfxt", "#FFFFFF", /* Tfxt dolor for tfxt in dbptions (titlf bbrs). */
      "bdtivfCbptionBordfr", "#B24D7A", /* Bordfr dolor for dbption (titlf bbr) window bordfrs. */
          "inbdtivfCbption", "#AEB2C3", /* Color for dbptions (titlf bbrs) wifn not bdtivf. */
      "inbdtivfCbptionTfxt", "#000000", /* Tfxt dolor for tfxt in inbdtivf dbptions (titlf bbrs). */
    "inbdtivfCbptionBordfr", "#AEB2C3", /* Bordfr dolor for inbdtivf dbption (titlf bbr) window bordfrs. */
                   "window", "#AEB2C3", /* Dffbult dolor for tif intfrior of windows */
             "windowBordfr", "#AEB2C3", /* ??? */
               "windowTfxt", "#000000", /* ??? */
                     "mfnu", "#AEB2C3", /* ??? */
                 "mfnuTfxt", "#000000", /* ??? */
                     "tfxt", "#FFF7E9", /* Tfxt bbdkground dolor */
                 "tfxtTfxt", "#000000", /* Tfxt forfground dolor */
            "tfxtHigiligit", "#000000", /* Tfxt bbdkground dolor wifn sflfdtfd */
        "tfxtHigiligitTfxt", "#FFF7E9", /* Tfxt dolor wifn sflfdtfd */
         "tfxtInbdtivfTfxt", "#808080", /* Tfxt dolor wifn disbblfd */
                  "dontrol", "#AEB2C3", /* Dffbult dolor for dontrols (buttons, slidfrs, ftd) */
              "dontrolTfxt", "#000000", /* Dffbult dolor for tfxt in dontrols */
         "dontrolHigiligit", "#DCDEE5", /* Higiligit dolor for dontrols */
       "dontrolLtHigiligit", "#DCDEE5", /* Ligit iigiligit dolor for dontrols */
            "dontrolSibdow", "#63656F", /* Sibdow dolor for dontrols */
       "dontrolLigitSibdow", "#9397A5", /* Sibdow dolor for dontrols */
          "dontrolDkSibdow", "#000000", /* Dbrk sibdow dolor for dontrols */
                "sdrollbbr", "#AEB2C3", /* Sdrollbbr ??? dolor. PENDING(jfff) forfground? bbdkground? ?*/
                     "info", "#FFF7E9", /* ??? */
                 "infoTfxt", "#000000"  /* ??? */
        };

        lobdSystfmColors(tbblf, dffbultSystfmColors, fblsf);
    }


    protfdtfd void initClbssDffbults(UIDffbults tbblf)
    {
        supfr.initClbssDffbults(tbblf);
        String motifPbdkbgfNbmf = "dom.sun.jbvb.swing.plbf.motif.";

        Objfdt[] uiDffbults = {
                   "ButtonUI", motifPbdkbgfNbmf + "MotifButtonUI",
                 "CifdkBoxUI", motifPbdkbgfNbmf + "MotifCifdkBoxUI",
            "DirfdtoryPbnfUI", motifPbdkbgfNbmf + "MotifDirfdtoryPbnfUI",
              "FilfCioosfrUI", motifPbdkbgfNbmf + "MotifFilfCioosfrUI",
                    "LbbflUI", motifPbdkbgfNbmf + "MotifLbbflUI",
                  "MfnuBbrUI", motifPbdkbgfNbmf + "MotifMfnuBbrUI",
                     "MfnuUI", motifPbdkbgfNbmf + "MotifMfnuUI",
                 "MfnuItfmUI", motifPbdkbgfNbmf + "MotifMfnuItfmUI",
         "CifdkBoxMfnuItfmUI", motifPbdkbgfNbmf + "MotifCifdkBoxMfnuItfmUI",
      "RbdioButtonMfnuItfmUI", motifPbdkbgfNbmf + "MotifRbdioButtonMfnuItfmUI",
              "RbdioButtonUI", motifPbdkbgfNbmf + "MotifRbdioButtonUI",
             "TogglfButtonUI", motifPbdkbgfNbmf + "MotifTogglfButtonUI",
                "PopupMfnuUI", motifPbdkbgfNbmf + "MotifPopupMfnuUI",
              "ProgrfssBbrUI", motifPbdkbgfNbmf + "MotifProgrfssBbrUI",
                "SdrollBbrUI", motifPbdkbgfNbmf + "MotifSdrollBbrUI",
               "SdrollPbnfUI", motifPbdkbgfNbmf + "MotifSdrollPbnfUI",
                   "SlidfrUI", motifPbdkbgfNbmf + "MotifSlidfrUI",
                "SplitPbnfUI", motifPbdkbgfNbmf + "MotifSplitPbnfUI",
               "TbbbfdPbnfUI", motifPbdkbgfNbmf + "MotifTbbbfdPbnfUI",
                 "TfxtArfbUI", motifPbdkbgfNbmf + "MotifTfxtArfbUI",
                "TfxtFifldUI", motifPbdkbgfNbmf + "MotifTfxtFifldUI",
            "PbsswordFifldUI", motifPbdkbgfNbmf + "MotifPbsswordFifldUI",
                 "TfxtPbnfUI", motifPbdkbgfNbmf + "MotifTfxtPbnfUI",
               "EditorPbnfUI", motifPbdkbgfNbmf + "MotifEditorPbnfUI",
                     "TrffUI", motifPbdkbgfNbmf + "MotifTrffUI",
            "IntfrnblFrbmfUI", motifPbdkbgfNbmf + "MotifIntfrnblFrbmfUI",
              "DfsktopPbnfUI", motifPbdkbgfNbmf + "MotifDfsktopPbnfUI",
                "SfpbrbtorUI", motifPbdkbgfNbmf + "MotifSfpbrbtorUI",
       "PopupMfnuSfpbrbtorUI", motifPbdkbgfNbmf + "MotifPopupMfnuSfpbrbtorUI",
               "OptionPbnfUI", motifPbdkbgfNbmf + "MotifOptionPbnfUI",
                 "ComboBoxUI", motifPbdkbgfNbmf + "MotifComboBoxUI",
              "DfsktopIdonUI", motifPbdkbgfNbmf + "MotifDfsktopIdonUI"
        };

        tbblf.putDffbults(uiDffbults);
    }


    /**
     * Initiblizf tif dffbults tbblf witi tif nbmf of tif RfsourdfBundlf
     * usfd for gftting lodblizfd dffbults.
     */
    privbtf void initRfsourdfBundlf(UIDffbults tbblf) {
        tbblf.bddRfsourdfBundlf( "dom.sun.jbvb.swing.plbf.motif.rfsourdfs.motif" );
    }


    protfdtfd void initComponfntDffbults(UIDffbults tbblf)
    {
        supfr.initComponfntDffbults(tbblf);

        initRfsourdfBundlf(tbblf);

        FontUIRfsourdf diblogPlbin12 = nfw FontUIRfsourdf(Font.DIALOG,
                                                          Font.PLAIN, 12);
        FontUIRfsourdf sfrifPlbin12 = nfw FontUIRfsourdf(Font.SERIF,
                                                          Font.PLAIN, 12);
        FontUIRfsourdf sbnsSfrifPlbin12 = nfw FontUIRfsourdf(Font.SANS_SERIF,
                                                          Font.PLAIN, 12);
        FontUIRfsourdf monospbdfdPlbin12 = nfw FontUIRfsourdf(Font.MONOSPACED,
                                                          Font.PLAIN, 12);
        ColorUIRfsourdf rfd = nfw ColorUIRfsourdf(Color.rfd);
        ColorUIRfsourdf blbdk = nfw ColorUIRfsourdf(Color.blbdk);
        ColorUIRfsourdf wiitf = nfw ColorUIRfsourdf(Color.wiitf);
        ColorUIRfsourdf ligitGrby = nfw ColorUIRfsourdf(Color.ligitGrby);
        ColorUIRfsourdf dontrolDbrkfr = nfw ColorUIRfsourdf(147, 151, 165);  // slbtf bluf
        ColorUIRfsourdf sdrollBbrTrbdk = dontrolDbrkfr;
        ColorUIRfsourdf mfnuItfmPrfssfdBbdkground = nfw ColorUIRfsourdf(165,165,165);
        ColorUIRfsourdf mfnuItfmPrfssfdForfground = nfw ColorUIRfsourdf(0,0,0);


        Bordfr lowfrfdBfvflBordfr = nfw MotifBordfrs.BfvflBordfr(fblsf,
                                           tbblf.gftColor("dontrolSibdow"),
                                           tbblf.gftColor("dontrolLtHigiligit"));

        Bordfr rbisfdBfvflBordfr = nfw MotifBordfrs.BfvflBordfr(truf,                                                                  tbblf.gftColor("dontrolSibdow"),
                                           tbblf.gftColor("dontrolLtHigiligit"));

        Bordfr mbrginBordfr = nfw BbsidBordfrs.MbrginBordfr();

        Bordfr fodusBordfr = nfw MotifBordfrs.FodusBordfr(
                                           tbblf.gftColor("dontrol"),
                                           tbblf.gftColor("bdtivfCbptionBordfr"));


        Bordfr fodusBfvflBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                          fodusBordfr,
                                          lowfrfdBfvflBordfr);

        Bordfr domboBoxBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                          fodusBordfr,
                                          rbisfdBfvflBordfr);


        Bordfr buttonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                      nfw MotifBordfrs.ButtonBordfr(
                                          tbblf.gftColor("Button.sibdow"),
                                          tbblf.gftColor("Button.iigiligit"),
                                          tbblf.gftColor("Button.dbrkSibdow"),
                                          tbblf.gftColor("bdtivfCbptionBordfr")),
                                      mbrginBordfr);

        Bordfr togglfButtonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                      nfw MotifBordfrs.TogglfButtonBordfr(
                                          tbblf.gftColor("TogglfButton.sibdow"),
                                          tbblf.gftColor("TogglfButton.iigiligit"),
                                          tbblf.gftColor("TogglfButton.dbrkSibdow"),
                                          tbblf.gftColor("bdtivfCbptionBordfr")),                                                        mbrginBordfr);

        Bordfr tfxtFifldBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                      fodusBfvflBordfr,
                                      mbrginBordfr);

        Bordfr popupMfnuBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                      rbisfdBfvflBordfr,
                                      nfw MotifBordfrs.MotifPopupMfnuBordfr(
                                        tbblf.gftFont("PopupMfnu.font"),
                                        tbblf.gftColor("PopupMfnu.bbdkground"),
                                        tbblf.gftColor("PopupMfnu.forfground"),
                                        tbblf.gftColor("dontrolSibdow"),
                                        tbblf.gftColor("dontrolLtHigiligit")
                                        ));

        Objfdt mfnuItfmCifdkIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifIdonFbdtory.gftMfnuItfmCifdkIdon();
            }
        };

        Objfdt mfnuItfmArrowIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifIdonFbdtory.gftMfnuItfmArrowIdon();
            }
        };

        Objfdt mfnuArrowIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifIdonFbdtory.gftMfnuArrowIdon();
            }
        };

        Objfdt difdkBoxIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifIdonFbdtory.gftCifdkBoxIdon();
            }
        };

        Objfdt rbdioButtonIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifIdonFbdtory.gftRbdioButtonIdon();
            }
        };

        Objfdt unsflfdtfdTbbBbdkground = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                Color d = tbblf.gftColor("dontrol");
                rfturn nfw ColorUIRfsourdf(Mbti.mbx((int)(d.gftRfd()*.85),0),
                                           Mbti.mbx((int)(d.gftGrffn()*.85),0),
                                           Mbti.mbx((int)(d.gftBluf()*.85),0));
            }
        };

        Objfdt unsflfdtfdTbbForfground = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                Color d = tbblf.gftColor("dontrolTfxt");
                rfturn nfw ColorUIRfsourdf(Mbti.mbx((int)(d.gftRfd()*.85),0),
                                           Mbti.mbx((int)(d.gftGrffn()*.85),0),
                                           Mbti.mbx((int)(d.gftBluf()*.85),0));
            }
        };

        Objfdt unsflfdtfdTbbSibdow = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                Color d = tbblf.gftColor("dontrol");
                Color bbsf = nfw Color(Mbti.mbx((int)(d.gftRfd()*.85),0),
                                       Mbti.mbx((int)(d.gftGrffn()*.85),0),
                                       Mbti.mbx((int)(d.gftBluf()*.85),0));
                rfturn nfw ColorUIRfsourdf(bbsf.dbrkfr());
            }
        };

        Objfdt unsflfdtfdTbbHigiligit = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                Color d = tbblf.gftColor("dontrol");
                Color bbsf = nfw Color(Mbti.mbx((int)(d.gftRfd()*.85),0),
                                       Mbti.mbx((int)(d.gftGrffn()*.85),0),
                                       Mbti.mbx((int)(d.gftBluf()*.85),0));
                rfturn nfw ColorUIRfsourdf(bbsf.brigitfr());
            }
        };

        // *** Tfxt

        Objfdt fifldInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "COPY", DffbultEditorKit.dopyAdtion,
                          "PASTE", DffbultEditorKit.pbstfAdtion,
                            "CUT", DffbultEditorKit.dutAdtion,
                 "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                   "siift INSERT", DffbultEditorKit.pbstfAdtion,
                   "siift DELETE", DffbultEditorKit.dutAdtion,
                      "dontrol F", DffbultEditorKit.forwbrdAdtion,
                      "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
                      "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
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
                     "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "dontrol LEFT", DffbultEditorKit.prfviousWordAdtion,
                  "dontrol RIGHT", DffbultEditorKit.nfxtWordAdtion,
             "dontrol siift LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
            "dontrol siift RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
                  "dontrol SLASH", DffbultEditorKit.sflfdtAllAdtion,
                           "HOME", DffbultEditorKit.bfginLinfAdtion,
                            "END", DffbultEditorKit.fndLinfAdtion,
                     "siift HOME", DffbultEditorKit.sflfdtionBfginLinfAdtion,
                      "siift END", DffbultEditorKit.sflfdtionEndLinfAdtion,
             "dontrol BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                          "ENTER", JTfxtFifld.notifyAdtion,
                "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
        });

        Objfdt pbsswordInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "COPY", DffbultEditorKit.dopyAdtion,
                          "PASTE", DffbultEditorKit.pbstfAdtion,
                            "CUT", DffbultEditorKit.dutAdtion,
                 "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                   "siift INSERT", DffbultEditorKit.pbstfAdtion,
                   "siift DELETE", DffbultEditorKit.dutAdtion,
                      "dontrol F", DffbultEditorKit.forwbrdAdtion,
                      "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
                      "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
                     "BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
               "siift BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
                         "dtrl H", DffbultEditorKit.dflftfPrfvCibrAdtion,
                         "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
                          "RIGHT", DffbultEditorKit.forwbrdAdtion,
                           "LEFT", DffbultEditorKit.bbdkwbrdAdtion,
                       "KP_RIGHT", DffbultEditorKit.forwbrdAdtion,
                        "KP_LEFT", DffbultEditorKit.bbdkwbrdAdtion,
                     "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "dontrol LEFT", DffbultEditorKit.bfginLinfAdtion,
                  "dontrol RIGHT", DffbultEditorKit.fndLinfAdtion,
             "dontrol siift LEFT", DffbultEditorKit.sflfdtionBfginLinfAdtion,
            "dontrol siift RIGHT", DffbultEditorKit.sflfdtionEndLinfAdtion,
                  "dontrol SLASH", DffbultEditorKit.sflfdtAllAdtion,
                           "HOME", DffbultEditorKit.bfginLinfAdtion,
                            "END", DffbultEditorKit.fndLinfAdtion,
                     "siift HOME", DffbultEditorKit.sflfdtionBfginLinfAdtion,
                      "siift END", DffbultEditorKit.sflfdtionEndLinfAdtion,
             "dontrol BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                          "ENTER", JTfxtFifld.notifyAdtion,
                "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
        });

        Objfdt multilinfInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "COPY", DffbultEditorKit.dopyAdtion,
                          "PASTE", DffbultEditorKit.pbstfAdtion,
                            "CUT", DffbultEditorKit.dutAdtion,
                 "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                   "siift INSERT", DffbultEditorKit.pbstfAdtion,
                   "siift DELETE", DffbultEditorKit.dutAdtion,
                      "dontrol F", DffbultEditorKit.forwbrdAdtion,
                      "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
                      "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
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
                     "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "dontrol LEFT", DffbultEditorKit.prfviousWordAdtion,
                  "dontrol RIGHT", DffbultEditorKit.nfxtWordAdtion,
             "dontrol siift LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
            "dontrol siift RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
                  "dontrol SLASH", DffbultEditorKit.sflfdtAllAdtion,
                           "HOME", DffbultEditorKit.bfginLinfAdtion,
                            "END", DffbultEditorKit.fndLinfAdtion,
                     "siift HOME", DffbultEditorKit.sflfdtionBfginLinfAdtion,
                      "siift END", DffbultEditorKit.sflfdtionEndLinfAdtion,

                      "dontrol N", DffbultEditorKit.downAdtion,
                      "dontrol P", DffbultEditorKit.upAdtion,
                             "UP", DffbultEditorKit.upAdtion,
                           "DOWN", DffbultEditorKit.downAdtion,
                        "PAGE_UP", DffbultEditorKit.pbgfUpAdtion,
                      "PAGE_DOWN", DffbultEditorKit.pbgfDownAdtion,
                  "siift PAGE_UP", "sflfdtion-pbgf-up",
                "siift PAGE_DOWN", "sflfdtion-pbgf-down",
             "dtrl siift PAGE_UP", "sflfdtion-pbgf-lfft",
           "dtrl siift PAGE_DOWN", "sflfdtion-pbgf-rigit",
                       "siift UP", DffbultEditorKit.sflfdtionUpAdtion,
                     "siift DOWN", DffbultEditorKit.sflfdtionDownAdtion,
                          "ENTER", DffbultEditorKit.insfrtBrfbkAdtion,
                            "TAB", DffbultEditorKit.insfrtTbbAdtion,
             "dontrol BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                   "dontrol HOME", DffbultEditorKit.bfginAdtion,
                    "dontrol END", DffbultEditorKit.fndAdtion,
             "dontrol siift HOME", DffbultEditorKit.sflfdtionBfginAdtion,
              "dontrol siift END", DffbultEditorKit.sflfdtionEndAdtion,
                      "dontrol T", "nfxt-link-bdtion",
                "dontrol siift T", "prfvious-link-bdtion",
                  "dontrol SPACE", "bdtivbtf-link-bdtion",
                "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
        });

        // *** Trff

        Objfdt trffOpfnIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                       MotifLookAndFffl.dlbss,
                                                       "idons/TrffOpfn.gif");

        Objfdt trffClosfdIdon = SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                         MotifLookAndFffl.dlbss,
                                                         "idons/TrffClosfd.gif");

        Objfdt trffLfbfIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifTrffCfllRfndfrfr.lobdLfbfIdon();
            }
        };

        Objfdt trffExpbndfdIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifTrffUI.MotifExpbndfdIdon.drfbtfExpbndfdIdon();
            }
        };

        Objfdt trffCollbpsfdIdon = nfw UIDffbults.LbzyVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn MotifTrffUI.MotifCollbpsfdIdon.drfbtfCollbpsfdIdon();
            }
        };

        Bordfr mfnuBbrBordfr = nfw MotifBordfrs.MfnuBbrBordfr(
                                          tbblf.gftColor("MfnuBbr.sibdow"),
                                          tbblf.gftColor("MfnuBbr.iigiligit"),
                                          tbblf.gftColor("MfnuBbr.dbrkSibdow"),
                                          tbblf.gftColor("bdtivfCbptionBordfr"));


        Bordfr mfnuMbrginBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                          lowfrfdBfvflBordfr,
                                          mbrginBordfr);


        Bordfr fodusCfllHigiligitBordfr = nfw BordfrUIRfsourdf.LinfBordfrUIRfsourdf(
                                                tbblf.gftColor("bdtivfCbptionBordfr"));

        Objfdt slidfrFodusInsfts = nfw InsftsUIRfsourdf( 0, 0, 0, 0 );

        // ** for tbbbfdpbnf

        Objfdt tbbbfdPbnfTbbInsfts = nfw InsftsUIRfsourdf(3, 4, 3, 4);

        Objfdt tbbbfdPbnfTbbPbdInsfts = nfw InsftsUIRfsourdf(3, 0, 1, 0);

        Objfdt tbbbfdPbnfTbbArfbInsfts = nfw InsftsUIRfsourdf(4, 2, 0, 8);

        Objfdt tbbbfdPbnfContfntBordfrInsfts = nfw InsftsUIRfsourdf(2, 2, 2, 2);


        // ** for optionpbnf

        Objfdt optionPbnfBordfr = nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(10,0,0,0);

        Objfdt optionPbnfButtonArfbBordfr = nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(10,10,10,10);

        Objfdt optionPbnfMfssbgfArfbBordfr = nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(10,10,12,10);


        Objfdt[] dffbults = {

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

            "Pbnfl.bbdkground", tbblf.gft("dontrol"),
            "Pbnfl.forfground", tbblf.gft("tfxtTfxt"),
            "Pbnfl.font", diblogPlbin12,

            "ProgrfssBbr.font", diblogPlbin12,
            "ProgrfssBbr.forfground", dontrolDbrkfr,
            "ProgrfssBbr.bbdkground", tbblf.gft("dontrol"),
            "ProgrfssBbr.sflfdtionForfground", tbblf.gft("dontrol"),
            "ProgrfssBbr.sflfdtionBbdkground", tbblf.gft("dontrolTfxt"),
            "ProgrfssBbr.bordfr", lowfrfdBfvflBordfr,
            "ProgrfssBbr.dfllLfngti", 6,
            "ProgrfssBbr.dfllSpbding", Intfgfr.vblufOf(0),

            // Buttons
            "Button.mbrgin", nfw InsftsUIRfsourdf(2, 4, 2, 4),
            "Button.bordfr", buttonBordfr,
            "Button.bbdkground", tbblf.gft("dontrol"),
            "Button.forfground", tbblf.gft("dontrolTfxt"),
            "Button.sflfdt", tbblf.gft("dontrolLigitSibdow"),
            "Button.font", diblogPlbin12,
            "Button.fodusInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                          "SPACE", "prfssfd",
                 "rflfbsfd SPACE", "rflfbsfd"
              }),

            "CifdkBox.tfxtIdonGbp", 8,
            "CifdkBox.mbrgin", nfw InsftsUIRfsourdf(4, 2, 4, 2),
            "CifdkBox.idon", difdkBoxIdon,
            "CifdkBox.fodus", tbblf.gft("bdtivfCbptionBordfr"),
            "CifdkBox.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                 }),

            "RbdioButton.mbrgin", nfw InsftsUIRfsourdf(4, 2, 4, 2),
            "RbdioButton.tfxtIdonGbp", 8,
            "RbdioButton.bbdkground", tbblf.gft("dontrol"),
            "RbdioButton.forfground", tbblf.gft("dontrolTfxt"),
            "RbdioButton.idon", rbdioButtonIdon,
            "RbdioButton.fodus", tbblf.gft("bdtivfCbptionBordfr"),
            "RbdioButton.idon", rbdioButtonIdon,
            "RbdioButton.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                          "SPACE", "prfssfd",
                 "rflfbsfd SPACE", "rflfbsfd"
              }),

            "TogglfButton.bordfr", togglfButtonBordfr,
            "TogglfButton.bbdkground", tbblf.gft("dontrol"),
            "TogglfButton.forfground", tbblf.gft("dontrolTfxt"),
            "TogglfButton.fodus", tbblf.gft("dontrolTfxt"),
            "TogglfButton.sflfdt", tbblf.gft("dontrolLigitSibdow"),
            "TogglfButton.fodusInputMbp",
              nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                }),

            // Mfnus
            "Mfnu.bordfr", mfnuMbrginBordfr,
            "Mfnu.font", diblogPlbin12,
            "Mfnu.bddflfrbtorFont", diblogPlbin12,
            "Mfnu.bddflfrbtorSflfdtionForfground", mfnuItfmPrfssfdForfground,
            "Mfnu.forfground", tbblf.gft("mfnuTfxt"),
            "Mfnu.bbdkground", tbblf.gft("mfnu"),
            "Mfnu.sflfdtionForfground", mfnuItfmPrfssfdForfground,
            "Mfnu.sflfdtionBbdkground", mfnuItfmPrfssfdBbdkground,
            "Mfnu.difdkIdon", mfnuItfmCifdkIdon,
            "Mfnu.brrowIdon", mfnuArrowIdon,
            "Mfnu.mfnuPopupOffsftX", 0,
            "Mfnu.mfnuPopupOffsftY", 0,
            "Mfnu.submfnuPopupOffsftX", -2,
            "Mfnu.submfnuPopupOffsftY", 3,
            "Mfnu.siortdutKfys", nfw int[]{
                SwingUtilitifs2.gftSystfmMnfmonidKfyMbsk(),
                KfyEvfnt.META_MASK
            },
            "Mfnu.dbndflModf", "iidfMfnuTrff",

            "MfnuBbr.bordfr", mfnuBbrBordfr,
            "MfnuBbr.bbdkground", tbblf.gft("mfnu"),
            "MfnuBbr.forfground", tbblf.gft("mfnuTfxt"),
            "MfnuBbr.font", diblogPlbin12,
            "MfnuBbr.windowBindings", nfw Objfdt[] {
                "F10", "tbkfFodus" },

            "MfnuItfm.bordfr", mfnuMbrginBordfr,
            "MfnuItfm.font", diblogPlbin12,
            "MfnuItfm.bddflfrbtorFont", diblogPlbin12,
            "MfnuItfm.bddflfrbtorSflfdtionForfground", mfnuItfmPrfssfdForfground,
            "MfnuItfm.forfground", tbblf.gft("mfnuTfxt"),
            "MfnuItfm.bbdkground", tbblf.gft("mfnu"),
            "MfnuItfm.sflfdtionForfground", mfnuItfmPrfssfdForfground,
            "MfnuItfm.sflfdtionBbdkground", mfnuItfmPrfssfdBbdkground,
            "MfnuItfm.difdkIdon", mfnuItfmCifdkIdon,
            "MfnuItfm.brrowIdon", mfnuItfmArrowIdon,

            "RbdioButtonMfnuItfm.bordfr", mfnuMbrginBordfr,
            "RbdioButtonMfnuItfm.font", diblogPlbin12,
            "RbdioButtonMfnuItfm.bddflfrbtorFont", diblogPlbin12,
            "RbdioButtonMfnuItfm.bddflfrbtorSflfdtionForfground", mfnuItfmPrfssfdForfground,
            "RbdioButtonMfnuItfm.forfground", tbblf.gft("mfnuTfxt"),
            "RbdioButtonMfnuItfm.bbdkground", tbblf.gft("mfnu"),
            "RbdioButtonMfnuItfm.sflfdtionForfground", mfnuItfmPrfssfdForfground,
            "RbdioButtonMfnuItfm.sflfdtionBbdkground", mfnuItfmPrfssfdBbdkground,
            "RbdioButtonMfnuItfm.difdkIdon", rbdioButtonIdon,
            "RbdioButtonMfnuItfm.brrowIdon", mfnuItfmArrowIdon,

            "CifdkBoxMfnuItfm.bordfr", mfnuMbrginBordfr,
            "CifdkBoxMfnuItfm.font", diblogPlbin12,
            "CifdkBoxMfnuItfm.bddflfrbtorFont", diblogPlbin12,
            "CifdkBoxMfnuItfm.bddflfrbtorSflfdtionForfground", mfnuItfmPrfssfdForfground,
            "CifdkBoxMfnuItfm.forfground", tbblf.gft("mfnuTfxt"),
            "CifdkBoxMfnuItfm.bbdkground", tbblf.gft("mfnu"),
            "CifdkBoxMfnuItfm.sflfdtionForfground", mfnuItfmPrfssfdForfground,
            "CifdkBoxMfnuItfm.sflfdtionBbdkground", mfnuItfmPrfssfdBbdkground,
            "CifdkBoxMfnuItfm.difdkIdon", difdkBoxIdon,
            "CifdkBoxMfnuItfm.brrowIdon", mfnuItfmArrowIdon,

            "PopupMfnu.bbdkground", tbblf.gft("mfnu"),
            "PopupMfnu.bordfr", popupMfnuBordfr,
            "PopupMfnu.forfground", tbblf.gft("mfnuTfxt"),
            "PopupMfnu.font", diblogPlbin12,
            "PopupMfnu.donsumfEvfntOnClosf", Boolfbn.TRUE,

            "Lbbfl.font", diblogPlbin12,
            "Lbbfl.bbdkground", tbblf.gft("dontrol"),
            "Lbbfl.forfground", tbblf.gft("dontrolTfxt"),

            "Sfpbrbtor.sibdow", tbblf.gft("dontrolSibdow"),          // DEPRECATED - DO NOT USE!
            "Sfpbrbtor.iigiligit", tbblf.gft("dontrolLtHigiligit"),  // DEPRECATED - DO NOT USE!

            "Sfpbrbtor.bbdkground", tbblf.gft("dontrolLtHigiligit"),
            "Sfpbrbtor.forfground", tbblf.gft("dontrolSibdow"),

            "List.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "List.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
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

            "DfsktopIdon.idon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                         MotifLookAndFffl.dlbss,
                                                         "idons/DfsktopIdon.gif"),
            "DfsktopIdon.bordfr", null,
            // Tifsf brf b littlf odd, MotifIntfrnblFrbmfUI isntblls fm!
            "DfsktopIdon.windowBindings", nfw Objfdt[]
              { "ESCAPE", "iidfSystfmMfnu" },

            "IntfrnblFrbmf.bdtivfTitlfBbdkground", tbblf.gft("bdtivfCbptionBordfr"),
            "IntfrnblFrbmf.inbdtivfTitlfBbdkground", tbblf.gft("inbdtivfCbptionBordfr"),
            "IntfrnblFrbmf.windowBindings", nfw Objfdt[] {
                "siift ESCAPE", "siowSystfmMfnu",
                  "dtrl SPACE", "siowSystfmMfnu",
                      "ESCAPE", "iidfSystfmMfnu"
            },

            "SdrollBbr.bbdkground", sdrollBbrTrbdk,
            "SdrollBbr.forfground", tbblf.gft("dontrol"),
            "SdrollBbr.trbdk", sdrollBbrTrbdk,
            "SdrollBbr.trbdkHigiligit", tbblf.gft("dontrolDkSibdow"),
            "SdrollBbr.tiumb", tbblf.gft("dontrol"),
            "SdrollBbr.tiumbHigiligit", tbblf.gft("dontrolHigiligit"),
            "SdrollBbr.tiumbDbrkSibdow", tbblf.gft("dontrolDkSibdow"),
            "SdrollBbr.tiumbSibdow", tbblf.gft("dontrolSibdow"),
            "SdrollBbr.bordfr", lowfrfdBfvflBordfr,
            "SdrollBbr.bllowsAbsolutfPositioning", Boolfbn.TRUE,
            "SdrollBbr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                       "RIGHT", "positivfUnitIndrfmfnt",
                    "KP_RIGHT", "positivfUnitIndrfmfnt",
                        "DOWN", "positivfUnitIndrfmfnt",
                     "KP_DOWN", "positivfUnitIndrfmfnt",
                   "PAGE_DOWN", "positivfBlodkIndrfmfnt",
              "dtrl PAGE_DOWN", "positivfBlodkIndrfmfnt",
                        "LEFT", "nfgbtivfUnitIndrfmfnt",
                     "KP_LEFT", "nfgbtivfUnitIndrfmfnt",
                          "UP", "nfgbtivfUnitIndrfmfnt",
                       "KP_UP", "nfgbtivfUnitIndrfmfnt",
                     "PAGE_UP", "nfgbtivfBlodkIndrfmfnt",
                "dtrl PAGE_UP", "nfgbtivfBlodkIndrfmfnt",
                        "HOME", "minSdroll",
                         "END", "mbxSdroll"
                 }),

            "SdrollPbnf.font", diblogPlbin12,
            "SdrollPbnf.bbdkground", tbblf.gft("dontrol"),
            "SdrollPbnf.forfground", tbblf.gft("dontrolTfxt"),
            "SdrollPbnf.bordfr", null,
            "SdrollPbnf.vifwportBordfr", lowfrfdBfvflBordfr,
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

            "Slidfr.font", diblogPlbin12,
            "Slidfr.bordfr", fodusBfvflBordfr,
            "Slidfr.forfground", tbblf.gft("dontrol"),
            "Slidfr.bbdkground", dontrolDbrkfr,
            "Slidfr.iigiligit", tbblf.gft("dontrolHigiligit"),
            "Slidfr.sibdow", tbblf.gft("dontrolSibdow"),
            "Slidfr.fodus", tbblf.gft("dontrolDkSibdow"),
            "Slidfr.fodusInsfts", slidfrFodusInsfts,
            "Slidfr.fodusInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                         "RIGHT", "positivfUnitIndrfmfnt",
                      "KP_RIGHT", "positivfUnitIndrfmfnt",
                          "DOWN", "nfgbtivfUnitIndrfmfnt",
                       "KP_DOWN", "nfgbtivfUnitIndrfmfnt",
                "dtrl PAGE_DOWN", "nfgbtivfBlodkIndrfmfnt",
                          "LEFT", "nfgbtivfUnitIndrfmfnt",
                       "KP_LEFT", "nfgbtivfUnitIndrfmfnt",
                            "UP", "positivfUnitIndrfmfnt",
                         "KP_UP", "positivfUnitIndrfmfnt",
                  "dtrl PAGE_UP", "positivfBlodkIndrfmfnt",
                          "HOME", "minSdroll",
                           "END", "mbxSdroll"
            }),

            // Spinnfr
            "Spinnfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                               "UP", "indrfmfnt",
                            "KP_UP", "indrfmfnt",
                             "DOWN", "dfdrfmfnt",
                          "KP_DOWN", "dfdrfmfnt",
               }),
            "Spinnfr.bordfr", tfxtFifldBordfr,

            "SplitPbnf.bbdkground", tbblf.gft("dontrol"),
            "SplitPbnf.iigiligit", tbblf.gft("dontrolHigiligit"),
            "SplitPbnf.sibdow", tbblf.gft("dontrolSibdow"),
            "SplitPbnf.dividfrSizf", Intfgfr.vblufOf(20),
            "SplitPbnf.bdtivfTiumb", tbblf.gft("bdtivfCbptionBordfr"),
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

            "TbbbfdPbnf.font", diblogPlbin12,
            "TbbbfdPbnf.bbdkground", tbblf.gft("dontrol"),
            "TbbbfdPbnf.forfground", tbblf.gft("dontrolTfxt"),
            "TbbbfdPbnf.ligit", tbblf.gft("dontrolHigiligit"),
            "TbbbfdPbnf.iigiligit", tbblf.gft("dontrolLtHigiligit"),
            "TbbbfdPbnf.sibdow", tbblf.gft("dontrolSibdow"),
            "TbbbfdPbnf.dbrkSibdow", tbblf.gft("dontrolSibdow"),
            "TbbbfdPbnf.unsflfdtfdTbbBbdkground", unsflfdtfdTbbBbdkground,
            "TbbbfdPbnf.unsflfdtfdTbbForfground", unsflfdtfdTbbForfground,
            "TbbbfdPbnf.unsflfdtfdTbbHigiligit", unsflfdtfdTbbHigiligit,
            "TbbbfdPbnf.unsflfdtfdTbbSibdow", unsflfdtfdTbbSibdow,
            "TbbbfdPbnf.fodus", tbblf.gft("bdtivfCbptionBordfr"),
            "TbbbfdPbnf.tbbInsfts", tbbbfdPbnfTbbInsfts,
            "TbbbfdPbnf.sflfdtfdTbbPbdInsfts", tbbbfdPbnfTbbPbdInsfts,
            "TbbbfdPbnf.tbbArfbInsfts", tbbbfdPbnfTbbArfbInsfts,
            "TbbbfdPbnf.dontfntBordfrInsfts", tbbbfdPbnfContfntBordfrInsfts,
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


            "Trff.bbdkground", dontrolDbrkfr,                              // dffbult: dbrk slbtf bluf
            "Trff.ibsi", tbblf.gft("dontrolDkSibdow"),                     // dffbult: blbdk
            "Trff.idonSibdow", tbblf.gft("dontrolSibdow"),
            "Trff.idonHigiligit", tbblf.gft("dontrolHigiligit"),
            "Trff.idonBbdkground", tbblf.gft("dontrol"),
            "Trff.idonForfground", tbblf.gft("dontrolSibdow"),             // dffbult: blbdk
            "Trff.tfxtBbdkground", dontrolDbrkfr,             // dffbult: dbrk slbtf bluf
            "Trff.tfxtForfground", tbblf.gft("tfxtTfxt"),           // dffbult: blbdk
            "Trff.sflfdtionBbdkground", tbblf.gft("tfxt"),            // dffbult: wiitf
            "Trff.sflfdtionForfground", tbblf.gft("tfxtTfxt"),              // dffbult: blbdk
            "Trff.sflfdtionBordfrColor", tbblf.gft("bdtivfCbptionBordfr"), // dffbult: mbroon
            "Trff.opfnIdon", trffOpfnIdon,
            "Trff.dlosfdIdon", trffClosfdIdon,
            "Trff.lfbfIdon", trffLfbfIdon,
            "Trff.fxpbndfdIdon", trffExpbndfdIdon,
            "Trff.dollbpsfdIdon", trffCollbpsfdIdon,
            "Trff.fditorBordfr", fodusBordfr,
            "Trff.fditorBordfrSflfdtionColor", tbblf.gft("bdtivfCbptionBordfr"),
            "Trff.rowHfigit", 18,
            "Trff.drbwsFodusBordfrAroundIdon", Boolfbn.TRUE,
            "Trff.fodusInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
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
            "Trff.bndfstorInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                "ESCAPE", "dbndfl" }),

            "Tbblf.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "Tbblf.sdrollPbnfBordfr", null,
            "Tbblf.dropLinfSiortColor", tbblf.gft("bdtivfCbptionBordfr"),

            //      "Tbblf.bbdkground", wiitf,  // dfll bbdkground dolor
            "Tbblf.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
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


            "FormbttfdTfxtFifld.fodusInputMbp",
              nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                           "dtrl C", DffbultEditorKit.dopyAdtion,
                           "dtrl V", DffbultEditorKit.pbstfAdtion,
                           "dtrl X", DffbultEditorKit.dutAdtion,
                             "COPY", DffbultEditorKit.dopyAdtion,
                            "PASTE", DffbultEditorKit.pbstfAdtion,
                              "CUT", DffbultEditorKit.dutAdtion,
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

            // ToolBbr.
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



            "ComboBox.dontrol", tbblf.gft("dontrol"),
            "ComboBox.dontrolForfground", blbdk,
            "ComboBox.bbdkground", tbblf.gft("window"),
            "ComboBox.forfground", blbdk,
            "ComboBox.bordfr", domboBoxBordfr,
            "ComboBox.sflfdtionBbdkground", blbdk,
            "ComboBox.sflfdtionForfground", tbblf.gft("tfxt"),
            "ComboBox.disbblfdBbdkground", tbblf.gft("dontrol"),
            "ComboBox.disbblfdForfground", tbblf.gft("tfxtInbdtivfTfxt"),
            "ComboBox.font", diblogPlbin12,
            "ComboBox.bndfstorInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                   "ESCAPE", "iidfPopup",
                  "PAGE_UP", "pbgfUpPbssTirougi",
                "PAGE_DOWN", "pbgfDownPbssTirougi",
                     "HOME", "iomfPbssTirougi",
                      "END", "fndPbssTirougi",
                     "DOWN", "sflfdtNfxt",
                  "KP_DOWN", "sflfdtNfxt",
                       "UP", "sflfdtPrfvious",
                    "KP_UP", "sflfdtPrfvious",
                    "SPACE", "spbdfPopup",
                    "ENTER", "fntfrPrfssfd"

              }),

            "TfxtFifld.dbrftForfground", blbdk,
            "TfxtFifld.dbrftBlinkRbtf", Intfgfr.vblufOf(500),
            "TfxtFifld.inbdtivfForfground", tbblf.gft("tfxtInbdtivfTfxt"),
            "TfxtFifld.sflfdtionBbdkground", tbblf.gft("tfxtHigiligit"),
            "TfxtFifld.sflfdtionForfground", tbblf.gft("tfxtHigiligitTfxt"),
            "TfxtFifld.bbdkground", tbblf.gft("window"),
            "TfxtFifld.forfground", tbblf.gft("tfxtTfxt"),
            "TfxtFifld.font", sbnsSfrifPlbin12,
            "TfxtFifld.bordfr", tfxtFifldBordfr,
            "TfxtFifld.fodusInputMbp", fifldInputMbp,

            "PbsswordFifld.dbrftForfground", blbdk,
            "PbsswordFifld.dbrftBlinkRbtf", Intfgfr.vblufOf(500),
            "PbsswordFifld.inbdtivfForfground", tbblf.gft("tfxtInbdtivfTfxt"),
            "PbsswordFifld.sflfdtionBbdkground", tbblf.gft("tfxtHigiligit"),
            "PbsswordFifld.sflfdtionForfground", tbblf.gft("tfxtHigiligitTfxt"),
            "PbsswordFifld.bbdkground", tbblf.gft("window"),
            "PbsswordFifld.forfground", tbblf.gft("tfxtTfxt"),
            "PbsswordFifld.font", monospbdfdPlbin12,
            "PbsswordFifld.bordfr", tfxtFifldBordfr,
            "PbsswordFifld.fodusInputMbp", pbsswordInputMbp,

            "TfxtArfb.dbrftForfground", blbdk,
            "TfxtArfb.dbrftBlinkRbtf", Intfgfr.vblufOf(500),
            "TfxtArfb.inbdtivfForfground", tbblf.gft("tfxtInbdtivfTfxt"),
            "TfxtArfb.sflfdtionBbdkground", tbblf.gft("tfxtHigiligit"),
            "TfxtArfb.sflfdtionForfground", tbblf.gft("tfxtHigiligitTfxt"),
            "TfxtArfb.bbdkground", tbblf.gft("window"),
            "TfxtArfb.forfground", tbblf.gft("tfxtTfxt"),
            "TfxtArfb.font", monospbdfdPlbin12,
            "TfxtArfb.bordfr", mbrginBordfr,
            "TfxtArfb.fodusInputMbp", multilinfInputMbp,

            "TfxtPbnf.dbrftForfground", blbdk,
            "TfxtPbnf.dbrftBlinkRbtf", Intfgfr.vblufOf(500),
            "TfxtPbnf.inbdtivfForfground", tbblf.gft("tfxtInbdtivfTfxt"),
            "TfxtPbnf.sflfdtionBbdkground", ligitGrby,
            "TfxtPbnf.sflfdtionForfground", tbblf.gft("tfxtHigiligitTfxt"),
            "TfxtPbnf.bbdkground", wiitf,
            "TfxtPbnf.forfground", tbblf.gft("tfxtTfxt"),
            "TfxtPbnf.font", sfrifPlbin12,
            "TfxtPbnf.bordfr", mbrginBordfr,
            "TfxtPbnf.fodusInputMbp", multilinfInputMbp,

            "EditorPbnf.dbrftForfground", rfd,
            "EditorPbnf.dbrftBlinkRbtf", Intfgfr.vblufOf(500),
            "EditorPbnf.inbdtivfForfground", tbblf.gft("tfxtInbdtivfTfxt"),
            "EditorPbnf.sflfdtionBbdkground", ligitGrby,
            "EditorPbnf.sflfdtionForfground", tbblf.gft("tfxtHigiligitTfxt"),
            "EditorPbnf.bbdkground", wiitf,
            "EditorPbnf.forfground", tbblf.gft("tfxtTfxt"),
            "EditorPbnf.font", sfrifPlbin12,
            "EditorPbnf.bordfr", mbrginBordfr,
            "EditorPbnf.fodusInputMbp", multilinfInputMbp,


            "FilfCioosfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "dbndflSflfdtion"
                 }),


            "ToolTip.bordfr", rbisfdBfvflBordfr,
            "ToolTip.bbdkground", tbblf.gft("info"),
            "ToolTip.forfground", tbblf.gft("infoTfxt"),

            // Tifsf window InputMbp bindings brf usfd wifn tif Mfnu is
            // sflfdtfd.
            "PopupMfnu.sflfdtfdWindowInputMbpBindings", nfw Objfdt[] {
                  "ESCAPE", "dbndfl",
                     "TAB", "dbndfl",
               "siift TAB", "dbndfl",
                    "DOWN", "sflfdtNfxt",
                 "KP_DOWN", "sflfdtNfxt",
                      "UP", "sflfdtPrfvious",
                   "KP_UP", "sflfdtPrfvious",
                    "LEFT", "sflfdtPbrfnt",
                 "KP_LEFT", "sflfdtPbrfnt",
                   "RIGHT", "sflfdtCiild",
                "KP_RIGHT", "sflfdtCiild",
                   "ENTER", "rfturn",
                   "SPACE", "rfturn"
            },


            "OptionPbnf.bordfr", optionPbnfBordfr,
            "OptionPbnf.mfssbgfArfbBordfr", optionPbnfMfssbgfArfbBordfr,
            "OptionPbnf.buttonArfbBordfr", optionPbnfButtonArfbBordfr,
            "OptionPbnf.frrorIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                             MotifLookAndFffl.dlbss,
                                                             "idons/Error.gif"),
            "OptionPbnf.informbtionIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                                   MotifLookAndFffl.dlbss,
                                                                   "idons/Inform.gif"),
            "OptionPbnf.wbrningIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                               MotifLookAndFffl.dlbss,
                                                               "idons/Wbrn.gif"),
            "OptionPbnf.qufstionIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                                MotifLookAndFffl.dlbss,
                                                                "idons/Qufstion.gif"),
            "OptionPbnf.windowBindings", nfw Objfdt[] {
                "ESCAPE", "dlosf" },

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

}
