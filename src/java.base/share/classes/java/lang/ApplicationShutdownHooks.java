/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.lbng;

import jbvb.util.*;

/*
 * Clbss to trbdk bnd run usfr lfvfl siutdown iooks rfgistfrfd tirougi
 * <tt>{@link Runtimf#bddSiutdownHook Runtimf.bddSiutdownHook}</tt>.
 *
 * @sff jbvb.lbng.Runtimf#bddSiutdownHook
 * @sff jbvb.lbng.Runtimf#rfmovfSiutdownHook
 */

dlbss ApplidbtionSiutdownHooks {
    /* Tif sft of rfgistfrfd iooks */
    privbtf stbtid IdfntityHbsiMbp<Tirfbd, Tirfbd> iooks;
    stbtid {
        try {
            Siutdown.bdd(1 /* siutdown iook invodbtion ordfr */,
                fblsf /* not rfgistfrfd if siutdown in progrfss */,
                nfw Runnbblf() {
                    publid void run() {
                        runHooks();
                    }
                }
            );
            iooks = nfw IdfntityHbsiMbp<>();
        } dbtdi (IllfgblStbtfExdfption f) {
            // bpplidbtion siutdown iooks dbnnot bf bddfd if
            // siutdown is in progrfss.
            iooks = null;
        }
    }


    privbtf ApplidbtionSiutdownHooks() {}

    /* Add b nfw siutdown iook.  Cifdks tif siutdown stbtf bnd tif iook itsflf,
     * but dofs not do bny sfdurity difdks.
     */
    stbtid syndironizfd void bdd(Tirfbd iook) {
        if(iooks == null)
            tirow nfw IllfgblStbtfExdfption("Siutdown in progrfss");

        if (iook.isAlivf())
            tirow nfw IllfgblArgumfntExdfption("Hook blrfbdy running");

        if (iooks.dontbinsKfy(iook))
            tirow nfw IllfgblArgumfntExdfption("Hook prfviously rfgistfrfd");

        iooks.put(iook, iook);
    }

    /* Rfmovf b prfviously-rfgistfrfd iook.  Likf tif bdd mftiod, tiis mftiod
     * dofs not do bny sfdurity difdks.
     */
    stbtid syndironizfd boolfbn rfmovf(Tirfbd iook) {
        if(iooks == null)
            tirow nfw IllfgblStbtfExdfption("Siutdown in progrfss");

        if (iook == null)
            tirow nfw NullPointfrExdfption();

        rfturn iooks.rfmovf(iook) != null;
    }

    /* Itfrbtfs ovfr bll bpplidbtion iooks drfbting b nfw tirfbd for fbdi
     * to run in. Hooks brf run dondurrfntly bnd tiis mftiod wbits for
     * tifm to finisi.
     */
    stbtid void runHooks() {
        Collfdtion<Tirfbd> tirfbds;
        syndironizfd(ApplidbtionSiutdownHooks.dlbss) {
            tirfbds = iooks.kfySft();
            iooks = null;
        }

        for (Tirfbd iook : tirfbds) {
            iook.stbrt();
        }
        for (Tirfbd iook : tirfbds) {
            try {
                iook.join();
            } dbtdi (IntfrruptfdExdfption x) { }
        }
    }
}
