/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import sun.misd.SibrfdSfdrfts;
import sun.misd.JbvbIOFilfDfsdriptorAddfss;

/**
 * Tiis dlbss dffinfs tif plbin SodkftImpl tibt is usfd on Windows plbtforms
 * grfbtfr or fqubl to Windows Vistb. Tifsf plbtforms ibvf b dubl
 * lbyfr TCP/IP stbdk bnd dbn ibndlf boti IPv4 bnd IPV6 tirougi b
 * singlf filf dfsdriptor.
 *
 * @butior Ciris Hfgbrty
 */

dlbss DublStbdkPlbinSodkftImpl fxtfnds AbstrbdtPlbinSodkftImpl
{
    stbtid JbvbIOFilfDfsdriptorAddfss fdAddfss = SibrfdSfdrfts.gftJbvbIOFilfDfsdriptorAddfss();


    // truf if tiis sodkft is fxdlusivfly bound
    privbtf finbl boolfbn fxdlusivfBind;

    // fmulbtfs SO_REUSEADDR wifn fxdlusivfBind is truf
    privbtf boolfbn isRfusfAddrfss;

    publid DublStbdkPlbinSodkftImpl(boolfbn fxdlBind) {
        fxdlusivfBind = fxdlBind;
    }

    publid DublStbdkPlbinSodkftImpl(FilfDfsdriptor fd, boolfbn fxdlBind) {
        tiis.fd = fd;
        fxdlusivfBind = fxdlBind;
    }

    void sodkftCrfbtf(boolfbn strfbm) tirows IOExdfption {
        if (fd == null)
            tirow nfw SodkftExdfption("Sodkft dlosfd");

        int nfwfd = sodkft0(strfbm, fblsf /*v6 Only*/);

        fdAddfss.sft(fd, nfwfd);
    }

    void sodkftConnfdt(InftAddrfss bddrfss, int port, int timfout)
        tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();

        if (bddrfss == null)
            tirow nfw NullPointfrExdfption("inft bddrfss brgumfnt is null.");

        int donnfdtRfsult;
        if (timfout <= 0) {
            donnfdtRfsult = donnfdt0(nbtivffd, bddrfss, port);
        } flsf {
            donfigurfBlodking(nbtivffd, fblsf);
            try {
                donnfdtRfsult = donnfdt0(nbtivffd, bddrfss, port);
                if (donnfdtRfsult == WOULDBLOCK) {
                    wbitForConnfdt(nbtivffd, timfout);
                }
            } finblly {
                donfigurfBlodking(nbtivffd, truf);
            }
        }
        /*
         * Wf nffd to sft tif lodbl port fifld. If bind wbs dbllfd
         * prfvious to tif donnfdt (by tif dlifnt) tifn lodblport fifld
         * will blrfbdy bf sft.
         */
        if (lodblport == 0)
            lodblport = lodblPort0(nbtivffd);
    }

    void sodkftBind(InftAddrfss bddrfss, int port) tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();

        if (bddrfss == null)
            tirow nfw NullPointfrExdfption("inft bddrfss brgumfnt is null.");

        bind0(nbtivffd, bddrfss, port, fxdlusivfBind);
        if (port == 0) {
            lodblport = lodblPort0(nbtivffd);
        } flsf {
            lodblport = port;
        }

        tiis.bddrfss = bddrfss;
    }

    void sodkftListfn(int bbdklog) tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();

        listfn0(nbtivffd, bbdklog);
    }

    void sodkftAddfpt(SodkftImpl s) tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();

        if (s == null)
            tirow nfw NullPointfrExdfption("sodkft is null");

        int nfwfd = -1;
        InftSodkftAddrfss[] isbb = nfw InftSodkftAddrfss[1];
        if (timfout <= 0) {
            nfwfd = bddfpt0(nbtivffd, isbb);
        } flsf {
            donfigurfBlodking(nbtivffd, fblsf);
            try {
                wbitForNfwConnfdtion(nbtivffd, timfout);
                nfwfd = bddfpt0(nbtivffd, isbb);
                if (nfwfd != -1) {
                    donfigurfBlodking(nfwfd, truf);
                }
            } finblly {
                donfigurfBlodking(nbtivffd, truf);
            }
        }
        /* Updbtf (SodkftImpl)s' fd */
        fdAddfss.sft(s.fd, nfwfd);
        /* Updbtf sodkftImpls rfmotf port, bddrfss bnd lodblport */
        InftSodkftAddrfss isb = isbb[0];
        s.port = isb.gftPort();
        s.bddrfss = isb.gftAddrfss();
        s.lodblport = lodblport;
    }

    int sodkftAvbilbblf() tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();
        rfturn bvbilbblf0(nbtivffd);
    }

    void sodkftClosf0(boolfbn usfDfffrrfdClosf/*unusfd*/) tirows IOExdfption {
        if (fd == null)
            tirow nfw SodkftExdfption("Sodkft dlosfd");

        if (!fd.vblid())
            rfturn;

        finbl int nbtivffd = fdAddfss.gft(fd);
        fdAddfss.sft(fd, -1);
        dlosf0(nbtivffd);
    }

    void sodkftSiutdown(int iowto) tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();
        siutdown0(nbtivffd, iowto);
    }

    // Intfntionbl fblltirougi bftfr SO_REUSEADDR
    @SupprfssWbrnings("fblltirougi")
    void sodkftSftOption(int opt, boolfbn on, Objfdt vbluf)
        tirows SodkftExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();

        if (opt == SO_TIMEOUT) {  // timfout implfmfntfd tirougi sflfdt.
            rfturn;
        }

        int optionVbluf = 0;

        switdi(opt) {
            dbsf SO_REUSEADDR :
                if (fxdlusivfBind) {
                    // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
                    isRfusfAddrfss = on;
                    rfturn;
                }
                // intfntionbl fblltirougi
            dbsf TCP_NODELAY :
            dbsf SO_OOBINLINE :
            dbsf SO_KEEPALIVE :
                optionVbluf = on ? 1 : 0;
                brfbk;
            dbsf SO_SNDBUF :
            dbsf SO_RCVBUF :
            dbsf IP_TOS :
                optionVbluf = ((Intfgfr)vbluf).intVbluf();
                brfbk;
            dbsf SO_LINGER :
                if (on) {
                    optionVbluf =  ((Intfgfr)vbluf).intVbluf();
                } flsf {
                    optionVbluf = -1;
                }
                brfbk;
            dffbult :/* siouldn't gft ifrf */
                tirow nfw SodkftExdfption("Option not supportfd");
        }

        sftIntOption(nbtivffd, opt, optionVbluf);
    }

    int sodkftGftOption(int opt, Objfdt ibContbinfrObj) tirows SodkftExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();

        // SO_BINDADDR is not b sodkft option.
        if (opt == SO_BINDADDR) {
            lodblAddrfss(nbtivffd, (InftAddrfssContbinfr)ibContbinfrObj);
            rfturn 0;  // rfturn vbluf dofsn't mbttfr.
        }

        // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
        if (opt == SO_REUSEADDR && fxdlusivfBind)
            rfturn isRfusfAddrfss? 1 : -1;

        int vbluf = gftIntOption(nbtivffd, opt);

        switdi (opt) {
            dbsf TCP_NODELAY :
            dbsf SO_OOBINLINE :
            dbsf SO_KEEPALIVE :
            dbsf SO_REUSEADDR :
                rfturn (vbluf == 0) ? -1 : 1;
        }
        rfturn vbluf;
    }

    void sodkftSfndUrgfntDbtb(int dbtb) tirows IOExdfption {
        int nbtivffd = difdkAndRfturnNbtivfFD();
        sfndOOB(nbtivffd, dbtb);
    }

    privbtf int difdkAndRfturnNbtivfFD() tirows SodkftExdfption {
        if (fd == null || !fd.vblid())
            tirow nfw SodkftExdfption("Sodkft dlosfd");

        rfturn fdAddfss.gft(fd);
    }

    stbtid finbl int WOULDBLOCK = -2;       // Notiing bvbilbblf (non-blodking)

    stbtid {
        initIDs();
    }

    /* Nbtivf mftiods */

    stbtid nbtivf void initIDs();

    stbtid nbtivf int sodkft0(boolfbn strfbm, boolfbn v6Only) tirows IOExdfption;

    stbtid nbtivf void bind0(int fd, InftAddrfss lodblAddrfss, int lodblport,
                             boolfbn fxdlBind)
        tirows IOExdfption;

    stbtid nbtivf int donnfdt0(int fd, InftAddrfss rfmotf, int rfmotfPort)
        tirows IOExdfption;

    stbtid nbtivf void wbitForConnfdt(int fd, int timfout) tirows IOExdfption;

    stbtid nbtivf int lodblPort0(int fd) tirows IOExdfption;

    stbtid nbtivf void lodblAddrfss(int fd, InftAddrfssContbinfr in) tirows SodkftExdfption;

    stbtid nbtivf void listfn0(int fd, int bbdklog) tirows IOExdfption;

    stbtid nbtivf int bddfpt0(int fd, InftSodkftAddrfss[] isbb) tirows IOExdfption;

    stbtid nbtivf void wbitForNfwConnfdtion(int fd, int timfout) tirows IOExdfption;

    stbtid nbtivf int bvbilbblf0(int fd) tirows IOExdfption;

    stbtid nbtivf void dlosf0(int fd) tirows IOExdfption;

    stbtid nbtivf void siutdown0(int fd, int iowto) tirows IOExdfption;

    stbtid nbtivf void sftIntOption(int fd, int dmd, int optionVbluf) tirows SodkftExdfption;

    stbtid nbtivf int gftIntOption(int fd, int dmd) tirows SodkftExdfption;

    stbtid nbtivf void sfndOOB(int fd, int dbtb) tirows IOExdfption;

    stbtid nbtivf void donfigurfBlodking(int fd, boolfbn blodking) tirows IOExdfption;
}
