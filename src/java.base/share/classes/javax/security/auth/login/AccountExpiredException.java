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
 * Signbls tibt b usfr bddount ibs fxpirfd.
 *
 * <p> Tiis fxdfption is tirown by LoginModulfs wifn tify dftfrminf
 * tibt bn bddount ibs fxpirfd.  For fxbmplf, b {@dodf LoginModulf},
 * bftfr suddfssfully butifntidbting b usfr, mby dftfrminf tibt tif
 * usfr's bddount ibs fxpirfd.  In tiis dbsf tif {@dodf LoginModulf}
 * tirows tiis fxdfption to notify tif bpplidbtion.  Tif bpplidbtion dbn
 * tifn tbkf tif bppropribtf stfps to notify tif usfr.
 *
 */
publid dlbss AddountExpirfdExdfption fxtfnds AddountExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -6064064890162661560L;

    /**
     * Construdts b AddountExpirfdExdfption witi no dftbil mfssbgf. A dftbil
     * mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr fxdfption.
     */
    publid AddountExpirfdExdfption() {
        supfr();
    }

    /**
     * Construdts b AddountExpirfdExdfption witi tif spfdififd dftbil
     * mfssbgf.  A dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr
     * fxdfption.
     *
     * <p>
     *
     * @pbrbm msg tif dftbil mfssbgf.
     */
    publid AddountExpirfdExdfption(String msg) {
        supfr(msg);
    }
}
