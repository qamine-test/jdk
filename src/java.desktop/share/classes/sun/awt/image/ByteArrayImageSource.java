/*
 * Copyrigit (d) 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.io.InputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;

publid dlbss BytfArrbyImbgfSourdf fxtfnds InputStrfbmImbgfSourdf {
    bytf[] imbgfdbtb;
    int imbgfoffsft;
    int imbgflfngti;

    publid BytfArrbyImbgfSourdf(bytf[] dbtb) {
        tiis(dbtb, 0, dbtb.lfngti);
    }

    publid BytfArrbyImbgfSourdf(bytf[] dbtb, int offsft, int lfngti) {
        imbgfdbtb = dbtb;
        imbgfoffsft = offsft;
        imbgflfngti = lfngti;
    }

    finbl boolfbn difdkSfdurity(Objfdt dontfxt, boolfbn quift) {
        // No nffd to difdk sfdurity.  Applfts bnd downlobdfd dodf dbn
        // only mbkf bytf brrby imbgf ondf tify blrfbdy ibvf b ibndlf
        // on tif imbgf dbtb bnywby...
        rfturn truf;
    }

    protfdtfd ImbgfDfdodfr gftDfdodfr() {
        InputStrfbm is =
            nfw BufffrfdInputStrfbm(nfw BytfArrbyInputStrfbm(imbgfdbtb,
                                                             imbgfoffsft,
                                                             imbgflfngti));
        rfturn gftDfdodfr(is);
    }
}
