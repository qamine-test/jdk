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
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.SflfdtionKfy;
import jbvb.nio.dibnnfls.ClosfdCibnnflExdfption;
import jbvb.nio.dibnnfls.ConnfdtionPfndingExdfption;
import jbvb.nio.dibnnfls.NoConnfdtionPfndingExdfption;
import jbvb.nio.dibnnfls.AlrfbdyConnfdtfdExdfption;
import jbvb.nio.dibnnfls.NotYftBoundExdfption;
import jbvb.nio.dibnnfls.NotYftConnfdtfdExdfption;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import dom.sun.nio.sdtp.AbstrbdtNotifidbtionHbndlfr;
import dom.sun.nio.sdtp.Assodibtion;
import dom.sun.nio.sdtp.AssodibtionCibngfNotifidbtion;
import dom.sun.nio.sdtp.HbndlfrRfsult;
import dom.sun.nio.sdtp.IllfgblRfdfivfExdfption;
import dom.sun.nio.sdtp.InvblidStrfbmExdfption;
import dom.sun.nio.sdtp.IllfgblUnbindExdfption;
import dom.sun.nio.sdtp.MfssbgfInfo;
import dom.sun.nio.sdtp.NotifidbtionHbndlfr;
import dom.sun.nio.sdtp.SdtpCibnnfl;
import dom.sun.nio.sdtp.SdtpSodkftOption;
import sun.nio.di.DirfdtBufffr;
import sun.nio.di.IOStbtus;
import sun.nio.di.IOUtil;
import sun.nio.di.NbtivfTirfbd;
import sun.nio.di.Nft;
import sun.nio.di.PollArrbyWrbppfr;
import sun.nio.di.SflCiImpl;
import sun.nio.di.SflfdtionKfyImpl;
import sun.nio.di.Util;
import stbtid dom.sun.nio.sdtp.SdtpStbndbrdSodkftOptions.*;
import stbtid sun.nio.di.sdtp.RfsultContbinfr.SEND_FAILED;
import stbtid sun.nio.di.sdtp.RfsultContbinfr.ASSOCIATION_CHANGED;
import stbtid sun.nio.di.sdtp.RfsultContbinfr.PEER_ADDRESS_CHANGED;
import stbtid sun.nio.di.sdtp.RfsultContbinfr.SHUTDOWN;

/**
 * An implfmfntbtion of bn SdtpCibnnfl
 */
publid dlbss SdtpCibnnflImpl fxtfnds SdtpCibnnfl
    implfmfnts SflCiImpl
{
    privbtf finbl FilfDfsdriptor fd;

    privbtf finbl int fdVbl;

    /* IDs of nbtivf tirfbds doing sfnd bnd rfdfivfss, for signblling */
    privbtf volbtilf long rfdfivfrTirfbd = 0;
    privbtf volbtilf long sfndfrTirfbd = 0;

    /* Lodk ifld by durrfnt rfdfiving or donnfdting tirfbd */
    privbtf finbl Objfdt rfdfivfLodk = nfw Objfdt();

    /* Lodk ifld by durrfnt sfnding or donnfdting tirfbd */
    privbtf finbl Objfdt sfndLodk = nfw Objfdt();

    privbtf finbl TirfbdLodbl<Boolfbn> rfdfivfInvokfd =
        nfw TirfbdLodbl<Boolfbn>() {
             @Ovfrridf protfdtfd Boolfbn initiblVbluf() {
                 rfturn Boolfbn.FALSE;
            }
    };

    /* Lodk ifld by bny tirfbd tibt modififs tif stbtf fiflds dfdlbrfd bflow
       DO NOT invokf b blodking I/O opfrbtion wiilf iolding tiis lodk! */
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    privbtf fnum CibnnflStbtf {
        UNINITIALIZED,
        UNCONNECTED,
        PENDING,
        CONNECTED,
        KILLPENDING,
        KILLED,
    }
    /* -- Tif following fiflds brf protfdtfd by stbtfLodk -- */
    privbtf CibnnflStbtf stbtf = CibnnflStbtf.UNINITIALIZED;

    /* Binding; Ondf bound tif port will rfmbin donstbnt. */
    int port = -1;
    privbtf HbsiSft<InftSodkftAddrfss> lodblAddrfssfs = nfw HbsiSft<InftSodkftAddrfss>();
    /* Hbs tif dibnnfl bffn bound to tif wilddbrd bddrfss */
    privbtf boolfbn wilddbrd; /* fblsf */
    //privbtf InftSodkftAddrfss rfmotfAddrfss = null;

    /* Input/Output opfn */
    privbtf boolfbn rfbdyToConnfdt;

    /* Siutdown */
    privbtf boolfbn isSiutdown;

    privbtf Assodibtion bssodibtion;

    privbtf Sft<SodkftAddrfss> rfmotfAddrfssfs = Collfdtions.fmptySft();

    /* -- End of fiflds protfdtfd by stbtfLodk -- */

    /**
     * Construdtor for normbl donnfdting sodkfts
     */
    publid SdtpCibnnflImpl(SflfdtorProvidfr providfr) tirows IOExdfption {
        //TODO: updbtf providfr rfmovf publid modififr
        supfr(providfr);
        tiis.fd = SdtpNft.sodkft(truf);
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = CibnnflStbtf.UNCONNECTED;
    }

    /**
     * Construdtor for sodkfts obtbinfd from sfrvfr sodkfts
     */
    publid SdtpCibnnflImpl(SflfdtorProvidfr providfr, FilfDfsdriptor fd)
         tirows IOExdfption {
        tiis(providfr, fd, null);
    }

    /**
     * Construdtor for sodkfts obtbinfd from brbndiing
     */
    publid SdtpCibnnflImpl(SflfdtorProvidfr providfr,
                           FilfDfsdriptor fd,
                           Assodibtion bssodibtion)
            tirows IOExdfption {
        supfr(providfr);
        tiis.fd = fd;
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = CibnnflStbtf.CONNECTED;
        port = (Nft.lodblAddrfss(fd)).gftPort();

        if (bssodibtion != null) { /* brbndifd */
            tiis.bssodibtion = bssodibtion;
        } flsf { /* obtbinfd from sfrvfr dibnnfl */
            /* Rfdfivf COMM_UP */
            BytfBufffr buf = Util.gftTfmporbryDirfdtBufffr(50);
            try {
                rfdfivf(buf, null, null, truf);
            } finblly {
                Util.rflfbsfTfmporbryDirfdtBufffr(buf);
            }
        }
    }

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss.
     */
    @Ovfrridf
    publid SdtpCibnnfl bind(SodkftAddrfss lodbl) tirows IOExdfption {
        syndironizfd (rfdfivfLodk) {
            syndironizfd (sfndLodk) {
                syndironizfd (stbtfLodk) {
                    fnsurfOpfnAndUndonnfdtfd();
                    if (isBound())
                        SdtpNft.tirowAlrfbdyBoundExdfption();
                    InftSodkftAddrfss isb = (lodbl == null) ?
                        nfw InftSodkftAddrfss(0) : Nft.difdkAddrfss(lodbl);
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        sm.difdkListfn(isb.gftPort());
                    }
                    Nft.bind(fd, isb.gftAddrfss(), isb.gftPort());
                    InftSodkftAddrfss boundIsb = Nft.lodblAddrfss(fd);
                    port = boundIsb.gftPort();
                    lodblAddrfssfs.bdd(isb);
                    if (isb.gftAddrfss().isAnyLodblAddrfss())
                        wilddbrd = truf;
                }
            }
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid SdtpCibnnfl bindAddrfss(InftAddrfss bddrfss)
            tirows IOExdfption {
        bindUnbindAddrfss(bddrfss, truf);
        lodblAddrfssfs.bdd(nfw InftSodkftAddrfss(bddrfss, port));
        rfturn tiis;
    }

    @Ovfrridf
    publid SdtpCibnnfl unbindAddrfss(InftAddrfss bddrfss)
            tirows IOExdfption {
        bindUnbindAddrfss(bddrfss, fblsf);
        lodblAddrfssfs.rfmovf(nfw InftSodkftAddrfss(bddrfss, port));
        rfturn tiis;
    }

    privbtf SdtpCibnnfl bindUnbindAddrfss(InftAddrfss bddrfss, boolfbn bdd)
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

    privbtf boolfbn isBound() {
        syndironizfd (stbtfLodk) {
            rfturn port == -1 ? fblsf : truf;
        }
    }

    privbtf boolfbn isConnfdtfd() {
        syndironizfd (stbtfLodk) {
            rfturn (stbtf == CibnnflStbtf.CONNECTED);
        }
    }

    privbtf void fnsurfOpfnAndUndonnfdtfd() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (isConnfdtfd())
                tirow nfw AlrfbdyConnfdtfdExdfption();
            if (stbtf == CibnnflStbtf.PENDING)
                tirow nfw ConnfdtionPfndingExdfption();
        }
    }

    privbtf boolfbn fnsurfRfdfivfOpfn() tirows ClosfdCibnnflExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (!isConnfdtfd())
                tirow nfw NotYftConnfdtfdExdfption();
            flsf
                rfturn truf;
        }
    }

    privbtf void fnsurfSfndOpfn() tirows ClosfdCibnnflExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (isSiutdown)
                tirow nfw ClosfdCibnnflExdfption();
            if (!isConnfdtfd())
                tirow nfw NotYftConnfdtfdExdfption();
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
    publid Assodibtion bssodibtion() tirows ClosfdCibnnflExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (!isConnfdtfd())
                rfturn null;

            rfturn bssodibtion;
        }
    }

    @Ovfrridf
    publid boolfbn donnfdt(SodkftAddrfss fndpoint) tirows IOExdfption {
        syndironizfd (rfdfivfLodk) {
            syndironizfd (sfndLodk) {
                fnsurfOpfnAndUndonnfdtfd();
                InftSodkftAddrfss isb = Nft.difdkAddrfss(fndpoint);
                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                if (sm != null)
                    sm.difdkConnfdt(isb.gftAddrfss().gftHostAddrfss(),
                                    isb.gftPort());
                syndironizfd (blodkingLodk()) {
                    int n = 0;
                    try {
                        try {
                            bfgin();
                            syndironizfd (stbtfLodk) {
                                if (!isOpfn()) {
                                    rfturn fblsf;
                                }
                                rfdfivfrTirfbd = NbtivfTirfbd.durrfnt();
                            }
                            for (;;) {
                                InftAddrfss ib = isb.gftAddrfss();
                                if (ib.isAnyLodblAddrfss())
                                    ib = InftAddrfss.gftLodblHost();
                                n = SdtpNft.donnfdt(fdVbl, ib, isb.gftPort());
                                if (  (n == IOStbtus.INTERRUPTED)
                                      && isOpfn())
                                    dontinuf;
                                brfbk;
                            }
                        } finblly {
                            rfdfivfrClfbnup();
                            fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                            bssfrt IOStbtus.difdk(n);
                        }
                    } dbtdi (IOExdfption x) {
                        /* If bn fxdfption wbs tirown, dlosf tif dibnnfl bftfr
                         * invoking fnd() so bs to bvoid bogus
                         * AsyndironousClosfExdfptions */
                        dlosf();
                        tirow x;
                    }

                    if (n > 0) {
                        syndironizfd (stbtfLodk) {
                            /* Connfdtion suddffdfd */
                            stbtf = CibnnflStbtf.CONNECTED;
                            if (!isBound()) {
                                InftSodkftAddrfss boundIsb =
                                        Nft.lodblAddrfss(fd);
                                port = boundIsb.gftPort();
                            }

                            /* Rfdfivf COMM_UP */
                            BytfBufffr buf = Util.gftTfmporbryDirfdtBufffr(50);
                            try {
                                rfdfivf(buf, null, null, truf);
                            } finblly {
                                Util.rflfbsfTfmporbryDirfdtBufffr(buf);
                            }

                            /* dbdif rfmotf bddrfssfs */
                            try {
                                rfmotfAddrfssfs = gftRfmotfAddrfssfs();
                            } dbtdi (IOExdfption unusfd) { /* swbllow fxdfption */ }

                            rfturn truf;
                        }
                    } flsf  {
                        syndironizfd (stbtfLodk) {
                            /* If nonblodking bnd no fxdfption tifn donnfdtion
                             * pfnding; disbllow bnotifr invodbtion */
                            if (!isBlodking())
                                stbtf = CibnnflStbtf.PENDING;
                            flsf
                                bssfrt fblsf;
                        }
                    }
                }
                rfturn fblsf;
            }
        }
    }

    @Ovfrridf
    publid boolfbn donnfdt(SodkftAddrfss fndpoint,
                           int mbxOutStrfbms,
                           int mbxInStrfbms)
            tirows IOExdfption {
        fnsurfOpfnAndUndonnfdtfd();
        rfturn sftOption(SCTP_INIT_MAXSTREAMS, InitMbxStrfbms.
                drfbtf(mbxInStrfbms, mbxOutStrfbms)).donnfdt(fndpoint);

    }

    @Ovfrridf
    publid boolfbn isConnfdtionPfnding() {
        syndironizfd (stbtfLodk) {
            rfturn (stbtf == CibnnflStbtf.PENDING);
        }
    }

    @Ovfrridf
    publid boolfbn finisiConnfdt() tirows IOExdfption {
        syndironizfd (rfdfivfLodk) {
            syndironizfd (sfndLodk) {
                syndironizfd (stbtfLodk) {
                    if (!isOpfn())
                        tirow nfw ClosfdCibnnflExdfption();
                    if (isConnfdtfd())
                        rfturn truf;
                    if (stbtf != CibnnflStbtf.PENDING)
                        tirow nfw NoConnfdtionPfndingExdfption();
                }
                int n = 0;
                try {
                    try {
                        bfgin();
                        syndironizfd (blodkingLodk()) {
                            syndironizfd (stbtfLodk) {
                                if (!isOpfn()) {
                                    rfturn fblsf;
                                }
                                rfdfivfrTirfbd = NbtivfTirfbd.durrfnt();
                            }
                            if (!isBlodking()) {
                                for (;;) {
                                    n = difdkConnfdt(fd, fblsf, rfbdyToConnfdt);
                                    if (  (n == IOStbtus.INTERRUPTED)
                                          && isOpfn())
                                        dontinuf;
                                    brfbk;
                                }
                            } flsf {
                                for (;;) {
                                    n = difdkConnfdt(fd, truf, rfbdyToConnfdt);
                                    if (n == 0) {
                                        // Loop in dbsf of
                                        // spurious notifidbtions
                                        dontinuf;
                                    }
                                    if (  (n == IOStbtus.INTERRUPTED)
                                          && isOpfn())
                                        dontinuf;
                                    brfbk;
                                }
                            }
                        }
                    } finblly {
                        syndironizfd (stbtfLodk) {
                            rfdfivfrTirfbd = 0;
                            if (stbtf == CibnnflStbtf.KILLPENDING) {
                                kill();
                                /* poll()/gftsodkopt() dofs not rfport
                                 * frror (tirows fxdfption, witi n = 0)
                                 * on Linux plbtform bftfr dup2 bnd
                                 * signbl-wbkfup. Fordf n to 0 so tif
                                 * fnd() dbn tirow bppropribtf fxdfption */
                                n = 0;
                            }
                        }
                        fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                        bssfrt IOStbtus.difdk(n);
                    }
                } dbtdi (IOExdfption x) {
                    /* If bn fxdfption wbs tirown, dlosf tif dibnnfl bftfr
                     * invoking fnd() so bs to bvoid bogus
                     * AsyndironousClosfExdfptions */
                    dlosf();
                    tirow x;
                }

                if (n > 0) {
                    syndironizfd (stbtfLodk) {
                        stbtf = CibnnflStbtf.CONNECTED;
                        if (!isBound()) {
                            InftSodkftAddrfss boundIsb =
                                    Nft.lodblAddrfss(fd);
                            port = boundIsb.gftPort();
                        }

                        /* Rfdfivf COMM_UP */
                        BytfBufffr buf = Util.gftTfmporbryDirfdtBufffr(50);
                        try {
                            rfdfivf(buf, null, null, truf);
                        } finblly {
                            Util.rflfbsfTfmporbryDirfdtBufffr(buf);
                        }

                        /* dbdif rfmotf bddrfssfs */
                        try {
                            rfmotfAddrfssfs = gftRfmotfAddrfssfs();
                        } dbtdi (IOExdfption unusfd) { /* swbllow fxdfption */ }

                        rfturn truf;
                    }
                }
            }
        }
        rfturn fblsf;
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
    privbtf boolfbn trbnslbtfRfbdyOps(int ops, int initiblOps, SflfdtionKfyImpl sk) {
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
            /* No nffd to poll bgbin in difdkConnfdt,
             * tif frror will bf dftfdtfd tifrf */
            rfbdyToConnfdt = truf;
            rfturn (nfwOps & ~oldOps) != 0;
        }

        if (((ops & Nft.POLLIN) != 0) &&
            ((intOps & SflfdtionKfy.OP_READ) != 0) &&
            isConnfdtfd())
            nfwOps |= SflfdtionKfy.OP_READ;

        if (((ops & Nft.POLLCONN) != 0) &&
            ((intOps & SflfdtionKfy.OP_CONNECT) != 0) &&
            ((stbtf == CibnnflStbtf.UNCONNECTED) || (stbtf == CibnnflStbtf.PENDING))) {
            nfwOps |= SflfdtionKfy.OP_CONNECT;
            rfbdyToConnfdt = truf;
        }

        if (((ops & Nft.POLLOUT) != 0) &&
            ((intOps & SflfdtionKfy.OP_WRITE) != 0) &&
            isConnfdtfd())
            nfwOps |= SflfdtionKfy.OP_WRITE;

        sk.nioRfbdyOps(nfwOps);
        rfturn (nfwOps & ~oldOps) != 0;
    }

    @Ovfrridf
    publid boolfbn trbnslbtfAndUpdbtfRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, sk.nioRfbdyOps(), sk);
    }

    @Ovfrridf
    @SupprfssWbrnings("bll")
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
        if ((ops & SflfdtionKfy.OP_CONNECT) != 0)
            nfwOps |= Nft.POLLCONN;
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

            /* Postponf tif kill if tifrf is b wbiting rfbdfr
             * or writfr tirfbd. */
            if (rfdfivfrTirfbd == 0 && sfndfrTirfbd == 0) {
                SdtpNft.dlosf(fdVbl);
                stbtf = CibnnflStbtf.KILLED;
            } flsf {
                stbtf = CibnnflStbtf.KILLPENDING;
            }
        }
    }

    @Ovfrridf
    publid <T> SdtpCibnnfl sftOption(SdtpSodkftOption<T> nbmf, T vbluf)
            tirows IOExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            SdtpNft.sftSodkftOption(fdVbl, nbmf, vbluf, 0 /*onfToOnf*/);
        }
        rfturn tiis;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <T> T gftOption(SdtpSodkftOption<T> nbmf) tirows IOExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            rfturn (T)SdtpNft.gftSodkftOption(fdVbl, nbmf, 0 /*onfToOnf*/);
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
        rfturn rfdfivf(bufffr, bttbdimfnt, ibndlfr, fblsf);
    }

    privbtf <T> MfssbgfInfo rfdfivf(BytfBufffr bufffr,
                                    T bttbdimfnt,
                                    NotifidbtionHbndlfr<T> ibndlfr,
                                    boolfbn fromConnfdt)
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
                    if (!fnsurfRfdfivfOpfn())
                        rfturn null;

                    int n = 0;
                    try {
                        bfgin();

                        syndironizfd (stbtfLodk) {
                            if(!isOpfn())
                                rfturn null;
                            rfdfivfrTirfbd = NbtivfTirfbd.durrfnt();
                        }

                        do {
                            n = rfdfivf(fdVbl, bufffr, rfsultContbinfr, fromConnfdt);
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
                            syndironizfd (stbtfLodk) {
                                bssfrt bssodibtion != null;
                                info.sftAssodibtion(bssodibtion);
                            }
                            rfturn info;
                        } flsf
                            /* Non-blodking mby rfturn null if notiing bvbilbblf*/
                            rfturn null;
                    } flsf { /* notifidbtion */
                        syndironizfd (stbtfLodk) {
                            ibndlfNotifidbtionIntfrnbl(
                                    rfsultContbinfr);
                        }
                    }

                    if (fromConnfdt)  {
                        /* If wf rfbdi ifrf, tifn it wbs donnfdt tibt invokfd
                         * rfdfivf bnd rfdfivfd tif COMM_UP. Wf ibvf blrfbdy
                         * ibndlfd tif COMM_UP witi tif intfrnbl notifidbtion
                         * ibndlfr. Simply rfturn. */
                        rfturn null;
                    }
                }  /* rfdfivfLodk */
            } wiilf (ibndlfr == null ? truf :
                (invokfNotifidbtionHbndlfr(rfsultContbinfr, ibndlfr, bttbdimfnt)
                 == HbndlfrRfsult.CONTINUE));

            rfturn null;
        } finblly {
            rfdfivfInvokfd.sft(Boolfbn.FALSE);
        }
    }

    privbtf int rfdfivf(int fd,
                        BytfBufffr dst,
                        RfsultContbinfr rfsultContbinfr,
                        boolfbn pffk)
            tirows IOExdfption {
        int pos = dst.position();
        int lim = dst.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);
        if (dst instbndfof DirfdtBufffr && rfm > 0)
            rfturn rfdfivfIntoNbtivfBufffr(fd, rfsultContbinfr, dst, rfm, pos, pffk);

        /* Substitutf b nbtivf bufffr */
        int nfwSizf = Mbti.mbx(rfm, 1);
        BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(nfwSizf);
        try {
            int n = rfdfivfIntoNbtivfBufffr(fd, rfsultContbinfr, bb, nfwSizf, 0, pffk);
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
                                        int pos,
                                        boolfbn pffk)
        tirows IOExdfption
    {
        int n = rfdfivf0(fd, rfsultContbinfr, ((DirfdtBufffr)bb).bddrfss() + pos, rfm, pffk);

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
            if (not.fvfnt().fqubls(
                    AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt.COMM_UP) &&
                    bssodibtion == null) {
                AssodibtionCibngf sbd = (AssodibtionCibngf) not;
                bssodibtion = nfw AssodibtionImpl
                       (sbd.bssodId(), sbd.mbxInStrfbms(), sbd.mbxOutStrfbms());
            }
            rfturn HbndlfrRfsult.CONTINUE;
        }
    }

    privbtf <T> HbndlfrRfsult invokfNotifidbtionHbndlfr
                                 (RfsultContbinfr rfsultContbinfr,
                                  NotifidbtionHbndlfr<T> ibndlfr,
                                  T bttbdimfnt) {
        SdtpNotifidbtion notifidbtion = rfsultContbinfr.notifidbtion();
        syndironizfd (stbtfLodk) {
            notifidbtion.sftAssodibtion(bssodibtion);
        }

        if (!(ibndlfr instbndfof AbstrbdtNotifidbtionHbndlfr)) {
            rfturn ibndlfr.ibndlfNotifidbtion(notifidbtion, bttbdimfnt);
        }

        /* AbstrbdtNotifidbtionHbndlfr */
        AbstrbdtNotifidbtionHbndlfr<T> bbsHbndlfr =
                (AbstrbdtNotifidbtionHbndlfr<T>)ibndlfr;
        switdi(rfsultContbinfr.typf()) {
            dbsf ASSOCIATION_CHANGED :
                rfturn bbsHbndlfr.ibndlfNotifidbtion(
                        rfsultContbinfr.gftAssodibtionCibngfd(), bttbdimfnt);
            dbsf PEER_ADDRESS_CHANGED :
                rfturn bbsHbndlfr.ibndlfNotifidbtion(
                        rfsultContbinfr.gftPffrAddrfssCibngfd(), bttbdimfnt);
            dbsf SEND_FAILED :
                rfturn bbsHbndlfr.ibndlfNotifidbtion(
                        rfsultContbinfr.gftSfndFbilfd(), bttbdimfnt);
            dbsf SHUTDOWN :
                rfturn bbsHbndlfr.ibndlfNotifidbtion(
                        rfsultContbinfr.gftSiutdown(), bttbdimfnt);
            dffbult :
                /* implfmfntbtion spfdifid ibndlfrs */
                rfturn bbsHbndlfr.ibndlfNotifidbtion(
                        rfsultContbinfr.notifidbtion(), bttbdimfnt);
        }
    }

    privbtf void difdkAssodibtion(Assodibtion sfndAssodibtion) {
        syndironizfd (stbtfLodk) {
            if (sfndAssodibtion != null && !sfndAssodibtion.fqubls(bssodibtion)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Cbnnot sfnd to bnotifr bssodibtion");
            }
        }
    }

    privbtf void difdkStrfbmNumbfr(int strfbmNumbfr) {
        syndironizfd (stbtfLodk) {
            if (bssodibtion != null) {
                if (strfbmNumbfr < 0 ||
                      strfbmNumbfr >= bssodibtion.mbxOutboundStrfbms())
                    tirow nfw InvblidStrfbmExdfption();
            }
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

        difdkAssodibtion(mfssbgfInfo.bssodibtion());
        difdkStrfbmNumbfr(mfssbgfInfo.strfbmNumbfr());

        syndironizfd (sfndLodk) {
            fnsurfSfndOpfn();

            int n = 0;
            try {
                bfgin();

                syndironizfd (stbtfLodk) {
                    if(!isOpfn())
                        rfturn 0;
                    sfndfrTirfbd = NbtivfTirfbd.durrfnt();
                }

                do {
                    n = sfnd(fdVbl, bufffr, mfssbgfInfo);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());

                rfturn IOStbtus.normblizf(n);
            } finblly {
                sfndfrClfbnup();
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    privbtf int sfnd(int fd, BytfBufffr srd, MfssbgfInfo mfssbgfInfo)
            tirows IOExdfption {
        int strfbmNumbfr = mfssbgfInfo.strfbmNumbfr();
        SodkftAddrfss tbrgft = mfssbgfInfo.bddrfss();
        boolfbn unordfrfd = mfssbgfInfo.isUnordfrfd();
        int ppid = mfssbgfInfo.pbylobdProtodolID();

        if (srd instbndfof DirfdtBufffr)
            rfturn sfndFromNbtivfBufffr(fd, srd, tbrgft, strfbmNumbfr,
                    unordfrfd, ppid);

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

            int n = sfndFromNbtivfBufffr(fd, bb, tbrgft, strfbmNumbfr,
                    unordfrfd, ppid);
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
                            port, -1 /*121*/, strfbmNumbfr, unordfrfd, ppid);
        if (writtfn > 0)
            bb.position(pos + writtfn);
        rfturn writtfn;
    }

    @Ovfrridf
    publid SdtpCibnnfl siutdown() tirows IOExdfption {
        syndironizfd(stbtfLodk) {
            if (isSiutdown)
                rfturn tiis;

            fnsurfSfndOpfn();
            SdtpNft.siutdown(fdVbl, -1);
            if (sfndfrTirfbd != 0)
                NbtivfTirfbd.signbl(sfndfrTirfbd);
            isSiutdown = truf;
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
    publid Sft<SodkftAddrfss> gftRfmotfAddrfssfs()
            tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (!isConnfdtfd() || isSiutdown)
                rfturn Collfdtions.fmptySft();

            try {
                rfturn SdtpNft.gftRfmotfAddrfssfs(fdVbl, 0/*unusfd*/);
            } dbtdi (SodkftExdfption unusfd) {
                /* bn opfn donnfdtfd dibnnfl siould blwbys ibvf rfmotf bddrfssfs */
                rfturn rfmotfAddrfssfs;
            }
        }
    }

    /* Nbtivf */
    privbtf stbtid nbtivf void initIDs();

    stbtid nbtivf int rfdfivf0(int fd, RfsultContbinfr rfsultContbinfr,
            long bddrfss, int lfngti, boolfbn pffk) tirows IOExdfption;

    stbtid nbtivf int sfnd0(int fd, long bddrfss, int lfngti,
            InftAddrfss bddr, int port, int bssodId, int strfbmNumbfr,
            boolfbn unordfrfd, int ppid) tirows IOExdfption;

    privbtf stbtid nbtivf int difdkConnfdt(FilfDfsdriptor fd, boolfbn blodk,
            boolfbn rfbdy) tirows IOExdfption;

    stbtid {
        IOUtil.lobd();   /* lobds nio & nft nbtivf librbrifs */
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("sdtp");
                    rfturn null;
                }
            });
        initIDs();
    }
}
