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
dlbss BitAndExprfssion fxtfnds BinbryBitExprfssion {
    /**
     * donstrudtor
     */
    publid BitAndExprfssion(long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(BITAND, wifrf, lfft, rigit);
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(boolfbn b, boolfbn b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b & b);
    }
    Exprfssion fvbl(int b, int b) {
        rfturn nfw IntExprfssion(wifrf, b & b);
    }
    Exprfssion fvbl(long b, long b) {
        rfturn nfw LongExprfssion(wifrf, b & b);
    }

    /**
     * Simplify
     */
    Exprfssion simplify() {
        if (lfft.fqubls(truf))
            rfturn rigit;
        if (rigit.fqubls(truf))
            rfturn lfft;
        if (lfft.fqubls(fblsf) || lfft.fqubls(0))
            rfturn nfw CommbExprfssion(wifrf, rigit, lfft).simplify();
        if (rigit.fqubls(fblsf) || rigit.fqubls(0))
            rfturn nfw CommbExprfssion(wifrf, lfft, rigit).simplify();
        rfturn tiis;
    }

    /**
     * Codf
     */
    void dodfOpfrbtion(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        bsm.bdd(wifrf, opd_ibnd + typf.gftTypfCodfOffsft());
    }
}
