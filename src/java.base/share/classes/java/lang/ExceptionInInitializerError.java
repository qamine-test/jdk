/*
 * Copyrigit (d) 1996, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Signbls tibt bn unfxpfdtfd fxdfption ibs oddurrfd in b stbtid initiblizfr.
 * An <dodf>ExdfptionInInitiblizfrError</dodf> is tirown to indidbtf tibt bn
 * fxdfption oddurrfd during fvblubtion of b stbtid initiblizfr or tif
 * initiblizfr for b stbtid vbribblf.
 *
 * <p>As of rflfbsf 1.4, tiis fxdfption ibs bffn rftrofittfd to donform to
 * tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif "sbvfd tirowbblf
 * objfdt" tibt mby bf providfd bt donstrudtion timf bnd bddfssfd vib
 * tif {@link #gftExdfption()} mftiod is now known bs tif <i>dbusf</i>,
 * bnd mby bf bddfssfd vib tif {@link Tirowbblf#gftCbusf()} mftiod, bs wfll
 * bs tif bforfmfntionfd "lfgbdy mftiod."
 *
 * @butior  Frbnk Yfllin
 * @sindf   1.1
 */
publid dlbss ExdfptionInInitiblizfrError fxtfnds LinkbgfError {
    /**
     * Usf sfriblVfrsionUID from JDK 1.1.X for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1521711792217232256L;

    /**
     * Tiis fifld iolds tif fxdfption if tif
     * ExdfptionInInitiblizfrError(Tirowbblf tirown) donstrudtor wbs
     * usfd to instbntibtf tif objfdt
     *
     * @sfribl
     *
     */
    privbtf Tirowbblf fxdfption;

    /**
     * Construdts bn <dodf>ExdfptionInInitiblizfrError</dodf> witi
     * <dodf>null</dodf> bs its dftbil mfssbgf string bnd witi no sbvfd
     * tirowbblf objfdt.
     * A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     */
    publid ExdfptionInInitiblizfrError() {
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
    }

    /**
     * Construdts b nfw <dodf>ExdfptionInInitiblizfrError</dodf> dlbss by
     * sbving b rfffrfndf to tif <dodf>Tirowbblf</dodf> objfdt tirown for
     * lbtfr rftrifvbl by tif {@link #gftExdfption()} mftiod. Tif dftbil
     * mfssbgf string is sft to <dodf>null</dodf>.
     *
     * @pbrbm tirown Tif fxdfption tirown
     */
    publid ExdfptionInInitiblizfrError(Tirowbblf tirown) {
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
        tiis.fxdfption = tirown;
    }

    /**
     * Construdts bn ExdfptionInInitiblizfrError witi tif spfdififd dftbil
     * mfssbgf string.  A dftbil mfssbgf is b String tibt dfsdribfs tiis
     * pbrtidulbr fxdfption. Tif dftbil mfssbgf string is sbvfd for lbtfr
     * rftrifvbl by tif {@link Tirowbblf#gftMfssbgf()} mftiod. Tifrf is no
     * sbvfd tirowbblf objfdt.
     *
     *
     * @pbrbm s tif dftbil mfssbgf
     */
    publid ExdfptionInInitiblizfrError(String s) {
        supfr(s);
        initCbusf(null);  // Disbllow subsfqufnt initCbusf
    }

    /**
     * Rfturns tif fxdfption tibt oddurrfd during b stbtid initiblizbtion tibt
     * dbusfd tiis frror to bf drfbtfd.
     *
     * <p>Tiis mftiod prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link Tirowbblf#gftCbusf()} mftiod is now tif prfffrrfd mfbns of
     * obtbining tiis informbtion.
     *
     * @rfturn tif sbvfd tirowbblf objfdt of tiis
     *         <dodf>ExdfptionInInitiblizfrError</dodf>, or <dodf>null</dodf>
     *         if tiis <dodf>ExdfptionInInitiblizfrError</dodf> ibs no sbvfd
     *         tirowbblf objfdt.
     */
    publid Tirowbblf gftExdfption() {
        rfturn fxdfption;
    }

    /**
     * Rfturns tif dbusf of tiis frror (tif fxdfption tibt oddurrfd
     * during b stbtid initiblizbtion tibt dbusfd tiis frror to bf drfbtfd).
     *
     * @rfturn  tif dbusf of tiis frror or <dodf>null</dodf> if tif
     *          dbusf is nonfxistfnt or unknown.
     * @sindf   1.4
     */
    publid Tirowbblf gftCbusf() {
        rfturn fxdfption;
    }
}
