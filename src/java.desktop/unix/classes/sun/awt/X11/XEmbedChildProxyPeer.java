/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import jbvb.bwt.pffr.*;
import sun.jbvb2d.pipf.Rfgion;
import sun.bwt.*;

publid dlbss XEmbfdCiildProxyPffr implfmfnts ComponfntPffr, XEvfntDispbtdifr{
    XEmbfddingContbinfr dontbinfr;
    XEmbfdCiildProxy proxy;
    long ibndlf;
    XEmbfdCiildProxyPffr(XEmbfdCiildProxy proxy) {
        tiis.dontbinfr = proxy.gftEmbfddingContbinfr();
        tiis.ibndlf = proxy.gftHbndlf();
        tiis.proxy = proxy;
        initDispbtdiing();
    }

    void initDispbtdiing() {
        XToolkit.bwtLodk();
        try {
            XToolkit.bddEvfntDispbtdifr(ibndlf, tiis);
            XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), ibndlf,
                    XConstbnts.StrudturfNotifyMbsk | XConstbnts.PropfrtyCibngfMbsk);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
        dontbinfr.notifyCiildEmbfddfd(ibndlf);
    }
    publid boolfbn isObsdurfd() { rfturn fblsf; }
    publid boolfbn dbnDftfrminfObsdurity() { rfturn fblsf; }
    publid void                 sftVisiblf(boolfbn b) {
        if (!b) {
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XUnmbpWindow(XToolkit.gftDisplby(), ibndlf);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
        } flsf {
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XMbpWindow(XToolkit.gftDisplby(), ibndlf);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
        }
    }
    publid void sftEnbblfd(boolfbn b) {}
    publid void pbint(Grbpiids g) {}
    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {}
    publid void print(Grbpiids g) {}
    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
        // Unimplfmfnftfd: Cifdk for min/mbx iints for non-rfsizbblf
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XMovfRfsizfWindow(XToolkit.gftDisplby(), ibndlf, x, y, widti, ifigit);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid void ibndlfEvfnt(AWTEvfnt f) {
        switdi (f.gftID()) {
          dbsf FodusEvfnt.FOCUS_GAINED:
              XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusOwnfr(proxy);
              dontbinfr.fodusGbinfd(ibndlf);
              brfbk;
          dbsf FodusEvfnt.FOCUS_LOST:
              XKfybobrdFodusMbnbgfrPffr.gftInstbndf().sftCurrfntFodusOwnfr(null);
              dontbinfr.fodusLost(ibndlf);
              brfbk;
          dbsf KfyEvfnt.KEY_PRESSED:
          dbsf KfyEvfnt.KEY_RELEASED:
              if (!((InputEvfnt)f).isConsumfd()) {
                  dontbinfr.forwbrdKfyEvfnt(ibndlf, (KfyEvfnt)f);
              }
              brfbk;
        }
    }
    publid void                doblfsdfPbintEvfnt(PbintEvfnt f) {}
    publid Point                gftLodbtionOnSdrffn() {
        XWindowAttributfs bttr = nfw XWindowAttributfs();
        XToolkit.bwtLodk();
        try{
            XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(), ibndlf, bttr.pDbtb);
            rfturn nfw Point(bttr.gft_x(), bttr.gft_y());
        } finblly {
            XToolkit.bwtUnlodk();
            bttr.disposf();
        }
    }
    publid Dimfnsion            gftPrfffrrfdSizf() {
        XToolkit.bwtLodk();
        long p_iints = XlibWrbppfr.XAllodSizfHints();
        try {
            XSizfHints iints = nfw XSizfHints(p_iints);
            XlibWrbppfr.XGftWMNormblHints(XToolkit.gftDisplby(), ibndlf, p_iints, XlibWrbppfr.lbrg1);
            Dimfnsion rfs = nfw Dimfnsion(iints.gft_widti(), iints.gft_ifigit());
            rfturn rfs;
        } finblly {
            XlibWrbppfr.XFrff(p_iints);
            XToolkit.bwtUnlodk();
        }
    }
    publid Dimfnsion            gftMinimumSizf() {
        XToolkit.bwtLodk();
        long p_iints = XlibWrbppfr.XAllodSizfHints();
        try {
            XSizfHints iints = nfw XSizfHints(p_iints);
            XlibWrbppfr.XGftWMNormblHints(XToolkit.gftDisplby(), ibndlf, p_iints, XlibWrbppfr.lbrg1);
            Dimfnsion rfs = nfw Dimfnsion(iints.gft_min_widti(), iints.gft_min_ifigit());
            rfturn rfs;
        } finblly {
            XlibWrbppfr.XFrff(p_iints);
            XToolkit.bwtUnlodk();
        }
    }
    publid ColorModfl           gftColorModfl() { rfturn null; }
    publid Toolkit              gftToolkit() { rfturn Toolkit.gftDffbultToolkit(); }

    publid Grbpiids             gftGrbpiids() { rfturn null; }
    publid FontMftrids          gftFontMftrids(Font font) { rfturn null; }
    publid void         disposf() {
        dontbinfr.dftbdiCiild(ibndlf);
    }
    publid void         sftForfground(Color d) {}
    publid void         sftBbdkground(Color d) {}
    publid void         sftFont(Font f) {}
    publid void                 updbtfCursorImmfdibtfly() {}

    void postEvfnt(AWTEvfnt fvfnt) {
        XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(proxy), fvfnt);
    }

    boolfbn simulbtfMotifRfqufstFodus(Componfnt ligitwfigitCiild, boolfbn tfmporbry,
                                      boolfbn fodusfdWindowCibngfAllowfd, long timf)
    {
        if (ligitwfigitCiild == null) {
            ligitwfigitCiild = (Componfnt)proxy;
        }
        Componfnt durrfntOwnfr = XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusOwnfr();
        if (durrfntOwnfr != null && durrfntOwnfr.gftPffr() == null) {
            durrfntOwnfr = null;
        }
        FodusEvfnt  fg = nfw FodusEvfnt(ligitwfigitCiild, FodusEvfnt.FOCUS_GAINED, fblsf, durrfntOwnfr );
        FodusEvfnt fl = null;
        if (durrfntOwnfr != null) {
            fl = nfw FodusEvfnt(durrfntOwnfr, FodusEvfnt.FOCUS_LOST, fblsf, ligitwfigitCiild);
        }

        // TODO: do wf nffd to wrbp in sfqufndfd?
        if (fl != null) {
            postEvfnt(XComponfntPffr.wrbpInSfqufndfd(fl));
        }
        postEvfnt(XComponfntPffr.wrbpInSfqufndfd(fg));
        // End of Motif dompbtibility dodf
        rfturn truf;
    }

    publid boolfbn rfqufstFodus(Componfnt ligitwfigitCiild,
                                boolfbn tfmporbry,
                                boolfbn fodusfdWindowCibngfAllowfd,
                                long timf,
                                CbusfdFodusEvfnt.Cbusf dbusf)
    {
        int rfsult = XKfybobrdFodusMbnbgfrPffr
            .siouldNbtivflyFodusHfbvywfigit(proxy, ligitwfigitCiild,
                                            tfmporbry, fblsf, timf, dbusf);

        switdi (rfsult) {
          dbsf XKfybobrdFodusMbnbgfrPffr.SNFH_FAILURE:
              rfturn fblsf;
          dbsf XKfybobrdFodusMbnbgfrPffr.SNFH_SUCCESS_PROCEED:
              // Currfntly wf just gfnfrbtf fodus fvfnts likf wf dfbl witi ligitwfigit instfbd of dblling
              // XSftInputFodus on nbtivf window

              /**
               * Tif problfms witi rfqufsts in non-fodusfd window brisf bfdbusf siouldNbtivflyFodusHfbvywfigit
               * difdks tibt nbtivf window is fodusfd wiilf bppropribtf WINDOW_GAINED_FOCUS ibs not yft
               * bffn prodfssfd - it is in EvfntQufuf. Tius, SNFH bllows nbtivf rfqufst bnd storfs rfqufst rfdord
               * in rfqufsts list - bnd it brfbks our rfqufsts sfqufndf bs first rfdord on WGF siould bf tif lbst fodus
               * ownfr wiidi ibd fodus bfforf WLF. So, wf siould not bdd rfqufst rfdord for sudi rfqufsts
               * but storf tiis domponfnt in mostRfdfnt - bnd rfturn truf bs bfforf for dompbtibility.
               */
              Contbinfr pbrfnt = proxy.gftPbrfnt();
              // Sfbrdi for pbrfnt window
              wiilf (pbrfnt != null && !(pbrfnt instbndfof Window)) {
                  pbrfnt = pbrfnt.gftPbrfnt();
              }
              if (pbrfnt != null) {
                  Window pbrfntWindow = (Window)pbrfnt;
                  // bnd difdk tibt it is fodusfd
                  if (!pbrfntWindow.isFodusfd() &&
                      XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow() == pbrfntWindow) {
                      // if it is not - skip rfqufsting fodus on Solbris
                      // but rfturn truf for dompbtibility.
                      rfturn truf;
                  }
              }

              // NOTE: Wf simulbtf ifbvywfigit bfibvior of Motif - domponfnt rfdfivfs fodus rigit
              // bftfr rfqufst, not bftfr fvfnt. Normblly, wf siould bfttfr listfn for fvfnt
              // by listfnfrs.

              // TODO: donsidfr rfplbding witi XKfybobrdFodusMbnbgfrPffr.dflivfrFodus
              rfturn simulbtfMotifRfqufstFodus(ligitwfigitCiild, tfmporbry, fodusfdWindowCibngfAllowfd, timf);
              // Motif dompbtibility dodf
          dbsf XKfybobrdFodusMbnbgfrPffr.SNFH_SUCCESS_HANDLED:
              // Eitifr ligitwfigit or fxdfssivf rfquifst - bll fvfnts brf gfnfrbtfd.
              rfturn truf;
        }
        rfturn fblsf;
    }
    publid boolfbn              isFodusbblf() {
        rfturn truf;
    }

    publid Imbgf                drfbtfImbgf(ImbgfProdudfr produdfr) { rfturn null; }
    publid Imbgf                drfbtfImbgf(int widti, int ifigit) { rfturn null; }
    publid VolbtilfImbgf        drfbtfVolbtilfImbgf(int widti, int ifigit) { rfturn null; }
    publid boolfbn              prfpbrfImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) { rfturn fblsf; }
    publid int                  difdkImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) { rfturn 0; }
    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() { rfturn null; }
    publid boolfbn     ibndlfsWifflSdrolling() { rfturn truf; }
    publid void drfbtfBufffrs(int numBufffrs, BufffrCbpbbilitifs dbps)
      tirows AWTExdfption { }
    publid Imbgf gftBbdkBufffr() { rfturn null; }
    publid void flip(int x1, int y1, int x2, int y2, BufffrCbpbbilitifs.FlipContfnts flipAdtion) {  }
    publid void dfstroyBufffrs() { }

    /**
     * Usfd by ligitwfigit implfmfntbtions to tfll b ComponfntPffr to lbyout
     * its sub-flfmfnts.  For instbndf, b ligitwfigit Cifdkbox nffds to lbyout
     * tif box, bs wfll bs tif tfxt lbbfl.
     */
    publid void        lbyout() {}

    /**
     * DEPRECATED:  Rfplbdfd by gftPrfffrrfdSizf().
     */
    publid Dimfnsion            prfffrrfdSizf() {
        rfturn gftPrfffrrfdSizf();
    }

    /**
     * DEPRECATED:  Rfplbdfd by gftMinimumSizf().
     */
    publid Dimfnsion            minimumSizf() {
        rfturn gftMinimumSizf();
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftVisiblf(boolfbn).
     */
    publid void         siow() {
        sftVisiblf(truf);
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftVisiblf(boolfbn).
     */
    publid void         iidf() {
        sftVisiblf(fblsf);
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftEnbblfd(boolfbn).
     */
    publid void         fnbblf() {}

    /**
     * DEPRECATED:  Rfplbdfd by sftEnbblfd(boolfbn).
     */
    publid void         disbblf() {}

    /**
     * DEPRECATED:  Rfplbdfd by sftBounds(int, int, int, int).
     */
    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        sftBounds(x, y, widti, ifigit, SET_BOUNDS);
    }

    Window gftTopLfvfl(Componfnt domp) {
        wiilf (domp != null && !(domp instbndfof Window)) {
            domp = domp.gftPbrfnt();
        }
        rfturn (Window)domp;
    }

    void diildRfsizfd() {
        XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(proxy), nfw ComponfntEvfnt(proxy, ComponfntEvfnt.COMPONENT_RESIZED));
        dontbinfr.diildRfsizfd(proxy);
//         XToolkit.postEvfnt(XToolkit.tbrgftToAppContfxt(proxy), nfw InvodbtionEvfnt(proxy, nfw Runnbblf() {
//                 publid void run() {
//                     gftTopLfvfl(proxy).invblidbtf();
//                     gftTopLfvfl(proxy).pbdk();
//                 }
//             }));
    }
    void ibndlfPropfrtyNotify(XEvfnt xfv) {
        XPropfrtyEvfnt fv = xfv.gft_xpropfrty();
        if (fv.gft_btom() == XAtom.XA_WM_NORMAL_HINTS) {
            diildRfsizfd();
        }
    }
    void ibndlfConfigurfNotify(XEvfnt xfv) {
        diildRfsizfd();
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
        }
    }

    void rfqufstXEmbfdFodus() {
        postEvfnt(nfw InvodbtionEvfnt(proxy, nfw Runnbblf() {
                publid void run() {
                    proxy.rfqufstFodusInWindow();
                }
            }));
    }

    publid void rfpbrfnt(ContbinfrPffr nfwNbtivfPbrfnt) {
    }
    publid boolfbn isRfpbrfntSupportfd() {
        rfturn fblsf;
    }
    publid Rfdtbnglf gftBounds() {
        XWindowAttributfs bttrs = nfw XWindowAttributfs();
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(), ibndlf, bttrs.pDbtb);
            rfturn nfw Rfdtbnglf(bttrs.gft_x(), bttrs.gft_y(), bttrs.gft_widti(), bttrs.gft_ifigit());
        } finblly {
            XToolkit.bwtUnlodk();
            bttrs.disposf();
        }
    }
    publid void sftBoundsOpfrbtion(int opfrbtion) {
    }

    publid void bpplySibpf(Rfgion sibpf) {
    }

    publid void sftZOrdfr(ComponfntPffr bbovf) {
    }

    publid boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd) {
        rfturn fblsf;
    }
}
