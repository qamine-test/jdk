/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.util.List;
import jbvb.sfdurity.dfrt.CfrtPbtiBuildfrExdfption;

/**
 * Tiis is b subdlbss of tif gfnfrid <dodf>CfrtPbtiBuildfrExdfption</dodf>.
 * It dontbins bn bdjbdfndy list witi informbtion rfgbrding tif unsuddfssful
 * pbtis tibt tif SunCfrtPbtiBuildfr trifd.
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 * @sff         CfrtPbtiBuildfrExdfption
 */
publid dlbss SunCfrtPbtiBuildfrExdfption fxtfnds CfrtPbtiBuildfrExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -7814288414129264709L;

    /**
     * @sfribl
     */
    privbtf trbnsifnt AdjbdfndyList bdjList;

    /**
     * Construdts b <dodf>SunCfrtPbtiBuildfrExdfption</dodf> witi
     * <dodf>null</dodf> bs its dftbil mfssbgf.
     */
    publid SunCfrtPbtiBuildfrExdfption() {
        supfr();
    }

    /**
     * Construdts b <dodf>SunCfrtPbtiBuildfrExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf. A dftbil mfssbgf is b <dodf>String</dodf> tibt
     * dfsdribfs tiis pbrtidulbr fxdfption.
     *
     * @pbrbm msg tif dftbil mfssbgf
     */
    publid SunCfrtPbtiBuildfrExdfption(String msg) {
        supfr(msg);
    }

    /**
     * Construdts b <dodf>SunCfrtPbtiBuildfrExdfption</dodf> tibt wrbps tif
     * spfdififd tirowbblf. Tiis bllows bny fxdfption to bf donvfrtfd into b
     * <dodf>SunCfrtPbtiBuildfrExdfption</dodf>, wiilf rftbining informbtion
     * bbout tif dbusf, wiidi mby bf usfful for dfbugging. Tif dftbil mfssbgf is
     * sft to (<dodf>dbusf==null ? null : dbusf.toString()</dodf>) (wiidi
     * typidblly dontbins tif dlbss bnd dftbil mfssbgf of dbusf).
     *
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     * {@link #gftCbusf gftCbusf()} mftiod). (A <dodf>null</dodf> vbluf is
     * pfrmittfd, bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * root dbusf.
     */
    publid SunCfrtPbtiBuildfrExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }

    /**
     * Crfbtfs b <dodf>SunCfrtPbtiBuildfrExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm msg tif dftbil mfssbgf
     * @pbrbm dbusf tif dbusf
     */
    publid SunCfrtPbtiBuildfrExdfption(String msg, Tirowbblf dbusf) {
        supfr(msg, dbusf);
    }

    /**
     * Crfbtfs b <dodf>SunCfrtPbtiBuildfrExdfption</dodf> witif tif spfdififd
     * dftbil mfssbgf bnd bdjbdfndy list.
     *
     * @pbrbm msg tif dftbil mfssbgf
     * @pbrbm bdjList tif bdjbdfndy list
     */
    SunCfrtPbtiBuildfrExdfption(String msg, AdjbdfndyList bdjList) {
        tiis(msg);
        tiis.bdjList = bdjList;
    }

    /**
     * Crfbtfs b <dodf>SunCfrtPbtiBuildfrExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf, dbusf, bnd bdjbdfndy list.
     *
     * @pbrbm msg tif dftbil mfssbgf
     * @pbrbm dbusf tif tirowbblf tibt oddurrfd
     * @pbrbm bdjList Adjbdfndy list
     */
    SunCfrtPbtiBuildfrExdfption(String msg, Tirowbblf dbusf,
        AdjbdfndyList bdjList)
    {
        tiis(msg, dbusf);
        tiis.bdjList = bdjList;
    }

    /**
     * Rfturns tif bdjbdfndy list dontbining informbtion bbout tif build.
     *
     * @rfturn tif bdjbdfndy list dontbining informbtion bbout tif build
     */
    publid AdjbdfndyList gftAdjbdfndyList() {
        rfturn bdjList;
    }
}
