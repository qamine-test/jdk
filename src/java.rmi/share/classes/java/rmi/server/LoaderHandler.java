/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;

/**
 * <dodf>LobdfrHbndlfr</dodf> is bn intfrfbdf usfd intfrnblly by tif RMI
 * runtimf in prfvious implfmfntbtion vfrsions.  It siould nfvfr bf bddfssfd
 * by bpplidbtion dodf.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.1
 *
 * @dfprfdbtfd no rfplbdfmfnt
 */
@Dfprfdbtfd
publid intfrfbdf LobdfrHbndlfr {

    /** pbdkbgf of systfm <dodf>LobdfrHbndlfr</dodf> implfmfntbtion. */
    finbl stbtid String pbdkbgfPrffix = "sun.rmi.sfrvfr";

    /**
     * Lobds b dlbss from tif lodbtion spfdififd by tif
     * <dodf>jbvb.rmi.sfrvfr.dodfbbsf</dodf> propfrty.
     *
     * @pbrbm  nbmf tif nbmf of tif dlbss to lobd
     * @rfturn tif <dodf>Clbss</dodf> objfdt rfprfsfnting tif lobdfd dlbss
     * @fxdfption MblformfdURLExdfption
     *            if tif systfm propfrty <b>jbvb.rmi.sfrvfr.dodfbbsf</b>
     *            dontbins bn invblid URL
     * @fxdfption ClbssNotFoundExdfption
     *            if b dffinition for tif dlbss dould not
     *            bf found bt tif dodfbbsf lodbtion.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    Clbss<?> lobdClbss(String nbmf)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption;

    /**
     * Lobds b dlbss from b URL.
     *
     * @pbrbm dodfbbsf  tif URL from wiidi to lobd tif dlbss
     * @pbrbm nbmf      tif nbmf of tif dlbss to lobd
     * @rfturn tif <dodf>Clbss</dodf> objfdt rfprfsfnting tif lobdfd dlbss
     * @fxdfption MblformfdURLExdfption
     *            if tif <dodf>dodfbbsf</dodf> pbrbmbtfr
     *            dontbins bn invblid URL
     * @fxdfption ClbssNotFoundExdfption
     *            if b dffinition for tif dlbss dould not
     *            bf found bt tif spfdififd URL
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    Clbss<?> lobdClbss(URL dodfbbsf, String nbmf)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption;

    /**
     * Rfturns tif sfdurity dontfxt of tif givfn dlbss lobdfr.
     *
     * @pbrbm lobdfr  b dlbss lobdfr from wiidi to gft tif sfdurity dontfxt
     * @rfturn tif sfdurity dontfxt
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    Objfdt gftSfdurityContfxt(ClbssLobdfr lobdfr);
}
