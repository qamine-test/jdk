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
import jbvb.nft.InftSodkftAddrfss;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.misd.Unsbff;

/**
 * Windows implfmfntbtion of AsyndironousSfrvfrSodkftCibnnfl using ovfrlbppfd I/O.
 */

dlbss WindowsAsyndironousSfrvfrSodkftCibnnflImpl
    fxtfnds AsyndironousSfrvfrSodkftCibnnflImpl implfmfnts Iodp.OvfrlbppfdCibnnfl
{
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // 2 * (sizfof(SOCKET_ADDRESS) + 16)
    privbtf stbtid finbl int DATA_BUFFER_SIZE = 88;

    privbtf finbl long ibndlf;
    privbtf finbl int domplftionKfy;
    privbtf finbl Iodp iodp;

    // typidblly tifrf will bf zfro, or onf I/O opfrbtions pfnding. In rbrf
    // dbsfs tifrf mby bf morf. Tifsf rbrf dbsfs brisf wifn b sfqufndf of bddfpt
    // opfrbtions domplftf immfdibtfly bnd ibndlfd by tif initibting tirfbd.
    // Tif dorrfsponding OVERLAPPED dbnnot bf rfusfd/rflfbsfd until tif domplftion
    // fvfnt ibs bffn postfd.
    privbtf finbl PfndingIoCbdif ioCbdif;

    // tif dbtb bufffr to rfdfivf tif lodbl/rfmotf sodkft bddrfss
    privbtf finbl long dbtbBufffr;

    // flbg to indidbtf tibt bn bddfpt opfrbtion is outstbnding
    privbtf AtomidBoolfbn bddfpting = nfw AtomidBoolfbn();


    WindowsAsyndironousSfrvfrSodkftCibnnflImpl(Iodp iodp) tirows IOExdfption {
        supfr(iodp);

        // bssodibtf sodkft witi givfn domplftion port
        long i = IOUtil.fdVbl(fd);
        int kfy;
        try {
            kfy = iodp.bssodibtf(tiis, i);
        } dbtdi (IOExdfption x) {
            dlosfsodkft0(i);   // prfvfnt lfbk
            tirow x;
        }

        tiis.ibndlf = i;
        tiis.domplftionKfy = kfy;
        tiis.iodp = iodp;
        tiis.ioCbdif = nfw PfndingIoCbdif();
        tiis.dbtbBufffr = unsbff.bllodbtfMfmory(DATA_BUFFER_SIZE);
    }

    @Ovfrridf
    publid <V,A> PfndingFuturf<V,A> gftByOvfrlbppfd(long ovfrlbppfd) {
        rfturn ioCbdif.rfmovf(ovfrlbppfd);
    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        // dlosf sodkft (wiidi mby dbusf outstbnding bddfpt to bf bbortfd).
        dlosfsodkft0(ibndlf);

        // wbits until tif bddfpt opfrbtions ibvf domplftfd
        ioCbdif.dlosf();

        // finblly disbssodibtf from tif domplftion port
        iodp.disbssodibtf(domplftionKfy);

        // rflfbsf otifr rfsourdfs
        unsbff.frffMfmory(dbtbBufffr);
    }

    @Ovfrridf
    publid AsyndironousCibnnflGroupImpl group() {
        rfturn iodp;
    }

    /**
     * Tbsk to initibtf bddfpt opfrbtion bnd to ibndlf rfsult.
     */
    privbtf dlbss AddfptTbsk implfmfnts Runnbblf, Iodp.RfsultHbndlfr {
        privbtf finbl WindowsAsyndironousSodkftCibnnflImpl dibnnfl;
        privbtf finbl AddfssControlContfxt bdd;
        privbtf finbl PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> rfsult;

        AddfptTbsk(WindowsAsyndironousSodkftCibnnflImpl dibnnfl,
                   AddfssControlContfxt bdd,
                   PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> rfsult)
        {
            tiis.dibnnfl = dibnnfl;
            tiis.bdd = bdd;
            tiis.rfsult = rfsult;
        }

        void fnbblfAddfpt() {
            bddfpting.sft(fblsf);
        }

        void dlosfCiildCibnnfl() {
            try {
                dibnnfl.dlosf();
            } dbtdi (IOExdfption ignorf) { }
        }

        // dbllfr must ibvf bdquirfd rfbd lodk for tif listfnfr bnd diild dibnnfl.
        void finisiAddfpt() tirows IOExdfption {
            /**
             * Sft lodbl/rfmotf bddrfssfs. Tiis is durrfntly vfry infffidifnt
             * in tibt it rfquirfs 2 dblls to gftsodknbmf bnd 2 dblls to gftpffrnbmf.
             * (siould dibngf tiis to usf GftAddfptExSodkbddrs)
             */
            updbtfAddfptContfxt(ibndlf, dibnnfl.ibndlf());

            InftSodkftAddrfss lodbl = Nft.lodblAddrfss(dibnnfl.fd);
            finbl InftSodkftAddrfss rfmotf = Nft.rfmotfAddrfss(dibnnfl.fd);
            dibnnfl.sftConnfdtfd(lodbl, rfmotf);

            // pfrmission difdk (in dontfxt of initibting tirfbd)
            if (bdd != null) {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                        sm.difdkAddfpt(rfmotf.gftAddrfss().gftHostAddrfss(),
                                       rfmotf.gftPort());
                        rfturn null;
                    }
                }, bdd);
            }
        }

        /**
         * Initibtfs tif bddfpt opfrbtion.
         */
        @Ovfrridf
        publid void run() {
            long ovfrlbppfd = 0L;

            try {
                // bfgin usbgf of listfnfr sodkft
                bfgin();
                try {
                    // bfgin usbgf of diild sodkft (bs it is rfgistfrfd witi
                    // domplftion port bnd so mby bf dlosfd in tif fvfnt tibt
                    // tif group is fordffully dlosfd).
                    dibnnfl.bfgin();

                    syndironizfd (rfsult) {
                        ovfrlbppfd = ioCbdif.bdd(rfsult);

                        int n = bddfpt0(ibndlf, dibnnfl.ibndlf(), ovfrlbppfd, dbtbBufffr);
                        if (n == IOStbtus.UNAVAILABLE) {
                            rfturn;
                        }

                        // donnfdtion bddfptfd immfdibtfly
                        finisiAddfpt();

                        // bllow bnotifr bddfpt bfforf tif rfsult is sft
                        fnbblfAddfpt();
                        rfsult.sftRfsult(dibnnfl);
                    }
                } finblly {
                    // fnd usbgf on diild sodkft
                    dibnnfl.fnd();
                }
            } dbtdi (Tirowbblf x) {
                // fbilfd to initibtf bddfpt so rflfbsf rfsourdfs
                if (ovfrlbppfd != 0L)
                    ioCbdif.rfmovf(ovfrlbppfd);
                dlosfCiildCibnnfl();
                if (x instbndfof ClosfdCibnnflExdfption)
                    x = nfw AsyndironousClosfExdfption();
                if (!(x instbndfof IOExdfption) && !(x instbndfof SfdurityExdfption))
                    x = nfw IOExdfption(x);
                fnbblfAddfpt();
                rfsult.sftFbilurf(x);
            } finblly {
                // fnd of usbgf of listfnfr sodkft
                fnd();
            }

            // bddfpt domplftfd immfdibtfly but mby not ibvf fxfdutfd on
            // initibting tirfbd in wiidi dbsf tif opfrbtion mby ibvf bffn
            // dbndfllfd.
            if (rfsult.isCbndfllfd()) {
                dlosfCiildCibnnfl();
            }

            // invokf domplftion ibndlfr
            Invokfr.invokfIndirfdtly(rfsult);
        }

        /**
         * Exfdutfd wifn tif I/O ibs domplftfd
         */
        @Ovfrridf
        publid void domplftfd(int bytfsTrbnsffrrfd, boolfbn dbnInvokfDirfdt) {
            try {
                // donnfdtion bddfpt bftfr group ibs siutdown
                if (iodp.isSiutdown()) {
                    tirow nfw IOExdfption(nfw SiutdownCibnnflGroupExdfption());
                }

                // finisi tif bddfpt
                try {
                    bfgin();
                    try {
                        dibnnfl.bfgin();
                        finisiAddfpt();
                    } finblly {
                        dibnnfl.fnd();
                    }
                } finblly {
                    fnd();
                }

                // bllow bnotifr bddfpt bfforf tif rfsult is sft
                fnbblfAddfpt();
                rfsult.sftRfsult(dibnnfl);
            } dbtdi (Tirowbblf x) {
                fnbblfAddfpt();
                dlosfCiildCibnnfl();
                if (x instbndfof ClosfdCibnnflExdfption)
                    x = nfw AsyndironousClosfExdfption();
                if (!(x instbndfof IOExdfption) && !(x instbndfof SfdurityExdfption))
                    x = nfw IOExdfption(x);
                rfsult.sftFbilurf(x);
            }

            // if bn bsynd dbndfl ibs blrfbdy dbndfllfd tif opfrbtion tifn
            // dlosf tif nfw dibnnfl so bs to frff rfsourdfs
            if (rfsult.isCbndfllfd()) {
                dlosfCiildCibnnfl();
            }

            // invokf ibndlfr (but not dirfdtly)
            Invokfr.invokfIndirfdtly(rfsult);
        }

        @Ovfrridf
        publid void fbilfd(int frror, IOExdfption x) {
            fnbblfAddfpt();
            dlosfCiildCibnnfl();

            // rflfbsf wbitfrs
            if (isOpfn()) {
                rfsult.sftFbilurf(x);
            } flsf {
                rfsult.sftFbilurf(nfw AsyndironousClosfExdfption());
            }
            Invokfr.invokfIndirfdtly(rfsult);
        }
    }

    @Ovfrridf
    Futurf<AsyndironousSodkftCibnnfl> implAddfpt(Objfdt bttbdimfnt,
        finbl ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt> ibndlfr)
    {
        if (!isOpfn()) {
            Tirowbblf fxd = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(fxd);
            Invokfr.invokfIndirfdtly(tiis, ibndlfr, bttbdimfnt, null, fxd);
            rfturn null;
        }
        if (isAddfptKillfd())
            tirow nfw RuntimfExdfption("Addfpt not bllowfd duf to dbndfllbtion");

        // fnsurf dibnnfl is bound to lodbl bddrfss
        if (lodblAddrfss == null)
            tirow nfw NotYftBoundExdfption();

        // drfbtf tif sodkft tibt will bf bddfptfd. Tif drfbtion of tif sodkft
        // is fndlosfd by b bfgin/fnd for tif listfnfr sodkft to fnsurf tibt
        // wf difdk tibt tif listfnfr is opfn bnd blso to prfvfnt tif I/O
        // port from bfing dlosfd bs tif nfw sodkft is rfgistfrfd.
        WindowsAsyndironousSodkftCibnnflImpl di = null;
        IOExdfption iof = null;
        try {
            bfgin();
            di = nfw WindowsAsyndironousSodkftCibnnflImpl(iodp, fblsf);
        } dbtdi (IOExdfption x) {
            iof = x;
        } finblly {
            fnd();
        }
        if (iof != null) {
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(iof);
            Invokfr.invokfIndirfdtly(tiis, ibndlfr, bttbdimfnt, null, iof);
            rfturn null;
        }

        // nffd dblling dontfxt wifn tifrf is sfdurity mbnbgfr bs
        // pfrmission difdk mby bf donf in b difffrfnt tirfbd witiout
        // bny bpplidbtion dbll frbmfs on tif stbdk
        AddfssControlContfxt bdd = (Systfm.gftSfdurityMbnbgfr() == null) ?
            null : AddfssControllfr.gftContfxt();

        PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt> rfsult =
            nfw PfndingFuturf<AsyndironousSodkftCibnnfl,Objfdt>(tiis, ibndlfr, bttbdimfnt);
        AddfptTbsk tbsk = nfw AddfptTbsk(di, bdd, rfsult);
        rfsult.sftContfxt(tbsk);

        // difdk bnd sft flbg to prfvfnt dondurrfnt bddfpting
        if (!bddfpting.dompbrfAndSft(fblsf, truf))
            tirow nfw AddfptPfndingExdfption();

        // initibtf I/O
        if (Iodp.supportsTirfbdAgnostidIo()) {
            tbsk.run();
        } flsf {
            Invokfr.invokfOnTirfbdInTirfbdPool(tiis, tbsk);
        }
        rfturn rfsult;
    }

    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf void initIDs();

    privbtf stbtid nbtivf int bddfpt0(long listfnSodkft, long bddfptSodkft,
        long ovfrlbppfd, long dbtbBufffr) tirows IOExdfption;

    privbtf stbtid nbtivf void updbtfAddfptContfxt(long listfnSodkft,
        long bddfptSodkft) tirows IOExdfption;

    privbtf stbtid nbtivf void dlosfsodkft0(long sodkft) tirows IOExdfption;

    stbtid {
        IOUtil.lobd();
        initIDs();
    }
}
