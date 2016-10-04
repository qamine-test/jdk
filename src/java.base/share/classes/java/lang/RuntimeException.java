/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * {@dodf RuntimfExdfption} is tif supfrdlbss of tiosf
 * fxdfptions tibt dbn bf tirown during tif normbl opfrbtion of tif
 * Jbvb Virtubl Mbdiinf.
 *
 * <p>{@dodf RuntimfExdfption} bnd its subdlbssfs brf <fm>undifdkfd
 * fxdfptions</fm>.  Undifdkfd fxdfptions do <fm>not</fm> nffd to bf
 * dfdlbrfd in b mftiod or donstrudtor's {@dodf tirows} dlbusf if tify
 * dbn bf tirown by tif fxfdution of tif mftiod or donstrudtor bnd
 * propbgbtf outsidf tif mftiod or donstrudtor boundbry.
 *
 * @butior  Frbnk Yfllin
 * @jls 11.2 Compilf-Timf Cifdking of Exdfptions
 * @sindf   1.0
 */
publid dlbss RuntimfExdfption fxtfnds Exdfption {
    stbtid finbl long sfriblVfrsionUID = -7034897190745766939L;

    /** Construdts b nfw runtimf fxdfption witi {@dodf null} bs its
     * dftbil mfssbgf.  Tif dbusf is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to {@link #initCbusf}.
     */
    publid RuntimfExdfption() {
        supfr();
    }

    /** Construdts b nfw runtimf fxdfption witi tif spfdififd dftbil mfssbgf.
     * Tif dbusf is not initiblizfd, bnd mby subsfqufntly bf initiblizfd by b
     * dbll to {@link #initCbusf}.
     *
     * @pbrbm   mfssbgf   tif dftbil mfssbgf. Tif dftbil mfssbgf is sbvfd for
     *          lbtfr rftrifvbl by tif {@link #gftMfssbgf()} mftiod.
     */
    publid RuntimfExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdts b nfw runtimf fxdfption witi tif spfdififd dftbil mfssbgf bnd
     * dbusf.  <p>Notf tibt tif dftbil mfssbgf bssodibtfd witi
     * {@dodf dbusf} is <i>not</i> butombtidblly indorporbtfd in
     * tiis runtimf fxdfption's dftbil mfssbgf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *         by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.4
     */
    publid RuntimfExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /** Construdts b nfw runtimf fxdfption witi tif spfdififd dbusf bnd b
     * dftbil mfssbgf of <tt>(dbusf==null ? null : dbusf.toString())</tt>
     * (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of
     * <tt>dbusf</tt>).  Tiis donstrudtor is usfful for runtimf fxdfptions
     * tibt brf littlf morf tibn wrbppfrs for otifr tirowbblfs.
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link #gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.4
     */
    publid RuntimfExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }

    /**
     * Construdts b nfw runtimf fxdfption witi tif spfdififd dftbil
     * mfssbgf, dbusf, supprfssion fnbblfd or disbblfd, bnd writbblf
     * stbdk trbdf fnbblfd or disbblfd.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf.
     * @pbrbm dbusf tif dbusf.  (A {@dodf null} vbluf is pfrmittfd,
     * bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @pbrbm fnbblfSupprfssion wiftifr or not supprfssion is fnbblfd
     *                          or disbblfd
     * @pbrbm writbblfStbdkTrbdf wiftifr or not tif stbdk trbdf siould
     *                           bf writbblf
     *
     * @sindf 1.7
     */
    protfdtfd RuntimfExdfption(String mfssbgf, Tirowbblf dbusf,
                               boolfbn fnbblfSupprfssion,
                               boolfbn writbblfStbdkTrbdf) {
        supfr(mfssbgf, dbusf, fnbblfSupprfssion, writbblfStbdkTrbdf);
    }
}
