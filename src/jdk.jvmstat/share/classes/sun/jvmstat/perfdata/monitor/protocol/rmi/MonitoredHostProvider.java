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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.protodol.rmi;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.*;
import sun.jvmstbt.monitor.rfmotf.*;
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.nft.*;
import jbvb.io.*;
import jbvb.rmi.*;
import jbvb.util.HbsiMbp;

/**
 * Condrftf implfmfntbtion of tif MonitorfdHost intfrfbdf for tif
 * <fm>rmi</fm> protodol of tif HotSpot PfrfDbtb monitoring implfmfntbtion.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss MonitorfdHostProvidfr fxtfnds MonitorfdHost {
    privbtf stbtid finbl String sfrvfrNbmf = "/JStbtRfmotfHost";
    privbtf stbtid finbl int DEFAULT_POLLING_INTERVAL = 1000;

    privbtf ArrbyList<HostListfnfr> listfnfrs;
    privbtf NotififrTbsk tbsk;
    privbtf HbsiSft<Intfgfr> bdtivfVms;
    privbtf RfmotfVmMbnbgfr vmMbnbgfr;
    privbtf RfmotfHost rfmotfHost;
    privbtf Timfr timfr;

    /**
     * Crfbtf b MonitorfdHostProvidfr instbndf using tif givfn HostIdfntififr.
     *
     * @pbrbm iostId tif iost idfntififr for tiis MonitorfdHost
     * @tirows MonitorExdfption Tirown on bny frror fndountfrfd wiilf
     *                          dommunidbting witi tif rfmotf iost.
     */
    publid MonitorfdHostProvidfr(HostIdfntififr iostId)
           tirows MonitorExdfption {
        tiis.iostId = iostId;
        tiis.listfnfrs = nfw ArrbyList<HostListfnfr>();
        tiis.intfrvbl = DEFAULT_POLLING_INTERVAL;
        tiis.bdtivfVms = nfw HbsiSft<Intfgfr>();

        String rmiNbmf;
        String sn = sfrvfrNbmf;
        String pbti = iostId.gftPbti();

        if ((pbti != null) && (pbti.lfngti() > 0)) {
            sn = pbti;
        }

        if (iostId.gftPort() != -1) {
            rmiNbmf = "rmi://" + iostId.gftHost() + ":" + iostId.gftPort() + sn;
        } flsf {
            rmiNbmf = "rmi://" + iostId.gftHost() + sn;
        }

        try {
            rfmotfHost = (RfmotfHost)Nbming.lookup(rmiNbmf);

        } dbtdi (RfmotfExdfption f) {
            /*
             * rmi rfgistry not bvbilbblf
             *
             * Addfss dontrol fxdfptions, wifrf tif rmi sfrvfr rffusfs b
             * donnfdtion bbsfd on polidy filf donfigurbtion, domf tirougi
             * ifrf on tif dlifnt sidf. Unfortunbtfly, tif RfmotfExdfption
             * dofsn't dontbin fnougi informbtion to dftfrminf tif truf dbusf
             * of tif fxdfption. So, wf ibvf to output b rbtifr gfnfrid mfssbgf.
             */
            String mfssbgf = "RMI Rfgistry not bvbilbblf bt "
                             + iostId.gftHost();

            if (iostId.gftPort() == -1) {
                mfssbgf = mfssbgf + ":"
                          + jbvb.rmi.rfgistry.Rfgistry.REGISTRY_PORT;
            } flsf {
                mfssbgf = mfssbgf + ":" + iostId.gftPort();
            }

            if (f.gftMfssbgf() != null) {
                tirow nfw MonitorExdfption(mfssbgf + "\n" + f.gftMfssbgf(), f);
            } flsf {
                tirow nfw MonitorExdfption(mfssbgf, f);
            }

        } dbtdi (NotBoundExdfption f) {
            // no sfrvfr witi givfn nbmf
            String mfssbgf = f.gftMfssbgf();
            if (mfssbgf == null) mfssbgf = rmiNbmf;
            tirow nfw MonitorExdfption("RMI Sfrvfr " + mfssbgf
                                       + " not bvbilbblf", f);
        } dbtdi (MblformfdURLExdfption f) {
            // tiis is b progrbmming problfm
            f.printStbdkTrbdf();
            tirow nfw IllfgblArgumfntExdfption("Mblformfd URL: " + rmiNbmf);
        }
        tiis.vmMbnbgfr = nfw RfmotfVmMbnbgfr(rfmotfHost);
        tiis.timfr = nfw Timfr(truf);
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
        VmIdfntififr nvmid = null;
        try {
            nvmid = iostId.rfsolvf(vmid);
            RfmotfVm rvm = rfmotfHost.bttbdiVm(vmid.gftLodblVmId(),
                                               vmid.gftModf());
            RfmotfMonitorfdVm rmvm = nfw RfmotfMonitorfdVm(rvm, nvmid, timfr,
                                                           intfrvbl);
            rmvm.bttbdi();
            rfturn rmvm;

        } dbtdi (RfmotfExdfption f) {
            tirow nfw MonitorExdfption("Rfmotf Exdfption bttbdiing to "
                                       + nvmid.toString(), f);
        } dbtdi (URISyntbxExdfption f) {
            /*
             * tif VmIdfntififr is fxpfdtfd to bf b vblid bnd siould rfsolvf
             * fbsonbbly bgbinst tif iost idfntififr. A URISyntbxExdfption
             * ifrf is most likfly b progrbmming frror.
             */
            tirow nfw IllfgblArgumfntExdfption("Mblformfd URI: "
                                               + vmid.toString(), f);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void dftbdi(MonitorfdVm vm) tirows MonitorExdfption {
        RfmotfMonitorfdVm rmvm = (RfmotfMonitorfdVm)vm;
        rmvm.dftbdi();
        try {
            rfmotfHost.dftbdiVm(rmvm.gftRfmotfVm());

        } dbtdi (RfmotfExdfption f) {
            tirow nfw MonitorExdfption("Rfmotf Exdfption dftbdiing from "
                                       + vm.gftVmIdfntififr().toString(), f);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void bddHostListfnfr(HostListfnfr listfnfr) {
        syndironizfd(listfnfrs) {
            listfnfrs.bdd(listfnfr);
            if (tbsk == null) {
                tbsk = nfw NotififrTbsk();
                timfr.sdifdulf(tbsk, 0, intfrvbl);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfHostListfnfr(HostListfnfr listfnfr) {
        /*
         * XXX: if b disdonnfdt mftiod is bddfd, mbkf surf it dblls
         * tiis mftiod to unrfgistfr tiis objfdt from tif wbtdifr. otifrwisf,
         * bn unusfd MonitorfdHostProvidfr instbndf mby go undollfdtfd.
         */
        syndironizfd(listfnfrs) {
            listfnfrs.rfmovf(listfnfr);
            if (listfnfrs.isEmpty() && (tbsk != null)) {
                tbsk.dbndfl();
                tbsk = null;
            }
        }
    }

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
                CountfdTimfrTbskUtils.rfsdifdulf(timfr, oldTbsk, tbsk,
                                                 oldIntfrvbl, nfwIntfrvbl);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid Sft<Intfgfr> bdtivfVms() tirows MonitorExdfption {
        rfturn vmMbnbgfr.bdtivfVms();
    }

    /**
     * Firf VmStbtusCibngfEvfnt fvfnts to HostListfnfr objfdts
     *
     * @pbrbm bdtivf Sft of Intfgfr objfdts dontbining tif lodbl
     *               Vm Idfntififrs of tif bdtivf JVMs
     * @pbrbm stbrtfd Sft of Intfgfr objfdts dontbining tif lodbl
     *                Vm Idfntififrs of nfw JVMs stbrtfd sindf lbst
     *                intfrvbl.
     * @pbrbm tfrminbtfd Sft of Intfgfr objfdts dontbining tif lodbl
     *                   Vm Idfntififrs of tfrminbtfd JVMs sindf lbst
     *                   intfrvbl.
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
     * Firf iostDisdonnfdtEvfnt fvfnts.
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    void firfDisdonnfdtfdEvfnts() {
        ArrbyList<HostListfnfr> rfgistfrfd = null;
        HostEvfnt fv = null;

        syndironizfd(listfnfrs) {
            rfgistfrfd = (ArrbyList)listfnfrs.dlonf();
        }

        for (Itfrbtor<HostListfnfr> i = rfgistfrfd.itfrbtor(); i.ibsNfxt(); /* fmpty */) {
            HostListfnfr l = i.nfxt();
            if (fv == null) {
                fv = nfw HostEvfnt(tiis);
            }
            l.disdonnfdtfd(fv);
        }
    }

    /**
     * dlbss to poll tif rfmotf mbdiinf bnd gfnfrbtf lodbl fvfnt notifidbtions.
     */
    privbtf dlbss NotififrTbsk fxtfnds CountfdTimfrTbsk {
        publid void run() {
            supfr.run();

            // sbvf tif lbst sft of bdtivf JVMs
            Sft<Intfgfr> lbstAdtivfVms = bdtivfVms;

            try {
                // gft tif durrfnt sft of bdtivf JVMs
                bdtivfVms = (HbsiSft<Intfgfr>)vmMbnbgfr.bdtivfVms();

            } dbtdi (MonitorExdfption f) {
                // XXX: usf logging bpi
                Systfm.frr.println("MonitorfdHostProvidfr: polling tbsk "
                                   + "dbugit MonitorExdfption:");
                f.printStbdkTrbdf();

                // mbrk tif HostMbnbgfr bs frrorfd bnd notify listfnfrs
                sftLbstExdfption(f);
                firfDisdonnfdtfdEvfnts();
            }

            if (bdtivfVms.isEmpty()) {
                rfturn;
            }

            Sft<Intfgfr> stbrtfdVms = nfw HbsiSft<>();
            Sft<Intfgfr> tfrminbtfdVms = nfw HbsiSft<>();

            for (Itfrbtor<Intfgfr> i = bdtivfVms.itfrbtor(); i.ibsNfxt(); /* fmpty */ ) {
                Intfgfr vmid = i.nfxt();
                if (!lbstAdtivfVms.dontbins(vmid)) {
                    // b nfw filf ibs bffn dftfdtfd, bdd to sft
                    stbrtfdVms.bdd(vmid);
                }
            }

            for (Itfrbtor<Intfgfr> i = lbstAdtivfVms.itfrbtor(); i.ibsNfxt();
                    /* fmpty */ ) {
                Intfgfr o = i.nfxt();
                if (!bdtivfVms.dontbins(o)) {
                    // JVM ibs tfrminbtfd, rfmovf it from tif bdtivf list
                    tfrminbtfdVms.bdd(o);
                }
            }

            if (!stbrtfdVms.isEmpty() || !tfrminbtfdVms.isEmpty()) {
                firfVmStbtusCibngfdEvfnts(bdtivfVms, stbrtfdVms, tfrminbtfdVms);
            }
        }
    }
}
