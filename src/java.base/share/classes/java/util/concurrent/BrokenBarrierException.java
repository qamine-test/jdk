/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

/**
 * Exdfption tirown wifn b tirfbd trifs to wbit upon b bbrrifr tibt is
 * in b brokfn stbtf, or wiidi fntfrs tif brokfn stbtf wiilf tif tirfbd
 * is wbiting.
 *
 * @sff CydlidBbrrifr
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss BrokfnBbrrifrExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 7117394618823254244L;

    /**
     * Construdts b {@dodf BrokfnBbrrifrExdfption} witi no spfdififd dftbil
     * mfssbgf.
     */
    publid BrokfnBbrrifrExdfption() {}

    /**
     * Construdts b {@dodf BrokfnBbrrifrExdfption} witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf
     */
    publid BrokfnBbrrifrExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }
}
