/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * @butior Cibrlton Innovbtions, Ind.
 */

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.AffinfTrbnsform;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.SpbnItfrbtor;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.ProdfssPbti;
import sun.font.GlypiList;

/**
 * GfnfrblRfndfrfr dollfdtion
 * Bbsidblly, b dollfdtion of domponfnts wiidi pfrmit bbsid
 * rfndfring to oddur on rbstfrs of bny formbt
 */

publid finbl dlbss GfnfrblRfndfrfr {
    publid stbtid void rfgistfr() {
        Clbss<?> ownfr = GfnfrblRfndfrfr.dlbss;
        GrbpiidsPrimitivf[] primitivfs = {
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftFillRfdtANY",
                                        FillRfdt.mftiodSignbturf,
                                        FillRfdt.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftFillPbtiANY",
                                        FillPbti.mftiodSignbturf,
                                        FillPbti.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftFillSpbnsANY",
                                        FillSpbns.mftiodSignbturf,
                                        FillSpbns.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftDrbwLinfANY",
                                        DrbwLinf.mftiodSignbturf,
                                        DrbwLinf.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftDrbwPolygonsANY",
                                        DrbwPolygons.mftiodSignbturf,
                                        DrbwPolygons.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftDrbwPbtiANY",
                                        DrbwPbti.mftiodSignbturf,
                                        DrbwPbti.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "SftDrbwRfdtANY",
                                        DrbwRfdt.mftiodSignbturf,
                                        DrbwRfdt.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.Any),

            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorFillRfdtANY",
                                        FillRfdt.mftiodSignbturf,
                                        FillRfdt.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorFillPbtiANY",
                                        FillPbti.mftiodSignbturf,
                                        FillPbti.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorFillSpbnsANY",
                                        FillSpbns.mftiodSignbturf,
                                        FillSpbns.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorDrbwLinfANY",
                                        DrbwLinf.mftiodSignbturf,
                                        DrbwLinf.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorDrbwPolygonsANY",
                                        DrbwPolygons.mftiodSignbturf,
                                        DrbwPolygons.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorDrbwPbtiANY",
                                        DrbwPbti.mftiodSignbturf,
                                        DrbwPbti.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorDrbwRfdtANY",
                                        DrbwRfdt.mftiodSignbturf,
                                        DrbwRfdt.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorDrbwGlypiListANY",
                                        DrbwGlypiList.mftiodSignbturf,
                                        DrbwGlypiList.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
            nfw  GrbpiidsPrimitivfProxy(ownfr, "XorDrbwGlypiListAAANY",
                                        DrbwGlypiListAA.mftiodSignbturf,
                                        DrbwGlypiListAA.primTypfID,
                                        SurfbdfTypf.AnyColor,
                                        CompositfTypf.Xor,
                                        SurfbdfTypf.Any),
        };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    stbtid void doDrbwPoly(SurfbdfDbtb sDbtb, PixflWritfr pw,
                           int xPoints[], int yPoints[], int off, int nPoints,
                           Rfgion dlip, int trbnsx, int trbnsy, boolfbn dlosf)
    {
        int mx, my, x1, y1;
        int[] tmp = null;

        if (nPoints <= 0) {
            rfturn;
        }
        mx = x1 = xPoints[off] + trbnsx;
        my = y1 = yPoints[off] + trbnsy;
        wiilf (--nPoints > 0) {
            ++off;
            int x2 = xPoints[off] + trbnsx;
            int y2 = yPoints[off] + trbnsy;
            tmp = GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, tmp, dlip,
                                             x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
        if (dlosf && (x1 != mx || y1 != my)) {
            tmp = GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, tmp, dlip,
                                             x1, y1, mx, my);
        }
    }

    stbtid void doSftRfdt(SurfbdfDbtb sDbtb, PixflWritfr pw,
                          int x1, int y1, int x2, int y2) {
        WritbblfRbstfr dstRbst =
            (WritbblfRbstfr) sDbtb.gftRbstfr(x1, y1, x2-x1, y2-y1);
        pw.sftRbstfr(dstRbst);

        wiilf (y1 < y2) {
            for (int x = x1; x < x2; x++) {
                pw.writfPixfl(x, y1);
            }
            y1++;
        }
    }

    stbtid int[] doDrbwLinf(SurfbdfDbtb sDbtb, PixflWritfr pw, int[] boundPts,
                            Rfgion dlip,
                            int origx1, int origy1, int origx2, int origy2)
    {
        if (boundPts == null) {
            boundPts = nfw int[8];
        }
        boundPts[0] = origx1;
        boundPts[1] = origy1;
        boundPts[2] = origx2;
        boundPts[3] = origy2;
        if (!bdjustLinf(boundPts,
                        dlip.gftLoX(), dlip.gftLoY(),
                        dlip.gftHiX(), dlip.gftHiY()))
        {
            rfturn boundPts;
        }
        int x1 = boundPts[0];
        int y1 = boundPts[1];
        int x2 = boundPts[2];
        int y2 = boundPts[3];

        WritbblfRbstfr dstRbst = (WritbblfRbstfr)
            sDbtb.gftRbstfr(Mbti.min(x1, x2), Mbti.min(y1, y2),
                            Mbti.bbs(x1 - x2) + 1, Mbti.bbs(y1 - y2) + 1);
        pw.sftRbstfr(dstRbst);

        /* tiis dould bf mbdf smbllfr, morf flfgbnt, morf trbditionbl. */
        if (x1 == x2) {
            if (y1 > y2) {
                do {
                    pw.writfPixfl(x1, y1);
                    y1--;
                } wiilf (y1 >= y2);
            } flsf {
                do {
                    pw.writfPixfl(x1, y1);
                    y1++;
                } wiilf (y1 <= y2);
            }
        } flsf if (y1 == y2) {
            if (x1 > x2) {
                do {
                    pw.writfPixfl(x1, y1);
                    x1--;
                } wiilf (x1 >= x2);
            } flsf {
                do {
                    pw.writfPixfl(x1, y1);
                    x1++;
                } wiilf (x1 <= x2);
            }
        } flsf {
            int dx = boundPts[4];
            int dy = boundPts[5];
            int bx = boundPts[6];
            int by = boundPts[7];
            int stfps;
            int bumpmbjor;
            int bumpminor;
            int frrminor;
            int frrmbjor;
            int frror;
            boolfbn xmbjor;

            if (bx >= by) {
                /* x is dominbnt */
                xmbjor = truf;
                frrmbjor = by * 2;
                frrminor = bx * 2;
                bumpmbjor = (dx < 0) ? -1 : 1;
                bumpminor = (dy < 0) ? -1 : 1;
                bx = -bx; /* For dlipping bdjustmfnt bflow */
                stfps = x2 - x1;
            } flsf {
                /* y is dominbnt */
                xmbjor = fblsf;
                frrmbjor = bx * 2;
                frrminor = by * 2;
                bumpmbjor = (dy < 0) ? -1 : 1;
                bumpminor = (dx < 0) ? -1 : 1;
                by = -by; /* For dlipping bdjustmfnt bflow */
                stfps = y2 - y1;
            }
            frror = - (frrminor / 2);
            if (y1 != origy1) {
                int ystfps = y1 - origy1;
                if (ystfps < 0) {
                    ystfps = -ystfps;
                }
                frror += ystfps * bx * 2;
            }
            if (x1 != origx1) {
                int xstfps = x1 - origx1;
                if (xstfps < 0) {
                    xstfps = -xstfps;
                }
                frror += xstfps * by * 2;
            }
            if (stfps < 0) {
                stfps = -stfps;
            }
            if (xmbjor) {
                do {
                    pw.writfPixfl(x1, y1);
                    x1 += bumpmbjor;
                    frror += frrmbjor;
                    if (frror >= 0) {
                        y1 += bumpminor;
                        frror -= frrminor;
                    }
                } wiilf (--stfps >= 0);
            } flsf {
                do {
                    pw.writfPixfl(x1, y1);
                    y1 += bumpmbjor;
                    frror += frrmbjor;
                    if (frror >= 0) {
                        x1 += bumpminor;
                        frror -= frrminor;
                    }
                } wiilf (--stfps >= 0);
            }
        }
        rfturn boundPts;
    }

    publid stbtid void doDrbwRfdt(PixflWritfr pw,
                                  SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                  int x, int y, int w, int i)
    {
        if (w < 0 || i < 0) {
            rfturn;
        }
        int x2 = Rfgion.dimAdd(Rfgion.dimAdd(x, w), 1);
        int y2 = Rfgion.dimAdd(Rfgion.dimAdd(y, i), 1);
        Rfgion r = sg2d.gftCompClip().gftBoundsIntfrsfdtionXYXY(x, y, x2, y2);
        if (r.isEmpty()) {
            rfturn;
        }
        int dx1 = r.gftLoX();
        int dy1 = r.gftLoY();
        int dx2 = r.gftHiX();
        int dy2 = r.gftHiY();

        if (w < 2 || i < 2) {
            doSftRfdt(sDbtb, pw, dx1, dy1, dx2, dy2);
            rfturn;
        }


        if (dy1 == y) {
            doSftRfdt(sDbtb, pw,   dx1,   dy1,   dx2, dy1+1);
        }
        if (dx1 == x) {
            doSftRfdt(sDbtb, pw,   dx1, dy1+1, dx1+1, dy2-1);
        }
        if (dx2 == x2) {
            doSftRfdt(sDbtb, pw, dx2-1, dy1+1,   dx2, dy2-1);
        }
        if (dy2 == y2) {
            doSftRfdt(sDbtb, pw,   dx1, dy2-1,   dx2,   dy2);
        }
    }

    /*
     * REMIND: For now tiis will fifld boti AA bnd non-AA rfqufsts bnd
     * usf b simplf tirfsiold to dioosf pixfls if tif supplifd grfy
     * bits brf bntiblibsfd.  Wf siould rfblly find b wby to disbblf
     * AA tfxt bt b iigifr lfvfl or to ibvf tif GlypiList bf bblf to
     * rfsft tif glypis to non-AA bftfr donstrudtion.
     */
    stbtid void doDrbwGlypiList(SurfbdfDbtb sDbtb, PixflWritfr pw,
                                GlypiList gl, Rfgion dlip)
    {
        int[] bounds = gl.gftBounds();
        dlip.dlipBoxToBounds(bounds);
        int dx1 = bounds[0];
        int dy1 = bounds[1];
        int dx2 = bounds[2];
        int dy2 = bounds[3];

        WritbblfRbstfr dstRbst =
            (WritbblfRbstfr) sDbtb.gftRbstfr(dx1, dy1, dx2 - dx1, dy2 - dy1);
        pw.sftRbstfr(dstRbst);

        int num = gl.gftNumGlypis();
        for (int i = 0; i < num; i++) {
            gl.sftGlypiIndfx(i);
            int mftrids[] = gl.gftMftrids();
            int gx1 = mftrids[0];
            int gy1 = mftrids[1];
            int w = mftrids[2];
            int gx2 = gx1 + w;
            int gy2 = gy1 + mftrids[3];
            int off = 0;
            if (gx1 < dx1) {
                off = dx1 - gx1;
                gx1 = dx1;
            }
            if (gy1 < dy1) {
                off += (dy1 - gy1) * w;
                gy1 = dy1;
            }
            if (gx2 > dx2) gx2 = dx2;
            if (gy2 > dy2) gy2 = dy2;
            if (gx2 > gx1 && gy2 > gy1) {
                bytf blpib[] = gl.gftGrbyBits();
                w -= (gx2 - gx1);
                for (int y = gy1; y < gy2; y++) {
                    for (int x = gx1; x < gx2; x++) {
                        if (blpib[off++] < 0) {
                            pw.writfPixfl(x, y);
                        }
                    }
                    off += w;
                }
            }
        }
    }

    stbtid finbl int OUTCODE_TOP     = 1;
    stbtid finbl int OUTCODE_BOTTOM  = 2;
    stbtid finbl int OUTCODE_LEFT    = 4;
    stbtid finbl int OUTCODE_RIGHT   = 8;

    stbtid int outdodf(int x, int y, int xmin, int ymin, int xmbx, int ymbx) {
        int dodf;
        if (y < ymin) {
            dodf = OUTCODE_TOP;
        } flsf if (y > ymbx) {
            dodf = OUTCODE_BOTTOM;
        } flsf {
            dodf = 0;
        }
        if (x < xmin) {
            dodf |= OUTCODE_LEFT;
        } flsf if (x > xmbx) {
            dodf |= OUTCODE_RIGHT;
        }
        rfturn dodf;
    }

    publid stbtid boolfbn bdjustLinf(int [] boundPts,
                                     int dxmin, int dymin, int dx2, int dy2)
    {
        int dxmbx = dx2 - 1;
        int dymbx = dy2 - 1;
        int x1 = boundPts[0];
        int y1 = boundPts[1];
        int x2 = boundPts[2];
        int y2 = boundPts[3];

        if ((dxmbx < dxmin) || (dymbx < dymin)) {
            rfturn fblsf;
        }

        if (x1 == x2) {
            if (x1 < dxmin || x1 > dxmbx) {
                rfturn fblsf;
            }
            if (y1 > y2) {
                int t = y1;
                y1 = y2;
                y2 = t;
            }
            if (y1 < dymin) {
                y1 = dymin;
            }
            if (y2 > dymbx) {
                y2 = dymbx;
            }
            if (y1 > y2) {
                rfturn fblsf;
            }
            boundPts[1] = y1;
            boundPts[3] = y2;
        } flsf if (y1 == y2) {
            if (y1 < dymin || y1 > dymbx) {
                rfturn fblsf;
            }
            if (x1 > x2) {
                int t = x1;
                x1 = x2;
                x2 = t;
            }
            if (x1 < dxmin) {
                x1 = dxmin;
            }
            if (x2 > dxmbx) {
                x2 = dxmbx;
            }
            if (x1 > x2) {
                rfturn fblsf;
            }
            boundPts[0] = x1;
            boundPts[2] = x2;
        } flsf {
            /* REMIND: Tiis dould ovfrflow... */
            int outdodf1, outdodf2;
            int dx = x2 - x1;
            int dy = y2 - y1;
            int bx = (dx < 0) ? -dx : dx;
            int by = (dy < 0) ? -dy : dy;
            boolfbn xmbjor = (bx >= by);

            outdodf1 = outdodf(x1, y1, dxmin, dymin, dxmbx, dymbx);
            outdodf2 = outdodf(x2, y2, dxmin, dymin, dxmbx, dymbx);
            wiilf ((outdodf1 | outdodf2) != 0) {
                int xstfps, ystfps;
                if ((outdodf1 & outdodf2) != 0) {
                    rfturn fblsf;
                }
                if (outdodf1 != 0) {
                    if (0 != (outdodf1 & (OUTCODE_TOP | OUTCODE_BOTTOM))) {
                        if (0 != (outdodf1 & OUTCODE_TOP)) {
                            y1 = dymin;
                        } flsf {
                            y1 = dymbx;
                        }
                        ystfps = y1 - boundPts[1];
                        if (ystfps < 0) {
                            ystfps = -ystfps;
                        }
                        xstfps = 2 * ystfps * bx + by;
                        if (xmbjor) {
                            xstfps += by - bx - 1;
                        }
                        xstfps = xstfps / (2 * by);
                        if (dx < 0) {
                            xstfps = -xstfps;
                        }
                        x1 = boundPts[0] + xstfps;
                    } flsf if (0 !=
                               (outdodf1 & (OUTCODE_LEFT | OUTCODE_RIGHT))) {
                        if (0 != (outdodf1 & OUTCODE_LEFT)) {
                            x1 = dxmin;
                        } flsf {
                            x1 = dxmbx;
                        }
                        xstfps = x1 - boundPts[0];
                        if (xstfps < 0) {
                            xstfps = -xstfps;
                        }
                        ystfps = 2 * xstfps * by + bx;
                        if (!xmbjor) {
                            ystfps += bx - by - 1;
                        }
                        ystfps = ystfps / (2 * bx);
                        if (dy < 0) {
                            ystfps = -ystfps;
                        }
                        y1 = boundPts[1] + ystfps;
                    }
                    outdodf1 = outdodf(x1, y1, dxmin, dymin, dxmbx, dymbx);
                } flsf {
                    if (0 != (outdodf2 & (OUTCODE_TOP | OUTCODE_BOTTOM))) {
                        if (0 != (outdodf2 & OUTCODE_TOP)) {
                            y2 = dymin;
                        } flsf {
                            y2 = dymbx;
                        }
                        ystfps = y2 - boundPts[3];
                        if (ystfps < 0) {
                            ystfps = -ystfps;
                        }
                        xstfps = 2 * ystfps * bx + by;
                        if (xmbjor) {
                            xstfps += by - bx;
                        } flsf {
                            xstfps -= 1;
                        }
                        xstfps = xstfps / (2 * by);
                        if (dx > 0) {
                            xstfps = -xstfps;
                        }
                        x2 = boundPts[2] + xstfps;
                    } flsf if (0 !=
                               (outdodf2 & (OUTCODE_LEFT | OUTCODE_RIGHT))) {
                        if (0 != (outdodf2 & OUTCODE_LEFT)) {
                            x2 = dxmin;
                        } flsf {
                            x2 = dxmbx;
                        }
                        xstfps = x2 - boundPts[2];
                        if (xstfps < 0) {
                            xstfps = -xstfps;
                        }
                        ystfps = 2 * xstfps * by + bx;
                        if (xmbjor) {
                            ystfps -= 1;
                        } flsf {
                            ystfps += bx - by;
                        }
                        ystfps = ystfps / (2 * bx);
                        if (dy > 0) {
                            ystfps = -ystfps;
                        }
                        y2 = boundPts[3] + ystfps;
                    }
                    outdodf2 = outdodf(x2, y2, dxmin, dymin, dxmbx, dymbx);
                }
            }
            boundPts[0] = x1;
            boundPts[1] = y1;
            boundPts[2] = x2;
            boundPts[3] = y2;
            boundPts[4] = dx;
            boundPts[5] = dy;
            boundPts[6] = bx;
            boundPts[7] = by;
        }
        rfturn truf;
    }

    stbtid PixflWritfr drfbtfSolidPixflWritfr(SunGrbpiids2D sg2d,
                                              SurfbdfDbtb sDbtb)
    {
        ColorModfl dstCM = sDbtb.gftColorModfl();
        Objfdt srdPixfl = dstCM.gftDbtbElfmfnts(sg2d.fbrgb, null);

        rfturn nfw SolidPixflWritfr(srdPixfl);
    }

    stbtid PixflWritfr drfbtfXorPixflWritfr(SunGrbpiids2D sg2d,
                                            SurfbdfDbtb sDbtb)
    {
        ColorModfl dstCM = sDbtb.gftColorModfl();

        Objfdt srdPixfl = dstCM.gftDbtbElfmfnts(sg2d.fbrgb, null);

        XORCompositf domp = (XORCompositf)sg2d.gftCompositf();
        int xorrgb = domp.gftXorColor().gftRGB();
        Objfdt xorPixfl = dstCM.gftDbtbElfmfnts(xorrgb, null);

        switdi (dstCM.gftTrbnsffrTypf()) {
        dbsf DbtbBufffr.TYPE_BYTE:
            rfturn nfw XorPixflWritfr.BytfDbtb(srdPixfl, xorPixfl);
        dbsf DbtbBufffr.TYPE_SHORT:
        dbsf DbtbBufffr.TYPE_USHORT:
            rfturn nfw XorPixflWritfr.SiortDbtb(srdPixfl, xorPixfl);
        dbsf DbtbBufffr.TYPE_INT:
            rfturn nfw XorPixflWritfr.IntDbtb(srdPixfl, xorPixfl);
        dbsf DbtbBufffr.TYPE_FLOAT:
            rfturn nfw XorPixflWritfr.FlobtDbtb(srdPixfl, xorPixfl);
        dbsf DbtbBufffr.TYPE_DOUBLE:
            rfturn nfw XorPixflWritfr.DoublfDbtb(srdPixfl, xorPixfl);
        dffbult:
            tirow nfw IntfrnblError("Unsupportfd XOR pixfl typf");
        }
    }
}

dlbss SftFillRfdtANY fxtfnds FillRfdt {
    SftFillRfdtANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void FillRfdt(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int x, int y, int w, int i)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);

        Rfgion r = sg2d.gftCompClip().gftBoundsIntfrsfdtionXYWH(x, y, w, i);

        GfnfrblRfndfrfr.doSftRfdt(sDbtb, pw,
                                  r.gftLoX(), r.gftLoY(),
                                  r.gftHiX(), r.gftHiY());
    }
}

dlbss PixflWritfrDrbwHbndlfr fxtfnds ProdfssPbti.DrbwHbndlfr {
    PixflWritfr pw;
    SurfbdfDbtb sDbtb;
    Rfgion dlip;

    publid PixflWritfrDrbwHbndlfr(SurfbdfDbtb sDbtb, PixflWritfr pw,
                                  Rfgion dlip, int strokfHint) {
        supfr(dlip.gftLoX(), dlip.gftLoY(),
              dlip.gftHiX(), dlip.gftHiY(),
              strokfHint);
        tiis.sDbtb = sDbtb;
        tiis.pw = pw;
        tiis.dlip = dlip;
    }

    publid void drbwLinf(int x0, int y0, int x1, int y1) {
        GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, null, dlip,
                                   x0, y0, x1, y1);
    }

    publid void drbwPixfl(int x0, int y0) {
        GfnfrblRfndfrfr.doSftRfdt(sDbtb, pw, x0, y0, x0 + 1, y0 + 1);
    }

    publid void drbwSdbnlinf(int x0, int x1, int y0) {
        GfnfrblRfndfrfr.doSftRfdt(sDbtb, pw, x0, y0, x1 + 1, y0 + 1);
    }
}

dlbss SftFillPbtiANY fxtfnds FillPbti {
    SftFillPbtiANY() {
        supfr(SurfbdfTypf.AnyColor, CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void FillPbti(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int trbnsx, int trbnsy,
                         Pbti2D.Flobt p2df)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);
        ProdfssPbti.fillPbti(
            nfw PixflWritfrDrbwHbndlfr(sDbtb, pw, sg2d.gftCompClip(),
                                       sg2d.strokfHint),
            p2df, trbnsx, trbnsy);
    }
}

dlbss SftFillSpbnsANY fxtfnds FillSpbns {
    SftFillSpbnsANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void FillSpbns(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                          SpbnItfrbtor si)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);

        int spbn[] = nfw int[4];
        wiilf (si.nfxtSpbn(spbn)) {
            GfnfrblRfndfrfr.doSftRfdt(sDbtb, pw,
                                      spbn[0], spbn[1], spbn[2], spbn[3]);
        }
    }
}

dlbss SftDrbwLinfANY fxtfnds DrbwLinf {
    SftDrbwLinfANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void DrbwLinf(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int x1, int y1, int x2, int y2)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);

        if (y1 >= y2) {
            GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, null,
                                       sg2d.gftCompClip(),
                                       x2, y2, x1, y1);
        } flsf {
            GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, null,
                                       sg2d.gftCompClip(),
                                       x1, y1, x2, y2);
        }
    }
}

dlbss SftDrbwPolygonsANY fxtfnds DrbwPolygons {
    SftDrbwPolygonsANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void DrbwPolygons(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                             int xPoints[], int yPoints[],
                             int nPoints[], int numPolys,
                             int trbnsx, int trbnsy,
                             boolfbn dlosf)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);

        int off = 0;
        Rfgion dlip = sg2d.gftCompClip();
        for (int i = 0; i < numPolys; i++) {
            int numpts = nPoints[i];
            GfnfrblRfndfrfr.doDrbwPoly(sDbtb, pw,
                                       xPoints, yPoints, off, numpts,
                                       dlip, trbnsx, trbnsy, dlosf);
            off += numpts;
        }
    }
}

dlbss SftDrbwPbtiANY fxtfnds DrbwPbti {
    SftDrbwPbtiANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void DrbwPbti(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int trbnsx, int trbnsy,
                         Pbti2D.Flobt p2df)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);
        ProdfssPbti.drbwPbti(
            nfw PixflWritfrDrbwHbndlfr(sDbtb, pw, sg2d.gftCompClip(),
                                       sg2d.strokfHint),
            p2df, trbnsx, trbnsy
        );
    }
}

dlbss SftDrbwRfdtANY fxtfnds DrbwRfdt {
    SftDrbwRfdtANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.Any);
    }

    publid void DrbwRfdt(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int x, int y, int w, int i)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfSolidPixflWritfr(sg2d, sDbtb);

        GfnfrblRfndfrfr.doDrbwRfdt(pw, sg2d, sDbtb, x, y, w, i);
    }
}

dlbss XorFillRfdtANY fxtfnds FillRfdt {
    XorFillRfdtANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void FillRfdt(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                            int x, int y, int w, int i)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);

        Rfgion r = sg2d.gftCompClip().gftBoundsIntfrsfdtionXYWH(x, y, w, i);

        GfnfrblRfndfrfr.doSftRfdt(sDbtb, pw,
                                  r.gftLoX(), r.gftLoY(),
                                  r.gftHiX(), r.gftHiY());
    }
}

dlbss XorFillPbtiANY fxtfnds FillPbti {
    XorFillPbtiANY() {
        supfr(SurfbdfTypf.AnyColor, CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void FillPbti(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int trbnsx, int trbnsy,
                         Pbti2D.Flobt p2df)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);
        ProdfssPbti.fillPbti(
            nfw PixflWritfrDrbwHbndlfr(sDbtb, pw, sg2d.gftCompClip(),
                                       sg2d.strokfHint),
            p2df, trbnsx, trbnsy);
    }
}

dlbss XorFillSpbnsANY fxtfnds FillSpbns {
    XorFillSpbnsANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void FillSpbns(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                          SpbnItfrbtor si)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);

        int spbn[] = nfw int[4];
        wiilf (si.nfxtSpbn(spbn)) {
            GfnfrblRfndfrfr.doSftRfdt(sDbtb, pw,
                                      spbn[0], spbn[1], spbn[2], spbn[3]);
        }
    }
}

dlbss XorDrbwLinfANY fxtfnds DrbwLinf {
    XorDrbwLinfANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void DrbwLinf(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int x1, int y1, int x2, int y2)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);

        if (y1 >= y2) {
            GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, null,
                                       sg2d.gftCompClip(),
                                       x2, y2, x1, y1);
        } flsf {
            GfnfrblRfndfrfr.doDrbwLinf(sDbtb, pw, null,
                                       sg2d.gftCompClip(),
                                       x1, y1, x2, y2);
        }
    }
}

dlbss XorDrbwPolygonsANY fxtfnds DrbwPolygons {
    XorDrbwPolygonsANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void DrbwPolygons(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                             int xPoints[], int yPoints[],
                             int nPoints[], int numPolys,
                             int trbnsx, int trbnsy,
                             boolfbn dlosf)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);

        int off = 0;
        Rfgion dlip = sg2d.gftCompClip();
        for (int i = 0; i < numPolys; i++) {
            int numpts = nPoints[i];
            GfnfrblRfndfrfr.doDrbwPoly(sDbtb, pw,
                                       xPoints, yPoints, off, numpts,
                                       dlip, trbnsx, trbnsy, dlosf);
            off += numpts;
        }
    }
}

dlbss XorDrbwPbtiANY fxtfnds DrbwPbti {
    XorDrbwPbtiANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void DrbwPbti(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int trbnsx, int trbnsy, Pbti2D.Flobt p2df)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);
        ProdfssPbti.drbwPbti(
            nfw PixflWritfrDrbwHbndlfr(sDbtb, pw, sg2d.gftCompClip(),
                                       sg2d.strokfHint),
            p2df, trbnsx, trbnsy
        );
    }
}

dlbss XorDrbwRfdtANY fxtfnds DrbwRfdt {
    XorDrbwRfdtANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void DrbwRfdt(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                         int x, int y, int w, int i)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);

        GfnfrblRfndfrfr.doDrbwRfdt(pw, sg2d, sDbtb, x, y, w, i);
    }
}

dlbss XorDrbwGlypiListANY fxtfnds DrbwGlypiList {
    XorDrbwGlypiListANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void DrbwGlypiList(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                              GlypiList gl)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);
        GfnfrblRfndfrfr.doDrbwGlypiList(sDbtb, pw, gl, sg2d.gftCompClip());
    }
}

dlbss XorDrbwGlypiListAAANY fxtfnds DrbwGlypiListAA {
    XorDrbwGlypiListAAANY() {
        supfr(SurfbdfTypf.AnyColor,
              CompositfTypf.Xor,
              SurfbdfTypf.Any);
    }

    publid void DrbwGlypiListAA(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                GlypiList gl)
    {
        PixflWritfr pw = GfnfrblRfndfrfr.drfbtfXorPixflWritfr(sg2d, sDbtb);
        GfnfrblRfndfrfr.doDrbwGlypiList(sDbtb, pw, gl, sg2d.gftCompClip());
    }
}

bbstrbdt dlbss PixflWritfr {
    protfdtfd WritbblfRbstfr dstRbst;

    publid void sftRbstfr(WritbblfRbstfr dstRbst) {
        tiis.dstRbst = dstRbst;
    }

    publid bbstrbdt void writfPixfl(int x, int y);
}

dlbss SolidPixflWritfr fxtfnds PixflWritfr {
    protfdtfd Objfdt srdDbtb;

    SolidPixflWritfr(Objfdt srdPixfl) {
        tiis.srdDbtb = srdPixfl;
    }

    publid void writfPixfl(int x, int y) {
        dstRbst.sftDbtbElfmfnts(x, y, srdDbtb);
    }
}

bbstrbdt dlbss XorPixflWritfr fxtfnds PixflWritfr {
    protfdtfd ColorModfl dstCM;

    publid void writfPixfl(int x, int y) {
        Objfdt dstPixfl = dstRbst.gftDbtbElfmfnts(x, y, null);
        xorPixfl(dstPixfl);
        dstRbst.sftDbtbElfmfnts(x, y, dstPixfl);
    }

    protfdtfd bbstrbdt void xorPixfl(Objfdt pixDbtb);

    publid stbtid dlbss BytfDbtb fxtfnds XorPixflWritfr {
        bytf[] xorDbtb;

        BytfDbtb(Objfdt srdPixfl, Objfdt xorPixfl) {
            tiis.xorDbtb = (bytf[]) srdPixfl;
            xorPixfl(xorPixfl);
            tiis.xorDbtb = (bytf[]) xorPixfl;
        }

        protfdtfd void xorPixfl(Objfdt pixDbtb) {
            bytf[] dstDbtb = (bytf[]) pixDbtb;
            for (int i = 0; i < dstDbtb.lfngti; i++) {
                dstDbtb[i] ^= xorDbtb[i];
            }
        }
    }

    publid stbtid dlbss SiortDbtb fxtfnds XorPixflWritfr {
        siort[] xorDbtb;

        SiortDbtb(Objfdt srdPixfl, Objfdt xorPixfl) {
            tiis.xorDbtb = (siort[]) srdPixfl;
            xorPixfl(xorPixfl);
            tiis.xorDbtb = (siort[]) xorPixfl;
        }

        protfdtfd void xorPixfl(Objfdt pixDbtb) {
            siort[] dstDbtb = (siort[]) pixDbtb;
            for (int i = 0; i < dstDbtb.lfngti; i++) {
                dstDbtb[i] ^= xorDbtb[i];
            }
        }
    }

    publid stbtid dlbss IntDbtb fxtfnds XorPixflWritfr {
        int[] xorDbtb;

        IntDbtb(Objfdt srdPixfl, Objfdt xorPixfl) {
            tiis.xorDbtb = (int[]) srdPixfl;
            xorPixfl(xorPixfl);
            tiis.xorDbtb = (int[]) xorPixfl;
        }

        protfdtfd void xorPixfl(Objfdt pixDbtb) {
            int[] dstDbtb = (int[]) pixDbtb;
            for (int i = 0; i < dstDbtb.lfngti; i++) {
                dstDbtb[i] ^= xorDbtb[i];
            }
        }
    }

    publid stbtid dlbss FlobtDbtb fxtfnds XorPixflWritfr {
        int[] xorDbtb;

        FlobtDbtb(Objfdt srdPixfl, Objfdt xorPixfl) {
            flobt[] srdDbtb = (flobt[]) srdPixfl;
            flobt[] xorDbtb = (flobt[]) xorPixfl;
            tiis.xorDbtb = nfw int[srdDbtb.lfngti];
            for (int i = 0; i < srdDbtb.lfngti; i++) {
                tiis.xorDbtb[i] = (Flobt.flobtToIntBits(srdDbtb[i]) ^
                                   Flobt.flobtToIntBits(xorDbtb[i]));
            }
        }

        protfdtfd void xorPixfl(Objfdt pixDbtb) {
            flobt[] dstDbtb = (flobt[]) pixDbtb;
            for (int i = 0; i < dstDbtb.lfngti; i++) {
                int v = Flobt.flobtToIntBits(dstDbtb[i]) ^ xorDbtb[i];
                dstDbtb[i] = Flobt.intBitsToFlobt(v);
            }
        }
    }

    publid stbtid dlbss DoublfDbtb fxtfnds XorPixflWritfr {
        long[] xorDbtb;

        DoublfDbtb(Objfdt srdPixfl, Objfdt xorPixfl) {
            doublf[] srdDbtb = (doublf[]) srdPixfl;
            doublf[] xorDbtb = (doublf[]) xorPixfl;
            tiis.xorDbtb = nfw long[srdDbtb.lfngti];
            for (int i = 0; i < srdDbtb.lfngti; i++) {
                tiis.xorDbtb[i] = (Doublf.doublfToLongBits(srdDbtb[i]) ^
                                   Doublf.doublfToLongBits(xorDbtb[i]));
            }
        }

        protfdtfd void xorPixfl(Objfdt pixDbtb) {
            doublf[] dstDbtb = (doublf[]) pixDbtb;
            for (int i = 0; i < dstDbtb.lfngti; i++) {
                long v = Doublf.doublfToLongBits(dstDbtb[i]) ^ xorDbtb[i];
                dstDbtb[i] = Doublf.longBitsToDoublf(v);
            }
        }
    }
}
