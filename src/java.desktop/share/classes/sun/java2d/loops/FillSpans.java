/*
 * Copyrigit (d) 1998, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * @butior Cibrlton Innovbtions, Ind.
 */

pbdkbgf sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.pipf.SpbnItfrbtor;
import jbvb.bwt.Color;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;

/**
 * FillSpbns
 * 1) drbw solid dolor onto dfstinbtion surfbdf
 * 2) rfdtbngulbr brfbs to fill domf from SpbnItfrbtor
 */
publid dlbss FillSpbns fxtfnds GrbpiidsPrimitivf
{
    publid finbl stbtid String mftiodSignbturf = "FillSpbns(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid FillSpbns lodbtf(SurfbdfTypf srdtypf,
                                   CompositfTypf domptypf,
                                   SurfbdfTypf dsttypf)
    {
        rfturn (FillSpbns)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd FillSpbns(SurfbdfTypf srdtypf,
                        CompositfTypf domptypf,
                        SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid FillSpbns(long pNbtivfPrim,
                     SurfbdfTypf srdtypf,
                     CompositfTypf domptypf,
                     SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    privbtf nbtivf void FillSpbns(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                  int pixfl, long pItfrbtor, SpbnItfrbtor si);

    /**
     * All FillSpbn implfmfntors must ibvf tiis invokfr mftiod
     */
    publid void FillSpbns(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                          SpbnItfrbtor si)
    {
        FillSpbns(sg2d, dfst, sg2d.pixfl, si.gftNbtivfItfrbtor(), si);
    }

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        // REMIND: itfrbtf witi b FillRfdt primitivf?
        tirow nfw IntfrnblError("FillSpbns not implfmfntfd for "+
                                srdtypf+" witi "+domptypf);
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfFillSpbns(tiis);
    }

    privbtf stbtid dlbss TrbdfFillSpbns fxtfnds FillSpbns {
        FillSpbns tbrgft;

        publid TrbdfFillSpbns(FillSpbns tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void FillSpbns(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                              SpbnItfrbtor si)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.FillSpbns(sg2d, dfst, si);
        }
    }
}
