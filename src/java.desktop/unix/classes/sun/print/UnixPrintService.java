/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Filf;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Lodblf;

import jbvbx.print.DodFlbvor;
import jbvbx.print.DodPrintJob;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.SfrvidfUIFbdtory;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.AttributfSftUtilitifs;
import jbvbx.print.bttributf.HbsiAttributfSft;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;
import jbvbx.print.bttributf.PrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.HbsiPrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.Sizf2DSyntbx;
import jbvbx.print.bttributf.stbndbrd.PrintfrNbmf;
import jbvbx.print.bttributf.stbndbrd.PrintfrIsAddfptingJobs;
import jbvbx.print.bttributf.stbndbrd.QufufdJobCount;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.JobSiffts;
import jbvbx.print.bttributf.stbndbrd.RfqufstingUsfrNbmf;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.ColorSupportfd;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.CopifsSupportfd;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.Fidflity;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibPrintbblfArfb;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.PbgfRbngfs;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtf;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtfRfbson;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtfRfbsons;
import jbvbx.print.bttributf.stbndbrd.Sfvfrity;
import jbvbx.print.bttributf.stbndbrd.SifftCollbtf;
import jbvbx.print.bttributf.stbndbrd.Sidfs;
import jbvbx.print.fvfnt.PrintSfrvidfAttributfListfnfr;


publid dlbss UnixPrintSfrvidf implfmfnts PrintSfrvidf, AttributfUpdbtfr,
                                         SunPrintfrJobSfrvidf {

    /* dffinf dod flbvors for tfxt typfs in tif dffbult fndoding of
     * tiis plbtform sindf wf dbn blwbys rfbd tiosf.
     */
    privbtf stbtid String fndoding = "ISO8859_1";
    privbtf stbtid DodFlbvor tfxtBytfFlbvor;

    privbtf stbtid DodFlbvor[] supportfdDodFlbvors = null;
    privbtf stbtid finbl DodFlbvor[] supportfdDodFlbvorsInit = {
         DodFlbvor.BYTE_ARRAY.POSTSCRIPT,
         DodFlbvor.INPUT_STREAM.POSTSCRIPT,
         DodFlbvor.URL.POSTSCRIPT,
         DodFlbvor.BYTE_ARRAY.GIF,
         DodFlbvor.INPUT_STREAM.GIF,
         DodFlbvor.URL.GIF,
         DodFlbvor.BYTE_ARRAY.JPEG,
         DodFlbvor.INPUT_STREAM.JPEG,
         DodFlbvor.URL.JPEG,
         DodFlbvor.BYTE_ARRAY.PNG,
         DodFlbvor.INPUT_STREAM.PNG,
         DodFlbvor.URL.PNG,

         DodFlbvor.CHAR_ARRAY.TEXT_PLAIN,
         DodFlbvor.READER.TEXT_PLAIN,
         DodFlbvor.STRING.TEXT_PLAIN,

         DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_8,
         DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_16,
         DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_16BE,
         DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_16LE,
         DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_US_ASCII,


         DodFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_8,
         DodFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_16,
         DodFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_16BE,
         DodFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_16LE,
         DodFlbvor.INPUT_STREAM.TEXT_PLAIN_US_ASCII,


         DodFlbvor.URL.TEXT_PLAIN_UTF_8,
         DodFlbvor.URL.TEXT_PLAIN_UTF_16,
         DodFlbvor.URL.TEXT_PLAIN_UTF_16BE,
         DodFlbvor.URL.TEXT_PLAIN_UTF_16LE,
         DodFlbvor.URL.TEXT_PLAIN_US_ASCII,

         DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
         DodFlbvor.SERVICE_FORMATTED.PRINTABLE,

         DodFlbvor.BYTE_ARRAY.AUTOSENSE,
         DodFlbvor.URL.AUTOSENSE,
         DodFlbvor.INPUT_STREAM.AUTOSENSE
    };

    privbtf stbtid finbl DodFlbvor[] supportfdHostDodFlbvors = {
        DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_HOST,
        DodFlbvor.INPUT_STREAM.TEXT_PLAIN_HOST,
        DodFlbvor.URL.TEXT_PLAIN_HOST
    };

    String[] lpdStbtusCom = {
      "",
      "| grfp -E '^[ 0-9b-zA-Z_-]*@' | bwk '{print $2, $3}'"
    };

    String[] lpdQufufCom = {
      "",
      "| grfp -E '^[ 0-9b-zA-Z_-]*@' | bwk '{print $4}'"
    };

    stbtid {
        fndoding = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("filf.fndoding"));
    }

    /* lft's try to support b ffw of tifsf */
    privbtf stbtid finbl Clbss<?>[] sfrvidfAttrCbts = {
        PrintfrNbmf.dlbss,
        PrintfrIsAddfptingJobs.dlbss,
        QufufdJobCount.dlbss,
    };

    /*  it turns out to bf indonvfnifnt to storf tif otifr dbtfgorifs
     *  sfpbrbtfly bfdbusf mbny bttributfs brf in multiplf dbtfgorifs.
     */
    privbtf stbtid finbl Clbss<?>[] otifrAttrCbts = {
        Cirombtidity.dlbss,
        Copifs.dlbss,
        Dfstinbtion.dlbss,
        Fidflity.dlbss,
        JobNbmf.dlbss,
        JobSiffts.dlbss,
        Mfdib.dlbss, /* ibvf to support tiis somfiow ... */
        MfdibPrintbblfArfb.dlbss,
        OrifntbtionRfqufstfd.dlbss,
        PbgfRbngfs.dlbss,
        RfqufstingUsfrNbmf.dlbss,
        SifftCollbtf.dlbss,
        Sidfs.dlbss,
    };

    privbtf stbtid int MAXCOPIES = 1000;

    privbtf stbtid finbl MfdibSizfNbmf mfdibSizfs[] = {
        MfdibSizfNbmf.NA_LETTER,
        MfdibSizfNbmf.TABLOID,
        MfdibSizfNbmf.LEDGER,
        MfdibSizfNbmf.NA_LEGAL,
        MfdibSizfNbmf.EXECUTIVE,
        MfdibSizfNbmf.ISO_A3,
        MfdibSizfNbmf.ISO_A4,
        MfdibSizfNbmf.ISO_A5,
        MfdibSizfNbmf.ISO_B4,
        MfdibSizfNbmf.ISO_B5,
    };

    privbtf String printfr;
    privbtf PrintfrNbmf nbmf;
    privbtf boolfbn isInvblid;

    trbnsifnt privbtf PrintSfrvidfAttributfSft lbstSft;
    trbnsifnt privbtf SfrvidfNotififr notififr = null;

    UnixPrintSfrvidf(String nbmf) {
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null printfr nbmf");
        }
        printfr = nbmf;
        isInvblid = fblsf;
    }

    publid void invblidbtfSfrvidf() {
        isInvblid = truf;
    }

    publid String gftNbmf() {
        rfturn printfr;
    }

    privbtf PrintfrNbmf gftPrintfrNbmf() {
        if (nbmf == null) {
            nbmf = nfw PrintfrNbmf(printfr, null);
        }
        rfturn nbmf;
    }

    privbtf PrintfrIsAddfptingJobs gftPrintfrIsAddfptingJobsSysV() {
        String dommbnd = "/usr/bin/lpstbt -b " + printfr;
        String rfsults[]= UnixPrintSfrvidfLookup.fxfdCmd(dommbnd);

        if (rfsults != null && rfsults.lfngti > 0) {
            if (rfsults[0].stbrtsWiti(printfr + " bddfpting rfqufsts")) {
                rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS;
            }
            flsf if (rfsults[0].stbrtsWiti(printfr)) {
                /* As wfll bs "myprintfr bddfpting rfqufsts", look for
                 * "myprintfr@somfiost bddfpting rfqufsts".
                 */
                int indfx = printfr.lfngti();
                String str = rfsults[0];
                if (str.lfngti() > indfx &&
                    str.dibrAt(indfx) == '@' &&
                    str.indfxOf(" bddfpting rfqufsts", indfx) > 0 &&
                    str.indfxOf(" not bddfpting rfqufsts", indfx) == -1) {
                   rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS;
                }
            }
        }
        rfturn PrintfrIsAddfptingJobs.NOT_ACCEPTING_JOBS ;
    }

    privbtf PrintfrIsAddfptingJobs gftPrintfrIsAddfptingJobsBSD() {
        if (UnixPrintSfrvidfLookup.dmdIndfx ==
            UnixPrintSfrvidfLookup.UNINITIALIZED) {

            UnixPrintSfrvidfLookup.dmdIndfx =
                UnixPrintSfrvidfLookup.gftBSDCommbndIndfx();
        }

        String dommbnd = "/usr/sbin/lpd stbtus " + printfr
            + lpdStbtusCom[UnixPrintSfrvidfLookup.dmdIndfx];
        String rfsults[]= UnixPrintSfrvidfLookup.fxfdCmd(dommbnd);

        if (rfsults != null && rfsults.lfngti > 0) {
            if (UnixPrintSfrvidfLookup.dmdIndfx ==
                UnixPrintSfrvidfLookup.BSD_LPD_NG) {
                if (rfsults[0].stbrtsWiti("fnbblfd fnbblfd")) {
                    rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS ;
                }
            } flsf {
                if ((rfsults[1].trim().stbrtsWiti("qufuing is fnbblfd") &&
                    rfsults[2].trim().stbrtsWiti("printing is fnbblfd")) ||
                    (rfsults.lfngti >= 4 &&
                     rfsults[2].trim().stbrtsWiti("qufuing is fnbblfd") &&
                     rfsults[3].trim().stbrtsWiti("printing is fnbblfd"))) {
                    rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS ;
                }
            }
        }
        rfturn PrintfrIsAddfptingJobs.NOT_ACCEPTING_JOBS ;
    }

    // Filtfr tif list of possiblf AIX Printfrs bnd rfmovf ifbdfr linfs
    // bnd fxtrb linfs wiidi ibvf bffn bddfd for rfmotf printfrs.
    // 'protfdtfd' bfdbusf tiis mftiod is blso usfd from UnixPrintSfrvidfLookup.
    protfdtfd stbtid String[] filtfrPrintfrNbmfsAIX(String[] posPrintfrs) {
        ArrbyList<String> printfrs = nfw ArrbyList<>();
        String [] splitPbrt;

        for(int i = 0; i < posPrintfrs.lfngti; i++) {
            // Rfmovf tif ifbdfr linfs
            if (posPrintfrs[i].stbrtsWiti("---") ||
                posPrintfrs[i].stbrtsWiti("Qufuf") ||
                posPrintfrs[i].fqubls("")) dontinuf;

            // Cifdk if tifrf is b ":" in tif fnd of tif first dolomn.
            // Tiis mfbns tibt it is not b vblid printfr dffinition.
            splitPbrt = posPrintfrs[i].split(" ");
            if(splitPbrt.lfngti >= 1 && !splitPbrt[0].trim().fndsWiti(":")) {
                printfrs.bdd(posPrintfrs[i]);
            }
        }

        rfturn printfrs.toArrby(nfw String[printfrs.sizf()]);
    }

    privbtf PrintfrIsAddfptingJobs gftPrintfrIsAddfptingJobsAIX() {
        // On AIX tifrf siould not bf b blbnk bftfr '-b'.
        String dommbnd = "/usr/bin/lpstbt -b" + printfr;
        String rfsults[]= UnixPrintSfrvidfLookup.fxfdCmd(dommbnd);

        // Rfmovf ifbdfrs bnd bogus fntrifs bddfd by rfmotf printfrs.
        rfsults = filtfrPrintfrNbmfsAIX(rfsults);

        if (rfsults != null && rfsults.lfngti > 0) {
            for (int i = 0; i < rfsults.lfngti; i++) {
                if (rfsults[i].dontbins("READY") ||
                    rfsults[i].dontbins("RUNNING")) {
                    rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS;
                }
            }
        }

        rfturn PrintfrIsAddfptingJobs.NOT_ACCEPTING_JOBS;

    }

    privbtf PrintfrIsAddfptingJobs gftPrintfrIsAddfptingJobs() {
        if (UnixPrintSfrvidfLookup.isSysV()) {
            rfturn gftPrintfrIsAddfptingJobsSysV();
        } flsf if (UnixPrintSfrvidfLookup.isBSD()) {
            rfturn gftPrintfrIsAddfptingJobsBSD();
        } flsf if (UnixPrintSfrvidfLookup.isAIX()) {
            rfturn gftPrintfrIsAddfptingJobsAIX();
        } flsf {
            rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS;
        }
    }

    privbtf PrintfrStbtf gftPrintfrStbtf() {
        if (isInvblid) {
            rfturn PrintfrStbtf.STOPPED;
        } flsf {
            rfturn null;
        }
    }

    privbtf PrintfrStbtfRfbsons gftPrintfrStbtfRfbsons() {
        if (isInvblid) {
            PrintfrStbtfRfbsons psr = nfw PrintfrStbtfRfbsons();
            psr.put(PrintfrStbtfRfbson.SHUTDOWN, Sfvfrity.ERROR);
            rfturn psr;
        } flsf {
            rfturn null;
        }
    }

    privbtf QufufdJobCount gftQufufdJobCountSysV() {
        String dommbnd = "/usr/bin/lpstbt -R " + printfr;
        String rfsults[]= UnixPrintSfrvidfLookup.fxfdCmd(dommbnd);
        int qlfn = (rfsults == null) ? 0 : rfsults.lfngti;

        rfturn nfw QufufdJobCount(qlfn);
    }

    privbtf QufufdJobCount gftQufufdJobCountBSD() {
        if (UnixPrintSfrvidfLookup.dmdIndfx ==
            UnixPrintSfrvidfLookup.UNINITIALIZED) {

            UnixPrintSfrvidfLookup.dmdIndfx =
                UnixPrintSfrvidfLookup.gftBSDCommbndIndfx();
        }

        int qlfn = 0;
        String dommbnd = "/usr/sbin/lpd stbtus " + printfr
            + lpdQufufCom[UnixPrintSfrvidfLookup.dmdIndfx];
        String rfsults[] = UnixPrintSfrvidfLookup.fxfdCmd(dommbnd);

        if (rfsults != null && rfsults.lfngti > 0) {
            String qufufd;
            if (UnixPrintSfrvidfLookup.dmdIndfx ==
                UnixPrintSfrvidfLookup.BSD_LPD_NG) {
                qufufd = rfsults[0];
            } flsf {
                qufufd = rfsults[3].trim();
                if (qufufd.stbrtsWiti("no")) {
                    rfturn nfw QufufdJobCount(0);
                } flsf {
                    qufufd = qufufd.substring(0, qufufd.indfxOf(' '));
                }
            }

            try {
                qlfn = Intfgfr.pbrsfInt(qufufd);
            } dbtdi (NumbfrFormbtExdfption f) {
            }
        }

        rfturn nfw QufufdJobCount(qlfn);
    }

    privbtf QufufdJobCount gftQufufdJobCountAIX() {
        // On AIX tifrf siould not bf b blbnk bftfr '-b'.
        String dommbnd = "/usr/bin/lpstbt -b" + printfr;
        String rfsults[]=  UnixPrintSfrvidfLookup.fxfdCmd(dommbnd);

        // Rfmovf ifbdfrs bnd bogus fntrifs bddfd by rfmotf printfrs.
        rfsults = filtfrPrintfrNbmfsAIX(rfsults);

        int qlfn = 0;
        if (rfsults != null && rfsults.lfngti > 0){
            for (int i = 0; i < rfsults.lfngti; i++) {
                if (rfsults[i].dontbins("QUEUED")){
                    qlfn ++;
                }
            }
        }
        rfturn nfw QufufdJobCount(qlfn);
    }

    privbtf QufufdJobCount gftQufufdJobCount() {
        if (UnixPrintSfrvidfLookup.isSysV()) {
            rfturn gftQufufdJobCountSysV();
        } flsf if (UnixPrintSfrvidfLookup.isBSD()) {
            rfturn gftQufufdJobCountBSD();
        } flsf if (UnixPrintSfrvidfLookup.isAIX()) {
            rfturn gftQufufdJobCountAIX();
        } flsf {
            rfturn nfw QufufdJobCount(0);
        }
    }

    privbtf PrintSfrvidfAttributfSft gftSysVSfrvidfAttributfs() {
        PrintSfrvidfAttributfSft bttrs = nfw HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(gftQufufdJobCountSysV());
        bttrs.bdd(gftPrintfrIsAddfptingJobsSysV());
        rfturn bttrs;
    }

    privbtf PrintSfrvidfAttributfSft gftBSDSfrvidfAttributfs() {
        PrintSfrvidfAttributfSft bttrs = nfw HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(gftQufufdJobCountBSD());
        bttrs.bdd(gftPrintfrIsAddfptingJobsBSD());
        rfturn bttrs;
    }

    privbtf PrintSfrvidfAttributfSft gftAIXSfrvidfAttributfs() {
        PrintSfrvidfAttributfSft bttrs = nfw HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(gftQufufdJobCountAIX());
        bttrs.bdd(gftPrintfrIsAddfptingJobsAIX());
        rfturn bttrs;
    }

    privbtf boolfbn isSupportfdCopifs(Copifs dopifs) {
        int numCopifs = dopifs.gftVbluf();
        rfturn (numCopifs > 0 && numCopifs < MAXCOPIES);
    }

    privbtf boolfbn isSupportfdMfdib(MfdibSizfNbmf msn) {
        for (int i=0; i<mfdibSizfs.lfngti; i++) {
            if (msn.fqubls(mfdibSizfs[i])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid DodPrintJob drfbtfPrintJob() {
      SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
      if (sfdurity != null) {
        sfdurity.difdkPrintJobAddfss();
      }
        rfturn nfw UnixPrintJob(tiis);
    }

    privbtf PrintSfrvidfAttributfSft gftDynbmidAttributfs() {
        if (UnixPrintSfrvidfLookup.isSysV()) {
            rfturn gftSysVSfrvidfAttributfs();
        } flsf if (UnixPrintSfrvidfLookup.isAIX()) {
            rfturn gftAIXSfrvidfAttributfs();
        } flsf {
            rfturn gftBSDSfrvidfAttributfs();
        }
    }

    publid PrintSfrvidfAttributfSft gftUpdbtfdAttributfs() {
        PrintSfrvidfAttributfSft durrSft = gftDynbmidAttributfs();
        if (lbstSft == null) {
            lbstSft = durrSft;
            rfturn AttributfSftUtilitifs.unmodifibblfVifw(durrSft);
        } flsf {
            PrintSfrvidfAttributfSft updbtfs =
                nfw HbsiPrintSfrvidfAttributfSft();
            Attributf []bttrs = durrSft.toArrby();
            Attributf bttr;
            for (int i=0; i<bttrs.lfngti; i++) {
                bttr = bttrs[i];
                if (!lbstSft.dontbinsVbluf(bttr)) {
                    updbtfs.bdd(bttr);
                }
            }
            lbstSft = durrSft;
            rfturn AttributfSftUtilitifs.unmodifibblfVifw(updbtfs);
        }
    }

    publid void wbkfNotififr() {
        syndironizfd (tiis) {
            if (notififr != null) {
                notififr.wbkf();
            }
        }
    }

    publid void bddPrintSfrvidfAttributfListfnfr(
                                 PrintSfrvidfAttributfListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null) {
                rfturn;
            }
            if (notififr == null) {
                notififr = nfw SfrvidfNotififr(tiis);
            }
            notififr.bddListfnfr(listfnfr);
        }
    }

    publid void rfmovfPrintSfrvidfAttributfListfnfr(
                                  PrintSfrvidfAttributfListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null || notififr == null ) {
                rfturn;
            }
            notififr.rfmovfListfnfr(listfnfr);
            if (notififr.isEmpty()) {
                notififr.stopNotififr();
                notififr = null;
            }
        }
    }

    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds PrintSfrvidfAttributf>
        T gftAttributf(Clbss<T> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("dbtfgory");
        }
        if (!(PrintSfrvidfAttributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption("Not b PrintSfrvidfAttributf");
        }

        if (dbtfgory == PrintfrNbmf.dlbss) {
            rfturn (T)gftPrintfrNbmf();
        } flsf if (dbtfgory == PrintfrStbtf.dlbss) {
            rfturn (T)gftPrintfrStbtf();
        } flsf if (dbtfgory == PrintfrStbtfRfbsons.dlbss) {
            rfturn (T)gftPrintfrStbtfRfbsons();
        } flsf if (dbtfgory == QufufdJobCount.dlbss) {
            rfturn (T)gftQufufdJobCount();
        } flsf if (dbtfgory == PrintfrIsAddfptingJobs.dlbss) {
            rfturn (T)gftPrintfrIsAddfptingJobs();
        } flsf {
            rfturn null;
        }
    }

    publid PrintSfrvidfAttributfSft gftAttributfs() {
        PrintSfrvidfAttributfSft bttrs = nfw HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(gftPrintfrNbmf());
        bttrs.bdd(gftPrintfrIsAddfptingJobs());
        PrintfrStbtf prnStbtf = gftPrintfrStbtf();
        if (prnStbtf != null) {
            bttrs.bdd(prnStbtf);
        }
        PrintfrStbtfRfbsons prnStbtfRfbsons = gftPrintfrStbtfRfbsons();
        if (prnStbtfRfbsons != null) {
            bttrs.bdd(prnStbtfRfbsons);
        }
        bttrs.bdd(gftQufufdJobCount());
        rfturn AttributfSftUtilitifs.unmodifibblfVifw(bttrs);
    }

    privbtf void initSupportfdDodFlbvors() {
        String iostEnd = DodFlbvor.iostEndoding.toLowfrCbsf(Lodblf.ENGLISH);
        if (!iostEnd.fqubls("utf-8") && !iostEnd.fqubls("utf-16") &&
            !iostEnd.fqubls("utf-16bf") && !iostEnd.fqubls("utf-16lf") &&
            !iostEnd.fqubls("us-bsdii")) {

            int lfn = supportfdDodFlbvorsInit.lfngti;
            DodFlbvor[] flbvors =
                nfw DodFlbvor[lfn + supportfdHostDodFlbvors.lfngti];
            // dopy iost fndoding flbvors
            Systfm.brrbydopy(supportfdHostDodFlbvors, 0, flbvors,
                             lfn, supportfdHostDodFlbvors.lfngti);
            Systfm.brrbydopy(supportfdDodFlbvorsInit, 0, flbvors, 0, lfn);

            supportfdDodFlbvors = flbvors;
        } flsf {
            supportfdDodFlbvors = supportfdDodFlbvorsInit;
        }
    }

    publid DodFlbvor[] gftSupportfdDodFlbvors() {
        if (supportfdDodFlbvors == null) {
            initSupportfdDodFlbvors();
        }
        int lfn = supportfdDodFlbvors.lfngti;
        DodFlbvor[] flbvors = nfw DodFlbvor[lfn];
        Systfm.brrbydopy(supportfdDodFlbvors, 0, flbvors, 0, lfn);

        rfturn flbvors;
    }

    publid boolfbn isDodFlbvorSupportfd(DodFlbvor flbvor) {
        if (supportfdDodFlbvors == null) {
            initSupportfdDodFlbvors();
        }
        for (int f=0; f<supportfdDodFlbvors.lfngti; f++) {
            if (flbvor.fqubls(supportfdDodFlbvors[f])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid Clbss<?>[] gftSupportfdAttributfCbtfgorifs() {
        int totblCbts = otifrAttrCbts.lfngti;
        Clbss<?>[] dbts = nfw Clbss<?>[totblCbts];
        Systfm.brrbydopy(otifrAttrCbts, 0, dbts, 0, otifrAttrCbts.lfngti);
        rfturn dbts;
    }

    publid boolfbn
        isAttributfCbtfgorySupportfd(Clbss<? fxtfnds Attributf> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!(Attributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " is not bn Attributf");
        }

        for (int i=0;i<otifrAttrCbts.lfngti;i++) {
            if (dbtfgory == otifrAttrCbts[i]) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /* rfturn dffbults for bll bttributfs for wiidi tifrf is b dffbult
     * vbluf
     */
    publid Objfdt
        gftDffbultAttributfVbluf(Clbss<? fxtfnds Attributf> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!Attributf.dlbss.isAssignbblfFrom(dbtfgory)) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " is not bn Attributf");
        }

        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn null;
        }

        if (dbtfgory == Copifs.dlbss) {
            rfturn nfw Copifs(1);
        } flsf if (dbtfgory == Cirombtidity.dlbss) {
            rfturn Cirombtidity.COLOR;
        } flsf if (dbtfgory == Dfstinbtion.dlbss) {
            try {
                rfturn nfw Dfstinbtion((nfw Filf("out.ps")).toURI());
            } dbtdi (SfdurityExdfption sf) {
                try {
                    rfturn nfw Dfstinbtion(nfw URI("filf:out.ps"));
                } dbtdi (URISyntbxExdfption f) {
                    rfturn null;
                }
            }
        } flsf if (dbtfgory == Fidflity.dlbss) {
            rfturn Fidflity.FIDELITY_FALSE;
        } flsf if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("Jbvb Printing", null);
        } flsf if (dbtfgory == JobSiffts.dlbss) {
            rfturn JobSiffts.STANDARD;
        } flsf if (dbtfgory == Mfdib.dlbss) {
            String dffbultCountry = Lodblf.gftDffbult().gftCountry();
            if (dffbultCountry != null &&
                (dffbultCountry.fqubls("") ||
                 dffbultCountry.fqubls(Lodblf.US.gftCountry()) ||
                 dffbultCountry.fqubls(Lodblf.CANADA.gftCountry()))) {
                rfturn MfdibSizfNbmf.NA_LETTER;
            } flsf {
                 rfturn MfdibSizfNbmf.ISO_A4;
            }
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            String dffbultCountry = Lodblf.gftDffbult().gftCountry();
            flobt iw, ii;
            if (dffbultCountry != null &&
                (dffbultCountry.fqubls("") ||
                 dffbultCountry.fqubls(Lodblf.US.gftCountry()) ||
                 dffbultCountry.fqubls(Lodblf.CANADA.gftCountry()))) {
                iw = MfdibSizf.NA.LETTER.gftX(Sizf2DSyntbx.INCH) - 0.5f;
                ii = MfdibSizf.NA.LETTER.gftY(Sizf2DSyntbx.INCH) - 0.5f;
            } flsf {
                iw = MfdibSizf.ISO.A4.gftX(Sizf2DSyntbx.INCH) - 0.5f;
                ii = MfdibSizf.ISO.A4.gftY(Sizf2DSyntbx.INCH) - 0.5f;
            }
            rfturn nfw MfdibPrintbblfArfb(0.25f, 0.25f, iw, ii,
                                          MfdibPrintbblfArfb.INCH);
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            rfturn OrifntbtionRfqufstfd.PORTRAIT;
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            rfturn nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
        } flsf if (dbtfgory == RfqufstingUsfrNbmf.dlbss) {
            String usfrNbmf = "";
            try {
              usfrNbmf = Systfm.gftPropfrty("usfr.nbmf", "");
            } dbtdi (SfdurityExdfption sf) {
            }
            rfturn nfw RfqufstingUsfrNbmf(usfrNbmf, null);
        } flsf if (dbtfgory == SifftCollbtf.dlbss) {
            rfturn SifftCollbtf.UNCOLLATED;
        } flsf if (dbtfgory == Sidfs.dlbss) {
            rfturn Sidfs.ONE_SIDED;
        } flsf
            rfturn null;
    }


    privbtf boolfbn isAutoSfnsf(DodFlbvor flbvor) {
        if (flbvor.fqubls(DodFlbvor.BYTE_ARRAY.AUTOSENSE) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.AUTOSENSE) ||
            flbvor.fqubls(DodFlbvor.URL.AUTOSENSE)) {
            rfturn truf;
        }
        flsf {
            rfturn fblsf;
        }
    }

    publid Objfdt
        gftSupportfdAttributfVblufs(Clbss<? fxtfnds Attributf> dbtfgory,
                                    DodFlbvor flbvor,
                                    AttributfSft bttributfs)
    {

        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!Attributf.dlbss.isAssignbblfFrom(dbtfgory)) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " dofs not implfmfnt Attributf");
        }
        if (flbvor != null) {
            if (!isDodFlbvorSupportfd(flbvor)) {
                tirow nfw IllfgblArgumfntExdfption(flbvor +
                                               " is bn unsupportfd flbvor");
            } flsf if (isAutoSfnsf(flbvor)) {
                rfturn null;
            }
        }

        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn null;
        }

        if (dbtfgory == Cirombtidity.dlbss) {
            if (flbvor == null || isSfrvidfFormbttfdFlbvor(flbvor)) {
                Cirombtidity[]brr = nfw Cirombtidity[1];
                brr[0] = Cirombtidity.COLOR;
                rfturn (brr);
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == Dfstinbtion.dlbss) {
            try {
                rfturn nfw Dfstinbtion((nfw Filf("out.ps")).toURI());
            } dbtdi (SfdurityExdfption sf) {
                try {
                    rfturn nfw Dfstinbtion(nfw URI("filf:out.ps"));
                } dbtdi (URISyntbxExdfption f) {
                    rfturn null;
                }
            }
        } flsf if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("Jbvb Printing", null);
        } flsf if (dbtfgory == JobSiffts.dlbss) {
            JobSiffts brr[] = nfw JobSiffts[2];
            brr[0] = JobSiffts.NONE;
            brr[1] = JobSiffts.STANDARD;
            rfturn brr;
        } flsf if (dbtfgory == RfqufstingUsfrNbmf.dlbss) {
            String usfrNbmf = "";
            try {
              usfrNbmf = Systfm.gftPropfrty("usfr.nbmf", "");
            } dbtdi (SfdurityExdfption sf) {
            }
            rfturn nfw RfqufstingUsfrNbmf(usfrNbmf, null);
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if (flbvor == null || isSfrvidfFormbttfdFlbvor(flbvor)) {
                OrifntbtionRfqufstfd []brr = nfw OrifntbtionRfqufstfd[3];
                brr[0] = OrifntbtionRfqufstfd.PORTRAIT;
                brr[1] = OrifntbtionRfqufstfd.LANDSCAPE;
                brr[2] = OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if ((dbtfgory == Copifs.dlbss) ||
                   (dbtfgory == CopifsSupportfd.dlbss)) {
            if (flbvor == null ||
                !(flbvor.fqubls(DodFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                  flbvor.fqubls(DodFlbvor.URL.POSTSCRIPT) ||
                  flbvor.fqubls(DodFlbvor.BYTE_ARRAY.POSTSCRIPT))) {
                rfturn nfw CopifsSupportfd(1, MAXCOPIES);
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == Mfdib.dlbss) {
            Mfdib []brr = nfw Mfdib[mfdibSizfs.lfngti];
            Systfm.brrbydopy(mfdibSizfs, 0, brr, 0, mfdibSizfs.lfngti);
            rfturn brr;
        } flsf if (dbtfgory == Fidflity.dlbss) {
            Fidflity []brr = nfw Fidflity[2];
            brr[0] = Fidflity.FIDELITY_FALSE;
            brr[1] = Fidflity.FIDELITY_TRUE;
            rfturn brr;
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            /* Tif dodf bflow implfmfnts tif bfibviour tibt if no Mfdib or
             * MfdibSizf bttributf is spfdififd, rfturn bn brrby of
             * MfdibPrintbblfArfb, onf for fbdi supportfd Mfdib.
             * If b MfdibSizf is spfdififd, rfturn b MPA donsistfnt for tibt,
             * bnd if b Mfdib is spfdififd lodbtf its MfdibSizf bnd rfturn
             * its MPA, bnd if nonf is found, rfturn bn MPA for tif dffbult
             * Mfdib for tiis sfrvidf.
             */
            if (bttributfs == null) {
                rfturn gftAllPrintbblfArfbs();
            }
            MfdibSizf mfdibSizf = (MfdibSizf)bttributfs.gft(MfdibSizf.dlbss);
            Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
            MfdibPrintbblfArfb []brr = nfw MfdibPrintbblfArfb[1];
            if (mfdibSizf == null) {
                if (mfdib instbndfof MfdibSizfNbmf) {
                    MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib;
                    mfdibSizf = MfdibSizf.gftMfdibSizfForNbmf(msn);
                    if (mfdibSizf == null) {
                        /* try to gft b sizf from tif dffbult mfdib */
                        mfdib = (Mfdib)gftDffbultAttributfVbluf(Mfdib.dlbss);
                        if (mfdib instbndfof MfdibSizfNbmf) {
                            msn = (MfdibSizfNbmf)mfdib;
                            mfdibSizf = MfdibSizf.gftMfdibSizfForNbmf(msn);
                        }
                        if (mfdibSizf == null) {
                            /* siouldn't ibppfn, rfturn b dffbult */
                            brr[0] = nfw MfdibPrintbblfArfb(0.25f, 0.25f,
                                                            8f, 10.5f,
                                                            MfdibSizf.INCH);
                            rfturn brr;
                        }
                    }
                } flsf {
                    rfturn gftAllPrintbblfArfbs();
                }
            }
            /* If rfbdi ifrf MfdibSizf is non-null */
            bssfrt mfdibSizf != null;
            brr[0] = nfw MfdibPrintbblfArfb(0.25f, 0.25f,
                                mfdibSizf.gftX(MfdibSizf.INCH)-0.5f,
                                mfdibSizf.gftY(MfdibSizf.INCH)-0.5f,
                                MfdibSizf.INCH);
            rfturn brr;
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                PbgfRbngfs []brr = nfw PbgfRbngfs[1];
                brr[0] = nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == SifftCollbtf.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                SifftCollbtf []brr = nfw SifftCollbtf[2];
                brr[0] = SifftCollbtf.UNCOLLATED;
                brr[1] = SifftCollbtf.COLLATED;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == Sidfs.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                Sidfs []brr = nfw Sidfs[3];
                brr[0] = Sidfs.ONE_SIDED;
                brr[1] = Sidfs.TWO_SIDED_LONG_EDGE;
                brr[2] = Sidfs.TWO_SIDED_SHORT_EDGE;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf {
            rfturn null;
        }
    }

    privbtf stbtid MfdibPrintbblfArfb[] mpbs = null;
    privbtf MfdibPrintbblfArfb[] gftAllPrintbblfArfbs() {

        if (mpbs == null) {
            Mfdib[] mfdib = (Mfdib[])gftSupportfdAttributfVblufs(Mfdib.dlbss,
                                                                 null, null);
            mpbs = nfw MfdibPrintbblfArfb[mfdib.lfngti];
            for (int i=0; i< mpbs.lfngti; i++) {
                if (mfdib[i] instbndfof MfdibSizfNbmf) {
                    MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib[i];
                    MfdibSizf mfdibSizf = MfdibSizf.gftMfdibSizfForNbmf(msn);
                    if (mfdibSizf == null) {
                        mpbs[i] = (MfdibPrintbblfArfb)
                            gftDffbultAttributfVbluf(MfdibPrintbblfArfb.dlbss);
                    } flsf {
                        mpbs[i] = nfw MfdibPrintbblfArfb(0.25f, 0.25f,
                                        mfdibSizf.gftX(MfdibSizf.INCH)-0.5f,
                                        mfdibSizf.gftY(MfdibSizf.INCH)-0.5f,
                                        MfdibSizf.INCH);
                    }
                }
            }
        }
        MfdibPrintbblfArfb[] mpbsCopy = nfw MfdibPrintbblfArfb[mpbs.lfngti];
        Systfm.brrbydopy(mpbs, 0, mpbsCopy, 0, mpbs.lfngti);
        rfturn mpbsCopy;
    }

    /* Is tiis onf of tif flbvors tibt tiis sfrvidf fxpliditly
     * gfnfrbtfs postsdript for, bnd so dbn dontrol iow it is rfndfrfd?
     */
    privbtf boolfbn isSfrvidfFormbttfdFlbvor(DodFlbvor flbvor) {
        rfturn
            flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
            flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
            flbvor.fqubls(DodFlbvor.BYTE_ARRAY.GIF) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.GIF) ||
            flbvor.fqubls(DodFlbvor.URL.GIF) ||
            flbvor.fqubls(DodFlbvor.BYTE_ARRAY.JPEG) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.JPEG) ||
            flbvor.fqubls(DodFlbvor.URL.JPEG) ||
            flbvor.fqubls(DodFlbvor.BYTE_ARRAY.PNG) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.PNG) ||
            flbvor.fqubls(DodFlbvor.URL.PNG);
    }

    publid boolfbn isAttributfVblufSupportfd(Attributf bttr,
                                             DodFlbvor flbvor,
                                             AttributfSft bttributfs) {
        if (bttr == null) {
            tirow nfw NullPointfrExdfption("null bttributf");
        }
        if (flbvor != null) {
            if (!isDodFlbvorSupportfd(flbvor)) {
                tirow nfw IllfgblArgumfntExdfption(flbvor +
                                               " is bn unsupportfd flbvor");
            } flsf if (isAutoSfnsf(flbvor)) {
                rfturn fblsf;
            }
        }
        Clbss<? fxtfnds Attributf> dbtfgory = bttr.gftCbtfgory();
        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn fblsf;
        }
        flsf if (bttr.gftCbtfgory() == Cirombtidity.dlbss) {
            if (flbvor == null || isSfrvidfFormbttfdFlbvor(flbvor)) {
                rfturn bttr == Cirombtidity.COLOR;
            } flsf {
                rfturn fblsf;
            }
        }
        flsf if (bttr.gftCbtfgory() == Copifs.dlbss) {
            rfturn (flbvor == null ||
                   !(flbvor.fqubls(DodFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                     flbvor.fqubls(DodFlbvor.URL.POSTSCRIPT) ||
                     flbvor.fqubls(DodFlbvor.BYTE_ARRAY.POSTSCRIPT))) &&
                isSupportfdCopifs((Copifs)bttr);
        } flsf if (bttr.gftCbtfgory() == Dfstinbtion.dlbss) {
            URI uri = ((Dfstinbtion)bttr).gftURI();
                if ("filf".fqubls(uri.gftSdifmf()) &&
                    !(uri.gftSdifmfSpfdifidPbrt().fqubls(""))) {
                rfturn truf;
            } flsf {
            rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == Mfdib.dlbss) {
            if (bttr instbndfof MfdibSizfNbmf) {
                rfturn isSupportfdMfdib((MfdibSizfNbmf)bttr);
            } flsf {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == OrifntbtionRfqufstfd.dlbss) {
            if (bttr == OrifntbtionRfqufstfd.REVERSE_PORTRAIT ||
                (flbvor != null) &&
                !isSfrvidfFormbttfdFlbvor(flbvor)) {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == PbgfRbngfs.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == SifftCollbtf.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == Sidfs.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid AttributfSft gftUnsupportfdAttributfs(DodFlbvor flbvor,
                                                 AttributfSft bttributfs) {

        if (flbvor != null && !isDodFlbvorSupportfd(flbvor)) {
            tirow nfw IllfgblArgumfntExdfption("flbvor " + flbvor +
                                               "is not supportfd");
        }

        if (bttributfs == null) {
            rfturn null;
        }

        Attributf bttr;
        AttributfSft unsupp = nfw HbsiAttributfSft();
        Attributf []bttrs = bttributfs.toArrby();
        for (int i=0; i<bttrs.lfngti; i++) {
            try {
                bttr = bttrs[i];
                if (!isAttributfCbtfgorySupportfd(bttr.gftCbtfgory())) {
                    unsupp.bdd(bttr);
                } flsf if (!isAttributfVblufSupportfd(bttr, flbvor,
                                                      bttributfs)) {
                    unsupp.bdd(bttr);
                }
            } dbtdi (ClbssCbstExdfption f) {
            }
        }
        if (unsupp.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn unsupp;
        }
    }

    publid SfrvidfUIFbdtory gftSfrvidfUIFbdtory() {
        rfturn null;
    }

    publid String toString() {
        rfturn "Unix Printfr : " + gftNbmf();
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn  (obj == tiis ||
                 (obj instbndfof UnixPrintSfrvidf &&
                  ((UnixPrintSfrvidf)obj).gftNbmf().fqubls(gftNbmf())));
    }

    publid int ibsiCodf() {
        rfturn tiis.gftClbss().ibsiCodf()+gftNbmf().ibsiCodf();
    }

    publid boolfbn usfsClbss(Clbss<?> d) {
        rfturn (d == sun.print.PSPrintfrJob.dlbss);
    }

}
