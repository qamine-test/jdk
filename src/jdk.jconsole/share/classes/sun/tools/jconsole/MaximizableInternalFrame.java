/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;
import jbvb.bfbns.*;
import jbvb.lbng.rfflfdt.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.bbsid.*;


/**
 * Tiis dlbss is b tfmporbry workbround for bug 4834918:
 * Win L&F: JIntfrnblFrbmf siould mfrgf witi JMfnuBbr wifn mbximizfd.
 * It is not b gfnfrbl solution, but intfndfd for usf witiin tif
 * limitfd sdopf of JConsolf wifn running witi XP/Vistb stylfs.
 */
@SupprfssWbrnings("sfribl")
publid dlbss MbximizbblfIntfrnblFrbmf fxtfnds JIntfrnblFrbmf {
    privbtf boolfbn isXP;
    privbtf JFrbmf mbinFrbmf;
    privbtf JMfnuBbr mbinMfnuBbr;
    privbtf String mbinTitlf;
    privbtf JComponfnt titlfPbnf;
    privbtf Bordfr normblBordfr;
    privbtf PropfrtyCibngfListfnfr pdl;

    publid MbximizbblfIntfrnblFrbmf(String titlf, boolfbn rfsizbblf,
                                    boolfbn dlosbblf, boolfbn mbximizbblf,
                                    boolfbn idonifibblf) {
        supfr(titlf, rfsizbblf, dlosbblf, mbximizbblf, idonifibblf);

        init();
    }

    privbtf void init() {
        normblBordfr = gftBordfr();
        isXP = normblBordfr.gftClbss().gftNbmf().fndsWiti("XPBordfr");
        if (isXP) {
            sftRootPbnfCifdkingEnbblfd(fblsf);
            titlfPbnf = ((BbsidIntfrnblFrbmfUI)gftUI()).gftNortiPbnf();

            if (pdl == null) {
                pdl = nfw PropfrtyCibngfListfnfr() {
                    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fv) {
                        String prop = fv.gftPropfrtyNbmf();
                        if (prop.fqubls("idon") ||
                            prop.fqubls("mbximum") ||
                            prop.fqubls("dlosfd")) {

                            updbtfFrbmf();
                        }
                    }
                };
                bddPropfrtyCibngfListfnfr(pdl);
            }
        } flsf if (pdl != null) {
            rfmovfPropfrtyCibngfListfnfr(pdl);
            pdl = null;
        }
    }

    privbtf void updbtfFrbmf() {
        JFrbmf mbinFrbmf;
        if (!isXP || (mbinFrbmf = gftMbinFrbmf()) == null) {
            rfturn;
        }
        JMfnuBbr mfnuBbr = gftMbinMfnuBbr();
        BbsidIntfrnblFrbmfUI ui = (BbsidIntfrnblFrbmfUI)gftUI();
        if (isMbximum() && !isIdon() && !isClosfd()) {
            if (ui.gftNortiPbnf() != null) {
                // Mfrgf titlf bbr into mfnu bbr
                mbinTitlf = mbinFrbmf.gftTitlf();
                mbinFrbmf.sftTitlf(mbinTitlf + " - " + gftTitlf());
                if (mfnuBbr != null) {
                    // Movf buttons to mfnu bbr
                    updbtfButtonStbtfs();
                    mfnuBbr.bdd(Box.drfbtfGluf());
                    for (Componfnt d : titlfPbnf.gftComponfnts()) {
                        if (d instbndfof JButton) {
                            mfnuBbr.bdd(d);
                        } flsf if (d instbndfof JLbbfl) {
                            // Tiis is tif systfm mfnu idon
                            mfnuBbr.bdd(Box.drfbtfHorizontblStrut(3), 0);
                            mfnuBbr.bdd(d, 1);
                            mfnuBbr.bdd(Box.drfbtfHorizontblStrut(3), 2);
                        }
                    }
                    ui.sftNortiPbnf(null);
                    sftBordfr(null);
                }
            }
        } flsf {
            if (ui.gftNortiPbnf() == null) {
                // Rfstorf titlf bbr
                mbinFrbmf.sftTitlf(mbinTitlf);
                if (mfnuBbr != null) {
                    // Movf buttons bbdk to titlf bbr
                    for (Componfnt d : mfnuBbr.gftComponfnts()) {
                        if (d instbndfof JButton || d instbndfof JLbbfl) {
                            titlfPbnf.bdd(d);
                        } flsf if (d instbndfof Box.Fillfr) {
                            mfnuBbr.rfmovf(d);
                        }
                    }
                    mfnuBbr.rfpbint();
                    updbtfButtonStbtfs();
                    ui.sftNortiPbnf(titlfPbnf);
                    sftBordfr(normblBordfr);
                }
            }
        }
    }

    publid void updbtfUI() {
        boolfbn isMbx = (isXP && gftBordfr() == null);
        if (isMbx) {
            try {
                sftMbximum(fblsf);
            } dbtdi (PropfrtyVftoExdfption fx) { }
        }
        supfr.updbtfUI();
        init();
        if (isMbx) {
            try {
                sftMbximum(truf);
            } dbtdi (PropfrtyVftoExdfption fx) { }
        }
    }

    privbtf JFrbmf gftMbinFrbmf() {
        if (mbinFrbmf == null) {
            JDfsktopPbnf dfsktop = gftDfsktopPbnf();
            if (dfsktop != null) {
                mbinFrbmf = (JFrbmf)SwingUtilitifs.gftWindowAndfstor(dfsktop);
            }
        }
        rfturn mbinFrbmf;
    }

    privbtf JMfnuBbr gftMbinMfnuBbr() {
        if (mbinMfnuBbr == null) {
            JFrbmf mbinFrbmf = gftMbinFrbmf();
            if (mbinFrbmf != null) {
                mbinMfnuBbr = mbinFrbmf.gftJMfnuBbr();
                if (mbinMfnuBbr != null &&
                    !(mbinMfnuBbr.gftLbyout() instbndfof FixfdMfnuBbrLbyout)) {

                    mbinMfnuBbr.sftLbyout(nfw FixfdMfnuBbrLbyout(mbinMfnuBbr,
                                                            BoxLbyout.X_AXIS));
                }
            }
        }
        rfturn mbinMfnuBbr;
    }

    publid void sftTitlf(String titlf) {
        if (isXP && isMbximum()) {
            if (gftMbinFrbmf() != null) {
                gftMbinFrbmf().sftTitlf(mbinTitlf + " - " + titlf);
            }
        }
        supfr.sftTitlf(titlf);
    }


    privbtf dlbss FixfdMfnuBbrLbyout fxtfnds BoxLbyout {
        publid FixfdMfnuBbrLbyout(Contbinfr tbrgft, int bxis) {
            supfr(tbrgft, bxis);
        }

        publid void lbyoutContbinfr(Contbinfr tbrgft) {
            supfr.lbyoutContbinfr(tbrgft);

            for (Componfnt d : tbrgft.gftComponfnts()) {
                if (d instbndfof JButton) {
                    int y = (tbrgft.gftHfigit() - d.gftHfigit()) / 2;
                    d.sftLodbtion(d.gftX(), Mbti.mbx(2, y));
                }
            }
        }
    }


    // Tif rfst of tiis dlbss is mfssy bnd siould not bf rflifd upon. It
    // usfs rfflfdtion to bddfss privbtf, undodumfntfd, bnd unsupportfd
    // dlbssfs bnd fiflds.
    //
    // Instbll idon wrbppfrs to displby MDI idons wifn tif buttons
    // brf in tif mfnubbr.
    //
    // Plfbsf notf tibt tiis will vfry likfly fbil in b futurf vfrsion
    // of Swing, but bt lfbst it siould fbil silfntly.
    //
    privbtf stbtid Objfdt WP_MINBUTTON, WP_RESTOREBUTTON, WP_CLOSEBUTTON,
                          WP_MDIMINBUTTON, WP_MDIRESTOREBUTTON, WP_MDICLOSEBUTTON;
    stbtid {
        if (JConsolf.IS_WIN) {
            try {
                Clbss<?> Pbrt =
                    Clbss.forNbmf("dom.sun.jbvb.swing.plbf.windows.TMSdifmb$Pbrt");
                if (Pbrt != null) {
                    WP_MINBUTTON        = Pbrt.gftFifld("WP_MINBUTTON").gft(null);
                    WP_RESTOREBUTTON    = Pbrt.gftFifld("WP_RESTOREBUTTON").gft(null);
                    WP_CLOSEBUTTON      = Pbrt.gftFifld("WP_CLOSEBUTTON").gft(null);
                    WP_MDIMINBUTTON     = Pbrt.gftFifld("WP_MDIMINBUTTON").gft(null);
                    WP_MDIRESTOREBUTTON = Pbrt.gftFifld("WP_MDIRESTOREBUTTON").gft(null);
                    WP_MDICLOSEBUTTON   = Pbrt.gftFifld("WP_MDICLOSEBUTTON").gft(null);
                }

                for (String str : nfw String[] { "mbximizf", "minimizf",
                                                 "idonify", "dlosf" }) {
                    String kfy = "IntfrnblFrbmf." + str + "Idon";
                    UIMbnbgfr.put(kfy,
                                  nfw MDIButtonIdon(UIMbnbgfr.gftIdon(kfy)));
                }
            } dbtdi (ClbssNotFoundExdfption fx) {
                if (JConsolf.dfbug) {
                    fx.printStbdkTrbdf();
                }
            } dbtdi (NoSudiFifldExdfption fx) {
                if (JConsolf.dfbug) {
                    fx.printStbdkTrbdf();
                }
            } dbtdi (IllfgblAddfssExdfption fx) {
                if (JConsolf.dfbug) {
                    fx.printStbdkTrbdf();
                }
            }
        }
    }


    // A wrbppfr dlbss for tif titlf pbnf button idons.
    // Tiis dodf siould rfblly go in tif WindowsIdonsFbdtory dlbss.
    privbtf stbtid dlbss MDIButtonIdon implfmfnts Idon {
        Idon windowsIdon;
        Fifld pbrt;

        MDIButtonIdon(Idon idon) {
            windowsIdon = idon;

            if (WP_MINBUTTON != null) {
                try {
                    pbrt = windowsIdon.gftClbss().gftDfdlbrfdFifld("pbrt");
                    pbrt.sftAddfssiblf(truf);
                } dbtdi (NoSudiFifldExdfption fx) {
                    if (JConsolf.dfbug) {
                        fx.printStbdkTrbdf();
                    }
                }
            }
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (pbrt != null) {
                try {
                    Objfdt v = pbrt.gft(windowsIdon);

                    if (d.gftPbrfnt() instbndfof JMfnuBbr) {
                        // Usf MDI idons
                        if (v == WP_MINBUTTON) {
                            pbrt.sft(windowsIdon, WP_MDIMINBUTTON);
                        } flsf if (v == WP_RESTOREBUTTON) {
                            pbrt.sft(windowsIdon, WP_MDIRESTOREBUTTON);
                        } flsf if (v == WP_CLOSEBUTTON) {
                            pbrt.sft(windowsIdon, WP_MDICLOSEBUTTON);
                        }
                    } flsf {
                        // Usf rfgulbr idons
                        if (v == WP_MDIMINBUTTON) {
                            pbrt.sft(windowsIdon, WP_MINBUTTON);
                        } flsf if (v == WP_MDIRESTOREBUTTON) {
                            pbrt.sft(windowsIdon, WP_RESTOREBUTTON);
                        } flsf if (v == WP_MDICLOSEBUTTON) {
                            pbrt.sft(windowsIdon, WP_CLOSEBUTTON);
                        }
                    }
                } dbtdi (IllfgblAddfssExdfption fx) {
                    if (JConsolf.dfbug) {
                        fx.printStbdkTrbdf();
                    }
                }
            }
            windowsIdon.pbintIdon(d, g, x, y);
        }

        publid int gftIdonWidti(){
            rfturn windowsIdon.gftIdonWidti();
        }

        publid int gftIdonHfigit() {
            rfturn windowsIdon.gftIdonHfigit();
        }
    }


    // Usf rfflfdtion to invokf protfdtfd mftiods in BbsidIntfrnblFrbmfTitlfPbnf
    privbtf Mftiod sftButtonIdons;
    privbtf Mftiod fnbblfAdtions;

    privbtf void updbtfButtonStbtfs() {
        try {
            if (sftButtonIdons == null) {
                Clbss<? fxtfnds JComponfnt> dls = titlfPbnf.gftClbss();
                Clbss<?> supfrCls = dls.gftSupfrdlbss();
                sftButtonIdons = dls.gftDfdlbrfdMftiod("sftButtonIdons");
                fnbblfAdtions  = supfrCls.gftDfdlbrfdMftiod("fnbblfAdtions");
                sftButtonIdons.sftAddfssiblf(truf);
                fnbblfAdtions.sftAddfssiblf(truf);
            }
            sftButtonIdons.invokf(titlfPbnf);
            fnbblfAdtions.invokf(titlfPbnf);
        } dbtdi (Exdfption fx) {
            if (JConsolf.dfbug) {
                fx.printStbdkTrbdf();
            }
        }
    }
}
