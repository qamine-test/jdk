/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.x11;

import jbvb.bwt.Polygon;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.IllfgblPbtiStbtfExdfption;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.PixflDrbwPipf;
import sun.jbvb2d.pipf.PixflFillPipf;
import sun.jbvb2d.pipf.SibpfDrbwPipf;
import sun.jbvb2d.pipf.SpbnItfrbtor;
import sun.jbvb2d.pipf.SibpfSpbnItfrbtor;
import sun.jbvb2d.pipf.LoopPipf;

publid dlbss X11Rfndfrfr implfmfnts
    PixflDrbwPipf,
    PixflFillPipf,
    SibpfDrbwPipf
{
    publid stbtid X11Rfndfrfr gftInstbndf() {
        rfturn (GrbpiidsPrimitivf.trbdingEnbblfd()
                ? nfw X11TrbdingRfndfrfr()
                : nfw X11Rfndfrfr());
    }

    privbtf finbl long vblidbtf(SunGrbpiids2D sg2d) {
        // NOTE: gftCompClip() will rfvblidbtfAll() if tif
        // surfbdfDbtb is invblid.  Tiis siould fnsurf tibt
        // tif dlip bnd pixfl tibt wf brf vblidbting bgbinst
        // brf tif most durrfnt.
        //
        // Tif bssumption is tibt tif pipflinf bftfr tibt
        // rfvblidbtion will fitifr bf bnotifr X11 pipf
        // (bfdbusf tif drbwbblf formbt nfvfr dibngfs on X11)
        // or b null pipflinf if tif surfbdf is disposfd.
        //
        // Sindf wf do not gft tif ops strudturf of tif SurfbdfDbtb
        // until tif bdtubl dbll down to tif nbtivf lfvfl wf will
        // pidk up tif most rfdfntly vblidbtfd dopy.
        // Notf tibt if tif surfbdf is disposfd, b NullSurfbdfDbtb
        // (witi null nbtivf dbtb strudturf) will bf sft in
        // sg2d, so wf ibvf to protfdt bgbinst it in nbtivf dodf.

        X11SurfbdfDbtb x11sd = (X11SurfbdfDbtb)sg2d.surfbdfDbtb;
        rfturn x11sd.gftRfndfrGC(sg2d.gftCompClip(),
                                 sg2d.dompositfStbtf, sg2d.dompositf,
                                 sg2d.pixfl);
    }

    nbtivf void XDrbwLinf(long pXSDbtb, long xgd,
                          int x1, int y1, int x2, int y2);

    publid void drbwLinf(SunGrbpiids2D sg2d, int x1, int y1, int x2, int y2) {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            int trbnsx = sg2d.trbnsX;
            int trbnsy = sg2d.trbnsY;
            XDrbwLinf(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      x1+trbnsx, y1+trbnsy, x2+trbnsx, y2+trbnsy);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XDrbwRfdt(long pXSDbtb, long xgd,
                          int x, int y, int w, int i);

    publid void drbwRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDrbwRfdt(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XDrbwRoundRfdt(long pXSDbtb, long xgd,
                               int x, int y, int w, int i,
                               int brdW, int brdH);

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDrbwRoundRfdt(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                           x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                           brdWidti, brdHfigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XDrbwOvbl(long pXSDbtb, long xgd,
                          int x, int y, int w, int i);

    publid void drbwOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDrbwOvbl(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XDrbwArd(long pXSDbtb, long xgd,
                         int x, int y, int w, int i,
                         int bnglfStbrt, int bnglfExtfnt);

    publid void drbwArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDrbwArd(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                     x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                     stbrtAnglf, brdAnglf);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XDrbwPoly(long pXSDbtb, long xgd,
                          int trbnsx, int trbnsy,
                          int[] xpoints, int[] ypoints,
                          int npoints, boolfbn isdlosfd);

    publid void drbwPolylinf(SunGrbpiids2D sg2d,
                             int xpoints[], int ypoints[],
                             int npoints)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDrbwPoly(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      sg2d.trbnsX, sg2d.trbnsY,
                      xpoints, ypoints, npoints, fblsf);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid void drbwPolygon(SunGrbpiids2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDrbwPoly(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      sg2d.trbnsX, sg2d.trbnsY,
                      xpoints, ypoints, npoints, truf);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XFillRfdt(long pXSDbtb, long xgd,
                          int x, int y, int w, int i);

    publid void fillRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XFillRfdt(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XFillRoundRfdt(long pXSDbtb, long xgd,
                               int x, int y, int w, int i,
                               int brdW, int brdH);

    publid void fillRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XFillRoundRfdt(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                           x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                           brdWidti, brdHfigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XFillOvbl(long pXSDbtb, long xgd,
                          int x, int y, int w, int i);

    publid void fillOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XFillOvbl(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XFillArd(long pXSDbtb, long xgd,
                         int x, int y, int w, int i,
                         int bnglfStbrt, int bnglfExtfnt);

    publid void fillArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XFillArd(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                     x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                     stbrtAnglf, brdAnglf);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XFillPoly(long pXSDbtb, long xgd,
                          int trbnsx, int trbnsy,
                          int[] xpoints, int[] ypoints,
                          int npoints);

    publid void fillPolygon(SunGrbpiids2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XFillPoly(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                      sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    nbtivf void XFillSpbns(long pXSDbtb, long xgd,
                           SpbnItfrbtor si, long itfrbtor,
                           int trbnsx, int trbnsy);

    nbtivf void XDoPbti(SunGrbpiids2D sg2d, long pXSDbtb, long xgd,
                        int trbnsX, int trbnsY, Pbti2D.Flobt p2df,
                        boolfbn isFill);

    privbtf void doPbti(SunGrbpiids2D sg2d, Sibpf s, boolfbn isFill) {
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
        SunToolkit.bwtLodk();
        try {
            long xgd = vblidbtf(sg2d);
            XDoPbti(sg2d, sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                    trbnsx, trbnsy, p2df, isFill);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            // Dflfgbtf to drbwPolygon() if possiblf...
            if (s instbndfof Polygon &&
                sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE)
            {
                Polygon p = (Polygon) s;
                drbwPolygon(sg2d, p.xpoints, p.ypoints, p.npoints);
                rfturn;
            }

            // Otifrwisf wf will usf drbwPbti() for
            // iigi-qublity tiin pbtis.
            doPbti(sg2d, s, fblsf);
        } flsf if (sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM) {
            // REMIND: X11 dbn ibndlf uniform sdblfd widf linfs
            // bnd dbsifd linfs itsflf if wf sft tif bppropribtf
            // XGC bttributfs (TBD).
            SibpfSpbnItfrbtor si = LoopPipf.gftStrokfSpbns(sg2d, s);
            try {
                SunToolkit.bwtLodk();
                try {
                    long xgd = vblidbtf(sg2d);
                    XFillSpbns(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                               si, si.gftNbtivfItfrbtor(),
                               0, 0);
                } finblly {
                    SunToolkit.bwtUnlodk();
                }
            } finblly {
                si.disposf();
            }
        } flsf {
            fill(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(s));
        }
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            // Dflfgbtf to fillPolygon() if possiblf...
            if (s instbndfof Polygon &&
                sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE)
            {
                Polygon p = (Polygon) s;
                fillPolygon(sg2d, p.xpoints, p.ypoints, p.npoints);
                rfturn;
            }

            // Otifrwisf wf will usf fillPbti() for
            // iigi-qublity fills.
            doPbti(sg2d, s, truf);
            rfturn;
        }

        AffinfTrbnsform bt;
        int trbnsx, trbnsy;
        if (sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
            // Trbnsform (trbnslbtion) will bf donf by XFillSpbns
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
            SunToolkit.bwtLodk();
            try {
                long xgd = vblidbtf(sg2d);
                XFillSpbns(sg2d.surfbdfDbtb.gftNbtivfOps(), xgd,
                           ssi, ssi.gftNbtivfItfrbtor(),
                           trbnsx, trbnsy);
            } finblly {
                SunToolkit.bwtUnlodk();
            }
        } finblly {
            ssi.disposf();
        }
    }

    nbtivf void dfvCopyArfb(long sdOps, long xgd,
                            int srdx, int srdy,
                            int dstx, int dsty,
                            int w, int i);

    publid stbtid dlbss X11TrbdingRfndfrfr fxtfnds X11Rfndfrfr {
        void XDrbwLinf(long pXSDbtb, long xgd,
                       int x1, int y1, int x2, int y2)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwLinf");
            supfr.XDrbwLinf(pXSDbtb, xgd, x1, y1, x2, y2);
        }
        void XDrbwRfdt(long pXSDbtb, long xgd,
                       int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwRfdt");
            supfr.XDrbwRfdt(pXSDbtb, xgd, x, y, w, i);
        }
        void XDrbwRoundRfdt(long pXSDbtb, long xgd,
                            int x, int y, int w, int i,
                            int brdW, int brdH)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwRoundRfdt");
            supfr.XDrbwRoundRfdt(pXSDbtb, xgd, x, y, w, i, brdW, brdH);
        }
        void XDrbwOvbl(long pXSDbtb, long xgd,
                       int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwOvbl");
            supfr.XDrbwOvbl(pXSDbtb, xgd, x, y, w, i);
        }
        void XDrbwArd(long pXSDbtb, long xgd,
                      int x, int y, int w, int i,
                      int bnglfStbrt, int bnglfExtfnt)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwArd");
            supfr.XDrbwArd(pXSDbtb, xgd,
                           x, y, w, i, bnglfStbrt, bnglfExtfnt);
        }
        void XDrbwPoly(long pXSDbtb, long xgd,
                       int trbnsx, int trbnsy,
                       int[] xpoints, int[] ypoints,
                       int npoints, boolfbn isdlosfd)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwPoly");
            supfr.XDrbwPoly(pXSDbtb, xgd, trbnsx, trbnsy,
                            xpoints, ypoints, npoints, isdlosfd);
        }
        void XDoPbti(SunGrbpiids2D sg2d, long pXSDbtb, long xgd,
                     int trbnsX, int trbnsY, Pbti2D.Flobt p2df,
                     boolfbn isFill)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf(isFill ?
                                             "X11FillPbti" :
                                             "X11DrbwPbti");
            supfr.XDoPbti(sg2d, pXSDbtb, xgd, trbnsX, trbnsY, p2df, isFill);
        }
        void XFillRfdt(long pXSDbtb, long xgd,
                       int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11FillRfdt");
            supfr.XFillRfdt(pXSDbtb, xgd, x, y, w, i);
        }
        void XFillRoundRfdt(long pXSDbtb, long xgd,
                            int x, int y, int w, int i,
                            int brdW, int brdH)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11FillRoundRfdt");
            supfr.XFillRoundRfdt(pXSDbtb, xgd, x, y, w, i, brdW, brdH);
        }
        void XFillOvbl(long pXSDbtb, long xgd,
                       int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11FillOvbl");
            supfr.XFillOvbl(pXSDbtb, xgd, x, y, w, i);
        }
        void XFillArd(long pXSDbtb, long xgd,
                      int x, int y, int w, int i,
                      int bnglfStbrt, int bnglfExtfnt)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11FillArd");
            supfr.XFillArd(pXSDbtb, xgd,
                           x, y, w, i, bnglfStbrt, bnglfExtfnt);
        }
        void XFillPoly(long pXSDbtb, long xgd,
                       int trbnsx, int trbnsy,
                       int[] xpoints, int[] ypoints,
                       int npoints)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11FillPoly");
            supfr.XFillPoly(pXSDbtb, xgd,
                            trbnsx, trbnsy, xpoints, ypoints, npoints);
        }
        void XFillSpbns(long pXSDbtb, long xgd,
                        SpbnItfrbtor si, long itfrbtor, int trbnsx, int trbnsy)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11FillSpbns");
            supfr.XFillSpbns(pXSDbtb, xgd,
                             si, itfrbtor, trbnsx, trbnsy);
        }
        void dfvCopyArfb(long sdOps, long xgd,
                         int srdx, int srdy,
                         int dstx, int dsty,
                         int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11CopyArfb");
            supfr.dfvCopyArfb(sdOps, xgd, srdx, srdy, dstx, dsty, w, i);
        }
    }
}
