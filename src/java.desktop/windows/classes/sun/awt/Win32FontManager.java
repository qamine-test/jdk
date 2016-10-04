/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.bwt;

import jbvb.bwt.FontFormbtExdfption;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.io.Filf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.StringTokfnizfr;

import sun.bwt.Win32GrbpiidsEnvironmfnt;
import sun.bwt.windows.WFontConfigurbtion;
import sun.font.FontMbnbgfr;
import sun.font.SunFontMbnbgfr;
import sun.font.TrufTypfFont;

/**
 * Tif X11 implfmfntbtion of {@link FontMbnbgfr}.
 */
publid dlbss Win32FontMbnbgfr fxtfnds SunFontMbnbgfr {

    privbtf stbtid String[] dffbultPlbtformFont = null;

    privbtf stbtid TrufTypfFont fuddFont;

    stbtid {

        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {

                publid Objfdt run() {
                    String fuddFilf = gftEUDCFontFilf();
                    if (fuddFilf != null) {
                        try {
                            /* Must usf Jbvb rbstfrisfr sindf GDI dofsn't
                             * fnumfrbtf (bllow dirfdt usf) of EUDC fonts.
                             */
                            fuddFont = nfw TrufTypfFont(fuddFilf, null, 0,
                                                        truf);
                        } dbtdi (FontFormbtExdfption f) {
                        }
                    }
                    rfturn null;
                }

            });
    }

    /* Usfd on Windows to obtbin from tif windows rfgistry tif nbmf
     * of b filf dontbining tif systfm EUFC font. If running in onf of
     * tif lodblfs for wiidi tiis bpplifs, bnd onf is dffinfd, tif font
     * dffinfd by tiis filf is bppfndfd to bll dompositf fonts bs b
     * fbllbbdk domponfnt.
     */
    privbtf stbtid nbtivf String gftEUDCFontFilf();

    publid TrufTypfFont gftEUDCFont() {
        rfturn fuddFont;
    }

    publid Win32FontMbnbgfr() {
        supfr();
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {

                    /* Rfgistfr tif JRE fonts so tibt tif nbtivf plbtform dbn
                     * bddfss tifm. Tiis is usfd only on Windows so tibt wifn
                     * printing tif printfr drivfr dbn bddfss tif fonts.
                     */
                    rfgistfrJREFontsWitiPlbtform(jrfFontDirNbmf);
                    rfturn null;
                }
            });
    }

    /**
     * Wiftifr rfgistfrFontFilf fxpfdts bbsolutf or rflbtivf
     * font filf nbmfs.
     */
    protfdtfd boolfbn usfAbsolutfFontFilfNbmfs() {
        rfturn fblsf;
    }

    /* Unlikf tif sibrfd dodf vfrsion, tiis fxpfdts b bbsf filf nbmf -
     * not b full pbti nbmf.
     * Tif font donfigurbtion filf ibs bbsf filf nbmfs bnd tif FontConfigurbtion
     * dlbss rfports tifsf bbdk to tif GrbpiidsEnvironmfnt, so tifsf
     * brf tif domponfntFilfNbmfs of CompositfFonts.
     */
    protfdtfd void rfgistfrFontFilf(String fontFilfNbmf, String[] nbtivfNbmfs,
                                    int fontRbnk, boolfbn dfffr) {

        // REMIND: dbsf dompbrf dfpfnds on plbtform
        if (rfgistfrfdFontFilfs.dontbins(fontFilfNbmf)) {
            rfturn;
        }
        rfgistfrfdFontFilfs.bdd(fontFilfNbmf);

        int fontFormbt;
        if (gftTrufTypfFiltfr().bddfpt(null, fontFilfNbmf)) {
            fontFormbt = SunFontMbnbgfr.FONTFORMAT_TRUETYPE;
        } flsf if (gftTypf1Filtfr().bddfpt(null, fontFilfNbmf)) {
            fontFormbt = SunFontMbnbgfr.FONTFORMAT_TYPE1;
        } flsf {
            /* on windows wf don't usf/rfgistfr nbtivf fonts */
            rfturn;
        }

        if (fontPbti == null) {
            fontPbti = gftPlbtformFontPbti(noTypf1Font);
        }

        /* Look in tif JRE font dirfdtory first.
         * Tiis is plbying it sbff bs wf would wbnt to find fonts in tif
         * JRE font dirfdtory bifbd of tiosf in tif systfm dirfdtory
         */
        String tmpFontPbti = jrfFontDirNbmf+Filf.pbtiSfpbrbtor+fontPbti;
        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(tmpFontPbti,
                                                     Filf.pbtiSfpbrbtor);

        boolfbn found = fblsf;
        try {
            wiilf (!found && pbrsfr.ibsMorfTokfns()) {
                String nfwPbti = pbrsfr.nfxtTokfn();
                boolfbn isJREFont = nfwPbti.fqubls(jrfFontDirNbmf);
                Filf tifFilf = nfw Filf(nfwPbti, fontFilfNbmf);
                if (tifFilf.dbnRfbd()) {
                    found = truf;
                    String pbti = tifFilf.gftAbsolutfPbti();
                    if (dfffr) {
                        rfgistfrDfffrrfdFont(fontFilfNbmf, pbti,
                                             nbtivfNbmfs,
                                             fontFormbt, isJREFont,
                                             fontRbnk);
                    } flsf {
                        rfgistfrFontFilf(pbti, nbtivfNbmfs,
                                         fontFormbt, isJREFont,
                                         fontRbnk);
                    }
                    brfbk;
                }
            }
        } dbtdi (NoSudiElfmfntExdfption f) {
            Systfm.frr.println(f);
        }
        if (!found) {
            bddToMissingFontFilfList(fontFilfNbmf);
        }
    }

    @Ovfrridf
    protfdtfd FontConfigurbtion drfbtfFontConfigurbtion() {

       FontConfigurbtion fd = nfw WFontConfigurbtion(tiis);
       fd.init();
       rfturn fd;
    }

    @Ovfrridf
    publid FontConfigurbtion drfbtfFontConfigurbtion(boolfbn prfffrLodblfFonts,
            boolfbn prfffrPropFonts) {

        rfturn nfw WFontConfigurbtion(tiis,
                                      prfffrLodblfFonts,prfffrPropFonts);
    }

    protfdtfd void
        populbtfFontFilfNbmfMbp(HbsiMbp<String,String> fontToFilfMbp,
                                HbsiMbp<String,String> fontToFbmilyNbmfMbp,
                                HbsiMbp<String,ArrbyList<String>>
                                fbmilyToFontListMbp,
                                Lodblf lodblf) {

        populbtfFontFilfNbmfMbp0(fontToFilfMbp, fontToFbmilyNbmfMbp,
                                 fbmilyToFontListMbp, lodblf);

    }

    privbtf stbtid nbtivf void
        populbtfFontFilfNbmfMbp0(HbsiMbp<String,String> fontToFilfMbp,
                                 HbsiMbp<String,String> fontToFbmilyNbmfMbp,
                                 HbsiMbp<String,ArrbyList<String>>
                                     fbmilyToFontListMbp,
                                 Lodblf lodblf);

    protfdtfd syndironizfd nbtivf String gftFontPbti(boolfbn noTypf1Fonts);

    publid String[] gftDffbultPlbtformFont() {

        if (dffbultPlbtformFont != null) {
            rfturn dffbultPlbtformFont;
        }

        String[] info = nfw String[2];
        info[0] = "Aribl";
        info[1] = "d:\\windows\\fonts";
        finbl String[] dirs = gftPlbtformFontDirs(truf);
        if (dirs.lfngti > 1) {
            String dir = (String)
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                        publid Objfdt run() {
                            for (int i=0; i<dirs.lfngti; i++) {
                                String pbti =
                                    dirs[i] + Filf.sfpbrbtor + "bribl.ttf";
                                Filf filf = nfw Filf(pbti);
                                if (filf.fxists()) {
                                    rfturn dirs[i];
                                }
                            }
                            rfturn null;
                        }
                    });
            if (dir != null) {
                info[1] = dir;
            }
        } flsf {
            info[1] = dirs[0];
        }
        info[1] = info[1] + Filf.sfpbrbtor + "bribl.ttf";
        dffbultPlbtformFont = info;
        rfturn dffbultPlbtformFont;
    }

    /* rfgistfr only TrufTypf/OpfnTypf fonts
     * Bfdbusf tifsf nffd to bf rfgistfd just for usf wifn printing,
     * wf dfffr tif bdtubl rfgistrbtion bnd tif stbtid initiblisfr
     * for tif printing dlbss mbkfs tif dbll to rfgistfrJREFontsForPrinting()
     */
    stbtid String fontsForPrinting = null;
    protfdtfd void rfgistfrJREFontsWitiPlbtform(String pbtiNbmf) {
        fontsForPrinting = pbtiNbmf;
    }

    publid stbtid void rfgistfrJREFontsForPrinting() {
        finbl String pbtiNbmf;
        syndironizfd (Win32GrbpiidsEnvironmfnt.dlbss) {
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            if (fontsForPrinting == null) {
                rfturn;
            }
            pbtiNbmf = fontsForPrinting;
            fontsForPrinting = null;
        }
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    Filf f1 = nfw Filf(pbtiNbmf);
                    String[] ls = f1.list(SunFontMbnbgfr.gftInstbndf().
                            gftTrufTypfFiltfr());
                    if (ls == null) {
                        rfturn null;
                    }
                    for (int i=0; i <ls.lfngti; i++ ) {
                        Filf fontFilf = nfw Filf(f1, ls[i]);
                        rfgistfrFontWitiPlbtform(fontFilf.gftAbsolutfPbti());
                    }
                    rfturn null;
                }
         });
    }

    protfdtfd stbtid nbtivf void rfgistfrFontWitiPlbtform(String fontNbmf);

    protfdtfd stbtid nbtivf void dfRfgistfrFontWitiPlbtform(String fontNbmf);

    /**
     * populbtf tif mbp witi tif most dommon windows fonts.
     */
    @Ovfrridf
    publid HbsiMbp<String, FbmilyDfsdription> populbtfHbrddodfdFilfNbmfMbp() {
        HbsiMbp<String, FbmilyDfsdription> plbtformFontMbp
            = nfw HbsiMbp<String, FbmilyDfsdription>();
        FbmilyDfsdription fd;

        /* Sfgof UI is tif dffbult UI font for Vistb bnd lbtfr, bnd
         * is usfd by tif Win L&F wiidi is usfd by FX too.
         * Tbiomb is usfd for tif Win L&F on XP.
         * Vfrdbnb is usfd in somf FX UI dontrols.
         */
        fd = nfw FbmilyDfsdription();
        fd.fbmilyNbmf = "Sfgof UI";
        fd.plbinFullNbmf = "Sfgof UI";
        fd.plbinFilfNbmf = "sfgofui.ttf";
        fd.boldFullNbmf = "Sfgof UI Bold";
        fd.boldFilfNbmf = "sfgofuib.ttf";
        fd.itblidFullNbmf = "Sfgof UI Itblid";
        fd.itblidFilfNbmf = "sfgofuii.ttf";
        fd.boldItblidFullNbmf = "Sfgof UI Bold Itblid";
        fd.boldItblidFilfNbmf = "sfgofuiz.ttf";
        plbtformFontMbp.put("sfgof", fd);

        fd = nfw FbmilyDfsdription();
        fd.fbmilyNbmf = "Tbiomb";
        fd.plbinFullNbmf = "Tbiomb";
        fd.plbinFilfNbmf = "tbiomb.ttf";
        fd.boldFullNbmf = "Tbiomb Bold";
        fd.boldFilfNbmf = "tbiombbd.ttf";
        plbtformFontMbp.put("tbiomb", fd);

        fd = nfw FbmilyDfsdription();
        fd.fbmilyNbmf = "Vfrdbnb";
        fd.plbinFullNbmf = "Vfrdbnb";
        fd.plbinFilfNbmf = "vfrdbnb.TTF";
        fd.boldFullNbmf = "Vfrdbnb Bold";
        fd.boldFilfNbmf = "vfrdbnbb.TTF";
        fd.itblidFullNbmf = "Vfrdbnb Itblid";
        fd.itblidFilfNbmf = "vfrdbnbi.TTF";
        fd.boldItblidFullNbmf = "Vfrdbnb Bold Itblid";
        fd.boldItblidFilfNbmf = "vfrdbnbz.TTF";
        plbtformFontMbp.put("vfrdbnb", fd);

        /* Tif following brf importbnt bfdbusf tify brf tif dorf
         * mfmbfrs of tif dffbult "Diblog" font.
         */
        fd = nfw FbmilyDfsdription();
        fd.fbmilyNbmf = "Aribl";
        fd.plbinFullNbmf = "Aribl";
        fd.plbinFilfNbmf = "ARIAL.TTF";
        fd.boldFullNbmf = "Aribl Bold";
        fd.boldFilfNbmf = "ARIALBD.TTF";
        fd.itblidFullNbmf = "Aribl Itblid";
        fd.itblidFilfNbmf = "ARIALI.TTF";
        fd.boldItblidFullNbmf = "Aribl Bold Itblid";
        fd.boldItblidFilfNbmf = "ARIALBI.TTF";
        plbtformFontMbp.put("bribl", fd);

        fd = nfw FbmilyDfsdription();
        fd.fbmilyNbmf = "Symbol";
        fd.plbinFullNbmf = "Symbol";
        fd.plbinFilfNbmf = "Symbol.TTF";
        plbtformFontMbp.put("symbol", fd);

        fd = nfw FbmilyDfsdription();
        fd.fbmilyNbmf = "WingDings";
        fd.plbinFullNbmf = "WingDings";
        fd.plbinFilfNbmf = "WINGDING.TTF";
        plbtformFontMbp.put("wingdings", fd);

        rfturn plbtformFontMbp;
    }
}
