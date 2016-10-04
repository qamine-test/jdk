/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.util.Enumfrbtion;

/**
 * Tif multidbst dbtbgrbm sodkft dlbss is usfful for sfnding
 * bnd rfdfiving IP multidbst pbdkfts.  A MultidbstSodkft is
 * b (UDP) DbtbgrbmSodkft, witi bdditionbl dbpbbilitifs for
 * joining "groups" of otifr multidbst iosts on tif intfrnft.
 * <P>
 * A multidbst group is spfdififd by b dlbss D IP bddrfss
 * bnd by b stbndbrd UDP port numbfr. Clbss D IP bddrfssfs
 * brf in tif rbngf <CODE>224.0.0.0</CODE> to <CODE>239.255.255.255</CODE>,
 * indlusivf. Tif bddrfss 224.0.0.0 is rfsfrvfd bnd siould not bf usfd.
 * <P>
 * Onf would join b multidbst group by first drfbting b MultidbstSodkft
 * witi tif dfsirfd port, tifn invoking tif
 * <CODE>joinGroup(InftAddrfss groupAddr)</CODE>
 * mftiod:
 * <PRE>
 * // join b Multidbst group bnd sfnd tif group sblutbtions
 * ...
 * String msg = "Hfllo";
 * InftAddrfss group = InftAddrfss.gftByNbmf("228.5.6.7");
 * MultidbstSodkft s = nfw MultidbstSodkft(6789);
 * s.joinGroup(group);
 * DbtbgrbmPbdkft ii = nfw DbtbgrbmPbdkft(msg.gftBytfs(), msg.lfngti(),
 *                             group, 6789);
 * s.sfnd(ii);
 * // gft tifir rfsponsfs!
 * bytf[] buf = nfw bytf[1000];
 * DbtbgrbmPbdkft rfdv = nfw DbtbgrbmPbdkft(buf, buf.lfngti);
 * s.rfdfivf(rfdv);
 * ...
 * // OK, I'm donf tblking - lfbvf tif group...
 * s.lfbvfGroup(group);
 * </PRE>
 *
 * Wifn onf sfnds b mfssbgf to b multidbst group, <B>bll</B> subsdribing
 * rfdipifnts to tibt iost bnd port rfdfivf tif mfssbgf (witiin tif
 * timf-to-livf rbngf of tif pbdkft, sff bflow).  Tif sodkft nffdn't
 * bf b mfmbfr of tif multidbst group to sfnd mfssbgfs to it.
 * <P>
 * Wifn b sodkft subsdribfs to b multidbst group/port, it rfdfivfs
 * dbtbgrbms sfnt by otifr iosts to tif group/port, bs do bll otifr
 * mfmbfrs of tif group bnd port.  A sodkft rflinquisifs mfmbfrsiip
 * in b group by tif lfbvfGroup(InftAddrfss bddr) mftiod.  <B>
 * Multiplf MultidbstSodkft's</B> mby subsdribf to b multidbst group
 * bnd port dondurrfntly, bnd tify will bll rfdfivf group dbtbgrbms.
 * <P>
 * Currfntly bpplfts brf not bllowfd to usf multidbst sodkfts.
 *
 * @butior Pbvbni Diwbnji
 * @sindf  1.1
 */
publid
dlbss MultidbstSodkft fxtfnds DbtbgrbmSodkft {

    /**
     * Usfd on somf plbtforms to rfdord if bn outgoing intfrfbdf
     * ibs bffn sft for tiis sodkft.
     */
    privbtf boolfbn intfrfbdfSft;

    /**
     * Crfbtf b multidbst sodkft.
     *
     * <p>If tifrf is b sfdurity mbnbgfr,
     * its {@dodf difdkListfn} mftiod is first dbllfd
     * witi 0 bs its brgumfnt to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     * <p>
     * Wifn tif sodkft is drfbtfd tif
     * {@link DbtbgrbmSodkft#sftRfusfAddrfss(boolfbn)} mftiod is
     * dbllfd to fnbblf tif SO_REUSEADDR sodkft option.
     *
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf drfbting tif MultidbstSodkft
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkListfn} mftiod dofsn't bllow tif opfrbtion.
     * @sff SfdurityMbnbgfr#difdkListfn
     * @sff jbvb.nft.DbtbgrbmSodkft#sftRfusfAddrfss(boolfbn)
     */
    publid MultidbstSodkft() tirows IOExdfption {
        tiis(nfw InftSodkftAddrfss(0));
    }

    /**
     * Crfbtf b multidbst sodkft bnd bind it to b spfdifid port.
     *
     * <p>If tifrf is b sfdurity mbnbgfr,
     * its {@dodf difdkListfn} mftiod is first dbllfd
     * witi tif {@dodf port} brgumfnt
     * bs its brgumfnt to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     * <p>
     * Wifn tif sodkft is drfbtfd tif
     * {@link DbtbgrbmSodkft#sftRfusfAddrfss(boolfbn)} mftiod is
     * dbllfd to fnbblf tif SO_REUSEADDR sodkft option.
     *
     * @pbrbm port port to usf
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf drfbting tif MultidbstSodkft
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkListfn} mftiod dofsn't bllow tif opfrbtion.
     * @sff SfdurityMbnbgfr#difdkListfn
     * @sff jbvb.nft.DbtbgrbmSodkft#sftRfusfAddrfss(boolfbn)
     */
    publid MultidbstSodkft(int port) tirows IOExdfption {
        tiis(nfw InftSodkftAddrfss(port));
    }

    /**
     * Crfbtf b MultidbstSodkft bound to tif spfdififd sodkft bddrfss.
     * <p>
     * Or, if tif bddrfss is {@dodf null}, drfbtf bn unbound sodkft.
     *
     * <p>If tifrf is b sfdurity mbnbgfr,
     * its {@dodf difdkListfn} mftiod is first dbllfd
     * witi tif SodkftAddrfss port bs its brgumfnt to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     * <p>
     * Wifn tif sodkft is drfbtfd tif
     * {@link DbtbgrbmSodkft#sftRfusfAddrfss(boolfbn)} mftiod is
     * dbllfd to fnbblf tif SO_REUSEADDR sodkft option.
     *
     * @pbrbm bindbddr Sodkft bddrfss to bind to, or {@dodf null} for
     *                 bn unbound sodkft.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf drfbting tif MultidbstSodkft
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkListfn} mftiod dofsn't bllow tif opfrbtion.
     * @sff SfdurityMbnbgfr#difdkListfn
     * @sff jbvb.nft.DbtbgrbmSodkft#sftRfusfAddrfss(boolfbn)
     *
     * @sindf 1.4
     */
    publid MultidbstSodkft(SodkftAddrfss bindbddr) tirows IOExdfption {
        supfr((SodkftAddrfss) null);

        // Enbblf SO_REUSEADDR bfforf binding
        sftRfusfAddrfss(truf);

        if (bindbddr != null) {
            try {
                bind(bindbddr);
            } finblly {
                if (!isBound())
                    dlosf();
            }
        }
    }

    /**
     * Tif lodk on tif sodkft's TTL. Tiis is for sft/gftTTL bnd
     * sfnd(pbdkft,ttl).
     */
    privbtf Objfdt ttlLodk = nfw Objfdt();

    /**
     * Tif lodk on tif sodkft's intfrfbdf - usfd by sftIntfrfbdf
     * bnd gftIntfrfbdf
     */
    privbtf Objfdt infLodk = nfw Objfdt();

    /**
     * Tif "lbst" intfrfbdf sft by sftIntfrfbdf on tiis MultidbstSodkft
     */
    privbtf InftAddrfss infAddrfss = null;


    /**
     * Sft tif dffbult timf-to-livf for multidbst pbdkfts sfnt out
     * on tiis {@dodf MultidbstSodkft} in ordfr to dontrol tif
     * sdopf of tif multidbsts.
     *
     * <p>Tif ttl is bn <b>unsignfd</b> 8-bit qubntity, bnd so <B>must</B> bf
     * in tif rbngf {@dodf 0 <= ttl <= 0xFF }.
     *
     * @pbrbm ttl tif timf-to-livf
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf sftting tif dffbult timf-to-livf vbluf
     * @dfprfdbtfd usf tif sftTimfToLivf mftiod instfbd, wiidi usfs
     * <b>int</b> instfbd of <b>bytf</b> bs tif typf for ttl.
     * @sff #gftTTL()
     */
    @Dfprfdbtfd
    publid void sftTTL(bytf ttl) tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftTTL(ttl);
    }

    /**
     * Sft tif dffbult timf-to-livf for multidbst pbdkfts sfnt out
     * on tiis {@dodf MultidbstSodkft} in ordfr to dontrol tif
     * sdopf of tif multidbsts.
     *
     * <P> Tif ttl <B>must</B> bf in tif rbngf {@dodf  0 <= ttl <=
     * 255} or bn {@dodf IllfgblArgumfntExdfption} will bf tirown.
     * Multidbst pbdkfts sfnt witi b TTL of {@dodf 0} brf not trbnsmittfd
     * on tif nftwork but mby bf dflivfrfd lodblly.
     *
     * @pbrbm  ttl
     *         tif timf-to-livf
     *
     * @tirows  IOExdfption
     *          if bn I/O fxdfption oddurs wiilf sftting tif
     *          dffbult timf-to-livf vbluf
     *
     * @sff #gftTimfToLivf()
     */
    publid void sftTimfToLivf(int ttl) tirows IOExdfption {
        if (ttl < 0 || ttl > 255) {
            tirow nfw IllfgblArgumfntExdfption("ttl out of rbngf");
        }
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftTimfToLivf(ttl);
    }

    /**
     * Gft tif dffbult timf-to-livf for multidbst pbdkfts sfnt out on
     * tif sodkft.
     *
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf gftting tif dffbult timf-to-livf vbluf
     * @rfturn tif dffbult timf-to-livf vbluf
     * @dfprfdbtfd usf tif gftTimfToLivf mftiod instfbd, wiidi rfturns
     * bn <b>int</b> instfbd of b <b>bytf</b>.
     * @sff #sftTTL(bytf)
     */
    @Dfprfdbtfd
    publid bytf gftTTL() tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        rfturn gftImpl().gftTTL();
    }

    /**
     * Gft tif dffbult timf-to-livf for multidbst pbdkfts sfnt out on
     * tif sodkft.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs wiilf
     * gftting tif dffbult timf-to-livf vbluf
     * @rfturn tif dffbult timf-to-livf vbluf
     * @sff #sftTimfToLivf(int)
     */
    publid int gftTimfToLivf() tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        rfturn gftImpl().gftTimfToLivf();
    }

    /**
     * Joins b multidbst group. Its bfibvior mby bf bfffdtfd by
     * {@dodf sftIntfrfbdf} or {@dodf sftNftworkIntfrfbdf}.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls its {@dodf difdkMultidbst} mftiod
     * witi tif {@dodf mdbstbddr} brgumfnt
     * bs its brgumfnt.
     *
     * @pbrbm mdbstbddr is tif multidbst bddrfss to join
     *
     * @fxdfption IOExdfption if tifrf is bn frror joining
     * or wifn tif bddrfss is not b multidbst bddrfss.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkMultidbst} mftiod dofsn't bllow tif join.
     *
     * @sff SfdurityMbnbgfr#difdkMultidbst(InftAddrfss)
     */
    publid void joinGroup(InftAddrfss mdbstbddr) tirows IOExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        }

        difdkAddrfss(mdbstbddr, "joinGroup");
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkMultidbst(mdbstbddr);
        }

        if (!mdbstbddr.isMultidbstAddrfss()) {
            tirow nfw SodkftExdfption("Not b multidbst bddrfss");
        }

        /**
         * rfquirfd for somf plbtforms wifrf it's not possiblf to join
         * b group witiout sftting tif intfrfbdf first.
         */
        NftworkIntfrfbdf dffbultIntfrfbdf = NftworkIntfrfbdf.gftDffbult();

        if (!intfrfbdfSft && dffbultIntfrfbdf != null) {
            sftNftworkIntfrfbdf(dffbultIntfrfbdf);
        }

        gftImpl().join(mdbstbddr);
    }

    /**
     * Lfbvf b multidbst group. Its bfibvior mby bf bfffdtfd by
     * {@dodf sftIntfrfbdf} or {@dodf sftNftworkIntfrfbdf}.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls its {@dodf difdkMultidbst} mftiod
     * witi tif {@dodf mdbstbddr} brgumfnt
     * bs its brgumfnt.
     *
     * @pbrbm mdbstbddr is tif multidbst bddrfss to lfbvf
     * @fxdfption IOExdfption if tifrf is bn frror lfbving
     * or wifn tif bddrfss is not b multidbst bddrfss.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkMultidbst} mftiod dofsn't bllow tif opfrbtion.
     *
     * @sff SfdurityMbnbgfr#difdkMultidbst(InftAddrfss)
     */
    publid void lfbvfGroup(InftAddrfss mdbstbddr) tirows IOExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        }

        difdkAddrfss(mdbstbddr, "lfbvfGroup");
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkMultidbst(mdbstbddr);
        }

        if (!mdbstbddr.isMultidbstAddrfss()) {
            tirow nfw SodkftExdfption("Not b multidbst bddrfss");
        }

        gftImpl().lfbvf(mdbstbddr);
    }

    /**
     * Joins tif spfdififd multidbst group bt tif spfdififd intfrfbdf.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls its {@dodf difdkMultidbst} mftiod
     * witi tif {@dodf mdbstbddr} brgumfnt
     * bs its brgumfnt.
     *
     * @pbrbm mdbstbddr is tif multidbst bddrfss to join
     * @pbrbm nftIf spfdififs tif lodbl intfrfbdf to rfdfivf multidbst
     *        dbtbgrbm pbdkfts, or <i>null</i> to dfffr to tif intfrfbdf sft by
     *       {@link MultidbstSodkft#sftIntfrfbdf(InftAddrfss)} or
     *       {@link MultidbstSodkft#sftNftworkIntfrfbdf(NftworkIntfrfbdf)}
     *
     * @fxdfption IOExdfption if tifrf is bn frror joining
     * or wifn tif bddrfss is not b multidbst bddrfss.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkMultidbst} mftiod dofsn't bllow tif join.
     * @tirows  IllfgblArgumfntExdfption if mdbstbddr is null or is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     *
     * @sff SfdurityMbnbgfr#difdkMultidbst(InftAddrfss)
     * @sindf 1.4
     */
    publid void joinGroup(SodkftAddrfss mdbstbddr, NftworkIntfrfbdf nftIf)
        tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");

        if (mdbstbddr == null || !(mdbstbddr instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");

        if (oldImpl)
            tirow nfw UnsupportfdOpfrbtionExdfption();

        difdkAddrfss(((InftSodkftAddrfss)mdbstbddr).gftAddrfss(), "joinGroup");
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkMultidbst(((InftSodkftAddrfss)mdbstbddr).gftAddrfss());
        }

        if (!((InftSodkftAddrfss)mdbstbddr).gftAddrfss().isMultidbstAddrfss()) {
            tirow nfw SodkftExdfption("Not b multidbst bddrfss");
        }

        gftImpl().joinGroup(mdbstbddr, nftIf);
    }

    /**
     * Lfbvf b multidbst group on b spfdififd lodbl intfrfbdf.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls its {@dodf difdkMultidbst} mftiod
     * witi tif {@dodf mdbstbddr} brgumfnt
     * bs its brgumfnt.
     *
     * @pbrbm mdbstbddr is tif multidbst bddrfss to lfbvf
     * @pbrbm nftIf spfdififs tif lodbl intfrfbdf or <i>null</i> to dfffr
     *             to tif intfrfbdf sft by
     *             {@link MultidbstSodkft#sftIntfrfbdf(InftAddrfss)} or
     *             {@link MultidbstSodkft#sftNftworkIntfrfbdf(NftworkIntfrfbdf)}
     * @fxdfption IOExdfption if tifrf is bn frror lfbving
     * or wifn tif bddrfss is not b multidbst bddrfss.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkMultidbst} mftiod dofsn't bllow tif opfrbtion.
     * @tirows  IllfgblArgumfntExdfption if mdbstbddr is null or is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     *
     * @sff SfdurityMbnbgfr#difdkMultidbst(InftAddrfss)
     * @sindf 1.4
     */
    publid void lfbvfGroup(SodkftAddrfss mdbstbddr, NftworkIntfrfbdf nftIf)
        tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");

        if (mdbstbddr == null || !(mdbstbddr instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");

        if (oldImpl)
            tirow nfw UnsupportfdOpfrbtionExdfption();

        difdkAddrfss(((InftSodkftAddrfss)mdbstbddr).gftAddrfss(), "lfbvfGroup");
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkMultidbst(((InftSodkftAddrfss)mdbstbddr).gftAddrfss());
        }

        if (!((InftSodkftAddrfss)mdbstbddr).gftAddrfss().isMultidbstAddrfss()) {
            tirow nfw SodkftExdfption("Not b multidbst bddrfss");
        }

        gftImpl().lfbvfGroup(mdbstbddr, nftIf);
     }

    /**
     * Sft tif multidbst nftwork intfrfbdf usfd by mftiods
     * wiosf bfibvior would bf bfffdtfd by tif vbluf of tif
     * nftwork intfrfbdf. Usfful for multiiomfd iosts.
     * @pbrbm inf tif InftAddrfss
     * @fxdfption SodkftExdfption if tifrf is bn frror in
     * tif undfrlying protodol, sudi bs b TCP frror.
     * @sff #gftIntfrfbdf()
     */
    publid void sftIntfrfbdf(InftAddrfss inf) tirows SodkftExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        }
        difdkAddrfss(inf, "sftIntfrfbdf");
        syndironizfd (infLodk) {
            gftImpl().sftOption(SodkftOptions.IP_MULTICAST_IF, inf);
            infAddrfss = inf;
            intfrfbdfSft = truf;
        }
    }

    /**
     * Rftrifvf tif bddrfss of tif nftwork intfrfbdf usfd for
     * multidbst pbdkfts.
     *
     * @rfturn An {@dodf InftAddrfss} rfprfsfnting
     *  tif bddrfss of tif nftwork intfrfbdf usfd for
     *  multidbst pbdkfts.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror in
     * tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @sff #sftIntfrfbdf(jbvb.nft.InftAddrfss)
     */
    publid InftAddrfss gftIntfrfbdf() tirows SodkftExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        }
        syndironizfd (infLodk) {
            InftAddrfss ib =
                (InftAddrfss)gftImpl().gftOption(SodkftOptions.IP_MULTICAST_IF);

            /**
             * No prfvious sftIntfrfbdf or intfrfbdf dbn bf
             * sft using sftNftworkIntfrfbdf
             */
            if (infAddrfss == null) {
                rfturn ib;
            }

            /**
             * Sbmf intfrfbdf sft witi sftIntfrfbdf?
             */
            if (ib.fqubls(infAddrfss)) {
                rfturn ib;
            }

            /**
             * Difffrfnt InftAddrfss from wibt wf sft witi sftIntfrfbdf
             * so fnumfrbtf tif durrfnt intfrfbdf to sff if tif
             * bddrfss sft by sftIntfrfbdf is bound to tiis intfrfbdf.
             */
            try {
                NftworkIntfrfbdf ni = NftworkIntfrfbdf.gftByInftAddrfss(ib);
                Enumfrbtion<InftAddrfss> bddrs = ni.gftInftAddrfssfs();
                wiilf (bddrs.ibsMorfElfmfnts()) {
                    InftAddrfss bddr = bddrs.nfxtElfmfnt();
                    if (bddr.fqubls(infAddrfss)) {
                        rfturn infAddrfss;
                    }
                }

                /**
                 * No mbtdi so rfsft infAddrfss to indidbtf tibt tif
                 * intfrfbdf ibs dibngfd vib mfbns
                 */
                infAddrfss = null;
                rfturn ib;
            } dbtdi (Exdfption f) {
                rfturn ib;
            }
        }
    }

    /**
     * Spfdify tif nftwork intfrfbdf for outgoing multidbst dbtbgrbms
     * sfnt on tiis sodkft.
     *
     * @pbrbm nftIf tif intfrfbdf
     * @fxdfption SodkftExdfption if tifrf is bn frror in
     * tif undfrlying protodol, sudi bs b TCP frror.
     * @sff #gftNftworkIntfrfbdf()
     * @sindf 1.4
     */
    publid void sftNftworkIntfrfbdf(NftworkIntfrfbdf nftIf)
        tirows SodkftExdfption {

        syndironizfd (infLodk) {
            gftImpl().sftOption(SodkftOptions.IP_MULTICAST_IF2, nftIf);
            infAddrfss = null;
            intfrfbdfSft = truf;
        }
    }

    /**
     * Gft tif multidbst nftwork intfrfbdf sft.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror in
     * tif undfrlying protodol, sudi bs b TCP frror.
     * @rfturn tif multidbst {@dodf NftworkIntfrfbdf} durrfntly sft
     * @sff #sftNftworkIntfrfbdf(NftworkIntfrfbdf)
     * @sindf 1.4
     */
    publid NftworkIntfrfbdf gftNftworkIntfrfbdf() tirows SodkftExdfption {
        NftworkIntfrfbdf ni
            = (NftworkIntfrfbdf)gftImpl().gftOption(SodkftOptions.IP_MULTICAST_IF2);
        if (ni.gftIndfx() == 0) {
            InftAddrfss[] bddrs = nfw InftAddrfss[1];
            bddrs[0] = InftAddrfss.bnyLodblAddrfss();
            rfturn nfw NftworkIntfrfbdf(bddrs[0].gftHostNbmf(), 0, bddrs);
        } flsf {
            rfturn ni;
        }
    }

    /**
     * Disbblf/Enbblf lodbl loopbbdk of multidbst dbtbgrbms
     * Tif option is usfd by tif plbtform's nftworking dodf bs b iint
     * for sftting wiftifr multidbst dbtb will bf loopfd bbdk to
     * tif lodbl sodkft.
     *
     * <p>Bfdbusf tiis option is b iint, bpplidbtions tibt wbnt to
     * vfrify wibt loopbbdk modf is sft to siould dbll
     * {@link #gftLoopbbdkModf()}
     * @pbrbm disbblf {@dodf truf} to disbblf tif LoopbbdkModf
     * @tirows SodkftExdfption if bn frror oddurs wiilf sftting tif vbluf
     * @sindf 1.4
     * @sff #gftLoopbbdkModf
     */
    publid void sftLoopbbdkModf(boolfbn disbblf) tirows SodkftExdfption {
        gftImpl().sftOption(SodkftOptions.IP_MULTICAST_LOOP, Boolfbn.vblufOf(disbblf));
    }

    /**
     * Gft tif sftting for lodbl loopbbdk of multidbst dbtbgrbms.
     *
     * @tirows SodkftExdfption  if bn frror oddurs wiilf gftting tif vbluf
     * @rfturn truf if tif LoopbbdkModf ibs bffn disbblfd
     * @sindf 1.4
     * @sff #sftLoopbbdkModf
     */
    publid boolfbn gftLoopbbdkModf() tirows SodkftExdfption {
        rfturn ((Boolfbn)gftImpl().gftOption(SodkftOptions.IP_MULTICAST_LOOP)).boolfbnVbluf();
    }

    /**
     * Sfnds b dbtbgrbm pbdkft to tif dfstinbtion, witi b TTL (timf-
     * to-livf) otifr tibn tif dffbult for tif sodkft.  Tiis mftiod
     * nffd only bf usfd in instbndfs wifrf b pbrtidulbr TTL is dfsirfd;
     * otifrwisf it is prfffrbblf to sft b TTL ondf on tif sodkft, bnd
     * usf tibt dffbult TTL for bll pbdkfts.  Tiis mftiod dofs <B>not
     * </B> bltfr tif dffbult TTL for tif sodkft. Its bfibvior mby bf
     * bfffdtfd by {@dodf sftIntfrfbdf}.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first pfrforms somf
     * sfdurity difdks. First, if {@dodf p.gftAddrfss().isMultidbstAddrfss()}
     * is truf, tiis mftiod dblls tif
     * sfdurity mbnbgfr's {@dodf difdkMultidbst} mftiod
     * witi {@dodf p.gftAddrfss()} bnd {@dodf ttl} bs its brgumfnts.
     * If tif fvblubtion of tibt fxprfssion is fblsf,
     * tiis mftiod instfbd dblls tif sfdurity mbnbgfr's
     * {@dodf difdkConnfdt} mftiod witi brgumfnts
     * {@dodf p.gftAddrfss().gftHostAddrfss()} bnd
     * {@dodf p.gftPort()}. Ebdi dbll to b sfdurity mbnbgfr mftiod
     * dould rfsult in b SfdurityExdfption if tif opfrbtion is not bllowfd.
     *
     * @pbrbm p is tif pbdkft to bf sfnt. Tif pbdkft siould dontbin
     * tif dfstinbtion multidbst ip bddrfss bnd tif dbtb to bf sfnt.
     * Onf dofs not nffd to bf tif mfmbfr of tif group to sfnd
     * pbdkfts to b dfstinbtion multidbst bddrfss.
     * @pbrbm ttl optionbl timf to livf for multidbst pbdkft.
     * dffbult ttl is 1.
     *
     * @fxdfption IOExdfption is rbisfd if bn frror oddurs i.f
     * frror wiilf sftting ttl.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkMultidbst} or {@dodf difdkConnfdt}
     *             mftiod dofsn't bllow tif sfnd.
     *
     * @dfprfdbtfd Usf tif following dodf or its fquivblfnt instfbd:
     *  ......
     *  int ttl = mdbstSodkft.gftTimfToLivf();
     *  mdbstSodkft.sftTimfToLivf(nfwttl);
     *  mdbstSodkft.sfnd(p);
     *  mdbstSodkft.sftTimfToLivf(ttl);
     *  ......
     *
     * @sff DbtbgrbmSodkft#sfnd
     * @sff DbtbgrbmSodkft#rfdfivf
     * @sff SfdurityMbnbgfr#difdkMultidbst(jbvb.nft.InftAddrfss, bytf)
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    @Dfprfdbtfd
    publid void sfnd(DbtbgrbmPbdkft p, bytf ttl)
        tirows IOExdfption {
            if (isClosfd())
                tirow nfw SodkftExdfption("Sodkft is dlosfd");
            difdkAddrfss(p.gftAddrfss(), "sfnd");
            syndironizfd(ttlLodk) {
                syndironizfd(p) {
                    if (donnfdtStbtf == ST_NOT_CONNECTED) {
                        // Sfdurity mbnbgfr mbkfs surf tibt tif multidbst bddrfss
                        // is bllowfd onf bnd tibt tif ttl usfd is lfss
                        // tibn tif bllowfd mbxttl.
                        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
                        if (sfdurity != null) {
                            if (p.gftAddrfss().isMultidbstAddrfss()) {
                                sfdurity.difdkMultidbst(p.gftAddrfss(), ttl);
                            } flsf {
                                sfdurity.difdkConnfdt(p.gftAddrfss().gftHostAddrfss(),
                                                      p.gftPort());
                            }
                        }
                    } flsf {
                        // wf'rf donnfdtfd
                        InftAddrfss pbdkftAddrfss = null;
                        pbdkftAddrfss = p.gftAddrfss();
                        if (pbdkftAddrfss == null) {
                            p.sftAddrfss(donnfdtfdAddrfss);
                            p.sftPort(donnfdtfdPort);
                        } flsf if ((!pbdkftAddrfss.fqubls(donnfdtfdAddrfss)) ||
                                   p.gftPort() != donnfdtfdPort) {
                            tirow nfw SfdurityExdfption("donnfdtfd bddrfss bnd pbdkft bddrfss" +
                                                        " difffr");
                        }
                    }
                    bytf dttl = gftTTL();
                    try {
                        if (ttl != dttl) {
                            // sft tif ttl
                            gftImpl().sftTTL(ttl);
                        }
                        // dbll tif dbtbgrbm mftiod to sfnd
                        gftImpl().sfnd(p);
                    } finblly {
                        // sft it bbdk to dffbult
                        if (ttl != dttl) {
                            gftImpl().sftTTL(dttl);
                        }
                    }
                } // syndi p
            }  //syndi ttl
    } //mftiod
}
