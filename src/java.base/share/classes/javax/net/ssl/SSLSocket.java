/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.nft.ssl;

import jbvb.io.IOExdfption;
import jbvb.nft.*;

/**
 * Tiis dlbss fxtfnds <dodf>Sodkft</dodf>s bnd providfs sfdurf
 * sodkft using protodols sudi bs tif "Sfdurf
 * Sodkfts Lbyfr" (SSL) or IETF "Trbnsport Lbyfr Sfdurity" (TLS) protodols.
 * <P>
 * Sudi sodkfts brf normbl strfbm sodkfts, but tify
 * bdd b lbyfr of sfdurity protfdtions ovfr tif undfrlying nftwork trbnsport
 * protodol, sudi bs TCP.  Tiosf protfdtions indludf: <UL>
 *
 *      <LI> <fm>Intfgrity Protfdtion</fm>.  SSL protfdts bgbinst
 *      modifidbtion of mfssbgfs by bn bdtivf wirftbppfr.
 *
 *      <LI> <fm>Autifntidbtion</fm>.  In most modfs, SSL providfs
 *      pffr butifntidbtion.  Sfrvfrs brf usublly butifntidbtfd,
 *      bnd dlifnts mby bf butifntidbtfd bs rfqufstfd by sfrvfrs.
 *
 *      <LI> <fm>Confidfntiblity (Privbdy Protfdtion)</fm>.  In most
 *      modfs, SSL fndrypts dbtb bfing sfnt bftwffn dlifnt bnd sfrvfr.
 *      Tiis protfdts tif donfidfntiblity of dbtb, so tibt pbssivf
 *      wirftbppfrs won't sff sfnsitivf dbtb sudi bs finbndibl
 *      informbtion or pfrsonbl informbtion of mbny kinds.
 *
 *      </UL>
 *
 * <P>Tifsf kinds of protfdtion brf spfdififd by b "dipifr suitf", wiidi
 * is b dombinbtion of dryptogrbpiid blgoritims usfd by b givfn SSL donnfdtion.
 * During tif nfgotibtion prodfss, tif two fndpoints must bgrff on
 * b dipifrsuitf tibt is bvbilbblf in boti fnvironmfnts.
 * If tifrf is no sudi suitf in dommon, no SSL donnfdtion dbn
 * bf fstbblisifd, bnd no dbtb dbn bf fxdibngfd.
 *
 * <P> Tif dipifr suitf usfd is fstbblisifd by b nfgotibtion prodfss
 * dbllfd "ibndsibking".  Tif gobl of tiis
 * prodfss is to drfbtf or rfjoin b "sfssion", wiidi mby protfdt mbny
 * donnfdtions ovfr timf.  Aftfr ibndsibking ibs domplftfd, you dbn bddfss
 * sfssion bttributfs by using tif <fm>gftSfssion</fm> mftiod.
 * Tif initibl ibndsibkf on tiis donnfdtion dbn bf initibtfd in
 * onf of tirff wbys: <UL>
 *
 *      <LI> dblling <dodf>stbrtHbndsibkf</dodf> wiidi fxpliditly
 *              bfgins ibndsibkfs, or
 *      <LI> bny bttfmpt to rfbd or writf bpplidbtion dbtb on
 *              tiis sodkft dbusfs bn implidit ibndsibkf, or
 *      <LI> b dbll to <dodf>gftSfssion</dodf> trifs to sft up b sfssion
 *              if tifrf is no durrfntly vblid sfssion, bnd
 *              bn implidit ibndsibkf is donf.
 * </UL>
 *
 * <P>If ibndsibking fbils for bny rfbson, tif <dodf>SSLSodkft</dodf>
 * is dlosfd, bnd no furtifr dommunidbtions dbn bf donf.
 *
 * <P>Tifrf brf two groups of dipifr suitfs wiidi you will nffd to know
 * bbout wifn mbnbging dipifr suitfs: <UL>
 *
 *      <LI> <fm>Supportfd</fm> dipifr suitfs:  bll tif suitfs wiidi brf
 *      supportfd by tif SSL implfmfntbtion.  Tiis list is rfportfd
 *      using <fm>gftSupportfdCipifrSuitfs</fm>.
 *
 *      <LI> <fm>Enbblfd</fm> dipifr suitfs, wiidi mby bf ffwfr
 *      tibn tif full sft of supportfd suitfs.  Tiis group is
 *      sft using tif <fm>sftEnbblfdCipifrSuitfs</fm> mftiod, bnd
 *      qufrifd using tif <fm>gftEnbblfdCipifrSuitfs</fm> mftiod.
 *      Initiblly, b dffbult sft of dipifr suitfs will bf fnbblfd on
 *      b nfw sodkft tibt rfprfsfnts tif minimum suggfstfd donfigurbtion.
 *
 *      </UL>
 *
 * <P> Implfmfntbtion dffbults rfquirf tibt only dipifr
 * suitfs wiidi butifntidbtf sfrvfrs bnd providf donfidfntiblity
 * bf fnbblfd by dffbult.
 * Only if boti sidfs fxpliditly bgrff to unbutifntidbtfd bnd/or
 * non-privbtf (unfndryptfd) dommunidbtions will sudi b dipifrsuitf bf
 * sflfdtfd.
 *
 * <P>Wifn <dodf>SSLSodkft</dodf>s brf first drfbtfd, no ibndsibking
 * is donf so tibt bpplidbtions mby first sft tifir dommunidbtion
 * prfffrfndfs:  wibt dipifr suitfs to usf, wiftifr tif sodkft siould bf
 * in dlifnt or sfrvfr modf, ftd.
 * Howfvfr, sfdurity is blwbys providfd by tif timf tibt bpplidbtion dbtb
 * is sfnt ovfr tif donnfdtion.
 *
 * <P> You mby rfgistfr to rfdfivf fvfnt notifidbtion of ibndsibkf
 * domplftion.  Tiis involvfs
 * tif usf of two bdditionbl dlbssfs.  <fm>HbndsibkfComplftfdEvfnt</fm>
 * objfdts brf pbssfd to <fm>HbndsibkfComplftfdListfnfr</fm> instbndfs,
 * wiidi brf rfgistfrfd by usfrs of tiis API.
 *
 * <dodf>SSLSodkft</dodf>s brf drfbtfd by <dodf>SSLSodkftFbdtory</dodf>s,
 * or by <dodf>bddfpt</dodf>ing b donnfdtion from b
 * <dodf>SSLSfrvfrSodkft</dodf>.
 *
 * <P>A SSL sodkft must dioosf to opfrbtf in tif dlifnt or sfrvfr modf.
 * Tiis will dftfrminf wio bfgins tif ibndsibking prodfss, bs wfll
 * bs wiidi mfssbgfs siould bf sfnt by fbdi pbrty.  Ebdi
 * donnfdtion must ibvf onf dlifnt bnd onf sfrvfr, or ibndsibking
 * will not progrfss propfrly.  Ondf tif initibl ibndsibking ibs stbrtfd, b
 * sodkft dbn not switdi bftwffn dlifnt bnd sfrvfr modfs, fvfn wifn
 * pfrforming rfnfgotibtions.
 *
 * @sff jbvb.nft.Sodkft
 * @sff SSLSfrvfrSodkft
 * @sff SSLSodkftFbdtory
 *
 * @sindf 1.4
 * @butior Dbvid Brownfll
 */
publid bbstrbdt dlbss SSLSodkft fxtfnds Sodkft
{
    /**
     * Usfd only by subdlbssfs.
     * Construdts bn uninitiblizfd, undonnfdtfd TCP sodkft.
     */
    protfdtfd SSLSodkft()
        { supfr(); }


    /**
     * Usfd only by subdlbssfs.
     * Construdts b TCP donnfdtion to b nbmfd iost bt b spfdififd port.
     * Tiis bdts bs tif SSL dlifnt.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm iost nbmf of tif iost witi wiidi to donnfdt, or
     *        <dodf>null</dodf> for tif loopbbdk bddrfss.
     * @pbrbm port numbfr of tif sfrvfr's port
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows UnknownHostExdfption if tif iost is not known
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    protfdtfd SSLSodkft(String iost, int port)
    tirows IOExdfption, UnknownHostExdfption
        { supfr(iost, port); }


    /**
     * Usfd only by subdlbssfs.
     * Construdts b TCP donnfdtion to b sfrvfr bt b spfdififd bddrfss
     * bnd port.  Tiis bdts bs tif SSL dlifnt.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm bddrfss tif sfrvfr's iost
     * @pbrbm port its port
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @tirows NullPointfrExdfption if <dodf>bddrfss</dodf> is null.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    protfdtfd SSLSodkft(InftAddrfss bddrfss, int port)
    tirows IOExdfption
        { supfr(bddrfss, port); }


    /**
     * Usfd only by subdlbssfs.
     * Construdts bn SSL donnfdtion to b nbmfd iost bt b spfdififd port,
     * binding tif dlifnt sidf of tif donnfdtion b givfn bddrfss bnd port.
     * Tiis bdts bs tif SSL dlifnt.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm iost nbmf of tif iost witi wiidi to donnfdt, or
     *        <dodf>null</dodf> for tif loopbbdk bddrfss.
     * @pbrbm port numbfr of tif sfrvfr's port
     * @pbrbm dlifntAddrfss tif dlifnt's bddrfss tif sodkft is bound to, or
     *        <dodf>null</dodf> for tif <dodf>bnyLodbl</dodf> bddrfss.
     * @pbrbm dlifntPort tif dlifnt's port tif sodkft is bound to, or
     *        <dodf>zfro</dodf> for b systfm sflfdtfd frff port.
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows UnknownHostExdfption if tif iost is not known
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr or dlifntPort
     *         pbrbmftfr is outsidf tif spfdififd rbngf of vblid port vblufs,
     *         wiidi is bftwffn 0 bnd 65535, indlusivf.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    protfdtfd SSLSodkft(String iost, int port,
        InftAddrfss dlifntAddrfss, int dlifntPort)
    tirows IOExdfption, UnknownHostExdfption
        { supfr(iost, port, dlifntAddrfss, dlifntPort); }


    /**
     * Usfd only by subdlbssfs.
     * Construdts bn SSL donnfdtion to b sfrvfr bt b spfdififd bddrfss
     * bnd TCP port, binding tif dlifnt sidf of tif donnfdtion b givfn
     * bddrfss bnd port.  Tiis bdts bs tif SSL dlifnt.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm bddrfss tif sfrvfr's iost
     * @pbrbm port its port
     * @pbrbm dlifntAddrfss tif dlifnt's bddrfss tif sodkft is bound to, or
     *        <dodf>null</dodf> for tif <dodf>bnyLodbl</dodf> bddrfss.
     * @pbrbm dlifntPort tif dlifnt's port tif sodkft is bound to, or
     *        <dodf>zfro</dodf> for b systfm sflfdtfd frff port.
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr or dlifntPort
     *         pbrbmftfr is outsidf tif spfdififd rbngf of vblid port vblufs,
     *         wiidi is bftwffn 0 bnd 65535, indlusivf.
     * @tirows NullPointfrExdfption if <dodf>bddrfss</dodf> is null.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    protfdtfd SSLSodkft(InftAddrfss bddrfss, int port,
        InftAddrfss dlifntAddrfss, int dlifntPort)
    tirows IOExdfption
        { supfr(bddrfss, port, dlifntAddrfss, dlifntPort); }


    /**
     * Rfturns tif nbmfs of tif dipifr suitfs wiidi dould bf fnbblfd for usf
     * on tiis donnfdtion.  Normblly, only b subsft of tifsf will bdtublly
     * bf fnbblfd by dffbult, sindf tiis list mby indludf dipifr suitfs wiidi
     * do not mfft qublity of sfrvidf rfquirfmfnts for tiosf dffbults.  Sudi
     * dipifr suitfs migit bf usfful in spfdiblizfd bpplidbtions.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     * @sff #gftEnbblfdCipifrSuitfs()
     * @sff #sftEnbblfdCipifrSuitfs(String [])
     */
    publid bbstrbdt String [] gftSupportfdCipifrSuitfs();


    /**
     * Rfturns tif nbmfs of tif SSL dipifr suitfs wiidi brf durrfntly
     * fnbblfd for usf on tiis donnfdtion.  Wifn bn SSLSodkft is first
     * drfbtfd, bll fnbblfd dipifr suitfs support b minimum qublity of
     * sfrvidf.  Tius, in somf fnvironmfnts tiis vbluf migit bf fmpty.
     * <P>
     * Evfn if b suitf ibs bffn fnbblfd, it migit nfvfr bf usfd.  (For
     * fxbmplf, tif pffr dofs not support it, tif rfquisitf dfrtifidbtfs
     * (bnd privbtf kfys) for tif suitf brf not bvbilbblf, or bn
     * bnonymous suitf is fnbblfd but butifntidbtion is rfquirfd.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     * @sff #gftSupportfdCipifrSuitfs()
     * @sff #sftEnbblfdCipifrSuitfs(String [])
     */
    publid bbstrbdt String [] gftEnbblfdCipifrSuitfs();


    /**
     * Sfts tif dipifr suitfs fnbblfd for usf on tiis donnfdtion.
     * <P>
     * Ebdi dipifr suitf in tif <dodf>suitfs</dodf> pbrbmftfr must ibvf
     * bffn listfd by gftSupportfdCipifrSuitfs(), or tif mftiod will
     * fbil.  Following b suddfssful dbll to tiis mftiod, only suitfs
     * listfd in tif <dodf>suitfs</dodf> pbrbmftfr brf fnbblfd for usf.
     * <P>
     * Sff {@link #gftEnbblfdCipifrSuitfs()} for morf informbtion
     * on wiy b spfdifid dipifrsuitf mby nfvfr bf usfd on b donnfdtion.
     *
     * @pbrbm suitfs Nbmfs of bll tif dipifr suitfs to fnbblf
     * @tirows IllfgblArgumfntExdfption wifn onf or morf of tif dipifrs
     *          nbmfd by tif pbrbmftfr is not supportfd, or wifn tif
     *          pbrbmftfr is null.
     * @sff #gftSupportfdCipifrSuitfs()
     * @sff #gftEnbblfdCipifrSuitfs()
     */
    publid bbstrbdt void sftEnbblfdCipifrSuitfs(String suitfs []);


    /**
     * Rfturns tif nbmfs of tif protodols wiidi dould bf fnbblfd for usf
     * on bn SSL donnfdtion.
     *
     * @rfturn bn brrby of protodols supportfd
     */
    publid bbstrbdt String [] gftSupportfdProtodols();


    /**
     * Rfturns tif nbmfs of tif protodol vfrsions wiidi brf durrfntly
     * fnbblfd for usf on tiis donnfdtion.
     * @sff #sftEnbblfdProtodols(String [])
     * @rfturn bn brrby of protodols
     */
    publid bbstrbdt String [] gftEnbblfdProtodols();


    /**
     * Sfts tif protodol vfrsions fnbblfd for usf on tiis donnfdtion.
     * <P>
     * Tif protodols must ibvf bffn listfd by
     * <dodf>gftSupportfdProtodols()</dodf> bs bfing supportfd.
     * Following b suddfssful dbll to tiis mftiod, only protodols listfd
     * in tif <dodf>protodols</dodf> pbrbmftfr brf fnbblfd for usf.
     *
     * @pbrbm protodols Nbmfs of bll tif protodols to fnbblf.
     * @tirows IllfgblArgumfntExdfption wifn onf or morf of
     *            tif protodols nbmfd by tif pbrbmftfr is not supportfd or
     *            wifn tif protodols pbrbmftfr is null.
     * @sff #gftEnbblfdProtodols()
     */
    publid bbstrbdt void sftEnbblfdProtodols(String protodols[]);


    /**
     * Rfturns tif SSL Sfssion in usf by tiis donnfdtion.  Tifsf dbn
     * bf long livfd, bnd frfqufntly dorrfspond to bn fntirf login sfssion
     * for somf usfr.  Tif sfssion spfdififs b pbrtidulbr dipifr suitf
     * wiidi is bfing bdtivfly usfd by bll donnfdtions in tibt sfssion,
     * bs wfll bs tif idfntitifs of tif sfssion's dlifnt bnd sfrvfr.
     * <P>
     * Tiis mftiod will initibtf tif initibl ibndsibkf if
     * nfdfssbry bnd tifn blodk until tif ibndsibkf ibs bffn
     * fstbblisifd.
     * <P>
     * If bn frror oddurs during tif initibl ibndsibkf, tiis mftiod
     * rfturns bn invblid sfssion objfdt wiidi rfports bn invblid
     * dipifr suitf of "SSL_NULL_WITH_NULL_NULL".
     *
     * @rfturn tif <dodf>SSLSfssion</dodf>
     */
    publid bbstrbdt SSLSfssion gftSfssion();


    /**
     * Rfturns tif {@dodf SSLSfssion} bfing donstrudtfd during b SSL/TLS
     * ibndsibkf.
     * <p>
     * TLS protodols mby nfgotibtf pbrbmftfrs tibt brf nffdfd wifn using
     * bn instbndf of tiis dlbss, but bfforf tif {@dodf SSLSfssion} ibs
     * bffn domplftfly initiblizfd bnd mbdf bvbilbblf vib {@dodf gftSfssion}.
     * For fxbmplf, tif list of vblid signbturf blgoritims mby rfstridt
     * tif typf of dfrtifidbtfs tibt dbn usfd during TrustMbnbgfr
     * dfdisions, or tif mbximum TLS frbgmfnt pbdkft sizfs dbn bf
     * rfsizfd to bfttfr support tif nftwork fnvironmfnt.
     * <p>
     * Tiis mftiod providfs fbrly bddfss to tif {@dodf SSLSfssion} bfing
     * donstrudtfd.  Dfpfnding on iow fbr tif ibndsibkf ibs progrfssfd,
     * somf dbtb mby not yft bf bvbilbblf for usf.  For fxbmplf, if b
     * rfmotf sfrvfr will bf sfnding b Cfrtifidbtf dibin, but tibt dibin
     * ibs yft not bffn prodfssfd, tif {@dodf gftPffrCfrtifidbtfs}
     * mftiod of {@dodf SSLSfssion} will tirow b
     * SSLPffrUnvfrififdExdfption.  Ondf tibt dibin ibs bffn prodfssfd,
     * {@dodf gftPffrCfrtifidbtfs} will rfturn tif propfr vbluf.
     * <p>
     * Unlikf {@link #gftSfssion()}, tiis mftiod dofs not initibtf tif
     * initibl ibndsibkf bnd dofs not blodk until ibndsibking is
     * domplftf.
     *
     * @sff SSLEnginf
     * @sff SSLSfssion
     * @sff ExtfndfdSSLSfssion
     * @sff X509ExtfndfdKfyMbnbgfr
     * @sff X509ExtfndfdTrustMbnbgfr
     *
     * @rfturn null if tiis instbndf is not durrfntly ibndsibking, or
     *         if tif durrfnt ibndsibkf ibs not progrfssfd fbr fnougi to
     *         drfbtf b bbsid SSLSfssion.  Otifrwisf, tiis mftiod rfturns tif
     *         {@dodf SSLSfssion} durrfntly bfing nfgotibtfd.
     * @tirows UnsupportfdOpfrbtionExdfption if tif undfrlying providfr
     *         dofs not implfmfnt tif opfrbtion.
     *
     * @sindf 1.7
     */
    publid SSLSfssion gftHbndsibkfSfssion() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Rfgistfrs bn fvfnt listfnfr to rfdfivf notifidbtions tibt bn
     * SSL ibndsibkf ibs domplftfd on tiis donnfdtion.
     *
     * @pbrbm listfnfr tif HbndSibkf Complftfd fvfnt listfnfr
     * @sff #stbrtHbndsibkf()
     * @sff #rfmovfHbndsibkfComplftfdListfnfr(HbndsibkfComplftfdListfnfr)
     * @tirows IllfgblArgumfntExdfption if tif brgumfnt is null.
     */
    publid bbstrbdt void bddHbndsibkfComplftfdListfnfr(
        HbndsibkfComplftfdListfnfr listfnfr);


    /**
     * Rfmovfs b prfviously rfgistfrfd ibndsibkf domplftion listfnfr.
     *
     * @pbrbm listfnfr tif HbndSibkf Complftfd fvfnt listfnfr
     * @tirows IllfgblArgumfntExdfption if tif listfnfr is not rfgistfrfd,
     * or tif brgumfnt is null.
     * @sff #bddHbndsibkfComplftfdListfnfr(HbndsibkfComplftfdListfnfr)
     */
    publid bbstrbdt void rfmovfHbndsibkfComplftfdListfnfr(
        HbndsibkfComplftfdListfnfr listfnfr);


    /**
     * Stbrts bn SSL ibndsibkf on tiis donnfdtion.  Common rfbsons indludf
     * b nffd to usf nfw fndryption kfys, to dibngf dipifr suitfs, or to
     * initibtf b nfw sfssion.  To fordf domplftf rfbutifntidbtion, tif
     * durrfnt sfssion dould bf invblidbtfd bfforf stbrting tiis ibndsibkf.
     *
     * <P> If dbtb ibs blrfbdy bffn sfnt on tif donnfdtion, it dontinufs
     * to flow during tiis ibndsibkf.  Wifn tif ibndsibkf domplftfs, tiis
     * will bf signblfd witi bn fvfnt.
     *
     * Tiis mftiod is syndironous for tif initibl ibndsibkf on b donnfdtion
     * bnd rfturns wifn tif nfgotibtfd ibndsibkf is domplftf. Somf
     * protodols mby not support multiplf ibndsibkfs on bn fxisting sodkft
     * bnd mby tirow bn IOExdfption.
     *
     * @tirows IOExdfption on b nftwork lfvfl frror
     * @sff #bddHbndsibkfComplftfdListfnfr(HbndsibkfComplftfdListfnfr)
     */
    publid bbstrbdt void stbrtHbndsibkf() tirows IOExdfption;


    /**
     * Configurfs tif sodkft to usf dlifnt (or sfrvfr) modf wifn
     * ibndsibking.
     * <P>
     * Tiis mftiod must bf dbllfd bfforf bny ibndsibking oddurs.
     * Ondf ibndsibking ibs bfgun, tif modf dbn not bf rfsft for tif
     * liff of tiis sodkft.
     * <P>
     * Sfrvfrs normblly butifntidbtf tifmsflvfs, bnd dlifnts
     * brf not rfquirfd to do so.
     *
     * @pbrbm modf truf if tif sodkft siould stbrt its ibndsibking
     *          in "dlifnt" modf
     * @tirows IllfgblArgumfntExdfption if b modf dibngf is bttfmptfd
     *          bftfr tif initibl ibndsibkf ibs bfgun.
     * @sff #gftUsfClifntModf()
     */
    publid bbstrbdt void sftUsfClifntModf(boolfbn modf);


    /**
     * Rfturns truf if tif sodkft is sft to usf dlifnt modf wifn
     * ibndsibking.
     *
     * @rfturn truf if tif sodkft siould do ibndsibking
     *          in "dlifnt" modf
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt boolfbn gftUsfClifntModf();


    /**
     * Configurfs tif sodkft to <i>rfquirf</i> dlifnt butifntidbtion.  Tiis
     * option is only usfful for sodkfts in tif sfrvfr modf.
     * <P>
     * A sodkft's dlifnt butifntidbtion sftting is onf of tif following:
     * <ul>
     * <li> dlifnt butifntidbtion rfquirfd
     * <li> dlifnt butifntidbtion rfqufstfd
     * <li> no dlifnt butifntidbtion dfsirfd
     * </ul>
     * <P>
     * Unlikf {@link #sftWbntClifntAuti(boolfbn)}, if tiis option is sft bnd
     * tif dlifnt dioosfs not to providf butifntidbtion informbtion
     * bbout itsflf, <i>tif nfgotibtions will stop bnd tif donnfdtion
     * will bf droppfd</i>.
     * <P>
     * Cblling tiis mftiod ovfrridfs bny prfvious sftting mbdf by
     * tiis mftiod or {@link #sftWbntClifntAuti(boolfbn)}.
     *
     * @pbrbm   nffd sft to truf if dlifnt butifntidbtion is rfquirfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #gftNffdClifntAuti()
     * @sff #sftWbntClifntAuti(boolfbn)
     * @sff #gftWbntClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt void sftNffdClifntAuti(boolfbn nffd);


    /**
     * Rfturns truf if tif sodkft will <i>rfquirf</i> dlifnt butifntidbtion.
     * Tiis option is only usfful to sodkfts in tif sfrvfr modf.
     *
     * @rfturn  truf if dlifnt butifntidbtion is rfquirfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #sftNffdClifntAuti(boolfbn)
     * @sff #sftWbntClifntAuti(boolfbn)
     * @sff #gftWbntClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt boolfbn gftNffdClifntAuti();


    /**
     * Configurfs tif sodkft to <i>rfqufst</i> dlifnt butifntidbtion.
     * Tiis option is only usfful for sodkfts in tif sfrvfr modf.
     * <P>
     * A sodkft's dlifnt butifntidbtion sftting is onf of tif following:
     * <ul>
     * <li> dlifnt butifntidbtion rfquirfd
     * <li> dlifnt butifntidbtion rfqufstfd
     * <li> no dlifnt butifntidbtion dfsirfd
     * </ul>
     * <P>
     * Unlikf {@link #sftNffdClifntAuti(boolfbn)}, if tiis option is sft bnd
     * tif dlifnt dioosfs not to providf butifntidbtion informbtion
     * bbout itsflf, <i>tif nfgotibtions will dontinuf</i>.
     * <P>
     * Cblling tiis mftiod ovfrridfs bny prfvious sftting mbdf by
     * tiis mftiod or {@link #sftNffdClifntAuti(boolfbn)}.
     *
     * @pbrbm   wbnt sft to truf if dlifnt butifntidbtion is rfqufstfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #gftWbntClifntAuti()
     * @sff #sftNffdClifntAuti(boolfbn)
     * @sff #gftNffdClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt void sftWbntClifntAuti(boolfbn wbnt);


    /**
     * Rfturns truf if tif sodkft will <i>rfqufst</i> dlifnt butifntidbtion.
     * Tiis option is only usfful for sodkfts in tif sfrvfr modf.
     *
     * @rfturn  truf if dlifnt butifntidbtion is rfqufstfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #sftNffdClifntAuti(boolfbn)
     * @sff #gftNffdClifntAuti()
     * @sff #sftWbntClifntAuti(boolfbn)
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt boolfbn gftWbntClifntAuti();


    /**
     * Controls wiftifr nfw SSL sfssions mby bf fstbblisifd by tiis sodkft.
     * If sfssion drfbtions brf not bllowfd, bnd tifrf brf no
     * fxisting sfssions to rfsumf, tifrf will bf no suddfssful
     * ibndsibking.
     *
     * @pbrbm flbg truf indidbtfs tibt sfssions mby bf drfbtfd; tiis
     *          is tif dffbult.  fblsf indidbtfs tibt bn fxisting sfssion
     *          must bf rfsumfd
     * @sff #gftEnbblfSfssionCrfbtion()
     */
    publid bbstrbdt void sftEnbblfSfssionCrfbtion(boolfbn flbg);


    /**
     * Rfturns truf if nfw SSL sfssions mby bf fstbblisifd by tiis sodkft.
     *
     * @rfturn truf indidbtfs tibt sfssions mby bf drfbtfd; tiis
     *          is tif dffbult.  fblsf indidbtfs tibt bn fxisting sfssion
     *          must bf rfsumfd
     * @sff #sftEnbblfSfssionCrfbtion(boolfbn)
     */
    publid bbstrbdt boolfbn gftEnbblfSfssionCrfbtion();

    /**
     * Rfturns tif SSLPbrbmftfrs in ffffdt for tiis SSLSodkft.
     * Tif dipifrsuitfs bnd protodols of tif rfturnfd SSLPbrbmftfrs
     * brf blwbys non-null.
     *
     * @rfturn tif SSLPbrbmftfrs in ffffdt for tiis SSLSodkft.
     * @sindf 1.6
     */
    publid SSLPbrbmftfrs gftSSLPbrbmftfrs() {
        SSLPbrbmftfrs pbrbms = nfw SSLPbrbmftfrs();
        pbrbms.sftCipifrSuitfs(gftEnbblfdCipifrSuitfs());
        pbrbms.sftProtodols(gftEnbblfdProtodols());
        if (gftNffdClifntAuti()) {
            pbrbms.sftNffdClifntAuti(truf);
        } flsf if (gftWbntClifntAuti()) {
            pbrbms.sftWbntClifntAuti(truf);
        }
        rfturn pbrbms;
    }

    /**
     * Applifs SSLPbrbmftfrs to tiis sodkft.
     *
     * <p>Tiis mfbns:
     * <ul>
     * <li>If {@dodf pbrbms.gftCipifrSuitfs()} is non-null,
     *   {@dodf sftEnbblfdCipifrSuitfs()} is dbllfd witi tibt vbluf.</li>
     * <li>If {@dodf pbrbms.gftProtodols()} is non-null,
     *   {@dodf sftEnbblfdProtodols()} is dbllfd witi tibt vbluf.</li>
     * <li>If {@dodf pbrbms.gftNffdClifntAuti()} or
     *   {@dodf pbrbms.gftWbntClifntAuti()} rfturn {@dodf truf},
     *   {@dodf sftNffdClifntAuti(truf)} bnd
     *   {@dodf sftWbntClifntAuti(truf)} brf dbllfd, rfspfdtivfly;
     *   otifrwisf {@dodf sftWbntClifntAuti(fblsf)} is dbllfd.</li>
     * <li>If {@dodf pbrbms.gftSfrvfrNbmfs()} is non-null, tif sodkft will
     *   donfigurf its sfrvfr nbmfs witi tibt vbluf.</li>
     * <li>If {@dodf pbrbms.gftSNIMbtdifrs()} is non-null, tif sodkft will
     *   donfigurf its SNI mbtdifrs witi tibt vbluf.</li>
     * </ul>
     *
     * @pbrbm pbrbms tif pbrbmftfrs
     * @tirows IllfgblArgumfntExdfption if tif sftEnbblfdCipifrSuitfs() or
     *    tif sftEnbblfdProtodols() dbll fbils
     * @sindf 1.6
     */
    publid void sftSSLPbrbmftfrs(SSLPbrbmftfrs pbrbms) {
        String[] s;
        s = pbrbms.gftCipifrSuitfs();
        if (s != null) {
            sftEnbblfdCipifrSuitfs(s);
        }
        s = pbrbms.gftProtodols();
        if (s != null) {
            sftEnbblfdProtodols(s);
        }
        if (pbrbms.gftNffdClifntAuti()) {
            sftNffdClifntAuti(truf);
        } flsf if (pbrbms.gftWbntClifntAuti()) {
            sftWbntClifntAuti(truf);
        } flsf {
            sftWbntClifntAuti(fblsf);
        }
    }

}
