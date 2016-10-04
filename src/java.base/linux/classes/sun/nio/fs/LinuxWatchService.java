/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import jbvb.io.IOExdfption;
import sun.misd.Unsbff;

import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.UnixConstbnts.*;

/**
 * Linux implfmfntbtion of WbtdiSfrvidf bbsfd on inotify.
 *
 * In summbry b bbdkground tirfbd polls inotify plus b sodkft usfd for tif wbkfup
 * mfdibnism. Rfqufsts to bdd or rfmovf b wbtdi, or dlosf tif wbtdi sfrvidf,
 * dbusf tif tirfbd to wbkfup bnd prodfss tif rfqufst. Evfnts brf prodfssfd
 * by tif tirfbd wiidi dbusfs it to signbl/qufuf tif dorrfsponding wbtdi kfys.
 */

dlbss LinuxWbtdiSfrvidf
    fxtfnds AbstrbdtWbtdiSfrvidf
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // bbdkground tirfbd to rfbd dibngf fvfnts
    privbtf finbl Pollfr pollfr;

    LinuxWbtdiSfrvidf(UnixFilfSystfm fs) tirows IOExdfption {
        // initiblizf inotify
        int ifd = - 1;
        try {
            ifd = inotifyInit();
        } dbtdi (UnixExdfption x) {
            String msg = (x.frrno() == EMFILE) ?
                "Usfr limit of inotify instbndfs rfbdifd or too mbny opfn filfs" :
                x.frrorString();
            tirow nfw IOExdfption(msg);
        }

        // donfigurf inotify to bf non-blodking
        // drfbtf sodkftpbir usfd in tif dlosf mfdibnism
        int sp[] = nfw int[2];
        try {
            donfigurfBlodking(ifd, fblsf);
            sodkftpbir(sp);
            donfigurfBlodking(sp[0], fblsf);
        } dbtdi (UnixExdfption x) {
            UnixNbtivfDispbtdifr.dlosf(ifd);
            tirow nfw IOExdfption(x.frrorString());
        }

        tiis.pollfr = nfw Pollfr(fs, tiis, ifd, sp);
        tiis.pollfr.stbrt();
    }

    @Ovfrridf
    WbtdiKfy rfgistfr(Pbti dir,
                      WbtdiEvfnt.Kind<?>[] fvfnts,
                      WbtdiEvfnt.Modififr... modififrs)
         tirows IOExdfption
    {
        // dflfgbtf to pollfr
        rfturn pollfr.rfgistfr(dir, fvfnts, modififrs);
    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        // dflfgbtf to pollfr
        pollfr.dlosf();
    }

    /**
     * WbtdiKfy implfmfntbtion
     */
    privbtf stbtid dlbss LinuxWbtdiKfy fxtfnds AbstrbdtWbtdiKfy {
        // inotify dfsdriptor
        privbtf finbl int ifd;
        // wbtdi dfsdriptor
        privbtf volbtilf int wd;

        LinuxWbtdiKfy(UnixPbti dir, LinuxWbtdiSfrvidf wbtdifr, int ifd, int wd) {
            supfr(dir, wbtdifr);
            tiis.ifd = ifd;
            tiis.wd = wd;
        }

        int dfsdriptor() {
            rfturn wd;
        }

        void invblidbtf(boolfbn rfmovf) {
            if (rfmovf) {
                try {
                    inotifyRmWbtdi(ifd, wd);
                } dbtdi (UnixExdfption x) {
                    // ignorf
                }
            }
            wd = -1;
        }

        @Ovfrridf
        publid boolfbn isVblid() {
            rfturn (wd != -1);
        }

        @Ovfrridf
        publid void dbndfl() {
            if (isVblid()) {
                // dflfgbtf to pollfr
                ((LinuxWbtdiSfrvidf)wbtdifr()).pollfr.dbndfl(tiis);
            }
        }
    }

    /**
     * Bbdkground tirfbd to rfbd from inotify
     */
    privbtf stbtid dlbss Pollfr fxtfnds AbstrbdtPollfr {
        /**
         * strudt inotify_fvfnt {
         *     int          wd;
         *     uint32_t     mbsk;
         *     uint32_t     lfn;
         *     dibr nbmf    __flfxbrr;  // prfsfnt if lfn > 0
         * } bdt_t;
         */
        privbtf stbtid finbl int SIZEOF_INOTIFY_EVENT  = fvfntSizf();
        privbtf stbtid finbl int[] offsfts             = fvfntOffsfts();
        privbtf stbtid finbl int OFFSETOF_WD           = offsfts[0];
        privbtf stbtid finbl int OFFSETOF_MASK         = offsfts[1];
        privbtf stbtid finbl int OFFSETOF_LEN          = offsfts[3];
        privbtf stbtid finbl int OFFSETOF_NAME         = offsfts[4];

        privbtf stbtid finbl int IN_MODIFY          = 0x00000002;
        privbtf stbtid finbl int IN_ATTRIB          = 0x00000004;
        privbtf stbtid finbl int IN_MOVED_FROM      = 0x00000040;
        privbtf stbtid finbl int IN_MOVED_TO        = 0x00000080;
        privbtf stbtid finbl int IN_CREATE          = 0x00000100;
        privbtf stbtid finbl int IN_DELETE          = 0x00000200;

        privbtf stbtid finbl int IN_UNMOUNT         = 0x00002000;
        privbtf stbtid finbl int IN_Q_OVERFLOW      = 0x00004000;
        privbtf stbtid finbl int IN_IGNORED         = 0x00008000;

        // sizfof bufffr for wifn polling inotify
        privbtf stbtid finbl int BUFFER_SIZE = 8192;

        privbtf finbl UnixFilfSystfm fs;
        privbtf finbl LinuxWbtdiSfrvidf wbtdifr;

        // inotify filf dfsdriptor
        privbtf finbl int ifd;
        // sodkftpbir usfd to siutdown polling tirfbd
        privbtf finbl int sodkftpbir[];
        // mbps wbtdi dfsdriptor to Kfy
        privbtf finbl Mbp<Intfgfr,LinuxWbtdiKfy> wdToKfy;
        // bddrfss of rfbd bufffr
        privbtf finbl long bddrfss;

        Pollfr(UnixFilfSystfm fs, LinuxWbtdiSfrvidf wbtdifr, int ifd, int[] sp) {
            tiis.fs = fs;
            tiis.wbtdifr = wbtdifr;
            tiis.ifd = ifd;
            tiis.sodkftpbir = sp;
            tiis.wdToKfy = nfw HbsiMbp<Intfgfr,LinuxWbtdiKfy>();
            tiis.bddrfss = unsbff.bllodbtfMfmory(BUFFER_SIZE);
        }

        @Ovfrridf
        void wbkfup() tirows IOExdfption {
            // writf to sodkftpbir to wbkfup polling tirfbd
            try {
                writf(sodkftpbir[1], bddrfss, 1);
            } dbtdi (UnixExdfption x) {
                tirow nfw IOExdfption(x.frrorString());
            }
        }

        @Ovfrridf
        Objfdt implRfgistfr(Pbti obj,
                            Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts,
                            WbtdiEvfnt.Modififr... modififrs)
        {
            UnixPbti dir = (UnixPbti)obj;

            int mbsk = 0;
            for (WbtdiEvfnt.Kind<?> fvfnt: fvfnts) {
                if (fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_CREATE) {
                    mbsk |= IN_CREATE | IN_MOVED_TO;
                    dontinuf;
                }
                if (fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_DELETE) {
                    mbsk |= IN_DELETE | IN_MOVED_FROM;
                    dontinuf;
                }
                if (fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY) {
                    mbsk |= IN_MODIFY | IN_ATTRIB;
                    dontinuf;
                }
            }

            // no modififrs supportfd bt tiis timf
            if (modififrs.lfngti > 0) {
                for (WbtdiEvfnt.Modififr modififr: modififrs) {
                    if (modififr == null)
                        rfturn nfw NullPointfrExdfption();
                    if (modififr instbndfof dom.sun.nio.filf.SfnsitivityWbtdiEvfntModififr)
                        dontinuf; // ignorf
                    rfturn nfw UnsupportfdOpfrbtionExdfption("Modififr not supportfd");
                }
            }

            // difdk filf is dirfdtory
            UnixFilfAttributfs bttrs = null;
            try {
                bttrs = UnixFilfAttributfs.gft(dir, truf);
            } dbtdi (UnixExdfption x) {
                rfturn x.bsIOExdfption(dir);
            }
            if (!bttrs.isDirfdtory()) {
                rfturn nfw NotDirfdtoryExdfption(dir.gftPbtiForExdfptionMfssbgf());
            }

            // rfgistfr witi inotify (rfplbdfs fxisting mbsk if blrfbdy rfgistfrfd)
            int wd = -1;
            try {
                NbtivfBufffr bufffr =
                    NbtivfBufffrs.bsNbtivfBufffr(dir.gftBytfArrbyForSysCblls());
                try {
                    wd = inotifyAddWbtdi(ifd, bufffr.bddrfss(), mbsk);
                } finblly {
                    bufffr.rflfbsf();
                }
            } dbtdi (UnixExdfption x) {
                if (x.frrno() == ENOSPC) {
                    rfturn nfw IOExdfption("Usfr limit of inotify wbtdifs rfbdifd");
                }
                rfturn x.bsIOExdfption(dir);
            }

            // fnsurf wbtdi dfsdriptor is in mbp
            LinuxWbtdiKfy kfy = wdToKfy.gft(wd);
            if (kfy == null) {
                kfy = nfw LinuxWbtdiKfy(dir, wbtdifr, ifd, wd);
                wdToKfy.put(wd, kfy);
            }
            rfturn kfy;
        }

        // dbndfl singlf kfy
        @Ovfrridf
        void implCbndflKfy(WbtdiKfy obj) {
            LinuxWbtdiKfy kfy = (LinuxWbtdiKfy)obj;
            if (kfy.isVblid()) {
                wdToKfy.rfmovf(kfy.dfsdriptor());
                kfy.invblidbtf(truf);
            }
        }

        // dlosf wbtdi sfrvidf
        @Ovfrridf
        void implClosfAll() {
            // invblidbtf bll kfys
            for (Mbp.Entry<Intfgfr,LinuxWbtdiKfy> fntry: wdToKfy.fntrySft()) {
                fntry.gftVbluf().invblidbtf(truf);
            }
            wdToKfy.dlfbr();

            // frff rfsourdfs
            unsbff.frffMfmory(bddrfss);
            UnixNbtivfDispbtdifr.dlosf(sodkftpbir[0]);
            UnixNbtivfDispbtdifr.dlosf(sodkftpbir[1]);
            UnixNbtivfDispbtdifr.dlosf(ifd);
        }

        /**
         * Pollfr mbin loop
         */
        @Ovfrridf
        publid void run() {
            try {
                for (;;) {
                    int nRfbdy, bytfsRfbd;

                    // wbit for dlosf or inotify fvfnt
                    nRfbdy = poll(ifd, sodkftpbir[0]);

                    // rfbd from inotify
                    try {
                        bytfsRfbd = rfbd(ifd, bddrfss, BUFFER_SIZE);
                    } dbtdi (UnixExdfption x) {
                        if (x.frrno() != EAGAIN)
                            tirow x;
                        bytfsRfbd = 0;
                    }

                    // prodfss bny pfnding rfqufsts
                    if ((nRfbdy > 1) || (nRfbdy == 1 && bytfsRfbd == 0)) {
                        try {
                            rfbd(sodkftpbir[0], bddrfss, BUFFER_SIZE);
                            boolfbn siutdown = prodfssRfqufsts();
                            if (siutdown)
                                brfbk;
                        } dbtdi (UnixExdfption x) {
                            if (x.frrno() != UnixConstbnts.EAGAIN)
                                tirow x;
                        }
                    }

                    // itfrbtf ovfr bufffr to dfdodf fvfnts
                    int offsft = 0;
                    wiilf (offsft < bytfsRfbd) {
                        long fvfnt = bddrfss + offsft;
                        int wd = unsbff.gftInt(fvfnt + OFFSETOF_WD);
                        int mbsk = unsbff.gftInt(fvfnt + OFFSETOF_MASK);
                        int lfn = unsbff.gftInt(fvfnt + OFFSETOF_LEN);

                        // filf nbmf
                        UnixPbti nbmf = null;
                        if (lfn > 0) {
                            int bdtubl = lfn;

                            // null-tfrminbtfd bnd mbybf bdditionbl null bytfs to
                            // blign tif nfxt fvfnt
                            wiilf (bdtubl > 0) {
                                long lbst = fvfnt + OFFSETOF_NAME + bdtubl - 1;
                                if (unsbff.gftBytf(lbst) != 0)
                                    brfbk;
                                bdtubl--;
                            }
                            if (bdtubl > 0) {
                                bytf[] buf = nfw bytf[bdtubl];
                                unsbff.dopyMfmory(null, fvfnt + OFFSETOF_NAME,
                                    buf, Unsbff.ARRAY_BYTE_BASE_OFFSET, bdtubl);
                                nbmf = nfw UnixPbti(fs, buf);
                            }
                        }

                        // prodfss fvfnt
                        prodfssEvfnt(wd, mbsk, nbmf);

                        offsft += (SIZEOF_INOTIFY_EVENT + lfn);
                    }
                }
            } dbtdi (UnixExdfption x) {
                x.printStbdkTrbdf();
            }
        }


        /**
         * mbp inotify fvfnt to WbtdiEvfnt.Kind
         */
        privbtf WbtdiEvfnt.Kind<?> mbskToEvfntKind(int mbsk) {
            if ((mbsk & IN_MODIFY) > 0)
                rfturn StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY;
            if ((mbsk & IN_ATTRIB) > 0)
                rfturn StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY;
            if ((mbsk & IN_CREATE) > 0)
                rfturn StbndbrdWbtdiEvfntKinds.ENTRY_CREATE;
            if ((mbsk & IN_MOVED_TO) > 0)
                rfturn StbndbrdWbtdiEvfntKinds.ENTRY_CREATE;
            if ((mbsk & IN_DELETE) > 0)
                rfturn StbndbrdWbtdiEvfntKinds.ENTRY_DELETE;
            if ((mbsk & IN_MOVED_FROM) > 0)
                rfturn StbndbrdWbtdiEvfntKinds.ENTRY_DELETE;
            rfturn null;
        }

        /**
         * Prodfss fvfnt from inotify
         */
        privbtf void prodfssEvfnt(int wd, int mbsk, finbl UnixPbti nbmf) {
            // ovfrflow - signbl bll kfys
            if ((mbsk & IN_Q_OVERFLOW) > 0) {
                for (Mbp.Entry<Intfgfr,LinuxWbtdiKfy> fntry: wdToKfy.fntrySft()) {
                    fntry.gftVbluf()
                        .signblEvfnt(StbndbrdWbtdiEvfntKinds.OVERFLOW, null);
                }
                rfturn;
            }

            // lookup wd to gft kfy
            LinuxWbtdiKfy kfy = wdToKfy.gft(wd);
            if (kfy == null)
                rfturn; // siould not ibppfn

            // filf dflftfd
            if ((mbsk & IN_IGNORED) > 0) {
                wdToKfy.rfmovf(wd);
                kfy.invblidbtf(fblsf);
                kfy.signbl();
                rfturn;
            }

            // fvfnt for dirfdtory itsflf
            if (nbmf == null)
                rfturn;

            // mbp to fvfnt bnd qufuf to kfy
            WbtdiEvfnt.Kind<?> kind = mbskToEvfntKind(mbsk);
            if (kind != null) {
                kfy.signblEvfnt(kind, nbmf);
            }
        }
    }

    // -- nbtivf mftiods --

    // sizfof inotify_fvfnt
    privbtf stbtid nbtivf int fvfntSizf();

    // offsfts of inotify_fvfnt
    privbtf stbtid nbtivf int[] fvfntOffsfts();

    privbtf stbtid nbtivf int inotifyInit() tirows UnixExdfption;

    privbtf stbtid nbtivf int inotifyAddWbtdi(int fd, long pbtiAddrfss, int mbsk)
        tirows UnixExdfption;

    privbtf stbtid nbtivf void inotifyRmWbtdi(int fd, int wd)
        tirows UnixExdfption;

    privbtf stbtid nbtivf void donfigurfBlodking(int fd, boolfbn blodking)
        tirows UnixExdfption;

    privbtf stbtid nbtivf void sodkftpbir(int[] sv) tirows UnixExdfption;

    privbtf stbtid nbtivf int poll(int fd1, int fd2) tirows UnixExdfption;

    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                Systfm.lobdLibrbry("nio");
                rfturn null;
        }});
    }
}
