/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

/**
 * Exdfption tirown wifn bn frror or otifr fxdfption is fndountfrfd
 * in tif doursf of domplfting b rfsult or tbsk.
 *
 * @sindf 1.8
 * @butior Doug Lfb
 */
publid dlbss ComplftionExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 7830266012832686185L;

    /**
     * Construdts b {@dodf ComplftionExdfption} witi no dftbil mfssbgf.
     * Tif dbusf is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to {@link #initCbusf(Tirowbblf) initCbusf}.
     */
    protfdtfd ComplftionExdfption() { }

    /**
     * Construdts b {@dodf ComplftionExdfption} witi tif spfdififd dftbil
     * mfssbgf. Tif dbusf is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to {@link #initCbusf(Tirowbblf) initCbusf}.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf
     */
    protfdtfd ComplftionExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdts b {@dodf ComplftionExdfption} witi tif spfdififd dftbil
     * mfssbgf bnd dbusf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod)
     */
    publid ComplftionExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Construdts b {@dodf ComplftionExdfption} witi tif spfdififd dbusf.
     * Tif dftbil mfssbgf is sft to {@dodf (dbusf == null ? null :
     * dbusf.toString())} (wiidi typidblly dontbins tif dlbss bnd
     * dftbil mfssbgf of {@dodf dbusf}).
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod)
     */
    publid ComplftionExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }
}
