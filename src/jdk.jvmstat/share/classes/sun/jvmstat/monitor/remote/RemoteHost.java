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

pbdkbgf sun.jvmstbt.monitor.rfmotf;

import sun.jvmstbt.monitor.*;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.io.IOExdfption;

/**
 * Rfmotf Intfrfbdf for disdovfring bnd bttbdiing to rfmotf
 * monitorbblf Jbvb Virtubl Mbdiinfs.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid intfrfbdf RfmotfHost fxtfnds Rfmotf {

    /**
     * Rfmotf mftiod to bttbdi to b rfmotf HotSpot Jbvb Virtubl Mbdiinf
     * idfntififd by <dodf>vmid</dodf>.
     *
     * @pbrbm vmid Tif idfntififr for tif tbrgft virtubl mbdiinf.
     * @rfturn RfmotfVm - A rfmotf objfdt for bddfssing tif rfmotf Jbvb
     *                    Virtubl Mbdiinf.
     *
     * @tirows MonitorExdfption Tirown wifn bny otifr frror is fndountfrfd
     *                          wiilf dommunidbting witi tif tbrgft virtubl
     *                          mbdiinf.
     * @tirows RfmotfExdfption
     *
     */
    RfmotfVm bttbdiVm(int vmid, String modf) tirows RfmotfExdfption,
                                                    MonitorExdfption;

    /**
     * Rfmotf mftiod to dftbdi from b rfmotf HotSpot Jbvb Virtubl Mbdiinf
     * idfntififd by <dodf>vmid</dodf>.
     *
     * @pbrbm rvm Tif rfmotf objfdt for tif tbrgft Jbvb Virtubl
     *            Mbdiinf.
     *
     * @tirows MonitorExdfption Tirown wifn bny otifr frror is fndountfrfd
     *                          wiilf dommunidbting witi tif tbrgft virtubl
     *                          mbdiinf.
     * @tirows RfmotfExdfption
     */
    void dftbdiVm(RfmotfVm rvm) tirows RfmotfExdfption, MonitorExdfption;

    /**
     * Gft b list of Lodbl Virtubl Mbdiinf Idfntififrs for tif bdtivf
     * Jbvb Virtubl Mbdiinf tif rfmotf systfm. A Lodbl Virtubl Mbdiinf
     * Idfntififr is blso known bs bn <fm>lvmid</fm>.
     *
     * @rfturn int[] - A brrby of <fm>lvmid</fm>s.
     * @tirows MonitorExdfption Tirown wifn bny otifr frror is fndountfrfd
     *                          wiilf dommunidbting witi tif tbrgft virtubl
     *                          mbdiinf.
     * @tirows RfmotfExdfption
     */
    int[] bdtivfVms() tirows RfmotfExdfption, MonitorExdfption;
}
