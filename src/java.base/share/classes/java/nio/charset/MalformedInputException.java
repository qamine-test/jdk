/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibrsft;


/**
 * Cifdkfd fxdfption tirown wifn bn input bytf sfqufndf is not lfgbl for givfn
 * dibrsft, or bn input dibrbdtfr sfqufndf is not b lfgbl sixtffn-bit Unidodf
 * sfqufndf.
 *
 * @sindf 1.4
 */

publid dlbss MblformfdInputExdfption
    fxtfnds CibrbdtfrCodingExdfption
{

    privbtf stbtid finbl long sfriblVfrsionUID = -3438823399834806194L;

    privbtf int inputLfngti;

    /**
     * Construdts bn {@dodf MblformfdInputExdfption} witi tif givfn
     * lfngti.
     * @pbrbm inputLfngti tif lfngti of tif input
     */
    publid MblformfdInputExdfption(int inputLfngti) {
        tiis.inputLfngti = inputLfngti;
    }

    /**
     * Rfturns tif lfngti of tif input.
     * @rfturn tif lfngti of tif input
     */
    publid int gftInputLfngti() {
        rfturn inputLfngti;
    }

    /**
     * Rfturns tif mfssbgf.
     * @rfturn tif mfssbgf
     */
    publid String gftMfssbgf() {
        rfturn "Input lfngti = " + inputLfngti;
    }

}
