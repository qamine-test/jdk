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

import jbvbx.print.bttributf.*;
import jbvbx.print.bttributf.stbndbrd.*;
import jbvbx.print.DodFlbvor;
import jbvbx.print.DodPrintJob;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.SfrvidfUIFbdtory;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;
import jbvb.util.Dbtf;
import jbvb.util.Arrbys;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvbx.print.fvfnt.PrintSfrvidfAttributfListfnfr;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.nft.HttpURLConnfdtion;
import jbvb.io.Filf;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.OutputStrfbmWritfr;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.nio.dibrsft.Cibrsft;

import jbvb.util.Itfrbtor;
import jbvb.util.HbsiSft;


publid dlbss IPPPrintSfrvidf implfmfnts PrintSfrvidf, SunPrintfrJobSfrvidf {

    publid stbtid finbl boolfbn dfbugPrint;
    privbtf stbtid finbl String dfbugPrffix = "IPPPrintSfrvidf>> ";
    protfdtfd stbtid void dfbug_println(String str) {
        if (dfbugPrint) {
            Systfm.out.println(str);
        }
    }

    privbtf stbtid finbl String FORCE_PIPE_PROP = "sun.print.ippdfbug";

    stbtid {
        String dfbugStr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                  nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(FORCE_PIPE_PROP));

        dfbugPrint = "truf".fqublsIgnorfCbsf(dfbugStr);
    }

    privbtf String printfr;
    privbtf URI    myURI;
    privbtf URL    myURL;
    trbnsifnt privbtf SfrvidfNotififr notififr = null;

    privbtf stbtid int MAXCOPIES = 1000;
    privbtf stbtid siort MAX_ATTRIBUTE_LENGTH = 255;

    privbtf CUPSPrintfr dps;
    privbtf HttpURLConnfdtion urlConnfdtion = null;
    privbtf DodFlbvor[] supportfdDodFlbvors;
    privbtf Clbss<?>[] supportfdCbts;
    privbtf MfdibTrby[] mfdibTrbys;
    privbtf MfdibSizfNbmf[] mfdibSizfNbmfs;
    privbtf CustomMfdibSizfNbmf[] dustomMfdibSizfNbmfs;
    privbtf int dffbultMfdibIndfx;
    privbtf int[] rbwRfsolutions = null;
    privbtf PrintfrRfsolution[] printfrRfsolutions = null;
    privbtf boolfbn isCupsPrintfr;
    privbtf boolfbn init;
    privbtf Boolfbn isPS;
    privbtf HbsiMbp<String, AttributfClbss> gftAttMbp;
    privbtf boolfbn pngImbgfsAddfd = fblsf;
    privbtf boolfbn gifImbgfsAddfd = fblsf;
    privbtf boolfbn jpgImbgfsAddfd = fblsf;


    /**
     * IPP Stbtus Codfs
     */
    privbtf stbtid finbl bytf STATUSCODE_SUCCESS = 0x00;

    /**
     * IPP Group Tbgs.  Ebdi tbg is usfd ondf bfforf tif first bttributf
     * of tibt group.
     */
    // opfrbtion bttributfs group
    privbtf stbtid finbl bytf GRPTAG_OP_ATTRIBUTES = 0x01;
    // job bttributfs group
    privbtf stbtid finbl bytf GRPTAG_JOB_ATTRIBUTES = 0x02;
    // printfr bttributfs group
    privbtf stbtid finbl bytf GRPTAG_PRINTER_ATTRIBUTES = 0x04;
    // usfd bs tif lbst tbg in bn IPP mfssbgf.
    privbtf stbtid finbl bytf GRPTAG_END_ATTRIBUTES = 0x03;

    /**
     * IPP Opfrbtion dodfs
     */
    // gfts tif bttributfs for b printfr
    publid stbtid finbl String OP_GET_ATTRIBUTES = "000B";
    // gfts tif dffbult printfr
    publid stbtid finbl String OP_CUPS_GET_DEFAULT = "4001";
    // gfts tif list of printfrs
    publid stbtid finbl String OP_CUPS_GET_PRINTERS = "4002";


    /**
     * List of bll PrintRfqufstAttributfs.  Tiis is usfd
     * for looping tirougi bll tif IPP bttributf nbmf.
     */
    privbtf stbtid Objfdt[] printRfqAttribDffbult = {
        Cirombtidity.COLOR,
        nfw Copifs(1),
        Fidflity.FIDELITY_FALSE,
        Finisiings.NONE,
        //nfw JobHoldUntil(nfw Dbtf()),
        //nfw JobImprfssions(0),
        //JobImprfssions,
        //JobKOdtfts,
        //JobMfdibSiffts,
        nfw JobNbmf("", Lodblf.gftDffbult()),
        //JobPriority,
        JobSiffts.NONE,
        (Mfdib)MfdibSizfNbmf.NA_LETTER,
        //MfdibPrintbblfArfb.dlbss, // not bn IPP bttributf
        //MultiplfDodumfntHbndling.SINGLE_DOCUMENT,
        nfw NumbfrUp(1),
        OrifntbtionRfqufstfd.PORTRAIT,
        nfw PbgfRbngfs(1),
        //PrfsfntbtionDirfdtion,
                 // CUPS dofs not supply printfr-rfsolution bttributf
        //nfw PrintfrRfsolution(300, 300, PrintfrRfsolution.DPI),
        //PrintQublity.NORMAL,
        nfw RfqufstingUsfrNbmf("", Lodblf.gftDffbult()),
        //SifftCollbtf.UNCOLLATED, //CUPS ibs no sifft dollbtf?
        Sidfs.ONE_SIDED,
    };


    /**
     * List of bll PrintSfrvidfAttributfs.  Tiis is usfd
     * for looping tirougi bll tif IPP bttributf nbmf.
     */
    privbtf stbtid Objfdt[][] sfrvidfAttributfs = {
        {ColorSupportfd.dlbss, "dolor-supportfd"},
        {PbgfsPfrMinutf.dlbss,  "pbgfs-pfr-minutf"},
        {PbgfsPfrMinutfColor.dlbss, "pbgfs-pfr-minutf-dolor"},
        {PDLOvfrridfSupportfd.dlbss, "pdl-ovfrridf-supportfd"},
        {PrintfrInfo.dlbss, "printfr-info"},
        {PrintfrIsAddfptingJobs.dlbss, "printfr-is-bddfpting-jobs"},
        {PrintfrLodbtion.dlbss, "printfr-lodbtion"},
        {PrintfrMbkfAndModfl.dlbss, "printfr-mbkf-bnd-modfl"},
        {PrintfrMfssbgfFromOpfrbtor.dlbss, "printfr-mfssbgf-from-opfrbtor"},
        {PrintfrMorfInfo.dlbss, "printfr-morf-info"},
        {PrintfrMorfInfoMbnufbdturfr.dlbss, "printfr-morf-info-mbnufbdturfr"},
        {PrintfrNbmf.dlbss, "printfr-nbmf"},
        {PrintfrStbtf.dlbss, "printfr-stbtf"},
        {PrintfrStbtfRfbsons.dlbss, "printfr-stbtf-rfbsons"},
        {PrintfrURI.dlbss, "printfr-uri"},
        {QufufdJobCount.dlbss, "qufufd-job-dount"}
    };


    /**
     * List of DodFlbvors, groupfd bbsfd on mbtdiing mimf-typf.
     * NOTE: For bny dibngf in tif prfdffinfd DodFlbvors, it must bf rfflfdtfd
     * ifrf blso.
     */
    // PDF DodFlbvors
    privbtf stbtid DodFlbvor[] bppPDF = {
        DodFlbvor.BYTE_ARRAY.PDF,
        DodFlbvor.INPUT_STREAM.PDF,
        DodFlbvor.URL.PDF
    };

    // Postsdript DodFlbvors
    privbtf stbtid DodFlbvor[] bppPostSdript = {
        DodFlbvor.BYTE_ARRAY.POSTSCRIPT,
        DodFlbvor.INPUT_STREAM.POSTSCRIPT,
        DodFlbvor.URL.POSTSCRIPT
    };

    // Autosfnsf DodFlbvors
    privbtf stbtid DodFlbvor[] bppOdtftStrfbm = {
        DodFlbvor.BYTE_ARRAY.AUTOSENSE,
        DodFlbvor.INPUT_STREAM.AUTOSENSE,
        DodFlbvor.URL.AUTOSENSE
    };

    // Tfxt DodFlbvors
    privbtf stbtid DodFlbvor[] tfxtPlbin = {
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
        DodFlbvor.CHAR_ARRAY.TEXT_PLAIN,
        DodFlbvor.STRING.TEXT_PLAIN,
        DodFlbvor.READER.TEXT_PLAIN
    };

    privbtf stbtid DodFlbvor[] tfxtPlbinHost = {
        DodFlbvor.BYTE_ARRAY.TEXT_PLAIN_HOST,
        DodFlbvor.INPUT_STREAM.TEXT_PLAIN_HOST,
        DodFlbvor.URL.TEXT_PLAIN_HOST
    };

    // JPG DodFlbvors
    privbtf stbtid DodFlbvor[] imbgfJPG = {
        DodFlbvor.BYTE_ARRAY.JPEG,
        DodFlbvor.INPUT_STREAM.JPEG,
        DodFlbvor.URL.JPEG
    };

    // GIF DodFlbvors
    privbtf stbtid DodFlbvor[] imbgfGIF = {
        DodFlbvor.BYTE_ARRAY.GIF,
        DodFlbvor.INPUT_STREAM.GIF,
        DodFlbvor.URL.GIF
    };

    // PNG DodFlbvors
    privbtf stbtid DodFlbvor[] imbgfPNG = {
        DodFlbvor.BYTE_ARRAY.PNG,
        DodFlbvor.INPUT_STREAM.PNG,
        DodFlbvor.URL.PNG
    };

    // HTML DodFlbvors
    privbtf  stbtid DodFlbvor[] tfxtHtml = {
        DodFlbvor.BYTE_ARRAY.TEXT_HTML_UTF_8,
        DodFlbvor.BYTE_ARRAY.TEXT_HTML_UTF_16,
        DodFlbvor.BYTE_ARRAY.TEXT_HTML_UTF_16BE,
        DodFlbvor.BYTE_ARRAY.TEXT_HTML_UTF_16LE,
        DodFlbvor.BYTE_ARRAY.TEXT_HTML_US_ASCII,
        DodFlbvor.INPUT_STREAM.TEXT_HTML_UTF_8,
        DodFlbvor.INPUT_STREAM.TEXT_HTML_UTF_16,
        DodFlbvor.INPUT_STREAM.TEXT_HTML_UTF_16BE,
        DodFlbvor.INPUT_STREAM.TEXT_HTML_UTF_16LE,
        DodFlbvor.INPUT_STREAM.TEXT_HTML_US_ASCII,
        DodFlbvor.URL.TEXT_HTML_UTF_8,
        DodFlbvor.URL.TEXT_HTML_UTF_16,
        DodFlbvor.URL.TEXT_HTML_UTF_16BE,
        DodFlbvor.URL.TEXT_HTML_UTF_16LE,
        DodFlbvor.URL.TEXT_HTML_US_ASCII,
        // Tifsf brf not ibndlfd in UnixPrintJob so dommfnting tifsf
        // for now.
        /*
        DodFlbvor.CHAR_ARRAY.TEXT_HTML,
        DodFlbvor.STRING.TEXT_HTML,
        DodFlbvor.READER.TEXT_HTML,
        */
    };

    privbtf  stbtid DodFlbvor[] tfxtHtmlHost = {
        DodFlbvor.BYTE_ARRAY.TEXT_HTML_HOST,
        DodFlbvor.INPUT_STREAM.TEXT_HTML_HOST,
        DodFlbvor.URL.TEXT_HTML_HOST,
    };


    // PCL DodFlbvors
    privbtf stbtid DodFlbvor[] bppPCL = {
        DodFlbvor.BYTE_ARRAY.PCL,
        DodFlbvor.INPUT_STREAM.PCL,
        DodFlbvor.URL.PCL
    };

    // List of bll DodFlbvors, usfd in looping
    // tirougi bll supportfd mimf-typfs
    privbtf stbtid Objfdt[] bllDodFlbvors = {
        bppPDF, bppPostSdript, bppOdtftStrfbm,
        tfxtPlbin, imbgfJPG, imbgfGIF, imbgfPNG,
        tfxtHtml, bppPCL,
    };


    IPPPrintSfrvidf(String nbmf, URL url) {
        if ((nbmf == null) || (url == null)){
            tirow nfw IllfgblArgumfntExdfption("null uri or printfr nbmf");
        }
        printfr = nbmf;
        supportfdDodFlbvors = null;
        supportfdCbts = null;
        mfdibSizfNbmfs = null;
        dustomMfdibSizfNbmfs = null;
        mfdibTrbys = null;
        myURL = url;
        dps = null;
        isCupsPrintfr = fblsf;
        init = fblsf;
        dffbultMfdibIndfx = -1;

        String iost = myURL.gftHost();
        if (iost!=null && iost.fqubls(CUPSPrintfr.gftSfrvfr())) {
            isCupsPrintfr = truf;
            try {
                myURI =  nfw URI("ipp://"+iost+
                                 "/printfrs/"+printfr);
                dfbug_println(dfbugPrffix+"IPPPrintSfrvidf myURI : "+myURI);
            } dbtdi (jbvb.nft.URISyntbxExdfption f) {
                tirow nfw IllfgblArgumfntExdfption("invblid url");
            }
        }
    }


    IPPPrintSfrvidf(String nbmf, String uriStr, boolfbn isCups) {
        if ((nbmf == null) || (uriStr == null)){
            tirow nfw IllfgblArgumfntExdfption("null uri or printfr nbmf");
        }
        printfr = nbmf;
        supportfdDodFlbvors = null;
        supportfdCbts = null;
        mfdibSizfNbmfs = null;
        dustomMfdibSizfNbmfs = null;
        mfdibTrbys = null;
        dps = null;
        init = fblsf;
        dffbultMfdibIndfx = -1;
        try {
            myURL =
                nfw URL(uriStr.rfplbdfFirst("ipp", "ittp"));
        } dbtdi (Exdfption f) {
            IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                                          " IPPPrintSfrvidf, myURL="+
                                          myURL+" Exdfption= "+
                                          f);
            tirow nfw IllfgblArgumfntExdfption("invblid url");
        }

        isCupsPrintfr = isCups;
        try {
            myURI =  nfw URI(uriStr);
            dfbug_println(dfbugPrffix+"IPPPrintSfrvidf myURI : "+myURI);
        } dbtdi (jbvb.nft.URISyntbxExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("invblid uri");
        }
    }


    /*
     * Initiblizf mfdibSizfNbmfs, mfdibTrbys bnd otifr bttributfs.
     * Mfdib sizf/trbys brf initiblizfd to non-null vblufs, mby bf 0-lfngti
     * brrby.
     * NOTE: Must bf dbllfd from b syndironizfd blodk only.
     */
    privbtf void initAttributfs() {
        if (!init) {
            // init dustomMfdibSizfNbmfs
            dustomMfdibSizfNbmfs = nfw CustomMfdibSizfNbmf[0];

            if ((urlConnfdtion = gftIPPConnfdtion(myURL)) == null) {
                mfdibSizfNbmfs = nfw MfdibSizfNbmf[0];
                mfdibTrbys = nfw MfdibTrby[0];
                dfbug_println(dfbugPrffix+"initAttributfs, NULL urlConnfdtion ");
                init = truf;
                rfturn;
            }

            // gft bll supportfd bttributfs tirougi IPP
            opGftAttributfs();

            if (isCupsPrintfr) {
                // notf, it is possiblf to qufry mfdib in CUPS using IPP
                // rigit now wf blwbys gft it from PPD.
                // mbybf usf "&& (usfPPD)" lbtfr?
                // Anotifr rfbson wiy wf usf PPD is bfdbusf
                // IPP durrfntly dofs not support it but PPD dofs.

                try {
                    dps = nfw CUPSPrintfr(printfr);
                    mfdibSizfNbmfs = dps.gftMfdibSizfNbmfs();
                    mfdibTrbys = dps.gftMfdibTrbys();
                    dustomMfdibSizfNbmfs = dps.gftCustomMfdibSizfNbmfs();
                    dffbultMfdibIndfx = dps.gftDffbultMfdibIndfx();
                    rbwRfsolutions = dps.gftRbwRfsolutions();
                    urlConnfdtion.disdonnfdt();
                    init = truf;
                    rfturn;
                } dbtdi (Exdfption f) {
                    IPPPrintSfrvidf.dfbug_println(dfbugPrffix+
                                       "initAttributfs, frror drfbting CUPSPrintfr f="+f);
                }
            }

            // usf IPP to gft bll mfdib,
            Mfdib[] bllMfdib = gftSupportfdMfdib();
            ArrbyList<Mfdib> sizfList = nfw ArrbyList<>();
            ArrbyList<Mfdib> trbyList = nfw ArrbyList<>();
            for (int i=0; i<bllMfdib.lfngti; i++) {
                if (bllMfdib[i] instbndfof MfdibSizfNbmf) {
                    sizfList.bdd(bllMfdib[i]);
                } flsf if (bllMfdib[i] instbndfof MfdibTrby) {
                    trbyList.bdd(bllMfdib[i]);
                }
            }

            if (sizfList != null) {
                mfdibSizfNbmfs = nfw MfdibSizfNbmf[sizfList.sizf()];
                mfdibSizfNbmfs = sizfList.toArrby(mfdibSizfNbmfs);
            }
            if (trbyList != null) {
                mfdibTrbys = nfw MfdibTrby[trbyList.sizf()];
                mfdibTrbys = trbyList.toArrby(mfdibTrbys);
            }
            urlConnfdtion.disdonnfdt();

            init = truf;
        }
    }


    publid DodPrintJob drfbtfPrintJob() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPrintJobAddfss();
        }
        // REMIND: drfbtf IPPPrintJob
        rfturn nfw UnixPrintJob(tiis);
    }


    publid syndironizfd Objfdt
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

        /* Tfst if tif flbvor is dompbtiblf witi tif bttributfs */
        if (!isDfstinbtionSupportfd(flbvor, bttributfs)) {
            rfturn null;
        }

        initAttributfs();

        /* Tfst if tif flbvor is dompbtiblf witi tif dbtfgory */
        if ((dbtfgory == Copifs.dlbss) ||
            (dbtfgory == CopifsSupportfd.dlbss)) {
            if (flbvor == null ||
                !(flbvor.fqubls(DodFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                  flbvor.fqubls(DodFlbvor.URL.POSTSCRIPT) ||
                  flbvor.fqubls(DodFlbvor.BYTE_ARRAY.POSTSCRIPT))) {
                CopifsSupportfd ds = nfw CopifsSupportfd(1, MAXCOPIES);
                AttributfClbss bttribClbss = (gftAttMbp != null) ?
                    gftAttMbp.gft(ds.gftNbmf()) : null;
                if (bttribClbss != null) {
                    int[] rbngf = bttribClbss.gftIntRbngfVbluf();
                    ds = nfw CopifsSupportfd(rbngf[0], rbngf[1]);
                }
                rfturn ds;
            } flsf {
                rfturn null;
            }
        } flsf  if (dbtfgory == Cirombtidity.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                !isIPPSupportfdImbgfs(flbvor.gftMimfTypf())) {
                Cirombtidity[]brr = nfw Cirombtidity[1];
                brr[0] = Cirombtidity.COLOR;
                rfturn (brr);
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == Dfstinbtion.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                try {
                    rfturn nfw Dfstinbtion((nfw Filf("out.ps")).toURI());
                } dbtdi (SfdurityExdfption sf) {
                    try {
                        rfturn nfw Dfstinbtion(nfw URI("filf:out.ps"));
                    } dbtdi (URISyntbxExdfption f) {
                        rfturn null;
                    }
                }
            }
            rfturn null;
        } flsf if (dbtfgory == Fidflity.dlbss) {
            Fidflity []brr = nfw Fidflity[2];
            brr[0] = Fidflity.FIDELITY_FALSE;
            brr[1] = Fidflity.FIDELITY_TRUE;
            rfturn brr;
        } flsf if (dbtfgory == Finisiings.dlbss) {
            AttributfClbss bttribClbss = (gftAttMbp != null) ?
                gftAttMbp.gft("finisiings-supportfd")
                : null;
            if (bttribClbss != null) {
                int[] finArrby = bttribClbss.gftArrbyOfIntVblufs();
                if ((finArrby != null) && (finArrby.lfngti > 0)) {
                    Finisiings[] finSup = nfw Finisiings[finArrby.lfngti];
                    for (int i=0; i<finArrby.lfngti; i++) {
                        finSup[i] = Finisiings.NONE;
                        Finisiings[] fAll = (Finisiings[])
                            (nfw ExtFinisiing(100)).gftAll();
                        for (int j=0; j<fAll.lfngti; j++) {
                            if (finArrby[i] == fAll[j].gftVbluf()) {
                                finSup[i] = fAll[j];
                                brfbk;
                            }
                        }
                    }
                    rfturn finSup;
                }
            }
        } flsf if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("Jbvb Printing", null);
        } flsf if (dbtfgory == JobSiffts.dlbss) {
            JobSiffts brr[] = nfw JobSiffts[2];
            brr[0] = JobSiffts.NONE;
            brr[1] = JobSiffts.STANDARD;
            rfturn brr;

        } flsf if (dbtfgory == Mfdib.dlbss) {
            Mfdib[] bllMfdib = nfw Mfdib[mfdibSizfNbmfs.lfngti+
                                        mfdibTrbys.lfngti];

            for (int i=0; i<mfdibSizfNbmfs.lfngti; i++) {
                bllMfdib[i] = mfdibSizfNbmfs[i];
            }

            for (int i=0; i<mfdibTrbys.lfngti; i++) {
                bllMfdib[i+mfdibSizfNbmfs.lfngti] = mfdibTrbys[i];
            }

            if (bllMfdib.lfngti == 0) {
                bllMfdib = nfw Mfdib[1];
                bllMfdib[0] = (Mfdib)gftDffbultAttributfVbluf(Mfdib.dlbss);
            }

            rfturn bllMfdib;
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            MfdibPrintbblfArfb[] mpbs = null;
            if (dps != null) {
                mpbs = dps.gftMfdibPrintbblfArfb();
            }

            if (mpbs == null) {
                mpbs = nfw MfdibPrintbblfArfb[1];
                mpbs[0] = (MfdibPrintbblfArfb)
                    gftDffbultAttributfVbluf(MfdibPrintbblfArfb.dlbss);
            }

            if ((bttributfs == null) || (bttributfs.sizf() == 0)) {
                ArrbyList<MfdibPrintbblfArfb> printbblfList =
                                       nfw ArrbyList<MfdibPrintbblfArfb>();

                for (int i=0; i<mpbs.lfngti; i++) {
                    if (mpbs[i] != null) {
                        printbblfList.bdd(mpbs[i]);
                    }
                }
                if (printbblfList.sizf() > 0) {
                    mpbs  = nfw MfdibPrintbblfArfb[printbblfList.sizf()];
                    printbblfList.toArrby(mpbs);
                }
                rfturn mpbs;
            }

            int mbtdi = -1;
            Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
            if (mfdib != null && mfdib instbndfof MfdibSizfNbmf) {
                MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib;

                // dbsf wifn no supportfd mfdibsizfnbmfs brf rfportfd
                // difdk givfn mfdib bgbinst tif dffbult
                if (mfdibSizfNbmfs.lfngti == 0 &&
                    msn.fqubls(gftDffbultAttributfVbluf(Mfdib.dlbss))) {
                    //dffbult printbblf brfb is tibt of dffbult mfdibsizf
                    rfturn mpbs;
                }

                for (int i=0; i<mfdibSizfNbmfs.lfngti; i++) {
                    if (msn.fqubls(mfdibSizfNbmfs[i])) {
                        mbtdi = i;
                    }
                }
            }

            if (mbtdi == -1) {
                rfturn null;
            } flsf {
                MfdibPrintbblfArfb []brr = nfw MfdibPrintbblfArfb[1];
                brr[0] = mpbs[mbtdi];
                rfturn brr;
            }
        } flsf if (dbtfgory == NumbfrUp.dlbss) {
            AttributfClbss bttribClbss = (gftAttMbp != null) ?
                gftAttMbp.gft("numbfr-up-supportfd") : null;
            if (bttribClbss != null) {
                int[] vblufs = bttribClbss.gftArrbyOfIntVblufs();
                if (vblufs != null) {
                    NumbfrUp[] nUp = nfw NumbfrUp[vblufs.lfngti];
                    for (int i=0; i<vblufs.lfngti; i++) {
                        nUp[i] = nfw NumbfrUp(vblufs[i]);
                    }
                    rfturn nUp;
                } flsf {
                    rfturn null;
                }
            }
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if ((flbvor != null) &&
                (flbvor.fqubls(DodFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                 flbvor.fqubls(DodFlbvor.URL.POSTSCRIPT) ||
                 flbvor.fqubls(DodFlbvor.BYTE_ARRAY.POSTSCRIPT))) {
                rfturn null;
            }

            boolfbn rfvPort = fblsf;
            OrifntbtionRfqufstfd[] orifntSup = null;

            AttributfClbss bttribClbss = (gftAttMbp != null) ?
              gftAttMbp.gft("orifntbtion-rfqufstfd-supportfd")
                : null;
            if (bttribClbss != null) {
                int[] orifntArrby = bttribClbss.gftArrbyOfIntVblufs();
                if ((orifntArrby != null) && (orifntArrby.lfngti > 0)) {
                    orifntSup =
                        nfw OrifntbtionRfqufstfd[orifntArrby.lfngti];
                    for (int i=0; i<orifntArrby.lfngti; i++) {
                        switdi (orifntArrby[i]) {
                        dffbult:
                        dbsf 3 :
                            orifntSup[i] = OrifntbtionRfqufstfd.PORTRAIT;
                            brfbk;
                        dbsf 4:
                            orifntSup[i] = OrifntbtionRfqufstfd.LANDSCAPE;
                            brfbk;
                        dbsf 5:
                            orifntSup[i] =
                                OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                            brfbk;
                        dbsf 6:
                            orifntSup[i] =
                                OrifntbtionRfqufstfd.REVERSE_PORTRAIT;
                            rfvPort = truf;
                            brfbk;
                        }
                    }
                }
            }
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {

                if (rfvPort && flbvor == null) {
                    OrifntbtionRfqufstfd []orSup = nfw OrifntbtionRfqufstfd[4];
                    orSup[0] = OrifntbtionRfqufstfd.PORTRAIT;
                    orSup[1] = OrifntbtionRfqufstfd.LANDSCAPE;
                    orSup[2] = OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                    orSup[3] = OrifntbtionRfqufstfd.REVERSE_PORTRAIT;
                    rfturn orSup;
                } flsf {
                    OrifntbtionRfqufstfd []orSup = nfw OrifntbtionRfqufstfd[3];
                    orSup[0] = OrifntbtionRfqufstfd.PORTRAIT;
                    orSup[1] = OrifntbtionRfqufstfd.LANDSCAPE;
                    orSup[2] = OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                    rfturn orSup;
                }
            } flsf {
                rfturn orifntSup;
            }
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
           if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                PbgfRbngfs []brr = nfw PbgfRbngfs[1];
                brr[0] = nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
                rfturn brr;
            } flsf {
                // Rfturning null bs tiis is not yft supportfd in UnixPrintJob.
                rfturn null;
            }
        } flsf if (dbtfgory == RfqufstingUsfrNbmf.dlbss) {
            String usfrNbmf = "";
            try {
              usfrNbmf = Systfm.gftPropfrty("usfr.nbmf", "");
            } dbtdi (SfdurityExdfption sf) {
            }
            rfturn nfw RfqufstingUsfrNbmf(usfrNbmf, null);
        } flsf if (dbtfgory == Sidfs.dlbss) {
            // Tif printfr tbkfs dbrf of Sidfs so if siort-fdgf
            // is diosfn in b job, tif rotbtion is donf by tif printfr.
            // Orifntbtion is rotbtfd by fmulbtion if pbgfbblf
            // or printbblf so if tif dodumfnt is in Lbndsdbpf, tiis mby
            // rfsult in doublf rotbtion.
            AttributfClbss bttribClbss = (gftAttMbp != null) ?
                gftAttMbp.gft("sidfs-supportfd")
                : null;
            if (bttribClbss != null) {
                String[] sidfsArrby = bttribClbss.gftArrbyOfStringVblufs();
                if ((sidfsArrby != null) && (sidfsArrby.lfngti > 0)) {
                    Sidfs[] sidfsSup = nfw Sidfs[sidfsArrby.lfngti];
                    for (int i=0; i<sidfsArrby.lfngti; i++) {
                        if (sidfsArrby[i].fndsWiti("long-fdgf")) {
                            sidfsSup[i] = Sidfs.TWO_SIDED_LONG_EDGE;
                        } flsf if (sidfsArrby[i].fndsWiti("siort-fdgf")) {
                            sidfsSup[i] = Sidfs.TWO_SIDED_SHORT_EDGE;
                        } flsf {
                            sidfsSup[i] = Sidfs.ONE_SIDED;
                        }
                    }
                    rfturn sidfsSup;
                }
            }
        } flsf if (dbtfgory == PrintfrRfsolution.dlbss) {
            PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
            if (supportfdRfs == null) {
                rfturn null;
            }
            PrintfrRfsolution []brr =
                nfw PrintfrRfsolution[supportfdRfs.lfngti];
            Systfm.brrbydopy(supportfdRfs, 0, brr, 0, supportfdRfs.lfngti);
            rfturn brr;
        }

        rfturn null;
    }

    //Tiis dlbss is for gftting bll prf-dffinfd Finisiings
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    privbtf dlbss ExtFinisiing fxtfnds Finisiings {
        ExtFinisiing(int vbluf) {
            supfr(100); // 100 to bvoid bny donflidts witi prfdffinfd vblufs.
        }

        EnumSyntbx[] gftAll() {
            EnumSyntbx[] fs = supfr.gftEnumVblufTbblf();
            rfturn fs;
        }
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


    publid syndironizfd DodFlbvor[] gftSupportfdDodFlbvors() {

        if (supportfdDodFlbvors != null) {
            int lfn = supportfdDodFlbvors.lfngti;
                DodFlbvor[] dopyflbvors = nfw DodFlbvor[lfn];
                Systfm.brrbydopy(supportfdDodFlbvors, 0, dopyflbvors, 0, lfn);
                rfturn dopyflbvors;
        }
        initAttributfs();

        if ((gftAttMbp != null) &&
            gftAttMbp.dontbinsKfy("dodumfnt-formbt-supportfd")) {

            AttributfClbss bttribClbss =
                gftAttMbp.gft("dodumfnt-formbt-supportfd");
            if (bttribClbss != null) {
                String mimfTypf;
                boolfbn psSupportfd = fblsf;
                String[] dodFlbvors = bttribClbss.gftArrbyOfStringVblufs();
                DodFlbvor[] flbvors;
                HbsiSft<Objfdt> dodList = nfw HbsiSft<>();
                int j;
                String iostEnd = DodFlbvor.iostEndoding.
                    toLowfrCbsf(Lodblf.ENGLISH);
                boolfbn bddHostEndoding = !iostEnd.fqubls("utf-8") &&
                    !iostEnd.fqubls("utf-16") && !iostEnd.fqubls("utf-16bf") &&
                    !iostEnd.fqubls("utf-16lf") && !iostEnd.fqubls("us-bsdii");

                for (int i = 0; i < dodFlbvors.lfngti; i++) {
                    for (j=0; j<bllDodFlbvors.lfngti; j++) {
                        flbvors = (DodFlbvor[])bllDodFlbvors[j];

                        mimfTypf = flbvors[0].gftMimfTypf();
                        if (mimfTypf.stbrtsWiti(dodFlbvors[i])) {

                            dodList.bddAll(Arrbys.bsList(flbvors));

                            if (mimfTypf.fqubls("tfxt/plbin") &&
                                bddHostEndoding) {
                                dodList.bdd(Arrbys.bsList(tfxtPlbinHost));
                            } flsf if (mimfTypf.fqubls("tfxt/itml") &&
                                       bddHostEndoding) {
                                dodList.bdd(Arrbys.bsList(tfxtHtmlHost));
                            } flsf if (mimfTypf.fqubls("imbgf/png")) {
                                pngImbgfsAddfd = truf;
                            } flsf if (mimfTypf.fqubls("imbgf/gif")) {
                                gifImbgfsAddfd = truf;
                            } flsf if (mimfTypf.fqubls("imbgf/jpfg")) {
                                jpgImbgfsAddfd = truf;
                            } flsf if (mimfTypf.indfxOf("postsdript") != -1) {
                                psSupportfd = truf;
                            }
                            brfbk;
                        }
                    }

                    // Not bddfd? Crfbtf nfw DodFlbvors
                    if (j == bllDodFlbvors.lfngti) {
                        //  mbkf nfw DodFlbvors
                        dodList.bdd(nfw DodFlbvor.BYTE_ARRAY(dodFlbvors[i]));
                        dodList.bdd(nfw DodFlbvor.INPUT_STREAM(dodFlbvors[i]));
                        dodList.bdd(nfw DodFlbvor.URL(dodFlbvors[i]));
                    }
                }

                // difdk if wf nffd to bdd imbgf DodFlbvors
                // bnd Pbgfbblf/Printbblf flbvors
                if (psSupportfd || isCupsPrintfr) {
                    /*
                     Alwbys bdd Pbgfbblf bnd Printbblf for CUPS
                     sindf it usfs Filtfrs to donvfrt from Postsdript
                     to dfvidf printfr lbngubgf.
                    */
                    dodList.bdd(DodFlbvor.SERVICE_FORMATTED.PAGEABLE);
                    dodList.bdd(DodFlbvor.SERVICE_FORMATTED.PRINTABLE);

                    dodList.bddAll(Arrbys.bsList(imbgfJPG));
                    dodList.bddAll(Arrbys.bsList(imbgfPNG));
                    dodList.bddAll(Arrbys.bsList(imbgfGIF));
                }
                supportfdDodFlbvors = nfw DodFlbvor[dodList.sizf()];
                dodList.toArrby(supportfdDodFlbvors);
                int lfn = supportfdDodFlbvors.lfngti;
                DodFlbvor[] dopyflbvors = nfw DodFlbvor[lfn];
                Systfm.brrbydopy(supportfdDodFlbvors, 0, dopyflbvors, 0, lfn);
                rfturn dopyflbvors;
            }
        }
        rfturn null;
    }


    publid boolfbn isDodFlbvorSupportfd(DodFlbvor flbvor) {
        if (supportfdDodFlbvors == null) {
            gftSupportfdDodFlbvors();
        }
        if (supportfdDodFlbvors != null) {
            for (int f=0; f<supportfdDodFlbvors.lfngti; f++) {
                if (flbvor.fqubls(supportfdDodFlbvors[f])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }


    /**
     * Finds mbtdiing CustomMfdibSizfNbmf of givfn mfdib.
     */
    publid CustomMfdibSizfNbmf findCustomMfdib(MfdibSizfNbmf mfdib) {
        if (dustomMfdibSizfNbmfs == null) {
            rfturn null;
        }
        for (int i=0; i< dustomMfdibSizfNbmfs.lfngti; i++) {
            CustomMfdibSizfNbmf dustom = dustomMfdibSizfNbmfs[i];
            MfdibSizfNbmf msn = dustom.gftStbndbrdMfdib();
            if (mfdib.fqubls(msn)) {
                rfturn dustomMfdibSizfNbmfs[i];
            }
        }
        rfturn null;
    }


    /**
     * Rfturns tif mbtdiing stbndbrd Mfdib using string dompbrison of nbmfs.
     */
    privbtf Mfdib gftIPPMfdib(String mfdibNbmf) {
        CustomMfdibSizfNbmf sbmplfSizf = nfw CustomMfdibSizfNbmf("sbmplf", "",
                                                                 0, 0);
        Mfdib[] sizfs = sbmplfSizf.gftSupfrEnumTbblf();
        for (int i=0; i<sizfs.lfngti; i++) {
            if (mfdibNbmf.fqubls(""+sizfs[i])) {
                rfturn sizfs[i];
            }
        }
        CustomMfdibTrby sbmplfTrby = nfw CustomMfdibTrby("sbmplf", "");
        Mfdib[] trbys = sbmplfTrby.gftSupfrEnumTbblf();
        for (int i=0; i<trbys.lfngti; i++) {
            if (mfdibNbmf.fqubls(""+trbys[i])) {
                rfturn trbys[i];
            }
        }
        rfturn null;
    }

    privbtf Mfdib[] gftSupportfdMfdib() {
        if ((gftAttMbp != null) &&
            gftAttMbp.dontbinsKfy("mfdib-supportfd")) {

            AttributfClbss bttribClbss = gftAttMbp.gft("mfdib-supportfd");

            if (bttribClbss != null) {
                String[] mfdibVbls = bttribClbss.gftArrbyOfStringVblufs();
                Mfdib msn;
                Mfdib[] mfdibNbmfs =
                    nfw Mfdib[mfdibVbls.lfngti];
                for (int i=0; i<mfdibVbls.lfngti; i++) {
                    msn = gftIPPMfdib(mfdibVbls[i]);
                    //REMIND: if null, drfbtf dustom?
                    mfdibNbmfs[i] = msn;
                }
                rfturn mfdibNbmfs;
            }
        }
        rfturn nfw Mfdib[0];
    }


    publid syndironizfd Clbss<?>[] gftSupportfdAttributfCbtfgorifs() {
        if (supportfdCbts != null) {
            rfturn supportfdCbts;
        }

        initAttributfs();

        ArrbyList<Clbss<?>> dbtList = nfw ArrbyList<>();

        for (int i=0; i < printRfqAttribDffbult.lfngti; i++) {
            PrintRfqufstAttributf prb =
                (PrintRfqufstAttributf)printRfqAttribDffbult[i];
            if (gftAttMbp != null &&
                gftAttMbp.dontbinsKfy(prb.gftNbmf()+"-supportfd")) {
                dbtList.bdd(prb.gftCbtfgory());
            }
        }

        // Somf IPP printfrs likf lfxd710 do not ibvf list of supportfd mfdib
        // but CUPS dbn gft tif mfdib from PPD, so wf still rfport bs
        // supportfd dbtfgory.
        if (isCupsPrintfr) {
            if (!dbtList.dontbins(Mfdib.dlbss)) {
                dbtList.bdd(Mfdib.dlbss);
            }

            // Alwbys bdd MfdibPrintbblf for dups,
            // bfdbusf wf dbn gft it from PPD.
            dbtList.bdd(MfdibPrintbblfArfb.dlbss);

            // tiis is blrfbdy supportfd in UnixPrintJob
            dbtList.bdd(Dfstinbtion.dlbss);

            // It is unfortunbtf tibt CUPS dofsn't providf b wby to qufry
            // if printfr supports dollbtion but sindf most printfrs
            // now supports dollbtion bnd tibt most OS ibs b wby
            // of sftting it, it is b sbff bssumption to just blwbys
            // indludf SifftCollbtf bs supportfd bttributf.

            /*
               In Linux, wf usf Postsdript for rfndfring but Linux still
               ibs issufs in propbgbting Postsdript-fmbfddfd sftpbgfdfvidf
               sftting likf dollbtion.  Tifrfforf, wf tfmporbrily fxdludf
               Linux.
            */
            if (!UnixPrintSfrvidfLookup.isLinux()) {
                dbtList.bdd(SifftCollbtf.dlbss);
            }
        }

        // Witi tif bssumption tibt  Cirombtidity is fquivblfnt to
        // ColorSupportfd.
        if (gftAttMbp != null && gftAttMbp.dontbinsKfy("dolor-supportfd")) {
            dbtList.bdd(Cirombtidity.dlbss);
        }

        // CUPS dofs not rfport printfr rfsolution vib IPP but it
        // mby bf glfbnfd from tif PPD.
        PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
        if (supportfdRfs != null && (supportfdRfs.lfngti > 0)) {
            dbtList.bdd(PrintfrRfsolution.dlbss);
        }

        supportfdCbts = nfw Clbss<?>[dbtList.sizf()];
        dbtList.toArrby(supportfdCbts);
        rfturn supportfdCbts;
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

        if (supportfdCbts == null) {
            gftSupportfdAttributfCbtfgorifs();
        }

        // It is sbff to bssumf tibt Orifntbtion is blwbys supportfd
        // bnd fvfn if CUPS or bn IPP dfvidf rfports it bs not,
        // our rfndfrfr dbn do portrbit, lbndsdbpf bnd
        // rfvfrsf lbndsdbpf.
        if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            rfturn truf;
        }

        for (int i=0;i<supportfdCbts.lfngti;i++) {
            if (dbtfgory == supportfdCbts[i]) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    @SupprfssWbrnings("undifdkfd")
    publid syndironizfd <T fxtfnds PrintSfrvidfAttributf>
        T gftAttributf(Clbss<T> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("dbtfgory");
        }
        if (!(PrintSfrvidfAttributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption("Not b PrintSfrvidfAttributf");
        }

        initAttributfs();

        if (dbtfgory == PrintfrNbmf.dlbss) {
            rfturn (T)(nfw PrintfrNbmf(printfr, null));
        } flsf if (dbtfgory == PrintfrInfo.dlbss) {
            PrintfrInfo pInfo = nfw PrintfrInfo(printfr, null);
            AttributfClbss bd = (gftAttMbp != null) ?
                gftAttMbp.gft(pInfo.gftNbmf())
                : null;
            if (bd != null) {
                rfturn (T)(nfw PrintfrInfo(bd.gftStringVbluf(), null));
            }
            rfturn (T)pInfo;
        } flsf if (dbtfgory == QufufdJobCount.dlbss) {
            QufufdJobCount qjd = nfw QufufdJobCount(0);
            AttributfClbss bd = (gftAttMbp != null) ?
                gftAttMbp.gft(qjd.gftNbmf())
                : null;
            if (bd != null) {
                qjd = nfw QufufdJobCount(bd.gftIntVbluf());
            }
            rfturn (T)qjd;
        } flsf if (dbtfgory == PrintfrIsAddfptingJobs.dlbss) {
            PrintfrIsAddfptingJobs bddJob =
                PrintfrIsAddfptingJobs.ACCEPTING_JOBS;
            AttributfClbss bd = (gftAttMbp != null) ?
                gftAttMbp.gft(bddJob.gftNbmf())
                : null;
            if ((bd != null) && (bd.gftBytfVbluf() == 0)) {
                bddJob = PrintfrIsAddfptingJobs.NOT_ACCEPTING_JOBS;
            }
            rfturn (T)bddJob;
        } flsf if (dbtfgory == ColorSupportfd.dlbss) {
            ColorSupportfd ds = ColorSupportfd.SUPPORTED;
            AttributfClbss bd = (gftAttMbp != null) ?
                gftAttMbp.gft(ds.gftNbmf())
                : null;
            if ((bd != null) && (bd.gftBytfVbluf() == 0)) {
                ds = ColorSupportfd.NOT_SUPPORTED;
            }
            rfturn (T)ds;
        } flsf if (dbtfgory == PDLOvfrridfSupportfd.dlbss) {

            if (isCupsPrintfr) {
                // Dodumfntfd: For CUPS tiis will blwbys bf fblsf
                rfturn (T)PDLOvfrridfSupportfd.NOT_ATTEMPTED;
            } flsf {
                // REMIND: difdk bttributf vblufs
                rfturn (T)PDLOvfrridfSupportfd.NOT_ATTEMPTED;
            }
        } flsf if (dbtfgory == PrintfrURI.dlbss) {
            rfturn (T)(nfw PrintfrURI(myURI));
        } flsf {
            rfturn null;
        }
    }


    publid syndironizfd PrintSfrvidfAttributfSft gftAttributfs() {
        // updbtf gftAttMbp by sfnding bgbin gft-bttributfs IPP rfqufst
        init = fblsf;
        initAttributfs();

        HbsiPrintSfrvidfAttributfSft bttrs =
            nfw HbsiPrintSfrvidfAttributfSft();

        for (int i=0; i < sfrvidfAttributfs.lfngti; i++) {
            String nbmf = (String)sfrvidfAttributfs[i][1];
            if (gftAttMbp != null && gftAttMbp.dontbinsKfy(nbmf)) {
                @SupprfssWbrnings("undifdkfd")
                Clbss<PrintSfrvidfAttributf> d = (Clbss<PrintSfrvidfAttributf>)sfrvidfAttributfs[i][0];
                PrintSfrvidfAttributf psb = gftAttributf(d);
                if (psb != null) {
                    bttrs.bdd(psb);
                }
            }
        }
        rfturn AttributfSftUtilitifs.unmodifibblfVifw(bttrs);
    }

    publid boolfbn isIPPSupportfdImbgfs(String mimfTypf) {
        if (supportfdDodFlbvors == null) {
            gftSupportfdDodFlbvors();
        }

        if (mimfTypf.fqubls("imbgf/png") && pngImbgfsAddfd) {
            rfturn truf;
        } flsf if (mimfTypf.fqubls("imbgf/gif") && gifImbgfsAddfd) {
            rfturn truf;
        } flsf if (mimfTypf.fqubls("imbgf/jpfg") && jpgImbgfsAddfd) {
            rfturn truf;
        }

        rfturn fblsf;
    }


    privbtf boolfbn isSupportfdCopifs(Copifs dopifs) {
        CopifsSupportfd ds = (CopifsSupportfd)
            gftSupportfdAttributfVblufs(Copifs.dlbss, null, null);
        int[][] mfmbfrs = ds.gftMfmbfrs();
        int min, mbx;
        if ((mfmbfrs.lfngti > 0) && (mfmbfrs[0].lfngti > 0)) {
            min = mfmbfrs[0][0];
            mbx = mfmbfrs[0][1];
        } flsf {
            min = 1;
            mbx = MAXCOPIES;
        }

        int vbluf = dopifs.gftVbluf();
        rfturn (vbluf >= min && vbluf <= mbx);
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

    privbtf syndironizfd boolfbn isSupportfdMfdibTrby(MfdibTrby msn) {
        initAttributfs();

        if (mfdibTrbys != null) {
            for (int i=0; i<mfdibTrbys.lfngti; i++) {
               if (msn.fqubls(mfdibTrbys[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    privbtf syndironizfd boolfbn isSupportfdMfdib(MfdibSizfNbmf msn) {
        initAttributfs();

        if (msn.fqubls((Mfdib)gftDffbultAttributfVbluf(Mfdib.dlbss))) {
            rfturn truf;
        }
        for (int i=0; i<mfdibSizfNbmfs.lfngti; i++) {
            dfbug_println(dfbugPrffix+"isSupportfdMfdib, mfdibSizfNbmfs[i] "+mfdibSizfNbmfs[i]);
            if (msn.fqubls(mfdibSizfNbmfs[i])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /* Rfturn fblsf if flbvor is not null, pbgfbblf, nor printbblf bnd
     * Dfstinbtion is pbrt of bttributfs.
     */
    privbtf boolfbn
        isDfstinbtionSupportfd(DodFlbvor flbvor, AttributfSft bttributfs) {

            if ((bttributfs != null) &&
                    (bttributfs.gft(Dfstinbtion.dlbss) != null) &&
                    !(flbvor == null ||
                      flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                      flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
            rfturn truf;
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

        /* Tfst if tif flbvor is dompbtiblf witi tif bttributfs */
        if (!isDfstinbtionSupportfd(flbvor, bttributfs)) {
            rfturn fblsf;
        }

        /* Tfst if tif flbvor is dompbtiblf witi tif dbtfgory */
        if (bttr.gftCbtfgory() == Cirombtidity.dlbss) {
            if ((flbvor == null) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                !isIPPSupportfdImbgfs(flbvor.gftMimfTypf())) {
                rfturn bttr == Cirombtidity.COLOR;
            } flsf {
                rfturn fblsf;
            }
        } flsf if (bttr.gftCbtfgory() == Copifs.dlbss) {
            rfturn (flbvor == null ||
                   !(flbvor.fqubls(DodFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                   flbvor.fqubls(DodFlbvor.URL.POSTSCRIPT) ||
                   flbvor.fqubls(DodFlbvor.BYTE_ARRAY.POSTSCRIPT))) &&
                isSupportfdCopifs((Copifs)bttr);

        } flsf if (bttr.gftCbtfgory() == Dfstinbtion.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                URI uri = ((Dfstinbtion)bttr).gftURI();
                if ("filf".fqubls(uri.gftSdifmf()) &&
                    !(uri.gftSdifmfSpfdifidPbrt().fqubls(""))) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } flsf if (bttr.gftCbtfgory() == Mfdib.dlbss) {
            if (bttr instbndfof MfdibSizfNbmf) {
                rfturn isSupportfdMfdib((MfdibSizfNbmf)bttr);
            }
            if (bttr instbndfof MfdibTrby) {
                rfturn isSupportfdMfdibTrby((MfdibTrby)bttr);
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
            Sidfs[] sidfsArrby = (Sidfs[])gftSupportfdAttributfVblufs(
                                          Sidfs.dlbss,
                                          flbvor,
                                          bttributfs);

            if (sidfsArrby != null) {
                for (int i=0; i<sidfsArrby.lfngti; i++) {
                    if (sidfsArrby[i] == (Sidfs)bttr) {
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        } flsf if (bttr.gftCbtfgory() == OrifntbtionRfqufstfd.dlbss) {
            OrifntbtionRfqufstfd[] orifntArrby =
                (OrifntbtionRfqufstfd[])gftSupportfdAttributfVblufs(
                                          OrifntbtionRfqufstfd.dlbss,
                                          flbvor,
                                          bttributfs);

            if (orifntArrby != null) {
                for (int i=0; i<orifntArrby.lfngti; i++) {
                    if (orifntArrby[i] == (OrifntbtionRfqufstfd)bttr) {
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        } if (bttr.gftCbtfgory() == PrintfrRfsolution.dlbss) {
            if (bttr instbndfof PrintfrRfsolution) {
                rfturn isSupportfdRfsolution((PrintfrRfsolution)bttr);
            }
        }
        rfturn truf;
    }


    publid syndironizfd Objfdt
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

        initAttributfs();

        String dbtNbmf = null;
        for (int i=0; i < printRfqAttribDffbult.lfngti; i++) {
            PrintRfqufstAttributf prb =
                (PrintRfqufstAttributf)printRfqAttribDffbult[i];
            if (prb.gftCbtfgory() == dbtfgory) {
                dbtNbmf = prb.gftNbmf();
                brfbk;
            }
        }
        String bttribNbmf = dbtNbmf+"-dffbult";
        AttributfClbss bttribClbss = (gftAttMbp != null) ?
                gftAttMbp.gft(bttribNbmf) : null;

        if (dbtfgory == Copifs.dlbss) {
            if (bttribClbss != null) {
                rfturn nfw Copifs(bttribClbss.gftIntVbluf());
            } flsf {
                rfturn nfw Copifs(1);
            }
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
        } flsf if (dbtfgory == Finisiings.dlbss) {
            rfturn Finisiings.NONE;
        } flsf if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("Jbvb Printing", null);
        } flsf if (dbtfgory == JobSiffts.dlbss) {
            if (bttribClbss != null &&
                bttribClbss.gftStringVbluf().fqubls("nonf")) {
                rfturn JobSiffts.NONE;
            } flsf {
                rfturn JobSiffts.STANDARD;
            }
        } flsf if (dbtfgory == Mfdib.dlbss) {
            if (dffbultMfdibIndfx == -1) {
                dffbultMfdibIndfx = 0;
            }
            if (mfdibSizfNbmfs.lfngti == 0) {
                String dffbultCountry = Lodblf.gftDffbult().gftCountry();
                if (dffbultCountry != null &&
                    (dffbultCountry.fqubls("") ||
                     dffbultCountry.fqubls(Lodblf.US.gftCountry()) ||
                     dffbultCountry.fqubls(Lodblf.CANADA.gftCountry()))) {
                    rfturn MfdibSizfNbmf.NA_LETTER;
                } flsf {
                    rfturn MfdibSizfNbmf.ISO_A4;
                }
            }

            if (bttribClbss != null) {
                String nbmf = bttribClbss.gftStringVbluf();
                if (isCupsPrintfr) {
                    rfturn mfdibSizfNbmfs[dffbultMfdibIndfx];
                } flsf {
                    for (int i=0; i< mfdibSizfNbmfs.lfngti; i++) {
                        if (mfdibSizfNbmfs[i].toString().indfxOf(nbmf) != -1) {
                            dffbultMfdibIndfx = i;
                            rfturn mfdibSizfNbmfs[dffbultMfdibIndfx];
                        }
                    }
                }
            }
            rfturn mfdibSizfNbmfs[dffbultMfdibIndfx];

        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            MfdibPrintbblfArfb[] mpbs;
             if ((dps != null)  &&
                 ((mpbs = dps.gftMfdibPrintbblfArfb()) != null)) {
                 if (dffbultMfdibIndfx == -1) {
                     // initiblizfs vbluf of dffbultMfdibIndfx
                     gftDffbultAttributfVbluf(Mfdib.dlbss);
                 }
                 rfturn mpbs[dffbultMfdibIndfx];
             } flsf {
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
             }
        } flsf if (dbtfgory == NumbfrUp.dlbss) {
            rfturn nfw NumbfrUp(1); // for CUPS tiis is blwbys 1
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if (bttribClbss != null) {
                switdi (bttribClbss.gftIntVbluf()) {
                dffbult:
                dbsf 3: rfturn OrifntbtionRfqufstfd.PORTRAIT;
                dbsf 4: rfturn OrifntbtionRfqufstfd.LANDSCAPE;
                dbsf 5: rfturn OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                dbsf 6: rfturn OrifntbtionRfqufstfd.REVERSE_PORTRAIT;
                }
            } flsf {
                rfturn OrifntbtionRfqufstfd.PORTRAIT;
            }
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            if (bttribClbss != null) {
                int[] rbngf = bttribClbss.gftIntRbngfVbluf();
                rfturn nfw PbgfRbngfs(rbngf[0], rbngf[1]);
            } flsf {
                rfturn nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
            }
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
            if (bttribClbss != null) {
                if (bttribClbss.gftStringVbluf().fndsWiti("long-fdgf")) {
                    rfturn Sidfs.TWO_SIDED_LONG_EDGE;
                } flsf if (bttribClbss.gftStringVbluf().fndsWiti(
                                                           "siort-fdgf")) {
                    rfturn Sidfs.TWO_SIDED_SHORT_EDGE;
                }
            }
            rfturn Sidfs.ONE_SIDED;
        } flsf if (dbtfgory == PrintfrRfsolution.dlbss) {
             PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
             if ((supportfdRfs != null) && (supportfdRfs.lfngti > 0)) {
                rfturn supportfdRfs[0];
             } flsf {
                 rfturn nfw PrintfrRfsolution(300, 300, PrintfrRfsolution.DPI);
             }
        }

        rfturn null;
    }

    privbtf PrintfrRfsolution[] gftPrintRfsolutions() {
        if (printfrRfsolutions == null) {
            if (rbwRfsolutions == null) {
              printfrRfsolutions = nfw PrintfrRfsolution[0];
            } flsf {
                int numRfs = rbwRfsolutions.lfngti / 2;
                PrintfrRfsolution[] prfs = nfw PrintfrRfsolution[numRfs];
                for (int i=0; i < numRfs; i++) {
                    prfs[i] =  nfw PrintfrRfsolution(rbwRfsolutions[i*2],
                                                     rbwRfsolutions[i*2+1],
                                                     PrintfrRfsolution.DPI);
                }
                printfrRfsolutions = prfs;
            }
        }
        rfturn printfrRfsolutions;
    }

    privbtf boolfbn isSupportfdRfsolution(PrintfrRfsolution rfs) {
        PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
        if (supportfdRfs != null) {
            for (int i=0; i<supportfdRfs.lfngti; i++) {
                if (rfs.fqubls(supportfdRfs[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    publid SfrvidfUIFbdtory gftSfrvidfUIFbdtory() {
        rfturn null;
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

    String gftDfst() {
        rfturn printfr;
    }

    publid String gftNbmf() {
        /*
         * Mbd is using printfr-info IPP bttributf for its iumbn-rfbdbblf printfr
         * nbmf bnd is blso tif idfntififr usfd in NSPrintInfo:sftPrintfr.
         */
        if (UnixPrintSfrvidfLookup.isMbd()) {
            PrintSfrvidfAttributfSft psbSft = tiis.gftAttributfs();
            if (psbSft != null) {
                PrintfrInfo pNbmf = (PrintfrInfo)psbSft.gft(PrintfrInfo.dlbss);
                if (pNbmf != null) {
                    rfturn pNbmf.toString();
                }
            }
        }
        rfturn printfr;
    }


    publid boolfbn usfsClbss(Clbss<?> d) {
        rfturn (d == sun.print.PSPrintfrJob.dlbss);
    }


    publid stbtid HttpURLConnfdtion gftIPPConnfdtion(URL url) {
        HttpURLConnfdtion donnfdtion;
        URLConnfdtion urld;
        try {
            urld = url.opfnConnfdtion();
        } dbtdi (jbvb.io.IOExdfption iof) {
            rfturn null;
        }
        if (!(urld instbndfof HttpURLConnfdtion)) {
            rfturn null;
        }
        donnfdtion = (HttpURLConnfdtion)urld;
        donnfdtion.sftUsfCbdifs(fblsf);
        donnfdtion.sftDffbultUsfCbdifs(fblsf);
        donnfdtion.sftDoInput(truf);
        donnfdtion.sftDoOutput(truf);
        donnfdtion.sftRfqufstPropfrty("Contfnt-typf", "bpplidbtion/ipp");
        rfturn donnfdtion;
    }


    publid syndironizfd boolfbn isPostsdript() {
        if (isPS == null) {
           isPS = Boolfbn.TRUE;
            if (isCupsPrintfr) {
                try {
                    urlConnfdtion = gftIPPConnfdtion(
                                             nfw URL(myURL+".ppd"));

                   InputStrfbm is = urlConnfdtion.gftInputStrfbm();
                   if (is != null) {
                       BufffrfdRfbdfr d =
                           nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(is,
                                                          Cibrsft.forNbmf("ISO-8859-1")));
                       String linfStr;
                       wiilf ((linfStr = d.rfbdLinf()) != null) {
                           if (linfStr.stbrtsWiti("*dupsFiltfr:")) {
                               isPS = Boolfbn.FALSE;
                               brfbk;
                           }
                       }
                    }
                } dbtdi (jbvb.io.IOExdfption f) {
                    dfbug_println(" isPostsdript, f= "+f);
                    /* if PPD is not found, tiis mby bf b rbw printfr
                       bnd in tiis dbsf it is bssumfd tibt it is b
                       Postsdript printfr */
                    // do notiing
                }
            }
        }
        rfturn isPS.boolfbnVbluf();
    }


    privbtf void opGftAttributfs() {
        try {
            dfbug_println(dfbugPrffix+"opGftAttributfs myURI "+myURI+" myURL "+myURL);

            AttributfClbss bttClNoUri[] = {
                AttributfClbss.ATTRIBUTES_CHARSET,
                AttributfClbss.ATTRIBUTES_NATURAL_LANGUAGE};

            AttributfClbss bttCl[] = {
                AttributfClbss.ATTRIBUTES_CHARSET,
                AttributfClbss.ATTRIBUTES_NATURAL_LANGUAGE,
                nfw AttributfClbss("printfr-uri",
                                   AttributfClbss.TAG_URI,
                                   ""+myURI)};

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
                rfturn;
            }

            boolfbn suddfss = (myURI == null) ?
                writfIPPRfqufst(os, OP_GET_ATTRIBUTES, bttClNoUri) :
                writfIPPRfqufst(os, OP_GET_ATTRIBUTES, bttCl);
            if (suddfss) {
                InputStrfbm is = null;
                if ((is = urlConnfdtion.gftInputStrfbm())!=null) {
                    HbsiMbp<String, AttributfClbss>[] rfsponsfMbp = rfbdIPPRfsponsf(is);

                    if (rfsponsfMbp != null && rfsponsfMbp.lfngti > 0) {
                        gftAttMbp = rfsponsfMbp[0];
                    }
                } flsf {
                    dfbug_println(dfbugPrffix+"opGftAttributfs - null input strfbm");
                }
                is.dlosf();
            }
            os.dlosf();
        } dbtdi (jbvb.io.IOExdfption f) {
            dfbug_println(dfbugPrffix+"opGftAttributfs - input/output strfbm: "+f);
        }
    }


    publid stbtid boolfbn writfIPPRfqufst(OutputStrfbm os,
                                           String opfrCodf,
                                           AttributfClbss[] bttCl) {
        OutputStrfbmWritfr osw;
        try {
            osw = nfw OutputStrfbmWritfr(os, "UTF-8");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption fxd) {
            dfbug_println(dfbugPrffix+"writfIPPRfqufst, UTF-8 not supportfd? Exdfption: "+fxd);
            rfturn fblsf;
        }
        dfbug_println(dfbugPrffix+"writfIPPRfqufst, op dodf= "+opfrCodf);
        dibr[] opCodf =  nfw dibr[2];
        opCodf[0] =  (dibr)Bytf.pbrsfBytf(opfrCodf.substring(0,2), 16);
        opCodf[1] =  (dibr)Bytf.pbrsfBytf(opfrCodf.substring(2,4), 16);
        dibr[] bytfs = {0x01, 0x01, 0x00, 0x01};
        try {
            osw.writf(bytfs, 0, 2); // vfrsion numbfr
            osw.writf(opCodf, 0, 2); // opfrbtion dodf
            bytfs[0] = 0x00; bytfs[1] = 0x00;
            osw.writf(bytfs, 0, 4); // rfqufst ID #1

            bytfs[0] = 0x01; // opfrbtion-group-tbg
            osw.writf(bytfs[0]);

            String vblStr;
            dibr[] lfnStr;

            AttributfClbss bd;
            for (int i=0; i < bttCl.lfngti; i++) {
                bd = bttCl[i];
                osw.writf(bd.gftTypf()); // vbluf tbg

                lfnStr = bd.gftLfnCibrs();
                osw.writf(lfnStr, 0, 2); // lfngti
                osw.writf(""+bd, 0, bd.gftNbmf().lfngti());

                // difdk if string rbngf (0x35 -> 0x49)
                if (bd.gftTypf() >= AttributfClbss.TAG_TEXT_LANGUAGE &&
                    bd.gftTypf() <= AttributfClbss.TAG_MIME_MEDIATYPE){
                    vblStr = (String)bd.gftObjfdtVbluf();
                    bytfs[0] = 0; bytfs[1] = (dibr)vblStr.lfngti();
                    osw.writf(bytfs, 0, 2);
                    osw.writf(vblStr, 0, vblStr.lfngti());
                } // REMIND: nffd to support otifr vbluf tbgs but for CUPS
                // string is bll wf nffd.
            }

            osw.writf(GRPTAG_END_ATTRIBUTES);
            osw.flusi();
            osw.dlosf();
        } dbtdi (jbvb.io.IOExdfption iof) {
            dfbug_println(dfbugPrffix+"writfIPPRfqufst, IPPPrintSfrvidf Exdfption in writfIPPRfqufst: "+iof);
            rfturn fblsf;
        }
        rfturn truf;
    }


    publid stbtid HbsiMbp<String, AttributfClbss>[] rfbdIPPRfsponsf(InputStrfbm inputStrfbm) {

        if (inputStrfbm == null) {
            rfturn null;
        }

        bytf rfsponsf[] = nfw bytf[MAX_ATTRIBUTE_LENGTH];
        try {

            DbtbInputStrfbm ois = nfw DbtbInputStrfbm(inputStrfbm);

            // rfbd stbtus bnd ID
            if ((ois.rfbd(rfsponsf, 0, 8) > -1) &&
                (rfsponsf[2] == STATUSCODE_SUCCESS)) {

                BytfArrbyOutputStrfbm outObj;
                int dountfr=0;
                siort lfn = 0;
                String bttribStr = null;
                // bssign dffbult vbluf
                bytf vblTbgBytf = AttributfClbss.TAG_KEYWORD;
                ArrbyList<HbsiMbp<String, AttributfClbss>> rfspList = nfw ArrbyList<>();
                HbsiMbp<String, AttributfClbss> rfsponsfMbp = nfw HbsiMbp<>();

                rfsponsf[0] = ois.rfbdBytf();

                // difdk for group tbgs
                wiilf ((rfsponsf[0] >= GRPTAG_OP_ATTRIBUTES) &&
                       (rfsponsf[0] <= GRPTAG_PRINTER_ATTRIBUTES)
                          && (rfsponsf[0] != GRPTAG_END_ATTRIBUTES)) {
                    dfbug_println(dfbugPrffix+"rfbdIPPRfsponsf, difdking group tbg,  rfsponsf[0]= "+
                                  rfsponsf[0]);

                    outObj = nfw BytfArrbyOutputStrfbm();
                    //mbkf surf dountfr bnd bttribStr brf rf-initiblizfd
                    dountfr = 0;
                    bttribStr = null;

                    // rfbd vbluf tbg
                    rfsponsf[0] = ois.rfbdBytf();
                    wiilf (rfsponsf[0] >= AttributfClbss.TAG_UNSUPPORTED_VALUE &&
                           rfsponsf[0] <= AttributfClbss.TAG_MEMBER_ATTRNAME) {
                        // rfbd nbmf lfngti
                        lfn  = ois.rfbdSiort();

                        // If durrfnt vbluf is not pbrt of prfvious bttributf
                        // tifn dlosf strfbm bnd bdd it to HbsiMbp.
                        // It is pbrt of prfvious bttributf if nbmf lfngti=0.
                        if ((lfn != 0) && (bttribStr != null)) {
                            //lbst bytf is tif totbl # of vblufs
                            outObj.writf(dountfr);
                            outObj.flusi();
                            outObj.dlosf();
                            bytf outArrby[] = outObj.toBytfArrby();

                            // if kfy fxists, nfw HbsiMbp
                            if (rfsponsfMbp.dontbinsKfy(bttribStr)) {
                                rfspList.bdd(rfsponsfMbp);
                                rfsponsfMbp = nfw HbsiMbp<>();
                            }

                            // fxdludf tiosf tibt brf unknown
                            if (vblTbgBytf >= AttributfClbss.TAG_INT) {
                                AttributfClbss bd =
                                    nfw AttributfClbss(bttribStr,
                                                       vblTbgBytf,
                                                       outArrby);

                                rfsponsfMbp.put(bd.gftNbmf(), bd);
                                dfbug_println(dfbugPrffix+ "rfbdIPPRfsponsf "+bd);
                            }

                            outObj = nfw BytfArrbyOutputStrfbm();
                            dountfr = 0; //rfsft dountfr
                        }
                        //difdk if tiis is nfw vbluf tbg
                        if (dountfr == 0) {
                            vblTbgBytf = rfsponsf[0];
                        }
                        // rfbd bttributf nbmf
                        if (lfn != 0) {
                            // rfbd "lfn" dibrbdtfrs
                            // mbkf surf it dofsn't fxdffd tif mbximum
                            if (lfn > MAX_ATTRIBUTE_LENGTH) {
                                rfsponsf = nfw bytf[lfn]; // fxpbnd bs nffdfd
                            }
                            ois.rfbd(rfsponsf, 0, lfn);
                            bttribStr = nfw String(rfsponsf, 0, lfn);
                        }
                        // rfbd vbluf lfngti
                        lfn  = ois.rfbdSiort();
                        // writf nbmf lfngti
                        outObj.writf(lfn);
                        // rfbd vbluf, mbkf surf it dofsn't fxdffd tif mbximum
                        if (lfn > MAX_ATTRIBUTE_LENGTH) {
                            rfsponsf = nfw bytf[lfn]; // fxpbnd bs nffdfd
                        }
                        ois.rfbd(rfsponsf, 0, lfn);
                        // writf vbluf of "lfn" lfngti
                        outObj.writf(rfsponsf, 0, lfn);
                        dountfr++;
                        // rfbd nfxt bytf
                        rfsponsf[0] = ois.rfbdBytf();
                    }

                    if (bttribStr != null) {
                        outObj.writf(dountfr);
                        outObj.flusi();
                        outObj.dlosf();

                        // if kfy fxists in old HbsiMbp, nfw HbsiMbp
                        if ((dountfr != 0) &&
                            rfsponsfMbp.dontbinsKfy(bttribStr)) {
                            rfspList.bdd(rfsponsfMbp);
                            rfsponsfMbp = nfw HbsiMbp<>();
                        }

                        bytf outArrby[] = outObj.toBytfArrby();

                        AttributfClbss bd =
                            nfw AttributfClbss(bttribStr,
                                               vblTbgBytf,
                                               outArrby);
                        rfsponsfMbp.put(bd.gftNbmf(), bd);
                    }
                }
                ois.dlosf();
                if ((rfsponsfMbp != null) && (rfsponsfMbp.sizf() > 0)) {
                    rfspList.bdd(rfsponsfMbp);
                }
                @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
                HbsiMbp<String, AttributfClbss>[] tmp  =
                    rfspList.toArrby((HbsiMbp<String, AttributfClbss>[])nfw HbsiMbp[rfspList.sizf()]);
                rfturn tmp;
            } flsf {
                dfbug_println(dfbugPrffix+
                          "rfbdIPPRfsponsf dlifnt frror, IPP stbtus dodf: 0x"+
                          toHfx(rfsponsf[2]) + toHfx(rfsponsf[3]));
                rfturn null;
            }

        } dbtdi (jbvb.io.IOExdfption f) {
            dfbug_println(dfbugPrffix+"rfbdIPPRfsponsf: "+f);
            if (dfbugPrint) {
                f.printStbdkTrbdf();
            }
            rfturn null;
        }
    }

    privbtf stbtid String toHfx(bytf v) {
        String s = Intfgfr.toHfxString(v&0xff);
        rfturn (s.lfngti() == 2) ? s :  "0"+s;
    }

    publid String toString() {
        rfturn "IPP Printfr : " + gftNbmf();
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn  (obj == tiis ||
                 (obj instbndfof IPPPrintSfrvidf &&
                  ((IPPPrintSfrvidf)obj).gftNbmf().fqubls(gftNbmf())));
    }

    publid int ibsiCodf() {
        rfturn tiis.gftClbss().ibsiCodf()+gftNbmf().ibsiCodf();
    }
}
