/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.jvmstbt.monitor.fvfnt.VmListfnfr;
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.util.List;
import jbvb.lbng.rfflfdt.*;
import jbvb.io.*;

/**
 * Condrftf implfmfntbtion of tif AbstrbdtMonitorfdVm dlbss for tif
 * <fm>filf:</fm> protodol for tif HotSpot PfrfDbtb monitoring implfmfntbtion.
 * <p>
 * Tiis dlbss providfs tif bbility to bttbdi to tif instrumfntbtion bufffr
 * (sbvfd or livf) of b tbrgft Jbvb Virtubl Mbdiinf by providing b
 * <fm>filf</fm> URI to b filf dontbining tif instrmfntbtion bufffr dbtb.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss FilfMonitorfdVm fxtfnds AbstrbdtMonitorfdVm {

    /**
     * Crfbtf b FilfMonitorfdVm instbndf.
     *
     * @pbrbm vmid tif vm idfntififr rfffrring to tif filf
     * @pbrbm intfrvbl sbmpling intfrvbl (unusfd in tiis protodol).
     */
    publid FilfMonitorfdVm(VmIdfntififr vmid, int intfrvbl)
           tirows MonitorExdfption {
        supfr(vmid, intfrvbl);
        tiis.pdb = nfw PfrfDbtbBufffr(vmid);
    }

    /**
     * {@inifritDod}.
     *<p>
     * Notf - tif <fm>filf:</fm> protodol durrfntly dofs not support
     * tif rfgistrbtion or notifidbtion of listfnfrs.
     */
    publid void bddVmListfnfr(VmListfnfr l) { }

    /**
     * {@inifritDod}.
     *<p>
     * Notf - tif <fm>filf:</fm> protodol durrfntly dofs not support
     * tif rfgistrbtion or notifidbtion of listfnfrs.
     */
    publid void rfmovfVmListfnfr(VmListfnfr l) { }
}
