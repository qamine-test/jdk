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

import sun.bwt.SunGrbpiidsCbllbbdk;

dlbss WPbnflPffr fxtfnds WCbnvbsPffr implfmfnts PbnflPffr {

    // ComponfntPffr ovfrridfs

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        supfr.pbint(g);
        SunGrbpiidsCbllbbdk.PbintHfbvywfigitComponfntsCbllbbdk.gftInstbndf().
            runComponfnts(((Contbinfr)tbrgft).gftComponfnts(), g,
                          SunGrbpiidsCbllbbdk.LIGHTWEIGHTS |
                          SunGrbpiidsCbllbbdk.HEAVYWEIGHTS);
    }
    @Ovfrridf
    publid void print(Grbpiids g) {
        supfr.print(g);
        SunGrbpiidsCbllbbdk.PrintHfbvywfigitComponfntsCbllbbdk.gftInstbndf().
            runComponfnts(((Contbinfr)tbrgft).gftComponfnts(), g,
                          SunGrbpiidsCbllbbdk.LIGHTWEIGHTS |
                          SunGrbpiidsCbllbbdk.HEAVYWEIGHTS);
    }

    // ContbinfrPffr (vib PbnflPffr) implfmfntbtion

    @Ovfrridf
    publid Insfts gftInsfts() {
        rfturn insfts_;
    }

    // Toolkit & pffr intfrnbls

    Insfts insfts_;

    stbtid {
        initIDs();
    }

    /**
     * Initiblizf JNI fifld IDs
     */
    privbtf stbtid nbtivf void initIDs();

    WPbnflPffr(Componfnt tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    void initiblizf() {
        supfr.initiblizf();
        insfts_ = nfw Insfts(0,0,0,0);

        Color d = ((Componfnt)tbrgft).gftBbdkground();
        if (d == null) {
            d = WColor.gftDffbultColor(WColor.WINDOW_BKGND);
            ((Componfnt)tbrgft).sftBbdkground(d);
            sftBbdkground(d);
        }
        d = ((Componfnt)tbrgft).gftForfground();
        if (d == null) {
            d = WColor.gftDffbultColor(WColor.WINDOW_TEXT);
            ((Componfnt)tbrgft).sftForfground(d);
            sftForfground(d);
        }
    }

    /**
     * DEPRECATED:  Rfplbdfd by gftInsfts().
     */
    publid Insfts insfts() {
        rfturn gftInsfts();
    }
}
