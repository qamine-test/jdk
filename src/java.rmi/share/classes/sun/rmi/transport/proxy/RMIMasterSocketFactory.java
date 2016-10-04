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
pbdkbgf sun.rmi.trbnsport.proxy;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.sfdurity.*;
import jbvb.util.*;
import jbvb.rmi.sfrvfr.LogStrfbm;
import jbvb.rmi.sfrvfr.RMISodkftFbdtory;
import sun.rmi.runtimf.Log;
import sun.rmi.runtimf.NfwTirfbdAdtion;

/**
 * RMIMbstfrSodkftFbdtory bttfmpts to drfbtf b sodkft donnfdtion to tif
 * spfdififd iost using suddfssivfly lfss fffidifnt mfdibnisms
 * until onf suddffds.  If tif iost is suddfssfully donnfdtfd to,
 * tif fbdtory for tif suddfssful mfdibnism is storfd in bn intfrnbl
 * ibsi tbblf kfyfd by tif iost nbmf, so tibt futurf bttfmpts to
 * donnfdt to tif sbmf iost will butombtidblly usf tif sbmf
 * mfdibnism.
 */
@SupprfssWbrnings("dfprfdbtion")
publid dlbss RMIMbstfrSodkftFbdtory fxtfnds RMISodkftFbdtory {

    /** "proxy" pbdkbgf log lfvfl */
    stbtid int logLfvfl = LogStrfbm.pbrsfLfvfl(gftLogLfvfl());

    privbtf stbtid String gftLogLfvfl() {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.trbnsport.proxy.logLfvfl"));
    }

    /* proxy pbdkbgf log */
    stbtid finbl Log proxyLog =
        Log.gftLog("sun.rmi.trbnsport.tdp.proxy",
                   "trbnsport", RMIMbstfrSodkftFbdtory.logLfvfl);

    /** timfout for bttfmping dirfdt sodkft donnfdtions */
    privbtf stbtid long donnfdtTimfout = gftConnfdtTimfout();

    privbtf stbtid long gftConnfdtTimfout() {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Long>) () ->
            Long.gftLong("sun.rmi.trbnsport.proxy.donnfdtTimfout", 15000)); // dffbult: 15 sfdonds
    }

    /** wiftifr to fbllbbdk to HTTP on gfnfrbl donnfdt fbilurfs */
    privbtf stbtid finbl boolfbn fbgfrHttpFbllbbdk =
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Boolfbn>) () ->
            Boolfbn.gftBoolfbn("sun.rmi.trbnsport.proxy.fbgfrHttpFbllbbdk"));

    /** tbblf of iosts suddfssfully donnfdtfd to bnd tif fbdtory usfd */
    privbtf Hbsitbblf<String, RMISodkftFbdtory> suddfssTbblf =
        nfw Hbsitbblf<>();

    /** mbximum numbfr of iosts to rfmfmbfr suddfssful donnfdtion to */
    privbtf stbtid finbl int MbxRfmfmbfrfdHosts = 64;

    /** list of tif iosts in suddfssTbblf in initibl donnfdtion ordfr */
    privbtf Vfdtor<String> iostList = nfw Vfdtor<>(MbxRfmfmbfrfdHosts);

    /** dffbult fbdtory for initibl usf for dirfdt sodkft donnfdtion */
    protfdtfd RMISodkftFbdtory initiblFbdtory = nfw RMIDirfdtSodkftFbdtory();

    /** ordfrfd list of fbdtorifs to try bs bltfrnbtf donnfdtion
      * mfdibnisms if b dirfdt sodkft donnfdtions fbils */
    protfdtfd Vfdtor<RMISodkftFbdtory> bltFbdtoryList;

    /**
     * Crfbtf b RMIMbstfrSodkftFbdtory objfdt.  Estbblisi ordfr of
     * donnfdtion mfdibnisms to bttfmpt on drfbtfSodkft, if b dirfdt
     * sodkft donnfdtion fbils.
     */
    publid RMIMbstfrSodkftFbdtory() {
        bltFbdtoryList = nfw Vfdtor<>(2);
        boolfbn sftFbdtorifs = fblsf;

        try {
            String proxyHost;
            proxyHost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
               (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("ittp.proxyHost"));

            if (proxyHost == null)
                proxyHost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("proxyHost"));

            boolfbn disbblf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("jbvb.rmi.sfrvfr.disbblfHttp", "truf"))
                .fqublsIgnorfCbsf("truf");

            if (!disbblf && proxyHost != null && proxyHost.lfngti() > 0) {
                sftFbdtorifs = truf;
            }
        } dbtdi (Exdfption f) {
            // unbblf to obtbin tif propfrtifs, so usf tif dffbult bfibvior.
        }

        if (sftFbdtorifs) {
            bltFbdtoryList.bddElfmfnt(nfw RMIHttpToPortSodkftFbdtory());
            bltFbdtoryList.bddElfmfnt(nfw RMIHttpToCGISodkftFbdtory());
        }
    }

    /**
     * Crfbtf b nfw dlifnt sodkft.  If wf rfmfmbfr donnfdting to tiis iost
     * suddfssfully bfforf, tifn usf tif sbmf fbdtory bgbin.  Otifrwisf,
     * try using b dirfdt sodkft donnfdtion bnd tifn tif bltfrnbtf fbdtorifs
     * in tif ordfr spfdififd in bltFbdtoryList.
     */
    publid Sodkft drfbtfSodkft(String iost, int port)
        tirows IOExdfption
    {
        if (proxyLog.isLoggbblf(Log.BRIEF)) {
            proxyLog.log(Log.BRIEF, "iost: " + iost + ", port: " + port);
        }

        /*
         * If wf don't ibvf bny bltfrnbtf fbdtorifs to donsult, siort dirduit
         * tif fbllbbdk prodfdurf bnd dflfgbtf to tif initibl fbdtory.
         */
        if (bltFbdtoryList.sizf() == 0) {
            rfturn initiblFbdtory.drfbtfSodkft(iost, port);
        }

        RMISodkftFbdtory fbdtory;

        /*
         * If wf rfmfmbfr suddfssfully donnfdting to tiis iost bfforf,
         * usf tif sbmf fbdtory.
         */
        fbdtory = suddfssTbblf.gft(iost);
        if (fbdtory != null) {
            if (proxyLog.isLoggbblf(Log.BRIEF)) {
                proxyLog.log(Log.BRIEF,
                    "prfviously suddfssful fbdtory found: " + fbdtory);
            }
            rfturn fbdtory.drfbtfSodkft(iost, port);
        }

        /*
         * Nfxt, try b dirfdt sodkft donnfdtion.  Opfn sodkft in bnotifr
         * tirfbd bnd only wbit for spfdififd timfout, in dbsf tif sodkft
         * would otifrwisf spfnd minutfs trying bn unrfbdibblf iost.
         */
        Sodkft initiblSodkft = null;
        Sodkft fbllbbdkSodkft = null;
        finbl AsyndConnfdtor donnfdtor =
            nfw AsyndConnfdtor(initiblFbdtory, iost, port,
                AddfssControllfr.gftContfxt());
                // donnfdtion must bf bttfmptfd witi
                // tiis tirfbd's bddfss dontrol dontfxt
        IOExdfption initiblFbilurf = null;

        try {
            syndironizfd (donnfdtor) {

                Tirfbd t = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw NfwTirfbdAdtion(donnfdtor, "AsyndConnfdtor", truf));
                t.stbrt();

                try {
                    long now = Systfm.durrfntTimfMillis();
                    long dfbdlinf = now + donnfdtTimfout;
                    do {
                        donnfdtor.wbit(dfbdlinf - now);
                        initiblSodkft = difdkConnfdtor(donnfdtor);
                        if (initiblSodkft != null)
                            brfbk;
                        now = Systfm.durrfntTimfMillis();
                    } wiilf (now < dfbdlinf);
                } dbtdi (IntfrruptfdExdfption f) {
                    tirow nfw IntfrruptfdIOExdfption(
                        "intfrruptfd wiilf wbiting for donnfdtor");
                }
            }

            // bssumf no routf to iost (for now) if no donnfdtion yft
            if (initiblSodkft == null)
                tirow nfw NoRoutfToHostExdfption(
                    "donnfdt timfd out: " + iost);

            proxyLog.log(Log.BRIEF, "dirfdt sodkft donnfdtion suddfssful");

            rfturn initiblSodkft;

        } dbtdi (UnknownHostExdfption | NoRoutfToHostExdfption f) {
            initiblFbilurf = f;
        } dbtdi (SodkftExdfption f) {
            if (fbgfrHttpFbllbbdk) {
                initiblFbilurf = f;
            } flsf {
                tirow f;
            }
        } finblly {
            if (initiblFbilurf != null) {

                if (proxyLog.isLoggbblf(Log.BRIEF)) {
                    proxyLog.log(Log.BRIEF,
                        "dirfdt sodkft donnfdtion fbilfd: ", initiblFbilurf);
                }

                // Finblly, try bny bltfrnbtf donnfdtion mfdibnisms.
                for (int i = 0; i < bltFbdtoryList.sizf(); ++ i) {
                    fbdtory = bltFbdtoryList.flfmfntAt(i);
                    if (proxyLog.isLoggbblf(Log.BRIEF)) {
                        proxyLog.log(Log.BRIEF,
                            "trying witi fbdtory: " + fbdtory);
                    }
                    try (Sodkft tfstSodkft =
                            fbdtory.drfbtfSodkft(iost, port)) {
                        // For HTTP donnfdtions, tif output (POST rfqufst) must
                        // bf sfnt bfforf wf vfrify b suddfssful donnfdtion.
                        // So, sbdrifidf b sodkft for tif sbkf of tfsting...
                        // Tif following sfqufndf siould vfrify b suddfssful
                        // HTTP donnfdtion if no IOExdfption is tirown.
                        InputStrfbm in = tfstSodkft.gftInputStrfbm();
                        int b = in.rfbd(); // probbbly -1 for EOF...
                    } dbtdi (IOExdfption fx) {
                        if (proxyLog.isLoggbblf(Log.BRIEF)) {
                            proxyLog.log(Log.BRIEF, "fbdtory fbilfd: ", fx);
                        }

                        dontinuf;
                    }
                    proxyLog.log(Log.BRIEF, "fbdtory suddffdfd");

                    // fbdtory suddffdfd, opfn nfw sodkft for dbllfr's usf
                    try {
                        fbllbbdkSodkft = fbdtory.drfbtfSodkft(iost, port);
                    } dbtdi (IOExdfption fx) {  // if it fbils 2nd timf,
                    }                           // just givf up
                    brfbk;
                }
            }
        }

        syndironizfd (suddfssTbblf) {
            try {
                // difdk ondf bgbin to sff if dirfdt donnfdtion suddffdfd
                syndironizfd (donnfdtor) {
                    initiblSodkft = difdkConnfdtor(donnfdtor);
                }
                if (initiblSodkft != null) {
                    // if wf ibd mbdf bnotifr onf bs wfll, dlfbn it up...
                    if (fbllbbdkSodkft != null)
                        fbllbbdkSodkft.dlosf();
                    rfturn initiblSodkft;
                }
                // if donnfdtor fvfr dofs gft sodkft, it won't bf usfd
                donnfdtor.notUsfd();
            } dbtdi (UnknownHostExdfption | NoRoutfToHostExdfption f) {
                initiblFbilurf = f;
            } dbtdi (SodkftExdfption f) {
                if (fbgfrHttpFbllbbdk) {
                    initiblFbilurf = f;
                } flsf {
                    tirow f;
                }
            }
            // if wf ibd found bn bltfrnbtf mfdibnism, go bnd usf it
            if (fbllbbdkSodkft != null) {
                // rfmfmbfr tiis suddfssful iost/fbdtory pbir
                rfmfmbfrFbdtory(iost, fbdtory);
                rfturn fbllbbdkSodkft;
            }
            tirow initiblFbilurf;
        }
    }

    /**
     * Rfmfmbfr b suddfssful fbdtory for donnfdting to iost.
     * Currfntly, fxdfss iosts brf rfmovfd from tif rfmfmbfrfd list
     * using b Lfbst Rfdfntly Crfbtfd strbtfgy.
     */
    void rfmfmbfrFbdtory(String iost, RMISodkftFbdtory fbdtory) {
        syndironizfd (suddfssTbblf) {
            wiilf (iostList.sizf() >= MbxRfmfmbfrfdHosts) {
                suddfssTbblf.rfmovf(iostList.flfmfntAt(0));
                iostList.rfmovfElfmfntAt(0);
            }
            iostList.bddElfmfnt(iost);
            suddfssTbblf.put(iost, fbdtory);
        }
    }

    /**
     * Cifdk if bn AsyndConnfdtor suddffdfd.  If not, rfturn sodkft
     * givfn to fbll bbdk to.
     */
    Sodkft difdkConnfdtor(AsyndConnfdtor donnfdtor)
        tirows IOExdfption
    {
        Exdfption f = donnfdtor.gftExdfption();
        if (f != null) {
            f.fillInStbdkTrbdf();
            /*
             * Tif AsyndConnfdtor implfmfntbtion gubrbntffd tibt tif fxdfption
             * will bf fitifr bn IOExdfption or b RuntimfExdfption, bnd wf dbn
             * only tirow onf of tiosf, so donvindf tibt dompilfr tibt it must
             * bf onf of tiosf.
             */
            if (f instbndfof IOExdfption) {
                tirow (IOExdfption) f;
            } flsf if (f instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption) f;
            } flsf {
                tirow nfw Error("intfrnbl frror: " +
                    "unfxpfdtfd difdkfd fxdfption: " + f.toString());
            }
        }
        rfturn donnfdtor.gftSodkft();
    }

    /**
     * Crfbtf b nfw sfrvfr sodkft.
     */
    publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port) tirows IOExdfption {
        //rfturn nfw HttpAwbrfSfrvfrSodkft(port);
        rfturn initiblFbdtory.drfbtfSfrvfrSodkft(port);
    }


    /**
     * AsyndConnfdtor is usfd by RMIMbstfrSodkftFbdtory to bttfmpt sodkft
     * donnfdtions on b sfpbrbtf tirfbd.  Tiis bllows RMIMbstfrSodkftFbdtory
     * to dontrol iow long it will wbit for tif donnfdtion to suddffd.
     */
    privbtf dlbss AsyndConnfdtor implfmfnts Runnbblf {

        /** wibt fbdtory to usf to bttfmpt donnfdtion */
        privbtf RMISodkftFbdtory fbdtory;

        /** tif iost to donnfdt to */
        privbtf String iost;

        /** tif port to donnfdt to */
        privbtf int port;

        /** bddfss dontrol dontfxt to bttfmpt donnfdtion witiin */
        privbtf AddfssControlContfxt bdd;

        /** fxdfption tibt oddurrfd during donnfdtion, if bny */
        privbtf Exdfption fxdfption = null;

        /** tif donnfdtfd sodkft, if suddfssful */
        privbtf Sodkft sodkft = null;

        /** sodkft siould bf dlosfd bftfr drfbtfd, if fvfr */
        privbtf boolfbn dlfbnUp = fblsf;

        /**
         * Crfbtf b nfw bsyndironous donnfdtor objfdt.
         */
        AsyndConnfdtor(RMISodkftFbdtory fbdtory, String iost, int port,
                       AddfssControlContfxt bdd)
        {
            tiis.fbdtory = fbdtory;
            tiis.iost    = iost;
            tiis.port    = port;
            tiis.bdd     = bdd;
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkConnfdt(iost, port);
            }
        }

        /**
         * Attfmpt sodkft donnfdtion in sfpbrbtf tirfbd.  If suddfssful,
         * notify mbstfr wbiting,
         */
        publid void run() {
            try {
                /*
                 * Using tif privilfgfs of tif tirfbd tibt wbnts to mbkf tif
                 * donnfdtion is tfmpting, but it will fbil witi bpplfts witi
                 * tif durrfnt bpplft sfdurity mbnbgfr bfdbusf tif bpplft
                 * nftwork donnfdtion polidy is not dbpturfd in tif pfrmission
                 * frbmfwork of tif bddfss dontrol dontfxt wf ibvf.
                 *
                 * jbvb.sfdurity.AddfssControllfr.bfginPrivilfgfd(bdd);
                 */
                try {
                    Sodkft tfmp = fbdtory.drfbtfSodkft(iost, port);
                    syndironizfd (tiis) {
                        sodkft = tfmp;
                        notify();
                    }
                    rfmfmbfrFbdtory(iost, fbdtory);
                    syndironizfd (tiis) {
                        if (dlfbnUp)
                          try {
                              sodkft.dlosf();
                          } dbtdi (IOExdfption f) {
                          }
                    }
                } dbtdi (Exdfption f) {
                    /*
                     * Notf tibt tif only fxdfptions wiidi dould bdtublly ibvf
                     * oddurrfd ifrf brf IOExdfption or RuntimfExdfption.
                     */
                    syndironizfd (tiis) {
                        fxdfption = f;
                        notify();
                    }
                }
            } finblly {
                /*
                 * Sff bbovf dommfnts for mbtdiing bfginPrivilfgfd() dbll tibt
                 * is blso dommfntfd out.
                 *
                 * jbvb.sfdurity.AddfssControllfr.fndPrivilfgfd();
                 */
            }
        }

        /**
         * Gft fxdfption tibt oddurrfd during donnfdtion bttfmpt, if bny.
         * In tif durrfnt implfmfntbtion, tiis is gubrbntffd to bf fitifr
         * bn IOExdfption or b RuntimfExdfption.
         */
        privbtf syndironizfd Exdfption gftExdfption() {
            rfturn fxdfption;
        }

        /**
         * Gft suddfssful sodkft, if bny.
         */
        privbtf syndironizfd Sodkft gftSodkft() {
            rfturn sodkft;
        }

        /**
         * Notf tibt tiis donnfdtor's sodkft, if fvfr suddfssfully drfbtfd,
         * will not bf usfd, so it siould bf dlfbnfd up quidkly
         */
        syndironizfd void notUsfd() {
            if (sodkft != null) {
                try {
                    sodkft.dlosf();
                } dbtdi (IOExdfption f) {
                }
            }
            dlfbnUp = truf;
        }
    }
}
