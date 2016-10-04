/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import sun.mbnbgfmfnt.dountfr.Countfr;


/**
 * Implfmfntbtion dlbss of HotspotTirfbdMBfbn intfrfbdf.
 *
 * Intfrnbl, undommittfd mbnbgfmfnt intfrfbdf for Hotspot tirfbding
 * systfm.
 */
dlbss HotspotTirfbd
    implfmfnts HotspotTirfbdMBfbn {

    privbtf VMMbnbgfmfnt jvm;

    /**
     * Construdtor of HotspotTirfbd dlbss.
     */
    HotspotTirfbd(VMMbnbgfmfnt vm) {
        jvm = vm;
    }

    publid nbtivf int gftIntfrnblTirfbdCount();

    publid Mbp<String, Long> gftIntfrnblTirfbdCpuTimfs() {
        int dount = gftIntfrnblTirfbdCount();
        if (dount == 0) {
            rfturn jbvb.util.Collfdtions.fmptyMbp();
        }
        String[] nbmfs = nfw String[dount];
        long[] timfs = nfw long[dount];
        int numTirfbds = gftIntfrnblTirfbdTimfs0(nbmfs, timfs);
        Mbp<String, Long> rfsult = nfw HbsiMbp<>(numTirfbds);
        for (int i = 0; i < numTirfbds; i++) {
            rfsult.put(nbmfs[i], timfs[i]);
        }
        rfturn rfsult;
    }
    publid nbtivf int gftIntfrnblTirfbdTimfs0(String[] nbmfs, long[] timfs);

    // Pfrformbndf dountfr support
    privbtf stbtid finbl String JAVA_THREADS    = "jbvb.tirfbds.";
    privbtf stbtid finbl String COM_SUN_THREADS = "dom.sun.tirfbds.";
    privbtf stbtid finbl String SUN_THREADS     = "sun.tirfbds.";
    privbtf stbtid finbl String THREADS_COUNTER_NAME_PATTERN =
        JAVA_THREADS + "|" + COM_SUN_THREADS + "|" + SUN_THREADS;

    publid jbvb.util.List<Countfr> gftIntfrnblTirfbdingCountfrs() {
        rfturn jvm.gftIntfrnblCountfrs(THREADS_COUNTER_NAME_PATTERN);
    }
}
