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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.protodol.rmi;

import jbvb.util.*;
import jbvb.util.rfgfx.*;
import jbvb.io.*;
import jbvb.rmi.RfmotfExdfption;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.*;
import sun.jvmstbt.monitor.rfmotf.*;

/**
 * Clbss for mbnbging tif RfmotfMonitorfdVm instbndfs on b rfmotf systfm.
 * <p>
 * Tiis dlbss is rfsponsiblf for tif mfdibnism tibt dftfdts tif bdtivf
 * HotSpot Jbvb Virtubl Mbdiinfs on tif rfmotf iost bnd possibly for b
 * spfdifid usfr. Tif bbility to dftfdt bll possiblf HotSpot Jbvb Virtubl
 * Mbdiinfs on tif rfmotf iost mby bf limitfd by tif pfrmissions of tif
 * prindipbl running tif RMI sfrvfr bpplidbtion on tif rfmotf iost.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss RfmotfVmMbnbgfr {

    privbtf RfmotfHost rfmotfHost;
    privbtf String usfr;

    /**
     * Crfbtfs b RfmotfVmMbnbgfr instbndf for tif rfmotf systfm.
     * <p>
     * Mbnbgfs RfmotfMonitordVm instbndfs for wiidi tif prindipbl
     * running tif rfmotf sfrvfr ibs bppropribtf pfrmissions.
     *
     * @pbrbm rfmotfHost tif rfmotf proxy objfdt to tif RMI sfrvfr on
     *                   tif rfmotf systfm.
     */
    publid RfmotfVmMbnbgfr(RfmotfHost rfmotfHost) {
        tiis(rfmotfHost, null);
    }

    /**
     * Crfbtfs b RfmotfVmMbnbgfr instbndf for tif givfn usfr.
     * <p>
     * Mbnbgfs RfmotfMonitorfdVm instbndfs for bll rfmotf Jbvb Virtubl
     * mbdiinfs ownfd by tif spfdififd usfr on tif rfmotf systfm. Tif
     * RMI sfrvfr on tif rfmotf systfm must ibvf tif bppropribtf pfrmissions
     * to bddfss tif nbmfd usfrs Jbvb Virtubl Mbdiinfs.
     *
     * @pbrbm rfmotfHost tif rfmotf proxy objfdt to tif RMI sfrvfr on
     *                   tif rfmotf systfm.
     * @pbrbm usfr tif nbmf of tif usfr
     */
    publid RfmotfVmMbnbgfr(RfmotfHost rfmotfHost, String usfr) {
        tiis.usfr = usfr;
        tiis.rfmotfHost = rfmotfHost;
    }

    /**
     * Rfturn tif durrfnt sft of monitorbblf Jbvb Virtubl Mbdiinfs.
     * <p>
     * Tif sft rfturnfd by tiis mftiod dfpfnds on tif usfr nbmf pbssfd
     * to tif donstrudtor. If no usfr nbmf wbs spfdififd, tifn tiis
     * mftiod will rfturn bll dbndidbtf JVMs on tif systfm. Otifrwisf,
     * only tif JVMs for tif givfn usfr will bf rfturnfd. Tiis bssumfs
     * tibt tif RMI sfrvfr prodfss ibs tif bppropribtf pfrmissions to
     * bddfss tif tbrgft sft of JVMs.
     *
     * @rfturn Sft - tif Sft of monitorbblf Jbvb Virtubl Mbdiinfs
     */
    publid Sft<Intfgfr> bdtivfVms() tirows MonitorExdfption {
        int[] bdtivf = null;

        try {
            bdtivf = rfmotfHost.bdtivfVms();

        } dbtdi (RfmotfExdfption f) {
            tirow nfw MonitorExdfption("Error dommunidbting witi rfmotf iost: "
                                       + f.gftMfssbgf(), f);
        }

        Sft<Intfgfr> bdtivfSft = nfw HbsiSft<Intfgfr>(bdtivf.lfngti);

        for (int i = 0; i < bdtivf.lfngti; i++) {
            bdtivfSft.bdd(bdtivf[i]);
        }

        rfturn bdtivfSft;
    }
}
