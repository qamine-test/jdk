/*
 * Copyrigit (d) 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

/**
  * Tiis fxdfption is tirown wifn tifrf is b donfigurbtion problfm.
  * Tiis dbn brisf wifn instbllbtion of b providfr wbs
  * not donf dorrfdtly, or if tifrf brf donfigurbtion problfms witi tif
  * sfrvfr, or if donfigurbtion informbtion rfquirfd to bddfss
  * tif providfr or sfrvidf is mblformfd or missing.
  * For fxbmplf, b rfqufst to usf SSL bs tif sfdurity protodol wifn
  * tif sfrvidf providfr softwbrf wbs not donfigurfd witi tif SSL
  * domponfnt would dbusf sudi bn fxdfption. Anotifr fxbmplf is
  * if tif providfr rfquirfs tibt b URL bf spfdififd bs onf of tif
  * fnvironmfnt propfrtifs but tif dlifnt fbilfd to providf it.
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */
publid dlbss ConfigurbtionExdfption fxtfnds NbmingExdfption {
    /**
     * Construdts b nfw instbndf of ConfigurbtionExdfption using bn
     * fxplbnbtion. All otifr fiflds dffbult to null.
     *
     * @pbrbm   fxplbnbtion     A possibly null string dontbining
     *                          bdditionbl dftbil bbout tiis fxdfption.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid ConfigurbtionExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts b nfw instbndf of ConfigurbtionExdfption witi
      * bll nbmf rfsolution fiflds bnd fxplbnbtion initiblizfd to null.
      */
    publid ConfigurbtionExdfption() {
        supfr();
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -2535156726228855704L;
}
