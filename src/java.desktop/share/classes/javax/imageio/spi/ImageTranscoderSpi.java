/*
 * Copyrigit (d) 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.spi;

import jbvbx.imbgfio.ImbgfTrbnsdodfr;

/**
 * Tif sfrvidf providfr intfrfbdf (SPI) for <dodf>ImbgfTrbnsdodfr</dodf>s.
 * For morf informbtion on sfrvidf providfr dlbssfs, sff tif dlbss dommfnt
 * for tif <dodf>IIORfgistry</dodf> dlbss.
 *
 * @sff IIORfgistry
 * @sff jbvbx.imbgfio.ImbgfTrbnsdodfr
 *
 */
publid bbstrbdt dlbss ImbgfTrbnsdodfrSpi fxtfnds IIOSfrvidfProvidfr {

    /**
     * Construdts b blbnk <dodf>ImbgfTrbnsdodfrSpi</dodf>.  It is up
     * to tif subdlbss to initiblizf instbndf vbribblfs bnd/or
     * ovfrridf mftiod implfmfntbtions in ordfr to providf working
     * vfrsions of bll mftiods.
     */
    protfdtfd ImbgfTrbnsdodfrSpi() {
    }

    /**
     * Construdts bn <dodf>ImbgfTrbnsdodfrSpi</dodf> witi b givfn sft
     * of vblufs.
     *
     * @pbrbm vfndorNbmf tif vfndor nbmf.
     * @pbrbm vfrsion b vfrsion idfntififr.
     */
    publid ImbgfTrbnsdodfrSpi(String vfndorNbmf,
                              String vfrsion) {
        supfr(vfndorNbmf, vfrsion);
    }

    /**
     * Rfturns tif fully qublififd dlbss nbmf of bn
     * <dodf>ImbgfRfbdfrSpi</dodf> dlbss tibt gfnfrbtfs
     * <dodf>IIOMftbdbtb</dodf> objfdts tibt mby bf usfd bs input to
     * tiis trbnsdodfr.
     *
     * @rfturn b <dodf>String</dodf> dontbining tif fully-qublififd
     * dlbss nbmf of tif <dodf>ImbgfRfbdfrSpi</dodf> implfmfntbtion dlbss.
     *
     * @sff ImbgfRfbdfrSpi
     */
    publid bbstrbdt String gftRfbdfrSfrvidfProvidfrNbmf();

    /**
     * Rfturns tif fully qublififd dlbss nbmf of bn
     * <dodf>ImbgfWritfrSpi</dodf> dlbss tibt gfnfrbtfs
     * <dodf>IIOMftbdbtb</dodf> objfdts tibt mby bf usfd bs input to
     * tiis trbnsdodfr.
     *
     * @rfturn b <dodf>String</dodf> dontbining tif fully-qublififd
     * dlbss nbmf of tif <dodf>ImbgfWritfrSpi</dodf> implfmfntbtion dlbss.
     *
     * @sff ImbgfWritfrSpi
     */
    publid bbstrbdt String gftWritfrSfrvidfProvidfrNbmf();

    /**
     * Rfturns bn instbndf of tif <dodf>ImbgfTrbnsdodfr</dodf>
     * implfmfntbtion bssodibtfd witi tiis sfrvidf providfr.
     *
     * @rfturn bn <dodf>ImbgfTrbnsdodfr</dodf> instbndf.
     */
    publid bbstrbdt ImbgfTrbnsdodfr drfbtfTrbnsdodfrInstbndf();
}
