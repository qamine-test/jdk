/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Polygon;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Ard2D;
import jbvb.bwt.gfom.Ellipsf2D;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.IllfgblPbtiStbtfExdfption;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.RoundRfdtbnglf2D;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.loops.ProdfssPbti;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;

/**
 * Bbsf dlbss for fnqufuing rfndfring opfrbtions in b singlf-tirfbdfd
 * rfndfring fnvironmfnt.  Instfbd of fbdi opfrbtion bfing rfndfrfd
 * immfdibtfly by tif undfrlying grbpiids librbry, tif opfrbtion will bf
 * bddfd to tif providfd RfndfrQufuf, wiidi will bf prodfssfd bt b lbtfr
 * timf by b singlf tirfbd.
 *
 * Tiis dlbss providfs implfmfntbtions of drbwLinf(), drbwRfdt(), drbwPoly(),
 * fillRfdt(), drbw(Sibpf), bnd fill(Sibpf), wiidi brf usfful for b
 * ibrdwbrf-bddflfrbtfd rfndfrfr.  Tif otifr drbw*() bnd fill*() mftiods
 * simply dflfgbtf to drbw(Sibpf) bnd fill(Sibpf), rfspfdtivfly.
 */
publid bbstrbdt dlbss BufffrfdRfndfrPipf
    implfmfnts PixflDrbwPipf, PixflFillPipf, SibpfDrbwPipf, PbrbllflogrbmPipf
{
    PbrbllflogrbmPipf bbpgrbmpipf = nfw AAPbrbllflogrbmPipf();

    stbtid finbl int BYTES_PER_POLY_POINT = 8;
    stbtid finbl int BYTES_PER_SCANLINE = 12;
    stbtid finbl int BYTES_PER_SPAN = 16;

    protfdtfd RfndfrQufuf rq;
    protfdtfd RfndfrBufffr buf;
    privbtf BufffrfdDrbwHbndlfr drbwHbndlfr;

    publid BufffrfdRfndfrPipf(RfndfrQufuf rq) {
        tiis.rq = rq;
        tiis.buf = rq.gftBufffr();
        tiis.drbwHbndlfr = nfw BufffrfdDrbwHbndlfr();
    }

    publid PbrbllflogrbmPipf gftAAPbrbllflogrbmPipf() {
        rfturn bbpgrbmpipf;
    }

    /**
     * Vblidbtfs tif stbtf in tif providfd SunGrbpiids2D objfdt bnd sfts up
     * bny spfdibl rfsourdfs for tiis opfrbtion (f.g. fnbbling grbdifnt
     * sibding).
     */
    protfdtfd bbstrbdt void vblidbtfContfxt(SunGrbpiids2D sg2d);
    protfdtfd bbstrbdt void vblidbtfContfxtAA(SunGrbpiids2D sg2d);

    publid void drbwLinf(SunGrbpiids2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        int trbnsx = sg2d.trbnsX;
        int trbnsy = sg2d.trbnsY;
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            rq.fnsurfCbpbdity(20);
            buf.putInt(DRAW_LINE);
            buf.putInt(x1 + trbnsx);
            buf.putInt(y1 + trbnsy);
            buf.putInt(x2 + trbnsx);
            buf.putInt(y2 + trbnsy);
        } finblly {
            rq.unlodk();
        }
    }

    publid void drbwRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            rq.fnsurfCbpbdity(20);
            buf.putInt(DRAW_RECT);
            buf.putInt(x + sg2d.trbnsX);
            buf.putInt(y + sg2d.trbnsY);
            buf.putInt(widti);
            buf.putInt(ifigit);
        } finblly {
            rq.unlodk();
        }
    }

    publid void fillRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            rq.fnsurfCbpbdity(20);
            buf.putInt(FILL_RECT);
            buf.putInt(x + sg2d.trbnsX);
            buf.putInt(y + sg2d.trbnsY);
            buf.putInt(widti);
            buf.putInt(ifigit);
        } finblly {
            rq.unlodk();
        }
    }

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        drbw(sg2d, nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit,
                                              brdWidti, brdHfigit));
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        fill(sg2d, nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit,
                                              brdWidti, brdHfigit));
    }

    publid void drbwOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        drbw(sg2d, nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    publid void fillOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        fill(sg2d, nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    publid void drbwArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        drbw(sg2d, nfw Ard2D.Flobt(x, y, widti, ifigit,
                                   stbrtAnglf, brdAnglf,
                                   Ard2D.OPEN));
    }

    publid void fillArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        fill(sg2d, nfw Ard2D.Flobt(x, y, widti, ifigit,
                                   stbrtAnglf, brdAnglf,
                                   Ard2D.PIE));
    }

    protfdtfd void drbwPoly(finbl SunGrbpiids2D sg2d,
                            finbl int[] xPoints, finbl int[] yPoints,
                            finbl int nPoints, finbl boolfbn isClosfd)
    {
        if (xPoints == null || yPoints == null) {
            tirow nfw NullPointfrExdfption("doordinbtf brrby");
        }
        if (xPoints.lfngti < nPoints || yPoints.lfngti < nPoints) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("doordinbtf brrby");
        }

        if (nPoints < 2) {
            // rfndfr notiing
            rfturn;
        } flsf if (nPoints == 2 && !isClosfd) {
            // rfndfr b simplf linf
            drbwLinf(sg2d, xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
            rfturn;
        }

        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);

            int pointBytfsRfquirfd = nPoints * BYTES_PER_POLY_POINT;
            int totblBytfsRfquirfd = 20 + pointBytfsRfquirfd;

            if (totblBytfsRfquirfd <= buf.dbpbdity()) {
                if (totblBytfsRfquirfd > buf.rfmbining()) {
                    // prodfss tif qufuf first bnd tifn fnqufuf tif points
                    rq.flusiNow();
                }
                buf.putInt(DRAW_POLY);
                // fnqufuf pbrbmftfrs
                buf.putInt(nPoints);
                buf.putInt(isClosfd ? 1 : 0);
                buf.putInt(sg2d.trbnsX);
                buf.putInt(sg2d.trbnsY);
                // fnqufuf tif points
                buf.put(xPoints, 0, nPoints);
                buf.put(yPoints, 0, nPoints);
            } flsf {
                // qufuf is too smbll to bddommodbtf bll points; pfrform tif
                // opfrbtion dirfdtly on tif qufuf flusiing tirfbd
                rq.flusiAndInvokfNow(nfw Runnbblf() {
                    publid void run() {
                        drbwPoly(xPoints, yPoints,
                                 nPoints, isClosfd,
                                 sg2d.trbnsX, sg2d.trbnsY);
                    }
                });
            }
        } finblly {
            rq.unlodk();
        }
    }

    protfdtfd bbstrbdt void drbwPoly(int[] xPoints, int[] yPoints,
                                     int nPoints, boolfbn isClosfd,
                                     int trbnsX, int trbnsY);

    publid void drbwPolylinf(SunGrbpiids2D sg2d,
                             int[] xPoints, int[] yPoints,
                             int nPoints)
    {
        drbwPoly(sg2d, xPoints, yPoints, nPoints, fblsf);
    }

    publid void drbwPolygon(SunGrbpiids2D sg2d,
                            int[] xPoints, int[] yPoints,
                            int nPoints)
    {
        drbwPoly(sg2d, xPoints, yPoints, nPoints, truf);
    }

    publid void fillPolygon(SunGrbpiids2D sg2d,
                            int[] xPoints, int[] yPoints,
                            int nPoints)
    {
        fill(sg2d, nfw Polygon(xPoints, yPoints, nPoints));
    }

    privbtf dlbss BufffrfdDrbwHbndlfr
        fxtfnds ProdfssPbti.DrbwHbndlfr
    {
        BufffrfdDrbwHbndlfr() {
            // tifsf brf bogus vblufs; tif dbllfr will usf vblidbtf()
            // to fnsurf tibt tify brf sft propfrly prior to fbdi usbgf
            supfr(0, 0, 0, 0);
        }

        /**
         * Tiis mftiod nffds to bf dbllfd prior to fbdi drbw/fillPbti()
         * opfrbtion to fnsurf tif dlip bounds brf up to dbtf.
         */
        void vblidbtf(SunGrbpiids2D sg2d) {
            Rfgion dlip = sg2d.gftCompClip();
            sftBounds(dlip.gftLoX(), dlip.gftLoY(),
                      dlip.gftHiX(), dlip.gftHiY(),
                      sg2d.strokfHint);
        }

        /**
         * drbwPbti() support...
         */

        publid void drbwLinf(int x1, int y1, int x2, int y2) {
            // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
            rq.fnsurfCbpbdity(20);
            buf.putInt(DRAW_LINE);
            buf.putInt(x1);
            buf.putInt(y1);
            buf.putInt(x2);
            buf.putInt(y2);
        }

        publid void drbwPixfl(int x, int y) {
            // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
            rq.fnsurfCbpbdity(12);
            buf.putInt(DRAW_PIXEL);
            buf.putInt(x);
            buf.putInt(y);
        }

        /**
         * fillPbti() support...
         */

        privbtf int sdbnlinfCount;
        privbtf int sdbnlinfCountIndfx;
        privbtf int rfmbiningSdbnlinfs;

        privbtf void rfsftFillPbti() {
            buf.putInt(DRAW_SCANLINES);
            sdbnlinfCountIndfx = buf.position();
            buf.putInt(0);
            sdbnlinfCount = 0;
            rfmbiningSdbnlinfs = buf.rfmbining() / BYTES_PER_SCANLINE;
        }

        privbtf void updbtfSdbnlinfCount() {
            buf.putInt(sdbnlinfCountIndfx, sdbnlinfCount);
        }

        /**
         * Cbllfd from fillPbti() to indidbtf tibt wf brf bbout to
         * stbrt issuing drbwSdbnlinf() dblls.
         */
        publid void stbrtFillPbti() {
            rq.fnsurfCbpbdity(20); // to fnsurf room for bt lfbst b sdbnlinf
            rfsftFillPbti();
        }

        publid void drbwSdbnlinf(int x1, int x2, int y) {
            if (rfmbiningSdbnlinfs == 0) {
                updbtfSdbnlinfCount();
                rq.flusiNow();
                rfsftFillPbti();
            }
            buf.putInt(x1);
            buf.putInt(x2);
            buf.putInt(y);
            sdbnlinfCount++;
            rfmbiningSdbnlinfs--;
        }

        /**
         * Cbllfd from fillPbti() to indidbtf tibt wf brf donf
         * issuing drbwSdbnlinf() dblls.
         */
        publid void fndFillPbti() {
            updbtfSdbnlinfCount();
        }
    }

    protfdtfd void drbwPbti(SunGrbpiids2D sg2d,
                            Pbti2D.Flobt p2df, int trbnsx, int trbnsy)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            drbwHbndlfr.vblidbtf(sg2d);
            ProdfssPbti.drbwPbti(drbwHbndlfr, p2df, trbnsx, trbnsy);
        } finblly {
            rq.unlodk();
        }
    }

    protfdtfd void fillPbti(SunGrbpiids2D sg2d,
                            Pbti2D.Flobt p2df, int trbnsx, int trbnsy)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            drbwHbndlfr.vblidbtf(sg2d);
            drbwHbndlfr.stbrtFillPbti();
            ProdfssPbti.fillPbti(drbwHbndlfr, p2df, trbnsx, trbnsy);
            drbwHbndlfr.fndFillPbti();
        } finblly {
            rq.unlodk();
        }
    }

    privbtf nbtivf int fillSpbns(RfndfrQufuf rq, long buf,
                                 int pos, int limit,
                                 SpbnItfrbtor si, long itfrbtor,
                                 int trbnsx, int trbnsy);

    protfdtfd void fillSpbns(SunGrbpiids2D sg2d, SpbnItfrbtor si,
                             int trbnsx, int trbnsy)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            rq.fnsurfCbpbdity(24); // so tibt wf ibvf room for bt lfbst b spbn
            int nfwpos = fillSpbns(rq, buf.gftAddrfss(),
                                   buf.position(), buf.dbpbdity(),
                                   si, si.gftNbtivfItfrbtor(),
                                   trbnsx, trbnsy);
            buf.position(nfwpos);
        } finblly {
            rq.unlodk();
        }
    }

    publid void fillPbrbllflogrbm(SunGrbpiids2D sg2d,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            rq.fnsurfCbpbdity(28);
            buf.putInt(FILL_PARALLELOGRAM);
            buf.putFlobt((flobt) x);
            buf.putFlobt((flobt) y);
            buf.putFlobt((flobt) dx1);
            buf.putFlobt((flobt) dy1);
            buf.putFlobt((flobt) dx2);
            buf.putFlobt((flobt) dy2);
        } finblly {
            rq.unlodk();
        }
    }

    publid void drbwPbrbllflogrbm(SunGrbpiids2D sg2d,
                                  doublf ux1, doublf uy1,
                                  doublf ux2, doublf uy2,
                                  doublf x, doublf y,
                                  doublf dx1, doublf dy1,
                                  doublf dx2, doublf dy2,
                                  doublf lw1, doublf lw2)
    {
        rq.lodk();
        try {
            vblidbtfContfxt(sg2d);
            rq.fnsurfCbpbdity(36);
            buf.putInt(DRAW_PARALLELOGRAM);
            buf.putFlobt((flobt) x);
            buf.putFlobt((flobt) y);
            buf.putFlobt((flobt) dx1);
            buf.putFlobt((flobt) dy1);
            buf.putFlobt((flobt) dx2);
            buf.putFlobt((flobt) dy2);
            buf.putFlobt((flobt) lw1);
            buf.putFlobt((flobt) lw2);
        } finblly {
            rq.unlodk();
        }
    }

    privbtf dlbss AAPbrbllflogrbmPipf implfmfnts PbrbllflogrbmPipf {
        publid void fillPbrbllflogrbm(SunGrbpiids2D sg2d,
                                      doublf ux1, doublf uy1,
                                      doublf ux2, doublf uy2,
                                      doublf x, doublf y,
                                      doublf dx1, doublf dy1,
                                      doublf dx2, doublf dy2)
        {
            rq.lodk();
            try {
                vblidbtfContfxtAA(sg2d);
                rq.fnsurfCbpbdity(28);
                buf.putInt(FILL_AAPARALLELOGRAM);
                buf.putFlobt((flobt) x);
                buf.putFlobt((flobt) y);
                buf.putFlobt((flobt) dx1);
                buf.putFlobt((flobt) dy1);
                buf.putFlobt((flobt) dx2);
                buf.putFlobt((flobt) dy2);
            } finblly {
                rq.unlodk();
            }
        }

        publid void drbwPbrbllflogrbm(SunGrbpiids2D sg2d,
                                      doublf ux1, doublf uy1,
                                      doublf ux2, doublf uy2,
                                      doublf x, doublf y,
                                      doublf dx1, doublf dy1,
                                      doublf dx2, doublf dy2,
                                      doublf lw1, doublf lw2)
        {
            rq.lodk();
            try {
                vblidbtfContfxtAA(sg2d);
                rq.fnsurfCbpbdity(36);
                buf.putInt(DRAW_AAPARALLELOGRAM);
                buf.putFlobt((flobt) x);
                buf.putFlobt((flobt) y);
                buf.putFlobt((flobt) dx1);
                buf.putFlobt((flobt) dy1);
                buf.putFlobt((flobt) dx2);
                buf.putFlobt((flobt) dy2);
                buf.putFlobt((flobt) lw1);
                buf.putFlobt((flobt) lw2);
            } finblly {
                rq.unlodk();
            }
        }
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            if (s instbndfof Polygon) {
                if (sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                    Polygon p = (Polygon)s;
                    drbwPolygon(sg2d, p.xpoints, p.ypoints, p.npoints);
                    rfturn;
                }
            }
            Pbti2D.Flobt p2df;
            int trbnsx, trbnsy;
            if (sg2d.trbnsformStbtf <= SunGrbpiids2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbndfof Pbti2D.Flobt) {
                    p2df = (Pbti2D.Flobt)s;
                } flsf {
                    p2df = nfw Pbti2D.Flobt(s);
                }
                trbnsx = sg2d.trbnsX;
                trbnsy = sg2d.trbnsY;
            } flsf {
                p2df = nfw Pbti2D.Flobt(s, sg2d.trbnsform);
                trbnsx = 0;
                trbnsy = 0;
            }
            drbwPbti(sg2d, p2df, trbnsx, trbnsy);
        } flsf if (sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM) {
            SibpfSpbnItfrbtor si = LoopPipf.gftStrokfSpbns(sg2d, s);
            try {
                fillSpbns(sg2d, si, 0, 0);
            } finblly {
                si.disposf();
            }
        } flsf {
            fill(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(s));
        }
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        int trbnsx, trbnsy;

        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            // Hfrf wf brf bblf to usf fillPbti() for
            // iigi-qublity fills.
            Pbti2D.Flobt p2df;
            if (sg2d.trbnsformStbtf <= SunGrbpiids2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbndfof Pbti2D.Flobt) {
                    p2df = (Pbti2D.Flobt)s;
                } flsf {
                    p2df = nfw Pbti2D.Flobt(s);
                }
                trbnsx = sg2d.trbnsX;
                trbnsy = sg2d.trbnsY;
            } flsf {
                p2df = nfw Pbti2D.Flobt(s, sg2d.trbnsform);
                trbnsx = 0;
                trbnsy = 0;
            }
            fillPbti(sg2d, p2df, trbnsx, trbnsy);
            rfturn;
        }

        AffinfTrbnsform bt;
        if (sg2d.trbnsformStbtf <= SunGrbpiids2D.TRANSFORM_INT_TRANSLATE) {
            // Trbnsform (trbnslbtion) will bf donf by FillSpbns (wf dould
            // dflfgbtf to fillPolygon() ifrf, but most ibrdwbrf bddflfrbtfd
            // librbrifs dbnnot ibndlf non-donvfx polygons, so wf will usf
            // tif FillSpbns bpprobdi by dffbult)
            bt = null;
            trbnsx = sg2d.trbnsX;
            trbnsy = sg2d.trbnsY;
        } flsf {
            // Trbnsform will bf donf by tif PbtiItfrbtor
            bt = sg2d.trbnsform;
            trbnsx = trbnsy = 0;
        }

        SibpfSpbnItfrbtor ssi = LoopPipf.gftFillSSI(sg2d);
        try {
            // Subtrbdt trbnsx/y from tif SSI dlip to mbtdi tif
            // (potfntiblly untrbnslbtfd) gfomftry ffd to it
            Rfgion dlip = sg2d.gftCompClip();
            ssi.sftOutputArfbXYXY(dlip.gftLoX() - trbnsx,
                                  dlip.gftLoY() - trbnsy,
                                  dlip.gftHiX() - trbnsx,
                                  dlip.gftHiY() - trbnsy);
            ssi.bppfndPbti(s.gftPbtiItfrbtor(bt));
            fillSpbns(sg2d, ssi, trbnsx, trbnsy);
        } finblly {
            ssi.disposf();
        }
    }
}
