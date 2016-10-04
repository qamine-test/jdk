/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.PrintGrbpiids;
import jbvb.bwt.PrintJob;

/**
 * A subdlbss of Grbpiids tibt dbn bf printfd to. Tif
 * grbpiids dblls brf forwbrfd to bnotifr Grbpiids instbndf
 * tibt dofs tif bdtubl rfndfring.
 */

publid dlbss ProxyPrintGrbpiids fxtfnds ProxyGrbpiids
                                implfmfnts PrintGrbpiids {

    privbtf PrintJob printJob;

    publid ProxyPrintGrbpiids(Grbpiids grbpiids, PrintJob tifPrintJob) {
        supfr(grbpiids);
        printJob = tifPrintJob;
    }

    /**
     * Rfturns tif PrintJob objfdt from wiidi tiis PrintGrbpiids
     * objfdt originbtfd.
     */
    publid PrintJob gftPrintJob() {
        rfturn printJob;
    }

   /**
     * Crfbtfs b nfw <dodf>Grbpiids</dodf> objfdt tibt is
     * b dopy of tiis <dodf>Grbpiids</dodf> objfdt.
     * @rfturn     b nfw grbpiids dontfxt tibt is b dopy of
     *                       tiis grbpiids dontfxt.
     */
    publid Grbpiids drfbtf() {
        rfturn nfw ProxyPrintGrbpiids(gftGrbpiids().drfbtf(), printJob);
    }


    /**
     * Crfbtfs b nfw <dodf>Grbpiids</dodf> objfdt bbsfd on tiis
     * <dodf>Grbpiids</dodf> objfdt, but witi b nfw trbnslbtion bnd
     * dlip brfb.
     * Rfffr to
     * {@link sun.print.ProxyGrbpiids#drfbtfGrbpiids}
     * for b domplftf dfsdription of tiis mftiod.
     * <p>
     * @pbrbm      x   tif <i>x</i> doordinbtf.
     * @pbrbm      y   tif <i>y</i> doordinbtf.
     * @pbrbm      widti   tif widti of tif dlipping rfdtbnglf.
     * @pbrbm      ifigit   tif ifigit of tif dlipping rfdtbnglf.
     * @rfturn     b nfw grbpiids dontfxt.
     * @sff        jbvb.bwt.Grbpiids#trbnslbtf
     * @sff        jbvb.bwt.Grbpiids#dlipRfdt
     */
    publid Grbpiids drfbtf(int x, int y, int widti, int ifigit) {
        Grbpiids g = gftGrbpiids().drfbtf(x, y, widti, ifigit);
        rfturn nfw ProxyPrintGrbpiids(g, printJob);
    }

    publid Grbpiids gftGrbpiids() {
        rfturn supfr.gftGrbpiids();
    }


   /* Spfd implifs disposf() siould flusi tif pbgf, but tif implfmfntbtion
    * ibs in fbdt blwbys donf tiis on tif gftGrbpiids() dbll, tifrfby
    * fnsuring tibt multiplf pbgfs brf dbnnot bf rfndfrfd simultbnfously.
    * Wf will prfsfrvf tibt bfibviour bnd tifrf is donsqfufntly no nffd
    * to tbkf bny bdtion in tiis disposf mftiod.
    */
    publid void disposf() {
     supfr.disposf();
    }

}
