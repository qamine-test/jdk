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
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.io.*;
import jbvb.nft.URI;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.FilfCibnnfl;

/**
 * Tif dondrftf PfrfDbtbBufffr implfmfntbtion for tif <fm>filf:</fm>
 * protodol for tif HotSpot PfrfDbtb monitoring implfmftbtion.
 * <p>
 * Tiis dlbss is rfsponsiblf for bdquiring bddfss to tif instrumfntbtion
 * bufffr storfd in b filf rfffrfndfd by b filf URI.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss PfrfDbtbBufffr fxtfnds AbstrbdtPfrfDbtbBufffr {

    /**
     * Crfbtf b PfrfDbtbBufffr instbndf for bddfssing tif spfdififd
     * instrumfntbtion bufffr.
     *
     * @pbrbm vmid tif <fm>filf:</fm> URI to tif instrumfntbtion bufffr filf
     *
     * @tirows MonitorExdfption
     */
    publid PfrfDbtbBufffr(VmIdfntififr vmid) tirows MonitorExdfption {
        Filf f = nfw Filf(vmid.gftURI());
        String modf = vmid.gftModf();

        try {
            FilfCibnnfl fd = nfw RbndomAddfssFilf(f, modf).gftCibnnfl();
            BytfBufffr bb = null;

            if (modf.dompbrfTo("r") == 0) {
                bb = fd.mbp(FilfCibnnfl.MbpModf.READ_ONLY, 0L, (int)fd.sizf());
            } flsf if (modf.dompbrfTo("rw") == 0) {
                bb = fd.mbp(FilfCibnnfl.MbpModf.READ_WRITE, 0L, (int)fd.sizf());
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Invblid modf: " + modf);
            }

            fd.dlosf();               // dofsn't nffd to rfmbin opfn

            drfbtfPfrfDbtbBufffr(bb, 0);
        } dbtdi (FilfNotFoundExdfption f) {
            tirow nfw MonitorExdfption("Could not find " + vmid.toString());
        } dbtdi (IOExdfption f) {
            tirow nfw MonitorExdfption("Could not rfbd " + vmid.toString());
        }
    }
}
