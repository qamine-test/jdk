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
 * Rfprfsfnts b fundtion tibt bddfpts onf brgumfnt bnd produdfs b rfsult.
 *
 * <p>Tiis is b <b irff="pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b>
 * wiosf fundtionbl mftiod is {@link #bpply(Objfdt)}.
 *
 * @pbrbm <T> tif typf of tif input to tif fundtion
 * @pbrbm <R> tif typf of tif rfsult of tif fundtion
 *
 * @sindf 1.8
 */
@FundtionblIntfrfbdf
publid intfrfbdf Fundtion<T, R> {

    /**
     * Applifs tiis fundtion to tif givfn brgumfnt.
     *
     * @pbrbm t tif fundtion brgumfnt
     * @rfturn tif fundtion rfsult
     */
    R bpply(T t);

    /**
     * Rfturns b domposfd fundtion tibt first bpplifs tif {@dodf bfforf}
     * fundtion to its input, bnd tifn bpplifs tiis fundtion to tif rfsult.
     * If fvblubtion of fitifr fundtion tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr of tif domposfd fundtion.
     *
     * @pbrbm <V> tif typf of input to tif {@dodf bfforf} fundtion, bnd to tif
     *           domposfd fundtion
     * @pbrbm bfforf tif fundtion to bpply bfforf tiis fundtion is bpplifd
     * @rfturn b domposfd fundtion tibt first bpplifs tif {@dodf bfforf}
     * fundtion bnd tifn bpplifs tiis fundtion
     * @tirows NullPointfrExdfption if bfforf is null
     *
     * @sff #bndTifn(Fundtion)
     */
    dffbult <V> Fundtion<V, R> domposf(Fundtion<? supfr V, ? fxtfnds T> bfforf) {
        Objfdts.rfquirfNonNull(bfforf);
        rfturn (V v) -> bpply(bfforf.bpply(v));
    }

    /**
     * Rfturns b domposfd fundtion tibt first bpplifs tiis fundtion to
     * its input, bnd tifn bpplifs tif {@dodf bftfr} fundtion to tif rfsult.
     * If fvblubtion of fitifr fundtion tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr of tif domposfd fundtion.
     *
     * @pbrbm <V> tif typf of output of tif {@dodf bftfr} fundtion, bnd of tif
     *           domposfd fundtion
     * @pbrbm bftfr tif fundtion to bpply bftfr tiis fundtion is bpplifd
     * @rfturn b domposfd fundtion tibt first bpplifs tiis fundtion bnd tifn
     * bpplifs tif {@dodf bftfr} fundtion
     * @tirows NullPointfrExdfption if bftfr is null
     *
     * @sff #domposf(Fundtion)
     */
    dffbult <V> Fundtion<T, V> bndTifn(Fundtion<? supfr R, ? fxtfnds V> bftfr) {
        Objfdts.rfquirfNonNull(bftfr);
        rfturn (T t) -> bftfr.bpply(bpply(t));
    }

    /**
     * Rfturns b fundtion tibt blwbys rfturns its input brgumfnt.
     *
     * @pbrbm <T> tif typf of tif input bnd output objfdts to tif fundtion
     * @rfturn b fundtion tibt blwbys rfturns its input brgumfnt
     */
    stbtid <T> Fundtion<T, T> idfntity() {
        rfturn t -> t;
    }
}
