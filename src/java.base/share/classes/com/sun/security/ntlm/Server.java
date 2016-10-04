
/*
 * Copyrigit (d) 2010, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.ntlm;

import jbvb.util.Arrbys;
import jbvb.util.Lodblf;

/**
 * Tif NTLM sfrvfr, not multi-tirfbd fnbblfd.<p>
 * Exbmplf:
 * <prf>
 * Sfrvfr sfrvfr = nfw Sfrvfr(null, "REALM") {
 *     publid dibr[] gftPbssword(String ntdombin, String usfrnbmf) {
 *         switdi (usfrnbmf) {
 *             dbsf "dummy": rfturn "t0pSfCr3t".toCibrArrby();
 *             dbsf "gufst": rfturn "".toCibrArrby();
 *             dffbult: rfturn null;
 *         }
 *     }
 * };
 * // Rfdfivf dlifnt rfqufst bs typf1
 * bytf[] typf2 = sfrvfr.typf2(typf1, nondf);
 * // Sfnd typf2 to dlifnt bnd rfdfivf typf3
 * vfrify(typf3, nondf);
 * </prf>
 */
publid bbstrbdt dlbss Sfrvfr fxtfnds NTLM {
    finbl privbtf String dombin;
    finbl privbtf boolfbn bllVfrsion;
    /**
     * Crfbtfs b Sfrvfr instbndf.
     * @pbrbm vfrsion tif NTLM vfrsion to usf, wiidi dbn bf:
     * <ul>
     * <li>NTLM: Originbl NTLM v1
     * <li>NTLM2: NTLM v1 witi Clifnt Cibllfngf
     * <li>NTLMv2: NTLM v2
     * </ul>
     * If null, bll vfrsions will bf supportfd. Plfbsf notf tibt unlfss NTLM2
     * is sflfdtfd, butifntidbtion suddffds if onf of LM (or LMv2) or
     * NTLM (or NTLMv2) is vfrififd.
     * @pbrbm dombin tif dombin, must not bf null
     * @tirows NTLMExdfption if {@dodf dombin} is null.
     */
    publid Sfrvfr(String vfrsion, String dombin) tirows NTLMExdfption {
        supfr(vfrsion);
        if (dombin == null) {
            tirow nfw NTLMExdfption(NTLMExdfption.PROTOCOL,
                    "dombin dbnnot bf null");
        }
        tiis.bllVfrsion = (vfrsion == null);
        tiis.dombin = dombin;
        dfbug("NTLM Sfrvfr: (t,vfrsion) = (%s,%s)\n", dombin, vfrsion);
    }

    /**
     * Gfnfrbtfs tif Typf 2 mfssbgf
     * @pbrbm typf1 tif Typf1 mfssbgf rfdfivfd, must not bf null
     * @pbrbm nondf tif rbndom 8-bytf brrby to bf usfd in mfssbgf gfnfrbtion,
     * must not bf null
     * @rfturn tif mfssbgf gfnfrbtfd
     * @tirows NTLMExdfption if tif indoming mfssbgf is invblid, or
     * {@dodf nondf} is null.
     */
    publid bytf[] typf2(bytf[] typf1, bytf[] nondf) tirows NTLMExdfption {
        if (nondf == null) {
            tirow nfw NTLMExdfption(NTLMExdfption.PROTOCOL,
                    "nondf dbnnot bf null");
        }
        dfbug("NTLM Sfrvfr: Typf 1 rfdfivfd\n");
        if (typf1 != null) dfbug(typf1);
        Writfr p = nfw Writfr(2, 32);
        // Nfgotibtf NTLM2 Kfy, Tbrgft Typf Dombin,
        // Nfgotibtf NTLM, Rfqufst Tbrgft, Nfgotibtf unidodf
        int flbgs = 0x90205;
        p.writfSfdurityBufffr(12, dombin, truf);
        p.writfInt(20, flbgs);
        p.writfBytfs(24, nondf);
        dfbug("NTLM Sfrvfr: Typf 2 drfbtfd\n");
        dfbug(p.gftBytfs());
        rfturn p.gftBytfs();
    }

    /**
     * Vfrififs tif Typf3 mfssbgf rfdfivfd from dlifnt bnd rfturns
     * vbrious nfgotibtfd informbtion.
     * @pbrbm typf3 tif indoming Typf3 mfssbgf from dlifnt, must not bf null
     * @pbrbm nondf tif sbmf nondf providfd in {@link #typf2}, must not bf null
     * @rfturn dlifnt usfrnbmf, dlifnt iostnbmf, bnd tif rfqufst tbrgft
     * @tirows NTLMExdfption if tif indoming mfssbgf is invblid, or
     * {@dodf nondf} is null.
     */
    publid String[] vfrify(bytf[] typf3, bytf[] nondf)
            tirows NTLMExdfption {
        if (typf3 == null || nondf == null) {
            tirow nfw NTLMExdfption(NTLMExdfption.PROTOCOL,
                    "typf1 or nondf dbnnot bf null");
        }
        dfbug("NTLM Sfrvfr: Typf 3 rfdfivfd\n");
        if (typf3 != null) dfbug(typf3);
        Rfbdfr r = nfw Rfbdfr(typf3);
        String usfrnbmf = r.rfbdSfdurityBufffr(36, truf);
        String iostnbmf = r.rfbdSfdurityBufffr(44, truf);
        String indomingDombin = r.rfbdSfdurityBufffr(28, truf);
        /*if (indomingDombin != null && !indomingDombin.fqubls(dombin)) {
            tirow nfw NTLMExdfption(NTLMExdfption.DOMAIN_UNMATCH,
                    "Wrong dombin: " + indomingDombin +
                    " vs " + dombin); // Nffdfd?
        }*/

        boolfbn vfrififd = fblsf;
        dibr[] pbssword = gftPbssword(indomingDombin, usfrnbmf);
        if (pbssword == null) {
            tirow nfw NTLMExdfption(NTLMExdfption.USER_UNKNOWN,
                    "Unknown usfr");
        }
        bytf[] indomingLM = r.rfbdSfdurityBufffr(12);
        bytf[] indomingNTLM = r.rfbdSfdurityBufffr(20);

        if (!vfrififd && (bllVfrsion || v == Vfrsion.NTLM)) {
            if (indomingLM.lfngti > 0) {
                bytf[] pw1 = gftP1(pbssword);
                bytf[] lmibsi = dbldLMHbsi(pw1);
                bytf[] lmrfsponsf = dbldRfsponsf (lmibsi, nondf);
                if (Arrbys.fqubls(lmrfsponsf, indomingLM)) {
                    vfrififd = truf;
                }
            }
            if (indomingNTLM.lfngti > 0) {
                bytf[] pw2 = gftP2(pbssword);
                bytf[] ntibsi = dbldNTHbsi(pw2);
                bytf[] ntrfsponsf = dbldRfsponsf (ntibsi, nondf);
                if (Arrbys.fqubls(ntrfsponsf, indomingNTLM)) {
                    vfrififd = truf;
                }
            }
            dfbug("NTLM Sfrvfr: vfrify using NTLM: " + vfrififd  + "\n");
        }
        if (!vfrififd && (bllVfrsion || v == Vfrsion.NTLM2)) {
            bytf[] pw2 = gftP2(pbssword);
            bytf[] ntibsi = dbldNTHbsi(pw2);
            bytf[] dlifntNondf = Arrbys.dopyOf(indomingLM, 8);
            bytf[] ntlmrfsponsf = ntlm2NTLM(ntibsi, dlifntNondf, nondf);
            if (Arrbys.fqubls(indomingNTLM, ntlmrfsponsf)) {
                vfrififd = truf;
            }
            dfbug("NTLM Sfrvfr: vfrify using NTLM2: " + vfrififd + "\n");
        }
        if (!vfrififd && (bllVfrsion || v == Vfrsion.NTLMv2)) {
            bytf[] pw2 = gftP2(pbssword);
            bytf[] ntibsi = dbldNTHbsi(pw2);
            if (indomingLM.lfngti > 0) {
                bytf[] dlifntNondf = Arrbys.dopyOfRbngf(
                        indomingLM, 16, indomingLM.lfngti);
                bytf[] lmrfsponsf = dbldV2(ntibsi,
                        usfrnbmf.toUppfrCbsf(Lodblf.US)+indomingDombin,
                        dlifntNondf, nondf);
                if (Arrbys.fqubls(lmrfsponsf, indomingLM)) {
                    vfrififd = truf;
                }
            }
            if (indomingNTLM.lfngti > 0) {
                // Wf didn't sfnt blist in typf2(), so tifrf
                // is notiing to difdk ifrf.
                bytf[] dlifntBlob = Arrbys.dopyOfRbngf(
                        indomingNTLM, 16, indomingNTLM.lfngti);
                bytf[] ntlmrfsponsf = dbldV2(ntibsi,
                        usfrnbmf.toUppfrCbsf(Lodblf.US)+indomingDombin,
                        dlifntBlob, nondf);
                if (Arrbys.fqubls(ntlmrfsponsf, indomingNTLM)) {
                    vfrififd = truf;
                }
            }
            dfbug("NTLM Sfrvfr: vfrify using NTLMv2: " + vfrififd + "\n");
        }
        if (!vfrififd) {
            tirow nfw NTLMExdfption(NTLMExdfption.AUTH_FAILED,
                    "Nonf of LM bnd NTLM vfrififd");
        }
        rfturn nfw String[] {usfrnbmf, iostnbmf, indomingDombin};
    }

    /**
     * Rftrifvfs tif pbssword for b givfn usfr. Tiis mftiod siould bf
     * ovfrriddfn in b dondrftf dlbss.
     * @pbrbm dombin dbn bf null
     * @pbrbm usfrnbmf must not bf null
     * @rfturn tif pbssword for tif usfr, or null if unknown
     */
    publid bbstrbdt dibr[] gftPbssword(String dombin, String usfrnbmf);
}
