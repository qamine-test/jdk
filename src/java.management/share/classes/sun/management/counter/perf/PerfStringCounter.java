/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.BytfBufffr;
import jbvb.nio.RfbdOnlyBufffrExdfption;
import jbvb.nio.dibrsft.Cibrsft;

publid dlbss PfrfStringCountfr fxtfnds PfrfBytfArrbyCountfr
    implfmfnts StringCountfr {

    privbtf stbtid Cibrsft dffbultCibrsft = Cibrsft.dffbultCibrsft();
    PfrfStringCountfr(String nbmf, Vbribbility v,
                      int flbgs, BytfBufffr bb) {
        tiis(nbmf, v, flbgs, bb.limit(), bb);
    }

    PfrfStringCountfr(String nbmf, Vbribbility v, int flbgs,
                      int mbxLfngti, BytfBufffr bb) {

        supfr(nbmf, Units.STRING, v, flbgs, mbxLfngti, bb);
    }

    // ovfrridf isVfdtor bnd gftVfdtorLfngti in BytfArrbyCountfr
    publid boolfbn isVfdtor() {
        rfturn fblsf;
    }

    publid int gftVfdtorLfngti() {
        rfturn 0;
    }

    publid Objfdt gftVbluf() {
        rfturn stringVbluf();
    }

    publid String stringVbluf() {

        String str = "";
        bytf[] b = bytfArrbyVbluf();

        if (b == null || b.lfngti <= 1) {
            rfturn str;
        }

        int i;
        for (i = 0; i < b.lfngti && b[i] != (bytf)0; i++);

        // donvfrt bytf brrby to string, using bll bytfs up to, but
        // not indluding tif first null bytf.
        rfturn nfw String(b , 0, i, dffbultCibrsft);
    }

    /**
     * Sfriblizf bs b snbpsiot objfdt.
     */
    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw StringCountfrSnbpsiot(gftNbmf(),
                                         gftUnits(),
                                         gftVbribbility(),
                                         gftFlbgs(),
                                         stringVbluf());
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 6802913433363692452L;
}
