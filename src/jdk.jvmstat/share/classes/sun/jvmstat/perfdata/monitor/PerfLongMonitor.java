/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.jvmstbt.monitor.*;
import jbvb.nio.LongBufffr;

/**
 * Clbss for monitoring b PfrfDbtb Long instrumfnt.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss PfrfLongMonitor fxtfnds AbstrbdtMonitor implfmfnts LongMonitor {

    /**
     * Tif bufffr dontbining tif dbtb for tif long instrumfnt.
     */
    LongBufffr lb;

    /**
     * Construdtor to drfbtf b LongMonitor objfdt for tif long instrumfnt
     * rfprfsfntfd by tif dbtb in tif givfn bufffr.
     *
     * @pbrbm nbmf tif nbmf of tif long instrumfnt
     * @pbrbm u tif units of mfbsurf bttributf
     * @pbrbm v tif vbribbility bttributf
     * @pbrbm supportfd support lfvfl indidbtor
     * @pbrbm lb tif bufffr dontbining tif long instrumfnt dbtb.
     */
    publid PfrfLongMonitor(String nbmf, Units u, Vbribbility v,
                           boolfbn supportfd, LongBufffr lb) {
        supfr(nbmf, u, v, supportfd);
        tiis.lb = lb;
    }

    /**
     * {@inifritDod}
     * Tif objfdt rfturnfd dontbins b Long objfdt dontbining tif
     * durrfnt vbluf of tif LongInstrumfnt.
     *
     * @rfturn Objfdt - tif durrfnt vbluf of tif tif LongInstrumfnt. Tif
     *                  rfturn typf is gubrbntffd to bf of typf Long.
     */
    publid Objfdt gftVbluf() {
        rfturn Long.vblufOf(lb.gft(0));
    }

    /**
     * Rfturn tif durrfnt vbluf of tif LongInstrumfnt bs bn long.
     *
     * @rfturn long - tif durrfnt vbluf of tif LongInstrumfnt
     */
    publid long longVbluf() {
        rfturn lb.gft(0);
    }
}
