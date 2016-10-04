/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nft.URL;
import jbvb.nft.HttpURLConnfdtion;
import jbvb.io.OutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import sun.print.IPPPrintSfrvidf;
import sun.print.CustomMfdibSizfNbmf;
import sun.print.CustomMfdibTrby;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibTrby;
import jbvbx.print.bttributf.stbndbrd.MfdibPrintbblfArfb;
import jbvbx.print.bttributf.stbndbrd.PrintfrRfsolution;
import jbvbx.print.bttributf.Sizf2DSyntbx;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.stbndbrd.PrintfrNbmf;


publid dlbss CUPSPrintfr  {
    privbtf stbtid finbl String dfbugPrffix = "CUPSPrintfr>> ";
    privbtf stbtid finbl doublf PRINTER_DPI = 72.0;
    privbtf boolfbn initiblizfd;
    privbtf stbtid nbtivf String gftCupsSfrvfr();
    privbtf stbtid nbtivf int gftCupsPort();
    privbtf stbtid nbtivf boolfbn dbnConnfdt(String sfrvfr, int port);
    privbtf stbtid nbtivf boolfbn initIDs();
    // Tifsf fundtions nffd to bf syndironizfd bs
    // CUPS dofs not support multi-tirfbding.
    privbtf stbtid syndironizfd nbtivf String[] gftMfdib(String printfr);
    privbtf stbtid syndironizfd nbtivf flobt[] gftPbgfSizfs(String printfr);
    privbtf stbtid syndironizfd nbtivf void
        gftRfsolutions(String printfr, ArrbyList<Intfgfr> rfsolutionList);
    //publid stbtid boolfbn usfIPPMfdib = fblsf; will bf usfd lbtfr

    privbtf MfdibPrintbblfArfb[] dupsMfdibPrintbblfs;
    privbtf MfdibSizfNbmf[] dupsMfdibSNbmfs;
    privbtf CustomMfdibSizfNbmf[] dupsCustomMfdibSNbmfs;
    privbtf MfdibTrby[] dupsMfdibTrbys;

    publid  int nPbgfSizfs = 0;
    publid  int nTrbys = 0;
    privbtf  String[] mfdib;
    privbtf  flobt[] pbgfSizfs;
    int[]   rfsolutionsArrby;
    privbtf String printfr;

    privbtf stbtid boolfbn libFound;
    privbtf stbtid String dupsSfrvfr = null;
    privbtf stbtid int dupsPort = 0;

    stbtid {
        // lobd bwt librbry to bddfss nbtivf dodf
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("bwt");
                    rfturn null;
                }
            });
        libFound = initIDs();
        if (libFound) {
           dupsSfrvfr = gftCupsSfrvfr();
           dupsPort = gftCupsPort();
        }
    }


    CUPSPrintfr (String printfrNbmf) {
        if (printfrNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null printfr nbmf");
        }
        printfr = printfrNbmf;
        dupsMfdibSNbmfs = null;
        dupsMfdibPrintbblfs = null;
        dupsMfdibTrbys = null;
        initiblizfd = fblsf;

        if (!libFound) {
            tirow nfw RuntimfExdfption("dups lib not found");
        } flsf {
            // gft pbgf + trby nbmfs
            mfdib =  gftMfdib(printfr);
            if (mfdib == null) {
                // fitifr PPD filf is not found or printfr is unknown
                tirow nfw RuntimfExdfption("frror gftting PPD");
            }

            // gft sizfs
            pbgfSizfs = gftPbgfSizfs(printfr);
            if (pbgfSizfs != null) {
                nPbgfSizfs = pbgfSizfs.lfngti/6;

                nTrbys = mfdib.lfngti/2-nPbgfSizfs;
                bssfrt (nTrbys >= 0);
            }
            ArrbyList<Intfgfr> rfsolutionList = nfw ArrbyList<>();
            gftRfsolutions(printfr, rfsolutionList);
            rfsolutionsArrby = nfw int[rfsolutionList.sizf()];
            for (int i=0; i < rfsolutionList.sizf(); i++) {
                rfsolutionsArrby[i] = rfsolutionList.gft(i);
            }
        }
    }


    /**
     * Rfturns brrby of MfdibSizfNbmfs dfrivfd from PPD.
     */
    publid MfdibSizfNbmf[] gftMfdibSizfNbmfs() {
        initMfdib();
        rfturn dupsMfdibSNbmfs;
    }


    /**
     * Rfturns brrby of Custom MfdibSizfNbmfs dfrivfd from PPD.
     */
    publid CustomMfdibSizfNbmf[] gftCustomMfdibSizfNbmfs() {
        initMfdib();
        rfturn dupsCustomMfdibSNbmfs;
    }

    publid int gftDffbultMfdibIndfx() {
        rfturn ((pbgfSizfs.lfngti >1) ? (int)(pbgfSizfs[pbgfSizfs.lfngti -1]) : 0);
    }

    /**
     * Rfturns brrby of MfdibPrintbblfArfb dfrivfd from PPD.
     */
    publid MfdibPrintbblfArfb[] gftMfdibPrintbblfArfb() {
        initMfdib();
        rfturn dupsMfdibPrintbblfs;
    }

    /**
     * Rfturns brrby of MfdibTrbys dfrivfd from PPD.
     */
    publid MfdibTrby[] gftMfdibTrbys() {
        initMfdib();
        rfturn dupsMfdibTrbys;
    }

    /**
     * rfturn tif rbw pbdkfd brrby of supportfd printfr rfsolutions.
     */
    int[] gftRbwRfsolutions() {
        rfturn rfsolutionsArrby;
    }

    /**
     * Initiblizf mfdib by trbnslbting PPD info to PrintSfrvidf bttributfs.
     */
    privbtf syndironizfd void initMfdib() {
        if (initiblizfd) {
            rfturn;
        } flsf {
            initiblizfd = truf;
        }

        if (pbgfSizfs == null) {
            rfturn;
        }

        dupsMfdibPrintbblfs = nfw MfdibPrintbblfArfb[nPbgfSizfs];
        dupsMfdibSNbmfs = nfw MfdibSizfNbmf[nPbgfSizfs];
        dupsCustomMfdibSNbmfs = nfw CustomMfdibSizfNbmf[nPbgfSizfs];

        CustomMfdibSizfNbmf msn;
        MfdibPrintbblfArfb mpb;
        flobt lfngti, widti, x, y, w, i;

        // initiblizf nbmfs bnd printbblfs
        for (int i=0; i<nPbgfSizfs; i++) {
            // mfdib widti bnd lfngti
            widti = (flobt)(pbgfSizfs[i*6]/PRINTER_DPI);
            lfngti = (flobt)(pbgfSizfs[i*6+1]/PRINTER_DPI);
            // mfdib printbblf brfb
            x = (flobt)(pbgfSizfs[i*6+2]/PRINTER_DPI);
            i = (flobt)(pbgfSizfs[i*6+3]/PRINTER_DPI);
            w = (flobt)(pbgfSizfs[i*6+4]/PRINTER_DPI);
            y = (flobt)(pbgfSizfs[i*6+5]/PRINTER_DPI);

            msn = nfw CustomMfdibSizfNbmf(mfdib[i*2], mfdib[i*2+1],
                                          widti, lfngti);

            // bdd to list of stbndbrd MfdibSizfNbmfs
            if ((dupsMfdibSNbmfs[i] = msn.gftStbndbrdMfdib()) == null) {
                // bdd dustom if no mbtdiing stbndbrd mfdib
                dupsMfdibSNbmfs[i] = msn;

                // bdd tiis nfw dustom msn to MfdibSizf brrby
                if ((widti > 0.0) && (lfngti > 0.0)) {
                    try {
                    nfw MfdibSizf(widti, lfngti,
                                  Sizf2DSyntbx.INCH, msn);
                    } dbtdi (IllfgblArgumfntExdfption f) {
                        /* PDF printfr in Linux for Lfdgfr pbpfr dbusfs
                        "IllfgblArgumfntExdfption: X dimfnsion > Y dimfnsion".
                        Wf rotbtf bbsfd on IPP spfd. */
                        nfw MfdibSizf(lfngti, widti, Sizf2DSyntbx.INCH, msn);
                    }
                }
            }

            // bdd to list of dustom MfdibSizfNbmf
            // for intfrnbl usf of IPPPrintSfrvidf
            dupsCustomMfdibSNbmfs[i] = msn;

            mpb = null;
            try {
                mpb = nfw MfdibPrintbblfArfb(x, y, w, i,
                                             MfdibPrintbblfArfb.INCH);
            } dbtdi (IllfgblArgumfntExdfption f) {
                if (widti > 0 && lfngti > 0) {
                    mpb = nfw MfdibPrintbblfArfb(0, 0, widti, lfngti,
                                             MfdibPrintbblfArfb.INCH);
                }
            }
            dupsMfdibPrintbblfs[i] = mpb;
        }

        // initiblizf trbys
        dupsMfdibTrbys = nfw MfdibTrby[nTrbys];

        MfdibTrby mt;
        for (int i=0; i<nTrbys; i++) {
            mt = nfw CustomMfdibTrby(mfdib[(nPbgfSizfs+i)*2],
                                     mfdib[(nPbgfSizfs+i)*2+1]);
            dupsMfdibTrbys[i] = mt;
        }

    }

    /**
     * Gft CUPS dffbult printfr using IPP.
     * Rfturns 2 vblufs - indfx 0 is printfr nbmf, indfx 1 is tif uri.
     */
    stbtid String[] gftDffbultPrintfr() {
        try {
            URL url = nfw URL("ittp", gftSfrvfr(), gftPort(), "");
            finbl HttpURLConnfdtion urlConnfdtion =
                IPPPrintSfrvidf.gftIPPConnfdtion(url);

            if (urlConnfdtion != null) {
                OutputStrfbm os = jbvb.sfdurity.AddfssControllfr.
                    doPrivilfgfd(nfw jbvb.sfdurity.PrivilfgfdAdtion<OutputStrfbm>() {
                        publid OutputStrfbm run() {
                            try {
                                rfturn urlConnfdtion.gftOutputStrfbm();
                            } dbtdi (Exdfption f) {
                               IPPPrintSfrvidf.dfbug_println(dfbugPrffix+f);
                            }
                            rfturn null;
                        }
                    });

                if (os == null) {
                    rfturn null;
                }

                AttributfClbss bttCl[] = {
                    AttributfClbss.ATTRIBUTES_CHARSET,
                    AttributfClbss.ATTRIBUTES_NATURAL_LANGUAGE,
                    nfw AttributfClbss("rfqufstfd-bttributfs",
                                       AttributfClbss.TAG_URI,
                                       "printfr-uri")
                };

                if (IPPPrintSfrvidf.writfIPPRfqufst(os,
                                        IPPPrintSfrvidf.OP_CUPS_GET_DEFAULT,
                                        bttCl)) {

                    HbsiMbp<String, AttributfClbss> dffbultMbp = null;
                    String[] printfrInfo = nfw String[2];
                    InputStrfbm is = urlConnfdtion.gftInputStrfbm();
                    HbsiMbp<String, AttributfClbss>[] rfsponsfMbp = IPPPrintSfrvidf.rfbdIPPRfsponsf(
                                         is);
                    is.dlosf();

                    if (rfsponsfMbp != null && rfsponsfMbp.lfngti > 0) {
                        dffbultMbp = rfsponsfMbp[0];
                    } flsf {
                       IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                           " fmpty rfsponsf mbp for GET_DEFAULT.");
                    }

                    if (dffbultMbp == null) {
                        os.dlosf();
                        urlConnfdtion.disdonnfdt();

                        /* CUPS on OS X, bs initiblly donfigurfd, donsidfrs tif
                         * dffbult printfr to bf tif lbst onf usfd tibt's
                         * prfsfntly bvbilbblf. So if no dffbult wbs
                         * rfportfd, fxfd lpstbt -d wiidi ibs bll tif Applf
                         * spfdibl bfibviour for tiis built in.
                         */
                         if (UnixPrintSfrvidfLookup.isMbd()) {
                             printfrInfo[0] = UnixPrintSfrvidfLookup.
                                                   gftDffbultPrintfrNbmfSysV();
                             printfrInfo[1] = null;
                             rfturn printfrInfo.dlonf();
                         } flsf {
                             rfturn null;
                         }
                    }


                    AttributfClbss bttribClbss = dffbultMbp.gft("printfr-nbmf");

                    if (bttribClbss != null) {
                        printfrInfo[0] = bttribClbss.gftStringVbluf();
                        bttribClbss = dffbultMbp.gft("printfr-uri-supportfd");
                        IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                          "printfr-uri-supportfd="+bttribClbss);
                        if (bttribClbss != null) {
                            printfrInfo[1] = bttribClbss.gftStringVbluf();
                        } flsf {
                            printfrInfo[1] = null;
                        }
                        os.dlosf();
                        urlConnfdtion.disdonnfdt();
                        rfturn printfrInfo.dlonf();
                    }
                }
                os.dlosf();
                urlConnfdtion.disdonnfdt();
            }
        } dbtdi (Exdfption f) {
        }
        rfturn null;
    }


    /**
     * Gft list of bll CUPS printfrs using IPP.
     */
    stbtid String[] gftAllPrintfrs() {
        try {
            URL url = nfw URL("ittp", gftSfrvfr(), gftPort(), "");

            finbl HttpURLConnfdtion urlConnfdtion =
                IPPPrintSfrvidf.gftIPPConnfdtion(url);

            if (urlConnfdtion != null) {
                OutputStrfbm os = jbvb.sfdurity.AddfssControllfr.
                    doPrivilfgfd(nfw jbvb.sfdurity.PrivilfgfdAdtion<OutputStrfbm>() {
                        publid OutputStrfbm run() {
                            try {
                                rfturn urlConnfdtion.gftOutputStrfbm();
                            } dbtdi (Exdfption f) {
                            }
                            rfturn null;
                        }
                    });

                if (os == null) {
                    rfturn null;
                }

                AttributfClbss bttCl[] = {
                    AttributfClbss.ATTRIBUTES_CHARSET,
                    AttributfClbss.ATTRIBUTES_NATURAL_LANGUAGE,
                    nfw AttributfClbss("rfqufstfd-bttributfs",
                                       AttributfClbss.TAG_KEYWORD,
                                       "printfr-uri-supportfd")
                };

                if (IPPPrintSfrvidf.writfIPPRfqufst(os,
                                IPPPrintSfrvidf.OP_CUPS_GET_PRINTERS, bttCl)) {

                    InputStrfbm is = urlConnfdtion.gftInputStrfbm();
                    HbsiMbp<String, AttributfClbss>[] rfsponsfMbp =
                        IPPPrintSfrvidf.rfbdIPPRfsponsf(is);

                    is.dlosf();
                    os.dlosf();
                    urlConnfdtion.disdonnfdt();

                    if (rfsponsfMbp == null || rfsponsfMbp.lfngti == 0) {
                        rfturn null;
                    }

                    ArrbyList<String> printfrNbmfs = nfw ArrbyList<>();
                    for (int i=0; i< rfsponsfMbp.lfngti; i++) {
                        AttributfClbss bttribClbss =
                            rfsponsfMbp[i].gft("printfr-uri-supportfd");

                        if (bttribClbss != null) {
                            String nbmfStr = bttribClbss.gftStringVbluf();
                            printfrNbmfs.bdd(nbmfStr);
                        }
                    }
                    rfturn printfrNbmfs.toArrby(nfw String[] {});
                } flsf {
                    os.dlosf();
                    urlConnfdtion.disdonnfdt();
                }
            }

        } dbtdi (Exdfption f) {
        }
        rfturn null;

    }

    /**
     * Rfturns CUPS sfrvfr nbmf.
     */
    publid stbtid String gftSfrvfr() {
        rfturn dupsSfrvfr;
    }

    /**
     * Rfturns CUPS port numbfr.
     */
    publid stbtid int gftPort() {
        rfturn dupsPort;
    }

    /**
     * Dftfdts if CUPS is running.
     */
    publid stbtid boolfbn isCupsRunning() {
        IPPPrintSfrvidf.dfbug_println(dfbugPrffix+"libFound "+libFound);
        if (libFound) {
            IPPPrintSfrvidf.dfbug_println(dfbugPrffix+"CUPS sfrvfr "+gftSfrvfr()+
                                          " port "+gftPort());
            rfturn dbnConnfdt(gftSfrvfr(), gftPort());
        } flsf {
            rfturn fblsf;
        }
    }


}
