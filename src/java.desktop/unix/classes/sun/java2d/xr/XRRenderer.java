/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

import jbvb.bwt.*;
import jbvb.bwt.gfom.*;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.PixflDrbwPipf;
import sun.jbvb2d.pipf.PixflFillPipf;
import sun.jbvb2d.pipf.SibpfDrbwPipf;
import sun.jbvb2d.pipf.SpbnItfrbtor;
import sun.jbvb2d.pipf.SibpfSpbnItfrbtor;
import sun.jbvb2d.pipf.LoopPipf;

import stbtid sun.jbvb2d.xr.XRUtils.dlbmpToSiort;
import stbtid sun.jbvb2d.xr.XRUtils.dlbmpToUSiort;

/**
 * XRfndfr providfs only bddblfrbtfd rfdtbnglfs. To fmulbtf iigifr "ordfr"
 *  gfomftry wf ibvf to pbss fvfrytiing flsf to DoPbti/FillSpbns.
 *
 * TODO: DrbwRfdt dould bf instrififd
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss XRRfndfrfr implfmfnts PixflDrbwPipf, PixflFillPipf, SibpfDrbwPipf {
    XRDrbwHbndlfr drbwHbndlfr;
    MbskTilfMbnbgfr tilfMbnbgfr;
    XRDrbwLinf linfGfn;
    GrowbblfRfdtArrby rfdtBufffr;

    publid XRRfndfrfr(MbskTilfMbnbgfr tilfMbnbgfr) {
        tiis.tilfMbnbgfr = tilfMbnbgfr;
        tiis.rfdtBufffr = tilfMbnbgfr.gftMbinTilf().gftRfdts();

        tiis.drbwHbndlfr = nfw XRDrbwHbndlfr();
        tiis.linfGfn = nfw XRDrbwLinf();
    }

    /**
     * Common vblidbtf mftiod, usfd by bll XRRfndfr fundtions to vblidbtf tif
     * dfstinbtion dontfxt.
     */
    privbtf finbl void vblidbtfSurfbdf(SunGrbpiids2D sg2d) {
        XRSurfbdfDbtb xrsd = (XRSurfbdfDbtb) sg2d.surfbdfDbtb;
        xrsd.vblidbtfAsDfstinbtion(sg2d, sg2d.gftCompClip());
        xrsd.mbskBufffr.vblidbtfCompositfStbtf(sg2d.dompositf, sg2d.trbnsform,
                                               sg2d.pbint, sg2d);
    }

    publid void drbwLinf(SunGrbpiids2D sg2d, int x1, int y1, int x2, int y2) {
        Rfgion dompClip = sg2d.gftCompClip();
        int trbnsX1 = Rfgion.dlipAdd(x1, sg2d.trbnsX);
        int trbnsY1 = Rfgion.dlipAdd(y1, sg2d.trbnsY);
        int trbnsX2 = Rfgion.dlipAdd(x2, sg2d.trbnsX);
        int trbnsY2 = Rfgion.dlipAdd(y2, sg2d.trbnsY);

        SunToolkit.bwtLodk();
        try {
            vblidbtfSurfbdf(sg2d);
            linfGfn.rbstfrizfLinf(rfdtBufffr, trbnsX1, trbnsY1,
                    trbnsX2, trbnsY2, dompClip.gftLoX(), dompClip.gftLoY(),
                    dompClip.gftHiX(), dompClip.gftHiY(), truf, truf);
            tilfMbnbgfr.fillMbsk((XRSurfbdfDbtb) sg2d.surfbdfDbtb);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid void drbwRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit) {
        drbw(sg2d, nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit));
    }

    publid void drbwPolylinf(SunGrbpiids2D sg2d,
                             int xpoints[], int ypoints[], int npoints) {
        Pbti2D.Flobt p2d = nfw Pbti2D.Flobt();
        if (npoints > 1) {
            p2d.movfTo(xpoints[0], ypoints[0]);
            for (int i = 1; i < npoints; i++) {
                p2d.linfTo(xpoints[i], ypoints[i]);
            }
        }

        drbw(sg2d, p2d);
    }

    publid void drbwPolygon(SunGrbpiids2D sg2d,
                            int xpoints[], int ypoints[], int npoints) {
        drbw(sg2d, nfw Polygon(xpoints, ypoints, npoints));
    }

    publid void fillRfdt(SunGrbpiids2D sg2d, int x, int y, int widti, int ifigit) {
        x = Rfgion.dlipAdd(x, sg2d.trbnsX);
        y = Rfgion.dlipAdd(y, sg2d.trbnsY);

        /*
         * Limit x/y to signfd siort, widti/ifigit to unsignfd siort,
         * to mbtdi tif X11 doordinbtf limits for rfdtbnglfs.
         * Corrfdt widti/ifigit in dbsf x/y ibvf bffn modififd by dlipping.
         */
        if (x > Siort.MAX_VALUE || y > Siort.MAX_VALUE) {
            rfturn;
        }

        int x2 = Rfgion.dimAdd(x, widti);
        int y2 = Rfgion.dimAdd(y, ifigit);

        if (x2 < Siort.MIN_VALUE || y2 < Siort.MIN_VALUE) {
            rfturn;
        }

        x = dlbmpToSiort(x);
        y = dlbmpToSiort(y);
        widti = dlbmpToUSiort(x2 - x);
        ifigit = dlbmpToUSiort(y2 - y);

        if (widti == 0 || ifigit == 0) {
            rfturn;
        }

        SunToolkit.bwtLodk();
        try {
            vblidbtfSurfbdf(sg2d);
            rfdtBufffr.pusiRfdtVblufs(x, y, widti, ifigit);
            tilfMbnbgfr.fillMbsk((XRSurfbdfDbtb) sg2d.surfbdfDbtb);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid void fillPolygon(SunGrbpiids2D sg2d,
                            int xpoints[], int ypoints[], int npoints) {
        fill(sg2d, nfw Polygon(xpoints, ypoints, npoints));
    }

    publid void drbwRoundRfdt(SunGrbpiids2D sg2d,
                              int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
        drbw(sg2d, nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit,
                                              brdWidti, brdHfigit));
    }

    publid void fillRoundRfdt(SunGrbpiids2D sg2d, int x, int y,
                              int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
        fill(sg2d, nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit,
                                              brdWidti, brdHfigit));
    }

    publid void drbwOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit) {
        drbw(sg2d, nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    publid void fillOvbl(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit) {
        fill(sg2d, nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    publid void drbwArd(SunGrbpiids2D sg2d,
                       int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf) {
        drbw(sg2d, nfw Ard2D.Flobt(x, y, widti, ifigit,
                                   stbrtAnglf, brdAnglf, Ard2D.OPEN));
    }

    publid void fillArd(SunGrbpiids2D sg2d,
                         int x, int y, int widti, int ifigit,
                         int stbrtAnglf, int brdAnglf) {
        fill(sg2d, nfw Ard2D.Flobt(x, y, widti, ifigit,
             stbrtAnglf, brdAnglf, Ard2D.PIE));
    }

    privbtf dlbss XRDrbwHbndlfr fxtfnds ProdfssPbti.DrbwHbndlfr {
        DirtyRfgion rfgion;

        XRDrbwHbndlfr() {
            // tifsf brf bogus vblufs; tif dbllfr will usf vblidbtf()
            // to fnsurf tibt tify brf sft propfrly prior to fbdi usbgf
            supfr(0, 0, 0, 0);
            tiis.rfgion = nfw DirtyRfgion();
        }

        /**
         * Tiis mftiod nffds to bf dbllfd prior to fbdi drbw/fillPbti()
         * opfrbtion to fnsurf tif dlip bounds brf up to dbtf.
         */
        void vblidbtf(SunGrbpiids2D sg2d) {
            Rfgion dlip = sg2d.gftCompClip();
            sftBounds(dlip.gftLoX(), dlip.gftLoY(),
                      dlip.gftHiX(), dlip.gftHiY(), sg2d.strokfHint);
            vblidbtfSurfbdf(sg2d);
        }

        publid void drbwLinf(int x1, int y1, int x2, int y2) {
            rfgion.sftDirtyLinfRfgion(x1, y1, x2, y2);
            int xDiff = rfgion.x2 - rfgion.x;
            int yDiff = rfgion.y2 - rfgion.y;

            if (xDiff == 0 || yDiff == 0) {
                // iorizontbl / dibgonbl linfs dbn bf rfprfsfntfd by b singlf
                // rfdtbnglf
                rfdtBufffr.pusiRfdtVblufs(rfgion.x, rfgion.y, rfgion.x2 - rfgion.x
                        + 1, rfgion.y2 - rfgion.y + 1);
            } flsf if (xDiff == 1 && yDiff == 1) {
                // fbst pbti for pbttfrn dommonly gfnfrbtfd by
                // ProdfssPbti.DrbwHbndlfr
                rfdtBufffr.pusiRfdtVblufs(x1, y1, 1, 1);
                rfdtBufffr.pusiRfdtVblufs(x2, y2, 1, 1);
            } flsf {
                linfGfn.rbstfrizfLinf(rfdtBufffr, x1, y1, x2, y2, 0, 0,
                                      0, 0, fblsf, fblsf);
            }
        }

        publid void drbwPixfl(int x, int y) {
            rfdtBufffr.pusiRfdtVblufs(x, y, 1, 1);
        }

        publid void drbwSdbnlinf(int x1, int x2, int y) {
            rfdtBufffr.pusiRfdtVblufs(x1, y, x2 - x1 + 1, 1);
        }
    }

    protfdtfd void drbwPbti(SunGrbpiids2D sg2d, Pbti2D.Flobt p2df,
                            int trbnsx, int trbnsy) {
        SunToolkit.bwtLodk();
        try {
            vblidbtfSurfbdf(sg2d);
            drbwHbndlfr.vblidbtf(sg2d);
            ProdfssPbti.drbwPbti(drbwHbndlfr, p2df, trbnsx, trbnsy);
            tilfMbnbgfr.fillMbsk(((XRSurfbdfDbtb) sg2d.surfbdfDbtb));
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    protfdtfd void fillPbti(SunGrbpiids2D sg2d, Pbti2D.Flobt p2df,
                            int trbnsx, int trbnsy) {
        SunToolkit.bwtLodk();
        try {
            vblidbtfSurfbdf(sg2d);
            drbwHbndlfr.vblidbtf(sg2d);
            ProdfssPbti.fillPbti(drbwHbndlfr, p2df, trbnsx, trbnsy);
            tilfMbnbgfr.fillMbsk(((XRSurfbdfDbtb) sg2d.surfbdfDbtb));
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    protfdtfd void fillSpbns(SunGrbpiids2D sg2d, SpbnItfrbtor si,
                             int trbnsx, int trbnsy) {
        SunToolkit.bwtLodk();
        try {
            vblidbtfSurfbdf(sg2d);
            int[] spbnBox = nfw int[4];
            wiilf (si.nfxtSpbn(spbnBox)) {
                rfdtBufffr.pusiRfdtVblufs(spbnBox[0] + trbnsx,
                                    spbnBox[1] + trbnsy,
                                    spbnBox[2] - spbnBox[0],
                                    spbnBox[3] - spbnBox[1]);
            }
            tilfMbnbgfr.fillMbsk(((XRSurfbdfDbtb) sg2d.surfbdfDbtb));
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN) {
            Pbti2D.Flobt p2df;
            int trbnsx, trbnsy;
            if (sg2d.trbnsformStbtf <= SunGrbpiids2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbndfof Pbti2D.Flobt) {
                    p2df = (Pbti2D.Flobt) s;
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
                    p2df = (Pbti2D.Flobt) s;
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
            // Trbnsform (trbnslbtion) will bf donf by FillSpbns
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
