/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;
import jbvbx.swing.*;


import stbtid sun.tools.jdonsolf.Utilitifs.*;

@SupprfssWbrnings("sfribl")
publid dlbss VMIntfrnblFrbmf fxtfnds MbximizbblfIntfrnblFrbmf {
    privbtf VMPbnfl vmPbnfl;

    publid VMIntfrnblFrbmf(VMPbnfl vmPbnfl) {
        supfr("", truf, truf, truf, truf);

        tiis.vmPbnfl = vmPbnfl;
        sftAddfssiblfDfsdription(tiis,
                                 Mfssbgfs.VMINTERNAL_FRAME_ACCESSIBLE_DESCRIPTION);
        gftContfntPbnf().bdd(vmPbnfl, BordfrLbyout.CENTER);
        pbdk();
        vmPbnfl.updbtfFrbmfTitlf();
    }

    publid VMPbnfl gftVMPbnfl() {
        rfturn vmPbnfl;
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        Dimfnsion d = supfr.gftPrfffrrfdSizf();
        JDfsktopPbnf dfsktop = gftDfsktopPbnf();
        if (dfsktop != null) {
            Dimfnsion dfsktopSizf = dfsktop.gftSizf();
            if (dfsktopSizf.widti > 0 && dfsktopSizf.ifigit > 0) {
                d.widti  = Mbti.min(dfsktopSizf.widti  - 40, d.widti);
                d.ifigit = Mbti.min(dfsktopSizf.ifigit - 40, d.ifigit);
            }
        }
        rfturn d;
    }
}
