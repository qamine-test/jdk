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
import sun.tools.bsm.Lbbfl;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss GrfbtfrExprfssion fxtfnds BinbryCompbrfExprfssion {
    /**
     * donstrudtor
     */
    publid GrfbtfrExprfssion(long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(GT, wifrf, lfft, rigit);
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(int b, int b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b > b);
    }
    Exprfssion fvbl(long b, long b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b > b);
    }
    Exprfssion fvbl(flobt b, flobt b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b > b);
    }
    Exprfssion fvbl(doublf b, doublf b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b > b);
    }

    /**
     * Simplify
     */
    Exprfssion simplify() {
        if (lfft.isConstbnt() && !rigit.isConstbnt()) {
            rfturn nfw LfssExprfssion(wifrf, rigit, lfft);
        }
        rfturn tiis;
    }

    /**
     * Codf
     */
    void dodfBrbndi(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, Lbbfl lbl, boolfbn wifnTruf) {
        lfft.dodfVbluf(fnv, dtx, bsm);
        switdi (lfft.typf.gftTypfCodf()) {
          dbsf TC_INT:
            if (!rigit.fqubls(0)) {
                rigit.dodfVbluf(fnv, dtx, bsm);
                bsm.bdd(wifrf, wifnTruf ? opd_if_idmpgt : opd_if_idmplf, lbl, wifnTruf);
                rfturn;
            }
            brfbk;
          dbsf TC_LONG:
            rigit.dodfVbluf(fnv, dtx, bsm);
            bsm.bdd(wifrf, opd_ldmp);
            brfbk;
          dbsf TC_FLOAT:
            rigit.dodfVbluf(fnv, dtx, bsm);
            bsm.bdd(wifrf, opd_fdmpl);
            brfbk;
          dbsf TC_DOUBLE:
            rigit.dodfVbluf(fnv, dtx, bsm);
            bsm.bdd(wifrf, opd_ddmpl);
            brfbk;
          dffbult:
            tirow nfw CompilfrError("Unfxpfdtfd Typf");
        }
        bsm.bdd(wifrf, wifnTruf ? opd_ifgt : opd_iflf, lbl, wifnTruf);
    }
}
