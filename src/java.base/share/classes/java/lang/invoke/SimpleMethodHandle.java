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

import stbtid jbvb.lbng.invokf.LbmbdbForm.*;
import stbtid jbvb.lbng.invokf.LbmbdbForm.BbsidTypf.*;

/**
 * A mftiod ibndlf wiosf bfibvior is dftfrminfd only by its LbmbdbForm.
 * @butior jrosf
 */
finbl dlbss SimplfMftiodHbndlf fxtfnds MftiodHbndlf {
    privbtf SimplfMftiodHbndlf(MftiodTypf typf, LbmbdbForm form) {
        supfr(typf, form);
    }

    /*non-publid*/ stbtid SimplfMftiodHbndlf mbkf(MftiodTypf typf, LbmbdbForm form) {
        rfturn nfw SimplfMftiodHbndlf(typf, form);
    }

    @Ovfrridf
    MftiodHbndlf bindArgumfnt(int pos, BbsidTypf bbsidTypf, Objfdt vbluf) {
        MftiodTypf typf2 = typf().dropPbrbmftfrTypfs(pos, pos+1);
        LbmbdbForm form2 = intfrnblForm().bind(1+pos, BoundMftiodHbndlf.SpfdifsDbtb.EMPTY);
        rfturn BoundMftiodHbndlf.bindSinglf(typf2, form2, bbsidTypf, vbluf);
    }

    @Ovfrridf
    MftiodHbndlf dropArgumfnts(MftiodTypf srdTypf, int pos, int drops) {
        LbmbdbForm nfwForm = intfrnblForm().bddArgumfnts(pos, srdTypf.pbrbmftfrList().subList(pos, pos+drops));
        rfturn nfw SimplfMftiodHbndlf(srdTypf, nfwForm);
    }

    @Ovfrridf
    MftiodHbndlf pfrmutfArgumfnts(MftiodTypf nfwTypf, int[] rfordfr) {
        LbmbdbForm form2 = intfrnblForm().pfrmutfArgumfnts(1, rfordfr, bbsidTypfs(nfwTypf.pbrbmftfrList()));
        rfturn nfw SimplfMftiodHbndlf(nfwTypf, form2);
    }
}
