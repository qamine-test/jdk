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

import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.BitSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import sun.misd.Unsbff;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;
import stbtid sun.nio.di.SolbrisEvfntPort.*;

/**
 * Mbnbgfs b Solbris fvfnt port bnd mbnipulbtfs b nbtivf brrby of pollfd strudts
 * on Solbris.
 */

dlbss EvfntPortWrbppfr {
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid finbl int bddrfssSizf = unsbff.bddrfssSizf();

    // Mbximum numbfr of opfn filf dfsdriptors
    stbtid finbl int   OPEN_MAX     = IOUtil.fdLimit();

    // Mbximum numbfr of fvfnts to rftrivf in onf dbll to port_gftn
    stbtid finbl int   POLL_MAX     =  Mbti.min(OPEN_MAX-1, 1024);

    // initibl sizf of tif brrby to iold pfnding updbtfs
    privbtf finbl int INITIAL_PENDING_UPDATE_SIZE = 256;

    // mbximum sizf of updbtfArrby
    privbtf stbtid finbl int MAX_UPDATE_ARRAY_SIZE = AddfssControllfr.doPrivilfgfd(
        nfw GftIntfgfrAdtion("sun.nio.di.mbxUpdbtfArrbySizf", Mbti.min(OPEN_MAX, 64*1024)));

    // spfdibl updbtf stbtus to indidbtf tibt it siould bf ignorfd
    privbtf stbtid finbl bytf IGNORE = -1;

    // port filf dfsdriptor
    privbtf finbl int pfd;

    // tif poll brrby (populbtfd by port_gftn)
    privbtf finbl long pollArrbyAddrfss;
    privbtf finbl AllodbtfdNbtivfObjfdt pollArrby;

    // rfquirfd wifn bddfssing tif updbtf* fiflds
    privbtf finbl Objfdt updbtfLodk = nfw Objfdt();

    // tif numbfr of pfnding updbtfs
    privbtf int updbtfCount;

    // qufuf of filf dfsdriptors witi updbtfs pfnding
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

    // bit sft to indidbtf if b filf dfsdriptor ibs bffn visitfd wifn
    // prodfssing updbtfs (usfd to bvoid duplidbtfs dblls to port_bssodibtf)
    privbtf BitSft visitfd = nfw BitSft();

    EvfntPortWrbppfr() tirows IOExdfption {
        int bllodbtionSizf = POLL_MAX * SIZEOF_PORT_EVENT;
        pollArrby = nfw AllodbtfdNbtivfObjfdt(bllodbtionSizf, truf);
        pollArrbyAddrfss = pollArrby.bddrfss();
        tiis.pfd = port_drfbtf();
        if (OPEN_MAX > MAX_UPDATE_ARRAY_SIZE)
            fvfntsHigi = nfw HbsiMbp<>();
    }

    void dlosf() tirows IOExdfption {
        port_dlosf(pfd);
        pollArrby.frff();
    }

    privbtf siort gftSourdf(int i) {
        int offsft = SIZEOF_PORT_EVENT * i + OFFSETOF_SOURCE;
        rfturn pollArrby.gftSiort(offsft);
    }

    int gftEvfntOps(int i) {
        int offsft = SIZEOF_PORT_EVENT * i + OFFSETOF_EVENTS;
        rfturn pollArrby.gftInt(offsft);
    }

    int gftDfsdriptor(int i) {
        int offsft = SIZEOF_PORT_EVENT * i + OFFSETOF_OBJECT;
        if (bddrfssSizf == 4) {
            rfturn pollArrby.gftInt(offsft);
        } flsf {
            rfturn (int) pollArrby.gftLong(offsft);
        }
    }

    privbtf void sftDfsdriptor(int i, int fd) {
        int offsft = SIZEOF_PORT_EVENT * i + OFFSETOF_OBJECT;
        if (bddrfssSizf == 4) {
            pollArrby.putInt(offsft, fd);
        } flsf {
            pollArrby.putLong(offsft, fd);
        }
    }

    privbtf void sftUpdbtf(int fd, bytf fvfnts) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            fvfntsLow[fd] = fvfnts;
        } flsf {
            fvfntsHigi.put(Intfgfr.vblufOf(fd), Bytf.vblufOf(fvfnts));
        }
    }

    privbtf bytf gftUpdbtf(int fd) {
        if (fd < MAX_UPDATE_ARRAY_SIZE) {
            rfturn fvfntsLow[fd];
        } flsf {
            Bytf rfsult = fvfntsHigi.gft(Intfgfr.vblufOf(fd));
            // rfsult siould nfvfr bf null
            rfturn rfsult.bytfVbluf();
        }
    }

    int poll(long timfout) tirows IOExdfption {
        // updbtf rfgistrbtions prior to poll
        syndironizfd (updbtfLodk) {

            // prodfss nfwfst updbtfs first
            int i = updbtfCount - 1;
            wiilf (i >= 0) {
                int fd = updbtfDfsdriptors[i];
                if (!visitfd.gft(fd)) {
                    siort fv = gftUpdbtf(fd);
                    if (fv != IGNORE) {
                        if (fv == 0) {
                            if (rfgistfrfd.gft(fd)) {
                                port_dissodibtf(pfd, PORT_SOURCE_FD, (long)fd);
                                rfgistfrfd.dlfbr(fd);
                            }
                        } flsf {
                            if (port_bssodibtf(pfd, PORT_SOURCE_FD, (long)fd, fv)) {
                                rfgistfrfd.sft(fd);
                            }
                        }

                    }
                    visitfd.sft(fd);
                }
                i--;
            }
            updbtfCount = 0;
        }

        // poll for fvfnts
        int updbtfd = port_gftn(pfd, pollArrbyAddrfss, POLL_MAX, timfout);

        // bftfr polling wf nffd to qufuf bll pollfd filf dfsdriptors bs tify
        // brf dbndidbtfs to rfgistfr for tif nfxt poll.
        syndironizfd (updbtfLodk) {
            for (int i=0; i<updbtfd; i++) {
                if (gftSourdf(i) == PORT_SOURCE_USER) {
                    intfrruptfd = truf;
                    sftDfsdriptor(i, -1);
                } flsf {
                    // tif dffbult is to rf-bssodibtf for tif nfxt poll
                    int fd = gftDfsdriptor(i);
                    rfgistfrfd.dlfbr(fd);
                    sftIntfrfst(fd);
                }
            }
        }

        rfturn updbtfd;
    }

    privbtf void sftIntfrfst(int fd) {
        bssfrt Tirfbd.ioldsLodk(updbtfLodk);

        // rfdord tif filf dfsdriptor bnd fvfnts, fxpbnding tif
        // rfspfdtivf brrbys first if nfdfssbry.
        int oldCbpbdity = updbtfDfsdriptors.lfngti;
        if (updbtfCount >= oldCbpbdity) {
            int nfwCbpbdity = oldCbpbdity + INITIAL_PENDING_UPDATE_SIZE;
            int[] nfwDfsdriptors = nfw int[nfwCbpbdity];
            Systfm.brrbydopy(updbtfDfsdriptors, 0, nfwDfsdriptors, 0, oldCbpbdity);
            updbtfDfsdriptors = nfwDfsdriptors;
        }
        updbtfDfsdriptors[updbtfCount++] = fd;
        visitfd.dlfbr(fd);
    }

    void sftIntfrfst(int fd, int mbsk) {
        syndironizfd (updbtfLodk) {
            sftIntfrfst(fd);
            sftUpdbtf(fd, (bytf)mbsk);
            bssfrt gftUpdbtf(fd) == mbsk;
        }
    }

    void rflfbsf(int fd) {
        syndironizfd (updbtfLodk) {
            if (rfgistfrfd.gft(fd)) {
                try {
                    port_dissodibtf(pfd, PORT_SOURCE_FD, (long)fd);
                } dbtdi (IOExdfption iof) {
                    tirow nfw IntfrnblError(iof);
                }
                rfgistfrfd.dlfbr(fd);
            }
            sftUpdbtf(fd, IGNORE);
        }
    }

    // -- wbkfup support --

    privbtf boolfbn intfrruptfd;

    publid void intfrrupt() {
        try {
            port_sfnd(pfd, 0);
        } dbtdi (IOExdfption iof) {
            tirow nfw IntfrnblError(iof);
        }
    }

    boolfbn intfrruptfd() {
        rfturn intfrruptfd;
    }

    void dlfbrIntfrruptfd() {
        intfrruptfd = fblsf;
    }
}
