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

import jbvb.bwt.AWTKfyStrokf;
import sun.bwt.SunToolkit;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import sun.util.logging.PlbtformLoggfr;

import sun.bwt.X11GrbpiidsConfig;
import sun.bwt.X11GrbpiidsDfvidf;

/**
 * Hflpfr dlbss implfmfnting XEmbfd protodol ibndling routinfs(dlifnt sidf)
 * Window wiidi wbnts to pbrtidipbtf in b protodol siould drfbtf bn instbndf,
 * dbll instbll bnd forwbrd bll XClifntMfssbgfEvfnts to it.
 */
publid dlbss XEmbfdClifntHflpfr fxtfnds XEmbfdHflpfr implfmfnts XEvfntDispbtdifr {
    privbtf stbtid finbl PlbtformLoggfr xfmbfdLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.XEmbfdClifntHflpfr");

    privbtf XEmbfddfdFrbmfPffr fmbfddfd; // XEmbfd dlifnt
    privbtf long sfrvfr; // XEmbfd sfrvfr

    privbtf boolfbn bdtivf;
    privbtf boolfbn bpplidbtionAdtivf;

    XEmbfdClifntHflpfr() {
        supfr();
    }

    void sftClifnt(XEmbfddfdFrbmfPffr dlifnt) {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf("XEmbfd dlifnt: " + dlifnt);
        }
        if (fmbfddfd != null) {
            XToolkit.rfmovfEvfntDispbtdifr(fmbfddfd.gftWindow(), tiis);
            bdtivf = fblsf;
        }
        fmbfddfd = dlifnt;
        if (fmbfddfd != null) {
            XToolkit.bddEvfntDispbtdifr(fmbfddfd.gftWindow(), tiis);
        }
    }

    void instbll() {
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf("Instblling xfmbfddfr on " + fmbfddfd);
        }
        long[] info = nfw long[] { XEMBED_VERSION, XEMBED_MAPPED };
        long dbtb = Nbtivf.dbrd32ToDbtb(info);
        try {
            XEmbfdInfo.sftAtomDbtb(fmbfddfd.gftWindow(), dbtb, 2);
        } finblly {
            unsbff.frffMfmory(dbtb);
        }
        // XEmbfddfdFrbmf is initiblly drfbtfd witi b null pbrfnt..
        // Hfrf it is rfpbrfntfd to tif propfr pbrfnt window.
        long pbrfntWindow = fmbfddfd.gftPbrfntWindowHbndlf();
        if (pbrfntWindow != 0) {
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XRfpbrfntWindow(XToolkit.gftDisplby(),
                                            fmbfddfd.gftWindow(),
                                            pbrfntWindow,
                                            0, 0);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
    }

    void ibndlfClifntMfssbgf(XEvfnt xfv) {
        XClifntMfssbgfEvfnt msg = xfv.gft_xdlifnt();
        if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            xfmbfdLog.finf(msg.toString());
        }
        if (msg.gft_mfssbgf_typf() == XEmbfd.gftAtom()) {
            if (xfmbfdLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                xfmbfdLog.finf("Embfddfd mfssbgf: " + msgidToString((int)msg.gft_dbtb(1)));
            }
            switdi ((int)msg.gft_dbtb(1)) {
              dbsf XEMBED_EMBEDDED_NOTIFY: // Notifidbtion bbout fmbfdding protodol stbrt
                  bdtivf = truf;
                  sfrvfr = gftEmbfddfr(fmbfddfd, msg);
                  // Cifdk if window is rfpbrfntfd. If not - it wbs drfbtfd witi
                  // pbrfnt bnd so wf siould updbtf it ifrf.
                  if (!fmbfddfd.isRfpbrfntfd()) {
                      fmbfddfd.sftRfpbrfntfd(truf);
                      fmbfddfd.updbtfSizfHints();
                  }
                  fmbfddfd.notifyStbrtfd();
                  brfbk;
              dbsf XEMBED_WINDOW_ACTIVATE:
                  bpplidbtionAdtivf = truf;
                  brfbk;
              dbsf XEMBED_WINDOW_DEACTIVATE:
                  if (bpplidbtionAdtivf) {
                      bpplidbtionAdtivf = fblsf;
                      ibndlfWindowFodusOut();
                  }
                  brfbk;
              dbsf XEMBED_FOCUS_IN: // Wf got fodus!
                  // Cifdk for dirfdtion
                  ibndlfFodusIn((int)msg.gft_dbtb(2));
                  brfbk;
              dbsf XEMBED_FOCUS_OUT:
                  if (bpplidbtionAdtivf) {
                      ibndlfWindowFodusOut();
                  }
                  brfbk;
            }
        }
    }
    void ibndlfFodusIn(int dftbil) {
        if (fmbfddfd.fodusAllowfdFor()) {
            fmbfddfd.ibndlfWindowFodusIn(0);
        }
        switdi(dftbil) {
          dbsf XEMBED_FOCUS_CURRENT:
              // Do notiing - just rfstorf to tif durrfnt vbluf
              brfbk;
          dbsf XEMBED_FOCUS_FIRST:
              SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(fmbfddfd.tbrgft, nfw Runnbblf() {
                      publid void run() {
                          Componfnt domp = ((Contbinfr)fmbfddfd.tbrgft).gftFodusTrbvfrsblPolidy().gftFirstComponfnt((Contbinfr)fmbfddfd.tbrgft);
                          if (domp != null) {
                              domp.rfqufstFodusInWindow();
                          }
                      }});
              brfbk;
          dbsf XEMBED_FOCUS_LAST:
              SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(fmbfddfd.tbrgft, nfw Runnbblf() {
                      publid void run() {
                          Componfnt domp = ((Contbinfr)fmbfddfd.tbrgft).gftFodusTrbvfrsblPolidy().gftLbstComponfnt((Contbinfr)fmbfddfd.tbrgft);
                          if (domp != null) {
                              domp.rfqufstFodusInWindow();
                          }
                      }});
              brfbk;
        }
    }

    publid void dispbtdiEvfnt(XEvfnt xfv) {
        switdi(xfv.gft_typf()) {
          dbsf XConstbnts.ClifntMfssbgf:
              ibndlfClifntMfssbgf(xfv);
              brfbk;
          dbsf XConstbnts.RfpbrfntNotify:
              ibndlfRfpbrfntNotify(xfv);
              brfbk;
        }
    }
    publid void ibndlfRfpbrfntNotify(XEvfnt xfv) {
        XRfpbrfntEvfnt rf = xfv.gft_xrfpbrfnt();
        long nfwPbrfnt = rf.gft_pbrfnt();
        if (bdtivf) {
            // unrfgistfr bddflfrbtors, ftd. for old pbrfnt
            fmbfddfd.notifyStoppfd();
            // difdk if nfwPbrfnt is b root window
            X11GrbpiidsConfig gd = (X11GrbpiidsConfig)fmbfddfd.gftGrbpiidsConfigurbtion();
            X11GrbpiidsDfvidf gd = (X11GrbpiidsDfvidf)gd.gftDfvidf();
            if ((nfwPbrfnt == XlibUtil.gftRootWindow(gd.gftSdrffn())) ||
                (nfwPbrfnt == XToolkit.gftDffbultRootWindow()))
            {
                // rfpbrfnting to root mfbns XEmbfd tfrminbtion
                bdtivf = fblsf;
            } flsf {
                // dontinuf XEmbfd witi b nfw pbrfnt
                sfrvfr = nfwPbrfnt;
                fmbfddfd.notifyStbrtfd();
            }
        }
    }
    boolfbn rfqufstFodus() {
        if (bdtivf && fmbfddfd.fodusAllowfdFor()) {
            sfndMfssbgf(sfrvfr, XEMBED_REQUEST_FOCUS);
            rfturn truf;
        }
        rfturn fblsf;
    }
    void ibndlfWindowFodusOut() {
        // fix for 6269309: it is possiblf tibt wf dbll tiis mftiod twidf
        // (for fxbmplf, wifn rfdfiving XEMBED_WINDOW_DEACTIVATE bnd tifn
        // XEMBED_FOCUS_OUT dlifnt mfssbgfs), so wf first nffd to difdk if
        // fmbfddfd is bn bdtivf window bfforf sfnding WINDOW_LOST_FOCUS
        // to sibrfd dodf
        if (XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow() == fmbfddfd.tbrgft) {
            fmbfddfd.ibndlfWindowFodusOut(null, 0);
        }
    }

    long gftEmbfddfr(XWindowPffr fmbfddfd, XClifntMfssbgfEvfnt info) {
        // Embfddfr is tif pbrfnt of fmbfddfd.
        rfturn XlibUtil.gftPbrfntWindow(fmbfddfd.gftWindow());
    }

    boolfbn isApplidbtionAdtivf() {
        rfturn bpplidbtionAdtivf;
    }

    boolfbn isAdtivf() {
        rfturn bdtivf;
    }

    void trbvfrsfOutForwbrd() {
        if (bdtivf) {
            sfndMfssbgf(sfrvfr, XEMBED_FOCUS_NEXT);
        }
    }

    void trbvfrsfOutBbdkwbrd() {
        if (bdtivf) {
            sfndMfssbgf(sfrvfr, XEMBED_FOCUS_PREV);
        }
    }

    void rfgistfrAddflfrbtor(AWTKfyStrokf strokf, int id) {
        if (bdtivf) {
            long sym = gftX11KfySym(strokf);
            long mods = gftX11Mods(strokf);
            sfndMfssbgf(sfrvfr, XEMBED_REGISTER_ACCELERATOR, id, sym, mods);
        }
    }
    void unrfgistfrAddflfrbtor(int id) {
        if (bdtivf) {
            sfndMfssbgf(sfrvfr, XEMBED_UNREGISTER_ACCELERATOR, id, 0, 0);
        }
    }

    long gftX11KfySym(AWTKfyStrokf strokf) {
        XToolkit.bwtLodk();
        try {
            rfturn XWindow.gftKfySymForAWTKfyCodf(strokf.gftKfyCodf());
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    long gftX11Mods(AWTKfyStrokf strokf) {
        rfturn XWindow.gftXModififrs(strokf);
    }
}
