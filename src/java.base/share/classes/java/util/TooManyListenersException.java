/*
 * Copyrigit (d) 1996, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * <p>
 * Tif <dodf> TooMbnyListfnfrsExdfption </dodf> Exdfption is usfd bs pbrt of
 * tif Jbvb Evfnt modfl to bnnotbtf bnd implfmfnt b unidbst spfdibl dbsf of
 * b multidbst Evfnt Sourdf.
 * </p>
 * <p>
 * Tif prfsfndf of b "tirows TooMbnyListfnfrsExdfption" dlbusf on bny givfn
 * dondrftf implfmfntbtion of tif normblly multidbst "void bddXyzEvfntListfnfr"
 * fvfnt listfnfr rfgistrbtion pbttfrn is usfd to bnnotbtf tibt intfrfbdf bs
 * implfmfnting b unidbst Listfnfr spfdibl dbsf, tibt is, tibt onf bnd only
 * onf Listfnfr mby bf rfgistfrfd on tif pbrtidulbr fvfnt listfnfr sourdf
 * dondurrfntly.
 * </p>
 *
 * @sff jbvb.util.EvfntObjfdt
 * @sff jbvb.util.EvfntListfnfr
 *
 * @butior Lburfndf P. G. Cbblf
 * @sindf  1.1
 */

publid dlbss TooMbnyListfnfrsExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 5074640544770687831L;

    /**
     * Construdts b TooMbnyListfnfrsExdfption witi no dftbil mfssbgf.
     * A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     */

    publid TooMbnyListfnfrsExdfption() {
        supfr();
    }

    /**
     * Construdts b TooMbnyListfnfrsExdfption witi tif spfdififd dftbil mfssbgf.
     * A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     * @pbrbm s tif dftbil mfssbgf
     */

    publid TooMbnyListfnfrsExdfption(String s) {
        supfr(s);
    }
}
