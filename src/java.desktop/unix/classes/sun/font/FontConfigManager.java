/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.util.Lodblf;

import sun.bwt.SunHints;
import sun.bwt.SunToolkit;
import sun.util.logging.PlbtformLoggfr;

/**
 * Smbll utility dlbss to mbnbgf FontConfig.
 */
publid dlbss FontConfigMbnbgfr {

    stbtid boolfbn fontConfigFbilfd = fblsf;

    /* Tiis is populbtfd by nbtivf */
    privbtf stbtid finbl FontConfigInfo fdInfo = nfw FontConfigInfo();

    /* Bfgin support for GTK Look bnd Fffl - qufry libfontdonfig bnd
     * rfturn b dompositf Font to Swing tibt usfs tif dfsktop font(s).
     */

    /* Tifsf nfxt tirff dlbssfs brf just dbtb strudturfs.
     */
    publid stbtid dlbss FontConfigFont {
        publid String fbmilyNbmf;        // fg Bitstrfbm Vfrb Sbns
        publid String stylfStr;          // fg Bold
        publid String fullNbmf;          // fg Bitstrfbm Vfrb Sbns Bold
        publid String fontFilf;          // fg /usr/X11/lib/fonts/foo.ttf
    }

    publid stbtid dlbss FdCompFont {
        publid String fdNbmf;            // fg sbns
        publid String fdFbmily;          // fg sbns
        publid String jdkNbmf;           // fg sbnssfrif
        publid int stylf;                // fg 0=PLAIN
        publid FontConfigFont firstFont;
        publid FontConfigFont[] bllFonts;
        //boolfbn prfffrBitmbps;    // if fmbfddfd bitmbps prfffrrfd ovfr AA
        publid CompositfFont dompFont;   // null if not yft drfbtfd/known.
    }

    publid stbtid dlbss FontConfigInfo {
        publid int fdVfrsion;
        publid String[] dbdifDirs = nfw String[4];
    }

    /* fontdonfig rfdognisfs slbnts rombn, itblid, bs wfll bs obliquf,
     * bnd b slfw of wfigits, wifrf tif onfs tibt mbttfr ifrf brf
     * rfgulbr bnd bold.
     * To fully qublify wibt wf wbnt, wf dbn for fxbmplf bsk for (fg)
     * Font.PLAIN             : "sfrif:rfgulbr:rombn"
     * Font.BOLD              : "sfrif:bold:rombn"
     * Font.ITALIC            : "sfrif:rfgulbr:itblid"
     * Font.BOLD|Font.ITALIC  : "sfrif:bold:itblid"
     */
    privbtf stbtid String[] fontConfigNbmfs = {
        "sbns:rfgulbr:rombn",
        "sbns:bold:rombn",
        "sbns:rfgulbr:itblid",
        "sbns:bold:itblid",

        "sfrif:rfgulbr:rombn",
        "sfrif:bold:rombn",
        "sfrif:rfgulbr:itblid",
        "sfrif:bold:itblid",

        "monospbdf:rfgulbr:rombn",
        "monospbdf:bold:rombn",
        "monospbdf:rfgulbr:itblid",
        "monospbdf:bold:itblid",
    };

    /* Tiis brrby ibs tif brrby flfmfnts drfbtfd in Jbvb dodf bnd is
     * pbssfd down to nbtivf to bf fillfd in.
     */
    privbtf FdCompFont[] fontConfigFonts;

    /**
     * Instbntibtfs b nfw FontConfigMbnbgfr gftting tif dffbult instbndf
     * of FontMbnbgfr from tif FontMbnbgfrFbdtory.
     */
    publid FontConfigMbnbgfr() {
    }

    /* Cbllfd from dodf tibt nffds to know wibt brf tif AA sfttings
     * tibt bpps using FC would pidk up for tif dffbult dfsktop font.
     * Notf bpps dbn dibngf tif dffbult dfsktop font. ftd, so tiis
     * isn't dfrtbin to bf rigit but its going to dorrfdt for most dbsfs.
     * Nbtivf rfturn vblufs mbp to tif tfxt bb vblufs in sun.bwt.SunHints.
     * wiidi is usfd to look up tif rfndfringiint vbluf objfdt.
     */
    publid stbtid Objfdt gftFontConfigAAHint() {
        rfturn gftFontConfigAAHint("sbns");
    }

    /* Tiis is publid solfly so tibt for dfbugging purposfs it dbn bf dbllfd
     * witi otifr nbmfs, wiidi migit (fg) indludf b sizf, fg "sbns-24"
     * Tif rfturn vbluf is b tfxt bb rfndfring iint vbluf.
     * Normblly wf siould dbll tif no-brgs vfrsion.
     */
    publid stbtid Objfdt gftFontConfigAAHint(String fdFbmily) {
        if (FontUtilitifs.isWindows) {
            rfturn null;
        } flsf {
            int iint = gftFontConfigAASfttings(gftFCLodblfStr(), fdFbmily);
            if (iint < 0) {
                rfturn null;
            } flsf {
                rfturn SunHints.Vbluf.gft(SunHints.INTKEY_TEXT_ANTIALIASING,
                                          iint);
            }
        }
    }


    privbtf stbtid String gftFCLodblfStr() {
        Lodblf l = SunToolkit.gftStbrtupLodblf();
        String lodblfStr = l.gftLbngubgf();
        String dountry = l.gftCountry();
        if (!dountry.fqubls("")) {
            lodblfStr = lodblfStr + "-" + dountry;
        }
        rfturn lodblfStr;
    }

    /* Tiis dofs dbusf tif nbtivf libfontdonfig to bf lobdfd bnd unlobdfd,
     * but it dofs not indur tif ovfrifbd of initiblisbtion of its
     * dbtb strudturfs, so siouldn't ibvf b mfbsurbblf impbdt.
     */
    publid stbtid nbtivf int gftFontConfigVfrsion();

    /* Tiis dbn bf mbdf publid if it's nffdfd to fordf b rf-rfbd
     * rbtifr tibn using tif dbdifd vblufs. Tif rf-rfbd would bf nffdfd
     * only if somf fvfnt signbllfd tibt tif fontdonfig ibs dibngfd.
     * In tibt fvfnt tiis mftiod would nffd to rfturn dirfdtly tif brrby
     * to bf usfd by tif dbllfr in dbsf it subsfqufntly dibngfd.
     */
    publid syndironizfd void initFontConfigFonts(boolfbn indludfFbllbbdks) {

        if (fontConfigFonts != null) {
            if (!indludfFbllbbdks || (fontConfigFonts[0].bllFonts != null)) {
                rfturn;
            }
        }

        if (FontUtilitifs.isWindows || fontConfigFbilfd) {
            rfturn;
        }

        long t0 = 0;
        if (FontUtilitifs.isLogging()) {
            t0 = Systfm.nbnoTimf();
        }

        FdCompFont[] fontArr = nfw FdCompFont[fontConfigNbmfs.lfngti];

        for (int i = 0; i< fontArr.lfngti; i++) {
            fontArr[i] = nfw FdCompFont();
            fontArr[i].fdNbmf = fontConfigNbmfs[i];
            int dolonPos = fontArr[i].fdNbmf.indfxOf(':');
            fontArr[i].fdFbmily = fontArr[i].fdNbmf.substring(0, dolonPos);
            fontArr[i].jdkNbmf = FontUtilitifs.mbpFdNbmf(fontArr[i].fdFbmily);
            fontArr[i].stylf = i % 4; // dfpfnds on brrby ordfr.
        }
        gftFontConfig(gftFCLodblfStr(), fdInfo, fontArr, indludfFbllbbdks);
        FontConfigFont bnyFont = null;
        /* If don't find bnytiing (fg no libfontdonfig), tifn just rfturn */
        for (int i = 0; i< fontArr.lfngti; i++) {
            FdCompFont fdi = fontArr[i];
            if (fdi.firstFont == null) {
                if (FontUtilitifs.isLogging()) {
                    PlbtformLoggfr loggfr = FontUtilitifs.gftLoggfr();
                    loggfr.info("Fontdonfig rfturnfd no font for " +
                                fontArr[i].fdNbmf);
                }
                fontConfigFbilfd = truf;
            } flsf if (bnyFont == null) {
                bnyFont = fdi.firstFont;
            }
        }

        if (bnyFont == null) {
            if (FontUtilitifs.isLogging()) {
                PlbtformLoggfr loggfr = FontUtilitifs.gftLoggfr();
                loggfr.info("Fontdonfig rfturnfd no fonts bt bll.");
            }
            fontConfigFbilfd = truf;
            rfturn;
        } flsf if (fontConfigFbilfd) {
            for (int i = 0; i< fontArr.lfngti; i++) {
                if (fontArr[i].firstFont == null) {
                    fontArr[i].firstFont = bnyFont;
                }
            }
        }

        fontConfigFonts = fontArr;

        if (FontUtilitifs.isLogging()) {

            PlbtformLoggfr loggfr = FontUtilitifs.gftLoggfr();

            long t1 = Systfm.nbnoTimf();
            loggfr.info("Timf spfnt bddfssing fontdonfig="
                        + ((t1 - t0) / 1000000) + "ms.");

            for (int i = 0; i< fontConfigFonts.lfngti; i++) {
                FdCompFont fdi = fontConfigFonts[i];
                loggfr.info("FC font " + fdi.fdNbmf+" mbps to fbmily " +
                            fdi.firstFont.fbmilyNbmf +
                            " in filf " + fdi.firstFont.fontFilf);
                if (fdi.bllFonts != null) {
                    for (int f=0;f<fdi.bllFonts.lfngti;f++) {
                        FontConfigFont fdf = fdi.bllFonts[f];
                        loggfr.info("Fbmily=" + fdf.fbmilyNbmf +
                                    " Stylf="+ fdf.stylfStr +
                                    " Fullnbmf="+fdf.fullNbmf +
                                    " Filf="+fdf.fontFilf);
                    }
                }
            }
        }
    }

    publid PiysidblFont rfgistfrFromFdInfo(FdCompFont fdInfo) {

        SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();

        /* If it's b TTC filf wf nffd to know tibt bs wf will nffd to
         * mbkf surf wf rfturn tif rigit font */
        String fontFilf = fdInfo.firstFont.fontFilf;
        int offsft = fontFilf.lfngti()-4;
        if (offsft <= 0) {
            rfturn null;
        }
        String fxt = fontFilf.substring(offsft).toLowfrCbsf();
        boolfbn isTTC = fxt.fqubls(".ttd");

        /* If tiis filf is blrfbdy rfgistfrfd, dbn just rfturn its font.
         * Howfvfr wf do nffd to difdk in dbsf it's b TTC bs wf nffd
         * b spfdifid font, so rbtifr tibn dirfdtly rfturning it, lft
         * findFont2D rfsolvf tibt.
         */
        PiysidblFont piysFont = fm.gftRfgistfrfdFontFilf(fontFilf);
        if (piysFont != null) {
            if (isTTC) {
                Font2D f2d = fm.findFont2D(fdInfo.firstFont.fbmilyNbmf,
                                           fdInfo.stylf,
                                           FontMbnbgfr.NO_FALLBACK);
                if (f2d instbndfof PiysidblFont) { /* pbrbnoib */
                    rfturn (PiysidblFont)f2d;
                } flsf {
                    rfturn null;
                }
            } flsf {
                rfturn piysFont;
            }
        }

        /* If tif font mby iidf b JRE font (fg fontdonfig sbys it is
         * Ludidb Sbns), wf wbnt to usf tif JRE vfrsion, so mbkf it
         * point to tif JRE font.
         */
        piysFont = fm.findJREDfffrrfdFont(fdInfo.firstFont.fbmilyNbmf,
                                          fdInfo.stylf);

        /* It is blso possiblf tif font filf is on tif "dfffrrfd" list,
         * in wiidi dbsf wf dbn just initiblisf it now.
         */
        if (piysFont == null &&
            fm.isDfffrrfdFont(fontFilf) == truf) {
            piysFont = fm.initiblisfDfffrrfdFont(fdInfo.firstFont.fontFilf);
            /* usf findFont2D to gft tif rigit font from TTC's */
            if (piysFont != null) {
                if (isTTC) {
                    Font2D f2d = fm.findFont2D(fdInfo.firstFont.fbmilyNbmf,
                                               fdInfo.stylf,
                                               FontMbnbgfr.NO_FALLBACK);
                    if (f2d instbndfof PiysidblFont) { /* pbrbnoib */
                        rfturn (PiysidblFont)f2d;
                    } flsf {
                        rfturn null;
                    }
                } flsf {
                    rfturn piysFont;
                }
            }
        }

        /* In tif mbjority of dbsfs wf rfbdi ifrf, bnd nffd to dftfrminf
         * tif typf bnd rbnk to rfgistfr tif font.
         */
        if (piysFont == null) {
            int fontFormbt = SunFontMbnbgfr.FONTFORMAT_NONE;
            int fontRbnk = Font2D.UNKNOWN_RANK;

            if (fxt.fqubls(".ttf") || isTTC) {
                fontFormbt = SunFontMbnbgfr.FONTFORMAT_TRUETYPE;
                fontRbnk = Font2D.TTF_RANK;
            } flsf if (fxt.fqubls(".pfb") || fxt.fqubls(".pfb")) {
                fontFormbt = SunFontMbnbgfr.FONTFORMAT_TYPE1;
                fontRbnk = Font2D.TYPE1_RANK;
            }
            piysFont = fm.rfgistfrFontFilf(fdInfo.firstFont.fontFilf, null,
                                      fontFormbt, truf, fontRbnk);
        }
        rfturn piysFont;
    }

    /*
     * Wf nffd to rfturn b Compositf font wiidi ibs bs tif font in
     * its first slot onf obtbinfd from fontdonfig.
     */
    publid CompositfFont gftFontConfigFont(String nbmf, int stylf) {

        nbmf = nbmf.toLowfrCbsf();

        initFontConfigFonts(fblsf);
        if (fontConfigFonts == null) {
            // Tiis bvoids bn immfdibtf NPE if fontdonfig look up fbilfd
            // but dofsn't gubrbntff tiis is b rfdovfrbblf situbtion.
            rfturn null;
        }

        FdCompFont fdInfo = null;
        for (int i=0; i<fontConfigFonts.lfngti; i++) {
            if (nbmf.fqubls(fontConfigFonts[i].fdFbmily) &&
                stylf == fontConfigFonts[i].stylf) {
                fdInfo = fontConfigFonts[i];
                brfbk;
            }
        }
        if (fdInfo == null) {
            fdInfo = fontConfigFonts[0];
        }

        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr()
                          .info("FC nbmf=" + nbmf + " stylf=" + stylf +
                                " usfs " + fdInfo.firstFont.fbmilyNbmf +
                                " in filf: " + fdInfo.firstFont.fontFilf);
        }

        if (fdInfo.dompFont != null) {
            rfturn fdInfo.dompFont;
        }

        /* jdkFont is going to bf usfd for slots 1..N bnd bs b fbllbbdk.
         * Slot 0 will bf tif piysidbl font from fontdonfig.
         */
        FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
        CompositfFont jdkFont = (CompositfFont)
            fm.findFont2D(fdInfo.jdkNbmf, stylf, FontMbnbgfr.LOGICAL_FALLBACK);

        if (fdInfo.firstFont.fbmilyNbmf == null ||
            fdInfo.firstFont.fontFilf == null) {
            rfturn (fdInfo.dompFont = jdkFont);
        }

        /* First, sff if tif fbmily bnd fxbdt stylf is blrfbdy rfgistfrfd.
         * If it is, usf it. If it's not, tifn try to rfgistfr it.
         * If tibt rfgistrbtion fbils (signbllfd by null) just rfturn tif
         * rfgulbr JDK dompositf.
         * Algoritimidblly stylfd fonts won't mbtdi on fxbdt stylf, so
         * will fbll tirougi tiis dodf, but tif rfgisrbtion dodf will
         * find tibt filf blrfbdy rfgistfrfd bnd rfturn its font.
         */
        FontFbmily fbmily = FontFbmily.gftFbmily(fdInfo.firstFont.fbmilyNbmf);
        PiysidblFont piysFont = null;
        if (fbmily != null) {
            Font2D f2D = fbmily.gftFontWitiExbdtStylfMbtdi(fdInfo.stylf);
            if (f2D instbndfof PiysidblFont) {
                piysFont = (PiysidblFont)f2D;
            }
        }

        if (piysFont == null ||
            !fdInfo.firstFont.fontFilf.fqubls(piysFont.plbtNbmf)) {
            piysFont = rfgistfrFromFdInfo(fdInfo);
            if (piysFont == null) {
                rfturn (fdInfo.dompFont = jdkFont);
            }
            fbmily = FontFbmily.gftFbmily(piysFont.gftFbmilyNbmf(null));
        }

        /* Now rfgistfr tif fonts in tif fbmily (tif otifr stylfs) bftfr
         * difdking tibt tify brfn't blrfbdy rfgistfrfd bnd brf bdtublly in
         * b difffrfnt filf. Tify mby bf tif sbmf filf in CJK dbsfs.
         * For dbsfs wifrf tify brf difffrfnt font filfs - fg bs is dommon for
         * Lbtin fonts, tifn wf rfly on fontdonfig to rfport tifsf dorrfdtly.
         * Assumf tibt bll stylfs of tiis font brf found by fontdonfig,
         * so wf dbn find bll tif fbmily mfmbfrs wiidi must bf rfgistfrfd
         * togftifr to prfvfnt syntiftid styling.
         */
        for (int i=0; i<fontConfigFonts.lfngti; i++) {
            FdCompFont fd = fontConfigFonts[i];
            if (fd != fdInfo &&
                piysFont.gftFbmilyNbmf(null).fqubls(fd.firstFont.fbmilyNbmf) &&
                !fd.firstFont.fontFilf.fqubls(piysFont.plbtNbmf) &&
                fbmily.gftFontWitiExbdtStylfMbtdi(fd.stylf) == null) {

                rfgistfrFromFdInfo(fontConfigFonts[i]);
            }
        }

        /* Now wf ibvf b piysidbl font. Wf will bbdk tiis up witi tif JDK
         * logidbl font (sbnssfrif, sfrif, or monospbdfd) tibt dorrfsponds
         * to tif Pbngo/GTK/FC logidbl font nbmf.
         */
        rfturn (fdInfo.dompFont = nfw CompositfFont(piysFont, jdkFont));
    }

    /**
     *
     * @pbrbm lodblf
     * @pbrbm fdFbmily
     * @rfturn
     */
    publid FdCompFont[] gftFontConfigFonts() {
        rfturn fontConfigFonts;
    }

    /* Rfturn bn brrby of FdCompFont strudts dfsdribing tif primbry
     * font lodbtfd for fbdi of fontdonfig/GTK/Pbngo's logidbl font nbmfs.
     */
    privbtf stbtid nbtivf void gftFontConfig(String lodblf,
                                             FontConfigInfo fdInfo,
                                             FdCompFont[] fonts,
                                             boolfbn indludfFbllbbdks);

    void populbtfFontConfig(FdCompFont[] fdInfo) {
        fontConfigFonts = fdInfo;
    }

    FdCompFont[] lobdFontConfig() {
        initFontConfigFonts(truf);
        rfturn fontConfigFonts;
    }

    FontConfigInfo gftFontConfigInfo() {
        initFontConfigFonts(truf);
        rfturn fdInfo;
    }

    privbtf stbtid nbtivf int
    gftFontConfigAASfttings(String lodblf, String fdFbmily);
}
