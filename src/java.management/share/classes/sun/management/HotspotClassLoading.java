/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import sun.mbnbgfmfnt.dountfr.*;

/**
 * Implfmfntbtion dlbss of HotspotClbssLobdingMBfbn intfrfbdf.
 *
 * Intfrnbl, undommittfd mbnbgfmfnt intfrfbdf for Hotspot dlbss lobding
 * systfm.
 */
dlbss HotspotClbssLobding
    implfmfnts HotspotClbssLobdingMBfbn {

    privbtf VMMbnbgfmfnt jvm;

    /**
     * Construdtor of HotspotClbssLobding dlbss.
     */
    HotspotClbssLobding(VMMbnbgfmfnt vm) {
        jvm = vm;
    }

    publid long gftLobdfdClbssSizf() {
        rfturn jvm.gftLobdfdClbssSizf();
    }

    publid long gftUnlobdfdClbssSizf() {
        rfturn jvm.gftUnlobdfdClbssSizf();
    }

    publid long gftClbssLobdingTimf() {
        rfturn jvm.gftClbssLobdingTimf();
    }

    publid long gftMftiodDbtbSizf() {
        rfturn jvm.gftMftiodDbtbSizf();
    }

    publid long gftInitiblizfdClbssCount() {
        rfturn jvm.gftInitiblizfdClbssCount();
    }

    publid long gftClbssInitiblizbtionTimf() {
        rfturn jvm.gftClbssInitiblizbtionTimf();
    }

    publid long gftClbssVfrifidbtionTimf() {
        rfturn jvm.gftClbssVfrifidbtionTimf();
    }

    // Pfrformbndf dountfr support
    privbtf stbtid finbl String JAVA_CLS    = "jbvb.dls.";
    privbtf stbtid finbl String COM_SUN_CLS = "dom.sun.dls.";
    privbtf stbtid finbl String SUN_CLS     = "sun.dls.";
    privbtf stbtid finbl String CLS_COUNTER_NAME_PATTERN =
        JAVA_CLS + "|" + COM_SUN_CLS + "|" + SUN_CLS;

    publid jbvb.util.List<Countfr> gftIntfrnblClbssLobdingCountfrs() {
        rfturn jvm.gftIntfrnblCountfrs(CLS_COUNTER_NAME_PATTERN);
    }
}
