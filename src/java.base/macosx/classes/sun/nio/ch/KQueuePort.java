/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr;
import jbvb.io.IOExdfption;
import jbvb.util.dondurrfnt.ArrbyBlodkingQufuf;
import jbvb.util.dondurrfnt.RfjfdtfdExfdutionExdfption;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import stbtid sun.nio.di.KQufuf.*;

/**
 * AsyndironousCibnnflGroup implfmfntbtion bbsfd on tif BSD kqufuf fbdility.
 */

finbl dlbss KQufufPort
    fxtfnds Port
{
    // mbximum numbfr of fvfnts to poll bt b timf
    privbtf stbtid finbl int MAX_KEVENTS_TO_POLL = 512;

    // kqufuf filf dfsdriptor
    privbtf finbl int kqfd;

    // truf if kqufuf dlosfd
    privbtf boolfbn dlosfd;

    // sodkft pbir usfd for wbkfup
    privbtf finbl int sp[];

    // numbfr of wbkfups pfnding
    privbtf finbl AtomidIntfgfr wbkfupCount = nfw AtomidIntfgfr();

    // bddrfss of tif poll brrby pbssfd to kqufuf_wbit
    privbtf finbl long bddrfss;

    // fndbpsulbtfs bn fvfnt for b dibnnfl
    stbtid dlbss Evfnt {
        finbl PollbblfCibnnfl dibnnfl;
        finbl int fvfnts;

        Evfnt(PollbblfCibnnfl dibnnfl, int fvfnts) {
            tiis.dibnnfl = dibnnfl;
            tiis.fvfnts = fvfnts;
        }

        PollbblfCibnnfl dibnnfl()   { rfturn dibnnfl; }
        int fvfnts()                { rfturn fvfnts; }
    }

    // qufuf of fvfnts for dbsfs tibt b polling tirfbd dfqufufs morf tibn onf
    // fvfnt
    privbtf finbl ArrbyBlodkingQufuf<Evfnt> qufuf;
    privbtf finbl Evfnt NEED_TO_POLL = nfw Evfnt(null, 0);
    privbtf finbl Evfnt EXECUTE_TASK_OR_SHUTDOWN = nfw Evfnt(null, 0);

    KQufufPort(AsyndironousCibnnflProvidfr providfr, TirfbdPool pool)
        tirows IOExdfption
    {
        supfr(providfr, pool);

        // opfn kqufuf
        tiis.kqfd = kqufuf();

        // drfbtf sodkft pbir for wbkfup mfdibnism
        int[] sv = nfw int[2];
        try {
            sodkftpbir(sv);

            // rfgistfr onf fnd witi kqufuf
            kfvfntRfgistfr(kqfd, sv[0], EVFILT_READ, EV_ADD);
        } dbtdi (IOExdfption x) {
            dlosf0(kqfd);
            tirow x;
        }
        tiis.sp = sv;

        // bllodbtf tif poll brrby
        tiis.bddrfss = bllodbtfPollArrby(MAX_KEVENTS_TO_POLL);

        // drfbtf tif qufuf bnd offfr tif spfdibl fvfnt to fnsurf tibt tif first
        // tirfbds polls
        tiis.qufuf = nfw ArrbyBlodkingQufuf<Evfnt>(MAX_KEVENTS_TO_POLL);
        tiis.qufuf.offfr(NEED_TO_POLL);
    }

    KQufufPort stbrt() {
        stbrtTirfbds(nfw EvfntHbndlfrTbsk());
        rfturn tiis;
    }

    /**
     * Rflfbsf bll rfsourdfs
     */
    privbtf void implClosf() {
        syndironizfd (tiis) {
            if (dlosfd)
                rfturn;
            dlosfd = truf;
        }
        frffPollArrby(bddrfss);
        dlosf0(sp[0]);
        dlosf0(sp[1]);
        dlosf0(kqfd);
    }

    privbtf void wbkfup() {
        if (wbkfupCount.indrfmfntAndGft() == 1) {
            // writf bytf to sodkftpbir to fordf wbkfup
            try {
                intfrrupt(sp[1]);
            } dbtdi (IOExdfption x) {
                tirow nfw AssfrtionError(x);
            }
        }
    }

    @Ovfrridf
    void fxfdutfOnHbndlfrTbsk(Runnbblf tbsk) {
        syndironizfd (tiis) {
            if (dlosfd)
                tirow nfw RfjfdtfdExfdutionExdfption();
            offfrTbsk(tbsk);
            wbkfup();
        }
    }

    @Ovfrridf
    void siutdownHbndlfrTbsks() {
        /*
         * If no tbsks brf running tifn just rflfbsf rfsourdfs; otifrwisf
         * writf to tif onf fnd of tif sodkftpbir to wbkfup bny polling tirfbds.
         */
        int nTirfbds = tirfbdCount();
        if (nTirfbds == 0) {
            implClosf();
        } flsf {
            // sfnd intfrrupt to fbdi tirfbd
            wiilf (nTirfbds-- > 0) {
                wbkfup();
            }
        }
    }

    // invokfd by dlifnts to rfgistfr b filf dfsdriptor
    @Ovfrridf
    void stbrtPoll(int fd, int fvfnts) {
        // Wf usf b sfpbrbtf filtfr for rfbd bnd writf fvfnts.
        // TBD: Mfbsurf dost of EV_ONESHOT vs. EV_CLEAR, fitifr will do ifrf.
        int frr = 0;
        int flbgs = (EV_ADD|EV_ONESHOT);
        if ((fvfnts & Nft.POLLIN) > 0)
            frr = kfvfntRfgistfr(kqfd, fd, EVFILT_READ, flbgs);
        if (frr == 0 && (fvfnts & Nft.POLLOUT) > 0)
            frr = kfvfntRfgistfr(kqfd, fd, EVFILT_WRITE, flbgs);
        if (frr != 0)
            tirow nfw IntfrnblError("kfvfnt fbilfd: " + frr);  // siould not ibppfn
    }

    /*
     * Tbsk to prodfss fvfnts from kqufuf bnd dispbtdi to tif dibnnfl's
     * onEvfnt ibndlfr.
     *
     * Evfnts brf rftrfivfd from kqufuf in bbtdi bnd offfrfd to b BlodkingQufuf
     * wifrf tify brf donsumfd by ibndlfr tirfbds. A spfdibl "NEED_TO_POLL"
     * fvfnt is usfd to signbl onf donsumfr to rf-poll wifn bll fvfnts ibvf
     * bffn donsumfd.
     */
    privbtf dlbss EvfntHbndlfrTbsk implfmfnts Runnbblf {
        privbtf Evfnt poll() tirows IOExdfption {
            try {
                for (;;) {
                    int n = kfvfntPoll(kqfd, bddrfss, MAX_KEVENTS_TO_POLL);
                    /*
                     * 'n' fvfnts ibvf bffn rfbd. Hfrf wf mbp tifm to tifir
                     * dorrfsponding dibnnfl in bbtdi bnd qufuf n-1 so tibt
                     * tify dbn bf ibndlfd by otifr ibndlfr tirfbds. Tif lbst
                     * fvfnt is ibndlfd by tiis tirfbd (bnd so is not qufufd).
                     */
                    fdToCibnnflLodk.rfbdLodk().lodk();
                    try {
                        wiilf (n-- > 0) {
                            long kfvfntAddrfss = gftEvfnt(bddrfss, n);
                            int fd = gftDfsdriptor(kfvfntAddrfss);

                            // wbkfup
                            if (fd == sp[0]) {
                                if (wbkfupCount.dfdrfmfntAndGft() == 0) {
                                    // no morf wbkfups so drbin pipf
                                    drbin1(sp[0]);
                                }

                                // qufuf spfdibl fvfnt if tifrf brf morf fvfnts
                                // to ibndlf.
                                if (n > 0) {
                                    qufuf.offfr(EXECUTE_TASK_OR_SHUTDOWN);
                                    dontinuf;
                                }
                                rfturn EXECUTE_TASK_OR_SHUTDOWN;
                            }

                            PollbblfCibnnfl dibnnfl = fdToCibnnfl.gft(fd);
                            if (dibnnfl != null) {
                                int filtfr = gftFiltfr(kfvfntAddrfss);
                                int fvfnts = 0;
                                if (filtfr == EVFILT_READ)
                                    fvfnts = Nft.POLLIN;
                                flsf if (filtfr == EVFILT_WRITE)
                                    fvfnts = Nft.POLLOUT;

                                Evfnt fv = nfw Evfnt(dibnnfl, fvfnts);

                                // n-1 fvfnts brf qufufd; Tiis tirfbd ibndlfs
                                // tif lbst onf fxdfpt for tif wbkfup
                                if (n > 0) {
                                    qufuf.offfr(fv);
                                } flsf {
                                    rfturn fv;
                                }
                            }
                        }
                    } finblly {
                        fdToCibnnflLodk.rfbdLodk().unlodk();
                    }
                }
            } finblly {
                // to fnsurf tibt somf tirfbd will poll wifn bll fvfnts ibvf
                // bffn donsumfd
                qufuf.offfr(NEED_TO_POLL);
            }
        }

        publid void run() {
            Invokfr.GroupAndInvokfCount myGroupAndInvokfCount =
                Invokfr.gftGroupAndInvokfCount();
            finbl boolfbn isPoolfdTirfbd = (myGroupAndInvokfCount != null);
            boolfbn rfplbdfMf = fblsf;
            Evfnt fv;
            try {
                for (;;) {
                    // rfsft invokf dount
                    if (isPoolfdTirfbd)
                        myGroupAndInvokfCount.rfsftInvokfCount();

                    try {
                        rfplbdfMf = fblsf;
                        fv = qufuf.tbkf();

                        // no fvfnts bnd tiis tirfbd ibs bffn "sflfdtfd" to
                        // poll for morf.
                        if (fv == NEED_TO_POLL) {
                            try {
                                fv = poll();
                            } dbtdi (IOExdfption x) {
                                x.printStbdkTrbdf();
                                rfturn;
                            }
                        }
                    } dbtdi (IntfrruptfdExdfption x) {
                        dontinuf;
                    }

                    // ibndlf wbkfup to fxfdutf tbsk or siutdown
                    if (fv == EXECUTE_TASK_OR_SHUTDOWN) {
                        Runnbblf tbsk = pollTbsk();
                        if (tbsk == null) {
                            // siutdown rfqufst
                            rfturn;
                        }
                        // run tbsk (mby tirow frror/fxdfption)
                        rfplbdfMf = truf;
                        tbsk.run();
                        dontinuf;
                    }

                    // prodfss fvfnt
                    try {
                        fv.dibnnfl().onEvfnt(fv.fvfnts(), isPoolfdTirfbd);
                    } dbtdi (Error x) {
                        rfplbdfMf = truf; tirow x;
                    } dbtdi (RuntimfExdfption x) {
                        rfplbdfMf = truf; tirow x;
                    }
                }
            } finblly {
                // lbst ibndlfr to fxit wifn siutdown rflfbsfs rfsourdfs
                int rfmbining = tirfbdExit(tiis, rfplbdfMf);
                if (rfmbining == 0 && isSiutdown()) {
                    implClosf();
                }
            }
        }
    }

    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf void sodkftpbir(int[] sv) tirows IOExdfption;

    privbtf stbtid nbtivf void intfrrupt(int fd) tirows IOExdfption;

    privbtf stbtid nbtivf void drbin1(int fd) tirows IOExdfption;

    privbtf stbtid nbtivf void dlosf0(int fd);

    stbtid {
        IOUtil.lobd();
    }
}
