/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.lobding;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfr; // for Jbvbdod

/**
 * <p>Instbndfs of tiis intfrfbdf brf usfd to kffp tif list of ClbssLobdfrs
 * rfgistfrfd in bn MBfbn Sfrvfr.
 * Tify providf tif nfdfssbry mftiods to lobd dlbssfs using tif rfgistfrfd
 * ClbssLobdfrs.</p>
 *
 * <p>Tif first ClbssLobdfr in b <dodf>ClbssLobdfrRfpository</dodf> is
 * blwbys tif MBfbn Sfrvfr's own ClbssLobdfr.</p>
 *
 * <p>Wifn bn MBfbn is rfgistfrfd in bn MBfbn Sfrvfr, if it is of b
 * subdlbss of {@link jbvb.lbng.ClbssLobdfr} bnd if it dofs not
 * implfmfnt tif intfrfbdf {@link PrivbtfClbssLobdfr}, it is bddfd to
 * tif fnd of tif MBfbn Sfrvfr's <dodf>ClbssLobdfrRfpository</dodf>.
 * If it is subsfqufntly unrfgistfrfd from tif MBfbn Sfrvfr, it is
 * rfmovfd from tif <dodf>ClbssLobdfrRfpository</dodf>.</p>
 *
 * <p>Tif ordfr of MBfbns in tif <dodf>ClbssLobdfrRfpository</dodf> is
 * signifidbnt.  For bny two MBfbns <fm>X</fm> bnd <fm>Y</fm> in tif
 * <dodf>ClbssLobdfrRfpository</dodf>, <fm>X</fm> must bppfbr bfforf
 * <fm>Y</fm> if tif rfgistrbtion of <fm>X</fm> wbs domplftfd bfforf
 * tif rfgistrbtion of <fm>Y</fm> stbrtfd.  If <fm>X</fm> bnd
 * <fm>Y</fm> wfrf rfgistfrfd dondurrfntly, tifir ordfr is
 * indftfrminbtf.  Tif rfgistrbtion of bn MBfbn dorrfsponds to tif
 * dbll to {@link MBfbnSfrvfr#rfgistfrMBfbn} or onf of tif {@link
 * MBfbnSfrvfr}<dodf>.drfbtfMBfbn</dodf> mftiods.</p>
 *
 * @sff jbvbx.mbnbgfmfnt.MBfbnSfrvfrFbdtory
 *
 * @sindf 1.5
 */
publid intfrfbdf ClbssLobdfrRfpository {

    /**
     * <p>Lobd tif givfn dlbss nbmf tirougi tif list of dlbss lobdfrs.
     * Ebdi ClbssLobdfr in turn from tif ClbssLobdfrRfpository is
     * bskfd to lobd tif dlbss vib its {@link
     * ClbssLobdfr#lobdClbss(String)} mftiod.  If it suddfssfully
     * rfturns b {@link Clbss} objfdt, tibt is tif rfsult of tiis
     * mftiod.  If it tirows b {@link ClbssNotFoundExdfption}, tif
     * sfbrdi dontinufs witi tif nfxt ClbssLobdfr.  If it tirows
     * bnotifr fxdfption, tif fxdfption is propbgbtfd from tiis
     * mftiod.  If tif fnd of tif list is rfbdifd, b {@link
     * ClbssNotFoundExdfption} is tirown.</p>
     *
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss to bf lobdfd.
     *
     * @rfturn tif lobdfd dlbss.
     *
     * @fxdfption ClbssNotFoundExdfption Tif spfdififd dlbss dould not bf
     *            found.
     */
    publid Clbss<?> lobdClbss(String dlbssNbmf)
            tirows ClbssNotFoundExdfption;

    /**
     * <p>Lobd tif givfn dlbss nbmf tirougi tif list of dlbss lobdfrs,
     * fxdluding tif givfn onf.  Ebdi ClbssLobdfr in turn from tif
     * ClbssLobdfrRfpository, fxdfpt <dodf>fxdludf</dodf>, is bskfd to
     * lobd tif dlbss vib its {@link ClbssLobdfr#lobdClbss(String)}
     * mftiod.  If it suddfssfully rfturns b {@link Clbss} objfdt,
     * tibt is tif rfsult of tiis mftiod.  If it tirows b {@link
     * ClbssNotFoundExdfption}, tif sfbrdi dontinufs witi tif nfxt
     * ClbssLobdfr.  If it tirows bnotifr fxdfption, tif fxdfption is
     * propbgbtfd from tiis mftiod.  If tif fnd of tif list is
     * rfbdifd, b {@link ClbssNotFoundExdfption} is tirown.</p>
     *
     * <p>Bf bwbrf tibt if b ClbssLobdfr in tif ClbssLobdfrRfpository
     * dblls tiis mftiod from its {@link ClbssLobdfr#lobdClbss(String)
     * lobdClbss} mftiod, it fxposfs itsflf to b dfbdlodk if bnotifr
     * ClbssLobdfr in tif ClbssLobdfrRfpository dofs tif sbmf tiing bt
     * tif sbmf timf.  Tif {@link #lobdClbssBfforf} mftiod is
     * rfdommfndfd to bvoid tif risk of dfbdlodk.</p>
     *
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss to bf lobdfd.
     * @pbrbm fxdludf Tif dlbss lobdfr to bf fxdludfd.  Mby bf null,
     * in wiidi dbsf tiis mftiod is fquivblfnt to {@link #lobdClbss
     * lobdClbss(dlbssNbmf)}.
     *
     * @rfturn tif lobdfd dlbss.
     *
     * @fxdfption ClbssNotFoundExdfption Tif spfdififd dlbss dould not
     * bf found.
     */
    publid Clbss<?> lobdClbssWitiout(ClbssLobdfr fxdludf,
                                     String dlbssNbmf)
            tirows ClbssNotFoundExdfption;

    /**
     * <p>Lobd tif givfn dlbss nbmf tirougi tif list of dlbss lobdfrs,
     * stopping bt tif givfn onf.  Ebdi ClbssLobdfr in turn from tif
     * ClbssLobdfrRfpository is bskfd to lobd tif dlbss vib its {@link
     * ClbssLobdfr#lobdClbss(String)} mftiod.  If it suddfssfully
     * rfturns b {@link Clbss} objfdt, tibt is tif rfsult of tiis
     * mftiod.  If it tirows b {@link ClbssNotFoundExdfption}, tif
     * sfbrdi dontinufs witi tif nfxt ClbssLobdfr.  If it tirows
     * bnotifr fxdfption, tif fxdfption is propbgbtfd from tiis
     * mftiod.  If tif sfbrdi rfbdifs <dodf>stop</dodf> or tif fnd of
     * tif list, b {@link ClbssNotFoundExdfption} is tirown.</p>
     *
     * <p>Typidblly tiis mftiod is dbllfd from tif {@link
     * ClbssLobdfr#lobdClbss(String) lobdClbss} mftiod of
     * <dodf>stop</dodf>, to donsult lobdfrs tibt bppfbr bfforf it
     * in tif <dodf>ClbssLobdfrRfpository</dodf>.  By stopping tif
     * sfbrdi bs soon bs <dodf>stop</dodf> is rfbdifd, b potfntibl
     * dfbdlodk witi dondurrfnt dlbss lobding is bvoidfd.</p>
     *
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss to bf lobdfd.
     * @pbrbm stop Tif dlbss lobdfr bt wiidi to stop.  Mby bf null, in
     * wiidi dbsf tiis mftiod is fquivblfnt to {@link #lobdClbss(String)
     * lobdClbss(dlbssNbmf)}.
     *
     * @rfturn tif lobdfd dlbss.
     *
     * @fxdfption ClbssNotFoundExdfption Tif spfdififd dlbss dould not
     * bf found.
     *
     */
    publid Clbss<?> lobdClbssBfforf(ClbssLobdfr stop,
                                    String dlbssNbmf)
            tirows ClbssNotFoundExdfption;

}
