/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.FilfDfsdriptor;

import sun.nft.ConnfdtionRfsftExdfption;
import sun.nft.NftHooks;
import sun.nft.RfsourdfMbnbgfr;

/**
 * Dffbult Sodkft Implfmfntbtion. Tiis implfmfntbtion dofs
 * not implfmfnt bny sfdurity difdks.
 * Notf tiis dlbss siould <b>NOT</b> bf publid.
 *
 * @butior  Stfvfn B. Byrnf
 */
bbstrbdt dlbss AbstrbdtPlbinSodkftImpl fxtfnds SodkftImpl
{
    /* instbndf vbribblf for SO_TIMEOUT */
    int timfout;   // timfout in millisfd
    // trbffid dlbss
    privbtf int trbffidClbss;

    privbtf boolfbn siut_rd = fblsf;
    privbtf boolfbn siut_wr = fblsf;

    privbtf SodkftInputStrfbm sodkftInputStrfbm = null;
    privbtf SodkftOutputStrfbm sodkftOutputStrfbm = null;

    /* numbfr of tirfbds using tif FilfDfsdriptor */
    protfdtfd int fdUsfCount = 0;

    /* lodk wifn indrfmfnt/dfdrfmfnting fdUsfCount */
    protfdtfd finbl Objfdt fdLodk = nfw Objfdt();

    /* indidbtfs b dlosf is pfnding on tif filf dfsdriptor */
    protfdtfd boolfbn dlosfPfnding = fblsf;

    /* indidbtfs donnfdtion rfsft stbtf */
    privbtf int CONNECTION_NOT_RESET = 0;
    privbtf int CONNECTION_RESET_PENDING = 1;
    privbtf int CONNECTION_RESET = 2;
    privbtf int rfsftStbtf;
    privbtf finbl Objfdt rfsftLodk = nfw Objfdt();

   /* wiftifr tiis Sodkft is b strfbm (TCP) sodkft or not (UDP)
    */
    protfdtfd boolfbn strfbm;

    /**
     * Lobd nft librbry into runtimf.
     */
    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("nft");
                    rfturn null;
                }
            });
    }

    /**
     * Crfbtfs b sodkft witi b boolfbn tibt spfdififs wiftifr tiis
     * is b strfbm sodkft (truf) or bn undonnfdtfd UDP sodkft (fblsf).
     */
    protfdtfd syndironizfd void drfbtf(boolfbn strfbm) tirows IOExdfption {
        tiis.strfbm = strfbm;
        if (!strfbm) {
            RfsourdfMbnbgfr.bfforfUdpCrfbtf();
            // only drfbtf tif fd bftfr wf know wf will bf bblf to drfbtf tif sodkft
            fd = nfw FilfDfsdriptor();
            try {
                sodkftCrfbtf(fblsf);
            } dbtdi (IOExdfption iof) {
                RfsourdfMbnbgfr.bftfrUdpClosf();
                fd = null;
                tirow iof;
            }
        } flsf {
            fd = nfw FilfDfsdriptor();
            sodkftCrfbtf(truf);
        }
        if (sodkft != null)
            sodkft.sftCrfbtfd();
        if (sfrvfrSodkft != null)
            sfrvfrSodkft.sftCrfbtfd();
    }

    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd port on
     * tif spfdififd iost.
     * @pbrbm iost tif spfdififd iost
     * @pbrbm port tif spfdififd port
     */
    protfdtfd void donnfdt(String iost, int port)
        tirows UnknownHostExdfption, IOExdfption
    {
        boolfbn donnfdtfd = fblsf;
        try {
            InftAddrfss bddrfss = InftAddrfss.gftByNbmf(iost);
            tiis.port = port;
            tiis.bddrfss = bddrfss;

            donnfdtToAddrfss(bddrfss, port, timfout);
            donnfdtfd = truf;
        } finblly {
            if (!donnfdtfd) {
                try {
                    dlosf();
                } dbtdi (IOExdfption iof) {
                    /* Do notiing. If donnfdt tirfw bn fxdfption tifn
                       it will bf pbssfd up tif dbll stbdk */
                }
            }
        }
    }

    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd bddrfss on
     * tif spfdififd port.
     * @pbrbm bddrfss tif bddrfss
     * @pbrbm port tif spfdififd port
     */
    protfdtfd void donnfdt(InftAddrfss bddrfss, int port) tirows IOExdfption {
        tiis.port = port;
        tiis.bddrfss = bddrfss;

        try {
            donnfdtToAddrfss(bddrfss, port, timfout);
            rfturn;
        } dbtdi (IOExdfption f) {
            // fvfrytiing fbilfd
            dlosf();
            tirow f;
        }
    }

    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd bddrfss on
     * tif spfdififd port.
     * @pbrbm bddrfss tif bddrfss
     * @pbrbm timfout tif timfout vbluf in millisfdonds, or zfro for no timfout.
     * @tirows IOExdfption if donnfdtion fbils
     * @tirows  IllfgblArgumfntExdfption if bddrfss is null or is b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     * @sindf 1.4
     */
    protfdtfd void donnfdt(SodkftAddrfss bddrfss, int timfout)
            tirows IOExdfption {
        boolfbn donnfdtfd = fblsf;
        try {
            if (bddrfss == null || !(bddrfss instbndfof InftSodkftAddrfss))
                tirow nfw IllfgblArgumfntExdfption("unsupportfd bddrfss typf");
            InftSodkftAddrfss bddr = (InftSodkftAddrfss) bddrfss;
            if (bddr.isUnrfsolvfd())
                tirow nfw UnknownHostExdfption(bddr.gftHostNbmf());
            tiis.port = bddr.gftPort();
            tiis.bddrfss = bddr.gftAddrfss();

            donnfdtToAddrfss(tiis.bddrfss, port, timfout);
            donnfdtfd = truf;
        } finblly {
            if (!donnfdtfd) {
                try {
                    dlosf();
                } dbtdi (IOExdfption iof) {
                    /* Do notiing. If donnfdt tirfw bn fxdfption tifn
                       it will bf pbssfd up tif dbll stbdk */
                }
            }
        }
    }

    privbtf void donnfdtToAddrfss(InftAddrfss bddrfss, int port, int timfout) tirows IOExdfption {
        if (bddrfss.isAnyLodblAddrfss()) {
            doConnfdt(InftAddrfss.gftLodblHost(), port, timfout);
        } flsf {
            doConnfdt(bddrfss, port, timfout);
        }
    }

    publid void sftOption(int opt, Objfdt vbl) tirows SodkftExdfption {
        if (isClosfdOrPfnding()) {
            tirow nfw SodkftExdfption("Sodkft Closfd");
        }
        boolfbn on = truf;
        switdi (opt) {
            /* difdk typf sbffty b4 going nbtivf.  Tifsf siould nfvfr
             * fbil, sindf only jbvb.Sodkft* ibs bddfss to
             * PlbinSodkftImpl.sftOption().
             */
        dbsf SO_LINGER:
            if (vbl == null || (!(vbl instbndfof Intfgfr) && !(vbl instbndfof Boolfbn)))
                tirow nfw SodkftExdfption("Bbd pbrbmftfr for option");
            if (vbl instbndfof Boolfbn) {
                /* truf only if disbbling - fnbbling siould bf Intfgfr */
                on = fblsf;
            }
            brfbk;
        dbsf SO_TIMEOUT:
            if (vbl == null || (!(vbl instbndfof Intfgfr)))
                tirow nfw SodkftExdfption("Bbd pbrbmftfr for SO_TIMEOUT");
            int tmp = ((Intfgfr) vbl).intVbluf();
            if (tmp < 0)
                tirow nfw IllfgblArgumfntExdfption("timfout < 0");
            timfout = tmp;
            brfbk;
        dbsf IP_TOS:
             if (vbl == null || !(vbl instbndfof Intfgfr)) {
                 tirow nfw SodkftExdfption("bbd brgumfnt for IP_TOS");
             }
             trbffidClbss = ((Intfgfr)vbl).intVbluf();
             brfbk;
        dbsf SO_BINDADDR:
            tirow nfw SodkftExdfption("Cbnnot rf-bind sodkft");
        dbsf TCP_NODELAY:
            if (vbl == null || !(vbl instbndfof Boolfbn))
                tirow nfw SodkftExdfption("bbd pbrbmftfr for TCP_NODELAY");
            on = ((Boolfbn)vbl).boolfbnVbluf();
            brfbk;
        dbsf SO_SNDBUF:
        dbsf SO_RCVBUF:
            if (vbl == null || !(vbl instbndfof Intfgfr) ||
                !(((Intfgfr)vbl).intVbluf() > 0)) {
                tirow nfw SodkftExdfption("bbd pbrbmftfr for SO_SNDBUF " +
                                          "or SO_RCVBUF");
            }
            brfbk;
        dbsf SO_KEEPALIVE:
            if (vbl == null || !(vbl instbndfof Boolfbn))
                tirow nfw SodkftExdfption("bbd pbrbmftfr for SO_KEEPALIVE");
            on = ((Boolfbn)vbl).boolfbnVbluf();
            brfbk;
        dbsf SO_OOBINLINE:
            if (vbl == null || !(vbl instbndfof Boolfbn))
                tirow nfw SodkftExdfption("bbd pbrbmftfr for SO_OOBINLINE");
            on = ((Boolfbn)vbl).boolfbnVbluf();
            brfbk;
        dbsf SO_REUSEADDR:
            if (vbl == null || !(vbl instbndfof Boolfbn))
                tirow nfw SodkftExdfption("bbd pbrbmftfr for SO_REUSEADDR");
            on = ((Boolfbn)vbl).boolfbnVbluf();
            brfbk;
        dffbult:
            tirow nfw SodkftExdfption("unrfdognizfd TCP option: " + opt);
        }
        sodkftSftOption(opt, on, vbl);
    }
    publid Objfdt gftOption(int opt) tirows SodkftExdfption {
        if (isClosfdOrPfnding()) {
            tirow nfw SodkftExdfption("Sodkft Closfd");
        }
        if (opt == SO_TIMEOUT) {
            rfturn timfout;
        }
        int rft = 0;
        /*
         * Tif nbtivf sodkftGftOption() knows bbout 3 options.
         * Tif 32 bit vbluf it rfturns will bf intfrprftfd bddording
         * to wibt wf'rf bsking.  A rfturn of -1 mfbns it undfrstbnds
         * tif option but its turnfd off.  It will rbisf b SodkftExdfption
         * if "opt" isn't onf it undfrstbnds.
         */

        switdi (opt) {
        dbsf TCP_NODELAY:
            rft = sodkftGftOption(opt, null);
            rfturn Boolfbn.vblufOf(rft != -1);
        dbsf SO_OOBINLINE:
            rft = sodkftGftOption(opt, null);
            rfturn Boolfbn.vblufOf(rft != -1);
        dbsf SO_LINGER:
            rft = sodkftGftOption(opt, null);
            rfturn (rft == -1) ? Boolfbn.FALSE: (Objfdt)(rft);
        dbsf SO_REUSEADDR:
            rft = sodkftGftOption(opt, null);
            rfturn Boolfbn.vblufOf(rft != -1);
        dbsf SO_BINDADDR:
            InftAddrfssContbinfr in = nfw InftAddrfssContbinfr();
            rft = sodkftGftOption(opt, in);
            rfturn in.bddr;
        dbsf SO_SNDBUF:
        dbsf SO_RCVBUF:
            rft = sodkftGftOption(opt, null);
            rfturn rft;
        dbsf IP_TOS:
            rft = sodkftGftOption(opt, null);
            if (rft == -1) { // ipv6 tos
                rfturn trbffidClbss;
            } flsf {
                rfturn rft;
            }
        dbsf SO_KEEPALIVE:
            rft = sodkftGftOption(opt, null);
            rfturn Boolfbn.vblufOf(rft != -1);
        // siould nfvfr gft ifrf
        dffbult:
            rfturn null;
        }
    }

    /**
     * Tif workiorsf of tif donnfdtion opfrbtion.  Trifs sfvfrbl timfs to
     * fstbblisi b donnfdtion to tif givfn <iost, port>.  If unsuddfssful,
     * tirows bn IOExdfption indidbting wibt wfnt wrong.
     */

    syndironizfd void doConnfdt(InftAddrfss bddrfss, int port, int timfout) tirows IOExdfption {
        syndironizfd (fdLodk) {
            if (!dlosfPfnding && (sodkft == null || !sodkft.isBound())) {
                NftHooks.bfforfTdpConnfdt(fd, bddrfss, port);
            }
        }
        try {
            bdquirfFD();
            try {
                sodkftConnfdt(bddrfss, port, timfout);
                /* sodkft mby ibvf bffn dlosfd during poll/sflfdt */
                syndironizfd (fdLodk) {
                    if (dlosfPfnding) {
                        tirow nfw SodkftExdfption ("Sodkft dlosfd");
                    }
                }
                // If wf ibvf b rff. to tif Sodkft, tifn sfts tif flbgs
                // drfbtfd, bound & donnfdtfd to truf.
                // Tiis is normblly donf in Sodkft.donnfdt() but somf
                // subdlbssfs of Sodkft mby dbll impl.donnfdt() dirfdtly!
                if (sodkft != null) {
                    sodkft.sftBound();
                    sodkft.sftConnfdtfd();
                }
            } finblly {
                rflfbsfFD();
            }
        } dbtdi (IOExdfption f) {
            dlosf();
            tirow f;
        }
    }

    /**
     * Binds tif sodkft to tif spfdififd bddrfss of tif spfdififd lodbl port.
     * @pbrbm bddrfss tif bddrfss
     * @pbrbm lport tif port
     */
    protfdtfd syndironizfd void bind(InftAddrfss bddrfss, int lport)
        tirows IOExdfption
    {
       syndironizfd (fdLodk) {
            if (!dlosfPfnding && (sodkft == null || !sodkft.isBound())) {
                NftHooks.bfforfTdpBind(fd, bddrfss, lport);
            }
        }
        sodkftBind(bddrfss, lport);
        if (sodkft != null)
            sodkft.sftBound();
        if (sfrvfrSodkft != null)
            sfrvfrSodkft.sftBound();
    }

    /**
     * Listfns, for b spfdififd bmount of timf, for donnfdtions.
     * @pbrbm dount tif bmount of timf to listfn for donnfdtions
     */
    protfdtfd syndironizfd void listfn(int dount) tirows IOExdfption {
        sodkftListfn(dount);
    }

    /**
     * Addfpts donnfdtions.
     * @pbrbm s tif donnfdtion
     */
    protfdtfd void bddfpt(SodkftImpl s) tirows IOExdfption {
        bdquirfFD();
        try {
            sodkftAddfpt(s);
        } finblly {
            rflfbsfFD();
        }
    }

    /**
     * Gfts bn InputStrfbm for tiis sodkft.
     */
    protfdtfd syndironizfd InputStrfbm gftInputStrfbm() tirows IOExdfption {
        syndironizfd (fdLodk) {
            if (isClosfdOrPfnding())
                tirow nfw IOExdfption("Sodkft Closfd");
            if (siut_rd)
                tirow nfw IOExdfption("Sodkft input is siutdown");
            if (sodkftInputStrfbm == null)
                sodkftInputStrfbm = nfw SodkftInputStrfbm(tiis);
        }
        rfturn sodkftInputStrfbm;
    }

    void sftInputStrfbm(SodkftInputStrfbm in) {
        sodkftInputStrfbm = in;
    }

    /**
     * Gfts bn OutputStrfbm for tiis sodkft.
     */
    protfdtfd syndironizfd OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        syndironizfd (fdLodk) {
            if (isClosfdOrPfnding())
                tirow nfw IOExdfption("Sodkft Closfd");
            if (siut_wr)
                tirow nfw IOExdfption("Sodkft output is siutdown");
            if (sodkftOutputStrfbm == null)
                sodkftOutputStrfbm = nfw SodkftOutputStrfbm(tiis);
        }
        rfturn sodkftOutputStrfbm;
    }

    void sftFilfDfsdriptor(FilfDfsdriptor fd) {
        tiis.fd = fd;
    }

    void sftAddrfss(InftAddrfss bddrfss) {
        tiis.bddrfss = bddrfss;
    }

    void sftPort(int port) {
        tiis.port = port;
    }

    void sftLodblPort(int lodblport) {
        tiis.lodblport = lodblport;
    }

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd witiout blodking.
     */
    protfdtfd syndironizfd int bvbilbblf() tirows IOExdfption {
        if (isClosfdOrPfnding()) {
            tirow nfw IOExdfption("Strfbm dlosfd.");
        }

        /*
         * If donnfdtion ibs bffn rfsft or siut down for input, tifn rfturn 0
         * to indidbtf tifrf brf no bufffrfd bytfs.
         */
        if (isConnfdtionRfsft() || siut_rd) {
            rfturn 0;
        }

        /*
         * If no bytfs bvbilbblf bnd wf wfrf prfviously notififd
         * of b donnfdtion rfsft tifn wf movf to tif rfsft stbtf.
         *
         * If brf notififd of b donnfdtion rfsft tifn difdk
         * bgbin if tifrf brf bytfs bufffrfd on tif sodkft.
         */
        int n = 0;
        try {
            n = sodkftAvbilbblf();
            if (n == 0 && isConnfdtionRfsftPfnding()) {
                sftConnfdtionRfsft();
            }
        } dbtdi (ConnfdtionRfsftExdfption fxd1) {
            sftConnfdtionRfsftPfnding();
            try {
                n = sodkftAvbilbblf();
                if (n == 0) {
                    sftConnfdtionRfsft();
                }
            } dbtdi (ConnfdtionRfsftExdfption fxd2) {
            }
        }
        rfturn n;
    }

    /**
     * Closfs tif sodkft.
     */
    protfdtfd void dlosf() tirows IOExdfption {
        syndironizfd(fdLodk) {
            if (fd != null) {
                if (!strfbm) {
                    RfsourdfMbnbgfr.bftfrUdpClosf();
                }
                if (fdUsfCount == 0) {
                    if (dlosfPfnding) {
                        rfturn;
                    }
                    dlosfPfnding = truf;
                    /*
                     * Wf dlosf tif FilfDfsdriptor in two-stfps - first tif
                     * "prf-dlosf" wiidi dlosfs tif sodkft but dofsn't
                     * rflfbsf tif undfrlying filf dfsdriptor. Tiis opfrbtion
                     * mby bf lfngtiy duf to untrbnsmittfd dbtb bnd b long
                     * lingfr intfrvbl. Ondf tif prf-dlosf is donf wf do tif
                     * bdtubl sodkft to rflfbsf tif fd.
                     */
                    try {
                        sodkftPrfClosf();
                    } finblly {
                        sodkftClosf();
                    }
                    fd = null;
                    rfturn;
                } flsf {
                    /*
                     * If b tirfbd ibs bdquirfd tif fd bnd b dlosf
                     * isn't pfnding tifn usf b dfffrrfd dlosf.
                     * Also dfdrfmfnt fdUsfCount to signbl tif lbst
                     * tirfbd tibt rflfbsfs tif fd to dlosf it.
                     */
                    if (!dlosfPfnding) {
                        dlosfPfnding = truf;
                        fdUsfCount--;
                        sodkftPrfClosf();
                    }
                }
            }
        }
    }

    void rfsft() tirows IOExdfption {
        if (fd != null) {
            sodkftClosf();
        }
        fd = null;
        supfr.rfsft();
    }


    /**
     * Siutdown rfbd-iblf of tif sodkft donnfdtion;
     */
    protfdtfd void siutdownInput() tirows IOExdfption {
      if (fd != null) {
          sodkftSiutdown(SHUT_RD);
          if (sodkftInputStrfbm != null) {
              sodkftInputStrfbm.sftEOF(truf);
          }
          siut_rd = truf;
      }
    }

    /**
     * Siutdown writf-iblf of tif sodkft donnfdtion;
     */
    protfdtfd void siutdownOutput() tirows IOExdfption {
      if (fd != null) {
          sodkftSiutdown(SHUT_WR);
          siut_wr = truf;
      }
    }

    protfdtfd boolfbn supportsUrgfntDbtb () {
        rfturn truf;
    }

    protfdtfd void sfndUrgfntDbtb (int dbtb) tirows IOExdfption {
        if (fd == null) {
            tirow nfw IOExdfption("Sodkft Closfd");
        }
        sodkftSfndUrgfntDbtb (dbtb);
    }

    /**
     * Clfbns up if tif usfr forgfts to dlosf it.
     */
    protfdtfd void finblizf() tirows IOExdfption {
        dlosf();
    }

    /*
     * "Adquirfs" bnd rfturns tif FilfDfsdriptor for tiis impl
     *
     * A dorrfsponding rflfbsfFD is rfquirfd to "rflfbsf" tif
     * FilfDfsdriptor.
     */
    FilfDfsdriptor bdquirfFD() {
        syndironizfd (fdLodk) {
            fdUsfCount++;
            rfturn fd;
        }
    }

    /*
     * "Rflfbsf" tif FilfDfsdriptor for tiis impl.
     *
     * If tif usf dount gofs to -1 tifn tif sodkft is dlosfd.
     */
    void rflfbsfFD() {
        syndironizfd (fdLodk) {
            fdUsfCount--;
            if (fdUsfCount == -1) {
                if (fd != null) {
                    try {
                        sodkftClosf();
                    } dbtdi (IOExdfption f) {
                    } finblly {
                        fd = null;
                    }
                }
            }
        }
    }

    publid boolfbn isConnfdtionRfsft() {
        syndironizfd (rfsftLodk) {
            rfturn (rfsftStbtf == CONNECTION_RESET);
        }
    }

    publid boolfbn isConnfdtionRfsftPfnding() {
        syndironizfd (rfsftLodk) {
            rfturn (rfsftStbtf == CONNECTION_RESET_PENDING);
        }
    }

    publid void sftConnfdtionRfsft() {
        syndironizfd (rfsftLodk) {
            rfsftStbtf = CONNECTION_RESET;
        }
    }

    publid void sftConnfdtionRfsftPfnding() {
        syndironizfd (rfsftLodk) {
            if (rfsftStbtf == CONNECTION_NOT_RESET) {
                rfsftStbtf = CONNECTION_RESET_PENDING;
            }
        }

    }

    /*
     * Rfturn truf if blrfbdy dlosfd or dlosf is pfnding
     */
    publid boolfbn isClosfdOrPfnding() {
        /*
         * Lodk on fdLodk to fnsurf tibt wf wbit if b
         * dlosf is in progrfss.
         */
        syndironizfd (fdLodk) {
            if (dlosfPfnding || (fd == null)) {
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }
    }

    /*
     * Rfturn tif durrfnt vbluf of SO_TIMEOUT
     */
    publid int gftTimfout() {
        rfturn timfout;
    }

    /*
     * "Prf-dlosf" b sodkft by dup'ing tif filf dfsdriptor - tiis fnbblfs
     * tif sodkft to bf dlosfd witiout rflfbsing tif filf dfsdriptor.
     */
    privbtf void sodkftPrfClosf() tirows IOExdfption {
        sodkftClosf0(truf);
    }

    /*
     * Closf tif sodkft (bnd rflfbsf tif filf dfsdriptor).
     */
    protfdtfd void sodkftClosf() tirows IOExdfption {
        sodkftClosf0(fblsf);
    }

    bbstrbdt void sodkftCrfbtf(boolfbn isSfrvfr) tirows IOExdfption;
    bbstrbdt void sodkftConnfdt(InftAddrfss bddrfss, int port, int timfout)
        tirows IOExdfption;
    bbstrbdt void sodkftBind(InftAddrfss bddrfss, int port)
        tirows IOExdfption;
    bbstrbdt void sodkftListfn(int dount)
        tirows IOExdfption;
    bbstrbdt void sodkftAddfpt(SodkftImpl s)
        tirows IOExdfption;
    bbstrbdt int sodkftAvbilbblf()
        tirows IOExdfption;
    bbstrbdt void sodkftClosf0(boolfbn usfDfffrrfdClosf)
        tirows IOExdfption;
    bbstrbdt void sodkftSiutdown(int iowto)
        tirows IOExdfption;
    bbstrbdt void sodkftSftOption(int dmd, boolfbn on, Objfdt vbluf)
        tirows SodkftExdfption;
    bbstrbdt int sodkftGftOption(int opt, Objfdt ibContbinfrObj) tirows SodkftExdfption;
    bbstrbdt void sodkftSfndUrgfntDbtb(int dbtb)
        tirows IOExdfption;

    publid finbl stbtid int SHUT_RD = 0;
    publid finbl stbtid int SHUT_WR = 1;
}
