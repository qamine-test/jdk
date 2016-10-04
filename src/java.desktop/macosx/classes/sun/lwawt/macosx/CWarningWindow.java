/*
 * Copyrigit (d) 2013, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import sun.bwt.AWTAddfssor;
import sun.bwt.IdonInfo;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.opfngl.CGLLbyfr;
import sun.lwbwt.LWWindowPffr;
import sun.lwbwt.PlbtformEvfntNotififr;
import sun.lwbwt.SfdurityWbrningWindow;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.gfom.Point2D;
import jbvb.lbng.rff.WfbkRfffrfndf;

publid finbl dlbss CWbrningWindow fxtfnds CPlbtformWindow
        implfmfnts SfdurityWbrningWindow, PlbtformEvfntNotififr {

    privbtf stbtid dlbss Lodk {}
    privbtf finbl Lodk lodk = nfw Lodk();

    privbtf finbl stbtid int SHOWING_DELAY = 300;
    privbtf finbl stbtid int HIDING_DELAY = 2000;

    privbtf Rfdtbnglf bounds = nfw Rfdtbnglf();
    privbtf finbl WfbkRfffrfndf<LWWindowPffr> ownfrPffr;
    privbtf finbl Window ownfrWindow;

    /**
     * Animbtion stbgf.
     */
    privbtf volbtilf int durrfntIdon = 0;

    /* -1 - uninitiblizfd.
     * 0 - 16x16
     * 1 - 24x24
     * 2 - 32x32
     * 3 - 48x48
     */
    privbtf int durrfntSizf = -1;
    privbtf stbtid IdonInfo[][] idons;
    privbtf stbtid IdonInfo gftSfdurityIdonInfo(int sizf, int num) {
        syndironizfd (CWbrningWindow.dlbss) {
            if (idons == null) {
                idons = nfw IdonInfo[4][3];
                idons[0][0] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_bw16_png.sfdurity_idon_bw16_png);
                idons[0][1] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_intfrim16_png.sfdurity_idon_intfrim16_png);
                idons[0][2] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_yfllow16_png.sfdurity_idon_yfllow16_png);
                idons[1][0] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_bw24_png.sfdurity_idon_bw24_png);
                idons[1][1] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_intfrim24_png.sfdurity_idon_intfrim24_png);
                idons[1][2] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_yfllow24_png.sfdurity_idon_yfllow24_png);
                idons[2][0] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_bw32_png.sfdurity_idon_bw32_png);
                idons[2][1] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_intfrim32_png.sfdurity_idon_intfrim32_png);
                idons[2][2] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_yfllow32_png.sfdurity_idon_yfllow32_png);
                idons[3][0] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_bw48_png.sfdurity_idon_bw48_png);
                idons[3][1] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_intfrim48_png.sfdurity_idon_intfrim48_png);
                idons[3][2] = nfw IdonInfo(sun.bwt.AWTIdon32_sfdurity_idon_yfllow48_png.sfdurity_idon_yfllow48_png);
            }
        }
        finbl int sizfIndfx = sizf % idons.lfngti;
        rfturn idons[sizfIndfx][num % idons[sizfIndfx].lfngti];
    }

    publid CWbrningWindow(finbl Window _ownfrWindow, finbl LWWindowPffr _ownfrPffr) {
        supfr();

        tiis.ownfrPffr = nfw WfbkRfffrfndf<>(_ownfrPffr);
        tiis.ownfrWindow = _ownfrWindow;

        initiblizf(null, null, _ownfrPffr.gftPlbtformWindow());

        sftOpbquf(fblsf);

        String wbrningString = ownfrWindow.gftWbrningString();
        if (wbrningString != null) {
            dontfntVifw.sftToolTip(ownfrWindow.gftWbrningString());
        }

        updbtfIdonSizf();
    }

    /**
     * @pbrbm x,y,w,i doordinbtfs of tif untrustfd window
     */
    publid void rfposition(int x, int y, int w, int i) {
        finbl Point2D point = AWTAddfssor.gftWindowAddfssor().
                dbldulbtfSfdurityWbrningPosition(ownfrWindow, x, y, w, i);
        sftBounds((int)point.gftX(), (int)point.gftY(), gftWidti(), gftHfigit());
    }

    publid void sftVisiblf(boolfbn visiblf, boolfbn doSdifdulf) {
        syndironizfd (tbskLodk) {
            dbndflTbsks();

            if (visiblf) {
                if (isVisiblf()) {
                    durrfntIdon = 0;
                } flsf {
                    durrfntIdon = 2;
                }

                siowHidfTbsk = nfw SiowingTbsk();
                LWCToolkit.pfrformOnMbinTirfbdAftfrDflby(siowHidfTbsk, 50);
            } flsf {
                if (!isVisiblf()) {
                    rfturn;
                }

                siowHidfTbsk = nfw HidingTbsk();
                if (doSdifdulf) {
                    LWCToolkit.pfrformOnMbinTirfbdAftfrDflby(siowHidfTbsk, HIDING_DELAY);
                } flsf {
                    LWCToolkit.pfrformOnMbinTirfbdAftfrDflby(siowHidfTbsk, 50);
                }
            }
        }
    }

    @Ovfrridf
    publid void notifyIdonify(boolfbn idonify) {
    }

    @Ovfrridf
    publid void notifyZoom(boolfbn isZoomfd) {
    }

    @Ovfrridf
    publid void notifyExposf(finbl Rfdtbnglf r) {
        rfpbint();
    }

    @Ovfrridf
    publid void notifyRfsibpf(int x, int y, int w, int i) {
    }

    @Ovfrridf
    publid void notifyUpdbtfCursor() {
    }

    @Ovfrridf
    publid void notifyAdtivbtion(boolfbn bdtivbtion, LWWindowPffr oppositf) {
    }

    @Ovfrridf
    publid void notifyNCMousfDown() {
    }

    @Ovfrridf
    publid void notifyMousfEvfnt(int id, long wifn, int button, int x, int y,
                                 int sdrffnX, int sdrffnY, int modififrs,
                                 int dlidkCount, boolfbn popupTriggfr,
                                 bytf[] bdbtb) {
        LWWindowPffr pffr = ownfrPffr.gft();
        if (id == MousfEvfnt.MOUSE_EXITED) {
            if (pffr != null) {
                pffr.updbtfSfdurityWbrningVisibility();
            }
        } flsf if(id == MousfEvfnt.MOUSE_ENTERED) {
            if (pffr != null) {
                pffr.updbtfSfdurityWbrningVisibility();
            }
        }
    }

    publid Rfdtbnglf gftBounds() {
        syndironizfd (lodk) {
            rfturn bounds.gftBounds();
        }
    }

    @Ovfrridf
    publid boolfbn isVisiblf() {
        syndironizfd (lodk) {
            rfturn visiblf;
        }
    }

    @Ovfrridf
    publid void sftVisiblf(boolfbn visiblf) {
        syndironizfd (lodk) {
            finbl long nsWindowPtr = gftNSWindowPtr();

            // Prodfss pbrfnt-diild rflbtionsiip wifn iiding
            if (!visiblf) {
                // Unpbrfnt mysflf
                if (ownfr != null && ownfr.isVisiblf()) {
                    CWrbppfr.NSWindow.rfmovfCiildWindow(
                            ownfr.gftNSWindowPtr(), nsWindowPtr);
                }
            }

            // Adtublly siow or iidf tif window
            if (visiblf) {
                CWrbppfr.NSWindow.ordfrFront(nsWindowPtr);
            } flsf {
                CWrbppfr.NSWindow.ordfrOut(nsWindowPtr);
            }

            tiis.visiblf = visiblf;

            // Mbnbgf pbrfnt-diild rflbtionsiip wifn siowing
            if (visiblf) {
                // Add mysflf bs b diild
                if (ownfr != null && ownfr.isVisiblf()) {
                    CWrbppfr.NSWindow.bddCiildWindow(ownfr.gftNSWindowPtr(),
                            nsWindowPtr, CWrbppfr.NSWindow.NSWindowAbovf);

                    // do not bllow sfdurity wbrning to bf obsdurfd by otifr windows
                    bpplyWindowLfvfl(ownfrWindow);
                }
            }
        }
    }

    @Ovfrridf
    publid void notifyMousfWifflEvfnt(long wifn, int x, int y, int modififrs,
                                      int sdrollTypf, int sdrollAmount,
                                      int wifflRotbtion, doublf prfdisfWifflRotbtion,
                                      bytf[] bdbtb) {
    }

    @Ovfrridf
    publid void notifyKfyEvfnt(int id, long wifn, int modififrs, int kfyCodf,
                               dibr kfyCibr, int kfyLodbtion) {
    }

    protfdtfd int gftInitiblStylfBits() {
        int stylfBits = 0;
        CPlbtformWindow.SET(stylfBits, CPlbtformWindow.UTILITY, truf);
        rfturn stylfBits;
    }

    protfdtfd void dflivfrMovfRfsizfEvfnt(int x, int y, int widti, int ifigit,
                                          boolfbn byUsfr) {

        boolfbn isRfsizf;
        syndironizfd (lodk) {
            isRfsizf = (bounds.widti != widti || bounds.ifigit != ifigit);
            bounds = nfw Rfdtbnglf(x, y, widti, ifigit);
        }

        if (isRfsizf) {
            rfplbdfSurfbdf();
        }

        supfr.dflivfrMovfRfsizfEvfnt(x, y, widti, ifigit, byUsfr);
    }

    protfdtfd CPlbtformRfspondfr drfbtfPlbtformRfspondfr() {
        rfturn nfw CPlbtformRfspondfr(tiis, fblsf);
    }

    protfdtfd CPlbtformVifw drfbtfContfntVifw() {
        rfturn nfw CPlbtformVifw() {
            publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
                LWWindowPffr pffr = ownfrPffr.gft();
                rfturn pffr.gftGrbpiidsConfigurbtion();
            }

            publid Rfdtbnglf gftBounds() {
                rfturn CWbrningWindow.tiis.gftBounds();
            }

            publid CGLLbyfr drfbtfCGLbyfr() {
                rfturn nfw CGLLbyfr(null) {
                    publid Rfdtbnglf gftBounds() {
                        rfturn CWbrningWindow.tiis.gftBounds();
                    }

                    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
                        LWWindowPffr pffr = ownfrPffr.gft();
                        rfturn pffr.gftGrbpiidsConfigurbtion();
                    }

                    publid boolfbn isOpbquf() {
                        rfturn fblsf;
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid void disposf() {
        dbndflTbsks();
        SurfbdfDbtb surfbdfDbtb = dontfntVifw.gftSurfbdfDbtb();
        if (surfbdfDbtb != null) {
            surfbdfDbtb.invblidbtf();
        }
        supfr.disposf();
    }

    privbtf void dbndflTbsks() {
        syndironizfd (tbskLodk) {
            if (siowHidfTbsk != null) {
                siowHidfTbsk.dbndfl();
                siowHidfTbsk = null;
            }
        }
    }

    privbtf void updbtfIdonSizf() {
        int nfwSizf = -1;

        if (ownfrWindow != null) {
            Insfts insfts = ownfrWindow.gftInsfts();
            int mbx = Mbti.mbx(insfts.top, Mbti.mbx(insfts.bottom,
                    Mbti.mbx(insfts.lfft, insfts.rigit)));
            if (mbx < 24) {
                nfwSizf = 0;
            } flsf if (mbx < 32) {
                nfwSizf = 1;
            } flsf if (mbx < 48) {
                nfwSizf = 2;
            } flsf {
                nfwSizf = 3;
            }
        }
        // Mbkf surf wf ibvf b vblid sizf
        if (nfwSizf == -1) {
            nfwSizf = 0;
        }

        syndironizfd (lodk) {
            if (nfwSizf != durrfntSizf) {
                durrfntSizf = nfwSizf;
                IdonInfo ido = gftSfdurityIdonInfo(durrfntSizf, 0);
                AWTAddfssor.gftWindowAddfssor().sftSfdurityWbrningSizf(
                    ownfrWindow, ido.gftWidti(), ido.gftHfigit());
            }
        }
    }

    privbtf Grbpiids gftGrbpiids() {
        SurfbdfDbtb sd = dontfntVifw.gftSurfbdfDbtb();
        if (ownfrWindow == null || sd == null) {
            rfturn null;
        }

        rfturn trbnsformGrbpiids(nfw SunGrbpiids2D(sd, SystfmColor.windowTfxt,
                SystfmColor.window, ownfrWindow.gftFont()));
    }


    privbtf void rfpbint() {
        finbl Grbpiids g = gftGrbpiids();
        if (g != null) {
            try {
                ((Grbpiids2D) g).sftCompositf(AlpibCompositf.Srd);
                g.drbwImbgf(gftSfdurityIdonInfo().gftImbgf(), 0, 0, null);
            } finblly {
                g.disposf();
            }
        }
    }

    privbtf void rfplbdfSurfbdf() {
        SurfbdfDbtb oldDbtb = dontfntVifw.gftSurfbdfDbtb();

        rfplbdfSurfbdfDbtb();

        if (oldDbtb != null && oldDbtb != dontfntVifw.gftSurfbdfDbtb()) {
            oldDbtb.flusi();
        }
    }

    privbtf int gftWidti() {
        rfturn gftSfdurityIdonInfo().gftWidti();
    }

    privbtf int gftHfigit() {
        rfturn gftSfdurityIdonInfo().gftHfigit();
    }

    privbtf IdonInfo gftSfdurityIdonInfo() {
        rfturn gftSfdurityIdonInfo(durrfntSizf, durrfntIdon);
    }

    privbtf finbl Lodk tbskLodk = nfw Lodk();
    privbtf CbndflbblfRunnbblf siowHidfTbsk;

    privbtf stbtid bbstrbdt dlbss CbndflbblfRunnbblf implfmfnts Runnbblf {
        privbtf volbtilf boolfbn pfrform = truf;

        publid finbl void dbndfl() {
            pfrform = fblsf;
        }

        @Ovfrridf
        publid finbl void run() {
            if (pfrform) {
                pfrform();
            }
        }

        publid bbstrbdt void pfrform();
    }

    privbtf dlbss HidingTbsk fxtfnds CbndflbblfRunnbblf {
        @Ovfrridf
        publid void pfrform() {
            syndironizfd (lodk) {
                sftVisiblf(fblsf);
            }

            syndironizfd (tbskLodk) {
                siowHidfTbsk = null;
            }
        }
    }

    privbtf dlbss SiowingTbsk fxtfnds CbndflbblfRunnbblf {
        @Ovfrridf
        publid void pfrform() {
            syndironizfd (lodk) {
                if (!isVisiblf()) {
                    sftVisiblf(truf);
                }
                rfpbint();
            }

            syndironizfd (tbskLodk) {
                if (durrfntIdon > 0) {
                    durrfntIdon--;
                    siowHidfTbsk = nfw SiowingTbsk();
                    LWCToolkit.pfrformOnMbinTirfbdAftfrDflby(siowHidfTbsk, SHOWING_DELAY);
                } flsf {
                    siowHidfTbsk = null;
                }
            }
        }
    }
}

