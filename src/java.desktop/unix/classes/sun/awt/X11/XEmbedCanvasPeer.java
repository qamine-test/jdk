/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.dnd.DropTbrgftListfnfr;
import jbvb.bwt.fvfnt.*;
import sun.bwt.*;
import sun.bwt.AWTAddfssor;
import sun.util.logging.PlbtformLoggfr;
import jbvb.util.*;
import stbtid sun.bwt.X11.XEmbfdHflpfr.*;

import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;

publid dlbss XEmbfdCbnvbsPffr fxtfnds XCbnvbsPffr implfmfnts WindowFodusListfnfr, KfyEvfntPostProdfssor, ModblityListfnfr, WindowIDProvidfr {
    privbtf stbtid finbl PlbtformLoggfr xfmbfdLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.XEmbfdCbnvbsPffr");

    boolfbn bpplidbtionAdtivf; // Wiftifr tif bpplidbtion is bdtivf(ibs fodus)
    XEmbfdSfrvfr xfmbfd = nfw XEmbfdSfrvfr(); // Hflpfr objfdt, dontbins XEmbfd intrinsids
    Mbp<Long, AWTKfyStrokf> bddflfrbtors = nfw HbsiMbp<Long, AWTKfyStrokf>(); // Mbps bddflfrbtor ID into AWTKfyStrokf
    Mbp<AWTKfyStrokf, Long> bddfl_lookup = nfw HbsiMbp<AWTKfyStrokf, Long>(); // Mbps AWTKfyStrokf into bddflfrbtor ID
    Sft<GrbbbfdKfy> grbbbfd_kfys = nfw HbsiSft<GrbbbfdKfy>(); // A sft of kfys grbbbfd by dlifnt
    Objfdt ACCEL_LOCK = bddflfrbtors; // Lodk objfdt for working witi bddflfrbtors;
    Objfdt GRAB_LOCK = grbbbfd_kfys; // Lodk objfdt for working witi kfys grbbbfd by dlifnt

    XEmbfdCbnvbsPffr() {}

    XEmbfdCbnvbsPffr(XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms);
    }

    XEmbfdCbnvbsPffr(Componfnt tbrgft) {
        supfr(tbrgft);
    }

    protfdtfd void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);

        instbllAdtivbtfListfnfr();
        instbllAddflfrbtorListfnfr();
        instbllModblityListfnfr();

        // XEmbfd dbnvbs siould bf non-trbvfrsbblf.
        // FIXME: Probbbly siould bf rfmovfd bnd fnfordfd sftting of it by tif usfrs
        tbrgft.sftFodusTrbvfrsblKfysEnbblfd(fblsf);
    }

    protfdtfd void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);

        pbrbms.put(EVENT_MASK,
                   XConstbnts.KfyPrfssMbsk       | XConstbnts.KfyRflfbsfMbsk
                   | XConstbnts.FodusCibngfMbsk  | XConstbnts.ButtonPrfssMbsk | XConstbnts.ButtonRflfbsfMbsk
                   | XConstbnts.EntfrWindowMbsk  | XConstbnts.LfbvfWindowMbsk | XConstbnts.PointfrMotionMbsk
                   | XConstbnts.ButtonMotionMbsk | XConstbnts.ExposurfMbsk    | XConstbnts.StrudturfNotifyMbsk | XConstbnts.SubstrudturfNotifyMbsk);

    }

    void instbllModblityListfnfr() {
        ((SunToolkit)Toolkit.gftDffbultToolkit()).bddModblityListfnfr(tiis);
    }

    void dfinstbllModblityListfnfr() {
        ((SunToolkit)Toolkit.gftDffbultToolkit()).rfmovfModblityListfnfr(tiis);
    }

    void instbllAddflfrbtorListfnfr() {
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().bddKfyEvfntPostProdfssor(tiis);
    }

    void dfinstbllAddflfrbtorListfnfr() {
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().rfmovfKfyEvfntPostProdfssor(tiis);
    }

    void instbllAdtivbtfListfnfr() {
        // FIXME: siould wbtdi for iifrbrdiy dibngfs
        Window toplfvfl = gftTopLfvfl(tbrgft);
        if (toplfvfl != null) {
            toplfvfl.bddWindowFodusListfnfr(tiis);
            bpplidbtionAdtivf = toplfvfl.isFodusfd();
        }
    }

    void dfinstbllAdtivbtfListfnfr() {
        Window toplfvfl = gftTopLfvfl(tbrgft);
        if (toplfvfl != null) {
            toplfvfl.rfmovfWindowFodusListfnfr(tiis);
        }
    }

    boolfbn isXEmbfdAdtivf() {
        rfturn xfmbfd.ibndlf != 0;
    }

    boolfbn isApplidbtionAdtivf() {
        rfturn bpplidbtionAdtivf;
    }

    void initDispbtdiing() {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf("Init fmbfdding for " + Long.toHfxString(xfmbfd.ibndlf));
        }
        XToolkit.bwtLodk();
        try {
            XToolkit.bddEvfntDispbtdifr(xfmbfd.ibndlf, xfmbfd);
            XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), xfmbfd.ibndlf,
                                     XConstbnts.StrudturfNotifyMbsk | XConstbnts.PropfrtyCibngfMbsk);

            XDropTbrgftRfgistry.gftRfgistry().rfgistfrXEmbfdClifnt(gftWindow(), xfmbfd.ibndlf);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        xfmbfd.prodfssXEmbfdInfo();

        notifyCiildEmbfddfd();
    }

    void fndDispbtdiing() {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf("End dispbtdiing for " + Long.toHfxString(xfmbfd.ibndlf));
        }
        XToolkit.bwtLodk();
        try {
            XDropTbrgftRfgistry.gftRfgistry().unrfgistfrXEmbfdClifnt(gftWindow(), xfmbfd.ibndlf);
            // Wf dbn't dfsflfdt input sindf somfonf flsf migit bf intfrfstfd in it
            XToolkit.rfmovfEvfntDispbtdifr(xfmbfd.ibndlf, xfmbfd);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void fmbfdCiild(long diild) {
        if (xfmbfd.ibndlf != 0) {
            dftbdiCiild();
        }
        xfmbfd.ibndlf = diild;
        initDispbtdiing();
    }

    void diildDfstroyfd() {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf("Ciild " + Long.toHfxString(xfmbfd.ibndlf) + " ibs sflf-dfstroyfd.");
        }
        fndDispbtdiing();
        xfmbfd.ibndlf = 0;
    }

    publid void ibndlfEvfnt(AWTEvfnt f) {
        supfr.ibndlfEvfnt(f);
        if (isXEmbfdAdtivf()) {
            switdi (f.gftID()) {
              dbsf FodusEvfnt.FOCUS_GAINED:
                  dbnvbsFodusGbinfd((FodusEvfnt)f);
                  brfbk;
              dbsf FodusEvfnt.FOCUS_LOST:
                  dbnvbsFodusLost((FodusEvfnt)f);
                  brfbk;
              dbsf KfyEvfnt.KEY_PRESSED:
              dbsf KfyEvfnt.KEY_RELEASED:
                  if (!((InputEvfnt)f).isConsumfd()) {
                      forwbrdKfyEvfnt((KfyEvfnt)f);
                  }
                  brfbk;
            }
        }
    }

    publid void dispbtdiEvfnt(XEvfnt fv) {
        supfr.dispbtdiEvfnt(fv);
        switdi (fv.gft_typf()) {
          dbsf XConstbnts.CrfbtfNotify:
              XCrfbtfWindowEvfnt dr = fv.gft_xdrfbtfwindow();
              if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                  xfmbfdLog.finfst("Mfssbgf on fmbfddfr: " + dr);
              }
              if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  xfmbfdLog.finfr("Crfbtf notify for pbrfnt " + Long.toHfxString(dr.gft_pbrfnt()) +
                                  ", window " + Long.toHfxString(dr.gft_window()));
              }
              fmbfdCiild(dr.gft_window());
              brfbk;
          dbsf XConstbnts.DfstroyNotify:
              XDfstroyWindowEvfnt dn = fv.gft_xdfstroywindow();
              if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                  xfmbfdLog.finfst("Mfssbgf on fmbfddfr: " + dn);
              }
              if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  xfmbfdLog.finfr("Dfstroy notify for pbrfnt: " + dn);
              }
              diildDfstroyfd();
              brfbk;
          dbsf XConstbnts.RfpbrfntNotify:
              XRfpbrfntEvfnt rfp = fv.gft_xrfpbrfnt();
              if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                  xfmbfdLog.finfst("Mfssbgf on fmbfddfr: " + rfp);
              }
              if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  xfmbfdLog.finfr("Rfpbrfnt notify for pbrfnt " + Long.toHfxString(rfp.gft_pbrfnt()) +
                                  ", window " + Long.toHfxString(rfp.gft_window()) +
                                  ", fvfnt " + Long.toHfxString(rfp.gft_fvfnt()));
              }
              if (rfp.gft_pbrfnt() == gftWindow()) {
                  // Rfpbrfntfd into us - fmbfd it
                  fmbfdCiild(rfp.gft_window());
              } flsf {
                  // Rfpbrfntfd out of us - dftbdi it
                  diildDfstroyfd();
              }
              brfbk;
        }
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        if (isXEmbfdAdtivf()) {
            XToolkit.bwtLodk();
            try {
                long p_iints = XlibWrbppfr.XAllodSizfHints();
                XSizfHints iints = nfw XSizfHints(p_iints);
                XlibWrbppfr.XGftWMNormblHints(XToolkit.gftDisplby(), xfmbfd.ibndlf, p_iints, XlibWrbppfr.lbrg1);
                Dimfnsion rfs = nfw Dimfnsion(iints.gft_widti(), iints.gft_ifigit());
                XlibWrbppfr.XFrff(p_iints);
                rfturn rfs;
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } flsf {
            rfturn supfr.gftPrfffrrfdSizf();
        }
    }
    publid Dimfnsion gftMinimumSizf() {
        if (isXEmbfdAdtivf()) {
            XToolkit.bwtLodk();
            try {
                long p_iints = XlibWrbppfr.XAllodSizfHints();
                XSizfHints iints = nfw XSizfHints(p_iints);
                XlibWrbppfr.XGftWMNormblHints(XToolkit.gftDisplby(), xfmbfd.ibndlf, p_iints, XlibWrbppfr.lbrg1);
                Dimfnsion rfs = nfw Dimfnsion(iints.gft_min_widti(), iints.gft_min_ifigit());
                XlibWrbppfr.XFrff(p_iints);
                rfturn rfs;
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } flsf {
            rfturn supfr.gftMinimumSizf();
        }
    }
    publid void disposf() {
        if (isXEmbfdAdtivf()) {
            dftbdiCiild();
        }
        dfinstbllAdtivbtfListfnfr();
        dfinstbllModblityListfnfr();
        dfinstbllAddflfrbtorListfnfr();

        // BUG: Fodus trbvfrsbl dofsn't bfdomf fnbblfd bftfr tif onf round of fmbfdding
        //tbrgft.sftFodusTrbvfrsblKfysEnbblfd(truf);

        supfr.disposf();
    }

    // Fodusbblf is truf in ordfr to fnbblf fodus trbvfrsbl tirougi tiis Cbnvbs
    publid boolfbn isFodusbblf() {
        rfturn truf;
    }

    Window gftTopLfvfl(Componfnt domp) {
        wiilf (domp != null && !(domp instbndfof Window)) {
            domp = domp.gftPbrfnt();
        }
        rfturn (Window)domp;
    }

    Rfdtbnglf gftClifntBounds() {
        XToolkit.bwtLodk();
        try {
            XWindowAttributfs wbttr = nfw XWindowAttributfs();
            try {
                XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
                int stbtus = XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(),
                                                              xfmbfd.ibndlf, wbttr.pDbtb);

                XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

                if ((stbtus == 0) ||
                    ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                    (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss))) {
                    rfturn null;
                }

                rfturn nfw Rfdtbnglf(wbttr.gft_x(), wbttr.gft_y(), wbttr.gft_widti(), wbttr.gft_ifigit());
            } finblly {
                wbttr.disposf();
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void diildRfsizfd() {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            Rfdtbnglf bounds = gftClifntBounds();
            xfmbfdLog.finfr("Ciild rfsizfd: " + bounds);
            // It is not rfquirfd to updbtf fmbfddfr's sizf wifn dlifnt sizf dibngfs
            // Howfvfr, sindf tifrf is no bny mfbns to gft dlifnt sizf it sffms to bf tif
            // only wby to providf it. Howfvfr, it dontrbdidts witi Jbvb lbyout dondfpt -
            // so it is disbblfd for now.
//             Rfdtbnglf my_bounds = gftBounds();
//             sftBounds(my_bounds.x, my_bounds.y, bounds.widti, bounds.ifigit, SET_BOUNDS);
        }
        XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(tbrgft), nfw ComponfntEvfnt(tbrgft, ComponfntEvfnt.COMPONENT_RESIZED));
    }

    void fodusNfxt() {
        if (isXEmbfdAdtivf()) {
            xfmbfdLog.finf("Rfqufsting fodus for tif nfxt domponfnt bftfr fmbfddfr");
            postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().fodusNfxtComponfnt(tbrgft);
                    }
                }));
        } flsf {
            xfmbfdLog.finf("XEmbfd is not bdtivf - dfnying fodus nfxt");
        }
    }

    void fodusPrfv() {
        if (isXEmbfdAdtivf()) {
            xfmbfdLog.finf("Rfqufsting fodus for tif nfxt domponfnt bftfr fmbfddfr");
            postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().fodusPrfviousComponfnt(tbrgft);
                    }
                }));
        } flsf {
            xfmbfdLog.finf("XEmbfd is not bdtivf - dfnying fodus prfv");
        }
    }

    void rfqufstXEmbfdFodus() {
        if (isXEmbfdAdtivf()) {
            xfmbfdLog.finf("Rfqufsting fodus for dlifnt");
            postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        tbrgft.rfqufstFodus();
                    }
                }));
        } flsf {
            xfmbfdLog.finf("XEmbfd is not bdtivf - dfnying rfqufst fodus");
        }
    }

    void notifyCiildEmbfddfd() {
        xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_EMBEDDED_NOTIFY, gftWindow(), Mbti.min(xfmbfd.vfrsion, XEMBED_VERSION), 0);
        if (isApplidbtionAdtivf()) {
            xfmbfdLog.finf("Sfnding WINDOW_ACTIVATE during initiblizbtion");
            xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_WINDOW_ACTIVATE);
            if (ibsFodus()) {
                xfmbfdLog.finf("Sfnding FOCUS_GAINED during initiblizbtion");
                xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_FOCUS_IN, XEMBED_FOCUS_CURRENT, 0, 0);
            }
        }
    }

    void dftbdiCiild() {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf("Dftbdiing diild " + Long.toHfxString(xfmbfd.ibndlf));
        }
        /**
         *  XEmbfd spfdifidbtion:
         *  "Tif fmbfddfr dbn unmbp tif dlifnt bnd rfpbrfnt tif dlifnt window to tif root window. If tif
         *  dlifnt rfdfivfs bn RfpbrfntNotify fvfnt, it siould difdk tif pbrfnt fifld of tif XRfpbrfntEvfnt
         *  strudturf. If tiis is tif root window of tif window's sdrffn, tifn tif protodol is finisifd bnd
         *  tifrf is no furtifr intfrbdtion. If it is b window otifr tibn tif root window, tifn tif protodol
         *  dontinufs witi tif nfw pbrfnt bdting bs tif fmbfddfr window."
         */
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XUnmbpWindow(XToolkit.gftDisplby(), xfmbfd.ibndlf);
            XlibWrbppfr.XRfpbrfntWindow(XToolkit.gftDisplby(), xfmbfd.ibndlf, XToolkit.gftDffbultRootWindow(), 0, 0);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        fndDispbtdiing();
        xfmbfd.ibndlf = 0;
    }

    publid void windowGbinfdFodus(WindowEvfnt f) {
        bpplidbtionAdtivf = truf;
        if (isXEmbfdAdtivf()) {
            xfmbfdLog.finf("Sfnding WINDOW_ACTIVATE");
            xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_WINDOW_ACTIVATE);
        }
    }

    publid void windowLostFodus(WindowEvfnt f) {
        bpplidbtionAdtivf = fblsf;
        if (isXEmbfdAdtivf()) {
            xfmbfdLog.finf("Sfnding WINDOW_DEACTIVATE");
            xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_WINDOW_DEACTIVATE);
        }
    }

    void dbnvbsFodusGbinfd(FodusEvfnt f) {
        if (isXEmbfdAdtivf()) {
            xfmbfdLog.finf("Forwbrding FOCUS_GAINED");
            int flbvor = XEMBED_FOCUS_CURRENT;
            if (f instbndfof CbusfdFodusEvfnt) {
                CbusfdFodusEvfnt df = (CbusfdFodusEvfnt)f;
                if (df.gftCbusf() == CbusfdFodusEvfnt.Cbusf.TRAVERSAL_FORWARD) {
                    flbvor = XEMBED_FOCUS_FIRST;
                } flsf if (df.gftCbusf() == CbusfdFodusEvfnt.Cbusf.TRAVERSAL_BACKWARD) {
                    flbvor = XEMBED_FOCUS_LAST;
                }
            }
            xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_FOCUS_IN, flbvor, 0, 0);
        }
    }

    void dbnvbsFodusLost(FodusEvfnt f) {
        if (isXEmbfdAdtivf() && !f.isTfmporbry()) {
            xfmbfdLog.finf("Forwbrding FOCUS_LOST");
            int num = 0;
            if (AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("sun.bwt.xfmbfd.tfsting"))) {
                Componfnt opp = f.gftOppositfComponfnt();
                try {
                    num = Intfgfr.pbrsfInt(opp.gftNbmf());
                } dbtdi (NumbfrFormbtExdfption nff) {
                }
            }
            xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_FOCUS_OUT, num, 0, 0);
        }
    }

    stbtid bytf[] gftBDbtb(KfyEvfnt f) {
        rfturn AWTAddfssor.gftAWTEvfntAddfssor().gftBDbtb(f);
    }

    void forwbrdKfyEvfnt(KfyEvfnt f) {
        xfmbfdLog.finf("Try to forwbrd kfy fvfnt");
        bytf[] bdbtb = gftBDbtb(f);
        long dbtb = Nbtivf.toDbtb(bdbtb);
        if (dbtb == 0) {
            rfturn;
        }
        try {
            XKfyEvfnt kf = nfw XKfyEvfnt(dbtb);
            kf.sft_window(xfmbfd.ibndlf);
            if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                xfmbfdLog.finf("Forwbrding nbtivf kfy fvfnt: " + kf);
            }
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), xfmbfd.ibndlf, fblsf, XConstbnts.NoEvfntMbsk, dbtb);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            XlibWrbppfr.unsbff.frffMfmory(dbtb);
        }
    }


    /**
     * Grbb/ungrbb kfy fundtionblity is bn unoffidibl API supportfd by
     * GTK.  Unfortunbtfly, it dofsn't support bddflfrbtor API, so,
     * sindf tiis is tif ONLY siortdut-prodfssing API bvbilbblf, wf
     * must support it.  Sff XEmbfd.NON_STANDARD_XEMBED_GTK_*
     * mfssbgfs.  Tif formbt of tifsf mfssbgfs is bs follows:
     * - rfqufst from dlifnt:
     * dbtb[1] = NON_STANDARD_XEMBED_GTK_GRAB_KEY or NON_STANDARD_XEMBED_GTK_UNGRAB_KEY
     * dbtb[3] = X kfysym
     * dbtb[4] = X modififrs
     *
     * - rfsponsf from sfrvfr (in dbsf tif grbbbfd kfy ibs bffn prfssfd):
     * forwbrdfd XKfyEvfnt tibt mbtdifs kfysym/modififrs pbir
     */
    void grbbKfy(finbl long kfysym, finbl long modififrs) {
        postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                publid void run() {
                    GrbbbfdKfy grbb = nfw GrbbbfdKfy(kfysym, modififrs);
                    if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        xfmbfdLog.finf("Grbbbing kfy: " + grbb);
                    }
                    syndironizfd(GRAB_LOCK) {
                        grbbbfd_kfys.bdd(grbb);
                    }
                }
            }));
    }

    void ungrbbKfy(finbl long kfysym, finbl long modififrs) {
        postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                publid void run() {
                    GrbbbfdKfy grbb = nfw GrbbbfdKfy(kfysym, modififrs);
                    if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        xfmbfdLog.finf("UnGrbbbing kfy: " + grbb);
                    }
                    syndironizfd(GRAB_LOCK) {
                        grbbbfd_kfys.rfmovf(grbb);
                    }
                }
            }));
    }

    void rfgistfrAddflfrbtor(finbl long bddfl_id, finbl long kfysym, finbl long modififrs) {
        postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                publid void run() {
                    AWTKfyStrokf strokf = xfmbfd.gftKfyStrokfForKfySym(kfysym, modififrs);
                    if (strokf != null) {
                        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            xfmbfdLog.finf("Rfgistfring bddflfrbtor " + bddfl_id + " for " + strokf);
                        }
                        syndironizfd(ACCEL_LOCK) {
                            bddflfrbtors.put(bddfl_id, strokf);
                            bddfl_lookup.put(strokf, bddfl_id);
                        }
                    }
                    propogbtfRfgistfrAddflfrbtor(strokf);
                }
            }));
    }

    void unrfgistfrAddflfrbtor(finbl long bddfl_id) {
        postEvfnt(nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
                publid void run() {
                    AWTKfyStrokf strokf = null;
                    syndironizfd(ACCEL_LOCK) {
                        strokf = bddflfrbtors.gft(bddfl_id);
                        if (strokf != null) {
                            if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                                xfmbfdLog.finf("Unrfgistfring bddflfrbtor: " + bddfl_id);
                            }
                            bddflfrbtors.rfmovf(bddfl_id);
                            bddfl_lookup.rfmovf(strokf); // FIXME: How bbout sfvfrbl bddflfrbtors witi tif sbmf strokf?
                        }
                    }
                    propogbtfUnRfgistfrAddflfrbtor(strokf);
                }
            }));
    }

    void propogbtfRfgistfrAddflfrbtor(AWTKfyStrokf strokf) {
        // Find tif top-lfvfl bnd sff if it is XEmbfd dlifnt. If so, bsk iim to
        // rfgistfr tif bddflfrbtor
        XWindowPffr pbrfnt = gftToplfvflXWindow();
        if (pbrfnt != null && pbrfnt instbndfof XEmbfddfdFrbmfPffr) {
            XEmbfddfdFrbmfPffr fmbfddfd = (XEmbfddfdFrbmfPffr)pbrfnt;
            fmbfddfd.rfgistfrAddflfrbtor(strokf);
        }
    }

    void propogbtfUnRfgistfrAddflfrbtor(AWTKfyStrokf strokf) {
        // Find tif top-lfvfl bnd sff if it is XEmbfd dlifnt. If so, bsk iim to
        // rfgistfr tif bddflfrbtor
        XWindowPffr pbrfnt = gftToplfvflXWindow();
        if (pbrfnt != null && pbrfnt instbndfof XEmbfddfdFrbmfPffr) {
            XEmbfddfdFrbmfPffr fmbfddfd = (XEmbfddfdFrbmfPffr)pbrfnt;
            fmbfddfd.unrfgistfrAddflfrbtor(strokf);
        }
    }

    publid boolfbn postProdfssKfyEvfnt(KfyEvfnt f) {
        // Prodfssing fvfnts only if wf brf in tif fodusfd window but
        // wf brf not fodus ownfr sindf otifrwisf wf will gft
        // duplidbtf siortdut fvfnts in tif dlifnt - onf is from
        // bdtivbtf_bddflfrbtor, bnotifr from forwbrdfd fvfnt
        // FIXME: Tiis is probbbly bn indompbtibility, protodol
        // dofsn't sby bnytiing bbout disbblf bddflfrbtors wifn dlifnt
        // is fodusfd.

        XWindowPffr pbrfnt = gftToplfvflXWindow();
        if (pbrfnt == null || !((Window)pbrfnt.gftTbrgft()).isFodusfd() || tbrgft.isFodusOwnfr()) {
            rfturn fblsf;
        }

        boolfbn rfsult = fblsf;

        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            xfmbfdLog.finfr("Post-prodfssing fvfnt " + f);
        }

        // Prodfss ACCELERATORS
        AWTKfyStrokf strokf = AWTKfyStrokf.gftAWTKfyStrokfForEvfnt(f);
        long bddfl_id = 0;
        boolfbn fxists = fblsf;
        syndironizfd(ACCEL_LOCK) {
            fxists = bddfl_lookup.dontbinsKfy(strokf);
            if (fxists) {
                bddfl_id = bddfl_lookup.gft(strokf).longVbluf();
            }
        }
        if (fxists) {
            if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                xfmbfdLog.finf("Adtivbting bddflfrbtor " + bddfl_id);
            }
            xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_ACTIVATE_ACCELERATOR, bddfl_id, 0, 0); // FIXME: How bbout ovfrlobdfd?
            rfsult = truf;
        }

        // Prodfss Grbbs, unoffidibl GTK ffbturf
        fxists = fblsf;
        GrbbbfdKfy kfy = nfw GrbbbfdKfy(f);
        syndironizfd(GRAB_LOCK) {
            fxists = grbbbfd_kfys.dontbins(kfy);
        }
        if (fxists) {
            if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                xfmbfdLog.finf("Forwbrding grbbbfd kfy " + f);
            }
            forwbrdKfyEvfnt(f);
            rfsult = truf;
        }

        rfturn rfsult;
    }

    publid void modblityPusifd(ModblityEvfnt fv) {
        xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_MODALITY_ON);
    }

    publid void modblityPoppfd(ModblityEvfnt fv) {
        xfmbfd.sfndMfssbgf(xfmbfd.ibndlf, XEMBED_MODALITY_OFF);
    }

    publid void ibndlfClifntMfssbgf(XEvfnt xfv) {
        supfr.ibndlfClifntMfssbgf(xfv);
        XClifntMfssbgfEvfnt msg = xfv.gft_xdlifnt();
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            xfmbfdLog.finfr("Clifnt mfssbgf to fmbfddfr: " + msg);
        }
        if (msg.gft_mfssbgf_typf() == XEmbfdHflpfr.XEmbfd.gftAtom()) {
            if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                xfmbfdLog.finf(XEmbfdHflpfr.XEmbfdMfssbgfToString(msg));
            }
        }
        if (isXEmbfdAdtivf()) {
            switdi ((int)msg.gft_dbtb(1)) {
              dbsf XEMBED_REQUEST_FOCUS:
                  rfqufstXEmbfdFodus();
                  brfbk;
              dbsf XEMBED_FOCUS_NEXT:
                  fodusNfxt();
                  brfbk;
              dbsf XEMBED_FOCUS_PREV:
                  fodusPrfv();
                  brfbk;
              dbsf XEMBED_REGISTER_ACCELERATOR:
                  rfgistfrAddflfrbtor(msg.gft_dbtb(2), msg.gft_dbtb(3), msg.gft_dbtb(4));
                  brfbk;
              dbsf XEMBED_UNREGISTER_ACCELERATOR:
                  unrfgistfrAddflfrbtor(msg.gft_dbtb(2));
                  brfbk;
              dbsf NON_STANDARD_XEMBED_GTK_GRAB_KEY:
                  grbbKfy(msg.gft_dbtb(3), msg.gft_dbtb(4));
                  brfbk;
              dbsf NON_STANDARD_XEMBED_GTK_UNGRAB_KEY:
                  ungrbbKfy(msg.gft_dbtb(3), msg.gft_dbtb(4));
                  brfbk;
            }
        } flsf {
            xfmbfdLog.finfr("But XEmbfd is not Adtivf!");
        }
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf stbtid dlbss XEmbfdDropTbrgft fxtfnds DropTbrgft {
        publid void bddDropTbrgftListfnfr(DropTbrgftListfnfr dtl)
          tirows TooMbnyListfnfrsExdfption {
            // Drop tbrgft listfnfrs rfgistfrfd witi tiis tbrgft will nfvfr bf
            // notififd, sindf bll drbg notifidbtions brf routfd to tif XEmbfd
            // dlifnt. To bvoid donfusion wf proiibit listfnfrs rfgistrbtion
            // by tirowing TooMbnyListfnfrsExdfption bs if tifrf is b listfnfr
            // rfgistfrfd witi tiis tbrgft blrfbdy.
            tirow nfw TooMbnyListfnfrsExdfption();
        }
    }

    publid void sftXEmbfdDropTbrgft() {
        // Rfgistfr b drop sitf on tif top lfvfl.
        Runnbblf r = nfw Runnbblf() {
                publid void run() {
                    tbrgft.sftDropTbrgft(nfw XEmbfdDropTbrgft());
                }
            };
        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, r);
    }

    publid void rfmovfXEmbfdDropTbrgft() {
        // Unrfgistfr b drop sitf on tif top lfvfl.
        Runnbblf r = nfw Runnbblf() {
                publid void run() {
                    if (tbrgft.gftDropTbrgft() instbndfof XEmbfdDropTbrgft) {
                        tbrgft.sftDropTbrgft(null);
                    }
                }
            };
        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, r);
    }

    publid boolfbn prodfssXEmbfdDnDEvfnt(long dtxt, int fvfntID) {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            xfmbfdLog.finfst("     Drop tbrgft=" + tbrgft.gftDropTbrgft());
        }
        if (tbrgft.gftDropTbrgft() instbndfof XEmbfdDropTbrgft) {
            AppContfxt bppContfxt = XToolkit.tbrgftToAppContfxt(gftTbrgft());
            XDropTbrgftContfxtPffr pffr =
                XDropTbrgftContfxtPffr.gftPffr(bppContfxt);
            pffr.forwbrdEvfntToEmbfddfd(xfmbfd.ibndlf, dtxt, fvfntID);
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    dlbss XEmbfdSfrvfr fxtfnds XEmbfdHflpfr implfmfnts XEvfntDispbtdifr {
        long ibndlf; // Hbndlf to XEmbfd dlifnt
        long vfrsion;
        long flbgs;

        boolfbn prodfssXEmbfdInfo() {
            long xfmbfd_info_dbtb = Nbtivf.bllodbtfLongArrby(2);
            try {
                if (!XEmbfdInfo.gftAtomDbtb(ibndlf, xfmbfd_info_dbtb, 2)) {
                    // No morf XEMBED_INFO? Tiis is not XEmbfd dlifnt!
                    // Unfortunbtfly tiis is tif initibl stbtf of tif most dlifnts
                    // FIXME: bdd 5-stbtf prodfssing
                    //diildDfstroyfd();
                    xfmbfdLog.finfr("Unbblf to gft XEMBED_INFO btom dbtb");
                    rfturn fblsf;
                }
                vfrsion = Nbtivf.gftCbrd32(xfmbfd_info_dbtb, 0);
                flbgs = Nbtivf.gftCbrd32(xfmbfd_info_dbtb, 1);
                boolfbn nfw_mbppfd = (flbgs & XEMBED_MAPPED) != 0;
                boolfbn durrfntly_mbppfd = XlibUtil.gftWindowMbpStbtf(ibndlf) != XConstbnts.IsUnmbppfd;
                if (nfw_mbppfd != durrfntly_mbppfd) {
                    if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        xfmbfdLog.finfr("Mbpping stbtf of tif dlifnt ibs dibngfd, old stbtf: " + durrfntly_mbppfd + ", nfw stbtf: " + nfw_mbppfd);
                    }
                    if (nfw_mbppfd) {
                        XToolkit.bwtLodk();
                        try {
                            XlibWrbppfr.XMbpWindow(XToolkit.gftDisplby(), ibndlf);
                        } finblly {
                            XToolkit.bwtUnlodk();
                        }
                    } flsf {
                        XToolkit.bwtLodk();
                        try {
                            XlibWrbppfr.XUnmbpWindow(XToolkit.gftDisplby(), ibndlf);
                        } finblly {
                            XToolkit.bwtUnlodk();
                        }
                    }
                } flsf {
                    if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        xfmbfdLog.finfr("Mbpping stbtf didn't dibngf, mbppfd: " + durrfntly_mbppfd);
                    }
                }
                rfturn truf;
            } finblly {
                XlibWrbppfr.unsbff.frffMfmory(xfmbfd_info_dbtb);
            }
        }

        publid void ibndlfPropfrtyNotify(XEvfnt xfv) {
            if (isXEmbfdAdtivf()) {
                XPropfrtyEvfnt fv = xfv.gft_xpropfrty();
                if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    xfmbfdLog.finfr("Propfrty dibngf on dlifnt: " + fv);
                }
                if (fv.gft_btom() == XAtom.XA_WM_NORMAL_HINTS) {
                    diildRfsizfd();
                } flsf if (fv.gft_btom() == XEmbfdInfo.gftAtom()) {
                    prodfssXEmbfdInfo();
                } flsf if (fv.gft_btom() ==
                           XDnDConstbnts.XA_XdndAwbrf.gftAtom()) {
                    XDropTbrgftRfgistry.gftRfgistry().unrfgistfrXEmbfdClifnt(gftWindow(),
                                                                             xfmbfd.ibndlf);
                    if (fv.gft_stbtf() == XConstbnts.PropfrtyNfwVbluf) {
                        XDropTbrgftRfgistry.gftRfgistry().rfgistfrXEmbfdClifnt(gftWindow(),
                                                                                xfmbfd.ibndlf);
                    }
                }
            } flsf {
                xfmbfdLog.finfr("XEmbfd is not bdtivf");
            }
        }
        void ibndlfConfigurfNotify(XEvfnt xfv) {
            if (isXEmbfdAdtivf()) {
                XConfigurfEvfnt fv = xfv.gft_xdonfigurf();
                if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    xfmbfdLog.finfr("Bounds dibngf on dlifnt: " + fv);
                }
                if (xfv.gft_xbny().gft_window() == ibndlf) {
                    diildRfsizfd();
                }
            }
        }
        publid void dispbtdiEvfnt(XEvfnt xfv) {
            int typf = xfv.gft_typf();
            switdi (typf) {
              dbsf XConstbnts.PropfrtyNotify:
                  ibndlfPropfrtyNotify(xfv);
                  brfbk;
              dbsf XConstbnts.ConfigurfNotify:
                  ibndlfConfigurfNotify(xfv);
                  brfbk;
              dbsf XConstbnts.ClifntMfssbgf:
                  ibndlfClifntMfssbgf(xfv);
                  brfbk;
            }
        }
    }

    stbtid dlbss GrbbbfdKfy {
        long kfysym;
        long modififrs;
        GrbbbfdKfy(long kfysym, long modififrs) {
            tiis.kfysym = kfysym;
            tiis.modififrs = modififrs;
        }

        GrbbbfdKfy(KfyEvfnt fv) {
            init(fv);
        }

        privbtf void init(KfyEvfnt f) {
            bytf[] bdbtb = gftBDbtb(f);
            long dbtb = Nbtivf.toDbtb(bdbtb);
            if (dbtb == 0) {
                rfturn;
            }
            try {
                XToolkit.bwtLodk();
                try {
                    kfysym = XWindow.gftKfySymForAWTKfyCodf(f.gftKfyCodf());
                } finblly {
                    XToolkit.bwtUnlodk();
                }
                XKfyEvfnt kf = nfw XKfyEvfnt(dbtb);

                // Wf rfdognizf only tifsf mbsks
                modififrs = kf.gft_stbtf() & (XConstbnts.SiiftMbsk | XConstbnts.ControlMbsk | XConstbnts.LodkMbsk);
                if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    xfmbfdLog.finfst("Mbppfd " + f + " to " + tiis);
                }
            } finblly {
                XlibWrbppfr.unsbff.frffMfmory(dbtb);
            }
        }

        publid int ibsiCodf() {
            rfturn (int)kfysym & 0xFFFFFFFF;
        }

        publid boolfbn fqubls(Objfdt o) {
            if (!(o instbndfof GrbbbfdKfy)) {
                rfturn fblsf;
            }
            GrbbbfdKfy kfy = (GrbbbfdKfy)o;
            rfturn (kfysym == kfy.kfysym && modififrs == kfy.modififrs);
        }

        publid String toString() {
            rfturn "Kfy dombinbtion[kfysym=" + kfysym + ", mods=" + modififrs + "]";
        }
    }
}
