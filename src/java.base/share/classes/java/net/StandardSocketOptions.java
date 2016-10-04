/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Dffinfs tif <fm>stbndbrd</fm> sodkft options.
 *
 * <p> Tif {@link SodkftOption#nbmf nbmf} of fbdi sodkft option dffinfd by tiis
 * dlbss is its fifld nbmf.
 *
 * <p> In tiis rflfbsf, tif sodkft options dffinfd ifrf brf usfd by {@link
 * jbvb.nio.dibnnfls.NftworkCibnnfl nftwork} dibnnfls in tif {@link
 * jbvb.nio.dibnnfls dibnnfls} pbdkbgf.
 *
 * @sindf 1.7
 */

publid finbl dlbss StbndbrdSodkftOptions {
    privbtf StbndbrdSodkftOptions() { }

    // -- SOL_SOCKET --

    /**
     * Allow trbnsmission of brobddbst dbtbgrbms.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. Tif option is spfdifid to
     * dbtbgrbm-orifntfd sodkfts sfnding to {@link jbvb.nft.Inft4Addrfss IPv4}
     * brobddbst bddrfssfs. Wifn tif sodkft option is fnbblfd tifn tif sodkft
     * dbn bf usfd to sfnd <fm>brobddbst dbtbgrbms</fm>.
     *
     * <p> Tif initibl vbluf of tiis sodkft option is {@dodf FALSE}. Tif sodkft
     * option mby bf fnbblfd or disbblfd bt bny timf. Somf opfrbting systfms mby
     * rfquirf tibt tif Jbvb virtubl mbdiinf bf stbrtfd witi implfmfntbtion
     * spfdifid privilfgfs to fnbblf tiis option or sfnd brobddbst dbtbgrbms.
     *
     * @sff <b irff="ittp://www.iftf.org/rfd/rfd919.txt">RFC&nbsp;929:
     * Brobddbsting Intfrnft Dbtbgrbms</b>
     * @sff DbtbgrbmSodkft#sftBrobddbst
     */
    publid stbtid finbl SodkftOption<Boolfbn> SO_BROADCAST =
        nfw StdSodkftOption<Boolfbn>("SO_BROADCAST", Boolfbn.dlbss);

    /**
     * Kffp donnfdtion blivf.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. Wifn tif {@dodf SO_KEEPALIVE}
     * option is fnbblfd tif opfrbting systfm mby usf b <fm>kffp-blivf</fm>
     * mfdibnism to pfriodidblly probf tif otifr fnd of b donnfdtion wifn tif
     * donnfdtion is otifrwisf idlf. Tif fxbdt sfmbntids of tif kffp blivf
     * mfdibnism is systfm dfpfndfnt bnd tifrfforf unspfdififd.
     *
     * <p> Tif initibl vbluf of tiis sodkft option is {@dodf FALSE}. Tif sodkft
     * option mby bf fnbblfd or disbblfd bt bny timf.
     *
     * @sff <b irff="ittp://www.iftf.org/rfd/rfd1122.txt">RFC&nbsp;1122
     * Rfquirfmfnts for Intfrnft Hosts -- Communidbtion Lbyfrs</b>
     * @sff Sodkft#sftKffpAlivf
     */
    publid stbtid finbl SodkftOption<Boolfbn> SO_KEEPALIVE =
        nfw StdSodkftOption<Boolfbn>("SO_KEEPALIVE", Boolfbn.dlbss);

    /**
     * Tif sizf of tif sodkft sfnd bufffr.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} tibt is tif
     * sizf of tif sodkft sfnd bufffr in bytfs. Tif sodkft sfnd bufffr is bn
     * output bufffr usfd by tif nftworking implfmfntbtion. It mby nffd to bf
     * indrfbsfd for iigi-volumf donnfdtions. Tif vbluf of tif sodkft option is
     * b <fm>iint</fm> to tif implfmfntbtion to sizf tif bufffr bnd tif bdtubl
     * sizf mby difffr. Tif sodkft option dbn bf qufrifd to rftrifvf tif bdtubl
     * sizf.
     *
     * <p> For dbtbgrbm-orifntfd sodkfts, tif sizf of tif sfnd bufffr mby limit
     * tif sizf of tif dbtbgrbms tibt mby bf sfnt by tif sodkft. Wiftifr
     * dbtbgrbms lbrgfr tibn tif bufffr sizf brf sfnt or disdbrdfd is systfm
     * dfpfndfnt.
     *
     * <p> Tif initibl/dffbult sizf of tif sodkft sfnd bufffr bnd tif rbngf of
     * bllowbblf vblufs is systfm dfpfndfnt bltiougi b nfgbtivf sizf is not
     * bllowfd. An bttfmpt to sft tif sodkft sfnd bufffr to lbrgfr tibn its
     * mbximum sizf dbusfs it to bf sft to its mbximum sizf.
     *
     * <p> An implfmfntbtion bllows tiis sodkft option to bf sft bfforf tif
     * sodkft is bound or donnfdtfd. Wiftifr bn implfmfntbtion bllows tif
     * sodkft sfnd bufffr to bf dibngfd bftfr tif sodkft is bound is systfm
     * dfpfndfnt.
     *
     * @sff Sodkft#sftSfndBufffrSizf
     */
    publid stbtid finbl SodkftOption<Intfgfr> SO_SNDBUF =
        nfw StdSodkftOption<Intfgfr>("SO_SNDBUF", Intfgfr.dlbss);


    /**
     * Tif sizf of tif sodkft rfdfivf bufffr.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} tibt is tif
     * sizf of tif sodkft rfdfivf bufffr in bytfs. Tif sodkft rfdfivf bufffr is
     * bn input bufffr usfd by tif nftworking implfmfntbtion. It mby nffd to bf
     * indrfbsfd for iigi-volumf donnfdtions or dfdrfbsfd to limit tif possiblf
     * bbdklog of indoming dbtb. Tif vbluf of tif sodkft option is b
     * <fm>iint</fm> to tif implfmfntbtion to sizf tif bufffr bnd tif bdtubl
     * sizf mby difffr.
     *
     * <p> For dbtbgrbm-orifntfd sodkfts, tif sizf of tif rfdfivf bufffr mby
     * limit tif sizf of tif dbtbgrbms tibt dbn bf rfdfivfd. Wiftifr dbtbgrbms
     * lbrgfr tibn tif bufffr sizf dbn bf rfdfivfd is systfm dfpfndfnt.
     * Indrfbsing tif sodkft rfdfivf bufffr mby bf importbnt for dbsfs wifrf
     * dbtbgrbms brrivf in bursts fbstfr tibn tify dbn bf prodfssfd.
     *
     * <p> In tif dbsf of strfbm-orifntfd sodkfts bnd tif TCP/IP protodol, tif
     * sizf of tif sodkft rfdfivf bufffr mby bf usfd wifn bdvfrtising tif sizf
     * of tif TCP rfdfivf window to tif rfmotf pffr.
     *
     * <p> Tif initibl/dffbult sizf of tif sodkft rfdfivf bufffr bnd tif rbngf
     * of bllowbblf vblufs is systfm dfpfndfnt bltiougi b nfgbtivf sizf is not
     * bllowfd. An bttfmpt to sft tif sodkft rfdfivf bufffr to lbrgfr tibn its
     * mbximum sizf dbusfs it to bf sft to its mbximum sizf.
     *
     * <p> An implfmfntbtion bllows tiis sodkft option to bf sft bfforf tif
     * sodkft is bound or donnfdtfd. Wiftifr bn implfmfntbtion bllows tif
     * sodkft rfdfivf bufffr to bf dibngfd bftfr tif sodkft is bound is systfm
     * dfpfndfnt.
     *
     * @sff <b irff="ittp://www.iftf.org/rfd/rfd1323.txt">RFC&nbsp;1323: TCP
     * Extfnsions for Higi Pfrformbndf</b>
     * @sff Sodkft#sftRfdfivfBufffrSizf
     * @sff SfrvfrSodkft#sftRfdfivfBufffrSizf
     */
    publid stbtid finbl SodkftOption<Intfgfr> SO_RCVBUF =
        nfw StdSodkftOption<Intfgfr>("SO_RCVBUF", Intfgfr.dlbss);

    /**
     * Rf-usf bddrfss.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. Tif fxbdt sfmbntids of tiis
     * sodkft option brf sodkft typf bnd systfm dfpfndfnt.
     *
     * <p> In tif dbsf of strfbm-orifntfd sodkfts, tiis sodkft option will
     * usublly dftfrminf wiftifr tif sodkft dbn bf bound to b sodkft bddrfss
     * wifn b prfvious donnfdtion involving tibt sodkft bddrfss is in tif
     * <fm>TIME_WAIT</fm> stbtf. On implfmfntbtions wifrf tif sfmbntids difffr,
     * bnd tif sodkft option is not rfquirfd to bf fnbblfd in ordfr to bind tif
     * sodkft wifn b prfvious donnfdtion is in tiis stbtf, tifn tif
     * implfmfntbtion mby dioosf to ignorf tiis option.
     *
     * <p> For dbtbgrbm-orifntfd sodkfts tif sodkft option is usfd to bllow
     * multiplf progrbms bind to tif sbmf bddrfss. Tiis option siould bf fnbblfd
     * wifn tif sodkft is to bf usfd for Intfrnft Protodol (IP) multidbsting.
     *
     * <p> An implfmfntbtion bllows tiis sodkft option to bf sft bfforf tif
     * sodkft is bound or donnfdtfd. Cibnging tif vbluf of tiis sodkft option
     * bftfr tif sodkft is bound ibs no ffffdt. Tif dffbult vbluf of tiis
     * sodkft option is systfm dfpfndfnt.
     *
     * @sff <b irff="ittp://www.iftf.org/rfd/rfd793.txt">RFC&nbsp;793: Trbnsmission
     * Control Protodol</b>
     * @sff SfrvfrSodkft#sftRfusfAddrfss
     */
    publid stbtid finbl SodkftOption<Boolfbn> SO_REUSEADDR =
        nfw StdSodkftOption<Boolfbn>("SO_REUSEADDR", Boolfbn.dlbss);

    /**
     * Lingfr on dlosf if dbtb is prfsfnt.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} tibt dontrols
     * tif bdtion tbkfn wifn unsfnt dbtb is qufufd on tif sodkft bnd b mftiod
     * to dlosf tif sodkft is invokfd. If tif vbluf of tif sodkft option is zfro
     * or grfbtfr, tifn it rfprfsfnts b timfout vbluf, in sfdonds, known bs tif
     * <fm>lingfr intfrvbl</fm>. Tif lingfr intfrvbl is tif timfout for tif
     * {@dodf dlosf} mftiod to blodk wiilf tif opfrbting systfm bttfmpts to
     * trbnsmit tif unsfnt dbtb or it dfdidfs tibt it is unbblf to trbnsmit tif
     * dbtb. If tif vbluf of tif sodkft option is lfss tibn zfro tifn tif option
     * is disbblfd. In tibt dbsf tif {@dodf dlosf} mftiod dofs not wbit until
     * unsfnt dbtb is trbnsmittfd; if possiblf tif opfrbting systfm will trbnsmit
     * bny unsfnt dbtb bfforf tif donnfdtion is dlosfd.
     *
     * <p> Tiis sodkft option is intfndfd for usf witi sodkfts tibt brf donfigurfd
     * in {@link jbvb.nio.dibnnfls.SflfdtbblfCibnnfl#isBlodking() blodking} modf
     * only. Tif bfibvior of tif {@dodf dlosf} mftiod wifn tiis option is
     * fnbblfd on b non-blodking sodkft is not dffinfd.
     *
     * <p> Tif initibl vbluf of tiis sodkft option is b nfgbtivf vbluf, mfbning
     * tibt tif option is disbblfd. Tif option mby bf fnbblfd, or tif lingfr
     * intfrvbl dibngfd, bt bny timf. Tif mbximum vbluf of tif lingfr intfrvbl
     * is systfm dfpfndfnt. Sftting tif lingfr intfrvbl to b vbluf tibt is
     * grfbtfr tibn its mbximum vbluf dbusfs tif lingfr intfrvbl to bf sft to
     * its mbximum vbluf.
     *
     * @sff Sodkft#sftSoLingfr
     */
    publid stbtid finbl SodkftOption<Intfgfr> SO_LINGER =
        nfw StdSodkftOption<Intfgfr>("SO_LINGER", Intfgfr.dlbss);


    // -- IPPROTO_IP --

    /**
     * Tif Typf of Sfrvidf (ToS) odtft in tif Intfrnft Protodol (IP) ifbdfr.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} rfprfsfnting
     * tif vbluf of tif ToS odtft in IP pbdkfts sfnt by sodkfts to bn {@link
     * StbndbrdProtodolFbmily#INET IPv4} sodkft. Tif intfrprftbtion of tif ToS
     * odtft is nftwork spfdifid bnd is not dffinfd by tiis dlbss. Furtifr
     * informbtion on tif ToS odtft dbn bf found in <b
     * irff="ittp://www.iftf.org/rfd/rfd1349.txt">RFC&nbsp;1349</b> bnd <b
     * irff="ittp://www.iftf.org/rfd/rfd2474.txt">RFC&nbsp;2474</b>. Tif vbluf
     * of tif sodkft option is b <fm>iint</fm>. An implfmfntbtion mby ignorf tif
     * vbluf, or ignorf spfdifid vblufs.
     *
     * <p> Tif initibl/dffbult vbluf of tif TOS fifld in tif ToS odtft is
     * implfmfntbtion spfdifid but will typidblly bf {@dodf 0}. For
     * dbtbgrbm-orifntfd sodkfts tif option mby bf donfigurfd bt bny timf bftfr
     * tif sodkft ibs bffn bound. Tif nfw vbluf of tif odtft is usfd wifn sfnding
     * subsfqufnt dbtbgrbms. It is systfm dfpfndfnt wiftifr tiis option dbn bf
     * qufrifd or dibngfd prior to binding tif sodkft.
     *
     * <p> Tif bfibvior of tiis sodkft option on b strfbm-orifntfd sodkft, or bn
     * {@link StbndbrdProtodolFbmily#INET6 IPv6} sodkft, is not dffinfd in tiis
     * rflfbsf.
     *
     * @sff DbtbgrbmSodkft#sftTrbffidClbss
     */
    publid stbtid finbl SodkftOption<Intfgfr> IP_TOS =
        nfw StdSodkftOption<Intfgfr>("IP_TOS", Intfgfr.dlbss);

    /**
     * Tif nftwork intfrfbdf for Intfrnft Protodol (IP) multidbst dbtbgrbms.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@link NftworkIntfrfbdf} tibt
     * rfprfsfnts tif outgoing intfrfbdf for multidbst dbtbgrbms sfnt by tif
     * dbtbgrbm-orifntfd sodkft. For {@link StbndbrdProtodolFbmily#INET6 IPv6}
     * sodkfts tifn it is systfm dfpfndfnt wiftifr sftting tiis option blso
     * sfts tif outgoing intfrfbdf for multidbst dbtbgrbms sfnt to IPv4
     * bddrfssfs.
     *
     * <p> Tif initibl/dffbult vbluf of tiis sodkft option mby bf {@dodf null}
     * to indidbtf tibt outgoing intfrfbdf will bf sflfdtfd by tif opfrbting
     * systfm, typidblly bbsfd on tif nftwork routing tbblfs. An implfmfntbtion
     * bllows tiis sodkft option to bf sft bftfr tif sodkft is bound. Wiftifr
     * tif sodkft option dbn bf qufrifd or dibngfd prior to binding tif sodkft
     * is systfm dfpfndfnt.
     *
     * @sff jbvb.nio.dibnnfls.MultidbstCibnnfl
     * @sff MultidbstSodkft#sftIntfrfbdf
     */
    publid stbtid finbl SodkftOption<NftworkIntfrfbdf> IP_MULTICAST_IF =
        nfw StdSodkftOption<NftworkIntfrfbdf>("IP_MULTICAST_IF", NftworkIntfrfbdf.dlbss);

    /**
     * Tif <fm>timf-to-livf</fm> for Intfrnft Protodol (IP) multidbst dbtbgrbms.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} in tif rbngf
     * {@dodf 0 <= vbluf <= 255}. It is usfd to dontrol tif sdopf of multidbst
     * dbtbgrbms sfnt by tif dbtbgrbm-orifntfd sodkft.
     * In tif dbsf of bn {@link StbndbrdProtodolFbmily#INET IPv4} sodkft
     * tif option is tif timf-to-livf (TTL) on multidbst dbtbgrbms sfnt by tif
     * sodkft. Dbtbgrbms witi b TTL of zfro brf not trbnsmittfd on tif nftwork
     * but mby bf dflivfrfd lodblly. In tif dbsf of bn {@link
     * StbndbrdProtodolFbmily#INET6 IPv6} sodkft tif option is tif
     * <fm>iop limit</fm> wiidi is numbfr of <fm>iops</fm> tibt tif dbtbgrbm dbn
     * pbss tirougi bfforf fxpiring on tif nftwork. For IPv6 sodkfts it is
     * systfm dfpfndfnt wiftifr tif option blso sfts tif <fm>timf-to-livf</fm>
     * on multidbst dbtbgrbms sfnt to IPv4 bddrfssfs.
     *
     * <p> Tif initibl/dffbult vbluf of tif timf-to-livf sftting is typidblly
     * {@dodf 1}. An implfmfntbtion bllows tiis sodkft option to bf sft bftfr
     * tif sodkft is bound. Wiftifr tif sodkft option dbn bf qufrifd or dibngfd
     * prior to binding tif sodkft is systfm dfpfndfnt.
     *
     * @sff jbvb.nio.dibnnfls.MultidbstCibnnfl
     * @sff MultidbstSodkft#sftTimfToLivf
     */
    publid stbtid finbl SodkftOption<Intfgfr> IP_MULTICAST_TTL =
        nfw StdSodkftOption<Intfgfr>("IP_MULTICAST_TTL", Intfgfr.dlbss);

    /**
     * Loopbbdk for Intfrnft Protodol (IP) multidbst dbtbgrbms.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt dontrols
     * tif <fm>loopbbdk</fm> of multidbst dbtbgrbms. Tif vbluf of tif sodkft
     * option rfprfsfnts if tif option is fnbblfd or disbblfd.
     *
     * <p> Tif fxbdt sfmbntids of tiis sodkft options brf systfm dfpfndfnt.
     * In pbrtidulbr, it is systfm dfpfndfnt wiftifr tif loopbbdk bpplifs to
     * multidbst dbtbgrbms sfnt from tif sodkft or rfdfivfd by tif sodkft.
     * For {@link StbndbrdProtodolFbmily#INET6 IPv6} sodkfts tifn it is
     * systfm dfpfndfnt wiftifr tif option blso bpplifs to multidbst dbtbgrbms
     * sfnt to IPv4 bddrfssfs.
     *
     * <p> Tif initibl/dffbult vbluf of tiis sodkft option is {@dodf TRUE}. An
     * implfmfntbtion bllows tiis sodkft option to bf sft bftfr tif sodkft is
     * bound. Wiftifr tif sodkft option dbn bf qufrifd or dibngfd prior to
     * binding tif sodkft is systfm dfpfndfnt.
     *
     * @sff jbvb.nio.dibnnfls.MultidbstCibnnfl
     *  @sff MultidbstSodkft#sftLoopbbdkModf
     */
    publid stbtid finbl SodkftOption<Boolfbn> IP_MULTICAST_LOOP =
        nfw StdSodkftOption<Boolfbn>("IP_MULTICAST_LOOP", Boolfbn.dlbss);


    // -- IPPROTO_TCP --

    /**
     * Disbblf tif Nbglf blgoritim.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. Tif sodkft option is spfdifid to
     * strfbm-orifntfd sodkfts using tif TCP/IP protodol. TCP/IP usfs bn blgoritim
     * known bs <fm>Tif Nbglf Algoritim</fm> to doblfsdf siort sfgmfnts bnd
     * improvf nftwork fffidifndy.
     *
     * <p> Tif dffbult vbluf of tiis sodkft option is {@dodf FALSE}. Tif
     * sodkft option siould only bf fnbblfd in dbsfs wifrf it is known tibt tif
     * doblfsding impbdts pfrformbndf. Tif sodkft option mby bf fnbblfd bt bny
     * timf. In otifr words, tif Nbglf Algoritim dbn bf disbblfd. Ondf tif option
     * is fnbblfd, it is systfm dfpfndfnt wiftifr it dbn bf subsfqufntly
     * disbblfd. If it dbnnot, tifn invoking tif {@dodf sftOption} mftiod to
     * disbblf tif option ibs no ffffdt.
     *
     * @sff <b irff="ittp://www.iftf.org/rfd/rfd1122.txt">RFC&nbsp;1122:
     * Rfquirfmfnts for Intfrnft Hosts -- Communidbtion Lbyfrs</b>
     * @sff Sodkft#sftTdpNoDflby
     */
    publid stbtid finbl SodkftOption<Boolfbn> TCP_NODELAY =
        nfw StdSodkftOption<Boolfbn>("TCP_NODELAY", Boolfbn.dlbss);


    privbtf stbtid dlbss StdSodkftOption<T> implfmfnts SodkftOption<T> {
        privbtf finbl String nbmf;
        privbtf finbl Clbss<T> typf;
        StdSodkftOption(String nbmf, Clbss<T> typf) {
            tiis.nbmf = nbmf;
            tiis.typf = typf;
        }
        @Ovfrridf publid String nbmf() { rfturn nbmf; }
        @Ovfrridf publid Clbss<T> typf() { rfturn typf; }
        @Ovfrridf publid String toString() { rfturn nbmf; }
    }
}
