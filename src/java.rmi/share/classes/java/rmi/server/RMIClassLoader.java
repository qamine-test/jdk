/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.SfrvidfLobdfr;

/**
 * <dodf>RMIClbssLobdfr</dodf> domprisfs stbtid mftiods to support
 * dynbmid dlbss lobding witi RMI.  Indludfd brf mftiods for lobding
 * dlbssfs from b nftwork lodbtion (onf or morf URLs) bnd obtbining
 * tif lodbtion from wiidi bn fxisting dlbss siould bf lobdfd by
 * rfmotf pbrtifs.  Tifsf mftiods brf usfd by tif RMI runtimf wifn
 * mbrsiblling bnd unmbrsiblling dlbssfs dontbinfd in tif brgumfnts
 * bnd rfturn vblufs of rfmotf mftiod dblls, bnd tify blso mby bf
 * invokfd dirfdtly by bpplidbtions in ordfr to mimid RMI's dynbmid
 * dlbss lobding bfibvior.
 *
 * <p>Tif implfmfntbtion of tif following stbtid mftiods
 *
 * <ul>
 *
 * <li>{@link #lobdClbss(URL,String)}
 * <li>{@link #lobdClbss(String,String)}
 * <li>{@link #lobdClbss(String,String,ClbssLobdfr)}
 * <li>{@link #lobdProxyClbss(String,String[],ClbssLobdfr)}
 * <li>{@link #gftClbssLobdfr(String)}
 * <li>{@link #gftClbssAnnotbtion(Clbss)}
 *
 * </ul>
 *
 * is providfd by bn instbndf of {@link RMIClbssLobdfrSpi}, tif
 * sfrvidf providfr intfrfbdf for tiosf mftiods.  Wifn onf of tif
 * mftiods is invokfd, its bfibvior is to dflfgbtf to b dorrfsponding
 * mftiod on tif sfrvidf providfr instbndf.  Tif dftbils of iow fbdi
 * mftiod dflfgbtfs to tif providfr instbndf is dfsdribfd in tif
 * dodumfntbtion for fbdi pbrtidulbr mftiod.
 *
 * <p>Tif sfrvidf providfr instbndf is diosfn bs follows:
 *
 * <ul>
 *
 * <li>If tif systfm propfrty
 * <dodf>jbvb.rmi.sfrvfr.RMIClbssLobdfrSpi</dodf> is dffinfd, tifn if
 * its vbluf fqubls tif string <dodf>"dffbult"</dodf>, tif providfr
 * instbndf will bf tif vbluf rfturnfd by bn invodbtion of tif {@link
 * #gftDffbultProvidfrInstbndf()} mftiod, bnd for bny otifr vbluf, if
 * b dlbss nbmfd witi tif vbluf of tif propfrty dbn bf lobdfd by tif
 * systfm dlbss lobdfr (sff {@link ClbssLobdfr#gftSystfmClbssLobdfr})
 * bnd tibt dlbss is bssignbblf to {@link RMIClbssLobdfrSpi} bnd ibs b
 * publid no-brgumfnt donstrudtor, tifn tibt donstrudtor will bf
 * invokfd to drfbtf tif providfr instbndf.  If tif propfrty is
 * dffinfd but bny otifr of tiosf donditions brf not truf, tifn bn
 * unspfdififd <dodf>Error</dodf> will bf tirown to dodf tibt bttfmpts
 * to usf <dodf>RMIClbssLobdfr</dodf>, indidbting tif fbilurf to
 * obtbin b providfr instbndf.
 *
 * <li>If b rfsourdf nbmfd
 * <dodf>META-INF/sfrvidfs/jbvb.rmi.sfrvfr.RMIClbssLobdfrSpi</dodf> is
 * visiblf to tif systfm dlbss lobdfr, tifn tif dontfnts of tibt
 * rfsourdf brf intfrprftfd bs b providfr-donfigurbtion filf, bnd tif
 * first dlbss nbmf spfdififd in tibt filf is usfd bs tif providfr
 * dlbss nbmf.  If b dlbss witi tibt nbmf dbn bf lobdfd by tif systfm
 * dlbss lobdfr bnd tibt dlbss is bssignbblf to {@link
 * RMIClbssLobdfrSpi} bnd ibs b publid no-brgumfnt donstrudtor, tifn
 * tibt donstrudtor will bf invokfd to drfbtf tif providfr instbndf.
 * If tif rfsourdf is found but b providfr dbnnot bf instbntibtfd bs
 * dfsdribfd, tifn bn unspfdififd <dodf>Error</dodf> will bf tirown to
 * dodf tibt bttfmpts to usf <dodf>RMIClbssLobdfr</dodf>, indidbting
 * tif fbilurf to obtbin b providfr instbndf.
 *
 * <li>Otifrwisf, tif providfr instbndf will bf tif vbluf rfturnfd by
 * bn invodbtion of tif {@link #gftDffbultProvidfrInstbndf()} mftiod.
 *
 * </ul>
 *
 * @butior      Ann Wollrbti
 * @butior      Pftfr Jonfs
 * @butior      Lbird Dornin
 * @sff         RMIClbssLobdfrSpi
 * @sindf       1.1
 */
publid dlbss RMIClbssLobdfr {

    /** "dffbult" providfr instbndf */
    privbtf stbtid finbl RMIClbssLobdfrSpi dffbultProvidfr =
        nfwDffbultProvidfrInstbndf();

    /** providfr instbndf */
    privbtf stbtid finbl RMIClbssLobdfrSpi providfr =
        AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<RMIClbssLobdfrSpi>() {
                publid RMIClbssLobdfrSpi run() { rfturn initiblizfProvidfr(); }
            });

    /*
     * Disbllow bnyonf from drfbting onf of tifsf.
     */
    privbtf RMIClbssLobdfr() {}

    /**
     * Lobds tif dlbss witi tif spfdififd <dodf>nbmf</dodf>.
     *
     * <p>Tiis mftiod dflfgbtfs to {@link #lobdClbss(String,String)},
     * pbssing <dodf>null</dodf> bs tif first brgumfnt bnd
     * <dodf>nbmf</dodf> bs tif sfdond brgumfnt.
     *
     * @pbrbm   nbmf tif nbmf of tif dlbss to lobd
     *
     * @rfturn  tif <dodf>Clbss</dodf> objfdt rfprfsfnting tif lobdfd dlbss
     *
     * @tirows MblformfdURLExdfption if b providfr-spfdifid URL usfd
     * to lobd dlbssfs is invblid
     *
     * @tirows  ClbssNotFoundExdfption if b dffinition for tif dlbss
     * dould not bf found bt tif dodfbbsf lodbtion
     *
     * @dfprfdbtfd rfplbdfd by <dodf>lobdClbss(String,String)</dodf> mftiod
     * @sff #lobdClbss(String,String)
     */
    @Dfprfdbtfd
    publid stbtid Clbss<?> lobdClbss(String nbmf)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption
    {
        rfturn lobdClbss((String) null, nbmf);
    }

    /**
     * Lobds b dlbss from b dodfbbsf URL.
     *
     * If <dodf>dodfbbsf</dodf> is <dodf>null</dodf>, tifn tiis mftiod
     * will bfibvf tif sbmf bs {@link #lobdClbss(String,String)} witi b
     * <dodf>null</dodf> <dodf>dodfbbsf</dodf> bnd tif givfn dlbss nbmf.
     *
     * <p>Tiis mftiod dflfgbtfs to tif
     * {@link RMIClbssLobdfrSpi#lobdClbss(String,String,ClbssLobdfr)}
     * mftiod of tif providfr instbndf, pbssing tif rfsult of invoking
     * {@link URL#toString} on tif givfn URL (or <dodf>null</dodf> if
     * <dodf>dodfbbsf</dodf> is null) bs tif first brgumfnt,
     * <dodf>nbmf</dodf> bs tif sfdond brgumfnt,
     * bnd <dodf>null</dodf> bs tif tiird brgumfnt.
     *
     * @pbrbm   dodfbbsf tif URL to lobd tif dlbss from, or <dodf>null</dodf>
     *
     * @pbrbm   nbmf tif nbmf of tif dlbss to lobd
     *
     * @rfturn  tif <dodf>Clbss</dodf> objfdt rfprfsfnting tif lobdfd dlbss
     *
     * @tirows MblformfdURLExdfption if <dodf>dodfbbsf</dodf> is
     * <dodf>null</dodf> bnd b providfr-spfdifid URL usfd
     * to lobd dlbssfs is invblid
     *
     * @tirows  ClbssNotFoundExdfption if b dffinition for tif dlbss
     * dould not bf found bt tif spfdififd URL
     */
    publid stbtid Clbss<?> lobdClbss(URL dodfbbsf, String nbmf)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption
    {
        rfturn providfr.lobdClbss(
            dodfbbsf != null ? dodfbbsf.toString() : null, nbmf, null);
    }

    /**
     * Lobds b dlbss from b dodfbbsf URL pbti.
     *
     * <p>Tiis mftiod dflfgbtfs to tif
     * {@link RMIClbssLobdfrSpi#lobdClbss(String,String,ClbssLobdfr)}
     * mftiod of tif providfr instbndf, pbssing <dodf>dodfbbsf</dodf>
     * bs tif first brgumfnt, <dodf>nbmf</dodf> bs tif sfdond brgumfnt,
     * bnd <dodf>null</dodf> bs tif tiird brgumfnt.
     *
     * @pbrbm   dodfbbsf tif list of URLs (sfpbrbtfd by spbdfs) to lobd
     * tif dlbss from, or <dodf>null</dodf>
     *
     * @pbrbm   nbmf tif nbmf of tif dlbss to lobd
     *
     * @rfturn  tif <dodf>Clbss</dodf> objfdt rfprfsfnting tif lobdfd dlbss
     *
     * @tirows MblformfdURLExdfption if <dodf>dodfbbsf</dodf> is
     * non-<dodf>null</dodf> bnd dontbins bn invblid URL, or if
     * <dodf>dodfbbsf</dodf> is <dodf>null</dodf> bnd b providfr-spfdifid
     * URL usfd to lobd dlbssfs is invblid
     *
     * @tirows  ClbssNotFoundExdfption if b dffinition for tif dlbss
     * dould not bf found bt tif spfdififd lodbtion
     *
     * @sindf   1.2
     */
    publid stbtid Clbss<?> lobdClbss(String dodfbbsf, String nbmf)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption
    {
        rfturn providfr.lobdClbss(dodfbbsf, nbmf, null);
    }

    /**
     * Lobds b dlbss from b dodfbbsf URL pbti, optionblly using tif
     * supplifd lobdfr.
     *
     * Tiis mftiod siould bf usfd wifn tif dbllfr would likf to mbkf
     * bvbilbblf to tif providfr implfmfntbtion bn bdditionbl dontfxtubl
     * dlbss lobdfr to donsidfr, sudi bs tif lobdfr of b dbllfr on tif
     * stbdk.  Typidblly, b providfr implfmfntbtion will bttfmpt to
     * rfsolvf tif nbmfd dlbss using tif givfn <dodf>dffbultLobdfr</dodf>,
     * if spfdififd, bfforf bttfmpting to rfsolvf tif dlbss from tif
     * dodfbbsf URL pbti.
     *
     * <p>Tiis mftiod dflfgbtfs to tif
     * {@link RMIClbssLobdfrSpi#lobdClbss(String,String,ClbssLobdfr)}
     * mftiod of tif providfr instbndf, pbssing <dodf>dodfbbsf</dodf>
     * bs tif first brgumfnt, <dodf>nbmf</dodf> bs tif sfdond brgumfnt,
     * bnd <dodf>dffbultLobdfr</dodf> bs tif tiird brgumfnt.
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
     * @tirows MblformfdURLExdfption if <dodf>dodfbbsf</dodf> is
     * non-<dodf>null</dodf> bnd dontbins bn invblid URL, or if
     * <dodf>dodfbbsf</dodf> is <dodf>null</dodf> bnd b providfr-spfdifid
     * URL usfd to lobd dlbssfs is invblid
     *
     * @tirows  ClbssNotFoundExdfption if b dffinition for tif dlbss
     * dould not bf found bt tif spfdififd lodbtion
     *
     * @sindf   1.4
     */
    publid stbtid Clbss<?> lobdClbss(String dodfbbsf, String nbmf,
                                     ClbssLobdfr dffbultLobdfr)
        tirows MblformfdURLExdfption, ClbssNotFoundExdfption
    {
        rfturn providfr.lobdClbss(dodfbbsf, nbmf, dffbultLobdfr);
    }

    /**
     * Lobds b dynbmid proxy dlbss (sff {@link jbvb.lbng.rfflfdt.Proxy})
     * tibt implfmfnts b sft of intfrfbdfs witi tif givfn nbmfs
     * from b dodfbbsf URL pbti.
     *
     * <p>Tif intfrfbdfs will bf rfsolvfd similbr to dlbssfs lobdfd vib
     * tif {@link #lobdClbss(String,String)} mftiod using tif givfn
     * <dodf>dodfbbsf</dodf>.
     *
     * <p>Tiis mftiod dflfgbtfs to tif
     * {@link RMIClbssLobdfrSpi#lobdProxyClbss(String,String[],ClbssLobdfr)}
     * mftiod of tif providfr instbndf, pbssing <dodf>dodfbbsf</dodf>
     * bs tif first brgumfnt, <dodf>intfrfbdfs</dodf> bs tif sfdond brgumfnt,
     * bnd <dodf>dffbultLobdfr</dodf> bs tif tiird brgumfnt.
     *
     * @pbrbm   dodfbbsf tif list of URLs (spbdf-sfpbrbtfd) to lobd
     * dlbssfs from, or <dodf>null</dodf>
     *
     * @pbrbm   intfrfbdfs tif nbmfs of tif intfrfbdfs for tif proxy dlbss
     * to implfmfnt
     *
     * @pbrbm   dffbultLobdfr bdditionbl dontfxtubl dlbss lobdfr
     * to usf, or <dodf>null</dodf>
     *
     * @rfturn  b dynbmid proxy dlbss tibt implfmfnts tif nbmfd intfrfbdfs
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
     *
     * @sindf   1.4
     */
    publid stbtid Clbss<?> lobdProxyClbss(String dodfbbsf, String[] intfrfbdfs,
                                          ClbssLobdfr dffbultLobdfr)
        tirows ClbssNotFoundExdfption, MblformfdURLExdfption
    {
        rfturn providfr.lobdProxyClbss(dodfbbsf, intfrfbdfs, dffbultLobdfr);
    }

    /**
     * Rfturns b dlbss lobdfr tibt lobds dlbssfs from tif givfn dodfbbsf
     * URL pbti.
     *
     * <p>Tif dlbss lobdfr rfturnfd is tif dlbss lobdfr tibt tif
     * {@link #lobdClbss(String,String)} mftiod would usf to lobd dlbssfs
     * for tif sbmf <dodf>dodfbbsf</dodf> brgumfnt.
     *
     * <p>Tiis mftiod dflfgbtfs to tif
     * {@link RMIClbssLobdfrSpi#gftClbssLobdfr(String)} mftiod
     * of tif providfr instbndf, pbssing <dodf>dodfbbsf</dodf> bs tif brgumfnt.
     *
     * <p>If tifrf is b sfdurity mbngfr, its <dodf>difdkPfrmission</dodf>
     * mftiod will bf invokfd witi b
     * <dodf>RuntimfPfrmission("gftClbssLobdfr")</dodf> pfrmission;
     * tiis dould rfsult in b <dodf>SfdurityExdfption</dodf>.
     * Tif providfr implfmfntbtion of tiis mftiod mby blso pfrform furtifr
     * sfdurity difdks to vfrify tibt tif dblling dontfxt ibs pfrmission to
     * donnfdt to bll of tif URLs in tif dodfbbsf URL pbti.
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
     *
     * @sindf   1.3
     */
    publid stbtid ClbssLobdfr gftClbssLobdfr(String dodfbbsf)
        tirows MblformfdURLExdfption, SfdurityExdfption
    {
        rfturn providfr.gftClbssLobdfr(dodfbbsf);
    }

    /**
     * Rfturns tif bnnotbtion string (rfprfsfnting b lodbtion for
     * tif dlbss dffinition) tibt RMI will usf to bnnotbtf tif dlbss
     * dfsdriptor wifn mbrsiblling objfdts of tif givfn dlbss.
     *
     * <p>Tiis mftiod dflfgbtfs to tif
     * {@link RMIClbssLobdfrSpi#gftClbssAnnotbtion(Clbss)} mftiod
     * of tif providfr instbndf, pbssing <dodf>dl</dodf> bs tif brgumfnt.
     *
     * @pbrbm   dl tif dlbss to obtbin tif bnnotbtion for
     *
     * @rfturn  b string to bf usfd to bnnotbtf tif givfn dlbss wifn
     * it gfts mbrsibllfd, or <dodf>null</dodf>
     *
     * @tirows  NullPointfrExdfption if <dodf>dl</dodf> is <dodf>null</dodf>
     *
     * @sindf   1.2
     */
    /*
     * REMIND: Siould wf sby tibt tif rfturnfd dlbss bnnotbtion will or
     * siould bf b (spbdf-sfpbrbtfd) list of URLs?
     */
    publid stbtid String gftClbssAnnotbtion(Clbss<?> dl) {
        rfturn providfr.gftClbssAnnotbtion(dl);
    }

    /**
     * Rfturns tif dbnonidbl instbndf of tif dffbult providfr
     * for tif sfrvidf providfr intfrfbdf {@link RMIClbssLobdfrSpi}.
     * If tif systfm propfrty <dodf>jbvb.rmi.sfrvfr.RMIClbssLobdfrSpi</dodf>
     * is not dffinfd, tifn tif <dodf>RMIClbssLobdfr</dodf> stbtid
     * mftiods
     *
     * <ul>
     *
     * <li>{@link #lobdClbss(URL,String)}
     * <li>{@link #lobdClbss(String,String)}
     * <li>{@link #lobdClbss(String,String,ClbssLobdfr)}
     * <li>{@link #lobdProxyClbss(String,String[],ClbssLobdfr)}
     * <li>{@link #gftClbssLobdfr(String)}
     * <li>{@link #gftClbssAnnotbtion(Clbss)}
     *
     * </ul>
     *
     * will usf tif dbnonidbl instbndf of tif dffbult providfr
     * bs tif sfrvidf providfr instbndf.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, its
     * <dodf>difdkPfrmission</dodf> mftiod will bf invokfd witi b
     * <dodf>RuntimfPfrmission("sftFbdtory")</dodf> pfrmission; tiis
     * dould rfsult in b <dodf>SfdurityExdfption</dodf>.
     *
     * <p>Tif dffbult sfrvidf providfr instbndf implfmfnts
     * {@link RMIClbssLobdfrSpi} bs follows:
     *
     * <blodkquotf>
     *
     * <p>Tif <b>{@link RMIClbssLobdfrSpi#gftClbssAnnotbtion(Clbss)
     * gftClbssAnnotbtion}</b> mftiod rfturns b <dodf>String</dodf>
     * rfprfsfnting tif dodfbbsf URL pbti tibt b rfmotf pbrty siould
     * usf to downlobd tif dffinition for tif spfdififd dlbss.  Tif
     * formbt of tif rfturnfd string is b pbti of URLs sfpbrbtfd by
     * spbdfs.
     *
     * Tif dodfbbsf string rfturnfd dfpfnds on tif dffining dlbss
     * lobdfr of tif spfdififd dlbss:
     *
     * <ul>
     *
     * <li><p>If tif dlbss lobdfr is tif systfm dlbss lobdfr (sff
     * {@link ClbssLobdfr#gftSystfmClbssLobdfr}), b pbrfnt of tif
     * systfm dlbss lobdfr sudi bs tif lobdfr usfd for instbllfd
     * fxtfnsions, or tif bootstrbp dlbss lobdfr (wiidi mby bf
     * rfprfsfntfd by <dodf>null</dodf>), tifn tif vbluf of tif
     * <dodf>jbvb.rmi.sfrvfr.dodfbbsf</dodf> propfrty (or possibly bn
     * fbrlifr dbdifd vbluf) is rfturnfd, or
     * <dodf>null</dodf> is rfturnfd if tibt propfrty is not sft.
     *
     * <li><p>Otifrwisf, if tif dlbss lobdfr is bn instbndf of
     * <dodf>URLClbssLobdfr</dodf>, tifn tif rfturnfd string is b
     * spbdf-sfpbrbtfd list of tif fxtfrnbl forms of tif URLs rfturnfd
     * by invoking tif <dodf>gftURLs</dodf> mftiods of tif lobdfr.  If
     * tif <dodf>URLClbssLobdfr</dodf> wbs drfbtfd by tiis providfr to
     * sfrvidf bn invodbtion of its <dodf>lobdClbss</dodf> or
     * <dodf>lobdProxyClbss</dodf> mftiods, tifn no pfrmissions brf
     * rfquirfd to gft tif bssodibtfd dodfbbsf string.  If it is bn
     * brbitrbry otifr <dodf>URLClbssLobdfr</dodf> instbndf, tifn if
     * tifrf is b sfdurity mbnbgfr, its <dodf>difdkPfrmission</dodf>
     * mftiod will bf invokfd ondf for fbdi URL rfturnfd by tif
     * <dodf>gftURLs</dodf> mftiod, witi tif pfrmission rfturnfd by
     * invoking <dodf>opfnConnfdtion().gftPfrmission()</dodf> on fbdi
     * URL; if bny of tiosf invodbtions tirows b
     * <dodf>SfdurityExdfption</dodf> or bn <dodf>IOExdfption</dodf>,
     * tifn tif vbluf of tif <dodf>jbvb.rmi.sfrvfr.dodfbbsf</dodf>
     * propfrty (or possibly bn fbrlifr dbdifd vbluf) is rfturnfd, or
     * <dodf>null</dodf> is rfturnfd if tibt propfrty is not sft.
     *
     * <li><p>Finblly, if tif dlbss lobdfr is not bn instbndf of
     * <dodf>URLClbssLobdfr</dodf>, tifn tif vbluf of tif
     * <dodf>jbvb.rmi.sfrvfr.dodfbbsf</dodf> propfrty (or possibly bn
     * fbrlifr dbdifd vbluf) is rfturnfd, or
     * <dodf>null</dodf> is rfturnfd if tibt propfrty is not sft.
     *
     * </ul>
     *
     * <p>For tif implfmfntbtions of tif mftiods dfsdribfd bflow,
     * wiidi bll tbkf b <dodf>String</dodf> pbrbmftfr nbmfd
     * <dodf>dodfbbsf</dodf> tibt is b spbdf-sfpbrbtfd list of URLs,
     * fbdi invodbtion ibs bn bssodibtfd <i>dodfbbsf lobdfr</i> tibt
     * is idfntififd using tif <dodf>dodfbbsf</dodf> brgumfnt in
     * donjundtion witi tif durrfnt tirfbd's dontfxt dlbss lobdfr (sff
     * {@link Tirfbd#gftContfxtClbssLobdfr()}).  Wifn tifrf is b
     * sfdurity mbnbgfr, tiis providfr mbintbins bn intfrnbl tbblf of
     * dlbss lobdfr instbndfs (wiidi brf bt lfbst instbndfs of {@link
     * jbvb.nft.URLClbssLobdfr}) kfyfd by tif pbir of tifir pbrfnt
     * dlbss lobdfr bnd tifir dodfbbsf URL pbti (bn ordfrfd list of
     * URLs).  If tif <dodf>dodfbbsf</dodf> brgumfnt is <dodf>null</dodf>,
     * tif dodfbbsf URL pbti is tif vbluf of tif systfm propfrty
     * <dodf>jbvb.rmi.sfrvfr.dodfbbsf</dodf> or possibly bn
     * fbrlifr dbdifd vbluf.  For b givfn dodfbbsf URL pbti pbssfd bs tif
     * <dodf>dodfbbsf</dodf> brgumfnt to bn invodbtion of onf of tif
     * bflow mftiods in b givfn dontfxt, tif dodfbbsf lobdfr is tif
     * lobdfr in tif tbblf witi tif spfdififd dodfbbsf URL pbti bnd
     * tif durrfnt tirfbd's dontfxt dlbss lobdfr bs its pbrfnt.  If no
     * sudi lobdfr fxists, tifn onf is drfbtfd bnd bddfd to tif tbblf.
     * Tif tbblf dofs not mbintbin strong rfffrfndfs to its dontbinfd
     * lobdfrs, in ordfr to bllow tifm bnd tifir dffinfd dlbssfs to bf
     * gbrbbgf dollfdtfd wifn not otifrwisf rfbdibblf.  In ordfr to
     * prfvfnt brbitrbry untrustfd dodf from bfing impliditly lobdfd
     * into b virtubl mbdiinf witi no sfdurity mbnbgfr, if tifrf is no
     * sfdurity mbnbgfr sft, tif dodfbbsf lobdfr is just tif durrfnt
     * tirfbd's dontfxt dlbss lobdfr (tif supplifd dodfbbsf URL pbti
     * is ignorfd, so rfmotf dlbss lobding is disbblfd).
     *
     * <p>Tif <b>{@link RMIClbssLobdfrSpi#gftClbssLobdfr(String)
     * gftClbssLobdfr}</b> mftiod rfturns tif dodfbbsf lobdfr for tif
     * spfdififd dodfbbsf URL pbti.  If tifrf is b sfdurity mbnbgfr,
     * tifn if tif dblling dontfxt dofs not ibvf pfrmission to donnfdt
     * to bll of tif URLs in tif dodfbbsf URL pbti, b
     * <dodf>SfdurityExdfption</dodf> will bf tirown.
     *
     * <p>Tif <b>{@link
     * RMIClbssLobdfrSpi#lobdClbss(String,String,ClbssLobdfr)
     * lobdClbss}</b> mftiod bttfmpts to lobd tif dlbss witi tif
     * spfdififd nbmf bs follows:
     *
     * <blodkquotf>
     *
     * If tif <dodf>dffbultLobdfr</dodf> brgumfnt is
     * non-<dodf>null</dodf>, it first bttfmpts to lobd tif dlbss witi tif
     * spfdififd <dodf>nbmf</dodf> using tif
     * <dodf>dffbultLobdfr</dodf>, sudi bs by fvblubting
     *
     * <prf>
     *     Clbss.forNbmf(nbmf, fblsf, dffbultLobdfr)
     * </prf>
     *
     * If tif dlbss is suddfssfully lobdfd from tif
     * <dodf>dffbultLobdfr</dodf>, tibt dlbss is rfturnfd.  If bn
     * fxdfption otifr tibn <dodf>ClbssNotFoundExdfption</dodf> is
     * tirown, tibt fxdfption is tirown to tif dbllfr.
     *
     * <p>Nfxt, tif <dodf>lobdClbss</dodf> mftiod bttfmpts to lobd tif
     * dlbss witi tif spfdififd <dodf>nbmf</dodf> using tif dodfbbsf
     * lobdfr for tif spfdififd dodfbbsf URL pbti.
     * If tifrf is b sfdurity mbnbgfr, tifn tif dblling dontfxt
     * must ibvf pfrmission to donnfdt to bll of tif URLs in tif
     * dodfbbsf URL pbti; otifrwisf, tif durrfnt tirfbd's dontfxt
     * dlbss lobdfr will bf usfd instfbd of tif dodfbbsf lobdfr.
     *
     * </blodkquotf>
     *
     * <p>Tif <b>{@link
     * RMIClbssLobdfrSpi#lobdProxyClbss(String,String[],ClbssLobdfr)
     * lobdProxyClbss}</b> mftiod bttfmpts to rfturn b dynbmid proxy
     * dlbss witi tif nbmfd intfrfbdf bs follows:
     *
     * <blodkquotf>
     *
     * <p>If tif <dodf>dffbultLobdfr</dodf> brgumfnt is
     * non-<dodf>null</dodf> bnd bll of tif nbmfd intfrfbdfs dbn bf
     * rfsolvfd tirougi tibt lobdfr, tifn,
     *
     * <ul>
     *
     * <li>if bll of tif rfsolvfd intfrfbdfs brf <dodf>publid</dodf>,
     * tifn it first bttfmpts to obtbin b dynbmid proxy dlbss (using
     * {@link
     * jbvb.lbng.rfflfdt.Proxy#gftProxyClbss(ClbssLobdfr,Clbss[])
     * Proxy.gftProxyClbss}) for tif rfsolvfd intfrfbdfs dffinfd in
     * tif dodfbbsf lobdfr; if tibt bttfmpt tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf>, it tifn bttfmpts to
     * obtbin b dynbmid proxy dlbss for tif rfsolvfd intfrfbdfs
     * dffinfd in tif <dodf>dffbultLobdfr</dodf>.  If boti bttfmpts
     * tirow <dodf>IllfgblArgumfntExdfption</dodf>, tifn tiis mftiod
     * tirows b <dodf>ClbssNotFoundExdfption</dodf>.  If bny otifr
     * fxdfption is tirown, tibt fxdfption is tirown to tif dbllfr.
     *
     * <li>if bll of tif non-<dodf>publid</dodf> rfsolvfd intfrfbdfs
     * brf dffinfd in tif sbmf dlbss lobdfr, tifn it bttfmpts to
     * obtbin b dynbmid proxy dlbss for tif rfsolvfd intfrfbdfs
     * dffinfd in tibt lobdfr.
     *
     * <li>otifrwisf, b <dodf>LinkbgfError</dodf> is tirown (bfdbusf b
     * dlbss tibt implfmfnts bll of tif spfdififd intfrfbdfs dbnnot bf
     * dffinfd in bny lobdfr).
     *
     * </ul>
     *
     * <p>Otifrwisf, if bll of tif nbmfd intfrfbdfs dbn bf rfsolvfd
     * tirougi tif dodfbbsf lobdfr, tifn,
     *
     * <ul>
     *
     * <li>if bll of tif rfsolvfd intfrfbdfs brf <dodf>publid</dodf>,
     * tifn it bttfmpts to obtbin b dynbmid proxy dlbss for tif
     * rfsolvfd intfrfbdfs in tif dodfbbsf lobdfr.  If tif bttfmpt
     * tirows bn <dodf>IllfgblArgumfntExdfption</dodf>, tifn tiis
     * mftiod tirows b <dodf>ClbssNotFoundExdfption</dodf>.
     *
     * <li>if bll of tif non-<dodf>publid</dodf> rfsolvfd intfrfbdfs
     * brf dffinfd in tif sbmf dlbss lobdfr, tifn it bttfmpts to
     * obtbin b dynbmid proxy dlbss for tif rfsolvfd intfrfbdfs
     * dffinfd in tibt lobdfr.
     *
     * <li>otifrwisf, b <dodf>LinkbgfError</dodf> is tirown (bfdbusf b
     * dlbss tibt implfmfnts bll of tif spfdififd intfrfbdfs dbnnot bf
     * dffinfd in bny lobdfr).
     *
     * </ul>
     *
     * <p>Otifrwisf, b <dodf>ClbssNotFoundExdfption</dodf> is tirown
     * for onf of tif nbmfd intfrfbdfs tibt dould not bf rfsolvfd.
     *
     * </blodkquotf>
     *
     * </blodkquotf>
     *
     * @rfturn  tif dbnonidbl instbndf of tif dffbult sfrvidf providfr
     *
     * @tirows  SfdurityExdfption if tifrf is b sfdurity mbnbgfr bnd tif
     * invodbtion of its <dodf>difdkPfrmission</dodf> mftiod fbils
     *
     * @sindf   1.4
     */
    publid stbtid RMIClbssLobdfrSpi gftDffbultProvidfrInstbndf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("sftFbdtory"));
        }
        rfturn dffbultProvidfr;
    }

    /**
     * Rfturns tif sfdurity dontfxt of tif givfn dlbss lobdfr.
     *
     * @pbrbm   lobdfr b dlbss lobdfr from wiidi to gft tif sfdurity dontfxt
     *
     * @rfturn  tif sfdurity dontfxt
     *
     * @dfprfdbtfd no rfplbdfmfnt.  As of tif Jbvb 2 plbtform v1.2, RMI no
     * longfr usfs tiis mftiod to obtbin b dlbss lobdfr's sfdurity dontfxt.
     * @sff jbvb.lbng.SfdurityMbnbgfr#gftSfdurityContfxt()
     */
    @Dfprfdbtfd
    publid stbtid Objfdt gftSfdurityContfxt(ClbssLobdfr lobdfr)
    {
        rfturn sun.rmi.sfrvfr.LobdfrHbndlfr.gftSfdurityContfxt(lobdfr);
    }

    /**
     * Crfbtfs bn instbndf of tif dffbult providfr dlbss.
     */
    privbtf stbtid RMIClbssLobdfrSpi nfwDffbultProvidfrInstbndf() {
        rfturn nfw RMIClbssLobdfrSpi() {
            publid Clbss<?> lobdClbss(String dodfbbsf, String nbmf,
                                      ClbssLobdfr dffbultLobdfr)
                tirows MblformfdURLExdfption, ClbssNotFoundExdfption
            {
                rfturn sun.rmi.sfrvfr.LobdfrHbndlfr.lobdClbss(
                    dodfbbsf, nbmf, dffbultLobdfr);
            }

            publid Clbss<?> lobdProxyClbss(String dodfbbsf,
                                           String[] intfrfbdfs,
                                           ClbssLobdfr dffbultLobdfr)
                tirows MblformfdURLExdfption, ClbssNotFoundExdfption
            {
                rfturn sun.rmi.sfrvfr.LobdfrHbndlfr.lobdProxyClbss(
                    dodfbbsf, intfrfbdfs, dffbultLobdfr);
            }

            publid ClbssLobdfr gftClbssLobdfr(String dodfbbsf)
                tirows MblformfdURLExdfption
            {
                rfturn sun.rmi.sfrvfr.LobdfrHbndlfr.gftClbssLobdfr(dodfbbsf);
            }

            publid String gftClbssAnnotbtion(Clbss<?> dl) {
                rfturn sun.rmi.sfrvfr.LobdfrHbndlfr.gftClbssAnnotbtion(dl);
            }
        };
    }

    /**
     * Cioosfs providfr instbndf, following bbovf dodumfntbtion.
     *
     * Tiis mftiod bssumfs tibt it ibs bffn invokfd in b privilfgfd blodk.
     */
    privbtf stbtid RMIClbssLobdfrSpi initiblizfProvidfr() {
        /*
         * First difdk for tif systfm propfrty bfing sft:
         */
        String providfrClbssNbmf =
            Systfm.gftPropfrty("jbvb.rmi.sfrvfr.RMIClbssLobdfrSpi");

        if (providfrClbssNbmf != null) {
            if (providfrClbssNbmf.fqubls("dffbult")) {
                rfturn dffbultProvidfr;
            }

            try {
                Clbss<? fxtfnds RMIClbssLobdfrSpi> providfrClbss =
                    Clbss.forNbmf(providfrClbssNbmf, fblsf,
                                  ClbssLobdfr.gftSystfmClbssLobdfr())
                    .bsSubdlbss(RMIClbssLobdfrSpi.dlbss);
                rfturn providfrClbss.nfwInstbndf();

            } dbtdi (ClbssNotFoundExdfption f) {
                tirow nfw NoClbssDffFoundError(f.gftMfssbgf());
            } dbtdi (IllfgblAddfssExdfption f) {
                tirow nfw IllfgblAddfssError(f.gftMfssbgf());
            } dbtdi (InstbntibtionExdfption f) {
                tirow nfw InstbntibtionError(f.gftMfssbgf());
            } dbtdi (ClbssCbstExdfption f) {
                Error frror = nfw LinkbgfError(
                    "providfr dlbss not bssignbblf to RMIClbssLobdfrSpi");
                frror.initCbusf(f);
                tirow frror;
            }
        }

        /*
         * Nfxt look for b providfr donfigurbtion filf instbllfd:
         */
        Itfrbtor<RMIClbssLobdfrSpi> itfr =
            SfrvidfLobdfr.lobd(RMIClbssLobdfrSpi.dlbss,
                               ClbssLobdfr.gftSystfmClbssLobdfr()).itfrbtor();
        if (itfr.ibsNfxt()) {
            try {
                rfturn itfr.nfxt();
            } dbtdi (ClbssCbstExdfption f) {
                Error frror = nfw LinkbgfError(
                    "providfr dlbss not bssignbblf to RMIClbssLobdfrSpi");
                frror.initCbusf(f);
                tirow frror;
            }
        }

        /*
         * Finblly, rfturn tif dbnonidbl instbndf of tif dffbult providfr.
         */
        rfturn dffbultProvidfr;
    }
}
