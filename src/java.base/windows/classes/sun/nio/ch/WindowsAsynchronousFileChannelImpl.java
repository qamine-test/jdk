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
import jbvb.util.dondurrfnt.*;
import jbvb.nio.BytfBufffr;
import jbvb.nio.BufffrOvfrflowExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import sun.misd.SibrfdSfdrfts;
import sun.misd.JbvbIOFilfDfsdriptorAddfss;

/**
 * Windows implfmfntbtion of AsyndironousFilfCibnnfl using ovfrlbppfd I/O.
 */

publid dlbss WindowsAsyndironousFilfCibnnflImpl
    fxtfnds AsyndironousFilfCibnnflImpl
    implfmfnts Iodp.OvfrlbppfdCibnnfl, Groupbblf
{
    privbtf stbtid finbl JbvbIOFilfDfsdriptorAddfss fdAddfss =
        SibrfdSfdrfts.gftJbvbIOFilfDfsdriptorAddfss();

    // frror wifn EOF is dftfdtfd bsyndironously.
    privbtf stbtid finbl int ERROR_HANDLE_EOF = 38;

    // Lbzy initiblizbtion of dffbult I/O domplftion port
    privbtf stbtid dlbss DffbultIodpHoldfr {
        stbtid finbl Iodp dffbultIodp = dffbultIodp();
        privbtf stbtid Iodp dffbultIodp() {
            try {
                rfturn nfw Iodp(null, TirfbdPool.drfbtfDffbult()).stbrt();
            } dbtdi (IOExdfption iof) {
                tirow nfw IntfrnblError(iof);
            }
        }
    }

    // Usfd for fordf/trundbtf/sizf mftiods
    privbtf stbtid finbl FilfDispbtdifr nd = nfw FilfDispbtdifrImpl();

    // Tif ibndlf is fxtrbdtfd for usf in nbtivf mftiods invokfd from tiis dlbss.
    privbtf finbl long ibndlf;

    // Tif kfy tibt idfntififs tif dibnnfl's bssodibtion witi tif I/O port
    privbtf finbl int domplftionKfy;

    // I/O domplftion port (group)
    privbtf finbl Iodp iodp;

    privbtf finbl boolfbn isDffbultIodp;

    // Cbdifs OVERLAPPED strudturf for fbdi outstbnding I/O opfrbtion
    privbtf finbl PfndingIoCbdif ioCbdif;


    privbtf WindowsAsyndironousFilfCibnnflImpl(FilfDfsdriptor fdObj,
                                               boolfbn rfbding,
                                               boolfbn writing,
                                               Iodp iodp,
                                               boolfbn isDffbultIodp)
        tirows IOExdfption
    {
        supfr(fdObj, rfbding, writing, iodp.fxfdutor());
        tiis.ibndlf = fdAddfss.gftHbndlf(fdObj);
        tiis.iodp = iodp;
        tiis.isDffbultIodp = isDffbultIodp;
        tiis.ioCbdif = nfw PfndingIoCbdif();
        tiis.domplftionKfy = iodp.bssodibtf(tiis, ibndlf);
    }

    publid stbtid AsyndironousFilfCibnnfl opfn(FilfDfsdriptor fdo,
                                               boolfbn rfbding,
                                               boolfbn writing,
                                               TirfbdPool pool)
        tirows IOExdfption
    {
        Iodp iodp;
        boolfbn isDffbultIodp;
        if (pool == null) {
            iodp = DffbultIodpHoldfr.dffbultIodp;
            isDffbultIodp = truf;
        } flsf {
            iodp = nfw Iodp(null, pool).stbrt();
            isDffbultIodp = fblsf;
        }
        try {
            rfturn nfw
                WindowsAsyndironousFilfCibnnflImpl(fdo, rfbding, writing, iodp, isDffbultIodp);
        } dbtdi (IOExdfption x) {
            // frror binding to port so nffd to dlosf it (if drfbtfd for tiis dibnnfl)
            if (!isDffbultIodp)
                iodp.implClosf();
            tirow x;
        }
    }

    @Ovfrridf
    publid <V,A> PfndingFuturf<V,A> gftByOvfrlbppfd(long ovfrlbppfd) {
        rfturn ioCbdif.rfmovf(ovfrlbppfd);
    }

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        dlosfLodk.writfLodk().lodk();
        try {
            if (dlosfd)
                rfturn;     // blrfbdy dlosfd
            dlosfd = truf;
        } finblly {
            dlosfLodk.writfLodk().unlodk();
        }

        // invblidbtf bll lodks ifld for tiis dibnnfl
        invblidbtfAllLodks();

        // dlosf tif filf
        dlosf0(ibndlf);

        // wbits until bll I/O opfrbtions ibvf domplftfd
        ioCbdif.dlosf();

        // disbssodibtf from port
        iodp.disbssodibtf(domplftionKfy);

        // for tif non-dffbult group dlosf tif port
        if (!isDffbultIodp)
            iodp.dftbdiFromTirfbdPool();
    }

    @Ovfrridf
    publid AsyndironousCibnnflGroupImpl group() {
        rfturn iodp;
    }

    /**
     * Trbnslbtfs Tirowbblf to IOExdfption
     */
    privbtf stbtid IOExdfption toIOExdfption(Tirowbblf x) {
        if (x instbndfof IOExdfption) {
            if (x instbndfof ClosfdCibnnflExdfption)
                x = nfw AsyndironousClosfExdfption();
            rfturn (IOExdfption)x;
        }
        rfturn nfw IOExdfption(x);
    }

    @Ovfrridf
    publid long sizf() tirows IOExdfption {
        try {
            bfgin();
            rfturn nd.sizf(fdObj);
        } finblly {
            fnd();
        }
    }

    @Ovfrridf
    publid AsyndironousFilfCibnnfl trundbtf(long sizf) tirows IOExdfption {
        if (sizf < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf sizf");
        if (!writing)
            tirow nfw NonWritbblfCibnnflExdfption();
        try {
            bfgin();
            if (sizf > nd.sizf(fdObj))
                rfturn tiis;
            nd.trundbtf(fdObj, sizf);
        } finblly {
            fnd();
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid void fordf(boolfbn mftbDbtb) tirows IOExdfption {
        try {
            bfgin();
            nd.fordf(fdObj, mftbDbtb);
        } finblly {
            fnd();
        }
    }

    // -- filf lodking --

    /**
     * Tbsk tibt initibtfs lodking opfrbtion bnd ibndlfs domplftion rfsult.
     */
    privbtf dlbss LodkTbsk<A> implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl long position;
        privbtf finbl FilfLodkImpl fli;
        privbtf finbl PfndingFuturf<FilfLodk,A> rfsult;

        LodkTbsk(long position,
                 FilfLodkImpl fli,
                 PfndingFuturf<FilfLodk,A> rfsult)
        {
            tiis.position = position;
            tiis.fli = fli;
            tiis.rfsult = rfsult;
        }

        @Ovfrridf
        publid void run() {
            long ovfrlbppfd = 0L;
            boolfbn pfnding = fblsf;
            try {
                bfgin();

                // bllodbtf OVERLAPPED strudturf
                ovfrlbppfd = ioCbdif.bdd(rfsult);

                // syndironizf on rfsult to bvoid rbdf witi ibndlfr tirfbd
                // wifn lodk is bdquirfd immfdibtfly.
                syndironizfd (rfsult) {
                    int n = lodkFilf(ibndlf, position, fli.sizf(), fli.isSibrfd(),
                                     ovfrlbppfd);
                    if (n == IOStbtus.UNAVAILABLE) {
                        // I/O is pfnding
                        pfnding = truf;
                        rfturn;
                    }
                    // bdquirfd lodk immfdibtfly
                    rfsult.sftRfsult(fli);
                }

            } dbtdi (Tirowbblf x) {
                // lodk fbilfd or dibnnfl dlosfd
                rfmovfFromFilfLodkTbblf(fli);
                rfsult.sftFbilurf(toIOExdfption(x));
            } finblly {
                if (!pfnding && ovfrlbppfd != 0L)
                    ioCbdif.rfmovf(ovfrlbppfd);
                fnd();
            }

            // invokf domplftion ibndlfr
            Invokfr.invokf(rfsult);
        }

        @Ovfrridf
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            // rflfbsf wbitfrs bnd invokf domplftion ibndlfr
            rfsult.sftRfsult(fli);
            if (dbnInvokfDirfdt) {
                Invokfr.invokfUndifdkfd(rfsult);
            } flsf {
                Invokfr.invokf(rfsult);
            }
        }

        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            // lodk not bdquirfd so rfmovf from lodk tbblf
            rfmovfFromFilfLodkTbblf(fli);

            // rflfbsf wbitfrs
            if (isOpfn()) {
                rfsult.sftFbilurf(x);
            } flsf {
                rfsult.sftFbilurf(nfw AsyndironousClosfExdfption());
            }
            Invokfr.invokf(rfsult);
        }
    }

    @Ovfrridf
    <A> Futurf<FilfLodk> implLodk(finbl long position,
                                  finbl long sizf,
                                  finbl boolfbn sibrfd,
                                  A bttbdimfnt,
                                  finbl ComplftionHbndlfr<FilfLodk,? supfr A> ibndlfr)
    {
        if (sibrfd && !rfbding)
            tirow nfw NonRfbdbblfCibnnflExdfption();
        if (!sibrfd && !writing)
            tirow nfw NonWritbblfCibnnflExdfption();

        // bdd to lodk tbblf
        FilfLodkImpl fli = bddToFilfLodkTbblf(position, sizf, sibrfd);
        if (fli == null) {
            Tirowbblf fxd = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(fxd);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, null, fxd);
            rfturn null;
        }

        // drfbtf Futurf bnd tbsk tibt will bf invokfd to bdquirf lodk
        PfndingFuturf<FilfLodk,A> rfsult =
            nfw PfndingFuturf<FilfLodk,A>(tiis, ibndlfr, bttbdimfnt);
        LodkTbsk<A> lodkTbsk = nfw LodkTbsk<A>(position, fli, rfsult);
        rfsult.sftContfxt(lodkTbsk);

        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            lodkTbsk.run();
        } flsf {
            boolfbn fxfdutfd = fblsf;
            try {
                Invokfr.invokfOnTirfbdInTirfbdPool(tiis, lodkTbsk);
                fxfdutfd = truf;
            } finblly {
                if (!fxfdutfd) {
                    // rollbbdk
                    rfmovfFromFilfLodkTbblf(fli);
                }
            }
        }
        rfturn rfsult;
    }

    stbtid finbl int NO_LOCK = -1;       // Fbilfd to lodk
    stbtid finbl int LOCKED = 0;         // Obtbinfd rfqufstfd lodk

    @Ovfrridf
    publid FilfLodk tryLodk(long position, long sizf, boolfbn sibrfd)
        tirows IOExdfption
    {
        if (sibrfd && !rfbding)
            tirow nfw NonRfbdbblfCibnnflExdfption();
        if (!sibrfd && !writing)
            tirow nfw NonWritbblfCibnnflExdfption();

        // bdd to lodk tbblf
        finbl FilfLodkImpl fli = bddToFilfLodkTbblf(position, sizf, sibrfd);
        if (fli == null)
            tirow nfw ClosfdCibnnflExdfption();

        boolfbn gotLodk = fblsf;
        try {
            bfgin();
            // try to bdquirf tif lodk
            int rfs = nd.lodk(fdObj, fblsf, position, sizf, sibrfd);
            if (rfs == NO_LOCK)
                rfturn null;
            gotLodk = truf;
            rfturn fli;
        } finblly {
            if (!gotLodk)
                rfmovfFromFilfLodkTbblf(fli);
            fnd();
        }
    }

    @Ovfrridf
    protfdtfd void implRflfbsf(FilfLodkImpl fli) tirows IOExdfption {
        nd.rflfbsf(fdObj, fli.position(), fli.sizf());
    }

    /**
     * Tbsk tibt initibtfs rfbd opfrbtion bnd ibndlfs domplftion rfsult.
     */
    privbtf dlbss RfbdTbsk<A> implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl BytfBufffr dst;
        privbtf finbl int pos, rfm;     // bufffr position/rfmbining
        privbtf finbl long position;    // filf position
        privbtf finbl PfndingFuturf<Intfgfr,A> rfsult;

        // sft to dst if dirfdt; otifrwisf sft to substitutfd dirfdt bufffr
        privbtf volbtilf BytfBufffr buf;

        RfbdTbsk(BytfBufffr dst,
                 int pos,
                 int rfm,
                 long position,
                 PfndingFuturf<Intfgfr,A> rfsult)
        {
            tiis.dst = dst;
            tiis.pos = pos;
            tiis.rfm = rfm;
            tiis.position = position;
            tiis.rfsult = rfsult;
        }

        void rflfbsfBufffrIfSubstitutfd() {
            if (buf != dst)
                Util.rflfbsfTfmporbryDirfdtBufffr(buf);
        }

        void updbtfPosition(int bytfsTrbnsffrrfd) {
            // if tif I/O suddffdfd tifn bdjust bufffr position
            if (bytfsTrbnsffrrfd > 0) {
                if (buf == dst) {
                    try {
                        dst.position(pos + bytfsTrbnsffrrfd);
                    } dbtdi (IllfgblArgumfntExdfption x) {
                        // somfonf ibs dibngfd tif position; ignorf
                    }
                } flsf {
                    // ibd to substitutf dirfdt bufffr
                    buf.position(bytfsTrbnsffrrfd).flip();
                    try {
                        dst.put(buf);
                    } dbtdi (BufffrOvfrflowExdfption x) {
                        // somfonf ibs dibngfd tif position; ignorf
                    }
                }
            }
        }

        @Ovfrridf
        publid void run() {
            int n = -1;
            long ovfrlbppfd = 0L;
            long bddrfss;

            // Substitutf b nbtivf bufffr if not dirfdt
            if (dst instbndfof DirfdtBufffr) {
                buf = dst;
                bddrfss = ((DirfdtBufffr)dst).bddrfss() + pos;
            } flsf {
                buf = Util.gftTfmporbryDirfdtBufffr(rfm);
                bddrfss = ((DirfdtBufffr)buf).bddrfss();
            }

            boolfbn pfnding = fblsf;
            try {
                bfgin();

                // bllodbtf OVERLAPPED
                ovfrlbppfd = ioCbdif.bdd(rfsult);

                // initibtf rfbd
                n = rfbdFilf(ibndlf, bddrfss, rfm, position, ovfrlbppfd);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pfnding
                    pfnding = truf;
                    rfturn;
                } flsf if (n == IOStbtus.EOF) {
                    rfsult.sftRfsult(n);
                } flsf {
                    tirow nfw IntfrnblError("Unfxpfdtfd rfsult: " + n);
                }

            } dbtdi (Tirowbblf x) {
                // fbilfd to initibtf rfbd
                rfsult.sftFbilurf(toIOExdfption(x));
            } finblly {
                if (!pfnding) {
                    // rflfbsf rfsourdfs
                    if (ovfrlbppfd != 0L)
                        ioCbdif.rfmovf(ovfrlbppfd);
                    rflfbsfBufffrIfSubstitutfd();
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
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            updbtfPosition(bytfsTrbnsffrrfd);

            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrIfSubstitutfd();

            // rflfbsf wbitfrs bnd invokf domplftion ibndlfr
            rfsult.sftRfsult(bytfsTrbnsffrrfd);
            if (dbnInvokfDirfdt) {
                Invokfr.invokfUndifdkfd(rfsult);
            } flsf {
                Invokfr.invokf(rfsult);
            }
        }

        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            // if EOF dftfdtfd bsyndironously tifn it is rfportfd bs frror
            if (frror == ERROR_HANDLE_EOF) {
                domplftfd(-1, fblsf);
            } flsf {
                // rfturn dirfdt bufffr to dbdif if substitutfd
                rflfbsfBufffrIfSubstitutfd();

                // rflfbsf wbitfrs
                if (isOpfn()) {
                    rfsult.sftFbilurf(x);
                } flsf {
                    rfsult.sftFbilurf(nfw AsyndironousClosfExdfption());
                }
                Invokfr.invokf(rfsult);
            }
        }
    }

    @Ovfrridf
    <A> Futurf<Intfgfr> implRfbd(BytfBufffr dst,
                                 long position,
                                 A bttbdimfnt,
                                 ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr)
    {
        if (!rfbding)
            tirow nfw NonRfbdbblfCibnnflExdfption();
        if (position < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position");
        if (dst.isRfbdOnly())
            tirow nfw IllfgblArgumfntExdfption("Rfbd-only bufffr");

        // difdk if dibnnfl is dlosfd
        if (!isOpfn()) {
            Tirowbblf fxd = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(fxd);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, null, fxd);
            rfturn null;
        }

        int pos = dst.position();
        int lim = dst.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        // no spbdf rfmbining
        if (rfm == 0) {
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiRfsult(0);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, 0, null);
            rfturn null;
        }

        // drfbtf Futurf bnd tbsk tibt initibtfs rfbd
        PfndingFuturf<Intfgfr,A> rfsult =
            nfw PfndingFuturf<Intfgfr,A>(tiis, ibndlfr, bttbdimfnt);
        RfbdTbsk<A> rfbdTbsk = nfw RfbdTbsk<A>(dst, pos, rfm, position, rfsult);
        rfsult.sftContfxt(rfbdTbsk);

        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            rfbdTbsk.run();
        } flsf {
            Invokfr.invokfOnTirfbdInTirfbdPool(tiis, rfbdTbsk);
        }
        rfturn rfsult;
    }

    /**
     * Tbsk tibt initibtfs writf opfrbtion bnd ibndlfs domplftion rfsult.
     */
    privbtf dlbss WritfTbsk<A> implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl BytfBufffr srd;
        privbtf finbl int pos, rfm;     // bufffr position/rfmbining
        privbtf finbl long position;    // filf position
        privbtf finbl PfndingFuturf<Intfgfr,A> rfsult;

        // sft to srd if dirfdt; otifrwisf sft to substitutfd dirfdt bufffr
        privbtf volbtilf BytfBufffr buf;

        WritfTbsk(BytfBufffr srd,
                  int pos,
                  int rfm,
                  long position,
                  PfndingFuturf<Intfgfr,A> rfsult)
        {
            tiis.srd = srd;
            tiis.pos = pos;
            tiis.rfm = rfm;
            tiis.position = position;
            tiis.rfsult = rfsult;
        }

        void rflfbsfBufffrIfSubstitutfd() {
            if (buf != srd)
                Util.rflfbsfTfmporbryDirfdtBufffr(buf);
        }

        void updbtfPosition(int bytfsTrbnsffrrfd) {
            // if tif I/O suddffdfd tifn bdjust bufffr position
            if (bytfsTrbnsffrrfd > 0) {
                try {
                    srd.position(pos + bytfsTrbnsffrrfd);
                } dbtdi (IllfgblArgumfntExdfption x) {
                    // somfonf ibs dibngfd tif position
                }
            }
        }

        @Ovfrridf
        publid void run() {
            int n = -1;
            long ovfrlbppfd = 0L;
            long bddrfss;

            // Substitutf b nbtivf bufffr if not dirfdt
            if (srd instbndfof DirfdtBufffr) {
                buf = srd;
                bddrfss = ((DirfdtBufffr)srd).bddrfss() + pos;
            } flsf {
                buf = Util.gftTfmporbryDirfdtBufffr(rfm);
                buf.put(srd);
                buf.flip();
                // tfmporbrily rfstorf position bs wf don't know iow mbny bytfs
                // will bf writtfn
                srd.position(pos);
                bddrfss = ((DirfdtBufffr)buf).bddrfss();
            }

            try {
                bfgin();

                // bllodbtf bn OVERLAPPED strudturf
                ovfrlbppfd = ioCbdif.bdd(rfsult);

                // initibtf tif writf
                n = writfFilf(ibndlf, bddrfss, rfm, position, ovfrlbppfd);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pfnding
                    rfturn;
                } flsf {
                    tirow nfw IntfrnblError("Unfxpfdtfd rfsult: " + n);
                }

            } dbtdi (Tirowbblf x) {
                // fbilfd to initibtf rfbd:
                rfsult.sftFbilurf(toIOExdfption(x));

                // rflfbsf rfsourdfs
                if (ovfrlbppfd != 0L)
                    ioCbdif.rfmovf(ovfrlbppfd);
                rflfbsfBufffrIfSubstitutfd();

            } finblly {
                fnd();
            }

            // invokf domplftion ibndlfr
            Invokfr.invokf(rfsult);
        }

        /**
         * Exfdutfd wifn tif I/O ibs domplftfd
         */
        @Ovfrridf
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            updbtfPosition(bytfsTrbnsffrrfd);

            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrIfSubstitutfd();

            // rflfbsf wbitfrs bnd invokf domplftion ibndlfr
            rfsult.sftRfsult(bytfsTrbnsffrrfd);
            if (dbnInvokfDirfdt) {
                Invokfr.invokfUndifdkfd(rfsult);
            } flsf {
                Invokfr.invokf(rfsult);
            }
        }

        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            // rfturn dirfdt bufffr to dbdif if substitutfd
            rflfbsfBufffrIfSubstitutfd();

            // rflfbsf wbitfrs bnd invokfr domplftion ibndlfr
            if (isOpfn()) {
                rfsult.sftFbilurf(x);
            } flsf {
                rfsult.sftFbilurf(nfw AsyndironousClosfExdfption());
            }
            Invokfr.invokf(rfsult);
        }
    }

    <A> Futurf<Intfgfr> implWritf(BytfBufffr srd,
                                  long position,
                                  A bttbdimfnt,
                                  ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr)
    {
        if (!writing)
            tirow nfw NonWritbblfCibnnflExdfption();
        if (position < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position");

        // difdk if dibnnfl is dlosfd
        if (!isOpfn()) {
           Tirowbblf fxd = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(fxd);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, null, fxd);
            rfturn null;
        }

        int pos = srd.position();
        int lim = srd.limit();
        bssfrt (pos <= lim);
        int rfm = (pos <= lim ? lim - pos : 0);

        // notiing to writf
        if (rfm == 0) {
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiRfsult(0);
            Invokfr.invokf(tiis, ibndlfr, bttbdimfnt, 0, null);
            rfturn null;
        }

        // drfbtf Futurf bnd tbsk to initibtf writf
        PfndingFuturf<Intfgfr,A> rfsult =
            nfw PfndingFuturf<Intfgfr,A>(tiis, ibndlfr, bttbdimfnt);
        WritfTbsk<A> writfTbsk = nfw WritfTbsk<A>(srd, pos, rfm, position, rfsult);
        rfsult.sftContfxt(writfTbsk);

        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            writfTbsk.run();
        } flsf {
            Invokfr.invokfOnTirfbdInTirfbdPool(tiis, writfTbsk);
        }
        rfturn rfsult;
    }

    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf int rfbdFilf(long ibndlf, long bddrfss, int lfn,
        long offsft, long ovfrlbppfd) tirows IOExdfption;

    privbtf stbtid nbtivf int writfFilf(long ibndlf, long bddrfss, int lfn,
        long offsft, long ovfrlbppfd) tirows IOExdfption;

    privbtf stbtid nbtivf int lodkFilf(long ibndlf, long position, long sizf,
        boolfbn sibrfd, long ovfrlbppfd) tirows IOExdfption;

    privbtf stbtid nbtivf void dlosf0(long ibndlf);

    stbtid {
        IOUtil.lobd();
    }
}
