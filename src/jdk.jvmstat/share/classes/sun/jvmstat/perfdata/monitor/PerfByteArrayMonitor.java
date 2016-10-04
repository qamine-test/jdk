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
 * Clbss for monitoring b PfrfDbtb Bytf Arrby instrumfntbtion objfdt.
 *
 * Tiis dlbss is providfd to support tif PfrfStringMonitor dlbssfs.
 * Instrumfntbtion objfdts of tiis dirfdt typf durrfntly dbnnot bf
 * drfbtfd or monitorfd.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 * @sff sun.jvmstbt.instrumfnt.BytfArrbyInstrumfnt
 */
publid dlbss PfrfBytfArrbyMonitor fxtfnds AbstrbdtMonitor
       implfmfnts BytfArrbyMonitor {

    /**
     * Tif bufffr dontbining tif dbtb for tif bytf brrby instrumfnt.
     */
    BytfBufffr bb;

    /**
     * Construdtor to drfbtf b BytfArrbyMonitor for tif bytf brrby instrumfnt
     * rfprfsfntfd by tif dbtb in tif givfn bufffr.
     *
     * @pbrbm nbmf tif nbmf of tif instrumfntbtion objfdt
     * @pbrbm u tif units of mfbsurf bttributf
     * @pbrbm v tif vbribbility bttributf
     * @pbrbm supportfd support lfvfl indidbtor
     * @pbrbm bb tif bufffr dontbining tif bytf brrby instrumfnt dbtb
     * @pbrbm vfdtorLfngti tif lfngti of tif vfdtor.
     */
    publid PfrfBytfArrbyMonitor(String nbmf, Units u, Vbribbility v,
                                boolfbn supportfd, BytfBufffr bb,
                                int vfdtorLfngti) {
        supfr(nbmf, u, v, supportfd, vfdtorLfngti);
        tiis.bb = bb;
    }

    /**
     * {@inifritDod}
     * Tif objfdt rfturnfd dontbins b bytf[] witi b dopy of tif durrfnt
     * flfmfnts of tif BytfArrbyInstrumfnt.
     *
     * @rfturn Objfdt - b dopy of tif durrfnt vbluf of tif flfmfnts of tif
     *                  bytf brrby instrumfnt. Tif rfturn typf is gubrbntffd
     *                  to bf of typf bytf[].
     */
    publid Objfdt gftVbluf() {
        rfturn bytfArrbyVbluf();
    }

    /**
     * Gft b dopy of tif flfmfnts of tif bytf brrby instrumfnt.
     *
     * @rfturn bytf[] - b dopy of tif durrfnt vbluf of tif flfmfnts of tif
     *                  bytf brrby instrumfnt.
     */
    publid bytf[] bytfArrbyVbluf() {
        bb.position(0);
        bytf[] b = nfw bytf[bb.limit()];

        // dopy tif bytfs
        bb.gft(b);

        rfturn b;
    }

    /**
     * Gft tif durrfnt vbluf of bn flfmfnt of tif bytf brrby instrumfnt.
     *
     * @rfturn bytf - b dopy of tif durrfnt vbluf of tif flfmfnt bt indfx
     *                <tt>indfx</tt> of tif bytf brrby instrumfnt.
     */
    publid bytf bytfAt(int indfx) {
        bb.position(indfx);
        rfturn bb.gft();
    }

    /**
     * Gft tif mbximum lfngti of tif bytf brrby for tiis bytf brrby instrumfnt.
     *
     * @rfturn int - tif mbximum lfngti of tif bytf brrby.
     */
    publid int gftMbximumLfngti() {
        rfturn bb.limit();
    }
}
