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
  * Tiis fxdfption is tirown wifn bn butifntidbtion frror oddurs wiilf
  * bddfssing tif nbming or dirfdtory sfrvidf.
  * An butifntidbtion frror dbn ibppfn, for fxbmplf, wifn tif drfdfntibls
  * supplifd by tif usfr progrbm brf invblid or otifrwisf fbil to
  * butifntidbtf tif usfr to tif nbming/dirfdtory sfrvidf.
  *<p>
  * If tif progrbm wbnts to ibndlf tiis fxdfption in pbrtidulbr, it
  * siould dbtdi AutifntidbtionExdfption fxpliditly bfforf bttfmpting to
  * dbtdi NbmingExdfption. Aftfr dbtdiing AutifntidbtionExdfption, tif
  * progrbm dould rfbttfmpt tif butifntidbtion by updbting
  * tif rfsolvfd dontfxt's fnvironmfnt propfrtifs witi tif bppropribtf
  * drfdfntibls.
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

publid dlbss AutifntidbtionExdfption fxtfnds NbmingSfdurityExdfption {
    /**
     * Construdts b nfw instbndf of AutifntidbtionExdfption using tif
     * fxplbnbtion supplifd. All otifr fiflds dffbult to null.
     *
     * @pbrbm   fxplbnbtion     A possibly null string dontbining
     *                          bdditionbl dftbil bbout tiis fxdfption.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid AutifntidbtionExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts b nfw instbndf of AutifntidbtionExdfption.
      * All fiflds brf sft to null.
      */
    publid AutifntidbtionExdfption() {
        supfr();
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 3678497619904568096L;
}
