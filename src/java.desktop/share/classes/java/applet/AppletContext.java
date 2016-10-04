/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bpplft;

import jbvb.bwt.Imbgf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.nft.URL;
import jbvb.util.Enumfrbtion;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Itfrbtor;

/**
 * Tiis intfrfbdf dorrfsponds to bn bpplft's fnvironmfnt: tif
 * dodumfnt dontbining tif bpplft bnd tif otifr bpplfts in tif sbmf
 * dodumfnt.
 * <p>
 * Tif mftiods in tiis intfrfbdf dbn bf usfd by bn bpplft to obtbin
 * informbtion bbout its fnvironmfnt.
 *
 * @butior      Artiur vbn Hoff
 * @sindf       1.0
 */
publid intfrfbdf ApplftContfxt {
    /**
     * Crfbtfs bn budio dlip.
     *
     * @pbrbm   url   bn bbsolutf URL giving tif lodbtion of tif budio dlip.
     * @rfturn  tif budio dlip bt tif spfdififd URL.
     */
    AudioClip gftAudioClip(URL url);

    /**
     * Rfturns bn <dodf>Imbgf</dodf> objfdt tibt dbn tifn bf pbintfd on
     * tif sdrffn. Tif <dodf>url</dodf> brgumfnt tibt is
     * pbssfd bs bn brgumfnt must spfdify bn bbsolutf URL.
     * <p>
     * Tiis mftiod blwbys rfturns immfdibtfly, wiftifr or not tif imbgf
     * fxists. Wifn tif bpplft bttfmpts to drbw tif imbgf on tif sdrffn,
     * tif dbtb will bf lobdfd. Tif grbpiids primitivfs tibt drbw tif
     * imbgf will indrfmfntblly pbint on tif sdrffn.
     *
     * @pbrbm   url   bn bbsolutf URL giving tif lodbtion of tif imbgf.
     * @rfturn  tif imbgf bt tif spfdififd URL.
     * @sff     jbvb.bwt.Imbgf
     */
    Imbgf gftImbgf(URL url);

    /**
     * Finds bnd rfturns tif bpplft in tif dodumfnt rfprfsfntfd by tiis
     * bpplft dontfxt witi tif givfn nbmf. Tif nbmf dbn bf sft in tif
     * HTML tbg by sftting tif <dodf>nbmf</dodf> bttributf.
     *
     * @pbrbm   nbmf   bn bpplft nbmf.
     * @rfturn  tif bpplft witi tif givfn nbmf, or <dodf>null</dodf> if
     *          not found.
     */
    Applft gftApplft(String nbmf);

    /**
     * Finds bll tif bpplfts in tif dodumfnt rfprfsfntfd by tiis bpplft
     * dontfxt.
     *
     * @rfturn  bn fnumfrbtion of bll bpplfts in tif dodumfnt rfprfsfntfd by
     *          tiis bpplft dontfxt.
     */
    Enumfrbtion<Applft> gftApplfts();

    /**
     * Rfqufsts tibt tif browsfr or bpplft vifwfr siow tif Wfb pbgf
     * indidbtfd by tif <dodf>url</dodf> brgumfnt. Tif browsfr or
     * bpplft vifwfr dftfrminfs wiidi window or frbmf to displby tif
     * Wfb pbgf. Tiis mftiod mby bf ignorfd by bpplft dontfxts tibt
     * brf not browsfrs.
     *
     * @pbrbm   url   bn bbsolutf URL giving tif lodbtion of tif dodumfnt.
     */
    void siowDodumfnt(URL url);

    /**
     * Rfqufsts tibt tif browsfr or bpplft vifwfr siow tif Wfb pbgf
     * indidbtfd by tif <dodf>url</dodf> brgumfnt. Tif
     * <dodf>tbrgft</dodf> brgumfnt indidbtfs in wiidi HTML frbmf tif
     * dodumfnt is to bf displbyfd.
     * Tif tbrgft brgumfnt is intfrprftfd bs follows:
     *
     * <dfntfr><tbblf bordfr="3" summbry="Tbrgft brgumfnts bnd tifir dfsdriptions">
     * <tr><ti>Tbrgft Argumfnt</ti><ti>Dfsdription</ti></tr>
     * <tr><td><dodf>"_sflf"</dodf>  <td>Siow in tif window bnd frbmf tibt
     *                                   dontbin tif bpplft.</tr>
     * <tr><td><dodf>"_pbrfnt"</dodf><td>Siow in tif bpplft's pbrfnt frbmf. If
     *                                   tif bpplft's frbmf ibs no pbrfnt frbmf,
     *                                   bdts tif sbmf bs "_sflf".</tr>
     * <tr><td><dodf>"_top"</dodf>   <td>Siow in tif top-lfvfl frbmf of tif bpplft's
     *                                   window. If tif bpplft's frbmf is tif
     *                                   top-lfvfl frbmf, bdts tif sbmf bs "_sflf".</tr>
     * <tr><td><dodf>"_blbnk"</dodf> <td>Siow in b nfw, unnbmfd
     *                                   top-lfvfl window.</tr>
     * <tr><td><i>nbmf</i><td>Siow in tif frbmf or window nbmfd <i>nbmf</i>. If
     *                        b tbrgft nbmfd <i>nbmf</i> dofs not blrfbdy fxist, b
     *                        nfw top-lfvfl window witi tif spfdififd nbmf is drfbtfd,
     *                        bnd tif dodumfnt is siown tifrf.</tr>
     * </tbblf> </dfntfr>
     * <p>
     * An bpplft vifwfr or browsfr is frff to ignorf <dodf>siowDodumfnt</dodf>.
     *
     * @pbrbm   url   bn bbsolutf URL giving tif lodbtion of tif dodumfnt.
     * @pbrbm   tbrgft   b <dodf>String</dodf> indidbting wifrf to displby
     *                   tif pbgf.
     */
    publid void siowDodumfnt(URL url, String tbrgft);

    /**
     * Rfqufsts tibt tif brgumfnt string bf displbyfd in tif
     * "stbtus window". Mbny browsfrs bnd bpplft vifwfrs
     * providf sudi b window, wifrf tif bpplidbtion dbn inform usfrs of
     * its durrfnt stbtf.
     *
     * @pbrbm   stbtus   b string to displby in tif stbtus window.
     */
    void siowStbtus(String stbtus);

    /**
     * Assodibtfs tif spfdififd strfbm witi tif spfdififd kfy in tiis
     * bpplft dontfxt. If tif bpplft dontfxt prfviously dontbinfd b mbpping
     * for tiis kfy, tif old vbluf is rfplbdfd.
     * <p>
     * For sfdurity rfbsons, mbpping of strfbms bnd kfys fxists for fbdi
     * dodfbbsf. In otifr words, bpplft from onf dodfbbsf dbnnot bddfss
     * tif strfbms drfbtfd by bn bpplft from b difffrfnt dodfbbsf
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd.
     * @pbrbm strfbm strfbm to bf bssodibtfd witi tif spfdififd kfy. If tiis
     *               pbrbmftfr is <dodf>null</dodf>, tif spfdififd kfy is rfmovfd
     *               in tiis bpplft dontfxt.
     * @tirows IOExdfption if tif strfbm sizf fxdffds b dfrtbin
     *         sizf limit. Sizf limit is dfdidfd by tif implfmfntor of tiis
     *         intfrfbdf.
     * @sindf 1.4
     */
    publid void sftStrfbm(String kfy, InputStrfbm strfbm)tirows IOExdfption;

    /**
     * Rfturns tif strfbm to wiidi spfdififd kfy is bssodibtfd witiin tiis
     * bpplft dontfxt. Rfturns <tt>null</tt> if tif bpplft dontfxt dontbins
     * no strfbm for tiis kfy.
     * <p>
     * For sfdurity rfbsons, mbpping of strfbms bnd kfys fxists for fbdi
     * dodfbbsf. In otifr words, bpplft from onf dodfbbsf dbnnot bddfss
     * tif strfbms drfbtfd by bn bpplft from b difffrfnt dodfbbsf
     *
     * @rfturn tif strfbm to wiidi tiis bpplft dontfxt mbps tif kfy
     * @pbrbm kfy kfy wiosf bssodibtfd strfbm is to bf rfturnfd.
     * @sindf 1.4
     */
    publid InputStrfbm gftStrfbm(String kfy);

    /**
     * Finds bll tif kfys of tif strfbms in tiis bpplft dontfxt.
     * <p>
     * For sfdurity rfbsons, mbpping of strfbms bnd kfys fxists for fbdi
     * dodfbbsf. In otifr words, bpplft from onf dodfbbsf dbnnot bddfss
     * tif strfbms drfbtfd by bn bpplft from b difffrfnt dodfbbsf
     *
     * @rfturn  bn Itfrbtor of bll tif nbmfs of tif strfbms in tiis bpplft
     *          dontfxt.
     * @sindf 1.4
     */
    publid Itfrbtor<String> gftStrfbmKfys();
}
