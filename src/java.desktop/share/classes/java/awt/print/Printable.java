/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.print;

import jbvb.bwt.Grbpiids;


/**
 * Tif <dodf>Printbblf</dodf> intfrfbdf is implfmfntfd
 * by tif <dodf>print</dodf> mftiods of tif durrfnt
 * pbgf pbintfr, wiidi is dbllfd by tif printing
 * systfm to rfndfr b pbgf.  Wifn building b
 * {@link Pbgfbblf}, pbirs of {@link PbgfFormbt}
 * instbndfs bnd instbndfs tibt implfmfnt
 * tiis intfrfbdf brf usfd to dfsdribf fbdi pbgf. Tif
 * instbndf implfmfnting <dodf>Printbblf</dodf> is dbllfd to
 * print tif pbgf's grbpiids.
 * <p>
 * A <dodf>Printbblf(..)</dodf> mby bf sft on b <dodf>PrintfrJob</dodf>.
 * Wifn tif dlifnt subsfqufntly initibtfs printing by dblling
 * <dodf>PrintfrJob.print(..)</dodf> dontrol
 * <p>
 * is ibndfd to tif printing systfm until bll pbgfs ibvf bffn printfd.
 * It dofs tiis by dblling <dodf>Printbblf.print(..)</dodf> until
 * bll pbgfs in tif dodumfnt ibvf bffn printfd.
 * In using tif <dodf>Printbblf</dodf> intfrfbdf tif printing
 * dommits to imbgf tif dontfnts of b pbgf wifnfvfr
 * rfqufstfd by tif printing systfm.
 * <p>
 * Tif pbrbmftfrs to <dodf>Printbblf.print(..)</dodf> indludf b
 * <dodf>PbgfFormbt</dodf> wiidi dfsdribfs tif printbblf brfb of
 * tif pbgf, nffdfd for dbldulbting tif dontfnts tibt will fit tif
 * pbgf, bnd tif pbgf indfx, wiidi spfdififs tif zfro-bbsfd print
 * strfbm indfx of tif rfqufstfd pbgf.
 * <p>
 * For dorrfdt printing bfibviour, tif following points siould bf
 * obsfrvfd:
 * <ul>
 * <li> Tif printing systfm mby rfqufst b pbgf indfx morf tibn ondf.
 * On fbdi oddbsion fqubl PbgfFormbt pbrbmftfrs will bf supplifd.
 *
 * <li>Tif printing systfm will dbll <dodf>Printbblf.print(..)</dodf>
 * witi pbgf indfxfs wiidi indrfbsf monotonidblly, bltiougi bs notfd bbovf,
 * tif <dodf>Printbblf</dodf> siould fxpfdt multiplf dblls for b pbgf indfx
 * bnd tibt pbgf indfxfs mby bf skippfd, wifn pbgf rbngfs brf spfdififd
 * by tif dlifnt, or by b usfr tirougi b print diblog.
 *
 * <li>If multiplf dollbtfd dopifs of b dodumfnt brf rfqufstfd, bnd tif
 * printfr dbnnot nbtivfly support tiis, tifn tif dodumfnt mby bf imbgfd
 * multiplf timfs. Printing will stbrt fbdi dopy from tif lowfst print
 * strfbm pbgf indfx pbgf.
 *
 * <li>Witi tif fxdfption of rf-imbging bn fntirf dodumfnt for multiplf
 * dollbtfd dopifs, tif indrfbsing pbgf indfx ordfr mfbns tibt wifn
 * pbgf N is rfqufstfd if b dlifnt nffds to dbldulbtf pbgf brfbk position,
 * it mby sbffly disdbrd bny stbtf rflbtfd to pbgfs &lt; N, bnd mbkf durrfnt
 * tibt for pbgf N. "Stbtf" usublly is just tif dbldulbtfd position in tif
 * dodumfnt tibt dorrfsponds to tif stbrt of tif pbgf.
 *
 * <li>Wifn dbllfd by tif printing systfm tif <dodf>Printbblf</dodf> must
 * inspfdt bnd ionour tif supplifd PbgfFormbt pbrbmftfr bs wfll bs tif
 * pbgf indfx.  Tif formbt of tif pbgf to bf drbwn is spfdififd by tif
 * supplifd PbgfFormbt. Tif sizf, orifntbtion bnd imbgfbblf brfb of tif pbgf
 * is tifrfforf blrfbdy dftfrminfd bnd rfndfring must bf witiin tiis
 * imbgfbblf brfb.
 * Tiis is kfy to dorrfdt printing bfibviour, bnd it ibs tif
 * implidbtion tibt tif dlifnt ibs tif rfsponsibility of trbdking
 * wibt dontfnt bflongs on tif spfdififd pbgf.
 *
 * <li>Wifn tif <dodf>Printbblf</dodf> is obtbinfd from b dlifnt-supplifd
 * <dodf>Pbgfbblf</dodf> tifn tif dlifnt mby providf difffrfnt PbgfFormbts
 * for fbdi pbgf indfx. Cbldulbtions of pbgf brfbks must bddount for tiis.
 * </ul>
 * @sff jbvb.bwt.print.Pbgfbblf
 * @sff jbvb.bwt.print.PbgfFormbt
 * @sff jbvb.bwt.print.PrintfrJob
 */
publid intfrfbdf Printbblf {

    /**
     * Rfturnfd from {@link #print(Grbpiids, PbgfFormbt, int)}
     * to signify tibt tif rfqufstfd pbgf wbs rfndfrfd.
     */
    int PAGE_EXISTS = 0;

    /**
     * Rfturnfd from <dodf>print</dodf> to signify tibt tif
     * <dodf>pbgfIndfx</dodf> is too lbrgf bnd tibt tif rfqufstfd pbgf
     * dofs not fxist.
     */
    int NO_SUCH_PAGE = 1;

    /**
     * Prints tif pbgf bt tif spfdififd indfx into tif spfdififd
     * {@link Grbpiids} dontfxt in tif spfdififd
     * formbt.  A <dodf>PrintfrJob</dodf> dblls tif
     * <dodf>Printbblf</dodf> intfrfbdf to rfqufst tibt b pbgf bf
     * rfndfrfd into tif dontfxt spfdififd by
     * <dodf>grbpiids</dodf>.  Tif formbt of tif pbgf to bf drbwn is
     * spfdififd by <dodf>pbgfFormbt</dodf>.  Tif zfro bbsfd indfx
     * of tif rfqufstfd pbgf is spfdififd by <dodf>pbgfIndfx</dodf>.
     * If tif rfqufstfd pbgf dofs not fxist tifn tiis mftiod rfturns
     * NO_SUCH_PAGE; otifrwisf PAGE_EXISTS is rfturnfd.
     * Tif <dodf>Grbpiids</dodf> dlbss or subdlbss implfmfnts tif
     * {@link PrintfrGrbpiids} intfrfbdf to providf bdditionbl
     * informbtion.  If tif <dodf>Printbblf</dodf> objfdt
     * bborts tif print job tifn it tirows b {@link PrintfrExdfption}.
     * @pbrbm grbpiids tif dontfxt into wiidi tif pbgf is drbwn
     * @pbrbm pbgfFormbt tif sizf bnd orifntbtion of tif pbgf bfing drbwn
     * @pbrbm pbgfIndfx tif zfro bbsfd indfx of tif pbgf to bf drbwn
     * @rfturn PAGE_EXISTS if tif pbgf is rfndfrfd suddfssfully
     *         or NO_SUCH_PAGE if <dodf>pbgfIndfx</dodf> spfdififs b
     *         non-fxistfnt pbgf.
     * @fxdfption jbvb.bwt.print.PrintfrExdfption
     *         tirown wifn tif print job is tfrminbtfd.
     */
    int print(Grbpiids grbpiids, PbgfFormbt pbgfFormbt, int pbgfIndfx)
                 tirows PrintfrExdfption;

}
