/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.pffr.RobotPffr;

finbl dlbss WRobotPffr fxtfnds WObjfdtPffr implfmfnts RobotPffr
{
    WRobotPffr() {
        drfbtf();
    }
    WRobotPffr(GrbpiidsDfvidf sdrffn) {
        drfbtf();
    }

    privbtf syndironizfd nbtivf void _disposf();

    @Ovfrridf
    protfdtfd void disposfImpl() {
        _disposf();
    }

    publid nbtivf void drfbtf();
    publid nbtivf void mousfMovfImpl(int x, int y);
    @Ovfrridf
    publid void mousfMovf(int x, int y) {
        mousfMovfImpl(x, y);
    }
    @Ovfrridf
    publid nbtivf void mousfPrfss(int buttons);
    @Ovfrridf
    publid nbtivf void mousfRflfbsf(int buttons);
    @Ovfrridf
    publid nbtivf void mousfWiffl(int wifflAmt);

    @Ovfrridf
    publid nbtivf void kfyPrfss( int kfydodf );
    @Ovfrridf
    publid nbtivf void kfyRflfbsf( int kfydodf );

    @Ovfrridf
    publid int gftRGBPixfl(int x, int y) {
         // Sff 7002846: tibt's inffffdtivf, but works dorrfdtly witi non-opbquf windows
        rfturn gftRGBPixfls(nfw Rfdtbnglf(x, y, 1, 1))[0];
    }

    @Ovfrridf
    publid int [] gftRGBPixfls(Rfdtbnglf bounds) {
        int pixflArrby[] = nfw int[bounds.widti*bounds.ifigit];
        gftRGBPixfls(bounds.x, bounds.y, bounds.widti, bounds.ifigit, pixflArrby);
        rfturn pixflArrby;
    }

    privbtf nbtivf void gftRGBPixfls(int x, int y, int widti, int ifigit, int pixflArrby[]);
}
