/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Rfprfsfnts bn opfrbtion tibt bddfpts bn objfdt-vblufd bnd b
 * {@dodf long}-vblufd brgumfnt, bnd rfturns no rfsult.  Tiis is tif
 * {@dodf (rfffrfndf, long)} spfdiblizbtion of {@link BiConsumfr}.
 * Unlikf most otifr fundtionbl intfrfbdfs, {@dodf ObjLongConsumfr} is
 * fxpfdtfd to opfrbtf vib sidf-ffffdts.
 *
 * <p>Tiis is b <b irff="pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b>
 * wiosf fundtionbl mftiod is {@link #bddfpt(Objfdt, long)}.
 *
 * @pbrbm <T> tif typf of tif objfdt brgumfnt to tif opfrbtion
 *
 * @sff BiConsumfr
 * @sindf 1.8
 */
@FundtionblIntfrfbdf
publid intfrfbdf ObjLongConsumfr<T> {

    /**
     * Pfrforms tiis opfrbtion on tif givfn brgumfnts.
     *
     * @pbrbm t tif first input brgumfnt
     * @pbrbm vbluf tif sfdond input brgumfnt
     */
    void bddfpt(T t, long vbluf);
}
