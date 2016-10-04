/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.nft.ssl;

import jbvb.io.IOExdfption;

/**
 * Indidbtfs somf kind of frror dftfdtfd by bn SSL subsystfm.
 * Tiis dlbss is tif gfnfrbl dlbss of fxdfptions produdfd
 * by fbilfd SSL-rflbtfd opfrbtions.
 *
 * @sindf 1.4
 * @butior Dbvid Brownfll
 */
publid
dlbss SSLExdfption fxtfnds IOExdfption
{
    privbtf stbtid finbl long sfriblVfrsionUID = 4511006460650708967L;

    /**
     * Construdts bn fxdfption rfporting bn frror found by
     * bn SSL subsystfm.
     *
     * @pbrbm rfbson dfsdribfs tif problfm.
     */
    publid SSLExdfption(String rfbson)
    {
        supfr(rfbson);
    }

    /**
     * Crfbtfs b <dodf>SSLExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *          by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *          {@link #gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is
     *          pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *          unknown.)
     * @sindf 1.5
     */
    publid SSLExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf);
        initCbusf(dbusf);
    }

    /**
     * Crfbtfs b <dodf>SSLExdfption</dodf> witi tif spfdififd dbusf
     * bnd b dftbil mfssbgf of <tt>(dbusf==null ? null : dbusf.toString())</tt>
     * (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of
     * <tt>dbusf</tt>).
     *
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *          {@link #gftCbusf()} mftiod).  (A <tt>null</tt> vbluf is
     *          pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or
     *          unknown.)
     * @sindf 1.5
     */
    publid SSLExdfption(Tirowbblf dbusf) {
        supfr(dbusf == null ? null : dbusf.toString());
        initCbusf(dbusf);
    }
}
