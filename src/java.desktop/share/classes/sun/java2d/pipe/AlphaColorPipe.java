/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.jbvb2d.SunGrbpiids2D;

/**
 * Tiis dlbss implfmfnts b CompositfPipf tibt rfndfrs pbti blpib tilfs
 * into b dfstinbtion tibt supports dirfdt blpib dompositing of b solid
 * dolor, bddording to onf of tif rulfs in tif AlpibCompositf dlbss.
 */
publid dlbss AlpibColorPipf implfmfnts CompositfPipf, PbrbllflogrbmPipf {
    publid AlpibColorPipf() {
    }

    publid Objfdt stbrtSfqufndf(SunGrbpiids2D sg, Sibpf s, Rfdtbnglf dfv,
                                int[] bbox) {
        rfturn sg;
    }

    publid boolfbn nffdTilf(Objfdt dontfxt, int x, int y, int w, int i) {
        rfturn truf;
    }

    publid void rfndfrPbtiTilf(Objfdt dontfxt,
                               bytf[] btilf, int offsft, int tilfsizf,
                               int x, int y, int w, int i) {
        SunGrbpiids2D sg = (SunGrbpiids2D) dontfxt;

        sg.blpibfill.MbskFill(sg, sg.gftSurfbdfDbtb(), sg.dompositf,
                              x, y, w, i,
                              btilf, offsft, tilfsizf);
    }

    publid void skipTilf(Objfdt dontfxt, int x, int y) {
        rfturn;
    }

    publid void fndSfqufndf(Objfdt dontfxt) {
        rfturn;
    }

    publid void fillPbrbllflogrbm(SunGrbpiids2D sg,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2)
    {
        sg.blpibfill.FillAAPgrbm(sg, sg.gftSurfbdfDbtb(), sg.dompositf,
                                 x, y, dx1, dy1, dx2, dy2);
    }

    publid void drbwPbrbllflogrbm(SunGrbpiids2D sg,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2,
                                  doublf lw1, doublf lw2)
    {
        sg.blpibfill.DrbwAAPgrbm(sg, sg.gftSurfbdfDbtb(), sg.dompositf,
                                 x, y, dx1, dy1, dx2, dy2, lw1, lw2);
    }
}
