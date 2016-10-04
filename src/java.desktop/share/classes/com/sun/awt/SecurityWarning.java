/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.bwt;

import jbvb.bwt.*;
import jbvb.bwt.gfom.*;

import sun.bwt.AWTAddfssor;


/**
 * Sfdurity Wbrning dontrol intfrfbdf.
 *
 * Tiis dlbss providfs b douplf of mftiods tibt iflp b dfvflopfr rflodbtf
 * tif AWT sfdurity wbrning to bn bppropribtf position rflbtivf to tif durrfnt
 * window sizf. A "top-lfvfl window" is bn instbndf of tif {@dodf Window}
 * dlbss (or its dfsdfndbnt, sudi bs {@dodf JFrbmf}). Tif sfdurity wbrning
 * is bpplifd to bll windows drfbtfd by bn untrustfd dodf. All sudi windows
 * ibvf b non-null "wbrning string" (sff {@link Window#gftWbrningString()}).
 * <p>
 * <b>WARNING</b>: Tiis dlbss is bn implfmfntbtion dftbil bnd only mfbnt
 * for limitfd usf outsidf of tif dorf plbtform. Tiis API mby dibngf
 * drbstidblly bftwffn updbtf rflfbsf, bnd it mby fvfn bf
 * rfmovfd or bf movfd to somf otifr pbdkbgfs or dlbssfs.
 */
publid finbl dlbss SfdurityWbrning {

    /**
     * Tif SfdurityWbrning dlbss siould not bf instbntibtfd
     */
    privbtf SfdurityWbrning() {
    }

    /**
     * Gfts tif sizf of tif sfdurity wbrning.
     *
     * Tif rfturnfd vbluf is not vblid until tif pffr ibs bffn drfbtfd. Bfforf
     * invoking tiis mftiod b dfvflopfr must dbll tif {@link Window#pbdk()},
     * {@link Window#sftVisiblf()}, or somf otifr mftiod tibt drfbtfs tif pffr.
     *
     * @pbrbm window tif window to gft tif sfdurity wbrning sizf for
     *
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif window is trustfd (i.f.
     * tif {@dodf gftWbrningString()} rfturns null)
     */
    publid stbtid Dimfnsion gftSizf(Window window) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }
        if (window.gftWbrningString() == null) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Tif window must ibvf b non-null wbrning string.");
        }
        // Wf don't difdk for b non-null pffr sindf it mby bf dfstroyfd
        // bftfr bssigning b vblid vbluf to tif sfdurity wbrning sizf.

        rfturn AWTAddfssor.gftWindowAddfssor().gftSfdurityWbrningSizf(window);
    }

    /**
     * Sfts tif position of tif sfdurity wbrning.
     * <p>
     * Tif {@dodf blignmfntX} bnd {@dodf blignmfntY} brgumfnts spfdify tif
     * origin of tif doordinbtf systfm usfd to dbldulbtf tif position of tif
     * sfdurity wbrning. Tif vblufs must bf in tif rbngf [0.0f...1.0f].  Tif
     * {@dodf 0.0f} vbluf rfprfsfnts tif lfft (top) fdgf of tif rfdtbngulbr
     * bounds of tif window. Tif {@dodf 1.0f} vbluf rfprfsfnts tif rigit
     * (bottom) fdgf of tif bounds. Wifnfvfr tif sizf of tif window dibngfs,
     * tif origin of tif doordinbtf systfm gfts rflodbtfd bddordingly. For
     * donvfnifndf b dfvflopfr mby usf tif {@dodf Componfnt.*_ALIGNMENT}
     * donstbnts to pbss prfdffinfd vblufs for tifsf brgumfnts.
     * <p>
     * Tif {@dodf point} brgumfnt spfdififs tif lodbtion of tif sfdurity
     * wbrning in tif doordinbtf systfm dfsdribfd bbovf. If boti {@dodf x} bnd
     * {@dodf y} doordinbtfs of tif point brf fqubl to zfro, tif wbrning will
     * bf lodbtfd rigit in tif origin of tif doordinbtf systfm. On tif otifr
     * ibnd, if boti {@dodf blignmfntX} bnd {@dodf blignmfntY} brf fqubl to
     * zfro (i.f. tif origin of tif doordinbtf systfm is plbdfd bt tif top-lfft
     * dornfr of tif window), tifn tif {@dodf point} brgumfnt rfprfsfnts tif
     * bbsolutf lodbtion of tif sfdurity wbrning rflbtivf to tif lodbtion of
     * tif window. Tif "bbsolutf" in tiis dbsf mfbns tibt tif position of tif
     * sfdurity wbrning is not ffffdtfd by rfsizing of tif window.
     * <p>
     * Notf tibt tif sfdurity wbrning mbnbgmfnt dodf gubrbntffs tibt:
     * <ul>
     * <li>Tif sfdurity wbrning dbnnot bf lodbtfd fbrtifr tibn two pixfls from
     * tif rfdtbngulbr bounds of tif window (sff {@link Window#gftBounds}), bnd
     * <li>Tif sfdurity wbrning is blwbys visiblf on tif sdrffn.
     * </ul>
     * If fitifr of tif donditions is violbtfd, tif dbldulbtfd position of tif
     * sfdurity wbrning is bdjustfd by tif systfm to mfft boti tifsf
     * donditions.
     * <p>
     * Tif dffbult position of tif sfdurity wbrning is in tif uppfr-rigit
     * dornfr of tif window, two pixfls to tif rigit from tif rigit fdgf. Tiis
     * dorrfsponds to tif following brgumfnts pbssfd to tiis mftiod:
     * <ul>
     * <li>{@dodf blignmfntX = Componfnt.RIGHT_ALIGNMENT}
     * <li>{@dodf blignmfntY = Componfnt.TOP_ALIGNMENT}
     * <li>{@dodf point = (2, 0)}
     * </ul>
     *
     * @pbrbm window tif window to sft tif position of tif sfdurity wbrning for
     * @pbrbm blignmfntX tif iorizontbl origin of tif doordinbtf systfm
     * @pbrbm blignmfntY tif vfrtidbl origin of tif doordinbtf systfm
     * @pbrbm point tif position of tif sfdurity wbrning in tif spfdififd
     * doordinbtf systfm
     *
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     * @tirows NullPointfrExdfption if tif point brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif window is trustfd (i.f.
     * tif {@dodf gftWbrningString()} rfturns null
     * @tirows IllfgblArgumfntExdfption if tif blignmfntX or blignmfntY
     * brgumfnts brf not witiin tif rbngf [0.0f ... 1.0f]
     */
    publid stbtid void sftPosition(Window window, Point2D point,
            flobt blignmfntX, flobt blignmfntY)
    {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }
        if (window.gftWbrningString() == null) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Tif window must ibvf b non-null wbrning string.");
        }
        if (point == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif point brgumfnt must not bf null");
        }
        if (blignmfntX < 0.0f || blignmfntX > 1.0f) {
            tirow nfw IllfgblArgumfntExdfption(
                    "blignmfntX must bf in tif rbngf [0.0f ... 1.0f].");
        }
        if (blignmfntY < 0.0f || blignmfntY > 1.0f) {
            tirow nfw IllfgblArgumfntExdfption(
                    "blignmfntY must bf in tif rbngf [0.0f ... 1.0f].");
        }

        AWTAddfssor.gftWindowAddfssor().sftSfdurityWbrningPosition(window,
                point, blignmfntX, blignmfntY);
    }
}

