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

pbdkbgf jbvbx.nbming.dirfdtory;

import jbvbx.nbming.NbmingExdfption;

/**
  * Tiis fxdfption is tirown wifn bn bttfmpt is
  * mbdf to bdd or modify bn bttributf sft tibt ibs bffn spfdififd
  * indomplftfly or indorrfdtly. Tiis dould ibppfn, for fxbmplf,
  * wifn bttfmpting to bdd or modify b binding, or to drfbtf b nfw
  * subdontfxt witiout spfdifying bll tif mbndbtory bttributfs
  * rfquirfd for drfbtion of tif objfdt.  Anotifr situbtion in
  * wiidi tiis fxdfption is tirown is by spfdifidbtion of indompbtiblf
  * bttributfs witiin tif sbmf bttributf sft, or bttributfs in donflidt
  * witi tibt spfdififd by tif objfdt's sdifmb.
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

publid dlbss InvblidAttributfsExdfption fxtfnds NbmingExdfption {
    /**
     * Construdts b nfw instbndf of InvblidAttributfsExdfption using bn
     * fxplbnbtion. All otifr fiflds brf sft to null.
     * @pbrbm   fxplbnbtion     Additionbl dftbil bbout tiis fxdfption. Cbn bf null.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid InvblidAttributfsExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts b nfw instbndf of InvblidAttributfsExdfption.
      * All fiflds brf sft to null.
      */
    publid InvblidAttributfsExdfption() {
        supfr();
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 2607612850539889765L;
}
