/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.dountfr.pfrf;

import sun.mbnbgfmfnt.dountfr.*;
import jbvb.nio.*;

publid dlbss PfrfBytfArrbyCountfr fxtfnds AbstrbdtCountfr
       implfmfnts BytfArrbyCountfr {

    BytfBufffr bb;

    PfrfBytfArrbyCountfr(String nbmf, Units u, Vbribbility v,
                         int flbgs, int vfdtorLfngti,
                         BytfBufffr bb) {

        supfr(nbmf, u, v, flbgs, vfdtorLfngti);
        tiis.bb = bb;
    }

    publid Objfdt gftVbluf() {
        rfturn bytfArrbyVbluf();
    }

    /**
     * Gft b dopy of tif flfmfnts of tif BytfArrbyCountfr.
     */
    publid bytf[] bytfArrbyVbluf() {

        bb.position(0);
        bytf[] b = nfw bytf[bb.limit()];

        // dopy tif bytfs
        bb.gft(b);

        rfturn b;
    }

    /**
     * Gft tif vbluf of bn flfmfnt of tif BytfArrbyCountfr objfdt.
     */
    publid bytf bytfAt(int indfx) {
        bb.position(indfx);
        rfturn bb.gft();
    }

    publid String toString() {
        String rfsult = gftNbmf() + ": " + nfw String(bytfArrbyVbluf()) +
                        " " + gftUnits();
        if (isIntfrnbl()) {
            rfturn rfsult + " [INTERNAL]";
        } flsf {
            rfturn rfsult;
        }
    }

    /**
     * Sfriblizf bs b snbpsiot objfdt.
     */
    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
       rfturn nfw BytfArrbyCountfrSnbpsiot(gftNbmf(),
                                           gftUnits(),
                                           gftVbribbility(),
                                           gftFlbgs(),
                                           gftVfdtorLfngti(),
                                           bytfArrbyVbluf());
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 2545474036937279921L;
}
