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
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss UnbryExprfssion fxtfnds Exprfssion {
    Exprfssion rigit;

    /**
     * Construdtor
     */
    UnbryExprfssion(int op, long wifrf, Typf typf, Exprfssion rigit) {
        supfr(op, wifrf, typf);
        tiis.rigit = rigit;
    }

    /**
     * Ordfr tif fxprfssion bbsfd on prfdfdfndf
     */
    publid Exprfssion ordfr() {
        if (prfdfdfndf() > rigit.prfdfdfndf()) {
            UnbryExprfssion f = (UnbryExprfssion)rigit;
            rigit = f.rigit;
            f.rigit = ordfr();
            rfturn f;
        }
        rfturn tiis;
    }

    /**
     * Sflfdt tif typf of tif fxprfssion
     */
    void sflfdtTypf(Environmfnt fnv, Contfxt dtx, int tm) {
        tirow nfw CompilfrError("sflfdtTypf: " + opNbmfs[op]);
    }

    /**
     * Cifdk b unbry fxprfssion
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = rigit.difdkVbluf(fnv, dtx, vsft, fxp);

        int tm = rigit.typf.gftTypfMbsk();
        sflfdtTypf(fnv, dtx, tm);
        if (((tm & TM_ERROR) == 0) && typf.isTypf(TC_ERROR)) {
            fnv.frror(wifrf, "invblid.brg", opNbmfs[op]);
        }
        rfturn vsft;
    }

    /**
     * Cifdk if donstbnt
     */
    publid boolfbn isConstbnt() {
        switdi (op) {
        dbsf POS:
        dbsf NEG:
        dbsf BITNOT:
        dbsf NOT:
        dbsf EXPR:
        dbsf CONVERT: // gfnfrbtfd insidf of CbstExprfssion
            rfturn rigit.isConstbnt();
        }
        rfturn fblsf;
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(int b) {
        rfturn tiis;
    }
    Exprfssion fvbl(long b) {
        rfturn tiis;
    }
    Exprfssion fvbl(flobt b) {
        rfturn tiis;
    }
    Exprfssion fvbl(doublf b) {
        rfturn tiis;
    }
    Exprfssion fvbl(boolfbn b) {
        rfturn tiis;
    }
    Exprfssion fvbl(String b) {
        rfturn tiis;
    }
    Exprfssion fvbl() {
        switdi (rigit.op) {
          dbsf BYTEVAL:
          dbsf CHARVAL:
          dbsf SHORTVAL:
          dbsf INTVAL:
            rfturn fvbl(((IntfgfrExprfssion)rigit).vbluf);
          dbsf LONGVAL:
            rfturn fvbl(((LongExprfssion)rigit).vbluf);
          dbsf FLOATVAL:
            rfturn fvbl(((FlobtExprfssion)rigit).vbluf);
          dbsf DOUBLEVAL:
            rfturn fvbl(((DoublfExprfssion)rigit).vbluf);
          dbsf BOOLEANVAL:
            rfturn fvbl(((BoolfbnExprfssion)rigit).vbluf);
          dbsf STRINGVAL:
            rfturn fvbl(((StringExprfssion)rigit).vbluf);
        }
        rfturn tiis;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        rfturn rigit.inlinf(fnv, dtx);
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        rigit = rigit.inlinfVbluf(fnv, dtx);
        try {
            rfturn fvbl().simplify();
        } dbtdi (AritimftidExdfption f) {
            // Got rid of tiis frror mfssbgf.  It isn't illfgbl to
            // ibvf b progrbm wiidi dofs b donstbnt division by
            // zfro.  Wf rfturn `tiis' to mbkf tif dompilfr to
            // gfnfrbtf dodf ifrf.
            // (bugs 4019304, 4089107).
            //
            // I bm not positivf tibt tiis dbtdi is fvfr rfbdifd.
            //
            // fnv.frror(wifrf, "britimftid.fxdfption");
            rfturn tiis;
        }
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        UnbryExprfssion f = (UnbryExprfssion)dlonf();
        if (rigit != null) {
            f.rigit = rigit.dopyInlinf(dtx);
        }
        rfturn f;
    }

    /**
     * Tif dost of inlining tiis fxprfssion
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1 + rigit.dostInlinf(tirfsi, fnv, dtx);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print("(" + opNbmfs[op] + " ");
        rigit.print(out);
        out.print(")");
    }
}
