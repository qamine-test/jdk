/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tirown to indidbtf tibt dodf ibs bttfmptfd to dbll b mftiod ibndlf
 * vib tif wrong mftiod typf.  As witi tif bytfdodf rfprfsfntbtion of
 * normbl Jbvb mftiod dblls, mftiod ibndlf dblls brf strongly typfd
 * to b spfdifid typf dfsdriptor bssodibtfd witi b dbll sitf.
 * <p>
 * Tiis fxdfption mby blso bf tirown wifn two mftiod ibndlfs brf
 * domposfd, bnd tif systfm dftfdts tibt tifir typfs dbnnot bf
 * mbtdifd up dorrfdtly.  Tiis bmounts to bn fbrly fvblubtion
 * of tif typf mismbtdi, bt mftiod ibndlf donstrudtion timf,
 * instfbd of wifn tif mismbtdifd mftiod ibndlf is dbllfd.
 *
 * @butior Join Rosf, JSR 292 EG
 * @sindf 1.7
 */
publid dlbss WrongMftiodTypfExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 292L;

    /**
     * Construdts b {@dodf WrongMftiodTypfExdfption} witi no dftbil mfssbgf.
     */
    publid WrongMftiodTypfExdfption() {
        supfr();
    }

    /**
     * Construdts b {@dodf WrongMftiodTypfExdfption} witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm s tif dftbil mfssbgf.
     */
    publid WrongMftiodTypfExdfption(String s) {
        supfr(s);
    }

    /**
     * Construdts b {@dodf WrongMftiodTypfExdfption} witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm s tif dftbil mfssbgf.
     * @pbrbm dbusf tif dbusf of tif fxdfption, or null.
     */
    //FIXME: mbkf tiis publid in MR1
    /*non-publid*/ WrongMftiodTypfExdfption(String s, Tirowbblf dbusf) {
        supfr(s, dbusf);
    }

    /**
     * Construdts b {@dodf WrongMftiodTypfExdfption} witi tif spfdififd
     * dbusf.
     *
     * @pbrbm dbusf tif dbusf of tif fxdfption, or null.
     */
    //FIXME: mbkf tiis publid in MR1
    /*non-publid*/ WrongMftiodTypfExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }
}
