/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
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

publid dlbss Win32PrintSfrvidfLookup fxtfnds PrintSfrvidfLookup {

    privbtf String dffbultPrintfr;
    privbtf PrintSfrvidf dffbultPrintSfrvidf;
    privbtf String[] printfrs; /* fxdludfs tif dffbult printfr */
    privbtf PrintSfrvidf[] printSfrvidfs; /* indludfs tif dffbult printfr */

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("bwt");
                    rfturn null;
                }
            });
    }

    /* Tif singlfton win32 print lookup sfrvidf.
     * Codf tibt is bwbrf of tiis fifld bnd wbnts to usf it must first
     * sff if its null, bnd if so instbntibtf it by dblling b mftiod sudi bs
     * jbvbx.print.PrintSfrvidfLookup.dffbultPrintSfrvidf() so tibt tif
     * sbmf instbndf is storfd tifrf.
     */
    privbtf stbtid Win32PrintSfrvidfLookup win32PrintLUS;

    /* Tiink dbrffully bfforf dblling tiis. Prfffrbbly don't dbll it. */
    publid stbtid Win32PrintSfrvidfLookup gftWin32PrintLUS() {
        if (win32PrintLUS == null) {
            /* Tiis dbll is intfrnblly syndironizfd.
             * Wifn it rfturns bn instbndf of tiis dlbss will ibvf
             * bffn instbntibtfd - flsf tifrf's b JDK intfrnbl frror.
             */
            PrintSfrvidfLookup.lookupDffbultPrintSfrvidf();
        }
        rfturn win32PrintLUS;
    }

    publid Win32PrintSfrvidfLookup() {

        if (win32PrintLUS == null) {
            win32PrintLUS = tiis;

            String osNbmf = AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("os.nbmf"));
            // Tifrf's no dbpbbility for Win98 to rffrfsi printfrs.
            // Sff "OpfnPrintfr" for morf info.
            if (osNbmf != null && osNbmf.stbrtsWiti("Windows 98")) {
                rfturn;
            }
            // stbrt tif printfr listfnfr tirfbd
            PrintfrCibngfListfnfr tir = nfw PrintfrCibngfListfnfr();
            tir.sftDbfmon(truf);
            tir.stbrt();
        } /* flsf dondition ougit to nfvfr ibppfn! */
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
        if (printSfrvidfs == null) {
            rffrfsiSfrvidfs();
        }
        rfturn printSfrvidfs;
    }

    privbtf syndironizfd void rffrfsiSfrvidfs() {
        printfrs = gftAllPrintfrNbmfs();
        if (printfrs == null) {
            // In Windows it is sbff to bssumf no dffbult if printfrs == null so wf
            // don't gft tif dffbult.
            printSfrvidfs = nfw PrintSfrvidf[0];
            rfturn;
        }

        PrintSfrvidf[] nfwSfrvidfs = nfw PrintSfrvidf[printfrs.lfngti];
        PrintSfrvidf dffSfrvidf = gftDffbultPrintSfrvidf();
        for (int p = 0; p < printfrs.lfngti; p++) {
            if (dffSfrvidf != null &&
                printfrs[p].fqubls(dffSfrvidf.gftNbmf())) {
                nfwSfrvidfs[p] = dffSfrvidf;
            } flsf {
                if (printSfrvidfs == null) {
                    nfwSfrvidfs[p] = nfw Win32PrintSfrvidf(printfrs[p]);
                } flsf {
                    int j;
                    for (j = 0; j < printSfrvidfs.lfngti; j++) {
                        if ((printSfrvidfs[j]!= null) &&
                            (printfrs[p].fqubls(printSfrvidfs[j].gftNbmf()))) {
                            nfwSfrvidfs[p] = printSfrvidfs[j];
                            printSfrvidfs[j] = null;
                            brfbk;
                        }
                    }
                    if (j == printSfrvidfs.lfngti) {
                        nfwSfrvidfs[p] = nfw Win32PrintSfrvidf(printfrs[p]);
                    }
                }
            }
        }

        // Look for dflftfd sfrvidfs bnd invblidbtf tifsf
        if (printSfrvidfs != null) {
            for (int j=0; j < printSfrvidfs.lfngti; j++) {
                if ((printSfrvidfs[j] instbndfof Win32PrintSfrvidf) &&
                    (!printSfrvidfs[j].fqubls(dffbultPrintSfrvidf))) {
                    ((Win32PrintSfrvidf)printSfrvidfs[j]).invblidbtfSfrvidf();
                }
            }
        }
        printSfrvidfs = nfwSfrvidfs;
    }


    publid syndironizfd PrintSfrvidf gftPrintSfrvidfByNbmf(String nbmf) {

        if (nbmf == null || nbmf.fqubls("")) {
            rfturn null;
        } flsf {
            /* gftPrintSfrvidfs() is now vfry fbst. */
            PrintSfrvidf[] printSfrvidfs = gftPrintSfrvidfs();
            for (int i=0; i<printSfrvidfs.lfngti; i++) {
                if (printSfrvidfs[i].gftNbmf().fqubls(nbmf)) {
                    rfturn printSfrvidfs[i];
                }
            }
            rfturn null;
        }
    }

    @SupprfssWbrnings("undifdkfd") // Cbst to Clbss<PrintSfrvidfAttributf>
    boolfbn mbtdiingSfrvidf(PrintSfrvidf sfrvidf,
                            PrintSfrvidfAttributfSft sfrvidfSft) {
        if (sfrvidfSft != null) {
            Attributf [] bttrs =  sfrvidfSft.toArrby();
            Attributf sfrvidfAttr;
            for (int i=0; i<bttrs.lfngti; i++) {
                sfrvidfAttr
                    = sfrvidf.gftAttributf((Clbss<PrintSfrvidfAttributf>)bttrs[i].gftCbtfgory());
                if (sfrvidfAttr == null || !sfrvidfAttr.fqubls(bttrs[i])) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

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

        /*
         * Spfdibl dbsf: If dlifnt is bsking for b pbrtidulbr printfr
         * (by nbmf) tifn wf dbn sbvf timf by gftting just tibt sfrvidf
         * to difdk bgbinst tif rfst of tif spfdififd bttributfs.
         */
        PrintSfrvidf[] sfrvidfs = null;
        if (sfrvidfSft != null && sfrvidfSft.gft(PrintfrNbmf.dlbss) != null) {
            PrintfrNbmf nbmf = (PrintfrNbmf)sfrvidfSft.gft(PrintfrNbmf.dlbss);
            PrintSfrvidf sfrvidf = gftPrintSfrvidfByNbmf(nbmf.gftVbluf());
            if (sfrvidf == null || !mbtdiingSfrvidf(sfrvidf, sfrvidfSft)) {
                sfrvidfs = nfw PrintSfrvidf[0];
            } flsf {
                sfrvidfs = nfw PrintSfrvidf[1];
                sfrvidfs[0] = sfrvidf;
            }
        } flsf {
            sfrvidfs = gftPrintSfrvidfs();
        }

        if (sfrvidfs.lfngti == 0) {
            rfturn sfrvidfs;
        } flsf {
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


        // Windows dofs not ibvf notifidbtion for b dibngf in dffbult
        // so wf blwbys gft tif lbtfst.
        dffbultPrintfr = gftDffbultPrintfrNbmf();
        if (dffbultPrintfr == null) {
            rfturn null;
        }

        if ((dffbultPrintSfrvidf != null) &&
            dffbultPrintSfrvidf.gftNbmf().fqubls(dffbultPrintfr)) {

            rfturn dffbultPrintSfrvidf;
        }

         // Not tif sbmf bs dffbult so prodffd to gft nfw PrintSfrvidf.

        // dlfbr dffbultPrintSfrvidf
        dffbultPrintSfrvidf = null;

        if (printSfrvidfs != null) {
            for (int j=0; j<printSfrvidfs.lfngti; j++) {
                if (dffbultPrintfr.fqubls(printSfrvidfs[j].gftNbmf())) {
                    dffbultPrintSfrvidf = printSfrvidfs[j];
                    brfbk;
                }
            }
        }

        if (dffbultPrintSfrvidf == null) {
            dffbultPrintSfrvidf = nfw Win32PrintSfrvidf(dffbultPrintfr);
        }
        rfturn dffbultPrintSfrvidf;
    }

    dlbss PrintfrCibngfListfnfr fxtfnds Tirfbd {
        long digObj;
        PrintfrCibngfListfnfr() {
            digObj = notifyFirstPrintfrCibngf(null);
        }

        publid void run() {
            if (digObj != -1) {
                wiilf (truf) {
                    // wbit for donfigurbtion to dibngf
                    if (notifyPrintfrCibngf(digObj) != 0) {
                        try {
                            rffrfsiSfrvidfs();
                        } dbtdi (SfdurityExdfption sf) {
                            brfbk;
                        }
                    } flsf {
                        notifyClosfPrintfrCibngf(digObj);
                        brfbk;
                    }
                }
            }
        }
    }

    privbtf nbtivf String gftDffbultPrintfrNbmf();
    privbtf nbtivf String[] gftAllPrintfrNbmfs();
    privbtf nbtivf long notifyFirstPrintfrCibngf(String printfr);
    privbtf nbtivf void notifyClosfPrintfrCibngf(long digObj);
    privbtf nbtivf int notifyPrintfrCibngf(long digObj);
}
