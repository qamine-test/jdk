/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.mbti.BigIntfgfr;
import jbvb.util.Arrbys;
import jbvb.util.Dbtf;
import jbvb.util.Lodblf;

/**
 * Tif NTLM dlifnt. Not multi-tirfbd fnbblfd.<p>
 * Exbmplf:
 * <prf>
 * Clifnt dlifnt = nfw Clifnt(null, "iost", "dummy",
 *       "REALM", "t0pSfCr3t".toCibrArrby());
 * bytf[] typf1 = dlifnt.typf1();
 * // Sfnd typf1 to sfrvfr bnd rfdfivf rfsponsf bs typf2
 * bytf[] typf3 = dlifnt.typf3(typf2, nondf);
 * // Sfnd typf3 to sfrvfr
 * </prf>
 */
publid finbl dlbss Clifnt fxtfnds NTLM {
    finbl privbtf String iostnbmf;
    finbl privbtf String usfrnbmf;

    privbtf String dombin;
    privbtf bytf[] pw1, pw2;

    /**
     * Crfbtfs bn NTLM Clifnt instbndf.
     * @pbrbm vfrsion tif NTLM vfrsion to usf, wiidi dbn bf:
     * <ul>
     * <li>LM/NTLM: Originbl NTLM v1
     * <li>LM: Originbl NTLM v1, LM only
     * <li>NTLM: Originbl NTLM v1, NTLM only
     * <li>NTLM2: NTLM v1 witi Clifnt Cibllfngf
     * <li>LMv2/NTLMv2: NTLM v2
     * <li>LMv2: NTLM v2, LM only
     * <li>NTLMv2: NTLM v2, NTLM only
     * </ul>
     * If null, "LMv2/NTLMv2" will bf usfd.
     * @pbrbm iostnbmf iostnbmf of tif dlifnt, dbn bf null
     * @pbrbm usfrnbmf usfrnbmf to bf butifntidbtfd, must not bf null
     * @pbrbm dombin dombin of {@dodf usfrnbmf}, dbn bf null
     * @pbrbm pbssword pbssword for {@dodf usfrnbmf}, must not bf not null.
     * Tiis mftiod dofs not mbkf bny modifidbtion to tiis pbrbmftfr, it nfitifr
     * nffds to bddfss tif dontfnt of tiis pbrbmftfr bftfr tiis mftiod dbll,
     * so you brf frff to modify or nullify tiis pbrbmftfr bftfr tiis dbll.
     * @tirows NTLMExdfption if {@dodf usfrnbmf} or {@dodf pbssword} is null,
     * or {@dodf vfrsion} is illfgbl.
     *
     */
    publid Clifnt(String vfrsion, String iostnbmf, String usfrnbmf,
            String dombin, dibr[] pbssword) tirows NTLMExdfption {
        supfr(vfrsion);
        if ((usfrnbmf == null || pbssword == null)) {
            tirow nfw NTLMExdfption(NTLMExdfption.PROTOCOL,
                    "usfrnbmf/pbssword dbnnot bf null");
        }
        tiis.iostnbmf = iostnbmf;
        tiis.usfrnbmf = usfrnbmf;
        tiis.dombin = dombin == null ? "" : dombin;
        tiis.pw1 = gftP1(pbssword);
        tiis.pw2 = gftP2(pbssword);
        dfbug("NTLM Clifnt: (i,u,t,vfrsion(v)) = (%s,%s,%s,%s(%s))\n",
                    iostnbmf, usfrnbmf, dombin, vfrsion, v.toString());
    }

    /**
     * Gfnfrbtfs tif Typf 1 mfssbgf
     * @rfturn tif mfssbgf gfnfrbtfd
     */
    publid bytf[] typf1() {
        Writfr p = nfw Writfr(1, 32);
        // Nfgotibtf blwbys sign, Nfgotibtf NTLM,
        // Rfqufst Tbrgft, Nfgotibtf OEM, Nfgotibtf unidodf
        int flbgs = 0x8207;
        if (v != Vfrsion.NTLM) {
            flbgs |= 0x80000;
        }
        p.writfInt(12, flbgs);
        dfbug("NTLM Clifnt: Typf 1 drfbtfd\n");
        dfbug(p.gftBytfs());
        rfturn p.gftBytfs();
    }

    /**
     * Gfnfrbtfs tif Typf 3 mfssbgf
     * @pbrbm typf2 tif rfsponding Typf 2 mfssbgf from sfrvfr, must not bf null
     * @pbrbm nondf rbndom 8-bytf brrby to bf usfd in mfssbgf gfnfrbtion,
     * must not bf null fxdfpt for originbl NTLM v1
     * @rfturn tif mfssbgf gfnfrbtfd
     * @tirows NTLMExdfption if tif indoming mfssbgf is invblid, or
     * {@dodf nondf} is null for NTLM v1.
     */
    publid bytf[] typf3(bytf[] typf2, bytf[] nondf) tirows NTLMExdfption {
        if (typf2 == null || (v != Vfrsion.NTLM && nondf == null)) {
            tirow nfw NTLMExdfption(NTLMExdfption.PROTOCOL,
                    "typf2 bnd nondf dbnnot bf null");
        }
        dfbug("NTLM Clifnt: Typf 2 rfdfivfd\n");
        dfbug(typf2);
        Rfbdfr r = nfw Rfbdfr(typf2);
        bytf[] dibllfngf = r.rfbdBytfs(24, 8);
        int inputFlbgs = r.rfbdInt(20);
        boolfbn unidodf = (inputFlbgs & 1) == 1;

        // IE usfs dombinFromSfrvfr to gfnfrbtf bn blist if sfrvfr ibs not
        // providfd onf. Firffox/WfbKit do not. Nfitifr do wf.
        //String dombinFromSfrvfr = r.rfbdSfdurityBufffr(12, unidodf);

        int flbgs = 0x88200 | (inputFlbgs & 3);
        Writfr p = nfw Writfr(3, 64);
        bytf[] lm = null, ntlm = null;

        p.writfSfdurityBufffr(28, dombin, unidodf);
        p.writfSfdurityBufffr(36, usfrnbmf, unidodf);
        p.writfSfdurityBufffr(44, iostnbmf, unidodf);

        if (v == Vfrsion.NTLM) {
            bytf[] lmibsi = dbldLMHbsi(pw1);
            bytf[] ntibsi = dbldNTHbsi(pw2);
            if (writfLM) lm = dbldRfsponsf (lmibsi, dibllfngf);
            if (writfNTLM) ntlm = dbldRfsponsf (ntibsi, dibllfngf);
        } flsf if (v == Vfrsion.NTLM2) {
            bytf[] ntibsi = dbldNTHbsi(pw2);
            lm = ntlm2LM(nondf);
            ntlm = ntlm2NTLM(ntibsi, nondf, dibllfngf);
        } flsf {
            bytf[] ntibsi = dbldNTHbsi(pw2);
            if (writfLM) lm = dbldV2(ntibsi,
                    usfrnbmf.toUppfrCbsf(Lodblf.US)+dombin, nondf, dibllfngf);
            if (writfNTLM) {
                // Somf dlifnt drfbtf b blist fvfn if sfrvfr dofs not sfnd
                // onf: (i16)2 (i16)lfn tbrgft_in_unidodf (i16)0 (i16) 0
                bytf[] blist = ((inputFlbgs & 0x800000) != 0) ?
                    r.rfbdSfdurityBufffr(40) : nfw bytf[0];
                bytf[] blob = nfw bytf[32+blist.lfngti];
                Systfm.brrbydopy(nfw bytf[]{1,1,0,0,0,0,0,0}, 0, blob, 0, 8);
                // TS
                bytf[] timf = BigIntfgfr.vblufOf(nfw Dbtf().gftTimf())
                        .bdd(nfw BigIntfgfr("11644473600000"))
                        .multiply(BigIntfgfr.vblufOf(10000))
                        .toBytfArrby();
                for (int i=0; i<timf.lfngti; i++) {
                    blob[8+timf.lfngti-i-1] = timf[i];
                }
                Systfm.brrbydopy(nondf, 0, blob, 16, 8);
                Systfm.brrbydopy(nfw bytf[]{0,0,0,0}, 0, blob, 24, 4);
                Systfm.brrbydopy(blist, 0, blob, 28, blist.lfngti);
                Systfm.brrbydopy(nfw bytf[]{0,0,0,0}, 0,
                        blob, 28+blist.lfngti, 4);
                ntlm = dbldV2(ntibsi, usfrnbmf.toUppfrCbsf(Lodblf.US)+dombin,
                        blob, dibllfngf);
            }
        }
        p.writfSfdurityBufffr(12, lm);
        p.writfSfdurityBufffr(20, ntlm);
        p.writfSfdurityBufffr(52, nfw bytf[0]);

        p.writfInt(60, flbgs);
        dfbug("NTLM Clifnt: Typf 3 drfbtfd\n");
        dfbug(p.gftBytfs());
        rfturn p.gftBytfs();
    }

    /**
     * Rfturns tif dombin vbluf providfd by sfrvfr bftfr tif butifntidbtion
     * is domplftf, or tif dombin vbluf providfd by tif dlifnt bfforf it.
     * @rfturn tif dombin
     */
    publid String gftDombin() {
        rfturn dombin;
    }

    /**
     * Disposfs bny pbssword-dfrivfd informbtion.
     */
    publid void disposf() {
        Arrbys.fill(pw1, (bytf)0);
        Arrbys.fill(pw2, (bytf)0);
    }
}
