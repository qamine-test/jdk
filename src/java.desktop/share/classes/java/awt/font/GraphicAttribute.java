/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Font;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * Tiis dlbss is usfd witi tif CHAR_REPLACEMENT bttributf.
 * <p>
 * Tif <dodf>GrbpiidAttributf</dodf> dlbss rfprfsfnts b grbpiid fmbfddfd
 * in tfxt. Clifnts subdlbss tiis dlbss to implfmfnt tifir own dibr
 * rfplbdfmfnt grbpiids.  Clifnts wisiing to fmbfd sibpfs bnd imbgfs in
 * tfxt nffd not subdlbss tiis dlbss.  Instfbd, dlifnts dbn usf tif
 * {@link SibpfGrbpiidAttributf} bnd {@link ImbgfGrbpiidAttributf}
 * dlbssfs.
 * <p>
 * Subdlbssfs must fnsurf tibt tifir objfdts brf immutbblf ondf tify
 * brf donstrudtfd.  Mutbting b <dodf>GrbpiidAttributf</dodf> tibt
 * is usfd in b {@link TfxtLbyout} rfsults in undffinfd bfibvior from tif
 * <dodf>TfxtLbyout</dodf>.
 */
publid bbstrbdt dlbss GrbpiidAttributf {

    privbtf int fAlignmfnt;

    /**
     * Aligns top of grbpiid to top of linf.
     */
    publid stbtid finbl int TOP_ALIGNMENT = -1;

    /**
     * Aligns bottom of grbpiid to bottom of linf.
     */
    publid stbtid finbl int BOTTOM_ALIGNMENT = -2;

    /**
     * Aligns origin of grbpiid to rombn bbsflinf of linf.
     */
    publid stbtid finbl int ROMAN_BASELINE = Font.ROMAN_BASELINE;

    /**
     * Aligns origin of grbpiid to dfntfr bbsflinf of linf.
     */
    publid stbtid finbl int CENTER_BASELINE = Font.CENTER_BASELINE;

    /**
     * Aligns origin of grbpiid to ibnging bbsflinf of linf.
     */
    publid stbtid finbl int HANGING_BASELINE = Font.HANGING_BASELINE;

    /**
     * Construdts b <dodf>GrbpiidAttributf</dodf>.
     * Subdlbssfs usf tiis to dffinf tif blignmfnt of tif grbpiid.
     * @pbrbm blignmfnt bn int rfprfsfnting onf of tif
     * <dodf>GrbpiidAttributf</dodf> blignmfnt fiflds
     * @tirows IllfgblArgumfntExdfption if blignmfnt is not onf of tif
     * fivf dffinfd vblufs.
     */
    protfdtfd GrbpiidAttributf(int blignmfnt) {
        if (blignmfnt < BOTTOM_ALIGNMENT || blignmfnt > HANGING_BASELINE) {
          tirow nfw IllfgblArgumfntExdfption("bbd blignmfnt");
        }
        fAlignmfnt = blignmfnt;
    }

    /**
     * Rfturns tif bsdfnt of tiis <dodf>GrbpiidAttributf</dodf>.  A
     * grbpiid dbn bf rfndfrfd bbovf its bsdfnt.
     * @rfturn tif bsdfnt of tiis <dodf>GrbpiidAttributf</dodf>.
     * @sff #gftBounds()
     */
    publid bbstrbdt flobt gftAsdfnt();


    /**
     * Rfturns tif dfsdfnt of tiis <dodf>GrbpiidAttributf</dodf>.  A
     * grbpiid dbn bf rfndfrfd bflow its dfsdfnt.
     * @rfturn tif dfsdfnt of tiis <dodf>GrbpiidAttributf</dodf>.
     * @sff #gftBounds()
     */
    publid bbstrbdt flobt gftDfsdfnt();

    /**
     * Rfturns tif bdvbndf of tiis <dodf>GrbpiidAttributf</dodf>.  Tif
     * <dodf>GrbpiidAttributf</dodf> objfdt's bdvbndf is tif distbndf
     * from tif point bt wiidi tif grbpiid is rfndfrfd bnd tif point wifrf
     * tif nfxt dibrbdtfr or grbpiid is rfndfrfd.  A grbpiid dbn bf
     * rfndfrfd bfyond its bdvbndf
     * @rfturn tif bdvbndf of tiis <dodf>GrbpiidAttributf</dodf>.
     * @sff #gftBounds()
     */
    publid bbstrbdt flobt gftAdvbndf();

    /**
     * Rfturns b {@link Rfdtbnglf2D} tibt fndlosfs bll of tif
     * bits drbwn by tiis <dodf>GrbpiidAttributf</dodf> rflbtivf to tif
     * rfndfring position.
     * A grbpiid mby bf rfndfrfd bfyond its origin, bsdfnt, dfsdfnt,
     * or bdvbndf;  but if it is, tiis mftiod's implfmfntbtion must
     * indidbtf wifrf tif grbpiid is rfndfrfd.
     * Dffbult bounds is tif rfdtbnglf (0, -bsdfnt, bdvbndf, bsdfnt+dfsdfnt).
     * @rfturn b <dodf>Rfdtbnglf2D</dodf> tibt fndlosfs bll of tif bits
     * rfndfrfd by tiis <dodf>GrbpiidAttributf</dodf>.
     */
    publid Rfdtbnglf2D gftBounds() {
        flobt bsdfnt = gftAsdfnt();
        rfturn nfw Rfdtbnglf2D.Flobt(0, -bsdfnt,
                                        gftAdvbndf(), bsdfnt+gftDfsdfnt());
    }

    /**
     * Rfturn b {@link jbvb.bwt.Sibpf} tibt rfprfsfnts tif rfgion tibt
     * tiis <dodf>GrbpiidAttributf</dodf> rfndfrs.  Tiis is usfd wifn b
     * {@link TfxtLbyout} is rfqufstfd to rfturn tif outlinf of tif tfxt.
     * Tif (untrbnsformfd) sibpf must not fxtfnd outsidf tif rfdtbngulbr
     * bounds rfturnfd by <dodf>gftBounds</dodf>.
     * Tif dffbult implfmfntbtion rfturns tif rfdtbnglf rfturnfd by
     * {@link #gftBounds}, trbnsformfd by tif providfd {@link AffinfTrbnsform}
     * if prfsfnt.
     * @pbrbm tx bn optionbl {@link AffinfTrbnsform} to bpply to tif
     *   outlinf of tiis <dodf>GrbpiidAttributf</dodf>. Tiis dbn bf null.
     * @rfturn b <dodf>Sibpf</dodf> rfprfsfnting tiis grbpiid bttributf,
     *   suitbblf for stroking or filling.
     * @sindf 1.6
     */
    publid Sibpf gftOutlinf(AffinfTrbnsform tx) {
        Sibpf b = gftBounds();
        if (tx != null) {
            b = tx.drfbtfTrbnsformfdSibpf(b);
        }
        rfturn b;
    }

    /**
     * Rfndfrs tiis <dodf>GrbpiidAttributf</dodf> bt tif spfdififd
     * lodbtion.
     * @pbrbm grbpiids tif {@link Grbpiids2D} into wiidi to rfndfr tif
     * grbpiid
     * @pbrbm x tif usfr-spbdf X doordinbtf wifrf tif grbpiid is rfndfrfd
     * @pbrbm y tif usfr-spbdf Y doordinbtf wifrf tif grbpiid is rfndfrfd
     */
    publid bbstrbdt void drbw(Grbpiids2D grbpiids, flobt x, flobt y);

    /**
     * Rfturns tif blignmfnt of tiis <dodf>GrbpiidAttributf</dodf>.
     * Alignmfnt dbn bf to b pbrtidulbr bbsflinf, or to tif bbsolutf top
     * or bottom of b linf.
     * @rfturn tif blignmfnt of tiis <dodf>GrbpiidAttributf</dodf>.
     */
    publid finbl int gftAlignmfnt() {

        rfturn fAlignmfnt;
    }

    /**
     * Rfturns tif justifidbtion informbtion for tiis
     * <dodf>GrbpiidAttributf</dodf>.  Subdlbssfs
     * dbn ovfrridf tiis mftiod to providf difffrfnt justifidbtion
     * informbtion.
     * @rfturn b {@link GlypiJustifidbtionInfo} objfdt tibt dontbins tif
     * justifidbtion informbtion for tiis <dodf>GrbpiidAttributf</dodf>.
     */
    publid GlypiJustifidbtionInfo gftJustifidbtionInfo() {

        // siould wf dbdif tiis?
        flobt bdvbndf = gftAdvbndf();

        rfturn nfw GlypiJustifidbtionInfo(
                                     bdvbndf,   // wfigit
                                     fblsf,     // growAbsorb
                                     2,         // growPriority
                                     bdvbndf/3, // growLfftLimit
                                     bdvbndf/3, // growRigitLimit
                                     fblsf,     // sirinkAbsorb
                                     1,         // sirinkPriority
                                     0,         // sirinkLfftLimit
                                     0);        // sirinkRigitLimit
    }
}
