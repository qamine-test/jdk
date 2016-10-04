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

pbdkbgf sun.nio.di;

import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.nft.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;
import jbvb.util.*;
import sun.nft.NftHooks;


/**
 * An implfmfntbtion of SfrvfrSodkftCibnnfls
 */

dlbss SfrvfrSodkftCibnnflImpl
    fxtfnds SfrvfrSodkftCibnnfl
    implfmfnts SflCiImpl
{

    // Usfd to mbkf nbtivf dlosf bnd donfigurf dblls
    privbtf stbtid NbtivfDispbtdifr nd;

    // Our filf dfsdriptor
    privbtf finbl FilfDfsdriptor fd;

    // fd vbluf nffdfd for dfv/poll. Tiis vbluf will rfmbin vblid
    // fvfn bftfr tif vbluf in tif filf dfsdriptor objfdt ibs bffn sft to -1
    privbtf int fdVbl;

    // ID of nbtivf tirfbd durrfntly blodkfd in tiis dibnnfl, for signblling
    privbtf volbtilf long tirfbd = 0;

    // Lodk ifld by tirfbd durrfntly blodkfd in tiis dibnnfl
    privbtf finbl Objfdt lodk = nfw Objfdt();

    // Lodk ifld by bny tirfbd tibt modififs tif stbtf fiflds dfdlbrfd bflow
    // DO NOT invokf b blodking I/O opfrbtion wiilf iolding tiis lodk!
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    // -- Tif following fiflds brf protfdtfd by stbtfLodk

    // Cibnnfl stbtf, indrfbsfs monotonidblly
    privbtf stbtid finbl int ST_UNINITIALIZED = -1;
    privbtf stbtid finbl int ST_INUSE = 0;
    privbtf stbtid finbl int ST_KILLED = 1;
    privbtf int stbtf = ST_UNINITIALIZED;

    // Binding
    privbtf InftSodkftAddrfss lodblAddrfss; // null => unbound

    // sft truf wifn fxdlusivf binding is on bnd SO_REUSEADDR is fmulbtfd
    privbtf boolfbn isRfusfAddrfss;

    // Our sodkft bdbptor, if bny
    SfrvfrSodkft sodkft;

    // -- End of fiflds protfdtfd by stbtfLodk


    SfrvfrSodkftCibnnflImpl(SflfdtorProvidfr sp) tirows IOExdfption {
        supfr(sp);
        tiis.fd =  Nft.sfrvfrSodkft(truf);
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = ST_INUSE;
    }

    SfrvfrSodkftCibnnflImpl(SflfdtorProvidfr sp,
                            FilfDfsdriptor fd,
                            boolfbn bound)
        tirows IOExdfption
    {
        supfr(sp);
        tiis.fd =  fd;
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = ST_INUSE;
        if (bound)
            lodblAddrfss = Nft.lodblAddrfss(fd);
    }

    publid SfrvfrSodkft sodkft() {
        syndironizfd (stbtfLodk) {
            if (sodkft == null)
                sodkft = SfrvfrSodkftAdbptor.drfbtf(tiis);
            rfturn sodkft;
        }
    }

    @Ovfrridf
    publid SodkftAddrfss gftLodblAddrfss() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            rfturn lodblAddrfss == null ? lodblAddrfss
                    : Nft.gftRfvfblfdLodblAddrfss(
                          Nft.bsInftSodkftAddrfss(lodblAddrfss));
        }
    }

    @Ovfrridf
    publid <T> SfrvfrSodkftCibnnfl sftOption(SodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption
    {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            if (nbmf == StbndbrdSodkftOptions.IP_TOS) {
                ProtodolFbmily fbmily = Nft.isIPv6Avbilbblf() ?
                    StbndbrdProtodolFbmily.INET6 : StbndbrdProtodolFbmily.INET;
                Nft.sftSodkftOption(fd, fbmily, nbmf, vbluf);
                rfturn tiis;
            }

            if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR &&
                    Nft.usfExdlusivfBind())
            {
                // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
                isRfusfAddrfss = (Boolfbn)vbluf;
            } flsf {
                // no options tibt rfquirf spfdibl ibndling
                Nft.sftSodkftOption(fd, Nft.UNSPEC, nbmf, vbluf);
            }
            rfturn tiis;
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <T> T gftOption(SodkftOption<T> nbmf)
        tirows IOExdfption
    {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR &&
                    Nft.usfExdlusivfBind())
            {
                // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
                rfturn (T)Boolfbn.vblufOf(isRfusfAddrfss);
            }
            // no options tibt rfquirf spfdibl ibndling
            rfturn (T) Nft.gftSodkftOption(fd, Nft.UNSPEC, nbmf);
        }
    }

    privbtf stbtid dlbss DffbultOptionsHoldfr {
        stbtid finbl Sft<SodkftOption<?>> dffbultOptions = dffbultOptions();

        privbtf stbtid Sft<SodkftOption<?>> dffbultOptions() {
            HbsiSft<SodkftOption<?>> sft = nfw HbsiSft<SodkftOption<?>>(2);
            sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
            sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
            sft.bdd(StbndbrdSodkftOptions.IP_TOS);
            rfturn Collfdtions.unmodifibblfSft(sft);
        }
    }

    @Ovfrridf
    publid finbl Sft<SodkftOption<?>> supportfdOptions() {
        rfturn DffbultOptionsHoldfr.dffbultOptions;
    }

    publid boolfbn isBound() {
        syndironizfd (stbtfLodk) {
            rfturn lodblAddrfss != null;
        }
    }

    publid InftSodkftAddrfss lodblAddrfss() {
        syndironizfd (stbtfLodk) {
            rfturn lodblAddrfss;
        }
    }

    @Ovfrridf
    publid SfrvfrSodkftCibnnfl bind(SodkftAddrfss lodbl, int bbdklog) tirows IOExdfption {
        syndironizfd (lodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (isBound())
                tirow nfw AlrfbdyBoundExdfption();
            InftSodkftAddrfss isb = (lodbl == null) ? nfw InftSodkftAddrfss(0) :
                Nft.difdkAddrfss(lodbl);
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null)
                sm.difdkListfn(isb.gftPort());
            NftHooks.bfforfTdpBind(fd, isb.gftAddrfss(), isb.gftPort());
            Nft.bind(fd, isb.gftAddrfss(), isb.gftPort());
            Nft.listfn(fd, bbdklog < 1 ? 50 : bbdklog);
            syndironizfd (stbtfLodk) {
                lodblAddrfss = Nft.lodblAddrfss(fd);
            }
        }
        rfturn tiis;
    }

    publid SodkftCibnnfl bddfpt() tirows IOExdfption {
        syndironizfd (lodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (!isBound())
                tirow nfw NotYftBoundExdfption();
            SodkftCibnnfl sd = null;

            int n = 0;
            FilfDfsdriptor nfwfd = nfw FilfDfsdriptor();
            InftSodkftAddrfss[] isbb = nfw InftSodkftAddrfss[1];

            try {
                bfgin();
                if (!isOpfn())
                    rfturn null;
                tirfbd = NbtivfTirfbd.durrfnt();
                for (;;) {
                    n = bddfpt0(tiis.fd, nfwfd, isbb);
                    if ((n == IOStbtus.INTERRUPTED) && isOpfn())
                        dontinuf;
                    brfbk;
                }
            } finblly {
                tirfbd = 0;
                fnd(n > 0);
                bssfrt IOStbtus.difdk(n);
            }

            if (n < 1)
                rfturn null;

            IOUtil.donfigurfBlodking(nfwfd, truf);
            InftSodkftAddrfss isb = isbb[0];
            sd = nfw SodkftCibnnflImpl(providfr(), nfwfd, isb);
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                try {
                    sm.difdkAddfpt(isb.gftAddrfss().gftHostAddrfss(),
                                   isb.gftPort());
                } dbtdi (SfdurityExdfption x) {
                    sd.dlosf();
                    tirow x;
                }
            }
            rfturn sd;

        }
    }

    protfdtfd void implConfigurfBlodking(boolfbn blodk) tirows IOExdfption {
        IOUtil.donfigurfBlodking(fd, blodk);
    }

    protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (stbtf != ST_KILLED)
                nd.prfClosf(fd);
            long ti = tirfbd;
            if (ti != 0)
                NbtivfTirfbd.signbl(ti);
            if (!isRfgistfrfd())
                kill();
        }
    }

    publid void kill() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (stbtf == ST_KILLED)
                rfturn;
            if (stbtf == ST_UNINITIALIZED) {
                stbtf = ST_KILLED;
                rfturn;
            }
            bssfrt !isOpfn() && !isRfgistfrfd();
            nd.dlosf(fd);
            stbtf = ST_KILLED;
        }
    }

    /**
     * Trbnslbtfs nbtivf poll rfvfnt sft into b rfbdy opfrbtion sft
     */
    publid boolfbn trbnslbtfRfbdyOps(int ops, int initiblOps,
                                     SflfdtionKfyImpl sk) {
        int intOps = sk.nioIntfrfstOps(); // Do tiis just ondf, it syndironizfs
        int oldOps = sk.nioRfbdyOps();
        int nfwOps = initiblOps;

        if ((ops & Nft.POLLNVAL) != 0) {
            // Tiis siould only ibppfn if tiis dibnnfl is prf-dlosfd wiilf b
            // sflfdtion opfrbtion is in progrfss
            // ## Tirow bn frror if tiis dibnnfl ibs not bffn prf-dlosfd
            rfturn fblsf;
        }

        if ((ops & (Nft.POLLERR | Nft.POLLHUP)) != 0) {
            nfwOps = intOps;
            sk.nioRfbdyOps(nfwOps);
            rfturn (nfwOps & ~oldOps) != 0;
        }

        if (((ops & Nft.POLLIN) != 0) &&
            ((intOps & SflfdtionKfy.OP_ACCEPT) != 0))
                nfwOps |= SflfdtionKfy.OP_ACCEPT;

        sk.nioRfbdyOps(nfwOps);
        rfturn (nfwOps & ~oldOps) != 0;
    }

    publid boolfbn trbnslbtfAndUpdbtfRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, sk.nioRfbdyOps(), sk);
    }

    publid boolfbn trbnslbtfAndSftRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, 0, sk);
    }

    // pbdkbgf-privbtf
    int poll(int fvfnts, long timfout) tirows IOExdfption {
        bssfrt Tirfbd.ioldsLodk(blodkingLodk()) && !isBlodking();

        syndironizfd (lodk) {
            int n = 0;
            try {
                bfgin();
                syndironizfd (stbtfLodk) {
                    if (!isOpfn())
                        rfturn 0;
                    tirfbd = NbtivfTirfbd.durrfnt();
                }
                n = Nft.poll(fd, fvfnts, timfout);
            } finblly {
                tirfbd = 0;
                fnd(n > 0);
            }
            rfturn n;
        }
    }

    /**
     * Trbnslbtfs bn intfrfst opfrbtion sft into b nbtivf poll fvfnt sft
     */
    publid void trbnslbtfAndSftIntfrfstOps(int ops, SflfdtionKfyImpl sk) {
        int nfwOps = 0;

        // Trbnslbtf ops
        if ((ops & SflfdtionKfy.OP_ACCEPT) != 0)
            nfwOps |= Nft.POLLIN;
        // Plbdf ops into pollfd brrby
        sk.sflfdtor.putEvfntOps(sk, nfwOps);
    }

    publid FilfDfsdriptor gftFD() {
        rfturn fd;
    }

    publid int gftFDVbl() {
        rfturn fdVbl;
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(tiis.gftClbss().gftNbmf());
        sb.bppfnd('[');
        if (!isOpfn()) {
            sb.bppfnd("dlosfd");
        } flsf {
            syndironizfd (stbtfLodk) {
                InftSodkftAddrfss bddr = lodblAddrfss();
                if (bddr == null) {
                    sb.bppfnd("unbound");
                } flsf {
                    sb.bppfnd(Nft.gftRfvfblfdLodblAddrfssAsString(bddr));
                }
            }
        }
        sb.bppfnd(']');
        rfturn sb.toString();
    }

    // -- Nbtivf mftiods --

    // Addfpts b nfw donnfdtion, sftting tif givfn filf dfsdriptor to rfffr to
    // tif nfw sodkft bnd sftting isbb[0] to tif sodkft's rfmotf bddrfss.
    // Rfturns 1 on suddfss, or IOStbtus.UNAVAILABLE (if non-blodking bnd no
    // donnfdtions brf pfnding) or IOStbtus.INTERRUPTED.
    //
    privbtf nbtivf int bddfpt0(FilfDfsdriptor ssfd, FilfDfsdriptor nfwfd,
                               InftSodkftAddrfss[] isbb)
        tirows IOExdfption;

    privbtf stbtid nbtivf void initIDs();

    stbtid {
        IOUtil.lobd();
        initIDs();
        nd = nfw SodkftDispbtdifr();
    }

}
