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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import dom.sun.nio.filf.ExtfndfdWbtdiEvfntModififr;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/*
 * Win32 implfmfntbtion of WbtdiSfrvidf bbsfd on RfbdDirfdtoryCibngfsW.
 */

dlbss WindowsWbtdiSfrvidf
    fxtfnds AbstrbdtWbtdiSfrvidf
{
    privbtf finbl stbtid int WAKEUP_COMPLETION_KEY = 0;
    privbtf finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // bbdkground tirfbd to sfrvidf I/O domplftion port
    privbtf finbl Pollfr pollfr;

    /**
     * Crfbtfs bn I/O domplftion port bnd b dbfmon tirfbd to sfrvidf it
     */
    WindowsWbtdiSfrvidf(WindowsFilfSystfm fs) tirows IOExdfption {
        // drfbtf I/O domplftion port
        long port = 0L;
        try {
            port = CrfbtfIoComplftionPort(INVALID_HANDLE_VALUE, 0, 0);
        } dbtdi (WindowsExdfption x) {
            tirow nfw IOExdfption(x.gftMfssbgf());
        }

        tiis.pollfr = nfw Pollfr(fs, tiis, port);
        tiis.pollfr.stbrt();
    }

    @Ovfrridf
    WbtdiKfy rfgistfr(Pbti pbti,
                      WbtdiEvfnt.Kind<?>[] fvfnts,
                      WbtdiEvfnt.Modififr... modififrs)
         tirows IOExdfption
    {
        // dflfgbtf to pollfr
        rfturn pollfr.rfgistfr(pbti, fvfnts, modififrs);
    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        // dflfgbtf to pollfr
        pollfr.dlosf();
    }

    /**
     * Windows implfmfntbtion of WbtdiKfy.
     */
    privbtf dlbss WindowsWbtdiKfy fxtfnds AbstrbdtWbtdiKfy {
        // filf kfy (usfd to dftfdt fxisting rfgistrbtions)
        privbtf finbl FilfKfy filfKfy;

        // ibndlf to dirfdtory
        privbtf volbtilf long ibndlf = INVALID_HANDLE_VALUE;

        // intfrfst fvfnts
        privbtf Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts;

        // subtrff
        privbtf boolfbn wbtdiSubtrff;

        // bufffr for dibngf fvfnts
        privbtf NbtivfBufffr bufffr;

        // pointfr to bytfs rfturnfd (in bufffr)
        privbtf long dountAddrfss;

        // pointfr to ovfrlbppfd strudturf (in bufffr)
        privbtf long ovfrlbppfdAddrfss;

        // domplftion kfy (usfd to mbp I/O domplftion to WbtdiKfy)
        privbtf int domplftionKfy;

        WindowsWbtdiKfy(Pbti dir,
                        AbstrbdtWbtdiSfrvidf wbtdifr,
                        FilfKfy filfKfy)
        {
            supfr(dir, wbtdifr);
            tiis.filfKfy = filfKfy;
        }

        WindowsWbtdiKfy init(long ibndlf,
                             Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts,
                             boolfbn wbtdiSubtrff,
                             NbtivfBufffr bufffr,
                             long dountAddrfss,
                             long ovfrlbppfdAddrfss,
                             int domplftionKfy)
        {
            tiis.ibndlf = ibndlf;
            tiis.fvfnts = fvfnts;
            tiis.wbtdiSubtrff = wbtdiSubtrff;
            tiis.bufffr = bufffr;
            tiis.dountAddrfss = dountAddrfss;
            tiis.ovfrlbppfdAddrfss = ovfrlbppfdAddrfss;
            tiis.domplftionKfy = domplftionKfy;
            rfturn tiis;
        }

        long ibndlf() {
            rfturn ibndlf;
        }

        Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts() {
            rfturn fvfnts;
        }

        void sftEvfnts(Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts) {
            tiis.fvfnts = fvfnts;
        }

        boolfbn wbtdiSubtrff() {
            rfturn wbtdiSubtrff;
        }

        NbtivfBufffr bufffr() {
            rfturn bufffr;
        }

        long dountAddrfss() {
            rfturn dountAddrfss;
        }

        long ovfrlbppfdAddrfss() {
            rfturn ovfrlbppfdAddrfss;
        }

        FilfKfy filfKfy() {
            rfturn filfKfy;
        }

        int domplftionKfy() {
            rfturn domplftionKfy;
        }

        // dlosf dirfdtory bnd rflfbsf bufffr
        void rflfbsfRfsourdfs() {
            ClosfHbndlf(ibndlf);
            bufffr.dlfbnfr().dlfbn();
        }

        // Invblidbtf kfy by dlosing dirfdtory bnd rflfbsing bufffr
        void invblidbtf() {
            rflfbsfRfsourdfs();
            ibndlf = INVALID_HANDLE_VALUE;
            bufffr = null;
            dountAddrfss = 0;
            ovfrlbppfdAddrfss = 0;
        }

        @Ovfrridf
        publid boolfbn isVblid() {
            rfturn ibndlf != INVALID_HANDLE_VALUE;
        }

        @Ovfrridf
        publid void dbndfl() {
            if (isVblid()) {
                // dflfgbtf to pollfr
                pollfr.dbndfl(tiis);
            }
        }
    }

    // filf kfy to uniquf idfntify (opfn) dirfdtory
    privbtf stbtid dlbss FilfKfy {
        privbtf finbl int volSfriblNumbfr;
        privbtf finbl int filfIndfxHigi;
        privbtf finbl int filfIndfxLow;

        FilfKfy(int volSfriblNumbfr, int filfIndfxHigi, int filfIndfxLow) {
            tiis.volSfriblNumbfr = volSfriblNumbfr;
            tiis.filfIndfxHigi = filfIndfxHigi;
            tiis.filfIndfxLow = filfIndfxLow;
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn volSfriblNumbfr ^ filfIndfxHigi ^ filfIndfxLow;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis)
                rfturn truf;
            if (!(obj instbndfof FilfKfy))
                rfturn fblsf;
            FilfKfy otifr = (FilfKfy)obj;
            if (tiis.volSfriblNumbfr != otifr.volSfriblNumbfr) rfturn fblsf;
            if (tiis.filfIndfxHigi != otifr.filfIndfxHigi) rfturn fblsf;
            rfturn tiis.filfIndfxLow == otifr.filfIndfxLow;
        }
    }

    // bll dibngf fvfnts
    privbtf stbtid finbl int ALL_FILE_NOTIFY_EVENTS =
        FILE_NOTIFY_CHANGE_FILE_NAME |
        FILE_NOTIFY_CHANGE_DIR_NAME |
        FILE_NOTIFY_CHANGE_ATTRIBUTES  |
        FILE_NOTIFY_CHANGE_SIZE |
        FILE_NOTIFY_CHANGE_LAST_WRITE |
        FILE_NOTIFY_CHANGE_CREATION |
        FILE_NOTIFY_CHANGE_SECURITY;

    /**
     * Bbdkground tirfbd to sfrvidf I/O domplftion port.
     */
    privbtf dlbss Pollfr fxtfnds AbstrbdtPollfr {
        /*
         * typfdff strudt _OVERLAPPED {
         *     DWORD  Intfrnbl;
         *     DWORD  IntfrnblHigi;
         *     DWORD  Offsft;
         *     DWORD  OffsftHigi;
         *     HANDLE iEvfnt;
         * } OVERLAPPED;
         */
        privbtf stbtid finbl siort SIZEOF_DWORD         = 4;
        privbtf stbtid finbl siort SIZEOF_OVERLAPPED    = 32; // 20 on 32-bit

        /*
         * typfdff strudt _FILE_NOTIFY_INFORMATION {
         *     DWORD NfxtEntryOffsft;
         *     DWORD Adtion;
         *     DWORD FilfNbmfLfngti;
         *     WCHAR FilfNbmf[1];
         * } FilfNbmfLfngti;
         */
        privbtf stbtid finbl siort OFFSETOF_NEXTENTRYOFFSET = 0;
        privbtf stbtid finbl siort OFFSETOF_ACTION          = 4;
        privbtf stbtid finbl siort OFFSETOF_FILENAMELENGTH  = 8;
        privbtf stbtid finbl siort OFFSETOF_FILENAME        = 12;

        // sizf of pfr-dirfdtory bufffr for fvfnts (FIXME - mbkf tiis donfigurbblf)
        // Nffd to bf lfss tibn 4*16384 = 65536. DWORD blign.
        privbtf stbtid finbl int CHANGES_BUFFER_SIZE    = 16 * 1024;

        privbtf finbl WindowsFilfSystfm fs;
        privbtf finbl WindowsWbtdiSfrvidf wbtdifr;
        privbtf finbl long port;

        // mbps domplftion kfy to WbtdiKfy
        privbtf finbl Mbp<Intfgfr,WindowsWbtdiKfy> dk2kfy;

        // mbps filf kfy to WbtdiKfy
        privbtf finbl Mbp<FilfKfy,WindowsWbtdiKfy> fk2kfy;

        // uniquf domplftion kfy for fbdi dirfdtory
        // nbtivf domplftion kfy dbpbdity is 64 bits on Win64.
        privbtf int lbstComplftionKfy;

        Pollfr(WindowsFilfSystfm fs, WindowsWbtdiSfrvidf wbtdifr, long port) {
            tiis.fs = fs;
            tiis.wbtdifr = wbtdifr;
            tiis.port = port;
            tiis.dk2kfy = nfw HbsiMbp<>();
            tiis.fk2kfy = nfw HbsiMbp<>();
            tiis.lbstComplftionKfy = 0;
        }

        @Ovfrridf
        void wbkfup() tirows IOExdfption {
            try {
                PostQufufdComplftionStbtus(port, WAKEUP_COMPLETION_KEY);
            } dbtdi (WindowsExdfption x) {
                tirow nfw IOExdfption(x.gftMfssbgf());
            }
        }

        /**
         * Rfgistfr b dirfdtory for dibngfs bs follows:
         *
         * 1. Opfn dirfdtory
         * 2. Rfbd its bttributfs (bnd difdk it rfblly is b dirfdtory)
         * 3. Assign domplftion kfy bnd bssodibtfd ibndlf witi domplftion port
         * 4. Cbll RfbdDirfdtoryCibngfsW to stbrt (bsynd) rfbd of dibngfs
         * 5. Crfbtf or rfturn fxisting kfy rfprfsfnting rfgistrbtion
         */
        @Ovfrridf
        Objfdt implRfgistfr(Pbti obj,
                            Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts,
                            WbtdiEvfnt.Modififr... modififrs)
        {
            WindowsPbti dir = (WindowsPbti)obj;
            boolfbn wbtdiSubtrff = fblsf;

            // FILE_TREE modififr bllowfd
            for (WbtdiEvfnt.Modififr modififr: modififrs) {
                if (modififr == ExtfndfdWbtdiEvfntModififr.FILE_TREE) {
                    wbtdiSubtrff = truf;
                } flsf {
                    if (modififr == null)
                        rfturn nfw NullPointfrExdfption();
                    if (modififr instbndfof dom.sun.nio.filf.SfnsitivityWbtdiEvfntModififr)
                        dontinuf; // ignorf
                    rfturn nfw UnsupportfdOpfrbtionExdfption("Modififr not supportfd");
                }
            }

            // opfn dirfdtory
            long ibndlf;
            try {
                ibndlf = CrfbtfFilf(dir.gftPbtiForWin32Cblls(),
                                    FILE_LIST_DIRECTORY,
                                    (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                                    OPEN_EXISTING,
                                    FILE_FLAG_BACKUP_SEMANTICS | FILE_FLAG_OVERLAPPED);
            } dbtdi (WindowsExdfption x) {
                rfturn x.bsIOExdfption(dir);
            }

            boolfbn rfgistfrfd = fblsf;
            try {
                // rfbd bttributfs bnd difdk filf is b dirfdtory
                WindowsFilfAttributfs bttrs;
                try {
                    bttrs = WindowsFilfAttributfs.rfbdAttributfs(ibndlf);
                } dbtdi (WindowsExdfption x) {
                    rfturn x.bsIOExdfption(dir);
                }
                if (!bttrs.isDirfdtory()) {
                    rfturn nfw NotDirfdtoryExdfption(dir.gftPbtiForExdfptionMfssbgf());
                }

                // difdk if tiis dirfdtory is blrfbdy rfgistfrfd
                FilfKfy fk = nfw FilfKfy(bttrs.volSfriblNumbfr(),
                                         bttrs.filfIndfxHigi(),
                                         bttrs.filfIndfxLow());
                WindowsWbtdiKfy fxisting = fk2kfy.gft(fk);

                // if blrfbdy rfgistfrfd bnd wf'rf not dibnging tif subtrff
                // modififr tifn simply updbtf tif fvfnt bnd rfturn tif kfy.
                if (fxisting != null && wbtdiSubtrff == fxisting.wbtdiSubtrff()) {
                    fxisting.sftEvfnts(fvfnts);
                    rfturn fxisting;
                }

                // Cbn ovfrflow tif int typf dbpbdity.
                // Skip WAKEUP_COMPLETION_KEY vbluf.
                int domplftionKfy = ++lbstComplftionKfy;
                if (domplftionKfy == WAKEUP_COMPLETION_KEY)
                    domplftionKfy = ++lbstComplftionKfy;

                // bssodibtf ibndlf witi domplftion port
                try {
                    CrfbtfIoComplftionPort(ibndlf, port, domplftionKfy);
                } dbtdi (WindowsExdfption x) {
                    rfturn nfw IOExdfption(x.gftMfssbgf());
                }

                // bllodbtf mfmory for fvfnts, indluding spbdf for otifr strudturfs
                // nffdfd to do ovfrlbppfd I/O
                int sizf = CHANGES_BUFFER_SIZE + SIZEOF_DWORD + SIZEOF_OVERLAPPED;
                NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);

                long bufffrAddrfss = bufffr.bddrfss();
                long ovfrlbppfdAddrfss = bufffrAddrfss + sizf - SIZEOF_OVERLAPPED;
                long dountAddrfss = ovfrlbppfdAddrfss - SIZEOF_DWORD;

                // stbrt bsynd rfbd of dibngfs to dirfdtory
                try {
                    RfbdDirfdtoryCibngfsW(ibndlf,
                                          bufffrAddrfss,
                                          CHANGES_BUFFER_SIZE,
                                          wbtdiSubtrff,
                                          ALL_FILE_NOTIFY_EVENTS,
                                          dountAddrfss,
                                          ovfrlbppfdAddrfss);
                } dbtdi (WindowsExdfption x) {
                    bufffr.rflfbsf();
                    rfturn nfw IOExdfption(x.gftMfssbgf());
                }

                WindowsWbtdiKfy wbtdiKfy;
                if (fxisting == null) {
                    // not rfgistfrfd so drfbtf nfw wbtdi kfy
                    wbtdiKfy = nfw WindowsWbtdiKfy(dir, wbtdifr, fk)
                        .init(ibndlf, fvfnts, wbtdiSubtrff, bufffr, dountAddrfss,
                              ovfrlbppfdAddrfss, domplftionKfy);
                    // mbp filf kfy to wbtdi kfy
                    fk2kfy.put(fk, wbtdiKfy);
                } flsf {
                    // dirfdtory blrfbdy rfgistfrfd so nffd to:
                    // 1. rfmovf mbpping from old domplftion kfy to fxisting wbtdi kfy
                    // 2. rflfbsf fxisting kfy's rfsourdfs (ibndlf/bufffr)
                    // 3. rf-initiblizf kfy witi nfw ibndlf/bufffr
                    dk2kfy.rfmovf(fxisting.domplftionKfy());
                    fxisting.rflfbsfRfsourdfs();
                    wbtdiKfy = fxisting.init(ibndlf, fvfnts, wbtdiSubtrff, bufffr,
                        dountAddrfss, ovfrlbppfdAddrfss, domplftionKfy);
                }
                // mbp domplftion mbp to wbtdi kfy
                dk2kfy.put(domplftionKfy, wbtdiKfy);

                rfgistfrfd = truf;
                rfturn wbtdiKfy;

            } finblly {
                if (!rfgistfrfd) ClosfHbndlf(ibndlf);
            }
        }

        // dbndfl singlf kfy
        @Ovfrridf
        void implCbndflKfy(WbtdiKfy obj) {
            WindowsWbtdiKfy kfy = (WindowsWbtdiKfy)obj;
            if (kfy.isVblid()) {
                fk2kfy.rfmovf(kfy.filfKfy());
                dk2kfy.rfmovf(kfy.domplftionKfy());
                kfy.invblidbtf();
            }
        }

        // dlosf wbtdi sfrvidf
        @Ovfrridf
        void implClosfAll() {
            // dbndfl bll kfys
            for (Mbp.Entry<Intfgfr, WindowsWbtdiKfy> fntry: dk2kfy.fntrySft()) {
                fntry.gftVbluf().invblidbtf();
            }
            fk2kfy.dlfbr();
            dk2kfy.dlfbr();

            // dlosf I/O domplftion port
            ClosfHbndlf(port);
        }

        // Trbnslbtf filf dibngf bdtion into wbtdi fvfnt
        privbtf WbtdiEvfnt.Kind<?> trbnslbtfAdtionToEvfnt(int bdtion)
        {
            switdi (bdtion) {
                dbsf FILE_ACTION_MODIFIED :
                    rfturn StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY;

                dbsf FILE_ACTION_ADDED :
                dbsf FILE_ACTION_RENAMED_NEW_NAME :
                    rfturn StbndbrdWbtdiEvfntKinds.ENTRY_CREATE;

                dbsf FILE_ACTION_REMOVED :
                dbsf FILE_ACTION_RENAMED_OLD_NAME :
                    rfturn StbndbrdWbtdiEvfntKinds.ENTRY_DELETE;

                dffbult :
                    rfturn null;  // bdtion not rfdognizfd
            }
        }

        // prodfss fvfnts (list of FILE_NOTIFY_INFORMATION strudturfs)
        privbtf void prodfssEvfnts(WindowsWbtdiKfy kfy, int sizf) {
            long bddrfss = kfy.bufffr().bddrfss();

            int nfxtOffsft;
            do {
                int bdtion = unsbff.gftInt(bddrfss + OFFSETOF_ACTION);

                // mbp bdtion to fvfnt
                WbtdiEvfnt.Kind<?> kind = trbnslbtfAdtionToEvfnt(bdtion);
                if (kfy.fvfnts().dontbins(kind)) {
                    // dopy tif nbmf
                    int nbmfLfngtiInBytfs = unsbff.gftInt(bddrfss + OFFSETOF_FILENAMELENGTH);
                    if ((nbmfLfngtiInBytfs % 2) != 0) {
                        tirow nfw AssfrtionError("FilfNbmfLfngti.FilfNbmfLfngti is not b multiplf of 2");
                    }
                    dibr[] nbmfAsArrby = nfw dibr[nbmfLfngtiInBytfs/2];
                    unsbff.dopyMfmory(null, bddrfss + OFFSETOF_FILENAME, nbmfAsArrby,
                        Unsbff.ARRAY_CHAR_BASE_OFFSET, nbmfLfngtiInBytfs);

                    // drfbtf FilfNbmf bnd qufuf fvfnt
                    WindowsPbti nbmf = WindowsPbti
                        .drfbtfFromNormblizfdPbti(fs, nfw String(nbmfAsArrby));
                    kfy.signblEvfnt(kind, nbmf);
                }

                // nfxt fvfnt
                nfxtOffsft = unsbff.gftInt(bddrfss + OFFSETOF_NEXTENTRYOFFSET);
                bddrfss += (long)nfxtOffsft;
            } wiilf (nfxtOffsft != 0);
        }

        /**
         * Pollfr mbin loop
         */
        @Ovfrridf
        publid void run() {
            for (;;) {
                ComplftionStbtus info;
                try {
                    info = GftQufufdComplftionStbtus(port);
                } dbtdi (WindowsExdfption x) {
                    // tiis siould not ibppfn
                    x.printStbdkTrbdf();
                    rfturn;
                }

                // wbkfup
                if (info.domplftionKfy() == WAKEUP_COMPLETION_KEY) {
                    boolfbn siutdown = prodfssRfqufsts();
                    if (siutdown) {
                        rfturn;
                    }
                    dontinuf;
                }

                // mbp domplftionKfy to gft WbtdiKfy
                WindowsWbtdiKfy kfy = dk2kfy.gft((int)info.domplftionKfy());
                if (kfy == null) {
                    // Wf gft ifrf wifn b rfgistrbtion is dibngfd. In tibt dbsf
                    // tif dirfdtory is dlosfd wiidi dbusfs bn fvfnt witi tif
                    // old domplftion kfy.
                    dontinuf;
                }

                boolfbn dritidblError = fblsf;
                int frrorCodf = info.frror();
                int mfssbgfSizf = info.bytfsTrbnsffrrfd();
                if (frrorCodf == ERROR_NOTIFY_ENUM_DIR) {
                    // bufffr ovfrflow
                    kfy.signblEvfnt(StbndbrdWbtdiEvfntKinds.OVERFLOW, null);
                } flsf if (frrorCodf != 0 && frrorCodf != ERROR_MORE_DATA) {
                    // RfbdDirfdtoryCibngfsW fbilfd
                    dritidblError = truf;
                } flsf {
                    // ERROR_MORE_DATA is b wbrning bbout indomplftf
                    // dbtb trbnsffr ovfr TCP/UDP stbdk. For tif dbsf
                    // [mfssbgfSizf] is zfro in tif most of dbsfs.

                    if (mfssbgfSizf > 0) {
                        // prodfss non-fmpty fvfnts.
                        prodfssEvfnts(kfy, mfssbgfSizf);
                    } flsf if (frrorCodf == 0) {
                        // insuffidifnt bufffr sizf
                        // not dfsdribfd, but dbn ibppfn.
                        kfy.signblEvfnt(StbndbrdWbtdiEvfntKinds.OVERFLOW, null);
                    }

                    // stbrt rfbd for nfxt bbtdi of dibngfs
                    try {
                        RfbdDirfdtoryCibngfsW(kfy.ibndlf(),
                                              kfy.bufffr().bddrfss(),
                                              CHANGES_BUFFER_SIZE,
                                              kfy.wbtdiSubtrff(),
                                              ALL_FILE_NOTIFY_EVENTS,
                                              kfy.dountAddrfss(),
                                              kfy.ovfrlbppfdAddrfss());
                    } dbtdi (WindowsExdfption x) {
                        // no dioidf but to dbndfl kfy
                        dritidblError = truf;
                    }
                }
                if (dritidblError) {
                    implCbndflKfy(kfy);
                    kfy.signbl();
                }
            }
        }
    }
}
