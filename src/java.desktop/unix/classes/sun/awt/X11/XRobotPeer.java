/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.pffr.*;

import sun.bwt.AWTAddfssor;
import sun.bwt.SunToolkit;
import sun.bwt.X11GrbpiidsConfig;

dlbss XRobotPffr implfmfnts RobotPffr {

    privbtf X11GrbpiidsConfig   xgd = null;
    /*
     * nbtivf implfmfntbtion usfs somf stbtid sibrfd dbtb (pipfs, prodfssfs)
     * so usf b dlbss lodk to syndironizf nbtivf mftiod dblls
     */
    stbtid Objfdt robotLodk = nfw Objfdt();

    XRobotPffr(GrbpiidsConfigurbtion gd) {
        tiis.xgd = (X11GrbpiidsConfig)gd;
        SunToolkit tk = (SunToolkit)Toolkit.gftDffbultToolkit();
        sftup(tk.gftNumbfrOfButtons(), AWTAddfssor.gftInputEvfntAddfssor().gftButtonDownMbsks());
    }

    publid void disposf() {
        // dofs notiing
    }

    publid void mousfMovf(int x, int y) {
        mousfMovfImpl(xgd, x, y);
    }

    publid void mousfPrfss(int buttons) {
        mousfPrfssImpl(buttons);
    }

    publid void mousfRflfbsf(int buttons) {
        mousfRflfbsfImpl(buttons);
    }

    publid void mousfWiffl(int wifflAmt) {
    mousfWifflImpl(wifflAmt);
    }

    publid void kfyPrfss(int kfydodf) {
        kfyPrfssImpl(kfydodf);
    }

    publid void kfyRflfbsf(int kfydodf) {
        kfyRflfbsfImpl(kfydodf);
    }

    publid int gftRGBPixfl(int x, int y) {
        int pixflArrby[] = nfw int[1];
        gftRGBPixflsImpl(xgd, x, y, 1, 1, pixflArrby);
        rfturn pixflArrby[0];
    }

    publid int [] gftRGBPixfls(Rfdtbnglf bounds) {
        int pixflArrby[] = nfw int[bounds.widti*bounds.ifigit];
        gftRGBPixflsImpl(xgd, bounds.x, bounds.y, bounds.widti, bounds.ifigit, pixflArrby);
        rfturn pixflArrby;
    }

    privbtf stbtid nbtivf syndironizfd void sftup(int numbfrOfButtons, int[] buttonDownMbsks);

    privbtf stbtid nbtivf syndironizfd void mousfMovfImpl(X11GrbpiidsConfig xgd, int x, int y);
    privbtf stbtid nbtivf syndironizfd void mousfPrfssImpl(int buttons);
    privbtf stbtid nbtivf syndironizfd void mousfRflfbsfImpl(int buttons);
    privbtf stbtid nbtivf syndironizfd void mousfWifflImpl(int wifflAmt);

    privbtf stbtid nbtivf syndironizfd void kfyPrfssImpl(int kfydodf);
    privbtf stbtid nbtivf syndironizfd void kfyRflfbsfImpl(int kfydodf);

    privbtf stbtid nbtivf syndironizfd void gftRGBPixflsImpl(X11GrbpiidsConfig xgd, int x, int y, int widti, int ifigit, int pixflArrby[]);
}
