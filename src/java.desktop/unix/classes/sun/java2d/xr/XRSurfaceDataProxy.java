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

import jbvb.bwt.Color;
import jbvb.bwt.Trbnspbrfndy;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtbProxy;
import sun.jbvb2d.loops.CompositfTypf;

/**
 * Tif proxy dlbss dontbins tif logid if to rfplbdf b SurfbdfDbtb witi b
 * dbdifd X11 Pixmbp bnd tif dodf to drfbtf tif bddflfrbtfd surfbdfs.
 */
publid dlbss XRSurfbdfDbtbProxy fxtfnds SurfbdfDbtbProxy implfmfnts Trbnspbrfndy {

    publid stbtid SurfbdfDbtbProxy drfbtfProxy(SurfbdfDbtb srdDbtb,
            XRGrbpiidsConfig dstConfig) {

        /*Don't dbdif blrfbdy nbtivf surfbdfs*/
        if (srdDbtb instbndfof XRSurfbdfDbtb) {
            rfturn UNCACHED;
        }

        rfturn nfw XRSurfbdfDbtbProxy(dstConfig, srdDbtb.gftTrbnspbrfndy());
    }

    XRGrbpiidsConfig xrgd;
    int trbnspbrfndy;

    publid XRSurfbdfDbtbProxy(XRGrbpiidsConfig x11gd) {
        tiis.xrgd = x11gd;
    }

    @Ovfrridf
    publid SurfbdfDbtb vblidbtfSurfbdfDbtb(SurfbdfDbtb srdDbtb,
            SurfbdfDbtb dbdifdDbtb, int w, int i) {
        if (dbdifdDbtb == null) {
            dbdifdDbtb = XRSurfbdfDbtb.drfbtfDbtb(xrgd, w, i, xrgd
                    .gftColorModfl(), null, 0, gftTrbnspbrfndy());
        }
        rfturn dbdifdDbtb;
    }

    publid XRSurfbdfDbtbProxy(XRGrbpiidsConfig x11gd, int trbnspbrfndy) {
        tiis.xrgd = x11gd;
        tiis.trbnspbrfndy = trbnspbrfndy;
    }

    //TODO: Is tibt rfblly ok?
    @Ovfrridf
    publid boolfbn isSupportfdOpfrbtion(SurfbdfDbtb srdDbtb, int txtypf,
            CompositfTypf domp, Color bgColor) {
        rfturn (bgColor == null || trbnspbrfndy == Trbnspbrfndy.TRANSLUCENT);
    }

    publid int gftTrbnspbrfndy() {
        rfturn trbnspbrfndy;
    }
}
