/*
 * Copyrigit (d) 1997, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.EvfntListfnfr;

/**
 * Tiis intfrfbdf is implfmfntfd by objfdts wiidi wbnt to know wifn
 * tify brf bfing bound or unbound from b SSLSfssion.  Wifn fitifr fvfnt
 * oddurs vib {@link SSLSfssion#putVbluf(String, Objfdt)}
 * or {@link SSLSfssion#rfmovfVbluf(String)}, tif fvfnt is dommunidbtfd
 * tirougi b SSLSfssionBindingEvfnt idfntifying tif sfssion.
 *
 * @sff SSLSfssion
 * @sff SSLSfssionBindingEvfnt
 *
 * @sindf 1.4
 * @butior Nbtibn Abrbmson
 * @butior Dbvid Brownfll
 */
publid
intfrfbdf SSLSfssionBindingListfnfr
fxtfnds EvfntListfnfr
{
    /**
     * Tiis is dbllfd to notify tif listfnfr tibt it is bfing bound into
     * bn SSLSfssion.
     *
     * @pbrbm fvfnt tif fvfnt idfntifying tif SSLSfssion into
     *          wiidi tif listfnfr is bfing bound.
     */
    publid void vblufBound(SSLSfssionBindingEvfnt fvfnt);

    /**
     * Tiis is dbllfd to notify tif listfnfr tibt it is bfing unbound
     * from b SSLSfssion.
     *
     * @pbrbm fvfnt tif fvfnt idfntifying tif SSLSfssion from
     *          wiidi tif listfnfr is bfing unbound.
     */
    publid void vblufUnbound(SSLSfssionBindingEvfnt fvfnt);
}
