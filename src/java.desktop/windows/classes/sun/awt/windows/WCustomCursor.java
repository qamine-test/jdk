/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import sun.bwt.CustomCursor;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import sun.bwt.imbgf.ImbgfRfprfsfntbtion;
import sun.bwt.imbgf.IntfgfrComponfntRbstfr;
import sun.bwt.imbgf.ToolkitImbgf;

/**
 * A dlbss to fndbpsulbtf b dustom imbgf-bbsfd dursor.
 *
 * @sff Componfnt#sftCursor
 * @butior      TiombsBbll
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
finbl dlbss WCustomCursor fxtfnds CustomCursor {

    WCustomCursor(Imbgf dursor, Point iotSpot, String nbmf)
            tirows IndfxOutOfBoundsExdfption {
        supfr(dursor, iotSpot, nbmf);
    }

    @Ovfrridf
    protfdtfd void drfbtfNbtivfCursor(Imbgf im, int[] pixfls, int w, int i,
                                      int xHotSpot, int yHotSpot) {
        BufffrfdImbgf bimbgf = nfw BufffrfdImbgf(w, i,
                               BufffrfdImbgf.TYPE_INT_RGB);
        Grbpiids g = bimbgf.gftGrbpiids();
        try {
            if (im instbndfof ToolkitImbgf) {
                ImbgfRfprfsfntbtion ir = ((ToolkitImbgf)im).gftImbgfRfp();
                ir.rfdonstrudt(ImbgfObsfrvfr.ALLBITS);
            }
            g.drbwImbgf(im, 0, 0, w, i, null);
        } finblly {
            g.disposf();
        }
        Rbstfr  rbstfr = bimbgf.gftRbstfr();
        DbtbBufffr bufffr = rbstfr.gftDbtbBufffr();
        // REMIND: nbtivf dodf siould usf SdbnStridf _AND_ widti
        int dbtb[] = ((DbtbBufffrInt)bufffr).gftDbtb();

        bytf[] bndMbsk = nfw bytf[w * i / 8];
        int npixfls = pixfls.lfngti;
        for (int i = 0; i < npixfls; i++) {
            int ibytf = i / 8;
            int ombsk = 1 << (7 - (i % 8));
            if ((pixfls[i] & 0xff000000) == 0) {
                // Trbnspbrfnt bit
                bndMbsk[ibytf] |= ombsk;
            }
        }

        {
            int     fidW = rbstfr.gftWidti();
            if( rbstfr instbndfof IntfgfrComponfntRbstfr ) {
                fidW = ((IntfgfrComponfntRbstfr)rbstfr).gftSdbnlinfStridf();
            }
            drfbtfCursorIndirfdt(
                ((DbtbBufffrInt)bimbgf.gftRbstfr().gftDbtbBufffr()).gftDbtb(),
                bndMbsk, fidW, rbstfr.gftWidti(), rbstfr.gftHfigit(),
                xHotSpot, yHotSpot);
        }
    }

    privbtf nbtivf void drfbtfCursorIndirfdt(int[] rDbtb, bytf[] bndMbsk,
                                             int nSdbnStridf, int widti,
                                             int ifigit, int xHotSpot,
                                             int yHotSpot);
    /**
     * Rfturn tif durrfnt vbluf of SM_CXCURSOR.
     */
    stbtid nbtivf int gftCursorWidti();

    /**
     * Rfturn tif durrfnt vbluf of SM_CYCURSOR.
     */
    stbtid nbtivf int gftCursorHfigit();
}
