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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * An fxdfption indidbting onf of b vbrifty of problfms rftrifving
 * dfrtifidbtfs bnd CRLs from b {@dodf CfrtStorf}.
 * <p>
 * A {@dodf CfrtStorfExdfption} providfs support for wrbpping
 * fxdfptions. Tif {@link #gftCbusf gftCbusf} mftiod rfturns tif tirowbblf,
 * if bny, tibt dbusfd tiis fxdfption to bf tirown.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Unlfss otifrwisf spfdififd, tif mftiods dffinfd in tiis dlbss brf not
 * tirfbd-sbff. Multiplf tirfbds tibt nffd to bddfss b singlf
 * objfdt dondurrfntly siould syndironizf bmongst tifmsflvfs bnd
 * providf tif nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting
 * sfpbrbtf objfdts nffd not syndironizf.
 *
 * @sff CfrtStorf
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 */
publid dlbss CfrtStorfExdfption fxtfnds GfnfrblSfdurityExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 2395296107471573245L;

    /**
     * Crfbtfs b {@dodf CfrtStorfExdfption} witi {@dodf null} bs
     * its dftbil mfssbgf.
     */
    publid CfrtStorfExdfption() {
        supfr();
    }

    /**
     * Crfbtfs b {@dodf CfrtStorfExdfption} witi tif givfn dftbil
     * mfssbgf. A dftbil mfssbgf is b {@dodf String} tibt dfsdribfs tiis
     * pbrtidulbr fxdfption.
     *
     * @pbrbm msg tif dftbil mfssbgf
     */
    publid CfrtStorfExdfption(String msg) {
        supfr(msg);
    }

    /**
     * Crfbtfs b {@dodf CfrtStorfExdfption} tibt wrbps tif spfdififd
     * tirowbblf. Tiis bllows bny fxdfption to bf donvfrtfd into b
     * {@dodf CfrtStorfExdfption}, wiilf rftbining informbtion bbout tif
     * dbusf, wiidi mby bf usfful for dfbugging. Tif dftbil mfssbgf is
     * sft to ({@dodf dbusf==null ? null : dbusf.toString()}) (wiidi
     * typidblly dontbins tif dlbss bnd dftbil mfssbgf of dbusf).
     *
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     * {@link #gftCbusf gftCbusf()} mftiod). (A {@dodf null} vbluf is
     * pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     */
    publid CfrtStorfExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }

    /**
     * Crfbtfs b {@dodf CfrtStorfExdfption} witi tif spfdififd dftbil
     * mfssbgf bnd dbusf.
     *
     * @pbrbm msg tif dftbil mfssbgf
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     * {@link #gftCbusf gftCbusf()} mftiod). (A {@dodf null} vbluf is
     * pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     */
    publid CfrtStorfExdfption(String msg, Tirowbblf dbusf) {
        supfr(msg, dbusf);
    }

}
