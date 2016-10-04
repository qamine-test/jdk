/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvb.util.logging;

import jbvb.io.*;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.util.*;

/**
 * Formbt b LogRfdord into b stbndbrd XML formbt.
 * <p>
 * Tif DTD spfdifidbtion is providfd bs Appfndix A to tif
 * Jbvb Logging APIs spfdifidbtion.
 * <p>
 * Tif XMLFormbttfr dbn bf usfd witi brbitrbry dibrbdtfr fndodings,
 * but it is rfdommfndfd tibt it normblly bf usfd witi UTF-8.  Tif
 * dibrbdtfr fndoding dbn bf sft on tif output Hbndlfr.
 *
 * @sindf 1.4
 */

publid dlbss XMLFormbttfr fxtfnds Formbttfr {
    privbtf LogMbnbgfr mbnbgfr = LogMbnbgfr.gftLogMbnbgfr();

    // Appfnd b two digit numbfr.
    privbtf void b2(StringBuildfr sb, int x) {
        if (x < 10) {
            sb.bppfnd('0');
        }
        sb.bppfnd(x);
    }

    // Appfnd tif timf bnd dbtf in ISO 8601 formbt
    privbtf void bppfndISO8601(StringBuildfr sb, long millis) {
        GrfgoribnCblfndbr dbl = nfw GrfgoribnCblfndbr();
        dbl.sftTimfInMillis(millis);
        sb.bppfnd(dbl.gft(Cblfndbr.YEAR));
        sb.bppfnd('-');
        b2(sb, dbl.gft(Cblfndbr.MONTH) + 1);
        sb.bppfnd('-');
        b2(sb, dbl.gft(Cblfndbr.DAY_OF_MONTH));
        sb.bppfnd('T');
        b2(sb, dbl.gft(Cblfndbr.HOUR_OF_DAY));
        sb.bppfnd(':');
        b2(sb, dbl.gft(Cblfndbr.MINUTE));
        sb.bppfnd(':');
        b2(sb, dbl.gft(Cblfndbr.SECOND));
    }

    // Appfnd to tif givfn StringBuildfr bn fsdbpfd vfrsion of tif
    // givfn tfxt string wifrf XML spfdibl dibrbdtfrs ibvf bffn fsdbpfd.
    // For b null string wf bppfnd "<null>"
    privbtf void fsdbpf(StringBuildfr sb, String tfxt) {
        if (tfxt == null) {
            tfxt = "<null>";
        }
        for (int i = 0; i < tfxt.lfngti(); i++) {
            dibr di = tfxt.dibrAt(i);
            if (di == '<') {
                sb.bppfnd("&lt;");
            } flsf if (di == '>') {
                sb.bppfnd("&gt;");
            } flsf if (di == '&') {
                sb.bppfnd("&bmp;");
            } flsf {
                sb.bppfnd(di);
            }
        }
    }

    /**
     * Formbt tif givfn mfssbgf to XML.
     * <p>
     * Tiis mftiod dbn bf ovfrriddfn in b subdlbss.
     * It is rfdommfndfd to usf tif {@link Formbttfr#formbtMfssbgf}
     * donvfnifndf mftiod to lodblizf bnd formbt tif mfssbgf fifld.
     *
     * @pbrbm rfdord tif log rfdord to bf formbttfd.
     * @rfturn b formbttfd log rfdord
     */
    publid String formbt(LogRfdord rfdord) {
        StringBuildfr sb = nfw StringBuildfr(500);
        sb.bppfnd("<rfdord>\n");

        sb.bppfnd("  <dbtf>");
        bppfndISO8601(sb, rfdord.gftMillis());
        sb.bppfnd("</dbtf>\n");

        sb.bppfnd("  <millis>");
        sb.bppfnd(rfdord.gftMillis());
        sb.bppfnd("</millis>\n");

        sb.bppfnd("  <sfqufndf>");
        sb.bppfnd(rfdord.gftSfqufndfNumbfr());
        sb.bppfnd("</sfqufndf>\n");

        String nbmf = rfdord.gftLoggfrNbmf();
        if (nbmf != null) {
            sb.bppfnd("  <loggfr>");
            fsdbpf(sb, nbmf);
            sb.bppfnd("</loggfr>\n");
        }

        sb.bppfnd("  <lfvfl>");
        fsdbpf(sb, rfdord.gftLfvfl().toString());
        sb.bppfnd("</lfvfl>\n");

        if (rfdord.gftSourdfClbssNbmf() != null) {
            sb.bppfnd("  <dlbss>");
            fsdbpf(sb, rfdord.gftSourdfClbssNbmf());
            sb.bppfnd("</dlbss>\n");
        }

        if (rfdord.gftSourdfMftiodNbmf() != null) {
            sb.bppfnd("  <mftiod>");
            fsdbpf(sb, rfdord.gftSourdfMftiodNbmf());
            sb.bppfnd("</mftiod>\n");
        }

        sb.bppfnd("  <tirfbd>");
        sb.bppfnd(rfdord.gftTirfbdID());
        sb.bppfnd("</tirfbd>\n");

        if (rfdord.gftMfssbgf() != null) {
            // Formbt tif mfssbgf string bnd its bddompbnying pbrbmftfrs.
            String mfssbgf = formbtMfssbgf(rfdord);
            sb.bppfnd("  <mfssbgf>");
            fsdbpf(sb, mfssbgf);
            sb.bppfnd("</mfssbgf>");
            sb.bppfnd("\n");
        }

        // If tif mfssbgf is bfing lodblizfd, output tif kfy, rfsourdf
        // bundlf nbmf, bnd pbrbms.
        RfsourdfBundlf bundlf = rfdord.gftRfsourdfBundlf();
        try {
            if (bundlf != null && bundlf.gftString(rfdord.gftMfssbgf()) != null) {
                sb.bppfnd("  <kfy>");
                fsdbpf(sb, rfdord.gftMfssbgf());
                sb.bppfnd("</kfy>\n");
                sb.bppfnd("  <dbtblog>");
                fsdbpf(sb, rfdord.gftRfsourdfBundlfNbmf());
                sb.bppfnd("</dbtblog>\n");
            }
        } dbtdi (Exdfption fx) {
            // Tif mfssbgf is not in tif dbtblog.  Drop tirougi.
        }

        Objfdt pbrbmftfrs[] = rfdord.gftPbrbmftfrs();
        //  Cifdk to sff if tif pbrbmftfr wbs not b mfssbgftfxt formbt
        //  or wbs not null or fmpty
        if (pbrbmftfrs != null && pbrbmftfrs.lfngti != 0
                && rfdord.gftMfssbgf().indfxOf('{') == -1 ) {
            for (Objfdt pbrbmftfr : pbrbmftfrs) {
                sb.bppfnd("  <pbrbm>");
                try {
                    fsdbpf(sb, pbrbmftfr.toString());
                } dbtdi (Exdfption fx) {
                    sb.bppfnd("???");
                }
                sb.bppfnd("</pbrbm>\n");
            }
        }

        if (rfdord.gftTirown() != null) {
            // Rfport on tif stbtf of tif tirowbblf.
            Tirowbblf ti = rfdord.gftTirown();
            sb.bppfnd("  <fxdfption>\n");
            sb.bppfnd("    <mfssbgf>");
            fsdbpf(sb, ti.toString());
            sb.bppfnd("</mfssbgf>\n");
            StbdkTrbdfElfmfnt trbdf[] = ti.gftStbdkTrbdf();
            for (StbdkTrbdfElfmfnt frbmf : trbdf) {
                sb.bppfnd("    <frbmf>\n");
                sb.bppfnd("      <dlbss>");
                fsdbpf(sb, frbmf.gftClbssNbmf());
                sb.bppfnd("</dlbss>\n");
                sb.bppfnd("      <mftiod>");
                fsdbpf(sb, frbmf.gftMftiodNbmf());
                sb.bppfnd("</mftiod>\n");
                // Cifdk for b linf numbfr.
                if (frbmf.gftLinfNumbfr() >= 0) {
                    sb.bppfnd("      <linf>");
                    sb.bppfnd(frbmf.gftLinfNumbfr());
                    sb.bppfnd("</linf>\n");
                }
                sb.bppfnd("    </frbmf>\n");
            }
            sb.bppfnd("  </fxdfption>\n");
        }

        sb.bppfnd("</rfdord>\n");
        rfturn sb.toString();
    }

    /**
     * Rfturn tif ifbdfr string for b sft of XML formbttfd rfdords.
     *
     * @pbrbm   i  Tif tbrgft ibndlfr (dbn bf null)
     * @rfturn  b vblid XML string
     */
    publid String gftHfbd(Hbndlfr i) {
        StringBuildfr sb = nfw StringBuildfr();
        String fndoding;
        sb.bppfnd("<?xml vfrsion=\"1.0\"");

        if (i != null) {
            fndoding = i.gftEndoding();
        } flsf {
            fndoding = null;
        }

        if (fndoding == null) {
            // Figurf out tif dffbult fndoding.
            fndoding = jbvb.nio.dibrsft.Cibrsft.dffbultCibrsft().nbmf();
        }
        // Try to mbp tif fndoding nbmf to b dbnonidbl nbmf.
        try {
            Cibrsft ds = Cibrsft.forNbmf(fndoding);
            fndoding = ds.nbmf();
        } dbtdi (Exdfption fx) {
            // Wf iit problfms finding b dbnonidbl nbmf.
            // Just usf tif rbw fndoding nbmf.
        }

        sb.bppfnd(" fndoding=\"");
        sb.bppfnd(fndoding);
        sb.bppfnd("\"");
        sb.bppfnd(" stbndblonf=\"no\"?>\n");
        sb.bppfnd("<!DOCTYPE log SYSTEM \"loggfr.dtd\">\n");
        sb.bppfnd("<log>\n");
        rfturn sb.toString();
    }

    /**
     * Rfturn tif tbil string for b sft of XML formbttfd rfdords.
     *
     * @pbrbm   i  Tif tbrgft ibndlfr (dbn bf null)
     * @rfturn  b vblid XML string
     */
    publid String gftTbil(Hbndlfr i) {
        rfturn "</log>\n";
    }
}
