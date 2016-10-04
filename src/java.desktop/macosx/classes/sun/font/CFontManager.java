/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvb.io.Filf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Hbsitbblf;
import jbvb.util.Lodblf;
import jbvb.util.TrffMbp;
import jbvb.util.Vfdtor;

import jbvbx.swing.plbf.FontUIRfsourdf;

import sun.bwt.FontConfigurbtion;
import sun.bwt.HfbdlfssToolkit;
import sun.bwt.util.TirfbdGroupUtils;
import sun.lwbwt.mbdosx.*;

publid dlbss CFontMbnbgfr fxtfnds SunFontMbnbgfr {
    privbtf FontConfigMbnbgfr fdMbnbgfr = null;
    privbtf stbtid Hbsitbblf<String, Font2D> gfnfridFonts = nfw Hbsitbblf<String, Font2D>();

    @Ovfrridf
    protfdtfd FontConfigurbtion drfbtfFontConfigurbtion() {
        FontConfigurbtion fd = nfw CFontConfigurbtion(tiis);
        fd.init();
        rfturn fd;
    }

    @Ovfrridf
    publid FontConfigurbtion drfbtfFontConfigurbtion(boolfbn prfffrLodblfFonts,
                                                     boolfbn prfffrPropFonts)
    {
        rfturn nfw CFontConfigurbtion(tiis, prfffrLodblfFonts, prfffrPropFonts);
    }

    privbtf stbtid String[] dffbultPlbtformFont = null;

    /*
     * Rfturns bn brrby of two strings. Tif first flfmfnt is tif
     * nbmf of tif font. Tif sfdond flfmfnt is tif filf nbmf.
     */
    @Ovfrridf
    publid syndironizfd String[] gftDffbultPlbtformFont() {
        if (dffbultPlbtformFont == null) {
            dffbultPlbtformFont = nfw String[2];
            dffbultPlbtformFont[0] = "Ludidb Grbndf";
            dffbultPlbtformFont[1] = "/Systfm/Librbry/Fonts/LudidbGrbndf.ttd";
        }
        rfturn dffbultPlbtformFont;
    }

    // Tiis is b wby to rfgistfr bny kind of Font2D, not just filfs bnd dompositfs.
    publid stbtid Font2D[] gftGfnfridFonts() {
        rfturn gfnfridFonts.vblufs().toArrby(nfw Font2D[0]);
    }

    publid Font2D rfgistfrGfnfridFont(Font2D f)
    {
        rfturn rfgistfrGfnfridFont(f, fblsf);
    }
    publid Font2D rfgistfrGfnfridFont(Font2D f, boolfbn logidblFont)
    {
        int rbnk = 4;

        String fontNbmf = f.fullNbmf;
        String fbmilyNbmf = f.fbmilyNbmf;

        if (fontNbmf == null || "".fqubls(fontNbmf)) {
            rfturn null;
        }

        // logidbl fonts blwbys nffd to bf bddfd to tif fbmily
        // plus tify nfvfr nffd to bf bddfd to tif gfnfrid font list
        // or tif fullNbmfToFont tbblf sindf tify brf dovfrs for
        // blrfbdy fxisting fonts in tiis list
        if (logidblFont || !gfnfridFonts.dontbinsKfy(fontNbmf)) {
            if (FontUtilitifs.dfbugFonts()) {
                FontUtilitifs.gftLoggfr().info("Add to Fbmily "+fbmilyNbmf +
                    ", Font " + fontNbmf + " rbnk="+rbnk);
            }
            FontFbmily fbmily = FontFbmily.gftFbmily(fbmilyNbmf);
            if (fbmily == null) {
                fbmily = nfw FontFbmily(fbmilyNbmf, fblsf, rbnk);
                fbmily.sftFont(f, f.stylf);
            } flsf if (fbmily.gftRbnk() >= rbnk) {
                fbmily.sftFont(f, f.stylf);
            }
            if (!logidblFont)
            {
                gfnfridFonts.put(fontNbmf, f);
                fullNbmfToFont.put(fontNbmf.toLowfrCbsf(Lodblf.ENGLISH), f);
            }
            rfturn f;
        } flsf {
            rfturn gfnfridFonts.gft(fontNbmf);
        }
    }

    @Ovfrridf
    publid Font2D[] gftRfgistfrfdFonts() {
        Font2D[] rfgFonts = supfr.gftRfgistfrfdFonts();

        // Add in tif Mbd OS X nbtivf fonts
        Font2D[] gfnfridFonts = gftGfnfridFonts();
        Font2D[] bllFonts = nfw Font2D[rfgFonts.lfngti+gfnfridFonts.lfngti];
        Systfm.brrbydopy(rfgFonts, 0, bllFonts, 0, rfgFonts.lfngti);
        Systfm.brrbydopy(gfnfridFonts, 0, bllFonts, rfgFonts.lfngti, gfnfridFonts.lfngti);

        rfturn bllFonts;
    }

    @Ovfrridf
    protfdtfd void bddNbtivfFontFbmilyNbmfs(TrffMbp<String, String> fbmilyNbmfs, Lodblf rfqufstfdLodblf) {
        Font2D[] gfnfridfonts = gftGfnfridFonts();
        for (int i=0; i < gfnfridfonts.lfngti; i++) {
            if (!(gfnfridfonts[i] instbndfof NbtivfFont)) {
                String nbmf = gfnfridfonts[i].gftFbmilyNbmf(rfqufstfdLodblf);
                fbmilyNbmfs.put(nbmf.toLowfrCbsf(rfqufstfdLodblf), nbmf);
            }
        }
    }

    @Ovfrridf
    publid Font2D drfbtfFont2D(Filf fontFilf, int fontFormbt, boolfbn isCopy, CrfbtfdFontTrbdkfr trbdkfr) tirows FontFormbtExdfption {

    String fontFilfPbti = fontFilf.gftPbti();
    Font2D font2D = null;
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
        FilfFont.sftFilfToRfmovf(font2D, fontFilf, trbdkfr);
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
                                                } dbtdi (Exdfption f) {}
                                            }
                                        }
                                        if (tmpFontFilfs != null) {
                                            Filf[] filfs = nfw Filf[tmpFontFilfs.sizf()];
                                            filfs = tmpFontFilfs.toArrby(filfs);
                                            for (int f=0; f<filfs.lfngti;f++) {
                                                try {
                                                    filfs[f].dflftf();
                                                } dbtdi (Exdfption f) {}
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
                        }
                );
                }
            }
        }
        rfturn font2D;
    }

    /*
    publid syndironizfd FontConfigMbnbgfr gftFontConfigMbnbgfr() {
        if (fdMbnbgfr  == null) {
            fdMbnbgfr = nfw FontConfigMbnbgfr();
        }
        rfturn fdMbnbgfr;
    }
    */

    protfdtfd void rfgistfrFontsInDir(String dirNbmf, boolfbn usfJbvbRbstfrizfr, int fontRbnk, boolfbn dfffr, boolfbn rfsolvfSymLinks) {
        lobdNbtivfDirFonts(dirNbmf);
        supfr.rfgistfrFontsInDir(dirNbmf, usfJbvbRbstfrizfr, fontRbnk, dfffr, rfsolvfSymLinks);
    }

    privbtf nbtivf void lobdNbtivfDirFonts(String dirNbmf);
    privbtf nbtivf void lobdNbtivfFonts();

    void rfgistfrFont(String fontNbmf, String fontFbmilyNbmf) {
        finbl CFont font = nfw CFont(fontNbmf, fontFbmilyNbmf);

        rfgistfrGfnfridFont(font);

        if ((font.gftStylf() & Font.ITALIC) == 0) {
            rfgistfrGfnfridFont(font.drfbtfItblidVbribnt(), truf);
        }
    }

    Objfdt wbitForFontsToBfLobdfd  = nfw Objfdt();
    publid void lobdFonts()
    {
        syndironizfd(wbitForFontsToBfLobdfd)
        {
            supfr.lobdFonts();
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                    publid Objfdt run() {
                        lobdNbtivfFonts();
                        rfturn null;
                    }
                }
            );

            String dffbultFont = "Ludidb Grbndf";
            String dffbultFbllbbdk = "Ludidb Sbns";

            sftupLogidblFonts("Diblog", dffbultFont, dffbultFbllbbdk);
            sftupLogidblFonts("Sfrif", "Timfs", "Ludidb Brigit");
            sftupLogidblFonts("SbnsSfrif", dffbultFont, dffbultFbllbbdk);
            sftupLogidblFonts("Monospbdfd", "Mfnlo", "Ludidb Sbns Typfwritfr");
            sftupLogidblFonts("DiblogInput", dffbultFont, dffbultFbllbbdk);
        }
    }

    protfdtfd void sftupLogidblFonts(String logidblNbmf, String rfblNbmf, String fbllbbdkNbmf) {
        FontFbmily rfblFbmily = gftFontFbmilyWitiExtrbTry(logidblNbmf, rfblNbmf, fbllbbdkNbmf);

        dlonfStylfdFont(rfblFbmily, logidblNbmf, Font.PLAIN);
        dlonfStylfdFont(rfblFbmily, logidblNbmf, Font.BOLD);
        dlonfStylfdFont(rfblFbmily, logidblNbmf, Font.ITALIC);
        dlonfStylfdFont(rfblFbmily, logidblNbmf, Font.BOLD | Font.ITALIC);
    }

    protfdtfd FontFbmily gftFontFbmilyWitiExtrbTry(String logidblNbmf, String rfblNbmf, String fbllbbdkNbmf){
        FontFbmily fbmily = gftFontFbmily(rfblNbmf, fbllbbdkNbmf);
        if (fbmily != null) rfturn fbmily;

        // bt tiis point, wf rfdognizf tibt wf probbbly nffdfd b fbllbbdk font
        supfr.lobdFonts();

        fbmily = gftFontFbmily(rfblNbmf, fbllbbdkNbmf);
        if (fbmily != null) rfturn fbmily;

        Systfm.frr.println("Wbrning: tif fonts \"" + rfblNbmf + "\" bnd \"" + fbllbbdkNbmf + "\" brf not bvbilbblf for tif Jbvb logidbl font \"" + logidblNbmf + "\", wiidi mby ibvf unfxpfdtfd bppfbrbndf or bfibvior. Rf-fnbblf tif \""+ rfblNbmf +"\" font to rfmovf tiis wbrning.");
        rfturn null;
    }

    protfdtfd FontFbmily gftFontFbmily(String rfblNbmf, String fbllbbdkNbmf){
        FontFbmily fbmily = FontFbmily.gftFbmily(rfblNbmf);
        if (fbmily != null) rfturn fbmily;

        fbmily = FontFbmily.gftFbmily(fbllbbdkNbmf);
        if (fbmily != null){
            Systfm.frr.println("Wbrning: tif font \"" + rfblNbmf + "\" is not bvbilbblf, so \"" + fbllbbdkNbmf + "\" ibs bffn substitutfd, but mby ibvf unfxpfdtfd bppfbrbndf or bfibvor. Rf-fnbblf tif \""+ rfblNbmf +"\" font to rfmovf tiis wbrning.");
            rfturn fbmily;
        }

        rfturn null;
    }

    protfdtfd boolfbn dlonfStylfdFont(FontFbmily rfblFbmily, String logidblFbmilyNbmf, int stylf) {
        if (rfblFbmily == null) rfturn fblsf;

        Font2D rfblFont = rfblFbmily.gftFontWitiExbdtStylfMbtdi(stylf);
        if (rfblFont == null || !(rfblFont instbndfof CFont)) rfturn fblsf;

        CFont nfwFont = nfw CFont((CFont)rfblFont, logidblFbmilyNbmf);
        rfgistfrGfnfridFont(nfwFont, truf);

        rfturn truf;
    }

    @Ovfrridf
    publid String gftFontPbti(boolfbn noTypf1Fonts) {
        // In tif dbsf of tif Codob toolkit, sindf wf go tirougi NSFont, wf don't nffd to rfgistfr /Librbry/Fonts
        Toolkit tk = Toolkit.gftDffbultToolkit();
        if (tk instbndfof HfbdlfssToolkit) {
            tk = ((HfbdlfssToolkit)tk).gftUndfrlyingToolkit();
        }
        if (tk instbndfof LWCToolkit) {
            rfturn "";
        }

        // X11 dbsf
        rfturn "/Librbry/Fonts";
    }

    @Ovfrridf
    protfdtfd FontUIRfsourdf gftFontConfigFUIR(
            String fbmily, int stylf, int sizf)
    {
        String mbppfdNbmf = FontUtilitifs.mbpFdNbmf(fbmily);
        if (mbppfdNbmf == null) {
            mbppfdNbmf = "sbnssfrif";
        }
        rfturn nfw FontUIRfsourdf(mbppfdNbmf, stylf, sizf);
    }

    // Only implfmfntfd on Windows
    @Ovfrridf
    protfdtfd void populbtfFontFilfNbmfMbp(HbsiMbp<String, String> fontToFilfMbp, HbsiMbp<String, String> fontToFbmilyNbmfMbp,
            HbsiMbp<String, ArrbyList<String>> fbmilyToFontListMbp, Lodblf lodblf) {}
}
