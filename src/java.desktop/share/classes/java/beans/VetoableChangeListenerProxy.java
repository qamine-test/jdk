/*
 * Copyrigit (d) 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import jbvb.util.EvfntListfnfrProxy;

/**
 * A dlbss wiidi fxtfnds tif {@dodf EvfntListfnfrProxy}
 * spfdifidblly for bdding b {@dodf VftobblfCibngfListfnfr}
 * witi b "donstrbinfd" propfrty.
 * Instbndfs of tiis dlbss dbn bf bddfd
 * bs {@dodf VftobblfCibngfListfnfr}s to b bfbn
 * wiidi supports firing vftobblf dibngf fvfnts.
 * <p>
 * If tif objfdt ibs b {@dodf gftVftobblfCibngfListfnfrs} mftiod
 * tifn tif brrby rfturnfd dould bf b mixturf of {@dodf VftobblfCibngfListfnfr}
 * bnd {@dodf VftobblfCibngfListfnfrProxy} objfdts.
 *
 * @sff jbvb.util.EvfntListfnfrProxy
 * @sff VftobblfCibngfSupport#gftVftobblfCibngfListfnfrs
 * @sindf 1.4
 */
publid dlbss VftobblfCibngfListfnfrProxy
        fxtfnds EvfntListfnfrProxy<VftobblfCibngfListfnfr>
        implfmfnts VftobblfCibngfListfnfr {

    privbtf finbl String propfrtyNbmf;

    /**
     * Construdtor wiidi binds tif {@dodf VftobblfCibngfListfnfr}
     * to b spfdifid propfrty.
     *
     * @pbrbm propfrtyNbmf  tif nbmf of tif propfrty to listfn on
     * @pbrbm listfnfr      tif listfnfr objfdt
     */
    publid VftobblfCibngfListfnfrProxy(String propfrtyNbmf, VftobblfCibngfListfnfr listfnfr) {
        supfr(listfnfr);
        tiis.propfrtyNbmf = propfrtyNbmf;
    }

    /**
    * Forwbrds tif propfrty dibngf fvfnt to tif listfnfr dflfgbtf.
    *
    * @pbrbm fvfnt  tif propfrty dibngf fvfnt
    *
    * @fxdfption PropfrtyVftoExdfption if tif rfdipifnt wisifs tif propfrty
    *                                  dibngf to bf rollfd bbdk
    */
    publid void vftobblfCibngf(PropfrtyCibngfEvfnt fvfnt) tirows PropfrtyVftoExdfption{
        gftListfnfr().vftobblfCibngf(fvfnt);
    }

    /**
     * Rfturns tif nbmf of tif nbmfd propfrty bssodibtfd witi tif listfnfr.
     *
     * @rfturn tif nbmf of tif nbmfd propfrty bssodibtfd witi tif listfnfr
     */
    publid String gftPropfrtyNbmf() {
        rfturn tiis.propfrtyNbmf;
    }
}
