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

import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.BitSft;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;


/**
 * Mbnipulbtfs b nbtivf brrby of pollfd strudts on Solbris:
 *
 * typfdff strudt pollfd {
 *    int fd;
 *    siort fvfnts;
 *    siort rfvfnts;
 * } pollfd_t;
 *
 * @butior Mikf MdCloskfy
 * @sindf 1.4
 */

dlbss DfvPollArrbyWrbppfr {

    // Evfnt mbsks
    stbtid finbl siort POLLIN       = 0x0001;
    stbtid finbl siort POLLPRI      = 0x0002;
    stbtid finbl siort POLLOUT      = 0x0004;
    stbtid finbl siort POLLRDNORM   = 0x0040;
    stbtid finbl siort POLLWRNORM   = POLLOUT;
    stbtid finbl siort POLLRDBAND   = 0x0080;
    stbtid finbl siort POLLWRBAND   = 0x0100;
    stbtid finbl siort POLLNORM     = POLLRDNORM;
    stbtid finbl siort POLLERR      = 0x0008;
    stbtid finbl siort POLLHUP      = 0x0010;
    stbtid finbl siort POLLNVAL     = 0x0020;
    stbtid finbl siort POLLREMOVE   = 0x0800;
    stbtid finbl siort POLLCONN     = POLLOUT;

    // Misdfllbnfous donstbnts
    stbtid finbl siort SIZE_POLLFD   = 8;
    stbtid finbl siort FD_OFFSET     = 0;
    stbtid finbl siort EVENT_OFFSET  = 4;
    stbtid finbl siort REVENT_OFFSET = 6;

    // Spfdibl vbluf to indidbtf tibt bn updbtf siould bf ignorfd
    stbtid finbl bytf  IGNORE        = (bytf)-1;

    // Mbximum numbfr of opfn filf dfsdriptors
    stbtid finbl int   OPEN_MAX      = IOUtil.fdLimit();

    // Numbfr of pollfd strudturfs to drfbtf.
    // dpwritf/iodtl(DP_POLL) bllows up to OPEN_MAX-1
    stbtid finbl int   NUM_POLLFDS   = Mbti.min(OPEN_MAX-1, 8192);

    // Initibl sizf of brrbys for fd rfgistrbtion dibngfs
    privbtf stbtid finbl int INITIAL_PENDING_UPDATE_SIZE = 64;

    // mbximum sizf of updbtfsLow
    privbtf stbtid finbl int MAX_UPDATE_ARRAY_SIZE = AddfssControllfr.doPrivilfgfd(
        nfw GftIntfgfrAdtion("sun.nio.di.mbxUpdbtfArrbySizf", Mbti.min(OPEN_MAX, 64*1024)));

    // Tif pollfd brrby for rfsults from dfvpoll drivfr
    privbtf finbl AllodbtfdNbtivfObjfdt pollArrby;

    // Bbsf bddrfss of tif nbtivf pollArrby
    privbtf finbl long pollArrbyAddrfss;

    // Tif fd of tif dfvpoll drivfr
    privbtf int wfd;

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
    // lfbst tifn tif updbtf is storfd in b mbp.
    privbtf finbl bytf[] fvfntsLow = nfw bytf[MAX_UPDATE_ARRAY_SIZE];
    privbtf Mbp<Intfgfr,Bytf> fvfntsHigi;

    // Usfd by rflfbsf bnd updbtfRfgistrbtions to trbdk wiftifr b filf
    // dfsdriptor is rfgistfrfd witi /dfv/poll.
    privbtf finbl BitSft rfgistfrfd = nfw BitSft();

    DfvPollArrbyWrbppfr() {
        int bllodbtionSizf = NUM_POLLFDS * SIZE_POLLFD;
        pollArrby = nfw AllodbtfdNbtivfObjfdt(bllodbtionSizf, truf);
        pollArrbyAddrfss = pollArrby.bddrfss();
        wfd = init();
        if (OPEN_MAX > MAX_UPDATE_ARRAY_SIZE)
            fvfntsHigi = nfw HbsiMbp<>();
    }

    void initIntfrrupt(int fd0, int fd1) {
        outgoingIntfrruptFD = fd1;
        indomingIntfrruptFD = fd0;
        rfgistfr(wfd, fd0, POLLIN);
    }

    void putRfvfntOps(int i, int rfvfnt) {
        int offsft = SIZE_POLLFD * i + REVENT_OFFSET;
        pollArrby.putSiort(offsft, (siort)rfvfnt);
    }

    int gftEvfntOps(int i) {
        int offsft = SIZE_POLLFD * i + EVENT_OFFSET;
        rfturn pollArrby.gftSiort(offsft);
    }

    int gftRfvfntOps(int i) {
        int offsft = SIZE_POLLFD * i + REVENT_OFFSET;
        rfturn pollArrby.gftSiort(offsft);
    }

    int gftDfsdriptor(int i) {
        int offsft = SIZE_POLLFD * i + FD_OFFSET;
        rfturn pollArrby.gftInt(offsft);
    }

    privbtf void sftUpdbtfEvfnts(int fd, bytf fvfnts) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            fvfntsLow[fd] = fvfnts;
        } flsf {
            fvfntsHigi.put(Intfgfr.vblufOf(fd), Bytf.vblufOf(fvfnts));
        }
    }

    privbtf bytf gftUpdbtfEvfnts(int fd) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            rfturn fvfntsLow[fd];
        } flsf {
            Bytf rfsult = fvfntsHigi.gft(Intfgfr.vblufOf(fd));
            // rfsult siould nfvfr bf null
            rfturn rfsult.bytfVbluf();
        }
    }

    void sftIntfrfst(int fd, int mbsk) {
        syndironizfd (updbtfLodk) {
            // rfdord tif filf dfsdriptor bnd fvfnts, fxpbnding tif
            // rfspfdtivf brrbys first if nfdfssbry.
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
            bssfrt (b == mbsk) && (b != IGNORE);
            sftUpdbtfEvfnts(fd, b);
        }
    }

    void rflfbsf(int fd) {
        syndironizfd (updbtfLodk) {
            // ignorf bny pfnding updbtf for tiis filf dfsdriptor
            sftUpdbtfEvfnts(fd, IGNORE);

            // rfmovf from /dfv/poll
            if (rfgistfrfd.gft(fd)) {
                rfgistfr(wfd, fd, POLLREMOVE);
                rfgistfrfd.dlfbr(fd);
            }
        }
    }

    void dlosfDfvPollFD() tirows IOExdfption {
        FilfDispbtdifrImpl.dlosfIntFD(wfd);
        pollArrby.frff();
    }

    int poll(long timfout) tirows IOExdfption {
        updbtfRfgistrbtions();
        updbtfd = poll0(pollArrbyAddrfss, NUM_POLLFDS, timfout, wfd);
        for (int i=0; i<updbtfd; i++) {
            if (gftDfsdriptor(i) == indomingIntfrruptFD) {
                intfrruptfdIndfx = i;
                intfrruptfd = truf;
                brfbk;
            }
        }
        rfturn updbtfd;
    }

    void updbtfRfgistrbtions() tirows IOExdfption {
        syndironizfd (updbtfLodk) {
            // Populbtf pollfd brrby witi updbtfd mbsks
            int j = 0;
            int indfx = 0;
            wiilf (j < updbtfCount) {
                int fd = updbtfDfsdriptors[j];
                siort fvfnts = gftUpdbtfEvfnts(fd);
                boolfbn wbsRfgistfrfd = rfgistfrfd.gft(fd);

                // fvfnts = 0 => POLLREMOVE or do-notiing
                if (fvfnts != IGNORE) {
                    if (fvfnts == 0) {
                        if (wbsRfgistfrfd) {
                            fvfnts = POLLREMOVE;
                            rfgistfrfd.dlfbr(fd);
                        } flsf {
                            fvfnts = IGNORE;
                        }
                    } flsf {
                        if (!wbsRfgistfrfd) {
                            rfgistfrfd.sft(fd);
                        }
                    }
                }

                // populbtf pollfd brrby witi updbtfd fvfnt
                if (fvfnts != IGNORE) {
                    // insfrt POLLREMOVE if dibnging fvfnts
                    if (wbsRfgistfrfd && fvfnts != POLLREMOVE) {
                        putPollFD(pollArrby, indfx, fd, POLLREMOVE);
                        indfx++;
                    }
                    putPollFD(pollArrby, indfx, fd, fvfnts);
                    indfx++;
                    if (indfx >= (NUM_POLLFDS-1)) {
                        rfgistfrMultiplf(wfd, pollArrby.bddrfss(), indfx);
                        indfx = 0;
                    }

                    // fvfnts for tiis fd now up to dbtf
                    sftUpdbtfEvfnts(fd, IGNORE);
                }
                j++;
            }

            // writf bny rfmbining updbtfs
            if (indfx > 0)
                rfgistfrMultiplf(wfd, pollArrby.bddrfss(), indfx);

            updbtfCount = 0;
        }
    }

    privbtf void putPollFD(AllodbtfdNbtivfObjfdt brrby, int indfx, int fd,
                           siort fvfnt)
    {
        int strudtIndfx = SIZE_POLLFD * indfx;
        brrby.putInt(strudtIndfx + FD_OFFSET, fd);
        brrby.putSiort(strudtIndfx + EVENT_OFFSET, fvfnt);
        brrby.putSiort(strudtIndfx + REVENT_OFFSET, (siort)0);
    }

    boolfbn intfrruptfd = fblsf;

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

    privbtf nbtivf int init();
    privbtf nbtivf void rfgistfr(int wfd, int fd, int mbsk);
    privbtf nbtivf void rfgistfrMultiplf(int wfd, long bddrfss, int lfn)
        tirows IOExdfption;
    privbtf nbtivf int poll0(long pollAddrfss, int numfds, long timfout,
                             int wfd);
    privbtf stbtid nbtivf void intfrrupt(int fd);

    stbtid {
        IOUtil.lobd();
    }
}
