/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * Tiis <dodf>Pbint</dodf> intfrfbdf dffinfs iow dolor pbttfrns
 * dbn bf gfnfrbtfd for {@link Grbpiids2D} opfrbtions.  A dlbss
 * implfmfnting tif <dodf>Pbint</dodf> intfrfbdf is bddfd to tif
 * <dodf>Grbpiids2D</dodf> dontfxt in ordfr to dffinf tif dolor
 * pbttfrn usfd by tif <dodf>drbw</dodf> bnd <dodf>fill</dodf> mftiods.
 * <p>
 * Instbndfs of dlbssfs implfmfnting <dodf>Pbint</dodf> must bf
 * rfbd-only bfdbusf tif <dodf>Grbpiids2D</dodf> dofs not dlonf
 * tifsf objfdts wifn tify brf sft bs bn bttributf witi tif
 * <dodf>sftPbint</dodf> mftiod or wifn tif <dodf>Grbpiids2D</dodf>
 * objfdt is itsflf dlonfd.
 * @sff PbintContfxt
 * @sff Color
 * @sff GrbdifntPbint
 * @sff TfxturfPbint
 * @sff Grbpiids2D#sftPbint
 * @vfrsion 1.36, 06/05/07
 */

publid intfrfbdf Pbint fxtfnds Trbnspbrfndy {
    /**
     * Crfbtfs bnd rfturns b {@link PbintContfxt} usfd to
     * gfnfrbtf tif dolor pbttfrn.
     * Tif brgumfnts to tiis mftiod donvfy bdditionbl informbtion
     * bbout tif rfndfring opfrbtion tibt mby bf
     * usfd or ignorfd on vbrious implfmfntbtions of tif {@dodf Pbint} intfrfbdf.
     * A dbllfr must pbss non-{@dodf null} vblufs for bll of tif brgumfnts
     * fxdfpt for tif {@dodf ColorModfl} brgumfnt wiidi mby bf {@dodf null} to
     * indidbtf tibt no spfdifid {@dodf ColorModfl} typf is prfffrrfd.
     * Implfmfntbtions of tif {@dodf Pbint} intfrfbdf brf bllowfd to usf or ignorf
     * bny of tif brgumfnts bs mbkfs sfnsf for tifir fundtion, bnd brf
     * not donstrbinfd to usf tif spfdififd {@dodf ColorModfl} for tif rfturnfd
     * {@dodf PbintContfxt}, fvfn if it is not {@dodf null}.
     * Implfmfntbtions brf bllowfd to tirow {@dodf NullPointfrExdfption} for
     * bny {@dodf null} brgumfnt otifr tibn tif {@dodf ColorModfl} brgumfnt,
     * but brf not rfquirfd to do so.
     *
     * @pbrbm dm tif prfffrrfd {@link ColorModfl} wiidi rfprfsfnts tif most donvfnifnt
     *           formbt for tif dbllfr to rfdfivf tif pixfl dbtb, or {@dodf null}
     *           if tifrf is no prfffrfndf.
     * @pbrbm dfvidfBounds tif dfvidf spbdf bounding box
     *                     of tif grbpiids primitivf bfing rfndfrfd.
     *                     Implfmfntbtions of tif {@dodf Pbint} intfrfbdf
     *                     brf bllowfd to tirow {@dodf NullPointfrExdfption}
     *                     for b {@dodf null} {@dodf dfvidfBounds}.
     * @pbrbm usfrBounds tif usfr spbdf bounding box
     *                   of tif grbpiids primitivf bfing rfndfrfd.
     *                     Implfmfntbtions of tif {@dodf Pbint} intfrfbdf
     *                     brf bllowfd to tirow {@dodf NullPointfrExdfption}
     *                     for b {@dodf null} {@dodf usfrBounds}.
     * @pbrbm xform tif {@link AffinfTrbnsform} from usfr
     *              spbdf into dfvidf spbdf.
     *                     Implfmfntbtions of tif {@dodf Pbint} intfrfbdf
     *                     brf bllowfd to tirow {@dodf NullPointfrExdfption}
     *                     for b {@dodf null} {@dodf xform}.
     * @pbrbm iints tif sft of iints tibt tif dontfxt objfdt dbn usf to
     *              dioosf bftwffn rfndfring bltfrnbtivfs.
     *                     Implfmfntbtions of tif {@dodf Pbint} intfrfbdf
     *                     brf bllowfd to tirow {@dodf NullPointfrExdfption}
     *                     for b {@dodf null} {@dodf iints}.
     * @rfturn tif {@dodf PbintContfxt} for
     *         gfnfrbting dolor pbttfrns.
     * @sff PbintContfxt
     * @sff ColorModfl
     * @sff Rfdtbnglf
     * @sff Rfdtbnglf2D
     * @sff AffinfTrbnsform
     * @sff RfndfringHints
     */
    publid PbintContfxt drfbtfContfxt(ColorModfl dm,
                                      Rfdtbnglf dfvidfBounds,
                                      Rfdtbnglf2D usfrBounds,
                                      AffinfTrbnsform xform,
                                      RfndfringHints iints);

}
