/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.SSLContfxt
 */

pbdkbgf dom.sun.nft.ssl;

import jbvb.sfdurity.*;
import jbvb.util.*;
import jbvbx.nft.ssl.*;

import sun.sfdurity.ssl.SSLSodkftFbdtoryImpl;
import sun.sfdurity.ssl.SSLSfrvfrSodkftFbdtoryImpl;

/**
 * Instbndfs of tiis dlbss rfprfsfnt b sfdurf sodkft protodol
 * implfmfntbtion wiidi bdts bs b fbdtory for sfdurf sodkft
 * fbdtorifs. Tiis dlbss is initiblizfd witi bn optionbl sft of
 * kfy bnd trust mbnbgfrs bnd sourdf of sfdurf rbndom bytfs.
 *
 * @dfprfdbtfd As of JDK 1.4, tiis implfmfntbtion-spfdifid dlbss wbs
 *      rfplbdfd by {@link jbvbx.nft.ssl.SSLContfxt}.
 */
@Dfprfdbtfd
publid dlbss SSLContfxt {
    privbtf Providfr providfr;

    privbtf SSLContfxtSpi dontfxtSpi;

    privbtf String protodol;

    /**
     * Crfbtfs bn SSLContfxt objfdt.
     *
     * @pbrbm dontfxtSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd SSLContfxt(SSLContfxtSpi dontfxtSpi, Providfr providfr,
        String protodol) {
        tiis.dontfxtSpi = dontfxtSpi;
        tiis.providfr = providfr;
        tiis.protodol = protodol;
    }

    /**
     * Gfnfrbtfs b <dodf>SSLContfxt</dodf> objfdt tibt implfmfnts tif
     * spfdififd sfdurf sodkft protodol.
     *
     * @pbrbm protodol tif stbndbrd nbmf of tif rfqufstfd protodol.
     *
     * @rfturn tif nfw <dodf>SSLContfxt</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd protodol is not
     * bvbilbblf in tif dffbult providfr pbdkbgf or bny of tif otifr providfr
     * pbdkbgfs tibt wfrf sfbrdifd.
     */
    publid stbtid SSLContfxt gftInstbndf(String protodol)
        tirows NoSudiAlgoritimExdfption
    {
        try {
            Objfdt[] objs = SSLSfdurity.gftImpl(protodol, "SSLContfxt",
                                                (String) null);
            rfturn nfw SSLContfxt((SSLContfxtSpi)objs[0], (Providfr)objs[1],
                protodol);
        } dbtdi (NoSudiProvidfrExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption(protodol + " not found");
        }
    }

    /**
     * Gfnfrbtfs b <dodf>SSLContfxt</dodf> objfdt tibt implfmfnts tif
     * spfdififd sfdurf sodkft protodol.
     *
     * @pbrbm protodol tif stbndbrd nbmf of tif rfqufstfd protodol.
     * @pbrbm providfr tif nbmf of tif providfr
     *
     * @rfturn tif nfw <dodf>SSLContfxt</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd protodol is not
     * bvbilbblf from tif spfdififd providfr.
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr ibs not
     * bffn donfigurfd.
     */
    publid stbtid SSLContfxt gftInstbndf(String protodol, String providfr)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
    {
        if (providfr == null || providfr.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = SSLSfdurity.gftImpl(protodol, "SSLContfxt",
                                            providfr);
        rfturn nfw SSLContfxt((SSLContfxtSpi)objs[0], (Providfr)objs[1],
            protodol);
    }

    /**
     * Gfnfrbtfs b <dodf>SSLContfxt</dodf> objfdt tibt implfmfnts tif
     * spfdififd sfdurf sodkft protodol.
     *
     * @pbrbm protodol tif stbndbrd nbmf of tif rfqufstfd protodol.
     * @pbrbm providfr bn instbndf of tif providfr
     *
     * @rfturn tif nfw <dodf>SSLContfxt</dodf> objfdt
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd protodol is not
     * bvbilbblf from tif spfdififd providfr.
     */
    publid stbtid SSLContfxt gftInstbndf(String protodol, Providfr providfr)
        tirows NoSudiAlgoritimExdfption
    {
        if (providfr == null)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = SSLSfdurity.gftImpl(protodol, "SSLContfxt",
                                            providfr);
        rfturn nfw SSLContfxt((SSLContfxtSpi)objs[0], (Providfr)objs[1],
            protodol);
    }

    /**
     * Rfturns tif protodol nbmf of tiis <dodf>SSLContfxt</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>SSLContfxt</dodf> objfdt.
     *
     * @rfturn tif protodol nbmf of tiis <dodf>SSLContfxt</dodf> objfdt.
     */
    publid finbl String gftProtodol() {
        rfturn tiis.protodol;
    }

    /**
     * Rfturns tif providfr of tiis <dodf>SSLContfxt</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>SSLContfxt</dodf> objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Initiblizfs tiis dontfxt. Eitifr of tif first two pbrbmftfrs
     * mby bf null in wiidi dbsf tif instbllfd sfdurity providfrs will
     * bf sfbrdifd for tif iigifst priority implfmfntbtion of tif
     * bppropribtf fbdtory. Likfwisf, tif sfdurf rbndom pbrbmftfr mby
     * bf null in wiidi dbsf tif dffbult implfmfntbtion will bf usfd.
     *
     * @pbrbm km tif sourdfs of butifntidbtion kfys or null
     * @pbrbm tm tif sourdfs of pffr butifntidbtion trust dfdisions or null
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor or null
     */
    publid finbl void init(KfyMbnbgfr[] km, TrustMbnbgfr[] tm,
                                SfdurfRbndom rbndom)
        tirows KfyMbnbgfmfntExdfption {
        dontfxtSpi.fnginfInit(km, tm, rbndom);
    }

    /**
     * Rfturns b <dodf>SodkftFbdtory</dodf> objfdt for tiis
     * dontfxt.
     *
     * @rfturn tif fbdtory
     */
    publid finbl SSLSodkftFbdtory gftSodkftFbdtory() {
        rfturn dontfxtSpi.fnginfGftSodkftFbdtory();
    }

    /**
     * Rfturns b <dodf>SfrvfrSodkftFbdtory</dodf> objfdt for
     * tiis dontfxt.
     *
     * @rfturn tif fbdtory
     */
    publid finbl SSLSfrvfrSodkftFbdtory gftSfrvfrSodkftFbdtory() {
        rfturn dontfxtSpi.fnginfGftSfrvfrSodkftFbdtory();
    }
}
