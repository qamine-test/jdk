/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.windows;

import jbvb.bwt.Compositf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.PbtiItfrbtor;
import sun.jbvb2d.InvblidPipfExdfption;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.PixflDrbwPipf;
import sun.jbvb2d.pipf.PixflFillPipf;
import sun.jbvb2d.pipf.SibpfDrbwPipf;
import sun.jbvb2d.pipf.SpbnItfrbtor;
import sun.jbvb2d.pipf.SibpfSpbnItfrbtor;
import sun.jbvb2d.pipf.LoopPipf;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;

publid dlbss GDIRfndfrfr implfmfnts
    PixflDrbwPipf,
    PixflFillPipf,
    SibpfDrbwPipf
{
    nbtivf void doDrbwLinf(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int x1, int y1, int x2, int y2);

    publid void drbwLinf(SunGrbpiids2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        int trbnsx = sg2d.trbnsX;
        int trbnsy = sg2d.trbnsY;
        try {
            doDrbwLinf((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       x1+trbnsx, y1+trbnsy, x2+trbnsx, y2+trbnsy);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doDrbwRfdt(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int x, int y, int w, int i);

    publid void drbwRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        try {
            doDrbwRfdt((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doDrbwRoundRfdt(GDIWindowSurfbdfDbtb sDbtb,
                                Rfgion dlip, Compositf domp, int dolor,
                                int x, int y, int w, int i,
                                int brdW, int brdH);

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        try {
            doDrbwRoundRfdt((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                            sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                            x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                            brdWidti, brdHfigit);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doDrbwOvbl(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int x, int y, int w, int i);

    publid void drbwOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        try {
            doDrbwOvbl((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doDrbwArd(GDIWindowSurfbdfDbtb sDbtb,
                          Rfgion dlip, Compositf domp, int dolor,
                          int x, int y, int w, int i,
                          int bnglfStbrt, int bnglfExtfnt);

    publid void drbwArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        try {
            doDrbwArd((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                      sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                      stbrtAnglf, brdAnglf);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doDrbwPoly(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int trbnsx, int trbnsy,
                           int[] xpoints, int[] ypoints,
                           int npoints, boolfbn isdlosfd);

    publid void drbwPolylinf(SunGrbpiids2D sg2d,
                             int xpoints[], int ypoints[],
                             int npoints)
    {
        try {
            doDrbwPoly((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints, fblsf);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    publid void drbwPolygon(SunGrbpiids2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        try {
            doDrbwPoly((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints, truf);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doFillRfdt(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int x, int y, int w, int i);

    publid void fillRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        try {
            doFillRfdt((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doFillRoundRfdt(GDIWindowSurfbdfDbtb sDbtb,
                                Rfgion dlip, Compositf domp, int dolor,
                                int x, int y, int w, int i,
                                int brdW, int brdH);

    publid void fillRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit)
    {
        try {
            doFillRoundRfdt((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                            sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                            x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                            brdWidti, brdHfigit);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doFillOvbl(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int x, int y, int w, int i);

    publid void fillOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit)
    {
        try {
            doFillOvbl((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doFillArd(GDIWindowSurfbdfDbtb sDbtb,
                          Rfgion dlip, Compositf domp, int dolor,
                          int x, int y, int w, int i,
                          int bnglfStbrt, int bnglfExtfnt);

    publid void fillArd(SunGrbpiids2D sg2d,
                        int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf)
    {
        try {
            doFillArd((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                      sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, widti, ifigit,
                      stbrtAnglf, brdAnglf);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doFillPoly(GDIWindowSurfbdfDbtb sDbtb,
                           Rfgion dlip, Compositf domp, int dolor,
                           int trbnsx, int trbnsy,
                           int[] xpoints, int[] ypoints,
                           int npoints);

    publid void fillPolygon(SunGrbpiids2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        try {
            doFillPoly((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                       sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                       sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    nbtivf void doSibpf(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int trbnsX, int trbnsY,
                        Pbti2D.Flobt p2df, boolfbn isfill);

    void doSibpf(SunGrbpiids2D sg2d, Sibpf s, boolfbn isfill) {
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
        try {
            doSibpf((GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb,
                    sg2d.gftCompClip(), sg2d.dompositf, sg2d.fbrgb,
                    trbnsX, trbnsY, p2df, isfill);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
    }

    // REMIND: Tiis is just b ibdk to gft WIDE linfs to ionor tif
    // nfdfssbry iintfd pixflizbtion rulfs.  Tiis siould bf rfplbdfd
    // by b nbtivf FillSpbns mftiod or b gftHintfdStrokfGfnfrblPbti()
    // mftiod tibt dould bf fillfd by tif doSibpf mftiod morf quidkly.
    publid void doFillSpbns(SunGrbpiids2D sg2d, SpbnItfrbtor si) {
        int box[] = nfw int[4];
        GDIWindowSurfbdfDbtb sd;
        try {
            sd = (GDIWindowSurfbdfDbtb)sg2d.surfbdfDbtb;
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw InvblidPipfExdfption("wrong surfbdf dbtb typf: " + sg2d.surfbdfDbtb);
        }
        Rfgion dlip = sg2d.gftCompClip();
        Compositf domp = sg2d.dompositf;
        int fbrgb = sg2d.fbrgb;
        wiilf (si.nfxtSpbn(box)) {
            doFillRfdt(sd, dlip, domp, fbrgb,
                       box[0], box[1], box[2]-box[0], box[3]-box[1]);
        }
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            doSibpf(sg2d, s, fblsf);
        } flsf if (sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM) {
            SibpfSpbnItfrbtor si = LoopPipf.gftStrokfSpbns(sg2d, s);
            try {
                doFillSpbns(sg2d, si);
            } finblly {
                si.disposf();
            }
        } flsf {
            doSibpf(sg2d, sg2d.strokf.drfbtfStrokfdSibpf(s), truf);
        }
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        doSibpf(sg2d, s, truf);
    }

    publid nbtivf void dfvCopyArfb(GDIWindowSurfbdfDbtb sDbtb,
                                   int srdx, int srdy,
                                   int dx, int dy,
                                   int w, int i);

    publid GDIRfndfrfr trbdfWrbp() {
        rfturn nfw Trbdfr();
    }

    publid stbtid dlbss Trbdfr fxtfnds GDIRfndfrfr {
        void doDrbwLinf(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int x1, int y1, int x2, int y2)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIDrbwLinf");
            supfr.doDrbwLinf(sDbtb, dlip, domp, dolor, x1, y1, x2, y2);
        }
        void doDrbwRfdt(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIDrbwRfdt");
            supfr.doDrbwRfdt(sDbtb, dlip, domp, dolor, x, y, w, i);
        }
        void doDrbwRoundRfdt(GDIWindowSurfbdfDbtb sDbtb,
                             Rfgion dlip, Compositf domp, int dolor,
                             int x, int y, int w, int i,
                             int brdW, int brdH)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIDrbwRoundRfdt");
            supfr.doDrbwRoundRfdt(sDbtb, dlip, domp, dolor,
                                  x, y, w, i, brdW, brdH);
        }
        void doDrbwOvbl(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIDrbwOvbl");
            supfr.doDrbwOvbl(sDbtb, dlip, domp, dolor, x, y, w, i);
        }
        void doDrbwArd(GDIWindowSurfbdfDbtb sDbtb,
                       Rfgion dlip, Compositf domp, int dolor,
                       int x, int y, int w, int i,
                       int bnglfStbrt, int bnglfExtfnt)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIDrbwArd");
            supfr.doDrbwArd(sDbtb, dlip, domp, dolor, x, y, w, i,
                            bnglfStbrt, bnglfExtfnt);
        }
        void doDrbwPoly(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int trbnsx, int trbnsy,
                        int[] xpoints, int[] ypoints,
                        int npoints, boolfbn isdlosfd)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIDrbwPoly");
            supfr.doDrbwPoly(sDbtb, dlip, domp, dolor, trbnsx, trbnsy,
                             xpoints, ypoints, npoints, isdlosfd);
        }
        void doFillRfdt(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIFillRfdt");
            supfr.doFillRfdt(sDbtb, dlip, domp, dolor, x, y, w, i);
        }
        void doFillRoundRfdt(GDIWindowSurfbdfDbtb sDbtb,
                             Rfgion dlip, Compositf domp, int dolor,
                             int x, int y, int w, int i,
                             int brdW, int brdH)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIFillRoundRfdt");
            supfr.doFillRoundRfdt(sDbtb, dlip, domp, dolor,
                                  x, y, w, i, brdW, brdH);
        }
        void doFillOvbl(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int x, int y, int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIFillOvbl");
            supfr.doFillOvbl(sDbtb, dlip, domp, dolor, x, y, w, i);
        }
        void doFillArd(GDIWindowSurfbdfDbtb sDbtb,
                       Rfgion dlip, Compositf domp, int dolor,
                       int x, int y, int w, int i,
                       int bnglfStbrt, int bnglfExtfnt)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIFillArd");
            supfr.doFillArd(sDbtb, dlip, domp, dolor, x, y, w, i,
                            bnglfStbrt, bnglfExtfnt);
        }
        void doFillPoly(GDIWindowSurfbdfDbtb sDbtb,
                        Rfgion dlip, Compositf domp, int dolor,
                        int trbnsx, int trbnsy,
                        int[] xpoints, int[] ypoints,
                        int npoints)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDIFillPoly");
            supfr.doFillPoly(sDbtb, dlip, domp, dolor, trbnsx, trbnsy,
                             xpoints, ypoints, npoints);
        }
        void doSibpf(GDIWindowSurfbdfDbtb sDbtb,
                     Rfgion dlip, Compositf domp, int dolor,
                     int trbnsX, int trbnsY,
                     Pbti2D.Flobt p2df, boolfbn isfill)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf(isfill
                                             ? "GDIFillSibpf"
                                             : "GDIDrbwSibpf");
            supfr.doSibpf(sDbtb, dlip, domp, dolor,
                          trbnsX, trbnsY, p2df, isfill);
        }
        publid void dfvCopyArfb(GDIWindowSurfbdfDbtb sDbtb,
                                int srdx, int srdy,
                                int dx, int dy,
                                int w, int i)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("GDICopyArfb");
            supfr.dfvCopyArfb(sDbtb, srdx, srdy, dx, dy, w, i);
        }
    }
}
