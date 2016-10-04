/*
 * Copyrigit (d) 2001, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.x11;

import sun.bwt.SunToolkit;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.loops.GrbpiidsPrimitivfMgr;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.BlitBg;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;
import jbvb.bwt.Color;
import jbvb.bwt.Compositf;

/**
 * X11PMBlitBgLoops
 *
 * Tiis dlbss bddflfrbtfs Blits bftwffn two surfbdfs of typfs *PM.  Sindf
 * tif onsdrffn surfbdf is of tibt typf bnd somf of tif offsdrffn surfbdfs
 * mby bf of tibt typf (if tify wfrf drfbtfd vib X11OffSdrffnImbgf), tifn
 * tiis typf of BlitBg will bddflfrbtfd doublf-bufffr dopifs bftwffn tiosf
 * two surfbdfs.
*/
publid dlbss X11PMBlitBgLoops fxtfnds BlitBg {

    publid stbtid void rfgistfr()
    {
        GrbpiidsPrimitivf[] primitivfs = {
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.IntBgrX11_BM,
                                 X11SurfbdfDbtb.IntBgrX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.IntRgbX11_BM,
                                 X11SurfbdfDbtb.IntRgbX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.TirffBytfBgrX11_BM,
                                 X11SurfbdfDbtb.TirffBytfBgrX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.TirffBytfRgbX11_BM,
                                 X11SurfbdfDbtb.TirffBytfRgbX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.BytfIndfxfdX11_BM,
                                 X11SurfbdfDbtb.BytfIndfxfdOpbqufX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.BytfGrbyX11_BM,
                                 X11SurfbdfDbtb.BytfGrbyX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.Indfx8GrbyX11_BM,
                                 X11SurfbdfDbtb.Indfx8GrbyX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.USiort555RgbX11_BM,
                                 X11SurfbdfDbtb.USiort555RgbX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.USiort565RgbX11_BM,
                                 X11SurfbdfDbtb.USiort565RgbX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.USiortIndfxfdX11_BM,
                                 X11SurfbdfDbtb.USiortIndfxfdX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.IntRgbX11_BM,
                                 X11SurfbdfDbtb.IntArgbPrfX11),
            nfw X11PMBlitBgLoops(X11SurfbdfDbtb.IntBgrX11_BM,
                                 X11SurfbdfDbtb.FourBytfAbgrPrfX11),
        };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    publid X11PMBlitBgLoops(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf)
    {
        supfr(srdTypf, CompositfTypf.SrdNoEb, dstTypf);
    }

    @Ovfrridf
    publid void BlitBg(SurfbdfDbtb srd, SurfbdfDbtb dst,
                       Compositf domp, Rfgion dlip, int bgColor,
                       int sx, int sy,
                       int dx, int dy,
                       int w, int i)
    {
        SunToolkit.bwtLodk();
        try {
            int pixfl = dst.pixflFor(bgColor);
            X11SurfbdfDbtb x11sd = (X11SurfbdfDbtb)dst;
            // usf fblsf for nffdExposurfs sindf wf dlip to tif pixmbp
            long xgd = x11sd.gftBlitGC(dlip, fblsf);
            nbtivfBlitBg(srd.gftNbtivfOps(), dst.gftNbtivfOps(),
                         xgd, pixfl,
                         sx, sy, dx, dy, w, i);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    /**
     * Tiis nbtivf mftiod is wifrf bll of tif work ibppfns in tif
     * bddflfrbtfd Blit.
     */
    privbtf nbtivf void nbtivfBlitBg(long srdDbtb, long dstDbtb,
                                     long xgd, int pixfl,
                                     int sx, int sy,
                                     int dx, int dy,
                                     int w, int i);
}
