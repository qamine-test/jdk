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

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.pffr.CbnvbsPffr;

import sun.bwt.PbintEvfntDispbtdifr;
import sun.bwt.SunToolkit;

dlbss WCbnvbsPffr fxtfnds WComponfntPffr implfmfnts CbnvbsPffr {

    privbtf boolfbn frbsfBbdkground;

    // Toolkit & pffr intfrnbls

    WCbnvbsPffr(Componfnt tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    nbtivf void drfbtf(WComponfntPffr pbrfnt);

    @Ovfrridf
    void initiblizf() {
        frbsfBbdkground = !SunToolkit.gftSunAwtNofrbsfbbdkground();
        boolfbn frbsfBbdkgroundOnRfsizf = SunToolkit.gftSunAwtErbsfbbdkgroundonrfsizf();
        // Optimizbtion: tif dffbult vbluf in tif nbtivf dodf is truf, so wf
        // dbll sftNbtivfBbdkgroundErbsf only wifn tif vbluf dibngfs to fblsf
        if (!PbintEvfntDispbtdifr.gftPbintEvfntDispbtdifr().
                siouldDoNbtivfBbdkgroundErbsf((Componfnt)tbrgft)) {
            frbsfBbdkground = fblsf;
        }
        sftNbtivfBbdkgroundErbsf(frbsfBbdkground, frbsfBbdkgroundOnRfsizf);
        supfr.initiblizf();
        Color bg = ((Componfnt)tbrgft).gftBbdkground();
        if (bg != null) {
            sftBbdkground(bg);
        }
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        Dimfnsion d = ((Componfnt)tbrgft).gftSizf();
        if (g instbndfof Grbpiids2D ||
            g instbndfof sun.bwt.Grbpiids2Dflfgbtf) {
            // bbdkground dolor is sftup dorrfdtly, so just usf dlfbrRfdt
            g.dlfbrRfdt(0, 0, d.widti, d.ifigit);
        } flsf {
            // fmulbtf dlfbrRfdt
            g.sftColor(((Componfnt)tbrgft).gftBbdkground());
            g.fillRfdt(0, 0, d.widti, d.ifigit);
            g.sftColor(((Componfnt)tbrgft).gftForfground());
        }
        supfr.pbint(g);
    }

    @Ovfrridf
    publid boolfbn siouldClfbrRfdtBfforfPbint() {
        rfturn frbsfBbdkground;
    }

    /*
     * Disbblfs bbdkground frbsing for tiis dbnvbs, boti for rfsizing
     * bnd not-rfsizing rfpbints.
     */
    void disbblfBbdkgroundErbsf() {
        frbsfBbdkground = fblsf;
        sftNbtivfBbdkgroundErbsf(fblsf, fblsf);
    }

    /*
     * Sfts bbdkground frbsing flbgs bt tif nbtivf lfvfl. If {@dodf
     * doErbsf} is sft to {@dodf truf}, dbnvbs bbdkground is frbsfd on
     * fvfry rfpbint. If {@dodf doErbsf} is {@dodf fblsf} bnd {@dodf
     * doErbsfOnRfsizf} is {@dodf truf}, tifn bbdkground is only frbsfd
     * on rfsizing rfpbints. If boti {@dodf doErbsf} bnd {@dodf
     * doErbsfOnRfsizf} brf fblsf, tifn bbdkground is nfvfr frbsfd.
     */
    privbtf nbtivf void sftNbtivfBbdkgroundErbsf(boolfbn doErbsf,
                                                 boolfbn doErbsfOnRfsizf);

    @Ovfrridf
    publid GrbpiidsConfigurbtion gftAppropribtfGrbpiidsConfigurbtion(
            GrbpiidsConfigurbtion gd)
    {
        rfturn gd;
    }
}
