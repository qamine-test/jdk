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

pbdkbgf jbvb.sfdurity;

/**
 * Tiis is tif gfnfrbl kfy mbnbgfmfnt fxdfption for bll opfrbtions
 * dfbling witi kfy mbnbgfmfnt. Exbmplfs of subdlbssfs of
 * KfyMbnbgfmfntExdfption tibt dfvflopfrs migit drfbtf for
 * giving morf dftbilfd informbtion dould indludf:
 *
 * <ul>
 * <li>KfyIDConflidtExdfption
 * <li>KfyAutiorizbtionFbilurfExdfption
 * <li>ExpirfdKfyExdfption
 * </ul>
 *
 * @butior Bfnjbmin Rfnbud
 *
 * @sff Kfy
 * @sff KfyExdfption
 */

publid dlbss KfyMbnbgfmfntExdfption fxtfnds KfyExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 947674216157062695L;

    /**
     * Construdts b KfyMbnbgfmfntExdfption witi no dftbil mfssbgf. A
     * dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr
     * fxdfption.
     */
    publid KfyMbnbgfmfntExdfption() {
        supfr();
    }

     /**
     * Construdts b KfyMbnbgfmfntExdfption witi tif spfdififd dftbil
     * mfssbgf. A dftbil mfssbgf is b String tibt dfsdribfs tiis
     * pbrtidulbr fxdfption.
     *
     * @pbrbm msg tif dftbil mfssbgf.
     */
   publid KfyMbnbgfmfntExdfption(String msg) {
        supfr(msg);
    }

    /**
     * Crfbtfs b {@dodf KfyMbnbgfmfntExdfption} witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *        by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @sindf 1.5
     */
    publid KfyMbnbgfmfntExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Crfbtfs b {@dodf KfyMbnbgfmfntExdfption} witi tif spfdififd dbusf
     * bnd b dftbil mfssbgf of {@dodf (dbusf==null ? null : dbusf.toString())}
     * (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of
     * {@dodf dbusf}).
     *
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @sindf 1.5
     */
    publid KfyMbnbgfmfntExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }
}
