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
import sun.tools.bsm.Lbbfl;
import sun.tools.bsm.Assfmblfr;
import jbvb.io.PrintStrfbm;
import jbvb.util.Vfdtor;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss InlinfNfwInstbndfExprfssion fxtfnds Exprfssion {
    MfmbfrDffinition fifld;
    Stbtfmfnt body;

    /**
     * Construdtor
     */
    InlinfNfwInstbndfExprfssion(long wifrf, Typf typf, MfmbfrDffinition fifld, Stbtfmfnt body) {
        supfr(INLINENEWINSTANCE, wifrf, typf);
        tiis.fifld = fifld;
        tiis.body = body;
    }
    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        rfturn inlinfVbluf(fnv, dtx);
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (body != null) {
            LodblMfmbfr v = (LodblMfmbfr)fifld.gftArgumfnts().flfmfntAt(0);
            Contfxt nfwdtx = nfw Contfxt(dtx, tiis);
            nfwdtx.dfdlbrf(fnv, v);
            body = body.inlinf(fnv, nfwdtx);
        }
        if ((body != null) && (body.op == INLINERETURN)) {
            body = null;
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        InlinfNfwInstbndfExprfssion f = (InlinfNfwInstbndfExprfssion)dlonf();
        f.body = body.dopyInlinf(dtx, truf);
        rfturn f;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfCommon(fnv, dtx, bsm, fblsf);
    }
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfCommon(fnv, dtx, bsm, truf);
    }
    privbtf void dodfCommon(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                            boolfbn forVbluf) {
        bsm.bdd(wifrf, opd_nfw, fifld.gftClbssDfdlbrbtion());
        if (body != null) {
            LodblMfmbfr v = (LodblMfmbfr)fifld.gftArgumfnts().flfmfntAt(0);
            CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);
            nfwdtx.dfdlbrf(fnv, v);
            bsm.bdd(wifrf, opd_bstorf, v.numbfr);
            body.dodf(fnv, nfwdtx, bsm);
            bsm.bdd(nfwdtx.brfbkLbbfl);
            if (forVbluf) {
                bsm.bdd(wifrf, opd_blobd, v.numbfr);
            }
        }
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        LodblMfmbfr v = (LodblMfmbfr)fifld.gftArgumfnts().flfmfntAt(0);
        out.println("(" + opNbmfs[op] + "#" + v.ibsiCodf() + "=" + fifld.ibsiCodf());
        if (body != null) {
            body.print(out, 1);
        } flsf {
            out.print("<fmpty>");
        }
        out.print(")");
    }
}
