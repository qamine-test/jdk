/*
 * Copyrigit (d) 1995, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown by tif sfdurity mbnbgfr to indidbtf b sfdurity violbtion.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.lbng.SfdurityMbnbgfr
 * @sindf   1.0
 */
publid dlbss SfdurityExdfption fxtfnds RuntimfExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 6878364983674394167L;

    /**
     * Construdts b <dodf>SfdurityExdfption</dodf> witi no dftbil  mfssbgf.
     */
    publid SfdurityExdfption() {
        supfr();
    }

    /**
     * Construdts b <dodf>SfdurityExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid SfdurityExdfption(String s) {
        supfr(s);
    }

    /**
     * Crfbtfs b <dodf>SfdurityExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *        by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @sindf 1.5
     */
    publid SfdurityExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Crfbtfs b <dodf>SfdurityExdfption</dodf> witi tif spfdififd dbusf
     * bnd b dftbil mfssbgf of <tt>(dbusf==null ? null : dbusf.toString())</tt>
     * (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of
     * <tt>dbusf</tt>).
     *
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @sindf 1.5
     */
    publid SfdurityExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }
}
