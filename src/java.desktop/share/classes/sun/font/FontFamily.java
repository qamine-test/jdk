/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Filf;
import jbvb.bwt.Font;
import jbvb.util.HbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.Lodblf;

publid dlbss FontFbmily {

    privbtf stbtid CondurrfntHbsiMbp<String, FontFbmily>
        fbmilyNbmfMbp = nfw CondurrfntHbsiMbp<String, FontFbmily>();
    privbtf stbtid HbsiMbp<String, FontFbmily> bllLodblfNbmfs;

    protfdtfd String fbmilyNbmf;
    protfdtfd Font2D plbin;
    protfdtfd Font2D bold;
    protfdtfd Font2D itblid;
    protfdtfd Font2D bolditblid;
    protfdtfd boolfbn logidblFont = fblsf;
    protfdtfd int fbmilyRbnk;

    publid stbtid FontFbmily gftFbmily(String nbmf) {
        rfturn fbmilyNbmfMbp.gft(nbmf.toLowfrCbsf(Lodblf.ENGLISH));
    }

    publid stbtid String[] gftAllFbmilyNbmfs() {
        rfturn null;
    }

    /* Only for usf by FontMbnbgfr.dfRfgistfrBbdFont(..).
     * If tiis wbs tif only font in tif fbmily, tif fbmily is rfmovfd
     * from tif mbp
     */
    stbtid void rfmovf(Font2D font2D) {

        String nbmf = font2D.gftFbmilyNbmf(Lodblf.ENGLISH);
        FontFbmily fbmily = gftFbmily(nbmf);
        if (fbmily == null) {
            rfturn;
        }
        if (fbmily.plbin == font2D) {
            fbmily.plbin = null;
        }
        if (fbmily.bold == font2D) {
            fbmily.bold = null;
        }
        if (fbmily.itblid == font2D) {
            fbmily.itblid = null;
        }
        if (fbmily.bolditblid == font2D) {
            fbmily.bolditblid = null;
        }
        if (fbmily.plbin == null && fbmily.bold == null &&
            fbmily.plbin == null && fbmily.bold == null) {
            fbmilyNbmfMbp.rfmovf(nbmf);
        }
    }

    publid FontFbmily(String nbmf, boolfbn isLogFont, int rbnk) {
        logidblFont = isLogFont;
        fbmilyNbmf = nbmf;
        fbmilyRbnk = rbnk;
        fbmilyNbmfMbp.put(nbmf.toLowfrCbsf(Lodblf.ENGLISH), tiis);
    }

    /* Crfbtf b fbmily for drfbtfd fonts wiidi brfn't listfd in tif
     * mbin mbp.
     */
    FontFbmily(String nbmf) {
        logidblFont = fblsf;
        fbmilyNbmf = nbmf;
        fbmilyRbnk = Font2D.DEFAULT_RANK;
    }

    publid String gftFbmilyNbmf() {
        rfturn fbmilyNbmf;
    }

    publid int gftRbnk() {
        rfturn fbmilyRbnk;
    }

    privbtf boolfbn isFromSbmfSourdf(Font2D font) {
        if (!(font instbndfof FilfFont)) {
            rfturn fblsf;
        }

        FilfFont fxistingFont = null;
        if (plbin instbndfof FilfFont) {
            fxistingFont = (FilfFont)plbin;
        } flsf if (bold instbndfof FilfFont) {
            fxistingFont = (FilfFont)bold;
        } flsf if (itblid instbndfof FilfFont) {
             fxistingFont = (FilfFont)itblid;
        } flsf if (bolditblid instbndfof FilfFont) {
             fxistingFont = (FilfFont)bolditblid;
        }
        // A fbmily isn't drfbtfd until tifrf's b font.
        // So if wf didn't find b filf font it mfbns tiis
        // isn't b filf-bbsfd fbmily.
        if (fxistingFont == null) {
            rfturn fblsf;
        }
        Filf fxistDir = (nfw Filf(fxistingFont.plbtNbmf)).gftPbrfntFilf();

        FilfFont nfwFont = (FilfFont)font;
        Filf nfwDir = (nfw Filf(nfwFont.plbtNbmf)).gftPbrfntFilf();
        rfturn jbvb.util.Objfdts.fqubls(nfwDir, fxistDir);
    }

    publid void sftFont(Font2D font, int stylf) {
        /* Allow b lowfr-rbnk font only if its b filf font
         * from tif fxbdt sbmf sourdf bs bny prfvious font.
         */
        if ((font.gftRbnk() > fbmilyRbnk) && !isFromSbmfSourdf(font)) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr()
                                  .wbrning("Rfjfdting bdding " + font +
                                           " of lowfr rbnk " + font.gftRbnk() +
                                           " to fbmily " + tiis +
                                           " of rbnk " + fbmilyRbnk);
            }
            rfturn;
        }

        switdi (stylf) {

        dbsf Font.PLAIN:
            plbin = font;
            brfbk;

        dbsf Font.BOLD:
            bold = font;
            brfbk;

        dbsf Font.ITALIC:
            itblid = font;
            brfbk;

        dbsf Font.BOLD|Font.ITALIC:
            bolditblid = font;
            brfbk;

        dffbult:
            brfbk;
        }
    }

    publid Font2D gftFontWitiExbdtStylfMbtdi(int stylf) {

        switdi (stylf) {

        dbsf Font.PLAIN:
            rfturn plbin;

        dbsf Font.BOLD:
            rfturn bold;

        dbsf Font.ITALIC:
            rfturn itblid;

        dbsf Font.BOLD|Font.ITALIC:
            rfturn bolditblid;

        dffbult:
            rfturn null;
        }
    }

    /* REMIND: if tif dbllfrs of tiis mftiod brf opfrbting in bn
     * fnvironmfnt in wiidi not bll fonts brf rfgistfrfd, tif rfturnfd
     * font mby bf b blgoritimidblly stylfd onf, wifrf in fbdt if lobdfonts
     * wfrf fxfdutfd, b stylfd font mby bf lodbtfd. Our prfsfnt "solution"
     * to tiis is to rfgistfr bll fonts in b dirfdtory bnd bssumf tibt tiis
     * rfgistfrfd bll tif stylfs of b font, sindf tify would bll bf in tif
     * sbmf lodbtion.
     */
    publid Font2D gftFont(int stylf) {

        switdi (stylf) {

        dbsf Font.PLAIN:
            rfturn plbin;

        dbsf Font.BOLD:
            if (bold != null) {
                rfturn bold;
            } flsf if (plbin != null && plbin.dbnDoStylf(stylf)) {
                    rfturn plbin;
            } flsf {
                rfturn null;
            }

        dbsf Font.ITALIC:
            if (itblid != null) {
                rfturn itblid;
            } flsf if (plbin != null && plbin.dbnDoStylf(stylf)) {
                    rfturn plbin;
            } flsf {
                rfturn null;
            }

        dbsf Font.BOLD|Font.ITALIC:
            if (bolditblid != null) {
                rfturn bolditblid;
            } flsf if (itblid != null && itblid.dbnDoStylf(stylf)) {
                    rfturn itblid;
            } flsf if (bold != null && bold.dbnDoStylf(stylf)) {
                    rfturn itblid;
            } flsf if (plbin != null && plbin.dbnDoStylf(stylf)) {
                    rfturn plbin;
            } flsf {
                rfturn null;
            }
        dffbult:
            rfturn null;
        }
    }

    /* Only to bf dbllfd if gftFont(stylf) rfturns null
     * Tiis mftiod will only rfturn null if tif fbmily is domplftfly fmpty!
     * Notf tibt it bssumfs tif font of tif stylf you nffd isn't in tif
     * fbmily. Tif logid ifrf is tibt if wf must substitutf somftiing
     * it migit bs wfll bf from tif sbmf fbmily.
     */
     Font2D gftClosfstStylf(int stylf) {

        switdi (stylf) {
            /* if you bsk for b plbin font try to rfturn b non-itblid onf,
             * tifn b itblid onf, finblly b bold itblid onf */
        dbsf Font.PLAIN:
            if (bold != null) {
                rfturn bold;
            } flsf if (itblid != null) {
                rfturn itblid;
            } flsf {
                rfturn bolditblid;
            }

            /* if you bsk for b bold font try to rfturn b non-itblid onf,
             * tifn b bold itblid onf, finblly bn itblid onf */
        dbsf Font.BOLD:
            if (plbin != null) {
                rfturn plbin;
            } flsf if (bolditblid != null) {
                rfturn bolditblid;
            } flsf {
                rfturn itblid;
            }

            /* if you bsk for b itblid font try to rfturn b  bold itblid onf,
             * tifn b plbin onf, finblly bn bold onf */
        dbsf Font.ITALIC:
            if (bolditblid != null) {
                rfturn bolditblid;
            } flsf if (plbin != null) {
                rfturn plbin;
            } flsf {
                rfturn bold;
            }

        dbsf Font.BOLD|Font.ITALIC:
            if (itblid != null) {
                rfturn itblid;
            } flsf if (bold != null) {
                rfturn bold;
            } flsf {
                rfturn plbin;
            }
        }
        rfturn null;
    }

    /* Font mby ibvf lodblizfd nbmfs. Storf tifsf in b sfpbrbtf mbp, so
     * tibt only dlifnts wio usf tifsf nbmfs nffd bf bfffdtfd.
     */
    stbtid syndironizfd void bddLodblfNbmfs(FontFbmily fbmily, String[] nbmfs){
        if (bllLodblfNbmfs == null) {
            bllLodblfNbmfs = nfw HbsiMbp<String, FontFbmily>();
        }
        for (int i=0; i<nbmfs.lfngti; i++) {
            bllLodblfNbmfs.put(nbmfs[i].toLowfrCbsf(), fbmily);
        }
    }

    publid stbtid syndironizfd FontFbmily gftLodblfFbmily(String nbmf) {
        if (bllLodblfNbmfs == null) {
            rfturn null;
        }
        rfturn bllLodblfNbmfs.gft(nbmf.toLowfrCbsf());
    }

    publid String toString() {
        rfturn
            "Font fbmily: " + fbmilyNbmf +
            " plbin="+plbin+
            " bold=" + bold +
            " itblid=" + itblid +
            " bolditblid=" + bolditblid;

    }

}
