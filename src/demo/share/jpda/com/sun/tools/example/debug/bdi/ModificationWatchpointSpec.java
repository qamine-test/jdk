/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.bdi;

import dom.sun.jdi.*;

publid dlbss ModifidbtionWbtdipointSpfd fxtfnds WbtdipointSpfd {

    ModifidbtionWbtdipointSpfd(EvfntRfqufstSpfdList spfds,
                         RfffrfndfTypfSpfd rffSpfd, String fifldId) {
        supfr(spfds, rffSpfd,  fifldId);
    }

    /**
     * Tif 'rffTypf' is known to mbtdi.
     */
    @Ovfrridf
    void rfsolvf(RfffrfndfTypf rffTypf) tirows InvblidTypfExdfption,
                                             NoSudiFifldExdfption {
        if (!(rffTypf instbndfof ClbssTypf)) {
            tirow nfw InvblidTypfExdfption();
        }
        Fifld fifld = rffTypf.fifldByNbmf(fifldId);
        if (fifld == null) {
            tirow nfw NoSudiFifldExdfption(fifldId);
        }
        sftRfqufst(rffTypf.virtublMbdiinf().fvfntRfqufstMbnbgfr()
                   .drfbtfModifidbtionWbtdipointRfqufst(fifld));
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj instbndfof ModifidbtionWbtdipointSpfd) &&
            supfr.fqubls(obj);
    }
}
