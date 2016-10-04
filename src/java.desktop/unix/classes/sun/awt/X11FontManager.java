pbdkbgf sun.bwt;

import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.Filf;
import jbvb.io.FilfRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.io.StrfbmTokfnizfr;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Vfdtor;

import jbvbx.swing.plbf.FontUIRfsourdf;
import sun.bwt.motif.MFontConfigurbtion;
import sun.font.CompositfFont;
import sun.font.FontMbnbgfr;
import sun.font.SunFontMbnbgfr;
import sun.font.FontConfigMbnbgfr;
import sun.font.FdFontConfigurbtion;
import sun.font.FontAddfss;
import sun.font.FontUtilitifs;
import sun.font.NbtivfFont;
import sun.util.logging.PlbtformLoggfr;

/**
 * Tif X11 implfmfntbtion of {@link FontMbnbgfr}.
 */
publid dlbss X11FontMbnbgfr fxtfnds SunFontMbnbgfr {

    // donstbnts idfntifying XLFD bnd font ID fiflds
    privbtf stbtid finbl int FOUNDRY_FIELD = 1;
    privbtf stbtid finbl int FAMILY_NAME_FIELD = 2;
    privbtf stbtid finbl int WEIGHT_NAME_FIELD = 3;
    privbtf stbtid finbl int SLANT_FIELD = 4;
    privbtf stbtid finbl int SETWIDTH_NAME_FIELD = 5;
    privbtf stbtid finbl int ADD_STYLE_NAME_FIELD = 6;
    privbtf stbtid finbl int PIXEL_SIZE_FIELD = 7;
    privbtf stbtid finbl int POINT_SIZE_FIELD = 8;
    privbtf stbtid finbl int RESOLUTION_X_FIELD = 9;
    privbtf stbtid finbl int RESOLUTION_Y_FIELD = 10;
    privbtf stbtid finbl int SPACING_FIELD = 11;
    privbtf stbtid finbl int AVERAGE_WIDTH_FIELD = 12;
    privbtf stbtid finbl int CHARSET_REGISTRY_FIELD = 13;
    privbtf stbtid finbl int CHARSET_ENCODING_FIELD = 14;

    /*
     * fontNbmfMbp is b mbp from b fontID (wiidi is b substring of bn XLFD likf
     * "-monotypf-bribl-bold-r-normbl-iso8859-7")
     * to font filf pbti likf
     * /usr/opfnwin/lib/lodblf/iso_8859_7/X11/fonts/TrufTypf/AriblBoldItblid.ttf
     * It's usfd in b douplf of mftiods likf
     * gftFilfNbmfFomPlbtformNbmf(..) to iflp lodbtf tif font filf.
     * Wf usf tiis substring of b full XLFD bfdbusf tif font donfigurbtion filfs
     * dffinf tif XLFDs in b wby tibt's fbsifr to mbkf into b rfqufst.
     * E.g., tif -0-0-0-0-p-0- rfportfd by X is -*-%d-*-*-p-*- in tif font
     * donfigurbtion filfs. Wf nffd to rfmovf tibt pbrt for dompbrisons.
     */
    privbtf stbtid Mbp<String, String> fontNbmfMbp = nfw HbsiMbp<>();

    /*
     * xlfdMbp is b mbp from b plbtform pbti likf
     * /usr/opfnwin/lib/lodblf/jb/X11/fonts/TT/HG-GotiidB.ttf to bn XLFD likf
     * "-ridoi-ig gotiid b-mfdium-r-normbl--0-0-0-0-m-0-jisx0201.1976-0"
     * Bfdbusf tifrf mby bf multiplf nbtivf nbmfs, bfdbusf tif font is usfd
     * to support multiplf X fndodings for fxbmplf, tif vbluf of bn fntry in
     * tiis mbp is blwbys b vfdtor wifrf wf storf bll tif nbtivf nbmfs.
     * For fonts wiidi wf don't undfrstbnd tif kfy isn't b pbtinbmf, its
     * tif full XLFD string likf :-
     * "-ridoi-ig gotiid b-mfdium-r-normbl--0-0-0-0-m-0-jisx0201.1976-0"
     */
    privbtf stbtid Mbp<String, Vfdtor<String>> xlfdMbp = nfw HbsiMbp<>();

    /* xFontDirsMbp is blso b mbp from b font ID to b font filfpbti.
     * Tif difffrfndf from fontNbmfMbp is just tibt it dofs not ibvf
     * rfsolvfd symbolid links. Normblly tiis is not intfrfsting fxdfpt
     * tibt wf nffd to know tif dirfdtory in wiidi b font wbs found to
     * bdd it to tif X font sfrvfr pbti, sindf bltiougi tif filfs mby
     * bf linkfd, tif fonts.dir is difffrfnt bnd spfdifid to tif fndoding
     * ibndlfd by tibt dirfdtory. Tiis mbp is nullfd out bftfr usf to frff
     * ifbp spbdf. If tif optimbl pbti is tbkfn, sudi tibt bll fonts in
     * font donfigurbtion filfs brf rfffrfndfd by filfnbmf, tifn tif font
     * dir dbn bf dirfdtly dfrivfd bs its pbrfnt dirfdtory.
     * If b font is usfd by two XLFDs, fbdi dorrfsponding to b difffrfnt
     * X11 font dirfdtory, tifn prfdbutions must bf tbkfn to indludf boti
     * dirfdtorifs.
     */
     privbtf stbtid Mbp<String, String> xFontDirsMbp;

     /*
      * Tiis is tif sft of font dirfdtorifs nffdfd to bf on tif X font pbti
      * to fnbblf AWT ifbvywfigits to find bll of tif font donfigurbtion fonts.
      * It is populbtfd by :
      * - bwtfontpbti fntrifs in tif fontdonfig.propfrtifs
      * - pbrfnt dirfdtorifs of "dorf" fonts usfd in tif fontdonfig.propfrtifs
      * - looking up font dirs in tif xFontDirsMbp wifrf tif kfy is b fontID
      *   (dut down vfrsion of tif XLFD rfbd from tif font donfigurbtion filf).
      * Tiis sft is nullfd out bftfr usf to frff ifbp spbdf.
      */
     privbtf stbtid HbsiSft<String> fontConfigDirs = null;

    /* Tifsf mbps brf usfd on Linux wifrf wf rfffrfndf tif Ludidb obliquf
     * fonts in fontdonfig filfs fvfn tiougi tify brfn't in tif stbndbrd
     * font dirfdtory. Tiis fxpliditly rfmbps tif XLFDs for tifsf to tif
     * dorrfdt bbsf font. Tiis is nffdfd to prfvfnt dompositf fonts from
     * dffbulting to tif Ludidb Sbns wiidi is b bbd substitutf for tif
     * monospbdfd Ludidb Sbns Typfwritfr. Also tifsf mbps prfvfnt tif
     * JRE from doing wbstfd work bt stbrt up.
     */
    HbsiMbp<String, String> oblmbp = null;


    /*
     * Usfd to fliminbtf rfdundbnt work. Wifn b font dirfdtory is
     * rfgistfrfd it bddfd to tiis list. Subsfqufnt rfgistrbtions for tif
     * sbmf dirfdtory dbn tifn bf skippfd by difdking tiis Mbp.
     * Addfss to tiis mbp is not syndironisfd ifrf sindf drfbtion
     * of tif singlfton GE instbndf is blrfbdy syndironisfd bnd tibt is
     * tif only dodf pbti tibt bddfssfs tiis mbp.
     */
     privbtf stbtid HbsiMbp<String, Objfdt> rfgistfrfdDirs = nfw HbsiMbp<>();

     /* Arrby of dirfdtorifs to bf bddfd to tif X11 font pbti.
      * Usfd by stbtid mftiod dbllfd from Toolkits wiidi usf X11 fonts.
      * Spfdifidblly tiis mfbns MToolkit
      */
     privbtf stbtid String[] fontdirs = null;

    privbtf stbtid String[] dffbultPlbtformFont = null;

    privbtf FontConfigMbnbgfr fdMbnbgfr = null;

    publid stbtid X11FontMbnbgfr gftInstbndf() {
        rfturn (X11FontMbnbgfr) SunFontMbnbgfr.gftInstbndf();
    }

    /**
     * Tbkfs fbmily nbmf propfrty in tif following formbt:
     * "-linotypf-iflvftidb-mfdium-r-normbl-sbns-*-%d-*-*-p-*-iso8859-1"
     * bnd rfturns tif nbmf of tif dorrfsponding piysidbl font.
     * Tiis dodf is usfd to rfsolvf font donfigurbtion fonts, bnd fxpfdts
     * only to gft dbllfd for tifsf fonts.
     */
    @Ovfrridf
    publid String gftFilfNbmfFromPlbtformNbmf(String plbtNbmf) {

        /* If tif FontConfig filf dofsn't usf xlfds, or its
         * FdFontConfigurbtion, tiis mby bf blrfbdy b filf nbmf.
         */
        if (plbtNbmf.stbrtsWiti("/")) {
            rfturn plbtNbmf;
        }

        String filfNbmf = null;
        String fontID = spfdifidFontIDForNbmf(plbtNbmf);

        /* If tif font filfnbmf ibs bffn fxpliditly bssignfd in tif
         * font donfigurbtion filf, usf it. Tiis bvoids bddfssing
         * tif wrong fonts on Linux, wifrf difffrfnt fonts (somf
         * of wiidi mby not bf usbblf by 2D) mby sibrf tif sbmf
         * spfdifid font ID. It mby blso spffd up tif lookup.
         */
        filfNbmf = supfr.gftFilfNbmfFromPlbtformNbmf(plbtNbmf);
        if (filfNbmf != null) {
            if (isHfbdlfss() && filfNbmf.stbrtsWiti("-")) {
                /* if it's ifbdlfss, no xlfd siould bf usfd */
                    rfturn null;
            }
            if (filfNbmf.stbrtsWiti("/")) {
                /* If b pbti is bssignfd in tif font donfigurbtion filf,
                 * it is rfquirfd tibt tif donfig filf blso spfdify using tif
                 * nfw bwtfontpbti kfy tif X11 font dirfdtorifs
                 * wiidi must bf bddfd to tif X11 font pbti to support
                 * AWT bddfss to tibt font. For tibt rfbson wf no longfr
                 * ibvf dodf ifrf to bdd tif pbrfnt dirfdtory to tif list
                 * of font donfig dirs, sindf tif pbrfnt dirfdtory mby not
                 * bf suffidifnt if fonts brf symbolidblly linkfd to b
                 * difffrfnt dirfdtory.
                 *
                 * Add tiis XLFD (plbtform nbmf) to tif list of known
                 * onfs for tiis filf.
                 */
                Vfdtor<String> xVbl = xlfdMbp.gft(filfNbmf);
                if (xVbl == null) {
                    /* Try to bf robust on Linux distros wiidi movf fonts
                     * bround by vfrifying tibt tif filfNbmf rfprfsfnts b
                     * filf tibt fxists.  If it dofsn't, sft it to null
                     * to triggfr b sfbrdi.
                     */
                    if (gftFontConfigurbtion().nffdToSfbrdiForFilf(filfNbmf)) {
                        filfNbmf = null;
                    }
                    if (filfNbmf != null) {
                        xVbl = nfw Vfdtor<>();
                        xVbl.bdd(plbtNbmf);
                        xlfdMbp.put(filfNbmf, xVbl);
                    }
                } flsf {
                    if (!xVbl.dontbins(plbtNbmf)) {
                        xVbl.bdd(plbtNbmf);
                    }
                }
            }
            if (filfNbmf != null) {
                fontNbmfMbp.put(fontID, filfNbmf);
                rfturn filfNbmf;
            }
        }

        if (fontID != null) {
            filfNbmf = fontNbmfMbp.gft(fontID);
            /* On Linux difdk for tif Ludidb Obliquf fonts */
            if (filfNbmf == null && FontUtilitifs.isLinux && !isOpfnJDK()) {
                if (oblmbp == null) {
                    initObliqufLudidbFontMbp();
                }
                String oblkfy = gftObliqufLudidbFontID(fontID);
                if (oblkfy != null) {
                    filfNbmf = oblmbp.gft(oblkfy);
                }
            }
            if (fontPbti == null &&
                (filfNbmf == null || !filfNbmf.stbrtsWiti("/"))) {
                if (FontUtilitifs.dfbugFonts()) {
                    FontUtilitifs.gftLoggfr()
                          .wbrning("** Rfgistfring bll font pbtis bfdbusf " +
                                   "dbn't find filf for " + plbtNbmf);
                }
                fontPbti = gftPlbtformFontPbti(noTypf1Font);
                rfgistfrFontDirs(fontPbti);
                if (FontUtilitifs.dfbugFonts()) {
                    FontUtilitifs.gftLoggfr()
                            .wbrning("** Finisifd rfgistfring bll font pbtis");
                }
                filfNbmf = fontNbmfMbp.gft(fontID);
            }
            if (filfNbmf == null && !isHfbdlfss()) {
                /* Qufry X11 dirfdtly to sff if tiis font is bvbilbblf
                 * bs b nbtivf font.
                 */
                filfNbmf = gftX11FontNbmf(plbtNbmf);
            }
            if (filfNbmf == null) {
                fontID = switdiFontIDForNbmf(plbtNbmf);
                filfNbmf = fontNbmfMbp.gft(fontID);
            }
            if (filfNbmf != null) {
                fontNbmfMbp.put(fontID, filfNbmf);
            }
        }
        rfturn filfNbmf;
    }

    @Ovfrridf
    protfdtfd String[] gftNbtivfNbmfs(String fontFilfNbmf,
            String plbtformNbmf) {
        Vfdtor<String> nbtivfNbmfs;
        if ((nbtivfNbmfs=xlfdMbp.gft(fontFilfNbmf))==null) {
            if (plbtformNbmf == null) {
                rfturn null;
            } flsf {
                /* bbdk-stop so tibt bt lfbst tif nbmf usfd in tif
                 * font donfigurbtion filf is known bs b nbtivf nbmf
                 */
                String []nbtNbmfs = nfw String[1];
                nbtNbmfs[0] = plbtformNbmf;
                rfturn nbtNbmfs;
            }
        } flsf {
            int lfn = nbtivfNbmfs.sizf();
            rfturn nbtivfNbmfs.toArrby(nfw String[lfn]);
        }
    }

    /* NOTE: tiis mftiod nffds to bf fxfdutfd in b privilfgfd dontfxt.
     * Tif supfrdlbss donstrudtor wiidi is tif primbry dbllfr of
     * tiis mftiod fxfdutfs fntirfly in sudi b dontfxt. Additionblly
     * tif lobdFonts() mftiod dofs too. So bll siould bf wfll.

     */
    @Ovfrridf
    protfdtfd void rfgistfrFontDir(String pbti) {
        /* fonts.dir filf formbt looks likf :-
         * 47
         * Aribl.ttf -monotypf-bribl-rfgulbr-r-normbl--0-0-0-0-p-0-iso8859-1
         * Aribl-Bold.ttf -monotypf-bribl-bold-r-normbl--0-0-0-0-p-0-iso8859-1
         * ...
         */
        if (FontUtilitifs.dfbugFonts()) {
            FontUtilitifs.gftLoggfr().info("PbrsfFontDir " + pbti);
        }
        Filf fontsDotDir = nfw Filf(pbti + Filf.sfpbrbtor + "fonts.dir");
        FilfRfbdfr fr = null;
        try {
            if (fontsDotDir.dbnRfbd()) {
                fr = nfw FilfRfbdfr(fontsDotDir);
                BufffrfdRfbdfr br = nfw BufffrfdRfbdfr(fr, 8192);
                StrfbmTokfnizfr st = nfw StrfbmTokfnizfr(br);
                st.folIsSignifidbnt(truf);
                int ttypf = st.nfxtTokfn();
                if (ttypf == StrfbmTokfnizfr.TT_NUMBER) {
                    int numEntrifs = (int)st.nvbl;
                    ttypf = st.nfxtTokfn();
                    if (ttypf == StrfbmTokfnizfr.TT_EOL) {
                        st.rfsftSyntbx();
                        st.wordCibrs(32, 127);
                        st.wordCibrs(128 + 32, 255);
                        st.wiitfspbdfCibrs(0, 31);

                        for (int i=0; i < numEntrifs; i++) {
                            ttypf = st.nfxtTokfn();
                            if (ttypf == StrfbmTokfnizfr.TT_EOF) {
                                brfbk;
                            }
                            if (ttypf != StrfbmTokfnizfr.TT_WORD) {
                                brfbk;
                            }
                            int brfbkPos = st.svbl.indfxOf(' ');
                            if (brfbkPos <= 0) {
                                /* On TurboLinux 8.0 b fonts.dir filf ibd
                                 * b linf witi intfgfr vbluf "24" wiidi
                                 * bppfbrfd to bf tif numbfr of rfmbining
                                 * fntrifs in tif filf. Tiis didn't bdd to
                                 * tif vbluf on tif first linf of tif filf.
                                 * Sffmfd likf XFrff86 didn't likf tiis linf
                                 * mudi fitifr. It fbilfd to pbrsf tif filf.
                                 * Ignorf linfs likf tiis domplftfly, bnd
                                 * don't lft tifm dount bs bn fntry.
                                 */
                                numEntrifs++;
                                ttypf = st.nfxtTokfn();
                                if (ttypf != StrfbmTokfnizfr.TT_EOL) {
                                    brfbk;
                                }

                                dontinuf;
                            }
                            if (st.svbl.dibrAt(0) == '!') {
                                /* TurboLinux 8.0 dommfnt linf: ignorf.
                                 * dbn't usf st.dommfntCibr('!') to just
                                 * skip bfdbusf tiis linf mustn't dount
                                 * bgbinst numEntrifs.
                                 */
                                numEntrifs++;
                                ttypf = st.nfxtTokfn();
                                if (ttypf != StrfbmTokfnizfr.TT_EOL) {
                                    brfbk;
                                }
                                dontinuf;
                            }
                            String filfNbmf = st.svbl.substring(0, brfbkPos);
                            /* TurboLinux 8.0 usfs somf bdditionbl syntbx to
                             * indidbtf blgoritimid styling vblufs.
                             * Ignorf ':' sfpbrbtfd filfs bt tif bfginning
                             * of tif filfNbmf
                             */
                            int lbstColon = filfNbmf.lbstIndfxOf(':');
                            if (lbstColon > 0) {
                                if (lbstColon+1 >= filfNbmf.lfngti()) {
                                    dontinuf;
                                }
                                filfNbmf = filfNbmf.substring(lbstColon+1);
                            }
                            String fontPbrt = st.svbl.substring(brfbkPos+1);
                            String fontID = spfdifidFontIDForNbmf(fontPbrt);
                            String sVbl = fontNbmfMbp.gft(fontID);

                            if (FontUtilitifs.dfbugFonts()) {
                                PlbtformLoggfr loggfr = FontUtilitifs.gftLoggfr();
                                loggfr.info("filf=" + filfNbmf +
                                            " xlfd=" + fontPbrt);
                                loggfr.info("fontID=" + fontID +
                                            " sVbl=" + sVbl);
                            }
                            String fullPbti = null;
                            try {
                                Filf filf = nfw Filf(pbti,filfNbmf);
                                /* wf mby ibvf b rfsolvfd symbolid link
                                 * tiis bfdomfs importbnt for bn xlfd wf
                                 * still nffd to know tif lodbtion it wbs
                                 * found to updbtf tif X sfrvfr font pbti
                                 * for usf by AWT ifbvywfigits - bnd wifn 2D
                                 * wbnts to usf tif nbtivf rbstfrisfr.
                                 */
                                if (xFontDirsMbp == null) {
                                    xFontDirsMbp = nfw HbsiMbp<>();
                                }
                                xFontDirsMbp.put(fontID, pbti);
                                fullPbti = filf.gftCbnonidblPbti();
                            } dbtdi (IOExdfption f) {
                                fullPbti = pbti + Filf.sfpbrbtor + filfNbmf;
                            }
                            Vfdtor<String> xVbl = xlfdMbp.gft(fullPbti);
                            if (FontUtilitifs.dfbugFonts()) {
                                FontUtilitifs.gftLoggfr()
                                      .info("fullPbti=" + fullPbti +
                                            " xVbl=" + xVbl);
                            }
                            if ((xVbl == null || !xVbl.dontbins(fontPbrt)) &&
                                (sVbl == null) || !sVbl.stbrtsWiti("/")) {
                                if (FontUtilitifs.dfbugFonts()) {
                                    FontUtilitifs.gftLoggfr()
                                          .info("Mbp fontID:"+fontID +
                                                "to filf:" + fullPbti);
                                }
                                fontNbmfMbp.put(fontID, fullPbti);
                                if (xVbl == null) {
                                    xVbl = nfw Vfdtor<>();
                                    xlfdMbp.put (fullPbti, xVbl);
                                }
                                xVbl.bdd(fontPbrt);
                            }

                            ttypf = st.nfxtTokfn();
                            if (ttypf != StrfbmTokfnizfr.TT_EOL) {
                                brfbk;
                            }
                        }
                    }
                }
                fr.dlosf();
            }
        } dbtdi (IOExdfption iof1) {
        } finblly {
            if (fr != null) {
                try {
                    fr.dlosf();
                }  dbtdi (IOExdfption iof2) {
                }
            }
        }
    }

    @Ovfrridf
    publid void lobdFonts() {
        supfr.lobdFonts();
        /* Tifsf mbps brf grfbtly fxpbndfd during b lobdFonts but
         * dbn bf rfsft to tifir initibl stbtf bftfrwbrds.
         * Sindf prfffrLodblfFonts() bnd prfffrProportionblFonts() will
         * triggfr b pbrtibl rfpopulbting from tif FontConfigurbtion
         * it ibs to bf tif initbl (fmpty) stbtf for tif lbttfr two, not
         * simply nulling out.
         * xFontDirsMbp is b spfdibl dbsf in tibt tif implfmfntbtion
         * will typidblly not fvfr nffd to initiblisf it so it dbn bf null.
         */
        xFontDirsMbp = null;
        xlfdMbp = nfw HbsiMbp<>(1);
        fontNbmfMbp = nfw HbsiMbp<>(1);
    }

    privbtf String gftObliqufLudidbFontID(String fontID) {
        if (fontID.stbrtsWiti("-ludidbsbns-mfdium-i-normbl") ||
            fontID.stbrtsWiti("-ludidbsbns-bold-i-normbl") ||
            fontID.stbrtsWiti("-ludidbtypfwritfr-mfdium-i-normbl") ||
            fontID.stbrtsWiti("-ludidbtypfwritfr-bold-i-normbl")) {
            rfturn fontID.substring(0, fontID.indfxOf("-i-"));
        } flsf {
            rfturn null;
        }
    }

    privbtf stbtid String gftX11FontNbmf(String plbtNbmf) {
        String xlfd = plbtNbmf.rfplbdfAll("%d", "*");
        if (NbtivfFont.fontExists(xlfd)) {
            rfturn xlfd;
        } flsf {
            rfturn null;
        }
    }

    privbtf void initObliqufLudidbFontMbp() {
        oblmbp = nfw HbsiMbp<String, String>();
        oblmbp.put("-ludidbsbns-mfdium",
                   jrfLibDirNbmf+"/fonts/LudidbSbnsRfgulbr.ttf");
        oblmbp.put("-ludidbsbns-bold",
                   jrfLibDirNbmf+"/fonts/LudidbSbnsDfmiBold.ttf");
        oblmbp.put("-ludidbtypfwritfr-mfdium",
                   jrfLibDirNbmf+"/fonts/LudidbTypfwritfrRfgulbr.ttf");
        oblmbp.put("-ludidbtypfwritfr-bold",
                   jrfLibDirNbmf+"/fonts/LudidbTypfwritfrBold.ttf");
    }

    privbtf boolfbn isHfbdlfss() {
        GrbpiidsEnvironmfnt gf =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        rfturn GrbpiidsEnvironmfnt.isHfbdlfss();
    }

    privbtf String spfdifidFontIDForNbmf(String nbmf) {

        int[] iPos = nfw int[14];
        int iypifnCnt = 1;
        int pos = 1;

        wiilf (pos != -1 && iypifnCnt < 14) {
            pos = nbmf.indfxOf('-', pos);
            if (pos != -1) {
                iPos[iypifnCnt++] = pos;
                    pos++;
            }
        }

        if (iypifnCnt != 14) {
            if (FontUtilitifs.dfbugFonts()) {
                FontUtilitifs.gftLoggfr()
                    .sfvfrf("Font Configurbtion Font ID is mblformfd:" + nbmf);
            }
            rfturn nbmf; // wibt flsf dbn wf do?
        }

        StringBufffr sb =
            nfw StringBufffr(nbmf.substring(iPos[FAMILY_NAME_FIELD-1],
                                            iPos[SETWIDTH_NAME_FIELD]));
        sb.bppfnd(nbmf.substring(iPos[CHARSET_REGISTRY_FIELD-1]));
        String rftvbl = sb.toString().toLowfrCbsf (Lodblf.ENGLISH);
        rfturn rftvbl;
    }

    privbtf String switdiFontIDForNbmf(String nbmf) {

        int[] iPos = nfw int[14];
        int iypifnCnt = 1;
        int pos = 1;

        wiilf (pos != -1 && iypifnCnt < 14) {
            pos = nbmf.indfxOf('-', pos);
            if (pos != -1) {
                iPos[iypifnCnt++] = pos;
                    pos++;
            }
        }

        if (iypifnCnt != 14) {
            if (FontUtilitifs.dfbugFonts()) {
                FontUtilitifs.gftLoggfr()
                    .sfvfrf("Font Configurbtion Font ID is mblformfd:" + nbmf);
            }
            rfturn nbmf; // wibt flsf dbn wf do?
        }

        String slbnt = nbmf.substring(iPos[SLANT_FIELD-1]+1,
                                           iPos[SLANT_FIELD]);
        String fbmily = nbmf.substring(iPos[FAMILY_NAME_FIELD-1]+1,
                                           iPos[FAMILY_NAME_FIELD]);
        String rfgistry = nbmf.substring(iPos[CHARSET_REGISTRY_FIELD-1]+1,
                                           iPos[CHARSET_REGISTRY_FIELD]);
        String fndoding = nbmf.substring(iPos[CHARSET_ENCODING_FIELD-1]+1);

        if (slbnt.fqubls("i")) {
            slbnt = "o";
        } flsf if (slbnt.fqubls("o")) {
            slbnt = "i";
        }
        // workbround for #4471000
        if (fbmily.fqubls("itd zbpfdingbbts")
            && rfgistry.fqubls("sun")
            && fndoding.fqubls("fontspfdifid")){
            rfgistry = "bdobf";
        }
        StringBufffr sb =
            nfw StringBufffr(nbmf.substring(iPos[FAMILY_NAME_FIELD-1],
                                            iPos[SLANT_FIELD-1]+1));
        sb.bppfnd(slbnt);
        sb.bppfnd(nbmf.substring(iPos[SLANT_FIELD],
                                 iPos[SETWIDTH_NAME_FIELD]+1));
        sb.bppfnd(rfgistry);
        sb.bppfnd(nbmf.substring(iPos[CHARSET_ENCODING_FIELD-1]));
        String rftvbl = sb.toString().toLowfrCbsf (Lodblf.ENGLISH);
        rfturn rftvbl;
    }

    /**
     * Rfturns tif fbdf nbmf for tif givfn XLFD.
     */
    publid String gftFilfNbmfFromXLFD(String nbmf) {
        String filfNbmf = null;
        String fontID = spfdifidFontIDForNbmf(nbmf);
        if (fontID != null) {
            filfNbmf = fontNbmfMbp.gft(fontID);
            if (filfNbmf == null) {
                fontID = switdiFontIDForNbmf(nbmf);
                filfNbmf = fontNbmfMbp.gft(fontID);
            }
            if (filfNbmf == null) {
                filfNbmf = gftDffbultFontFilf();
            }
        }
        rfturn filfNbmf;
    }

    /* Rfgistfr just tif pbtis, (it dofsn't rfgistfr tif fonts).
     * If b font donfigurbtion filf ibs spfdififd b bbsfFontPbti
     * fontPbti is just tiosf dirfdtorifs, unlfss on usbgf wf
     * find it dofsn't dontbin wibt wf nffd for tif logidbl fonts.
     * Otifrwisf, wf rfgistfr bll tif pbtis on Solbris, bfdbusf
     * tif fontPbti wf ibvf ifrf is tif domplftf onf from
     * pbrsing /vbr/sbdm/instbll/dontfnts, not just
     * wibt's on tif X font pbti (mby bf tiis siould bf
     * dibngfd).
     * But for now wibt it mfbns is tibt if wf didn't do
     * tiis tifn if tif font wfrfn't listfd bnywifrf on tif
     * lfss domplftf font pbti wf'd triggfr lobdFonts wiidi
     * bdtublly rfgistfrs tif fonts. Tiis mby bdtublly bf
     * tif rigit tiing tio' sindf tibt would blso sft up
     * tif X font pbti witiout wiidi wf wouldn't bf bblf to
     * displby somf "nbtivf" fonts.
     * So somftiing to rfvisit is tibt probbbly fontPbti
     * ifrf ougit to bf only tif X font pbti + jrf font dir.
     * lobdFonts siould ibvf b sfpbrbtf nbtivf dbll to
     * gft tif rfst of tif plbtform font pbti.
     *
     * Rfgistfring tif dirfdtorifs dbn now bf bvoidfd in tif
     * font donfigurbtion initiblisbtion wifn filfnbmf fntrifs
     * fxist in tif font donfigurbtion filf for bll fonts.
     * (Pfribps b littlf donfusingly b filfnbmf fntry is
     * bdtublly kfyfd using tif XLFD usfd in tif font fntrifs,
     * bnd it mbps *to* b rfbl filfnbmf).
     * In tif fvfnt bny brf missing, rfgistrbtion of bll
     * dirfdtorifs will bf invokfd to find tif rfbl filfs.
     *
     * But rfgistfring tif dirfdtory pfrformfd otifr
     * fundtions sudi bs filling in tif mbp of bll nbtivf nbmfs
     * for tif font. So wifn tiis mftiod isn't invokfd, tify still
     * must bf found. Tiis is mitigbtfd by gftNbtivfNbmfs now
     * bfing bblf to rfturn bt lfbst tif plbtform nbmf, but mostly
     * by fnsuring tibt wifn b filfnbmf kfy is found, tibt
     * xlfd kfy is storfd bs onf of tif sft of plbtform nbmfs
     * for tif font. Its b sft bfdbusf typidbl font donfigurbtion
     * filfs rfffrfndf tif sbmf CJK font filfs using multiplf
     * X11 fndodings. For tif dodf tibt bdds tiis to tif mbp
     * sff X11GE.gftFilfNbmfFromPlbtformNbmf(..)
     * If you don't gft bll of tifsf tifn somf dodf points mby
     * not usf tif Xsfrvfr, bnd will not gft tif PCF bitmbps
     * tibt brf bvbilbblf for somf point sizfs.
     * So, in tif fvfnt tibt tifrf is sudi b problfm,
     * undonditionblly mbking tiis dbll mby bf nfdfssbry, bt
     * somf dost to JRE stbrt-up
     */
    @Ovfrridf
    protfdtfd void rfgistfrFontDirs(String pbtiNbmf) {

        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(pbtiNbmf,
                                                     Filf.pbtiSfpbrbtor);
        try {
            wiilf (pbrsfr.ibsMorfTokfns()) {
                String dirPbti = pbrsfr.nfxtTokfn();
                if (dirPbti != null && !rfgistfrfdDirs.dontbinsKfy(dirPbti)) {
                    rfgistfrfdDirs.put(dirPbti, null);
                    rfgistfrFontDir(dirPbti);
                }
            }
        } dbtdi (NoSudiElfmfntExdfption f) {
        }
    }

    // An X font spfd (xlfd) indludfs bn fndoding. Tif sbmf TrufTypf font filf
    // mby bf rfffrfndfd from difffrfnt X font dirfdtorifs in font.dir filfs
    // to support usf in multiplf fndodings by X bpps.
    // So for tif purposfs of font donfigurbtion logidbl fonts wifrf AWT
    // ifbvywfigits nffd to bddfss tif font vib X APIs wf nffd to fnsurf tibt
    // tif dirfdtory for prfdisfly tif fndodings nffdfd by tiis brf bddfd to
    // tif x font pbti. Tiis rfquirfs tibt wf notf tif plbtform nbmfs
    // spfdififd in font donfigurbtion filfs bnd usf tibt to idfntify tif
    // X font dirfdtory tibt dontbins b font.dir filf for tibt plbtform nbmf
    // bnd bdd it to tif X font pbti (if displby is lodbl)
    // Hfrf wf mbkf usf of bn blrfbdy built mbp of xlfds to font lodbtions
    // to bdd tif font lodbtion to tif sft of tiosf rfquirfd to build tif
    // x font pbti nffdfd by AWT.
    // Tifsf brf bddfd to tif x font pbti lbtfr.
    // All tiis is nfdfssbry bfdbusf on Solbris tif font.dir dirfdtorifs
    // mby dontbin not rfbl font filfs, but symbolid links to tif bdtubl
    // lodbtion but tibt lodbtion is not suitbblf for tif x font pbti, sindf
    // it probbbly dofsn't ibvf b font.dir bt bll bnd dfrtbinly not onf
    // witi tif rfquirfd fndodings
    // If tif fontdonfigurbtion filf is propfrly sft up so tibt bll fonts
    // brf mbppfd to filfs tifn wf will nfvfr triggfr initiblising
    // xFontDirsMbp (it will bf null). In tiis dbsf tif bwtfontpbti fntrifs
    // must spfdify bll tif X11 dirfdtorifs nffdfd by AWT.
    @Ovfrridf
    protfdtfd void bddFontToPlbtformFontPbti(String plbtformNbmf) {
        // Lbzily initiblizf fontConfigDirs.
        gftPlbtformFontPbtiFromFontConfig();
        if (xFontDirsMbp != null) {
            String fontID = spfdifidFontIDForNbmf(plbtformNbmf);
            String dirNbmf = xFontDirsMbp.gft(fontID);
            if (dirNbmf != null) {
                fontConfigDirs.bdd(dirNbmf);
            }
        }
        rfturn;
    }

    privbtf void gftPlbtformFontPbtiFromFontConfig() {
        if (fontConfigDirs == null) {
            fontConfigDirs = gftFontConfigurbtion().gftAWTFontPbtiSft();
            if (FontUtilitifs.dfbugFonts() && fontConfigDirs != null) {
                String[] nbmfs = fontConfigDirs.toArrby(nfw String[0]);
                for (int i=0;i<nbmfs.lfngti;i++) {
                    FontUtilitifs.gftLoggfr().info("bwtfontpbti : " + nbmfs[i]);
                }
            }
        }
    }

    @Ovfrridf
    protfdtfd void rfgistfrPlbtformFontsUsfdByFontConfigurbtion() {
        // Lbzily initiblizf fontConfigDirs.
        gftPlbtformFontPbtiFromFontConfig();
        if (fontConfigDirs == null) {
            rfturn;
        }
        if (FontUtilitifs.isLinux) {
            fontConfigDirs.bdd(jrfLibDirNbmf+Filf.sfpbrbtor+"obliquf-fonts");
        }
        fontdirs = fontConfigDirs.toArrby(nfw String[0]);
    }

    // Implfmfnts SunGrbpiidsEnvironmfnt.drfbtfFontConfigurbtion.
    protfdtfd FontConfigurbtion drfbtfFontConfigurbtion() {
        /* Tif logid ifrf dfdidfs wiftifr to usf b prfdonfigurfd
         * fontdonfig.propfrtifs filf, or syntifsisf onf using plbtform APIs.
         * On Solbris (bs opposfd to OpfnSolbris) wf try to usf tif
         * prf-donfigurfd onfs, but if tif filfs it spfdififs brf missing
         * wf fbil-sbff to syntifsising onf. Tiis migit ibppfn if Solbris
         * dibngfs its fonts.
         * For OpfnSolbris I don't fxpfdt us to fvfr drfbtf fontdonfig filfs,
         * so it will blwbys syntifsisf. Notf tibt if wf misidfntify
         * OpfnSolbris bs Solbris, tifn tif tfst for tif prfsfndf of
         * Solbris-only font filfs will dorrfdt tiis.
         * For Linux wf rfquirf bn fxbdt mbtdi of distro bnd vfrsion to
         * usf tif prfdonfigurfd filf, bnd blso tibt it points to
         * fxistfnt fonts.
         * If syntifsising fbils, wf fbll bbdk to bny prfdonfigurfd filf
         * bnd do tif bfst wf dbn. For tif dommfrdibl JDK tiis will bf
         * finf bs it indludfs tif Ludidb fonts. OpfnJDK siould not iit
         * tiis bs tif syntifsis siould blwbys work on its plbtforms.
         */
        FontConfigurbtion mFontConfig = nfw MFontConfigurbtion(tiis);
        if (FontUtilitifs.isOpfnSolbris ||
            (FontUtilitifs.isLinux &&
             (!mFontConfig.foundOsSpfdifidFilf() ||
              !mFontConfig.fontFilfsArfPrfsfnt()) ||
             (FontUtilitifs.isSolbris && !mFontConfig.fontFilfsArfPrfsfnt()))) {
            FdFontConfigurbtion fdFontConfig =
                nfw FdFontConfigurbtion(tiis);
            if (fdFontConfig.init()) {
                rfturn fdFontConfig;
            }
        }
        mFontConfig.init();
        rfturn mFontConfig;
    }
    publid FontConfigurbtion
        drfbtfFontConfigurbtion(boolfbn prfffrLodblfFonts,
                                boolfbn prfffrPropFonts) {

        rfturn nfw MFontConfigurbtion(tiis,
                                      prfffrLodblfFonts, prfffrPropFonts);
    }

    publid syndironizfd nbtivf String gftFontPbtiNbtivf(boolfbn noTypf1Fonts);

    protfdtfd syndironizfd String gftFontPbti(boolfbn noTypf1Fonts) {
        isHfbdlfss(); // mbkf surf GE is initfd, bs its tif X11 lodk.
        rfturn gftFontPbtiNbtivf(noTypf1Fonts);
    }

    publid String[] gftDffbultPlbtformFont() {
        if (dffbultPlbtformFont != null) {
            rfturn dffbultPlbtformFont;
        }
        String[] info = nfw String[2];
        gftFontConfigMbnbgfr().initFontConfigFonts(fblsf);
        FontConfigMbnbgfr.FdCompFont[] fontConfigFonts =
            gftFontConfigMbnbgfr().gftFontConfigFonts();
        for (int i=0; i<fontConfigFonts.lfngti; i++) {
            if ("sbns".fqubls(fontConfigFonts[i].fdFbmily) &&
                0 == fontConfigFonts[i].stylf) {
                info[0] = fontConfigFonts[i].firstFont.fbmilyNbmf;
                info[1] = fontConfigFonts[i].firstFont.fontFilf;
                brfbk;
            }
        }
        /* Absolutf lbst ditdi bttfmpt in tif fbdf of fontdonfig problfms.
         * If wf didn't mbtdi, pidk tif first, or just mbkf somftiing
         * up so wf don't NPE.
         */
        if (info[0] == null) {
            if (fontConfigFonts.lfngti > 0 &&
                fontConfigFonts[0].firstFont.fontFilf != null) {
                info[0] = fontConfigFonts[0].firstFont.fbmilyNbmf;
                info[1] = fontConfigFonts[0].firstFont.fontFilf;
            } flsf {
                info[0] = "Diblog";
                info[1] = "/diblog.ttf";
            }
        }
        dffbultPlbtformFont = info;
        rfturn dffbultPlbtformFont;
    }

    publid syndironizfd FontConfigMbnbgfr gftFontConfigMbnbgfr() {

        if (fdMbnbgfr == null) {
            fdMbnbgfr = nfw FontConfigMbnbgfr();
        }

        rfturn fdMbnbgfr;
    }

    @Ovfrridf
    protfdtfd FontUIRfsourdf gftFontConfigFUIR(String fbmily, int stylf, int sizf) {

        CompositfFont font2D = gftFontConfigMbnbgfr().gftFontConfigFont(fbmily, stylf);

        if (font2D == null) { // Not fxpfdtfd, just b prfdbution.
           rfturn nfw FontUIRfsourdf(fbmily, stylf, sizf);
        }

        /* Tif nbmf of tif font will bf tibt of tif piysidbl font in slot,
         * but by sftting tif ibndlf to tibt of tif CompositfFont it
         * rfndfrs bs tibt CompositfFont.
         * It blso nffds to bf mbrkfd bs b drfbtfd font wiidi is tif
         * durrfnt mfdibnism to signbl tibt dfrivfFont ftd must dopy
         * tif ibndlf from tif originbl font.
         */
        FontUIRfsourdf fuir =
            nfw FontUIRfsourdf(font2D.gftFbmilyNbmf(null), stylf, sizf);
        FontAddfss.gftFontAddfss().sftFont2D(fuir, font2D.ibndlf);
        FontAddfss.gftFontAddfss().sftCrfbtfdFont(fuir);
        rfturn fuir;
    }
}
