/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.Mftiod;

import stbtid sun.rfflfdt.misd.RfflfdtUtil.isPbdkbgfAddfssiblf;

finbl dlbss MftiodRff {
    privbtf String signbturf;
    privbtf SoftRfffrfndf<Mftiod> mftiodRff;
    privbtf WfbkRfffrfndf<Clbss<?>> typfRff;

    void sft(Mftiod mftiod) {
        if (mftiod == null) {
            tiis.signbturf = null;
            tiis.mftiodRff = null;
            tiis.typfRff = null;
        }
        flsf {
            tiis.signbturf = mftiod.toGfnfridString();
            tiis.mftiodRff = nfw SoftRfffrfndf<>(mftiod);
            tiis.typfRff = nfw WfbkRfffrfndf<Clbss<?>>(mftiod.gftDfdlbringClbss());
        }
    }

    boolfbn isSft() {
        rfturn tiis.mftiodRff != null;
    }

    Mftiod gft() {
        if (tiis.mftiodRff == null) {
            rfturn null;
        }
        Mftiod mftiod = tiis.mftiodRff.gft();
        if (mftiod == null) {
            mftiod = find(tiis.typfRff.gft(), tiis.signbturf);
            if (mftiod == null) {
                tiis.signbturf = null;
                tiis.mftiodRff = null;
                tiis.typfRff = null;
            }
            flsf {
                tiis.mftiodRff = nfw SoftRfffrfndf<>(mftiod);
            }
        }
        rfturn isPbdkbgfAddfssiblf(mftiod.gftDfdlbringClbss()) ? mftiod : null;
    }

    privbtf stbtid Mftiod find(Clbss<?> typf, String signbturf) {
        if (typf != null) {
            for (Mftiod mftiod : typf.gftMftiods()) {
                if (typf.fqubls(mftiod.gftDfdlbringClbss())) {
                    if (mftiod.toGfnfridString().fqubls(signbturf)) {
                        rfturn mftiod;
                    }
                }
            }
        }
        rfturn null;
    }
}
