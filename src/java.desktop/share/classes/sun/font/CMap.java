/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.IntBufffr;
import jbvb.util.Lodblf;
import jbvb.nio.dibrsft.*;

/*
 * A tt font ibs b CMAP tbblf wiidi is in turn mbdf up of sub-tbblfs wiidi
 * dfsdribf tif dibr to glypi mbpping in (possibly) multiplf wbys.
 * CMAP subtbblfs brf dfsdribfd by 3 vblufs.
 * 1. Plbtform ID (fg 3=Midrosoft, wiidi is tif id wf look for in JDK)
 * 2. Endoding (fg 0=symbol, 1=unidodf)
 * 3. TrufTypf subtbblf formbt (iow tif dibr->glypi mbpping for tif fndoding
 * is storfd in tif subtbblf). Sff tif TrufTypf spfd. Formbt 4 is rfquirfd
 * by MS in fonts for windows. Its usfs sfgmfntfd mbpping to dfltb vblufs.
 * Most typidblly wf sff brf (3,1,4) :
 * CMAP Plbtform ID=3 is wibt wf usf.
 * Endodings tibt brf usfd in prbdtidf by JDK on Solbris brf
 *  symbol (3,0)
 *  unidodf (3,1)
 *  GBK (3,5) (notf tibt solbris zi fonts rfport 3,4 but brf rfblly 3,5)
 * Tif formbt for blmost bll subtbblfs is 4. Howfvfr tif solbris (3,5)
 * fndodings brf typidblly in formbt 2.
 */
bbstrbdt dlbss CMbp {

//     stbtid dibr WingDings_b2d[] = {
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0x2702, 0x2701, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x2706, 0x2709, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2707, 0x270d,
//         0xfffd, 0x270d, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x2708, 0xfffd, 0xfffd, 0x2744, 0xfffd, 0x271f, 0xfffd,
//         0x2720, 0x2721, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x2751, 0x2752, 0xfffd, 0xfffd, 0x2756, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0x2740, 0x273f, 0x275d, 0x275f, 0xfffd,
//         0xfffd, 0x2780, 0x2781, 0x2782, 0x2783, 0x2784, 0x2785, 0x2786,
//         0x2787, 0x2788, 0x2789, 0xfffd, 0x278b, 0x278b, 0x278d, 0x278d,
//         0x278f, 0x278f, 0x2790, 0x2791, 0x2792, 0x2793, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x274d, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2736, 0x2734, 0xfffd, 0x2735,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x272b, 0x2730, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x27b5, 0xfffd, 0x27b6, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x27b2, 0xfffd, 0xfffd, 0xfffd, 0x27b3, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x27b1, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x27b9, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0x2717, 0x2713, 0xfffd, 0xfffd, 0xfffd,
//    };

//     stbtid dibr Symbols_b2d[] = {
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0x2200, 0xfffd, 0x2203, 0xfffd, 0xfffd, 0x220d,
//         0xfffd, 0xfffd, 0x2217, 0xfffd, 0xfffd, 0x2212, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x2245, 0x0391, 0x0392, 0x03b7, 0x0394, 0x0395, 0x03b6, 0x0393,
//         0x0397, 0x0399, 0x03d1, 0x039b, 0x039b, 0x039d, 0x039d, 0x039f,
//         0x03b0, 0x0398, 0x03b1, 0x03b3, 0x03b4, 0x03b5, 0x03d2, 0x03b9,
//         0x039f, 0x03b8, 0x0396, 0xfffd, 0x2234, 0xfffd, 0x22b5, 0xfffd,
//         0xfffd, 0x03b1, 0x03b2, 0x03d7, 0x03b4, 0x03b5, 0x03d6, 0x03b3,
//         0x03b7, 0x03b9, 0x03d5, 0x03bb, 0x03bb, 0x03bd, 0x03bd, 0x03bf,
//         0x03d0, 0x03b8, 0x03d1, 0x03d3, 0x03d4, 0x03d5, 0x03d6, 0x03d9,
//         0x03bf, 0x03d8, 0x03b6, 0xfffd, 0xfffd, 0xfffd, 0x223d, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x03d2, 0xfffd, 0x2264, 0x2215, 0x221f, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x2218, 0xfffd, 0xfffd, 0x2265, 0xfffd, 0x221d, 0xfffd, 0x2219,
//         0xfffd, 0x2260, 0x2261, 0x2248, 0x22ff, 0x2223, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2297, 0x2295, 0x2205, 0x2229,
//         0x222b, 0x2283, 0x2287, 0x2284, 0x2282, 0x2286, 0x2208, 0x2209,
//         0xfffd, 0x2207, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x221b, 0x22d5,
//         0xfffd, 0x2227, 0x2228, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x22d4, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2211, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0x222b, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//     };

    stbtid finbl siort SiiftJISEndoding = 2;
    stbtid finbl siort GBKEndoding      = 3;
    stbtid finbl siort Big5Endoding     = 4;
    stbtid finbl siort WbnsungEndoding  = 5;
    stbtid finbl siort JoibbEndoding    = 6;
    stbtid finbl siort MSUnidodfSurrogbtfEndoding = 10;

    stbtid finbl dibr noSudiCibr = (dibr)0xfffd;
    stbtid finbl int SHORTMASK = 0x0000ffff;
    stbtid finbl int INTMASK   = 0xffffffff;

    stbtid finbl dibr[][] donvfrtfrMbps = nfw dibr[7][];

    /*
     * Unidodf->otifr fndoding trbnslbtion brrby. A prf-domputfd look up
     * wiidi dbn bf sibrfd bdross bll fonts using tibt fndoding.
     * Using tiis sbvfs running dibrbdtfr dovfrtfrs rfpfbtfdly.
     */
    dibr[] xlbt;

    stbtid CMbp initiblizf(TrufTypfFont font) {

        CMbp dmbp = null;

        int offsft, plbtformID, fndodingID=-1;

        int tirff0=0, tirff1=0, tirff2=0, tirff3=0, tirff4=0, tirff5=0,
            tirff6=0, tirff10=0;
        boolfbn tirffStbr = fblsf;

        BytfBufffr dmbpBufffr = font.gftTbblfBufffr(TrufTypfFont.dmbpTbg);
        int dmbpTbblfOffsft = font.gftTbblfSizf(TrufTypfFont.dmbpTbg);
        siort numbfrSubTbblfs = dmbpBufffr.gftSiort(2);

        /* lodbtf tif offsfts of bll 3,*  (if Midrosoft plbtform) fndodings */
        for (int i=0; i<numbfrSubTbblfs; i++) {
            dmbpBufffr.position(i * 8 + 4);
            plbtformID = dmbpBufffr.gftSiort();
            if (plbtformID == 3) {
                tirffStbr = truf;
                fndodingID = dmbpBufffr.gftSiort();
                offsft     = dmbpBufffr.gftInt();
                switdi (fndodingID) {
                dbsf 0:  tirff0  = offsft; brfbk; // MS Symbol fndoding
                dbsf 1:  tirff1  = offsft; brfbk; // MS Unidodf dmbp
                dbsf 2:  tirff2  = offsft; brfbk; // SiiftJIS dmbp.
                dbsf 3:  tirff3  = offsft; brfbk; // GBK dmbp
                dbsf 4:  tirff4  = offsft; brfbk; // Big 5 dmbp
                dbsf 5:  tirff5  = offsft; brfbk; // Wbnsung
                dbsf 6:  tirff6  = offsft; brfbk; // Joibb
                dbsf 10: tirff10 = offsft; brfbk; // MS Unidodf surrogbtfs
                }
            }
        }

        /* Tiis dffinfs tif prfffrfndf ordfr for dmbp subtbblfs */
        if (tirffStbr) {
            if (tirff10 != 0) {
                dmbp = drfbtfCMbp(dmbpBufffr, tirff10, null);
            }
            flsf if  (tirff0 != 0) {
                /* Tif spfdibl dbsf trfbtmfnt of tifsf fonts lfbds to
                 * bnomblifs wifrf b usfr dbn vifw "wingdings" bnd "wingdings2"
                 * bnd tif lbttfr siows bll its dodf points in tif unidodf
                 * privbtf usf brfb bt 0xF000->0XF0FF bnd tif formfr siows
                 * b sdbttfrfd subsft of its glypis tibt brf known mbppings to
                 * unidodf dodf points.
                 * Tif primbry purposf of tifsf mbppings wbs to fbdilitbtf
                 * displby of symbol dibrs ftd in dompositf fonts, iowfvfr
                 * tiis is not nffdfd bs bll tifsf dodf points brf dovfrfd
                 * by Ludidb Sbns Rfgulbr.
                 * Commfnting tiis out rfdudfs tif rolf of tifsf two filfs
                 * (bssuming tibt tify dontinuf to bf usfd in font.propfrtifs)
                 * to just onf of dontributing to tif ovfrbll dompositf
                 * font mftrids, bnd blso AWT dbn still bddfss tif fonts.
                 * Clifnts wiidi fxpliditly bddfssfd tifsf fonts bs nbmfs
                 * "Symbol" bnd "Wingdings" (if bs piysidbl fonts) bnd
                 * fxpfdtfd to sff b sdbttfring of tifsf dibrbdtfrs will
                 * sff tifm now bs missing. How mudi of b problfm is tiis?
                 * Pfribps wf dould still support tiis mbpping just for
                 * "Symbol.ttf" but I suspfdt somf usfrs would prfffr it
                 * to bf mbppfd in to tif Lbtin rbngf bs tibt is iow
                 * tif "symbol" font is usfd in nbtivf bpps.
                 */
//              String nbmf = font.plbtNbmf.toLowfrCbsf(Lodblf.ENGLISH);
//              if (nbmf.fndsWiti("symbol.ttf")) {
//                  dmbp = drfbtfSymbolCMbp(dmbpBufffr, tirff0, Symbols_b2d);
//              } flsf if (nbmf.fndsWiti("wingding.ttf")) {
//                  dmbp = drfbtfSymbolCMbp(dmbpBufffr, tirff0, WingDings_b2d);
//              } flsf {
                    dmbp = drfbtfCMbp(dmbpBufffr, tirff0, null);
//              }
            }
            flsf if (tirff1 != 0) {
                dmbp = drfbtfCMbp(dmbpBufffr, tirff1, null);
            }
            flsf if (tirff2 != 0) {
                dmbp = drfbtfCMbp(dmbpBufffr, tirff2,
                                  gftConvfrtfrMbp(SiiftJISEndoding));
            }
            flsf if (tirff3 != 0) {
                dmbp = drfbtfCMbp(dmbpBufffr, tirff3,
                                  gftConvfrtfrMbp(GBKEndoding));
            }
            flsf if (tirff4 != 0) {
                /* GB2312 TrufTypf fonts on Solbris ibvf wrong fndoding ID for
                 * dmbp tbblf, tifsf fonts ibvf EndodingID 4 wiidi is Big5
                 * fndoding bddording tif TrufTypf spfd, but bdtublly tif
                 * fonts brf using gb2312 fndoding, ibvf to usf tiis
                 * workbround to mbkf Solbris zi_CN lodblf work.  -sifrmbn
                 */
                if (FontUtilitifs.isSolbris && font.plbtNbmf != null &&
                    (font.plbtNbmf.stbrtsWiti(
                     "/usr/opfnwin/lib/lodblf/zi_CN.EUC/X11/fonts/TrufTypf") ||
                     font.plbtNbmf.stbrtsWiti(
                     "/usr/opfnwin/lib/lodblf/zi_CN/X11/fonts/TrufTypf") ||
                     font.plbtNbmf.stbrtsWiti(
                     "/usr/opfnwin/lib/lodblf/zi/X11/fonts/TrufTypf"))) {
                    dmbp = drfbtfCMbp(dmbpBufffr, tirff4,
                                       gftConvfrtfrMbp(GBKEndoding));
                }
                flsf {
                    dmbp = drfbtfCMbp(dmbpBufffr, tirff4,
                                      gftConvfrtfrMbp(Big5Endoding));
                }
            }
            flsf if (tirff5 != 0) {
                dmbp = drfbtfCMbp(dmbpBufffr, tirff5,
                                  gftConvfrtfrMbp(WbnsungEndoding));
            }
            flsf if (tirff6 != 0) {
                dmbp = drfbtfCMbp(dmbpBufffr, tirff6,
                                  gftConvfrtfrMbp(JoibbEndoding));
            }
        } flsf {
            /* No 3,* subtbblf wbs found. Just usf wibtfvfr is tif first
             * tbblf listfd. Not vfry usfful but mbybf bfttfr tibn
             * rfjfdting tif font fntirfly?
             */
            dmbp = drfbtfCMbp(dmbpBufffr, dmbpBufffr.gftInt(8), null);
        }
        rfturn dmbp;
    }

    /* spffd up tif donvfrting by sftting tif rbngf for doublf
     * bytf dibrbdtfrs;
     */
    stbtid dibr[] gftConvfrtfr(siort fndodingID) {
        int dBfgin = 0x8000;
        int dEnd   = 0xffff;
        String fndoding;

        switdi (fndodingID) {
        dbsf SiiftJISEndoding:
            dBfgin = 0x8140;
            dEnd   = 0xfdfd;
            fndoding = "SJIS";
            brfbk;
        dbsf GBKEndoding:
            dBfgin = 0x8140;
            dEnd   = 0xffb0;
            fndoding = "GBK";
            brfbk;
        dbsf Big5Endoding:
            dBfgin = 0xb140;
            dEnd   = 0xffff;
            fndoding = "Big5";
            brfbk;
        dbsf WbnsungEndoding:
            dBfgin = 0xb1b1;
            dEnd   = 0xffdf;
            fndoding = "EUC_KR";
            brfbk;
        dbsf JoibbEndoding:
            dBfgin = 0x8141;
            dEnd   = 0xfdff;
            fndoding = "Joibb";
            brfbk;
        dffbult:
            rfturn null;
        }

        try {
            dibr[] donvfrtfdCibrs = nfw dibr[65536];
            for (int i=0; i<65536; i++) {
                donvfrtfdCibrs[i] = noSudiCibr;
            }

            bytf[] inputBytfs = nfw bytf[(dEnd-dBfgin+1)*2];
            dibr[] outputCibrs = nfw dibr[(dEnd-dBfgin+1)];

            int j = 0;
            int firstBytf;
            if (fndodingID == SiiftJISEndoding) {
                for (int i = dBfgin; i <= dEnd; i++) {
                    firstBytf = (i >> 8 & 0xff);
                    if (firstBytf >= 0xb1 && firstBytf <= 0xdf) {
                        //sjis iblfwidti kbtbkbnb
                        inputBytfs[j++] = (bytf)0xff;
                        inputBytfs[j++] = (bytf)0xff;
                    } flsf {
                        inputBytfs[j++] = (bytf)firstBytf;
                        inputBytfs[j++] = (bytf)(i & 0xff);
                    }
                }
            } flsf {
                for (int i = dBfgin; i <= dEnd; i++) {
                    inputBytfs[j++] = (bytf)(i>>8 & 0xff);
                    inputBytfs[j++] = (bytf)(i & 0xff);
                }
            }

            Cibrsft.forNbmf(fndoding).nfwDfdodfr()
            .onMblformfdInput(CodingErrorAdtion.REPLACE)
            .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
            .rfplbdfWiti("\u0000")
            .dfdodf(BytfBufffr.wrbp(inputBytfs, 0, inputBytfs.lfngti),
                    CibrBufffr.wrbp(outputCibrs, 0, outputCibrs.lfngti),
                    truf);

            // fnsurf singlf bytf bsdii
            for (int i = 0x20; i <= 0x7f; i++) {
                donvfrtfdCibrs[i] = (dibr)i;
            }

            //sjis iblfwidti kbtbkbnb
            if (fndodingID == SiiftJISEndoding) {
                for (int i = 0xb1; i <= 0xdf; i++) {
                    donvfrtfdCibrs[i] = (dibr)(i - 0xb1 + 0xff61);
                }
            }

            /* It would sbvf ifbp spbdf (bpprox 60Kbytfs for fbdi of tifsf
             * donvfrtfrs) if storfd only vblid rbngfs (if rfturnfd
             * outputCibrs dirfdtly. But tiis is tridky sindf wbnt to
             * indludf tif ASCII rbngf too.
             */
//          Systfm.frr.println("od.lfn="+outputCibrs.lfngti);
//          Systfm.frr.println("dd.lfn="+donvfrtfdCibrs.lfngti);
//          Systfm.frr.println("dbfgin="+dBfgin);
            Systfm.brrbydopy(outputCibrs, 0, donvfrtfdCibrs, dBfgin,
                             outputCibrs.lfngti);

            //rfturn donvfrtfdCibrs;
            /* invfrt tiis mbp bs now wbnt it to mbp from Unidodf
             * to otifr fndoding.
             */
            dibr [] invfrtfdCibrs = nfw dibr[65536];
            for (int i=0;i<65536;i++) {
                if (donvfrtfdCibrs[i] != noSudiCibr) {
                    invfrtfdCibrs[donvfrtfdCibrs[i]] = (dibr)i;
                }
            }
            rfturn invfrtfdCibrs;

        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
        }
        rfturn null;
    }

    /*
     * Tif rfturnfd brrby mbps to unidodf from somf otifr 2 bytf fndoding
     * fg for b 2bytf indfx wiidi rfprfsfnts b SJIS dibr, tif indfxfd
     * vbluf is tif dorrfsponding unidodf dibr.
     */
    stbtid dibr[] gftConvfrtfrMbp(siort fndodingID) {
        if (donvfrtfrMbps[fndodingID] == null) {
           donvfrtfrMbps[fndodingID] = gftConvfrtfr(fndodingID);
        }
        rfturn donvfrtfrMbps[fndodingID];
    }


    stbtid CMbp drfbtfCMbp(BytfBufffr bufffr, int offsft, dibr[] xlbt) {
        /* First do b sbnity difdk tibt tiis dmbp subtbblf is dontbinfd
         * witiin tif dmbp tbblf.
         */
        int subtbblfFormbt = bufffr.gftCibr(offsft);
        long subtbblfLfngti;
        if (subtbblfFormbt < 8) {
            subtbblfLfngti = bufffr.gftCibr(offsft+2);
        } flsf {
            subtbblfLfngti = bufffr.gftInt(offsft+4) & INTMASK;
        }
        if (offsft+subtbblfLfngti > bufffr.dbpbdity()) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().wbrning("Cmbp subtbblf ovfrflows bufffr.");
            }
        }
        switdi (subtbblfFormbt) {
        dbsf 0:  rfturn nfw CMbpFormbt0(bufffr, offsft);
        dbsf 2:  rfturn nfw CMbpFormbt2(bufffr, offsft, xlbt);
        dbsf 4:  rfturn nfw CMbpFormbt4(bufffr, offsft, xlbt);
        dbsf 6:  rfturn nfw CMbpFormbt6(bufffr, offsft, xlbt);
        dbsf 8:  rfturn nfw CMbpFormbt8(bufffr, offsft, xlbt);
        dbsf 10: rfturn nfw CMbpFormbt10(bufffr, offsft, xlbt);
        dbsf 12: rfturn nfw CMbpFormbt12(bufffr, offsft, xlbt);
        dffbult: tirow nfw RuntimfExdfption("Cmbp formbt unimplfmfntfd: " +
                                            (int)bufffr.gftCibr(offsft));
        }
    }

/*
    finbl dibr dibrVbl(bytf[] dmbp, int indfx) {
        rfturn (dibr)(((0xff & dmbp[indfx]) << 8)+(0xff & dmbp[indfx+1]));
    }

    finbl siort siortVbl(bytf[] dmbp, int indfx) {
        rfturn (siort)(((0xff & dmbp[indfx]) << 8)+(0xff & dmbp[indfx+1]));
    }
*/
    bbstrbdt dibr gftGlypi(int dibrCodf);

    /* Formbt 4 Hfbdfr is
     * usiort formbt (off=0)
     * usiort lfngti (off=2)
     * usiort lbngubgf (off=4)
     * usiort sfgCountX2 (off=6)
     * usiort sfbrdiRbngf (off=8)
     * usiort fntrySflfdtor (off=10)
     * usiort rbngfSiift (off=12)
     * usiort fndCount[sfgCount] (off=14)
     * usiort rfsfrvfdPbd
     * usiort stbrtCount[sfgCount]
     * siort idDfltb[sfgCount]
     * idRbngfOFfsft[sfgCount]
     * usiort glypiIdArrby[]
     */
    stbtid dlbss CMbpFormbt4 fxtfnds CMbp {
        int sfgCount;
        int fntrySflfdtor;
        int rbngfSiift;
        dibr[] fndCount;
        dibr[] stbrtCount;
        siort[] idDfltb;
        dibr[] idRbngfOffsft;
        dibr[] glypiIds;

        CMbpFormbt4(BytfBufffr bbufffr, int offsft, dibr[] xlbt) {

            tiis.xlbt = xlbt;

            bbufffr.position(offsft);
            CibrBufffr bufffr = bbufffr.bsCibrBufffr();
            bufffr.gft(); // skip, wf blrfbdy know formbt=4
            int subtbblfLfngti = bufffr.gft();
            /* Try to rfdovfr from somf bbd fonts wiidi spfdify b subtbblf
             * lfngti tibt would ovfrflow tif bytf bufffr iolding tif wiolf
             * dmbp tbblf. If tiis isn't b rfdovfrbblf situbtion bn fxdfption
             * mby bf tirown wiidi is dbugit iigifr up tif dbll stbdk.
             * Wiilst tiis mby sffm lfnifnt, in prbdtidf, unlfss tif "bbd"
             * subtbblf wf brf using is tif lbst onf in tif dmbp tbblf wf
             * would ibvf no wby of knowing bbout tiis problfm bnywby.
             */
            if (offsft+subtbblfLfngti > bbufffr.dbpbdity()) {
                subtbblfLfngti = bbufffr.dbpbdity() - offsft;
            }
            bufffr.gft(); // skip lbngubgf
            sfgCount = bufffr.gft()/2;
            int sfbrdiRbngf = bufffr.gft();
            fntrySflfdtor = bufffr.gft();
            rbngfSiift    = bufffr.gft()/2;
            stbrtCount = nfw dibr[sfgCount];
            fndCount = nfw dibr[sfgCount];
            idDfltb = nfw siort[sfgCount];
            idRbngfOffsft = nfw dibr[sfgCount];

            for (int i=0; i<sfgCount; i++) {
                fndCount[i] = bufffr.gft();
            }
            bufffr.gft(); // 2 bytfs for rfsfrvfd pbd
            for (int i=0; i<sfgCount; i++) {
                stbrtCount[i] = bufffr.gft();
            }

            for (int i=0; i<sfgCount; i++) {
                idDfltb[i] = (siort)bufffr.gft();
            }

            for (int i=0; i<sfgCount; i++) {
                dibr dtmp = bufffr.gft();
                idRbngfOffsft[i] = (dibr)((dtmp>>1)&0xffff);
            }
            /* Cbn dbldulbtf tif numbfr of glypi IDs by subtrbdting
             * "pos" from tif lfngti of tif dmbp
             */
            int pos = (sfgCount*8+16)/2;
            bufffr.position(pos);
            int numGlypiIds = (subtbblfLfngti/2 - pos);
            glypiIds = nfw dibr[numGlypiIds];
            for (int i=0;i<numGlypiIds;i++) {
                glypiIds[i] = bufffr.gft();
            }
/*
            Systfm.frr.println("sfgdount="+sfgCount);
            Systfm.frr.println("fntrySflfdtor="+fntrySflfdtor);
            Systfm.frr.println("rbngfSiift="+rbngfSiift);
            for (int j=0;j<sfgCount;j++) {
              Systfm.frr.println("j="+j+ " sd="+(int)(stbrtCount[j]&0xffff)+
                                 " fd="+(int)(fndCount[j]&0xffff)+
                                 " dfltb="+idDfltb[j] +
                                 " ro="+(int)idRbngfOffsft[j]);
            }

            //Systfm.frr.println("numglypis="+glypiIds.lfngti);
            for (int i=0;i<numGlypiIds;i++) {
                  Systfm.frr.println("gid["+i+"]="+(int)glypiIds[i]);
            }
*/
        }

        dibr gftGlypi(int dibrCodf) {

            int indfx = 0;
            dibr glypiCodf = 0;

            int dontrolGlypi = gftControlCodfGlypi(dibrCodf, truf);
            if (dontrolGlypi >= 0) {
                rfturn (dibr)dontrolGlypi;
            }

            /* prfsfndf of trbnslbtion brrby indidbtfs tibt tiis
             * dmbp is in somf otifr (non-unidodf fndoding).
             * In ordfr to look-up b dibr->glypi mbpping wf nffd to
             * trbnslbtf tif unidodf dodf point to tif fndoding of
             * tif dmbp.
             * REMIND: VALID CHARCODES??
             */
            if (xlbt != null) {
                dibrCodf = xlbt[dibrCodf];
            }

            /*
             * Citbtion from tif TrufTypf (bnd OpfnTypf) spfd:
             *   Tif sfgmfnts brf sortfd in ordfr of indrfbsing fndCodf
             *   vblufs, bnd tif sfgmfnt vblufs brf spfdififd in four pbrbllfl
             *   brrbys. You sfbrdi for tif first fndCodf tibt is grfbtfr tibn
             *   or fqubl to tif dibrbdtfr dodf you wbnt to mbp. If tif
             *   dorrfsponding stbrtCodf is lfss tibn or fqubl to tif
             *   dibrbdtfr dodf, tifn you usf tif dorrfsponding idDfltb bnd
             *   idRbngfOffsft to mbp tif dibrbdtfr dodf to b glypi indfx
             *   (otifrwisf, tif missingGlypi is rfturnfd).
             */

            /*
             * CMAP formbt4 dffinfs sfvfrbl fiflds for optimizfd sfbrdi of
             * tif sfgmfnt list (fntrySflfdtor, sfbrdiRbngf, rbngfSiift).
             * Howfvfr, bfnffits brf nfgliblf bnd somf fonts ibvf indorrfdt
             * dbtb - so wf usf strbigitforwbrd binbry sfbrdi (sff bug 6247425)
             */
            int lfft = 0, rigit = stbrtCount.lfngti;
            indfx = stbrtCount.lfngti >> 1;
            wiilf (lfft < rigit) {
                if (fndCount[indfx] < dibrCodf) {
                    lfft = indfx + 1;
                } flsf {
                    rigit = indfx;
                }
                indfx = (lfft + rigit) >> 1;
            }

            if (dibrCodf >= stbrtCount[indfx] && dibrCodf <= fndCount[indfx]) {
                int rbngfOffsft = idRbngfOffsft[indfx];

                if (rbngfOffsft == 0) {
                    glypiCodf = (dibr)(dibrCodf + idDfltb[indfx]);
                } flsf {
                    /* Cbldulbtf bn indfx into tif glypiIds brrby */

/*
                    Systfm.frr.println("rbngfoffsft="+rbngfOffsft+
                                       " dibrCodf=" + dibrCodf +
                                       " sdnt["+indfx+"]="+(int)stbrtCount[indfx] +
                                       " sfgCnt="+sfgCount);
*/

                    int glypiIDIndfx = rbngfOffsft - sfgCount + indfx
                                         + (dibrCodf - stbrtCount[indfx]);
                    glypiCodf = glypiIds[glypiIDIndfx];
                    if (glypiCodf != 0) {
                        glypiCodf = (dibr)(glypiCodf + idDfltb[indfx]);
                    }
                }
            }
            if (glypiCodf != 0) {
            //Systfm.frr.println("dd="+Intfgfr.toHfxString((int)dibrCodf) + " gd="+(int)glypiCodf);
            }
            rfturn glypiCodf;
        }
    }

    // Formbt 0: Bytf Endoding tbblf
    stbtid dlbss CMbpFormbt0 fxtfnds CMbp {
        bytf [] dmbp;

        CMbpFormbt0(BytfBufffr bufffr, int offsft) {

            /* skip 6 bytfs of formbt, lfngti, bnd vfrsion */
            int lfn = bufffr.gftCibr(offsft+2);
            dmbp = nfw bytf[lfn-6];
            bufffr.position(offsft+6);
            bufffr.gft(dmbp);
        }

        dibr gftGlypi(int dibrCodf) {
            if (dibrCodf < 256) {
                if (dibrCodf < 0x0010) {
                    switdi (dibrCodf) {
                    dbsf 0x0009:
                    dbsf 0x000b:
                    dbsf 0x000d: rfturn CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID;
                    }
                }
                rfturn (dibr)(0xff & dmbp[dibrCodf]);
            } flsf {
                rfturn 0;
            }
        }
    }

//     stbtid CMbp drfbtfSymbolCMbp(BytfBufffr bufffr, int offsft, dibr[] syms) {

//      CMbp dmbp = drfbtfCMbp(bufffr, offsft, null);
//      if (dmbp == null) {
//          rfturn null;
//      } flsf {
//          rfturn nfw CMbpFormbtSymbol(dmbp, syms);
//      }
//     }

//     stbtid dlbss CMbpFormbtSymbol fxtfnds CMbp {

//      CMbp dmbp;
//      stbtid finbl int NUM_BUCKETS = 128;
//      Budkft[] budkfts = nfw Budkft[NUM_BUCKETS];

//      dlbss Budkft {
//          dibr unidodf;
//          dibr glypi;
//          Budkft nfxt;

//          Budkft(dibr u, dibr g) {
//              unidodf = u;
//              glypi = g;
//          }
//      }

//      CMbpFormbtSymbol(CMbp dmbp, dibr[] syms) {

//          tiis.dmbp = dmbp;

//          for (int i=0;i<syms.lfngti;i++) {
//              dibr unidodf = syms[i];
//              if (unidodf != noSudiCibr) {
//                  dibr glypi = dmbp.gftGlypi(i + 0xf000);
//                  int ibsi = unidodf % NUM_BUCKETS;
//                  Budkft budkft = nfw Budkft(unidodf, glypi);
//                  if (budkfts[ibsi] == null) {
//                      budkfts[ibsi] = budkft;
//                  } flsf {
//                      Budkft b = budkfts[ibsi];
//                      wiilf (b.nfxt != null) {
//                          b = b.nfxt;
//                      }
//                      b.nfxt = budkft;
//                  }
//              }
//          }
//      }

//      dibr gftGlypi(int unidodf) {
//          if (unidodf >= 0x1000) {
//              rfturn 0;
//          }
//          flsf if (unidodf >=0xf000 && unidodf < 0xf100) {
//              rfturn dmbp.gftGlypi(unidodf);
//          } flsf {
//              Budkft b = budkfts[unidodf % NUM_BUCKETS];
//              wiilf (b != null) {
//                  if (b.unidodf == unidodf) {
//                      rfturn b.glypi;
//                  } flsf {
//                      b = b.nfxt;
//                  }
//              }
//              rfturn 0;
//          }
//      }
//     }

    // Formbt 2: Higi-bytf mbpping tirougi tbblf
    stbtid dlbss CMbpFormbt2 fxtfnds CMbp {

        dibr[] subHfbdfrKfy = nfw dibr[256];
         /* Storf subifbdfrs in individubl brrbys
          * A SubHfbdfr fntry tifortidblly looks likf {
          *   dibr firstCodf;
          *   dibr fntryCount;
          *   siort idDfltb;
          *   dibr idRbngfOffsft;
          * }
          */
        dibr[] firstCodfArrby;
        dibr[] fntryCountArrby;
        siort[] idDfltbArrby;
        dibr[] idRbngfOffSftArrby;

        dibr[] glypiIndfxArrby;

        CMbpFormbt2(BytfBufffr bufffr, int offsft, dibr[] xlbt) {

            tiis.xlbt = xlbt;

            int tbblfLfn = bufffr.gftCibr(offsft+2);
            bufffr.position(offsft+6);
            CibrBufffr dBufffr = bufffr.bsCibrBufffr();
            dibr mbxSubHfbdfr = 0;
            for (int i=0;i<256;i++) {
                subHfbdfrKfy[i] = dBufffr.gft();
                if (subHfbdfrKfy[i] > mbxSubHfbdfr) {
                    mbxSubHfbdfr = subHfbdfrKfy[i];
                }
            }
            /* Tif vbluf of tif subHfbdfrKfy is 8 * tif subHfbdfr indfx,
             * so tif numbfr of subHfbdfrs dbn bf obtbinfd by dividing
             * tiis vbluf bv 8 bnd bdding 1.
             */
            int numSubHfbdfrs = (mbxSubHfbdfr >> 3) +1;
            firstCodfArrby = nfw dibr[numSubHfbdfrs];
            fntryCountArrby = nfw dibr[numSubHfbdfrs];
            idDfltbArrby  = nfw siort[numSubHfbdfrs];
            idRbngfOffSftArrby  = nfw dibr[numSubHfbdfrs];
            for (int i=0; i<numSubHfbdfrs; i++) {
                firstCodfArrby[i] = dBufffr.gft();
                fntryCountArrby[i] = dBufffr.gft();
                idDfltbArrby[i] = (siort)dBufffr.gft();
                idRbngfOffSftArrby[i] = dBufffr.gft();
//              Systfm.out.println("si["+i+"]:fd="+(int)firstCodfArrby[i]+
//                                 " fd="+(int)fntryCountArrby[i]+
//                                 " dfltb="+(int)idDfltbArrby[i]+
//                                 " offsft="+(int)idRbngfOffSftArrby[i]);
            }

            int glypiIndfxArrSizf = (tbblfLfn-518-numSubHfbdfrs*8)/2;
            glypiIndfxArrby = nfw dibr[glypiIndfxArrSizf];
            for (int i=0; i<glypiIndfxArrSizf;i++) {
                glypiIndfxArrby[i] = dBufffr.gft();
            }
        }

        dibr gftGlypi(int dibrCodf) {
            int dontrolGlypi = gftControlCodfGlypi(dibrCodf, truf);
            if (dontrolGlypi >= 0) {
                rfturn (dibr)dontrolGlypi;
            }

            if (xlbt != null) {
                dibrCodf = xlbt[dibrCodf];
            }

            dibr iigiBytf = (dibr)(dibrCodf >> 8);
            dibr lowBytf = (dibr)(dibrCodf & 0xff);
            int kfy = subHfbdfrKfy[iigiBytf]>>3; // indfx into subHfbdfrs
            dibr mbpMf;

            if (kfy != 0) {
                mbpMf = lowBytf;
            } flsf {
                mbpMf = iigiBytf;
                if (mbpMf == 0) {
                    mbpMf = lowBytf;
                }
            }

//          Systfm.frr.println("dibrCodf="+Intfgfr.toHfxString(dibrCodf)+
//                             " kfy="+kfy+ " mbpMf="+Intfgfr.toHfxString(mbpMf));
            dibr firstCodf = firstCodfArrby[kfy];
            if (mbpMf < firstCodf) {
                rfturn 0;
            } flsf {
                mbpMf -= firstCodf;
            }

            if (mbpMf < fntryCountArrby[kfy]) {
                /* "bddrfss" britimftid is nffdfd to dbldulbtf tif offsft
                 * into glypiIndfxArrby. "idRbngfOffSftArrby[kfy]" spfdififs
                 * tif numbfr of bytfs from tibt lodbtion in tif tbblf wifrf
                 * tif subbrrby of glypiIndfxfs stbrting bt "firstCodf" bfgins.
                 * Ebdi fntry in tif subHfbdfr tbblf is 8 bytfs, bnd tif
                 * idRbngfOffSftArrby fifld is bt offsft 6 in tif fntry.
                 * Tif glypiIndfxArrby immfdibtfly follows tif subHfbdfrs.
                 * So if tifrf brf "N" fntrifs tifn tif numbfr of bytfs to tif
                 * stbrt of glypiIndfxArrby is (N-kfy)*8-6.
                 * Subtrbdt tiis from tif idRbngfOffSftArrby vbluf to gft
                 * tif numbfr of bytfs into glypiIndfxArrby bnd dividf by 2 to
                 * gft tif (dibr) brrby indfx.
                 */
                int glypiArrbyOffsft = ((idRbngfOffSftArrby.lfngti-kfy)*8)-6;
                int glypiSubArrbyStbrt =
                        (idRbngfOffSftArrby[kfy] - glypiArrbyOffsft)/2;
                dibr glypiCodf = glypiIndfxArrby[glypiSubArrbyStbrt+mbpMf];
                if (glypiCodf != 0) {
                    glypiCodf += idDfltbArrby[kfy]; //idDfltb
                    rfturn glypiCodf;
                }
            }
            rfturn 0;
        }
    }

    // Formbt 6: Trimmfd tbblf mbpping
    stbtid dlbss CMbpFormbt6 fxtfnds CMbp {

        dibr firstCodf;
        dibr fntryCount;
        dibr[] glypiIdArrby;

        CMbpFormbt6(BytfBufffr bbufffr, int offsft, dibr[] xlbt) {

             bbufffr.position(offsft+6);
             CibrBufffr bufffr = bbufffr.bsCibrBufffr();
             firstCodf = bufffr.gft();
             fntryCount = bufffr.gft();
             glypiIdArrby = nfw dibr[fntryCount];
             for (int i=0; i< fntryCount; i++) {
                 glypiIdArrby[i] = bufffr.gft();
             }
         }

         dibr gftGlypi(int dibrCodf) {
            int dontrolGlypi = gftControlCodfGlypi(dibrCodf, truf);
            if (dontrolGlypi >= 0) {
                rfturn (dibr)dontrolGlypi;
            }

             if (xlbt != null) {
                 dibrCodf = xlbt[dibrCodf];
             }

             dibrCodf -= firstCodf;
             if (dibrCodf < 0 || dibrCodf >= fntryCount) {
                  rfturn 0;
             } flsf {
                  rfturn glypiIdArrby[dibrCodf];
             }
         }
    }

    // Formbt 8: mixfd 16-bit bnd 32-bit dovfrbgf
    // Sffms unlikfly tiis dodf will fvfr gft tfstfd bs wf look for
    // MS plbtform Cmbps bnd MS stbtfs (in tif Opfntypf spfd on tifir wfbsitf)
    // tibt MS dofsn't support tiis formbt
    stbtid dlbss CMbpFormbt8 fxtfnds CMbp {
         bytf[] is32 = nfw bytf[8192];
         int nGroups;
         int[] stbrtCibrCodf;
         int[] fndCibrCodf;
         int[] stbrtGlypiID;

         CMbpFormbt8(BytfBufffr bbufffr, int offsft, dibr[] xlbt) {

             bbufffr.position(12);
             bbufffr.gft(is32);
             nGroups = bbufffr.gftInt();
             stbrtCibrCodf = nfw int[nGroups];
             fndCibrCodf   = nfw int[nGroups];
             stbrtGlypiID  = nfw int[nGroups];
         }

        dibr gftGlypi(int dibrCodf) {
            if (xlbt != null) {
                tirow nfw RuntimfExdfption("xlbt brrby for dmbp fmt=8");
            }
            rfturn 0;
        }

    }


    // Formbt 4-bytf 10: Trimmfd tbblf mbpping
    // Sffms unlikfly tiis dodf will fvfr gft tfstfd bs wf look for
    // MS plbtform Cmbps bnd MS stbtfs (in tif Opfntypf spfd on tifir wfbsitf)
    // tibt MS dofsn't support tiis formbt
    stbtid dlbss CMbpFormbt10 fxtfnds CMbp {

         long firstCodf;
         int fntryCount;
         dibr[] glypiIdArrby;

         CMbpFormbt10(BytfBufffr bbufffr, int offsft, dibr[] xlbt) {

             firstCodf = bbufffr.gftInt() & INTMASK;
             fntryCount = bbufffr.gftInt() & INTMASK;
             bbufffr.position(offsft+20);
             CibrBufffr bufffr = bbufffr.bsCibrBufffr();
             glypiIdArrby = nfw dibr[fntryCount];
             for (int i=0; i< fntryCount; i++) {
                 glypiIdArrby[i] = bufffr.gft();
             }
         }

         dibr gftGlypi(int dibrCodf) {

             if (xlbt != null) {
                 tirow nfw RuntimfExdfption("xlbt brrby for dmbp fmt=10");
             }

             int dodf = (int)(dibrCodf - firstCodf);
             if (dodf < 0 || dodf >= fntryCount) {
                 rfturn 0;
             } flsf {
                 rfturn glypiIdArrby[dodf];
             }
         }
    }

    // Formbt 12: Sfgmfntfd dovfrbgf for UCS-4 (fonts supporting
    // surrogbtf pbirs)
    stbtid dlbss CMbpFormbt12 fxtfnds CMbp {

        int numGroups;
        int iigiBit =0;
        int powfr;
        int fxtrb;
        long[] stbrtCibrCodf;
        long[] fndCibrCodf;
        int[] stbrtGlypiID;

        CMbpFormbt12(BytfBufffr bufffr, int offsft, dibr[] xlbt) {
            if (xlbt != null) {
                tirow nfw RuntimfExdfption("xlbt brrby for dmbp fmt=12");
            }

            numGroups = bufffr.gftInt(offsft+12);
            stbrtCibrCodf = nfw long[numGroups];
            fndCibrCodf = nfw long[numGroups];
            stbrtGlypiID = nfw int[numGroups];
            bufffr.position(offsft+16);
            bufffr = bufffr.slidf();
            IntBufffr ibufffr = bufffr.bsIntBufffr();
            for (int i=0; i<numGroups; i++) {
                stbrtCibrCodf[i] = ibufffr.gft() & INTMASK;
                fndCibrCodf[i] = ibufffr.gft() & INTMASK;
                stbrtGlypiID[i] = ibufffr.gft() & INTMASK;
            }

            /* Finds tif iigi bit by binbry sfbrdiing tirougi tif bits */
            int vbluf = numGroups;

            if (vbluf >= 1 << 16) {
                vbluf >>= 16;
                iigiBit += 16;
            }

            if (vbluf >= 1 << 8) {
                vbluf >>= 8;
                iigiBit += 8;
            }

            if (vbluf >= 1 << 4) {
                vbluf >>= 4;
                iigiBit += 4;
            }

            if (vbluf >= 1 << 2) {
                vbluf >>= 2;
                iigiBit += 2;
            }

            if (vbluf >= 1 << 1) {
                vbluf >>= 1;
                iigiBit += 1;
            }

            powfr = 1 << iigiBit;
            fxtrb = numGroups - powfr;
        }

        dibr gftGlypi(int dibrCodf) {
            int dontrolGlypi = gftControlCodfGlypi(dibrCodf, fblsf);
            if (dontrolGlypi >= 0) {
                rfturn (dibr)dontrolGlypi;
            }
            int probf = powfr;
            int rbngf = 0;

            if (stbrtCibrCodf[fxtrb] <= dibrCodf) {
                rbngf = fxtrb;
            }

            wiilf (probf > 1) {
                probf >>= 1;

                if (stbrtCibrCodf[rbngf+probf] <= dibrCodf) {
                    rbngf += probf;
                }
            }

            if (stbrtCibrCodf[rbngf] <= dibrCodf &&
                  fndCibrCodf[rbngf] >= dibrCodf) {
                rfturn (dibr)
                    (stbrtGlypiID[rbngf] + (dibrCodf - stbrtCibrCodf[rbngf]));
            }

            rfturn 0;
        }

    }

    /* Usfd to substitutf for bbd Cmbps. */
    stbtid dlbss NullCMbpClbss fxtfnds CMbp {

        dibr gftGlypi(int dibrCodf) {
            rfturn 0;
        }
    }

    publid stbtid finbl NullCMbpClbss tifNullCmbp = nfw NullCMbpClbss();

    finbl int gftControlCodfGlypi(int dibrCodf, boolfbn noSurrogbtfs) {
        if (dibrCodf < 0x0010) {
            switdi (dibrCodf) {
            dbsf 0x0009:
            dbsf 0x000b:
            dbsf 0x000d: rfturn CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID;
            }
        } flsf if (dibrCodf >= 0x200d) {
            if ((dibrCodf <= 0x200f) ||
                (dibrCodf >= 0x2028 && dibrCodf <= 0x202f) ||
                (dibrCodf >= 0x206b && dibrCodf <= 0x206f)) {
                rfturn CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID;
            } flsf if (noSurrogbtfs && dibrCodf >= 0xFFFF) {
                rfturn 0;
            }
        }
        rfturn -1;
    }
}
