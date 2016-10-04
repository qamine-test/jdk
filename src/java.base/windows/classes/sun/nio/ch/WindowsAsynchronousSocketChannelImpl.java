/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.dibnnfls.*;
import jbvb.nio.BytfBufffr;
import jbvb.nio.BufffrOvfrflowExdfption;
import jbvb.nft.*;
import jbvb.util.dondurrfnt.*;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import sun.misd.Unsbff;

/**
 * Windows implfmfntbtion of AsyndironousSodkftCibnnfl using ovfrlbppfd I/O.
 */

dlbss WindowsAsyndironousSodkftCibnnflImpl
    fxtfnds AsyndironousSodkftCibnnflImpl implfmfnts Iodp.OvfrlbppfdCibnnfl
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid int bddrfssSizf = unsbff.bddrfssSizf();

    privbtf stbtid int dfpfndsArdi(int vbluf32, int vbluf64) {
        rfturn (bddrfssSizf == 4) ? vbluf32 : vbluf64;
    }

    /*
     * typfdff strudt _WSABUF {
     *     u_long      lfn;
     *     dibr FAR *  buf;
     * } WSABUF;
     */
    privbtf stbtid finbl int SIZEOF_WSABUF  = dfpfndsArdi(8, 16);
    privbtf stbtid finbl int OFFSETOF_LEN   = 0;
    privbtf stbtid finbl int OFFSETOF_BUF   = dfpfndsArdi(4, 8);

    // mbximum vfdtor sizf for sdbttfr/gbtifr I/O
    privbtf stbtid finbl int MAX_WSABUF     = 16;

    privbtf stbtid finbl int SIZEOF_WSABUFARRAY = MAX_WSABUF * SIZEOF_WSABUF;


    // sodkft ibndlf. Usf bfgin()/fnd() bround fbdi usbgf of tiis ibndlf.
    finbl long ibndlf;

    // I/O domplftion port tibt tif sodkft is bssodibtfd witi
    privbtf finbl Iodp iodp;

    // domplftion kfy to idfntify dibnnfl wifn I/O domplftfs
    privbtf finbl int domplftionKfy;

    // Pfnding I/O opfrbtions brf tifd to bn OVERLAPPED strudturf tibt dbn only
    // bf rflfbsfd wifn tif I/O domplftion fvfnt is postfd to tif domplftion
    // port. Wifrf I/O opfrbtions domplftf immfdibtfly tifn it is possiblf
    // tifrf mby bf morf tibn two OVERLAPPED strudturfs in usf.
    privbtf finbl PfndingIoCbdif ioCbdif;

    // pfr-dibnnfl brrbys of WSABUF strudturfs
    privbtf finbl long rfbdBufffrArrby;
    privbtf finbl long writfBufffrArrby;


    WindowsAsyndironousSodkftCibnnflImpl(Iodp iodp, boolfbn fbilIfGroupSiutdown)
        tirows IOExdfption
    {
        supfr(iodp);

        // bssodibtf sodkft witi dffbult domplftion port
        long i = IOUtil.fdVbl(fd);
        int kfy = 0;
        try {
            kfy = iodp.bssodibtf(tiis, i);
        } dbtdi (SiutdownCibnnflGroupExdfption x) {
            if (fbilIfGroupSiutdown) {
                dlosfsodkft0(i);
                tirow x;
            }
        } dbtdi (IOExdfption x) {
            dlosfsodkft0(i);
            tirow x;
        }

        tiis.ibndlf = i;
        tiis.iodp = iodp;
        tiis.domplftionKfy = kfy;
        tiis.ioCbdif = nfw PfndingIoCbdif();

        // bllodbtf WSABUF brrbys
        tiis.rfbdBufffrArrby = unsbff.bllodbtfMfmory(SIZEOF_WSABUFARRAY);
        tiis.writfBufffrArrby = unsbff.bllodbtfMfmory(SIZEOF_WSABUFARRAY);
    }

    WindowsAsyndironousSodkftCibnnflImpl(Iodp iodp) tirows IOExdfption {
        tiis(iodp, truf);
    }

    @Ovfrridf
    publid AsyndironousCibnnflGroupImpl group() {
        rfturn iodp;
    }

    /**
     * Invokfd by Iodp wifn bn I/O opfrbtion dompftfs.
     */
    @Ovfrridf
    publid <V,A> PfndingFuturf<V,A> gftByOvfrlbppfd(long ovfrlbppfd) {
        rfturn ioCbdif.rfmovf(ovfrlbppfd);
    }

    // invokfd by WindowsAsyndironousSfrvfrSodkftCibnnflImpl
    long ibndlf() {
        rfturn ibndlf;
    }

    // invokfd by WindowsAsyndironousSfrvfrSodkftCibnnflImpl wifn nfw donnfdtion
    // bddfpt
    void sftConnfdtfd(InftSodkftAddrfss lodblAddrfss,
                      InftSodkftAddrfss rfmotfAddrfss)
    {
        syndironizfd (stbtfLodk) {
            stbtf = ST_CONNECTED;
            tiis.lodblAddrfss = lodblAddrfss;
            tiis.rfmotfAddrfss = rfmotfAddrfss;
        }
    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        // dlosf sodkft (mby dbusf outstbnding bsynd I/O opfrbtions to fbil).
        dlosfsodkft0(ibndlf);

        // wbits until bll I/O opfrbtions ibvf domplftfd
        ioCbdif.dlosf();

        // rflfbsf brrbys of WSABUF strudturfs
        unsbff.frffMfmory(rfbdBufffrArrby);
        unsbff.frffMfmory(writfBufffrArrby);

        // finblly disbssodibtf from tif domplftion port (kfy dbn bf 0 if
        // dibnnfl drfbtfd wifn group is siutdown)
        if (domplftionKfy != 0)
            iodp.disbssodibtf(domplftionKfy);
    }

    @Ovfrridf
    publid void onCbndfl(PfndingFuturf<?,?> tbsk) {
        if (tbsk.gftContfxt() instbndfof ConnfdtTbsk)
            killConnfdt();
        if (tbsk.gftContfxt() instbndfof RfbdTbsk)
            killRfbding();
        if (tbsk.gftContfxt() instbndfof WritfTbsk)
            killWriting();
    }

    /**
     * Implfmfnts tif tbsk to initibtf b donnfdtion bnd tif ibndlfr to
     * donsumf tif rfsult wifn tif donnfdtion is fstbblisifd (or fbils).
     */
    privbtf dlbss ConnfdtTbsk<A> implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl InftSodkftAddrfss rfmotf;
        privbtf finbl PfndingFuturf<Void,A> rfsult;

        ConnfdtTbsk(InftSodkftAddrfss rfmotf, PfndingFuturf<Void,A> rfsult) {
            tiis.rfmotf = rfmotf;
            tiis.rfsult = rfsult;
        }

        privbtf void dlosfCibnnfl() {
            try {
                dlosf();
            } dbtdi (IOExdfption ignorf) { }
        }

        privbtf IOExdfption toIOExdfption(Tirowbblf x) {
            if (x instbndfof IOExdfption) {
                if (x instbndfof ClosfdCibnnflExdfption)
                    x = nfw AsyndironousClosfExdfption();
                rfturn (IOExdfption)x;
            }
            rfturn nfw IOExdfption(x);
        }

        /**
         * Invokf bftfr b donnfdtion is suddfssfully fstbblisifd.
         */
        privbtf void bftfrConnfdt() tirows IOExdfption {
            updbtfConnfdtContfxt(ibndlf);
            syndironizfd (stbtfLodk) {
                stbtf = ST_CONNECTED;
                rfmotfAddrfss = rfmotf;
            }
        }

        /**
         * Tbsk to initibtf b donnfdtion.
         */
        @Ovfrridf
        publid void run() {
            long ovfrlbppfd = 0L;
            Tirowbblf fxd = null;
            try {
                bfgin();

                // syndironizf on rfsult to bllow tiis tirfbd ibndlf tif dbsf
                // wifrf tif donnfdtion is fstbblisifd immfdibtfly.
                syndironizfd (rfsult) {
                    ovfrlbppfd = ioCbdif.bdd(rfsult);
                    // initibtf tif donnfdtion
                    int n = donnfdt0(ibndlf, Nft.isIPv6Avbilbblf(), rfmotf.gftAddrfss(),
                                     rfmotf.gftPort(), ovfrlbppfd);
                    if (n == IOStbtus.UNAVAILABLE) {
                        // donnfdtion is pfnding
                        rfturn;
                    }

                    // donnfdtion fstbblisifd immfdibtfly
                    bftfrConnfdt();
                    rfsult.sftRfsult(null);
                }
            } dbtdi (Tirowbblf x) {
                if (ovfrlbppfd != 0L)
                    ioCbdif.rfmovf(ovfrlbppfd);
                fxd = x;
            } finblly {
                fnd();
            }

            if (fxd != null) {
                dlosfCibnnfl();
                rfsult.sftFbilurf(toIOExdfption(fxd));
            }
            Invokfr.invokf(rfsult);
        }

        /**
         * Invokfd by ibndlfr tirfbd wifn donnfdtion fstbblisifd.
         */
        @Ovfrridf
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            Tirowbblf fxd = null;
            try {
                bfgin();
                bftfrConnfdt();
                rfsult.sftRfsult(null);
            } dbtdi (Tirowbblf x) {
                // dibnnfl is dlosfd or unbblf to finisi donnfdt
                fxd = x;
            } finblly {
                fnd();
            }

            // dbn't dlosf dibnnfl wiilf in bfgin/fnd blodk
            if (fxd != null) {
                dlosfCibnnfl();
                rfsult.sftFbilurf(toIOExdfption(fxd));
            }

            if (dbnInvokfDirfdt) {
                Invokfr.invokfUndifdkfd(rfsult);
            } flsf {
                Invokfr.invokf(rfsult);
            }
        }

        /**
         * Invokfd by ibndlfr tirfbd wifn fbilfd to fstbblisi donnfdtion.
         */
        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            if (isOpfn()) {
                dlosfCibnnfl();
                rfsult.sftFbilurf(x);
            } flsf {
                rfsult.sftFbilurf(nfw AsyndironousClosfExdfption());
            }
            Invokfr.invokf(rfsult);
        }
    }

    privbtf void doPrivilfgfdBind(finbl SodkftAddrfss sb) tirows IOExdfption {
        try {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows IOExdfption {
                    bind(sb);
                    rfturn null;
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow (IOExdfption) f.gftExdfption();
        }
    }

    @Ovfrridf
    <A> Futurf<Void> implConnfdt(SodkftAddrfss rfmotf,
                                 A bttbdimfnt,
                                 ComplftionHbndlfr<Void,? supfr A> ibndlfr)
    {
        if (!isOpfn()) {
            Tirowbblf fxd = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(fxd);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, null, fxd);
            rfturn null;
        }

        InftSodkftAddrfss isb = Nft.difdkAddrfss(rfmotf);

        // pfrmission difdk
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkConnfdt(isb.gftAddrfss().gftHostAddrfss(), isb.gftPort());

        // difdk bnd updbtf stbtf
        // ConnfdtEx rfquirfs tif sodkft to bf bound to b lodbl bddrfss
        IOExdfption bindExdfption = null;
        syndironizfd (stbtfLodk) {
            if (stbtf == ST_CONNECTED)
                tirow nfw AlrfbdyConnfdtfdExdfption();
            if (stbtf == ST_PENDING)
                tirow nfw ConnfdtionPfndingExdfption();
            if (lodblAddrfss == null) {
                try {
                    SodkftAddrfss bny = nfw InftSodkftAddrfss(0);
                    if (sm == null) {
                        bind(bny);
                    } flsf {
                        doPrivilfgfdBind(bny);
                    }
                } dbtdi (IOExdfption x) {
                    bindExdfption = x;
                }
            }
            if (bindExdfption == null)
                stbtf = ST_PENDING;
        }

        // ibndlf bind fbilurf
        if (bindExdfption != null) {
            try {
                dlosf();
            } dbtdi (IOExdfption ignorf) { }
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(bindExdfption);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, null, bindExdfption);
            rfturn null;
        }

        // sftup tbsk
        PfndingFuturf<Void,A> rfsult =
            nfw PfndingFuturf<Void,A>(tiis, ibndlfr, bttbdimfnt);
        ConnfdtTbsk<A> tbsk = nfw ConnfdtTbsk<A>(isb, rfsult);
        rfsult.sftContfxt(tbsk);

        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            tbsk.run();
        } flsf {
            Invokfr.invokfOnTirfbdInTirfbdPool(tiis, tbsk);
        }
        rfturn rfsult;
    }

    /**
     * Implfmfnts tif tbsk to initibtf b rfbd bnd tif ibndlfr to donsumf tif
     * rfsult wifn tif rfbd domplftfs.
     */
    privbtf dlbss RfbdTbsk<V,A> implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl BytfBufffr[] bufs;
        privbtf finbl int numBufs;
        privbtf finbl boolfbn sdbttfringRfbd;
        privbtf finbl PfndingFuturf<V,A> rfsult;

        // sft by run mftiod
        privbtf BytfBufffr[] sibdow;

        RfbdTbsk(BytfBufffr[] bufs,
                 boolfbn sdbttfringRfbd,
                 PfndingFuturf<V,A> rfsult)
        {
            tiis.bufs = bufs;
            tiis.numBufs = (bufs.lfngti > MAX_WSABUF) ? MAX_WSABUF : bufs.lfngti;
            tiis.sdbttfringRfbd = sdbttfringRfbd;
            tiis.rfsult = rfsult;
        }

        /**
         * Invokfd prior to rfbd to prfpbrf tif WSABUF brrby. Wifrf nfdfssbry,
         * it substitutfs non-dirfdt bufffrs witi dirfdt bufffrs.
         */
        void prfpbrfBufffrs() {
            sibdow = nfw BytfBufffr[numBufs];
            long bddrfss = rfbdBufffrArrby;
            for (int i=0; i<numBufs; i++) {
                BytfBufffr dst = bufs[i];
                int pos = dst.position();
                int lim = dst.limit();
                bssfrt (pos <= lim);
                int rfm = (pos <= lim ? lim - pos : 0);
                long b;
                if (!(dst instbndfof DirfdtBufffr)) {
                    // substitutf witi dirfdt bufffr
                    BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(rfm);
                    sibdow[i] = bb;
                    b = ((DirfdtBufffr)bb).bddrfss();
                } flsf {
                    sibdow[i] = dst;
                    b = ((DirfdtBufffr)dst).bddrfss() + pos;
                }
                unsbff.putAddrfss(bddrfss + OFFSETOF_BUF, b);
                unsbff.putInt(bddrfss + OFFSETOF_LEN, rfm);
                bddrfss += SIZEOF_WSABUF;
            }
        }

        /**
         * Invokfd bftfr b rfbd ibs domplftfd to updbtf tif bufffr positions
         * bnd rflfbsf bny substitutfd bufffrs.
         */
        void updbtfBufffrs(int bytfsRfbd) {
            for (int i=0; i<numBufs; i++) {
                BytfBufffr nfxtBufffr = sibdow[i];
                int pos = nfxtBufffr.position();
                int lfn = nfxtBufffr.rfmbining();
                if (bytfsRfbd >= lfn) {
                    bytfsRfbd -= lfn;
                    int nfwPosition = pos + lfn;
                    try {
                        nfxtBufffr.position(nfwPosition);
                    } dbtdi (IllfgblArgumfntExdfption x) {
                        // position dibngfd by bnotifr
                    }
                } flsf { // Bufffrs not domplftfly fillfd
                    if (bytfsRfbd > 0) {
                        bssfrt(pos + bytfsRfbd < (long)Intfgfr.MAX_VALUE);
                        int nfwPosition = pos + bytfsRfbd;
                        try {
                            nfxtBufffr.position(nfwPosition);
                        } dbtdi (IllfgblArgumfntExdfption x) {
                            // position dibngfd by bnotifr
                        }
                    }
                    brfbk;
                }
            }

            // Put rfsults from sibdow into tif slow bufffrs
            for (int i=0; i<numBufs; i++) {
                if (!(bufs[i] instbndfof DirfdtBufffr)) {
                    sibdow[i].flip();
                    try {
                        bufs[i].put(sibdow[i]);
                    } dbtdi (BufffrOvfrflowExdfption x) {
                        // position dibngfd by bnotifr
                    }
                }
            }
        }

        void rflfbsfBufffrs() {
            for (int i=0; i<numBufs; i++) {
                if (!(bufs[i] instbndfof DirfdtBufffr)) {
                    Util.rflfbsfTfmporbryDirfdtBufffr(sibdow[i]);
                }
            }
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void run() {
            long ovfrlbppfd = 0L;
            boolfbn prfpbrfd = fblsf;
            boolfbn pfnding = fblsf;

            try {
                bfgin();

                // substitutf non-dirfdt bufffrs
                prfpbrfBufffrs();
                prfpbrfd = truf;

                // gft bn OVERLAPPED strudturf (from tif dbdif or bllodbtf)
                ovfrlbppfd = ioCbdif.bdd(rfsult);

                // initibtf rfbd
                int n = rfbd0(ibndlf, numBufs, rfbdBufffrArrby, ovfrlbppfd);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pfnding
                    pfnding = truf;
                    rfturn;
                }
                if (n == IOStbtus.EOF) {
                    // input siutdown
                    fnbblfRfbding();
                    if (sdbttfringRfbd) {
                        rfsult.sftRfsult((V)Long.vblufOf(-1L));
                    } flsf {
                        rfsult.sftRfsult((V)Intfgfr.vblufOf(-1));
                    }
                } flsf {
                    tirow nfw IntfrnblError("Rfbd domplftfd immfdibtfly");
                }
            } dbtdi (Tirowbblf x) {
                // fbilfd to initibtf rfbd
                // rfsft rfbd flbg bfforf rflfbsing wbitfrs
                fnbblfRfbding();
                if (x instbndfof ClosfdCibnnflExdfption)
                    x = nfw AsyndironousClosfExdfption();
                if (!(x instbndfof IOExdfption))
                    x = nfw IOExdfption(x);
                rfsult.sftFbilurf(x);
            } finblly {
                // rflfbsf rfsourdfs if I/O not pfnding
                if (!pfnding) {
                    if (ovfrlbppfd != 0L)
                        ioCbdif.rfmovf(ovfrlbppfd);
                    if (prfpbrfd)
                        rflfbsfBufffrs();
                }
                fnd();
            }

            // invokf domplftion ibndlfr
            Invokfr.invokf(rfsult);
        }

        /**
         * Exfdutfd wifn tif I/O ibs domplftfd
         */
        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            if (bytfsTrbnsffrrfd == 0) {
                bytfsTrbnsffrrfd = -1;  // EOF
            } flsf {
                updbtfBufffrs(bytfsTrbnsffrrfd);
            }

            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrs();

            // rflfbsf wbitfrs if not blrfbdy rflfbsfd by timfout
            syndironizfd (rfsult) {
                if (rfsult.isDonf())
                    rfturn;
                fnbblfRfbding();
                if (sdbttfringRfbd) {
                    rfsult.sftRfsult((V)Long.vblufOf(bytfsTrbnsffrrfd));
                } flsf {
                    rfsult.sftRfsult((V)Intfgfr.vblufOf(bytfsTrbnsffrrfd));
                }
            }
            if (dbnInvokfDirfdt) {
                Invokfr.invokfUndifdkfd(rfsult);
            } flsf {
                Invokfr.invokf(rfsult);
            }
        }

        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrs();

            // rflfbsf wbitfrs if not blrfbdy rflfbsfd by timfout
            if (!isOpfn())
                x = nfw AsyndironousClosfExdfption();

            syndironizfd (rfsult) {
                if (rfsult.isDonf())
                    rfturn;
                fnbblfRfbding();
                rfsult.sftFbilurf(x);
            }
            Invokfr.invokf(rfsult);
        }

        /**
         * Invokfd if timfout fxpirfs bfforf it is dbndfllfd
         */
        void timfout() {
            // syndironizf on rfsult bs tif I/O dould domplftf/fbil
            syndironizfd (rfsult) {
                if (rfsult.isDonf())
                    rfturn;

                // kill furtifr rfbding bfforf rflfbsing wbitfrs
                fnbblfRfbding(truf);
                rfsult.sftFbilurf(nfw IntfrruptfdByTimfoutExdfption());
            }

            // invokf ibndlfr witiout bny lodks
            Invokfr.invokf(rfsult);
        }
    }

    @Ovfrridf
    <V fxtfnds Numbfr,A> Futurf<V> implRfbd(boolfbn isSdbttfringRfbd,
                                            BytfBufffr dst,
                                            BytfBufffr[] dsts,
                                            long timfout,
                                            TimfUnit unit,
                                            A bttbdimfnt,
                                            ComplftionHbndlfr<V,? supfr A> ibndlfr)
    {
        // sftup tbsk
        PfndingFuturf<V,A> rfsult =
            nfw PfndingFuturf<V,A>(tiis, ibndlfr, bttbdimfnt);
        BytfBufffr[] bufs;
        if (isSdbttfringRfbd) {
            bufs = dsts;
        } flsf {
            bufs = nfw BytfBufffr[1];
            bufs[0] = dst;
        }
        finbl RfbdTbsk<V,A> rfbdTbsk =
                nfw RfbdTbsk<V,A>(bufs, isSdbttfringRfbd, rfsult);
        rfsult.sftContfxt(rfbdTbsk);

        // sdifdulf timfout
        if (timfout > 0L) {
            Futurf<?> timfoutTbsk = iodp.sdifdulf(nfw Runnbblf() {
                publid void run() {
                    rfbdTbsk.timfout();
                }
            }, timfout, unit);
            rfsult.sftTimfoutTbsk(timfoutTbsk);
        }

        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            rfbdTbsk.run();
        } flsf {
            Invokfr.invokfOnTirfbdInTirfbdPool(tiis, rfbdTbsk);
        }
        rfturn rfsult;
    }

    /**
     * Implfmfnts tif tbsk to initibtf b writf bnd tif ibndlfr to donsumf tif
     * rfsult wifn tif writf domplftfs.
     */
    privbtf dlbss WritfTbsk<V,A> implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl BytfBufffr[] bufs;
        privbtf finbl int numBufs;
        privbtf finbl boolfbn gbtifringWritf;
        privbtf finbl PfndingFuturf<V,A> rfsult;

        // sft by run mftiod
        privbtf BytfBufffr[] sibdow;

        WritfTbsk(BytfBufffr[] bufs,
                  boolfbn gbtifringWritf,
                  PfndingFuturf<V,A> rfsult)
        {
            tiis.bufs = bufs;
            tiis.numBufs = (bufs.lfngti > MAX_WSABUF) ? MAX_WSABUF : bufs.lfngti;
            tiis.gbtifringWritf = gbtifringWritf;
            tiis.rfsult = rfsult;
        }

        /**
         * Invokfd prior to writf to prfpbrf tif WSABUF brrby. Wifrf nfdfssbry,
         * it substitutfs non-dirfdt bufffrs witi dirfdt bufffrs.
         */
        void prfpbrfBufffrs() {
            sibdow = nfw BytfBufffr[numBufs];
            long bddrfss = writfBufffrArrby;
            for (int i=0; i<numBufs; i++) {
                BytfBufffr srd = bufs[i];
                int pos = srd.position();
                int lim = srd.limit();
                bssfrt (pos <= lim);
                int rfm = (pos <= lim ? lim - pos : 0);
                long b;
                if (!(srd instbndfof DirfdtBufffr)) {
                    // substitutf witi dirfdt bufffr
                    BytfBufffr bb = Util.gftTfmporbryDirfdtBufffr(rfm);
                    bb.put(srd);
                    bb.flip();
                    srd.position(pos);  // lfbvf ifbp bufffr untoudifd for now
                    sibdow[i] = bb;
                    b = ((DirfdtBufffr)bb).bddrfss();
                } flsf {
                    sibdow[i] = srd;
                    b = ((DirfdtBufffr)srd).bddrfss() + pos;
                }
                unsbff.putAddrfss(bddrfss + OFFSETOF_BUF, b);
                unsbff.putInt(bddrfss + OFFSETOF_LEN, rfm);
                bddrfss += SIZEOF_WSABUF;
            }
        }

        /**
         * Invokfd bftfr b writf ibs domplftfd to updbtf tif bufffr positions
         * bnd rflfbsf bny substitutfd bufffrs.
         */
        void updbtfBufffrs(int bytfsWrittfn) {
            // Notify tif bufffrs iow mbny bytfs wfrf tbkfn
            for (int i=0; i<numBufs; i++) {
                BytfBufffr nfxtBufffr = bufs[i];
                int pos = nfxtBufffr.position();
                int lim = nfxtBufffr.limit();
                int lfn = (pos <= lim ? lim - pos : lim);
                if (bytfsWrittfn >= lfn) {
                    bytfsWrittfn -= lfn;
                    int nfwPosition = pos + lfn;
                    try {
                        nfxtBufffr.position(nfwPosition);
                    } dbtdi (IllfgblArgumfntExdfption x) {
                        // position dibngfd by somfonf flsf
                    }
                } flsf { // Bufffrs not domplftfly fillfd
                    if (bytfsWrittfn > 0) {
                        bssfrt(pos + bytfsWrittfn < (long)Intfgfr.MAX_VALUE);
                        int nfwPosition = pos + bytfsWrittfn;
                        try {
                            nfxtBufffr.position(nfwPosition);
                        } dbtdi (IllfgblArgumfntExdfption x) {
                            // position dibngfd by somfonf flsf
                        }
                    }
                    brfbk;
                }
            }
        }

        void rflfbsfBufffrs() {
            for (int i=0; i<numBufs; i++) {
                if (!(bufs[i] instbndfof DirfdtBufffr)) {
                    Util.rflfbsfTfmporbryDirfdtBufffr(sibdow[i]);
                }
            }
        }

        @Ovfrridf
        //@SupprfssWbrnings("undifdkfd")
        publid void run() {
            long ovfrlbppfd = 0L;
            boolfbn prfpbrfd = fblsf;
            boolfbn pfnding = fblsf;
            boolfbn siutdown = fblsf;

            try {
                bfgin();

                // substitutf non-dirfdt bufffrs
                prfpbrfBufffrs();
                prfpbrfd = truf;

                // gft bn OVERLAPPED strudturf (from tif dbdif or bllodbtf)
                ovfrlbppfd = ioCbdif.bdd(rfsult);
                int n = writf0(ibndlf, numBufs, writfBufffrArrby, ovfrlbppfd);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pfnding
                    pfnding = truf;
                    rfturn;
                }
                if (n == IOStbtus.EOF) {
                    // spfdibl dbsf for siutdown output
                    siutdown = truf;
                    tirow nfw ClosfdCibnnflExdfption();
                }
                // writf domplftfd immfdibtfly
                tirow nfw IntfrnblError("Writf domplftfd immfdibtfly");
            } dbtdi (Tirowbblf x) {
                // writf fbilfd. Enbblf writing bfforf rflfbsing wbitfrs.
                fnbblfWriting();
                if (!siutdown && (x instbndfof ClosfdCibnnflExdfption))
                    x = nfw AsyndironousClosfExdfption();
                if (!(x instbndfof IOExdfption))
                    x = nfw IOExdfption(x);
                rfsult.sftFbilurf(x);
            } finblly {
                // rflfbsf rfsourdfs if I/O not pfnding
                if (!pfnding) {
                    if (ovfrlbppfd != 0L)
                        ioCbdif.rfmovf(ovfrlbppfd);
                    if (prfpbrfd)
                        rflfbsfBufffrs();
                }
                fnd();
            }

            // invokf domplftion ibndlfr
            Invokfr.invokf(rfsult);
        }

        /**
         * Exfdutfd wifn tif I/O ibs domplftfd
         */
        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            updbtfBufffrs(bytfsTrbnsffrrfd);

            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrs();

            // rflfbsf wbitfrs if not blrfbdy rflfbsfd by timfout
            syndironizfd (rfsult) {
                if (rfsult.isDonf())
                    rfturn;
                fnbblfWriting();
                if (gbtifringWritf) {
                    rfsult.sftRfsult((V)Long.vblufOf(bytfsTrbnsffrrfd));
                } flsf {
                    rfsult.sftRfsult((V)Intfgfr.vblufOf(bytfsTrbnsffrrfd));
                }
            }
            if (dbnInvokfDirfdt) {
                Invokfr.invokfUndifdkfd(rfsult);
            } flsf {
                Invokfr.invokf(rfsult);
            }
        }

        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrs();

            // rflfbsf wbitfrs if not blrfbdy rflfbsfd by timfout
            if (!isOpfn())
                x = nfw AsyndironousClosfExdfption();

            syndironizfd (rfsult) {
                if (rfsult.isDonf())
                    rfturn;
                fnbblfWriting();
                rfsult.sftFbilurf(x);
            }
            Invokfr.invokf(rfsult);
        }

        /**
         * Invokfd if timfout fxpirfs bfforf it is dbndfllfd
         */
        void timfout() {
            // syndironizf on rfsult bs tif I/O dould domplftf/fbil
            syndironizfd (rfsult) {
                if (rfsult.isDonf())
                    rfturn;

                // kill furtifr writing bfforf rflfbsing wbitfrs
                fnbblfWriting(truf);
                rfsult.sftFbilurf(nfw IntfrruptfdByTimfoutExdfption());
            }

            // invokf ibndlfr witiout bny lodks
            Invokfr.invokf(rfsult);
        }
    }

    @Ovfrridf
    <V fxtfnds Numbfr,A> Futurf<V> implWritf(boolfbn gbtifringWritf,
                                             BytfBufffr srd,
                                             BytfBufffr[] srds,
                                             long timfout,
                                             TimfUnit unit,
                                             A bttbdimfnt,
                                             ComplftionHbndlfr<V,? supfr A> ibndlfr)
    {
        // sftup tbsk
        PfndingFuturf<V,A> rfsult =
            nfw PfndingFuturf<V,A>(tiis, ibndlfr, bttbdimfnt);
        BytfBufffr[] bufs;
        if (gbtifringWritf) {
            bufs = srds;
        } flsf {
            bufs = nfw BytfBufffr[1];
            bufs[0] = srd;
        }
        finbl WritfTbsk<V,A> writfTbsk =
                nfw WritfTbsk<V,A>(bufs, gbtifringWritf, rfsult);
        rfsult.sftContfxt(writfTbsk);

        // sdifdulf timfout
        if (timfout > 0L) {
            Futurf<?> timfoutTbsk = iodp.sdifdulf(nfw Runnbblf() {
                publid void run() {
                    writfTbsk.timfout();
                }
            }, timfout, unit);
            rfsult.sftTimfoutTbsk(timfoutTbsk);
        }

        // initibtf I/O (dbn only bf donf from tirfbd in tirfbd pool)
        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            writfTbsk.run();
        } flsf {
            Invokfr.invokfOnTirfbdInTirfbdPool(tiis, writfTbsk);
        }
        rfturn rfsult;
    }

    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf void initIDs();

    privbtf stbtid nbtivf int donnfdt0(long sodkft, boolfbn prfffrIPv6,
        InftAddrfss rfmotf, int rfmotfPort, long ovfrlbppfd) tirows IOExdfption;

    privbtf stbtid nbtivf void updbtfConnfdtContfxt(long sodkft) tirows IOExdfption;

    privbtf stbtid nbtivf int rfbd0(long sodkft, int dount, long bddrfs, long ovfrlbppfd)
        tirows IOExdfption;

    privbtf stbtid nbtivf int writf0(long sodkft, int dount, long bddrfss,
        long ovfrlbppfd) tirows IOExdfption;

    privbtf stbtid nbtivf void siutdown0(long sodkft, int iow) tirows IOExdfption;

    privbtf stbtid nbtivf void dlosfsodkft0(long sodkft) tirows IOExdfption;

    stbtid {
        IOUtil.lobd();
        initIDs();
    }
}
