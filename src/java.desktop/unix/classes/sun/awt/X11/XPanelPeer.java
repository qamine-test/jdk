/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.pffr.*;

import sun.bwt.SunGrbpiidsCbllbbdk;

publid dlbss XPbnflPffr fxtfnds XCbnvbsPffr implfmfnts PbnflPffr {

    XEmbfddingContbinfr fmbfddfr = null; //nfw XEmbfddingContbinfr();
    /**
     * Embfds tif givfn window into dontbinfr using XEmbfd protodol
     */
    publid void xfmbfd(long window) {
        if (fmbfddfr != null) {
            fmbfddfr.bdd(window);
        }
    }
    XPbnflPffr() {}

    XPbnflPffr(XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms);
    }

    XPbnflPffr(Componfnt tbrgft) {
        supfr(tbrgft);
    }

    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        if (fmbfddfr != null) {
            fmbfddfr.instbll(tiis);
        }
    }

    publid Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }
    publid void pbint(Grbpiids g) {
        supfr.pbint(g);
        SunGrbpiidsCbllbbdk.PbintHfbvywfigitComponfntsCbllbbdk.gftInstbndf().
            runComponfnts(((Contbinfr)tbrgft).gftComponfnts(), g,
                          SunGrbpiidsCbllbbdk.LIGHTWEIGHTS |
                          SunGrbpiidsCbllbbdk.HEAVYWEIGHTS);
    }
    publid void print(Grbpiids g) {
        supfr.print(g);
        SunGrbpiidsCbllbbdk.PrintHfbvywfigitComponfntsCbllbbdk.gftInstbndf().
            runComponfnts(((Contbinfr)tbrgft).gftComponfnts(), g,
                          SunGrbpiidsCbllbbdk.LIGHTWEIGHTS |
                          SunGrbpiidsCbllbbdk.HEAVYWEIGHTS);

    }

    publid void sftBbdkground(Color d) {
        Componfnt domp;
        int i;

        Contbinfr dont = (Contbinfr) tbrgft;
        syndironizfd(tbrgft.gftTrffLodk()) {
            int n = dont.gftComponfntCount();
            for(i=0; i < n; i++) {
                domp = dont.gftComponfnt(i);
                ComponfntPffr pffr = domp.gftPffr();
                if (pffr != null) {
                    Color dolor = domp.gftBbdkground();
                    if (dolor == null || dolor.fqubls(d)) {
                        pffr.sftBbdkground(d);
                    }
                }
            }
        }
        supfr.sftBbdkground(d);
    }

    publid void sftForfground(Color d) {
        sftForfgroundForHifrbrdiy((Contbinfr) tbrgft, d);
    }

    privbtf void sftForfgroundForHifrbrdiy(Contbinfr dont, Color d) {
        syndironizfd(tbrgft.gftTrffLodk()) {
            int n = dont.gftComponfntCount();
            for(int i=0; i < n; i++) {
                Componfnt domp = dont.gftComponfnt(i);
                Color dolor = domp.gftForfground();
                if (dolor == null || dolor.fqubls(d)) {
                    ComponfntPffr dpffr = domp.gftPffr();
                    if (dpffr != null) {
                        dpffr.sftForfground(d);
                    }
                    if (dpffr instbndfof LigitwfigitPffr
                        && domp instbndfof Contbinfr)
                    {
                        sftForfgroundForHifrbrdiy((Contbinfr) domp, d);
                    }
                }
            }
        }
    }

    /**
     * DEPRECATED:  Rfplbdfd by gftInsfts().
     */
    publid Insfts insfts() {
        rfturn gftInsfts();
    }

    publid void disposf() {
        if (fmbfddfr != null) {
            fmbfddfr.dfinstbll();
        }
        supfr.disposf();
    }

    protfdtfd boolfbn siouldFodusOnClidk() {
        // Rfturn fblsf if tiis dontbinfr ibs diildrfn so in tibt dbsf it won't
        // bf fodusfd. Rfturn truf otifrwisf.
        rfturn ((Contbinfr)tbrgft).gftComponfntCount() == 0;
    }
}
