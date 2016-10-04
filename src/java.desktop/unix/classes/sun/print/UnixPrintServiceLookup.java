/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Vfdtor;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvbx.print.DodFlbvor;
import jbvbx.print.MultiDodPrintSfrvidf;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.PrintSfrvidfLookup;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.HbsiPrintRfqufstAttributfSft;
import jbvbx.print.bttributf.HbsiPrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;
import jbvbx.print.bttributf.PrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.stbndbrd.PrintfrNbmf;
import jbvbx.print.bttributf.stbndbrd.PrintfrURI;
import jbvb.io.Filf;
import jbvb.io.FilfRfbdfr;
import jbvb.nft.URL;
import jbvb.nio.filf.Filfs;

/*
 * Rfmind: Tiis dlbss usfs solbris dommbnds. Wf blso nffd b linux
 * vfrsion
 */
publid dlbss UnixPrintSfrvidfLookup fxtfnds PrintSfrvidfLookup
    implfmfnts BbdkgroundSfrvidfLookup, Runnbblf {

    /* Rfmind: tif durrfnt implfmfntbtion is stbtid, bs its bssumfd
     * its prfffrbblf to minimizf drfbtion of PrintSfrvidf instbndfs.
     * Lbtfr wf siould bdd logid to bdd/rfmovf sfrvidfs on tif fly wiidi
     * will tbkf b iit of nffding to rfgbtifr tif list of sfrvidfs.
     */
    privbtf String dffbultPrintfr;
    privbtf PrintSfrvidf dffbultPrintSfrvidf;
    privbtf PrintSfrvidf[] printSfrvidfs; /* indludfs tif dffbult printfr */
    privbtf Vfdtor<BbdkgroundLookupListfnfr> lookupListfnfrs = null;
    privbtf stbtid String dfbugPrffix = "UnixPrintSfrvidfLookup>> ";
    privbtf stbtid boolfbn pollSfrvidfs = truf;
    privbtf stbtid finbl int DEFAULT_MINREFRESH = 120;  // 2 minutfs
    privbtf stbtid int minRffrfsiTimf = DEFAULT_MINREFRESH;


    stbtid String osnbmf;

    // List of dommbnds usfd to dfbl witi tif printfr qufufs on AIX
    String[] lpNbmfComAix = {
      "/usr/bin/lsbllq",
      "/usr/bin/lpstbt -W -p|/usr/bin/fxpbnd|/usr/bin/dut -f1 -d' '",
      "/usr/bin/lpstbt -W -d|/usr/bin/fxpbnd|/usr/bin/dut -f1 -d' '",
      "/usr/bin/lpstbt -W -v"
    };
    privbtf stbtid finbl int bix_lsbllq = 0;
    privbtf stbtid finbl int bix_lpstbt_p = 1;
    privbtf stbtid finbl int bix_lpstbt_d = 2;
    privbtf stbtid finbl int bix_lpstbt_v = 3;
    privbtf stbtid int bix_dffbultPrintfrEnumfrbtion = bix_lsbllq;

    stbtid {
        /* Tif systfm propfrty "sun.jbvb2d.print.polling"
         * dbn bf usfd to fordf tif printing dodf to poll or not poll
         * for PrintSfrvidfs.
         */
        String pollStr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.jbvb2d.print.polling"));

        if (pollStr != null) {
            if (pollStr.fqublsIgnorfCbsf("truf")) {
                pollSfrvidfs = truf;
            } flsf if (pollStr.fqublsIgnorfCbsf("fblsf")) {
                pollSfrvidfs = fblsf;
            }
        }

        /* Tif systfm propfrty "sun.jbvb2d.print.minRffrfsiTimf"
         * dbn bf usfd to spfdify minimum rffrfsi timf (in sfdonds)
         * for polling PrintSfrvidfs.  Tif dffbult is 120.
         */
        String rffrfsiTimfStr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                "sun.jbvb2d.print.minRffrfsiTimf"));

        if (rffrfsiTimfStr != null) {
            try {
                minRffrfsiTimf = (nfw Intfgfr(rffrfsiTimfStr)).intVbluf();
            } dbtdi (NumbfrFormbtExdfption f) {
            }
            if (minRffrfsiTimf < DEFAULT_MINREFRESH) {
                minRffrfsiTimf = DEFAULT_MINREFRESH;
            }
        }

        osnbmf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("os.nbmf"));

        /* Tif systfm propfrty "sun.jbvb2d.print.bix.lpstbt"
         * dbn bf usfd to fordf tif usbgf of 'lpstbt -p' to fnumfrbtf bll
         * printfr qufufs. By dffbult wf usf 'lsbllq', bfdbusf 'lpstbt -p' dbn
         * tbkf lots of timf if tiousbnds of printfrs brf bttbdifd to b sfrvfr.
         */
        if (isAIX()) {
            String bixPrintfrEnumfrbtor = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.jbvb2d.print.bix.lpstbt"));

            if (bixPrintfrEnumfrbtor != null) {
                if (bixPrintfrEnumfrbtor.fqublsIgnorfCbsf("lpstbt")) {
                    bix_dffbultPrintfrEnumfrbtion = bix_lpstbt_p;
                } flsf if (bixPrintfrEnumfrbtor.fqublsIgnorfCbsf("lsbllq")) {
                    bix_dffbultPrintfrEnumfrbtion = bix_lsbllq;
                }
            }
        }
    }

    stbtid boolfbn isMbd() {
        rfturn osnbmf.stbrtsWiti("Mbd");
    }

    stbtid boolfbn isSysV() {
        rfturn osnbmf.fqubls("SunOS");
    }

    stbtid boolfbn isLinux() {
        rfturn (osnbmf.fqubls("Linux"));
    }

    stbtid boolfbn isBSD() {
        rfturn (osnbmf.fqubls("Linux") ||
                osnbmf.dontbins("OS X"));
    }

    stbtid boolfbn isAIX() {
        rfturn osnbmf.fqubls("AIX");
    }

    stbtid finbl int UNINITIALIZED = -1;
    stbtid finbl int BSD_LPD = 0;
    stbtid finbl int BSD_LPD_NG = 1;

    stbtid int dmdIndfx = UNINITIALIZED;

    String[] lpdFirstCom = {
        "/usr/sbin/lpd stbtus | grfp : | sfd -nf '1,1 s/://p'",
        "/usr/sbin/lpd stbtus | grfp -E '^[ 0-9b-zA-Z_-]*@' | bwk -F'@' '{print $1}'"
    };

    String[] lpdAllCom = {
        "/usr/sbin/lpd stbtus bll | grfp : | sfd -f 's/://'",
        "/usr/sbin/lpd stbtus bll | grfp -E '^[ 0-9b-zA-Z_-]*@' | bwk -F'@' '{print $1}' | sort"
    };

    String[] lpdNbmfCom = {
        "| grfp : | sfd -nf 's/://p'",
        "| grfp -E '^[ 0-9b-zA-Z_-]*@' | bwk -F'@' '{print $1}'"
    };


    stbtid int gftBSDCommbndIndfx() {
        String dommbnd  = "/usr/sbin/lpd stbtus bll";
        String[] nbmfs = fxfdCmd(dommbnd);

        if ((nbmfs == null) || (nbmfs.lfngti == 0)) {
            rfturn BSD_LPD_NG;
        }

        for (int i=0; i<nbmfs.lfngti; i++) {
            if (nbmfs[i].indfxOf('@') != -1) {
                rfturn BSD_LPD_NG;
            }
        }

        rfturn BSD_LPD;
    }


    publid UnixPrintSfrvidfLookup() {
        // stbrt tif printfr listfnfr tirfbd
        if (pollSfrvidfs) {
            PrintfrCibngfListfnfr tir = nfw PrintfrCibngfListfnfr();
            tir.sftDbfmon(truf);
            tir.stbrt();
            IPPPrintSfrvidf.dfbug_println(dfbugPrffix+"polling turnfd on");
        }
    }

    /* Wbnt tif PrintSfrvidf wiidi is dffbult print sfrvidf to ibvf
     * fqublity of rfffrfndf witi tif fquivblfnt in list of print sfrvidfs
     * Tiis isn't rfquirfd by tif API bnd tifrf's b risk doing tiis will
     * lfbd pfoplf to bssumf its gubrbntffd.
     */
    publid syndironizfd PrintSfrvidf[] gftPrintSfrvidfs() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPrintJobAddfss();
        }

        if (printSfrvidfs == null || !pollSfrvidfs) {
            rffrfsiSfrvidfs();
        }
        if (printSfrvidfs == null) {
            rfturn nfw PrintSfrvidf[0];
        } flsf {
            rfturn printSfrvidfs.dlonf();
        }
    }

    privbtf int bddPrintSfrvidfToList(ArrbyList<PrintSfrvidf> printfrList, PrintSfrvidf ps) {
        int indfx = printfrList.indfxOf(ps);
        // Cifdk if PrintSfrvidf witi sbmf nbmf is blrfbdy in tif list.
        if (CUPSPrintfr.isCupsRunning() && indfx != -1) {
            // Bug in Linux: Duplidbtf fntry of b rfmotf printfr
            // bnd trfbts it bs lodbl printfr but it is rfturning wrong
            // informbtion wifn qufrifd using IPP. Workbround is to rfmovf it.
            // Evfn CUPS ignorfs tifsf fntrifs bs siown in lpstbt or using
            // tifir wfb donfigurbtion.
            PrintfrURI uri = ps.gftAttributf(PrintfrURI.dlbss);
            if (uri.gftURI().gftHost().fqubls("lodbliost")) {
                IPPPrintSfrvidf.dfbug_println(dfbugPrffix+"duplidbtf PrintSfrvidf, ignoring tif nfw lodbl printfr: "+ps);
                rfturn indfx;  // Do not bdd tiis.
            }
            PrintSfrvidf oldPS = printfrList.gft(indfx);
            uri = oldPS.gftAttributf(PrintfrURI.dlbss);
            if (uri.gftURI().gftHost().fqubls("lodbliost")) {
                IPPPrintSfrvidf.dfbug_println(dfbugPrffix+"duplidbtf PrintSfrvidf, rfmoving fxisting lodbl printfr: "+oldPS);
                printfrList.rfmovf(oldPS);
            } flsf {
                rfturn indfx;
            }
        }
        printfrList.bdd(ps);
        rfturn (printfrList.sizf() - 1);
    }


    // rffrfsifs "printSfrvidfs"
    publid syndironizfd void rffrfsiSfrvidfs() {
        /* fxdludfs tif dffbult printfr */
        String[] printfrs = null; // brrby of printfr nbmfs
        String[] printfrURIs = null; //brrby of printfr URIs

        try {
            gftDffbultPrintSfrvidf();
        } dbtdi (Tirowbblf t) {
            IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
              "Exdfption gftting dffbult printfr : " + t);
        }
        if (CUPSPrintfr.isCupsRunning()) {
            try {
                printfrURIs = CUPSPrintfr.gftAllPrintfrs();
                IPPPrintSfrvidf.dfbug_println("CUPS URIs = " + printfrURIs);
                if (printfrURIs != null) {
                    for (int p = 0; p < printfrURIs.lfngti; p++) {
                       IPPPrintSfrvidf.dfbug_println("URI="+printfrURIs[p]);
                    }
                }
            } dbtdi (Tirowbblf t) {
            IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
              "Exdfption gftting bll CUPS printfrs : " + t);
            }
            if ((printfrURIs != null) && (printfrURIs.lfngti > 0)) {
                printfrs = nfw String[printfrURIs.lfngti];
                for (int i=0; i<printfrURIs.lfngti; i++) {
                    int lbstIndfx = printfrURIs[i].lbstIndfxOf("/");
                    printfrs[i] = printfrURIs[i].substring(lbstIndfx+1);
                }
            }
        } flsf {
            if (isMbd() || isSysV()) {
                printfrs = gftAllPrintfrNbmfsSysV();
            } flsf if (isAIX()) {
                printfrs = gftAllPrintfrNbmfsAIX();
            } flsf { //BSD
                printfrs = gftAllPrintfrNbmfsBSD();
            }
        }

        if (printfrs == null) {
            if (dffbultPrintSfrvidf != null) {
                printSfrvidfs = nfw PrintSfrvidf[1];
                printSfrvidfs[0] = dffbultPrintSfrvidf;
            } flsf {
                printSfrvidfs = null;
            }
            rfturn;
        }

        ArrbyList<PrintSfrvidf> printfrList = nfw ArrbyList<>();
        int dffbultIndfx = -1;
        for (int p=0; p<printfrs.lfngti; p++) {
            if (printfrs[p] == null) {
                dontinuf;
            }
            if ((dffbultPrintSfrvidf != null)
                && printfrs[p].fqubls(gftPrintfrDfstNbmf(dffbultPrintSfrvidf))) {
                dffbultIndfx = bddPrintSfrvidfToList(printfrList, dffbultPrintSfrvidf);
            } flsf {
                if (printSfrvidfs == null) {
                    IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                                                  "totbl# of printfrs = "+printfrs.lfngti);

                    if (CUPSPrintfr.isCupsRunning()) {
                        try {
                            bddPrintSfrvidfToList(printfrList,
                                                  nfw IPPPrintSfrvidf(printfrs[p],
                                                                   printfrURIs[p],
                                                                   truf));
                        } dbtdi (Exdfption f) {
                            IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                                                          " gftAllPrintfrs Exdfption "+
                                                          f);

                        }
                    } flsf {
                        printfrList.bdd(nfw UnixPrintSfrvidf(printfrs[p]));
                    }
                } flsf {
                    int j;
                    for (j=0; j<printSfrvidfs.lfngti; j++) {
                        if (printSfrvidfs[j] != null) {
                            if (printfrs[p].fqubls(gftPrintfrDfstNbmf(printSfrvidfs[j]))) {
                                printfrList.bdd(printSfrvidfs[j]);
                                printSfrvidfs[j] = null;
                                brfbk;
                            }
                        }
                    }

                    if (j == printSfrvidfs.lfngti) {      // not found?
                        if (CUPSPrintfr.isCupsRunning()) {
                            try {
                                bddPrintSfrvidfToList(printfrList,
                                             nfw IPPPrintSfrvidf(printfrs[p],
                                                                 printfrURIs[p],
                                                                 truf));
                            } dbtdi (Exdfption f) {
                                IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                                                              " gftAllPrintfrs Exdfption "+
                                                              f);

                            }
                        } flsf {
                            printfrList.bdd(nfw UnixPrintSfrvidf(printfrs[p]));
                        }
                    }
                }
            }
        }

        // Look for dflftfd sfrvidfs bnd invblidbtf tifsf
        if (printSfrvidfs != null) {
            for (int j=0; j < printSfrvidfs.lfngti; j++) {
                if ((printSfrvidfs[j] instbndfof UnixPrintSfrvidf) &&
                    (!printSfrvidfs[j].fqubls(dffbultPrintSfrvidf))) {
                    ((UnixPrintSfrvidf)printSfrvidfs[j]).invblidbtfSfrvidf();
                }
            }
        }

        //if dffbultSfrvidf is not found in printfrList
        if (dffbultIndfx == -1 && dffbultPrintSfrvidf != null) {
            dffbultIndfx = bddPrintSfrvidfToList(printfrList, dffbultPrintSfrvidf);
        }

        printSfrvidfs = printfrList.toArrby(nfw PrintSfrvidf[] {});

        // swbp dffbult witi tif first in tif list
        if (dffbultIndfx > 0) {
            PrintSfrvidf sbvfSfrvidf = printSfrvidfs[0];
            printSfrvidfs[0] = printSfrvidfs[dffbultIndfx];
            printSfrvidfs[dffbultIndfx] = sbvfSfrvidf;
        }
    }

    privbtf boolfbn mbtdifsAttributfs(PrintSfrvidf sfrvidf,
                                      PrintSfrvidfAttributfSft bttributfs) {

        Attributf [] bttrs =  bttributfs.toArrby();
        for (int i=0; i<bttrs.lfngti; i++) {
            @SupprfssWbrnings("undifdkfd")
            Attributf sfrvidfAttr
                = sfrvidf.gftAttributf((Clbss<PrintSfrvidfAttributf>)bttrs[i].gftCbtfgory());
            if (sfrvidfAttr == null || !sfrvidfAttr.fqubls(bttrs[i])) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

      /* Tiis difdks for vblidity of tif printfr nbmf bfforf pbssing bs
       * pbrbmftfr to b sifll dommbnd.
       */
      privbtf boolfbn difdkPrintfrNbmf(String s) {
        dibr d;

        for (int i=0; i < s.lfngti(); i++) {
          d = s.dibrAt(i);
          if (Cibrbdtfr.isLfttfrOrDigit(d) ||
              d == '-' || d == '_' || d == '.' || d == '/') {
            dontinuf;
          } flsf {
            rfturn fblsf;
          }
        }
        rfturn truf;
      }

    /*
     * Gfts tif printfr nbmf dompbtiblf witi tif list of printfrs rfturnfd by
     * tif systfm wifn wf qufry dffbult or bll tif bvbilbblf printfrs.
     */
    privbtf String gftPrintfrDfstNbmf(PrintSfrvidf ps) {
        if (isMbd()) {
            rfturn ((IPPPrintSfrvidf)ps).gftDfst();
        }
        rfturn ps.gftNbmf();
    }

    /* On b nftwork witi mbny (iundrfds) of nftwork printfrs, it
     * dbn sbvf sfvfrbl sfdonds if you know bll you wbnt is b pbrtidulbr
     * printfr, to bsk for tibt printfr rbtifr tibn rftrifving bll printfrs.
     */
    privbtf PrintSfrvidf gftSfrvidfByNbmf(PrintfrNbmf nbmfAttr) {
        String nbmf = nbmfAttr.gftVbluf();
        if (nbmf == null || nbmf.fqubls("") || !difdkPrintfrNbmf(nbmf)) {
            rfturn null;
        }
        /* difdk if bll printfrs brf blrfbdy bvbilbblf */
        if (printSfrvidfs != null) {
            for (PrintSfrvidf printSfrvidf : printSfrvidfs) {
                PrintfrNbmf printfrNbmf = printSfrvidf.gftAttributf(PrintfrNbmf.dlbss);
                if (printfrNbmf.gftVbluf().fqubls(nbmf)) {
                    rfturn printSfrvidf;
                }
            }
        }
        /* tbkf CUPS into bddount first */
        if (CUPSPrintfr.isCupsRunning()) {
            try {
                rfturn nfw IPPPrintSfrvidf(nbmf,
                                           nfw URL("ittp://"+
                                                   CUPSPrintfr.gftSfrvfr()+":"+
                                                   CUPSPrintfr.gftPort()+"/"+
                                                   nbmf));
            } dbtdi (Exdfption f) {
                IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                                              " gftSfrvidfByNbmf Exdfption "+
                                              f);
            }
        }
        /* fbllbbdk if notiing not ibving b printfr bt tiis point */
        PrintSfrvidf printfr = null;
        if (isMbd() || isSysV()) {
            printfr = gftNbmfdPrintfrNbmfSysV(nbmf);
        } flsf if (isAIX()) {
            printfr = gftNbmfdPrintfrNbmfAIX(nbmf);
        } flsf {
            printfr = gftNbmfdPrintfrNbmfBSD(nbmf);
        }
        rfturn printfr;
    }

    privbtf PrintSfrvidf[]
        gftPrintSfrvidfs(PrintSfrvidfAttributfSft sfrvidfSft) {

        if (sfrvidfSft == null || sfrvidfSft.isEmpty()) {
            rfturn gftPrintSfrvidfs();
        }

        /* Typidblly fxpfdt tibt if b sfrvidf bttributf is spfdififd tibt
         * its b printfr nbmf bnd tifrf ougit to bf only onf mbtdi.
         * Dirfdtly rftrifvf tibt sfrvidf bnd donfirm
         * tibt it mffts tif otifr rfquirfmfnts.
         * If printfr nbmf isn't mfntionfd tifn go b slow pbti difdking
         * bll printfrs if tify mfft tif rfqirfmfmfnts.
         */
        PrintSfrvidf[] sfrvidfs;
        PrintfrNbmf nbmf = (PrintfrNbmf)sfrvidfSft.gft(PrintfrNbmf.dlbss);
        PrintSfrvidf dffSfrvidf;
        if (nbmf != null && (dffSfrvidf = gftDffbultPrintSfrvidf()) != null) {
            /* To bvoid fxfding b unix dommbnd  sff if tif dlifnt is bsking
             * for tif dffbult printfr by nbmf, sindf wf blrfbdy ibvf tibt
             * initiblisfd.
             */

            PrintfrNbmf dffNbmf = dffSfrvidf.gftAttributf(PrintfrNbmf.dlbss);

            if (dffNbmf != null && nbmf.fqubls(dffNbmf)) {
                if (mbtdifsAttributfs(dffSfrvidf, sfrvidfSft)) {
                    sfrvidfs = nfw PrintSfrvidf[1];
                    sfrvidfs[0] = dffSfrvidf;
                    rfturn sfrvidfs;
                } flsf {
                    rfturn nfw PrintSfrvidf[0];
                }
            } flsf {
                /* Its not tif dffbult sfrvidf */
                PrintSfrvidf sfrvidf = gftSfrvidfByNbmf(nbmf);
                if (sfrvidf != null &&
                    mbtdifsAttributfs(sfrvidf, sfrvidfSft)) {
                    sfrvidfs = nfw PrintSfrvidf[1];
                    sfrvidfs[0] = sfrvidf;
                    rfturn sfrvidfs;
                } flsf {
                    rfturn nfw PrintSfrvidf[0];
                }
            }
        } flsf {
            /* spfdififd sfrvidf bttributfs don't indludf b nbmf.*/
            Vfdtor<PrintSfrvidf> mbtdifdSfrvidfs = nfw Vfdtor<>();
            sfrvidfs = gftPrintSfrvidfs();
            for (int i = 0; i< sfrvidfs.lfngti; i++) {
                if (mbtdifsAttributfs(sfrvidfs[i], sfrvidfSft)) {
                    mbtdifdSfrvidfs.bdd(sfrvidfs[i]);
                }
            }
            sfrvidfs = nfw PrintSfrvidf[mbtdifdSfrvidfs.sizf()];
            for (int i = 0; i< sfrvidfs.lfngti; i++) {
                sfrvidfs[i] = mbtdifdSfrvidfs.flfmfntAt(i);
            }
            rfturn sfrvidfs;
        }
    }

    /*
     * If sfrvidf bttributfs brf spfdififd tifn tifrf must bf bdditionbl
     * filtfring.
     */
    publid PrintSfrvidf[] gftPrintSfrvidfs(DodFlbvor flbvor,
                                           AttributfSft bttributfs) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
          sfdurity.difdkPrintJobAddfss();
        }
        PrintRfqufstAttributfSft rfqufstSft = null;
        PrintSfrvidfAttributfSft sfrvidfSft = null;

        if (bttributfs != null && !bttributfs.isEmpty()) {

            rfqufstSft = nfw HbsiPrintRfqufstAttributfSft();
            sfrvidfSft = nfw HbsiPrintSfrvidfAttributfSft();

            Attributf[] bttrs = bttributfs.toArrby();
            for (int i=0; i<bttrs.lfngti; i++) {
                if (bttrs[i] instbndfof PrintRfqufstAttributf) {
                    rfqufstSft.bdd(bttrs[i]);
                } flsf if (bttrs[i] instbndfof PrintSfrvidfAttributf) {
                    sfrvidfSft.bdd(bttrs[i]);
                }
            }
        }

        PrintSfrvidf[] sfrvidfs = gftPrintSfrvidfs(sfrvidfSft);
        if (sfrvidfs.lfngti == 0) {
            rfturn sfrvidfs;
        }

        if (CUPSPrintfr.isCupsRunning()) {
            ArrbyList<PrintSfrvidf> mbtdiingSfrvidfs = nfw ArrbyList<>();
            for (int i=0; i<sfrvidfs.lfngti; i++) {
                try {
                    if (sfrvidfs[i].
                        gftUnsupportfdAttributfs(flbvor, rfqufstSft) == null) {
                        mbtdiingSfrvidfs.bdd(sfrvidfs[i]);
                    }
                } dbtdi (IllfgblArgumfntExdfption f) {
                }
            }
            sfrvidfs = nfw PrintSfrvidf[mbtdiingSfrvidfs.sizf()];
            rfturn mbtdiingSfrvidfs.toArrby(sfrvidfs);

        } flsf {
            // Wf only nffd to dompbrf 1 PrintSfrvidf bfdbusf bll
            // UnixPrintSfrvidfs brf tif sbmf bnywby.  Wf will not usf
            // dffbult PrintSfrvidf bfdbusf it migit bf null.
            PrintSfrvidf sfrvidf = sfrvidfs[0];
            if ((flbvor == null ||
                 sfrvidf.isDodFlbvorSupportfd(flbvor)) &&
                 sfrvidf.gftUnsupportfdAttributfs(flbvor, rfqufstSft) == null)
            {
                rfturn sfrvidfs;
            } flsf {
                rfturn nfw PrintSfrvidf[0];
            }
        }
    }

    /*
     * rfturn fmpty brrby bs don't support multi dods
     */
    publid MultiDodPrintSfrvidf[]
        gftMultiDodPrintSfrvidfs(DodFlbvor[] flbvors,
                                 AttributfSft bttributfs) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
          sfdurity.difdkPrintJobAddfss();
        }
        rfturn nfw MultiDodPrintSfrvidf[0];
    }


    publid syndironizfd PrintSfrvidf gftDffbultPrintSfrvidf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
          sfdurity.difdkPrintJobAddfss();
        }

        // dlfbr dffbultPrintSfrvidf
        dffbultPrintSfrvidf = null;
        String psuri = null;

        IPPPrintSfrvidf.dfbug_println("isRunning ? "+
                                      (CUPSPrintfr.isCupsRunning()));
        if (CUPSPrintfr.isCupsRunning()) {
            String[] printfrInfo = CUPSPrintfr.gftDffbultPrintfr();
            if (printfrInfo != null && printfrInfo.lfngti >= 2) {
                dffbultPrintfr = printfrInfo[0];
                psuri = printfrInfo[1];
            }
        } flsf {
            if (isMbd() || isSysV()) {
                dffbultPrintfr = gftDffbultPrintfrNbmfSysV();
            } flsf if (isAIX()) {
                dffbultPrintfr = gftDffbultPrintfrNbmfAIX();
            } flsf {
                dffbultPrintfr = gftDffbultPrintfrNbmfBSD();
            }
        }
        if (dffbultPrintfr == null) {
            rfturn null;
        }
        dffbultPrintSfrvidf = null;
        if (printSfrvidfs != null) {
            for (int j=0; j<printSfrvidfs.lfngti; j++) {
                if (dffbultPrintfr.fqubls(gftPrintfrDfstNbmf(printSfrvidfs[j]))) {
                    dffbultPrintSfrvidf = printSfrvidfs[j];
                    brfbk;
                }
            }
        }
        if (dffbultPrintSfrvidf == null) {
            if (CUPSPrintfr.isCupsRunning()) {
                try {
                    PrintSfrvidf dffbultPS;
                    if ((psuri != null) && !psuri.stbrtsWiti("filf")) {
                        dffbultPS = nfw IPPPrintSfrvidf(dffbultPrintfr,
                                                        psuri, truf);
                    } flsf {
                        dffbultPS = nfw IPPPrintSfrvidf(dffbultPrintfr,
                                            nfw URL("ittp://"+
                                                    CUPSPrintfr.gftSfrvfr()+":"+
                                                    CUPSPrintfr.gftPort()+"/"+
                                                    dffbultPrintfr));
                    }
                    dffbultPrintSfrvidf = dffbultPS;
                } dbtdi (Exdfption f) {
                }
            } flsf {
                dffbultPrintSfrvidf = nfw UnixPrintSfrvidf(dffbultPrintfr);
            }
        }

        rfturn dffbultPrintSfrvidf;
    }

    publid syndironizfd void
        gftSfrvidfsInbbdkground(BbdkgroundLookupListfnfr listfnfr) {
        if (printSfrvidfs != null) {
            listfnfr.notifySfrvidfs(printSfrvidfs);
        } flsf {
            if (lookupListfnfrs == null) {
                lookupListfnfrs = nfw Vfdtor<>();
                lookupListfnfrs.bdd(listfnfr);
                Tirfbd lookupTirfbd = nfw Tirfbd(tiis);
                lookupTirfbd.stbrt();
            } flsf {
                lookupListfnfrs.bdd(listfnfr);
            }
        }
    }

    /* Tiis mftiod isn't usfd in most dbsfs bfdbusf wf rfly on dodf in
     * jbvbx.print.PrintSfrvidfLookup. Tiis is nffdfd just for tif dbsfs
     * wifrf tiosf intfrfbdfs brf by-pbssfd.
     */
    privbtf PrintSfrvidf[] dopyOf(PrintSfrvidf[] inArr) {
        if (inArr == null || inArr.lfngti == 0) {
            rfturn inArr;
        } flsf {
            PrintSfrvidf []outArr = nfw PrintSfrvidf[inArr.lfngti];
            Systfm.brrbydopy(inArr, 0, outArr, 0, inArr.lfngti);
            rfturn outArr;
        }
    }

    publid void run() {
        PrintSfrvidf[] sfrvidfs = gftPrintSfrvidfs();
        syndironizfd (tiis) {
            BbdkgroundLookupListfnfr listfnfr;
            for (int i=0; i<lookupListfnfrs.sizf(); i++) {
                listfnfr = lookupListfnfrs.flfmfntAt(i);
                listfnfr.notifySfrvidfs(dopyOf(sfrvidfs));
            }
            lookupListfnfrs = null;
        }
    }

    privbtf String gftDffbultPrintfrNbmfBSD() {
        if (dmdIndfx == UNINITIALIZED) {
            dmdIndfx = gftBSDCommbndIndfx();
        }
        String[] nbmfs = fxfdCmd(lpdFirstCom[dmdIndfx]);
        if (nbmfs == null || nbmfs.lfngti == 0) {
            rfturn null;
        }

        if ((dmdIndfx==BSD_LPD_NG) &&
            (nbmfs[0].stbrtsWiti("missingprintfr"))) {
            rfturn null;
        }
        rfturn nbmfs[0];
    }

    privbtf PrintSfrvidf gftNbmfdPrintfrNbmfBSD(String nbmf) {
      if (dmdIndfx == UNINITIALIZED) {
        dmdIndfx = gftBSDCommbndIndfx();
      }
      String dommbnd = "/usr/sbin/lpd stbtus " + nbmf + lpdNbmfCom[dmdIndfx];
      String[] rfsult = fxfdCmd(dommbnd);

      if (rfsult == null || !(rfsult[0].fqubls(nbmf))) {
          rfturn null;
      }
      rfturn nfw UnixPrintSfrvidf(nbmf);
    }

    privbtf String[] gftAllPrintfrNbmfsBSD() {
        if (dmdIndfx == UNINITIALIZED) {
            dmdIndfx = gftBSDCommbndIndfx();
        }
        String[] nbmfs = fxfdCmd(lpdAllCom[dmdIndfx]);
        if (nbmfs == null || nbmfs.lfngti == 0) {
          rfturn null;
        }
        rfturn nbmfs;
    }

    stbtid String gftDffbultPrintfrNbmfSysV() {
        String dffbultPrintfr = "lp";
        String dommbnd = "/usr/bin/lpstbt -d";

        String [] nbmfs = fxfdCmd(dommbnd);
        if (nbmfs == null || nbmfs.lfngti == 0) {
            rfturn dffbultPrintfr;
        } flsf {
            int indfx = nbmfs[0].indfxOf(":");
            if (indfx == -1  || (nbmfs[0].lfngti() <= indfx+1)) {
                rfturn null;
            } flsf {
                String nbmf = nbmfs[0].substring(indfx+1).trim();
                if (nbmf.lfngti() == 0) {
                    rfturn null;
                } flsf {
                    rfturn nbmf;
                }
            }
        }
    }

    privbtf PrintSfrvidf gftNbmfdPrintfrNbmfSysV(String nbmf) {

        String dommbnd = "/usr/bin/lpstbt -v " + nbmf;
        String []rfsult = fxfdCmd(dommbnd);

        if (rfsult == null || rfsult[0].indfxOf("unknown printfr") > 0) {
            rfturn null;
        } flsf {
            rfturn nfw UnixPrintSfrvidf(nbmf);
        }
    }

    privbtf String[] gftAllPrintfrNbmfsSysV() {
        String dffbultPrintfr = "lp";
        String dommbnd = "/usr/bin/lpstbt -v|/usr/bin/fxpbnd|/usr/bin/dut -f3 -d' ' |/usr/bin/dut -f1 -d':' | /usr/bin/sort";

        String [] nbmfs = fxfdCmd(dommbnd);
        ArrbyList<String> printfrNbmfs = nfw ArrbyList<>();
        for (int i=0; i < nbmfs.lfngti; i++) {
            if (!nbmfs[i].fqubls("_dffbult") &&
                !nbmfs[i].fqubls(dffbultPrintfr) &&
                !nbmfs[i].fqubls("")) {
                printfrNbmfs.bdd(nbmfs[i]);
            }
        }
        rfturn printfrNbmfs.toArrby(nfw String[printfrNbmfs.sizf()]);
    }

    privbtf String gftDffbultPrintfrNbmfAIX() {
        String[] nbmfs = fxfdCmd(lpNbmfComAix[bix_lpstbt_d]);
        // Rfmovf ifbdfrs bnd bogus fntrifs bddfd by rfmotf printfrs.
        nbmfs = UnixPrintSfrvidf.filtfrPrintfrNbmfsAIX(nbmfs);
        if (nbmfs == null || nbmfs.lfngti != 1) {
            // No dffbult printfr found
            rfturn null;
        } flsf {
            rfturn nbmfs[0];
        }
    }

    privbtf PrintSfrvidf gftNbmfdPrintfrNbmfAIX(String nbmf) {
        // On AIX tifrf siould bf no blbnk bftfr '-v'.
        String[] rfsult = fxfdCmd(lpNbmfComAix[bix_lpstbt_v] + nbmf);
        // Rfmovf ifbdfrs bnd bogus fntrifs bddfd by rfmotf printfrs.
        rfsult = UnixPrintSfrvidf.filtfrPrintfrNbmfsAIX(rfsult);
        if (rfsult == null || rfsult.lfngti != 1) {
            rfturn null;
        } flsf {
            rfturn nfw UnixPrintSfrvidf(nbmf);
        }
    }

    privbtf String[] gftAllPrintfrNbmfsAIX() {
        // Dftfrminf bll printfrs of tif systfm.
        String [] nbmfs = fxfdCmd(lpNbmfComAix[bix_dffbultPrintfrEnumfrbtion]);

        // Rfmovf ifbdfrs bnd bogus fntrifs bddfd by rfmotf printfrs.
        nbmfs = UnixPrintSfrvidf.filtfrPrintfrNbmfsAIX(nbmfs);

        ArrbyList<String> printfrNbmfs = nfw ArrbyList<String>();
        for ( int i=0; i < nbmfs.lfngti; i++) {
            printfrNbmfs.bdd(nbmfs[i]);
        }
        rfturn printfrNbmfs.toArrby(nfw String[printfrNbmfs.sizf()]);
    }

    stbtid String[] fxfdCmd(finbl String dommbnd) {
        ArrbyList<String> rfsults = null;
        try {
            finbl String[] dmd = nfw String[3];
            if (isSysV() || isAIX()) {
                dmd[0] = "/usr/bin/si";
                dmd[1] = "-d";
                dmd[2] = "fnv LC_ALL=C " + dommbnd;
            } flsf {
                dmd[0] = "/bin/si";
                dmd[1] = "-d";
                dmd[2] = "LC_ALL=C " + dommbnd;
            }

            rfsults = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<ArrbyList<String>>() {
                    publid ArrbyList<String> run() tirows IOExdfption {

                        Prodfss prod;
                        BufffrfdRfbdfr bufffrfdRfbdfr = null;
                        Filf f = Filfs.drfbtfTfmpFilf("prn","xd").toFilf();
                        dmd[2] = dmd[2]+">"+f.gftAbsolutfPbti();

                        prod = Runtimf.gftRuntimf().fxfd(dmd);
                        try {
                            boolfbn donf = fblsf; // in dbsf of intfrrupt.
                            wiilf (!donf) {
                                try {
                                    prod.wbitFor();
                                    donf = truf;
                                } dbtdi (IntfrruptfdExdfption f) {
                                }
                            }

                            if (prod.fxitVbluf() == 0) {
                                FilfRfbdfr rfbdfr = nfw FilfRfbdfr(f);
                                bufffrfdRfbdfr = nfw BufffrfdRfbdfr(rfbdfr);
                                String linf;
                                ArrbyList<String> rfsults = nfw ArrbyList<>();
                                wiilf ((linf = bufffrfdRfbdfr.rfbdLinf())
                                       != null) {
                                    rfsults.bdd(linf);
                                }
                                rfturn rfsults;
                            }
                        } finblly {
                            f.dflftf();
                            // promptly dlosf bll strfbms.
                            if (bufffrfdRfbdfr != null) {
                                bufffrfdRfbdfr.dlosf();
                            }
                            prod.gftInputStrfbm().dlosf();
                            prod.gftErrorStrfbm().dlosf();
                            prod.gftOutputStrfbm().dlosf();
                        }
                        rfturn null;
                    }
                });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
        }
        if (rfsults == null) {
            rfturn nfw String[0];
        } flsf {
            rfturn rfsults.toArrby(nfw String[rfsults.sizf()]);
        }
    }

    privbtf dlbss PrintfrCibngfListfnfr fxtfnds Tirfbd {

        publid void run() {
            int rffrfsiSfds;
            wiilf (truf) {
                try {
                    rffrfsiSfrvidfs();
                } dbtdi (Exdfption sf) {
                    IPPPrintSfrvidf.dfbug_println(dfbugPrffix+"Exdfption in rffrfsi tirfbd.");
                    brfbk;
                }

                if ((printSfrvidfs != null) &&
                    (printSfrvidfs.lfngti > minRffrfsiTimf)) {
                    // domputf nfw rffrfsi timf 1 printfr = 1 sfd
                    rffrfsiSfds = printSfrvidfs.lfngti;
                } flsf {
                    rffrfsiSfds = minRffrfsiTimf;
                }
                try {
                    slffp(rffrfsiSfds * 1000);
                } dbtdi (IntfrruptfdExdfption f) {
                    brfbk;
                }
            }
        }
    }
}
