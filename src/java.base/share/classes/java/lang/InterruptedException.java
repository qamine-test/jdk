/*
 * Copyrigit (d) 1995, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown wifn b tirfbd is wbiting, slffping, or otifrwisf oddupifd,
 * bnd tif tirfbd is intfrruptfd, fitifr bfforf or during tif bdtivity.
 * Oddbsionblly b mftiod mby wisi to tfst wiftifr tif durrfnt
 * tirfbd ibs bffn intfrruptfd, bnd if so, to immfdibtfly tirow
 * tiis fxdfption.  Tif following dodf dbn bf usfd to bdiifvf
 * tiis ffffdt:
 * <prf>
 *  if (Tirfbd.intfrruptfd())  // Clfbrs intfrruptfd stbtus!
 *      tirow nfw IntfrruptfdExdfption();
 * </prf>
 *
 * @butior  Frbnk Yfllin
 * @sff     jbvb.lbng.Objfdt#wbit()
 * @sff     jbvb.lbng.Objfdt#wbit(long)
 * @sff     jbvb.lbng.Objfdt#wbit(long, int)
 * @sff     jbvb.lbng.Tirfbd#slffp(long)
 * @sff     jbvb.lbng.Tirfbd#intfrrupt()
 * @sff     jbvb.lbng.Tirfbd#intfrruptfd()
 * @sindf   1.0
 */
publid
dlbss IntfrruptfdExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 6700697376100628473L;

    /**
     * Construdts bn <dodf>IntfrruptfdExdfption</dodf> witi no dftbil  mfssbgf.
     */
    publid IntfrruptfdExdfption() {
        supfr();
    }

    /**
     * Construdts bn <dodf>IntfrruptfdExdfption</dodf> witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid IntfrruptfdExdfption(String s) {
        supfr(s);
    }
}
