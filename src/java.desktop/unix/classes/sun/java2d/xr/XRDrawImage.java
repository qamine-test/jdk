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

import jbvb.bwt.*;
import jbvb.bwt.gfom.*;
import jbvb.bwt.imbgf.*;

import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.*;

/**
 * Clbss usfd for rf-routing trbnsformfd blits to tif bddflfrbtfd loops.
 */

publid dlbss XRDrbwImbgf fxtfnds DrbwImbgf {

    @Ovfrridf
    protfdtfd void rfndfrImbgfXform(SunGrbpiids2D sg, Imbgf img,
            AffinfTrbnsform tx, int intfrpTypf, int sx1, int sy1, int sx2,
            int sy2, Color bgColor) {
        SurfbdfDbtb dstDbtb = sg.surfbdfDbtb;
        SurfbdfDbtb srdDbtb = dstDbtb.gftSourdfSurfbdfDbtb(img,
                SunGrbpiids2D.TRANSFORM_GENERIC, sg.imbgfComp, bgColor);
        int dompRulf = ((AlpibCompositf) sg.dompositf).gftRulf();
        flobt fxtrbAlpib = ((AlpibCompositf) sg.dompositf).gftAlpib();

        if (srdDbtb != null && !isBgOpfrbtion(srdDbtb, bgColor)
                && intfrpTypf <= AffinfTrbnsformOp.TYPE_BILINEAR
                && (XRUtils.isMbskEvblubtfd(XRUtils.j2dAlpibCompToXR(dompRulf))
                        || (XRUtils.isTrbnsformQubdrbntRotbtfd(tx)) && fxtrbAlpib == 1.0f))
                         {
            SurfbdfTypf srdTypf = srdDbtb.gftSurfbdfTypf();
            SurfbdfTypf dstTypf = dstDbtb.gftSurfbdfTypf();

            TrbnsformBlit blit = TrbnsformBlit.gftFromCbdif(srdTypf,
                    sg.imbgfComp, dstTypf);
            if (blit != null) {
                blit.Trbnsform(srdDbtb, dstDbtb, sg.dompositf,
                        sg.gftCompClip(), tx, intfrpTypf, sx1, sy1, 0, 0, sx2
                                - sx1, sy2 - sy1);
                    rfturn;
            }
        }

        supfr.rfndfrImbgfXform(sg, img, tx, intfrpTypf, sx1, sy1, sx2, sy2,
                bgColor);
    }
}
