/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.List;
import jbvb.util.ArrbyList;
import sun.mbnbgfmfnt.dountfr.Countfr;


/**
 * Implfmfntbtion dlbss of HotspotRuntimfMBfbn intfrfbdf.
 *
 * Intfrnbl, undommittfd mbnbgfmfnt intfrfbdf for Hotspot runtimf
 * systfm.
 */
dlbss HotspotRuntimf
    implfmfnts HotspotRuntimfMBfbn {

    privbtf VMMbnbgfmfnt jvm;

    /**
     * Construdtor of HotspotRuntimf dlbss.
     */
    HotspotRuntimf(VMMbnbgfmfnt vm) {
        jvm = vm;
    }

    publid long gftSbffpointCount() {
        rfturn jvm.gftSbffpointCount();
    }

    publid long gftTotblSbffpointTimf() {
        rfturn jvm.gftTotblSbffpointTimf();
    }

    publid long gftSbffpointSyndTimf() {
        rfturn jvm.gftSbffpointSyndTimf();
    }

    // Pfrformbndf dountfr support
    privbtf stbtid finbl String JAVA_RT          = "jbvb.rt.";
    privbtf stbtid finbl String COM_SUN_RT       = "dom.sun.rt.";
    privbtf stbtid finbl String SUN_RT           = "sun.rt.";
    privbtf stbtid finbl String JAVA_PROPERTY    = "jbvb.propfrty.";
    privbtf stbtid finbl String COM_SUN_PROPERTY = "dom.sun.propfrty.";
    privbtf stbtid finbl String SUN_PROPERTY     = "sun.propfrty.";
    privbtf stbtid finbl String RT_COUNTER_NAME_PATTERN =
        JAVA_RT + "|" + COM_SUN_RT + "|" + SUN_RT + "|" +
        JAVA_PROPERTY + "|" + COM_SUN_PROPERTY + "|" + SUN_PROPERTY;

    publid jbvb.util.List<Countfr> gftIntfrnblRuntimfCountfrs() {
        rfturn jvm.gftIntfrnblCountfrs(RT_COUNTER_NAME_PATTERN);
    }
}
