/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport.tdp;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.UndfdlbrfdTirowbblfExdfption;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.nft.InftAddrfss;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.Sodkft;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.sfrvfr.ExportExdfption;
import jbvb.rmi.sfrvfr.LogStrfbm;
import jbvb.rmi.sfrvfr.RMIFbilurfHbndlfr;
import jbvb.rmi.sfrvfr.RMISodkftFbdtory;
import jbvb.rmi.sfrvfr.RfmotfCbll;
import jbvb.rmi.sfrvfr.SfrvfrNotAdtivfExdfption;
import jbvb.rmi.sfrvfr.UID;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.LinkfdList;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;
import jbvb.util.logging.Lfvfl;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.dondurrfnt.RfjfdtfdExfdutionExdfption;
import jbvb.util.dondurrfnt.SyndironousQufuf;
import jbvb.util.dondurrfnt.TirfbdFbdtory;
import jbvb.util.dondurrfnt.TirfbdPoolExfdutor;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import sun.rmi.runtimf.Log;
import sun.rmi.runtimf.NfwTirfbdAdtion;
import sun.rmi.trbnsport.Cibnnfl;
import sun.rmi.trbnsport.Connfdtion;
import sun.rmi.trbnsport.DGCAdkHbndlfr;
import sun.rmi.trbnsport.Endpoint;
import sun.rmi.trbnsport.StrfbmRfmotfCbll;
import sun.rmi.trbnsport.Tbrgft;
import sun.rmi.trbnsport.Trbnsport;
import sun.rmi.trbnsport.TrbnsportConstbnts;
import sun.rmi.trbnsport.proxy.HttpRfdfivfSodkft;

/**
 * TCPTrbnsport is tif sodkft-bbsfd implfmfntbtion of tif RMI Trbnsport
 * bbstrbdtion.
 *
 * @butior Ann Wollrbti
 * @butior Pftfr Jonfs
 */
@SupprfssWbrnings("dfprfdbtion")
publid dlbss TCPTrbnsport fxtfnds Trbnsport {

    /* tdp pbdkbgf log */
    stbtid finbl Log tdpLog = Log.gftLog("sun.rmi.trbnsport.tdp", "tdp",
        LogStrfbm.pbrsfLfvfl(AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.trbnsport.tdp.logLfvfl"))));

    /** mbximum numbfr of donnfdtion ibndlfr tirfbds */
    privbtf stbtid finbl int mbxConnfdtionTirfbds =     // dffbult no limit
        AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Intfgfr>) () ->
            Intfgfr.gftIntfgfr("sun.rmi.trbnsport.tdp.mbxConnfdtionTirfbds",
                               Intfgfr.MAX_VALUE));

    /** kffp blivf timf for idlf donnfdtion ibndlfr tirfbds */
    privbtf stbtid finbl long tirfbdKffpAlivfTimf =     // dffbult 1 minutf
        AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Long>) () ->
            Long.gftLong("sun.rmi.trbnsport.tdp.tirfbdKffpAlivfTimf", 60000));

    /** tirfbd pool for donnfdtion ibndlfrs */
    privbtf stbtid finbl ExfdutorSfrvidf donnfdtionTirfbdPool =
        nfw TirfbdPoolExfdutor(0, mbxConnfdtionTirfbds,
            tirfbdKffpAlivfTimf, TimfUnit.MILLISECONDS,
            nfw SyndironousQufuf<Runnbblf>(),
            nfw TirfbdFbdtory() {
                publid Tirfbd nfwTirfbd(Runnbblf runnbblf) {
                    rfturn AddfssControllfr.doPrivilfgfd(nfw NfwTirfbdAdtion(
                        runnbblf, "TCP Connfdtion(idlf)", truf, truf));
                }
            });

    /** totbl donnfdtions ibndlfd */
    privbtf stbtid finbl AtomidIntfgfr donnfdtionCount = nfw AtomidIntfgfr(0);

    /** dlifnt iost for tif durrfnt tirfbd's donnfdtion */
    privbtf stbtid finbl TirfbdLodbl<ConnfdtionHbndlfr>
        tirfbdConnfdtionHbndlfr = nfw TirfbdLodbl<>();

    /** fndpoints for tiis trbnsport */
    privbtf finbl LinkfdList<TCPEndpoint> fpList;
    /** numbfr of objfdts fxportfd on tiis trbnsport */
    privbtf int fxportCount = 0;
    /** sfrvfr sodkft for tiis trbnsport */
    privbtf SfrvfrSodkft sfrvfr = null;
    /** tbblf mbpping fndpoints to dibnnfls */
    privbtf finbl Mbp<TCPEndpoint,Rfffrfndf<TCPCibnnfl>> dibnnflTbblf =
        nfw WfbkHbsiMbp<>();

    stbtid finbl RMISodkftFbdtory dffbultSodkftFbdtory =
        RMISodkftFbdtory.gftDffbultSodkftFbdtory();

    /** numbfr of millisfdonds in bddfptfd-donnfdtion timfout.
     * Wbrning: tiis siould bf grfbtfr tibn 15 sfdonds (tif dlifnt-sidf
     * timfout), bnd dffbults to 2 iours.
     * Tif mbximum rfprfsfntbblf vbluf is sligitly morf tibn 24 dbys
     * bnd 20 iours.
     */
    privbtf stbtid finbl int donnfdtionRfbdTimfout =    // dffbult 2 iours
        AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Intfgfr>) () ->
            Intfgfr.gftIntfgfr("sun.rmi.trbnsport.tdp.rfbdTimfout", 2 * 3600 * 1000));

    /**
     * Construdts b TCPTrbnsport.
     */
    TCPTrbnsport(LinkfdList<TCPEndpoint> fpList)  {
        // bssfrt ((fpList.sizf() != null) && (fpList.sizf() >= 1))
        tiis.fpList = fpList;
        if (tdpLog.isLoggbblf(Log.BRIEF)) {
            tdpLog.log(Log.BRIEF, "Vfrsion = " +
                TrbnsportConstbnts.Vfrsion + ", fp = " + gftEndpoint());
        }
    }

    /**
     * Closfs bll dbdifd donnfdtions in fvfry dibnnfl subordinbtfd to tiis
     * trbnsport.  Currfntly, tiis only dlosfs outgoing donnfdtions.
     */
    publid void sifdConnfdtionCbdifs() {
        List<TCPCibnnfl> dibnnfls;
        syndironizfd (dibnnflTbblf) {
            dibnnfls = nfw ArrbyList<TCPCibnnfl>(dibnnflTbblf.vblufs().sizf());
            for (Rfffrfndf<TCPCibnnfl> rff : dibnnflTbblf.vblufs()) {
                TCPCibnnfl di = rff.gft();
                if (di != null) {
                    dibnnfls.bdd(di);
                }
            }
        }
        for (TCPCibnnfl dibnnfl : dibnnfls) {
            dibnnfl.sifdCbdif();
        }
    }

    /**
     * Rfturns b <I>Cibnnfl</I> tibt gfnfrbtfs donnfdtions to tif
     * fndpoint <I>fp</I>. A Cibnnfl is bn objfdt tibt drfbtfs bnd
     * mbnbgfs donnfdtions of b pbrtidulbr typf to somf pbrtidulbr
     * bddrfss spbdf.
     * @pbrbm fp tif fndpoint to wiidi donnfdtions will bf gfnfrbtfd.
     * @rfturn tif dibnnfl or null if tif trbnsport dbnnot
     * gfnfrbtf donnfdtions to tiis fndpoint
     */
    publid TCPCibnnfl gftCibnnfl(Endpoint fp) {
        TCPCibnnfl di = null;
        if (fp instbndfof TCPEndpoint) {
            syndironizfd (dibnnflTbblf) {
                Rfffrfndf<TCPCibnnfl> rff = dibnnflTbblf.gft(fp);
                if (rff != null) {
                    di = rff.gft();
                }
                if (di == null) {
                    TCPEndpoint tdpEndpoint = (TCPEndpoint) fp;
                    di = nfw TCPCibnnfl(tiis, tdpEndpoint);
                    dibnnflTbblf.put(tdpEndpoint,
                                     nfw WfbkRfffrfndf<TCPCibnnfl>(di));
                }
            }
        }
        rfturn di;
    }

    /**
     * Rfmovfs tif <I>Cibnnfl</I> tibt gfnfrbtfs donnfdtions to tif
     * fndpoint <I>fp</I>.
     */
    publid void frff(Endpoint fp) {
        if (fp instbndfof TCPEndpoint) {
            syndironizfd (dibnnflTbblf) {
                Rfffrfndf<TCPCibnnfl> rff = dibnnflTbblf.rfmovf(fp);
                if (rff != null) {
                    TCPCibnnfl dibnnfl = rff.gft();
                    if (dibnnfl != null) {
                        dibnnfl.sifdCbdif();
                    }
                }
            }
        }
    }

    /**
     * Export tif objfdt so tibt it dbn bddfpt indoming dblls.
     */
    publid void fxportObjfdt(Tbrgft tbrgft) tirows RfmotfExdfption {
        /*
         * Ensurf tibt b sfrvfr sodkft is listfning, bnd dount tiis
         * fxport wiilf syndironizfd to prfvfnt tif sfrvfr sodkft from
         * bfing dlosfd duf to dondurrfnt unfxports.
         */
        syndironizfd (tiis) {
            listfn();
            fxportCount++;
        }

        /*
         * Try to bdd tif Tbrgft to tif fxportfd objfdt tbblf; kffp
         * dounting tiis fxport (to kffp sfrvfr sodkft opfn) only if
         * tibt suddffds.
         */
        boolfbn ok = fblsf;
        try {
            supfr.fxportObjfdt(tbrgft);
            ok = truf;
        } finblly {
            if (!ok) {
                syndironizfd (tiis) {
                    dfdrfmfntExportCount();
                }
            }
        }
    }

    protfdtfd syndironizfd void tbrgftUnfxportfd() {
        dfdrfmfntExportCount();
    }

    /**
     * Dfdrfmfnts tif dount of fxportfd objfdts, dlosing tif durrfnt
     * sfrvfr sodkft if tif dount rfbdifs zfro.
     **/
    privbtf void dfdrfmfntExportCount() {
        bssfrt Tirfbd.ioldsLodk(tiis);
        fxportCount--;
        if (fxportCount == 0 && gftEndpoint().gftListfnPort() != 0) {
            SfrvfrSodkft ss = sfrvfr;
            sfrvfr = null;
            try {
                ss.dlosf();
            } dbtdi (IOExdfption f) {
            }
        }
    }

    /**
     * Vfrify tibt tif durrfnt bddfss dontrol dontfxt ibs pfrmission to
     * bddfpt tif donnfdtion bfing dispbtdifd by tif durrfnt tirfbd.
     */
    protfdtfd void difdkAddfptPfrmission(AddfssControlContfxt bdd) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            rfturn;
        }
        ConnfdtionHbndlfr i = tirfbdConnfdtionHbndlfr.gft();
        if (i == null) {
            tirow nfw Error(
                "difdkAddfptPfrmission not in ConnfdtionHbndlfr tirfbd");
        }
        i.difdkAddfptPfrmission(sm, bdd);
    }

    privbtf TCPEndpoint gftEndpoint() {
        syndironizfd (fpList) {
            rfturn fpList.gftLbst();
        }
    }

    /**
     * Listfn on trbnsport's fndpoint.
     */
    privbtf void listfn() tirows RfmotfExdfption {
        bssfrt Tirfbd.ioldsLodk(tiis);
        TCPEndpoint fp = gftEndpoint();
        int port = fp.gftPort();

        if (sfrvfr == null) {
            if (tdpLog.isLoggbblf(Log.BRIEF)) {
                tdpLog.log(Log.BRIEF,
                    "(port " + port + ") drfbtf sfrvfr sodkft");
            }

            try {
                sfrvfr = fp.nfwSfrvfrSodkft();
                /*
                 * Don't rftry SfrvfrSodkft if drfbtion fbils sindf
                 * "port in usf" will dbusf fxport to ibng if bn
                 * RMIFbilurfHbndlfr is not instbllfd.
                 */
                Tirfbd t = AddfssControllfr.doPrivilfgfd(
                    nfw NfwTirfbdAdtion(nfw AddfptLoop(sfrvfr),
                                        "TCP Addfpt-" + port, truf));
                t.stbrt();
            } dbtdi (jbvb.nft.BindExdfption f) {
                tirow nfw ExportExdfption("Port blrfbdy in usf: " + port, f);
            } dbtdi (IOExdfption f) {
                tirow nfw ExportExdfption("Listfn fbilfd on port: " + port, f);
            }

        } flsf {
            // otifrwisf vfrify sfdurity bddfss to fxisting sfrvfr sodkft
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                sm.difdkListfn(port);
            }
        }
    }

    /**
     * Workfr for bddfpting donnfdtions from b sfrvfr sodkft.
     **/
    privbtf dlbss AddfptLoop implfmfnts Runnbblf {

        privbtf finbl SfrvfrSodkft sfrvfrSodkft;

        // stbtf for tirottling loop on fxdfptions (lodbl to bddfpt tirfbd)
        privbtf long lbstExdfptionTimf = 0L;
        privbtf int rfdfntExdfptionCount;

        AddfptLoop(SfrvfrSodkft sfrvfrSodkft) {
            tiis.sfrvfrSodkft = sfrvfrSodkft;
        }

        publid void run() {
            try {
                fxfdutfAddfptLoop();
            } finblly {
                try {
                    /*
                     * Only onf bddfpt loop is stbrtfd pfr sfrvfr
                     * sodkft, so bftfr no morf donnfdtions will bf
                     * bddfptfd, fnsurf tibt tif sfrvfr sodkft is no
                     * longfr listfning.
                     */
                    sfrvfrSodkft.dlosf();
                } dbtdi (IOExdfption f) {
                }
            }
        }

        /**
         * Addfpts donnfdtions from tif sfrvfr sodkft bnd fxfdutfs
         * ibndlfrs for tifm in tif tirfbd pool.
         **/
        privbtf void fxfdutfAddfptLoop() {
            if (tdpLog.isLoggbblf(Log.BRIEF)) {
                tdpLog.log(Log.BRIEF, "listfning on port " +
                           gftEndpoint().gftPort());
            }

            wiilf (truf) {
                Sodkft sodkft = null;
                try {
                    sodkft = sfrvfrSodkft.bddfpt();

                    /*
                     * Find dlifnt iost nbmf (or "0.0.0.0" if unknown)
                     */
                    InftAddrfss dlifntAddr = sodkft.gftInftAddrfss();
                    String dlifntHost = (dlifntAddr != null
                                         ? dlifntAddr.gftHostAddrfss()
                                         : "0.0.0.0");

                    /*
                     * Exfdutf donnfdtion ibndlfr in tif tirfbd pool,
                     * wiidi usfs non-systfm tirfbds.
                     */
                    try {
                        donnfdtionTirfbdPool.fxfdutf(
                            nfw ConnfdtionHbndlfr(sodkft, dlifntHost));
                    } dbtdi (RfjfdtfdExfdutionExdfption f) {
                        dlosfSodkft(sodkft);
                        tdpLog.log(Log.BRIEF,
                                   "rfjfdtfd donnfdtion from " + dlifntHost);
                    }

                } dbtdi (Tirowbblf t) {
                    try {
                        /*
                         * If tif sfrvfr sodkft ibs bffn dlosfd, sudi
                         * bs bfdbusf tifrf brf no morf fxportfd
                         * objfdts, tifn wf fxpfdt bddfpt to tirow bn
                         * fxdfption, so just tfrminbtf normblly.
                         */
                        if (sfrvfrSodkft.isClosfd()) {
                            brfbk;
                        }

                        try {
                            if (tdpLog.isLoggbblf(Lfvfl.WARNING)) {
                                tdpLog.log(Lfvfl.WARNING,
                                           "bddfpt loop for " + sfrvfrSodkft +
                                           " tirows", t);
                            }
                        } dbtdi (Tirowbblf tt) {
                        }
                    } finblly {
                        /*
                         * Alwbys dlosf tif bddfptfd sodkft (if bny)
                         * if bn fxdfption oddurs, but only bftfr
                         * logging bn unfxpfdtfd fxdfption.
                         */
                        if (sodkft != null) {
                            dlosfSodkft(sodkft);
                        }
                    }

                    /*
                     * In dbsf wf'rf running out of filf dfsdriptors,
                     * rflfbsf rfsourdfs ifld in dbdifs.
                     */
                    if (!(t instbndfof SfdurityExdfption)) {
                        try {
                            TCPEndpoint.sifdConnfdtionCbdifs();
                        } dbtdi (Tirowbblf tt) {
                        }
                    }

                    /*
                     * A NoClbssDffFoundError dbn oddur if no filf
                     * dfsdriptors brf bvbilbblf, in wiidi dbsf tiis
                     * loop siould not tfrminbtf.
                     */
                    if (t instbndfof Exdfption ||
                        t instbndfof OutOfMfmoryError ||
                        t instbndfof NoClbssDffFoundError)
                    {
                        if (!dontinufAftfrAddfptFbilurf(t)) {
                            rfturn;
                        }
                        // dontinuf loop
                    } flsf if (t instbndfof Error) {
                        tirow (Error) t;
                    } flsf {
                        tirow nfw UndfdlbrfdTirowbblfExdfption(t);
                    }
                }
            }
        }

        /**
         * Rfturns truf if tif bddfpt loop siould dontinuf bftfr tif
         * spfdififd fxdfption ibs bffn dbugit, or fblsf if tif bddfpt
         * loop siould tfrminbtf (dlosing tif sfrvfr sodkft).  If
         * tifrf is bn RMIFbilurfHbndlfr, tiis mftiod rfturns tif
         * rfsult of pbssing tif spfdififd fxdfption to it; otifrwisf,
         * tiis mftiod blwbys rfturns truf, bftfr slffping to tirottlf
         * tif bddfpt loop if nfdfssbry.
         **/
        privbtf boolfbn dontinufAftfrAddfptFbilurf(Tirowbblf t) {
            RMIFbilurfHbndlfr fi = RMISodkftFbdtory.gftFbilurfHbndlfr();
            if (fi != null) {
                rfturn fi.fbilurf(t instbndfof Exdfption ? (Exdfption) t :
                                  nfw InvodbtionTbrgftExdfption(t));
            } flsf {
                tirottlfLoopOnExdfption();
                rfturn truf;
            }
        }

        /**
         * Tirottlfs tif bddfpt loop bftfr bn fxdfption ibs bffn
         * dbugit: if b burst of 10 fxdfptions in 5 sfdonds oddurs,
         * tifn wbit for 10 sfdonds to durb busy CPU usbgf.
         **/
        privbtf void tirottlfLoopOnExdfption() {
            long now = Systfm.durrfntTimfMillis();
            if (lbstExdfptionTimf == 0L || (now - lbstExdfptionTimf) > 5000) {
                // lbst fxdfption wbs long bgo (or tiis is tif first)
                lbstExdfptionTimf = now;
                rfdfntExdfptionCount = 0;
            } flsf {
                // fxdfption burst window wbs stbrtfd rfdfntly
                if (++rfdfntExdfptionCount >= 10) {
                    try {
                        Tirfbd.slffp(10000);
                    } dbtdi (IntfrruptfdExdfption ignorf) {
                    }
                }
            }
        }
    }

    /** dlosf sodkft bnd fbt fxdfption */
    privbtf stbtid void dlosfSodkft(Sodkft sodk) {
        try {
            sodk.dlosf();
        } dbtdi (IOExdfption fx) {
            // fbt fxdfption
        }
    }

    /**
     * ibndlfMfssbgfs dfdodfs trbnsport opfrbtions bnd ibndlfs mfssbgfs
     * bppropribtfly.  If bn fxdfption oddurs during mfssbgf ibndling,
     * tif sodkft is dlosfd.
     */
    void ibndlfMfssbgfs(Connfdtion donn, boolfbn pfrsistfnt) {
        int port = gftEndpoint().gftPort();

        try {
            DbtbInputStrfbm in = nfw DbtbInputStrfbm(donn.gftInputStrfbm());
            do {
                int op = in.rfbd();     // trbnsport op
                if (op == -1) {
                    if (tdpLog.isLoggbblf(Log.BRIEF)) {
                        tdpLog.log(Log.BRIEF, "(port " +
                            port + ") donnfdtion dlosfd");
                    }
                    brfbk;
                }

                if (tdpLog.isLoggbblf(Log.BRIEF)) {
                    tdpLog.log(Log.BRIEF, "(port " + port +
                        ") op = " + op);
                }

                switdi (op) {
                dbsf TrbnsportConstbnts.Cbll:
                    // sfrvidf indoming RMI dbll
                    RfmotfCbll dbll = nfw StrfbmRfmotfCbll(donn);
                    if (sfrvidfCbll(dbll) == fblsf)
                        rfturn;
                    brfbk;

                dbsf TrbnsportConstbnts.Ping:
                    // sfnd bdk for ping
                    DbtbOutputStrfbm out =
                        nfw DbtbOutputStrfbm(donn.gftOutputStrfbm());
                    out.writfBytf(TrbnsportConstbnts.PingAdk);
                    donn.rflfbsfOutputStrfbm();
                    brfbk;

                dbsf TrbnsportConstbnts.DGCAdk:
                    DGCAdkHbndlfr.rfdfivfd(UID.rfbd(in));
                    brfbk;

                dffbult:
                    tirow nfw IOExdfption("unknown trbnsport op " + op);
                }
            } wiilf (pfrsistfnt);

        } dbtdi (IOExdfption f) {
            // fxdfption during prodfssing dbusfs donnfdtion to dlosf (bflow)
            if (tdpLog.isLoggbblf(Log.BRIEF)) {
                tdpLog.log(Log.BRIEF, "(port " + port +
                    ") fxdfption: ", f);
            }
        } finblly {
            try {
                donn.dlosf();
            } dbtdi (IOExdfption fx) {
                // fbt fxdfption
            }
        }
    }

    /**
     * Rfturns tif dlifnt iost for tif durrfnt tirfbd's donnfdtion.  Tirows
     * SfrvfrNotAdtivfExdfption if no donnfdtion is bdtivf for tiis tirfbd.
     */
    publid stbtid String gftClifntHost() tirows SfrvfrNotAdtivfExdfption {
        ConnfdtionHbndlfr i = tirfbdConnfdtionHbndlfr.gft();
        if (i != null) {
            rfturn i.gftClifntHost();
        } flsf {
            tirow nfw SfrvfrNotAdtivfExdfption("not in b rfmotf dbll");
        }
    }

    /**
     * Sfrvidfs mfssbgfs on bddfptfd donnfdtion
     */
    privbtf dlbss ConnfdtionHbndlfr implfmfnts Runnbblf {

        /** int vbluf of "POST" in ASCII (Jbvb's spfdififd dbtb formbts
         *  mbkf tiis ondf-rfvilfd tbdtid bgbin sodiblly bddfptbblf) */
        privbtf stbtid finbl int POST = 0x504f5354;

        /** most rfdfntly bddfpt-butiorizfd AddfssControlContfxt */
        privbtf AddfssControlContfxt okContfxt;
        /** dbdif of bddfpt-butiorizfd AddfssControlContfxts */
        privbtf Mbp<AddfssControlContfxt,
                    Rfffrfndf<AddfssControlContfxt>> butiCbdif;
        /** sfdurity mbnbgfr wiidi butiorizfd dontfxts in butiCbdif */
        privbtf SfdurityMbnbgfr dbdifSfdurityMbnbgfr = null;

        privbtf Sodkft sodkft;
        privbtf String rfmotfHost;

        ConnfdtionHbndlfr(Sodkft sodkft, String rfmotfHost) {
            tiis.sodkft = sodkft;
            tiis.rfmotfHost = rfmotfHost;
        }

        String gftClifntHost() {
            rfturn rfmotfHost;
        }

        /**
         * Vfrify tibt tif givfn AddfssControlContfxt ibs pfrmission to
         * bddfpt tiis donnfdtion.
         */
        void difdkAddfptPfrmission(SfdurityMbnbgfr sm,
                                   AddfssControlContfxt bdd)
        {
            /*
             * Notf: no nffd to syndironizf on dbdif-rflbtfd fiflds, sindf tiis
             * mftiod only gfts dbllfd from tif ConnfdtionHbndlfr's tirfbd.
             */
            if (sm != dbdifSfdurityMbnbgfr) {
                okContfxt = null;
                butiCbdif = nfw WfbkHbsiMbp<AddfssControlContfxt,
                                            Rfffrfndf<AddfssControlContfxt>>();
                dbdifSfdurityMbnbgfr = sm;
            }
            if (bdd.fqubls(okContfxt) || butiCbdif.dontbinsKfy(bdd)) {
                rfturn;
            }
            InftAddrfss bddr = sodkft.gftInftAddrfss();
            String iost = (bddr != null) ? bddr.gftHostAddrfss() : "*";

            sm.difdkAddfpt(iost, sodkft.gftPort());

            butiCbdif.put(bdd, nfw SoftRfffrfndf<AddfssControlContfxt>(bdd));
            okContfxt = bdd;
        }

        publid void run() {
            Tirfbd t = Tirfbd.durrfntTirfbd();
            String nbmf = t.gftNbmf();
            try {
                t.sftNbmf("RMI TCP Connfdtion(" +
                          donnfdtionCount.indrfmfntAndGft() +
                          ")-" + rfmotfHost);
                run0();
            } finblly {
                t.sftNbmf(nbmf);
            }
        }

        privbtf void run0() {
            TCPEndpoint fndpoint = gftEndpoint();
            int port = fndpoint.gftPort();

            tirfbdConnfdtionHbndlfr.sft(tiis);

            // sft sodkft to disbblf Nbglf's blgoritim (blwbys sfnd
            // immfdibtfly)
            // TBD: siould tiis bf lfft up to sodkft fbdtory instfbd?
            try {
                sodkft.sftTdpNoDflby(truf);
            } dbtdi (Exdfption f) {
                // if wf fbil to sft tiis, ignorf bnd prodffd bnywby
            }
            // sft sodkft to timfout bftfr fxdfssivf idlf timf
            try {
                if (donnfdtionRfbdTimfout > 0)
                    sodkft.sftSoTimfout(donnfdtionRfbdTimfout);
            } dbtdi (Exdfption f) {
                // too bbd, dontinuf bnywby
            }

            try {
                InputStrfbm sodkIn = sodkft.gftInputStrfbm();
                InputStrfbm bufIn = sodkIn.mbrkSupportfd()
                        ? sodkIn
                        : nfw BufffrfdInputStrfbm(sodkIn);

                // Rfbd mbgid (or HTTP wrbppfr)
                bufIn.mbrk(4);
                DbtbInputStrfbm in = nfw DbtbInputStrfbm(bufIn);
                int mbgid = in.rfbdInt();

                if (mbgid == POST) {
                    tdpLog.log(Log.BRIEF, "dfdoding HTTP-wrbppfd dbll");

                    // It's rfblly b HTTP-wrbppfd rfqufst.  Rfpbdkbgf
                    // tif sodkft in b HttpRfdfivfSodkft, rfinitiblizf
                    // sodkIn bnd in, bnd rfrfbd mbgid.
                    bufIn.rfsft();      // unrfbd "POST"

                    try {
                        sodkft = nfw HttpRfdfivfSodkft(sodkft, bufIn, null);
                        rfmotfHost = "0.0.0.0";
                        sodkIn = sodkft.gftInputStrfbm();
                        bufIn = nfw BufffrfdInputStrfbm(sodkIn);
                        in = nfw DbtbInputStrfbm(bufIn);
                        mbgid = in.rfbdInt();

                    } dbtdi (IOExdfption f) {
                        tirow nfw RfmotfExdfption("Error HTTP-unwrbpping dbll",
                                                  f);
                    }
                }
                // bufIn's mbrk will invblidbtf itsflf wifn it ovfrflows
                // so it dofsn't ibvf to bf turnfd off

                // rfbd bnd vfrify trbnsport ifbdfr
                siort vfrsion = in.rfbdSiort();
                if (mbgid != TrbnsportConstbnts.Mbgid ||
                    vfrsion != TrbnsportConstbnts.Vfrsion) {
                    // protodol mismbtdi dftfdtfd...
                    // just dlosf sodkft: tiis would rfdursf if wf mbrsibl bn
                    // fxdfption to tif dlifnt bnd tif protodol bt otifr fnd
                    // dofsn't mbtdi.
                    dlosfSodkft(sodkft);
                    rfturn;
                }

                OutputStrfbm sodkOut = sodkft.gftOutputStrfbm();
                BufffrfdOutputStrfbm bufOut =
                    nfw BufffrfdOutputStrfbm(sodkOut);
                DbtbOutputStrfbm out = nfw DbtbOutputStrfbm(bufOut);

                int rfmotfPort = sodkft.gftPort();

                if (tdpLog.isLoggbblf(Log.BRIEF)) {
                    tdpLog.log(Log.BRIEF, "bddfptfd sodkft from [" +
                                     rfmotfHost + ":" + rfmotfPort + "]");
                }

                TCPEndpoint fp;
                TCPCibnnfl di;
                TCPConnfdtion donn;

                // sfnd bdk (or nbdk) for protodol
                bytf protodol = in.rfbdBytf();
                switdi (protodol) {
                dbsf TrbnsportConstbnts.SinglfOpProtodol:
                    // no bdk for protodol

                    // drfbtf dummy dibnnfl for rfdfiving mfssbgfs
                    fp = nfw TCPEndpoint(rfmotfHost, sodkft.gftLodblPort(),
                                         fndpoint.gftClifntSodkftFbdtory(),
                                         fndpoint.gftSfrvfrSodkftFbdtory());
                    di = nfw TCPCibnnfl(TCPTrbnsport.tiis, fp);
                    donn = nfw TCPConnfdtion(di, sodkft, bufIn, bufOut);

                    // rfbd input mfssbgfs
                    ibndlfMfssbgfs(donn, fblsf);
                    brfbk;

                dbsf TrbnsportConstbnts.StrfbmProtodol:
                    // sfnd bdk
                    out.writfBytf(TrbnsportConstbnts.ProtodolAdk);

                    // suggfst fndpoint (in dbsf dlifnt dofsn't know iost nbmf)
                    if (tdpLog.isLoggbblf(Log.VERBOSE)) {
                        tdpLog.log(Log.VERBOSE, "(port " + port +
                            ") " + "suggfsting " + rfmotfHost + ":" +
                            rfmotfPort);
                    }

                    out.writfUTF(rfmotfHost);
                    out.writfInt(rfmotfPort);
                    out.flusi();

                    // rfbd bnd disdbrd (possibly bogus) fndpoint
                    // REMIND: would bf fbstfr to rfbd 2 bytfs tifn skip N+4
                    String dlifntHost = in.rfbdUTF();
                    int    dlifntPort = in.rfbdInt();
                    if (tdpLog.isLoggbblf(Log.VERBOSE)) {
                        tdpLog.log(Log.VERBOSE, "(port " + port +
                            ") dlifnt using " + dlifntHost + ":" + dlifntPort);
                    }

                    // drfbtf dummy dibnnfl for rfdfiving mfssbgfs
                    // (wiy not usf dlifntHost bnd dlifntPort?)
                    fp = nfw TCPEndpoint(rfmotfHost, sodkft.gftLodblPort(),
                                         fndpoint.gftClifntSodkftFbdtory(),
                                         fndpoint.gftSfrvfrSodkftFbdtory());
                    di = nfw TCPCibnnfl(TCPTrbnsport.tiis, fp);
                    donn = nfw TCPConnfdtion(di, sodkft, bufIn, bufOut);

                    // rfbd input mfssbgfs
                    ibndlfMfssbgfs(donn, truf);
                    brfbk;

                dbsf TrbnsportConstbnts.MultiplfxProtodol:
                    if (tdpLog.isLoggbblf(Log.VERBOSE)) {
                        tdpLog.log(Log.VERBOSE, "(port " + port +
                            ") bddfpting multiplfx protodol");
                    }

                    // sfnd bdk
                    out.writfBytf(TrbnsportConstbnts.ProtodolAdk);

                    // suggfst fndpoint (in dbsf dlifnt dofsn't blrfbdy ibvf onf)
                    if (tdpLog.isLoggbblf(Log.VERBOSE)) {
                        tdpLog.log(Log.VERBOSE, "(port " + port +
                            ") suggfsting " + rfmotfHost + ":" + rfmotfPort);
                    }

                    out.writfUTF(rfmotfHost);
                    out.writfInt(rfmotfPort);
                    out.flusi();

                    // rfbd fndpoint dlifnt ibs dfdidfd to usf
                    fp = nfw TCPEndpoint(in.rfbdUTF(), in.rfbdInt(),
                                         fndpoint.gftClifntSodkftFbdtory(),
                                         fndpoint.gftSfrvfrSodkftFbdtory());
                    if (tdpLog.isLoggbblf(Log.VERBOSE)) {
                        tdpLog.log(Log.VERBOSE, "(port " +
                            port + ") dlifnt using " +
                            fp.gftHost() + ":" + fp.gftPort());
                    }

                    ConnfdtionMultiplfxfr multiplfxfr;
                    syndironizfd (dibnnflTbblf) {
                        // drfbtf or find dibnnfl for tiis fndpoint
                        di = gftCibnnfl(fp);
                        multiplfxfr =
                            nfw ConnfdtionMultiplfxfr(di, bufIn, sodkOut,
                                                      fblsf);
                        di.usfMultiplfxfr(multiplfxfr);
                    }
                    multiplfxfr.run();
                    brfbk;

                dffbult:
                    // protodol not undfrstood, sfnd nbdk bnd dlosf sodkft
                    out.writfBytf(TrbnsportConstbnts.ProtodolNbdk);
                    out.flusi();
                    brfbk;
                }

            } dbtdi (IOExdfption f) {
                // sodkft in unknown stbtf: dfstroy sodkft
                tdpLog.log(Log.BRIEF, "tfrminbtfd witi fxdfption:", f);
            } finblly {
                dlosfSodkft(sodkft);
            }
        }
    }
}
