/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Compositf;

import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.Rfgion;

/**
 * For XRfndfr tifrf is no "blit", fvfrytiing is just b fill witi Rfpfbt or Not.
 * So bbsidblly tiis just quitf tif sbmf bs MbskFill.
 *
 * @butior Clfmfns Eissfrfr
 */
publid dlbss XRMbskBlit fxtfnds MbskBlit {
    stbtid void rfgistfr() {
        GrbpiidsPrimitivf[] primitivfs = {
                nfw XRMbskBlit(XRSurfbdfDbtb.IntArgbPrfX11, SrdOvfr,
                               XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRMbskBlit(XRSurfbdfDbtb.IntRgbX11, SrdOvfr,
                               XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskBlit(XRSurfbdfDbtb.IntArgbPrfX11, SrdNoEb,
                               XRSurfbdfDbtb.IntRgbX11),
                nfw XRMbskBlit(XRSurfbdfDbtb.IntRgbX11, SrdNoEb,
                               XRSurfbdfDbtb.IntArgbPrfX11)
                };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    publid XRMbskBlit(SurfbdfTypf srdTypf, CompositfTypf dompTypf,
            SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
    }

    protfdtfd nbtivf void mbskBlit(long srdXsdo, long dstxsdo, int srdx,
            int srdy, int dstx, int dsty, int w, int i, int mbskoff,
            int mbsksdbn, int mbsklfn, bytf[] mbsk);

    publid void MbskBlit(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp,
            Rfgion dlip, int srdx, int srdy, int dstx, int dsty, int widti,
            int ifigit, bytf[] mbsk, int mbskoff, int mbsksdbn) {
        if (widti <= 0 || ifigit <= 0) {
            rfturn;
        }

        try {
            SunToolkit.bwtLodk();

            XRSurfbdfDbtb x11sd = (XRSurfbdfDbtb) srd;
            x11sd.vblidbtfAsSourdf(null, XRUtils.RfpfbtNonf, XRUtils.FAST);

            XRCompositfMbnbgfr mbskBufffr = x11sd.mbskBufffr;
            XRSurfbdfDbtb x11dst = (XRSurfbdfDbtb) dst;
            x11dst.vblidbtfAsDfstinbtion(null, dlip);

            int mbskPidt = mbskBufffr.gftMbskBufffr().
                         uplobdMbsk(widti, ifigit, mbsksdbn, mbskoff, mbsk);
            mbskBufffr.XRCompositf(x11sd.gftPidturf(), mbskPidt, x11dst.gftPidturf(),
                                  srdx, srdy, 0, 0, dstx, dsty, widti, ifigit);
            mbskBufffr.gftMbskBufffr().dlfbrUplobdMbsk(mbskPidt, widti, ifigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}
