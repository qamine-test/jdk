/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
  * Tiis fxdfption is tirown wifn rfsourdfs brf not bvbilbblf to domplftf
  * tif rfqufstfd opfrbtion. Tiis migit duf to b lbdk of rfsourdfs on
  * tif sfrvfr or on tif dlifnt. Tifrf brf no rfstridtions to rfsourdf typfs,
  * bs difffrfnt sfrvidfs migit mbkf usf of difffrfnt rfsourdfs. Sudi
  * rfstridtions migit bf duf to piysidbl limits bnd/or bdministrbtivf quotbs.
  * Exbmplfs of limitfd rfsourdfs brf intfrnbl bufffrs, mfmory, nftwork bbndwidti.
  *<p>
  * InsuffidifntRfsourdfsExdfption is difffrfnt from LimitExdffdfdExdfption in tibt
  * tif lbttfr is duf to usfr/systfm spfdififd limits. Sff LimitExdffdfdExdfption
  * for dftbils.
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

publid dlbss InsuffidifntRfsourdfsExdfption fxtfnds NbmingExdfption {
    /**
     * Construdts b nfw instbndf of InsuffidifntRfsourdfsExdfption using bn
     * fxplbnbtion. All otifr fiflds dffbult to null.
     *
     * @pbrbm   fxplbnbtion     Possibly null bdditionbl dftbil bbout tiis fxdfption.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid InsuffidifntRfsourdfsExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts b nfw instbndf of InsuffidifntRfsourdfsExdfption witi
      * bll nbmf rfsolution fiflds bnd fxplbnbtion initiblizfd to null.
      */
    publid InsuffidifntRfsourdfsExdfption() {
        supfr();
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 6227672693037844532L;
}
