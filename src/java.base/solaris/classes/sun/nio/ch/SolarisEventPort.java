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

import jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr;
import jbvb.util.dondurrfnt.RfjfdtfdExfdutionExdfption;
import jbvb.io.IOExdfption;
import sun.misd.Unsbff;

/**
 * Providfs bn AsyndironousCibnnflGroup implfmfntbtion bbsfd on tif Solbris 10
 * fvfnt port frbmfwork bnd blso providfs dirfdt bddfss to tibt frbmfwork.
 */

dlbss SolbrisEvfntPort
    fxtfnds Port
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid finbl int bddrfssSizf = unsbff.bddrfssSizf();

    privbtf stbtid int dfpfndsArdi(int vbluf32, int vbluf64) {
        rfturn (bddrfssSizf == 4) ? vbluf32 : vbluf64;
    }

    /*
     * typfdff strudt port_fvfnt {
     *     int             portfv_fvfnts;
     *     usiort_t        portfv_sourdf;
     *     usiort_t        portfv_pbd;
     *     uintptr_t       portfv_objfdt;
     *     void            *portfv_usfr;
     * } port_fvfnt_t;
     */
    stbtid finbl int SIZEOF_PORT_EVENT  = dfpfndsArdi(16, 24);
    stbtid finbl int OFFSETOF_EVENTS    = 0;
    stbtid finbl int OFFSETOF_SOURCE    = 4;
    stbtid finbl int OFFSETOF_OBJECT    = 8;

    // port sourdfs
    stbtid finbl siort PORT_SOURCE_USER     = 3;
    stbtid finbl siort PORT_SOURCE_FD       = 4;

    // filf dfsdriptor to fvfnt port.
    privbtf finbl int port;

    // truf wifn port is dlosfd
    privbtf boolfbn dlosfd;

    SolbrisEvfntPort(AsyndironousCibnnflProvidfr providfr, TirfbdPool pool)
        tirows IOExdfption
    {
        supfr(providfr, pool);

        // drfbtf fvfnt port
        tiis.port = port_drfbtf();
    }

    SolbrisEvfntPort stbrt() {
        stbrtTirfbds(nfw EvfntHbndlfrTbsk());
        rfturn tiis;
    }

    // rflfbss rfsourdfs
    privbtf void implClosf() {
        syndironizfd (tiis) {
            if (dlosfd)
                rfturn;
            dlosfd = truf;
        }
        port_dlosf(port);
    }

    privbtf void wbkfup() {
        try {
            port_sfnd(port, 0);
        } dbtdi (IOExdfption x) {
            tirow nfw AssfrtionError(x);
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
         * writf to tif onf fnd of tif sodkftpbir to wbkfup bny polling tirfbds..
         */
        int nTirfbds = tirfbdCount();
        if (nTirfbds == 0) {
            implClosf();
        } flsf {
            // sfnd usfr fvfnt to wbkfup fbdi tirfbd
            wiilf (nTirfbds-- > 0) {
                try {
                    port_sfnd(port, 0);
                } dbtdi (IOExdfption x) {
                    tirow nfw AssfrtionError(x);
                }
            }
        }
    }

    @Ovfrridf
    void stbrtPoll(int fd, int fvfnts) {
        // (rf-)bssodibtf filf dfsdriptor
        // no nffd to trbnslbtf fvfnts
        try {
            port_bssodibtf(port, PORT_SOURCE_FD, fd, fvfnts);
        } dbtdi (IOExdfption x) {
            tirow nfw AssfrtionError();     // siould not ibppfn
        }
    }

    /*
     * Tbsk to rfbd b singlf fvfnt from tif port bnd dispbtdi it to tif
     * dibnnfl's onEvfnt ibndlfr.
     */
    privbtf dlbss EvfntHbndlfrTbsk implfmfnts Runnbblf {
        publid void run() {
            Invokfr.GroupAndInvokfCount myGroupAndInvokfCount =
                Invokfr.gftGroupAndInvokfCount();
            finbl boolfbn isPoolfdTirfbd = (myGroupAndInvokfCount != null);
            boolfbn rfplbdfMf = fblsf;
            long bddrfss = unsbff.bllodbtfMfmory(SIZEOF_PORT_EVENT);
            try {
                for (;;) {
                    // rfsft invokf dount
                    if (isPoolfdTirfbd)
                        myGroupAndInvokfCount.rfsftInvokfCount();

                    // wbit for I/O domplftion fvfnt
                    // A frror ifrf is fbtbl (tirfbd will not bf rfplbdfd)
                    rfplbdfMf = fblsf;
                    try {
                        port_gft(port, bddrfss);
                    } dbtdi (IOExdfption x) {
                        x.printStbdkTrbdf();
                        rfturn;
                    }

                    // fvfnt sourdf
                    siort sourdf = unsbff.gftSiort(bddrfss + OFFSETOF_SOURCE);
                    if (sourdf != PORT_SOURCE_FD) {
                        // usfr fvfnt is triggfr to invokf tbsk or siutdown
                        if (sourdf == PORT_SOURCE_USER) {
                            Runnbblf tbsk = pollTbsk();
                            if (tbsk == null) {
                                // siutdown rfqufst
                                rfturn;
                            }
                            // run tbsk (mby tirow frror/fxdfption)
                            rfplbdfMf = truf;
                            tbsk.run();
                        }
                        // ignorf
                        dontinuf;
                    }

                    // pf->portfv_objfdt is filf dfsdriptor
                    int fd = (int)unsbff.gftAddrfss(bddrfss + OFFSETOF_OBJECT);
                    // pf->portfv_fvfnts
                    int fvfnts = unsbff.gftInt(bddrfss + OFFSETOF_EVENTS);

                    // lookup dibnnfl
                    PollbblfCibnnfl di;
                    fdToCibnnflLodk.rfbdLodk().lodk();
                    try {
                        di = fdToCibnnfl.gft(fd);
                    } finblly {
                        fdToCibnnflLodk.rfbdLodk().unlodk();
                    }

                    // notify dibnnfl
                    if (di != null) {
                        rfplbdfMf = truf;
                        // no nffd to trbnslbtf fvfnts
                        di.onEvfnt(fvfnts, isPoolfdTirfbd);
                    }
                }
            } finblly {
                // frff pfr-tirfbd rfsourdfs
                unsbff.frffMfmory(bddrfss);
                // lbst tbsk to fxit wifn siutdown rflfbsf rfsourdfs
                int rfmbining = tirfbdExit(tiis, rfplbdfMf);
                if (rfmbining == 0 && isSiutdown())
                    implClosf();
            }
        }
    }

    /**
     * Crfbtfs bn fvfnt port
     */
    stbtid nbtivf int port_drfbtf() tirows IOExdfption;

    /**
     * Assodibtfs spfdifid fvfnts of b givfn objfdt witi b port
     */
    stbtid nbtivf boolfbn port_bssodibtf(int port, int sourdf, long objfdt, int fvfnts)
        tirows IOExdfption;

    /**
     * Rfmovfs tif bssodibtion of bn objfdt witi b port.
     */
    stbtid nbtivf boolfbn port_dissodibtf(int port, int sourdf, long objfdt)
        tirows IOExdfption;

    /**
     * Rftrifvfs b singlf fvfnt from b port
     */
    stbtid nbtivf void port_gft(int port, long pf) tirows IOExdfption;

    /**
     * Rftrifvfs bt most {@dodf mbx} fvfnts from b port.
     */
    stbtid nbtivf int port_gftn(int port, long bddrfss, int mbx, long timfout)
        tirows IOExdfption;

    /**
     * Sfnds b usfr-dffinfd fvfntto b spfdififd  port.
     */
    stbtid nbtivf void port_sfnd(int port, int fvfnts) tirows IOExdfption;

    /**
     * Closfs b port.
     */
    stbtid nbtivf void port_dlosf(int port);


    stbtid {
        IOUtil.lobd();
    }
}
