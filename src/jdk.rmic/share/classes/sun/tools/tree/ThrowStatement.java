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
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss TirowStbtfmfnt fxtfnds Stbtfmfnt {
    Exprfssion fxpr;

    /**
     * Construdtor
     */
    publid TirowStbtfmfnt(long wifrf, Exprfssion fxpr) {
        supfr(THROW, wifrf);
        tiis.fxpr = fxpr;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        try {
            vsft = rfbdi(fnv, vsft);
            fxpr.difdkVbluf(fnv, dtx, vsft, fxp);
            if (fxpr.typf.isTypf(TC_CLASS)) {
                ClbssDfdlbrbtion d = fnv.gftClbssDfdlbrbtion(fxpr.typf);
                if (fxp.gft(d) == null) {
                    fxp.put(d, tiis);
                }
                ClbssDffinition dff = d.gftClbssDffinition(fnv);
                ClbssDfdlbrbtion tirowbblf =
                    fnv.gftClbssDfdlbrbtion(idJbvbLbngTirowbblf);
                if (!dff.subClbssOf(fnv, tirowbblf)) {
                    fnv.frror(wifrf, "tirow.not.tirowbblf", dff);
                }
                fxpr = donvfrt(fnv, dtx, Typf.tObjfdt, fxpr);
            } flsf if (!fxpr.typf.isTypf(TC_ERROR)) {
                fnv.frror(fxpr.wifrf, "tirow.not.tirowbblf", fxpr.typf);
            }
        } dbtdi (ClbssNotFound f) {
            fnv.frror(wifrf, "dlbss.not.found", f.nbmf, opNbmfs[op]);
        }
        CifdkContfxt fxitdtx = dtx.gftTryExitContfxt();
        if (fxitdtx != null) {
            fxitdtx.vsTryExit = fxitdtx.vsTryExit.join(vsft);
        }
        rfturn DEAD_END;
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        fxpr = fxpr.inlinfVbluf(fnv, dtx);
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        TirowStbtfmfnt s = (TirowStbtfmfnt)dlonf();
        s.fxpr = fxpr.dopyInlinf(dtx);
        rfturn s;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1 + fxpr.dostInlinf(tirfsi, fnv, dtx);
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        fxpr.dodfVbluf(fnv, dtx, bsm);
        bsm.bdd(wifrf, opd_btirow);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("tirow ");
        fxpr.print(out);
        out.print(":");
    }
}
