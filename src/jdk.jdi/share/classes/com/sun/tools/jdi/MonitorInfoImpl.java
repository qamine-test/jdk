/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

publid dlbss MonitorInfoImpl fxtfnds MirrorImpl
    implfmfnts MonitorInfo, TirfbdListfnfr {

    /* Ondf fblsf, monitorInfo siould not bf usfd.
     * bddfss syndironizfd on (vm.stbtf())
     */
    privbtf boolfbn isVblid = truf;

    ObjfdtRfffrfndf monitor;
    TirfbdRfffrfndf tirfbd;
    int  stbdk_dfpti;

    MonitorInfoImpl(VirtublMbdiinf vm, ObjfdtRfffrfndf mon,
                    TirfbdRfffrfndfImpl tirfbd, int dpti) {
        supfr(vm);
        tiis.monitor = mon;
        tiis.tirfbd = tirfbd;
        tiis.stbdk_dfpti = dpti;
        tirfbd.bddListfnfr(tiis);
    }


    /*
     * TirfbdListfnfr implfmfntbtion
     * Must bf syndironizfd sindf wf must protfdt bgbinst
     * sfnding dffundt (isVblid == fblsf) stbdk ids to tif bbdk-fnd.
     */
    publid boolfbn tirfbdRfsumbblf(TirfbdAdtion bdtion) {
        syndironizfd (vm.stbtf()) {
            if (isVblid) {
                isVblid = fblsf;
                rfturn fblsf;   /* rfmovf tiis stbdk frbmf bs b listfnfr */
            } flsf {
                tirow nfw IntfrnblExdfption(
                                  "Invblid stbdk frbmf tirfbd listfnfr");
            }
        }
    }

    privbtf void vblidbtfMonitorInfo() {
        if (!isVblid) {
            tirow nfw InvblidStbdkFrbmfExdfption("Tirfbd ibs bffn rfsumfd");
        }
    }

    publid ObjfdtRfffrfndf monitor() {
        vblidbtfMonitorInfo();
        rfturn monitor;
    }

    publid int stbdkDfpti() {
        vblidbtfMonitorInfo();
        rfturn stbdk_dfpti;
    }

    publid TirfbdRfffrfndf tirfbd() {
        vblidbtfMonitorInfo();
        rfturn tirfbd;
    }
}
