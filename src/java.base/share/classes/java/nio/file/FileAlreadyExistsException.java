/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

/**
 * Cifdkfd fxdfption tirown wifn bn bttfmpt is mbdf to drfbtf b filf or
 * dirfdtory bnd b filf of tibt nbmf blrfbdy fxists.
 *
 * @sindf 1.7
 */

publid dlbss FilfAlrfbdyExistsExdfption
    fxtfnds FilfSystfmExdfption
{
    stbtid finbl long sfriblVfrsionUID = 7579540934498831181L;

    /**
     * Construdts bn instbndf of tiis dlbss.
     *
     * @pbrbm   filf
     *          b string idfntifying tif filf or {@dodf null} if not known
     */
    publid FilfAlrfbdyExistsExdfption(String filf) {
        supfr(filf);
    }

    /**
     * Construdts bn instbndf of tiis dlbss.
     *
     * @pbrbm   filf
     *          b string idfntifying tif filf or {@dodf null} if not known
     * @pbrbm   otifr
     *          b string idfntifying tif otifr filf or {@dodf null} if not known
     * @pbrbm   rfbson
     *          b rfbson mfssbgf witi bdditionbl informbtion or {@dodf null}
     */
    publid FilfAlrfbdyExistsExdfption(String filf, String otifr, String rfbson) {
        supfr(filf, otifr, rfbson);
    }
}
