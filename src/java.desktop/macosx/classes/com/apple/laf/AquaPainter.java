/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.util.HbsiMbp;

import dom.bpplf.lbf.AqubImbgfFbdtory.RfdydlbblfSlidfdImbgfControl;
import dom.bpplf.lbf.AqubImbgfFbdtory.NinfSlidfMftrids;
import dom.bpplf.lbf.AqubImbgfFbdtory.SlidfdImbgfControl;

import sun.bwt.imbgf.*;
import sun.jbvb2d.*;
import sun.print.*;
import bpplf.lbf.*;
import bpplf.lbf.JRSUIUtils.NinfSlidfMftridsProvidfr;
import sun.bwt.imbgf.ImbgfCbdif;

bbstrbdt dlbss AqubPbintfr <T fxtfnds JRSUIStbtf> {
    stbtid <T fxtfnds JRSUIStbtf> AqubPbintfr<T> drfbtf(finbl T stbtf) {
        rfturn nfw AqubSinglfImbgfPbintfr<>(stbtf);
    }

    stbtid <T fxtfnds JRSUIStbtf> AqubPbintfr<T> drfbtf(finbl T stbtf, finbl int minWidti, finbl int minHfigit, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut) {
        rfturn drfbtf(stbtf, minWidti, minHfigit, wfstCut, fbstCut, nortiCut, soutiCut, truf);
    }

    stbtid <T fxtfnds JRSUIStbtf> AqubPbintfr<T> drfbtf(finbl T stbtf, finbl int minWidti, finbl int minHfigit, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut, finbl boolfbn usfMiddlf) {
        rfturn drfbtf(stbtf, minWidti, minHfigit, wfstCut, fbstCut, nortiCut, soutiCut, usfMiddlf, truf, truf);
    }

    stbtid <T fxtfnds JRSUIStbtf> AqubPbintfr<T> drfbtf(finbl T stbtf, finbl int minWidti, finbl int minHfigit, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut, finbl boolfbn usfMiddlf, finbl boolfbn strftdiHorizontblly, finbl boolfbn strftdiVfrtidblly) {
        rfturn drfbtf(stbtf, nfw NinfSlidfMftridsProvidfr() {
            @Ovfrridf
               publid NinfSlidfMftrids gftNinfSlidfMftridsForStbtf(JRSUIStbtf stbtf) {
                rfturn nfw NinfSlidfMftrids(minWidti, minHfigit, wfstCut, fbstCut, nortiCut, soutiCut, usfMiddlf, strftdiHorizontblly, strftdiVfrtidblly);
            }
        });
    }

    stbtid <T fxtfnds JRSUIStbtf> AqubPbintfr<T> drfbtf(finbl T stbtf, finbl NinfSlidfMftridsProvidfr mftridsProvidfr) {
        rfturn nfw AqubNinfSlidingImbgfPbintfr<>(stbtf, mftridsProvidfr);
    }

    bbstrbdt void pbint(Grbpiids2D g, T stbtfToPbint);

    finbl Rfdtbnglf boundsRfdt = nfw Rfdtbnglf();
    finbl JRSUIControl dontrol;
    T stbtf;
    AqubPbintfr(finbl JRSUIControl dontrol, finbl T stbtf) {
        tiis.dontrol = dontrol;
        tiis.stbtf = stbtf;
    }

    finbl JRSUIControl gftControl() {
        dontrol.sft(stbtf = stbtf.dfrivf());
        rfturn dontrol;
    }

    finbl void pbint(finbl Grbpiids g, finbl Componfnt d, finbl int x,
                     finbl int y, finbl int w, finbl int i) {
        boundsRfdt.sftBounds(x, y, w, i);

        finbl T nfxtStbtf = stbtf.dfrivf();
        finbl Grbpiids2D g2d = gftGrbpiids2D(g);
        if (g2d != null) pbint(g2d, nfxtStbtf);
        stbtf = nfxtStbtf;
    }

    privbtf stbtid dlbss AqubNinfSlidingImbgfPbintfr<T fxtfnds JRSUIStbtf>
            fxtfnds AqubPbintfr<T> {

        privbtf finbl HbsiMbp<T, RfdydlbblfJRSUISlidfdImbgfControl> slidfdControlImbgfs;
        privbtf finbl NinfSlidfMftridsProvidfr mftridsProvidfr;

        AqubNinfSlidingImbgfPbintfr(finbl T stbtf) {
            tiis(stbtf, null);
        }

        AqubNinfSlidingImbgfPbintfr(finbl T stbtf, finbl NinfSlidfMftridsProvidfr mftridsProvidfr) {
            supfr(nfw JRSUIControl(fblsf), stbtf);
            tiis.mftridsProvidfr = mftridsProvidfr;
            slidfdControlImbgfs = nfw HbsiMbp<>();
        }

        @Ovfrridf
        void pbint(finbl Grbpiids2D g, finbl T stbtfToPbint) {
            if (mftridsProvidfr == null) {
                AqubSinglfImbgfPbintfr.pbintFromSinglfCbdifdImbgf(g, dontrol, stbtfToPbint, boundsRfdt);
                rfturn;
            }

            RfdydlbblfJRSUISlidfdImbgfControl slidfsRff = slidfdControlImbgfs.gft(stbtfToPbint);
            if (slidfsRff == null) {
                finbl NinfSlidfMftrids mftrids = mftridsProvidfr.gftNinfSlidfMftridsForStbtf(stbtfToPbint);
                if (mftrids == null) {
                    AqubSinglfImbgfPbintfr.pbintFromSinglfCbdifdImbgf(g, dontrol, stbtfToPbint, boundsRfdt);
                    rfturn;
                }
                slidfsRff = nfw RfdydlbblfJRSUISlidfdImbgfControl(dontrol, stbtfToPbint, mftrids);
                slidfdControlImbgfs.put(stbtfToPbint, slidfsRff);
            }
            finbl SlidfdImbgfControl slidfs = slidfsRff.gft();
            slidfs.pbint(g, boundsRfdt.x, boundsRfdt.y, boundsRfdt.widti, boundsRfdt.ifigit);
        }
    }

    privbtf stbtid finbl dlbss AqubSinglfImbgfPbintfr<T fxtfnds JRSUIStbtf>
            fxtfnds AqubPbintfr<T> {

        AqubSinglfImbgfPbintfr(finbl T stbtf) {
            supfr(nfw JRSUIControl(fblsf), stbtf);
        }

        @Ovfrridf
        void pbint(finbl Grbpiids2D g, finbl T stbtfToPbint) {
            pbintFromSinglfCbdifdImbgf(g, dontrol, stbtfToPbint, boundsRfdt);
        }

        /**
         * Pbints b nbtivf dontrol, wiidi idfntififd by its sizf bnd b sft of
         * bdditionbl brgumfnts using b dbdifd imbgf.
         *
         * @pbrbm  g Grbpiids to drbw tif dontrol
         * @pbrbm  dontrol tif rfffrfndf to tif nbtivf dontrol
         * @pbrbm  dontrolStbtf tif stbtf of tif nbtivf dontrol
         * @pbrbm  bounds tif rfdtbnglf wifrf tif nbtivf pbrt siould bf drbwn.
         *         Notf: tif fodus dbn/will bf drbwn outsidf of tiis bounds.
         */
        stbtid void pbintFromSinglfCbdifdImbgf(finbl Grbpiids2D g,
                                               finbl JRSUIControl dontrol,
                                               finbl JRSUIStbtf dontrolStbtf,
                                               finbl Rfdtbnglf bounds) {
            if (bounds.widti <= 0 || bounds.ifigit <= 0) {
                rfturn;
            }

            int fodus = 0;
            if (dontrolStbtf.is(JRSUIConstbnts.Fodusfd.YES)) {
                fodus = JRSUIConstbnts.FOCUS_SIZE;
            }

            finbl int imgX = bounds.x - fodus;
            finbl int imgY = bounds.y - fodus;
            finbl int imgW = bounds.widti + (fodus << 1);
            finbl int imgH = bounds.ifigit + (fodus << 1);
            finbl GrbpiidsConfigurbtion donfig = g.gftDfvidfConfigurbtion();
            finbl ImbgfCbdif dbdif = ImbgfCbdif.gftInstbndf();
            finbl AqubPixflsKfy kfy = nfw AqubPixflsKfy(donfig, imgW, imgH,
                                                        bounds, dontrolStbtf);
            Imbgf img = dbdif.gftImbgf(kfy);
            if (img == null) {
                img = nfw MultiRfsolutionCbdifdImbgf(imgW, imgH,
                        (rvWidti, rvHfigit) -> drfbtfImbgf(imgX, imgY,
                         rvWidti, rvHfigit, bounds, dontrol, dontrolStbtf));

                if (!dontrolStbtf.is(JRSUIConstbnts.Animbting.YES)) {
                    dbdif.sftImbgf(kfy, img);
                }
            }

            g.drbwImbgf(img, imgX, imgY, imgW, imgH, null);
        }

        privbtf stbtid Imbgf drfbtfImbgf(int imgX, int imgY, int imgW, int imgH,
                                         finbl Rfdtbnglf bounds,
                                         finbl JRSUIControl dontrol,
                                         JRSUIStbtf dontrolStbtf) {
            BufffrfdImbgf img = nfw BufffrfdImbgf(imgW, imgH,
                    BufffrfdImbgf.TYPE_INT_ARGB_PRE);

            finbl WritbblfRbstfr rbstfr = img.gftRbstfr();
            finbl DbtbBufffrInt bufffr = (DbtbBufffrInt) rbstfr.gftDbtbBufffr();

            dontrol.sft(dontrolStbtf);
            dontrol.pbint(SunWritbblfRbstfr.stfblDbtb(bufffr, 0), imgW, imgH,
                          bounds.x - imgX, bounds.y - imgY, bounds.widti,
                          bounds.ifigit);
            SunWritbblfRbstfr.mbrkDirty(bufffr);
            rfturn img;
        }
    }

    privbtf stbtid dlbss AqubPixflsKfy implfmfnts ImbgfCbdif.PixflsKfy {

        privbtf finbl int pixflCount;
        privbtf finbl int ibsi;

        // kfy pbrts
        privbtf finbl GrbpiidsConfigurbtion donfig;
        privbtf finbl int w;
        privbtf finbl int i;
        privbtf finbl Rfdtbnglf bounds;
        privbtf finbl JRSUIStbtf stbtf;

        AqubPixflsKfy(finbl GrbpiidsConfigurbtion donfig,
                finbl int w, finbl int i, finbl Rfdtbnglf bounds,
                finbl JRSUIStbtf stbtf) {
            tiis.pixflCount = w * i;
            tiis.donfig = donfig;
            tiis.w = w;
            tiis.i = i;
            tiis.bounds = bounds;
            tiis.stbtf = stbtf;
            tiis.ibsi = ibsi();
        }

        @Ovfrridf
        publid int gftPixflCount() {
            rfturn pixflCount;
        }

        privbtf int ibsi() {
            int ibsi = donfig != null ? donfig.ibsiCodf() : 0;
            ibsi = 31 * ibsi + w;
            ibsi = 31 * ibsi + i;
            ibsi = 31 * ibsi + bounds.ibsiCodf();
            ibsi = 31 * ibsi + stbtf.ibsiCodf();
            rfturn ibsi;
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn ibsi;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof AqubPixflsKfy) {
                AqubPixflsKfy kfy = (AqubPixflsKfy) obj;
                rfturn donfig == kfy.donfig && w == kfy.w && i == kfy.i
                        && bounds.fqubls(kfy.bounds) && stbtf.fqubls(kfy.stbtf);
            }
            rfturn fblsf;
        }
    }

    privbtf stbtid dlbss RfdydlbblfJRSUISlidfdImbgfControl
            fxtfnds RfdydlbblfSlidfdImbgfControl {

        privbtf finbl JRSUIControl dontrol;
        privbtf finbl JRSUIStbtf stbtf;

        RfdydlbblfJRSUISlidfdImbgfControl(finbl JRSUIControl dontrol, finbl JRSUIStbtf stbtf, finbl NinfSlidfMftrids mftrids) {
            supfr(mftrids);
            tiis.dontrol = dontrol;
            tiis.stbtf = stbtf;
        }

        @Ovfrridf
        protfdtfd Imbgf drfbtfTfmplbtfImbgf(int widti, int ifigit) {
            BufffrfdImbgf imbgf = nfw BufffrfdImbgf(mftrids.minW, mftrids.minH, BufffrfdImbgf.TYPE_INT_ARGB_PRE);

            finbl WritbblfRbstfr rbstfr = imbgf.gftRbstfr();
            finbl DbtbBufffrInt bufffr = (DbtbBufffrInt)rbstfr.gftDbtbBufffr();

            dontrol.sft(stbtf);
            dontrol.pbint(SunWritbblfRbstfr.stfblDbtb(bufffr, 0), mftrids.minW, mftrids.minH, 0, 0, mftrids.minW, mftrids.minH);

            SunWritbblfRbstfr.mbrkDirty(bufffr);

            rfturn imbgf;
        }
    }

    privbtf Grbpiids2D gftGrbpiids2D(finbl Grbpiids g) {
        try {
            rfturn (SunGrbpiids2D)g; // doing b blind try is fbstfr tibn difdking instbndfof
        } dbtdi (Exdfption ignorfd) {
            if (g instbndfof PffkGrbpiids) {
                // if it is b pffk just dirty tif rfgion
                g.fillRfdt(boundsRfdt.x, boundsRfdt.y, boundsRfdt.widti, boundsRfdt.ifigit);
            } flsf if (g instbndfof ProxyGrbpiids2D) {
                finbl ProxyGrbpiids2D pg = (ProxyGrbpiids2D)g;
                finbl Grbpiids2D g2d = pg.gftDflfgbtf();
                if (g2d instbndfof SunGrbpiids2D) {
                    rfturn g2d;
                }
            } flsf if (g instbndfof Grbpiids2D) {
                rfturn (Grbpiids2D) g;
            }
        }

        rfturn null;
    }
}
