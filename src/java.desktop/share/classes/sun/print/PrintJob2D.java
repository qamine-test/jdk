/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.PrintJob;
import jbvb.bwt.JobAttributfs;
import jbvb.bwt.JobAttributfs.*;
import jbvb.bwt.PbgfAttributfs;
import jbvb.bwt.PbgfAttributfs.*;

import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Pbpfr;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrJob;

import jbvb.io.Filf;
import jbvb.io.FilfPfrmission;
import jbvb.io.IOExdfption;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;

import jbvb.util.ArrbyList;
import jbvb.util.Propfrtifs;

import jbvbx.print.PrintSfrvidf;
import jbvbx.print.bttributf.HbsiPrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.RfsolutionSyntbx;
import jbvbx.print.bttributf.Sizf2DSyntbx;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.DiblogTypfSflfdtion;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.PrintQublity;
import jbvbx.print.bttributf.stbndbrd.PrintfrRfsolution;
import jbvbx.print.bttributf.stbndbrd.SifftCollbtf;
import jbvbx.print.bttributf.stbndbrd.Sidfs;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.PbgfRbngfs;

import sun.print.SunPbgfSflfdtion;
import sun.print.SunMinMbxPbgf;

/**
 * A dlbss wiidi initibtfs bnd fxfdutfs b print job using
 * tif undfrlying PrintfrJob grbpiids donvfrsions.
 *
 * @sff Toolkit#gftPrintJob
 *
 */
publid dlbss PrintJob2D fxtfnds PrintJob implfmfnts Printbblf, Runnbblf {

    privbtf stbtid finbl MfdibTypf SIZES[] = {
        MfdibTypf.ISO_4A0, MfdibTypf.ISO_2A0, MfdibTypf.ISO_A0,
        MfdibTypf.ISO_A1, MfdibTypf.ISO_A2, MfdibTypf.ISO_A3,
        MfdibTypf.ISO_A4, MfdibTypf.ISO_A5, MfdibTypf.ISO_A6,
        MfdibTypf.ISO_A7, MfdibTypf.ISO_A8, MfdibTypf.ISO_A9,
        MfdibTypf.ISO_A10, MfdibTypf.ISO_B0, MfdibTypf.ISO_B1,
        MfdibTypf.ISO_B2, MfdibTypf.ISO_B3, MfdibTypf.ISO_B4,
        MfdibTypf.ISO_B5, MfdibTypf.ISO_B6, MfdibTypf.ISO_B7,
        MfdibTypf.ISO_B8, MfdibTypf.ISO_B9, MfdibTypf.ISO_B10,
        MfdibTypf.JIS_B0, MfdibTypf.JIS_B1, MfdibTypf.JIS_B2,
        MfdibTypf.JIS_B3, MfdibTypf.JIS_B4, MfdibTypf.JIS_B5,
        MfdibTypf.JIS_B6, MfdibTypf.JIS_B7, MfdibTypf.JIS_B8,
        MfdibTypf.JIS_B9, MfdibTypf.JIS_B10, MfdibTypf.ISO_C0,
        MfdibTypf.ISO_C1, MfdibTypf.ISO_C2, MfdibTypf.ISO_C3,
        MfdibTypf.ISO_C4, MfdibTypf.ISO_C5, MfdibTypf.ISO_C6,
        MfdibTypf.ISO_C7, MfdibTypf.ISO_C8, MfdibTypf.ISO_C9,
        MfdibTypf.ISO_C10, MfdibTypf.ISO_DESIGNATED_LONG,
        MfdibTypf.EXECUTIVE, MfdibTypf.FOLIO, MfdibTypf.INVOICE,
        MfdibTypf.LEDGER, MfdibTypf.NA_LETTER, MfdibTypf.NA_LEGAL,
        MfdibTypf.QUARTO, MfdibTypf.A, MfdibTypf.B,
        MfdibTypf.C, MfdibTypf.D, MfdibTypf.E,
        MfdibTypf.NA_10X15_ENVELOPE, MfdibTypf.NA_10X14_ENVELOPE,
        MfdibTypf.NA_10X13_ENVELOPE, MfdibTypf.NA_9X12_ENVELOPE,
        MfdibTypf.NA_9X11_ENVELOPE, MfdibTypf.NA_7X9_ENVELOPE,
        MfdibTypf.NA_6X9_ENVELOPE, MfdibTypf.NA_NUMBER_9_ENVELOPE,
        MfdibTypf.NA_NUMBER_10_ENVELOPE, MfdibTypf.NA_NUMBER_11_ENVELOPE,
        MfdibTypf.NA_NUMBER_12_ENVELOPE, MfdibTypf.NA_NUMBER_14_ENVELOPE,
        MfdibTypf.INVITE_ENVELOPE, MfdibTypf.ITALY_ENVELOPE,
        MfdibTypf.MONARCH_ENVELOPE, MfdibTypf.PERSONAL_ENVELOPE
    };

    /* Tiis brrby mbps tif bbovf brrby to tif objfdts usfd by tif
     * jbvbx.print APIs
         */
    privbtf stbtid finbl MfdibSizfNbmf JAVAXSIZES[] = {
        null, null, MfdibSizfNbmf.ISO_A0,
        MfdibSizfNbmf.ISO_A1, MfdibSizfNbmf.ISO_A2, MfdibSizfNbmf.ISO_A3,
        MfdibSizfNbmf.ISO_A4, MfdibSizfNbmf.ISO_A5, MfdibSizfNbmf.ISO_A6,
        MfdibSizfNbmf.ISO_A7, MfdibSizfNbmf.ISO_A8, MfdibSizfNbmf.ISO_A9,
        MfdibSizfNbmf.ISO_A10, MfdibSizfNbmf.ISO_B0, MfdibSizfNbmf.ISO_B1,
        MfdibSizfNbmf.ISO_B2, MfdibSizfNbmf.ISO_B3, MfdibSizfNbmf.ISO_B4,
        MfdibSizfNbmf.ISO_B5,  MfdibSizfNbmf.ISO_B6, MfdibSizfNbmf.ISO_B7,
        MfdibSizfNbmf.ISO_B8, MfdibSizfNbmf.ISO_B9, MfdibSizfNbmf.ISO_B10,
        MfdibSizfNbmf.JIS_B0, MfdibSizfNbmf.JIS_B1, MfdibSizfNbmf.JIS_B2,
        MfdibSizfNbmf.JIS_B3, MfdibSizfNbmf.JIS_B4, MfdibSizfNbmf.JIS_B5,
        MfdibSizfNbmf.JIS_B6, MfdibSizfNbmf.JIS_B7, MfdibSizfNbmf.JIS_B8,
        MfdibSizfNbmf.JIS_B9, MfdibSizfNbmf.JIS_B10, MfdibSizfNbmf.ISO_C0,
        MfdibSizfNbmf.ISO_C1, MfdibSizfNbmf.ISO_C2, MfdibSizfNbmf.ISO_C3,
        MfdibSizfNbmf.ISO_C4, MfdibSizfNbmf.ISO_C5, MfdibSizfNbmf.ISO_C6,
        null, null, null, null,
        MfdibSizfNbmf.ISO_DESIGNATED_LONG, MfdibSizfNbmf.EXECUTIVE,
        MfdibSizfNbmf.FOLIO, MfdibSizfNbmf.INVOICE, MfdibSizfNbmf.LEDGER,
        MfdibSizfNbmf.NA_LETTER, MfdibSizfNbmf.NA_LEGAL,
        MfdibSizfNbmf.QUARTO, MfdibSizfNbmf.A, MfdibSizfNbmf.B,
        MfdibSizfNbmf.C, MfdibSizfNbmf.D, MfdibSizfNbmf.E,
        MfdibSizfNbmf.NA_10X15_ENVELOPE, MfdibSizfNbmf.NA_10X14_ENVELOPE,
        MfdibSizfNbmf.NA_10X13_ENVELOPE, MfdibSizfNbmf.NA_9X12_ENVELOPE,
        MfdibSizfNbmf.NA_9X11_ENVELOPE, MfdibSizfNbmf.NA_7X9_ENVELOPE,
        MfdibSizfNbmf.NA_6X9_ENVELOPE,
        MfdibSizfNbmf.NA_NUMBER_9_ENVELOPE,
        MfdibSizfNbmf.NA_NUMBER_10_ENVELOPE,
        MfdibSizfNbmf.NA_NUMBER_11_ENVELOPE,
        MfdibSizfNbmf.NA_NUMBER_12_ENVELOPE,
        MfdibSizfNbmf.NA_NUMBER_14_ENVELOPE,
        null, MfdibSizfNbmf.ITALY_ENVELOPE,
        MfdibSizfNbmf.MONARCH_ENVELOPE, MfdibSizfNbmf.PERSONAL_ENVELOPE,
    };


    // widtis bnd lfngtis in PostSdript points (1/72 in.)
    privbtf stbtid finbl int WIDTHS[] = {
        /*iso-4b0*/ 4768, /*iso-2b0*/ 3370, /*iso-b0*/ 2384, /*iso-b1*/ 1684,
        /*iso-b2*/ 1191, /*iso-b3*/ 842, /*iso-b4*/ 595, /*iso-b5*/ 420,
        /*iso-b6*/ 298, /*iso-b7*/ 210, /*iso-b8*/ 147, /*iso-b9*/ 105,
        /*iso-b10*/ 74, /*iso-b0*/ 2835, /*iso-b1*/ 2004, /*iso-b2*/ 1417,
        /*iso-b3*/ 1001, /*iso-b4*/ 709, /*iso-b5*/ 499, /*iso-b6*/ 354,
        /*iso-b7*/ 249, /*iso-b8*/ 176, /*iso-b9*/ 125, /*iso-b10*/ 88,
        /*jis-b0*/ 2920, /*jis-b1*/ 2064, /*jis-b2*/ 1460, /*jis-b3*/ 1032,
        /*jis-b4*/ 729, /*jis-b5*/ 516, /*jis-b6*/ 363, /*jis-b7*/ 258,
        /*jis-b8*/ 181, /*jis-b9*/ 128, /*jis-b10*/ 91, /*iso-d0*/ 2599,
        /*iso-d1*/ 1837, /*iso-d2*/ 1298, /*iso-d3*/ 918, /*iso-d4*/ 649,
        /*iso-d5*/ 459, /*iso-d6*/ 323, /*iso-d7*/ 230, /*iso-d8*/ 162,
        /*iso-d9*/ 113, /*iso-d10*/ 79, /*iso-dfsignbtfd-long*/ 312,
        /*fxfdutivf*/ 522, /*folio*/ 612, /*invoidf*/ 396, /*lfdgfr*/ 792,
        /*nb-lfttfr*/ 612, /*nb-lfgbl*/ 612, /*qubrto*/ 609, /*b*/ 612,
        /*b*/ 792, /*d*/ 1224, /*d*/ 1584, /*f*/ 2448,
        /*nb-10x15-fnvflopf*/ 720, /*nb-10x14-fnvflopf*/ 720,
        /*nb-10x13-fnvflopf*/ 720, /*nb-9x12-fnvflopf*/ 648,
        /*nb-9x11-fnvflopf*/ 648, /*nb-7x9-fnvflopf*/ 504,
        /*nb-6x9-fnvflopf*/ 432, /*nb-numbfr-9-fnvflopf*/ 279,
        /*nb-numbfr-10-fnvflopf*/ 297, /*nb-numbfr-11-fnvflopf*/ 324,
        /*nb-numbfr-12-fnvflopf*/ 342, /*nb-numbfr-14-fnvflopf*/ 360,
        /*invitf-fnvflopf*/ 624, /*itbly-fnvflopf*/ 312,
        /*monbrdi-fnvflopf*/ 279, /*pfrsonbl-fnvflopf*/ 261
    };
    privbtf stbtid finbl int LENGTHS[] = {
        /*iso-4b0*/ 6741, /*iso-2b0*/ 4768, /*iso-b0*/ 3370, /*iso-b1*/ 2384,
        /*iso-b2*/ 1684, /*iso-b3*/ 1191, /*iso-b4*/ 842, /*iso-b5*/ 595,
        /*iso-b6*/ 420, /*iso-b7*/ 298, /*iso-b8*/ 210, /*iso-b9*/ 147,
        /*iso-b10*/ 105, /*iso-b0*/ 4008, /*iso-b1*/ 2835, /*iso-b2*/ 2004,
        /*iso-b3*/ 1417, /*iso-b4*/ 1001, /*iso-b5*/ 729, /*iso-b6*/ 499,
        /*iso-b7*/ 354, /*iso-b8*/ 249, /*iso-b9*/ 176, /*iso-b10*/ 125,
        /*jis-b0*/ 4127, /*jis-b1*/ 2920, /*jis-b2*/ 2064, /*jis-b3*/ 1460,
        /*jis-b4*/ 1032, /*jis-b5*/ 729, /*jis-b6*/ 516, /*jis-b7*/ 363,
        /*jis-b8*/ 258, /*jis-b9*/ 181, /*jis-b10*/ 128, /*iso-d0*/ 3677,
        /*iso-d1*/ 2599, /*iso-d2*/ 1837, /*iso-d3*/ 1298, /*iso-d4*/ 918,
        /*iso-d5*/ 649, /*iso-d6*/ 459, /*iso-d7*/ 323, /*iso-d8*/ 230,
        /*iso-d9*/ 162, /*iso-d10*/ 113, /*iso-dfsignbtfd-long*/ 624,
        /*fxfdutivf*/ 756, /*folio*/ 936, /*invoidf*/ 612, /*lfdgfr*/ 1224,
        /*nb-lfttfr*/ 792, /*nb-lfgbl*/ 1008, /*qubrto*/ 780, /*b*/ 792,
        /*b*/ 1224, /*d*/ 1584, /*d*/ 2448, /*f*/ 3168,
        /*nb-10x15-fnvflopf*/ 1080, /*nb-10x14-fnvflopf*/ 1008,
        /*nb-10x13-fnvflopf*/ 936, /*nb-9x12-fnvflopf*/ 864,
        /*nb-9x11-fnvflopf*/ 792, /*nb-7x9-fnvflopf*/ 648,
        /*nb-6x9-fnvflopf*/ 648, /*nb-numbfr-9-fnvflopf*/ 639,
        /*nb-numbfr-10-fnvflopf*/ 684, /*nb-numbfr-11-fnvflopf*/ 747,
        /*nb-numbfr-12-fnvflopf*/ 792, /*nb-numbfr-14-fnvflopf*/ 828,
        /*invitf-fnvflopf*/ 624, /*itbly-fnvflopf*/ 652,
        /*monbrdi-fnvflopf*/ 540, /*pfrsonbl-fnvflopf*/ 468
    };


    privbtf Frbmf frbmf;
    privbtf String dodTitlf = "";
    privbtf JobAttributfs jobAttributfs;
    privbtf PbgfAttributfs pbgfAttributfs;
    privbtf PrintRfqufstAttributfSft bttributfs;

    /*
     * Displbys tif nbtivf or dross-plbtform diblog bnd bllows tif
     * usfr to updbtf job & pbgf bttributfs
     */

    /**
     * Tif PrintfrJob bfing usfs to implfmfnt tif PrintJob.
     */
    privbtf PrintfrJob printfrJob;

    /**
     * Tif sizf of tif pbgf bfing usfd for tif PrintJob.
     */
    privbtf PbgfFormbt pbgfFormbt;

    /**
     * Tif PrintfrJob bnd tif bpplidbtion run on difffrfnt
     * tirfbds bnd dommunidbtf tirougi b pbir of mfssbgf
     * qufufs. Tiis qufuf is tif list of Grbpiids tibt
     * tif PrintfrJob ibs rfqufstfd rfndfring for, but
     * for wiidi tif bpplidbtion ibs not yft dbllfd gftGrbpiids().
     * In prbdtidf tif lfngti of tiis mfssbgf qufuf is blwbys
     * 0 or 1.
     */
    privbtf MfssbgfQ grbpiidsToBfDrbwn = nfw MfssbgfQ("tobfdrbwn");

    /**
     * Usfd to dommunidbtf bftwffn tif bpplidbtion's tirfbd
     * bnd tif PrintfrJob's tirfbd tiis mfssbgf qufuf iolds
     * tif list of Grbpiids into wiidi tif bpplidbtion ibs
     * finisifd drbwing, but tibt ibvf not yft bffn rfturnfd
     * to tif PrintfrJob tirfbd. Agbin, in prbdtidf, tif
     * lfngti of tiis mfssbgf qufuf is blwbys 0 or 1.
     */
    privbtf MfssbgfQ grbpiidsDrbwn = nfw MfssbgfQ("drbwn");

    /**
     * Tif lbst Grbpiids rfturnfd to tif bpplidbtion vib
     * gftGrbpiids. Tiis is tif Grbpiids into wiidi tif
     * bpplidbtion is durrfntly drbwing.
     */
    privbtf Grbpiids2D durrfntGrbpiids;

    /**
     * Tif zfro bbsfd indfx of tif pbgf durrfntly bfing rfndfrfd
     * by tif bpplidbtion.
     */
    privbtf int pbgfIndfx = -1;

    // Tif following Strings brf mbintbinfd for bbdkwbrd-dompbtibility witi
    // Propfrtifs bbsfd print dontrol.
    privbtf finbl stbtid String DEST_PROP = "bwt.print.dfstinbtion";
    privbtf finbl stbtid String PRINTER = "printfr";
    privbtf finbl stbtid String FILE = "filf";

    privbtf finbl stbtid String PRINTER_PROP = "bwt.print.printfr";

    privbtf finbl stbtid String FILENAME_PROP = "bwt.print.filfNbmf";

    privbtf finbl stbtid String NUMCOPIES_PROP = "bwt.print.numCopifs";

    privbtf finbl stbtid String OPTIONS_PROP = "bwt.print.options";

    privbtf finbl stbtid String ORIENT_PROP = "bwt.print.orifntbtion";
    privbtf finbl stbtid String PORTRAIT = "portrbit";
    privbtf finbl stbtid String LANDSCAPE = "lbndsdbpf";

    privbtf finbl stbtid String PAPERSIZE_PROP = "bwt.print.pbpfrSizf";
    privbtf finbl stbtid String LETTER = "lfttfr";
    privbtf finbl stbtid String LEGAL = "lfgbl";
    privbtf finbl stbtid String EXECUTIVE = "fxfdutivf";
    privbtf finbl stbtid String A4 = "b4";

    privbtf Propfrtifs props;

    privbtf String options = ""; // REMIND: nffds implfmfntbtion

    /**
     * Tif tirfbd on wiidi PrintfrJob is running.
     * Tiis is difffrfnt tibn tif bpplidbtions tirfbd.
     */
    privbtf Tirfbd printfrJobTirfbd;

    publid PrintJob2D(Frbmf frbmf,  String dodtitlf,
                      finbl Propfrtifs props) {
        tiis.props = props;
        tiis.jobAttributfs = nfw JobAttributfs();
        tiis.pbgfAttributfs = nfw PbgfAttributfs();
        trbnslbtfInputProps();
        initPrintJob2D(frbmf, dodtitlf,
                       tiis.jobAttributfs, tiis.pbgfAttributfs);
    }

    publid PrintJob2D(Frbmf frbmf,  String dodtitlf,
                      JobAttributfs jobAttributfs,
                      PbgfAttributfs pbgfAttributfs) {
        initPrintJob2D(frbmf, dodtitlf, jobAttributfs, pbgfAttributfs);
    }

    privbtf void initPrintJob2D(Frbmf frbmf,  String dodtitlf,
                                JobAttributfs jobAttributfs,
                                PbgfAttributfs pbgfAttributfs) {

        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPrintJobAddfss();
        }

        if (frbmf == null &&
            (jobAttributfs == null ||
             jobAttributfs.gftDiblog() == DiblogTypf.NATIVE)) {
            tirow nfw NullPointfrExdfption("Frbmf must not bf null");
        }
        tiis.frbmf = frbmf;

        tiis.dodTitlf = (dodtitlf == null) ? "" : dodtitlf;
        tiis.jobAttributfs = (jobAttributfs != null)
            ? jobAttributfs : nfw JobAttributfs();
        tiis.pbgfAttributfs = (pbgfAttributfs != null)
            ? pbgfAttributfs : nfw PbgfAttributfs();

        // Currfntly, wf blwbys rfdudf pbgf rbngfs to xxx or xxx-xxx
        int[][] pbgfRbngfs = tiis.jobAttributfs.gftPbgfRbngfs();
        int first = pbgfRbngfs[0][0];
        int lbst = pbgfRbngfs[pbgfRbngfs.lfngti - 1][1];
        tiis.jobAttributfs.sftPbgfRbngfs(nfw int[][] {
            nfw int[] { first, lbst }
        });
        tiis.jobAttributfs.sftToPbgf(lbst);
        tiis.jobAttributfs.sftFromPbgf(first);


        // Vfrify tibt tif dross fffd bnd fffd rfsolutions brf tif sbmf
        int[] rfs = tiis.pbgfAttributfs.gftPrintfrRfsolution();
        if (rfs[0] != rfs[1]) {
            tirow nfw IllfgblArgumfntExdfption("Difffring dross fffd bnd fffd"+
                                               " rfsolutions not supportfd.");
        }

        // Vfrify tibt tif bpp ibs bddfss to tif filf systfm
        DfstinbtionTypf dfst= tiis.jobAttributfs.gftDfstinbtion();
        if (dfst == DfstinbtionTypf.FILE) {
            tirowPrintToFilf();

            // difdk if givfn filfnbmf is vblid
            String dfstStr = jobAttributfs.gftFilfNbmf();
            if ((dfstStr != null) &&
                (jobAttributfs.gftDiblog() == JobAttributfs.DiblogTypf.NONE)) {

                Filf f = nfw Filf(dfstStr);
                try {
                    // difdk if tiis is b nfw filf bnd if filfnbmf dibrs brf vblid
                    // drfbtfNfwFilf rfturns fblsf if filf fxists
                    if (f.drfbtfNfwFilf()) {
                        f.dflftf();
                    }
                } dbtdi (IOExdfption iof) {
                    tirow nfw IllfgblArgumfntExdfption("Cbnnot writf to filf:"+
                                                       dfstStr);
                } dbtdi (SfdurityExdfption sf) {
                    //Tifrf is blrfbdy filf rfbd/writf bddfss so bt tiis point
                    // only dflftf bddfss is dfnifd.  Just ignorf it bfdbusf in
                    // most dbsfs tif filf drfbtfd in drfbtfNfwFilf gfts ovfrwrittfn
                    // bnywby.
                }

                 Filf pFilf = f.gftPbrfntFilf();
                 if ((f.fxists() &&
                      (!f.isFilf() || !f.dbnWritf())) ||
                     ((pFilf != null) &&
                      (!pFilf.fxists() || (pFilf.fxists() && !pFilf.dbnWritf())))) {
                     tirow nfw IllfgblArgumfntExdfption("Cbnnot writf to filf:"+
                                                        dfstStr);
                 }
            }
        }
    }

    publid boolfbn printDiblog() {

        boolfbn prodffdWitiPrint = fblsf;

        printfrJob = PrintfrJob.gftPrintfrJob();
        if (printfrJob == null) {
            rfturn fblsf;
        }
        DiblogTypf d = tiis.jobAttributfs.gftDiblog();
        PrintSfrvidf pSfrv = printfrJob.gftPrintSfrvidf();
        if ((pSfrv == null) &&  (d == DiblogTypf.NONE)){
            rfturn fblsf;
        }
        dopyAttributfs(pSfrv);

        DffbultSflfdtionTypf sflfdt =
            tiis.jobAttributfs.gftDffbultSflfdtion();
        if (sflfdt == DffbultSflfdtionTypf.RANGE) {
            bttributfs.bdd(SunPbgfSflfdtion.RANGE);
        } flsf if (sflfdt == DffbultSflfdtionTypf.SELECTION) {
            bttributfs.bdd(SunPbgfSflfdtion.SELECTION);
        } flsf {
            bttributfs.bdd(SunPbgfSflfdtion.ALL);
        }

        if (frbmf != null) {
             bttributfs.bdd(nfw DiblogOwnfr(frbmf));
         }

        if ( d == DiblogTypf.NONE) {
            prodffdWitiPrint = truf;
        } flsf {
            if (d == DiblogTypf.NATIVE) {
                bttributfs.bdd(DiblogTypfSflfdtion.NATIVE);
            }  flsf { //  (d == DiblogTypf.COMMON)
                bttributfs.bdd(DiblogTypfSflfdtion.COMMON);
            }
            if (prodffdWitiPrint = printfrJob.printDiblog(bttributfs)) {
                if (pSfrv == null) {
                    // Windows givfs bn option to instbll b sfrvidf
                    // wifn it dftfdts tifrf brf no printfrs so
                    // wf mbkf surf wf gft tif updbtfd print sfrvidf.
                    pSfrv = printfrJob.gftPrintSfrvidf();
                    if (pSfrv == null) {
                        rfturn fblsf;
                    }
                }
                updbtfAttributfs();
                trbnslbtfOutputProps();
            }
        }

        if (prodffdWitiPrint) {

            JobNbmf jnbmf = (JobNbmf)bttributfs.gft(JobNbmf.dlbss);
            if (jnbmf != null) {
                printfrJob.sftJobNbmf(jnbmf.toString());
            }

            pbgfFormbt = nfw PbgfFormbt();

            Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
            MfdibSizf mfdibSizf =  null;
            if (mfdib != null  && mfdib instbndfof MfdibSizfNbmf) {
                mfdibSizf = MfdibSizf.gftMfdibSizfForNbmf((MfdibSizfNbmf)mfdib);
            }

            Pbpfr p = pbgfFormbt.gftPbpfr();
            if (mfdibSizf != null) {
                p.sftSizf(mfdibSizf.gftX(MfdibSizf.INCH)*72.0,
                          mfdibSizf.gftY(MfdibSizf.INCH)*72.0);
            }

            if (pbgfAttributfs.gftOrigin()==OriginTypf.PRINTABLE) {
                // AWT usfs 1/4" bordfrs by dffbult
                p.sftImbgfbblfArfb(18.0, 18.0,
                                   p.gftWidti()-36.0,
                                   p.gftHfigit()-36.0);
            } flsf {
                p.sftImbgfbblfArfb(0.0,0.0,p.gftWidti(),p.gftHfigit());
            }

            pbgfFormbt.sftPbpfr(p);

            OrifntbtionRfqufstfd orifnt =
               (OrifntbtionRfqufstfd)bttributfs.gft(OrifntbtionRfqufstfd.dlbss);
            if (orifnt!= null &&
                orifnt == OrifntbtionRfqufstfd.REVERSE_LANDSCAPE) {
                pbgfFormbt.sftOrifntbtion(PbgfFormbt.REVERSE_LANDSCAPE);
            } flsf if (orifnt == OrifntbtionRfqufstfd.LANDSCAPE) {
                pbgfFormbt.sftOrifntbtion(PbgfFormbt.LANDSCAPE);
            } flsf {
                pbgfFormbt.sftOrifntbtion(PbgfFormbt.PORTRAIT);
                }

            printfrJob.sftPrintbblf(tiis, pbgfFormbt);

        }

        rfturn prodffdWitiPrint;
    }

    privbtf void updbtfAttributfs() {
        Copifs d = (Copifs)bttributfs.gft(Copifs.dlbss);
        jobAttributfs.sftCopifs(d.gftVbluf());

        SunPbgfSflfdtion sfl =
            (SunPbgfSflfdtion)bttributfs.gft(SunPbgfSflfdtion.dlbss);
        if (sfl == SunPbgfSflfdtion.RANGE) {
            jobAttributfs.sftDffbultSflfdtion(DffbultSflfdtionTypf.RANGE);
        } flsf if (sfl == SunPbgfSflfdtion.SELECTION) {
            jobAttributfs.sftDffbultSflfdtion(DffbultSflfdtionTypf.SELECTION);
        } flsf {
            jobAttributfs.sftDffbultSflfdtion(DffbultSflfdtionTypf.ALL);
        }

        Dfstinbtion dfst = (Dfstinbtion)bttributfs.gft(Dfstinbtion.dlbss);
        if (dfst != null) {
            jobAttributfs.sftDfstinbtion(DfstinbtionTypf.FILE);
            jobAttributfs.sftFilfNbmf(dfst.gftURI().gftPbti());
        } flsf {
            jobAttributfs.sftDfstinbtion(DfstinbtionTypf.PRINTER);
        }

        PrintSfrvidf sfrv = printfrJob.gftPrintSfrvidf();
        if (sfrv != null) {
            jobAttributfs.sftPrintfr(sfrv.gftNbmf());
        }

        PbgfRbngfs rbngf = (PbgfRbngfs)bttributfs.gft(PbgfRbngfs.dlbss);
        int[][] mfmbfrs = rbngf.gftMfmbfrs();
        jobAttributfs.sftPbgfRbngfs(mfmbfrs);

        SifftCollbtf dollbtion =
            (SifftCollbtf)bttributfs.gft(SifftCollbtf.dlbss);
        if (dollbtion == SifftCollbtf.COLLATED) {
            jobAttributfs.sftMultiplfDodumfntHbndling(
            MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_COLLATED_COPIES);
        } flsf {
            jobAttributfs.sftMultiplfDodumfntHbndling(
            MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES);
        }

        Sidfs sidfs = (Sidfs)bttributfs.gft(Sidfs.dlbss);
        if (sidfs == Sidfs.TWO_SIDED_LONG_EDGE) {
            jobAttributfs.sftSidfs(SidfsTypf.TWO_SIDED_LONG_EDGE);
        } flsf if (sidfs == Sidfs.TWO_SIDED_SHORT_EDGE) {
            jobAttributfs.sftSidfs(SidfsTypf.TWO_SIDED_SHORT_EDGE);
        } flsf {
            jobAttributfs.sftSidfs(SidfsTypf.ONE_SIDED);
        }

        // PbgfAttributfs

        Cirombtidity dolor =
            (Cirombtidity)bttributfs.gft(Cirombtidity.dlbss);
        if (dolor == Cirombtidity.COLOR) {
            pbgfAttributfs.sftColor(ColorTypf.COLOR);
        } flsf {
            pbgfAttributfs.sftColor(ColorTypf.MONOCHROME);
        }

        OrifntbtionRfqufstfd orifnt =
            (OrifntbtionRfqufstfd)bttributfs.gft(OrifntbtionRfqufstfd.dlbss);
        if (orifnt == OrifntbtionRfqufstfd.LANDSCAPE) {
            pbgfAttributfs.sftOrifntbtionRfqufstfd(
                                       OrifntbtionRfqufstfdTypf.LANDSCAPE);
        } flsf {
            pbgfAttributfs.sftOrifntbtionRfqufstfd(
                                       OrifntbtionRfqufstfdTypf.PORTRAIT);
        }

        PrintQublity qubl = (PrintQublity)bttributfs.gft(PrintQublity.dlbss);
        if (qubl == PrintQublity.DRAFT) {
            pbgfAttributfs.sftPrintQublity(PrintQublityTypf.DRAFT);
        } flsf if (qubl == PrintQublity.HIGH) {
            pbgfAttributfs.sftPrintQublity(PrintQublityTypf.HIGH);
        } flsf { // NORMAL
            pbgfAttributfs.sftPrintQublity(PrintQublityTypf.NORMAL);
        }

        Mfdib msn = (Mfdib)bttributfs.gft(Mfdib.dlbss);
        if (msn != null && msn instbndfof MfdibSizfNbmf) {
            MfdibTypf mTypf = unMbpMfdib((MfdibSizfNbmf)msn);

            if (mTypf != null) {
                pbgfAttributfs.sftMfdib(mTypf);
            }
        }
        dfbugPrintAttributfs(fblsf, fblsf);
    }

    privbtf void dfbugPrintAttributfs(boolfbn jb, boolfbn pb ) {
        if (jb) {
            Systfm.out.println("nfw Attributfs\ndopifs = "+
                               jobAttributfs.gftCopifs()+
                               "\nsflfdtion = "+
                               jobAttributfs.gftDffbultSflfdtion()+
                               "\ndfst "+jobAttributfs.gftDfstinbtion()+
                               "\nfilf "+jobAttributfs.gftFilfNbmf()+
                               "\nfromPbgf "+jobAttributfs.gftFromPbgf()+
                               "\ntoPbgf "+jobAttributfs.gftToPbgf()+
                               "\ndollbtion "+
                               jobAttributfs.gftMultiplfDodumfntHbndling()+
                               "\nPrintfr "+jobAttributfs.gftPrintfr()+
                               "\nSidfs2 "+jobAttributfs.gftSidfs()
                               );
        }

        if (pb) {
            Systfm.out.println("nfw Attributfs\ndolor = "+
                               pbgfAttributfs.gftColor()+
                               "\norifntbtion = "+
                               pbgfAttributfs.gftOrifntbtionRfqufstfd()+
                               "\nqublity "+pbgfAttributfs.gftPrintQublity()+
                               "\nMfdib2 "+pbgfAttributfs.gftMfdib()
                               );
        }
    }


    /* From JobAttributfs wf will dopy job nbmf bnd duplfx printing
     * bnd dfstinbtion.
     * Tif mbjority of tif rfst of tif bttributfs brf rfflfdtfd
     * bttributfs.
     *
     * From PbgfAttributfs wf dopy dolor, mfdib sizf, orifntbtion,
     * origin typf, rfsolution bnd print qublity.
     * Wf usf tif mfdib, orifntbtion in drfbting tif pbgf formbt, bnd
     * tif origin typf to sft its imbgfbblf brfb.
     *
     * REMIND: Intfrprftbtion of rfsolution, bdditionbl mfdib sizfs.
     */
    privbtf void dopyAttributfs(PrintSfrvidf printSfrv) {

        bttributfs = nfw HbsiPrintRfqufstAttributfSft();
        bttributfs.bdd(nfw JobNbmf(dodTitlf, null));
        PrintSfrvidf pSfrv = printSfrv;

        String printfrNbmf = jobAttributfs.gftPrintfr();
        if (printfrNbmf != null && printfrNbmf != ""
            && !printfrNbmf.fqubls(pSfrv.gftNbmf())) {

            // Sfbrdi for tif givfn printfrNbmf in tif list of PrintSfrvidfs
            PrintSfrvidf []sfrvidfs = PrintfrJob.lookupPrintSfrvidfs();
            try {
                for (int i=0; i<sfrvidfs.lfngti; i++) {
                    if (printfrNbmf.fqubls(sfrvidfs[i].gftNbmf())) {
                        printfrJob.sftPrintSfrvidf(sfrvidfs[i]);
                        pSfrv = sfrvidfs[i];
                        brfbk;
                    }
                }
            } dbtdi (PrintfrExdfption pf) {
            }
        }

        DfstinbtionTypf dfst = jobAttributfs.gftDfstinbtion();
        if (dfst == DfstinbtionTypf.FILE &&
            pSfrv.isAttributfCbtfgorySupportfd(Dfstinbtion.dlbss)) {

            String filfNbmf = jobAttributfs.gftFilfNbmf();

            Dfstinbtion dffbultDfst;
            if (filfNbmf == null && (dffbultDfst = (Dfstinbtion)pSfrv.
                    gftDffbultAttributfVbluf(Dfstinbtion.dlbss)) != null) {
                bttributfs.bdd(dffbultDfst);
            } flsf {
                URI uri = null;
                try {
                    if (filfNbmf != null) {
                        if (filfNbmf.fqubls("")) {
                            filfNbmf = ".";
                        }
                    } flsf {
                        // dffbultDfst siould not bf null.  Tif following dodf
                        // is only bddfd to sbffgubrd bgbinst b possiblf
                        // buggy implfmfntbtion of b PrintSfrvidf ibving b
                        // null dffbult Dfstinbtion.
                        filfNbmf = "out.prn";
                    }
                    uri = (nfw Filf(filfNbmf)).toURI();
                } dbtdi (SfdurityExdfption sf) {
                    try {
                        // '\\' filf sfpbrbtor is illfgbl dibrbdtfr in opbquf
                        // pbrt bnd dbusfs URISyntbxExdfption, so wf rfplbdf
                        // it witi '/'
                        filfNbmf = filfNbmf.rfplbdf('\\', '/');
                        uri = nfw URI("filf:"+filfNbmf);
                    } dbtdi (URISyntbxExdfption f) {
                    }
                }
                if (uri != null) {
                    bttributfs.bdd(nfw Dfstinbtion(uri));
                }
            }
        }
        bttributfs.bdd(nfw SunMinMbxPbgf(jobAttributfs.gftMinPbgf(),
                                         jobAttributfs.gftMbxPbgf()));
        SidfsTypf sTypf = jobAttributfs.gftSidfs();
        if (sTypf == SidfsTypf.TWO_SIDED_LONG_EDGE) {
            bttributfs.bdd(Sidfs.TWO_SIDED_LONG_EDGE);
        } flsf if (sTypf == SidfsTypf.TWO_SIDED_SHORT_EDGE) {
            bttributfs.bdd(Sidfs.TWO_SIDED_SHORT_EDGE);
        } flsf if (sTypf == SidfsTypf.ONE_SIDED) {
            bttributfs.bdd(Sidfs.ONE_SIDED);
        }

        MultiplfDodumfntHbndlingTypf iTypf =
          jobAttributfs.gftMultiplfDodumfntHbndling();
        if (iTypf ==
            MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_COLLATED_COPIES) {
          bttributfs.bdd(SifftCollbtf.COLLATED);
        } flsf {
          bttributfs.bdd(SifftCollbtf.UNCOLLATED);
        }

        bttributfs.bdd(nfw Copifs(jobAttributfs.gftCopifs()));

        bttributfs.bdd(nfw PbgfRbngfs(jobAttributfs.gftFromPbgf(),
                                      jobAttributfs.gftToPbgf()));

        if (pbgfAttributfs.gftColor() == ColorTypf.COLOR) {
            bttributfs.bdd(Cirombtidity.COLOR);
        } flsf {
            bttributfs.bdd(Cirombtidity.MONOCHROME);
        }

        pbgfFormbt = printfrJob.dffbultPbgf();
        if (pbgfAttributfs.gftOrifntbtionRfqufstfd() ==
            OrifntbtionRfqufstfdTypf.LANDSCAPE) {
            pbgfFormbt.sftOrifntbtion(PbgfFormbt.LANDSCAPE);
                bttributfs.bdd(OrifntbtionRfqufstfd.LANDSCAPE);
        } flsf {
                pbgfFormbt.sftOrifntbtion(PbgfFormbt.PORTRAIT);
                bttributfs.bdd(OrifntbtionRfqufstfd.PORTRAIT);
        }

        MfdibTypf mfdib = pbgfAttributfs.gftMfdib();
        MfdibSizfNbmf msn = mbpMfdib(mfdib);
        if (msn != null) {
            bttributfs.bdd(msn);
        }

        PrintQublityTypf qTypf =
            pbgfAttributfs.gftPrintQublity();
        if (qTypf == PrintQublityTypf.DRAFT) {
            bttributfs.bdd(PrintQublity.DRAFT);
        } flsf if (qTypf == PrintQublityTypf.NORMAL) {
            bttributfs.bdd(PrintQublity.NORMAL);
        } flsf if (qTypf == PrintQublityTypf.HIGH) {
            bttributfs.bdd(PrintQublity.HIGH);
        }
    }

    /**
     * Gfts b Grbpiids objfdt tibt will drbw to tif nfxt pbgf.
     * Tif pbgf is sfnt to tif printfr wifn tif grbpiids
     * objfdt is disposfd.  Tiis grbpiids objfdt will blso implfmfnt
     * tif PrintGrbpiids intfrfbdf.
     * @sff PrintGrbpiids
     */
    publid Grbpiids gftGrbpiids() {

        Grbpiids printGrbpiids = null;

        syndironizfd (tiis) {
            ++pbgfIndfx;

            // Tirfbd siould not bf drfbtfd bftfr fnd ibs bffn dbllfd.
            // Onf wby to dftfdt tiis is if bny of tif grbpiids qufuf
            //  ibs bffn dlosfd.
            if (pbgfIndfx == 0 && !grbpiidsToBfDrbwn.isClosfd()) {

            /* Wf stbrt b tirfbd on wiidi tif PrintfrJob will run.
             * Tif PrintfrJob will bsk for pbgfs on tibt tirfbd
             * bnd will usf b mfssbgf qufuf to fulfill tif bpplidbtion's
             * rfqufsts for b Grbpiids on tif bpplidbtion's
             * tirfbd.
             */

                stbrtPrintfrJobTirfbd();

            }
            notify();
        }

        /* If tif bpplidbtion ibs blrfbdy bffn ibndfd bbdk
         * b grbpiids tifn wf nffd to put tibt grbpiids into
         * tif drbwn qufuf so tibt tif PrintfrJob tirfbd dbn
         * rfturn to tif print systfm.
         */
        if (durrfntGrbpiids != null) {
            grbpiidsDrbwn.bppfnd(durrfntGrbpiids);
            durrfntGrbpiids = null;
        }

        /* Wf'll blodk ifrf until b nfw grbpiids bfdomfs
         * bvbilbblf.
         */

        durrfntGrbpiids = grbpiidsToBfDrbwn.pop();

        if (durrfntGrbpiids instbndfof PffkGrbpiids) {
            ( (PffkGrbpiids) durrfntGrbpiids).sftAWTDrbwingOnly();
            grbpiidsDrbwn.bppfnd(durrfntGrbpiids);
            durrfntGrbpiids = grbpiidsToBfDrbwn.pop();
        }


        if (durrfntGrbpiids != null) {

            /* In tif PrintJob API, tif origin is bt tif uppfr-
             * lfft of tif imbgfbblf brfb wifn using tif nfw "printbblf"
             * origin bttributf, otifrwisf its tif piysidbl origin (for
             * bbdkwbrds dompbtibility. Wf fmulbtf tiis by drfbtfing
             * b PbgfFormbt wiidi mbtdifs bnd tifn pfrforming tif
             * trbnslbtf to tif origin. Tiis is b no-op if piysidbl
             * origin is spfdififd.
             */
            durrfntGrbpiids.trbnslbtf(pbgfFormbt.gftImbgfbblfX(),
                                      pbgfFormbt.gftImbgfbblfY());

            /* Sdblf to bddommodbtf AWT's notion of printfr rfsolution */
            doublf bwtSdblf = 72.0/gftPbgfRfsolutionIntfrnbl();
            durrfntGrbpiids.sdblf(bwtSdblf, bwtSdblf);

            /* Tif dbllfr wbnts b Grbpiids instbndf but wf do
             * not wbnt tifm to mbkf 2D dblls. Wf dbn't ibnd
             * bbdk b Grbpiids2D. Tif rfturnfd Grbpiids blso
             * nffds to implfmfnt PrintGrbpiids, so wf wrbp
             * tif Grbpiids2D instbndf. Tif PrintJob API ibs
             * tif bpplidbtion disposf of tif Grbpiids so
             * wf drfbtf b dopy of tif onf rfturnfd by PrintfrJob.
             */
            printGrbpiids = nfw ProxyPrintGrbpiids(durrfntGrbpiids.drfbtf(),
                                                   tiis);

        }

        rfturn printGrbpiids;
    }

    /**
     * Rfturns tif dimfnsions of tif pbgf in pixfls.
     * Tif rfsolution of tif pbgf is diosfn so tibt it
     * is similbr to tif sdrffn rfsolution.
     * Exdfpt (sindf 1.3) wifn tif bpplidbtion spfdififs b rfsolution.
     * In tibt dbsf it it sdblfd bddordingly.
     */
    publid Dimfnsion gftPbgfDimfnsion() {
        doublf wid, igt, sdblf;
        if (pbgfAttributfs != null &&
            pbgfAttributfs.gftOrigin()==OriginTypf.PRINTABLE) {
            wid = pbgfFormbt.gftImbgfbblfWidti();
            igt = pbgfFormbt.gftImbgfbblfHfigit();
        } flsf {
            wid = pbgfFormbt.gftWidti();
            igt = pbgfFormbt.gftHfigit();
        }
        sdblf = gftPbgfRfsolutionIntfrnbl() / 72.0;
        rfturn nfw Dimfnsion((int)(wid * sdblf), (int)(igt * sdblf));
    }

     privbtf doublf gftPbgfRfsolutionIntfrnbl() {
        if (pbgfAttributfs != null) {
            int []rfs = pbgfAttributfs.gftPrintfrRfsolution();
            if (rfs[2] == 3) {
                rfturn rfs[0];
            } flsf /* if (rfs[2] == 4) */ {
                rfturn (rfs[0] * 2.54);
            }
        } flsf {
            rfturn 72.0;
        }
    }

    /**
     * Rfturns tif rfsolution of tif pbgf in pixfls pfr indi.
     * Notf tibt tiis dofsn't ibvf to dorrfspond to tif piysidbl
     * rfsolution of tif printfr.
     */
    publid int gftPbgfRfsolution() {
        rfturn (int)gftPbgfRfsolutionIntfrnbl();
    }

    /**
     * Rfturns truf if tif lbst pbgf will bf printfd first.
     */
    publid boolfbn lbstPbgfFirst() {
        rfturn fblsf;
    }

    /**
     * Ends tif print job bnd dofs bny nfdfssbry dlfbnup.
     */
    publid syndironizfd void fnd() {

        /* Prfvfnt tif PrintfrJob tirfbd from bppfnding bny morf
         * grbpiids to tif to-bf-drbwn qufuf
         */
        grbpiidsToBfDrbwn.dlosf();

        /* If wf ibvf b durrfntGrbpiids it wbs tif lbst onf rfturnfd to tif
         * PrintJob dlifnt. Appfnd it to tif drbwn qufuf so tibt print()
         * will rfturn bllowing tif pbgf to bf flusifd.
         * Tiis rfblly ougit to ibppfn in disposf() but for wibtfvfr rfbson
         * tibt isn't iow tif old PrintJob workfd fvfn tiougi its spfd
         * sbid disposf() flusifd tif pbgf.
         */
        if (durrfntGrbpiids != null) {
            grbpiidsDrbwn.bppfnd(durrfntGrbpiids);
        }
        grbpiidsDrbwn.dlosfWifnEmpty();

        /* Wbit for tif PrintfrJob.print() tirfbd to tfrminbtf, fnsuring
         * tibt RbstfrPrintfrJob ibs mbdf its fnd dod dbll, bnd rfsourdfs
         * brf rflfbsfd, filfs dlosfd ftd.
         */
        if( printfrJobTirfbd != null && printfrJobTirfbd.isAlivf() ){
            try {
                printfrJobTirfbd.join();
            } dbtdi (IntfrruptfdExdfption f) {
            }
        }
    }

    /**
     * Ends tiis print job ondf it is no longfr rfffrfndfd.
     * @sff #fnd
     */
    publid void finblizf() {
        fnd();
    }

    /**
     * Prints tif pbgf bt tif spfdififd indfx into tif spfdififd
     * {@link Grbpiids} dontfxt in tif spfdififd
     * formbt.  A <dodf>PrintfrJob</dodf> dblls tif
     * <dodf>Printbblf</dodf> intfrfbdf to rfqufst tibt b pbgf bf
     * rfndfrfd into tif dontfxt spfdififd by
     * <dodf>grbpiids</dodf>.  Tif formbt of tif pbgf to bf drbwn is
     * spfdififd by <dodf>pbgfFormbt</dodf>.  Tif zfro bbsfd indfx
     * of tif rfqufstfd pbgf is spfdififd by <dodf>pbgfIndfx</dodf>.
     * If tif rfqufstfd pbgf dofs not fxist tifn tiis mftiod rfturns
     * NO_SUCH_PAGE; otifrwisf PAGE_EXISTS is rfturnfd.
     * Tif <dodf>Grbpiids</dodf> dlbss or subdlbss implfmfnts tif
     * {@link PrintfrGrbpiids} intfrfbdf to providf bdditionbl
     * informbtion.  If tif <dodf>Printbblf</dodf> objfdt
     * bborts tif print job tifn it tirows b {@link PrintfrExdfption}.
     * @pbrbm grbpiids tif dontfxt into wiidi tif pbgf is drbwn
     * @pbrbm pbgfFormbt tif sizf bnd orifntbtion of tif pbgf bfing drbwn
     * @pbrbm pbgfIndfx tif zfro bbsfd indfx of tif pbgf to bf drbwn
     * @rfturn PAGE_EXISTS if tif pbgf is rfndfrfd suddfssfully
     *         or NO_SUCH_PAGE if <dodf>pbgfIndfx</dodf> spfdififs b
     *         non-fxistfnt pbgf.
     * @fxdfption jbvb.bwt.print.PrintfrExdfption
     *         tirown wifn tif print job is tfrminbtfd.
     */
    publid int print(Grbpiids grbpiids, PbgfFormbt pbgfFormbt, int pbgfIndfx)
                 tirows PrintfrExdfption {

        int rfsult;

        /* Tiis mftiod will bf dbllfd by tif PrintfrJob on b tirfbd otifr
         * tibt tif bpplidbtion's tirfbd. Wf iold on to tif grbpiids
         * until wf dbn rfndfvous witi tif bpplidbtion's tirfbd bnd
         * ibnd ovfr tif grbpiids. Tif bpplidbtion tifn dofs bll tif
         * drbwing. Wifn tif bpplidbtion is donf drbwing wf rfndfvous
         * bgbin witi tif PrintfrJob tirfbd bnd rflfbsf tif Grbpiids
         * so tibt it knows wf brf donf.
         */

        /* Add tif grbpiids to tif mfssbgf qufuf of grbpiids to
         * bf rfndfrfd. Tiis is rfblly b onf slot qufuf. Tif
         * bpplidbtion's tirfbd will domf blong bnd rfmovf tif
         * grbpiids from tif qufuf wifn tif bpp bsks for b grbpiids.
         */
        grbpiidsToBfDrbwn.bppfnd( (Grbpiids2D) grbpiids);

        /* Wf now wbit for tif bpp's tirfbd to finisi drbwing on
         * tif Grbpiids. Tiis tirfbd will slffp until tif bpplidbtion
         * rflfbsf tif grbpiids by plbding it in tif grbpiids drbwn
         * mfssbgf qufuf. If tif bpplidbtion signbls tibt it is
         * finisifd drbwing tif fntirf dodumfnt tifn wf'll gft null
         * rfturnfd wifn wf try bnd pop b finisifd grbpiid.
         */
        if (grbpiidsDrbwn.pop() != null) {
            rfsult = PAGE_EXISTS;
        } flsf {
            rfsult = NO_SUCH_PAGE;
        }

        rfturn rfsult;
    }

    privbtf void stbrtPrintfrJobTirfbd() {

        printfrJobTirfbd = nfw Tirfbd(tiis, "printfrJobTirfbd");
        printfrJobTirfbd.stbrt();
    }


    publid void run() {

        try {
            printfrJob.print(bttributfs);
        } dbtdi (PrintfrExdfption f) {
            //REMIND: nffd to storf tiis bwby bnd not rftirow it.
        }

        /* Closf tif mfssbgf qufufs so tibt nobody is studk
         * wbiting for onf.
         */
        grbpiidsToBfDrbwn.dlosfWifnEmpty();
        grbpiidsDrbwn.dlosf();
    }

    privbtf dlbss MfssbgfQ {

        privbtf String qid="nonbmf";

        privbtf ArrbyList<Grbpiids2D> qufuf = nfw ArrbyList<>();

        MfssbgfQ(String id) {
          qid = id;
        }

        syndironizfd void dlosfWifnEmpty() {

            wiilf (qufuf != null && qufuf.sizf() > 0) {
                try {
                    wbit(1000);
                } dbtdi (IntfrruptfdExdfption f) {
                    // do notiing.
                }
            }

            qufuf = null;
            notifyAll();
        }

        syndironizfd void dlosf() {
            qufuf = null;
            notifyAll();
        }

        syndironizfd boolfbn bppfnd(Grbpiids2D g) {

            boolfbn qufufd = fblsf;

            if (qufuf != null) {
                qufuf.bdd(g);
                qufufd = truf;
                notify();
            }

            rfturn qufufd;
        }

        syndironizfd Grbpiids2D pop() {
            Grbpiids2D g = null;

            wiilf (g == null && qufuf != null) {

                if (qufuf.sizf() > 0) {
                    g = qufuf.rfmovf(0);
                    notify();

                } flsf {
                    try {
                        wbit(2000);
                    } dbtdi (IntfrruptfdExdfption f) {
                        // do notiing.
                    }
                }
            }

            rfturn g;
        }

        syndironizfd boolfbn isClosfd() {
            rfturn qufuf == null;
        }

    }


    privbtf stbtid int[] gftSizf(MfdibTypf mTypf) {
        int []dim = nfw int[2];
        dim[0] = 612;
        dim[1] = 792;

        for (int i=0; i < SIZES.lfngti; i++) {
            if (SIZES[i] == mTypf) {
                dim[0] = WIDTHS[i];
                dim[1] = LENGTHS[i];
                brfbk;
            }
        }
        rfturn dim;
    }

    publid stbtid MfdibSizfNbmf mbpMfdib(MfdibTypf mTypf) {
        MfdibSizfNbmf mfdib = null;

        // JAVAXSIZES.lfngti bnd SIZES.lfngti must bf fqubl!
        // Attfmpt to rfdovfr by gftting tif smbllfr sizf.
        int lfngti = Mbti.min(SIZES.lfngti, JAVAXSIZES.lfngti);

        for (int i=0; i < lfngti; i++) {
            if (SIZES[i] == mTypf) {
                if ((JAVAXSIZES[i] != null) &&
                    MfdibSizf.gftMfdibSizfForNbmf(JAVAXSIZES[i]) != null) {
                    mfdib = JAVAXSIZES[i];
                    brfbk;
                } flsf {
                    /* drfbtf Custom Mfdib */
                    mfdib = nfw CustomMfdibSizfNbmf(SIZES[i].toString());

                    flobt w = (flobt)Mbti.rint(WIDTHS[i]  / 72.0);
                    flobt i = (flobt)Mbti.rint(LENGTHS[i] / 72.0);
                    if (w > 0.0 && i > 0.0) {
                        // bdd nfw drfbtfd MfdibSizf to our stbtid mbp
                        // so it will bf found wifn wf dbll findMfdib
                        nfw MfdibSizf(w, i, Sizf2DSyntbx.INCH, mfdib);
                    }

                    brfbk;
                }
            }
        }
        rfturn mfdib;
    }


    publid stbtid MfdibTypf unMbpMfdib(MfdibSizfNbmf mSizf) {
        MfdibTypf mfdib = null;

        // JAVAXSIZES.lfngti bnd SIZES.lfngti must bf fqubl!
        // Attfmpt to rfdovfr by gftting tif smbllfr sizf.
        int lfngti = Mbti.min(SIZES.lfngti, JAVAXSIZES.lfngti);

        for (int i=0; i < lfngti; i++) {
            if (JAVAXSIZES[i] == mSizf) {
                if (SIZES[i] != null) {
                    mfdib = SIZES[i];
                    brfbk;
                }
            }
        }
        rfturn mfdib;
    }

    privbtf void trbnslbtfInputProps() {
        if (props == null) {
            rfturn;
        }

        String str;

        str = props.gftPropfrty(DEST_PROP);
        if (str != null) {
            if (str.fqubls(PRINTER)) {
                jobAttributfs.sftDfstinbtion(DfstinbtionTypf.PRINTER);
            } flsf if (str.fqubls(FILE)) {
                jobAttributfs.sftDfstinbtion(DfstinbtionTypf.FILE);
            }
        }
        str = props.gftPropfrty(PRINTER_PROP);
        if (str != null) {
            jobAttributfs.sftPrintfr(str);
        }
        str = props.gftPropfrty(FILENAME_PROP);
        if (str != null) {
            jobAttributfs.sftFilfNbmf(str);
        }
        str = props.gftPropfrty(NUMCOPIES_PROP);
        if (str != null) {
            jobAttributfs.sftCopifs(Intfgfr.pbrsfInt(str));
        }

        tiis.options = props.gftPropfrty(OPTIONS_PROP, "");

        str = props.gftPropfrty(ORIENT_PROP);
        if (str != null) {
            if (str.fqubls(PORTRAIT)) {
                pbgfAttributfs.sftOrifntbtionRfqufstfd(
                                        OrifntbtionRfqufstfdTypf.PORTRAIT);
            } flsf if (str.fqubls(LANDSCAPE)) {
                pbgfAttributfs.sftOrifntbtionRfqufstfd(
                                        OrifntbtionRfqufstfdTypf.LANDSCAPE);
            }
        }
        str = props.gftPropfrty(PAPERSIZE_PROP);
        if (str != null) {
            if (str.fqubls(LETTER)) {
                pbgfAttributfs.sftMfdib(SIZES[MfdibTypf.LETTER.ibsiCodf()]);
            } flsf if (str.fqubls(LEGAL)) {
                pbgfAttributfs.sftMfdib(SIZES[MfdibTypf.LEGAL.ibsiCodf()]);
            } flsf if (str.fqubls(EXECUTIVE)) {
                pbgfAttributfs.sftMfdib(SIZES[MfdibTypf.EXECUTIVE.ibsiCodf()]);
            } flsf if (str.fqubls(A4)) {
                pbgfAttributfs.sftMfdib(SIZES[MfdibTypf.A4.ibsiCodf()]);
            }
        }
    }

    privbtf void trbnslbtfOutputProps() {
        if (props == null) {
            rfturn;
        }

        String str;

        props.sftPropfrty(DEST_PROP,
            (jobAttributfs.gftDfstinbtion() == DfstinbtionTypf.PRINTER) ?
                          PRINTER : FILE);
        str = jobAttributfs.gftPrintfr();
        if (str != null && !str.fqubls("")) {
            props.sftPropfrty(PRINTER_PROP, str);
        }
        str = jobAttributfs.gftFilfNbmf();
        if (str != null && !str.fqubls("")) {
            props.sftPropfrty(FILENAME_PROP, str);
        }
        int dopifs = jobAttributfs.gftCopifs();
        if (dopifs > 0) {
            props.sftPropfrty(NUMCOPIES_PROP, "" + dopifs);
        }
        str = tiis.options;
        if (str != null && !str.fqubls("")) {
            props.sftPropfrty(OPTIONS_PROP, str);
        }
        props.sftPropfrty(ORIENT_PROP,
            (pbgfAttributfs.gftOrifntbtionRfqufstfd() ==
             OrifntbtionRfqufstfdTypf.PORTRAIT)
                          ? PORTRAIT : LANDSCAPE);
        MfdibTypf mfdib = SIZES[pbgfAttributfs.gftMfdib().ibsiCodf()];
        if (mfdib == MfdibTypf.LETTER) {
            str = LETTER;
        } flsf if (mfdib == MfdibTypf.LEGAL) {
            str = LEGAL;
        } flsf if (mfdib == MfdibTypf.EXECUTIVE) {
            str = EXECUTIVE;
        } flsf if (mfdib == MfdibTypf.A4) {
            str = A4;
        } flsf {
            str = mfdib.toString();
        }
        props.sftPropfrty(PAPERSIZE_PROP, str);
    }

    privbtf void tirowPrintToFilf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        FilfPfrmission printToFilfPfrmission = null;
        if (sfdurity != null) {
            if (printToFilfPfrmission == null) {
                printToFilfPfrmission =
                    nfw FilfPfrmission("<<ALL FILES>>", "rfbd,writf");
            }
            sfdurity.difdkPfrmission(printToFilfPfrmission);
        }
    }

}
