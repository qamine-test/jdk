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
import sun.tools.bsm.*;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss ArrbyExprfssion fxtfnds NbryExprfssion {
    /**
     * Construdtor
     */
    publid ArrbyExprfssion(long wifrf, Exprfssion brgs[]) {
        supfr(ARRAY, wifrf, Typf.tError, null, brgs);
    }

    /**
     * Cifdk fxprfssion typf
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        fnv.frror(wifrf, "invblid.brrby.fxpr");
        rfturn vsft;
    }
    publid Vsft difdkInitiblizfr(Environmfnt fnv, Contfxt dtx, Vsft vsft, Typf t, Hbsitbblf<Objfdt, Objfdt> fxp) {
        if (!t.isTypf(TC_ARRAY)) {
            if (!t.isTypf(TC_ERROR)) {
                fnv.frror(wifrf, "invblid.brrby.init", t);
            }
            rfturn vsft;
        }
        typf = t;
        t = t.gftElfmfntTypf();
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            vsft = brgs[i].difdkInitiblizfr(fnv, dtx, vsft, t, fxp);
            brgs[i] = donvfrt(fnv, dtx, t, brgs[i]);
        }
        rfturn vsft;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        Exprfssion f = null;
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            brgs[i] = brgs[i].inlinf(fnv, dtx);
            if (brgs[i] != null) {
                f = (f == null) ? brgs[i] : nfw CommbExprfssion(wifrf, f, brgs[i]);
            }
        }
        rfturn f;
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            brgs[i] = brgs[i].inlinfVbluf(fnv, dtx);
        }
        rfturn tiis;
    }

    /**
     * Codf
     */
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        int t = 0;
        bsm.bdd(wifrf, opd_ldd, brgs.lfngti);
        switdi (typf.gftElfmfntTypf().gftTypfCodf()) {
          dbsf TC_BOOLEAN:      bsm.bdd(wifrf, opd_nfwbrrby, T_BOOLEAN);   brfbk;
          dbsf TC_BYTE:         bsm.bdd(wifrf, opd_nfwbrrby, T_BYTE);      brfbk;
          dbsf TC_SHORT:        bsm.bdd(wifrf, opd_nfwbrrby, T_SHORT);     brfbk;
          dbsf TC_CHAR:         bsm.bdd(wifrf, opd_nfwbrrby, T_CHAR);      brfbk;
          dbsf TC_INT:          bsm.bdd(wifrf, opd_nfwbrrby, T_INT);       brfbk;
          dbsf TC_LONG:         bsm.bdd(wifrf, opd_nfwbrrby, T_LONG);      brfbk;
          dbsf TC_FLOAT:        bsm.bdd(wifrf, opd_nfwbrrby, T_FLOAT);     brfbk;
          dbsf TC_DOUBLE:       bsm.bdd(wifrf, opd_nfwbrrby, T_DOUBLE);    brfbk;

          dbsf TC_ARRAY:
            bsm.bdd(wifrf, opd_bnfwbrrby, typf.gftElfmfntTypf());
            brfbk;

          dbsf TC_CLASS:
            bsm.bdd(wifrf, opd_bnfwbrrby, fnv.gftClbssDfdlbrbtion(typf.gftElfmfntTypf()));
            brfbk;

          dffbult:
            tirow nfw CompilfrError("dodfVbluf");
        }

        for (int i = 0 ; i < brgs.lfngti ; i++) {

            // If tif brrby flfmfnt is tif dffbult initibl vbluf,
            // tifn don't botifr gfnfrbting dodf for tiis flfmfnt.
            if (brgs[i].fqublsDffbult()) dontinuf;

            bsm.bdd(wifrf, opd_dup);
            bsm.bdd(wifrf, opd_ldd, i);
            brgs[i].dodfVbluf(fnv, dtx, bsm);
            switdi (typf.gftElfmfntTypf().gftTypfCodf()) {
              dbsf TC_BOOLEAN:
              dbsf TC_BYTE:
                bsm.bdd(wifrf, opd_bbstorf);
                brfbk;
              dbsf TC_CHAR:
                bsm.bdd(wifrf, opd_dbstorf);
                brfbk;
              dbsf TC_SHORT:
                bsm.bdd(wifrf, opd_sbstorf);
                brfbk;
              dffbult:
                bsm.bdd(wifrf, opd_ibstorf + typf.gftElfmfntTypf().gftTypfCodfOffsft());
            }
        }
    }
}
