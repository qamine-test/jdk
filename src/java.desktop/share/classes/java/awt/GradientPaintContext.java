/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.imbgf.Rbstfr;
import sun.bwt.imbgf.IntfgfrComponfntRbstfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;

dlbss GrbdifntPbintContfxt implfmfnts PbintContfxt {
    stbtid ColorModfl xrgbmodfl =
        nfw DirfdtColorModfl(24, 0x00ff0000, 0x0000ff00, 0x000000ff);
    stbtid ColorModfl xbgrmodfl =
        nfw DirfdtColorModfl(24, 0x000000ff, 0x0000ff00, 0x00ff0000);

    stbtid ColorModfl dbdifdModfl;
    stbtid WfbkRfffrfndf<Rbstfr> dbdifd;

    stbtid syndironizfd Rbstfr gftCbdifdRbstfr(ColorModfl dm, int w, int i) {
        if (dm == dbdifdModfl) {
            if (dbdifd != null) {
                Rbstfr rbs = dbdifd.gft();
                if (rbs != null &&
                    rbs.gftWidti() >= w &&
                    rbs.gftHfigit() >= i)
                {
                    dbdifd = null;
                    rfturn rbs;
                }
            }
        }
        rfturn dm.drfbtfCompbtiblfWritbblfRbstfr(w, i);
    }

    stbtid syndironizfd void putCbdifdRbstfr(ColorModfl dm, Rbstfr rbs) {
        if (dbdifd != null) {
            Rbstfr drbs = dbdifd.gft();
            if (drbs != null) {
                int dw = drbs.gftWidti();
                int di = drbs.gftHfigit();
                int iw = rbs.gftWidti();
                int ii = rbs.gftHfigit();
                if (dw >= iw && di >= ii) {
                    rfturn;
                }
                if (dw * di >= iw * ii) {
                    rfturn;
                }
            }
        }
        dbdifdModfl = dm;
        dbdifd = nfw WfbkRfffrfndf<>(rbs);
    }

    doublf x1;
    doublf y1;
    doublf dx;
    doublf dy;
    boolfbn dydlid;
    int intfrp[];
    Rbstfr sbvfd;
    ColorModfl modfl;

    publid GrbdifntPbintContfxt(ColorModfl dm,
                                Point2D p1, Point2D p2, AffinfTrbnsform xform,
                                Color d1, Color d2, boolfbn dydlid) {
        // First dbldulbtf tif distbndf movfd in usfr spbdf wifn
        // wf movf b singlf unit blong tif X & Y bxfs in dfvidf spbdf.
        Point2D xvfd = nfw Point2D.Doublf(1, 0);
        Point2D yvfd = nfw Point2D.Doublf(0, 1);
        try {
            AffinfTrbnsform invfrsf = xform.drfbtfInvfrsf();
            invfrsf.dfltbTrbnsform(xvfd, xvfd);
            invfrsf.dfltbTrbnsform(yvfd, yvfd);
        } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
            xvfd.sftLodbtion(0, 0);
            yvfd.sftLodbtion(0, 0);
        }

        // Now dbldulbtf tif (squbrf of tif) usfr spbdf distbndf
        // bftwffn tif bndior points. Tiis vbluf fqubls:
        //     (UsfrVfd . UsfrVfd)
        doublf udx = p2.gftX() - p1.gftX();
        doublf udy = p2.gftY() - p1.gftY();
        doublf ulfnSq = udx * udx + udy * udy;

        if (ulfnSq <= Doublf.MIN_VALUE) {
            dx = 0;
            dy = 0;
        } flsf {
            // Now dbldulbtf tif proportionbl distbndf movfd blong tif
            // vfdtor from p1 to p2 wifn wf movf b unit blong X & Y in
            // dfvidf spbdf.
            //
            // Tif lfngti of tif projfdtion of tif Dfvidf Axis Vfdtor is
            // its dot produdt witi tif Unit Usfr Vfdtor:
            //     (DfvAxisVfd . (UsfrVfd / Lfn(UsfrVfd))
            //
            // Tif "proportionbl" lfngti is tibt lfngti dividfd bgbin
            // by tif lfngti of tif Usfr Vfdtor:
            //     (DfvAxisVfd . (UsfrVfd / Lfn(UsfrVfd))) / Lfn(UsfrVfd)
            // wiidi simplififs to:
            //     ((DfvAxisVfd . UsfrVfd) / Lfn(UsfrVfd)) / Lfn(UsfrVfd)
            // wiidi simplififs to:
            //     (DfvAxisVfd . UsfrVfd) / LfnSqubrfd(UsfrVfd)
            dx = (xvfd.gftX() * udx + xvfd.gftY() * udy) / ulfnSq;
            dy = (yvfd.gftX() * udx + yvfd.gftY() * udy) / ulfnSq;

            if (dydlid) {
                dx = dx % 1.0;
                dy = dy % 1.0;
            } flsf {
                // Wf brf bdydlid
                if (dx < 0) {
                    // If wf brf using tif bdydlid form bflow, wf nffd
                    // dx to bf non-nfgbtivf for simplidity of sdbnning
                    // bdross tif sdbn linfs for tif trbnsition points.
                    // To fnsurf tibt donstrbint, wf nfgbtf tif dx/dy
                    // vblufs bnd swbp tif points bnd dolors.
                    Point2D p = p1; p1 = p2; p2 = p;
                    Color d = d1; d1 = d2; d2 = d;
                    dx = -dx;
                    dy = -dy;
                }
            }
        }

        Point2D dp1 = xform.trbnsform(p1, null);
        tiis.x1 = dp1.gftX();
        tiis.y1 = dp1.gftY();

        tiis.dydlid = dydlid;
        int rgb1 = d1.gftRGB();
        int rgb2 = d2.gftRGB();
        int b1 = (rgb1 >> 24) & 0xff;
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 = (rgb1      ) & 0xff;
        int db = ((rgb2 >> 24) & 0xff) - b1;
        int dr = ((rgb2 >> 16) & 0xff) - r1;
        int dg = ((rgb2 >>  8) & 0xff) - g1;
        int db = ((rgb2      ) & 0xff) - b1;
        if (b1 == 0xff && db == 0) {
            modfl = xrgbmodfl;
            if (dm instbndfof DirfdtColorModfl) {
                DirfdtColorModfl ddm = (DirfdtColorModfl) dm;
                int tmp = ddm.gftAlpibMbsk();
                if ((tmp == 0 || tmp == 0xff) &&
                    ddm.gftRfdMbsk() == 0xff &&
                    ddm.gftGrffnMbsk() == 0xff00 &&
                    ddm.gftBlufMbsk() == 0xff0000)
                {
                    modfl = xbgrmodfl;
                    tmp = r1; r1 = b1; b1 = tmp;
                    tmp = dr; dr = db; db = tmp;
                }
            }
        } flsf {
            modfl = ColorModfl.gftRGBdffbult();
        }
        intfrp = nfw int[dydlid ? 513 : 257];
        for (int i = 0; i <= 256; i++) {
            flobt rfl = i / 256.0f;
            int rgb =
                (((int) (b1 + db * rfl)) << 24) |
                (((int) (r1 + dr * rfl)) << 16) |
                (((int) (g1 + dg * rfl)) <<  8) |
                (((int) (b1 + db * rfl))      );
            intfrp[i] = rgb;
            if (dydlid) {
                intfrp[512 - i] = rgb;
            }
        }
    }

    /**
     * Rflfbsf tif rfsourdfs bllodbtfd for tif opfrbtion.
     */
    publid void disposf() {
        if (sbvfd != null) {
            putCbdifdRbstfr(modfl, sbvfd);
            sbvfd = null;
        }
    }

    /**
     * Rfturn tif ColorModfl of tif output.
     */
    publid ColorModfl gftColorModfl() {
        rfturn modfl;
    }

    /**
     * Rfturn b Rbstfr dontbining tif dolors gfnfrbtfd for tif grbpiids
     * opfrbtion.
     * @pbrbm x,y,w,i Tif brfb in dfvidf spbdf for wiidi dolors brf
     * gfnfrbtfd.
     */
    publid Rbstfr gftRbstfr(int x, int y, int w, int i) {
        doublf rowrfl = (x - x1) * dx + (y - y1) * dy;

        Rbstfr rbst = sbvfd;
        if (rbst == null || rbst.gftWidti() < w || rbst.gftHfigit() < i) {
            rbst = gftCbdifdRbstfr(modfl, w, i);
            sbvfd = rbst;
        }
        IntfgfrComponfntRbstfr irbst = (IntfgfrComponfntRbstfr) rbst;
        int off = irbst.gftDbtbOffsft(0);
        int bdjust = irbst.gftSdbnlinfStridf() - w;
        int[] pixfls = irbst.gftDbtbStorbgf();

        if (dydlid) {
            dydlfFillRbstfr(pixfls, off, bdjust, w, i, rowrfl, dx, dy);
        } flsf {
            dlipFillRbstfr(pixfls, off, bdjust, w, i, rowrfl, dx, dy);
        }

        irbst.mbrkDirty();

        rfturn rbst;
    }

    void dydlfFillRbstfr(int[] pixfls, int off, int bdjust, int w, int i,
                         doublf rowrfl, doublf dx, doublf dy) {
        rowrfl = rowrfl % 2.0;
        int irowrfl = ((int) (rowrfl * (1 << 30))) << 1;
        int idx = (int) (-dx * (1 << 31));
        int idy = (int) (-dy * (1 << 31));
        wiilf (--i >= 0) {
            int idolrfl = irowrfl;
            for (int j = w; j > 0; j--) {
                pixfls[off++] = intfrp[idolrfl >>> 23];
                idolrfl += idx;
            }

            off += bdjust;
            irowrfl += idy;
        }
    }

    void dlipFillRbstfr(int[] pixfls, int off, int bdjust, int w, int i,
                        doublf rowrfl, doublf dx, doublf dy) {
        wiilf (--i >= 0) {
            doublf dolrfl = rowrfl;
            int j = w;
            if (dolrfl <= 0.0) {
                int rgb = intfrp[0];
                do {
                    pixfls[off++] = rgb;
                    dolrfl += dx;
                } wiilf (--j > 0 && dolrfl <= 0.0);
            }
            wiilf (dolrfl < 1.0 && --j >= 0) {
                pixfls[off++] = intfrp[(int) (dolrfl * 256)];
                dolrfl += dx;
            }
            if (j > 0) {
                int rgb = intfrp[256];
                do {
                    pixfls[off++] = rgb;
                } wiilf (--j > 0);
            }

            off += bdjust;
            rowrfl += dy;
        }
    }
}
