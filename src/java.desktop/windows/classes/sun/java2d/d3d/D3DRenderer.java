/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.d3d;

import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.gfom.Pbti2D;
import sun.jbvb2d.InvblidPipfExdfption;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.pipf.BufffrfdPbints;
import sun.jbvb2d.pipf.BufffrfdRfndfrPipf;
import sun.jbvb2d.pipf.RfndfrQufuf;
import sun.jbvb2d.pipf.SpbnItfrbtor;
import sun.jbvb2d.pipf.PbrbllflogrbmPipf;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;

dlbss D3DRfndfrfr fxtfnds BufffrfdRfndfrPipf {

    D3DRfndfrfr(RfndfrQufuf rq) {
        supfr(rq);
    }

    @Ovfrridf
    protfdtfd void vblidbtfContfxt(SunGrbpiids2D sg2d) {
        int dtxflbgs =
            sg2d.pbint.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE ?
                D3DContfxt.SRC_IS_OPAQUE : D3DContfxt.NO_CONTEXT_FLAGS;
        D3DSurfbdfDbtb dstDbtb;
        try {
            dstDbtb = (D3DSurfbdfDbtb)sg2d.surfbdfDbtb;
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
        D3DContfxt.vblidbtfContfxt(dstDbtb, dstDbtb,
                                   sg2d.gftCompClip(), sg2d.dompositf,
                                   null, sg2d.pbint, sg2d, dtxflbgs);
    }

    @Ovfrridf
    protfdtfd void vblidbtfContfxtAA(SunGrbpiids2D sg2d) {
        int dtxflbgs = D3DContfxt.NO_CONTEXT_FLAGS;
        D3DSurfbdfDbtb dstDbtb;
        try {
            dstDbtb = (D3DSurfbdfDbtb)sg2d.surfbdfDbtb;
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
        D3DContfxt.vblidbtfContfxt(dstDbtb, dstDbtb,
                                   sg2d.gftCompClip(), sg2d.dompositf,
                                   null, sg2d.pbint, sg2d, dtxflbgs);
    }

    void dopyArfb(SunGrbpiids2D sg2d,
                  int x, int y, int w, int i, int dx, int dy)
    {
        rq.lodk();
        try {
            int dtxflbgs =
                sg2d.surfbdfDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE ?
                    D3DContfxt.SRC_IS_OPAQUE : D3DContfxt.NO_CONTEXT_FLAGS;
            D3DSurfbdfDbtb dstDbtb;
            try {
                dstDbtb = (D3DSurfbdfDbtb)sg2d.surfbdfDbtb;
            } dbtdi (ClbssCbstExdfption f) {
                tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
            }
            D3DContfxt.vblidbtfContfxt(dstDbtb, dstDbtb,
                                       sg2d.gftCompClip(), sg2d.dompositf,
                                       null, null, null, dtxflbgs);

            rq.fnsurfCbpbdity(28);
            buf.putInt(COPY_AREA);
            buf.putInt(x).putInt(y).putInt(w).putInt(i);
            buf.putInt(dx).putInt(dy);
        } finblly {
            rq.unlodk();
        }
    }

    protfdtfd nbtivf void drbwPoly(int[] xPoints, int[] yPoints,
                                   int nPoints, boolfbn isClosfd,
                                   int trbnsX, int trbnsY);

    D3DRfndfrfr trbdfWrbp() {
        rfturn nfw Trbdfr(tiis);
    }

    privbtf dlbss Trbdfr fxtfnds D3DRfndfrfr {
        privbtf D3DRfndfrfr d3dr;
        Trbdfr(D3DRfndfrfr d3dr) {
            supfr(d3dr.rq);
            tiis.d3dr = d3dr;
        }
        publid PbrbllflogrbmPipf gftAAPbrbllflogrbmPipf() {
            finbl PbrbllflogrbmPipf rfblpipf = d3dr.gftAAPbrbllflogrbmPipf();
            rfturn nfw PbrbllflogrbmPipf() {
                publid void fillPbrbllflogrbm(SunGrbpiids2D sg2d,
                                              doublf ux1, doublf uy1,
                                              doublf ux2, doublf uy2,
                                              doublf x, doublf y,
                                              doublf dx1, doublf dy1,
                                              doublf dx2, doublf dy2)
                {
                    GrbpiidsPrimitivf.trbdfPrimitivf("D3DFillAAPbrbllflogrbm");
                    rfblpipf.fillPbrbllflogrbm(sg2d,
                                               ux1, uy1, ux2, uy2,
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
                    GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwAAPbrbllflogrbm");
                    rfblpipf.drbwPbrbllflogrbm(sg2d,
                                               ux1, uy1, ux2, uy2,
                                               x, y, dx1, dy1, dx2, dy2,
                                               lw1, lw2);
                }
            };
        }

        protfdtfd void vblidbtfContfxt(SunGrbpiids2D sg2d) {
            d3dr.vblidbtfContfxt(sg2d);
        }
        publid void drbwLinf(SunGrbpiids2D sg2d,
                             int x1, int y1, int x2, int y2)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwLinf");
            d3dr.drbwLinf(sg2d, x1, y1, x2, y2);
        }
        publid void drbwRfdt(SunGrbpiids2D sg2d, int x, int y, int w, int i) {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwRfdt");
            d3dr.drbwRfdt(sg2d, x, y, w, i);
        }
        protfdtfd void drbwPoly(SunGrbpiids2D sg2d,
                                int[] xPoints, int[] yPoints,
                                int nPoints, boolfbn isClosfd)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwPoly");
            d3dr.drbwPoly(sg2d, xPoints, yPoints, nPoints, isClosfd);
        }
        publid void fillRfdt(SunGrbpiids2D sg2d, int x, int y, int w, int i) {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DFillRfdt");
            d3dr.fillRfdt(sg2d, x, y, w, i);
        }
        protfdtfd void drbwPbti(SunGrbpiids2D sg2d,
                                Pbti2D.Flobt p2df, int trbnsx, int trbnsy)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwPbti");
            d3dr.drbwPbti(sg2d, p2df, trbnsx, trbnsy);
        }
        protfdtfd void fillPbti(SunGrbpiids2D sg2d,
                                Pbti2D.Flobt p2df, int trbnsx, int trbnsy)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DFillPbti");
            d3dr.fillPbti(sg2d, p2df, trbnsx, trbnsy);
        }
        protfdtfd void fillSpbns(SunGrbpiids2D sg2d, SpbnItfrbtor si,
                                 int trbnsx, int trbnsy)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DFillSpbns");
            d3dr.fillSpbns(sg2d, si, trbnsx, trbnsy);
        }
        publid void fillPbrbllflogrbm(SunGrbpiids2D sg2d,
                                      doublf ux1, doublf uy1,
                                      doublf ux2, doublf uy2,
                                      doublf x, doublf y,
                                      doublf dx1, doublf dy1,
                                      doublf dx2, doublf dy2)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DFillPbrbllflogrbm");
            d3dr.fillPbrbllflogrbm(sg2d,
                                   ux1, uy1, ux2, uy2,
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
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwPbrbllflogrbm");
            d3dr.drbwPbrbllflogrbm(sg2d,
                                   ux1, uy1, ux2, uy2,
                                   x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }
        publid void dopyArfb(SunGrbpiids2D sg2d,
                             int x, int y, int w, int i, int dx, int dy)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DCopyArfb");
            d3dr.dopyArfb(sg2d, x, y, w, i, dx, dy);
        }
    }
}
