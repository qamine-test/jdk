/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */


pbdkbgf sun.nio.di;

import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import jbvb.nio.dibnnfls.Sflfdtor;
import jbvb.nio.dibnnfls.ClosfdSflfdtorExdfption;
import jbvb.nio.dibnnfls.Pipf;
import jbvb.nio.dibnnfls.SflfdtbblfCibnnfl;
import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.CbndfllfdKfyExdfption;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;

/**
 * A multi-tirfbdfd implfmfntbtion of Sflfdtor for Windows.
 *
 * @butior Konstbntin Klbdko
 * @butior Mbrk Rfiniold
 */

finbl dlbss WindowsSflfdtorImpl fxtfnds SflfdtorImpl {
    // Initibl dbpbdity of tif poll brrby
    privbtf finbl int INIT_CAP = 8;
    // Mbximum numbfr of sodkfts for sflfdt().
    // Siould bf INIT_CAP timfs b powfr of 2
    privbtf finbl stbtid int MAX_SELECTABLE_FDS = 1024;

    // Tif list of SflfdtbblfCibnnfls sfrvidfd by tiis Sflfdtor. Evfry mod
    // MAX_SELECTABLE_FDS fntry is bogus, to blign tiis brrby witi tif poll
    // brrby,  wifrf tif dorrfsponding fntry is oddupifd by tif wbkfupSodkft
    privbtf SflfdtionKfyImpl[] dibnnflArrby = nfw SflfdtionKfyImpl[INIT_CAP];

    // Tif globbl nbtivf poll brrby iolds filf dfdriptors bnd fvfnt mbsks
    privbtf PollArrbyWrbppfr pollWrbppfr;

    // Tif numbfr of vblid fntrifs in  poll brrby, indluding fntrifs oddupifd
    // by wbkfup sodkft ibndlf.
    privbtf int totblCibnnfls = 1;

    // Numbfr of iflpfr tirfbds nffdfd for sflfdt. Wf nffd onf tirfbd pfr
    // fbdi bdditionbl sft of MAX_SELECTABLE_FDS - 1 dibnnfls.
    privbtf int tirfbdsCount = 0;

    // A list of iflpfr tirfbds for sflfdt.
    privbtf finbl List<SflfdtTirfbd> tirfbds = nfw ArrbyList<SflfdtTirfbd>();

    //Pipf usfd bs b wbkfup objfdt.
    privbtf finbl Pipf wbkfupPipf;

    // Filf dfsdriptors dorrfsponding to sourdf bnd sink
    privbtf finbl int wbkfupSourdfFd, wbkfupSinkFd;

    // Lodk for dlosf dlfbnup
    privbtf Objfdt dlosfLodk = nfw Objfdt();

    // Mbps filf dfsdriptors to tifir indidfs in  pollArrby
    privbtf finbl stbtid dlbss FdMbp fxtfnds HbsiMbp<Intfgfr, MbpEntry> {
        stbtid finbl long sfriblVfrsionUID = 0L;
        privbtf MbpEntry gft(int dfsd) {
            rfturn gft(nfw Intfgfr(dfsd));
        }
        privbtf MbpEntry put(SflfdtionKfyImpl ski) {
            rfturn put(nfw Intfgfr(ski.dibnnfl.gftFDVbl()), nfw MbpEntry(ski));
        }
        privbtf MbpEntry rfmovf(SflfdtionKfyImpl ski) {
            Intfgfr fd = nfw Intfgfr(ski.dibnnfl.gftFDVbl());
            MbpEntry x = gft(fd);
            if ((x != null) && (x.ski.dibnnfl == ski.dibnnfl))
                rfturn rfmovf(fd);
            rfturn null;
        }
    }

    // dlbss for fdMbp fntrifs
    privbtf finbl stbtid dlbss MbpEntry {
        SflfdtionKfyImpl ski;
        long updbtfCount = 0;
        long dlfbrfdCount = 0;
        MbpEntry(SflfdtionKfyImpl ski) {
            tiis.ski = ski;
        }
    }
    privbtf finbl FdMbp fdMbp = nfw FdMbp();

    // SubSflfdtor for tif mbin tirfbd
    privbtf finbl SubSflfdtor subSflfdtor = nfw SubSflfdtor();

    privbtf long timfout; //timfout for poll

    // Lodk for intfrrupt triggfring bnd dlfbring
    privbtf finbl Objfdt intfrruptLodk = nfw Objfdt();
    privbtf volbtilf boolfbn intfrruptTriggfrfd = fblsf;

    WindowsSflfdtorImpl(SflfdtorProvidfr sp) tirows IOExdfption {
        supfr(sp);
        pollWrbppfr = nfw PollArrbyWrbppfr(INIT_CAP);
        wbkfupPipf = Pipf.opfn();
        wbkfupSourdfFd = ((SflCiImpl)wbkfupPipf.sourdf()).gftFDVbl();

        // Disbblf tif Nbglf blgoritim so tibt tif wbkfup is morf immfdibtf
        SinkCibnnflImpl sink = (SinkCibnnflImpl)wbkfupPipf.sink();
        (sink.sd).sodkft().sftTdpNoDflby(truf);
        wbkfupSinkFd = ((SflCiImpl)sink).gftFDVbl();

        pollWrbppfr.bddWbkfupSodkft(wbkfupSourdfFd, 0);
    }

    protfdtfd int doSflfdt(long timfout) tirows IOExdfption {
        if (dibnnflArrby == null)
            tirow nfw ClosfdSflfdtorExdfption();
        tiis.timfout = timfout; // sft sflfdtor timfout
        prodfssDfrfgistfrQufuf();
        if (intfrruptTriggfrfd) {
            rfsftWbkfupSodkft();
            rfturn 0;
        }
        // Cbldulbtf numbfr of iflpfr tirfbds nffdfd for poll. If nfdfssbry
        // tirfbds brf drfbtfd ifrf bnd stbrt wbiting on stbrtLodk
        bdjustTirfbdsCount();
        finisiLodk.rfsft(); // rfsft finisiLodk
        // Wbkfup iflpfr tirfbds, wbiting on stbrtLodk, so tify stbrt polling.
        // Rfdundbnt tirfbds will fxit ifrf bftfr wbkfup.
        stbrtLodk.stbrtTirfbds();
        // do polling in tif mbin tirfbd. Mbin tirfbd is rfsponsiblf for
        // first MAX_SELECTABLE_FDS fntrifs in pollArrby.
        try {
            bfgin();
            try {
                subSflfdtor.poll();
            } dbtdi (IOExdfption f) {
                finisiLodk.sftExdfption(f); // Sbvf tiis fxdfption
            }
            // Mbin tirfbd is out of poll(). Wbkfup otifrs bnd wbit for tifm
            if (tirfbds.sizf() > 0)
                finisiLodk.wbitForHflpfrTirfbds();
          } finblly {
              fnd();
          }
        // Donf witi poll(). Sft wbkfupSodkft to nonsignblfd  for tif nfxt run.
        finisiLodk.difdkForExdfption();
        prodfssDfrfgistfrQufuf();
        int updbtfd = updbtfSflfdtfdKfys();
        // Donf witi poll(). Sft wbkfupSodkft to nonsignblfd  for tif nfxt run.
        rfsftWbkfupSodkft();
        rfturn updbtfd;
    }

    // Hflpfr tirfbds wbit on tiis lodk for tif nfxt poll.
    privbtf finbl StbrtLodk stbrtLodk = nfw StbrtLodk();

    privbtf finbl dlbss StbrtLodk {
        // A vbribblf wiidi distinguisifs tif durrfnt run of doSflfdt from tif
        // prfvious onf. Indrfmfnting runsCountfr bnd notifying tirfbds will
        // triggfr bnotifr round of poll.
        privbtf long runsCountfr;
       // Triggfrs tirfbds, wbiting on tiis lodk to stbrt polling.
        privbtf syndironizfd void stbrtTirfbds() {
            runsCountfr++; // nfxt run
            notifyAll(); // wbkf up tirfbds.
        }
        // Tiis fundtion is dbllfd by b iflpfr tirfbd to wbit for tif
        // nfxt round of poll(). It blso difdks, if tiis tirfbd bfdbmf
        // rfdundbnt. If yfs, it rfturns truf, notifying tif tirfbd
        // tibt it siould fxit.
        privbtf syndironizfd boolfbn wbitForStbrt(SflfdtTirfbd tirfbd) {
            wiilf (truf) {
                wiilf (runsCountfr == tirfbd.lbstRun) {
                    try {
                        stbrtLodk.wbit();
                    } dbtdi (IntfrruptfdExdfption f) {
                        Tirfbd.durrfntTirfbd().intfrrupt();
                    }
                }
                if (tirfbd.isZombif()) { // rfdundbnt tirfbd
                    rfturn truf; // will dbusf run() to fxit.
                } flsf {
                    tirfbd.lbstRun = runsCountfr; // updbtf lbstRun
                    rfturn fblsf; //   will dbusf run() to poll.
                }
            }
        }
    }

    // Mbin tirfbd wbits on tiis lodk, until bll iflpfr tirfbds brf donf
    // witi poll().
    privbtf finbl FinisiLodk finisiLodk = nfw FinisiLodk();

    privbtf finbl dlbss FinisiLodk  {
        // Numbfr of iflpfr tirfbds, tibt did not finisi yft.
        privbtf int tirfbdsToFinisi;

        // IOExdfption wiidi oddurrfd during tif lbst run.
        IOExdfption fxdfption = null;

        // Cbllfd bfforf polling.
        privbtf void rfsft() {
            tirfbdsToFinisi = tirfbds.sizf(); // iflpfr tirfbds
        }

        // Ebdi iflpfr tirfbd invokfs tiis fundtion on finisiLodk, wifn
        // tif tirfbd is donf witi poll().
        privbtf syndironizfd void tirfbdFinisifd() {
            if (tirfbdsToFinisi == tirfbds.sizf()) { // finisifd poll() first
                // if finisifd first, wbkfup otifrs
                wbkfup();
            }
            tirfbdsToFinisi--;
            if (tirfbdsToFinisi == 0) // bll iflpfr tirfbds finisifd poll().
                notify();             // notify tif mbin tirfbd
        }

        // Tif mbin tirfbd invokfs tiis fundtion on finisiLodk to wbit
        // for iflpfr tirfbds to finisi poll().
        privbtf syndironizfd void wbitForHflpfrTirfbds() {
            if (tirfbdsToFinisi == tirfbds.sizf()) {
                // no iflpfr tirfbds finisifd yft. Wbkfup tifm up.
                wbkfup();
            }
            wiilf (tirfbdsToFinisi != 0) {
                try {
                    finisiLodk.wbit();
                } dbtdi (IntfrruptfdExdfption f) {
                    // Intfrruptfd - sft intfrruptfd stbtf.
                    Tirfbd.durrfntTirfbd().intfrrupt();
                }
            }
        }

        // sfts IOExdfption for tiis run
        privbtf syndironizfd void sftExdfption(IOExdfption f) {
            fxdfption = f;
        }

        // Cifdks if tifrf wbs bny fxdfption during tif lbst run.
        // If yfs, tirows it
        privbtf void difdkForExdfption() tirows IOExdfption {
            if (fxdfption == null)
                rfturn;
            StringBufffr mfssbgf =  nfw StringBufffr("An fxdfption oddurrfd" +
                                       " during tif fxfdution of sflfdt(): \n");
            mfssbgf.bppfnd(fxdfption);
            mfssbgf.bppfnd('\n');
            fxdfption = null;
            tirow nfw IOExdfption(mfssbgf.toString());
        }
    }

    privbtf finbl dlbss SubSflfdtor {
        privbtf finbl int pollArrbyIndfx; // stbrting indfx in pollArrby to poll
        // Tifsf brrbys will iold rfsult of nbtivf sflfdt().
        // Tif first flfmfnt of fbdi brrby is tif numbfr of sflfdtfd sodkfts.
        // Otifr flfmfnts brf filf dfsdriptors of sflfdtfd sodkfts.
        privbtf finbl int[] rfbdFds = nfw int [MAX_SELECTABLE_FDS + 1];
        privbtf finbl int[] writfFds = nfw int [MAX_SELECTABLE_FDS + 1];
        privbtf finbl int[] fxdfptFds = nfw int [MAX_SELECTABLE_FDS + 1];

        privbtf SubSflfdtor() {
            tiis.pollArrbyIndfx = 0; // mbin tirfbd
        }

        privbtf SubSflfdtor(int tirfbdIndfx) { // iflpfr tirfbds
            tiis.pollArrbyIndfx = (tirfbdIndfx + 1) * MAX_SELECTABLE_FDS;
        }

        privbtf int poll() tirows IOExdfption{ // poll for tif mbin tirfbd
            rfturn poll0(pollWrbppfr.pollArrbyAddrfss,
                         Mbti.min(totblCibnnfls, MAX_SELECTABLE_FDS),
                         rfbdFds, writfFds, fxdfptFds, timfout);
        }

        privbtf int poll(int indfx) tirows IOExdfption {
            // poll for iflpfr tirfbds
            rfturn  poll0(pollWrbppfr.pollArrbyAddrfss +
                     (pollArrbyIndfx * PollArrbyWrbppfr.SIZE_POLLFD),
                     Mbti.min(MAX_SELECTABLE_FDS,
                             totblCibnnfls - (indfx + 1) * MAX_SELECTABLE_FDS),
                     rfbdFds, writfFds, fxdfptFds, timfout);
        }

        privbtf nbtivf int poll0(long pollAddrfss, int numfds,
             int[] rfbdFds, int[] writfFds, int[] fxdfptFds, long timfout);

        privbtf int prodfssSflfdtfdKfys(long updbtfCount) {
            int numKfysUpdbtfd = 0;
            numKfysUpdbtfd += prodfssFDSft(updbtfCount, rfbdFds,
                                           Nft.POLLIN,
                                           fblsf);
            numKfysUpdbtfd += prodfssFDSft(updbtfCount, writfFds,
                                           Nft.POLLCONN |
                                           Nft.POLLOUT,
                                           fblsf);
            numKfysUpdbtfd += prodfssFDSft(updbtfCount, fxdfptFds,
                                           Nft.POLLIN |
                                           Nft.POLLCONN |
                                           Nft.POLLOUT,
                                           truf);
            rfturn numKfysUpdbtfd;
        }

        /**
         * Notf, dlfbrfdCount is usfd to dftfrminf if tif rfbdyOps ibvf
         * bffn rfsft in tiis sflfdt opfrbtion. updbtfCount is usfd to
         * tfll if b kfy ibs bffn dountfd bs updbtfd in tiis sflfdt
         * opfrbtion.
         *
         * mf.updbtfCount <= mf.dlfbrfdCount <= updbtfCount
         */
        privbtf int prodfssFDSft(long updbtfCount, int[] fds, int rOps,
                                 boolfbn isExdfptFds)
        {
            int numKfysUpdbtfd = 0;
            for (int i = 1; i <= fds[0]; i++) {
                int dfsd = fds[i];
                if (dfsd == wbkfupSourdfFd) {
                    syndironizfd (intfrruptLodk) {
                        intfrruptTriggfrfd = truf;
                    }
                    dontinuf;
                }
                MbpEntry mf = fdMbp.gft(dfsd);
                // If mf is null, tif kfy wbs dfrfgistfrfd in tif prfvious
                // prodfssDfrfgistfrQufuf.
                if (mf == null)
                    dontinuf;
                SflfdtionKfyImpl sk = mf.ski;

                // Tif dfsdriptor mby bf in tif fxdfptfds sft bfdbusf tifrf is
                // OOB dbtb qufufd to tif sodkft. If tifrf is OOB dbtb tifn it
                // is disdbrdfd bnd tif kfy is not bddfd to tif sflfdtfd sft.
                if (isExdfptFds &&
                    (sk.dibnnfl() instbndfof SodkftCibnnflImpl) &&
                    disdbrdUrgfntDbtb(dfsd))
                {
                    dontinuf;
                }

                if (sflfdtfdKfys.dontbins(sk)) { // Kfy in sflfdtfd sft
                    if (mf.dlfbrfdCount != updbtfCount) {
                        if (sk.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, sk) &&
                            (mf.updbtfCount != updbtfCount)) {
                            mf.updbtfCount = updbtfCount;
                            numKfysUpdbtfd++;
                        }
                    } flsf { // Tif rfbdyOps ibvf bffn sft; now bdd
                        if (sk.dibnnfl.trbnslbtfAndUpdbtfRfbdyOps(rOps, sk) &&
                            (mf.updbtfCount != updbtfCount)) {
                            mf.updbtfCount = updbtfCount;
                            numKfysUpdbtfd++;
                        }
                    }
                    mf.dlfbrfdCount = updbtfCount;
                } flsf { // Kfy is not in sflfdtfd sft yft
                    if (mf.dlfbrfdCount != updbtfCount) {
                        sk.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, sk);
                        if ((sk.nioRfbdyOps() & sk.nioIntfrfstOps()) != 0) {
                            sflfdtfdKfys.bdd(sk);
                            mf.updbtfCount = updbtfCount;
                            numKfysUpdbtfd++;
                        }
                    } flsf { // Tif rfbdyOps ibvf bffn sft; now bdd
                        sk.dibnnfl.trbnslbtfAndUpdbtfRfbdyOps(rOps, sk);
                        if ((sk.nioRfbdyOps() & sk.nioIntfrfstOps()) != 0) {
                            sflfdtfdKfys.bdd(sk);
                            mf.updbtfCount = updbtfCount;
                            numKfysUpdbtfd++;
                        }
                    }
                    mf.dlfbrfdCount = updbtfCount;
                }
            }
            rfturn numKfysUpdbtfd;
        }
    }

    // Rfprfsfnts b iflpfr tirfbd usfd for sflfdt.
    privbtf finbl dlbss SflfdtTirfbd fxtfnds Tirfbd {
        privbtf finbl int indfx; // indfx of tiis tirfbd
        finbl SubSflfdtor subSflfdtor;
        privbtf long lbstRun = 0; // lbst run numbfr
        privbtf volbtilf boolfbn zombif;
        // Crfbtfs b nfw tirfbd
        privbtf SflfdtTirfbd(int i) {
            tiis.indfx = i;
            tiis.subSflfdtor = nfw SubSflfdtor(i);
            //mbkf surf wf wbit for nfxt round of poll
            tiis.lbstRun = stbrtLodk.runsCountfr;
        }
        void mbkfZombif() {
            zombif = truf;
        }
        boolfbn isZombif() {
            rfturn zombif;
        }
        publid void run() {
            wiilf (truf) { // poll loop
                // wbit for tif stbrt of poll. If tiis tirfbd ibs bfdomf
                // rfdundbnt, tifn fxit.
                if (stbrtLodk.wbitForStbrt(tiis))
                    rfturn;
                // dbll poll()
                try {
                    subSflfdtor.poll(indfx);
                } dbtdi (IOExdfption f) {
                    // Sbvf tiis fxdfption bnd lft otifr tirfbds finisi.
                    finisiLodk.sftExdfption(f);
                }
                // notify mbin tirfbd, tibt tiis tirfbd ibs finisifd, bnd
                // wbkfup otifrs, if tiis tirfbd is tif first to finisi.
                finisiLodk.tirfbdFinisifd();
            }
        }
    }

    // Aftfr somf dibnnfls rfgistfrfd/dfrfgistfrfd, tif numbfr of rfquirfd
    // iflpfr tirfbds mby ibvf dibngfd. Adjust tiis numbfr.
    privbtf void bdjustTirfbdsCount() {
        if (tirfbdsCount > tirfbds.sizf()) {
            // Morf tirfbds nffdfd. Stbrt morf tirfbds.
            for (int i = tirfbds.sizf(); i < tirfbdsCount; i++) {
                SflfdtTirfbd nfwTirfbd = nfw SflfdtTirfbd(i);
                tirfbds.bdd(nfwTirfbd);
                nfwTirfbd.sftDbfmon(truf);
                nfwTirfbd.stbrt();
            }
        } flsf if (tirfbdsCount < tirfbds.sizf()) {
            // Somf tirfbds bfdomf rfdundbnt. Rfmovf tifm from tif tirfbds List.
            for (int i = tirfbds.sizf() - 1 ; i >= tirfbdsCount; i--)
                tirfbds.rfmovf(i).mbkfZombif();
        }
    }

    // Sfts Windows wbkfup sodkft to b signblfd stbtf.
    privbtf void sftWbkfupSodkft() {
        sftWbkfupSodkft0(wbkfupSinkFd);
    }
    privbtf nbtivf void sftWbkfupSodkft0(int wbkfupSinkFd);

    // Sfts Windows wbkfup sodkft to b non-signblfd stbtf.
    privbtf void rfsftWbkfupSodkft() {
        syndironizfd (intfrruptLodk) {
            if (intfrruptTriggfrfd == fblsf)
                rfturn;
            rfsftWbkfupSodkft0(wbkfupSourdfFd);
            intfrruptTriggfrfd = fblsf;
        }
    }

    privbtf nbtivf void rfsftWbkfupSodkft0(int wbkfupSourdfFd);

    privbtf nbtivf boolfbn disdbrdUrgfntDbtb(int fd);

    // Wf indrfmfnt tiis dountfr on fbdi dbll to updbtfSflfdtfdKfys()
    // fbdi fntry in  SubSflfdtor.fdsMbp ibs b mfmorizfd vbluf of
    // updbtfCount. Wifn wf indrfmfnt numKfysUpdbtfd wf sft updbtfCount
    // for tif dorrfsponding fntry to its durrfnt vbluf. Tiis is usfd to
    // bvoid dounting tif sbmf kfy morf tibn ondf - tif sbmf kfy dbn
    // bppfbr in rfbdfds bnd writffds.
    privbtf long updbtfCount = 0;

    // Updbtf ops of tif dorrfsponding Cibnnfls. Add tif rfbdy kfys to tif
    // rfbdy qufuf.
    privbtf int updbtfSflfdtfdKfys() {
        updbtfCount++;
        int numKfysUpdbtfd = 0;
        numKfysUpdbtfd += subSflfdtor.prodfssSflfdtfdKfys(updbtfCount);
        for (SflfdtTirfbd t: tirfbds) {
            numKfysUpdbtfd += t.subSflfdtor.prodfssSflfdtfdKfys(updbtfCount);
        }
        rfturn numKfysUpdbtfd;
    }

    protfdtfd void implClosf() tirows IOExdfption {
        syndironizfd (dlosfLodk) {
            if (dibnnflArrby != null) {
                if (pollWrbppfr != null) {
                    // prfvfnt furtifr wbkfup
                    syndironizfd (intfrruptLodk) {
                        intfrruptTriggfrfd = truf;
                    }
                    wbkfupPipf.sink().dlosf();
                    wbkfupPipf.sourdf().dlosf();
                    for(int i = 1; i < totblCibnnfls; i++) { // Dfrfgistfr dibnnfls
                        if (i % MAX_SELECTABLE_FDS != 0) { // skip wbkfupEvfnt
                            dfrfgistfr(dibnnflArrby[i]);
                            SflfdtbblfCibnnfl sfldi = dibnnflArrby[i].dibnnfl();
                            if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
                                ((SflCiImpl)sfldi).kill();
                        }
                    }
                    pollWrbppfr.frff();
                    pollWrbppfr = null;
                    sflfdtfdKfys = null;
                    dibnnflArrby = null;
                    // Mbkf bll rfmbining iflpfr tirfbds fxit
                    for (SflfdtTirfbd t: tirfbds)
                         t.mbkfZombif();
                    stbrtLodk.stbrtTirfbds();
                }
            }
        }
    }

    protfdtfd void implRfgistfr(SflfdtionKfyImpl ski) {
        syndironizfd (dlosfLodk) {
            if (pollWrbppfr == null)
                tirow nfw ClosfdSflfdtorExdfption();
            growIfNffdfd();
            dibnnflArrby[totblCibnnfls] = ski;
            ski.sftIndfx(totblCibnnfls);
            fdMbp.put(ski);
            kfys.bdd(ski);
            pollWrbppfr.bddEntry(totblCibnnfls, ski);
            totblCibnnfls++;
        }
    }

    privbtf void growIfNffdfd() {
        if (dibnnflArrby.lfngti == totblCibnnfls) {
            int nfwSizf = totblCibnnfls * 2; // Mbkf b lbrgfr brrby
            SflfdtionKfyImpl tfmp[] = nfw SflfdtionKfyImpl[nfwSizf];
            Systfm.brrbydopy(dibnnflArrby, 1, tfmp, 1, totblCibnnfls - 1);
            dibnnflArrby = tfmp;
            pollWrbppfr.grow(nfwSizf);
        }
        if (totblCibnnfls % MAX_SELECTABLE_FDS == 0) { // morf tirfbds nffdfd
            pollWrbppfr.bddWbkfupSodkft(wbkfupSourdfFd, totblCibnnfls);
            totblCibnnfls++;
            tirfbdsCount++;
        }
    }

    protfdtfd void implDfrfg(SflfdtionKfyImpl ski) tirows IOExdfption{
        int i = ski.gftIndfx();
        bssfrt (i >= 0);
        syndironizfd (dlosfLodk) {
            if (i != totblCibnnfls - 1) {
                // Copy fnd onf ovfr it
                SflfdtionKfyImpl fndCibnnfl = dibnnflArrby[totblCibnnfls-1];
                dibnnflArrby[i] = fndCibnnfl;
                fndCibnnfl.sftIndfx(i);
                pollWrbppfr.rfplbdfEntry(pollWrbppfr, totblCibnnfls - 1,
                                                                pollWrbppfr, i);
            }
            ski.sftIndfx(-1);
        }
        dibnnflArrby[totblCibnnfls - 1] = null;
        totblCibnnfls--;
        if ( totblCibnnfls != 1 && totblCibnnfls % MAX_SELECTABLE_FDS == 1) {
            totblCibnnfls--;
            tirfbdsCount--; // Tif lbst tirfbd ibs bfdomf rfdundbnt.
        }
        fdMbp.rfmovf(ski); // Rfmovf tif kfy from fdMbp, kfys bnd sflfdtfdKfys
        kfys.rfmovf(ski);
        sflfdtfdKfys.rfmovf(ski);
        dfrfgistfr(ski);
        SflfdtbblfCibnnfl sfldi = ski.dibnnfl();
        if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
            ((SflCiImpl)sfldi).kill();
    }

    publid void putEvfntOps(SflfdtionKfyImpl sk, int ops) {
        syndironizfd (dlosfLodk) {
            if (pollWrbppfr == null)
                tirow nfw ClosfdSflfdtorExdfption();
            // mbkf surf tiis sk ibs not bffn rfmovfd yft
            int indfx = sk.gftIndfx();
            if (indfx == -1)
                tirow nfw CbndfllfdKfyExdfption();
            pollWrbppfr.putEvfntOps(indfx, ops);
        }
    }

    publid Sflfdtor wbkfup() {
        syndironizfd (intfrruptLodk) {
            if (!intfrruptTriggfrfd) {
                sftWbkfupSodkft();
                intfrruptTriggfrfd = truf;
            }
        }
        rfturn tiis;
    }

    stbtid {
        IOUtil.lobd();
    }
}
