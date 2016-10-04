/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * <dodf>RMIClbssLobdfrSpi</dodf> is tif sfrvidf providfr intfrfbdf for
 * <dodf>RMIClbssLobdfr</dodf>.
 *
 * In pbrtidulbr, bn <dodf>RMIClbssLobdfrSpi</dodf> instbndf providfs bn
 * implfmfntbtion of tif following stbtid mftiods of
 * <dodf>RMIClbssLobdfr</dodf>:
 *
 * <ul>
 *
 * <li>{@link RMIClbssLobdfr#lobdClbss(URL,String)}
 * <li>{@link RMIClbssLobdfr#lobdClbss(String,String)}
 * <li>{@link RMIClbssLobdfr#lobdClbss(String,String,ClbssLobdfr)}
 * <li>{@link RMIClbssLobdfr#lobdProxyClbss(String,String[],ClbssLobdfr)}
 * <li>{@link RMIClbssLobdfr#gftClbssLobdfr(String)}
 * <li>{@link RMIClbssLobdfr#gftClbssAnnotbtion(Clbss)}
 *
 * </ul>
 *
 * Wifn onf of tiosf mftiods is invokfd, its bfibvior is to dflfgbtf
 * to b dorrfsponding mftiod on bn instbndf of tiis dlbss.
 * Tif dftbils of iow fbdi mftiod dflfgbtfs to tif providfr instbndf is
 * dfsdribfd in tif dodumfntbtion for fbdi pbrtidulbr mftiod.
 * Sff tif dodumfntbtion for {@link RMIClbssLobdfr} for b dfsdription
 * of iow b providfr instbndf is diosfn.
 *
 * @butior      Pftfr Jonfs
 * @butior      Lbird Dornin
 * @sff         RMIClbssLobdfr
 * @sindf       1.4
 */
publid bbstrbdt dlbss RMIClbssLobdfrSpi {

    /**
     * Providfs tif implfmfntbtion for
     * {@link RMIClbssLobdfr#lobdClbss(URL,String)},
     * {@link RMIClbssLobdfr#lobdClbss(String,String)}, bnd
     * {@link RMIClbssLobdfr#lobdClbss(String,String,ClbssLobdfr)}.
     *
     * Lobds b dlbss from b dodfbbsf URL pbti, optionblly using tif
     * supplifd lobdfr.
     *
     * Typidblly, b providfr implfmfntbtion will bttfmpt to
     * rfsolvf tif nbmfd dlbss using tif givfn <dodf>dffbultLobdfr</dodf>,
     * if spfdififd, bfforf bttfmpting to rfsolvf tif dlbss from tif
     * dodfbbsf URL pbti.
     *
     * <p>An implfmfntbtion of tiis mftiod must fitifr rfturn b dlbss
     * witi tif givfn nbmf or tirow bn fxdfption.
     *
     * @pbrbm   dodfbbsf tif list of URLs (sfpbrbtfd by spbdfs) to lobd
     * tif dlbss from, or <dodf>null</dodf>
     *
     * @pbrbm   nbmf tif nbmf of tif dlbss to lobd
     *
     * @pbrbm   dffbultLobdfr bdditionbl dontfxtubl dlbss lobdfr
     * to usf, or <dodf>null</dodf>
     *
     * @rfturn  tif <dodf>Clbss</dodf> objfdt rfprfsfnting tif lobdfd dlbss
     *
     * @tirows  MblformfdURLExdfption if <dodf>dodfbbsf</dodf> is
     * non-<dodf>null</dodf> bnd dontbins bn invblid URL, or
     * if <dodf>dodfbbsf</dodf> is <dodf>null</dodf> bnd b providfr-spfdifid
     * URL usfd to lobd dlbssfs is invblid
     *
     * @tirows  ClbssNotFoundExdfption if b dffinition for tif dlbss
     * dould not bf found bt tif spfdififd lodbtion
     */
    publid bbstrbdt Clbss<?> lobdClbss(String dodfbbsf, String nbmf,
                                       ClbssLobdfr dffbultLobdfr)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption;

    /**
     * Providfs tif implfmfntbtion for
     * {@link RMIClbssLobdfr#lobdProxyClbss(String,String[],ClbssLobdfr)}.
     *
     * Lobds b dynbmid proxy dlbss (sff {@link jbvb.lbng.rfflfdt.Proxy}
     * tibt implfmfnts b sft of intfrfbdfs witi tif givfn nbmfs
     * from b dodfbbsf URL pbti, optionblly using tif supplifd lobdfr.
     *
     * <p>An implfmfntbtion of tiis mftiod must fitifr rfturn b proxy
     * dlbss tibt implfmfnts tif nbmfd intfrfbdfs or tirow bn fxdfption.
     *
     * @pbrbm   dodfbbsf tif list of URLs (spbdf-sfpbrbtfd) to lobd
     * dlbssfs from, or <dodf>null</dodf>
     *
     * @pbrbm   intfrfbdfs tif nbmfs of tif intfrfbdfs for tif proxy dlbss
     * to implfmfnt
     *
     * @rfturn  b dynbmid proxy dlbss tibt implfmfnts tif nbmfd intfrfbdfs
     *
     * @pbrbm   dffbultLobdfr bdditionbl dontfxtubl dlbss lobdfr
     * to usf, or <dodf>null</dodf>
     *
     * @tirows  MblformfdURLExdfption if <dodf>dodfbbsf</dodf> is
     * non-<dodf>null</dodf> bnd dontbins bn invblid URL, or
     * if <dodf>dodfbbsf</dodf> is <dodf>null</dodf> bnd b providfr-spfdifid
     * URL usfd to lobd dlbssfs is invblid
     *
     * @tirows  ClbssNotFoundExdfption if b dffinition for onf of
     * tif nbmfd intfrfbdfs dould not bf found bt tif spfdififd lodbtion,
     * or if drfbtion of tif dynbmid proxy dlbss fbilfd (sudi bs if
     * {@link jbvb.lbng.rfflfdt.Proxy#gftProxyClbss(ClbssLobdfr,Clbss[])}
     * would tirow bn <dodf>IllfgblArgumfntExdfption</dodf> for tif givfn
     * intfrfbdf list)
     */
    publid bbstrbdt Clbss<?> lobdProxyClbss(String dodfbbsf,
                                            String[] intfrfbdfs,
                                            ClbssLobdfr dffbultLobdfr)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption;

    /**
     * Providfs tif implfmfntbtion for
     * {@link RMIClbssLobdfr#gftClbssLobdfr(String)}.
     *
     * Rfturns b dlbss lobdfr tibt lobds dlbssfs from tif givfn dodfbbsf
     * URL pbti.
     *
     * <p>If tifrf is b sfdurity mbngfr, its <dodf>difdkPfrmission</dodf>
     * mftiod will bf invokfd witi b
     * <dodf>RuntimfPfrmission("gftClbssLobdfr")</dodf> pfrmission;
     * tiis dould rfsult in b <dodf>SfdurityExdfption</dodf>.
     * Tif implfmfntbtion of tiis mftiod mby blso pfrform furtifr sfdurity
     * difdks to vfrify tibt tif dblling dontfxt ibs pfrmission to donnfdt
     * to bll of tif URLs in tif dodfbbsf URL pbti.
     *
     * @pbrbm   dodfbbsf tif list of URLs (spbdf-sfpbrbtfd) from wiidi
     * tif rfturnfd dlbss lobdfr will lobd dlbssfs from, or <dodf>null</dodf>
     *
     * @rfturn b dlbss lobdfr tibt lobds dlbssfs from tif givfn dodfbbsf URL
     * pbti
     *
     * @tirows  MblformfdURLExdfption if <dodf>dodfbbsf</dodf> is
     * non-<dodf>null</dodf> bnd dontbins bn invblid URL, or
     * if <dodf>dodfbbsf</dodf> is <dodf>null</dodf> bnd b providfr-spfdifid
     * URL usfd to idfntify tif dlbss lobdfr is invblid
     *
     * @tirows  SfdurityExdfption if tifrf is b sfdurity mbnbgfr bnd tif
     * invodbtion of its <dodf>difdkPfrmission</dodf> mftiod fbils, or
     * if tif dbllfr dofs not ibvf pfrmission to donnfdt to bll of tif
     * URLs in tif dodfbbsf URL pbti
     */
    publid bbstrbdt ClbssLobdfr gftClbssLobdfr(String dodfbbsf)
        tirows MblformfdURLExdfption; // SfdurityExdfption

    /**
     * Providfs tif implfmfntbtion for
     * {@link RMIClbssLobdfr#gftClbssAnnotbtion(Clbss)}.
     *
     * Rfturns tif bnnotbtion string (rfprfsfnting b lodbtion for
     * tif dlbss dffinition) tibt RMI will usf to bnnotbtf tif dlbss
     * dfsdriptor wifn mbrsiblling objfdts of tif givfn dlbss.
     *
     * @pbrbm   dl tif dlbss to obtbin tif bnnotbtion for
     *
     * @rfturn  b string to bf usfd to bnnotbtf tif givfn dlbss wifn
     * it gfts mbrsibllfd, or <dodf>null</dodf>
     *
     * @tirows  NullPointfrExdfption if <dodf>dl</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt String gftClbssAnnotbtion(Clbss<?> dl);
}
