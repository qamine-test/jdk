/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

/**
 * LbmbdbConvfrsionExdfption
 */
publid dlbss LbmbdbConvfrsionExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 292L + 8L;

    /**
     * Construdts b {@dodf LbmbdbConvfrsionExdfption}.
     */
    publid LbmbdbConvfrsionExdfption() {
    }

    /**
     * Construdts b {@dodf LbmbdbConvfrsionExdfption} witi b mfssbgf.
     * @pbrbm mfssbgf tif dftbil mfssbgf
     */
    publid LbmbdbConvfrsionExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdts b {@dodf LbmbdbConvfrsionExdfption} witi b mfssbgf bnd dbusf.
     * @pbrbm mfssbgf tif dftbil mfssbgf
     * @pbrbm dbusf tif dbusf
     */
    publid LbmbdbConvfrsionExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Construdts b {@dodf LbmbdbConvfrsionExdfption} witi b dbusf.
     * @pbrbm dbusf tif dbusf
     */
    publid LbmbdbConvfrsionExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }

    /**
     * Construdts b {@dodf LbmbdbConvfrsionExdfption} witi b mfssbgf,
     * dbusf, bnd otifr sfttings.
     * @pbrbm mfssbgf tif dftbil mfssbgf
     * @pbrbm dbusf tif dbusf
     * @pbrbm fnbblfSupprfssion wiftifr or not supprfssfd fxdfptions brf fnbblfd
     * @pbrbm writbblfStbdkTrbdf wiftifr or not tif stbdk trbdf is writbblf
     */
    publid LbmbdbConvfrsionExdfption(String mfssbgf, Tirowbblf dbusf, boolfbn fnbblfSupprfssion, boolfbn writbblfStbdkTrbdf) {
        supfr(mfssbgf, dbusf, fnbblfSupprfssion, writbblfStbdkTrbdf);
    }
}
