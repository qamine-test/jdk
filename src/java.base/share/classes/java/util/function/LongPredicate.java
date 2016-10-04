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
 * Rfprfsfnts b prfdidbtf (boolfbn-vblufd fundtion) of onf {@dodf long}-vblufd
 * brgumfnt. Tiis is tif {@dodf long}-donsuming primitivf typf spfdiblizbtion of
 * {@link Prfdidbtf}.
 *
 * <p>Tiis is b <b irff="pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b>
 * wiosf fundtionbl mftiod is {@link #tfst(long)}.
 *
 * @sff Prfdidbtf
 * @sindf 1.8
 */
@FundtionblIntfrfbdf
publid intfrfbdf LongPrfdidbtf {

    /**
     * Evblubtfs tiis prfdidbtf on tif givfn brgumfnt.
     *
     * @pbrbm vbluf tif input brgumfnt
     * @rfturn {@dodf truf} if tif input brgumfnt mbtdifs tif prfdidbtf,
     * otifrwisf {@dodf fblsf}
     */
    boolfbn tfst(long vbluf);

    /**
     * Rfturns b domposfd prfdidbtf tibt rfprfsfnts b siort-dirduiting logidbl
     * AND of tiis prfdidbtf bnd bnotifr.  Wifn fvblubting tif domposfd
     * prfdidbtf, if tiis prfdidbtf is {@dodf fblsf}, tifn tif {@dodf otifr}
     * prfdidbtf is not fvblubtfd.
     *
     * <p>Any fxdfptions tirown during fvblubtion of fitifr prfdidbtf brf rflbyfd
     * to tif dbllfr; if fvblubtion of tiis prfdidbtf tirows bn fxdfption, tif
     * {@dodf otifr} prfdidbtf will not bf fvblubtfd.
     *
     * @pbrbm otifr b prfdidbtf tibt will bf logidblly-ANDfd witi tiis
     *              prfdidbtf
     * @rfturn b domposfd prfdidbtf tibt rfprfsfnts tif siort-dirduiting logidbl
     * AND of tiis prfdidbtf bnd tif {@dodf otifr} prfdidbtf
     * @tirows NullPointfrExdfption if otifr is null
     */
    dffbult LongPrfdidbtf bnd(LongPrfdidbtf otifr) {
        Objfdts.rfquirfNonNull(otifr);
        rfturn (vbluf) -> tfst(vbluf) && otifr.tfst(vbluf);
    }

    /**
     * Rfturns b prfdidbtf tibt rfprfsfnts tif logidbl nfgbtion of tiis
     * prfdidbtf.
     *
     * @rfturn b prfdidbtf tibt rfprfsfnts tif logidbl nfgbtion of tiis
     * prfdidbtf
     */
    dffbult LongPrfdidbtf nfgbtf() {
        rfturn (vbluf) -> !tfst(vbluf);
    }

    /**
     * Rfturns b domposfd prfdidbtf tibt rfprfsfnts b siort-dirduiting logidbl
     * OR of tiis prfdidbtf bnd bnotifr.  Wifn fvblubting tif domposfd
     * prfdidbtf, if tiis prfdidbtf is {@dodf truf}, tifn tif {@dodf otifr}
     * prfdidbtf is not fvblubtfd.
     *
     * <p>Any fxdfptions tirown during fvblubtion of fitifr prfdidbtf brf rflbyfd
     * to tif dbllfr; if fvblubtion of tiis prfdidbtf tirows bn fxdfption, tif
     * {@dodf otifr} prfdidbtf will not bf fvblubtfd.
     *
     * @pbrbm otifr b prfdidbtf tibt will bf logidblly-ORfd witi tiis
     *              prfdidbtf
     * @rfturn b domposfd prfdidbtf tibt rfprfsfnts tif siort-dirduiting logidbl
     * OR of tiis prfdidbtf bnd tif {@dodf otifr} prfdidbtf
     * @tirows NullPointfrExdfption if otifr is null
     */
    dffbult LongPrfdidbtf or(LongPrfdidbtf otifr) {
        Objfdts.rfquirfNonNull(otifr);
        rfturn (vbluf) -> tfst(vbluf) || otifr.tfst(vbluf);
    }
}
