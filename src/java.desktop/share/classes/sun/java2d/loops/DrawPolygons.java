/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * @butior Cibrlton Innovbtions, Ind.
 */

pbdkbgf sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;

/**
 *   DrbwPolygons
 *   1) drbw singlf-widti linf polygons onto dfstinbtion surfbdf
 *   2) must bddfpt output brfb [x, y, dx, dy]
 *      from witiin tif surfbdf dfsdription dbtb for dlip rfdt
 */
publid dlbss DrbwPolygons fxtfnds GrbpiidsPrimitivf
{
    publid finbl stbtid String mftiodSignbturf = "DrbwPolygons(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid DrbwPolygons lodbtf(SurfbdfTypf srdtypf,
                                      CompositfTypf domptypf,
                                      SurfbdfTypf dsttypf)
    {
        rfturn (DrbwPolygons)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd DrbwPolygons(SurfbdfTypf srdtypf,
                           CompositfTypf domptypf,
                           SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid DrbwPolygons(long pNbtivfPrim,
                        SurfbdfTypf srdtypf,
                        CompositfTypf domptypf,
                        SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    /**
     *   All DrbwPolygon implfmfntors must ibvf tiis invokfr mftiod
     */
    publid nbtivf void DrbwPolygons(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                    int xPoints[], int yPoints[],
                                    int nPoints[], int numPolys,
                                    int trbnsX, int trbnsY,
                                    boolfbn dlosf);

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        // REMIND: usf FillSpbns or donvfrtfr objfdt?
        tirow nfw IntfrnblError("DrbwPolygons not implfmfntfd for "+
                                srdtypf+" witi "+domptypf);
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfDrbwPolygons(tiis);
    }

    privbtf stbtid dlbss TrbdfDrbwPolygons fxtfnds DrbwPolygons {
        DrbwPolygons tbrgft;

        publid TrbdfDrbwPolygons(DrbwPolygons tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void DrbwPolygons(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                 int xPoints[], int yPoints[],
                                 int nPoints[], int numPolys,
                                 int trbnsX, int trbnsY,
                                 boolfbn dlosf)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.DrbwPolygons(sg2d, sDbtb,
                                xPoints, yPoints, nPoints, numPolys,
                                trbnsX, trbnsY, dlosf);
        }
    }
}
