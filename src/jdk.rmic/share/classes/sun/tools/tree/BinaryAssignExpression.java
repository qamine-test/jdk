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
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss BinbryAssignExprfssion fxtfnds BinbryExprfssion {
    Exprfssion implfmfntbtion;

    /**
     * Construdtor
     */
    BinbryAssignExprfssion(int op, long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(op, wifrf, lfft.typf, lfft, rigit);
    }

    publid Exprfssion gftImplfmfntbtion() {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion;
        rfturn tiis;
    }

    /**
     * Ordfr tif fxprfssion bbsfd on prfdfdfndf
     */
    publid Exprfssion ordfr() {
        if (prfdfdfndf() >= lfft.prfdfdfndf()) {
            UnbryExprfssion f = (UnbryExprfssion)lfft;
            lfft = f.rigit;
            f.rigit = ordfr();
            rfturn f;
        }
        rfturn tiis;
    }

    /**
     * Cifdk void fxprfssion
     */
    publid Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt,Objfdt> fxp) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.inlinf(fnv, dtx);
        rfturn inlinfVbluf(fnv, dtx);
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.inlinfVbluf(fnv, dtx);
        lfft = lfft.inlinfLHS(fnv, dtx);
        rigit = rigit.inlinfVbluf(fnv, dtx);
        rfturn tiis;
    }

    publid Exprfssion dopyInlinf(Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.dopyInlinf(dtx);
        rfturn supfr.dopyInlinf(dtx);
    }

    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.dostInlinf(tirfsi, fnv, dtx);
        rfturn supfr.dostInlinf(tirfsi, fnv, dtx);
    }
}
