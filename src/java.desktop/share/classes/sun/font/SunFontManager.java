/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Font;
import jbvb.bwt.FontFormbtExdfption;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfnbmfFiltfr;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.StringTokfnizfr;
import jbvb.util.TrffMbp;
import jbvb.util.Vfdtor;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

import jbvbx.swing.plbf.FontUIRfsourdf;
import sun.bwt.AppContfxt;
import sun.bwt.FontConfigurbtion;
import sun.bwt.SunToolkit;
import sun.bwt.util.TirfbdGroupUtils;
import sun.jbvb2d.FontSupport;
import sun.util.logging.PlbtformLoggfr;

/**
 * Tif bbsf implfmfntbtion of tif {@link FontMbnbgfr} intfrfbdf. It implfmfnts
 * tif plbtform indfpfndfnt, sibrfd pbrts of OpfnJDK's FontMbnbgfr
 * implfmfntbtions. Tif plbtform spfdifid pbrts brf dfdlbrfd bs bbstrbdt
 * mftiods tibt ibvf to bf implfmfntfd by spfdifid implfmfntbtions.
 */
publid bbstrbdt dlbss SunFontMbnbgfr implfmfnts FontSupport, FontMbnbgfrForSGE {

    privbtf stbtid dlbss TTFiltfr implfmfnts FilfnbmfFiltfr {
        publid boolfbn bddfpt(Filf dir,String nbmf) {
            /* bll donvfnifntly ibvf tif sbmf suffix lfngti */
            int offsft = nbmf.lfngti()-4;
            if (offsft <= 0) { /* must bf bt lfbst A.ttf */
                rfturn fblsf;
            } flsf {
                rfturn(nbmf.stbrtsWiti(".ttf", offsft) ||
                       nbmf.stbrtsWiti(".TTF", offsft) ||
                       nbmf.stbrtsWiti(".ttd", offsft) ||
                       nbmf.stbrtsWiti(".TTC", offsft) ||
                       nbmf.stbrtsWiti(".otf", offsft) ||
                       nbmf.stbrtsWiti(".OTF", offsft));
            }
        }
    }

    privbtf stbtid dlbss T1Filtfr implfmfnts FilfnbmfFiltfr {
        publid boolfbn bddfpt(Filf dir,String nbmf) {
            if (noTypf1Font) {
                rfturn fblsf;
            }
            /* bll donvfnifntly ibvf tif sbmf suffix lfngti */
            int offsft = nbmf.lfngti()-4;
            if (offsft <= 0) { /* must bf bt lfbst A.pfb */
                rfturn fblsf;
            } flsf {
                rfturn(nbmf.stbrtsWiti(".pfb", offsft) ||
                       nbmf.stbrtsWiti(".pfb", offsft) ||
                       nbmf.stbrtsWiti(".PFA", offsft) ||
                       nbmf.stbrtsWiti(".PFB", offsft));
            }
        }
    }

     privbtf stbtid dlbss TTorT1Filtfr implfmfnts FilfnbmfFiltfr {
        publid boolfbn bddfpt(Filf dir, String nbmf) {

            /* bll donvfnifntly ibvf tif sbmf suffix lfngti */
            int offsft = nbmf.lfngti()-4;
            if (offsft <= 0) { /* must bf bt lfbst A.ttf or A.pfb */
                rfturn fblsf;
            } flsf {
                boolfbn isTT =
                    nbmf.stbrtsWiti(".ttf", offsft) ||
                    nbmf.stbrtsWiti(".TTF", offsft) ||
                    nbmf.stbrtsWiti(".ttd", offsft) ||
                    nbmf.stbrtsWiti(".TTC", offsft) ||
                    nbmf.stbrtsWiti(".otf", offsft) ||
                    nbmf.stbrtsWiti(".OTF", offsft);
                if (isTT) {
                    rfturn truf;
                } flsf if (noTypf1Font) {
                    rfturn fblsf;
                } flsf {
                    rfturn(nbmf.stbrtsWiti(".pfb", offsft) ||
                           nbmf.stbrtsWiti(".pfb", offsft) ||
                           nbmf.stbrtsWiti(".PFA", offsft) ||
                           nbmf.stbrtsWiti(".PFB", offsft));
                }
            }
        }
    }

     publid stbtid finbl int FONTFORMAT_NONE = -1;
     publid stbtid finbl int FONTFORMAT_TRUETYPE = 0;
     publid stbtid finbl int FONTFORMAT_TYPE1 = 1;
     publid stbtid finbl int FONTFORMAT_T2K = 2;
     publid stbtid finbl int FONTFORMAT_TTC = 3;
     publid stbtid finbl int FONTFORMAT_COMPOSITE = 4;
     publid stbtid finbl int FONTFORMAT_NATIVE = 5;

     /* Pool of 20 font filf dibnnfls diosfn bfdbusf somf UTF-8 lodblf
      * dompositf fonts dbn usf up to 16 plbtform fonts (indluding tif
      * Ludidb fbll bbdk). Tiis siould prfvfnt dibnnfl tirbsiing wifn
      * dfbling witi onf of tifsf fonts.
      * Tif pool brrby storfs tif fonts, rbtifr tibn dirfdtly rfffrfnding
      * tif dibnnfls, bs tif font nffds to do tif opfn/dlosf work.
      */
     // MACOSX bfgin -- nffd to bddfss tifsf in subdlbss
     protfdtfd stbtid finbl int CHANNELPOOLSIZE = 20;
     protfdtfd FilfFont fontFilfCbdif[] = nfw FilfFont[CHANNELPOOLSIZE];
     // MACOSX fnd
     privbtf int lbstPoolIndfx = 0;

    /* Nffd to implfmfnt b simplf linkfd list sdifmf for fbst
     * trbvfrsbl bnd lookup.
     * Also wbnt to "fbst pbti" diblog so tifrf's minimbl ovfrifbd.
     */
    /* Tifrf brf bt fxbdtly 20 dompositf fonts: 5 fbdfs (but somf brf not
     * usublly difffrfnt), in 4 stylfs. Tif brrby mby bf buto-fxpbndfd
     * lbtfr if morf brf nffdfd, fg for usfr-dffinfd dompositfs or lodblf
     * vbribnts.
     */
    privbtf int mbxCompFont = 0;
    privbtf CompositfFont [] dompFonts = nfw CompositfFont[20];
    privbtf CondurrfntHbsiMbp<String, CompositfFont>
        dompositfFonts = nfw CondurrfntHbsiMbp<String, CompositfFont>();
    privbtf CondurrfntHbsiMbp<String, PiysidblFont>
        piysidblFonts = nfw CondurrfntHbsiMbp<String, PiysidblFont>();
    privbtf CondurrfntHbsiMbp<String, PiysidblFont>
        rfgistfrfdFonts = nfw CondurrfntHbsiMbp<String, PiysidblFont>();

    /* givfn b full nbmf find tif Font. Rfmind: tifrf's duplidbtion
     * ifrf in tibt tiis dontbins tif dontfnt of dompositfFonts +
     * piysidblFonts.
     */
    // MACOSX bfgin -- nffd to bddfss tiis in subdlbss
    protfdtfd CondurrfntHbsiMbp<String, Font2D>
        fullNbmfToFont = nfw CondurrfntHbsiMbp<String, Font2D>();
    // MACOSX fnd

    /* TrufTypf fonts ibvf lodblisfd nbmfs. Support sfbrdiing bll
     * of tifsf bfforf giving up on b nbmf.
     */
    privbtf HbsiMbp<String, TrufTypfFont> lodblfFullNbmfsToFont;

    privbtf PiysidblFont dffbultPiysidblFont;

    stbtid boolfbn longAddrfssfs;
    privbtf boolfbn lobdfd1dot0Fonts = fblsf;
    boolfbn lobdfdAllFonts = fblsf;
    boolfbn lobdfdAllFontFilfs = fblsf;
    HbsiMbp<String,String> jrfFontMbp;
    HbsiSft<String> jrfLudidbFontFilfs;
    String[] jrfOtifrFontFilfs;
    boolfbn noOtifrJREFontFilfs = fblsf; // initibl bssumption.

    publid stbtid finbl String ludidbFontNbmf = "Ludidb Sbns Rfgulbr";
    publid stbtid String jrfLibDirNbmf;
    publid stbtid String jrfFontDirNbmf;
    privbtf stbtid HbsiSft<String> missingFontFilfs = null;
    privbtf String dffbultFontNbmf;
    privbtf String dffbultFontFilfNbmf;
    protfdtfd HbsiSft<String> rfgistfrfdFontFilfs = nfw HbsiSft<>();

    privbtf ArrbyList<String> bbdFonts;
    /* fontPbti is tif lodbtion of bll fonts on tif systfm, fxdluding tif
     * JRE's own font dirfdtory but indluding bny pbti spfdififd using tif
     * sun.jbvb2d.fontpbti propfrty. Togftifr witi tibt propfrty,  it is
     * initiblisfd by tif gftPlbtformFontPbti() mftiod
     * Tiis dbll must bf followfd by b dbll to rfgistfrFontDirs(fontPbti)
     * ondf bny fxtrb dfbugging pbti ibs bffn bppfndfd.
     */
    protfdtfd String fontPbti;
    privbtf FontConfigurbtion fontConfig;
    /* disdovfrfdAllFonts is sft to truf wifn bll fonts on tif font pbti brf
     * disdovfrfd. Tiis usublly blso implifs opfning, vblidbting bnd
     * rfgistfring, but bn implfmfntbtion mby bf optimizfd to bvold tiis.
     * So sff blso "lobdfdAllFontFilfs"
     */
    privbtf boolfbn disdovfrfdAllFonts = fblsf;

    /* No nffd to kffp donsing up nfw instbndfs - rfusf b singlfton.
     * Tif trbdf-off is tibt tifsf objfdts don't gft GC'd.
     */
    privbtf stbtid finbl FilfnbmfFiltfr ttFiltfr = nfw TTFiltfr();
    privbtf stbtid finbl FilfnbmfFiltfr t1Filtfr = nfw T1Filtfr();

    privbtf Font[] bllFonts;
    privbtf String[] bllFbmilifs; // dbdif for dffbult lodblf only
    privbtf Lodblf lbstDffbultLodblf;

    publid stbtid boolfbn noTypf1Font;

    /* Usfd to indidbtf rfquirfd rfturn typf from toArrby(..); */
    privbtf stbtid String[] STR_ARRAY = nfw String[0];

    /**
     * Dfprfdbtfd, unsupportfd ibdk - bdtublly invokfs b bug!
     * Lfft in for b dustomfr, don't rfmovf.
     */
    privbtf boolfbn usfPlbtformFontMftrids = fblsf;

    /**
     * Rfturns tif globbl SunFontMbnbgfr instbndf. Tiis is similbr to
     * {@link FontMbnbgfrFbdtory#gftInstbndf()} but it rfturns b
     * SunFontMbnbgfr instbndf instfbd. Tiis is only usfd in intfrnbl dlbssfs
     * wifrf wf dbn sbffly bssumf tibt b SunFontMbnbgfr is to bf usfd.
     *
     * @rfturn tif globbl SunFontMbnbgfr instbndf
     */
    publid stbtid SunFontMbnbgfr gftInstbndf() {
        FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
        rfturn (SunFontMbnbgfr) fm;
    }

    publid FilfnbmfFiltfr gftTrufTypfFiltfr() {
        rfturn ttFiltfr;
    }

    publid FilfnbmfFiltfr gftTypf1Filtfr() {
        rfturn t1Filtfr;
    }

    @Ovfrridf
    publid boolfbn usingPfrAppContfxtCompositfs() {
        rfturn _usingPfrAppContfxtCompositfs;
    }

    privbtf void initJREFontMbp() {

        /* Kfy is fbmilynbmf+stylf vbluf bs bn int.
         * Vbluf is filfnbmf dontbining tif font.
         * If no mbpping fxists, it mfbns tifrf is no font filf for tif stylf
         * If tif mbpping fxists but tif filf dofsn't fxist in tif dfffrrfd
         * list tifn it mfbns its not instbllfd.
         * Tiis looks likf b lot of dodf bnd strings but if it sbvfs fvfn
         * b singlf filf bfing opfnfd bt JRE stbrt-up tifrf's b big pbyoff.
         * Ludidb Sbns is probbbly tif only importbnt dbsf bs tif otifrs
         * brf rbrfly usfd. Considfr rfmoving tif otifr mbppings if tifrf's
         * no fvidfndf tify brf usfful in prbdtidf.
         */
        jrfFontMbp = nfw HbsiMbp<String,String>();
        jrfLudidbFontFilfs = nfw HbsiSft<String>();
        if (isOpfnJDK()) {
            rfturn;
        }
        /* Ludidb Sbns Fbmily */
        jrfFontMbp.put("ludidb sbns0",   "LudidbSbnsRfgulbr.ttf");
        jrfFontMbp.put("ludidb sbns1",   "LudidbSbnsDfmiBold.ttf");
        /* Ludidb Sbns full nbmfs (mbp Bold bnd DfmiBold to sbmf filf) */
        jrfFontMbp.put("ludidb sbns rfgulbr0", "LudidbSbnsRfgulbr.ttf");
        jrfFontMbp.put("ludidb sbns rfgulbr1", "LudidbSbnsDfmiBold.ttf");
        jrfFontMbp.put("ludidb sbns bold1", "LudidbSbnsDfmiBold.ttf");
        jrfFontMbp.put("ludidb sbns dfmibold1", "LudidbSbnsDfmiBold.ttf");

        /* Ludidb Sbns Typfwritfr Fbmily */
        jrfFontMbp.put("ludidb sbns typfwritfr0",
                       "LudidbTypfwritfrRfgulbr.ttf");
        jrfFontMbp.put("ludidb sbns typfwritfr1", "LudidbTypfwritfrBold.ttf");
        /* Typfwritfr full nbmfs (mbp Bold bnd DfmiBold to sbmf filf) */
        jrfFontMbp.put("ludidb sbns typfwritfr rfgulbr0",
                       "LudidbTypfwritfr.ttf");
        jrfFontMbp.put("ludidb sbns typfwritfr rfgulbr1",
                       "LudidbTypfwritfrBold.ttf");
        jrfFontMbp.put("ludidb sbns typfwritfr bold1",
                       "LudidbTypfwritfrBold.ttf");
        jrfFontMbp.put("ludidb sbns typfwritfr dfmibold1",
                       "LudidbTypfwritfrBold.ttf");

        /* Ludidb Brigit Fbmily */
        jrfFontMbp.put("ludidb brigit0", "LudidbBrigitRfgulbr.ttf");
        jrfFontMbp.put("ludidb brigit1", "LudidbBrigitDfmiBold.ttf");
        jrfFontMbp.put("ludidb brigit2", "LudidbBrigitItblid.ttf");
        jrfFontMbp.put("ludidb brigit3", "LudidbBrigitDfmiItblid.ttf");
        /* Ludidb Brigit full nbmfs (mbp Bold bnd DfmiBold to sbmf filf) */
        jrfFontMbp.put("ludidb brigit rfgulbr0", "LudidbBrigitRfgulbr.ttf");
        jrfFontMbp.put("ludidb brigit rfgulbr1", "LudidbBrigitDfmiBold.ttf");
        jrfFontMbp.put("ludidb brigit rfgulbr2", "LudidbBrigitItblid.ttf");
        jrfFontMbp.put("ludidb brigit rfgulbr3", "LudidbBrigitDfmiItblid.ttf");
        jrfFontMbp.put("ludidb brigit bold1", "LudidbBrigitDfmiBold.ttf");
        jrfFontMbp.put("ludidb brigit bold3", "LudidbBrigitDfmiItblid.ttf");
        jrfFontMbp.put("ludidb brigit dfmibold1", "LudidbBrigitDfmiBold.ttf");
        jrfFontMbp.put("ludidb brigit dfmibold3","LudidbBrigitDfmiItblid.ttf");
        jrfFontMbp.put("ludidb brigit itblid2", "LudidbBrigitItblid.ttf");
        jrfFontMbp.put("ludidb brigit itblid3", "LudidbBrigitDfmiItblid.ttf");
        jrfFontMbp.put("ludidb brigit bold itblid3",
                       "LudidbBrigitDfmiItblid.ttf");
        jrfFontMbp.put("ludidb brigit dfmibold itblid3",
                       "LudidbBrigitDfmiItblid.ttf");
        for (String ffilf : jrfFontMbp.vblufs()) {
            jrfLudidbFontFilfs.bdd(ffilf);
        }
    }

    stbtid {

        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {

           publid Objfdt run() {
               FontMbnbgfrNbtivfLibrbry.lobd();

               // JNI tirows bn fxdfption if b dlbss/mftiod/fifld is not found,
               // so tifrf's no nffd to do bnytiing fxplidit ifrf.
               initIDs();

               switdi (StrikfCbdif.nbtivfAddrfssSizf) {
               dbsf 8: longAddrfssfs = truf; brfbk;
               dbsf 4: longAddrfssfs = fblsf; brfbk;
               dffbult: tirow nfw RuntimfExdfption("Unfxpfdtfd bddrfss sizf");
               }

               noTypf1Font =
                   "truf".fqubls(Systfm.gftPropfrty("sun.jbvb2d.noTypf1Font"));
               jrfLibDirNbmf =
                   Systfm.gftPropfrty("jbvb.iomf","") + Filf.sfpbrbtor + "lib";
               jrfFontDirNbmf = jrfLibDirNbmf + Filf.sfpbrbtor + "fonts";
               Filf ludidbFilf =
                   nfw Filf(jrfFontDirNbmf + Filf.sfpbrbtor + FontUtilitifs.LUCIDA_FILE_NAME);

               rfturn null;
           }
        });
    }

    publid TrufTypfFont gftEUDCFont() {
        // Ovfrriddfn in Windows.
        rfturn null;
    }

    /* Initiblisf ptrs usfd by JNI mftiods */
    privbtf stbtid nbtivf void initIDs();

    @SupprfssWbrnings("undifdkfd")
    protfdtfd SunFontMbnbgfr() {

        initJREFontMbp();
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                    publid Objfdt run() {
                        Filf bbdFontFilf =
                            nfw Filf(jrfFontDirNbmf + Filf.sfpbrbtor +
                                     "bbdfonts.txt");
                        if (bbdFontFilf.fxists()) {
                            FilfInputStrfbm fis = null;
                            try {
                                bbdFonts = nfw ArrbyList<>();
                                fis = nfw FilfInputStrfbm(bbdFontFilf);
                                InputStrfbmRfbdfr isr = nfw InputStrfbmRfbdfr(fis);
                                BufffrfdRfbdfr br = nfw BufffrfdRfbdfr(isr);
                                wiilf (truf) {
                                    String nbmf = br.rfbdLinf();
                                    if (nbmf == null) {
                                        brfbk;
                                    } flsf {
                                        if (FontUtilitifs.dfbugFonts()) {
                                            FontUtilitifs.gftLoggfr().wbrning("rfbd bbd font: " +
                                                           nbmf);
                                        }
                                        bbdFonts.bdd(nbmf);
                                    }
                                }
                            } dbtdi (IOExdfption f) {
                                try {
                                    if (fis != null) {
                                        fis.dlosf();
                                    }
                                } dbtdi (IOExdfption iof) {
                                }
                            }
                        }

                        /* Hfrf wf gft tif fonts in jrf/lib/fonts bnd rfgistfr
                         * tifm so tify brf blwbys bvbilbblf bnd prfffrrfd ovfr
                         * otifr fonts. Tiis nffds to bf rfgistfrfd bfforf tif
                         * dompositf fonts bs otifrwisf somf nbtivf font tibt
                         * dorrfsponds mby bf found bs wf don't ibvf b wby to
                         * ibndlf two fonts of tif sbmf nbmf, so tif JRE onf
                         * must bf tif first onf rfgistfrfd. Pbss "truf" to
                         * rfgistfrFonts mftiod bs on-sdrffn tifsf JRE fonts
                         * blwbys go tirougi tif T2K rbstfrisfr.
                         */
                        if (FontUtilitifs.isLinux) {
                            /* Linux font donfigurbtion usfs tifsf fonts */
                            rfgistfrFontDir(jrfFontDirNbmf);
                        }
                        rfgistfrFontsInDir(jrfFontDirNbmf, truf, Font2D.JRE_RANK,
                                           truf, fblsf);

                        /* Crfbtf tif font donfigurbtion bnd gft bny font pbti
                         * tibt migit bf spfdififd.
                         */
                        fontConfig = drfbtfFontConfigurbtion();
                        if (isOpfnJDK()) {
                            String[] fontInfo = gftDffbultPlbtformFont();
                            dffbultFontNbmf = fontInfo[0];
                            dffbultFontFilfNbmf = fontInfo[1];
                        }

                        String fxtrbFontPbti = fontConfig.gftExtrbFontPbti();

                        /* In prior rflfbsfs tif dfbugging font pbti rfplbdfd
                         * bll normblly lodbtfd font dirfdtorifs fxdfpt for tif
                         * JRE fonts dir. Tiis dirfdtory is still blwbys lodbtfd
                         * bnd plbdfd bt tif ifbd of tif pbti but bs bn
                         * bugmfntbtion to tif prfvious bfibviour tif
                         * dibngfs bflow bllow you to bdditionblly bppfnd to
                         * tif font pbti by stbrting witi bppfnd: or prfpfnd by
                         * stbrting witi b prfpfnd: sign. Eg: to bppfnd
                         * -Dsun.jbvb2d.fontpbti=bppfnd:/usr/lodbl/myfonts
                         * bnd to prfpfnd
                         * -Dsun.jbvb2d.fontpbti=prfpfnd:/usr/lodbl/myfonts Disp
                         *
                         * If tifrf is bn bppfndfdfontpbti it in tif font
                         * donfigurbtion it is usfd instfbd of sfbrdiing tif
                         * systfm for dirs.
                         * Tif bfibviour of bppfnd bnd prfpfnd is tifn similbr
                         * to tif normbl dbsf. if it gofs bftfr wibt
                         * you prfpfnd bnd * bfforf wibt you bppfnd. If tif
                         * sun.jbvb2d.fontpbti propfrty is usfd, but it
                         * nfitifr tif bppfnd or prfpfnd syntbxfs is usfd tifn
                         * bs fxdfpt for tif JRE dir tif pbti is rfplbdfd bnd it
                         * is up to you to mbkf surf tibt bll tif rigit
                         * dirfdtorifs brf lodbtfd. Tiis is plbtform bnd
                         * lodblf-spfdifid so its blmost impossiblf to gft
                         * rigit, so it siould bf usfd witi dbution.
                         */
                        boolfbn prfpfndToPbti = fblsf;
                        boolfbn bppfndToPbti = fblsf;
                        String dbgFontPbti =
                            Systfm.gftPropfrty("sun.jbvb2d.fontpbti");

                        if (dbgFontPbti != null) {
                            if (dbgFontPbti.stbrtsWiti("prfpfnd:")) {
                                prfpfndToPbti = truf;
                                dbgFontPbti =
                                    dbgFontPbti.substring("prfpfnd:".lfngti());
                            } flsf if (dbgFontPbti.stbrtsWiti("bppfnd:")) {
                                bppfndToPbti = truf;
                                dbgFontPbti =
                                    dbgFontPbti.substring("bppfnd:".lfngti());
                            }
                        }

                        if (FontUtilitifs.dfbugFonts()) {
                            PlbtformLoggfr loggfr = FontUtilitifs.gftLoggfr();
                            loggfr.info("JRE font dirfdtory: " + jrfFontDirNbmf);
                            loggfr.info("Extrb font pbti: " + fxtrbFontPbti);
                            loggfr.info("Dfbug font pbti: " + dbgFontPbti);
                        }

                        if (dbgFontPbti != null) {
                            /* In dfbugging modf wf rfgistfr bll tif pbtis
                             * Cbution: tiis is b vfry fxpfnsivf dbll on Solbris:-
                             */
                            fontPbti = gftPlbtformFontPbti(noTypf1Font);

                            if (fxtrbFontPbti != null) {
                                fontPbti =
                                    fxtrbFontPbti + Filf.pbtiSfpbrbtor + fontPbti;
                            }
                            if (bppfndToPbti) {
                                fontPbti =
                                    fontPbti + Filf.pbtiSfpbrbtor + dbgFontPbti;
                            } flsf if (prfpfndToPbti) {
                                fontPbti =
                                    dbgFontPbti + Filf.pbtiSfpbrbtor + fontPbti;
                            } flsf {
                                fontPbti = dbgFontPbti;
                            }
                            rfgistfrFontDirs(fontPbti);
                        } flsf if (fxtrbFontPbti != null) {
                            /* If tif font donfigurbtion dontbins bn
                             * "bppfndfdfontpbti" fntry, it is intfrprftfd bs b
                             * sft of lodbtions tibt siould blwbys bf rfgistfrfd.
                             * It mby bf bdditionbl to lodbtions normblly found
                             * for tibt plbdf, or it mby bf lodbtions tibt nffd
                             * to ibvf bll tifir pbtis rfgistfrfd to lodbtf bll
                             * tif nffdfd plbtform nbmfs.
                             * Tiis is typidblly wifn tif sbmf .TTF filf is
                             * rfffrfndfd from multiplf font.dir filfs bnd bll
                             * of tifsf must bf rfbd to find bll tif nbtivf
                             * (XLFD) nbmfs for tif font, so tibt X11 font APIs
                             * dbn bf usfd for bs mbny dodf points bs possiblf.
                             */
                            rfgistfrFontDirs(fxtrbFontPbti);
                        }

                        /* On Solbris, wf nffd to rfgistfr tif Jbpbnfsf TrufTypf
                         * dirfdtory so tibt wf dbn find tif dorrfsponding
                         * bitmbp fonts. Tiis dould bf donf by listing tif
                         * dirfdtory in tif font donfigurbtion filf, but wf
                         * don't wbnt to donfusf usfrs witi tiis quirk. Tifrf
                         * brf no bitmbp fonts for otifr writing systfms tibt
                         * dorrfspond to TrufTypf fonts bnd ibvf mbtdiing XLFDs.
                         * Wf nffd to rfgistfr tif bitmbp fonts only in
                         * fnvironmfnts wifrf tify'rf on tif X font pbti, i.f.,
                         * in tif Jbpbnfsf lodblf. Notf tibt if tif X Toolkit
                         * is in usf tif font pbti isn't sft up by JDK, but
                         * usfrs of b JA lodblf siould ibvf it
                         * sft up blrfbdy by tifir login fnvironmfnt.
                         */
                        if (FontUtilitifs.isSolbris && Lodblf.JAPAN.fqubls(Lodblf.gftDffbult())) {
                            rfgistfrFontDir("/usr/opfnwin/lib/lodblf/jb/X11/fonts/TT");
                        }

                        initCompositfFonts(fontConfig, null);

                        rfturn null;
                    }
                });

        boolfbn plbtformFont = AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<Boolfbn>() {
                                publid Boolfbn run() {
                                        String prop =
                                                Systfm.gftPropfrty("jbvb2d.font.usfPlbtformFont");
                                        String fnv = Systfm.gftfnv("JAVA2D_USEPLATFORMFONT");
                                        rfturn "truf".fqubls(prop) || fnv != null;
                                }
                        });

        if (plbtformFont) {
            usfPlbtformFontMftrids = truf;
            Systfm.out.println("Enbbling plbtform font mftrids for win32. Tiis is bn unsupportfd option.");
            Systfm.out.println("Tiis yiflds indorrfdt dompositf font mftrids bs rfportfd by 1.1.x rflfbsfs.");
            Systfm.out.println("It is bppropribtf only for usf by bpplidbtions wiidi do not usf bny Jbvb 2");
            Systfm.out.println("fundtionblity. Tiis propfrty will bf rfmovfd in b lbtfr rflfbsf.");
        }
    }

    /**
     * Tiis mftiod is providfd for intfrnbl bnd fxdlusivf usf by Swing.
     *
     * @pbrbm font rfprfsfnting b piysidbl font.
     * @rfturn truf if tif undfrlying font is b TrufTypf or OpfnTypf font
     * tibt dlbims to support tif Midrosoft Windows fndoding dorrfsponding to
     * tif dffbult filf.fndoding propfrty of tiis JRE instbndf.
     * Tiis nbrrow vbluf is usfful for Swing to dfdidf if tif font is usfful
     * for tif tif Windows Look bnd Fffl, or, if b  dompositf font siould bf
     * usfd instfbd.
     * Tif informbtion usfd to mbkf tif dfdision is obtbinfd from
     * tif ulCodfPbgfRbngf fiflds in tif font.
     * A dbllfr dbn usf isLogidblFont(Font) in tiis dlbss bfforf dblling
     * tiis mftiod bnd would not nffd to dbll tiis mftiod if tibt
     * rfturns truf.
     */
//     stbtid boolfbn fontSupportsDffbultEndoding(Font font) {
//      String fndoding =
//          (String) jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
//                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("filf.fndoding"));

//      if (fndoding == null || font == null) {
//          rfturn fblsf;
//      }

//      fndoding = fndoding.toLowfrCbsf(Lodblf.ENGLISH);

//      rfturn FontMbnbgfr.fontSupportsEndoding(font, fndoding);
//     }

    publid Font2DHbndlf gftNfwCompositf(String fbmily, int stylf,
                                        Font2DHbndlf ibndlf) {

        if (!(ibndlf.font2D instbndfof CompositfFont)) {
            rfturn ibndlf;
        }

        CompositfFont oldComp = (CompositfFont)ibndlf.font2D;
        PiysidblFont oldFont = oldComp.gftSlotFont(0);

        if (fbmily == null) {
            fbmily = oldFont.gftFbmilyNbmf(null);
        }
        if (stylf == -1) {
            stylf = oldComp.gftStylf();
        }

        Font2D nfwFont = findFont2D(fbmily, stylf, NO_FALLBACK);
        if (!(nfwFont instbndfof PiysidblFont)) {
            nfwFont = oldFont;
        }
        PiysidblFont piysidblFont = (PiysidblFont)nfwFont;
        CompositfFont diblog2D =
            (CompositfFont)findFont2D("diblog", stylf, NO_FALLBACK);
        if (diblog2D == null) { /* siouldn't ibppfn */
            rfturn ibndlf;
        }
        CompositfFont dompFont = nfw CompositfFont(piysidblFont, diblog2D);
        Font2DHbndlf nfwHbndlf = nfw Font2DHbndlf(dompFont);
        rfturn nfwHbndlf;
    }

    protfdtfd void rfgistfrCompositfFont(String dompositfNbmf,
                                      String[] domponfntFilfNbmfs,
                                      String[] domponfntNbmfs,
                                      int numMftridsSlots,
                                      int[] fxdlusionRbngfs,
                                      int[] fxdlusionMbxIndfx,
                                      boolfbn dfffr) {

        CompositfFont df = nfw CompositfFont(dompositfNbmf,
                                             domponfntFilfNbmfs,
                                             domponfntNbmfs,
                                             numMftridsSlots,
                                             fxdlusionRbngfs,
                                             fxdlusionMbxIndfx, dfffr, tiis);
        bddCompositfToFontList(df, Font2D.FONT_CONFIG_RANK);
        syndironizfd (dompFonts) {
            dompFonts[mbxCompFont++] = df;
        }
    }

    /* Tiis vbribnt is usfd only wifn tif bpplidbtion spfdififs
     * b vbribnt of dompositf fonts wiidi prfffrs lodblf spfdifid or
     * proportionbl fonts.
     */
    protfdtfd stbtid void rfgistfrCompositfFont(String dompositfNbmf,
                                                String[] domponfntFilfNbmfs,
                                                String[] domponfntNbmfs,
                                                int numMftridsSlots,
                                                int[] fxdlusionRbngfs,
                                                int[] fxdlusionMbxIndfx,
                                                boolfbn dfffr,
                                                CondurrfntHbsiMbp<String, Font2D>
                                                bltNbmfCbdif) {

        CompositfFont df = nfw CompositfFont(dompositfNbmf,
                                             domponfntFilfNbmfs,
                                             domponfntNbmfs,
                                             numMftridsSlots,
                                             fxdlusionRbngfs,
                                             fxdlusionMbxIndfx, dfffr,
                                             SunFontMbnbgfr.gftInstbndf());

        /* if tif dbdif ibs bn fxisting dompositf for tiis dbsf, mbkf
         * its ibndlf point to tiis nfw font.
         * Tiis fnsurfs tibt wifn tif bltNbmfCbdif tibt is pbssfd in
         * is tif globbl mbpNbmfCbdif - if wf brf running bs bn bpplidbtion -
         * tibt bny stbtidblly drfbtfd jbvb.bwt.Font instbndfs wiidi blrfbdy
         * ibvf b Font2D instbndf will ibvf tibt rf-dirfdtfd to tif nfw Font
         * on subsfqufnt usfs. Tiis is pbrtidulbrly importbnt for "tif"
         * dffbult font instbndf, or similbr dbsfs wifrf b UI toolkit (fg
         * Swing) ibs dbdifd b jbvb.bwt.Font. Notf tibt if Swing is using
         * b dustom dompositf APIs wiidi updbtf tif stbndbrd dompositfs ibvf
         * no ffffdt - tiis is typidblly tif dbsf only wifn using tif Windows
         * L&F wifrf tifsf APIs would donflidt witi tibt L&F bnywby.
         */
        Font2D oldFont =bltNbmfCbdif.gft(dompositfNbmf.toLowfrCbsf(Lodblf.ENGLISH));
        if (oldFont instbndfof CompositfFont) {
            oldFont.ibndlf.font2D = df;
        }
        bltNbmfCbdif.put(dompositfNbmf.toLowfrCbsf(Lodblf.ENGLISH), df);
    }

    privbtf void bddCompositfToFontList(CompositfFont f, int rbnk) {

        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr().info("Add to Fbmily "+ f.fbmilyNbmf +
                        ", Font " + f.fullNbmf + " rbnk="+rbnk);
        }
        f.sftRbnk(rbnk);
        dompositfFonts.put(f.fullNbmf, f);
        fullNbmfToFont.put(f.fullNbmf.toLowfrCbsf(Lodblf.ENGLISH), f);

        FontFbmily fbmily = FontFbmily.gftFbmily(f.fbmilyNbmf);
        if (fbmily == null) {
            fbmily = nfw FontFbmily(f.fbmilyNbmf, truf, rbnk);
        }
        fbmily.sftFont(f, f.stylf);
    }

    /*
     * Systfms mby ibvf fonts witi tif sbmf nbmf.
     * Wf wbnt to rfgistfr only onf of sudi fonts (bt lfbst until
     * sudi timf bs tifrf migit bf APIs wiidi dbn bddommodbtf > 1).
     * Rbnk is 1) font donfigurbtion fonts, 2) JRE fonts, 3) OT/TT fonts,
     * 4) Typf1 fonts, 5) nbtivf fonts.
     *
     * If tif nfw font ibs tif sbmf nbmf bs tif old font, tif iigifr
     * rbnkfd font gfts bddfd, rfplbding tif lowfr rbnkfd onf.
     * If tif fonts brf of fqubl rbnk, tifn mbkf b spfdibl dbsf of
     * font donfigurbtion rbnk fonts, wiidi brf on dlosfr inspfdtion,
     * OT/TT fonts sudi tibt tif lbrgfr font is rfgistfrfd. Tiis is
     * b ifuristid sindf b font mby bf "lbrgfr" in tif sfnsf of morf
     * dodf points, or bf b lbrgfr "filf" bfdbusf it ibs morf bitmbps.
     * So it is possiblf tibt using filfsizf mby lfbd to lfss glypis, bnd
     * using glypis mby lfbd to lowfr qublity displby. Probbbly numbfr
     * of glypis is tif idfbl, but filfsizf is informbtion wf blrfbdy
     * ibvf bnd is good fnougi for tif known dbsfs.
     * Also don't wbnt to rfgistfr fonts tibt mbtdi JRE font fbmilifs
     * but brf doming from b sourdf otifr tibn tif JRE.
     * Tiis will fnsurf tibt wf will blgoritimidblly stylf tif JRE
     * plbin font bnd gft tif sbmf sft of glypis for bll stylfs.
     *
     * Notf tibt tiis mftiod rfturns b vbluf
     * if it rfturns tif sbmf objfdt bs its brgumfnt tibt mfbns tiis
     * font wbs nfwly rfgistfrfd.
     * If it rfturns b difffrfnt objfdt it mfbns tiis font blrfbdy fxists,
     * bnd you siould usf tibt onf.
     * If it rfturns null mfbns tiis font wbs not rfgistfrfd bnd nonf
     * in tibt nbmf is rfgistfrfd. Tif dbllfr must find b substitutf
     */
    // MACOSX bfgin -- nffd to bddfss tiis in subdlbss
    protfdtfd PiysidblFont bddToFontList(PiysidblFont f, int rbnk) {
    // MACOSX fnd

        String fontNbmf = f.fullNbmf;
        String fbmilyNbmf = f.fbmilyNbmf;
        if (fontNbmf == null || "".fqubls(fontNbmf)) {
            rfturn null;
        }
        if (dompositfFonts.dontbinsKfy(fontNbmf)) {
            /* Don't rfgistfr bny font tibt ibs tif sbmf nbmf bs b dompositf */
            rfturn null;
        }
        f.sftRbnk(rbnk);
        if (!piysidblFonts.dontbinsKfy(fontNbmf)) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().info("Add to Fbmily "+fbmilyNbmf +
                            ", Font " + fontNbmf + " rbnk="+rbnk);
            }
            piysidblFonts.put(fontNbmf, f);
            FontFbmily fbmily = FontFbmily.gftFbmily(fbmilyNbmf);
            if (fbmily == null) {
                fbmily = nfw FontFbmily(fbmilyNbmf, fblsf, rbnk);
                fbmily.sftFont(f, f.stylf);
            } flsf {
                fbmily.sftFont(f, f.stylf);
            }
            fullNbmfToFont.put(fontNbmf.toLowfrCbsf(Lodblf.ENGLISH), f);
            rfturn f;
        } flsf {
            PiysidblFont nfwFont = f;
            PiysidblFont oldFont = piysidblFonts.gft(fontNbmf);
            if (oldFont == null) {
                rfturn null;
            }
            /* If tif nfw font is of bn fqubl or iigifr rbnk, it is b
             * dbndidbtf to rfplbdf tif durrfnt onf, subjfdt to furtifr tfsts.
             */
            if (oldFont.gftRbnk() >= rbnk) {

                /* All fonts initiblisf tifir mbppfr wifn first
                 * usfd. If tif mbppfr is non-null tifn tiis font
                 * ibs bffn bddfssfd bt lfbst ondf. In tibt dbsf
                 * do not rfplbdf it. Tiis mby bf ovfrly stringfnt,
                 * but its probbbly bfttfr not to rfplbdf b font tibt
                 * somfonf is blrfbdy using witiout b dompflling rfbson.
                 * Additionblly tif primbry dbsf wifrf it is known
                 * tiis bfibviour is importbnt is in dfrtbin dompositf
                 * fonts, bnd sindf bll tif domponfnts of b givfn
                 * dompositf brf usublly initiblisfd togftifr tiis
                 * is unlikfly. For tiis to bf b problfm, tifrf would
                 * ibvf to bf b dbsf wifrf two difffrfnt dompositfs usfd
                 * difffrfnt vfrsions of tif sbmf-nbmfd font, bnd tify
                 * wfrf initiblisfd bnd usfd bt sfpbrbtf timfs.
                 * In tibt dbsf wf dontinuf on bnd bllow tif nfw font to
                 * bf instbllfd, but rfplbdfFont will dontinuf to bllow
                 * tif originbl font to bf usfd in Compositf fonts.
                 */
                if (oldFont.mbppfr != null && rbnk > Font2D.FONT_CONFIG_RANK) {
                    rfturn oldFont;
                }

                /* Normblly wf rfquirf b iigifr rbnk to rfplbdf b font,
                 * but bs b spfdibl dbsf, if tif two fonts brf tif sbmf rbnk,
                 * bnd brf instbndfs of TrufTypfFont wf wbnt tif
                 * morf domplftf (lbrgfr) onf.
                 */
                if (oldFont.gftRbnk() == rbnk) {
                    if (oldFont instbndfof TrufTypfFont &&
                        nfwFont instbndfof TrufTypfFont) {
                        TrufTypfFont oldTTFont = (TrufTypfFont)oldFont;
                        TrufTypfFont nfwTTFont = (TrufTypfFont)nfwFont;
                        if (oldTTFont.filfSizf >= nfwTTFont.filfSizf) {
                            rfturn oldFont;
                        }
                    } flsf {
                        rfturn oldFont;
                    }
                }
                /* Don't rfplbdf fvfr JRE fonts.
                 * Tiis tfst is in dbsf b font donfigurbtion rfffrfndfs
                 * b Ludidb font, wiidi ibs bffn mbppfd to b Ludidb
                 * from tif iost O/S. Tif bssumption ifrf is tibt bny
                 * sudi font donfigurbtion filf is probbbly indorrfdt, or
                 * tif iost O/S vfrsion is for tif usf of AWT.
                 * In otifr words if wf rfbdi ifrf, tifrf's b possiblf
                 * problfm witi our dioidf of font donfigurbtion fonts.
                 */
                if (oldFont.plbtNbmf.stbrtsWiti(jrfFontDirNbmf)) {
                    if (FontUtilitifs.isLogging()) {
                        FontUtilitifs.gftLoggfr()
                              .wbrning("Unfxpfdtfd bttfmpt to rfplbdf b JRE " +
                                       " font " + fontNbmf + " from " +
                                        oldFont.plbtNbmf +
                                       " witi " + nfwFont.plbtNbmf);
                    }
                    rfturn oldFont;
                }

                if (FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr()
                          .info("Rfplbdf in Fbmily " + fbmilyNbmf +
                                ",Font " + fontNbmf + " nfw rbnk="+rbnk +
                                " from " + oldFont.plbtNbmf +
                                " witi " + nfwFont.plbtNbmf);
                }
                rfplbdfFont(oldFont, nfwFont);
                piysidblFonts.put(fontNbmf, nfwFont);
                fullNbmfToFont.put(fontNbmf.toLowfrCbsf(Lodblf.ENGLISH),
                                   nfwFont);

                FontFbmily fbmily = FontFbmily.gftFbmily(fbmilyNbmf);
                if (fbmily == null) {
                    fbmily = nfw FontFbmily(fbmilyNbmf, fblsf, rbnk);
                    fbmily.sftFont(nfwFont, nfwFont.stylf);
                } flsf {
                    fbmily.sftFont(nfwFont, nfwFont.stylf);
                }
                rfturn nfwFont;
            } flsf {
                rfturn oldFont;
            }
        }
    }

    publid Font2D[] gftRfgistfrfdFonts() {
        PiysidblFont[] piysFonts = gftPiysidblFonts();
        int mdf = mbxCompFont; /* for MT-sbffty */
        Font2D[] rfgFonts = nfw Font2D[piysFonts.lfngti+mdf];
        Systfm.brrbydopy(dompFonts, 0, rfgFonts, 0, mdf);
        Systfm.brrbydopy(piysFonts, 0, rfgFonts, mdf, piysFonts.lfngti);
        rfturn rfgFonts;
    }

    protfdtfd PiysidblFont[] gftPiysidblFonts() {
        rfturn piysidblFonts.vblufs().toArrby(nfw PiysidblFont[0]);
    }


    /* Tif dlbss FontRfgistrbtionInfo is usfd wifn b dlifnt sbys not
     * to rfgistfr b font immfdibtfly. Tiis mfdibnism is usfd to dfffr
     * initiblisbtion of bll tif domponfnts of dompositf fonts bt JRE
     * stbrt-up. Tif CompositfFont dlbss is "bwbrf" of tiis bnd wifn it
     * is first usfd it bsks for tif rfgistrbtion of its domponfnts.
     * Also in tif fvfnt tibt bny piysidbl font is rfqufstfd tif
     * dfffrrfd fonts brf initiblisfd bfforf triggfring b sfbrdi of tif
     * systfm.
     * Two mbps brf usfd. Onf to trbdk tif dfffrrfd fonts. Tif
     * otifr to trbdk tif fonts tibt ibvf bffn initiblisfd tirougi tiis
     * mfdibnism.
     */

    privbtf stbtid finbl dlbss FontRfgistrbtionInfo {

        String fontFilfPbti;
        String[] nbtivfNbmfs;
        int fontFormbt;
        boolfbn jbvbRbstfrizfr;
        int fontRbnk;

        FontRfgistrbtionInfo(String fontPbti, String[] nbmfs, int formbt,
                             boolfbn usfJbvbRbstfrizfr, int rbnk) {
            tiis.fontFilfPbti = fontPbti;
            tiis.nbtivfNbmfs = nbmfs;
            tiis.fontFormbt = formbt;
            tiis.jbvbRbstfrizfr = usfJbvbRbstfrizfr;
            tiis.fontRbnk = rbnk;
        }
    }

    privbtf finbl CondurrfntHbsiMbp<String, FontRfgistrbtionInfo>
        dfffrrfdFontFilfs =
        nfw CondurrfntHbsiMbp<String, FontRfgistrbtionInfo>();
    privbtf finbl CondurrfntHbsiMbp<String, Font2DHbndlf>
        initiblisfdFonts = nfw CondurrfntHbsiMbp<String, Font2DHbndlf>();

    /* Rfmind: possibly fnibndf initiblisfDfffrrfdFonts() to bf
     * optionblly givfn b nbmf bnd b stylf bnd it dould stop wifn it
     * finds tibt font - but tiis would bf b problfm if two of tif
     * fonts rfffrfndf tif sbmf font fbdf nbmf (df tif Solbris
     * furo fonts).
     */
    protfdtfd syndironizfd void initiblisfDfffrrfdFonts() {
        for (String filfNbmf : dfffrrfdFontFilfs.kfySft()) {
            initiblisfDfffrrfdFont(filfNbmf);
        }
    }

    protfdtfd syndironizfd void rfgistfrDfffrrfdJREFonts(String jrfDir) {
        for (FontRfgistrbtionInfo info : dfffrrfdFontFilfs.vblufs()) {
            if (info.fontFilfPbti != null &&
                info.fontFilfPbti.stbrtsWiti(jrfDir)) {
                initiblisfDfffrrfdFont(info.fontFilfPbti);
            }
        }
    }

    publid boolfbn isDfffrrfdFont(String filfNbmf) {
        rfturn dfffrrfdFontFilfs.dontbinsKfy(filfNbmf);
    }

    /* Wf kffp b mbp of tif filfs wiidi dontbin tif Ludidb fonts so wf
     * don't nffd to sfbrdi for tifm.
     * But sindf wf know wibt fonts tifsf filfs dontbin, wf dbn blso bvoid
     * opfning tifm to look for b font nbmf wf don't rfdognisf - sff
     * findDfffrrfdFont().
     * For typidbl dbsfs wifrf tif font isn't b JRE onf tif ovfrifbd is
     * tiis mftiod dbll, HbsiMbp.gft() bnd null rfffrfndf tfst, tifn
     * b boolfbn tfst of noOtifrJREFontFilfs.
     */
    publid
    /*privbtf*/ PiysidblFont findJREDfffrrfdFont(String nbmf, int stylf) {

        PiysidblFont piysidblFont;
        String nbmfAndStylf = nbmf.toLowfrCbsf(Lodblf.ENGLISH) + stylf;
        String filfNbmf = jrfFontMbp.gft(nbmfAndStylf);
        if (filfNbmf != null) {
            filfNbmf = jrfFontDirNbmf + Filf.sfpbrbtor + filfNbmf;
            if (dfffrrfdFontFilfs.gft(filfNbmf) != null) {
                piysidblFont = initiblisfDfffrrfdFont(filfNbmf);
                if (piysidblFont != null &&
                    (piysidblFont.gftFontNbmf(null).fqublsIgnorfCbsf(nbmf) ||
                     piysidblFont.gftFbmilyNbmf(null).fqublsIgnorfCbsf(nbmf))
                    && piysidblFont.stylf == stylf) {
                    rfturn piysidblFont;
                }
            }
        }

        /* Itfrbtf ovfr tif dfffrrfd font filfs looking for bny in tif
         * jrf dirfdtory tibt wf didn't rfdognisf, opfn fbdi of tifsf.
         * In blmost bll instbllbtions tiis will quidkly fbll tirougi
         * bfdbusf only tif Ludidbs will bf prfsfnt bnd jrfOtifrFontFilfs
         * will bf fmpty.
         * noOtifrJREFontFilfs is usfd so wf dbn skip tiis blodk bs soon
         * bs its dftfrminfd tibt its not nffdfd - blmost blwbys bftfr tif
         * vfry first timf tirougi.
         */
        if (noOtifrJREFontFilfs) {
            rfturn null;
        }
        syndironizfd (jrfLudidbFontFilfs) {
            if (jrfOtifrFontFilfs == null) {
                HbsiSft<String> otifrFontFilfs = nfw HbsiSft<String>();
                for (String dfffrrfdFilf : dfffrrfdFontFilfs.kfySft()) {
                    Filf filf = nfw Filf(dfffrrfdFilf);
                    String dir = filf.gftPbrfnt();
                    String fnbmf = filf.gftNbmf();
                    /* skip nbmfs wiidi brfn't bbsolutf, brfn't in tif JRE
                     * dirfdtory, or brf known Ludidb fonts.
                     */
                    if (dir == null ||
                        !dir.fqubls(jrfFontDirNbmf) ||
                        jrfLudidbFontFilfs.dontbins(fnbmf)) {
                        dontinuf;
                    }
                    otifrFontFilfs.bdd(dfffrrfdFilf);
                }
                jrfOtifrFontFilfs = otifrFontFilfs.toArrby(STR_ARRAY);
                if (jrfOtifrFontFilfs.lfngti == 0) {
                    noOtifrJREFontFilfs = truf;
                }
            }

            for (int i=0; i<jrfOtifrFontFilfs.lfngti;i++) {
                filfNbmf = jrfOtifrFontFilfs[i];
                if (filfNbmf == null) {
                    dontinuf;
                }
                jrfOtifrFontFilfs[i] = null;
                piysidblFont = initiblisfDfffrrfdFont(filfNbmf);
                if (piysidblFont != null &&
                    (piysidblFont.gftFontNbmf(null).fqublsIgnorfCbsf(nbmf) ||
                     piysidblFont.gftFbmilyNbmf(null).fqublsIgnorfCbsf(nbmf))
                    && piysidblFont.stylf == stylf) {
                    rfturn piysidblFont;
                }
            }
        }

        rfturn null;
    }

    /* Tiis skips JRE instbllfd fonts. */
    privbtf PiysidblFont findOtifrDfffrrfdFont(String nbmf, int stylf) {
        for (String filfNbmf : dfffrrfdFontFilfs.kfySft()) {
            Filf filf = nfw Filf(filfNbmf);
            String dir = filf.gftPbrfnt();
            String fnbmf = filf.gftNbmf();
            if (dir != null &&
                dir.fqubls(jrfFontDirNbmf) &&
                jrfLudidbFontFilfs.dontbins(fnbmf)) {
                dontinuf;
            }
            PiysidblFont piysidblFont = initiblisfDfffrrfdFont(filfNbmf);
            if (piysidblFont != null &&
                (piysidblFont.gftFontNbmf(null).fqublsIgnorfCbsf(nbmf) ||
                piysidblFont.gftFbmilyNbmf(null).fqublsIgnorfCbsf(nbmf)) &&
                piysidblFont.stylf == stylf) {
                rfturn piysidblFont;
            }
        }
        rfturn null;
    }

    privbtf PiysidblFont findDfffrrfdFont(String nbmf, int stylf) {

        PiysidblFont piysidblFont = findJREDfffrrfdFont(nbmf, stylf);
        if (piysidblFont != null) {
            rfturn piysidblFont;
        } flsf {
            rfturn findOtifrDfffrrfdFont(nbmf, stylf);
        }
    }

    publid void rfgistfrDfffrrfdFont(String filfNbmfKfy,
                                     String fullPbtiNbmf,
                                     String[] nbtivfNbmfs,
                                     int fontFormbt,
                                     boolfbn usfJbvbRbstfrizfr,
                                     int fontRbnk) {
        FontRfgistrbtionInfo rfgInfo =
            nfw FontRfgistrbtionInfo(fullPbtiNbmf, nbtivfNbmfs, fontFormbt,
                                     usfJbvbRbstfrizfr, fontRbnk);
        dfffrrfdFontFilfs.put(filfNbmfKfy, rfgInfo);
    }


    publid syndironizfd
         PiysidblFont initiblisfDfffrrfdFont(String filfNbmfKfy) {

        if (filfNbmfKfy == null) {
            rfturn null;
        }
        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr()
                            .info("Opfning dfffrrfd font filf " + filfNbmfKfy);
        }

        PiysidblFont piysidblFont;
        FontRfgistrbtionInfo rfgInfo = dfffrrfdFontFilfs.gft(filfNbmfKfy);
        if (rfgInfo != null) {
            dfffrrfdFontFilfs.rfmovf(filfNbmfKfy);
            piysidblFont = rfgistfrFontFilf(rfgInfo.fontFilfPbti,
                                            rfgInfo.nbtivfNbmfs,
                                            rfgInfo.fontFormbt,
                                            rfgInfo.jbvbRbstfrizfr,
                                            rfgInfo.fontRbnk);


            if (piysidblFont != null) {
                /* Storf tif ibndlf, so tibt if b font is bbd, wf
                 * rftrifvf tif substitutfd font.
                 */
                initiblisfdFonts.put(filfNbmfKfy, piysidblFont.ibndlf);
            } flsf {
                initiblisfdFonts.put(filfNbmfKfy,
                                     gftDffbultPiysidblFont().ibndlf);
            }
        } flsf {
            Font2DHbndlf ibndlf = initiblisfdFonts.gft(filfNbmfKfy);
            if (ibndlf == null) {
                /* Probbbly siouldn't ibppfn, but just in dbsf */
                piysidblFont = gftDffbultPiysidblFont();
            } flsf {
                piysidblFont = (PiysidblFont)(ibndlf.font2D);
            }
        }
        rfturn piysidblFont;
    }

    publid boolfbn isRfgistfrfdFontFilf(String nbmf) {
        rfturn rfgistfrfdFonts.dontbinsKfy(nbmf);
    }

    publid PiysidblFont gftRfgistfrfdFontFilf(String nbmf) {
        rfturn rfgistfrfdFonts.gft(nbmf);
    }

    /* Notf tibt tif rfturn vbluf from tiis mftiod is not blwbys
     * dfrivfd from tiis filf, bnd mby bf null. Sff bddToFontList for
     * somf fxplbnbtion of tiis.
     */
    publid PiysidblFont rfgistfrFontFilf(String filfNbmf,
                                         String[] nbtivfNbmfs,
                                         int fontFormbt,
                                         boolfbn usfJbvbRbstfrizfr,
                                         int fontRbnk) {

        PiysidblFont rfgFont = rfgistfrfdFonts.gft(filfNbmf);
        if (rfgFont != null) {
            rfturn rfgFont;
        }

        PiysidblFont piysidblFont = null;
        try {
            String nbmf;

            switdi (fontFormbt) {

            dbsf FONTFORMAT_TRUETYPE:
                int fn = 0;
                TrufTypfFont ttf;
                do {
                    ttf = nfw TrufTypfFont(filfNbmf, nbtivfNbmfs, fn++,
                                           usfJbvbRbstfrizfr);
                    PiysidblFont pf = bddToFontList(ttf, fontRbnk);
                    if (piysidblFont == null) {
                        piysidblFont = pf;
                    }
                }
                wiilf (fn < ttf.gftFontCount());
                brfbk;

            dbsf FONTFORMAT_TYPE1:
                Typf1Font t1f = nfw Typf1Font(filfNbmf, nbtivfNbmfs);
                piysidblFont = bddToFontList(t1f, fontRbnk);
                brfbk;

            dbsf FONTFORMAT_NATIVE:
                NbtivfFont nf = nfw NbtivfFont(filfNbmf, fblsf);
                piysidblFont = bddToFontList(nf, fontRbnk);
                brfbk;
            dffbult:

            }
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                      .info("Rfgistfrfd filf " + filfNbmf + " bs font " +
                            piysidblFont + " rbnk="  + fontRbnk);
            }
        } dbtdi (FontFormbtExdfption fff) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().wbrning("Unusbblf font: " +
                               filfNbmf + " " + fff.toString());
            }
        }
        if (piysidblFont != null &&
            fontFormbt != FONTFORMAT_NATIVE) {
            rfgistfrfdFonts.put(filfNbmf, piysidblFont);
        }
        rfturn piysidblFont;
    }

    publid void rfgistfrFonts(String[] filfNbmfs,
                              String[][] nbtivfNbmfs,
                              int fontCount,
                              int fontFormbt,
                              boolfbn usfJbvbRbstfrizfr,
                              int fontRbnk, boolfbn dfffr) {

        for (int i=0; i < fontCount; i++) {
            if (dfffr) {
                rfgistfrDfffrrfdFont(filfNbmfs[i],filfNbmfs[i], nbtivfNbmfs[i],
                                     fontFormbt, usfJbvbRbstfrizfr, fontRbnk);
            } flsf {
                rfgistfrFontFilf(filfNbmfs[i], nbtivfNbmfs[i],
                                 fontFormbt, usfJbvbRbstfrizfr, fontRbnk);
            }
        }
    }

    /*
     * Tiis is tif Piysidbl font usfd wifn somf otifr font on tif systfm
     * dbn't bf lodbtfd. Tifrf ibs to bf bt lfbst onf font or tif font
     * systfm is not usfful bnd tif grbpiids fnvironmfnt dbnnot sustbin
     * tif Jbvb plbtform.
     */
    publid PiysidblFont gftDffbultPiysidblFont() {
        if (dffbultPiysidblFont == null) {
            /* findFont2D will lobd bll fonts bfforf giving up tif sfbrdi.
             * If tif JRE Ludidb isn't found (fg bfdbusf tif JRE fonts
             * dirfdtory is missing), it dould find bnotifr vfrsion of Ludidb
             * from tif iost systfm. Tiis is OK bfdbusf bt tibt point wf brf
             * trying to grbdffully ibndlf/rfdovfr from b systfm
             * misdonfigurbtion bnd tiis is probbbly b rfbsonbblf substitution.
             */
            dffbultPiysidblFont = (PiysidblFont)
                findFont2D("Ludidb Sbns Rfgulbr", Font.PLAIN, NO_FALLBACK);
            if (dffbultPiysidblFont == null) {
                dffbultPiysidblFont = (PiysidblFont)
                    findFont2D("Aribl", Font.PLAIN, NO_FALLBACK);
            }
            if (dffbultPiysidblFont == null) {
                /* Bfdbusf of tif findFont2D dbll bbovf, if wf rfbdi ifrf, wf
                 * know bll fonts ibvf blrfbdy bffn lobdfd, just bddfpt bny
                 * mbtdi bt tiis point. If tiis fbils wf brf in rfbl troublf
                 * bnd I don't know iow to rfdovfr from tifrf bfing bbsolutfly
                 * no fonts bnywifrf on tif systfm.
                 */
                Itfrbtor<PiysidblFont> i = piysidblFonts.vblufs().itfrbtor();
                if (i.ibsNfxt()) {
                    dffbultPiysidblFont = i.nfxt();
                } flsf {
                    tirow nfw Error("Probbblf fbtbl frror:No fonts found.");
                }
            }
        }
        rfturn dffbultPiysidblFont;
    }

    publid Font2D gftDffbultLogidblFont(int stylf) {
        rfturn findFont2D("diblog", stylf, NO_FALLBACK);
    }

    /*
     * rfturn String rfprfsfntbtion of stylf prfpfndfd witi "."
     * Tiis is usfful for pfrformbndf to bvoid unnfdfssbry string opfrbtions.
     */
    privbtf stbtid String dotStylfStr(int num) {
        switdi(num){
          dbsf Font.BOLD:
            rfturn ".bold";
          dbsf Font.ITALIC:
            rfturn ".itblid";
          dbsf Font.ITALIC | Font.BOLD:
            rfturn ".bolditblid";
          dffbult:
            rfturn ".plbin";
        }
    }

    /* Tiis is implfmfntfd only on windows bnd is dbllfd from dodf tibt
     * fxfdutfs only on windows. Tiis isn't prftty but its not b prfdfdfnt
     * in tiis filf. Tiis vfry probbbly siould bf dlfbnfd up bt somf point.
     */
    protfdtfd void
        populbtfFontFilfNbmfMbp(HbsiMbp<String,String> fontToFilfMbp,
                                HbsiMbp<String,String> fontToFbmilyNbmfMbp,
                                HbsiMbp<String,ArrbyList<String>>
                                fbmilyToFontListMbp,
                                Lodblf lodblf) {
    }

    /* Obtbinfd from Plbtform APIs (windows only)
     * Mbp from lowfr-dbsf font full nbmf to bbsfnbmf of font filf.
     * Eg "bribl bold" -> ARIALBD.TTF.
     * For TTC filfs, tifrf is b mbpping for fbdi font in tif filf.
     */
    privbtf HbsiMbp<String,String> fontToFilfMbp = null;

    /* Obtbinfd from Plbtform APIs (windows only)
     * Mbp from lowfr-dbsf font full nbmf to tif nbmf of its font fbmily
     * Eg "bribl bold" -> "Aribl"
     */
    privbtf HbsiMbp<String,String> fontToFbmilyNbmfMbp = null;

    /* Obtbinfd from Plbtform APIs (windows only)
     * Mbp from b lowfr-dbsf fbmily nbmf to b list of full nbmfs of
     * tif mfmbfr fonts, fg:
     * "bribl" -> ["Aribl", "Aribl Bold", "Aribl Itblid","Aribl Bold Itblid"]
     */
    privbtf HbsiMbp<String,ArrbyList<String>> fbmilyToFontListMbp= null;

    /* Tif dirfdtorifs wiidi dontbin plbtform fonts */
    privbtf String[] pbtiDirs = null;

    privbtf boolfbn ibvfCifdkfdUnrfffrfndfdFontFilfs;

    privbtf String[] gftFontFilfsFromPbti(boolfbn noTypf1) {
        finbl FilfnbmfFiltfr filtfr;
        if (noTypf1) {
            filtfr = ttFiltfr;
        } flsf {
            filtfr = nfw TTorT1Filtfr();
        }
        rfturn (String[])AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                if (pbtiDirs.lfngti == 1) {
                    Filf dir = nfw Filf(pbtiDirs[0]);
                    String[] filfs = dir.list(filtfr);
                    if (filfs == null) {
                        rfturn nfw String[0];
                    }
                    for (int f=0; f<filfs.lfngti; f++) {
                        filfs[f] = filfs[f].toLowfrCbsf();
                    }
                    rfturn filfs;
                } flsf {
                    ArrbyList<String> filfList = nfw ArrbyList<String>();
                    for (int i = 0; i< pbtiDirs.lfngti; i++) {
                        Filf dir = nfw Filf(pbtiDirs[i]);
                        String[] filfs = dir.list(filtfr);
                        if (filfs == null) {
                            dontinuf;
                        }
                        for (int f=0; f<filfs.lfngti ; f++) {
                            filfList.bdd(filfs[f].toLowfrCbsf());
                        }
                    }
                    rfturn filfList.toArrby(STR_ARRAY);
                }
            }
        });
    }

    /* Tiis is nffdfd sindf somf windows rfgistry nbmfs don't mbtdi
     * tif font nbmfs.
     * - UPC stylfd font nbmfs ibvf b doublf spbdf, but tif
     * rfgistry fntry mbpping to b filf dofsn't.
     * - Mbrlftt is in b iiddfn filf not listfd in tif rfgistry
     * - Tif rfgistry bdvfrtisfs tibt tif filf dbvid.ttf dontbins b
     * font witi tif full nbmf "Dbvid Rfgulbr" wifn in fbdt its
     * just "Dbvid".
     * Dirfdtly fix up tifsf known dbsfs bs tiis is fbstfr.
     * If b font wiidi dofsn't mbtdi tifsf known dbsfs ibs no filf,
     * it mby bf b font tibt ibs bffn tfmporbrily bddfd to tif known sft
     * or it mby bf bn instbllfd font witi b missing rfgistry fntry.
     * Instbllfd fonts brf tiosf in tif windows font dirfdtorifs.
     * Mbkf b bfst fffort bttfmpt to lodbtf tifsf.
     * Wf obtbin tif list of TrufTypf fonts in tifsf dirfdtorifs bnd
     * filtfr out bll tif font filfs wf blrfbdy know bbout from tif rfgistry.
     * Wibt rfmbins mby bf "bbd" fonts, duplidbtf fonts, or pfribps tif
     * missing font(s) wf brf looking for.
     * Opfn fbdi of tifsf filfs to find out.
     */
    privbtf void rfsolvfWindowsFonts() {

        ArrbyList<String> unmbppfdFontNbmfs = null;
        for (String font : fontToFbmilyNbmfMbp.kfySft()) {
            String filf = fontToFilfMbp.gft(font);
            if (filf == null) {
                if (font.indfxOf("  ") > 0) {
                    String nfwNbmf = font.rfplbdfFirst("  ", " ");
                    filf = fontToFilfMbp.gft(nfwNbmf);
                    /* If tiis nbmf fxists bnd isn't for b vblid nbmf
                     * rfplbdf tif mbpping to tif filf witi tiis font
                     */
                    if (filf != null &&
                        !fontToFbmilyNbmfMbp.dontbinsKfy(nfwNbmf)) {
                        fontToFilfMbp.rfmovf(nfwNbmf);
                        fontToFilfMbp.put(font, filf);
                    }
                } flsf if (font.fqubls("mbrlftt")) {
                    fontToFilfMbp.put(font, "mbrlftt.ttf");
                } flsf if (font.fqubls("dbvid")) {
                    filf = fontToFilfMbp.gft("dbvid rfgulbr");
                    if (filf != null) {
                        fontToFilfMbp.rfmovf("dbvid rfgulbr");
                        fontToFilfMbp.put("dbvid", filf);
                    }
                } flsf {
                    if (unmbppfdFontNbmfs == null) {
                        unmbppfdFontNbmfs = nfw ArrbyList<String>();
                    }
                    unmbppfdFontNbmfs.bdd(font);
                }
            }
        }

        if (unmbppfdFontNbmfs != null) {
            HbsiSft<String> unmbppfdFontFilfs = nfw HbsiSft<String>();

            /* Evfry font kfy in fontToFilfMbp ougit to dorrfspond to b
             * font kfy in fontToFbmilyNbmfMbp. Entrifs tibt don't sffm
             * to dorrfspond brf likfly fonts tibt wfrf nbmfd difffrfntly
             * by GDI tibn in tif rfgistry. Onf known dbusf of tiis is wifn
             * Windows ibs ibd its rfgionbl sfttings dibngfd so tibt from
             * GDI wf gft b lodblisfd (fg Ciinfsf or Jbpbnfsf) nbmf for tif
             * font, but tif rfgistry rftbins tif Englisi vfrsion of tif nbmf
             * tibt dorrfspondfd to tif "instbll" lodblf for windows.
             * Sindf wf brf in tiis dodf blodk bfdbusf tifrf brf unmbppfd
             * font nbmfs, wf dbn look to find unusfd font->filf mbppings
             * bnd tifn opfn tif filfs to rfbd tif nbmfs. Wf don't gfnfrblly
             * wbnt to opfn font filfs, bs its b pfrformbndf iit, but tiis
             * oddurs only for b smbll numbfr of fonts on spfdifid systfm
             * donfigs - if is bflifvfd tibt b "truf" Jbpbnfsf windows would
             * ibvf JA nbmfs in tif rfgistry too.
             * Clonf fontToFilfMbp bnd rfmovf from tif dlonf bll kfys wiidi
             * mbtdi b fontToFbmilyNbmfMbp kfy. Wibt rfmbins mbps to tif
             * filfs wf wbnt to opfn to find tif fonts GDI rfturnfd.
             * A font in sudi b filf is bddfd to tif fontToFilfMbp bftfr
             * difdking its onf of tif unmbppfdFontNbmfs wf brf looking for.
             * Tif originbl nbmf tibt didn't mbp is rfmovfd from fontToFilfMbp
             * so fssfntiblly tiis "fixfs up" fontToFilfMbp to usf tif sbmf
             * nbmf bs GDI.
             * Also notf tibt typidblly tif fonts for wiidi tiis oddurs in
             * CJK lodblfs brf TTC fonts bnd not bll fonts in b TTC mby ibvf
             * lodblisfd nbmfs. Eg MSGOTHIC.TTC dontbins 3 fonts bnd onf of
             * tifm "MS UI Gotiid" ibs no JA nbmf wifrfbs tif otifr two do.
             * So not fvfry font in tifsf filfs is unmbppfd or nfw.
             */
            @SupprfssWbrnings("undifdkfd")
            HbsiMbp<String,String> ffmbpCopy =
                (HbsiMbp<String,String>)(fontToFilfMbp.dlonf());
            for (String kfy : fontToFbmilyNbmfMbp.kfySft()) {
                ffmbpCopy.rfmovf(kfy);
            }
            for (String kfy : ffmbpCopy.kfySft()) {
                unmbppfdFontFilfs.bdd(ffmbpCopy.gft(kfy));
                fontToFilfMbp.rfmovf(kfy);
            }

            rfsolvfFontFilfs(unmbppfdFontFilfs, unmbppfdFontNbmfs);

            /* If tifrf brf still unmbppfd font nbmfs, tiis mfbns tifrf's
             * somftiing tibt wbsn't in tif rfgistry. Wf nffd to gft bll
             * tif font filfs dirfdtly bnd look bt tif onfs tibt wfrfn't
             * found in tif rfgistry.
             */
            if (unmbppfdFontNbmfs.sizf() > 0) {

                /* gftFontFilfsFromPbti() rfturns bll lowfr dbsf nbmfs.
                 * To dompbrf wf blso nffd lowfr dbsf
                 * vfrsions of tif nbmfs from tif rfgistry.
                 */
                ArrbyList<String> rfgistryFilfs = nfw ArrbyList<String>();

                for (String rfgFilf : fontToFilfMbp.vblufs()) {
                    rfgistryFilfs.bdd(rfgFilf.toLowfrCbsf());
                }
                /* Wf don't look for Typf1 filfs ifrf bs windows will
                 * not fnumfrbtf tifsf, so brfn't usfful in rfdondiling
                 * GDI's unmbppfd filfs. Wf do find tifsf lbtfr wifn
                 * wf fnumfrbtf bll fonts.
                 */
                for (String pbtiFilf : gftFontFilfsFromPbti(truf)) {
                    if (!rfgistryFilfs.dontbins(pbtiFilf)) {
                        unmbppfdFontFilfs.bdd(pbtiFilf);
                    }
                }

                rfsolvfFontFilfs(unmbppfdFontFilfs, unmbppfdFontNbmfs);
            }

            /* rfmovf from tif sft of nbmfs tibt will bf rfturnfd to tif
             * usfr bny fonts tibt dbn't bf mbppfd to filfs.
             */
            if (unmbppfdFontNbmfs.sizf() > 0) {
                int sz = unmbppfdFontNbmfs.sizf();
                for (int i=0; i<sz; i++) {
                    String nbmf = unmbppfdFontNbmfs.gft(i);
                    String fbmilyNbmf = fontToFbmilyNbmfMbp.gft(nbmf);
                    if (fbmilyNbmf != null) {
                        ArrbyList<String> fbmily = fbmilyToFontListMbp.gft(fbmilyNbmf);
                        if (fbmily != null) {
                            if (fbmily.sizf() <= 1) {
                                fbmilyToFontListMbp.rfmovf(fbmilyNbmf);
                            }
                        }
                    }
                    fontToFbmilyNbmfMbp.rfmovf(nbmf);
                    if (FontUtilitifs.isLogging()) {
                        FontUtilitifs.gftLoggfr()
                                             .info("No filf for font:" + nbmf);
                    }
                }
            }
        }
    }

    /**
     * In somf dbsfs windows mby ibvf fonts in tif fonts foldfr tibt
     * don't siow up in tif rfgistry or in tif GDI dblls to fnumfrbtf fonts.
     * Tif only wby to find tifsf is to list tif dirfdtory. Wf invokf tiis
     * only in gftAllFonts/Fbmilifs, so most sfbrdifs for b spfdifid
     * font tibt is sbtisfifd by tif GDI/rfgistry dblls don't tbkf tif
     * bdditionbl iit of listing tif dirfdtory. Tiis iit is smbll fnougi
     * tibt its not signifidbnt in tifsf 'fnumfrbtf bll tif fonts' dbsfs.
     * Tif bbsid bpprobdi is to dross-rfffrfndf tif filfs windows found
     * witi tif onfs in tif dirfdtory listing bpprobdi, bnd for fbdi
     * in tif lbttfr list tibt is missing from tif formfr list, rfgistfr it.
     */
    privbtf syndironizfd void difdkForUnrfffrfndfdFontFilfs() {
        if (ibvfCifdkfdUnrfffrfndfdFontFilfs) {
            rfturn;
        }
        ibvfCifdkfdUnrfffrfndfdFontFilfs = truf;
        if (!FontUtilitifs.isWindows) {
            rfturn;
        }
        /* gftFontFilfsFromPbti() rfturns bll lowfr dbsf nbmfs.
         * To dompbrf wf blso nffd lowfr dbsf
         * vfrsions of tif nbmfs from tif rfgistry.
         */
        ArrbyList<String> rfgistryFilfs = nfw ArrbyList<String>();
        for (String rfgFilf : fontToFilfMbp.vblufs()) {
            rfgistryFilfs.bdd(rfgFilf.toLowfrCbsf());
        }

        /* To bvoid bny issufs witi dondurrfnt modifidbtion, drfbtf
         * dopifs of tif fxisting mbps, bdd tif nfw fonts into tifsf
         * bnd tifn rfplbdf tif rfffrfndfs to tif old onfs witi tif
         * nfw mbps. CondurrfntHbsimbp is bnotifr option but its b lot
         * morf dibngfs bnd witi tiis fxdfption, tifsf mbps brf intfndfd
         * to bf stbtid.
         */
        HbsiMbp<String,String> fontToFilfMbp2 = null;
        HbsiMbp<String,String> fontToFbmilyNbmfMbp2 = null;
        HbsiMbp<String,ArrbyList<String>> fbmilyToFontListMbp2 = null;;

        for (String pbtiFilf : gftFontFilfsFromPbti(fblsf)) {
            if (!rfgistryFilfs.dontbins(pbtiFilf)) {
                if (FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr()
                                 .info("Found non-rfgistry filf : " + pbtiFilf);
                }
                PiysidblFont f = rfgistfrFontFilf(gftPbtiNbmf(pbtiFilf));
                if (f == null) {
                    dontinuf;
                }
                if (fontToFilfMbp2 == null) {
                    fontToFilfMbp2 = nfw HbsiMbp<String,String>(fontToFilfMbp);
                    fontToFbmilyNbmfMbp2 =
                        nfw HbsiMbp<String,String>(fontToFbmilyNbmfMbp);
                    fbmilyToFontListMbp2 = nfw
                        HbsiMbp<String,ArrbyList<String>>(fbmilyToFontListMbp);
                }
                String fontNbmf = f.gftFontNbmf(null);
                String fbmily = f.gftFbmilyNbmf(null);
                String fbmilyLC = fbmily.toLowfrCbsf();
                fontToFbmilyNbmfMbp2.put(fontNbmf, fbmily);
                fontToFilfMbp2.put(fontNbmf, pbtiFilf);
                ArrbyList<String> fonts = fbmilyToFontListMbp2.gft(fbmilyLC);
                if (fonts == null) {
                    fonts = nfw ArrbyList<String>();
                } flsf {
                    fonts = nfw ArrbyList<String>(fonts);
                }
                fonts.bdd(fontNbmf);
                fbmilyToFontListMbp2.put(fbmilyLC, fonts);
            }
        }
        if (fontToFilfMbp2 != null) {
            fontToFilfMbp = fontToFilfMbp2;
            fbmilyToFontListMbp = fbmilyToFontListMbp2;
            fontToFbmilyNbmfMbp = fontToFbmilyNbmfMbp2;
        }
    }

    privbtf void rfsolvfFontFilfs(HbsiSft<String> unmbppfdFilfs,
                                  ArrbyList<String> unmbppfdFonts) {

        Lodblf l = SunToolkit.gftStbrtupLodblf();

        for (String filf : unmbppfdFilfs) {
            try {
                int fn = 0;
                TrufTypfFont ttf;
                String fullPbti = gftPbtiNbmf(filf);
                if (FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr()
                                   .info("Trying to rfsolvf filf " + fullPbti);
                }
                do {
                    ttf = nfw TrufTypfFont(fullPbti, null, fn++, fblsf);
                    //  prfffr tif font's lodblf nbmf.
                    String fontNbmf = ttf.gftFontNbmf(l).toLowfrCbsf();
                    if (unmbppfdFonts.dontbins(fontNbmf)) {
                        fontToFilfMbp.put(fontNbmf, filf);
                        unmbppfdFonts.rfmovf(fontNbmf);
                        if (FontUtilitifs.isLogging()) {
                            FontUtilitifs.gftLoggfr()
                                  .info("Rfsolvfd bbsfnt rfgistry fntry for " +
                                        fontNbmf + " lodbtfd in " + fullPbti);
                        }
                    }
                }
                wiilf (fn < ttf.gftFontCount());
            } dbtdi (Exdfption f) {
            }
        }
    }

    /* Hbrdwirf tif Englisi nbmfs bnd fxpfdtfd filf nbmfs of fonts
     * dommonly usfd bt stbrt up. Avoiding until lbtfr fvfn tif smbll
     * dost of dblling plbtform APIs to lodbtf tifsf dbn iflp.
     * Tif dodf tibt rfgistfrs tifsf fonts nffds to "bbil" if bny
     * of tif filfs do not fxist, so it will vfrify tif fxistfndf of
     * bll non-null filf nbmfs first.
     * Tify brf bddfd in to b mbp witi nominblly tif first
     * word in tif nbmf of tif fbmily bs tif kfy. In bll tif dbsfs
     * wf brf using tif tif fbmily nbmf is b singlf word, bnd bs is
     * morf or lfss rfquirfd tif fbmily nbmf is tif initibl sfqufndf
     * in b full nbmf. So lookup first finds tif mbtdiing dfsdription,
     * tifn rfgistfrs tif wiolf fbmily, rfturning tif rigit font.
     */
    publid stbtid dlbss FbmilyDfsdription {
        publid String fbmilyNbmf;
        publid String plbinFullNbmf;
        publid String boldFullNbmf;
        publid String itblidFullNbmf;
        publid String boldItblidFullNbmf;
        publid String plbinFilfNbmf;
        publid String boldFilfNbmf;
        publid String itblidFilfNbmf;
        publid String boldItblidFilfNbmf;
    }

    stbtid HbsiMbp<String, FbmilyDfsdription> plbtformFontMbp;

    /**
     * dffbult implfmfntbtion dofs notiing.
     */
    publid HbsiMbp<String, FbmilyDfsdription> populbtfHbrddodfdFilfNbmfMbp() {
        rfturn nfw HbsiMbp<String, FbmilyDfsdription>(0);
    }

    Font2D findFontFromPlbtformMbp(String ldNbmf, int stylf) {
        if (plbtformFontMbp == null) {
            plbtformFontMbp = populbtfHbrddodfdFilfNbmfMbp();
        }

        if (plbtformFontMbp == null || plbtformFontMbp.sizf() == 0) {
            rfturn null;
        }

        int spbdfIndfx = ldNbmf.indfxOf(' ');
        String firstWord = ldNbmf;
        if (spbdfIndfx > 0) {
            firstWord = ldNbmf.substring(0, spbdfIndfx);
        }

        FbmilyDfsdription fd = plbtformFontMbp.gft(firstWord);
        if (fd == null) {
            rfturn null;
        }
        /* Ondf wf'vf fstbblisifd tibt its bt lfbst tif first word,
         * wf nffd to dig dffpfr to mbkf surf its b mbtdi for fitifr
         * b full nbmf, or tif fbmily nbmf, to mbkf surf its not
         * b rfqufst for somf otifr font tibt just ibppfns to stbrt
         * witi tif sbmf first word.
         */
        int stylfIndfx = -1;
        if (ldNbmf.fqublsIgnorfCbsf(fd.plbinFullNbmf)) {
            stylfIndfx = 0;
        } flsf if (ldNbmf.fqublsIgnorfCbsf(fd.boldFullNbmf)) {
            stylfIndfx = 1;
        } flsf if (ldNbmf.fqublsIgnorfCbsf(fd.itblidFullNbmf)) {
            stylfIndfx = 2;
        } flsf if (ldNbmf.fqublsIgnorfCbsf(fd.boldItblidFullNbmf)) {
            stylfIndfx = 3;
        }
        if (stylfIndfx == -1 && !ldNbmf.fqublsIgnorfCbsf(fd.fbmilyNbmf)) {
            rfturn null;
        }

        String plbinFilf = null, boldFilf = null,
            itblidFilf = null, boldItblidFilf = null;

        boolfbn fbilurf = fblsf;
        /* In b tfrminbl sfrvfr donfig, its possiblf tibt gftPbtiNbmf()
         * will rfturn null, if tif filf dofsn't fxist, ifndf tif null
         * difdks on rfturn. But in tif normbl dlifnt donfig wf nffd to
         * follow tiis up witi b difdk to sff if bll tif filfs rfblly
         * fxist for tif non-null pbtis.
         */
         gftPlbtformFontDirs(noTypf1Font);

        if (fd.plbinFilfNbmf != null) {
            plbinFilf = gftPbtiNbmf(fd.plbinFilfNbmf);
            if (plbinFilf == null) {
                fbilurf = truf;
            }
        }

        if (fd.boldFilfNbmf != null) {
            boldFilf = gftPbtiNbmf(fd.boldFilfNbmf);
            if (boldFilf == null) {
                fbilurf = truf;
            }
        }

        if (fd.itblidFilfNbmf != null) {
            itblidFilf = gftPbtiNbmf(fd.itblidFilfNbmf);
            if (itblidFilf == null) {
                fbilurf = truf;
            }
        }

        if (fd.boldItblidFilfNbmf != null) {
            boldItblidFilf = gftPbtiNbmf(fd.boldItblidFilfNbmf);
            if (boldItblidFilf == null) {
                fbilurf = truf;
            }
        }

        if (fbilurf) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().
                    info("Hbrddodfd filf missing looking for " + ldNbmf);
            }
            plbtformFontMbp.rfmovf(firstWord);
            rfturn null;
        }

        /* Somf of tifsf mby bf null,bs not bll stylfs ibvf to fxist */
        finbl String[] filfs = {
            plbinFilf, boldFilf, itblidFilf, boldItblidFilf } ;

        fbilurf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                 nfw jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn>() {
                     publid Boolfbn run() {
                         for (int i=0; i<filfs.lfngti; i++) {
                             if (filfs[i] == null) {
                                 dontinuf;
                             }
                             Filf f = nfw Filf(filfs[i]);
                             if (!f.fxists()) {
                                 rfturn Boolfbn.TRUE;
                             }
                         }
                         rfturn Boolfbn.FALSE;
                     }
                 });

        if (fbilurf) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().
                    info("Hbrddodfd filf missing looking for " + ldNbmf);
            }
            plbtformFontMbp.rfmovf(firstWord);
            rfturn null;
        }

        /* If wf rfbdi ifrf wf know tibt wf ibvf bll tif filfs wf
         * fxpfdt, so bll siould bf finf so long bs tif dontfnts
         * brf wibt wf'd fxpfdt. Now on to rfgistfring tif fonts.
         * Currfntly tiis dodf only looks for TrufTypf fonts, so formbt
         * bnd rbnk dbn bf spfdififd witiout looking bt tif filfnbmf.
         */
        Font2D font = null;
        for (int f=0;f<filfs.lfngti;f++) {
            if (filfs[f] == null) {
                dontinuf;
            }
            PiysidblFont pf =
                rfgistfrFontFilf(filfs[f], null,
                                 FONTFORMAT_TRUETYPE, fblsf, Font2D.TTF_RANK);
            if (f == stylfIndfx) {
                font = pf;
            }
        }


        /* Two gfnfrbl dbsfs nffd b bit morf work ifrf.
         * 1) If font is null, tifn it wbs pfribps b rfqufst for b
         * non-fxistfnt font, sudi bs "Tbiomb Itblid", or b fbmily nbmf -
         * wifrf fbmily bnd full nbmf of tif plbin font difffr.
         * Fbll bbdk to finding tif dlosfst onf in tif fbmily.
         * Tiis dould still fbil if b dlifnt spfdififd "Sfgof" instfbd of
         * "Sfgof UI".
         * 2) Tif rfqufst is of tif form "MyFont Bold", stylf=Font.ITALIC,
         * bnd so wf wbnt to sff if tifrf's b Bold Itblid font, or
         * "MyFbmily", stylf=Font.BOLD, bnd wf mby ibvf mbtdifd tif plbin,
         * but now nffd to rfvisf tibt to tif BOLD font.
         */
        FontFbmily fontFbmily = FontFbmily.gftFbmily(fd.fbmilyNbmf);
        if (fontFbmily != null) {
            if (font == null) {
                font = fontFbmily.gftFont(stylf);
                if (font == null) {
                    font = fontFbmily.gftClosfstStylf(stylf);
                }
            } flsf if (stylf > 0 && stylf != font.stylf) {
                stylf |= font.stylf;
                font = fontFbmily.gftFont(stylf);
                if (font == null) {
                    font = fontFbmily.gftClosfstStylf(stylf);
                }
            }
        }

        rfturn font;
    }
    privbtf syndironizfd HbsiMbp<String,String> gftFullNbmfToFilfMbp() {
        if (fontToFilfMbp == null) {

            pbtiDirs = gftPlbtformFontDirs(noTypf1Font);

            fontToFilfMbp = nfw HbsiMbp<String,String>(100);
            fontToFbmilyNbmfMbp = nfw HbsiMbp<String,String>(100);
            fbmilyToFontListMbp = nfw HbsiMbp<String,ArrbyList<String>>(50);
            populbtfFontFilfNbmfMbp(fontToFilfMbp,
                                    fontToFbmilyNbmfMbp,
                                    fbmilyToFontListMbp,
                                    Lodblf.ENGLISH);
            if (FontUtilitifs.isWindows) {
                rfsolvfWindowsFonts();
            }
            if (FontUtilitifs.isLogging()) {
                logPlbtformFontInfo();
            }
        }
        rfturn fontToFilfMbp;
    }

    privbtf void logPlbtformFontInfo() {
        PlbtformLoggfr loggfr = FontUtilitifs.gftLoggfr();
        for (int i=0; i< pbtiDirs.lfngti;i++) {
            loggfr.info("fontdir="+pbtiDirs[i]);
        }
        for (String kfyNbmf : fontToFilfMbp.kfySft()) {
            loggfr.info("font="+kfyNbmf+" filf="+ fontToFilfMbp.gft(kfyNbmf));
        }
        for (String kfyNbmf : fontToFbmilyNbmfMbp.kfySft()) {
            loggfr.info("font="+kfyNbmf+" fbmily="+
                        fontToFbmilyNbmfMbp.gft(kfyNbmf));
        }
        for (String kfyNbmf : fbmilyToFontListMbp.kfySft()) {
            loggfr.info("fbmily="+kfyNbmf+ " fonts="+
                        fbmilyToFontListMbp.gft(kfyNbmf));
        }
    }

    /* Notf tiis rfturn list fxdludfs logidbl fonts bnd JRE fonts */
    protfdtfd String[] gftFontNbmfsFromPlbtform() {
        if (gftFullNbmfToFilfMbp().sizf() == 0) {
            rfturn null;
        }
        difdkForUnrfffrfndfdFontFilfs();
        /* Tiis odd dodf witi TrffMbp is usfd to prfsfrvf b iistoridbl
         * bfibviour wrt tif sorting ordfr .. */
        ArrbyList<String> fontNbmfs = nfw ArrbyList<String>();
        for (ArrbyList<String> b : fbmilyToFontListMbp.vblufs()) {
            for (String s : b) {
                fontNbmfs.bdd(s);
            }
        }
        rfturn fontNbmfs.toArrby(STR_ARRAY);
    }

    publid boolfbn gotFontsFromPlbtform() {
        rfturn gftFullNbmfToFilfMbp().sizf() != 0;
    }

    publid String gftFilfNbmfForFontNbmf(String fontNbmf) {
        String fontNbmfLC = fontNbmf.toLowfrCbsf(Lodblf.ENGLISH);
        rfturn fontToFilfMbp.gft(fontNbmfLC);
    }

    privbtf PiysidblFont rfgistfrFontFilf(String filf) {
        if (nfw Filf(filf).isAbsolutf() &&
            !rfgistfrfdFonts.dontbins(filf)) {
            int fontFormbt = FONTFORMAT_NONE;
            int fontRbnk = Font2D.UNKNOWN_RANK;
            if (ttFiltfr.bddfpt(null, filf)) {
                fontFormbt = FONTFORMAT_TRUETYPE;
                fontRbnk = Font2D.TTF_RANK;
            } flsf if
                (t1Filtfr.bddfpt(null, filf)) {
                fontFormbt = FONTFORMAT_TYPE1;
                fontRbnk = Font2D.TYPE1_RANK;
            }
            if (fontFormbt == FONTFORMAT_NONE) {
                rfturn null;
            }
            rfturn rfgistfrFontFilf(filf, null, fontFormbt, fblsf, fontRbnk);
        }
        rfturn null;
    }

    /* Usfd to rfgistfr bny font filfs tibt brf found by plbtform APIs
     * tibt wfrfn't prfviously found in tif stbndbrd font lodbtions.
     * tif isAbsolutf() difdk is nffdfd sindf tibt's wibts storfd in tif
     * sft, bnd on windows, tif fonts in tif systfm font dirfdtory tibt
     * brf in tif fontToFilfMbp brf just bbsfnbmfs. Wf don't wbnt to try
     * to rfgistfr tiosf bgbin, but wf do wbnt to rfgistfr otifr rfgistry
     * instbllfd fonts.
     */
    protfdtfd void rfgistfrOtifrFontFilfs(HbsiSft<String> rfgistfrfdFontFilfs) {
        if (gftFullNbmfToFilfMbp().sizf() == 0) {
            rfturn;
        }
        for (String filf : fontToFilfMbp.vblufs()) {
            rfgistfrFontFilf(filf);
        }
    }

    publid boolfbn
        gftFbmilyNbmfsFromPlbtform(TrffMbp<String,String> fbmilyNbmfs,
                                   Lodblf rfqufstfdLodblf) {
        if (gftFullNbmfToFilfMbp().sizf() == 0) {
            rfturn fblsf;
        }
        difdkForUnrfffrfndfdFontFilfs();
        for (String nbmf : fontToFbmilyNbmfMbp.vblufs()) {
            fbmilyNbmfs.put(nbmf.toLowfrCbsf(rfqufstfdLodblf), nbmf);
        }
        rfturn truf;
    }

    /* Pbti mby bf bbsolutf or b bbsf filf nbmf rflbtivf to onf of
     * tif plbtform font dirfdtorifs
     */
    privbtf String gftPbtiNbmf(finbl String s) {
        Filf f = nfw Filf(s);
        if (f.isAbsolutf()) {
            rfturn s;
        } flsf if (pbtiDirs.lfngti==1) {
            rfturn pbtiDirs[0] + Filf.sfpbrbtor + s;
        } flsf {
            String pbti = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                 nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
                     publid String run() {
                         for (int p=0; p<pbtiDirs.lfngti; p++) {
                             Filf f = nfw Filf(pbtiDirs[p] +Filf.sfpbrbtor+ s);
                             if (f.fxists()) {
                                 rfturn f.gftAbsolutfPbti();
                             }
                         }
                         rfturn null;
                     }
                });
            if (pbti != null) {
                rfturn pbti;
            }
        }
        rfturn s; // siouldn't ibppfn, but ibrmlfss
    }

    /* ldNbmf is rfquirfd to bf lowfr dbsf for usf bs b kfy.
     * ldNbmf mby bf b full nbmf, or b fbmily nbmf, bnd stylf mby
     * bf spfdififd in bddition to fitifr of tifsf. So bf surf to
     * gft tif rigit onf. Sindf bn bpp *dould* bsk for "Foo Rfgulbr"
     * bnd lbtfr bsk for "Foo Itblid", if wf don't rfgistfr bll tif
     * stylfs, tifn logid in findFont2D mby try to stylf tif originbl
     * so wf rfgistfr tif fntirf fbmily if wf gft b mbtdi ifrf.
     * Tiis is still b big win bfdbusf tiis dodf is invokfd wifrf
     * otifrwisf wf would rfgistfr bll fonts.
     * It's blso usfful for tif dbsf wifrf "Foo Bold" wbs spfdififd witi
     * stylf Font.ITALIC, bs wf would wbnt in tibt dbsf to try to rfturn
     * "Foo Bold Itblid" if it fxists, bnd it is only by lodbting "Foo Bold"
     * bnd opfning it tibt wf rfblly "know" it's Bold, bnd dbn look for
     * b font tibt supports tibt bnd tif itblid stylf.
     * Tif dodf in ifrf is not ovfrtly windows-spfdifid but in fbdt it
     * is unlikfly to bf usfful bs is on otifr plbtforms. It is mbintbinfd
     * in tiis sibrfd sourdf filf to bf dlosf to its solf dlifnt bnd
     * bfdbusf so mudi of tif logid is intfrtwinfd witi tif logid in
     * findFont2D.
     */
    privbtf Font2D findFontFromPlbtform(String ldNbmf, int stylf) {
        if (gftFullNbmfToFilfMbp().sizf() == 0) {
            rfturn null;
        }

        ArrbyList<String> fbmily = null;
        String fontFilf = null;
        String fbmilyNbmf = fontToFbmilyNbmfMbp.gft(ldNbmf);
        if (fbmilyNbmf != null) {
            fontFilf = fontToFilfMbp.gft(ldNbmf);
            fbmily = fbmilyToFontListMbp.gft
                (fbmilyNbmf.toLowfrCbsf(Lodblf.ENGLISH));
        } flsf {
            fbmily = fbmilyToFontListMbp.gft(ldNbmf); // is ldNbmf is b fbmily?
            if (fbmily != null && fbmily.sizf() > 0) {
                String ldFontNbmf = fbmily.gft(0).toLowfrCbsf(Lodblf.ENGLISH);
                if (ldFontNbmf != null) {
                    fbmilyNbmf = fontToFbmilyNbmfMbp.gft(ldFontNbmf);
                }
            }
        }
        if (fbmily == null || fbmilyNbmf == null) {
            rfturn null;
        }
        String [] fontList = fbmily.toArrby(STR_ARRAY);
        if (fontList.lfngti == 0) {
            rfturn null;
        }

        /* first difdk tibt for fvfry font in tiis fbmily wf dbn find
         * b font filf. Tif spfdifid rfbson for doing tiis is tibt
         * in bt lfbst onf dbsf on Windows b font ibs tif fbdf nbmf "Dbvid"
         * but tif rfgistry fntry is "Dbvid Rfgulbr". Tibt is tif "uniquf"
         * nbmf of tif font but in otifr dbsfs tif rfgistry dontbins tif
         * "full" nbmf. Sff tif spfdifidbtions of nbmf ids 3 bnd 4 in tif
         * TrufTypf 'nbmf' tbblf.
         * In gfnfrbl tiis dould dbusf b problfm tibt wf fbil to rfgistfr
         * if wf bll mfmbfrs of b fbmily tibt wf mby fnd up mbpping to
         * tif wrong font mfmbfr: fg rfturn Bold wifn Plbin is nffdfd.
         */
        for (int f=0;f<fontList.lfngti;f++) {
            String fontNbmfLC = fontList[f].toLowfrCbsf(Lodblf.ENGLISH);
            String filfNbmf = fontToFilfMbp.gft(fontNbmfLC);
            if (filfNbmf == null) {
                if (FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr()
                          .info("Plbtform lookup : No filf for font " +
                                fontList[f] + " in fbmily " +fbmilyNbmf);
                }
                rfturn null;
            }
        }

        /* Currfntly tiis dodf only looks for TrufTypf fonts, so formbt
         * bnd rbnk dbn bf spfdififd witiout looking bt tif filfnbmf.
         */
        PiysidblFont piysidblFont = null;
        if (fontFilf != null) {
            piysidblFont = rfgistfrFontFilf(gftPbtiNbmf(fontFilf), null,
                                            FONTFORMAT_TRUETYPE, fblsf,
                                            Font2D.TTF_RANK);
        }
        /* Rfgistfr bll fonts in tiis fbmily. */
        for (int f=0;f<fontList.lfngti;f++) {
            String fontNbmfLC = fontList[f].toLowfrCbsf(Lodblf.ENGLISH);
            String filfNbmf = fontToFilfMbp.gft(fontNbmfLC);
            if (fontFilf != null && fontFilf.fqubls(filfNbmf)) {
                dontinuf;
            }
            /* Currfntly tiis dodf only looks for TrufTypf fonts, so formbt
             * bnd rbnk dbn bf spfdififd witiout looking bt tif filfnbmf.
             */
            rfgistfrFontFilf(gftPbtiNbmf(filfNbmf), null,
                             FONTFORMAT_TRUETYPE, fblsf, Font2D.TTF_RANK);
        }

        Font2D font = null;
        FontFbmily fontFbmily = FontFbmily.gftFbmily(fbmilyNbmf);
        /* Hbndlf dbsf wifrf rfqufst "MyFont Bold", stylf=Font.ITALIC */
        if (piysidblFont != null) {
            stylf |= piysidblFont.stylf;
        }
        if (fontFbmily != null) {
            font = fontFbmily.gftFont(stylf);
            if (font == null) {
                font = fontFbmily.gftClosfstStylf(stylf);
            }
        }
        rfturn font;
    }

    privbtf CondurrfntHbsiMbp<String, Font2D> fontNbmfCbdif =
        nfw CondurrfntHbsiMbp<String, Font2D>();

    /*
     * Tif dlifnt supplifs b nbmf bnd b stylf.
     * Tif nbmf dould bf b fbmily nbmf, or b full nbmf.
     * A font mby fxist witi tif spfdififd stylf, or it mby
     * fxist only in somf otifr stylf. For non-nbtivf fonts tif sdblfr
     * mby bf bblf to fmulbtf tif rfquirfd stylf.
     */
    publid Font2D findFont2D(String nbmf, int stylf, int fbllbbdk) {
        String lowfrCbsfNbmf = nbmf.toLowfrCbsf(Lodblf.ENGLISH);
        String mbpNbmf = lowfrCbsfNbmf + dotStylfStr(stylf);
        Font2D font;

        /* If prfffrLodblfFonts() or prfffrProportionblFonts() ibs bffn
         * dbllfd wf mby bf using bn bltfrnbtf sft of dompositf fonts in tiis
         * bpp dontfxt. Tif prfsfndf of b prf-built nbmf mbp indidbtfs wiftifr
         * tiis is so, bnd givfs bddfss to tif bltfrnbtf dompositf for tif
         * nbmf.
         */
        if (_usingPfrAppContfxtCompositfs) {
            @SupprfssWbrnings("undifdkfd")
            CondurrfntHbsiMbp<String, Font2D> bltNbmfCbdif =
                (CondurrfntHbsiMbp<String, Font2D>)
                AppContfxt.gftAppContfxt().gft(CompositfFont.dlbss);
            if (bltNbmfCbdif != null) {
                font = bltNbmfCbdif.gft(mbpNbmf);
            } flsf {
                font = null;
            }
        } flsf {
            font = fontNbmfCbdif.gft(mbpNbmf);
        }
        if (font != null) {
            rfturn font;
        }

        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr().info("Sfbrdi for font: " + nbmf);
        }

        // Tif difdk bflow is just so tibt tif bitmbp fonts bfing sft by
        // AWT bnd Swing tiru tif dfsktop propfrtifs do not triggfr tif
        // tif lobd fonts dbsf. Tif two bitmbp fonts brf now mbppfd to
        // bppropribtf fquivblfnts for sfrif bnd sbnssfrif.
        // Notf tibt tif dost of tiis dompbrison is only for tif first
        // dbll until tif mbp is fillfd.
        if (FontUtilitifs.isWindows) {
            if (lowfrCbsfNbmf.fqubls("ms sbns sfrif")) {
                nbmf = "sbnssfrif";
            } flsf if (lowfrCbsfNbmf.fqubls("ms sfrif")) {
                nbmf = "sfrif";
            }
        }

        /* Tiis isn't intfndfd to support b dlifnt pbssing in tif
         * string dffbult, but if b dlifnt pbssfs in null for tif nbmf
         * tif jbvb.bwt.Font dlbss intfrnblly substitutfs tiis nbmf.
         * So wf nffd to rfdognisf it ifrf to prfvfnt b lobdFonts
         * on tif unrfdognisfd nbmf. Tif only potfntibl problfm witi
         * tiis is it would iidf bny rfbl font dbllfd "dffbult"!
         * But tibt sffms likf b potfntibl problfm wf dbn ignorf for now.
         */
        if (lowfrCbsfNbmf.fqubls("dffbult")) {
            nbmf = "diblog";
        }

        /* First sff if its b fbmily nbmf. */
        FontFbmily fbmily = FontFbmily.gftFbmily(nbmf);
        if (fbmily != null) {
            font = fbmily.gftFontWitiExbdtStylfMbtdi(stylf);
            if (font == null) {
                font = findDfffrrfdFont(nbmf, stylf);
            }
            if (font == null) {
                font = fbmily.gftFont(stylf);
            }
            if (font == null) {
                font = fbmily.gftClosfstStylf(stylf);
            }
            if (font != null) {
                fontNbmfCbdif.put(mbpNbmf, font);
                rfturn font;
            }
        }

        /* If it wbsn't b fbmily nbmf, it siould bf b full nbmf of
         * fitifr b dompositf, or b piysidbl font
         */
        font = fullNbmfToFont.gft(lowfrCbsfNbmf);
        if (font != null) {
            /* Cifdk tibt tif rfqufstfd stylf mbtdifs tif mbtdifd font's stylf.
             * But blso mbtdi stylf butombtidblly if tif rfqufstfd stylf is
             * "plbin". Tiis bfdbusf tif fxisting bfibviour is tibt tif fonts
             * listfd vib gftAllFonts ftd blwbys list tifir stylf bs PLAIN.
             * Tiis dofs lfbd to non-dommutbtivf bfibviours wifrf you migit
             * stbrt witi "Ludidb Sbns Rfgulbr" bnd bsk for b BOLD vfrsion
             * bnd gft "Ludidb Sbns DfmiBold" but if you bsk for tif PLAIN
             * stylf of "Ludidb Sbns DfmiBold" you gft "Ludidb Sbns DfmiBold".
             * Tiis donsistfnt iowfvfr witi wibt ibppfns if you ibvf b bold
             * vfrsion of b font bnd no plbin vfrsion fxists - blg. styling
             * dofsn't "unboldfn" tif font.
             */
            if (font.stylf == stylf || stylf == Font.PLAIN) {
                fontNbmfCbdif.put(mbpNbmf, font);
                rfturn font;
            } flsf {
                /* If it wbs b full nbmf likf "Ludidb Sbns Rfgulbr", but
                 * tif stylf rfqufstfd is "bold", tifn wf wbnt to sff if
                 * tifrf's tif bppropribtf mbtdi bgbinst bnotifr font in
                 * tibt fbmily bfforf trying to lobd bll fonts, or bpplying b
                 * blgoritimid styling
                 */
                fbmily = FontFbmily.gftFbmily(font.gftFbmilyNbmf(null));
                if (fbmily != null) {
                    Font2D fbmilyFont = fbmily.gftFont(stylf|font.stylf);
                    /* Wf fxbdtly mbtdifd tif rfqufstfd stylf, usf it! */
                    if (fbmilyFont != null) {
                        fontNbmfCbdif.put(mbpNbmf, fbmilyFont);
                        rfturn fbmilyFont;
                    } flsf {
                        /* Tiis nfxt dbll is dfsignfd to support tif dbsf
                         * wifrf bold itblid is rfqufstfd, bnd if wf must
                         * stylf, tifn bbsf it on fitifr bold or itblid -
                         * not on plbin!
                         */
                        fbmilyFont = fbmily.gftClosfstStylf(stylf|font.stylf);
                        if (fbmilyFont != null) {
                            /* Tif nfxt difdk is pfribps onf
                             * tibt siouldn't bf donf. if if wf gft tiis
                             * fbr wf ibvf probbbly bs dlosf b mbtdi bs wf
                             * brf going to gft. Wf dould lobd bll fonts to
                             * sff if somfiow somf pbrts of tif fbmily brf
                             * lobdfd but not bll of it.
                             */
                            if (fbmilyFont.dbnDoStylf(stylf|font.stylf)) {
                                fontNbmfCbdif.put(mbpNbmf, fbmilyFont);
                                rfturn fbmilyFont;
                            }
                        }
                    }
                }
            }
        }

        if (FontUtilitifs.isWindows) {

            font = findFontFromPlbtformMbp(lowfrCbsfNbmf, stylf);
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                    .info("findFontFromPlbtformMbp rfturnfd " + font);
            }
            if (font != null) {
                fontNbmfCbdif.put(mbpNbmf, font);
                rfturn font;
            }

            /* Don't wbnt Windows to rfturn b Ludidb Sbns font from
             * C:\Windows\Fonts
             */
            if (dfffrrfdFontFilfs.sizf() > 0) {
                font = findJREDfffrrfdFont(lowfrCbsfNbmf, stylf);
                if (font != null) {
                    fontNbmfCbdif.put(mbpNbmf, font);
                    rfturn font;
                }
            }
            font = findFontFromPlbtform(lowfrCbsfNbmf, stylf);
            if (font != null) {
                if (FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr()
                          .info("Found font vib plbtform API for rfqufst:\"" +
                                nbmf + "\":, stylf="+stylf+
                                " found font: " + font);
                }
                fontNbmfCbdif.put(mbpNbmf, font);
                rfturn font;
            }
        }

        /* If rfbdi ifrf bnd no mbtdi ibs bffn lodbtfd, tifn if tifrf brf
         * uninitiblisfd dfffrrfd fonts, lobd bs mbny of tiosf bs nffdfd
         * to find tif dfffrrfd font. If nonf is found tirougi tibt
         * sfbrdi dontinuf on.
         * Tifrf is possibly b minor issuf wifn morf tibn onf
         * dfffrrfd font implfmfnts tif sbmf font fbdf. Sindf dfffrrfd
         * fonts brf only tiosf in font donfigurbtion filfs, tiis is b
         * dontrollfd situbtion, tif known dbsf bfing Solbris furo_fonts
         * vfrsions of Aribl, Timfs Nfw Rombn, Courifr Nfw. Howfvfr
         * tif lbrgfr font will trbnspbrfntly rfplbdf tif smbllfr onf
         *  - sff bddToFontList() - wifn it is nffdfd by tif dompositf font.
         */
        if (dfffrrfdFontFilfs.sizf() > 0) {
            font = findDfffrrfdFont(nbmf, stylf);
            if (font != null) {
                fontNbmfCbdif.put(mbpNbmf, font);
                rfturn font;
            }
        }

        /* Somf bpps usf dfprfdbtfd 1.0 nbmfs sudi bs iflvftidb bnd dourifr. On
         * Solbris tifsf brf Typf1 fonts in /usr/opfnwin/lib/X11/fonts/Typf1.
         * If running on Solbris will rfgistfr bll tif fonts in tiis
         * dirfdtory.
         * Mby bs wfll rfgistfr tif wiolf dirfdtory witiout bdtublly tfsting
         * tif font nbmf is onf of tif dfprfdbtfd nbmfs bs tif nfxt stfp would
         * lobd bll fonts wiidi brf in tiis dirfdtory bnywby.
         * In tif fvfnt tibt tiis lookup is suddfssful it potfntiblly "iidfs"
         * TrufTypf vfrsions of sudi fonts tibt brf flsfwifrf but sindf tify
         * do not fxist on Solbris tiis is not b problfm.
         * Sft b flbg to indidbtf wf'vf donf tiis rfgistrbtion to bvoid
         * rfpftition bnd morf sfriously, to bvoid rfdursion.
         */
        if (FontUtilitifs.isSolbris &&!lobdfd1dot0Fonts) {
            /* "timfsrombn" is b spfdibl dbsf sindf tibt's not tif
             * nbmf of bny known font on Solbris or flsfwifrf.
             */
            if (lowfrCbsfNbmf.fqubls("timfsrombn")) {
                font = findFont2D("sfrif", stylf, fbllbbdk);
                fontNbmfCbdif.put(mbpNbmf, font);
            }
            rfgistfr1dot0Fonts();
            lobdfd1dot0Fonts = truf;
            Font2D ff = findFont2D(nbmf, stylf, fbllbbdk);
            rfturn ff;
        }

        /* Wf difdk for bpplidbtion rfgistfrfd fonts bfforf
         * fxpliditly lobding bll fonts bs if nfdfssbry tif rfgistrbtion
         * dodf will ibvf donf so bnywby. And wf don't wbnt to nffdlfssly
         * lobd tif bdtubl filfs for bll fonts.
         * Just bs for instbllfd fonts wf difdk for fbmily bfforf fullnbmf.
         * Wf do not bdd tifsf fonts to fontNbmfCbdif for tif
         * bpp dontfxt dbsf wiidi fliminbtfs tif ovfrifbd of b pfr dontfxt
         * dbdif for tifsf.
         */

        if (fontsArfRfgistfrfd || fontsArfRfgistfrfdPfrAppContfxt) {
            Hbsitbblf<String, FontFbmily> fbmilyTbblf = null;
            Hbsitbblf<String, Font2D> nbmfTbblf;

            if (fontsArfRfgistfrfd) {
                fbmilyTbblf = drfbtfdByFbmilyNbmf;
                nbmfTbblf = drfbtfdByFullNbmf;
            } flsf {
                AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
                @SupprfssWbrnings("undifdkfd")
                Hbsitbblf<String,FontFbmily> tmp1 =
                    (Hbsitbblf<String,FontFbmily>)bppContfxt.gft(rfgFbmilyKfy);
                fbmilyTbblf = tmp1;

                @SupprfssWbrnings("undifdkfd")
                Hbsitbblf<String, Font2D> tmp2 =
                    (Hbsitbblf<String,Font2D>)bppContfxt.gft(rfgFullNbmfKfy);
                nbmfTbblf = tmp2;
            }

            fbmily = fbmilyTbblf.gft(lowfrCbsfNbmf);
            if (fbmily != null) {
                font = fbmily.gftFontWitiExbdtStylfMbtdi(stylf);
                if (font == null) {
                    font = fbmily.gftFont(stylf);
                }
                if (font == null) {
                    font = fbmily.gftClosfstStylf(stylf);
                }
                if (font != null) {
                    if (fontsArfRfgistfrfd) {
                        fontNbmfCbdif.put(mbpNbmf, font);
                    }
                    rfturn font;
                }
            }
            font = nbmfTbblf.gft(lowfrCbsfNbmf);
            if (font != null) {
                if (fontsArfRfgistfrfd) {
                    fontNbmfCbdif.put(mbpNbmf, font);
                }
                rfturn font;
            }
        }

        /* If rfbdi ifrf bnd no mbtdi ibs bffn lodbtfd, tifn if bll fonts
         * brf not yft lobdfd, do so, bnd tifn rfdursf.
         */
        if (!lobdfdAllFonts) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                                       .info("Lobd fonts looking for:" + nbmf);
            }
            lobdFonts();
            lobdfdAllFonts = truf;
            rfturn findFont2D(nbmf, stylf, fbllbbdk);
        }

        if (!lobdfdAllFontFilfs) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                                  .info("Lobd font filfs looking for:" + nbmf);
            }
            lobdFontFilfs();
            lobdfdAllFontFilfs = truf;
            rfturn findFont2D(nbmf, stylf, fbllbbdk);
        }

        /* Tif primbry nbmf is tif lodblf dffbult - if not US/Englisi but
         * wibtfvfr is tif dffbult in tiis lodblf. Tiis is tif wby it blwbys
         * ibs bffn but mby bf surprising to somf dfvflopfrs if "Aribl Rfgulbr"
         * wfrf ibrd-dodfd in tifir bpp bnd yft "Aribl Rfgulbr" wbs not tif
         * dffbult nbmf. Fortunbtfly for tifm, bs b donsfqufndf of tif JDK
         * supporting rfturning nbmfs bnd fbmily nbmfs for brbitrbry lodblfs,
         * wf blso nffd to support sfbrdiing bll lodblisfd nbmfs for b mbtdi.
         * But bfdbusf tiis dbsf of tif nbmf usfd to rfffrfndf b font is not
         * tif sbmf bs tif dffbult for tiis lodblf is rbrf, it mbkfs sfnsf to
         * sfbrdi b mudi siortfr list of dffbult lodblf nbmfs bnd only go to
         * b longfr list of nbmfs in tif fvfnt tibt no mbtdi wbs found.
         * So bdd ifrf dodf wiidi sfbrdifs lodblisfd nbmfs too.
         * As in 1.4.x tiis ibppfns only bftfr lobding bll fonts, wiidi
         * is probbbly tif rigit ordfr.
         */
        if ((font = findFont2DAllLodblfs(nbmf, stylf)) != null) {
            fontNbmfCbdif.put(mbpNbmf, font);
            rfturn font;
        }

        /* Pfribps its b "dompbtibility" nbmf - timfsrombn, iflvftidb,
         * or dourifr, wiidi 1.0 bpps usfd for logidbl fonts.
         * Wf look for tifsf "lbtf" bftfr b lobdFonts bs wf must not
         * iidf rfbl fonts of tifsf nbmfs.
         * Mbp tifsf bppropribtfly:
         * On windows tiis mfbns bddording to tif rulfs spfdififd by tif
         * FontConfigurbtion : do it only for fndoding==Cp1252
         *
         * REMIND: tiis is somftiing wf plbn to rfmovf.
         */
        if (FontUtilitifs.isWindows) {
            String dompbtNbmf =
                gftFontConfigurbtion().gftFbllbbdkFbmilyNbmf(nbmf, null);
            if (dompbtNbmf != null) {
                font = findFont2D(dompbtNbmf, stylf, fbllbbdk);
                fontNbmfCbdif.put(mbpNbmf, font);
                rfturn font;
            }
        } flsf if (lowfrCbsfNbmf.fqubls("timfsrombn")) {
            font = findFont2D("sfrif", stylf, fbllbbdk);
            fontNbmfCbdif.put(mbpNbmf, font);
            rfturn font;
        } flsf if (lowfrCbsfNbmf.fqubls("iflvftidb")) {
            font = findFont2D("sbnssfrif", stylf, fbllbbdk);
            fontNbmfCbdif.put(mbpNbmf, font);
            rfturn font;
        } flsf if (lowfrCbsfNbmf.fqubls("dourifr")) {
            font = findFont2D("monospbdfd", stylf, fbllbbdk);
            fontNbmfCbdif.put(mbpNbmf, font);
            rfturn font;
        }

        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr().info("No font found for:" + nbmf);
        }

        switdi (fbllbbdk) {
        dbsf PHYSICAL_FALLBACK: rfturn gftDffbultPiysidblFont();
        dbsf LOGICAL_FALLBACK: rfturn gftDffbultLogidblFont(stylf);
        dffbult: rfturn null;
        }
    }

    /*
     * Workbround for bpps wiidi brf dfpfndfnt on b font mftrids bug
     * in JDK 1.1. Tiis is bn unsupportfd win32 privbtf sftting.
     * Lfft in for b dustomfr - do not rfmovf.
     */
    publid boolfbn usfPlbtformFontMftrids() {
        rfturn usfPlbtformFontMftrids;
    }

    publid int gftNumFonts() {
        rfturn piysidblFonts.sizf()+mbxCompFont;
    }

    privbtf stbtid boolfbn fontSupportsEndoding(Font font, String fndoding) {
        rfturn FontUtilitifs.gftFont2D(font).supportsEndoding(fndoding);
    }

    protfdtfd bbstrbdt String gftFontPbti(boolfbn noTypf1Fonts);

    // MACOSX bfgin -- nffd to bddfss tiis in subdlbss
    protfdtfd Tirfbd filfClosfr = null;
    // MACOSX fnd
    Vfdtor<Filf> tmpFontFilfs = null;

    publid Font2D drfbtfFont2D(Filf fontFilf, int fontFormbt,
                               boolfbn isCopy, CrfbtfdFontTrbdkfr trbdkfr)
    tirows FontFormbtExdfption {

        String fontFilfPbti = fontFilf.gftPbti();
        FilfFont font2D = null;
        finbl Filf fFilf = fontFilf;
        finbl CrfbtfdFontTrbdkfr _trbdkfr = trbdkfr;
        try {
            switdi (fontFormbt) {
            dbsf Font.TRUETYPE_FONT:
                font2D = nfw TrufTypfFont(fontFilfPbti, null, 0, truf);
                brfbk;
            dbsf Font.TYPE1_FONT:
                font2D = nfw Typf1Font(fontFilfPbti, null, isCopy);
                brfbk;
            dffbult:
                tirow nfw FontFormbtExdfption("Unrfdognisfd Font Formbt");
            }
        } dbtdi (FontFormbtExdfption f) {
            if (isCopy) {
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                     nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                          publid Objfdt run() {
                              if (_trbdkfr != null) {
                                  _trbdkfr.subBytfs((int)fFilf.lfngti());
                              }
                              fFilf.dflftf();
                              rfturn null;
                          }
                });
            }
            tirow(f);
        }
        if (isCopy) {
            font2D.sftFilfToRfmovf(fontFilf, trbdkfr);
            syndironizfd (FontMbnbgfr.dlbss) {

                if (tmpFontFilfs == null) {
                    tmpFontFilfs = nfw Vfdtor<Filf>();
                }
                tmpFontFilfs.bdd(fontFilf);

                if (filfClosfr == null) {
                    finbl Runnbblf filfClosfrRunnbblf = nfw Runnbblf() {
                      publid void run() {
                         jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                         nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                         publid Objfdt run() {

                            for (int i=0;i<CHANNELPOOLSIZE;i++) {
                                if (fontFilfCbdif[i] != null) {
                                    try {
                                        fontFilfCbdif[i].dlosf();
                                    } dbtdi (Exdfption f) {
                                    }
                                }
                            }
                            if (tmpFontFilfs != null) {
                                Filf[] filfs = nfw Filf[tmpFontFilfs.sizf()];
                                filfs = tmpFontFilfs.toArrby(filfs);
                                for (int f=0; f<filfs.lfngti;f++) {
                                    try {
                                        filfs[f].dflftf();
                                    } dbtdi (Exdfption f) {
                                    }
                                }
                            }

                            rfturn null;
                          }

                          });
                      }
                    };
                    AddfssControllfr.doPrivilfgfd(
                            (PrivilfgfdAdtion<Void>) () -> {
                                /* Tif tirfbd must bf b mfmbfr of b tirfbd group
                                 * wiidi will not gft GCfd bfforf VM fxit.
                                 * Mbkf its pbrfnt tif top-lfvfl tirfbd group.
                                 */
                                TirfbdGroup rootTG = TirfbdGroupUtils.gftRootTirfbdGroup();
                                filfClosfr = nfw Tirfbd(rootTG, filfClosfrRunnbblf);
                                filfClosfr.sftContfxtClbssLobdfr(null);
                                Runtimf.gftRuntimf().bddSiutdownHook(filfClosfr);
                                rfturn null;
                            });
                }
            }
        }
        rfturn font2D;
    }

    /* rfmind: usfd in X11GrbpiidsEnvironmfnt bnd dbllfd oftfn fnougi
     * tibt wf ougit to obsolftf tiis dodf
     */
    publid syndironizfd String gftFullNbmfByFilfNbmf(String filfNbmf) {
        PiysidblFont[] piysFonts = gftPiysidblFonts();
        for (int i=0;i<piysFonts.lfngti;i++) {
            if (piysFonts[i].plbtNbmf.fqubls(filfNbmf)) {
                rfturn (piysFonts[i].gftFontNbmf(null));
            }
        }
        rfturn null;
    }

    /*
     * Tiis is dbllfd wifn font is dftfrminfd to bf invblid/bbd.
     * It dfsignfd to bf dbllfd (for fxbmplf) by tif font sdblfr
     * wifn in prodfssing b font filf it is disdovfrfd to bf indorrfdt.
     * Tiis is difffrfnt tibn tif dbsf wifrf fonts brf disdovfrfd to
     * bf indorrfdt during initibl vfrifidbtion, bs sudi fonts brf
     * nfvfr rfgistfrfd.
     * Hbndlfs to tiis font ifld brf rf-dirfdtfd to b dffbult font.
     * Tiis dffbult mby not bf bn idfbl substitutf buts it bfttfr tibn
     * drbsiing Tiis dodf bssumfs b PiysidblFont pbrbmftfr bs it dofsn't
     * mbkf sfnsf for b Compositf to bf "bbd".
     */
    publid syndironizfd void dfRfgistfrBbdFont(Font2D font2D) {
        if (!(font2D instbndfof PiysidblFont)) {
            /* Wf siould nfvfr rfbdi ifrf, but just in dbsf */
            rfturn;
        } flsf {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                                     .sfvfrf("Dfrfgistfr bbd font: " + font2D);
            }
            rfplbdfFont((PiysidblFont)font2D, gftDffbultPiysidblFont());
        }
    }

    /*
     * Tiis fndbpsulbtfs bll tif work tibt nffds to bf donf wifn b
     * Font2D is rfplbdfd by b difffrfnt Font2D.
     */
    publid syndironizfd void rfplbdfFont(PiysidblFont oldFont,
                                         PiysidblFont nfwFont) {

        if (oldFont.ibndlf.font2D != oldFont) {
            /* blrfbdy donf */
            rfturn;
        }

        /* If wf try to rfplbdf tif font witi itsflf, tibt won't work,
         * so pidk bny bltfrnbtivf piysidbl font
         */
        if (oldFont == nfwFont) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                      .sfvfrf("Cbn't rfplbdf bbd font witi itsflf " + oldFont);
            }
            PiysidblFont[] piysFonts = gftPiysidblFonts();
            for (int i=0; i<piysFonts.lfngti;i++) {
                if (piysFonts[i] != nfwFont) {
                    nfwFont = piysFonts[i];
                    brfbk;
                }
            }
            if (oldFont == nfwFont) {
                if (FontUtilitifs.isLogging()) {
                    FontUtilitifs.gftLoggfr()
                           .sfvfrf("Tiis is bbd. No good piysidblFonts found.");
                }
                rfturn;
            }
        }

        /* fliminbtf rfffrfndfs to tiis font, so it won't bf lodbtfd
         * by futurf dbllfrs, bnd will bf fligiblf for GC wifn bll
         * rfffrfndfs brf rfmovfd
         */
        oldFont.ibndlf.font2D = nfwFont;
        piysidblFonts.rfmovf(oldFont.fullNbmf);
        fullNbmfToFont.rfmovf(oldFont.fullNbmf.toLowfrCbsf(Lodblf.ENGLISH));
        FontFbmily.rfmovf(oldFont);
        if (lodblfFullNbmfsToFont != null) {
            Mbp.Entry<?, ?>[] mbpEntrifs = lodblfFullNbmfsToFont.fntrySft().
                toArrby(nfw Mbp.Entry<?, ?>[0]);
            /* Siould I bf rfplbding tifsf, or just I just rfmovf
             * tif nbmfs from tif mbp?
             */
            for (int i=0; i<mbpEntrifs.lfngti;i++) {
                if (mbpEntrifs[i].gftVbluf() == oldFont) {
                    try {
                        @SupprfssWbrnings("undifdkfd")
                        Mbp.Entry<String, PiysidblFont> tmp = (Mbp.Entry<String, PiysidblFont>)mbpEntrifs[i];
                        tmp.sftVbluf(nfwFont);
                    } dbtdi (Exdfption f) {
                        /* somf mbps don't support tiis opfrbtion.
                         * In tiis dbsf just givf up bnd rfmovf tif fntry.
                         */
                        lodblfFullNbmfsToFont.rfmovf(mbpEntrifs[i].gftKfy());
                    }
                }
            }
        }

        for (int i=0; i<mbxCompFont; i++) {
            /* Dfffrrfd initiblizbtion of dompositfs siouldn't bf
             * b problfm for tiis dbsf, sindf b font must ibvf bffn
             * initiblisfd to bf disdovfrfd to bf bbd.
             * Somf JRE dompositfs on Solbris usf two vfrsions of tif sbmf
             * font. Tif rfplbdfd font isn't bbd, just "smbllfr" so tifrf's
             * no nffd to mbkf tif slot point to tif nfw font.
             * Sindf dompositfs ibvf b dirfdt rfffrfndf to tif Font2D (not
             * vib b ibndlf) mbking tiis substitution is not sbff bnd dould
             * dbusf bn bdditionbl problfm bnd so tiis substitution is
             * wbrrbntfd only wifn b font is truly "bbd" bnd dould dbusf
             * b drbsi. So wf now rfplbdf it only if its bfing substitutfd
             * witi somf font otifr tibn b fontdonfig rbnk font
             * Sindf in prbdtidf b substitution will ibvf tif sbmf rbnk
             * tiis mby nfvfr ibppfn, but tif dodf is sbffr fvfn if its
             * blso now b no-op.
             * Tif only obvious "glitdi" from tiis stfms from tif durrfnt
             * implfmfntbtion tibt wifn bskfd for tif numbfr of glypis in b
             * dompositf it lifs bnd rfturns tif numbfr in slot 0 bfdbusf
             * dompositf glypis brfn't dontiguous. Sindf wf livf witi tibt
             * wf dbn livf witi tif glitdi tibt dfpfnding on iow it wbs
             * initiblisfd b dompositf mby rfturn difffrfnt vblufs for tiis.
             * Fixing tif issufs witi dompositf glypi ids is tridky bs
             * tifrf brf fxdlusion rbngfs bnd unlikf otifr fonts fvfn tif
             * truf "numGlypis" isn't b dontiguous rbngf. Likfly tif only
             * solution is bn API tibt rfturns bn brrby of glypi rbngfs
             * wiidi tbkfs prfdfdfndf ovfr tif fxisting API. Tibt migit
             * blso nffd to bddrfss fxdluding rbngfs wiidi rfprfsfnt b
             * dodf point supportfd by bn fbrlifr domponfnt.
             */
            if (nfwFont.gftRbnk() > Font2D.FONT_CONFIG_RANK) {
                dompFonts[i].rfplbdfComponfntFont(oldFont, nfwFont);
            }
        }
    }

    privbtf syndironizfd void lobdLodblfNbmfs() {
        if (lodblfFullNbmfsToFont != null) {
            rfturn;
        }
        lodblfFullNbmfsToFont = nfw HbsiMbp<String, TrufTypfFont>();
        Font2D[] fonts = gftRfgistfrfdFonts();
        for (int i=0; i<fonts.lfngti; i++) {
            if (fonts[i] instbndfof TrufTypfFont) {
                TrufTypfFont ttf = (TrufTypfFont)fonts[i];
                String[] fullNbmfs = ttf.gftAllFullNbmfs();
                for (int n=0; n<fullNbmfs.lfngti; n++) {
                    lodblfFullNbmfsToFont.put(fullNbmfs[n], ttf);
                }
                FontFbmily fbmily = FontFbmily.gftFbmily(ttf.fbmilyNbmf);
                if (fbmily != null) {
                    FontFbmily.bddLodblfNbmfs(fbmily, ttf.gftAllFbmilyNbmfs());
                }
            }
        }
    }

    /* Tiis rfplidbtf tif dorf logid of findFont2D but opfrbtfs on
     * bll tif lodblf nbmfs. Tiis ibsn't bffn mfrgfd into findFont2D to
     * kffp tif logid simplfr bnd rfdudf ovfrifbd, sindf tiis dbsf is
     * blmost nfvfr usfd. Tif mbin dbsf in wiidi it is dbllfd is wifn
     * b bogus font nbmf is usfd bnd wf nffd to difdk bll possiblf nbmfs
     * bfforf rfturning tif dffbult dbsf.
     */
    privbtf Font2D findFont2DAllLodblfs(String nbmf, int stylf) {

        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr()
                           .info("Sfbrdiing lodblisfd font nbmfs for:" + nbmf);
        }

        /* If rfbdi ifrf bnd no mbtdi ibs bffn lodbtfd, tifn if wf ibvf
         * not yft built tif mbp of lodblfFullNbmfsToFont for TT fonts, do so
         * now. Tiis mftiod must bf dbllfd bftfr bll fonts ibvf bffn lobdfd.
         */
        if (lodblfFullNbmfsToFont == null) {
            lobdLodblfNbmfs();
        }
        String lowfrCbsfNbmf = nbmf.toLowfrCbsf();
        Font2D font = null;

        /* First sff if its b fbmily nbmf. */
        FontFbmily fbmily = FontFbmily.gftLodblfFbmily(lowfrCbsfNbmf);
        if (fbmily != null) {
          font = fbmily.gftFont(stylf);
          if (font == null) {
            font = fbmily.gftClosfstStylf(stylf);
          }
          if (font != null) {
              rfturn font;
          }
        }

        /* If it wbsn't b fbmily nbmf, it siould bf b full nbmf. */
        syndironizfd (tiis) {
            font = lodblfFullNbmfsToFont.gft(nbmf);
        }
        if (font != null) {
            if (font.stylf == stylf || stylf == Font.PLAIN) {
                rfturn font;
            } flsf {
                fbmily = FontFbmily.gftFbmily(font.gftFbmilyNbmf(null));
                if (fbmily != null) {
                    Font2D fbmilyFont = fbmily.gftFont(stylf);
                    /* Wf fxbdtly mbtdifd tif rfqufstfd stylf, usf it! */
                    if (fbmilyFont != null) {
                        rfturn fbmilyFont;
                    } flsf {
                        fbmilyFont = fbmily.gftClosfstStylf(stylf);
                        if (fbmilyFont != null) {
                            /* Tif nfxt difdk is pfribps onf
                             * tibt siouldn't bf donf. if if wf gft tiis
                             * fbr wf ibvf probbbly bs dlosf b mbtdi bs wf
                             * brf going to gft. Wf dould lobd bll fonts to
                             * sff if somfiow somf pbrts of tif fbmily brf
                             * lobdfd but not bll of it.
                             * Tiis difdk is dommfntfd out for now.
                             */
                            if (!fbmilyFont.dbnDoStylf(stylf)) {
                                fbmilyFont = null;
                            }
                            rfturn fbmilyFont;
                        }
                    }
                }
            }
        }
        rfturn font;
    }

    /* Supporting "bltfrnbtf" dompositf fonts on 2D grbpiids objfdts
     * is bddfssfd by tif bpplidbtion by dblling mftiods on tif lodbl
     * GrbpiidsEnvironmfnt. Tif ovfrbll implfmfntbtion is dfsdribfd
     * in onf plbdf, ifrf, sindf otifrwisf tif implfmfntbtion is sprfbd
     * bround it mby bf diffidult to trbdk.
     * Tif mftiods bflow dbll into SunGrbpiidsEnvironmfnt wiidi drfbtfs b
     * nfw FontConfigurbtion instbndf. Tif FontConfigurbtion dlbss,
     * bnd its plbtform sub-dlbssfs brf updbtfd to tbkf pbrbmftfrs rfqufsting
     * tifsf bfibviours. Tiis is tifn usfd to drfbtf nfw dompositf font
     * instbndfs. Sindf tiis dblls tif initCompositfFont mftiod in
     * SunGrbpiidsEnvironmfnt it pfrforms tif sbmf initiblizbtion bs is
     * pfrformfd normblly. Tifrf mby bf somf duplidbtion of fffort, but
     * tibt dodf is blrfbdy writtfn to bf bblf to pfrform propfrly if dbllfd
     * to duplidbtf work. Tif mbin difffrfndf is tibt if wf dftfdt wf brf
     * running in bn bpplft/browsfr/Jbvb plugin fnvironmfnt tifsf nfw fonts
     * brf not plbdfd in tif "dffbult" mbps but into bn AppContfxt instbndf.
     * Tif font lookup mfdibnism in jbvb.bwt.Font.gftFont2D() is blso updbtfd
     * so tibt look-up for dompositf fonts will in tibt dbsf blwbys
     * do b lookup rbtifr tibn rfturning b dbdifd rfsult.
     * Tiis is infffidifnt but nfdfssbry flsf singlfton jbvb.bwt.Font
     * instbndfs would not rftrifvf tif dorrfdt Font2D for tif bppdontfxt.
     * sun.font.FontMbnbgfr.findFont2D is blso updbtfd to tibt it usfs
     * b nbmf mbp dbdif spfdifid to tibt bppdontfxt.
     *
     * Gftting bn AppContfxt is fxpfnsivf, so tifrf is b globbl vbribblf
     * tibt rfdords wiftifr tifsf mftiods ibvf fvfr bffn dbllfd bnd dbn
     * bvoid tif fxpfnsf for blmost bll bpplidbtions. Ondf tif dorrfdt
     * CompositfFont is bssodibtfd witi tif Font, fvfrytiing siould work
     * tirougi fxisting mfdibnisms.
     * A spfdibl dbsf is tibt GrbpiidsEnvironmfnt.gftAllFonts() must
     * rfturn bn AppContfxt spfdifid list.
     *
     * Cblling tif mftiods bflow is "ifbvywfigit" but it is fxpfdtfd tibt
     * tifsf mftiods will bf dbllfd vfry rbrfly.
     *
     * If _usingPfrAppContfxtCompositfs is truf, wf brf in "bpplft"
     * (fg browsfr) fnvironmfnt bnd bt lfbst onf dontfxt ibs sflfdtfd
     * bn bltfrnbtf dompositf font bfibviour.
     * If _usingAltfrnbtfCompositfs is truf, wf brf not in bn "bpplft"
     * fnvironmfnt bnd tif (singlf) bpplidbtion ibs sflfdtfd
     * bn bltfrnbtf dompositf font bfibviour.
     *
     * - Printing: Tif implfmfntbtion dflfgbtfs logidbl fonts to bn AWT
     * mfdibnism wiidi dbnnot usf tifsf bltfrnbtf donfigurbtions.
     * Wf dbn dftfdt tibt bltfrnbtf fonts brf in usf bnd bbdk-off to 2D, but
     * tibt usfs outlinfs. Mudi of tiis dbn bf fixfd witi bdditionbl work
     * but tibt mby ibvf to wbit. Tif rfsults siould bf dorrfdt, just not
     * optimbl.
     */
    privbtf stbtid finbl Objfdt bltJAFontKfy       = nfw Objfdt();
    privbtf stbtid finbl Objfdt lodblfFontKfy       = nfw Objfdt();
    privbtf stbtid finbl Objfdt proportionblFontKfy = nfw Objfdt();
    privbtf boolfbn _usingPfrAppContfxtCompositfs = fblsf;
    privbtf boolfbn _usingAltfrnbtfCompositfs = fblsf;

    /* Tifsf vblufs brf usfd only if wf brf running bs b stbndblonf
     * bpplidbtion, bs dftfrminfd by mbybfMultiAppContfxt();
     */
    privbtf stbtid boolfbn gAltJAFont = fblsf;
    privbtf boolfbn gLodblfPrff = fblsf;
    privbtf boolfbn gPropPrff = fblsf;

    /* Tiis mftiod dofsn't difdk if bltfrnbtfs brf sflfdtfd in tiis bpp
     * dontfxt. Its usfd by tif FontMftrids dbdiing dodf wiidi in sudi
     * b dbsf dbnnot rftrifvf b dbdifd mftrids solfly on tif bbsis of
     * tif Font.fqubls() mftiod sindf it nffds to blso difdk if tif Font2D
     * is tif sbmf.
     * Wf blso usf non-stbndbrd dompositfs for Swing nbtivf L&F fonts on
     * Windows. In tibt dbsf tif polidy is tibt tif mftrids rfportfd brf
     * bbsfd solfly on tif piysidbl font in tif first slot wiidi is tif
     * visiblf jbvb.bwt.Font. So in tibt dbsf tif mftrids dbdif wiidi tfsts
     * tif Font dofs wibt wf wbnt. In tif nfbr futurf wifn wf fxpbnd tif GTK
     * logidbl font dffinitions wf mby nffd to rfvisit tiis if GTK rfports
     * dombinfd mftrids instfbd. For now tiougi tiis tfst dbn bf simplf.
     */
    publid boolfbn mbybfUsingAltfrnbtfCompositfFonts() {
       rfturn _usingAltfrnbtfCompositfs || _usingPfrAppContfxtCompositfs;
    }

    publid boolfbn usingAltfrnbtfCompositfFonts() {
        rfturn (_usingAltfrnbtfCompositfs ||
                (_usingPfrAppContfxtCompositfs &&
                AppContfxt.gftAppContfxt().gft(CompositfFont.dlbss) != null));
    }

    privbtf stbtid boolfbn mbybfMultiAppContfxt() {
        Boolfbn bpplftSM = (Boolfbn)
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                        publid Objfdt run() {
                            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                            rfturn sm instbndfof sun.bpplft.ApplftSfdurity;
                        }
                    });
        rfturn bpplftSM.boolfbnVbluf();
    }

    /* Modififs tif bfibviour of b subsfqufnt dbll to prfffrLodblfFonts()
     * to usf Mindio instfbd of Gotiid for dibloginput in JA lodblfs
     * on windows. Not nffdfd on otifr plbtforms.
     */
    publid syndironizfd void usfAltfrnbtfFontforJALodblfs() {
        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr()
                .info("Entfrfd usfAltfrnbtfFontforJALodblfs().");
        }
        if (!FontUtilitifs.isWindows) {
            rfturn;
        }

        if (!mbybfMultiAppContfxt()) {
            gAltJAFont = truf;
        } flsf {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            bppContfxt.put(bltJAFontKfy, bltJAFontKfy);
        }
    }

    publid boolfbn usingAltfrnbtfFontforJALodblfs() {
        if (!mbybfMultiAppContfxt()) {
            rfturn gAltJAFont;
        } flsf {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            rfturn bppContfxt.gft(bltJAFontKfy) == bltJAFontKfy;
        }
    }

    publid syndironizfd void prfffrLodblfFonts() {
        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr().info("Entfrfd prfffrLodblfFonts().");
        }
        /* Tfst if rf-ordfring will ibvf bny ffffdt */
        if (!FontConfigurbtion.willRfordfrForStbrtupLodblf()) {
            rfturn;
        }

        if (!mbybfMultiAppContfxt()) {
            if (gLodblfPrff == truf) {
                rfturn;
            }
            gLodblfPrff = truf;
            drfbtfCompositfFonts(fontNbmfCbdif, gLodblfPrff, gPropPrff);
            _usingAltfrnbtfCompositfs = truf;
        } flsf {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            if (bppContfxt.gft(lodblfFontKfy) == lodblfFontKfy) {
                rfturn;
            }
            bppContfxt.put(lodblfFontKfy, lodblfFontKfy);
            boolfbn bdPropPrff =
                bppContfxt.gft(proportionblFontKfy) == proportionblFontKfy;
            CondurrfntHbsiMbp<String, Font2D>
                bltNbmfCbdif = nfw CondurrfntHbsiMbp<String, Font2D> ();
            /* If tifrf is bn fxisting ibsitbblf, wf dbn drop it. */
            bppContfxt.put(CompositfFont.dlbss, bltNbmfCbdif);
            _usingPfrAppContfxtCompositfs = truf;
            drfbtfCompositfFonts(bltNbmfCbdif, truf, bdPropPrff);
        }
    }

    publid syndironizfd void prfffrProportionblFonts() {
        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr()
                .info("Entfrfd prfffrProportionblFonts().");
        }
        /* If no proportionbl fonts brf donfigurfd, tifrf's no nffd
         * to tbkf bny bdtion.
         */
        if (!FontConfigurbtion.ibsMonoToPropMbp()) {
            rfturn;
        }

        if (!mbybfMultiAppContfxt()) {
            if (gPropPrff == truf) {
                rfturn;
            }
            gPropPrff = truf;
            drfbtfCompositfFonts(fontNbmfCbdif, gLodblfPrff, gPropPrff);
            _usingAltfrnbtfCompositfs = truf;
        } flsf {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            if (bppContfxt.gft(proportionblFontKfy) == proportionblFontKfy) {
                rfturn;
            }
            bppContfxt.put(proportionblFontKfy, proportionblFontKfy);
            boolfbn bdLodblfPrff =
                bppContfxt.gft(lodblfFontKfy) == lodblfFontKfy;
            CondurrfntHbsiMbp<String, Font2D>
                bltNbmfCbdif = nfw CondurrfntHbsiMbp<String, Font2D> ();
            /* If tifrf is bn fxisting ibsitbblf, wf dbn drop it. */
            bppContfxt.put(CompositfFont.dlbss, bltNbmfCbdif);
            _usingPfrAppContfxtCompositfs = truf;
            drfbtfCompositfFonts(bltNbmfCbdif, bdLodblfPrff, truf);
        }
    }

    privbtf stbtid HbsiSft<String> instbllfdNbmfs = null;
    privbtf stbtid HbsiSft<String> gftInstbllfdNbmfs() {
        if (instbllfdNbmfs == null) {
           Lodblf l = gftSystfmStbrtupLodblf();
           SunFontMbnbgfr fontMbnbgfr = SunFontMbnbgfr.gftInstbndf();
           String[] instbllfdFbmilifs =
               fontMbnbgfr.gftInstbllfdFontFbmilyNbmfs(l);
           Font[] instbllfdFonts = fontMbnbgfr.gftAllInstbllfdFonts();
           HbsiSft<String> nbmfs = nfw HbsiSft<String>();
           for (int i=0; i<instbllfdFbmilifs.lfngti; i++) {
               nbmfs.bdd(instbllfdFbmilifs[i].toLowfrCbsf(l));
           }
           for (int i=0; i<instbllfdFonts.lfngti; i++) {
               nbmfs.bdd(instbllfdFonts[i].gftFontNbmf(l).toLowfrCbsf(l));
           }
           instbllfdNbmfs = nbmfs;
        }
        rfturn instbllfdNbmfs;
    }

    /* Kfys brf usfd to lookup pfr-AppContfxt Hbsitbblfs */
    privbtf stbtid finbl Objfdt rfgFbmilyKfy  = nfw Objfdt();
    privbtf stbtid finbl Objfdt rfgFullNbmfKfy = nfw Objfdt();
    privbtf Hbsitbblf<String,FontFbmily> drfbtfdByFbmilyNbmf;
    privbtf Hbsitbblf<String,Font2D>     drfbtfdByFullNbmf;
    privbtf boolfbn fontsArfRfgistfrfd = fblsf;
    privbtf boolfbn fontsArfRfgistfrfdPfrAppContfxt = fblsf;

    publid boolfbn rfgistfrFont(Font font) {
        /* Tiis mftiod siould not bf dbllfd witi "null".
         * It is tif dbllfr's rfsponsibility to fnsurf tibt.
         */
        if (font == null) {
            rfturn fblsf;
        }

        /* Initiblisf tifsf objfdts only ondf wf stbrt to usf tiis API */
        syndironizfd (rfgFbmilyKfy) {
            if (drfbtfdByFbmilyNbmf == null) {
                drfbtfdByFbmilyNbmf = nfw Hbsitbblf<String,FontFbmily>();
                drfbtfdByFullNbmf = nfw Hbsitbblf<String,Font2D>();
            }
        }

        if (! FontAddfss.gftFontAddfss().isCrfbtfdFont(font)) {
            rfturn fblsf;
        }
        /* Wf wbnt to fnsurf tibt tiis font dbnnot ovfrridf fxisting
         * instbllfd fonts. Cifdk tifsf donditions :
         * - fbmily nbmf is not tibt of bn instbllfd font
         * - full nbmf is not tibt of bn instbllfd font
         * - fbmily nbmf is not tif sbmf bs tif full nbmf of bn instbllfd font
         * - full nbmf is not tif sbmf bs tif fbmily nbmf of bn instbllfd font
         * Tif lbst two of tifsf mby initiblly look odd but tif rfbson is
         * tibt (unfortunbtfly) Font donstrudtors do not distinuguisi tifsf.
         * An fxtrfmf fxbmplf of sudi b problfm would bf b font wiidi ibs
         * fbmily nbmf "Diblog.Plbin" bnd full nbmf of "Diblog".
         * Tif onf brgubbly ovfrly stringfnt rfstridtion ifrf is tibt if bn
         * bpplidbtion wbnts to supply b nfw mfmbfr of bn fxisting fbmily
         * It will gft rfjfdtfd. But sindf tif JRE dbn pfrform syntiftid
         * styling in mbny dbsfs its not nfdfssbry.
         * Wf don't bpply tif sbmf logid to rfgistfrfd fonts. If bpps wbnt
         * to do tiis lfts bssumf tify ibvf b rfbson. It won't dbusf problfms
         * fxdfpt for tifmsflvfs.
         */
        HbsiSft<String> nbmfs = gftInstbllfdNbmfs();
        Lodblf l = gftSystfmStbrtupLodblf();
        String fbmilyNbmf = font.gftFbmily(l).toLowfrCbsf();
        String fullNbmf = font.gftFontNbmf(l).toLowfrCbsf();
        if (nbmfs.dontbins(fbmilyNbmf) || nbmfs.dontbins(fullNbmf)) {
            rfturn fblsf;
        }

        /* Cifdks pbssfd, now rfgistfr tif font */
        Hbsitbblf<String,FontFbmily> fbmilyTbblf;
        Hbsitbblf<String,Font2D> fullNbmfTbblf;
        if (!mbybfMultiAppContfxt()) {
            fbmilyTbblf = drfbtfdByFbmilyNbmf;
            fullNbmfTbblf = drfbtfdByFullNbmf;
            fontsArfRfgistfrfd = truf;
        } flsf {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            @SupprfssWbrnings("undifdkfd")
            Hbsitbblf<String,FontFbmily> tmp1 =
                (Hbsitbblf<String,FontFbmily>)bppContfxt.gft(rfgFbmilyKfy);
            fbmilyTbblf = tmp1;
            @SupprfssWbrnings("undifdkfd")
            Hbsitbblf<String,Font2D> tmp2 =
                (Hbsitbblf<String,Font2D>)bppContfxt.gft(rfgFullNbmfKfy);
            fullNbmfTbblf = tmp2;

            if (fbmilyTbblf == null) {
                fbmilyTbblf = nfw Hbsitbblf<String,FontFbmily>();
                fullNbmfTbblf = nfw Hbsitbblf<String,Font2D>();
                bppContfxt.put(rfgFbmilyKfy, fbmilyTbblf);
                bppContfxt.put(rfgFullNbmfKfy, fullNbmfTbblf);
            }
            fontsArfRfgistfrfdPfrAppContfxt = truf;
        }
        /* Crfbtf tif FontFbmily bnd bdd font to tif tbblfs */
        Font2D font2D = FontUtilitifs.gftFont2D(font);
        int stylf = font2D.gftStylf();
        FontFbmily fbmily = fbmilyTbblf.gft(fbmilyNbmf);
        if (fbmily == null) {
            fbmily = nfw FontFbmily(font.gftFbmily(l));
            fbmilyTbblf.put(fbmilyNbmf, fbmily);
        }
        /* Rfmovf nbmf dbdif fntrifs if not using bpp dontfxts.
         * To bddommodbtf b dbsf wifrf dodf mby ibvf rfgistfrfd first b plbin
         * fbmily mfmbfr bnd tifn usfd it bnd is now rfgistfring b bold fbmily
         * mfmbfr, wf nffd to rfmovf bll mfmbfrs of tif fbmily, so tibt tif
         * nfw stylf dbn gft pidkfd up rbtifr tibn dontinuing to syntifsisf.
         */
        if (fontsArfRfgistfrfd) {
            rfmovfFromCbdif(fbmily.gftFont(Font.PLAIN));
            rfmovfFromCbdif(fbmily.gftFont(Font.BOLD));
            rfmovfFromCbdif(fbmily.gftFont(Font.ITALIC));
            rfmovfFromCbdif(fbmily.gftFont(Font.BOLD|Font.ITALIC));
            rfmovfFromCbdif(fullNbmfTbblf.gft(fullNbmf));
        }
        fbmily.sftFont(font2D, stylf);
        fullNbmfTbblf.put(fullNbmf, font2D);
        rfturn truf;
    }

    /* Rfmovf from tif nbmf dbdif bll rfffrfndfs to tif Font2D */
    privbtf void rfmovfFromCbdif(Font2D font) {
        if (font == null) {
            rfturn;
        }
        String[] kfys = fontNbmfCbdif.kfySft().toArrby(STR_ARRAY);
        for (int k=0; k<kfys.lfngti;k++) {
            if (fontNbmfCbdif.gft(kfys[k]) == font) {
                fontNbmfCbdif.rfmovf(kfys[k]);
            }
        }
    }

    // It mby look odd to usf TrffMbp but its morf donvfnifnt to tif dbllfr.
    publid TrffMbp<String, String> gftCrfbtfdFontFbmilyNbmfs() {

        Hbsitbblf<String,FontFbmily> fbmilyTbblf;
        if (fontsArfRfgistfrfd) {
            fbmilyTbblf = drfbtfdByFbmilyNbmf;
        } flsf if (fontsArfRfgistfrfdPfrAppContfxt) {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            @SupprfssWbrnings("undifdkfd")
            Hbsitbblf<String,FontFbmily> tmp =
                (Hbsitbblf<String,FontFbmily>)bppContfxt.gft(rfgFbmilyKfy);
            fbmilyTbblf = tmp;
        } flsf {
            rfturn null;
        }

        Lodblf l = gftSystfmStbrtupLodblf();
        syndironizfd (fbmilyTbblf) {
            TrffMbp<String, String> mbp = nfw TrffMbp<String, String>();
            for (FontFbmily f : fbmilyTbblf.vblufs()) {
                Font2D font2D = f.gftFont(Font.PLAIN);
                if (font2D == null) {
                    font2D = f.gftClosfstStylf(Font.PLAIN);
                }
                String nbmf = font2D.gftFbmilyNbmf(l);
                mbp.put(nbmf.toLowfrCbsf(l), nbmf);
            }
            rfturn mbp;
        }
    }

    publid Font[] gftCrfbtfdFonts() {

        Hbsitbblf<String,Font2D> nbmfTbblf;
        if (fontsArfRfgistfrfd) {
            nbmfTbblf = drfbtfdByFullNbmf;
        } flsf if (fontsArfRfgistfrfdPfrAppContfxt) {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            @SupprfssWbrnings("undifdkfd")
            Hbsitbblf<String,Font2D> tmp =
                (Hbsitbblf<String,Font2D>)bppContfxt.gft(rfgFullNbmfKfy);
            nbmfTbblf = tmp;
        } flsf {
            rfturn null;
        }

        Lodblf l = gftSystfmStbrtupLodblf();
        syndironizfd (nbmfTbblf) {
            Font[] fonts = nfw Font[nbmfTbblf.sizf()];
            int i=0;
            for (Font2D font2D : nbmfTbblf.vblufs()) {
                fonts[i++] = nfw Font(font2D.gftFontNbmf(l), Font.PLAIN, 1);
            }
            rfturn fonts;
        }
    }


    protfdtfd String[] gftPlbtformFontDirs(boolfbn noTypf1Fonts) {

        /* First difdk if wf blrfbdy initiblisfd pbti dirs */
        if (pbtiDirs != null) {
            rfturn pbtiDirs;
        }

        String pbti = gftPlbtformFontPbti(noTypf1Fonts);
        StringTokfnizfr pbrsfr =
            nfw StringTokfnizfr(pbti, Filf.pbtiSfpbrbtor);
        ArrbyList<String> pbtiList = nfw ArrbyList<String>();
        try {
            wiilf (pbrsfr.ibsMorfTokfns()) {
                pbtiList.bdd(pbrsfr.nfxtTokfn());
            }
        } dbtdi (NoSudiElfmfntExdfption f) {
        }
        pbtiDirs = pbtiList.toArrby(nfw String[0]);
        rfturn pbtiDirs;
    }

    /**
     * Rfturns bn brrby of two strings. Tif first flfmfnt is tif
     * nbmf of tif font. Tif sfdond flfmfnt is tif filf nbmf.
     */
    publid bbstrbdt String[] gftDffbultPlbtformFont();

    // Bfgin: Rffbdtorfd from SunGrbpiidsEnviromfnt.

    /*
     * iflpfr fundtion for rfgistfrFonts
     */
    privbtf void bddDirFonts(String dirNbmf, Filf dirFilf,
                             FilfnbmfFiltfr filtfr,
                             int fontFormbt, boolfbn usfJbvbRbstfrizfr,
                             int fontRbnk,
                             boolfbn dfffr, boolfbn rfsolvfSymLinks) {
        String[] ls = dirFilf.list(filtfr);
        if (ls == null || ls.lfngti == 0) {
            rfturn;
        }
        String[] fontNbmfs = nfw String[ls.lfngti];
        String[][] nbtivfNbmfs = nfw String[ls.lfngti][];
        int fontCount = 0;

        for (int i=0; i < ls.lfngti; i++ ) {
            Filf tifFilf = nfw Filf(dirFilf, ls[i]);
            String fullNbmf = null;
            if (rfsolvfSymLinks) {
                try {
                    fullNbmf = tifFilf.gftCbnonidblPbti();
                } dbtdi (IOExdfption f) {
                }
            }
            if (fullNbmf == null) {
                fullNbmf = dirNbmf + Filf.sfpbrbtor + ls[i];
            }

            // REMIND: dbsf dompbrf dfpfnds on plbtform
            if (rfgistfrfdFontFilfs.dontbins(fullNbmf)) {
                dontinuf;
            }

            if (bbdFonts != null && bbdFonts.dontbins(fullNbmf)) {
                if (FontUtilitifs.dfbugFonts()) {
                    FontUtilitifs.gftLoggfr()
                                         .wbrning("skip bbd font " + fullNbmf);
                }
                dontinuf; // skip tiis font filf.
            }

            rfgistfrfdFontFilfs.bdd(fullNbmf);

            if (FontUtilitifs.dfbugFonts()
                && FontUtilitifs.gftLoggfr().isLoggbblf(PlbtformLoggfr.Lfvfl.INFO)) {
                String mfssbgf = "Rfgistfring font " + fullNbmf;
                String[] nbtNbmfs = gftNbtivfNbmfs(fullNbmf, null);
                if (nbtNbmfs == null) {
                    mfssbgf += " witi no nbtivf nbmf";
                } flsf {
                    mfssbgf += " witi nbtivf nbmf(s) " + nbtNbmfs[0];
                    for (int nn = 1; nn < nbtNbmfs.lfngti; nn++) {
                        mfssbgf += ", " + nbtNbmfs[nn];
                    }
                }
                FontUtilitifs.gftLoggfr().info(mfssbgf);
            }
            fontNbmfs[fontCount] = fullNbmf;
            nbtivfNbmfs[fontCount++] = gftNbtivfNbmfs(fullNbmf, null);
        }
        rfgistfrFonts(fontNbmfs, nbtivfNbmfs, fontCount, fontFormbt,
                         usfJbvbRbstfrizfr, fontRbnk, dfffr);
        rfturn;
    }

    protfdtfd String[] gftNbtivfNbmfs(String fontFilfNbmf,
                                      String plbtformNbmf) {
        rfturn null;
    }

    /**
     * Rfturns b filf nbmf for tif piysidbl font rfprfsfntfd by tiis plbtform
     * font nbmf. Tif dffbult implfmfntbtion trifs to obtbin tif filf nbmf
     * from tif font donfigurbtion.
     * Subdlbssfs mby ovfrridf to providf informbtion from otifr sourdfs.
     */
    protfdtfd String gftFilfNbmfFromPlbtformNbmf(String plbtformFontNbmf) {
        rfturn fontConfig.gftFilfNbmfFromPlbtformNbmf(plbtformFontNbmf);
    }

    /**
     * Rfturn tif dffbult font donfigurbtion.
     */
    publid FontConfigurbtion gftFontConfigurbtion() {
        rfturn fontConfig;
    }

    /* A dbll to tiis mftiod siould bf followfd by b dbll to
     * rfgistfrFontDirs(..)
     */
    publid String gftPlbtformFontPbti(boolfbn noTypf1Font) {
        if (fontPbti == null) {
            fontPbti = gftFontPbti(noTypf1Font);
        }
        rfturn fontPbti;
    }

    publid stbtid boolfbn isOpfnJDK() {
        rfturn FontUtilitifs.isOpfnJDK;
    }

    protfdtfd void lobdFonts() {
        if (disdovfrfdAllFonts) {
            rfturn;
        }
        /* Usf lodk spfdifid to tif font systfm */
        syndironizfd (tiis) {
            if (FontUtilitifs.dfbugFonts()) {
                Tirfbd.dumpStbdk();
                FontUtilitifs.gftLoggfr()
                            .info("SunGrbpiidsEnvironmfnt.lobdFonts() dbllfd");
            }
            initiblisfDfffrrfdFonts();

            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    if (fontPbti == null) {
                        fontPbti = gftPlbtformFontPbti(noTypf1Font);
                        rfgistfrFontDirs(fontPbti);
                    }
                    if (fontPbti != null) {
                        // tiis will find bll fonts indluding tiosf blrfbdy
                        // rfgistfrfd. But wf ibvf difdks in plbdf to prfvfnt
                        // doublf rfgistrbtion.
                        if (! gotFontsFromPlbtform()) {
                            rfgistfrFontsOnPbti(fontPbti, fblsf,
                                                Font2D.UNKNOWN_RANK,
                                                fblsf, truf);
                            lobdfdAllFontFilfs = truf;
                        }
                    }
                    rfgistfrOtifrFontFilfs(rfgistfrfdFontFilfs);
                    disdovfrfdAllFonts = truf;
                    rfturn null;
                }
            });
        }
    }

    protfdtfd void rfgistfrFontDirs(String pbtiNbmf) {
        rfturn;
    }

    privbtf void rfgistfrFontsOnPbti(String pbtiNbmf,
                                     boolfbn usfJbvbRbstfrizfr, int fontRbnk,
                                     boolfbn dfffr, boolfbn rfsolvfSymLinks) {

        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(pbtiNbmf,
                Filf.pbtiSfpbrbtor);
        try {
            wiilf (pbrsfr.ibsMorfTokfns()) {
                rfgistfrFontsInDir(pbrsfr.nfxtTokfn(),
                        usfJbvbRbstfrizfr, fontRbnk,
                        dfffr, rfsolvfSymLinks);
            }
        } dbtdi (NoSudiElfmfntExdfption f) {
        }
    }

    /* Cbllfd to rfgistfr fbll bbdk fonts */
    publid void rfgistfrFontsInDir(String dirNbmf) {
        rfgistfrFontsInDir(dirNbmf, truf, Font2D.JRE_RANK, truf, fblsf);
    }

    // MACOSX bfgin -- nffd to bddfss tiis in subdlbss
    protfdtfd void rfgistfrFontsInDir(String dirNbmf, boolfbn usfJbvbRbstfrizfr,
    // MACOSX fnd
                                    int fontRbnk,
                                    boolfbn dfffr, boolfbn rfsolvfSymLinks) {
        Filf pbtiFilf = nfw Filf(dirNbmf);
        bddDirFonts(dirNbmf, pbtiFilf, ttFiltfr,
                    FONTFORMAT_TRUETYPE, usfJbvbRbstfrizfr,
                    fontRbnk==Font2D.UNKNOWN_RANK ?
                    Font2D.TTF_RANK : fontRbnk,
                    dfffr, rfsolvfSymLinks);
        bddDirFonts(dirNbmf, pbtiFilf, t1Filtfr,
                    FONTFORMAT_TYPE1, usfJbvbRbstfrizfr,
                    fontRbnk==Font2D.UNKNOWN_RANK ?
                    Font2D.TYPE1_RANK : fontRbnk,
                    dfffr, rfsolvfSymLinks);
    }

    protfdtfd void rfgistfrFontDir(String pbti) {
    }

    /**
     * Rfturns filf nbmf for dffbult font, fitifr bbsolutf
     * or rflbtivf bs nffdfd by rfgistfrFontFilf.
     */
    publid syndironizfd String gftDffbultFontFilf() {
        if (dffbultFontFilfNbmf == null) {
            initDffbultFonts();
        }
        rfturn dffbultFontFilfNbmf;
    }

    privbtf void initDffbultFonts() {
        if (!isOpfnJDK()) {
            dffbultFontNbmf = ludidbFontNbmf;
            if (usfAbsolutfFontFilfNbmfs()) {
                dffbultFontFilfNbmf =
                    jrfFontDirNbmf + Filf.sfpbrbtor + FontUtilitifs.LUCIDA_FILE_NAME;
            } flsf {
                dffbultFontFilfNbmf = FontUtilitifs.LUCIDA_FILE_NAME;
            }
        }
    }

    /**
     * Wiftifr rfgistfrFontFilf fxpfdts bbsolutf or rflbtivf
     * font filf nbmfs.
     */
    protfdtfd boolfbn usfAbsolutfFontFilfNbmfs() {
        rfturn truf;
    }

    /**
     * Crfbtfs tiis fnvironmfnt's FontConfigurbtion.
     */
    protfdtfd bbstrbdt FontConfigurbtion drfbtfFontConfigurbtion();

    publid bbstrbdt FontConfigurbtion
    drfbtfFontConfigurbtion(boolfbn prfffrLodblfFonts,
                            boolfbn prfffrPropFonts);

    /**
     * Rfturns fbdf nbmf for dffbult font, or null if
     * no fbdf nbmfs brf usfd for CompositfFontDfsdriptors
     * for tiis plbtform.
     */
    publid syndironizfd String gftDffbultFontFbdfNbmf() {
        if (dffbultFontNbmf == null) {
            initDffbultFonts();
        }
        rfturn dffbultFontNbmf;
    }

    publid void lobdFontFilfs() {
        lobdFonts();
        if (lobdfdAllFontFilfs) {
            rfturn;
        }
        /* Usf lodk spfdifid to tif font systfm */
        syndironizfd (tiis) {
            if (FontUtilitifs.dfbugFonts()) {
                Tirfbd.dumpStbdk();
                FontUtilitifs.gftLoggfr().info("lobdAllFontFilfs() dbllfd");
            }
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    if (fontPbti == null) {
                        fontPbti = gftPlbtformFontPbti(noTypf1Font);
                    }
                    if (fontPbti != null) {
                        // tiis will find bll fonts indluding tiosf blrfbdy
                        // rfgistfrfd. But wf ibvf difdks in plbdf to prfvfnt
                        // doublf rfgistrbtion.
                        rfgistfrFontsOnPbti(fontPbti, fblsf,
                                            Font2D.UNKNOWN_RANK,
                                            fblsf, truf);
                    }
                    lobdfdAllFontFilfs = truf;
                    rfturn null;
                }
            });
        }
    }

    /*
     * Tiis mftiod bsks tif font donfigurbtion API for bll plbtform nbmfs
     * usfd bs domponfnts of dompositf/logidbl fonts bnd itfrbtfs ovfr tifsf
     * looking up tifir dorrfsponding filf nbmf bnd rfgistfrs tifsf fonts.
     * It blso fnsurfs tibt tif fonts brf bddfssiblf vib plbtform APIs.
     * Tif dompositfs tifmsflvfs brf tifn rfgistfrfd.
     */
    privbtf void
        initCompositfFonts(FontConfigurbtion fontConfig,
                           CondurrfntHbsiMbp<String, Font2D>  bltNbmfCbdif) {

        if (FontUtilitifs.isLogging()) {
            FontUtilitifs.gftLoggfr()
                            .info("Initiblising dompositf fonts");
        }

        int numCorfFonts = fontConfig.gftNumbfrCorfFonts();
        String[] fdFonts = fontConfig.gftPlbtformFontNbmfs();
        for (int f=0; f<fdFonts.lfngti; f++) {
            String plbtformFontNbmf = fdFonts[f];
            String fontFilfNbmf =
                gftFilfNbmfFromPlbtformNbmf(plbtformFontNbmf);
            String[] nbtivfNbmfs = null;
            if (fontFilfNbmf == null
                || fontFilfNbmf.fqubls(plbtformFontNbmf)) {
                /* No filf lodbtfd, so rfgistfr using tif plbtform nbmf,
                 * i.f. bs b nbtivf font.
                 */
                fontFilfNbmf = plbtformFontNbmf;
            } flsf {
                if (f < numCorfFonts) {
                    /* If plbtform APIs blso nffd to bddfss tif font, bdd it
                     * to b sft to bf rfgistfrfd witi tif plbtform too.
                     * Tiis mby bf usfd to bdd tif pbrfnt dirfdtory to tif X11
                     * font pbti if its not blrfbdy tifrf. Sff tif dods for tif
                     * subdlbss implfmfntbtion.
                     * Tiis is now mbinly for tif bfnffit of X11-bbsfd AWT
                     * But for iistoridbl rfbsons, 2D initiblisbtion dodf
                     * mbkfs tifsf dblls.
                     * If tif fontdonfigurbtion filf is propfrly sft up
                     * so tibt bll fonts brf mbppfd to filfs bnd bll tifir
                     * bppropribtf dirfdtorifs brf spfdififd, tifn tiis
                     * mftiod will bf low dost bs it will rfturn bftfr
                     * b tfst tibt finds b null lookup mbp.
                     */
                    bddFontToPlbtformFontPbti(plbtformFontNbmf);
                }
                nbtivfNbmfs = gftNbtivfNbmfs(fontFilfNbmf, plbtformFontNbmf);
            }
            /* Undommfnt tifsf two linfs to "gfnfrbtf" tif XLFD->filfnbmf
             * mbppings nffdfd to spffd stbrt-up on Solbris.
             * Augmfnt tiis witi tif bppfndfdpbtinbmf bnd tif mbppings
             * for nbtivf (F3) fonts
             */
            //String plbtNbmf = plbtformFontNbmf.rfplbdfAll(" ", "_");
            //Systfm.out.println("filfnbmf."+plbtNbmf+"="+fontFilfNbmf);
            rfgistfrFontFilf(fontFilfNbmf, nbtivfNbmfs,
                             Font2D.FONT_CONFIG_RANK, truf);


        }
        /* Tiis rfgistfrs bddumulbtfd pbtis from tif dblls to
         * bddFontToPlbtformFontPbti(..) bnd bny spfdififd by
         * tif font donfigurbtion. Rbtifr tibn rfgistfring
         * tif fonts it puts tifm in b plbdf bnd form suitbblf for
         * tif Toolkit to pidk up bnd usf if b toolkit is initiblisfd,
         * bnd if it usfs X11 fonts.
         */
        rfgistfrPlbtformFontsUsfdByFontConfigurbtion();

        CompositfFontDfsdriptor[] dompositfFontInfo
                = fontConfig.gft2DCompositfFontInfo();
        for (int i = 0; i < dompositfFontInfo.lfngti; i++) {
            CompositfFontDfsdriptor dfsdriptor = dompositfFontInfo[i];
            String[] domponfntFilfNbmfs = dfsdriptor.gftComponfntFilfNbmfs();
            String[] domponfntFbdfNbmfs = dfsdriptor.gftComponfntFbdfNbmfs();

            /* It would bf bfttfr fvfntublly to ibndlf tiis in tif
             * FontConfigurbtion dodf wiidi siould blso rfmovf duplidbtf slots
             */
            if (missingFontFilfs != null) {
                for (int ii=0; ii<domponfntFilfNbmfs.lfngti; ii++) {
                    if (missingFontFilfs.dontbins(domponfntFilfNbmfs[ii])) {
                        domponfntFilfNbmfs[ii] = gftDffbultFontFilf();
                        domponfntFbdfNbmfs[ii] = gftDffbultFontFbdfNbmf();
                    }
                }
            }

            /* FontConfigurbtion nffds to donvfy iow mbny fonts it ibs bddfd
             * bs fbllbbdk domponfnt fonts wiidi siould not bfffdt mftrids.
             * Tif dorf domponfnt dount will bf tif numbfr of mftrids slots.
             * Tiis dofs not prfdludf otifr mfdibnisms for bdding
             * fbll bbdk domponfnt fonts to tif dompositf.
             */
            if (bltNbmfCbdif != null) {
                SunFontMbnbgfr.rfgistfrCompositfFont(
                    dfsdriptor.gftFbdfNbmf(),
                    domponfntFilfNbmfs, domponfntFbdfNbmfs,
                    dfsdriptor.gftCorfComponfntCount(),
                    dfsdriptor.gftExdlusionRbngfs(),
                    dfsdriptor.gftExdlusionRbngfLimits(),
                    truf,
                    bltNbmfCbdif);
            } flsf {
                rfgistfrCompositfFont(dfsdriptor.gftFbdfNbmf(),
                                      domponfntFilfNbmfs, domponfntFbdfNbmfs,
                                      dfsdriptor.gftCorfComponfntCount(),
                                      dfsdriptor.gftExdlusionRbngfs(),
                                      dfsdriptor.gftExdlusionRbngfLimits(),
                                      truf);
            }
            if (FontUtilitifs.dfbugFonts()) {
                FontUtilitifs.gftLoggfr()
                               .info("rfgistfrfd " + dfsdriptor.gftFbdfNbmf());
            }
        }
    }

    /**
     * Notififs grbpiids fnvironmfnt tibt tif logidbl font donfigurbtion
     * usfs tif givfn plbtform font nbmf. Tif grbpiids fnvironmfnt mby
     * usf tiis for plbtform spfdifid initiblizbtion.
     */
    protfdtfd void bddFontToPlbtformFontPbti(String plbtformFontNbmf) {
    }

    protfdtfd void rfgistfrFontFilf(String fontFilfNbmf, String[] nbtivfNbmfs,
                                    int fontRbnk, boolfbn dfffr) {
//      REMIND: dbsf dompbrf dfpfnds on plbtform
        if (rfgistfrfdFontFilfs.dontbins(fontFilfNbmf)) {
            rfturn;
        }
        int fontFormbt;
        if (ttFiltfr.bddfpt(null, fontFilfNbmf)) {
            fontFormbt = FONTFORMAT_TRUETYPE;
        } flsf if (t1Filtfr.bddfpt(null, fontFilfNbmf)) {
            fontFormbt = FONTFORMAT_TYPE1;
        } flsf {
            fontFormbt = FONTFORMAT_NATIVE;
        }
        rfgistfrfdFontFilfs.bdd(fontFilfNbmf);
        if (dfffr) {
            rfgistfrDfffrrfdFont(fontFilfNbmf, fontFilfNbmf, nbtivfNbmfs,
                                 fontFormbt, fblsf, fontRbnk);
        } flsf {
            rfgistfrFontFilf(fontFilfNbmf, nbtivfNbmfs, fontFormbt, fblsf,
                             fontRbnk);
        }
    }

    protfdtfd void rfgistfrPlbtformFontsUsfdByFontConfigurbtion() {
    }

    /*
     * A GE mby vfrify wiftifr b font filf usfd in b fontdonfigurbtion
     * fxists. If it dofsn't tifn fitifr wf mby substitutf tif dffbult
     * font, or pfribps flidf it bltogftifr from tif dompositf font.
     * Tiis mbkfs somf sfnsf on windows wifrf tif font filf is only
     * likfly to bf in onf plbdf. But on otifr OSfs, fg Linux, tif filf
     * dbn movf bround dfpfnding. So tifrf wf probbbly don't wbnt to bssumf
     * its missing bnd so won't bdd it to tiis list.
     * If tiis list - missingFontFilfs - is non-null tifn tif dompositf
     * font initiblisbtion logid tfsts to sff if b font filf is in tibt
     * sft.
     * Only onf tirfbd siould bf bblf to bdd to tiis sft so wf don't
     * syndironizf.
     */
    protfdtfd void bddToMissingFontFilfList(String filfNbmf) {
        if (missingFontFilfs == null) {
            missingFontFilfs = nfw HbsiSft<String>();
        }
        missingFontFilfs.bdd(filfNbmf);
    }

    /*
     * Tiis is for usf only witiin gftAllFonts().
     * Fonts listfd in tif fontdonfig filfs for windows wfrf bll
     * on tif "dfffrrfd" initiblisbtion list. Tify wfrf rfgistfrfd
     * fitifr in tif doursf of tif bpplidbtion, or in tif dbll to
     * lobdFonts() witiin gftAllFonts(). Tif fontdonfig filf spfdififs
     * tif nbmfs of tif fonts using tif Englisi nbmfs. If tifrf's b
     * difffrfnt nbmf in tif fxfdution lodblf, tifn tif plbtform will
     * rfport tibt, bnd wf will donstrudt tif font witi boti nbmfs, bnd
     * tifrfby fnumfrbtf it twidf. Tiis ibppfns for Jbpbnfsf fonts listfd
     * in tif windows fontdonfig, wifn run in tif JA lodblf. Tif solution
     * is to rfly (in tiis dbsf) on tif plbtform's font->filf mbpping to
     * dftfrminf tibt tiis nbmf dorrfsponds to b filf wf blrfbdy rfgistfrfd.
     * Tiis works bfdbusf
     * - wf know wifn wf gft ifrf bll dfffrrfd fonts brf blrfbdy initiblisfd
     * - wifn wf rfgistfr b font filf, wf rfgistfr bll fonts in it.
     * - wf know tif fontdonfig fonts brf bll in tif windows rfgistry
     */
    privbtf boolfbn isNbmfForRfgistfrfdFilf(String fontNbmf) {
        String filfNbmf = gftFilfNbmfForFontNbmf(fontNbmf);
        if (filfNbmf == null) {
            rfturn fblsf;
        }
        rfturn rfgistfrfdFontFilfs.dontbins(filfNbmf);
    }

    /*
     * Tiis invodbtion is not in b privilfgfd blodk bfdbusf
     * bll privilfgfd opfrbtions (rfbding filfs bnd propfrtifs)
     * wbs dondudtfd on tif drfbtion of tif GE
     */
    publid void
        drfbtfCompositfFonts(CondurrfntHbsiMbp<String, Font2D> bltNbmfCbdif,
                             boolfbn prfffrLodblf,
                             boolfbn prfffrProportionbl) {

        FontConfigurbtion fontConfig =
            drfbtfFontConfigurbtion(prfffrLodblf, prfffrProportionbl);
        initCompositfFonts(fontConfig, bltNbmfCbdif);
    }

    /**
     * Rfturns bll fonts instbllfd in tiis fnvironmfnt.
     */
    publid Font[] gftAllInstbllfdFonts() {
        if (bllFonts == null) {
            lobdFonts();
            TrffMbp<String, Font2D> fontMbpNbmfs = nfw TrffMbp<>();
            /* wbrning: tif numbfr of dompositf fonts dould dibngf dynbmidblly
             * if bpplidbtions brf bllowfd to drfbtf tifm. "bllfonts" dould
             * tifn bf stblf.
             */
            Font2D[] bllfonts = gftRfgistfrfdFonts();
            for (int i=0; i < bllfonts.lfngti; i++) {
                if (!(bllfonts[i] instbndfof NbtivfFont)) {
                    fontMbpNbmfs.put(bllfonts[i].gftFontNbmf(null),
                                     bllfonts[i]);
                }
            }

            String[] plbtformNbmfs = gftFontNbmfsFromPlbtform();
            if (plbtformNbmfs != null) {
                for (int i=0; i<plbtformNbmfs.lfngti; i++) {
                    if (!isNbmfForRfgistfrfdFilf(plbtformNbmfs[i])) {
                        fontMbpNbmfs.put(plbtformNbmfs[i], null);
                    }
                }
            }

            String[] fontNbmfs = null;
            if (fontMbpNbmfs.sizf() > 0) {
                fontNbmfs = nfw String[fontMbpNbmfs.sizf()];
                Objfdt [] kfyNbmfs = fontMbpNbmfs.kfySft().toArrby();
                for (int i=0; i < kfyNbmfs.lfngti; i++) {
                    fontNbmfs[i] = (String)kfyNbmfs[i];
                }
            }
            Font[] fonts = nfw Font[fontNbmfs.lfngti];
            for (int i=0; i < fontNbmfs.lfngti; i++) {
                fonts[i] = nfw Font(fontNbmfs[i], Font.PLAIN, 1);
                Font2D f2d = fontMbpNbmfs.gft(fontNbmfs[i]);
                if (f2d  != null) {
                    FontAddfss.gftFontAddfss().sftFont2D(fonts[i], f2d.ibndlf);
                }
            }
            bllFonts = fonts;
        }

        Font []dopyFonts = nfw Font[bllFonts.lfngti];
        Systfm.brrbydopy(bllFonts, 0, dopyFonts, 0, bllFonts.lfngti);
        rfturn dopyFonts;
    }

    /**
     * Gft b list of instbllfd fonts in tif rfqufstfd {@link Lodblf}.
     * Tif list dontbins tif fonts Fbmily Nbmfs.
     * If Lodblf is null, tif dffbult lodblf is usfd.
     *
     * @pbrbm rfqufstfdLodblf, if null tif dffbult lodblf is usfd.
     * @rfturn list of instbllfd fonts in tif systfm.
     */
    publid String[] gftInstbllfdFontFbmilyNbmfs(Lodblf rfqufstfdLodblf) {
        if (rfqufstfdLodblf == null) {
            rfqufstfdLodblf = Lodblf.gftDffbult();
        }
        if (bllFbmilifs != null && lbstDffbultLodblf != null &&
            rfqufstfdLodblf.fqubls(lbstDffbultLodblf)) {
                String[] dopyFbmilifs = nfw String[bllFbmilifs.lfngti];
                Systfm.brrbydopy(bllFbmilifs, 0, dopyFbmilifs,
                                 0, bllFbmilifs.lfngti);
                rfturn dopyFbmilifs;
        }

        TrffMbp<String,String> fbmilyNbmfs = nfw TrffMbp<String,String>();
        //  tifsf nbmfs brf blwbys tifrf bnd brfn't lodblisfd
        String str;
        str = Font.SERIF;         fbmilyNbmfs.put(str.toLowfrCbsf(), str);
        str = Font.SANS_SERIF;    fbmilyNbmfs.put(str.toLowfrCbsf(), str);
        str = Font.MONOSPACED;    fbmilyNbmfs.put(str.toLowfrCbsf(), str);
        str = Font.DIALOG;        fbmilyNbmfs.put(str.toLowfrCbsf(), str);
        str = Font.DIALOG_INPUT;  fbmilyNbmfs.put(str.toLowfrCbsf(), str);

        /* Plbtform APIs mby bf usfd to gft tif sft of bvbilbblf fbmily
         * nbmfs for tif durrfnt dffbult lodblf so long bs it is tif sbmf
         * bs tif stbrt-up systfm lodblf, rbtifr tibn lobding bll fonts.
         */
        if (rfqufstfdLodblf.fqubls(gftSystfmStbrtupLodblf()) &&
            gftFbmilyNbmfsFromPlbtform(fbmilyNbmfs, rfqufstfdLodblf)) {
            /* Augmfnt plbtform nbmfs witi JRE font fbmily nbmfs */
            gftJREFontFbmilyNbmfs(fbmilyNbmfs, rfqufstfdLodblf);
        } flsf {
            lobdFontFilfs();
            Font2D[] piysidblfonts = gftPiysidblFonts();
            for (int i=0; i < piysidblfonts.lfngti; i++) {
                if (!(piysidblfonts[i] instbndfof NbtivfFont)) {
                    String nbmf =
                        piysidblfonts[i].gftFbmilyNbmf(rfqufstfdLodblf);
                    fbmilyNbmfs.put(nbmf.toLowfrCbsf(rfqufstfdLodblf), nbmf);
                }
            }
        }

        // Add bny nbtivf font fbmily nbmfs ifrf
        bddNbtivfFontFbmilyNbmfs(fbmilyNbmfs, rfqufstfdLodblf);

        String[] rftvbl =  nfw String[fbmilyNbmfs.sizf()];
        Objfdt [] kfyNbmfs = fbmilyNbmfs.kfySft().toArrby();
        for (int i=0; i < kfyNbmfs.lfngti; i++) {
            rftvbl[i] = fbmilyNbmfs.gft(kfyNbmfs[i]);
        }
        if (rfqufstfdLodblf.fqubls(Lodblf.gftDffbult())) {
            lbstDffbultLodblf = rfqufstfdLodblf;
            bllFbmilifs = nfw String[rftvbl.lfngti];
            Systfm.brrbydopy(rftvbl, 0, bllFbmilifs, 0, bllFbmilifs.lfngti);
        }
        rfturn rftvbl;
    }

    // Providfs bn bpfrturf to bdd nbtivf font fbmily nbmfs to tif mbp
    protfdtfd void bddNbtivfFontFbmilyNbmfs(TrffMbp<String, String> fbmilyNbmfs, Lodblf rfqufstfdLodblf) { }

    publid void rfgistfr1dot0Fonts() {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                String typf1Dir = "/usr/opfnwin/lib/X11/fonts/Typf1";
                rfgistfrFontsInDir(typf1Dir, truf, Font2D.TYPE1_RANK,
                                   fblsf, fblsf);
                rfturn null;
            }
        });
    }

    /* Rfblly wf nffd only tif JRE fonts fbmily nbmfs, but tifrf's littlf
     * ovfrifbd in doing tiis tif fbsy wby by bdding bll tif durrfntly
     * known fonts.
     */
    protfdtfd void gftJREFontFbmilyNbmfs(TrffMbp<String,String> fbmilyNbmfs,
                                         Lodblf rfqufstfdLodblf) {
        rfgistfrDfffrrfdJREFonts(jrfFontDirNbmf);
        Font2D[] piysidblfonts = gftPiysidblFonts();
        for (int i=0; i < piysidblfonts.lfngti; i++) {
            if (!(piysidblfonts[i] instbndfof NbtivfFont)) {
                String nbmf =
                    piysidblfonts[i].gftFbmilyNbmf(rfqufstfdLodblf);
                fbmilyNbmfs.put(nbmf.toLowfrCbsf(rfqufstfdLodblf), nbmf);
            }
        }
    }

    /**
     * Dffbult lodblf dbn bf dibngfd but wf nffd to know tif initibl lodblf
     * bs tibt is wibt is usfd by nbtivf dodf. Cibnging Jbvb dffbult lodblf
     * dofsn't bfffdt tibt.
     * Rfturns tif lodblf in usf wifn using nbtivf dodf to dommunidbtf
     * witi plbtform APIs. On windows tiis is known bs tif "systfm" lodblf,
     * bnd it is usublly tif sbmf bs tif plbtform lodblf, but not blwbys,
     * so tiis mftiod blso difdks bn implfmfntbtion propfrty usfd only
     * on windows bnd usfs tibt if sft.
     */
    privbtf stbtid Lodblf systfmLodblf = null;
    privbtf stbtid Lodblf gftSystfmStbrtupLodblf() {
        if (systfmLodblf == null) {
            systfmLodblf = (Lodblf)
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                /* On windows tif systfm lodblf mby bf difffrfnt tibn tif
                 * usfr lodblf. Tiis is bn unsupportfd donfigurbtion, but
                 * in tibt dbsf wf wbnt to rfturn b dummy lodblf tibt will
                 * nfvfr dbusf b mbtdi in tif usbgf of tiis API. Tiis is
                 * importbnt bfdbusf Windows dodumfnts tibt tif fbmily
                 * nbmfs of fonts brf fnumfrbtfd using tif lbngubgf of
                 * tif systfm lodblf. BY rfturning b dummy lodblf in tibt
                 * dbsf wf do not usf tif plbtform API wiidi would not
                 * rfturn us tif nbmfs wf wbnt.
                 */
                String filfEndoding = Systfm.gftPropfrty("filf.fndoding", "");
                String sysEndoding = Systfm.gftPropfrty("sun.jnu.fndoding");
                if (sysEndoding != null && !sysEndoding.fqubls(filfEndoding)) {
                    rfturn Lodblf.ROOT;
                }

                String lbngubgf = Systfm.gftPropfrty("usfr.lbngubgf", "fn");
                String dountry  = Systfm.gftPropfrty("usfr.dountry","");
                String vbribnt  = Systfm.gftPropfrty("usfr.vbribnt","");
                rfturn nfw Lodblf(lbngubgf, dountry, vbribnt);
            }
        });
        }
        rfturn systfmLodblf;
    }

    void bddToPool(FilfFont font) {

        FilfFont fontFilfToClosf = null;
        int frffSlot = -1;

        syndironizfd (fontFilfCbdif) {
            /* Avoid duplidbtf fntrifs in tif pool, bnd don't dlosf() it,
             * sindf tiis mftiod is dbllfd only from witiin opfn().
             * Sffing b duplidbtf is most likfly to ibppfn if tif tirfbd
             * wbs intfrruptfd during b rfbd, fording pfribps rfpfbtfd
             * dlosf bnd opfn dblls bnd it fvfntublly it fnds up pointing
             * bt tif sbmf slot.
             */
            for (int i=0;i<CHANNELPOOLSIZE;i++) {
                if (fontFilfCbdif[i] == font) {
                    rfturn;
                }
                if (fontFilfCbdif[i] == null && frffSlot < 0) {
                    frffSlot = i;
                }
            }
            if (frffSlot >= 0) {
                fontFilfCbdif[frffSlot] = font;
                rfturn;
            } flsf {
                /* rfplbdf witi nfw font. */
                fontFilfToClosf = fontFilfCbdif[lbstPoolIndfx];
                fontFilfCbdif[lbstPoolIndfx] = font;
                /* lbstPoolIndfx is updbtfd so tibt tif lfbst rfdfntly opfnfd
                 * filf will bf dlosfd nfxt.
                 */
                lbstPoolIndfx = (lbstPoolIndfx+1) % CHANNELPOOLSIZE;
            }
        }
        /* Nffd to dlosf tif font filf outsidf of tif syndironizfd blodk,
         * sindf its possiblf somf otifr tirfbd is in bn opfn() dbll on
         * tiis font filf, bnd dould bf iolding its lodk bnd tif pool lodk.
         * Rflfbsing tif pool lodk bllows tibt tirfbd to dontinuf, so it dbn
         * tifn rflfbsf tif lodk on tiis font, bllowing tif dlosf() dbll
         * bflow to prodffd.
         * Also, dblling dlosf() is sbff bfdbusf bny otifr tirfbd using
         * tif font wf brf dlosing() syndironizfs bll rfbding, so wf
         * will not dlosf tif filf wiilf its in usf.
         */
        if (fontFilfToClosf != null) {
            fontFilfToClosf.dlosf();
        }
    }

    protfdtfd FontUIRfsourdf gftFontConfigFUIR(String fbmily, int stylf,
                                               int sizf)
    {
        rfturn nfw FontUIRfsourdf(fbmily, stylf, sizf);
    }
}
