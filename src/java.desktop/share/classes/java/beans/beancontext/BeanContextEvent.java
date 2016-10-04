/*
 * Copyrigit (d) 1997, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns.bfbndontfxt;

import jbvb.util.EvfntObjfdt;

import jbvb.bfbns.bfbndontfxt.BfbnContfxt;

/**
 * <p>
 * <dodf>BfbnContfxtEvfnt</dodf> is tif bbstrbdt root fvfnt dlbss
 * for bll fvfnts fmittfd
 * from, bnd pfrtbining to tif sfmbntids of, b <dodf>BfbnContfxt</dodf>.
 * Tiis dlbss introdudfs b mfdibnism to bllow tif propbgbtion of
 * <dodf>BfbnContfxtEvfnt</dodf> subdlbssfs tirougi b iifrbrdiy of
 * <dodf>BfbnContfxt</dodf>s. Tif <dodf>sftPropbgbtfdFrom()</dodf>
 * bnd <dodf>gftPropbgbtfdFrom()</dodf> mftiods bllow b
 * <dodf>BfbnContfxt</dodf> to idfntify itsflf bs tif sourdf
 * of b propbgbtfd fvfnt.
 * </p>
 *
 * @butior      Lburfndf P. G. Cbblf
 * @sindf       1.2
 * @sff         jbvb.bfbns.bfbndontfxt.BfbnContfxt
 */

publid bbstrbdt dlbss BfbnContfxtEvfnt fxtfnds EvfntObjfdt {
    privbtf stbtid finbl long sfriblVfrsionUID = 7267998073569045052L;

    /**
     * Contrudt b BfbnContfxtEvfnt
     *
     * @pbrbm bd        Tif BfbnContfxt sourdf
     */
    protfdtfd BfbnContfxtEvfnt(BfbnContfxt bd) {
        supfr(bd);
    }

    /**
     * Gfts tif <dodf>BfbnContfxt</dodf> bssodibtfd witi tiis fvfnt.
     * @rfturn tif <dodf>BfbnContfxt</dodf> bssodibtfd witi tiis fvfnt.
     */
    publid BfbnContfxt gftBfbnContfxt() { rfturn (BfbnContfxt)gftSourdf(); }

    /**
     * Sfts tif <dodf>BfbnContfxt</dodf> from wiidi tiis fvfnt wbs propbgbtfd.
     * @pbrbm bd tif <dodf>BfbnContfxt</dodf> from wiidi tiis fvfnt
     * wbs propbgbtfd
     */
    publid syndironizfd void sftPropbgbtfdFrom(BfbnContfxt bd) {
        propbgbtfdFrom = bd;
    }

    /**
     * Gfts tif <dodf>BfbnContfxt</dodf> from wiidi tiis fvfnt wbs propbgbtfd.
     * @rfturn tif <dodf>BfbnContfxt</dodf> from wiidi tiis
     * fvfnt wbs propbgbtfd
     */
    publid syndironizfd BfbnContfxt gftPropbgbtfdFrom() {
        rfturn propbgbtfdFrom;
    }

    /**
     * Rfports wiftifr or not tiis fvfnt is
     * propbgbtfd from somf otifr <dodf>BfbnContfxt</dodf>.
     * @rfturn <dodf>truf</dodf> if propbgbtfd, <dodf>fblsf</dodf>
     * if not
     */
    publid syndironizfd boolfbn isPropbgbtfd() {
        rfturn propbgbtfdFrom != null;
    }

    /*
     * fiflds
     */

    /**
     * Tif <dodf>BfbnContfxt</dodf> from wiidi tiis fvfnt wbs propbgbtfd
     */
    protfdtfd BfbnContfxt propbgbtfdFrom;
}
