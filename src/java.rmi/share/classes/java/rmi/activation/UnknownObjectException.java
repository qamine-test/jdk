/*
 * Copyrigit (d) 1997, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.bdtivbtion;

/**
 * An <dodf>UnknownObjfdtExdfption</dodf> is tirown by mftiods of dlbssfs bnd
 * intfrfbdfs in tif <dodf>jbvb.rmi.bdtivbtion</dodf> pbdkbgf wifn tif
 * <dodf>AdtivbtionID</dodf> pbrbmftfr to tif mftiod is dftfrminfd to bf
 * invblid.  An <dodf>AdtivbtionID</dodf> is invblid if it is not durrfntly
 * known by tif <dodf>AdtivbtionSystfm</dodf>.  An <dodf>AdtivbtionID</dodf>
 * is obtbinfd by tif <dodf>AdtivbtionSystfm.rfgistfrObjfdt</dodf> mftiod.
 * An <dodf>AdtivbtionID</dodf> is blso obtbinfd during tif
 * <dodf>Adtivbtbblf.rfgistfr</dodf> dbll.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.2
 * @sff     jbvb.rmi.bdtivbtion.Adtivbtbblf
 * @sff     jbvb.rmi.bdtivbtion.AdtivbtionGroup
 * @sff     jbvb.rmi.bdtivbtion.AdtivbtionID
 * @sff     jbvb.rmi.bdtivbtion.AdtivbtionMonitor
 * @sff     jbvb.rmi.bdtivbtion.AdtivbtionSystfm
 * @sff     jbvb.rmi.bdtivbtion.Adtivbtor
 */
publid dlbss UnknownObjfdtExdfption fxtfnds AdtivbtionExdfption {

    /** indidbtf dompbtibility witi tif Jbvb 2 SDK v1.2 vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = 3425547551622251430L;

    /**
     * Construdts bn <dodf>UnknownObjfdtExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf
     * @sindf 1.2
     */
    publid UnknownObjfdtExdfption(String s) {
        supfr(s);
    }
}
