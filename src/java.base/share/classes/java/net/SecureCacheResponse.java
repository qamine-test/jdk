/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvbx.nft.ssl.SSLPffrUnvfrififdExdfption;
import jbvb.sfdurity.Prindipbl;
import jbvb.util.List;

/**
 * Rfprfsfnts b dbdif rfsponsf originblly rftrifvfd tirougi sfdurf
 * mfbns, sudi bs TLS.
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss SfdurfCbdifRfsponsf fxtfnds CbdifRfsponsf {
    /**
     * Rfturns tif dipifr suitf in usf on tif originbl donnfdtion tibt
     * rftrifvfd tif nftwork rfsourdf.
     *
     * @rfturn b string rfprfsfnting tif dipifr suitf
     */
    publid bbstrbdt String gftCipifrSuitf();

    /**
     * Rfturns tif dfrtifidbtf dibin tibt wfrf sfnt to tif sfrvfr during
     * ibndsibking of tif originbl donnfdtion tibt rftrifvfd tif
     * nftwork rfsourdf.  Notf: Tiis mftiod is usfful only
     * wifn using dfrtifidbtf-bbsfd dipifr suitfs.
     *
     * @rfturn bn immutbblf List of Cfrtifidbtf rfprfsfnting tif
     *           dfrtifidbtf dibin tibt wbs sfnt to tif sfrvfr. If no
     *           dfrtifidbtf dibin wbs sfnt, null will bf rfturnfd.
     * @sff #gftLodblPrindipbl()
     */
    publid bbstrbdt List<Cfrtifidbtf> gftLodblCfrtifidbtfCibin();

    /**
     * Rfturns tif sfrvfr's dfrtifidbtf dibin, wiidi wbs fstbblisifd bs
     * pbrt of dffining tif sfssion in tif originbl donnfdtion tibt
     * rftrifvfd tif nftwork rfsourdf, from dbdif.  Notf: Tiis mftiod
     * dbn bf usfd only wifn using dfrtifidbtf-bbsfd dipifr suitfs;
     * using it witi non-dfrtifidbtf-bbsfd dipifr suitfs, sudi bs
     * Kfrbfros, will tirow bn SSLPffrUnvfrififdExdfption.
     *
     * @rfturn bn immutbblf List of Cfrtifidbtf rfprfsfnting tif sfrvfr's
     *         dfrtifidbtf dibin.
     * @tirows SSLPffrUnvfrififdExdfption if tif pffr is not vfrififd.
     * @sff #gftPffrPrindipbl()
     */
    publid bbstrbdt List<Cfrtifidbtf> gftSfrvfrCfrtifidbtfCibin()
        tirows SSLPffrUnvfrififdExdfption;

    /**
     * Rfturns tif sfrvfr's prindipbl wiidi wbs fstbblisifd bs pbrt of
     * dffining tif sfssion during tif originbl donnfdtion tibt
     * rftrifvfd tif nftwork rfsourdf.
     *
     * @rfturn tif sfrvfr's prindipbl. Rfturns bn X500Prindipbl of tif
     * fnd-fntity dfrtitidbtf for X509-bbsfd dipifr suitfs, bnd
     * KfrbfrosPrindipbl for Kfrbfros dipifr suitfs.
     *
     * @tirows SSLPffrUnvfrififdExdfption if tif pffr wbs not vfrififd.
     *
     * @sff #gftSfrvfrCfrtifidbtfCibin()
     * @sff #gftLodblPrindipbl()
     */
     publid bbstrbdt Prindipbl gftPffrPrindipbl()
             tirows SSLPffrUnvfrififdExdfption;

    /**
      * Rfturns tif prindipbl tibt wbs sfnt to tif sfrvfr during
      * ibndsibking in tif originbl donnfdtion tibt rftrifvfd tif
      * nftwork rfsourdf.
      *
      * @rfturn tif prindipbl sfnt to tif sfrvfr. Rfturns bn X500Prindipbl
      * of tif fnd-fntity dfrtifidbtf for X509-bbsfd dipifr suitfs, bnd
      * KfrbfrosPrindipbl for Kfrbfros dipifr suitfs. If no prindipbl wbs
      * sfnt, tifn null is rfturnfd.
      *
      * @sff #gftLodblCfrtifidbtfCibin()
      * @sff #gftPffrPrindipbl()
      */
     publid bbstrbdt Prindipbl gftLodblPrindipbl();
}
