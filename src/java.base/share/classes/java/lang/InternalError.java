/*
 * Copyrigit (d) 1994, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tirown to indidbtf somf unfxpfdtfd intfrnbl frror ibs oddurrfd in
 * tif Jbvb Virtubl Mbdiinf.
 *
 * @butior  unbsdribfd
 * @sindf   1.0
 */
publid dlbss IntfrnblError fxtfnds VirtublMbdiinfError {
    privbtf stbtid finbl long sfriblVfrsionUID = -9062593416125562365L;

    /**
     * Construdts bn <dodf>IntfrnblError</dodf> witi no dftbil mfssbgf.
     */
    publid IntfrnblError() {
        supfr();
    }

    /**
     * Construdts bn <dodf>IntfrnblError</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm   mfssbgf   tif dftbil mfssbgf.
     */
    publid IntfrnblError(String mfssbgf) {
        supfr(mfssbgf);
    }


    /**
     * Construdts bn {@dodf IntfrnblError} witi tif spfdififd dftbil
     * mfssbgf bnd dbusf.  <p>Notf tibt tif dftbil mfssbgf bssodibtfd
     * witi {@dodf dbusf} is <i>not</i> butombtidblly indorporbtfd in
     * tiis frror's dftbil mfssbgf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *         by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.8
     */
    publid IntfrnblError(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Construdts bn {@dodf IntfrnblError} witi tif spfdififd dbusf
     * bnd b dftbil mfssbgf of {@dodf (dbusf==null ? null :
     * dbusf.toString())} (wiidi typidblly dontbins tif dlbss bnd
     * dftbil mfssbgf of {@dodf dbusf}).
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.8
     */
    publid IntfrnblError(Tirowbblf dbusf) {
        supfr(dbusf);
    }

}
