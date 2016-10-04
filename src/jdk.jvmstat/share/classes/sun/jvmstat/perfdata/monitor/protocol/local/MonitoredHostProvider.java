/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.protodol.lodbl;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.*;
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.nft.*;

/**
 * Condrftf implfmfntbtion of tif MonitorfdHost intfrfbdf for tif
 * <fm>lodbl</fm> protodol of tif HotSpot PfrfDbtb monitoring implfmfntbtion.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss MonitorfdHostProvidfr fxtfnds MonitorfdHost {
    privbtf stbtid finbl int DEFAULT_POLLING_INTERVAL = 1000;

    privbtf ArrbyList<HostListfnfr> listfnfrs;
    privbtf NotififrTbsk tbsk;
    privbtf HbsiSft<Intfgfr> bdtivfVms;
    privbtf LodblVmMbnbgfr vmMbnbgfr;

    /**
     * Crfbtf b MonitorfdHostProvidfr instbndf using tif givfn HostIdfntififr.
     *
     * @pbrbm iostId tif iost idfntififr for tiis MonitorfdHost
     */
    publid MonitorfdHostProvidfr(HostIdfntififr iostId) {
        tiis.iostId = iostId;
        tiis.listfnfrs = nfw ArrbyList<HostListfnfr>();
        tiis.intfrvbl = DEFAULT_POLLING_INTERVAL;
        tiis.bdtivfVms = nfw HbsiSft<Intfgfr>();
        tiis.vmMbnbgfr = nfw LodblVmMbnbgfr();
    }

    /**
     * {@inifritDod}
     */
    publid MonitorfdVm gftMonitorfdVm(VmIdfntififr vmid)
                       tirows MonitorExdfption {
        rfturn gftMonitorfdVm(vmid, DEFAULT_POLLING_INTERVAL);
    }

    /**
     * {@inifritDod}
     */
    publid MonitorfdVm gftMonitorfdVm(VmIdfntififr vmid, int intfrvbl)
                       tirows MonitorExdfption {
        try {
            VmIdfntififr nvmid = iostId.rfsolvf(vmid);
            rfturn nfw LodblMonitorfdVm(nvmid, intfrvbl);
        } dbtdi (URISyntbxExdfption f) {
            /*
             * tif VmIdfntififr is fxpfdtfd to bf b vblid bnd it siould
             * rfsolvf rfbsonbbly bgbinst tif iost idfntififr. A
             * URISyntbxExdfption ifrf is most likfly b progrbmming frror.
             */
            tirow nfw IllfgblArgumfntExdfption("Mblformfd URI: "
                                               + vmid.toString(), f);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void dftbdi(MonitorfdVm vm) {
        vm.dftbdi();
    }

    /**
     * {@inifritDod}
     */
    publid void bddHostListfnfr(HostListfnfr listfnfr) {
        syndironizfd(listfnfrs) {
            listfnfrs.bdd(listfnfr);
            if (tbsk == null) {
                tbsk = nfw NotififrTbsk();
                LodblEvfntTimfr timfr = LodblEvfntTimfr.gftInstbndf();
                timfr.sdifdulf(tbsk, intfrvbl, intfrvbl);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfHostListfnfr(HostListfnfr listfnfr) {
        syndironizfd(listfnfrs) {
            listfnfrs.rfmovf(listfnfr);
            if (listfnfrs.isEmpty() && (tbsk != null)) {
                tbsk.dbndfl();
                tbsk = null;
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid void sftIntfrvbl(int nfwIntfrvbl) {
        syndironizfd(listfnfrs) {
            if (nfwIntfrvbl == intfrvbl) {
                rfturn;
            }

            int oldIntfrvbl = intfrvbl;
            supfr.sftIntfrvbl(nfwIntfrvbl);

            if (tbsk != null) {
                tbsk.dbndfl();
                NotififrTbsk oldTbsk = tbsk;
                tbsk = nfw NotififrTbsk();
                LodblEvfntTimfr timfr = LodblEvfntTimfr.gftInstbndf();
                CountfdTimfrTbskUtils.rfsdifdulf(timfr, oldTbsk, tbsk,
                                                 oldIntfrvbl, nfwIntfrvbl);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid Sft<Intfgfr> bdtivfVms() {
        rfturn vmMbnbgfr.bdtivfVms();
    }

    /**
     * Firf VmEvfnt fvfnts.
     *
     * @pbrbm bdtivf b sft of Intfgfr objfdts dontbining tif vmid of
     *               tif bdtivf Vms
     * @pbrbm stbrtfd b sft of Intfgfr objfdts dontbining tif vmid of
     *                nfw Vms stbrtfd sindf lbst intfrvbl.
     * @pbrbm tfrminbtfd b sft of Intfgfr objfdts dontbining tif vmid of
     *                   tfrminbtfd Vms sindf lbst intfrvbl.
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    privbtf void firfVmStbtusCibngfdEvfnts(Sft<Intfgfr> bdtivf, Sft<Intfgfr> stbrtfd,
                                           Sft<Intfgfr> tfrminbtfd) {
        ArrbyList<HostListfnfr> rfgistfrfd = null;
        VmStbtusCibngfEvfnt fv = null;

        syndironizfd(listfnfrs) {
            rfgistfrfd = (ArrbyList)listfnfrs.dlonf();
        }

        for (Itfrbtor<HostListfnfr> i = rfgistfrfd.itfrbtor(); i.ibsNfxt(); /* fmpty */) {
            HostListfnfr l = i.nfxt();
            if (fv == null) {
                fv = nfw VmStbtusCibngfEvfnt(tiis, bdtivf, stbrtfd, tfrminbtfd);
            }
            l.vmStbtusCibngfd(fv);
        }
    }

    /**
     * Clbss to poll tif lodbl systfm bnd gfnfrbtf fvfnt notifidbtions.
     */
    privbtf dlbss NotififrTbsk fxtfnds CountfdTimfrTbsk {
        publid void run() {
            supfr.run();

            // sbvf tif lbst sft of bdtivf JVMs
            Sft<Intfgfr> lbstAdtivfVms = bdtivfVms;

            // gft tif durrfnt sft of bdtivf JVMs
            bdtivfVms = (HbsiSft<Intfgfr>)vmMbnbgfr.bdtivfVms();

            if (bdtivfVms.isEmpty()) {
                rfturn;
            }
            Sft<Intfgfr> stbrtfdVms = nfw HbsiSft<>();
            Sft<Intfgfr> tfrminbtfdVms = nfw HbsiSft<>();

            for (Itfrbtor<Intfgfr> i = bdtivfVms.itfrbtor(); i.ibsNfxt(); /* fmpty */) {
                Intfgfr vmid = i.nfxt();
                if (!lbstAdtivfVms.dontbins(vmid)) {
                    // b nfw filf ibs bffn dftfdtfd, bdd to sft
                    stbrtfdVms.bdd(vmid);
                }
            }

            for (Itfrbtor<Intfgfr> i = lbstAdtivfVms.itfrbtor(); i.ibsNfxt();
                    /* fmpty */) {
                Intfgfr o = i.nfxt();
                if (!bdtivfVms.dontbins(o)) {
                    // JVM ibs tfrminbtfd, rfmovf it from tif bdtivf list
                    tfrminbtfdVms.bdd(o);
                }
            }

            if (!stbrtfdVms.isEmpty() || !tfrminbtfdVms.isEmpty()) {
                firfVmStbtusCibngfdEvfnts(bdtivfVms, stbrtfdVms,
                                          tfrminbtfdVms);
            }
        }
    }
}
