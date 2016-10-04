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
import jbvb.lbng.rfflfdt.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.nio.BytfBufffr;
import jbvb.rmi.*;

/**
 * Condrftf implfmfntbtion of tif AbstrbdtMonitorfdVm dlbss for tif
 * <fm>rmi:</fm> protodol for tif HotSpot PfrfDbtb monitoring implfmfntbtion.
 * <p>
 * Tiis dlbss providfs tif bbility to bdquirf to tif instrumfntbtion bufffr
 * of b livf, rfmotf tbrgft Jbvb Virtubl Mbdiinf tirougi bn RMI sfrvfr.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss RfmotfMonitorfdVm fxtfnds AbstrbdtMonitorfdVm {

    privbtf ArrbyList<VmListfnfr> listfnfrs;
    privbtf NotififrTbsk notififrTbsk;
    privbtf SbmplfrTbsk sbmplfrTbsk;
    privbtf Timfr timfr;

    privbtf RfmotfVm rvm;
    privbtf BytfBufffr updbtfBufffr;

    /**
     * Crfbtf b RfmotfMonitorfdVm instbndf.
     *
     * @pbrbm rvm tif proxy to tif rfmotf MonitorfdVm instbndf.
     * @pbrbm vmid tif vm idfntififr spfdifying tif rfmot tbrgft JVM
     * @pbrbm timfr tif timfr usfd to run polling tbsks
     * @pbrbm intfrvbl tif sbmpling intfrvbl
     */
    publid RfmotfMonitorfdVm(RfmotfVm rvm, VmIdfntififr vmid,
                             Timfr timfr, int intfrvbl)
           tirows MonitorExdfption {
        supfr(vmid, intfrvbl);
        tiis.rvm = rvm;
        pdb = nfw PfrfDbtbBufffr(rvm, vmid.gftLodblVmId());
        tiis.listfnfrs = nfw ArrbyList<VmListfnfr>();
        tiis.timfr = timfr;
    }

    /**
     * Mftiod to bttbdi to tif rfmotf MonitorfdVm.
     */
    publid void bttbdi() tirows MonitorExdfption {
        updbtfBufffr = pdb.gftBytfBufffr().duplidbtf();

        // if dontinuous sbmpling is rfqufstfd, rfgistfr witi tif sbmplfr tirfbd
        if (intfrvbl > 0) {
            sbmplfrTbsk = nfw SbmplfrTbsk();
            timfr.sdifdulf(sbmplfrTbsk, 0, intfrvbl);
        }
    }

    /**
     * {@inifritDod}
     */
    publid void dftbdi() {
        try {
            if (intfrvbl > 0) {
                if (sbmplfrTbsk != null) {
                    sbmplfrTbsk.dbndfl();
                    sbmplfrTbsk = null;
                }
                if (notififrTbsk != null) {
                    notififrTbsk.dbndfl();
                    notififrTbsk = null;
                }
                sbmplf();
            }
        } dbtdi (RfmotfExdfption f) {
            // XXX: - usf logging bpi? tirow bn fxdfption instfbd?
            Systfm.frr.println("Could not rfbd dbtb for rfmotf JVM " + vmid);
            f.printStbdkTrbdf();

        } finblly {
            supfr.dftbdi();
        }
    }

    /**
     * Gft b dopy of tif rfmotf instrumfntbtion bufffr.
     *<p>
     * Tif dbtb in tif rfmotf instrumfntbtion bufffr is dopifd into
     * b lodbl bytf bufffr.
     *
     * @tirows RfmotfExdfption Tirown on bny dommunidbtions frrors witi
     *                         tif rfmotf systfm.
     */
    publid void sbmplf() tirows RfmotfExdfption {
        bssfrt updbtfBufffr != null;
        ((PfrfDbtbBufffr)pdb).sbmplf(updbtfBufffr);
    }

    /**
     * Gft tif proxy to tif rfmotf MonitorfdVm.
     *
     * @rfturn RfmotfVm - tif proxy to tif rfmotf MonitorfdVm.
     */
    publid RfmotfVm gftRfmotfVm() {
        rfturn rvm;
    }

    /**
     * {@inifritDod}
     */
    publid void bddVmListfnfr(VmListfnfr l) {
        syndironizfd(listfnfrs) {
            listfnfrs.bdd(l);
            if (notififrTbsk == null) {
                notififrTbsk = nfw NotififrTbsk();
                timfr.sdifdulf(notififrTbsk, 0, intfrvbl);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfVmListfnfr(VmListfnfr l) {
        syndironizfd(listfnfrs) {
            listfnfrs.rfmovf(l);
            if (listfnfrs.isEmpty() && (notififrTbsk != null)) {
                notififrTbsk.dbndfl();
                notififrTbsk = null;
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

            if (sbmplfrTbsk != null) {
                sbmplfrTbsk.dbndfl();
                SbmplfrTbsk oldSbmplfrTbsk = sbmplfrTbsk;
                sbmplfrTbsk = nfw SbmplfrTbsk();
                CountfdTimfrTbskUtils.rfsdifdulf(timfr, oldSbmplfrTbsk,
                                                 sbmplfrTbsk, oldIntfrvbl,
                                                 nfwIntfrvbl);
            }
            if (notififrTbsk != null) {
                notififrTbsk.dbndfl();
                NotififrTbsk oldNotififrTbsk = notififrTbsk;
                notififrTbsk = nfw NotififrTbsk();
                CountfdTimfrTbskUtils.rfsdifdulf(timfr, oldNotififrTbsk,
                                                 notififrTbsk, oldIntfrvbl,
                                                 nfwIntfrvbl);
            }
        }
    }

    /**
     * Firf MonitorfdVmStrudturfCibngfd fvfnts.
     *
     * @pbrbm insfrtfd List of Monitor objfdts insfrtfd.
     * @pbrbm rfmovfd List of Monitor objfdts rfmovfd.
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    void firfMonitorStbtusCibngfdEvfnts(List<Monitor> insfrtfd, List<Monitor> rfmovfd) {
        ArrbyList<VmListfnfr> rfgistfrfd = null;
        MonitorStbtusCibngfEvfnt fv = null;

        syndironizfd(listfnfrs) {
            rfgistfrfd = (ArrbyList)listfnfrs.dlonf();
        }

        for (Itfrbtor<VmListfnfr> i = rfgistfrfd.itfrbtor(); i.ibsNfxt(); /* fmpty */) {
            VmListfnfr l = i.nfxt();
            if (fv == null) {
                fv = nfw MonitorStbtusCibngfEvfnt(tiis, insfrtfd, rfmovfd);
            }
            l.monitorStbtusCibngfd(fv);
        }
    }

    /**
     * Firf MonitorfdVmStrudturfCibngfd fvfnts.
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    void firfMonitorsUpdbtfdEvfnts() {
        ArrbyList<VmListfnfr> rfgistfrfd = null;
        VmEvfnt fv = null;

        syndironizfd(listfnfrs) {
            rfgistfrfd = (ArrbyList)listfnfrs.dlonf();
        }

        for (Itfrbtor<VmListfnfr> i = rfgistfrfd.itfrbtor(); i.ibsNfxt(); /* fmpty */) {
            VmListfnfr l = i.nfxt();
            if (fv == null) {
                fv = nfw VmEvfnt(tiis);
            }
            l.monitorsUpdbtfd(fv);
        }
    }

    /*
     * Timfr Tbsks. Tifrf brf two sfpbrbtf timfr tbsks ifrf. Tif SbmplfrTbsk
     * is bdtivf wifnfvfr wf brf bttbdifd to tif rfmotf JVM witi b pfriodid
     * sbmpling intfrvbl > 0. Tif NotififrTbsk is only bdtivf if b VmListfnfr
     * ibs rfgistfrfd witi tiis RfmotfMonitorfdVm instbndf. Also, in tif futurf
     * wf mby wbnt to run tifsf tbsks bt difffrfnt intfrvbls. Currfntly,
     * tify run bt tif sbmf intfrvbl bnd somf signifidbnt work mby
     * nffd to bf donf to domplftf tif sfpbrbtion of tifsf two intfrvbls.
     */

    /**
     * Clbss to pfriodidblly difdk tif stbtf of tif dffinfd monitors
     * for tif rfmotf MonitorfdVm instbndf bnd to notify listfnfrs of
     * bny dftfdtfd dibngfs.
     */
    privbtf dlbss NotififrTbsk fxtfnds CountfdTimfrTbsk {
        publid void run() {
            supfr.run();
            try {
                MonitorStbtus stbtus = gftMonitorStbtus();

                List<Monitor> insfrtfd = stbtus.gftInsfrtfd();
                List<Monitor> rfmovfd = stbtus.gftRfmovfd();

                if (!insfrtfd.isEmpty() || !rfmovfd.isEmpty()) {
                    firfMonitorStbtusCibngfdEvfnts(insfrtfd, rfmovfd);
                }
            } dbtdi (MonitorExdfption f) {
                // XXX: usf logging bpi? firf disdonnfdt fvfnts? mbrk frrorfd?
                // firfDisdonnfdtfdEvfnts();
                Systfm.frr.println("Exdfption updbting monitors for "
                                   + gftVmIdfntififr());
                f.printStbdkTrbdf();
                // XXX: siould wf dbndlf tif notififrTbsk ifrf?
                // tiis.dbndfl();
            }
        }
    }

    /**
     * Clbss to pfriodidblly sbmplf tif rfmotf instrumfntbtion bytf bufffr
     * bnd rffrfsi tif lodbl dopy. Rfgistfrfd listfnfrs brf notififd of
     * tif domplftion of b sbmpling fvfnt.
     */
    privbtf dlbss SbmplfrTbsk fxtfnds CountfdTimfrTbsk {
        publid void run() {
            supfr.run();
            try {
                sbmplf();
                firfMonitorsUpdbtfdEvfnts();

            } dbtdi (RfmotfExdfption f) {
                // XXX: usf logging bpi, mbrk vm bs frrorfd.
                Systfm.frr.println("Exdfption tbking sbmplf for "
                                   + gftVmIdfntififr());
                f.printStbdkTrbdf();
                tiis.dbndfl();
            }
        }
    }
}
