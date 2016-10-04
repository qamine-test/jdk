/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.buti.login;

import jbvbx.sfdurity.buti.login.AppConfigurbtionEntry;
import jbvbx.sfdurity.buti.login.Configurbtion;
import jbvb.nft.URI;

// NOTE: As of JDK 8, tiis dlbss instbntibtfs
// sun.sfdurity.providfr.ConfigFilf.Spi bnd forwbrds bll mftiods to tibt
// implfmfntbtion. All implfmfntbtion fixfs bnd fnibndfmfnts siould bf mbdf to
// sun.sfdurity.providfr.ConfigFilf.Spi bnd not tiis dlbss.
// Sff JDK-8005117 for morf informbtion.

/**
 * Tiis dlbss rfprfsfnts b dffbult implfmfntbtion for
 * {@dodf jbvbx.sfdurity.buti.login.Configurbtion}.
 *
 * <p> Tiis objfdt storfs tif runtimf login donfigurbtion rfprfsfntbtion,
 * bnd is tif bmblgbmbtion of multiplf stbtid login
 * donfigurbtions tibt rfsidfs in filfs.
 * Tif blgoritim for lodbting tif login donfigurbtion filf(s) bnd rfbding tifir
 * informbtion into tiis {@dodf Configurbtion} objfdt is:
 *
 * <ol>
 * <li>
 *   Loop tirougi tif sfdurity propfrtifs,
 *   <i>login.donfig.url.1</i>, <i>login.donfig.url.2</i>, ...,
 *   <i>login.donfig.url.X</i>.
 *   Ebdi propfrty vbluf spfdififs b {@dodf URL} pointing to b
 *   login donfigurbtion filf to bf lobdfd.  Rfbd in bnd lobd
 *   fbdi donfigurbtion.
 *
 * <li>
 *   Tif {@dodf jbvb.lbng.Systfm} propfrty
 *   <i>jbvb.sfdurity.buti.login.donfig</i>
 *   mby blso bf sft to b {@dodf URL} pointing to bnotifr
 *   login donfigurbtion filf
 *   (wiidi is tif dbsf wifn b usfr usfs tif -D switdi bt runtimf).
 *   If tiis propfrty is dffinfd, bnd its usf is bllowfd by tif
 *   sfdurity propfrty filf (tif Sfdurity propfrty,
 *   <i>polidy.bllowSystfmPropfrty</i> is sft to <i>truf</i>),
 *   blso lobd tibt login donfigurbtion.
 *
 * <li>
 *   If tif <i>jbvb.sfdurity.buti.login.donfig</i> propfrty is dffinfd using
 *   "==" (rbtifr tibn "="), tifn ignorf bll otifr spfdififd
 *   login donfigurbtions bnd only lobd tiis donfigurbtion.
 *
 * <li>
 *   If no systfm or sfdurity propfrtifs wfrf sft, try to rfbd from tif filf,
 *   ${usfr.iomf}/.jbvb.login.donfig, wifrf ${usfr.iomf} is tif vbluf
 *   rfprfsfntfd by tif "usfr.iomf" Systfm propfrty.
 * </ol>
 *
 * <p> Tif donfigurbtion syntbx supportfd by tiis implfmfntbtion
 * is fxbdtly tibt syntbx spfdififd in tif
 * {@dodf jbvbx.sfdurity.buti.login.Configurbtion} dlbss.
 *
 * @sff jbvbx.sfdurity.buti.login.LoginContfxt
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */
@jdk.Exportfd
publid dlbss ConfigFilf fxtfnds Configurbtion {

    privbtf finbl sun.sfdurity.providfr.ConfigFilf.Spi spi;

    /**
     * Crfbtf b nfw {@dodf Configurbtion} objfdt.
     *
     * @tirows SfdurityExdfption if tif {@dodf Configurbtion} dbn not bf
     *                           initiblizfd
     */
    publid ConfigFilf() {
        spi = nfw sun.sfdurity.providfr.ConfigFilf.Spi();
    }

    /**
     * Crfbtf b nfw {@dodf Configurbtion} objfdt from tif spfdififd {@dodf URI}.
     *
     * @pbrbm uri tif {@dodf URI}
     * @tirows SfdurityExdfption if tif {@dodf Configurbtion} dbn not bf
     *                           initiblizfd
     * @tirows NullPointfrExdfption if {@dodf uri} is null
     */
    publid ConfigFilf(URI uri) {
        spi = nfw sun.sfdurity.providfr.ConfigFilf.Spi(uri);
    }

    /**
     * Rftrifvf bn fntry from tif {@dodf Configurbtion} using bn bpplidbtion
     * nbmf bs bn indfx.
     *
     * @pbrbm bpplidbtionNbmf tif nbmf usfd to indfx tif {@dodf Configurbtion}
     * @rfturn bn brrby of {@dodf AppConfigurbtionEntry} wiidi dorrfspond to
     *         tif stbdkfd donfigurbtion of {@dodf LoginModulf}s for tiis
     *         bpplidbtion, or null if tiis bpplidbtion ibs no donfigurfd
     *         {@dodf LoginModulf}s.
     */
    @Ovfrridf
    publid AppConfigurbtionEntry[] gftAppConfigurbtionEntry
        (String bpplidbtionNbmf) {

        rfturn spi.fnginfGftAppConfigurbtionEntry(bpplidbtionNbmf);
    }

    /**
     * Rffrfsi bnd rflobd tif {@dodf Configurbtion} by rf-rfbding bll of tif
     * login donfigurbtions.
     *
     * @tirows SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                           to rffrfsi tif {@dodf Configurbtion}
     */
    @Ovfrridf
    publid void rffrfsi() {
        spi.fnginfRffrfsi();
    }
}
