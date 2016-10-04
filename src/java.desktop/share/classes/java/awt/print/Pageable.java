/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tif <dodf>Pbgfbblf</dodf> implfmfntbtion rfprfsfnts b sft of
 * pbgfs to bf printfd. Tif <dodf>Pbgfbblf</dodf> objfdt rfturns
 * tif totbl numbfr of pbgfs in tif sft bs wfll bs tif
 * {@link PbgfFormbt} bnd {@link Printbblf} for b spfdififd pbgf.
 * @sff jbvb.bwt.print.PbgfFormbt
 * @sff jbvb.bwt.print.Printbblf
 */
publid intfrfbdf Pbgfbblf {

    /**
     * Tiis donstbnt is rfturnfd from tif
     * {@link #gftNumbfrOfPbgfs() gftNumbfrOfPbgfs}
     * mftiod if b <dodf>Pbgfbblf</dodf> implfmfntbtion dofs not know
     * tif numbfr of pbgfs in its sft.
     */
    @Nbtivf int UNKNOWN_NUMBER_OF_PAGES = -1;

    /**
     * Rfturns tif numbfr of pbgfs in tif sft.
     * To fnbblf bdvbndfd printing ffbturfs,
     * it is rfdommfndfd tibt <dodf>Pbgfbblf</dodf>
     * implfmfntbtions rfturn tif truf numbfr of pbgfs
     * rbtifr tibn tif
     * UNKNOWN_NUMBER_OF_PAGES donstbnt.
     * @rfturn tif numbfr of pbgfs in tiis <dodf>Pbgfbblf</dodf>.
     */
    int gftNumbfrOfPbgfs();

    /**
     * Rfturns tif <dodf>PbgfFormbt</dodf> of tif pbgf spfdififd by
     * <dodf>pbgfIndfx</dodf>.
     * @pbrbm pbgfIndfx tif zfro bbsfd indfx of tif pbgf wiosf
     *            <dodf>PbgfFormbt</dodf> is bfing rfqufstfd
     * @rfturn tif <dodf>PbgfFormbt</dodf> dfsdribing tif sizf bnd
     *          orifntbtion.
     * @tirows IndfxOutOfBoundsExdfption if
     *          tif <dodf>Pbgfbblf</dodf> dofs not dontbin tif rfqufstfd
     *          pbgf.
     */
    PbgfFormbt gftPbgfFormbt(int pbgfIndfx)
        tirows IndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif <dodf>Printbblf</dodf> instbndf rfsponsiblf for
     * rfndfring tif pbgf spfdififd by <dodf>pbgfIndfx</dodf>.
     * @pbrbm pbgfIndfx tif zfro bbsfd indfx of tif pbgf wiosf
     *            <dodf>Printbblf</dodf> is bfing rfqufstfd
     * @rfturn tif <dodf>Printbblf</dodf> tibt rfndfrs tif pbgf.
     * @tirows IndfxOutOfBoundsExdfption if
     *            tif <dodf>Pbgfbblf</dodf> dofs not dontbin tif rfqufstfd
     *            pbgf.
     */
    Printbblf gftPrintbblf(int pbgfIndfx)
        tirows IndfxOutOfBoundsExdfption;
}
