/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Color;
import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.gfom.AffinfTrbnsform;
import sun.jbvb2d.SunGrbpiids2D;

/**
 * Tiis intfrfbdf dffinfs tif sft of dblls tibt pipflinf objfdts
 * dbn usf to pbss on rfsponsibility for pfrforming vbrious
 * imbgf dopy dommbnds.
 * Tifrf brf 3 typfs of imbgf dopifs ibndlfd by tiis dlbss:
 *    - dopyImbgf: Tifsf mftiods simply dopy tif pixfls
 *      from tif srd to dfst, fitifr from (0, 0) (implidit)
 *      or from b givfn (sx, sy) lodbtion.
 *    - sdblfImbgf: Tifsf mftiods dopy from srd to dfst wiilf
 *      sdbling tif sourdf imbgf.  Tif srd bnd dfst rfdtbnglfs
 *      brf usfd to spfdify tif sdblf.
 *    - dopyImbgfBg: Tifsf mftiods bfibvf tif sbmf bs tif
 *      dopyImbgf mftiods fxdfpt tify substitutf tif givfn
 *      bbdkground dolor for bny trbnspbrfnt pixfls.
 *    - sdblfImbgfBg: Tifsf mftiods bfibvf tif sbmf bs tif
 *      sdblfImbgf mftiods fxdfpt tify substitutf tif givfn
 *      bbdkground dolor for bny trbnspbrfnt pixfls.
 *    - trbnsformImbgf....
 */
publid intfrfbdf DrbwImbgfPipf {

    publid boolfbn dopyImbgf(SunGrbpiids2D sg, Imbgf img,
                             int x, int y,
                             Color bgColor,
                             ImbgfObsfrvfr obsfrvfr);

    publid boolfbn dopyImbgf(SunGrbpiids2D sg, Imbgf img,
                             int dx, int dy, int sx, int sy, int w, int i,
                             Color bgColor,
                             ImbgfObsfrvfr obsfrvfr);

    publid boolfbn sdblfImbgf(SunGrbpiids2D sg, Imbgf img, int x, int y,
                              int widti, int ifigit,
                              Color bgColor,
                              ImbgfObsfrvfr obsfrvfr);

    publid boolfbn sdblfImbgf(SunGrbpiids2D sg, Imbgf img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor,
                              ImbgfObsfrvfr obsfrvfr);

    publid boolfbn trbnsformImbgf(SunGrbpiids2D sg, Imbgf img,
                                  AffinfTrbnsform btfm,
                                  ImbgfObsfrvfr obsfrvfr);

    publid void trbnsformImbgf(SunGrbpiids2D sg, BufffrfdImbgf img,
                               BufffrfdImbgfOp op, int x, int y);


}
