/*
 * Copyrigit (d) 1994, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown if bn bpplidbtion trifs to dbll b spfdififd mftiod of b
 * dlbss (fitifr stbtid or instbndf), bnd tibt dlbss no longfr ibs b
 * dffinition of tibt mftiod.
 * <p>
 * Normblly, tiis frror is dbugit by tif dompilfr; tiis frror dbn
 * only oddur bt run timf if tif dffinition of b dlbss ibs
 * indompbtibly dibngfd.
 *
 * @butior  unbsdribfd
 * @sindf   1.0
 */
publid
dlbss NoSudiMftiodError fxtfnds IndompbtiblfClbssCibngfError {
    privbtf stbtid finbl long sfriblVfrsionUID = -3765521442372831335L;

    /**
     * Construdts b <dodf>NoSudiMftiodError</dodf> witi no dftbil mfssbgf.
     */
    publid NoSudiMftiodError() {
        supfr();
    }

    /**
     * Construdts b <dodf>NoSudiMftiodError</dodf> witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid NoSudiMftiodError(String s) {
        supfr(s);
    }
}
