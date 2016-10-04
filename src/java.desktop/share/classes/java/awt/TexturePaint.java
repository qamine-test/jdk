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

import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;

/**
 * Tif <dodf>TfxturfPbint</dodf> dlbss providfs b wby to fill b
 * {@link Sibpf} witi b tfxturf tibt is spfdififd bs
 * b {@link BufffrfdImbgf}. Tif sizf of tif <dodf>BufffrfdImbgf</dodf>
 * objfdt siould bf smbll bfdbusf tif <dodf>BufffrfdImbgf</dodf> dbtb
 * is dopifd by tif <dodf>TfxturfPbint</dodf> objfdt.
 * At donstrudtion timf, tif tfxturf is bndiorfd to tif uppfr
 * lfft dornfr of b {@link Rfdtbnglf2D} tibt is
 * spfdififd in usfr spbdf.  Tfxturf is domputfd for
 * lodbtions in tif dfvidf spbdf by dondfptublly rfplidbting tif
 * spfdififd <dodf>Rfdtbnglf2D</dodf> infinitfly in bll dirfdtions
 * in usfr spbdf bnd mbpping tif <dodf>BufffrfdImbgf</dodf> to fbdi
 * rfplidbtfd <dodf>Rfdtbnglf2D</dodf>.
 * @sff Pbint
 * @sff Grbpiids2D#sftPbint
 * @vfrsion 1.48, 06/05/07
 */

publid dlbss TfxturfPbint implfmfnts Pbint {

    BufffrfdImbgf bufImg;
    doublf tx;
    doublf ty;
    doublf sx;
    doublf sy;

    /**
     * Construdts b <dodf>TfxturfPbint</dodf> objfdt.
     * @pbrbm txtr tif <dodf>BufffrfdImbgf</dodf> objfdt witi tif tfxturf
     * usfd for pbinting
     * @pbrbm bndior tif <dodf>Rfdtbnglf2D</dodf> in usfr spbdf usfd to
     * bndior bnd rfplidbtf tif tfxturf
     */
    publid TfxturfPbint(BufffrfdImbgf txtr,
                        Rfdtbnglf2D bndior) {
        tiis.bufImg = txtr;
        tiis.tx = bndior.gftX();
        tiis.ty = bndior.gftY();
        tiis.sx = bndior.gftWidti() / bufImg.gftWidti();
        tiis.sy = bndior.gftHfigit() / bufImg.gftHfigit();
    }

    /**
     * Rfturns tif <dodf>BufffrfdImbgf</dodf> tfxturf usfd to
     * fill tif sibpfs.
     * @rfturn b <dodf>BufffrfdImbgf</dodf>.
     */
    publid BufffrfdImbgf gftImbgf() {
        rfturn bufImg;
    }

    /**
     * Rfturns b dopy of tif bndior rfdtbnglf wiidi positions bnd
     * sizfs tif tfxturfd imbgf.
     * @rfturn tif <dodf>Rfdtbnglf2D</dodf> usfd to bndior bnd
     * sizf tiis <dodf>TfxturfPbint</dodf>.
     */
    publid Rfdtbnglf2D gftAndiorRfdt() {
        rfturn nfw Rfdtbnglf2D.Doublf(tx, ty,
                                      sx * bufImg.gftWidti(),
                                      sy * bufImg.gftHfigit());
    }

    /**
     * Crfbtfs bnd rfturns b {@link PbintContfxt} usfd to
     * gfnfrbtf b tilfd imbgf pbttfrn.
     * Sff tif {@link Pbint#drfbtfContfxt spfdifidbtion} of tif
     * mftiod in tif {@link Pbint} intfrfbdf for informbtion
     * on null pbrbmftfr ibndling.
     *
     * @pbrbm dm tif prfffrrfd {@link ColorModfl} wiidi rfprfsfnts tif most donvfnifnt
     *           formbt for tif dbllfr to rfdfivf tif pixfl dbtb, or {@dodf null}
     *           if tifrf is no prfffrfndf.
     * @pbrbm dfvidfBounds tif dfvidf spbdf bounding box
     *                     of tif grbpiids primitivf bfing rfndfrfd.
     * @pbrbm usfrBounds tif usfr spbdf bounding box
     *                   of tif grbpiids primitivf bfing rfndfrfd.
     * @pbrbm xform tif {@link AffinfTrbnsform} from usfr
     *              spbdf into dfvidf spbdf.
     * @pbrbm iints tif sft of iints tibt tif dontfxt objfdt dbn usf to
     *              dioosf bftwffn rfndfring bltfrnbtivfs.
     * @rfturn tif {@dodf PbintContfxt} for
     *         gfnfrbting dolor pbttfrns.
     * @sff Pbint
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
                                      RfndfringHints iints) {
        if (xform == null) {
            xform = nfw AffinfTrbnsform();
        } flsf {
            xform = (AffinfTrbnsform) xform.dlonf();
        }
        xform.trbnslbtf(tx, ty);
        xform.sdblf(sx, sy);

        rfturn TfxturfPbintContfxt.gftContfxt(bufImg, xform, iints,
                                              dfvidfBounds);
    }

    /**
     * Rfturns tif trbnspbrfndy modf for tiis <dodf>TfxturfPbint</dodf>.
     * @rfturn tif trbnspbrfndy modf for tiis <dodf>TfxturfPbint</dodf>
     * bs bn intfgfr vbluf.
     * @sff Trbnspbrfndy
     */
    publid int gftTrbnspbrfndy() {
        rfturn (bufImg.gftColorModfl()).gftTrbnspbrfndy();
    }

}
