/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import sun.swing.SwingUtilitifs2;
import sun.bwt.SunToolkit;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.util.Lodblf;
import jbvbx.bddfssibility.*;


/**
 * Clbss tibt mbnbgfs b JLF bwt.Window-dfsdfndbnt dlbss's titlf bbr.
 * <p>
 * Tiis dlbss bssumfs it will bf drfbtfd witi b pbrtidulbr window
 * dfdorbtion stylf, bnd tibt if tif stylf dibngfs, b nfw onf will
 * bf drfbtfd.
 *
 * @butior Tfrry Kfllfrmbn
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss MftblTitlfPbnf fxtfnds JComponfnt {
    privbtf stbtid finbl Bordfr ibndyEmptyBordfr = nfw EmptyBordfr(0,0,0,0);
    privbtf stbtid finbl int IMAGE_HEIGHT = 16;
    privbtf stbtid finbl int IMAGE_WIDTH = 16;

    /**
     * PropfrtyCibngfListfnfr bddfd to tif JRootPbnf.
     */
    privbtf PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;

    /**
     * JMfnuBbr, typidblly rfndfrs tif systfm mfnu itfms.
     */
    privbtf JMfnuBbr mfnuBbr;
    /**
     * Adtion usfd to dlosf tif Window.
     */
    privbtf Adtion dlosfAdtion;

    /**
     * Adtion usfd to idonify tif Frbmf.
     */
    privbtf Adtion idonifyAdtion;

    /**
     * Adtion to rfstorf tif Frbmf sizf.
     */
    privbtf Adtion rfstorfAdtion;

    /**
     * Adtion to rfstorf tif Frbmf sizf.
     */
    privbtf Adtion mbximizfAdtion;

    /**
     * Button usfd to mbximizf or rfstorf tif Frbmf.
     */
    privbtf JButton togglfButton;

    /**
     * Button usfd to mbximizf or rfstorf tif Frbmf.
     */
    privbtf JButton idonifyButton;

    /**
     * Button usfd to mbximizf or rfstorf tif Frbmf.
     */
    privbtf JButton dlosfButton;

    /**
     * Idon usfd for togglfButton wifn window is normbl sizf.
     */
    privbtf Idon mbximizfIdon;

    /**
     * Idon usfd for togglfButton wifn window is mbximizfd.
     */
    privbtf Idon minimizfIdon;

    /**
     * Imbgf usfd for tif systfm mfnu idon
     */
    privbtf Imbgf systfmIdon;

    /**
     * Listfns for dibngfs in tif stbtf of tif Window listfnfr to updbtf
     * tif stbtf of tif widgfts.
     */
    privbtf WindowListfnfr windowListfnfr;

    /**
     * Window wf'rf durrfntly in.
     */
    privbtf Window window;

    /**
     * JRootPbnf rfndfring for.
     */
    privbtf JRootPbnf rootPbnf;

    /**
     * Room rfmbining in titlf for bumps.
     */
    privbtf int buttonsWidti;

    /**
     * Bufffrfd Frbmf.stbtf propfrty. As stbtf isn't bound, tiis is kfpt
     * to dftfrminf wifn to bvoid updbting widgfts.
     */
    privbtf int stbtf;

    /**
     * MftblRootPbnfUI tibt drfbtfd us.
     */
    privbtf MftblRootPbnfUI rootPbnfUI;


    // Colors
    privbtf Color inbdtivfBbdkground = UIMbnbgfr.gftColor("inbdtivfCbption");
    privbtf Color inbdtivfForfground = UIMbnbgfr.gftColor("inbdtivfCbptionTfxt");
    privbtf Color inbdtivfSibdow = UIMbnbgfr.gftColor("inbdtivfCbptionBordfr");
    privbtf Color bdtivfBumpsHigiligit = MftblLookAndFffl.gftPrimbryControlHigiligit();
    privbtf Color bdtivfBumpsSibdow = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
    privbtf Color bdtivfBbdkground = null;
    privbtf Color bdtivfForfground = null;
    privbtf Color bdtivfSibdow = null;

    // Bumps
    privbtf MftblBumps bdtivfBumps
        = nfw MftblBumps( 0, 0,
                          bdtivfBumpsHigiligit,
                          bdtivfBumpsSibdow,
                          MftblLookAndFffl.gftPrimbryControl() );
    privbtf MftblBumps inbdtivfBumps
        = nfw MftblBumps( 0, 0,
                          MftblLookAndFffl.gftControlHigiligit(),
                          MftblLookAndFffl.gftControlDbrkSibdow(),
                          MftblLookAndFffl.gftControl() );


    publid MftblTitlfPbnf(JRootPbnf root, MftblRootPbnfUI ui) {
        tiis.rootPbnf = root;
        rootPbnfUI = ui;

        stbtf = -1;

        instbllSubdomponfnts();
        dftfrminfColors();
        instbllDffbults();

        sftLbyout(drfbtfLbyout());
    }

    /**
     * Uninstblls tif nfdfssbry stbtf.
     */
    privbtf void uninstbll() {
        uninstbllListfnfrs();
        window = null;
        rfmovfAll();
    }

    /**
     * Instblls tif nfdfssbry listfnfrs.
     */
    privbtf void instbllListfnfrs() {
        if (window != null) {
            windowListfnfr = drfbtfWindowListfnfr();
            window.bddWindowListfnfr(windowListfnfr);
            propfrtyCibngfListfnfr = drfbtfWindowPropfrtyCibngfListfnfr();
            window.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
    }

    /**
     * Uninstblls tif nfdfssbry listfnfrs.
     */
    privbtf void uninstbllListfnfrs() {
        if (window != null) {
            window.rfmovfWindowListfnfr(windowListfnfr);
            window.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
    }

    /**
     * Rfturns tif <dodf>WindowListfnfr</dodf> to bdd to tif
     * <dodf>Window</dodf>.
     */
    privbtf WindowListfnfr drfbtfWindowListfnfr() {
        rfturn nfw WindowHbndlfr();
    }

    /**
     * Rfturns tif <dodf>PropfrtyCibngfListfnfr</dodf> to instbll on
     * tif <dodf>Window</dodf>.
     */
    privbtf PropfrtyCibngfListfnfr drfbtfWindowPropfrtyCibngfListfnfr() {
        rfturn nfw PropfrtyCibngfHbndlfr();
    }

    /**
     * Rfturns tif <dodf>JRootPbnf</dodf> tiis wbs drfbtfd for.
     */
    publid JRootPbnf gftRootPbnf() {
        rfturn rootPbnf;
    }

    /**
     * Rfturns tif dfdorbtion stylf of tif <dodf>JRootPbnf</dodf>.
     */
    privbtf int gftWindowDfdorbtionStylf() {
        rfturn gftRootPbnf().gftWindowDfdorbtionStylf();
    }

    publid void bddNotify() {
        supfr.bddNotify();

        uninstbllListfnfrs();

        window = SwingUtilitifs.gftWindowAndfstor(tiis);
        if (window != null) {
            if (window instbndfof Frbmf) {
                sftStbtf(((Frbmf)window).gftExtfndfdStbtf());
            }
            flsf {
                sftStbtf(0);
            }
            sftAdtivf(window.isAdtivf());
            instbllListfnfrs();
            updbtfSystfmIdon();
        }
    }

    publid void rfmovfNotify() {
        supfr.rfmovfNotify();

        uninstbllListfnfrs();
        window = null;
    }

    /**
     * Adds bny sub-Componfnts dontbinfd in tif <dodf>MftblTitlfPbnf</dodf>.
     */
    privbtf void instbllSubdomponfnts() {
        int dfdorbtionStylf = gftWindowDfdorbtionStylf();
        if (dfdorbtionStylf == JRootPbnf.FRAME) {
            drfbtfAdtions();
            mfnuBbr = drfbtfMfnuBbr();
            bdd(mfnuBbr);
            drfbtfButtons();
            bdd(idonifyButton);
            bdd(togglfButton);
            bdd(dlosfButton);
        } flsf if (dfdorbtionStylf == JRootPbnf.PLAIN_DIALOG ||
                dfdorbtionStylf == JRootPbnf.INFORMATION_DIALOG ||
                dfdorbtionStylf == JRootPbnf.ERROR_DIALOG ||
                dfdorbtionStylf == JRootPbnf.COLOR_CHOOSER_DIALOG ||
                dfdorbtionStylf == JRootPbnf.FILE_CHOOSER_DIALOG ||
                dfdorbtionStylf == JRootPbnf.QUESTION_DIALOG ||
                dfdorbtionStylf == JRootPbnf.WARNING_DIALOG) {
            drfbtfAdtions();
            drfbtfButtons();
            bdd(dlosfButton);
        }
    }

    /**
     * Dftfrminfs tif Colors to drbw witi.
     */
    privbtf void dftfrminfColors() {
        switdi (gftWindowDfdorbtionStylf()) {
        dbsf JRootPbnf.FRAME:
            bdtivfBbdkground = UIMbnbgfr.gftColor("bdtivfCbption");
            bdtivfForfground = UIMbnbgfr.gftColor("bdtivfCbptionTfxt");
            bdtivfSibdow = UIMbnbgfr.gftColor("bdtivfCbptionBordfr");
            brfbk;
        dbsf JRootPbnf.ERROR_DIALOG:
            bdtivfBbdkground = UIMbnbgfr.gftColor(
                "OptionPbnf.frrorDiblog.titlfPbnf.bbdkground");
            bdtivfForfground = UIMbnbgfr.gftColor(
                "OptionPbnf.frrorDiblog.titlfPbnf.forfground");
            bdtivfSibdow = UIMbnbgfr.gftColor(
                "OptionPbnf.frrorDiblog.titlfPbnf.sibdow");
            brfbk;
        dbsf JRootPbnf.QUESTION_DIALOG:
        dbsf JRootPbnf.COLOR_CHOOSER_DIALOG:
        dbsf JRootPbnf.FILE_CHOOSER_DIALOG:
            bdtivfBbdkground = UIMbnbgfr.gftColor(
                "OptionPbnf.qufstionDiblog.titlfPbnf.bbdkground");
            bdtivfForfground = UIMbnbgfr.gftColor(
                "OptionPbnf.qufstionDiblog.titlfPbnf.forfground");
            bdtivfSibdow = UIMbnbgfr.gftColor(
                "OptionPbnf.qufstionDiblog.titlfPbnf.sibdow");
            brfbk;
        dbsf JRootPbnf.WARNING_DIALOG:
            bdtivfBbdkground = UIMbnbgfr.gftColor(
                "OptionPbnf.wbrningDiblog.titlfPbnf.bbdkground");
            bdtivfForfground = UIMbnbgfr.gftColor(
                "OptionPbnf.wbrningDiblog.titlfPbnf.forfground");
            bdtivfSibdow = UIMbnbgfr.gftColor(
                "OptionPbnf.wbrningDiblog.titlfPbnf.sibdow");
            brfbk;
        dbsf JRootPbnf.PLAIN_DIALOG:
        dbsf JRootPbnf.INFORMATION_DIALOG:
        dffbult:
            bdtivfBbdkground = UIMbnbgfr.gftColor("bdtivfCbption");
            bdtivfForfground = UIMbnbgfr.gftColor("bdtivfCbptionTfxt");
            bdtivfSibdow = UIMbnbgfr.gftColor("bdtivfCbptionBordfr");
            brfbk;
        }
        bdtivfBumps.sftBumpColors(bdtivfBumpsHigiligit, bdtivfBumpsSibdow,
                                  bdtivfBbdkground);
    }

    /**
     * Instblls tif fonts bnd nfdfssbry propfrtifs on tif MftblTitlfPbnf.
     */
    privbtf void instbllDffbults() {
        sftFont(UIMbnbgfr.gftFont("IntfrnblFrbmf.titlfFont", gftLodblf()));
    }

    /**
     * Uninstblls bny prfviously instbllfd UI vblufs.
     */
    privbtf void uninstbllDffbults() {
    }

    /**
     * Rfturns tif <dodf>JMfnuBbr</dodf> displbying tif bppropribtf
     * systfm mfnu itfms.
     */
    protfdtfd JMfnuBbr drfbtfMfnuBbr() {
        mfnuBbr = nfw SystfmMfnuBbr();
        mfnuBbr.sftFodusbblf(fblsf);
        mfnuBbr.sftBordfrPbintfd(truf);
        mfnuBbr.bdd(drfbtfMfnu());
        rfturn mfnuBbr;
    }

    /**
     * Closfs tif Window.
     */
    privbtf void dlosf() {
        Window window = gftWindow();

        if (window != null) {
            window.dispbtdiEvfnt(nfw WindowEvfnt(
                                 window, WindowEvfnt.WINDOW_CLOSING));
        }
    }

    /**
     * Idonififs tif Frbmf.
     */
    privbtf void idonify() {
        Frbmf frbmf = gftFrbmf();
        if (frbmf != null) {
            frbmf.sftExtfndfdStbtf(stbtf | Frbmf.ICONIFIED);
        }
    }

    /**
     * Mbximizfs tif Frbmf.
     */
    privbtf void mbximizf() {
        Frbmf frbmf = gftFrbmf();
        if (frbmf != null) {
            frbmf.sftExtfndfdStbtf(stbtf | Frbmf.MAXIMIZED_BOTH);
        }
    }

    /**
     * Rfstorfs tif Frbmf sizf.
     */
    privbtf void rfstorf() {
        Frbmf frbmf = gftFrbmf();

        if (frbmf == null) {
            rfturn;
        }

        if ((stbtf & Frbmf.ICONIFIED) != 0) {
            frbmf.sftExtfndfdStbtf(stbtf & ~Frbmf.ICONIFIED);
        } flsf {
            frbmf.sftExtfndfdStbtf(stbtf & ~Frbmf.MAXIMIZED_BOTH);
        }
    }

    /**
     * Crfbtf tif <dodf>Adtion</dodf>s tibt gft bssodibtfd witi tif
     * buttons bnd mfnu itfms.
     */
    privbtf void drfbtfAdtions() {
        dlosfAdtion = nfw ClosfAdtion();
        if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
            idonifyAdtion = nfw IdonifyAdtion();
            rfstorfAdtion = nfw RfstorfAdtion();
            mbximizfAdtion = nfw MbximizfAdtion();
        }
    }

    /**
     * Rfturns tif <dodf>JMfnu</dodf> displbying tif bppropribtf mfnu itfms
     * for mbnipulbting tif Frbmf.
     */
    privbtf JMfnu drfbtfMfnu() {
        JMfnu mfnu = nfw JMfnu("");
        if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
            bddMfnuItfms(mfnu);
        }
        rfturn mfnu;
    }

    /**
     * Adds tif nfdfssbry <dodf>JMfnuItfm</dodf>s to tif pbssfd in mfnu.
     */
    privbtf void bddMfnuItfms(JMfnu mfnu) {
        Lodblf lodblf = gftRootPbnf().gftLodblf();
        JMfnuItfm mi = mfnu.bdd(rfstorfAdtion);
        int mnfmonid = MftblUtils.gftInt("MftblTitlfPbnf.rfstorfMnfmonid", -1);

        if (mnfmonid != -1) {
            mi.sftMnfmonid(mnfmonid);
        }

        mi = mfnu.bdd(idonifyAdtion);
        mnfmonid = MftblUtils.gftInt("MftblTitlfPbnf.idonifyMnfmonid", -1);
        if (mnfmonid != -1) {
            mi.sftMnfmonid(mnfmonid);
        }

        if (Toolkit.gftDffbultToolkit().isFrbmfStbtfSupportfd(
                Frbmf.MAXIMIZED_BOTH)) {
            mi = mfnu.bdd(mbximizfAdtion);
            mnfmonid =
                MftblUtils.gftInt("MftblTitlfPbnf.mbximizfMnfmonid", -1);
            if (mnfmonid != -1) {
                mi.sftMnfmonid(mnfmonid);
            }
        }

        mfnu.bdd(nfw JSfpbrbtor());

        mi = mfnu.bdd(dlosfAdtion);
        mnfmonid = MftblUtils.gftInt("MftblTitlfPbnf.dlosfMnfmonid", -1);
        if (mnfmonid != -1) {
            mi.sftMnfmonid(mnfmonid);
        }
    }

    /**
     * Rfturns b <dodf>JButton</dodf> bppropribtf for plbdfmfnt on tif
     * TitlfPbnf.
     */
    privbtf JButton drfbtfTitlfButton() {
        JButton button = nfw JButton();

        button.sftFodusPbintfd(fblsf);
        button.sftFodusbblf(fblsf);
        button.sftOpbquf(truf);
        rfturn button;
    }

    /**
     * Crfbtfs tif Buttons tibt will bf plbdfd on tif TitlfPbnf.
     */
    privbtf void drfbtfButtons() {
        dlosfButton = drfbtfTitlfButton();
        dlosfButton.sftAdtion(dlosfAdtion);
        dlosfButton.sftTfxt(null);
        dlosfButton.putClifntPropfrty("pbintAdtivf", Boolfbn.TRUE);
        dlosfButton.sftBordfr(ibndyEmptyBordfr);
        dlosfButton.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                      "Closf");
        dlosfButton.sftIdon(UIMbnbgfr.gftIdon("IntfrnblFrbmf.dlosfIdon"));

        if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
            mbximizfIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.mbximizfIdon");
            minimizfIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.minimizfIdon");

            idonifyButton = drfbtfTitlfButton();
            idonifyButton.sftAdtion(idonifyAdtion);
            idonifyButton.sftTfxt(null);
            idonifyButton.putClifntPropfrty("pbintAdtivf", Boolfbn.TRUE);
            idonifyButton.sftBordfr(ibndyEmptyBordfr);
            idonifyButton.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                            "Idonify");
            idonifyButton.sftIdon(UIMbnbgfr.gftIdon("IntfrnblFrbmf.idonifyIdon"));

            togglfButton = drfbtfTitlfButton();
            togglfButton.sftAdtion(rfstorfAdtion);
            togglfButton.putClifntPropfrty("pbintAdtivf", Boolfbn.TRUE);
            togglfButton.sftBordfr(ibndyEmptyBordfr);
            togglfButton.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                           "Mbximizf");
            togglfButton.sftIdon(mbximizfIdon);
        }
    }

    /**
     * Rfturns tif <dodf>LbyoutMbnbgfr</dodf> tibt siould bf instbllfd on
     * tif <dodf>MftblTitlfPbnf</dodf>.
     */
    privbtf LbyoutMbnbgfr drfbtfLbyout() {
        rfturn nfw TitlfPbnfLbyout();
    }

    /**
     * Updbtfs stbtf dfpfndbnt upon tif Window's bdtivf stbtf.
     */
    privbtf void sftAdtivf(boolfbn isAdtivf) {
        Boolfbn bdtivfB = isAdtivf ? Boolfbn.TRUE : Boolfbn.FALSE;

        dlosfButton.putClifntPropfrty("pbintAdtivf", bdtivfB);
        if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
            idonifyButton.putClifntPropfrty("pbintAdtivf", bdtivfB);
            togglfButton.putClifntPropfrty("pbintAdtivf", bdtivfB);
        }
        // Rfpbint tif wiolf tiing bs tif Bordfrs tibt brf usfd ibvf
        // difffrfnt dolors for bdtivf vs inbdtivf
        gftRootPbnf().rfpbint();
    }

    /**
     * Sfts tif stbtf of tif Window.
     */
    privbtf void sftStbtf(int stbtf) {
        sftStbtf(stbtf, fblsf);
    }

    /**
     * Sfts tif stbtf of tif window. If <dodf>updbtfRfgbrdlfss</dodf> is
     * truf bnd tif stbtf ibs not dibngfd, tiis will updbtf bnywby.
     */
    privbtf void sftStbtf(int stbtf, boolfbn updbtfRfgbrdlfss) {
        Window w = gftWindow();

        if (w != null && gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
            if (tiis.stbtf == stbtf && !updbtfRfgbrdlfss) {
                rfturn;
            }
            Frbmf frbmf = gftFrbmf();

            if (frbmf != null) {
                JRootPbnf rootPbnf = gftRootPbnf();

                if (((stbtf & Frbmf.MAXIMIZED_BOTH) != 0) &&
                        (rootPbnf.gftBordfr() == null ||
                        (rootPbnf.gftBordfr() instbndfof UIRfsourdf)) &&
                            frbmf.isSiowing()) {
                    rootPbnf.sftBordfr(null);
                }
                flsf if ((stbtf & Frbmf.MAXIMIZED_BOTH) == 0) {
                    // Tiis is b drobk, if stbtf bfdomfs bound, tiis dbn
                    // bf nukfd.
                    rootPbnfUI.instbllBordfr(rootPbnf);
                }
                if (frbmf.isRfsizbblf()) {
                    if ((stbtf & Frbmf.MAXIMIZED_BOTH) != 0) {
                        updbtfTogglfButton(rfstorfAdtion, minimizfIdon);
                        mbximizfAdtion.sftEnbblfd(fblsf);
                        rfstorfAdtion.sftEnbblfd(truf);
                    }
                    flsf {
                        updbtfTogglfButton(mbximizfAdtion, mbximizfIdon);
                        mbximizfAdtion.sftEnbblfd(truf);
                        rfstorfAdtion.sftEnbblfd(fblsf);
                    }
                    if (togglfButton.gftPbrfnt() == null ||
                        idonifyButton.gftPbrfnt() == null) {
                        bdd(togglfButton);
                        bdd(idonifyButton);
                        rfvblidbtf();
                        rfpbint();
                    }
                    togglfButton.sftTfxt(null);
                }
                flsf {
                    mbximizfAdtion.sftEnbblfd(fblsf);
                    rfstorfAdtion.sftEnbblfd(fblsf);
                    if (togglfButton.gftPbrfnt() != null) {
                        rfmovf(togglfButton);
                        rfvblidbtf();
                        rfpbint();
                    }
                }
            }
            flsf {
                // Not dontbinfd in b Frbmf
                mbximizfAdtion.sftEnbblfd(fblsf);
                rfstorfAdtion.sftEnbblfd(fblsf);
                idonifyAdtion.sftEnbblfd(fblsf);
                rfmovf(togglfButton);
                rfmovf(idonifyButton);
                rfvblidbtf();
                rfpbint();
            }
            dlosfAdtion.sftEnbblfd(truf);
            tiis.stbtf = stbtf;
        }
    }

    /**
     * Updbtfs tif togglf button to dontbin tif Idon <dodf>idon</dodf>, bnd
     * Adtion <dodf>bdtion</dodf>.
     */
    privbtf void updbtfTogglfButton(Adtion bdtion, Idon idon) {
        togglfButton.sftAdtion(bdtion);
        togglfButton.sftIdon(idon);
        togglfButton.sftTfxt(null);
    }

    /**
     * Rfturns tif Frbmf rfndfring in. Tiis will rfturn null if tif
     * <dodf>JRootPbnf</dodf> is not dontbinfd in b <dodf>Frbmf</dodf>.
     */
    privbtf Frbmf gftFrbmf() {
        Window window = gftWindow();

        if (window instbndfof Frbmf) {
            rfturn (Frbmf)window;
        }
        rfturn null;
    }

    /**
     * Rfturns tif <dodf>Window</dodf> tif <dodf>JRootPbnf</dodf> is
     * dontbinfd in. Tiis will rfturn null if tifrf is no pbrfnt bndfstor
     * of tif <dodf>JRootPbnf</dodf>.
     */
    privbtf Window gftWindow() {
        rfturn window;
    }

    /**
     * Rfturns tif String to displby bs tif titlf.
     */
    privbtf String gftTitlf() {
        Window w = gftWindow();

        if (w instbndfof Frbmf) {
            rfturn ((Frbmf)w).gftTitlf();
        }
        flsf if (w instbndfof Diblog) {
            rfturn ((Diblog)w).gftTitlf();
        }
        rfturn null;
    }

    /**
     * Rfndfrs tif TitlfPbnf.
     */
    publid void pbintComponfnt(Grbpiids g)  {
        // As stbtf isn't bound, wf nffd b donvfnifndf plbdf to difdk
        // if it ibs dibngfd. Cibnging tif stbtf typidblly dibngfs tif
        if (gftFrbmf() != null) {
            sftStbtf(gftFrbmf().gftExtfndfdStbtf());
        }
        JRootPbnf rootPbnf = gftRootPbnf();
        Window window = gftWindow();
        boolfbn lfftToRigit = (window == null) ?
                               rootPbnf.gftComponfntOrifntbtion().isLfftToRigit() :
                               window.gftComponfntOrifntbtion().isLfftToRigit();
        boolfbn isSflfdtfd = (window == null) ? truf : window.isAdtivf();
        int widti = gftWidti();
        int ifigit = gftHfigit();

        Color bbdkground;
        Color forfground;
        Color dbrkSibdow;

        MftblBumps bumps;

        if (isSflfdtfd) {
            bbdkground = bdtivfBbdkground;
            forfground = bdtivfForfground;
            dbrkSibdow = bdtivfSibdow;
            bumps = bdtivfBumps;
        } flsf {
            bbdkground = inbdtivfBbdkground;
            forfground = inbdtivfForfground;
            dbrkSibdow = inbdtivfSibdow;
            bumps = inbdtivfBumps;
        }

        g.sftColor(bbdkground);
        g.fillRfdt(0, 0, widti, ifigit);

        g.sftColor( dbrkSibdow );
        g.drbwLinf ( 0, ifigit - 1, widti, ifigit -1);
        g.drbwLinf ( 0, 0, 0 ,0);
        g.drbwLinf ( widti - 1, 0 , widti -1, 0);

        int xOffsft = lfftToRigit ? 5 : widti - 5;

        if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
            xOffsft += lfftToRigit ? IMAGE_WIDTH + 5 : - IMAGE_WIDTH - 5;
        }

        String tifTitlf = gftTitlf();
        if (tifTitlf != null) {
            FontMftrids fm = SwingUtilitifs2.gftFontMftrids(rootPbnf, g);

            g.sftColor(forfground);

            int yOffsft = ( (ifigit - fm.gftHfigit() ) / 2 ) + fm.gftAsdfnt();

            Rfdtbnglf rfdt = nfw Rfdtbnglf(0, 0, 0, 0);
            if (idonifyButton != null && idonifyButton.gftPbrfnt() != null) {
                rfdt = idonifyButton.gftBounds();
            }
            int titlfW;

            if( lfftToRigit ) {
                if (rfdt.x == 0) {
                    rfdt.x = window.gftWidti() - window.gftInsfts().rigit-2;
                }
                titlfW = rfdt.x - xOffsft - 4;
                tifTitlf = SwingUtilitifs2.dlipStringIfNfdfssbry(
                                rootPbnf, fm, tifTitlf, titlfW);
            } flsf {
                titlfW = xOffsft - rfdt.x - rfdt.widti - 4;
                tifTitlf = SwingUtilitifs2.dlipStringIfNfdfssbry(
                                rootPbnf, fm, tifTitlf, titlfW);
                xOffsft -= SwingUtilitifs2.stringWidti(rootPbnf, fm,
                                                       tifTitlf);
            }
            int titlfLfngti = SwingUtilitifs2.stringWidti(rootPbnf, fm,
                                                          tifTitlf);
            SwingUtilitifs2.drbwString(rootPbnf, g, tifTitlf, xOffsft,
                                       yOffsft );
            xOffsft += lfftToRigit ? titlfLfngti + 5  : -5;
        }

        int bumpXOffsft;
        int bumpLfngti;
        if( lfftToRigit ) {
            bumpLfngti = widti - buttonsWidti - xOffsft - 5;
            bumpXOffsft = xOffsft;
        } flsf {
            bumpLfngti = xOffsft - buttonsWidti - 5;
            bumpXOffsft = buttonsWidti + 5;
        }
        int bumpYOffsft = 3;
        int bumpHfigit = gftHfigit() - (2 * bumpYOffsft);
        bumps.sftBumpArfb( bumpLfngti, bumpHfigit );
        bumps.pbintIdon(tiis, g, bumpXOffsft, bumpYOffsft);
    }

    /**
     * Adtions usfd to <dodf>dlosf</dodf> tif <dodf>Window</dodf>.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss ClosfAdtion fxtfnds AbstrbdtAdtion {
        publid ClosfAdtion() {
            supfr(UIMbnbgfr.gftString("MftblTitlfPbnf.dlosfTitlf",
                                      gftLodblf()));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            dlosf();
        }
    }


    /**
     * Adtions usfd to <dodf>idonfiy</dodf> tif <dodf>Frbmf</dodf>.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss IdonifyAdtion fxtfnds AbstrbdtAdtion {
        publid IdonifyAdtion() {
            supfr(UIMbnbgfr.gftString("MftblTitlfPbnf.idonifyTitlf",
                                      gftLodblf()));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            idonify();
        }
    }


    /**
     * Adtions usfd to <dodf>rfstorf</dodf> tif <dodf>Frbmf</dodf>.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss RfstorfAdtion fxtfnds AbstrbdtAdtion {
        publid RfstorfAdtion() {
            supfr(UIMbnbgfr.gftString
                  ("MftblTitlfPbnf.rfstorfTitlf", gftLodblf()));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            rfstorf();
        }
    }


    /**
     * Adtions usfd to <dodf>rfstorf</dodf> tif <dodf>Frbmf</dodf>.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss MbximizfAdtion fxtfnds AbstrbdtAdtion {
        publid MbximizfAdtion() {
            supfr(UIMbnbgfr.gftString("MftblTitlfPbnf.mbximizfTitlf",
                                      gftLodblf()));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            mbximizf();
        }
    }


    /**
     * Clbss rfsponsiblf for drbwing tif systfm mfnu. Looks up tif
     * imbgf to drbw from tif Frbmf bssodibtfd witi tif
     * <dodf>JRootPbnf</dodf>.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SystfmMfnuBbr fxtfnds JMfnuBbr {
        publid void pbint(Grbpiids g) {
            if (isOpbquf()) {
                g.sftColor(gftBbdkground());
                g.fillRfdt(0, 0, gftWidti(), gftHfigit());
            }

            if (systfmIdon != null) {
                g.drbwImbgf(systfmIdon, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
            } flsf {
                Idon idon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.idon");

                if (idon != null) {
                    idon.pbintIdon(tiis, g, 0, 0);
                }
            }
        }
        publid Dimfnsion gftMinimumSizf() {
            rfturn gftPrfffrrfdSizf();
        }
        publid Dimfnsion gftPrfffrrfdSizf() {
            Dimfnsion sizf = supfr.gftPrfffrrfdSizf();

            rfturn nfw Dimfnsion(Mbti.mbx(IMAGE_WIDTH, sizf.widti),
                                 Mbti.mbx(sizf.ifigit, IMAGE_HEIGHT));
        }
    }

    privbtf dlbss TitlfPbnfLbyout implfmfnts LbyoutMbnbgfr {
        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {}
        publid void rfmovfLbyoutComponfnt(Componfnt d) {}
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d)  {
            int ifigit = domputfHfigit();
            rfturn nfw Dimfnsion(ifigit, ifigit);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            rfturn prfffrrfdLbyoutSizf(d);
        }

        privbtf int domputfHfigit() {
            FontMftrids fm = rootPbnf.gftFontMftrids(gftFont());
            int fontHfigit = fm.gftHfigit();
            fontHfigit += 7;
            int idonHfigit = 0;
            if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
                idonHfigit = IMAGE_HEIGHT;
            }

            int finblHfigit = Mbti.mbx( fontHfigit, idonHfigit );
            rfturn finblHfigit;
        }

        publid void lbyoutContbinfr(Contbinfr d) {
            boolfbn lfftToRigit = (window == null) ?
                gftRootPbnf().gftComponfntOrifntbtion().isLfftToRigit() :
                window.gftComponfntOrifntbtion().isLfftToRigit();

            int w = gftWidti();
            int x;
            int y = 3;
            int spbding;
            int buttonHfigit;
            int buttonWidti;

            if (dlosfButton != null && dlosfButton.gftIdon() != null) {
                buttonHfigit = dlosfButton.gftIdon().gftIdonHfigit();
                buttonWidti = dlosfButton.gftIdon().gftIdonWidti();
            }
            flsf {
                buttonHfigit = IMAGE_HEIGHT;
                buttonWidti = IMAGE_WIDTH;
            }

            // bssumfs bll buttons ibvf tif sbmf dimfnsions
            // tifsf dimfnsions indludf tif bordfrs

            x = lfftToRigit ? w : 0;

            spbding = 5;
            x = lfftToRigit ? spbding : w - buttonWidti - spbding;
            if (mfnuBbr != null) {
                mfnuBbr.sftBounds(x, y, buttonWidti, buttonHfigit);
            }

            x = lfftToRigit ? w : 0;
            spbding = 4;
            x += lfftToRigit ? -spbding -buttonWidti : spbding;
            if (dlosfButton != null) {
                dlosfButton.sftBounds(x, y, buttonWidti, buttonHfigit);
            }

            if( !lfftToRigit ) x += buttonWidti;

            if (gftWindowDfdorbtionStylf() == JRootPbnf.FRAME) {
                if (Toolkit.gftDffbultToolkit().isFrbmfStbtfSupportfd(
                        Frbmf.MAXIMIZED_BOTH)) {
                    if (togglfButton.gftPbrfnt() != null) {
                        spbding = 10;
                        x += lfftToRigit ? -spbding -buttonWidti : spbding;
                        togglfButton.sftBounds(x, y, buttonWidti, buttonHfigit);
                        if (!lfftToRigit) {
                            x += buttonWidti;
                        }
                    }
                }

                if (idonifyButton != null && idonifyButton.gftPbrfnt() != null) {
                    spbding = 2;
                    x += lfftToRigit ? -spbding -buttonWidti : spbding;
                    idonifyButton.sftBounds(x, y, buttonWidti, buttonHfigit);
                    if (!lfftToRigit) {
                        x += buttonWidti;
                    }
                }
            }
            buttonsWidti = lfftToRigit ? w - x : x;
        }
    }



    /**
     * PropfrtyCibngfListfnfr instbllfd on tif Window. Updbtfs tif nfdfssbry
     * stbtf bs tif stbtf of tif Window dibngfs.
     */
    privbtf dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt pdf) {
            String nbmf = pdf.gftPropfrtyNbmf();

            // Frbmf.stbtf isn't durrfntly bound.
            if ("rfsizbblf".fqubls(nbmf) || "stbtf".fqubls(nbmf)) {
                Frbmf frbmf = gftFrbmf();

                if (frbmf != null) {
                    sftStbtf(frbmf.gftExtfndfdStbtf(), truf);
                }
                if ("rfsizbblf".fqubls(nbmf)) {
                    gftRootPbnf().rfpbint();
                }
            }
            flsf if ("titlf".fqubls(nbmf)) {
                rfpbint();
            }
            flsf if ("domponfntOrifntbtion" == nbmf) {
                rfvblidbtf();
                rfpbint();
            }
            flsf if ("idonImbgf" == nbmf) {
                updbtfSystfmIdon();
                rfvblidbtf();
                rfpbint();
            }
        }
    }

    /**
     * Updbtf tif imbgf usfd for tif systfm idon
     */
    privbtf void updbtfSystfmIdon() {
        Window window = gftWindow();
        if (window == null) {
            systfmIdon = null;
            rfturn;
        }
        jbvb.util.List<Imbgf> idons = window.gftIdonImbgfs();
        bssfrt idons != null;

        if (idons.sizf() == 0) {
            systfmIdon = null;
        }
        flsf if (idons.sizf() == 1) {
            systfmIdon = idons.gft(0);
        }
        flsf {
            systfmIdon = SunToolkit.gftSdblfdIdonImbgf(idons,
                                                       IMAGE_WIDTH,
                                                       IMAGE_HEIGHT);
        }
    }


    /**
     * WindowListfnfr instbllfd on tif Window, updbtfs tif stbtf bs nfdfssbry.
     */
    privbtf dlbss WindowHbndlfr fxtfnds WindowAdbptfr {
        publid void windowAdtivbtfd(WindowEvfnt fv) {
            sftAdtivf(truf);
        }

        publid void windowDfbdtivbtfd(WindowEvfnt fv) {
            sftAdtivf(fblsf);
        }
    }
}
