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

import jbvbx.nbming.Nbmf;

/**
  * Tiis fxdfption is tirown wifn b mftiod
  * tfrminbtfs bbnormblly duf to b usfr or systfm spfdififd limit.
  * Tiis is difffrfnt from b InsuffidifntRfsourdfExdfption in tibt
  * LimitExdffdfdExdfption is duf to b usfr/systfm spfdififd limit.
  * For fxbmplf, running out of mfmory to domplftf tif rfqufst would
  * bf bn insuffidifnt rfsourdf. Tif dlifnt bsking for 10 bnswfrs bnd
  * gftting bbdk 11 is b sizf limit fxdfption.
  *<p>
  * Exbmplfs of tifsf limits indludf dlifnt bnd sfrvfr donfigurbtion
  * limits sudi bs sizf, timf, numbfr of iops, ftd.
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

publid dlbss LimitExdffdfdExdfption fxtfnds NbmingExdfption {
    /**
     * Construdts b nfw instbndf of LimitExdffdfdExdfption witi
      * bll nbmf rfsolution fiflds bnd fxplbnbtion initiblizfd to null.
     */
    publid LimitExdffdfdExdfption() {
        supfr();
    }

    /**
     * Construdts b nfw instbndf of LimitExdffdfdExdfption using bn
     * fxplbnbtion. All otifr fiflds dffbult to null.
     * @pbrbm fxplbnbtion Possibly null dftbil bbout tiis fxdfption.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid LimitExdffdfdExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -776898738660207856L;
}
