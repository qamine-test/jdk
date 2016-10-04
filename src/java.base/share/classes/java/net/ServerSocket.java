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

import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.Sft;
import jbvb.util.Collfdtions;

/**
 * Tiis dlbss implfmfnts sfrvfr sodkfts. A sfrvfr sodkft wbits for
 * rfqufsts to domf in ovfr tif nftwork. It pfrforms somf opfrbtion
 * bbsfd on tibt rfqufst, bnd tifn possibly rfturns b rfsult to tif rfqufstfr.
 * <p>
 * Tif bdtubl work of tif sfrvfr sodkft is pfrformfd by bn instbndf
 * of tif {@dodf SodkftImpl} dlbss. An bpplidbtion dbn
 * dibngf tif sodkft fbdtory tibt drfbtfs tif sodkft
 * implfmfntbtion to donfigurf itsflf to drfbtf sodkfts
 * bppropribtf to tif lodbl firfwbll.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.nft.SodkftImpl
 * @sff     jbvb.nft.SfrvfrSodkft#sftSodkftFbdtory(jbvb.nft.SodkftImplFbdtory)
 * @sff     jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl
 * @sindf   1.0
 */
publid
dlbss SfrvfrSodkft implfmfnts jbvb.io.Closfbblf {
    /**
     * Vbrious stbtfs of tiis sodkft.
     */
    privbtf boolfbn drfbtfd = fblsf;
    privbtf boolfbn bound = fblsf;
    privbtf boolfbn dlosfd = fblsf;
    privbtf Objfdt dlosfLodk = nfw Objfdt();

    /**
     * Tif implfmfntbtion of tiis Sodkft.
     */
    privbtf SodkftImpl impl;

    /**
     * Arf wf using bn oldfr SodkftImpl?
     */
    privbtf boolfbn oldImpl = fblsf;

    /**
     * Pbdkbgf-privbtf donstrudtor to drfbtf b SfrvfrSodkft bssodibtfd witi
     * tif givfn SodkftImpl.
     */
    SfrvfrSodkft(SodkftImpl impl) {
        tiis.impl = impl;
        impl.sftSfrvfrSodkft(tiis);
    }

    /**
     * Crfbtfs bn unbound sfrvfr sodkft.
     *
     * @fxdfption IOExdfption IO frror wifn opfning tif sodkft.
     * @rfvisfd 1.4
     */
    publid SfrvfrSodkft() tirows IOExdfption {
        sftImpl();
    }

    /**
     * Crfbtfs b sfrvfr sodkft, bound to tif spfdififd port. A port numbfr
     * of {@dodf 0} mfbns tibt tif port numbfr is butombtidblly
     * bllodbtfd, typidblly from bn fpifmfrbl port rbngf. Tiis port
     * numbfr dbn tifn bf rftrifvfd by dblling {@link #gftLodblPort gftLodblPort}.
     * <p>
     * Tif mbximum qufuf lfngti for indoming donnfdtion indidbtions (b
     * rfqufst to donnfdt) is sft to {@dodf 50}. If b donnfdtion
     * indidbtion brrivfs wifn tif qufuf is full, tif donnfdtion is rffusfd.
     * <p>
     * If tif bpplidbtion ibs spfdififd b sfrvfr sodkft fbdtory, tibt
     * fbdtory's {@dodf drfbtfSodkftImpl} mftiod is dbllfd to drfbtf
     * tif bdtubl sodkft implfmfntbtion. Otifrwisf b "plbin" sodkft is drfbtfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr,
     * its {@dodf difdkListfn} mftiod is dbllfd
     * witi tif {@dodf port} brgumfnt
     * bs its brgumfnt to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     *
     * @pbrbm      port  tif port numbfr, or {@dodf 0} to usf b port
     *                   numbfr tibt is butombtidblly bllodbtfd.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn opfning tif sodkft.
     * @fxdfption  SfdurityExdfption
     * if b sfdurity mbnbgfr fxists bnd its {@dodf difdkListfn}
     * mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     *
     * @sff        jbvb.nft.SodkftImpl
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        jbvb.nft.SfrvfrSodkft#sftSodkftFbdtory(jbvb.nft.SodkftImplFbdtory)
     * @sff        SfdurityMbnbgfr#difdkListfn
     */
    publid SfrvfrSodkft(int port) tirows IOExdfption {
        tiis(port, 50, null);
    }

    /**
     * Crfbtfs b sfrvfr sodkft bnd binds it to tif spfdififd lodbl port
     * numbfr, witi tif spfdififd bbdklog.
     * A port numbfr of {@dodf 0} mfbns tibt tif port numbfr is
     * butombtidblly bllodbtfd, typidblly from bn fpifmfrbl port rbngf.
     * Tiis port numbfr dbn tifn bf rftrifvfd by dblling
     * {@link #gftLodblPort gftLodblPort}.
     * <p>
     * Tif mbximum qufuf lfngti for indoming donnfdtion indidbtions (b
     * rfqufst to donnfdt) is sft to tif {@dodf bbdklog} pbrbmftfr. If
     * b donnfdtion indidbtion brrivfs wifn tif qufuf is full, tif
     * donnfdtion is rffusfd.
     * <p>
     * If tif bpplidbtion ibs spfdififd b sfrvfr sodkft fbdtory, tibt
     * fbdtory's {@dodf drfbtfSodkftImpl} mftiod is dbllfd to drfbtf
     * tif bdtubl sodkft implfmfntbtion. Otifrwisf b "plbin" sodkft is drfbtfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr,
     * its {@dodf difdkListfn} mftiod is dbllfd
     * witi tif {@dodf port} brgumfnt
     * bs its brgumfnt to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * Tif {@dodf bbdklog} brgumfnt is tif rfqufstfd mbximum numbfr of
     * pfnding donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion
     * spfdifid. In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti
     * or mby dioosf to ignorf tif pbrbmftfr bltogtifr. Tif vbluf providfd
     * siould bf grfbtfr tibn {@dodf 0}. If it is lfss tibn or fqubl to
     * {@dodf 0}, tifn bn implfmfntbtion spfdifid dffbult will bf usfd.
     *
     * @pbrbm      port     tif port numbfr, or {@dodf 0} to usf b port
     *                      numbfr tibt is butombtidblly bllodbtfd.
     * @pbrbm      bbdklog  rfqufstfd mbximum lfngti of tif qufuf of indoming
     *                      donnfdtions.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn opfning tif sodkft.
     * @fxdfption  SfdurityExdfption
     * if b sfdurity mbnbgfr fxists bnd its {@dodf difdkListfn}
     * mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     *
     * @sff        jbvb.nft.SodkftImpl
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        jbvb.nft.SfrvfrSodkft#sftSodkftFbdtory(jbvb.nft.SodkftImplFbdtory)
     * @sff        SfdurityMbnbgfr#difdkListfn
     */
    publid SfrvfrSodkft(int port, int bbdklog) tirows IOExdfption {
        tiis(port, bbdklog, null);
    }

    /**
     * Crfbtf b sfrvfr witi tif spfdififd port, listfn bbdklog, bnd
     * lodbl IP bddrfss to bind to.  Tif <i>bindAddr</i> brgumfnt
     * dbn bf usfd on b multi-iomfd iost for b SfrvfrSodkft tibt
     * will only bddfpt donnfdt rfqufsts to onf of its bddrfssfs.
     * If <i>bindAddr</i> is null, it will dffbult bddfpting
     * donnfdtions on bny/bll lodbl bddrfssfs.
     * Tif port must bf bftwffn 0 bnd 65535, indlusivf.
     * A port numbfr of {@dodf 0} mfbns tibt tif port numbfr is
     * butombtidblly bllodbtfd, typidblly from bn fpifmfrbl port rbngf.
     * Tiis port numbfr dbn tifn bf rftrifvfd by dblling
     * {@link #gftLodblPort gftLodblPort}.
     *
     * <P>If tifrf is b sfdurity mbnbgfr, tiis mftiod
     * dblls its {@dodf difdkListfn} mftiod
     * witi tif {@dodf port} brgumfnt
     * bs its brgumfnt to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * Tif {@dodf bbdklog} brgumfnt is tif rfqufstfd mbximum numbfr of
     * pfnding donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion
     * spfdifid. In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti
     * or mby dioosf to ignorf tif pbrbmftfr bltogtifr. Tif vbluf providfd
     * siould bf grfbtfr tibn {@dodf 0}. If it is lfss tibn or fqubl to
     * {@dodf 0}, tifn bn implfmfntbtion spfdifid dffbult will bf usfd.
     *
     * @pbrbm port  tif port numbfr, or {@dodf 0} to usf b port
     *              numbfr tibt is butombtidblly bllodbtfd.
     * @pbrbm bbdklog rfqufstfd mbximum lfngti of tif qufuf of indoming
     *                donnfdtions.
     * @pbrbm bindAddr tif lodbl InftAddrfss tif sfrvfr will bind to
     *
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     * its {@dodf difdkListfn} mftiod dofsn't bllow tif opfrbtion.
     *
     * @tirows  IOExdfption if bn I/O frror oddurs wifn opfning tif sodkft.
     * @fxdfption  IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf
     *             tif spfdififd rbngf of vblid port vblufs, wiidi is bftwffn
     *             0 bnd 65535, indlusivf.
     *
     * @sff SodkftOptions
     * @sff SodkftImpl
     * @sff SfdurityMbnbgfr#difdkListfn
     * @sindf   1.1
     */
    publid SfrvfrSodkft(int port, int bbdklog, InftAddrfss bindAddr) tirows IOExdfption {
        sftImpl();
        if (port < 0 || port > 0xFFFF)
            tirow nfw IllfgblArgumfntExdfption(
                       "Port vbluf out of rbngf: " + port);
        if (bbdklog < 1)
          bbdklog = 50;
        try {
            bind(nfw InftSodkftAddrfss(bindAddr, port), bbdklog);
        } dbtdi(SfdurityExdfption f) {
            dlosf();
            tirow f;
        } dbtdi(IOExdfption f) {
            dlosf();
            tirow f;
        }
    }

    /**
     * Gft tif {@dodf SodkftImpl} bttbdifd to tiis sodkft, drfbting
     * it if nfdfssbry.
     *
     * @rfturn  tif {@dodf SodkftImpl} bttbdifd to tibt SfrvfrSodkft.
     * @tirows SodkftExdfption if drfbtion fbils.
     * @sindf 1.4
     */
    SodkftImpl gftImpl() tirows SodkftExdfption {
        if (!drfbtfd)
            drfbtfImpl();
        rfturn impl;
    }

    privbtf void difdkOldImpl() {
        if (impl == null)
            rfturn;
        // SodkftImpl.donnfdt() is b protfdtfd mftiod, tifrfforf wf nffd to usf
        // gftDfdlbrfdMftiod, tifrfforf wf nffd pfrmission to bddfss tif mfmbfr
        try {
            AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows NoSudiMftiodExdfption {
                        impl.gftClbss().gftDfdlbrfdMftiod("donnfdt",
                                                          SodkftAddrfss.dlbss,
                                                          int.dlbss);
                        rfturn null;
                    }
                });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption f) {
            oldImpl = truf;
        }
    }

    privbtf void sftImpl() {
        if (fbdtory != null) {
            impl = fbdtory.drfbtfSodkftImpl();
            difdkOldImpl();
        } flsf {
            // No nffd to do b difdkOldImpl() ifrf, wf know it's bn up to dbtf
            // SodkftImpl!
            impl = nfw SodksSodkftImpl();
        }
        if (impl != null)
            impl.sftSfrvfrSodkft(tiis);
    }

    /**
     * Crfbtfs tif sodkft implfmfntbtion.
     *
     * @tirows IOExdfption if drfbtion fbils
     * @sindf 1.4
     */
    void drfbtfImpl() tirows SodkftExdfption {
        if (impl == null)
            sftImpl();
        try {
            impl.drfbtf(truf);
            drfbtfd = truf;
        } dbtdi (IOExdfption f) {
            tirow nfw SodkftExdfption(f.gftMfssbgf());
        }
    }

    /**
     *
     * Binds tif {@dodf SfrvfrSodkft} to b spfdifid bddrfss
     * (IP bddrfss bnd port numbfr).
     * <p>
     * If tif bddrfss is {@dodf null}, tifn tif systfm will pidk up
     * bn fpifmfrbl port bnd b vblid lodbl bddrfss to bind tif sodkft.
     *
     * @pbrbm   fndpoint        Tif IP bddrfss bnd port numbfr to bind to.
     * @tirows  IOExdfption if tif bind opfrbtion fbils, or if tif sodkft
     *                     is blrfbdy bound.
     * @tirows  SfdurityExdfption       if b {@dodf SfdurityMbnbgfr} is prfsfnt bnd
     * its {@dodf difdkListfn} mftiod dofsn't bllow tif opfrbtion.
     * @tirows  IllfgblArgumfntExdfption if fndpoint is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     * @sindf 1.4
     */
    publid void bind(SodkftAddrfss fndpoint) tirows IOExdfption {
        bind(fndpoint, 50);
    }

    /**
     *
     * Binds tif {@dodf SfrvfrSodkft} to b spfdifid bddrfss
     * (IP bddrfss bnd port numbfr).
     * <p>
     * If tif bddrfss is {@dodf null}, tifn tif systfm will pidk up
     * bn fpifmfrbl port bnd b vblid lodbl bddrfss to bind tif sodkft.
     * <P>
     * Tif {@dodf bbdklog} brgumfnt is tif rfqufstfd mbximum numbfr of
     * pfnding donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion
     * spfdifid. In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti
     * or mby dioosf to ignorf tif pbrbmftfr bltogtifr. Tif vbluf providfd
     * siould bf grfbtfr tibn {@dodf 0}. If it is lfss tibn or fqubl to
     * {@dodf 0}, tifn bn implfmfntbtion spfdifid dffbult will bf usfd.
     * @pbrbm   fndpoint        Tif IP bddrfss bnd port numbfr to bind to.
     * @pbrbm   bbdklog         rfqufstfd mbximum lfngti of tif qufuf of
     *                          indoming donnfdtions.
     * @tirows  IOExdfption if tif bind opfrbtion fbils, or if tif sodkft
     *                     is blrfbdy bound.
     * @tirows  SfdurityExdfption       if b {@dodf SfdurityMbnbgfr} is prfsfnt bnd
     * its {@dodf difdkListfn} mftiod dofsn't bllow tif opfrbtion.
     * @tirows  IllfgblArgumfntExdfption if fndpoint is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     * @sindf 1.4
     */
    publid void bind(SodkftAddrfss fndpoint, int bbdklog) tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!oldImpl && isBound())
            tirow nfw SodkftExdfption("Alrfbdy bound");
        if (fndpoint == null)
            fndpoint = nfw InftSodkftAddrfss(0);
        if (!(fndpoint instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");
        InftSodkftAddrfss fpoint = (InftSodkftAddrfss) fndpoint;
        if (fpoint.isUnrfsolvfd())
            tirow nfw SodkftExdfption("Unrfsolvfd bddrfss");
        if (bbdklog < 1)
          bbdklog = 50;
        try {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null)
                sfdurity.difdkListfn(fpoint.gftPort());
            gftImpl().bind(fpoint.gftAddrfss(), fpoint.gftPort());
            gftImpl().listfn(bbdklog);
            bound = truf;
        } dbtdi(SfdurityExdfption f) {
            bound = fblsf;
            tirow f;
        } dbtdi(IOExdfption f) {
            bound = fblsf;
            tirow f;
        }
    }

    /**
     * Rfturns tif lodbl bddrfss of tiis sfrvfr sodkft.
     * <p>
     * If tif sodkft wbs bound prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif lodbl bddrfss
     * bftfr tif sodkft is dlosfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * tif {@link InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss is rfturnfd.
     *
     * @rfturn  tif bddrfss to wiidi tiis sodkft is bound,
     *          or tif loopbbdk bddrfss if dfnifd by tif sfdurity mbnbgfr,
     *          or {@dodf null} if tif sodkft is unbound.
     *
     * @sff SfdurityMbnbgfr#difdkConnfdt
     */
    publid InftAddrfss gftInftAddrfss() {
        if (!isBound())
            rfturn null;
        try {
            InftAddrfss in = gftImpl().gftInftAddrfss();
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null)
                sm.difdkConnfdt(in.gftHostAddrfss(), -1);
            rfturn in;
        } dbtdi (SfdurityExdfption f) {
            rfturn InftAddrfss.gftLoopbbdkAddrfss();
        } dbtdi (SodkftExdfption f) {
            // notiing
            // If wf'rf bound, tif impl ibs bffn drfbtfd
            // so wf siouldn't gft ifrf
        }
        rfturn null;
    }

    /**
     * Rfturns tif port numbfr on wiidi tiis sodkft is listfning.
     * <p>
     * If tif sodkft wbs bound prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif port numbfr
     * bftfr tif sodkft is dlosfd.
     *
     * @rfturn  tif port numbfr to wiidi tiis sodkft is listfning or
     *          -1 if tif sodkft is not bound yft.
     */
    publid int gftLodblPort() {
        if (!isBound())
            rfturn -1;
        try {
            rfturn gftImpl().gftLodblPort();
        } dbtdi (SodkftExdfption f) {
            // notiing
            // If wf'rf bound, tif impl ibs bffn drfbtfd
            // so wf siouldn't gft ifrf
        }
        rfturn -1;
    }

    /**
     * Rfturns tif bddrfss of tif fndpoint tiis sodkft is bound to.
     * <p>
     * If tif sodkft wbs bound prior to bfing {@link #dlosf dlosfd},
     * tifn tiis mftiod will dontinuf to rfturn tif bddrfss of tif fndpoint
     * bftfr tif sodkft is dlosfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * b {@dodf SodkftAddrfss} rfprfsfnting tif
     * {@link InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss bnd tif lodbl
     * port to wiidi tif sodkft is bound is rfturnfd.
     *
     * @rfturn b {@dodf SodkftAddrfss} rfprfsfnting tif lodbl fndpoint of
     *         tiis sodkft, or b {@dodf SodkftAddrfss} rfprfsfnting tif
     *         loopbbdk bddrfss if dfnifd by tif sfdurity mbnbgfr,
     *         or {@dodf null} if tif sodkft is not bound yft.
     *
     * @sff #gftInftAddrfss()
     * @sff #gftLodblPort()
     * @sff #bind(SodkftAddrfss)
     * @sff SfdurityMbnbgfr#difdkConnfdt
     * @sindf 1.4
     */

    publid SodkftAddrfss gftLodblSodkftAddrfss() {
        if (!isBound())
            rfturn null;
        rfturn nfw InftSodkftAddrfss(gftInftAddrfss(), gftLodblPort());
    }

    /**
     * Listfns for b donnfdtion to bf mbdf to tiis sodkft bnd bddfpts
     * it. Tif mftiod blodks until b donnfdtion is mbdf.
     *
     * <p>A nfw Sodkft {@dodf s} is drfbtfd bnd, if tifrf
     * is b sfdurity mbnbgfr,
     * tif sfdurity mbnbgfr's {@dodf difdkAddfpt} mftiod is dbllfd
     * witi {@dodf s.gftInftAddrfss().gftHostAddrfss()} bnd
     * {@dodf s.gftPort()}
     * bs its brgumfnts to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn wbiting for b
     *               donnfdtion.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkAddfpt} mftiod dofsn't bllow tif opfrbtion.
     * @fxdfption  SodkftTimfoutExdfption if b timfout wbs prfviously sft witi sftSoTimfout bnd
     *             tif timfout ibs bffn rfbdifd.
     * @fxdfption  jbvb.nio.dibnnfls.IllfgblBlodkingModfExdfption
     *             if tiis sodkft ibs bn bssodibtfd dibnnfl, tif dibnnfl is in
     *             non-blodking modf, bnd tifrf is no donnfdtion rfbdy to bf
     *             bddfptfd
     *
     * @rfturn tif nfw Sodkft
     * @sff SfdurityMbnbgfr#difdkAddfpt
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    publid Sodkft bddfpt() tirows IOExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!isBound())
            tirow nfw SodkftExdfption("Sodkft is not bound yft");
        Sodkft s = nfw Sodkft((SodkftImpl) null);
        implAddfpt(s);
        rfturn s;
    }

    /**
     * Subdlbssfs of SfrvfrSodkft usf tiis mftiod to ovfrridf bddfpt()
     * to rfturn tifir own subdlbss of sodkft.  So b FooSfrvfrSodkft
     * will typidblly ibnd tiis mftiod bn <i>fmpty</i> FooSodkft.  On
     * rfturn from implAddfpt tif FooSodkft will bf donnfdtfd to b dlifnt.
     *
     * @pbrbm s tif Sodkft
     * @tirows jbvb.nio.dibnnfls.IllfgblBlodkingModfExdfption
     *         if tiis sodkft ibs bn bssodibtfd dibnnfl,
     *         bnd tif dibnnfl is in non-blodking modf
     * @tirows IOExdfption if bn I/O frror oddurs wifn wbiting
     * for b donnfdtion.
     * @sindf   1.1
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    protfdtfd finbl void implAddfpt(Sodkft s) tirows IOExdfption {
        SodkftImpl si = null;
        try {
            if (s.impl == null)
              s.sftImpl();
            flsf {
                s.impl.rfsft();
            }
            si = s.impl;
            s.impl = null;
            si.bddrfss = nfw InftAddrfss();
            si.fd = nfw FilfDfsdriptor();
            gftImpl().bddfpt(si);

            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkAddfpt(si.gftInftAddrfss().gftHostAddrfss(),
                                     si.gftPort());
            }
        } dbtdi (IOExdfption f) {
            if (si != null)
                si.rfsft();
            s.impl = si;
            tirow f;
        } dbtdi (SfdurityExdfption f) {
            if (si != null)
                si.rfsft();
            s.impl = si;
            tirow f;
        }
        s.impl = si;
        s.postAddfpt();
    }

    /**
     * Closfs tiis sodkft.
     *
     * Any tirfbd durrfntly blodkfd in {@link #bddfpt()} will tirow
     * b {@link SodkftExdfption}.
     *
     * <p> If tiis sodkft ibs bn bssodibtfd dibnnfl tifn tif dibnnfl is dlosfd
     * bs wfll.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn dlosing tif sodkft.
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    publid void dlosf() tirows IOExdfption {
        syndironizfd(dlosfLodk) {
            if (isClosfd())
                rfturn;
            if (drfbtfd)
                impl.dlosf();
            dlosfd = truf;
        }
    }

    /**
     * Rfturns tif uniquf {@link jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl} objfdt
     * bssodibtfd witi tiis sodkft, if bny.
     *
     * <p> A sfrvfr sodkft will ibvf b dibnnfl if, bnd only if, tif dibnnfl
     * itsflf wbs drfbtfd vib tif {@link
     * jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl#opfn SfrvfrSodkftCibnnfl.opfn}
     * mftiod.
     *
     * @rfturn  tif sfrvfr-sodkft dibnnfl bssodibtfd witi tiis sodkft,
     *          or {@dodf null} if tiis sodkft wbs not drfbtfd
     *          for b dibnnfl
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid SfrvfrSodkftCibnnfl gftCibnnfl() {
        rfturn null;
    }

    /**
     * Rfturns tif binding stbtf of tif SfrvfrSodkft.
     *
     * @rfturn truf if tif SfrvfrSodkft suddfssfully bound to bn bddrfss
     * @sindf 1.4
     */
    publid boolfbn isBound() {
        // Bfforf 1.3 SfrvfrSodkfts wfrf blwbys bound during drfbtion
        rfturn bound || oldImpl;
    }

    /**
     * Rfturns tif dlosfd stbtf of tif SfrvfrSodkft.
     *
     * @rfturn truf if tif sodkft ibs bffn dlosfd
     * @sindf 1.4
     */
    publid boolfbn isClosfd() {
        syndironizfd(dlosfLodk) {
            rfturn dlosfd;
        }
    }

    /**
     * Enbblf/disbblf {@link SodkftOptions#SO_TIMEOUT SO_TIMEOUT} witi tif
     * spfdififd timfout, in millisfdonds.  Witi tiis option sft to b non-zfro
     * timfout, b dbll to bddfpt() for tiis SfrvfrSodkft
     * will blodk for only tiis bmount of timf.  If tif timfout fxpirfs,
     * b <B>jbvb.nft.SodkftTimfoutExdfption</B> is rbisfd, tiougi tif
     * SfrvfrSodkft is still vblid.  Tif option <B>must</B> bf fnbblfd
     * prior to fntfring tif blodking opfrbtion to ibvf ffffdt.  Tif
     * timfout must bf {@dodf > 0}.
     * A timfout of zfro is intfrprftfd bs bn infinitf timfout.
     * @pbrbm timfout tif spfdififd timfout, in millisfdonds
     * @fxdfption SodkftExdfption if tifrf is bn frror in
     * tif undfrlying protodol, sudi bs b TCP frror.
     * @sindf   1.1
     * @sff #gftSoTimfout()
     */
    publid syndironizfd void sftSoTimfout(int timfout) tirows SodkftExdfption {
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_TIMEOUT, timfout);
    }

    /**
     * Rftrifvf sftting for {@link SodkftOptions#SO_TIMEOUT SO_TIMEOUT}.
     * 0 rfturns implifs tibt tif option is disbblfd (i.f., timfout of infinity).
     * @rfturn tif {@link SodkftOptions#SO_TIMEOUT SO_TIMEOUT} vbluf
     * @fxdfption IOExdfption if bn I/O frror oddurs
     * @sindf   1.1
     * @sff #sftSoTimfout(int)
     */
    publid syndironizfd int gftSoTimfout() tirows IOExdfption {
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
     * Enbbling {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR} prior to
     * binding tif sodkft using {@link #bind(SodkftAddrfss)} bllows tif sodkft
     * to bf bound fvfn tiougi b prfvious donnfdtion is in b timfout stbtf.
     * <p>
     * Wifn b {@dodf SfrvfrSodkft} is drfbtfd tif initibl sftting
     * of {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR} is not dffinfd.
     * Applidbtions dbn usf {@link #gftRfusfAddrfss()} to dftfrminf tif initibl
     * sftting of {@link SodkftOptions#SO_REUSEADDR SO_REUSEADDR}.
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
     * @sff #isBound()
     * @sff #isClosfd()
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
     * Rfturns tif implfmfntbtion bddrfss bnd implfmfntbtion port of
     * tiis sodkft bs b {@dodf String}.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * bn {@dodf InftAddrfss} rfprfsfnting tif
     * {@link InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss is rfturnfd bs
     * tif implfmfntbtion bddrfss.
     *
     * @rfturn  b string rfprfsfntbtion of tiis sodkft.
     */
    publid String toString() {
        if (!isBound())
            rfturn "SfrvfrSodkft[unbound]";
        InftAddrfss in;
        if (Systfm.gftSfdurityMbnbgfr() != null)
            in = InftAddrfss.gftLoopbbdkAddrfss();
        flsf
            in = impl.gftInftAddrfss();
        rfturn "SfrvfrSodkft[bddr=" + in +
                ",lodblport=" + impl.gftLodblPort()  + "]";
    }

    void sftBound() {
        bound = truf;
    }

    void sftCrfbtfd() {
        drfbtfd = truf;
    }

    /**
     * Tif fbdtory for bll sfrvfr sodkfts.
     */
    privbtf stbtid SodkftImplFbdtory fbdtory = null;

    /**
     * Sfts tif sfrvfr sodkft implfmfntbtion fbdtory for tif
     * bpplidbtion. Tif fbdtory dbn bf spfdififd only ondf.
     * <p>
     * Wifn bn bpplidbtion drfbtfs b nfw sfrvfr sodkft, tif sodkft
     * implfmfntbtion fbdtory's {@dodf drfbtfSodkftImpl} mftiod is
     * dbllfd to drfbtf tif bdtubl sodkft implfmfntbtion.
     * <p>
     * Pbssing {@dodf null} to tif mftiod is b no-op unlfss tif fbdtory
     * wbs blrfbdy sft.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, tiis mftiod first dblls
     * tif sfdurity mbnbgfr's {@dodf difdkSftFbdtory} mftiod
     * to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm      fbd   tif dfsirfd fbdtory.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn sftting tif
     *               sodkft fbdtory.
     * @fxdfption  SodkftExdfption  if tif fbdtory ibs blrfbdy bffn dffinfd.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkSftFbdtory} mftiod dofsn't bllow tif opfrbtion.
     * @sff        jbvb.nft.SodkftImplFbdtory#drfbtfSodkftImpl()
     * @sff        SfdurityMbnbgfr#difdkSftFbdtory
     */
    publid stbtid syndironizfd void sftSodkftFbdtory(SodkftImplFbdtory fbd) tirows IOExdfption {
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
     * Sfts b dffbult proposfd vbluf for tif
     * {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} option for sodkfts
     * bddfptfd from tiis {@dodf SfrvfrSodkft}. Tif vbluf bdtublly sft
     * in tif bddfptfd sodkft must bf dftfrminfd by dblling
     * {@link Sodkft#gftRfdfivfBufffrSizf()} bftfr tif sodkft
     * is rfturnfd by {@link #bddfpt()}.
     * <p>
     * Tif vbluf of {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} is usfd boti to
     * sft tif sizf of tif intfrnbl sodkft rfdfivf bufffr, bnd to sft tif sizf
     * of tif TCP rfdfivf window tibt is bdvfrtizfd to tif rfmotf pffr.
     * <p>
     * It is possiblf to dibngf tif vbluf subsfqufntly, by dblling
     * {@link Sodkft#sftRfdfivfBufffrSizf(int)}. Howfvfr, if tif bpplidbtion
     * wisifs to bllow b rfdfivf window lbrgfr tibn 64K bytfs, bs dffinfd by RFC1323
     * tifn tif proposfd vbluf must bf sft in tif SfrvfrSodkft <B>bfforf</B>
     * it is bound to b lodbl bddrfss. Tiis implifs, tibt tif SfrvfrSodkft must bf
     * drfbtfd witi tif no-brgumfnt donstrudtor, tifn sftRfdfivfBufffrSizf() must
     * bf dbllfd bnd lbstly tif SfrvfrSodkft is bound to bn bddrfss by dblling bind().
     * <p>
     * Fbilurf to do tiis will not dbusf bn frror, bnd tif bufffr sizf mby bf sft to tif
     * rfqufstfd vbluf but tif TCP rfdfivf window in sodkfts bddfptfd from
     * tiis SfrvfrSodkft will bf no lbrgfr tibn 64K bytfs.
     *
     * @fxdfption SodkftExdfption if tifrf is bn frror
     * in tif undfrlying protodol, sudi bs b TCP frror.
     *
     * @pbrbm sizf tif sizf to wiidi to sft tif rfdfivf bufffr
     * sizf. Tiis vbluf must bf grfbtfr tibn 0.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif
     * vbluf is 0 or is nfgbtivf.
     *
     * @sindf 1.4
     * @sff #gftRfdfivfBufffrSizf
     */
     publid syndironizfd void sftRfdfivfBufffrSizf (int sizf) tirows SodkftExdfption {
        if (!(sizf > 0)) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf rfdfivf sizf");
        }
        if (isClosfd())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        gftImpl().sftOption(SodkftOptions.SO_RCVBUF, sizf);
    }

    /**
     * Gfts tif vbluf of tif {@link SodkftOptions#SO_RCVBUF SO_RCVBUF} option
     * for tiis {@dodf SfrvfrSodkft}, tibt is tif proposfd bufffr sizf tibt
     * will bf usfd for Sodkfts bddfptfd from tiis {@dodf SfrvfrSodkft}.
     *
     * <p>Notf, tif vbluf bdtublly sft in tif bddfptfd sodkft is dftfrminfd by
     * dblling {@link Sodkft#gftRfdfivfBufffrSizf()}.
     * @rfturn tif vbluf of tif {@link SodkftOptions#SO_RCVBUF SO_RCVBUF}
     *         option for tiis {@dodf Sodkft}.
     * @fxdfption SodkftExdfption if tifrf is bn frror
     *            in tif undfrlying protodol, sudi bs b TCP frror.
     * @sff #sftRfdfivfBufffrSizf(int)
     * @sindf 1.4
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
     * Sfts pfrformbndf prfffrfndfs for tiis SfrvfrSodkft.
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
     * dompbrfd, witi lbrgfr vblufs indidbting strongfr prfffrfndfs.  If tif
     * bpplidbtion prfffrs siort donnfdtion timf ovfr boti low lbtfndy bnd iigi
     * bbndwidti, for fxbmplf, tifn it dould invokf tiis mftiod witi tif vblufs
     * {@dodf (1, 0, 0)}.  If tif bpplidbtion prfffrs iigi bbndwidti bbovf low
     * lbtfndy, bnd low lbtfndy bbovf siort donnfdtion timf, tifn it dould
     * invokf tiis mftiod witi tif vblufs {@dodf (0, 1, 2)}.
     *
     * <p> Invoking tiis mftiod bftfr tiis sodkft ibs bffn bound
     * will ibvf no ffffdt. Tiis implifs tibt in ordfr to usf tiis dbpbbility
     * rfquirfs tif sodkft to bf drfbtfd witi tif no-brgumfnt donstrudtor.
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
     * @rfturn tiis SfrvfrSodkft
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sfrvfr sodkft dofs not
     *         support tif option.
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
    publid <T> SfrvfrSodkft sftOption(SodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption
    {
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
     * @tirows UnsupportfdOpfrbtionExdfption if tif sfrvfr sodkft dofs not
     *         support tif option.
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
    publid <T> T gftOption(SodkftOption<T> nbmf) tirows IOExdfption {
        rfturn gftImpl().gftOption(nbmf);
    }

    privbtf stbtid Sft<SodkftOption<?>> options;
    privbtf stbtid boolfbn optionsSft = fblsf;

    /**
     * Rfturns b sft of tif sodkft options supportfd by tiis sfrvfr sodkft.
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
        syndironizfd (SfrvfrSodkft.dlbss) {
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
