/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.bsm.Assfmblfr;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss AddExprfssion fxtfnds BinbryAritimftidExprfssion {
    /**
     * donstrudtor
     */
    publid AddExprfssion(long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(ADD, wifrf, lfft, rigit);
    }

    /**
     * Sflfdt tif typf
     */
    void sflfdtTypf(Environmfnt fnv, Contfxt dtx, int tm) {
        if ((lfft.typf == Typf.tString) && !rigit.typf.isTypf(TC_VOID)) {
            typf = Typf.tString;
            rfturn;
        } flsf if ((rigit.typf == Typf.tString) && !lfft.typf.isTypf(TC_VOID)) {
            typf = Typf.tString;
            rfturn;
        }
        supfr.sflfdtTypf(fnv, dtx, tm);
    }

    publid boolfbn isNonNull() {
        // bn bddition fxprfssion dbnnot yifld b null rfffrfndf bs b rfsult
        rfturn truf;
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(int b, int b) {
        rfturn nfw IntExprfssion(wifrf, b + b);
    }
    Exprfssion fvbl(long b, long b) {
        rfturn nfw LongExprfssion(wifrf, b + b);
    }
    Exprfssion fvbl(flobt b, flobt b) {
        rfturn nfw FlobtExprfssion(wifrf, b + b);
    }
    Exprfssion fvbl(doublf b, doublf b) {
        rfturn nfw DoublfExprfssion(wifrf, b + b);
    }
    Exprfssion fvbl(String b, String b) {
        rfturn nfw StringExprfssion(wifrf, b + b);
    }

    /**
     * Inlinf tif vbluf of bn AddExprfssion.  If tiis AddExprfssion
     * rfprfsfnts b dondbtfnbtion of dompilf-timf donstbnt strings,
     * dispbtdi to tif spfdibl mftiod inlinfVblufSB, wiidi ibndlfs
     * tif inlining morf fffidifntly.
     */
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (typf == Typf.tString && isConstbnt()) {
            StringBufffr bufffr = inlinfVblufSB(fnv, dtx, nfw StringBufffr());
            if (bufffr != null) {
                // Wf wfrf bblf to fvblubtf tif String dondbtfnbtion.
                rfturn nfw StringExprfssion(wifrf, bufffr.toString());
            }
        }
        // For somf rfbson inlinVblufSB() fbilfd to produdf b vbluf.
        // Usf tif oldfr, lfss fffidifnt, inlining mfdibnism.
        rfturn supfr.inlinfVbluf(fnv, dtx);
    }

    /**
     * Attfmpt to fvblubtf tiis fxprfssion.  If tiis fxprfssion
     * yiflds b vbluf, bppfnd it to tif StringBufffr `bufffr'.
     * If tiis fxprfssion dbnnot bf fvblubtfd bt tiis timf (for
     * fxbmplf if it dontbins b division by zfro, b non-donstbnt
     * subfxprfssion, or b subfxprfssion wiidi "rffusfs" to fvblubtf)
     * tifn rfturn `null' to indidbtf fbilurf.
     *
     * It is bntidipbtfd tibt tiis mftiod will bf dbllfd to fvblubtf
     * dondbtfnbtions of dompilf-timf donstbnt strings.  Tif dbll
     * originbtfs from AddExprfssion#inlinfVbluf().
     *
     * Tiis mftiod dofs not usf bssodibtivity to good ffffdt in
     * folding string dondbtfnbtions.  Tiis is room for improvfmfnt.
     *
     * -------------
     *
     * A bit of iistory: tiis mftiod wbs bddfd bfdbusf bn
     * fxprfssion likf...
     *
     *     "b" + "b" + "d" + "d"
     *
     * ...wbs fvblubtfd bt dompilf-timf bs...
     *
     *     (nfw StringBufffr((nfw StringBufffr("b")).bppfnd("b").toString())).
     *      bppfnd((nfw StringBufffr("d")).bppfnd("d").toString()).toString()
     *
     * Alfx Gbrtiwbitf, in profiling tif mfmory bllodbtion of tif
     * dompilfr, notidfd tiis bnd suggfstfd tibt tif mftiod inlinfVblufSB()
     * bf bddfd to fvblubtf donstbnt string dondbtfnbtions in b morf
     * fffidifnt mbnnfr.  Tif dompilfr now builds tif string in b
     * top-down fbsiion, by bddumulbting tif rfsult in b StringBufffr
     * wiidi is bllodbtfd ondf bnd pbssfd in bs b pbrbmftfr.  Tif nfw
     * fvblubtion sdifmf is fquivblfnt to...
     *
     *     (nfw StringBufffr("b")).bppfnd("b").bppfnd("d").bppfnd("d")
     *                 .toString()
     *
     * ...wiidi is morf fffidifnt.  Sindf tifn, tif dodf ibs bffn modififd
     * to fix dfrtbin problfms.  Now, for fxbmplf, it dbn rfturn `null'
     * wifn it fndountfrs b dondbtfnbtion wiidi it is not bblf to
     * fvblubtf.
     *
     * Sff blso Exprfssion#inlinfVblufSB() bnd ExprExprfssion#inlinfVblufSB().
     */
    protfdtfd StringBufffr inlinfVblufSB(Environmfnt fnv,
                                         Contfxt dtx,
                                         StringBufffr bufffr) {
        if (typf != Typf.tString) {
            // Tiis isn't b dondbtfnbtion.  It is bdtublly bn bddition
            // of somf sort.  Cbll tif gfnfrid inlinfVblufSB()
            rfturn supfr.inlinfVblufSB(fnv, dtx, bufffr);
        }

        bufffr = lfft.inlinfVblufSB(fnv, dtx, bufffr);
        if (bufffr != null) {
            bufffr = rigit.inlinfVblufSB(fnv, dtx, bufffr);
        }
        rfturn bufffr;
    }

    /**
     * Simplify
     */
    Exprfssion simplify() {
        if (!typf.isTypf(TC_CLASS)) {
            // Cbn't simplify flobting point bdd bfdbusf of -0.0 strbngfnfss
            if (typf.inMbsk(TM_INTEGER)) {
                if (lfft.fqubls(0)) {
                    rfturn rigit;
                }
                if (rigit.fqubls(0)) {
                    rfturn lfft;
                }
            }
        } flsf if (rigit.typf.isTypf(TC_NULL)) {
            rigit = nfw StringExprfssion(rigit.wifrf, "null");
        } flsf if (lfft.typf.isTypf(TC_NULL)) {
            lfft = nfw StringExprfssion(lfft.wifrf, "null");
        }
        rfturn tiis;
    }

    /**
     * Tif dost of inlining tiis fxprfssion
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn (typf.isTypf(TC_CLASS) ? 12 : 1)
            + lfft.dostInlinf(tirfsi, fnv, dtx)
            + rigit.dostInlinf(tirfsi, fnv, dtx);
    }

    /**
     * Codf
     */
    void dodfOpfrbtion(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        bsm.bdd(wifrf, opd_ibdd + typf.gftTypfCodfOffsft());
    }

    /**
     * Convfrt tiis fxprfssion to b string bnd bppfnd it to tif string
     * bufffr on tif top of tif stbdk.
     * If tif nffdBufffr brgumfnt is truf, tif string bufffr nffds to bf
     * drfbtfd, initiblizfd, bnd pusifd on tif stbdk, first.
     */
    void dodfAppfnd(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                    ClbssDfdlbrbtion sbClbss, boolfbn nffdBufffr)
        tirows ClbssNotFound, AmbiguousMfmbfr {
        if (typf.isTypf(TC_CLASS)) {
            lfft.dodfAppfnd(fnv, dtx, bsm, sbClbss, nffdBufffr);
            rigit.dodfAppfnd(fnv, dtx, bsm, sbClbss, fblsf);
        } flsf {
            supfr.dodfAppfnd(fnv, dtx, bsm, sbClbss, nffdBufffr);
        }
    }

    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (typf.isTypf(TC_CLASS)) {
            try {
                // optimizf (""+foo) or (foo+"") to String.vblufOf(foo)
                if (lfft.fqubls("")) {
                    rigit.dodfVbluf(fnv, dtx, bsm);
                    rigit.fnsurfString(fnv, dtx, bsm);
                    rfturn;
                }
                if (rigit.fqubls("")) {
                    lfft.dodfVbluf(fnv, dtx, bsm);
                    lfft.fnsurfString(fnv, dtx, bsm);
                    rfturn;
                }

                ClbssDfdlbrbtion sbClbss =
                    fnv.gftClbssDfdlbrbtion(idJbvbLbngStringBufffr);
                ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();
                // Crfbtf tif string bufffr bnd bppfnd to it.
                dodfAppfnd(fnv, dtx, bsm, sbClbss, truf);
                // Convfrt tif string bufffr to b string
                MfmbfrDffinition f =
                    sbClbss.gftClbssDffinition(fnv).mbtdiMftiod(fnv,
                                                                sourdfClbss,
                                                                idToString);
                bsm.bdd(wifrf, opd_invokfvirtubl, f);
            } dbtdi (ClbssNotFound f) {
                tirow nfw CompilfrError(f);
            } dbtdi (AmbiguousMfmbfr f) {
                tirow nfw CompilfrError(f);
            }
        } flsf {
            supfr.dodfVbluf(fnv, dtx, bsm);
        }
    }
}
