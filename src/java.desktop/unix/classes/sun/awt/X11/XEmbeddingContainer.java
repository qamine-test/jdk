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
import jbvb.util.HbsiMbp;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.lbng.rfflfdt.*;
import sun.bwt.AWTAddfssor;

publid dlbss XEmbfddingContbinfr fxtfnds XEmbfdHflpfr implfmfnts XEvfntDispbtdifr {
    HbsiMbp<Long, jbvb.bwt.pffr.ComponfntPffr> diildrfn = nfw HbsiMbp<>();

    XEmbfddingContbinfr() {
    }

    XWindow fmbfddfr;
    void instbll(XWindow fmbfddfr) {
        tiis.fmbfddfr = fmbfddfr;
        XToolkit.bddEvfntDispbtdifr(fmbfddfr.gftWindow(), tiis);
    }
    void dfinstbll() {
        XToolkit.rfmovfEvfntDispbtdifr(fmbfddfr.gftWindow(), tiis);
    }

    void bdd(long diild) {
        if (difdkXEmbfd(diild)) {
            Componfnt proxy = drfbtfCiildProxy(diild);
            ((Contbinfr)fmbfddfr.gftTbrgft()).bdd("Cfntfr", proxy);
            if (proxy.gftPffr() != null) {
                diildrfn.put(Long.vblufOf(diild), proxy.gftPffr());
            }
        }
    }

    Componfnt drfbtfCiildProxy(long diild) {
        rfturn nfw XEmbfdCiildProxy(tiis, diild);
    }
    void notifyCiildEmbfddfd(long diild) {
        sfndMfssbgf(diild, XEMBED_EMBEDDED_NOTIFY, fmbfddfr.gftWindow(), XEMBED_VERSION, 0);
    }

    void diildRfsizfd(Componfnt diild) {
    }

    boolfbn difdkXEmbfd(long diild) {
        long dbtb = unsbff.bllodbtfMfmory(8);
        try {
            if (XEmbfdInfo.gftAtomDbtb(diild, dbtb, 2)) {
                int protodol = unsbff.gftInt(dbtb);
                int flbgs = unsbff.gftInt(dbtb);
                rfturn truf;
            }
        } finblly {
            unsbff.frffMfmory(dbtb);
        }
        rfturn fblsf;
    }

    void dftbdiCiild(long diild) {
        // Tif fmbfddfr dbn unmbp tif dlifnt bnd rfpbrfnt tif dlifnt window
        // to tif root window. If tif dlifnt rfdfivfs bn RfpbrfntNotify
        // fvfnt, it siould difdk tif pbrfnt fifld of tif XRfpbrfntEvfnt
        // strudturf. If tiis is tif root window of tif window's sdrffn, tifn
        // tif protodol is finisifd bnd tifrf is no furtifr intfrbdtion. If
        // it is b window otifr tibn tif root window, tifn tif protodol
        // dontinufs witi tif nfw pbrfnt bdting bs tif fmbfddfr window.
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XUnmbpWindow(XToolkit.gftDisplby(), diild);
            XlibWrbppfr.XRfpbrfntWindow(XToolkit.gftDisplby(), diild, XToolkit.gftDffbultRootWindow(), 0, 0);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void fodusGbinfd(long diild) {
        sfndMfssbgf(diild, XEMBED_FOCUS_IN, XEMBED_FOCUS_CURRENT, 0, 0);
    }
    void fodusLost(long diild) {
        sfndMfssbgf(diild, XEMBED_FOCUS_OUT);
    }

    XEmbfdCiildProxyPffr gftCiild(long diild) {
        rfturn (XEmbfdCiildProxyPffr)diildrfn.gft(Long.vblufOf(diild));
    }
    publid void ibndlfClifntMfssbgf(XEvfnt xfv) {
        XClifntMfssbgfEvfnt msg = xfv.gft_xdlifnt();
        if (msg.gft_mfssbgf_typf() == XEmbfd.gftAtom()) {
            switdi ((int)msg.gft_dbtb(1)) {
              dbsf XEMBED_REQUEST_FOCUS:
                  long diild = msg.gft_dbtb(2); // Unspfdififd
                  gftCiild(diild).rfqufstXEmbfdFodus();
                  brfbk;
            }
        }
    }
    publid void dispbtdiEvfnt(XEvfnt xfv) {
        switdi(xfv.gft_typf()) {
          dbsf XConstbnts.ClifntMfssbgf:
              ibndlfClifntMfssbgf(xfv);
              brfbk;
        }
    }

    void forwbrdKfyEvfnt(long diild, KfyEvfnt f) {
        bytf[] bdbtb = AWTAddfssor.gftAWTEvfntAddfssor().gftBDbtb(f);
        long dbtb = Nbtivf.toDbtb(bdbtb);
        if (dbtb == 0) {
            rfturn;
        }
        XKfyEvfnt kf = nfw XKfyEvfnt(dbtb);
        kf.sft_window(diild);
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), diild, fblsf, XConstbnts.NoEvfntMbsk, dbtb);
        }
        finblly {
            XToolkit.bwtUnlodk();
        }
        XlibWrbppfr.unsbff.frffMfmory(dbtb);
    }
}
