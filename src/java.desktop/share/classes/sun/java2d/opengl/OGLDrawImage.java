/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.Color;
import jbvb.bwt.Imbgf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.AffinfTrbnsformOp;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.TrbnsformBlit;
import sun.jbvb2d.pipf.DrbwImbgf;

publid dlbss OGLDrbwImbgf fxtfnds DrbwImbgf {

    @Ovfrridf
    protfdtfd void rfndfrImbgfXform(SunGrbpiids2D sg, Imbgf img,
                                    AffinfTrbnsform tx, int intfrpTypf,
                                    int sx1, int sy1, int sx2, int sy2,
                                    Color bgColor)
    {
        // punt to tif MfdibLib-bbsfd trbnsformImbgf() in tif supfrdlbss if:
        //     - bidubid intfrpolbtion is spfdififd
        //     - b bbdkground dolor is spfdififd bnd will bf usfd
        //     - tif sourdf surfbdf is nfitifr b tfxturf nor rfndfr-to-tfxturf
        //       surfbdf, bnd b non-dffbult intfrpolbtion iint is spfdififd
        //       (wf dbn only dontrol tif filtfring for tfxturf->surfbdf
        //       dopifs)
        //         REMIND: wf siould twfbk tif sw->tfxturf->surfbdf
        //         trbnsform dbsf to ibndlf filtfring bppropribtfly
        //         (sff 4841762)...
        //     - bn bppropribtf TrbnsformBlit primitivf dould not bf found
        if (intfrpTypf != AffinfTrbnsformOp.TYPE_BICUBIC) {
            SurfbdfDbtb dstDbtb = sg.surfbdfDbtb;
            SurfbdfDbtb srdDbtb =
                dstDbtb.gftSourdfSurfbdfDbtb(img,
                                             SunGrbpiids2D.TRANSFORM_GENERIC,
                                             sg.imbgfComp,
                                             bgColor);

            if (srdDbtb != null &&
                !isBgOpfrbtion(srdDbtb, bgColor) &&
                (srdDbtb.gftSurfbdfTypf() == OGLSurfbdfDbtb.OpfnGLTfxturf ||
                 srdDbtb.gftSurfbdfTypf() == OGLSurfbdfDbtb.OpfnGLSurfbdfRTT ||
                 intfrpTypf == AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR))
            {
                SurfbdfTypf srdTypf = srdDbtb.gftSurfbdfTypf();
                SurfbdfTypf dstTypf = dstDbtb.gftSurfbdfTypf();
                TrbnsformBlit blit = TrbnsformBlit.gftFromCbdif(srdTypf,
                                                                sg.imbgfComp,
                                                                dstTypf);

                if (blit != null) {
                    blit.Trbnsform(srdDbtb, dstDbtb,
                                   sg.dompositf, sg.gftCompClip(),
                                   tx, intfrpTypf,
                                   sx1, sy1, 0, 0, sx2-sx1, sy2-sy1);
                    rfturn;
                }
            }
        }

        supfr.rfndfrImbgfXform(sg, img, tx, intfrpTypf,
                               sx1, sy1, sx2, sy2, bgColor);
    }

    @Ovfrridf
    publid void trbnsformImbgf(SunGrbpiids2D sg, BufffrfdImbgf img,
                               BufffrfdImbgfOp op, int x, int y)
    {
        if (op != null) {
            if (op instbndfof AffinfTrbnsformOp) {
                AffinfTrbnsformOp btop = (AffinfTrbnsformOp) op;
                trbnsformImbgf(sg, img, x, y,
                               btop.gftTrbnsform(),
                               btop.gftIntfrpolbtionTypf());
                rfturn;
            } flsf {
                if (OGLBufImgOps.rfndfrImbgfWitiOp(sg, img, op, x, y)) {
                    rfturn;
                }
            }
            img = op.filtfr(img, null);
        }
        dopyImbgf(sg, img, x, y, null);
    }
}
