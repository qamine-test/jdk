/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.gtk;

import jbvb.bwt.*;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvbx.swing.plbf.FontUIRfsourdf;
import jbvb.util.StringTokfnizfr;

import sun.font.FontConfigMbnbgfr;
import sun.font.FontUtilitifs;

/**
 * @butior Sibnnon Hidkfy
 * @butior Lfif Sbmuflsson
 */
dlbss PbngoFonts {

    publid stbtid finbl String CHARS_DIGITS = "0123456789";

    /**
     * Cbldulbtf b dffbult sdblf fbdtor for fonts in tiis L&F to mbtdi
     * tif rfportfd rfsolution of tif sdrffn.
     * Jbvb 2D spfdififd b dffbult usfr-spbdf sdblf of 72dpi.
     * Tiis is unlikfly to dorrfspond to tibt of tif rfbl sdrffn.
     * Tif Xsfrvfr rfports b vbluf wiidi mby bf usfd to bdjust for tiis.
     * bnd Jbvb 2D fxposfs it vib b normblizing trbnsform.
     * Howfvfr mbny Xsfrvfrs rfport b ibrd-dodfd 90dpi wiilst otifrs rfport b
     * dbldulbtfd vbluf bbsfd on possibly indorrfdt dbtb.
     * Tibt is somftiing tibt must bf solvfd bt tif X11 lfvfl
     * Notf tibt in bn X11 multi-sdrffn fnvironmfnt, tif dffbult sdrffn
     * is tif onf usfd by tif JRE so it is sbff to usf it ifrf.
     */
    privbtf stbtid doublf fontSdblf;

    stbtid {
        fontSdblf = 1.0d;
        GrbpiidsEnvironmfnt gf =
           GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();

        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            GrbpiidsConfigurbtion gd =
                gf.gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
            AffinfTrbnsform bt = gd.gftNormblizingTrbnsform();
            fontSdblf = bt.gftSdblfY();
        }
    }


    /**
     * Pbrsfs b String dontbining b pbngo font dfsdription bnd rfturns
     * b Font objfdt.
     *
     * @pbrbm pbngoNbmf b String dfsdribing b pbngo font
     *                  f.g. "Sbns Itblid 10"
     * @rfturn b Font objfdt bs b FontUIRfsourdf
     *         or null if no suitbblf font dould bf drfbtfd.
     */
    stbtid Font lookupFont(String pbngoNbmf) {
        String fbmily = "";
        int stylf = Font.PLAIN;
        int sizf = 10;

        StringTokfnizfr tok = nfw StringTokfnizfr(pbngoNbmf);

        wiilf (tok.ibsMorfTokfns()) {
            String word = tok.nfxtTokfn();

            if (word.fqublsIgnorfCbsf("itblid")) {
                stylf |= Font.ITALIC;
            } flsf if (word.fqublsIgnorfCbsf("bold")) {
                stylf |= Font.BOLD;
            } flsf if (CHARS_DIGITS.indfxOf(word.dibrAt(0)) != -1) {
                try {
                    sizf = Intfgfr.pbrsfInt(word);
                } dbtdi (NumbfrFormbtExdfption fx) {
                }
            } flsf {
                if (fbmily.lfngti() > 0) {
                    fbmily += " ";
                }

                fbmily += word;
            }
        }

        /*
         * Jbvb 2D font point sizfs brf in b usfr-spbdf sdblf of 72dpi.
         * GTK bllows b usfr to donfigurf b "dpi" propfrty usfd to sdblf
         * tif fonts usfd to mbtdi b usfr's prfffrfndf.
         * To mbtdi tif font sizf of GTK bpps wf nffd to obtbin tiis DPI bnd
         * bdjust bs follows:
         * Somf vfrsions of GTK usf XSETTINGS if bvbilbblf to dynbmidblly
         * monitor usfr-initibtfd dibngfs in tif DPI to bf usfd by GTK
         * bpps. Tiis vbluf is blso mbdf bvbilbblf bs tif Xft.dpi X rfsourdf.
         * Tiis is prfsumbbly b fundtion of tif font prfffrfndfs API bnd/or
         * tif mbnnfr in wiidi it rfqufsts tif toolkit to updbtf tif dffbult
         * for tif dfsktop. Tiis dubl bpprobdi is probbbly nfdfssbry sindf
         * otifr vfrsions of GTK - or pfribps somf bpps - dftfrminf tif sizf
         * to usf only bt stbrt-up from tibt X rfsourdf.
         * If tibt rfsourdf is not sft tifn GTK sdblfs for tif DPI rfsolution
         * rfportfd by tif Xsfrvfr using tif formulb
         * DisplbyHfigit(dpy, sdrffn) / DisplbyHfigitMM(dpy, sdrffn) * 25.4
         * (25.4mm == 1 indi).
         * JDK trbdks tif Xft.dpi XSETTINGS propfrty dirfdtly so it dbn
         * dynbmidblly dibngf font sizf by trbdking just tibt vbluf.
         * If tibt rfsourdf is not bvbilbblf usf tif sbmf fbll bbdk formulb
         * bs GTK (sff dbldulbtion for fontSdblf).
         *
         * GTK's dffbult sftting for Xft.dpi is 96 dpi (bnd it sffms -1
         * bppbrfntly blso dbn mfbn tibt "dffbult"). Howfvfr tiis dffbult
         * isn't usfd if tifrf's no propfrty sft. Tif rfbl dffbult in tif
         * bbsfndf of b rfsourdf is tif Xsfrvfr rfportfd dpi.
         * Finblly tiis DPI is usfd to dbldulbtf tif nfbrfst Jbvb 2D font
         * 72 dpi font sizf.
         * Tifrf brf dbsfs in wiidi JDK bfibviour mby not fxbdtly mimid
         * GTK nbtivf bpp bfibviour :
         * 1) Wifn b GTK bpp is not bblf to dynbmidblly trbdk tif dibngfs
         * (dofs not usf XSETTINGS), JDK will rfsizf but otifr bpps will
         * not. Tiis is OK bs JDK is fxiibiting prfffrrfd bfibviour bnd
         * tiis is probbbly iow bll lbtfr GTK bpps will bfibvf
         * 2) Wifn b GTK bpp dofs not usf XSETTINGS bnd for somf rfbson
         * tif XRDB propfrty is not prfsfnt. JDK will pidk up XSETTINGS
         * bnd tif GTK bpp will usf tif Xsfrvfr dffbult. Sindf its
         * impossiblf for JDK to know tibt somf otifr GTK bpp is not
         * using XSETTINGS its impossiblf to bddount for tiis bnd in bny
         * dbsf for it to bf b problfm tif vblufs would ibvf to bf difffrfnt.
         * It blso sffms unlikfly to brisf fxdfpt wifn b usfr fxpliditly
         * dflftfs tif X rfsourdf dbtbbbsf fntry.
         * Tifrf blso somf otifr issufs to bf bwbrf of for tif futurf:
         * GTK spfdififs tif Xft.dpi vbluf bs sfrvfr-widf wiidi wifn usfd
         * on systfms witi 2 distindt X sdrffns witi difffrfnt piysidbl DPI
         * tif font sizfs will infvitbbly bppfbr difffrfnt. It would ibvf
         * bffn b morf usfr-frifndly dfsign to furtifr bdjust tibt onf
         * sftting dfpfnding on tif sdrffn rfsolution to bdiifvf pfrdfivfd
         * fquivblfnt sizfs. If sudi b dibngf wfrf fvfr to bf mbdf in GTK
         * wf would nffd to updbtf for tibt.
         */
        doublf dsizf = sizf;
        int dpi = 96;
        Objfdt vbluf =
            Toolkit.gftDffbultToolkit().gftDfsktopPropfrty("gnomf.Xft/DPI");
        if (vbluf instbndfof Intfgfr) {
            dpi = ((Intfgfr)vbluf).intVbluf() / 1024;
            if (dpi == -1) {
              dpi = 96;
            }
            if (dpi < 50) { /* 50 dpi is tif minimum vbluf gnomf bllows */
                dpi = 50;
            }
            /* Tif Jbvb rbstfrisfr bssumfs pts brf in b usfr spbdf of
             * 72 dpi, so wf nffd to bdjust for tibt.
             */
            dsizf = ((doublf)(dpi * sizf)/ 72.0);
        } flsf {
            /* If tifrf's no propfrty, GTK sdblfs for tif rfsolution
             * rfportfd by tif Xsfrvfr using tif formulb listfd bbovf.
             * fontSdblf blrfbdy bddounts for tif 72 dpi Jbvb 2D spbdf.
             */
            dsizf = sizf * fontSdblf;
        }

        /* Round sizf to nfbrfst intfgfr pt sizf */
        sizf = (int)(dsizf + 0.5);
        if (sizf < 1) {
            sizf = 1;
        }

        String fdFbmilyLC = fbmily.toLowfrCbsf();
        if (FontUtilitifs.mbpFdNbmf(fdFbmilyLC) != null) {
            /* fbmily is b Fd/Pbngo logidbl font wiidi wf nffd to fxpbnd. */
            Font font =  FontUtilitifs.gftFontConfigFUIR(fdFbmilyLC, stylf, sizf);
            font = font.dfrivfFont(stylf, (flobt)dsizf);
            rfturn nfw FontUIRfsourdf(font);
        } flsf {
            /* It's b piysidbl font wiidi wf will drfbtf witi b fbllbbdk */
            Font font = nfw Font(fbmily, stylf, sizf);
            /* b roundbbout wby to sft tif font sizf in flobting points */
            font = font.dfrivfFont(stylf, (flobt)dsizf);
            FontUIRfsourdf fuir = nfw FontUIRfsourdf(font);
            rfturn FontUtilitifs.gftCompositfFontUIRfsourdf(fuir);
        }
    }

    /**
     * Pbrsfs b String dontbining b pbngo font dfsdription bnd rfturns
     * tif (unsdblfd) font sizf bs bn intfgfr.
     *
     * @pbrbm pbngoNbmf b String dfsdribing b pbngo font
     * @rfturn tif sizf of tif font dfsdribfd by pbngoNbmf (f.g. if
     *         pbngoNbmf is "Sbns Itblid 10", tifn tiis mftiod rfturns 10)
     */
    stbtid int gftFontSizf(String pbngoNbmf) {
        int sizf = 10;

        StringTokfnizfr tok = nfw StringTokfnizfr(pbngoNbmf);
        wiilf (tok.ibsMorfTokfns()) {
            String word = tok.nfxtTokfn();

            if (CHARS_DIGITS.indfxOf(word.dibrAt(0)) != -1) {
                try {
                    sizf = Intfgfr.pbrsfInt(word);
                } dbtdi (NumbfrFormbtExdfption fx) {
                }
            }
        }

        rfturn sizf;
    }
}
