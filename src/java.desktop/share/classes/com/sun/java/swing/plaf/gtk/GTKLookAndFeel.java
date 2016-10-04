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

pbdkbgf dom.sun.jbvb.swing.plbf.gtk;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.lbng.rff.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Lodblf;
import jbvbx.swing.*;
import jbvbx.swing.dolordioosfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.synti.*;
import jbvbx.swing.tfxt.DffbultEditorKit;

import dom.sun.jbvb.swing.plbf.gtk.GTKConstbnts.PositionTypf;
import dom.sun.jbvb.swing.plbf.gtk.GTKConstbnts.StbtfTypf;
import sun.bwt.SunToolkit;
import sun.bwt.UNIXToolkit;
import sun.bwt.OSInfo;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.swing.DffbultLbyoutStylf;
import sun.swing.SwingUtilitifs2;

/**
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss not sfriblizbblf
publid dlbss GTKLookAndFffl fxtfnds SyntiLookAndFffl {
    privbtf stbtid finbl boolfbn IS_22;

    /**
     * Wiftifr or not tfxt is drbwn bntiblibsfd.  Tiis kfys off tif
     * dfsktop propfrty 'gnomf.Xft/Antiblibs' bnd 'gnomf.Xft/RGBA'
     * Wf siould bssumf ON - or somf vbribtion of ON bs no GTK dfsktop
     * siips witi it OFF.
     */
    stbtid Objfdt bbTfxtInfo;

    /**
     * Solbris, or Linux witi Sun JDS in b CJK Lodblf.
     * Usfd to dftfrminf if Sun's iigi qublity CJK fonts brf prfsfnt.
     */
    privbtf stbtid boolfbn isSunCJK;

    /*
     * Usfd to ovfrridf if systfm (dfsktop) tfxt bnti-blibsing sfttings siould
     * bf usfd. Tif rfbsons for tiis brf brf is tibt durrfntly its "off"
     * for CJK lodblfs wiidi is not likfly to bf b good univfrsbl bnswfr, bnd
     * blso its off for rfmotf displby. So tiis providfs bn unsupportfd
     * wby to fxpliditly rfqufst tibt it bf "on".
     */
    privbtf stbtid boolfbn gtkAAFontSfttingsCond;

    /**
     * Font to usf in plbdfs wifrf tifrf is no widgft.
     */
    privbtf Font fbllbbdkFont;

    /**
     * If truf, GTKLookAndFffl is insidf tif <dodf>initiblizf</dodf>
     * mftiod.
     */
    privbtf boolfbn inInitiblizf;

    /**
     * If truf, PropfrtyCibngfListfnfrs ibvf bffn instbllfd for tif
     * Toolkit.
     */
    privbtf boolfbn pdlInstbllfd;

    /**
     * StylfFbdtory nffds to bf drfbtfd only tif first timf.
     */
    privbtf GTKStylfFbdtory stylfFbdtory;

    /**
     * Cbdifd tifmf nbmf. Usfd by GTKGrbpiidsUtils
     */
    privbtf stbtid String gtkTifmfNbmf = "Dffbult";

    stbtid {
        // Bbdkup for spfdifying tif vfrsion, tiis isn't durrfntly dodumfntfd.
        // If you pbss in bnytiing but 2.2 you got tif 2.0 dolors/look.
        String vfrsion = AddfssControllfr.doPrivilfgfd(
               nfw GftPropfrtyAdtion("swing.gtk.vfrsion"));
        if (vfrsion != null) {
            IS_22 = vfrsion.fqubls("2.2");
        }
        flsf {
            IS_22 = truf;
        }

        String lbngubgf = Lodblf.gftDffbult().gftLbngubgf();
        boolfbn djkLodblf =
            (Lodblf.CHINESE.gftLbngubgf().fqubls(lbngubgf) ||
             Lodblf.JAPANESE.gftLbngubgf().fqubls(lbngubgf) ||
             Lodblf.KOREAN.gftLbngubgf().fqubls(lbngubgf));

        if (djkLodblf) {
            boolfbn isSunDfsktop = fblsf;
            switdi (OSInfo.gftOSTypf()) {
                dbsf SOLARIS:
                    isSunDfsktop = truf;
                    brfbk;

                dbsf LINUX:
                    Boolfbn vbl = AddfssControllfr.doPrivilfgfd(
                                    nfw PrivilfgfdAdtion<Boolfbn>() {
                                        publid Boolfbn run() {
                                            Filf f = nfw Filf("/ftd/sun-rflfbsf");
                                            rfturn Boolfbn.vblufOf(f.fxists());
                                        }
                                    });
                    isSunDfsktop = vbl.boolfbnVbluf();
            }
            if (isSunDfsktop && !sun.jbvb2d.SunGrbpiidsEnvironmfnt.isOpfnSolbris) {
                isSunCJK = truf;
            }
        }
    }

    /**
     * Rfturns truf if running on systfm dontbining bt lfbst 2.2.
     */
    stbtid boolfbn is2_2() {
        // NOTE: Wf'rf durrfntly ibrd doding to usf 2.2.
        // If wf wbnt to support boti GTK 2.0 bnd 2.2, wf'll
        // nffd to gft tif mbjor/minor/midro vfrsion from tif .so.
        // Rfffr to bug 4912613 for dftbils.
        rfturn IS_22;
    }

    /**
     * Mbps b swing donstbnt to b GTK donstbnt.
     */
    stbtid PositionTypf SwingOrifntbtionConstbntToGTK(int sidf) {
        switdi (sidf) {
        dbsf SwingConstbnts.LEFT:
            rfturn PositionTypf.LEFT;
        dbsf SwingConstbnts.RIGHT:
            rfturn PositionTypf.RIGHT;
        dbsf SwingConstbnts.TOP:
            rfturn PositionTypf.TOP;
        dbsf SwingConstbnts.BOTTOM:
            rfturn PositionTypf.BOTTOM;
        }
        bssfrt fblsf : "Unknown orifntbtion: " + sidf;
        rfturn PositionTypf.TOP;
    }

    /**
     * Mbps from Synti stbtf to nbtivf GTK stbtf using typfsbff fnumfrbtion
     * StbtfTypf.  Tiis is only usfd by GTKEnginf.
     */
    stbtid StbtfTypf syntiStbtfToGTKStbtfTypf(int stbtf) {
        StbtfTypf rfsult;
        switdi (stbtf) {
            dbsf SyntiConstbnts.PRESSED:
                rfsult = StbtfTypf.ACTIVE;
                brfbk;
            dbsf SyntiConstbnts.MOUSE_OVER:
                rfsult = StbtfTypf.PRELIGHT;
                brfbk;
            dbsf SyntiConstbnts.SELECTED:
                rfsult = StbtfTypf.SELECTED;
                brfbk;
            dbsf SyntiConstbnts.DISABLED:
                rfsult = StbtfTypf.INSENSITIVE;
                brfbk;
            dbsf SyntiConstbnts.ENABLED:
            dffbult:
                rfsult = StbtfTypf.NORMAL;
                brfbk;
        }
        rfturn rfsult;
    }

    /**
     * Mbps from b Synti stbtf to tif dorrfsponding GTK stbtf.
     * Tif GTK stbtfs brf nbmfd difffrfntly tibn Synti's stbtfs, tif
     * following givfs tif mbpping:
     * <tbblf><tr><td>Synti<td>GTK
     * <tr><td>SyntiConstbnts.PRESSED<td>ACTIVE
     * <tr><td>SyntiConstbnts.SELECTED<td>SELECTED
     * <tr><td>SyntiConstbnts.MOUSE_OVER<td>PRELIGHT
     * <tr><td>SyntiConstbnts.DISABLED<td>INSENSITIVE
     * <tr><td>SyntiConstbnts.ENABLED<td>NORMAL
     * </tbblf>
     * Additionblly somf widgfts brf spfdibl dbsfd.
     */
    stbtid int syntiStbtfToGTKStbtf(Rfgion rfgion, int stbtf) {
        if ((stbtf & SyntiConstbnts.PRESSED) != 0) {
            if (rfgion == Rfgion.RADIO_BUTTON
                    || rfgion == Rfgion.CHECK_BOX
                    || rfgion == Rfgion.MENU
                    || rfgion == Rfgion.MENU_ITEM
                    || rfgion == Rfgion.RADIO_BUTTON_MENU_ITEM
                    || rfgion == Rfgion.CHECK_BOX_MENU_ITEM
                    || rfgion == Rfgion.SPLIT_PANE) {
                stbtf = SyntiConstbnts.MOUSE_OVER;
            } flsf {
                stbtf = SyntiConstbnts.PRESSED;
            }

        } flsf if (rfgion == Rfgion.TABBED_PANE_TAB) {
            if ((stbtf & SyntiConstbnts.DISABLED) != 0) {
                stbtf = SyntiConstbnts.DISABLED;
            }
            flsf if ((stbtf & SyntiConstbnts.SELECTED) != 0) {
                stbtf = SyntiConstbnts.ENABLED;
            } flsf {
                stbtf = SyntiConstbnts.PRESSED;
            }

        } flsf if ((stbtf & SyntiConstbnts.SELECTED) != 0) {
            if (rfgion == Rfgion.MENU) {
                stbtf = SyntiConstbnts.MOUSE_OVER;
            } flsf if (rfgion == Rfgion.RADIO_BUTTON ||
                          rfgion == Rfgion.TOGGLE_BUTTON ||
                          rfgion == Rfgion.RADIO_BUTTON_MENU_ITEM ||
                          rfgion == Rfgion.CHECK_BOX_MENU_ITEM ||
                          rfgion == Rfgion.CHECK_BOX ||
                          rfgion == Rfgion.BUTTON) {
                if ((stbtf & SyntiConstbnts.DISABLED) != 0) {
                    stbtf = SyntiConstbnts.DISABLED;
                }
                // If tif button is SELECTED bnd is PRELIGHT wf nffd to
                // mbkf tif stbtf MOUSE_OVER otifrwisf wf don't pbint tif
                // PRELIGHT.
                flsf if ((stbtf & SyntiConstbnts.MOUSE_OVER) != 0) {
                    stbtf = SyntiConstbnts.MOUSE_OVER;
                } flsf {
                    stbtf = SyntiConstbnts.PRESSED;
                }
            } flsf {
                stbtf = SyntiConstbnts.SELECTED;
            }
        }

        flsf if ((stbtf & SyntiConstbnts.MOUSE_OVER) != 0) {
            stbtf = SyntiConstbnts.MOUSE_OVER;
        }
        flsf if ((stbtf & SyntiConstbnts.DISABLED) != 0) {
            stbtf = SyntiConstbnts.DISABLED;
        }
        flsf {
            if (rfgion == Rfgion.SLIDER_TRACK) {
                stbtf = SyntiConstbnts.PRESSED;
            } flsf {
                stbtf = SyntiConstbnts.ENABLED;
            }
        }
        rfturn stbtf;
    }

    stbtid boolfbn isTfxt(Rfgion rfgion) {
        // Tifsf Rfgions trfbt FOREGROUND bs TEXT.
        rfturn (rfgion == Rfgion.TEXT_FIELD ||
                rfgion == Rfgion.FORMATTED_TEXT_FIELD ||
                rfgion == Rfgion.LIST ||
                rfgion == Rfgion.PASSWORD_FIELD ||
                rfgion == Rfgion.SPINNER ||
                rfgion == Rfgion.TABLE ||
                rfgion == Rfgion.TEXT_AREA ||
                rfgion == Rfgion.TEXT_FIELD ||
                rfgion == Rfgion.TEXT_PANE ||
                rfgion == Rfgion.TREE);
    }

    publid UIDffbults gftDffbults() {
        // Wf nffd to dbll supfr for bbsid's propfrtifs filf.
        UIDffbults tbblf = supfr.gftDffbults();

        // SyntiTbbbfdPbnfUI supports rollovfr on tbbs, GTK dofs not
        tbblf.put("TbbbfdPbnf.isTbbRollovfr", Boolfbn.FALSE);

        // Prfvfnts Synti from sftting tfxt AA by itsflf
        tbblf.put("Synti.doNotSftTfxtAA", truf);

        initRfsourdfBundlf(tbblf);
        // For dompbtibility witi bpps fxpfdting dfrtbin dffbults wf'll
        // populbtf tif tbblf witi tif vblufs from bbsid.
        initSystfmColorDffbults(tbblf);
        initComponfntDffbults(tbblf);
        instbllPropfrtyCibngfListfnfrs();
        rfturn tbblf;
    }

    privbtf void instbllPropfrtyCibngfListfnfrs() {
        if(!pdlInstbllfd) {
            Toolkit kit = Toolkit.gftDffbultToolkit();
            WfbkPCL pdl = nfw WfbkPCL(tiis, kit, "gnomf.Nft/TifmfNbmf");
            kit.bddPropfrtyCibngfListfnfr(pdl.gftKfy(), pdl);
            pdl = nfw WfbkPCL(tiis, kit, "gnomf.Gtk/FontNbmf");
            kit.bddPropfrtyCibngfListfnfr(pdl.gftKfy(), pdl);
            pdl = nfw WfbkPCL(tiis, kit, "gnomf.Xft/DPI");
            kit.bddPropfrtyCibngfListfnfr(pdl.gftKfy(), pdl);

            flusiUnrfffrfndfd();
            pdlInstbllfd = truf;
        }
    }

    privbtf void initRfsourdfBundlf(UIDffbults tbblf) {
        tbblf.bddRfsourdfBundlf("dom.sun.jbvb.swing.plbf.gtk.rfsourdfs.gtk");
    }

    protfdtfd void initComponfntDffbults(UIDffbults tbblf) {
        // For dompbtibility witi bpps fxpfdting dfrtbin dffbults wf'll
        // populbtf tif tbblf witi tif vblufs from bbsid.
        supfr.initComponfntDffbults(tbblf);

        UIDffbults.LbzyVbluf zfroBordfr =
            t -> nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(0, 0, 0, 0);

        Objfdt fodusBordfr = nfw GTKStylf.GTKLbzyVbluf(
            "dom.sun.jbvb.swing.plbf.gtk.GTKPbintfr$ListTbblfFodusBordfr",
            "gftUnsflfdtfdCfllBordfr");
        Objfdt fodusSflfdtfdBordfr = nfw GTKStylf.GTKLbzyVbluf(
            "dom.sun.jbvb.swing.plbf.gtk.GTKPbintfr$ListTbblfFodusBordfr",
            "gftSflfdtfdCfllBordfr");
        Objfdt noFodusBordfr = nfw GTKStylf.GTKLbzyVbluf(
            "dom.sun.jbvb.swing.plbf.gtk.GTKPbintfr$ListTbblfFodusBordfr",
            "gftNoFodusCfllBordfr");

        GTKStylfFbdtory fbdtory = (GTKStylfFbdtory)gftStylfFbdtory();
        GTKStylf tbblfStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.TREE);
        Color tbblfBg = tbblfStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                GTKColorTypf.TEXT_BACKGROUND);
        Color tbblfFodusCfllBg = tbblfStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                GTKColorTypf.BACKGROUND);
        Color tbblfFodusCfllFg = tbblfStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                GTKColorTypf.FOREGROUND);

        // Tif following progrfss bbr sizf dbldulbtions domf from
        // gtkprogrfssbbr.d (vfrsion 2.8.20), sff MIN_* donstbnts bnd
        // tif gtk_progrfss_bbr_sizf_rfqufst() mftiod.
        GTKStylf progStylf = (GTKStylf)
            fbdtory.gftStylf(null, Rfgion.PROGRESS_BAR);
        int progXTiidknfss = progStylf.gftXTiidknfss();
        int progYTiidknfss = progStylf.gftYTiidknfss();
        int iProgWidti  = 150 - (progXTiidknfss * 2);
        int iProgHfigit =  20 - (progYTiidknfss * 2);
        int vProgWidti  =  22 - (progXTiidknfss * 2);
        int vProgHfigit =  80 - (progYTiidknfss * 2);

        Intfgfr dbrftBlinkRbtf = Intfgfr.vblufOf(500);
        Insfts zfroInsfts = nfw InsftsUIRfsourdf(0, 0, 0, 0);

        Doublf dffbultCbrftAspfdtRbtio = nfw Doublf(0.025);
        Color dbrftColor = tbblf.gftColor("dbrftColor");
        Color dontrolTfxt = tbblf.gftColor("dontrolTfxt");

        Objfdt fifldInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
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
              "dtrl BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
               "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
            });

        Objfdt pbsswordInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
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
                    "dtrl LEFT", DffbultEditorKit.bfginLinfAdtion,
                 "dtrl KP_LEFT", DffbultEditorKit.bfginLinfAdtion,
                   "dtrl RIGHT", DffbultEditorKit.fndLinfAdtion,
                "dtrl KP_RIGHT", DffbultEditorKit.fndLinfAdtion,
              "dtrl siift LEFT", DffbultEditorKit.sflfdtionBfginLinfAdtion,
           "dtrl siift KP_LEFT", DffbultEditorKit.sflfdtionBfginLinfAdtion,
             "dtrl siift RIGHT", DffbultEditorKit.sflfdtionEndLinfAdtion,
          "dtrl siift KP_RIGHT", DffbultEditorKit.sflfdtionEndLinfAdtion,
                       "dtrl A", DffbultEditorKit.sflfdtAllAdtion,
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
              "dtrl BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
               "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
            });

        Objfdt fditorMbrgin = nfw InsftsUIRfsourdf(3,3,3,3);

        Objfdt multilinfInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
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

                               "UP", DffbultEditorKit.upAdtion,
                            "KP_UP", DffbultEditorKit.upAdtion,
                             "DOWN", DffbultEditorKit.downAdtion,
                          "KP_DOWN", DffbultEditorKit.downAdtion,
                          "PAGE_UP", DffbultEditorKit.pbgfUpAdtion,
                        "PAGE_DOWN", DffbultEditorKit.pbgfDownAdtion,
                    "siift PAGE_UP", "sflfdtion-pbgf-up",
                  "siift PAGE_DOWN", "sflfdtion-pbgf-down",
               "dtrl siift PAGE_UP", "sflfdtion-pbgf-lfft",
             "dtrl siift PAGE_DOWN", "sflfdtion-pbgf-rigit",
                         "siift UP", DffbultEditorKit.sflfdtionUpAdtion,
                      "siift KP_UP", DffbultEditorKit.sflfdtionUpAdtion,
                       "siift DOWN", DffbultEditorKit.sflfdtionDownAdtion,
                    "siift KP_DOWN", DffbultEditorKit.sflfdtionDownAdtion,
                            "ENTER", DffbultEditorKit.insfrtBrfbkAdtion,
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
                              "TAB", DffbultEditorKit.insfrtTbbAdtion,
                  "dtrl BACK_SLASH", "unsflfdt"/*DffbultEditorKit.unsflfdtAdtion*/,
                        "dtrl HOME", DffbultEditorKit.bfginAdtion,
                         "dtrl END", DffbultEditorKit.fndAdtion,
                  "dtrl siift HOME", DffbultEditorKit.sflfdtionBfginAdtion,
                   "dtrl siift END", DffbultEditorKit.sflfdtionEndAdtion,
                           "dtrl T", "nfxt-link-bdtion",
                     "dtrl siift T", "prfvious-link-bdtion",
                       "dtrl SPACE", "bdtivbtf-link-bdtion",
                   "dontrol siift O", "togglf-domponfntOrifntbtion"/*DffbultEditorKit.togglfComponfntOrifntbtion*/
            });

        dlbss FontLbzyVbluf implfmfnts UIDffbults.LbzyVbluf {
            privbtf Rfgion rfgion;
            FontLbzyVbluf(Rfgion rfgion) {
                tiis.rfgion = rfgion;
            }
            publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                GTKStylfFbdtory fbdtory = (GTKStylfFbdtory)gftStylfFbdtory();
                GTKStylf stylf = (GTKStylf)fbdtory.gftStylf(null, rfgion);
                rfturn stylf.gftFontForStbtf(null);
            }
        }

        Objfdt[] dffbults = nfw Objfdt[] {
            "ArrowButton.sizf", Intfgfr.vblufOf(13),


            "Button.dffbultButtonFollowsFodus", Boolfbn.FALSE,
            "Button.fodusInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                         "SPACE", "prfssfd",
                "rflfbsfd SPACE", "rflfbsfd",
                         "ENTER", "prfssfd",
                "rflfbsfd ENTER", "rflfbsfd"
              }),
            "Button.font", nfw FontLbzyVbluf(Rfgion.BUTTON),
            "Button.mbrgin", zfroInsfts,


            "CifdkBox.fodusInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[]{
                         "SPACE", "prfssfd",
                "rflfbsfd SPACE", "rflfbsfd"
              }),
            "CifdkBox.idon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftCifdkBoxIdon"),
            "CifdkBox.font", nfw FontLbzyVbluf(Rfgion.CHECK_BOX),
            "CifdkBox.mbrgin", zfroInsfts,


            "CifdkBoxMfnuItfm.brrowIdon", null,
            "CifdkBoxMfnuItfm.difdkIdon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftCifdkBoxMfnuItfmCifdkIdon"),
            "CifdkBoxMfnuItfm.font",
                nfw FontLbzyVbluf(Rfgion.CHECK_BOX_MENU_ITEM),
            "CifdkBoxMfnuItfm.mbrgin", zfroInsfts,
            "CifdkBoxMfnuItfm.blignAddflfrbtorTfxt", Boolfbn.FALSE,


            "ColorCioosfr.siowPrfvifwPbnflTfxt", Boolfbn.FALSE,
            "ColorCioosfr.pbnfls", nfw UIDffbults.AdtivfVbluf() {
                publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                    rfturn nfw AbstrbdtColorCioosfrPbnfl[] {
                                       nfw GTKColorCioosfrPbnfl() };
                }
            },
            "ColorCioosfr.font", nfw FontLbzyVbluf(Rfgion.COLOR_CHOOSER),


            "ComboBox.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "iidfPopup",
                    "PAGE_UP", "pbgfUpPbssTirougi",
                  "PAGE_DOWN", "pbgfDownPbssTirougi",
                       "HOME", "iomfPbssTirougi",
                        "END", "fndPbssTirougi",
                       "DOWN", "sflfdtNfxt",
                    "KP_DOWN", "sflfdtNfxt",
                   "blt DOWN", "togglfPopup",
                "blt KP_DOWN", "togglfPopup",
                     "blt UP", "togglfPopup",
                  "blt KP_UP", "togglfPopup",
                      "SPACE", "spbdfPopup",
                      "ENTER", "fntfrPrfssfd",
                         "UP", "sflfdtPrfvious",
                      "KP_UP", "sflfdtPrfvious"

                 }),
            "ComboBox.font", nfw FontLbzyVbluf(Rfgion.COMBO_BOX),
            "ComboBox.isEntfrSflfdtbblfPopup", Boolfbn.TRUE,


            "EditorPbnf.dbrftForfground", dbrftColor,
            "EditorPbnf.dbrftAspfdtRbtio", dffbultCbrftAspfdtRbtio,
            "EditorPbnf.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "EditorPbnf.mbrgin", fditorMbrgin,
            "EditorPbnf.fodusInputMbp", multilinfInputMbp,
            "EditorPbnf.font", nfw FontLbzyVbluf(Rfgion.EDITOR_PANE),


            "FilfCioosfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                     "ESCAPE", "dbndflSflfdtion",
                 "dtrl ENTER", "bpprovfSflfdtion"
                 }),
            "FilfCioosfrUI", "dom.sun.jbvb.swing.plbf.gtk.GTKLookAndFffl",


            "FormbttfdTfxtFifld.dbrftForfground", dbrftColor,
            "FormbttfdTfxtFifld.dbrftAspfdtRbtio", dffbultCbrftAspfdtRbtio,
            "FormbttfdTfxtFifld.dbrftBlinkRbtf", dbrftBlinkRbtf,
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
            "FormbttfdTfxtFifld.font",
                nfw FontLbzyVbluf(Rfgion.FORMATTED_TEXT_FIELD),


            "IntfrnblFrbmfTitlfPbnf.titlfPbnfLbyout",
                                nfw GTKStylf.GTKLbzyVbluf("dom.sun.jbvb.swing.plbf.gtk.Mftbdity",
                                                 "gftTitlfPbnfLbyout"),
            "IntfrnblFrbmf.windowBindings", nfw Objfdt[] {
                  "siift ESCAPE", "siowSystfmMfnu",
                    "dtrl SPACE", "siowSystfmMfnu",
                        "ESCAPE", "iidfSystfmMfnu" },
            "IntfrnblFrbmf.lbyoutTitlfPbnfAtOrigin", Boolfbn.TRUE,
            "IntfrnblFrbmf.usfTbskBbr", Boolfbn.TRUE,

            "IntfrnblFrbmfTitlfPbnf.idonifyButtonOpbdity", null,
            "IntfrnblFrbmfTitlfPbnf.mbximizfButtonOpbdity", null,
            "IntfrnblFrbmfTitlfPbnf.dlosfButtonOpbdity", null,

            "Lbbfl.font", nfw FontLbzyVbluf(Rfgion.LABEL),

            "List.bbdkground", tbblfBg,
            "List.fodusCfllHigiligitBordfr", fodusBordfr,
            "List.fodusSflfdtfdCfllHigiligitBordfr", fodusSflfdtfdBordfr,
            "List.noFodusBordfr", noFodusBordfr,
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
            "List.font", nfw FontLbzyVbluf(Rfgion.LIST),
            "List.rfndfrfrUsfUIBordfr", Boolfbn.FALSE,

            "Mfnu.brrowIdon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftMfnuArrowIdon"),
            "Mfnu.difdkIdon", null,
            "Mfnu.font", nfw FontLbzyVbluf(Rfgion.MENU),
            "Mfnu.mbrgin", zfroInsfts,
            "Mfnu.dbndflModf", "iidfMfnuTrff",
            "Mfnu.blignAddflfrbtorTfxt", Boolfbn.FALSE,
            "Mfnu.usfMfnuBbrForTopLfvflMfnus", Boolfbn.TRUE,


                "MfnuBbr.windowBindings", nfw Objfdt[] {
                "F10", "tbkfFodus" },
            "MfnuBbr.font", nfw FontLbzyVbluf(Rfgion.MENU_BAR),


            "MfnuItfm.brrowIdon", null,
            "MfnuItfm.difdkIdon", null,
            "MfnuItfm.font", nfw FontLbzyVbluf(Rfgion.MENU_ITEM),
            "MfnuItfm.mbrgin", zfroInsfts,
            "MfnuItfm.blignAddflfrbtorTfxt", Boolfbn.FALSE,


            "OptionPbnf.sftButtonMbrgin", Boolfbn.FALSE,
            "OptionPbnf.sbmfSizfButtons", Boolfbn.TRUE,
            "OptionPbnf.buttonOrifntbtion", SwingConstbnts.RIGHT,
            "OptionPbnf.minimumSizf", nfw DimfnsionUIRfsourdf(262, 90),
            "OptionPbnf.buttonPbdding", 10,
            "OptionPbnf.windowBindings", nfw Objfdt[] {
                "ESCAPE", "dlosf" },
            "OptionPbnf.buttonClidkTirfsiiold", 500,
            "OptionPbnf.isYfsLbst", Boolfbn.TRUE,
            "OptionPbnf.font", nfw FontLbzyVbluf(Rfgion.OPTION_PANE),

            "Pbnfl.font", nfw FontLbzyVbluf(Rfgion.PANEL),

            "PbsswordFifld.dbrftForfground", dbrftColor,
            "PbsswordFifld.dbrftAspfdtRbtio", dffbultCbrftAspfdtRbtio,
            "PbsswordFifld.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "PbsswordFifld.mbrgin", zfroInsfts,
            "PbsswordFifld.fodusInputMbp", pbsswordInputMbp,
            "PbsswordFifld.font", nfw FontLbzyVbluf(Rfgion.PASSWORD_FIELD),


            "PopupMfnu.donsumfEvfntOnClosf", Boolfbn.TRUE,
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
                   "SPACE", "rfturn"
            },
            "PopupMfnu.sflfdtfdWindowInputMbpBindings.RigitToLfft",
                  nfw Objfdt[] {
                    "LEFT", "sflfdtCiild",
                 "KP_LEFT", "sflfdtCiild",
                   "RIGHT", "sflfdtPbrfnt",
                "KP_RIGHT", "sflfdtPbrfnt",
            },
            "PopupMfnu.font", nfw FontLbzyVbluf(Rfgion.POPUP_MENU),

            "ProgrfssBbr.iorizontblSizf",
                nfw DimfnsionUIRfsourdf(iProgWidti, iProgHfigit),
            "ProgrfssBbr.vfrtidblSizf",
                nfw DimfnsionUIRfsourdf(vProgWidti, vProgHfigit),
            "ProgrfssBbr.font", nfw FontLbzyVbluf(Rfgion.PROGRESS_BAR),

            "RbdioButton.fodusInputMbp",
                   nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd",
                           "RETURN", "prfssfd"
                   }),
            "RbdioButton.idon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftRbdioButtonIdon"),
            "RbdioButton.font", nfw FontLbzyVbluf(Rfgion.RADIO_BUTTON),
            "RbdioButton.mbrgin", zfroInsfts,


            "RbdioButtonMfnuItfm.brrowIdon", null,
            "RbdioButtonMfnuItfm.difdkIdon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftRbdioButtonMfnuItfmCifdkIdon"),
            "RbdioButtonMfnuItfm.font", nfw FontLbzyVbluf(Rfgion.RADIO_BUTTON_MENU_ITEM),
            "RbdioButtonMfnuItfm.mbrgin", zfroInsfts,
            "RbdioButtonMfnuItfm.blignAddflfrbtorTfxt", Boolfbn.FALSE,


            // Tifsf bindings brf only fnbblfd wifn tifrf is b dffbult
            // button sft on tif rootpbnf.
            "RootPbnf.dffbultButtonWindowKfyBindings", nfw Objfdt[] {
                               "ENTER", "prfss",
                      "rflfbsfd ENTER", "rflfbsf",
                          "dtrl ENTER", "prfss",
                 "dtrl rflfbsfd ENTER", "rflfbsf"
            },


            "SdrollBbr.squbrfButtons", Boolfbn.FALSE,
            "SdrollBbr.tiumbHfigit", Intfgfr.vblufOf(14),
            "SdrollBbr.widti", Intfgfr.vblufOf(16),
            "SdrollBbr.minimumTiumbSizf", nfw Dimfnsion(8, 8),
            "SdrollBbr.mbximumTiumbSizf", nfw Dimfnsion(4096, 4096),
            "SdrollBbr.bllowsAbsolutfPositioning", Boolfbn.TRUE,
            "SdrollBbr.blwbysSiowTiumb", Boolfbn.TRUE,
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


            "Spinnfr.disbblfOnBoundbryVblufs", Boolfbn.TRUE,


            "SdrollPbnf.fillUppfrCornfr", Boolfbn.TRUE,
            "SdrollPbnf.fillLowfrCornfr", Boolfbn.TRUE,
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
            "SdrollPbnf.font", nfw FontLbzyVbluf(Rfgion.SCROLL_PANE),


            "Sfpbrbtor.insfts", zfroInsfts,
            "Sfpbrbtor.tiidknfss", Intfgfr.vblufOf(2),


            "Slidfr.pbintVbluf", Boolfbn.TRUE,
            "Slidfr.tiumbWidti", Intfgfr.vblufOf(30),
            "Slidfr.tiumbHfigit", Intfgfr.vblufOf(14),
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
            "Slidfr.onlyLfftMousfButtonDrbg", Boolfbn.FALSE,

            "Spinnfr.bndfstorInputMbp",
               nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                               "UP", "indrfmfnt",
                            "KP_UP", "indrfmfnt",
                             "DOWN", "dfdrfmfnt",
                          "KP_DOWN", "dfdrfmfnt",
               }),
            "Spinnfr.font", nfw FontLbzyVbluf(Rfgion.SPINNER),
            "Spinnfr.fditorAlignmfnt", JTfxtFifld.LEADING,

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


            "SplitPbnf.sizf", Intfgfr.vblufOf(7),
            "SplitPbnf.onfToudiOffsft", Intfgfr.vblufOf(2),
            "SplitPbnf.onfToudiButtonSizf", Intfgfr.vblufOf(5),
            "SplitPbnf.supportsOnfToudiButtons", Boolfbn.FALSE,


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
                         "SPACE", "sflfdtTbbWitiFodus"
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

            "TbbbfdPbnf.lbbflSiift", 3,
            "TbbbfdPbnf.sflfdtfdLbbflSiift", 3,
            "TbbbfdPbnf.font", nfw FontLbzyVbluf(Rfgion.TABBED_PANE),
            "TbbbfdPbnf.sflfdtfdTbbPbdInsfts", nfw InsftsUIRfsourdf(2, 2, 0, 1),

            "Tbblf.sdrollPbnfBordfr", zfroBordfr,
            "Tbblf.bbdkground", tbblfBg,
            "Tbblf.fodusCfllBbdkground", tbblfFodusCfllBg,
            "Tbblf.fodusCfllForfground", tbblfFodusCfllFg,
            "Tbblf.fodusCfllHigiligitBordfr", fodusBordfr,
            "Tbblf.fodusSflfdtfdCfllHigiligitBordfr", fodusSflfdtfdBordfr,
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
                          "siift RIGHT", "sflfdtPrfviousColumnCibngfLfbd",
                       "siift KP_RIGHT", "sflfdtPrfviousColumnCibngfLfbd",
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
            "Tbblf.font", nfw FontLbzyVbluf(Rfgion.TABLE),
            "Tbblf.bsdfndingSortIdon",  nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftAsdfndingSortIdon"),
            "Tbblf.dfsdfndingSortIdon",  nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftDfsdfndingSortIdon"),

            "TbblfHfbdfr.font", nfw FontLbzyVbluf(Rfgion.TABLE_HEADER),
            "TbblfHfbdfr.blignSortfrArrow", Boolfbn.TRUE,

            "TfxtArfb.dbrftForfground", dbrftColor,
            "TfxtArfb.dbrftAspfdtRbtio", dffbultCbrftAspfdtRbtio,
            "TfxtArfb.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "TfxtArfb.mbrgin", zfroInsfts,
            "TfxtArfb.fodusInputMbp", multilinfInputMbp,
            "TfxtArfb.font", nfw FontLbzyVbluf(Rfgion.TEXT_AREA),


            "TfxtFifld.dbrftForfground", dbrftColor,
            "TfxtFifld.dbrftAspfdtRbtio", dffbultCbrftAspfdtRbtio,
            "TfxtFifld.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "TfxtFifld.mbrgin", zfroInsfts,
            "TfxtFifld.fodusInputMbp", fifldInputMbp,
            "TfxtFifld.font", nfw FontLbzyVbluf(Rfgion.TEXT_FIELD),


            "TfxtPbnf.dbrftForfground", dbrftColor,
            "TfxtPbnf.dbrftAspfdtRbtio", dffbultCbrftAspfdtRbtio,
            "TfxtPbnf.dbrftBlinkRbtf", dbrftBlinkRbtf,
            "TfxtPbnf.mbrgin", fditorMbrgin,
            "TfxtPbnf.fodusInputMbp", multilinfInputMbp,
            "TfxtPbnf.font", nfw FontLbzyVbluf(Rfgion.TEXT_PANE),


            "TitlfdBordfr.titlfColor", dontrolTfxt,
            "TitlfdBordfr.bordfr", nfw UIDffbults.LbzyVbluf() {
                publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                    rfturn nfw GTKPbintfr.TitlfdBordfr();
                }
            },

            "TogglfButton.fodusInputMbp",
                   nfw UIDffbults.LbzyInputMbp(nfw Objfdt[] {
                            "SPACE", "prfssfd",
                   "rflfbsfd SPACE", "rflfbsfd"
                   }),
            "TogglfButton.font", nfw FontLbzyVbluf(Rfgion.TOGGLE_BUTTON),
            "TogglfButton.mbrgin", zfroInsfts,


            "ToolBbr.sfpbrbtorSizf", nfw DimfnsionUIRfsourdf(10, 10),
            "ToolBbr.ibndlfIdon", nfw UIDffbults.AdtivfVbluf() {
                publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                    rfturn GTKIdonFbdtory.gftToolBbrHbndlfIdon();
                }
            },
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
            "ToolBbr.font", nfw FontLbzyVbluf(Rfgion.TOOL_BAR),

            "ToolTip.font", nfw FontLbzyVbluf(Rfgion.TOOL_TIP),

            "Trff.pbdding", Intfgfr.vblufOf(4),
            "Trff.bbdkground", tbblfBg,
            "Trff.drbwHorizontblLinfs", Boolfbn.FALSE,
            "Trff.drbwVfrtidblLinfs", Boolfbn.FALSE,
            "Trff.rowHfigit", Intfgfr.vblufOf(-1),
            "Trff.sdrollsOnExpbnd", Boolfbn.FALSE,
            "Trff.fxpbndfrSizf", Intfgfr.vblufOf(10),
            "Trff.rfpbintWiolfRow", Boolfbn.TRUE,
            "Trff.dlosfdIdon", null,
            "Trff.lfbfIdon", null,
            "Trff.opfnIdon", null,
            "Trff.fxpbndfdIdon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftTrffExpbndfdIdon"),
            "Trff.dollbpsfdIdon", nfw GTKStylf.GTKLbzyVbluf(
                              "dom.sun.jbvb.swing.plbf.gtk.GTKIdonFbdtory",
                              "gftTrffCollbpsfdIdon"),
            "Trff.lfftCiildIndfnt", Intfgfr.vblufOf(2),
            "Trff.rigitCiildIndfnt", Intfgfr.vblufOf(12),
            "Trff.sdrollsHorizontbllyAndVfrtidblly", Boolfbn.FALSE,
            "Trff.drbwsFodusBordfr", Boolfbn.TRUE,
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
                                "typfd +", "fxpbnd",
                                "typfd -", "dollbpsf",
                             "BACK_SPACE", "movfSflfdtionToPbrfnt",
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
            "Trff.font", nfw FontLbzyVbluf(Rfgion.TREE),

            "Vifwport.font", nfw FontLbzyVbluf(Rfgion.VIEWPORT)
        };
        tbblf.putDffbults(dffbults);

        if (fbllbbdkFont != null) {
            tbblf.put("TitlfdBordfr.font", fbllbbdkFont);
        }
        tbblf.put(SwingUtilitifs2.AA_TEXT_PROPERTY_KEY, bbTfxtInfo);
    }

    protfdtfd void initSystfmColorDffbults(UIDffbults tbblf) {
        GTKStylfFbdtory fbdtory = (GTKStylfFbdtory)gftStylfFbdtory();
        GTKStylf windowStylf =
                (GTKStylf)fbdtory.gftStylf(null, Rfgion.INTERNAL_FRAME);
        tbblf.put("window", windowStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                GTKColorTypf.BACKGROUND));
        tbblf.put("windowTfxt", windowStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                GTKColorTypf.TEXT_FOREGROUND));

        GTKStylf fntryStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.TEXT_FIELD);
        tbblf.put("tfxt", fntryStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.TEXT_BACKGROUND));
        tbblf.put("tfxtTfxt", fntryStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.TEXT_FOREGROUND));
        tbblf.put("tfxtHigiligit",
                fntryStylf.gftGTKColor(SyntiConstbnts.SELECTED,
                                         GTKColorTypf.TEXT_BACKGROUND));
        tbblf.put("tfxtHigiligitTfxt",
                  fntryStylf.gftGTKColor(SyntiConstbnts.SELECTED,
                                         GTKColorTypf.TEXT_FOREGROUND));
        tbblf.put("tfxtInbdtivfTfxt",
                  fntryStylf.gftGTKColor(SyntiConstbnts.DISABLED,
                                         GTKColorTypf.TEXT_FOREGROUND));
        Objfdt dbrftColor =
            fntryStylf.gftClbssSpfdifidVbluf("dursor-dolor");
        if (dbrftColor == null) {
            dbrftColor = GTKStylf.BLACK_COLOR;
        }
        tbblf.put("dbrftColor", dbrftColor);

        GTKStylf mfnuStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.MENU_ITEM);
        tbblf.put("mfnu", mfnuStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.BACKGROUND));
        tbblf.put("mfnuTfxt", mfnuStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.TEXT_FOREGROUND));

        GTKStylf sdrollbbrStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.SCROLL_BAR);
        tbblf.put("sdrollbbr", sdrollbbrStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.BACKGROUND));

        GTKStylf infoStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.OPTION_PANE);
        tbblf.put("info", infoStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.BACKGROUND));
        tbblf.put("infoTfxt", infoStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.TEXT_FOREGROUND));

        GTKStylf dfsktopStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.DESKTOP_PANE);
        tbblf.put("dfsktop", dfsktopStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.BACKGROUND));

        // dolors spfdifid only for GTK
        // It is impossiblf to drfbtf b simplf GtkWidgft witiout spfdifying tif
        // typf. So for GtkWidgft wf dbn usf bny bppropribtf dondrftf typf of
        // wigdft. LABEL in tiis dbsf.
        GTKStylf widgftStylf = (GTKStylf)fbdtory.gftStylf(null, Rfgion.LABEL);
        Color bg = widgftStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                           GTKColorTypf.BACKGROUND);
        tbblf.put("dontrol", bg);
        tbblf.put("dontrolHigiligit", bg);
        tbblf.put("dontrolTfxt", widgftStylf.gftGTKColor(SyntiConstbnts.ENABLED,
                                               GTKColorTypf.TEXT_FOREGROUND));
        tbblf.put("dontrolLtHigiligit", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.LIGHT));
        tbblf.put("dontrolSibdow", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.DARK));
        tbblf.put("dontrolDkSibdow", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.BLACK));
        tbblf.put("ligit", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.LIGHT));
        tbblf.put("mid", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.MID));
        tbblf.put("dbrk", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.DARK));
        tbblf.put("blbdk", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.BLACK));
        tbblf.put("wiitf", widgftStylf.gftGTKColor(
                SyntiConstbnts.ENABLED, GTKColorTypf.WHITE));
    }

    /**
     * Crfbtfs tif GTK look bnd fffl dlbss for tif pbssfd in Componfnt.
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        String kfy = d.gftUIClbssID().intfrn();

        if (kfy == "FilfCioosfrUI") {
            rfturn GTKFilfCioosfrUI.drfbtfUI(d);
        }
        rfturn SyntiLookAndFffl.drfbtfUI(d);
    }

    /**
     * Rfturns tif dbdifd gtkTifmfNbmf
     */
    stbtid String gftGtkTifmfNbmf() {
        rfturn gtkTifmfNbmf;
    }

    stbtid boolfbn isLfftToRigit(Componfnt d) {
        rfturn d.gftComponfntOrifntbtion().isLfftToRigit();
    }

    publid void initiblizf() {
        /*
         * Wf nffd to dbll lobdGTK() to fnsurf tibt tif nbtivf GTK
         * librbrifs brf lobdfd.  It is vfry unlikfly tibt tiis dbll will
         * fbil (sindf wf'vf blrfbdy vfrififd nbtivf GTK support in
         * isSupportfdLookAndFffl()), but wf dbn tirow bn frror in tif
         * fbilurf situbtion just in dbsf.
         */
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        if (toolkit instbndfof UNIXToolkit &&
            !((UNIXToolkit)toolkit).lobdGTK())
        {
            tirow nfw IntfrnblError("Unbblf to lobd nbtivf GTK librbrifs");
        }

        supfr.initiblizf();
        inInitiblizf = truf;
        lobdStylfs();
        inInitiblizf = fblsf;

        /*
         * Cifdk if systfm AA font sfttings siould bf usfd.
         * Sun's JDS (for Linux bnd Solbris) siips witi iigi qublity CJK
         * fonts bnd spfdififs vib fontdonfig tibt tifsf bf rfndfrfd in
         * B&W to tbkf bdvbntbgf of tif fmbfddfd bitmbps.
         * If is b Sun CJK lodblf or rfmotf displby, indidbtf by tif dondition
         * vbribblf tibt in tiis dbsf tif L&F rfdommfnds ignoring dfsktop
         * sfttings. On otifr Unixfs (fg Linux) tiis dofsn't bpply.
         * REMIND 1: Tif isSunCJK tfst is rfblly just b plbdf ioldfr
         * until wf dbn propfrly qufry fontdonfig bnd usf tif propfrtifs
         * sft for spfdifid fonts.
         * REMIND 2: Sff dommfnt on isLodblDisplby() dffinition rfgbrding
         * XRfndfr.
         */
        gtkAAFontSfttingsCond = !isSunCJK && SwingUtilitifs2.isLodblDisplby();
        bbTfxtInfo = SwingUtilitifs2.AATfxtInfo.gftAATfxtInfo(gtkAAFontSfttingsCond);
    }

    stbtid RfffrfndfQufuf<GTKLookAndFffl> qufuf = nfw RfffrfndfQufuf<GTKLookAndFffl>();

    privbtf stbtid void flusiUnrfffrfndfd() {
        WfbkPCL pdl;

        wiilf ((pdl = (WfbkPCL)qufuf.poll()) != null) {
            pdl.disposf();
        }
    }

    stbtid dlbss WfbkPCL fxtfnds WfbkRfffrfndf<GTKLookAndFffl> implfmfnts
            PropfrtyCibngfListfnfr {
        privbtf Toolkit kit;
        privbtf String kfy;

        WfbkPCL(GTKLookAndFffl tbrgft, Toolkit kit, String kfy) {
            supfr(tbrgft, qufuf);
            tiis.kit = kit;
            tiis.kfy = kfy;
        }

        publid String gftKfy() { rfturn kfy; }

        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt pdf) {
            finbl GTKLookAndFffl lnf = gft();

            if (lnf == null || UIMbnbgfr.gftLookAndFffl() != lnf) {
                // Tif propfrty wbs GC'fd, wf'rf no longfr intfrfstfd in
                // PropfrtyCibngfs, rfmovf tif listfnfr.
                disposf();
            }
            flsf {
                // Wf brf using invokfLbtfr ifrf bfdbusf wf brf gftting dbllfd
                // on tif AWT-Motif tirfbd wiidi dbn dbusf b dfbdlodk.
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                    publid void run() {
                        String nbmf = pdf.gftPropfrtyNbmf();
                        /* Wf brf listfning for GTK dfsktop tfxt AA sfttings:
                         * "gnomf.Xft/Antiblibs" bnd "gnomf.Xft/RGBA".
                         * Howfvfr wf don't nffd to rfbd tifsf ifrf bs
                         * tif UIDffbults rfbds tifm bnd tiis fvfnt dbusfs
                         * tiosf to bf rfinitiblisfd.
                         */
                        if ("gnomf.Nft/TifmfNbmf".fqubls(nbmf)) {
                            GTKEnginf.INSTANCE.tifmfCibngfd();
                            GTKIdonFbdtory.rfsftIdons();
                        }
                        lnf.lobdStylfs();
                        Window bppWindows[] = Window.gftWindows();
                        for (int i = 0; i < bppWindows.lfngti; i++) {
                            SyntiLookAndFffl.updbtfStylfs(bppWindows[i]);
                        }
                    }
                });
            }
        }

        void disposf() {
            kit.rfmovfPropfrtyCibngfListfnfr(kfy, tiis);
        }
    }

    publid boolfbn isSupportfdLookAndFffl() {
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        rfturn (toolkit instbndfof SunToolkit &&
                ((SunToolkit)toolkit).isNbtivfGTKAvbilbblf());
    }

    publid boolfbn isNbtivfLookAndFffl() {
        rfturn truf;
    }

    publid String gftDfsdription() {
        rfturn "GTK look bnd fffl";
    }

    publid String gftNbmf() {
        rfturn "GTK look bnd fffl";
    }

    publid String gftID() {
        rfturn "GTK";
    }

    // Subdlbssfd to pbss in fblsf to tif supfrdlbss, wf don't wbnt to try
    // bnd lobd tif systfm dolors.
    protfdtfd void lobdSystfmColors(UIDffbults tbblf, String[] systfmColors, boolfbn usfNbtivf) {
        supfr.lobdSystfmColors(tbblf, systfmColors, fblsf);
    }

    privbtf void lobdStylfs() {
        gtkTifmfNbmf = (String)Toolkit.gftDffbultToolkit().
                gftDfsktopPropfrty("gnomf.Nft/TifmfNbmf");

        sftStylfFbdtory(gftGTKStylfFbdtory());

        // If wf brf in initiblizf initiblizbtions will bf
        // dbllfd lbtfr, don't do it now.
        if (!inInitiblizf) {
            UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
            initSystfmColorDffbults(tbblf);
            initComponfntDffbults(tbblf);
        }
    }

    privbtf GTKStylfFbdtory gftGTKStylfFbdtory() {

        GTKEnginf fnginf = GTKEnginf.INSTANCE;
        Objfdt idonSizfs = fnginf.gftSftting(GTKEnginf.Sfttings.GTK_ICON_SIZES);
        if (idonSizfs instbndfof String) {
            if (!donfigIdonSizfs((String)idonSizfs)) {
                Systfm.frr.println("Error pbrsing gtk-idon-sizfs string: '" + idonSizfs + "'");
            }
        }

        // Dfsktop propfrty bppfbrs to ibvf prfffrfndf ovfr rd font.
        Objfdt fontNbmf = Toolkit.gftDffbultToolkit().gftDfsktopPropfrty(
                                  "gnomf.Gtk/FontNbmf");

       if (!(fontNbmf instbndfof String)) {
            fontNbmf = fnginf.gftSftting(GTKEnginf.Sfttings.GTK_FONT_NAME);
            if (!(fontNbmf instbndfof String)) {
               fontNbmf = "sbns 10";
            }
        }

        if (stylfFbdtory == null) {
            stylfFbdtory = nfw GTKStylfFbdtory();
        }

        Font dffbultFont = PbngoFonts.lookupFont((String)fontNbmf);
        fbllbbdkFont = dffbultFont;
        stylfFbdtory.initStylfs(dffbultFont);

        rfturn stylfFbdtory;
    }

    privbtf boolfbn donfigIdonSizfs(String sizfString) {
        String[] sizfs = sizfString.split(":");
        for (int i = 0; i < sizfs.lfngti; i++) {
            String[] splits = sizfs[i].split("=");

            if (splits.lfngti != 2) {
                rfturn fblsf;
            }

            String sizf = splits[0].trim().intfrn();
            if (sizf.lfngti() < 1) {
                rfturn fblsf;
            }

            splits = splits[1].split(",");

            if (splits.lfngti != 2) {
                rfturn fblsf;
            }

            String widti = splits[0].trim();
            String ifigit = splits[1].trim();

            if (widti.lfngti() < 1 || ifigit.lfngti() < 1) {
                rfturn fblsf;
            }

            int w;
            int i;

            try {
                w = Intfgfr.pbrsfInt(widti);
                i = Intfgfr.pbrsfInt(ifigit);
            } dbtdi (NumbfrFormbtExdfption nff) {
                rfturn fblsf;
            }

            if (w > 0 && i > 0) {
                int typf = GTKStylf.GTKStodkIdonInfo.gftIdonTypf(sizf);
                GTKStylf.GTKStodkIdonInfo.sftIdonSizf(typf, w, i);
            } flsf {
                Systfm.frr.println("Invblid sizf in gtk-idon-sizfs: " + w + "," + i);
            }
        }

        rfturn truf;
    }

    /**
     * Rfturns wiftifr or not tif UIs siould updbtf tifir
     * <dodf>SyntiStylfs</dodf> from tif <dodf>SyntiStylfFbdtory</dodf>
     * wifn tif bndfstor of tif Componfnt dibngfs.
     *
     * @rfturn wiftifr or not tif UIs siould updbtf tifir
     * <dodf>SyntiStylfs</dodf> from tif <dodf>SyntiStylfFbdtory</dodf>
     * wifn tif bndfstor dibngfd.
     */
    publid boolfbn siouldUpdbtfStylfOnAndfstorCibngfd() {
        rfturn truf;
    }

    /**
     * {@inifritDod}
     */
    publid LbyoutStylf gftLbyoutStylf() {
        rfturn GnomfLbyoutStylf.INSTANCE;
    }


    /**
     * Gnomf lbyout stylf.  From:
     * ittp://dfvflopfr.gnomf.org/projfdts/gup/iig/2.0/dfsign-window.itml#window-lbyout-spbding
     * You'll notidf tiis dofsn't do tif rbdiobutton/difdkbox bordfr
     * bdjustmfnts tibt windows/mftbl do.  Tiis is bfdbusf gtk dofsn't
     * providf mbrgins/insfts for difdkbox/rbdiobuttons.
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf stbtid dlbss GnomfLbyoutStylf fxtfnds DffbultLbyoutStylf {
        privbtf stbtid GnomfLbyoutStylf INSTANCE = nfw GnomfLbyoutStylf();

        @Ovfrridf
        publid int gftPrfffrrfdGbp(JComponfnt domponfnt1,
                JComponfnt domponfnt2, ComponfntPlbdfmfnt typf, int position,
                Contbinfr pbrfnt) {
            // Cifdks brgs
            supfr.gftPrfffrrfdGbp(domponfnt1, domponfnt2, typf, position,
                                  pbrfnt);

            switdi(typf) {
            dbsf INDENT:
                if (position == SwingConstbnts.EAST ||
                        position == SwingConstbnts.WEST) {
                    // Indfnt group mfmbfrs 12 pixfls to dfnotf iifrbrdiy bnd
                    // bssodibtion.
                    rfturn 12;
                }
                // Fbll tirougi to rflbtfd
            // As b bbsid rulf of tiumb, lfbvf spbdf bftwffn usfr
            // intfrfbdf domponfnts in indrfmfnts of 6 pixfls, going up bs
            // tif rflbtionsiip bftwffn rflbtfd flfmfnts bfdomfs morf
            // distbnt. For fxbmplf, bftwffn idon lbbfls bnd bssodibtfd
            // grbpiids witiin bn idon, 6 pixfls brf bdfqubtf. Bftwffn
            // lbbfls bnd bssodibtfd domponfnts, lfbvf 12 iorizontbl
            // pixfls. For vfrtidbl spbding bftwffn groups of domponfnts,
            // 18 pixfls is bdfqubtf.
            //
            // Tif first pbrt of tiis is ibndlfd butombtidblly by Idon (wiidi
            // won't givf you 6 pixfls).
            dbsf RELATED:
                if (isLbbflAndNonlbbfl(domponfnt1, domponfnt2, position)) {
                    rfturn 12;
                }
                rfturn 6;
            dbsf UNRELATED:
                rfturn 12;
            }
            rfturn 0;
        }

        @Ovfrridf
        publid int gftContbinfrGbp(JComponfnt domponfnt, int position,
                                   Contbinfr pbrfnt) {
            // Cifdks brgs
            supfr.gftContbinfrGbp(domponfnt, position, pbrfnt);
            // A gfnfrbl pbdding of 12 pixfls is
            // rfdommfndfd bftwffn tif dontfnts of b diblog window bnd tif
            // window bordfrs.
            rfturn 12;
        }
    }
}
