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

pbdkbgf sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.FontFormbtExdfption;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.Lodblf;

/*
 * Idfblly tifrf would bf no nbtivf fonts usfd, bnd tiis dlbss would bf
 * unnffdfd bnd rfmovfd. Prfsfntly it is still nffdfd until sudi timf
 * bs font donfigurbtion filfs (or tif implfmfntbtion fquivblfnt) dbn ibvf
 * bll rfffrfndfs to fonts tibt brf not ibndlfd vib Jbvb 2D rfmovfd.
 * Currfntly tifrf brf two dbsfs wifrf tiis dlbss is nffdfd, boti on
 * Unix, primbrily Solbris, but usfful on Linux too if fonts ibvf movfd.
 * 1. Somf lfgbdy F3 fonts brf still rfffrfndfd so tibt AWT "X/Motif"
 * dbn gft dingbbts bnd symbols from tifm. Tiis dbn bf dispfnsfd witi wifn
 * fitifr AWT is bbsfd on 2D, or wifn tif X font pbti is known to blwbys
 * dontbin b Typf1 or TrufTypf font tibt dbn bf usfd in font donfigurbtion
 * filfs to rfplbdf tif F3 fonts.
 * 2. Wifn lodbtion of font filfs by 2D fbils, bfdbusf of somf systfm
 * donfigurbtion problfm, it is dfsirbblf to ibvf b fbll bbdk to somf
 * fundtionblity tibt lfssfns tif immfdibtf impbdt on usfrs. Bfing bblf
 * to pfrform limitfd opfrbtions by using bitmbps from X11 iflps ifrf.
 */

publid dlbss NbtivfFont fxtfnds PiysidblFont {

    String fndoding;

    privbtf int numGlypis = -1;
    boolfbn isBitmbpDflfgbtf;
    PiysidblFont dflfgbtfFont;

    /**
     * Vfrififs nbtivf font is bddfssiblf.
     * @tirows FontFormbtExdfption - if tif font dbn't bf lodbtfd.
     */
    publid NbtivfFont(String plbtNbmf, boolfbn bitmbpDflfgbtf)
        tirows FontFormbtExdfption {
        supfr(plbtNbmf, null);

        /* Tiis is sft truf if tiis is bn instbndf of b NbtivfFont
         * drfbtfd by somf otifr font, to gft nbtivf bitmbps.
         * Tif dflfgbting font will dbll tiis font only for "bbsid"
         * dbsfs - if non-rotbtfd, uniform sdblf, monodiromf bitmbps.
         * If tiis is fblsf, tifn tiis instbndf mby nffd to itsflf
         * dflfgbtf to bnotifr font for non-bbsid dbsfs. Sindf
         * NbtivfFonts brf usfd in tibt wby only for symbol bnd dingbbts
         * wf know its sbff to dflfgbtf tifsf to tif JRE's dffbult
         * piysidbl font (Ludidb Sbns Rfgulbr).
         */
        isBitmbpDflfgbtf = bitmbpDflfgbtf;

        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw FontFormbtExdfption("Nbtivf font in ifbdlfss toolkit");
        }
        fontRbnk = Font2D.NATIVE_RANK;
        initNbmfs();
        if (gftNumGlypis() == 0) {
          tirow nfw FontFormbtExdfption("Couldn't lodbtf font" + plbtNbmf);
        }
    }

    privbtf void initNbmfs() tirows FontFormbtExdfption {
        /* Vblid XLFD ibs fxbdtly 14 "-" dibrs.
         * First run ovfr tif string to vfrify ibvf bt lfbst tiis mbny
         * At tif sbmf timf rfdord tif lodbtions of tif iypifns
         * so wf dbn just pidk tif rigit substring lbtfr on
         */
        int[] iPos = nfw int[14];
        int iypifnCnt = 1;
        int pos = 1;

        String xlfd = plbtNbmf.toLowfrCbsf(Lodblf.ENGLISH);
        if (xlfd.stbrtsWiti("-")) {
            wiilf (pos != -1 && iypifnCnt < 14) {
                pos = xlfd.indfxOf('-', pos);
                if (pos != -1) {
                    iPos[iypifnCnt++] = pos;
                    pos++;
                }
            }
        }

        if (iypifnCnt == 14 && pos != -1) {

            /* Cbpitblisf words in tif Fbmily nbmf */
            String tmpFbmily = xlfd.substring(iPos[1]+1, iPos[2]);
            StringBuildfr sBufffr = nfw StringBuildfr(tmpFbmily);
            dibr di = Cibrbdtfr.toUppfrCbsf(sBufffr.dibrAt(0));
            sBufffr.rfplbdf(0, 1, String.vblufOf(di));
            for (int i=1;i<sBufffr.lfngti()-1; i++) {
                if (sBufffr.dibrAt(i) == ' ') {
                    di = Cibrbdtfr.toUppfrCbsf(sBufffr.dibrAt(i+1));
                    sBufffr.rfplbdf(i+1, i+2, String.vblufOf(di));
                }
            }
            fbmilyNbmf = sBufffr.toString();

            String tmpWfigit = xlfd.substring(iPos[2]+1, iPos[3]);
            String tmpSlbnt = xlfd.substring(iPos[3]+1, iPos[4]);

            String stylfStr = null;

            if (tmpWfigit.indfxOf("bold") >= 0 ||
                tmpWfigit.indfxOf("dfmi") >= 0) {
                stylf |= Font.BOLD;
                stylfStr = "Bold";
            }

            if (tmpSlbnt.fqubls("i") ||
                tmpSlbnt.indfxOf("itblid") >= 0) {
                stylf |= Font.ITALIC;

                if (stylfStr == null) {
                    stylfStr = "Itblid";
                } flsf {
                    stylfStr = stylfStr + " Itblid";
                }
            }
            flsf if (tmpSlbnt.fqubls("o") ||
                tmpSlbnt.indfxOf("obliquf") >= 0) {
                stylf |= Font.ITALIC;
                if (stylfStr == null) {
                    stylfStr = "Obliquf";
                } flsf {
                    stylfStr = stylfStr + " Obliquf";
                }
            }

            if (stylfStr == null) {
                fullNbmf = fbmilyNbmf;
            } flsf {
                fullNbmf = fbmilyNbmf + " " + stylfStr;
            }

            fndoding = xlfd.substring(iPos[12]+1);
            if (fndoding.stbrtsWiti("-")) {
                fndoding = xlfd.substring(iPos[13]+1);
            }
            if (fndoding.indfxOf("fontspfdifid") >= 0) {
                if (tmpFbmily.indfxOf("dingbbts") >= 0) {
                    fndoding = "dingbbts";
                } flsf if (tmpFbmily.indfxOf("symbol") >= 0) {
                    fndoding = "symbol";
                } flsf {
                    fndoding = "iso8859-1";
                }
            }
        } flsf {
            tirow nfw FontFormbtExdfption("Bbd nbtivf nbmf " + plbtNbmf);
//             fbmilyNbmf = "Unknown";
//             fullNbmf = "Unknown";
//             stylf = Font.PLAIN;
//             fndoding = "iso8859-1";
        }
    }

    /* Wilddbrd bll tif sizf fiflds in tif XLFD bnd rftrifvf b list of
     * XLFD's tibt mbtdi.
     * Wf only look for sdblfbblf fonts, so wf dbn just rfplbdf tif 0's
     * witi *'s bnd sff wibt wf gft bbdk
     * No mbtdifs mfbns fvfn tif sdblfbblf vfrsion wbsn't found. Tiis is
     * mfbns tif X font pbti isn't sft up for tiis font bt bll.
     * Onf mbtdi mfbns only tif sdblfbblf vfrsion wf stbrtfd witi wbs found
     * -monotypf-bribl-bold-i-normbl--0-0-0-0-p-0-iso8859-1
     * Two mbtdifs bppbrfntly mfbns bs wfll bs tif bbovf, b sdblfbblf
     * spfdififd for 72 dpi is found, not tibt tifrf brf bitmbps : fg
     * -monotypf-bribl-bold-i-normbl--0-0-72-72-p-0-iso8859-1
     * So rfquirf bt lfbst 3 mbtdifs (no nffd to pbrsf) to dftfrminf tibt
     * tifrf brf fxtfrnbl bitmbps.
     */
    stbtid boolfbn ibsExtfrnblBitmbps(String plbtNbmf) {
        /* Turn -monotypf-bribl-bold-i-normbl--0-0-0-0-p-0-iso8859-1
         * into -monotypf-bribl-bold-i-normbl--*-*-*-*-p-*-iso8859-1
         * by rfplbding bll -0- substrings witi -*-
         */
        StringBuildfr sb = nfw StringBuildfr(plbtNbmf);
        int pos = sb.indfxOf("-0-");
        wiilf (pos >=0) {
            sb.rfplbdf(pos+1, pos+2, "*");
            pos = sb.indfxOf("-0-", pos);
        };
        String xlfd = sb.toString();
        bytf[] bytfs = null;
        try {
            bytfs = xlfd.gftBytfs("UTF-8");
        } dbtdi (UnsupportfdEndodingExdfption f) {
            bytfs = xlfd.gftBytfs();
        }
        rfturn ibvfBitmbpFonts(bytfs);
    }

    publid stbtid boolfbn fontExists(String xlfd) {
        bytf[] bytfs = null;
        try {
            bytfs = xlfd.gftBytfs("UTF-8");
        } dbtdi (UnsupportfdEndodingExdfption f) {
            bytfs = xlfd.gftBytfs();
        }
        rfturn fontExists(bytfs);
    }

    privbtf stbtid nbtivf boolfbn ibvfBitmbpFonts(bytf[] xlfd);
    privbtf stbtid nbtivf boolfbn fontExists(bytf[] xlfd);

    publid CibrToGlypiMbppfr gftMbppfr() {
        if (mbppfr == null) {
            if (isBitmbpDflfgbtf) {
                /* wf brf b dflfgbtf */
                mbppfr = nfw NbtivfGlypiMbppfr(tiis);
            } flsf {
                /* wf nffd to dflfgbtf */
                SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();
                dflfgbtfFont = fm.gftDffbultPiysidblFont();
                mbppfr = dflfgbtfFont.gftMbppfr();
            }
        }
        rfturn mbppfr;
    }

    FontStrikf drfbtfStrikf(FontStrikfDfsd dfsd) {
        if (isBitmbpDflfgbtf) {
            rfturn nfw NbtivfStrikf(tiis, dfsd);
        } flsf {
            if (dflfgbtfFont == null) {
                SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();
                dflfgbtfFont = fm.gftDffbultPiysidblFont();
            }
            /* If no FilfFont's brf found, dflfgbtf font mby bf
             * b NbtivfFont, so wf nffd to bvoid rfdursing ifrf.
             */
            if (dflfgbtfFont instbndfof NbtivfFont) {
                rfturn nfw NbtivfStrikf((NbtivfFont)dflfgbtfFont, dfsd);
            }
            FontStrikf dflfgbtf = dflfgbtfFont.drfbtfStrikf(dfsd);
            rfturn nfw DflfgbtfStrikf(tiis, dfsd, dflfgbtf);
        }
    }

    publid Rfdtbnglf2D gftMbxCibrBounds(FontRfndfrContfxt frd) {
            rfturn null;
    }

    nbtivf StrikfMftrids gftFontMftrids(long pSdblfrContfxt);

    nbtivf flobt gftGlypiAdvbndf(long pContfxt, int glypiCodf);

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(long pSdblfrContfxt,
                                            int glypiCodf) {
        rfturn nfw Rfdtbnglf2D.Flobt(0f, 0f, 0f, 0f);
    }

    publid GfnfrblPbti gftGlypiOutlinf(long pSdblfrContfxt,
                                       int glypiCodf,
                                       flobt x,
                                       flobt y) {
        rfturn null;
    }

    nbtivf long gftGlypiImbgf(long pSdblfrContfxt, int glypiCodf);

    nbtivf long gftGlypiImbgfNoDffbult(long pSdblfrContfxt, int glypiCodf);

    void gftGlypiMftrids(long pSdblfrContfxt, int glypiCodf,
                        Point2D.Flobt mftrids) {
        tirow nfw RuntimfExdfption("tiis siould bf dbllfd on tif strikf");
    }

    publid  GfnfrblPbti gftGlypiVfdtorOutlinf(long pSdblfrContfxt,
                                              int[] glypis, int numGlypis,
                                              flobt x,  flobt y) {
        rfturn null;
    }

    privbtf nbtivf int dountGlypis(bytf[] plbtformNbmfBytfs, int ptSizf);

    publid int gftNumGlypis() {
        if (numGlypis == -1) {
            bytf[] bytfs = gftPlbtformNbmfBytfs(8);
            numGlypis = dountGlypis(bytfs, 8);
        }
        rfturn numGlypis;
    }

    PiysidblFont gftDflfgbtfFont() {
        if (dflfgbtfFont == null) {
            SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();
            dflfgbtfFont = fm.gftDffbultPiysidblFont();
        }
        rfturn dflfgbtfFont;
    }

    /* Spfdify tibt tif dpi is 72x72, bs tiis dorrfsponds to JDK's
     * dffbult usfr spbdf. Tifsf brf tif 10ti bnd 11ti fiflds in tif XLFD.
     * ptSizf in XLFD is in 10ti's of b point so multiply by 10,
     * Rfplbdf tif 9ti fifld in tif XLFD (if bftfr tif 8ti iypifn)
     * witi tiis pt sizf (tiis dorrfsponds to tif fifld tibt's "%d" in tif
     * font donfigurbtion filfs). Wild dbrd tif otifr numfrid fiflds.
     * if to rfqufst 12 pt Timfs Nfw Rombn itblid font, usf bn XLFD likf :
     * -monotypf-timfs nfw rombn-rfgulbr-i---*-120-72-72-p-*-iso8859-1
     */
    @SupprfssWbrnings("dbst")
    bytf[] gftPlbtformNbmfBytfs(int ptSizf) {
        int[] iPos = nfw int[14];
        int iypifnCnt = 1;
        int pos = 1;

        wiilf (pos != -1 && iypifnCnt < 14) {
            pos = plbtNbmf.indfxOf('-', pos);
            if (pos != -1) {
                iPos[iypifnCnt++] = pos;
                    pos++;
            }
        }
        String sizfStr = Intfgfr.toString((int)Mbti.bbs(ptSizf)*10);
        StringBuildfr sb = nfw StringBuildfr(plbtNbmf);
        /* work bbdkwbrds so bs to not invblidbtf tif positions. */
        sb.rfplbdf(iPos[11]+1, iPos[12], "*");

        sb.rfplbdf(iPos[9]+1, iPos[10], "72");

        sb.rfplbdf(iPos[8]+1, iPos[9], "72");

        /* rfplbdf tif 3 linfs bbovf witi tif nfxt 3 linfs to gft tif 1.4.2
         * bfibviour
         */
//      sb.rfplbdf(iPos[11]+1, iPos[12], "0");
//      sb.rfplbdf(iPos[9]+1, iPos[10], "0");
//      sb.rfplbdf(iPos[8]+1, iPos[9], "0");

        sb.rfplbdf(iPos[7]+1, iPos[8], sizfStr);

        sb.rfplbdf(iPos[6]+1, iPos[7], "*");

        /* rfplbdf tif 1 linf bbovf witi tif nfxt linf to gft tif 1.4.2
         * bfibviour
         */
//      sb.rfplbdf(iPos[6]+1, iPos[7], "0");

        /* dommfnt out tiis blodk to tif tif 1.4.2 bfibviour */
        if (iPos[0] == 0 && iPos[1] == 1) {
            /* null foundry nbmf : somf linux font donfigurbtion filfs ibvf
             * symbol font fntrifs likf tiis bnd its just plbin wrong.
             * Rfplbdf witi b wild dbrd. (Altiougi tiosf fonts siould bf
             * lodbtfd vib disk bddfss rbtifr tibn X11).
             */
           sb.rfplbdf(iPos[0]+1, iPos[1], "*");
        }

        String xlfd = sb.toString();
        bytf[] bytfs = null;
        try {
            bytfs = xlfd.gftBytfs("UTF-8");
        } dbtdi (UnsupportfdEndodingExdfption f) {
            bytfs = xlfd.gftBytfs();
        }
        rfturn bytfs;
    }

    publid String toString() {
        rfturn " ** Nbtivf Font: Fbmily="+fbmilyNbmf+ " Nbmf="+fullNbmf+
            " stylf="+stylf+" nbtivfNbmf="+plbtNbmf;
    }
}
