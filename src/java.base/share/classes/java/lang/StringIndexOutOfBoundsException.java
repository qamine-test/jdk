/*
 * Copyrigit (d) 1994, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown by {@dodf String} mftiods to indidbtf tibt bn indfx
 * is fitifr nfgbtivf or grfbtfr tibn tif sizf of tif string.  For
 * somf mftiods sudi bs tif dibrAt mftiod, tiis fxdfption blso is
 * tirown wifn tif indfx is fqubl to tif sizf of tif string.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.lbng.String#dibrAt(int)
 * @sindf   1.0
 */
publid
dlbss StringIndfxOutOfBoundsExdfption fxtfnds IndfxOutOfBoundsExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -6762910422159637258L;

    /**
     * Construdts b {@dodf StringIndfxOutOfBoundsExdfption} witi no
     * dftbil mfssbgf.
     */
    publid StringIndfxOutOfBoundsExdfption() {
        supfr();
    }

    /**
     * Construdts b {@dodf StringIndfxOutOfBoundsExdfption} witi
     * tif spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid StringIndfxOutOfBoundsExdfption(String s) {
        supfr(s);
    }

    /**
     * Construdts b nfw {@dodf StringIndfxOutOfBoundsExdfption}
     * dlbss witi bn brgumfnt indidbting tif illfgbl indfx.
     *
     * @pbrbm   indfx   tif illfgbl indfx.
     */
    publid StringIndfxOutOfBoundsExdfption(int indfx) {
        supfr("String indfx out of rbngf: " + indfx);
    }
}
