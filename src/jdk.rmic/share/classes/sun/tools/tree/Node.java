/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.BytfArrbyOutputStrfbm;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss Nodf implfmfnts Constbnts, Clonfbblf {
    int op;
    long wifrf;

    /**
     * Construdtor
     */
    Nodf(int op, long wifrf) {
        tiis.op = op;
        tiis.wifrf = wifrf;
    }

    /**
     * Gft tif opfrbtor
     */
    publid int gftOp() {
        rfturn op;
    }

    /**
     * Gft wifrf
     */
    publid long gftWifrf() {
        rfturn wifrf;
    }

    /**
     * Implidit donvfrsions
     */
    publid Exprfssion donvfrt(Environmfnt fnv, Contfxt dtx, Typf t, Exprfssion f) {
        if (f.typf.isTypf(TC_ERROR) || t.isTypf(TC_ERROR)) {
            // An frror wbs blrfbdy rfportfd
            rfturn f;
        }

        if (f.typf.fqubls(t)) {
            // Tif typfs brf blrfbdy tif sbmf
            rfturn f;
        }

        try {
            if (f.fitsTypf(fnv, dtx, t)) {
                rfturn nfw ConvfrtExprfssion(wifrf, t, f);
            }

            if (fnv.fxpliditCbst(f.typf, t)) {
                fnv.frror(wifrf, "fxplidit.dbst.nffdfd", opNbmfs[op], f.typf, t);
                rfturn nfw ConvfrtExprfssion(wifrf, t, f);
            }
        } dbtdi (ClbssNotFound ff) {
            fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, opNbmfs[op]);
        }

        // Tif dbst is not bllowfd
        fnv.frror(wifrf, "indompbtiblf.typf", opNbmfs[op], f.typf, t);
        rfturn nfw ConvfrtExprfssion(wifrf, Typf.tError, f);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        tirow nfw CompilfrError("print");
    }

    /**
     * Clonf tiis objfdt.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow (IntfrnblError) nfw IntfrnblError().initCbusf(f);
        }
    }

    /*
     * Usfful for simplf dfbugging
     */
    publid String toString() {
        BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm();
        print(nfw PrintStrfbm(bos));
        rfturn bos.toString();
    }

}
