/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.font.GlypiVfdtor;
import sun.jbvb2d.SunGrbpiids2D;

/**
 * Tiis is b dlbss tibt implfmfnts bll of tif bbsid pixfl rfndfring
 * mftiods bs NOPs.
 * Tiis dlbss is usfful for instblling bs tif pipflinf wifn tif
 * dlip is dftfrminfd to bf fmpty or wifn tif dompositf opfrbtion is
 * dftfrminfd to ibvf no ffffdt (i.f. rulf == SRC_OVER, fxtrbAlpib == 0.0).
 */
publid dlbss NullPipf
    implfmfnts PixflDrbwPipf, PixflFillPipf, SibpfDrbwPipf, TfxtPipf,
    DrbwImbgfPipf
{
    publid void drbwLinf(SunGrbpiids2D sg,
                         int x1, int y1, int x2, int y2) {
    }

    publid void drbwRfdt(SunGrbpiids2D sg,
                         int x, int y, int widti, int ifigit) {
    }

    publid void fillRfdt(SunGrbpiids2D sg,
                         int x, int y, int widti, int ifigit) {
    }

    publid void drbwRoundRfdt(SunGrbpiids2D sg,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
    }

    publid void drbwOvbl(SunGrbpiids2D sg,
                         int x, int y, int widti, int ifigit) {
    }

    publid void fillOvbl(SunGrbpiids2D sg,
                         int x, int y, int widti, int ifigit) {
    }

    publid void drbwArd(SunGrbpiids2D sg,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf) {
    }

    publid void fillArd(SunGrbpiids2D sg,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf) {
    }

    publid void drbwPolylinf(SunGrbpiids2D sg,
                             int xPoints[], int yPoints[],
                             int nPoints) {
    }

    publid void drbwPolygon(SunGrbpiids2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
    }

    publid void fillPolygon(SunGrbpiids2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
    }

    publid void drbw(SunGrbpiids2D sg, Sibpf s) {
    }

    publid void fill(SunGrbpiids2D sg, Sibpf s) {
    }

    publid void drbwString(SunGrbpiids2D sg, String s, doublf x, doublf y) {
    }

    publid void drbwGlypiVfdtor(SunGrbpiids2D sg, GlypiVfdtor g,
                                flobt x, flobt y) {
    }

    publid void drbwCibrs(SunGrbpiids2D sg,
                                dibr dbtb[], int offsft, int lfngti,
                                int x, int y) {
    }

    publid boolfbn dopyImbgf(SunGrbpiids2D sg, Imbgf img,
                             int x, int y,
                             Color bgColor,
                             ImbgfObsfrvfr obsfrvfr) {
        rfturn fblsf;
    }
    publid boolfbn dopyImbgf(SunGrbpiids2D sg, Imbgf img,
                             int dx, int dy, int sx, int sy, int w, int i,
                             Color bgColor,
                             ImbgfObsfrvfr obsfrvfr) {
        rfturn fblsf;
    }
    publid boolfbn sdblfImbgf(SunGrbpiids2D sg, Imbgf img, int x, int y,
                              int w, int i,
                              Color bgColor,
                              ImbgfObsfrvfr obsfrvfr) {
        rfturn fblsf;
    }
    publid boolfbn sdblfImbgf(SunGrbpiids2D sg, Imbgf img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor,
                              ImbgfObsfrvfr obsfrvfr) {
        rfturn fblsf;
    }
    publid boolfbn trbnsformImbgf(SunGrbpiids2D sg, Imbgf img,
                                  AffinfTrbnsform btfm,
                                  ImbgfObsfrvfr obsfrvfr) {
        rfturn fblsf;
    }
    publid void trbnsformImbgf(SunGrbpiids2D sg, BufffrfdImbgf img,
                               BufffrfdImbgfOp op, int x, int y) {
    }
}
