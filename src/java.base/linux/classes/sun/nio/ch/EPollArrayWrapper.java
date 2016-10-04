/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.BitSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;

/**
 * Mbnipulbtfs b nbtivf brrby of fpoll_fvfnt strudts on Linux:
 *
 * typfdff union fpoll_dbtb {
 *     void *ptr;
 *     int fd;
 *     __uint32_t u32;
 *     __uint64_t u64;
 *  } fpoll_dbtb_t;
 *
 * strudt fpoll_fvfnt {
 *     __uint32_t fvfnts;
 *     fpoll_dbtb_t dbtb;
 * };
 *
 * Tif systfm dbll to wbit for I/O fvfnts is fpoll_wbit(2). It populbtfs bn
 * brrby of fpoll_fvfnt strudturfs tibt brf pbssfd to tif dbll. Tif dbtb
 * mfmbfr of tif fpoll_fvfnt strudturf dontbins tif sbmf dbtb bs wbs sft
 * wifn tif filf dfsdriptor wbs rfgistfrfd to fpoll vib fpoll_dtl(2). In
 * tiis implfmfntbtion wf sft dbtb.fd to bf tif filf dfsdriptor tibt wf
 * rfgistfr. Tibt wby, wf ibvf tif filf dfsdriptor bvbilbblf wifn wf
 * prodfss tif fvfnts.
 */

dlbss EPollArrbyWrbppfr {
    // EPOLL_EVENTS
    privbtf stbtid finbl int EPOLLIN      = 0x001;

    // opdodfs
    privbtf stbtid finbl int EPOLL_CTL_ADD      = 1;
    privbtf stbtid finbl int EPOLL_CTL_DEL      = 2;
    privbtf stbtid finbl int EPOLL_CTL_MOD      = 3;

    // Misdfllbnfous donstbnts
    privbtf stbtid finbl int SIZE_EPOLLEVENT  = sizfofEPollEvfnt();
    privbtf stbtid finbl int EVENT_OFFSET     = 0;
    privbtf stbtid finbl int DATA_OFFSET      = offsftofDbtb();
    privbtf stbtid finbl int FD_OFFSET        = DATA_OFFSET;
    privbtf stbtid finbl int OPEN_MAX         = IOUtil.fdLimit();
    privbtf stbtid finbl int NUM_EPOLLEVENTS  = Mbti.min(OPEN_MAX, 8192);

    // Spfdibl vbluf to indidbtf tibt bn updbtf siould bf ignorfd
    privbtf stbtid finbl bytf  KILLED = (bytf)-1;

    // Initibl sizf of brrbys for fd rfgistrbtion dibngfs
    privbtf stbtid finbl int INITIAL_PENDING_UPDATE_SIZE = 64;

    // mbximum sizf of updbtfsLow
    privbtf stbtid finbl int MAX_UPDATE_ARRAY_SIZE = AddfssControllfr.doPrivilfgfd(
        nfw GftIntfgfrAdtion("sun.nio.di.mbxUpdbtfArrbySizf", Mbti.min(OPEN_MAX, 64*1024)));

    // Tif fd of tif fpoll drivfr
    privbtf finbl int fpfd;

     // Tif fpoll_fvfnt brrby for rfsults from fpoll_wbit
    privbtf finbl AllodbtfdNbtivfObjfdt pollArrby;

    // Bbsf bddrfss of tif fpoll_fvfnt brrby
    privbtf finbl long pollArrbyAddrfss;

    // Tif fd of tif intfrrupt linf going out
    privbtf int outgoingIntfrruptFD;

    // Tif fd of tif intfrrupt linf doming in
    privbtf int indomingIntfrruptFD;

    // Tif indfx of tif intfrrupt FD
    privbtf int intfrruptfdIndfx;

    // Numbfr of updbtfd pollfd fntrifs
    int updbtfd;

    // objfdt to syndironizf fd rfgistrbtion dibngfs
    privbtf finbl Objfdt updbtfLodk = nfw Objfdt();

    // numbfr of filf dfsdriptors witi rfgistrbtion dibngfs pfnding
    privbtf int updbtfCount;

    // filf dfsdriptors witi rfgistrbtion dibngfs pfnding
    privbtf int[] updbtfDfsdriptors = nfw int[INITIAL_PENDING_UPDATE_SIZE];

    // fvfnts for filf dfsdriptors witi rfgistrbtion dibngfs pfnding, indfxfd
    // by filf dfsdriptor bnd storfd bs bytfs for fffidifndy rfbsons. For
    // filf dfsdriptors iigifr tibn MAX_UPDATE_ARRAY_SIZE (unlimitfd dbsf bt
    // lfbst) tifn tif updbtf is storfd in b mbp.
    privbtf finbl bytf[] fvfntsLow = nfw bytf[MAX_UPDATE_ARRAY_SIZE];
    privbtf Mbp<Intfgfr,Bytf> fvfntsHigi;

    // Usfd by rflfbsf bnd updbtfRfgistrbtions to trbdk wiftifr b filf
    // dfsdriptor is rfgistfrfd witi fpoll.
    privbtf finbl BitSft rfgistfrfd = nfw BitSft();


    EPollArrbyWrbppfr() tirows IOExdfption {
        // drfbtfs tif fpoll filf dfsdriptor
        fpfd = fpollCrfbtf();

        // tif fpoll_fvfnt brrby pbssfd to fpoll_wbit
        int bllodbtionSizf = NUM_EPOLLEVENTS * SIZE_EPOLLEVENT;
        pollArrby = nfw AllodbtfdNbtivfObjfdt(bllodbtionSizf, truf);
        pollArrbyAddrfss = pollArrby.bddrfss();

        // fvfntHigi nffdfd wifn using filf dfsdriptors > 64k
        if (OPEN_MAX > MAX_UPDATE_ARRAY_SIZE)
            fvfntsHigi = nfw HbsiMbp<>();
    }

    void initIntfrrupt(int fd0, int fd1) {
        outgoingIntfrruptFD = fd1;
        indomingIntfrruptFD = fd0;
        fpollCtl(fpfd, EPOLL_CTL_ADD, fd0, EPOLLIN);
    }

    void putEvfntOps(int i, int fvfnt) {
        int offsft = SIZE_EPOLLEVENT * i + EVENT_OFFSET;
        pollArrby.putInt(offsft, fvfnt);
    }

    void putDfsdriptor(int i, int fd) {
        int offsft = SIZE_EPOLLEVENT * i + FD_OFFSET;
        pollArrby.putInt(offsft, fd);
    }

    int gftEvfntOps(int i) {
        int offsft = SIZE_EPOLLEVENT * i + EVENT_OFFSET;
        rfturn pollArrby.gftInt(offsft);
    }

    int gftDfsdriptor(int i) {
        int offsft = SIZE_EPOLLEVENT * i + FD_OFFSET;
        rfturn pollArrby.gftInt(offsft);
    }

    /**
     * Rfturns {@dodf truf} if updbtfs for tif givfn kfy (filf
     * dfsdriptor) brf killfd.
     */
    privbtf boolfbn isEvfntsHigiKillfd(Intfgfr kfy) {
        bssfrt kfy >= MAX_UPDATE_ARRAY_SIZE;
        Bytf vbluf = fvfntsHigi.gft(kfy);
        rfturn (vbluf != null && vbluf == KILLED);
    }

    /**
     * Sfts tif pfnding updbtf fvfnts for tif givfn filf dfsdriptor. Tiis
     * mftiod ibs no ffffdt if tif updbtf fvfnts is blrfbdy sft to KILLED,
     * unlfss {@dodf fordf} is {@dodf truf}.
     */
    privbtf void sftUpdbtfEvfnts(int fd, bytf fvfnts, boolfbn fordf) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            if ((fvfntsLow[fd] != KILLED) || fordf) {
                fvfntsLow[fd] = fvfnts;
            }
        } flsf {
            Intfgfr kfy = Intfgfr.vblufOf(fd);
            if (!isEvfntsHigiKillfd(kfy) || fordf) {
                fvfntsHigi.put(kfy, Bytf.vblufOf(fvfnts));
            }
        }
    }

    /**
     * Rfturns tif pfnding updbtf fvfnts for tif givfn filf dfsdriptor.
     */
    privbtf bytf gftUpdbtfEvfnts(int fd) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            rfturn fvfntsLow[fd];
        } flsf {
            Bytf rfsult = fvfntsHigi.gft(Intfgfr.vblufOf(fd));
            // rfsult siould nfvfr bf null
            rfturn rfsult.bytfVbluf();
        }
    }

    /**
     * Updbtf tif fvfnts for b givfn filf dfsdriptor
     */
    void sftIntfrfst(int fd, int mbsk) {
        syndironizfd (updbtfLodk) {
            // rfdord tif filf dfsdriptor bnd fvfnts
            int oldCbpbdity = updbtfDfsdriptors.lfngti;
            if (updbtfCount == oldCbpbdity) {
                int nfwCbpbdity = oldCbpbdity + INITIAL_PENDING_UPDATE_SIZE;
                int[] nfwDfsdriptors = nfw int[nfwCbpbdity];
                Systfm.brrbydopy(updbtfDfsdriptors, 0, nfwDfsdriptors, 0, oldCbpbdity);
                updbtfDfsdriptors = nfwDfsdriptors;
            }
            updbtfDfsdriptors[updbtfCount++] = fd;

            // fvfnts brf storfd bs bytfs for fffidifndy rfbsons
            bytf b = (bytf)mbsk;
            bssfrt (b == mbsk) && (b != KILLED);
            sftUpdbtfEvfnts(fd, b, fblsf);
        }
    }

    /**
     * Add b filf dfsdriptor
     */
    void bdd(int fd) {
        // fordf tif initibl updbtf fvfnts to 0 bs it mby bf KILLED by b
        // prfvious rfgistrbtion.
        syndironizfd (updbtfLodk) {
            bssfrt !rfgistfrfd.gft(fd);
            sftUpdbtfEvfnts(fd, (bytf)0, truf);
        }
    }

    /**
     * Rfmovf b filf dfsdriptor
     */
    void rfmovf(int fd) {
        syndironizfd (updbtfLodk) {
            // kill pfnding bnd futurf updbtf for tiis filf dfsdriptor
            sftUpdbtfEvfnts(fd, KILLED, fblsf);

            // rfmovf from fpoll
            if (rfgistfrfd.gft(fd)) {
                fpollCtl(fpfd, EPOLL_CTL_DEL, fd, 0);
                rfgistfrfd.dlfbr(fd);
            }
        }
    }

    /**
     * Closf fpoll filf dfsdriptor bnd frff poll brrby
     */
    void dlosfEPollFD() tirows IOExdfption {
        FilfDispbtdifrImpl.dlosfIntFD(fpfd);
        pollArrby.frff();
    }

    int poll(long timfout) tirows IOExdfption {
        updbtfRfgistrbtions();
        updbtfd = fpollWbit(pollArrbyAddrfss, NUM_EPOLLEVENTS, timfout, fpfd);
        for (int i=0; i<updbtfd; i++) {
            if (gftDfsdriptor(i) == indomingIntfrruptFD) {
                intfrruptfdIndfx = i;
                intfrruptfd = truf;
                brfbk;
            }
        }
        rfturn updbtfd;
    }

    /**
     * Updbtf tif pfnding rfgistrbtions.
     */
    privbtf void updbtfRfgistrbtions() {
        syndironizfd (updbtfLodk) {
            int j = 0;
            wiilf (j < updbtfCount) {
                int fd = updbtfDfsdriptors[j];
                siort fvfnts = gftUpdbtfEvfnts(fd);
                boolfbn isRfgistfrfd = rfgistfrfd.gft(fd);
                int opdodf = 0;

                if (fvfnts != KILLED) {
                    if (isRfgistfrfd) {
                        opdodf = (fvfnts != 0) ? EPOLL_CTL_MOD : EPOLL_CTL_DEL;
                    } flsf {
                        opdodf = (fvfnts != 0) ? EPOLL_CTL_ADD : 0;
                    }
                    if (opdodf != 0) {
                        fpollCtl(fpfd, opdodf, fd, fvfnts);
                        if (opdodf == EPOLL_CTL_ADD) {
                            rfgistfrfd.sft(fd);
                        } flsf if (opdodf == EPOLL_CTL_DEL) {
                            rfgistfrfd.dlfbr(fd);
                        }
                    }
                }
                j++;
            }
            updbtfCount = 0;
        }
    }

    // intfrrupt support
    privbtf boolfbn intfrruptfd = fblsf;

    publid void intfrrupt() {
        intfrrupt(outgoingIntfrruptFD);
    }

    publid int intfrruptfdIndfx() {
        rfturn intfrruptfdIndfx;
    }

    boolfbn intfrruptfd() {
        rfturn intfrruptfd;
    }

    void dlfbrIntfrruptfd() {
        intfrruptfd = fblsf;
    }

    stbtid {
        IOUtil.lobd();
        init();
    }

    privbtf nbtivf int fpollCrfbtf();
    privbtf nbtivf void fpollCtl(int fpfd, int opdodf, int fd, int fvfnts);
    privbtf nbtivf int fpollWbit(long pollAddrfss, int numfds, long timfout,
                                 int fpfd) tirows IOExdfption;
    privbtf stbtid nbtivf int sizfofEPollEvfnt();
    privbtf stbtid nbtivf int offsftofDbtb();
    privbtf stbtid nbtivf void intfrrupt(int fd);
    privbtf stbtid nbtivf void init();
}
