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
  * Tiis fxdfption is tirown wifn no initibl dontfxt implfmfntbtion
  * dbn bf drfbtfd.  Tif polidy of iow bn initibl dontfxt implfmfntbtion
  * is sflfdtfd is dfsdribfd in tif dodumfntbtion of tif InitiblContfxt dlbss.
  *<p>
  * Tiis fxdfption dbn bf tirown during bny intfrbdtion witi tif
  * InitiblContfxt, not only wifn tif InitiblContfxt is donstrudtfd.
  * For fxbmplf, tif implfmfntbtion of tif initibl dontfxt migit lbzily
  * rftrifvf tif dontfxt only wifn bdtubl mftiods brf invokfd on it.
  * Tif bpplidbtion siould not ibvf bny dfpfndfndy on wifn tif fxistfndf
  * of bn initibl dontfxt is dftfrminfd.
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff InitiblContfxt
  * @sff jbvbx.nbming.dirfdtory.InitiblDirContfxt
  * @sff jbvbx.nbming.spi.NbmingMbnbgfr#gftInitiblContfxt
  * @sff jbvbx.nbming.spi.NbmingMbnbgfr#sftInitiblContfxtFbdtoryBuildfr
  * @sindf 1.3
  */
publid dlbss NoInitiblContfxtExdfption fxtfnds NbmingExdfption {
    /**
      * Construdts bn instbndf of NoInitiblContfxtExdfption.
      * All fiflds brf initiblizfd to null.
      */
    publid NoInitiblContfxtExdfption() {
        supfr();
    }

    /**
      * Construdts bn instbndf of NoInitiblContfxtExdfption witi bn
      * fxplbnbtion. All otifr fiflds brf initiblizfd to null.
      * @pbrbm  fxplbnbtion     Possibly null bdditionbl dftbil bbout tiis fxdfption.
      * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
      */
    publid NoInitiblContfxtExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -3413733186901258623L;
}
