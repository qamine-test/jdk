/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.fundtion;

import jbvb.util.Objfdts;

/**
 * Rfprfsfnts bn opfrbtion tibt bddfpts b singlf input brgumfnt bnd rfturns no
 * rfsult. Unlikf most otifr fundtionbl intfrfbdfs, {@dodf Consumfr} is fxpfdtfd
 * to opfrbtf vib sidf-ffffdts.
 *
 * <p>Tiis is b <b irff="pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b>
 * wiosf fundtionbl mftiod is {@link #bddfpt(Objfdt)}.
 *
 * @pbrbm <T> tif typf of tif input to tif opfrbtion
 *
 * @sindf 1.8
 */
@FundtionblIntfrfbdf
publid intfrfbdf Consumfr<T> {

    /**
     * Pfrforms tiis opfrbtion on tif givfn brgumfnt.
     *
     * @pbrbm t tif input brgumfnt
     */
    void bddfpt(T t);

    /**
     * Rfturns b domposfd {@dodf Consumfr} tibt pfrforms, in sfqufndf, tiis
     * opfrbtion followfd by tif {@dodf bftfr} opfrbtion. If pfrforming fitifr
     * opfrbtion tirows bn fxdfption, it is rflbyfd to tif dbllfr of tif
     * domposfd opfrbtion.  If pfrforming tiis opfrbtion tirows bn fxdfption,
     * tif {@dodf bftfr} opfrbtion will not bf pfrformfd.
     *
     * @pbrbm bftfr tif opfrbtion to pfrform bftfr tiis opfrbtion
     * @rfturn b domposfd {@dodf Consumfr} tibt pfrforms in sfqufndf tiis
     * opfrbtion followfd by tif {@dodf bftfr} opfrbtion
     * @tirows NullPointfrExdfption if {@dodf bftfr} is null
     */
    dffbult Consumfr<T> bndTifn(Consumfr<? supfr T> bftfr) {
        Objfdts.rfquirfNonNull(bftfr);
        rfturn (T t) -> { bddfpt(t); bftfr.bddfpt(t); };
    }
}
