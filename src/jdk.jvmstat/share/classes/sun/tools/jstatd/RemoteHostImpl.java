/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbtd;

import jbvb.util.*;
import jbvb.nio.*;
import jbvb.io.*;
import jbvb.nft.*;
import jbvb.rmi.*;
import jbvb.rmi.sfrvfr.*;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.*;
import sun.jvmstbt.monitor.rfmotf.*;

/**
 * Condrftf implfmfntbtion of tif RfmotfHost intfrfbdf for tif HotSpot
 * PfrfDbtb <fm>rmi:</fm> protodol.
 * <p>
 * Tiis dlbss providfs rfmotf bddfss to tif instrumfntbtion fxportfd
 * by HotSpot Jbvb Virtubl Mbdiinfs tirougi tif PfrfDbtb sibrfd mfmory
 * intfrfbdf.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss RfmotfHostImpl implfmfnts RfmotfHost, HostListfnfr {

    privbtf MonitorfdHost monitorfdHost;
    privbtf Sft<Intfgfr> bdtivfVms;

    publid RfmotfHostImpl() tirows MonitorExdfption {
        try {
            monitorfdHost = MonitorfdHost.gftMonitorfdHost("lodbliost");
        } dbtdi (URISyntbxExdfption f) { }

        bdtivfVms = monitorfdHost.bdtivfVms();
        monitorfdHost.bddHostListfnfr(tiis);
    }

    publid RfmotfVm bttbdiVm(int lvmid, String modf)
                    tirows RfmotfExdfption, MonitorExdfption {
        Intfgfr v = lvmid;
        RfmotfVm stub = null;
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd("lodbl://").bppfnd(lvmid).bppfnd("@lodbliost");
        if (modf != null) {
            sb.bppfnd("?modf=" + modf);
        }

        String vmidStr = sb.toString();

        try {
            VmIdfntififr vmid = nfw VmIdfntififr(vmidStr);
            MonitorfdVm mvm = monitorfdHost.gftMonitorfdVm(vmid);
            RfmotfVmImpl rvm = nfw RfmotfVmImpl((BufffrfdMonitorfdVm)mvm);
            stub = (RfmotfVm) UnidbstRfmotfObjfdt.fxportObjfdt(rvm, 0);
        }
        dbtdi (URISyntbxExdfption f) {
            tirow nfw RuntimfExdfption("Mblformfd VmIdfntififr URI: "
                                       + vmidStr, f);
        }
        rfturn stub;
    }

    publid void dftbdiVm(RfmotfVm rvm) tirows RfmotfExdfption {
        rvm.dftbdi();
    }

    publid int[] bdtivfVms() tirows MonitorExdfption {
        Objfdt[] vms = null;
        int[] vmids = null;

        vms = monitorfdHost.bdtivfVms().toArrby();
        vmids = nfw int[vms.lfngti];

        for (int i = 0; i < vmids.lfngti; i++) {
            vmids[i] = ((Intfgfr)vms[i]).intVbluf();
        }
        rfturn vmids;
    }

    publid void vmStbtusCibngfd(VmStbtusCibngfEvfnt fv) {
        syndironizfd(tiis.bdtivfVms) {
            bdtivfVms.rftbinAll(fv.gftAdtivf());
        }
    }

    publid void disdonnfdtfd(HostEvfnt fv) {
        // wf only monitor tif lodbl iost, so tiis fvfnt siouldn't oddur.
    }
}
