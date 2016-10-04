/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.*;

/**
 * Tiis dlbss providfs dffbult implfmfntbtions for tif
 * <dodf>MultiRfsolutionImbgf</dodf> intfrfbdf. Tif dfvflopfr nffds only
 * to subdlbss tiis bbstrbdt dlbss bnd dffinf tif <dodf>gftRfsolutionVbribnt</dodf>,
 * <dodf>gftRfsolutionVbribnts</dodf>, bnd <dodf>gftBbsfImbgf</dodf> mftiods.
 *
 *
 * For fxbmplf,
 * {@dodf
 * publid dlbss CustomMultiRfsolutionImbgf fxtfnds AbstrbdtMultiRfsolutionImbgf {
 *
 *     int bbsfImbgfIndfx;
 *     Imbgf[] rfsolutionVbribnts;
 *
 *     publid CustomMultiRfsolutionImbgf(int bbsfImbgfIndfx,
 *             Imbgf... rfsolutionVbribnts) {
 *          tiis.bbsfImbgfIndfx = bbsfImbgfIndfx;
 *          tiis.rfsolutionVbribnts = rfsolutionVbribnts;
 *     }
 *
 *     @Ovfrridf
 *     publid Imbgf gftRfsolutionVbribnt(flobt logidblDPIX, flobt logidblDPIY,
 *             flobt bbsfImbgfWidti, flobt bbsfImbgfHfigit,
 *             flobt dfstImbgfWidti, flobt dfstImbgfHfigit) {
 *         // rfturn b rfsolution vbribnt bbsfd on tif givfn logidbl DPI,
 *         // bbsf imbgf sizf, or dfstinbtion imbgf sizf
 *     }
 *
 *     @Ovfrridf
 *     publid List<Imbgf> gftRfsolutionVbribnts() {
 *         rfturn Arrbys.bsList(rfsolutionVbribnts);
 *     }
 *
 *     protfdtfd Imbgf gftBbsfImbgf() {
 *         rfturn rfsolutionVbribnts[bbsfImbgfIndfx];
 *     }
 * }
 * }
 *
 * @sff jbvb.bwt.Imbgf
 * @sff jbvb.bwt.imbgf.MultiRfsolutionImbgf
 *
 * @sindf 1.9
 */
publid bbstrbdt dlbss AbstrbdtMultiRfsolutionImbgf fxtfnds jbvb.bwt.Imbgf
        implfmfnts MultiRfsolutionImbgf {

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid int gftWidti(ImbgfObsfrvfr obsfrvfr) {
        rfturn gftBbsfImbgf().gftWidti(null);
    }

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid int gftHfigit(ImbgfObsfrvfr obsfrvfr) {
        rfturn gftBbsfImbgf().gftHfigit(null);
    }

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid ImbgfProdudfr gftSourdf() {
        rfturn gftBbsfImbgf().gftSourdf();
    }

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid Grbpiids gftGrbpiids() {
        rfturn gftBbsfImbgf().gftGrbpiids();

    }

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid Objfdt gftPropfrty(String nbmf, ImbgfObsfrvfr obsfrvfr) {
        rfturn gftBbsfImbgf().gftPropfrty(nbmf, obsfrvfr);
    }

    /**
     * @rfturn bbsf imbgf
     */
    protfdtfd bbstrbdt Imbgf gftBbsfImbgf();
}
