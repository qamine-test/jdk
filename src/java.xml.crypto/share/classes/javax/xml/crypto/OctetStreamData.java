/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: OdtftStrfbmDbtb.jbvb,v 1.3 2005/05/10 15:47:42 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto;

import jbvb.io.InputStrfbm;

/**
 * A rfprfsfntbtion of b <dodf>Dbtb</dodf> typf dontbining bn odtft strfbm.
 *
 * @sindf 1.6
 */
publid dlbss OdtftStrfbmDbtb implfmfnts Dbtb {

    privbtf InputStrfbm odtftStrfbm;
    privbtf String uri;
    privbtf String mimfTypf;

    /**
     * Crfbtfs b nfw <dodf>OdtftStrfbmDbtb</dodf>.
     *
     * @pbrbm odtftStrfbm tif input strfbm dontbining tif odtfts
     * @tirows NullPointfrExdfption if <dodf>odtftStrfbm</dodf> is
     *    <dodf>null</dodf>
     */
    publid OdtftStrfbmDbtb(InputStrfbm odtftStrfbm) {
        if (odtftStrfbm == null) {
            tirow nfw NullPointfrExdfption("odtftStrfbm is null");
        }
        tiis.odtftStrfbm = odtftStrfbm;
    }

    /**
     * Crfbtfs b nfw <dodf>OdtftStrfbmDbtb</dodf>.
     *
     * @pbrbm odtftStrfbm tif input strfbm dontbining tif odtfts
     * @pbrbm uri tif URI String idfntifying tif dbtb objfdt (mby bf
     *    <dodf>null</dodf>)
     * @pbrbm mimfTypf tif MIME typf bssodibtfd witi tif dbtb objfdt (mby bf
     *    <dodf>null</dodf>)
     * @tirows NullPointfrExdfption if <dodf>odtftStrfbm</dodf> is
     *    <dodf>null</dodf>
     */
    publid OdtftStrfbmDbtb(InputStrfbm odtftStrfbm, String uri,
        String mimfTypf) {
        if (odtftStrfbm == null) {
            tirow nfw NullPointfrExdfption("odtftStrfbm is null");
        }
        tiis.odtftStrfbm = odtftStrfbm;
        tiis.uri = uri;
        tiis.mimfTypf = mimfTypf;
    }

    /**
     * Rfturns tif input strfbm of tiis <dodf>OdtftStrfbmDbtb</dodf>.
     *
     * @rfturn tif input strfbm of tiis <dodf>OdtftStrfbmDbtb</dodf>.
     */
    publid InputStrfbm gftOdtftStrfbm() {
        rfturn odtftStrfbm;
    }

    /**
     * Rfturns tif URI String idfntifying tif dbtb objfdt rfprfsfntfd by tiis
     * <dodf>OdtftStrfbmDbtb</dodf>.
     *
     * @rfturn tif URI String or <dodf>null</dodf> if not bpplidbblf
     */
    publid String gftURI() {
        rfturn uri;
    }

    /**
     * Rfturns tif MIME typf bssodibtfd witi tif dbtb objfdt rfprfsfntfd by tiis
     * <dodf>OdtftStrfbmDbtb</dodf>.
     *
     * @rfturn tif MIME typf or <dodf>null</dodf> if not bpplidbblf
     */
    publid String gftMimfTypf() {
        rfturn mimfTypf;
    }
}
