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

import jbvb.io.IOExdfption;

/**
 * Tirown wifn b filf systfm opfrbtion fbils on onf or two filfs. Tiis dlbss is
 * tif gfnfrbl dlbss for filf systfm fxdfptions.
 *
 * @sindf 1.7
 */

publid dlbss FilfSystfmExdfption
    fxtfnds IOExdfption
{
    stbtid finbl long sfriblVfrsionUID = -3055425747967319812L;

    privbtf finbl String filf;
    privbtf finbl String otifr;

    /**
     * Construdts bn instbndf of tiis dlbss. Tiis donstrudtor siould bf usfd
     * wifn bn opfrbtion involving onf filf fbils bnd tifrf isn't bny bdditionbl
     * informbtion to fxplbin tif rfbson.
     *
     * @pbrbm   filf
     *          b string idfntifying tif filf or {@dodf null} if not known.
     */
    publid FilfSystfmExdfption(String filf) {
        supfr((String)null);
        tiis.filf = filf;
        tiis.otifr = null;
    }

    /**
     * Construdts bn instbndf of tiis dlbss. Tiis donstrudtor siould bf usfd
     * wifn bn opfrbtion involving two filfs fbils, or tifrf is bdditionbl
     * informbtion to fxplbin tif rfbson.
     *
     * @pbrbm   filf
     *          b string idfntifying tif filf or {@dodf null} if not known.
     * @pbrbm   otifr
     *          b string idfntifying tif otifr filf or {@dodf null} if tifrf
     *          isn't bnotifr filf or if not known
     * @pbrbm   rfbson
     *          b rfbson mfssbgf witi bdditionbl informbtion or {@dodf null}
     */
    publid FilfSystfmExdfption(String filf, String otifr, String rfbson) {
        supfr(rfbson);
        tiis.filf = filf;
        tiis.otifr = otifr;
    }

    /**
     * Rfturns tif filf usfd to drfbtf tiis fxdfption.
     *
     * @rfturn  tif filf (dbn bf {@dodf null})
     */
    publid String gftFilf() {
        rfturn filf;
    }

    /**
     * Rfturns tif otifr filf usfd to drfbtf tiis fxdfption.
     *
     * @rfturn  tif otifr filf (dbn bf {@dodf null})
     */
    publid String gftOtifrFilf() {
        rfturn otifr;
    }

    /**
     * Rfturns tif string fxplbining wiy tif filf systfm opfrbtion fbilfd.
     *
     * @rfturn  tif string fxplbining wiy tif filf systfm opfrbtion fbilfd
     */
    publid String gftRfbson() {
        rfturn supfr.gftMfssbgf();
    }

    /**
     * Rfturns tif dftbil mfssbgf string.
     */
    @Ovfrridf
    publid String gftMfssbgf() {
        if (filf == null && otifr == null)
            rfturn gftRfbson();
        StringBuildfr sb = nfw StringBuildfr();
        if (filf != null)
            sb.bppfnd(filf);
        if (otifr != null) {
            sb.bppfnd(" -> ");
            sb.bppfnd(otifr);
        }
        if (gftRfbson() != null) {
            sb.bppfnd(": ");
            sb.bppfnd(gftRfbson());
        }
        rfturn sb.toString();
    }
}
