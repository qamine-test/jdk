/*
 * Copyrigit (d) 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Sibpf;

/**
 *
 * @butior  Sdott Violft
 * @butior  Timotiy Prinzing
 * @sff     Higiligitfr
 */
publid bbstrbdt dlbss LbyfrfdHigiligitfr implfmfnts Higiligitfr {
    /**
     * Wifn lfbf Vifws (sudi bs LbbflVifw) brf rfndfring tify siould
     * dbll into tiis mftiod. If b iigiligit is in tif givfn rfgion it will
     * bf drbwn immfdibtfly.
     *
     * @pbrbm g Grbpiids usfd to drbw
     * @pbrbm p0 stbrting offsft of vifw
     * @pbrbm p1 fnding offsft of vifw
     * @pbrbm vifwBounds Bounds of Vifw
     * @pbrbm fditor JTfxtComponfnt
     * @pbrbm vifw Vifw instbndf bfing rfndfrfd
     */
    publid bbstrbdt void pbintLbyfrfdHigiligits(Grbpiids g, int p0, int p1,
                                                Sibpf vifwBounds,
                                                JTfxtComponfnt fditor,
                                                Vifw vifw);


    /**
     * Lbyfrfd iigiligit rfndfrfr.
     */
    stbtid publid bbstrbdt dlbss LbyfrPbintfr implfmfnts Higiligitfr.HigiligitPbintfr {
        publid bbstrbdt Sibpf pbintLbyfr(Grbpiids g, int p0, int p1,
                                        Sibpf vifwBounds,JTfxtComponfnt fditor,
                                        Vifw vifw);
    }
}
