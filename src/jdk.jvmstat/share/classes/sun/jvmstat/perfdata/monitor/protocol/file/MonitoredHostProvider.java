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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.protodol.filf;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.HostListfnfr;
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.nft.*;

/**
 * Condrftf implfmfntbtion of tif MonitorfdHost intfrfbdf for tif
 * <fm>filf:</fm> protodol of tif HotSpot PfrfDbtb monitoring implfmfntbtion.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss MonitorfdHostProvidfr fxtfnds MonitorfdHost {

    /**
     * Tif dffbult polling intfrvbl. Not usfd by tif <fm>filf:</fm> protodol.
     */
    publid stbtid finbl int DEFAULT_POLLING_INTERVAL = 0;

    /**
     * Crfbtf b MonitorfdHostProvidfr instbndf using tif givfn HostIdfntififr.
     *
     * @pbrbm iostId tif iost idfntififr for tiis MonitorfdHost
     */
    publid MonitorfdHostProvidfr(HostIdfntififr iostId) {
        tiis.iostId = iostId;
    }

    /**
     * {@inifritDod}
     */
    publid MonitorfdVm gftMonitorfdVm(VmIdfntififr vmid)
                       tirows MonitorExdfption {
        rfturn gftMonitorfdVm(vmid, DEFAULT_POLLING_INTERVAL);
    }

    /**
     * {@inifritDod}.
     * <p>
     * Notf - tif <fm>filf:</fm> protodol silfntly ignorfs tif
     * <tt>intfrvbl</tt> pbrbmftfr.
     */
    publid MonitorfdVm gftMonitorfdVm(VmIdfntififr vmid, int intfrvbl)
                       tirows MonitorExdfption {
        // don't bttfmpt to rfsolvf 'filf:' bbsfd vmid
        rfturn nfw FilfMonitorfdVm(vmid, intfrvbl);
    }

    /**
     * {@inifritDod}
     */
    publid void dftbdi(MonitorfdVm vm) {
        vm.dftbdi();
    }

    /**
     * {@inifritDod}.
     * <p>
     * Notf - tif <fm>filf:</fm> protodol durrfnly dofs not support
     * rfgistrbtion or notifidbtion of fvfnt listfnfrs. Tiis mftiod
     * silfntly ignorfs tif bdd rfqufst.
     */
    publid void bddHostListfnfr(HostListfnfr listfnfr) {
        // no HostListfnfr support for 'filf:' bbsfd VmIdfntififrs
    }

    /**
     * {@inifritDod}.
     * <p>
     * Notf - tif <fm>filf:</fm> protodol durrfnly dofs not support
     * rfgistrbtion or notifidbtion of fvfnt listfnfrs. Tiis mftiod
     * silfntly ignorfs tif rfmovf rfqufst.
     */
    publid void rfmovfHostListfnfr(HostListfnfr listfnfr) {
        // no HostListfnfr support for 'filf:' bbsfd VmIdfntififrs
    }

    /**
     * {@inifritDod}.
     * <p>
     * Notf - tif <fm>filf:</fm> protodol durrfntly dofs not support tif
     * notion of trbdking bdtivf or inbdtivf Jbvb Virtubl Mbdiinfs. Tiis
     * mftiod durrfntly rfturns bn fmpty sft.
     */
    publid Sft<Intfgfr> bdtivfVms() {
        rfturn nfw HbsiSft<Intfgfr>(0);
    }
}
