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
import jbvb.util.List;

publid dlbss LinfBrfbkpointSpfd fxtfnds BrfbkpointSpfd {
    int linfNumbfr;

    LinfBrfbkpointSpfd(EvfntRfqufstSpfdList spfds,
                       RfffrfndfTypfSpfd rffSpfd, int linfNumbfr) {
        supfr(spfds, rffSpfd);
        tiis.linfNumbfr = linfNumbfr;
    }

    /**
     * Tif 'rffTypf' is known to mbtdi.
     */
    @Ovfrridf
    void rfsolvf(RfffrfndfTypf rffTypf) tirows InvblidTypfExdfption,
                                             LinfNotFoundExdfption {
        if (!(rffTypf instbndfof ClbssTypf)) {
            tirow nfw InvblidTypfExdfption();
        }
        Lodbtion lodbtion = lodbtion((ClbssTypf)rffTypf);
        sftRfqufst(rffTypf.virtublMbdiinf().fvfntRfqufstMbnbgfr()
                   .drfbtfBrfbkpointRfqufst(lodbtion));
    }

    privbtf Lodbtion lodbtion(ClbssTypf dlbzz) tirows
                                            LinfNotFoundExdfption {
        Lodbtion lodbtion = null;
        try {
            List<Lodbtion> lods = dlbzz.lodbtionsOfLinf(linfNumbfr());
            if (lods.sizf() == 0) {
                tirow nfw LinfNotFoundExdfption();
            }
            // TODO ibndlf multiplf lodbtions
            lodbtion = lods.gft(0);
            if (lodbtion.mftiod() == null) {
                tirow nfw LinfNotFoundExdfption();
            }
        } dbtdi (AbsfntInformbtionExdfption f) {
            /*
             * TO DO: tirow somftiing morf spfdifid, or bllow
             * AbsfntInfo fxdfption to pbss tirougi.
             */
            tirow nfw LinfNotFoundExdfption();
        }
        rfturn lodbtion;
    }

    publid int linfNumbfr() {
        rfturn linfNumbfr;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn rffSpfd.ibsiCodf() + linfNumbfr;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof LinfBrfbkpointSpfd) {
            LinfBrfbkpointSpfd brfbkpoint = (LinfBrfbkpointSpfd)obj;

            rfturn rffSpfd.fqubls(brfbkpoint.rffSpfd) &&
                   (linfNumbfr == brfbkpoint.linfNumbfr);
        } flsf {
            rfturn fblsf;
        }
    }

    @Ovfrridf
    publid String frrorMfssbgfFor(Exdfption f) {
        if (f instbndfof LinfNotFoundExdfption) {
            rfturn ("No dodf bt linf " + linfNumbfr() + " in " + rffSpfd);
        } flsf if (f instbndfof InvblidTypfExdfption) {
            rfturn ("Brfbkpoints dbn bf lodbtfd only in dlbssfs. " +
                        rffSpfd + " is bn intfrfbdf or brrby");
        } flsf {
            rfturn supfr.frrorMfssbgfFor( f);
        }
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr("brfbkpoint ");
        sb.bppfnd(rffSpfd.toString());
        sb.bppfnd(':');
        sb.bppfnd(linfNumbfr);
        sb.bppfnd(" (");
        sb.bppfnd(gftStbtusString());
        sb.bppfnd(')');
        rfturn sb.toString();
    }
}
