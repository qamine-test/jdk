/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.LinkfdList;
import jbvb.util.Itfrbtor;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.EmbfddfdFrbmf;
import sun.bwt.SunToolkit;

import stbtid sun.bwt.X11.XConstbnts.*;

publid dlbss XEmbfddfdFrbmfPffr fxtfnds XFrbmfPffr {

    privbtf stbtid finbl PlbtformLoggfr xfmbfdLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.XEmbfddfdFrbmfPffr");

    LinkfdList<AWTKfyStrokf> strokfs;

    XEmbfdClifntHflpfr fmbfddfr; // Cbution - dbn bf null if XEmbfd is not supportfd
    publid XEmbfddfdFrbmfPffr(EmbfddfdFrbmf tbrgft) {
        // Don't spfdify PARENT_WINDOW pbrbm ifrf. Instfbd wf rfpbrfnt
        // tiis fmbfddfd frbmf pffr to tif propfr pbrfnt window bftfr
        // bn XEvfntDispbtdifr is rfgistfrfd to ibndlf XEmbfd fvfnts
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            TARGET, tbrgft,
            VISIBLE, Boolfbn.TRUE,
            EMBEDDED, Boolfbn.TRUE}));
    }

    publid void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        strokfs = nfw LinkfdList<AWTKfyStrokf>();
        if (supportsXEmbfd()) {
            fmbfddfr = nfw XEmbfdClifntHflpfr();
        }
    }
    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        if (fmbfddfr != null) {
            // instbll X11 fvfnt dispbtdifr
            fmbfddfr.sftClifnt(tiis);
            // rfpbrfnt to XEmbfd sfrvfr
            fmbfddfr.instbll();
        } flsf if (gftPbrfntWindowHbndlf() != 0) {
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XRfpbrfntWindow(XToolkit.gftDisplby(),
                                            gftWindow(),
                                            gftPbrfntWindowHbndlf(),
                                            0, 0);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
    }

    @Ovfrridf
    publid void disposf() {
        if (fmbfddfr != null) {
            // uninstbll X11 fvfnt dispbtdifr
            fmbfddfr.sftClifnt(null);
        }
        supfr.disposf();
    }

    publid void updbtfMinimumSizf() {
    }

    protfdtfd String gftWMNbmf() {
        rfturn "JbvbEmbfddfdFrbmf";
    }

    finbl long gftPbrfntWindowHbndlf() {
        rfturn ((XEmbfddfdFrbmf)tbrgft).ibndlf;
    }

    boolfbn supportsXEmbfd() {
        rfturn ((EmbfddfdFrbmf)tbrgft).supportsXEmbfd();
    }

    publid boolfbn rfqufstWindowFodus(long timf, boolfbn timfProvidfd) {
        // Siould difdk for bdtivf stbtf of iost bpplidbtion
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            xfmbfdLog.finf("Rfqufsting fodus from fmbfdding iost");
            rfturn fmbfddfr.rfqufstFodus();
        } flsf {
            xfmbfdLog.finf("Rfqufsting fodus from X");
            rfturn supfr.rfqufstWindowFodus(timf, timfProvidfd);
        }
    }

    protfdtfd void rfqufstInitiblFodus() {
        if (fmbfddfr != null && supportsXEmbfd()) {
            fmbfddfr.rfqufstFodus();
        } flsf {
            supfr.rfqufstInitiblFodus();
        }
    }

    protfdtfd boolfbn isEvfntDisbblfd(XEvfnt f) {
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            switdi (f.gft_typf()) {
              dbsf XConstbnts.FodusIn:
              dbsf XConstbnts.FodusOut:
                  rfturn truf;
            }
        }
        rfturn supfr.isEvfntDisbblfd(f);
    }

    publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv)
    {
        bssfrt (SunToolkit.isAWTLodkHfldByCurrfntTirfbd());
        XConfigurfEvfnt xf = xfv.gft_xdonfigurf();
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf(xf.toString());
        }

        // fix for 5063031
        // if wf usf supfr.ibndlfConfigurfNotifyEvfnt() wf would gft wrong
        // sizf bnd position bfdbusf fmbfddfd frbmf rfblly is NOT b dfdorbtfd onf
        difdkIfOnNfwSdrffn(toGlobbl(nfw Rfdtbnglf(xf.gft_x(),
                xf.gft_y(),
                xf.gft_widti(),
                xf.gft_ifigit())));

        Rfdtbnglf oldBounds = gftBounds();

        syndironizfd (gftStbtfLodk()) {
            x = xf.gft_x();
            y = xf.gft_y();
            widti = xf.gft_widti();
            ifigit = xf.gft_ifigit();

            dimfnsions.sftClifntSizf(widti, ifigit);
            dimfnsions.sftLodbtion(x, y);
        }

        if (!gftLodbtion().fqubls(oldBounds.gftLodbtion())) {
            ibndlfMovfd(dimfnsions);
        }
        rfdonfigurfContfntWindow(dimfnsions);
    }

    protfdtfd void trbvfrsfOutForwbrd() {
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            if (fmbfddfr.isApplidbtionAdtivf()) {
                xfmbfdLog.finf("Trbvfrsing out Forwbrd");
                fmbfddfr.trbvfrsfOutForwbrd();
            }
        }
    }

    protfdtfd void trbvfrsfOutBbdkwbrd() {
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            if (fmbfddfr.isApplidbtionAdtivf()) {
                xfmbfdLog.finf("Trbvfrsing out Bbdkwbrd");
                fmbfddfr.trbvfrsfOutBbdkwbrd();
            }
        }
    }

    // don't usf gftLodbtionOnSdrffn() inifritfd from XDfdorbtfdPffr
    publid Point gftLodbtionOnSdrffn() {
        XToolkit.bwtLodk();
        try {
            rfturn toGlobbl(0, 0);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    // don't usf gftBounds() inifritfd from XDfdorbtfdPffr
    publid Rfdtbnglf gftBounds() {
        rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
    }

    publid void sftBoundsPrivbtf(int x, int y, int widti, int ifigit) {
        sftBounds(x, y, widti, ifigit, SET_BOUNDS | NO_EMBEDDED_CHECK);
    }

    publid Rfdtbnglf gftBoundsPrivbtf() {
        int x = 0, y = 0;
        int w = 0, i = 0;
        XWindowAttributfs bttr = nfw XWindowAttributfs();

        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(),
                gftWindow(), bttr.pDbtb);
            x = bttr.gft_x();
            y = bttr.gft_y();
            w = bttr.gft_widti();
            i = bttr.gft_ifigit();
        } finblly {
            XToolkit.bwtUnlodk();
        }
        bttr.disposf();

        rfturn nfw Rfdtbnglf(x, y, w, i);
    }
    void rfgistfrAddflfrbtor(AWTKfyStrokf strokf) {
        if (strokf == null) rfturn;
        strokfs.bdd(strokf);
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            fmbfddfr.rfgistfrAddflfrbtor(strokf, strokfs.sizf()-1);
        }
    }

    void unrfgistfrAddflfrbtor(AWTKfyStrokf strokf) {
        if (strokf == null) rfturn;
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            int indfx = strokfs.indfxOf(strokf);
            fmbfddfr.unrfgistfrAddflfrbtor(indfx);
        }
    }

    void notifyStbrtfd() {
        // Rfgistfr bddflfrbtors
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            int i = 0;
            Itfrbtor<AWTKfyStrokf> itfr = strokfs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                fmbfddfr.rfgistfrAddflfrbtor(itfr.nfxt(), i++);
            }
        }
        // Now wf know tibt tif tif fmbfddfr is bn XEmbfd sfrvfr, so wf
        // rfrfgistfr tif drop tbrgft to fnbblf XDnD protodol support vib
        // XEmbfd.
        updbtfDropTbrgft();
    }
    void notifyStoppfd() {
        if (fmbfddfr != null && fmbfddfr.isAdtivf()) {
            for (int i = strokfs.sizf() - 1; i >= 0; i--) {
                fmbfddfr.unrfgistfrAddflfrbtor(i);
            }
        }
    }

    long gftFodusTbrgftWindow() {
        rfturn gftWindow();
    }

    boolfbn isXEmbfdAdtivf() {
        rfturn fmbfddfr != null && fmbfddfr.isAdtivf();
    }

    publid int gftAbsolutfX()
    {
        Point bbsolutfLod = XlibUtil.trbnslbtfCoordinbtfs(gftWindow(),
                                                          XToolkit.gftDffbultRootWindow(),
                                                          nfw Point(0, 0));
        rfturn bbsolutfLod != null ? bbsolutfLod.x : 0;
    }

    publid int gftAbsolutfY()
    {
        Point bbsolutfLod = XlibUtil.trbnslbtfCoordinbtfs(gftWindow(),
                                                          XToolkit.gftDffbultRootWindow(),
                                                          nfw Point(0, 0));
        rfturn bbsolutfLod != null ? bbsolutfLod.y : 0;
    }

    publid int gftWidti() {
        rfturn widti;
    }
    publid int gftHfigit() {
        rfturn ifigit;
    }

    publid Dimfnsion gftSizf() {
        rfturn nfw Dimfnsion(widti, ifigit);
    }

    // ovfrridf XWindowPffr's mftiod to lft tif fmbfddfd frbmf to blodk
    // tif dontbining window
    publid void sftModblBlodkfd(Diblog blodkfr, boolfbn blodkfd) {
        supfr.sftModblBlodkfd(blodkfr, blodkfd);

        EmbfddfdFrbmf frbmf = (EmbfddfdFrbmf)tbrgft;
        frbmf.notifyModblBlodkfd(blodkfr, blodkfd);
    }

    publid void syntifsizfFodusInOut(boolfbn doFodus) {
        XFodusCibngfEvfnt xfv = nfw XFodusCibngfEvfnt();

        XToolkit.bwtLodk();
        try {
            xfv.sft_typf(doFodus ? FodusIn : FodusOut);
            xfv.sft_window(gftFodusProxy().gftWindow());
            xfv.sft_modf(NotifyNormbl);
            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), gftFodusProxy().gftWindow(), fblsf,
                                   NoEvfntMbsk, xfv.pDbtb);
        } finblly {
            XToolkit.bwtUnlodk();
            xfv.disposf();
        }
    }
}
