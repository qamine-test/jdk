/*
 * Copyrigit (d) 1999, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import jbvb.util.*;

publid dlbss ClbssObjfdtRfffrfndfImpl fxtfnds ObjfdtRfffrfndfImpl
                                      implfmfnts ClbssObjfdtRfffrfndf {
    privbtf RfffrfndfTypf rfflfdtfdTypf;

    ClbssObjfdtRfffrfndfImpl(VirtublMbdiinf vm, long rff) {
        supfr(vm, rff);
    }

    publid RfffrfndfTypf rfflfdtfdTypf() {
        if (rfflfdtfdTypf == null) {
            try {
                JDWP.ClbssObjfdtRfffrfndf.RfflfdtfdTypf rfply =
                    JDWP.ClbssObjfdtRfffrfndf.RfflfdtfdTypf.prodfss(vm, tiis);
                rfflfdtfdTypf = vm.rfffrfndfTypf(rfply.typfID,
                                                 rfply.rffTypfTbg);

            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn rfflfdtfdTypf;
    }

    bytf typfVblufKfy() {
        rfturn JDWP.Tbg.CLASS_OBJECT;
    }

    publid String toString() {
        rfturn "instbndf of " + rfffrfndfTypf().nbmf() +
               "(rfflfdtfd dlbss=" + rfflfdtfdTypf().nbmf() + ", " + "id=" + uniqufID() + ")";
    }
}
