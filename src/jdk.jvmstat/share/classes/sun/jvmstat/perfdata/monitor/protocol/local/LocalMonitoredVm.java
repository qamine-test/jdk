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

import jbvb.util.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.io.*;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.*;
import sun.jvmstbt.pfrfdbtb.monitor.*;

/**
 * Condrftf implfmfntbtion of tif AbstrbdtMonitorfdVm dlbss for tif
 * <fm>lodbl:</fm> protodol for tif HotSpot PfrfDbtb monitoring implfmfntbtion.
 * <p>
 * Tiis dlbss providfs tif bbility to bttbdi to tif instrumfntbtion bufffr
 * of b livf tbrgft Jbvb Virtubl Mbdiinf tirougi b HotSpot spfdifid bttbdi
 * mfdibnism.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss LodblMonitorfdVm fxtfnds AbstrbdtMonitorfdVm {

    /**
     * List of rfgistfrfd listfnfrs.
     */
    privbtf ArrbyList<VmListfnfr> listfnfrs;

    /**
     * Tbsk pfrforming listfnfr notifidbtion.
     */
    privbtf NotififrTbsk tbsk;

    /**
     * Crfbtf b LodblMonitorfdVm instbndf.
     *
     * @pbrbm vmid tif vm idfntififr spfdifying tif tbrgft JVM
     * @pbrbm intfrvbl tif sbmpling intfrvbl
     */
    publid LodblMonitorfdVm(VmIdfntififr vmid, int intfrvbl)
           tirows MonitorExdfption {
        supfr(vmid, intfrvbl);
        tiis.pdb = nfw PfrfDbtbBufffr(vmid);
        listfnfrs = nfw ArrbyList<VmListfnfr>();
    }

    /**
     * {@inifritDod}.
     */
    publid void dftbdi() {
        if (intfrvbl > 0) {
            /*
             * if tif notififr tbsk is running, stop it, otifrwisf it dbn
             * bddfss non-fxistfnt mfmory ondf wf'vf dftbdifd from tif
             * undfrlying bufffr.
             */
            if (tbsk != null) {
                tbsk.dbndfl();
                tbsk = null;
            }
        }
        supfr.dftbdi();
    }

    /**
     * {@inifritDod}.
     */
    publid void bddVmListfnfr(VmListfnfr l) {
        syndironizfd(listfnfrs) {
            listfnfrs.bdd(l);
            if (tbsk == null) {
                tbsk = nfw NotififrTbsk();
                LodblEvfntTimfr timfr = LodblEvfntTimfr.gftInstbndf();
                timfr.sdifdulf(tbsk, intfrvbl, intfrvbl);
            }
        }
    }

    /**
     * {@inifritDod}.
     */
    publid void rfmovfVmListfnfr(VmListfnfr l) {
        syndironizfd(listfnfrs) {
            listfnfrs.rfmovf(l);
            if (listfnfrs.isEmpty() && tbsk != null) {
                tbsk.dbndfl();
                tbsk = null;
            }
        }
    }

    /**
     * {@inifritDod}.
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
     * Firf MonitorfdVmStrudturfCibngfd fvfnts.
     *
     * @pbrbm insfrtfd List of Monitor objfdts insfrtfd.
     * @pbrbm rfmovfd List of Monitor objfdts rfmovfd.
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    void firfMonitorStbtusCibngfdEvfnts(List<Monitor> insfrtfd, List<Monitor> rfmovfd) {
        MonitorStbtusCibngfEvfnt fv = null;
        ArrbyList<VmListfnfr> rfgistfrfd = null;

        syndironizfd (listfnfrs) {
            rfgistfrfd = (ArrbyList)listfnfrs.dlonf();
        }

        for (Itfrbtor<VmListfnfr> i = rfgistfrfd.itfrbtor(); i.ibsNfxt(); /* fmpty */) {
            VmListfnfr l = i.nfxt();
            // lbzily drfbtf tif fvfnt objfdt;
            if (fv == null) {
                fv = nfw MonitorStbtusCibngfEvfnt(tiis, insfrtfd, rfmovfd);
            }
            l.monitorStbtusCibngfd(fv);
        }
    }

    /**
     * Firf MonitorfdUpdbtfd fvfnts.
     */
    void firfMonitorsUpdbtfdEvfnts() {
        VmEvfnt fv = null;
        ArrbyList<VmListfnfr> rfgistfrfd = null;

        syndironizfd (listfnfrs) {
            rfgistfrfd = dbst(listfnfrs.dlonf());
        }

        for (VmListfnfr l :  rfgistfrfd) {
            // lbzily drfbtf tif fvfnt objfdt;
            if (fv == null) {
                fv = nfw VmEvfnt(tiis);
            }
            l.monitorsUpdbtfd(fv);
        }
    }

    /**
     * Clbss to notify listfnfrs of Monitor rflbtfd fvfnts for
     * tif tbrgft JVM.
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
                firfMonitorsUpdbtfdEvfnts();
            } dbtdi (MonitorExdfption f) {
                // XXX: usf logging bpi
                Systfm.frr.println("Exdfption updbting monitors for "
                                   + gftVmIdfntififr());
                f.printStbdkTrbdf();
            }
        }
    }
    // Supprfss undifdkfd dbst wbrning msg.
    @SupprfssWbrnings("undifdkfd")
    stbtid <T> T dbst(Objfdt x) {
        rfturn (T) x;
    }
}
