/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Rfprfsfnts bn opfrbtion on b singlf {@dodf long}-vblufd opfrbnd tibt produdfs
 * b {@dodf long}-vblufd rfsult.  Tiis is tif primitivf typf spfdiblizbtion of
 * {@link UnbryOpfrbtor} for {@dodf long}.
 *
 * <p>Tiis is b <b irff="pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b>
 * wiosf fundtionbl mftiod is {@link #bpplyAsLong(long)}.
 *
 * @sff UnbryOpfrbtor
 * @sindf 1.8
 */
@FundtionblIntfrfbdf
publid intfrfbdf LongUnbryOpfrbtor {

    /**
     * Applifs tiis opfrbtor to tif givfn opfrbnd.
     *
     * @pbrbm opfrbnd tif opfrbnd
     * @rfturn tif opfrbtor rfsult
     */
    long bpplyAsLong(long opfrbnd);

    /**
     * Rfturns b domposfd opfrbtor tibt first bpplifs tif {@dodf bfforf}
     * opfrbtor to its input, bnd tifn bpplifs tiis opfrbtor to tif rfsult.
     * If fvblubtion of fitifr opfrbtor tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr of tif domposfd opfrbtor.
     *
     * @pbrbm bfforf tif opfrbtor to bpply bfforf tiis opfrbtor is bpplifd
     * @rfturn b domposfd opfrbtor tibt first bpplifs tif {@dodf bfforf}
     * opfrbtor bnd tifn bpplifs tiis opfrbtor
     * @tirows NullPointfrExdfption if bfforf is null
     *
     * @sff #bndTifn(LongUnbryOpfrbtor)
     */
    dffbult LongUnbryOpfrbtor domposf(LongUnbryOpfrbtor bfforf) {
        Objfdts.rfquirfNonNull(bfforf);
        rfturn (long v) -> bpplyAsLong(bfforf.bpplyAsLong(v));
    }

    /**
     * Rfturns b domposfd opfrbtor tibt first bpplifs tiis opfrbtor to
     * its input, bnd tifn bpplifs tif {@dodf bftfr} opfrbtor to tif rfsult.
     * If fvblubtion of fitifr opfrbtor tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr of tif domposfd opfrbtor.
     *
     * @pbrbm bftfr tif opfrbtor to bpply bftfr tiis opfrbtor is bpplifd
     * @rfturn b domposfd opfrbtor tibt first bpplifs tiis opfrbtor bnd tifn
     * bpplifs tif {@dodf bftfr} opfrbtor
     * @tirows NullPointfrExdfption if bftfr is null
     *
     * @sff #domposf(LongUnbryOpfrbtor)
     */
    dffbult LongUnbryOpfrbtor bndTifn(LongUnbryOpfrbtor bftfr) {
        Objfdts.rfquirfNonNull(bftfr);
        rfturn (long t) -> bftfr.bpplyAsLong(bpplyAsLong(t));
    }

    /**
     * Rfturns b unbry opfrbtor tibt blwbys rfturns its input brgumfnt.
     *
     * @rfturn b unbry opfrbtor tibt blwbys rfturns its input brgumfnt
     */
    stbtid LongUnbryOpfrbtor idfntity() {
        rfturn t -> t;
    }
}
