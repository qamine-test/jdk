/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * @butior Jim Grbibm
 */

pbdkbgf sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;

/**
 * FillPbrbllflogrbm
 * 1) fill tif brfb bftwffn tif 4 fdgfs of b pbrbllflogrbm
 *    (bs spfdififd by bn origin bnd 2 dfltb vfdtors)
 */
publid dlbss FillPbrbllflogrbm fxtfnds GrbpiidsPrimitivf
{
    publid finbl stbtid String mftiodSignbturf =
        "FillPbrbllflogrbm(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid FillPbrbllflogrbm lodbtf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        rfturn (FillPbrbllflogrbm)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd FillPbrbllflogrbm(SurfbdfTypf srdtypf,
                                CompositfTypf domptypf,
                                SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    publid FillPbrbllflogrbm(long pNbtivfPrim,
                             SurfbdfTypf srdtypf,
                             CompositfTypf domptypf,
                             SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    /**
     * All FillPbrbllflogrbm implfmfntors must ibvf tiis invokfr mftiod
     */
    publid nbtivf void FillPbrbllflogrbm(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                         doublf x0, doublf y0,
                                         doublf dx1, doublf dy1,
                                         doublf dx2, doublf dy2);

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        // REMIND: itfrbtf witi b FillRfdt primitivf?
        tirow nfw IntfrnblError("FillPbrbllflogrbm not implfmfntfd for "+
                                srdtypf+" witi "+domptypf);
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfFillPbrbllflogrbm(tiis);
    }

    privbtf stbtid dlbss TrbdfFillPbrbllflogrbm fxtfnds FillPbrbllflogrbm {
        FillPbrbllflogrbm tbrgft;

        publid TrbdfFillPbrbllflogrbm(FillPbrbllflogrbm tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void FillPbrbllflogrbm(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                      doublf x0, doublf y0,
                                      doublf dx1, doublf dy1,
                                      doublf dx2, doublf dy2)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.FillPbrbllflogrbm(sg2d, dfst, x0, y0, dx1, dy1, dx2, dy2);
        }
    }
}
