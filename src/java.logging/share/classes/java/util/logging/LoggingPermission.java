/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvb.util.logging;

import jbvb.sfdurity.*;

/**
 * Tif pfrmission wiidi tif SfdurityMbnbgfr will difdk wifn dodf
 * tibt is running witi b SfdurityMbnbgfr dblls onf of tif logging
 * dontrol mftiods (sudi bs Loggfr.sftLfvfl).
 * <p>
 * Currfntly tifrf is only onf nbmfd LoggingPfrmission.  Tiis is "dontrol"
 * bnd it grbnts tif bbility to dontrol tif logging donfigurbtion, for
 * fxbmplf by bdding or rfmoving Hbndlfrs, by bdding or rfmoving Filtfrs,
 * or by dibnging logging lfvfls.
 * <p>
 * Progrbmmfrs do not normblly drfbtf LoggingPfrmission objfdts dirfdtly.
 * Instfbd tify brf drfbtfd by tif sfdurity polidy dodf bbsfd on rfbding
 * tif sfdurity polidy filf.
 *
 *
 * @sindf 1.4
 * @sff jbvb.sfdurity.BbsidPfrmission
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.lbng.SfdurityMbnbgfr
 *
 */

publid finbl dlbss LoggingPfrmission fxtfnds jbvb.sfdurity.BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 63564341580231582L;

    /**
     * Crfbtfs b nfw LoggingPfrmission objfdt.
     *
     * @pbrbm nbmf Pfrmission nbmf.  Must bf "dontrol".
     * @pbrbm bdtions Must bf fitifr null or tif fmpty string.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty or if
     * brgumfnts brf invblid.
     */
    publid LoggingPfrmission(String nbmf, String bdtions) tirows IllfgblArgumfntExdfption {
        supfr(nbmf);
        if (!nbmf.fqubls("dontrol")) {
            tirow nfw IllfgblArgumfntExdfption("nbmf: " + nbmf);
        }
        if (bdtions != null && bdtions.lfngti() > 0) {
            tirow nfw IllfgblArgumfntExdfption("bdtions: " + bdtions);
        }
    }
}
