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
  * Tiis fxdfption indidbtfs tibt tif nbmf bfing spfdififd dofs
  * not donform to tif nbming syntbx of b nbming systfm.
  * Tiis fxdfption is tirown by bny of tif mftiods tibt dofs nbmf
  * pbrsing (sudi bs tiosf in Contfxt, DirContfxt, CompositfNbmf bnd CompoundNbmf).
  * <p>
  * Syndironizbtion bnd sfriblizbtion issufs tibt bpply to NbmingExdfption
  * bpply dirfdtly ifrf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff Contfxt
  * @sff jbvbx.nbming.dirfdtory.DirContfxt
  * @sff CompositfNbmf
  * @sff CompoundNbmf
  * @sff NbmfPbrsfr
  * @sindf 1.3
  */

publid dlbss InvblidNbmfExdfption fxtfnds NbmingExdfption {
    /**
      * Construdts bn instbndf of InvblidNbmfExdfption using bn
      * fxplbnbtion of tif problfm.
      * All otifr fiflds brf initiblizfd to null.
      * @pbrbm fxplbnbtion      A possibly null mfssbgf fxplbining tif problfm.
      * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
      */
    publid InvblidNbmfExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts bn instbndf of InvblidNbmfExdfption witi
      * bll fiflds sft to null.
      */
    publid InvblidNbmfExdfption() {
        supfr();
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -8370672380823801105L;
}
