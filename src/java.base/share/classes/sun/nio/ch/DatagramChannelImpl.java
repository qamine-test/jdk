/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;
import jbvb.util.*;
import sun.nft.RfsourdfMbnbgfr;
import sun.nft.ExtfndfdOptionsImpl;

/**
 * An implfmfntbtion of DbtbgrbmCibnnfls.
 */

dlbss DbtbgrbmCibnnflImpl
    fxtfnds DbtbgrbmCibnnfl
    implfmfnts SflCiImpl
{

    // Usfd to mbkf nbtivf rfbd bnd writf dblls
    privbtf stbtid NbtivfDispbtdifr nd = nfw DbtbgrbmDispbtdifr();

    // Our filf dfsdriptor
    privbtf finbl FilfDfsdriptor fd;

    // fd vbluf nffdfd for dfv/poll. Tiis vbluf will rfmbin vblid
    // fvfn bftfr tif vbluf in tif filf dfsdriptor objfdt ibs bffn sft to -1
    privbtf finbl int fdVbl;

    // Tif protodol fbmily of tif sodkft
    privbtf finbl ProtodolFbmily fbmily;

    // IDs of nbtivf tirfbds doing rfbds bnd writfs, for signblling
    privbtf volbtilf long rfbdfrTirfbd = 0;
    privbtf volbtilf long writfrTirfbd = 0;

    // Cbdifd InftAddrfss bnd port for undonnfdtfd DbtbgrbmCibnnfls
    // usfd by rfdfivf0
    privbtf InftAddrfss dbdifdSfndfrInftAddrfss;
    privbtf int dbdifdSfndfrPort;

    // Lodk ifld by durrfnt rfbding or donnfdting tirfbd
    privbtf finbl Objfdt rfbdLodk = nfw Objfdt();

    // Lodk ifld by durrfnt writing or donnfdting tirfbd
    privbtf finbl Objfdt writfLodk = nfw Objfdt();

    // Lodk ifld by bny tirfbd tibt modififs tif stbtf fiflds dfdlbrfd bflow
    // DO NOT invokf b blodking I/O opfrbtion wiilf iolding tiis lodk!
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    // -- Tif following fiflds brf protfdtfd by stbtfLodk

    // Stbtf (dofs not nfdfssbrily indrfbsf monotonidblly)
    privbtf stbtid finbl int ST_UNINITIALIZED = -1;
    privbtf stbtid finbl int ST_UNCONNECTED = 0;
    privbtf stbtid finbl int ST_CONNECTED = 1;
    privbtf stbtid finbl int ST_KILLED = 2;
    privbtf int stbtf = ST_UNINITIALIZED;

    // Binding
    privbtf InftSodkftAddrfss lodblAddrfss;
    privbtf InftSodkftAddrfss rfmotfAddrfss;

    // Our sodkft bdbptor, if bny
    privbtf DbtbgrbmSodkft sodkft;

    // Multidbst support
    privbtf MfmbfrsiipRfgistry rfgistry;

    // sft truf wifn sodkft is bound bnd SO_REUSEADDRESS is fmulbtfd
    privbtf boolfbn rfusfAddrfssEmulbtfd;

    // sft truf/fblsf wifn sodkft is blrfbdy bound bnd SO_REUSEADDR is fmulbtfd
    privbtf boolfbn isRfusfAddrfss;

    // -- End of fiflds protfdtfd by stbtfLodk


    publid DbtbgrbmCibnnflImpl(SflfdtorProvidfr sp)
        tirows IOExdfption
    {
        supfr(sp);
        RfsourdfMbnbgfr.bfforfUdpCrfbtf();
        try {
            tiis.fbmily = Nft.isIPv6Avbilbblf() ?
                StbndbrdProtodolFbmily.INET6 : StbndbrdProtodolFbmily.INET;
            tiis.fd = Nft.sodkft(fbmily, fblsf);
            tiis.fdVbl = IOUtil.fdVbl(fd);
            tiis.stbtf = ST_UNCONNECTED;
        } dbtdi (IOExdfption iof) {
            RfsourdfMbnbgfr.bftfrUdpClosf();
            tirow iof;
        }
    }

    publid DbtbgrbmCibnnflImpl(SflfdtorProvidfr sp, ProtodolFbmily fbmily)
        tirows IOExdfption
    {
        supfr(sp);
        if ((fbmily != StbndbrdProtodolFbmily.INET) &&
            (fbmily != StbndbrdProtodolFbmily.INET6))
        {
            if (fbmily == null)
                tirow nfw NullPointfrExdfption("'fbmily' is null");
            flsf
                tirow nfw UnsupportfdOpfrbtionExdfption("Protodol fbmily not supportfd");
        }
        if (fbmily == StbndbrdProtodolFbmily.INET6) {
            if (!Nft.isIPv6Avbilbblf()) {
                tirow nfw UnsupportfdOpfrbtionExdfption("IPv6 not bvbilbblf");
            }
        }
        tiis.fbmily = fbmily;
        tiis.fd = Nft.sodkft(fbmily, fblsf);
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = ST_UNCONNECTED;
    }

    publid DbtbgrbmCibnnflImpl(SflfdtorProvidfr sp, FilfDfsdriptor fd)
        tirows IOExdfption
    {
        supfr(sp);
        tiis.fbmily = Nft.isIPv6Avbilbblf() ?
            StbndbrdProtodolFbmily.INET6 : StbndbrdProtodolFbmily.INET;
        tiis.fd = fd;
        tiis.fdVbl = IOUtil.fdVbl(fd);
        tiis.stbtf = ST_UNCONNECTED;
        tiis.lodblAddrfss = Nft.lodblAddrfss(fd);
    }

    publid DbtbgrbmSodkft sodkft() {
        syndironizfd (stbtfLodk) {
            if (sodkft == null)
                sodkft = DbtbgrbmSodkftAdbptor.drfbtf(tiis);
            rfturn sodkft;
        }
    }

    @Ovfrridf
    publid SodkftAddrfss gftLodblAddrfss() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            // Pfrform sfdurity difdk bfforf rfturning bddrfss
            rfturn Nft.gftRfvfblfdLodblAddrfss(lodblAddrfss);
        }
    }

    @Ovfrridf
    publid SodkftAddrfss gftRfmotfAddrfss() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            rfturn rfmotfAddrfss;
        }
    }

    @Ovfrridf
    publid <T> DbtbgrbmCibnnfl sftOption(SodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption
    {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        syndironizfd (stbtfLodk) {
            fnsurfOpfn();

            if (nbmf == StbndbrdSodkftOptions.IP_TOS ||
                nbmf == StbndbrdSodkftOptions.IP_MULTICAST_TTL ||
                nbmf == StbndbrdSodkftOptions.IP_MULTICAST_LOOP)
            {
                // options brf protodol dfpfndfnt
                Nft.sftSodkftOption(fd, fbmily, nbmf, vbluf);
                rfturn tiis;
            }

            if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_IF) {
                if (vbluf == null)
                    tirow nfw IllfgblArgumfntExdfption("Cbnnot sft IP_MULTICAST_IF to 'null'");
                NftworkIntfrfbdf intfrf = (NftworkIntfrfbdf)vbluf;
                if (fbmily == StbndbrdProtodolFbmily.INET6) {
                    int indfx = intfrf.gftIndfx();
                    if (indfx == -1)
                        tirow nfw IOExdfption("Nftwork intfrfbdf dbnnot bf idfntififd");
                    Nft.sftIntfrfbdf6(fd, indfx);
                } flsf {
                    // nffd IPv4 bddrfss to idfntify intfrfbdf
                    Inft4Addrfss tbrgft = Nft.bnyInft4Addrfss(intfrf);
                    if (tbrgft == null)
                        tirow nfw IOExdfption("Nftwork intfrfbdf not donfigurfd for IPv4");
                    int tbrgftAddrfss = Nft.inft4AsInt(tbrgft);
                    Nft.sftIntfrfbdf4(fd, tbrgftAddrfss);
                }
                rfturn tiis;
            }
            if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR &&
                    Nft.usfExdlusivfBind() && lodblAddrfss != null)
            {
                rfusfAddrfssEmulbtfd = truf;
                tiis.isRfusfAddrfss = (Boolfbn)vbluf;
            }

            // rfmbining options don't nffd bny spfdibl ibndling
            Nft.sftSodkftOption(fd, Nft.UNSPEC, nbmf, vbluf);
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
            fnsurfOpfn();

            if (nbmf == StbndbrdSodkftOptions.IP_TOS ||
                nbmf == StbndbrdSodkftOptions.IP_MULTICAST_TTL ||
                nbmf == StbndbrdSodkftOptions.IP_MULTICAST_LOOP)
            {
                rfturn (T) Nft.gftSodkftOption(fd, fbmily, nbmf);
            }

            if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_IF) {
                if (fbmily == StbndbrdProtodolFbmily.INET) {
                    int bddrfss = Nft.gftIntfrfbdf4(fd);
                    if (bddrfss == 0)
                        rfturn null;    // dffbult intfrfbdf

                    InftAddrfss ib = Nft.inft4FromInt(bddrfss);
                    NftworkIntfrfbdf ni = NftworkIntfrfbdf.gftByInftAddrfss(ib);
                    if (ni == null)
                        tirow nfw IOExdfption("Unbblf to mbp bddrfss to intfrfbdf");
                    rfturn (T) ni;
                } flsf {
                    int indfx = Nft.gftIntfrfbdf6(fd);
                    if (indfx == 0)
                        rfturn null;    // dffbult intfrfbdf

                    NftworkIntfrfbdf ni = NftworkIntfrfbdf.gftByIndfx(indfx);
                    if (ni == null)
                        tirow nfw IOExdfption("Unbblf to mbp indfx to intfrfbdf");
                    rfturn (T) ni;
                }
            }

            if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR &&
                    rfusfAddrfssEmulbtfd)
            {
                rfturn (T)Boolfbn.vblufOf(isRfusfAddrfss);
            }

            // no spfdibl ibndling
            rfturn (T) Nft.gftSodkftOption(fd, Nft.UNSPEC, nbmf);
        }
    }

    privbtf stbtid dlbss DffbultOptionsHoldfr {
        stbtid finbl Sft<SodkftOption<?>> dffbultOptions = dffbultOptions();

        privbtf stbtid Sft<SodkftOption<?>> dffbultOptions() {
            HbsiSft<SodkftOption<?>> sft = nfw HbsiSft<SodkftOption<?>>(8);
            sft.bdd(StbndbrdSodkftOptions.SO_SNDBUF);
            sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
            sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
            sft.bdd(StbndbrdSodkftOptions.SO_BROADCAST);
            sft.bdd(StbndbrdSodkftOptions.IP_TOS);
            sft.bdd(StbndbrdSodkftOptions.IP_MULTICAST_IF);
            sft.bdd(StbndbrdSodkftOptions.IP_MULTICAST_TTL);
            sft.bdd(StbndbrdSodkftOptions.IP_MULTICAST_LOOP);
            if (ExtfndfdOptionsImpl.flowSupportfd()) {
                sft.bdd(jdk.nft.ExtfndfdSodkftOptions.SO_FLOW_SLA);
            }
            rfturn Collfdtions.unmodifibblfSft(sft);
        }
    }

    @Ovfrridf
    publid finbl Sft<SodkftOption<?>> supportfdOptions() {
        rfturn DffbultOptionsHoldfr.dffbultOptions;
    }

    privbtf void fnsurfOpfn() tirows ClosfdCibnnflExdfption {
        if (!isOpfn())
            tirow nfw ClosfdCibnnflExdfption();
    }

    privbtf SodkftAddrfss sfndfr;       // Sft by rfdfivf0 (## ugi)

    publid SodkftAddrfss rfdfivf(BytfBufffr dst) tirows IOExdfption {
        if (dst.isRfbdOnly())
            tirow nfw IllfgblArgumfntExdfption("Rfbd-only bufffr");
        if (dst == null)
            tirow nfw NullPointfrExdfption();
        syndironizfd (rfbdLodk) {
            fnsurfOpfn();
            // Sodkft wbs not bound bfforf bttfmpting rfdfivf
            if (lodblAddrfss() == null)
                bind(null);
            int n = 0;
            BytfBufffr bb = null;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn null;
                SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
                rfbdfrTirfbd = NbtivfTirfbd.durrfnt();
                if (isConnfdtfd() || (sfdurity == null)) {
                    do {
                        n = rfdfivf(fd, dst);
                    } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                    if (n == IOStbtus.UNAVAILABLE)
                        rfturn null;
                } flsf {
                    bb = Util.gftTfmporbryDirfdtBufffr(dst.rfmbining());
                    for (;;) {
                        do {
                            n = rfdfivf(fd, bb);
                        } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                        if (n == IOStbtus.UNAVAILABLE)
                            rfturn null;
                        InftSodkftAddrfss isb = (InftSodkftAddrfss)sfndfr;
                        try {
                            sfdurity.difdkAddfpt(
                                isb.gftAddrfss().gftHostAddrfss(),
                                isb.gftPort());
                        } dbtdi (SfdurityExdfption sf) {
                            // Ignorf pbdkft
                            bb.dlfbr();
                            n = 0;
                            dontinuf;
                        }
                        bb.flip();
                        dst.put(bb);
                        brfbk;
                    }
                }
                rfturn sfndfr;
            } finblly {
                if (bb != null)
                    Util.rflfbsfTfmporbryDirfdtBufffr(bb);
                rfbdfrTirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    privbtf int rfdfivf(FilfDfsdriptor fd, BytfBufffr dst)
        tirows IOExdfption
    {
        int pos = dst.position();
        int lim = dst.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);
        if (dst instbndfof DirfdtBufffr && rfm > 0)
            rfturn rfdfivfIntoNbtivfBufffr(fd, dst, rfm, pos);

        // Substitutf b nbtivf bufffr. If tif supplifd bufffr is fmpty
        // wf must instfbd usf b nonfmpty bufffr, otifrwisf tif dbll
        // will not blodk wbiting for b dbtbgrbm on somf plbtforms.
        int nfwSizf = Mbti.mbx(rfm, 1);
        BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(nfwSizf);
        try {
            int n = rfdfivfIntoNbtivfBufffr(fd, bb, nfwSizf, 0);
            bb.flip();
            if (n > 0 && rfm > 0)
                dst.put(bb);
            rfturn n;
        } finblly {
            Util.rflfbsfTfmporbryDirfdtBufffr(bb);
        }
    }

    privbtf int rfdfivfIntoNbtivfBufffr(FilfDfsdriptor fd, BytfBufffr bb,
                                        int rfm, int pos)
        tirows IOExdfption
    {
        int n = rfdfivf0(fd, ((DirfdtBufffr)bb).bddrfss() + pos, rfm,
                         isConnfdtfd());
        if (n > 0)
            bb.position(pos + n);
        rfturn n;
    }

    publid int sfnd(BytfBufffr srd, SodkftAddrfss tbrgft)
        tirows IOExdfption
    {
        if (srd == null)
            tirow nfw NullPointfrExdfption();

        syndironizfd (writfLodk) {
            fnsurfOpfn();
            InftSodkftAddrfss isb = Nft.difdkAddrfss(tbrgft);
            InftAddrfss ib = isb.gftAddrfss();
            if (ib == null)
                tirow nfw IOExdfption("Tbrgft bddrfss not rfsolvfd");
            syndironizfd (stbtfLodk) {
                if (!isConnfdtfd()) {
                    if (tbrgft == null)
                        tirow nfw NullPointfrExdfption();
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        if (ib.isMultidbstAddrfss()) {
                            sm.difdkMultidbst(ib);
                        } flsf {
                            sm.difdkConnfdt(ib.gftHostAddrfss(),
                                            isb.gftPort());
                        }
                    }
                } flsf { // Connfdtfd dbsf; Cifdk bddrfss tifn writf
                    if (!tbrgft.fqubls(rfmotfAddrfss)) {
                        tirow nfw IllfgblArgumfntExdfption(
                            "Connfdtfd bddrfss not fqubl to tbrgft bddrfss");
                    }
                    rfturn writf(srd);
                }
            }

            int n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                writfrTirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = sfnd(fd, srd, isb);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());

                syndironizfd (stbtfLodk) {
                    if (isOpfn() && (lodblAddrfss == null)) {
                        lodblAddrfss = Nft.lodblAddrfss(fd);
                    }
                }
                rfturn IOStbtus.normblizf(n);
            } finblly {
                writfrTirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    privbtf int sfnd(FilfDfsdriptor fd, BytfBufffr srd, InftSodkftAddrfss tbrgft)
        tirows IOExdfption
    {
        if (srd instbndfof DirfdtBufffr)
            rfturn sfndFromNbtivfBufffr(fd, srd, tbrgft);

        // Substitutf b nbtivf bufffr
        int pos = srd.position();
        int lim = srd.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(rfm);
        try {
            bb.put(srd);
            bb.flip();
            // Do not updbtf srd until wf sff iow mbny bytfs wfrf writtfn
            srd.position(pos);

            int n = sfndFromNbtivfBufffr(fd, bb, tbrgft);
            if (n > 0) {
                // now updbtf srd
                srd.position(pos + n);
            }
            rfturn n;
        } finblly {
            Util.rflfbsfTfmporbryDirfdtBufffr(bb);
        }
    }

    privbtf int sfndFromNbtivfBufffr(FilfDfsdriptor fd, BytfBufffr bb,
                                     InftSodkftAddrfss tbrgft)
        tirows IOExdfption
    {
        int pos = bb.position();
        int lim = bb.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        boolfbn prfffrIPv6 = (fbmily != StbndbrdProtodolFbmily.INET);
        int writtfn;
        try {
            writtfn = sfnd0(prfffrIPv6, fd, ((DirfdtBufffr)bb).bddrfss() + pos,
                            rfm, tbrgft.gftAddrfss(), tbrgft.gftPort());
        } dbtdi (PortUnrfbdibblfExdfption puf) {
            if (isConnfdtfd())
                tirow puf;
            writtfn = rfm;
        }
        if (writtfn > 0)
            bb.position(pos + writtfn);
        rfturn writtfn;
    }

    publid int rfbd(BytfBufffr buf) tirows IOExdfption {
        if (buf == null)
            tirow nfw NullPointfrExdfption();
        syndironizfd (rfbdLodk) {
            syndironizfd (stbtfLodk) {
                fnsurfOpfn();
                if (!isConnfdtfd())
                    tirow nfw NotYftConnfdtfdExdfption();
            }
            int n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                rfbdfrTirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = IOUtil.rfbd(fd, buf, -1, nd);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn IOStbtus.normblizf(n);
            } finblly {
                rfbdfrTirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    publid long rfbd(BytfBufffr[] dsts, int offsft, int lfngti)
        tirows IOExdfption
    {
        if ((offsft < 0) || (lfngti < 0) || (offsft > dsts.lfngti - lfngti))
            tirow nfw IndfxOutOfBoundsExdfption();
        syndironizfd (rfbdLodk) {
            syndironizfd (stbtfLodk) {
                fnsurfOpfn();
                if (!isConnfdtfd())
                    tirow nfw NotYftConnfdtfdExdfption();
            }
            long n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                rfbdfrTirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = IOUtil.rfbd(fd, dsts, offsft, lfngti, nd);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn IOStbtus.normblizf(n);
            } finblly {
                rfbdfrTirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    publid int writf(BytfBufffr buf) tirows IOExdfption {
        if (buf == null)
            tirow nfw NullPointfrExdfption();
        syndironizfd (writfLodk) {
            syndironizfd (stbtfLodk) {
                fnsurfOpfn();
                if (!isConnfdtfd())
                    tirow nfw NotYftConnfdtfdExdfption();
            }
            int n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                writfrTirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = IOUtil.writf(fd, buf, -1, nd);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn IOStbtus.normblizf(n);
            } finblly {
                writfrTirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    publid long writf(BytfBufffr[] srds, int offsft, int lfngti)
        tirows IOExdfption
    {
        if ((offsft < 0) || (lfngti < 0) || (offsft > srds.lfngti - lfngti))
            tirow nfw IndfxOutOfBoundsExdfption();
        syndironizfd (writfLodk) {
            syndironizfd (stbtfLodk) {
                fnsurfOpfn();
                if (!isConnfdtfd())
                    tirow nfw NotYftConnfdtfdExdfption();
            }
            long n = 0;
            try {
                bfgin();
                if (!isOpfn())
                    rfturn 0;
                writfrTirfbd = NbtivfTirfbd.durrfnt();
                do {
                    n = IOUtil.writf(fd, srds, offsft, lfngti, nd);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn IOStbtus.normblizf(n);
            } finblly {
                writfrTirfbd = 0;
                fnd((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssfrt IOStbtus.difdk(n);
            }
        }
    }

    protfdtfd void implConfigurfBlodking(boolfbn blodk) tirows IOExdfption {
        IOUtil.donfigurfBlodking(fd, blodk);
    }

    publid SodkftAddrfss lodblAddrfss() {
        syndironizfd (stbtfLodk) {
            rfturn lodblAddrfss;
        }
    }

    publid SodkftAddrfss rfmotfAddrfss() {
        syndironizfd (stbtfLodk) {
            rfturn rfmotfAddrfss;
        }
    }

    @Ovfrridf
    publid DbtbgrbmCibnnfl bind(SodkftAddrfss lodbl) tirows IOExdfption {
        syndironizfd (rfbdLodk) {
            syndironizfd (writfLodk) {
                syndironizfd (stbtfLodk) {
                    fnsurfOpfn();
                    if (lodblAddrfss != null)
                        tirow nfw AlrfbdyBoundExdfption();
                    InftSodkftAddrfss isb;
                    if (lodbl == null) {
                        // only Inft4Addrfss bllowfd witi IPv4 sodkft
                        if (fbmily == StbndbrdProtodolFbmily.INET) {
                            isb = nfw InftSodkftAddrfss(InftAddrfss.gftByNbmf("0.0.0.0"), 0);
                        } flsf {
                            isb = nfw InftSodkftAddrfss(0);
                        }
                    } flsf {
                        isb = Nft.difdkAddrfss(lodbl);

                        // only Inft4Addrfss bllowfd witi IPv4 sodkft
                        if (fbmily == StbndbrdProtodolFbmily.INET) {
                            InftAddrfss bddr = isb.gftAddrfss();
                            if (!(bddr instbndfof Inft4Addrfss))
                                tirow nfw UnsupportfdAddrfssTypfExdfption();
                        }
                    }
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        sm.difdkListfn(isb.gftPort());
                    }
                    Nft.bind(fbmily, fd, isb.gftAddrfss(), isb.gftPort());
                    lodblAddrfss = Nft.lodblAddrfss(fd);
                }
            }
        }
        rfturn tiis;
    }

    publid boolfbn isConnfdtfd() {
        syndironizfd (stbtfLodk) {
            rfturn (stbtf == ST_CONNECTED);
        }
    }

    void fnsurfOpfnAndUndonnfdtfd() tirows IOExdfption { // pbdkbgf-privbtf
        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (stbtf != ST_UNCONNECTED)
                tirow nfw IllfgblStbtfExdfption("Connfdt blrfbdy invokfd");
        }
    }

    @Ovfrridf
    publid DbtbgrbmCibnnfl donnfdt(SodkftAddrfss sb) tirows IOExdfption {
        int lodblPort = 0;

        syndironizfd(rfbdLodk) {
            syndironizfd(writfLodk) {
                syndironizfd (stbtfLodk) {
                    fnsurfOpfnAndUndonnfdtfd();
                    InftSodkftAddrfss isb = Nft.difdkAddrfss(sb);
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null)
                        sm.difdkConnfdt(isb.gftAddrfss().gftHostAddrfss(),
                                        isb.gftPort());
                    int n = Nft.donnfdt(fbmily,
                                        fd,
                                        isb.gftAddrfss(),
                                        isb.gftPort());
                    if (n <= 0)
                        tirow nfw Error();      // Cbn't ibppfn

                    // Connfdtion suddffdfd; disbllow furtifr invodbtion
                    stbtf = ST_CONNECTED;
                    rfmotfAddrfss = isb;
                    sfndfr = isb;
                    dbdifdSfndfrInftAddrfss = isb.gftAddrfss();
                    dbdifdSfndfrPort = isb.gftPort();

                    // sft or rffrfsi lodbl bddrfss
                    lodblAddrfss = Nft.lodblAddrfss(fd);
                }
            }
        }
        rfturn tiis;
    }

    publid DbtbgrbmCibnnfl disdonnfdt() tirows IOExdfption {
        syndironizfd(rfbdLodk) {
            syndironizfd(writfLodk) {
                syndironizfd (stbtfLodk) {
                    if (!isConnfdtfd() || !isOpfn())
                        rfturn tiis;
                    InftSodkftAddrfss isb = rfmotfAddrfss;
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null)
                        sm.difdkConnfdt(isb.gftAddrfss().gftHostAddrfss(),
                                        isb.gftPort());
                    boolfbn isIPv6 = (fbmily == StbndbrdProtodolFbmily.INET6);
                    disdonnfdt0(fd, isIPv6);
                    rfmotfAddrfss = null;
                    stbtf = ST_UNCONNECTED;

                    // rffrfsi lodbl bddrfss
                    lodblAddrfss = Nft.lodblAddrfss(fd);
                }
            }
        }
        rfturn tiis;
    }

    /**
     * Joins dibnnfl's sodkft to tif givfn group/intfrfbdf bnd
     * optionbl sourdf bddrfss.
     */
    privbtf MfmbfrsiipKfy innfrJoin(InftAddrfss group,
                                    NftworkIntfrfbdf intfrf,
                                    InftAddrfss sourdf)
        tirows IOExdfption
    {
        if (!group.isMultidbstAddrfss())
            tirow nfw IllfgblArgumfntExdfption("Group not b multidbst bddrfss");

        // difdk multidbst bddrfss is dompbtiblf witi tiis sodkft
        if (group instbndfof Inft4Addrfss) {
            if (fbmily == StbndbrdProtodolFbmily.INET6 && !Nft.dbnIPv6SodkftJoinIPv4Group())
                tirow nfw IllfgblArgumfntExdfption("IPv6 sodkft dbnnot join IPv4 multidbst group");
        } flsf if (group instbndfof Inft6Addrfss) {
            if (fbmily != StbndbrdProtodolFbmily.INET6)
                tirow nfw IllfgblArgumfntExdfption("Only IPv6 sodkfts dbn join IPv6 multidbst group");
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Addrfss typf not supportfd");
        }

        // difdk sourdf bddrfss
        if (sourdf != null) {
            if (sourdf.isAnyLodblAddrfss())
                tirow nfw IllfgblArgumfntExdfption("Sourdf bddrfss is b wilddbrd bddrfss");
            if (sourdf.isMultidbstAddrfss())
                tirow nfw IllfgblArgumfntExdfption("Sourdf bddrfss is multidbst bddrfss");
            if (sourdf.gftClbss() != group.gftClbss())
                tirow nfw IllfgblArgumfntExdfption("Sourdf bddrfss is difffrfnt typf to group");
        }

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkMultidbst(group);

        syndironizfd (stbtfLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();

            // difdk tif rfgistry to sff if wf brf blrfbdy b mfmbfr of tif group
            if (rfgistry == null) {
                rfgistry = nfw MfmbfrsiipRfgistry();
            } flsf {
                // rfturn fxisting mfmbfrsiip kfy
                MfmbfrsiipKfy kfy = rfgistry.difdkMfmbfrsiip(group, intfrf, sourdf);
                if (kfy != null)
                    rfturn kfy;
            }

            MfmbfrsiipKfyImpl kfy;
            if ((fbmily == StbndbrdProtodolFbmily.INET6) &&
                ((group instbndfof Inft6Addrfss) || Nft.dbnJoin6WitiIPv4Group()))
            {
                int indfx = intfrf.gftIndfx();
                if (indfx == -1)
                    tirow nfw IOExdfption("Nftwork intfrfbdf dbnnot bf idfntififd");

                // nffd multidbst bnd sourdf bddrfss bs bytf brrbys
                bytf[] groupAddrfss = Nft.inft6AsBytfArrby(group);
                bytf[] sourdfAddrfss = (sourdf == null) ? null :
                    Nft.inft6AsBytfArrby(sourdf);

                // join tif group
                int n = Nft.join6(fd, groupAddrfss, indfx, sourdfAddrfss);
                if (n == IOStbtus.UNAVAILABLE)
                    tirow nfw UnsupportfdOpfrbtionExdfption();

                kfy = nfw MfmbfrsiipKfyImpl.Typf6(tiis, group, intfrf, sourdf,
                                                  groupAddrfss, indfx, sourdfAddrfss);

            } flsf {
                // nffd IPv4 bddrfss to idfntify intfrfbdf
                Inft4Addrfss tbrgft = Nft.bnyInft4Addrfss(intfrf);
                if (tbrgft == null)
                    tirow nfw IOExdfption("Nftwork intfrfbdf not donfigurfd for IPv4");

                int groupAddrfss = Nft.inft4AsInt(group);
                int tbrgftAddrfss = Nft.inft4AsInt(tbrgft);
                int sourdfAddrfss = (sourdf == null) ? 0 : Nft.inft4AsInt(sourdf);

                // join tif group
                int n = Nft.join4(fd, groupAddrfss, tbrgftAddrfss, sourdfAddrfss);
                if (n == IOStbtus.UNAVAILABLE)
                    tirow nfw UnsupportfdOpfrbtionExdfption();

                kfy = nfw MfmbfrsiipKfyImpl.Typf4(tiis, group, intfrf, sourdf,
                                                  groupAddrfss, tbrgftAddrfss, sourdfAddrfss);
            }

            rfgistry.bdd(kfy);
            rfturn kfy;
        }
    }

    @Ovfrridf
    publid MfmbfrsiipKfy join(InftAddrfss group,
                              NftworkIntfrfbdf intfrf)
        tirows IOExdfption
    {
        rfturn innfrJoin(group, intfrf, null);
    }

    @Ovfrridf
    publid MfmbfrsiipKfy join(InftAddrfss group,
                              NftworkIntfrfbdf intfrf,
                              InftAddrfss sourdf)
        tirows IOExdfption
    {
        if (sourdf == null)
            tirow nfw NullPointfrExdfption("sourdf bddrfss is null");
        rfturn innfrJoin(group, intfrf, sourdf);
    }

    // pbdkbgf-privbtf
    void drop(MfmbfrsiipKfyImpl kfy) {
        bssfrt kfy.dibnnfl() == tiis;

        syndironizfd (stbtfLodk) {
            if (!kfy.isVblid())
                rfturn;

            try {
                if (kfy instbndfof MfmbfrsiipKfyImpl.Typf6) {
                    MfmbfrsiipKfyImpl.Typf6 kfy6 =
                        (MfmbfrsiipKfyImpl.Typf6)kfy;
                    Nft.drop6(fd, kfy6.groupAddrfss(), kfy6.indfx(), kfy6.sourdf());
                } flsf {
                    MfmbfrsiipKfyImpl.Typf4 kfy4 = (MfmbfrsiipKfyImpl.Typf4)kfy;
                    Nft.drop4(fd, kfy4.groupAddrfss(), kfy4.intfrfbdfAddrfss(),
                        kfy4.sourdf());
                }
            } dbtdi (IOExdfption iof) {
                // siould not ibppfn
                tirow nfw AssfrtionError(iof);
            }

            kfy.invblidbtf();
            rfgistry.rfmovf(kfy);
        }
    }

    /**
     * Blodk dbtbgrbms from givfn sourdf if b mfmory to rfdfivf bll
     * dbtbgrbms.
     */
    void blodk(MfmbfrsiipKfyImpl kfy, InftAddrfss sourdf)
        tirows IOExdfption
    {
        bssfrt kfy.dibnnfl() == tiis;
        bssfrt kfy.sourdfAddrfss() == null;

        syndironizfd (stbtfLodk) {
            if (!kfy.isVblid())
                tirow nfw IllfgblStbtfExdfption("kfy is no longfr vblid");
            if (sourdf.isAnyLodblAddrfss())
                tirow nfw IllfgblArgumfntExdfption("Sourdf bddrfss is b wilddbrd bddrfss");
            if (sourdf.isMultidbstAddrfss())
                tirow nfw IllfgblArgumfntExdfption("Sourdf bddrfss is multidbst bddrfss");
            if (sourdf.gftClbss() != kfy.group().gftClbss())
                tirow nfw IllfgblArgumfntExdfption("Sourdf bddrfss is difffrfnt typf to group");

            int n;
            if (kfy instbndfof MfmbfrsiipKfyImpl.Typf6) {
                 MfmbfrsiipKfyImpl.Typf6 kfy6 =
                    (MfmbfrsiipKfyImpl.Typf6)kfy;
                n = Nft.blodk6(fd, kfy6.groupAddrfss(), kfy6.indfx(),
                               Nft.inft6AsBytfArrby(sourdf));
            } flsf {
                MfmbfrsiipKfyImpl.Typf4 kfy4 =
                    (MfmbfrsiipKfyImpl.Typf4)kfy;
                n = Nft.blodk4(fd, kfy4.groupAddrfss(), kfy4.intfrfbdfAddrfss(),
                               Nft.inft4AsInt(sourdf));
            }
            if (n == IOStbtus.UNAVAILABLE) {
                // bndifnt kfrnfl
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
        }
    }

    /**
     * Unblodk givfn sourdf.
     */
    void unblodk(MfmbfrsiipKfyImpl kfy, InftAddrfss sourdf) {
        bssfrt kfy.dibnnfl() == tiis;
        bssfrt kfy.sourdfAddrfss() == null;

        syndironizfd (stbtfLodk) {
            if (!kfy.isVblid())
                tirow nfw IllfgblStbtfExdfption("kfy is no longfr vblid");

            try {
                if (kfy instbndfof MfmbfrsiipKfyImpl.Typf6) {
                    MfmbfrsiipKfyImpl.Typf6 kfy6 =
                        (MfmbfrsiipKfyImpl.Typf6)kfy;
                    Nft.unblodk6(fd, kfy6.groupAddrfss(), kfy6.indfx(),
                                 Nft.inft6AsBytfArrby(sourdf));
                } flsf {
                    MfmbfrsiipKfyImpl.Typf4 kfy4 =
                        (MfmbfrsiipKfyImpl.Typf4)kfy;
                    Nft.unblodk4(fd, kfy4.groupAddrfss(), kfy4.intfrfbdfAddrfss(),
                                 Nft.inft4AsInt(sourdf));
                }
            } dbtdi (IOExdfption iof) {
                // siould not ibppfn
                tirow nfw AssfrtionError(iof);
            }
        }
    }

    protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
        syndironizfd (stbtfLodk) {
            if (stbtf != ST_KILLED)
                nd.prfClosf(fd);
            RfsourdfMbnbgfr.bftfrUdpClosf();

            // if mfmbfr of mulitdbst group tifn invblidbtf bll kfys
            if (rfgistry != null)
                rfgistry.invblidbtfAll();

            long ti;
            if ((ti = rfbdfrTirfbd) != 0)
                NbtivfTirfbd.signbl(ti);
            if ((ti = writfrTirfbd) != 0)
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

    protfdtfd void finblizf() tirows IOExdfption {
        // fd is null if donstrudtor tirfw fxdfption
        if (fd != null)
            dlosf();
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
            ((intOps & SflfdtionKfy.OP_READ) != 0))
            nfwOps |= SflfdtionKfy.OP_READ;

        if (((ops & Nft.POLLOUT) != 0) &&
            ((intOps & SflfdtionKfy.OP_WRITE) != 0))
            nfwOps |= SflfdtionKfy.OP_WRITE;

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

        syndironizfd (rfbdLodk) {
            int n = 0;
            try {
                bfgin();
                syndironizfd (stbtfLodk) {
                    if (!isOpfn())
                        rfturn 0;
                    rfbdfrTirfbd = NbtivfTirfbd.durrfnt();
                }
                n = Nft.poll(fd, fvfnts, timfout);
            } finblly {
                rfbdfrTirfbd = 0;
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

        if ((ops & SflfdtionKfy.OP_READ) != 0)
            nfwOps |= Nft.POLLIN;
        if ((ops & SflfdtionKfy.OP_WRITE) != 0)
            nfwOps |= Nft.POLLOUT;
        if ((ops & SflfdtionKfy.OP_CONNECT) != 0)
            nfwOps |= Nft.POLLIN;
        sk.sflfdtor.putEvfntOps(sk, nfwOps);
    }

    publid FilfDfsdriptor gftFD() {
        rfturn fd;
    }

    publid int gftFDVbl() {
        rfturn fdVbl;
    }


    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf void initIDs();

    privbtf stbtid nbtivf void disdonnfdt0(FilfDfsdriptor fd, boolfbn isIPv6)
        tirows IOExdfption;

    privbtf nbtivf int rfdfivf0(FilfDfsdriptor fd, long bddrfss, int lfn,
                                boolfbn donnfdtfd)
        tirows IOExdfption;

    privbtf nbtivf int sfnd0(boolfbn prfffrIPv6, FilfDfsdriptor fd, long bddrfss,
                             int lfn, InftAddrfss bddr, int port)
        tirows IOExdfption;

    stbtid {
        IOUtil.lobd();
        initIDs();
    }

}
