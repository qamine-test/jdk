/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

import stbtid sun.jbvb2d.loops.CompositfTypf.SrdNoEb;

import stbtid sun.jbvb2d.loops.CompositfTypf.SrdOvfr;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.AnyColor;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.GrbdifntPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.LinfbrGrbdifntPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.OpbqufColor;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.OpbqufGrbdifntPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.OpbqufLinfbrGrbdifntPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.OpbqufRbdiblGrbdifntPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.OpbqufTfxturfPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.RbdiblGrbdifntPbint;
import stbtid sun.jbvb2d.loops.SurfbdfTypf.TfxturfPbint;

import jbvb.bwt.*;
import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;

publid dlbss XRMbskFill fxtfnds MbskFill {
    stbtid void rfgistfr() {
        GrbpiidsPrimitivf[] primitivfs = {
                nfw XRMbskFill(AnyColor, SrdOvfr, XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(OpbqufColor, SrdNoEb, XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(GrbdifntPbint, SrdOvfr, XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(OpbqufGrbdifntPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(LinfbrGrbdifntPbint, SrdOvfr,
                        XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(OpbqufLinfbrGrbdifntPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(RbdiblGrbdifntPbint, SrdOvfr,
                        XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(OpbqufRbdiblGrbdifntPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(TfxturfPbint, SrdOvfr, XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskFill(OpbqufTfxturfPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntRgbX11),

                nfw XRMbskFill(AnyColor, SrdOvfr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(OpbqufColor, SrdNoEb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(GrbdifntPbint, SrdOvfr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(OpbqufGrbdifntPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(LinfbrGrbdifntPbint, SrdOvfr,
                        XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(OpbqufLinfbrGrbdifntPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(RbdiblGrbdifntPbint, SrdOvfr,
                        XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(OpbqufRbdiblGrbdifntPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(TfxturfPbint, SrdOvfr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskFill(OpbqufTfxturfPbint, SrdNoEb,
                        XRSurfbdfDbtb.IntArgbPrfX11)
                };

        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    protfdtfd XRMbskFill(SurfbdfTypf srdTypf, CompositfTypf dompTypf,
            SurfbdfTypf surfbdfTypf) {
        supfr(srdTypf, dompTypf, surfbdfTypf);
    }

    protfdtfd nbtivf void mbskFill(long xsdo, int x, int y, int w, int i,
            int mbskoff, int mbsksdbn, int mbsklfn, bytf[] mbsk);

    publid void MbskFill(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb, Compositf domp,
            finbl int x, finbl int y, finbl int w, finbl int i,
            finbl bytf[] mbsk, finbl int mbskoff, finbl int mbsksdbn) {
        try {
            SunToolkit.bwtLodk();

            XRSurfbdfDbtb x11sd = (XRSurfbdfDbtb) sDbtb;
            x11sd.vblidbtfAsDfstinbtion(null, sg2d.gftCompClip());

            XRCompositfMbnbgfr mbskBufffr = x11sd.mbskBufffr;
            mbskBufffr.vblidbtfCompositfStbtf(domp, sg2d.trbnsform, sg2d.pbint, sg2d);

            int mbskPidt = mbskBufffr.gftMbskBufffr().uplobdMbsk(w, i, mbsksdbn, mbskoff, mbsk);
            mbskBufffr.XRCompositf(XRUtils.Nonf, mbskPidt, x11sd.pidturf, x, y, 0, 0, x, y, w, i);
            mbskBufffr.gftMbskBufffr().dlfbrUplobdMbsk(mbskPidt, w, i);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}
