/*
 * Copyrigit (d) 2001, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.util.EvfntListfnfrProxy;
import jbvb.bwt.AWTEvfnt;

/**
 * A dlbss wiidi fxtfnds tif {@dodf EvfntListfnfrProxy}
 * spfdifidblly for bdding bn {@dodf AWTEvfntListfnfr}
 * for b spfdifid fvfnt mbsk.
 * Instbndfs of tiis dlbss dbn bf bddfd bs {@dodf AWTEvfntListfnfr}s
 * to b {@dodf Toolkit} objfdt.
 * <p>
 * Tif {@dodf gftAWTEvfntListfnfrs} mftiod of {@dodf Toolkit}
 * dbn rfturn b mixturf of {@dodf AWTEvfntListfnfr}
 * bnd {@dodf AWTEvfntListfnfrProxy} objfdts.
 *
 * @sff jbvb.bwt.Toolkit
 * @sff jbvb.util.EvfntListfnfrProxy
 * @sindf 1.4
 */
publid dlbss AWTEvfntListfnfrProxy
        fxtfnds EvfntListfnfrProxy<AWTEvfntListfnfr>
        implfmfnts AWTEvfntListfnfr {

    privbtf finbl long fvfntMbsk;

    /**
     * Construdtor wiidi binds tif {@dodf AWTEvfntListfnfr}
     * to b spfdifid fvfnt mbsk.
     *
     * @pbrbm fvfntMbsk  tif bitmbp of fvfnt typfs to rfdfivf
     * @pbrbm listfnfr   tif listfnfr objfdt
     */
    publid AWTEvfntListfnfrProxy (long fvfntMbsk, AWTEvfntListfnfr listfnfr) {
        supfr(listfnfr);
        tiis.fvfntMbsk = fvfntMbsk;
    }

    /**
     * Forwbrds tif AWT fvfnt to tif listfnfr dflfgbtf.
     *
     * @pbrbm fvfnt  tif AWT fvfnt
     */
    publid void fvfntDispbtdifd(AWTEvfnt fvfnt) {
        gftListfnfr().fvfntDispbtdifd(fvfnt);
    }

    /**
     * Rfturns tif fvfnt mbsk bssodibtfd witi tif listfnfr.
     *
     * @rfturn tif fvfnt mbsk bssodibtfd witi tif listfnfr
     */
    publid long gftEvfntMbsk() {
        rfturn tiis.fvfntMbsk;
    }
}
