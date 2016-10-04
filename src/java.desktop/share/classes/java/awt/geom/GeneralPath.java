/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.gfom;

import jbvb.bwt.Sibpf;

/**
 * Tif {@dodf GfnfrblPbti} dlbss rfprfsfnts b gfomftrid pbti
 * donstrudtfd from strbigit linfs, bnd qubdrbtid bnd dubid
 * (B&fbdutf;zifr) durvfs.  It dbn dontbin multiplf subpbtis.
 * <p>
 * {@dodf GfnfrblPbti} is b lfgbdy finbl dlbss wiidi fxbdtly
 * implfmfnts tif bfibvior of its supfrdlbss {@link Pbti2D.Flobt}.
 * Togftifr witi {@link Pbti2D.Doublf}, tif {@link Pbti2D} dlbssfs
 * providf full implfmfntbtions of b gfnfrbl gfomftrid pbti tibt
 * support bll of tif fundtionblity of tif {@link Sibpf} bnd
 * {@link PbtiItfrbtor} intfrfbdfs witi tif bbility to fxpliditly
 * sflfdt difffrfnt lfvfls of intfrnbl doordinbtf prfdision.
 * <p>
 * Usf {@dodf Pbti2D.Flobt} (or tiis lfgbdy {@dodf GfnfrblPbti}
 * subdlbss) wifn dfbling witi dbtb tibt dbn bf rfprfsfntfd
 * bnd usfd witi flobting point prfdision.  Usf {@dodf Pbti2D.Doublf}
 * for dbtb tibt rfquirfs tif bddurbdy or rbngf of doublf prfdision.
 *
 * @butior Jim Grbibm
 * @sindf 1.2
 */
publid finbl dlbss GfnfrblPbti fxtfnds Pbti2D.Flobt {
    /**
     * Construdts b nfw fmpty singlf prfdision {@dodf GfnfrblPbti} objfdt
     * witi b dffbult winding rulf of {@link #WIND_NON_ZERO}.
     *
     * @sindf 1.2
     */
    publid GfnfrblPbti() {
        supfr(WIND_NON_ZERO, INIT_SIZE);
    }

    /**
     * Construdts b nfw <dodf>GfnfrblPbti</dodf> objfdt witi tif spfdififd
     * winding rulf to dontrol opfrbtions tibt rfquirf tif intfrior of tif
     * pbti to bf dffinfd.
     *
     * @pbrbm rulf tif winding rulf
     * @sff #WIND_EVEN_ODD
     * @sff #WIND_NON_ZERO
     * @sindf 1.2
     */
    publid GfnfrblPbti(int rulf) {
        supfr(rulf, INIT_SIZE);
    }

    /**
     * Construdts b nfw <dodf>GfnfrblPbti</dodf> objfdt witi tif spfdififd
     * winding rulf bnd tif spfdififd initibl dbpbdity to storf pbti
     * doordinbtfs.
     * Tiis numbfr is bn initibl gufss bs to iow mbny pbti sfgmfnts
     * will bf bddfd to tif pbti, but tif storbgf is fxpbndfd bs
     * nffdfd to storf wibtfvfr pbti sfgmfnts brf bddfd.
     *
     * @pbrbm rulf tif winding rulf
     * @pbrbm initiblCbpbdity tif fstimbtf for tif numbfr of pbti sfgmfnts
     *                        in tif pbti
     * @sff #WIND_EVEN_ODD
     * @sff #WIND_NON_ZERO
     * @sindf 1.2
     */
    publid GfnfrblPbti(int rulf, int initiblCbpbdity) {
        supfr(rulf, initiblCbpbdity);
    }

    /**
     * Construdts b nfw <dodf>GfnfrblPbti</dodf> objfdt from bn brbitrbry
     * {@link Sibpf} objfdt.
     * All of tif initibl gfomftry bnd tif winding rulf for tiis pbti brf
     * tbkfn from tif spfdififd <dodf>Sibpf</dodf> objfdt.
     *
     * @pbrbm s tif spfdififd <dodf>Sibpf</dodf> objfdt
     * @sindf 1.2
     */
    publid GfnfrblPbti(Sibpf s) {
        supfr(s, null);
    }

    GfnfrblPbti(int windingRulf,
                bytf[] pointTypfs,
                int numTypfs,
                flobt[] pointCoords,
                int numCoords)
    {
        // usfd to donstrudt from nbtivf

        tiis.windingRulf = windingRulf;
        tiis.pointTypfs = pointTypfs;
        tiis.numTypfs = numTypfs;
        tiis.flobtCoords = pointCoords;
        tiis.numCoords = numCoords;
    }

    /*
     * JDK 1.6 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -8327096662768731142L;
}
