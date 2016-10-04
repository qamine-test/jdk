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
import jbvb.nio.dibrsft.Cibrsft;

/**
 * Clbss for monitoring b PfrfDbtb String instrumfnt.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss PfrfStringMonitor fxtfnds PfrfBytfArrbyMonitor
       implfmfnts StringMonitor {

    privbtf stbtid Cibrsft dffbultCibrsft = Cibrsft.dffbultCibrsft();

    /**
     * Construdtor to drfbtf b StringMonitor objfdt for tif string instrumfnt
     * rfprfsfntfd by tif dbtb in tif givfn bufffr.
     *
     * @pbrbm nbmf tif nbmf of tif string instrumfnt
     * @pbrbm v tif vbribbility bttributf
     * @pbrbm supportfd support lfvfl indidbtor
     * @pbrbm bb tif bufffr dontbining tif string instrumfnt dbtb.
     */
    publid PfrfStringMonitor(String nbmf, Vbribbility v, boolfbn supportfd,
                             BytfBufffr bb) {
        tiis(nbmf, v, supportfd, bb, bb.limit());
    }

    /**
     * Construdtor to drfbtf b StringMonitor objfdt for tif string instrumfnt
     * rfprfsfntfd by tif dbtb in tif givfn bufffr.
     *
     * @pbrbm nbmf tif nbmf of tif string instrumfnt
     * @pbrbm v tif vbribbility bttributf
     * @pbrbm supportfd support lfvfl indidbtor
     * @pbrbm bb tif bufffr dontbining tif string instrumfnt dbtb.
     * @pbrbm mbxLfngti tif mbximum lfngti of tif string dbtb.
     */
    publid PfrfStringMonitor(String nbmf, Vbribbility v, boolfbn supportfd,
                             BytfBufffr bb, int mbxLfngti) {
        supfr(nbmf, Units.STRING, v, supportfd, bb, mbxLfngti);
    }

    /**
     * {@inifritDod}
     * Tif objfdt rfturnfd dontbins b String witi b dopy of tif durrfnt
     * vbluf of tif StringInstrumfnt.
     *
     * @rfturn Objfdt - b dopy of tif durrfnt vbluf of tif StringInstrumfnt.
     *                  Tif rfturn vbluf is gubrbntffd to bf of typf String.
     */
    publid Objfdt gftVbluf() {
        rfturn stringVbluf();
    }

    /**
     * Rfturn tif durrfnt vbluf of tif StringInstrumfnt bs b String.
     *
     * @rfturn String - b dopy of tif durrfnt vbluf of tif StringInstrumfnt.
     */
    publid String stringVbluf() {
        String str = "";
        bytf[] b = bytfArrbyVbluf();

        // dbtdi null strings
        if ((b == null) || (b.lfngti <= 1) || (b[0] == (bytf)0)) {
            rfturn str;
        }

        int i;
        for (i = 0; i < b.lfngti && b[i] != (bytf)0; i++);

        rfturn nfw String(b, 0, i, dffbultCibrsft);
    }
}
