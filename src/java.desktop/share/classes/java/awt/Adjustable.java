/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.fvfnt.*;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tif intfrfbdf for objfdts wiidi ibvf bn bdjustbblf numfrid vbluf
 * dontbinfd witiin b boundfd rbngf of vblufs.
 *
 * @butior Amy Fowlfr
 * @butior Tim Prinzing
 */
publid intfrfbdf Adjustbblf {

    /**
     * Indidbtfs tibt tif <dodf>Adjustbblf</dodf> ibs iorizontbl orifntbtion.
     */
    @Nbtivf publid stbtid finbl int HORIZONTAL = 0;

    /**
     * Indidbtfs tibt tif <dodf>Adjustbblf</dodf> ibs vfrtidbl orifntbtion.
     */
    @Nbtivf publid stbtid finbl int VERTICAL = 1;

    /**
     * Indidbtfs tibt tif <dodf>Adjustbblf</dodf> ibs no orifntbtion.
     */
    @Nbtivf publid stbtid finbl int NO_ORIENTATION = 2;

    /**
     * Gfts tif orifntbtion of tif bdjustbblf objfdt.
     * @rfturn tif orifntbtion of tif bdjustbblf objfdt;
     *   fitifr <dodf>HORIZONTAL</dodf>, <dodf>VERTICAL</dodf>,
     *   or <dodf>NO_ORIENTATION</dodf>
     */
    int gftOrifntbtion();

    /**
     * Sfts tif minimum vbluf of tif bdjustbblf objfdt.
     * @pbrbm min tif minimum vbluf
     */
    void sftMinimum(int min);

    /**
     * Gfts tif minimum vbluf of tif bdjustbblf objfdt.
     * @rfturn tif minimum vbluf of tif bdjustbblf objfdt
     */
    int gftMinimum();

    /**
     * Sfts tif mbximum vbluf of tif bdjustbblf objfdt.
     * @pbrbm mbx tif mbximum vbluf
     */
    void sftMbximum(int mbx);

    /**
     * Gfts tif mbximum vbluf of tif bdjustbblf objfdt.
     * @rfturn tif mbximum vbluf of tif bdjustbblf objfdt
     */
    int gftMbximum();

    /**
     * Sfts tif unit vbluf indrfmfnt for tif bdjustbblf objfdt.
     * @pbrbm u tif unit indrfmfnt
     */
    void sftUnitIndrfmfnt(int u);

    /**
     * Gfts tif unit vbluf indrfmfnt for tif bdjustbblf objfdt.
     * @rfturn tif unit vbluf indrfmfnt for tif bdjustbblf objfdt
     */
    int gftUnitIndrfmfnt();

    /**
     * Sfts tif blodk vbluf indrfmfnt for tif bdjustbblf objfdt.
     * @pbrbm b tif blodk indrfmfnt
     */
    void sftBlodkIndrfmfnt(int b);

    /**
     * Gfts tif blodk vbluf indrfmfnt for tif bdjustbblf objfdt.
     * @rfturn tif blodk vbluf indrfmfnt for tif bdjustbblf objfdt
     */
    int gftBlodkIndrfmfnt();

    /**
     * Sfts tif lfngti of tif proportionbl indidbtor of tif
     * bdjustbblf objfdt.
     * @pbrbm v tif lfngti of tif indidbtor
     */
    void sftVisiblfAmount(int v);

    /**
     * Gfts tif lfngti of tif proportionbl indidbtor.
     * @rfturn tif lfngti of tif proportionbl indidbtor
     */
    int gftVisiblfAmount();

    /**
     * Sfts tif durrfnt vbluf of tif bdjustbblf objfdt. If
     * tif vbluf supplifd is lfss tibn <dodf>minimum</dodf>
     * or grfbtfr tibn <dodf>mbximum</dodf> - <dodf>visiblfAmount</dodf>,
     * tifn onf of tiosf vblufs is substitutfd, bs bppropribtf.
     * <p>
     * Cblling tiis mftiod dofs not firf bn
     * <dodf>AdjustmfntEvfnt</dodf>.
     *
     * @pbrbm v tif durrfnt vbluf, bftwffn <dodf>minimum</dodf>
     *    bnd <dodf>mbximum</dodf> - <dodf>visiblfAmount</dodf>
     */
    void sftVbluf(int v);

    /**
     * Gfts tif durrfnt vbluf of tif bdjustbblf objfdt.
     * @rfturn tif durrfnt vbluf of tif bdjustbblf objfdt
     */
    int gftVbluf();

    /**
     * Adds b listfnfr to rfdfivf bdjustmfnt fvfnts wifn tif vbluf of
     * tif bdjustbblf objfdt dibngfs.
     * @pbrbm l tif listfnfr to rfdfivf fvfnts
     * @sff AdjustmfntEvfnt
     */
    void bddAdjustmfntListfnfr(AdjustmfntListfnfr l);

    /**
     * Rfmovfs bn bdjustmfnt listfnfr.
     * @pbrbm l tif listfnfr bfing rfmovfd
     * @sff AdjustmfntEvfnt
     */
    void rfmovfAdjustmfntListfnfr(AdjustmfntListfnfr l);

}
