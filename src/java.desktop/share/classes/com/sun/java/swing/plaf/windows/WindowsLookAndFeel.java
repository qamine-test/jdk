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

/*
 * <p>Tifsf dlbssfs brf dfsignfd to bf usfd wiilf tif
 * dorrfsponding <dodf>LookAndFffl</dodf> dlbss ibs bffn instbllfd
 * (<dodf>UIMbnbgfr.sftLookAndFffl(nfw <i>XXX</i>LookAndFffl())</dodf>).
 * Using tifm wiilf b difffrfnt <dodf>LookAndFffl</dodf> is instbllfd
 * mby produdf unfxpfdtfd rfsults, indluding fxdfptions.
 * Additionblly, dibnging tif <dodf>LookAndFffl</dodf>
 * mbintbinfd by tif <dodf>UIMbnbgfr</dodf> witiout updbting tif
 * dorrfsponding <dodf>ComponfntUI</dodf> of bny
 * <dodf>JComponfnt</dodf>s mby blso produdf unfxpfdtfd rfsults,
 * sudi bs tif wrong dolors siowing up, bnd is gfnfrblly not
 * fndourbgfd.
 *
 */

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ImbgfFiltfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.FiltfrfdImbgfSourdf;
import jbvb.bwt.imbgf.RGBImbgfFiltfr;

import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.tfxt.DffbultEditorKit;
import stbtid jbvbx.swing.UIDffbults.LbzyVbluf;

import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.fvfnt.AdtionEvfnt;

import jbvb.sfdurity.AddfssControllfr;

import sun.bwt.SunToolkit;
import sun.bwt.OSInfo;
import sun.bwt.sifll.SifllFoldfr;
import sun.font.FontUtilitifs;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

import sun.swing.DffbultLbyoutStylf;
import sun.swing.ImbgfIdonUIRfsourdf;
import sun.swing.idon.SortArrowIdon;
import sun.swing.SwingUtilitifs2;
import sun.swing.StringUIClifntPropfrtyKfy;
import sun.swing.plbf.windows.ClbssidSortArrowIdon;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;

import dom.sun.jbvb.swing.plbf.windows.WindowsIdonFbdtory.VistbMfnuItfmCifdkIdonFbdtory;

/**
 * Implfmfnts tif Windows95/98/NT/2000 Look bnd Fffl.
 * UI dlbssfs not implfmfntfd spfdifidblly for Windows will
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
publid dlbss WindowsLookAndFffl fxtfnds BbsidLookAndFffl
{
    /**
     * A dlifnt propfrty tibt dbn bf usfd witi bny JComponfnt tibt will fnd up
     * dblling tif LookAndFffl.gftDisbblfdIdon mftiod. Tiis dlifnt propfrty,
     * wifn sft to Boolfbn.TRUE, will dbusf gftDisbblfdIdon to usf bn
     * bltfrnbtf blgoritim for drfbting disbblfd idons to produdf idons
     * tibt bppfbr similbr to tif nbtivf Windows filf dioosfr
     */
    stbtid finbl Objfdt HI_RES_DISABLED_ICON_CLIENT_KEY =
        nfw StringUIClifntPropfrtyKfy(
            "WindowsLookAndFffl.gfnfrbtfHiRfsDisbblfdIdon");

    privbtf boolfbn updbtfPfnding = fblsf;

    privbtf boolfbn usfSystfmFontSfttings = truf;
    privbtf boolfbn usfSystfmFontSizfSfttings;

    // Tifsf propfrtifs brf not usfd dirfdtly, but brf kfpt bs
    // privbtf mfmbfrs to bvoid bfing GC'd.
    privbtf DfsktopPropfrty tifmfAdtivf, dllNbmf, dolorNbmf, sizfNbmf;
    privbtf DfsktopPropfrty bbSfttings;

    privbtf trbnsifnt LbyoutStylf stylf;

    /**
     * Bbsf diblog units blong tif iorizontbl bxis.
     */
    privbtf int bbsfUnitX;

    /**
     * Bbsf diblog units blong tif vfrtidbl bxis.
     */
    privbtf int bbsfUnitY;

    publid String gftNbmf() {
        rfturn "Windows";
    }

    publid String gftDfsdription() {
        rfturn "Tif Midrosoft Windows Look bnd Fffl";
    }

    publid String gftID() {
        rfturn "Windows";
    }

    publid boolfbn isNbtivfLookAndFffl() {
        rfturn OSInfo.gftOSTypf() == OSInfo.OSTypf.WINDOWS;
    }

    publid boolfbn isSupportfdLookAndFffl() {
        rfturn isNbtivfLookAndFffl();
    }

    publid void initiblizf() {
        supfr.initiblizf();

        // Sft tif flbg wiidi dftfrminfs wiidi vfrsion of Windows siould
        // bf rfndfrfd. Tiis flbg only nffd to bf sft ondf.
        // if vfrsion <= 4.0 tifn tif dlbssid LAF siould bf lobdfd.
        if (OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_95) <= 0) {
            isClbssidWindows = truf;
        } flsf {
            isClbssidWindows = fblsf;
            XPStylf.invblidbtfStylf();
        }

        // Using tif fonts sft by tif usfr dbn potfntiblly dbusf
        // pfrformbndf bnd dompbtibility issufs, so bllow tiis ffbturf
        // to bf switdifd off fitifr bt runtimf or progrbmmbtidblly
        //
        String systfmFonts = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
               nfw GftPropfrtyAdtion("swing.usfSystfmFontSfttings"));
        usfSystfmFontSfttings = (systfmFonts == null ||
                                 Boolfbn.vblufOf(systfmFonts).boolfbnVbluf());

        if (usfSystfmFontSfttings) {
            Objfdt vbluf = UIMbnbgfr.gft("Applidbtion.usfSystfmFontSfttings");

            usfSystfmFontSfttings = (vbluf == null ||
                                     Boolfbn.TRUE.fqubls(vbluf));
        }
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
            bddKfyEvfntPostProdfssor(WindowsRootPbnfUI.bltProdfssor);

    }

    /**
     * Initiblizf tif uiClbssID to BbsidComponfntUI mbpping.
     * Tif JComponfnt dlbssfs dffinf tifir own uiClbssID donstbnts
     * (sff AbstrbdtComponfnt.gftUIClbssID).  Tiis tbblf must
     * mbp tiosf donstbnts to b BbsidComponfntUI dlbss of tif
     * bppropribtf typf.
     *
     * @sff BbsidLookAndFffl#gftDffbults
     */
    protfdtfd void initClbssDffbults(UIDffbults tbblf)
    {
        supfr.initClbssDffbults(tbblf);

        finbl String windowsPbdkbgfNbmf = "dom.sun.jbvb.swing.plbf.windows.";

        Objfdt[] uiDffbults = {
              "ButtonUI", windowsPbdkbgfNbmf + "WindowsButtonUI",
            "CifdkBoxUI", windowsPbdkbgfNbmf + "WindowsCifdkBoxUI",
    "CifdkBoxMfnuItfmUI", windowsPbdkbgfNbmf + "WindowsCifdkBoxMfnuItfmUI",
               "LbbflUI", windowsPbdkbgfNbmf + "WindowsLbbflUI",
         "RbdioButtonUI", windowsPbdkbgfNbmf + "WindowsRbdioButtonUI",
 "RbdioButtonMfnuItfmUI", windowsPbdkbgfNbmf + "WindowsRbdioButtonMfnuItfmUI",
        "TogglfButtonUI", windowsPbdkbgfNbmf + "WindowsTogglfButtonUI",
         "ProgrfssBbrUI", windowsPbdkbgfNbmf + "WindowsProgrfssBbrUI",
              "SlidfrUI", windowsPbdkbgfNbmf + "WindowsSlidfrUI",
           "SfpbrbtorUI", windowsPbdkbgfNbmf + "WindowsSfpbrbtorUI",
           "SplitPbnfUI", windowsPbdkbgfNbmf + "WindowsSplitPbnfUI",
             "SpinnfrUI", windowsPbdkbgfNbmf + "WindowsSpinnfrUI",
          "TbbbfdPbnfUI", windowsPbdkbgfNbmf + "WindowsTbbbfdPbnfUI",
            "TfxtArfbUI", windowsPbdkbgfNbmf + "WindowsTfxtArfbUI",
           "TfxtFifldUI", windowsPbdkbgfNbmf + "WindowsTfxtFifldUI",
       "PbsswordFifldUI", windowsPbdkbgfNbmf + "WindowsPbsswordFifldUI",
            "TfxtPbnfUI", windowsPbdkbgfNbmf + "WindowsTfxtPbnfUI",
          "EditorPbnfUI", windowsPbdkbgfNbmf + "WindowsEditorPbnfUI",
                "TrffUI", windowsPbdkbgfNbmf + "WindowsTrffUI",
             "ToolBbrUI", windowsPbdkbgfNbmf + "WindowsToolBbrUI",
    "ToolBbrSfpbrbtorUI", windowsPbdkbgfNbmf + "WindowsToolBbrSfpbrbtorUI",
            "ComboBoxUI", windowsPbdkbgfNbmf + "WindowsComboBoxUI",
         "TbblfHfbdfrUI", windowsPbdkbgfNbmf + "WindowsTbblfHfbdfrUI",
       "IntfrnblFrbmfUI", windowsPbdkbgfNbmf + "WindowsIntfrnblFrbmfUI",
         "DfsktopPbnfUI", windowsPbdkbgfNbmf + "WindowsDfsktopPbnfUI",
         "DfsktopIdonUI", windowsPbdkbgfNbmf + "WindowsDfsktopIdonUI",
         "FilfCioosfrUI", windowsPbdkbgfNbmf + "WindowsFilfCioosfrUI",
                "MfnuUI", windowsPbdkbgfNbmf + "WindowsMfnuUI",
            "MfnuItfmUI", windowsPbdkbgfNbmf + "WindowsMfnuItfmUI",
             "MfnuBbrUI", windowsPbdkbgfNbmf + "WindowsMfnuBbrUI",
           "PopupMfnuUI", windowsPbdkbgfNbmf + "WindowsPopupMfnuUI",
  "PopupMfnuSfpbrbtorUI", windowsPbdkbgfNbmf + "WindowsPopupMfnuSfpbrbtorUI",
           "SdrollBbrUI", windowsPbdkbgfNbmf + "WindowsSdrollBbrUI",
            "RootPbnfUI", windowsPbdkbgfNbmf + "WindowsRootPbnfUI"
        };

        tbblf.putDffbults(uiDffbults);
    }

    /**
     * Lobd tif SystfmColors into tif dffbults tbblf.  Tif kfys
     * for SystfmColor dffbults brf tif sbmf bs tif nbmfs of
     * tif publid fiflds in SystfmColor.  If tif tbblf is bfing
     * drfbtfd on b nbtivf Windows plbtform wf usf tif SystfmColor
     * vblufs, otifrwisf wf drfbtf dolor objfdts wiosf vblufs mbtdi
     * tif dffbults Windows95 dolors.
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
       "mfnuPrfssfdItfmB", "#000080", /* LigitSibdow of mfnubutton iigiligit */
       "mfnuPrfssfdItfmF", "#FFFFFF", /* Dffbult dolor for forfground "tfxt" in mfnu itfm */
               "mfnuTfxt", "#000000", /* Tfxt dolor for mfnus  */
                   "tfxt", "#C0C0C0", /* Tfxt bbdkground dolor */
               "tfxtTfxt", "#000000", /* Tfxt forfground dolor */
          "tfxtHigiligit", "#000080", /* Tfxt bbdkground dolor wifn sflfdtfd */
      "tfxtHigiligitTfxt", "#FFFFFF", /* Tfxt dolor wifn sflfdtfd */
       "tfxtInbdtivfTfxt", "#808080", /* Tfxt dolor wifn disbblfd */
                "dontrol", "#C0C0C0", /* Dffbult dolor for dontrols (buttons, slidfrs, ftd) */
            "dontrolTfxt", "#000000", /* Dffbult dolor for tfxt in dontrols */
       "dontrolHigiligit", "#C0C0C0",

  /*"dontrolHigiligit", "#E0E0E0",*/ /* Spfdulbr iigiligit (oppositf of tif sibdow) */
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
     * Initiblizf tif dffbults tbblf witi tif nbmf of tif RfsourdfBundlf
     * usfd for gftting lodblizfd dffbults.
     */
    privbtf void initRfsourdfBundlf(UIDffbults tbblf) {
        tbblf.bddRfsourdfBundlf( "dom.sun.jbvb.swing.plbf.windows.rfsourdfs.windows" );
    }

    // XXX - tifrf brf probbbly b lot of rfdundbnt vblufs tibt dould bf rfmovfd.
    // if. Tbkf b look bt RbdioButtonBordfr, ftd...
    protfdtfd void initComponfntDffbults(UIDffbults tbblf)
    {
        supfr.initComponfntDffbults( tbblf );

        initRfsourdfBundlf(tbblf);

        // *** Sibrfd Fonts
        LbzyVbluf diblogPlbin12 = t -> nfw FontUIRfsourdf(Font.DIALOG, Font.PLAIN, 12);

        LbzyVbluf sbnsSfrifPlbin12 =  t -> nfw FontUIRfsourdf(Font.SANS_SERIF, Font.PLAIN, 12);
        LbzyVbluf monospbdfdPlbin12 = t -> nfw FontUIRfsourdf(Font.MONOSPACED, Font.PLAIN, 12);
        LbzyVbluf diblogBold12 = t -> nfw FontUIRfsourdf(Font.DIALOG, Font.BOLD, 12);

        // *** Colors
        // XXX - somf of tifsf dofns't sffm to bf usfd
        ColorUIRfsourdf rfd = nfw ColorUIRfsourdf(Color.rfd);
        ColorUIRfsourdf blbdk = nfw ColorUIRfsourdf(Color.blbdk);
        ColorUIRfsourdf wiitf = nfw ColorUIRfsourdf(Color.wiitf);
        ColorUIRfsourdf grby = nfw ColorUIRfsourdf(Color.grby);
        ColorUIRfsourdf dbrkGrby = nfw ColorUIRfsourdf(Color.dbrkGrby);
        ColorUIRfsourdf sdrollBbrTrbdkHigiligit = dbrkGrby;

        // Sft tif flbg wiidi dftfrminfs wiidi vfrsion of Windows siould
        // bf rfndfrfd. Tiis flbg only nffd to bf sft ondf.
        // if vfrsion <= 4.0 tifn tif dlbssid LAF siould bf lobdfd.
        isClbssidWindows = OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_95) <= 0;

        // *** Trff
        Objfdt trffExpbndfdIdon = WindowsTrffUI.ExpbndfdIdon.drfbtfExpbndfdIdon();

        Objfdt trffCollbpsfdIdon = WindowsTrffUI.CollbpsfdIdon.drfbtfCollbpsfdIdon();


        // *** Tfxt
        Objfdt fifldInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                      "dontrol C", DffbultEditorKit.dopyAdtion,
                      "dontrol V", DffbultEditorKit.pbstfAdtion,
                      "dontrol X", DffbultEditorKit.dutAdtion,
                           "COPY", DffbultEditorKit.dopyAdtion,
                          "PASTE", DffbultEditorKit.pbstfAdtion,
                            "CUT", DffbultEditorKit.dutAdtion,
                 "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                   "siift INSERT", DffbultEditorKit.pbstfAdtion,
                   "siift DELETE", DffbultEditorKit.dutAdtion,
                      "dontrol A", DffbultEditorKit.sflfdtAllAdtion,
             "dontrol BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                     "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "dontrol LEFT", DffbultEditorKit.prfviousWordAdtion,
                  "dontrol RIGHT", DffbultEditorKit.nfxtWordAdtion,
             "dontrol siift LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
            "dontrol siift RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
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
                "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
        });

        Objfdt pbsswordInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                      "dontrol C", DffbultEditorKit.dopyAdtion,
                      "dontrol V", DffbultEditorKit.pbstfAdtion,
                      "dontrol X", DffbultEditorKit.dutAdtion,
                           "COPY", DffbultEditorKit.dopyAdtion,
                          "PASTE", DffbultEditorKit.pbstfAdtion,
                            "CUT", DffbultEditorKit.dutAdtion,
                 "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                   "siift INSERT", DffbultEditorKit.pbstfAdtion,
                   "siift DELETE", DffbultEditorKit.dutAdtion,
                      "dontrol A", DffbultEditorKit.sflfdtAllAdtion,
             "dontrol BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                     "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "dontrol LEFT", DffbultEditorKit.bfginLinfAdtion,
                  "dontrol RIGHT", DffbultEditorKit.fndLinfAdtion,
             "dontrol siift LEFT", DffbultEditorKit.sflfdtionBfginLinfAdtion,
            "dontrol siift RIGHT", DffbultEditorKit.sflfdtionEndLinfAdtion,
                           "HOME", DffbultEditorKit.bfginLinfAdtion,
                            "END", DffbultEditorKit.fndLinfAdtion,
                     "siift HOME", DffbultEditorKit.sflfdtionBfginLinfAdtion,
                      "siift END", DffbultEditorKit.sflfdtionEndLinfAdtion,
                     "BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
               "siift BACK_SPACE", DffbultEditorKit.dflftfPrfvCibrAdtion,
                         "dtrl H", DffbultEditorKit.dflftfPrfvCibrAdtion,
                         "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
                          "RIGHT", DffbultEditorKit.forwbrdAdtion,
                           "LEFT", DffbultEditorKit.bbdkwbrdAdtion,
                       "KP_RIGHT", DffbultEditorKit.forwbrdAdtion,
                        "KP_LEFT", DffbultEditorKit.bbdkwbrdAdtion,
                          "ENTER", JTfxtFifld.notifyAdtion,
                "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
        });

        Objfdt multilinfInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                      "dontrol C", DffbultEditorKit.dopyAdtion,
                      "dontrol V", DffbultEditorKit.pbstfAdtion,
                      "dontrol X", DffbultEditorKit.dutAdtion,
                           "COPY", DffbultEditorKit.dopyAdtion,
                          "PASTE", DffbultEditorKit.pbstfAdtion,
                            "CUT", DffbultEditorKit.dutAdtion,
                 "dontrol INSERT", DffbultEditorKit.dopyAdtion,
                   "siift INSERT", DffbultEditorKit.pbstfAdtion,
                   "siift DELETE", DffbultEditorKit.dutAdtion,
                     "siift LEFT", DffbultEditorKit.sflfdtionBbdkwbrdAdtion,
                    "siift RIGHT", DffbultEditorKit.sflfdtionForwbrdAdtion,
                   "dontrol LEFT", DffbultEditorKit.prfviousWordAdtion,
                  "dontrol RIGHT", DffbultEditorKit.nfxtWordAdtion,
             "dontrol siift LEFT", DffbultEditorKit.sflfdtionPrfviousWordAdtion,
            "dontrol siift RIGHT", DffbultEditorKit.sflfdtionNfxtWordAdtion,
                      "dontrol A", DffbultEditorKit.sflfdtAllAdtion,
             "dontrol BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                           "HOME", DffbultEditorKit.bfginLinfAdtion,
                            "END", DffbultEditorKit.fndLinfAdtion,
                     "siift HOME", DffbultEditorKit.sflfdtionBfginLinfAdtion,
                      "siift END", DffbultEditorKit.sflfdtionEndLinfAdtion,
                   "dontrol HOME", DffbultEditorKit.bfginAdtion,
                    "dontrol END", DffbultEditorKit.fndAdtion,
             "dontrol siift HOME", DffbultEditorKit.sflfdtionBfginAdtion,
              "dontrol siift END", DffbultEditorKit.sflfdtionEndAdtion,
                             "UP", DffbultEditorKit.upAdtion,
                           "DOWN", DffbultEditorKit.downAdtion,
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
                      "dontrol T", "nfxt-link-bdtion",
                "dontrol siift T", "prfvious-link-bdtion",
                  "dontrol SPACE", "bdtivbtf-link-bdtion",
                "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
        });

        Objfdt mfnuItfmAddflfrbtorDflimitfr = "+";

        Objfdt ControlBbdkgroundColor = nfw DfsktopPropfrty(
                                                       "win.3d.bbdkgroundColor",
                                                        tbblf.gft("dontrol"));
        Objfdt ControlLigitColor      = nfw DfsktopPropfrty(
                                                       "win.3d.ligitColor",
                                                        tbblf.gft("dontrolHigiligit"));
        Objfdt ControlHigiligitColor  = nfw DfsktopPropfrty(
                                                       "win.3d.iigiligitColor",
                                                        tbblf.gft("dontrolLtHigiligit"));
        Objfdt ControlSibdowColor     = nfw DfsktopPropfrty(
                                                       "win.3d.sibdowColor",
                                                        tbblf.gft("dontrolSibdow"));
        Objfdt ControlDbrkSibdowColor = nfw DfsktopPropfrty(
                                                       "win.3d.dbrkSibdowColor",
                                                        tbblf.gft("dontrolDkSibdow"));
        Objfdt ControlTfxtColor       = nfw DfsktopPropfrty(
                                                       "win.button.tfxtColor",
                                                        tbblf.gft("dontrolTfxt"));
        Objfdt MfnuBbdkgroundColor    = nfw DfsktopPropfrty(
                                                       "win.mfnu.bbdkgroundColor",
                                                        tbblf.gft("mfnu"));
        Objfdt MfnuBbrBbdkgroundColor = nfw DfsktopPropfrty(
                                                       "win.mfnubbr.bbdkgroundColor",
                                                        tbblf.gft("mfnu"));
        Objfdt MfnuTfxtColor          = nfw DfsktopPropfrty(
                                                       "win.mfnu.tfxtColor",
                                                        tbblf.gft("mfnuTfxt"));
        Objfdt SflfdtionBbdkgroundColor = nfw DfsktopPropfrty(
                                                       "win.itfm.iigiligitColor",
                                                        tbblf.gft("tfxtHigiligit"));
        Objfdt SflfdtionTfxtColor     = nfw DfsktopPropfrty(
                                                       "win.itfm.iigiligitTfxtColor",
                                                        tbblf.gft("tfxtHigiligitTfxt"));
        Objfdt WindowBbdkgroundColor  = nfw DfsktopPropfrty(
                                                       "win.frbmf.bbdkgroundColor",
                                                        tbblf.gft("window"));
        Objfdt WindowTfxtColor        = nfw DfsktopPropfrty(
                                                       "win.frbmf.tfxtColor",
                                                        tbblf.gft("windowTfxt"));
        Objfdt WindowBordfrWidti      = nfw DfsktopPropfrty(
                                                       "win.frbmf.sizingBordfrWidti",
                                                       Intfgfr.vblufOf(1));
        Objfdt TitlfPbnfHfigit        = nfw DfsktopPropfrty(
                                                       "win.frbmf.dbptionHfigit",
                                                       Intfgfr.vblufOf(18));
        Objfdt TitlfButtonWidti       = nfw DfsktopPropfrty(
                                                       "win.frbmf.dbptionButtonWidti",
                                                       Intfgfr.vblufOf(16));
        Objfdt TitlfButtonHfigit      = nfw DfsktopPropfrty(
                                                       "win.frbmf.dbptionButtonHfigit",
                                                       Intfgfr.vblufOf(16));
        Objfdt InbdtivfTfxtColor      = nfw DfsktopPropfrty(
                                                       "win.tfxt.grbyfdTfxtColor",
                                                        tbblf.gft("tfxtInbdtivfTfxt"));
        Objfdt SdrollbbrBbdkgroundColor = nfw DfsktopPropfrty(
                                                       "win.sdrollbbr.bbdkgroundColor",
                                                        tbblf.gft("sdrollbbr"));
        Objfdt buttonFodusColor = nfw FodusColorPropfrty();

        Objfdt TfxtBbdkground         = nfw XPColorVbluf(Pbrt.EP_EDIT, null, Prop.FILLCOLOR,
                                                         WindowBbdkgroundColor);
        //Tif following four linfs wfrf dommfntfd out bs pbrt of bug 4991597
        //Tiis dodf *is* dorrfdt, iowfvfr it difffrs from WindowsXP bnd is, bppbrfntly
        //b Windows XP bug. Until Windows fixfs tiis bug, wf sibll blso fxiibit tif sbmf
        //bfibvior
        //Objfdt RfbdOnlyTfxtBbdkground = nfw XPColorVbluf(Pbrt.EP_EDITTEXT, Stbtf.READONLY, Prop.FILLCOLOR,
        //                                                 ControlBbdkgroundColor);
        //Objfdt DisbblfdTfxtBbdkground = nfw XPColorVbluf(Pbrt.EP_EDITTEXT, Stbtf.DISABLED, Prop.FILLCOLOR,
        //                                                 ControlBbdkgroundColor);
        Objfdt RfbdOnlyTfxtBbdkground = ControlBbdkgroundColor;
        Objfdt DisbblfdTfxtBbdkground = ControlBbdkgroundColor;

        Objfdt MfnuFont = diblogPlbin12;
        Objfdt FixfdControlFont = monospbdfdPlbin12;
        Objfdt ControlFont = diblogPlbin12;
        Objfdt MfssbgfFont = diblogPlbin12;
        Objfdt WindowFont = diblogBold12;
        Objfdt ToolTipFont = sbnsSfrifPlbin12;
        Objfdt IdonFont = ControlFont;

        Objfdt sdrollBbrWidti = nfw DfsktopPropfrty("win.sdrollbbr.widti", Intfgfr.vblufOf(16));

        Objfdt mfnuBbrHfigit = nfw DfsktopPropfrty("win.mfnu.ifigit", null);

        Objfdt iotTrbdkingOn = nfw DfsktopPropfrty("win.itfm.iotTrbdkingOn", truf);

        Objfdt siowMnfmonids = nfw DfsktopPropfrty("win.mfnu.kfybobrdCufsOn", Boolfbn.TRUE);

        if (usfSystfmFontSfttings) {
            MfnuFont = gftDfsktopFontVbluf("win.mfnu.font", MfnuFont);
            FixfdControlFont = gftDfsktopFontVbluf("win.bnsiFixfd.font", FixfdControlFont);
            ControlFont = gftDfsktopFontVbluf("win.dffbultGUI.font", ControlFont);
            MfssbgfFont = gftDfsktopFontVbluf("win.mfssbgfbox.font", MfssbgfFont);
            WindowFont = gftDfsktopFontVbluf("win.frbmf.dbptionFont", WindowFont);
            IdonFont    = gftDfsktopFontVbluf("win.idon.font", IdonFont);
            ToolTipFont = gftDfsktopFontVbluf("win.tooltip.font", ToolTipFont);

            /* Put tif dfsktop AA sfttings in tif dffbults.
             * JComponfnt.sftUI() rftrifvfs tiis bnd mbkfs it bvbilbblf
             * bs b dlifnt propfrty on tif JComponfnt. Usf tif sbmf kfy nbmf
             * for boti dlifnt propfrty bnd UIDffbults.
             * Also nffd to sft up listfnfrs for dibngfs in tifsf sfttings.
             */
            Objfdt bbTfxtInfo = SwingUtilitifs2.AATfxtInfo.gftAATfxtInfo(truf);
            tbblf.put(SwingUtilitifs2.AA_TEXT_PROPERTY_KEY, bbTfxtInfo);
            tiis.bbSfttings =
                nfw FontDfsktopPropfrty(SunToolkit.DESKTOPFONTHINTS);
        }
        if (usfSystfmFontSizfSfttings) {
            MfnuFont = nfw WindowsFontSizfPropfrty("win.mfnu.font.ifigit", Font.DIALOG, Font.PLAIN, 12);
            FixfdControlFont = nfw WindowsFontSizfPropfrty("win.bnsiFixfd.font.ifigit", Font.MONOSPACED,
                       Font.PLAIN, 12);
            ControlFont = nfw WindowsFontSizfPropfrty("win.dffbultGUI.font.ifigit", Font.DIALOG, Font.PLAIN, 12);
            MfssbgfFont = nfw WindowsFontSizfPropfrty("win.mfssbgfbox.font.ifigit", Font.DIALOG, Font.PLAIN, 12);
            WindowFont = nfw WindowsFontSizfPropfrty("win.frbmf.dbptionFont.ifigit", Font.DIALOG, Font.BOLD, 12);
            ToolTipFont = nfw WindowsFontSizfPropfrty("win.tooltip.font.ifigit", Font.SANS_SERIF, Font.PLAIN, 12);
            IdonFont    = nfw WindowsFontSizfPropfrty("win.idon.font.ifigit", Font.DIALOG, Font.PLAIN, 12);
        }


        if (!(tiis instbndfof WindowsClbssidLookAndFffl) &&
            (OSInfo.gftOSTypf() == OSInfo.OSTypf.WINDOWS &&
             OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_XP) >= 0) &&
            AddfssControllfr.doPrivilfgfd(nfw GftPropfrtyAdtion("swing.noxp")) == null) {

            // Tifsf dfsktop propfrtifs brf not usfd dirfdtly, but brf nffdfd to
            // triggfr rfblobding of UI's.
            tiis.tifmfAdtivf = nfw TriggfrDfsktopPropfrty("win.xpstylf.tifmfAdtivf");
            tiis.dllNbmf     = nfw TriggfrDfsktopPropfrty("win.xpstylf.dllNbmf");
            tiis.dolorNbmf   = nfw TriggfrDfsktopPropfrty("win.xpstylf.dolorNbmf");
            tiis.sizfNbmf    = nfw TriggfrDfsktopPropfrty("win.xpstylf.sizfNbmf");
        }


        Objfdt[] dffbults = {
            // *** Auditory Fffdbbdk
            // tiis kfy dffinfs wiidi of tif vbrious dufs to rfndfr
            // Ovfrriddfn from BbsidL&F. Tiis L&F siould plby bll sounds
            // bll tif timf. Tif infrbstrudturf dfdidfs wibt to plby.
            // Tiis is disbblfd until sound bugs dbn bf rfsolvfd.
            "AuditoryCufs.plbyList", null, // tbblf.gft("AuditoryCufs.dufList"),

            "Applidbtion.usfSystfmFontSfttings", Boolfbn.vblufOf(usfSystfmFontSfttings),

            "TfxtFifld.fodusInputMbp", fifldInputMbp,
            "PbsswordFifld.fodusInputMbp", pbsswordInputMbp,
            "TfxtArfb.fodusInputMbp", multilinfInputMbp,
            "TfxtPbnf.fodusInputMbp", multilinfInputMbp,
            "EditorPbnf.fodusInputMbp", multilinfInputMbp,

            // Buttons
            "Button.font", ControlFont,
            "Button.bbdkground", ControlBbdkgroundColor,
            // Button.forfground, Button.sibdow, Button.dbrkSibdow,
            // Button.disbblfdForground, bnd Button.disbblfdSibdow brf only
            // usfd for Windows Clbssid. Windows XP will usf dolors
            // from tif durrfnt visubl stylf.
            "Button.forfground", ControlTfxtColor,
            "Button.sibdow", ControlSibdowColor,
            "Button.dbrkSibdow", ControlDbrkSibdowColor,
            "Button.ligit", ControlLigitColor,
            "Button.iigiligit", ControlHigiligitColor,
            "Button.disbblfdForfground", InbdtivfTfxtColor,
            "Button.disbblfdSibdow", ControlHigiligitColor,
            "Button.fodus", buttonFodusColor,
            "Button.dbsifdRfdtGbpX", nfw XPVbluf(Intfgfr.vblufOf(3), Intfgfr.vblufOf(5)),
            "Button.dbsifdRfdtGbpY", nfw XPVbluf(Intfgfr.vblufOf(3), Intfgfr.vblufOf(4)),
            "Button.dbsifdRfdtGbpWidti", nfw XPVbluf(Intfgfr.vblufOf(6), Intfgfr.vblufOf(10)),
            "Button.dbsifdRfdtGbpHfigit", nfw XPVbluf(Intfgfr.vblufOf(6), Intfgfr.vblufOf(8)),
            "Button.tfxtSiiftOffsft", nfw XPVbluf(Intfgfr.vblufOf(0),
                                                  Intfgfr.vblufOf(1)),
            // W2K kfybobrd nbvigbtion iidding.
            "Button.siowMnfmonids", siowMnfmonids,
            "Button.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                 }),

            "Cbrft.widti",
                  nfw DfsktopPropfrty("win.dbrft.widti", null),

            "CifdkBox.font", ControlFont,
            "CifdkBox.intfriorBbdkground", WindowBbdkgroundColor,
            "CifdkBox.bbdkground", ControlBbdkgroundColor,
            "CifdkBox.forfground", WindowTfxtColor,
            "CifdkBox.sibdow", ControlSibdowColor,
            "CifdkBox.dbrkSibdow", ControlDbrkSibdowColor,
            "CifdkBox.ligit", ControlLigitColor,
            "CifdkBox.iigiligit", ControlHigiligitColor,
            "CifdkBox.fodus", buttonFodusColor,
            "CifdkBox.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                 }),
            // mbrgin is 2 bll tif wby bround, BbsidBordfrs.RbdioButtonBordfr
            // (difdkbox usfs RbdioButtonBordfr) is 2 bll tif wby bround too.
            "CifdkBox.totblInsfts", nfw Insfts(4, 4, 4, 4),

            "CifdkBoxMfnuItfm.font", MfnuFont,
            "CifdkBoxMfnuItfm.bbdkground", MfnuBbdkgroundColor,
            "CifdkBoxMfnuItfm.forfground", MfnuTfxtColor,
            "CifdkBoxMfnuItfm.sflfdtionForfground", SflfdtionTfxtColor,
            "CifdkBoxMfnuItfm.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "CifdkBoxMfnuItfm.bddflfrbtorForfground", MfnuTfxtColor,
            "CifdkBoxMfnuItfm.bddflfrbtorSflfdtionForfground", SflfdtionTfxtColor,
            "CifdkBoxMfnuItfm.dommbndSound", "win.sound.mfnuCommbnd",

            "ComboBox.font", ControlFont,
            "ComboBox.bbdkground", WindowBbdkgroundColor,
            "ComboBox.forfground", WindowTfxtColor,
            "ComboBox.buttonBbdkground", ControlBbdkgroundColor,
            "ComboBox.buttonSibdow", ControlSibdowColor,
            "ComboBox.buttonDbrkSibdow", ControlDbrkSibdowColor,
            "ComboBox.buttonHigiligit", ControlHigiligitColor,
            "ComboBox.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "ComboBox.sflfdtionForfground", SflfdtionTfxtColor,
            "ComboBox.fditorBordfr", nfw XPVbluf(nfw EmptyBordfr(1,2,1,1),
                                                 nfw EmptyBordfr(1,4,1,4)),
            "ComboBox.disbblfdBbdkground",
                        nfw XPColorVbluf(Pbrt.CP_COMBOBOX, Stbtf.DISABLED,
                        Prop.FILLCOLOR, DisbblfdTfxtBbdkground),
            "ComboBox.disbblfdForfground",
                        nfw XPColorVbluf(Pbrt.CP_COMBOBOX, Stbtf.DISABLED,
                        Prop.TEXTCOLOR, InbdtivfTfxtColor),
            "ComboBox.bndfstorInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                   "ESCAPE", "iidfPopup",
                  "PAGE_UP", "pbgfUpPbssTirougi",
                "PAGE_DOWN", "pbgfDownPbssTirougi",
                     "HOME", "iomfPbssTirougi",
                      "END", "fndPbssTirougi",
                     "DOWN", "sflfdtNfxt2",
                  "KP_DOWN", "sflfdtNfxt2",
                       "UP", "sflfdtPrfvious2",
                    "KP_UP", "sflfdtPrfvious2",
                    "ENTER", "fntfrPrfssfd",
                       "F4", "togglfPopup",
                 "blt DOWN", "togglfPopup",
              "blt KP_DOWN", "togglfPopup",
                   "blt UP", "togglfPopup",
                "blt KP_UP", "togglfPopup"
              }),

            // DfskTop.
            "Dfsktop.bbdkground", nfw DfsktopPropfrty(
                                                 "win.dfsktop.bbdkgroundColor",
                                                  tbblf.gft("dfsktop")),
            "Dfsktop.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                   "dtrl F5", "rfstorf",
                   "dtrl F4", "dlosf",
                   "dtrl F7", "movf",
                   "dtrl F8", "rfsizf",
                   "RIGHT", "rigit",
                   "KP_RIGHT", "rigit",
                   "LEFT", "lfft",
                   "KP_LEFT", "lfft",
                   "UP", "up",
                   "KP_UP", "up",
                   "DOWN", "down",
                   "KP_DOWN", "down",
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

            // DfsktopIdon
            "DfsktopIdon.widti", Intfgfr.vblufOf(160),

            "EditorPbnf.font", ControlFont,
            "EditorPbnf.bbdkground", WindowBbdkgroundColor,
            "EditorPbnf.forfground", WindowTfxtColor,
            "EditorPbnf.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "EditorPbnf.sflfdtionForfground", SflfdtionTfxtColor,
            "EditorPbnf.dbrftForfground", WindowTfxtColor,
            "EditorPbnf.inbdtivfForfground", InbdtivfTfxtColor,
            "EditorPbnf.inbdtivfBbdkground", WindowBbdkgroundColor,
            "EditorPbnf.disbblfdBbdkground", DisbblfdTfxtBbdkground,

            "FilfCioosfr.iomfFoldfrIdon",  nfw LbzyWindowsIdon(null,
                                                               "idons/HomfFoldfr.gif"),
            "FilfCioosfr.listFont", IdonFont,
            "FilfCioosfr.listVifwBbdkground", nfw XPColorVbluf(Pbrt.LVP_LISTVIEW, null, Prop.FILLCOLOR,
                                                               WindowBbdkgroundColor),
            "FilfCioosfr.listVifwBordfr", nfw XPBordfrVbluf(Pbrt.LVP_LISTVIEW,
               (LbzyVbluf) t -> BordfrUIRfsourdf.gftLowfrfdBfvflBordfrUIRfsourdf()),
            "FilfCioosfr.listVifwIdon",    nfw LbzyWindowsIdon("filfCioosfrIdon ListVifw",
                                                               "idons/ListVifw.gif"),
            "FilfCioosfr.listVifwWindowsStylf", Boolfbn.TRUE,
            "FilfCioosfr.dftbilsVifwIdon", nfw LbzyWindowsIdon("filfCioosfrIdon DftbilsVifw",
                                                               "idons/DftbilsVifw.gif"),
            "FilfCioosfr.vifwMfnuIdon", nfw LbzyWindowsIdon("filfCioosfrIdon VifwMfnu",
                                                            "idons/ListVifw.gif"),
            "FilfCioosfr.upFoldfrIdon",    nfw LbzyWindowsIdon("filfCioosfrIdon UpFoldfr",
                                                               "idons/UpFoldfr.gif"),
            "FilfCioosfr.nfwFoldfrIdon",   nfw LbzyWindowsIdon("filfCioosfrIdon NfwFoldfr",
                                                               "idons/NfwFoldfr.gif"),
            "FilfCioosfr.usfSystfmExtfnsionHiding", Boolfbn.TRUE,

            "FilfCioosfr.usfsSinglfFilfPbnf", Boolfbn.TRUE,
            "FilfCioosfr.noPlbdfsBbr", nfw DfsktopPropfrty("win.domdlg.noPlbdfsBbr",
                                                           Boolfbn.FALSE),
            "FilfCioosfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "dbndflSflfdtion",
                     "F2", "fditFilfNbmf",
                     "F5", "rffrfsi",
                     "BACK_SPACE", "Go Up"
                 }),

            "FilfVifw.dirfdtoryIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                               WindowsLookAndFffl.dlbss,
                                                               "idons/Dirfdtory.gif"),
            "FilfVifw.filfIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                          WindowsLookAndFffl.dlbss,
                                                          "idons/Filf.gif"),
            "FilfVifw.domputfrIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                              WindowsLookAndFffl.dlbss,
                                                              "idons/Computfr.gif"),
            "FilfVifw.ibrdDrivfIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                               WindowsLookAndFffl.dlbss,
                                                               "idons/HbrdDrivf.gif"),
            "FilfVifw.floppyDrivfIdon", SwingUtilitifs2.mbkfIdon(gftClbss(),
                                                                 WindowsLookAndFffl.dlbss,
                                                                 "idons/FloppyDrivf.gif"),

            "FormbttfdTfxtFifld.font", ControlFont,
            "IntfrnblFrbmf.titlfFont", WindowFont,
            "IntfrnblFrbmf.titlfPbnfHfigit",   TitlfPbnfHfigit,
            "IntfrnblFrbmf.titlfButtonWidti",  TitlfButtonWidti,
            "IntfrnblFrbmf.titlfButtonHfigit", TitlfButtonHfigit,
            "IntfrnblFrbmf.titlfButtonToolTipsOn", iotTrbdkingOn,
            "IntfrnblFrbmf.bordfrColor", ControlBbdkgroundColor,
            "IntfrnblFrbmf.bordfrSibdow", ControlSibdowColor,
            "IntfrnblFrbmf.bordfrDbrkSibdow", ControlDbrkSibdowColor,
            "IntfrnblFrbmf.bordfrHigiligit", ControlHigiligitColor,
            "IntfrnblFrbmf.bordfrLigit", ControlLigitColor,
            "IntfrnblFrbmf.bordfrWidti", WindowBordfrWidti,
            "IntfrnblFrbmf.minimizfIdonBbdkground", ControlBbdkgroundColor,
            "IntfrnblFrbmf.rfsizfIdonHigiligit", ControlLigitColor,
            "IntfrnblFrbmf.rfsizfIdonSibdow", ControlSibdowColor,
            "IntfrnblFrbmf.bdtivfBordfrColor", nfw DfsktopPropfrty(
                                                       "win.frbmf.bdtivfBordfrColor",
                                                       tbblf.gft("windowBordfr")),
            "IntfrnblFrbmf.inbdtivfBordfrColor", nfw DfsktopPropfrty(
                                                       "win.frbmf.inbdtivfBordfrColor",
                                                       tbblf.gft("windowBordfr")),
            "IntfrnblFrbmf.bdtivfTitlfBbdkground", nfw DfsktopPropfrty(
                                                        "win.frbmf.bdtivfCbptionColor",
                                                         tbblf.gft("bdtivfCbption")),
            "IntfrnblFrbmf.bdtivfTitlfGrbdifnt", nfw DfsktopPropfrty(
                                                        "win.frbmf.bdtivfCbptionGrbdifntColor",
                                                         tbblf.gft("bdtivfCbption")),
            "IntfrnblFrbmf.bdtivfTitlfForfground", nfw DfsktopPropfrty(
                                                        "win.frbmf.dbptionTfxtColor",
                                                         tbblf.gft("bdtivfCbptionTfxt")),
            "IntfrnblFrbmf.inbdtivfTitlfBbdkground", nfw DfsktopPropfrty(
                                                        "win.frbmf.inbdtivfCbptionColor",
                                                         tbblf.gft("inbdtivfCbption")),
            "IntfrnblFrbmf.inbdtivfTitlfGrbdifnt", nfw DfsktopPropfrty(
                                                        "win.frbmf.inbdtivfCbptionGrbdifntColor",
                                                         tbblf.gft("inbdtivfCbption")),
            "IntfrnblFrbmf.inbdtivfTitlfForfground", nfw DfsktopPropfrty(
                                                        "win.frbmf.inbdtivfCbptionTfxtColor",
                                                         tbblf.gft("inbdtivfCbptionTfxt")),

            "IntfrnblFrbmf.mbximizfIdon",
                WindowsIdonFbdtory.drfbtfFrbmfMbximizfIdon(),
            "IntfrnblFrbmf.minimizfIdon",
                WindowsIdonFbdtory.drfbtfFrbmfMinimizfIdon(),
            "IntfrnblFrbmf.idonifyIdon",
                WindowsIdonFbdtory.drfbtfFrbmfIdonifyIdon(),
            "IntfrnblFrbmf.dlosfIdon",
                WindowsIdonFbdtory.drfbtfFrbmfClosfIdon(),
            "IntfrnblFrbmf.idon",
               (LbzyVbluf) t -> nfw Objfdt[]{
                    // Tif donstrudtor tbkfs onf brg: bn brrby of UIDffbults.LbzyVbluf
                    // rfprfsfnting tif idons
                        SwingUtilitifs2.mbkfIdon(gftClbss(), BbsidLookAndFffl.dlbss, "idons/JbvbCup16.png"),
                        SwingUtilitifs2.mbkfIdon(gftClbss(), WindowsLookAndFffl.dlbss, "idons/JbvbCup32.png")
                },
            // Intfrnbl Frbmf Auditory Cuf Mbppings
            "IntfrnblFrbmf.dlosfSound", "win.sound.dlosf",
            "IntfrnblFrbmf.mbximizfSound", "win.sound.mbximizf",
            "IntfrnblFrbmf.minimizfSound", "win.sound.minimizf",
            "IntfrnblFrbmf.rfstorfDownSound", "win.sound.rfstorfDown",
            "IntfrnblFrbmf.rfstorfUpSound", "win.sound.rfstorfUp",

            "IntfrnblFrbmf.windowBindings", nfw Objfdt[] {
                "siift ESCAPE", "siowSystfmMfnu",
                  "dtrl SPACE", "siowSystfmMfnu",
                      "ESCAPE", "iidfSystfmMfnu"},

            // Lbbfl
            "Lbbfl.font", ControlFont,
            "Lbbfl.bbdkground", ControlBbdkgroundColor,
            "Lbbfl.forfground", WindowTfxtColor,
            "Lbbfl.disbblfdForfground", InbdtivfTfxtColor,
            "Lbbfl.disbblfdSibdow", ControlHigiligitColor,

            // List.
            "List.font", ControlFont,
            "List.bbdkground", WindowBbdkgroundColor,
            "List.forfground", WindowTfxtColor,
            "List.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "List.sflfdtionForfground", SflfdtionTfxtColor,
            "List.lodkToPositionOnSdroll", Boolfbn.TRUE,
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

            // PopupMfnu
            "PopupMfnu.font", MfnuFont,
            "PopupMfnu.bbdkground", MfnuBbdkgroundColor,
            "PopupMfnu.forfground", MfnuTfxtColor,
            "PopupMfnu.popupSound", "win.sound.mfnuPopup",
            "PopupMfnu.donsumfEvfntOnClosf", Boolfbn.TRUE,

            // Mfnus
            "Mfnu.font", MfnuFont,
            "Mfnu.forfground", MfnuTfxtColor,
            "Mfnu.bbdkground", MfnuBbdkgroundColor,
            "Mfnu.usfMfnuBbrBbdkgroundForTopLfvfl", Boolfbn.TRUE,
            "Mfnu.sflfdtionForfground", SflfdtionTfxtColor,
            "Mfnu.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "Mfnu.bddflfrbtorForfground", MfnuTfxtColor,
            "Mfnu.bddflfrbtorSflfdtionForfground", SflfdtionTfxtColor,
            "Mfnu.mfnuPopupOffsftX", Intfgfr.vblufOf(0),
            "Mfnu.mfnuPopupOffsftY", Intfgfr.vblufOf(0),
            "Mfnu.submfnuPopupOffsftX", Intfgfr.vblufOf(-4),
            "Mfnu.submfnuPopupOffsftY", Intfgfr.vblufOf(-3),
            "Mfnu.drossMfnuMnfmonid", Boolfbn.FALSE,
            "Mfnu.prfsfrvfTopLfvflSflfdtion", Boolfbn.TRUE,

            // MfnuBbr.
            "MfnuBbr.font", MfnuFont,
            "MfnuBbr.bbdkground", nfw XPVbluf(MfnuBbrBbdkgroundColor,
                                              MfnuBbdkgroundColor),
            "MfnuBbr.forfground", MfnuTfxtColor,
            "MfnuBbr.sibdow", ControlSibdowColor,
            "MfnuBbr.iigiligit", ControlHigiligitColor,
            "MfnuBbr.ifigit", mfnuBbrHfigit,
            "MfnuBbr.rollovfrEnbblfd", iotTrbdkingOn,
            "MfnuBbr.windowBindings", nfw Objfdt[] {
                "F10", "tbkfFodus" },

            "MfnuItfm.font", MfnuFont,
            "MfnuItfm.bddflfrbtorFont", MfnuFont,
            "MfnuItfm.forfground", MfnuTfxtColor,
            "MfnuItfm.bbdkground", MfnuBbdkgroundColor,
            "MfnuItfm.sflfdtionForfground", SflfdtionTfxtColor,
            "MfnuItfm.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "MfnuItfm.disbblfdForfground", InbdtivfTfxtColor,
            "MfnuItfm.bddflfrbtorForfground", MfnuTfxtColor,
            "MfnuItfm.bddflfrbtorSflfdtionForfground", SflfdtionTfxtColor,
            "MfnuItfm.bddflfrbtorDflimitfr", mfnuItfmAddflfrbtorDflimitfr,
                 // Mfnu Itfm Auditory Cuf Mbpping
            "MfnuItfm.dommbndSound", "win.sound.mfnuCommbnd",
             // indidbtfs tibt kfybobrd nbvigbtion won't skip disbblfd mfnu itfms
            "MfnuItfm.disbblfdArfNbvigbblf", Boolfbn.TRUE,

            "RbdioButton.font", ControlFont,
            "RbdioButton.intfriorBbdkground", WindowBbdkgroundColor,
            "RbdioButton.bbdkground", ControlBbdkgroundColor,
            "RbdioButton.forfground", WindowTfxtColor,
            "RbdioButton.sibdow", ControlSibdowColor,
            "RbdioButton.dbrkSibdow", ControlDbrkSibdowColor,
            "RbdioButton.ligit", ControlLigitColor,
            "RbdioButton.iigiligit", ControlHigiligitColor,
            "RbdioButton.fodus", buttonFodusColor,
            "RbdioButton.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                          "SPACE", "prfssfd",
                 "rflfbsfd SPACE", "rflfbsfd"
              }),
            // mbrgin is 2 bll tif wby bround, BbsidBordfrs.RbdioButtonBordfr
            // is 2 bll tif wby bround too.
            "RbdioButton.totblInsfts", nfw Insfts(4, 4, 4, 4),


            "RbdioButtonMfnuItfm.font", MfnuFont,
            "RbdioButtonMfnuItfm.forfground", MfnuTfxtColor,
            "RbdioButtonMfnuItfm.bbdkground", MfnuBbdkgroundColor,
            "RbdioButtonMfnuItfm.sflfdtionForfground", SflfdtionTfxtColor,
            "RbdioButtonMfnuItfm.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "RbdioButtonMfnuItfm.disbblfdForfground", InbdtivfTfxtColor,
            "RbdioButtonMfnuItfm.bddflfrbtorForfground", MfnuTfxtColor,
            "RbdioButtonMfnuItfm.bddflfrbtorSflfdtionForfground", SflfdtionTfxtColor,
            "RbdioButtonMfnuItfm.dommbndSound", "win.sound.mfnuCommbnd",

            // OptionPbnf.
            "OptionPbnf.font", MfssbgfFont,
            "OptionPbnf.mfssbgfFont", MfssbgfFont,
            "OptionPbnf.buttonFont", MfssbgfFont,
            "OptionPbnf.bbdkground", ControlBbdkgroundColor,
            "OptionPbnf.forfground", WindowTfxtColor,
            "OptionPbnf.buttonMinimumWidti", nfw XPDLUVbluf(50, 50, SwingConstbnts.EAST),
            "OptionPbnf.mfssbgfForfground", ControlTfxtColor,
            "OptionPbnf.frrorIdon",       nfw LbzyWindowsIdon("optionPbnfIdon Error",
                                                              "idons/Error.gif"),
            "OptionPbnf.informbtionIdon", nfw LbzyWindowsIdon("optionPbnfIdon Informbtion",
                                                              "idons/Inform.gif"),
            "OptionPbnf.qufstionIdon",    nfw LbzyWindowsIdon("optionPbnfIdon Qufstion",
                                                              "idons/Qufstion.gif"),
            "OptionPbnf.wbrningIdon",     nfw LbzyWindowsIdon("optionPbnfIdon Wbrning",
                                                              "idons/Wbrn.gif"),
            "OptionPbnf.windowBindings", nfw Objfdt[] {
                "ESCAPE", "dlosf" },
                 // Option Pbnf Auditory Cuf Mbppings
            "OptionPbnf.frrorSound", "win.sound.ibnd", // Error
            "OptionPbnf.informbtionSound", "win.sound.bstfrisk", // Info Plbin
            "OptionPbnf.qufstionSound", "win.sound.qufstion", // Qufstion
            "OptionPbnf.wbrningSound", "win.sound.fxdlbmbtion", // Wbrning

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
            "FormbttfdTfxtFifld.inbdtivfBbdkground", RfbdOnlyTfxtBbdkground,
            "FormbttfdTfxtFifld.disbblfdBbdkground", DisbblfdTfxtBbdkground,

            // *** Pbnfl
            "Pbnfl.font", ControlFont,
            "Pbnfl.bbdkground", ControlBbdkgroundColor,
            "Pbnfl.forfground", WindowTfxtColor,

            // *** PbsswordFifld
            "PbsswordFifld.font", ControlFont,
            "PbsswordFifld.bbdkground", TfxtBbdkground,
            "PbsswordFifld.forfground", WindowTfxtColor,
            "PbsswordFifld.inbdtivfForfground", InbdtivfTfxtColor,      // for disbblfd
            "PbsswordFifld.inbdtivfBbdkground", RfbdOnlyTfxtBbdkground, // for rfbdonly
            "PbsswordFifld.disbblfdBbdkground", DisbblfdTfxtBbdkground, // for disbblfd
            "PbsswordFifld.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "PbsswordFifld.sflfdtionForfground", SflfdtionTfxtColor,
            "PbsswordFifld.dbrftForfground",WindowTfxtColor,
            "PbsswordFifld.fdioCibr", nfw XPVbluf((dibr)0x25CF, '*'),

            // *** ProgrfssBbr
            "ProgrfssBbr.font", ControlFont,
            "ProgrfssBbr.forfground",  SflfdtionBbdkgroundColor,
            "ProgrfssBbr.bbdkground", ControlBbdkgroundColor,
            "ProgrfssBbr.sibdow", ControlSibdowColor,
            "ProgrfssBbr.iigiligit", ControlHigiligitColor,
            "ProgrfssBbr.sflfdtionForfground", ControlBbdkgroundColor,
            "ProgrfssBbr.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "ProgrfssBbr.dfllLfngti", Intfgfr.vblufOf(7),
            "ProgrfssBbr.dfllSpbding", Intfgfr.vblufOf(2),
            "ProgrfssBbr.indftfrminbtfInsfts", nfw Insfts(3, 3, 3, 3),

            // *** RootPbnf.
            // Tifsf bindings brf only fnbblfd wifn tifrf is b dffbult
            // button sft on tif rootpbnf.
            "RootPbnf.dffbultButtonWindowKfyBindings", nfw Objfdt[] {
                             "ENTER", "prfss",
                    "rflfbsfd ENTER", "rflfbsf",
                        "dtrl ENTER", "prfss",
               "dtrl rflfbsfd ENTER", "rflfbsf"
              },

            // *** SdrollBbr.
            "SdrollBbr.bbdkground", SdrollbbrBbdkgroundColor,
            "SdrollBbr.forfground", ControlBbdkgroundColor,
            "SdrollBbr.trbdk", wiitf,
            "SdrollBbr.trbdkForfground", SdrollbbrBbdkgroundColor,
            "SdrollBbr.trbdkHigiligit", blbdk,
            "SdrollBbr.trbdkHigiligitForfground", sdrollBbrTrbdkHigiligit,
            "SdrollBbr.tiumb", ControlBbdkgroundColor,
            "SdrollBbr.tiumbHigiligit", ControlHigiligitColor,
            "SdrollBbr.tiumbDbrkSibdow", ControlDbrkSibdowColor,
            "SdrollBbr.tiumbSibdow", ControlSibdowColor,
            "SdrollBbr.widti", sdrollBbrWidti,
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

            // *** SdrollPbnf.
            "SdrollPbnf.font", ControlFont,
            "SdrollPbnf.bbdkground", ControlBbdkgroundColor,
            "SdrollPbnf.forfground", ControlTfxtColor,
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

            // *** Sfpbrbtor
            "Sfpbrbtor.bbdkground", ControlHigiligitColor,
            "Sfpbrbtor.forfground", ControlSibdowColor,

            // *** Slidfr.
            "Slidfr.font", ControlFont,
            "Slidfr.forfground", ControlBbdkgroundColor,
            "Slidfr.bbdkground", ControlBbdkgroundColor,
            "Slidfr.iigiligit", ControlHigiligitColor,
            "Slidfr.sibdow", ControlSibdowColor,
            "Slidfr.fodus", ControlDbrkSibdowColor,
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

            // Spinnfr
            "Spinnfr.font", ControlFont,
            "Spinnfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                               "UP", "indrfmfnt",
                            "KP_UP", "indrfmfnt",
                             "DOWN", "dfdrfmfnt",
                          "KP_DOWN", "dfdrfmfnt",
               }),

            // *** SplitPbnf
            "SplitPbnf.bbdkground", ControlBbdkgroundColor,
            "SplitPbnf.iigiligit", ControlHigiligitColor,
            "SplitPbnf.sibdow", ControlSibdowColor,
            "SplitPbnf.dbrkSibdow", ControlDbrkSibdowColor,
            "SplitPbnf.dividfrSizf", Intfgfr.vblufOf(5),
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
            "TbbbfdPbnf.tbbsOvfrlbpBordfr", nfw XPVbluf(Boolfbn.TRUE, Boolfbn.FALSE),
            "TbbbfdPbnf.tbbInsfts",         nfw XPVbluf(nfw InsftsUIRfsourdf(1, 4, 1, 4),
                                                        nfw InsftsUIRfsourdf(0, 4, 1, 4)),
            "TbbbfdPbnf.tbbArfbInsfts",     nfw XPVbluf(nfw InsftsUIRfsourdf(3, 2, 2, 2),
                                                        nfw InsftsUIRfsourdf(3, 2, 0, 2)),
            "TbbbfdPbnf.font", ControlFont,
            "TbbbfdPbnf.bbdkground", ControlBbdkgroundColor,
            "TbbbfdPbnf.forfground", ControlTfxtColor,
            "TbbbfdPbnf.iigiligit", ControlHigiligitColor,
            "TbbbfdPbnf.ligit", ControlLigitColor,
            "TbbbfdPbnf.sibdow", ControlSibdowColor,
            "TbbbfdPbnf.dbrkSibdow", ControlDbrkSibdowColor,
            "TbbbfdPbnf.fodus", ControlTfxtColor,
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
                         "dtrl TAB", "nbvigbtfNfxt",
                   "dtrl siift TAB", "nbvigbtfPrfvious",
                   "dtrl PAGE_DOWN", "nbvigbtfPbgfDown",
                     "dtrl PAGE_UP", "nbvigbtfPbgfUp",
                          "dtrl UP", "rfqufstFodus",
                       "dtrl KP_UP", "rfqufstFodus",
                 }),

            // *** Tbblf
            "Tbblf.font", ControlFont,
            "Tbblf.forfground", ControlTfxtColor,  // dfll tfxt dolor
            "Tbblf.bbdkground", WindowBbdkgroundColor,  // dfll bbdkground dolor
            "Tbblf.iigiligit", ControlHigiligitColor,
            "Tbblf.ligit", ControlLigitColor,
            "Tbblf.sibdow", ControlSibdowColor,
            "Tbblf.dbrkSibdow", ControlDbrkSibdowColor,
            "Tbblf.sflfdtionForfground", SflfdtionTfxtColor,
            "Tbblf.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "Tbblf.gridColor", grby,  // grid linf dolor
            "Tbblf.fodusCfllBbdkground", WindowBbdkgroundColor,
            "Tbblf.fodusCfllForfground", ControlTfxtColor,
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
            "Tbblf.sortIdonHigiligit", ControlSibdowColor,
            "Tbblf.sortIdonLigit", wiitf,

            "TbblfHfbdfr.font", ControlFont,
            "TbblfHfbdfr.forfground", ControlTfxtColor, // ifbdfr tfxt dolor
            "TbblfHfbdfr.bbdkground", ControlBbdkgroundColor, // ifbdfr bbdkground
            "TbblfHfbdfr.fodusCfllBbdkground",
                nfw XPVbluf(XPVbluf.NULL_VALUE,     // usf dffbult bg from XP stylfs
                            WindowBbdkgroundColor), // or wiitf bg otifrwisf

            // *** TfxtArfb
            "TfxtArfb.font", FixfdControlFont,
            "TfxtArfb.bbdkground", WindowBbdkgroundColor,
            "TfxtArfb.forfground", WindowTfxtColor,
            "TfxtArfb.inbdtivfForfground", InbdtivfTfxtColor,
            "TfxtArfb.inbdtivfBbdkground", WindowBbdkgroundColor,
            "TfxtArfb.disbblfdBbdkground", DisbblfdTfxtBbdkground,
            "TfxtArfb.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "TfxtArfb.sflfdtionForfground", SflfdtionTfxtColor,
            "TfxtArfb.dbrftForfground", WindowTfxtColor,

            // *** TfxtFifld
            "TfxtFifld.font", ControlFont,
            "TfxtFifld.bbdkground", TfxtBbdkground,
            "TfxtFifld.forfground", WindowTfxtColor,
            "TfxtFifld.sibdow", ControlSibdowColor,
            "TfxtFifld.dbrkSibdow", ControlDbrkSibdowColor,
            "TfxtFifld.ligit", ControlLigitColor,
            "TfxtFifld.iigiligit", ControlHigiligitColor,
            "TfxtFifld.inbdtivfForfground", InbdtivfTfxtColor,      // for disbblfd
            "TfxtFifld.inbdtivfBbdkground", RfbdOnlyTfxtBbdkground, // for rfbdonly
            "TfxtFifld.disbblfdBbdkground", DisbblfdTfxtBbdkground, // for disbblfd
            "TfxtFifld.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "TfxtFifld.sflfdtionForfground", SflfdtionTfxtColor,
            "TfxtFifld.dbrftForfground", WindowTfxtColor,

            // *** TfxtPbnf
            "TfxtPbnf.font", ControlFont,
            "TfxtPbnf.bbdkground", WindowBbdkgroundColor,
            "TfxtPbnf.forfground", WindowTfxtColor,
            "TfxtPbnf.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "TfxtPbnf.sflfdtionForfground", SflfdtionTfxtColor,
            "TfxtPbnf.inbdtivfBbdkground", WindowBbdkgroundColor,
            "TfxtPbnf.disbblfdBbdkground", DisbblfdTfxtBbdkground,
            "TfxtPbnf.dbrftForfground", WindowTfxtColor,

            // *** TitlfdBordfr
            "TitlfdBordfr.font", ControlFont,
            "TitlfdBordfr.titlfColor",
                        nfw XPColorVbluf(Pbrt.BP_GROUPBOX, null, Prop.TEXTCOLOR,
                                         WindowTfxtColor),

            // *** TogglfButton
            "TogglfButton.font", ControlFont,
            "TogglfButton.bbdkground", ControlBbdkgroundColor,
            "TogglfButton.forfground", ControlTfxtColor,
            "TogglfButton.sibdow", ControlSibdowColor,
            "TogglfButton.dbrkSibdow", ControlDbrkSibdowColor,
            "TogglfButton.ligit", ControlLigitColor,
            "TogglfButton.iigiligit", ControlHigiligitColor,
            "TogglfButton.fodus", ControlTfxtColor,
            "TogglfButton.tfxtSiiftOffsft", Intfgfr.vblufOf(1),
            "TogglfButton.fodusInputMbp",
              nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                }),

            // *** ToolBbr
            "ToolBbr.font", MfnuFont,
            "ToolBbr.bbdkground", ControlBbdkgroundColor,
            "ToolBbr.forfground", ControlTfxtColor,
            "ToolBbr.sibdow", ControlSibdowColor,
            "ToolBbr.dbrkSibdow", ControlDbrkSibdowColor,
            "ToolBbr.ligit", ControlLigitColor,
            "ToolBbr.iigiligit", ControlHigiligitColor,
            "ToolBbr.dodkingBbdkground", ControlBbdkgroundColor,
            "ToolBbr.dodkingForfground", rfd,
            "ToolBbr.flobtingBbdkground", ControlBbdkgroundColor,
            "ToolBbr.flobtingForfground", dbrkGrby,
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
            "ToolBbr.sfpbrbtorSizf", null,

            // *** ToolTip
            "ToolTip.font", ToolTipFont,
            "ToolTip.bbdkground", nfw DfsktopPropfrty("win.tooltip.bbdkgroundColor", tbblf.gft("info")),
            "ToolTip.forfground", nfw DfsktopPropfrty("win.tooltip.tfxtColor", tbblf.gft("infoTfxt")),

        // *** ToolTipMbnbgfr
            "ToolTipMbnbgfr.fnbblfToolTipModf", "bdtivfApplidbtion",

        // *** Trff
            "Trff.sflfdtionBordfrColor", blbdk,
            "Trff.drbwDbsifdFodusIndidbtor", Boolfbn.TRUE,
            "Trff.linfTypfDbsifd", Boolfbn.TRUE,
            "Trff.font", ControlFont,
            "Trff.bbdkground", WindowBbdkgroundColor,
            "Trff.forfground", WindowTfxtColor,
            "Trff.ibsi", grby,
            "Trff.lfftCiildIndfnt", Intfgfr.vblufOf(8),
            "Trff.rigitCiildIndfnt", Intfgfr.vblufOf(11),
            "Trff.tfxtForfground", WindowTfxtColor,
            "Trff.tfxtBbdkground", WindowBbdkgroundColor,
            "Trff.sflfdtionForfground", SflfdtionTfxtColor,
            "Trff.sflfdtionBbdkground", SflfdtionBbdkgroundColor,
            "Trff.fxpbndfdIdon", trffExpbndfdIdon,
            "Trff.dollbpsfdIdon", trffCollbpsfdIdon,
            "Trff.opfnIdon",   nfw AdtivfWindowsIdon("win.idon.sifllIdonBPP",
                                   "sifll32Idon 5", "idons/TrffOpfn.gif"),
            "Trff.dlosfdIdon", nfw AdtivfWindowsIdon("win.idon.sifllIdonBPP",
                                   "sifll32Idon 4", "idons/TrffClosfd.gif"),
            "Trff.fodusInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                                    "ADD", "fxpbnd",
                               "SUBTRACT", "dollbpsf",
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
            "Trff.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "dbndfl"
                 }),

            // *** Vifwport
            "Vifwport.font", ControlFont,
            "Vifwport.bbdkground", ControlBbdkgroundColor,
            "Vifwport.forfground", WindowTfxtColor,


        };

        tbblf.putDffbults(dffbults);
        tbblf.putDffbults(gftLbzyVblufDffbults());
        initVistbComponfntDffbults(tbblf);
    }

    stbtid boolfbn isOnVistb() {
        rfturn OSInfo.gftOSTypf() == OSInfo.OSTypf.WINDOWS
                && OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_VISTA) >= 0;
    }

    privbtf void initVistbComponfntDffbults(UIDffbults tbblf) {
        if (! isOnVistb()) {
            rfturn;
        }
        /* START ibndling mfnus for Vistb */
        String[] mfnuClbssfs = { "MfnuItfm", "Mfnu",
                "CifdkBoxMfnuItfm", "RbdioButtonMfnuItfm",
        };

        Objfdt mfnuDffbults[] = nfw Objfdt[mfnuClbssfs.lfngti * 2];

        /* bll tif mfnus nffd to bf non opbquf. */
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".opbquf";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] =
                nfw XPVbluf(Boolfbn.FALSE, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /*
         * bddflfrbtorSflfdtionForfground dolor is tif sbmf bs
         * bddflfrbtorForfground
         */
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".bddflfrbtorSflfdtionForfground";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] =
                nfw XPVbluf(
                    tbblf.gftColor(
                        mfnuClbssfs[i] + ".bddflfrbtorForfground"),
                        oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /* tify ibvf tif sbmf MfnuItfmCifdkIdonFbdtory */
        VistbMfnuItfmCifdkIdonFbdtory mfnuItfmCifdkIdonFbdtory =
            WindowsIdonFbdtory.gftMfnuItfmCifdkIdonFbdtory();
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".difdkIdonFbdtory";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] =
                nfw XPVbluf(mfnuItfmCifdkIdonFbdtory, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".difdkIdon";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] =
                nfw XPVbluf(mfnuItfmCifdkIdonFbdtory.gftIdon(mfnuClbssfs[i]),
                    oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);


        /* ifigit dbn bf fvfn */
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".fvfnHfigit";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] = nfw XPVbluf(Boolfbn.TRUE, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /* no mbrgins */
        InsftsUIRfsourdf insfts = nfw InsftsUIRfsourdf(0, 0, 0, 0);
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".mbrgin";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] = nfw XPVbluf(insfts, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /* sft difdkIdon offsft */
        Intfgfr difdkIdonOffsftIntfgfr =
            Intfgfr.vblufOf(0);
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".difdkIdonOffsft";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] =
                nfw XPVbluf(difdkIdonOffsftIntfgfr, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /* sft widti of tif gbp bftfr difdk idon */
        Intfgfr bftfrCifdkIdonGbp = WindowsPopupMfnuUI.gftSpbnBfforfGuttfr()
                + WindowsPopupMfnuUI.gftGuttfrWidti()
                + WindowsPopupMfnuUI.gftSpbnAftfrGuttfr();
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".bftfrCifdkIdonGbp";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] =
                nfw XPVbluf(bftfrCifdkIdonGbp, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /* tfxt is stbrtfd bftfr tiis position */
        Objfdt minimumTfxtOffsft = nfw UIDffbults.AdtivfVbluf() {
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                rfturn VistbMfnuItfmCifdkIdonFbdtory.gftIdonWidti()
                + WindowsPopupMfnuUI.gftSpbnBfforfGuttfr()
                + WindowsPopupMfnuUI.gftGuttfrWidti()
                + WindowsPopupMfnuUI.gftSpbnAftfrGuttfr();
            }
        };
        for (int i = 0, j = 0; i < mfnuClbssfs.lfngti; i++) {
            String kfy = mfnuClbssfs[i] + ".minimumTfxtOffsft";
            Objfdt oldVbluf = tbblf.gft(kfy);
            mfnuDffbults[j++] = kfy;
            mfnuDffbults[j++] = nfw XPVbluf(minimumTfxtOffsft, oldVbluf);
        }
        tbblf.putDffbults(mfnuDffbults);

        /*
         * JPopupMfnu ibs b bit of frff spbdf bround mfnu itfms
         */
        String POPUP_MENU_BORDER = "PopupMfnu.bordfr";

        Objfdt popupMfnuBordfr = nfw XPBordfrVbluf(Pbrt.MENU,
            (LbzyVbluf) t -> BbsidBordfrs.gftIntfrnblFrbmfBordfr(),
                  BordfrFbdtory.drfbtfEmptyBordfr(2, 2, 2, 2));
        tbblf.put(POPUP_MENU_BORDER, popupMfnuBordfr);
        /* END ibndling mfnus for Vistb */

        /* START tbblf ibndling for Vistb */
        tbblf.put("Tbblf.bsdfndingSortIdon", nfw XPVbluf(
            nfw SkinIdon(Pbrt.HP_HEADERSORTARROW, Stbtf.SORTEDDOWN),
               (LbzyVbluf) t -> nfw ClbssidSortArrowIdon(truf)));
        tbblf.put("Tbblf.dfsdfndingSortIdon", nfw XPVbluf(
            nfw SkinIdon(Pbrt.HP_HEADERSORTARROW, Stbtf.SORTEDUP),
               (LbzyVbluf) t -> nfw ClbssidSortArrowIdon(fblsf)));
        /* END tbblf ibndling for Vistb */
    }

    /**
     * If wf support lobding of fonts from tif dfsktop tiis will rfturn
     * b DfsktopPropfrty rfprfsfnting tif font. If tif font dbn't bf
     * rfprfsfntfd in tif durrfnt fndoding tiis will rfturn null bnd
     * turn off tif usf of systfm fonts.
     */
    privbtf Objfdt gftDfsktopFontVbluf(String fontNbmf, Objfdt bbdkup) {
        if (usfSystfmFontSfttings) {
            rfturn nfw WindowsFontPropfrty(fontNbmf, bbdkup);
        }
        rfturn null;
    }

    // Wifn b dfsktop propfrty dibngf is dftfdtfd, tifsf dlbssfs must bf
    // rfinitiblizfd in tif dffbults tbblf to fnsurf tif dlbssfs rfffrfndf
    // tif updbtfd dfsktop propfrty vblufs (dolors mostly)
    //
    privbtf Objfdt[] gftLbzyVblufDffbults() {

        Objfdt buttonBordfr =
            nfw XPBordfrVbluf(Pbrt.BP_PUSHBUTTON,
               (LbzyVbluf) t -> BbsidBordfrs.gftButtonBordfr());

        Objfdt tfxtFifldBordfr =
            nfw XPBordfrVbluf(Pbrt.EP_EDIT,
               (LbzyVbluf) t -> BbsidBordfrs.gftTfxtFifldBordfr());

        Objfdt tfxtFifldMbrgin =
            nfw XPVbluf(nfw InsftsUIRfsourdf(2, 2, 2, 2),
                        nfw InsftsUIRfsourdf(1, 1, 1, 1));

        Objfdt spinnfrBordfr =
            nfw XPBordfrVbluf(Pbrt.EP_EDIT, tfxtFifldBordfr,
                              nfw EmptyBordfr(2, 2, 2, 2));

        Objfdt spinnfrArrowInsfts =
            nfw XPVbluf(nfw InsftsUIRfsourdf(1, 1, 1, 1),
                        null);

        Objfdt domboBoxBordfr = nfw XPBordfrVbluf(Pbrt.CP_COMBOBOX, tfxtFifldBordfr);

        // For fodus rfdtbnglf for dflls bnd trffs.
        LbzyVbluf fodusCfllHigiligitBordfr = t -> WindowsBordfrs.gftFodusCfllHigiligitBordfr();

        LbzyVbluf ftdifdBordfr = t -> BordfrUIRfsourdf.gftEtdifdBordfrUIRfsourdf();

        LbzyVbluf intfrnblFrbmfBordfr = t -> WindowsBordfrs.gftIntfrnblFrbmfBordfr();

        LbzyVbluf lowfrfdBfvflBordfr = t -> BordfrUIRfsourdf.gftLowfrfdBfvflBordfrUIRfsourdf();


        LbzyVbluf mbrginBordfr = t -> nfw BbsidBordfrs.MbrginBordfr();

        LbzyVbluf mfnuBbrBordfr = t -> BbsidBordfrs.gftMfnuBbrBordfr();


        Objfdt popupMfnuBordfr = nfw XPBordfrVbluf(Pbrt.MENU,
            (LbzyVbluf) t -> BbsidBordfrs.gftIntfrnblFrbmfBordfr());

        // *** ProgrfssBbr
        LbzyVbluf progrfssBbrBordfr = t -> WindowsBordfrs.gftProgrfssBbrBordfr();

        LbzyVbluf rbdioButtonBordfr = t -> BbsidBordfrs.gftRbdioButtonBordfr();

        Objfdt sdrollPbnfBordfr =
            nfw XPBordfrVbluf(Pbrt.LBP_LISTBOX, tfxtFifldBordfr);

        Objfdt tbblfSdrollPbnfBordfr =
            nfw XPBordfrVbluf(Pbrt.LBP_LISTBOX, lowfrfdBfvflBordfr);

        LbzyVbluf tbblfHfbdfrBordfr = t -> WindowsBordfrs.gftTbblfHfbdfrBordfr();

        // *** ToolBbr
        LbzyVbluf toolBbrBordfr = t -> WindowsBordfrs.gftToolBbrBordfr();

        // *** ToolTips
        LbzyVbluf toolTipBordfr = t -> BordfrUIRfsourdf.gftBlbdkLinfBordfrUIRfsourdf();



        LbzyVbluf difdkBoxIdon = t -> WindowsIdonFbdtory.gftCifdkBoxIdon();

        LbzyVbluf rbdioButtonIdon = t -> WindowsIdonFbdtory.gftRbdioButtonIdon();

        LbzyVbluf rbdioButtonMfnuItfmIdon = t -> WindowsIdonFbdtory.gftRbdioButtonMfnuItfmIdon();

        LbzyVbluf mfnuItfmCifdkIdon = t -> WindowsIdonFbdtory.gftMfnuItfmCifdkIdon();

        LbzyVbluf mfnuItfmArrowIdon = t -> WindowsIdonFbdtory.gftMfnuItfmArrowIdon();

        LbzyVbluf mfnuArrowIdon = t -> WindowsIdonFbdtory.gftMfnuArrowIdon();


        Objfdt[] lbzyDffbults = {
            "Button.bordfr", buttonBordfr,
            "CifdkBox.bordfr", rbdioButtonBordfr,
            "ComboBox.bordfr", domboBoxBordfr,
            "DfsktopIdon.bordfr", intfrnblFrbmfBordfr,
            "FormbttfdTfxtFifld.bordfr", tfxtFifldBordfr,
            "FormbttfdTfxtFifld.mbrgin", tfxtFifldMbrgin,
            "IntfrnblFrbmf.bordfr", intfrnblFrbmfBordfr,
            "List.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "Tbblf.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "Mfnu.bordfr", mbrginBordfr,
            "MfnuBbr.bordfr", mfnuBbrBordfr,
            "MfnuItfm.bordfr", mbrginBordfr,
            "PbsswordFifld.bordfr", tfxtFifldBordfr,
            "PbsswordFifld.mbrgin", tfxtFifldMbrgin,
            "PopupMfnu.bordfr", popupMfnuBordfr,
            "ProgrfssBbr.bordfr", progrfssBbrBordfr,
            "RbdioButton.bordfr", rbdioButtonBordfr,
            "SdrollPbnf.bordfr", sdrollPbnfBordfr,
            "Spinnfr.bordfr", spinnfrBordfr,
            "Spinnfr.brrowButtonInsfts", spinnfrArrowInsfts,
            "Spinnfr.brrowButtonSizf", nfw Dimfnsion(17, 9),
            "Tbblf.sdrollPbnfBordfr", tbblfSdrollPbnfBordfr,
            "TbblfHfbdfr.dfllBordfr", tbblfHfbdfrBordfr,
            "TfxtArfb.mbrgin", tfxtFifldMbrgin,
            "TfxtFifld.bordfr", tfxtFifldBordfr,
            "TfxtFifld.mbrgin", tfxtFifldMbrgin,
            "TitlfdBordfr.bordfr",
                        nfw XPBordfrVbluf(Pbrt.BP_GROUPBOX, ftdifdBordfr),
            "TogglfButton.bordfr", rbdioButtonBordfr,
            "ToolBbr.bordfr", toolBbrBordfr,
            "ToolTip.bordfr", toolTipBordfr,

            "CifdkBox.idon", difdkBoxIdon,
            "Mfnu.brrowIdon", mfnuArrowIdon,
            "MfnuItfm.difdkIdon", mfnuItfmCifdkIdon,
            "MfnuItfm.brrowIdon", mfnuItfmArrowIdon,
            "RbdioButton.idon", rbdioButtonIdon,
            "RbdioButtonMfnuItfm.difdkIdon", rbdioButtonMfnuItfmIdon,
            "IntfrnblFrbmf.lbyoutTitlfPbnfAtOrigin",
                        nfw XPVbluf(Boolfbn.TRUE, Boolfbn.FALSE),
            "Tbblf.bsdfndingSortIdon", nfw XPVbluf(
               (LbzyVbluf) t -> nfw SortArrowIdon(truf,"Tbblf.sortIdonColor"),
                  (LbzyVbluf) t -> nfw ClbssidSortArrowIdon(truf)),
            "Tbblf.dfsdfndingSortIdon", nfw XPVbluf(
               (LbzyVbluf) t -> nfw SortArrowIdon(fblsf,"Tbblf.sortIdonColor"),
                  (LbzyVbluf) t -> nfw ClbssidSortArrowIdon(fblsf)),
        };

        rfturn lbzyDffbults;
    }

    publid void uninitiblizf() {
        supfr.uninitiblizf();

        if (WindowsPopupMfnuUI.mnfmonidListfnfr != null) {
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().
                rfmovfCibngfListfnfr(WindowsPopupMfnuUI.mnfmonidListfnfr);
        }
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
            rfmovfKfyEvfntPostProdfssor(WindowsRootPbnfUI.bltProdfssor);
        DfsktopPropfrty.flusiUnrfffrfndfdPropfrtifs();
    }


    // Togglf flbg for drbwing tif mnfmonid stbtf
    privbtf stbtid boolfbn isMnfmonidHiddfn = truf;

    // Flbg wiidi indidbtfs tibt tif Win98/Win2k/WinME ffbturfs
    // siould bf disbblfd.
    privbtf stbtid boolfbn isClbssidWindows = fblsf;

    /**
     * Sfts tif stbtf of tif iidf mnfmonid flbg. Tiis flbg is usfd by tif
     * domponfnt UI dflfgbtfs to dftfrminf if tif mnfmonid siould bf rfndfrfd.
     * Tiis mftiod is b non opfrbtion if tif undfrlying opfrbting systfm
     * dofs not support tif mnfmonid iiding ffbturf.
     *
     * @pbrbm iidf truf if mnfmonids siould bf iiddfn
     * @sindf 1.4
     */
    publid stbtid void sftMnfmonidHiddfn(boolfbn iidf) {
        if (UIMbnbgfr.gftBoolfbn("Button.siowMnfmonids") == truf) {
            // Do not iidf mnfmonids if tif UI dffbults do not support tiis
            isMnfmonidHiddfn = fblsf;
        } flsf {
            isMnfmonidHiddfn = iidf;
        }
    }

    /**
     * Gfts tif stbtf of tif iidf mnfmonid flbg. Tiis only ibs mfbning
     * if tiis ffbturf is supportfd by tif undfrlying OS.
     *
     * @rfturn truf if mnfmonids brf iiddfn, otifrwisf, fblsf
     * @sff #sftMnfmonidHiddfn
     * @sindf 1.4
     */
    publid stbtid boolfbn isMnfmonidHiddfn() {
        if (UIMbnbgfr.gftBoolfbn("Button.siowMnfmonids") == truf) {
            // Do not iidf mnfmonids if tif UI dffbults do not support tiis
            isMnfmonidHiddfn = fblsf;
        }
        rfturn isMnfmonidHiddfn;
    }

    /**
     * Gfts tif stbtf of tif flbg wiidi indidbtfs if tif old Windows
     * look bnd fffl siould bf rfndfrfd. Tiis flbg is usfd by tif
     * domponfnt UI dflfgbtfs bs b iint to dftfrminf wiidi stylf tif domponfnt
     * siould bf rfndfrfd.
     *
     * @rfturn truf if Windows 95 bnd Windows NT 4 look bnd fffl siould
     *         bf rfndfrfd
     * @sindf 1.4
     */
    publid stbtid boolfbn isClbssidWindows() {
        rfturn isClbssidWindows;
    }

    /**
     * <p>
     * Invokfd wifn tif usfr bttfmpts bn invblid opfrbtion,
     * sudi bs pbsting into bn unfditbblf <dodf>JTfxtFifld</dodf>
     * tibt ibs fodus.
     * </p>
     * <p>
     * If tif usfr ibs fnbblfd visubl frror indidbtion on
     * tif dfsktop, tiis mftiod will flbsi tif dbption bbr
     * of tif bdtivf window. Tif usfr dbn blso sft tif
     * propfrty bwt.visublbfll=truf to bdiifvf tif sbmf
     * rfsults.
     * </p>
     *
     * @pbrbm domponfnt Componfnt tif frror oddurrfd in, mby bf
     *                  null indidbting tif frror dondition is
     *                  not dirfdtly bssodibtfd witi b
     *                  <dodf>Componfnt</dodf>.
     *
     * @sff jbvbx.swing.LookAndFffl#providfErrorFffdbbdk
     */
     publid void providfErrorFffdbbdk(Componfnt domponfnt) {
         supfr.providfErrorFffdbbdk(domponfnt);
     }

    /**
     * {@inifritDod}
     */
    publid LbyoutStylf gftLbyoutStylf() {
        LbyoutStylf stylf = tiis.stylf;
        if (stylf == null) {
            stylf = nfw WindowsLbyoutStylf();
            tiis.stylf = stylf;
        }
        rfturn stylf;
    }

    // ********* Auditory Cuf support mftiods bnd objfdts *********

    /**
     * Rfturns bn <dodf>Adtion</dodf>.
     * <P>
     * Tiis Adtion dontbins tif informbtion bnd logid to rfndfr bn
     * buditory duf. Tif <dodf>Objfdt</dodf> tibt is pbssfd to tiis
     * mftiod dontbins tif informbtion nffdfd to rfndfr tif buditory
     * duf. Normblly, tiis <dodf>Objfdt</dodf> is b <dodf>String</dodf>
     * tibt points to b <dodf>Toolkit</dodf> <dodf>dfsktopPropfrty</dodf>.
     * Tiis <dodf>dfsktopPropfrty</dodf> is rfsolvfd by AWT bnd tif
     * Windows OS.
     * <P>
     * Tiis <dodf>Adtion</dodf>'s <dodf>bdtionPfrformfd</dodf> mftiod
     * is firfd by tif <dodf>plbySound</dodf> mftiod.
     *
     * @rfturn      bn Adtion wiidi knows iow to rfndfr tif buditory
     *              duf for onf pbrtidulbr systfm or usfr bdtivity
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

    stbtid void rfpbintRootPbnf(Componfnt d) {
        JRootPbnf root = null;
        for (; d != null; d = d.gftPbrfnt()) {
            if (d instbndfof JRootPbnf) {
                root = (JRootPbnf)d;
            }
        }

        if (root != null) {
            root.rfpbint();
        } flsf {
            d.rfpbint();
        }
    }

    /**
     * Pbss tif nbmf String to tif supfr donstrudtor. Tiis is usfd
     * lbtfr to idfntify tif Adtion bnd dfdidf wiftifr to plby it or
     * not. Storf tif rfsourdf String. It is usfd to gft tif budio
     * rfsourdf. In tiis dbsf, tif rfsourdf is b <dodf>Runnbblf</dodf>
     * supplifd by <dodf>Toolkit</dodf>. Tiis <dodf>Runnbblf</dodf> is
     * ffffdtivfly b pointfr down into tif Win32 OS tibt knows iow to
     * plby tif rigit sound.
     *
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss AudioAdtion fxtfnds AbstrbdtAdtion {
        privbtf Runnbblf budioRunnbblf;
        privbtf String budioRfsourdf;
        /**
         * Wf usf tif String bs tif nbmf of tif Adtion bnd bs b pointfr to
         * tif undfrlying OSfs budio rfsourdf.
         */
        publid AudioAdtion(String nbmf, String rfsourdf) {
            supfr(nbmf);
            budioRfsourdf = rfsourdf;
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (budioRunnbblf == null) {
                budioRunnbblf = (Runnbblf)Toolkit.gftDffbultToolkit().gftDfsktopPropfrty(budioRfsourdf);
            }
            if (budioRunnbblf != null) {
                // Runnbblf bppfbrs to blodk until domplftfd plbying, ifndf
                // stbrt up bnotifr tirfbd to ibndlf plbying.
                nfw Tirfbd(budioRunnbblf).stbrt();
            }
        }
    }

    /**
     * Gfts bn <dodf>Idon</dodf> from tif nbtivf librbrifs if bvbilbblf,
     * otifrwisf gfts it from bn imbgf rfsourdf filf.
     */
    privbtf stbtid dlbss LbzyWindowsIdon implfmfnts UIDffbults.LbzyVbluf {
        privbtf String nbtivfImbgf;
        privbtf String rfsourdf;

        LbzyWindowsIdon(String nbtivfImbgf, String rfsourdf) {
            tiis.nbtivfImbgf = nbtivfImbgf;
            tiis.rfsourdf = rfsourdf;
        }

        publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
            if (nbtivfImbgf != null) {
                Imbgf imbgf = (Imbgf)SifllFoldfr.gft(nbtivfImbgf);
                if (imbgf != null) {
                    rfturn nfw ImbgfIdon(imbgf);
                }
            }
            rfturn SwingUtilitifs2.mbkfIdon(gftClbss(),
                                            WindowsLookAndFffl.dlbss,
                                            rfsourdf);
        }
    }


    /**
     * Gfts bn <dodf>Idon</dodf> from tif nbtivf librbrifs if bvbilbblf.
     * A dfsktop propfrty is usfd to triggfr rflobding tif idon wifn nffdfd.
     */
    privbtf dlbss AdtivfWindowsIdon implfmfnts UIDffbults.AdtivfVbluf {
        privbtf Idon idon;
        privbtf String nbtivfImbgfNbmf;
        privbtf String fbllbbdkNbmf;
        privbtf DfsktopPropfrty dfsktopPropfrty;

        AdtivfWindowsIdon(String dfsktopPropfrtyNbmf,
                            String nbtivfImbgfNbmf, String fbllbbdkNbmf) {
            tiis.nbtivfImbgfNbmf = nbtivfImbgfNbmf;
            tiis.fbllbbdkNbmf = fbllbbdkNbmf;

            if (OSInfo.gftOSTypf() == OSInfo.OSTypf.WINDOWS &&
                    OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_XP) < 0) {
                // Tiis dfsktop propfrty is nffdfd to triggfr rflobding tif idon.
                // It is kfpt in mfmbfr vbribblf to bvoid GC.
                tiis.dfsktopPropfrty = nfw TriggfrDfsktopPropfrty(dfsktopPropfrtyNbmf) {
                    @Ovfrridf protfdtfd void updbtfUI() {
                        idon = null;
                        supfr.updbtfUI();
                    }
                };
            }
        }

        @Ovfrridf
        publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
            if (idon == null) {
                Imbgf imbgf = (Imbgf)SifllFoldfr.gft(nbtivfImbgfNbmf);
                if (imbgf != null) {
                    idon = nfw ImbgfIdonUIRfsourdf(imbgf);
                }
            }
            if (idon == null && fbllbbdkNbmf != null) {
                UIDffbults.LbzyVbluf fbllbbdk = (UIDffbults.LbzyVbluf)
                        SwingUtilitifs2.mbkfIdon(WindowsLookAndFffl.dlbss,
                            BbsidLookAndFffl.dlbss, fbllbbdkNbmf);
                idon = (Idon) fbllbbdk.drfbtfVbluf(tbblf);
            }
            rfturn idon;
        }
    }

    /**
     * Idon bbdkfd-up by XP Skin.
     */
    privbtf stbtid dlbss SkinIdon implfmfnts Idon, UIRfsourdf {
        privbtf finbl Pbrt pbrt;
        privbtf finbl Stbtf stbtf;
        SkinIdon(Pbrt pbrt, Stbtf stbtf) {
            tiis.pbrt = pbrt;
            tiis.stbtf = stbtf;
        }

        /**
         * Drbw tif idon bt tif spfdififd lodbtion.  Idon implfmfntbtions
         * mby usf tif Componfnt brgumfnt to gft propfrtifs usfful for
         * pbinting, f.g. tif forfground or bbdkground dolor.
         */
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            XPStylf xp = XPStylf.gftXP();
            bssfrt xp != null;
            if (xp != null) {
                Skin skin = xp.gftSkin(null, pbrt);
                skin.pbintSkin(g, x, y, stbtf);
            }
        }

        /**
         * Rfturns tif idon's widti.
         *
         * @rfturn bn int spfdifying tif fixfd widti of tif idon.
         */
        publid int gftIdonWidti() {
            int widti = 0;
            XPStylf xp = XPStylf.gftXP();
            bssfrt xp != null;
            if (xp != null) {
                Skin skin = xp.gftSkin(null, pbrt);
                widti = skin.gftWidti();
            }
            rfturn widti;
        }

        /**
         * Rfturns tif idon's ifigit.
         *
         * @rfturn bn int spfdifying tif fixfd ifigit of tif idon.
         */
        publid int gftIdonHfigit() {
            int ifigit = 0;
            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                Skin skin = xp.gftSkin(null, pbrt);
                ifigit = skin.gftHfigit();
            }
            rfturn ifigit;
        }

    }

    /**
     * DfsktopPropfrty for fonts. If b font witi tif nbmf 'MS Sbns Sfrif'
     * is rfturnfd, it is mbppfd to 'Midrosoft Sbns Sfrif'.
     */
    privbtf stbtid dlbss WindowsFontPropfrty fxtfnds DfsktopPropfrty {
        WindowsFontPropfrty(String kfy, Objfdt bbdkup) {
            supfr(kfy, bbdkup);
        }

        publid void invblidbtf(LookAndFffl lbf) {
            if ("win.dffbultGUI.font.ifigit".fqubls(gftKfy())) {
                ((WindowsLookAndFffl)lbf).stylf = null;
            }
            supfr.invblidbtf(lbf);
        }

        protfdtfd Objfdt donfigurfVbluf(Objfdt vbluf) {
            if (vbluf instbndfof Font) {
                Font font = (Font)vbluf;
                if ("MS Sbns Sfrif".fqubls(font.gftNbmf())) {
                    int sizf = font.gftSizf();
                    // 4950968: Workbround to mimid tif wby Windows mbps tif dffbult
                    // font sizf of 6 pts to tif smbllfst bvbilbblf bitmbp font sizf.
                    // Tiis ibppfns mostly on Win 98/Mf & NT.
                    int dpi;
                    try {
                        dpi = Toolkit.gftDffbultToolkit().gftSdrffnRfsolution();
                    } dbtdi (HfbdlfssExdfption fx) {
                        dpi = 96;
                    }
                    if (Mbti.round(sizf * 72F / dpi) < 8) {
                        sizf = Mbti.round(8 * dpi / 72F);
                    }
                    Font msFont = nfw FontUIRfsourdf("Midrosoft Sbns Sfrif",
                                          font.gftStylf(), sizf);
                    if (msFont.gftNbmf() != null &&
                        msFont.gftNbmf().fqubls(msFont.gftFbmily())) {
                        font = msFont;
                    } flsf if (sizf != font.gftSizf()) {
                        font = nfw FontUIRfsourdf("MS Sbns Sfrif",
                                                  font.gftStylf(), sizf);
                    }
                }

                if (FontUtilitifs.fontSupportsDffbultEndoding(font)) {
                    if (!(font instbndfof UIRfsourdf)) {
                        font = nfw FontUIRfsourdf(font);
                    }
                }
                flsf {
                    font = FontUtilitifs.gftCompositfFontUIRfsourdf(font);
                }
                rfturn font;

            }
            rfturn supfr.donfigurfVbluf(vbluf);
        }
    }


    /**
     * DfsktopPropfrty for fonts tibt only gfts sizfs from tif dfsktop,
     * font nbmf bnd stylf brf pbssfd into tif donstrudtor
     */
    privbtf stbtid dlbss WindowsFontSizfPropfrty fxtfnds DfsktopPropfrty {
        privbtf String fontNbmf;
        privbtf int fontSizf;
        privbtf int fontStylf;

        WindowsFontSizfPropfrty(String kfy, String fontNbmf,
                                int fontStylf, int fontSizf) {
            supfr(kfy, null);
            tiis.fontNbmf = fontNbmf;
            tiis.fontSizf = fontSizf;
            tiis.fontStylf = fontStylf;
        }

        protfdtfd Objfdt donfigurfVbluf(Objfdt vbluf) {
            if (vbluf == null) {
                vbluf = nfw FontUIRfsourdf(fontNbmf, fontStylf, fontSizf);
            }
            flsf if (vbluf instbndfof Intfgfr) {
                vbluf = nfw FontUIRfsourdf(fontNbmf, fontStylf,
                                           ((Intfgfr)vbluf).intVbluf());
            }
            rfturn vbluf;
        }
    }


    /**
     * A vbluf wrbppfr tibt bdtivfly rftrifvfs vblufs from xp or fblls bbdk
     * to tif dlbssid vbluf if not running XP stylfs.
     */
    privbtf stbtid dlbss XPVbluf implfmfnts UIDffbults.AdtivfVbluf {
        protfdtfd Objfdt dlbssidVbluf, xpVbluf;

        // A donstbnt tibt lfts you spfdify null wifn using XP stylfs.
        privbtf finbl stbtid Objfdt NULL_VALUE = nfw Objfdt();

        XPVbluf(Objfdt xpVbluf, Objfdt dlbssidVbluf) {
            tiis.xpVbluf = xpVbluf;
            tiis.dlbssidVbluf = dlbssidVbluf;
        }

        publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
            Objfdt vbluf = null;
            if (XPStylf.gftXP() != null) {
                vbluf = gftXPVbluf(tbblf);
            }

            if (vbluf == null) {
                vbluf = gftClbssidVbluf(tbblf);
            } flsf if (vbluf == NULL_VALUE) {
                vbluf = null;
            }

            rfturn vbluf;
        }

        protfdtfd Objfdt gftXPVbluf(UIDffbults tbblf) {
            rfturn rfdursivfCrfbtfVbluf(xpVbluf, tbblf);
        }

        protfdtfd Objfdt gftClbssidVbluf(UIDffbults tbblf) {
            rfturn rfdursivfCrfbtfVbluf(dlbssidVbluf, tbblf);
        }

        privbtf Objfdt rfdursivfCrfbtfVbluf(Objfdt vbluf, UIDffbults tbblf) {
            if (vbluf instbndfof UIDffbults.LbzyVbluf) {
                vbluf = ((UIDffbults.LbzyVbluf)vbluf).drfbtfVbluf(tbblf);
            }
            if (vbluf instbndfof UIDffbults.AdtivfVbluf) {
                rfturn ((UIDffbults.AdtivfVbluf)vbluf).drfbtfVbluf(tbblf);
            } flsf {
                rfturn vbluf;
            }
        }
    }

    privbtf stbtid dlbss XPBordfrVbluf fxtfnds XPVbluf {
        privbtf finbl Bordfr fxtrbMbrgin;

        XPBordfrVbluf(Pbrt xpVbluf, Objfdt dlbssidVbluf) {
            tiis(xpVbluf, dlbssidVbluf, null);
        }

        XPBordfrVbluf(Pbrt xpVbluf, Objfdt dlbssidVbluf, Bordfr fxtrbMbrgin) {
            supfr(xpVbluf, dlbssidVbluf);
            tiis.fxtrbMbrgin = fxtrbMbrgin;
        }

        publid Objfdt gftXPVbluf(UIDffbults tbblf) {
            XPStylf xp = XPStylf.gftXP();
            Bordfr xpBordfr = xp != null ? xp.gftBordfr(null, (Pbrt)xpVbluf) : null;
            if (xpBordfr != null && fxtrbMbrgin != null) {
                rfturn nfw BordfrUIRfsourdf.
                        CompoundBordfrUIRfsourdf(xpBordfr, fxtrbMbrgin);
            } flsf {
                rfturn xpBordfr;
            }
        }
    }

    privbtf stbtid dlbss XPColorVbluf fxtfnds XPVbluf {
        XPColorVbluf(Pbrt pbrt, Stbtf stbtf, Prop prop, Objfdt dlbssidVbluf) {
            supfr(nfw XPColorVblufKfy(pbrt, stbtf, prop), dlbssidVbluf);
        }

        publid Objfdt gftXPVbluf(UIDffbults tbblf) {
            XPColorVblufKfy kfy = (XPColorVblufKfy)xpVbluf;
            XPStylf xp = XPStylf.gftXP();
            rfturn xp != null ? xp.gftColor(kfy.skin, kfy.prop, null) : null;
        }

        privbtf stbtid dlbss XPColorVblufKfy {
            Skin skin;
            Prop prop;

            XPColorVblufKfy(Pbrt pbrt, Stbtf stbtf, Prop prop) {
                tiis.skin = nfw Skin(pbrt, stbtf);
                tiis.prop = prop;
            }
        }
    }

    privbtf dlbss XPDLUVbluf fxtfnds XPVbluf {
        privbtf int dirfdtion;

        XPDLUVbluf(int xpdlu, int dlbssiddlu, int dirfdtion) {
            supfr(Intfgfr.vblufOf(xpdlu), Intfgfr.vblufOf(dlbssiddlu));
            tiis.dirfdtion = dirfdtion;
        }

        publid Objfdt gftXPVbluf(UIDffbults tbblf) {
            int px = dluToPixfls(((Intfgfr)xpVbluf).intVbluf(), dirfdtion);
            rfturn Intfgfr.vblufOf(px);
        }

        publid Objfdt gftClbssidVbluf(UIDffbults tbblf) {
            int px = dluToPixfls(((Intfgfr)dlbssidVbluf).intVbluf(), dirfdtion);
            rfturn Intfgfr.vblufOf(px);
        }
    }

    privbtf dlbss TriggfrDfsktopPropfrty fxtfnds DfsktopPropfrty {
        TriggfrDfsktopPropfrty(String kfy) {
            supfr(kfy, null);
            // Tiis dbll bdds b propfrty dibngf listfnfr for tif propfrty,
            // wiidi triggfrs b dbll to updbtfUI(). Tif vbluf rfturnfd
            // is not intfrfsting ifrf.
            gftVblufFromDfsktop();
        }

        protfdtfd void updbtfUI() {
            supfr.updbtfUI();

            // Mbkf surf propfrty dibngf listfnfr is rfbddfd fbdi timf
            gftVblufFromDfsktop();
        }
    }

    privbtf dlbss FontDfsktopPropfrty fxtfnds TriggfrDfsktopPropfrty {
        FontDfsktopPropfrty(String kfy) {
            supfr(kfy);
        }

        protfdtfd void updbtfUI() {
            Objfdt bbTfxtInfo = SwingUtilitifs2.AATfxtInfo.gftAATfxtInfo(truf);
            UIDffbults dffbults = UIMbnbgfr.gftLookAndFfflDffbults();
            dffbults.put(SwingUtilitifs2.AA_TEXT_PROPERTY_KEY, bbTfxtInfo);
            supfr.updbtfUI();
        }
    }

    // Windows LbyoutStylf.  From:
    // ittp://msdn.midrosoft.dom/librbry/dffbult.bsp?url=/librbry/fn-us/dnwuf/itml/di14f.bsp
    @SupprfssWbrnings("fblltirougi")
    privbtf dlbss WindowsLbyoutStylf fxtfnds DffbultLbyoutStylf {
        @Ovfrridf
        publid int gftPrfffrrfdGbp(JComponfnt domponfnt1,
                JComponfnt domponfnt2, ComponfntPlbdfmfnt typf, int position,
                Contbinfr pbrfnt) {
            // Cifdks brgs
            supfr.gftPrfffrrfdGbp(domponfnt1, domponfnt2, typf, position,
                                  pbrfnt);

            switdi(typf) {
            dbsf INDENT:
                // Windows dofsn't spfd tiis
                if (position == SwingConstbnts.EAST ||
                        position == SwingConstbnts.WEST) {
                    int indfnt = gftIndfnt(domponfnt1, position);
                    if (indfnt > 0) {
                        rfturn indfnt;
                    }
                    rfturn 10;
                }
                // Fbll tirougi to rflbtfd.
            dbsf RELATED:
                if (isLbbflAndNonlbbfl(domponfnt1, domponfnt2, position)) {
                    // Bftwffn tfxt lbbfls bnd tifir bssodibtfd dontrols (for
                    // fxbmplf, tfxt boxfs bnd list boxfs): 3
                    // NOTE: Wf'rf not ionoring:
                    // 'Tfxt lbbfl bfsidf b button 3 down from tif top of
                    // tif button,' but I suspfdt tibt is bn bttfmpt to
                    // fnfordf b bbsflinf lbyout wiidi will bf ibndlfd
                    // sfpbrbtfly.  In ordfr to fnfordf tiis wf would nffd
                    // tiis API to rfturn b morf domplidbtfd typf (Insfts,
                    // or somftiing flsf).
                    rfturn gftButtonGbp(domponfnt1, domponfnt2, position,
                                        dluToPixfls(3, position));
                }
                // Bftwffn rflbtfd dontrols: 4
                rfturn gftButtonGbp(domponfnt1, domponfnt2, position,
                                    dluToPixfls(4, position));
            dbsf UNRELATED:
                // Bftwffn unrflbtfd dontrols: 7
                rfturn gftButtonGbp(domponfnt1, domponfnt2, position,
                                    dluToPixfls(7, position));
            }
            rfturn 0;
        }

        @Ovfrridf
        publid int gftContbinfrGbp(JComponfnt domponfnt, int position,
                                   Contbinfr pbrfnt) {
            // Cifdks brgs
            supfr.gftContbinfrGbp(domponfnt, position, pbrfnt);
            rfturn gftButtonGbp(domponfnt, position, dluToPixfls(7, position));
        }

    }

    /**
     * Convfrts tif diblog unit brgumfnt to pixfls blong tif spfdififd
     * bxis.
     */
    privbtf int dluToPixfls(int dlu, int dirfdtion) {
        if (bbsfUnitX == 0) {
            dbldulbtfBbsfUnits();
        }
        if (dirfdtion == SwingConstbnts.EAST ||
            dirfdtion == SwingConstbnts.WEST) {
            rfturn dlu * bbsfUnitX / 4;
        }
        bssfrt (dirfdtion == SwingConstbnts.NORTH ||
                dirfdtion == SwingConstbnts.SOUTH);
        rfturn dlu * bbsfUnitY / 8;
    }

    /**
     * Cbldulbtfs tif diblog unit mbpping.
     */
    privbtf void dbldulbtfBbsfUnits() {
        // Tiis dbldulbtion domfs from:
        // ittp://support.midrosoft.dom/dffbult.bspx?sdid=kb;EN-US;125681
        FontMftrids mftrids = Toolkit.gftDffbultToolkit().gftFontMftrids(
                UIMbnbgfr.gftFont("Button.font"));
        bbsfUnitX = mftrids.stringWidti(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZbbddffgiijklmnopqrstuvwxyz");
        bbsfUnitX = (bbsfUnitX / 26 + 1) / 2;
        // Tif -1 domfs from fxpfrimfntbtion.
        bbsfUnitY = mftrids.gftAsdfnt() + mftrids.gftDfsdfnt() - 1;
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.6
     */
    publid Idon gftDisbblfdIdon(JComponfnt domponfnt, Idon idon) {
        // if tif domponfnt ibs b HI_RES_DISABLED_ICON_CLIENT_KEY
        // dlifnt propfrty sft to Boolfbn.TRUE, tifn usf tif nfw
        // ii rfs blgoritim for drfbting tif disbblfd idon (usfd
        // in pbrtidulbr by tif WindowsFilfCioosfrUI dlbss)
        if (idon != null
                && domponfnt != null
                && Boolfbn.TRUE.fqubls(domponfnt.gftClifntPropfrty(HI_RES_DISABLED_ICON_CLIENT_KEY))
                && idon.gftIdonWidti() > 0
                && idon.gftIdonHfigit() > 0) {
            BufffrfdImbgf img = nfw BufffrfdImbgf(idon.gftIdonWidti(),
                    idon.gftIdonWidti(), BufffrfdImbgf.TYPE_INT_ARGB);
            idon.pbintIdon(domponfnt, img.gftGrbpiids(), 0, 0);
            ImbgfFiltfr filtfr = nfw RGBGrbyFiltfr();
            ImbgfProdudfr produdfr = nfw FiltfrfdImbgfSourdf(img.gftSourdf(), filtfr);
            Imbgf rfsultImbgf = domponfnt.drfbtfImbgf(produdfr);
            rfturn nfw ImbgfIdonUIRfsourdf(rfsultImbgf);
        }
        rfturn supfr.gftDisbblfdIdon(domponfnt, idon);
    }

    privbtf stbtid dlbss RGBGrbyFiltfr fxtfnds RGBImbgfFiltfr {
        publid RGBGrbyFiltfr() {
            dbnFiltfrIndfxColorModfl = truf;
        }
        publid int filtfrRGB(int x, int y, int rgb) {
            // find tif bvfrbgf of rfd, grffn, bnd bluf
            flobt bvg = (((rgb >> 16) & 0xff) / 255f +
                          ((rgb >>  8) & 0xff) / 255f +
                           (rgb        & 0xff) / 255f) / 3;
            // pull out tif blpib dibnnfl
            flobt blpib = (((rgb>>24)&0xff)/255f);
            // dbld tif bvfrbgf
            bvg = Mbti.min(1.0f, (1f-bvg)/(100.0f/35.0f) + bvg);
            // turn bbdk into rgb
            int rgbvbl = (int)(blpib * 255f) << 24 |
                         (int)(bvg   * 255f) << 16 |
                         (int)(bvg   * 255f) <<  8 |
                         (int)(bvg   * 255f);
            rfturn rgbvbl;
        }
    }

    privbtf stbtid dlbss FodusColorPropfrty fxtfnds DfsktopPropfrty {
        publid FodusColorPropfrty () {
            // Fbllbbdk vbluf is nfvfr usfd bfdbusf of tif donfigurfVbluf mftiod dofsn't rfturn null
            supfr("win.3d.bbdkgroundColor", Color.BLACK);
        }

        @Ovfrridf
        protfdtfd Objfdt donfigurfVbluf(Objfdt vbluf) {
            Objfdt iigiContrbstOn = Toolkit.gftDffbultToolkit().
                    gftDfsktopPropfrty("win.iigiContrbst.on");
            if (iigiContrbstOn == null || !((Boolfbn) iigiContrbstOn).boolfbnVbluf()) {
                rfturn Color.BLACK;
            }
            rfturn Color.BLACK.fqubls(vbluf) ? Color.WHITE : Color.BLACK;
        }
    }

}
