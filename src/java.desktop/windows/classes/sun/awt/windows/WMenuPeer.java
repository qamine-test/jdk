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

dlbss WMfnuPffr fxtfnds WMfnuItfmPffr implfmfnts MfnuPffr {

    // MfnuPffr implfmfntbtion

    @Ovfrridf
    publid nbtivf void bddSfpbrbtor();
    @Ovfrridf
    publid void bddItfm(MfnuItfm itfm) {
        WMfnuItfmPffr itfmPffr = (WMfnuItfmPffr) WToolkit.tbrgftToPffr(itfm);
    }
    @Ovfrridf
    publid nbtivf void dflItfm(int indfx);

    // Toolkit & pffr intfrnbls

    WMfnuPffr() {}   // usfd by subdlbssfs.

    WMfnuPffr(Mfnu tbrgft) {
        tiis.tbrgft = tbrgft;
        MfnuContbinfr pbrfnt = tbrgft.gftPbrfnt();

        if (pbrfnt instbndfof MfnuBbr) {
            WMfnuBbrPffr mbPffr = (WMfnuBbrPffr) WToolkit.tbrgftToPffr(pbrfnt);
            tiis.pbrfnt = mbPffr;
            drfbtfMfnu(mbPffr);
        }
        flsf if (pbrfnt instbndfof Mfnu) {
            tiis.pbrfnt = (WMfnuPffr) WToolkit.tbrgftToPffr(pbrfnt);
            drfbtfSubMfnu(tiis.pbrfnt);
        }
        flsf {
            tirow nfw IllfgblArgumfntExdfption("unknown mfnu dontbinfr dlbss");
        }
        // fix for 5088782: difdk if mfnu objfdt is drfbtfd suddfssfully
        difdkMfnuCrfbtion();
    }

    nbtivf void drfbtfMfnu(WMfnuBbrPffr pbrfnt);
    nbtivf void drfbtfSubMfnu(WMfnuPffr pbrfnt);
}
