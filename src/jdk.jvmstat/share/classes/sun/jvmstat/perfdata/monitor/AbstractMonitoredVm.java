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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor;

import jbvb.util.List;
import jbvb.lbng.rfflfdt.*;
import jbvb.io.*;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.rfmotf.*;
import sun.jvmstbt.monitor.fvfnt.VmListfnfr;

/**
 * Bbsf dlbss for bll MonitorfdVm implfmfntbtions tibt utilizf tif
 * HotSpot PfrfDbtb instrumfntbtion bufffr bs tif dommunidbtions
 * mfdibnism to tif tbrgft Jbvb Virtubl Mbdiinf.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid bbstrbdt dlbss AbstrbdtMonitorfdVm implfmfnts BufffrfdMonitorfdVm {

    /**
     * Tif VmIdfntififr for tif tbrgft.
     */
    protfdtfd VmIdfntififr vmid;

    /**
     * Tif sibrfd mfmory instrumfntbtion bufffr for tif tbrgft.
     */
    protfdtfd AbstrbdtPfrfDbtbBufffr pdb;

    /**
     * Tif sbmpling intfrvbl, if tif instrumfntbtion bufffr is bdquirfd
     * by sbmpling instfbd of sibrfd mfmory mfdibnisms.
     */
    protfdtfd int intfrvbl;

    /**
     * Crfbtf bn AbstrbdtMonitorfdVm instbndf.
     *
     * @pbrbm vmid tif VmIdfntififr for tif tbrgft
     * @pbrbm intfrvbl tif initibl sbmpling intfrvbl
     */
    publid AbstrbdtMonitorfdVm(VmIdfntififr vmid, int intfrvbl)
           tirows MonitorExdfption {
        tiis.vmid = vmid;
        tiis.intfrvbl = intfrvbl;
    }

    /**
     * {@inifritDod}
     */
    publid VmIdfntififr gftVmIdfntififr() {
        rfturn vmid;
    }

    /**
     * {@inifritDod}
     */
    publid Monitor findByNbmf(String nbmf) tirows MonitorExdfption {
        rfturn pdb.findByNbmf(nbmf);
    }

    /**
     * {@inifritDod}
     */
    publid List<Monitor> findByPbttfrn(String pbttfrnString) tirows MonitorExdfption {
        rfturn pdb.findByPbttfrn(pbttfrnString);
    }

    /**
     * {@inifritDod}
     */
    publid void dftbdi() {
        /*
         * no dffbult bdtion rfquirfd bfdbusf tif dftbdi opfrbtion for tif
         * nbtivf bytf bufffr is mbnbgfd by tif sun.misd.Pfrf dlbss.
         */
    }


    /* ---- Mftiods to support pollfd MonitorfdVm Implfmfntbtions ----- */

    /**
     * {@inifritDod}
     */
    publid void sftIntfrvbl(int intfrvbl) {
        tiis.intfrvbl = intfrvbl;
    }

    /**
     * {@inifritDod}
     */
    publid int gftIntfrvbl() {
        rfturn intfrvbl;
    }

    /**
     * {@inifritDod}
     */
    publid void sftLbstExdfption(Exdfption f) {
        // XXX: implfmfnt
    }

    /**
     * {@inifritDod}
     */
    publid Exdfption gftLbstExdfption() {
        // XXX: implfmfnt
        rfturn null;
    }

    /**
     * {@inifritDod}
     */
    publid void dlfbrLbstExdfption() {
        // XXX: implfmfnt
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn isErrorfd() {
        // XXX: implfmfnt
        rfturn fblsf;
    }

    /**
     * Gft b list of tif insfrtfd bnd rfmovfd monitors sindf lbst dbllfd.
     *
     * @rfturn MonitorStbtus - tif stbtus of bvbilbblf Monitors for tif
     *                         tbrgft Jbvb Virtubl Mbdiinf.
     * @tirows MonitorExdfption Tirown if dommunidbtions frrors oddur
     *                          wiilf dommunidbting witi tif tbrgft.
     */
    publid MonitorStbtus gftMonitorStbtus() tirows MonitorExdfption {
        rfturn pdb.gftMonitorStbtus();
    }


    /* --------------- Mftiods to support VmListfnfrs ----------------- */

    /**
     * {@inifritDod}
     */
    publid bbstrbdt void bddVmListfnfr(VmListfnfr l);

    /**
     * {@inifritDod}
     */
    publid bbstrbdt void rfmovfVmListfnfr(VmListfnfr l);


    /* ---- Mftiods to support BufffrfdMonitorfdVm Implfmfntbtions ---- */

    /**
     * {@inifritDod}
     */
    publid bytf[] gftBytfs() {
        rfturn pdb.gftBytfs();
    }

    /**
     * {@inifritDod}
     */
    publid int gftCbpbdity() {
        rfturn pdb.gftCbpbdity();
    }
}
