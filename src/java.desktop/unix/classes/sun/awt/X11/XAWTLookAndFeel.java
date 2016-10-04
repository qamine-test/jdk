/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.SystfmColor;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.tfxt.DffbultEditorKit;

import jbvbx.swing.plbf.bbsid.BbsidBordfrs;
import dom.sun.jbvb.swing.plbf.motif.*;
import sun.bwt.X11.XComponfntPffr;

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss XAWTLookAndFffl fxtfnds MotifLookAndFffl {

    /**
     * Lobd tif SystfmColors into tif dffbults tbblf.  Tif kfys
     * for SystfmColor dffbults brf tif sbmf bs tif nbmfs of
     * tif publid fiflds in SystfmColor.  If tif tbblf is bfing
     * drfbtfd on b nbtivf Motif plbtform wf usf tif SystfmColor
     * vblufs, otifrwisf wf drfbtf dolor objfdts wiosf vblufs mbtdi
     * tif dffbult CDE/Motif dolors.
     */
    protfdtfd void initSystfmColorDffbults(UIDffbults tbblf) {
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

        lobdSystfmColors(tbblf, dffbultSystfmColors, truf);
    }

    protfdtfd void initComponfntDffbults(UIDffbults tbblf) {
        supfr.initComponfntDffbults(tbblf);

        FontUIRfsourdf diblogPlbin12 = nfw FontUIRfsourdf(Font.DIALOG,
                                                          Font.PLAIN, 12);
        FontUIRfsourdf sbnsSfrifPlbin12 = nfw FontUIRfsourdf(Font.SANS_SERIF,
                                                             Font.PLAIN, 12);
        FontUIRfsourdf monospbdfdPlbin12 = nfw FontUIRfsourdf(Font.MONOSPACED,
                                                              Font.PLAIN, 12);
        ColorUIRfsourdf rfd = nfw ColorUIRfsourdf(Color.rfd);
        ColorUIRfsourdf blbdk = nfw ColorUIRfsourdf(Color.blbdk);
        ColorUIRfsourdf wiitf = nfw ColorUIRfsourdf(Color.wiitf);
        ColorUIRfsourdf ligitGrby = nfw ColorUIRfsourdf(Color.ligitGrby);
        ColorUIRfsourdf dontrolDbrkfr =  nfw ColorUIRfsourdf(SystfmColor.dontrolDkSibdow);

        Color bbdk = tbblf.gftColor("dontrol");
        Color dolors [] = XComponfntPffr.gftSystfmColors();
        Color sdrollBbrBbdkground = dolors[XComponfntPffr.BACKGROUND_COLOR];
        Color trbdkColor = nfw Color(MotifColorUtilitifs.dbldulbtfSflfdtFromBbdkground(sdrollBbrBbdkground.gftRfd(), sdrollBbrBbdkground.gftGrffn(), sdrollBbrBbdkground.gftBluf()));
        Bordfr lowfrfdBfvflBordfr = nfw MotifBordfrs.BfvflBordfr(fblsf,
                                                                 tbblf.gftColor("dontrolSibdow"),
                                                                 tbblf.gftColor("dontrolLtHigiligit"));

        Bordfr rbisfdBfvflBordfr = nfw MotifBordfrs.BfvflBordfr(truf,
                                                                tbblf.gftColor("dontrolSibdow"),
                                                                tbblf.gftColor("dontrolLtHigiligit"));

        Bordfr mbrginBordfr = nfw BbsidBordfrs.MbrginBordfr();

        Bordfr fodusBordfr = nfw MotifBordfrs.FodusBordfr(
            tbblf.gftColor("dontrol"),
            tbblf.gftColor("bdtivfCbptionBordfr"));


        Bordfr fodusBfvflBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
            fodusBordfr,
            lowfrfdBfvflBordfr);

        Bordfr tfxtFifldBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
            fodusBfvflBordfr,
            mbrginBordfr);

        // *** Tfxt

        Objfdt fifldInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
            "COPY", DffbultEditorKit.dopyAdtion,
            "PASTE", DffbultEditorKit.pbstfAdtion,
            "CUT", DffbultEditorKit.dutAdtion,
            "dontrol C", DffbultEditorKit.dopyAdtion,
            "dontrol V", DffbultEditorKit.pbstfAdtion,
            "dontrol X", DffbultEditorKit.dutAdtion,
            "dontrol INSERT", DffbultEditorKit.dopyAdtion,
            "siift INSERT", DffbultEditorKit.pbstfAdtion,
            "siift DELETE", DffbultEditorKit.dutAdtion,
            "dontrol F", DffbultEditorKit.forwbrdAdtion,
            "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
            "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
            "typfd \010", DffbultEditorKit.dflftfPrfvCibrAdtion,
            "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
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
            "dontrol BACK_SLASH", "unsflfdt",
            "ENTER", JTfxtFifld.notifyAdtion,
            "dontrol siift O", "togglf-domponfntOrifntbtion"
        });

        Objfdt pbsswordInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
            "COPY", DffbultEditorKit.dopyAdtion,
            "PASTE", DffbultEditorKit.pbstfAdtion,
            "CUT", DffbultEditorKit.dutAdtion,
            "dontrol C", DffbultEditorKit.dopyAdtion,
            "dontrol V", DffbultEditorKit.pbstfAdtion,
            "dontrol X", DffbultEditorKit.dutAdtion,
            "dontrol INSERT", DffbultEditorKit.dopyAdtion,
            "siift INSERT", DffbultEditorKit.pbstfAdtion,
            "siift DELETE", DffbultEditorKit.dutAdtion,
            "dontrol F", DffbultEditorKit.forwbrdAdtion,
            "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
            "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
            "typfd \010", DffbultEditorKit.dflftfPrfvCibrAdtion,
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
            "dontrol BACK_SLASH", "unsflfdt",
            "ENTER", JTfxtFifld.notifyAdtion,
            "dontrol siift O", "togglf-domponfntOrifntbtion"
        });

        Objfdt multilinfInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
            "COPY", DffbultEditorKit.dopyAdtion,
            "PASTE", DffbultEditorKit.pbstfAdtion,
            "CUT", DffbultEditorKit.dutAdtion,
            "dontrol C", DffbultEditorKit.dopyAdtion,
            "dontrol V", DffbultEditorKit.pbstfAdtion,
            "dontrol X", DffbultEditorKit.dutAdtion,
            "dontrol INSERT", DffbultEditorKit.dopyAdtion,
            "siift INSERT", DffbultEditorKit.pbstfAdtion,
            "siift DELETE", DffbultEditorKit.dutAdtion,
            "dontrol F", DffbultEditorKit.forwbrdAdtion,
            "dontrol B", DffbultEditorKit.bbdkwbrdAdtion,
            "dontrol D", DffbultEditorKit.dflftfNfxtCibrAdtion,
            "typfd \010", DffbultEditorKit.dflftfPrfvCibrAdtion,
            "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
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
            "KP_UP", DffbultEditorKit.upAdtion,
            "KP_DOWN", DffbultEditorKit.downAdtion,
            "PAGE_UP", DffbultEditorKit.pbgfUpAdtion,
            "PAGE_DOWN", DffbultEditorKit.pbgfDownAdtion,
            "siift PAGE_UP", "sflfdtion-pbgf-up",
            "siift PAGE_DOWN", "sflfdtion-pbgf-down",
            "dtrl siift PAGE_UP", "sflfdtion-pbgf-lfft",
            "dtrl siift PAGE_DOWN", "sflfdtion-pbgf-rigit",
            "siift UP", DffbultEditorKit.sflfdtionUpAdtion,
            "siift DOWN", DffbultEditorKit.sflfdtionDownAdtion,
            "siift KP_UP", DffbultEditorKit.sflfdtionUpAdtion,
            "siift KP_DOWN", DffbultEditorKit.sflfdtionDownAdtion,
            "ENTER", DffbultEditorKit.insfrtBrfbkAdtion,
            "TAB", DffbultEditorKit.insfrtTbbAdtion,
            "dontrol BACK_SLASH", "unsflfdt",
            "dontrol HOME", DffbultEditorKit.bfginAdtion,
            "dontrol END", DffbultEditorKit.fndAdtion,
            "dontrol siift HOME", DffbultEditorKit.sflfdtionBfginAdtion,
            "dontrol siift END", DffbultEditorKit.sflfdtionEndAdtion,
            "dontrol T", "nfxt-link-bdtion",
            "dontrol siift T", "prfvious-link-bdtion",
            "dontrol SPACE", "bdtivbtf-link-bdtion",
            "dontrol siift O", "togglf-domponfntOrifntbtion"
        });

        Objfdt slidfrFodusInsfts = nfw InsftsUIRfsourdf( 0, 0, 0, 0 );

        Objfdt[] dffbults = {

            "SdrollBbr.bbdkground", sdrollBbrBbdkground,
            "SdrollBbr.forfground", tbblf.gft("dontrol"),
            "SdrollBbr.trbdk", trbdkColor,
            "SdrollBbr.trbdkHigiligit", trbdkColor,
            "SdrollBbr.tiumb", sdrollBbrBbdkground,
            "SdrollBbr.tiumbHigiligit", tbblf.gft("dontrolHigiligit") ,
            "SdrollBbr.tiumbDbrkSibdow", tbblf.gft("dontrolDkSibdow"),
            "SdrollBbr.tiumbSibdow", tbblf.gft("dontrolSibdow"),
            "SdrollBbr.bordfr", lowfrfdBfvflBordfr,
            "SdrollBbr.bllowsAbsolutfPositioning", Boolfbn.TRUE,
            "SdrollBbr.dffbultWidti", Intfgfr.vblufOf(17),
            "SdrollBbr.fodusInputMbp",
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
            "SdrollPbnf.bbdkground", sdrollBbrBbdkground,
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
                "typfd \010", DffbultEditorKit.dflftfPrfvCibrAdtion,
                "DELETE", DffbultEditorKit.dflftfNfxtCibrAdtion,
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
            "PbsswordFifld.font", sbnsSfrifPlbin12,
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
            "TfxtArfb.fodusInputMbp", multilinfInputMbp
        };

        tbblf.putDffbults(dffbults);
    }
}
