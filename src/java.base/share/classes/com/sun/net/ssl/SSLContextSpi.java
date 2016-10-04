/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.SSLContfxtSpi
 */

pbdkbgf dom.sun.nft.ssl;

import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvbx.nft.ssl.*;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif <dodf>SSLContfxt</dodf> dlbss.
 *
 * <p> All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b pbrtidulbr SSL dontfxt.
 *
 * @dfprfdbtfd As of JDK 1.4, tiis implfmfntbtion-spfdifid dlbss wbs
 *      rfplbdfd by {@link jbvbx.nft.ssl.SSLContfxtSpi}.
 */
@Dfprfdbtfd
publid bbstrbdt dlbss SSLContfxtSpi {
    /**
     * Initiblizfs tiis dontfxt.
     *
     * @pbrbm km tif sourdfs of butifntidbtion kfys
     * @pbrbm tm tif sourdfs of pffr butifntidbtion trust dfdisions
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor
     */
    protfdtfd bbstrbdt void fnginfInit(KfyMbnbgfr[] bi, TrustMbnbgfr[] ti,
        SfdurfRbndom sr) tirows KfyMbnbgfmfntExdfption;

    /**
     * Rfturns b <dodf>SodkftFbdtory</dodf> objfdt for tiis
     * dontfxt.
     *
     * @rfturn tif fbdtory
     */
    protfdtfd bbstrbdt SSLSodkftFbdtory fnginfGftSodkftFbdtory();

    /**
     * Rfturns b <dodf>SfrvfrSodkftFbdtory</dodf> objfdt for
     * tiis dontfxt.
     *
     * @rfturn tif fbdtory
     */
    protfdtfd bbstrbdt SSLSfrvfrSodkftFbdtory fnginfGftSfrvfrSodkftFbdtory();
}
