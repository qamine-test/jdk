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
 * DrbwPbrbllflogrbm
 * 1) fill tif brfb bftwffn tif 4 fdgfs of bn outfr pbrbllflogrbm
 *    (bs spfdififd by bn origin bnd 2 dfltb vfdtors)
 *    but blso outsidf tif 4 fdgfs of bn innfr pbrbllflogrbm
 *    (bs spfdififd by proportionbl bmounts of tif outfr dfltb vfdtors)
 */
publid dlbss DrbwPbrbllflogrbm fxtfnds GrbpiidsPrimitivf
{
    publid finbl stbtid String mftiodSignbturf =
        "DrbwPbrbllflogrbm(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid DrbwPbrbllflogrbm lodbtf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        rfturn (DrbwPbrbllflogrbm)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd DrbwPbrbllflogrbm(SurfbdfTypf srdtypf,
                                CompositfTypf domptypf,
                                SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    publid DrbwPbrbllflogrbm(long pNbtivfPrim,
                             SurfbdfTypf srdtypf,
                             CompositfTypf domptypf,
                             SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    /**
     * All DrbwPbrbllflogrbm implfmfntors must ibvf tiis invokfr mftiod
     */
    publid nbtivf void DrbwPbrbllflogrbm(SunGrbpiids2D sg, SurfbdfDbtb dfst,
                                         doublf x, doublf y,
                                         doublf dx1, doublf dy1,
                                         doublf dx2, doublf dy2,
                                         doublf lw1, doublf lw2);

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        // REMIND: itfrbtf witi b FillRfdt primitivf?
        tirow nfw IntfrnblError("DrbwPbrbllflogrbm not implfmfntfd for "+
                                srdtypf+" witi "+domptypf);
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfDrbwPbrbllflogrbm(tiis);
    }

    privbtf stbtid dlbss TrbdfDrbwPbrbllflogrbm fxtfnds DrbwPbrbllflogrbm {
        DrbwPbrbllflogrbm tbrgft;

        publid TrbdfDrbwPbrbllflogrbm(DrbwPbrbllflogrbm tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void DrbwPbrbllflogrbm(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                      doublf x, doublf y,
                                      doublf dx1, doublf dy1,
                                      doublf dx2, doublf dy2,
                                      doublf lw1, doublf lw2)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.DrbwPbrbllflogrbm(sg2d, dfst,
                                     x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }
    }
}
