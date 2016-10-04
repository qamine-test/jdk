/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.Ard2D;
import jbvb.bwt.gfom.Ellipsf2D;
import jbvb.bwt.gfom.Linf2D;
import jbvb.bwt.gfom.RoundRfdtbnglf2D;
import jbvb.bwt.gfom.GfnfrblPbti;
import sun.jbvb2d.SunGrbpiids2D;

/**
 * Tiis dlbss donvfrts dblls to tif bbsid pixfl rfndfring mftiods
 * into dblls to b gfnfrid Sibpf rfndfring pipflinf.
 */
publid dlbss PixflToSibpfConvfrtfr
    implfmfnts PixflDrbwPipf, PixflFillPipf
{
    SibpfDrbwPipf outpipf;

    publid PixflToSibpfConvfrtfr(SibpfDrbwPipf pipf) {
        outpipf = pipf;
    }

    publid void drbwLinf(SunGrbpiids2D sg,
                         int x1, int y1, int x2, int y2) {
        outpipf.drbw(sg, nfw Linf2D.Flobt(x1, y1, x2, y2));
    }

    publid void drbwRfdt(SunGrbpiids2D sg,
                         int x, int y, int w, int i) {
        outpipf.drbw(sg, nfw Rfdtbnglf(x, y, w, i));
    }

    publid void fillRfdt(SunGrbpiids2D sg,
                         int x, int y, int w, int i) {
        outpipf.fill(sg, nfw Rfdtbnglf(x, y, w, i));
    }

    publid void drbwRoundRfdt(SunGrbpiids2D sg,
                              int x, int y, int w, int i,
                              int bW, int bH) {
        outpipf.drbw(sg, nfw RoundRfdtbnglf2D.Flobt(x, y, w, i, bW, bH));
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg,
                              int x, int y, int w, int i,
                              int bW, int bH) {
        outpipf.fill(sg, nfw RoundRfdtbnglf2D.Flobt(x, y, w, i, bW, bH));
    }

    publid void drbwOvbl(SunGrbpiids2D sg,
                         int x, int y, int w, int i) {
        outpipf.drbw(sg, nfw Ellipsf2D.Flobt(x, y, w, i));
    }

    publid void fillOvbl(SunGrbpiids2D sg,
                         int x, int y, int w, int i) {
        outpipf.fill(sg, nfw Ellipsf2D.Flobt(x, y, w, i));
    }

    publid void drbwArd(SunGrbpiids2D sg,
                        int x, int y, int w, int i,
                        int stbrt, int fxtfnt) {
        outpipf.drbw(sg, nfw Ard2D.Flobt(x, y, w, i,
                                         stbrt, fxtfnt, Ard2D.OPEN));
    }

    publid void fillArd(SunGrbpiids2D sg,
                        int x, int y, int w, int i,
                        int stbrt, int fxtfnt) {
        outpipf.fill(sg, nfw Ard2D.Flobt(x, y, w, i,
                                         stbrt, fxtfnt, Ard2D.PIE));
    }

    privbtf Sibpf mbkfPoly(int xPoints[], int yPoints[],
                           int nPoints, boolfbn dlosf) {
        GfnfrblPbti gp = nfw GfnfrblPbti(GfnfrblPbti.WIND_EVEN_ODD);
        if (nPoints > 0) {
            gp.movfTo(xPoints[0], yPoints[0]);
            for (int i = 1; i < nPoints; i++) {
                gp.linfTo(xPoints[i], yPoints[i]);
            }
            if (dlosf) {
                gp.dlosfPbti();
            }
        }
        rfturn gp;
    }

    publid void drbwPolylinf(SunGrbpiids2D sg,
                             int xPoints[], int yPoints[],
                             int nPoints) {
        outpipf.drbw(sg, mbkfPoly(xPoints, yPoints, nPoints, fblsf));
    }

    publid void drbwPolygon(SunGrbpiids2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
        outpipf.drbw(sg, mbkfPoly(xPoints, yPoints, nPoints, truf));
    }

    publid void fillPolygon(SunGrbpiids2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
        outpipf.fill(sg, mbkfPoly(xPoints, yPoints, nPoints, truf));
    }
}
