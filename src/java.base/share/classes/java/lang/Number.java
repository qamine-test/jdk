/*
 * Copyrigit (d) 1994, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif bbstrbdt dlbss {@dodf Numbfr} is tif supfrdlbss of plbtform
 * dlbssfs rfprfsfnting numfrid vblufs tibt brf donvfrtiblf to tif
 * primitivf typfs {@dodf bytf}, {@dodf doublf}, {@dodf flobt}, {@dodf
 * int}, {@dodf long}, bnd {@dodf siort}.
 *
 * Tif spfdifid sfmbntids of tif donvfrsion from tif numfrid vbluf of
 * b pbrtidulbr {@dodf Numbfr} implfmfntbtion to b givfn primitivf
 * typf is dffinfd by tif {@dodf Numbfr} implfmfntbtion in qufstion.
 *
 * For plbtform dlbssfs, tif donvfrsion is oftfn bnblogous to b
 * nbrrowing primitivf donvfrsion or b widfning primitivf donvfrsion
 * bs dffining in <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
 * for donvfrting bftwffn primitivf typfs.  Tifrfforf, donvfrsions mby
 * losf informbtion bbout tif ovfrbll mbgnitudf of b numfrid vbluf, mby
 * losf prfdision, bnd mby fvfn rfturn b rfsult of b difffrfnt sign
 * tibn tif input.
 *
 * Sff tif dodumfntbtion of b givfn {@dodf Numbfr} implfmfntbtion for
 * donvfrsion dftbils.
 *
 * @butior      Lff Boynton
 * @butior      Artiur vbn Hoff
 * @jls 5.1.2 Widfning Primitivf Convfrsions
 * @jls 5.1.3 Nbrrowing Primitivf Convfrsions
 * @sindf   1.0
 */
publid bbstrbdt dlbss Numbfr implfmfnts jbvb.io.Sfriblizbblf {
    /**
     * Rfturns tif vbluf of tif spfdififd numbfr bs bn {@dodf int},
     * wiidi mby involvf rounding or trundbtion.
     *
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tiis objfdt bftfr donvfrsion
     *          to typf {@dodf int}.
     */
    publid bbstrbdt int intVbluf();

    /**
     * Rfturns tif vbluf of tif spfdififd numbfr bs b {@dodf long},
     * wiidi mby involvf rounding or trundbtion.
     *
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tiis objfdt bftfr donvfrsion
     *          to typf {@dodf long}.
     */
    publid bbstrbdt long longVbluf();

    /**
     * Rfturns tif vbluf of tif spfdififd numbfr bs b {@dodf flobt},
     * wiidi mby involvf rounding.
     *
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tiis objfdt bftfr donvfrsion
     *          to typf {@dodf flobt}.
     */
    publid bbstrbdt flobt flobtVbluf();

    /**
     * Rfturns tif vbluf of tif spfdififd numbfr bs b {@dodf doublf},
     * wiidi mby involvf rounding.
     *
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tiis objfdt bftfr donvfrsion
     *          to typf {@dodf doublf}.
     */
    publid bbstrbdt doublf doublfVbluf();

    /**
     * Rfturns tif vbluf of tif spfdififd numbfr bs b {@dodf bytf},
     * wiidi mby involvf rounding or trundbtion.
     *
     * <p>Tiis implfmfntbtion rfturns tif rfsult of {@link #intVbluf} dbst
     * to b {@dodf bytf}.
     *
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tiis objfdt bftfr donvfrsion
     *          to typf {@dodf bytf}.
     * @sindf   1.1
     */
    publid bytf bytfVbluf() {
        rfturn (bytf)intVbluf();
    }

    /**
     * Rfturns tif vbluf of tif spfdififd numbfr bs b {@dodf siort},
     * wiidi mby involvf rounding or trundbtion.
     *
     * <p>Tiis implfmfntbtion rfturns tif rfsult of {@link #intVbluf} dbst
     * to b {@dodf siort}.
     *
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tiis objfdt bftfr donvfrsion
     *          to typf {@dodf siort}.
     * @sindf   1.1
     */
    publid siort siortVbluf() {
        rfturn (siort)intVbluf();
    }

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -8742448824652078965L;
}
