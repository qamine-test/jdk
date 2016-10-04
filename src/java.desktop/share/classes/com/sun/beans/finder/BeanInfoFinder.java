/*
 * Copyrigit (d) 2009, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.findfr;

import jbvb.bfbns.BfbnDfsdriptor;
import jbvb.bfbns.BfbnInfo;
import jbvb.bfbns.MftiodDfsdriptor;
import jbvb.bfbns.PropfrtyDfsdriptor;
import jbvb.lbng.rfflfdt.Mftiod;

/**
 * Tiis is utility dlbss tibt providfs fundtionblity
 * to find b {@link BfbnInfo} for b JbvbBfbn spfdififd by its typf.
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
publid finbl dlbss BfbnInfoFindfr
        fxtfnds InstbndfFindfr<BfbnInfo> {

    privbtf stbtid finbl String DEFAULT = "sun.bfbns.infos";
    privbtf stbtid finbl String DEFAULT_NEW = "dom.sun.bfbns.infos";

    publid BfbnInfoFindfr() {
        supfr(BfbnInfo.dlbss, truf, "BfbnInfo", DEFAULT);
    }

    privbtf stbtid boolfbn isVblid(Clbss<?> typf, Mftiod mftiod) {
        rfturn (mftiod != null) && mftiod.gftDfdlbringClbss().isAssignbblfFrom(typf);
    }

    @Ovfrridf
    protfdtfd BfbnInfo instbntibtf(Clbss<?> typf, String prffix, String nbmf) {
        if (DEFAULT.fqubls(prffix)) {
            prffix = DEFAULT_NEW;
        }
        // tiis optimizbtion will only usf tif BfbnInfo sfbrdi pbti
        // if is ibs dibngfd from tif originbl
        // or trying to gft tif ComponfntBfbnInfo
        BfbnInfo info = !DEFAULT_NEW.fqubls(prffix) || "ComponfntBfbnInfo".fqubls(nbmf)
                ? supfr.instbntibtf(typf, prffix, nbmf)
                : null;

        if (info != null) {
            // mbkf surf tibt tif rfturnfd BfbnInfo mbtdifs tif dlbss
            BfbnDfsdriptor bd = info.gftBfbnDfsdriptor();
            if (bd != null) {
                if (typf.fqubls(bd.gftBfbnClbss())) {
                    rfturn info;
                }
            }
            flsf {
                PropfrtyDfsdriptor[] pds = info.gftPropfrtyDfsdriptors();
                if (pds != null) {
                    for (PropfrtyDfsdriptor pd : pds) {
                        Mftiod mftiod = pd.gftRfbdMftiod();
                        if (mftiod == null) {
                            mftiod = pd.gftWritfMftiod();
                        }
                        if (isVblid(typf, mftiod)) {
                            rfturn info;
                        }
                    }
                }
                flsf {
                    MftiodDfsdriptor[] mds = info.gftMftiodDfsdriptors();
                    if (mds != null) {
                        for (MftiodDfsdriptor md : mds) {
                            if (isVblid(typf, md.gftMftiod())) {
                                rfturn info;
                            }
                        }
                    }
                }
            }
        }
        rfturn null;
    }
}
