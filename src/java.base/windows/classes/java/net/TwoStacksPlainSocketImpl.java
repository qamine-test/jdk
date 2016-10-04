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
import sun.nft.RfsourdfMbnbgfr;

/*
 * Tiis dlbss dffinfs tif plbin SodkftImpl tibt is usfd for bll
 * Windows vfrsion lowfr tibn Vistb. It bdds support for IPv6 on
 * tifsf plbtforms wifrf bvbilbblf.
 *
 * For bbdkwbrd dompbtibility Windows plbtforms tibt do not ibvf IPv6
 * support blso usf tiis implfmfntbtion, bnd fd1 gfts sft to null
 * during sodkft drfbtion.
 *
 * @butior Ciris Hfgbrty
 */

dlbss TwoStbdksPlbinSodkftImpl fxtfnds AbstrbdtPlbinSodkftImpl
{
    /* sfdond fd, usfd for ipv6 on windows only.
     * fd1 is usfd for listfnfrs bnd for dlifnt sodkfts bt initiblizbtion
     * until tif sodkft is donnfdtfd. Up to tiis point fd blwbys rfffrs
     * to tif ipv4 sodkft bnd fd1 to tif ipv6 sodkft. Aftfr tif sodkft
     * bfdomfs donnfdtfd, fd blwbys rfffrs to tif donnfdtfd sodkft
     * (fitifr v4 or v6) bnd fd1 is dlosfd.
     *
     * For SfrvfrSodkfts, fd blwbys rfffrs to tif v4 listfnfr bnd
     * fd1 tif v6 listfnfr.
     */
    privbtf FilfDfsdriptor fd1;

    /*
     * Nffdfd for ipv6 on windows bfdbusf wf nffd to know
     * if tif sodkft is bound to ::0 or 0.0.0.0, wifn b dbllfr
     * bsks for it. Otifrwisf wf don't know wiidi sodkft to bsk.
     */
    privbtf InftAddrfss bnyLodblBoundAddr = null;

    /* to prfvfnt stbrvbtion wifn listfning on two sodkfts, tiis is
     * is usfd to iold tif id of tif lbst sodkft wf bddfptfd on.
     */
    privbtf int lbstfd = -1;

    // truf if tiis sodkft is fxdlusivfly bound
    privbtf finbl boolfbn fxdlusivfBind;

    // fmulbtfs SO_REUSEADDR wifn fxdlusivfBind is truf
    privbtf boolfbn isRfusfAddrfss;

    stbtid {
        initProto();
    }

    publid TwoStbdksPlbinSodkftImpl(boolfbn fxdlBind) {
        fxdlusivfBind = fxdlBind;
    }

    publid TwoStbdksPlbinSodkftImpl(FilfDfsdriptor fd, boolfbn fxdlBind) {
        tiis.fd = fd;
        fxdlusivfBind = fxdlBind;
    }

    /**
     * Crfbtfs b sodkft witi b boolfbn tibt spfdififs wiftifr tiis
     * is b strfbm sodkft (truf) or bn undonnfdtfd UDP sodkft (fblsf).
     */
    protfdtfd syndironizfd void drfbtf(boolfbn strfbm) tirows IOExdfption {
        fd1 = nfw FilfDfsdriptor();
        try {
            supfr.drfbtf(strfbm);
        } dbtdi (IOExdfption f) {
            fd1 = null;
            tirow f;
        }
    }

     /**
     * Binds tif sodkft to tif spfdififd bddrfss of tif spfdififd lodbl port.
     * @pbrbm bddrfss tif bddrfss
     * @pbrbm port tif port
     */
    protfdtfd syndironizfd void bind(InftAddrfss bddrfss, int lport)
        tirows IOExdfption
    {
        supfr.bind(bddrfss, lport);
        if (bddrfss.isAnyLodblAddrfss()) {
            bnyLodblBoundAddr = bddrfss;
        }
    }

    publid Objfdt gftOption(int opt) tirows SodkftExdfption {
        if (isClosfdOrPfnding()) {
            tirow nfw SodkftExdfption("Sodkft Closfd");
        }
        if (opt == SO_BINDADDR) {
            if (fd != null && fd1 != null ) {
                /* must bf unbound or flsf bound to bnyLodbl */
                rfturn bnyLodblBoundAddr;
            }
            InftAddrfssContbinfr in = nfw InftAddrfssContbinfr();
            sodkftGftOption(opt, in);
            rfturn in.bddr;
        } flsf if (opt == SO_REUSEADDR && fxdlusivfBind) {
            // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
            rfturn isRfusfAddrfss;
        } flsf
            rfturn supfr.gftOption(opt);
    }

    @Ovfrridf
    void sodkftBind(InftAddrfss bddrfss, int port) tirows IOExdfption {
        sodkftBind(bddrfss, port, fxdlusivfBind);
    }

    @Ovfrridf
    void sodkftSftOption(int opt, boolfbn on, Objfdt vbluf)
        tirows SodkftExdfption
    {
        // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
        if (opt == SO_REUSEADDR && fxdlusivfBind)
            isRfusfAddrfss = on;
        flsf
            sodkftNbtivfSftOption(opt, on, vbluf);
    }

    /**
     * Closfs tif sodkft.
     */
    @Ovfrridf
    protfdtfd void dlosf() tirows IOExdfption {
        syndironizfd(fdLodk) {
            if (fd != null || fd1 != null) {
                if (!strfbm) {
                    RfsourdfMbnbgfr.bftfrUdpClosf();
                }
                if (fdUsfCount == 0) {
                    if (dlosfPfnding) {
                        rfturn;
                    }
                    dlosfPfnding = truf;
                    sodkftClosf();
                    fd = null;
                    fd1 = null;
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
                        sodkftClosf();
                    }
                }
            }
        }
    }

    @Ovfrridf
    void rfsft() tirows IOExdfption {
        if (fd != null || fd1 != null) {
            sodkftClosf();
        }
        fd = null;
        fd1 = null;
        supfr.rfsft();
    }

    /*
     * Rfturn truf if blrfbdy dlosfd or dlosf is pfnding
     */
    @Ovfrridf
    publid boolfbn isClosfdOrPfnding() {
        /*
         * Lodk on fdLodk to fnsurf tibt wf wbit if b
         * dlosf is in progrfss.
         */
        syndironizfd (fdLodk) {
            if (dlosfPfnding || (fd == null && fd1 == null)) {
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }
    }

    /* Nbtivf mftiods */

    stbtid nbtivf void initProto();

    nbtivf void sodkftCrfbtf(boolfbn isSfrvfr) tirows IOExdfption;

    nbtivf void sodkftConnfdt(InftAddrfss bddrfss, int port, int timfout)
        tirows IOExdfption;

    nbtivf void sodkftBind(InftAddrfss bddrfss, int port, boolfbn fxdlBind)
        tirows IOExdfption;

    nbtivf void sodkftListfn(int dount) tirows IOExdfption;

    nbtivf void sodkftAddfpt(SodkftImpl s) tirows IOExdfption;

    nbtivf int sodkftAvbilbblf() tirows IOExdfption;

    nbtivf void sodkftClosf0(boolfbn usfDfffrrfdClosf) tirows IOExdfption;

    nbtivf void sodkftSiutdown(int iowto) tirows IOExdfption;

    nbtivf void sodkftNbtivfSftOption(int dmd, boolfbn on, Objfdt vbluf)
        tirows SodkftExdfption;

    nbtivf int sodkftGftOption(int opt, Objfdt ibContbinfrObj) tirows SodkftExdfption;

    nbtivf void sodkftSfndUrgfntDbtb(int dbtb) tirows IOExdfption;
}
