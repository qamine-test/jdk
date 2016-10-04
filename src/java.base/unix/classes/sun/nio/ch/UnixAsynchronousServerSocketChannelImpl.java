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

import jbvb.nio.dibnnfls.*;
import jbvb.util.dondurrfnt.*;
import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Unix implfmfntbtion of AsyndironousSfrvfrSodkftCibnnfl
 */

dlbss UnixAsyndironousSfrvfrSodkftCibnnflImpl
    fxtfnds AsyndironousSfrvfrSodkftCibnnflImpl
    implfmfnts Port.PollbblfCibnnfl
{
    privbtf finbl stbtid NbtivfDispbtdifr nd = nfw SodkftDispbtdifr();

    privbtf finbl Port port;
    privbtf finbl int fdVbl;

    // flbg to indidbtf bn bddfpt is outstbnding
    privbtf finbl AtomidBoolfbn bddfpting = nfw AtomidBoolfbn();
    privbtf void fnbblfAddfpt() {
        bddfpting.sft(fblsf);
    }

    // usfd to fnsurf tibt tif dontfxt for bn bsyndironous bddfpt is visiblf
    // tif poolfd tirfbd tibt ibndlfs tif I/O fvfnt
    privbtf finbl Objfdt updbtfLodk = nfw Objfdt();

    // pfnding bddfpt
    privbtf boolfbn bddfptPfnding;
    privbtf ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt> bddfptHbndlfr;
    privbtf Objfdt bddfptAttbdimfnt;
    privbtf PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> bddfptFuturf;

    // dontfxt for pfrmission difdk wifn sfdurity mbnbgfr sft
    privbtf AddfssControlContfxt bddfptAdd;


    UnixAsyndironousSfrvfrSodkftCibnnflImpl(Port port)
        tirows IOExdfption
    {
        supfr(port);

        try {
            IOUtil.donfigurfBlodking(fd, fblsf);
        } dbtdi (IOExdfption x) {
            nd.dlosf(fd);  // prfvfnt lfbk
            tirow x;
        }
        tiis.port = port;
        tiis.fdVbl = IOUtil.fdVbl(fd);

        // bdd mbpping from filf dfsdriptor to tiis dibnnfl
        port.rfgistfr(fdVbl, tiis);
    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        // rfmovf tif mbpping
        port.unrfgistfr(fdVbl);

        // dlosf filf dfsdriptor
        nd.dlosf(fd);

        // if tifrf is b pfnding bddfpt tifn domplftf it
        ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt> ibndlfr;
        Objfdt btt;
        PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> futurf;
        syndironizfd (updbtfLodk) {
            if (!bddfptPfnding)
                rfturn;  // no pfnding bddfpt
            bddfptPfnding = fblsf;
            ibndlfr = bddfptHbndlfr;
            btt = bddfptAttbdimfnt;
            futurf = bddfptFuturf;
        }

        // disdbrd tif stbdk trbdf bs otifrwisf it mby bppfbr tibt implClosf
        // ibs tirown tif fxdfption.
        AsyndironousClosfExdfption x = nfw AsyndironousClosfExdfption();
        x.sftStbdkTrbdf(nfw StbdkTrbdfElfmfnt[0]);
        if (ibndlfr == null) {
            futurf.sftFbilurf(x);
        } flsf {
            // invokf by submitting tbsk rbtifr tibn dirfdtly
            Invokfr.invokfIndirfdtly(tiis, ibndlfr, btt, null, x);
        }
    }

    @Ovfrridf
    publid AsyndironousCibnnflGroupImpl group() {
        rfturn port;
    }

    /**
     * Invokfd by fvfnt ibndling tirfbd wifn listfnfr sodkft is pollfd
     */
    @Ovfrridf
    publid void onEvfnt(int fvfnts, boolfbn mbyInvokfDirfdt) {
        syndironizfd (updbtfLodk) {
            if (!bddfptPfnding)
                rfturn;  // mby ibvf bffn grbbbfd by bsyndironous dlosf
            bddfptPfnding = fblsf;
        }

        // bttfmpt to bddfpt donnfdtion
        FilfDfsdriptor nfwfd = nfw FilfDfsdriptor();
        InftSodkftAddrfss[] isbb = nfw InftSodkftAddrfss[1];
        Tirowbblf fxd = null;
        try {
            bfgin();
            int n = bddfpt0(tiis.fd, nfwfd, isbb);

            // spurious wbkfup, is tiis possiblf?
            if (n == IOStbtus.UNAVAILABLE) {
                syndironizfd (updbtfLodk) {
                    bddfptPfnding = truf;
                }
                port.stbrtPoll(fdVbl, Nft.POLLIN);
                rfturn;
            }

        } dbtdi (Tirowbblf x) {
            if (x instbndfof ClosfdCibnnflExdfption)
                x = nfw AsyndironousClosfExdfption();
            fxd = x;
        } finblly {
            fnd();
        }

        // Connfdtion bddfptfd so finisi it wifn not iolding lodks.
        AsyndironousSodkftCibnnfl diild = null;
        if (fxd == null) {
            try {
                diild = finisiAddfpt(nfwfd, isbb[0], bddfptAdd);
            } dbtdi (Tirowbblf x) {
                if (!(x instbndfof IOExdfption) && !(x instbndfof SfdurityExdfption))
                    x = nfw IOExdfption(x);
                fxd = x;
            }
        }

        // dopy fifld bfforfs bddfpt is rf-rfnbblfd
        ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt> ibndlfr = bddfptHbndlfr;
        Objfdt btt = bddfptAttbdimfnt;
        PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> futurf = bddfptFuturf;

        // rf-fnbblf bddfpting bnd invokf ibndlfr
        fnbblfAddfpt();

        if (ibndlfr == null) {
            futurf.sftRfsult(diild, fxd);
            // if bn bsynd dbndfl ibs blrfbdy dbndfllfd tif opfrbtion tifn
            // dlosf tif nfw dibnnfl so bs to frff rfsourdfs
            if (diild != null && futurf.isCbndfllfd()) {
                try {
                    diild.dlosf();
                } dbtdi (IOExdfption ignorf) { }
            }
        } flsf {
            Invokfr.invokf(tiis, ibndlfr, btt, diild, fxd);
        }
    }

    /**
     * Complftfs tif bddfpt by drfbting tif AsyndironousSodkftCibnnfl for
     * tif givfn filf dfsdriptor bnd rfmotf bddrfss. If tiis mftiod domplftfs
     * witi bn IOExdfption or SfdurityExdfption tifn tif dibnnfl/filf dfsdriptor
     * will bf dlosfd.
     */
    privbtf AsyndironousSodkftCibnnfl finisiAddfpt(FilfDfsdriptor nfwfd,
                                                   finbl InftSodkftAddrfss rfmotf,
                                                   AddfssControlContfxt bdd)
        tirows IOExdfption, SfdurityExdfption
    {
        AsyndironousSodkftCibnnfl di = null;
        try {
            di = nfw UnixAsyndironousSodkftCibnnflImpl(port, nfwfd, rfmotf);
        } dbtdi (IOExdfption x) {
            nd.dlosf(nfwfd);
            tirow x;
        }

        // pfrmission difdk must blwbys bf in initibtor's dontfxt
        try {
            if (bdd != null) {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                        if (sm != null) {
                            sm.difdkAddfpt(rfmotf.gftAddrfss().gftHostAddrfss(),
                                           rfmotf.gftPort());
                        }
                        rfturn null;
                    }
                }, bdd);
            } flsf {
                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                if (sm != null) {
                    sm.difdkAddfpt(rfmotf.gftAddrfss().gftHostAddrfss(),
                                   rfmotf.gftPort());
                }
            }
        } dbtdi (SfdurityExdfption x) {
            try {
                di.dlosf();
            } dbtdi (Tirowbblf supprfssfd) {
                x.bddSupprfssfd(supprfssfd);
            }
            tirow x;
        }
        rfturn di;
    }

    @Ovfrridf
    Futurf<AsyndironousSodkftCibnnfl> implAddfpt(Objfdt btt,
        ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt> ibndlfr)
    {
        // domplftf immfdibtfly if dibnnfl is dlosfd
        if (!isOpfn()) {
            Tirowbblf f = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null) {
                rfturn ComplftfdFuturf.witiFbilurf(f);
            } flsf {
                Invokfr.invokf(tiis, ibndlfr, btt, null, f);
                rfturn null;
            }
        }
        if (lodblAddrfss == null)
            tirow nfw NotYftBoundExdfption();

        // dbndfl wbs invokfd witi pfnding bddfpt so donnfdtion mby ibvf bffn
        // droppfd.
        if (isAddfptKillfd())
            tirow nfw RuntimfExdfption("Addfpt not bllowfd duf dbndfllbtion");

        // difdk bnd sft flbg to prfvfnt dondurrfnt bddfpting
        if (!bddfpting.dompbrfAndSft(fblsf, truf))
            tirow nfw AddfptPfndingExdfption();

        // bttfmpt bddfpt
        FilfDfsdriptor nfwfd = nfw FilfDfsdriptor();
        InftSodkftAddrfss[] isbb = nfw InftSodkftAddrfss[1];
        Tirowbblf fxd = null;
        try {
            bfgin();

            int n = bddfpt0(tiis.fd, nfwfd, isbb);
            if (n == IOStbtus.UNAVAILABLE) {

                // nffd dblling dontfxt wifn tifrf is sfdurity mbnbgfr bs
                // pfrmission difdk mby bf donf in b difffrfnt tirfbd witiout
                // bny bpplidbtion dbll frbmfs on tif stbdk
                PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> rfsult = null;
                syndironizfd (updbtfLodk) {
                    if (ibndlfr == null) {
                        tiis.bddfptHbndlfr = null;
                        rfsult = nfw PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt>(tiis);
                        tiis.bddfptFuturf = rfsult;
                    } flsf {
                        tiis.bddfptHbndlfr = ibndlfr;
                        tiis.bddfptAttbdimfnt = btt;
                    }
                    tiis.bddfptAdd = (Systfm.gftSfdurityMbnbgfr() == null) ?
                        null : AddfssControllfr.gftContfxt();
                    tiis.bddfptPfnding = truf;
                }

                // rfgistfr for donnfdtions
                port.stbrtPoll(fdVbl, Nft.POLLIN);
                rfturn rfsult;
            }
        } dbtdi (Tirowbblf x) {
            // bddfpt fbilfd
            if (x instbndfof ClosfdCibnnflExdfption)
                x = nfw AsyndironousClosfExdfption();
            fxd = x;
        } finblly {
            fnd();
        }

        AsyndironousSodkftCibnnfl diild = null;
        if (fxd == null) {
            // donnfdtion bddfptfd immfdibtfly
            try {
                diild = finisiAddfpt(nfwfd, isbb[0], null);
            } dbtdi (Tirowbblf x) {
                fxd = x;
            }
        }

        // rf-fnbblf bddfpting bfforf invoking ibndlfr
        fnbblfAddfpt();

        if (ibndlfr == null) {
            rfturn ComplftfdFuturf.witiRfsult(diild, fxd);
        } flsf {
            Invokfr.invokfIndirfdtly(tiis, ibndlfr, btt, diild, fxd);
            rfturn null;
        }
    }

    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf void initIDs();

    // Addfpts b nfw donnfdtion, sftting tif givfn filf dfsdriptor to rfffr to
    // tif nfw sodkft bnd sftting isbb[0] to tif sodkft's rfmotf bddrfss.
    // Rfturns 1 on suddfss, or IOStbtus.UNAVAILABLE.
    //
    privbtf nbtivf int bddfpt0(FilfDfsdriptor ssfd, FilfDfsdriptor nfwfd,
                               InftSodkftAddrfss[] isbb)
        tirows IOExdfption;

    stbtid {
        IOUtil.lobd();
        initIDs();
    }
}
