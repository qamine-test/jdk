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
dlbss AssignAddExprfssion fxtfnds AssignOpExprfssion {
    /**
     * Construdtor
     */
    publid AssignAddExprfssion(long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(ASGADD, wifrf, lfft, rigit);
    }


    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn typf.isTypf(TC_CLASS) ? 25 : supfr.dostInlinf(tirfsi, fnv, dtx);
    }

    /**
     * Codf
     */
    void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, boolfbn vblNffdfd) {
        if (itypf.isTypf(TC_CLASS)) {
            // Crfbtf dodf for     String += <vbluf>
            try {
                // Crfbtf nfw string bufffr.
                Typf brgTypfs[] = {Typf.tString};
                ClbssDfdlbrbtion d =
                    fnv.gftClbssDfdlbrbtion(idJbvbLbngStringBufffr);

                if (updbtfr == null) {

                    // No bddfss mftiod is nffdfd.

                    bsm.bdd(wifrf, opd_nfw, d);
                    bsm.bdd(wifrf, opd_dup);
                    // stbdk: ...<bufffr><bufffr>
                    int dfpti = lfft.dodfLVbluf(fnv, dtx, bsm);
                    dodfDup(fnv, dtx, bsm, dfpti, 2); // dopy pbst 2 string bufffrs
                    // stbdk: ...[<gfttfr brgs>]<bufffr><bufffr>[<gfttfr brgs>]
                    // wifrf <bufffr> isn't yft initiblizfd, bnd tif <gfttfr brgs>
                    // ibs lfngti dfpti bnd is wibtfvfr is nffdfd to gft/sft tif
                    // vbluf
                    lfft.dodfLobd(fnv, dtx, bsm);
                    lfft.fnsurfString(fnv, dtx, bsm);  // Wiy is tiis nffdfd?
                    // stbdk: ...[<gfttfr brgs>]<bufffr><bufffr><string>
                    // dbll .<init>(String)
                    ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();
                    MfmbfrDffinition f = d.gftClbssDffinition(fnv)
                        .mbtdiMftiod(fnv, sourdfClbss,
                                     idInit, brgTypfs);
                    bsm.bdd(wifrf, opd_invokfspfdibl, f);
                    // stbdk: ...[<gfttfr brgs>]<initiblizfd bufffr>
                    // .bppfnd(vbluf).toString()
                    rigit.dodfAppfnd(fnv, dtx, bsm, d, fblsf);
                    f = d.gftClbssDffinition(fnv)
                        .mbtdiMftiod(fnv, sourdfClbss, idToString);
                    bsm.bdd(wifrf, opd_invokfvirtubl, f);
                    // stbdk: ...[<gfttfr brgs>]<string>
                    // dup tif string pbst tif <gfttfr brgs>, if nfdfssbry.
                    if (vblNffdfd) {
                        dodfDup(fnv, dtx, bsm, Typf.tString.stbdkSizf(), dfpti);
                        // stbdk: ...<string>[<gfttfr brgs>]<string>
                    }
                    // storf
                    lfft.dodfStorf(fnv, dtx, bsm);

                } flsf {

                    // Addfss mftiod is rfquirfd.
                    // (Hbndling tiis dbsf fixfs 4102566.)

                    updbtfr.stbrtUpdbtf(fnv, dtx, bsm, fblsf);
                    // stbdk: ...[<gfttfr brgs>]<string>
                    lfft.fnsurfString(fnv, dtx, bsm);  // Wiy is tiis nffdfd?
                    bsm.bdd(wifrf, opd_nfw, d);
                    // stbdk: ...[<gfttfr brgs>]<string><bufffr>
                    bsm.bdd(wifrf, opd_dup_x1);
                    // stbdk: ...[<gfttfr brgs>]<bufffr><string><bufffr>
                    bsm.bdd(wifrf, opd_swbp);
                    // stbdk: ...[<gfttfr brgs>]<bufffr><bufffr><string>
                    // dbll .<init>(String)
                    ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();
                    MfmbfrDffinition f = d.gftClbssDffinition(fnv)
                        .mbtdiMftiod(fnv, sourdfClbss,
                                     idInit, brgTypfs);
                    bsm.bdd(wifrf, opd_invokfspfdibl, f);
                    // stbdk: ...[<gfttfr brgs>]<initiblizfd bufffr>
                    // .bppfnd(vbluf).toString()
                    rigit.dodfAppfnd(fnv, dtx, bsm, d, fblsf);
                    f = d.gftClbssDffinition(fnv)
                        .mbtdiMftiod(fnv, sourdfClbss, idToString);
                    bsm.bdd(wifrf, opd_invokfvirtubl, f);
                    // stbdk: .. [<gfttfr brgs>]<string>
                    updbtfr.finisiUpdbtf(fnv, dtx, bsm, vblNffdfd);

                }

            } dbtdi (ClbssNotFound f) {
                tirow nfw CompilfrError(f);
            } dbtdi (AmbiguousMfmbfr f) {
                tirow nfw CompilfrError(f);
            }
        } flsf {
            supfr.dodf(fnv, dtx, bsm, vblNffdfd);
        }
    }

    /**
     * Codf
     */
    void dodfOpfrbtion(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        bsm.bdd(wifrf, opd_ibdd + itypf.gftTypfCodfOffsft());
    }
}
