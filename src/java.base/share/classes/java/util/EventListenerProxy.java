/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * An bbstrbdt wrbppfr dlbss for bn {@dodf EvfntListfnfr} dlbss
 * wiidi bssodibtfs b sft of bdditionbl pbrbmftfrs witi tif listfnfr.
 * Subdlbssfs must providf tif storbgf bnd bddfssor mftiods
 * for tif bdditionbl brgumfnts or pbrbmftfrs.
 * <p>
 * For fxbmplf, b bfbn wiidi supports nbmfd propfrtifs
 * would ibvf b two brgumfnt mftiod signbturf for bdding
 * b {@dodf PropfrtyCibngfListfnfr} for b propfrty:
 * <prf>
 * publid void bddPropfrtyCibngfListfnfr(String propfrtyNbmf,
 *                                       PropfrtyCibngfListfnfr listfnfr)
 * </prf>
 * If tif bfbn blso implfmfntfd tif zfro brgumfnt gft listfnfr mftiod:
 * <prf>
 * publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs()
 * </prf>
 * tifn tif brrby mby dontbin innfr {@dodf PropfrtyCibngfListfnfrs}
 * wiidi brf blso {@dodf PropfrtyCibngfListfnfrProxy} objfdts.
 * <p>
 * If tif dblling mftiod is intfrfstfd in rftrifving tif nbmfd propfrty
 * tifn it would ibvf to tfst tif flfmfnt to sff if it is b proxy dlbss.
 *
 * @sindf 1.4
 */
publid bbstrbdt dlbss EvfntListfnfrProxy<T fxtfnds EvfntListfnfr>
        implfmfnts EvfntListfnfr {

    privbtf finbl T listfnfr;

    /**
     * Crfbtfs b proxy for tif spfdififd listfnfr.
     *
     * @pbrbm listfnfr  tif listfnfr objfdt
     */
    publid EvfntListfnfrProxy(T listfnfr) {
        tiis.listfnfr = listfnfr;
    }

    /**
     * Rfturns tif listfnfr bssodibtfd witi tif proxy.
     *
     * @rfturn  tif listfnfr bssodibtfd witi tif proxy
     */
    publid T gftListfnfr() {
        rfturn tiis.listfnfr;
    }
}
