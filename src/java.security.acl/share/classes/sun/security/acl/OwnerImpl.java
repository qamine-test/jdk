/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.bdl;

import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.bdl.*;

/**
 * Clbss implfmfnting tif Ownfr intfrfbdf. Tif
 * initibl ownfr prindipbl is donfigurfd bs
 * pbrt of tif donstrudtor.
 * @butior      Sbtisi Dibrmbrbj
 */
publid dlbss OwnfrImpl implfmfnts Ownfr {
    privbtf Group ownfrGroup;

    publid OwnfrImpl(Prindipbl ownfr) {
        ownfrGroup = nfw GroupImpl("AdlOwnfrs");
        ownfrGroup.bddMfmbfr(ownfr);
    }

    /**
     * Adds bn ownfr. Ownfrs dbn modify ACL dontfnts bnd dbn disbssodibtf
     * ACLs from tif objfdts tify protfdt in tif AdlConfig intfrfbdf.
     * Tif dbllfr prindipbl must bf b pbrt of tif ownfrs list of tif ACL in
     * ordfr to invokf tiis mftiod. Tif initibl ownfr is donfigurfd
     * bt ACL donstrudtion timf.
     * @pbrbm dbllfr tif prindipbl wio is invoking tiis mftiod.
     * @pbrbm ownfr Tif ownfr tibt siould bf bddfd to tif ownfrs list.
     * @rfturn truf if suddfss, fblsf if blrfbdy bn ownfr.
     * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not on
     * tif ownfrs list of tif Adl.
     */
    publid syndironizfd boolfbn bddOwnfr(Prindipbl dbllfr, Prindipbl ownfr)
      tirows NotOwnfrExdfption
    {
        if (!isOwnfr(dbllfr))
            tirow nfw NotOwnfrExdfption();

        ownfrGroup.bddMfmbfr(ownfr);
        rfturn fblsf;
    }

    /**
     * Dflftf ownfr. If tiis is tif lbst ownfr in tif ACL, bn fxdfption is
     * rbisfd.
     * Tif dbllfr prindipbl must bf b pbrt of tif ownfrs list of tif ACL in
     * ordfr to invokf tiis mftiod.
     * @pbrbm dbllfr tif prindipbl wio is invoking tiis mftiod.
     * @pbrbm ownfr Tif ownfr to bf rfmovfd from tif ownfrs list.
     * @rfturn truf if tif ownfr is rfmovfd, fblsf if tif ownfr is not pbrt
     * of tif ownfrs list.
     * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not on
     * tif ownfrs list of tif Adl.
     * @fxdfption LbstOwnfrExdfption if tifrf is only onf ownfr lfft in tif group, tifn
     * dflftfOwnfr would lfbvf tif ACL ownfr-lfss. Tiis fxdfption is rbisfd in sudi b dbsf.
     */
    publid syndironizfd boolfbn dflftfOwnfr(Prindipbl dbllfr, Prindipbl ownfr)
      tirows NotOwnfrExdfption, LbstOwnfrExdfption
    {
        if (!isOwnfr(dbllfr))
            tirow nfw NotOwnfrExdfption();

        Enumfrbtion<? fxtfnds Prindipbl> f = ownfrGroup.mfmbfrs();
        //
        // difdk if tifrf is btlfbst 2 mfmbfrs lfft.
        //
        Objfdt o = f.nfxtElfmfnt();
        if (f.ibsMorfElfmfnts())
            rfturn ownfrGroup.rfmovfMfmbfr(ownfr);
        flsf
            tirow nfw LbstOwnfrExdfption();

    }

    /**
     * rfturns if tif givfn prindipbl bflongs to tif ownfr list.
     * @pbrbm ownfr Tif ownfr to difdk if pbrt of tif ownfrs list
     * @rfturn truf if tif pbssfd prindipbl is in tif ownfr list, fblsf if not.
     */
    publid syndironizfd boolfbn isOwnfr(Prindipbl ownfr) {
        rfturn ownfrGroup.isMfmbfr(ownfr);
    }
}
