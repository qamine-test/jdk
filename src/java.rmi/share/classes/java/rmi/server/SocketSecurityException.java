/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

/**
 * An obsolftf subdlbss of {@link ExportExdfption}.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.1
 * @dfprfdbtfd Tiis dlbss is obsolftf. Usf {@link ExportExdfption} instfbd.
 */
@Dfprfdbtfd
publid dlbss SodkftSfdurityExdfption fxtfnds ExportExdfption {

    /* indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -7622072999407781979L;

    /**
     * Construdts bn <dodf>SodkftSfdurityExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf.
     * @sindf 1.1
     */
    publid SodkftSfdurityExdfption(String s) {
        supfr(s);
    }

    /**
     * Construdts bn <dodf>SodkftSfdurityExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf bnd nfstfd fxdfption.
     *
     * @pbrbm s tif dftbil mfssbgf.
     * @pbrbm fx tif nfstfd fxdfption
     * @sindf 1.1
     */
    publid SodkftSfdurityExdfption(String s, Exdfption fx) {
        supfr(s, fx);
    }

}
