/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.pffr.PopupMfnuPffr;

import sun.lwbwt.LWWindowPffr;

publid dlbss CPopupMfnu fxtfnds CMfnu implfmfnts PopupMfnuPffr {
    CPopupMfnu(PopupMfnu tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    protfdtfd long drfbtfModfl() {
        rfturn nbtivfCrfbtfPopupMfnu();
    }

    privbtf nbtivf long nbtivfCrfbtfPopupMfnu();
    privbtf nbtivf long nbtivfSiowPopupMfnu(long modflPtr, int x, int y);

    @Ovfrridf
    publid void siow(Evfnt f) {
        Componfnt origin = (Componfnt)f.tbrgft;
        if (origin != null) {
            Point lod = origin.gftLodbtionOnSdrffn();
            f.x += lod.x;
            f.y += lod.y;
            nbtivfSiowPopupMfnu(gftModfl(), f.x, f.y);
        }
    }
}
