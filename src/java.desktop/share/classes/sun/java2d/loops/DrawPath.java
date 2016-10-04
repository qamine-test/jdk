/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import jbvb.bwt.gfom.Pbti2D;

/**
 *   DrbwPbti
 *   1. drbw singlf-widti linf pbti onto dfstinbtion surfbdf
 *   2. must bddfpt output brfb [x, y, dx, dy]
 *      from witiin tif surfbdf dfsdription dbtb for dlip rfdt
 */
publid dlbss DrbwPbti fxtfnds GrbpiidsPrimitivf {

    publid finbl stbtid String mftiodSignbturf =
        "DrbwPbti(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid DrbwPbti lodbtf(SurfbdfTypf srdtypf,
                                  CompositfTypf domptypf,
                                  SurfbdfTypf dsttypf)
    {
        rfturn (DrbwPbti)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd DrbwPbti(SurfbdfTypf srdtypf,
                       CompositfTypf domptypf,
                       SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    publid DrbwPbti(long pNbtivfPrim,
                    SurfbdfTypf srdtypf,
                    CompositfTypf domptypf,
                    SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }


    /**
     *   All DrbwPbti implfmfntors must ibvf tiis invokfr mftiod
     */
    publid nbtivf void DrbwPbti(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                int trbnsX, int trbnsY,
                                Pbti2D.Flobt p2df);

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        tirow nfw IntfrnblError("DrbwPbti not implfmfntfd for "+
                                srdtypf+" witi "+domptypf);
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfDrbwPbti(tiis);
    }

    privbtf stbtid dlbss TrbdfDrbwPbti fxtfnds DrbwPbti {
        DrbwPbti tbrgft;

        publid TrbdfDrbwPbti(DrbwPbti tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void DrbwPbti(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                             int trbnsX, int trbnsY,
                             Pbti2D.Flobt p2df)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.DrbwPbti(sg2d, sDbtb, trbnsX, trbnsY, p2df);
        }
    }
}
