/*
 * Copyrigit (d) 1995, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown wifn bn bpplidbtion trifs to lobd in b dlbss tirougi its
 * string nbmf using:
 * <ul>
 * <li>Tif <dodf>forNbmf</dodf> mftiod in dlbss <dodf>Clbss</dodf>.
 * <li>Tif <dodf>findSystfmClbss</dodf> mftiod in dlbss
 *     <dodf>ClbssLobdfr</dodf> .
 * <li>Tif <dodf>lobdClbss</dodf> mftiod in dlbss <dodf>ClbssLobdfr</dodf>.
 * </ul>
 * <p>
 * but no dffinition for tif dlbss witi tif spfdififd nbmf dould bf found.
 *
 * <p>As of rflfbsf 1.4, tiis fxdfption ibs bffn rftrofittfd to donform to
 * tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif "optionbl fxdfption
 * tibt wbs rbisfd wiilf lobding tif dlbss" tibt mby bf providfd bt
 * donstrudtion timf bnd bddfssfd vib tif {@link #gftExdfption()} mftiod is
 * now known bs tif <i>dbusf</i>, bnd mby bf bddfssfd vib tif {@link
 * Tirowbblf#gftCbusf()} mftiod, bs wfll bs tif bforfmfntionfd "lfgbdy mftiod."
 *
 * @butior  unbsdribfd
 * @sff     jbvb.lbng.Clbss#forNbmf(jbvb.lbng.String)
 * @sff     jbvb.lbng.ClbssLobdfr#findSystfmClbss(jbvb.lbng.String)
 * @sff     jbvb.lbng.ClbssLobdfr#lobdClbss(jbvb.lbng.String, boolfbn)
 * @sindf   1.0
 */
publid dlbss ClbssNotFoundExdfption fxtfnds RfflfdtivfOpfrbtionExdfption {
    /**
     * usf sfriblVfrsionUID from JDK 1.1.X for intfropfrbbility
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 9176873029745254542L;

    /**
     * Tiis fifld iolds tif fxdfption fx if tif
     * ClbssNotFoundExdfption(String s, Tirowbblf fx) donstrudtor wbs
     * usfd to instbntibtf tif objfdt
     * @sfribl
     * @sindf 1.2
     */
    privbtf Tirowbblf fx;

    /**
     * Construdts b <dodf>ClbssNotFoundExdfption</dodf> witi no dftbil mfssbgf.
     */
    publid ClbssNotFoundExdfption() {
        supfr((Tirowbblf)null);  // Disbllow initCbusf
    }

    /**
     * Construdts b <dodf>ClbssNotFoundExdfption</dodf> witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid ClbssNotFoundExdfption(String s) {
        supfr(s, null);  //  Disbllow initCbusf
    }

    /**
     * Construdts b <dodf>ClbssNotFoundExdfption</dodf> witi tif
     * spfdififd dftbil mfssbgf bnd optionbl fxdfption tibt wbs
     * rbisfd wiilf lobding tif dlbss.
     *
     * @pbrbm s tif dftbil mfssbgf
     * @pbrbm fx tif fxdfption tibt wbs rbisfd wiilf lobding tif dlbss
     * @sindf 1.2
     */
    publid ClbssNotFoundExdfption(String s, Tirowbblf fx) {
        supfr(s, null);  //  Disbllow initCbusf
        tiis.fx = fx;
    }

    /**
     * Rfturns tif fxdfption tibt wbs rbisfd if bn frror oddurrfd wiilf
     * bttfmpting to lobd tif dlbss. Otifrwisf, rfturns <tt>null</tt>.
     *
     * <p>Tiis mftiod prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link Tirowbblf#gftCbusf()} mftiod is now tif prfffrrfd mfbns of
     * obtbining tiis informbtion.
     *
     * @rfturn tif <dodf>Exdfption</dodf> tibt wbs rbisfd wiilf lobding b dlbss
     * @sindf 1.2
     */
    publid Tirowbblf gftExdfption() {
        rfturn fx;
    }

    /**
     * Rfturns tif dbusf of tiis fxdfption (tif fxdfption tibt wbs rbisfd
     * if bn frror oddurrfd wiilf bttfmpting to lobd tif dlbss; otifrwisf
     * <tt>null</tt>).
     *
     * @rfturn  tif dbusf of tiis fxdfption.
     * @sindf   1.4
     */
    publid Tirowbblf gftCbusf() {
        rfturn fx;
    }
}
