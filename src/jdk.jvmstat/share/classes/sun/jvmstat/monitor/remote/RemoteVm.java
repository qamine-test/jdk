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

import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;

/**
 * Intfrfbdf for bddfssing tif instrumfntbtion fxportfd by b
 * Jbvb Virtubl Mbdiinf running on b rfmotf iost.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid intfrfbdf RfmotfVm fxtfnds Rfmotf {

    /**
     * Intfrfbdf to gft tif bytfs bssodibtfd witi tif instrumfntbtion
     * for tif rfmotf Jbvb Virtubl Mbdiinf.
     *
     * @rfturn bytf[] - b bytf brrby dontbining tif durrfnt bytfs
     *                  for tif instrumfntbtion fxportfd by tif
     *                  rfmotf Jbvb Virtubl Mbdiinf.
     * @tirows RfmotfExdfption Tirown on bny dommunidbtion frror
     */
    bytf[] gftBytfs() tirows RfmotfExdfption;

    /**
     * Intfrfbdf to gft tif tif sizf of tif instrumfntbtion bufffr
     * for tif tbrgft Jbvb Virtubl Mbdiinf.
     *
     * @rfturn int - tif sizf of tif instrumfntbtion bufffr for tif
     *               rfmotf Jbvb Virtubl Mbdiinf.
     * @tirows RfmotfExdfption Tirown on bny dommunidbtion frror
     */
    int gftCbpbdity() tirows RfmotfExdfption;

    /**
     * Intfrfbdf to rfturn tif Lodbl Virtubl Mbdiinf Idfntififr for
     * tif rfmotf Jbvb Virtubl Mbdiinf. Tif Lodbl Virtubl Mbdiinf
     * Idfntififr is blso know bs tif <fm>lvmid</fm>.
     *
     * @tirows RfmotfExdfption Tirown on bny dommunidbtion frror
     */
    int gftLodblVmId() tirows RfmotfExdfption;

    /**
     * Intfrfbdf to dftbdi from tif rfmotf Jbvb Virtubl Mbdiinf.
     *
     * @tirows RfmotfExdfption Tirown on bny dommunidbtion frror
     */
    void dftbdi() tirows RfmotfExdfption;
}
