/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.gfom.Point2D;
import jbvb.lbng.rff.WfbkRfffrfndf;

import sun.bwt.IdonInfo;
import sun.bwt.AWTAddfssor;
import sun.bwt.SunToolkit;

dlbss XWbrningWindow fxtfnds XWindow {
    privbtf finbl stbtid int SHOWING_DELAY = 330;
    privbtf finbl stbtid int HIDING_DELAY = 2000;

    privbtf finbl Window ownfrWindow;
    privbtf WfbkRfffrfndf<XWindowPffr> ownfrPffr;
    privbtf long pbrfntWindow;

    privbtf finbl stbtid String OWNER = "OWNER";
    privbtf InfoWindow.Tooltip tooltip;

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
        syndironizfd (XWbrningWindow.dlbss) {
            if (idons == null) {
                idons = nfw IdonInfo[4][3];
                if (XlibWrbppfr.dbtbModfl == 32) {
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
                } flsf {
                    idons[0][0] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_bw16_png.sfdurity_idon_bw16_png);
                    idons[0][1] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_intfrim16_png.sfdurity_idon_intfrim16_png);
                    idons[0][2] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_yfllow16_png.sfdurity_idon_yfllow16_png);
                    idons[1][0] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_bw24_png.sfdurity_idon_bw24_png);
                    idons[1][1] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_intfrim24_png.sfdurity_idon_intfrim24_png);
                    idons[1][2] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_yfllow24_png.sfdurity_idon_yfllow24_png);
                    idons[2][0] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_bw32_png.sfdurity_idon_bw32_png);
                    idons[2][1] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_intfrim32_png.sfdurity_idon_intfrim32_png);
                    idons[2][2] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_yfllow32_png.sfdurity_idon_yfllow32_png);
                    idons[3][0] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_bw48_png.sfdurity_idon_bw48_png);
                    idons[3][1] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_intfrim48_png.sfdurity_idon_intfrim48_png);
                    idons[3][2] = nfw IdonInfo(sun.bwt.AWTIdon64_sfdurity_idon_yfllow48_png.sfdurity_idon_yfllow48_png);
                }
            }
        }
        finbl int sizfIndfx = sizf % idons.lfngti;
        rfturn idons[sizfIndfx][num % idons[sizfIndfx].lfngti];
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

        // Notf: tiis is not tif most wisf solution to usf bwtLodk ifrf,
        // tiis siould ibvf bffn synd'fd witi tif stbtfLodk. Howfvfr,
        // tif bwtLodk must bf tbkfn first (sff XBbsfWindow.gftStbtfLodk()),
        // bnd wf nffd tif bwtLodk bnywby to updbtf tif sibpf of tif idon.
        // So it's fbsifr to usf just onf lodk instfbd.
        XToolkit.bwtLodk();
        try {
            if (nfwSizf != durrfntSizf) {
                durrfntSizf = nfwSizf;
                IdonInfo ido = gftSfdurityIdonInfo(durrfntSizf, 0);
                XlibWrbppfr.SftBitmbpSibpf(XToolkit.gftDisplby(), gftWindow(),
                        ido.gftWidti(), ido.gftHfigit(), ido.gftIntDbtb());
                AWTAddfssor.gftWindowAddfssor().sftSfdurityWbrningSizf(
                        ownfrWindow, ido.gftWidti(), ido.gftHfigit());
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    privbtf IdonInfo gftSfdurityIdonInfo() {
        updbtfIdonSizf();
        rfturn gftSfdurityIdonInfo(durrfntSizf, durrfntIdon);
    }

    XWbrningWindow(finbl Window ownfrWindow, long pbrfntWindow, XWindowPffr ownfrPffr) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
                        TARGET, ownfrWindow,
                        OWNER, Long.vblufOf(pbrfntWindow)
        }));
        tiis.ownfrWindow = ownfrWindow;
        tiis.pbrfntWindow = pbrfntWindow;
        tiis.tooltip = nfw InfoWindow.Tooltip(null, gftTbrgft(),
                nfw InfoWindow.Tooltip.LivfArgumfnts() {
                    publid boolfbn isDisposfd() {
                        rfturn XWbrningWindow.tiis.isDisposfd();
                    }
                    publid Rfdtbnglf gftBounds() {
                        rfturn XWbrningWindow.tiis.gftBounds();
                    }
                    publid String gftTooltipString() {
                        rfturn XWbrningWindow.tiis.ownfrWindow.gftWbrningString();
                    }
                });
        tiis.ownfrPffr = nfw WfbkRfffrfndf<XWindowPffr>(ownfrPffr);
    }

    privbtf void rfqufstNoTbskbbr() {
        XNETProtodol nftProtodol = XWM.gftWM().gftNETProtodol();
        if (nftProtodol != null) {
            nftProtodol.rfqufstStbtf(tiis, nftProtodol.XA_NET_WM_STATE_SKIP_TASKBAR, truf);
        }
    }

    @Ovfrridf
    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        XToolkit.bwtLodk();
        try {
            XWM.sftMotifDfdor(tiis, fblsf, 0, 0);
            XWM.sftOLDfdor(tiis, fblsf, 0);

            long pbrfntWindow = ((Long)pbrbms.gft(OWNER)).longVbluf();
            XlibWrbppfr.XSftTrbnsifntFor(XToolkit.gftDisplby(),
                    gftWindow(), pbrfntWindow);

            XWMHints iints = gftWMHints();
            iints.sft_flbgs(iints.gft_flbgs() | (int)XUtilConstbnts.InputHint | (int)XUtilConstbnts.StbtfHint);
            iints.sft_input(fblsf);
            iints.sft_initibl_stbtf(XUtilConstbnts.NormblStbtf);
            XlibWrbppfr.XSftWMHints(XToolkit.gftDisplby(), gftWindow(), iints.pDbtb);

            initWMProtodols();
            rfqufstNoTbskbbr();
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * @pbrbm x,y,w,i doordinbtfs of tif untrustfd window
     */
    publid void rfposition(int x, int y, int w, int i) {
        Point2D point = AWTAddfssor.gftWindowAddfssor().
            dbldulbtfSfdurityWbrningPosition(ownfrWindow,
                x, y, w, i);
        rfsibpf((int)point.gftX(), (int)point.gftY(), gftWidti(), gftHfigit());
    }

    protfdtfd String gftWMNbmf() {
        rfturn "Wbrning window";
    }

    publid Grbpiids gftGrbpiids() {
        if ((surfbdfDbtb == null) || (ownfrWindow == null)) rfturn null;
        rfturn gftGrbpiids(surfbdfDbtb,
                                 gftColor(),
                                 gftBbdkground(),
                                 gftFont());
    }
    void pbint(Grbpiids g, int x, int y, int widti, int ifigit) {
        g.drbwImbgf(gftSfdurityIdonInfo().gftImbgf(), 0, 0, null);
    }

    String gftWbrningString() {
        rfturn ownfrWindow.gftWbrningString();
    }

    int gftWidti() {
        rfturn gftSfdurityIdonInfo().gftWidti();
    }

    int gftHfigit() {
        rfturn gftSfdurityIdonInfo().gftHfigit();
    }

    Color gftBbdkground() {
        rfturn SystfmColor.window;
    }
    Color gftColor() {
        rfturn Color.blbdk;
    }
    Font gftFont () {
        rfturn ownfrWindow.gftFont();
    }

    @Ovfrridf
    publid void rfpbint() {
        finbl Rfdtbnglf bounds = gftBounds();
        finbl Grbpiids g = gftGrbpiids();
        if (g != null) {
            try {
                pbint(g, 0, 0, bounds.widti, bounds.ifigit);
            } finblly {
                g.disposf();
            }
        }
    }
    @Ovfrridf
    publid void ibndlfExposfEvfnt(XEvfnt xfv) {
        supfr.ibndlfExposfEvfnt(xfv);

        XExposfEvfnt xf = xfv.gft_xfxposf();
        finbl int x = xf.gft_x();
        finbl int y = xf.gft_y();
        finbl int widti = xf.gft_widti();
        finbl int ifigit = xf.gft_ifigit();
        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft,
                nfw Runnbblf() {
                    publid void run() {
                        finbl Grbpiids g = gftGrbpiids();
                        if (g != null) {
                            try {
                                pbint(g, x, y, widti, ifigit);
                            } finblly {
                                g.disposf();
                            }
                        }
                    }
                });
    }

    @Ovfrridf
    protfdtfd boolfbn isEvfntDisbblfd(XEvfnt f) {
        rfturn truf;
    }

    /** Sfnd b syntiftid UnmbpNotify in ordfr to witidrbw tif window.
     */
    privbtf void witidrbw() {
        XEvfnt rfq = nfw XEvfnt();
        try {
            long root;
            XToolkit.bwtLodk();
            try {
                root = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), gftSdrffnNumbfr());
            }
            finblly {
                XToolkit.bwtUnlodk();
            }

            rfq.sft_typf(XConstbnts.UnmbpNotify);

            XUnmbpEvfnt umfv = rfq.gft_xunmbp();

            umfv.sft_fvfnt(root);
            umfv.sft_window(gftWindow());
            umfv.sft_from_donfigurf(fblsf);

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                        root,
                        fblsf,
                        XConstbnts.SubstrudturfRfdirfdtMbsk | XConstbnts.SubstrudturfNotifyMbsk,
                        rfq.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            rfq.disposf();
        }
    }

    @Ovfrridf
    protfdtfd void stbtfCibngfd(long timf, int oldStbtf, int nfwStbtf) {
        if (nfwStbtf == XUtilConstbnts.IdonidStbtf) {
            supfr.xSftVisiblf(fblsf);
            witidrbw();
        }
    }

    @Ovfrridf
    protfdtfd void sftMousfAbovf(boolfbn bbovf) {
        supfr.sftMousfAbovf(bbovf);
        XWindowPffr p = ownfrPffr.gft();
        if (p != null) {
            p.updbtfSfdurityWbrningVisibility();
        }
    }

    @Ovfrridf
    protfdtfd void fntfrNotify(long window) {
        supfr.fntfrNotify(window);
        if (window == gftWindow()) {
            tooltip.fntfr();
        }
    }

    @Ovfrridf
    protfdtfd void lfbvfNotify(long window) {
        supfr.lfbvfNotify(window);
        if (window == gftWindow()) {
            tooltip.fxit();
        }
    }

    @Ovfrridf
    publid void xSftVisiblf(boolfbn visiblf) {
        supfr.xSftVisiblf(visiblf);

        // Tif _NET_WM_STATE_SKIP_TASKBAR got rfsft upon iiding/siowing,
        // so wf rfqufst it fvfry timf wifnfvfr wf dibngf tif visibility.
        rfqufstNoTbskbbr();
    }

    privbtf finbl Runnbblf iidingTbsk = nfw Runnbblf() {
        publid void run() {
            xSftVisiblf(fblsf);
        }
    };

    privbtf finbl Runnbblf siowingTbsk = nfw Runnbblf() {
        publid void run() {
            if (!isVisiblf()) {
                xSftVisiblf(truf);
                updbtfIdonSizf();
                XWindowPffr pffr = ownfrPffr.gft();
                if (pffr != null) {
                    pffr.rfpositionSfdurityWbrning();
                }
            }
            rfpbint();
            if (durrfntIdon > 0) {
                durrfntIdon--;
                XToolkit.sdifdulf(siowingTbsk, SHOWING_DELAY);
            }
        }
    };

    publid void sftSfdurityWbrningVisiblf(boolfbn visiblf, boolfbn doSdifdulf) {
        if (visiblf) {
            XToolkit.rfmovf(iidingTbsk);
            XToolkit.rfmovf(siowingTbsk);
            if (isVisiblf()) {
                durrfntIdon = 0;
            } flsf {
                durrfntIdon = 3;
            }
            if (doSdifdulf) {
                XToolkit.sdifdulf(siowingTbsk, 1);
            } flsf {
                siowingTbsk.run();
            }
        } flsf {
            XToolkit.rfmovf(siowingTbsk);
            XToolkit.rfmovf(iidingTbsk);
            if (!isVisiblf()) {
                rfturn;
            }
            if (doSdifdulf) {
                XToolkit.sdifdulf(iidingTbsk, HIDING_DELAY);
            } flsf {
                iidingTbsk.run();
            }
        }
    }
}
