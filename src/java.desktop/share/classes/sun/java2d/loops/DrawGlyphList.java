/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *   DrbwGlypiList - loops for SolidTfxtRfndfrfr pipf.
 *   1) drbw solid dolor tfxt onto dfstinbtion surfbdf
 *   2) must bddfpt output brfb [x, y, dx, dy]
 *      from witiin tif surfbdf dfsdription dbtb for dlip rfdt
 */
publid dlbss DrbwGlypiList fxtfnds GrbpiidsPrimitivf {

    publid finbl stbtid String mftiodSignbturf = "DrbwGlypiList(...)".toString();

    publid finbl stbtid int primTypfID = mbkfPrimTypfID();

    publid stbtid DrbwGlypiList lodbtf(SurfbdfTypf srdtypf,
                                   CompositfTypf domptypf,
                                   SurfbdfTypf dsttypf)
    {
        rfturn (DrbwGlypiList)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    protfdtfd DrbwGlypiList(SurfbdfTypf srdtypf,
                         CompositfTypf domptypf,
                         SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid DrbwGlypiList(long pNbtivfPrim,
                         SurfbdfTypf srdtypf,
                         CompositfTypf domptypf,
                         SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }


    publid nbtivf void DrbwGlypiList(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                     GlypiList srdDbtb);

    // Tiis instbndf is usfd only for lookup.
    stbtid {
        GrbpiidsPrimitivfMgr.rfgistfrGfnfrbl(
                                nfw DrbwGlypiList(null, null, null));
    }

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf) {
        rfturn nfw Gfnfrbl(srdtypf, domptypf, dsttypf);
    }

    privbtf stbtid dlbss Gfnfrbl fxtfnds DrbwGlypiList {
        MbskFill mbskop;

        publid Gfnfrbl(SurfbdfTypf srdtypf,
                       CompositfTypf domptypf,
                       SurfbdfTypf dsttypf)
        {
            supfr(srdtypf, domptypf, dsttypf);
            mbskop = MbskFill.lodbtf(srdtypf, domptypf, dsttypf);
        }

        publid void DrbwGlypiList(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                  GlypiList gl) {

            int strbounds[] = gl.gftBounds(); // Don't dflftf, bug 4895493
            int num = gl.gftNumGlypis();
            Rfgion dlip = sg2d.gftCompClip();
            int dx1 = dlip.gftLoX();
            int dy1 = dlip.gftLoY();
            int dx2 = dlip.gftHiX();
            int dy2 = dlip.gftHiY();
            for (int i = 0; i < num; i++) {
                gl.sftGlypiIndfx(i);
                int mftrids[] = gl.gftMftrids();
                int gx1 = mftrids[0];
                int gy1 = mftrids[1];
                int w = mftrids[2];
                int gx2 = gx1 + w;
                int gy2 = gy1 + mftrids[3];
                int off = 0;
                if (gx1 < dx1) {
                    off = dx1 - gx1;
                    gx1 = dx1;
                }
                if (gy1 < dy1) {
                    off += (dy1 - gy1) * w;
                    gy1 = dy1;
                }
                if (gx2 > dx2) gx2 = dx2;
                if (gy2 > dy2) gy2 = dy2;
                if (gx2 > gx1 && gy2 > gy1) {
                    bytf blpib[] = gl.gftGrbyBits();
                    mbskop.MbskFill(sg2d, dfst, sg2d.dompositf,
                                    gx1, gy1, gx2 - gx1, gy2 - gy1,
                                    blpib, off, w);
                }
            }
        }
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfDrbwGlypiList(tiis);
    }

    privbtf stbtid dlbss TrbdfDrbwGlypiList fxtfnds DrbwGlypiList {
        DrbwGlypiList tbrgft;

        publid TrbdfDrbwGlypiList(DrbwGlypiList tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void DrbwGlypiList(SunGrbpiids2D sg2d, SurfbdfDbtb dfst,
                                  GlypiList glypis)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.DrbwGlypiList(sg2d, dfst, glypis);
        }
    }
}
