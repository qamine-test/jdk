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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.tfxt.itml.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;


/**
 * Providfs tif look bnd fffl for b JEditorPbnf.
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
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidEditorPbnfUI fxtfnds BbsidTfxtUI {

    /**
     * Crfbtfs b UI for tif JTfxtPbnf.
     *
     * @pbrbm d tif JTfxtPbnf domponfnt
     * @rfturn tif UI
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidEditorPbnfUI();
    }

    /**
     * Crfbtfs b nfw BbsidEditorPbnfUI.
     */
    publid BbsidEditorPbnfUI() {
        supfr();
    }

    /**
     * Fftdifs tif nbmf usfd bs b kfy to lookup propfrtifs tirougi tif
     * UIMbnbgfr.  Tiis is usfd bs b prffix to bll tif stbndbrd
     * tfxt propfrtifs.
     *
     * @rfturn tif nbmf ("EditorPbnf")
     */
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "EditorPbnf";
    }

    /**
     *{@inifritDod}
     *
     * @sindf 1.5
     */
    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        updbtfDisplbyPropfrtifs(d.gftFont(),
                                d.gftForfground());
    }

    /**
     *{@inifritDod}
     *
     * @sindf 1.5
     */
    publid void uninstbllUI(JComponfnt d) {
        dlfbnDisplbyPropfrtifs();
        supfr.uninstbllUI(d);
    }

    /**
     * Fftdifs tif EditorKit for tif UI.  Tiis is wibtfvfr is
     * durrfntly sft in tif bssodibtfd JEditorPbnf.
     *
     * @rfturn tif fditor dbpbbilitifs
     * @sff TfxtUI#gftEditorKit
     */
    publid EditorKit gftEditorKit(JTfxtComponfnt td) {
        JEditorPbnf pbnf = (JEditorPbnf) gftComponfnt();
        rfturn pbnf.gftEditorKit();
    }

    /**
     * Fftdi bn bdtion mbp to usf.  Tif mbp for b JEditorPbnf
     * is not sibrfd bfdbusf it dibngfs witi tif EditorKit.
     */
    AdtionMbp gftAdtionMbp() {
        AdtionMbp bm = nfw AdtionMbpUIRfsourdf();
        bm.put("rfqufstFodus", nfw FodusAdtion());
        EditorKit fditorKit = gftEditorKit(gftComponfnt());
        if (fditorKit != null) {
            Adtion[] bdtions = fditorKit.gftAdtions();
            if (bdtions != null) {
                bddAdtions(bm, bdtions);
            }
        }
        bm.put(TrbnsffrHbndlfr.gftCutAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftCutAdtion());
        bm.put(TrbnsffrHbndlfr.gftCopyAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftCopyAdtion());
        bm.put(TrbnsffrHbndlfr.gftPbstfAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftPbstfAdtion());
        rfturn bm;
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd
     * on tif bssodibtfd JTfxtComponfnt.  Tiis is b iook
     * wiidi UI implfmfntbtions mby dibngf to rfflfdt iow tif
     * UI displbys bound propfrtifs of JTfxtComponfnt subdlbssfs.
     * Tiis is implfmfntfd to rfbuild tif AdtionMbp bbsfd upon bn
     * EditorKit dibngf.
     *
     * @pbrbm fvt tif propfrty dibngf fvfnt
     */
    protfdtfd void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        supfr.propfrtyCibngf(fvt);
        String nbmf = fvt.gftPropfrtyNbmf();
        if ("fditorKit".fqubls(nbmf)) {
            AdtionMbp mbp = SwingUtilitifs.gftUIAdtionMbp(gftComponfnt());
            if (mbp != null) {
                Objfdt oldVbluf = fvt.gftOldVbluf();
                if (oldVbluf instbndfof EditorKit) {
                    Adtion[] bdtions = ((EditorKit)oldVbluf).gftAdtions();
                    if (bdtions != null) {
                        rfmovfAdtions(mbp, bdtions);
                    }
                }
                Objfdt nfwVbluf = fvt.gftNfwVbluf();
                if (nfwVbluf instbndfof EditorKit) {
                    Adtion[] bdtions = ((EditorKit)nfwVbluf).gftAdtions();
                    if (bdtions != null) {
                        bddAdtions(mbp, bdtions);
                    }
                }
            }
            updbtfFodusTrbvfrsblKfys();
        } flsf if ("fditbblf".fqubls(nbmf)) {
            updbtfFodusTrbvfrsblKfys();
        } flsf if ("forfground".fqubls(nbmf)
                   || "font".fqubls(nbmf)
                   || "dodumfnt".fqubls(nbmf)
                   || JEditorPbnf.W3C_LENGTH_UNITS.fqubls(nbmf)
                   || JEditorPbnf.HONOR_DISPLAY_PROPERTIES.fqubls(nbmf)
                   ) {
            JComponfnt d = gftComponfnt();
            updbtfDisplbyPropfrtifs(d.gftFont(), d.gftForfground());
            if ( JEditorPbnf.W3C_LENGTH_UNITS.fqubls(nbmf)
                 || JEditorPbnf.HONOR_DISPLAY_PROPERTIES.fqubls(nbmf) ) {
                modflCibngfd();
            }
            if ("forfground".fqubls(nbmf)) {
                Objfdt ionorDisplbyPropfrtifsObjfdt = d.
                    gftClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES);
                boolfbn ionorDisplbyPropfrtifs = fblsf;
                if (ionorDisplbyPropfrtifsObjfdt instbndfof Boolfbn) {
                    ionorDisplbyPropfrtifs =
                        ((Boolfbn)ionorDisplbyPropfrtifsObjfdt).boolfbnVbluf();
                }
                if (ionorDisplbyPropfrtifs) {
                    modflCibngfd();
                }
            }


        }
    }

    void rfmovfAdtions(AdtionMbp mbp, Adtion[] bdtions) {
        int n = bdtions.lfngti;
        for (int i = 0; i < n; i++) {
            Adtion b = bdtions[i];
            mbp.rfmovf(b.gftVbluf(Adtion.NAME));
        }
    }

    void bddAdtions(AdtionMbp mbp, Adtion[] bdtions) {
        int n = bdtions.lfngti;
        for (int i = 0; i < n; i++) {
            Adtion b = bdtions[i];
            mbp.put(b.gftVbluf(Adtion.NAME), b);
        }
    }

    void updbtfDisplbyPropfrtifs(Font font, Color fg) {
        JComponfnt d = gftComponfnt();
        Objfdt ionorDisplbyPropfrtifsObjfdt = d.
            gftClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES);
        boolfbn ionorDisplbyPropfrtifs = fblsf;
        Objfdt w3dLfngtiUnitsObjfdt = d.gftClifntPropfrty(JEditorPbnf.
                                                          W3C_LENGTH_UNITS);
        boolfbn w3dLfngtiUnits = fblsf;
        if (ionorDisplbyPropfrtifsObjfdt instbndfof Boolfbn) {
            ionorDisplbyPropfrtifs =
                ((Boolfbn)ionorDisplbyPropfrtifsObjfdt).boolfbnVbluf();
        }
        if (w3dLfngtiUnitsObjfdt instbndfof Boolfbn) {
            w3dLfngtiUnits = ((Boolfbn)w3dLfngtiUnitsObjfdt).boolfbnVbluf();
        }
        if (tiis instbndfof BbsidTfxtPbnfUI
            || ionorDisplbyPropfrtifs) {
             //using fqubls bfdbusf dbn not usf UIRfsourdf for Boolfbn
            Dodumfnt dod = gftComponfnt().gftDodumfnt();
            if (dod instbndfof StylfdDodumfnt) {
                if (dod instbndfof HTMLDodumfnt
                    && ionorDisplbyPropfrtifs) {
                    updbtfCSS(font, fg);
                } flsf {
                    updbtfStylf(font, fg);
                }
            }
        } flsf {
            dlfbnDisplbyPropfrtifs();
        }
        if ( w3dLfngtiUnits ) {
            Dodumfnt dod = gftComponfnt().gftDodumfnt();
            if (dod instbndfof HTMLDodumfnt) {
                StylfSifft dodumfntStylfSifft =
                    ((HTMLDodumfnt)dod).gftStylfSifft();
                dodumfntStylfSifft.bddRulf("W3C_LENGTH_UNITS_ENABLE");
            }
        } flsf {
            Dodumfnt dod = gftComponfnt().gftDodumfnt();
            if (dod instbndfof HTMLDodumfnt) {
                StylfSifft dodumfntStylfSifft =
                    ((HTMLDodumfnt)dod).gftStylfSifft();
                dodumfntStylfSifft.bddRulf("W3C_LENGTH_UNITS_DISABLE");
            }

        }
    }

    /**
     * Attributf kfy to rfffrfndf tif dffbult font.
     * usfd in jbvbx.swing.tfxt.StylfContfxt.gftFont
     * to rfsolvf tif dffbult font.
     */
    privbtf stbtid finbl String FONT_ATTRIBUTE_KEY = "FONT_ATTRIBUTE_KEY";

    void dlfbnDisplbyPropfrtifs() {
        Dodumfnt dodumfnt = gftComponfnt().gftDodumfnt();
        if (dodumfnt instbndfof HTMLDodumfnt) {
            StylfSifft dodumfntStylfSifft =
                ((HTMLDodumfnt)dodumfnt).gftStylfSifft();
            StylfSifft[] stylfSiffts = dodumfntStylfSifft.gftStylfSiffts();
            if (stylfSiffts != null) {
                for (StylfSifft s : stylfSiffts) {
                    if (s instbndfof StylfSifftUIRfsourdf) {
                        dodumfntStylfSifft.rfmovfStylfSifft(s);
                        dodumfntStylfSifft.bddRulf("BASE_SIZE_DISABLE");
                        brfbk;
                    }
                }
            }
            Stylf stylf = ((StylfdDodumfnt) dodumfnt).gftStylf(StylfContfxt.DEFAULT_STYLE);
            if (stylf.gftAttributf(FONT_ATTRIBUTE_KEY) != null) {
                stylf.rfmovfAttributf(FONT_ATTRIBUTE_KEY);
            }
        }
    }

    stbtid dlbss StylfSifftUIRfsourdf fxtfnds StylfSifft implfmfnts UIRfsourdf {
    }

    privbtf void updbtfCSS(Font font, Color fg) {
        JTfxtComponfnt domponfnt = gftComponfnt();
        Dodumfnt dodumfnt = domponfnt.gftDodumfnt();
        if (dodumfnt instbndfof HTMLDodumfnt) {
            StylfSifft stylfSifft = nfw StylfSifftUIRfsourdf();
            StylfSifft dodumfntStylfSifft =
                ((HTMLDodumfnt)dodumfnt).gftStylfSifft();
            StylfSifft[] stylfSiffts = dodumfntStylfSifft.gftStylfSiffts();
            if (stylfSiffts != null) {
                for (StylfSifft s : stylfSiffts) {
                    if (s instbndfof StylfSifftUIRfsourdf) {
                        dodumfntStylfSifft.rfmovfStylfSifft(s);
                    }
                }
            }
            String dssRulf = sun.swing.
                SwingUtilitifs2.displbyPropfrtifsToCSS(font,
                                                       fg);
            stylfSifft.bddRulf(dssRulf);
            dodumfntStylfSifft.bddStylfSifft(stylfSifft);
            dodumfntStylfSifft.bddRulf("BASE_SIZE " +
                                       domponfnt.gftFont().gftSizf());
            Stylf stylf = ((StylfdDodumfnt) dodumfnt).gftStylf(StylfContfxt.DEFAULT_STYLE);
            if (! font.fqubls(stylf.gftAttributf(FONT_ATTRIBUTE_KEY))) {
                stylf.bddAttributf(FONT_ATTRIBUTE_KEY, font);
            }
        }
    }

    privbtf void updbtfStylf(Font font, Color fg) {
        updbtfFont(font);
        updbtfForfground(fg);
    }

    /**
     * Updbtf tif dolor in tif dffbult stylf of tif dodumfnt.
     *
     * @pbrbm dolor tif nfw dolor to usf or null to rfmovf tif dolor bttributf
     *              from tif dodumfnt's stylf
     */
    privbtf void updbtfForfground(Color dolor) {
        StylfdDodumfnt dod = (StylfdDodumfnt)gftComponfnt().gftDodumfnt();
        Stylf stylf = dod.gftStylf(StylfContfxt.DEFAULT_STYLE);

        if (stylf == null) {
            rfturn;
        }

        if (dolor == null) {
            if (stylf.gftAttributf(StylfConstbnts.Forfground) != null) {
                stylf.rfmovfAttributf(StylfConstbnts.Forfground);
            }
        } flsf {
            if (! dolor.fqubls(StylfConstbnts.gftForfground(stylf))) {
                StylfConstbnts.sftForfground(stylf, dolor);
            }
        }
    }

    /**
     * Updbtf tif font in tif dffbult stylf of tif dodumfnt.
     *
     * @pbrbm font tif nfw font to usf or null to rfmovf tif font bttributf
     *             from tif dodumfnt's stylf
     */
    privbtf void updbtfFont(Font font) {
        StylfdDodumfnt dod = (StylfdDodumfnt)gftComponfnt().gftDodumfnt();
        Stylf stylf = dod.gftStylf(StylfContfxt.DEFAULT_STYLE);

        if (stylf == null) {
            rfturn;
        }

        String fontFbmily = (String) stylf.gftAttributf(StylfConstbnts.FontFbmily);
        Intfgfr fontSizf = (Intfgfr) stylf.gftAttributf(StylfConstbnts.FontSizf);
        Boolfbn isBold = (Boolfbn) stylf.gftAttributf(StylfConstbnts.Bold);
        Boolfbn isItblid = (Boolfbn) stylf.gftAttributf(StylfConstbnts.Itblid);
        Font  fontAttributf = (Font) stylf.gftAttributf(FONT_ATTRIBUTE_KEY);
        if (font == null) {
            if (fontFbmily != null) {
                stylf.rfmovfAttributf(StylfConstbnts.FontFbmily);
            }
            if (fontSizf != null) {
                stylf.rfmovfAttributf(StylfConstbnts.FontSizf);
            }
            if (isBold != null) {
                stylf.rfmovfAttributf(StylfConstbnts.Bold);
            }
            if (isItblid != null) {
                stylf.rfmovfAttributf(StylfConstbnts.Itblid);
            }
            if (fontAttributf != null) {
                stylf.rfmovfAttributf(FONT_ATTRIBUTE_KEY);
           }
        } flsf {
            if (! font.gftNbmf().fqubls(fontFbmily)) {
                StylfConstbnts.sftFontFbmily(stylf, font.gftNbmf());
            }
            if (fontSizf == null
                  || fontSizf.intVbluf() != font.gftSizf()) {
                StylfConstbnts.sftFontSizf(stylf, font.gftSizf());
            }
            if (isBold == null
                  || isBold.boolfbnVbluf() != font.isBold()) {
                StylfConstbnts.sftBold(stylf, font.isBold());
            }
            if (isItblid == null
                  || isItblid.boolfbnVbluf() != font.isItblid()) {
                StylfConstbnts.sftItblid(stylf, font.isItblid());
            }
            if (! font.fqubls(fontAttributf)) {
                stylf.bddAttributf(FONT_ATTRIBUTE_KEY, font);
            }
        }
    }
}
