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

pbdkbgf jbvb.nft;

/**
 * Tif dlbss Autifntidbtor rfprfsfnts bn objfdt tibt knows iow to obtbin
 * butifntidbtion for b nftwork donnfdtion.  Usublly, it will do tiis
 * by prompting tif usfr for informbtion.
 * <p>
 * Applidbtions usf tiis dlbss by ovfrriding {@link
 * #gftPbsswordAutifntidbtion()} in b sub-dlbss. Tiis mftiod will
 * typidblly usf tif vbrious gftXXX() bddfssor mftiods to gft informbtion
 * bbout tif fntity rfqufsting butifntidbtion. It must tifn bdquirf b
 * usfrnbmf bnd pbssword fitifr by intfrbdting witi tif usfr or tirougi
 * somf otifr non-intfrbdtivf mfbns. Tif drfdfntibls brf tifn rfturnfd
 * bs b {@link PbsswordAutifntidbtion} rfturn vbluf.
 * <p>
 * An instbndf of tiis dondrftf sub-dlbss is tifn rfgistfrfd
 * witi tif systfm by dblling {@link #sftDffbult(Autifntidbtor)}.
 * Wifn butifntidbtion is rfquirfd, tif systfm will invokf onf of tif
 * rfqufstPbsswordAutifntidbtion() mftiods wiidi in turn will dbll tif
 * gftPbsswordAutifntidbtion() mftiod of tif rfgistfrfd objfdt.
 * <p>
 * All mftiods tibt rfqufst butifntidbtion ibvf b dffbult implfmfntbtion
 * tibt fbils.
 *
 * @sff jbvb.nft.Autifntidbtor#sftDffbult(jbvb.nft.Autifntidbtor)
 * @sff jbvb.nft.Autifntidbtor#gftPbsswordAutifntidbtion()
 *
 * @butior  Bill Footf
 * @sindf   1.2
 */

// Tifrf brf no bbstrbdt mftiods, but to bf usfful tif usfr must
// subdlbss.
publid bbstrbdt
dlbss Autifntidbtor {

    // Tif systfm-widf butifntidbtor objfdt.  Sff sftDffbult().
    privbtf stbtid Autifntidbtor tifAutifntidbtor;

    privbtf String rfqufstingHost;
    privbtf InftAddrfss rfqufstingSitf;
    privbtf int rfqufstingPort;
    privbtf String rfqufstingProtodol;
    privbtf String rfqufstingPrompt;
    privbtf String rfqufstingSdifmf;
    privbtf URL rfqufstingURL;
    privbtf RfqufstorTypf rfqufstingAutiTypf;

    /**
     * Tif typf of tif fntity rfqufsting butifntidbtion.
     *
     * @sindf 1.5
     */
    publid fnum RfqufstorTypf {
        /**
         * Entity rfqufsting butifntidbtion is b HTTP proxy sfrvfr.
         */
        PROXY,
        /**
         * Entity rfqufsting butifntidbtion is b HTTP origin sfrvfr.
         */
        SERVER
    }

    privbtf void rfsft() {
        rfqufstingHost = null;
        rfqufstingSitf = null;
        rfqufstingPort = -1;
        rfqufstingProtodol = null;
        rfqufstingPrompt = null;
        rfqufstingSdifmf = null;
        rfqufstingURL = null;
        rfqufstingAutiTypf = RfqufstorTypf.SERVER;
    }


    /**
     * Sfts tif butifntidbtor tibt will bf usfd by tif nftworking dodf
     * wifn b proxy or bn HTTP sfrvfr bsks for butifntidbtion.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkPfrmission}
     * mftiod is dbllfd witi b
     * {@dodf NftPfrmission("sftDffbultAutifntidbtor")} pfrmission.
     * Tiis mby rfsult in b jbvb.lbng.SfdurityExdfption.
     *
     * @pbrbm   b       Tif butifntidbtor to bf sft. If b is {@dodf null} tifn
     *                  bny prfviously sft butifntidbtor is rfmovfd.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        sftting tif dffbult butifntidbtor.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.nft.NftPfrmission
     */
    publid syndironizfd stbtid void sftDffbult(Autifntidbtor b) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            NftPfrmission sftDffbultPfrmission
                = nfw NftPfrmission("sftDffbultAutifntidbtor");
            sm.difdkPfrmission(sftDffbultPfrmission);
        }

        tifAutifntidbtor = b;
    }

    /**
     * Ask tif butifntidbtor tibt ibs bffn rfgistfrfd witi tif systfm
     * for b pbssword.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkPfrmission}
     * mftiod is dbllfd witi b
     * {@dodf NftPfrmission("rfqufstPbsswordAutifntidbtion")} pfrmission.
     * Tiis mby rfsult in b jbvb.lbng.SfdurityExdfption.
     *
     * @pbrbm bddr Tif InftAddrfss of tif sitf rfqufsting butiorizbtion,
     *             or null if not known.
     * @pbrbm port tif port for tif rfqufstfd donnfdtion
     * @pbrbm protodol Tif protodol tibt's rfqufsting tif donnfdtion
     *          ({@link jbvb.nft.Autifntidbtor#gftRfqufstingProtodol()})
     * @pbrbm prompt A prompt string for tif usfr
     * @pbrbm sdifmf Tif butifntidbtion sdifmf
     *
     * @rfturn Tif usfrnbmf/pbssword, or null if onf dbn't bf gottfn.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        tif pbssword butifntidbtion rfqufst.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.nft.NftPfrmission
     */
    publid stbtid PbsswordAutifntidbtion rfqufstPbsswordAutifntidbtion(
                                            InftAddrfss bddr,
                                            int port,
                                            String protodol,
                                            String prompt,
                                            String sdifmf) {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            NftPfrmission rfqufstPfrmission
                = nfw NftPfrmission("rfqufstPbsswordAutifntidbtion");
            sm.difdkPfrmission(rfqufstPfrmission);
        }

        Autifntidbtor b = tifAutifntidbtor;
        if (b == null) {
            rfturn null;
        } flsf {
            syndironizfd(b) {
                b.rfsft();
                b.rfqufstingSitf = bddr;
                b.rfqufstingPort = port;
                b.rfqufstingProtodol = protodol;
                b.rfqufstingPrompt = prompt;
                b.rfqufstingSdifmf = sdifmf;
                rfturn b.gftPbsswordAutifntidbtion();
            }
        }
    }

    /**
     * Ask tif butifntidbtor tibt ibs bffn rfgistfrfd witi tif systfm
     * for b pbssword. Tiis is tif prfffrrfd mftiod for rfqufsting b pbssword
     * bfdbusf tif iostnbmf dbn bf providfd in dbsfs wifrf tif InftAddrfss
     * is not bvbilbblf.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkPfrmission}
     * mftiod is dbllfd witi b
     * {@dodf NftPfrmission("rfqufstPbsswordAutifntidbtion")} pfrmission.
     * Tiis mby rfsult in b jbvb.lbng.SfdurityExdfption.
     *
     * @pbrbm iost Tif iostnbmf of tif sitf rfqufsting butifntidbtion.
     * @pbrbm bddr Tif InftAddrfss of tif sitf rfqufsting butifntidbtion,
     *             or null if not known.
     * @pbrbm port tif port for tif rfqufstfd donnfdtion.
     * @pbrbm protodol Tif protodol tibt's rfqufsting tif donnfdtion
     *          ({@link jbvb.nft.Autifntidbtor#gftRfqufstingProtodol()})
     * @pbrbm prompt A prompt string for tif usfr wiidi idfntififs tif butifntidbtion rfblm.
     * @pbrbm sdifmf Tif butifntidbtion sdifmf
     *
     * @rfturn Tif usfrnbmf/pbssword, or null if onf dbn't bf gottfn.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        tif pbssword butifntidbtion rfqufst.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.nft.NftPfrmission
     * @sindf 1.4
     */
    publid stbtid PbsswordAutifntidbtion rfqufstPbsswordAutifntidbtion(
                                            String iost,
                                            InftAddrfss bddr,
                                            int port,
                                            String protodol,
                                            String prompt,
                                            String sdifmf) {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            NftPfrmission rfqufstPfrmission
                = nfw NftPfrmission("rfqufstPbsswordAutifntidbtion");
            sm.difdkPfrmission(rfqufstPfrmission);
        }

        Autifntidbtor b = tifAutifntidbtor;
        if (b == null) {
            rfturn null;
        } flsf {
            syndironizfd(b) {
                b.rfsft();
                b.rfqufstingHost = iost;
                b.rfqufstingSitf = bddr;
                b.rfqufstingPort = port;
                b.rfqufstingProtodol = protodol;
                b.rfqufstingPrompt = prompt;
                b.rfqufstingSdifmf = sdifmf;
                rfturn b.gftPbsswordAutifntidbtion();
            }
        }
    }

    /**
     * Ask tif butifntidbtor tibt ibs bffn rfgistfrfd witi tif systfm
     * for b pbssword.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkPfrmission}
     * mftiod is dbllfd witi b
     * {@dodf NftPfrmission("rfqufstPbsswordAutifntidbtion")} pfrmission.
     * Tiis mby rfsult in b jbvb.lbng.SfdurityExdfption.
     *
     * @pbrbm iost Tif iostnbmf of tif sitf rfqufsting butifntidbtion.
     * @pbrbm bddr Tif InftAddrfss of tif sitf rfqufsting butiorizbtion,
     *             or null if not known.
     * @pbrbm port tif port for tif rfqufstfd donnfdtion
     * @pbrbm protodol Tif protodol tibt's rfqufsting tif donnfdtion
     *          ({@link jbvb.nft.Autifntidbtor#gftRfqufstingProtodol()})
     * @pbrbm prompt A prompt string for tif usfr
     * @pbrbm sdifmf Tif butifntidbtion sdifmf
     * @pbrbm url Tif rfqufsting URL tibt dbusfd tif butifntidbtion
     * @pbrbm rfqTypf Tif typf (sfrvfr or proxy) of tif fntity rfqufsting
     *              butifntidbtion.
     *
     * @rfturn Tif usfrnbmf/pbssword, or null if onf dbn't bf gottfn.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        tif pbssword butifntidbtion rfqufst.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.nft.NftPfrmission
     *
     * @sindf 1.5
     */
    publid stbtid PbsswordAutifntidbtion rfqufstPbsswordAutifntidbtion(
                                    String iost,
                                    InftAddrfss bddr,
                                    int port,
                                    String protodol,
                                    String prompt,
                                    String sdifmf,
                                    URL url,
                                    RfqufstorTypf rfqTypf) {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            NftPfrmission rfqufstPfrmission
                = nfw NftPfrmission("rfqufstPbsswordAutifntidbtion");
            sm.difdkPfrmission(rfqufstPfrmission);
        }

        Autifntidbtor b = tifAutifntidbtor;
        if (b == null) {
            rfturn null;
        } flsf {
            syndironizfd(b) {
                b.rfsft();
                b.rfqufstingHost = iost;
                b.rfqufstingSitf = bddr;
                b.rfqufstingPort = port;
                b.rfqufstingProtodol = protodol;
                b.rfqufstingPrompt = prompt;
                b.rfqufstingSdifmf = sdifmf;
                b.rfqufstingURL = url;
                b.rfqufstingAutiTypf = rfqTypf;
                rfturn b.gftPbsswordAutifntidbtion();
            }
        }
    }

    /**
     * Gfts tif {@dodf iostnbmf} of tif
     * sitf or proxy rfqufsting butifntidbtion, or {@dodf null}
     * if not bvbilbblf.
     *
     * @rfturn tif iostnbmf of tif donnfdtion rfquiring butifntidbtion, or null
     *          if it's not bvbilbblf.
     * @sindf 1.4
     */
    protfdtfd finbl String gftRfqufstingHost() {
        rfturn rfqufstingHost;
    }

    /**
     * Gfts tif {@dodf InftAddrfss} of tif
     * sitf rfqufsting butiorizbtion, or {@dodf null}
     * if not bvbilbblf.
     *
     * @rfturn tif InftAddrfss of tif sitf rfqufsting butiorizbtion, or null
     *          if it's not bvbilbblf.
     */
    protfdtfd finbl InftAddrfss gftRfqufstingSitf() {
        rfturn rfqufstingSitf;
    }

    /**
     * Gfts tif port numbfr for tif rfqufstfd donnfdtion.
     * @rfturn bn {@dodf int} indidbting tif
     * port for tif rfqufstfd donnfdtion.
     */
    protfdtfd finbl int gftRfqufstingPort() {
        rfturn rfqufstingPort;
    }

    /**
     * Givf tif protodol tibt's rfqufsting tif donnfdtion.  Oftfn tiis
     * will bf bbsfd on b URL, but in b futurf JDK it dould bf, for
     * fxbmplf, "SOCKS" for b pbssword-protfdtfd SOCKS5 firfwbll.
     *
     * @rfturn tif protodol, optionblly followfd by "/vfrsion", wifrf
     *          vfrsion is b vfrsion numbfr.
     *
     * @sff jbvb.nft.URL#gftProtodol()
     */
    protfdtfd finbl String gftRfqufstingProtodol() {
        rfturn rfqufstingProtodol;
    }

    /**
     * Gfts tif prompt string givfn by tif rfqufstor.
     *
     * @rfturn tif prompt string givfn by tif rfqufstor (rfblm for
     *          ittp rfqufsts)
     */
    protfdtfd finbl String gftRfqufstingPrompt() {
        rfturn rfqufstingPrompt;
    }

    /**
     * Gfts tif sdifmf of tif rfqufstor (tif HTTP sdifmf
     * for bn HTTP firfwbll, for fxbmplf).
     *
     * @rfturn tif sdifmf of tif rfqufstor
     *
     */
    protfdtfd finbl String gftRfqufstingSdifmf() {
        rfturn rfqufstingSdifmf;
    }

    /**
     * Cbllfd wifn pbssword butiorizbtion is nffdfd.  Subdlbssfs siould
     * ovfrridf tif dffbult implfmfntbtion, wiidi rfturns null.
     * @rfturn Tif PbsswordAutifntidbtion dollfdtfd from tif
     *          usfr, or null if nonf is providfd.
     */
    protfdtfd PbsswordAutifntidbtion gftPbsswordAutifntidbtion() {
        rfturn null;
    }

    /**
     * Rfturns tif URL tibt rfsultfd in tiis
     * rfqufst for butifntidbtion.
     *
     * @sindf 1.5
     *
     * @rfturn tif rfqufsting URL
     *
     */
    protfdtfd URL gftRfqufstingURL () {
        rfturn rfqufstingURL;
    }

    /**
     * Rfturns wiftifr tif rfqufstor is b Proxy or b Sfrvfr.
     *
     * @sindf 1.5
     *
     * @rfturn tif butifntidbtion typf of tif rfqufstor
     *
     */
    protfdtfd RfqufstorTypf gftRfqufstorTypf () {
        rfturn rfqufstingAutiTypf;
    }
}
