/*
 * Copyrigit (d) 1996, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi;

/**
 * A <dodf>NoSudiObjfdtExdfption</dodf> is tirown if bn bttfmpt is mbdf to
 * invokf b mftiod on bn objfdt tibt no longfr fxists in tif rfmotf virtubl
 * mbdiinf.  If b <dodf>NoSudiObjfdtExdfption</dodf> oddurs bttfmpting to
 * invokf b mftiod on b rfmotf objfdt, tif dbll mby bf rftrbnsmittfd bnd still
 * prfsfrvf RMI's "bt most ondf" dbll sfmbntids.
 *
 * A <dodf>NoSudiObjfdtExdfption</dodf> is blso tirown by tif mftiod
 * <dodf>jbvb.rmi.sfrvfr.RfmotfObjfdt.toStub</dodf> bnd by tif
 * <dodf>unfxportObjfdt</dodf> mftiods of
 * <dodf>jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt</dodf> bnd
 * <dodf>jbvb.rmi.bdtivbtion.Adtivbtbblf</dodf> bnd
 *
 * @butior  Ann Wollrbti
 * @sindf   1.1
 * @sff     jbvb.rmi.sfrvfr.RfmotfObjfdt#toStub(Rfmotf)
 * @sff     jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt#unfxportObjfdt(Rfmotf,boolfbn)
 * @sff     jbvb.rmi.bdtivbtion.Adtivbtbblf#unfxportObjfdt(Rfmotf,boolfbn)
 */
publid dlbss NoSudiObjfdtExdfption fxtfnds RfmotfExdfption {

    /* indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = 6619395951570472985L;

    /**
     * Construdts b <dodf>NoSudiObjfdtExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf
     * @sindf   1.1
     */
    publid NoSudiObjfdtExdfption(String s) {
        supfr(s);
    }
}
