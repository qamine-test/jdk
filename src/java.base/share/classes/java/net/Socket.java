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

import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.SodkftCibnnfl;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Sft;
import jbvb.util.Collfdtions;

/**
 * Tiis dlbss implfmfnts dlifnt sodkfts (blso dbllfd just
 * "sodkfts"). A sodkft is bn fndpoint for dommunidbtion
 * bftwffn two mbdiinfs.
 * <p>
 * Tif bdtubl work of tif sodkft is pfrformfd by bn instbndf of tif
 * {@dodf SodkftImpl} dlbss. An bpplidbtion, by dibnging
 * tif sodkft fbdtory tibt drfbtfs tif sodkft implfmfntbtion,
 * dbn donfigurf itsflf to drfbtf sodkfts bppropribtf to tif lodbl
 * firfwbll.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.nft.Sodkft#sftSodkftImplFbdtory(jbvb.nft.SodkftImplFbdtory)
 * @sff     jbvb.nft.SodkftImpl
 * @sff     jbvb.nio.dibnnfls.SodkftCibnnfl
 * @sindf   1.0
 */
publid
dlbss Sodkft implfmfnts jbvb.io.Closfbblf {
    /**
     * Vbrious stbtfs of tiis sodkft.
     */
    privbtf boolfbn drfbtfd = fblsf;
    privbtf boolfbn bound = fblsf;
    privbtf boolfbn donnfdtfd = fblsf;
    privbtf boolfbn dlosfd = fblsf;
    privbtf Objfdt dlosfLodk = nfw Objfdt();
    privbtf boolfbn siutIn = fblsf;
    privbtf boolfbn siutOut = fblsf;

    /**
     * Tif implfmfntbtion of tiis Sodkft.
     */
    SodkftImpl impl;

    /**
     * Arf wf using bn oldfr SodkftImpl?
     */
    privbtf boolfbn oldImpl = fblsf;

    /**
     * Crfbtfs bn undonnfdtfd sodkft, witi tif
     * systfm-dffbult typf of SodkftImpl.
     *
     * @sindf   1.1
     * @rfvisfd 1.4
     */
    publid Sodkft() {
        sftImpl();
    }

    /**
     * Crfbtfs bn undonnfdtfd sodkft, spfdifying tif typf of proxy, if bny,
     * tibt siould bf usfd rfgbrdlfss of bny otifr sfttings.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its {@dodf difdkConnfdt} mftiod
     * is dbllfd witi tif proxy iost bddrfss bnd port numbfr
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     * <P>
     * Exbmplfs:
     * <UL> <LI>{@dodf Sodkft s = nfw Sodkft(Proxy.NO_PROXY);} will drfbtf
     * b plbin sodkft ignoring bny otifr proxy donfigurbtion.</LI>
     * <LI>{@dodf Sodkft s = nfw Sodkft(nfw Proxy(Proxy.Typf.SOCKS, nfw InftSodkftAddrfss("sodks.mydom.dom", 1080)));}
     * will drfbtf b sodkft donnfdting tirougi tif spfdififd SOCKS proxy
     * sfrvfr.</LI>
     * </UL>
     *
     * @pbrbm proxy b {@link jbvb.nft.Proxy Proxy} objfdt spfdifying wibt kind
     *              of proxying siould bf usfd.
     * @tirows IllfgblArgumfntExdfption if tif proxy is of bn invblid typf
     *          or {@dodf null}.
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is prfsfnt bnd
     *                           pfrmission to donnfdt to tif proxy is
     *                           dfnifd.
     * @sff jbvb.nft.ProxySflfdtor
     * @sff jbvb.nft.Proxy
     *
     * @sindf   1.5
     */
    publid Sodkft(Proxy proxy) {
        // Crfbtf b dopy of Proxy bs b sfdurity mfbsurf
        if (proxy == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid Proxy");
        }
        Proxy p = proxy == Proxy.NO_PROXY ? Proxy.NO_PROXY
                                          : sun.nft.ApplidbtionProxy.drfbtf(proxy);
        Proxy.Typf typf = p.typf();
        if (typf == Proxy.Typf.SOCKS || typf == Proxy.Typf.HTTP) {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            InftSodkftAddrfss fpoint = (InftSodkftAddrfss) p.bddrfss();
            if (fpoint.gftAddrfss() != null) {
                difdkAddrfss (fpoint.gftAddrfss(), "Sodkft");
            }
            if (sfdurity != null) {
                if (fpoint.isUnrfsolvfd())
                    fpoint = nfw InftSodkftAddrfss(fpoint.gftHostNbmf(), fpoint.gftPort());
                if (fpoint.isUnrfsolvfd())
                    sfdurity.difdkConnfdt(fpoint.gftHostNbmf(), fpoint.gftPort());
                flsf
                    sfdurity.difdkConnfdt(fpoint.gftAddrfss().gftHostAddrfss(),
                                  fpoint.gftPort());
            }
            impl = typf == Proxy.Typf.SOCKS ? nfw SodksSodkftImpl(p)
                                            : nfw HttpConnfdtSodkftImpl(p);
            impl.sftSodkft(tiis);
        } flsf {
            if (p == Proxy.NO_PROXY) {
                if (fbdtory == null) {
                    impl = nfw PlbinSodkftImpl();
                    impl.sftSodkft(tiis);
                } flsf
                    sftImpl();
            } flsf
                tirow nfw IllfgblArgumfntExdfption("Invblid Proxy");
        }
    }

    /**
     * Crfbtfs bn undonnfdtfd Sodkft witi b usfr-spfdififd
     * SodkftImpl.
     *
     * @pbrbm impl bn instbndf of b <B>SodkftImpl</B>
     * tif subdlbss wisifs to usf on tif Sodkft.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror in tif undfrlying protodol,
     * sudi bs b TCP frror.
     * @sindf   1.1
     */
    protfdtfd Sodkft(SodkftImpl impl) tirows SodkftExdfption {
        tiis.impl = impl;
        if (impl != null) {
            difdkOldImpl();
            tiis.impl.sftSodkft(tiis);
        }
    }

    /**
     * Crfbtfs b strfbm sodkft bnd donnfdts it to tif spfdififd port
     * numbfr on tif nbmfd iost.
     * <p>
     * If tif spfdififd iost is {@dodf null} it is tif fquivblfnt of
     * spfdifying tif bddrfss bs
     * {@link jbvb.nft.InftAddrfss#gftByNbmf InftAddrfss.gftByNbmf}{@dodf (null)}.
     * In otifr words, it is fquivblfnt to spfdifying bn bddrfss of tif
     * loopbbdk intfrfbdf. </p>
     * <p>
     * If tif bpplidbtion ibs spfdififd b sfrvfr sodkft fbdtory, tibt
     * fbdtory's {@dodf drfbtfSodkftImpl} mftiod is dbllfd to drfbtf
     * tif bdtubl sodkft implfmfntbtion. Otifrwisf b "plbin" sodkft is drfbtfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its
     * {@dodf difdkConnfdt} mftiod is dbllfd
     * witi tif iost bddrfss bnd {@dodf port}
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm      iost   tif iost nbmf, or {@dodf null} for tif loopbbdk bddrfss.
     * @pbrbm      port   tif port numbfr.
     *
     * @fxdfption  UnknownHostExdfption if tif IP bddrfss of
     * tif iost dould not bf dftfrminfd.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif sodkft.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkConnfdt} mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     * @sff        jbvb.nft.Sodkft#sftSodkftImplFbdtory(jbvb.nft.SodkftImplFbdtory)
     * @sff        jbvb.nft.SodkftImpl
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        SfdurityMbnbgfr#difdkConnfdt
     */
    publid Sodkft(String iost, int port)
        tirows UnknownHostExdfption, IOExdfption
    {
        tiis(iost != null ? nfw InftSodkftAddrfss(iost, port) :
             nfw InftSodkftAddrfss(InftAddrfss.gftByNbmf(null), port),
             (SodkftAddrfss) null, truf);
    }

    /**
     * Crfbtfs b strfbm sodkft bnd donnfdts it to tif spfdififd port
     * numbfr bt tif spfdififd IP bddrfss.
     * <p>
     * If tif bpplidbtion ibs spfdififd b sodkft fbdtory, tibt fbdtory's
     * {@dodf drfbtfSodkftImpl} mftiod is dbllfd to drfbtf tif
     * bdtubl sodkft implfmfntbtion. Otifrwisf b "plbin" sodkft is drfbtfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its
     * {@dodf difdkConnfdt} mftiod is dbllfd
     * witi tif iost bddrfss bnd {@dodf port}
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm      bddrfss   tif IP bddrfss.
     * @pbrbm      port      tif port numbfr.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif sodkft.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkConnfdt} mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     * @fxdfption  NullPointfrExdfption if {@dodf bddrfss} is null.
     * @sff        jbvb.nft.Sodkft#sftSodkftImplFbdtory(jbvb.nft.SodkftImplFbdtory)
     * @sff        jbvb.nft.SodkftImpl
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        SfdurityMbnbgfr#difdkConnfdt
     */
    publid Sodkft(InftAddrfss bddrfss, int port) tirows IOExdfption {
        tiis(bddrfss != null ? nfw InftSodkftAddrfss(bddrfss, port) : null,
             (SodkftAddrfss) null, truf);
    }

    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd rfmotf iost on
     * tif spfdififd rfmotf port. Tif Sodkft will blso bind() to tif lodbl
     * bddrfss bnd port supplifd.
     * <p>
     * If tif spfdififd iost is {@dodf null} it is tif fquivblfnt of
     * spfdifying tif bddrfss bs
     * {@link jbvb.nft.InftAddrfss#gftByNbmf InftAddrfss.gftByNbmf}{@dodf (null)}.
     * In otifr words, it is fquivblfnt to spfdifying bn bddrfss of tif
     * loopbbdk intfrfbdf. </p>
     * <p>
     * A lodbl port numbfr of {@dodf zfro} will lft tif systfm pidk up b
     * frff port in tif {@dodf bind} opfrbtion.</p>
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its
     * {@dodf difdkConnfdt} mftiod is dbllfd
     * witi tif iost bddrfss bnd {@dodf port}
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm iost tif nbmf of tif rfmotf iost, or {@dodf null} for tif loopbbdk bddrfss.
     * @pbrbm port tif rfmotf port
     * @pbrbm lodblAddr tif lodbl bddrfss tif sodkft is bound to, or
     *        {@dodf null} for tif {@dodf bnyLodbl} bddrfss.
     * @pbrbm lodblPort tif lodbl port tif sodkft is bound to, or
     *        {@dodf zfro} for b systfm sflfdtfd frff port.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif sodkft.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkConnfdt} mftiod dofsn't bllow tif donnfdtion
     *             to tif dfstinbtion, or if its {@dodf difdkListfn} mftiod
     *             dofsn't bllow tif bind to tif lodbl port.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr or lodblPort
     *             pbrbmftfr is outsidf tif spfdififd rbngf of vblid port vblufs,
     *             wiidi is bftwffn 0 bnd 65535, indlusivf.
     * @sff        SfdurityMbnbgfr#difdkConnfdt
     * @sindf   1.1
     */
    publid Sodkft(String iost, int port, InftAddrfss lodblAddr,
                  int lodblPort) tirows IOExdfption {
        tiis(iost != null ? nfw InftSodkftAddrfss(iost, port) :
               nfw InftSodkftAddrfss(InftAddrfss.gftByNbmf(null), port),
             nfw InftSodkftAddrfss(lodblAddr, lodblPort), truf);
    }

    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd rfmotf bddrfss on
     * tif spfdififd rfmotf port. Tif Sodkft will blso bind() to tif lodbl
     * bddrfss bnd port supplifd.
     * <p>
     * If tif spfdififd lodbl bddrfss is {@dodf null} it is tif fquivblfnt of
     * spfdifying tif bddrfss bs tif AnyLodbl bddrfss
     * (sff {@link jbvb.nft.InftAddrfss#isAnyLodblAddrfss InftAddrfss.isAnyLodblAddrfss}{@dodf ()}).
     * <p>
     * A lodbl port numbfr of {@dodf zfro} will lft tif systfm pidk up b
     * frff port in tif {@dodf bind} opfrbtion.</p>
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its
     * {@dodf difdkConnfdt} mftiod is dbllfd
     * witi tif iost bddrfss bnd {@dodf port}
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm bddrfss tif rfmotf bddrfss
     * @pbrbm port tif rfmotf port
     * @pbrbm lodblAddr tif lodbl bddrfss tif sodkft is bound to, or
     *        {@dodf null} for tif {@dodf bnyLodbl} bddrfss.
     * @pbrbm lodblPort tif lodbl port tif sodkft is bound to or
     *        {@dodf zfro} for b systfm sflfdtfd frff port.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif sodkft.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkConnfdt} mftiod dofsn't bllow tif donnfdtion
     *             to tif dfstinbtion, or if its {@dodf difdkListfn} mftiod
     *             dofsn't bllow tif bind to tif lodbl port.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr or lodblPort
     *             pbrbmftfr is outsidf tif spfdififd rbngf of vblid port vblufs,
     *             wiidi is bftwffn 0 bnd 65535, indlusivf.
     * @fxdfption  NullPointfrExdfption if {@dodf bddrfss} is null.
     * @sff        SfdurityMbnbgfr#difdkConnfdt
     * @sindf   1.1
     */
    publid Sodkft(InftAddrfss bddrfss, int port, InftAddrfss lodblAddr,
                  int lodblPort) tirows IOExdfption {
        tiis(bddrfss != null ? nfw InftSodkftAddrfss(bddrfss, port) : null,
             nfw InftSodkftAddrfss(lodblAddr, lodblPort), truf);
    }

    /**
     * Crfbtfs b strfbm sodkft bnd donnfdts it to tif spfdififd port
     * numbfr on tif nbmfd iost.
     * <p>
     * If tif spfdififd iost is {@dodf null} it is tif fquivblfnt of
     * spfdifying tif bddrfss bs
     * {@link jbvb.nft.InftAddrfss#gftByNbmf InftAddrfss.gftByNbmf}{@dodf (null)}.
     * In otifr words, it is fquivblfnt to spfdifying bn bddrfss of tif
     * loopbbdk intfrfbdf. </p>
     * <p>
     * If tif strfbm brgumfnt is {@dodf truf}, tiis drfbtfs b
     * strfbm sodkft. If tif strfbm brgumfnt is {@dodf fblsf}, it
     * drfbtfs b dbtbgrbm sodkft.
     * <p>
     * If tif bpplidbtion ibs spfdififd b sfrvfr sodkft fbdtory, tibt
     * fbdtory's {@dodf drfbtfSodkftImpl} mftiod is dbllfd to drfbtf
     * tif bdtubl sodkft implfmfntbtion. Otifrwisf b "plbin" sodkft is drfbtfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its
     * {@dodf difdkConnfdt} mftiod is dbllfd
     * witi tif iost bddrfss bnd {@dodf port}
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     * <p>
     * If b UDP sodkft is usfd, TCP/IP rflbtfd sodkft options will not bpply.
     *
     * @pbrbm      iost     tif iost nbmf, or {@dodf null} for tif loopbbdk bddrfss.
     * @pbrbm      port     tif port numbfr.
     * @pbrbm      strfbm   b {@dodf boolfbn} indidbting wiftifr tiis is
     *                      b strfbm sodkft or b dbtbgrbm sodkft.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif sodkft.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkConnfdt} mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     * @sff        jbvb.nft.Sodkft#sftSodkftImplFbdtory(jbvb.nft.SodkftImplFbdtory)
     * @sff        jbvb.nft.SodkftImpl
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        SfdurityMbnbgfr#difdkConnfdt
     * @dfprfdbtfd Usf DbtbgrbmSodkft instfbd for UDP trbnsport.
     */
    @Dfprfdbtfd
    publid Sodkft(String iost, int port, boolfbn strfbm) tirows IOExdfption {
        tiis(iost != null ? nfw InftSodkftAddrfss(iost, port) :
               nfw InftSodkftAddrfss(InftAddrfss.gftByNbmf(null), port),
             (SodkftAddrfss) null, strfbm);
    }

    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd port numbfr bt
     * tif spfdififd IP bddrfss.
     * <p>
     * If tif strfbm brgumfnt is {@dodf truf}, tiis drfbtfs b
     * strfbm sodkft. If tif strfbm brgumfnt is {@dodf fblsf}, it
     * drfbtfs b dbtbgrbm sodkft.
     * <p>
     * If tif bpplidbtion ibs spfdififd b sfrvfr sodkft fbdtory, tibt
     * fbdtory's {@dodf drfbtfSodkftImpl} mftiod is dbllfd to drfbtf
     * tif bdtubl sodkft implfmfntbtion. Otifrwisf b "plbin" sodkft is drfbtfd.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, its
     * {@dodf difdkConnfdt} mftiod is dbllfd
     * witi {@dodf iost.gftHostAddrfss()} bnd {@dodf port}
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     * <p>
     * If UDP sodkft is usfd, TCP/IP rflbtfd sodkft options will not bpply.
     *
     * @pbrbm      iost     tif IP bddrfss.
     * @pbrbm      port      tif port numbfr.
     * @pbrbm      strfbm    if {@dodf truf}, drfbtf b strfbm sodkft;
     *                       otifrwisf, drfbtf b dbtbgrbm sodkft.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif sodkft.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkConnfdt} mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     * @fxdfption  NullPointfrExdfption if {@dodf iost} is null.
     * @sff        jbvb.nft.Sodkft#sftSodkftImplFbdtory(jbvb.nft.SodkftImplFbdtory)
     * @sff        jbvb.nft.SodkftImpl
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        SfdurityMbnbgfr#difdkConnfdt
     * @dfprfdbtfd Usf DbtbgrbmSodkft instfbd for UDP trbnsport.
     */
    @Dfprfdbtfd
    publid Sodkft(InftAddrfss iost, int port, boolfbn strfbm) tirows IOExdfption {
        tiis(iost != null ? nfw InftSodkftAddrfss(iost, port) : null,
             nfw InftSodkftAddrfss(0), strfbm);
    }

    privbtf Sodkft(SodkftAddrfss bddrfss, SodkftAddrfss lodblAddr,
                   boolfbn strfbm) tirows IOExdfption {
        sftImpl();

        // bbdkwbrd dompbtibility
        if (bddrfss == null)
            tirow nfw NullPointfrExdfption();

        try {
            drfbtfImpl(strfbm);
            if (lodblAddr != null)
                bind(lodblAddr);
            donnfdt(bddrfss);
        } dbtdi (IOExdfption | IllfgblArgumfntExdfption | SfdurityExdfption f) {
            try {
                dlosf();
            } dbtdi (IOExdfption df) {
                f.bddSupprfssfd(df);
            }
            tirow f;
        }
    }

    /**
     * Crfbtfs tif sodkft implfmfntbtion.
     *
     * @pbrbm strfbm b {@dodf boolfbn} vbluf : {@dodf truf} for b TCP sodkft,
     *               {@dodf fblsf} for UDP.
     * @tirows IOExdfption if drfbtion fbils
     * @sindf 1.4
     */
     void drfbtfImpl(boolfbn strfbm) tirows SodkftExdfption {
        if (impl == null)
            sftImpl();
        try {
            impl.drfbtf(strfbm);
            drfbtfd = truf;
        } dbtdi (IOExdfption f) {
            tirow nfw SodkftExdfption(f.gftMfssbgf());
        }
    }

    privbtf void difdkOldImpl() {
        if (impl == null)
            rfturn;
        // SodkftImpl.donnfdt() is b protfdtfd mftiod, tifrfforf wf nffd to usf
        // gftDfdlbrfdMftiod, tifrfforf wf nffd pfrmission to bddfss tif mfmbfr

        oldImpl = AddfssControllfr.doPrivilfgfd
                                (nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                Clbss<?> dlbzz = impl.gftClbss();
                wiilf (truf) {
                    try {
                        dlbzz.gftDfdlbrfdMftiod("donnfdt", SodkftAddrfss.dlbss, int.dlbss);
                        rfturn Boolfbn.FALSE;
                    } dbtdi (NoSudiMftiodExdfption f) {
                        dlbzz = dlbzz.gftSupfrdlbss();
                        // jbvb.nft.SodkftImpl dlbss will blwbys ibvf tiis bbstrbdt mftiod.
                        // If wf ibvf not found it by now in tif iifrbrdiy tifn it dofs not
                        // fxist, wf brf bn old stylf impl.
                        if (dlbzz.fqubls(jbvb.nft.SodkftImpl.dlbss)) {
                            rfturn Boolfbn.TRUE;
                        }
                    }
                }
            }
        });
    }

    /**
     * Sfts impl to tif systfm-dffbult typf of SodkftImpl.
     * @sindf 1.4
     */
    void sftImpl() {
        if (fbdtory != null) {
            impl = fbdtory.drfbtfSodkftImpl();
            difdkOldImpl();
        } flsf {
            // No nffd to do b difdkOldImpl() ifrf, wf know it's bn up to dbtf
            // SodkftImpl!
            impl = nfw SodksSodkftImpl();
        }
        if (impl != null)
            impl.sftSodkft(tiis);
    }


    /**
     * Gft tif {@dodf SodkftImpl} bttbdifd to tiis sodkft, drfbting
     * it if nfdfssbry.
     *
     * @rfturn  tif {@dodf SodkftImpl} bttbdifd to tibt SfrvfrSodkft.
     * @tirows SodkftExdfption if drfbtion fbils
     * @sindf 1.4
     */
    SodkftImpl gftImpl() tirows SodkftExdfption {
        if (!drfbtfd)
            drfbtfImpl(truf);
        rfturn impl;
    }

    /**
     * Connfdts tiis sodkft to tif sfrvfr.
     *
     * @pbrbm   fndpoint tif {@dodf SodkftAddrfss}
     * @tirows  IOExdfption if bn frror oddurs during tif donnfdtion
     * @tirows  jbvb.nio.dibnnfls.IllfgblBlodkingModfExdfption
     *          if tiis sodkft ibs bn bssodibtfd dibnnfl,
     *          bnd tif dibnnfl is in non-blodking modf
     * @tirows  IllfgblArgumfntExdfption if fndpoint is null or is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid void donnfdt(SodkftAddrfss fndpoint) tirows IOExdfption {
        donnfdt(fndpoint, 0);
    }

    /**
     * Connfdts tiis sodkft to tif sfrvfr witi b spfdififd timfout vbluf.
     * A timfout of zfro is intfrprftfd bs bn infinitf timfout. Tif donnfdtion
     * will tifn blodk until fstbblisifd or bn frror oddurs.
     *
     * @pbrbm   fndpoint tif {@dodf SodkftAddrfss}
     * @pbrbm   timfout  tif timfout vbluf to bf usfd in millisfdonds.
     * @tirows  IOExdfption if bn frror oddurs during tif donnfdtion
     * @tirows  SodkftTimfoutExdfption if timfout fxpirfs bfforf donnfdting
     * @tirows  jbvb.nio.dibnnfls.IllfgblBlodkingModfExdfption
     *          if tiis sodkft ibs bn bssodibtfd dibnnfl,
     *          bnd tif dibnnfl is in non-blodking modf
     * @tirows  IllfgblArgumfntExdfption if fndpoint is null or is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid void donnfdt(SodkftAddrfss fndpoint, int timfout) tirows IOExdfption {
        if (fndpoint == null)
            tirow nfw IllfgblArgumfntExdfption("donnfdt: Tif bddrfss dbn't bf null");

        if (timfout < 0)
          tirow nfw IllfgblArgumfntExdfption("donnfdt: timfout dbn't bf nfgbtivf");

        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");

        if (!oldImpl && isConnfdtfd())
            tirow nfw SodkftExdfption("blrfbdy donnfdtfd");

        if (!(fndpoint instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");

        InftSodkftAddrfss fpoint = (InftSodkftAddrfss) fndpoint;
        InftAddrfss bddr = fpoint.gftAddrfss ();
        int port = fpoint.gftPort();
        difdkAddrfss(bddr, "donnfdt");

        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            if (fpoint.isUnrfsolvfd())
                sfdurity.difdkConnfdt(fpoint.gftHostNbmf(), port);
            flsf
                sfdurity.difdkConnfdt(bddr.gftHostAddrfss(), port);
        }
        if (!drfbtfd)
            drfbtfImpl(truf);
        if (!oldImpl)
            impl.donnfdt(fpoint, timfout);
        flsf if (timfout == 0) {
            if (fpoint.isUnrfsolvfd())
                impl.donnfdt(bddr.gftHostNbmf(), port);
            flsf
                impl.donnfdt(bddr, port);
        } flsf
            tirow nfw UnsupportfdOpfrbtionExdfption("SodkftImpl.donnfdt(bddr, timfout)");
        donnfdtfd = truf;
        /*
         * If tif sodkft wbs not bound bfforf tif donnfdt, it is now bfdbusf
         * tif kfrnfl will ibvf pidkfd bn fpifmfrbl port & b lodbl bddrfss
         */
        bound = truf;
    }

    /**
     * Binds tif sodkft to b lodbl bddrfss.
     * <P>
     * If tif bddrfss is {@dodf null}, tifn tif systfm will pidk up
     * bn fpifmfrbl port bnd b vblid lodbl bddrfss to bind tif sodkft.
     *
     * @pbrbm   bindpoint tif {@dodf SodkftAddrfss} to bind to
     * @tirows  IOExdfption if tif bind opfrbtion fbils, or if tif sodkft
     *                     is blrfbdy bound.
     * @tirows  IllfgblArgumfntExdfption if bindpoint is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     * @tirows  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *          {@dodf difdkListfn} mftiod dofsn't bllow tif bind
     *          to tif lodbl port.
     *
     * @sindf   1.4
     * @sff #isBound
     */
    publid void bind(SodkftAddrfss bindpoint) tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!oldImpl && isBound())
            tirow nfw SodkftExdfption("Alrfbdy bound");

        if (bindpoint != null && (!(bindpoint instbndfof InftSodkftAddrfss)))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");
        InftSodkftAddrfss fpoint = (InftSodkftAddrfss) bindpoint;
        if (fpoint != null && fpoint.isUnrfsolvfd())
            tirow nfw SodkftExdfption("Unrfsolvfd bddrfss");
        if (fpoint == null) {
            fpoint = nfw InftSodkftAddrfss(0);
        }
        InftAddrfss bddr = fpoint.gftAddrfss();
        int port = fpoint.gftPort();
        difdkAddrfss (bddr, "bind");
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkListfn(port);
        }
        gftImpl().bind (bddr, port);
        bound = truf;
    }

    privbtf void difdkAddrfss (InftAddrfss bddr, String op) {
        if (bddr == null) {
            rfturn;
        }
        if (!(bddr instbndfof Inft4Addrfss || bddr instbndfof Inft6Addrfss)) {
            tirow nfw IllfgblArgumfntExdfption(op + ": invblid bddrfss typf");
        }
    }

    /**
     * sft tif flbgs bftfr bn bddfpt() dbll.
     */
    finbl void postAddfpt() {
        donnfdtfd = truf;
        drfbtfd = truf;
        bound = truf;
    }

    void sftCrfbtfd() {
        drfbtfd = truf;
    }

    void sftBound() {
        bound = truf;
    }

    void sftConnfdtfd() {
        donnfdtfd = truf;
    }

    /**
     * Rfturns tif bddrfss to wiidi tif sodkft is donnfdtfd.
     * <p>
     * If tif sodkft wbs donnfdtfd prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif donnfdtfd bddrfss
     * bftfr tif sodkft is dlosfd.
     *
     * @rfturn  tif rfmotf IP bddrfss to wiidi tiis sodkft is donnfdtfd,
     *          or {@dodf null} if tif sodkft is not donnfdtfd.
     */
    publid InftAddrfss gftInftAddrfss() {
        if (!isConnfdtfd())
            rfturn null;
        try {
            rfturn gftImpl().gftInftAddrfss();
        } dbtdi (SodkftExdfption f) {
        }
        rfturn null;
    }

    /**
     * Gfts tif lodbl bddrfss to wiidi tif sodkft is bound.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * tif {@link InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss is rfturnfd.
     *
     * @rfturn tif lodbl bddrfss to wiidi tif sodkft is bound,
     *         tif loopbbdk bddrfss if dfnifd by tif sfdurity mbnbgfr, or
     *         tif wilddbrd bddrfss if tif sodkft is dlosfd or not bound yft.
     * @sindf   1.1
     *
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    publid InftAddrfss gftLodblAddrfss() {
        // Tiis is for bbdkwbrd dompbtibility
        if (!isBound())
            rfturn InftAddrfss.bnyLodblAddrfss();
        InftAddrfss in = null;
        try {
            in = (InftAddrfss) gftImpl().gftOption(SodkftOptions.SO_BINDADDR);
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null)
                sm.difdkConnfdt(in.gftHostAddrfss(), -1);
            if (in.isAnyLodblAddrfss()) {
                in = InftAddrfss.bnyLodblAddrfss();
            }
        } dbtdi (SfdurityExdfption f) {
            in = InftAddrfss.gftLoopbbdkAddrfss();
        } dbtdi (Exdfption f) {
            in = InftAddrfss.bnyLodblAddrfss(); // "0.0.0.0"
        }
        rfturn in;
    }

    /**
     * Rfturns tif rfmotf port numbfr to wiidi tiis sodkft is donnfdtfd.
     * <p>
     * If tif sodkft wbs donnfdtfd prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif donnfdtfd port numbfr
     * bftfr tif sodkft is dlosfd.
     *
     * @rfturn  tif rfmotf port numbfr to wiidi tiis sodkft is donnfdtfd, or
     *          0 if tif sodkft is not donnfdtfd yft.
     */
    publid int gftPort() {
        if (!isConnfdtfd())
            rfturn 0;
        try {
            rfturn gftImpl().gftPort();
        } dbtdi (SodkftExdfption f) {
            // Siouldn't ibppfn bs wf'rf donnfdtfd
        }
        rfturn -1;
    }

    /**
     * Rfturns tif lodbl port numbfr to wiidi tiis sodkft is bound.
     * <p>
     * If tif sodkft wbs bound prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif lodbl port numbfr
     * bftfr tif sodkft is dlosfd.
     *
     * @rfturn  tif lodbl port numbfr to wiidi tiis sodkft is bound or -1
     *          if tif sodkft is not bound yft.
     */
    publid int gftLodblPort() {
        if (!isBound())
            rfturn -1;
        try {
            rfturn gftImpl().gftLodblPort();
        } dbtdi(SodkftExdfption f) {
            // siouldn't ibppfn bs wf'rf bound
        }
        rfturn -1;
    }

    /**
     * Rfturns tif bddrfss of tif fndpoint tiis sodkft is donnfdtfd to, or
     * {@dodf null} if it is undonnfdtfd.
     * <p>
     * If tif sodkft wbs donnfdtfd prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif donnfdtfd bddrfss
     * bftfr tif sodkft is dlosfd.
     *

     * @rfturn b {@dodf SodkftAddrfss} rfprfsfnting tif rfmotf fndpoint of tiis
     *         sodkft, or {@dodf null} if it is not donnfdtfd yft.
     * @sff #gftInftAddrfss()
     * @sff #gftPort()
     * @sff #donnfdt(SodkftAddrfss, int)
     * @sff #donnfdt(SodkftAddrfss)
     * @sindf 1.4
     */
    publid SodkftAddrfss gftRfmotfSodkftAddrfss() {
        if (!isConnfdtfd())
            rfturn null;
        rfturn nfw InftSodkftAddrfss(gftInftAddrfss(), gftPort());
    }

    /**
     * Rfturns tif bddrfss of tif fndpoint tiis sodkft is bound to.
     * <p>
     * If b sodkft bound to bn fndpoint rfprfsfntfd by bn
     * {@dodf InftSodkftAddrfss } is {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn bn {@dodf InftSodkftAddrfss}
     * bftfr tif sodkft is dlosfd. In tibt dbsf tif rfturnfd
     * {@dodf InftSodkftAddrfss}'s bddrfss is tif
     * {@link InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss
     * bnd its port is tif lodbl port tibt it wbs bound to.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * b {@dodf SodkftAddrfss} rfprfsfnting tif
     * {@link InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss bnd tif lodbl
     * port to wiidi tiis sodkft is bound is rfturnfd.
     *
     * @rfturn b {@dodf SodkftAddrfss} rfprfsfnting tif lodbl fndpoint of
     *         tiis sodkft, or b {@dodf SodkftAddrfss} rfprfsfnting tif
     *         loopbbdk bddrfss if dfnifd by tif sfdurity mbnbgfr, or
     *         {@dodf null} if tif sodkft is not bound yft.
     *
     * @sff #gftLodblAddrfss()
     * @sff #gftLodblPort()
     * @sff #bind(SodkftAddrfss)
     * @sff SfdurityMbnbgfr#difdkConnfdt
     * @sindf 1.4
     */

    publid SodkftAddrfss gftLodblSodkftAddrfss() {
        if (!isBound())
            rfturn null;
        rfturn nfw InftSodkftAddrfss(gftLodblAddrfss(), gftLodblPort());
    }

    /**
     * Rfturns tif uniquf {@link jbvb.nio.dibnnfls.SodkftCibnnfl SodkftCibnnfl}
     * objfdt bssodibtfd witi tiis sodkft, if bny.
     *
     * <p> A sodkft will ibvf b dibnnfl if, bnd only if, tif dibnnfl itsflf wbs
     * drfbtfd vib tif {@link jbvb.nio.dibnnfls.SodkftCibnnfl#opfn
     * SodkftCibnnfl.opfn} or {@link
     * jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl#bddfpt SfrvfrSodkftCibnnfl.bddfpt}
     * mftiods.
     *
     * @rfturn  tif sodkft dibnnfl bssodibtfd witi tiis sodkft,
     *          or {@dodf null} if tiis sodkft wbs not drfbtfd
     *          for b dibnnfl
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid SodkftCibnnfl gftCibnnfl() {
        rfturn null;
    }

    /**
     * Rfturns bn input strfbm for tiis sodkft.
     *
     * <p> If tiis sodkft ibs bn bssodibtfd dibnnfl tifn tif rfsulting input
     * strfbm dflfgbtfs bll of its opfrbtions to tif dibnnfl.  If tif dibnnfl
     * is in non-blodking modf tifn tif input strfbm's {@dodf rfbd} opfrbtions
     * will tirow bn {@link jbvb.nio.dibnnfls.IllfgblBlodkingModfExdfption}.
     *
     * <p>Undfr bbnormbl donditions tif undfrlying donnfdtion mby bf
     * brokfn by tif rfmotf iost or tif nftwork softwbrf (for fxbmplf
     * b donnfdtion rfsft in tif dbsf of TCP donnfdtions). Wifn b
     * brokfn donnfdtion is dftfdtfd by tif nftwork softwbrf tif
     * following bpplifs to tif rfturnfd input strfbm :-
     *
     * <ul>
     *
     *   <li><p>Tif nftwork softwbrf mby disdbrd bytfs tibt brf bufffrfd
     *   by tif sodkft. Bytfs tibt brfn't disdbrdfd by tif nftwork
     *   softwbrf dbn bf rfbd using {@link jbvb.io.InputStrfbm#rfbd rfbd}.
     *
     *   <li><p>If tifrf brf no bytfs bufffrfd on tif sodkft, or bll
     *   bufffrfd bytfs ibvf bffn donsumfd by
     *   {@link jbvb.io.InputStrfbm#rfbd rfbd}, tifn bll subsfqufnt
     *   dblls to {@link jbvb.io.InputStrfbm#rfbd rfbd} will tirow bn
     *   {@link jbvb.io.IOExdfption IOExdfption}.
     *
     *   <li><p>If tifrf brf no bytfs bufffrfd on tif sodkft, bnd tif
     *   sodkft ibs not bffn dlosfd using {@link #dlosf dlosf}, tifn
     *   {@link jbvb.io.InputStrfbm#bvbilbblf bvbilbblf} will
     *   rfturn {@dodf 0}.
     *
     * </ul>
     *
     * <p> Closing tif rfturnfd {@link jbvb.io.InputStrfbm InputStrfbm}
     * will dlosf tif bssodibtfd sodkft.
     *
     * @rfturn     bn input strfbm for rfbding bytfs from tiis sodkft.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif
     *             input strfbm, tif sodkft is dlosfd, tif sodkft is
     *             not donnfdtfd, or tif sodkft input ibs bffn siutdown
     *             using {@link #siutdownInput()}
     *
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    publid InputStrfbm gftInputStrfbm() tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!isConnfdtfd())
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        if (isInputSiutdown())
            tirow nfw SodkftExdfption("Sodkft input is siutdown");
        finbl Sodkft s = tiis;
        InputStrfbm is = null;
        try {
            is = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<InputStrfbm>() {
                    publid InputStrfbm run() tirows IOExdfption {
                        rfturn impl.gftInputStrfbm();
                    }
                });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption f) {
            tirow (IOExdfption) f.gftExdfption();
        }
        rfturn is;
    }

    /**
     * Rfturns bn output strfbm for tiis sodkft.
     *
     * <p> If tiis sodkft ibs bn bssodibtfd dibnnfl tifn tif rfsulting output
     * strfbm dflfgbtfs bll of its opfrbtions to tif dibnnfl.  If tif dibnnfl
     * is in non-blodking modf tifn tif output strfbm's {@dodf writf}
     * opfrbtions will tirow bn {@link
     * jbvb.nio.dibnnfls.IllfgblBlodkingModfExdfption}.
     *
     * <p> Closing tif rfturnfd {@link jbvb.io.OutputStrfbm OutputStrfbm}
     * will dlosf tif bssodibtfd sodkft.
     *
     * @rfturn     bn output strfbm for writing bytfs to tiis sodkft.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn drfbting tif
     *               output strfbm or if tif sodkft is not donnfdtfd.
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!isConnfdtfd())
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        if (isOutputSiutdown())
            tirow nfw SodkftExdfption("Sodkft output is siutdown");
        finbl Sodkft s = tiis;
        OutputStrfbm os = null;
        try {
            os = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<OutputStrfbm>() {
                    publid OutputStrfbm run() tirows IOExdfption {
                        rfturn impl.gftOutputStrfbm();
                    }
                });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption f) {
            tirow (IOExdfption) f.gftExdfption();
        }
        rfturn os;
    }

    /**
     * Enbblf/disbblf {@link SodkftOptions#TCP_NODELAY TCP_NODELAY}
     * (disbblf/fnbblf Nbglf's blgoritim).
     *
     * @pbrbm on {@dodf truf} to fnbblf TCP_NODELAY,
     * {@dodf fblsf} to disbblf.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @sindf   1.1
     *
     * @sff #gftTdpNoDflby()
     */
    publid void sftTdpNoDflby(boolfbn on) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.TCP_NODELAY, Boolfbn.vblufOf(on));
    }

    /**
     * Tfsts if {@link SodkftOptions#TCP_NODELAY TCP_NODELAY} is fnbblfd.
     *
     * @rfturn b {@dodf boolfbn} indidbting wiftifr or not
     *         {@link SodkftOptions#TCP_NODELAY TCP_NODELAY} is fnbblfd.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.1
     * @sff #sftTdpNoDflby(boolfbn)
     */
    publid boolfbn gftTdpNoDflby() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        rfturn ((Boolfbn) gftImpl().gftOption(SodkftOptions.TCP_NODELAY)).boolfbnVbluf();
    }

    /**
     * Enbblf/disbblf {@link SodkftOptions#SO_LINGER SO_LINGER} witi tif
     * spfdififd lingfr timf in sfdonds. Tif mbximum timfout vbluf is plbtform
     * spfdifid.
     *
     * Tif sftting only bfffdts sodkft dlosf.
     *
     * @pbrbm on     wiftifr or not to lingfr on.
     * @pbrbm lingfr iow long to lingfr for, if on is truf.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @fxdfption IllfgblArgumfntExdfption if tif lingfr vbluf is nfgbtivf.
     * @sindf 1.1
     * @sff #gftSoLingfr()
     */
    publid void sftSoLingfr(boolfbn on, int lingfr) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!on) {
            gftImpl().sftOption(SodkftOptions.SO_LINGER, on);
        } flsf {
            if (lingfr < 0) {
                tirow nfw IllfgblArgumfntExdfption("invblid vbluf for SO_LINGER");
            }
            if (lingfr > 65535)
                lingfr = 65535;
            gftImpl().sftOption(SodkftOptions.SO_LINGER, lingfr);
        }
    }

    /**
     * Rfturns sftting for {@link SodkftOptions#SO_LINGER SO_LINGER}.
     * -1 rfturns implifs tibt tif
     * option is disbblfd.
     *
     * Tif sftting only bfffdts sodkft dlosf.
     *
     * @rfturn tif sftting for {@link SodkftOptions#SO_LINGER SO_LINGER}.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.1
     * @sff #sftSoLingfr(boolfbn, int)
     */
    publid int gftSoLingfr() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        Objfdt o = gftImpl().gftOption(SodkftOptions.SO_LINGER);
        if (o instbndfof Intfgfr) {
            rfturn ((Intfgfr) o).intVbluf();
        } flsf {
            rfturn -1;
        }
    }

    /**
     * Sfnd onf bytf of urgfnt dbtb on tif sodkft. Tif bytf to bf sfnt is tif lowfst figit
     * bits of tif dbtb pbrbmftfr. Tif urgfnt bytf is
     * sfnt bftfr bny prfdfding writfs to tif sodkft OutputStrfbm
     * bnd bfforf bny futurf writfs to tif OutputStrfbm.
     * @pbrbm dbtb Tif bytf of dbtb to sfnd
     * @fxdfption IOExdfption if tifrf is bn frror
     *  sfnding tif dbtb.
     * @sindf 1.4
     */
    publid void sfndUrgfntDbtb (int dbtb) tirows IOExdfption  {
        if (!gftImpl().supportsUrgfntDbtb ()) {
            tirow nfw SodkftExdfption ("Urgfnt dbtb not supportfd");
        }
        gftImpl().sfndUrgfntDbtb (dbtb);
    }

    /**
     * Enbblf/disbblf {@link SodkftOptions#SO_OOBINLINE SO_OOBINLINE}
     * (rfdfipt of TCP urgfnt dbtb)
     *
     * By dffbult, tiis option is disbblfd bnd TCP urgfnt dbtb rfdfivfd on b
     * sodkft is silfntly disdbrdfd. If tif usfr wisifs to rfdfivf urgfnt dbtb, tifn
     * tiis option must bf fnbblfd. Wifn fnbblfd, urgfnt dbtb is rfdfivfd
     * inlinf witi normbl dbtb.
     * <p>
     * Notf, only limitfd support is providfd for ibndling indoming urgfnt
     * dbtb. In pbrtidulbr, no notifidbtion of indoming urgfnt dbtb is providfd
     * bnd tifrf is no dbpbbility to distinguisi bftwffn normbl dbtb bnd urgfnt
     * dbtb unlfss providfd by b iigifr lfvfl protodol.
     *
     * @pbrbm on {@dodf truf} to fnbblf
     *           {@link SodkftOptions#SO_OOBINLINE SO_OOBINLINE},
     *           {@dodf fblsf} to disbblf.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @sindf   1.4
     *
     * @sff #gftOOBInlinf()
     */
    publid void sftOOBInlinf(boolfbn on) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_OOBINLINE, Boolfbn.vblufOf(on));
    }

    /**
     * Tfsts if {@link SodkftOptions#SO_OOBINLINE SO_OOBINLINE} is fnbblfd.
     *
     * @rfturn b {@dodf boolfbn} indidbting wiftifr or not
     *         {@link SodkftOptions#SO_OOBINLINE SO_OOBINLINE}is fnbblfd.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.4
     * @sff #sftOOBInlinf(boolfbn)
     */
    publid boolfbn gftOOBInlinf() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        rfturn ((Boolfbn) gftImpl().gftOption(SodkftOptions.SO_OOBINLINE)).boolfbnVbluf();
    }

    /**
     *  Enbblf/disbblf {@link SodkftOptions#SO_TIMEOUT SO_TIMEOUT}
     *  witi tif spfdififd timfout, in millisfdonds. Witi tiis option sft
     *  to b non-zfro timfout, b rfbd() dbll on tif InputStrfbm bssodibtfd witi
     *  tiis Sodkft will blodk for only tiis bmount of timf.  If tif timfout
     *  fxpirfs, b <B>jbvb.nft.SodkftTimfoutExdfption</B> is rbisfd, tiougi tif
     *  Sodkft is still vblid. Tif option <B>must</B> bf fnbblfd
     *  prior to fntfring tif blodking opfrbtion to ibvf ffffdt. Tif
     *  timfout must bf {@dodf > 0}.
     *  A timfout of zfro is intfrprftfd bs bn infinitf timfout.
     *
     * @pbrbm timfout tif spfdififd timfout, in millisfdonds.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.1
     * @sff #gftSoTimfout()
     */
    publid syndironizfd void sftSoTimfout(int timfout) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (timfout < 0)
          tirow nfw IllfgblArgumfntExdfption("timfout dbn't bf nfgbtivf");

        gftImpl().sftOption(SodkftOptions.SO_TIMEOUT, timfout);
    }

    /**
     * Rfturns sftting for {@link SodkftOptions#SO_TIMEOUT SO_TIMEOUT}.
     * 0 rfturns implifs tibt tif option is disbblfd (i.f., timfout of infinity).
     *
     * @rfturn tif sftting for {@link SodkftOptions#SO_TIMEOUT SO_TIMEOUT}
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @sindf   1.1
     * @sff #sftSoTimfout(int)
     */
    publid syndironizfd int gftSoTimfout() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        Objfdt o = gftImpl().gftOption(SodkftOptions.SO_TIMEOUT);
        /* fxtrb typf sbffty */
        if (o instbndfof Intfgfr) {
            rfturn ((Intfgfr) o).intVbluf();
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Sfts tif {@link SodkftOptions#SO_SNDBUF SO_SNDBUF} option to tif
     * spfdififd vbluf for tiis {@dodf Sodkft}.
     * Tif {@link SodkftOptions#SO_SNDBUF SO_SNDBUF} option is usfd by tif
     * plbtform's nftworking dodf bs b iint for tif sizf to sft tif undfrlying
     * nftwork I/O bufffrs.
     *
     * <p>Bfdbusf {@link SodkftOptions#SO_SNDBUF SO_SNDBUF} is b iint,
     * bpplidbtions tibt wbnt to vfrify wibt sizf tif bufffrs wfrf sft to
     * siould dbll {@link #gftSfndBufffrSizf()}.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @pbrbm sizf tif sizf to wiidi to sft tif sfnd bufffr
     * sizf. Tiis vbluf must bf grfbtfr tibn 0.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif
     * vbluf is 0 or is nfgbtivf.
     *
     * @sff #gftSfndBufffrSizf()
     * @sindf 1.2
     */
    publid syndironizfd void sftSfndBufffrSizf(int sizf)
    tirows SodkftExdfption{
        if (!(sizf > 0)) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf sfnd sizf");
        }
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_SNDBUF, sizf);
    }

    /**
     * Gft vbluf of tif {@link SodkftOptions#SO_SNDBUF SO_SNDBUF} option
     * for tiis {@dodf Sodkft}, tibt is tif bufffr sizf usfd by tif plbtform
     * for output on tiis {@dodf Sodkft}.
     * @rfturn tif vbluf of tif {@link SodkftOptions#SO_SNDBUF SO_SNDBUF}
     *         option for tiis {@dodf Sodkft}.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @sff #sftSfndBufffrSizf(int)
     * @sindf 1.2
     */
    publid syndironizfd int gftSfndBufffrSizf() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        int rfsult = 0;
        Objfdt o = gftImpl().gftOption(SodkftOptions.SO_SNDBUF);
        if (o instbndfof Intfgfr) {
            rfsult = ((Intfgfr)o).intVbluf();
        }
        rfturn rfsult;
    }

    /**
     * Sfts tif {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} option to tif
     * spfdififd vbluf for tiis {@dodf Sodkft}. Tif
     * {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} option is
     * usfd by tif plbtform's nftworking dodf bs b iint for tif sizf to sft
     * tif undfrlying nftwork I/O bufffrs.
     *
     * <p>Indrfbsing tif rfdfivf bufffr sizf dbn indrfbsf tif pfrformbndf of
     * nftwork I/O for iigi-volumf donnfdtion, wiilf dfdrfbsing it dbn
     * iflp rfdudf tif bbdklog of indoming dbtb.
     *
     * <p>Bfdbusf {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} is b iint,
     * bpplidbtions tibt wbnt to vfrify wibt sizf tif bufffrs wfrf sft to
     * siould dbll {@link #gftRfdfivfBufffrSizf()}.
     *
     * <p>Tif vbluf of {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} is blso usfd
     * to sft tif TCP rfdfivf window tibt is bdvfrtizfd to tif rfmotf pffr.
     * Gfnfrblly, tif window sizf dbn bf modififd bt bny timf wifn b sodkft is
     * donnfdtfd. Howfvfr, if b rfdfivf window lbrgfr tibn 64K is rfquirfd tifn
     * tiis must bf rfqufstfd <B>bfforf</B> tif sodkft is donnfdtfd to tif
     * rfmotf pffr. Tifrf brf two dbsfs to bf bwbrf of:
     * <ol>
     * <li>For sodkfts bddfptfd from b SfrvfrSodkft, tiis must bf donf by dblling
     * {@link SfrvfrSodkft#sftRfdfivfBufffrSizf(int)} bfforf tif SfrvfrSodkft
     * is bound to b lodbl bddrfss.</li>
     * <li>For dlifnt sodkfts, sftRfdfivfBufffrSizf() must bf dbllfd bfforf
     * donnfdting tif sodkft to its rfmotf pffr.</li></ol>
     * @pbrbm sizf tif sizf to wiidi to sft tif rfdfivf bufffr
     * sizf. Tiis vbluf must bf grfbtfr tibn 0.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif vbluf is 0 or is
     * nfgbtivf.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @sff #gftRfdfivfBufffrSizf()
     * @sff SfrvfrSodkft#sftRfdfivfBufffrSizf(int)
     * @sindf 1.2
     */
    publid syndironizfd void sftRfdfivfBufffrSizf(int sizf)
    tirows SodkftExdfption{
        if (sizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid rfdfivf sizf");
        }
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_RCVBUF, sizf);
    }

    /**
     * Gfts tif vbluf of tif {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} option
     * for tiis {@dodf Sodkft}, tibt is tif bufffr sizf usfd by tif plbtform
     * for input on tiis {@dodf Sodkft}.
     *
     * @rfturn tif vbluf of tif {@link SodkftOptions#SO_RCVBUF SO_RCVBUF}
     *         option for tiis {@dodf Sodkft}.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sff #sftRfdfivfBufffrSizf(int)
     * @sindf 1.2
     */
    publid syndironizfd int gftRfdfivfBufffrSizf()
    tirows SodkftExdfption{
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        int rfsult = 0;
        Objfdt o = gftImpl().gftOption(SodkftOptions.SO_RCVBUF);
        if (o instbndfof Intfgfr) {
            rfsult = ((Intfgfr)o).intVbluf();
        }
        rfturn rfsult;
    }

    /**
     * Enbblf/disbblf {@link SodkftOptions#SO_KEEPALIVE SO_KEEPALIVE}.
     *
     * @pbrbm on  wiftifr or not to ibvf sodkft kffp blivf turnfd on.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf 1.3
     * @sff #gftKffpAlivf()
     */
    publid void sftKffpAlivf(boolfbn on) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_KEEPALIVE, Boolfbn.vblufOf(on));
    }

    /**
     * Tfsts if {@link SodkftOptions#SO_KEEPALIVE SO_KEEPALIVE} is fnbblfd.
     *
     * @rfturn b {@dodf boolfbn} indidbting wiftifr or not
     *         {@link SodkftOptions#SO_KEEPALIVE SO_KEEPALIVE} is fnbblfd.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.3
     * @sff #sftKffpAlivf(boolfbn)
     */
    publid boolfbn gftKffpAlivf() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        rfturn ((Boolfbn) gftImpl().gftOption(SodkftOptions.SO_KEEPALIVE)).boolfbnVbluf();
    }

    /**
     * Sfts trbffid dlbss or typf-of-sfrvidf odtft in tif IP
     * ifbdfr for pbdkfts sfnt from tiis Sodkft.
     * As tif undfrlying nftwork implfmfntbtion mby ignorf tiis
     * vbluf bpplidbtions siould donsidfr it b iint.
     *
     * <P> Tif td <B>must</B> bf in tif rbngf {@dodf 0 <= td <=
     * 255} or bn IllfgblArgumfntExdfption will bf tirown.
     * <p>Notfs:
     * <p>For Intfrnft Protodol v4 tif vbluf donsists of bn
     * {@dodf intfgfr}, tif lfbst signifidbnt 8 bits of wiidi
     * rfprfsfnt tif vbluf of tif TOS odtft in IP pbdkfts sfnt by
     * tif sodkft.
     * RFC 1349 dffinfs tif TOS vblufs bs follows:
     *
     * <UL>
     * <LI><CODE>IPTOS_LOWCOST (0x02)</CODE></LI>
     * <LI><CODE>IPTOS_RELIABILITY (0x04)</CODE></LI>
     * <LI><CODE>IPTOS_THROUGHPUT (0x08)</CODE></LI>
     * <LI><CODE>IPTOS_LOWDELAY (0x10)</CODE></LI>
     * </UL>
     * Tif lbst low ordfr bit is blwbys ignorfd bs tiis
     * dorrfsponds to tif MBZ (must bf zfro) bit.
     * <p>
     * Sftting bits in tif prfdfdfndf fifld mby rfsult in b
     * SodkftExdfption indidbting tibt tif opfrbtion is not
     * pfrmittfd.
     * <p>
     * As RFC 1122 sfdtion 4.2.4.2 indidbtfs, b domplibnt TCP
     * implfmfntbtion siould, but is not rfquirfd to, lft bpplidbtion
     * dibngf tif TOS fifld during tif lifftimf of b donnfdtion.
     * So wiftifr tif typf-of-sfrvidf fifld dbn bf dibngfd bftfr tif
     * TCP donnfdtion ibs bffn fstbblisifd dfpfnds on tif implfmfntbtion
     * in tif undfrlying plbtform. Applidbtions siould not bssumf tibt
     * tify dbn dibngf tif TOS fifld bftfr tif donnfdtion.
     * <p>
     * For Intfrnft Protodol v6 {@dodf td} is tif vbluf tibt
     * would bf plbdfd into tif sin6_flowinfo fifld of tif IP ifbdfr.
     *
     * @pbrbm td        bn {@dodf int} vbluf for tif bitsft.
     * @tirows SodkftExdfption if tifrf is bn frror sftting tif
     * trbffid dlbss or typf-of-sfrvidf
     * @sindf 1.4
     * @sff #gftTrbffidClbss
     * @sff SodkftOptions#IP_TOS
     */
    publid void sftTrbffidClbss(int td) tirows SodkftExdfption {
        if (td < 0 || td > 255)
            tirow nfw IllfgblArgumfntExdfption("td is not in rbngf 0 -- 255");

        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.IP_TOS, td);
    }

    /**
     * Gfts trbffid dlbss or typf-of-sfrvidf in tif IP ifbdfr
     * for pbdkfts sfnt from tiis Sodkft
     * <p>
     * As tif undfrlying nftwork implfmfntbtion mby ignorf tif
     * trbffid dlbss or typf-of-sfrvidf sft using {@link #sftTrbffidClbss(int)}
     * tiis mftiod mby rfturn b difffrfnt vbluf tibn wbs prfviously
     * sft using tif {@link #sftTrbffidClbss(int)} mftiod on tiis Sodkft.
     *
     * @rfturn tif trbffid dlbss or typf-of-sfrvidf blrfbdy sft
     * @tirows SodkftExdfption if tifrf is bn frror obtbining tif
     * trbffid dlbss or typf-of-sfrvidf vbluf.
     * @sindf 1.4
     * @sff #sftTrbffidClbss(int)
     * @sff SodkftOptions#IP_TOS
     */
    publid int gftTrbffidClbss() tirows SodkftExdfption {
        rfturn ((Intfgfr) (gftImpl().gftOption(SodkftOptions.IP_TOS))).intVbluf();
    }

    /**
     * Enbblf/disbblf tif {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR}
     * sodkft option.
     * <p>
     * Wifn b TCP donnfdtion is dlosfd tif donnfdtion mby rfmbin
     * in b timfout stbtf for b pfriod of timf bftfr tif donnfdtion
     * is dlosfd (typidblly known bs tif {@dodf TIME_WAIT} stbtf
     * or {@dodf 2MSL} wbit stbtf).
     * For bpplidbtions using b wfll known sodkft bddrfss or port
     * it mby not bf possiblf to bind b sodkft to tif rfquirfd
     * {@dodf SodkftAddrfss} if tifrf is b donnfdtion in tif
     * timfout stbtf involving tif sodkft bddrfss or port.
     * <p>
     * Enbbling {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR}
     * prior to binding tif sodkft using {@link #bind(SodkftAddrfss)} bllows
     * tif sodkft to bf bound fvfn tiougi b prfvious donnfdtion is in b timfout
     * stbtf.
     * <p>
     * Wifn b {@dodf Sodkft} is drfbtfd tif initibl sftting
     * of {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR} is disbblfd.
     * <p>
     * Tif bfibviour wifn {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR} is
     * fnbblfd or disbblfd bftfr b sodkft is bound (Sff {@link #isBound()})
     * is not dffinfd.
     *
     * @pbrbm on  wiftifr to fnbblf or disbblf tif sodkft option
     * @fxdfption SodkftExdfption if bn frror oddurs fnbbling or
     *            disbbling tif {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR}
     *            sodkft option, or tif sodkft is dlosfd.
     * @sindf 1.4
     * @sff #gftRfusfAddrfss()
     * @sff #bind(SodkftAddrfss)
     * @sff #isClosfd()
     * @sff #isBound()
     */
    publid void sftRfusfAddrfss(boolfbn on) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_REUSEADDR, Boolfbn.vblufOf(on));
    }

    /**
     * Tfsts if {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR} is fnbblfd.
     *
     * @rfturn b {@dodf boolfbn} indidbting wiftifr or not
     *         {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR} is fnbblfd.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.4
     * @sff #sftRfusfAddrfss(boolfbn)
     */
    publid boolfbn gftRfusfAddrfss() tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        rfturn ((Boolfbn) (gftImpl().gftOption(SodkftOptions.SO_REUSEADDR))).boolfbnVbluf();
    }

    /**
     * Closfs tiis sodkft.
     * <p>
     * Any tirfbd durrfntly blodkfd in bn I/O opfrbtion upon tiis sodkft
     * will tirow b {@link SodkftExdfption}.
     * <p>
     * Ondf b sodkft ibs bffn dlosfd, it is not bvbilbblf for furtifr nftworking
     * usf (i.f. dbn't bf rfdonnfdtfd or rfbound). A nfw sodkft nffds to bf
     * drfbtfd.
     *
     * <p> Closing tiis sodkft will blso dlosf tif sodkft's
     * {@link jbvb.io.InputStrfbm InputStrfbm} bnd
     * {@link jbvb.io.OutputStrfbm OutputStrfbm}.
     *
     * <p> If tiis sodkft ibs bn bssodibtfd dibnnfl tifn tif dibnnfl is dlosfd
     * bs wfll.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn dlosing tiis sodkft.
     * @rfvisfd 1.4
     * @spfd JSR-51
     * @sff #isClosfd
     */
    publid syndironizfd void dlosf() tirows IOExdfption {
        syndironizfd(dlosfLodk) {
            if (isClosfd())
                rfturn;
            if (drfbtfd)
                impl.dlosf();
            dlosfd = truf;
        }
    }

    /**
     * Plbdfs tif input strfbm for tiis sodkft bt "fnd of strfbm".
     * Any dbtb sfnt to tif input strfbm sidf of tif sodkft is bdknowlfdgfd
     * bnd tifn silfntly disdbrdfd.
     * <p>
     * If you rfbd from b sodkft input strfbm bftfr invoking tiis mftiod on tif
     * sodkft, tif strfbm's {@dodf bvbilbblf} mftiod will rfturn 0, bnd its
     * {@dodf rfbd} mftiods will rfturn {@dodf -1} (fnd of strfbm).
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs wifn siutting down tiis
     * sodkft.
     *
     * @sindf 1.3
     * @sff jbvb.nft.Sodkft#siutdownOutput()
     * @sff jbvb.nft.Sodkft#dlosf()
     * @sff jbvb.nft.Sodkft#sftSoLingfr(boolfbn, int)
     * @sff #isInputSiutdown
     */
    publid void siutdownInput() tirows IOExdfption
    {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!isConnfdtfd())
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        if (isInputSiutdown())
            tirow nfw SodkftExdfption("Sodkft input is blrfbdy siutdown");
        gftImpl().siutdownInput();
        siutIn = truf;
    }

    /**
     * Disbblfs tif output strfbm for tiis sodkft.
     * For b TCP sodkft, bny prfviously writtfn dbtb will bf sfnt
     * followfd by TCP's normbl donnfdtion tfrminbtion sfqufndf.
     *
     * If you writf to b sodkft output strfbm bftfr invoking
     * siutdownOutput() on tif sodkft, tif strfbm will tirow
     * bn IOExdfption.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs wifn siutting down tiis
     * sodkft.
     *
     * @sindf 1.3
     * @sff jbvb.nft.Sodkft#siutdownInput()
     * @sff jbvb.nft.Sodkft#dlosf()
     * @sff jbvb.nft.Sodkft#sftSoLingfr(boolfbn, int)
     * @sff #isOutputSiutdown
     */
    publid void siutdownOutput() tirows IOExdfption
    {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!isConnfdtfd())
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        if (isOutputSiutdown())
            tirow nfw SodkftExdfption("Sodkft output is blrfbdy siutdown");
        gftImpl().siutdownOutput();
        siutOut = truf;
    }

    /**
     * Convfrts tiis sodkft to b {@dodf String}.
     *
     * @rfturn  b string rfprfsfntbtion of tiis sodkft.
     */
    publid String toString() {
        try {
            if (isConnfdtfd())
                rfturn "Sodkft[bddr=" + gftImpl().gftInftAddrfss() +
                    ",port=" + gftImpl().gftPort() +
                    ",lodblport=" + gftImpl().gftLodblPort() + "]";
        } dbtdi (SodkftExdfption f) {
        }
        rfturn "Sodkft[undonnfdtfd]";
    }

    /**
     * Rfturns tif donnfdtion stbtf of tif sodkft.
     * <p>
     * Notf: Closing b sodkft dofsn't dlfbr its donnfdtion stbtf, wiidi mfbns
     * tiis mftiod will rfturn {@dodf truf} for b dlosfd sodkft
     * (sff {@link #isClosfd()}) if it wbs suddfssfuly donnfdtfd prior
     * to bfing dlosfd.
     *
     * @rfturn truf if tif sodkft wbs suddfssfuly donnfdtfd to b sfrvfr
     * @sindf 1.4
     */
    publid boolfbn isConnfdtfd() {
        // Bfforf 1.3 Sodkfts wfrf blwbys donnfdtfd during drfbtion
        rfturn donnfdtfd || oldImpl;
    }

    /**
     * Rfturns tif binding stbtf of tif sodkft.
     * <p>
     * Notf: Closing b sodkft dofsn't dlfbr its binding stbtf, wiidi mfbns
     * tiis mftiod will rfturn {@dodf truf} for b dlosfd sodkft
     * (sff {@link #isClosfd()}) if it wbs suddfssfuly bound prior
     * to bfing dlosfd.
     *
     * @rfturn truf if tif sodkft wbs suddfssfuly bound to bn bddrfss
     * @sindf 1.4
     * @sff #bind
     */
    publid boolfbn isBound() {
        // Bfforf 1.3 Sodkfts wfrf blwbys bound during drfbtion
        rfturn bound || oldImpl;
    }

    /**
     * Rfturns tif dlosfd stbtf of tif sodkft.
     *
     * @rfturn truf if tif sodkft ibs bffn dlosfd
     * @sindf 1.4
     * @sff #dlosf
     */
    publid boolfbn isClosfd() {
        syndironizfd(dlosfLodk) {
            rfturn dlosfd;
        }
    }

    /**
     * Rfturns wiftifr tif rfbd-iblf of tif sodkft donnfdtion is dlosfd.
     *
     * @rfturn truf if tif input of tif sodkft ibs bffn siutdown
     * @sindf 1.4
     * @sff #siutdownInput
     */
    publid boolfbn isInputSiutdown() {
        rfturn siutIn;
    }

    /**
     * Rfturns wiftifr tif writf-iblf of tif sodkft donnfdtion is dlosfd.
     *
     * @rfturn truf if tif output of tif sodkft ibs bffn siutdown
     * @sindf 1.4
     * @sff #siutdownOutput
     */
    publid boolfbn isOutputSiutdown() {
        rfturn siutOut;
    }

    /**
     * Tif fbdtory for bll dlifnt sodkfts.
     */
    privbtf stbtid SodkftImplFbdtory fbdtory = null;

    /**
     * Sfts tif dlifnt sodkft implfmfntbtion fbdtory for tif
     * bpplidbtion. Tif fbdtory dbn bf spfdififd only ondf.
     * <p>
     * Wifn bn bpplidbtion drfbtfs b nfw dlifnt sodkft, tif sodkft
     * implfmfntbtion fbdtory's {@dodf drfbtfSodkftImpl} mftiod is
     * dbllfd to drfbtf tif bdtubl sodkft implfmfntbtion.
     * <p>
     * Pbssing {@dodf null} to tif mftiod is b no-op unlfss tif fbdtory
     * wbs blrfbdy sft.
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first dblls
     * tif sfdurity mbnbgfr's {@dodf difdkSftFbdtory} mftiod
     * to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm      fbd   tif dfsirfd fbdtory.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn sftting tif
     *               sodkft fbdtory.
     * @fxdfption  SodkftExdfption  if tif fbdtory is blrfbdy dffinfd.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkSftFbdtory} mftiod dofsn't bllow tif opfrbtion.
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        SfdurityMbnbgfr#difdkSftFbdtory
     */
    publid stbtid syndironizfd void sftSodkftImplFbdtory(SodkftImplFbdtory fbd)
        tirows IOExdfption
    {
        if (fbdtory != null) {
            tirow nfw SodkftExdfption("fbdtory blrfbdy dffinfd");
        }
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkSftFbdtory();
        }
        fbdtory = fbd;
    }

    /**
     * Sfts pfrformbndf prfffrfndfs for tiis sodkft.
     *
     * <p> Sodkfts usf tif TCP/IP protodol by dffbult.  Somf implfmfntbtions
     * mby offfr bltfrnbtivf protodols wiidi ibvf difffrfnt pfrformbndf
     * dibrbdtfristids tibn TCP/IP.  Tiis mftiod bllows tif bpplidbtion to
     * fxprfss its own prfffrfndfs bs to iow tifsf trbdfoffs siould bf mbdf
     * wifn tif implfmfntbtion dioosfs from tif bvbilbblf protodols.
     *
     * <p> Pfrformbndf prfffrfndfs brf dfsdribfd by tirff intfgfrs
     * wiosf vblufs indidbtf tif rflbtivf importbndf of siort donnfdtion timf,
     * low lbtfndy, bnd iigi bbndwidti.  Tif bbsolutf vblufs of tif intfgfrs
     * brf irrflfvbnt; in ordfr to dioosf b protodol tif vblufs brf simply
     * dompbrfd, witi lbrgfr vblufs indidbting strongfr prfffrfndfs. Nfgbtivf
     * vblufs rfprfsfnt b lowfr priority tibn positivf vblufs. If tif
     * bpplidbtion prfffrs siort donnfdtion timf ovfr boti low lbtfndy bnd iigi
     * bbndwidti, for fxbmplf, tifn it dould invokf tiis mftiod witi tif vblufs
     * {@dodf (1, 0, 0)}.  If tif bpplidbtion prfffrs iigi bbndwidti bbovf low
     * lbtfndy, bnd low lbtfndy bbovf siort donnfdtion timf, tifn it dould
     * invokf tiis mftiod witi tif vblufs {@dodf (0, 1, 2)}.
     *
     * <p> Invoking tiis mftiod bftfr tiis sodkft ibs bffn donnfdtfd
     * will ibvf no ffffdt.
     *
     * @pbrbm  donnfdtionTimf
     *         An {@dodf int} fxprfssing tif rflbtivf importbndf of b siort
     *         donnfdtion timf
     *
     * @pbrbm  lbtfndy
     *         An {@dodf int} fxprfssing tif rflbtivf importbndf of low
     *         lbtfndy
     *
     * @pbrbm  bbndwidti
     *         An {@dodf int} fxprfssing tif rflbtivf importbndf of iigi
     *         bbndwidti
     *
     * @sindf 1.5
     */
    publid void sftPfrformbndfPrfffrfndfs(int donnfdtionTimf,
                                          int lbtfndy,
                                          int bbndwidti)
    {
        /* Not implfmfntfd yft */
    }


    /**
     * Sfts tif vbluf of b sodkft option.
     *
     * @pbrbm nbmf Tif sodkft option
     * @pbrbm vbluf Tif vbluf of tif sodkft option. A vbluf of {@dodf null}
     *              mby bf vblid for somf options.
     * @rfturn tiis Sodkft
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IllfgblArgumfntExdfption if tif vbluf is not vblid for
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs, or if tif sodkft is dlosfd.
     *
     * @tirows NullPointfrExdfption if nbmf is {@dodf null}
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd if tif sodkft
     *         option rfquirfs b sfdurity pfrmission bnd if tif dbllfr dofs
     *         not ibvf tif rfquirfd pfrmission.
     *         {@link jbvb.nft.StbndbrdSodkftOptions StbndbrdSodkftOptions}
     *         do not rfquirf bny sfdurity pfrmission.
     *
     * @sindf 1.9
     */
    publid <T> Sodkft sftOption(SodkftOption<T> nbmf, T vbluf) tirows IOExdfption {
        gftImpl().sftOption(nbmf, vbluf);
        rfturn tiis;
    }

    /**
     * Rfturns tif vbluf of b sodkft option.
     *
     * @pbrbm nbmf Tif sodkft option
     *
     * @rfturn Tif vbluf of tif sodkft option.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs, or if tif sodkft is dlosfd.
     *
     * @tirows NullPointfrExdfption if nbmf is {@dodf null}
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd if tif sodkft
     *         option rfquirfs b sfdurity pfrmission bnd if tif dbllfr dofs
     *         not ibvf tif rfquirfd pfrmission.
     *         {@link jbvb.nft.StbndbrdSodkftOptions StbndbrdSodkftOptions}
     *         do not rfquirf bny sfdurity pfrmission.
     *
     * @sindf 1.9
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T gftOption(SodkftOption<T> nbmf) tirows IOExdfption {
        rfturn gftImpl().gftOption(nbmf);
    }

    privbtf stbtid Sft<SodkftOption<?>> options;
    privbtf stbtid boolfbn optionsSft = fblsf;

    /**
     * Rfturns b sft of tif sodkft options supportfd by tiis sodkft.
     *
     * Tiis mftiod will dontinuf to rfturn tif sft of options fvfn bftfr
     * tif sodkft ibs bffn dlosfd.
     *
     * @rfturn A sft of tif sodkft options supportfd by tiis sodkft. Tiis sft
     *         mby bf fmpty if tif sodkft's SodkftImpl dbnnot bf drfbtfd.
     *
     * @sindf 1.9
     */
    publid Sft<SodkftOption<?>> supportfdOptions() {
        syndironizfd (Sodkft.dlbss) {
            if (optionsSft) {
                rfturn options;
            }
            try {
                SodkftImpl impl = gftImpl();
                options = Collfdtions.unmodifibblfSft(impl.supportfdOptions());
            } dbtdi (IOExdfption f) {
                options = Collfdtions.fmptySft();
            }
            optionsSft = truf;
            rfturn options;
        }
    }
}
