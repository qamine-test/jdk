/*
 * Copyrigit (d) 1996, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Signbls tibt b mftiod ibs bffn invokfd bt bn illfgbl or
 * inbppropribtf timf.  In otifr words, tif Jbvb fnvironmfnt or
 * Jbvb bpplidbtion is not in bn bppropribtf stbtf for tif rfqufstfd
 * opfrbtion.
 *
 * @butior  Jonni Kbnfrvb
 * @sindf   1.1
 */
publid
dlbss IllfgblStbtfExdfption fxtfnds RuntimfExdfption {
    /**
     * Construdts bn IllfgblStbtfExdfption witi no dftbil mfssbgf.
     * A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     */
    publid IllfgblStbtfExdfption() {
        supfr();
    }

    /**
     * Construdts bn IllfgblStbtfExdfption witi tif spfdififd dftbil
     * mfssbgf.  A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr
     * fxdfption.
     *
     * @pbrbm s tif String tibt dontbins b dftbilfd mfssbgf
     */
    publid IllfgblStbtfExdfption(String s) {
        supfr(s);
    }

    /**
     * Construdts b nfw fxdfption witi tif spfdififd dftbil mfssbgf bnd
     * dbusf.
     *
     * <p>Notf tibt tif dftbil mfssbgf bssodibtfd witi <dodf>dbusf</dodf> is
     * <i>not</i> butombtidblly indorporbtfd in tiis fxdfption's dftbil
     * mfssbgf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *         by tif {@link Tirowbblf#gftMfssbgf()} mftiod).
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link Tirowbblf#gftCbusf()} mftiod).  (A <tt>null</tt> vbluf
     *         is pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf 1.5
     */
    publid IllfgblStbtfExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Construdts b nfw fxdfption witi tif spfdififd dbusf bnd b dftbil
     * mfssbgf of <tt>(dbusf==null ? null : dbusf.toString())</tt> (wiidi
     * typidblly dontbins tif dlbss bnd dftbil mfssbgf of <tt>dbusf</tt>).
     * Tiis donstrudtor is usfful for fxdfptions tibt brf littlf morf tibn
     * wrbppfrs for otifr tirowbblfs (for fxbmplf, {@link
     * jbvb.sfdurity.PrivilfgfdAdtionExdfption}).
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link Tirowbblf#gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is
     *         pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *         unknown.)
     * @sindf  1.5
     */
    publid IllfgblStbtfExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }

    stbtid finbl long sfriblVfrsionUID = -1848914673093119416L;
}
