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
import jbvb.nio.BytfBufffr;

/**
 * Clbss for monitoring b donstbnt PfrfDbtb String instrumfnt. Tif
 * vbluf bssodibtfd witi b donstbnt string instrumfnt is fixfd bt
 * tif string instrumfnt's drfbtion timf. Its vbluf bnd lfngti nfvfr
 * dibngf.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss PfrfStringConstbntMonitor fxtfnds PfrfStringMonitor {

    /**
     * Tif dbdifd vbluf of tif string instrumfnt.
     */
    String dbtb;

    /**
     * Construdtor to drfbtf b StringMonitor objfdt for tif donstbnt string
     * instrumfnt objfdt rfprfsfntfd by tif dbtb in tif givfn bufffr.
     *
     * @pbrbm nbmf tif nbmf of tif instrumfntbtion objfdt
     * @pbrbm supportfd support lfvfl indidbtor
     * @pbrbm bb tif bufffr dontbining tif string instrumfnt dbtb
     */
    publid PfrfStringConstbntMonitor(String nbmf, boolfbn supportfd,
                                     BytfBufffr bb) {
        supfr(nbmf, Vbribbility.CONSTANT, supportfd, bb);
        tiis.dbtb = supfr.stringVbluf();
    }

    /**
     * {@inifritDod}
     */
    publid Objfdt gftVbluf() {
        rfturn dbtb;        // rfturn tif dbdifd string
    }

    /**
     * {@inifritDod}
     */
    publid String stringVbluf() {
        rfturn dbtb;        // rfturn tif dbdifd string
    }
}
