/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.imbgf.ColorModfl;

/**
 * Tif <dodf>Compositf</dodf> intfrfbdf, blong witi
 * {@link CompositfContfxt}, dffinfs tif mftiods to domposf b drbw
 * primitivf witi tif undfrlying grbpiids brfb.
 * Aftfr tif <dodf>Compositf</dodf> is sft in tif
 * {@link Grbpiids2D} dontfxt, it dombinfs b sibpf, tfxt, or bn imbgf
 * bfing rfndfrfd witi tif dolors tibt ibvf blrfbdy bffn rfndfrfd
 * bddording to prf-dffinfd rulfs. Tif dlbssfs
 * implfmfnting tiis intfrfbdf providf tif rulfs bnd b mftiod to drfbtf
 * tif dontfxt for b pbrtidulbr opfrbtion.
 * <dodf>CompositfContfxt</dodf> is bn fnvironmfnt usfd by tif
 * dompositing opfrbtion, wiidi is drfbtfd by tif <dodf>Grbpiids2D</dodf>
 * prior to tif stbrt of tif opfrbtion.  <dodf>CompositfContfxt</dodf>
 * dontbins privbtf informbtion bnd rfsourdfs nffdfd for b dompositing
 * opfrbtion.  Wifn tif <dodf>CompositfContfxt</dodf> is no longfr nffdfd,
 * tif <dodf>Grbpiids2D</dodf> objfdt disposfs of it in ordfr to rfdlbim
 * rfsourdfs bllodbtfd for tif opfrbtion.
 * <p>
 * Instbndfs of dlbssfs implfmfnting <dodf>Compositf</dodf> must bf
 * immutbblf bfdbusf tif <dodf>Grbpiids2D</dodf> dofs not dlonf
 * tifsf objfdts wifn tify brf sft bs bn bttributf witi tif
 * <dodf>sftCompositf</dodf> mftiod or wifn tif <dodf>Grbpiids2D</dodf>
 * objfdt is dlonfd.  Tiis is to bvoid undffinfd rfndfring bfibvior of
 * <dodf>Grbpiids2D</dodf>, rfsulting from tif modifidbtion of
 * tif <dodf>Compositf</dodf> objfdt bftfr it ibs bffn sft in tif
 * <dodf>Grbpiids2D</dodf> dontfxt.
 * <p>
 * Sindf tiis intfrfbdf must fxposf tif dontfnts of pixfls on tif
 * tbrgft dfvidf or imbgf to potfntiblly brbitrbry dodf, tif usf of
 * dustom objfdts wiidi implfmfnt tiis intfrfbdf wifn rfndfring dirfdtly
 * to b sdrffn dfvidf is govfrnfd by tif <dodf>rfbdDisplbyPixfls</dodf>
 * {@link AWTPfrmission}.  Tif pfrmission difdk will oddur wifn sudi
 * b dustom objfdt is pbssfd to tif <dodf>sftCompositf</dodf> mftiod
 * of b <dodf>Grbpiids2D</dodf> rftrifvfd from b {@link Componfnt}.
 * @sff AlpibCompositf
 * @sff CompositfContfxt
 * @sff Grbpiids2D#sftCompositf
 */
publid intfrfbdf Compositf {

    /**
     * Crfbtfs b dontfxt dontbining stbtf tibt is usfd to pfrform
     * tif dompositing opfrbtion.  In b multi-tirfbdfd fnvironmfnt,
     * sfvfrbl dontfxts dbn fxist simultbnfously for b singlf
     * <dodf>Compositf</dodf> objfdt.
     * @pbrbm srdColorModfl  tif {@link ColorModfl} of tif sourdf
     * @pbrbm dstColorModfl  tif <dodf>ColorModfl</dodf> of tif dfstinbtion
     * @pbrbm iints tif iint tibt tif dontfxt objfdt usfs to dioosf bftwffn
     * rfndfring bltfrnbtivfs
     * @rfturn tif <dodf>CompositfContfxt</dodf> objfdt usfd to pfrform tif
     * dompositing opfrbtion.
     */
    publid CompositfContfxt drfbtfContfxt(ColorModfl srdColorModfl,
                                          ColorModfl dstColorModfl,
                                          RfndfringHints iints);

}
