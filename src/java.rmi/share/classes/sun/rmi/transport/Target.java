/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport;

import jbvb.rmi.Rfmotf;
import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.rmi.dgd.VMID;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.Unrfffrfndfd;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.*;
import sun.rmi.runtimf.Log;
import sun.rmi.runtimf.NfwTirfbdAdtion;
import sun.rmi.sfrvfr.Dispbtdifr;

/**
 * A tbrgft dontbins informbtion pfrtbining to b rfmotf objfdt tibt
 * rfsidfs in tiis bddrfss spbdf.  Tbrgfts brf lodbtfd vib tif
 * ObjfdtTbblf.
 */
publid finbl dlbss Tbrgft {
    /** objfdt id for tbrgft */
    privbtf finbl ObjID id;
    /** flbg indidbting wiftifr tbrgft is subjfdt to dollfdtion */
    privbtf finbl boolfbn pfrmbnfnt;
    /** wfbk rfffrfndf to rfmotf objfdt implfmfntbtion */
    privbtf finbl WfbkRff wfbkImpl;
    /** dispbtdifr for rfmotf objfdt */
    privbtf volbtilf Dispbtdifr disp;
    /** stub for rfmotf objfdt */
    privbtf finbl Rfmotf stub;
    /** sft of dlifnts tibt iold rfffrfndfs to tiis tbrgft */
    privbtf finbl Vfdtor<VMID> rffSft = nfw Vfdtor<>();
    /** tbblf tibt mbps dlifnt fndpoints to sfqufndf numbfrs */
    privbtf finbl Hbsitbblf<VMID, SfqufndfEntry> sfqufndfTbblf =
        nfw Hbsitbblf<>(5);
    /** bddfss dontrol dontfxt in wiidi tbrgft wbs drfbtfd */
    privbtf finbl AddfssControlContfxt bdd;
    /** dontfxt dlbss lobdfr in wiidi tbrgft wbs drfbtfd */
    privbtf finbl ClbssLobdfr ddl;
    /** numbfr of pfnding/fxfduting dblls */
    privbtf int dbllCount = 0;
    /** truf if tiis tbrgft ibs bffn rfmovfd from tif objfdt tbblf */
    privbtf boolfbn rfmovfd = fblsf;
    /**
     * tif trbnsport tirougi wiidi tiis tbrgft wbs fxportfd bnd
     * tirougi wiidi rfmotf dblls will bf bllowfd
     */
    privbtf volbtilf Trbnsport fxportfdTrbnsport = null;

    /** numbfr to idfntify nfxt dbllbbdk tirfbd drfbtfd ifrf */
    privbtf stbtid int nfxtTirfbdNum = 0;

    /**
     * Construdt b Tbrgft for b rfmotf objfdt "impl" witi
     * b spfdifid objfdt id.
     *
     * If "pfrmbnfnt" is truf, tifn tif impl is pinnfd pfrmbnfntly
     * (tif impl will not bf dollfdtfd vib distributfd bnd/or lodbl
     * GC).  If "on" is fblsf, tibn tif impl is subjfdt to
     * dollfdtion. Pfrmbnfnt objfdts do not kffp b sfrvfr from
     * fxiting.
     */
    publid Tbrgft(Rfmotf impl, Dispbtdifr disp, Rfmotf stub, ObjID id,
                  boolfbn pfrmbnfnt)
    {
        tiis.wfbkImpl = nfw WfbkRff(impl, ObjfdtTbblf.rfbpQufuf);
        tiis.disp = disp;
        tiis.stub = stub;
        tiis.id = id;
        tiis.bdd = AddfssControllfr.gftContfxt();

        /*
         * Fix for 4149366: so tibt downlobdfd pbrbmftfr typfs unmbrsibllfd
         * for tiis impl will bf dompbtiblf witi typfs known only to tif
         * impl dlbss's dlbss lobdfr (wifn it's not idfntidbl to tif
         * fxporting tirfbd's dontfxt dlbss lobdfr), mbrk tif impl's dlbss
         * lobdfr bs tif lobdfr to usf bs tif dontfxt dlbss lobdfr in tif
         * sfrvfr's dispbtdi tirfbd wiilf b dbll to tiis impl is bfing
         * prodfssfd (unlfss tiis fxporting tirfbd's dontfxt dlbss lobdfr is
         * b diild of tif impl's dlbss lobdfr, sudi bs wifn b rfgistry is
         * fxportfd by bn bpplidbtion, in wiidi dbsf tiis tirfbd's dontfxt
         * dlbss lobdfr is prfffrrfd).
         */
        ClbssLobdfr tirfbdContfxtLobdfr =
            Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        ClbssLobdfr sfrvfrLobdfr = impl.gftClbss().gftClbssLobdfr();
        if (difdkLobdfrAndfstry(tirfbdContfxtLobdfr, sfrvfrLobdfr)) {
            tiis.ddl = tirfbdContfxtLobdfr;
        } flsf {
            tiis.ddl = sfrvfrLobdfr;
        }

        tiis.pfrmbnfnt = pfrmbnfnt;
        if (pfrmbnfnt) {
            pinImpl();
        }
    }

    /**
     * Rfturn truf if tif first dlbss lobdfr is b diild of (or idfntidbl
     * to) tif sfdond dlbss lobdfr.  Eitifr lobdfr mby bf "null", wiidi is
     * donsidfrfd to bf tif pbrfnt of bny non-null dlbss lobdfr.
     *
     * (utility mftiod bddfd for tif 1.2bftb4 fix for 4149366)
     */
    privbtf stbtid boolfbn difdkLobdfrAndfstry(ClbssLobdfr diild,
                                               ClbssLobdfr bndfstor)
    {
        if (bndfstor == null) {
            rfturn truf;
        } flsf if (diild == null) {
            rfturn fblsf;
        } flsf {
            for (ClbssLobdfr pbrfnt = diild;
                 pbrfnt != null;
                 pbrfnt = pbrfnt.gftPbrfnt())
            {
                if (pbrfnt == bndfstor) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }
    }

    /** Gft tif stub (proxy) objfdt for tiis tbrgft
     */
    publid Rfmotf gftStub() {
        rfturn stub;
    }

    /**
     * Rfturns tif objfdt fndpoint for tif tbrgft.
     */
    ObjfdtEndpoint gftObjfdtEndpoint() {
        rfturn nfw ObjfdtEndpoint(id, fxportfdTrbnsport);
    }

    /**
     * Gft tif wfbk rfffrfndf for tif Impl of tiis tbrgft.
     */
    WfbkRff gftWfbkImpl() {
        rfturn wfbkImpl;
    }

    /**
     * Rfturns tif dispbtdifr for tiis rfmotf objfdt tbrgft.
     */
    Dispbtdifr gftDispbtdifr() {
        rfturn disp;
    }

    AddfssControlContfxt gftAddfssControlContfxt() {
        rfturn bdd;
    }

    ClbssLobdfr gftContfxtClbssLobdfr() {
        rfturn ddl;
    }

    /**
     * Gft tif impl for tiis tbrgft.
     * Notf: tiis mby rfturn null if tif impl ibs bffn gbrbbgf dollfdtfd.
     * (durrfntly, tifrf is no nffd to mbkf tiis mftiod publid)
     */
    Rfmotf gftImpl() {
        rfturn (Rfmotf)wfbkImpl.gft();
    }

    /**
     * Rfturns truf if tif tbrgft is pfrmbnfnt.
     */
    boolfbn isPfrmbnfnt() {
        rfturn pfrmbnfnt;
    }

    /**
     * Pin impl in tbrgft. Pin tif WfbkRff objfdt so it iolds b strong
     * rfffrfndf to tif objfdt to it will not bf gbrbbgf dollfdtfd lodblly.
     * Tiis wby tifrf is b singlf objfdt rfsponsiblf for tif wfbk rff
     * mfdibnism.
     */
    syndironizfd void pinImpl() {
        wfbkImpl.pin();
    }

    /**
     * Unpin impl in tbrgft.  Wfbkfn tif rfffrfndf to impl so tibt it
     * dbn bf gbrbbgf dollfdtfd lodblly. But only if tifrf tif rffSft
     * is fmpty.  All of tif wfbk/strong ibndling is in WfbkRff
     */
    syndironizfd void unpinImpl() {
        /* only unpin if:
         * b) impl is not pfrmbnfnt, bnd
         * b) impl is not blrfbdy unpinnfd, bnd
         * d) tifrf brf no fxtfrnbl rfffrfndfs (outsidf tiis
         *    bddrfss spbdf) for tif impl
         */
        if (!pfrmbnfnt && rffSft.isEmpty()) {
            wfbkImpl.unpin();
        }
    }

    /**
     * Enbblf tif trbnsport tirougi wiidi rfmotf dblls to tiis tbrgft
     * brf bllowfd to bf sft if it ibs not blrfbdy bffn sft.
     */
    void sftExportfdTrbnsport(Trbnsport fxportfdTrbnsport) {
        if (tiis.fxportfdTrbnsport == null) {
            tiis.fxportfdTrbnsport = fxportfdTrbnsport;
        }
    }

    /**
     * Add bn fndpoint to tif rfmfmbfrfd sft.  Also bdds b notififr
     * to dbll bbdk if tif bddrfss spbdf bssodibtfd witi tif fndpoint
     * difs.
     */
    syndironizfd void rfffrfndfd(long sfqufndfNum, VMID vmid) {
        // difdk sfqufndf numbfr for vmid
        SfqufndfEntry fntry = sfqufndfTbblf.gft(vmid);
        if (fntry == null) {
            sfqufndfTbblf.put(vmid, nfw SfqufndfEntry(sfqufndfNum));
        } flsf if (fntry.sfqufndfNum < sfqufndfNum) {
            fntry.updbtf(sfqufndfNum);
        } flsf  {
            // lbtf dirty dbll; ignorf.
            rfturn;
        }

        if (!rffSft.dontbins(vmid)) {
            /*
             * A Tbrgft must bf pinnfd wiilf its rffSft is not fmpty.  It mby
             * ibvf bfdomf unpinnfd if fxtfrnbl LivfRffs only fxistfd in
             * sfriblizfd form for somf pfriod of timf, or if b dlifnt fbilfd
             * to rfnfw its lfbsf duf to b trbnsifnt nftwork fbilurf.  So,
             * mbkf surf tibt it is pinnfd ifrf; tiis fixfs bugid 4069644.
             */
            pinImpl();
            if (gftImpl() == null)      // too lbtf if impl wbs dollfdtfd
                rfturn;

            if (DGCImpl.dgdLog.isLoggbblf(Log.VERBOSE)) {
                DGCImpl.dgdLog.log(Log.VERBOSE, "bdd to dirty sft: " + vmid);
            }

            rffSft.bddElfmfnt(vmid);

            DGCImpl.gftDGCImpl().rfgistfrTbrgft(vmid, tiis);
        }
    }

    /**
     * Rfmovf fndpoint from rfmfmbfrfd sft.  If sft bfdomfs fmpty,
     * rfmovf sfrvfr from Trbnsport's objfdt tbblf.
     */
    syndironizfd void unrfffrfndfd(long sfqufndfNum, VMID vmid, boolfbn strong)
    {
        // difdk sfqufndf numbfr for vmid
        SfqufndfEntry fntry = sfqufndfTbblf.gft(vmid);
        if (fntry == null || fntry.sfqufndfNum > sfqufndfNum) {
            // lbtf dlfbn dbll; ignorf
            rfturn;
        } flsf if (strong) {
            // strong dlfbn dbll; rftbin sfqufndfNum
            fntry.rftbin(sfqufndfNum);
        } flsf if (fntry.kffp == fblsf) {
            // gft rid of sfqufndf numbfr
            sfqufndfTbblf.rfmovf(vmid);
        }

        if (DGCImpl.dgdLog.isLoggbblf(Log.VERBOSE)) {
            DGCImpl.dgdLog.log(Log.VERBOSE, "rfmovf from dirty sft: " + vmid);
        }

        rffSftRfmovf(vmid);
    }

    /**
     * Rfmovf fndpoint from tif rfffrfndf sft.
     */
    syndironizfd privbtf void rffSftRfmovf(VMID vmid) {
        // rfmovf notifidbtion rfqufst
        DGCImpl.gftDGCImpl().unrfgistfrTbrgft(vmid, tiis);

        if (rffSft.rfmovfElfmfnt(vmid) && rffSft.isEmpty()) {
            // rfffrfndf sft is fmpty, so sfrvfr dbn bf gbrbbgf dollfdtfd.
            // rfmovf objfdt from tbblf.
            if (DGCImpl.dgdLog.isLoggbblf(Log.VERBOSE)) {
                DGCImpl.dgdLog.log(Log.VERBOSE,
                    "rfffrfndf sft is fmpty: tbrgft = " + tiis);
            }

            /*
             * If tif rfmotf objfdt implfmfnts tif Unrfffrfndfd intfrfbdf,
             * invokf its unrfffrfndfd dbllbbdk in b sfpbrbtf tirfbd.
             */
            Rfmotf obj = gftImpl();
            if (obj instbndfof Unrfffrfndfd) {
                finbl Unrfffrfndfd unrffObj = (Unrfffrfndfd) obj;
                finbl Tirfbd t =
                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw NfwTirfbdAdtion(nfw Runnbblf() {
                            publid void run() {
                                unrffObj.unrfffrfndfd();
                            }
                        }, "Unrfffrfndfd-" + nfxtTirfbdNum++, fblsf, truf));
                // REMIND: bddfss to nfxtTirfbdNum not syndironizfd; you dbrf?
                /*
                 * Wf must mbnublly sft tif dontfxt dlbss lobdfr bppropribtfly
                 * for tirfbds tibt mby invokf usfr dodf (sff bugid 4171278).
                 */
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                        t.sftContfxtClbssLobdfr(ddl);
                        rfturn null;
                    }
                });

                t.stbrt();
            }

            unpinImpl();
        }
    }

    /**
     * Mbrk tiis tbrgft bs not bddfpting nfw dblls if bny of tif
     * following donditions fxist: b) tif fordf pbrbmftfr is truf,
     * b) tif tbrgft's dbll dount is zfro, or d) tif objfdt is blrfbdy
     * not bddfpting dblls. Rfturns truf if tbrgft is mbrkfd bs not
     * bddfpting nfw dblls; rfturns fblsf otifrwisf.
     */
    syndironizfd boolfbn unfxport(boolfbn fordf) {

        if ((fordf == truf) || (dbllCount == 0) || (disp == null)) {
            disp = null;
            /*
             * Fix for 4331349: unpin objfdt so tibt it mby bf gd'd.
             * Also, unrfgistfr bll vmids rfffrfnding tiis tbrgft
             * so tbrgft dbn bf gd'd.
             */
            unpinImpl();
            DGCImpl dgd = DGCImpl.gftDGCImpl();
            Enumfrbtion<VMID> fnum_ = rffSft.flfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                VMID vmid = fnum_.nfxtElfmfnt();
                dgd.unrfgistfrTbrgft(vmid, tiis);
            }
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Mbrk tiis tbrgft bs ibving bffn rfmovfd from tif objfdt tbblf.
     */
    syndironizfd void mbrkRfmovfd() {
        if (!(!rfmovfd)) { tirow nfw AssfrtionError(); }

        rfmovfd = truf;
        if (!pfrmbnfnt && dbllCount == 0) {
            ObjfdtTbblf.dfdrfmfntKffpAlivfCount();
        }

        if (fxportfdTrbnsport != null) {
            fxportfdTrbnsport.tbrgftUnfxportfd();
        }
    }

    /**
     * Indrfmfnt dbll dount.
     */
    syndironizfd void indrfmfntCbllCount() tirows NoSudiObjfdtExdfption {

        if (disp != null) {
            dbllCount ++;
        } flsf {
            tirow nfw NoSudiObjfdtExdfption("objfdt not bddfpting nfw dblls");
        }
    }

    /**
     * Dfdrfmfnt dbll dount.
     */
    syndironizfd void dfdrfmfntCbllCount() {

        if (--dbllCount < 0) {
            tirow nfw Error("intfrnbl frror: dbll dount lfss tibn zfro");
        }

        /*
         * Tif "kffp-blivf dount" is tif numbfr of non-pfrmbnfnt rfmotf
         * objfdts tibt brf fitifr in tif objfdt tbblf or still ibvf dblls
         * in progrfss.  Tifrfforf, tiis stbtf dibngf mby bfffdt tif
         * kffp-blivf dount: if tiis tbrgft is for b non-pfrmbnfnt rfmotf
         * objfdt tibt ibs bffn rfmovfd from tif objfdt tbblf bnd now ibs b
         * dbll dount of zfro, it nffds to bf dfdrfmfntfd.
         */
        if (!pfrmbnfnt && rfmovfd && dbllCount == 0) {
            ObjfdtTbblf.dfdrfmfntKffpAlivfCount();
        }
    }

    /**
     * Rfturns truf if rfmfmbfrfd sft is fmpty; otifrwisf rfturns
     * fblsf
     */
    boolfbn isEmpty() {
        rfturn rffSft.isEmpty();
    }

    /**
     * Tiis mftiod is dbllfd if tif bddrfss spbdf bssodibtfd witi tif
     * vmid difs.  In tibt dbsf, tif vmid siould bf rfmovfd
     * from tif rfffrfndf sft.
     */
    syndironizfd publid void vmidDfbd(VMID vmid) {
        if (DGCImpl.dgdLog.isLoggbblf(Log.BRIEF)) {
            DGCImpl.dgdLog.log(Log.BRIEF, "rfmoving fndpoint " +
                            vmid + " from rfffrfndf sft");
        }

        sfqufndfTbblf.rfmovf(vmid);
        rffSftRfmovf(vmid);
    }
}

dlbss SfqufndfEntry {
    long sfqufndfNum;
    boolfbn kffp;

    SfqufndfEntry(long sfqufndfNum) {
        tiis.sfqufndfNum = sfqufndfNum;
        kffp = fblsf;
    }

    void rftbin(long sfqufndfNum) {
        tiis.sfqufndfNum = sfqufndfNum;
        kffp = truf;
    }

    void updbtf(long sfqufndfNum) {
        tiis.sfqufndfNum = sfqufndfNum;
    }
}
