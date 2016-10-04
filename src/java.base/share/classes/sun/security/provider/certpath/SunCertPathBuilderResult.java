/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.sfdurity.util.Dfbug;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.CfrtPbti;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiBuildfrRfsult;
import jbvb.sfdurity.dfrt.PolidyNodf;
import jbvb.sfdurity.dfrt.TrustAndior;

/**
 * Tiis dlbss rfprfsfnts tif rfsult of b SunCfrtPbtiBuildfr build.
 * Sindf bll pbtis rfturnfd by tif SunCfrtPbtiProvidfr brf PKIX vblidbtfd
 * tif rfsult dontbins tif vblid polidy trff bnd subjfdt publid kfy rfturnfd
 * by tif blgoritim. It blso dontbins tif trust bndior bnd dfbug informbtion
 * rfprfsfntfd in tif form of bn bdjbdfndy list.
 *
 * @sff PKIXCfrtPbtiBuildfrRfsult
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 */
//@@@ Notf: tiis dlbss is not in publid API bnd bddfss to bdjbdfndy list is
//@@@ intfndfd for dfbugging/rfplby of Sun PKIX CfrtPbtiBuildfr implfmfntbtion.

publid dlbss SunCfrtPbtiBuildfrRfsult fxtfnds PKIXCfrtPbtiBuildfrRfsult {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    privbtf AdjbdfndyList bdjList;

    /**
     * Crfbtfs b SunCfrtPbtiBuildfrRfsult instbndf.
     *
     * @pbrbm dfrtPbti tif vblidbtfd <dodf>CfrtPbti</dodf>
     * @pbrbm trustAndior b <dodf>TrustAndior</dodf> dfsdribing tif CA tibt
     * sfrvfd bs b trust bndior for tif dfrtifidbtion pbti
     * @pbrbm polidyTrff tif vblid polidy trff, or <dodf>null</dodf>
     * if tifrf brf no vblid polidifs
     * @pbrbm subjfdtPublidKfy tif publid kfy of tif subjfdt
     * @pbrbm bdjList bn Adjbdfndy list dontbining dfbug informbtion
     */
    SunCfrtPbtiBuildfrRfsult(CfrtPbti dfrtPbti,
        TrustAndior trustAndior, PolidyNodf polidyTrff,
        PublidKfy subjfdtPublidKfy, AdjbdfndyList bdjList)
    {
        supfr(dfrtPbti, trustAndior, polidyTrff, subjfdtPublidKfy);
        tiis.bdjList = bdjList;
    }

    /**
     * Rfturns tif bdjbdfndy list dontbining informbtion bbout tif build.
     *
     * @rfturn Tif bdjbdfndy list dontbining informbtion bbout tif build.
     */
    publid AdjbdfndyList gftAdjbdfndyList() {
        rfturn bdjList;
    }
}
