/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.font.GlypiList;

/**
 *   DrbwGlypiListLCD- loops for LCDTfxtRfndfrfr pipf
 *   1) drbw LCD bnti-blibsfd tfxt onto dfstinbtion surfbdf
 *   2) must bddfpt output brfb [x, y, dx, dy]
 *      from witiin tif surfbdf dfsdription dbtb for dlip rfdt
 */
publid dlbss DrbwGlypiListLCD fxtfnds GrbpiidsPrimitivf {

    publid finbl stbtid String
        mftiodSignbturf = "DrbwGlypiListLCD(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid DrbwGlypiListLCD lodbtf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        rfturn (DrbwGlypiListLCD)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd DrbwGlypiListLCD(SurfbdfTypf srdtypf,
                                CompositfTypf domptypf,
                                SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid DrbwGlypiListLCD(long pNbtivfPrim,
                             SurfbdfTypf srdtypf,
                             CompositfTypf domptypf,
                             SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    publid nbtivf void DrbwGlypiListLCD(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                         GlypiList srdDbtb);

    stbtid {
        GrbpiidsPrimitivfMgr.rfgistfrGfnfrbl(
                                   nfw DrbwGlypiListLCD(null, null, null));
    }

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf) {
        /* Do not rfturn b Gfnfrbl loop. SunGrbpiids2D dftfrminfs wiftifr
         * to usf LCD or stbndbrd AA tfxt bbsfd on wiftifr tifrf is bn
         * instbllfd loop.
         * Tiis dbn bf undommfntfd ondf tifrf is b Gfnfrbl loop wiidi dbn
         * ibndlf onf bytf pfr dolour domponfnt mbsks propfrly.
         */
        rfturn null;
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfDrbwGlypiListLCD(tiis);
    }

    privbtf stbtid dlbss TrbdfDrbwGlypiListLCD fxtfnds DrbwGlypiListLCD {
        DrbwGlypiListLCD tbrgft;

        publid TrbdfDrbwGlypiListLCD(DrbwGlypiListLCD tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void DrbwGlypiListLCD(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                      GlypiList glypis)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.DrbwGlypiListLCD(sg2d, dfst, glypis);
        }
    }
}
