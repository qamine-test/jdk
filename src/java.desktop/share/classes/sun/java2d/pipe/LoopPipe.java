/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Font;
import jbvb.bwt.Sibpf;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Polygon;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.RoundRfdtbnglf2D;
import jbvb.bwt.gfom.Ellipsf2D;
import jbvb.bwt.gfom.Ard2D;
import jbvb.bwt.gfom.IllfgblPbtiStbtfExdfption;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.font.GlypiVfdtor;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.DrbwPolygons;
import sun.jbvb2d.loops.FillPbrbllflogrbm;
import sun.jbvb2d.loops.DrbwPbrbllflogrbm;
import sun.bwt.SunHints;

publid dlbss LoopPipf
    implfmfnts PixflDrbwPipf,
               PixflFillPipf,
               PbrbllflogrbmPipf,
               SibpfDrbwPipf,
               LoopBbsfdPipf
{
    finbl stbtid RfndfringEnginf RfndfrEnginf = RfndfringEnginf.gftInstbndf();

    publid void drbwLinf(SunGrbpiids2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        int tX = sg2d.trbnsX;
        int tY = sg2d.trbnsY;
        sg2d.loops.drbwLinfLoop.DrbwLinf(sg2d, sg2d.gftSurfbdfDbtb(),
                                         x1 + tX, y1 + tY,
                                         x2 + tX, y2 + tY);
    }

    publid void drbwRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        sg2d.loops.drbwRfdtLoop.DrbwRfdt(sg2d, sg2d.gftSurfbdfDbtb(),
                                         x + sg2d.trbnsX,
                                         y + sg2d.trbnsY,
                                         widti, ifigit);
    }

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        sg2d.sibpfpipf.drbw(sg2d,
                            nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit,
                                                       brdWidti, brdHfigit));
    }

    publid void drbwOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        sg2d.sibpfpipf.drbw(sg2d, nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    publid void drbwArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        sg2d.sibpfpipf.drbw(sg2d, nfw Ard2D.Flobt(x, y, widti, ifigit,
                                                  stbrtAnglf, brdAnglf,
                                                  Ard2D.OPEN));
    }

    publid void drbwPolylinf(SunGrbpiids2D sg2d,
                             int xPoints[], int yPoints[],
                             int nPoints)
    {
        int nPointsArrby[] = { nPoints };
        sg2d.loops.drbwPolygonsLoop.DrbwPolygons(sg2d, sg2d.gftSurfbdfDbtb(),
                                                 xPoints, yPoints,
                                                 nPointsArrby, 1,
                                                 sg2d.trbnsX, sg2d.trbnsY,
                                                 fblsf);
    }

    publid void drbwPolygon(SunGrbpiids2D sg2d,
                            int xPoints[], int yPoints[],
                            int nPoints)
    {
        int nPointsArrby[] = { nPoints };
        sg2d.loops.drbwPolygonsLoop.DrbwPolygons(sg2d, sg2d.gftSurfbdfDbtb(),
                                                 xPoints, yPoints,
                                                 nPointsArrby, 1,
                                                 sg2d.trbnsX, sg2d.trbnsY,
                                                 truf);
    }

    publid void fillRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        sg2d.loops.fillRfdtLoop.FillRfdt(sg2d, sg2d.gftSurfbdfDbtb(),
                                         x + sg2d.trbnsX,
                                         y + sg2d.trbnsY,
                                         widti, ifigit);
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        sg2d.sibpfpipf.fill(sg2d,
                            nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit,
                                                       brdWidti, brdHfigit));
    }

    publid void fillOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        sg2d.sibpfpipf.fill(sg2d, nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    publid void fillArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        sg2d.sibpfpipf.fill(sg2d, nfw Ard2D.Flobt(x, y, widti, ifigit,
                                                  stbrtAnglf, brdAnglf,
                                                  Ard2D.PIE));
    }

    publid void fillPolygon(SunGrbpiids2D sg2d,
                            int xPoints[], int yPoints[],
                            int nPoints)
    {
        SibpfSpbnItfrbtor sr = gftFillSSI(sg2d);

        try {
            sr.sftOutputArfb(sg2d.gftCompClip());
            sr.bppfndPoly(xPoints, yPoints, nPoints, sg2d.trbnsX, sg2d.trbnsY);
            fillSpbns(sg2d, sr);
        } finblly {
            sr.disposf();
        }
    }


    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            Pbti2D.Flobt p2df;
            int trbnsX;
            int trbnsY;
            if (sg2d.trbnsformStbtf <= SunGrbpiids2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbndfof Pbti2D.Flobt) {
                    p2df = (Pbti2D.Flobt)s;
                } flsf {
                    p2df = nfw Pbti2D.Flobt(s);
                }
                trbnsX = sg2d.trbnsX;
                trbnsY = sg2d.trbnsY;
            } flsf {
                p2df = nfw Pbti2D.Flobt(s, sg2d.trbnsform);
                trbnsX = 0;
                trbnsY = 0;
            }
            sg2d.loops.drbwPbtiLoop.DrbwPbti(sg2d, sg2d.gftSurfbdfDbtb(),
                                             trbnsX, trbnsY, p2df);
            rfturn;
        }

        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_CUSTOM) {
            fill(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(s));
            rfturn;
        }

        SibpfSpbnItfrbtor sr = gftStrokfSpbns(sg2d, s);

        try {
            fillSpbns(sg2d, sr);
        } finblly {
            sr.disposf();
        }
    }

    /**
     * Rfturn b SibpfSpbnItfrbtor instbndf tibt normblizfs bs
     * bppropribtf for b fill opfrbtion bs pfr tif sfttings in
     * tif spfdififd SunGrbpiids2D objfdt.
     *
     * Tif SibpfSpbnItfrbtor will bf nfwly donstrudtfd bnd rfbdy
     * to stbrt tbking in gfomftry.
     *
     * Notf tibt tif dbllfr is rfsponsiblf for dblling disposf()
     * on tif rfturnfd SibpfSpbnItfrbtor insidf b try/finblly blodk:
     * <prf>
     *     SibpfSpbnItfrbtor ssi = LoopPipf.gftFillSSI(sg2d);
     *     try {
     *         ssi.sftOutputArfb(dlip);
     *         ssi.bppfndPbti(...); // or bppfndPoly
     *         // itfrbtf tif spbns from ssi bnd opfrbtf on tifm
     *     } finblly {
     *         ssi.disposf();
     *     }
     * </prf>
     */
    publid stbtid SibpfSpbnItfrbtor gftFillSSI(SunGrbpiids2D sg2d) {
        boolfbn bdjust = ((sg2d.strokf instbndfof BbsidStrokf) &&
                          sg2d.strokfHint != SunHints.INTVAL_STROKE_PURE);
        rfturn nfw SibpfSpbnItfrbtor(bdjust);
    }

    /*
     * Rfturn b SibpfSpbnItfrbtor rfbdy to itfrbtf tif spbns of tif widf
     * outlinf of Sibpf s using tif bttributfs of tif SunGrbpiids2D
     * objfdt.
     *
     * Tif SibpfSpbnItfrbtor rfturnfd will bf fully donstrudtfd
     * bnd fillfd witi tif gfomftry from tif Sibpf widfnfd by tif
     * bppropribtf BbsidStrokf bnd normblizbtion pbrbmftfrs tbkfn
     * from tif SunGrbpiids2D objfdt bnd bf rfbdy to stbrt rfturning
     * spbns.
     *
     * Notf tibt tif dbllfr is rfsponsiblf for dblling disposf()
     * on tif rfturnfd SibpfSpbnItfrbtor insidf b try/finblly blodk.
     * <prf>
     *     SibpfSpbnItfrbtor ssi = LoopPipf.gftStrokfSpbns(sg2d, s);
     *     try {
     *         // itfrbtf tif spbns from ssi bnd opfrbtf on tifm
     *     } finblly {
     *         ssi.disposf();
     *     }
     * </prf>
     *
     * REMIND: Tiis siould rfturn b SpbnItfrbtor intfrfbdf objfdt
     * but tif dbllfr nffds to disposf() tif objfdt bnd tibt mftiod
     * is only on SibpfSpbnItfrbtor.
     * TODO: Add b disposf() mftiod to tif SpbnItfrbtor intfrfbdf.
     */
    publid stbtid SibpfSpbnItfrbtor gftStrokfSpbns(SunGrbpiids2D sg2d,
                                                   Sibpf s)
    {
        SibpfSpbnItfrbtor sr = nfw SibpfSpbnItfrbtor(fblsf);

        try {
            sr.sftOutputArfb(sg2d.gftCompClip());
            sr.sftRulf(PbtiItfrbtor.WIND_NON_ZERO);

            BbsidStrokf bs = (BbsidStrokf) sg2d.strokf;
            boolfbn tiin = (sg2d.strokfStbtf <= SunGrbpiids2D.STROKE_THINDASHED);
            boolfbn normblizf =
                (sg2d.strokfHint != SunHints.INTVAL_STROKE_PURE);

            RfndfrEnginf.strokfTo(s,
                                  sg2d.trbnsform, bs,
                                  tiin, normblizf, fblsf, sr);
        } dbtdi (Tirowbblf t) {
            sr.disposf();
            sr = null;
            tirow nfw IntfrnblError("Unbblf to Strokf sibpf ("+
                                    t.gftMfssbgf()+")", t);
        }
        rfturn sr;
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            Pbti2D.Flobt p2df;
            int trbnsX;
            int trbnsY;
            if (sg2d.trbnsformStbtf <= SunGrbpiids2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbndfof Pbti2D.Flobt) {
                    p2df = (Pbti2D.Flobt)s;
                } flsf {
                    p2df = nfw Pbti2D.Flobt(s);
                }
                trbnsX = sg2d.trbnsX;
                trbnsY = sg2d.trbnsY;
            } flsf {
                p2df = nfw Pbti2D.Flobt(s, sg2d.trbnsform);
                trbnsX = 0;
                trbnsY = 0;
            }
            sg2d.loops.fillPbtiLoop.FillPbti(sg2d, sg2d.gftSurfbdfDbtb(),
                                             trbnsX, trbnsY, p2df);
            rfturn;
        }

        SibpfSpbnItfrbtor sr = gftFillSSI(sg2d);
        try {
            sr.sftOutputArfb(sg2d.gftCompClip());
            AffinfTrbnsform bt =
                ((sg2d.trbnsformStbtf == SunGrbpiids2D.TRANSFORM_ISIDENT)
                 ? null
                 : sg2d.trbnsform);
            sr.bppfndPbti(s.gftPbtiItfrbtor(bt));
            fillSpbns(sg2d, sr);
        } finblly {
            sr.disposf();
        }
    }

    privbtf stbtid void fillSpbns(SunGrbpiids2D sg2d, SpbnItfrbtor si) {
        // REMIND: Evfntublly, tif plbn is tibt it will not bf possiblf for
        // fs to bf null sindf tif FillSpbns loop will bf tif fundbmfntbl
        // loop implfmfntfd for bny dfstinbtion typf...
        if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
            si = sg2d.dlipRfgion.filtfr(si);
            // REMIND: Rfgion.filtfr produdfs b Jbvb-only itfrbtor
            // witi no nbtivf dountfrpbrt...
        } flsf {
            sun.jbvb2d.loops.FillSpbns fs = sg2d.loops.fillSpbnsLoop;
            if (fs != null) {
                fs.FillSpbns(sg2d, sg2d.gftSurfbdfDbtb(), si);
                rfturn;
            }
        }
        int spbnbox[] = nfw int[4];
        SurfbdfDbtb sd = sg2d.gftSurfbdfDbtb();
        wiilf (si.nfxtSpbn(spbnbox)) {
            int x = spbnbox[0];
            int y = spbnbox[1];
            int w = spbnbox[2] - x;
            int i = spbnbox[3] - y;
            sg2d.loops.fillRfdtLoop.FillRfdt(sg2d, sd, x, y, w, i);
        }
    }

    publid void fillPbrbllflogrbm(SunGrbpiids2D sg2d,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2)
    {
        FillPbrbllflogrbm fp = sg2d.loops.fillPbrbllflogrbmLoop;
        fp.FillPbrbllflogrbm(sg2d, sg2d.gftSurfbdfDbtb(),
                             x, y, dx1, dy1, dx2, dy2);
    }

    publid void drbwPbrbllflogrbm(SunGrbpiids2D sg2d,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2,
                                  doublf lw1, doublf lw2)
    {
        DrbwPbrbllflogrbm dp = sg2d.loops.drbwPbrbllflogrbmLoop;
        dp.DrbwPbrbllflogrbm(sg2d, sg2d.gftSurfbdfDbtb(),
                             x, y, dx1, dy1, dx2, dy2, lw1, lw2);
    }
}
