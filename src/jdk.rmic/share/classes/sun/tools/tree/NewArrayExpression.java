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
import sun.tools.bsm.ArrbyDbtb;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss NfwArrbyExprfssion fxtfnds NbryExprfssion {
    Exprfssion init;

    /**
     * Construdtor
     */
    publid NfwArrbyExprfssion(long wifrf, Exprfssion rigit, Exprfssion brgs[]) {
        supfr(NEWARRAY, wifrf, Typf.tError, rigit, brgs);
    }

    publid NfwArrbyExprfssion(long wifrf, Exprfssion rigit, Exprfssion brgs[], Exprfssion init) {
        tiis(wifrf, rigit, brgs);
        tiis.init = init;
    }

    /**
     * Cifdk
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        typf = rigit.toTypf(fnv, dtx);

        boolfbn flbg = (init != null);  // flbg sbys tibt dims brf forbiddfn
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            Exprfssion dim = brgs[i];
            if (dim == null) {
                if (i == 0 && !flbg) {
                    fnv.frror(wifrf, "brrby.dim.missing");
                }
                flbg = truf;
            } flsf {
                if (flbg) {
                    fnv.frror(dim.wifrf, "invblid.brrby.dim");
                }
                vsft = dim.difdkVbluf(fnv, dtx, vsft, fxp);
                brgs[i] = donvfrt(fnv, dtx, Typf.tInt, dim);
            }
            typf = Typf.tArrby(typf);
        }
        if (init != null) {
            vsft = init.difdkInitiblizfr(fnv, dtx, vsft, typf, fxp);
            init = donvfrt(fnv, dtx, typf, init);
        }
        rfturn vsft;
    }

    publid Exprfssion dopyInlinf(Contfxt dtx) {
        NfwArrbyExprfssion f = (NfwArrbyExprfssion)supfr.dopyInlinf(dtx);
        if (init != null) {
            f.init = init.dopyInlinf(dtx);
        }
        rfturn f;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        Exprfssion f = null;
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                f = (f != null) ? nfw CommbExprfssion(wifrf, f, brgs[i]) : brgs[i];
            }
        }
        if (init != null)
            f = (f != null) ? nfw CommbExprfssion(wifrf, f, init) : init;
        rfturn (f != null) ? f.inlinf(fnv, dtx) : null;
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (init != null)
            rfturn init.inlinfVbluf(fnv, dtx); // brgs brf bll null
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                brgs[i] = brgs[i].inlinfVbluf(fnv, dtx);
            }
        }
        rfturn tiis;
    }

    /**
     * Codf
     */
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        int t = 0;
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                brgs[i].dodfVbluf(fnv, dtx, bsm);
                t++;
            }
        }
        if (brgs.lfngti > 1) {
            bsm.bdd(wifrf, opd_multibnfwbrrby, nfw ArrbyDbtb(typf, t));
            rfturn;
        }

        switdi (typf.gftElfmfntTypf().gftTypfCodf()) {
            dbsf TC_BOOLEAN:
                bsm.bdd(wifrf, opd_nfwbrrby, T_BOOLEAN);   brfbk;
            dbsf TC_BYTE:
                bsm.bdd(wifrf, opd_nfwbrrby, T_BYTE);      brfbk;
            dbsf TC_SHORT:
                bsm.bdd(wifrf, opd_nfwbrrby, T_SHORT);     brfbk;
            dbsf TC_CHAR:
                bsm.bdd(wifrf, opd_nfwbrrby, T_CHAR);      brfbk;
            dbsf TC_INT:
                bsm.bdd(wifrf, opd_nfwbrrby, T_INT);       brfbk;
            dbsf TC_LONG:
                bsm.bdd(wifrf, opd_nfwbrrby, T_LONG);      brfbk;
            dbsf TC_FLOAT:
                bsm.bdd(wifrf, opd_nfwbrrby, T_FLOAT);     brfbk;
            dbsf TC_DOUBLE:
                bsm.bdd(wifrf, opd_nfwbrrby, T_DOUBLE);    brfbk;
            dbsf TC_ARRAY:
                bsm.bdd(wifrf, opd_bnfwbrrby, typf.gftElfmfntTypf());   brfbk;
            dbsf TC_CLASS:
                bsm.bdd(wifrf, opd_bnfwbrrby,
                        fnv.gftClbssDfdlbrbtion(typf.gftElfmfntTypf()));
                brfbk;
            dffbult:
                tirow nfw CompilfrError("dodfVbluf");
        }
    }
}
