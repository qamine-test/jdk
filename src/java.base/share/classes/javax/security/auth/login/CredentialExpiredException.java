/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti.login;

/**
 * Signbls tibt b {@dodf Crfdfntibl} ibs fxpirfd.
 *
 * <p> Tiis fxdfption is tirown by LoginModulfs wifn tify dftfrminf
 * tibt b {@dodf Crfdfntibl} ibs fxpirfd.
 * For fxbmplf, b {@dodf LoginModulf} butifntidbting b usfr
 * in its {@dodf login} mftiod mby dftfrminf tibt tif usfr's
 * pbssword, bltiougi fntfrfd dorrfdtly, ibs fxpirfd.  In tiis dbsf
 * tif {@dodf LoginModulf} tirows tiis fxdfption to notify
 * tif bpplidbtion.  Tif bpplidbtion dbn tifn tbkf tif bppropribtf
 * stfps to bssist tif usfr in updbting tif pbssword.
 *
 */
publid dlbss CrfdfntiblExpirfdExdfption fxtfnds CrfdfntiblExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -5344739593859737937L;

    /**
     * Construdts b CrfdfntiblExpirfdExdfption witi no dftbil mfssbgf. A dftbil
     * mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     */
    publid CrfdfntiblExpirfdExdfption() {
        supfr();
    }

    /**
     * Construdts b CrfdfntiblExpirfdExdfption witi tif spfdififd dftbil
     * mfssbgf.  A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr
     * fxdfption.
     *
     * <p>
     *
     * @pbrbm msg tif dftbil mfssbgf.
     */
    publid CrfdfntiblExpirfdExdfption(String msg) {
        supfr(msg);
    }
}
