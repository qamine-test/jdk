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
 * Tirown to indidbtf tibt bn brrby ibs bffn bddfssfd witi bn
 * illfgbl indfx. Tif indfx is fitifr nfgbtivf or grfbtfr tibn or
 * fqubl to tif sizf of tif brrby.
 *
 * @butior  unbsdribfd
 * @sindf   1.0
 */
publid
dlbss ArrbyIndfxOutOfBoundsExdfption fxtfnds IndfxOutOfBoundsExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -5116101128118950844L;

    /**
     * Construdts bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> witi no
     * dftbil mfssbgf.
     */
    publid ArrbyIndfxOutOfBoundsExdfption() {
        supfr();
    }

    /**
     * Construdts b nfw <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * dlbss witi bn brgumfnt indidbting tif illfgbl indfx.
     *
     * @pbrbm   indfx   tif illfgbl indfx.
     */
    publid ArrbyIndfxOutOfBoundsExdfption(int indfx) {
        supfr("Arrby indfx out of rbngf: " + indfx);
    }

    /**
     * Construdts bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> dlbss
     * witi tif spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid ArrbyIndfxOutOfBoundsExdfption(String s) {
        supfr(s);
    }
}
