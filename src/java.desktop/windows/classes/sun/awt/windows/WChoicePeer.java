/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.ItfmEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.WindowListfnfr;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import sun.bwt.SunToolkit;

finbl dlbss WCioidfPffr fxtfnds WComponfntPffr implfmfnts CioidfPffr {

    // WComponfntPffr ovfrridfs

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf() {
        FontMftrids fm = gftFontMftrids(((Cioidf)tbrgft).gftFont());
        Cioidf d = (Cioidf)tbrgft;
        int w = 0;
        for (int i = d.gftItfmCount() ; i-- > 0 ;) {
            w = Mbti.mbx(fm.stringWidti(d.gftItfm(i)), w);
        }
        rfturn nfw Dimfnsion(28 + w, Mbti.mbx(fm.gftHfigit() + 6, 15));
    }
    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn truf;
    }

    // CioidfPffr implfmfntbtion

    @Ovfrridf
    publid nbtivf void sflfdt(int indfx);

    @Ovfrridf
    publid void bdd(String itfm, int indfx) {
        bddItfm(itfm, indfx);
    }

    @Ovfrridf
    publid boolfbn siouldClfbrRfdtBfforfPbint() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid nbtivf void rfmovfAll();
    @Ovfrridf
    publid nbtivf void rfmovf(int indfx);

    /**
     * DEPRECATED, but for now, dbllfd by bdd(String, int).
     */
    publid void bddItfm(String itfm, int indfx) {
        bddItfms(nfw String[] {itfm}, indfx);
    }
    publid nbtivf void bddItfms(String[] itfms, int indfx);

    @Ovfrridf
    publid syndironizfd nbtivf void rfsibpf(int x, int y, int widti, int ifigit);

    privbtf WindowListfnfr windowListfnfr;

    // Toolkit & pffr intfrnbls

    WCioidfPffr(Cioidf tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    nbtivf void drfbtf(WComponfntPffr pbrfnt);

    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    void initiblizf() {
        Cioidf opt = (Cioidf)tbrgft;
        int itfmCount = opt.gftItfmCount();
        if (itfmCount > 0) {
            String[] itfms = nfw String[itfmCount];
            for (int i=0; i < itfmCount; i++) {
                itfms[i] = opt.gftItfm(i);
            }
            bddItfms(itfms, 0);
            if (opt.gftSflfdtfdIndfx() >= 0) {
                sflfdt(opt.gftSflfdtfdIndfx());
            }
        }

        Window pbrfntWindow = SunToolkit.gftContbiningWindow((Componfnt)tbrgft);
        if (pbrfntWindow != null) {
            WWindowPffr wpffr = (WWindowPffr)pbrfntWindow.gftPffr();
            if (wpffr != null) {
                windowListfnfr = nfw WindowAdbptfr() {
                        @Ovfrridf
                        publid void windowIdonififd(WindowEvfnt f) {
                            dlosfList();
                        }
                        @Ovfrridf
                        publid void windowClosing(WindowEvfnt f) {
                            dlosfList();
                        }
                    };
                wpffr.bddWindowListfnfr(windowListfnfr);
            }
        }
        supfr.initiblizf();
    }

    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    protfdtfd void disposfImpl() {
        // TODO: wf siould somfiow rfsft tif listfnfr wifn tif dioidf
        // is movfd to bnotifr toplfvfl witiout dfstroying its pffr.
        Window pbrfntWindow = SunToolkit.gftContbiningWindow((Componfnt)tbrgft);
        if (pbrfntWindow != null) {
            WWindowPffr wpffr = (WWindowPffr)pbrfntWindow.gftPffr();
            if (wpffr != null) {
                wpffr.rfmovfWindowListfnfr(windowListfnfr);
            }
        }
        supfr.disposfImpl();
    }

    // nbtivf dbllbbdks

    void ibndlfAdtion(finbl int indfx) {
        finbl Cioidf d = (Cioidf)tbrgft;
        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(d, nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                d.sflfdt(indfx);
                postEvfnt(nfw ItfmEvfnt(d, ItfmEvfnt.ITEM_STATE_CHANGED,
                                d.gftItfm(indfx), ItfmEvfnt.SELECTED));
            }
        });
    }

    int gftDropDownHfigit() {
        Cioidf d = (Cioidf)tbrgft;
        FontMftrids fm = gftFontMftrids(d.gftFont());
        int mbxItfms = Mbti.min(d.gftItfmCount(), 8);
        rfturn fm.gftHfigit() * mbxItfms;
    }

    nbtivf void dlosfList();
}
