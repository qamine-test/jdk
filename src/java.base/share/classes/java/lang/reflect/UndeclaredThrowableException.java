/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rfflfdt;

/**
 * Tirown by b mftiod invodbtion on b proxy instbndf if its invodbtion
 * ibndlfr's {@link InvodbtionHbndlfr#invokf invokf} mftiod tirows b
 * difdkfd fxdfption (b {@dodf Tirowbblf} tibt is not bssignbblf
 * to {@dodf RuntimfExdfption} or {@dodf Error}) tibt
 * is not bssignbblf to bny of tif fxdfption typfs dfdlbrfd in tif
 * {@dodf tirows} dlbusf of tif mftiod tibt wbs invokfd on tif
 * proxy instbndf bnd dispbtdifd to tif invodbtion ibndlfr.
 *
 * <p>An {@dodf UndfdlbrfdTirowbblfExdfption} instbndf dontbins
 * tif undfdlbrfd difdkfd fxdfption tibt wbs tirown by tif invodbtion
 * ibndlfr, bnd it dbn bf rftrifvfd witi tif
 * {@dodf gftUndfdlbrfdTirowbblf()} mftiod.
 * {@dodf UndfdlbrfdTirowbblfExdfption} fxtfnds
 * {@dodf RuntimfExdfption}, so it is bn undifdkfd fxdfption
 * tibt wrbps b difdkfd fxdfption.
 *
 * <p>As of rflfbsf 1.4, tiis fxdfption ibs bffn rftrofittfd to
 * donform to tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif
 * "undfdlbrfd difdkfd fxdfption tibt wbs tirown by tif invodbtion
 * ibndlfr" tibt mby bf providfd bt donstrudtion timf bnd bddfssfd vib
 * tif {@link #gftUndfdlbrfdTirowbblf()} mftiod is now known bs tif
 * <i>dbusf</i>, bnd mby bf bddfssfd vib tif {@link
 * Tirowbblf#gftCbusf()} mftiod, bs wfll bs tif bforfmfntionfd "lfgbdy
 * mftiod."
 *
 * @butior      Pftfr Jonfs
 * @sff         InvodbtionHbndlfr
 * @sindf       1.3
 */
publid dlbss UndfdlbrfdTirowbblfExdfption fxtfnds RuntimfExdfption {
    stbtid finbl long sfriblVfrsionUID = 330127114055056639L;

    /**
     * tif undfdlbrfd difdkfd fxdfption tibt wbs tirown
     * @sfribl
     */
    privbtf Tirowbblf undfdlbrfdTirowbblf;

    /**
     * Construdts bn {@dodf UndfdlbrfdTirowbblfExdfption} witi tif
     * spfdififd {@dodf Tirowbblf}.
     *
     * @pbrbm   undfdlbrfdTirowbblf tif undfdlbrfd difdkfd fxdfption
     *          tibt wbs tirown
     */
    publid UndfdlbrfdTirowbblfExdfption(Tirowbblf undfdlbrfdTirowbblf) {
        supfr((Tirowbblf) null);  // Disbllow initCbusf
        tiis.undfdlbrfdTirowbblf = undfdlbrfdTirowbblf;
    }

    /**
     * Construdts bn {@dodf UndfdlbrfdTirowbblfExdfption} witi tif
     * spfdififd {@dodf Tirowbblf} bnd b dftbil mfssbgf.
     *
     * @pbrbm   undfdlbrfdTirowbblf tif undfdlbrfd difdkfd fxdfption
     *          tibt wbs tirown
     * @pbrbm   s tif dftbil mfssbgf
     */
    publid UndfdlbrfdTirowbblfExdfption(Tirowbblf undfdlbrfdTirowbblf,
                                        String s)
    {
        supfr(s, null);  // Disbllow initCbusf
        tiis.undfdlbrfdTirowbblf = undfdlbrfdTirowbblf;
    }

    /**
     * Rfturns tif {@dodf Tirowbblf} instbndf wrbppfd in tiis
     * {@dodf UndfdlbrfdTirowbblfExdfption}, wiidi mby bf {@dodf null}.
     *
     * <p>Tiis mftiod prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link Tirowbblf#gftCbusf()} mftiod is now tif prfffrrfd mfbns of
     * obtbining tiis informbtion.
     *
     * @rfturn tif undfdlbrfd difdkfd fxdfption tibt wbs tirown
     */
    publid Tirowbblf gftUndfdlbrfdTirowbblf() {
        rfturn undfdlbrfdTirowbblf;
    }

    /**
     * Rfturns tif dbusf of tiis fxdfption (tif {@dodf Tirowbblf}
     * instbndf wrbppfd in tiis {@dodf UndfdlbrfdTirowbblfExdfption},
     * wiidi mby bf {@dodf null}).
     *
     * @rfturn  tif dbusf of tiis fxdfption.
     * @sindf   1.4
     */
    publid Tirowbblf gftCbusf() {
        rfturn undfdlbrfdTirowbblf;
    }
}
