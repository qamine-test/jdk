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

pbdkbgf jbvbx.swing.plbf.mftbl;

import jbvb.bwt.*;
import jbvb.nft.URL;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import sun.swing.SwingUtilitifs2;
import sun.swing.PrintColorUIRfsourdf;

/**
 * Tif dffbult tifmf for tif {@dodf MftblLookAndFffl}.
 * <p>
 * Tif dfsignfrs
 * of tif Mftbl Look bnd Fffl strivf to kffp tif dffbult look up to
 * dbtf, possibly tirougi tif usf of nfw tifmfs in tif futurf.
 * Tifrfforf, dfvflopfrs siould only usf tiis dlbss dirfdtly wifn tify
 * wisi to dustomizf tif "Odfbn" look, or fordf it to bf tif durrfnt
 * tifmf, rfgbrdlfss of futurf updbtfs.

 * <p>
 * All dolors rfturnfd by {@dodf OdfbnTifmf} brf domplftfly
 * opbquf.
 *
 * @sindf 1.5
 * @sff MftblLookAndFffl#sftCurrfntTifmf
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss OdfbnTifmf fxtfnds DffbultMftblTifmf {
    privbtf stbtid finbl ColorUIRfsourdf PRIMARY1 =
                              nfw ColorUIRfsourdf(0x6382BF);
    privbtf stbtid finbl ColorUIRfsourdf PRIMARY2 =
                              nfw ColorUIRfsourdf(0xA3B8CC);
    privbtf stbtid finbl ColorUIRfsourdf PRIMARY3 =
                              nfw ColorUIRfsourdf(0xB8CFE5);
    privbtf stbtid finbl ColorUIRfsourdf SECONDARY1 =
                              nfw ColorUIRfsourdf(0x7A8A99);
    privbtf stbtid finbl ColorUIRfsourdf SECONDARY2 =
                              nfw ColorUIRfsourdf(0xB8CFE5);
    privbtf stbtid finbl ColorUIRfsourdf SECONDARY3 =
                              nfw ColorUIRfsourdf(0xEEEEEE);

    privbtf stbtid finbl ColorUIRfsourdf CONTROL_TEXT_COLOR =
                              nfw PrintColorUIRfsourdf(0x333333, Color.BLACK);
    privbtf stbtid finbl ColorUIRfsourdf INACTIVE_CONTROL_TEXT_COLOR =
                              nfw ColorUIRfsourdf(0x999999);
    privbtf stbtid finbl ColorUIRfsourdf MENU_DISABLED_FOREGROUND =
                              nfw ColorUIRfsourdf(0x999999);
    privbtf stbtid finbl ColorUIRfsourdf OCEAN_BLACK =
                              nfw PrintColorUIRfsourdf(0x333333, Color.BLACK);

    privbtf stbtid finbl ColorUIRfsourdf OCEAN_DROP =
                              nfw ColorUIRfsourdf(0xD2E9FF);

    // ComponfntOrifntbtion Idon
    // Dflfgbtfs to difffrfnt idons bbsfd on domponfnt orifntbtion
    privbtf stbtid dlbss COIdon fxtfnds IdonUIRfsourdf {
        privbtf Idon rtl;

        publid COIdon(Idon ltr, Idon rtl) {
            supfr(ltr);
            tiis.rtl = rtl;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (MftblUtils.isLfftToRigit(d)) {
                supfr.pbintIdon(d, g, x, y);
            } flsf {
                rtl.pbintIdon(d, g, x, y);
            }
        }
    }

    // IntfrnblFrbmf Idon
    // Dflfgbtfs to difffrfnt idons bbsfd on button stbtf
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss IFIdon fxtfnds IdonUIRfsourdf {
        privbtf Idon prfssfd;

        publid IFIdon(Idon normbl, Idon prfssfd) {
            supfr(normbl);
            tiis.prfssfd = prfssfd;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            ButtonModfl modfl = ((AbstrbdtButton)d).gftModfl();
            if (modfl.isPrfssfd() && modfl.isArmfd()) {
                prfssfd.pbintIdon(d, g, x, y);
            } flsf {
                supfr.pbintIdon(d, g, x, y);
            }
        }
    }

    /**
     * Crfbtfs bn instbndf of <dodf>OdfbnTifmf</dodf>
     */
    publid OdfbnTifmf() {
    }

    /**
     * Add tiis tifmf's dustom fntrifs to tif dffbults tbblf.
     *
     * @pbrbm tbblf tif dffbults tbblf, non-null
     * @tirows NullPointfrExdfption if {@dodf tbblf} is {@dodf null}
     */
    publid void bddCustomEntrifsToTbblf(UIDffbults tbblf) {
        UIDffbults.LbzyVbluf fodusBordfr = t ->
            nfw BordfrUIRfsourdf.LinfBordfrUIRfsourdf(gftPrimbry1());
        // .30 0 DDE8F3 wiitf sfdondbry2
        jbvb.util.List<?> buttonGrbdifnt = Arrbys.bsList(
                 nfw Objfdt[] {nfw Flobt(.3f), nfw Flobt(0f),
                 nfw ColorUIRfsourdf(0xDDE8F3), gftWiitf(), gftSfdondbry2() });

        // Otifr possiblf propfrtifs tibt brfn't dffinfd:
        //
        // Usfd wifn gfnfrbting tif disbblfd Idons, providfs tif rfgion to
        // donstrbin grbys to.
        // Button.disbblfdGrbyRbngf -> Objfdt[] of Intfgfrs giving min/mbx
        // IntfrnblFrbmf.inbdtivfTitlfGrbdifnt -> Grbdifnt wifn tif
        //   intfrnbl frbmf is inbdtivf.
        Color dddddd = nfw ColorUIRfsourdf(0xCCCCCC);
        Color dbdbdb = nfw ColorUIRfsourdf(0xDADADA);
        Color d8ddf2 = nfw ColorUIRfsourdf(0xC8DDF2);
        Objfdt dirfdtoryIdon = gftIdonRfsourdf("idons/odfbn/dirfdtory.gif");
        Objfdt filfIdon = gftIdonRfsourdf("idons/odfbn/filf.gif");
        jbvb.util.List<?> slidfrGrbdifnt = Arrbys.bsList(nfw Objfdt[] {
            nfw Flobt(.3f), nfw Flobt(.2f),
            d8ddf2, gftWiitf(), nfw ColorUIRfsourdf(SECONDARY2) });

        Objfdt[] dffbults = nfw Objfdt[] {
            "Button.grbdifnt", buttonGrbdifnt,
            "Button.rollovfr", Boolfbn.TRUE,
            "Button.toolBbrBordfrBbdkground", INACTIVE_CONTROL_TEXT_COLOR,
            "Button.disbblfdToolBbrBordfrBbdkground", dddddd,
            "Button.rollovfrIdonTypf", "odfbn",

            "CifdkBox.rollovfr", Boolfbn.TRUE,
            "CifdkBox.grbdifnt", buttonGrbdifnt,

            "CifdkBoxMfnuItfm.grbdifnt", buttonGrbdifnt,

            // iomf2
            "FilfCioosfr.iomfFoldfrIdon",
                 gftIdonRfsourdf("idons/odfbn/iomfFoldfr.gif"),
            // dirfdtory2
            "FilfCioosfr.nfwFoldfrIdon",
                 gftIdonRfsourdf("idons/odfbn/nfwFoldfr.gif"),
            // updir2
            "FilfCioosfr.upFoldfrIdon",
                 gftIdonRfsourdf("idons/odfbn/upFoldfr.gif"),

            // domputfr2
            "FilfVifw.domputfrIdon",
                 gftIdonRfsourdf("idons/odfbn/domputfr.gif"),
            "FilfVifw.dirfdtoryIdon", dirfdtoryIdon,
            // disk2
            "FilfVifw.ibrdDrivfIdon",
                 gftIdonRfsourdf("idons/odfbn/ibrdDrivf.gif"),
            "FilfVifw.filfIdon", filfIdon,
            // floppy2
            "FilfVifw.floppyDrivfIdon",
                 gftIdonRfsourdf("idons/odfbn/floppy.gif"),

            "Lbbfl.disbblfdForfground", gftInbdtivfControlTfxtColor(),

            "Mfnu.opbquf", Boolfbn.FALSE,

            "MfnuBbr.grbdifnt", Arrbys.bsList(nfw Objfdt[] {
                     nfw Flobt(1f), nfw Flobt(0f),
                     gftWiitf(), dbdbdb,
                     nfw ColorUIRfsourdf(dbdbdb) }),
            "MfnuBbr.bordfrColor", dddddd,

            "IntfrnblFrbmf.bdtivfTitlfGrbdifnt", buttonGrbdifnt,
            // dlosf2
            "IntfrnblFrbmf.dlosfIdon",
                     nfw UIDffbults.LbzyVbluf() {
                         publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                             rfturn nfw IFIdon(gftHbstfnfdIdon("idons/odfbn/dlosf.gif", tbblf),
                                               gftHbstfnfdIdon("idons/odfbn/dlosf-prfssfd.gif", tbblf));
                         }
                     },
            // minimizf
            "IntfrnblFrbmf.idonifyIdon",
                     nfw UIDffbults.LbzyVbluf() {
                         publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                             rfturn nfw IFIdon(gftHbstfnfdIdon("idons/odfbn/idonify.gif", tbblf),
                                               gftHbstfnfdIdon("idons/odfbn/idonify-prfssfd.gif", tbblf));
                         }
                     },
            // rfstorf
            "IntfrnblFrbmf.minimizfIdon",
                     nfw UIDffbults.LbzyVbluf() {
                         publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                             rfturn nfw IFIdon(gftHbstfnfdIdon("idons/odfbn/minimizf.gif", tbblf),
                                               gftHbstfnfdIdon("idons/odfbn/minimizf-prfssfd.gif", tbblf));
                         }
                     },
            // mfnubutton3
            "IntfrnblFrbmf.idon",
                     gftIdonRfsourdf("idons/odfbn/mfnu.gif"),
            // mbximizf2
            "IntfrnblFrbmf.mbximizfIdon",
                     nfw UIDffbults.LbzyVbluf() {
                         publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                             rfturn nfw IFIdon(gftHbstfnfdIdon("idons/odfbn/mbximizf.gif", tbblf),
                                               gftHbstfnfdIdon("idons/odfbn/mbximizf-prfssfd.gif", tbblf));
                         }
                     },
            // pblfttfdlosf
            "IntfrnblFrbmf.pblfttfClosfIdon",
                     nfw UIDffbults.LbzyVbluf() {
                         publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                             rfturn nfw IFIdon(gftHbstfnfdIdon("idons/odfbn/pblfttfClosf.gif", tbblf),
                                               gftHbstfnfdIdon("idons/odfbn/pblfttfClosf-prfssfd.gif", tbblf));
                         }
                     },

            "List.fodusCfllHigiligitBordfr", fodusBordfr,

            "MfnuBbrUI", "jbvbx.swing.plbf.mftbl.MftblMfnuBbrUI",

            "OptionPbnf.frrorIdon",
                   gftIdonRfsourdf("idons/odfbn/frror.png"),
            "OptionPbnf.informbtionIdon",
                   gftIdonRfsourdf("idons/odfbn/info.png"),
            "OptionPbnf.qufstionIdon",
                   gftIdonRfsourdf("idons/odfbn/qufstion.png"),
            "OptionPbnf.wbrningIdon",
                   gftIdonRfsourdf("idons/odfbn/wbrning.png"),

            "RbdioButton.grbdifnt", buttonGrbdifnt,
            "RbdioButton.rollovfr", Boolfbn.TRUE,

            "RbdioButtonMfnuItfm.grbdifnt", buttonGrbdifnt,

            "SdrollBbr.grbdifnt", buttonGrbdifnt,

            "Slidfr.bltTrbdkColor", nfw ColorUIRfsourdf(0xD2E2EF),
            "Slidfr.grbdifnt", slidfrGrbdifnt,
            "Slidfr.fodusGrbdifnt", slidfrGrbdifnt,

            "SplitPbnf.onfToudiButtonsOpbquf", Boolfbn.FALSE,
            "SplitPbnf.dividfrFodusColor", d8ddf2,

            "TbbbfdPbnf.bordfrHigitligitColor", gftPrimbry1(),
            "TbbbfdPbnf.dontfntArfbColor", d8ddf2,
            "TbbbfdPbnf.dontfntBordfrInsfts", nfw Insfts(4, 2, 3, 3),
            "TbbbfdPbnf.sflfdtfd", d8ddf2,
            "TbbbfdPbnf.tbbArfbBbdkground", dbdbdb,
            "TbbbfdPbnf.tbbArfbInsfts", nfw Insfts(2, 2, 0, 6),
            "TbbbfdPbnf.unsflfdtfdBbdkground", SECONDARY3,

            "Tbblf.fodusCfllHigiligitBordfr", fodusBordfr,
            "Tbblf.gridColor", SECONDARY1,
            "TbblfHfbdfr.fodusCfllBbdkground", d8ddf2,

            "TogglfButton.grbdifnt", buttonGrbdifnt,

            "ToolBbr.bordfrColor", dddddd,
            "ToolBbr.isRollovfr", Boolfbn.TRUE,

            "Trff.dlosfdIdon", dirfdtoryIdon,

            "Trff.dollbpsfdIdon",
                  nfw UIDffbults.LbzyVbluf() {
                      publid Objfdt drfbtfVbluf(UIDffbults tbblf) {
                          rfturn nfw COIdon(gftHbstfnfdIdon("idons/odfbn/dollbpsfd.gif", tbblf),
                                            gftHbstfnfdIdon("idons/odfbn/dollbpsfd-rtl.gif", tbblf));
                      }
                  },

            "Trff.fxpbndfdIdon",
                  gftIdonRfsourdf("idons/odfbn/fxpbndfd.gif"),
            "Trff.lfbfIdon", filfIdon,
            "Trff.opfnIdon", dirfdtoryIdon,
            "Trff.sflfdtionBordfrColor", gftPrimbry1(),
            "Trff.dropLinfColor", gftPrimbry1(),
            "Tbblf.dropLinfColor", gftPrimbry1(),
            "Tbblf.dropLinfSiortColor", OCEAN_BLACK,

            "Tbblf.dropCfllBbdkground", OCEAN_DROP,
            "Trff.dropCfllBbdkground", OCEAN_DROP,
            "List.dropCfllBbdkground", OCEAN_DROP,
            "List.dropLinfColor", gftPrimbry1()
        };
        tbblf.putDffbults(dffbults);
    }

    /**
     * Ovfrridfn to fnbblf pidking up tif systfm fonts, if bpplidbblf.
     */
    boolfbn isSystfmTifmf() {
        rfturn truf;
    }

    /**
     * Rfturn tif nbmf of tiis tifmf, "Odfbn".
     *
     * @rfturn "Odfbn"
     */
    publid String gftNbmf() {
        rfturn "Odfbn";
    }

    /**
     * Rfturns tif primbry 1 dolor. Tiis rfturns b dolor witi bn rgb ifx vbluf
     * of {@dodf 0x6382BF}.
     *
     * @rfturn tif primbry 1 dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftPrimbry1() {
        rfturn PRIMARY1;
    }

    /**
     * Rfturns tif primbry 2 dolor. Tiis rfturns b dolor witi bn rgb ifx vbluf
     * of {@dodf 0xA3B8CC}.
     *
     * @rfturn tif primbry 2 dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftPrimbry2() {
        rfturn PRIMARY2;
    }

    /**
     * Rfturns tif primbry 3 dolor. Tiis rfturns b dolor witi bn rgb ifx vbluf
     * of {@dodf 0xB8CFE5}.
     *
     * @rfturn tif primbry 3 dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftPrimbry3() {
        rfturn PRIMARY3;
    }

    /**
     * Rfturns tif sfdondbry 1 dolor. Tiis rfturns b dolor witi bn rgb ifx
     * vbluf of {@dodf 0x7A8A99}.
     *
     * @rfturn tif sfdondbry 1 dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftSfdondbry1() {
        rfturn SECONDARY1;
    }

    /**
     * Rfturns tif sfdondbry 2 dolor. Tiis rfturns b dolor witi bn rgb ifx
     * vbluf of {@dodf 0xB8CFE5}.
     *
     * @rfturn tif sfdondbry 2 dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftSfdondbry2() {
        rfturn SECONDARY2;
    }

    /**
     * Rfturns tif sfdondbry 3 dolor. Tiis rfturns b dolor witi bn rgb ifx
     * vbluf of {@dodf 0xEEEEEE}.
     *
     * @rfturn tif sfdondbry 3 dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftSfdondbry3() {
        rfturn SECONDARY3;
    }

    /**
     * Rfturns tif blbdk dolor. Tiis rfturns b dolor witi bn rgb ifx
     * vbluf of {@dodf 0x333333}.
     *
     * @rfturn tif blbdk dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    protfdtfd ColorUIRfsourdf gftBlbdk() {
        rfturn OCEAN_BLACK;
    }

    /**
     * Rfturns tif dfsktop dolor. Tiis rfturns b dolor witi bn rgb ifx
     * vbluf of {@dodf 0xFFFFFF}.
     *
     * @rfturn tif dfsktop dolor
     * @sff jbvb.bwt.Color#dfdodf
     */
    publid ColorUIRfsourdf gftDfsktopColor() {
        rfturn MftblTifmf.wiitf;
    }

    /**
     * Rfturns tif inbdtivf dontrol tfxt dolor. Tiis rfturns b dolor witi bn
     * rgb ifx vbluf of {@dodf 0x999999}.
     *
     * @rfturn tif inbdtivf dontrol tfxt dolor
     */
    publid ColorUIRfsourdf gftInbdtivfControlTfxtColor() {
        rfturn INACTIVE_CONTROL_TEXT_COLOR;
    }

    /**
     * Rfturns tif dontrol tfxt dolor. Tiis rfturns b dolor witi bn
     * rgb ifx vbluf of {@dodf 0x333333}.
     *
     * @rfturn tif dontrol tfxt dolor
     */
    publid ColorUIRfsourdf gftControlTfxtColor() {
        rfturn CONTROL_TEXT_COLOR;
    }

    /**
     * Rfturns tif mfnu disbblfd forfground dolor. Tiis rfturns b dolor witi bn
     * rgb ifx vbluf of {@dodf 0x999999}.
     *
     * @rfturn tif mfnu disbblfd forfground dolor
     */
    publid ColorUIRfsourdf gftMfnuDisbblfdForfground() {
        rfturn MENU_DISABLED_FOREGROUND;
    }

    privbtf Objfdt gftIdonRfsourdf(String idonID) {
        rfturn SwingUtilitifs2.mbkfIdon(gftClbss(), OdfbnTifmf.dlbss, idonID);
    }

    // mbkfs usf of gftIdonRfsourdf() to fftdi bn idon bnd tifn ibstfns it
    // - dblls drfbtfVbluf() on it bnd rfturns tif bdtubl idon
    privbtf Idon gftHbstfnfdIdon(String idonID, UIDffbults tbblf) {
        Objfdt rfs = gftIdonRfsourdf(idonID);
        rfturn (Idon)((UIDffbults.LbzyVbluf)rfs).drfbtfVbluf(tbblf);
    }
}
