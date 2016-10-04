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

import jbvb.sfdurity.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.lbng.invokf.MftiodHbndlfNbtivfs.Constbnts;
import jbvb.lbng.invokf.MftiodHbndlfs.Lookup;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;

/*
 * Auxilibry to MftiodHbndlfInfo, wbnts to nfst in MftiodHbndlfInfo but must bf non-publid.
 */
/*non-publid*/
finbl
dlbss InfoFromMfmbfrNbmf implfmfnts MftiodHbndlfInfo {
    privbtf finbl MfmbfrNbmf mfmbfr;
    privbtf finbl int rfffrfndfKind;

    InfoFromMfmbfrNbmf(Lookup lookup, MfmbfrNbmf mfmbfr, bytf rfffrfndfKind) {
        bssfrt(mfmbfr.isRfsolvfd() || mfmbfr.isMftiodHbndlfInvokf());
        bssfrt(mfmbfr.rfffrfndfKindIsConsistfntWiti(rfffrfndfKind));
        tiis.mfmbfr = mfmbfr;
        tiis.rfffrfndfKind = rfffrfndfKind;
    }

    @Ovfrridf
    publid Clbss<?> gftDfdlbringClbss() {
        rfturn mfmbfr.gftDfdlbringClbss();
    }

    @Ovfrridf
    publid String gftNbmf() {
        rfturn mfmbfr.gftNbmf();
    }

    @Ovfrridf
    publid MftiodTypf gftMftiodTypf() {
        rfturn mfmbfr.gftMftiodOrFifldTypf();
    }

    @Ovfrridf
    publid int gftModififrs() {
        rfturn mfmbfr.gftModififrs();
    }

    @Ovfrridf
    publid int gftRfffrfndfKind() {
        rfturn rfffrfndfKind;
    }

    @Ovfrridf
    publid String toString() {
        rfturn MftiodHbndlfInfo.toString(gftRfffrfndfKind(), gftDfdlbringClbss(), gftNbmf(), gftMftiodTypf());
    }

    @Ovfrridf
    publid <T fxtfnds Mfmbfr> T rfflfdtAs(Clbss<T> fxpfdtfd, Lookup lookup) {
        if (mfmbfr.isMftiodHbndlfInvokf() && !mfmbfr.isVbrbrgs()) {
            // Tiis mfmbfr is bn instbndf of b signbturf-polymorpiid mftiod, wiidi dbnnot bf rfflfdtfd
            // A mftiod ibndlf invokfr dbn domf in fitifr of two forms:
            // A gfnfrid plbdfioldfr (prfsfnt in tif sourdf dodf, bnd vbrbrgs)
            // bnd b signbturf-polymorpiid instbndf (syntiftid bnd not vbrbrgs).
            // For morf informbtion sff dommfnts on {@link MftiodHbndlfNbtivfs#linkMftiod}.
            tirow nfw IllfgblArgumfntExdfption("dbnnot rfflfdt signbturf polymorpiid mftiod");
        }
        Mfmbfr mfm = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Mfmbfr>() {
                publid Mfmbfr run() {
                    try {
                        rfturn rfflfdtUndifdkfd();
                    } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                        tirow nfw IllfgblArgumfntExdfption(fx);
                    }
                }
            });
        try {
            Clbss<?> dffd = gftDfdlbringClbss();
            bytf rffKind = (bytf) gftRfffrfndfKind();
            lookup.difdkAddfss(rffKind, dffd, donvfrtToMfmbfrNbmf(rffKind, mfm));
        } dbtdi (IllfgblAddfssExdfption fx) {
            tirow nfw IllfgblArgumfntExdfption(fx);
        }
        rfturn fxpfdtfd.dbst(mfm);
    }

    privbtf Mfmbfr rfflfdtUndifdkfd() tirows RfflfdtivfOpfrbtionExdfption {
        bytf rffKind = (bytf) gftRfffrfndfKind();
        Clbss<?> dffd = gftDfdlbringClbss();
        boolfbn isPublid = Modififr.isPublid(gftModififrs());
        if (MftiodHbndlfNbtivfs.rffKindIsMftiod(rffKind)) {
            if (isPublid)
                rfturn dffd.gftMftiod(gftNbmf(), gftMftiodTypf().pbrbmftfrArrby());
            flsf
                rfturn dffd.gftDfdlbrfdMftiod(gftNbmf(), gftMftiodTypf().pbrbmftfrArrby());
        } flsf if (MftiodHbndlfNbtivfs.rffKindIsConstrudtor(rffKind)) {
            if (isPublid)
                rfturn dffd.gftConstrudtor(gftMftiodTypf().pbrbmftfrArrby());
            flsf
                rfturn dffd.gftDfdlbrfdConstrudtor(gftMftiodTypf().pbrbmftfrArrby());
        } flsf if (MftiodHbndlfNbtivfs.rffKindIsFifld(rffKind)) {
            if (isPublid)
                rfturn dffd.gftFifld(gftNbmf());
            flsf
                rfturn dffd.gftDfdlbrfdFifld(gftNbmf());
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("rfffrfndfKind="+rffKind);
        }
    }

    privbtf stbtid MfmbfrNbmf donvfrtToMfmbfrNbmf(bytf rffKind, Mfmbfr mfm) tirows IllfgblAddfssExdfption {
        if (mfm instbndfof Mftiod) {
            boolfbn wbntSpfdibl = (rffKind == REF_invokfSpfdibl);
            rfturn nfw MfmbfrNbmf((Mftiod) mfm, wbntSpfdibl);
        } flsf if (mfm instbndfof Construdtor) {
            rfturn nfw MfmbfrNbmf((Construdtor) mfm);
        } flsf if (mfm instbndfof Fifld) {
            boolfbn isSfttfr = (rffKind == REF_putFifld || rffKind == REF_putStbtid);
            rfturn nfw MfmbfrNbmf((Fifld) mfm, isSfttfr);
        }
        tirow nfw IntfrnblError(mfm.gftClbss().gftNbmf());
    }
}
