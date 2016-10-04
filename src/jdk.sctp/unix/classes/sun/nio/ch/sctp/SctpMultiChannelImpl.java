/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nio.di.sdtp;

import jbvb.nft.InftAddrfss;
import jbvb.nft.SodkftAddrfss;
import jbvb.nft.SodkftExdfption;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.Mbp.Entry;
import jbvb.util.Itfrbtor;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.SflfdtionKfy;
import jbvb.nio.dibnnfls.ClosfdCibnnflExdfption;
import jbvb.nio.dibnnfls.NotYftBoundExdfption;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import dom.sun.nio.sdtp.AbstrbdtNotifidbtionHbndlfr;
import dom.sun.nio.sdtp.Assodibtion;
import dom.sun.nio.sdtp.AssodibtionCibngfNotifidbtion;
import dom.sun.nio.sdtp.HbndlfrRfsult;
import dom.sun.nio.sdtp.IllfgblRfdfivfExdfption;
import dom.sun.nio.sdtp.InvblidStrfbmExdfption;
import dom.sun.nio.sdtp.IllfgblUnbindExdfption;
import dom.sun.nio.sdtp.NotifidbtionHbndlfr;
import dom.sun.nio.sdtp.MfssbgfInfo;
import dom.sun.nio.sdtp.SdtpCibnnfl;
import dom.sun.nio.sdtp.SdtpMultiCibnnfl;
import dom.sun.nio.sdtp.SdtpSodkftOption;
import sun.nio.di.DirfdtBufffr;
import sun.nio.di.NbtivfTirfbd;
import sun.nio.di.IOStbtus;
import sun.nio.di.IOUtil;
import sun.nio.di.Nft;
import sun.nio.di.PollArrbyWrbppfr;
import sun.nio.di.SflCiImpl;
import sun.nio.di.SflfdtionKfyImpl;
import sun.nio.di.Util;
import stbtid dom.sun.nio.sdtp.SdtpStbndbrdSodkftOptions.*;
import stbtid sun.nio.di.sdtp.RfsultContbinfr.*;

/**
 * An implfmfntbtion of SdtpMultiCibnnfl
 */
publid dlbss SdtpMultiCibnnflImpl fxtfnds SdtpMultiCibnnfl
    implfmfnts SflCiImpl
{
    privbtf finbl FilfDfsdriptor fd;

    privbtf finbl int fdVbl;

    /* IDs of nbtivf tirfbds doing sfnd bnd rfdfivfs, for signblling */
    privbtf volbtilf long rfdfivfrTirfbd = 0;
    privbtf volbtilf long sfndfrTirfbd = 0;

    /* Lodk ifld by durrfnt rfdfiving tirfbd */
    privbtf finbl Objfdt rfdfivfLodk = nfw Objfdt();

    /* Lodk ifld by durrfnt sfnding tirfbd */
    privbtf finbl Objfdt sfndLodk = nfw Objfdt();

    /* Lodk ifld by bny tirfbd tibt modififs tif stbtf fiflds dfdlbrfd bflow
     * DO NOT invokf b blodking I/O opfrbtion wiilf iolding tiis lodk! */
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    privbtf fnum CibnnflStbtf {
        UNINITIALIZED,
        KILLPENDING,
        KILLED,
    }

    /* -- Tif following fiflds brf protfdtfd by stbtfLodk -- */
    privbtf CibnnflStbtf stbtf = CibnnflStbtf.UNINITIALIZED;

    /* Binding: Ondf bound tif port will rfmbin donstbnt. */
    int port = -1;
    privbtf HbsiSft<InftSodkftAddrfss> lodblAddrfssfs = nfw HbsiSft<InftSodkftAddrfss>();
    /* Hbs tif dibnnfl bffn bound to tif wilddbrd bddrfss */
    privbtf boolfbn wilddbrd; /* fblsf */

    /* Kffps b mbp of bddrfssfs to bssodibtion, bnd visb vfrsb */
    privbtf HbsiMbp<SodkftAddrfss, Assodibtion> bddrfssMbp =
                         nfw HbsiMbp<SodkftAddrfss, Assodibtion>();
    privbtf HbsiMbp<Assodibtion, Sft<SodkftAddrfss>> bssodibtionMbp =
                         nfw HbsiMbp<Assodibtion, Sft<SodkftAddrfss>>();

    /* -- End of fiflds protfdtfd by stbtfLodk -- */

    /* If bn bssodibtion ibs bffn siutdown mbrk it for rfmovbl bftfr
     * tif usfr ibndlfr ibs bffn invokfd */
    privbtf finbl TirfbdLodbl<Assodibtion> bssodibtionToRfmovf =
        nfw TirfbdLodbl<Assodibtion>() {
             @Ovfrridf protfdtfd Assodibtion initiblVbluf() {
                 rfturn null;
            }
    };

    /* A notifidbtion ibndlfr dbnnot invokf rfdfivf */
    privbtf finbl TirfbdLodbl<Boolfbn> rfdfivfInvokfd =
        nfw TirfbdLodbl<Boolfbn>() {
             @Ovfrridf protfdtfd Boolfbn initiblVbluf() {
                 rfturn Boolfbn.FALSE;
            }
    };

    publid SdtpMultiCibnnflImpl(SflfdtorProvidfr providfr)
            tirows IOExdfption {
        //TODO: updbtf providfr, rfmovf publid modififr
        supfr(providfr);
        tiis.fd = SdtpNft.sodkft(fblsf /*onf-to-mbny*/);
        tiis.fdVbl = IOUtil.fdVbl(fd);
    }

    @Ovfrridf
    publid SdtpMultiCibnnfl bind(SodkftAddrfss lodbl, int bbdklog)
            tirows IOExdfption {
        syndironizfd (rfdfivfLodk) {
            syndironizfd (sfndLodk) {
                syndironizfd (stbtfLodk) {
                    fnsurfOpfn();
                    if (isBound())
                        SdtpNft.tirowAlrfbdyBoundExdfption();
                    InftSodkftAddrfss isb = (lodbl == null) ?
                        nfw InftSodkftAddrfss(0) : Nft.difdkAddrfss(lodbl);

                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null)
                        sm.difdkListfn(isb.gftPort());
                    Nft.bind(fd, isb.gftAddrfss(), isb.gftPort());

                    InftSodkftAddrfss boundIsb = Nft.lodblAddrfss(fd);
                    port = boundIsb.gftPort();
                    lodblAddrfssfs.bdd(isb);
                    if (isb.gftAddrfss().isAnyLodblAddrfss())
                        wilddbrd = truf;

                    SdtpNft.listfn(fdVbl, bbdklog < 1 ? 50 : bbdklog);
                }
            }
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid SdtpMultiCibnnfl bindAddrfss(InftAddrfss bddrfss)
            tirows IOExdfption {
        rfturn bindUnbindAddrfss(bddrfss, truf);
    }

    @Ovfrridf
    publid SdtpMultiCibnnfl unbindAddrfss(InftAddrfss bddrfss)
            tirows IOExdfption {
        rfturn bindUnbindAddrfss(bddrfss, fblsf);
    }

    privbtf SdtpMultiCibnnfl bindUnbindAddrfss(InftAddrfss bddrfss,
                                               boolfbn bdd)
            tirows IOExdfption {
        if (bddrfss == null)
            tirow nfw IllfgblArgumfntExdfption();

        syndironizfd (rfdfivfLodk) {
            syndironizfd (sfndLodk) {
                syndironizfd (stbtfLodk) {
                    if (!isOpfn())
                        tirow nfw ClosfdCibnnflExdfption();
                    if (!isBound())
                        tirow nfw NotYftBoundExdfption();
                    if (wilddbrd)
                        tirow nfw IllfgblStbtfExdfption(
                                "Cbnnot bdd or rfmovf bddrfssfs from b dibnnfl tibt is bound to tif wilddbrd bddrfss");
                    if (bddrfss.isAnyLodblAddrfss())
                        tirow nfw IllfgblArgumfntExdfption(
                                "Cbnnot bdd or rfmovf tif wilddbrd bddrfss");
                    if (bdd) {
                        for (InftSodkftAddrfss bddr : lodblAddrfssfs) {
                            if (bddr.gftAddrfss().fqubls(bddrfss)) {
                                SdtpNft.tirowAlrfbdyBoundExdfption();
                            }
                        }
                    } flsf { /*rfmoving */
                        /* Vfrify tibt tifrf is morf tibn onf bddrfss
                         * bnd tibt bddrfss is blrfbdy bound */
                        if (lodblAddrfssfs.sizf() <= 1)
                            tirow nfw IllfgblUnbindExdfption("Cbnnot rfmovf bddrfss from b dibnnfl witi only onf bddrfss bound");
                        boolfbn foundAddrfss = fblsf;
                        for (InftSodkftAddrfss bddr : lodblAddrfssfs) {
                            if (bddr.gftAddrfss().fqubls(bddrfss)) {
                                foundAddrfss = truf;
                                brfbk;
                            }
                        }
                        if (!foundAddrfss )
                            tirow nfw IllfgblUnbindExdfption("Cbnnot rfmovf bddrfss from b dibnnfl tibt is not bound to tibt bddrfss");
                    }

                    SdtpNft.bindx(fdVbl, nfw InftAddrfss[]{bddrfss}, port, bdd);

                    /* Updbtf our intfrnbl Sft to rfflfdt tif bddition/rfmovbl */
                    if (bdd)
                        lodblAddrfssfs.bdd(nfw InftSodkftAddrfss(bddrfss, port));
                    flsf {
                        for (InftSodkftAddrfss bddr : lodblAddrfssfs) {
                            if (bddr.gftAddrfss().fqubls(bddrfss)) {
                                lodblAddrfssfs.rfmovf(bddr);
                                brfbk;
                            }
                        }
                    }
                }
            }
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid Sft<Assodibtion> bssodibtions()
            tirows ClosfdCibnnflExdfption, NotYftBoundExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (!isBound())
                tirow nfw NotYftBoundExdfption();

            rfturn Collfdtions.unmodifibblfSft(bssodibtionMbp.kfySft());
        }
    }

    privbtf boolfbn isBound() {
        syndironizfd (stbtfLodk) {
            rfturn port == -1 ? fblsf : truf;
        }
    }

    privbtf void fnsurfOpfn() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
        }
    }

    privbtf void rfdfivfrClfbnup() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            rfdfivfrTirfbd = 0;
            if (stbtf == CibnnflStbtf.KILLPENDING)
                kill();
        }
    }

    privbtf void sfndfrClfbnup() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            sfndfrTirfbd = 0;
            if (stbtf == CibnnflStbtf.KILLPENDING)
                kill();
        }
    }

    @Ovfrridf
    protfdtfd void implConfigurfBlodking(boolfbn blodk) tirows IOExdfption {
        IOUtil.donfigurfBlodking(fd, blodk);
    }

    @Ovfrridf
    publid void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            SdtpNft.prfClosf(fdVbl);

            if (rfdfivfrTirfbd != 0)
                NbtivfTirfbd.signbl(rfdfivfrTirfbd);

            if (sfndfrTirfbd != 0)
                NbtivfTirfbd.signbl(sfndfrTirfbd);

            if (!isRfgistfrfd())
                kill();
        }
    }

    @Ovfrridf
    publid FilfDfsdriptor gftFD() {
        rfturn fd;
    }

    @Ovfrridf
    publid int gftFDVbl() {
        rfturn fdVbl;
    }

    /**
     * Trbnslbtfs nbtivf poll rfvfnt ops into b rfbdy opfrbtion ops
     */
    privbtf boolfbn trbnslbtfRfbdyOps(int ops, int initiblOps,
                                      SflfdtionKfyImpl sk) {
        int intOps = sk.nioIntfrfstOps();
        int oldOps = sk.nioRfbdyOps();
        int nfwOps = initiblOps;

        if ((ops & Nft.POLLNVAL) != 0) {
            /* Tiis siould only ibppfn if tiis dibnnfl is prf-dlosfd wiilf b
             * sflfdtion opfrbtion is in progrfss
             * ## Tirow bn frror if tiis dibnnfl ibs not bffn prf-dlosfd */
            rfturn fblsf;
        }

        if ((ops & (Nft.POLLERR | Nft.POLLHUP)) != 0) {
            nfwOps = intOps;
            sk.nioRfbdyOps(nfwOps);
            rfturn (nfwOps & ~oldOps) != 0;
        }

        if (((ops & Nft.POLLIN) != 0) &&
            ((intOps & SflfdtionKfy.OP_READ) != 0))
            nfwOps |= SflfdtionKfy.OP_READ;

        if (((ops & Nft.POLLOUT) != 0) &&
            ((intOps & SflfdtionKfy.OP_WRITE) != 0))
            nfwOps |= SflfdtionKfy.OP_WRITE;

        sk.nioRfbdyOps(nfwOps);
        rfturn (nfwOps & ~oldOps) != 0;
    }

    @Ovfrridf
    publid boolfbn trbnslbtfAndUpdbtfRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, sk.nioRfbdyOps(), sk);
    }

    @Ovfrridf
    publid boolfbn trbnslbtfAndSftRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, 0, sk);
    }

    @Ovfrridf
    publid void trbnslbtfAndSftIntfrfstOps(int ops, SflfdtionKfyImpl sk) {
        int nfwOps = 0;
        if ((ops & SflfdtionKfy.OP_READ) != 0)
            nfwOps |= Nft.POLLIN;
        if ((ops & SflfdtionKfy.OP_WRITE) != 0)
            nfwOps |= Nft.POLLOUT;
        sk.sflfdtor.putEvfntOps(sk, nfwOps);
    }

    @Ovfrridf
    publid void kill() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (stbtf == CibnnflStbtf.KILLED)
                rfturn;
            if (stbtf == CibnnflStbtf.UNINITIALIZED) {
                stbtf = CibnnflStbtf.KILLED;
                rfturn;
            }
            bssfrt !isOpfn() && !isRfgistfrfd();

            /* Postponf tif kill if tifrf is b tirfbd sfnding or rfdfiving. */
            if (rfdfivfrTirfbd == 0 && sfndfrTirfbd == 0) {
                SdtpNft.dlosf(fdVbl);
                stbtf = CibnnflStbtf.KILLED;
            } flsf {
                stbtf = CibnnflStbtf.KILLPENDING;
            }
        }
    }

    @Ovfrridf
    publid <T> SdtpMultiCibnnfl sftOption(SdtpSodkftOption<T> nbmf,
                                          T vbluf,
                                          Assodibtion bssodibtion)
            tirows IOExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!(supportfdOptions().dontbins(nbmf)))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        syndironizfd (stbtfLodk) {
            if (bssodibtion != null && (nbmf.fqubls(SCTP_PRIMARY_ADDR) ||
                    nbmf.fqubls(SCTP_SET_PEER_PRIMARY_ADDR))) {
                difdkAssodibtion(bssodibtion);
            }
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            int bssodId = bssodibtion == null ? 0 : bssodibtion.bssodibtionID();
            SdtpNft.sftSodkftOption(fdVbl, nbmf, vbluf, bssodId);
        }
        rfturn tiis;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <T> T gftOption(SdtpSodkftOption<T> nbmf, Assodibtion bssodibtion)
            tirows IOExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        syndironizfd (stbtfLodk) {
            if (bssodibtion != null && (nbmf.fqubls(SCTP_PRIMARY_ADDR) ||
                    nbmf.fqubls(SCTP_SET_PEER_PRIMARY_ADDR))) {
                difdkAssodibtion(bssodibtion);
            }
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            int bssodId = bssodibtion == null ? 0 : bssodibtion.bssodibtionID();
            rfturn (T)SdtpNft.gftSodkftOption(fdVbl, nbmf, bssodId);
        }
    }

    privbtf stbtid dlbss DffbultOptionsHoldfr {
        stbtid finbl Sft<SdtpSodkftOption<?>> dffbultOptions = dffbultOptions();

        privbtf stbtid Sft<SdtpSodkftOption<?>> dffbultOptions() {
            HbsiSft<SdtpSodkftOption<?>> sft = nfw HbsiSft<SdtpSodkftOption<?>>(10);
            sft.bdd(SCTP_DISABLE_FRAGMENTS);
            sft.bdd(SCTP_EXPLICIT_COMPLETE);
            sft.bdd(SCTP_FRAGMENT_INTERLEAVE);
            sft.bdd(SCTP_INIT_MAXSTREAMS);
            sft.bdd(SCTP_NODELAY);
            sft.bdd(SCTP_PRIMARY_ADDR);
            sft.bdd(SCTP_SET_PEER_PRIMARY_ADDR);
            sft.bdd(SO_SNDBUF);
            sft.bdd(SO_RCVBUF);
            sft.bdd(SO_LINGER);
            rfturn Collfdtions.unmodifibblfSft(sft);
        }
    }

    @Ovfrridf
    publid finbl Sft<SdtpSodkftOption<?>> supportfdOptions() {
        rfturn DffbultOptionsHoldfr.dffbultOptions;
    }

    @Ovfrridf
    publid <T> MfssbgfInfo rfdfivf(BytfBufffr bufffr,
                                   T bttbdimfnt,
                                   NotifidbtionHbndlfr<T> ibndlfr)
            tirows IOExdfption {
        if (bufffr == null)
            tirow nfw IllfgblArgumfntExdfption("bufffr dbnnot bf null");

        if (bufffr.isRfbdOnly())
            tirow nfw IllfgblArgumfntExdfption("Rfbd-only bufffr");

        if (rfdfivfInvokfd.gft())
            tirow nfw IllfgblRfdfivfExdfption(
                    "dbnnot invokf rfdfivf from ibndlfr");
        rfdfivfInvokfd.sft(Boolfbn.TRUE);

        try {
            RfsultContbinfr rfsultContbinfr = nfw RfsultContbinfr();
            do {
                rfsultContbinfr.dlfbr();
                syndironizfd (rfdfivfLodk) {
                    fnsurfOpfn();
                    if (!isBound())
                        tirow nfw NotYftBoundExdfption();

                    int n = 0;
                    try {
                        bfgin();

                        syndironizfd (stbtfLodk) {
                            if(!isOpfn())
                                rfturn null;
                            rfdfivfrTirfbd = NbtivfTirfbd.durrfnt();
                        }

                        do {
                            n = rfdfivf(fdVbl, bufffr, rfsultContbinfr);
                        } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());

                    } finblly {
                        rfdfivfrClfbnup();
                        fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                        bssfrt IOStbtus.difdk(n);
                    }

                    if (!rfsultContbinfr.isNotifidbtion()) {
                        /* mfssbgf or notiing */
                        if (rfsultContbinfr.ibsSomftiing()) {
                            /* Sft tif bssodibtion bfforf rfturning */
                            MfssbgfInfoImpl info =
                                    rfsultContbinfr.gftMfssbgfInfo();
                            info.sftAssodibtion(lookupAssodibtion(info.
                                    bssodibtionID()));
                            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                            if (sm != null) {
                                InftSodkftAddrfss isb  = (InftSodkftAddrfss)info.bddrfss();
                                if (!bddrfssMbp.dontbinsKfy(isb)) {
                                    /* must bf b nfw bssodibtion */
                                    try {
                                        sm.difdkAddfpt(isb.gftAddrfss().gftHostAddrfss(),
                                                       isb.gftPort());
                                    } dbtdi (SfdurityExdfption sf) {
                                        bufffr.dlfbr();
                                        tirow sf;
                                    }
                                }
                            }

                            bssfrt info.bssodibtion() != null;
                            rfturn info;
                        } flsf  {
                          /* Non-blodking mby rfturn null if notiing bvbilbblf*/
                            rfturn null;
                        }
                    } flsf { /* notifidbtion */
                        syndironizfd (stbtfLodk) {
                            ibndlfNotifidbtionIntfrnbl(
                                    rfsultContbinfr);
                        }
                    }
                } /* rfdfivfLodk */
            } wiilf (ibndlfr == null ? truf :
                (invokfNotifidbtionHbndlfr(rfsultContbinfr, ibndlfr, bttbdimfnt)
                 == HbndlfrRfsult.CONTINUE));
        } finblly {
            rfdfivfInvokfd.sft(Boolfbn.FALSE);
        }

        rfturn null;
    }

    privbtf int rfdfivf(int fd,
                        BytfBufffr dst,
                        RfsultContbinfr rfsultContbinfr)
            tirows IOExdfption {
        int pos = dst.position();
        int lim = dst.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);
        if (dst instbndfof DirfdtBufffr && rfm > 0)
            rfturn rfdfivfIntoNbtivfBufffr(fd, rfsultContbinfr, dst, rfm, pos);

        /* Substitutf b nbtivf bufffr. */
        int nfwSizf = Mbti.mbx(rfm, 1);
        BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(nfwSizf);
        try {
            int n = rfdfivfIntoNbtivfBufffr(fd, rfsultContbinfr, bb, nfwSizf, 0);
            bb.flip();
            if (n > 0 && rfm > 0)
                dst.put(bb);
            rfturn n;
        } finblly {
            Util.rflfbsfTfmporbryDirfdtBufffr(bb);
        }
    }

    privbtf int rfdfivfIntoNbtivfBufffr(int fd,
                                        RfsultContbinfr rfsultContbinfr,
                                        BytfBufffr bb,
                                        int rfm,
                                        int pos)
            tirows IOExdfption {
        int n = rfdfivf0(fd, rfsultContbinfr, ((DirfdtBufffr)bb).bddrfss() + pos, rfm);
        if (n > 0)
            bb.position(pos + n);
        rfturn n;
    }

    privbtf IntfrnblNotifidbtionHbndlfr intfrnblNotifidbtionHbndlfr =
            nfw IntfrnblNotifidbtionHbndlfr();

    privbtf void ibndlfNotifidbtionIntfrnbl(RfsultContbinfr rfsultContbinfr)
    {
        invokfNotifidbtionHbndlfr(rfsultContbinfr,
                intfrnblNotifidbtionHbndlfr, null);
    }

    privbtf dlbss IntfrnblNotifidbtionHbndlfr
            fxtfnds AbstrbdtNotifidbtionHbndlfr<Objfdt>
    {
        @Ovfrridf
        publid HbndlfrRfsult ibndlfNotifidbtion(
                AssodibtionCibngfNotifidbtion not, Objfdt unusfd) {
            AssodibtionCibngf sbd = (AssodibtionCibngf) not;

            /* Updbtf mbp to rfflfdt dibngf in bssodibtion */
            switdi (not.fvfnt()) {
                dbsf COMM_UP :
                    Assodibtion nfwAssodibtion = nfw AssodibtionImpl
                       (sbd.bssodId(), sbd.mbxInStrfbms(), sbd.mbxOutStrfbms());
                    bddAssodibtion(nfwAssodibtion);
                    brfbk;
                dbsf SHUTDOWN :
                dbsf COMM_LOST :
                //dbsf RESTART: ???
                    /* mbrk bssodibtion for rfmovbl bftfr usfr ibndlfr invokfd*/
                    bssodibtionToRfmovf.sft(lookupAssodibtion(sbd.bssodId()));
            }
            rfturn HbndlfrRfsult.CONTINUE;
        }
    }

    privbtf <T> HbndlfrRfsult invokfNotifidbtionHbndlfr(
                                   RfsultContbinfr rfsultContbinfr,
                                   NotifidbtionHbndlfr<T> ibndlfr,
                                   T bttbdimfnt) {
        HbndlfrRfsult rfsult;
        SdtpNotifidbtion notifidbtion = rfsultContbinfr.notifidbtion();
        notifidbtion.sftAssodibtion(lookupAssodibtion(notifidbtion.bssodId()));

        if (!(ibndlfr instbndfof AbstrbdtNotifidbtionHbndlfr)) {
            rfsult = ibndlfr.ibndlfNotifidbtion(notifidbtion, bttbdimfnt);
        } flsf { /* AbstrbdtNotifidbtionHbndlfr */
            AbstrbdtNotifidbtionHbndlfr<T> bbsHbndlfr =
                    (AbstrbdtNotifidbtionHbndlfr<T>)ibndlfr;
            switdi(rfsultContbinfr.typf()) {
                dbsf ASSOCIATION_CHANGED :
                    rfsult = bbsHbndlfr.ibndlfNotifidbtion(
                            rfsultContbinfr.gftAssodibtionCibngfd(), bttbdimfnt);
                    brfbk;
                dbsf PEER_ADDRESS_CHANGED :
                    rfsult = bbsHbndlfr.ibndlfNotifidbtion(
                            rfsultContbinfr.gftPffrAddrfssCibngfd(), bttbdimfnt);
                    brfbk;
                dbsf SEND_FAILED :
                    rfsult = bbsHbndlfr.ibndlfNotifidbtion(
                            rfsultContbinfr.gftSfndFbilfd(), bttbdimfnt);
                    brfbk;
                dbsf SHUTDOWN :
                    rfsult =  bbsHbndlfr.ibndlfNotifidbtion(
                            rfsultContbinfr.gftSiutdown(), bttbdimfnt);
                    brfbk;
                dffbult :
                    /* implfmfntbtion spfdifid ibndlfrs */
                    rfsult =  bbsHbndlfr.ibndlfNotifidbtion(
                            rfsultContbinfr.notifidbtion(), bttbdimfnt);
            }
        }

        if (!(ibndlfr instbndfof IntfrnblNotifidbtionHbndlfr)) {
            /* Only rfmovf bssodibtions bftfr usfr ibndlfr
             * ibs finisifd witi tifm */
            Assodibtion bssod = bssodibtionToRfmovf.gft();
            if (bssod != null) {
                rfmovfAssodibtion(bssod);
                bssodibtionToRfmovf.sft(null);
            }

        }

        rfturn rfsult;
    }

    privbtf Assodibtion lookupAssodibtion(int bssodId) {
        /* Lookup tif bssodibtion in our intfrnbl mbp */
        syndironizfd (stbtfLodk) {
            Sft<Assodibtion> bssods = bssodibtionMbp.kfySft();
            for (Assodibtion b : bssods) {
                if (b.bssodibtionID() == bssodId) {
                    rfturn b;
                }
            }
        }
        rfturn null;
    }

    privbtf void bddAssodibtion(Assodibtion bssodibtion) {
        syndironizfd (stbtfLodk) {
            int bssodId = bssodibtion.bssodibtionID();
            Sft<SodkftAddrfss> bddrfssfs = null;

            try {
                bddrfssfs = SdtpNft.gftRfmotfAddrfssfs(fdVbl, bssodId);
            } dbtdi (IOExdfption unusfd) {
                /* OK, dftfrmining donnfdtfd bddrfssfs mby not bf possiblf
                 * siutdown, donnfdtion lost, ftd */
            }

            bssodibtionMbp.put(bssodibtion, bddrfssfs);
            if (bddrfssfs != null) {
                for (SodkftAddrfss bddr : bddrfssfs)
                    bddrfssMbp.put(bddr, bssodibtion);
            }
        }
    }

    privbtf void rfmovfAssodibtion(Assodibtion bssodibtion) {
        syndironizfd (stbtfLodk) {
            int bssodId = bssodibtion.bssodibtionID();
            Sft<SodkftAddrfss> bddrfssfs = null;

             try {
                bddrfssfs = SdtpNft.gftRfmotfAddrfssfs(fdVbl, bssodId);
            } dbtdi (IOExdfption unusfd) {
                /* OK, dftfrmining donnfdtfd bddrfssfs mby not bf possiblf
                 * siutdown, donnfdtion lost, ftd */
            }

            Sft<Assodibtion> bssods = bssodibtionMbp.kfySft();
            for (Assodibtion b : bssods) {
                if (b.bssodibtionID() == bssodId) {
                    bssodibtionMbp.rfmovf(b);
                    brfbk;
                }
            }
            if (bddrfssfs != null) {
                for (SodkftAddrfss bddr : bddrfssfs)
                    bddrfssMbp.rfmovf(bddr);
            } flsf {
                /* Wf dbnnot dftfrminf tif donnfdtfd bddrfssfs */
                Sft<jbvb.util.Mbp.Entry<SodkftAddrfss, Assodibtion>> bddrAssods =
                        bddrfssMbp.fntrySft();
                Itfrbtor<Entry<SodkftAddrfss, Assodibtion>> itfrbtor = bddrAssods.itfrbtor();
                wiilf (itfrbtor.ibsNfxt()) {
                    Entry<SodkftAddrfss, Assodibtion> fntry = itfrbtor.nfxt();
                    if (fntry.gftVbluf().fqubls(bssodibtion)) {
                        itfrbtor.rfmovf();
                    }
                }
            }
        }
    }

    /**
     * @tirows  IllfgblArgumfntExdfption
     *          If tif givfn bssodibtion is not dontrollfd by tiis dibnnfl
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif givfn bssodibtion is onf
     *          of tif durrfnt bssodibtions dontrollfd by tiis dibnnfl
     */
    privbtf boolfbn difdkAssodibtion(Assodibtion mfssbgfAssod) {
        syndironizfd (stbtfLodk) {
            for (Assodibtion bssodibtion : bssodibtionMbp.kfySft()) {
                if (mfssbgfAssod.fqubls(bssodibtion)) {
                    rfturn truf;
                }
            }
        }
        tirow nfw IllfgblArgumfntExdfption(
              "Givfn Assodibtion is not dontrollfd by tiis dibnnfl");
    }

    privbtf void difdkStrfbmNumbfr(Assodibtion bssod, int strfbmNumbfr) {
        syndironizfd (stbtfLodk) {
            if (strfbmNumbfr < 0 || strfbmNumbfr >= bssod.mbxOutboundStrfbms())
                tirow nfw InvblidStrfbmExdfption();
        }
    }

    /* TODO: Add support for ttl bnd isComplftf to boti 121 12M
     *       SCTP_EOR not yft supportfd on rfffrfndf plbtforms
     *       TTL support limitfd...
     */
    @Ovfrridf
    publid int sfnd(BytfBufffr bufffr, MfssbgfInfo mfssbgfInfo)
            tirows IOExdfption {
        if (bufffr == null)
            tirow nfw IllfgblArgumfntExdfption("bufffr dbnnot bf null");

        if (mfssbgfInfo == null)
            tirow nfw IllfgblArgumfntExdfption("mfssbgfInfo dbnnot bf null");

        syndironizfd (sfndLodk) {
            fnsurfOpfn();

            if (!isBound())
                bind(null, 0);

            int n = 0;
            try {
                int bssodId = -1;
                SodkftAddrfss bddrfss = null;
                bfgin();

                syndironizfd (stbtfLodk) {
                    if(!isOpfn())
                        rfturn 0;
                    sfndfrTirfbd = NbtivfTirfbd.durrfnt();

                    /* Dftfrminf wibt bddrfss or bssodibtion to sfnd to */
                    Assodibtion bssod = mfssbgfInfo.bssodibtion();
                    InftSodkftAddrfss bddr = (InftSodkftAddrfss)mfssbgfInfo.bddrfss();
                    if (bssod != null) {
                        difdkAssodibtion(bssod);
                        difdkStrfbmNumbfr(bssod, mfssbgfInfo.strfbmNumbfr());
                        bssodId = bssod.bssodibtionID();
                        /* ibvf wf blso got b prfffrrfd bddrfss */
                        if (bddr != null) {
                            if (!bssod.fqubls(bddrfssMbp.gft(bddr)))
                                tirow nfw IllfgblArgumfntExdfption("givfn prfffrrfd bddrfss is not pbrt of tiis bssodibtion");
                            bddrfss = bddr;
                        }
                    } flsf if (bddr != null) {
                        bddrfss = bddr;
                        Assodibtion bssodibtion = bddrfssMbp.gft(bddr);
                        if (bssodibtion != null) {
                            difdkStrfbmNumbfr(bssodibtion, mfssbgfInfo.strfbmNumbfr());
                            bssodId = bssodibtion.bssodibtionID();

                        } flsf { /* must bf nfw bssodibtion */
                            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                            if (sm != null)
                                sm.difdkConnfdt(bddr.gftAddrfss().gftHostAddrfss(),
                                                bddr.gftPort());
                        }
                    } flsf {
                        tirow nfw AssfrtionError(
                            "Boti bssodibtion bnd bddrfss dbnnot bf null");
                    }
                }

                do {
                    n = sfnd(fdVbl, bufffr, bssodId, bddrfss, mfssbgfInfo);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());

                rfturn IOStbtus.normblizf(n);
            } finblly {
                sfndfrClfbnup();
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    privbtf int sfnd(int fd,
                     BytfBufffr srd,
                     int bssodId,
                     SodkftAddrfss tbrgft,
                     MfssbgfInfo mfssbgfInfo)
            tirows IOExdfption {
        int strfbmNumbfr = mfssbgfInfo.strfbmNumbfr();
        boolfbn unordfrfd = mfssbgfInfo.isUnordfrfd();
        int ppid = mfssbgfInfo.pbylobdProtodolID();

        if (srd instbndfof DirfdtBufffr)
            rfturn sfndFromNbtivfBufffr(fd, srd, tbrgft, bssodId,
                    strfbmNumbfr, unordfrfd, ppid);

        /* Substitutf b nbtivf bufffr */
        int pos = srd.position();
        int lim = srd.limit();
        bssfrt (pos <= lim && strfbmNumbfr >= 0);

        int rfm = (pos <= lim ? lim - pos : 0);
        BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(rfm);
        try {
            bb.put(srd);
            bb.flip();
            /* Do not updbtf srd until wf sff iow mbny bytfs wfrf writtfn */
            srd.position(pos);

            int n = sfndFromNbtivfBufffr(fd, bb, tbrgft, bssodId,
                    strfbmNumbfr, unordfrfd, ppid);
            if (n > 0) {
                /* now updbtf srd */
                srd.position(pos + n);
            }
            rfturn n;
        } finblly {
            Util.rflfbsfTfmporbryDirfdtBufffr(bb);
        }
    }

    privbtf int sfndFromNbtivfBufffr(int fd,
                                     BytfBufffr bb,
                                     SodkftAddrfss tbrgft,
                                     int bssodId,
                                     int strfbmNumbfr,
                                     boolfbn unordfrfd,
                                     int ppid)
            tirows IOExdfption {
        InftAddrfss bddr = null;     // no prfffrrfd bddrfss
        int port = 0;
        if (tbrgft != null) {
            InftSodkftAddrfss isb = Nft.difdkAddrfss(tbrgft);
            bddr = isb.gftAddrfss();
            port = isb.gftPort();
        }
        int pos = bb.position();
        int lim = bb.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        int writtfn = sfnd0(fd, ((DirfdtBufffr)bb).bddrfss() + pos, rfm, bddr,
                            port, bssodId, strfbmNumbfr, unordfrfd, ppid);
        if (writtfn > 0)
            bb.position(pos + writtfn);
        rfturn writtfn;
    }

    @Ovfrridf
    publid SdtpMultiCibnnfl siutdown(Assodibtion bssodibtion)
            tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            difdkAssodibtion(bssodibtion);
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            SdtpNft.siutdown(fdVbl, bssodibtion.bssodibtionID());
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid Sft<SodkftAddrfss> gftAllLodblAddrfssfs()
            tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (!isBound())
                rfturn Collfdtions.fmptySft();

            rfturn SdtpNft.gftLodblAddrfssfs(fdVbl);
        }
    }

    @Ovfrridf
    publid Sft<SodkftAddrfss> gftRfmotfAddrfssfs(Assodibtion bssodibtion)
            tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            difdkAssodibtion(bssodibtion);
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            try {
                rfturn SdtpNft.gftRfmotfAddrfssfs(fdVbl, bssodibtion.bssodibtionID());
            } dbtdi (SodkftExdfption sf) {
                /* b vblid bssodibtion siould blwbys ibvf rfmotf bddrfssfs */
                Sft<SodkftAddrfss> bddrs = bssodibtionMbp.gft(bssodibtion);
                rfturn bddrs != null ? bddrs : Collfdtions.<SodkftAddrfss>fmptySft();
            }
        }
    }

    @Ovfrridf
    publid SdtpCibnnfl brbndi(Assodibtion bssodibtion)
            tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            difdkAssodibtion(bssodibtion);
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            FilfDfsdriptor bFd = SdtpNft.brbndi(fdVbl,
                                                bssodibtion.bssodibtionID());
            /* suddfssfully brbndifd, wf dbn now rfmovf it from bssod list */
            rfmovfAssodibtion(bssodibtion);

            rfturn nfw SdtpCibnnflImpl(providfr(), bFd, bssodibtion);
        }
    }

    /* Usf dommon nbtivf implfmfntbtion sibrfd bftwffn
     * onf-to-onf bnd onf-to-mbny */
    privbtf stbtid int rfdfivf0(int fd,
                                RfsultContbinfr rfsultContbinfr,
                                long bddrfss,
                                int lfngti)
            tirows IOExdfption{
        rfturn SdtpCibnnflImpl.rfdfivf0(fd, rfsultContbinfr, bddrfss,
                lfngti, fblsf /*pffk */);
    }

    privbtf stbtid int sfnd0(int fd,
                             long bddrfss,
                             int lfngti,
                             InftAddrfss bddr,
                             int port,
                             int bssodId,
                             int strfbmNumbfr,
                             boolfbn unordfrfd,
                             int ppid)
            tirows IOExdfption {
        rfturn SdtpCibnnflImpl.sfnd0(fd, bddrfss, lfngti, bddr, port, bssodId,
                strfbmNumbfr, unordfrfd, ppid);
    }

    stbtid {
        IOUtil.lobd();   /* lobds nio & nft nbtivf librbrifs */
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("sdtp");
                    rfturn null;
                }
            });
    }
}
