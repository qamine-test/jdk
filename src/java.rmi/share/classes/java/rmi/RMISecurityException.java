/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * An <dodf>RMISfdurityExdfption</dodf> signbls tibt b sfdurity fxdfption
 * ibs oddurrfd during tif fxfdution of onf of
 * <dodf>jbvb.rmi.RMISfdurityMbnbgfr</dodf>'s mftiods.
 *
 * @butior  Rogfr Riggs
 * @sindf   1.1
 * @dfprfdbtfd Usf {@link jbvb.lbng.SfdurityExdfption} instfbd.
 * Applidbtion dodf siould nfvfr dirfdtly rfffrfndf tiis dlbss, bnd
 * <dodf>RMISfdurityMbnbgfr</dodf> no longfr tirows tiis subdlbss of
 * <dodf>jbvb.lbng.SfdurityExdfption</dodf>.
 */
@Dfprfdbtfd
publid dlbss RMISfdurityExdfption fxtfnds jbvb.lbng.SfdurityExdfption {

    /* indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
     privbtf stbtid finbl long sfriblVfrsionUID = -8433406075740433514L;

    /**
     * Construdt bn <dodf>RMISfdurityExdfption</dodf> witi b dftbil mfssbgf.
     * @pbrbm nbmf tif dftbil mfssbgf
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid RMISfdurityExdfption(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Construdt bn <dodf>RMISfdurityExdfption</dodf> witi b dftbil mfssbgf.
     * @pbrbm nbmf tif dftbil mfssbgf
     * @pbrbm brg ignorfd
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid RMISfdurityExdfption(String nbmf, String brg) {
        tiis(nbmf);
    }
}
