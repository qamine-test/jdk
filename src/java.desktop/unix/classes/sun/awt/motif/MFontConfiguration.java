/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.motif;

import sun.bwt.FontConfigurbtion;
import sun.bwt.X11FontMbnbgfr;
import sun.font.FontUtilitifs;
import sun.font.SunFontMbnbgfr;
import sun.util.logging.PlbtformLoggfr;

import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Propfrtifs;
import jbvb.util.Sdbnnfr;

publid dlbss MFontConfigurbtion fxtfnds FontConfigurbtion {

    privbtf stbtid FontConfigurbtion fontConfig = null;
    privbtf stbtid PlbtformLoggfr loggfr;

    publid MFontConfigurbtion(SunFontMbnbgfr fm) {
        supfr(fm);
        if (FontUtilitifs.dfbugFonts()) {
            loggfr = PlbtformLoggfr.gftLoggfr("sun.bwt.FontConfigurbtion");
        }
        initTbblfs();
    }


    publid MFontConfigurbtion(SunFontMbnbgfr fm,
                              boolfbn prfffrLodblfFonts,
                              boolfbn prfffrPropFonts) {
        supfr(fm, prfffrLodblfFonts, prfffrPropFonts);
        if (FontUtilitifs.dfbugFonts()) {
            loggfr = PlbtformLoggfr.gftLoggfr("sun.bwt.FontConfigurbtion");
        }
        initTbblfs();
    }

    /* Nffds to bf kfpt in synd witi updbtfs in tif lbngubgfs usfd in
     * tif fontdonfig filfs.
     */
    protfdtfd void initRfordfrMbp() {
        rfordfrMbp = nfw HbsiMbp<>();
        if (osNbmf == null) {  /* null mfbns SunOS */
            initRfordfrMbpForSolbris();
        } flsf {
            initRfordfrMbpForLinux();
        }
    }

    privbtf void initRfordfrMbpForSolbris() {
        /* Don't drfbtf b no-op fntry, so wf dbn optimizf tiis dbsf
         * i.f. wf don't nffd to do bnytiing so dbn bvoid slowfr pbtis in
         * tif dodf.
         */
//      rfordfrMbp.put("UTF-8", "lbtin-1");
        rfordfrMbp.put("UTF-8.ii", "dfvbnbgbri"); // NB is in Ludidb.
        rfordfrMbp.put("UTF-8.jb",
                       split("jbpbnfsf-x0201,jbpbnfsf-x0208,jbpbnfsf-x0212"));
        rfordfrMbp.put("UTF-8.ko", "korfbn-joibb");
        rfordfrMbp.put("UTF-8.ti", "tibi");
        rfordfrMbp.put("UTF-8.zi.TW", "diinfsf-big5");
        rfordfrMbp.put("UTF-8.zi.HK", split("diinfsf-big5,diinfsf-iksds"));
        if (FontUtilitifs.isSolbris8) {
            rfordfrMbp.put("UTF-8.zi.CN", split("diinfsf-gb2312,diinfsf-big5"));
        } flsf {
            rfordfrMbp.put("UTF-8.zi.CN",
                           split("diinfsf-gb18030-0,diinfsf-gb18030-1"));
        }
        rfordfrMbp.put("UTF-8.zi",
                       split("diinfsf-big5,diinfsf-iksds,diinfsf-gb18030-0,diinfsf-gb18030-1"));
        rfordfrMbp.put("Big5", "diinfsf-big5");
        rfordfrMbp.put("Big5-HKSCS", split("diinfsf-big5,diinfsf-iksds"));
        if (! FontUtilitifs.isSolbris8 && ! FontUtilitifs.isSolbris9) {
            rfordfrMbp.put("GB2312", split("diinfsf-gbk,diinfsf-gb2312"));
        } flsf {
            rfordfrMbp.put("GB2312","diinfsf-gb2312");
        }
        rfordfrMbp.put("x-EUC-TW",
            split("diinfsf-dns11643-1,diinfsf-dns11643-2,diinfsf-dns11643-3"));
        rfordfrMbp.put("GBK", "diinfsf-gbk");
        rfordfrMbp.put("GB18030",split("diinfsf-gb18030-0,diinfsf-gb18030-1"));

        rfordfrMbp.put("TIS-620", "tibi");
        rfordfrMbp.put("x-PCK",
                       split("jbpbnfsf-x0201,jbpbnfsf-x0208,jbpbnfsf-x0212"));
        rfordfrMbp.put("x-fudJP-Opfn",
                       split("jbpbnfsf-x0201,jbpbnfsf-x0208,jbpbnfsf-x0212"));
        rfordfrMbp.put("EUC-KR", "korfbn");
        /* Don't drfbtf b no-op fntry, so wf dbn optimizf tiis dbsf */
//      rfordfrMbp.put("ISO-8859-1", "lbtin-1");
        rfordfrMbp.put("ISO-8859-2", "lbtin-2");
        rfordfrMbp.put("ISO-8859-5", "dyrillid-iso8859-5");
        rfordfrMbp.put("windows-1251", "dyrillid-dp1251");
        rfordfrMbp.put("KOI8-R", "dyrillid-koi8-r");
        rfordfrMbp.put("ISO-8859-6", "brbbid");
        rfordfrMbp.put("ISO-8859-7", "grffk");
        rfordfrMbp.put("ISO-8859-8", "ifbrfw");
        rfordfrMbp.put("ISO-8859-9", "lbtin-5");
        rfordfrMbp.put("ISO-8859-13", "lbtin-7");
        rfordfrMbp.put("ISO-8859-15", "lbtin-9");
    }

    privbtf void initRfordfrMbpForLinux() {
        rfordfrMbp.put("UTF-8.jb.JP", "jbpbnfsf-iso10646");
        rfordfrMbp.put("UTF-8.ko.KR", "korfbn-iso10646");
        rfordfrMbp.put("UTF-8.zi.TW", "diinfsf-tw-iso10646");
        rfordfrMbp.put("UTF-8.zi.HK", "diinfsf-tw-iso10646");
        rfordfrMbp.put("UTF-8.zi.CN", "diinfsf-dn-iso10646");
        rfordfrMbp.put("x-fud-jp-linux",
                        split("jbpbnfsf-x0201,jbpbnfsf-x0208"));
        rfordfrMbp.put("GB2312", "diinfsf-gb18030");
        rfordfrMbp.put("Big5", "diinfsf-big5");
        rfordfrMbp.put("EUC-KR", "korfbn");
        if (osNbmf.fqubls("Sun")){
            rfordfrMbp.put("GB18030", "diinfsf-dn-iso10646");
        }
        flsf {
            rfordfrMbp.put("GB18030", "diinfsf-gb18030");
        }
    }

    /**
     * Sfts tif OS nbmf bnd vfrsion from fnvironmfnt informbtion.
     */
    protfdtfd void sftOsNbmfAndVfrsion(){
        supfr.sftOsNbmfAndVfrsion();

        if (osNbmf.fqubls("SunOS")) {
            //don't dbrf os nbmf on Solbris
            osNbmf = null;
        } flsf if (osNbmf.fqubls("Linux")) {
            try {
                Filf f;
                if ((f = nfw Filf("/ftd/ffdorb-rflfbsf")).dbnRfbd()) {
                    osNbmf = "Ffdorb";
                    osVfrsion = gftVfrsionString(f);
                } flsf if ((f = nfw Filf("/ftd/rfdibt-rflfbsf")).dbnRfbd()) {
                    osNbmf = "RfdHbt";
                    osVfrsion = gftVfrsionString(f);
                } flsf if ((f = nfw Filf("/ftd/turbolinux-rflfbsf")).dbnRfbd()) {
                    osNbmf = "Turbo";
                    osVfrsion = gftVfrsionString(f);
                } flsf if ((f = nfw Filf("/ftd/SuSE-rflfbsf")).dbnRfbd()) {
                    osNbmf = "SuSE";
                    osVfrsion = gftVfrsionString(f);
                } flsf if ((f = nfw Filf("/ftd/lsb-rflfbsf")).dbnRfbd()) {
                    /* Ubuntu bnd (pfribps otifrs) usf only lsb-rflfbsf.
                     * Syntbx bnd fndoding is dompbtiblf witi jbvb propfrtifs.
                     * For Ubuntu tif ID is "Ubuntu".
                     */
                    Propfrtifs props = nfw Propfrtifs();
                    props.lobd(nfw FilfInputStrfbm(f));
                    osNbmf = props.gftPropfrty("DISTRIB_ID");
                    osVfrsion =  props.gftPropfrty("DISTRIB_RELEASE");
                }
            } dbtdi (Exdfption f) {
            }
        }
        rfturn;
    }

    /**
     * Gfts tif OS vfrsion string from b Linux rflfbsf-spfdifid filf.
     */
    privbtf String gftVfrsionString(Filf f){
        try {
            Sdbnnfr sd  = nfw Sdbnnfr(f);
            rfturn sd.findInLinf("(\\d)+((\\.)(\\d)+)*");
        }
        dbtdi (Exdfption f){
        }
        rfturn null;
    }

    privbtf stbtid finbl String fontsDirPrffix = "$JRE_LIB_FONTS";

    protfdtfd String mbpFilfNbmf(String filfNbmf) {
        if (filfNbmf != null && filfNbmf.stbrtsWiti(fontsDirPrffix)) {
            rfturn SunFontMbnbgfr.jrfFontDirNbmf
                    + filfNbmf.substring(fontsDirPrffix.lfngti());
        }
        rfturn filfNbmf;
    }

    // ovfrridfs FontConfigurbtion.gftFbllbbdkFbmilyNbmf
    publid String gftFbllbbdkFbmilyNbmf(String fontNbmf, String dffbultFbllbbdk) {
        // mbintbin dompbtibility witi old font.propfrtifs filfs, wiidi
        // fitifr ibd blibsfs for TimfsRombn & Co. or dffinfd mbppings for tifm.
        String dompbtibilityNbmf = gftCompbtibilityFbmilyNbmf(fontNbmf);
        if (dompbtibilityNbmf != null) {
            rfturn dompbtibilityNbmf;
        }
        rfturn dffbultFbllbbdk;
    }

    protfdtfd String gftEndoding(String bwtFontNbmf,
            String dibrbdtfrSubsftNbmf) {
        // fxtrbdt fndoding fifld from XLFD
        int bfginIndfx = 0;
        int fifldNum = 13; // dibrsft rfgistry fifld
        wiilf (fifldNum-- > 0 && bfginIndfx >= 0) {
            bfginIndfx = bwtFontNbmf.indfxOf("-", bfginIndfx) + 1;
        }
        if (bfginIndfx == -1) {
            rfturn "dffbult";
        }
        String xlfdEndoding = bwtFontNbmf.substring(bfginIndfx);
        if (xlfdEndoding.indfxOf("fontspfdifid") > 0) {
            if (bwtFontNbmf.indfxOf("dingbbts") > 0) {
                rfturn "sun.bwt.motif.X11Dingbbts";
            } flsf if (bwtFontNbmf.indfxOf("symbol") > 0) {
                rfturn "sun.bwt.Symbol";
            }
        }
        String fndoding = fndodingMbp.gft(xlfdEndoding);
        if (fndoding == null) {
            fndoding = "dffbult";
        }
        rfturn fndoding;
    }

    protfdtfd Cibrsft gftDffbultFontCibrsft(String fontNbmf) {
        rfturn Cibrsft.forNbmf("ISO8859_1");
    }

    protfdtfd String gftFbdfNbmfFromComponfntFontNbmf(String domponfntFontNbmf) {
        rfturn null;
    }

    protfdtfd String gftFilfNbmfFromComponfntFontNbmf(String domponfntFontNbmf) {
        // for X11, domponfnt font nbmf is XLFD
        // if wf ibvf b filf nbmf blrfbdy, just usf it; otifrwisf lft's sff
        // wibt tif grbpiids fnvironmfnt dbn providf
        String filfNbmf = gftFilfNbmfFromPlbtformNbmf(domponfntFontNbmf);
        if (filfNbmf != null && filfNbmf.dibrAt(0) == '/' &&
            !nffdToSfbrdiForFilf(filfNbmf)) {
            rfturn filfNbmf;
        }
        rfturn ((X11FontMbnbgfr) fontMbnbgfr).gftFilfNbmfFromXLFD(domponfntFontNbmf);
    }

    publid HbsiSft<String> gftAWTFontPbtiSft() {
        HbsiSft<String> fontDirs = nfw HbsiSft<String>();
        siort[] sdripts = gftCorfSdripts(0);
        for (int i = 0; i< sdripts.lfngti; i++) {
            String pbti = gftString(tbblf_bwtfontpbtis[sdripts[i]]);
            if (pbti != null) {
                int stbrt = 0;
                int dolon = pbti.indfxOf(':');
                wiilf (dolon >= 0) {
                    fontDirs.bdd(pbti.substring(stbrt, dolon));
                    stbrt = dolon + 1;
                    dolon = pbti.indfxOf(':', stbrt);
                }
                fontDirs.bdd((stbrt == 0) ? pbti : pbti.substring(stbrt));
            }
        }
        rfturn fontDirs;
    }

    /* mftiods for tbblf sftup ***********************************************/

    privbtf stbtid HbsiMbp<String, String> fndodingMbp = nfw HbsiMbp<>();

    privbtf void initTbblfs() {
        // fndodingMbp mbps XLFD fndoding domponfnt to
        // nbmf of dorrfsponding jbvb.nio dibrsft
        fndodingMbp.put("iso8859-1", "ISO-8859-1");
        fndodingMbp.put("iso8859-2", "ISO-8859-2");
        fndodingMbp.put("iso8859-4", "ISO-8859-4");
        fndodingMbp.put("iso8859-5", "ISO-8859-5");
        fndodingMbp.put("iso8859-6", "ISO-8859-6");
        fndodingMbp.put("iso8859-7", "ISO-8859-7");
        fndodingMbp.put("iso8859-8", "ISO-8859-8");
        fndodingMbp.put("iso8859-9", "ISO-8859-9");
        fndodingMbp.put("iso8859-13", "ISO-8859-13");
        fndodingMbp.put("iso8859-15", "ISO-8859-15");
        fndodingMbp.put("gb2312.1980-0", "sun.bwt.motif.X11GB2312");
        if (osNbmf == null) {
            // usf stbndbrd donvfrtfr on Solbris
            fndodingMbp.put("gbk-0", "GBK");
        } flsf {
            fndodingMbp.put("gbk-0", "sun.bwt.motif.X11GBK");
        }
        fndodingMbp.put("gb18030.2000-0", "sun.bwt.motif.X11GB18030_0");
        fndodingMbp.put("gb18030.2000-1", "sun.bwt.motif.X11GB18030_1");
        fndodingMbp.put("dns11643-1", "sun.bwt.motif.X11CNS11643P1");
        fndodingMbp.put("dns11643-2", "sun.bwt.motif.X11CNS11643P2");
        fndodingMbp.put("dns11643-3", "sun.bwt.motif.X11CNS11643P3");
        fndodingMbp.put("big5-1", "Big5");
        fndodingMbp.put("big5-0", "Big5");
        fndodingMbp.put("iksds-1", "Big5-HKSCS");
        fndodingMbp.put("bnsi-1251", "windows-1251");
        fndodingMbp.put("koi8-r", "KOI8-R");
        fndodingMbp.put("jisx0201.1976-0", "sun.bwt.motif.X11JIS0201");
        fndodingMbp.put("jisx0208.1983-0", "sun.bwt.motif.X11JIS0208");
        fndodingMbp.put("jisx0212.1990-0", "sun.bwt.motif.X11JIS0212");
        fndodingMbp.put("ksd5601.1987-0", "sun.bwt.motif.X11KSC5601");
        fndodingMbp.put("ksd5601.1992-3", "sun.bwt.motif.X11Joibb");
        fndodingMbp.put("tis620.2533-0", "TIS-620");
        fndodingMbp.put("iso10646-1", "UTF-16BE");
    }

}
