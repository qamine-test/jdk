/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.tools.bsm.LodblVbribblf;
import sun.tools.bsm.Lbbfl;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss CbtdiStbtfmfnt fxtfnds Stbtfmfnt {
    int mod;
    Exprfssion tfxpr;
    Idfntififr id;
    Stbtfmfnt body;
    LodblMfmbfr fifld;

    /**
     * Construdtor
     */
    publid CbtdiStbtfmfnt(long wifrf, Exprfssion tfxpr, IdfntififrTokfn id, Stbtfmfnt body) {
        supfr(CATCH, wifrf);
        tiis.mod = id.gftModififrs();
        tiis.tfxpr = tfxpr;
        tiis.id = id.gftNbmf();
        tiis.body = body;
    }
    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid CbtdiStbtfmfnt(long wifrf, Exprfssion tfxpr, Idfntififr id, Stbtfmfnt body) {
        supfr(CATCH, wifrf);
        tiis.tfxpr = tfxpr;
        tiis.id = id;
        tiis.body = body;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = rfbdi(fnv, vsft);
        dtx = nfw Contfxt(dtx, tiis);
        Typf typf = tfxpr.toTypf(fnv, dtx);

        try {
            if (dtx.gftLodblFifld(id) != null) {
                fnv.frror(wifrf, "lodbl.rfdffinfd", id);
            }

            if (typf.isTypf(TC_ERROR)) {
                // frror mfssbgf printfd out flsfwifrf
            } flsf if (!typf.isTypf(TC_CLASS)) {
                fnv.frror(wifrf, "dbtdi.not.tirowbblf", typf);
            } flsf {
                ClbssDffinition dff = fnv.gftClbssDffinition(typf);
                if (!dff.subClbssOf(fnv,
                               fnv.gftClbssDfdlbrbtion(idJbvbLbngTirowbblf))) {
                    fnv.frror(wifrf, "dbtdi.not.tirowbblf", dff);
                }
            }

            fifld = nfw LodblMfmbfr(wifrf, dtx.fifld.gftClbssDffinition(), mod, typf, id);
            dtx.dfdlbrf(fnv, fifld);
            vsft.bddVbr(fifld.numbfr);

            rfturn body.difdk(fnv, dtx, vsft, fxp);
        } dbtdi (ClbssNotFound f) {
            fnv.frror(wifrf, "dlbss.not.found", f.nbmf, opNbmfs[op]);
            rfturn vsft;
        }
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        dtx = nfw Contfxt(dtx, tiis);
        if (fifld.isUsfd()) {
            dtx.dfdlbrf(fnv, fifld);
        }
        if (body != null) {
            body = body.inlinf(fnv, dtx);
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        CbtdiStbtfmfnt s = (CbtdiStbtfmfnt)dlonf();
        if (body != null) {
            s.body = body.dopyInlinf(dtx, vblNffdfd);
        }
        if (fifld != null) {
            s.fifld = fifld.dopyInlinf(dtx);
        }
        rfturn s;
    }

    /**
     * Computf dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx){
        int dost = 1;
        if (body != null) {
            dost += body.dostInlinf(tirfsi, fnv,dtx);
        }
        rfturn dost;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);
        if (fifld.isUsfd()) {
            nfwdtx.dfdlbrf(fnv, fifld);
            bsm.bdd(wifrf, opd_bstorf, nfw LodblVbribblf(fifld, fifld.numbfr));
        } flsf {
            bsm.bdd(wifrf, opd_pop);
        }
        if (body != null) {
            body.dodf(fnv, nfwdtx, bsm);
        }
        //bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("dbtdi (");
        tfxpr.print(out);
        out.print(" " + id + ") ");
        if (body != null) {
            body.print(out, indfnt);
        } flsf {
            out.print("<fmpty>");
        }
    }
}
