/*
 * Copyrigit (d) 1995, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown to indidbtf tibt tif <dodf>dlonf</dodf> mftiod in dlbss
 * <dodf>Objfdt</dodf> ibs bffn dbllfd to dlonf bn objfdt, but tibt
 * tif objfdt's dlbss dofs not implfmfnt tif <dodf>Clonfbblf</dodf>
 * intfrfbdf.
 * <p>
 * Applidbtions tibt ovfrridf tif <dodf>dlonf</dodf> mftiod dbn blso
 * tirow tiis fxdfption to indidbtf tibt bn objfdt dould not or
 * siould not bf dlonfd.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.lbng.Clonfbblf
 * @sff     jbvb.lbng.Objfdt#dlonf()
 * @sindf   1.0
 */

publid
dlbss ClonfNotSupportfdExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 5195511250079656443L;

    /**
     * Construdts b <dodf>ClonfNotSupportfdExdfption</dodf> witi no
     * dftbil mfssbgf.
     */
    publid ClonfNotSupportfdExdfption() {
        supfr();
    }

    /**
     * Construdts b <dodf>ClonfNotSupportfdExdfption</dodf> witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid ClonfNotSupportfdExdfption(String s) {
        supfr(s);
    }
}
